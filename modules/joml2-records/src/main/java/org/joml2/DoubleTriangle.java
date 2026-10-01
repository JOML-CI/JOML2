// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Immutable triangle of double-precision {@code double} components.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Double.doubleToLongBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns).
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 *
 * @param v0X the {@code v0X} component
 * @param v0Y the {@code v0Y} component
 * @param v0Z the {@code v0Z} component
 * @param v1X the {@code v1X} component
 * @param v1Y the {@code v1Y} component
 * @param v1Z the {@code v1Z} component
 * @param v2X the {@code v2X} component
 * @param v2Y the {@code v2Y} component
 * @param v2Z the {@code v2Z} component
 */
public record DoubleTriangle(double v0X, double v0Y, double v0Z, double v1X, double v1Y, double v1Z, double v2X, double v2Y, double v2Z) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 72;

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0X the {@code v0X} component
     * @param v0Y the {@code v0Y} component
     * @param v0Z the {@code v0Z} component
     * @param v1X the {@code v1X} component
     * @param v1Y the {@code v1Y} component
     * @param v1Z the {@code v1Z} component
     * @param v2X the {@code v2X} component
     * @param v2Y the {@code v2Y} component
     * @param v2Z the {@code v2Z} component
     */
    public DoubleTriangle(double v0X, double v0Y, double v0Z, double v1X, double v1Y, double v1Z, double v2X, double v2Y, double v2Z) {
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

    /**
     * Create a new instance initialized to all zeros.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public DoubleTriangle() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    /**
     * Create a triangle from its three vertices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the first vertex
     * @param v1 the second vertex
     * @param v2 the third vertex
     */
    public DoubleTriangle(Double3 v0, Double3 v1, Double3 v2) {
        this(v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z());
    }

    /** {@return the {@code v0X} component} <p>Valid input: any value, NaN and the infinities included. */
    public double v0X() { return v0X; }
    /** {@return the {@code v0Y} component} <p>Valid input: any value, NaN and the infinities included. */
    public double v0Y() { return v0Y; }
    /** {@return the {@code v0Z} component} <p>Valid input: any value, NaN and the infinities included. */
    public double v0Z() { return v0Z; }
    /** {@return the {@code v1X} component} <p>Valid input: any value, NaN and the infinities included. */
    public double v1X() { return v1X; }
    /** {@return the {@code v1Y} component} <p>Valid input: any value, NaN and the infinities included. */
    public double v1Y() { return v1Y; }
    /** {@return the {@code v1Z} component} <p>Valid input: any value, NaN and the infinities included. */
    public double v1Z() { return v1Z; }
    /** {@return the {@code v2X} component} <p>Valid input: any value, NaN and the infinities included. */
    public double v2X() { return v2X; }
    /** {@return the {@code v2Y} component} <p>Valid input: any value, NaN and the infinities included. */
    public double v2Y() { return v2Y; }
    /** {@return the {@code v2Z} component} <p>Valid input: any value, NaN and the infinities included. */
    public double v2Z() { return v2Z; }

    /**
     * Create a new triangle from its three vertices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v0 the first vertex
     * @param v1 the second vertex
     * @param v2 the third vertex
     * @return the resulting triangle
     */
    public DoubleTriangle set(Double3 v0, Double3 v1, Double3 v2) {
        return new DoubleTriangle(v0, v1, v2);
    }


    /**
     * Create a new triangle from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the triangle to copy
     * @return the resulting triangle
     */
    public DoubleTriangle set(DoubleTriangle v) {
        double vV0X = v.v0X();
        double vV0Y = v.v0Y();
        double vV0Z = v.v0Z();
        double vV1X = v.v1X();
        double vV1Y = v.v1Y();
        double vV1Z = v.v1Z();
        double vV2X = v.v2X();
        double vV2Y = v.v2Y();
        double vV2Z = v.v2Z();
        return new DoubleTriangle(vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z);
    }


    /**
     * Create a new triangle from the given values.
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
     * @return the resulting triangle
     */
    public DoubleTriangle set(double vV0X, double vV0Y, double vV0Z, double vV1X, double vV1Y, double vV1Z, double vV2X, double vV2Y, double vV2Z) {
        return new DoubleTriangle(vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z);
    }


    /**
     * Convert this triangle to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code FloatTriangle} holding the result
     */
    public FloatTriangle toFloat() {
        return new FloatTriangle((float) (this.v0X), (float) (this.v0Y), (float) (this.v0Z), (float) (this.v1X), (float) (this.v1Y), (float) (this.v1Z), (float) (this.v2X), (float) (this.v2Y), (float) (this.v2Z));
    }

    /** Private tail of {@code transform}; reached only through it. */
    private DoubleTriangle transform_s91e96b_tail(Double3x4 m, double _sfx0, double _sfx1, double _sfx2, double _sfx3, double _sfx4) {
        double _sfx5 = Math.fma(m.m22(), this.v1Z, Math.fma(m.m20(), this.v1X, Math.fma(m.m21(), this.v1Y, m.m23())));
        double _sfx6 = Math.fma(m.m02(), this.v2Z, Math.fma(m.m00(), this.v2X, Math.fma(m.m01(), this.v2Y, m.m03())));
        double _sfx7 = Math.fma(m.m12(), this.v2Z, Math.fma(m.m10(), this.v2X, Math.fma(m.m11(), this.v2Y, m.m13())));
        double _sfx8 = Math.fma(m.m22(), this.v2Z, Math.fma(m.m20(), this.v2X, Math.fma(m.m21(), this.v2Y, m.m23())));
        return new DoubleTriangle(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8);
    }


    /**
     * Transform this triangle by {@code m}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @return the resulting triangle
     */
    public DoubleTriangle transform(Double3x4 m) {
        double _sfx0 = Math.fma(m.m02(), this.v0Z, Math.fma(m.m00(), this.v0X, Math.fma(m.m01(), this.v0Y, m.m03())));
        double _sfx1 = Math.fma(m.m12(), this.v0Z, Math.fma(m.m10(), this.v0X, Math.fma(m.m11(), this.v0Y, m.m13())));
        double _sfx2 = Math.fma(m.m22(), this.v0Z, Math.fma(m.m20(), this.v0X, Math.fma(m.m21(), this.v0Y, m.m23())));
        double _sfx3 = Math.fma(m.m02(), this.v1Z, Math.fma(m.m00(), this.v1X, Math.fma(m.m01(), this.v1Y, m.m03())));
        double _sfx4 = Math.fma(m.m12(), this.v1Z, Math.fma(m.m10(), this.v1X, Math.fma(m.m11(), this.v1Y, m.m13())));
        return transform_s91e96b_tail(m, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }

    /** Private tail of {@code transform}; reached only through it. */
    private DoubleTriangle transform_sa000ec_tail(Double4x4 m, double _sfx0, double _sfx1, double _sfx2, double _sfx3, double _sfx4) {
        double _sfx5 = Math.fma(m.m22(), this.v1Z, Math.fma(m.m20(), this.v1X, Math.fma(m.m21(), this.v1Y, m.m23())));
        double _sfx6 = Math.fma(m.m02(), this.v2Z, Math.fma(m.m00(), this.v2X, Math.fma(m.m01(), this.v2Y, m.m03())));
        double _sfx7 = Math.fma(m.m12(), this.v2Z, Math.fma(m.m10(), this.v2X, Math.fma(m.m11(), this.v2Y, m.m13())));
        double _sfx8 = Math.fma(m.m22(), this.v2Z, Math.fma(m.m20(), this.v2X, Math.fma(m.m21(), this.v2Y, m.m23())));
        return new DoubleTriangle(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8);
    }


    /**
     * Transform this triangle by {@code m}, returning the result as a value.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @return the resulting triangle
     */
    public DoubleTriangle transform(Double4x4 m) {
        double _sfx0 = Math.fma(m.m02(), this.v0Z, Math.fma(m.m00(), this.v0X, Math.fma(m.m01(), this.v0Y, m.m03())));
        double _sfx1 = Math.fma(m.m12(), this.v0Z, Math.fma(m.m10(), this.v0X, Math.fma(m.m11(), this.v0Y, m.m13())));
        double _sfx2 = Math.fma(m.m22(), this.v0Z, Math.fma(m.m20(), this.v0X, Math.fma(m.m21(), this.v0Y, m.m23())));
        double _sfx3 = Math.fma(m.m02(), this.v1Z, Math.fma(m.m00(), this.v1X, Math.fma(m.m01(), this.v1Y, m.m03())));
        double _sfx4 = Math.fma(m.m12(), this.v1Z, Math.fma(m.m10(), this.v1X, Math.fma(m.m11(), this.v1Y, m.m13())));
        return transform_sa000ec_tail(m, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }


    /**
     * Compute the area of this triangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the area of this triangle
     */
    public double area() {
        double _t0 = this.v1X - this.v0X;
        double _t1 = this.v2Y - this.v0Y;
        double _t2 = this.v1Y - this.v0Y;
        double _t3 = this.v2X - this.v0X;
        double _t4 = this.v2Z - this.v0Z;
        double _t5 = this.v1Z - this.v0Z;
        double _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        double _t13 = Math.fma(_t2, _t4, -(_t5 * _t1));
        double _t14 = Math.fma(_t5, _t3, -(_t0 * _t4));
        return 0.5 * java.lang.Math.sqrt(Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14)));
    }


    /**
     * Compute the barycentric coordinates of the given point with respect to this triangle,
     * returning the result as a value.
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
     * @return the resulting vector
     */
    public Double3 barycentric(Double3 p) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        double _t1 = this.v2Y - this.v0Y;
        double _t3 = this.v2X - this.v0X;
        double _t4 = this.v1X - this.v0X;
        double _t5 = this.v1Y - this.v0Y;
        double _t6 = this.v2Z - this.v0Z;
        double _t8 = this.v1Z - this.v0Z;
        double _t28 = Math.fma(_t4, _t1, -(_t5 * _t3));
        double _t30 = Math.fma(_t5, _t6, -(_t8 * _t1));
        double _t32 = Math.fma(_t8, _t3, -(_t4 * _t6));
        double _t46 = Math.fma(_t28, _t28, Math.fma(_t30, _t30, _t32 * _t32));
        if (!(_t46 > 2.2250738585072014E-308 && _t46 < Double.POSITIVE_INFINITY)) return barycentric_degenerate(pX, pY, pZ);
        Double3 _r = barycentric_s1948e2d8_tail(_t46, pX - this.v0X, _t1, pY - this.v0Y, _t3, _t28, _t6, pZ - this.v0Z, _t30, _t32, _t4, _t5, _t8);
        return _r != null ? _r : barycentric_degenerate(pX, pY, pZ);
    }

    /** Private tail of {@code barycentric}; reached only through it. */
    private Double3 barycentric_s1948e2d8_tail(double _t46, double _t0, double _t1, double _t2, double _t3, double _t28, double _t6, double _t7, double _t30, double _t32, double _t4, double _t5, double _t8) {
        double _t46_inv = 1.0 / _t46;
        double _ct0 = Math.fma(Math.fma(_t0, _t1, -(_t2 * _t3)), _t28, Math.fma(Math.fma(_t2, _t6, -(_t7 * _t1)), _t30, Math.fma(_t7, _t3, -(_t0 * _t6)) * _t32));
        if (!(_ct0 > Double.NEGATIVE_INFINITY && _ct0 < Double.POSITIVE_INFINITY)) return null;
        double _t48 = _ct0 * _t46_inv;
        double _ct1 = Math.fma(Math.fma(_t2, _t4, -(_t0 * _t5)), _t28, Math.fma(Math.fma(_t0, _t8, -(_t7 * _t4)), _t32, Math.fma(_t7, _t5, -(_t2 * _t8)) * _t30));
        if (!(_ct1 > Double.NEGATIVE_INFINITY && _ct1 < Double.POSITIVE_INFINITY)) return null;
        double _t49 = _ct1 * _t46_inv;
        return new Double3(1.0 - _t48 - _t49, _t48, _t49);
    }


    /**
     * Compute the barycentric coordinates of the given point with respect to this triangle,
     * returning the result as a value.
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
     * @return the resulting vector
     */
    public Double3 barycentric(double pX, double pY, double pZ) {
        double _t1 = this.v2Y - this.v0Y;
        double _t3 = this.v2X - this.v0X;
        double _t4 = this.v1X - this.v0X;
        double _t5 = this.v1Y - this.v0Y;
        double _t6 = this.v2Z - this.v0Z;
        double _t8 = this.v1Z - this.v0Z;
        double _t28 = Math.fma(_t4, _t1, -(_t5 * _t3));
        double _t30 = Math.fma(_t5, _t6, -(_t8 * _t1));
        double _t32 = Math.fma(_t8, _t3, -(_t4 * _t6));
        double _t46 = Math.fma(_t28, _t28, Math.fma(_t30, _t30, _t32 * _t32));
        if (!(_t46 > 2.2250738585072014E-308 && _t46 < Double.POSITIVE_INFINITY)) return barycentric_degenerate(pX, pY, pZ);
        Double3 _r = barycentric_s1948e2d8_tail(_t46, pX - this.v0X, _t1, pY - this.v0Y, _t3, _t28, _t6, pZ - this.v0Z, _t30, _t32, _t4, _t5, _t8);
        return _r != null ? _r : barycentric_degenerate(pX, pY, pZ);
    }

    /** Private tail of {@code barycentric_degenerate}; reached only through it. */
    private Double3 barycentric_degenerate_s1948e2d8_tail(double _t28, double _t42, double _t46, double _t53, double _t54, double _t55, double _t57, double _t58, double _t59, double _t56) {
        double _t60 = this.v2X * _t28 - _t42;
        double _t61 = this.v2Z * _t28 - _t46;
        double _t71 = unitScale(_t53, _t54, _t55);
        double _t78 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t57), java.lang.Math.abs(_t58)), java.lang.Math.max(java.lang.Math.abs(_t59), java.lang.Math.abs(_t60)), java.lang.Math.max(java.lang.Math.abs(_t56), java.lang.Math.abs(_t61)));
        double _t85 = _t56 * _t78;
        double _t86 = _t60 * _t78;
        double _t87 = _t57 * _t78;
        double _t88 = _t58 * _t78;
        double _t89 = _t61 * _t78;
        double _t90 = _t59 * _t78;
        double _t117 = Math.fma(_t87, _t85, -(_t88 * _t86));
        double _t118 = Math.fma(_t88, _t89, -(_t90 * _t85));
        double _t119 = Math.fma(_t90, _t86, -(_t87 * _t89));
        return barycentric_degenerate_s1948e2d8_tail2(_t53 * _t71, _t85, _t54 * _t71, _t86, _t117, _t89, _t55 * _t71, _t118, _t119, _t78 / _t71 / Math.fma(_t117, _t117, Math.fma(_t118, _t118, _t119 * _t119)), _t87, _t88, _t90);
    }

    /** Private tail of {@code barycentric_degenerate}; reached only through it. */
    private Double3 barycentric_degenerate_s1948e2d8_tail2(double _t75, double _t85, double _t76, double _t86, double _t117, double _t89, double _t77, double _t118, double _t119, double _sp0, double _t87, double _t88, double _t90) {
        double _t131 = Math.fma(Math.fma(_t75, _t85, -(_t76 * _t86)), _t117, Math.fma(Math.fma(_t76, _t89, -(_t77 * _t85)), _t118, Math.fma(_t77, _t86, -(_t75 * _t89)) * _t119)) * _sp0;
        double _t132 = Math.fma(Math.fma(_t76, _t87, -(_t75 * _t88)), _t117, Math.fma(Math.fma(_t75, _t90, -(_t77 * _t87)), _t119, Math.fma(_t77, _t88, -(_t76 * _t90)) * _t118)) * _sp0;
        double _sfx0 = 1.0 - _t131 - _t132;
        return new Double3(_sfx0, _t131, _t132);
    }


    /**
     * Out-of-range path of {@code barycentric}: its methods leave here when the squared length of
     * the triangle's normal (quartic in its size) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Double3 barycentric_degenerate(double pX, double pY, double pZ) {
        double _t28 = java.lang.Math.min(1.0, unitScale(java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(this.v0X), java.lang.Math.abs(this.v0Y))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(this.v0Z), java.lang.Math.abs(this.v1X)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(this.v1Y), java.lang.Math.abs(this.v1Z))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(this.v2X), java.lang.Math.abs(this.v2Y)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(this.v2Z), java.lang.Math.abs(pX))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(pY), java.lang.Math.abs(pZ))))));
        double _t42 = this.v0X * _t28;
        double _t44 = this.v0Y * _t28;
        double _t46 = this.v0Z * _t28;
        return barycentric_degenerate_s1948e2d8_tail(_t28, _t42, _t46, pX * _t28 - _t42, pY * _t28 - _t44, pZ * _t28 - _t46, this.v1X * _t28 - _t42, this.v1Y * _t28 - _t44, this.v1Z * _t28 - _t46, this.v2Y * _t28 - _t44);
    }


    /**
     * Get the centroid (the average of the vertices) of this triangle, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getCentroid() {
        return new Double3(0.3333333333333333 * this.v0X + 0.3333333333333333 * this.v1X + 0.3333333333333333 * this.v2X, 0.3333333333333333 * this.v0Y + 0.3333333333333333 * this.v1Y + 0.3333333333333333 * this.v2Y, 0.3333333333333333 * this.v0Z + 0.3333333333333333 * this.v1Z + 0.3333333333333333 * this.v2Z);
    }


    /**
     * Get the normal of this triangle, i.e. the cross product {@code (v1 - v0) x (v2 - v0)} (not
     * unit length: its length is twice the triangle's area), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getNormal() {
        double _t0 = this.v1Y - this.v0Y;
        double _t1 = this.v2Z - this.v0Z;
        double _t2 = this.v1Z - this.v0Z;
        double _t3 = this.v2Y - this.v0Y;
        double _t4 = this.v2X - this.v0X;
        double _t5 = this.v1X - this.v0X;
        return new Double3(Math.fma(_t0, _t1, -(_t2 * _t3)), Math.fma(_t2, _t4, -(_t5 * _t1)), Math.fma(_t5, _t3, -(_t0 * _t4)));
    }


    /**
     * Get the first vertex of this triangle, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getV0() {
        return new Double3(this.v0X, this.v0Y, this.v0Z);
    }


    /**
     * Get the second vertex of this triangle, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getV1() {
        return new Double3(this.v1X, this.v1Y, this.v1Z);
    }


    /**
     * Get the third vertex of this triangle, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getV2() {
        return new Double3(this.v2X, this.v2Y, this.v2Z);
    }

    /**
     * Determine whether the projection of the given point onto this triangle's plane lies inside or
     * on this triangle (boundary inclusive). Delegates to the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the x coordinate of the point
     * @param pY the y coordinate of the point
     * @param pZ the z coordinate of the point
     * @return {@code true} if the projection of the given point onto this triangle's plane lies
     *        inside or on this triangle, {@code false} otherwise
     */
    public boolean containsPoint(double pX, double pY, double pZ) {
        return Intersectiond.testPointInTriangle(pX, pY, pZ, v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z());
    }

    /**
     * Determine whether the projection of the given point onto this triangle's plane lies inside or
     * on this triangle (boundary inclusive). Delegates to the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to test
     * @return {@code true} if the projection of the given point onto this triangle's plane lies
     *        inside or on this triangle, {@code false} otherwise
     */
    public boolean containsPoint(Double3 p) {
        return containsPoint(p.x(), p.y(), p.z());
    }

    /**
     * Determine whether this triangle intersects the given ray. Delegates to the shared
     * {@code Intersectiond} kernels.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param ray the ray to test for intersection
     * @param epsilon the tolerance below which the ray counts as parallel to the triangle's plane,
     *        guarding the near-zero determinant
     * @return {@code true} if this triangle and the given ray intersect, {@code false} otherwise
     */
    public boolean intersectsRay(DoubleRay ray, double epsilon) {
        return Intersectiond.testRayTriangle(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), epsilon);
    }

    /**
     * Determine whether this triangle intersects the given ray, front face only. Delegates to the
     * shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param ray the ray to test for intersection
     * @param epsilon the tolerance below which the ray counts as parallel to the triangle's plane,
     *        guarding the near-zero determinant
     * @return {@code true} if the ray hits the front face of this triangle, {@code false} otherwise
     */
    public boolean intersectsRayFront(DoubleRay ray, double epsilon) {
        return Intersectiond.testRayTriangleFront(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), epsilon);
    }

    /**
     * Compute the point on this triangle closest to the given point. Delegates to the shared
     * {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to find the closest point to
     * @return the point on this triangle closest to the given point
     */
    public Double3 closestPointToPoint(Double3 p) {
        return Intersectiond.closestPointOnTriangle(v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), p.x(), p.y(), p.z());
    }

    /**
     * {@return a copy with the {@code v0X} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v0X} component
     */
    public DoubleTriangle withV0X(double v) {
        return new DoubleTriangle(v, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z);
    }

    /**
     * {@return a copy with the {@code v0Y} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v0Y} component
     */
    public DoubleTriangle withV0Y(double v) {
        return new DoubleTriangle(v0X, v, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z);
    }

    /**
     * {@return a copy with the {@code v0Z} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v0Z} component
     */
    public DoubleTriangle withV0Z(double v) {
        return new DoubleTriangle(v0X, v0Y, v, v1X, v1Y, v1Z, v2X, v2Y, v2Z);
    }

    /**
     * {@return a copy with the {@code v1X} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v1X} component
     */
    public DoubleTriangle withV1X(double v) {
        return new DoubleTriangle(v0X, v0Y, v0Z, v, v1Y, v1Z, v2X, v2Y, v2Z);
    }

    /**
     * {@return a copy with the {@code v1Y} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v1Y} component
     */
    public DoubleTriangle withV1Y(double v) {
        return new DoubleTriangle(v0X, v0Y, v0Z, v1X, v, v1Z, v2X, v2Y, v2Z);
    }

    /**
     * {@return a copy with the {@code v1Z} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v1Z} component
     */
    public DoubleTriangle withV1Z(double v) {
        return new DoubleTriangle(v0X, v0Y, v0Z, v1X, v1Y, v, v2X, v2Y, v2Z);
    }

    /**
     * {@return a copy with the {@code v2X} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v2X} component
     */
    public DoubleTriangle withV2X(double v) {
        return new DoubleTriangle(v0X, v0Y, v0Z, v1X, v1Y, v1Z, v, v2Y, v2Z);
    }

    /**
     * {@return a copy with the {@code v2Y} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v2Y} component
     */
    public DoubleTriangle withV2Y(double v) {
        return new DoubleTriangle(v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v, v2Z);
    }

    /**
     * {@return a copy with the {@code v2Z} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v2Z} component
     */
    public DoubleTriangle withV2Z(double v) {
        return new DoubleTriangle(v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v);
    }

    @Override public String toString() {
        return "DoubleTriangle(" + v0X() + ", " + v0Y() + ", " + v0Z() + ", " + v1X() + ", " + v1Y() + ", " + v1Z() + ", " + v2X() + ", " + v2Y() + ", " + v2Z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleTriangle)) return false;
        DoubleTriangle o = (DoubleTriangle) obj;
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

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
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

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
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

    /**
     * Compare this value component-wise against {@code other}, allowing a difference of at
     * most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
     * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and
     * a NaN component never compares equal to anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the value to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    public boolean equalsEpsilon(DoubleTriangle other, double epsilon) {
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


    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] store(double[] dest, int offset) {
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

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle load(double[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        double _c6 = src[offset + 6];
        double _c7 = src[offset + 7];
        double _c8 = src[offset + 8];
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle load(double[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer store(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRelative(DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code DoubleTriangle} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleTriangle loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleTriangle r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 9);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer, starting at its current position and advancing
     * the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 72);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position and advancing
     * the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code DoubleTriangle} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleTriangle loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleTriangle r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 72);
        return r;
    }

    /**
     * Store the elements into the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public DoubleTriangle storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store(MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

    /**
     * Load the elements from the given memory segment, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(offset, src);
    }


    /**
     * Store the elements into the given array, converting each element to {@code float}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] store(float[] dest, int offset) {
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

    /**
     * Store the elements into the given array, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store(float[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code float}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle load(float[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        double _c6 = src[offset + 6];
        double _c7 = src[offset + 7];
        double _c8 = src[offset + 8];
        return new DoubleTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Load the elements from the given array, converting each element from {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle load(float[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code float}, starting
     * at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer store(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code float}, starting
     * at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code float}, starting
     * at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRelative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @return a new {@code DoubleTriangle} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleTriangle loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleTriangle r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 9);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @return a new {@code DoubleTriangle} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleTriangle loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleTriangle r = StoreLoad.BB_OPS.loadFloatAbsolute(pos, buf);
        buf.position(pos + 36);
        return r;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public DoubleTriangle storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment, converting each element to {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeFloat(MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code float},
     * starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment, converting each element from {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(0L, src); }

    /**
     * Load the elements from the given memory segment, converting each element from {@code float},
     * starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code DoubleTriangle} holding the loaded elements
     */
    public static DoubleTriangle loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(offset, src);
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
