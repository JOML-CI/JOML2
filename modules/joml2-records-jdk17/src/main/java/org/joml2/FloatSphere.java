// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import java.nio.ByteOrder;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

/**
 * Immutable sphere of single-precision {@code float} components.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
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
public record FloatSphere(float x, float y, float z, float r) {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 16;

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
    public FloatSphere(float x, float y, float z, float r) {
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
    public FloatSphere() {
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
    public FloatSphere(Float3 center, float radius) {
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
    public static FloatSphere[] newArray(int length) {
        FloatSphere[] a = new FloatSphere[length];
        java.util.Arrays.fill(a, new FloatSphere(0, 0, 0, 0));
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
    public static FloatSphere[] newArray(int length, FloatSphere init) {
        java.util.Objects.requireNonNull(init, "init");
        FloatSphere[] a = new FloatSphere[length];
        java.util.Arrays.fill(a, init);
        return a;
    }

    /** {@return the {@code x} component} <p>Valid input: any value, NaN and the infinities included. */
    public float x() { return x; }

    /** {@return the {@code y} component} <p>Valid input: any value, NaN and the infinities included. */
    public float y() { return y; }

    /** {@return the {@code z} component} <p>Valid input: any value, NaN and the infinities included. */
    public float z() { return z; }

    /** {@return the {@code r} component} <p>Valid input: any value, NaN and the infinities included. */
    public float r() { return r; }

    /**
     * Create a new sphere from its center and radius.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @return the resulting sphere
     */
    public FloatSphere set(Float3 center, float radius) {
        return new FloatSphere(center, radius);
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
    public FloatSphere set(FloatSphere v) {
        float vX = v.x;
        float vY = v.y;
        float vZ = v.z;
        float vR = v.r;
        return new FloatSphere(vX, vY, vZ, vR);
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
    public FloatSphere set(float vX, float vY, float vZ, float vR) {
        return new FloatSphere(vX, vY, vZ, vR);
    }

    /**
     * Set the center of this sphere to {@code c}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param c the new center
     * @return the resulting sphere
     */
    public FloatSphere setCenter(Float3 c) {
        float cX = c.x();
        float cY = c.y();
        float cZ = c.z();
        return new FloatSphere(cX, cY, cZ, this.r);
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
    public FloatSphere setCenter(float cX, float cY, float cZ) {
        return new FloatSphere(cX, cY, cZ, this.r);
    }

    /**
     * Set the radius of this sphere to {@code radius}, returning the result as a value.
     * <p>
     * Valid input: {@code radius} must not be negative.
     *
     * @param radius the radius
     * @return the resulting sphere
     */
    public FloatSphere setRadius(float radius) {
        return new FloatSphere(this.x, this.y, this.z, radius);
    }

    /**
     * Convert this sphere to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code DoubleSphere} holding the result
     */
    public DoubleSphere toDouble() {
        return new DoubleSphere(this.x, this.y, this.z, this.r);
    }

    /** Private tail of {@code transform}; reached only through it. */
    private FloatSphere transform_s37cad600_tail_fma(Float3x4 m, float _t9, float _t10) {
        float _m_m21 = m.m21();
        float _m_m22 = m.m22();
        float _m_m01 = m.m01();
        float _m_m02 = m.m02();
        float _m_m11 = m.m11();
        float _m_m12 = m.m12();
        float _t11 = java.lang.Math.abs(java.lang.Math.fma(_m_m21, _m_m22, java.lang.Math.fma(_m_m01, _m_m02, _m_m11 * _m_m12)));
        float _m_m00 = m.m00();
        float _m_m10 = m.m10();
        float _m_m20 = m.m20();
        return new FloatSphere(java.lang.Math.fma(_m_m02, this.z, java.lang.Math.fma(_m_m00, this.x, java.lang.Math.fma(_m_m01, this.y, m.m03()))), java.lang.Math.fma(_m_m12, this.z, java.lang.Math.fma(_m_m10, this.x, java.lang.Math.fma(_m_m11, this.y, m.m13()))), java.lang.Math.fma(_m_m22, this.z, java.lang.Math.fma(_m_m20, this.x, java.lang.Math.fma(_m_m21, this.y, m.m23()))), this.r * (float) java.lang.Math.sqrt(Math.max(Math.max(java.lang.Math.fma(_m_m00, _m_m00, java.lang.Math.fma(_m_m10, _m_m10, java.lang.Math.fma(_m_m20, _m_m20, _t9 + _t10))), java.lang.Math.fma(_m_m01, _m_m01, java.lang.Math.fma(_m_m11, _m_m11, java.lang.Math.fma(_m_m21, _m_m21, _t9 + _t11)))), java.lang.Math.fma(_m_m02, _m_m02, java.lang.Math.fma(_m_m12, _m_m12, java.lang.Math.fma(_m_m22, _m_m22, _t10 + _t11))))));
    }

    /** Private tail of {@code transform}; reached only through it. */
    private FloatSphere transform_s37cad600_tail_mulAdd(Float3x4 m, float _t9, float _t10) {
        float _m_m21 = m.m21();
        float _m_m22 = m.m22();
        float _m_m01 = m.m01();
        float _m_m02 = m.m02();
        float _m_m11 = m.m11();
        float _m_m12 = m.m12();
        float _t11 = java.lang.Math.abs(((_m_m21) * (_m_m22) + (((_m_m01) * (_m_m02) + (_m_m11 * _m_m12)))));
        float _m_m00 = m.m00();
        float _m_m10 = m.m10();
        float _m_m20 = m.m20();
        return new FloatSphere(((_m_m02) * (this.z) + (((_m_m00) * (this.x) + (((_m_m01) * (this.y) + (m.m03())))))), ((_m_m12) * (this.z) + (((_m_m10) * (this.x) + (((_m_m11) * (this.y) + (m.m13())))))), ((_m_m22) * (this.z) + (((_m_m20) * (this.x) + (((_m_m21) * (this.y) + (m.m23())))))), this.r * (float) java.lang.Math.sqrt(Math.max(Math.max(((_m_m00) * (_m_m00) + (((_m_m10) * (_m_m10) + (((_m_m20) * (_m_m20) + (_t9 + _t10)))))), ((_m_m01) * (_m_m01) + (((_m_m11) * (_m_m11) + (((_m_m21) * (_m_m21) + (_t9 + _t11))))))), ((_m_m02) * (_m_m02) + (((_m_m12) * (_m_m12) + (((_m_m22) * (_m_m22) + (_t10 + _t11)))))))));
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
    public FloatSphere transform(Float3x4 m) {
        float _m_m20 = m.m20();
        float _m_m21 = m.m21();
        float _m_m00 = m.m00();
        float _m_m01 = m.m01();
        float _m_m10 = m.m10();
        float _m_m11 = m.m11();
        float _m_m22 = m.m22();
        float _m_m02 = m.m02();
        float _m_m12 = m.m12();
        if (org.joml2.internal.FmaSwitch.ON) {
            return transform_s37cad600_tail_fma(m, java.lang.Math.abs(java.lang.Math.fma(_m_m20, _m_m21, java.lang.Math.fma(_m_m00, _m_m01, _m_m10 * _m_m11))), java.lang.Math.abs(java.lang.Math.fma(_m_m20, _m_m22, java.lang.Math.fma(_m_m00, _m_m02, _m_m10 * _m_m12))));
        } else {
            return transform_s37cad600_tail_mulAdd(m, java.lang.Math.abs(((_m_m20) * (_m_m21) + (((_m_m00) * (_m_m01) + (_m_m10 * _m_m11))))), java.lang.Math.abs(((_m_m20) * (_m_m22) + (((_m_m00) * (_m_m02) + (_m_m10 * _m_m12))))));
        }
    }

    /** Private tail of {@code transform}; reached only through it. */
    private FloatSphere transform_s37d8ed81_tail_fma(Float4x4 m, float _t9, float _t10) {
        float _m_m21 = m.m21();
        float _m_m22 = m.m22();
        float _m_m01 = m.m01();
        float _m_m02 = m.m02();
        float _m_m11 = m.m11();
        float _m_m12 = m.m12();
        float _t11 = java.lang.Math.abs(java.lang.Math.fma(_m_m21, _m_m22, java.lang.Math.fma(_m_m01, _m_m02, _m_m11 * _m_m12)));
        float _m_m00 = m.m00();
        float _m_m10 = m.m10();
        float _m_m20 = m.m20();
        return new FloatSphere(java.lang.Math.fma(_m_m02, this.z, java.lang.Math.fma(_m_m00, this.x, java.lang.Math.fma(_m_m01, this.y, m.m03()))), java.lang.Math.fma(_m_m12, this.z, java.lang.Math.fma(_m_m10, this.x, java.lang.Math.fma(_m_m11, this.y, m.m13()))), java.lang.Math.fma(_m_m22, this.z, java.lang.Math.fma(_m_m20, this.x, java.lang.Math.fma(_m_m21, this.y, m.m23()))), this.r * (float) java.lang.Math.sqrt(Math.max(Math.max(java.lang.Math.fma(_m_m00, _m_m00, java.lang.Math.fma(_m_m10, _m_m10, java.lang.Math.fma(_m_m20, _m_m20, _t9 + _t10))), java.lang.Math.fma(_m_m01, _m_m01, java.lang.Math.fma(_m_m11, _m_m11, java.lang.Math.fma(_m_m21, _m_m21, _t9 + _t11)))), java.lang.Math.fma(_m_m02, _m_m02, java.lang.Math.fma(_m_m12, _m_m12, java.lang.Math.fma(_m_m22, _m_m22, _t10 + _t11))))));
    }

    /** Private tail of {@code transform}; reached only through it. */
    private FloatSphere transform_s37d8ed81_tail_mulAdd(Float4x4 m, float _t9, float _t10) {
        float _m_m21 = m.m21();
        float _m_m22 = m.m22();
        float _m_m01 = m.m01();
        float _m_m02 = m.m02();
        float _m_m11 = m.m11();
        float _m_m12 = m.m12();
        float _t11 = java.lang.Math.abs(((_m_m21) * (_m_m22) + (((_m_m01) * (_m_m02) + (_m_m11 * _m_m12)))));
        float _m_m00 = m.m00();
        float _m_m10 = m.m10();
        float _m_m20 = m.m20();
        return new FloatSphere(((_m_m02) * (this.z) + (((_m_m00) * (this.x) + (((_m_m01) * (this.y) + (m.m03())))))), ((_m_m12) * (this.z) + (((_m_m10) * (this.x) + (((_m_m11) * (this.y) + (m.m13())))))), ((_m_m22) * (this.z) + (((_m_m20) * (this.x) + (((_m_m21) * (this.y) + (m.m23())))))), this.r * (float) java.lang.Math.sqrt(Math.max(Math.max(((_m_m00) * (_m_m00) + (((_m_m10) * (_m_m10) + (((_m_m20) * (_m_m20) + (_t9 + _t10)))))), ((_m_m01) * (_m_m01) + (((_m_m11) * (_m_m11) + (((_m_m21) * (_m_m21) + (_t9 + _t11))))))), ((_m_m02) * (_m_m02) + (((_m_m12) * (_m_m12) + (((_m_m22) * (_m_m22) + (_t10 + _t11)))))))));
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
    public FloatSphere transform(Float4x4 m) {
        float _m_m20 = m.m20();
        float _m_m21 = m.m21();
        float _m_m00 = m.m00();
        float _m_m01 = m.m01();
        float _m_m10 = m.m10();
        float _m_m11 = m.m11();
        float _m_m22 = m.m22();
        float _m_m02 = m.m02();
        float _m_m12 = m.m12();
        if (org.joml2.internal.FmaSwitch.ON) {
            return transform_s37d8ed81_tail_fma(m, java.lang.Math.abs(java.lang.Math.fma(_m_m20, _m_m21, java.lang.Math.fma(_m_m00, _m_m01, _m_m10 * _m_m11))), java.lang.Math.abs(java.lang.Math.fma(_m_m20, _m_m22, java.lang.Math.fma(_m_m00, _m_m02, _m_m10 * _m_m12))));
        } else {
            return transform_s37d8ed81_tail_mulAdd(m, java.lang.Math.abs(((_m_m20) * (_m_m21) + (((_m_m00) * (_m_m01) + (_m_m10 * _m_m11))))), java.lang.Math.abs(((_m_m20) * (_m_m22) + (((_m_m00) * (_m_m02) + (_m_m10 * _m_m12))))));
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
    public FloatSphere translate(Float3 delta) {
        float deltaX = delta.x();
        float deltaY = delta.y();
        float deltaZ = delta.z();
        return new FloatSphere(deltaX + this.x, deltaY + this.y, deltaZ + this.z, this.r);
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
    public FloatSphere translate(float deltaX, float deltaY, float deltaZ) {
        return new FloatSphere(deltaX + this.x, deltaY + this.y, deltaZ + this.z, this.r);
    }

    /**
     * Compute the point of this sphere closest to the given point. For a point inside or on the
     * sphere, the result is the point itself; otherwise it is the point on the surface in the
     * direction from the center toward the given point.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param p the point to find the closest point to
     * @return the resulting vector
     */
    public Float3 closestPointToPoint(Float3 p) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            float _t6 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
            float _t8 = this.r * (1.0f / (float) java.lang.Math.sqrt(_t6));
            if (_t6 <= this.r * this.r) {
                return new Float3(pX, pY, pZ);
            } else {
                return new Float3(java.lang.Math.fma(_t1, _t8, this.x), java.lang.Math.fma(_t2, _t8, this.y), java.lang.Math.fma(_t0, _t8, this.z));
            }
        } else {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            float _t6 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
            float _t8 = this.r * (1.0f / (float) java.lang.Math.sqrt(_t6));
            if (_t6 <= this.r * this.r) {
                return new Float3(pX, pY, pZ);
            } else {
                return new Float3(((_t1) * (_t8) + (this.x)), ((_t2) * (_t8) + (this.y)), ((_t0) * (_t8) + (this.z)));
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
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @return the resulting vector
     */
    public Float3 closestPointToPoint(float pX, float pY, float pZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            float _t6 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
            float _t8 = this.r * (1.0f / (float) java.lang.Math.sqrt(_t6));
            if (_t6 <= this.r * this.r) {
                return new Float3(pX, pY, pZ);
            } else {
                return new Float3(java.lang.Math.fma(_t1, _t8, this.x), java.lang.Math.fma(_t2, _t8, this.y), java.lang.Math.fma(_t0, _t8, this.z));
            }
        } else {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            float _t6 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
            float _t8 = this.r * (1.0f / (float) java.lang.Math.sqrt(_t6));
            if (_t6 <= this.r * this.r) {
                return new Float3(pX, pY, pZ);
            } else {
                return new Float3(((_t1) * (_t8) + (this.x)), ((_t2) * (_t8) + (this.y)), ((_t0) * (_t8) + (this.z)));
            }
        }
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
    public float distanceSquaredToAABB(FloatAABB aabb) {
        float minX = aabb.minX();
        float minY = aabb.minY();
        float minZ = aabb.minZ();
        float maxX = aabb.maxX();
        float maxY = aabb.maxY();
        float maxZ = aabb.maxZ();
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            float _t14 = Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
            return _t14 * _t14;
        } else {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            float _t14 = Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            float _t14 = Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
            return _t14 * _t14;
        } else {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            float _t14 = Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
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
    public float distanceSquaredToAABB(Float3 min, Float3 max) {
        float minX = min.x();
        float minY = min.y();
        float minZ = min.z();
        float maxX = max.x();
        float maxY = max.y();
        float maxZ = max.z();
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            float _t14 = Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
            return _t14 * _t14;
        } else {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            float _t14 = Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
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
    public float distanceSquaredToPoint(Float3 p) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            float _t8 = Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r);
            return _t8 * _t8;
        } else {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            float _t8 = Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            float _t8 = Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r);
            return _t8 * _t8;
        } else {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            float _t8 = Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r);
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
    public float distanceSquaredToSphere(FloatSphere other) {
        float otherX = other.x;
        float otherY = other.y;
        float otherZ = other.z;
        float otherR = other.r;
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = otherZ - this.z;
            float _t1 = otherX - this.x;
            float _t2 = otherY - this.y;
            float _t9 = Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r - otherR);
            return _t9 * _t9;
        } else {
            float _t0 = otherZ - this.z;
            float _t1 = otherX - this.x;
            float _t2 = otherY - this.y;
            float _t9 = Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r - otherR);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = otherZ - this.z;
            float _t1 = otherX - this.x;
            float _t2 = otherY - this.y;
            float _t9 = Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r - otherR);
            return _t9 * _t9;
        } else {
            float _t0 = otherZ - this.z;
            float _t1 = otherX - this.x;
            float _t2 = otherY - this.y;
            float _t9 = Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r - otherR);
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
    public float distanceToAABB(FloatAABB aabb) {
        float minX = aabb.minX();
        float minY = aabb.minY();
        float minZ = aabb.minZ();
        float maxX = aabb.maxX();
        float maxY = aabb.maxY();
        float maxZ = aabb.maxZ();
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
        } else {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
        } else {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
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
    public float distanceToAABB(Float3 min, Float3 max) {
        float minX = min.x();
        float minY = min.y();
        float minZ = min.z();
        float maxX = max.x();
        float maxY = max.y();
        float maxZ = max.z();
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - this.r);
        } else {
            float _t6 = this.z - Math.max(minZ, Math.min(this.z, maxZ));
            float _t7 = this.x - Math.max(minX, Math.min(this.x, maxX));
            float _t8 = this.y - Math.max(minY, Math.min(this.y, maxY));
            return Math.max(0.0f, (float) java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - this.r);
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
    public float distanceToPlane(FloatPlane plane) {
        float planeA = plane.a();
        float planeB = plane.b();
        float planeC = plane.c();
        float planeD = plane.d();
        if (org.joml2.internal.FmaSwitch.ON) {
            return Math.max(0.0f, java.lang.Math.fma((1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))), java.lang.Math.abs(java.lang.Math.fma(planeA, this.x, java.lang.Math.fma(planeB, this.y, java.lang.Math.fma(planeC, this.z, planeD)))), -this.r));
        } else {
            return Math.max(0.0f, (((1.0f / (float) java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB))))))) * (java.lang.Math.abs(((planeA) * (this.x) + (((planeB) * (this.y) + (((planeC) * (this.z) + (planeD)))))))) - (this.r)));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            return Math.max(0.0f, java.lang.Math.fma((1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))), java.lang.Math.abs(java.lang.Math.fma(planeA, this.x, java.lang.Math.fma(planeB, this.y, java.lang.Math.fma(planeC, this.z, planeD)))), -this.r));
        } else {
            return Math.max(0.0f, (((1.0f / (float) java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB))))))) * (java.lang.Math.abs(((planeA) * (this.x) + (((planeB) * (this.y) + (((planeC) * (this.z) + (planeD)))))))) - (this.r)));
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
    public float distanceToPlane(Float4 plane) {
        float planeA = plane.x();
        float planeB = plane.y();
        float planeC = plane.z();
        float planeD = plane.w();
        if (org.joml2.internal.FmaSwitch.ON) {
            return Math.max(0.0f, java.lang.Math.fma((1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))), java.lang.Math.abs(java.lang.Math.fma(planeA, this.x, java.lang.Math.fma(planeB, this.y, java.lang.Math.fma(planeC, this.z, planeD)))), -this.r));
        } else {
            return Math.max(0.0f, (((1.0f / (float) java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB))))))) * (java.lang.Math.abs(((planeA) * (this.x) + (((planeB) * (this.y) + (((planeC) * (this.z) + (planeD)))))))) - (this.r)));
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
    public float distanceToPoint(Float3 p) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            return Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r);
        } else {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            return Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            return Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r);
        } else {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            return Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r);
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
    public float distanceToSphere(FloatSphere other) {
        float otherX = other.x;
        float otherY = other.y;
        float otherZ = other.z;
        float otherR = other.r;
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = otherZ - this.z;
            float _t1 = otherX - this.x;
            float _t2 = otherY - this.y;
            return Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r - otherR);
        } else {
            float _t0 = otherZ - this.z;
            float _t1 = otherX - this.x;
            float _t2 = otherY - this.y;
            return Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r - otherR);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = otherZ - this.z;
            float _t1 = otherX - this.x;
            float _t2 = otherY - this.y;
            return Math.max(0.0f, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r - otherR);
        } else {
            float _t0 = otherZ - this.z;
            float _t1 = otherX - this.x;
            float _t2 = otherY - this.y;
            return Math.max(0.0f, (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r - otherR);
        }
    }

    /**
     * Get the center of this sphere, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getCenter() {
        return new Float3(this.x, this.y, this.z);
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
        return this.r >= 0.0f;
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
    public float signedDistanceToPoint(Float3 p) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r;
        } else {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2))) - this.r;
        } else {
            float _t0 = pZ - this.z;
            float _t1 = pX - this.x;
            float _t2 = pY - this.y;
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))))) - this.r;
        }
    }

    /**
     * Determine whether this sphere contains the given point (boundary inclusive). Delegates to the
     * shared {@code Intersectionf} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the x coordinate of the point
     * @param pY the y coordinate of the point
     * @param pZ the z coordinate of the point
     * @return {@code true} if the given point lies inside or on this sphere, {@code false}
     *        otherwise
     */
    public boolean containsPoint(float pX, float pY, float pZ) {
        return Intersectionf.testPointSphere(pX, pY, pZ, this.x, this.y, this.z, this.r * this.r);
    }

    /**
     * Determine whether this sphere contains the given point (boundary inclusive). Delegates to the
     * shared {@code Intersectionf} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to test
     * @return {@code true} if the given point lies inside or on this sphere, {@code false}
     *        otherwise
     */
    public boolean containsPoint(Float3 p) {
        return containsPoint(p.x(), p.y(), p.z());
    }

    /**
     * Determine whether this sphere intersects the given sphere. Delegates to the shared
     * {@code Intersectionf} kernels.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the sphere to test for intersection
     * @return {@code true} if this sphere and the given sphere intersect, {@code false} otherwise
     */
    public boolean intersectsSphere(FloatSphere o) {
        return Intersectionf.testSphereSphere(this.x, this.y, this.z, this.r * this.r, o.x, o.y, o.z, o.r * o.r);
    }

    /**
     * Determine whether this sphere intersects the given axis-aligned box. Delegates to the shared
     * {@code Intersectionf} kernels.
     * <p>
     * Valid input: the minimum corner of {@code a} must not exceed the maximum corner of {@code a}
     * in any component.
     *
     * @param a the axis-aligned box to test for intersection
     * @return {@code true} if this sphere and the given axis-aligned box intersect, {@code false}
     *        otherwise
     */
    public boolean intersectsAABB(FloatAABB a) {
        return Intersectionf.testAabbSphere(a.minX(), a.minY(), a.minZ(), a.maxX(), a.maxY(), a.maxZ(), this.x, this.y, this.z, this.r * this.r);
    }

    /**
     * Determine whether this sphere intersects the given plane. Delegates to the shared
     * {@code Intersectionf} kernels.
     * <p>
     * Valid input: the normal of {@code plane} must be non-zero.
     *
     * @param plane the plane to test for intersection
     * @return {@code true} if this sphere and the given plane intersect, {@code false} otherwise
     */
    public boolean intersectsPlane(FloatPlane plane) {
        return Intersectionf.testPlaneSphere(plane.a(), plane.b(), plane.c(), plane.d(), this.x, this.y, this.z, this.r);
    }

    /**
     * Determine whether this sphere intersects the given ray, whose direction must be normalized
     * (unlike the axis-aligned box's ray tests, the sphere test is not invariant to the direction's
     * length). The squared perpendicular distance from the center to the ray is formed directly,
     * from the component of the origin-to-center vector orthogonal to the direction, rather than as
     * the difference of two squared lengths, so a distant center does not lose the decision to
     * cancellation. Delegates to the shared {@code Intersectionf} kernels.
     * <p>
     * Valid input: the direction of {@code ray} must have unit length.
     *
     * @param ray the ray to test for intersection
     * @return {@code true} if this sphere and the given ray intersect, {@code false} otherwise
     */
    public boolean intersectsRay(FloatRay ray) {
        return Intersectionf.testRaySphere(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ(), this.x, this.y, this.z, this.r * this.r);
    }

    /**
     * {@return a copy with the {@code x} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code x} component
     */
    public FloatSphere withX(float v) {
        return new FloatSphere(v, y, z, r);
    }

    /**
     * {@return a copy with the {@code y} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code y} component
     */
    public FloatSphere withY(float v) {
        return new FloatSphere(x, v, z, r);
    }

    /**
     * {@return a copy with the {@code z} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code z} component
     */
    public FloatSphere withZ(float v) {
        return new FloatSphere(x, y, v, r);
    }

    /**
     * {@return a copy with the {@code r} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code r} component
     */
    public FloatSphere withR(float v) {
        return new FloatSphere(x, y, z, v);
    }

    @Override public String toString() {
        return "FloatSphere(" + this.x + ", " + this.y + ", " + this.z + ", " + this.r + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatSphere)) return false;
        FloatSphere o = (FloatSphere) obj;
        return Float.floatToIntBits(x) == Float.floatToIntBits(o.x)
            && Float.floatToIntBits(y) == Float.floatToIntBits(o.y)
            && Float.floatToIntBits(z) == Float.floatToIntBits(o.z)
            && Float.floatToIntBits(r) == Float.floatToIntBits(o.r);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(x);
        h = 31 * h + Float.floatToIntBits(y);
        h = 31 * h + Float.floatToIntBits(z);
        h = 31 * h + Float.floatToIntBits(r);
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Float.isFinite(x)
            && Float.isFinite(y)
            && Float.isFinite(z)
            && Float.isFinite(r);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Float.isNaN(x)
            || Float.isNaN(y)
            || Float.isNaN(z)
            || Float.isNaN(r);
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
    public boolean equalsEpsilon(FloatSphere other, float epsilon) {
        return java.lang.Math.abs(x - other.x) <= epsilon
            && java.lang.Math.abs(y - other.y) <= epsilon
            && java.lang.Math.abs(z - other.z) <= epsilon
            && java.lang.Math.abs(r - other.r) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatSphereBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatSphereBbOpsUnsafe()
            : new FloatSphereBbOpsApi();
        static final FloatSphereRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatSphereRawOpsUnsafe()
            : new FloatSphereRawOpsApi();
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
    public float[] store(float[] dest, int offset) {
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
    public float[] store(float[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code FloatSphere} holding the loaded elements
     */
    public static FloatSphere load(float[] src, int offset) {
        float _c0 = src[offset];
        float _c1 = src[offset + 1];
        float _c2 = src[offset + 2];
        float _c3 = src[offset + 3];
        return new FloatSphere(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code FloatSphere} holding the loaded elements
     */
    public static FloatSphere load(float[] src) { return load(src, 0); }

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
    public FloatBuffer store(FloatBuffer buf) {
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
    public FloatBuffer storeAbsolute(int index, FloatBuffer buf) {
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
    public FloatBuffer storeRelative(FloatBuffer buf) {
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
     * @return a new {@code FloatSphere} holding the loaded elements
     */
    public static FloatSphere load(FloatBuffer buf) {
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
     * @return a new {@code FloatSphere} holding the loaded elements
     */
    public static FloatSphere loadAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code FloatSphere} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatSphere loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatSphere r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
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
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 16);
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
     * @return a new {@code FloatSphere} holding the loaded elements
     */
    public static FloatSphere load(ByteBuffer buf) {
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
     * @return a new {@code FloatSphere} holding the loaded elements
     */
    public static FloatSphere loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code FloatSphere} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatSphere loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatSphere r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 16);
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
    public FloatSphere storeUnsafe(long address) {
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
     * @return a new {@code FloatSphere} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static FloatSphere loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(address);
    }

    /**
     * Store the elements into the given array, converting each element to {@code double}, starting
     * at the given offset.
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
     * Store the elements into the given array, converting each element to {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code double}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code FloatSphere} holding the loaded elements
     */
    public static FloatSphere load(double[] src, int offset) {
        float _c0 = (float) src[offset];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[offset + 2];
        float _c3 = (float) src[offset + 3];
        return new FloatSphere(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array, converting each element from {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code FloatSphere} holding the loaded elements
     */
    public static FloatSphere load(double[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code double}, starting
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
    public DoubleBuffer store(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code double}, starting
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
    public DoubleBuffer storeAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code double}, starting
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
    public DoubleBuffer storeRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
     * starting at its current position (the position is not modified).
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
     * @return a new {@code FloatSphere} holding the loaded elements
     */
    public static FloatSphere load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
     * starting at the given absolute index (the position is not used or modified).
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
     * @return a new {@code FloatSphere} holding the loaded elements
     */
    public static FloatSphere loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
     * starting at its current position and advancing the position accordingly.
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
     * @return a new {@code FloatSphere} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatSphere loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatSphere r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 4);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
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
    public ByteBuffer storeDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
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
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
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
    public ByteBuffer storeDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
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
     * @return a new {@code FloatSphere} holding the loaded elements
     */
    public static FloatSphere loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
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
     * @return a new {@code FloatSphere} holding the loaded elements
     */
    public static FloatSphere loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
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
     * @return a new {@code FloatSphere} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatSphere loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatSphere r = StoreLoad.BB_OPS.loadDoubleAbsolute(pos, buf);
        buf.position(pos + 32);
        return r;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public FloatSphere storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code FloatSphere} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static FloatSphere loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(address);
    }

    private abstract static class FloatSphereBbOps {
        public abstract FloatBuffer storeAbsolute(FloatSphere self, int index, FloatBuffer buf);
        public abstract FloatSphere loadAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeAbsolute(FloatSphere self, int index, ByteBuffer buf);
        public abstract FloatSphere loadAbsolute(int index, ByteBuffer buf);
        public abstract DoubleBuffer storeAbsolute(FloatSphere self, int index, DoubleBuffer buf);
        public abstract FloatSphere loadAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeDoubleAbsolute(FloatSphere self, int index, ByteBuffer buf);
        public abstract FloatSphere loadDoubleAbsolute(int index, ByteBuffer buf);
    }

    private static final class FloatSphereBbOpsApi extends FloatSphereBbOps {
        public FloatBuffer storeAbsolute(FloatSphere self, int index, FloatBuffer buf) {
            buf.put(index, self.x);
            buf.put(index + 1, self.y);
            buf.put(index + 2, self.z);
            buf.put(index + 3, self.r);
            return buf;
        }

        public FloatSphere loadAbsolute(int index, FloatBuffer buf) {
            float _c0 = buf.get(index);
            float _c1 = buf.get(index + 1);
            float _c2 = buf.get(index + 2);
            float _c3 = buf.get(index + 3);
            return new FloatSphere(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeAbsolute(FloatSphere self, int index, ByteBuffer buf) {
            buf.putFloat(index, self.x);
            buf.putFloat(index + 4, self.y);
            buf.putFloat(index + 8, self.z);
            buf.putFloat(index + 12, self.r);
            return buf;
        }

        public FloatSphere loadAbsolute(int index, ByteBuffer buf) {
            float _c0 = buf.getFloat(index);
            float _c1 = buf.getFloat(index + 4);
            float _c2 = buf.getFloat(index + 8);
            float _c3 = buf.getFloat(index + 12);
            return new FloatSphere(_c0, _c1, _c2, _c3);
        }

        public DoubleBuffer storeAbsolute(FloatSphere self, int index, DoubleBuffer buf) {
            buf.put(index, self.x);
            buf.put(index + 1, self.y);
            buf.put(index + 2, self.z);
            buf.put(index + 3, self.r);
            return buf;
        }

        public FloatSphere loadAbsolute(int index, DoubleBuffer buf) {
            float _c0 = (float) buf.get(index);
            float _c1 = (float) buf.get(index + 1);
            float _c2 = (float) buf.get(index + 2);
            float _c3 = (float) buf.get(index + 3);
            return new FloatSphere(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeDoubleAbsolute(FloatSphere self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.x);
            buf.putDouble(index + 8, self.y);
            buf.putDouble(index + 16, self.z);
            buf.putDouble(index + 24, self.r);
            return buf;
        }

        public FloatSphere loadDoubleAbsolute(int index, ByteBuffer buf) {
            float _c0 = (float) buf.getDouble(index);
            float _c1 = (float) buf.getDouble(index + 8);
            float _c2 = (float) buf.getDouble(index + 16);
            float _c3 = (float) buf.getDouble(index + 24);
            return new FloatSphere(_c0, _c1, _c2, _c3);
        }
    }

    private static final class FloatSphereBbOpsUnsafe extends FloatSphereBbOps {
        private static final FloatSphereBbOpsApi API = new FloatSphereBbOpsApi();

        public FloatBuffer storeAbsolute(FloatSphere self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            U.putFloat(address, self.x);
            U.putFloat(address + 4L, self.y);
            U.putFloat(address + 8L, self.z);
            U.putFloat(address + 12L, self.r);
            return buf;
        }

        public FloatSphere loadAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            return new FloatSphere(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeAbsolute(FloatSphere self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putFloat(address, self.x);
            U.putFloat(address + 4L, self.y);
            U.putFloat(address + 8L, self.z);
            U.putFloat(address + 12L, self.r);
            return buf;
        }

        private static ByteBuffer storeAbsoluteElse(FloatSphere self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putFloat(bytes, address, self.x);
            U.putFloat(bytes, address + 4L, self.y);
            U.putFloat(bytes, address + 8L, self.z);
            U.putFloat(bytes, address + 12L, self.r);
            return buf;
        }

        public FloatSphere loadAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            return new FloatSphere(_c0, _c1, _c2, _c3);
        }

        private static FloatSphere loadAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4L);
            float _c2 = _U.getFloat(bytes, address + 8L);
            float _c3 = _U.getFloat(bytes, address + 12L);
            return new FloatSphere(_c0, _c1, _c2, _c3);
        }

        public DoubleBuffer storeAbsolute(FloatSphere self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            U.putDouble(address, self.x);
            U.putDouble(address + 8L, self.y);
            U.putDouble(address + 16L, self.z);
            U.putDouble(address + 24L, self.r);
            return buf;
        }

        public FloatSphere loadAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            return new FloatSphere(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeDoubleAbsolute(FloatSphere self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeDoubleAbsoluteElse(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putDouble(address, self.x);
            U.putDouble(address + 8L, self.y);
            U.putDouble(address + 16L, self.z);
            U.putDouble(address + 24L, self.r);
            return buf;
        }

        private static ByteBuffer storeDoubleAbsoluteElse(FloatSphere self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeDoubleAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putDouble(bytes, address, self.x);
            U.putDouble(bytes, address + 8L, self.y);
            U.putDouble(bytes, address + 16L, self.z);
            U.putDouble(bytes, address + 24L, self.r);
            return buf;
        }

        public FloatSphere loadDoubleAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadDoubleAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            return new FloatSphere(_c0, _c1, _c2, _c3);
        }

        private static FloatSphere loadDoubleAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadDoubleAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8L);
            float _c2 = (float) _U.getDouble(bytes, address + 16L);
            float _c3 = (float) _U.getDouble(bytes, address + 24L);
            return new FloatSphere(_c0, _c1, _c2, _c3);
        }
    }

    private abstract static class FloatSphereRawOps {
        public abstract FloatSphere storeUnsafe(FloatSphere self, long address);
        public abstract FloatSphere loadUnsafe(long address);
        public abstract FloatSphere storeDoubleUnsafe(FloatSphere self, long address);
        public abstract FloatSphere loadDoubleUnsafe(long address);
    }

    /**
     * API-backend raw-address kernels of a target without {@code java.lang.foreign.MemorySegment}:
     * every method throws {@link UnsupportedOperationException} - without a segment view there is no
     * portable way to dereference a raw {@code long} address. Select {@code StoreLoadBackend.UNSAFE}
     * to use the raw-address forms on this variant.
     */
    private static final class FloatSphereRawOpsApi extends FloatSphereRawOps {
        public FloatSphere storeUnsafe(FloatSphere self, long address) {
            throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
        }

        public FloatSphere loadUnsafe(long address) {
            throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
        }

        public FloatSphere storeDoubleUnsafe(FloatSphere self, long address) {
            throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
        }

        public FloatSphere loadDoubleUnsafe(long address) {
            throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
        }
    }

    private static final class FloatSphereRawOpsUnsafe extends FloatSphereRawOps {
        public FloatSphere storeUnsafe(FloatSphere self, long address) {
            U.putFloat(address, self.x);
            U.putFloat(address + 4L, self.y);
            U.putFloat(address + 8L, self.z);
            U.putFloat(address + 12L, self.r);
            return self;
        }

        public FloatSphere storeHeap(FloatSphere self, byte[] bytes, long address) {
            U.putFloat(bytes, address, self.x);
            U.putFloat(bytes, address + 4L, self.y);
            U.putFloat(bytes, address + 8L, self.z);
            U.putFloat(bytes, address + 12L, self.r);
            return self;
        }

        public FloatSphere loadUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            return new FloatSphere(_c0, _c1, _c2, _c3);
        }

        public FloatSphere loadHeap(byte[] bytes, long address) {
            float _c0 = U.getFloat(bytes, address);
            float _c1 = U.getFloat(bytes, address + 4L);
            float _c2 = U.getFloat(bytes, address + 8L);
            float _c3 = U.getFloat(bytes, address + 12L);
            return new FloatSphere(_c0, _c1, _c2, _c3);
        }

        public FloatSphere storeDoubleUnsafe(FloatSphere self, long address) {
            U.putDouble(address, self.x);
            U.putDouble(address + 8L, self.y);
            U.putDouble(address + 16L, self.z);
            U.putDouble(address + 24L, self.r);
            return self;
        }

        public FloatSphere storeDoubleHeap(FloatSphere self, byte[] bytes, long address) {
            U.putDouble(bytes, address, self.x);
            U.putDouble(bytes, address + 8L, self.y);
            U.putDouble(bytes, address + 16L, self.z);
            U.putDouble(bytes, address + 24L, self.r);
            return self;
        }

        public FloatSphere loadDoubleUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            return new FloatSphere(_c0, _c1, _c2, _c3);
        }

        public FloatSphere loadDoubleHeap(byte[] bytes, long address) {
            float _c0 = (float) U.getDouble(bytes, address);
            float _c1 = (float) U.getDouble(bytes, address + 8L);
            float _c2 = (float) U.getDouble(bytes, address + 16L);
            float _c3 = (float) U.getDouble(bytes, address + 24L);
            return new FloatSphere(_c0, _c1, _c2, _c3);
        }
    }
}
