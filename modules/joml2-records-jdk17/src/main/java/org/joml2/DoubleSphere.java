// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.ByteOrder;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

/**
 * Immutable sphere of double-precision {@code double} components.
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
 * compares equal to an equal infinity (the difference {@code Inf - Inf} is NaN), and to a finite
 * one only under an infinite {@code epsilon}; a NaN component never compares equal to anything.
 *
 * @param x the {@code x} component
 * @param y the {@code y} component
 * @param z the {@code z} component
 * @param r the {@code r} component
 */
public record DoubleSphere(double x, double y, double z, double r) {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 32;

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     * @param r the {@code r} component
     */
    public DoubleSphere(double x, double y, double z, double r) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
    }

    /**
     * Create a new instance initialized to all zeros.
     * <p>
     * Valid input: the method reads no input.
     */
    public DoubleSphere() {
        this(0, 0, 0, 0);
    }

    /**
     * Create a sphere from its center and radius.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     */
    public DoubleSphere(Double3 center, double radius) {
        this(center.x(), center.y(), center.z(), radius);
    }

    /**
     * Create an array of {@code length} elements, each the instance with all components 0.
     * <p>
     * An ordinary array, filled: the value variant ({@code joml2-value}) hands out a flat one,
     * elements stored inline, where the application allows it.
     * <p>
     * Valid input: {@code length} must not be negative.
     *
     * @param length the number of elements
     * @return the new array
     * @throws NegativeArraySizeException if {@code length} is negative
     */
    public static DoubleSphere[] newArray(int length) {
        DoubleSphere[] a = new DoubleSphere[length];
        java.util.Arrays.fill(a, new DoubleSphere(0, 0, 0, 0));
        return a;
    }

    /**
     * Create an array of {@code length} elements, each {@code init}.
     * <p>
     * An ordinary array, filled: the value variant ({@code joml2-value}) hands out a flat one,
     * elements stored inline, where the application allows it.
     * <p>
     * Valid input: {@code length} must not be negative.
     *
     * @param length the number of elements
     * @param init the value every element starts as
     * @return the new array
     * @throws NullPointerException if {@code init} is null
     * @throws NegativeArraySizeException if {@code length} is negative
     */
    public static DoubleSphere[] newArray(int length, DoubleSphere init) {
        java.util.Objects.requireNonNull(init, "init");
        DoubleSphere[] a = new DoubleSphere[length];
        java.util.Arrays.fill(a, init);
        return a;
    }

    /** {@return the {@code x} component} <p>Valid input: any value, NaN and the infinities included. */
    public double x() { return x; }

    /** {@return the {@code y} component} <p>Valid input: any value, NaN and the infinities included. */
    public double y() { return y; }

    /** {@return the {@code z} component} <p>Valid input: any value, NaN and the infinities included. */
    public double z() { return z; }

    /** {@return the {@code r} component} <p>Valid input: any value, NaN and the infinities included. */
    public double r() { return r; }

    /**
     * Create a new sphere from its center and radius.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @return the resulting sphere
     */
    public DoubleSphere set(Double3 center, double radius) {
        return new DoubleSphere(center, radius);
    }

    /**
     * Return a sphere with the values of {@code v}: that sphere itself (an immutable value needs no
     * copy).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the sphere to copy
     * @return the resulting sphere
     */
    public DoubleSphere set(DoubleSphere v) {
        double vX = v.x;
        double vY = v.y;
        double vZ = v.z;
        double vR = v.r;
        return new DoubleSphere(vX, vY, vZ, vR);
    }

    /**
     * Create a new sphere from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the sphere {@code (vX, vY, vZ, vR)}
     * @param vY the {@code y} component of the sphere {@code (vX, vY, vZ, vR)}
     * @param vZ the {@code z} component of the sphere {@code (vX, vY, vZ, vR)}
     * @param vR the {@code r} component of the sphere {@code (vX, vY, vZ, vR)}
     * @return the resulting sphere
     */
    public DoubleSphere set(double vX, double vY, double vZ, double vR) {
        return new DoubleSphere(vX, vY, vZ, vR);
    }

    /**
     * Set the center of this sphere to {@code c}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param c the new center
     * @return the resulting sphere
     */
    public DoubleSphere setCenter(Double3 c) {
        double cX = c.x();
        double cY = c.y();
        double cZ = c.z();
        return new DoubleSphere(cX, cY, cZ, this.r);
    }

    /**
     * Set the center of this sphere to ({@code cX}, {@code cY}, {@code cZ}), returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @return the resulting sphere
     */
    public DoubleSphere setCenter(double cX, double cY, double cZ) {
        return new DoubleSphere(cX, cY, cZ, this.r);
    }

    /**
     * Set the radius of this sphere to {@code radius}, returning the result as a value.
     * <p>
     * Valid input: {@code radius} must not be negative.
     *
     * @param radius the radius
     * @return the resulting sphere
     */
    public DoubleSphere setRadius(double radius) {
        return new DoubleSphere(this.x, this.y, this.z, radius);
    }

    /**
     * Convert this sphere to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code FloatSphere} holding the result
     */
    public FloatSphere toFloat() {
        return new FloatSphere((float) (this.x), (float) (this.y), (float) (this.z), (float) (this.r));
    }

    /** Private tail of {@code transform}; reached only through it. */
    private DoubleSphere transform_s91e96b_tail_fma(Double3x4 m, double _t9, double _t10) {
        double _m_m21 = m.m21();
        double _m_m22 = m.m22();
        double _m_m01 = m.m01();
        double _m_m02 = m.m02();
        double _m_m11 = m.m11();
        double _m_m12 = m.m12();
        double _t11 = java.lang.Math.abs(java.lang.Math.fma(_m_m21, _m_m22, java.lang.Math.fma(_m_m01, _m_m02, _m_m11 * _m_m12)));
        double _m_m00 = m.m00();
        double _m_m10 = m.m10();
        double _m_m20 = m.m20();
        return new DoubleSphere(java.lang.Math.fma(_m_m02, this.z, java.lang.Math.fma(_m_m00, this.x, java.lang.Math.fma(_m_m01, this.y, m.m03()))), java.lang.Math.fma(_m_m12, this.z, java.lang.Math.fma(_m_m10, this.x, java.lang.Math.fma(_m_m11, this.y, m.m13()))), java.lang.Math.fma(_m_m22, this.z, java.lang.Math.fma(_m_m20, this.x, java.lang.Math.fma(_m_m21, this.y, m.m23()))), this.r * java.lang.Math.sqrt(Math.max(Math.max(java.lang.Math.fma(_m_m00, _m_m00, java.lang.Math.fma(_m_m10, _m_m10, java.lang.Math.fma(_m_m20, _m_m20, _t9 + _t10))), java.lang.Math.fma(_m_m01, _m_m01, java.lang.Math.fma(_m_m11, _m_m11, java.lang.Math.fma(_m_m21, _m_m21, _t9 + _t11)))), java.lang.Math.fma(_m_m02, _m_m02, java.lang.Math.fma(_m_m12, _m_m12, java.lang.Math.fma(_m_m22, _m_m22, _t10 + _t11))))));
    }

    /** Private tail of {@code transform}; reached only through it. */
    private DoubleSphere transform_s91e96b_tail_mulAdd(Double3x4 m, double _t9, double _t10) {
        double _m_m21 = m.m21();
        double _m_m22 = m.m22();
        double _m_m01 = m.m01();
        double _m_m02 = m.m02();
        double _m_m11 = m.m11();
        double _m_m12 = m.m12();
        double _t11 = java.lang.Math.abs(((_m_m21) * (_m_m22) + (((_m_m01) * (_m_m02) + (_m_m11 * _m_m12)))));
        double _m_m00 = m.m00();
        double _m_m10 = m.m10();
        double _m_m20 = m.m20();
        return new DoubleSphere(((_m_m02) * (this.z) + (((_m_m00) * (this.x) + (((_m_m01) * (this.y) + (m.m03())))))), ((_m_m12) * (this.z) + (((_m_m10) * (this.x) + (((_m_m11) * (this.y) + (m.m13())))))), ((_m_m22) * (this.z) + (((_m_m20) * (this.x) + (((_m_m21) * (this.y) + (m.m23())))))), this.r * java.lang.Math.sqrt(Math.max(Math.max(((_m_m00) * (_m_m00) + (((_m_m10) * (_m_m10) + (((_m_m20) * (_m_m20) + (_t9 + _t10)))))), ((_m_m01) * (_m_m01) + (((_m_m11) * (_m_m11) + (((_m_m21) * (_m_m21) + (_t9 + _t11))))))), ((_m_m02) * (_m_m02) + (((_m_m12) * (_m_m12) + (((_m_m22) * (_m_m22) + (_t10 + _t11)))))))));
    }

    /**
     * Transform this sphere by {@code m}, scaling the radius by an upper bound on the matrix's
     * largest stretch so that the result contains the transformed sphere, returning the result as a
     * value.
     * <p>
     * The radius factor is the square root of the largest absolute row sum of {@code M^T M} over
     * the upper-left 3x3: exact when the matrix maps the coordinate axes to perpendicular
     * directions (a rotation applied after an axis scale, {@code M = R * S}), and at most about
     * 1.17 times too large for a scale applied after the rotation or a shear. (The largest column
     * length would be no bound once the scale is applied after the rotation.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @return the resulting sphere
     */
    public DoubleSphere transform(Double3x4 m) {
        double _m_m20 = m.m20();
        double _m_m21 = m.m21();
        double _m_m00 = m.m00();
        double _m_m01 = m.m01();
        double _m_m10 = m.m10();
        double _m_m11 = m.m11();
        double _m_m22 = m.m22();
        double _m_m02 = m.m02();
        double _m_m12 = m.m12();
        if (Math.useFma()) {
            return transform_s91e96b_tail_fma(m, java.lang.Math.abs(java.lang.Math.fma(_m_m20, _m_m21, java.lang.Math.fma(_m_m00, _m_m01, _m_m10 * _m_m11))), java.lang.Math.abs(java.lang.Math.fma(_m_m20, _m_m22, java.lang.Math.fma(_m_m00, _m_m02, _m_m10 * _m_m12))));
        } else {
            return transform_s91e96b_tail_mulAdd(m, java.lang.Math.abs(((_m_m20) * (_m_m21) + (((_m_m00) * (_m_m01) + (_m_m10 * _m_m11))))), java.lang.Math.abs(((_m_m20) * (_m_m22) + (((_m_m00) * (_m_m02) + (_m_m10 * _m_m12))))));
        }
    }

    /** Private tail of {@code transform}; reached only through it. */
    private DoubleSphere transform_sa000ec_tail_fma(Double4x4 m, double _t9, double _t10) {
        double _m_m21 = m.m21();
        double _m_m22 = m.m22();
        double _m_m01 = m.m01();
        double _m_m02 = m.m02();
        double _m_m11 = m.m11();
        double _m_m12 = m.m12();
        double _t11 = java.lang.Math.abs(java.lang.Math.fma(_m_m21, _m_m22, java.lang.Math.fma(_m_m01, _m_m02, _m_m11 * _m_m12)));
        double _m_m00 = m.m00();
        double _m_m10 = m.m10();
        double _m_m20 = m.m20();
        return new DoubleSphere(java.lang.Math.fma(_m_m02, this.z, java.lang.Math.fma(_m_m00, this.x, java.lang.Math.fma(_m_m01, this.y, m.m03()))), java.lang.Math.fma(_m_m12, this.z, java.lang.Math.fma(_m_m10, this.x, java.lang.Math.fma(_m_m11, this.y, m.m13()))), java.lang.Math.fma(_m_m22, this.z, java.lang.Math.fma(_m_m20, this.x, java.lang.Math.fma(_m_m21, this.y, m.m23()))), this.r * java.lang.Math.sqrt(Math.max(Math.max(java.lang.Math.fma(_m_m00, _m_m00, java.lang.Math.fma(_m_m10, _m_m10, java.lang.Math.fma(_m_m20, _m_m20, _t9 + _t10))), java.lang.Math.fma(_m_m01, _m_m01, java.lang.Math.fma(_m_m11, _m_m11, java.lang.Math.fma(_m_m21, _m_m21, _t9 + _t11)))), java.lang.Math.fma(_m_m02, _m_m02, java.lang.Math.fma(_m_m12, _m_m12, java.lang.Math.fma(_m_m22, _m_m22, _t10 + _t11))))));
    }

    /** Private tail of {@code transform}; reached only through it. */
    private DoubleSphere transform_sa000ec_tail_mulAdd(Double4x4 m, double _t9, double _t10) {
        double _m_m21 = m.m21();
        double _m_m22 = m.m22();
        double _m_m01 = m.m01();
        double _m_m02 = m.m02();
        double _m_m11 = m.m11();
        double _m_m12 = m.m12();
        double _t11 = java.lang.Math.abs(((_m_m21) * (_m_m22) + (((_m_m01) * (_m_m02) + (_m_m11 * _m_m12)))));
        double _m_m00 = m.m00();
        double _m_m10 = m.m10();
        double _m_m20 = m.m20();
        return new DoubleSphere(((_m_m02) * (this.z) + (((_m_m00) * (this.x) + (((_m_m01) * (this.y) + (m.m03())))))), ((_m_m12) * (this.z) + (((_m_m10) * (this.x) + (((_m_m11) * (this.y) + (m.m13())))))), ((_m_m22) * (this.z) + (((_m_m20) * (this.x) + (((_m_m21) * (this.y) + (m.m23())))))), this.r * java.lang.Math.sqrt(Math.max(Math.max(((_m_m00) * (_m_m00) + (((_m_m10) * (_m_m10) + (((_m_m20) * (_m_m20) + (_t9 + _t10)))))), ((_m_m01) * (_m_m01) + (((_m_m11) * (_m_m11) + (((_m_m21) * (_m_m21) + (_t9 + _t11))))))), ((_m_m02) * (_m_m02) + (((_m_m12) * (_m_m12) + (((_m_m22) * (_m_m22) + (_t10 + _t11)))))))));
    }

    /**
     * Transform this sphere by {@code m}, scaling the radius by an upper bound on the matrix's
     * largest stretch so that the result contains the transformed sphere, returning the result as a
     * value.
     * <p>
     * The radius factor is the square root of the largest absolute row sum of {@code M^T M} over
     * the upper-left 3x3: exact when the matrix maps the coordinate axes to perpendicular
     * directions (a rotation applied after an axis scale, {@code M = R * S}), and at most about
     * 1.17 times too large for a scale applied after the rotation or a shear. (The largest column
     * length would be no bound once the scale is applied after the rotation.)
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @return the resulting sphere
     */
    public DoubleSphere transform(Double4x4 m) {
        double _m_m20 = m.m20();
        double _m_m21 = m.m21();
        double _m_m00 = m.m00();
        double _m_m01 = m.m01();
        double _m_m10 = m.m10();
        double _m_m11 = m.m11();
        double _m_m22 = m.m22();
        double _m_m02 = m.m02();
        double _m_m12 = m.m12();
        if (Math.useFma()) {
            return transform_sa000ec_tail_fma(m, java.lang.Math.abs(java.lang.Math.fma(_m_m20, _m_m21, java.lang.Math.fma(_m_m00, _m_m01, _m_m10 * _m_m11))), java.lang.Math.abs(java.lang.Math.fma(_m_m20, _m_m22, java.lang.Math.fma(_m_m00, _m_m02, _m_m10 * _m_m12))));
        } else {
            return transform_sa000ec_tail_mulAdd(m, java.lang.Math.abs(((_m_m20) * (_m_m21) + (((_m_m00) * (_m_m01) + (_m_m10 * _m_m11))))), java.lang.Math.abs(((_m_m20) * (_m_m22) + (((_m_m00) * (_m_m02) + (_m_m10 * _m_m12))))));
        }
    }

    /**
     * Translate this sphere by {@code delta}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @return the resulting sphere
     */
    public DoubleSphere translate(Double3 delta) {
        double deltaX = delta.x();
        double deltaY = delta.y();
        double deltaZ = delta.z();
        return new DoubleSphere(deltaX + this.x, deltaY + this.y, deltaZ + this.z, this.r);
    }

    /**
     * Translate this sphere by ({@code deltaX}, {@code deltaY}, {@code deltaZ}), returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaZ the {@code z} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @return the resulting sphere
     */
    public DoubleSphere translate(double deltaX, double deltaY, double deltaZ) {
        return new DoubleSphere(deltaX + this.x, deltaY + this.y, deltaZ + this.z, this.r);
    }

    /**
     * Compute the point of this sphere closest to the given point. For a point inside or on the
     * sphere, the result is the point itself; otherwise it is the point on the surface in the
     * direction from the center toward the given point.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param p the point to find the closest point to
     * @return the resulting vector
     */
    public Double3 closestPointToPoint(Double3 p) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        if (Math.useFma()) {
            double _t0 = pZ - this.z;
            double _t1 = pX - this.x;
            double _t2 = pY - this.y;
            double _t6 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
            double _t8 = this.r * (1.0 / java.lang.Math.sqrt(_t6));
            if (_t6 <= this.r * this.r) {
                return new Double3(pX, pY, pZ);
            } else {
                return new Double3(java.lang.Math.fma(_t1, _t8, this.x), java.lang.Math.fma(_t2, _t8, this.y), java.lang.Math.fma(_t0, _t8, this.z));
            }
        } else {
            double _t0 = pZ - this.z;
            double _t1 = pX - this.x;
            double _t2 = pY - this.y;
            double _t6 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
            double _t8 = this.r * (1.0 / java.lang.Math.sqrt(_t6));
            if (_t6 <= this.r * this.r) {
                return new Double3(pX, pY, pZ);
            } else {
                return new Double3(((_t1) * (_t8) + (this.x)), ((_t2) * (_t8) + (this.y)), ((_t0) * (_t8) + (this.z)));
            }
        }
    }

    /**
     * Compute the point of this sphere closest to the given point. For a point inside or on the
     * sphere, the result is the point itself; otherwise it is the point on the surface in the
     * direction from the center toward the given point.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @return the resulting vector
     */
    public Double3 closestPointToPoint(double pX, double pY, double pZ) {
        if (Math.useFma()) {
            double _t0 = pZ - this.z;
            double _t1 = pX - this.x;
            double _t2 = pY - this.y;
            double _t6 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
            double _t8 = this.r * (1.0 / java.lang.Math.sqrt(_t6));
            if (_t6 <= this.r * this.r) {
                return new Double3(pX, pY, pZ);
            } else {
                return new Double3(java.lang.Math.fma(_t1, _t8, this.x), java.lang.Math.fma(_t2, _t8, this.y), java.lang.Math.fma(_t0, _t8, this.z));
            }
        } else {
            double _t0 = pZ - this.z;
            double _t1 = pX - this.x;
            double _t2 = pY - this.y;
            double _t6 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
            double _t8 = this.r * (1.0 / java.lang.Math.sqrt(_t6));
            if (_t6 <= this.r * this.r) {
                return new Double3(pX, pY, pZ);
            } else {
                return new Double3(((_t1) * (_t8) + (this.x)), ((_t2) * (_t8) + (this.y)), ((_t0) * (_t8) + (this.z)));
            }
        }
    }

    /**
     * Compute the squared distance between this sphere and the given axis-aligned box, i.e. the
     * square of the distance from the box to the center minus the radius, clamped at zero; zero
     * when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of {@code aabb} must not exceed the maximum corner of {@code aabb} in any
     * component.
     *
     * @param aabb the axis-aligned box to measure the distance to
     * @return the squared distance between this sphere and the given axis-aligned box, i.e. the
     *        square of the distance from the box to the center minus the radius, clamped at zero;
     *        zero when they overlap or touch
     */
    public double distanceSquaredToAABB(DoubleAABB aabb) {
        double minX = aabb.minX();
        double minY = aabb.minY();
        double minZ = aabb.minZ();
        double maxX = aabb.maxX();
        double maxY = aabb.maxY();
        double maxZ = aabb.maxZ();
        if (Math.useFma()) {
            double _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            double _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            double _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            double _t14 = Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
            return _t14 * _t14;
        } else {
            double _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            double _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            double _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            double _t14 = Math.max(0.0, java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
            return _t14 * _t14;
        }
    }

    /**
     * Compute the squared distance between this sphere and the given axis-aligned box, i.e. the
     * square of the distance from the box to the center minus the radius, clamped at zero; zero
     * when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153};
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
    public double distanceSquaredToAABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (Math.useFma()) {
            double _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            double _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            double _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            double _t14 = Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
            return _t14 * _t14;
        } else {
            double _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            double _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            double _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            double _t14 = Math.max(0.0, java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
            return _t14 * _t14;
        }
    }

    /**
     * Compute the squared distance between this sphere and the given axis-aligned box, i.e. the
     * square of the distance from the box to the center minus the radius, clamped at zero; zero
     * when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153};
     * {@code min} must not exceed {@code max} in any component.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     * @return the squared distance between this sphere and the given axis-aligned box, i.e. the
     *        square of the distance from the box to the center minus the radius, clamped at zero;
     *        zero when they overlap or touch
     */
    public double distanceSquaredToAABB(Double3 min, Double3 max) {
        double minX = min.x();
        double minY = min.y();
        double minZ = min.z();
        double maxX = max.x();
        double maxY = max.y();
        double maxZ = max.z();
        if (Math.useFma()) {
            double _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            double _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            double _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            double _t14 = Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
            return _t14 * _t14;
        } else {
            double _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            double _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            double _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            double _t14 = Math.max(0.0, java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
            return _t14 * _t14;
        }
    }

    /**
     * Compute the squared distance between this sphere and the given point, i.e. the square of the
     * distance from the point to the center minus the radius, clamped at zero; zero for a point
     * inside or on the sphere.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this sphere and the given point, i.e. the square of the
     *        distance from the point to the center minus the radius, clamped at zero; zero for a
     *        point inside or on the sphere
     */
    public double distanceSquaredToPoint(Double3 p) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        if (Math.useFma()) {
            double _t0 = pZ - this.z;
            double _t1 = pX - this.x;
            double _t2 = pY - this.y;
            double _t8 = Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r);
            return _t8 * _t8;
        } else {
            double _t0 = pZ - this.z;
            double _t1 = pX - this.x;
            double _t2 = pY - this.y;
            double _t8 = Math.max(0.0, java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r);
            return _t8 * _t8;
        }
    }

    /**
     * Compute the squared distance between this sphere and the given point, i.e. the square of the
     * distance from the point to the center minus the radius, clamped at zero; zero for a point
     * inside or on the sphere.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
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
    public double distanceSquaredToPoint(double pX, double pY, double pZ) {
        if (Math.useFma()) {
            double _t0 = pZ - this.z;
            double _t1 = pX - this.x;
            double _t2 = pY - this.y;
            double _t8 = Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r);
            return _t8 * _t8;
        } else {
            double _t0 = pZ - this.z;
            double _t1 = pX - this.x;
            double _t2 = pY - this.y;
            double _t8 = Math.max(0.0, java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r);
            return _t8 * _t8;
        }
    }

    /**
     * Compute the squared distance between this sphere and the given sphere, i.e. the square of the
     * distance between the centers minus both radii, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param other the sphere to measure the distance to
     * @return the squared distance between this sphere and the given sphere, i.e. the square of the
     *        distance between the centers minus both radii, clamped at zero; zero when they overlap
     *        or touch
     */
    public double distanceSquaredToSphere(DoubleSphere other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        double otherR = other.r;
        if (Math.useFma()) {
            double _t0 = otherZ - this.z;
            double _t1 = otherX - this.x;
            double _t2 = otherY - this.y;
            double _t9 = Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r - otherR);
            return _t9 * _t9;
        } else {
            double _t0 = otherZ - this.z;
            double _t1 = otherX - this.x;
            double _t2 = otherY - this.y;
            double _t9 = Math.max(0.0, java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r - otherR);
            return _t9 * _t9;
        }
    }

    /**
     * Compute the squared distance between this sphere and the given sphere, i.e. the square of the
     * distance between the centers minus both radii, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
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
    public double distanceSquaredToSphere(double otherX, double otherY, double otherZ, double otherR) {
        if (Math.useFma()) {
            double _t0 = otherZ - this.z;
            double _t1 = otherX - this.x;
            double _t2 = otherY - this.y;
            double _t9 = Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r - otherR);
            return _t9 * _t9;
        } else {
            double _t0 = otherZ - this.z;
            double _t1 = otherX - this.x;
            double _t2 = otherY - this.y;
            double _t9 = Math.max(0.0, java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r - otherR);
            return _t9 * _t9;
        }
    }

    /**
     * Compute the distance between this sphere and the given axis-aligned box, i.e. the distance
     * from the box to the center minus the radius, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of {@code aabb} must not exceed the maximum corner of {@code aabb} in any
     * component.
     *
     * @param aabb the axis-aligned box to measure the distance to
     * @return the distance between this sphere and the given axis-aligned box, i.e. the distance
     *        from the box to the center minus the radius, clamped at zero; zero when they overlap
     *        or touch
     */
    public double distanceToAABB(DoubleAABB aabb) {
        double minX = aabb.minX();
        double minY = aabb.minY();
        double minZ = aabb.minZ();
        double maxX = aabb.maxX();
        double maxY = aabb.maxY();
        double maxZ = aabb.maxZ();
        if (Math.useFma()) {
            double _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            double _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            double _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
        } else {
            double _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            double _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            double _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0, java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
        }
    }

    /**
     * Compute the distance between this sphere and the given axis-aligned box, i.e. the distance
     * from the box to the center minus the radius, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153};
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
    public double distanceToAABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (Math.useFma()) {
            double _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            double _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            double _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
        } else {
            double _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            double _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            double _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0, java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
        }
    }

    /**
     * Compute the distance between this sphere and the given axis-aligned box, i.e. the distance
     * from the box to the center minus the radius, clamped at zero; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153};
     * {@code min} must not exceed {@code max} in any component.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     * @return the distance between this sphere and the given axis-aligned box, i.e. the distance
     *        from the box to the center minus the radius, clamped at zero; zero when they overlap
     *        or touch
     */
    public double distanceToAABB(Double3 min, Double3 max) {
        double minX = min.x();
        double minY = min.y();
        double minZ = min.z();
        double maxX = max.x();
        double maxY = max.y();
        double maxZ = max.z();
        if (Math.useFma()) {
            double _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            double _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            double _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
        } else {
            double _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            double _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            double _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0, java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
        }
    }

    /**
     * Compute the distance between this sphere and the given plane, i.e. the distance from the
     * center to the plane minus the radius, clamped at zero; zero when the plane intersects or
     * touches the sphere. The plane's normal need not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of {@code plane} must be non-zero.
     *
     * @param plane the plane to measure the distance to
     * @return the distance between this sphere and the given plane, i.e. the distance from the
     *        center to the plane minus the radius, clamped at zero; zero when the plane intersects
     *        or touches the sphere. The plane's normal need not be of unit length
     */
    public double distanceToPlane(DoublePlane plane) {
        double planeA = plane.a();
        double planeB = plane.b();
        double planeC = plane.c();
        double planeD = plane.d();
        if (Math.useFma()) {
            return Math.max(0.0, java.lang.Math.fma((1.0 / java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))), java.lang.Math.abs(java.lang.Math.fma(planeA, this.x, java.lang.Math.fma(planeB, this.y, java.lang.Math.fma(planeC, this.z, planeD)))), -this.r));
        } else {
            return Math.max(0.0, (((1.0 / java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB))))))) * (java.lang.Math.abs(((planeA) * (this.x) + (((planeB) * (this.y) + (((planeC) * (this.z) + (planeD)))))))) - (this.r)));
        }
    }

    /**
     * Compute the distance between this sphere and the given plane, i.e. the distance from the
     * center to the plane minus the radius, clamped at zero; zero when the plane intersects or
     * touches the sphere. The plane's normal need not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of {@code (planeA, planeB, planeC, planeD)} must be non-zero.
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
    public double distanceToPlane(double planeA, double planeB, double planeC, double planeD) {
        if (Math.useFma()) {
            return Math.max(0.0, java.lang.Math.fma((1.0 / java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))), java.lang.Math.abs(java.lang.Math.fma(planeA, this.x, java.lang.Math.fma(planeB, this.y, java.lang.Math.fma(planeC, this.z, planeD)))), -this.r));
        } else {
            return Math.max(0.0, (((1.0 / java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB))))))) * (java.lang.Math.abs(((planeA) * (this.x) + (((planeB) * (this.y) + (((planeC) * (this.z) + (planeD)))))))) - (this.r)));
        }
    }

    /**
     * Compute the distance between this sphere and the given plane, i.e. the distance from the
     * center to the plane minus the radius, clamped at zero; zero when the plane intersects or
     * touches the sphere. The plane's normal need not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of {@code plane} must be non-zero.
     *
     * @param plane the plane to measure the distance to
     * @return the distance between this sphere and the given plane, i.e. the distance from the
     *        center to the plane minus the radius, clamped at zero; zero when the plane intersects
     *        or touches the sphere. The plane's normal need not be of unit length
     */
    public double distanceToPlane(Double4 plane) {
        double planeA = plane.x();
        double planeB = plane.y();
        double planeC = plane.z();
        double planeD = plane.w();
        if (Math.useFma()) {
            return Math.max(0.0, java.lang.Math.fma((1.0 / java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))), java.lang.Math.abs(java.lang.Math.fma(planeA, this.x, java.lang.Math.fma(planeB, this.y, java.lang.Math.fma(planeC, this.z, planeD)))), -this.r));
        } else {
            return Math.max(0.0, (((1.0 / java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB))))))) * (java.lang.Math.abs(((planeA) * (this.x) + (((planeB) * (this.y) + (((planeC) * (this.z) + (planeD)))))))) - (this.r)));
        }
    }

    /**
     * Compute the distance between this sphere and the given point, i.e. the distance from the
     * point to the center minus the radius, clamped at zero; zero for a point inside or on the
     * sphere.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param p the point to measure the distance to
     * @return the distance between this sphere and the given point, i.e. the distance from the
     *        point to the center minus the radius, clamped at zero; zero for a point inside or on
     *        the sphere
     */
    public double distanceToPoint(Double3 p) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        if (Math.useFma()) {
            double _t0 = pZ - this.z;
            double _t1 = pX - this.x;
            double _t2 = pY - this.y;
            return Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r);
        } else {
            double _t0 = pZ - this.z;
            double _t1 = pX - this.x;
            double _t2 = pY - this.y;
            return Math.max(0.0, java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r);
        }
    }

    /**
     * Compute the distance between this sphere and the given point, i.e. the distance from the
     * point to the center minus the radius, clamped at zero; zero for a point inside or on the
     * sphere.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
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
    public double distanceToPoint(double pX, double pY, double pZ) {
        if (Math.useFma()) {
            double _t0 = pZ - this.z;
            double _t1 = pX - this.x;
            double _t2 = pY - this.y;
            return Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r);
        } else {
            double _t0 = pZ - this.z;
            double _t1 = pX - this.x;
            double _t2 = pY - this.y;
            return Math.max(0.0, java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r);
        }
    }

    /**
     * Compute the distance between this sphere and the given sphere, i.e. the distance between the
     * centers minus both radii, clamped at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param other the sphere to measure the distance to
     * @return the distance between this sphere and the given sphere, i.e. the distance between the
     *        centers minus both radii, clamped at zero; zero when they overlap or touch
     */
    public double distanceToSphere(DoubleSphere other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        double otherR = other.r;
        if (Math.useFma()) {
            double _t0 = otherZ - this.z;
            double _t1 = otherX - this.x;
            double _t2 = otherY - this.y;
            return Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r - otherR);
        } else {
            double _t0 = otherZ - this.z;
            double _t1 = otherX - this.x;
            double _t2 = otherY - this.y;
            return Math.max(0.0, java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r - otherR);
        }
    }

    /**
     * Compute the distance between this sphere and the given sphere, i.e. the distance between the
     * centers minus both radii, clamped at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
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
    public double distanceToSphere(double otherX, double otherY, double otherZ, double otherR) {
        if (Math.useFma()) {
            double _t0 = otherZ - this.z;
            double _t1 = otherX - this.x;
            double _t2 = otherY - this.y;
            return Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r - otherR);
        } else {
            double _t0 = otherZ - this.z;
            double _t1 = otherX - this.x;
            double _t2 = otherY - this.y;
            return Math.max(0.0, java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r - otherR);
        }
    }

    /**
     * Get the center of this sphere, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getCenter() {
        return new Double3(this.x, this.y, this.z);
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
        return this.r >= 0.0;
    }

    /**
     * Compute the signed distance between the given point and the surface of this sphere, i.e. the
     * distance from the point to the center minus the radius: positive outside, zero on the surface
     * and negative inside.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param p the point to measure the distance to
     * @return the signed distance between the given point and the surface of this sphere, i.e. the
     *        distance from the point to the center minus the radius: positive outside, zero on the
     *        surface and negative inside
     */
    public double signedDistanceToPoint(Double3 p) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        if (Math.useFma()) {
            double _t0 = pZ - this.z;
            double _t1 = pX - this.x;
            double _t2 = pY - this.y;
            return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r;
        } else {
            double _t0 = pZ - this.z;
            double _t1 = pX - this.x;
            double _t2 = pY - this.y;
            return java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r;
        }
    }

    /**
     * Compute the signed distance between the given point and the surface of this sphere, i.e. the
     * distance from the point to the center minus the radius: positive outside, zero on the surface
     * and negative inside.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
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
    public double signedDistanceToPoint(double pX, double pY, double pZ) {
        if (Math.useFma()) {
            double _t0 = pZ - this.z;
            double _t1 = pX - this.x;
            double _t2 = pY - this.y;
            return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r;
        } else {
            double _t0 = pZ - this.z;
            double _t1 = pX - this.x;
            double _t2 = pY - this.y;
            return java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r;
        }
    }

    /**
     * Determine whether this sphere contains the given point (boundary inclusive). Delegates to the
     * shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the x coordinate of the point
     * @param pY the y coordinate of the point
     * @param pZ the z coordinate of the point
     * @return {@code true} if the given point lies inside or on this sphere, {@code false}
     *        otherwise
     */
    public boolean containsPoint(double pX, double pY, double pZ) {
        return Intersectiond.testPointSphere(pX, pY, pZ, this.x, this.y, this.z, this.r * this.r);
    }

    /**
     * Determine whether this sphere contains the given point (boundary inclusive). Delegates to the
     * shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to test
     * @return {@code true} if the given point lies inside or on this sphere, {@code false}
     *        otherwise
     */
    public boolean containsPoint(Double3 p) {
        return containsPoint(p.x(), p.y(), p.z());
    }

    /**
     * Determine whether this sphere intersects the given sphere. Delegates to the shared
     * {@code Intersectiond} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the sphere to test for intersection
     * @return {@code true} if this sphere and the given sphere intersect, {@code false} otherwise
     */
    public boolean intersectsSphere(DoubleSphere o) {
        return Intersectiond.testSphereSphere(this.x, this.y, this.z, this.r * this.r, o.x, o.y, o.z, o.r * o.r);
    }

    /**
     * Determine whether this sphere intersects the given axis-aligned box. Delegates to the shared
     * {@code Intersectiond} kernels.
     * <p>
     * Valid input: the minimum corner of {@code a} must not exceed the maximum corner of {@code a}
     * in any component.
     *
     * @param a the axis-aligned box to test for intersection
     * @return {@code true} if this sphere and the given axis-aligned box intersect, {@code false}
     *        otherwise
     */
    public boolean intersectsAABB(DoubleAABB a) {
        return Intersectiond.testAabbSphere(a.minX(), a.minY(), a.minZ(), a.maxX(), a.maxY(), a.maxZ(), this.x, this.y, this.z, this.r * this.r);
    }

    /**
     * Determine whether this sphere intersects the given plane. Delegates to the shared
     * {@code Intersectiond} kernels.
     * <p>
     * Valid input: the normal of {@code plane} must be non-zero.
     *
     * @param plane the plane to test for intersection
     * @return {@code true} if this sphere and the given plane intersect, {@code false} otherwise
     */
    public boolean intersectsPlane(DoublePlane plane) {
        return Intersectiond.testPlaneSphere(plane.a(), plane.b(), plane.c(), plane.d(), this.x, this.y, this.z, this.r);
    }

    /**
     * Determine whether this sphere intersects the given ray, whose direction must be normalized
     * (unlike the axis-aligned box's ray tests, the sphere test is not invariant to the direction's
     * length). The squared perpendicular distance from the center to the ray is formed directly,
     * from the component of the origin-to-center vector orthogonal to the direction, rather than as
     * the difference of two squared lengths, so a distant center does not lose the decision to
     * cancellation. Delegates to the shared {@code Intersectiond} kernels.
     * <p>
     * Valid input: the direction of {@code ray} must have unit length.
     *
     * @param ray the ray to test for intersection
     * @return {@code true} if this sphere and the given ray intersect, {@code false} otherwise
     */
    public boolean intersectsRay(DoubleRay ray) {
        return Intersectiond.testRaySphere(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), this.x, this.y, this.z, this.r * this.r);
    }

    /**
     * {@return a copy with the {@code x} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code x} component
     */
    public DoubleSphere withX(double v) {
        return new DoubleSphere(v, y, z, r);
    }

    /**
     * {@return a copy with the {@code y} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code y} component
     */
    public DoubleSphere withY(double v) {
        return new DoubleSphere(x, v, z, r);
    }

    /**
     * {@return a copy with the {@code z} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code z} component
     */
    public DoubleSphere withZ(double v) {
        return new DoubleSphere(x, y, v, r);
    }

    /**
     * {@return a copy with the {@code r} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code r} component
     */
    public DoubleSphere withR(double v) {
        return new DoubleSphere(x, y, z, v);
    }

    @Override public String toString() {
        return "DoubleSphere(" + this.x + ", " + this.y + ", " + this.z + ", " + this.r + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleSphere)) return false;
        DoubleSphere o = (DoubleSphere) obj;
        return Double.doubleToLongBits(x) == Double.doubleToLongBits(o.x)
            && Double.doubleToLongBits(y) == Double.doubleToLongBits(o.y)
            && Double.doubleToLongBits(z) == Double.doubleToLongBits(o.z)
            && Double.doubleToLongBits(r) == Double.doubleToLongBits(o.r);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int) (Double.doubleToLongBits(x) ^ (Double.doubleToLongBits(x) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(y) ^ (Double.doubleToLongBits(y) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(z) ^ (Double.doubleToLongBits(z) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(r) ^ (Double.doubleToLongBits(r) >>> 32));
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Double.isFinite(x)
            && Double.isFinite(y)
            && Double.isFinite(z)
            && Double.isFinite(r);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Double.isNaN(x)
            || Double.isNaN(y)
            || Double.isNaN(z)
            || Double.isNaN(r);
    }

    /**
     * Compare this value component-wise against {@code other}, allowing a difference of at
     * most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
     * compares equal to an equal infinity (the difference {@code Inf - Inf} is NaN), and to a
     * finite one only under an infinite {@code epsilon}; a NaN component never compares equal to
     * anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the value to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    public boolean equalsEpsilon(DoubleSphere other, double epsilon) {
        return java.lang.Math.abs(x - other.x) <= epsilon
            && java.lang.Math.abs(y - other.y) <= epsilon
            && java.lang.Math.abs(z - other.z) <= epsilon
            && java.lang.Math.abs(r - other.r) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoubleSphereBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleSphereBbOpsUnsafe()
            : new DoubleSphereBbOpsApi();
        static final DoubleSphereRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleSphereRawOpsUnsafe()
            : new DoubleSphereRawOpsApi();
    }

    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] store(double[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.r;
        return dest;
    }

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code DoubleSphere} holding the loaded elements
     */
    public static DoubleSphere load(double[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        return new DoubleSphere(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code DoubleSphere} holding the loaded elements
     */
    public static DoubleSphere load(double[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code DoubleSphere} holding the loaded elements
     */
    public static DoubleSphere load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code DoubleSphere} holding the loaded elements
     */
    public static DoubleSphere loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code DoubleSphere} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleSphere loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleSphere r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 4);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code DoubleSphere} holding the loaded elements
     */
    public static DoubleSphere load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code DoubleSphere} holding the loaded elements
     */
    public static DoubleSphere loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position and advancing
     * the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code DoubleSphere} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleSphere loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleSphere r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 32);
        return r;
    }

    /**
     * Store the elements into the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public DoubleSphere storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code DoubleSphere} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static DoubleSphere loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(address);
    }

    /**
     * Store the elements into the given array, converting each element to {@code float}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] store(float[] dest, int offset) {
        dest[offset] = (float) this.x;
        dest[offset + 1] = (float) this.y;
        dest[offset + 2] = (float) this.z;
        dest[offset + 3] = (float) this.r;
        return dest;
    }

    /**
     * Store the elements into the given array, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store(float[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code float}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code DoubleSphere} holding the loaded elements
     */
    public static DoubleSphere load(float[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        return new DoubleSphere(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array, converting each element from {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code DoubleSphere} holding the loaded elements
     */
    public static DoubleSphere load(float[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code float}, starting
     * at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code DoubleSphere} holding the loaded elements
     */
    public static DoubleSphere load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code DoubleSphere} holding the loaded elements
     */
    public static DoubleSphere loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code DoubleSphere} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleSphere loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleSphere r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 4);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
     * starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
     * starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code DoubleSphere} holding the loaded elements
     */
    public static DoubleSphere loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code DoubleSphere} holding the loaded elements
     */
    public static DoubleSphere loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code DoubleSphere} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleSphere loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleSphere r = StoreLoad.BB_OPS.loadFloatAbsolute(pos, buf);
        buf.position(pos + 16);
        return r;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public DoubleSphere storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code DoubleSphere} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static DoubleSphere loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(address);
    }

    private abstract static class DoubleSphereBbOps {
        public abstract DoubleBuffer storeAbsolute(DoubleSphere self, int index, DoubleBuffer buf);
        public abstract DoubleSphere loadAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeAbsolute(DoubleSphere self, int index, ByteBuffer buf);
        public abstract DoubleSphere loadAbsolute(int index, ByteBuffer buf);
        public abstract FloatBuffer storeAbsolute(DoubleSphere self, int index, FloatBuffer buf);
        public abstract DoubleSphere loadAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeFloatAbsolute(DoubleSphere self, int index, ByteBuffer buf);
        public abstract DoubleSphere loadFloatAbsolute(int index, ByteBuffer buf);
    }

    private static final class DoubleSphereBbOpsApi extends DoubleSphereBbOps {
        public DoubleBuffer storeAbsolute(DoubleSphere self, int index, DoubleBuffer buf) {
            buf.put(index, self.x);
            buf.put(index + 1, self.y);
            buf.put(index + 2, self.z);
            buf.put(index + 3, self.r);
            return buf;
        }

        public DoubleSphere loadAbsolute(int index, DoubleBuffer buf) {
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(index + 2);
            double _c3 = buf.get(index + 3);
            return new DoubleSphere(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeAbsolute(DoubleSphere self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.x);
            buf.putDouble(index + 8, self.y);
            buf.putDouble(index + 16, self.z);
            buf.putDouble(index + 24, self.r);
            return buf;
        }

        public DoubleSphere loadAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getDouble(index);
            double _c1 = buf.getDouble(index + 8);
            double _c2 = buf.getDouble(index + 16);
            double _c3 = buf.getDouble(index + 24);
            return new DoubleSphere(_c0, _c1, _c2, _c3);
        }

        public FloatBuffer storeAbsolute(DoubleSphere self, int index, FloatBuffer buf) {
            buf.put(index, (float) self.x);
            buf.put(index + 1, (float) self.y);
            buf.put(index + 2, (float) self.z);
            buf.put(index + 3, (float) self.r);
            return buf;
        }

        public DoubleSphere loadAbsolute(int index, FloatBuffer buf) {
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(index + 2);
            double _c3 = buf.get(index + 3);
            return new DoubleSphere(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeFloatAbsolute(DoubleSphere self, int index, ByteBuffer buf) {
            buf.putFloat(index, (float) self.x);
            buf.putFloat(index + 4, (float) self.y);
            buf.putFloat(index + 8, (float) self.z);
            buf.putFloat(index + 12, (float) self.r);
            return buf;
        }

        public DoubleSphere loadFloatAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getFloat(index);
            double _c1 = buf.getFloat(index + 4);
            double _c2 = buf.getFloat(index + 8);
            double _c3 = buf.getFloat(index + 12);
            return new DoubleSphere(_c0, _c1, _c2, _c3);
        }
    }

    private static final class DoubleSphereBbOpsUnsafe extends DoubleSphereBbOps {
        private static final DoubleSphereBbOpsApi API = new DoubleSphereBbOpsApi();

        public DoubleBuffer storeAbsolute(DoubleSphere self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            U.putDouble(address, self.x);
            U.putDouble(address + 8L, self.y);
            U.putDouble(address + 16L, self.z);
            U.putDouble(address + 24L, self.r);
            return buf;
        }

        public DoubleSphere loadAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            return new DoubleSphere(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeAbsolute(DoubleSphere self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putDouble(address, self.x);
            U.putDouble(address + 8L, self.y);
            U.putDouble(address + 16L, self.z);
            U.putDouble(address + 24L, self.r);
            return buf;
        }

        private static ByteBuffer storeAbsoluteElse(DoubleSphere self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putDouble(bytes, address, self.x);
            U.putDouble(bytes, address + 8L, self.y);
            U.putDouble(bytes, address + 16L, self.z);
            U.putDouble(bytes, address + 24L, self.r);
            return buf;
        }

        public DoubleSphere loadAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            return new DoubleSphere(_c0, _c1, _c2, _c3);
        }

        private static DoubleSphere loadAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8L);
            double _c2 = _U.getDouble(bytes, address + 16L);
            double _c3 = _U.getDouble(bytes, address + 24L);
            return new DoubleSphere(_c0, _c1, _c2, _c3);
        }

        public FloatBuffer storeAbsolute(DoubleSphere self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            U.putFloat(address, (float) self.x);
            U.putFloat(address + 4L, (float) self.y);
            U.putFloat(address + 8L, (float) self.z);
            U.putFloat(address + 12L, (float) self.r);
            return buf;
        }

        public DoubleSphere loadAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            return new DoubleSphere(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeFloatAbsolute(DoubleSphere self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeFloatAbsoluteElse(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putFloat(address, (float) self.x);
            U.putFloat(address + 4L, (float) self.y);
            U.putFloat(address + 8L, (float) self.z);
            U.putFloat(address + 12L, (float) self.r);
            return buf;
        }

        private static ByteBuffer storeFloatAbsoluteElse(DoubleSphere self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeFloatAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putFloat(bytes, address, (float) self.x);
            U.putFloat(bytes, address + 4L, (float) self.y);
            U.putFloat(bytes, address + 8L, (float) self.z);
            U.putFloat(bytes, address + 12L, (float) self.r);
            return buf;
        }

        public DoubleSphere loadFloatAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadFloatAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            return new DoubleSphere(_c0, _c1, _c2, _c3);
        }

        private static DoubleSphere loadFloatAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadFloatAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4L);
            double _c2 = _U.getFloat(bytes, address + 8L);
            double _c3 = _U.getFloat(bytes, address + 12L);
            return new DoubleSphere(_c0, _c1, _c2, _c3);
        }
    }

    private abstract static class DoubleSphereRawOps {
        public abstract DoubleSphere storeUnsafe(DoubleSphere self, long address);
        public abstract DoubleSphere loadUnsafe(long address);
        public abstract DoubleSphere storeFloatUnsafe(DoubleSphere self, long address);
        public abstract DoubleSphere loadFloatUnsafe(long address);
    }

    /**
     * API-backend raw-address kernels of a target without {@code java.lang.foreign.MemorySegment}:
     * every method throws {@link UnsupportedOperationException} - without a segment view there is no
     * portable way to dereference a raw {@code long} address. Select {@code StoreLoadBackend.UNSAFE}
     * to use the raw-address forms on this variant.
     */
    private static final class DoubleSphereRawOpsApi extends DoubleSphereRawOps {
        public DoubleSphere storeUnsafe(DoubleSphere self, long address) {
            throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
        }

        public DoubleSphere loadUnsafe(long address) {
            throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
        }

        public DoubleSphere storeFloatUnsafe(DoubleSphere self, long address) {
            throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
        }

        public DoubleSphere loadFloatUnsafe(long address) {
            throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
        }
    }

    private static final class DoubleSphereRawOpsUnsafe extends DoubleSphereRawOps {
        public DoubleSphere storeUnsafe(DoubleSphere self, long address) {
            U.putDouble(address, self.x);
            U.putDouble(address + 8L, self.y);
            U.putDouble(address + 16L, self.z);
            U.putDouble(address + 24L, self.r);
            return self;
        }

        public DoubleSphere storeHeap(DoubleSphere self, byte[] bytes, long address) {
            U.putDouble(bytes, address, self.x);
            U.putDouble(bytes, address + 8L, self.y);
            U.putDouble(bytes, address + 16L, self.z);
            U.putDouble(bytes, address + 24L, self.r);
            return self;
        }

        public DoubleSphere loadUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            return new DoubleSphere(_c0, _c1, _c2, _c3);
        }

        public DoubleSphere loadHeap(byte[] bytes, long address) {
            double _c0 = U.getDouble(bytes, address);
            double _c1 = U.getDouble(bytes, address + 8L);
            double _c2 = U.getDouble(bytes, address + 16L);
            double _c3 = U.getDouble(bytes, address + 24L);
            return new DoubleSphere(_c0, _c1, _c2, _c3);
        }

        public DoubleSphere storeFloatUnsafe(DoubleSphere self, long address) {
            U.putFloat(address, (float) self.x);
            U.putFloat(address + 4L, (float) self.y);
            U.putFloat(address + 8L, (float) self.z);
            U.putFloat(address + 12L, (float) self.r);
            return self;
        }

        public DoubleSphere storeFloatHeap(DoubleSphere self, byte[] bytes, long address) {
            U.putFloat(bytes, address, (float) self.x);
            U.putFloat(bytes, address + 4L, (float) self.y);
            U.putFloat(bytes, address + 8L, (float) self.z);
            U.putFloat(bytes, address + 12L, (float) self.r);
            return self;
        }

        public DoubleSphere loadFloatUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            return new DoubleSphere(_c0, _c1, _c2, _c3);
        }

        public DoubleSphere loadFloatHeap(byte[] bytes, long address) {
            double _c0 = U.getFloat(bytes, address);
            double _c1 = U.getFloat(bytes, address + 4L);
            double _c2 = U.getFloat(bytes, address + 8L);
            double _c3 = U.getFloat(bytes, address + 12L);
            return new DoubleSphere(_c0, _c1, _c2, _c3);
        }
    }
}
