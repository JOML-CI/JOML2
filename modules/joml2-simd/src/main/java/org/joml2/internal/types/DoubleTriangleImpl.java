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
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link DoubleTriangle} backed by a {@code double[]} array, with
 * Vector API SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoubleTriangleImpl implements DoubleTriangle {

    public double[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoubleTriangleSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleTriangleSegOpsUnsafe()
                        : new DoubleTriangleSegOpsMS();
        static final DoubleTriangleBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleTriangleBbOpsUnsafe()
                        : new DoubleTriangleBbOpsApi();
        static final DoubleTriangleRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleTriangleRawOpsUnsafe()
                        : new DoubleTriangleRawOpsApi();
    }

    public DoubleTriangleImpl() {
        data = new double[9];
    }

    public DoubleTriangleImpl(double v0X, double v0Y, double v0Z, double v1X, double v1Y, double v1Z, double v2X, double v2Y, double v2Z) {
        double[] dd = this.data = new double[9];
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

    public DoubleTriangleImpl(DoubleTriangleR src) {
        double[] dd = this.data = new double[9];
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
    public @Mutated DoubleTriangle set(DoubleTriangleR v) {
        return set(v.v0X(), v.v0Y(), v.v0Z(), v.v1X(), v.v1Y(), v.v1Z(), v.v2X(), v.v2Y(), v.v2Z());
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
    @Mutated public DoubleTriangle set(double vV0X, double vV0Y, double vV0Z, double vV1X, double vV1Y, double vV1Z, double vV2X, double vV2Y, double vV2Z) {
        double[] dd = this.data;
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
     * Convert this triangle to {@code float} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatTriangle toFloat(@Mutated FloatTriangle dest) {
        double[] sd = this.data;
        float[] dd = ((FloatTriangleImpl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        dd[3] = (float) (sd[3]);
        dd[4] = (float) (sd[4]);
        dd[5] = (float) (sd[5]);
        dd[6] = (float) (sd[6]);
        dd[7] = (float) (sd[7]);
        dd[8] = (float) (sd[8]);
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
    public DoubleTriangle transform(Double3x4R m, @Mutated DoubleTriangle dest) {
        double[] sd = this.data;
        double[] mData = ((Double3x4Impl) m).data;
        double[] dd = ((DoubleTriangleImpl) dest).data;
        double _buf0 = Math.fma(mData[2], sd[2], Math.fma(mData[0], sd[0], Math.fma(mData[1], sd[1], mData[3])));
        double _buf1 = Math.fma(mData[6], sd[2], Math.fma(mData[4], sd[0], Math.fma(mData[5], sd[1], mData[7])));
        dd[2] = Math.fma(mData[10], sd[2], Math.fma(mData[8], sd[0], Math.fma(mData[9], sd[1], mData[11])));
        double _buf2 = Math.fma(mData[2], sd[5], Math.fma(mData[0], sd[3], Math.fma(mData[1], sd[4], mData[3])));
        double _buf3 = Math.fma(mData[6], sd[5], Math.fma(mData[4], sd[3], Math.fma(mData[5], sd[4], mData[7])));
        dd[5] = Math.fma(mData[10], sd[5], Math.fma(mData[8], sd[3], Math.fma(mData[9], sd[4], mData[11])));
        return transform_s6a7b1d9b_1(dest, sd, mData, dd, _buf0, _buf1, _buf2, _buf3);
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleTriangle transform_s6a7b1d9b_1(DoubleTriangle dest, double[] sd, double[] mData, double[] dd, double _buf0, double _buf1, double _buf2, double _buf3) {
        double _buf4 = Math.fma(mData[2], sd[8], Math.fma(mData[0], sd[6], Math.fma(mData[1], sd[7], mData[3])));
        double _buf5 = Math.fma(mData[6], sd[8], Math.fma(mData[4], sd[6], Math.fma(mData[5], sd[7], mData[7])));
        dd[8] = Math.fma(mData[10], sd[8], Math.fma(mData[8], sd[6], Math.fma(mData[9], sd[7], mData[11])));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        dd[7] = _buf5;
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
    public DoubleTriangle transform(Double4x4R m, @Mutated DoubleTriangle dest) {
        double[] sd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        double[] dd = ((DoubleTriangleImpl) dest).data;
        double _buf0 = Math.fma(mData[8], sd[2], Math.fma(mData[0], sd[0], Math.fma(mData[4], sd[1], mData[12])));
        double _buf1 = Math.fma(mData[9], sd[2], Math.fma(mData[1], sd[0], Math.fma(mData[5], sd[1], mData[13])));
        dd[2] = Math.fma(mData[10], sd[2], Math.fma(mData[2], sd[0], Math.fma(mData[6], sd[1], mData[14])));
        double _buf2 = Math.fma(mData[8], sd[5], Math.fma(mData[0], sd[3], Math.fma(mData[4], sd[4], mData[12])));
        double _buf3 = Math.fma(mData[9], sd[5], Math.fma(mData[1], sd[3], Math.fma(mData[5], sd[4], mData[13])));
        dd[5] = Math.fma(mData[10], sd[5], Math.fma(mData[2], sd[3], Math.fma(mData[6], sd[4], mData[14])));
        return transform_sd79031f4_1(dest, sd, mData, dd, _buf0, _buf1, _buf2, _buf3);
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleTriangle transform_sd79031f4_1(DoubleTriangle dest, double[] sd, double[] mData, double[] dd, double _buf0, double _buf1, double _buf2, double _buf3) {
        double _buf4 = Math.fma(mData[8], sd[8], Math.fma(mData[0], sd[6], Math.fma(mData[4], sd[7], mData[12])));
        double _buf5 = Math.fma(mData[9], sd[8], Math.fma(mData[1], sd[6], Math.fma(mData[5], sd[7], mData[13])));
        dd[8] = Math.fma(mData[10], sd[8], Math.fma(mData[2], sd[6], Math.fma(mData[6], sd[7], mData[14])));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[3] = _buf2;
        dd[4] = _buf3;
        dd[6] = _buf4;
        dd[7] = _buf5;
        return dest;
    }


    /**
     * Compute the area of this triangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the area of this triangle
     */
    public double area() {
        double[] sd = this.data;
        double _t0 = sd[3] - sd[0];
        double _t1 = sd[7] - sd[1];
        double _t2 = sd[4] - sd[1];
        double _t3 = sd[6] - sd[0];
        double _t4 = sd[8] - sd[2];
        double _t5 = sd[5] - sd[2];
        double _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        double _t13 = Math.fma(_t2, _t4, -(_t5 * _t1));
        double _t14 = Math.fma(_t5, _t3, -(_t0 * _t4));
        return 0.5 * Math.sqrt(Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14)));
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
    public Double3 barycentric(Double3R p, @Mutated Double3 dest) {
        return barycentric(p.x(), p.y(), p.z(), dest);
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
    public Double3 barycentric(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = pX - sd[0];
        double _t1 = sd[7] - sd[1];
        double _t2 = pY - sd[1];
        double _t3 = sd[6] - sd[0];
        double _t4 = sd[3] - sd[0];
        double _t5 = sd[4] - sd[1];
        double _t6 = sd[8] - sd[2];
        double _t7 = pZ - sd[2];
        double _t8 = sd[5] - sd[2];
        double _t28 = Math.fma(_t4, _t1, -(_t5 * _t3));
        double _t30 = Math.fma(_t5, _t6, -(_t8 * _t1));
        double _t32 = Math.fma(_t8, _t3, -(_t4 * _t6));
        double _t46 = Math.fma(_t28, _t28, Math.fma(_t30, _t30, _t32 * _t32));
        if (!(_t46 > 2.2250738585072014E-308 && _t46 < Double.POSITIVE_INFINITY)) return barycentric_degenerate(pX, pY, pZ, dest);
        return barycentric_s166cb433_1(pX, pY, pZ, dest, dd, _t0, _t2, _t4, _t5, _t7, _t8, _t28, _t30, _t32, 1.0 / _t46, Math.fma(Math.fma(_t0, _t1, -(_t2 * _t3)), _t28, Math.fma(Math.fma(_t2, _t6, -(_t7 * _t1)), _t30, Math.fma(_t7, _t3, -(_t0 * _t6)) * _t32)));
    }

    /** Piece 2 of {@code barycentric}, split to fit the inline budget; reached only through it. */
    private Double3 barycentric_s166cb433_1(double pX, double pY, double pZ, Double3 dest, double[] dd, double _t0, double _t2, double _t4, double _t5, double _t7, double _t8, double _t28, double _t30, double _t32, double _t46_inv, double _ct0) {
        if (!(_ct0 > Double.NEGATIVE_INFINITY && _ct0 < Double.POSITIVE_INFINITY)) return barycentric_degenerate(pX, pY, pZ, dest);
        double _t48 = _ct0 * _t46_inv;
        double _ct1 = Math.fma(Math.fma(_t2, _t4, -(_t0 * _t5)), _t28, Math.fma(Math.fma(_t0, _t8, -(_t7 * _t4)), _t32, Math.fma(_t7, _t5, -(_t2 * _t8)) * _t30));
        if (!(_ct1 > Double.NEGATIVE_INFINITY && _ct1 < Double.POSITIVE_INFINITY)) return barycentric_degenerate(pX, pY, pZ, dest);
        double _t49 = _ct1 * _t46_inv;
        dd[0] = 1.0 - _t48 - _t49;
        dd[1] = _t48;
        dd[2] = _t49;
        return dest;
    }



    /**
     * Out-of-range path of {@code barycentric}: its methods leave here when the squared length of
     * the triangle's normal (quartic in its size) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Double3 barycentric_degenerate(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t28 = Math.min(1.0, unitScale(Math.max(Math.abs(Math.max(Math.abs(sd[0]), Math.abs(sd[1]))), Math.abs(Math.max(Math.abs(sd[2]), Math.abs(sd[3])))), Math.max(Math.abs(Math.max(Math.abs(sd[4]), Math.abs(sd[5]))), Math.abs(Math.max(Math.abs(sd[6]), Math.abs(sd[7])))), Math.max(Math.abs(Math.max(Math.abs(sd[8]), Math.abs(pX))), Math.abs(Math.max(Math.abs(pY), Math.abs(pZ))))));
        double _t42 = sd[0] * _t28;
        double _t44 = sd[1] * _t28;
        double _t46 = sd[2] * _t28;
        return barycentric_degenerate_s8efdc31a_1(dest, dd, pX * _t28 - _t42, pY * _t28 - _t44, pZ * _t28 - _t46, sd[7] * _t28 - _t44, sd[3] * _t28 - _t42, sd[4] * _t28 - _t44, sd[5] * _t28 - _t46, sd[6] * _t28 - _t42, sd[8] * _t28 - _t46);
    }

    /** Piece 2 of {@code barycentric_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 barycentric_degenerate_s8efdc31a_1(Double3 dest, double[] dd, double _t53, double _t54, double _t55, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61) {
        double _t71 = unitScale(_t53, _t54, _t55);
        double _t75 = _t53 * _t71;
        double _t76 = _t54 * _t71;
        double _t77 = _t55 * _t71;
        double _t78 = unitScale(Math.max(Math.abs(_t57), Math.abs(_t58)), Math.max(Math.abs(_t59), Math.abs(_t60)), Math.max(Math.abs(_t56), Math.abs(_t61)));
        double _t85 = _t56 * _t78;
        double _t86 = _t60 * _t78;
        double _t87 = _t57 * _t78;
        double _t88 = _t58 * _t78;
        double _t89 = _t61 * _t78;
        double _t90 = _t59 * _t78;
        double _t117 = Math.fma(_t87, _t85, -(_t88 * _t86));
        double _t118 = Math.fma(_t88, _t89, -(_t90 * _t85));
        double _t119 = Math.fma(_t90, _t86, -(_t87 * _t89));
        double _sp0 = _t78 / _t71 / Math.fma(_t117, _t117, Math.fma(_t118, _t118, _t119 * _t119));
        return barycentric_degenerate_s8efdc31a_2(dest, dd, _t75, _t76, _t77, _t87, _t88, _t90, _t117, _t118, _t119, _sp0, Math.fma(Math.fma(_t75, _t85, -(_t76 * _t86)), _t117, Math.fma(Math.fma(_t76, _t89, -(_t77 * _t85)), _t118, Math.fma(_t77, _t86, -(_t75 * _t89)) * _t119)) * _sp0);
    }

    /** Piece 3 of {@code barycentric_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 barycentric_degenerate_s8efdc31a_2(Double3 dest, double[] dd, double _t75, double _t76, double _t77, double _t87, double _t88, double _t90, double _t117, double _t118, double _t119, double _sp0, double _t131) {
        double _t132 = Math.fma(Math.fma(_t76, _t87, -(_t75 * _t88)), _t117, Math.fma(Math.fma(_t75, _t90, -(_t77 * _t87)), _t119, Math.fma(_t77, _t88, -(_t76 * _t90)) * _t118)) * _sp0;
        dd[0] = 1.0 - _t131 - _t132;
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
    public Double3 getCentroid(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = 0.3333333333333333 * sd[0] + 0.3333333333333333 * sd[3] + 0.3333333333333333 * sd[6];
        dd[1] = 0.3333333333333333 * sd[1] + 0.3333333333333333 * sd[4] + 0.3333333333333333 * sd[7];
        dd[2] = 0.3333333333333333 * sd[2] + 0.3333333333333333 * sd[5] + 0.3333333333333333 * sd[8];
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
    public Double3 getNormal(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[4] - sd[1];
        double _t1 = sd[8] - sd[2];
        double _t2 = sd[5] - sd[2];
        double _t3 = sd[7] - sd[1];
        double _t4 = sd[6] - sd[0];
        double _t5 = sd[3] - sd[0];
        dd[0] = Math.fma(_t0, _t1, -(_t2 * _t3));
        dd[1] = Math.fma(_t2, _t4, -(_t5 * _t1));
        dd[2] = Math.fma(_t5, _t3, -(_t0 * _t4));
        return dest;
    }


    /**
     * Get the first vertex of this triangle and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getV0(@Mutated Double3 dest) {
        double[] sd = this.data;
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
    public Double3 getV1(@Mutated Double3 dest) {
        double[] sd = this.data;
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
    public Double3 getV2(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[6];
        dd[1] = sd[7];
        dd[2] = sd[8];
        return dest;
    }

    public double v0X() { return data[0]; }
    public double v0Y() { return data[1]; }
    public double v0Z() { return data[2]; }
    public double v1X() { return data[3]; }
    public double v1Y() { return data[4]; }
    public double v1Z() { return data[5]; }
    public double v2X() { return data[6]; }
    public double v2Y() { return data[7]; }
    public double v2Z() { return data[8]; }

    @Override public String toString() {
        return "DoubleTriangle(" + v0X() + ", " + v0Y() + ", " + v0Z() + ", " + v1X() + ", " + v1Y() + ", " + v1Z() + ", " + v2X() + ", " + v2Y() + ", " + v2Z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleTriangleImpl)) return false;
        DoubleTriangleImpl o = (DoubleTriangleImpl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Double.isFinite(data[0])
            && Double.isFinite(data[1])
            && Double.isFinite(data[2])
            && Double.isFinite(data[3])
            && Double.isFinite(data[4])
            && Double.isFinite(data[5])
            && Double.isFinite(data[6])
            && Double.isFinite(data[7])
            && Double.isFinite(data[8]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1])
            || Double.isNaN(data[2])
            || Double.isNaN(data[3])
            || Double.isNaN(data[4])
            || Double.isNaN(data[5])
            || Double.isNaN(data[6])
            || Double.isNaN(data[7])
            || Double.isNaN(data[8]);
    }

    @Override public boolean equalsEpsilon(DoubleTriangleR other, double epsilon) {
        return Math.abs(data[0] - other.v0X()) <= epsilon
            && Math.abs(data[1] - other.v0Y()) <= epsilon
            && Math.abs(data[2] - other.v0Z()) <= epsilon
            && Math.abs(data[3] - other.v1X()) <= epsilon
            && Math.abs(data[4] - other.v1Y()) <= epsilon
            && Math.abs(data[5] - other.v1Z()) <= epsilon
            && Math.abs(data[6] - other.v2X()) <= epsilon
            && Math.abs(data[7] - other.v2Y()) <= epsilon
            && Math.abs(data[8] - other.v2Z()) <= epsilon;
    }

    public boolean containsPoint(double pX, double pY, double pZ) {
        return Intersectiond.testPointInTriangle(pX, pY, pZ, v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z());
    }

    public boolean containsPoint(Double3R p) {
        return containsPoint(p.x(), p.y(), p.z());
    }

    public boolean intersectsRay(DoubleRayR ray, double epsilon) {
        return Intersectiond.testRayTriangle(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), epsilon);
    }

    public boolean intersectsRayFront(DoubleRayR ray, double epsilon) {
        return Intersectiond.testRayTriangleFront(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), epsilon);
    }

    public Double3 closestPointToPoint(double pX, double pY, double pZ, @Mutated Double3 dest) {
        Intersectiond.findClosestPointOnTriangle(v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), pX, pY, pZ, dest);
        return dest;
    }

    public Double3 closestPointToPoint(Double3R p, @Mutated Double3 dest) {
        return closestPointToPoint(p.x(), p.y(), p.z(), dest);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.data[0];
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
    public @Mutated DoubleTriangle load(double[] src, int offset) {
        this.data[0] = src[offset + 0];
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
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public DoubleTriangle loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoubleTriangle loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public DoubleTriangle storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public DoubleTriangle loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    public DoubleTriangle load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        dest[offset + 4] = (float) this.data[4];
        dest[offset + 5] = (float) this.data[5];
        dest[offset + 6] = (float) this.data[6];
        dest[offset + 7] = (float) this.data[7];
        dest[offset + 8] = (float) this.data[8];
        return dest;
    }
    public @Mutated DoubleTriangle load(float[] src, int offset) {
        this.data[0] = src[offset + 0];
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
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public DoubleTriangle loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public DoubleTriangle loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public DoubleTriangle storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public DoubleTriangle loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }
    public MemorySegment storeFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }
    public DoubleTriangle loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(this, offset, src);
    }

    private static final VectorSpecies<Double> COL_SPECIES = DoubleVector.SPECIES_256;

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

    /** Double-precision twin of {@link #unitScale(float, float, float)}. */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }
}
