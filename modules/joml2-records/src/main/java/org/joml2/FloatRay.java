// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;
import java.nio.ByteOrder;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

/**
 * Immutable ray of single-precision {@code float} components.
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
 * @param oX the {@code oX} component
 * @param oY the {@code oY} component
 * @param oZ the {@code oZ} component
 * @param dX the {@code dX} component
 * @param dY the {@code dY} component
 * @param dZ the {@code dZ} component
 */
public record FloatRay(float oX, float oY, float oZ, float dX, float dY, float dZ) {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 24;

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param oX the {@code oX} component
     * @param oY the {@code oY} component
     * @param oZ the {@code oZ} component
     * @param dX the {@code dX} component
     * @param dY the {@code dY} component
     * @param dZ the {@code dZ} component
     */
    public FloatRay(float oX, float oY, float oZ, float dX, float dY, float dZ) {
        this.oX = oX;
        this.oY = oY;
        this.oZ = oZ;
        this.dX = dX;
        this.dY = dY;
        this.dZ = dZ;
    }

    /**
     * Create a new instance initialized to all zeros.
     * <p>
     * Valid input: the method reads no input.
     */
    public FloatRay() {
        this(0, 0, 0, 0, 0, 0);
    }

    /**
     * Create a ray from its origin and direction.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param origin the origin of the ray
     * @param direction the direction of the ray, taken as given (not normalized)
     */
    public FloatRay(Float3 origin, Float3 direction) {
        this(origin.x(), origin.y(), origin.z(), direction.x(), direction.y(), direction.z());
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
    public static FloatRay[] newArray(int length) {
        FloatRay[] a = new FloatRay[length];
        java.util.Arrays.fill(a, new FloatRay(0, 0, 0, 0, 0, 0));
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
    public static FloatRay[] newArray(int length, FloatRay init) {
        java.util.Objects.requireNonNull(init, "init");
        FloatRay[] a = new FloatRay[length];
        java.util.Arrays.fill(a, init);
        return a;
    }

    /** {@return the {@code oX} component} <p>Valid input: any value, NaN and the infinities included. */
    public float oX() { return oX; }

    /** {@return the {@code oY} component} <p>Valid input: any value, NaN and the infinities included. */
    public float oY() { return oY; }

    /** {@return the {@code oZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public float oZ() { return oZ; }

    /** {@return the {@code dX} component} <p>Valid input: any value, NaN and the infinities included. */
    public float dX() { return dX; }

    /** {@return the {@code dY} component} <p>Valid input: any value, NaN and the infinities included. */
    public float dY() { return dY; }

    /** {@return the {@code dZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public float dZ() { return dZ; }

    /**
     * Create a new ray from its origin and direction.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param origin the origin of the ray
     * @param direction the direction of the ray, taken as given (not normalized)
     * @return the resulting ray
     */
    public FloatRay set(Float3 origin, Float3 direction) {
        return new FloatRay(origin, direction);
    }

    /**
     * Return a ray with the values of {@code v}: that ray itself (an immutable value needs no
     * copy).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the ray to copy
     * @return the resulting ray
     */
    public FloatRay set(FloatRay v) {
        float vOX = v.oX;
        float vOY = v.oY;
        float vOZ = v.oZ;
        float vDX = v.dX;
        float vDY = v.dY;
        float vDZ = v.dZ;
        return new FloatRay(vOX, vOY, vOZ, vDX, vDY, vDZ);
    }

    /**
     * Create a new ray from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vOX the {@code oX} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vOY the {@code oY} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vOZ the {@code oZ} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vDX the {@code dX} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vDY the {@code dY} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @param vDZ the {@code dZ} component of the ray {@code (vOX, vOY, vOZ, vDX, vDY, vDZ)}
     * @return the resulting ray
     */
    public FloatRay set(float vOX, float vOY, float vOZ, float vDX, float vDY, float vDZ) {
        return new FloatRay(vOX, vOY, vOZ, vDX, vDY, vDZ);
    }

    /**
     * Set the direction of this ray to {@code d}, returning the result as a value.
     * <p>
     * Valid input: {@code d} must be non-zero.
     *
     * @param d the new direction
     * @return the resulting ray
     */
    public FloatRay setDirection(Float3 d) {
        float dX = d.x();
        float dY = d.y();
        float dZ = d.z();
        return new FloatRay(this.oX, this.oY, this.oZ, dX, dY, dZ);
    }

    /**
     * Set the direction of this ray to ({@code dX}, {@code dY}, {@code dZ}), returning the result
     * as a value.
     * <p>
     * Valid input: {@code (dX, dY, dZ)} must be non-zero.
     *
     * @param dX the {@code x} component of the vector {@code (dX, dY, dZ)}
     * @param dY the {@code y} component of the vector {@code (dX, dY, dZ)}
     * @param dZ the {@code z} component of the vector {@code (dX, dY, dZ)}
     * @return the resulting ray
     */
    public FloatRay setDirection(float dX, float dY, float dZ) {
        return new FloatRay(this.oX, this.oY, this.oZ, dX, dY, dZ);
    }

    /**
     * Set the origin of this ray to {@code o}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the new origin
     * @return the resulting ray
     */
    public FloatRay setOrigin(Float3 o) {
        float oX = o.x();
        float oY = o.y();
        float oZ = o.z();
        return new FloatRay(oX, oY, oZ, this.dX, this.dY, this.dZ);
    }

    /**
     * Set the origin of this ray to ({@code oX}, {@code oY}, {@code oZ}), returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param oX the {@code x} component of the vector {@code (oX, oY, oZ)}
     * @param oY the {@code y} component of the vector {@code (oX, oY, oZ)}
     * @param oZ the {@code z} component of the vector {@code (oX, oY, oZ)}
     * @return the resulting ray
     */
    public FloatRay setOrigin(float oX, float oY, float oZ) {
        return new FloatRay(oX, oY, oZ, this.dX, this.dY, this.dZ);
    }

    /**
     * Convert this ray to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code DoubleRay} holding the result
     */
    public DoubleRay toDouble() {
        return new DoubleRay(this.oX, this.oY, this.oZ, this.dX, this.dY, this.dZ);
    }

    /**
     * Transform this ray by {@code m}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @return the resulting ray
     */
    public FloatRay transform(Float3x4 m) {
        if (!Math.useFma()) return transform_mulAdd(m);
        float _m_m02 = m.m02();
        float _m_m00 = m.m00();
        float _m_m01 = m.m01();
        float _m_m12 = m.m12();
        float _m_m10 = m.m10();
        float _m_m11 = m.m11();
        float _m_m22 = m.m22();
        float _m_m20 = m.m20();
        float _m_m21 = m.m21();
        return new FloatRay(java.lang.Math.fma(_m_m02, this.oZ, java.lang.Math.fma(_m_m00, this.oX, java.lang.Math.fma(_m_m01, this.oY, m.m03()))), java.lang.Math.fma(_m_m12, this.oZ, java.lang.Math.fma(_m_m10, this.oX, java.lang.Math.fma(_m_m11, this.oY, m.m13()))), java.lang.Math.fma(_m_m22, this.oZ, java.lang.Math.fma(_m_m20, this.oX, java.lang.Math.fma(_m_m21, this.oY, m.m23()))), java.lang.Math.fma(_m_m02, this.dZ, java.lang.Math.fma(_m_m00, this.dX, _m_m01 * this.dY)), java.lang.Math.fma(_m_m12, this.dZ, java.lang.Math.fma(_m_m10, this.dX, _m_m11 * this.dY)), java.lang.Math.fma(_m_m22, this.dZ, java.lang.Math.fma(_m_m20, this.dX, _m_m21 * this.dY)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRay transform_mulAdd(Float3x4 m) {
        float _m_m02 = m.m02();
        float _m_m00 = m.m00();
        float _m_m01 = m.m01();
        float _m_m12 = m.m12();
        float _m_m10 = m.m10();
        float _m_m11 = m.m11();
        float _m_m22 = m.m22();
        float _m_m20 = m.m20();
        float _m_m21 = m.m21();
        return new FloatRay(((_m_m02) * (this.oZ) + (((_m_m00) * (this.oX) + (((_m_m01) * (this.oY) + (m.m03())))))), ((_m_m12) * (this.oZ) + (((_m_m10) * (this.oX) + (((_m_m11) * (this.oY) + (m.m13())))))), ((_m_m22) * (this.oZ) + (((_m_m20) * (this.oX) + (((_m_m21) * (this.oY) + (m.m23())))))), ((_m_m02) * (this.dZ) + (((_m_m00) * (this.dX) + (_m_m01 * this.dY)))), ((_m_m12) * (this.dZ) + (((_m_m10) * (this.dX) + (_m_m11 * this.dY)))), ((_m_m22) * (this.dZ) + (((_m_m20) * (this.dX) + (_m_m21 * this.dY)))));
    }

    /**
     * Transform this ray by {@code m}, returning the result as a value.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @return the resulting ray
     */
    public FloatRay transform(Float4x4 m) {
        if (!Math.useFma()) return transform_mulAdd(m);
        float _m_m02 = m.m02();
        float _m_m00 = m.m00();
        float _m_m01 = m.m01();
        float _m_m12 = m.m12();
        float _m_m10 = m.m10();
        float _m_m11 = m.m11();
        float _m_m22 = m.m22();
        float _m_m20 = m.m20();
        float _m_m21 = m.m21();
        return new FloatRay(java.lang.Math.fma(_m_m02, this.oZ, java.lang.Math.fma(_m_m00, this.oX, java.lang.Math.fma(_m_m01, this.oY, m.m03()))), java.lang.Math.fma(_m_m12, this.oZ, java.lang.Math.fma(_m_m10, this.oX, java.lang.Math.fma(_m_m11, this.oY, m.m13()))), java.lang.Math.fma(_m_m22, this.oZ, java.lang.Math.fma(_m_m20, this.oX, java.lang.Math.fma(_m_m21, this.oY, m.m23()))), java.lang.Math.fma(_m_m02, this.dZ, java.lang.Math.fma(_m_m00, this.dX, _m_m01 * this.dY)), java.lang.Math.fma(_m_m12, this.dZ, java.lang.Math.fma(_m_m10, this.dX, _m_m11 * this.dY)), java.lang.Math.fma(_m_m22, this.dZ, java.lang.Math.fma(_m_m20, this.dX, _m_m21 * this.dY)));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRay transform_mulAdd(Float4x4 m) {
        float _m_m02 = m.m02();
        float _m_m00 = m.m00();
        float _m_m01 = m.m01();
        float _m_m12 = m.m12();
        float _m_m10 = m.m10();
        float _m_m11 = m.m11();
        float _m_m22 = m.m22();
        float _m_m20 = m.m20();
        float _m_m21 = m.m21();
        return new FloatRay(((_m_m02) * (this.oZ) + (((_m_m00) * (this.oX) + (((_m_m01) * (this.oY) + (m.m03())))))), ((_m_m12) * (this.oZ) + (((_m_m10) * (this.oX) + (((_m_m11) * (this.oY) + (m.m13())))))), ((_m_m22) * (this.oZ) + (((_m_m20) * (this.oX) + (((_m_m21) * (this.oY) + (m.m23())))))), ((_m_m02) * (this.dZ) + (((_m_m00) * (this.dX) + (_m_m01 * this.dY)))), ((_m_m12) * (this.dZ) + (((_m_m10) * (this.dX) + (_m_m11 * this.dY)))), ((_m_m22) * (this.dZ) + (((_m_m20) * (this.dX) + (_m_m21 * this.dY)))));
    }

    /**
     * Compute the point on this ray at the parameter value {@code t}, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the ray parameter: the point is {@code origin + t * direction}
     * @return the resulting vector
     */
    public Float3 at(float t) {
        if (Math.useFma()) {
            return new Float3(java.lang.Math.fma(t, this.dX, this.oX), java.lang.Math.fma(t, this.dY, this.oY), java.lang.Math.fma(t, this.dZ, this.oZ));
        } else {
            return new Float3(((t) * (this.dX) + (this.oX)), ((t) * (this.dY) + (this.oY)), ((t) * (this.dZ) + (this.oZ)));
        }
    }

    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to find the closest point to
     * @return the resulting vector
     */
    public Float3 closestPointToPoint(Float3 p) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (Math.useFma()) {
            float _t10 = Math.max(0.0f, java.lang.Math.fma(this.dZ, pZ - this.oZ, java.lang.Math.fma(this.dX, pX - this.oX, this.dY * (pY - this.oY))) / java.lang.Math.fma(this.dZ, this.dZ, java.lang.Math.fma(this.dX, this.dX, this.dY * this.dY)));
            return new Float3(java.lang.Math.fma(this.dX, _t10, this.oX), java.lang.Math.fma(this.dY, _t10, this.oY), java.lang.Math.fma(this.dZ, _t10, this.oZ));
        } else {
            float _t10 = Math.max(0.0f, ((this.dZ) * (pZ - this.oZ) + (((this.dX) * (pX - this.oX) + (this.dY * (pY - this.oY))))) / ((this.dZ) * (this.dZ) + (((this.dX) * (this.dX) + (this.dY * this.dY)))));
            return new Float3(((this.dX) * (_t10) + (this.oX)), ((this.dY) * (_t10) + (this.oY)), ((this.dZ) * (_t10) + (this.oZ)));
        }
    }

    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
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
     * @return the resulting vector
     */
    public Float3 closestPointToPoint(float pX, float pY, float pZ) {
        if (Math.useFma()) {
            float _t10 = Math.max(0.0f, java.lang.Math.fma(this.dZ, pZ - this.oZ, java.lang.Math.fma(this.dX, pX - this.oX, this.dY * (pY - this.oY))) / java.lang.Math.fma(this.dZ, this.dZ, java.lang.Math.fma(this.dX, this.dX, this.dY * this.dY)));
            return new Float3(java.lang.Math.fma(this.dX, _t10, this.oX), java.lang.Math.fma(this.dY, _t10, this.oY), java.lang.Math.fma(this.dZ, _t10, this.oZ));
        } else {
            float _t10 = Math.max(0.0f, ((this.dZ) * (pZ - this.oZ) + (((this.dX) * (pX - this.oX) + (this.dY * (pY - this.oY))))) / ((this.dZ) * (this.dZ) + (((this.dX) * (this.dX) + (this.dY * this.dY)))));
            return new Float3(((this.dX) * (_t10) + (this.oX)), ((this.dY) * (_t10) + (this.oY)), ((this.dZ) * (_t10) + (this.oZ)));
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
    public float distanceSquaredToPoint(Float3 p) {
        return distanceSquaredToPoint(p.x(), p.y(), p.z());
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
        if (Math.useFma()) {
            float _t3 = pZ - this.oZ;
            float _t4 = pX - this.oX;
            float _t5 = pY - this.oY;
            float _t13 = Math.max(0.0f, java.lang.Math.fma(this.dZ, _t3, java.lang.Math.fma(this.dX, _t4, this.dY * _t5)) / java.lang.Math.fma(this.dZ, this.dZ, java.lang.Math.fma(this.dX, this.dX, this.dY * this.dY)));
            float _t14 = java.lang.Math.fma(-this.dZ, _t13, _t3);
            float _t15 = java.lang.Math.fma(-this.dX, _t13, _t4);
            float _t16 = java.lang.Math.fma(-this.dY, _t13, _t5);
            return java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16));
        } else {
            float _t3 = pZ - this.oZ;
            float _t4 = pX - this.oX;
            float _t5 = pY - this.oY;
            float _t13 = Math.max(0.0f, ((this.dZ) * (_t3) + (((this.dX) * (_t4) + (this.dY * _t5)))) / ((this.dZ) * (this.dZ) + (((this.dX) * (this.dX) + (this.dY * this.dY)))));
            float _t14 = ((_t3) - (this.dZ) * (_t13));
            float _t15 = ((_t4) - (this.dX) * (_t13));
            float _t16 = ((_t5) - (this.dY) * (_t13));
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
    public float distanceToPoint(Float3 p) {
        return distanceToPoint(p.x(), p.y(), p.z());
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
        if (Math.useFma()) {
            float _t3 = pZ - this.oZ;
            float _t4 = pX - this.oX;
            float _t5 = pY - this.oY;
            float _t13 = Math.max(0.0f, java.lang.Math.fma(this.dZ, _t3, java.lang.Math.fma(this.dX, _t4, this.dY * _t5)) / java.lang.Math.fma(this.dZ, this.dZ, java.lang.Math.fma(this.dX, this.dX, this.dY * this.dY)));
            float _t14 = java.lang.Math.fma(-this.dZ, _t13, _t3);
            float _t15 = java.lang.Math.fma(-this.dX, _t13, _t4);
            float _t16 = java.lang.Math.fma(-this.dY, _t13, _t5);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)));
        } else {
            float _t3 = pZ - this.oZ;
            float _t4 = pX - this.oX;
            float _t5 = pY - this.oY;
            float _t13 = Math.max(0.0f, ((this.dZ) * (_t3) + (((this.dX) * (_t4) + (this.dY * _t5)))) / ((this.dZ) * (this.dZ) + (((this.dX) * (this.dX) + (this.dY * this.dY)))));
            float _t14 = ((_t3) - (this.dZ) * (_t13));
            float _t15 = ((_t4) - (this.dX) * (_t13));
            float _t16 = ((_t5) - (this.dY) * (_t13));
            return (float) java.lang.Math.sqrt(((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16)))));
        }
    }

    /**
     * Get the direction of this ray, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getDirection() {
        return new Float3(this.dX, this.dY, this.dZ);
    }

    /**
     * Get the origin of this ray, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getOrigin() {
        return new Float3(this.oX, this.oY, this.oZ);
    }

    /**
     * {@return a copy with the {@code oX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code oX} component
     */
    public FloatRay withOX(float v) {
        return new FloatRay(v, oY, oZ, dX, dY, dZ);
    }

    /**
     * {@return a copy with the {@code oY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code oY} component
     */
    public FloatRay withOY(float v) {
        return new FloatRay(oX, v, oZ, dX, dY, dZ);
    }

    /**
     * {@return a copy with the {@code oZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code oZ} component
     */
    public FloatRay withOZ(float v) {
        return new FloatRay(oX, oY, v, dX, dY, dZ);
    }

    /**
     * {@return a copy with the {@code dX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code dX} component
     */
    public FloatRay withDX(float v) {
        return new FloatRay(oX, oY, oZ, v, dY, dZ);
    }

    /**
     * {@return a copy with the {@code dY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code dY} component
     */
    public FloatRay withDY(float v) {
        return new FloatRay(oX, oY, oZ, dX, v, dZ);
    }

    /**
     * {@return a copy with the {@code dZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code dZ} component
     */
    public FloatRay withDZ(float v) {
        return new FloatRay(oX, oY, oZ, dX, dY, v);
    }

    @Override public String toString() {
        return "FloatRay(" + this.oX + ", " + this.oY + ", " + this.oZ + ", " + this.dX + ", " + this.dY + ", " + this.dZ + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatRay)) return false;
        FloatRay o = (FloatRay) obj;
        return Float.floatToIntBits(oX) == Float.floatToIntBits(o.oX)
            && Float.floatToIntBits(oY) == Float.floatToIntBits(o.oY)
            && Float.floatToIntBits(oZ) == Float.floatToIntBits(o.oZ)
            && Float.floatToIntBits(dX) == Float.floatToIntBits(o.dX)
            && Float.floatToIntBits(dY) == Float.floatToIntBits(o.dY)
            && Float.floatToIntBits(dZ) == Float.floatToIntBits(o.dZ);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(oX);
        h = 31 * h + Float.floatToIntBits(oY);
        h = 31 * h + Float.floatToIntBits(oZ);
        h = 31 * h + Float.floatToIntBits(dX);
        h = 31 * h + Float.floatToIntBits(dY);
        h = 31 * h + Float.floatToIntBits(dZ);
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Float.isFinite(oX)
            && Float.isFinite(oY)
            && Float.isFinite(oZ)
            && Float.isFinite(dX)
            && Float.isFinite(dY)
            && Float.isFinite(dZ);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Float.isNaN(oX)
            || Float.isNaN(oY)
            || Float.isNaN(oZ)
            || Float.isNaN(dX)
            || Float.isNaN(dY)
            || Float.isNaN(dZ);
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
    public boolean equalsEpsilon(FloatRay other, float epsilon) {
        return java.lang.Math.abs(oX - other.oX) <= epsilon
            && java.lang.Math.abs(oY - other.oY) <= epsilon
            && java.lang.Math.abs(oZ - other.oZ) <= epsilon
            && java.lang.Math.abs(dX - other.dX) <= epsilon
            && java.lang.Math.abs(dY - other.dY) <= epsilon
            && java.lang.Math.abs(dZ - other.dZ) <= epsilon;
    }

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
        dest[offset] = this.oX;
        dest[offset + 1] = this.oY;
        dest[offset + 2] = this.oZ;
        dest[offset + 3] = this.dX;
        dest[offset + 4] = this.dY;
        dest[offset + 5] = this.dZ;
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
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay load(float[] src, int offset) {
        float _c0 = src[offset];
        float _c1 = src[offset + 1];
        float _c2 = src[offset + 2];
        float _c3 = src[offset + 3];
        float _c4 = src[offset + 4];
        float _c5 = src[offset + 5];
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay load(float[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
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
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return buf;
    }

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code FloatRay} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatRay loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatRay r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 6);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
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
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position and advancing
     * the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code FloatRay} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatRay loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatRay r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 24);
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
     */
    public FloatRay storeUnsafe(long address) {
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
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store(MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

    /**
     * Load the elements from the given memory segment, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(offset, src);
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
        dest[offset] = this.oX;
        dest[offset + 1] = this.oY;
        dest[offset + 2] = this.oZ;
        dest[offset + 3] = this.dX;
        dest[offset + 4] = this.dY;
        dest[offset + 5] = this.dZ;
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
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay load(double[] src, int offset) {
        float _c0 = (float) src[offset];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[offset + 2];
        float _c3 = (float) src[offset + 3];
        float _c4 = (float) src[offset + 4];
        float _c5 = (float) src[offset + 5];
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    /**
     * Load the elements from the given array, converting each element from {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay load(double[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code double}, starting
     * at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
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
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return buf;
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
     * starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code FloatRay} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatRay loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatRay r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 6);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
     * starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
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
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
     * starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code FloatRay} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatRay loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatRay r = StoreLoad.BB_OPS.loadDoubleAbsolute(pos, buf);
        buf.position(pos + 48);
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
     */
    public FloatRay storeDoubleUnsafe(long address) {
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
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment, converting each element to {@code double}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeDouble(MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code double},
     * starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(0L, src); }

    /**
     * Load the elements from the given memory segment, converting each element from {@code double},
     * starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(offset, src);
    }

    private abstract static class FloatRaySegOps {
        public abstract MemorySegment store(FloatRay self, long offset, MemorySegment dest);
        public abstract FloatRay load(long offset, MemorySegment src);
        public abstract MemorySegment storeDouble(FloatRay self, long offset, MemorySegment dest);
        public abstract FloatRay loadDouble(long offset, MemorySegment src);
    }

    private static final class FloatRaySegOpsMS extends FloatRaySegOps {
        public MemorySegment store(FloatRay self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.oX);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.oY);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.oZ);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.dX);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.dY);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.dZ);
            return dest;
        }

        public FloatRay load(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            float _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            float _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
            float _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
            float _c3 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
            float _c4 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
            float _c5 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeDouble(FloatRay self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.oX);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.oY);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.oZ);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.dX);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.dY);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.dZ);
            return dest;
        }

        public FloatRay loadDouble(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            float _c0 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            float _c1 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
            float _c2 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
            float _c3 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
            float _c4 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
            float _c5 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }
    }

    private static final class FloatRaySegOpsUnsafe extends FloatRaySegOps {
        private static final FloatRaySegOpsMS MS = new FloatRaySegOpsMS();

        public MemorySegment store(FloatRay self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.store(self, offset, dest);
            if (!dest.isNative()) return storeOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.oX);
            _U.putFloat(address + 4L, self.oY);
            _U.putFloat(address + 8L, self.oZ);
            _U.putFloat(address + 12L, self.dX);
            _U.putFloat(address + 16L, self.dY);
            _U.putFloat(address + 20L, self.dZ);
            return dest;
        }

        private static MemorySegment storeOnHeap(FloatRay self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.store(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.oX);
            _U.putFloat(bytes, address + 4L, self.oY);
            _U.putFloat(bytes, address + 8L, self.oZ);
            _U.putFloat(bytes, address + 12L, self.dX);
            _U.putFloat(bytes, address + 16L, self.dY);
            _U.putFloat(bytes, address + 20L, self.dZ);
            return dest;
        }

        public FloatRay load(long offset, MemorySegment src) {
            if (!src.isNative()) return loadOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static FloatRay loadOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.load(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4L);
            float _c2 = _U.getFloat(bytes, address + 8L);
            float _c3 = _U.getFloat(bytes, address + 12L);
            float _c4 = _U.getFloat(bytes, address + 16L);
            float _c5 = _U.getFloat(bytes, address + 20L);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public MemorySegment storeDouble(FloatRay self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
            if (!dest.isNative()) return storeDoubleOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.oX);
            _U.putDouble(address + 8L, self.oY);
            _U.putDouble(address + 16L, self.oZ);
            _U.putDouble(address + 24L, self.dX);
            _U.putDouble(address + 32L, self.dY);
            _U.putDouble(address + 40L, self.dZ);
            return dest;
        }

        private static MemorySegment storeDoubleOnHeap(FloatRay self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeDouble(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.oX);
            _U.putDouble(bytes, address + 8L, self.oY);
            _U.putDouble(bytes, address + 16L, self.oZ);
            _U.putDouble(bytes, address + 24L, self.dX);
            _U.putDouble(bytes, address + 32L, self.dY);
            _U.putDouble(bytes, address + 40L, self.dZ);
            return dest;
        }

        public FloatRay loadDouble(long offset, MemorySegment src) {
            if (!src.isNative()) return loadDoubleOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static FloatRay loadDoubleOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadDouble(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8L);
            float _c2 = (float) _U.getDouble(bytes, address + 16L);
            float _c3 = (float) _U.getDouble(bytes, address + 24L);
            float _c4 = (float) _U.getDouble(bytes, address + 32L);
            float _c5 = (float) _U.getDouble(bytes, address + 40L);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }
    }

    private abstract static class FloatRayBbOps {
        public abstract FloatBuffer storeAbsolute(FloatRay self, int index, FloatBuffer buf);
        public abstract FloatRay loadAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeAbsolute(FloatRay self, int index, ByteBuffer buf);
        public abstract FloatRay loadAbsolute(int index, ByteBuffer buf);
        public abstract DoubleBuffer storeAbsolute(FloatRay self, int index, DoubleBuffer buf);
        public abstract FloatRay loadAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeDoubleAbsolute(FloatRay self, int index, ByteBuffer buf);
        public abstract FloatRay loadDoubleAbsolute(int index, ByteBuffer buf);
    }

    private static final class FloatRayBbOpsApi extends FloatRayBbOps {
        public FloatBuffer storeAbsolute(FloatRay self, int index, FloatBuffer buf) {
            buf.put(index, self.oX);
            buf.put(index + 1, self.oY);
            buf.put(index + 2, self.oZ);
            buf.put(index + 3, self.dX);
            buf.put(index + 4, self.dY);
            buf.put(index + 5, self.dZ);
            return buf;
        }

        public FloatRay loadAbsolute(int index, FloatBuffer buf) {
            float _c0 = buf.get(index);
            float _c1 = buf.get(index + 1);
            float _c2 = buf.get(index + 2);
            float _c3 = buf.get(index + 3);
            float _c4 = buf.get(index + 4);
            float _c5 = buf.get(index + 5);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeAbsolute(FloatRay self, int index, ByteBuffer buf) {
            buf.putFloat(index, self.oX);
            buf.putFloat(index + 4, self.oY);
            buf.putFloat(index + 8, self.oZ);
            buf.putFloat(index + 12, self.dX);
            buf.putFloat(index + 16, self.dY);
            buf.putFloat(index + 20, self.dZ);
            return buf;
        }

        public FloatRay loadAbsolute(int index, ByteBuffer buf) {
            float _c0 = buf.getFloat(index);
            float _c1 = buf.getFloat(index + 4);
            float _c2 = buf.getFloat(index + 8);
            float _c3 = buf.getFloat(index + 12);
            float _c4 = buf.getFloat(index + 16);
            float _c5 = buf.getFloat(index + 20);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public DoubleBuffer storeAbsolute(FloatRay self, int index, DoubleBuffer buf) {
            buf.put(index, self.oX);
            buf.put(index + 1, self.oY);
            buf.put(index + 2, self.oZ);
            buf.put(index + 3, self.dX);
            buf.put(index + 4, self.dY);
            buf.put(index + 5, self.dZ);
            return buf;
        }

        public FloatRay loadAbsolute(int index, DoubleBuffer buf) {
            float _c0 = (float) buf.get(index);
            float _c1 = (float) buf.get(index + 1);
            float _c2 = (float) buf.get(index + 2);
            float _c3 = (float) buf.get(index + 3);
            float _c4 = (float) buf.get(index + 4);
            float _c5 = (float) buf.get(index + 5);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeDoubleAbsolute(FloatRay self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.oX);
            buf.putDouble(index + 8, self.oY);
            buf.putDouble(index + 16, self.oZ);
            buf.putDouble(index + 24, self.dX);
            buf.putDouble(index + 32, self.dY);
            buf.putDouble(index + 40, self.dZ);
            return buf;
        }

        public FloatRay loadDoubleAbsolute(int index, ByteBuffer buf) {
            float _c0 = (float) buf.getDouble(index);
            float _c1 = (float) buf.getDouble(index + 8);
            float _c2 = (float) buf.getDouble(index + 16);
            float _c3 = (float) buf.getDouble(index + 24);
            float _c4 = (float) buf.getDouble(index + 32);
            float _c5 = (float) buf.getDouble(index + 40);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }
    }

    private static final class FloatRayBbOpsUnsafe extends FloatRayBbOps {
        private static final FloatRayBbOpsApi API = new FloatRayBbOpsApi();

        public FloatBuffer storeAbsolute(FloatRay self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            _U.putFloat(address, self.oX);
            _U.putFloat(address + 4L, self.oY);
            _U.putFloat(address + 8L, self.oZ);
            _U.putFloat(address + 12L, self.dX);
            _U.putFloat(address + 16L, self.dY);
            _U.putFloat(address + 20L, self.dZ);
            return buf;
        }

        public FloatRay loadAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeAbsolute(FloatRay self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(address, self.oX);
            _U.putFloat(address + 4L, self.oY);
            _U.putFloat(address + 8L, self.oZ);
            _U.putFloat(address + 12L, self.dX);
            _U.putFloat(address + 16L, self.dY);
            _U.putFloat(address + 20L, self.dZ);
            return buf;
        }

        private static ByteBuffer storeAbsoluteElse(FloatRay self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(bytes, address, self.oX);
            _U.putFloat(bytes, address + 4L, self.oY);
            _U.putFloat(bytes, address + 8L, self.oZ);
            _U.putFloat(bytes, address + 12L, self.dX);
            _U.putFloat(bytes, address + 16L, self.dY);
            _U.putFloat(bytes, address + 20L, self.dZ);
            return buf;
        }

        public FloatRay loadAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static FloatRay loadAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4L);
            float _c2 = _U.getFloat(bytes, address + 8L);
            float _c3 = _U.getFloat(bytes, address + 12L);
            float _c4 = _U.getFloat(bytes, address + 16L);
            float _c5 = _U.getFloat(bytes, address + 20L);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public DoubleBuffer storeAbsolute(FloatRay self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            _U.putDouble(address, self.oX);
            _U.putDouble(address + 8L, self.oY);
            _U.putDouble(address + 16L, self.oZ);
            _U.putDouble(address + 24L, self.dX);
            _U.putDouble(address + 32L, self.dY);
            _U.putDouble(address + 40L, self.dZ);
            return buf;
        }

        public FloatRay loadAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public ByteBuffer storeDoubleAbsolute(FloatRay self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeDoubleAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(address, self.oX);
            _U.putDouble(address + 8L, self.oY);
            _U.putDouble(address + 16L, self.oZ);
            _U.putDouble(address + 24L, self.dX);
            _U.putDouble(address + 32L, self.dY);
            _U.putDouble(address + 40L, self.dZ);
            return buf;
        }

        private static ByteBuffer storeDoubleAbsoluteElse(FloatRay self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeDoubleAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(bytes, address, self.oX);
            _U.putDouble(bytes, address + 8L, self.oY);
            _U.putDouble(bytes, address + 16L, self.oZ);
            _U.putDouble(bytes, address + 24L, self.dX);
            _U.putDouble(bytes, address + 32L, self.dY);
            _U.putDouble(bytes, address + 40L, self.dZ);
            return buf;
        }

        public FloatRay loadDoubleAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadDoubleAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        private static FloatRay loadDoubleAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadDoubleAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8L);
            float _c2 = (float) _U.getDouble(bytes, address + 16L);
            float _c3 = (float) _U.getDouble(bytes, address + 24L);
            float _c4 = (float) _U.getDouble(bytes, address + 32L);
            float _c5 = (float) _U.getDouble(bytes, address + 40L);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }
    }

    private abstract static class FloatRayRawOps {
        public abstract FloatRay storeUnsafe(FloatRay self, long address);
        public abstract FloatRay loadUnsafe(long address);
        public abstract FloatRay storeDoubleUnsafe(FloatRay self, long address);
        public abstract FloatRay loadDoubleUnsafe(long address);
    }

    private static final class FloatRayRawOpsApi extends FloatRayRawOps {
        public FloatRay storeUnsafe(FloatRay self, long address) {
            self.store(0L, virtualMemory().asSlice(address, 24L));
            return self;
        }

        public FloatRay loadUnsafe(long address) {
            return FloatRay.load(0L, virtualMemory().asSlice(address, 24L));
        }

        public FloatRay storeDoubleUnsafe(FloatRay self, long address) {
            self.storeDouble(0L, virtualMemory().asSlice(address, 48L));
            return self;
        }

        public FloatRay loadDoubleUnsafe(long address) {
            return FloatRay.loadDouble(0L, virtualMemory().asSlice(address, 48L));
        }
    }

    private static final class FloatRayRawOpsUnsafe extends FloatRayRawOps {
        public FloatRay storeUnsafe(FloatRay self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.oX);
            _U.putFloat(address + 4L, self.oY);
            _U.putFloat(address + 8L, self.oZ);
            _U.putFloat(address + 12L, self.dX);
            _U.putFloat(address + 16L, self.dY);
            _U.putFloat(address + 20L, self.dZ);
            return self;
        }

        public FloatRay storeHeap(FloatRay self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.oX);
            _U.putFloat(bytes, address + 4L, self.oY);
            _U.putFloat(bytes, address + 8L, self.oZ);
            _U.putFloat(bytes, address + 12L, self.dX);
            _U.putFloat(bytes, address + 16L, self.dY);
            _U.putFloat(bytes, address + 20L, self.dZ);
            return self;
        }

        public FloatRay loadUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public FloatRay loadHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4L);
            float _c2 = _U.getFloat(bytes, address + 8L);
            float _c3 = _U.getFloat(bytes, address + 12L);
            float _c4 = _U.getFloat(bytes, address + 16L);
            float _c5 = _U.getFloat(bytes, address + 20L);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public FloatRay storeDoubleUnsafe(FloatRay self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.oX);
            _U.putDouble(address + 8L, self.oY);
            _U.putDouble(address + 16L, self.oZ);
            _U.putDouble(address + 24L, self.dX);
            _U.putDouble(address + 32L, self.dY);
            _U.putDouble(address + 40L, self.dZ);
            return self;
        }

        public FloatRay storeDoubleHeap(FloatRay self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.oX);
            _U.putDouble(bytes, address + 8L, self.oY);
            _U.putDouble(bytes, address + 16L, self.oZ);
            _U.putDouble(bytes, address + 24L, self.dX);
            _U.putDouble(bytes, address + 32L, self.dY);
            _U.putDouble(bytes, address + 40L, self.dZ);
            return self;
        }

        public FloatRay loadDoubleUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }

        public FloatRay loadDoubleHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8L);
            float _c2 = (float) _U.getDouble(bytes, address + 16L);
            float _c3 = (float) _U.getDouble(bytes, address + 24L);
            float _c4 = (float) _U.getDouble(bytes, address + 32L);
            float _c5 = (float) _U.getDouble(bytes, address + 40L);
            return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
        }
    }
}
