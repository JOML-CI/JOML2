// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link FloatTriangle} backed by a {@code float[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatTriangleImpl implements FloatTriangle {

    public float[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatTriangleSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatTriangleSegOpsUnsafe()
                        : new FloatTriangleSegOpsMS();
        static final FloatTriangleBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatTriangleBbOpsUnsafe()
                        : new FloatTriangleBbOpsApi();
        static final FloatTriangleRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatTriangleRawOpsUnsafe()
                        : new FloatTriangleRawOpsApi();
    }

    public FloatTriangleImpl() {
        data = new float[9];
    }

    public FloatTriangleImpl(float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z) {
        float[] dd = this.data = new float[9];
        dd[0] = v0X;
        dd[1] = v0Y;
        dd[2] = v0Z;
        dd[3] = v1X;
        dd[4] = v1Y;
        dd[5] = v1Z;
        dd[6] = v2X;
        dd[7] = v2Y;
        dd[8] = v2Z;
    }

    public FloatTriangleImpl(FloatTriangleR src) {
        float[] dd = this.data = new float[9];
        dd[0] = src.v0X();
        dd[1] = src.v0Y();
        dd[2] = src.v0Z();
        dd[3] = src.v1X();
        dd[4] = src.v1Y();
        dd[5] = src.v1Z();
        dd[6] = src.v2X();
        dd[7] = src.v2Y();
        dd[8] = src.v2Z();
    }


    /**
     * Set this triangle to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the triangle to copy
     * @return this
     */
    public @Mutated FloatTriangle set(FloatTriangleR v) {
        float vV0Y = v.v0Y();
        float vV0Z = v.v0Z();
        float vV1X = v.v1X();
        float vV1Y = v.v1Y();
        float vV1Z = v.v1Z();
        float vV2X = v.v2X();
        float vV2Y = v.v2Y();
        float vV2Z = v.v2Z();
        float[] dd = this.data;
        dd[0] = v.v0X();
        dd[1] = vV0Y;
        dd[2] = vV0Z;
        dd[3] = vV1X;
        dd[4] = vV1Y;
        dd[5] = vV1Z;
        dd[6] = vV2X;
        dd[7] = vV2Y;
        dd[8] = vV2Z;
        return this;
    }


    /**
     * Set this triangle to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vV0X the {@code v0X} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @param vV0Y the {@code v0Y} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @param vV0Z the {@code v0Z} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @param vV1X the {@code v1X} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @param vV1Y the {@code v1Y} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @param vV1Z the {@code v1Z} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @param vV2X the {@code v2X} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @param vV2Y the {@code v2Y} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @param vV2Z the {@code v2Z} component of the triangle
     *        {@code (vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z)}
     * @return this
     */
    @Mutated public FloatTriangle set(float vV0X, float vV0Y, float vV0Z, float vV1X, float vV1Y, float vV1Z, float vV2X, float vV2Y, float vV2Z) {
        float[] dd = this.data;
        dd[0] = vV0X;
        dd[1] = vV0Y;
        dd[2] = vV0Z;
        dd[3] = vV1X;
        dd[4] = vV1Y;
        dd[5] = vV1Z;
        dd[6] = vV2X;
        dd[7] = vV2Y;
        dd[8] = vV2Z;
        return this;
    }


    /**
     * Convert this triangle to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTriangle toDouble(@Mutated DoubleTriangle dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTriangleImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        return dest;
    }


    /**
     * Transform this triangle by {@code m} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public FloatTriangle transform(Float3x4R m, @Mutated FloatTriangle dest) {
        if (Math.useFma()) return transform_fma(m, dest);
        return transform_mulAdd(m, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTriangle transform_fma(Float3x4R m, @Mutated FloatTriangle dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        float[] dd = ((FloatTriangleImpl) dest).data;
        float _rd0 = mData[0];
        float _rd1 = mData[1];
        float _rd2 = mData[2];
        float _rd3 = mData[3];
        float _rd4 = mData[4];
        float _rd5 = mData[5];
        float _rd6 = mData[6];
        float _rd7 = mData[7];
        float _rd8 = mData[8];
        float _rd9 = mData[9];
        float _rd10 = mData[10];
        float _rd11 = mData[11];
        float _rd12 = sd[0];
        float _rd13 = sd[1];
        float _rd14 = sd[2];
        float _rd15 = sd[3];
        float _rd16 = sd[4];
        float _rd17 = sd[5];
        float _rd18 = sd[6];
        float _rd19 = sd[7];
        float _rd20 = sd[8];
        dd[0] = java.lang.Math.fma(_rd2, _rd14, java.lang.Math.fma(_rd0, _rd12, java.lang.Math.fma(_rd1, _rd13, _rd3)));
        dd[1] = java.lang.Math.fma(_rd6, _rd14, java.lang.Math.fma(_rd4, _rd12, java.lang.Math.fma(_rd5, _rd13, _rd7)));
        dd[2] = java.lang.Math.fma(_rd10, _rd14, java.lang.Math.fma(_rd8, _rd12, java.lang.Math.fma(_rd9, _rd13, _rd11)));
        dd[3] = java.lang.Math.fma(_rd2, _rd17, java.lang.Math.fma(_rd0, _rd15, java.lang.Math.fma(_rd1, _rd16, _rd3)));
        return transform_s7e00c5d1_1_fma(dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7, _rd8, _rd9, _rd10, _rd11, _rd15, _rd16, _rd17, _rd18, _rd19, _rd20);
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTriangle transform_mulAdd(Float3x4R m, @Mutated FloatTriangle dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        float[] dd = ((FloatTriangleImpl) dest).data;
        float _rd0 = mData[0];
        float _rd1 = mData[1];
        float _rd2 = mData[2];
        float _rd3 = mData[3];
        float _rd4 = mData[4];
        float _rd5 = mData[5];
        float _rd6 = mData[6];
        float _rd7 = mData[7];
        float _rd8 = mData[8];
        float _rd9 = mData[9];
        float _rd10 = mData[10];
        float _rd11 = mData[11];
        float _rd12 = sd[0];
        float _rd13 = sd[1];
        float _rd14 = sd[2];
        float _rd15 = sd[3];
        float _rd16 = sd[4];
        float _rd17 = sd[5];
        float _rd18 = sd[6];
        float _rd19 = sd[7];
        float _rd20 = sd[8];
        dd[0] = ((_rd2) * (_rd14) + (((_rd0) * (_rd12) + (((_rd1) * (_rd13) + (_rd3))))));
        dd[1] = ((_rd6) * (_rd14) + (((_rd4) * (_rd12) + (((_rd5) * (_rd13) + (_rd7))))));
        dd[2] = ((_rd10) * (_rd14) + (((_rd8) * (_rd12) + (((_rd9) * (_rd13) + (_rd11))))));
        dd[3] = ((_rd2) * (_rd17) + (((_rd0) * (_rd15) + (((_rd1) * (_rd16) + (_rd3))))));
        return transform_s7e00c5d1_1_mulAdd(dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7, _rd8, _rd9, _rd10, _rd11, _rd15, _rd16, _rd17, _rd18, _rd19, _rd20);
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatTriangle transform_s7e00c5d1_1_fma(FloatTriangle dest, float[] dd, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7, float _rd8, float _rd9, float _rd10, float _rd11, float _rd15, float _rd16, float _rd17, float _rd18, float _rd19, float _rd20) {
        dd[4] = java.lang.Math.fma(_rd6, _rd17, java.lang.Math.fma(_rd4, _rd15, java.lang.Math.fma(_rd5, _rd16, _rd7)));
        dd[5] = java.lang.Math.fma(_rd10, _rd17, java.lang.Math.fma(_rd8, _rd15, java.lang.Math.fma(_rd9, _rd16, _rd11)));
        dd[6] = java.lang.Math.fma(_rd2, _rd20, java.lang.Math.fma(_rd0, _rd18, java.lang.Math.fma(_rd1, _rd19, _rd3)));
        dd[7] = java.lang.Math.fma(_rd6, _rd20, java.lang.Math.fma(_rd4, _rd18, java.lang.Math.fma(_rd5, _rd19, _rd7)));
        dd[8] = java.lang.Math.fma(_rd10, _rd20, java.lang.Math.fma(_rd8, _rd18, java.lang.Math.fma(_rd9, _rd19, _rd11)));
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatTriangle transform_s7e00c5d1_1_mulAdd(FloatTriangle dest, float[] dd, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7, float _rd8, float _rd9, float _rd10, float _rd11, float _rd15, float _rd16, float _rd17, float _rd18, float _rd19, float _rd20) {
        dd[4] = ((_rd6) * (_rd17) + (((_rd4) * (_rd15) + (((_rd5) * (_rd16) + (_rd7))))));
        dd[5] = ((_rd10) * (_rd17) + (((_rd8) * (_rd15) + (((_rd9) * (_rd16) + (_rd11))))));
        dd[6] = ((_rd2) * (_rd20) + (((_rd0) * (_rd18) + (((_rd1) * (_rd19) + (_rd3))))));
        dd[7] = ((_rd6) * (_rd20) + (((_rd4) * (_rd18) + (((_rd5) * (_rd19) + (_rd7))))));
        dd[8] = ((_rd10) * (_rd20) + (((_rd8) * (_rd18) + (((_rd9) * (_rd19) + (_rd11))))));
        return dest;
    }


    /**
     * Transform this triangle by {@code m} and store the result in {@code dest}.
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
    public DoubleTriangle transform(Float3x4R m, @Mutated DoubleTriangle dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        double[] dd = ((DoubleTriangleImpl) dest).data;
        dd[0] = Math.fma(mData[2], sd[2], Math.fma(mData[0], sd[0], Math.fma(mData[1], sd[1], mData[3])));
        dd[1] = Math.fma(mData[6], sd[2], Math.fma(mData[4], sd[0], Math.fma(mData[5], sd[1], mData[7])));
        dd[2] = Math.fma(mData[10], sd[2], Math.fma(mData[8], sd[0], Math.fma(mData[9], sd[1], mData[11])));
        dd[3] = Math.fma(mData[2], sd[5], Math.fma(mData[0], sd[3], Math.fma(mData[1], sd[4], mData[3])));
        dd[4] = Math.fma(mData[6], sd[5], Math.fma(mData[4], sd[3], Math.fma(mData[5], sd[4], mData[7])));
        dd[5] = Math.fma(mData[10], sd[5], Math.fma(mData[8], sd[3], Math.fma(mData[9], sd[4], mData[11])));
        return (Math.useFma() ? transform_sa2751ad2_1_fma(dest, sd, mData, dd) : transform_sa2751ad2_1_mulAdd(dest, sd, mData, dd));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleTriangle transform_sa2751ad2_1_fma(DoubleTriangle dest, float[] sd, float[] mData, double[] dd) {
        dd[6] = java.lang.Math.fma(mData[2], sd[8], java.lang.Math.fma(mData[0], sd[6], java.lang.Math.fma(mData[1], sd[7], mData[3])));
        dd[7] = java.lang.Math.fma(mData[6], sd[8], java.lang.Math.fma(mData[4], sd[6], java.lang.Math.fma(mData[5], sd[7], mData[7])));
        dd[8] = java.lang.Math.fma(mData[10], sd[8], java.lang.Math.fma(mData[8], sd[6], java.lang.Math.fma(mData[9], sd[7], mData[11])));
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleTriangle transform_sa2751ad2_1_mulAdd(DoubleTriangle dest, float[] sd, float[] mData, double[] dd) {
        dd[6] = ((mData[2]) * (sd[8]) + (((mData[0]) * (sd[6]) + (((mData[1]) * (sd[7]) + (mData[3]))))));
        dd[7] = ((mData[6]) * (sd[8]) + (((mData[4]) * (sd[6]) + (((mData[5]) * (sd[7]) + (mData[7]))))));
        dd[8] = ((mData[10]) * (sd[8]) + (((mData[8]) * (sd[6]) + (((mData[9]) * (sd[7]) + (mData[11]))))));
        return dest;
    }


    /**
     * Transform this triangle by {@code m} and store the result in {@code dest}.
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
    public FloatTriangle transform(Float4x4R m, @Mutated FloatTriangle dest) {
        if (Math.useFma()) return transform_fma(m, dest);
        return transform_mulAdd(m, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTriangle transform_fma(Float4x4R m, @Mutated FloatTriangle dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        float[] dd = ((FloatTriangleImpl) dest).data;
        float _rd0 = mData[0];
        float _rd1 = mData[1];
        float _rd2 = mData[2];
        float _rd3 = mData[4];
        float _rd4 = mData[5];
        float _rd5 = mData[6];
        float _rd6 = mData[8];
        float _rd7 = mData[9];
        float _rd8 = mData[10];
        float _rd9 = mData[12];
        float _rd10 = mData[13];
        float _rd11 = mData[14];
        float _rd12 = sd[0];
        float _rd13 = sd[1];
        float _rd14 = sd[2];
        float _rd15 = sd[3];
        float _rd16 = sd[4];
        float _rd17 = sd[5];
        float _rd18 = sd[6];
        float _rd19 = sd[7];
        float _rd20 = sd[8];
        dd[0] = java.lang.Math.fma(_rd6, _rd14, java.lang.Math.fma(_rd0, _rd12, java.lang.Math.fma(_rd3, _rd13, _rd9)));
        dd[1] = java.lang.Math.fma(_rd7, _rd14, java.lang.Math.fma(_rd1, _rd12, java.lang.Math.fma(_rd4, _rd13, _rd10)));
        dd[2] = java.lang.Math.fma(_rd8, _rd14, java.lang.Math.fma(_rd2, _rd12, java.lang.Math.fma(_rd5, _rd13, _rd11)));
        dd[3] = java.lang.Math.fma(_rd6, _rd17, java.lang.Math.fma(_rd0, _rd15, java.lang.Math.fma(_rd3, _rd16, _rd9)));
        return transform_s3bf1387c_1_fma(dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7, _rd8, _rd9, _rd10, _rd11, _rd15, _rd16, _rd17, _rd18, _rd19, _rd20);
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTriangle transform_mulAdd(Float4x4R m, @Mutated FloatTriangle dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        float[] dd = ((FloatTriangleImpl) dest).data;
        float _rd0 = mData[0];
        float _rd1 = mData[1];
        float _rd2 = mData[2];
        float _rd3 = mData[4];
        float _rd4 = mData[5];
        float _rd5 = mData[6];
        float _rd6 = mData[8];
        float _rd7 = mData[9];
        float _rd8 = mData[10];
        float _rd9 = mData[12];
        float _rd10 = mData[13];
        float _rd11 = mData[14];
        float _rd12 = sd[0];
        float _rd13 = sd[1];
        float _rd14 = sd[2];
        float _rd15 = sd[3];
        float _rd16 = sd[4];
        float _rd17 = sd[5];
        float _rd18 = sd[6];
        float _rd19 = sd[7];
        float _rd20 = sd[8];
        dd[0] = ((_rd6) * (_rd14) + (((_rd0) * (_rd12) + (((_rd3) * (_rd13) + (_rd9))))));
        dd[1] = ((_rd7) * (_rd14) + (((_rd1) * (_rd12) + (((_rd4) * (_rd13) + (_rd10))))));
        dd[2] = ((_rd8) * (_rd14) + (((_rd2) * (_rd12) + (((_rd5) * (_rd13) + (_rd11))))));
        dd[3] = ((_rd6) * (_rd17) + (((_rd0) * (_rd15) + (((_rd3) * (_rd16) + (_rd9))))));
        return transform_s3bf1387c_1_mulAdd(dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7, _rd8, _rd9, _rd10, _rd11, _rd15, _rd16, _rd17, _rd18, _rd19, _rd20);
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatTriangle transform_s3bf1387c_1_fma(FloatTriangle dest, float[] dd, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7, float _rd8, float _rd9, float _rd10, float _rd11, float _rd15, float _rd16, float _rd17, float _rd18, float _rd19, float _rd20) {
        dd[4] = java.lang.Math.fma(_rd7, _rd17, java.lang.Math.fma(_rd1, _rd15, java.lang.Math.fma(_rd4, _rd16, _rd10)));
        dd[5] = java.lang.Math.fma(_rd8, _rd17, java.lang.Math.fma(_rd2, _rd15, java.lang.Math.fma(_rd5, _rd16, _rd11)));
        dd[6] = java.lang.Math.fma(_rd6, _rd20, java.lang.Math.fma(_rd0, _rd18, java.lang.Math.fma(_rd3, _rd19, _rd9)));
        dd[7] = java.lang.Math.fma(_rd7, _rd20, java.lang.Math.fma(_rd1, _rd18, java.lang.Math.fma(_rd4, _rd19, _rd10)));
        dd[8] = java.lang.Math.fma(_rd8, _rd20, java.lang.Math.fma(_rd2, _rd18, java.lang.Math.fma(_rd5, _rd19, _rd11)));
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatTriangle transform_s3bf1387c_1_mulAdd(FloatTriangle dest, float[] dd, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7, float _rd8, float _rd9, float _rd10, float _rd11, float _rd15, float _rd16, float _rd17, float _rd18, float _rd19, float _rd20) {
        dd[4] = ((_rd7) * (_rd17) + (((_rd1) * (_rd15) + (((_rd4) * (_rd16) + (_rd10))))));
        dd[5] = ((_rd8) * (_rd17) + (((_rd2) * (_rd15) + (((_rd5) * (_rd16) + (_rd11))))));
        dd[6] = ((_rd6) * (_rd20) + (((_rd0) * (_rd18) + (((_rd3) * (_rd19) + (_rd9))))));
        dd[7] = ((_rd7) * (_rd20) + (((_rd1) * (_rd18) + (((_rd4) * (_rd19) + (_rd10))))));
        dd[8] = ((_rd8) * (_rd20) + (((_rd2) * (_rd18) + (((_rd5) * (_rd19) + (_rd11))))));
        return dest;
    }


    /**
     * Transform this triangle by {@code m} and store the result in {@code dest}.
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
    public DoubleTriangle transform(Float4x4R m, @Mutated DoubleTriangle dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        double[] dd = ((DoubleTriangleImpl) dest).data;
        dd[0] = Math.fma(mData[8], sd[2], Math.fma(mData[0], sd[0], Math.fma(mData[4], sd[1], mData[12])));
        dd[1] = Math.fma(mData[9], sd[2], Math.fma(mData[1], sd[0], Math.fma(mData[5], sd[1], mData[13])));
        dd[2] = Math.fma(mData[10], sd[2], Math.fma(mData[2], sd[0], Math.fma(mData[6], sd[1], mData[14])));
        dd[3] = Math.fma(mData[8], sd[5], Math.fma(mData[0], sd[3], Math.fma(mData[4], sd[4], mData[12])));
        dd[4] = Math.fma(mData[9], sd[5], Math.fma(mData[1], sd[3], Math.fma(mData[5], sd[4], mData[13])));
        dd[5] = Math.fma(mData[10], sd[5], Math.fma(mData[2], sd[3], Math.fma(mData[6], sd[4], mData[14])));
        return (Math.useFma() ? transform_s130b84c5_1_fma(dest, sd, mData, dd) : transform_s130b84c5_1_mulAdd(dest, sd, mData, dd));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleTriangle transform_s130b84c5_1_fma(DoubleTriangle dest, float[] sd, float[] mData, double[] dd) {
        dd[6] = java.lang.Math.fma(mData[8], sd[8], java.lang.Math.fma(mData[0], sd[6], java.lang.Math.fma(mData[4], sd[7], mData[12])));
        dd[7] = java.lang.Math.fma(mData[9], sd[8], java.lang.Math.fma(mData[1], sd[6], java.lang.Math.fma(mData[5], sd[7], mData[13])));
        dd[8] = java.lang.Math.fma(mData[10], sd[8], java.lang.Math.fma(mData[2], sd[6], java.lang.Math.fma(mData[6], sd[7], mData[14])));
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleTriangle transform_s130b84c5_1_mulAdd(DoubleTriangle dest, float[] sd, float[] mData, double[] dd) {
        dd[6] = ((mData[8]) * (sd[8]) + (((mData[0]) * (sd[6]) + (((mData[4]) * (sd[7]) + (mData[12]))))));
        dd[7] = ((mData[9]) * (sd[8]) + (((mData[1]) * (sd[6]) + (((mData[5]) * (sd[7]) + (mData[13]))))));
        dd[8] = ((mData[10]) * (sd[8]) + (((mData[2]) * (sd[6]) + (((mData[6]) * (sd[7]) + (mData[14]))))));
        return dest;
    }


    /**
     * Compute the area of this triangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the area of this triangle
     */
    public float area() {
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t0 = sd[3] - sd[0];
            float _t1 = sd[7] - sd[1];
            float _t2 = sd[4] - sd[1];
            float _t3 = sd[6] - sd[0];
            float _t4 = sd[8] - sd[2];
            float _t5 = sd[5] - sd[2];
            float _t12 = java.lang.Math.fma(_t0, _t1, -(_t2 * _t3));
            float _t13 = java.lang.Math.fma(_t2, _t4, -(_t5 * _t1));
            float _t14 = java.lang.Math.fma(_t5, _t3, -(_t0 * _t4));
            return 0.5f * (float) java.lang.Math.sqrt(java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14)));
        } else {
            float[] sd = this.data;
            float _t0 = sd[3] - sd[0];
            float _t1 = sd[7] - sd[1];
            float _t2 = sd[4] - sd[1];
            float _t3 = sd[6] - sd[0];
            float _t4 = sd[8] - sd[2];
            float _t5 = sd[5] - sd[2];
            float _t12 = ((_t0) * (_t1) - (_t2 * _t3));
            float _t13 = ((_t2) * (_t4) - (_t5 * _t1));
            float _t14 = ((_t5) * (_t3) - (_t0 * _t4));
            return 0.5f * (float) java.lang.Math.sqrt(((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14)))));
        }
    }


    /**
     * Compute the barycentric coordinates of the given point with respect to this triangle and
     * store the result in {@code dest}.
     * <p>
     * The three components weight the triangle's first, second and third vertex respectively and
     * sum to 1. A point that is not coplanar with the triangle yields the coordinates of its
     * orthogonal projection onto the triangle's plane. The weights are formed with the
     * cross-product form (areas of the sub-triangles against the triangle's normal), which stays
     * accurate for thin triangles.
     * <p>
     * Valid input: this triangle must be non-degenerate.
     *
     * @param p the point whose barycentric coordinates to compute
     * @param dest will hold the result
     * @return dest
     */
    public Float3 barycentric(Float3R p, @Mutated Float3 dest) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (Math.useFma()) return barycentric_fma(pX, pY, pZ, dest);
        return barycentric_mulAdd(pX, pY, pZ, dest);
    }


    /**
     * Compute the barycentric coordinates of the given point with respect to this triangle and
     * store the result in {@code dest}.
     * <p>
     * The three components weight the triangle's first, second and third vertex respectively and
     * sum to 1. A point that is not coplanar with the triangle yields the coordinates of its
     * orthogonal projection onto the triangle's plane. The weights are formed with the
     * cross-product form (areas of the sub-triangles against the triangle's normal), which stays
     * accurate for thin triangles.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this triangle must be non-degenerate.
     *
     * @param p the point whose barycentric coordinates to compute
     * @param dest will hold the result
     * @return dest
     */
    public Double3 barycentric(Float3R p, @Mutated Double3 dest) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (Math.useFma()) return barycentric_fma(pX, pY, pZ, dest);
        return barycentric_mulAdd(pX, pY, pZ, dest);
    }


    /**
     * Compute the barycentric coordinates of the given point with respect to this triangle and
     * store the result in {@code dest}.
     * <p>
     * The three components weight the triangle's first, second and third vertex respectively and
     * sum to 1. A point that is not coplanar with the triangle yields the coordinates of its
     * orthogonal projection onto the triangle's plane. The weights are formed with the
     * cross-product form (areas of the sub-triangles against the triangle's normal), which stays
     * accurate for thin triangles.
     * <p>
     * Valid input: this triangle must be non-degenerate.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 barycentric(float pX, float pY, float pZ, @Mutated Float3 dest) {
        if (Math.useFma()) return barycentric_fma(pX, pY, pZ, dest);
        return barycentric_mulAdd(pX, pY, pZ, dest);
    }

    /** {@code barycentric} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 barycentric_fma(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = pX - sd[0];
        float _t1 = sd[7] - sd[1];
        float _t2 = pY - sd[1];
        float _t3 = sd[6] - sd[0];
        float _t4 = sd[3] - sd[0];
        float _t5 = sd[4] - sd[1];
        float _t6 = sd[8] - sd[2];
        float _t7 = pZ - sd[2];
        float _t8 = sd[5] - sd[2];
        float _t28 = java.lang.Math.fma(_t4, _t1, -(_t5 * _t3));
        float _t30 = java.lang.Math.fma(_t5, _t6, -(_t8 * _t1));
        float _t32 = java.lang.Math.fma(_t8, _t3, -(_t4 * _t6));
        float _t46 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t30, _t30, _t32 * _t32));
        if (!(_t46 > 1.1754944E-38f && _t46 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_fma(pX, pY, pZ, dest);
        return barycentric_s2df94b79_1_fma(pX, pY, pZ, dest, dd, _t0, _t2, _t4, _t5, _t7, _t8, _t28, _t30, _t32, 1.0f / _t46, java.lang.Math.fma(java.lang.Math.fma(_t0, _t1, -(_t2 * _t3)), _t28, java.lang.Math.fma(java.lang.Math.fma(_t2, _t6, -(_t7 * _t1)), _t30, java.lang.Math.fma(_t7, _t3, -(_t0 * _t6)) * _t32)));
    }

    /** {@code barycentric} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 barycentric_mulAdd(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = pX - sd[0];
        float _t1 = sd[7] - sd[1];
        float _t2 = pY - sd[1];
        float _t3 = sd[6] - sd[0];
        float _t4 = sd[3] - sd[0];
        float _t5 = sd[4] - sd[1];
        float _t6 = sd[8] - sd[2];
        float _t7 = pZ - sd[2];
        float _t8 = sd[5] - sd[2];
        float _t28 = ((_t4) * (_t1) - (_t5 * _t3));
        float _t30 = ((_t5) * (_t6) - (_t8 * _t1));
        float _t32 = ((_t8) * (_t3) - (_t4 * _t6));
        float _t46 = ((_t28) * (_t28) + (((_t30) * (_t30) + (_t32 * _t32))));
        if (!(_t46 > 1.1754944E-38f && _t46 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_mulAdd(pX, pY, pZ, dest);
        return barycentric_s2df94b79_1_mulAdd(pX, pY, pZ, dest, dd, _t0, _t2, _t4, _t5, _t7, _t8, _t28, _t30, _t32, 1.0f / _t46, ((((_t0) * (_t1) - (_t2 * _t3))) * (_t28) + (((((_t2) * (_t6) - (_t7 * _t1))) * (_t30) + (((_t7) * (_t3) - (_t0 * _t6)) * _t32)))));
    }

    /** Piece 2 of {@code barycentric}, split to fit the inline budget; reached only through it. */
    private Float3 barycentric_s2df94b79_1_fma(float pX, float pY, float pZ, Float3 dest, float[] dd, float _t0, float _t2, float _t4, float _t5, float _t7, float _t8, float _t28, float _t30, float _t32, float _t46_inv, float _ct0) {
        if (!(_ct0 > Float.NEGATIVE_INFINITY && _ct0 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_fma(pX, pY, pZ, dest);
        float _t48 = _ct0 * _t46_inv;
        float _ct1 = java.lang.Math.fma(java.lang.Math.fma(_t2, _t4, -(_t0 * _t5)), _t28, java.lang.Math.fma(java.lang.Math.fma(_t0, _t8, -(_t7 * _t4)), _t32, java.lang.Math.fma(_t7, _t5, -(_t2 * _t8)) * _t30));
        if (!(_ct1 > Float.NEGATIVE_INFINITY && _ct1 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_fma(pX, pY, pZ, dest);
        float _t49 = _ct1 * _t46_inv;
        dd[0] = 1.0f - _t48 - _t49;
        dd[1] = _t48;
        dd[2] = _t49;
        return dest;
    }

    /** Piece 2 of {@code barycentric}, split to fit the inline budget; reached only through it. */
    private Float3 barycentric_s2df94b79_1_mulAdd(float pX, float pY, float pZ, Float3 dest, float[] dd, float _t0, float _t2, float _t4, float _t5, float _t7, float _t8, float _t28, float _t30, float _t32, float _t46_inv, float _ct0) {
        if (!(_ct0 > Float.NEGATIVE_INFINITY && _ct0 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_mulAdd(pX, pY, pZ, dest);
        float _t48 = _ct0 * _t46_inv;
        float _ct1 = ((((_t2) * (_t4) - (_t0 * _t5))) * (_t28) + (((((_t0) * (_t8) - (_t7 * _t4))) * (_t32) + (((_t7) * (_t5) - (_t2 * _t8)) * _t30))));
        if (!(_ct1 > Float.NEGATIVE_INFINITY && _ct1 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_mulAdd(pX, pY, pZ, dest);
        float _t49 = _ct1 * _t46_inv;
        dd[0] = 1.0f - _t48 - _t49;
        dd[1] = _t48;
        dd[2] = _t49;
        return dest;
    }


    /**
     * Compute the barycentric coordinates of the given point with respect to this triangle and
     * store the result in {@code dest}.
     * <p>
     * The three components weight the triangle's first, second and third vertex respectively and
     * sum to 1. A point that is not coplanar with the triangle yields the coordinates of its
     * orthogonal projection onto the triangle's plane. The weights are formed with the
     * cross-product form (areas of the sub-triangles against the triangle's normal), which stays
     * accurate for thin triangles.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this triangle must be non-degenerate.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 barycentric(float pX, float pY, float pZ, @Mutated Double3 dest) {
        if (Math.useFma()) return barycentric_fma(pX, pY, pZ, dest);
        return barycentric_mulAdd(pX, pY, pZ, dest);
    }

    /** {@code barycentric} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 barycentric_fma(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = pX - sd[0];
        float _t1 = sd[7] - sd[1];
        float _t2 = pY - sd[1];
        float _t3 = sd[6] - sd[0];
        float _t4 = sd[3] - sd[0];
        float _t5 = sd[4] - sd[1];
        float _t6 = sd[8] - sd[2];
        float _t7 = pZ - sd[2];
        float _t8 = sd[5] - sd[2];
        float _t28 = java.lang.Math.fma(_t4, _t1, -(_t5 * _t3));
        float _t30 = java.lang.Math.fma(_t5, _t6, -(_t8 * _t1));
        float _t32 = java.lang.Math.fma(_t8, _t3, -(_t4 * _t6));
        float _t46 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t30, _t30, _t32 * _t32));
        if (!(_t46 > 1.1754944E-38f && _t46 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_fma(pX, pY, pZ, dest);
        return barycentric_s65efdde6_1_fma(pX, pY, pZ, dest, dd, _t0, _t2, _t4, _t5, _t7, _t8, _t28, _t30, _t32, 1.0f / _t46, java.lang.Math.fma(java.lang.Math.fma(_t0, _t1, -(_t2 * _t3)), _t28, java.lang.Math.fma(java.lang.Math.fma(_t2, _t6, -(_t7 * _t1)), _t30, java.lang.Math.fma(_t7, _t3, -(_t0 * _t6)) * _t32)));
    }

    /** {@code barycentric} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 barycentric_mulAdd(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = pX - sd[0];
        float _t1 = sd[7] - sd[1];
        float _t2 = pY - sd[1];
        float _t3 = sd[6] - sd[0];
        float _t4 = sd[3] - sd[0];
        float _t5 = sd[4] - sd[1];
        float _t6 = sd[8] - sd[2];
        float _t7 = pZ - sd[2];
        float _t8 = sd[5] - sd[2];
        float _t28 = ((_t4) * (_t1) - (_t5 * _t3));
        float _t30 = ((_t5) * (_t6) - (_t8 * _t1));
        float _t32 = ((_t8) * (_t3) - (_t4 * _t6));
        float _t46 = ((_t28) * (_t28) + (((_t30) * (_t30) + (_t32 * _t32))));
        if (!(_t46 > 1.1754944E-38f && _t46 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_mulAdd(pX, pY, pZ, dest);
        return barycentric_s65efdde6_1_mulAdd(pX, pY, pZ, dest, dd, _t0, _t2, _t4, _t5, _t7, _t8, _t28, _t30, _t32, 1.0f / _t46, ((((_t0) * (_t1) - (_t2 * _t3))) * (_t28) + (((((_t2) * (_t6) - (_t7 * _t1))) * (_t30) + (((_t7) * (_t3) - (_t0 * _t6)) * _t32)))));
    }

    /** Piece 2 of {@code barycentric}, split to fit the inline budget; reached only through it. */
    private Double3 barycentric_s65efdde6_1_fma(float pX, float pY, float pZ, Double3 dest, double[] dd, float _t0, float _t2, float _t4, float _t5, float _t7, float _t8, float _t28, float _t30, float _t32, float _t46_inv, float _ct0) {
        if (!(_ct0 > Float.NEGATIVE_INFINITY && _ct0 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_fma(pX, pY, pZ, dest);
        float _t48 = _ct0 * _t46_inv;
        float _ct1 = java.lang.Math.fma(java.lang.Math.fma(_t2, _t4, -(_t0 * _t5)), _t28, java.lang.Math.fma(java.lang.Math.fma(_t0, _t8, -(_t7 * _t4)), _t32, java.lang.Math.fma(_t7, _t5, -(_t2 * _t8)) * _t30));
        if (!(_ct1 > Float.NEGATIVE_INFINITY && _ct1 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_fma(pX, pY, pZ, dest);
        float _t49 = _ct1 * _t46_inv;
        dd[0] = 1.0f - _t48 - _t49;
        dd[1] = _t48;
        dd[2] = _t49;
        return dest;
    }

    /** Piece 2 of {@code barycentric}, split to fit the inline budget; reached only through it. */
    private Double3 barycentric_s65efdde6_1_mulAdd(float pX, float pY, float pZ, Double3 dest, double[] dd, float _t0, float _t2, float _t4, float _t5, float _t7, float _t8, float _t28, float _t30, float _t32, float _t46_inv, float _ct0) {
        if (!(_ct0 > Float.NEGATIVE_INFINITY && _ct0 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_mulAdd(pX, pY, pZ, dest);
        float _t48 = _ct0 * _t46_inv;
        float _ct1 = ((((_t2) * (_t4) - (_t0 * _t5))) * (_t28) + (((((_t0) * (_t8) - (_t7 * _t4))) * (_t32) + (((_t7) * (_t5) - (_t2 * _t8)) * _t30))));
        if (!(_ct1 > Float.NEGATIVE_INFINITY && _ct1 < Float.POSITIVE_INFINITY)) return barycentric_degenerate_mulAdd(pX, pY, pZ, dest);
        float _t49 = _ct1 * _t46_inv;
        dd[0] = 1.0f - _t48 - _t49;
        dd[1] = _t48;
        dd[2] = _t49;
        return dest;
    }

    /**
     * Out-of-range path of {@code barycentric}: its methods leave here when the squared length of
     * the triangle's normal (quartic in its size) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Float3 barycentric_degenerate_fma(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float _t28 = java.lang.Math.min(1.0f, unitScale(java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[0]), java.lang.Math.abs(sd[1]))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[2]), java.lang.Math.abs(sd[3])))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[4]), java.lang.Math.abs(sd[5]))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[6]), java.lang.Math.abs(sd[7])))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[8]), java.lang.Math.abs(pX))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(pY), java.lang.Math.abs(pZ))))));
        float _t42 = sd[0] * _t28;
        float _t44 = sd[1] * _t28;
        float _t46 = sd[2] * _t28;
        float _t53 = pX * _t28 - _t42;
        float _t54 = pY * _t28 - _t44;
        float _t55 = pZ * _t28 - _t46;
        float _t71 = unitScale(_t53, _t54, _t55);
        return barycentric_degenerate_s7fdf5c50_1_fma(dest, ((Float3Impl) dest).data, sd[7] * _t28 - _t44, sd[3] * _t28 - _t42, sd[4] * _t28 - _t44, sd[5] * _t28 - _t46, sd[6] * _t28 - _t42, sd[8] * _t28 - _t46, _t71, _t53 * _t71, _t54 * _t71, _t55 * _t71);
    }

    /**
     * Out-of-range path of {@code barycentric}: its methods leave here when the squared length of
     * the triangle's normal (quartic in its size) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Float3 barycentric_degenerate_mulAdd(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float _t28 = java.lang.Math.min(1.0f, unitScale(java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[0]), java.lang.Math.abs(sd[1]))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[2]), java.lang.Math.abs(sd[3])))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[4]), java.lang.Math.abs(sd[5]))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[6]), java.lang.Math.abs(sd[7])))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[8]), java.lang.Math.abs(pX))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(pY), java.lang.Math.abs(pZ))))));
        float _t42 = sd[0] * _t28;
        float _t44 = sd[1] * _t28;
        float _t46 = sd[2] * _t28;
        float _t53 = pX * _t28 - _t42;
        float _t54 = pY * _t28 - _t44;
        float _t55 = pZ * _t28 - _t46;
        float _t71 = unitScale(_t53, _t54, _t55);
        return barycentric_degenerate_s7fdf5c50_1_mulAdd(dest, ((Float3Impl) dest).data, sd[7] * _t28 - _t44, sd[3] * _t28 - _t42, sd[4] * _t28 - _t44, sd[5] * _t28 - _t46, sd[6] * _t28 - _t42, sd[8] * _t28 - _t46, _t71, _t53 * _t71, _t54 * _t71, _t55 * _t71);
    }

    /** Piece 2 of {@code barycentric_degenerate}, split to fit the inline budget; reached only through it. */
    private Float3 barycentric_degenerate_s7fdf5c50_1_fma(Float3 dest, float[] dd, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t71, float _t75, float _t76, float _t77) {
        float _t78 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t57), java.lang.Math.abs(_t58)), java.lang.Math.max(java.lang.Math.abs(_t59), java.lang.Math.abs(_t60)), java.lang.Math.max(java.lang.Math.abs(_t56), java.lang.Math.abs(_t61)));
        float _t85 = _t56 * _t78;
        float _t86 = _t60 * _t78;
        float _t87 = _t57 * _t78;
        float _t88 = _t58 * _t78;
        float _t89 = _t61 * _t78;
        float _t90 = _t59 * _t78;
        float _t117 = java.lang.Math.fma(_t87, _t85, -(_t88 * _t86));
        float _t118 = java.lang.Math.fma(_t88, _t89, -(_t90 * _t85));
        float _t119 = java.lang.Math.fma(_t90, _t86, -(_t87 * _t89));
        float _sp0 = _t78 / _t71 / java.lang.Math.fma(_t117, _t117, java.lang.Math.fma(_t118, _t118, _t119 * _t119));
        float _t131 = java.lang.Math.fma(java.lang.Math.fma(_t75, _t85, -(_t76 * _t86)), _t117, java.lang.Math.fma(java.lang.Math.fma(_t76, _t89, -(_t77 * _t85)), _t118, java.lang.Math.fma(_t77, _t86, -(_t75 * _t89)) * _t119)) * _sp0;
        float _t132 = java.lang.Math.fma(java.lang.Math.fma(_t76, _t87, -(_t75 * _t88)), _t117, java.lang.Math.fma(java.lang.Math.fma(_t75, _t90, -(_t77 * _t87)), _t119, java.lang.Math.fma(_t77, _t88, -(_t76 * _t90)) * _t118)) * _sp0;
        dd[0] = 1.0f - _t131 - _t132;
        dd[1] = _t131;
        dd[2] = _t132;
        return dest;
    }

    /** Piece 2 of {@code barycentric_degenerate}, split to fit the inline budget; reached only through it. */
    private Float3 barycentric_degenerate_s7fdf5c50_1_mulAdd(Float3 dest, float[] dd, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t71, float _t75, float _t76, float _t77) {
        float _t78 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t57), java.lang.Math.abs(_t58)), java.lang.Math.max(java.lang.Math.abs(_t59), java.lang.Math.abs(_t60)), java.lang.Math.max(java.lang.Math.abs(_t56), java.lang.Math.abs(_t61)));
        float _t85 = _t56 * _t78;
        float _t86 = _t60 * _t78;
        float _t87 = _t57 * _t78;
        float _t88 = _t58 * _t78;
        float _t89 = _t61 * _t78;
        float _t90 = _t59 * _t78;
        float _t117 = ((_t87) * (_t85) - (_t88 * _t86));
        float _t118 = ((_t88) * (_t89) - (_t90 * _t85));
        float _t119 = ((_t90) * (_t86) - (_t87 * _t89));
        float _sp0 = _t78 / _t71 / ((_t117) * (_t117) + (((_t118) * (_t118) + (_t119 * _t119))));
        float _t131 = ((((_t75) * (_t85) - (_t76 * _t86))) * (_t117) + (((((_t76) * (_t89) - (_t77 * _t85))) * (_t118) + (((_t77) * (_t86) - (_t75 * _t89)) * _t119)))) * _sp0;
        float _t132 = ((((_t76) * (_t87) - (_t75 * _t88))) * (_t117) + (((((_t75) * (_t90) - (_t77 * _t87))) * (_t119) + (((_t77) * (_t88) - (_t76 * _t90)) * _t118)))) * _sp0;
        dd[0] = 1.0f - _t131 - _t132;
        dd[1] = _t131;
        dd[2] = _t132;
        return dest;
    }

    /**
     * Out-of-range path of {@code barycentric}: its methods leave here when the squared length of
     * the triangle's normal (quartic in its size) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Double3 barycentric_degenerate_fma(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        float _t28 = java.lang.Math.min(1.0f, unitScale(java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[0]), java.lang.Math.abs(sd[1]))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[2]), java.lang.Math.abs(sd[3])))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[4]), java.lang.Math.abs(sd[5]))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[6]), java.lang.Math.abs(sd[7])))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[8]), java.lang.Math.abs(pX))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(pY), java.lang.Math.abs(pZ))))));
        float _t42 = sd[0] * _t28;
        float _t44 = sd[1] * _t28;
        float _t46 = sd[2] * _t28;
        float _t53 = pX * _t28 - _t42;
        float _t54 = pY * _t28 - _t44;
        float _t55 = pZ * _t28 - _t46;
        float _t71 = unitScale(_t53, _t54, _t55);
        return barycentric_degenerate_sf3374b25_1_fma(dest, ((Double3Impl) dest).data, sd[7] * _t28 - _t44, sd[3] * _t28 - _t42, sd[4] * _t28 - _t44, sd[5] * _t28 - _t46, sd[6] * _t28 - _t42, sd[8] * _t28 - _t46, _t71, _t53 * _t71, _t54 * _t71, _t55 * _t71);
    }

    /**
     * Out-of-range path of {@code barycentric}: its methods leave here when the squared length of
     * the triangle's normal (quartic in its size) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Double3 barycentric_degenerate_mulAdd(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        float _t28 = java.lang.Math.min(1.0f, unitScale(java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[0]), java.lang.Math.abs(sd[1]))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[2]), java.lang.Math.abs(sd[3])))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[4]), java.lang.Math.abs(sd[5]))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[6]), java.lang.Math.abs(sd[7])))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(sd[8]), java.lang.Math.abs(pX))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(pY), java.lang.Math.abs(pZ))))));
        float _t42 = sd[0] * _t28;
        float _t44 = sd[1] * _t28;
        float _t46 = sd[2] * _t28;
        float _t53 = pX * _t28 - _t42;
        float _t54 = pY * _t28 - _t44;
        float _t55 = pZ * _t28 - _t46;
        float _t71 = unitScale(_t53, _t54, _t55);
        return barycentric_degenerate_sf3374b25_1_mulAdd(dest, ((Double3Impl) dest).data, sd[7] * _t28 - _t44, sd[3] * _t28 - _t42, sd[4] * _t28 - _t44, sd[5] * _t28 - _t46, sd[6] * _t28 - _t42, sd[8] * _t28 - _t46, _t71, _t53 * _t71, _t54 * _t71, _t55 * _t71);
    }

    /** Piece 2 of {@code barycentric_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 barycentric_degenerate_sf3374b25_1_fma(Double3 dest, double[] dd, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t71, float _t75, float _t76, float _t77) {
        float _t78 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t57), java.lang.Math.abs(_t58)), java.lang.Math.max(java.lang.Math.abs(_t59), java.lang.Math.abs(_t60)), java.lang.Math.max(java.lang.Math.abs(_t56), java.lang.Math.abs(_t61)));
        float _t85 = _t56 * _t78;
        float _t86 = _t60 * _t78;
        float _t87 = _t57 * _t78;
        float _t88 = _t58 * _t78;
        float _t89 = _t61 * _t78;
        float _t90 = _t59 * _t78;
        float _t117 = java.lang.Math.fma(_t87, _t85, -(_t88 * _t86));
        float _t118 = java.lang.Math.fma(_t88, _t89, -(_t90 * _t85));
        float _t119 = java.lang.Math.fma(_t90, _t86, -(_t87 * _t89));
        float _sp0 = _t78 / _t71 / java.lang.Math.fma(_t117, _t117, java.lang.Math.fma(_t118, _t118, _t119 * _t119));
        float _t131 = java.lang.Math.fma(java.lang.Math.fma(_t75, _t85, -(_t76 * _t86)), _t117, java.lang.Math.fma(java.lang.Math.fma(_t76, _t89, -(_t77 * _t85)), _t118, java.lang.Math.fma(_t77, _t86, -(_t75 * _t89)) * _t119)) * _sp0;
        float _t132 = java.lang.Math.fma(java.lang.Math.fma(_t76, _t87, -(_t75 * _t88)), _t117, java.lang.Math.fma(java.lang.Math.fma(_t75, _t90, -(_t77 * _t87)), _t119, java.lang.Math.fma(_t77, _t88, -(_t76 * _t90)) * _t118)) * _sp0;
        dd[0] = 1.0f - _t131 - _t132;
        dd[1] = _t131;
        dd[2] = _t132;
        return dest;
    }

    /** Piece 2 of {@code barycentric_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 barycentric_degenerate_sf3374b25_1_mulAdd(Double3 dest, double[] dd, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t71, float _t75, float _t76, float _t77) {
        float _t78 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t57), java.lang.Math.abs(_t58)), java.lang.Math.max(java.lang.Math.abs(_t59), java.lang.Math.abs(_t60)), java.lang.Math.max(java.lang.Math.abs(_t56), java.lang.Math.abs(_t61)));
        float _t85 = _t56 * _t78;
        float _t86 = _t60 * _t78;
        float _t87 = _t57 * _t78;
        float _t88 = _t58 * _t78;
        float _t89 = _t61 * _t78;
        float _t90 = _t59 * _t78;
        float _t117 = ((_t87) * (_t85) - (_t88 * _t86));
        float _t118 = ((_t88) * (_t89) - (_t90 * _t85));
        float _t119 = ((_t90) * (_t86) - (_t87 * _t89));
        float _sp0 = _t78 / _t71 / ((_t117) * (_t117) + (((_t118) * (_t118) + (_t119 * _t119))));
        float _t131 = ((((_t75) * (_t85) - (_t76 * _t86))) * (_t117) + (((((_t76) * (_t89) - (_t77 * _t85))) * (_t118) + (((_t77) * (_t86) - (_t75 * _t89)) * _t119)))) * _sp0;
        float _t132 = ((((_t76) * (_t87) - (_t75 * _t88))) * (_t117) + (((((_t75) * (_t90) - (_t77 * _t87))) * (_t119) + (((_t77) * (_t88) - (_t76 * _t90)) * _t118)))) * _sp0;
        dd[0] = 1.0f - _t131 - _t132;
        dd[1] = _t131;
        dd[2] = _t132;
        return dest;
    }


    /**
     * Get the centroid (the average of the vertices) of this triangle and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getCentroid(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = 0.33333334f * sd[0] + 0.33333334f * sd[3] + 0.33333334f * sd[6];
        dd[1] = 0.33333334f * sd[1] + 0.33333334f * sd[4] + 0.33333334f * sd[7];
        dd[2] = 0.33333334f * sd[2] + 0.33333334f * sd[5] + 0.33333334f * sd[8];
        return dest;
    }


    /**
     * Get the centroid (the average of the vertices) of this triangle and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getCentroid(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = 0.33333334f * sd[0] + 0.33333334f * sd[3] + 0.33333334f * sd[6];
        dd[1] = 0.33333334f * sd[1] + 0.33333334f * sd[4] + 0.33333334f * sd[7];
        dd[2] = 0.33333334f * sd[2] + 0.33333334f * sd[5] + 0.33333334f * sd[8];
        return dest;
    }


    /**
     * Get the normal of this triangle, i.e. the cross product {@code (v1 - v0) x (v2 - v0)} (not
     * unit length: its length is twice the triangle's area) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getNormal(@Mutated Float3 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            float _t0 = sd[4] - sd[1];
            float _t1 = sd[8] - sd[2];
            float _t2 = sd[5] - sd[2];
            float _t3 = sd[7] - sd[1];
            float _t4 = sd[6] - sd[0];
            float _t5 = sd[3] - sd[0];
            dd[0] = java.lang.Math.fma(_t0, _t1, -(_t2 * _t3));
            dd[1] = java.lang.Math.fma(_t2, _t4, -(_t5 * _t1));
            dd[2] = java.lang.Math.fma(_t5, _t3, -(_t0 * _t4));
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float3Impl) dest).data;
            float _t0 = sd[4] - sd[1];
            float _t1 = sd[8] - sd[2];
            float _t2 = sd[5] - sd[2];
            float _t3 = sd[7] - sd[1];
            float _t4 = sd[6] - sd[0];
            float _t5 = sd[3] - sd[0];
            dd[0] = ((_t0) * (_t1) - (_t2 * _t3));
            dd[1] = ((_t2) * (_t4) - (_t5 * _t1));
            dd[2] = ((_t5) * (_t3) - (_t0 * _t4));
            return dest;
        }
    }


    /**
     * Get the normal of this triangle, i.e. the cross product {@code (v1 - v0) x (v2 - v0)} (not
     * unit length: its length is twice the triangle's area) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getNormal(@Mutated Double3 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            float _t0 = sd[4] - sd[1];
            float _t1 = sd[8] - sd[2];
            float _t2 = sd[5] - sd[2];
            float _t3 = sd[7] - sd[1];
            float _t4 = sd[6] - sd[0];
            float _t5 = sd[3] - sd[0];
            dd[0] = java.lang.Math.fma(_t0, _t1, -(_t2 * _t3));
            dd[1] = java.lang.Math.fma(_t2, _t4, -(_t5 * _t1));
            dd[2] = java.lang.Math.fma(_t5, _t3, -(_t0 * _t4));
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double3Impl) dest).data;
            float _t0 = sd[4] - sd[1];
            float _t1 = sd[8] - sd[2];
            float _t2 = sd[5] - sd[2];
            float _t3 = sd[7] - sd[1];
            float _t4 = sd[6] - sd[0];
            float _t5 = sd[3] - sd[0];
            dd[0] = ((_t0) * (_t1) - (_t2 * _t3));
            dd[1] = ((_t2) * (_t4) - (_t5 * _t1));
            dd[2] = ((_t5) * (_t3) - (_t0 * _t4));
            return dest;
        }
    }


    /**
     * Get the first vertex of this triangle and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getV0(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
    }


    /**
     * Get the first vertex of this triangle and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getV0(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
    }


    /**
     * Get the second vertex of this triangle and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getV1(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[3];
        dd[1] = sd[4];
        dd[2] = sd[5];
        return dest;
    }


    /**
     * Get the second vertex of this triangle and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getV1(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[3];
        dd[1] = sd[4];
        dd[2] = sd[5];
        return dest;
    }


    /**
     * Get the third vertex of this triangle and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getV2(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[6];
        dd[1] = sd[7];
        dd[2] = sd[8];
        return dest;
    }


    /**
     * Get the third vertex of this triangle and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getV2(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[6];
        dd[1] = sd[7];
        dd[2] = sd[8];
        return dest;
    }

    public float v0X() { return data[0]; }
    public float v0Y() { return data[1]; }
    public float v0Z() { return data[2]; }
    public float v1X() { return data[3]; }
    public float v1Y() { return data[4]; }
    public float v1Z() { return data[5]; }
    public float v2X() { return data[6]; }
    public float v2Y() { return data[7]; }
    public float v2Z() { return data[8]; }

    @Override public String toString() {
        return "FloatTriangle(" + v0X() + ", " + v0Y() + ", " + v0Z() + ", " + v1X() + ", " + v1Y() + ", " + v1Z() + ", " + v2X() + ", " + v2Y() + ", " + v2Z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatTriangleImpl)) return false;
        FloatTriangleImpl o = (FloatTriangleImpl) obj;
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
            && Float.isFinite(data[5])
            && Float.isFinite(data[6])
            && Float.isFinite(data[7])
            && Float.isFinite(data[8]);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(data[0])
            || Float.isNaN(data[1])
            || Float.isNaN(data[2])
            || Float.isNaN(data[3])
            || Float.isNaN(data[4])
            || Float.isNaN(data[5])
            || Float.isNaN(data[6])
            || Float.isNaN(data[7])
            || Float.isNaN(data[8]);
    }

    @Override public boolean equalsEpsilon(FloatTriangleR other, float epsilon) {
        return java.lang.Math.abs(data[0] - other.v0X()) <= epsilon
            && java.lang.Math.abs(data[1] - other.v0Y()) <= epsilon
            && java.lang.Math.abs(data[2] - other.v0Z()) <= epsilon
            && java.lang.Math.abs(data[3] - other.v1X()) <= epsilon
            && java.lang.Math.abs(data[4] - other.v1Y()) <= epsilon
            && java.lang.Math.abs(data[5] - other.v1Z()) <= epsilon
            && java.lang.Math.abs(data[6] - other.v2X()) <= epsilon
            && java.lang.Math.abs(data[7] - other.v2Y()) <= epsilon
            && java.lang.Math.abs(data[8] - other.v2Z()) <= epsilon;
    }

    public boolean containsPoint(float pX, float pY, float pZ) {
        return Intersectionf.testPointInTriangle(pX, pY, pZ, v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z());
    }

    public boolean containsPoint(Float3R p) {
        return containsPoint(p.x(), p.y(), p.z());
    }

    public boolean intersectsRay(FloatRayR ray, float epsilon) {
        return Intersectionf.testRayTriangle(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), epsilon);
    }

    public boolean intersectsRayFront(FloatRayR ray, float epsilon) {
        return Intersectionf.testRayTriangleFront(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), epsilon);
    }

    public Float3 closestPointToPoint(float pX, float pY, float pZ, @Mutated Float3 dest) {
        Intersectionf.findClosestPointOnTriangle(v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), pX, pY, pZ, dest);
        return dest;
    }

    public Float3 closestPointToPoint(Float3R p, @Mutated Float3 dest) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        Intersectionf.findClosestPointOnTriangle(v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), pX, pY, pZ, dest);
        return dest;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        dest[offset + 6] = this.data[6];
        dest[offset + 7] = this.data[7];
        dest[offset + 8] = this.data[8];
        return dest;
    }
    public @Mutated FloatTriangle load(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
        this.data[7] = src[offset + 7];
        this.data[8] = src[offset + 8];
        return this;
    }
    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }
    @Mutated public FloatTriangle load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatTriangle loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatTriangle loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 9);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return buf;
    }
    public FloatTriangle load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public FloatTriangle loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public FloatTriangle loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatTriangle r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return r;
    }
    public FloatTriangle storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public FloatTriangle loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public FloatTriangle load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public FloatTriangle load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        dest[offset + 6] = this.data[6];
        dest[offset + 7] = this.data[7];
        dest[offset + 8] = this.data[8];
        return dest;
    }
    public @Mutated FloatTriangle load(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        this.data[4] = (float) src[offset + 4];
        this.data[5] = (float) src[offset + 5];
        this.data[6] = (float) src[offset + 6];
        this.data[7] = (float) src[offset + 7];
        this.data[8] = (float) src[offset + 8];
        return this;
    }
    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }
    @Mutated public FloatTriangle load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatTriangle loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatTriangle loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 9);
        return this;
    }
    public ByteBuffer storeDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 72);
        return buf;
    }
    public FloatTriangle loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }
    public FloatTriangle loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public FloatTriangle loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatTriangle r = StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 72);
        return r;
    }
    public FloatTriangle storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public FloatTriangle loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }
    public MemorySegment storeDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }
    public MemorySegment storeDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }
    @Mutated public FloatTriangle loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(this, 0L, src); }
    public FloatTriangle loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(this, offset, src);
    }

    /**
     * The power of two that brings max(|a|, |b|, |c|) into [1, 2), from the largest exponent
     * field: multiplying by it is exact. Clamped to [2^-126, 2^126], so zero and subnormal
     * values scale up without overflow and the largest floats land in [2, 4).
     */
    private static float unitScale(float a, float b, float c) {
        int e = java.lang.Math.max(java.lang.Math.max(Float.floatToRawIntBits(a) & 0x7F800000,
                Float.floatToRawIntBits(b) & 0x7F800000), Float.floatToRawIntBits(c) & 0x7F800000);
        return Float.intBitsToFloat(0x7F000000 - java.lang.Math.min(java.lang.Math.max(e, 0x00800000), 0x7E800000));
    }

    /**
     * The power of two that brings max(|a|, |b|, |c|) into [1, 2), from the largest exponent
     * field: multiplying by it is exact. Clamped to [2^-1022, 2^1022], so zero and subnormal
     * values scale up without overflow and the largest doubles land in [2, 4).
     */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }
}
