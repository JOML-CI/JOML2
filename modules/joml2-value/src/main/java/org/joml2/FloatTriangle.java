// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Immutable triangle of single-precision {@code float} components, declared as a value record.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance; as a value class, instances have no identity and may be flattened by the JVM.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
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
@jdk.internal.vm.annotation.LooselyConsistentValue
public value record FloatTriangle(float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int SIZE_BYTES = 36;

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
    public FloatTriangle(float v0X, float v0Y, float v0Z, float v1X, float v1Y, float v1Z, float v2X, float v2Y, float v2Z) {
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
    public FloatTriangle() {
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
    public FloatTriangle(Float3 v0, Float3 v1, Float3 v2) {
        this(v0.x(), v0.y(), v0.z(), v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z());
    }

    /** {@return the {@code v0X} component} <p>Valid input: any value, NaN and the infinities included. */
    public float v0X() { return v0X; }
    /** {@return the {@code v0Y} component} <p>Valid input: any value, NaN and the infinities included. */
    public float v0Y() { return v0Y; }
    /** {@return the {@code v0Z} component} <p>Valid input: any value, NaN and the infinities included. */
    public float v0Z() { return v0Z; }
    /** {@return the {@code v1X} component} <p>Valid input: any value, NaN and the infinities included. */
    public float v1X() { return v1X; }
    /** {@return the {@code v1Y} component} <p>Valid input: any value, NaN and the infinities included. */
    public float v1Y() { return v1Y; }
    /** {@return the {@code v1Z} component} <p>Valid input: any value, NaN and the infinities included. */
    public float v1Z() { return v1Z; }
    /** {@return the {@code v2X} component} <p>Valid input: any value, NaN and the infinities included. */
    public float v2X() { return v2X; }
    /** {@return the {@code v2Y} component} <p>Valid input: any value, NaN and the infinities included. */
    public float v2Y() { return v2Y; }
    /** {@return the {@code v2Z} component} <p>Valid input: any value, NaN and the infinities included. */
    public float v2Z() { return v2Z; }

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
    public FloatTriangle set(Float3 v0, Float3 v1, Float3 v2) {
        return new FloatTriangle(v0, v1, v2);
    }


    /**
     * Create a new triangle from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the triangle to copy
     * @return the resulting triangle
     */
    public FloatTriangle set(FloatTriangle v) {
        return set(v.v0X(), v.v0Y(), v.v0Z(), v.v1X(), v.v1Y(), v.v1Z(), v.v2X(), v.v2Y(), v.v2Z());
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
    public FloatTriangle set(float vV0X, float vV0Y, float vV0Z, float vV1X, float vV1Y, float vV1Z, float vV2X, float vV2Y, float vV2Z) {
        return new FloatTriangle(vV0X, vV0Y, vV0Z, vV1X, vV1Y, vV1Z, vV2X, vV2Y, vV2Z);
    }


    /**
     * Convert this triangle to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code DoubleTriangle} holding the result
     */
    public DoubleTriangle toDouble() {
        return new DoubleTriangle(this.v0X, this.v0Y, this.v0Z, this.v1X, this.v1Y, this.v1Z, this.v2X, this.v2Y, this.v2Z);
    }

    /** Private tail of {@code transform}; reached only through it. */
    private FloatTriangle transform_s37cad600_tail(Float3x4 m, float _sfx0, float _sfx1, float _sfx2, float _sfx3, float _sfx4) {
        float _sfx5 = Math.fma(m.m22(), this.v1Z, Math.fma(m.m20(), this.v1X, Math.fma(m.m21(), this.v1Y, m.m23())));
        float _sfx6 = Math.fma(m.m02(), this.v2Z, Math.fma(m.m00(), this.v2X, Math.fma(m.m01(), this.v2Y, m.m03())));
        float _sfx7 = Math.fma(m.m12(), this.v2Z, Math.fma(m.m10(), this.v2X, Math.fma(m.m11(), this.v2Y, m.m13())));
        float _sfx8 = Math.fma(m.m22(), this.v2Z, Math.fma(m.m20(), this.v2X, Math.fma(m.m21(), this.v2Y, m.m23())));
        return new FloatTriangle(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8);
    }


    /**
     * Transform this triangle by {@code m}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @return the resulting triangle
     */
    public FloatTriangle transform(Float3x4 m) {
        float _sfx0 = Math.fma(m.m02(), this.v0Z, Math.fma(m.m00(), this.v0X, Math.fma(m.m01(), this.v0Y, m.m03())));
        float _sfx1 = Math.fma(m.m12(), this.v0Z, Math.fma(m.m10(), this.v0X, Math.fma(m.m11(), this.v0Y, m.m13())));
        float _sfx2 = Math.fma(m.m22(), this.v0Z, Math.fma(m.m20(), this.v0X, Math.fma(m.m21(), this.v0Y, m.m23())));
        float _sfx3 = Math.fma(m.m02(), this.v1Z, Math.fma(m.m00(), this.v1X, Math.fma(m.m01(), this.v1Y, m.m03())));
        float _sfx4 = Math.fma(m.m12(), this.v1Z, Math.fma(m.m10(), this.v1X, Math.fma(m.m11(), this.v1Y, m.m13())));
        return transform_s37cad600_tail(m, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }

    /** Private tail of {@code transform}; reached only through it. */
    private FloatTriangle transform_s37d8ed81_tail(Float4x4 m, float _sfx0, float _sfx1, float _sfx2, float _sfx3, float _sfx4) {
        float _sfx5 = Math.fma(m.m22(), this.v1Z, Math.fma(m.m20(), this.v1X, Math.fma(m.m21(), this.v1Y, m.m23())));
        float _sfx6 = Math.fma(m.m02(), this.v2Z, Math.fma(m.m00(), this.v2X, Math.fma(m.m01(), this.v2Y, m.m03())));
        float _sfx7 = Math.fma(m.m12(), this.v2Z, Math.fma(m.m10(), this.v2X, Math.fma(m.m11(), this.v2Y, m.m13())));
        float _sfx8 = Math.fma(m.m22(), this.v2Z, Math.fma(m.m20(), this.v2X, Math.fma(m.m21(), this.v2Y, m.m23())));
        return new FloatTriangle(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8);
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
    public FloatTriangle transform(Float4x4 m) {
        float _sfx0 = Math.fma(m.m02(), this.v0Z, Math.fma(m.m00(), this.v0X, Math.fma(m.m01(), this.v0Y, m.m03())));
        float _sfx1 = Math.fma(m.m12(), this.v0Z, Math.fma(m.m10(), this.v0X, Math.fma(m.m11(), this.v0Y, m.m13())));
        float _sfx2 = Math.fma(m.m22(), this.v0Z, Math.fma(m.m20(), this.v0X, Math.fma(m.m21(), this.v0Y, m.m23())));
        float _sfx3 = Math.fma(m.m02(), this.v1Z, Math.fma(m.m00(), this.v1X, Math.fma(m.m01(), this.v1Y, m.m03())));
        float _sfx4 = Math.fma(m.m12(), this.v1Z, Math.fma(m.m10(), this.v1X, Math.fma(m.m11(), this.v1Y, m.m13())));
        return transform_s37d8ed81_tail(m, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }


    /**
     * Compute the area of this triangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the area of this triangle
     */
    public float area() {
        float _t0 = this.v1X - this.v0X;
        float _t1 = this.v2Y - this.v0Y;
        float _t2 = this.v1Y - this.v0Y;
        float _t3 = this.v2X - this.v0X;
        float _t4 = this.v2Z - this.v0Z;
        float _t5 = this.v1Z - this.v0Z;
        float _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        float _t13 = Math.fma(_t2, _t4, -(_t5 * _t1));
        float _t14 = Math.fma(_t5, _t3, -(_t0 * _t4));
        return 0.5f * (float) Math.sqrt(Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14)));
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
    public Float3 barycentric(Float3 p) {
        return barycentric(p.x(), p.y(), p.z());
    }

    /** Private tail of {@code barycentric}; reached only through it. */
    private Float3 barycentric_s2b62622f_tail(float _t46, float _t0, float _t1, float _t2, float _t3, float _t28, float _t6, float _t7, float _t30, float _t32, float _t4, float _t5, float _t8) {
        float _t46_inv = 1.0f / _t46;
        float _ct0 = Math.fma(Math.fma(_t0, _t1, -(_t2 * _t3)), _t28, Math.fma(Math.fma(_t2, _t6, -(_t7 * _t1)), _t30, Math.fma(_t7, _t3, -(_t0 * _t6)) * _t32));
        if (!(_ct0 > Float.NEGATIVE_INFINITY && _ct0 < Float.POSITIVE_INFINITY)) return null;
        float _t48 = _ct0 * _t46_inv;
        float _ct1 = Math.fma(Math.fma(_t2, _t4, -(_t0 * _t5)), _t28, Math.fma(Math.fma(_t0, _t8, -(_t7 * _t4)), _t32, Math.fma(_t7, _t5, -(_t2 * _t8)) * _t30));
        if (!(_ct1 > Float.NEGATIVE_INFINITY && _ct1 < Float.POSITIVE_INFINITY)) return null;
        float _t49 = _ct1 * _t46_inv;
        return new Float3(1.0f - _t48 - _t49, _t48, _t49);
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
    public Float3 barycentric(float pX, float pY, float pZ) {
        float _t1 = this.v2Y - this.v0Y;
        float _t3 = this.v2X - this.v0X;
        float _t4 = this.v1X - this.v0X;
        float _t5 = this.v1Y - this.v0Y;
        float _t6 = this.v2Z - this.v0Z;
        float _t8 = this.v1Z - this.v0Z;
        float _t28 = Math.fma(_t4, _t1, -(_t5 * _t3));
        float _t30 = Math.fma(_t5, _t6, -(_t8 * _t1));
        float _t32 = Math.fma(_t8, _t3, -(_t4 * _t6));
        float _t46 = Math.fma(_t28, _t28, Math.fma(_t30, _t30, _t32 * _t32));
        if (!(_t46 > 1.1754944E-38f && _t46 < Float.POSITIVE_INFINITY)) return barycentric_degenerate(pX, pY, pZ);
        Float3 _r = barycentric_s2b62622f_tail(_t46, pX - this.v0X, _t1, pY - this.v0Y, _t3, _t28, _t6, pZ - this.v0Z, _t30, _t32, _t4, _t5, _t8);
        return _r != null ? _r : barycentric_degenerate(pX, pY, pZ);
    }


    /**
     * Out-of-range path of {@code barycentric}: its methods leave here when the squared length of
     * the triangle's normal (quartic in its size) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Float3 barycentric_degenerate(Float3 p) {
        return barycentric_degenerate(p.x(), p.y(), p.z());
    }

    /** Private tail of {@code barycentric_degenerate}; reached only through it. */
    private Float3 barycentric_degenerate_s2b62622f_tail(float _t28, float _t42, float _t46, float _t53, float _t54, float _t55, float _t57, float _t58, float _t59, float _t56) {
        float _t60 = this.v2X * _t28 - _t42;
        float _t61 = this.v2Z * _t28 - _t46;
        float _t71 = unitScale(_t53, _t54, _t55);
        float _t78 = unitScale(Math.max(Math.abs(_t57), Math.abs(_t58)), Math.max(Math.abs(_t59), Math.abs(_t60)), Math.max(Math.abs(_t56), Math.abs(_t61)));
        float _t85 = _t56 * _t78;
        float _t86 = _t60 * _t78;
        float _t87 = _t57 * _t78;
        float _t88 = _t58 * _t78;
        float _t89 = _t61 * _t78;
        float _t90 = _t59 * _t78;
        float _t117 = Math.fma(_t87, _t85, -(_t88 * _t86));
        float _t118 = Math.fma(_t88, _t89, -(_t90 * _t85));
        float _t119 = Math.fma(_t90, _t86, -(_t87 * _t89));
        return barycentric_degenerate_s2b62622f_tail2(_t53 * _t71, _t85, _t54 * _t71, _t86, _t117, _t89, _t55 * _t71, _t118, _t119, _t78 / _t71 / Math.fma(_t117, _t117, Math.fma(_t118, _t118, _t119 * _t119)), _t87, _t88, _t90);
    }

    /** Private tail of {@code barycentric_degenerate}; reached only through it. */
    private Float3 barycentric_degenerate_s2b62622f_tail2(float _t75, float _t85, float _t76, float _t86, float _t117, float _t89, float _t77, float _t118, float _t119, float _sp0, float _t87, float _t88, float _t90) {
        float _t131 = Math.fma(Math.fma(_t75, _t85, -(_t76 * _t86)), _t117, Math.fma(Math.fma(_t76, _t89, -(_t77 * _t85)), _t118, Math.fma(_t77, _t86, -(_t75 * _t89)) * _t119)) * _sp0;
        float _t132 = Math.fma(Math.fma(_t76, _t87, -(_t75 * _t88)), _t117, Math.fma(Math.fma(_t75, _t90, -(_t77 * _t87)), _t119, Math.fma(_t77, _t88, -(_t76 * _t90)) * _t118)) * _sp0;
        float _sfx0 = 1.0f - _t131 - _t132;
        float _sfx1 = _t131;
        float _sfx2 = _t132;
        return new Float3(_sfx0, _sfx1, _sfx2);
    }


    /**
     * Out-of-range path of {@code barycentric}: its methods leave here when the squared length of
     * the triangle's normal (quartic in its size) is zero, NaN or outside the normal floating-point
     * range; reached only through them.
     */
    private Float3 barycentric_degenerate(float pX, float pY, float pZ) {
        float _t28 = Math.min(1.0f, unitScale(Math.max(Math.abs(Math.max(Math.abs(this.v0X), Math.abs(this.v0Y))), Math.abs(Math.max(Math.abs(this.v0Z), Math.abs(this.v1X)))), Math.max(Math.abs(Math.max(Math.abs(this.v1Y), Math.abs(this.v1Z))), Math.abs(Math.max(Math.abs(this.v2X), Math.abs(this.v2Y)))), Math.max(Math.abs(Math.max(Math.abs(this.v2Z), Math.abs(pX))), Math.abs(Math.max(Math.abs(pY), Math.abs(pZ))))));
        float _t42 = this.v0X * _t28;
        float _t44 = this.v0Y * _t28;
        float _t46 = this.v0Z * _t28;
        return barycentric_degenerate_s2b62622f_tail(_t28, _t42, _t46, pX * _t28 - _t42, pY * _t28 - _t44, pZ * _t28 - _t46, this.v1X * _t28 - _t42, this.v1Y * _t28 - _t44, this.v1Z * _t28 - _t46, this.v2Y * _t28 - _t44);
    }


    /**
     * Get the centroid (the average of the vertices) of this triangle, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getCentroid() {
        return new Float3(0.33333334f * this.v0X + 0.33333334f * this.v1X + 0.33333334f * this.v2X, 0.33333334f * this.v0Y + 0.33333334f * this.v1Y + 0.33333334f * this.v2Y, 0.33333334f * this.v0Z + 0.33333334f * this.v1Z + 0.33333334f * this.v2Z);
    }


    /**
     * Get the normal of this triangle, i.e. the cross product {@code (v1 - v0) x (v2 - v0)} (not
     * unit length: its length is twice the triangle's area), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getNormal() {
        float _t0 = this.v1Y - this.v0Y;
        float _t1 = this.v2Z - this.v0Z;
        float _t2 = this.v1Z - this.v0Z;
        float _t3 = this.v2Y - this.v0Y;
        float _t4 = this.v2X - this.v0X;
        float _t5 = this.v1X - this.v0X;
        return new Float3(Math.fma(_t0, _t1, -(_t2 * _t3)), Math.fma(_t2, _t4, -(_t5 * _t1)), Math.fma(_t5, _t3, -(_t0 * _t4)));
    }


    /**
     * Get the first vertex of this triangle, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getV0() {
        return new Float3(this.v0X, this.v0Y, this.v0Z);
    }


    /**
     * Get the second vertex of this triangle, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getV1() {
        return new Float3(this.v1X, this.v1Y, this.v1Z);
    }


    /**
     * Get the third vertex of this triangle, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getV2() {
        return new Float3(this.v2X, this.v2Y, this.v2Z);
    }

    /**
     * Determine whether the projection of the given point onto this triangle's plane lies inside or
     * on this triangle (boundary inclusive). Delegates to the shared {@code Intersectionf} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the x coordinate of the point
     * @param pY the y coordinate of the point
     * @param pZ the z coordinate of the point
     * @return {@code true} if the projection of the given point onto this triangle's plane lies
     *        inside or on this triangle, {@code false} otherwise
     */
    public boolean containsPoint(float pX, float pY, float pZ) {
        return Intersectionf.testPointInTriangle(pX, pY, pZ, v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z());
    }

    /**
     * Determine whether the projection of the given point onto this triangle's plane lies inside or
     * on this triangle (boundary inclusive). Delegates to the shared {@code Intersectionf} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to test
     * @return {@code true} if the projection of the given point onto this triangle's plane lies
     *        inside or on this triangle, {@code false} otherwise
     */
    public boolean containsPoint(Float3 p) {
        return containsPoint(p.x(), p.y(), p.z());
    }

    /**
     * Determine whether this triangle intersects the given ray. Delegates to the shared
     * {@code Intersectionf} kernels.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param ray the ray to test for intersection
     * @param epsilon the tolerance below which the ray counts as parallel to the triangle's plane,
     *        guarding the near-zero determinant
     * @return {@code true} if this triangle and the given ray intersect, {@code false} otherwise
     */
    public boolean intersectsRay(FloatRay ray, float epsilon) {
        return Intersectionf.testRayTriangle(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), epsilon);
    }

    /**
     * Determine whether this triangle intersects the given ray, front face only. Delegates to the
     * shared {@code Intersectionf} kernels.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param ray the ray to test for intersection
     * @param epsilon the tolerance below which the ray counts as parallel to the triangle's plane,
     *        guarding the near-zero determinant
     * @return {@code true} if the ray hits the front face of this triangle, {@code false} otherwise
     */
    public boolean intersectsRayFront(FloatRay ray, float epsilon) {
        return Intersectionf.testRayTriangleFront(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), epsilon);
    }

    /**
     * Compute the point on this triangle closest to the given point. Delegates to the shared
     * {@code Intersectionf} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to find the closest point to
     * @return the point on this triangle closest to the given point
     */
    public Float3 closestPointToPoint(Float3 p) {
        return Intersectionf.closestPointOnTriangle(v0X(), v0Y(), v0Z(), v1X(), v1Y(), v1Z(), v2X(), v2Y(), v2Z(), p.x(), p.y(), p.z());
    }

    /**
     * {@return a copy with the {@code v0X} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v0X} component
     */
    public FloatTriangle withV0X(float v) {
        return new FloatTriangle(v, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z);
    }

    /**
     * {@return a copy with the {@code v0Y} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v0Y} component
     */
    public FloatTriangle withV0Y(float v) {
        return new FloatTriangle(v0X, v, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v2Z);
    }

    /**
     * {@return a copy with the {@code v0Z} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v0Z} component
     */
    public FloatTriangle withV0Z(float v) {
        return new FloatTriangle(v0X, v0Y, v, v1X, v1Y, v1Z, v2X, v2Y, v2Z);
    }

    /**
     * {@return a copy with the {@code v1X} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v1X} component
     */
    public FloatTriangle withV1X(float v) {
        return new FloatTriangle(v0X, v0Y, v0Z, v, v1Y, v1Z, v2X, v2Y, v2Z);
    }

    /**
     * {@return a copy with the {@code v1Y} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v1Y} component
     */
    public FloatTriangle withV1Y(float v) {
        return new FloatTriangle(v0X, v0Y, v0Z, v1X, v, v1Z, v2X, v2Y, v2Z);
    }

    /**
     * {@return a copy with the {@code v1Z} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v1Z} component
     */
    public FloatTriangle withV1Z(float v) {
        return new FloatTriangle(v0X, v0Y, v0Z, v1X, v1Y, v, v2X, v2Y, v2Z);
    }

    /**
     * {@return a copy with the {@code v2X} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v2X} component
     */
    public FloatTriangle withV2X(float v) {
        return new FloatTriangle(v0X, v0Y, v0Z, v1X, v1Y, v1Z, v, v2Y, v2Z);
    }

    /**
     * {@return a copy with the {@code v2Y} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v2Y} component
     */
    public FloatTriangle withV2Y(float v) {
        return new FloatTriangle(v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v, v2Z);
    }

    /**
     * {@return a copy with the {@code v2Z} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code v2Z} component
     */
    public FloatTriangle withV2Z(float v) {
        return new FloatTriangle(v0X, v0Y, v0Z, v1X, v1Y, v1Z, v2X, v2Y, v);
    }

    @Override public String toString() {
        return "FloatTriangle(" + v0X() + ", " + v0Y() + ", " + v0Z() + ", " + v1X() + ", " + v1Y() + ", " + v1Z() + ", " + v2X() + ", " + v2Y() + ", " + v2Z() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatTriangle)) return false;
        FloatTriangle o = (FloatTriangle) obj;
        return Float.floatToIntBits(v0X) == Float.floatToIntBits(o.v0X)
            && Float.floatToIntBits(v0Y) == Float.floatToIntBits(o.v0Y)
            && Float.floatToIntBits(v0Z) == Float.floatToIntBits(o.v0Z)
            && Float.floatToIntBits(v1X) == Float.floatToIntBits(o.v1X)
            && Float.floatToIntBits(v1Y) == Float.floatToIntBits(o.v1Y)
            && Float.floatToIntBits(v1Z) == Float.floatToIntBits(o.v1Z)
            && Float.floatToIntBits(v2X) == Float.floatToIntBits(o.v2X)
            && Float.floatToIntBits(v2Y) == Float.floatToIntBits(o.v2Y)
            && Float.floatToIntBits(v2Z) == Float.floatToIntBits(o.v2Z);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(v0X);
        h = 31 * h + Float.floatToIntBits(v0Y);
        h = 31 * h + Float.floatToIntBits(v0Z);
        h = 31 * h + Float.floatToIntBits(v1X);
        h = 31 * h + Float.floatToIntBits(v1Y);
        h = 31 * h + Float.floatToIntBits(v1Z);
        h = 31 * h + Float.floatToIntBits(v2X);
        h = 31 * h + Float.floatToIntBits(v2Y);
        h = 31 * h + Float.floatToIntBits(v2Z);
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Float.isFinite(v0X)
            && Float.isFinite(v0Y)
            && Float.isFinite(v0Z)
            && Float.isFinite(v1X)
            && Float.isFinite(v1Y)
            && Float.isFinite(v1Z)
            && Float.isFinite(v2X)
            && Float.isFinite(v2Y)
            && Float.isFinite(v2Z);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Float.isNaN(v0X)
            || Float.isNaN(v0Y)
            || Float.isNaN(v0Z)
            || Float.isNaN(v1X)
            || Float.isNaN(v1Y)
            || Float.isNaN(v1Z)
            || Float.isNaN(v2X)
            || Float.isNaN(v2Y)
            || Float.isNaN(v2Z);
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
    public boolean equalsEpsilon(FloatTriangle other, float epsilon) {
        return Math.abs(v0X - other.v0X()) <= epsilon
            && Math.abs(v0Y - other.v0Y()) <= epsilon
            && Math.abs(v0Z - other.v0Z()) <= epsilon
            && Math.abs(v1X - other.v1X()) <= epsilon
            && Math.abs(v1Y - other.v1Y()) <= epsilon
            && Math.abs(v1Z - other.v1Z()) <= epsilon
            && Math.abs(v2X - other.v2X()) <= epsilon
            && Math.abs(v2Y - other.v2Y()) <= epsilon
            && Math.abs(v2Z - other.v2Z()) <= epsilon;
    }

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


    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] store(float[] dest, int offset) {
        dest[offset + 0] = this.v0X;
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
    public float[] store(float[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle load(float[] src, int offset) {
        float _c0 = src[offset + 0];
        float _c1 = src[offset + 1];
        float _c2 = src[offset + 2];
        float _c3 = src[offset + 3];
        float _c4 = src[offset + 4];
        float _c5 = src[offset + 5];
        float _c6 = src[offset + 6];
        float _c7 = src[offset + 7];
        float _c8 = src[offset + 8];
        return new FloatTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle load(float[] src) { return load(src, 0); }

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
    public FloatBuffer store(FloatBuffer buf) {
        return storeAbsolute(buf.position(), buf);
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
    public FloatBuffer storeAbsolute(int index, FloatBuffer buf) {
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
    public FloatBuffer storeRelative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
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
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle load(FloatBuffer buf) {
        return loadAbsolute(buf.position(), buf);
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
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle loadAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code FloatTriangle} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatTriangle loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatTriangle r = loadAbsolute(pos, buf);
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
        return storeAbsolute(buf.position(), buf);
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
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 36);
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
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle load(ByteBuffer buf) {
        return loadAbsolute(buf.position(), buf);
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
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code FloatTriangle} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatTriangle loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatTriangle r = loadAbsolute(pos, buf);
        buf.position(pos + 36);
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
    public FloatTriangle storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle loadUnsafe(long address) {
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
    public MemorySegment store(MemorySegment dest) { return store(0L, dest); }

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
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle load(MemorySegment src) { return load(0L, src); }

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
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(offset, src);
    }


    /**
     * Store the elements into the given array, converting each element to {@code double}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] store(double[] dest, int offset) {
        dest[offset + 0] = this.v0X;
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
     * Store the elements into the given array, converting each element to {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code double}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle load(double[] src, int offset) {
        float _c0 = (float) src[offset + 0];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[offset + 2];
        float _c3 = (float) src[offset + 3];
        float _c4 = (float) src[offset + 4];
        float _c5 = (float) src[offset + 5];
        float _c6 = (float) src[offset + 6];
        float _c7 = (float) src[offset + 7];
        float _c8 = (float) src[offset + 8];
        return new FloatTriangle(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Load the elements from the given array, converting each element from {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle load(double[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code double}, starting
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
    public DoubleBuffer store(DoubleBuffer buf) {
        return storeAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code double}, starting
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
    public DoubleBuffer storeAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code double}, starting
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
    public DoubleBuffer storeRelative(DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
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
     * @param buf the source buffer
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle load(DoubleBuffer buf) {
        return loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
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
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
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
     * @param buf the source buffer
     * @return a new {@code FloatTriangle} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatTriangle loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatTriangle r = loadAbsolute(pos, buf);
        buf.position(pos + 9);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
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
    public ByteBuffer storeDouble(ByteBuffer buf) {
        return storeDoubleAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
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
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
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
    public ByteBuffer storeDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeDoubleAbsolute(pos, buf);
        buf.position(pos + 72);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
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
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle loadDouble(ByteBuffer buf) {
        return loadDoubleAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
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
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
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
     * @return a new {@code FloatTriangle} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatTriangle loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatTriangle r = loadDoubleAbsolute(pos, buf);
        buf.position(pos + 72);
        return r;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public FloatTriangle storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment, converting each element to {@code double}.
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
    public MemorySegment storeDouble(MemorySegment dest) { return storeDouble(0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code double},
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
    public MemorySegment storeDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment, converting each element from {@code double}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle loadDouble(MemorySegment src) { return loadDouble(0L, src); }

    /**
     * Load the elements from the given memory segment, converting each element from {@code double},
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
     * @return a new {@code FloatTriangle} holding the loaded elements
     */
    public static FloatTriangle loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(offset, src);
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

    /** Double-precision twin of {@link #unitScale(float, float, float)}. */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }
}
