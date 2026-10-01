// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

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
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
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
     * Valid input: any value, NaN and the infinities included.
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
     * Create a new ray from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the ray to copy
     * @return the resulting ray
     */
    public FloatRay set(FloatRay v) {
        float vOX = v.oX();
        float vOY = v.oY();
        float vOZ = v.oZ();
        float vDX = v.dX();
        float vDY = v.dY();
        float vDZ = v.dZ();
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
        return new FloatRay(Math.fma(m.m02(), this.oZ, Math.fma(m.m00(), this.oX, Math.fma(m.m01(), this.oY, m.m03()))), Math.fma(m.m12(), this.oZ, Math.fma(m.m10(), this.oX, Math.fma(m.m11(), this.oY, m.m13()))), Math.fma(m.m22(), this.oZ, Math.fma(m.m20(), this.oX, Math.fma(m.m21(), this.oY, m.m23()))), Math.fma(m.m02(), this.dZ, Math.fma(m.m00(), this.dX, m.m01() * this.dY)), Math.fma(m.m12(), this.dZ, Math.fma(m.m10(), this.dX, m.m11() * this.dY)), Math.fma(m.m22(), this.dZ, Math.fma(m.m20(), this.dX, m.m21() * this.dY)));
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
        return new FloatRay(Math.fma(m.m02(), this.oZ, Math.fma(m.m00(), this.oX, Math.fma(m.m01(), this.oY, m.m03()))), Math.fma(m.m12(), this.oZ, Math.fma(m.m10(), this.oX, Math.fma(m.m11(), this.oY, m.m13()))), Math.fma(m.m22(), this.oZ, Math.fma(m.m20(), this.oX, Math.fma(m.m21(), this.oY, m.m23()))), Math.fma(m.m02(), this.dZ, Math.fma(m.m00(), this.dX, m.m01() * this.dY)), Math.fma(m.m12(), this.dZ, Math.fma(m.m10(), this.dX, m.m11() * this.dY)), Math.fma(m.m22(), this.dZ, Math.fma(m.m20(), this.dX, m.m21() * this.dY)));
    }


    /**
     * Compute the point on this ray at the parameter value {@code t}, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the distance along the ray, as a multiple of the ray direction
     * @return the resulting vector
     */
    public Float3 at(float t) {
        return new Float3(Math.fma(t, this.dX, this.oX), Math.fma(t, this.dY, this.oY), Math.fma(t, this.dZ, this.oZ));
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
        float _t10 = java.lang.Math.max(0.0f, Math.fma(this.dZ, p.z() - this.oZ, Math.fma(this.dX, p.x() - this.oX, this.dY * (p.y() - this.oY))) / Math.fma(this.dZ, this.dZ, Math.fma(this.dX, this.dX, this.dY * this.dY)));
        return new Float3(Math.fma(this.dX, _t10, this.oX), Math.fma(this.dY, _t10, this.oY), Math.fma(this.dZ, _t10, this.oZ));
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
        float _t10 = java.lang.Math.max(0.0f, Math.fma(this.dZ, pZ - this.oZ, Math.fma(this.dX, pX - this.oX, this.dY * (pY - this.oY))) / Math.fma(this.dZ, this.dZ, Math.fma(this.dX, this.dX, this.dY * this.dY)));
        return new Float3(Math.fma(this.dX, _t10, this.oX), Math.fma(this.dY, _t10, this.oY), Math.fma(this.dZ, _t10, this.oZ));
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
        float _t3 = p.z() - this.oZ;
        float _t4 = p.x() - this.oX;
        float _t5 = p.y() - this.oY;
        float _t13 = java.lang.Math.max(0.0f, Math.fma(this.dZ, _t3, Math.fma(this.dX, _t4, this.dY * _t5)) / Math.fma(this.dZ, this.dZ, Math.fma(this.dX, this.dX, this.dY * this.dY)));
        float _t14 = Math.fma(-this.dZ, _t13, _t3);
        float _t15 = Math.fma(-this.dX, _t13, _t4);
        float _t16 = Math.fma(-this.dY, _t13, _t5);
        return Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16));
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
        float _t3 = pZ - this.oZ;
        float _t4 = pX - this.oX;
        float _t5 = pY - this.oY;
        float _t13 = java.lang.Math.max(0.0f, Math.fma(this.dZ, _t3, Math.fma(this.dX, _t4, this.dY * _t5)) / Math.fma(this.dZ, this.dZ, Math.fma(this.dX, this.dX, this.dY * this.dY)));
        float _t14 = Math.fma(-this.dZ, _t13, _t3);
        float _t15 = Math.fma(-this.dX, _t13, _t4);
        float _t16 = Math.fma(-this.dY, _t13, _t5);
        return Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16));
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
        float _t3 = p.z() - this.oZ;
        float _t4 = p.x() - this.oX;
        float _t5 = p.y() - this.oY;
        float _t13 = java.lang.Math.max(0.0f, Math.fma(this.dZ, _t3, Math.fma(this.dX, _t4, this.dY * _t5)) / Math.fma(this.dZ, this.dZ, Math.fma(this.dX, this.dX, this.dY * this.dY)));
        float _t14 = Math.fma(-this.dZ, _t13, _t3);
        float _t15 = Math.fma(-this.dX, _t13, _t4);
        float _t16 = Math.fma(-this.dY, _t13, _t5);
        return (float) java.lang.Math.sqrt(Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16)));
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
        float _t3 = pZ - this.oZ;
        float _t4 = pX - this.oX;
        float _t5 = pY - this.oY;
        float _t13 = java.lang.Math.max(0.0f, Math.fma(this.dZ, _t3, Math.fma(this.dX, _t4, this.dY * _t5)) / Math.fma(this.dZ, this.dZ, Math.fma(this.dX, this.dX, this.dY * this.dY)));
        float _t14 = Math.fma(-this.dZ, _t13, _t3);
        float _t15 = Math.fma(-this.dX, _t13, _t4);
        float _t16 = Math.fma(-this.dY, _t13, _t5);
        return (float) java.lang.Math.sqrt(Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16)));
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
        return "FloatRay(" + oX() + ", " + oY() + ", " + oZ() + ", " + dX() + ", " + dY() + ", " + dZ() + ")";
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
     * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and
     * a NaN component never compares equal to anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the value to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    public boolean equalsEpsilon(FloatRay other, float epsilon) {
        return java.lang.Math.abs(oX - other.oX()) <= epsilon
            && java.lang.Math.abs(oY - other.oY()) <= epsilon
            && java.lang.Math.abs(oZ - other.oZ()) <= epsilon
            && java.lang.Math.abs(dX - other.dX()) <= epsilon
            && java.lang.Math.abs(dY - other.dY()) <= epsilon
            && java.lang.Math.abs(dZ - other.dZ()) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatRayBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatRayBbOpsUnsafe()
                        : new FloatRayBbOpsApi();
        static final FloatRayRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatRayRawOpsUnsafe()
                        : new FloatRayRawOpsApi();
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay load(float[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * Store the elements into the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public FloatRay storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code FloatRay} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static FloatRay loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(address);
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code FloatRay} holding the loaded elements
     */
    public static FloatRay load(double[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code double}, starting
     * at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * Store the elements into the given buffer, converting each element to {@code double}, starting
     * at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public FloatRay storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code FloatRay} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static FloatRay loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(address);
    }
}
