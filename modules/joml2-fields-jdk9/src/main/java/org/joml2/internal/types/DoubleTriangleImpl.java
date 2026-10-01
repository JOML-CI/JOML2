// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link DoubleTriangle} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoubleTriangleImpl implements DoubleTriangle {

    public double v0X;
    public double v0Y;
    public double v0Z;
    public double v1X;
    public double v1Y;
    public double v1Z;
    public double v2X;
    public double v2Y;
    public double v2Z;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
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
    }

    public DoubleTriangleImpl(double v0X, double v0Y, double v0Z, double v1X, double v1Y, double v1Z, double v2X, double v2Y, double v2Z) {
        this.v0X = v0X;
        this.v0Y = v0Y;
        this.v0Z = v0Z;
        this.v1X = v1X;
        this.v1Y = v1Y;
        this.v1Z = v1Z;
        this.v2X = v2X;
        this.v2Y = v2Y;
        this.v2Z = v2Z;
    }

    public DoubleTriangleImpl(DoubleTriangleR src) {
        this.v0X = src.v0X();
        this.v0Y = src.v0Y();
        this.v0Z = src.v0Z();
        this.v1X = src.v1X();
        this.v1Y = src.v1Y();
        this.v1Z = src.v1Z();
        this.v2X = src.v2X();
        this.v2Y = src.v2Y();
        this.v2Z = src.v2Z();
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
        double vV0Y = v.v0Y();
        double vV0Z = v.v0Z();
        double vV1X = v.v1X();
        double vV1Y = v.v1Y();
        double vV1Z = v.v1Z();
        double vV2X = v.v2X();
        double vV2Y = v.v2Y();
        double vV2Z = v.v2Z();
        this.v0X = v.v0X();
        this.v0Y = vV0Y;
        this.v0Z = vV0Z;
        this.v1X = vV1X;
        this.v1Y = vV1Y;
        this.v1Z = vV1Z;
        this.v2X = vV2X;
        this.v2Y = vV2Y;
        this.v2Z = vV2Z;
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
    @Mutated public DoubleTriangle set(double vV0X, double vV0Y, double vV0Z, double vV1X, double vV1Y, double vV1Z, double vV2X, double vV2Y, double vV2Z) {
        this.v0X = vV0X;
        this.v0Y = vV0Y;
        this.v0Z = vV0Z;
        this.v1X = vV1X;
        this.v1Y = vV1Y;
        this.v1Z = vV1Z;
        this.v2X = vV2X;
        this.v2Y = vV2Y;
        this.v2Z = vV2Z;
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
        FloatTriangleImpl d = (FloatTriangleImpl) dest;
        d.v0X = (float) (this.v0X);
        d.v0Y = (float) (this.v0Y);
        d.v0Z = (float) (this.v0Z);
        d.v1X = (float) (this.v1X);
        d.v1Y = (float) (this.v1Y);
        d.v1Z = (float) (this.v1Z);
        d.v2X = (float) (this.v2X);
        d.v2Y = (float) (this.v2Y);
        d.v2Z = (float) (this.v2Z);
        return d;
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s78e00a1c_c0_fma(DoubleTriangleImpl _dst, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _r12, double _r13, double _r14) {
        _dst.v0X = java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, java.lang.Math.fma(_r4, _r5, _r6)));
        _dst.v0Y = java.lang.Math.fma(_r7, _r1, java.lang.Math.fma(_r8, _r3, java.lang.Math.fma(_r9, _r5, _r10)));
        _dst.v0Z = java.lang.Math.fma(_r11, _r1, java.lang.Math.fma(_r12, _r3, java.lang.Math.fma(_r13, _r5, _r14)));
    }

    /**
     * Private store group 0 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s78e00a1c_c0_mulAdd(DoubleTriangleImpl _dst, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _r12, double _r13, double _r14) {
        _dst.v0X = ((_r0) * (_r1) + (((_r2) * (_r3) + (((_r4) * (_r5) + (_r6))))));
        _dst.v0Y = ((_r7) * (_r1) + (((_r8) * (_r3) + (((_r9) * (_r5) + (_r10))))));
        _dst.v0Z = ((_r11) * (_r1) + (((_r12) * (_r3) + (((_r13) * (_r5) + (_r14))))));
    }

    /**
     * Private store group 1 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s78e00a1c_c1_fma(DoubleTriangleImpl _dst, double _r0, double _r15, double _r2, double _r16, double _r4, double _r17, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _r12, double _r13, double _r14) {
        _dst.v1X = java.lang.Math.fma(_r0, _r15, java.lang.Math.fma(_r2, _r16, java.lang.Math.fma(_r4, _r17, _r6)));
        _dst.v1Y = java.lang.Math.fma(_r7, _r15, java.lang.Math.fma(_r8, _r16, java.lang.Math.fma(_r9, _r17, _r10)));
        _dst.v1Z = java.lang.Math.fma(_r11, _r15, java.lang.Math.fma(_r12, _r16, java.lang.Math.fma(_r13, _r17, _r14)));
    }

    /**
     * Private store group 1 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s78e00a1c_c1_mulAdd(DoubleTriangleImpl _dst, double _r0, double _r15, double _r2, double _r16, double _r4, double _r17, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _r12, double _r13, double _r14) {
        _dst.v1X = ((_r0) * (_r15) + (((_r2) * (_r16) + (((_r4) * (_r17) + (_r6))))));
        _dst.v1Y = ((_r7) * (_r15) + (((_r8) * (_r16) + (((_r9) * (_r17) + (_r10))))));
        _dst.v1Z = ((_r11) * (_r15) + (((_r12) * (_r16) + (((_r13) * (_r17) + (_r14))))));
    }

    /**
     * Private store group 2 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s78e00a1c_c2_fma(DoubleTriangleImpl _dst, double _r0, double _r18, double _r2, double _r19, double _r4, double _r20, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _r12, double _r13, double _r14) {
        _dst.v2X = java.lang.Math.fma(_r0, _r18, java.lang.Math.fma(_r2, _r19, java.lang.Math.fma(_r4, _r20, _r6)));
        _dst.v2Y = java.lang.Math.fma(_r7, _r18, java.lang.Math.fma(_r8, _r19, java.lang.Math.fma(_r9, _r20, _r10)));
        _dst.v2Z = java.lang.Math.fma(_r11, _r18, java.lang.Math.fma(_r12, _r19, java.lang.Math.fma(_r13, _r20, _r14)));
    }

    /**
     * Private store group 2 of {@code transform}: computes and stores it. Shared by 2 identical
     * private paths of {@code transform}; reached only through it.
     */
    private void transform_s78e00a1c_c2_mulAdd(DoubleTriangleImpl _dst, double _r0, double _r18, double _r2, double _r19, double _r4, double _r20, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _r12, double _r13, double _r14) {
        _dst.v2X = ((_r0) * (_r18) + (((_r2) * (_r19) + (((_r4) * (_r20) + (_r6))))));
        _dst.v2Y = ((_r7) * (_r18) + (((_r8) * (_r19) + (((_r9) * (_r20) + (_r10))))));
        _dst.v2Z = ((_r11) * (_r18) + (((_r12) * (_r19) + (((_r13) * (_r20) + (_r14))))));
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s78e00a1c_tail_fma(DoubleTriangleImpl _dst, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _r12, double _r13, double _r14, double _r15, double _r16, double _r17, double _r18, double _r19) {
        double _r20 = this.v2Y;
        transform_s78e00a1c_c0_fma(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
        transform_s78e00a1c_c1_fma(_dst, _r0, _r15, _r2, _r16, _r4, _r17, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
        transform_s78e00a1c_c2_fma(_dst, _r0, _r18, _r2, _r19, _r4, _r20, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
    }

    /**
     * Private tail of {@code transform}. Shared by 2 identical private paths of {@code transform};
     * reached only through it.
     */
    private void transform_s78e00a1c_tail_mulAdd(DoubleTriangleImpl _dst, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _r12, double _r13, double _r14, double _r15, double _r16, double _r17, double _r18, double _r19) {
        double _r20 = this.v2Y;
        transform_s78e00a1c_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
        transform_s78e00a1c_c1_mulAdd(_dst, _r0, _r15, _r2, _r16, _r4, _r17, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
        transform_s78e00a1c_c2_mulAdd(_dst, _r0, _r18, _r2, _r19, _r4, _r20, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14);
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
        DoubleTriangleImpl d = (DoubleTriangleImpl) dest;
        double _r0 = m.m02();
        double _r1 = this.v0Z;
        double _r2 = m.m00();
        double _r3 = this.v0X;
        double _r4 = m.m01();
        double _r5 = this.v0Y;
        double _r6 = m.m03();
        double _r7 = m.m12();
        double _r8 = m.m10();
        double _r9 = m.m11();
        double _r10 = m.m13();
        double _r11 = m.m22();
        double _r12 = m.m20();
        double _r13 = m.m21();
        double _r14 = m.m23();
        double _r15 = this.v1Z;
        double _r16 = this.v1X;
        double _r17 = this.v1Y;
        double _r18 = this.v2Z;
        double _r19 = this.v2X;
        if (Math.useFma()) transform_s78e00a1c_tail_fma(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19); else transform_s78e00a1c_tail_mulAdd(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19);
        return d;
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
        DoubleTriangleImpl d = (DoubleTriangleImpl) dest;
        double _r0 = m.m02();
        double _r1 = this.v0Z;
        double _r2 = m.m00();
        double _r3 = this.v0X;
        double _r4 = m.m01();
        double _r5 = this.v0Y;
        double _r6 = m.m03();
        double _r7 = m.m12();
        double _r8 = m.m10();
        double _r9 = m.m11();
        double _r10 = m.m13();
        double _r11 = m.m22();
        double _r12 = m.m20();
        double _r13 = m.m21();
        double _r14 = m.m23();
        double _r15 = this.v1Z;
        double _r16 = this.v1X;
        double _r17 = this.v1Y;
        double _r18 = this.v2Z;
        double _r19 = this.v2X;
        if (Math.useFma()) transform_s78e00a1c_tail_fma(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19); else transform_s78e00a1c_tail_mulAdd(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19);
        return d;
    }


    /**
     * Compute the area of this triangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the area of this triangle
     */
    public double area() {
        if (Math.useFma()) {
            double _t0 = this.v1X - this.v0X;
            double _t1 = this.v2Y - this.v0Y;
            double _t2 = this.v1Y - this.v0Y;
            double _t3 = this.v2X - this.v0X;
            double _t4 = this.v2Z - this.v0Z;
            double _t5 = this.v1Z - this.v0Z;
            double _t12 = java.lang.Math.fma(_t0, _t1, -(_t2 * _t3));
            double _t13 = java.lang.Math.fma(_t2, _t4, -(_t5 * _t1));
            double _t14 = java.lang.Math.fma(_t5, _t3, -(_t0 * _t4));
            return 0.5 * java.lang.Math.sqrt(java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14)));
        } else {
            double _t0 = this.v1X - this.v0X;
            double _t1 = this.v2Y - this.v0Y;
            double _t2 = this.v1Y - this.v0Y;
            double _t3 = this.v2X - this.v0X;
            double _t4 = this.v2Z - this.v0Z;
            double _t5 = this.v1Z - this.v0Z;
            double _t12 = ((_t0) * (_t1) - (_t2 * _t3));
            double _t13 = ((_t2) * (_t4) - (_t5 * _t1));
            double _t14 = ((_t5) * (_t3) - (_t0 * _t4));
            return 0.5 * java.lang.Math.sqrt(((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14)))));
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
    public Double3 barycentric(Double3R p, @Mutated Double3 dest) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
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
    public Double3 barycentric(double pX, double pY, double pZ, @Mutated Double3 dest) {
        if (Math.useFma()) return barycentric_fma(pX, pY, pZ, dest);
        return barycentric_mulAdd(pX, pY, pZ, dest);
    }

    /** {@code barycentric} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 barycentric_fma(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double _t0 = pX - this.v0X;
        double _t1 = this.v2Y - this.v0Y;
        double _t2 = pY - this.v0Y;
        double _t3 = this.v2X - this.v0X;
        double _t4 = this.v1X - this.v0X;
        double _t5 = this.v1Y - this.v0Y;
        double _t6 = this.v2Z - this.v0Z;
        double _t7 = pZ - this.v0Z;
        double _t8 = this.v1Z - this.v0Z;
        double _t28 = java.lang.Math.fma(_t4, _t1, -(_t5 * _t3));
        double _t30 = java.lang.Math.fma(_t5, _t6, -(_t8 * _t1));
        double _t32 = java.lang.Math.fma(_t8, _t3, -(_t4 * _t6));
        double _t46 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t30, _t30, _t32 * _t32));
        if (!(_t46 > 2.2250738585072014E-308 && _t46 < Double.POSITIVE_INFINITY)) return barycentric_degenerate_fma(pX, pY, pZ, dest);
        double _ct0 = java.lang.Math.fma(java.lang.Math.fma(_t0, _t1, -(_t2 * _t3)), _t28, java.lang.Math.fma(java.lang.Math.fma(_t2, _t6, -(_t7 * _t1)), _t30, java.lang.Math.fma(_t7, _t3, -(_t0 * _t6)) * _t32));
        if (!(_ct0 > Double.NEGATIVE_INFINITY && _ct0 < Double.POSITIVE_INFINITY)) return barycentric_degenerate_fma(pX, pY, pZ, dest);
        return barycentric_s166cb433_1_fma(pX, pY, pZ, dest, (Double3Impl) dest, _t0, _t2, _t4, _t5, _t7, _t8, _t28, _t30, _t32, 1.0 / _t46, _ct0);
    }

    /** {@code barycentric} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 barycentric_mulAdd(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double _t0 = pX - this.v0X;
        double _t1 = this.v2Y - this.v0Y;
        double _t2 = pY - this.v0Y;
        double _t3 = this.v2X - this.v0X;
        double _t4 = this.v1X - this.v0X;
        double _t5 = this.v1Y - this.v0Y;
        double _t6 = this.v2Z - this.v0Z;
        double _t7 = pZ - this.v0Z;
        double _t8 = this.v1Z - this.v0Z;
        double _t28 = ((_t4) * (_t1) - (_t5 * _t3));
        double _t30 = ((_t5) * (_t6) - (_t8 * _t1));
        double _t32 = ((_t8) * (_t3) - (_t4 * _t6));
        double _t46 = ((_t28) * (_t28) + (((_t30) * (_t30) + (_t32 * _t32))));
        if (!(_t46 > 2.2250738585072014E-308 && _t46 < Double.POSITIVE_INFINITY)) return barycentric_degenerate_mulAdd(pX, pY, pZ, dest);
        double _ct0 = ((((_t0) * (_t1) - (_t2 * _t3))) * (_t28) + (((((_t2) * (_t6) - (_t7 * _t1))) * (_t30) + (((_t7) * (_t3) - (_t0 * _t6)) * _t32))));
        if (!(_ct0 > Double.NEGATIVE_INFINITY && _ct0 < Double.POSITIVE_INFINITY)) return barycentric_degenerate_mulAdd(pX, pY, pZ, dest);
        return barycentric_s166cb433_1_mulAdd(pX, pY, pZ, dest, (Double3Impl) dest, _t0, _t2, _t4, _t5, _t7, _t8, _t28, _t30, _t32, 1.0 / _t46, _ct0);
    }

    /** Piece 2 of {@code barycentric}, split to fit the inline budget; reached only through it. */
    private Double3 barycentric_s166cb433_1_fma(double pX, double pY, double pZ, Double3 dest, Double3Impl d, double _t0, double _t2, double _t4, double _t5, double _t7, double _t8, double _t28, double _t30, double _t32, double _t46_inv, double _ct0) {
        double _t48 = _ct0 * _t46_inv;
        double _ct1 = java.lang.Math.fma(java.lang.Math.fma(_t2, _t4, -(_t0 * _t5)), _t28, java.lang.Math.fma(java.lang.Math.fma(_t0, _t8, -(_t7 * _t4)), _t32, java.lang.Math.fma(_t7, _t5, -(_t2 * _t8)) * _t30));
        if (!(_ct1 > Double.NEGATIVE_INFINITY && _ct1 < Double.POSITIVE_INFINITY)) return barycentric_degenerate_fma(pX, pY, pZ, dest);
        double _t49 = _ct1 * _t46_inv;
        d.x = 1.0 - _t48 - _t49;
        d.y = _t48;
        d.z = _t49;
        return d;
    }

    /** Piece 2 of {@code barycentric}, split to fit the inline budget; reached only through it. */
    private Double3 barycentric_s166cb433_1_mulAdd(double pX, double pY, double pZ, Double3 dest, Double3Impl d, double _t0, double _t2, double _t4, double _t5, double _t7, double _t8, double _t28, double _t30, double _t32, double _t46_inv, double _ct0) {
        double _t48 = _ct0 * _t46_inv;
        double _ct1 = ((((_t2) * (_t4) - (_t0 * _t5))) * (_t28) + (((((_t0) * (_t8) - (_t7 * _t4))) * (_t32) + (((_t7) * (_t5) - (_t2 * _t8)) * _t30))));
        if (!(_ct1 > Double.NEGATIVE_INFINITY && _ct1 < Double.POSITIVE_INFINITY)) return barycentric_degenerate_mulAdd(pX, pY, pZ, dest);
        double _t49 = _ct1 * _t46_inv;
        d.x = 1.0 - _t48 - _t49;
        d.y = _t48;
        d.z = _t49;
        return d;
    }

    /**
     * Out-of-range path of {@code barycentric}: its methods leave here when the squared length of
     * the triangle's normal (quartic in its size) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Double3 barycentric_degenerate_fma(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double _r0 = this.v0X;
        double _r1 = this.v0Y;
        double _r2 = this.v0Z;
        double _r3 = this.v1X;
        double _r4 = this.v1Y;
        double _r5 = this.v1Z;
        double _r6 = this.v2X;
        double _r7 = this.v2Y;
        double _r8 = this.v2Z;
        double _t28 = java.lang.Math.min(1.0, unitScale(java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r0), java.lang.Math.abs(_r1))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r2), java.lang.Math.abs(_r3)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r4), java.lang.Math.abs(_r5))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r6), java.lang.Math.abs(_r7)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r8), java.lang.Math.abs(pX))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(pY), java.lang.Math.abs(pZ))))));
        double _t42 = _r0 * _t28;
        double _t44 = _r1 * _t28;
        double _t46 = _r2 * _t28;
        double _t53 = pX * _t28 - _t42;
        double _t54 = pY * _t28 - _t44;
        double _t55 = pZ * _t28 - _t46;
        double _t71 = unitScale(_t53, _t54, _t55);
        return barycentric_degenerate_s8efdc31a_1_fma((Double3Impl) dest, _r7 * _t28 - _t44, _r3 * _t28 - _t42, _r4 * _t28 - _t44, _r5 * _t28 - _t46, _r6 * _t28 - _t42, _r8 * _t28 - _t46, _t71, _t53 * _t71, _t54 * _t71, _t55 * _t71);
    }

    /**
     * Out-of-range path of {@code barycentric}: its methods leave here when the squared length of
     * the triangle's normal (quartic in its size) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Double3 barycentric_degenerate_mulAdd(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double _r0 = this.v0X;
        double _r1 = this.v0Y;
        double _r2 = this.v0Z;
        double _r3 = this.v1X;
        double _r4 = this.v1Y;
        double _r5 = this.v1Z;
        double _r6 = this.v2X;
        double _r7 = this.v2Y;
        double _r8 = this.v2Z;
        double _t28 = java.lang.Math.min(1.0, unitScale(java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r0), java.lang.Math.abs(_r1))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r2), java.lang.Math.abs(_r3)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r4), java.lang.Math.abs(_r5))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r6), java.lang.Math.abs(_r7)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_r8), java.lang.Math.abs(pX))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(pY), java.lang.Math.abs(pZ))))));
        double _t42 = _r0 * _t28;
        double _t44 = _r1 * _t28;
        double _t46 = _r2 * _t28;
        double _t53 = pX * _t28 - _t42;
        double _t54 = pY * _t28 - _t44;
        double _t55 = pZ * _t28 - _t46;
        double _t71 = unitScale(_t53, _t54, _t55);
        return barycentric_degenerate_s8efdc31a_1_mulAdd((Double3Impl) dest, _r7 * _t28 - _t44, _r3 * _t28 - _t42, _r4 * _t28 - _t44, _r5 * _t28 - _t46, _r6 * _t28 - _t42, _r8 * _t28 - _t46, _t71, _t53 * _t71, _t54 * _t71, _t55 * _t71);
    }

    /** Piece 2 of {@code barycentric_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 barycentric_degenerate_s8efdc31a_1_fma(Double3Impl d, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t71, double _t75, double _t76, double _t77) {
        double _t78 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t57), java.lang.Math.abs(_t58)), java.lang.Math.max(java.lang.Math.abs(_t59), java.lang.Math.abs(_t60)), java.lang.Math.max(java.lang.Math.abs(_t56), java.lang.Math.abs(_t61)));
        double _t85 = _t56 * _t78;
        double _t86 = _t60 * _t78;
        double _t87 = _t57 * _t78;
        double _t88 = _t58 * _t78;
        double _t89 = _t61 * _t78;
        double _t90 = _t59 * _t78;
        double _t117 = java.lang.Math.fma(_t87, _t85, -(_t88 * _t86));
        double _t118 = java.lang.Math.fma(_t88, _t89, -(_t90 * _t85));
        double _t119 = java.lang.Math.fma(_t90, _t86, -(_t87 * _t89));
        double _sp0 = _t78 / _t71 / java.lang.Math.fma(_t117, _t117, java.lang.Math.fma(_t118, _t118, _t119 * _t119));
        double _t131 = java.lang.Math.fma(java.lang.Math.fma(_t75, _t85, -(_t76 * _t86)), _t117, java.lang.Math.fma(java.lang.Math.fma(_t76, _t89, -(_t77 * _t85)), _t118, java.lang.Math.fma(_t77, _t86, -(_t75 * _t89)) * _t119)) * _sp0;
        double _t132 = java.lang.Math.fma(java.lang.Math.fma(_t76, _t87, -(_t75 * _t88)), _t117, java.lang.Math.fma(java.lang.Math.fma(_t75, _t90, -(_t77 * _t87)), _t119, java.lang.Math.fma(_t77, _t88, -(_t76 * _t90)) * _t118)) * _sp0;
        d.x = 1.0 - _t131 - _t132;
        d.y = _t131;
        d.z = _t132;
        return d;
    }

    /** Piece 2 of {@code barycentric_degenerate}, split to fit the inline budget; reached only through it. */
    private Double3 barycentric_degenerate_s8efdc31a_1_mulAdd(Double3Impl d, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t71, double _t75, double _t76, double _t77) {
        double _t78 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t57), java.lang.Math.abs(_t58)), java.lang.Math.max(java.lang.Math.abs(_t59), java.lang.Math.abs(_t60)), java.lang.Math.max(java.lang.Math.abs(_t56), java.lang.Math.abs(_t61)));
        double _t85 = _t56 * _t78;
        double _t86 = _t60 * _t78;
        double _t87 = _t57 * _t78;
        double _t88 = _t58 * _t78;
        double _t89 = _t61 * _t78;
        double _t90 = _t59 * _t78;
        double _t117 = ((_t87) * (_t85) - (_t88 * _t86));
        double _t118 = ((_t88) * (_t89) - (_t90 * _t85));
        double _t119 = ((_t90) * (_t86) - (_t87 * _t89));
        double _sp0 = _t78 / _t71 / ((_t117) * (_t117) + (((_t118) * (_t118) + (_t119 * _t119))));
        double _t131 = ((((_t75) * (_t85) - (_t76 * _t86))) * (_t117) + (((((_t76) * (_t89) - (_t77 * _t85))) * (_t118) + (((_t77) * (_t86) - (_t75 * _t89)) * _t119)))) * _sp0;
        double _t132 = ((((_t76) * (_t87) - (_t75 * _t88))) * (_t117) + (((((_t75) * (_t90) - (_t77 * _t87))) * (_t119) + (((_t77) * (_t88) - (_t76 * _t90)) * _t118)))) * _sp0;
        d.x = 1.0 - _t131 - _t132;
        d.y = _t131;
        d.z = _t132;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = 0.3333333333333333 * this.v0X + 0.3333333333333333 * this.v1X + 0.3333333333333333 * this.v2X;
        d.y = 0.3333333333333333 * this.v0Y + 0.3333333333333333 * this.v1Y + 0.3333333333333333 * this.v2Y;
        d.z = 0.3333333333333333 * this.v0Z + 0.3333333333333333 * this.v1Z + 0.3333333333333333 * this.v2Z;
        return d;
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = this.v1Y - this.v0Y;
            double _t1 = this.v2Z - this.v0Z;
            double _t2 = this.v1Z - this.v0Z;
            double _t3 = this.v2Y - this.v0Y;
            double _t4 = this.v2X - this.v0X;
            double _t5 = this.v1X - this.v0X;
            d.x = java.lang.Math.fma(_t0, _t1, -(_t2 * _t3));
            d.y = java.lang.Math.fma(_t2, _t4, -(_t5 * _t1));
            d.z = java.lang.Math.fma(_t5, _t3, -(_t0 * _t4));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            double _t0 = this.v1Y - this.v0Y;
            double _t1 = this.v2Z - this.v0Z;
            double _t2 = this.v1Z - this.v0Z;
            double _t3 = this.v2Y - this.v0Y;
            double _t4 = this.v2X - this.v0X;
            double _t5 = this.v1X - this.v0X;
            d.x = ((_t0) * (_t1) - (_t2 * _t3));
            d.y = ((_t2) * (_t4) - (_t5 * _t1));
            d.z = ((_t5) * (_t3) - (_t0 * _t4));
            return d;
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
    public Double3 getV0(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.v0X;
        d.y = this.v0Y;
        d.z = this.v0Z;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = this.v1X;
        d.y = this.v1Y;
        d.z = this.v1Z;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = this.v2X;
        d.y = this.v2Y;
        d.z = this.v2Z;
        return d;
    }

    public double v0X() { return this.v0X; }
    public double v0Y() { return this.v0Y; }
    public double v0Z() { return this.v0Z; }
    public double v1X() { return this.v1X; }
    public double v1Y() { return this.v1Y; }
    public double v1Z() { return this.v1Z; }
    public double v2X() { return this.v2X; }
    public double v2Y() { return this.v2Y; }
    public double v2Z() { return this.v2Z; }

    @Override public String toString() {
        return "DoubleTriangle(" + v0X() + ", " + v0Y() + ", " + v0Z() + ", " + v1X() + ", " + v1Y() + ", " + v1Z() + ", " + v2X() + ", " + v2Y() + ", " + v2Z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleTriangleImpl)) return false;
        DoubleTriangleImpl o = (DoubleTriangleImpl) obj;
        return Double.doubleToLongBits(v0X) == Double.doubleToLongBits(o.v0X)
            && Double.doubleToLongBits(v0Y) == Double.doubleToLongBits(o.v0Y)
            && Double.doubleToLongBits(v0Z) == Double.doubleToLongBits(o.v0Z)
            && Double.doubleToLongBits(v1X) == Double.doubleToLongBits(o.v1X)
            && Double.doubleToLongBits(v1Y) == Double.doubleToLongBits(o.v1Y)
            && Double.doubleToLongBits(v1Z) == Double.doubleToLongBits(o.v1Z)
            && Double.doubleToLongBits(v2X) == Double.doubleToLongBits(o.v2X)
            && Double.doubleToLongBits(v2Y) == Double.doubleToLongBits(o.v2Y)
            && Double.doubleToLongBits(v2Z) == Double.doubleToLongBits(o.v2Z);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(Double.doubleToLongBits(v0X) ^ (Double.doubleToLongBits(v0X) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(v0Y) ^ (Double.doubleToLongBits(v0Y) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(v0Z) ^ (Double.doubleToLongBits(v0Z) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(v1X) ^ (Double.doubleToLongBits(v1X) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(v1Y) ^ (Double.doubleToLongBits(v1Y) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(v1Z) ^ (Double.doubleToLongBits(v1Z) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(v2X) ^ (Double.doubleToLongBits(v2X) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(v2Y) ^ (Double.doubleToLongBits(v2Y) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(v2Z) ^ (Double.doubleToLongBits(v2Z) >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return Double.isFinite(v0X)
            && Double.isFinite(v0Y)
            && Double.isFinite(v0Z)
            && Double.isFinite(v1X)
            && Double.isFinite(v1Y)
            && Double.isFinite(v1Z)
            && Double.isFinite(v2X)
            && Double.isFinite(v2Y)
            && Double.isFinite(v2Z);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(v0X)
            || Double.isNaN(v0Y)
            || Double.isNaN(v0Z)
            || Double.isNaN(v1X)
            || Double.isNaN(v1Y)
            || Double.isNaN(v1Z)
            || Double.isNaN(v2X)
            || Double.isNaN(v2Y)
            || Double.isNaN(v2Z);
    }

    @Override public boolean equalsEpsilon(DoubleTriangleR other, double epsilon) {
        return java.lang.Math.abs(v0X - other.v0X()) <= epsilon
            && java.lang.Math.abs(v0Y - other.v0Y()) <= epsilon
            && java.lang.Math.abs(v0Z - other.v0Z()) <= epsilon
            && java.lang.Math.abs(v1X - other.v1X()) <= epsilon
            && java.lang.Math.abs(v1Y - other.v1Y()) <= epsilon
            && java.lang.Math.abs(v1Z - other.v1Z()) <= epsilon
            && java.lang.Math.abs(v2X - other.v2X()) <= epsilon
            && java.lang.Math.abs(v2Y - other.v2Y()) <= epsilon
            && java.lang.Math.abs(v2Z - other.v2Z()) <= epsilon;
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
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        Intersectiond.findClosestPointOnTriangle(v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), pX, pY, pZ, dest);
        return dest;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.v0X;
        dest[offset + 1] = this.v0Y;
        dest[offset + 2] = this.v0Z;
        dest[offset + 3] = this.v1X;
        dest[offset + 4] = this.v1Y;
        dest[offset + 5] = this.v1Z;
        dest[offset + 6] = this.v2X;
        dest[offset + 7] = this.v2Y;
        dest[offset + 8] = this.v2Z;
        return dest;
    }
    public @Mutated DoubleTriangle load(double[] src, int offset) {
        this.v0X = src[offset];
        this.v0Y = src[offset + 1];
        this.v0Z = src[offset + 2];
        this.v1X = src[offset + 3];
        this.v1Y = src[offset + 4];
        this.v1Z = src[offset + 5];
        this.v2X = src[offset + 6];
        this.v2Y = src[offset + 7];
        this.v2Z = src[offset + 8];
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
    @Mutated public DoubleTriangle load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public DoubleTriangle loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public DoubleTriangle loadRelative(DoubleBuffer buf) {
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
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 72);
        return buf;
    }
    public DoubleTriangle load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public DoubleTriangle loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public DoubleTriangle loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleTriangle r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 72);
        return r;
    }
    public DoubleTriangle storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public DoubleTriangle loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.v0X;
        dest[offset + 1] = (float) this.v0Y;
        dest[offset + 2] = (float) this.v0Z;
        dest[offset + 3] = (float) this.v1X;
        dest[offset + 4] = (float) this.v1Y;
        dest[offset + 5] = (float) this.v1Z;
        dest[offset + 6] = (float) this.v2X;
        dest[offset + 7] = (float) this.v2Y;
        dest[offset + 8] = (float) this.v2Z;
        return dest;
    }
    public @Mutated DoubleTriangle load(float[] src, int offset) {
        this.v0X = src[offset];
        this.v0Y = src[offset + 1];
        this.v0Z = src[offset + 2];
        this.v1X = src[offset + 3];
        this.v1Y = src[offset + 4];
        this.v1Z = src[offset + 5];
        this.v2X = src[offset + 6];
        this.v2Y = src[offset + 7];
        this.v2Z = src[offset + 8];
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
    @Mutated public DoubleTriangle load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public DoubleTriangle loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public DoubleTriangle loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 9);
        return this;
    }
    public ByteBuffer storeFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return buf;
    }
    public DoubleTriangle loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, buf.position(), buf);
    }
    public DoubleTriangle loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public DoubleTriangle loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleTriangle r = StoreLoad.BB_OPS.loadFloatAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return r;
    }
    public DoubleTriangle storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public DoubleTriangle loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
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
