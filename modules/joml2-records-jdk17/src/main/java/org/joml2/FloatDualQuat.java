// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Immutable dual quaternion of single-precision {@code float} components.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance.
 * <p>
 * A rigid motion is a unit dual quaternion: a unit real part, and a dual part orthogonal to it. The
 * operations that apply, invert or convert this dual quaternion assume it and do not divide the
 * real part's length out. A value that has drifted from unit length (after many multiplications,
 * say) gives wrong results rather than an error: {@code normalize} it first.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns).
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 *
 * @param rX the {@code rX} component
 * @param rY the {@code rY} component
 * @param rZ the {@code rZ} component
 * @param rW the {@code rW} component
 * @param dX the {@code dX} component
 * @param dY the {@code dY} component
 * @param dZ the {@code dZ} component
 * @param dW the {@code dW} component
 */
public record FloatDualQuat(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 32;

    /** The zero dual quaternion (all components 0). */
    public static final FloatDualQuat ZERO = new FloatDualQuat(0, 0, 0, 0, 0, 0, 0, 0);

    /** The identity dual quaternion. */
    public static final FloatDualQuat IDENTITY = new FloatDualQuat();

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param rX the {@code rX} component
     * @param rY the {@code rY} component
     * @param rZ the {@code rZ} component
     * @param rW the {@code rW} component
     * @param dX the {@code dX} component
     * @param dY the {@code dY} component
     * @param dZ the {@code dZ} component
     * @param dW the {@code dW} component
     */
    public FloatDualQuat(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW) {
        this.rX = rX;
        this.rY = rY;
        this.rZ = rZ;
        this.rW = rW;
        this.dX = dX;
        this.dY = dY;
        this.dZ = dZ;
        this.dW = dW;
    }

    /**
     * Create a new instance initialized to the identity.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public FloatDualQuat() {
        this(0, 0, 0, 1, 0, 0, 0, 0);
    }

    /**
     * Create a dual quaternion from its real and dual parts.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param real the real part, i.e. the rotation quaternion
     * @param dual the dual part, carrying the translation
     */
    public FloatDualQuat(FloatQuat real, FloatQuat dual) {
        this(real.x(), real.y(), real.z(), real.w(), dual.x(), dual.y(), dual.z(), dual.w());
    }

    /** {@return the {@code rX} component} <p>Valid input: any value, NaN and the infinities included. */
    public float rX() { return rX; }
    /** {@return the {@code rY} component} <p>Valid input: any value, NaN and the infinities included. */
    public float rY() { return rY; }
    /** {@return the {@code rZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public float rZ() { return rZ; }
    /** {@return the {@code rW} component} <p>Valid input: any value, NaN and the infinities included. */
    public float rW() { return rW; }
    /** {@return the {@code dX} component} <p>Valid input: any value, NaN and the infinities included. */
    public float dX() { return dX; }
    /** {@return the {@code dY} component} <p>Valid input: any value, NaN and the infinities included. */
    public float dY() { return dY; }
    /** {@return the {@code dZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public float dZ() { return dZ; }
    /** {@return the {@code dW} component} <p>Valid input: any value, NaN and the infinities included. */
    public float dW() { return dW; }

    /**
     * Create a new dual quaternion from its real and dual parts.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param real the real part, i.e. the rotation quaternion
     * @param dual the dual part, carrying the translation
     * @return the resulting dual quaternion
     */
    public FloatDualQuat set(FloatQuat real, FloatQuat dual) {
        return new FloatDualQuat(real, dual);
    }


    /**
     * Add {@code other} to this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to add
     * @return the resulting dual quaternion
     */
    public FloatDualQuat add(FloatDualQuat other) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        return new FloatDualQuat(otherRX + this.rX, otherRY + this.rY, otherRZ + this.rZ, otherRW + this.rW, otherDX + this.dX, otherDY + this.dY, otherDZ + this.dZ, otherDW + this.dW);
    }


    /**
     * Add ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherDX},
     * {@code otherDY}, {@code otherDZ}, {@code otherDW}) to this dual quaternion, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat add(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW) {
        return new FloatDualQuat(otherRX + this.rX, otherRY + this.rY, otherRZ + this.rZ, otherRW + this.rW, otherDX + this.dX, otherDY + this.dY, otherDZ + this.dZ, otherDW + this.dW);
    }


    /**
     * Multiply each component of this dual quaternion by {@code scalar}, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting dual quaternion
     */
    public FloatDualQuat mul(float scalar) {
        return new FloatDualQuat(scalar * this.rX, scalar * this.rY, scalar * this.rZ, scalar * this.rW, scalar * this.dX, scalar * this.dY, scalar * this.dZ, scalar * this.dW);
    }


    /**
     * Negate this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting dual quaternion
     */
    public FloatDualQuat negate() {
        return new FloatDualQuat(-this.rX, -this.rY, -this.rZ, -this.rW, -this.dX, -this.dY, -this.dZ, -this.dW);
    }


    /**
     * Subtract {@code other} from this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to subtract
     * @return the resulting dual quaternion
     */
    public FloatDualQuat sub(FloatDualQuat other) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        return new FloatDualQuat(this.rX - otherRX, this.rY - otherRY, this.rZ - otherRZ, this.rW - otherRW, this.dX - otherDX, this.dY - otherDY, this.dZ - otherDZ, this.dW - otherDW);
    }


    /**
     * Subtract ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW},
     * {@code otherDX}, {@code otherDY}, {@code otherDZ}, {@code otherDW}) from this dual
     * quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat sub(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW) {
        return new FloatDualQuat(this.rX - otherRX, this.rY - otherRY, this.rZ - otherRZ, this.rW - otherRW, this.dX - otherDX, this.dY - otherDY, this.dZ - otherDZ, this.dW - otherDW);
    }


    /**
     * Create a new dual quaternion from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the dual quaternion to copy
     * @return the resulting dual quaternion
     */
    public FloatDualQuat set(FloatDualQuat v) {
        float vRX = v.rX();
        float vRY = v.rY();
        float vRZ = v.rZ();
        float vRW = v.rW();
        float vDX = v.dX();
        float vDY = v.dY();
        float vDZ = v.dZ();
        float vDW = v.dW();
        return new FloatDualQuat(vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW);
    }


    /**
     * Create a new dual quaternion from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vRX the {@code rX} component of the dual quaternion
     *        {@code (vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW)}
     * @param vRY the {@code rY} component of the dual quaternion
     *        {@code (vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW)}
     * @param vRZ the {@code rZ} component of the dual quaternion
     *        {@code (vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW)}
     * @param vRW the {@code rW} component of the dual quaternion
     *        {@code (vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW)}
     * @param vDX the {@code dX} component of the dual quaternion
     *        {@code (vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW)}
     * @param vDY the {@code dY} component of the dual quaternion
     *        {@code (vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW)}
     * @param vDZ the {@code dZ} component of the dual quaternion
     *        {@code (vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW)}
     * @param vDW the {@code dW} component of the dual quaternion
     *        {@code (vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat set(float vRX, float vRY, float vRZ, float vRW, float vDX, float vDY, float vDZ, float vDW) {
        return new FloatDualQuat(vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW);
    }


    /**
     * Convert this dual quaternion to {@code double} precision, returning the result as a new
     * instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code DoubleDualQuat} holding the result
     */
    public DoubleDualQuat toDouble() {
        return new DoubleDualQuat(this.rX, this.rY, this.rZ, this.rW, this.dX, this.dY, this.dZ, this.dW);
    }


    /**
     * Create the rigid motion of the given rigid transform (an exact conversion - both represent
     * rotation plus translation).
     * <p>
     * Valid input: the rotation of {@code r} must have unit length.
     *
     * @param r the rigid transform to convert
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeFromRigid(FloatRigid r) {
        float rTX = r.tX();
        float rTY = r.tY();
        float rTZ = r.tZ();
        float rRX = r.rX();
        float rRY = r.rY();
        float rRZ = r.rZ();
        float rRW = r.rW();
        float _t0 = -rTZ;
        return new FloatDualQuat(rRX, rRY, rRZ, rRW, 0.5f * Math.fma(_t0, rRY, Math.fma(rTX, rRW, rTY * rRZ)), 0.5f * Math.fma(rTZ, rRX, Math.fma(rTY, rRW, -(rTX * rRZ))), 0.5f * Math.fma(rTZ, rRW, Math.fma(rTX, rRY, -(rTY * rRX))), 0.5f * Math.fma(_t0, rRZ, Math.fma(-rTY, rRY, -(rTX * rRX))));
    }


    /**
     * Create the rigid motion of the given rigid transform (an exact conversion - both represent
     * rotation plus translation).
     * <p>
     * Valid input: {@code (rRX, rRY, rRZ, rRW)} must have unit length.
     *
     * @param rTX the {@code tX} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rTY the {@code tY} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rTZ the {@code tZ} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rRX the {@code rX} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rRY the {@code rY} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rRZ the {@code rZ} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rRW the {@code rW} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeFromRigid(float rTX, float rTY, float rTZ, float rRX, float rRY, float rRZ, float rRW) {
        float _t0 = -rTZ;
        return new FloatDualQuat(rRX, rRY, rRZ, rRW, 0.5f * Math.fma(_t0, rRY, Math.fma(rTX, rRW, rTY * rRZ)), 0.5f * Math.fma(rTZ, rRX, Math.fma(rTY, rRW, -(rTX * rRZ))), 0.5f * Math.fma(rTZ, rRW, Math.fma(rTX, rRY, -(rTY * rRX))), 0.5f * Math.fma(_t0, rRZ, Math.fma(-rTY, rRY, -(rTX * rRX))));
    }


    /**
     * Create the rigid motion (rotation and translation) of the given transform; the scale is
     * dropped (dual quaternions cannot represent it).
     * <p>
     * Valid input: the rotation of {@code t} must have unit length.
     *
     * @param t the transform to convert
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeFromTransform(FloatTransform t) {
        float tTX = t.tX();
        float tTY = t.tY();
        float tTZ = t.tZ();
        float tRX = t.rX();
        float tRY = t.rY();
        float tRZ = t.rZ();
        float tRW = t.rW();
        float _t0 = -tTZ;
        return new FloatDualQuat(tRX, tRY, tRZ, tRW, 0.5f * Math.fma(_t0, tRY, Math.fma(tTX, tRW, tTY * tRZ)), 0.5f * Math.fma(tTZ, tRX, Math.fma(tTY, tRW, -(tTX * tRZ))), 0.5f * Math.fma(tTZ, tRW, Math.fma(tTX, tRY, -(tTY * tRX))), 0.5f * Math.fma(_t0, tRZ, Math.fma(-tTY, tRY, -(tTX * tRX))));
    }


    /**
     * Create the rigid motion (rotation and translation) of the given transform; the scale is
     * dropped (dual quaternions cannot represent it).
     * <p>
     * Valid input: {@code (tRX, tRY, tRZ, tRW)} must have unit length.
     *
     * @param tTX the {@code tX} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tTY the {@code tY} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tTZ the {@code tZ} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tRX the {@code rX} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tRY the {@code rY} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tRZ the {@code rZ} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tRW the {@code rW} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tSX the {@code sX} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tSY the {@code sY} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tSZ the {@code sZ} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeFromTransform(float tTX, float tTY, float tTZ, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        float _t0 = -tTZ;
        return new FloatDualQuat(tRX, tRY, tRZ, tRW, 0.5f * Math.fma(_t0, tRY, Math.fma(tTX, tRW, tTY * tRZ)), 0.5f * Math.fma(tTZ, tRX, Math.fma(tTY, tRW, -(tTX * tRZ))), 0.5f * Math.fma(tTZ, tRW, Math.fma(tTX, tRY, -(tTY * tRX))), 0.5f * Math.fma(_t0, tRZ, Math.fma(-tTY, tRY, -(tTX * tRX))));
    }


    /**
     * Convert this unit dual quaternion to a rigid transform (an exact conversion - both represent
     * rotation plus translation), returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the resulting rigid transform
     */
    public FloatRigid toRigid() {
        return new FloatRigid(2.0f * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW))), 2.0f * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW))), 2.0f * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW))), this.rX, this.rY, this.rZ, this.rW);
    }


    /**
     * Convert this unit dual quaternion to a TRS transform (translation and rotation from the rigid
     * motion, scale = 1), returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the resulting transform
     */
    public FloatTransform toTransform() {
        return new FloatTransform(2.0f * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW))), 2.0f * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW))), 2.0f * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW))), this.rX, this.rY, this.rZ, this.rW, 1.0f, 1.0f, 1.0f);
    }


    /**
     * Determine whether the real (rotation) part of this dual quaternion has unit length.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param epsilon the maximum allowed deviation of the squared real-part length from {@code 1}
     * @return {@code true} if the real (rotation) part of this dual quaternion has unit length,
     *        {@code false} otherwise
     */
    public boolean isUnit(float epsilon) {
        return java.lang.Math.abs(Math.fma(this.rX, this.rX, Math.fma(this.rY, this.rY, Math.fma(this.rZ, this.rZ, Math.fma(this.rW, this.rW, -1.0f))))) <= epsilon;
    }


    /**
     * Create the rotation of {@code angle} radians about the axis {@code axis}, combined with a
     * translation by {@code translation}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param axis the rotation axis
     * @param angle the angle in radians
     * @param translation the translation
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeFromAxisAngle(Float3 axis, float angle, Float3 translation) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        float _t0 = 0.5f * angle;
        float _t1 = -translationZ;
        float _t2 = Math.sin(_t0);
        float _t3 = axis.x() * _t2;
        float _t4 = axis.y() * _t2;
        float _t5 = axis.z() * _t2;
        float _t6 = Math.cosFromSin(_t2, _t0);
        return new FloatDualQuat(_t3, _t4, _t5, _t6, 0.5f * Math.fma(_t1, _t4, Math.fma(translationX, _t6, translationY * _t5)), 0.5f * Math.fma(translationZ, _t3, Math.fma(translationY, _t6, -(translationX * _t5))), 0.5f * Math.fma(translationZ, _t6, Math.fma(translationX, _t4, -(translationY * _t3))), 0.5f * Math.fma(_t1, _t5, Math.fma(-translationY, _t4, -(translationX * _t3))));
    }


    /**
     * Create the rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}), combined with a translation by ({@code translationX}, {@code translationY},
     * {@code translationZ}).
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param angle the angle in radians
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeFromAxisAngle(float axisX, float axisY, float axisZ, float angle, float translationX, float translationY, float translationZ) {
        float _t0 = 0.5f * angle;
        float _t1 = -translationZ;
        float _t2 = Math.sin(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisY * _t2;
        float _t5 = axisZ * _t2;
        float _t6 = Math.cosFromSin(_t2, _t0);
        return new FloatDualQuat(_t3, _t4, _t5, _t6, 0.5f * Math.fma(_t1, _t4, Math.fma(translationX, _t6, translationY * _t5)), 0.5f * Math.fma(translationZ, _t3, Math.fma(translationY, _t6, -(translationX * _t5))), 0.5f * Math.fma(translationZ, _t6, Math.fma(translationX, _t4, -(translationY * _t3))), 0.5f * Math.fma(_t1, _t5, Math.fma(-translationY, _t4, -(translationX * _t3))));
    }


    /**
     * Create an identity dual quaternion.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeIdentity() {
        return new FloatDualQuat(0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create a rigid transformation that first rotates by {@code rotation} and then translates by
     * {@code translation} ({@code T * R}).
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param translation the translation
     * @param rotation the rotation
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeTranslationRotation(Float3 translation, FloatQuat rotation) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        float _t0 = -rotationY;
        return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * Math.fma(_t0, translationZ, Math.fma(rotationZ, translationY, rotationW * translationX)), 0.5f * Math.fma(rotationX, translationZ, Math.fma(rotationW, translationY, -(rotationZ * translationX))), 0.5f * Math.fma(rotationW, translationZ, Math.fma(rotationY, translationX, -(rotationX * translationY))), 0.5f * Math.fma(-rotationZ, translationZ, Math.fma(_t0, translationY, -(rotationX * translationX))));
    }


    /**
     * Create a rigid transformation that first rotates by ({@code rotationX}, {@code rotationY},
     * {@code rotationZ}, {@code rotationW}) and then translates by ({@code translationX},
     * {@code translationY}, {@code translationZ}) ({@code T * R}).
     * <p>
     * Valid input: {@code (rotationX, rotationY, rotationZ, rotationW)} must have unit length.
     *
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param rotationX the {@code x} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationY the {@code y} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationZ the {@code z} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationW the {@code w} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeTranslationRotation(float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW) {
        float _t0 = -rotationY;
        return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * Math.fma(_t0, translationZ, Math.fma(rotationZ, translationY, rotationW * translationX)), 0.5f * Math.fma(rotationX, translationZ, Math.fma(rotationW, translationY, -(rotationZ * translationX))), 0.5f * Math.fma(rotationW, translationZ, Math.fma(rotationY, translationX, -(rotationX * translationY))), 0.5f * Math.fma(-rotationZ, translationZ, Math.fma(_t0, translationY, -(rotationX * translationX))));
    }


    /**
     * Create an all-zero dual quaternion.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeZero() {
        return FloatDualQuat.ZERO;
    }


    /**
     * Create a new dual quaternion representing a pure rotation by {@code rotation} (zero
     * translation).
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @return the resulting dual quaternion
     */
    public FloatDualQuat set(FloatQuat rotation) {
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create a new dual quaternion representing a pure rotation by ({@code rotationX},
     * {@code rotationY}, {@code rotationZ}, {@code rotationW}) (zero translation).
     * <p>
     * Valid input: {@code (rotationX, rotationY, rotationZ, rotationW)} must have unit length.
     *
     * @param rotationX the {@code x} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationY the {@code y} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationZ the {@code z} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationW the {@code w} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat set(float rotationX, float rotationY, float rotationZ, float rotationW) {
        return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create a new dual quaternion representing a pure rotation by {@code rotation} (zero
     * translation).
     * <p>
     * Alias for {@code set}.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotation(FloatQuat rotation) {
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create a new dual quaternion representing a pure rotation by ({@code rotationX},
     * {@code rotationY}, {@code rotationZ}, {@code rotationW}) (zero translation).
     * <p>
     * Alias for {@code set}.
     * <p>
     * Valid input: {@code (rotationX, rotationY, rotationZ, rotationW)} must have unit length.
     *
     * @param rotationX the {@code x} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationY the {@code y} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationZ the {@code z} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationW the {@code w} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotation(float rotationX, float rotationY, float rotationZ, float rotationW) {
        return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create a new dual quaternion from the given values.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @param translation the translation
     * @return the resulting dual quaternion
     */
    public FloatDualQuat set(FloatQuat rotation, Float3 translation) {
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        float _t0 = -rotationY;
        return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * Math.fma(_t0, translationZ, Math.fma(rotationZ, translationY, rotationW * translationX)), 0.5f * Math.fma(rotationX, translationZ, Math.fma(rotationW, translationY, -(rotationZ * translationX))), 0.5f * Math.fma(rotationW, translationZ, Math.fma(rotationY, translationX, -(rotationX * translationY))), 0.5f * Math.fma(-rotationZ, translationZ, Math.fma(_t0, translationY, -(rotationX * translationX))));
    }


    /**
     * Create a new dual quaternion from the given values.
     * <p>
     * Valid input: {@code (rotationX, rotationY, rotationZ, rotationW)} must have unit length.
     *
     * @param rotationX the {@code x} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationY the {@code y} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationZ the {@code z} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationW the {@code w} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat set(float rotationX, float rotationY, float rotationZ, float rotationW, float translationX, float translationY, float translationZ) {
        float _t0 = -rotationY;
        return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * Math.fma(_t0, translationZ, Math.fma(rotationZ, translationY, rotationW * translationX)), 0.5f * Math.fma(rotationX, translationZ, Math.fma(rotationW, translationY, -(rotationZ * translationX))), 0.5f * Math.fma(rotationW, translationZ, Math.fma(rotationY, translationX, -(rotationX * translationY))), 0.5f * Math.fma(-rotationZ, translationZ, Math.fma(_t0, translationY, -(rotationX * translationX))));
    }


    /**
     * Create a new dual quaternion representing a pure translation by {@code translation} (identity
     * rotation).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation
     * @return the resulting dual quaternion
     */
    public FloatDualQuat set(Float3 translation) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        return new FloatDualQuat(0.0f, 0.0f, 0.0f, 1.0f, 0.5f * translationX, 0.5f * translationY, 0.5f * translationZ, 0.0f);
    }


    /**
     * Create a new dual quaternion representing a pure translation by ({@code translationX},
     * {@code translationY}, {@code translationZ}) (identity rotation).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat set(float translationX, float translationY, float translationZ) {
        return new FloatDualQuat(0.0f, 0.0f, 0.0f, 1.0f, 0.5f * translationX, 0.5f * translationY, 0.5f * translationZ, 0.0f);
    }


    /**
     * Create a new dual quaternion representing a pure translation by {@code translation} (identity
     * rotation).
     * <p>
     * Alias for {@code set}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeTranslation(Float3 translation) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        return new FloatDualQuat(0.0f, 0.0f, 0.0f, 1.0f, 0.5f * translationX, 0.5f * translationY, 0.5f * translationZ, 0.0f);
    }


    /**
     * Create a new dual quaternion representing a pure translation by ({@code translationX},
     * {@code translationY}, {@code translationZ}) (identity rotation).
     * <p>
     * Alias for {@code set}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeTranslation(float translationX, float translationY, float translationZ) {
        return new FloatDualQuat(0.0f, 0.0f, 0.0f, 1.0f, 0.5f * translationX, 0.5f * translationY, 0.5f * translationZ, 0.0f);
    }


    /**
     * Blend this dual quaternion with {@code other} using dual-quaternion linear blending with the
     * weight {@code t}, returning the result as a value.
     * <p>
     * The interpolation starts at this dual quaternion (weight {@code 0}) and ends at {@code other}
     * (weight {@code 1}).
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code other} must be a
     * unit dual quaternion; {@code t} must lie in {@code [0, 1]}.
     *
     * @param other the dual quaternion to blend towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat dlb(FloatDualQuat other, float t) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        float _t9 = Math.fma(otherRX, this.rX, otherRY * this.rY) + Math.fma(otherRZ, this.rZ, otherRW * this.rW) < 0.0f ? -1.0f : 1.0f;
        return dlb_s554612b4_tail(Math.fma(t, Math.fma(otherRX, _t9, -this.rX), this.rX), Math.fma(t, Math.fma(otherRY, _t9, -this.rY), this.rY), Math.fma(t, Math.fma(otherRZ, _t9, -this.rZ), this.rZ), Math.fma(t, Math.fma(otherRW, _t9, -this.rW), this.rW), t, otherDX, _t9, otherDY, otherDZ, otherDW);
    }

    /** Private tail of {@code dlb}; reached only through it. */
    private FloatDualQuat dlb_s554612b4_tail(float _t14, float _t15, float _t16, float _t17, float t, float otherDX, float _t9, float otherDY, float otherDZ, float otherDW) {
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t14, _t14, _t15 * _t15) + Math.fma(_t16, _t16, _t17 * _t17)));
        return new FloatDualQuat(_t14 * _t23, _t15 * _t23, _t16 * _t23, _t17 * _t23, Math.fma(t, Math.fma(otherDX, _t9, -this.dX), this.dX) * _t23, Math.fma(t, Math.fma(otherDY, _t9, -this.dY), this.dY) * _t23, Math.fma(t, Math.fma(otherDZ, _t9, -this.dZ), this.dZ) * _t23, Math.fma(t, Math.fma(otherDW, _t9, -this.dW), this.dW) * _t23);
    }


    /**
     * Blend this dual quaternion with ({@code otherRX}, {@code otherRY}, {@code otherRZ},
     * {@code otherRW}, {@code otherDX}, {@code otherDY}, {@code otherDZ}, {@code otherDW}) using
     * dual-quaternion linear blending with the weight {@code t}, returning the result as a value.
     * <p>
     * The interpolation starts at this dual quaternion (weight {@code 0}) and ends at
     * ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherDX},
     * {@code otherDY}, {@code otherDZ}, {@code otherDW}) (weight {@code 1}).
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion;
     * {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)} must be a
     * unit dual quaternion; {@code t} must lie in {@code [0, 1]}.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat dlb(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t) {
        float _t9 = Math.fma(otherRX, this.rX, otherRY * this.rY) + Math.fma(otherRZ, this.rZ, otherRW * this.rW) < 0.0f ? -1.0f : 1.0f;
        return dlb_s554612b4_tail(Math.fma(t, Math.fma(otherRX, _t9, -this.rX), this.rX), Math.fma(t, Math.fma(otherRY, _t9, -this.rY), this.rY), Math.fma(t, Math.fma(otherRZ, _t9, -this.rZ), this.rZ), Math.fma(t, Math.fma(otherRW, _t9, -this.rW), this.rW), t, otherDX, _t9, otherDY, otherDZ, otherDW);
    }


    /**
     * Linearly interpolate between this dual quaternion and {@code other} using the interpolation
     * factor {@code t}, returning the result as a value.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat lerp(FloatDualQuat other, float t) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        return new FloatDualQuat(Math.fma(t, otherRX - this.rX, this.rX), Math.fma(t, otherRY - this.rY, this.rY), Math.fma(t, otherRZ - this.rZ, this.rZ), Math.fma(t, otherRW - this.rW, this.rW), Math.fma(t, otherDX - this.dX, this.dX), Math.fma(t, otherDY - this.dY, this.dY), Math.fma(t, otherDZ - this.dZ, this.dZ), Math.fma(t, otherDW - this.dW, this.dW));
    }


    /**
     * Linearly interpolate between this dual quaternion and ({@code otherRX}, {@code otherRY},
     * {@code otherRZ}, {@code otherRW}, {@code otherDX}, {@code otherDY}, {@code otherDZ},
     * {@code otherDW}) using the interpolation factor {@code t}, returning the result as a value.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherDX},
     * {@code otherDY}, {@code otherDZ}, {@code otherDW}) (interpolation factor {@code 1}). Each
     * linearly interpolated component is {@code this + (other - this) * t}, as in JOML and
     * glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the
     * rounding of {@code other - this}, which shows when this component is much larger in magnitude
     * than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat lerp(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t) {
        return new FloatDualQuat(Math.fma(t, otherRX - this.rX, this.rX), Math.fma(t, otherRY - this.rY, this.rY), Math.fma(t, otherRZ - this.rZ, this.rZ), Math.fma(t, otherRW - this.rW, this.rW), Math.fma(t, otherDX - this.dX, this.dX), Math.fma(t, otherDY - this.dY, this.dY), Math.fma(t, otherDZ - this.dZ, this.dZ), Math.fma(t, otherDW - this.dW, this.dW));
    }


    /**
     * Screw-linearly interpolate between this dual quaternion and {@code other} using the
     * interpolation factor {@code t}, returning the result as a value.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code other} must be a unit dual quaternion; this dual quaternion must be a
     * unit dual quaternion.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat sclerp(FloatDualQuat other, float t) {
        return sclerp(other.rX(), other.rY(), other.rZ(), other.rW(), other.dX(), other.dY(), other.dZ(), other.dW(), t);
    }


    /**
     * Screw-linearly interpolate between this dual quaternion and ({@code otherRX},
     * {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherDX}, {@code otherDY},
     * {@code otherDZ}, {@code otherDW}) using the interpolation factor {@code t}, returning the
     * result as a value.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherDX},
     * {@code otherDY}, {@code otherDZ}, {@code otherDW}) (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * must be a unit dual quaternion; this dual quaternion must be a unit dual quaternion.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat sclerp(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t) {
        float _t0 = -this.rZ;
        float _t8 = Math.fma(otherRX, this.rX, otherRY * this.rY) + Math.fma(otherRZ, this.rZ, otherRW * this.rW) < 0.0f ? -1.0f : 1.0f;
        float _t9 = otherRX * _t8;
        float _t10 = otherRW * _t8;
        float _t11 = otherRZ * _t8;
        float _t12 = otherRY * _t8;
        float _t57 = Math.fma(this.rX, _t9, this.rW * _t10);
        float _t77 = Math.fma(_t0, _t11, -(this.rY * _t12));
        return sclerp_s2fc1b0dc_5(t, _t0, _t9, _t10, _t11, _t12, otherDX * _t8, otherDW * _t8, otherDY * _t8, otherDZ * _t8, _t57, _t77, _t57 - _t77, Math.fma(this.rY, _t9, -(this.rZ * _t10)) + Math.fma(this.rW, _t11, -(this.rX * _t12)), Math.fma(this.rX, _t11, this.rW * _t12) + Math.fma(_t0, _t9, -(this.rY * _t10)), Math.fma(this.rZ, _t12, -(this.rY * _t11)) + Math.fma(this.rW, _t9, -(this.rX * _t10)));
    }

    /** Part 1 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private float[] sclerp_s2fc1b0dc_1(float t, float _t106, float _t107, float _t108, float _t105, float _t112) {
        float _t122 = Math.fma(_t106, _t106, Math.fma(_t107, _t107, _t108 * _t108));
        float _t124 = (1.0f / (float) java.lang.Math.sqrt(_t122));
        float _t126 = _t124 * _t108;
        float _t127 = _t124 * _t106;
        float _t128 = _t124 * _t107;
        float _t129 = t * Math.atan2((float) java.lang.Math.sqrt(_t122), _t105);
        float _t130 = Math.sin(_t129);
        float _t131 = _t124 * _t112;
        float _t137 = Math.cosFromSin(_t130, _t129);
        float _t142, _t143, _t144, _t145, _t147;
        if (_t122 < 1.0E-12f) {
            _t142 = 1.0f;
            _t143 = t * _t108;
            _t144 = t * _t106;
            _t145 = t * _t107;
            _t147 = t * t * _t112;
        } else {
            _t142 = _t137;
            _t143 = _t126 * _t130;
            _t144 = _t127 * _t130;
            _t145 = _t128 * _t130;
            _t147 = (t * _t131) * _t130;
        }
        return new float[] {_t122, _t126, _t127, _t128, _t129, _t131, _t137, _t142, _t143, _t144, _t145, _t147};
    }

    /** Part 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private float[] sclerp_s2fc1b0dc_2(float t, float _t114, float _t115, float _t116, float _t105, float[] _bundle0) {
        float _t122 = _bundle0[0];
        float _t126 = _bundle0[1];
        float _t127 = _bundle0[2];
        float _t128 = _bundle0[3];
        float _t131 = _bundle0[5];
        float _t124 = (1.0f / (float) java.lang.Math.sqrt(_t122));
        float _t130 = Math.sin(_bundle0[4]);
        float _t133 = _t131 * _t105;
        float _t146 = (t * _t131) * _bundle0[6];
        float _t160, _t161, _t162;
        if (_t122 < 1.0E-12f) {
            _t160 = t * _t114;
            _t161 = t * _t115;
            _t162 = t * _t116;
        } else {
            _t160 = Math.fma(Math.fma(_t133, _t126, _t114) * _t124, _t130, -(_t146 * _t126));
            _t161 = Math.fma(Math.fma(_t133, _t127, _t115) * _t124, _t130, -(_t146 * _t127));
            _t162 = Math.fma(Math.fma(_t133, _t128, _t116) * _t124, _t130, -(_t146 * _t128));
        }
        return new float[] {_t160, _t161, _t162};
    }

    /** Part 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private float[] sclerp_s2fc1b0dc_3(float[] _bundle0, float[] _bundle1) {
        float _t142 = _bundle0[7];
        float _t143 = _bundle0[8];
        float _t144 = _bundle0[9];
        float _t145 = _bundle0[10];
        return new float[] {Math.fma(this.rX, _t142, this.rW * _t143) + Math.fma(this.rY, _t144, -(this.rZ * _t145)), Math.fma(this.rY, _t142, this.rZ * _t143) + Math.fma(this.rW, _t145, -(this.rX * _t144)), Math.fma(this.rX, _t145, this.rW * _t144) + Math.fma(this.rZ, _t142, -(this.rY * _t143)), Math.fma(this.rW, _t142, -(this.rX * _t143)) - Math.fma(this.rY, _t145, this.rZ * _t144), Math.fma(this.rX, _bundle0[11], this.rW * _bundle1[0]) + Math.fma(this.rY, _bundle1[1], -(this.rZ * _bundle1[2])) + (Math.fma(this.dX, _t142, this.dW * _t143) + Math.fma(this.dY, _t144, -(this.dZ * _t145)))};
    }

    /** Part 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_s2fc1b0dc_4(float[] _bundle0, float[] _bundle1, float[] _bundle2) {
        float _t147 = _bundle0[11];
        float _t142 = _bundle0[7];
        float _t143 = _bundle0[8];
        float _t145 = _bundle0[10];
        float _t144 = _bundle0[9];
        float _t160 = _bundle1[0];
        float _t162 = _bundle1[2];
        float _t161 = _bundle1[1];
        float _sfx5 = Math.fma(this.rY, _t147, this.rZ * _t160) + Math.fma(this.rW, _t162, -(this.rX * _t161)) + (Math.fma(this.dY, _t142, this.dZ * _t143) + Math.fma(this.dW, _t145, -(this.dX * _t144)));
        float _sfx6 = Math.fma(this.rX, _t162, this.rW * _t161) + Math.fma(this.rZ, _t147, -(this.rY * _t160)) + (Math.fma(this.dX, _t145, this.dW * _t144) + Math.fma(this.dZ, _t142, -(this.dY * _t143)));
        float _sfx7 = Math.fma(this.rW, _t147, -(this.rX * _t160)) + Math.fma((-this.rZ), _t161, -(this.rY * _t162)) + (Math.fma(this.dW, _t142, -(this.dX * _t143)) + Math.fma((-this.dZ), _t144, -(this.dY * _t145)));
        return new FloatDualQuat((_bundle2[0]), (_bundle2[1]), (_bundle2[2]), (_bundle2[3]), (_bundle2[4]), (_sfx5), (_sfx6), (_sfx7));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_s2fc1b0dc_5(float t, float _t0, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86) {
        return sclerp_s2fc1b0dc_6(t, _t57, _t77, _t83, _t84, _t85, _t86, Math.fma(this.rX, _t13, this.rW * _t14) + Math.fma(this.rY, _t15, this.rZ * _t16) + (Math.fma(this.dX, _t9, this.dW * _t10) + Math.fma(this.dY, _t12, this.dZ * _t11)), Math.fma(this.rZ, _t15, -(this.rY * _t16)) + Math.fma(this.rW, _t13, -(this.rX * _t14)) + (Math.fma(this.dZ, _t12, -(this.dY * _t11)) + Math.fma(this.dW, _t9, -(this.dX * _t10))), Math.fma(this.rY, _t13, -(this.rZ * _t14)) + Math.fma(this.rW, _t16, -(this.rX * _t15)) + (Math.fma(this.dY, _t9, -(this.dZ * _t10)) + Math.fma(this.dW, _t11, -(this.dX * _t12))), Math.fma(this.rX, _t16, this.rW * _t15) + Math.fma(_t0, _t13, -(this.rY * _t14)) + (Math.fma(this.dX, _t11, this.dW * _t12) + Math.fma((-this.dZ), _t9, -(this.dY * _t10))));
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_s2fc1b0dc_6(float t, float _t57, float _t77, float _t83, float _t84, float _t85, float _t86, float _t97, float _t99, float _t100, float _t101) {
        float _t105, _t106, _t107, _t108, _t112, _t114, _t115, _t116;
        if (_t83 < 0.0f) {
            _t105 = _t77 - _t57;
            _t106 = -_t84;
            _t107 = -_t85;
            _t108 = -_t86;
            _t112 = -_t97;
            _t114 = -_t99;
            _t115 = -_t100;
            _t116 = -_t101;
        } else {
            _t105 = _t83;
            _t106 = _t84;
            _t107 = _t85;
            _t108 = _t86;
            _t112 = _t97;
            _t114 = _t99;
            _t115 = _t100;
            _t116 = _t101;
        }
        float[] _bundle0 = sclerp_s2fc1b0dc_1(t, _t106, _t107, _t108, _t105, _t112);
        float[] _bundle1 = sclerp_s2fc1b0dc_2(t, _t114, _t115, _t116, _t105, _bundle0);
        float[] _bundle2 = sclerp_s2fc1b0dc_3(_bundle0, _bundle1);
        return sclerp_s2fc1b0dc_4(_bundle0, _bundle1, _bundle2);
    }


    /**
     * Multiply this dual quaternion by {@code other}, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code Q * R * v}, the transformation of the operand will be applied
     * first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand
     * @return the resulting dual quaternion
     */
    public FloatDualQuat mul(FloatDualQuat other) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        float _sfx0 = Math.fma(otherRX, this.rW, otherRW * this.rX) + Math.fma(otherRZ, this.rY, -(otherRY * this.rZ));
        float _sfx1 = Math.fma(otherRX, this.rZ, otherRW * this.rY) + Math.fma(otherRY, this.rW, -(otherRZ * this.rX));
        float _sfx2 = Math.fma(otherRY, this.rX, otherRZ * this.rW) + Math.fma(otherRW, this.rZ, -(otherRX * this.rY));
        float _sfx3 = Math.fma(otherRW, this.rW, -(otherRX * this.rX)) - Math.fma(otherRY, this.rY, otherRZ * this.rZ);
        float _sfx4 = Math.fma(otherRX, this.dW, otherRW * this.dX) + Math.fma(otherRZ, this.dY, -(otherRY * this.dZ)) + (Math.fma(otherDX, this.rW, otherDW * this.rX) + Math.fma(otherDZ, this.rY, -(otherDY * this.rZ)));
        return mul_s401e608_tail(otherRX, otherRW, otherRY, otherRZ, otherDX, otherDW, otherDY, otherDZ, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }

    /** Private tail of {@code mul}; reached only through it. */
    private FloatDualQuat mul_s401e608_tail(float otherRX, float otherRW, float otherRY, float otherRZ, float otherDX, float otherDW, float otherDY, float otherDZ, float _sfx0, float _sfx1, float _sfx2, float _sfx3, float _sfx4) {
        float _sfx5 = Math.fma(otherRX, this.dZ, otherRW * this.dY) + Math.fma(otherRY, this.dW, -(otherRZ * this.dX)) + (Math.fma(otherDX, this.rZ, otherDW * this.rY) + Math.fma(otherDY, this.rW, -(otherDZ * this.rX)));
        float _sfx6 = Math.fma(otherRY, this.dX, otherRZ * this.dW) + Math.fma(otherRW, this.dZ, -(otherRX * this.dY)) + (Math.fma(otherDY, this.rX, otherDZ * this.rW) + Math.fma(otherDW, this.rZ, -(otherDX * this.rY)));
        float _sfx7 = Math.fma(otherRW, this.dW, -(otherRX * this.dX)) + Math.fma(-otherRZ, this.dZ, -(otherRY * this.dY)) + (Math.fma(otherDW, this.rW, -(otherDX * this.rX)) + Math.fma(-otherDZ, this.rZ, -(otherDY * this.rY)));
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }


    /**
     * Multiply this dual quaternion by ({@code otherRX}, {@code otherRY}, {@code otherRZ},
     * {@code otherRW}, {@code otherDX}, {@code otherDY}, {@code otherDZ}, {@code otherDW}),
     * returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code Q * R * v}, the transformation of the operand will be applied
     * first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat mul(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW) {
        float _sfx0 = Math.fma(otherRX, this.rW, otherRW * this.rX) + Math.fma(otherRZ, this.rY, -(otherRY * this.rZ));
        float _sfx1 = Math.fma(otherRX, this.rZ, otherRW * this.rY) + Math.fma(otherRY, this.rW, -(otherRZ * this.rX));
        float _sfx2 = Math.fma(otherRY, this.rX, otherRZ * this.rW) + Math.fma(otherRW, this.rZ, -(otherRX * this.rY));
        float _sfx3 = Math.fma(otherRW, this.rW, -(otherRX * this.rX)) - Math.fma(otherRY, this.rY, otherRZ * this.rZ);
        float _sfx4 = Math.fma(otherRX, this.dW, otherRW * this.dX) + Math.fma(otherRZ, this.dY, -(otherRY * this.dZ)) + (Math.fma(otherDX, this.rW, otherDW * this.rX) + Math.fma(otherDZ, this.rY, -(otherDY * this.rZ)));
        return mul_s401e608_tail(otherRX, otherRW, otherRY, otherRZ, otherDX, otherDW, otherDY, otherDZ, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }


    /**
     * Pre-multiply {@code other} onto this dual quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code R * Q * v}, the transformation of the operand will be applied
     * last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @return the resulting dual quaternion
     */
    public FloatDualQuat preMul(FloatDualQuat other) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        float _sfx0 = Math.fma(otherRX, this.rW, otherRW * this.rX) + Math.fma(otherRY, this.rZ, -(otherRZ * this.rY));
        float _sfx1 = Math.fma(otherRY, this.rW, otherRZ * this.rX) + Math.fma(otherRW, this.rY, -(otherRX * this.rZ));
        float _sfx2 = Math.fma(otherRX, this.rY, otherRW * this.rZ) + Math.fma(otherRZ, this.rW, -(otherRY * this.rX));
        float _sfx3 = Math.fma(otherRW, this.rW, -(otherRX * this.rX)) - Math.fma(otherRY, this.rY, otherRZ * this.rZ);
        float _sfx4 = Math.fma(otherRX, this.dW, otherRW * this.dX) + Math.fma(otherRY, this.dZ, -(otherRZ * this.dY)) + (Math.fma(otherDX, this.rW, otherDW * this.rX) + Math.fma(otherDY, this.rZ, -(otherDZ * this.rY)));
        return preMul_s401e608_tail(otherRY, otherRZ, otherRW, otherRX, otherDY, otherDZ, otherDW, otherDX, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private FloatDualQuat preMul_s401e608_tail(float otherRY, float otherRZ, float otherRW, float otherRX, float otherDY, float otherDZ, float otherDW, float otherDX, float _sfx0, float _sfx1, float _sfx2, float _sfx3, float _sfx4) {
        float _sfx5 = Math.fma(otherRY, this.dW, otherRZ * this.dX) + Math.fma(otherRW, this.dY, -(otherRX * this.dZ)) + (Math.fma(otherDY, this.rW, otherDZ * this.rX) + Math.fma(otherDW, this.rY, -(otherDX * this.rZ)));
        float _sfx6 = Math.fma(otherRX, this.dY, otherRW * this.dZ) + Math.fma(otherRZ, this.dW, -(otherRY * this.dX)) + (Math.fma(otherDX, this.rY, otherDW * this.rZ) + Math.fma(otherDZ, this.rW, -(otherDY * this.rX)));
        float _sfx7 = Math.fma(otherRW, this.dW, -(otherRX * this.dX)) + Math.fma(-otherRZ, this.dZ, -(otherRY * this.dY)) + (Math.fma(otherDW, this.rW, -(otherDX * this.rX)) + Math.fma(-otherDZ, this.rZ, -(otherDY * this.rY)));
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }


    /**
     * Pre-multiply ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW},
     * {@code otherDX}, {@code otherDY}, {@code otherDZ}, {@code otherDW}) onto this dual
     * quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code R * Q * v}, the transformation of the operand will be applied
     * last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat preMul(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW) {
        float _sfx0 = Math.fma(otherRX, this.rW, otherRW * this.rX) + Math.fma(otherRY, this.rZ, -(otherRZ * this.rY));
        float _sfx1 = Math.fma(otherRY, this.rW, otherRZ * this.rX) + Math.fma(otherRW, this.rY, -(otherRX * this.rZ));
        float _sfx2 = Math.fma(otherRX, this.rY, otherRW * this.rZ) + Math.fma(otherRZ, this.rW, -(otherRY * this.rX));
        float _sfx3 = Math.fma(otherRW, this.rW, -(otherRX * this.rX)) - Math.fma(otherRY, this.rY, otherRZ * this.rZ);
        float _sfx4 = Math.fma(otherRX, this.dW, otherRW * this.dX) + Math.fma(otherRY, this.dZ, -(otherRZ * this.dY)) + (Math.fma(otherDX, this.rW, otherDW * this.rX) + Math.fma(otherDY, this.rZ, -(otherDZ * this.rY)));
        return preMul_s401e608_tail(otherRY, otherRZ, otherRW, otherRX, otherDY, otherDZ, otherDW, otherDX, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }


    /**
     * Add {@code other} scaled by {@code weight} to this dual quaternion, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @return the resulting dual quaternion
     */
    public FloatDualQuat addScaled(FloatDualQuat other, float weight) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        return new FloatDualQuat(Math.fma(weight, otherRX, this.rX), Math.fma(weight, otherRY, this.rY), Math.fma(weight, otherRZ, this.rZ), Math.fma(weight, otherRW, this.rW), Math.fma(weight, otherDX, this.dX), Math.fma(weight, otherDY, this.dY), Math.fma(weight, otherDZ, this.dZ), Math.fma(weight, otherDW, this.dW));
    }


    /**
     * Add ({@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherDX},
     * {@code otherDY}, {@code otherDZ}, {@code otherDW}) scaled by {@code weight} to this dual
     * quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param weight the factor to scale ({@code otherRX}, {@code otherRY}, {@code otherRZ},
     *        {@code otherRW}, {@code otherDX}, {@code otherDY}, {@code otherDZ}, {@code otherDW})
     *        by before adding
     * @return the resulting dual quaternion
     */
    public FloatDualQuat addScaled(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float weight) {
        return new FloatDualQuat(Math.fma(weight, otherRX, this.rX), Math.fma(weight, otherRY, this.rY), Math.fma(weight, otherRZ, this.rZ), Math.fma(weight, otherRW, this.rW), Math.fma(weight, otherDX, this.dX), Math.fma(weight, otherDY, this.dY), Math.fma(weight, otherDZ, this.dZ), Math.fma(weight, otherDW, this.dW));
    }


    /**
     * Compute the quaternion conjugate of this dual quaternion, conjugating both the real and the
     * dual part (for a unit dual quaternion this is its inverse), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting dual quaternion
     */
    public FloatDualQuat conjugate() {
        return new FloatDualQuat(-this.rX, -this.rY, -this.rZ, this.rW, -this.dX, -this.dY, -this.dZ, this.dW);
    }


    /**
     * Compute the difference between this dual quaternion and {@code other}, i.e. the rigid
     * transformation {@code D} with {@code this * D = other}, that is {@code D = this^-1 * other},
     * returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param other the target dual quaternion, reached by composing this dual quaternion with the
     *        result
     * @return the resulting dual quaternion
     */
    public FloatDualQuat difference(FloatDualQuat other) {
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float otherDX = other.dX();
        float otherDY = other.dY();
        float otherDZ = other.dZ();
        float otherDW = other.dW();
        float _t0 = -otherRX;
        float _sfx0 = Math.fma(otherRX, this.rW, -(otherRW * this.rX)) + Math.fma(otherRY, this.rZ, -(otherRZ * this.rY));
        float _sfx1 = Math.fma(otherRY, this.rW, otherRZ * this.rX) + Math.fma(_t0, this.rZ, -(otherRW * this.rY));
        float _sfx2 = Math.fma(otherRX, this.rY, -(otherRW * this.rZ)) + Math.fma(otherRZ, this.rW, -(otherRY * this.rX));
        float _sfx3 = Math.fma(otherRX, this.rX, otherRW * this.rW) - Math.fma(-otherRZ, this.rZ, -(otherRY * this.rY));
        float _sfx4 = Math.fma(otherRX, this.dW, -(otherRW * this.dX)) + Math.fma(otherRY, this.dZ, -(otherRZ * this.dY)) + (Math.fma(otherDX, this.rW, -(otherDW * this.rX)) + Math.fma(otherDY, this.rZ, -(otherDZ * this.rY)));
        return difference_s401e608_tail(otherRY, otherRZ, _t0, otherRW, otherDY, otherDZ, otherDX, otherDW, otherRX, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private FloatDualQuat difference_s401e608_tail(float otherRY, float otherRZ, float _t0, float otherRW, float otherDY, float otherDZ, float otherDX, float otherDW, float otherRX, float _sfx0, float _sfx1, float _sfx2, float _sfx3, float _sfx4) {
        float _sfx5 = Math.fma(otherRY, this.dW, otherRZ * this.dX) + Math.fma(_t0, this.dZ, -(otherRW * this.dY)) + (Math.fma(otherDY, this.rW, otherDZ * this.rX) + Math.fma(-otherDX, this.rZ, -(otherDW * this.rY)));
        float _sfx6 = Math.fma(otherRX, this.dY, -(otherRW * this.dZ)) + Math.fma(otherRZ, this.dW, -(otherRY * this.dX)) + (Math.fma(otherDX, this.rY, -(otherDW * this.rZ)) + Math.fma(otherDZ, this.rW, -(otherDY * this.rX)));
        float _sfx7 = Math.fma(otherRX, this.dX, otherRW * this.dW) + Math.fma(otherRY, this.dY, otherRZ * this.dZ) + (Math.fma(otherDX, this.rX, otherDW * this.rW) + Math.fma(otherDY, this.rY, otherDZ * this.rZ));
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }


    /**
     * Compute the difference between this dual quaternion and ({@code otherRX}, {@code otherRY},
     * {@code otherRZ}, {@code otherRW}, {@code otherDX}, {@code otherDY}, {@code otherDZ},
     * {@code otherDW}), i.e. the rigid transformation {@code D} with
     * {@code this * D = (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)},
     * that is
     * {@code D = this^-1 * (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)},
     * returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat difference(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW) {
        float _t0 = -otherRX;
        float _sfx0 = Math.fma(otherRX, this.rW, -(otherRW * this.rX)) + Math.fma(otherRY, this.rZ, -(otherRZ * this.rY));
        float _sfx1 = Math.fma(otherRY, this.rW, otherRZ * this.rX) + Math.fma(_t0, this.rZ, -(otherRW * this.rY));
        float _sfx2 = Math.fma(otherRX, this.rY, -(otherRW * this.rZ)) + Math.fma(otherRZ, this.rW, -(otherRY * this.rX));
        float _sfx3 = Math.fma(otherRX, this.rX, otherRW * this.rW) - Math.fma(-otherRZ, this.rZ, -(otherRY * this.rY));
        float _sfx4 = Math.fma(otherRX, this.dW, -(otherRW * this.dX)) + Math.fma(otherRY, this.dZ, -(otherRZ * this.dY)) + (Math.fma(otherDX, this.rW, -(otherDW * this.rX)) + Math.fma(otherDY, this.rZ, -(otherDZ * this.rY)));
        return difference_s401e608_tail(otherRY, otherRZ, _t0, otherRW, otherDY, otherDZ, otherDX, otherDW, otherRX, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }


    /**
     * Compute the dot product of this dual quaternion and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this dual quaternion and {@code other}
     */
    public float dot(FloatDualQuat other) {
        return Math.fma(other.rX(), this.rX, other.rY() * this.rY) + Math.fma(other.rZ(), this.rZ, other.rW() * this.rW) + (Math.fma(other.dX(), this.dX, other.dY() * this.dY) + Math.fma(other.dZ(), this.dZ, other.dW() * this.dW));
    }


    /**
     * Compute the dot product of this dual quaternion and ({@code otherRX}, {@code otherRY},
     * {@code otherRZ}, {@code otherRW}, {@code otherDX}, {@code otherDY}, {@code otherDZ},
     * {@code otherDW}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherRX the {@code rX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRY the {@code rY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRZ the {@code rZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherRW the {@code rW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDX the {@code dX} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDY the {@code dY} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDZ the {@code dZ} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @param otherDW the {@code dW} component of the dual quaternion
     *        {@code (otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW)}
     * @return the dot product of this dual quaternion and ({@code otherRX}, {@code otherRY},
     *        {@code otherRZ}, {@code otherRW}, {@code otherDX}, {@code otherDY}, {@code otherDZ},
     *        {@code otherDW})
     */
    public float dot(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW) {
        return Math.fma(otherRX, this.rX, otherRY * this.rY) + Math.fma(otherRZ, this.rZ, otherRW * this.rW) + (Math.fma(otherDX, this.dX, otherDY * this.dY) + Math.fma(otherDZ, this.dZ, otherDW * this.dW));
    }


    /**
     * Compute the dual-number conjugate of this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting dual quaternion
     */
    public FloatDualQuat dualConjugate() {
        return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, -this.dX, -this.dY, -this.dZ, -this.dW);
    }

    /** Private tail of {@code exp}; reached only through it. */
    private FloatDualQuat exp_s0_tail(float _t12, float _t13, float _t4, float _t9, float _t8, float _t10, float _t11, float _sp0, float _t5) {
        float _t14 = _t12 * _t13;
        if (_t4 < 1.0E-12f) {
            return new FloatDualQuat(this.rX, this.rY, this.rZ, 1.0f, this.dX, this.dY, this.dZ, -_t5);
        } else {
            return new FloatDualQuat(_t9 * _t8, _t10 * _t8, _t11 * _t8, _t13, Math.fma(_t9, _t14, Math.fma(-_t9, _t12, this.dX) * _sp0), Math.fma(_t10, _t14, Math.fma(-_t10, _t12, this.dY) * _sp0), Math.fma(_t11, _t14, Math.fma(-_t11, _t12, this.dZ) * _sp0), -(_t12 * _t8));
        }
    }


    /**
     * Compute the exponential of this dual quaternion, returning the result as a value.
     * <p>
     * This dual quaternion is read as a screw-motion generator, a pure dual quaternion as
     * {@code log} returns it: its scalar parts {@code rW} and {@code dW} are taken as zero and
     * ignored. The result is a unit dual quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting dual quaternion
     */
    public FloatDualQuat exp() {
        float _t4 = Math.fma(this.rZ, this.rZ, Math.fma(this.rX, this.rX, this.rY * this.rY));
        float _t5 = Math.fma(this.rZ, this.dZ, Math.fma(this.rX, this.dX, this.rY * this.dY));
        float _t7 = (float) java.lang.Math.sqrt(_t4);
        float _t6 = 1.0f / _t7;
        float _t8 = Math.sin(_t7);
        return exp_s0_tail(_t5 * _t6, Math.cosFromSin(_t8, _t7), _t4, this.rX * _t6, _t8, this.rY * _t6, this.rZ * _t6, _t6 * _t8, _t5);
    }


    /**
     * Get the dual part of this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting quaternion
     */
    public FloatQuat getDual() {
        return new FloatQuat(this.dX, this.dY, this.dZ, this.dW);
    }


    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the X, Y and Z
     * axes, in that order, returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationXYZ(e.x(), e.y(), e.z())}, which takes the angles in
     * application order, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesXYZ() {
        float _t1 = this.rY * this.rZ;
        float _t3 = this.rZ * this.rZ;
        float _t8 = 2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        float _t9 = 2.0f * Math.fma(this.rX, this.rW, -_t1);
        float _t10 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-7f) {
            return new Float3(Math.atan2(2.0f * Math.fma(this.rX, this.rW, _t1), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t3), 1.0f)), Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12)), 0.0f);
        } else {
            return new Float3(Math.atan2(_t9, _t10), Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12)), Math.atan2(2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t3), 1.0f)));
        }
    }


    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the X, Z and Y
     * axes, in that order, returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationXZY(e.x(), e.z(), e.y())}, which takes the angles in
     * application order, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesXZY() {
        float _t0 = this.rZ * this.rZ;
        float _t1 = this.rY * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rW, _t1);
        float _t8 = 2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            return new Float3(Math.atan2(2.0f * Math.fma(this.rX, this.rW, -_t1), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f)), 0.0f, Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11)));
        } else {
            return new Float3(Math.atan2(_t7, _t9), Math.atan2(2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f)), Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11)));
        }
    }


    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the Y, X and Z
     * axes, in that order, returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationYXZ(e.y(), e.x(), e.z())}, which takes the angles in
     * application order, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesYXZ() {
        float _t3 = this.rZ * this.rZ;
        float _t8 = 2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        float _t9 = 2.0f * Math.fma(this.rX, this.rW, -(this.rY * this.rZ));
        float _t10 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            return new Float3(Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12)), Math.atan2(2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t3), 1.0f)), 0.0f);
        } else {
            return new Float3(Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12)), Math.atan2(_t8, _t10), Math.atan2(2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t3), 1.0f)));
        }
    }


    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the Y, Z and X
     * axes, in that order, returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationYZX(e.y(), e.z(), e.x())}, which takes the angles in
     * application order, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesYZX() {
        float _t0 = this.rZ * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        float _t8 = 2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            return new Float3(0.0f, Math.atan2(2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f)), Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11)));
        } else {
            return new Float3(Math.atan2(2.0f * Math.fma(this.rX, this.rW, -(this.rY * this.rZ)), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f)), Math.atan2(_t8, _t9), Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11)));
        }
    }


    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the Z, X and Y
     * axes, in that order, returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationZXY(e.z(), e.x(), e.y())}, which takes the angles in
     * application order, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesZXY() {
        float _t1 = this.rZ * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        float _t8 = 2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t1), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            return new Float3(Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11)), 0.0f, Math.atan2(2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t1), 1.0f)));
        } else {
            return new Float3(Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11)), Math.atan2(2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f)), Math.atan2(_t8, _t9));
        }
    }


    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the Z, Y and X
     * axes, in that order, returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationZYX(e.z(), e.y(), e.x())}, which takes the angles in
     * application order, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesZYX() {
        float _t0 = this.rZ * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        float _t8 = 2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            return new Float3(0.0f, Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11)), Math.atan2(2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f)));
        } else {
            return new Float3(Math.atan2(2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f)), Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11)), Math.atan2(_t7, _t9));
        }
    }


    /**
     * Get the rotation of this dual quaternion, i.e. its raw real part (a unit quaternion only when
     * this dual quaternion has unit length), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting quaternion
     */
    public FloatQuat getRotation() {
        return new FloatQuat(this.rX, this.rY, this.rZ, this.rW);
    }


    /**
     * Get the translation of this dual quaternion, i.e. {@code 2 * dual * conj(real)} (the actual
     * translation only when this dual quaternion has unit length), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getTranslation() {
        return new Float3(2.0f * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW))), 2.0f * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW))), 2.0f * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW))));
    }


    /**
     * Compute the inverse of this dual quaternion (its conjugate, for a unit dual quaternion),
     * returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the resulting dual quaternion
     */
    public FloatDualQuat inverseUnit() {
        return new FloatDualQuat(-this.rX, -this.rY, -this.rZ, this.rW, -this.dX, -this.dY, -this.dZ, this.dW);
    }


    /**
     * Invert this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: the real part of this dual quaternion must be non-zero.
     *
     * @return the resulting dual quaternion
     */
    public FloatDualQuat invert() {
        float _t8 = Math.fma(this.rX, this.rX, this.rY * this.rY) + Math.fma(this.rZ, this.rZ, this.rW * this.rW);
        float _t8_inv = 1.0f / _t8;
        float _sp0 = 2.0f * (Math.fma(this.rX, this.dX, this.rY * this.dY) + Math.fma(this.rZ, this.dZ, this.rW * this.dW)) / (_t8 * _t8);
        return new FloatDualQuat(-(this.rX * _t8_inv), -(this.rY * _t8_inv), -(this.rZ * _t8_inv), this.rW * _t8_inv, Math.fma(this.rX, _sp0, -(this.dX * _t8_inv)), Math.fma(this.rY, _sp0, -(this.dY * _t8_inv)), Math.fma(this.rZ, _sp0, -(this.dZ * _t8_inv)), Math.fma(this.dW, _t8_inv, -(this.rW * _sp0)));
    }


    /**
     * Compute the length of this dual quaternion's real (rotation) part.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the length of this dual quaternion's real (rotation) part
     */
    public float length() {
        return (float) java.lang.Math.sqrt(Math.fma(this.rX, this.rX, this.rY * this.rY) + Math.fma(this.rZ, this.rZ, this.rW * this.rW));
    }


    /**
     * Compute the squared length of this dual quaternion's real (rotation) part.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this dual quaternion's real (rotation) part
     */
    public float lengthSquared() {
        return Math.fma(this.rX, this.rX, this.rY * this.rY) + Math.fma(this.rZ, this.rZ, this.rW * this.rW);
    }

    /** Private tail of {@code log}; reached only through it. */
    private FloatDualQuat log_s0_tail(float _t19, float _t10, float _t8, float _t11, float _t18, float _t9, float _t21, float _t22, float _t12, float _t14, float _t15) {
        float _t23 = _t19 * _t10;
        float _t24 = _t19 * _t8;
        float _t25 = _t19 * (this.rW < 0.0f ? -this.dW : this.dW);
        float _t26 = _t25 * _t11;
        if (_t18 < 1.0E-12f) {
            return new FloatDualQuat(_t9, _t10, _t8, 0.0f, _t12, _t14, _t15, 0.0f);
        } else {
            return new FloatDualQuat(_t21 * _t22, _t23 * _t22, _t24 * _t22, 0.0f, Math.fma(Math.fma(_t26, _t21, _t12) * _t19, _t22, -(_t21 * _t25)), Math.fma(Math.fma(_t26, _t23, _t14) * _t19, _t22, -(_t23 * _t25)), Math.fma(Math.fma(_t26, _t24, _t15) * _t19, _t22, -(_t24 * _t25)), 0.0f);
        }
    }


    /**
     * Compute the natural logarithm of this dual quaternion, returning the result as a value.
     * <p>
     * This dual quaternion must be a unit dual quaternion; the result is pure (both scalar parts
     * zero), the input {@code exp} expects.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the resulting dual quaternion
     */
    public FloatDualQuat log() {
        float _t8, _t9, _t10, _t11, _t12, _t14, _t15;
        if (this.rW < 0.0f) {
            _t8 = -this.rZ;
            _t9 = -this.rX;
            _t10 = -this.rY;
            _t11 = -this.rW;
            _t12 = -this.dX;
            _t14 = -this.dY;
            _t15 = -this.dZ;
        } else {
            _t8 = this.rZ;
            _t9 = this.rX;
            _t10 = this.rY;
            _t11 = this.rW;
            _t12 = this.dX;
            _t14 = this.dY;
            _t15 = this.dZ;
        }
        float _t18 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        return log_s0_tail(_t19, _t10, _t8, _t11, _t18, _t9, _t19 * _t9, Math.atan2((float) java.lang.Math.sqrt(_t18), _t11), _t12, _t14, _t15);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatDualQuat makeFromMatrix_s37d8ed81_tail(float _t14, float _sp0, float _t4, Float4x4 m, float _t5, float _t16, float _sp1, float _t6, float _sp2, float _t7, float _t8, float _sp3, float _t17, float _t9, float _t10, float _t18, float _t15, float _t0) {
        float _t63, _t64, _t65;
        if (_t14 > 0.0f) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
        } else {
            if (m.m00() > _t5) {
                _t63 = 0.5f * (float) java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
            } else {
                if (m.m11() > m.m22()) {
                    _t63 = _sp1 * _t6;
                    _t64 = 0.5f * (float) java.lang.Math.sqrt(_t17);
                    _t65 = _sp1 * _t9;
                } else {
                    _t63 = _sp2 * _t7;
                    _t64 = _sp2 * _t9;
                    _t65 = 0.5f * (float) java.lang.Math.sqrt(_t18);
                }
            }
        }
        return makeFromMatrix_s37d8ed81_tail2(_t14, _t15, m, _t5, _sp3, _t4, _sp1, _t8, _sp2, _t10, _t63, _t64, _t65, _t0);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatDualQuat makeFromMatrix_s37d8ed81_tail2(float _t14, float _t15, Float4x4 m, float _t5, float _sp3, float _t4, float _sp1, float _t8, float _sp2, float _t10, float _t63, float _t64, float _t65, float _t0) {
        float _t66 = _t14 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t15) : m.m00() > _t5 ? _sp3 * _t4 : m.m11() > m.m22() ? _sp1 * _t8 : _sp2 * _t10;
        float _sfx4 = 0.5f * Math.fma(_t0, _t64, Math.fma(m.m03(), _t66, m.m13() * _t65));
        float _sfx5 = 0.5f * Math.fma(m.m23(), _t63, Math.fma(m.m13(), _t66, -(m.m03() * _t65)));
        float _sfx6 = 0.5f * Math.fma(m.m23(), _t66, Math.fma(m.m03(), _t64, -(m.m13() * _t63)));
        float _sfx7 = 0.5f * Math.fma(_t0, _t65, Math.fma(-m.m13(), _t64, -(m.m03() * _t63)));
        return new FloatDualQuat(_t63, _t64, _t65, _t66, _sfx4, _sfx5, _sfx6, _sfx7);
    }


    /**
     * Create the rigid motion of the given matrix: rotation from its upper-left 3x3 block and
     * translation from its last column.
     * <p>
     * Valid input: {@code m} must be a rotation matrix.
     *
     * @param m the matrix to convert
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeFromMatrix(Float4x4 m) {
        float _t2 = 1.0f - m.m00();
        float _t14 = m.m22() + (m.m00() + m.m11());
        float _t15 = 1.0f + _t14;
        float _t16 = m.m00() + (1.0f - m.m11() - m.m22());
        float _t17 = m.m11() + (_t2 - m.m22());
        float _t18 = m.m22() + (_t2 - m.m11());
        return makeFromMatrix_s37d8ed81_tail(_t14, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), m.m21() - m.m12(), m, java.lang.Math.max(m.m11(), m.m22()), _t16, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), m.m01() + m.m10(), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t18)), m.m02() + m.m20(), m.m02() - m.m20(), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), _t17, m.m12() + m.m21(), m.m10() - m.m01(), _t18, _t15, -m.m23());
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatDualQuat makeFromMatrix_s37cad600_tail(float _t14, float _sp0, float _t4, Float3x4 m, float _t5, float _t16, float _sp1, float _t6, float _sp2, float _t7, float _t8, float _sp3, float _t17, float _t9, float _t10, float _t18, float _t15, float _t0) {
        float _t63, _t64, _t65;
        if (_t14 > 0.0f) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
        } else {
            if (m.m00() > _t5) {
                _t63 = 0.5f * (float) java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
            } else {
                if (m.m11() > m.m22()) {
                    _t63 = _sp1 * _t6;
                    _t64 = 0.5f * (float) java.lang.Math.sqrt(_t17);
                    _t65 = _sp1 * _t9;
                } else {
                    _t63 = _sp2 * _t7;
                    _t64 = _sp2 * _t9;
                    _t65 = 0.5f * (float) java.lang.Math.sqrt(_t18);
                }
            }
        }
        return makeFromMatrix_s37cad600_tail2(_t14, _t15, m, _t5, _sp3, _t4, _sp1, _t8, _sp2, _t10, _t63, _t64, _t65, _t0);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatDualQuat makeFromMatrix_s37cad600_tail2(float _t14, float _t15, Float3x4 m, float _t5, float _sp3, float _t4, float _sp1, float _t8, float _sp2, float _t10, float _t63, float _t64, float _t65, float _t0) {
        float _t66 = _t14 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t15) : m.m00() > _t5 ? _sp3 * _t4 : m.m11() > m.m22() ? _sp1 * _t8 : _sp2 * _t10;
        float _sfx4 = 0.5f * Math.fma(_t0, _t64, Math.fma(m.m03(), _t66, m.m13() * _t65));
        float _sfx5 = 0.5f * Math.fma(m.m23(), _t63, Math.fma(m.m13(), _t66, -(m.m03() * _t65)));
        float _sfx6 = 0.5f * Math.fma(m.m23(), _t66, Math.fma(m.m03(), _t64, -(m.m13() * _t63)));
        float _sfx7 = 0.5f * Math.fma(_t0, _t65, Math.fma(-m.m13(), _t64, -(m.m03() * _t63)));
        return new FloatDualQuat(_t63, _t64, _t65, _t66, _sfx4, _sfx5, _sfx6, _sfx7);
    }


    /**
     * Create the rigid motion of the given matrix: rotation from its upper-left 3x3 block and
     * translation from its last column.
     * <p>
     * Valid input: {@code m} must be a rotation matrix.
     *
     * @param m the matrix to convert
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeFromMatrix(Float3x4 m) {
        float _t2 = 1.0f - m.m00();
        float _t14 = m.m22() + (m.m00() + m.m11());
        float _t15 = 1.0f + _t14;
        float _t16 = m.m00() + (1.0f - m.m11() - m.m22());
        float _t17 = m.m11() + (_t2 - m.m22());
        float _t18 = m.m22() + (_t2 - m.m11());
        return makeFromMatrix_s37cad600_tail(_t14, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), m.m21() - m.m12(), m, java.lang.Math.max(m.m11(), m.m22()), _t16, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), m.m01() + m.m10(), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t18)), m.m02() + m.m20(), m.m02() - m.m20(), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), _t17, m.m12() + m.m21(), m.m10() - m.m01(), _t18, _t15, -m.m23());
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatDualQuat makeFromMatrix_s37cad23f_tail(float _t13, float _sp0, float _t3, Float3x3 m, float _t4, float _t15, float _sp1, float _t5, float _sp2, float _t6, float _t7, float _sp3, float _t16, float _t8, float _t9, float _t17, float _t14) {
        float _sfx0, _sfx1, _sfx2;
        if (_t13 > 0.0f) {
            _sfx0 = _sp0 * _t3;
            _sfx1 = _sp0 * _t7;
            _sfx2 = _sp0 * _t9;
        } else {
            if (m.m00() > _t4) {
                _sfx0 = 0.5f * (float) java.lang.Math.sqrt(_t15);
                _sfx1 = _sp3 * _t5;
                _sfx2 = _sp3 * _t6;
            } else {
                if (m.m11() > m.m22()) {
                    _sfx0 = _sp1 * _t5;
                    _sfx1 = 0.5f * (float) java.lang.Math.sqrt(_t16);
                    _sfx2 = _sp1 * _t8;
                } else {
                    _sfx0 = _sp2 * _t6;
                    _sfx1 = _sp2 * _t8;
                    _sfx2 = 0.5f * (float) java.lang.Math.sqrt(_t17);
                }
            }
        }
        return makeFromMatrix_s37cad23f_tail2(_t13, _t14, m, _t4, _sp3, _t3, _sp1, _t7, _sp2, _t9, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatDualQuat makeFromMatrix_s37cad23f_tail2(float _t13, float _t14, Float3x3 m, float _t4, float _sp3, float _t3, float _sp1, float _t7, float _sp2, float _t9, float _sfx0, float _sfx1, float _sfx2) {
        float _sfx3 = _t13 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t14) : m.m00() > _t4 ? _sp3 * _t3 : m.m11() > m.m22() ? _sp1 * _t7 : _sp2 * _t9;
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create the rotation represented by the given matrix (which must be a rotation: orthonormal,
     * with determinant +1 - a scaled or sheared block gives a wrong quaternion, not a longer one;
     * {@code getNormalizedRotation} strips scale first), with zero translation.
     * <p>
     * Valid input: {@code m} must be a rotation matrix.
     *
     * @param m the matrix to convert
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeFromMatrix(Float3x3 m) {
        float _t1 = 1.0f - m.m00();
        float _t13 = m.m22() + (m.m00() + m.m11());
        float _t14 = 1.0f + _t13;
        float _t15 = m.m00() + (1.0f - m.m11() - m.m22());
        float _t16 = m.m11() + (_t1 - m.m22());
        float _t17 = m.m22() + (_t1 - m.m11());
        return makeFromMatrix_s37cad23f_tail(_t13, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), m.m21() - m.m12(), m, java.lang.Math.max(m.m11(), m.m22()), _t15, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), m.m01() + m.m10(), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), m.m02() + m.m20(), m.m02() - m.m20(), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), _t16, m.m12() + m.m21(), m.m10() - m.m01(), _t17, _t14);
    }


    /**
     * Normalize this dual quaternion so that its real (rotation) part has unit length (a zero real
     * part yields the zero dual quaternion), returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting dual quaternion
     */
    public FloatDualQuat normalize() {
        float _t4 = Math.fma(this.rX, this.rX, this.rY * this.rY) + Math.fma(this.rZ, this.rZ, this.rW * this.rW);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        if (_t4 != 0.0f) {
            return new FloatDualQuat(this.rX * _t5, this.rY * _t5, this.rZ * _t5, this.rW * _t5, this.dX * _t5, this.dY * _t5, this.dZ * _t5, this.dW * _t5);
        } else {
            return FloatDualQuat.ZERO;
        }
    }

    /** Private tail of {@code pow}; reached only through it. */
    private FloatDualQuat pow_s5250ffb0_tail(float _t19, float _t10, float _t8, float _t13, float t, float _t18, float _t11, float _t9, float _t21, float _t12, float _t14, float _t15) {
        float _t25 = _t19 * _t13;
        float _t26 = t * Math.atan2((float) java.lang.Math.sqrt(_t18), _t11);
        float _t27 = t * _t25;
        float _t28 = Math.sin(_t26);
        float _t30 = Math.cosFromSin(_t28, _t26);
        return pow_s5250ffb0_tail2(_t18, t, _t9, _t21, _t28, _t10, _t19 * _t10, _t8, _t19 * _t8, _t30, _t12, _t25 * _t11, _t19, _t27 * _t30, _t14, _t15, _t13, _t27);
    }

    /** Private tail of {@code pow}; reached only through it. */
    private FloatDualQuat pow_s5250ffb0_tail2(float _t18, float t, float _t9, float _t21, float _t28, float _t10, float _t23, float _t8, float _t24, float _t30, float _t12, float _t29, float _t19, float _t31, float _t14, float _t15, float _t13, float _t27) {
        float _sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7;
        if (_t18 < 1.0E-12f) {
            _sfx0 = t * _t9;
            _sfx1 = t * _t10;
            _sfx2 = t * _t8;
            _sfx3 = 1.0f;
            _sfx4 = t * _t12;
            _sfx5 = t * _t14;
            _sfx6 = t * _t15;
            _sfx7 = t * t * _t13;
        } else {
            _sfx0 = _t21 * _t28;
            _sfx1 = _t23 * _t28;
            _sfx2 = _t24 * _t28;
            _sfx3 = _t30;
            _sfx4 = Math.fma(Math.fma(_t29, _t21, _t12) * _t19, _t28, -(_t31 * _t21));
            _sfx5 = Math.fma(Math.fma(_t29, _t23, _t14) * _t19, _t28, -(_t31 * _t23));
            _sfx6 = Math.fma(Math.fma(_t29, _t24, _t15) * _t19, _t28, -(_t31 * _t24));
            _sfx7 = _t27 * _t28;
        }
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }


    /**
     * Raise this dual quaternion to the power of {@code t} (screw-motion power: {@code t = 0}
     * yields the identity, {@code t = 1} yields {@code this}), returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param t the exponent
     * @return the resulting dual quaternion
     */
    public FloatDualQuat pow(float t) {
        float _t8, _t9, _t10, _t11, _t12, _t13, _t14, _t15;
        if (this.rW < 0.0f) {
            _t8 = -this.rZ;
            _t9 = -this.rX;
            _t10 = -this.rY;
            _t11 = -this.rW;
            _t12 = -this.dX;
            _t13 = -this.dW;
            _t14 = -this.dY;
            _t15 = -this.dZ;
        } else {
            _t8 = this.rZ;
            _t9 = this.rX;
            _t10 = this.rY;
            _t11 = this.rW;
            _t12 = this.dX;
            _t13 = this.dW;
            _t14 = this.dY;
            _t15 = this.dZ;
        }
        float _t18 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        return pow_s5250ffb0_tail(_t19, _t10, _t8, _t13, t, _t18, _t11, _t9, _t19 * _t9, _t12, _t14, _t15);
    }


    /**
     * Set the dual half of this dual quaternion to {@code dual}, keeping the real (rotation) half
     * as it is, returning the result as a value.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} follows the new dual half; use
     * {@code setTranslation} to set the translation itself.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dual the new dual half
     * @return the resulting dual quaternion
     */
    public FloatDualQuat setDual(FloatQuat dual) {
        float dualX = dual.x();
        float dualY = dual.y();
        float dualZ = dual.z();
        float dualW = dual.w();
        return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, dualX, dualY, dualZ, dualW);
    }


    /**
     * Set the dual half of this dual quaternion to ({@code dualX}, {@code dualY}, {@code dualZ},
     * {@code dualW}), keeping the real (rotation) half as it is, returning the result as a value.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} follows the new dual half; use
     * {@code setTranslation} to set the translation itself.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dualX the {@code x} component of the new dual half
     *        {@code (dualX, dualY, dualZ, dualW)}
     * @param dualY the {@code y} component of the new dual half
     *        {@code (dualX, dualY, dualZ, dualW)}
     * @param dualZ the {@code z} component of the new dual half
     *        {@code (dualX, dualY, dualZ, dualW)}
     * @param dualW the {@code w} component of the new dual half
     *        {@code (dualX, dualY, dualZ, dualW)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat setDual(float dualX, float dualY, float dualZ, float dualW) {
        return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, dualX, dualY, dualZ, dualW);
    }


    /**
     * Set the real (rotation) half of this dual quaternion to {@code real}, keeping the dual half
     * as it is, returning the result as a value.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} changes with the real half; use
     * {@code setRotation} to replace the rotation and keep the translation.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param real the new real (rotation) half
     * @return the resulting dual quaternion
     */
    public FloatDualQuat setReal(FloatQuat real) {
        float realX = real.x();
        float realY = real.y();
        float realZ = real.z();
        float realW = real.w();
        return new FloatDualQuat(realX, realY, realZ, realW, this.dX, this.dY, this.dZ, this.dW);
    }


    /**
     * Set the real (rotation) half of this dual quaternion to ({@code realX}, {@code realY},
     * {@code realZ}, {@code realW}), keeping the dual half as it is, returning the result as a
     * value.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} changes with the real half; use
     * {@code setRotation} to replace the rotation and keep the translation.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param realX the {@code x} component of the new real (rotation) half
     *        {@code (realX, realY, realZ, realW)}
     * @param realY the {@code y} component of the new real (rotation) half
     *        {@code (realX, realY, realZ, realW)}
     * @param realZ the {@code z} component of the new real (rotation) half
     *        {@code (realX, realY, realZ, realW)}
     * @param realW the {@code w} component of the new real (rotation) half
     *        {@code (realX, realY, realZ, realW)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat setReal(float realX, float realY, float realZ, float realW) {
        return new FloatDualQuat(realX, realY, realZ, realW, this.dX, this.dY, this.dZ, this.dW);
    }


    /**
     * Set the rotation of this dual quaternion to {@code rotation}, returning the result as a
     * value.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the new rotation
     * @return the resulting dual quaternion
     */
    public FloatDualQuat setRotation(FloatQuat rotation) {
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        float _t0 = -rotationY;
        float _t22 = 2.0f * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
        float _t23 = 2.0f * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW)));
        float _t24 = 2.0f * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW)));
        return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * Math.fma(_t0, _t22, Math.fma(rotationZ, _t23, rotationW * _t24)), 0.5f * Math.fma(rotationX, _t22, Math.fma(rotationW, _t23, -(rotationZ * _t24))), 0.5f * Math.fma(rotationW, _t22, Math.fma(rotationY, _t24, -(rotationX * _t23))), 0.5f * Math.fma(-rotationZ, _t22, Math.fma(_t0, _t23, -(rotationX * _t24))));
    }


    /**
     * Set the rotation of this dual quaternion to ({@code rotationX}, {@code rotationY},
     * {@code rotationZ}, {@code rotationW}), returning the result as a value.
     * <p>
     * Valid input: {@code (rotationX, rotationY, rotationZ, rotationW)} must have unit length.
     *
     * @param rotationX the {@code x} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationY the {@code y} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationZ the {@code z} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationW the {@code w} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat setRotation(float rotationX, float rotationY, float rotationZ, float rotationW) {
        float _t0 = -rotationY;
        float _t22 = 2.0f * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
        float _t23 = 2.0f * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW)));
        float _t24 = 2.0f * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW)));
        return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * Math.fma(_t0, _t22, Math.fma(rotationZ, _t23, rotationW * _t24)), 0.5f * Math.fma(rotationX, _t22, Math.fma(rotationW, _t23, -(rotationZ * _t24))), 0.5f * Math.fma(rotationW, _t22, Math.fma(rotationY, _t24, -(rotationX * _t23))), 0.5f * Math.fma(-rotationZ, _t22, Math.fma(_t0, _t23, -(rotationX * _t24))));
    }


    /**
     * Set the translation of this dual quaternion to {@code translation}, returning the result as a
     * value.
     * <p>
     * Valid input: the real part of this dual quaternion must have unit length.
     *
     * @param translation the new translation
     * @return the resulting dual quaternion
     */
    public FloatDualQuat setTranslation(Float3 translation) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        float _t0 = -this.rY;
        return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5f * Math.fma(_t0, translationZ, Math.fma(this.rZ, translationY, this.rW * translationX)), 0.5f * Math.fma(this.rX, translationZ, Math.fma(this.rW, translationY, -(this.rZ * translationX))), 0.5f * Math.fma(this.rW, translationZ, Math.fma(this.rY, translationX, -(this.rX * translationY))), 0.5f * Math.fma(-this.rZ, translationZ, Math.fma(_t0, translationY, -(this.rX * translationX))));
    }


    /**
     * Set the translation of this dual quaternion to ({@code translationX}, {@code translationY},
     * {@code translationZ}), returning the result as a value.
     * <p>
     * Valid input: the real part of this dual quaternion must have unit length.
     *
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat setTranslation(float translationX, float translationY, float translationZ) {
        float _t0 = -this.rY;
        return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5f * Math.fma(_t0, translationZ, Math.fma(this.rZ, translationY, this.rW * translationX)), 0.5f * Math.fma(this.rX, translationZ, Math.fma(this.rW, translationY, -(this.rZ * translationX))), 0.5f * Math.fma(this.rW, translationZ, Math.fma(this.rY, translationX, -(this.rX * translationY))), 0.5f * Math.fma(-this.rZ, translationZ, Math.fma(_t0, translationY, -(this.rX * translationX))));
    }

    /** Private per-column body of {@code toMatrix}; reached only through it. */
    private float[] toMatrix_s0_c0(float _t0, float _t6, float _t2, float _t3, float _sp0) {
        return new float[] {Math.fma(-2.0f, _t0, _t6), 2.0f * Math.fma(this.rX, this.rY, _t2), Math.fma(-2.0f, _t3, _sp0 * this.rZ), 0.0f};
    }

    /** Private per-column body of {@code toMatrix}; reached only through it. */
    private float[] toMatrix_s0_c1(float _t2, float _sp0, float _t4, float _t6, float _t5) {
        return new float[] {Math.fma(-2.0f, _t2, _sp0 * this.rY), Math.fma(-2.0f, _t4, _t6), 2.0f * Math.fma(this.rX, this.rW, _t5), 0.0f};
    }

    /** Private per-column body of {@code toMatrix}; reached only through it. */
    private float[] toMatrix_s0_c2(float _t3, float _t5, float _t4, float _t0) {
        return new float[] {2.0f * Math.fma(this.rX, this.rZ, _t3), Math.fma(-2.0f, this.rX * this.rW, _t5 + _t5), Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f)), 0.0f};
    }

    /** Private per-column body of {@code toMatrix}; reached only through it. */
    private float[] toMatrix_s0_c3() {
        return new float[] {2.0f * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW))), 2.0f * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW))), 2.0f * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW))), 1.0f};
    }


    /**
     * Compute the matrix representation of this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the resulting matrix
     */
    public Float4x4 toMatrix() {
        float _sp0 = this.rX + this.rX;
        float _t0 = this.rY * this.rY;
        float _t2 = this.rZ * this.rW;
        float _t3 = this.rY * this.rW;
        float _t4 = this.rX * this.rX;
        float _t5 = this.rY * this.rZ;
        float _t6 = Math.fma(-2.0f, this.rZ * this.rZ, 1.0f);
        float[] _col0 = toMatrix_s0_c0(_t0, _t6, _t2, _t3, _sp0);
        float[] _col1 = toMatrix_s0_c1(_t2, _sp0, _t4, _t6, _t5);
        float[] _col2 = toMatrix_s0_c2(_t3, _t5, _t4, _t0);
        float[] _col3 = toMatrix_s0_c3();
        return new Float4x4(_col0[0], _col1[0], _col2[0], _col3[0], _col0[1], _col1[1], _col2[1], _col3[1], _col0[2], _col1[2], _col2[2], _col3[2], _col0[3], _col1[3], _col2[3], _col3[3], Joml.BIT_ORTHOGONAL);
    }


    /**
     * Compute the 3x3 matrix representation of the rotation part of this dual quaternion (the
     * encoded translation is dropped), returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the resulting matrix
     */
    public Float3x3 toMatrix3x3() {
        float _sp0 = this.rX + this.rX;
        float _t0 = this.rY * this.rY;
        float _t2 = this.rZ * this.rW;
        float _t3 = this.rY * this.rW;
        float _t4 = this.rX * this.rX;
        float _t5 = this.rY * this.rZ;
        float _t6 = Math.fma(-2.0f, this.rZ * this.rZ, 1.0f);
        return new Float3x3(Math.fma(-2.0f, _t0, _t6), Math.fma(-2.0f, _t2, _sp0 * this.rY), 2.0f * Math.fma(this.rX, this.rZ, _t3), 2.0f * Math.fma(this.rX, this.rY, _t2), Math.fma(-2.0f, _t4, _t6), Math.fma(-2.0f, this.rX * this.rW, _t5 + _t5), Math.fma(-2.0f, _t3, _sp0 * this.rZ), 2.0f * Math.fma(this.rX, this.rW, _t5), Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f)), 0);
    }

    /** Private per-column body of {@code toMatrix3x4}; reached only through it. */
    private float[] toMatrix3x4_s0_c0(float _t0, float _t6, float _t2, float _t3, float _sp0) {
        return new float[] {Math.fma(-2.0f, _t0, _t6), 2.0f * Math.fma(this.rX, this.rY, _t2), Math.fma(-2.0f, _t3, _sp0 * this.rZ)};
    }

    /** Private per-column body of {@code toMatrix3x4}; reached only through it. */
    private float[] toMatrix3x4_s0_c1(float _t2, float _sp0, float _t4, float _t6, float _t5) {
        return new float[] {Math.fma(-2.0f, _t2, _sp0 * this.rY), Math.fma(-2.0f, _t4, _t6), 2.0f * Math.fma(this.rX, this.rW, _t5)};
    }

    /** Private per-column body of {@code toMatrix3x4}; reached only through it. */
    private float[] toMatrix3x4_s0_c2(float _t3, float _t5, float _t4, float _t0) {
        return new float[] {2.0f * Math.fma(this.rX, this.rZ, _t3), Math.fma(-2.0f, this.rX * this.rW, _t5 + _t5), Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f))};
    }

    /** Private per-column body of {@code toMatrix3x4}; reached only through it. */
    private float[] toMatrix3x4_s0_c3() {
        return new float[] {2.0f * (Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW))), 2.0f * (Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW))), 2.0f * (Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)))};
    }


    /**
     * Compute the 3x4 matrix representation of this dual quaternion (the omitted last row is
     * implicitly {@code 0, 0, 0, 1}), returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the resulting matrix
     */
    public Float3x4 toMatrix3x4() {
        float _sp0 = this.rX + this.rX;
        float _t0 = this.rY * this.rY;
        float _t2 = this.rZ * this.rW;
        float _t3 = this.rY * this.rW;
        float _t4 = this.rX * this.rX;
        float _t5 = this.rY * this.rZ;
        float _t6 = Math.fma(-2.0f, this.rZ * this.rZ, 1.0f);
        float[] _col0 = toMatrix3x4_s0_c0(_t0, _t6, _t2, _t3, _sp0);
        float[] _col1 = toMatrix3x4_s0_c1(_t2, _sp0, _t4, _t6, _t5);
        float[] _col2 = toMatrix3x4_s0_c2(_t3, _t5, _t4, _t0);
        float[] _col3 = toMatrix3x4_s0_c3();
        return new Float3x4(_col0[0], _col1[0], _col2[0], _col3[0], _col0[1], _col1[1], _col2[1], _col3[1], _col0[2], _col1[2], _col2[2], _col3[2], Joml.BIT_ORTHOGONAL);
    }


    /**
     * Apply a rotation transformation that makes {@code +z} point along {@code dir} to this dual
     * quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction the local {@code +z} axis is
     *        mapped to
     * @param up the direction of "up"
     * @return the resulting dual quaternion
     */
    public FloatDualQuat lookAlong(Float3 dir, Float3 up) {
        return lookAlong(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }


    /**
     * Apply a rotation transformation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}) to this dual quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t8 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t28 = Math.fma(-dirY, _sp0, upY);
        float _t29 = Math.fma(-dirZ, _sp0, upZ);
        float _t30 = Math.fma(-dirX, _sp0, upX);
        float _t37 = Math.fma(dirZ, _t28, -(dirY * _t29));
        float _t38 = Math.fma(dirY, _t30, -(dirX * _t28));
        float _t39 = Math.fma(dirX, _t29, -(dirZ * _t30));
        float _ct0 = Math.fma(_t38, _t38, Math.fma(_t39, _t39, _t37 * _t37));
        if (!(_ct0 > Math.fma(_t8, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t17 = dirZ * _t16;
        float _t18 = dirX * _t16;
        return lookAlong_sa325590d_1(dirX, dirY, dirZ, _t16, _t37, _t39, _t45, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37 * _t45, _t38 * _t45, _t39 * _t45, Math.fma(-_t37, _t45, 1.0f));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_sa325590d_1(float dirX, float dirY, float dirZ, float _t16, float _t37, float _t39, float _t45, float _t1, float _t17, float _t18, float _t19, float _t20, float _t22, float _t46, float _t47, float _t48, float _t50) {
        float _t65 = Math.fma(_t18, _t48, -(_t19 * _t46));
        float _t66 = Math.fma(_t19, _t47, -(_t17 * _t48));
        float _t78 = Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t37, _t45, Math.fma(dirZ, _t16, 1.0f))));
        float _t80 = Math.fma(_t37, _t45, Math.fma(_t22, _t46, Math.fma(_t18, _t47, Math.fma(_t1, _t16, 1.0f))));
        float _t81 = Math.fma(dirZ, _t16, Math.fma(_t22, _t46, Math.fma(_t18, _t47, _t50)));
        float _t84 = Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t1, _t16, _t50)));
        return lookAlong_sa325590d_2(dirZ, _t16, _t37, _t45, _t17, _t46, Math.fma(dirX, _t16, _t47), Math.fma(dirX, _t16, -_t47), Math.fma(_t17, _t46, -(_t18 * _t47)), Math.fma(dirY, _t16, _t65), Math.fma(-dirY, _t16, _t65), Math.fma(_t39, _t45, _t66), Math.fma(_t39, _t45, -_t66), _t78, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), _t84, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84)));
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_sa325590d_2(float dirZ, float _t16, float _t37, float _t45, float _t17, float _t46, float _t51, float _t55, float _t63, float _t69, float _t71, float _t74, float _t75, float _t78, float _t80, float _t81, float _sp1, float _sp4, float _t84, float _sp3, float _sp2) {
        float _t126, _t127, _t128, _t129;
        if (Math.fma(dirZ, _t16, Math.fma(_t37, _t45, _t63)) > 0.0f) {
            _t126 = _sp1 * _t71;
            _t127 = _sp1 * _t75;
            _t128 = _sp1 * _t55;
            _t129 = 0.5f * (float) java.lang.Math.sqrt(_t78);
        } else {
            if (_t46 > java.lang.Math.max(_t63, _t17)) {
                _t126 = 0.5f * (float) java.lang.Math.sqrt(_t80);
                _t127 = _sp4 * _t51;
                _t128 = _sp4 * _t74;
                _t129 = _sp4 * _t71;
            } else {
                if (_t63 > _t17) {
                    _t126 = _sp2 * _t74;
                    _t127 = _sp2 * _t69;
                    _t128 = 0.5f * (float) java.lang.Math.sqrt(_t84);
                    _t129 = _sp2 * _t55;
                } else {
                    _t126 = _sp3 * _t51;
                    _t127 = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    _t128 = _sp3 * _t69;
                    _t129 = _sp3 * _t75;
                }
            }
        }
        return lookAlong_sa325590d_3(_t126, _t127, _t128, _t129, Math.fma(this.rX, _t129, this.rW * _t126) + Math.fma(this.rY, _t127, -(this.rZ * _t128)), Math.fma(this.rY, _t129, this.rZ * _t126) + Math.fma(this.rW, _t128, -(this.rX * _t127)), Math.fma(this.rX, _t128, this.rW * _t127) + Math.fma(this.rZ, _t129, -(this.rY * _t126)));
    }

    /** Piece 4 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_sa325590d_3(float _t126, float _t127, float _t128, float _t129, float _sfx0, float _sfx1, float _sfx2) {
        float _sfx3 = Math.fma(this.rW, _t129, -(this.rX * _t126)) - Math.fma(this.rY, _t128, this.rZ * _t127);
        float _sfx4 = Math.fma(this.dX, _t129, this.dW * _t126) + Math.fma(this.dY, _t127, -(this.dZ * _t128));
        float _sfx5 = Math.fma(this.dY, _t129, this.dZ * _t126) + Math.fma(this.dW, _t128, -(this.dX * _t127));
        float _sfx6 = Math.fma(this.dX, _t128, this.dW * _t127) + Math.fma(this.dZ, _t129, -(this.dY * _t126));
        float _sfx7 = Math.fma(this.dW, _t129, -(this.dX * _t126)) - Math.fma(this.dY, _t128, this.dZ * _t127);
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }


    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_degenerate(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0f) {
            _t21 = 0.0f;
            _t22 = 0.0f;
            _t23 = 1.0f;
            _t24 = 1.0f;
            _t25 = 0.0f;
            _t26 = 0.0f;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        float _t29 = -_t26;
        float _t36, _t37, _t41;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t36 = 0.0f;
            _t37 = _t29;
            _t41 = _t25;
        } else {
            _t36 = _t26;
            _t37 = 0.0f;
            _t41 = -_t24;
        }
        float _t43 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        float _t44 = Math.fma(_t43, _t25, _t22);
        float _t45 = Math.fma(_t43, _t26, _t23);
        float _t46 = Math.fma(_t43, _t24, _t21);
        return lookAlong_degenerate_sedf50666_2(_t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, Math.fma(_t44, _t26, -(_t45 * _t25)), Math.fma(_t46, _t25, -(_t44 * _t24)), Math.fma(_t45, _t24, -(_t46 * _t26)));
    }

    /** Part 1 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_degenerate_sedf50666_1(float _t151, float _t148, float _t149, float _t150) {
        float _sfx0 = Math.fma(this.rX, _t151, this.rW * _t148) + Math.fma(this.rY, _t149, -(this.rZ * _t150));
        float _sfx1 = Math.fma(this.rY, _t151, this.rZ * _t148) + Math.fma(this.rW, _t150, -(this.rX * _t149));
        float _sfx2 = Math.fma(this.rX, _t150, this.rW * _t149) + Math.fma(this.rZ, _t151, -(this.rY * _t148));
        float _sfx3 = Math.fma(this.rW, _t151, -(this.rX * _t148)) - Math.fma(this.rY, _t150, this.rZ * _t149);
        float _sfx4 = Math.fma(this.dX, _t151, this.dW * _t148) + Math.fma(this.dY, _t149, -(this.dZ * _t150));
        float _sfx5 = Math.fma(this.dY, _t151, this.dZ * _t148) + Math.fma(this.dW, _t150, -(this.dX * _t149));
        float _sfx6 = Math.fma(this.dX, _t150, this.dW * _t149) + Math.fma(this.dZ, _t151, -(this.dY * _t148));
        float _sfx7 = Math.fma(this.dW, _t151, -(this.dX * _t148)) - Math.fma(this.dY, _t150, this.dZ * _t149);
        return new FloatDualQuat((_sfx0), (_sfx1), (_sfx2), (_sfx3), (_sfx4), (_sfx5), (_sfx6), (_sfx7));
    }

    /** Piece 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_degenerate_sedf50666_2(float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t55, float _t56, float _t57) {
        float _t61 = Math.fma(_t55, _t55, Math.fma(_t56, _t56, _t57 * _t57));
        float _t62, _t63, _t64, _t66;
        if (_t61 <= Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t62 = _t36;
            _t63 = _t37;
            _t64 = _t41;
            _t66 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t41 * _t41))));
        } else {
            _t62 = _t55;
            _t63 = _t57;
            _t64 = _t56;
            _t66 = (1.0f / (float) java.lang.Math.sqrt(_t61));
        }
        float _t67 = -_t66;
        float _t68 = _t66 * _t62;
        float _t69 = _t66 * _t63;
        float _t70 = -_t68;
        float _t71 = -_t69;
        float _t72 = _t66 * _t64;
        float _t99 = Math.fma(_t66, _t63, Math.fma(_t71, _t24, Math.fma(_t68, _t25, _t32)));
        return lookAlong_degenerate_sedf50666_3(_t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, Math.fma(_t66, _t62, _t25), Math.fma(_t67, _t62, _t25), Math.fma(_t69, _t24, -(_t68 * _t25)), Math.fma(_t68, _t26, -(_t72 * _t24)), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t26)), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t29)), Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t31))), _t99, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)));
    }

    /** Piece 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_degenerate_sedf50666_3(float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t95, float _t96, float _t98, float _t99, float _sp3) {
        float _t102 = Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t67, _t63, _t32)));
        float _t103 = Math.fma(_t71, _t24, Math.fma(_t68, _t25, Math.fma(_t67, _t63, _t31)));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103));
        float _t114 = Math.fma(_t66, _t64, _t91);
        float _t115 = Math.fma(_t66, _t64, -_t91);
        float _t148, _t149, _t150, _t151;
        if (Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t24))) > 0.0f) {
            _t148 = _sp0 * _t96;
            _t149 = _sp0 * _t115;
            _t150 = _sp0 * _t76;
            _t151 = 0.5f * (float) java.lang.Math.sqrt(_t98);
        } else {
            if (_t69 > java.lang.Math.max(_t87, _t24)) {
                _t148 = 0.5f * (float) java.lang.Math.sqrt(_t99);
                _t149 = _sp3 * _t73;
                _t150 = _sp3 * _t114;
                _t151 = _sp3 * _t96;
            } else {
                if (_t87 > _t24) {
                    _t148 = _sp1 * _t114;
                    _t149 = _sp1 * _t95;
                    _t150 = 0.5f * (float) java.lang.Math.sqrt(_t102);
                    _t151 = _sp1 * _t76;
                } else {
                    _t148 = _sp2 * _t73;
                    _t149 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t150 = _sp2 * _t95;
                    _t151 = _sp2 * _t115;
                }
            }
        }
        return lookAlong_degenerate_sedf50666_1(_t151, _t148, _t149, _t150);
    }


    /**
     * Create a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationAxis(float angle, Float3 axis) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        return new FloatDualQuat(axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0), 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}).
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        return new FloatDualQuat(axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0), 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create a rotation that makes {@code +z} point along {@code dir}.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction the local {@code +z} axis is
     *        mapped to
     * @param up the direction of "up"
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationLookAlong(Float3 dir, Float3 up) {
        return makeRotationLookAlong(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }


    /**
     * Create a rotation that makes {@code +z} point along ({@code dirX}, {@code dirY},
     * {@code dirZ}).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t8 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t28 = Math.fma(-dirY, _sp0, upY);
        float _t29 = Math.fma(-dirZ, _sp0, upZ);
        float _t30 = Math.fma(-dirX, _sp0, upX);
        float _t37 = Math.fma(dirZ, _t28, -(dirY * _t29));
        float _t38 = Math.fma(dirY, _t30, -(dirX * _t28));
        float _t39 = Math.fma(dirX, _t29, -(dirZ * _t30));
        float _ct0 = Math.fma(_t38, _t38, Math.fma(_t39, _t39, _t37 * _t37));
        if (!(_ct0 > Math.fma(_t8, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t17 = dirZ * _t16;
        float _t18 = dirX * _t16;
        return makeRotationLookAlong_s25627a91_1(dirX, dirY, dirZ, _t16, _t37, _t39, _t45, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, _t37 * _t45, _t38 * _t45, _t39 * _t45, Math.fma(-_t37, _t45, 1.0f));
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private static FloatDualQuat makeRotationLookAlong_s25627a91_1(float dirX, float dirY, float dirZ, float _t16, float _t37, float _t39, float _t45, float _t1, float _t17, float _t18, float _t19, float _t20, float _t22, float _t46, float _t47, float _t48, float _t50) {
        float _t64 = Math.fma(_t18, _t48, -(_t19 * _t46));
        float _t66 = Math.fma(_t19, _t47, -(_t17 * _t48));
        float _t78 = Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t37, _t45, Math.fma(dirZ, _t16, 1.0f))));
        float _t80 = Math.fma(_t37, _t45, Math.fma(_t22, _t46, Math.fma(_t18, _t47, Math.fma(_t1, _t16, 1.0f))));
        float _t81 = Math.fma(dirZ, _t16, Math.fma(_t22, _t46, Math.fma(_t18, _t47, _t50)));
        float _t82 = Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t1, _t16, _t50)));
        return makeRotationLookAlong_s25627a91_2(dirZ, _t16, _t37, _t45, _t17, _t46, Math.fma(dirX, _t16, _t47), Math.fma(dirX, _t16, -_t47), Math.fma(_t17, _t46, -(_t18 * _t47)), Math.fma(dirY, _t16, _t64), Math.fma(-dirY, _t16, _t64), Math.fma(_t39, _t45, _t66), Math.fma(_t39, _t45, -_t66), _t78, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), _t80, _t81, _t82, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t82)));
    }

    /** Piece 3 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private static FloatDualQuat makeRotationLookAlong_s25627a91_2(float dirZ, float _t16, float _t37, float _t45, float _t17, float _t46, float _t51, float _t56, float _t63, float _t69, float _t70, float _t74, float _t75, float _t78, float _sp1, float _t80, float _t81, float _t82, float _sp3, float _sp4, float _sp2) {
        float _sfx0, _sfx1, _sfx2, _sfx3;
        if (Math.fma(dirZ, _t16, Math.fma(_t37, _t45, _t63)) > 0.0f) {
            _sfx0 = _sp1 * _t70;
            _sfx1 = _sp1 * _t56;
            _sfx2 = _sp1 * _t75;
            _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t78);
        } else {
            if (_t46 > java.lang.Math.max(_t63, _t17)) {
                _sfx0 = 0.5f * (float) java.lang.Math.sqrt(_t80);
                _sfx1 = _sp4 * _t74;
                _sfx2 = _sp4 * _t51;
                _sfx3 = _sp4 * _t70;
            } else {
                if (_t63 > _t17) {
                    _sfx0 = _sp2 * _t74;
                    _sfx1 = 0.5f * (float) java.lang.Math.sqrt(_t82);
                    _sfx2 = _sp2 * _t69;
                    _sfx3 = _sp2 * _t56;
                } else {
                    _sfx0 = _sp3 * _t51;
                    _sfx1 = _sp3 * _t69;
                    _sfx2 = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    _sfx3 = _sp3 * _t75;
                }
            }
        }
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static FloatDualQuat makeRotationLookAlong_degenerate(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0f) {
            _t21 = 0.0f;
            _t22 = 0.0f;
            _t23 = 1.0f;
            _t24 = 1.0f;
            _t25 = 0.0f;
            _t26 = 0.0f;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        float _t29 = -_t26;
        float _t36, _t37, _t41;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t36 = 0.0f;
            _t37 = _t29;
            _t41 = _t25;
        } else {
            _t36 = _t26;
            _t37 = 0.0f;
            _t41 = -_t24;
        }
        float _t43 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        float _t44 = Math.fma(_t43, _t25, _t22);
        float _t45 = Math.fma(_t43, _t26, _t23);
        float _t46 = Math.fma(_t43, _t24, _t21);
        return makeRotationLookAlong_degenerate_s1ae3d4da_1(_t21, _t22, _t23, _t24, _t25, _t26, _t29, 1.0f + _t24, 1.0f - _t24, _t36, _t37, _t41, Math.fma(_t44, _t26, -(_t45 * _t25)), Math.fma(_t46, _t25, -(_t44 * _t24)), Math.fma(_t45, _t24, -(_t46 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private static FloatDualQuat makeRotationLookAlong_degenerate_s1ae3d4da_1(float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t31, float _t32, float _t36, float _t37, float _t41, float _t55, float _t56, float _t57) {
        float _t61 = Math.fma(_t55, _t55, Math.fma(_t56, _t56, _t57 * _t57));
        float _t62, _t63, _t64, _t66;
        if (_t61 <= Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t62 = _t36;
            _t63 = _t37;
            _t64 = _t41;
            _t66 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t41 * _t41))));
        } else {
            _t62 = _t55;
            _t63 = _t57;
            _t64 = _t56;
            _t66 = (1.0f / (float) java.lang.Math.sqrt(_t61));
        }
        float _t67 = -_t66;
        float _t68 = _t66 * _t62;
        float _t69 = _t66 * _t63;
        float _t70 = -_t68;
        float _t71 = -_t69;
        float _t72 = _t66 * _t64;
        float _t98 = Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t31)));
        return makeRotationLookAlong_degenerate_s1ae3d4da_2(_t24, _t25, _t31, _t32, _t63, _t64, _t66, _t67, _t68, _t69, _t70, _t71, Math.fma(_t66, _t62, _t25), Math.fma(_t67, _t62, _t25), Math.fma(_t69, _t24, -(_t68 * _t25)), Math.fma(_t68, _t26, -(_t72 * _t24)), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t26)), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t29)), _t98, Math.fma(_t66, _t63, Math.fma(_t71, _t24, Math.fma(_t68, _t25, _t32))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private static FloatDualQuat makeRotationLookAlong_degenerate_s1ae3d4da_2(float _t24, float _t25, float _t31, float _t32, float _t63, float _t64, float _t66, float _t67, float _t68, float _t69, float _t70, float _t71, float _t73, float _t76, float _t87, float _t91, float _t95, float _t96, float _t98, float _t99, float _sp0) {
        float _t101 = Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t67, _t63, _t32)));
        float _t102 = Math.fma(_t71, _t24, Math.fma(_t68, _t25, Math.fma(_t67, _t63, _t31)));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t101));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102));
        float _t106 = Math.fma(_t66, _t64, _t91);
        float _t107 = Math.fma(_t66, _t64, -_t91);
        float _sfx0, _sfx1, _sfx2, _sfx3;
        if (Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t24))) > 0.0f) {
            _sfx0 = _sp0 * _t96;
            _sfx1 = _sp0 * _t76;
            _sfx2 = _sp0 * _t107;
            _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t98);
        } else {
            if (_t69 > java.lang.Math.max(_t87, _t24)) {
                _sfx0 = 0.5f * (float) java.lang.Math.sqrt(_t99);
                _sfx1 = _sp3 * _t106;
                _sfx2 = _sp3 * _t73;
                _sfx3 = _sp3 * _t96;
            } else {
                if (_t87 > _t24) {
                    _sfx0 = _sp1 * _t106;
                    _sfx1 = 0.5f * (float) java.lang.Math.sqrt(_t101);
                    _sfx2 = _sp1 * _t95;
                    _sfx3 = _sp1 * _t76;
                } else {
                    _sfx0 = _sp2 * _t73;
                    _sfx1 = _sp2 * _t95;
                    _sfx2 = 0.5f * (float) java.lang.Math.sqrt(_t102);
                    _sfx3 = _sp2 * _t107;
                }
            }
        }
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationX(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        return new FloatDualQuat(_t1, 0.0f, 0.0f, Math.cosFromSin(_t1, _t0), 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationXYZ(float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t4, _t1);
        float _t7 = Math.cosFromSin(_t5, _t2);
        float _t8 = Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        return new FloatDualQuat(Math.fma(_t10, _t7, _t11 * _t5), Math.fma(_t11, _t7, -(_t10 * _t5)), Math.fma(_t9, _t7, _t12 * _t5), Math.fma(_t12, _t7, -(_t9 * _t5)), 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationXZY(float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t4, _t1);
        float _t7 = Math.cosFromSin(_t5, _t2);
        float _t8 = Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        return new FloatDualQuat(Math.fma(_t10, _t7, -(_t11 * _t5)), Math.fma(_t12, _t5, -(_t9 * _t7)), Math.fma(_t10, _t5, _t11 * _t7), Math.fma(_t9, _t5, _t12 * _t7), 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationY(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        return new FloatDualQuat(0.0f, _t1, 0.0f, Math.cosFromSin(_t1, _t0), 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationYXZ(float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t4, _t1);
        float _t7 = Math.cosFromSin(_t5, _t2);
        float _t8 = Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        return new FloatDualQuat(Math.fma(_t10, _t7, _t11 * _t5), Math.fma(_t11, _t7, -(_t10 * _t5)), Math.fma(_t12, _t5, -(_t9 * _t7)), Math.fma(_t9, _t5, _t12 * _t7), 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationYZX(float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t5, _t2);
        float _t7 = Math.cosFromSin(_t3, _t0);
        float _t8 = Math.cosFromSin(_t4, _t1);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t8;
        float _t11 = _t4 * _t7;
        float _t12 = _t7 * _t8;
        return new FloatDualQuat(Math.fma(_t9, _t6, _t12 * _t5), Math.fma(_t10, _t6, _t11 * _t5), Math.fma(_t11, _t6, -(_t10 * _t5)), Math.fma(_t12, _t6, -(_t9 * _t5)), 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationZ(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        return new FloatDualQuat(0.0f, 0.0f, _t1, Math.cosFromSin(_t1, _t0), 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationZXY(float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t4, _t1);
        float _t7 = Math.cosFromSin(_t5, _t2);
        float _t8 = Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        return new FloatDualQuat(Math.fma(_t10, _t7, -(_t11 * _t5)), Math.fma(_t9, _t7, _t12 * _t5), Math.fma(_t10, _t5, _t11 * _t7), Math.fma(_t12, _t7, -(_t9 * _t5)), 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Create a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationZYX(float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        return new FloatDualQuat(Math.fma(_t12, _t5, -(_t9 * _t8)), Math.fma(_t10, _t8, _t11 * _t5), Math.fma(_t11, _t8, -(_t10 * _t5)), Math.fma(_t9, _t5, _t12 * _t8), 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Pre-multiply the rotation represented by the quaternion {@code rotation} onto this dual
     * quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code R * Q}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @return the resulting dual quaternion
     */
    public FloatDualQuat preRotate(FloatQuat rotation) {
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        float _sfx0 = Math.fma(rotationX, this.rW, rotationW * this.rX) + Math.fma(rotationY, this.rZ, -(rotationZ * this.rY));
        float _sfx1 = Math.fma(rotationY, this.rW, rotationZ * this.rX) + Math.fma(rotationW, this.rY, -(rotationX * this.rZ));
        float _sfx2 = Math.fma(rotationX, this.rY, rotationW * this.rZ) + Math.fma(rotationZ, this.rW, -(rotationY * this.rX));
        float _sfx3 = Math.fma(rotationW, this.rW, -(rotationX * this.rX)) - Math.fma(rotationY, this.rY, rotationZ * this.rZ);
        return preRotate_s16969c0a_tail(rotationX, rotationW, rotationY, rotationZ, _sfx0, _sfx1, _sfx2, _sfx3);
    }

    /** Private tail of {@code preRotate}; reached only through it. */
    private FloatDualQuat preRotate_s16969c0a_tail(float rotationX, float rotationW, float rotationY, float rotationZ, float _sfx0, float _sfx1, float _sfx2, float _sfx3) {
        float _sfx4 = Math.fma(rotationX, this.dW, rotationW * this.dX) + Math.fma(rotationY, this.dZ, -(rotationZ * this.dY));
        float _sfx5 = Math.fma(rotationY, this.dW, rotationZ * this.dX) + Math.fma(rotationW, this.dY, -(rotationX * this.dZ));
        float _sfx6 = Math.fma(rotationX, this.dY, rotationW * this.dZ) + Math.fma(rotationZ, this.dW, -(rotationY * this.dX));
        float _sfx7 = Math.fma(rotationW, this.dW, -(rotationX * this.dX)) - Math.fma(rotationY, this.dY, rotationZ * this.dZ);
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }


    /**
     * Pre-multiply the rotation represented by the quaternion ({@code rotationX},
     * {@code rotationY}, {@code rotationZ}, {@code rotationW}) onto this dual quaternion, returning
     * the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code R * Q}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code (rotationX, rotationY, rotationZ, rotationW)} must have unit length.
     *
     * @param rotationX the {@code x} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationY the {@code y} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationZ the {@code z} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationW the {@code w} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat preRotate(float rotationX, float rotationY, float rotationZ, float rotationW) {
        float _sfx0 = Math.fma(rotationX, this.rW, rotationW * this.rX) + Math.fma(rotationY, this.rZ, -(rotationZ * this.rY));
        float _sfx1 = Math.fma(rotationY, this.rW, rotationZ * this.rX) + Math.fma(rotationW, this.rY, -(rotationX * this.rZ));
        float _sfx2 = Math.fma(rotationX, this.rY, rotationW * this.rZ) + Math.fma(rotationZ, this.rW, -(rotationY * this.rX));
        float _sfx3 = Math.fma(rotationW, this.rW, -(rotationX * this.rX)) - Math.fma(rotationY, this.rY, rotationZ * this.rZ);
        return preRotate_s16969c0a_tail(rotationX, rotationW, rotationY, rotationZ, _sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Apply the rotation represented by the quaternion {@code rotation} to this dual quaternion,
     * returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation to apply
     * @return the resulting dual quaternion
     */
    public FloatDualQuat rotate(FloatQuat rotation) {
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        float _sfx0 = Math.fma(rotationX, this.rW, rotationW * this.rX) + Math.fma(rotationZ, this.rY, -(rotationY * this.rZ));
        float _sfx1 = Math.fma(rotationX, this.rZ, rotationW * this.rY) + Math.fma(rotationY, this.rW, -(rotationZ * this.rX));
        float _sfx2 = Math.fma(rotationY, this.rX, rotationZ * this.rW) + Math.fma(rotationW, this.rZ, -(rotationX * this.rY));
        float _sfx3 = Math.fma(rotationW, this.rW, -(rotationX * this.rX)) - Math.fma(rotationY, this.rY, rotationZ * this.rZ);
        return rotate_s16969c0a_tail(rotationX, rotationW, rotationZ, rotationY, _sfx0, _sfx1, _sfx2, _sfx3);
    }

    /** Private tail of {@code rotate}; reached only through it. */
    private FloatDualQuat rotate_s16969c0a_tail(float rotationX, float rotationW, float rotationZ, float rotationY, float _sfx0, float _sfx1, float _sfx2, float _sfx3) {
        float _sfx4 = Math.fma(rotationX, this.dW, rotationW * this.dX) + Math.fma(rotationZ, this.dY, -(rotationY * this.dZ));
        float _sfx5 = Math.fma(rotationX, this.dZ, rotationW * this.dY) + Math.fma(rotationY, this.dW, -(rotationZ * this.dX));
        float _sfx6 = Math.fma(rotationY, this.dX, rotationZ * this.dW) + Math.fma(rotationW, this.dZ, -(rotationX * this.dY));
        float _sfx7 = Math.fma(rotationW, this.dW, -(rotationX * this.dX)) - Math.fma(rotationY, this.dY, rotationZ * this.dZ);
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }


    /**
     * Apply the rotation represented by the quaternion ({@code rotationX}, {@code rotationY},
     * {@code rotationZ}, {@code rotationW}) to this dual quaternion, returning the result as a
     * value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code (rotationX, rotationY, rotationZ, rotationW)} must have unit length.
     *
     * @param rotationX the {@code x} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationY the {@code y} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationZ the {@code z} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationW the {@code w} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat rotate(float rotationX, float rotationY, float rotationZ, float rotationW) {
        float _sfx0 = Math.fma(rotationX, this.rW, rotationW * this.rX) + Math.fma(rotationZ, this.rY, -(rotationY * this.rZ));
        float _sfx1 = Math.fma(rotationX, this.rZ, rotationW * this.rY) + Math.fma(rotationY, this.rW, -(rotationZ * this.rX));
        float _sfx2 = Math.fma(rotationY, this.rX, rotationZ * this.rW) + Math.fma(rotationW, this.rZ, -(rotationX * this.rY));
        float _sfx3 = Math.fma(rotationW, this.rW, -(rotationX * this.rX)) - Math.fma(rotationY, this.rY, rotationZ * this.rZ);
        return rotate_s16969c0a_tail(rotationX, rotationW, rotationZ, rotationY, _sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this dual
     * quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return the resulting dual quaternion
     */
    public FloatDualQuat rotateAxis(float angle, Float3 axis) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z());
    }

    /** Private tail of {@code rotateAxis}; reached only through it. */
    private FloatDualQuat rotateAxis_s3b957671_tail(float _t5, float _t2, float _t3, float _t4, float _sfx0, float _sfx1, float _sfx2, float _sfx3) {
        float _sfx4 = Math.fma(this.dX, _t5, this.dW * _t2) + Math.fma(this.dY, _t3, -(this.dZ * _t4));
        float _sfx5 = Math.fma(this.dY, _t5, this.dZ * _t2) + Math.fma(this.dW, _t4, -(this.dX * _t3));
        float _sfx6 = Math.fma(this.dX, _t4, this.dW * _t3) + Math.fma(this.dZ, _t5, -(this.dY * _t2));
        float _sfx7 = Math.fma(this.dW, _t5, -(this.dX * _t2)) - Math.fma(this.dY, _t4, this.dZ * _t3);
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) to this dual quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat rotateAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        float _sfx0 = Math.fma(this.rX, _t5, this.rW * _t2) + Math.fma(this.rY, _t3, -(this.rZ * _t4));
        float _sfx1 = Math.fma(this.rY, _t5, this.rZ * _t2) + Math.fma(this.rW, _t4, -(this.rX * _t3));
        float _sfx2 = Math.fma(this.rX, _t4, this.rW * _t3) + Math.fma(this.rZ, _t5, -(this.rY * _t2));
        float _sfx3 = Math.fma(this.rW, _t5, -(this.rX * _t2)) - Math.fma(this.rY, _t4, this.rZ * _t3);
        return rotateAxis_s3b957671_tail(_t5, _t2, _t3, _t4, _sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Apply a rotation of {@code angle} radians about the X axis to this dual quaternion, returning
     * the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting dual quaternion
     */
    public FloatDualQuat rotateX(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        return new FloatDualQuat(Math.fma(this.rX, _t2, this.rW * _t1), Math.fma(this.rY, _t2, this.rZ * _t1), Math.fma(this.rZ, _t2, -(this.rY * _t1)), Math.fma(this.rW, _t2, -(this.rX * _t1)), Math.fma(this.dX, _t2, this.dW * _t1), Math.fma(this.dY, _t2, this.dZ * _t1), Math.fma(this.dZ, _t2, -(this.dY * _t1)), Math.fma(this.dW, _t2, -(this.dX * _t1)));
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY}
     * and {@code rotateZYX}; reached only through them.
     */
    private FloatDualQuat rotateXYZ_s6e793366_tail(float _t22, float _t20, float _t21, float _t19, float _sfx0, float _sfx1) {
        float _sfx2 = Math.fma(this.rX, _t22, this.rW * _t20) + Math.fma(this.rZ, _t21, -(this.rY * _t19));
        float _sfx3 = Math.fma(this.rW, _t21, -(this.rX * _t19)) - Math.fma(this.rY, _t22, this.rZ * _t20);
        float _sfx4 = Math.fma(this.dX, _t21, this.dW * _t19) + Math.fma(this.dY, _t20, -(this.dZ * _t22));
        float _sfx5 = Math.fma(this.dY, _t21, this.dZ * _t19) + Math.fma(this.dW, _t22, -(this.dX * _t20));
        float _sfx6 = Math.fma(this.dX, _t22, this.dW * _t20) + Math.fma(this.dZ, _t21, -(this.dY * _t19));
        float _sfx7 = Math.fma(this.dW, _t21, -(this.dX * _t19)) - Math.fma(this.dY, _t22, this.dZ * _t20);
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this dual quaternion, returning the result as a
     * value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return the resulting dual quaternion
     */
    public FloatDualQuat rotateXYZ(float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t10, _t8, _t11 * _t5);
        float _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        float _sfx0 = Math.fma(this.rX, _t21, this.rW * _t19) + Math.fma(this.rY, _t20, -(this.rZ * _t22));
        float _sfx1 = Math.fma(this.rY, _t21, this.rZ * _t19) + Math.fma(this.rW, _t22, -(this.rX * _t20));
        return rotateXYZ_s6e793366_tail(_t22, _t20, _t21, _t19, _sfx0, _sfx1);
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this dual quaternion, returning the result as a
     * value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return the resulting dual quaternion
     */
    public FloatDualQuat rotateXZY(float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t10, _t5, _t11 * _t8);
        float _t21 = Math.fma(_t10, _t8, -(_t11 * _t5));
        float _t22 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _sfx0 = Math.fma(this.rX, _t19, this.rW * _t21) + Math.fma(this.rY, _t20, -(this.rZ * _t22));
        float _sfx1 = Math.fma(this.rY, _t19, this.rZ * _t21) + Math.fma(this.rW, _t22, -(this.rX * _t20));
        return rotateXYZ_s6e793366_tail(_t22, _t20, _t19, _t21, _sfx0, _sfx1);
    }


    /**
     * Apply a rotation of {@code angle} radians about the Y axis to this dual quaternion, returning
     * the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting dual quaternion
     */
    public FloatDualQuat rotateY(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        return new FloatDualQuat(Math.fma(this.rX, _t2, -(this.rZ * _t1)), Math.fma(this.rY, _t2, this.rW * _t1), Math.fma(this.rX, _t1, this.rZ * _t2), Math.fma(this.rW, _t2, -(this.rY * _t1)), Math.fma(this.dX, _t2, -(this.dZ * _t1)), Math.fma(this.dY, _t2, this.dW * _t1), Math.fma(this.dX, _t1, this.dZ * _t2), Math.fma(this.dW, _t2, -(this.dY * _t1)));
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this dual quaternion, returning the result as a
     * value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return the resulting dual quaternion
     */
    public FloatDualQuat rotateYXZ(float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t10, _t8, _t11 * _t5);
        float _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        float _sfx0 = Math.fma(this.rX, _t19, this.rW * _t20) + Math.fma(this.rY, _t21, -(this.rZ * _t22));
        float _sfx1 = Math.fma(this.rY, _t19, this.rZ * _t20) + Math.fma(this.rW, _t22, -(this.rX * _t21));
        return rotateXYZ_s6e793366_tail(_t22, _t21, _t19, _t20, _sfx0, _sfx1);
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this dual quaternion, returning the result as a
     * value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return the resulting dual quaternion
     */
    public FloatDualQuat rotateYZX(float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        float _sfx0 = Math.fma(this.rX, _t21, this.rW * _t19) + Math.fma(this.rY, _t22, -(this.rZ * _t20));
        float _sfx1 = Math.fma(this.rY, _t21, this.rZ * _t19) + Math.fma(this.rW, _t20, -(this.rX * _t22));
        return rotateXYZ_s6e793366_tail(_t20, _t22, _t21, _t19, _sfx0, _sfx1);
    }


    /**
     * Apply a rotation of {@code angle} radians about the Z axis to this dual quaternion, returning
     * the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting dual quaternion
     */
    public FloatDualQuat rotateZ(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        return new FloatDualQuat(Math.fma(this.rX, _t2, this.rY * _t1), Math.fma(this.rY, _t2, -(this.rX * _t1)), Math.fma(this.rZ, _t2, this.rW * _t1), Math.fma(this.rW, _t2, -(this.rZ * _t1)), Math.fma(this.dX, _t2, this.dY * _t1), Math.fma(this.dY, _t2, -(this.dX * _t1)), Math.fma(this.dZ, _t2, this.dW * _t1), Math.fma(this.dW, _t2, -(this.dZ * _t1)));
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this dual quaternion, returning the result as a
     * value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return the resulting dual quaternion
     */
    public FloatDualQuat rotateZXY(float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t10, _t5, _t11 * _t8);
        float _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        float _sfx0 = Math.fma(this.rX, _t21, this.rW * _t22) + Math.fma(this.rY, _t19, -(this.rZ * _t20));
        float _sfx1 = Math.fma(this.rY, _t21, this.rZ * _t22) + Math.fma(this.rW, _t20, -(this.rX * _t19));
        return rotateXYZ_s6e793366_tail(_t20, _t19, _t21, _t22, _sfx0, _sfx1);
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this dual quaternion, returning the result as a
     * value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return the resulting dual quaternion
     */
    public FloatDualQuat rotateZYX(float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        float _sfx0 = Math.fma(this.rX, _t19, this.rW * _t21) + Math.fma(this.rY, _t22, -(this.rZ * _t20));
        float _sfx1 = Math.fma(this.rY, _t19, this.rZ * _t21) + Math.fma(this.rW, _t20, -(this.rX * _t22));
        return rotateXYZ_s6e793366_tail(_t20, _t22, _t19, _t21, _sfx0, _sfx1);
    }


    /**
     * Apply a translation by {@code translation} to this dual quaternion, returning the result as a
     * value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code T} the translation dual quaternion,
     * then the new dual quaternion will be {@code Q * T}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * T * v}, the translation will be applied
     * first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation offsets
     * @return the resulting dual quaternion
     */
    public FloatDualQuat translate(Float3 translation) {
        float _t0 = -this.rZ;
        float _t1 = -this.rX;
        float _t2 = -this.rY;
        float _t3 = 0.5f * translation.z();
        float _t4 = 0.5f * translation.y();
        float _t5 = 0.5f * translation.x();
        return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, Math.fma(this.rY, _t3, Math.fma(_t0, _t4, Math.fma(this.rW, _t5, this.dX))), Math.fma(this.rW, _t4, Math.fma(_t1, _t3, Math.fma(this.rZ, _t5, this.dY))), Math.fma(this.rX, _t4, Math.fma(this.rW, _t3, Math.fma(_t2, _t5, this.dZ))), Math.fma(_t1, _t5, Math.fma(_t2, _t4, Math.fma(_t0, _t3, this.dW))));
    }


    /**
     * Apply a translation by ({@code translationX}, {@code translationY}, {@code translationZ}) to
     * this dual quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code T} the translation dual quaternion,
     * then the new dual quaternion will be {@code Q * T}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * T * v}, the translation will be applied
     * first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat translate(float translationX, float translationY, float translationZ) {
        float _t0 = -this.rZ;
        float _t1 = -this.rX;
        float _t2 = -this.rY;
        float _t3 = 0.5f * translationZ;
        float _t4 = 0.5f * translationY;
        float _t5 = 0.5f * translationX;
        return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, Math.fma(this.rY, _t3, Math.fma(_t0, _t4, Math.fma(this.rW, _t5, this.dX))), Math.fma(this.rW, _t4, Math.fma(_t1, _t3, Math.fma(this.rZ, _t5, this.dY))), Math.fma(this.rX, _t4, Math.fma(this.rW, _t3, Math.fma(_t2, _t5, this.dZ))), Math.fma(_t1, _t5, Math.fma(_t2, _t4, Math.fma(_t0, _t3, this.dW))));
    }


    /**
     * Transform {@code p} by this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @return the resulting vector
     */
    public Float3 transform(Float3 p) {
        return transform(p.x(), p.y(), p.z());
    }

    /** Private tail of {@code transform}; reached only through it. */
    private Float3 transform_s2b62622f_tail(float pZ, float pY, float _t9, float _t10, float pX) {
        float _t11 = 2.0f * Math.fma(pZ, this.rY, -(pY * this.rZ));
        return new Float3(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, Math.fma(2.0f, Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW)), pX)))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, Math.fma(2.0f, Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW)), pY)))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, Math.fma(2.0f, Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)), pZ)))));
    }


    /**
     * Transform ({@code pX}, {@code pY}, {@code pZ}) by this dual quaternion, returning the result
     * as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @return the resulting vector
     */
    public Float3 transform(float pX, float pY, float pZ) {
        return transform_s2b62622f_tail(pZ, pY, 2.0f * Math.fma(pY, this.rX, -(pX * this.rY)), 2.0f * Math.fma(pX, this.rZ, -(pZ * this.rX)), pX);
    }


    /**
     * Transform the given direction by this dual quaternion, ignoring any translation, returning
     * the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the direction to transform
     * @return the resulting vector
     */
    public Float3 transformDirection(Float3 v) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Float3(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ))));
    }


    /**
     * Transform the given direction by this dual quaternion, ignoring any translation, returning
     * the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Float3 transformDirection(float vX, float vY, float vZ) {
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Float3(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ))));
    }


    /**
     * Transform the given direction by the inverse of this dual quaternion's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()}, returning the
     * result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the direction to transform
     * @return the resulting vector
     */
    public Float3 transformDirectionInverse(Float3 v) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        float _t9 = 2.0f * Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Float3(Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX))), Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY))), Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ))));
    }


    /**
     * Transform the given direction by the inverse of this dual quaternion's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()}, returning the
     * result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Float3 transformDirectionInverse(float vX, float vY, float vZ) {
        float _t9 = 2.0f * Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Float3(Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX))), Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY))), Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ))));
    }


    /**
     * Transform {@code p} by the inverse of this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @return the resulting vector
     */
    public Float3 transformInverse(Float3 p) {
        return transformInverse(p.x(), p.y(), p.z());
    }

    /** Private tail of {@code transformInverse}; reached only through it. */
    private Float3 transformInverse_s2b62622f_tail(float _t21, float _t22, float _t23) {
        float _t33 = 2.0f * Math.fma(this.rX, _t21, -(this.rZ * _t22));
        float _t34 = 2.0f * Math.fma(this.rY, _t22, -(this.rX * _t23));
        float _t35 = 2.0f * Math.fma(this.rZ, _t23, -(this.rY * _t21));
        return new Float3(Math.fma(this.rZ, _t33, Math.fma(-this.rY, _t34, Math.fma(this.rW, _t35, _t22))), Math.fma(this.rX, _t34, Math.fma(-this.rZ, _t35, Math.fma(this.rW, _t33, _t23))), Math.fma(this.rY, _t35, Math.fma(-this.rX, _t33, Math.fma(this.rW, _t34, _t21))));
    }


    /**
     * Transform ({@code pX}, {@code pY}, {@code pZ}) by the inverse of this dual quaternion,
     * returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @return the resulting vector
     */
    public Float3 transformInverse(float pX, float pY, float pZ) {
        return transformInverse_s2b62622f_tail(Math.fma(-2.0f, Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)), pZ), Math.fma(-2.0f, Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + Math.fma(this.rW, this.dX, -(this.rX * this.dW)), pX), Math.fma(-2.0f, Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + Math.fma(this.rW, this.dY, -(this.rY * this.dW)), pY));
    }


    /**
     * Transform the given position by this dual quaternion, treating it as a point with an implicit
     * {@code w = 1}, returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @return the resulting vector
     */
    public Float3 transformPosition(Float3 p) {
        return transform(p);
    }


    /**
     * Transform the given position by this dual quaternion, treating it as a point with an implicit
     * {@code w = 1}, returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @return the resulting vector
     */
    public Float3 transformPosition(float pX, float pY, float pZ) {
        return transform(pX, pY, pZ);
    }


    /**
     * Transform the given position by the inverse of this dual quaternion (world to local), without
     * materializing {@code invert()}, returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @return the resulting vector
     */
    public Float3 transformPositionInverse(Float3 p) {
        return transformInverse(p);
    }


    /**
     * Transform the given position by the inverse of this dual quaternion (world to local), without
     * materializing {@code invert()}, returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @return the resulting vector
     */
    public Float3 transformPositionInverse(float pX, float pY, float pZ) {
        return transformInverse(pX, pY, pZ);
    }


    /**
     * Transform the given vector by the rotation part of this dual quaternion, ignoring the
     * translation, returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the vector to transform
     * @return the resulting vector
     */
    public Float3 transformVector(Float3 v) {
        return transformDirection(v);
    }


    /**
     * Transform the given vector by the rotation part of this dual quaternion, ignoring the
     * translation, returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Float3 transformVector(float vX, float vY, float vZ) {
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Float3(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ))));
    }

    /**
     * {@return a copy with the {@code rX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rX} component
     */
    public FloatDualQuat withRX(float v) {
        return new FloatDualQuat(v, rY, rZ, rW, dX, dY, dZ, dW);
    }

    /**
     * {@return a copy with the {@code rY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rY} component
     */
    public FloatDualQuat withRY(float v) {
        return new FloatDualQuat(rX, v, rZ, rW, dX, dY, dZ, dW);
    }

    /**
     * {@return a copy with the {@code rZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rZ} component
     */
    public FloatDualQuat withRZ(float v) {
        return new FloatDualQuat(rX, rY, v, rW, dX, dY, dZ, dW);
    }

    /**
     * {@return a copy with the {@code rW} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rW} component
     */
    public FloatDualQuat withRW(float v) {
        return new FloatDualQuat(rX, rY, rZ, v, dX, dY, dZ, dW);
    }

    /**
     * {@return a copy with the {@code dX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code dX} component
     */
    public FloatDualQuat withDX(float v) {
        return new FloatDualQuat(rX, rY, rZ, rW, v, dY, dZ, dW);
    }

    /**
     * {@return a copy with the {@code dY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code dY} component
     */
    public FloatDualQuat withDY(float v) {
        return new FloatDualQuat(rX, rY, rZ, rW, dX, v, dZ, dW);
    }

    /**
     * {@return a copy with the {@code dZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code dZ} component
     */
    public FloatDualQuat withDZ(float v) {
        return new FloatDualQuat(rX, rY, rZ, rW, dX, dY, v, dW);
    }

    /**
     * {@return a copy with the {@code dW} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code dW} component
     */
    public FloatDualQuat withDW(float v) {
        return new FloatDualQuat(rX, rY, rZ, rW, dX, dY, dZ, v);
    }

    @Override public String toString() {
        return "FloatDualQuat(" + rX() + ", " + rY() + ", " + rZ() + ", " + rW() + ", " + dX() + ", " + dY() + ", " + dZ() + ", " + dW() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatDualQuat)) return false;
        FloatDualQuat o = (FloatDualQuat) obj;
        return Float.floatToIntBits(rX) == Float.floatToIntBits(o.rX)
            && Float.floatToIntBits(rY) == Float.floatToIntBits(o.rY)
            && Float.floatToIntBits(rZ) == Float.floatToIntBits(o.rZ)
            && Float.floatToIntBits(rW) == Float.floatToIntBits(o.rW)
            && Float.floatToIntBits(dX) == Float.floatToIntBits(o.dX)
            && Float.floatToIntBits(dY) == Float.floatToIntBits(o.dY)
            && Float.floatToIntBits(dZ) == Float.floatToIntBits(o.dZ)
            && Float.floatToIntBits(dW) == Float.floatToIntBits(o.dW);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(rX);
        h = 31 * h + Float.floatToIntBits(rY);
        h = 31 * h + Float.floatToIntBits(rZ);
        h = 31 * h + Float.floatToIntBits(rW);
        h = 31 * h + Float.floatToIntBits(dX);
        h = 31 * h + Float.floatToIntBits(dY);
        h = 31 * h + Float.floatToIntBits(dZ);
        h = 31 * h + Float.floatToIntBits(dW);
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Float.isFinite(rX)
            && Float.isFinite(rY)
            && Float.isFinite(rZ)
            && Float.isFinite(rW)
            && Float.isFinite(dX)
            && Float.isFinite(dY)
            && Float.isFinite(dZ)
            && Float.isFinite(dW);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Float.isNaN(rX)
            || Float.isNaN(rY)
            || Float.isNaN(rZ)
            || Float.isNaN(rW)
            || Float.isNaN(dX)
            || Float.isNaN(dY)
            || Float.isNaN(dZ)
            || Float.isNaN(dW);
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
    public boolean equalsEpsilon(FloatDualQuat other, float epsilon) {
        return java.lang.Math.abs(rX - other.rX()) <= epsilon
            && java.lang.Math.abs(rY - other.rY()) <= epsilon
            && java.lang.Math.abs(rZ - other.rZ()) <= epsilon
            && java.lang.Math.abs(rW - other.rW()) <= epsilon
            && java.lang.Math.abs(dX - other.dX()) <= epsilon
            && java.lang.Math.abs(dY - other.dY()) <= epsilon
            && java.lang.Math.abs(dZ - other.dZ()) <= epsilon
            && java.lang.Math.abs(dW - other.dW()) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatDualQuatBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatDualQuatBbOpsUnsafe()
                        : new FloatDualQuatBbOpsApi();
        static final FloatDualQuatRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatDualQuatRawOpsUnsafe()
                        : new FloatDualQuatRawOpsApi();
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
        dest[offset] = this.rX;
        dest[offset + 1] = this.rY;
        dest[offset + 2] = this.rZ;
        dest[offset + 3] = this.rW;
        dest[offset + 4] = this.dX;
        dest[offset + 5] = this.dY;
        dest[offset + 6] = this.dZ;
        dest[offset + 7] = this.dW;
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat load(float[] src, int offset) {
        float _c0 = src[offset];
        float _c1 = src[offset + 1];
        float _c2 = src[offset + 2];
        float _c3 = src[offset + 3];
        float _c4 = src[offset + 4];
        float _c5 = src[offset + 5];
        float _c6 = src[offset + 6];
        float _c7 = src[offset + 7];
        return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat load(float[] src) { return load(src, 0); }

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
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 8);
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat load(FloatBuffer buf) {
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat loadAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatDualQuat loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatDualQuat r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 8);
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat load(ByteBuffer buf) {
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatDualQuat loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatDualQuat r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 32);
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
    public FloatDualQuat storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code FloatDualQuat} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static FloatDualQuat loadUnsafe(long address) {
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
        dest[offset] = this.rX;
        dest[offset + 1] = this.rY;
        dest[offset + 2] = this.rZ;
        dest[offset + 3] = this.rW;
        dest[offset + 4] = this.dX;
        dest[offset + 5] = this.dY;
        dest[offset + 6] = this.dZ;
        dest[offset + 7] = this.dW;
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat load(double[] src, int offset) {
        float _c0 = (float) src[offset];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[offset + 2];
        float _c3 = (float) src[offset + 3];
        float _c4 = (float) src[offset + 4];
        float _c5 = (float) src[offset + 5];
        float _c6 = (float) src[offset + 6];
        float _c7 = (float) src[offset + 7];
        return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    /**
     * Load the elements from the given array, converting each element from {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat load(double[] src) { return load(src, 0); }

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
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 8);
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat load(DoubleBuffer buf) {
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat loadAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatDualQuat loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatDualQuat r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 8);
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
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 64);
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat loadDouble(ByteBuffer buf) {
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat loadDoubleAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatDualQuat loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatDualQuat r = StoreLoad.BB_OPS.loadDoubleAbsolute(pos, buf);
        buf.position(pos + 64);
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
    public FloatDualQuat storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code FloatDualQuat} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static FloatDualQuat loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(address);
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
}
