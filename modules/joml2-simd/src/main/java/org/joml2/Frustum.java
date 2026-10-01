// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;
import org.joml2.internal.types.Float2Impl;
import org.joml2.internal.types.Float3Impl;
import org.joml2.internal.types.Float4x4Impl;
import org.joml2.internal.types.FloatAABBImpl;
import org.joml2.internal.types.FloatRectImpl;
import org.joml2.internal.types.FloatSphereImpl;

/**
 * The six clip planes of a view-projection matrix, for view-frustum culling.
 * <p>
 * {@link #set(Float4x4R, boolean, boolean)} - and the equivalent constructors -
 * extracts the planes from a matrix. They live in the space that matrix maps
 * <em>from</em>: pass a projection matrix to cull in view space, a view-projection
 * matrix to cull in world space, or a model-view-projection matrix to cull in model
 * space. Every plane normal points <em>into</em> the frustum, so a point is inside
 * exactly when its signed distance from all six planes is non-negative.
 * <p>
 * The planes are named by {@link FrustumPlane}: the {@code cullingPlane} family
 * returns one to name the plane that culled a box (or <code>null</code> when nothing
 * culled it),
 * {@link #distanceToPlane(float, float, float, float, float, float, FrustumPlane)}
 * takes one, and the <code>PLANE_MASK_*</code> constants are the matching bits of the
 * {@code mask} argument, which selects the planes to test.
 * <p>
 * The plane coefficients are unit length only when the frustum was built with
 * <code>allowTestSpheres = true</code>. The sphere tests and
 * {@link #distanceToPlane(float, float, float, float, float, float, FrustumPlane)}
 * need that normalization to be metric; the point, box, rectangle and line-segment
 * tests give the same verdicts either way.
 * <p>
 * The sphere, box and rectangle tests are <em>conservative</em>: each asks only
 * whether the volume lies completely outside one single plane, so a volume in a
 * corner region - outside the frustum, yet outside no single plane - is reported as
 * intersecting. They never report a false negative, which is what culling needs.
 * {@link #testPoint(float, float, float)} and
 * {@link #testLineSegment(float, float, float, float, float, float)} are exact.
 * <p>
 * A frustum is mutable and not thread-safe: {@link #set(Float4x4R)} overwrites the
 * planes in place.
 */
public class Frustum {

    /**
     * How a volume relates to the frustum.
     * <p>
     * {@link #INSIDE} is exact for boxes and spheres alike. {@link #INTERSECT} is
     * conservative: a volume lying in a corner region outside the frustum - outside
     * the frustum, yet outside no single plane - is reported as intersecting rather
     * than {@link #OUTSIDE}. The error never goes the other way, which is what
     * culling needs.
     */
    public enum Intersection {
        /** The volume lies completely inside the frustum. */
        INSIDE,
        /** The volume crosses at least one plane. */
        INTERSECT,
        /** The volume lies completely outside at least one plane. */
        OUTSIDE
    }

    /** {@code mask} bit selecting the left (-X) plane: {@code 1 << FrustumPlane.NX.ordinal()}, as a constant expression. */
    public static final int PLANE_MASK_NX = 1 << 0;
    /** {@code mask} bit selecting the right (+X) plane: {@code 1 << FrustumPlane.PX.ordinal()}, as a constant expression. */
    public static final int PLANE_MASK_PX = 1 << 1;
    /** {@code mask} bit selecting the bottom (-Y) plane: {@code 1 << FrustumPlane.NY.ordinal()}, as a constant expression. */
    public static final int PLANE_MASK_NY = 1 << 2;
    /** {@code mask} bit selecting the top (+Y) plane: {@code 1 << FrustumPlane.PY.ordinal()}, as a constant expression. */
    public static final int PLANE_MASK_PY = 1 << 3;
    /** {@code mask} bit selecting the near (-Z) plane: {@code 1 << FrustumPlane.NZ.ordinal()}, as a constant expression. */
    public static final int PLANE_MASK_NZ = 1 << 4;
    /** {@code mask} bit selecting the far (+Z) plane: {@code 1 << FrustumPlane.PZ.ordinal()}, as a constant expression. */
    public static final int PLANE_MASK_PZ = 1 << 5;

    private float nxX, nxY, nxZ, nxW;
    private float pxX, pxY, pxZ, pxW;
    private float nyX, nyY, nyZ, nyW;
    private float pyX, pyY, pyZ, pyW;
    private float nzX, nzY, nzZ, nzW;
    private float pzX, pzY, pzZ, pzW;
    // |normal| per plane, kept by set(): the box tests use the centre/half-extent form
    // dot(n, c) +- dot(|n|, e), which is branch-free and fuses into one fma chain.
    private float nxAX, nxAY, nxAZ;
    private float pxAX, pxAY, pxAZ;
    private float nyAX, nyAY, nyAZ;
    private float pyAX, pyAY, pyAZ;
    private float nzAX, nzAY, nzAZ;
    private float pzAX, pzAY, pzAZ;

    /**
     * {@return whether any plane coefficient of this frustum is NaN} That is the case exactly when
     * the matrix it was last {@link #set(Float4x4) set} from produced a NaN plane, e.g. a matrix
     * holding a NaN element.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public boolean isNaN() {
        return Float.isNaN(nxX) || Float.isNaN(nxY) || Float.isNaN(nxZ) || Float.isNaN(nxW)
            || Float.isNaN(pxX) || Float.isNaN(pxY) || Float.isNaN(pxZ) || Float.isNaN(pxW)
            || Float.isNaN(nyX) || Float.isNaN(nyY) || Float.isNaN(nyZ) || Float.isNaN(nyW)
            || Float.isNaN(pyX) || Float.isNaN(pyY) || Float.isNaN(pyZ) || Float.isNaN(pyW)
            || Float.isNaN(nzX) || Float.isNaN(nzY) || Float.isNaN(nzZ) || Float.isNaN(nzW)
            || Float.isNaN(pzX) || Float.isNaN(pzY) || Float.isNaN(pzZ) || Float.isNaN(pzW);
    }

    /**
     * Create a frustum whose six planes are all zero.
     * <p>
     * Fill them in with {@link #set(Float4x4R)} before testing anything: on an
     * all-zero frustum every signed distance is zero, so every test reports the
     * argument as visible (inside, or intersecting for {@code intersectSphere}).
     * <p>
     * Valid input: the default range of the package documentation.
     */
    public Frustum() {
    }

    /**
     * Create a frustum from the planes of {@code m}, normalized so sphere tests work
     * and assuming OpenGL's <code>[-1, 1]</code> clip-space depth range.
     * <p>
     * Equivalent to {@link #set(Float4x4R, boolean, boolean) set(m, true, false)}.
     * <p>
     * Valid input: {@code m} must be a projection or view-projection matrix.
     *
     * @param m the matrix to extract the frustum planes from
     */
    public Frustum(Float4x4R m) {
        set(m, true, false);
    }

    /**
     * Create a frustum from the planes of {@code m}, assuming OpenGL's
     * <code>[-1, 1]</code> clip-space depth range.
     * <p>
     * Equivalent to
     * {@link #set(Float4x4R, boolean, boolean) set(m, allowTestSpheres, false)}.
     * <p>
     * Valid input: {@code m} must be a projection or view-projection matrix.
     *
     * @param m the matrix to extract the frustum planes from
     * @param allowTestSpheres whether to normalize the planes so sphere tests work
     */
    public Frustum(Float4x4R m, boolean allowTestSpheres) {
        set(m, allowTestSpheres, false);
    }

    /**
     * Build the frustum from {@code m}, which uses the given clip-space depth
     * convention.
     * <p>
     * Valid input: {@code m} must be a projection or view-projection matrix.
     *
     * @param m the matrix to extract the frustum planes from
     * @param allowTestSpheres whether to normalize the planes so sphere tests work
     * @param zeroBasedDepth whether {@code m} maps the view volume onto a
     *        <code>[0, 1]</code> clip-space depth range (Vulkan / Direct3D, i.e.
     *        DepthRange ZERO_TO_ONE) instead of OpenGL's
     *        <code>[-1, 1]</code> (NEGATIVE_ONE_TO_ONE)
     */
    public Frustum(Float4x4R m, boolean allowTestSpheres, boolean zeroBasedDepth) {
        set(m, allowTestSpheres, zeroBasedDepth);
    }

    /**
     * Extract the six frustum planes from {@code m}, normalized so sphere tests work
     * and assuming OpenGL's <code>[-1, 1]</code> clip-space depth range.
     * <p>
     * Equivalent to {@link #set(Float4x4R, boolean, boolean) set(m, true, false)}.
     * <p>
     * Valid input: {@code m} must be a projection or view-projection matrix.
     *
     * @param m the matrix to extract the frustum planes from
     * @return this
     */
    public Frustum set(Float4x4R m) {
        if (org.joml2.internal.FmaSwitch.ON) return set_fma(m, true, false);
        return set_mulAdd(m, true, false);
    }

    /**
     * Extract the six frustum planes from {@code m}, assuming OpenGL's
     * <code>[-1, 1]</code> clip-space depth range.
     * <p>
     * Equivalent to
     * {@link #set(Float4x4R, boolean, boolean) set(m, allowTestSpheres, false)}.
     * <p>
     * Valid input: {@code m} must be a projection or view-projection matrix.
     *
     * @param m the matrix to extract the frustum planes from
     * @param allowTestSpheres whether to normalize the planes so sphere tests work
     * @return this
     */
    public Frustum set(Float4x4R m, boolean allowTestSpheres) {
        if (org.joml2.internal.FmaSwitch.ON) return set_fma(m, allowTestSpheres, false);
        return set_mulAdd(m, allowTestSpheres, false);
    }

    /**
     * Extract the six frustum planes from {@code m}.
     * <p>
     * The near/far planes depend on the clip-space depth convention {@code m}
     * was built with: OpenGL maps the view volume onto <code>[-1, 1]</code>, so
     * both planes come from a combination with the w row, whereas Vulkan /
     * Direct3D map it onto <code>[0, 1]</code>, where the near plane is the z
     * row alone. Passing the wrong value silently mis-places a plane and the
     * culling results are wrong.
     * <p>
     * Valid input: {@code m} must be a projection or view-projection matrix.
     *
     * @param m the matrix to extract the frustum planes from
     * @param allowTestSpheres whether to normalize the planes so sphere tests work
     * @param zeroBasedDepth whether {@code m} maps the view volume onto a
     *        <code>[0, 1]</code> clip-space depth range (Vulkan / Direct3D, i.e.
     *        DepthRange ZERO_TO_ONE) instead of OpenGL's
     *        <code>[-1, 1]</code> (NEGATIVE_ONE_TO_ONE)
     * @return this
     */
    public Frustum set(Float4x4R m, boolean allowTestSpheres, boolean zeroBasedDepth) {
        if (org.joml2.internal.FmaSwitch.ON) return set_fma(m, allowTestSpheres, zeroBasedDepth);
        return set_mulAdd(m, allowTestSpheres, zeroBasedDepth);
    }

    /** {@code set} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Frustum set_fma(Float4x4R m, boolean allowTestSpheres, boolean zeroBasedDepth) {
        float[] mData = ((Float4x4Impl) m).data;
        float invl;
        nxX = mData[3] + mData[0];
        nxY = mData[7] + mData[4];
        nxZ = mData[11] + mData[8];
        nxW = mData[15] + mData[12];
        if (allowTestSpheres) {
            invl = invLength_fma(nxX, nxY, nxZ);
            nxX *= invl; nxY *= invl; nxZ *= invl; nxW = scaledW(nxW, invl);
        }
        pxX = mData[3] - mData[0];
        pxY = mData[7] - mData[4];
        pxZ = mData[11] - mData[8];
        pxW = mData[15] - mData[12];
        if (allowTestSpheres) {
            invl = invLength_fma(pxX, pxY, pxZ);
            pxX *= invl; pxY *= invl; pxZ *= invl; pxW = scaledW(pxW, invl);
        }
        nyX = mData[3] + mData[1];
        nyY = mData[7] + mData[5];
        nyZ = mData[11] + mData[9];
        nyW = mData[15] + mData[13];
        if (allowTestSpheres) {
            invl = invLength_fma(nyX, nyY, nyZ);
            nyX *= invl; nyY *= invl; nyZ *= invl; nyW = scaledW(nyW, invl);
        }
        pyX = mData[3] - mData[1];
        pyY = mData[7] - mData[5];
        pyZ = mData[11] - mData[9];
        pyW = mData[15] - mData[13];
        if (allowTestSpheres) {
            invl = invLength_fma(pyX, pyY, pyZ);
            pyX *= invl; pyY *= invl; pyZ *= invl; pyW = scaledW(pyW, invl);
        }
        if (zeroBasedDepth) {
            nzX = mData[2]; nzY = mData[6]; nzZ = mData[10]; nzW = mData[14];
        } else {
            nzX = mData[3] + mData[2]; nzY = mData[7] + mData[6]; nzZ = mData[11] + mData[10]; nzW = mData[15] + mData[14];
        }
        if (allowTestSpheres) {
            invl = invLength_fma(nzX, nzY, nzZ);
            nzX *= invl; nzY *= invl; nzZ *= invl; nzW = scaledW(nzW, invl);
        }
        pzX = mData[3] - mData[2];
        pzY = mData[7] - mData[6];
        pzZ = mData[11] - mData[10];
        pzW = mData[15] - mData[14];
        if (allowTestSpheres) {
            invl = invLength_fma(pzX, pzY, pzZ);
            pzX *= invl; pzY *= invl; pzZ *= invl; pzW = scaledW(pzW, invl);
        }
        nxAX = java.lang.Math.abs(nxX);
        nxAY = java.lang.Math.abs(nxY);
        nxAZ = java.lang.Math.abs(nxZ);
        pxAX = java.lang.Math.abs(pxX);
        pxAY = java.lang.Math.abs(pxY);
        pxAZ = java.lang.Math.abs(pxZ);
        nyAX = java.lang.Math.abs(nyX);
        nyAY = java.lang.Math.abs(nyY);
        nyAZ = java.lang.Math.abs(nyZ);
        pyAX = java.lang.Math.abs(pyX);
        pyAY = java.lang.Math.abs(pyY);
        pyAZ = java.lang.Math.abs(pyZ);
        nzAX = java.lang.Math.abs(nzX);
        nzAY = java.lang.Math.abs(nzY);
        nzAZ = java.lang.Math.abs(nzZ);
        pzAX = java.lang.Math.abs(pzX);
        pzAY = java.lang.Math.abs(pzY);
        pzAZ = java.lang.Math.abs(pzZ);
        return this;
    }

    /** {@code set} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Frustum set_mulAdd(Float4x4R m, boolean allowTestSpheres, boolean zeroBasedDepth) {
        float[] mData = ((Float4x4Impl) m).data;
        float invl;
        nxX = mData[3] + mData[0];
        nxY = mData[7] + mData[4];
        nxZ = mData[11] + mData[8];
        nxW = mData[15] + mData[12];
        if (allowTestSpheres) {
            invl = invLength_mulAdd(nxX, nxY, nxZ);
            nxX *= invl; nxY *= invl; nxZ *= invl; nxW = scaledW(nxW, invl);
        }
        pxX = mData[3] - mData[0];
        pxY = mData[7] - mData[4];
        pxZ = mData[11] - mData[8];
        pxW = mData[15] - mData[12];
        if (allowTestSpheres) {
            invl = invLength_mulAdd(pxX, pxY, pxZ);
            pxX *= invl; pxY *= invl; pxZ *= invl; pxW = scaledW(pxW, invl);
        }
        nyX = mData[3] + mData[1];
        nyY = mData[7] + mData[5];
        nyZ = mData[11] + mData[9];
        nyW = mData[15] + mData[13];
        if (allowTestSpheres) {
            invl = invLength_mulAdd(nyX, nyY, nyZ);
            nyX *= invl; nyY *= invl; nyZ *= invl; nyW = scaledW(nyW, invl);
        }
        pyX = mData[3] - mData[1];
        pyY = mData[7] - mData[5];
        pyZ = mData[11] - mData[9];
        pyW = mData[15] - mData[13];
        if (allowTestSpheres) {
            invl = invLength_mulAdd(pyX, pyY, pyZ);
            pyX *= invl; pyY *= invl; pyZ *= invl; pyW = scaledW(pyW, invl);
        }
        if (zeroBasedDepth) {
            nzX = mData[2]; nzY = mData[6]; nzZ = mData[10]; nzW = mData[14];
        } else {
            nzX = mData[3] + mData[2]; nzY = mData[7] + mData[6]; nzZ = mData[11] + mData[10]; nzW = mData[15] + mData[14];
        }
        if (allowTestSpheres) {
            invl = invLength_mulAdd(nzX, nzY, nzZ);
            nzX *= invl; nzY *= invl; nzZ *= invl; nzW = scaledW(nzW, invl);
        }
        pzX = mData[3] - mData[2];
        pzY = mData[7] - mData[6];
        pzZ = mData[11] - mData[10];
        pzW = mData[15] - mData[14];
        if (allowTestSpheres) {
            invl = invLength_mulAdd(pzX, pzY, pzZ);
            pzX *= invl; pzY *= invl; pzZ *= invl; pzW = scaledW(pzW, invl);
        }
        nxAX = java.lang.Math.abs(nxX);
        nxAY = java.lang.Math.abs(nxY);
        nxAZ = java.lang.Math.abs(nxZ);
        pxAX = java.lang.Math.abs(pxX);
        pxAY = java.lang.Math.abs(pxY);
        pxAZ = java.lang.Math.abs(pxZ);
        nyAX = java.lang.Math.abs(nyX);
        nyAY = java.lang.Math.abs(nyY);
        nyAZ = java.lang.Math.abs(nyZ);
        pyAX = java.lang.Math.abs(pyX);
        pyAY = java.lang.Math.abs(pyY);
        pyAZ = java.lang.Math.abs(pyZ);
        nzAX = java.lang.Math.abs(nzX);
        nzAY = java.lang.Math.abs(nzY);
        nzAZ = java.lang.Math.abs(nzZ);
        pzAX = java.lang.Math.abs(pzX);
        pzAY = java.lang.Math.abs(pzY);
        pzAZ = java.lang.Math.abs(pzZ);
        return this;
    }

    /**
     * 1 / |(x, y, z)|, and 0 for the zero vector: an infinite far (or near) plane
     * extracts as (0, 0, 0, w), holds every point and has no direction to normalize
     * (see {@link #scaledW}). The squared length leaves float's range for |(x, y, z)|
     * beyond ~1.8e19 or below ~1e-19 (an ortho projection a few 1e-20 wide or 1e20
     * across); only then the vector is first scaled by its largest component.
     */
    private static float invLength_fma(float x, float y, float z) {
        float lenSq = java.lang.Math.fma(x, x, java.lang.Math.fma(y, y, z * z));
        if (lenSq >= Float.MIN_NORMAL && lenSq < Float.POSITIVE_INFINITY)
            return 1.0f / (float) java.lang.Math.sqrt(lenSq);
        float s = Math.max(java.lang.Math.abs(x), Math.max(java.lang.Math.abs(y), java.lang.Math.abs(z)));
        if (s == 0.0f) return 0.0f;
        x /= s; y /= s; z /= s;
        return 1.0f / (s * (float) java.lang.Math.sqrt(java.lang.Math.fma(x, x, java.lang.Math.fma(y, y, z * z))));
    }

    /**
     * 1 / |(x, y, z)|, and 0 for the zero vector: an infinite far (or near) plane
     * extracts as (0, 0, 0, w), holds every point and has no direction to normalize
     * (see {@link #scaledW}). The squared length leaves float's range for |(x, y, z)|
     * beyond ~1.8e19 or below ~1e-19 (an ortho projection a few 1e-20 wide or 1e20
     * across); only then the vector is first scaled by its largest component.
     */
    private static float invLength_mulAdd(float x, float y, float z) {
        float lenSq = ((x) * (x) + (((y) * (y) + (z * z))));
        if (lenSq >= Float.MIN_NORMAL && lenSq < Float.POSITIVE_INFINITY)
            return 1.0f / (float) java.lang.Math.sqrt(lenSq);
        float s = Math.max(java.lang.Math.abs(x), Math.max(java.lang.Math.abs(y), java.lang.Math.abs(z)));
        if (s == 0.0f) return 0.0f;
        x /= s; y /= s; z /= s;
        return 1.0f / (s * (float) java.lang.Math.sqrt(((x) * (x) + (((y) * (y) + (z * z))))));
    }

    /**
     * The normalized w of a plane whose normal {@link #invLength} scaled by {@code invl}: a
     * plane at infinity (invl 0, a zero normal) gets w = +infinity, the distance of every
     * finite point from it, so the sphere tests and {@code distanceToPlane} see every point
     * infinitely far inside it rather than a constant 2 * near (or near) away.
     */
    private static float scaledW(float w, float invl) {
        return invl == 0.0f ? w * Float.POSITIVE_INFINITY : w * invl;
    }

    /**
     * The x coefficient (plane normal component) of the left (-X) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float nxX() { return nxX; }
    /**
     * The y coefficient (plane normal component) of the left (-X) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float nxY() { return nxY; }
    /**
     * The z coefficient (plane normal component) of the left (-X) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float nxZ() { return nxZ; }
    /**
     * The w coefficient (the signed distance from the origin when the planes are normalized, {@code allowTestSpheres}) of the left (-X) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float nxW() { return nxW; }

    /**
     * The x coefficient (plane normal component) of the right (+X) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float pxX() { return pxX; }
    /**
     * The y coefficient (plane normal component) of the right (+X) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float pxY() { return pxY; }
    /**
     * The z coefficient (plane normal component) of the right (+X) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float pxZ() { return pxZ; }
    /**
     * The w coefficient (the signed distance from the origin when the planes are normalized, {@code allowTestSpheres}) of the right (+X) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float pxW() { return pxW; }

    /**
     * The x coefficient (plane normal component) of the bottom (-Y) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float nyX() { return nyX; }
    /**
     * The y coefficient (plane normal component) of the bottom (-Y) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float nyY() { return nyY; }
    /**
     * The z coefficient (plane normal component) of the bottom (-Y) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float nyZ() { return nyZ; }
    /**
     * The w coefficient (the signed distance from the origin when the planes are normalized, {@code allowTestSpheres}) of the bottom (-Y) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float nyW() { return nyW; }

    /**
     * The x coefficient (plane normal component) of the top (+Y) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float pyX() { return pyX; }
    /**
     * The y coefficient (plane normal component) of the top (+Y) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float pyY() { return pyY; }
    /**
     * The z coefficient (plane normal component) of the top (+Y) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float pyZ() { return pyZ; }
    /**
     * The w coefficient (the signed distance from the origin when the planes are normalized, {@code allowTestSpheres}) of the top (+Y) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float pyW() { return pyW; }

    /**
     * The x coefficient (plane normal component) of the near (-Z) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float nzX() { return nzX; }
    /**
     * The y coefficient (plane normal component) of the near (-Z) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float nzY() { return nzY; }
    /**
     * The z coefficient (plane normal component) of the near (-Z) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float nzZ() { return nzZ; }
    /**
     * The w coefficient (the signed distance from the origin when the planes are normalized, {@code allowTestSpheres}) of the near (-Z) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float nzW() { return nzW; }

    /**
     * The x coefficient (plane normal component) of the far (+Z) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float pzX() { return pzX; }
    /**
     * The y coefficient (plane normal component) of the far (+Z) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float pzY() { return pzY; }
    /**
     * The z coefficient (plane normal component) of the far (+Z) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float pzZ() { return pzZ; }
    /**
     * The w coefficient (the signed distance from the origin when the planes are normalized, {@code allowTestSpheres}) of the far (+Z) plane.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public float pzW() { return pzW; }

    /**
     * Test whether {@code point} is inside the frustum.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param point the point to test
     * @return <code>true</code> iff the point is inside the frustum
     */
    public boolean testPoint(Float3R point) {
        float[] pointData = ((Float3Impl) point).data;
        return testPoint(pointData[0], pointData[1], pointData[2]);
    }

    /**
     * Test whether the point <code>(x, y, z)</code> is inside the frustum.
     * <p>
     * Exact, unlike the volume tests: a point lying exactly on a plane counts as
     * inside.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @param z the z coordinate of the point
     * @return <code>true</code> iff the point is inside the frustum
     */
    public boolean testPoint(float x, float y, float z) {
        if (!org.joml2.internal.FmaSwitch.ON) return testPoint_mulAdd(x, y, z);
        return java.lang.Math.fma(nxX, x, java.lang.Math.fma(nxY, y, java.lang.Math.fma(nxZ, z, nxW))) >= 0 &&
               java.lang.Math.fma(pxX, x, java.lang.Math.fma(pxY, y, java.lang.Math.fma(pxZ, z, pxW))) >= 0 &&
               java.lang.Math.fma(nyX, x, java.lang.Math.fma(nyY, y, java.lang.Math.fma(nyZ, z, nyW))) >= 0 &&
               java.lang.Math.fma(pyX, x, java.lang.Math.fma(pyY, y, java.lang.Math.fma(pyZ, z, pyW))) >= 0 &&
               java.lang.Math.fma(nzX, x, java.lang.Math.fma(nzY, y, java.lang.Math.fma(nzZ, z, nzW))) >= 0 &&
               java.lang.Math.fma(pzX, x, java.lang.Math.fma(pzY, y, java.lang.Math.fma(pzZ, z, pzW))) >= 0;
    }

    /** {@code testPoint} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean testPoint_mulAdd(float x, float y, float z) {
        return ((nxX) * (x) + (((nxY) * (y) + (((nxZ) * (z) + (nxW)))))) >= 0 &&
               ((pxX) * (x) + (((pxY) * (y) + (((pxZ) * (z) + (pxW)))))) >= 0 &&
               ((nyX) * (x) + (((nyY) * (y) + (((nyZ) * (z) + (nyW)))))) >= 0 &&
               ((pyX) * (x) + (((pyY) * (y) + (((pyZ) * (z) + (pyW)))))) >= 0 &&
               ((nzX) * (x) + (((nzY) * (y) + (((nzZ) * (z) + (nzW)))))) >= 0 &&
               ((pzX) * (x) + (((pzY) * (y) + (((pzZ) * (z) + (pzW)))))) >= 0;
    }

    /**
     * Test whether the sphere around {@code center} with radius {@code radius} is
     * partly or completely inside the frustum.
     * <p>
     * Valid input: {@code radius} must not be negative.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @return <code>false</code> if the sphere lies completely outside one of the frustum planes, <code>true</code> otherwise (a conservative test)
     */
    public boolean testSphere(Float3R center, float radius) {
        float[] centerData = ((Float3Impl) center).data;
        return testSphere(centerData[0], centerData[1], centerData[2], radius);
    }

    /**
     * Test whether the sphere around <code>(x, y, z)</code> with radius {@code r} is
     * partly or completely inside the frustum.
     * <p>
     * The frustum must have been built with <code>allowTestSpheres = true</code>:
     * un-normalized planes do not measure distances in world units, so the radius
     * cannot be compared against them. The test is conservative - a sphere in a
     * corner region outside the frustum can be reported as intersecting - but never
     * reports an intersecting sphere as outside.
     * <p>
     * Valid input: {@code r} must not be negative.
     *
     * @param x the x coordinate of the sphere center
     * @param y the y coordinate of the sphere center
     * @param z the z coordinate of the sphere center
     * @param r the radius of the sphere
     * @return <code>false</code> if the sphere lies completely outside one of the frustum planes, <code>true</code> otherwise (a conservative test)
     */
    public boolean testSphere(float x, float y, float z, float r) {
        if (!org.joml2.internal.FmaSwitch.ON) return testSphere_mulAdd(x, y, z, r);
        return java.lang.Math.fma(nxX, x, java.lang.Math.fma(nxY, y, java.lang.Math.fma(nxZ, z, nxW))) >= -r &&
               java.lang.Math.fma(pxX, x, java.lang.Math.fma(pxY, y, java.lang.Math.fma(pxZ, z, pxW))) >= -r &&
               java.lang.Math.fma(nyX, x, java.lang.Math.fma(nyY, y, java.lang.Math.fma(nyZ, z, nyW))) >= -r &&
               java.lang.Math.fma(pyX, x, java.lang.Math.fma(pyY, y, java.lang.Math.fma(pyZ, z, pyW))) >= -r &&
               java.lang.Math.fma(nzX, x, java.lang.Math.fma(nzY, y, java.lang.Math.fma(nzZ, z, nzW))) >= -r &&
               java.lang.Math.fma(pzX, x, java.lang.Math.fma(pzY, y, java.lang.Math.fma(pzZ, z, pzW))) >= -r;
    }

    /** {@code testSphere} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean testSphere_mulAdd(float x, float y, float z, float r) {
        return ((nxX) * (x) + (((nxY) * (y) + (((nxZ) * (z) + (nxW)))))) >= -r &&
               ((pxX) * (x) + (((pxY) * (y) + (((pxZ) * (z) + (pxW)))))) >= -r &&
               ((nyX) * (x) + (((nyY) * (y) + (((nyZ) * (z) + (nyW)))))) >= -r &&
               ((pyX) * (x) + (((pyY) * (y) + (((pyZ) * (z) + (pyW)))))) >= -r &&
               ((nzX) * (x) + (((nzY) * (y) + (((nzZ) * (z) + (nzW)))))) >= -r &&
               ((pzX) * (x) + (((pzY) * (y) + (((pzZ) * (z) + (pzW)))))) >= -r;
    }

    /**
     * Test whether {@code sphere} is partly or completely inside the frustum.
     * <p>
     * Valid input: the radius of {@code sphere} must not be negative.
     *
     * @param sphere the sphere to test
     * @return <code>false</code> if the sphere lies completely outside one of the frustum planes, <code>true</code> otherwise (a conservative test)
     */
    public boolean testSphere(FloatSphereR sphere) {
        float[] sphereData = ((FloatSphereImpl) sphere).data;
        return testSphere(sphereData[0], sphereData[1], sphereData[2], sphereData[3]);
    }

    /**
     * Determine how the sphere around {@code center} with radius {@code radius}
     * relates to the frustum.
     * <p>
     * Valid input: {@code radius} must not be negative.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @return how the sphere relates to the frustum
     */
    public Intersection intersectSphere(Float3R center, float radius) {
        float[] centerData = ((Float3Impl) center).data;
        return intersectSphere(centerData[0], centerData[1], centerData[2], radius);
    }

    /**
     * Determine how the sphere around <code>(x, y, z)</code> with radius {@code r}
     * relates to the frustum.
     * <p>
     * Like {@link #testSphere(float, float, float, float)} this needs a frustum built
     * with <code>allowTestSpheres = true</code>, and is conservative: a sphere in a
     * corner region outside the frustum is reported as {@link Intersection#INTERSECT}
     * rather than {@link Intersection#OUTSIDE}. {@link Intersection#INSIDE} and
     * {@link Intersection#OUTSIDE} are never reported wrongly in the other direction.
     * <p>
     * Valid input: {@code r} must not be negative.
     *
     * @param x the x coordinate of the sphere center
     * @param y the y coordinate of the sphere center
     * @param z the z coordinate of the sphere center
     * @param r the radius of the sphere
     * @return how the sphere relates to the frustum
     */
    public Intersection intersectSphere(float x, float y, float z, float r) {
        if (!org.joml2.internal.FmaSwitch.ON) return intersectSphere_mulAdd(x, y, z, r);
        float dist = java.lang.Math.fma(nxX, x, java.lang.Math.fma(nxY, y, java.lang.Math.fma(nxZ, z, nxW)));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        boolean inside = dist >= r;
        dist = java.lang.Math.fma(pxX, x, java.lang.Math.fma(pxY, y, java.lang.Math.fma(pxZ, z, pxW)));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        inside &= dist >= r;
        dist = java.lang.Math.fma(nyX, x, java.lang.Math.fma(nyY, y, java.lang.Math.fma(nyZ, z, nyW)));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        inside &= dist >= r;
        return intersectSphereYZ_fma(x, y, z, r, inside);
    }

    /** {@code intersectSphere} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Intersection intersectSphere_mulAdd(float x, float y, float z, float r) {
        float dist = ((nxX) * (x) + (((nxY) * (y) + (((nxZ) * (z) + (nxW))))));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        boolean inside = dist >= r;
        dist = ((pxX) * (x) + (((pxY) * (y) + (((pxZ) * (z) + (pxW))))));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        inside &= dist >= r;
        dist = ((nyX) * (x) + (((nyY) * (y) + (((nyZ) * (z) + (nyW))))));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        inside &= dist >= r;
        return intersectSphereYZ_mulAdd(x, y, z, r, inside);
    }

    /** Second half of {@link #intersectSphere(float, float, float, float)}: the py, nz and pz planes. */
    private Intersection intersectSphereYZ_fma(float x, float y, float z, float r, boolean inside) {
        float dist = java.lang.Math.fma(pyX, x, java.lang.Math.fma(pyY, y, java.lang.Math.fma(pyZ, z, pyW)));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        inside &= dist >= r;
        dist = java.lang.Math.fma(nzX, x, java.lang.Math.fma(nzY, y, java.lang.Math.fma(nzZ, z, nzW)));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        inside &= dist >= r;
        dist = java.lang.Math.fma(pzX, x, java.lang.Math.fma(pzY, y, java.lang.Math.fma(pzZ, z, pzW)));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        inside &= dist >= r;
        return inside ? Intersection.INSIDE : Intersection.INTERSECT;
    }

    /** Second half of {@link #intersectSphere(float, float, float, float)}: the py, nz and pz planes. */
    private Intersection intersectSphereYZ_mulAdd(float x, float y, float z, float r, boolean inside) {
        float dist = ((pyX) * (x) + (((pyY) * (y) + (((pyZ) * (z) + (pyW))))));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        inside &= dist >= r;
        dist = ((nzX) * (x) + (((nzY) * (y) + (((nzZ) * (z) + (nzW))))));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        inside &= dist >= r;
        dist = ((pzX) * (x) + (((pzY) * (y) + (((pzZ) * (z) + (pzW))))));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        inside &= dist >= r;
        return inside ? Intersection.INSIDE : Intersection.INTERSECT;
    }

    /**
     * Determine how {@code sphere} relates to the frustum.
     * <p>
     * Valid input: the radius of {@code sphere} must not be negative.
     *
     * @param sphere the sphere to test
     * @return how the sphere relates to the frustum
     */
    public Intersection intersectSphere(FloatSphereR sphere) {
        float[] sphereData = ((FloatSphereImpl) sphere).data;
        return intersectSphere(sphereData[0], sphereData[1], sphereData[2], sphereData[3]);
    }

    /**
     * Test whether the axis-aligned box spanned by {@code min} and {@code max} is
     * partly or completely inside the frustum.
     * <p>
     * An empty box - a minimum above its maximum in some component - is reported as outside.
     * <p>
     * Valid input: {@code min} may be infinite; {@code max} may be infinite.
     *
     * @param min the corner of the box with the smaller coordinates
     * @param max the corner of the box with the larger coordinates
     * @return <code>false</code> if the box lies completely outside one of the frustum planes, <code>true</code> otherwise (a conservative test)
     */
    public boolean testAabb(Float3R min, Float3R max) {
        float[] minData = ((Float3Impl) min).data;
        float[] maxData = ((Float3Impl) max).data;
        return testAabb(minData[0], minData[1], minData[2], maxData[0], maxData[1], maxData[2]);
    }

    /**
     * Test whether the axis-aligned box spanned by <code>(minX, minY, minZ)</code>
     * and <code>(maxX, maxY, maxZ)</code> is partly or completely inside the frustum.
     * <p>
     * The test is conservative: it only asks whether the box lies completely outside
     * one single plane, so a box in a corner region outside the frustum can be
     * reported as intersecting. A box that really does intersect is never reported as
     * outside, which is what culling needs.
     * <p>
     * An empty box - a minimum above its maximum in some component - is reported as outside.
     * <p>
     * Valid input: {@code (minX, minY, minZ)} may be infinite; {@code (maxX, maxY, maxZ)} may be infinite.
     *
     * @param minX the x coordinate of the corner with the smaller coordinates
     * @param minY the y coordinate of the corner with the smaller coordinates
     * @param minZ the z coordinate of the corner with the smaller coordinates
     * @param maxX the x coordinate of the corner with the larger coordinates
     * @param maxY the y coordinate of the corner with the larger coordinates
     * @param maxZ the z coordinate of the corner with the larger coordinates
     * @return <code>false</code> if the box lies completely outside one of the frustum planes, <code>true</code> otherwise (a conservative test)
     */
    public boolean testAabb(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return testAabb_mulAdd(minX, minY, minZ, maxX, maxY, maxZ);
        return pv_fma(nxX, nxY, nxZ, nxW, minX, minY, minZ, maxX, maxY, maxZ) && pv_fma(pxX, pxY, pxZ, pxW, minX, minY, minZ, maxX, maxY, maxZ)
            && pv_fma(nyX, nyY, nyZ, nyW, minX, minY, minZ, maxX, maxY, maxZ) && pv_fma(pyX, pyY, pyZ, pyW, minX, minY, minZ, maxX, maxY, maxZ)
            && pv_fma(nzX, nzY, nzZ, nzW, minX, minY, minZ, maxX, maxY, maxZ) && pv_fma(pzX, pzY, pzZ, pzW, minX, minY, minZ, maxX, maxY, maxZ)
            && minX <= maxX && minY <= maxY && minZ <= maxZ;
    }

    /** {@code testAabb} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean testAabb_mulAdd(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return pv_mulAdd(nxX, nxY, nxZ, nxW, minX, minY, minZ, maxX, maxY, maxZ) && pv_mulAdd(pxX, pxY, pxZ, pxW, minX, minY, minZ, maxX, maxY, maxZ)
            && pv_mulAdd(nyX, nyY, nyZ, nyW, minX, minY, minZ, maxX, maxY, maxZ) && pv_mulAdd(pyX, pyY, pyZ, pyW, minX, minY, minZ, maxX, maxY, maxZ)
            && pv_mulAdd(nzX, nzY, nzZ, nzW, minX, minY, minZ, maxX, maxY, maxZ) && pv_mulAdd(pzX, pzY, pzZ, pzW, minX, minY, minZ, maxX, maxY, maxZ)
            && minX <= maxX && minY <= maxY && minZ <= maxZ;
    }

    /**
     * The box vertex farthest along the plane normal is on the inner side: the box is not wholly
     * outside this plane. A NaN distance - 0 * infinity, a zero plane coefficient times an
     * infinite bound - counts as inside, so an unbounded box is never culled wrongly.
     */
    private static boolean pv_fma(float a, float b, float c, float w, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return !(java.lang.Math.fma(a, a < 0 ? minX : maxX, java.lang.Math.fma(b, b < 0 ? minY : maxY, c * (c < 0 ? minZ : maxZ))) < -w);
    }

    /**
     * The box vertex farthest along the plane normal is on the inner side: the box is not wholly
     * outside this plane. A NaN distance - 0 * infinity, a zero plane coefficient times an
     * infinite bound - counts as inside, so an unbounded box is never culled wrongly.
     */
    private static boolean pv_mulAdd(float a, float b, float c, float w, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return !(((a) * (a < 0 ? minX : maxX) + (((b) * (b < 0 ? minY : maxY) + (c * (c < 0 ? minZ : maxZ))))) < -w);
    }

    /** The box vertex farthest along the plane normal is strictly outside this plane: exactly the negation of {@link #pv}. */
    private static boolean outsidePv_fma(float a, float b, float c, float w, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return java.lang.Math.fma(a, a < 0 ? minX : maxX, java.lang.Math.fma(b, b < 0 ? minY : maxY, c * (c < 0 ? minZ : maxZ))) < -w;
    }

    /** The box vertex farthest along the plane normal is strictly outside this plane: exactly the negation of {@link #pv}. */
    private static boolean outsidePv_mulAdd(float a, float b, float c, float w, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return ((a) * (a < 0 ? minX : maxX) + (((b) * (b < 0 ? minY : maxY) + (c * (c < 0 ? minZ : maxZ))))) < -w;
    }

    private float farNx_fma(float cx, float cy, float cz, float ex, float ey, float ez) { return java.lang.Math.fma(nxX, cx, java.lang.Math.fma(nxY, cy, java.lang.Math.fma(nxZ, cz, java.lang.Math.fma(nxAX, ex, java.lang.Math.fma(nxAY, ey, nxAZ * ez))))); }

    private float farNx_mulAdd(float cx, float cy, float cz, float ex, float ey, float ez) { return ((nxX) * (cx) + (((nxY) * (cy) + (((nxZ) * (cz) + (((nxAX) * (ex) + (((nxAY) * (ey) + (nxAZ * ez)))))))))); }

    private float nearNx_fma(float cx, float cy, float cz, float ex, float ey, float ez) { return java.lang.Math.fma(nxX, cx, java.lang.Math.fma(nxY, cy, java.lang.Math.fma(nxZ, cz, -java.lang.Math.fma(nxAX, ex, java.lang.Math.fma(nxAY, ey, nxAZ * ez))))); }

    private float nearNx_mulAdd(float cx, float cy, float cz, float ex, float ey, float ez) { return ((nxX) * (cx) + (((nxY) * (cy) + (((nxZ) * (cz) - ((nxAX) * (ex) + (((nxAY) * (ey) + (nxAZ * ez))))))))); }

    private float farPx_fma(float cx, float cy, float cz, float ex, float ey, float ez) { return java.lang.Math.fma(pxX, cx, java.lang.Math.fma(pxY, cy, java.lang.Math.fma(pxZ, cz, java.lang.Math.fma(pxAX, ex, java.lang.Math.fma(pxAY, ey, pxAZ * ez))))); }

    private float farPx_mulAdd(float cx, float cy, float cz, float ex, float ey, float ez) { return ((pxX) * (cx) + (((pxY) * (cy) + (((pxZ) * (cz) + (((pxAX) * (ex) + (((pxAY) * (ey) + (pxAZ * ez)))))))))); }

    private float nearPx_fma(float cx, float cy, float cz, float ex, float ey, float ez) { return java.lang.Math.fma(pxX, cx, java.lang.Math.fma(pxY, cy, java.lang.Math.fma(pxZ, cz, -java.lang.Math.fma(pxAX, ex, java.lang.Math.fma(pxAY, ey, pxAZ * ez))))); }

    private float nearPx_mulAdd(float cx, float cy, float cz, float ex, float ey, float ez) { return ((pxX) * (cx) + (((pxY) * (cy) + (((pxZ) * (cz) - ((pxAX) * (ex) + (((pxAY) * (ey) + (pxAZ * ez))))))))); }

    private float farNy_fma(float cx, float cy, float cz, float ex, float ey, float ez) { return java.lang.Math.fma(nyX, cx, java.lang.Math.fma(nyY, cy, java.lang.Math.fma(nyZ, cz, java.lang.Math.fma(nyAX, ex, java.lang.Math.fma(nyAY, ey, nyAZ * ez))))); }

    private float farNy_mulAdd(float cx, float cy, float cz, float ex, float ey, float ez) { return ((nyX) * (cx) + (((nyY) * (cy) + (((nyZ) * (cz) + (((nyAX) * (ex) + (((nyAY) * (ey) + (nyAZ * ez)))))))))); }

    private float nearNy_fma(float cx, float cy, float cz, float ex, float ey, float ez) { return java.lang.Math.fma(nyX, cx, java.lang.Math.fma(nyY, cy, java.lang.Math.fma(nyZ, cz, -java.lang.Math.fma(nyAX, ex, java.lang.Math.fma(nyAY, ey, nyAZ * ez))))); }

    private float nearNy_mulAdd(float cx, float cy, float cz, float ex, float ey, float ez) { return ((nyX) * (cx) + (((nyY) * (cy) + (((nyZ) * (cz) - ((nyAX) * (ex) + (((nyAY) * (ey) + (nyAZ * ez))))))))); }

    private float farPy_fma(float cx, float cy, float cz, float ex, float ey, float ez) { return java.lang.Math.fma(pyX, cx, java.lang.Math.fma(pyY, cy, java.lang.Math.fma(pyZ, cz, java.lang.Math.fma(pyAX, ex, java.lang.Math.fma(pyAY, ey, pyAZ * ez))))); }

    private float farPy_mulAdd(float cx, float cy, float cz, float ex, float ey, float ez) { return ((pyX) * (cx) + (((pyY) * (cy) + (((pyZ) * (cz) + (((pyAX) * (ex) + (((pyAY) * (ey) + (pyAZ * ez)))))))))); }

    private float nearPy_fma(float cx, float cy, float cz, float ex, float ey, float ez) { return java.lang.Math.fma(pyX, cx, java.lang.Math.fma(pyY, cy, java.lang.Math.fma(pyZ, cz, -java.lang.Math.fma(pyAX, ex, java.lang.Math.fma(pyAY, ey, pyAZ * ez))))); }

    private float nearPy_mulAdd(float cx, float cy, float cz, float ex, float ey, float ez) { return ((pyX) * (cx) + (((pyY) * (cy) + (((pyZ) * (cz) - ((pyAX) * (ex) + (((pyAY) * (ey) + (pyAZ * ez))))))))); }

    private float farNz_fma(float cx, float cy, float cz, float ex, float ey, float ez) { return java.lang.Math.fma(nzX, cx, java.lang.Math.fma(nzY, cy, java.lang.Math.fma(nzZ, cz, java.lang.Math.fma(nzAX, ex, java.lang.Math.fma(nzAY, ey, nzAZ * ez))))); }

    private float farNz_mulAdd(float cx, float cy, float cz, float ex, float ey, float ez) { return ((nzX) * (cx) + (((nzY) * (cy) + (((nzZ) * (cz) + (((nzAX) * (ex) + (((nzAY) * (ey) + (nzAZ * ez)))))))))); }

    private float nearNz_fma(float cx, float cy, float cz, float ex, float ey, float ez) { return java.lang.Math.fma(nzX, cx, java.lang.Math.fma(nzY, cy, java.lang.Math.fma(nzZ, cz, -java.lang.Math.fma(nzAX, ex, java.lang.Math.fma(nzAY, ey, nzAZ * ez))))); }

    private float nearNz_mulAdd(float cx, float cy, float cz, float ex, float ey, float ez) { return ((nzX) * (cx) + (((nzY) * (cy) + (((nzZ) * (cz) - ((nzAX) * (ex) + (((nzAY) * (ey) + (nzAZ * ez))))))))); }

    private float farPz_fma(float cx, float cy, float cz, float ex, float ey, float ez) { return java.lang.Math.fma(pzX, cx, java.lang.Math.fma(pzY, cy, java.lang.Math.fma(pzZ, cz, java.lang.Math.fma(pzAX, ex, java.lang.Math.fma(pzAY, ey, pzAZ * ez))))); }

    private float farPz_mulAdd(float cx, float cy, float cz, float ex, float ey, float ez) { return ((pzX) * (cx) + (((pzY) * (cy) + (((pzZ) * (cz) + (((pzAX) * (ex) + (((pzAY) * (ey) + (pzAZ * ez)))))))))); }

    private float nearPz_fma(float cx, float cy, float cz, float ex, float ey, float ez) { return java.lang.Math.fma(pzX, cx, java.lang.Math.fma(pzY, cy, java.lang.Math.fma(pzZ, cz, -java.lang.Math.fma(pzAX, ex, java.lang.Math.fma(pzAY, ey, pzAZ * ez))))); }

    private float nearPz_mulAdd(float cx, float cy, float cz, float ex, float ey, float ez) { return ((pzX) * (cx) + (((pzY) * (cy) + (((pzZ) * (cz) - ((pzAX) * (ex) + (((pzAY) * (ey) + (pzAZ * ez))))))))); }

    /**
     * Test whether {@code aabb} is partly or completely inside the frustum.
     * <p>
     * An empty box - a minimum above its maximum in some component - is reported as outside.
     * <p>
     * Valid input: the minimum corner of {@code aabb} may be infinite; the maximum corner of {@code aabb} may be infinite.
     *
     * @param aabb the axis-aligned box to test
     * @return <code>false</code> if the box lies completely outside one of the frustum planes, <code>true</code> otherwise (a conservative test)
     */
    public boolean testAabb(FloatAABBR aabb) {
        float[] aabbData = ((FloatAABBImpl) aabb).data;
        return testAabb(aabbData[0], aabbData[1], aabbData[2],
                       aabbData[3], aabbData[4], aabbData[5]);
    }

    /**
     * Test whether the rectangle spanned by {@code min} and {@code max} in the XY
     * plane at <code>z = 0</code> is partly or completely within the frustum.
     * <p>
     * An empty rectangle - a minimum above its maximum in some component - is reported as outside.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param min the corner of the rectangle with the smaller coordinates
     * @param max the corner of the rectangle with the larger coordinates
     * @return <code>false</code> if the rectangle lies completely outside one of the frustum planes, <code>true</code> otherwise (a conservative test)
     */
    public boolean testPlaneXY(Float2R min, Float2R max) {
        float[] minData = ((Float2Impl) min).data;
        float[] maxData = ((Float2Impl) max).data;
        return testPlaneXY(minData[0], minData[1], maxData[0], maxData[1]);
    }

    /**
     * Test whether the rectangle spanned by <code>(minX, minY)</code> and
     * <code>(maxX, maxY)</code> in the XY plane at <code>z = 0</code> is partly or
     * completely within the frustum.
     * <p>
     * The z term of each plane drops out because the rectangle's <code>z</code> is
     * zero - this is <em>not</em> a test of the rectangle extended infinitely along
     * Z. Conservative in the same way {@link #testAabb(float, float, float, float, float, float)} is.
     * <p>
     * An empty rectangle - a minimum above its maximum in some component - is reported as outside.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the x coordinate of the corner with the smaller coordinates
     * @param minY the y coordinate of the corner with the smaller coordinates
     * @param maxX the x coordinate of the corner with the larger coordinates
     * @param maxY the y coordinate of the corner with the larger coordinates
     * @return <code>false</code> if the rectangle lies completely outside one of the frustum planes, <code>true</code> otherwise (a conservative test)
     */
    public boolean testPlaneXY(float minX, float minY, float maxX, float maxY) {
        if (org.joml2.internal.FmaSwitch.ON) return testPlaneXY_fma(minX, minY, maxX, maxY);
        return testPlaneXY_mulAdd(minX, minY, maxX, maxY);
    }

    /** {@code testPlaneXY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean testPlaneXY_fma(float minX, float minY, float maxX, float maxY) {
        return !(java.lang.Math.fma(nxX, nxX < 0 ? minX : maxX, nxY * (nxY < 0 ? minY : maxY)) < -nxW) &&
               !(java.lang.Math.fma(pxX, pxX < 0 ? minX : maxX, pxY * (pxY < 0 ? minY : maxY)) < -pxW) &&
               !(java.lang.Math.fma(nyX, nyX < 0 ? minX : maxX, nyY * (nyY < 0 ? minY : maxY)) < -nyW) &&
               !(java.lang.Math.fma(pyX, pyX < 0 ? minX : maxX, pyY * (pyY < 0 ? minY : maxY)) < -pyW) &&
               !(java.lang.Math.fma(nzX, nzX < 0 ? minX : maxX, nzY * (nzY < 0 ? minY : maxY)) < -nzW) &&
               !(java.lang.Math.fma(pzX, pzX < 0 ? minX : maxX, pzY * (pzY < 0 ? minY : maxY)) < -pzW) &&
               minX <= maxX && minY <= maxY;
    }

    /** {@code testPlaneXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean testPlaneXY_mulAdd(float minX, float minY, float maxX, float maxY) {
        return !(((nxX) * (nxX < 0 ? minX : maxX) + (nxY * (nxY < 0 ? minY : maxY))) < -nxW) &&
               !(((pxX) * (pxX < 0 ? minX : maxX) + (pxY * (pxY < 0 ? minY : maxY))) < -pxW) &&
               !(((nyX) * (nyX < 0 ? minX : maxX) + (nyY * (nyY < 0 ? minY : maxY))) < -nyW) &&
               !(((pyX) * (pyX < 0 ? minX : maxX) + (pyY * (pyY < 0 ? minY : maxY))) < -pyW) &&
               !(((nzX) * (nzX < 0 ? minX : maxX) + (nzY * (nzY < 0 ? minY : maxY))) < -nzW) &&
               !(((pzX) * (pzX < 0 ? minX : maxX) + (pzY * (pzY < 0 ? minY : maxY))) < -pzW) &&
               minX <= maxX && minY <= maxY;
    }

    /**
     * Test whether the given rectangle, lying in the XY plane at <code>z = 0</code>,
     * is partly or completely within the frustum. The rect's x/y axes map to
     * world X/Y. The z term of each plane drops out because the rectangle's
     * <code>z</code> is zero - this is <em>not</em> a test of the rectangle
     * extended infinitely along Z.
     * <p>
     * An empty rectangle - a minimum above its maximum in some component - is reported as outside.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param rect the rectangle to test
     * @return <code>false</code> if the rectangle lies completely outside one of the frustum planes, <code>true</code> otherwise (a conservative test)
     */
    public boolean testPlaneXY(FloatRectR rect) {
        float[] rectData = ((FloatRectImpl) rect).data;
        return testPlaneXY(rectData[0], rectData[1], rectData[2], rectData[3]);
    }

    /**
     * Test whether the rectangle spanned by <code>(minX, minZ)</code> and
     * <code>(maxX, maxZ)</code> in the XZ plane at <code>y = 0</code> is partly or
     * completely within the frustum.
     * <p>
     * The y term of each plane drops out because the rectangle's <code>y</code> is
     * zero - this is <em>not</em> a test of the rectangle extended infinitely along
     * Y. Conservative in the same way {@link #testAabb(float, float, float, float, float, float)} is.
     * <p>
     * An empty rectangle - a minimum above its maximum in some component - is reported as outside.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the x coordinate of the corner with the smaller coordinates
     * @param minZ the z coordinate of the corner with the smaller coordinates
     * @param maxX the x coordinate of the corner with the larger coordinates
     * @param maxZ the z coordinate of the corner with the larger coordinates
     * @return <code>false</code> if the rectangle lies completely outside one of the frustum planes, <code>true</code> otherwise (a conservative test)
     */
    public boolean testPlaneXZ(float minX, float minZ, float maxX, float maxZ) {
        if (org.joml2.internal.FmaSwitch.ON) return testPlaneXZ_fma(minX, minZ, maxX, maxZ);
        return testPlaneXZ_mulAdd(minX, minZ, maxX, maxZ);
    }

    /** {@code testPlaneXZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean testPlaneXZ_fma(float minX, float minZ, float maxX, float maxZ) {
        return !(java.lang.Math.fma(nxX, nxX < 0 ? minX : maxX, nxZ * (nxZ < 0 ? minZ : maxZ)) < -nxW) &&
               !(java.lang.Math.fma(pxX, pxX < 0 ? minX : maxX, pxZ * (pxZ < 0 ? minZ : maxZ)) < -pxW) &&
               !(java.lang.Math.fma(nyX, nyX < 0 ? minX : maxX, nyZ * (nyZ < 0 ? minZ : maxZ)) < -nyW) &&
               !(java.lang.Math.fma(pyX, pyX < 0 ? minX : maxX, pyZ * (pyZ < 0 ? minZ : maxZ)) < -pyW) &&
               !(java.lang.Math.fma(nzX, nzX < 0 ? minX : maxX, nzZ * (nzZ < 0 ? minZ : maxZ)) < -nzW) &&
               !(java.lang.Math.fma(pzX, pzX < 0 ? minX : maxX, pzZ * (pzZ < 0 ? minZ : maxZ)) < -pzW) &&
               minX <= maxX && minZ <= maxZ;
    }

    /** {@code testPlaneXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean testPlaneXZ_mulAdd(float minX, float minZ, float maxX, float maxZ) {
        return !(((nxX) * (nxX < 0 ? minX : maxX) + (nxZ * (nxZ < 0 ? minZ : maxZ))) < -nxW) &&
               !(((pxX) * (pxX < 0 ? minX : maxX) + (pxZ * (pxZ < 0 ? minZ : maxZ))) < -pxW) &&
               !(((nyX) * (nyX < 0 ? minX : maxX) + (nyZ * (nyZ < 0 ? minZ : maxZ))) < -nyW) &&
               !(((pyX) * (pyX < 0 ? minX : maxX) + (pyZ * (pyZ < 0 ? minZ : maxZ))) < -pyW) &&
               !(((nzX) * (nzX < 0 ? minX : maxX) + (nzZ * (nzZ < 0 ? minZ : maxZ))) < -nzW) &&
               !(((pzX) * (pzX < 0 ? minX : maxX) + (pzZ * (pzZ < 0 ? minZ : maxZ))) < -pzW) &&
               minX <= maxX && minZ <= maxZ;
    }

    /**
     * Test whether the given rectangle, lying in the XZ plane at <code>y = 0</code>,
     * is partly or completely within the frustum. The rect's x-axis maps to
     * world X and - since the rect lies in the XZ plane - its <em>y</em>-axis
     * maps to world <em>Z</em>: {@code rect.minY()} / {@code rect.maxY()} are
     * passed as {@code minZ} / {@code maxZ}. The y term of each plane drops out
     * because the rectangle's <code>y</code> is zero - this is <em>not</em> a
     * test of the rectangle extended infinitely along Y.
     * <p>
     * An empty rectangle - a minimum above its maximum in some component - is reported as outside.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param rect the rectangle to test, with its y-axis read as world Z
     * @return <code>false</code> if the rectangle lies completely outside one of the frustum planes, <code>true</code> otherwise (a conservative test)
     */
    public boolean testPlaneXZ(FloatRectR rect) {
        float[] rectData = ((FloatRectImpl) rect).data;
        return testPlaneXZ(rectData[0], rectData[1], rectData[2], rectData[3]);
    }

    /**
     * Determine how the axis-aligned box spanned by {@code min} and {@code max}
     * relates to the frustum.
     * <p>
     * An empty box - a minimum above its maximum in some component - is reported as
     * {@link Intersection#OUTSIDE}.
     * <p>
     * Valid input: {@code min} may be infinite; {@code max} may be infinite.
     *
     * @param min the corner of the box with the smaller coordinates
     * @param max the corner of the box with the larger coordinates
     * @return how the box relates to the frustum
     */
    public Intersection intersectAabb(Float3R min, Float3R max) {
        float[] minData = ((Float3Impl) min).data;
        float[] maxData = ((Float3Impl) max).data;
        return intersectAabb(minData[0], minData[1], minData[2], maxData[0], maxData[1], maxData[2]);
    }

    /**
     * Determine how the axis-aligned box spanned by <code>(minX, minY, minZ)</code>
     * and <code>(maxX, maxY, maxZ)</code> relates to the frustum.
     * <p>
     * {@link Intersection#INSIDE} is exact. {@link Intersection#INTERSECT} is
     * conservative in the same way
     * {@link #testAabb(float, float, float, float, float, float)} is: a box in a corner
     * region outside the frustum can be reported as intersecting instead of
     * {@link Intersection#OUTSIDE}. To learn <em>which</em> plane culls an outside box -
     * and to feed that back as a coherency hint next frame - use
     * {@link #cullingPlane(float, float, float, float, float, float)} instead.
     * <p>
     * The box is evaluated as centre and half-extent rather than by its corners, which
     * rounds differently: a box that touches a plane exactly can come out on either side
     * of it, and then be {@link Intersection#OUTSIDE} where {@code testAabb} reports it
     * visible, or {@link Intersection#INTERSECT} instead of {@link Intersection#INSIDE}.
     * A box with a NaN coordinate, and the empty box a default-constructed
     * {@code FloatAABB} holds (min +infinity, max -infinity), are
     * {@link Intersection#OUTSIDE}, as {@code testAabb} and {@code cullingPlane} cull
     * them too.
     * <p>
     * An empty box - a minimum above its maximum in some component - is reported as
     * {@link Intersection#OUTSIDE}.
     * <p>
     * Valid input: {@code (minX, minY, minZ)} may be infinite; {@code (maxX, maxY, maxZ)} may be infinite.
     *
     * @param minX the x coordinate of the corner with the smaller coordinates
     * @param minY the y coordinate of the corner with the smaller coordinates
     * @param minZ the z coordinate of the corner with the smaller coordinates
     * @param maxX the x coordinate of the corner with the larger coordinates
     * @param maxY the y coordinate of the corner with the larger coordinates
     * @param maxZ the z coordinate of the corner with the larger coordinates
     * @return how the box relates to the frustum
     */
    public Intersection intersectAabb(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        if (org.joml2.internal.FmaSwitch.ON) return intersectAabb_fma(minX, minY, minZ, maxX, maxY, maxZ);
        return intersectAabb_mulAdd(minX, minY, minZ, maxX, maxY, maxZ);
    }

    /** {@code intersectAabb} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Intersection intersectAabb_fma(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float cx = minX * 0.5f + maxX * 0.5f, cy = minY * 0.5f + maxY * 0.5f, cz = minZ * 0.5f + maxZ * 0.5f;
        float ex = maxX * 0.5f - minX * 0.5f, ey = maxY * 0.5f - minY * 0.5f, ez = maxZ * 0.5f - minZ * 0.5f;
        if (farNx_fma(cx, cy, cz, ex, ey, ez) < -nxW) return Intersection.OUTSIDE;
        boolean inside = nearNx_fma(cx, cy, cz, ex, ey, ez) >= -nxW;
        if (farPx_fma(cx, cy, cz, ex, ey, ez) < -pxW) return Intersection.OUTSIDE;
        inside &= nearPx_fma(cx, cy, cz, ex, ey, ez) >= -pxW;
        if (farNy_fma(cx, cy, cz, ex, ey, ez) < -nyW) return Intersection.OUTSIDE;
        inside &= nearNy_fma(cx, cy, cz, ex, ey, ez) >= -nyW;
        return intersectAabbYZ_fma(cx, cy, cz, ex, ey, ez, inside);
    }

    /** {@code intersectAabb} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Intersection intersectAabb_mulAdd(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float cx = minX * 0.5f + maxX * 0.5f, cy = minY * 0.5f + maxY * 0.5f, cz = minZ * 0.5f + maxZ * 0.5f;
        float ex = maxX * 0.5f - minX * 0.5f, ey = maxY * 0.5f - minY * 0.5f, ez = maxZ * 0.5f - minZ * 0.5f;
        if (farNx_mulAdd(cx, cy, cz, ex, ey, ez) < -nxW) return Intersection.OUTSIDE;
        boolean inside = nearNx_mulAdd(cx, cy, cz, ex, ey, ez) >= -nxW;
        if (farPx_mulAdd(cx, cy, cz, ex, ey, ez) < -pxW) return Intersection.OUTSIDE;
        inside &= nearPx_mulAdd(cx, cy, cz, ex, ey, ez) >= -pxW;
        if (farNy_mulAdd(cx, cy, cz, ex, ey, ez) < -nyW) return Intersection.OUTSIDE;
        inside &= nearNy_mulAdd(cx, cy, cz, ex, ey, ez) >= -nyW;
        return intersectAabbYZ_mulAdd(cx, cy, cz, ex, ey, ez, inside);
    }

    /** Second half of {@link #intersectAabb(float, float, float, float, float, float)}: the py, nz and pz planes. */
    private Intersection intersectAabbYZ_fma(float cx, float cy, float cz, float ex, float ey, float ez, boolean inside) {
        if (farPy_fma(cx, cy, cz, ex, ey, ez) < -pyW) return Intersection.OUTSIDE;
        inside &= nearPy_fma(cx, cy, cz, ex, ey, ez) >= -pyW;
        if (farNz_fma(cx, cy, cz, ex, ey, ez) < -nzW) return Intersection.OUTSIDE;
        inside &= nearNz_fma(cx, cy, cz, ex, ey, ez) >= -nzW;
        if (farPz_fma(cx, cy, cz, ex, ey, ez) < -pzW) return Intersection.OUTSIDE;
        inside &= nearPz_fma(cx, cy, cz, ex, ey, ez) >= -pzW;
        // the empty box (min +infinity, max -infinity) passes the planes whose far distance is 0 * infinity
        if (!(ex >= 0 && ey >= 0 && ez >= 0)) return Intersection.OUTSIDE;
        return inside ? Intersection.INSIDE : Intersection.INTERSECT;
    }

    /** Second half of {@link #intersectAabb(float, float, float, float, float, float)}: the py, nz and pz planes. */
    private Intersection intersectAabbYZ_mulAdd(float cx, float cy, float cz, float ex, float ey, float ez, boolean inside) {
        if (farPy_mulAdd(cx, cy, cz, ex, ey, ez) < -pyW) return Intersection.OUTSIDE;
        inside &= nearPy_mulAdd(cx, cy, cz, ex, ey, ez) >= -pyW;
        if (farNz_mulAdd(cx, cy, cz, ex, ey, ez) < -nzW) return Intersection.OUTSIDE;
        inside &= nearNz_mulAdd(cx, cy, cz, ex, ey, ez) >= -nzW;
        if (farPz_mulAdd(cx, cy, cz, ex, ey, ez) < -pzW) return Intersection.OUTSIDE;
        inside &= nearPz_mulAdd(cx, cy, cz, ex, ey, ez) >= -pzW;
        // the empty box (min +infinity, max -infinity) passes the planes whose far distance is 0 * infinity
        if (!(ex >= 0 && ey >= 0 && ez >= 0)) return Intersection.OUTSIDE;
        return inside ? Intersection.INSIDE : Intersection.INTERSECT;
    }

    /**
     * Determine how {@code aabb} relates to the frustum.
     * <p>
     * An empty box - a minimum above its maximum in some component - is reported as
     * {@link Intersection#OUTSIDE}.
     * <p>
     * Valid input: the minimum corner of {@code aabb} may be infinite; the maximum corner of {@code aabb} may be infinite.
     *
     * @param aabb the axis-aligned box to test
     * @return how the box relates to the frustum
     */
    public Intersection intersectAabb(FloatAABBR aabb) {
        float[] aabbData = ((FloatAABBImpl) aabb).data;
        return intersectAabb(aabbData[0], aabbData[1], aabbData[2],
                            aabbData[3], aabbData[4], aabbData[5]);
    }

    /**
     * Determine how the axis-aligned box spanned by {@code min} and {@code max}
     * relates to the frustum, letting only the planes selected by {@code mask} cull it.
     * <p>
     * An empty box - a minimum above its maximum in some component - is reported as
     * {@link Intersection#OUTSIDE}.
     * <p>
     * Valid input: {@code min} may be infinite; {@code max} may be infinite.
     *
     * @param min the corner of the box with the smaller coordinates
     * @param max the corner of the box with the larger coordinates
     * @param mask a bit mask of the <code>PLANE_MASK_*</code> constants selecting the
     *        planes that may cull the box
     * @return how the box relates to the frustum
     */
    public Intersection intersectAabb(Float3R min, Float3R max, int mask) {
        float[] minData = ((Float3Impl) min).data;
        float[] maxData = ((Float3Impl) max).data;
        return intersectAabb(minData[0], minData[1], minData[2], maxData[0], maxData[1], maxData[2], mask);
    }

    /**
     * Determine how the axis-aligned box spanned by <code>(minX, minY, minZ)</code>
     * and <code>(maxX, maxY, maxZ)</code> relates to the frustum, letting only the
     * planes selected by {@code mask} cull it.
     * <p>
     * Masking a plane off is the standard optimization for a bounding-volume
     * hierarchy: once a parent node is known to be fully inside a plane, none of its
     * children can be culled by that plane either. Verdicts are the same as for
     * {@link #intersectAabb(float, float, float, float, float, float)}.
     * <p>
     * An empty box - a minimum above its maximum in some component - is reported as
     * {@link Intersection#OUTSIDE}.
     * <p>
     * Valid input: {@code (minX, minY, minZ)} may be infinite; {@code (maxX, maxY, maxZ)} may be infinite.
     *
     * @param minX the x coordinate of the corner with the smaller coordinates
     * @param minY the y coordinate of the corner with the smaller coordinates
     * @param minZ the z coordinate of the corner with the smaller coordinates
     * @param maxX the x coordinate of the corner with the larger coordinates
     * @param maxY the y coordinate of the corner with the larger coordinates
     * @param maxZ the z coordinate of the corner with the larger coordinates
     * @param mask a bit mask of the <code>PLANE_MASK_*</code> constants selecting the
     *        planes that may cull the box; a plane whose bit is clear cannot cull it,
     *        but still takes part in the {@link Intersection#INSIDE} decision
     * @return how the box relates to the frustum
     */
    public Intersection intersectAabb(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int mask) {
        if (org.joml2.internal.FmaSwitch.ON) return intersectAabb_fma(minX, minY, minZ, maxX, maxY, maxZ, mask);
        return intersectAabb_mulAdd(minX, minY, minZ, maxX, maxY, maxZ, mask);
    }

    /** {@code intersectAabb} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Intersection intersectAabb_fma(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int mask) {
        float cx = minX * 0.5f + maxX * 0.5f, cy = minY * 0.5f + maxY * 0.5f, cz = minZ * 0.5f + maxZ * 0.5f;
        float ex = maxX * 0.5f - minX * 0.5f, ey = maxY * 0.5f - minY * 0.5f, ez = maxZ * 0.5f - minZ * 0.5f;
        if ((mask & PLANE_MASK_NX) != 0 && farNx_fma(cx, cy, cz, ex, ey, ez) < -nxW) return Intersection.OUTSIDE;
        boolean inside = nearNx_fma(cx, cy, cz, ex, ey, ez) >= -nxW;
        if ((mask & PLANE_MASK_PX) != 0 && farPx_fma(cx, cy, cz, ex, ey, ez) < -pxW) return Intersection.OUTSIDE;
        inside &= nearPx_fma(cx, cy, cz, ex, ey, ez) >= -pxW;
        if ((mask & PLANE_MASK_NY) != 0 && farNy_fma(cx, cy, cz, ex, ey, ez) < -nyW) return Intersection.OUTSIDE;
        inside &= nearNy_fma(cx, cy, cz, ex, ey, ez) >= -nyW;
        return intersectAabbYZ_fma(cx, cy, cz, ex, ey, ez, mask, inside);
    }

    /** {@code intersectAabb} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Intersection intersectAabb_mulAdd(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int mask) {
        float cx = minX * 0.5f + maxX * 0.5f, cy = minY * 0.5f + maxY * 0.5f, cz = minZ * 0.5f + maxZ * 0.5f;
        float ex = maxX * 0.5f - minX * 0.5f, ey = maxY * 0.5f - minY * 0.5f, ez = maxZ * 0.5f - minZ * 0.5f;
        if ((mask & PLANE_MASK_NX) != 0 && farNx_mulAdd(cx, cy, cz, ex, ey, ez) < -nxW) return Intersection.OUTSIDE;
        boolean inside = nearNx_mulAdd(cx, cy, cz, ex, ey, ez) >= -nxW;
        if ((mask & PLANE_MASK_PX) != 0 && farPx_mulAdd(cx, cy, cz, ex, ey, ez) < -pxW) return Intersection.OUTSIDE;
        inside &= nearPx_mulAdd(cx, cy, cz, ex, ey, ez) >= -pxW;
        if ((mask & PLANE_MASK_NY) != 0 && farNy_mulAdd(cx, cy, cz, ex, ey, ez) < -nyW) return Intersection.OUTSIDE;
        inside &= nearNy_mulAdd(cx, cy, cz, ex, ey, ez) >= -nyW;
        return intersectAabbYZ_mulAdd(cx, cy, cz, ex, ey, ez, mask, inside);
    }

    /** Second half of {@link #intersectAabb(float, float, float, float, float, float, int)}: the py, nz and pz planes. */
    private Intersection intersectAabbYZ_fma(float cx, float cy, float cz, float ex, float ey, float ez, int mask, boolean inside) {
        if ((mask & PLANE_MASK_PY) != 0 && farPy_fma(cx, cy, cz, ex, ey, ez) < -pyW) return Intersection.OUTSIDE;
        inside &= nearPy_fma(cx, cy, cz, ex, ey, ez) >= -pyW;
        if ((mask & PLANE_MASK_NZ) != 0 && farNz_fma(cx, cy, cz, ex, ey, ez) < -nzW) return Intersection.OUTSIDE;
        inside &= nearNz_fma(cx, cy, cz, ex, ey, ez) >= -nzW;
        if ((mask & PLANE_MASK_PZ) != 0 && farPz_fma(cx, cy, cz, ex, ey, ez) < -pzW) return Intersection.OUTSIDE;
        inside &= nearPz_fma(cx, cy, cz, ex, ey, ez) >= -pzW;
        // the empty box (min +infinity, max -infinity) passes the planes whose far distance is 0 * infinity
        if (!(ex >= 0 && ey >= 0 && ez >= 0)) return Intersection.OUTSIDE;
        return inside ? Intersection.INSIDE : Intersection.INTERSECT;
    }

    /** Second half of {@link #intersectAabb(float, float, float, float, float, float, int)}: the py, nz and pz planes. */
    private Intersection intersectAabbYZ_mulAdd(float cx, float cy, float cz, float ex, float ey, float ez, int mask, boolean inside) {
        if ((mask & PLANE_MASK_PY) != 0 && farPy_mulAdd(cx, cy, cz, ex, ey, ez) < -pyW) return Intersection.OUTSIDE;
        inside &= nearPy_mulAdd(cx, cy, cz, ex, ey, ez) >= -pyW;
        if ((mask & PLANE_MASK_NZ) != 0 && farNz_mulAdd(cx, cy, cz, ex, ey, ez) < -nzW) return Intersection.OUTSIDE;
        inside &= nearNz_mulAdd(cx, cy, cz, ex, ey, ez) >= -nzW;
        if ((mask & PLANE_MASK_PZ) != 0 && farPz_mulAdd(cx, cy, cz, ex, ey, ez) < -pzW) return Intersection.OUTSIDE;
        inside &= nearPz_mulAdd(cx, cy, cz, ex, ey, ez) >= -pzW;
        // the empty box (min +infinity, max -infinity) passes the planes whose far distance is 0 * infinity
        if (!(ex >= 0 && ey >= 0 && ez >= 0)) return Intersection.OUTSIDE;
        return inside ? Intersection.INSIDE : Intersection.INTERSECT;
    }

    /**
     * Determine how {@code aabb} relates to the frustum, letting only the planes
     * selected by {@code mask} cull it.
     * <p>
     * An empty box - a minimum above its maximum in some component - is reported as
     * {@link Intersection#OUTSIDE}.
     * <p>
     * Valid input: the minimum corner of {@code aabb} may be infinite; the maximum corner of {@code aabb} may be infinite.
     *
     * @param aabb the axis-aligned box to test
     * @param mask a bit mask of the <code>PLANE_MASK_*</code> constants selecting the
     *        planes that may cull the box
     * @return how the box relates to the frustum
     */
    public Intersection intersectAabb(FloatAABBR aabb, int mask) {
        float[] aabbData = ((FloatAABBImpl) aabb).data;
        return intersectAabb(aabbData[0], aabbData[1], aabbData[2],
                            aabbData[3], aabbData[4], aabbData[5], mask);
    }

    /**
     * Determine which frustum plane culls the axis-aligned box spanned by {@code min}
     * and {@code max}.
     * <p>
     * An empty box - a minimum above its maximum in some component - is always culled (by
     * {@link FrustumPlane#NX} when no plane culls it by itself).
     * <p>
     * Valid input: {@code min} may be infinite; {@code max} may be infinite.
     *
     * @param min the corner of the box with the smaller coordinates
     * @param max the corner of the box with the larger coordinates
     * @return the plane that culls the box, or <code>null</code> if none does
     */
    public FrustumPlane cullingPlane(Float3R min, Float3R max) {
        float[] minData = ((Float3Impl) min).data;
        float[] maxData = ((Float3Impl) max).data;
        return cullingPlane(minData[0], minData[1], minData[2], maxData[0], maxData[1], maxData[2]);
    }

    /**
     * Determine which frustum plane culls the axis-aligned box spanned by
     * <code>(minX, minY, minZ)</code> and <code>(maxX, maxY, maxZ)</code>.
     * <p>
     * A <code>null</code> result means the box is not culled - it is inside the frustum
     * or straddles it. Unlike
     * {@link #intersectAabb(float, float, float, float, float, float)} this does not
     * distinguish those two cases, and is correspondingly cheaper: it never evaluates
     * the fully-inside half of the test.
     * <p>
     * An empty box - a minimum above its maximum in some component - is always culled (by
     * {@link FrustumPlane#NX} when no plane culls it by itself).
     * <p>
     * Valid input: {@code (minX, minY, minZ)} may be infinite; {@code (maxX, maxY, maxZ)} may be infinite.
     *
     * @param minX the x coordinate of the corner with the smaller coordinates
     * @param minY the y coordinate of the corner with the smaller coordinates
     * @param minZ the z coordinate of the corner with the smaller coordinates
     * @param maxX the x coordinate of the corner with the larger coordinates
     * @param maxY the y coordinate of the corner with the larger coordinates
     * @param maxZ the z coordinate of the corner with the larger coordinates
     * @return the plane that culls the box, or <code>null</code> if none does
     */
    public FrustumPlane cullingPlane(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return cullingPlane_mulAdd(minX, minY, minZ, maxX, maxY, maxZ);
        if (outsidePv_fma(nxX, nxY, nxZ, nxW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NX;
        if (outsidePv_fma(pxX, pxY, pxZ, pxW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PX;
        if (outsidePv_fma(nyX, nyY, nyZ, nyW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NY;
        if (outsidePv_fma(pyX, pyY, pyZ, pyW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PY;
        if (outsidePv_fma(nzX, nzY, nzZ, nzW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NZ;
        if (outsidePv_fma(pzX, pzY, pzZ, pzW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PZ;
        // the empty box passes the planes whose p-vertex distance is 0 * infinity: every plane culls it
        return minX <= maxX && minY <= maxY && minZ <= maxZ ? null : FrustumPlane.NX;
    }

    /** {@code cullingPlane} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FrustumPlane cullingPlane_mulAdd(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        if (outsidePv_mulAdd(nxX, nxY, nxZ, nxW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NX;
        if (outsidePv_mulAdd(pxX, pxY, pxZ, pxW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PX;
        if (outsidePv_mulAdd(nyX, nyY, nyZ, nyW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NY;
        if (outsidePv_mulAdd(pyX, pyY, pyZ, pyW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PY;
        if (outsidePv_mulAdd(nzX, nzY, nzZ, nzW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NZ;
        if (outsidePv_mulAdd(pzX, pzY, pzZ, pzW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PZ;
        return minX <= maxX && minY <= maxY && minZ <= maxZ ? null : FrustumPlane.NX;
    }

    /**
     * Determine which frustum plane culls {@code aabb}.
     * <p>
     * An empty box - a minimum above its maximum in some component - is always culled (by
     * {@link FrustumPlane#NX} when no plane culls it by itself).
     * <p>
     * Valid input: the minimum corner of {@code aabb} may be infinite; the maximum corner of {@code aabb} may be infinite.
     *
     * @param aabb the axis-aligned box to test
     * @return the plane that culls the box, or <code>null</code> if none does
     */
    public FrustumPlane cullingPlane(FloatAABBR aabb) {
        float[] aabbData = ((FloatAABBImpl) aabb).data;
        return cullingPlane(aabbData[0], aabbData[1], aabbData[2],
                            aabbData[3], aabbData[4], aabbData[5]);
    }

    /**
     * Determine which of the planes selected by {@code mask} culls the axis-aligned box
     * spanned by {@code min} and {@code max}.
     * <p>
     * An empty box - a minimum above its maximum in some component - is always culled (by
     * {@link FrustumPlane#NX} when no plane culls it by itself), unless {@code mask} selects no plane.
     * <p>
     * Valid input: {@code min} may be infinite; {@code max} may be infinite.
     *
     * @param min the corner of the box with the smaller coordinates
     * @param max the corner of the box with the larger coordinates
     * @param mask a bit mask of the <code>PLANE_MASK_*</code> constants selecting the
     *        planes that may cull the box
     * @return the plane that culls the box, or <code>null</code> if none does
     */
    public FrustumPlane cullingPlane(Float3R min, Float3R max, int mask) {
        float[] minData = ((Float3Impl) min).data;
        float[] maxData = ((Float3Impl) max).data;
        return cullingPlane(minData[0], minData[1], minData[2], maxData[0], maxData[1], maxData[2], mask);
    }

    /**
     * Determine which of the planes selected by {@code mask} culls the axis-aligned box
     * spanned by <code>(minX, minY, minZ)</code> and <code>(maxX, maxY, maxZ)</code>.
     * <p>
     * Masking a plane off is the standard optimization for a bounding-volume
     * hierarchy: once a parent node is known to be fully inside a plane, none of its
     * children can be culled by that plane either.
     * <p>
     * An empty box - a minimum above its maximum in some component - is always culled (by
     * {@link FrustumPlane#NX} when no plane culls it by itself), unless {@code mask} selects no plane.
     * <p>
     * Valid input: {@code (minX, minY, minZ)} may be infinite; {@code (maxX, maxY, maxZ)} may be infinite.
     *
     * @param minX the x coordinate of the corner with the smaller coordinates
     * @param minY the y coordinate of the corner with the smaller coordinates
     * @param minZ the z coordinate of the corner with the smaller coordinates
     * @param maxX the x coordinate of the corner with the larger coordinates
     * @param maxY the y coordinate of the corner with the larger coordinates
     * @param maxZ the z coordinate of the corner with the larger coordinates
     * @param mask a bit mask of the <code>PLANE_MASK_*</code> constants selecting the
     *        planes that may cull the box
     * @return the plane that culls the box, or <code>null</code> if none does
     */
    public FrustumPlane cullingPlane(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int mask) {
        if (org.joml2.internal.FmaSwitch.ON) return cullingPlane_fma(minX, minY, minZ, maxX, maxY, maxZ, mask);
        return cullingPlane_mulAdd(minX, minY, minZ, maxX, maxY, maxZ, mask);
    }

    /** {@code cullingPlane} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FrustumPlane cullingPlane_fma(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int mask) {
        if ((mask & PLANE_MASK_NX) != 0 && outsidePv_fma(nxX, nxY, nxZ, nxW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NX;
        if ((mask & PLANE_MASK_PX) != 0 && outsidePv_fma(pxX, pxY, pxZ, pxW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PX;
        if ((mask & PLANE_MASK_NY) != 0 && outsidePv_fma(nyX, nyY, nyZ, nyW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NY;
        if ((mask & PLANE_MASK_PY) != 0 && outsidePv_fma(pyX, pyY, pyZ, pyW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PY;
        if ((mask & PLANE_MASK_NZ) != 0 && outsidePv_fma(nzX, nzY, nzZ, nzW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NZ;
        if ((mask & PLANE_MASK_PZ) != 0 && outsidePv_fma(pzX, pzY, pzZ, pzW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PZ;
        return minX <= maxX && minY <= maxY && minZ <= maxZ || (mask & 63) == 0 ? null
            : FrustumPlane.values()[Integer.numberOfTrailingZeros(mask & 63)];
    }

    /** {@code cullingPlane} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FrustumPlane cullingPlane_mulAdd(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int mask) {
        if ((mask & PLANE_MASK_NX) != 0 && outsidePv_mulAdd(nxX, nxY, nxZ, nxW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NX;
        if ((mask & PLANE_MASK_PX) != 0 && outsidePv_mulAdd(pxX, pxY, pxZ, pxW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PX;
        if ((mask & PLANE_MASK_NY) != 0 && outsidePv_mulAdd(nyX, nyY, nyZ, nyW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NY;
        if ((mask & PLANE_MASK_PY) != 0 && outsidePv_mulAdd(pyX, pyY, pyZ, pyW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PY;
        if ((mask & PLANE_MASK_NZ) != 0 && outsidePv_mulAdd(nzX, nzY, nzZ, nzW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NZ;
        if ((mask & PLANE_MASK_PZ) != 0 && outsidePv_mulAdd(pzX, pzY, pzZ, pzW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PZ;
        return minX <= maxX && minY <= maxY && minZ <= maxZ || (mask & 63) == 0 ? null
            : FrustumPlane.values()[Integer.numberOfTrailingZeros(mask & 63)];
    }

    /**
     * Determine which of the planes selected by {@code mask} culls {@code aabb}.
     * <p>
     * An empty box - a minimum above its maximum in some component - is always culled (by
     * {@link FrustumPlane#NX} when no plane culls it by itself), unless {@code mask} selects no plane.
     * <p>
     * Valid input: the minimum corner of {@code aabb} may be infinite; the maximum corner of {@code aabb} may be infinite.
     *
     * @param aabb the axis-aligned box to test
     * @param mask a bit mask of the <code>PLANE_MASK_*</code> constants selecting the
     *        planes that may cull the box
     * @return the plane that culls the box, or <code>null</code> if none does
     */
    public FrustumPlane cullingPlane(FloatAABBR aabb, int mask) {
        float[] aabbData = ((FloatAABBImpl) aabb).data;
        return cullingPlane(aabbData[0], aabbData[1], aabbData[2],
                            aabbData[3], aabbData[4], aabbData[5], mask);
    }

    /**
     * Determine which of the planes selected by {@code mask} culls the axis-aligned box
     * spanned by {@code min} and {@code max}, trying {@code hint} first.
     * <p>
     * An empty box - a minimum above its maximum in some component - is always culled (by
     * {@link FrustumPlane#NX} when no plane culls it by itself), unless {@code mask} selects no plane.
     * <p>
     * Valid input: {@code min} may be infinite; {@code max} may be infinite.
     *
     * @param min the corner of the box with the smaller coordinates
     * @param max the corner of the box with the larger coordinates
     * @param mask a bit mask of the <code>PLANE_MASK_*</code> constants selecting the
     *        planes that may cull the box
     * @param hint the plane to test first, or <code>null</code> for no hint
     * @return the plane that culls the box, or <code>null</code> if none does
     */
    public FrustumPlane cullingPlane(Float3R min, Float3R max, int mask, FrustumPlane hint) {
        float[] minData = ((Float3Impl) min).data;
        float[] maxData = ((Float3Impl) max).data;
        return cullingPlane(minData[0], minData[1], minData[2], maxData[0], maxData[1], maxData[2], mask, hint);
    }

    /**
     * Determine which of the planes selected by {@code mask} culls the axis-aligned box
     * spanned by <code>(minX, minY, minZ)</code> and <code>(maxX, maxY, maxZ)</code>,
     * trying {@code hint} first.
     * <p>
     * Frame-to-frame coherency: an object culled by one plane last frame is likely to
     * be culled by the same plane again, so testing that plane first usually ends the
     * query after a single comparison. Feed the previous call's result straight back -
     * its <code>null</code> (nothing culled the box) is a valid "no hint" argument:
     * <pre>{@code
     * hint = frustum.cullingPlane(box, mask, hint);
     * if (hint != null) skip(box);
     * }</pre>
     * <p>
     * An empty box - a minimum above its maximum in some component - is always culled (by
     * {@link FrustumPlane#NX} when no plane culls it by itself), unless {@code mask} selects no plane.
     * <p>
     * Valid input: {@code (minX, minY, minZ)} may be infinite; {@code (maxX, maxY, maxZ)} may be infinite.
     *
     * @param minX the x coordinate of the corner with the smaller coordinates
     * @param minY the y coordinate of the corner with the smaller coordinates
     * @param minZ the z coordinate of the corner with the smaller coordinates
     * @param maxX the x coordinate of the corner with the larger coordinates
     * @param maxY the y coordinate of the corner with the larger coordinates
     * @param maxZ the z coordinate of the corner with the larger coordinates
     * @param mask a bit mask of the <code>PLANE_MASK_*</code> constants selecting the
     *        planes that may cull the box
     * @param hint the plane to test first, or <code>null</code> for no hint; only
     *        consulted when its bit is set in {@code mask}
     * @return the plane that culls the box, or <code>null</code> if none does
     */
    public FrustumPlane cullingPlane(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int mask, FrustumPlane hint) {
        if (org.joml2.internal.FmaSwitch.ON) {
            if (hint != null && (mask & (1 << hint.ordinal())) != 0
                    && cullsAabb_fma(hint, minX, minY, minZ, maxX, maxY, maxZ))
                return hint;
            return cullingPlane(minX, minY, minZ, maxX, maxY, maxZ, mask);
        } else {
            if (hint != null && (mask & (1 << hint.ordinal())) != 0
                    && cullsAabb_mulAdd(hint, minX, minY, minZ, maxX, maxY, maxZ))
                return hint;
            return cullingPlane(minX, minY, minZ, maxX, maxY, maxZ, mask);
        }
    }

    /**
     * Determine which of the planes selected by {@code mask} culls {@code aabb}, trying
     * {@code hint} first.
     * <p>
     * An empty box - a minimum above its maximum in some component - is always culled (by
     * {@link FrustumPlane#NX} when no plane culls it by itself), unless {@code mask} selects no plane.
     * <p>
     * Valid input: the minimum corner of {@code aabb} may be infinite; the maximum corner of {@code aabb} may be infinite.
     *
     * @param aabb the axis-aligned box to test
     * @param mask a bit mask of the <code>PLANE_MASK_*</code> constants selecting the
     *        planes that may cull the box
     * @param hint the plane to test first, or <code>null</code> for no hint
     * @return the plane that culls the box, or <code>null</code> if none does
     */
    public FrustumPlane cullingPlane(FloatAABBR aabb, int mask, FrustumPlane hint) {
        float[] aabbData = ((FloatAABBImpl) aabb).data;
        return cullingPlane(aabbData[0], aabbData[1], aabbData[2],
                            aabbData[3], aabbData[4], aabbData[5], mask, hint);
    }

    /**
     * Compute the smallest signed distance any corner of the axis-aligned box spanned
     * by <code>(minX, minY, minZ)</code> and <code>(maxX, maxY, maxZ)</code> has from
     * the given frustum plane.
     * <p>
     * This is the minimum of the plane equation over the box's eight corners, so it
     * is negative exactly when at least one corner lies outside the plane, and
     * non-negative exactly when the whole box lies in that plane's inside half-space.
     * It is a distance in world units only when the frustum was built with
     * <code>allowTestSpheres = true</code>; otherwise it is scaled by the length of
     * the plane normal and only its sign and ordering are meaningful.
     * <p>
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any component.
     *
     * @param minX the x coordinate of the corner with the smaller coordinates
     * @param minY the y coordinate of the corner with the smaller coordinates
     * @param minZ the z coordinate of the corner with the smaller coordinates
     * @param maxX the x coordinate of the corner with the larger coordinates
     * @param maxY the y coordinate of the corner with the larger coordinates
     * @param maxZ the z coordinate of the corner with the larger coordinates
     * @param plane the plane to measure against
     * @return the signed distance of the box corner least inside the plane
     */
    public float distanceToPlane(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, FrustumPlane plane) {
        if (!org.joml2.internal.FmaSwitch.ON) return distanceToPlane_mulAdd(minX, minY, minZ, maxX, maxY, maxZ, plane);
        switch (plane) {
            case NX: return nvDist_fma(nxX, nxY, nxZ, nxW, minX, minY, minZ, maxX, maxY, maxZ);
            case PX: return nvDist_fma(pxX, pxY, pxZ, pxW, minX, minY, minZ, maxX, maxY, maxZ);
            case NY: return nvDist_fma(nyX, nyY, nyZ, nyW, minX, minY, minZ, maxX, maxY, maxZ);
            case PY: return nvDist_fma(pyX, pyY, pyZ, pyW, minX, minY, minZ, maxX, maxY, maxZ);
            case NZ: return nvDist_fma(nzX, nzY, nzZ, nzW, minX, minY, minZ, maxX, maxY, maxZ);
            case PZ: return nvDist_fma(pzX, pzY, pzZ, pzW, minX, minY, minZ, maxX, maxY, maxZ);
            default: throw new IllegalArgumentException("Unknown FrustumPlane: " + plane);
        }
    }

    /** {@code distanceToPlane} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private float distanceToPlane_mulAdd(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, FrustumPlane plane) {
        switch (plane) {
            case NX: return nvDist_mulAdd(nxX, nxY, nxZ, nxW, minX, minY, minZ, maxX, maxY, maxZ);
            case PX: return nvDist_mulAdd(pxX, pxY, pxZ, pxW, minX, minY, minZ, maxX, maxY, maxZ);
            case NY: return nvDist_mulAdd(nyX, nyY, nyZ, nyW, minX, minY, minZ, maxX, maxY, maxZ);
            case PY: return nvDist_mulAdd(pyX, pyY, pyZ, pyW, minX, minY, minZ, maxX, maxY, maxZ);
            case NZ: return nvDist_mulAdd(nzX, nzY, nzZ, nzW, minX, minY, minZ, maxX, maxY, maxZ);
            case PZ: return nvDist_mulAdd(pzX, pzY, pzZ, pzW, minX, minY, minZ, maxX, maxY, maxZ);
            default: throw new IllegalArgumentException("Unknown FrustumPlane: " + plane);
        }
    }

    /** Signed distance of the box vertex nearest along the plane normal from the plane. */
    private static float nvDist_fma(float a, float b, float c, float w, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return java.lang.Math.fma(a, a < 0 ? maxX : minX, java.lang.Math.fma(b, b < 0 ? maxY : minY, java.lang.Math.fma(c, c < 0 ? maxZ : minZ, w)));
    }

    /** Signed distance of the box vertex nearest along the plane normal from the plane. */
    private static float nvDist_mulAdd(float a, float b, float c, float w, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return ((a) * (a < 0 ? maxX : minX) + (((b) * (b < 0 ? maxY : minY) + (((c) * (c < 0 ? maxZ : minZ) + (w))))));
    }

    /**
     * Test whether the line segment from {@code a} to {@code b} is partly or
     * completely inside the frustum.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param a the first end point of the line segment
     * @param b the second end point of the line segment
     * @return <code>true</code> iff the line segment is partly or completely inside
     *         the frustum
     */
    public boolean testLineSegment(Float3R a, Float3R b) {
        float[] aData = ((Float3Impl) a).data;
        float[] bData = ((Float3Impl) b).data;
        return testLineSegment(aData[0], aData[1], aData[2], bData[0], bData[1], bData[2]);
    }

    /**
     * Test whether the line segment from <code>(aX, aY, aZ)</code> to
     * <code>(bX, bY, bZ)</code> is partly or completely inside the frustum.
     * <p>
     * Exact, unlike the volume tests: the segment is clipped against each plane in
     * turn, so a segment passing through a corner region outside the frustum is
     * correctly reported as outside.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param aX the x coordinate of the first end point
     * @param aY the y coordinate of the first end point
     * @param aZ the z coordinate of the first end point
     * @param bX the x coordinate of the second end point
     * @param bY the y coordinate of the second end point
     * @param bZ the z coordinate of the second end point
     * @return <code>true</code> iff the line segment is partly or completely inside
     *         the frustum
     */
    public boolean testLineSegment(float aX, float aY, float aZ, float bX, float bY, float bZ) {
        if (org.joml2.internal.FmaSwitch.ON) return testLineSegment_fma(aX, aY, aZ, bX, bY, bZ);
        return testLineSegment_mulAdd(aX, aY, aZ, bX, bY, bZ);
    }

    /** {@code testLineSegment} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean testLineSegment_fma(float aX, float aY, float aZ, float bX, float bY, float bZ) {
        float da, db;
        da = java.lang.Math.fma(nxX, aX, java.lang.Math.fma(nxY, aY, java.lang.Math.fma(nxZ, aZ, nxW)));
        db = java.lang.Math.fma(nxX, bX, java.lang.Math.fma(nxY, bY, java.lang.Math.fma(nxZ, bZ, nxW)));
        if (da < 0.0f && db < 0.0f) return false;
        if (da < 0.0f || db < 0.0f) {
            float p = java.lang.Math.abs(da) / java.lang.Math.abs(db - da);
            float dx = java.lang.Math.fma(bX - aX, p, aX), dy = java.lang.Math.fma(bY - aY, p, aY), dz = java.lang.Math.fma(bZ - aZ, p, aZ);
            if (da < 0.0f) { aX = dx; aY = dy; aZ = dz; }
            else           { bX = dx; bY = dy; bZ = dz; }
        }
        da = java.lang.Math.fma(pxX, aX, java.lang.Math.fma(pxY, aY, java.lang.Math.fma(pxZ, aZ, pxW)));
        db = java.lang.Math.fma(pxX, bX, java.lang.Math.fma(pxY, bY, java.lang.Math.fma(pxZ, bZ, pxW)));
        if (da < 0.0f && db < 0.0f) return false;
        if (da < 0.0f || db < 0.0f) {
            float p = java.lang.Math.abs(da) / java.lang.Math.abs(db - da);
            float dx = java.lang.Math.fma(bX - aX, p, aX), dy = java.lang.Math.fma(bY - aY, p, aY), dz = java.lang.Math.fma(bZ - aZ, p, aZ);
            if (da < 0.0f) { aX = dx; aY = dy; aZ = dz; }
            else           { bX = dx; bY = dy; bZ = dz; }
        }
        da = java.lang.Math.fma(nyX, aX, java.lang.Math.fma(nyY, aY, java.lang.Math.fma(nyZ, aZ, nyW)));
        db = java.lang.Math.fma(nyX, bX, java.lang.Math.fma(nyY, bY, java.lang.Math.fma(nyZ, bZ, nyW)));
        if (da < 0.0f && db < 0.0f) return false;
        if (da < 0.0f || db < 0.0f) {
            float p = java.lang.Math.abs(da) / java.lang.Math.abs(db - da);
            float dx = java.lang.Math.fma(bX - aX, p, aX), dy = java.lang.Math.fma(bY - aY, p, aY), dz = java.lang.Math.fma(bZ - aZ, p, aZ);
            if (da < 0.0f) { aX = dx; aY = dy; aZ = dz; }
            else           { bX = dx; bY = dy; bZ = dz; }
        }
        da = java.lang.Math.fma(pyX, aX, java.lang.Math.fma(pyY, aY, java.lang.Math.fma(pyZ, aZ, pyW)));
        db = java.lang.Math.fma(pyX, bX, java.lang.Math.fma(pyY, bY, java.lang.Math.fma(pyZ, bZ, pyW)));
        if (da < 0.0f && db < 0.0f) return false;
        if (da < 0.0f || db < 0.0f) {
            float p = java.lang.Math.abs(da) / java.lang.Math.abs(db - da);
            float dx = java.lang.Math.fma(bX - aX, p, aX), dy = java.lang.Math.fma(bY - aY, p, aY), dz = java.lang.Math.fma(bZ - aZ, p, aZ);
            if (da < 0.0f) { aX = dx; aY = dy; aZ = dz; }
            else           { bX = dx; bY = dy; bZ = dz; }
        }
        da = java.lang.Math.fma(nzX, aX, java.lang.Math.fma(nzY, aY, java.lang.Math.fma(nzZ, aZ, nzW)));
        db = java.lang.Math.fma(nzX, bX, java.lang.Math.fma(nzY, bY, java.lang.Math.fma(nzZ, bZ, nzW)));
        if (da < 0.0f && db < 0.0f) return false;
        if (da < 0.0f || db < 0.0f) {
            float p = java.lang.Math.abs(da) / java.lang.Math.abs(db - da);
            float dx = java.lang.Math.fma(bX - aX, p, aX), dy = java.lang.Math.fma(bY - aY, p, aY), dz = java.lang.Math.fma(bZ - aZ, p, aZ);
            if (da < 0.0f) { aX = dx; aY = dy; aZ = dz; }
            else           { bX = dx; bY = dy; bZ = dz; }
        }
        da = java.lang.Math.fma(pzX, aX, java.lang.Math.fma(pzY, aY, java.lang.Math.fma(pzZ, aZ, pzW)));
        db = java.lang.Math.fma(pzX, bX, java.lang.Math.fma(pzY, bY, java.lang.Math.fma(pzZ, bZ, pzW)));
        return da >= 0.0f || db >= 0.0f;
    }

    /** {@code testLineSegment} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private boolean testLineSegment_mulAdd(float aX, float aY, float aZ, float bX, float bY, float bZ) {
        float da, db;
        da = ((nxX) * (aX) + (((nxY) * (aY) + (((nxZ) * (aZ) + (nxW))))));
        db = ((nxX) * (bX) + (((nxY) * (bY) + (((nxZ) * (bZ) + (nxW))))));
        if (da < 0.0f && db < 0.0f) return false;
        if (da < 0.0f || db < 0.0f) {
            float p = java.lang.Math.abs(da) / java.lang.Math.abs(db - da);
            float dx = ((bX - aX) * (p) + (aX)), dy = ((bY - aY) * (p) + (aY)), dz = ((bZ - aZ) * (p) + (aZ));
            if (da < 0.0f) { aX = dx; aY = dy; aZ = dz; }
            else           { bX = dx; bY = dy; bZ = dz; }
        }
        da = ((pxX) * (aX) + (((pxY) * (aY) + (((pxZ) * (aZ) + (pxW))))));
        db = ((pxX) * (bX) + (((pxY) * (bY) + (((pxZ) * (bZ) + (pxW))))));
        if (da < 0.0f && db < 0.0f) return false;
        if (da < 0.0f || db < 0.0f) {
            float p = java.lang.Math.abs(da) / java.lang.Math.abs(db - da);
            float dx = ((bX - aX) * (p) + (aX)), dy = ((bY - aY) * (p) + (aY)), dz = ((bZ - aZ) * (p) + (aZ));
            if (da < 0.0f) { aX = dx; aY = dy; aZ = dz; }
            else           { bX = dx; bY = dy; bZ = dz; }
        }
        da = ((nyX) * (aX) + (((nyY) * (aY) + (((nyZ) * (aZ) + (nyW))))));
        db = ((nyX) * (bX) + (((nyY) * (bY) + (((nyZ) * (bZ) + (nyW))))));
        if (da < 0.0f && db < 0.0f) return false;
        if (da < 0.0f || db < 0.0f) {
            float p = java.lang.Math.abs(da) / java.lang.Math.abs(db - da);
            float dx = ((bX - aX) * (p) + (aX)), dy = ((bY - aY) * (p) + (aY)), dz = ((bZ - aZ) * (p) + (aZ));
            if (da < 0.0f) { aX = dx; aY = dy; aZ = dz; }
            else           { bX = dx; bY = dy; bZ = dz; }
        }
        da = ((pyX) * (aX) + (((pyY) * (aY) + (((pyZ) * (aZ) + (pyW))))));
        db = ((pyX) * (bX) + (((pyY) * (bY) + (((pyZ) * (bZ) + (pyW))))));
        if (da < 0.0f && db < 0.0f) return false;
        if (da < 0.0f || db < 0.0f) {
            float p = java.lang.Math.abs(da) / java.lang.Math.abs(db - da);
            float dx = ((bX - aX) * (p) + (aX)), dy = ((bY - aY) * (p) + (aY)), dz = ((bZ - aZ) * (p) + (aZ));
            if (da < 0.0f) { aX = dx; aY = dy; aZ = dz; }
            else           { bX = dx; bY = dy; bZ = dz; }
        }
        da = ((nzX) * (aX) + (((nzY) * (aY) + (((nzZ) * (aZ) + (nzW))))));
        db = ((nzX) * (bX) + (((nzY) * (bY) + (((nzZ) * (bZ) + (nzW))))));
        if (da < 0.0f && db < 0.0f) return false;
        if (da < 0.0f || db < 0.0f) {
            float p = java.lang.Math.abs(da) / java.lang.Math.abs(db - da);
            float dx = ((bX - aX) * (p) + (aX)), dy = ((bY - aY) * (p) + (aY)), dz = ((bZ - aZ) * (p) + (aZ));
            if (da < 0.0f) { aX = dx; aY = dy; aZ = dz; }
            else           { bX = dx; bY = dy; bZ = dz; }
        }
        da = ((pzX) * (aX) + (((pzY) * (aY) + (((pzZ) * (aZ) + (pzW))))));
        db = ((pzX) * (bX) + (((pzY) * (bY) + (((pzZ) * (bZ) + (pzW))))));
        return da >= 0.0f || db >= 0.0f;
    }

    private boolean cullsAabb_fma(FrustumPlane plane, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        switch (plane) {
            case NX: return outsidePv_fma(nxX, nxY, nxZ, nxW, minX, minY, minZ, maxX, maxY, maxZ);
            case PX: return outsidePv_fma(pxX, pxY, pxZ, pxW, minX, minY, minZ, maxX, maxY, maxZ);
            case NY: return outsidePv_fma(nyX, nyY, nyZ, nyW, minX, minY, minZ, maxX, maxY, maxZ);
            case PY: return outsidePv_fma(pyX, pyY, pyZ, pyW, minX, minY, minZ, maxX, maxY, maxZ);
            case NZ: return outsidePv_fma(nzX, nzY, nzZ, nzW, minX, minY, minZ, maxX, maxY, maxZ);
            case PZ: return outsidePv_fma(pzX, pzY, pzZ, pzW, minX, minY, minZ, maxX, maxY, maxZ);
            default: throw new IllegalArgumentException("Unknown FrustumPlane: " + plane);
        }
    }

    private boolean cullsAabb_mulAdd(FrustumPlane plane, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        switch (plane) {
            case NX: return outsidePv_mulAdd(nxX, nxY, nxZ, nxW, minX, minY, minZ, maxX, maxY, maxZ);
            case PX: return outsidePv_mulAdd(pxX, pxY, pxZ, pxW, minX, minY, minZ, maxX, maxY, maxZ);
            case NY: return outsidePv_mulAdd(nyX, nyY, nyZ, nyW, minX, minY, minZ, maxX, maxY, maxZ);
            case PY: return outsidePv_mulAdd(pyX, pyY, pyZ, pyW, minX, minY, minZ, maxX, maxY, maxZ);
            case NZ: return outsidePv_mulAdd(nzX, nzY, nzZ, nzW, minX, minY, minZ, maxX, maxY, maxZ);
            case PZ: return outsidePv_mulAdd(pzX, pzY, pzZ, pzW, minX, minY, minZ, maxX, maxY, maxZ);
            default: throw new IllegalArgumentException("Unknown FrustumPlane: " + plane);
        }
    }
}
