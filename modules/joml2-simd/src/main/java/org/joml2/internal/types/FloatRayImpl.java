// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import jdk.incubator.vector.*;
import org.joml2.internal.simd.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import org.joml2.internal.unsafe.HeapSegments;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link FloatRay} backed by a {@code float[]} array, with Vector API
 * SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatRayImpl implements FloatRay {
    public float[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatRaySegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatRaySegOpsUnsafe()
            : new FloatRaySegOpsMS();
        static final FloatRayBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatRayBbOpsUnsafe()
            : new FloatRayBbOpsApi();
        static final FloatRayRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatRayRawOpsUnsafe()
            : new FloatRayRawOpsApi();
    }

    public FloatRayImpl() {
        data = new float[6];
    }

    public FloatRayImpl(float oX, float oY, float oZ, float dX, float dY, float dZ) {
        float[] dd = this.data = new float[6];
        dd[0] = oX;
        dd[1] = oY;
        dd[2] = oZ;
        dd[3] = dX;
        dd[4] = dY;
        dd[5] = dZ;
    }

    public FloatRayImpl(FloatRayR src) {
        float[] srcData = ((FloatRayImpl) src).data;
        float[] dd = this.data = new float[6];
        dd[0] = srcData[0];
        dd[1] = srcData[1];
        dd[2] = srcData[2];
        dd[3] = srcData[3];
        dd[4] = srcData[4];
        dd[5] = srcData[5];
    }

    /**
     * Set this ray to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the ray to copy
     * @return this
     */
    @Mutated public FloatRay set(FloatRayR v) {
        float[] vData = ((FloatRayImpl) v).data;
        float vOY = vData[1];
        float vOZ = vData[2];
        float vDX = vData[3];
        float vDY = vData[4];
        float vDZ = vData[5];
        float[] dd = this.data;
        dd[0] = vData[0];
        dd[1] = vOY;
        dd[2] = vOZ;
        dd[3] = vDX;
        dd[4] = vDY;
        dd[5] = vDZ;
        return this;
    }

    /**
     * Set this ray to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vOX the {@code oX} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vOY the {@code oY} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vOZ the {@code oZ} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vDX the {@code dX} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vDY the {@code dY} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vDZ the {@code dZ} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @return this
     */
    @Mutated public FloatRay set(float vOX, float vOY, float vOZ, float vDX, float vDY, float vDZ) {
        float[] dd = this.data;
        dd[0] = vOX;
        dd[1] = vOY;
        dd[2] = vOZ;
        dd[3] = vDX;
        dd[4] = vDY;
        dd[5] = vDZ;
        return this;
    }

    /**
     * Set the direction of this ray to {@code d} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code d} must be non-zero.
     *
     * @param d the new direction
     * @param dest will hold the result
     * @return dest
     */
    public FloatRay setDirection(Float3R d, @Mutated FloatRay dest) {
        float[] dData = ((Float3Impl) d).data;
        float dX = dData[0];
        float dY = dData[1];
        float dZ = dData[2];
        float[] sd = this.data;
        float[] dd = ((FloatRayImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = dX;
        dd[4] = dY;
        dd[5] = dZ;
        return dest;
    }

    /**
     * Set the direction of this ray to {@code d} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code d} must be non-zero.
     *
     * @param d the new direction
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRay setDirection(Float3R d, @Mutated DoubleRay dest) {
        float[] dData = ((Float3Impl) d).data;
        float dX = dData[0];
        float dY = dData[1];
        float dZ = dData[2];
        float[] sd = this.data;
        double[] dd = ((DoubleRayImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = dX;
        dd[4] = dY;
        dd[5] = dZ;
        return dest;
    }

    /**
     * Set the direction of this ray to ({@code dX}, {@code dY}, {@code dZ}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code (dX, dY, dZ)} must be non-zero.
     *
     * @param dX the {@code x} component of the vector {@code (dX, dY, dZ)}
     * @param dY the {@code y} component of the vector {@code (dX, dY, dZ)}
     * @param dZ the {@code z} component of the vector {@code (dX, dY, dZ)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatRay setDirection(float dX, float dY, float dZ, @Mutated FloatRay dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRayImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = dX;
        dd[4] = dY;
        dd[5] = dZ;
        return dest;
    }

    /**
     * Set the direction of this ray to ({@code dX}, {@code dY}, {@code dZ}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (dX, dY, dZ)} must be non-zero.
     *
     * @param dX the {@code x} component of the vector {@code (dX, dY, dZ)}
     * @param dY the {@code y} component of the vector {@code (dX, dY, dZ)}
     * @param dZ the {@code z} component of the vector {@code (dX, dY, dZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRay setDirection(float dX, float dY, float dZ, @Mutated DoubleRay dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRayImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = dX;
        dd[4] = dY;
        dd[5] = dZ;
        return dest;
    }

    /**
     * Set the origin of this ray to {@code o} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the new origin
     * @param dest will hold the result
     * @return dest
     */
    public FloatRay setOrigin(Float3R o, @Mutated FloatRay dest) {
        float[] oData = ((Float3Impl) o).data;
        float oY = oData[1];
        float oZ = oData[2];
        float[] sd = this.data;
        float[] dd = ((FloatRayImpl) dest).data;
        dd[0] = oData[0];
        dd[1] = oY;
        dd[2] = oZ;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        return dest;
    }

    /**
     * Set the origin of this ray to {@code o} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the new origin
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRay setOrigin(Float3R o, @Mutated DoubleRay dest) {
        float[] oData = ((Float3Impl) o).data;
        float oY = oData[1];
        float oZ = oData[2];
        float[] sd = this.data;
        double[] dd = ((DoubleRayImpl) dest).data;
        dd[0] = oData[0];
        dd[1] = oY;
        dd[2] = oZ;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        return dest;
    }

    /**
     * Set the origin of this ray to ({@code oX}, {@code oY}, {@code oZ}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param oX the {@code x} component of the vector {@code (oX, oY, oZ)}
     * @param oY the {@code y} component of the vector {@code (oX, oY, oZ)}
     * @param oZ the {@code z} component of the vector {@code (oX, oY, oZ)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatRay setOrigin(float oX, float oY, float oZ, @Mutated FloatRay dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRayImpl) dest).data;
        dd[0] = oX;
        dd[1] = oY;
        dd[2] = oZ;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        return dest;
    }

    /**
     * Set the origin of this ray to ({@code oX}, {@code oY}, {@code oZ}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param oX the {@code x} component of the vector {@code (oX, oY, oZ)}
     * @param oY the {@code y} component of the vector {@code (oX, oY, oZ)}
     * @param oZ the {@code z} component of the vector {@code (oX, oY, oZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRay setOrigin(float oX, float oY, float oZ, @Mutated DoubleRay dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRayImpl) dest).data;
        dd[0] = oX;
        dd[1] = oY;
        dd[2] = oZ;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        return dest;
    }

    /**
     * Convert this ray to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRay toDouble(@Mutated DoubleRay dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRayImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        return dest;
    }

    /**
     * Transform this ray by {@code m} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public FloatRay transform(Float3x4R m, @Mutated FloatRay dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(m, dest);
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        float[] dd = ((FloatRayImpl) dest).data;
        float _buf0 = java.lang.Math.fma(mData[2], sd[2], java.lang.Math.fma(mData[0], sd[0], java.lang.Math.fma(mData[1], sd[1], mData[3])));
        float _buf1 = java.lang.Math.fma(mData[6], sd[2], java.lang.Math.fma(mData[4], sd[0], java.lang.Math.fma(mData[5], sd[1], mData[7])));
        dd[2] = java.lang.Math.fma(mData[10], sd[2], java.lang.Math.fma(mData[8], sd[0], java.lang.Math.fma(mData[9], sd[1], mData[11])));
        float _buf2 = java.lang.Math.fma(mData[2], sd[5], java.lang.Math.fma(mData[0], sd[3], mData[1] * sd[4]));
        float _buf3 = java.lang.Math.fma(mData[6], sd[5], java.lang.Math.fma(mData[4], sd[3], mData[5] * sd[4]));
        dd[5] = java.lang.Math.fma(mData[10], sd[5], java.lang.Math.fma(mData[8], sd[3], mData[9] * sd[4]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        return dest;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRay transform_mulAdd(Float3x4R m, @Mutated FloatRay dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        float[] dd = ((FloatRayImpl) dest).data;
        float _buf0 = ((mData[2]) * (sd[2]) + (((mData[0]) * (sd[0]) + (((mData[1]) * (sd[1]) + (mData[3]))))));
        float _buf1 = ((mData[6]) * (sd[2]) + (((mData[4]) * (sd[0]) + (((mData[5]) * (sd[1]) + (mData[7]))))));
        dd[2] = ((mData[10]) * (sd[2]) + (((mData[8]) * (sd[0]) + (((mData[9]) * (sd[1]) + (mData[11]))))));
        float _buf2 = ((mData[2]) * (sd[5]) + (((mData[0]) * (sd[3]) + (mData[1] * sd[4]))));
        float _buf3 = ((mData[6]) * (sd[5]) + (((mData[4]) * (sd[3]) + (mData[5] * sd[4]))));
        dd[5] = ((mData[10]) * (sd[5]) + (((mData[8]) * (sd[3]) + (mData[9] * sd[4]))));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        return dest;
    }

    /**
     * Transform this ray by {@code m} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRay transform(Float3x4R m, @Mutated DoubleRay dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(m, dest);
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        double[] dd = ((DoubleRayImpl) dest).data;
        dd[0] = java.lang.Math.fma(mData[2], sd[2], java.lang.Math.fma(mData[0], sd[0], java.lang.Math.fma(mData[1], sd[1], mData[3])));
        dd[1] = java.lang.Math.fma(mData[6], sd[2], java.lang.Math.fma(mData[4], sd[0], java.lang.Math.fma(mData[5], sd[1], mData[7])));
        dd[2] = java.lang.Math.fma(mData[10], sd[2], java.lang.Math.fma(mData[8], sd[0], java.lang.Math.fma(mData[9], sd[1], mData[11])));
        dd[3] = java.lang.Math.fma(mData[2], sd[5], java.lang.Math.fma(mData[0], sd[3], mData[1] * sd[4]));
        dd[4] = java.lang.Math.fma(mData[6], sd[5], java.lang.Math.fma(mData[4], sd[3], mData[5] * sd[4]));
        dd[5] = java.lang.Math.fma(mData[10], sd[5], java.lang.Math.fma(mData[8], sd[3], mData[9] * sd[4]));
        return dest;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRay transform_mulAdd(Float3x4R m, @Mutated DoubleRay dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        double[] dd = ((DoubleRayImpl) dest).data;
        dd[0] = ((mData[2]) * (sd[2]) + (((mData[0]) * (sd[0]) + (((mData[1]) * (sd[1]) + (mData[3]))))));
        dd[1] = ((mData[6]) * (sd[2]) + (((mData[4]) * (sd[0]) + (((mData[5]) * (sd[1]) + (mData[7]))))));
        dd[2] = ((mData[10]) * (sd[2]) + (((mData[8]) * (sd[0]) + (((mData[9]) * (sd[1]) + (mData[11]))))));
        dd[3] = ((mData[2]) * (sd[5]) + (((mData[0]) * (sd[3]) + (mData[1] * sd[4]))));
        dd[4] = ((mData[6]) * (sd[5]) + (((mData[4]) * (sd[3]) + (mData[5] * sd[4]))));
        dd[5] = ((mData[10]) * (sd[5]) + (((mData[8]) * (sd[3]) + (mData[9] * sd[4]))));
        return dest;
    }

    /**
     * Transform this ray by {@code m} and store the result in {@code dest}.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public FloatRay transform(Float4x4R m, @Mutated FloatRay dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(m, dest);
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        float[] dd = ((FloatRayImpl) dest).data;
        float _buf0 = java.lang.Math.fma(mData[8], sd[2], java.lang.Math.fma(mData[0], sd[0], java.lang.Math.fma(mData[4], sd[1], mData[12])));
        float _buf1 = java.lang.Math.fma(mData[9], sd[2], java.lang.Math.fma(mData[1], sd[0], java.lang.Math.fma(mData[5], sd[1], mData[13])));
        dd[2] = java.lang.Math.fma(mData[10], sd[2], java.lang.Math.fma(mData[2], sd[0], java.lang.Math.fma(mData[6], sd[1], mData[14])));
        float _buf2 = java.lang.Math.fma(mData[8], sd[5], java.lang.Math.fma(mData[0], sd[3], mData[4] * sd[4]));
        float _buf3 = java.lang.Math.fma(mData[9], sd[5], java.lang.Math.fma(mData[1], sd[3], mData[5] * sd[4]));
        dd[5] = java.lang.Math.fma(mData[10], sd[5], java.lang.Math.fma(mData[2], sd[3], mData[6] * sd[4]));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        return dest;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRay transform_mulAdd(Float4x4R m, @Mutated FloatRay dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        float[] dd = ((FloatRayImpl) dest).data;
        float _buf0 = ((mData[8]) * (sd[2]) + (((mData[0]) * (sd[0]) + (((mData[4]) * (sd[1]) + (mData[12]))))));
        float _buf1 = ((mData[9]) * (sd[2]) + (((mData[1]) * (sd[0]) + (((mData[5]) * (sd[1]) + (mData[13]))))));
        dd[2] = ((mData[10]) * (sd[2]) + (((mData[2]) * (sd[0]) + (((mData[6]) * (sd[1]) + (mData[14]))))));
        float _buf2 = ((mData[8]) * (sd[5]) + (((mData[0]) * (sd[3]) + (mData[4] * sd[4]))));
        float _buf3 = ((mData[9]) * (sd[5]) + (((mData[1]) * (sd[3]) + (mData[5] * sd[4]))));
        dd[5] = ((mData[10]) * (sd[5]) + (((mData[2]) * (sd[3]) + (mData[6] * sd[4]))));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        return dest;
    }

    /**
     * Transform this ray by {@code m} and store the result in {@code dest}.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRay transform(Float4x4R m, @Mutated DoubleRay dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(m, dest);
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        double[] dd = ((DoubleRayImpl) dest).data;
        dd[0] = java.lang.Math.fma(mData[8], sd[2], java.lang.Math.fma(mData[0], sd[0], java.lang.Math.fma(mData[4], sd[1], mData[12])));
        dd[1] = java.lang.Math.fma(mData[9], sd[2], java.lang.Math.fma(mData[1], sd[0], java.lang.Math.fma(mData[5], sd[1], mData[13])));
        dd[2] = java.lang.Math.fma(mData[10], sd[2], java.lang.Math.fma(mData[2], sd[0], java.lang.Math.fma(mData[6], sd[1], mData[14])));
        dd[3] = java.lang.Math.fma(mData[8], sd[5], java.lang.Math.fma(mData[0], sd[3], mData[4] * sd[4]));
        dd[4] = java.lang.Math.fma(mData[9], sd[5], java.lang.Math.fma(mData[1], sd[3], mData[5] * sd[4]));
        dd[5] = java.lang.Math.fma(mData[10], sd[5], java.lang.Math.fma(mData[2], sd[3], mData[6] * sd[4]));
        return dest;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRay transform_mulAdd(Float4x4R m, @Mutated DoubleRay dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        double[] dd = ((DoubleRayImpl) dest).data;
        dd[0] = ((mData[8]) * (sd[2]) + (((mData[0]) * (sd[0]) + (((mData[4]) * (sd[1]) + (mData[12]))))));
        dd[1] = ((mData[9]) * (sd[2]) + (((mData[1]) * (sd[0]) + (((mData[5]) * (sd[1]) + (mData[13]))))));
        dd[2] = ((mData[10]) * (sd[2]) + (((mData[2]) * (sd[0]) + (((mData[6]) * (sd[1]) + (mData[14]))))));
        dd[3] = ((mData[8]) * (sd[5]) + (((mData[0]) * (sd[3]) + (mData[4] * sd[4]))));
        dd[4] = ((mData[9]) * (sd[5]) + (((mData[1]) * (sd[3]) + (mData[5] * sd[4]))));
        dd[5] = ((mData[10]) * (sd[5]) + (((mData[2]) * (sd[3]) + (mData[6] * sd[4]))));
        return dest;
    }

    /**
     * Compute the point on this ray at the parameter value {@code t} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the ray parameter: the point is {@code origin + t * direction}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 at(float t, @Mutated Float3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, sd[3], sd[0]);
            dd[1] = java.lang.Math.fma(t, sd[4], sd[1]);
            dd[2] = java.lang.Math.fma(t, sd[5], sd[2]);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            dd[0] = ((t) * (sd[3]) + (sd[0]));
            dd[1] = ((t) * (sd[4]) + (sd[1]));
            dd[2] = ((t) * (sd[5]) + (sd[2]));
            return dest;
        }
    }

    /**
     * Compute the point on this ray at the parameter value {@code t} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the ray parameter: the point is {@code origin + t * direction}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 at(float t, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = java.lang.Math.fma(t, sd[3], sd[0]);
            dd[1] = java.lang.Math.fma(t, sd[4], sd[1]);
            dd[2] = java.lang.Math.fma(t, sd[5], sd[2]);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            dd[0] = ((t) * (sd[3]) + (sd[0]));
            dd[1] = ((t) * (sd[4]) + (sd[1]));
            dd[2] = ((t) * (sd[5]) + (sd[2]));
            return dest;
        }
    }

    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Float3 closestPointToPoint(Float3R p, @Mutated Float3 dest) {
        float[] pData = ((Float3Impl) p).data;
        return closestPointToPoint(pData[0], pData[1], pData[2], dest);
    }

    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointToPoint(Float3R p, @Mutated Double3 dest) {
        float[] pData = ((Float3Impl) p).data;
        return closestPointToPoint(pData[0], pData[1], pData[2], dest);
    }

    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param dest will hold the result
     * @return dest
     */
    public Float3 closestPointToPoint(float pX, float pY, float pZ, @Mutated Float3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            float _t10 = Math.max(0.0f, java.lang.Math.fma(sd[5], pZ - sd[2], java.lang.Math.fma(sd[3], pX - sd[0], sd[4] * (pY - sd[1]))) / java.lang.Math.fma(sd[5], sd[5], java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4])));
            dd[0] = java.lang.Math.fma(sd[3], _t10, sd[0]);
            dd[1] = java.lang.Math.fma(sd[4], _t10, sd[1]);
            dd[2] = java.lang.Math.fma(sd[5], _t10, sd[2]);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            float _t10 = Math.max(0.0f, ((sd[5]) * (pZ - sd[2]) + (((sd[3]) * (pX - sd[0]) + (sd[4] * (pY - sd[1]))))) / ((sd[5]) * (sd[5]) + (((sd[3]) * (sd[3]) + (sd[4] * sd[4])))));
            dd[0] = ((sd[3]) * (_t10) + (sd[0]));
            dd[1] = ((sd[4]) * (_t10) + (sd[1]));
            dd[2] = ((sd[5]) * (_t10) + (sd[2]));
            return dest;
        }
    }

    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointToPoint(float pX, float pY, float pZ, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            float _t10 = Math.max(0.0f, java.lang.Math.fma(sd[5], pZ - sd[2], java.lang.Math.fma(sd[3], pX - sd[0], sd[4] * (pY - sd[1]))) / java.lang.Math.fma(sd[5], sd[5], java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4])));
            dd[0] = java.lang.Math.fma(sd[3], _t10, sd[0]);
            dd[1] = java.lang.Math.fma(sd[4], _t10, sd[1]);
            dd[2] = java.lang.Math.fma(sd[5], _t10, sd[2]);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            float _t10 = Math.max(0.0f, ((sd[5]) * (pZ - sd[2]) + (((sd[3]) * (pX - sd[0]) + (sd[4] * (pY - sd[1]))))) / ((sd[5]) * (sd[5]) + (((sd[3]) * (sd[3]) + (sd[4] * sd[4])))));
            dd[0] = ((sd[3]) * (_t10) + (sd[0]));
            dd[1] = ((sd[4]) * (_t10) + (sd[1]));
            dd[2] = ((sd[5]) * (_t10) + (sd[2]));
            return dest;
        }
    }

    /**
     * Compute the squared distance between this ray and the given point, i.e. the squared distance
     * from the point to the closest point on the ray (the ray starts at its origin and extends only
     * along its direction). The direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this ray and the given point, i.e. the squared distance
     *        from the point to the closest point on the ray (the ray starts at its origin and
     *        extends only along its direction). The direction need not be of unit length but must
     *        not be zero
     */
    public float distanceSquaredToPoint(Float3R p) {
        float[] pData = ((Float3Impl) p).data;
        return distanceSquaredToPoint(pData[0], pData[1], pData[2]);
    }

    /**
     * Compute the squared distance between this ray and the given point, i.e. the squared distance
     * from the point to the closest point on the ray (the ray starts at its origin and extends only
     * along its direction). The direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the squared distance between this ray and the given point, i.e. the squared distance
     *        from the point to the closest point on the ray (the ray starts at its origin and
     *        extends only along its direction). The direction need not be of unit length but must
     *        not be zero
     */
    public float distanceSquaredToPoint(float pX, float pY, float pZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float _t3 = pZ - sd[2];
            float _t4 = pX - sd[0];
            float _t5 = pY - sd[1];
            float _t13 = Math.max(0.0f, java.lang.Math.fma(sd[5], _t3, java.lang.Math.fma(sd[3], _t4, sd[4] * _t5)) / java.lang.Math.fma(sd[5], sd[5], java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4])));
            float _t14 = java.lang.Math.fma(-sd[5], _t13, _t3);
            float _t15 = java.lang.Math.fma(-sd[3], _t13, _t4);
            float _t16 = java.lang.Math.fma(-sd[4], _t13, _t5);
            return java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16));
        } else {
            float[] sd = this.data;
            float _t3 = pZ - sd[2];
            float _t4 = pX - sd[0];
            float _t5 = pY - sd[1];
            float _t13 = Math.max(0.0f, ((sd[5]) * (_t3) + (((sd[3]) * (_t4) + (sd[4] * _t5)))) / ((sd[5]) * (sd[5]) + (((sd[3]) * (sd[3]) + (sd[4] * sd[4])))));
            float _t14 = ((_t3) - (sd[5]) * (_t13));
            float _t15 = ((_t4) - (sd[3]) * (_t13));
            float _t16 = ((_t5) - (sd[4]) * (_t13));
            return ((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))));
        }
    }

    /**
     * Compute the distance between this ray and the given point, i.e. the distance from the point
     * to the closest point on the ray (the ray starts at its origin and extends only along its
     * direction). The direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to measure the distance to
     * @return the distance between this ray and the given point, i.e. the distance from the point
     *        to the closest point on the ray (the ray starts at its origin and extends only along
     *        its direction). The direction need not be of unit length but must not be zero
     */
    public float distanceToPoint(Float3R p) {
        float[] pData = ((Float3Impl) p).data;
        float pX = pData[0];
        float pY = pData[1];
        float pZ = pData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return distanceToPoint_mulAdd(pX, pY, pZ);
        float[] sd = this.data;
        float _t3 = pZ - sd[2];
        float _t4 = pX - sd[0];
        float _t5 = pY - sd[1];
        float _t13 = Math.max(0.0f, java.lang.Math.fma(sd[5], _t3, java.lang.Math.fma(sd[3], _t4, sd[4] * _t5)) / java.lang.Math.fma(sd[5], sd[5], java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4])));
        float _t14 = java.lang.Math.fma(-sd[5], _t13, _t3);
        float _t15 = java.lang.Math.fma(-sd[3], _t13, _t4);
        float _t16 = java.lang.Math.fma(-sd[4], _t13, _t5);
        return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)));
    }

    /**
     * Compute the distance between this ray and the given point, i.e. the distance from the point
     * to the closest point on the ray (the ray starts at its origin and extends only along its
     * direction). The direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the distance between this ray and the given point, i.e. the distance from the point
     *        to the closest point on the ray (the ray starts at its origin and extends only along
     *        its direction). The direction need not be of unit length but must not be zero
     */
    public float distanceToPoint(float pX, float pY, float pZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return distanceToPoint_mulAdd(pX, pY, pZ);
        float[] sd = this.data;
        float _t3 = pZ - sd[2];
        float _t4 = pX - sd[0];
        float _t5 = pY - sd[1];
        float _t13 = Math.max(0.0f, java.lang.Math.fma(sd[5], _t3, java.lang.Math.fma(sd[3], _t4, sd[4] * _t5)) / java.lang.Math.fma(sd[5], sd[5], java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4])));
        float _t14 = java.lang.Math.fma(-sd[5], _t13, _t3);
        float _t15 = java.lang.Math.fma(-sd[3], _t13, _t4);
        float _t16 = java.lang.Math.fma(-sd[4], _t13, _t5);
        return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)));
    }

    /** {@code distanceToPoint} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private float distanceToPoint_mulAdd(float pX, float pY, float pZ) {
        float[] sd = this.data;
        float _t3 = pZ - sd[2];
        float _t4 = pX - sd[0];
        float _t5 = pY - sd[1];
        float _t13 = Math.max(0.0f, ((sd[5]) * (_t3) + (((sd[3]) * (_t4) + (sd[4] * _t5)))) / ((sd[5]) * (sd[5]) + (((sd[3]) * (sd[3]) + (sd[4] * sd[4])))));
        float _t14 = ((_t3) - (sd[5]) * (_t13));
        float _t15 = ((_t4) - (sd[3]) * (_t13));
        float _t16 = ((_t5) - (sd[4]) * (_t13));
        return (float) java.lang.Math.sqrt(((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16)))));
    }

    /**
     * Get the direction of this ray and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getDirection(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[3];
        dd[1] = sd[4];
        dd[2] = sd[5];
        return dest;
    }

    /**
     * Get the direction of this ray and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getDirection(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[3];
        dd[1] = sd[4];
        dd[2] = sd[5];
        return dest;
    }

    /**
     * Get the origin of this ray and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getOrigin(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
    }

    /**
     * Get the origin of this ray and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getOrigin(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
    }

    public float oX() { return data[0]; }
    public float oY() { return data[1]; }
    public float oZ() { return data[2]; }
    public float dX() { return data[3]; }
    public float dY() { return data[4]; }
    public float dZ() { return data[5]; }

    @Override public String toString() {
        return "FloatRay(" + this.data[0] + ", " + this.data[1] + ", " + this.data[2] + ", " + this.data[3] + ", " + this.data[4] + ", " + this.data[5] + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatRayImpl)) return false;
        FloatRayImpl o = (FloatRayImpl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Float.isFinite(data[0])
            && Float.isFinite(data[1])
            && Float.isFinite(data[2])
            && Float.isFinite(data[3])
            && Float.isFinite(data[4])
            && Float.isFinite(data[5]);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(data[0])
            || Float.isNaN(data[1])
            || Float.isNaN(data[2])
            || Float.isNaN(data[3])
            || Float.isNaN(data[4])
            || Float.isNaN(data[5]);
    }

    @Override public boolean equalsEpsilon(FloatRayR other, float epsilon) {
        float[] otherData = ((FloatRayImpl) other).data;
        float[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherData[0]) <= epsilon
            && java.lang.Math.abs(sd[1] - otherData[1]) <= epsilon
            && java.lang.Math.abs(sd[2] - otherData[2]) <= epsilon
            && java.lang.Math.abs(sd[3] - otherData[3]) <= epsilon
            && java.lang.Math.abs(sd[4] - otherData[4]) <= epsilon
            && java.lang.Math.abs(sd[5] - otherData[5]) <= epsilon;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        return dest;
    }

    @Mutated public FloatRay load(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        return this;
    }

    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return buf;
    }

    @Mutated public FloatRay load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatRay loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public FloatRay loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return this;
    }

    public ByteBuffer store(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public ByteBuffer storeRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }

    @Mutated public FloatRay load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatRay loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public FloatRay loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return this;
    }

    public FloatRay storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    @Mutated public FloatRay loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }

    public MemorySegment store(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }

    @Mutated public FloatRay load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }

    @Mutated public FloatRay load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        return dest;
    }

    @Mutated public FloatRay load(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        this.data[4] = (float) src[offset + 4];
        this.data[5] = (float) src[offset + 5];
        return this;
    }

    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return buf;
    }

    @Mutated public FloatRay load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatRay loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public FloatRay loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return this;
    }

    public ByteBuffer storeDouble(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeDoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeDoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }

    @Mutated public FloatRay loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatRay loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }

    @Mutated public FloatRay loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return this;
    }

    public FloatRay storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    @Mutated public FloatRay loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }

    public MemorySegment storeDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }

    public MemorySegment storeDouble(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }

    @Mutated public FloatRay loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(this, 0L, src); }

    @Mutated public FloatRay loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(this, offset, src);
    }
}
