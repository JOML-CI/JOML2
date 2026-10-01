// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link FloatSphere} backed by a {@code float[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatSphereImpl implements FloatSphere {

    public float[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatSphereBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatSphereBbOpsUnsafe()
                        : new FloatSphereBbOpsApi();
        static final FloatSphereRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatSphereRawOpsUnsafe()
                        : new FloatSphereRawOpsApi();
    }

    public FloatSphereImpl() {
        data = new float[4];
    }

    public FloatSphereImpl(float x, float y, float z, float r) {
        float[] dd = this.data = new float[4];
        dd[0] = x;
        dd[1] = y;
        dd[2] = z;
        dd[3] = r;
    }

    public FloatSphereImpl(FloatSphereR src) {
        float[] dd = this.data = new float[4];
        dd[0] = src.x();
        dd[1] = src.y();
        dd[2] = src.z();
        dd[3] = src.r();
    }


    /**
     * Set this sphere to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the sphere to copy
     * @return this
     */
    public @Mutated FloatSphere set(FloatSphereR v) {
        float vY = v.y();
        float vZ = v.z();
        float vR = v.r();
        float[] dd = this.data;
        dd[0] = v.x();
        dd[1] = vY;
        dd[2] = vZ;
        dd[3] = vR;
        return this;
    }


    /**
     * Set this sphere to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the sphere {@code (vX, vY, vZ, vR)}
     * @param vY the {@code y} component of the sphere {@code (vX, vY, vZ, vR)}
     * @param vZ the {@code z} component of the sphere {@code (vX, vY, vZ, vR)}
     * @param vR the {@code r} component of the sphere {@code (vX, vY, vZ, vR)}
     * @return this
     */
    @Mutated public FloatSphere set(float vX, float vY, float vZ, float vR) {
        float[] dd = this.data;
        dd[0] = vX;
        dd[1] = vY;
        dd[2] = vZ;
        dd[3] = vR;
        return this;
    }


    /**
     * Set the center of this sphere to {@code c} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param c the new center
     * @param dest will hold the result
     * @return dest
     */
    public FloatSphere setCenter(Float3R c, @Mutated FloatSphere dest) {
        float cY = c.y();
        float cZ = c.z();
        float[] sd = this.data;
        float[] dd = ((FloatSphereImpl) dest).data;
        dd[0] = c.x();
        dd[1] = cY;
        dd[2] = cZ;
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Set the center of this sphere to {@code c} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param c the new center
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere setCenter(Float3R c, @Mutated DoubleSphere dest) {
        float cY = c.y();
        float cZ = c.z();
        float[] sd = this.data;
        double[] dd = ((DoubleSphereImpl) dest).data;
        dd[0] = c.x();
        dd[1] = cY;
        dd[2] = cZ;
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Set the center of this sphere to ({@code cX}, {@code cY}, {@code cZ}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatSphere setCenter(float cX, float cY, float cZ, @Mutated FloatSphere dest) {
        float[] sd = this.data;
        float[] dd = ((FloatSphereImpl) dest).data;
        dd[0] = cX;
        dd[1] = cY;
        dd[2] = cZ;
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Set the center of this sphere to ({@code cX}, {@code cY}, {@code cZ}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere setCenter(float cX, float cY, float cZ, @Mutated DoubleSphere dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleSphereImpl) dest).data;
        dd[0] = cX;
        dd[1] = cY;
        dd[2] = cZ;
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Set the radius of this sphere to {@code radius} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code radius} must not be negative.
     *
     * @param radius the radius
     * @param dest will hold the result
     * @return dest
     */
    public FloatSphere setRadius(float radius, @Mutated FloatSphere dest) {
        float[] sd = this.data;
        float[] dd = ((FloatSphereImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = radius;
        return dest;
    }


    /**
     * Set the radius of this sphere to {@code radius} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code radius} must not be negative.
     *
     * @param radius the radius
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere setRadius(float radius, @Mutated DoubleSphere dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleSphereImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = radius;
        return dest;
    }


    /**
     * Convert this sphere to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere toDouble(@Mutated DoubleSphere dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleSphereImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Transform this sphere by {@code m}, scaling the radius by an upper bound on the matrix's
     * largest stretch so that the result contains the transformed sphere and store the result in
     * {@code dest}.
     * <p>
     * The radius factor is the square root of the largest absolute row sum of {@code M^T M} over
     * the upper-left 3x3: exact for a rotation combined with any axis scale, and at most about 1.17
     * times too large under shear. (The largest column length would be no bound once the scale is
     * applied after the rotation.) The matrix is taken as affine: its last row is not read.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public FloatSphere transform(Float3x4R m, @Mutated FloatSphere dest) {
        if (Math.useFma()) return transform_fma(m, dest);
        return transform_mulAdd(m, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatSphere transform_fma(Float3x4R m, @Mutated FloatSphere dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        return transform_sf8fcce86_1_fma(dest, sd, mData, ((FloatSphereImpl) dest).data, java.lang.Math.abs(java.lang.Math.fma(mData[8], mData[9], java.lang.Math.fma(mData[0], mData[1], mData[4] * mData[5]))), java.lang.Math.abs(java.lang.Math.fma(mData[8], mData[10], java.lang.Math.fma(mData[0], mData[2], mData[4] * mData[6]))), java.lang.Math.abs(java.lang.Math.fma(mData[9], mData[10], java.lang.Math.fma(mData[1], mData[2], mData[5] * mData[6]))), mData[0], mData[1], mData[2], mData[4], mData[5], mData[6], mData[8], mData[9], mData[10], sd[0], sd[1], sd[2]);
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatSphere transform_mulAdd(Float3x4R m, @Mutated FloatSphere dest) {
        float[] sd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        return transform_sf8fcce86_1_mulAdd(dest, sd, mData, ((FloatSphereImpl) dest).data, java.lang.Math.abs(((mData[8]) * (mData[9]) + (((mData[0]) * (mData[1]) + (mData[4] * mData[5]))))), java.lang.Math.abs(((mData[8]) * (mData[10]) + (((mData[0]) * (mData[2]) + (mData[4] * mData[6]))))), java.lang.Math.abs(((mData[9]) * (mData[10]) + (((mData[1]) * (mData[2]) + (mData[5] * mData[6]))))), mData[0], mData[1], mData[2], mData[4], mData[5], mData[6], mData[8], mData[9], mData[10], sd[0], sd[1], sd[2]);
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatSphere transform_sf8fcce86_1_fma(FloatSphere dest, float[] sd, float[] mData, float[] dd, float _t9, float _t10, float _t11, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7, float _rd8, float _rd9, float _rd10, float _rd11) {
        dd[0] = java.lang.Math.fma(_rd2, _rd11, java.lang.Math.fma(_rd0, _rd9, java.lang.Math.fma(_rd1, _rd10, mData[3])));
        dd[1] = java.lang.Math.fma(_rd5, _rd11, java.lang.Math.fma(_rd3, _rd9, java.lang.Math.fma(_rd4, _rd10, mData[7])));
        dd[2] = java.lang.Math.fma(_rd8, _rd11, java.lang.Math.fma(_rd6, _rd9, java.lang.Math.fma(_rd7, _rd10, mData[11])));
        dd[3] = sd[3] * (float) java.lang.Math.sqrt(java.lang.Math.max(java.lang.Math.max(java.lang.Math.fma(_rd0, _rd0, java.lang.Math.fma(_rd3, _rd3, java.lang.Math.fma(_rd6, _rd6, _t9 + _t10))), java.lang.Math.fma(_rd1, _rd1, java.lang.Math.fma(_rd4, _rd4, java.lang.Math.fma(_rd7, _rd7, _t9 + _t11)))), java.lang.Math.fma(_rd2, _rd2, java.lang.Math.fma(_rd5, _rd5, java.lang.Math.fma(_rd8, _rd8, _t10 + _t11)))));
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatSphere transform_sf8fcce86_1_mulAdd(FloatSphere dest, float[] sd, float[] mData, float[] dd, float _t9, float _t10, float _t11, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7, float _rd8, float _rd9, float _rd10, float _rd11) {
        dd[0] = ((_rd2) * (_rd11) + (((_rd0) * (_rd9) + (((_rd1) * (_rd10) + (mData[3]))))));
        dd[1] = ((_rd5) * (_rd11) + (((_rd3) * (_rd9) + (((_rd4) * (_rd10) + (mData[7]))))));
        dd[2] = ((_rd8) * (_rd11) + (((_rd6) * (_rd9) + (((_rd7) * (_rd10) + (mData[11]))))));
        dd[3] = sd[3] * (float) java.lang.Math.sqrt(java.lang.Math.max(java.lang.Math.max(((_rd0) * (_rd0) + (((_rd3) * (_rd3) + (((_rd6) * (_rd6) + (_t9 + _t10)))))), ((_rd1) * (_rd1) + (((_rd4) * (_rd4) + (((_rd7) * (_rd7) + (_t9 + _t11))))))), ((_rd2) * (_rd2) + (((_rd5) * (_rd5) + (((_rd8) * (_rd8) + (_t10 + _t11))))))));
        return dest;
    }


    /**
     * Transform this sphere by {@code m}, scaling the radius by an upper bound on the matrix's
     * largest stretch so that the result contains the transformed sphere and store the result in
     * {@code dest}.
     * <p>
     * The radius factor is the square root of the largest absolute row sum of {@code M^T M} over
     * the upper-left 3x3: exact for a rotation combined with any axis scale, and at most about 1.17
     * times too large under shear. (The largest column length would be no bound once the scale is
     * applied after the rotation.) The matrix is taken as affine: its last row is not read.
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
    public DoubleSphere transform(Float3x4R m, @Mutated DoubleSphere dest) {
        if (Math.useFma()) {
            float[] mData = ((Float3x4Impl) m).data;
            return transform_sbd7b35c1_1_fma(dest, this.data, mData, ((DoubleSphereImpl) dest).data, java.lang.Math.abs(java.lang.Math.fma(mData[8], mData[9], java.lang.Math.fma(mData[0], mData[1], mData[4] * mData[5]))), java.lang.Math.abs(java.lang.Math.fma(mData[8], mData[10], java.lang.Math.fma(mData[0], mData[2], mData[4] * mData[6]))), java.lang.Math.abs(java.lang.Math.fma(mData[9], mData[10], java.lang.Math.fma(mData[1], mData[2], mData[5] * mData[6]))));
        } else {
            float[] mData = ((Float3x4Impl) m).data;
            return transform_sbd7b35c1_1_mulAdd(dest, this.data, mData, ((DoubleSphereImpl) dest).data, java.lang.Math.abs(((mData[8]) * (mData[9]) + (((mData[0]) * (mData[1]) + (mData[4] * mData[5]))))), java.lang.Math.abs(((mData[8]) * (mData[10]) + (((mData[0]) * (mData[2]) + (mData[4] * mData[6]))))), java.lang.Math.abs(((mData[9]) * (mData[10]) + (((mData[1]) * (mData[2]) + (mData[5] * mData[6]))))));
        }
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleSphere transform_sbd7b35c1_1_fma(DoubleSphere dest, float[] sd, float[] mData, double[] dd, float _t9, float _t10, float _t11) {
        dd[0] = java.lang.Math.fma(mData[2], sd[2], java.lang.Math.fma(mData[0], sd[0], java.lang.Math.fma(mData[1], sd[1], mData[3])));
        dd[1] = java.lang.Math.fma(mData[6], sd[2], java.lang.Math.fma(mData[4], sd[0], java.lang.Math.fma(mData[5], sd[1], mData[7])));
        dd[2] = java.lang.Math.fma(mData[10], sd[2], java.lang.Math.fma(mData[8], sd[0], java.lang.Math.fma(mData[9], sd[1], mData[11])));
        dd[3] = sd[3] * (float) java.lang.Math.sqrt(java.lang.Math.max(java.lang.Math.max(java.lang.Math.fma(mData[0], mData[0], java.lang.Math.fma(mData[4], mData[4], java.lang.Math.fma(mData[8], mData[8], _t9 + _t10))), java.lang.Math.fma(mData[1], mData[1], java.lang.Math.fma(mData[5], mData[5], java.lang.Math.fma(mData[9], mData[9], _t9 + _t11)))), java.lang.Math.fma(mData[2], mData[2], java.lang.Math.fma(mData[6], mData[6], java.lang.Math.fma(mData[10], mData[10], _t10 + _t11)))));
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleSphere transform_sbd7b35c1_1_mulAdd(DoubleSphere dest, float[] sd, float[] mData, double[] dd, float _t9, float _t10, float _t11) {
        dd[0] = ((mData[2]) * (sd[2]) + (((mData[0]) * (sd[0]) + (((mData[1]) * (sd[1]) + (mData[3]))))));
        dd[1] = ((mData[6]) * (sd[2]) + (((mData[4]) * (sd[0]) + (((mData[5]) * (sd[1]) + (mData[7]))))));
        dd[2] = ((mData[10]) * (sd[2]) + (((mData[8]) * (sd[0]) + (((mData[9]) * (sd[1]) + (mData[11]))))));
        dd[3] = sd[3] * (float) java.lang.Math.sqrt(java.lang.Math.max(java.lang.Math.max(((mData[0]) * (mData[0]) + (((mData[4]) * (mData[4]) + (((mData[8]) * (mData[8]) + (_t9 + _t10)))))), ((mData[1]) * (mData[1]) + (((mData[5]) * (mData[5]) + (((mData[9]) * (mData[9]) + (_t9 + _t11))))))), ((mData[2]) * (mData[2]) + (((mData[6]) * (mData[6]) + (((mData[10]) * (mData[10]) + (_t10 + _t11))))))));
        return dest;
    }


    /**
     * Transform this sphere by {@code m}, scaling the radius by an upper bound on the matrix's
     * largest stretch so that the result contains the transformed sphere and store the result in
     * {@code dest}.
     * <p>
     * The radius factor is the square root of the largest absolute row sum of {@code M^T M} over
     * the upper-left 3x3: exact for a rotation combined with any axis scale, and at most about 1.17
     * times too large under shear. (The largest column length would be no bound once the scale is
     * applied after the rotation.) The matrix is taken as affine: its last row is not read.
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
    public FloatSphere transform(Float4x4R m, @Mutated FloatSphere dest) {
        if (Math.useFma()) return transform_fma(m, dest);
        return transform_mulAdd(m, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatSphere transform_fma(Float4x4R m, @Mutated FloatSphere dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        return transform_sfe021ce7_1_fma(dest, sd, mData, ((FloatSphereImpl) dest).data, java.lang.Math.abs(java.lang.Math.fma(mData[2], mData[6], java.lang.Math.fma(mData[0], mData[4], mData[1] * mData[5]))), java.lang.Math.abs(java.lang.Math.fma(mData[2], mData[10], java.lang.Math.fma(mData[0], mData[8], mData[1] * mData[9]))), java.lang.Math.abs(java.lang.Math.fma(mData[6], mData[10], java.lang.Math.fma(mData[4], mData[8], mData[5] * mData[9]))), mData[0], mData[1], mData[2], mData[4], mData[5], mData[6], mData[8], mData[9], mData[10], sd[0], sd[1], sd[2]);
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatSphere transform_mulAdd(Float4x4R m, @Mutated FloatSphere dest) {
        float[] sd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        return transform_sfe021ce7_1_mulAdd(dest, sd, mData, ((FloatSphereImpl) dest).data, java.lang.Math.abs(((mData[2]) * (mData[6]) + (((mData[0]) * (mData[4]) + (mData[1] * mData[5]))))), java.lang.Math.abs(((mData[2]) * (mData[10]) + (((mData[0]) * (mData[8]) + (mData[1] * mData[9]))))), java.lang.Math.abs(((mData[6]) * (mData[10]) + (((mData[4]) * (mData[8]) + (mData[5] * mData[9]))))), mData[0], mData[1], mData[2], mData[4], mData[5], mData[6], mData[8], mData[9], mData[10], sd[0], sd[1], sd[2]);
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatSphere transform_sfe021ce7_1_fma(FloatSphere dest, float[] sd, float[] mData, float[] dd, float _t9, float _t10, float _t11, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7, float _rd8, float _rd9, float _rd10, float _rd11) {
        dd[0] = java.lang.Math.fma(_rd6, _rd11, java.lang.Math.fma(_rd0, _rd9, java.lang.Math.fma(_rd3, _rd10, mData[12])));
        dd[1] = java.lang.Math.fma(_rd7, _rd11, java.lang.Math.fma(_rd1, _rd9, java.lang.Math.fma(_rd4, _rd10, mData[13])));
        dd[2] = java.lang.Math.fma(_rd8, _rd11, java.lang.Math.fma(_rd2, _rd9, java.lang.Math.fma(_rd5, _rd10, mData[14])));
        dd[3] = sd[3] * (float) java.lang.Math.sqrt(java.lang.Math.max(java.lang.Math.max(java.lang.Math.fma(_rd0, _rd0, java.lang.Math.fma(_rd1, _rd1, java.lang.Math.fma(_rd2, _rd2, _t9 + _t10))), java.lang.Math.fma(_rd3, _rd3, java.lang.Math.fma(_rd4, _rd4, java.lang.Math.fma(_rd5, _rd5, _t9 + _t11)))), java.lang.Math.fma(_rd6, _rd6, java.lang.Math.fma(_rd7, _rd7, java.lang.Math.fma(_rd8, _rd8, _t10 + _t11)))));
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private FloatSphere transform_sfe021ce7_1_mulAdd(FloatSphere dest, float[] sd, float[] mData, float[] dd, float _t9, float _t10, float _t11, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7, float _rd8, float _rd9, float _rd10, float _rd11) {
        dd[0] = ((_rd6) * (_rd11) + (((_rd0) * (_rd9) + (((_rd3) * (_rd10) + (mData[12]))))));
        dd[1] = ((_rd7) * (_rd11) + (((_rd1) * (_rd9) + (((_rd4) * (_rd10) + (mData[13]))))));
        dd[2] = ((_rd8) * (_rd11) + (((_rd2) * (_rd9) + (((_rd5) * (_rd10) + (mData[14]))))));
        dd[3] = sd[3] * (float) java.lang.Math.sqrt(java.lang.Math.max(java.lang.Math.max(((_rd0) * (_rd0) + (((_rd1) * (_rd1) + (((_rd2) * (_rd2) + (_t9 + _t10)))))), ((_rd3) * (_rd3) + (((_rd4) * (_rd4) + (((_rd5) * (_rd5) + (_t9 + _t11))))))), ((_rd6) * (_rd6) + (((_rd7) * (_rd7) + (((_rd8) * (_rd8) + (_t10 + _t11))))))));
        return dest;
    }


    /**
     * Transform this sphere by {@code m}, scaling the radius by an upper bound on the matrix's
     * largest stretch so that the result contains the transformed sphere and store the result in
     * {@code dest}.
     * <p>
     * The radius factor is the square root of the largest absolute row sum of {@code M^T M} over
     * the upper-left 3x3: exact for a rotation combined with any axis scale, and at most about 1.17
     * times too large under shear. (The largest column length would be no bound once the scale is
     * applied after the rotation.) The matrix is taken as affine: its last row is not read.
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
    public DoubleSphere transform(Float4x4R m, @Mutated DoubleSphere dest) {
        if (Math.useFma()) {
            float[] mData = ((Float4x4Impl) m).data;
            return transform_scff26d12_1_fma(dest, this.data, mData, ((DoubleSphereImpl) dest).data, java.lang.Math.abs(java.lang.Math.fma(mData[2], mData[6], java.lang.Math.fma(mData[0], mData[4], mData[1] * mData[5]))), java.lang.Math.abs(java.lang.Math.fma(mData[2], mData[10], java.lang.Math.fma(mData[0], mData[8], mData[1] * mData[9]))), java.lang.Math.abs(java.lang.Math.fma(mData[6], mData[10], java.lang.Math.fma(mData[4], mData[8], mData[5] * mData[9]))));
        } else {
            float[] mData = ((Float4x4Impl) m).data;
            return transform_scff26d12_1_mulAdd(dest, this.data, mData, ((DoubleSphereImpl) dest).data, java.lang.Math.abs(((mData[2]) * (mData[6]) + (((mData[0]) * (mData[4]) + (mData[1] * mData[5]))))), java.lang.Math.abs(((mData[2]) * (mData[10]) + (((mData[0]) * (mData[8]) + (mData[1] * mData[9]))))), java.lang.Math.abs(((mData[6]) * (mData[10]) + (((mData[4]) * (mData[8]) + (mData[5] * mData[9]))))));
        }
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleSphere transform_scff26d12_1_fma(DoubleSphere dest, float[] sd, float[] mData, double[] dd, float _t9, float _t10, float _t11) {
        dd[0] = java.lang.Math.fma(mData[8], sd[2], java.lang.Math.fma(mData[0], sd[0], java.lang.Math.fma(mData[4], sd[1], mData[12])));
        dd[1] = java.lang.Math.fma(mData[9], sd[2], java.lang.Math.fma(mData[1], sd[0], java.lang.Math.fma(mData[5], sd[1], mData[13])));
        dd[2] = java.lang.Math.fma(mData[10], sd[2], java.lang.Math.fma(mData[2], sd[0], java.lang.Math.fma(mData[6], sd[1], mData[14])));
        dd[3] = sd[3] * (float) java.lang.Math.sqrt(java.lang.Math.max(java.lang.Math.max(java.lang.Math.fma(mData[0], mData[0], java.lang.Math.fma(mData[1], mData[1], java.lang.Math.fma(mData[2], mData[2], _t9 + _t10))), java.lang.Math.fma(mData[4], mData[4], java.lang.Math.fma(mData[5], mData[5], java.lang.Math.fma(mData[6], mData[6], _t9 + _t11)))), java.lang.Math.fma(mData[8], mData[8], java.lang.Math.fma(mData[9], mData[9], java.lang.Math.fma(mData[10], mData[10], _t10 + _t11)))));
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleSphere transform_scff26d12_1_mulAdd(DoubleSphere dest, float[] sd, float[] mData, double[] dd, float _t9, float _t10, float _t11) {
        dd[0] = ((mData[8]) * (sd[2]) + (((mData[0]) * (sd[0]) + (((mData[4]) * (sd[1]) + (mData[12]))))));
        dd[1] = ((mData[9]) * (sd[2]) + (((mData[1]) * (sd[0]) + (((mData[5]) * (sd[1]) + (mData[13]))))));
        dd[2] = ((mData[10]) * (sd[2]) + (((mData[2]) * (sd[0]) + (((mData[6]) * (sd[1]) + (mData[14]))))));
        dd[3] = sd[3] * (float) java.lang.Math.sqrt(java.lang.Math.max(java.lang.Math.max(((mData[0]) * (mData[0]) + (((mData[1]) * (mData[1]) + (((mData[2]) * (mData[2]) + (_t9 + _t10)))))), ((mData[4]) * (mData[4]) + (((mData[5]) * (mData[5]) + (((mData[6]) * (mData[6]) + (_t9 + _t11))))))), ((mData[8]) * (mData[8]) + (((mData[9]) * (mData[9]) + (((mData[10]) * (mData[10]) + (_t10 + _t11))))))));
        return dest;
    }


    /**
     * Translate this sphere by {@code delta} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public FloatSphere translate(Float3R delta, @Mutated FloatSphere dest) {
        float deltaY = delta.y();
        float deltaZ = delta.z();
        float[] sd = this.data;
        float[] dd = ((FloatSphereImpl) dest).data;
        dd[0] = delta.x() + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaZ + sd[2];
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Translate this sphere by {@code delta} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere translate(Float3R delta, @Mutated DoubleSphere dest) {
        float deltaY = delta.y();
        float deltaZ = delta.z();
        float[] sd = this.data;
        double[] dd = ((DoubleSphereImpl) dest).data;
        dd[0] = delta.x() + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaZ + sd[2];
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Translate this sphere by ({@code deltaX}, {@code deltaY}, {@code deltaZ}) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaZ the {@code z} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param dest will hold the result
     * @return dest
     */
    public FloatSphere translate(float deltaX, float deltaY, float deltaZ, @Mutated FloatSphere dest) {
        float[] sd = this.data;
        float[] dd = ((FloatSphereImpl) dest).data;
        dd[0] = deltaX + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaZ + sd[2];
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Translate this sphere by ({@code deltaX}, {@code deltaY}, {@code deltaZ}) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaZ the {@code z} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleSphere translate(float deltaX, float deltaY, float deltaZ, @Mutated DoubleSphere dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleSphereImpl) dest).data;
        dd[0] = deltaX + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaZ + sd[2];
        dd[3] = sd[3];
        return dest;
    }


    /**
     * Compute the point of this sphere closest to the given point. For a point inside or on the
     * sphere, the result is the point itself; otherwise it is the point on the surface in the
     * direction from the center toward the given point.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Float3 closestPointToPoint(Float3R p, @Mutated Float3 dest) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t6 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t8 = sd[3] * (1.0f / (float) java.lang.Math.sqrt(_t6));
        if (_t6 <= sd[3] * sd[3]) {
            dd[0] = pX;
            dd[1] = pY;
            dd[2] = pZ;
        } else {
            dd[0] = Math.fma(_t1, _t8, sd[0]);
            dd[1] = Math.fma(_t2, _t8, sd[1]);
            dd[2] = Math.fma(_t0, _t8, sd[2]);
        }
        return dest;
    }


    /**
     * Compute the point of this sphere closest to the given point. For a point inside or on the
     * sphere, the result is the point itself; otherwise it is the point on the surface in the
     * direction from the center toward the given point.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointToPoint(Float3R p, @Mutated Double3 dest) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t6 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t8 = sd[3] * (1.0f / (float) java.lang.Math.sqrt(_t6));
        if (_t6 <= sd[3] * sd[3]) {
            dd[0] = pX;
            dd[1] = pY;
            dd[2] = pZ;
        } else {
            dd[0] = Math.fma(_t1, _t8, sd[0]);
            dd[1] = Math.fma(_t2, _t8, sd[1]);
            dd[2] = Math.fma(_t0, _t8, sd[2]);
        }
        return dest;
    }


    /**
     * Compute the point of this sphere closest to the given point. For a point inside or on the
     * sphere, the result is the point itself; otherwise it is the point on the surface in the
     * direction from the center toward the given point.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
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
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t6 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t8 = sd[3] * (1.0f / (float) java.lang.Math.sqrt(_t6));
        if (_t6 <= sd[3] * sd[3]) {
            dd[0] = pX;
            dd[1] = pY;
            dd[2] = pZ;
        } else {
            dd[0] = Math.fma(_t1, _t8, sd[0]);
            dd[1] = Math.fma(_t2, _t8, sd[1]);
            dd[2] = Math.fma(_t0, _t8, sd[2]);
        }
        return dest;
    }


    /**
     * Compute the point of this sphere closest to the given point. For a point inside or on the
     * sphere, the result is the point itself; otherwise it is the point on the surface in the
     * direction from the center toward the given point.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
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
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t6 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t8 = sd[3] * (1.0f / (float) java.lang.Math.sqrt(_t6));
        if (_t6 <= sd[3] * sd[3]) {
            dd[0] = pX;
            dd[1] = pY;
            dd[2] = pZ;
        } else {
            dd[0] = Math.fma(_t1, _t8, sd[0]);
            dd[1] = Math.fma(_t2, _t8, sd[1]);
            dd[2] = Math.fma(_t0, _t8, sd[2]);
        }
        return dest;
    }


    /**
     * Compute the squared distance between this sphere and the given axis-aligned box, i.e. the
     * square of the distance from the box to the center minus the radius, clamped at zero; zero
     * when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of {@code aabb} must not exceed the maximum corner of {@code aabb} in any component.
     *
     * @param aabb the axis-aligned box to measure the distance to
     * @return the squared distance between this sphere and the given axis-aligned box, i.e. the
     *        square of the distance from the box to the center minus the radius, clamped at zero;
     *        zero when they overlap or touch
     */
    public float distanceSquaredToAABB(FloatAABBR aabb) {
        float minX = aabb.minX();
        float minY = aabb.minY();
        float minZ = aabb.minZ();
        float maxX = aabb.maxX();
        float maxY = aabb.maxY();
        float maxZ = aabb.maxZ();
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t6 = sd[2] - java.lang.Math.max(minZ, java.lang.Math.min(sd[2], maxZ));
            float _t7 = sd[0] - java.lang.Math.max(minX, java.lang.Math.min(sd[0], maxX));
            float _t8 = sd[1] - java.lang.Math.max(minY, java.lang.Math.min(sd[1], maxY));
            float _t14 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - sd[3]);
            return _t14 * _t14;
        } else {
            float[] sd = this.data;
            float _t6 = sd[2] - java.lang.Math.max(minZ, java.lang.Math.min(sd[2], maxZ));
            float _t7 = sd[0] - java.lang.Math.max(minX, java.lang.Math.min(sd[0], maxX));
            float _t8 = sd[1] - java.lang.Math.max(minY, java.lang.Math.min(sd[1], maxY));
            float _t14 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - sd[3]);
            return _t14 * _t14;
        }
    }


    /**
     * Compute the squared distance between this sphere and the given axis-aligned box, i.e. the
     * square of the distance from the box to the center minus the radius, clamped at zero; zero
     * when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18};
     * {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any component.
     *
     * @param minX the {@code minX} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minY the {@code minY} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minZ the {@code minZ} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxX the {@code maxX} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxY the {@code maxY} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxZ the {@code maxZ} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @return the squared distance between this sphere and the given axis-aligned box, i.e. the
     *        square of the distance from the box to the center minus the radius, clamped at zero;
     *        zero when they overlap or touch
     */
    public float distanceSquaredToAABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t6 = sd[2] - java.lang.Math.max(minZ, java.lang.Math.min(sd[2], maxZ));
            float _t7 = sd[0] - java.lang.Math.max(minX, java.lang.Math.min(sd[0], maxX));
            float _t8 = sd[1] - java.lang.Math.max(minY, java.lang.Math.min(sd[1], maxY));
            float _t14 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - sd[3]);
            return _t14 * _t14;
        } else {
            float[] sd = this.data;
            float _t6 = sd[2] - java.lang.Math.max(minZ, java.lang.Math.min(sd[2], maxZ));
            float _t7 = sd[0] - java.lang.Math.max(minX, java.lang.Math.min(sd[0], maxX));
            float _t8 = sd[1] - java.lang.Math.max(minY, java.lang.Math.min(sd[1], maxY));
            float _t14 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - sd[3]);
            return _t14 * _t14;
        }
    }


    /**
     * Compute the squared distance between this sphere and the given axis-aligned box, i.e. the
     * square of the distance from the box to the center minus the radius, clamped at zero; zero
     * when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; {@code min}
     * must not exceed {@code max} in any component.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     * @return the squared distance between this sphere and the given axis-aligned box, i.e. the
     *        square of the distance from the box to the center minus the radius, clamped at zero;
     *        zero when they overlap or touch
     */
    public float distanceSquaredToAABB(Float3R min, Float3R max) {
        float minX = min.x();
        float minY = min.y();
        float minZ = min.z();
        float maxX = max.x();
        float maxY = max.y();
        float maxZ = max.z();
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t6 = sd[2] - java.lang.Math.max(minZ, java.lang.Math.min(sd[2], maxZ));
            float _t7 = sd[0] - java.lang.Math.max(minX, java.lang.Math.min(sd[0], maxX));
            float _t8 = sd[1] - java.lang.Math.max(minY, java.lang.Math.min(sd[1], maxY));
            float _t14 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - sd[3]);
            return _t14 * _t14;
        } else {
            float[] sd = this.data;
            float _t6 = sd[2] - java.lang.Math.max(minZ, java.lang.Math.min(sd[2], maxZ));
            float _t7 = sd[0] - java.lang.Math.max(minX, java.lang.Math.min(sd[0], maxX));
            float _t8 = sd[1] - java.lang.Math.max(minY, java.lang.Math.min(sd[1], maxY));
            float _t14 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - sd[3]);
            return _t14 * _t14;
        }
    }


    /**
     * Compute the squared distance between this sphere and the given point, i.e. the square of the
     * distance from the point to the center minus the radius, clamped at zero; zero for a point
     * inside or on the sphere.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this sphere and the given point, i.e. the square of the
     *        distance from the point to the center minus the radius, clamped at zero; zero for a
     *        point inside or on the sphere
     */
    public float distanceSquaredToPoint(Float3R p) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t0 = pZ - sd[2];
            float _t1 = pX - sd[0];
            float _t2 = pY - sd[1];
            float _t8 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - sd[3]);
            return _t8 * _t8;
        } else {
            float[] sd = this.data;
            float _t0 = pZ - sd[2];
            float _t1 = pX - sd[0];
            float _t2 = pY - sd[1];
            float _t8 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - sd[3]);
            return _t8 * _t8;
        }
    }


    /**
     * Compute the squared distance between this sphere and the given point, i.e. the square of the
     * distance from the point to the center minus the radius, clamped at zero; zero for a point
     * inside or on the sphere.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the squared distance between this sphere and the given point, i.e. the square of the
     *        distance from the point to the center minus the radius, clamped at zero; zero for a
     *        point inside or on the sphere
     */
    public float distanceSquaredToPoint(float pX, float pY, float pZ) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t0 = pZ - sd[2];
            float _t1 = pX - sd[0];
            float _t2 = pY - sd[1];
            float _t8 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - sd[3]);
            return _t8 * _t8;
        } else {
            float[] sd = this.data;
            float _t0 = pZ - sd[2];
            float _t1 = pX - sd[0];
            float _t2 = pY - sd[1];
            float _t8 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - sd[3]);
            return _t8 * _t8;
        }
    }


    /**
     * Compute the squared distance between this sphere and the given sphere, i.e. the square of the
     * distance between the centers minus both radii, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param other the sphere to measure the distance to
     * @return the squared distance between this sphere and the given sphere, i.e. the square of the
     *        distance between the centers minus both radii, clamped at zero; zero when they overlap
     *        or touch
     */
    public float distanceSquaredToSphere(FloatSphereR other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherR = other.r();
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t0 = otherZ - sd[2];
            float _t1 = otherX - sd[0];
            float _t2 = otherY - sd[1];
            float _t9 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - sd[3] - otherR);
            return _t9 * _t9;
        } else {
            float[] sd = this.data;
            float _t0 = otherZ - sd[2];
            float _t1 = otherX - sd[0];
            float _t2 = otherY - sd[1];
            float _t9 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - sd[3] - otherR);
            return _t9 * _t9;
        }
    }


    /**
     * Compute the squared distance between this sphere and the given sphere, i.e. the square of the
     * distance between the centers minus both radii, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param otherX the {@code x} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherY the {@code y} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherZ the {@code z} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherR the {@code r} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @return the squared distance between this sphere and the given sphere, i.e. the square of the
     *        distance between the centers minus both radii, clamped at zero; zero when they overlap
     *        or touch
     */
    public float distanceSquaredToSphere(float otherX, float otherY, float otherZ, float otherR) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t0 = otherZ - sd[2];
            float _t1 = otherX - sd[0];
            float _t2 = otherY - sd[1];
            float _t9 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - sd[3] - otherR);
            return _t9 * _t9;
        } else {
            float[] sd = this.data;
            float _t0 = otherZ - sd[2];
            float _t1 = otherX - sd[0];
            float _t2 = otherY - sd[1];
            float _t9 = java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - sd[3] - otherR);
            return _t9 * _t9;
        }
    }


    /**
     * Compute the distance between this sphere and the given axis-aligned box, i.e. the distance
     * from the box to the center minus the radius, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of {@code aabb} must not exceed the maximum corner of {@code aabb} in any component.
     *
     * @param aabb the axis-aligned box to measure the distance to
     * @return the distance between this sphere and the given axis-aligned box, i.e. the distance
     *        from the box to the center minus the radius, clamped at zero; zero when they overlap
     *        or touch
     */
    public float distanceToAABB(FloatAABBR aabb) {
        float minX = aabb.minX();
        float minY = aabb.minY();
        float minZ = aabb.minZ();
        float maxX = aabb.maxX();
        float maxY = aabb.maxY();
        float maxZ = aabb.maxZ();
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t6 = sd[2] - java.lang.Math.max(minZ, java.lang.Math.min(sd[2], maxZ));
            float _t7 = sd[0] - java.lang.Math.max(minX, java.lang.Math.min(sd[0], maxX));
            float _t8 = sd[1] - java.lang.Math.max(minY, java.lang.Math.min(sd[1], maxY));
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - sd[3]);
        } else {
            float[] sd = this.data;
            float _t6 = sd[2] - java.lang.Math.max(minZ, java.lang.Math.min(sd[2], maxZ));
            float _t7 = sd[0] - java.lang.Math.max(minX, java.lang.Math.min(sd[0], maxX));
            float _t8 = sd[1] - java.lang.Math.max(minY, java.lang.Math.min(sd[1], maxY));
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - sd[3]);
        }
    }


    /**
     * Compute the distance between this sphere and the given axis-aligned box, i.e. the distance
     * from the box to the center minus the radius, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18};
     * {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any component.
     *
     * @param minX the {@code minX} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minY the {@code minY} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minZ the {@code minZ} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxX the {@code maxX} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxY the {@code maxY} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxZ the {@code maxZ} component of the axis-aligned box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @return the distance between this sphere and the given axis-aligned box, i.e. the distance
     *        from the box to the center minus the radius, clamped at zero; zero when they overlap
     *        or touch
     */
    public float distanceToAABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t6 = sd[2] - java.lang.Math.max(minZ, java.lang.Math.min(sd[2], maxZ));
            float _t7 = sd[0] - java.lang.Math.max(minX, java.lang.Math.min(sd[0], maxX));
            float _t8 = sd[1] - java.lang.Math.max(minY, java.lang.Math.min(sd[1], maxY));
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - sd[3]);
        } else {
            float[] sd = this.data;
            float _t6 = sd[2] - java.lang.Math.max(minZ, java.lang.Math.min(sd[2], maxZ));
            float _t7 = sd[0] - java.lang.Math.max(minX, java.lang.Math.min(sd[0], maxX));
            float _t8 = sd[1] - java.lang.Math.max(minY, java.lang.Math.min(sd[1], maxY));
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - sd[3]);
        }
    }


    /**
     * Compute the distance between this sphere and the given axis-aligned box, i.e. the distance
     * from the box to the center minus the radius, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; {@code min}
     * must not exceed {@code max} in any component.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     * @return the distance between this sphere and the given axis-aligned box, i.e. the distance
     *        from the box to the center minus the radius, clamped at zero; zero when they overlap
     *        or touch
     */
    public float distanceToAABB(Float3R min, Float3R max) {
        float minX = min.x();
        float minY = min.y();
        float minZ = min.z();
        float maxX = max.x();
        float maxY = max.y();
        float maxZ = max.z();
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t6 = sd[2] - java.lang.Math.max(minZ, java.lang.Math.min(sd[2], maxZ));
            float _t7 = sd[0] - java.lang.Math.max(minX, java.lang.Math.min(sd[0], maxX));
            float _t8 = sd[1] - java.lang.Math.max(minY, java.lang.Math.min(sd[1], maxY));
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - sd[3]);
        } else {
            float[] sd = this.data;
            float _t6 = sd[2] - java.lang.Math.max(minZ, java.lang.Math.min(sd[2], maxZ));
            float _t7 = sd[0] - java.lang.Math.max(minX, java.lang.Math.min(sd[0], maxX));
            float _t8 = sd[1] - java.lang.Math.max(minY, java.lang.Math.min(sd[1], maxY));
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - sd[3]);
        }
    }


    /**
     * Compute the distance between this sphere and the given plane, i.e. the distance from the
     * center to the plane minus the radius, clamped at zero; zero when the plane intersects or
     * touches the sphere. The plane's normal need not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the normal
     * of {@code plane} must be non-zero.
     *
     * @param plane the plane to measure the distance to
     * @return the distance between this sphere and the given plane, i.e. the distance from the
     *        center to the plane minus the radius, clamped at zero; zero when the plane intersects
     *        or touches the sphere. The plane's normal need not be of unit length
     */
    public float distanceToPlane(FloatPlaneR plane) {
        float planeA = plane.a();
        float planeB = plane.b();
        float planeC = plane.c();
        float planeD = plane.d();
        if (Math.useFma()) {
            float[] sd = this.data;
            return java.lang.Math.max(0.0f, java.lang.Math.fma((1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))), java.lang.Math.abs(java.lang.Math.fma(planeA, sd[0], java.lang.Math.fma(planeB, sd[1], java.lang.Math.fma(planeC, sd[2], planeD)))), -sd[3]));
        } else {
            float[] sd = this.data;
            return java.lang.Math.max(0.0f, (((1.0f / (float) java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB))))))) * (java.lang.Math.abs(((planeA) * (sd[0]) + (((planeB) * (sd[1]) + (((planeC) * (sd[2]) + (planeD)))))))) - (sd[3])));
        }
    }


    /**
     * Compute the distance between this sphere and the given plane, i.e. the distance from the
     * center to the plane minus the radius, clamped at zero; zero when the plane intersects or
     * touches the sphere. The plane's normal need not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the normal
     * of {@code (planeA, planeB, planeC, planeD)} must be non-zero.
     *
     * @param planeA the {@code a} component of the plane {@code (planeA, planeB, planeC, planeD)}
     *        to measure the distance to
     * @param planeB the {@code b} component of the plane {@code (planeA, planeB, planeC, planeD)}
     *        to measure the distance to
     * @param planeC the {@code c} component of the plane {@code (planeA, planeB, planeC, planeD)}
     *        to measure the distance to
     * @param planeD the {@code d} component of the plane {@code (planeA, planeB, planeC, planeD)}
     *        to measure the distance to
     * @return the distance between this sphere and the given plane, i.e. the distance from the
     *        center to the plane minus the radius, clamped at zero; zero when the plane intersects
     *        or touches the sphere. The plane's normal need not be of unit length
     */
    public float distanceToPlane(float planeA, float planeB, float planeC, float planeD) {
        if (Math.useFma()) {
            float[] sd = this.data;
            return java.lang.Math.max(0.0f, java.lang.Math.fma((1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))), java.lang.Math.abs(java.lang.Math.fma(planeA, sd[0], java.lang.Math.fma(planeB, sd[1], java.lang.Math.fma(planeC, sd[2], planeD)))), -sd[3]));
        } else {
            float[] sd = this.data;
            return java.lang.Math.max(0.0f, (((1.0f / (float) java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB))))))) * (java.lang.Math.abs(((planeA) * (sd[0]) + (((planeB) * (sd[1]) + (((planeC) * (sd[2]) + (planeD)))))))) - (sd[3])));
        }
    }


    /**
     * Compute the distance between this sphere and the given plane, i.e. the distance from the
     * center to the plane minus the radius, clamped at zero; zero when the plane intersects or
     * touches the sphere. The plane's normal need not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the normal
     * of {@code plane} must be non-zero.
     *
     * @param plane the plane to measure the distance to
     * @return the distance between this sphere and the given plane, i.e. the distance from the
     *        center to the plane minus the radius, clamped at zero; zero when the plane intersects
     *        or touches the sphere. The plane's normal need not be of unit length
     */
    public float distanceToPlane(Float4R plane) {
        float planeA = plane.x();
        float planeB = plane.y();
        float planeC = plane.z();
        float planeD = plane.w();
        if (Math.useFma()) {
            float[] sd = this.data;
            return java.lang.Math.max(0.0f, java.lang.Math.fma((1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))), java.lang.Math.abs(java.lang.Math.fma(planeA, sd[0], java.lang.Math.fma(planeB, sd[1], java.lang.Math.fma(planeC, sd[2], planeD)))), -sd[3]));
        } else {
            float[] sd = this.data;
            return java.lang.Math.max(0.0f, (((1.0f / (float) java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB))))))) * (java.lang.Math.abs(((planeA) * (sd[0]) + (((planeB) * (sd[1]) + (((planeC) * (sd[2]) + (planeD)))))))) - (sd[3])));
        }
    }


    /**
     * Compute the distance between this sphere and the given point, i.e. the distance from the
     * point to the center minus the radius, clamped at zero; zero for a point inside or on the
     * sphere.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param p the point to measure the distance to
     * @return the distance between this sphere and the given point, i.e. the distance from the
     *        point to the center minus the radius, clamped at zero; zero for a point inside or on
     *        the sphere
     */
    public float distanceToPoint(Float3R p) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t0 = pZ - sd[2];
            float _t1 = pX - sd[0];
            float _t2 = pY - sd[1];
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - sd[3]);
        } else {
            float[] sd = this.data;
            float _t0 = pZ - sd[2];
            float _t1 = pX - sd[0];
            float _t2 = pY - sd[1];
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - sd[3]);
        }
    }


    /**
     * Compute the distance between this sphere and the given point, i.e. the distance from the
     * point to the center minus the radius, clamped at zero; zero for a point inside or on the
     * sphere.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the distance between this sphere and the given point, i.e. the distance from the
     *        point to the center minus the radius, clamped at zero; zero for a point inside or on
     *        the sphere
     */
    public float distanceToPoint(float pX, float pY, float pZ) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t0 = pZ - sd[2];
            float _t1 = pX - sd[0];
            float _t2 = pY - sd[1];
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - sd[3]);
        } else {
            float[] sd = this.data;
            float _t0 = pZ - sd[2];
            float _t1 = pX - sd[0];
            float _t2 = pY - sd[1];
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - sd[3]);
        }
    }


    /**
     * Compute the distance between this sphere and the given sphere, i.e. the distance between the
     * centers minus both radii, clamped at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param other the sphere to measure the distance to
     * @return the distance between this sphere and the given sphere, i.e. the distance between the
     *        centers minus both radii, clamped at zero; zero when they overlap or touch
     */
    public float distanceToSphere(FloatSphereR other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherR = other.r();
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t0 = otherZ - sd[2];
            float _t1 = otherX - sd[0];
            float _t2 = otherY - sd[1];
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - sd[3] - otherR);
        } else {
            float[] sd = this.data;
            float _t0 = otherZ - sd[2];
            float _t1 = otherX - sd[0];
            float _t2 = otherY - sd[1];
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - sd[3] - otherR);
        }
    }


    /**
     * Compute the distance between this sphere and the given sphere, i.e. the distance between the
     * centers minus both radii, clamped at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param otherX the {@code x} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherY the {@code y} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherZ the {@code z} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @param otherR the {@code r} component of the sphere {@code (otherX, otherY, otherZ, otherR)}
     *        to measure the distance to
     * @return the distance between this sphere and the given sphere, i.e. the distance between the
     *        centers minus both radii, clamped at zero; zero when they overlap or touch
     */
    public float distanceToSphere(float otherX, float otherY, float otherZ, float otherR) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t0 = otherZ - sd[2];
            float _t1 = otherX - sd[0];
            float _t2 = otherY - sd[1];
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - sd[3] - otherR);
        } else {
            float[] sd = this.data;
            float _t0 = otherZ - sd[2];
            float _t1 = otherX - sd[0];
            float _t2 = otherY - sd[1];
            return java.lang.Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - sd[3] - otherR);
        }
    }


    /**
     * Get the center of this sphere and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getCenter(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
    }


    /**
     * Get the center of this sphere and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getCenter(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
    }


    /**
     * Determine whether this sphere is valid, i.e. its radius is not negative.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return {@code true} if this sphere is valid, i.e. its radius is not negative, {@code false}
     *        otherwise
     */
    public boolean isValid() {
        return this.data[3] >= 0.0f;
    }


    /**
     * Compute the signed distance between the given point and the surface of this sphere, i.e. the
     * distance from the point to the center minus the radius: positive outside, zero on the surface
     * and negative inside.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param p the point to measure the distance to
     * @return the signed distance between the given point and the surface of this sphere, i.e. the
     *        distance from the point to the center minus the radius: positive outside, zero on the
     *        surface and negative inside
     */
    public float signedDistanceToPoint(Float3R p) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t0 = pZ - sd[2];
            float _t1 = pX - sd[0];
            float _t2 = pY - sd[1];
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - sd[3];
        } else {
            float[] sd = this.data;
            float _t0 = pZ - sd[2];
            float _t1 = pX - sd[0];
            float _t2 = pY - sd[1];
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - sd[3];
        }
    }


    /**
     * Compute the signed distance between the given point and the surface of this sphere, i.e. the
     * distance from the point to the center minus the radius: positive outside, zero on the surface
     * and negative inside.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the signed distance between the given point and the surface of this sphere, i.e. the
     *        distance from the point to the center minus the radius: positive outside, zero on the
     *        surface and negative inside
     */
    public float signedDistanceToPoint(float pX, float pY, float pZ) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float _t0 = pZ - sd[2];
            float _t1 = pX - sd[0];
            float _t2 = pY - sd[1];
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - sd[3];
        } else {
            float[] sd = this.data;
            float _t0 = pZ - sd[2];
            float _t1 = pX - sd[0];
            float _t2 = pY - sd[1];
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - sd[3];
        }
    }

    public float x() { return data[0]; }
    public float y() { return data[1]; }
    public float z() { return data[2]; }
    public float r() { return data[3]; }

    @Override public String toString() {
        return "FloatSphere(" + x() + ", " + y() + ", " + z() + ", " + r() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatSphereImpl)) return false;
        FloatSphereImpl o = (FloatSphereImpl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Float.isFinite(data[0])
            && Float.isFinite(data[1])
            && Float.isFinite(data[2])
            && Float.isFinite(data[3]);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(data[0])
            || Float.isNaN(data[1])
            || Float.isNaN(data[2])
            || Float.isNaN(data[3]);
    }

    @Override public boolean equalsEpsilon(FloatSphereR other, float epsilon) {
        return java.lang.Math.abs(data[0] - other.x()) <= epsilon
            && java.lang.Math.abs(data[1] - other.y()) <= epsilon
            && java.lang.Math.abs(data[2] - other.z()) <= epsilon
            && java.lang.Math.abs(data[3] - other.r()) <= epsilon;
    }

    public boolean containsPoint(float px, float py, float pz) {
        return Intersectionf.testPointSphere(px, py, pz, x(), y(), z(), r() * r());
    }

    public boolean containsPoint(Float3R p) {
        return Intersectionf.testPointSphere(p, this);
    }

    public boolean intersectsSphere(FloatSphereR o) {
        return Intersectionf.testSphereSphere(x(), y(), z(), r() * r(), o.x(), o.y(), o.z(), o.r() * o.r());
    }

    public boolean intersectsAABB(FloatAABBR aabb) {
        return Intersectionf.testAabbSphere(aabb, this);
    }

    public boolean intersectsPlane(FloatPlaneR plane) {
        return Intersectionf.testPlaneSphere(plane.a(), plane.b(), plane.c(), plane.d(), x(), y(), z(), r());
    }

    public boolean intersectsRay(FloatRayR ray) {
        return Intersectionf.testRaySphere(ray, this);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }
    public @Mutated FloatSphere load(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        return this;
    }
    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }
    @Mutated public FloatSphere load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatSphere loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatSphere loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }
    public FloatSphere load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public FloatSphere loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public FloatSphere loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatSphere r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return r;
    }
    public FloatSphere storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public FloatSphere loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }
    public @Mutated FloatSphere load(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        return this;
    }
    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }
    @Mutated public FloatSphere load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatSphere loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatSphere loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }
    public ByteBuffer storeDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }
    public FloatSphere loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }
    public FloatSphere loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public FloatSphere loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatSphere r = StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return r;
    }
    public FloatSphere storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public FloatSphere loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }
}
