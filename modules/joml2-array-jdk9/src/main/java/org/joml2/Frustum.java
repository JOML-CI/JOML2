// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

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
     * argument as inside.
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
        return set(m, true, false);
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
        return set(m, allowTestSpheres, false);
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
        float invl;
        nxX = m.m30() + m.m00(); nxY = m.m31() + m.m01(); nxZ = m.m32() + m.m02(); nxW = m.m33() + m.m03();
        if (allowTestSpheres) {
            invl = invLength(nxX, nxY, nxZ);
            nxX *= invl; nxY *= invl; nxZ *= invl; nxW *= invl;
        }
        pxX = m.m30() - m.m00(); pxY = m.m31() - m.m01(); pxZ = m.m32() - m.m02(); pxW = m.m33() - m.m03();
        if (allowTestSpheres) {
            invl = invLength(pxX, pxY, pxZ);
            pxX *= invl; pxY *= invl; pxZ *= invl; pxW *= invl;
        }
        nyX = m.m30() + m.m10(); nyY = m.m31() + m.m11(); nyZ = m.m32() + m.m12(); nyW = m.m33() + m.m13();
        if (allowTestSpheres) {
            invl = invLength(nyX, nyY, nyZ);
            nyX *= invl; nyY *= invl; nyZ *= invl; nyW *= invl;
        }
        pyX = m.m30() - m.m10(); pyY = m.m31() - m.m11(); pyZ = m.m32() - m.m12(); pyW = m.m33() - m.m13();
        if (allowTestSpheres) {
            invl = invLength(pyX, pyY, pyZ);
            pyX *= invl; pyY *= invl; pyZ *= invl; pyW *= invl;
        }
        if (zeroBasedDepth) {
            nzX = m.m20(); nzY = m.m21(); nzZ = m.m22(); nzW = m.m23();
        } else {
            nzX = m.m30() + m.m20(); nzY = m.m31() + m.m21(); nzZ = m.m32() + m.m22(); nzW = m.m33() + m.m23();
        }
        if (allowTestSpheres) {
            invl = invLength(nzX, nzY, nzZ);
            nzX *= invl; nzY *= invl; nzZ *= invl; nzW *= invl;
        }
        pzX = m.m30() - m.m20(); pzY = m.m31() - m.m21(); pzZ = m.m32() - m.m22(); pzW = m.m33() - m.m23();
        if (allowTestSpheres) {
            invl = invLength(pzX, pzY, pzZ);
            pzX *= invl; pzY *= invl; pzZ *= invl; pzW *= invl;
        }
        nxAX = Math.abs(nxX); nxAY = Math.abs(nxY); nxAZ = Math.abs(nxZ);
        pxAX = Math.abs(pxX); pxAY = Math.abs(pxY); pxAZ = Math.abs(pxZ);
        nyAX = Math.abs(nyX); nyAY = Math.abs(nyY); nyAZ = Math.abs(nyZ);
        pyAX = Math.abs(pyX); pyAY = Math.abs(pyY); pyAZ = Math.abs(pyZ);
        nzAX = Math.abs(nzX); nzAY = Math.abs(nzY); nzAZ = Math.abs(nzZ);
        pzAX = Math.abs(pzX); pzAY = Math.abs(pzY); pzAZ = Math.abs(pzZ);
        return this;
    }

    /**
     * 1 / |(x, y, z)|, and 1 for the zero vector: an infinite far (or near) plane
     * extracts as (0, 0, 0, w), holds every point and has no direction to normalize.
     * The squared length leaves float's range for |(x, y, z)| beyond ~1.8e19 or below
     * ~1e-19 (an ortho projection a few 1e-20 wide or 1e20 across); only then the
     * vector is first scaled by its largest component.
     */
    private static float invLength(float x, float y, float z) {
        float lenSq = Math.fma(x, x, Math.fma(y, y, z * z));
        if (lenSq >= Float.MIN_NORMAL && lenSq < Float.POSITIVE_INFINITY)
            return 1.0f / (float) Math.sqrt(lenSq);
        float s = Math.max(Math.abs(x), Math.max(Math.abs(y), Math.abs(z)));
        if (s == 0.0f) return 1.0f;
        x /= s; y /= s; z /= s;
        return 1.0f / (s * (float) Math.sqrt(Math.fma(x, x, Math.fma(y, y, z * z))));
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
     * The w coefficient (signed distance from origin) of the left (-X) plane.
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
     * The w coefficient (signed distance from origin) of the right (+X) plane.
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
     * The w coefficient (signed distance from origin) of the bottom (-Y) plane.
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
     * The w coefficient (signed distance from origin) of the top (+Y) plane.
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
     * The w coefficient (signed distance from origin) of the near (-Z) plane.
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
     * The w coefficient (signed distance from origin) of the far (+Z) plane.
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
        return testPoint(point.x(), point.y(), point.z());
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
        return Math.fma(nxX, x, Math.fma(nxY, y, Math.fma(nxZ, z, nxW))) >= 0 &&
               Math.fma(pxX, x, Math.fma(pxY, y, Math.fma(pxZ, z, pxW))) >= 0 &&
               Math.fma(nyX, x, Math.fma(nyY, y, Math.fma(nyZ, z, nyW))) >= 0 &&
               Math.fma(pyX, x, Math.fma(pyY, y, Math.fma(pyZ, z, pyW))) >= 0 &&
               Math.fma(nzX, x, Math.fma(nzY, y, Math.fma(nzZ, z, nzW))) >= 0 &&
               Math.fma(pzX, x, Math.fma(pzY, y, Math.fma(pzZ, z, pzW))) >= 0;
    }

    /**
     * Test whether the sphere around {@code center} with radius {@code radius} is
     * partly or completely inside the frustum.
     * <p>
     * Valid input: {@code radius} must not be negative.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @return <code>true</code> iff the sphere is partly or completely inside the frustum
     */
    public boolean testSphere(Float3R center, float radius) {
        return testSphere(center.x(), center.y(), center.z(), radius);
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
     * @return <code>true</code> iff the sphere is partly or completely inside the frustum
     */
    public boolean testSphere(float x, float y, float z, float r) {
        return Math.fma(nxX, x, Math.fma(nxY, y, Math.fma(nxZ, z, nxW))) >= -r &&
               Math.fma(pxX, x, Math.fma(pxY, y, Math.fma(pxZ, z, pxW))) >= -r &&
               Math.fma(nyX, x, Math.fma(nyY, y, Math.fma(nyZ, z, nyW))) >= -r &&
               Math.fma(pyX, x, Math.fma(pyY, y, Math.fma(pyZ, z, pyW))) >= -r &&
               Math.fma(nzX, x, Math.fma(nzY, y, Math.fma(nzZ, z, nzW))) >= -r &&
               Math.fma(pzX, x, Math.fma(pzY, y, Math.fma(pzZ, z, pzW))) >= -r;
    }

    /**
     * Test whether {@code sphere} is partly or completely inside the frustum.
     * <p>
     * Valid input: the radius of {@code sphere} must not be negative.
     *
     * @param sphere the sphere to test
     * @return <code>true</code> iff the sphere is partly or completely inside the frustum
     */
    public boolean testSphere(FloatSphereR sphere) {
        return testSphere(sphere.x(), sphere.y(), sphere.z(), sphere.r());
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
        return intersectSphere(center.x(), center.y(), center.z(), radius);
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
        float dist = Math.fma(nxX, x, Math.fma(nxY, y, Math.fma(nxZ, z, nxW)));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        boolean inside = dist >= r;
        dist = Math.fma(pxX, x, Math.fma(pxY, y, Math.fma(pxZ, z, pxW)));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        inside &= dist >= r;
        dist = Math.fma(nyX, x, Math.fma(nyY, y, Math.fma(nyZ, z, nyW)));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        inside &= dist >= r;
        return intersectSphereYZ(x, y, z, r, inside);
    }

    /** Second half of {@link #intersectSphere(float, float, float, float)}: the py, nz and pz planes. */
    private Intersection intersectSphereYZ(float x, float y, float z, float r, boolean inside) {
        float dist = Math.fma(pyX, x, Math.fma(pyY, y, Math.fma(pyZ, z, pyW)));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        inside &= dist >= r;
        dist = Math.fma(nzX, x, Math.fma(nzY, y, Math.fma(nzZ, z, nzW)));
        if (!(dist >= -r)) return Intersection.OUTSIDE;
        inside &= dist >= r;
        dist = Math.fma(pzX, x, Math.fma(pzY, y, Math.fma(pzZ, z, pzW)));
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
        return intersectSphere(sphere.x(), sphere.y(), sphere.z(), sphere.r());
    }

    /**
     * Test whether the axis-aligned box spanned by {@code min} and {@code max} is
     * partly or completely inside the frustum.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component; {@code min} may be infinite; {@code max} may be infinite.
     *
     * @param min the corner of the box with the smaller coordinates
     * @param max the corner of the box with the larger coordinates
     * @return <code>true</code> iff the box is partly or completely inside the frustum
     */
    public boolean testAabb(Float3R min, Float3R max) {
        return testAabb(min.x(), min.y(), min.z(), max.x(), max.y(), max.z());
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
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any component; {@code (minX, minY, minZ)} may be infinite; {@code (maxX, maxY, maxZ)} may be infinite.
     *
     * @param minX the x coordinate of the corner with the smaller coordinates
     * @param minY the y coordinate of the corner with the smaller coordinates
     * @param minZ the z coordinate of the corner with the smaller coordinates
     * @param maxX the x coordinate of the corner with the larger coordinates
     * @param maxY the y coordinate of the corner with the larger coordinates
     * @param maxZ the z coordinate of the corner with the larger coordinates
     * @return <code>true</code> iff the box is partly or completely inside the frustum
     */
    public boolean testAabb(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return pv(nxX, nxY, nxZ, nxW, minX, minY, minZ, maxX, maxY, maxZ) && pv(pxX, pxY, pxZ, pxW, minX, minY, minZ, maxX, maxY, maxZ)
            && pv(nyX, nyY, nyZ, nyW, minX, minY, minZ, maxX, maxY, maxZ) && pv(pyX, pyY, pyZ, pyW, minX, minY, minZ, maxX, maxY, maxZ)
            && pv(nzX, nzY, nzZ, nzW, minX, minY, minZ, maxX, maxY, maxZ) && pv(pzX, pzY, pzZ, pzW, minX, minY, minZ, maxX, maxY, maxZ)
            && minX <= maxX && minY <= maxY && minZ <= maxZ;
    }

    // The box tests are written as one call per plane so that each public method stays
    // under HotSpot's 325-byte FreqInlineSize: as single bodies (430-1000 bytes) they never
    // inlined into a culling loop, which cost 25% per box. Each helper is one plane's
    // p-vertex or n-vertex test with the original operand order, bit for bit.

    // Early-exit tests (testAabb, cullingPlane) keep the p-vertex select per plane: the plane
    // that rejects most boxes runs before anything else is computed. Measured against the
    // centre/half-extent form below: 3% faster here, 20% slower for intersectAabb.

    /**
     * The box vertex farthest along the plane normal is on the inner side: the box is not wholly
     * outside this plane. A NaN distance - 0 * infinity, a zero plane coefficient times an
     * infinite bound - counts as inside, so an unbounded box is never culled wrongly.
     */
    private static boolean pv(float a, float b, float c, float w, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return !(Math.fma(a, a < 0 ? minX : maxX, Math.fma(b, b < 0 ? minY : maxY, c * (c < 0 ? minZ : maxZ))) < -w);
    }

    /** The box vertex farthest along the plane normal is strictly outside this plane: exactly the negation of {@link #pv}. */
    private static boolean outsidePv(float a, float b, float c, float w, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return Math.fma(a, a < 0 ? minX : maxX, Math.fma(b, b < 0 ? minY : maxY, c * (c < 0 ? minZ : maxZ))) < -w;
    }

    // intersectAabb needs both the farthest and the nearest box vertex per plane. As (centre c,
    // half-extent e) those are dot(n, c) + dot(|n|, e) and dot(n, c) - dot(|n|, e): one fma chain
    // each and no per-component select, with |n| kept by set(). Same verdicts as the p/n-vertex
    // selects up to rounding, which can decide a box touching a plane exactly the other way;
    // measured 20% faster for intersectAabb. c and e are formed from the halved corners, which is
    // bit-identical to halving the sum and difference and cannot overflow for huge boxes, and
    // OUTSIDE is !(far >= -w) so that NaN is culled exactly like testAabb and cullingPlane do. One tiny helper per plane and
    // side (no plane index, no switch) keeps every helper and every public box test under
    // HotSpot's 325-byte inline budget; a switch-dispatched pair of helpers sat at 360 bytes,
    // stopped inlining and doubled the cost of every box test.
    private float farNx(float cx, float cy, float cz, float ex, float ey, float ez) { return Math.fma(nxX, cx, Math.fma(nxY, cy, Math.fma(nxZ, cz, Math.fma(nxAX, ex, Math.fma(nxAY, ey, nxAZ * ez))))); }
    private float nearNx(float cx, float cy, float cz, float ex, float ey, float ez) { return Math.fma(nxX, cx, Math.fma(nxY, cy, Math.fma(nxZ, cz, -Math.fma(nxAX, ex, Math.fma(nxAY, ey, nxAZ * ez))))); }
    private float farPx(float cx, float cy, float cz, float ex, float ey, float ez) { return Math.fma(pxX, cx, Math.fma(pxY, cy, Math.fma(pxZ, cz, Math.fma(pxAX, ex, Math.fma(pxAY, ey, pxAZ * ez))))); }
    private float nearPx(float cx, float cy, float cz, float ex, float ey, float ez) { return Math.fma(pxX, cx, Math.fma(pxY, cy, Math.fma(pxZ, cz, -Math.fma(pxAX, ex, Math.fma(pxAY, ey, pxAZ * ez))))); }
    private float farNy(float cx, float cy, float cz, float ex, float ey, float ez) { return Math.fma(nyX, cx, Math.fma(nyY, cy, Math.fma(nyZ, cz, Math.fma(nyAX, ex, Math.fma(nyAY, ey, nyAZ * ez))))); }
    private float nearNy(float cx, float cy, float cz, float ex, float ey, float ez) { return Math.fma(nyX, cx, Math.fma(nyY, cy, Math.fma(nyZ, cz, -Math.fma(nyAX, ex, Math.fma(nyAY, ey, nyAZ * ez))))); }
    private float farPy(float cx, float cy, float cz, float ex, float ey, float ez) { return Math.fma(pyX, cx, Math.fma(pyY, cy, Math.fma(pyZ, cz, Math.fma(pyAX, ex, Math.fma(pyAY, ey, pyAZ * ez))))); }
    private float nearPy(float cx, float cy, float cz, float ex, float ey, float ez) { return Math.fma(pyX, cx, Math.fma(pyY, cy, Math.fma(pyZ, cz, -Math.fma(pyAX, ex, Math.fma(pyAY, ey, pyAZ * ez))))); }
    private float farNz(float cx, float cy, float cz, float ex, float ey, float ez) { return Math.fma(nzX, cx, Math.fma(nzY, cy, Math.fma(nzZ, cz, Math.fma(nzAX, ex, Math.fma(nzAY, ey, nzAZ * ez))))); }
    private float nearNz(float cx, float cy, float cz, float ex, float ey, float ez) { return Math.fma(nzX, cx, Math.fma(nzY, cy, Math.fma(nzZ, cz, -Math.fma(nzAX, ex, Math.fma(nzAY, ey, nzAZ * ez))))); }
    private float farPz(float cx, float cy, float cz, float ex, float ey, float ez) { return Math.fma(pzX, cx, Math.fma(pzY, cy, Math.fma(pzZ, cz, Math.fma(pzAX, ex, Math.fma(pzAY, ey, pzAZ * ez))))); }
    private float nearPz(float cx, float cy, float cz, float ex, float ey, float ez) { return Math.fma(pzX, cx, Math.fma(pzY, cy, Math.fma(pzZ, cz, -Math.fma(pzAX, ex, Math.fma(pzAY, ey, pzAZ * ez))))); }

    /**
     * Test whether {@code aabb} is partly or completely inside the frustum.
     * <p>
     * Valid input: the minimum corner of {@code aabb} must not exceed the maximum corner of {@code aabb} in any component; the minimum corner of {@code aabb} may be infinite; the maximum corner of {@code aabb} may be infinite.
     *
     * @param aabb the axis-aligned box to test
     * @return <code>true</code> iff the box is partly or completely inside the frustum
     */
    public boolean testAabb(FloatAABBR aabb) {
        return testAabb(aabb.minX(), aabb.minY(), aabb.minZ(),
                       aabb.maxX(), aabb.maxY(), aabb.maxZ());
    }

    /**
     * Test whether the rectangle spanned by {@code min} and {@code max} in the XY
     * plane at <code>z = 0</code> is partly or completely within the frustum.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the corner of the rectangle with the smaller coordinates
     * @param max the corner of the rectangle with the larger coordinates
     * @return <code>true</code> iff the rectangle is partly or completely within the frustum
     */
    public boolean testPlaneXY(Float2R min, Float2R max) {
        return testPlaneXY(min.x(), min.y(), max.x(), max.y());
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
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component.
     *
     * @param minX the x coordinate of the corner with the smaller coordinates
     * @param minY the y coordinate of the corner with the smaller coordinates
     * @param maxX the x coordinate of the corner with the larger coordinates
     * @param maxY the y coordinate of the corner with the larger coordinates
     * @return <code>true</code> iff the rectangle is partly or completely within the frustum
     */
    public boolean testPlaneXY(float minX, float minY, float maxX, float maxY) {
        return !(Math.fma(nxX, nxX < 0 ? minX : maxX, nxY * (nxY < 0 ? minY : maxY)) < -nxW) &&
               !(Math.fma(pxX, pxX < 0 ? minX : maxX, pxY * (pxY < 0 ? minY : maxY)) < -pxW) &&
               !(Math.fma(nyX, nyX < 0 ? minX : maxX, nyY * (nyY < 0 ? minY : maxY)) < -nyW) &&
               !(Math.fma(pyX, pyX < 0 ? minX : maxX, pyY * (pyY < 0 ? minY : maxY)) < -pyW) &&
               !(Math.fma(nzX, nzX < 0 ? minX : maxX, nzY * (nzY < 0 ? minY : maxY)) < -nzW) &&
               !(Math.fma(pzX, pzX < 0 ? minX : maxX, pzY * (pzY < 0 ? minY : maxY)) < -pzW) &&
               minX <= maxX && minY <= maxY;
    }

    /**
     * Test whether the given rectangle, lying in the XY plane at <code>z = 0</code>,
     * is partly or completely within the frustum. The rect's x/y axes map to
     * world X/Y. The z term of each plane drops out because the rectangle's
     * <code>z</code> is zero - this is <em>not</em> a test of the rectangle
     * extended infinitely along Z.
     * <p>
     * Valid input: the minimum corner of {@code rect} must not exceed the maximum corner of {@code rect} in any component.
     *
     * @param rect the rectangle to test
     * @return <code>true</code> iff the rectangle is partly or completely within the frustum
     */
    public boolean testPlaneXY(FloatRectR rect) {
        return testPlaneXY(rect.minX(), rect.minY(), rect.maxX(), rect.maxY());
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
     * Valid input: {@code (minX, minZ)} must not exceed {@code (maxX, maxZ)} in any component.
     *
     * @param minX the x coordinate of the corner with the smaller coordinates
     * @param minZ the z coordinate of the corner with the smaller coordinates
     * @param maxX the x coordinate of the corner with the larger coordinates
     * @param maxZ the z coordinate of the corner with the larger coordinates
     * @return <code>true</code> iff the rectangle is partly or completely within the frustum
     */
    public boolean testPlaneXZ(float minX, float minZ, float maxX, float maxZ) {
        return !(Math.fma(nxX, nxX < 0 ? minX : maxX, nxZ * (nxZ < 0 ? minZ : maxZ)) < -nxW) &&
               !(Math.fma(pxX, pxX < 0 ? minX : maxX, pxZ * (pxZ < 0 ? minZ : maxZ)) < -pxW) &&
               !(Math.fma(nyX, nyX < 0 ? minX : maxX, nyZ * (nyZ < 0 ? minZ : maxZ)) < -nyW) &&
               !(Math.fma(pyX, pyX < 0 ? minX : maxX, pyZ * (pyZ < 0 ? minZ : maxZ)) < -pyW) &&
               !(Math.fma(nzX, nzX < 0 ? minX : maxX, nzZ * (nzZ < 0 ? minZ : maxZ)) < -nzW) &&
               !(Math.fma(pzX, pzX < 0 ? minX : maxX, pzZ * (pzZ < 0 ? minZ : maxZ)) < -pzW) &&
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
     * Valid input: the minimum corner of {@code rect} must not exceed the maximum corner of {@code rect} in any component.
     *
     * @param rect the rectangle to test, with its y-axis read as world Z
     * @return <code>true</code> iff the rectangle is partly or completely within the frustum
     */
    public boolean testPlaneXZ(FloatRectR rect) {
        return testPlaneXZ(rect.minX(), rect.minY(), rect.maxX(), rect.maxY());
    }

    /**
     * Determine how the axis-aligned box spanned by {@code min} and {@code max}
     * relates to the frustum.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component; {@code min} may be infinite; {@code max} may be infinite.
     *
     * @param min the corner of the box with the smaller coordinates
     * @param max the corner of the box with the larger coordinates
     * @return how the box relates to the frustum
     */
    public Intersection intersectAabb(Float3R min, Float3R max) {
        return intersectAabb(min.x(), min.y(), min.z(), max.x(), max.y(), max.z());
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
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any component; {@code (minX, minY, minZ)} may be infinite; {@code (maxX, maxY, maxZ)} may be infinite.
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
        float cx = minX * 0.5f + maxX * 0.5f, cy = minY * 0.5f + maxY * 0.5f, cz = minZ * 0.5f + maxZ * 0.5f;
        float ex = maxX * 0.5f - minX * 0.5f, ey = maxY * 0.5f - minY * 0.5f, ez = maxZ * 0.5f - minZ * 0.5f;
        if (farNx(cx, cy, cz, ex, ey, ez) < -nxW) return Intersection.OUTSIDE;
        boolean inside = nearNx(cx, cy, cz, ex, ey, ez) >= -nxW;
        if (farPx(cx, cy, cz, ex, ey, ez) < -pxW) return Intersection.OUTSIDE;
        inside &= nearPx(cx, cy, cz, ex, ey, ez) >= -pxW;
        if (farNy(cx, cy, cz, ex, ey, ez) < -nyW) return Intersection.OUTSIDE;
        inside &= nearNy(cx, cy, cz, ex, ey, ez) >= -nyW;
        return intersectAabbYZ(cx, cy, cz, ex, ey, ez, inside);
    }

    /** Second half of {@link #intersectAabb(float, float, float, float, float, float)}: the py, nz and pz planes. */
    private Intersection intersectAabbYZ(float cx, float cy, float cz, float ex, float ey, float ez, boolean inside) {
        if (farPy(cx, cy, cz, ex, ey, ez) < -pyW) return Intersection.OUTSIDE;
        inside &= nearPy(cx, cy, cz, ex, ey, ez) >= -pyW;
        if (farNz(cx, cy, cz, ex, ey, ez) < -nzW) return Intersection.OUTSIDE;
        inside &= nearNz(cx, cy, cz, ex, ey, ez) >= -nzW;
        if (farPz(cx, cy, cz, ex, ey, ez) < -pzW) return Intersection.OUTSIDE;
        inside &= nearPz(cx, cy, cz, ex, ey, ez) >= -pzW;
        // the empty box (min +infinity, max -infinity) passes the planes whose far distance is 0 * infinity
        if (!(ex >= 0 && ey >= 0 && ez >= 0)) return Intersection.OUTSIDE;
        return inside ? Intersection.INSIDE : Intersection.INTERSECT;
    }

    /**
     * Determine how {@code aabb} relates to the frustum.
     * <p>
     * Valid input: the minimum corner of {@code aabb} must not exceed the maximum corner of {@code aabb} in any component; the minimum corner of {@code aabb} may be infinite; the maximum corner of {@code aabb} may be infinite.
     *
     * @param aabb the axis-aligned box to test
     * @return how the box relates to the frustum
     */
    public Intersection intersectAabb(FloatAABBR aabb) {
        return intersectAabb(aabb.minX(), aabb.minY(), aabb.minZ(),
                            aabb.maxX(), aabb.maxY(), aabb.maxZ());
    }

    /**
     * Determine how the axis-aligned box spanned by {@code min} and {@code max}
     * relates to the frustum, letting only the planes selected by {@code mask} cull it.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component; {@code min} may be infinite; {@code max} may be infinite.
     *
     * @param min the corner of the box with the smaller coordinates
     * @param max the corner of the box with the larger coordinates
     * @param mask a bit mask of the <code>PLANE_MASK_*</code> constants selecting the
     *        planes that may cull the box
     * @return how the box relates to the frustum
     */
    public Intersection intersectAabb(Float3R min, Float3R max, int mask) {
        return intersectAabb(min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), mask);
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
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any component; {@code (minX, minY, minZ)} may be infinite; {@code (maxX, maxY, maxZ)} may be infinite.
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
        float cx = minX * 0.5f + maxX * 0.5f, cy = minY * 0.5f + maxY * 0.5f, cz = minZ * 0.5f + maxZ * 0.5f;
        float ex = maxX * 0.5f - minX * 0.5f, ey = maxY * 0.5f - minY * 0.5f, ez = maxZ * 0.5f - minZ * 0.5f;
        if ((mask & PLANE_MASK_NX) != 0 && farNx(cx, cy, cz, ex, ey, ez) < -nxW) return Intersection.OUTSIDE;
        boolean inside = nearNx(cx, cy, cz, ex, ey, ez) >= -nxW;
        if ((mask & PLANE_MASK_PX) != 0 && farPx(cx, cy, cz, ex, ey, ez) < -pxW) return Intersection.OUTSIDE;
        inside &= nearPx(cx, cy, cz, ex, ey, ez) >= -pxW;
        if ((mask & PLANE_MASK_NY) != 0 && farNy(cx, cy, cz, ex, ey, ez) < -nyW) return Intersection.OUTSIDE;
        inside &= nearNy(cx, cy, cz, ex, ey, ez) >= -nyW;
        return intersectAabbYZ(cx, cy, cz, ex, ey, ez, mask, inside);
    }

    /** Second half of {@link #intersectAabb(float, float, float, float, float, float, int)}: the py, nz and pz planes. */
    private Intersection intersectAabbYZ(float cx, float cy, float cz, float ex, float ey, float ez, int mask, boolean inside) {
        if ((mask & PLANE_MASK_PY) != 0 && farPy(cx, cy, cz, ex, ey, ez) < -pyW) return Intersection.OUTSIDE;
        inside &= nearPy(cx, cy, cz, ex, ey, ez) >= -pyW;
        if ((mask & PLANE_MASK_NZ) != 0 && farNz(cx, cy, cz, ex, ey, ez) < -nzW) return Intersection.OUTSIDE;
        inside &= nearNz(cx, cy, cz, ex, ey, ez) >= -nzW;
        if ((mask & PLANE_MASK_PZ) != 0 && farPz(cx, cy, cz, ex, ey, ez) < -pzW) return Intersection.OUTSIDE;
        inside &= nearPz(cx, cy, cz, ex, ey, ez) >= -pzW;
        // the empty box (min +infinity, max -infinity) passes the planes whose far distance is 0 * infinity
        if (!(ex >= 0 && ey >= 0 && ez >= 0)) return Intersection.OUTSIDE;
        return inside ? Intersection.INSIDE : Intersection.INTERSECT;
    }

    /**
     * Determine how {@code aabb} relates to the frustum, letting only the planes
     * selected by {@code mask} cull it.
     * <p>
     * Valid input: the minimum corner of {@code aabb} must not exceed the maximum corner of {@code aabb} in any component; the minimum corner of {@code aabb} may be infinite; the maximum corner of {@code aabb} may be infinite.
     *
     * @param aabb the axis-aligned box to test
     * @param mask a bit mask of the <code>PLANE_MASK_*</code> constants selecting the
     *        planes that may cull the box
     * @return how the box relates to the frustum
     */
    public Intersection intersectAabb(FloatAABBR aabb, int mask) {
        return intersectAabb(aabb.minX(), aabb.minY(), aabb.minZ(),
                            aabb.maxX(), aabb.maxY(), aabb.maxZ(), mask);
    }

    /**
     * Determine which frustum plane culls the axis-aligned box spanned by {@code min}
     * and {@code max}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component; {@code min} may be infinite; {@code max} may be infinite.
     *
     * @param min the corner of the box with the smaller coordinates
     * @param max the corner of the box with the larger coordinates
     * @return the plane that culls the box, or <code>null</code> if none does
     */
    public FrustumPlane cullingPlane(Float3R min, Float3R max) {
        return cullingPlane(min.x(), min.y(), min.z(), max.x(), max.y(), max.z());
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
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any component; {@code (minX, minY, minZ)} may be infinite; {@code (maxX, maxY, maxZ)} may be infinite.
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
        if (outsidePv(nxX, nxY, nxZ, nxW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NX;
        if (outsidePv(pxX, pxY, pxZ, pxW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PX;
        if (outsidePv(nyX, nyY, nyZ, nyW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NY;
        if (outsidePv(pyX, pyY, pyZ, pyW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PY;
        if (outsidePv(nzX, nzY, nzZ, nzW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NZ;
        if (outsidePv(pzX, pzY, pzZ, pzW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PZ;
        // the empty box passes the planes whose p-vertex distance is 0 * infinity: every plane culls it
        return minX <= maxX && minY <= maxY && minZ <= maxZ ? null : FrustumPlane.NX;
    }

    /**
     * Determine which frustum plane culls {@code aabb}.
     * <p>
     * Valid input: the minimum corner of {@code aabb} must not exceed the maximum corner of {@code aabb} in any component; the minimum corner of {@code aabb} may be infinite; the maximum corner of {@code aabb} may be infinite.
     *
     * @param aabb the axis-aligned box to test
     * @return the plane that culls the box, or <code>null</code> if none does
     */
    public FrustumPlane cullingPlane(FloatAABBR aabb) {
        return cullingPlane(aabb.minX(), aabb.minY(), aabb.minZ(),
                            aabb.maxX(), aabb.maxY(), aabb.maxZ());
    }

    /**
     * Determine which of the planes selected by {@code mask} culls the axis-aligned box
     * spanned by {@code min} and {@code max}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component; {@code min} may be infinite; {@code max} may be infinite.
     *
     * @param min the corner of the box with the smaller coordinates
     * @param max the corner of the box with the larger coordinates
     * @param mask a bit mask of the <code>PLANE_MASK_*</code> constants selecting the
     *        planes that may cull the box
     * @return the plane that culls the box, or <code>null</code> if none does
     */
    public FrustumPlane cullingPlane(Float3R min, Float3R max, int mask) {
        return cullingPlane(min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), mask);
    }

    /**
     * Determine which of the planes selected by {@code mask} culls the axis-aligned box
     * spanned by <code>(minX, minY, minZ)</code> and <code>(maxX, maxY, maxZ)</code>.
     * <p>
     * Masking a plane off is the standard optimization for a bounding-volume
     * hierarchy: once a parent node is known to be fully inside a plane, none of its
     * children can be culled by that plane either.
     * <p>
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any component; {@code (minX, minY, minZ)} may be infinite; {@code (maxX, maxY, maxZ)} may be infinite.
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
        if ((mask & PLANE_MASK_NX) != 0 && outsidePv(nxX, nxY, nxZ, nxW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NX;
        if ((mask & PLANE_MASK_PX) != 0 && outsidePv(pxX, pxY, pxZ, pxW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PX;
        if ((mask & PLANE_MASK_NY) != 0 && outsidePv(nyX, nyY, nyZ, nyW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NY;
        if ((mask & PLANE_MASK_PY) != 0 && outsidePv(pyX, pyY, pyZ, pyW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PY;
        if ((mask & PLANE_MASK_NZ) != 0 && outsidePv(nzX, nzY, nzZ, nzW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.NZ;
        if ((mask & PLANE_MASK_PZ) != 0 && outsidePv(pzX, pzY, pzZ, pzW, minX, minY, minZ, maxX, maxY, maxZ)) return FrustumPlane.PZ;
        // the empty box: every selected plane culls it, report the first
        return minX <= maxX && minY <= maxY && minZ <= maxZ || (mask & 63) == 0 ? null
            : FrustumPlane.values()[Integer.numberOfTrailingZeros(mask & 63)];
    }

    /**
     * Determine which of the planes selected by {@code mask} culls {@code aabb}.
     * <p>
     * Valid input: the minimum corner of {@code aabb} must not exceed the maximum corner of {@code aabb} in any component; the minimum corner of {@code aabb} may be infinite; the maximum corner of {@code aabb} may be infinite.
     *
     * @param aabb the axis-aligned box to test
     * @param mask a bit mask of the <code>PLANE_MASK_*</code> constants selecting the
     *        planes that may cull the box
     * @return the plane that culls the box, or <code>null</code> if none does
     */
    public FrustumPlane cullingPlane(FloatAABBR aabb, int mask) {
        return cullingPlane(aabb.minX(), aabb.minY(), aabb.minZ(),
                            aabb.maxX(), aabb.maxY(), aabb.maxZ(), mask);
    }

    /**
     * Determine which of the planes selected by {@code mask} culls the axis-aligned box
     * spanned by {@code min} and {@code max}, trying {@code hint} first.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component; {@code min} may be infinite; {@code max} may be infinite.
     *
     * @param min the corner of the box with the smaller coordinates
     * @param max the corner of the box with the larger coordinates
     * @param mask a bit mask of the <code>PLANE_MASK_*</code> constants selecting the
     *        planes that may cull the box
     * @param hint the plane to test first, or <code>null</code> for no hint
     * @return the plane that culls the box, or <code>null</code> if none does
     */
    public FrustumPlane cullingPlane(Float3R min, Float3R max, int mask, FrustumPlane hint) {
        return cullingPlane(min.x(), min.y(), min.z(), max.x(), max.y(), max.z(), mask, hint);
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
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any component; {@code (minX, minY, minZ)} may be infinite; {@code (maxX, maxY, maxZ)} may be infinite.
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
        // Coherency check: if hint culled previously, it likely culls again.
        if (hint != null && (mask & (1 << hint.ordinal())) != 0
                && cullsAabb(hint, minX, minY, minZ, maxX, maxY, maxZ))
            return hint;
        return cullingPlane(minX, minY, minZ, maxX, maxY, maxZ, mask);
    }

    /**
     * Determine which of the planes selected by {@code mask} culls {@code aabb}, trying
     * {@code hint} first.
     * <p>
     * Valid input: the minimum corner of {@code aabb} must not exceed the maximum corner of {@code aabb} in any component; the minimum corner of {@code aabb} may be infinite; the maximum corner of {@code aabb} may be infinite.
     *
     * @param aabb the axis-aligned box to test
     * @param mask a bit mask of the <code>PLANE_MASK_*</code> constants selecting the
     *        planes that may cull the box
     * @param hint the plane to test first, or <code>null</code> for no hint
     * @return the plane that culls the box, or <code>null</code> if none does
     */
    public FrustumPlane cullingPlane(FloatAABBR aabb, int mask, FrustumPlane hint) {
        return cullingPlane(aabb.minX(), aabb.minY(), aabb.minZ(),
                            aabb.maxX(), aabb.maxY(), aabb.maxZ(), mask, hint);
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
        switch (plane) {
            case NX: return nvDist(nxX, nxY, nxZ, nxW, minX, minY, minZ, maxX, maxY, maxZ);
            case PX: return nvDist(pxX, pxY, pxZ, pxW, minX, minY, minZ, maxX, maxY, maxZ);
            case NY: return nvDist(nyX, nyY, nyZ, nyW, minX, minY, minZ, maxX, maxY, maxZ);
            case PY: return nvDist(pyX, pyY, pyZ, pyW, minX, minY, minZ, maxX, maxY, maxZ);
            case NZ: return nvDist(nzX, nzY, nzZ, nzW, minX, minY, minZ, maxX, maxY, maxZ);
            case PZ: return nvDist(pzX, pzY, pzZ, pzW, minX, minY, minZ, maxX, maxY, maxZ);
            default: throw new IllegalArgumentException("Unknown FrustumPlane: " + plane);
        }
    }

    /** Signed distance of the box vertex nearest along the plane normal from the plane. */
    private static float nvDist(float a, float b, float c, float w, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return Math.fma(a, a < 0 ? maxX : minX, Math.fma(b, b < 0 ? maxY : minY, Math.fma(c, c < 0 ? maxZ : minZ, w)));
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
        return testLineSegment(a.x(), a.y(), a.z(), b.x(), b.y(), b.z());
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
        // Each plane cuts the segment back to its inside part whenever one endpoint is
        // outside - also when the other lies exactly on the plane (da * db would be
        // -0.0 there and skip the cut, letting the outside endpoint reach the later
        // planes' tests). The both-outside case has already returned.
        float da, db;
        da = Math.fma(nxX, aX, Math.fma(nxY, aY, Math.fma(nxZ, aZ, nxW)));
        db = Math.fma(nxX, bX, Math.fma(nxY, bY, Math.fma(nxZ, bZ, nxW)));
        if (da < 0.0f && db < 0.0f) return false;
        if (da < 0.0f || db < 0.0f) {
            float p = Math.abs(da) / Math.abs(db - da);
            float dx = Math.fma(bX - aX, p, aX), dy = Math.fma(bY - aY, p, aY), dz = Math.fma(bZ - aZ, p, aZ);
            if (da < 0.0f) { aX = dx; aY = dy; aZ = dz; }
            else           { bX = dx; bY = dy; bZ = dz; }
        }
        da = Math.fma(pxX, aX, Math.fma(pxY, aY, Math.fma(pxZ, aZ, pxW)));
        db = Math.fma(pxX, bX, Math.fma(pxY, bY, Math.fma(pxZ, bZ, pxW)));
        if (da < 0.0f && db < 0.0f) return false;
        if (da < 0.0f || db < 0.0f) {
            float p = Math.abs(da) / Math.abs(db - da);
            float dx = Math.fma(bX - aX, p, aX), dy = Math.fma(bY - aY, p, aY), dz = Math.fma(bZ - aZ, p, aZ);
            if (da < 0.0f) { aX = dx; aY = dy; aZ = dz; }
            else           { bX = dx; bY = dy; bZ = dz; }
        }
        da = Math.fma(nyX, aX, Math.fma(nyY, aY, Math.fma(nyZ, aZ, nyW)));
        db = Math.fma(nyX, bX, Math.fma(nyY, bY, Math.fma(nyZ, bZ, nyW)));
        if (da < 0.0f && db < 0.0f) return false;
        if (da < 0.0f || db < 0.0f) {
            float p = Math.abs(da) / Math.abs(db - da);
            float dx = Math.fma(bX - aX, p, aX), dy = Math.fma(bY - aY, p, aY), dz = Math.fma(bZ - aZ, p, aZ);
            if (da < 0.0f) { aX = dx; aY = dy; aZ = dz; }
            else           { bX = dx; bY = dy; bZ = dz; }
        }
        da = Math.fma(pyX, aX, Math.fma(pyY, aY, Math.fma(pyZ, aZ, pyW)));
        db = Math.fma(pyX, bX, Math.fma(pyY, bY, Math.fma(pyZ, bZ, pyW)));
        if (da < 0.0f && db < 0.0f) return false;
        if (da < 0.0f || db < 0.0f) {
            float p = Math.abs(da) / Math.abs(db - da);
            float dx = Math.fma(bX - aX, p, aX), dy = Math.fma(bY - aY, p, aY), dz = Math.fma(bZ - aZ, p, aZ);
            if (da < 0.0f) { aX = dx; aY = dy; aZ = dz; }
            else           { bX = dx; bY = dy; bZ = dz; }
        }
        da = Math.fma(nzX, aX, Math.fma(nzY, aY, Math.fma(nzZ, aZ, nzW)));
        db = Math.fma(nzX, bX, Math.fma(nzY, bY, Math.fma(nzZ, bZ, nzW)));
        if (da < 0.0f && db < 0.0f) return false;
        if (da < 0.0f || db < 0.0f) {
            float p = Math.abs(da) / Math.abs(db - da);
            float dx = Math.fma(bX - aX, p, aX), dy = Math.fma(bY - aY, p, aY), dz = Math.fma(bZ - aZ, p, aZ);
            if (da < 0.0f) { aX = dx; aY = dy; aZ = dz; }
            else           { bX = dx; bY = dy; bZ = dz; }
        }
        da = Math.fma(pzX, aX, Math.fma(pzY, aY, Math.fma(pzZ, aZ, pzW)));
        db = Math.fma(pzX, bX, Math.fma(pzY, bY, Math.fma(pzZ, bZ, pzW)));
        return da >= 0.0f || db >= 0.0f;
    }

    private boolean cullsAabb(FrustumPlane plane, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        switch (plane) {
            case NX: return outsidePv(nxX, nxY, nxZ, nxW, minX, minY, minZ, maxX, maxY, maxZ);
            case PX: return outsidePv(pxX, pxY, pxZ, pxW, minX, minY, minZ, maxX, maxY, maxZ);
            case NY: return outsidePv(nyX, nyY, nyZ, nyW, minX, minY, minZ, maxX, maxY, maxZ);
            case PY: return outsidePv(pyX, pyY, pyZ, pyW, minX, minY, minZ, maxX, maxY, maxZ);
            case NZ: return outsidePv(nzX, nzY, nzZ, nzW, minX, minY, minZ, maxX, maxY, maxZ);
            case PZ: return outsidePv(pzX, pzY, pzZ, pzW, minX, minY, minZ, maxX, maxY, maxZ);
            default: throw new IllegalArgumentException("Unknown FrustumPlane: " + plane);
        }
    }
}
