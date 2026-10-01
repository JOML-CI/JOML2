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
 * Immutable dual quaternion of single-precision {@code float} components.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance.
 * <p>
 * A rigid motion is a unit dual quaternion: a unit real part, and a dual part orthogonal to it. The
 * operations that apply or convert this dual quaternion, and {@code inverseUnit}, assume it and do
 * not divide the real part's length out; {@code invert} is the general inverse of any dual
 * quaternion with a non-zero real part. A value that has drifted from unit length (after many
 * multiplications, say) gives wrong results rather than an error: {@code normalize} it first.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns).
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal to an equal infinity (the difference {@code Inf - Inf} is NaN), and to a finite
 * one only under an infinite {@code epsilon}; a NaN component never compares equal to anything.
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
     * Valid input: the method reads no input.
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

    /**
     * Create an array of {@code length} elements, each {@link #ZERO}.
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
    public static FloatDualQuat[] newArray(int length) {
        FloatDualQuat[] a = new FloatDualQuat[length];
        java.util.Arrays.fill(a, ZERO);
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
    public static FloatDualQuat[] newArray(int length, FloatDualQuat init) {
        java.util.Objects.requireNonNull(init, "init");
        FloatDualQuat[] a = new FloatDualQuat[length];
        java.util.Arrays.fill(a, init);
        return a;
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
        float otherRX = other.rX;
        float otherRY = other.rY;
        float otherRZ = other.rZ;
        float otherRW = other.rW;
        float otherDX = other.dX;
        float otherDY = other.dY;
        float otherDZ = other.dZ;
        float otherDW = other.dW;
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
        float otherRX = other.rX;
        float otherRY = other.rY;
        float otherRZ = other.rZ;
        float otherRW = other.rW;
        float otherDX = other.dX;
        float otherDY = other.dY;
        float otherDZ = other.dZ;
        float otherDW = other.dW;
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
     * Return a dual quaternion with the values of {@code v}: that dual quaternion itself (an
     * immutable value needs no copy).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the dual quaternion to copy
     * @return the resulting dual quaternion
     */
    public FloatDualQuat set(FloatDualQuat v) {
        float vRX = v.rX;
        float vRY = v.rY;
        float vRZ = v.rZ;
        float vRW = v.rW;
        float vDX = v.dX;
        float vDY = v.dY;
        float vDZ = v.dZ;
        float vDW = v.dW;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = -rTZ;
            return new FloatDualQuat(rRX, rRY, rRZ, rRW, 0.5f * java.lang.Math.fma(_t0, rRY, java.lang.Math.fma(rTX, rRW, rTY * rRZ)), 0.5f * java.lang.Math.fma(rTZ, rRX, java.lang.Math.fma(rTY, rRW, -rTX * rRZ)), 0.5f * java.lang.Math.fma(rTZ, rRW, java.lang.Math.fma(rTX, rRY, -rTY * rRX)), 0.5f * java.lang.Math.fma(_t0, rRZ, java.lang.Math.fma(-rTY, rRY, -rTX * rRX)));
        } else {
            float _t0 = -rTZ;
            return new FloatDualQuat(rRX, rRY, rRZ, rRW, 0.5f * ((_t0) * (rRY) + (((rTX) * (rRW) + (rTY * rRZ)))), 0.5f * ((rTZ) * (rRX) + (((rTY) * (rRW) - (rTX * rRZ)))), 0.5f * ((rTZ) * (rRW) + (((rTX) * (rRY) - (rTY * rRX)))), 0.5f * ((_t0) * (rRZ) + (((-rTY) * (rRY) - (rTX * rRX)))));
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = -rTZ;
            return new FloatDualQuat(rRX, rRY, rRZ, rRW, 0.5f * java.lang.Math.fma(_t0, rRY, java.lang.Math.fma(rTX, rRW, rTY * rRZ)), 0.5f * java.lang.Math.fma(rTZ, rRX, java.lang.Math.fma(rTY, rRW, -rTX * rRZ)), 0.5f * java.lang.Math.fma(rTZ, rRW, java.lang.Math.fma(rTX, rRY, -rTY * rRX)), 0.5f * java.lang.Math.fma(_t0, rRZ, java.lang.Math.fma(-rTY, rRY, -rTX * rRX)));
        } else {
            float _t0 = -rTZ;
            return new FloatDualQuat(rRX, rRY, rRZ, rRW, 0.5f * ((_t0) * (rRY) + (((rTX) * (rRW) + (rTY * rRZ)))), 0.5f * ((rTZ) * (rRX) + (((rTY) * (rRW) - (rTX * rRZ)))), 0.5f * ((rTZ) * (rRW) + (((rTX) * (rRY) - (rTY * rRX)))), 0.5f * ((_t0) * (rRZ) + (((-rTY) * (rRY) - (rTX * rRX)))));
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = -tTZ;
            return new FloatDualQuat(tRX, tRY, tRZ, tRW, 0.5f * java.lang.Math.fma(_t0, tRY, java.lang.Math.fma(tTX, tRW, tTY * tRZ)), 0.5f * java.lang.Math.fma(tTZ, tRX, java.lang.Math.fma(tTY, tRW, -tTX * tRZ)), 0.5f * java.lang.Math.fma(tTZ, tRW, java.lang.Math.fma(tTX, tRY, -tTY * tRX)), 0.5f * java.lang.Math.fma(_t0, tRZ, java.lang.Math.fma(-tTY, tRY, -tTX * tRX)));
        } else {
            float _t0 = -tTZ;
            return new FloatDualQuat(tRX, tRY, tRZ, tRW, 0.5f * ((_t0) * (tRY) + (((tTX) * (tRW) + (tTY * tRZ)))), 0.5f * ((tTZ) * (tRX) + (((tTY) * (tRW) - (tTX * tRZ)))), 0.5f * ((tTZ) * (tRW) + (((tTX) * (tRY) - (tTY * tRX)))), 0.5f * ((_t0) * (tRZ) + (((-tTY) * (tRY) - (tTX * tRX)))));
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = -tTZ;
            return new FloatDualQuat(tRX, tRY, tRZ, tRW, 0.5f * java.lang.Math.fma(_t0, tRY, java.lang.Math.fma(tTX, tRW, tTY * tRZ)), 0.5f * java.lang.Math.fma(tTZ, tRX, java.lang.Math.fma(tTY, tRW, -tTX * tRZ)), 0.5f * java.lang.Math.fma(tTZ, tRW, java.lang.Math.fma(tTX, tRY, -tTY * tRX)), 0.5f * java.lang.Math.fma(_t0, tRZ, java.lang.Math.fma(-tTY, tRY, -tTX * tRX)));
        } else {
            float _t0 = -tTZ;
            return new FloatDualQuat(tRX, tRY, tRZ, tRW, 0.5f * ((_t0) * (tRY) + (((tTX) * (tRW) + (tTY * tRZ)))), 0.5f * ((tTZ) * (tRX) + (((tTY) * (tRW) - (tTX * tRZ)))), 0.5f * ((tTZ) * (tRW) + (((tTX) * (tRY) - (tTY * tRX)))), 0.5f * ((_t0) * (tRZ) + (((-tTY) * (tRY) - (tTX * tRX)))));
        }
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
        if (!org.joml2.internal.FmaSwitch.ON) return new FloatRigid(2.0f * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW))), 2.0f * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW))), 2.0f * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW))), this.rX, this.rY, this.rZ, this.rW);
        return new FloatRigid(2.0f * (java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW)), 2.0f * (java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW)), 2.0f * (java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, this.rZ * -this.dW)), this.rX, this.rY, this.rZ, this.rW);
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
        if (!org.joml2.internal.FmaSwitch.ON) return new FloatTransform(2.0f * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW))), 2.0f * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW))), 2.0f * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW))), this.rX, this.rY, this.rZ, this.rW, 1.0f, 1.0f, 1.0f);
        return new FloatTransform(2.0f * (java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW)), 2.0f * (java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW)), 2.0f * (java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, this.rZ * -this.dW)), this.rX, this.rY, this.rZ, this.rW, 1.0f, 1.0f, 1.0f);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            return java.lang.Math.abs(java.lang.Math.fma(this.rX, this.rX, java.lang.Math.fma(this.rY, this.rY, java.lang.Math.fma(this.rZ, this.rZ, java.lang.Math.fma(this.rW, this.rW, -1.0f))))) <= epsilon;
        } else {
            return java.lang.Math.abs(((this.rX) * (this.rX) + (((this.rY) * (this.rY) + (((this.rZ) * (this.rZ) + (((this.rW) * (this.rW) - (1.0f))))))))) <= epsilon;
        }
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
        return makeFromAxisAngle(axis.x(), axis.y(), axis.z(), angle, translation.x(), translation.y(), translation.z());
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = 0.5f * angle;
            float _t1 = -translationZ;
            float _t2 = Math.sinWithCos(_t0);
            float _t3 = Math.cosWithSin(_t0);
            float _t4 = axisX * _t2;
            float _t5 = axisY * _t2;
            float _t6 = axisZ * _t2;
            return new FloatDualQuat(_t4, _t5, _t6, _t3, 0.5f * java.lang.Math.fma(_t1, _t5, java.lang.Math.fma(translationX, _t3, translationY * _t6)), 0.5f * java.lang.Math.fma(translationZ, _t4, java.lang.Math.fma(translationY, _t3, -translationX * _t6)), 0.5f * java.lang.Math.fma(translationZ, _t3, java.lang.Math.fma(translationX, _t5, -translationY * _t4)), 0.5f * java.lang.Math.fma(_t1, _t6, java.lang.Math.fma(-translationY, _t5, -translationX * _t4)));
        } else {
            float _t0 = 0.5f * angle;
            float _t1 = -translationZ;
            float _t2 = Math.sinWithCos(_t0);
            float _t3 = Math.cosWithSin(_t0);
            float _t4 = axisX * _t2;
            float _t5 = axisY * _t2;
            float _t6 = axisZ * _t2;
            return new FloatDualQuat(_t4, _t5, _t6, _t3, 0.5f * ((_t1) * (_t5) + (((translationX) * (_t3) + (translationY * _t6)))), 0.5f * ((translationZ) * (_t4) + (((translationY) * (_t3) - (translationX * _t6)))), 0.5f * ((translationZ) * (_t3) + (((translationX) * (_t5) - (translationY * _t4)))), 0.5f * ((_t1) * (_t6) + (((-translationY) * (_t5) - (translationX * _t4)))));
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = -rotationY;
            return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX)), 0.5f * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -rotationZ * translationX)), 0.5f * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -rotationX * translationY)), 0.5f * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -rotationX * translationX)));
        } else {
            float _t0 = -rotationY;
            return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * ((_t0) * (translationZ) + (((rotationZ) * (translationY) + (rotationW * translationX)))), 0.5f * ((rotationX) * (translationZ) + (((rotationW) * (translationY) - (rotationZ * translationX)))), 0.5f * ((rotationW) * (translationZ) + (((rotationY) * (translationX) - (rotationX * translationY)))), 0.5f * ((((_t0) * (translationY) - (rotationX * translationX))) - (rotationZ) * (translationZ)));
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = -rotationY;
            return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX)), 0.5f * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -rotationZ * translationX)), 0.5f * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -rotationX * translationY)), 0.5f * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -rotationX * translationX)));
        } else {
            float _t0 = -rotationY;
            return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * ((_t0) * (translationZ) + (((rotationZ) * (translationY) + (rotationW * translationX)))), 0.5f * ((rotationX) * (translationZ) + (((rotationW) * (translationY) - (rotationZ * translationX)))), 0.5f * ((rotationW) * (translationZ) + (((rotationY) * (translationX) - (rotationX * translationY)))), 0.5f * ((((_t0) * (translationY) - (rotationX * translationX))) - (rotationZ) * (translationZ)));
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = -rotationY;
            return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX)), 0.5f * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -rotationZ * translationX)), 0.5f * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -rotationX * translationY)), 0.5f * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -rotationX * translationX)));
        } else {
            float _t0 = -rotationY;
            return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * ((_t0) * (translationZ) + (((rotationZ) * (translationY) + (rotationW * translationX)))), 0.5f * ((rotationX) * (translationZ) + (((rotationW) * (translationY) - (rotationZ * translationX)))), 0.5f * ((rotationW) * (translationZ) + (((rotationY) * (translationX) - (rotationX * translationY)))), 0.5f * ((((_t0) * (translationY) - (rotationX * translationX))) - (rotationZ) * (translationZ)));
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = -rotationY;
            return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX)), 0.5f * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -rotationZ * translationX)), 0.5f * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -rotationX * translationY)), 0.5f * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -rotationX * translationX)));
        } else {
            float _t0 = -rotationY;
            return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * ((_t0) * (translationZ) + (((rotationZ) * (translationY) + (rotationW * translationX)))), 0.5f * ((rotationX) * (translationZ) + (((rotationW) * (translationY) - (rotationZ * translationX)))), 0.5f * ((rotationW) * (translationZ) + (((rotationY) * (translationX) - (rotationX * translationY)))), 0.5f * ((((_t0) * (translationY) - (rotationX * translationX))) - (rotationZ) * (translationZ)));
        }
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
        return dlb(other.rX, other.rY, other.rZ, other.rW, other.dX, other.dY, other.dZ, other.dW, t);
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
        if (!org.joml2.internal.FmaSwitch.ON) return dlb_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t);
        float _t0 = 1.0f - t;
        float _t11 = t * (java.lang.Math.fma(otherRX, this.rX, otherRY * this.rY) + java.lang.Math.fma(otherRZ, this.rZ, otherRW * this.rW) < 0.0f ? -1.0f : 1.0f);
        float _t12 = java.lang.Math.fma(otherRX, _t11, this.rX * _t0);
        float _t13 = java.lang.Math.fma(otherRY, _t11, this.rY * _t0);
        float _t14 = java.lang.Math.fma(otherRZ, _t11, this.rZ * _t0);
        float _t15 = java.lang.Math.fma(otherRW, _t11, this.rW * _t0);
        float _t21 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t12, _t12, _t13 * _t13) + java.lang.Math.fma(_t14, _t14, _t15 * _t15)));
        return new FloatDualQuat(_t12 * _t21, _t13 * _t21, _t14 * _t21, _t15 * _t21, java.lang.Math.fma(otherDX, _t11, this.dX * _t0) * _t21, java.lang.Math.fma(otherDY, _t11, this.dY * _t0) * _t21, java.lang.Math.fma(otherDZ, _t11, this.dZ * _t0) * _t21, java.lang.Math.fma(otherDW, _t11, this.dW * _t0) * _t21);
    }

    /** {@code dlb} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat dlb_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t) {
        float _t0 = 1.0f - t;
        float _t11 = t * (((otherRX) * (this.rX) + (otherRY * this.rY)) + ((otherRZ) * (this.rZ) + (otherRW * this.rW)) < 0.0f ? -1.0f : 1.0f);
        float _t12 = ((otherRX) * (_t11) + (this.rX * _t0));
        float _t13 = ((otherRY) * (_t11) + (this.rY * _t0));
        float _t14 = ((otherRZ) * (_t11) + (this.rZ * _t0));
        float _t15 = ((otherRW) * (_t11) + (this.rW * _t0));
        float _t21 = (1.0f / (float) java.lang.Math.sqrt(((_t12) * (_t12) + (_t13 * _t13)) + ((_t14) * (_t14) + (_t15 * _t15))));
        return new FloatDualQuat(_t12 * _t21, _t13 * _t21, _t14 * _t21, _t15 * _t21, ((otherDX) * (_t11) + (this.dX * _t0)) * _t21, ((otherDY) * (_t11) + (this.dY * _t0)) * _t21, ((otherDZ) * (_t11) + (this.dZ * _t0)) * _t21, ((otherDW) * (_t11) + (this.dW * _t0)) * _t21);
    }

    /**
     * Linearly interpolate between this dual quaternion and {@code other} using the interpolation
     * factor {@code t}, returning the result as a value.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (1e8 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat lerp(FloatDualQuat other, float t) {
        return lerp(other.rX, other.rY, other.rZ, other.rW, other.dX, other.dY, other.dZ, other.dW, t);
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
     * than the other one (1e8 towards 1 ends at 0).
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
        if (org.joml2.internal.FmaSwitch.ON) {
            return new FloatDualQuat(java.lang.Math.fma(t, otherRX - this.rX, this.rX), java.lang.Math.fma(t, otherRY - this.rY, this.rY), java.lang.Math.fma(t, otherRZ - this.rZ, this.rZ), java.lang.Math.fma(t, otherRW - this.rW, this.rW), java.lang.Math.fma(t, otherDX - this.dX, this.dX), java.lang.Math.fma(t, otherDY - this.dY, this.dY), java.lang.Math.fma(t, otherDZ - this.dZ, this.dZ), java.lang.Math.fma(t, otherDW - this.dW, this.dW));
        } else {
            return new FloatDualQuat(((t) * (otherRX - this.rX) + (this.rX)), ((t) * (otherRY - this.rY) + (this.rY)), ((t) * (otherRZ - this.rZ) + (this.rZ)), ((t) * (otherRW - this.rW) + (this.rW)), ((t) * (otherDX - this.dX) + (this.dX)), ((t) * (otherDY - this.dY) + (this.dY)), ((t) * (otherDZ - this.dZ) + (this.dZ)), ((t) * (otherDW - this.dW) + (this.dW)));
        }
    }

    /**
     * Screw-linearly interpolate between this dual quaternion and {@code other} using the
     * interpolation factor {@code t}, returning the result as a value.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code other} must be a unit dual quaternion; this dual quaternion must be a
     * unit dual quaternion; {@code t} must lie in {@code [0, 1]}.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat sclerp(FloatDualQuat other, float t) {
        return sclerp(other.rX, other.rY, other.rZ, other.rW, other.dX, other.dY, other.dZ, other.dW, t);
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
     * must be a unit dual quaternion; this dual quaternion must be a unit dual quaternion;
     * {@code t} must lie in {@code [0, 1]}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return sclerp_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t);
        float _t0 = -this.rZ;
        float[] _bundle0 = sclerp_s2fc1b0dc_1_fma(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t);
        float _t5 = t < 0.5f ? 1.0f : -1.0f;
        float _t12 = otherRX * _bundle0[0];
        float _t13 = otherRW * _bundle0[0];
        float _t14 = otherRZ * _bundle0[0];
        float _t15 = otherRY * _bundle0[0];
        float _t68 = java.lang.Math.fma(this.rX, _t12, this.rW * _t13);
        float _t88 = java.lang.Math.fma(_t0, _t14, -this.rY * _t15);
        return sclerp_s2fc1b0dc_5_fma(t, _t0, _bundle0, _t5, _t12, _t13, _t14, _t15, otherDX * _bundle0[0], otherDW * _bundle0[0], otherDY * _bundle0[0], otherDZ * _bundle0[0], _t68, _t88, _t68 - _t88, (java.lang.Math.fma(this.rY, _t12, -this.rZ * _t13) + java.lang.Math.fma(this.rW, _t14, -(this.rX * _t15))) * _t5, (java.lang.Math.fma(this.rX, _t14, this.rW * _t15) + java.lang.Math.fma(_t0, _t12, -this.rY * _t13)) * _t5, (java.lang.Math.fma(this.rZ, _t15, -this.rY * _t14) + java.lang.Math.fma(this.rW, _t12, -(this.rX * _t13))) * _t5);
    }

    /** {@code sclerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat sclerp_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t) {
        float _t0 = -this.rZ;
        float[] _bundle0 = sclerp_s2fc1b0dc_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t);
        float _t5 = t < 0.5f ? 1.0f : -1.0f;
        float _t12 = otherRX * _bundle0[0];
        float _t13 = otherRW * _bundle0[0];
        float _t14 = otherRZ * _bundle0[0];
        float _t15 = otherRY * _bundle0[0];
        float _t68 = ((this.rX) * (_t12) + (this.rW * _t13));
        float _t88 = ((_t0) * (_t14) - (this.rY * _t15));
        return sclerp_s2fc1b0dc_5_mulAdd(t, _t0, _bundle0, _t5, _t12, _t13, _t14, _t15, otherDX * _bundle0[0], otherDW * _bundle0[0], otherDY * _bundle0[0], otherDZ * _bundle0[0], _t68, _t88, _t68 - _t88, (((this.rY) * (_t12) - (this.rZ * _t13)) + ((this.rW) * (_t14) - (this.rX * _t15))) * _t5, (((this.rX) * (_t14) + (this.rW * _t15)) + ((_t0) * (_t12) - (this.rY * _t13))) * _t5, (((this.rZ) * (_t15) - (this.rY * _t14)) + ((this.rW) * (_t12) - (this.rX * _t13))) * _t5);
    }

    /** Part 1 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private float[] sclerp_s2fc1b0dc_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t) {
        float _t11 = java.lang.Math.fma(otherRX, this.rX, otherRY * this.rY) + java.lang.Math.fma(otherRZ, this.rZ, otherRW * this.rW) < 0.0f ? -1.0f : 1.0f;
        float _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67;
        if (t < 0.5f) {
            _t60 = this.rX;
            _t61 = this.rW;
            _t62 = this.rY;
            _t63 = this.rZ;
            _t64 = this.dX;
            _t65 = this.dW;
            _t66 = this.dY;
            _t67 = this.dZ;
        } else {
            _t60 = (otherRX * _t11);
            _t61 = (otherRW * _t11);
            _t62 = (otherRY * _t11);
            _t63 = (otherRZ * _t11);
            _t64 = (otherDX * _t11);
            _t65 = (otherDW * _t11);
            _t66 = (otherDY * _t11);
            _t67 = (otherDZ * _t11);
        }
        return new float[] {_t11, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67};
    }

    /** Part 1 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private float[] sclerp_s2fc1b0dc_1_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW, float t) {
        float _t11 = ((otherRX) * (this.rX) + (otherRY * this.rY)) + ((otherRZ) * (this.rZ) + (otherRW * this.rW)) < 0.0f ? -1.0f : 1.0f;
        float _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67;
        if (t < 0.5f) {
            _t60 = this.rX;
            _t61 = this.rW;
            _t62 = this.rY;
            _t63 = this.rZ;
            _t64 = this.dX;
            _t65 = this.dW;
            _t66 = this.dY;
            _t67 = this.dZ;
        } else {
            _t60 = (otherRX * _t11);
            _t61 = (otherRW * _t11);
            _t62 = (otherRY * _t11);
            _t63 = (otherRZ * _t11);
            _t64 = (otherDX * _t11);
            _t65 = (otherDW * _t11);
            _t66 = (otherDY * _t11);
            _t67 = (otherDZ * _t11);
        }
        return new float[] {_t11, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67};
    }

    /** Part 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private float[] sclerp_s2fc1b0dc_2_fma(float t, float _t123, float _t124, float _t125, float _t122, float _t129) {
        float _t6 = Math.min(t, 1.0f - t);
        float _t139 = java.lang.Math.fma(_t123, _t123, java.lang.Math.fma(_t124, _t124, _t125 * _t125));
        float _t141 = (1.0f / (float) java.lang.Math.sqrt(_t139));
        float _t143 = Math.atan2((float) java.lang.Math.sqrt(_t139), _t122) * _t6;
        float _t145 = Math.sinWithCos(_t143);
        float _t146 = _t141 * _t125;
        float _t147 = _t141 * _t123;
        float _t148 = _t141 * _t124;
        float _t149 = _t141 * _t129;
        float _t150 = _t149 * _t6;
        float _t152, _t161, _t162, _t163, _t170;
        if (_t139 < 1.0E-12f) {
            _t152 = 1.0f;
            _t161 = _t6 * _t125;
            _t162 = _t6 * _t123;
            _t163 = _t6 * _t124;
            _t170 = _t6 * _t6 * _t129;
        } else {
            _t152 = (Math.cosWithSin(_t143));
            _t161 = _t146 * _t145;
            _t162 = _t147 * _t145;
            _t163 = _t148 * _t145;
            _t170 = _t150 * _t145;
        }
        return new float[] {_t139, _t143, _t146, _t147, _t148, _t149, _t150, _t152, _t161, _t162, _t163, _t170};
    }

    /** Part 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private float[] sclerp_s2fc1b0dc_2_mulAdd(float t, float _t123, float _t124, float _t125, float _t122, float _t129) {
        float _t6 = Math.min(t, 1.0f - t);
        float _t139 = ((_t123) * (_t123) + (((_t124) * (_t124) + (_t125 * _t125))));
        float _t141 = (1.0f / (float) java.lang.Math.sqrt(_t139));
        float _t143 = Math.atan2((float) java.lang.Math.sqrt(_t139), _t122) * _t6;
        float _t145 = Math.sinWithCos(_t143);
        float _t146 = _t141 * _t125;
        float _t147 = _t141 * _t123;
        float _t148 = _t141 * _t124;
        float _t149 = _t141 * _t129;
        float _t150 = _t149 * _t6;
        float _t152, _t161, _t162, _t163, _t170;
        if (_t139 < 1.0E-12f) {
            _t152 = 1.0f;
            _t161 = _t6 * _t125;
            _t162 = _t6 * _t123;
            _t163 = _t6 * _t124;
            _t170 = _t6 * _t6 * _t129;
        } else {
            _t152 = (Math.cosWithSin(_t143));
            _t161 = _t146 * _t145;
            _t162 = _t147 * _t145;
            _t163 = _t148 * _t145;
            _t170 = _t150 * _t145;
        }
        return new float[] {_t139, _t143, _t146, _t147, _t148, _t149, _t150, _t152, _t161, _t162, _t163, _t170};
    }

    /** Part 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private float[] sclerp_s2fc1b0dc_3_fma(float t, float _t60, float _t61, float _t62, float _t63, float _t131, float _t132, float _t133, float _t122, float _t139, float _t143, float _t146, float _t147, float _t148, float _t149, float _t150, float _t152, float _t161, float _t162, float _t163) {
        float _t6 = Math.min(t, 1.0f - t);
        float _t141 = (1.0f / (float) java.lang.Math.sqrt(_t139));
        float _t145 = Math.sinWithCos(_t143);
        float _t151 = _t149 * _t122;
        float _t157 = _t150 * (Math.cosWithSin(_t143));
        float _t177, _t178, _t179;
        if (_t139 < 1.0E-12f) {
            _t177 = _t6 * _t131;
            _t178 = _t6 * _t132;
            _t179 = _t6 * _t133;
        } else {
            _t177 = java.lang.Math.fma(java.lang.Math.fma(_t151, _t146, _t131) * _t141, _t145, -(_t157 * _t146));
            _t178 = java.lang.Math.fma(java.lang.Math.fma(_t151, _t147, _t132) * _t141, _t145, -(_t157 * _t147));
            _t179 = java.lang.Math.fma(java.lang.Math.fma(_t151, _t148, _t133) * _t141, _t145, -(_t157 * _t148));
        }
        return new float[] {_t177, _t178, _t179, java.lang.Math.fma(_t60, _t152, _t61 * _t161) + java.lang.Math.fma(_t62, _t162, -_t63 * _t163), java.lang.Math.fma(_t62, _t152, _t63 * _t161) + java.lang.Math.fma(_t61, _t163, -_t60 * _t162), java.lang.Math.fma(_t60, _t163, _t61 * _t162) + java.lang.Math.fma(_t63, _t152, -_t62 * _t161)};
    }

    /** Part 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private float[] sclerp_s2fc1b0dc_3_mulAdd(float t, float _t60, float _t61, float _t62, float _t63, float _t131, float _t132, float _t133, float _t122, float _t139, float _t143, float _t146, float _t147, float _t148, float _t149, float _t150, float _t152, float _t161, float _t162, float _t163) {
        float _t6 = Math.min(t, 1.0f - t);
        float _t141 = (1.0f / (float) java.lang.Math.sqrt(_t139));
        float _t145 = Math.sinWithCos(_t143);
        float _t151 = _t149 * _t122;
        float _t157 = _t150 * (Math.cosWithSin(_t143));
        float _t177, _t178, _t179;
        if (_t139 < 1.0E-12f) {
            _t177 = _t6 * _t131;
            _t178 = _t6 * _t132;
            _t179 = _t6 * _t133;
        } else {
            _t177 = ((((_t151) * (_t146) + (_t131)) * _t141) * (_t145) - (_t157 * _t146));
            _t178 = ((((_t151) * (_t147) + (_t132)) * _t141) * (_t145) - (_t157 * _t147));
            _t179 = ((((_t151) * (_t148) + (_t133)) * _t141) * (_t145) - (_t157 * _t148));
        }
        return new float[] {_t177, _t178, _t179, ((_t60) * (_t152) + (_t61 * _t161)) + ((_t62) * (_t162) - (_t63 * _t163)), ((_t62) * (_t152) + (_t63 * _t161)) + ((_t61) * (_t163) - (_t60 * _t162)), ((_t60) * (_t163) + (_t61 * _t162)) + ((_t63) * (_t152) - (_t62 * _t161))};
    }

    /** Part 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_s2fc1b0dc_4_fma(float _t61, float _t60, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t152, float _t161, float _t163, float _t162, float _t170, float _t177, float _t178, float _t179, float _sfx0, float _sfx1, float _sfx2) {
        float _sfx3 = java.lang.Math.fma(_t61, _t152, -_t60 * _t161) - java.lang.Math.fma(_t62, _t163, _t63 * _t162);
        float _sfx4 = java.lang.Math.fma(_t60, _t170, _t61 * _t177) + java.lang.Math.fma(_t62, _t178, -_t63 * _t179) + (java.lang.Math.fma(_t64, _t152, _t65 * _t161) + java.lang.Math.fma(_t66, _t162, -_t67 * _t163));
        float _sfx5 = java.lang.Math.fma(_t62, _t170, _t63 * _t177) + java.lang.Math.fma(_t61, _t179, -_t60 * _t178) + (java.lang.Math.fma(_t66, _t152, _t67 * _t161) + java.lang.Math.fma(_t65, _t163, -_t64 * _t162));
        float _sfx6 = java.lang.Math.fma(_t60, _t179, _t61 * _t178) + java.lang.Math.fma(_t63, _t170, -_t62 * _t177) + (java.lang.Math.fma(_t64, _t163, _t65 * _t162) + java.lang.Math.fma(_t67, _t152, -_t66 * _t161));
        float _sfx7 = java.lang.Math.fma(_t61, _t170, -_t60 * _t177) + java.lang.Math.fma(-_t63, _t178, -_t62 * _t179) + (java.lang.Math.fma(_t65, _t152, -_t64 * _t161) + java.lang.Math.fma(-_t67, _t162, -_t66 * _t163));
        return new FloatDualQuat((_sfx0), (_sfx1), (_sfx2), (_sfx3), (_sfx4), (_sfx5), (_sfx6), (_sfx7));
    }

    /** Part 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_s2fc1b0dc_4_mulAdd(float _t61, float _t60, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t152, float _t161, float _t163, float _t162, float _t170, float _t177, float _t178, float _t179, float _sfx0, float _sfx1, float _sfx2) {
        float _sfx3 = ((_t61) * (_t152) - (_t60 * _t161)) - ((_t62) * (_t163) + (_t63 * _t162));
        float _sfx4 = ((_t60) * (_t170) + (_t61 * _t177)) + ((_t62) * (_t178) - (_t63 * _t179)) + (((_t64) * (_t152) + (_t65 * _t161)) + ((_t66) * (_t162) - (_t67 * _t163)));
        float _sfx5 = ((_t62) * (_t170) + (_t63 * _t177)) + ((_t61) * (_t179) - (_t60 * _t178)) + (((_t66) * (_t152) + (_t67 * _t161)) + ((_t65) * (_t163) - (_t64 * _t162)));
        float _sfx6 = ((_t60) * (_t179) + (_t61 * _t178)) + ((_t63) * (_t170) - (_t62 * _t177)) + (((_t64) * (_t163) + (_t65 * _t162)) + ((_t67) * (_t152) - (_t66 * _t161)));
        float _sfx7 = ((_t61) * (_t170) - (_t60 * _t177)) + ((-_t63) * (_t178) - (_t62 * _t179)) + (((_t65) * (_t152) - (_t64 * _t161)) + ((-_t67) * (_t162) - (_t66 * _t163)));
        return new FloatDualQuat((_sfx0), (_sfx1), (_sfx2), (_sfx3), (_sfx4), (_sfx5), (_sfx6), (_sfx7));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_s2fc1b0dc_5_fma(float t, float _t0, float[] _bundle0, float _t5, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t68, float _t88, float _t94, float _t105, float _t106, float _t107) {
        return sclerp_s2fc1b0dc_6_fma(t, _bundle0, _t68, _t88, _t94, _t105, _t106, _t107, java.lang.Math.fma(this.rX, _t16, this.rW * _t17) + java.lang.Math.fma(this.rY, _t18, this.rZ * _t19) + (java.lang.Math.fma(this.dX, _t12, this.dW * _t13) + java.lang.Math.fma(this.dY, _t15, this.dZ * _t14)), (java.lang.Math.fma(this.rZ, _t18, -(this.rY * _t19)) + java.lang.Math.fma(this.rW, _t16, -this.rX * _t17) + (java.lang.Math.fma(this.dZ, _t15, -this.dY * _t14) + java.lang.Math.fma(this.dW, _t12, -this.dX * _t13))) * _t5, (java.lang.Math.fma(this.rY, _t16, -(this.rZ * _t17)) + java.lang.Math.fma(this.rW, _t19, -this.rX * _t18) + (java.lang.Math.fma(this.dY, _t12, -this.dZ * _t13) + java.lang.Math.fma(this.dW, _t14, -this.dX * _t15))) * _t5, (java.lang.Math.fma(this.rX, _t19, this.rW * _t18) + java.lang.Math.fma(_t0, _t16, -(this.rY * _t17)) + (java.lang.Math.fma(this.dX, _t14, this.dW * _t15) + java.lang.Math.fma(-this.dZ, _t12, -this.dY * _t13))) * _t5);
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_s2fc1b0dc_5_mulAdd(float t, float _t0, float[] _bundle0, float _t5, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t68, float _t88, float _t94, float _t105, float _t106, float _t107) {
        return sclerp_s2fc1b0dc_6_mulAdd(t, _bundle0, _t68, _t88, _t94, _t105, _t106, _t107, ((this.rX) * (_t16) + (this.rW * _t17)) + ((this.rY) * (_t18) + (this.rZ * _t19)) + (((this.dX) * (_t12) + (this.dW * _t13)) + ((this.dY) * (_t15) + (this.dZ * _t14))), (((this.rZ) * (_t18) - (this.rY * _t19)) + ((this.rW) * (_t16) - (this.rX * _t17)) + (((this.dZ) * (_t15) - (this.dY * _t14)) + ((this.dW) * (_t12) - (this.dX * _t13)))) * _t5, (((this.rY) * (_t16) - (this.rZ * _t17)) + ((this.rW) * (_t19) - (this.rX * _t18)) + (((this.dY) * (_t12) - (this.dZ * _t13)) + ((this.dW) * (_t14) - (this.dX * _t15)))) * _t5, (((this.rX) * (_t19) + (this.rW * _t18)) + ((_t0) * (_t16) - (this.rY * _t17)) + (((this.dX) * (_t14) + (this.dW * _t15)) + ((-this.dZ) * (_t12) - (this.dY * _t13)))) * _t5);
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_s2fc1b0dc_6_fma(float t, float[] _bundle0, float _t68, float _t88, float _t94, float _t105, float _t106, float _t107, float _t111, float _t116, float _t117, float _t118) {
        float _t122, _t123, _t124, _t125, _t129, _t131, _t132, _t133;
        if (_t94 < 0.0f) {
            _t122 = _t88 - _t68;
            _t123 = -_t105;
            _t124 = -_t106;
            _t125 = -_t107;
            _t129 = -_t111;
            _t131 = -_t116;
            _t132 = -_t117;
            _t133 = -_t118;
        } else {
            _t122 = _t94;
            _t123 = _t105;
            _t124 = _t106;
            _t125 = _t107;
            _t129 = _t111;
            _t131 = _t116;
            _t132 = _t117;
            _t133 = _t118;
        }
        float[] _bundle1 = sclerp_s2fc1b0dc_2_fma(t, _t123, _t124, _t125, _t122, _t129);
        float[] _bundle2 = sclerp_s2fc1b0dc_3_fma(t, _bundle0[1], _bundle0[2], _bundle0[3], _bundle0[4], _t131, _t132, _t133, _t122, _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle1[4], _bundle1[5], _bundle1[6], _bundle1[7], _bundle1[8], _bundle1[9], _bundle1[10]);
        return sclerp_s2fc1b0dc_4_fma(_bundle0[2], _bundle0[1], _bundle0[3], _bundle0[4], _bundle0[5], _bundle0[6], _bundle0[7], _bundle0[8], _bundle1[7], _bundle1[8], _bundle1[10], _bundle1[9], _bundle1[11], _bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3], _bundle2[4], _bundle2[5]);
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat sclerp_s2fc1b0dc_6_mulAdd(float t, float[] _bundle0, float _t68, float _t88, float _t94, float _t105, float _t106, float _t107, float _t111, float _t116, float _t117, float _t118) {
        float _t122, _t123, _t124, _t125, _t129, _t131, _t132, _t133;
        if (_t94 < 0.0f) {
            _t122 = _t88 - _t68;
            _t123 = -_t105;
            _t124 = -_t106;
            _t125 = -_t107;
            _t129 = -_t111;
            _t131 = -_t116;
            _t132 = -_t117;
            _t133 = -_t118;
        } else {
            _t122 = _t94;
            _t123 = _t105;
            _t124 = _t106;
            _t125 = _t107;
            _t129 = _t111;
            _t131 = _t116;
            _t132 = _t117;
            _t133 = _t118;
        }
        float[] _bundle1 = sclerp_s2fc1b0dc_2_mulAdd(t, _t123, _t124, _t125, _t122, _t129);
        float[] _bundle2 = sclerp_s2fc1b0dc_3_mulAdd(t, _bundle0[1], _bundle0[2], _bundle0[3], _bundle0[4], _t131, _t132, _t133, _t122, _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle1[4], _bundle1[5], _bundle1[6], _bundle1[7], _bundle1[8], _bundle1[9], _bundle1[10]);
        return sclerp_s2fc1b0dc_4_mulAdd(_bundle0[2], _bundle0[1], _bundle0[3], _bundle0[4], _bundle0[5], _bundle0[6], _bundle0[7], _bundle0[8], _bundle1[7], _bundle1[8], _bundle1[10], _bundle1[9], _bundle1[11], _bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3], _bundle2[4], _bundle2[5]);
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
        return mul(other.rX, other.rY, other.rZ, other.rW, other.dX, other.dY, other.dZ, other.dW);
    }

    /** Private tail of {@code mul}; reached only through it. */
    private FloatDualQuat mul_s401e608_tail_fma(float otherRX, float otherRW, float otherRY, float otherRZ, float otherDX, float otherDW, float otherDY, float otherDZ, float _sfx0, float _sfx1, float _sfx2, float _sfx3, float _sfx4) {
        float _sfx5 = java.lang.Math.fma(otherRX, this.dZ, otherRW * this.dY) + java.lang.Math.fma(otherRY, this.dW, -otherRZ * this.dX) + (java.lang.Math.fma(otherDX, this.rZ, otherDW * this.rY) + java.lang.Math.fma(otherDY, this.rW, otherDZ * -this.rX));
        float _sfx6 = java.lang.Math.fma(otherRY, this.dX, otherRZ * this.dW) + java.lang.Math.fma(otherRW, this.dZ, -otherRX * this.dY) + (java.lang.Math.fma(otherDY, this.rX, otherDZ * this.rW) + java.lang.Math.fma(otherDW, this.rZ, otherDX * -this.rY));
        float _sfx7 = java.lang.Math.fma(otherRW, this.dW, -otherRX * this.dX) + java.lang.Math.fma(-otherRZ, this.dZ, -otherRY * this.dY) + (java.lang.Math.fma(otherDW, this.rW, otherDX * -this.rX) + java.lang.Math.fma(otherDZ, -this.rZ, otherDY * -this.rY));
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code mul}; reached only through it. */
    private FloatDualQuat mul_s401e608_tail_mulAdd(float otherRX, float otherRW, float otherRY, float otherRZ, float otherDX, float otherDW, float otherDY, float otherDZ, float _sfx0, float _sfx1, float _sfx2, float _sfx3, float _sfx4) {
        float _sfx5 = ((otherRX) * (this.dZ) + (otherRW * this.dY)) + ((otherRY) * (this.dW) - (otherRZ * this.dX)) + (((otherDX) * (this.rZ) + (otherDW * this.rY)) + ((otherDY) * (this.rW) - (otherDZ * this.rX)));
        float _sfx6 = ((otherRY) * (this.dX) + (otherRZ * this.dW)) + ((otherRW) * (this.dZ) - (otherRX * this.dY)) + (((otherDY) * (this.rX) + (otherDZ * this.rW)) + ((otherDW) * (this.rZ) - (otherDX * this.rY)));
        float _sfx7 = ((otherRW) * (this.dW) - (otherRX * this.dX)) + ((-otherRZ) * (this.dZ) - (otherRY * this.dY)) + (((otherDW) * (this.rW) - (otherDX * this.rX)) + ((otherDZ) * (-this.rZ) - (otherDY * this.rY)));
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
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW);
        float _sfx0 = java.lang.Math.fma(otherRX, this.rW, otherRW * this.rX) + java.lang.Math.fma(otherRZ, this.rY, -otherRY * this.rZ);
        float _sfx1 = java.lang.Math.fma(otherRX, this.rZ, otherRW * this.rY) + java.lang.Math.fma(otherRY, this.rW, otherRZ * -this.rX);
        float _sfx2 = java.lang.Math.fma(otherRY, this.rX, otherRZ * this.rW) + java.lang.Math.fma(otherRW, this.rZ, -otherRX * this.rY);
        float _sfx3 = java.lang.Math.fma(otherRW, this.rW, otherRX * -this.rX) - java.lang.Math.fma(otherRY, this.rY, otherRZ * this.rZ);
        float _sfx4 = java.lang.Math.fma(otherRX, this.dW, otherRW * this.dX) + java.lang.Math.fma(otherRZ, this.dY, -otherRY * this.dZ) + (java.lang.Math.fma(otherDX, this.rW, otherDW * this.rX) + java.lang.Math.fma(otherDZ, this.rY, otherDY * -this.rZ));
        return mul_s401e608_tail_fma(otherRX, otherRW, otherRY, otherRZ, otherDX, otherDW, otherDY, otherDZ, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat mul_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW) {
        float _sfx0 = ((otherRX) * (this.rW) + (otherRW * this.rX)) + ((otherRZ) * (this.rY) - (otherRY * this.rZ));
        float _sfx1 = ((otherRX) * (this.rZ) + (otherRW * this.rY)) + ((otherRY) * (this.rW) - (otherRZ * this.rX));
        float _sfx2 = ((otherRY) * (this.rX) + (otherRZ * this.rW)) + ((otherRW) * (this.rZ) - (otherRX * this.rY));
        float _sfx3 = ((otherRW) * (this.rW) - (otherRX * this.rX)) - ((otherRY) * (this.rY) + (otherRZ * this.rZ));
        float _sfx4 = ((otherRX) * (this.dW) + (otherRW * this.dX)) + ((otherRZ) * (this.dY) - (otherRY * this.dZ)) + (((otherDX) * (this.rW) + (otherDW * this.rX)) + ((otherDZ) * (this.rY) - (otherDY * this.rZ)));
        return mul_s401e608_tail_mulAdd(otherRX, otherRW, otherRY, otherRZ, otherDX, otherDW, otherDY, otherDZ, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
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
        return preMul(other.rX, other.rY, other.rZ, other.rW, other.dX, other.dY, other.dZ, other.dW);
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private FloatDualQuat preMul_s401e608_tail_fma(float otherRY, float otherRZ, float otherRW, float otherRX, float otherDY, float otherDZ, float otherDW, float otherDX, float _sfx0, float _sfx1, float _sfx2, float _sfx3, float _sfx4) {
        float _sfx5 = java.lang.Math.fma(otherRY, this.dW, otherRZ * this.dX) + java.lang.Math.fma(otherRW, this.dY, -otherRX * this.dZ) + (java.lang.Math.fma(otherDY, this.rW, otherDZ * this.rX) + java.lang.Math.fma(otherDW, this.rY, otherDX * -this.rZ));
        float _sfx6 = java.lang.Math.fma(otherRX, this.dY, otherRW * this.dZ) + java.lang.Math.fma(otherRZ, this.dW, -otherRY * this.dX) + (java.lang.Math.fma(otherDX, this.rY, otherDW * this.rZ) + java.lang.Math.fma(otherDZ, this.rW, otherDY * -this.rX));
        float _sfx7 = java.lang.Math.fma(otherRW, this.dW, -otherRX * this.dX) + java.lang.Math.fma(-otherRZ, this.dZ, -otherRY * this.dY) + (java.lang.Math.fma(otherDW, this.rW, otherDX * -this.rX) + java.lang.Math.fma(otherDZ, -this.rZ, otherDY * -this.rY));
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private FloatDualQuat preMul_s401e608_tail_mulAdd(float otherRY, float otherRZ, float otherRW, float otherRX, float otherDY, float otherDZ, float otherDW, float otherDX, float _sfx0, float _sfx1, float _sfx2, float _sfx3, float _sfx4) {
        float _sfx5 = ((otherRY) * (this.dW) + (otherRZ * this.dX)) + ((otherRW) * (this.dY) - (otherRX * this.dZ)) + (((otherDY) * (this.rW) + (otherDZ * this.rX)) + ((otherDW) * (this.rY) - (otherDX * this.rZ)));
        float _sfx6 = ((otherRX) * (this.dY) + (otherRW * this.dZ)) + ((otherRZ) * (this.dW) - (otherRY * this.dX)) + (((otherDX) * (this.rY) + (otherDW * this.rZ)) + ((otherDZ) * (this.rW) - (otherDY * this.rX)));
        float _sfx7 = ((otherRW) * (this.dW) - (otherRX * this.dX)) + ((-otherRZ) * (this.dZ) - (otherRY * this.dY)) + (((otherDW) * (this.rW) - (otherDX * this.rX)) + ((otherDZ) * (-this.rZ) - (otherDY * this.rY)));
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
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW);
        float _sfx0 = java.lang.Math.fma(otherRX, this.rW, otherRW * this.rX) + java.lang.Math.fma(otherRY, this.rZ, -otherRZ * this.rY);
        float _sfx1 = java.lang.Math.fma(otherRY, this.rW, otherRZ * this.rX) + java.lang.Math.fma(otherRW, this.rY, -otherRX * this.rZ);
        float _sfx2 = java.lang.Math.fma(otherRX, this.rY, otherRW * this.rZ) + java.lang.Math.fma(otherRZ, this.rW, -otherRY * this.rX);
        float _sfx3 = java.lang.Math.fma(otherRW, this.rW, -otherRX * this.rX) - java.lang.Math.fma(otherRY, this.rY, otherRZ * this.rZ);
        float _sfx4 = java.lang.Math.fma(otherRX, this.dW, otherRW * this.dX) + java.lang.Math.fma(otherRY, this.dZ, -otherRZ * this.dY) + (java.lang.Math.fma(otherDX, this.rW, otherDW * this.rX) + java.lang.Math.fma(otherDY, this.rZ, otherDZ * -this.rY));
        return preMul_s401e608_tail_fma(otherRY, otherRZ, otherRW, otherRX, otherDY, otherDZ, otherDW, otherDX, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat preMul_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW) {
        float _sfx0 = ((otherRX) * (this.rW) + (otherRW * this.rX)) + ((otherRY) * (this.rZ) - (otherRZ * this.rY));
        float _sfx1 = ((otherRY) * (this.rW) + (otherRZ * this.rX)) + ((otherRW) * (this.rY) - (otherRX * this.rZ));
        float _sfx2 = ((otherRX) * (this.rY) + (otherRW * this.rZ)) + ((otherRZ) * (this.rW) - (otherRY * this.rX));
        float _sfx3 = ((otherRW) * (this.rW) - (otherRX * this.rX)) - ((otherRY) * (this.rY) + (otherRZ * this.rZ));
        float _sfx4 = ((otherRX) * (this.dW) + (otherRW * this.dX)) + ((otherRY) * (this.dZ) - (otherRZ * this.dY)) + (((otherDX) * (this.rW) + (otherDW * this.rX)) + ((otherDY) * (this.rZ) - (otherDZ * this.rY)));
        return preMul_s401e608_tail_mulAdd(otherRY, otherRZ, otherRW, otherRX, otherDY, otherDZ, otherDW, otherDX, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
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
        float otherRX = other.rX;
        float otherRY = other.rY;
        float otherRZ = other.rZ;
        float otherRW = other.rW;
        float otherDX = other.dX;
        float otherDY = other.dY;
        float otherDZ = other.dZ;
        float otherDW = other.dW;
        if (org.joml2.internal.FmaSwitch.ON) {
            return new FloatDualQuat(java.lang.Math.fma(weight, otherRX, this.rX), java.lang.Math.fma(weight, otherRY, this.rY), java.lang.Math.fma(weight, otherRZ, this.rZ), java.lang.Math.fma(weight, otherRW, this.rW), java.lang.Math.fma(weight, otherDX, this.dX), java.lang.Math.fma(weight, otherDY, this.dY), java.lang.Math.fma(weight, otherDZ, this.dZ), java.lang.Math.fma(weight, otherDW, this.dW));
        } else {
            return new FloatDualQuat(((weight) * (otherRX) + (this.rX)), ((weight) * (otherRY) + (this.rY)), ((weight) * (otherRZ) + (this.rZ)), ((weight) * (otherRW) + (this.rW)), ((weight) * (otherDX) + (this.dX)), ((weight) * (otherDY) + (this.dY)), ((weight) * (otherDZ) + (this.dZ)), ((weight) * (otherDW) + (this.dW)));
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            return new FloatDualQuat(java.lang.Math.fma(weight, otherRX, this.rX), java.lang.Math.fma(weight, otherRY, this.rY), java.lang.Math.fma(weight, otherRZ, this.rZ), java.lang.Math.fma(weight, otherRW, this.rW), java.lang.Math.fma(weight, otherDX, this.dX), java.lang.Math.fma(weight, otherDY, this.dY), java.lang.Math.fma(weight, otherDZ, this.dZ), java.lang.Math.fma(weight, otherDW, this.dW));
        } else {
            return new FloatDualQuat(((weight) * (otherRX) + (this.rX)), ((weight) * (otherRY) + (this.rY)), ((weight) * (otherRZ) + (this.rZ)), ((weight) * (otherRW) + (this.rW)), ((weight) * (otherDX) + (this.dX)), ((weight) * (otherDY) + (this.dY)), ((weight) * (otherDZ) + (this.dZ)), ((weight) * (otherDW) + (this.dW)));
        }
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
        return difference(other.rX, other.rY, other.rZ, other.rW, other.dX, other.dY, other.dZ, other.dW);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private FloatDualQuat difference_s401e608_tail_fma(float otherRY, float otherRZ, float _t0, float otherRW, float otherDY, float otherDZ, float otherDX, float otherDW, float otherRX, float _sfx0, float _sfx1, float _sfx2, float _sfx3, float _sfx4) {
        float _sfx5 = java.lang.Math.fma(otherRY, this.dW, otherRZ * this.dX) + java.lang.Math.fma(_t0, this.dZ, -otherRW * this.dY) + (java.lang.Math.fma(otherDY, this.rW, otherDZ * this.rX) + java.lang.Math.fma(otherDX, -this.rZ, otherDW * -this.rY));
        float _sfx6 = java.lang.Math.fma(otherRX, this.dY, -otherRW * this.dZ) + java.lang.Math.fma(otherRZ, this.dW, -(otherRY * this.dX)) + (java.lang.Math.fma(otherDX, this.rY, otherDW * -this.rZ) + java.lang.Math.fma(otherDZ, this.rW, otherDY * -this.rX));
        float _sfx7 = java.lang.Math.fma(otherRX, this.dX, otherRW * this.dW) + java.lang.Math.fma(otherRY, this.dY, otherRZ * this.dZ) + (java.lang.Math.fma(otherDX, this.rX, otherDW * this.rW) + java.lang.Math.fma(otherDY, this.rY, otherDZ * this.rZ));
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private FloatDualQuat difference_s401e608_tail_mulAdd(float otherRY, float otherRZ, float _t0, float otherRW, float otherDY, float otherDZ, float otherDX, float otherDW, float otherRX, float _sfx0, float _sfx1, float _sfx2, float _sfx3, float _sfx4) {
        float _sfx5 = ((otherRY) * (this.dW) + (otherRZ * this.dX)) + ((_t0) * (this.dZ) - (otherRW * this.dY)) + (((otherDY) * (this.rW) + (otherDZ * this.rX)) + ((otherDX) * (-this.rZ) - (otherDW * this.rY)));
        float _sfx6 = ((otherRX) * (this.dY) - (otherRW * this.dZ)) + ((otherRZ) * (this.dW) - (otherRY * this.dX)) + (((otherDX) * (this.rY) - (otherDW * this.rZ)) + ((otherDZ) * (this.rW) - (otherDY * this.rX)));
        float _sfx7 = ((otherRX) * (this.dX) + (otherRW * this.dW)) + ((otherRY) * (this.dY) + (otherRZ * this.dZ)) + (((otherDX) * (this.rX) + (otherDW * this.rW)) + ((otherDY) * (this.rY) + (otherDZ * this.rZ)));
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
        if (!org.joml2.internal.FmaSwitch.ON) return difference_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW);
        float _t0 = -otherRX;
        float _sfx0 = java.lang.Math.fma(otherRX, this.rW, -otherRW * this.rX) + java.lang.Math.fma(otherRY, this.rZ, otherRZ * -this.rY);
        float _sfx1 = java.lang.Math.fma(otherRY, this.rW, otherRZ * this.rX) + java.lang.Math.fma(_t0, this.rZ, -otherRW * this.rY);
        float _sfx2 = java.lang.Math.fma(otherRX, this.rY, -otherRW * this.rZ) + java.lang.Math.fma(otherRZ, this.rW, otherRY * -this.rX);
        float _sfx3 = java.lang.Math.fma(otherRX, this.rX, otherRW * this.rW) - java.lang.Math.fma(otherRZ, -this.rZ, otherRY * -this.rY);
        float _sfx4 = java.lang.Math.fma(otherRX, this.dW, -otherRW * this.dX) + java.lang.Math.fma(otherRY, this.dZ, -(otherRZ * this.dY)) + (java.lang.Math.fma(otherDX, this.rW, otherDW * -this.rX) + java.lang.Math.fma(otherDY, this.rZ, otherDZ * -this.rY));
        return difference_s401e608_tail_fma(otherRY, otherRZ, _t0, otherRW, otherDY, otherDZ, otherDX, otherDW, otherRX, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat difference_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, float otherDX, float otherDY, float otherDZ, float otherDW) {
        float _t0 = -otherRX;
        float _sfx0 = ((otherRX) * (this.rW) - (otherRW * this.rX)) + ((otherRY) * (this.rZ) - (otherRZ * this.rY));
        float _sfx1 = ((otherRY) * (this.rW) + (otherRZ * this.rX)) + ((_t0) * (this.rZ) - (otherRW * this.rY));
        float _sfx2 = ((otherRX) * (this.rY) - (otherRW * this.rZ)) + ((otherRZ) * (this.rW) - (otherRY * this.rX));
        float _sfx3 = ((otherRX) * (this.rX) + (otherRW * this.rW)) - ((otherRZ) * (-this.rZ) - (otherRY * this.rY));
        float _sfx4 = ((otherRX) * (this.dW) - (otherRW * this.dX)) + ((otherRY) * (this.dZ) - (otherRZ * this.dY)) + (((otherDX) * (this.rW) - (otherDW * this.rX)) + ((otherDY) * (this.rZ) - (otherDZ * this.rY)));
        return difference_s401e608_tail_mulAdd(otherRY, otherRZ, _t0, otherRW, otherDY, otherDZ, otherDX, otherDW, otherRX, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
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
        float otherRX = other.rX;
        float otherRY = other.rY;
        float otherRZ = other.rZ;
        float otherRW = other.rW;
        float otherDX = other.dX;
        float otherDY = other.dY;
        float otherDZ = other.dZ;
        float otherDW = other.dW;
        if (org.joml2.internal.FmaSwitch.ON) {
            return java.lang.Math.fma(otherRX, this.rX, otherRY * this.rY) + java.lang.Math.fma(otherRZ, this.rZ, otherRW * this.rW) + (java.lang.Math.fma(otherDX, this.dX, otherDY * this.dY) + java.lang.Math.fma(otherDZ, this.dZ, otherDW * this.dW));
        } else {
            return ((otherRX) * (this.rX) + (otherRY * this.rY)) + ((otherRZ) * (this.rZ) + (otherRW * this.rW)) + (((otherDX) * (this.dX) + (otherDY * this.dY)) + ((otherDZ) * (this.dZ) + (otherDW * this.dW)));
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            return java.lang.Math.fma(otherRX, this.rX, otherRY * this.rY) + java.lang.Math.fma(otherRZ, this.rZ, otherRW * this.rW) + (java.lang.Math.fma(otherDX, this.dX, otherDY * this.dY) + java.lang.Math.fma(otherDZ, this.dZ, otherDW * this.dW));
        } else {
            return ((otherRX) * (this.rX) + (otherRY * this.rY)) + ((otherRZ) * (this.rZ) + (otherRW * this.rW)) + (((otherDX) * (this.dX) + (otherDY * this.dY)) + ((otherDZ) * (this.dZ) + (otherDW * this.dW)));
        }
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
    private FloatDualQuat exp_s0_tail_fma(float _t13, float _t9, float _t4, float _t10, float _t8, float _t11, float _t12, float _sp0, float _t5) {
        float _t14 = _t13 * _t9;
        if (_t4 < 1.0E-12f) {
            return new FloatDualQuat(this.rX, this.rY, this.rZ, 1.0f, this.dX, this.dY, this.dZ, -_t5);
        } else {
            return new FloatDualQuat(_t10 * _t8, _t11 * _t8, _t12 * _t8, _t9, java.lang.Math.fma(_t10, _t14, java.lang.Math.fma(_t10, -_t13, this.dX) * _sp0), java.lang.Math.fma(_t11, _t14, java.lang.Math.fma(_t11, -_t13, this.dY) * _sp0), java.lang.Math.fma(_t12, _t14, java.lang.Math.fma(_t12, -_t13, this.dZ) * _sp0), -_t13 * _t8);
        }
    }

    /** Private tail of {@code exp}; reached only through it. */
    private FloatDualQuat exp_s0_tail_mulAdd(float _t13, float _t9, float _t4, float _t10, float _t8, float _t11, float _t12, float _sp0, float _t5) {
        float _t14 = _t13 * _t9;
        if (_t4 < 1.0E-12f) {
            return new FloatDualQuat(this.rX, this.rY, this.rZ, 1.0f, this.dX, this.dY, this.dZ, -_t5);
        } else {
            return new FloatDualQuat(_t10 * _t8, _t11 * _t8, _t12 * _t8, _t9, ((_t10) * (_t14) + (((this.dX) - (_t10) * (_t13)) * _sp0)), ((_t11) * (_t14) + (((this.dY) - (_t11) * (_t13)) * _sp0)), ((_t12) * (_t14) + (((this.dZ) - (_t12) * (_t13)) * _sp0)), -_t13 * _t8);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t4 = java.lang.Math.fma(this.rZ, this.rZ, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY));
            float _t5 = java.lang.Math.fma(this.rZ, this.dZ, java.lang.Math.fma(this.rX, this.dX, this.rY * this.dY));
            float _t7 = (float) java.lang.Math.sqrt(_t4);
            float _t6 = 1.0f / _t7;
            float _t8 = Math.sinWithCos(_t7);
            return exp_s0_tail_fma(_t5 * _t6, Math.cosWithSin(_t7), _t4, this.rX * _t6, _t8, this.rY * _t6, this.rZ * _t6, _t6 * _t8, _t5);
        } else {
            float _t4 = ((this.rZ) * (this.rZ) + (((this.rX) * (this.rX) + (this.rY * this.rY))));
            float _t5 = ((this.rZ) * (this.dZ) + (((this.rX) * (this.dX) + (this.rY * this.dY))));
            float _t7 = (float) java.lang.Math.sqrt(_t4);
            float _t6 = 1.0f / _t7;
            float _t8 = Math.sinWithCos(_t7);
            return exp_s0_tail_mulAdd(_t5 * _t6, Math.cosWithSin(_t7), _t4, this.rX * _t6, _t8, this.rY * _t6, this.rZ * _t6, _t6 * _t8, _t5);
        }
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
     * Get the Euler angles in radians of this dual quaternion for rotations about the X, Y and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X), returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationXYZ(e.x(), e.y(), e.z())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesXYZ() {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_mulAdd();
        float _t1 = this.rY * this.rZ;
        float _t2 = this.rY * this.rW;
        float _t10 = 2.0f * java.lang.Math.fma(this.rX, this.rZ, _t2);
        float _t11 = 2.0f * java.lang.Math.fma(this.rX, this.rW, _t1);
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, this.rW, -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rZ * this.rZ), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            return new Float3(Math.atan2(_t11, _t14), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), 0.0f);
        } else {
            return new Float3(Math.atan2(_t12, _t13), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW), 2.0f * java.lang.Math.fma(this.rX, this.rZ, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
        }
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesXYZ_mulAdd() {
        float _t1 = this.rY * this.rZ;
        float _t2 = this.rY * this.rW;
        float _t10 = 2.0f * ((this.rX) * (this.rZ) + (_t2));
        float _t11 = 2.0f * ((this.rX) * (this.rW) + (_t1));
        float _t12 = 2.0f * ((this.rX) * (this.rW) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
        float _t14 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rZ * this.rZ))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            return new Float3(Math.atan2(_t11, _t14), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), 0.0f);
        } else {
            return new Float3(Math.atan2(_t12, _t13), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), Math.atan2(((_t13) * (2.0f * ((this.rX) * (this.rY) + (this.rZ * this.rW))) + (2.0f * ((this.rX) * (this.rZ) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
        }
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the X, Y and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X), returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationXYZ(e.x(), e.y(), e.z())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesXYZ(float epsilon) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_mulAdd(epsilon);
        float _t1 = this.rY * this.rZ;
        float _t2 = this.rY * this.rW;
        float _t10 = 2.0f * java.lang.Math.fma(this.rX, this.rZ, _t2);
        float _t11 = 2.0f * java.lang.Math.fma(this.rX, this.rW, _t1);
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, this.rW, -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rZ * this.rZ), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            return new Float3(Math.atan2(_t11, _t14), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), 0.0f);
        } else {
            return new Float3(Math.atan2(_t12, _t13), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW), 2.0f * java.lang.Math.fma(this.rX, this.rZ, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
        }
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesXYZ_mulAdd(float epsilon) {
        float _t1 = this.rY * this.rZ;
        float _t2 = this.rY * this.rW;
        float _t10 = 2.0f * ((this.rX) * (this.rZ) + (_t2));
        float _t11 = 2.0f * ((this.rX) * (this.rW) + (_t1));
        float _t12 = 2.0f * ((this.rX) * (this.rW) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
        float _t14 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rZ * this.rZ))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            return new Float3(Math.atan2(_t11, _t14), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), 0.0f);
        } else {
            return new Float3(Math.atan2(_t12, _t13), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), Math.atan2(((_t13) * (2.0f * ((this.rX) * (this.rY) + (this.rZ * this.rW))) + (2.0f * ((this.rX) * (this.rZ) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
        }
    }

    /** Private tail of {@code getEulerAnglesXZY}; reached only through it. */
    private Float3 getEulerAnglesXZY_s0_tail_fma(float _t10, float _t14, float _t1, float _t12, float _t9, float _t11) {
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            return new Float3(Math.atan2(2.0f * java.lang.Math.fma(this.rX, this.rW, -_t1), _t12), 0.0f, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        } else {
            return new Float3(Math.atan2(_t9, _t11), Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW), _t9, _t11 * -2.0f * java.lang.Math.fma(this.rX, this.rZ, this.rY * -this.rW)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(this.rY, this.rZ, this.rX * -this.rW))), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        }
    }

    /** Private tail of {@code getEulerAnglesXZY}; reached only through it. */
    private Float3 getEulerAnglesXZY_s0_tail_mulAdd(float _t10, float _t14, float _t1, float _t12, float _t9, float _t11) {
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            return new Float3(Math.atan2(2.0f * ((this.rX) * (this.rW) - (_t1)), _t12), 0.0f, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        } else {
            return new Float3(Math.atan2(_t9, _t11), Math.atan2(((2.0f * ((this.rX) * (this.rY) + (this.rZ * this.rW))) * (_t9) - (_t11 * 2.0f * ((this.rX) * (this.rZ) - (this.rY * this.rW)))), ((_t12) * (_t11) - (_t9 * 2.0f * ((this.rY) * (this.rZ) - (this.rX * this.rW))))), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        }
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the X, Z and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X), returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationXZY(e.x(), e.z(), e.y())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesXZY() {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t1 = this.rY * this.rZ;
            float _t9 = 2.0f * java.lang.Math.fma(this.rX, this.rW, _t1);
            float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rZ * this.rZ), 1.0f);
            return getEulerAnglesXZY_s0_tail_fma(2.0f * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), java.lang.Math.fma(_t11, _t11, _t9 * _t9), _t1, java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f), _t9, _t11);
        } else {
            float _t1 = this.rY * this.rZ;
            float _t9 = 2.0f * ((this.rX) * (this.rW) + (_t1));
            float _t11 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rZ * this.rZ))));
            return getEulerAnglesXZY_s0_tail_mulAdd(2.0f * ((this.rZ) * (this.rW) - (this.rX * this.rY)), ((_t11) * (_t11) + (_t9 * _t9)), _t1, ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rY * this.rY)))), _t9, _t11);
        }
    }

    /** Private tail of {@code getEulerAnglesXZY}; reached only through it. */
    private Float3 getEulerAnglesXZY_s35c5b5a6_tail_fma(float epsilon, float _t10, float _t14, float _t1, float _t12, float _t9, float _t11) {
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            return new Float3(Math.atan2(2.0f * java.lang.Math.fma(this.rX, this.rW, -_t1), _t12), 0.0f, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        } else {
            return new Float3(Math.atan2(_t9, _t11), Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW), _t9, _t11 * -2.0f * java.lang.Math.fma(this.rX, this.rZ, this.rY * -this.rW)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(this.rY, this.rZ, this.rX * -this.rW))), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        }
    }

    /** Private tail of {@code getEulerAnglesXZY}; reached only through it. */
    private Float3 getEulerAnglesXZY_s35c5b5a6_tail_mulAdd(float epsilon, float _t10, float _t14, float _t1, float _t12, float _t9, float _t11) {
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            return new Float3(Math.atan2(2.0f * ((this.rX) * (this.rW) - (_t1)), _t12), 0.0f, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        } else {
            return new Float3(Math.atan2(_t9, _t11), Math.atan2(((2.0f * ((this.rX) * (this.rY) + (this.rZ * this.rW))) * (_t9) - (_t11 * 2.0f * ((this.rX) * (this.rZ) - (this.rY * this.rW)))), ((_t12) * (_t11) - (_t9 * 2.0f * ((this.rY) * (this.rZ) - (this.rX * this.rW))))), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        }
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the X, Z and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X), returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationXZY(e.x(), e.z(), e.y())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesXZY(float epsilon) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t1 = this.rY * this.rZ;
            float _t9 = 2.0f * java.lang.Math.fma(this.rX, this.rW, _t1);
            float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rZ * this.rZ), 1.0f);
            return getEulerAnglesXZY_s35c5b5a6_tail_fma(epsilon, 2.0f * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), java.lang.Math.fma(_t11, _t11, _t9 * _t9), _t1, java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f), _t9, _t11);
        } else {
            float _t1 = this.rY * this.rZ;
            float _t9 = 2.0f * ((this.rX) * (this.rW) + (_t1));
            float _t11 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rZ * this.rZ))));
            return getEulerAnglesXZY_s35c5b5a6_tail_mulAdd(epsilon, 2.0f * ((this.rZ) * (this.rW) - (this.rX * this.rY)), ((_t11) * (_t11) + (_t9 * _t9)), _t1, ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rY * this.rY)))), _t9, _t11);
        }
    }

    /** Private tail of {@code getEulerAnglesYXZ}; reached only through it. */
    private Float3 getEulerAnglesYXZ_s0_tail_fma(float _t10, float _t14, float _t12, float _t9, float _t11, float _t0, float _t2) {
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)), Math.atan2(2.0f * java.lang.Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), _t12), 0.0f);
        } else {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)), Math.atan2(_t9, _t11), Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(this.rX, this.rW, _t0), _t11 * -2.0f * java.lang.Math.fma(this.rX, this.rY, -(this.rZ * this.rW))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(this.rX, this.rZ, -_t2))));
        }
    }

    /** Private tail of {@code getEulerAnglesYXZ}; reached only through it. */
    private Float3 getEulerAnglesYXZ_s0_tail_mulAdd(float _t10, float _t14, float _t12, float _t9, float _t11, float _t0, float _t2) {
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)), Math.atan2(2.0f * ((this.rY) * (this.rW) - (this.rX * this.rZ)), _t12), 0.0f);
        } else {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)), Math.atan2(_t9, _t11), Math.atan2(((_t9) * (2.0f * ((this.rX) * (this.rW) + (_t0))) - (_t11 * 2.0f * ((this.rX) * (this.rY) - (this.rZ * this.rW)))), ((_t11) * (_t12) - (_t9 * 2.0f * ((this.rX) * (this.rZ) - (_t2))))));
        }
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Y, X and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y), returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationYXZ(e.y(), e.x(), e.z())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesYXZ() {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = this.rY * this.rZ;
            float _t2 = this.rY * this.rW;
            float _t9 = 2.0f * java.lang.Math.fma(this.rX, this.rZ, _t2);
            float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
            return getEulerAnglesYXZ_s0_tail_fma(2.0f * java.lang.Math.fma(this.rX, this.rW, -_t0), java.lang.Math.fma(_t11, _t11, _t9 * _t9), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.rY, this.rZ * this.rZ), 1.0f), _t9, _t11, _t0, _t2);
        } else {
            float _t0 = this.rY * this.rZ;
            float _t2 = this.rY * this.rW;
            float _t9 = 2.0f * ((this.rX) * (this.rZ) + (_t2));
            float _t11 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
            return getEulerAnglesYXZ_s0_tail_mulAdd(2.0f * ((this.rX) * (this.rW) - (_t0)), ((_t11) * (_t11) + (_t9 * _t9)), ((1.0f) - (2.0f) * (((this.rY) * (this.rY) + (this.rZ * this.rZ)))), _t9, _t11, _t0, _t2);
        }
    }

    /** Private tail of {@code getEulerAnglesYXZ}; reached only through it. */
    private Float3 getEulerAnglesYXZ_s35c5b5a6_tail_fma(float epsilon, float _t10, float _t14, float _t12, float _t9, float _t11, float _t0, float _t2) {
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)), Math.atan2(2.0f * java.lang.Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), _t12), 0.0f);
        } else {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)), Math.atan2(_t9, _t11), Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(this.rX, this.rW, _t0), _t11 * -2.0f * java.lang.Math.fma(this.rX, this.rY, -(this.rZ * this.rW))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(this.rX, this.rZ, -_t2))));
        }
    }

    /** Private tail of {@code getEulerAnglesYXZ}; reached only through it. */
    private Float3 getEulerAnglesYXZ_s35c5b5a6_tail_mulAdd(float epsilon, float _t10, float _t14, float _t12, float _t9, float _t11, float _t0, float _t2) {
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)), Math.atan2(2.0f * ((this.rY) * (this.rW) - (this.rX * this.rZ)), _t12), 0.0f);
        } else {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)), Math.atan2(_t9, _t11), Math.atan2(((_t9) * (2.0f * ((this.rX) * (this.rW) + (_t0))) - (_t11 * 2.0f * ((this.rX) * (this.rY) - (this.rZ * this.rW)))), ((_t11) * (_t12) - (_t9 * 2.0f * ((this.rX) * (this.rZ) - (_t2))))));
        }
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Y, X and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y), returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationYXZ(e.y(), e.x(), e.z())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesYXZ(float epsilon) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = this.rY * this.rZ;
            float _t2 = this.rY * this.rW;
            float _t9 = 2.0f * java.lang.Math.fma(this.rX, this.rZ, _t2);
            float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
            return getEulerAnglesYXZ_s35c5b5a6_tail_fma(epsilon, 2.0f * java.lang.Math.fma(this.rX, this.rW, -_t0), java.lang.Math.fma(_t11, _t11, _t9 * _t9), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.rY, this.rZ * this.rZ), 1.0f), _t9, _t11, _t0, _t2);
        } else {
            float _t0 = this.rY * this.rZ;
            float _t2 = this.rY * this.rW;
            float _t9 = 2.0f * ((this.rX) * (this.rZ) + (_t2));
            float _t11 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
            return getEulerAnglesYXZ_s35c5b5a6_tail_mulAdd(epsilon, 2.0f * ((this.rX) * (this.rW) - (_t0)), ((_t11) * (_t11) + (_t9 * _t9)), ((1.0f) - (2.0f) * (((this.rY) * (this.rY) + (this.rZ * this.rZ)))), _t9, _t11, _t0, _t2);
        }
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Y, Z and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y), returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationYZX(e.y(), e.z(), e.x())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesYZX() {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYZX_mulAdd();
        float _t2 = this.rZ * this.rW;
        float _t11 = 2.0f * java.lang.Math.fma(this.rX, this.rY, _t2);
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW);
        float _t13 = 2.0f * java.lang.Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.rY, this.rZ * this.rZ), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= java.lang.Math.fma(_t11, _t11, _t17) * 2.2737368E-13f) {
            return new Float3(0.0f, Math.atan2(_t12, _t15), Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        } else {
            return new Float3(Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ), 2.0f * java.lang.Math.fma(this.rX, this.rY, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13)), Math.atan2(_t13, _t14), Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        }
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesYZX_mulAdd() {
        float _t2 = this.rZ * this.rW;
        float _t11 = 2.0f * ((this.rX) * (this.rY) + (_t2));
        float _t12 = 2.0f * ((this.rX) * (this.rZ) + (this.rY * this.rW));
        float _t13 = 2.0f * ((this.rY) * (this.rW) - (this.rX * this.rZ));
        float _t14 = ((1.0f) - (2.0f) * (((this.rY) * (this.rY) + (this.rZ * this.rZ))));
        float _t15 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= ((_t11) * (_t11) + (_t17)) * 2.2737368E-13f) {
            return new Float3(0.0f, Math.atan2(_t12, _t15), Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        } else {
            return new Float3(Math.atan2(((_t14) * (2.0f * ((this.rX) * (this.rW) + (this.rY * this.rZ))) + (2.0f * ((this.rX) * (this.rY) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13))), Math.atan2(_t13, _t14), Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        }
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Y, Z and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y), returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationYZX(e.y(), e.z(), e.x())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesYZX(float epsilon) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYZX_mulAdd(epsilon);
        float _t2 = this.rZ * this.rW;
        float _t11 = 2.0f * java.lang.Math.fma(this.rX, this.rY, _t2);
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW);
        float _t13 = 2.0f * java.lang.Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.rY, this.rZ * this.rZ), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= epsilon * java.lang.Math.fma(_t11, _t11, _t17)) {
            return new Float3(0.0f, Math.atan2(_t12, _t15), Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        } else {
            return new Float3(Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ), 2.0f * java.lang.Math.fma(this.rX, this.rY, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13)), Math.atan2(_t13, _t14), Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        }
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesYZX_mulAdd(float epsilon) {
        float _t2 = this.rZ * this.rW;
        float _t11 = 2.0f * ((this.rX) * (this.rY) + (_t2));
        float _t12 = 2.0f * ((this.rX) * (this.rZ) + (this.rY * this.rW));
        float _t13 = 2.0f * ((this.rY) * (this.rW) - (this.rX * this.rZ));
        float _t14 = ((1.0f) - (2.0f) * (((this.rY) * (this.rY) + (this.rZ * this.rZ))));
        float _t15 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= epsilon * ((_t11) * (_t11) + (_t17))) {
            return new Float3(0.0f, Math.atan2(_t12, _t15), Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        } else {
            return new Float3(Math.atan2(((_t14) * (2.0f * ((this.rX) * (this.rW) + (this.rY * this.rZ))) + (2.0f * ((this.rX) * (this.rY) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13))), Math.atan2(_t13, _t14), Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        }
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Z, X and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z), returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationZXY(e.z(), e.x(), e.y())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesZXY() {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZXY_mulAdd();
        float _t1 = this.rZ * this.rZ;
        float _t10 = 2.0f * java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ);
        float _t11 = 2.0f * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW);
        float _t12 = 2.0f * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.rY, _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), 0.0f, Math.atan2(_t11, _t14));
        } else {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW), 2.0f * java.lang.Math.fma(this.rY, this.rZ, -(this.rX * this.rW)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)), Math.atan2(_t12, _t13));
        }
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesZXY_mulAdd() {
        float _t1 = this.rZ * this.rZ;
        float _t10 = 2.0f * ((this.rX) * (this.rW) + (this.rY * this.rZ));
        float _t11 = 2.0f * ((this.rX) * (this.rY) + (this.rZ * this.rW));
        float _t12 = 2.0f * ((this.rZ) * (this.rW) - (this.rX * this.rY));
        float _t13 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((this.rY) * (this.rY) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), 0.0f, Math.atan2(_t11, _t14));
        } else {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), Math.atan2(((_t13) * (2.0f * ((this.rX) * (this.rZ) + (this.rY * this.rW))) + (2.0f * ((this.rY) * (this.rZ) - (this.rX * this.rW)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))), Math.atan2(_t12, _t13));
        }
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Z, X and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z), returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationZXY(e.z(), e.x(), e.y())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesZXY(float epsilon) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZXY_mulAdd(epsilon);
        float _t1 = this.rZ * this.rZ;
        float _t10 = 2.0f * java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ);
        float _t11 = 2.0f * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW);
        float _t12 = 2.0f * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.rY, _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), 0.0f, Math.atan2(_t11, _t14));
        } else {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW), 2.0f * java.lang.Math.fma(this.rY, this.rZ, -(this.rX * this.rW)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)), Math.atan2(_t12, _t13));
        }
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesZXY_mulAdd(float epsilon) {
        float _t1 = this.rZ * this.rZ;
        float _t10 = 2.0f * ((this.rX) * (this.rW) + (this.rY * this.rZ));
        float _t11 = 2.0f * ((this.rX) * (this.rY) + (this.rZ * this.rW));
        float _t12 = 2.0f * ((this.rZ) * (this.rW) - (this.rX * this.rY));
        float _t13 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((this.rY) * (this.rY) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), 0.0f, Math.atan2(_t11, _t14));
        } else {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), Math.atan2(((_t13) * (2.0f * ((this.rX) * (this.rZ) + (this.rY * this.rW))) + (2.0f * ((this.rY) * (this.rZ) - (this.rX * this.rW)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))), Math.atan2(_t12, _t13));
        }
    }

    /** Private tail of {@code getEulerAnglesZYX}; reached only through it. */
    private Float3 getEulerAnglesZYX_s0_tail_fma(float _t9, float _t13, float _t8, float _t10, float _t11, float _t1) {
        if (_t13 <= java.lang.Math.fma(_t9, _t9, _t13) * 2.2737368E-13f) {
            return new Float3(0.0f, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)), Math.atan2(2.0f * java.lang.Math.fma(this.rZ, this.rW, -this.rX * this.rY), _t11));
        } else {
            return new Float3(Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW), _t10 * -2.0f * java.lang.Math.fma(this.rY, this.rZ, -(this.rX * this.rW))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(this.rX, this.rY, -_t1))), Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)), Math.atan2(_t8, _t10));
        }
    }

    /** Private tail of {@code getEulerAnglesZYX}; reached only through it. */
    private Float3 getEulerAnglesZYX_s0_tail_mulAdd(float _t9, float _t13, float _t8, float _t10, float _t11, float _t1) {
        if (_t13 <= ((_t9) * (_t9) + (_t13)) * 2.2737368E-13f) {
            return new Float3(0.0f, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)), Math.atan2(2.0f * ((this.rZ) * (this.rW) - (this.rX * this.rY)), _t11));
        } else {
            return new Float3(Math.atan2(((_t8) * (2.0f * ((this.rX) * (this.rZ) + (this.rY * this.rW))) - (_t10 * 2.0f * ((this.rY) * (this.rZ) - (this.rX * this.rW)))), ((_t11) * (_t10) - (_t8 * 2.0f * ((this.rX) * (this.rY) - (_t1))))), Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)), Math.atan2(_t8, _t10));
        }
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Z, Y and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z), returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationZYX(e.z(), e.y(), e.x())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesZYX() {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = this.rZ * this.rZ;
            float _t1 = this.rZ * this.rW;
            float _t8 = 2.0f * java.lang.Math.fma(this.rX, this.rY, _t1);
            float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.rY, _t0), 1.0f);
            return getEulerAnglesZYX_s0_tail_fma(2.0f * java.lang.Math.fma(this.rY, this.rW, -this.rX * this.rZ), java.lang.Math.fma(_t10, _t10, _t8 * _t8), _t8, _t10, java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, _t0), 1.0f), _t1);
        } else {
            float _t0 = this.rZ * this.rZ;
            float _t1 = this.rZ * this.rW;
            float _t8 = 2.0f * ((this.rX) * (this.rY) + (_t1));
            float _t10 = ((1.0f) - (2.0f) * (((this.rY) * (this.rY) + (_t0))));
            return getEulerAnglesZYX_s0_tail_mulAdd(2.0f * ((this.rY) * (this.rW) - (this.rX * this.rZ)), ((_t10) * (_t10) + (_t8 * _t8)), _t8, _t10, ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (_t0)))), _t1);
        }
    }

    /** Private tail of {@code getEulerAnglesZYX}; reached only through it. */
    private Float3 getEulerAnglesZYX_s35c5b5a6_tail_fma(float epsilon, float _t9, float _t13, float _t8, float _t10, float _t11, float _t1) {
        if (_t13 <= epsilon * java.lang.Math.fma(_t9, _t9, _t13)) {
            return new Float3(0.0f, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)), Math.atan2(2.0f * java.lang.Math.fma(this.rZ, this.rW, -this.rX * this.rY), _t11));
        } else {
            return new Float3(Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW), _t10 * -2.0f * java.lang.Math.fma(this.rY, this.rZ, -(this.rX * this.rW))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(this.rX, this.rY, -_t1))), Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)), Math.atan2(_t8, _t10));
        }
    }

    /** Private tail of {@code getEulerAnglesZYX}; reached only through it. */
    private Float3 getEulerAnglesZYX_s35c5b5a6_tail_mulAdd(float epsilon, float _t9, float _t13, float _t8, float _t10, float _t11, float _t1) {
        if (_t13 <= epsilon * ((_t9) * (_t9) + (_t13))) {
            return new Float3(0.0f, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)), Math.atan2(2.0f * ((this.rZ) * (this.rW) - (this.rX * this.rY)), _t11));
        } else {
            return new Float3(Math.atan2(((_t8) * (2.0f * ((this.rX) * (this.rZ) + (this.rY * this.rW))) - (_t10 * 2.0f * ((this.rY) * (this.rZ) - (this.rX * this.rW)))), ((_t11) * (_t10) - (_t8 * 2.0f * ((this.rX) * (this.rY) - (_t1))))), Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)), Math.atan2(_t8, _t10));
        }
    }

    /**
     * Get the Euler angles in radians of this dual quaternion for rotations about the Z, Y and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z), returning the result as a value.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationZYX(e.z(), e.y(), e.x())}, which takes the angles in
     * the order of its name, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code float} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must lie in
     * {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesZYX(float epsilon) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = this.rZ * this.rZ;
            float _t1 = this.rZ * this.rW;
            float _t8 = 2.0f * java.lang.Math.fma(this.rX, this.rY, _t1);
            float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.rY, _t0), 1.0f);
            return getEulerAnglesZYX_s35c5b5a6_tail_fma(epsilon, 2.0f * java.lang.Math.fma(this.rY, this.rW, -this.rX * this.rZ), java.lang.Math.fma(_t10, _t10, _t8 * _t8), _t8, _t10, java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, _t0), 1.0f), _t1);
        } else {
            float _t0 = this.rZ * this.rZ;
            float _t1 = this.rZ * this.rW;
            float _t8 = 2.0f * ((this.rX) * (this.rY) + (_t1));
            float _t10 = ((1.0f) - (2.0f) * (((this.rY) * (this.rY) + (_t0))));
            return getEulerAnglesZYX_s35c5b5a6_tail_mulAdd(epsilon, 2.0f * ((this.rY) * (this.rW) - (this.rX * this.rZ)), ((_t10) * (_t10) + (_t8 * _t8)), _t8, _t10, ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (_t0)))), _t1);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            return new Float3(2.0f * (java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW)), 2.0f * (java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW)), 2.0f * (java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, this.rZ * -this.dW)));
        } else {
            return new Float3(2.0f * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW))), 2.0f * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW))), 2.0f * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW))));
        }
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
        if (!org.joml2.internal.FmaSwitch.ON) return invert_mulAdd();
        float _t8 = java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY) + java.lang.Math.fma(this.rZ, this.rZ, this.rW * this.rW);
        float _t8_inv = 1.0f / _t8;
        float _sp0 = 2.0f * (java.lang.Math.fma(this.rX, this.dX, this.rY * this.dY) + java.lang.Math.fma(this.rZ, this.dZ, this.rW * this.dW)) / (_t8 * _t8);
        return new FloatDualQuat(-(this.rX * _t8_inv), -(this.rY * _t8_inv), -(this.rZ * _t8_inv), this.rW * _t8_inv, java.lang.Math.fma(this.rX, _sp0, -(this.dX * _t8_inv)), java.lang.Math.fma(this.rY, _sp0, -(this.dY * _t8_inv)), java.lang.Math.fma(this.rZ, _sp0, -(this.dZ * _t8_inv)), java.lang.Math.fma(this.dW, _t8_inv, -(this.rW * _sp0)));
    }

    /** {@code invert} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat invert_mulAdd() {
        float _t8 = ((this.rX) * (this.rX) + (this.rY * this.rY)) + ((this.rZ) * (this.rZ) + (this.rW * this.rW));
        float _t8_inv = 1.0f / _t8;
        float _sp0 = 2.0f * (((this.rX) * (this.dX) + (this.rY * this.dY)) + ((this.rZ) * (this.dZ) + (this.rW * this.dW))) / (_t8 * _t8);
        return new FloatDualQuat(-(this.rX * _t8_inv), -(this.rY * _t8_inv), -(this.rZ * _t8_inv), this.rW * _t8_inv, ((this.rX) * (_sp0) - (this.dX * _t8_inv)), ((this.rY) * (_sp0) - (this.dY * _t8_inv)), ((this.rZ) * (_sp0) - (this.dZ * _t8_inv)), ((this.dW) * (_t8_inv) - (this.rW * _sp0)));
    }

    /**
     * Compute the length of this dual quaternion's real (rotation) part.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the length of this dual quaternion's real (rotation) part
     */
    public float length() {
        if (org.joml2.internal.FmaSwitch.ON) {
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY) + java.lang.Math.fma(this.rZ, this.rZ, this.rW * this.rW));
        } else {
            return (float) java.lang.Math.sqrt(((this.rX) * (this.rX) + (this.rY * this.rY)) + ((this.rZ) * (this.rZ) + (this.rW * this.rW)));
        }
    }

    /**
     * Compute the squared length of this dual quaternion's real (rotation) part.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this dual quaternion's real (rotation) part
     */
    public float lengthSquared() {
        if (org.joml2.internal.FmaSwitch.ON) {
            return java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY) + java.lang.Math.fma(this.rZ, this.rZ, this.rW * this.rW);
        } else {
            return ((this.rX) * (this.rX) + (this.rY * this.rY)) + ((this.rZ) * (this.rZ) + (this.rW * this.rW));
        }
    }

    /** Private tail of {@code log}; reached only through it. */
    private FloatDualQuat log_s0_tail_fma(float _t19, float _t10, float _t8, float _t11, float _t18, float _t9, float _t21, float _t22, float _t12, float _t14, float _t15) {
        float _t23 = _t19 * _t10;
        float _t24 = _t19 * _t8;
        float _t25 = _t19 * (this.rW < 0.0f ? -this.dW : this.dW);
        float _t26 = _t25 * _t11;
        if (_t18 < 1.0E-12f) {
            return new FloatDualQuat(_t9, _t10, _t8, 0.0f, _t12, _t14, _t15, 0.0f);
        } else {
            return new FloatDualQuat(_t21 * _t22, _t23 * _t22, _t24 * _t22, 0.0f, java.lang.Math.fma(java.lang.Math.fma(_t26, _t21, _t12) * _t19, _t22, _t21 * -_t25), java.lang.Math.fma(java.lang.Math.fma(_t26, _t23, _t14) * _t19, _t22, _t23 * -_t25), java.lang.Math.fma(java.lang.Math.fma(_t26, _t24, _t15) * _t19, _t22, _t24 * -_t25), 0.0f);
        }
    }

    /** Private tail of {@code log}; reached only through it. */
    private FloatDualQuat log_s0_tail_mulAdd(float _t19, float _t10, float _t8, float _t11, float _t18, float _t9, float _t21, float _t22, float _t12, float _t14, float _t15) {
        float _t23 = _t19 * _t10;
        float _t24 = _t19 * _t8;
        float _t25 = _t19 * (this.rW < 0.0f ? -this.dW : this.dW);
        float _t26 = _t25 * _t11;
        if (_t18 < 1.0E-12f) {
            return new FloatDualQuat(_t9, _t10, _t8, 0.0f, _t12, _t14, _t15, 0.0f);
        } else {
            return new FloatDualQuat(_t21 * _t22, _t23 * _t22, _t24 * _t22, 0.0f, ((((_t26) * (_t21) + (_t12)) * _t19) * (_t22) - (_t21 * _t25)), ((((_t26) * (_t23) + (_t14)) * _t19) * (_t22) - (_t23 * _t25)), ((((_t26) * (_t24) + (_t15)) * _t19) * (_t22) - (_t24 * _t25)), 0.0f);
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
        if (!org.joml2.internal.FmaSwitch.ON) return log_mulAdd();
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
        float _t18 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        return log_s0_tail_fma(_t19, _t10, _t8, _t11, _t18, _t9, _t19 * _t9, Math.atan2((float) java.lang.Math.sqrt(_t18), _t11), _t12, _t14, _t15);
    }

    /** {@code log} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat log_mulAdd() {
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
        float _t18 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        return log_s0_tail_mulAdd(_t19, _t10, _t8, _t11, _t18, _t9, _t19 * _t9, Math.atan2((float) java.lang.Math.sqrt(_t18), _t11), _t12, _t14, _t15);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatDualQuat makeFromMatrix_s37d8ed81_tail_fma(float _t14, float _sp0, float _t4, Float4x4 m, float _t5, float _t16, float _sp1, float _t6, float _sp2, float _t7, float _t8, float _sp3, float _t17, float _t9, float _t10, float _t18, float _t15, float _t0) {
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
        return makeFromMatrix_s37d8ed81_tail2_fma(_t14, _t15, m, _t5, _sp3, _t4, _sp1, _t8, _sp2, _t10, _t63, _t64, _t65, _t0);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatDualQuat makeFromMatrix_s37d8ed81_tail_mulAdd(float _t14, float _sp0, float _t4, Float4x4 m, float _t5, float _t16, float _sp1, float _t6, float _sp2, float _t7, float _t8, float _sp3, float _t17, float _t9, float _t10, float _t18, float _t15, float _t0) {
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
        return makeFromMatrix_s37d8ed81_tail2_mulAdd(_t14, _t15, m, _t5, _sp3, _t4, _sp1, _t8, _sp2, _t10, _t63, _t64, _t65, _t0);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatDualQuat makeFromMatrix_s37d8ed81_tail2_fma(float _t14, float _t15, Float4x4 m, float _t5, float _sp3, float _t4, float _sp1, float _t8, float _sp2, float _t10, float _t63, float _t64, float _t65, float _t0) {
        float _t66 = _t14 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t15) : m.m00() > _t5 ? _sp3 * _t4 : m.m11() > m.m22() ? _sp1 * _t8 : _sp2 * _t10;
        float _m_m03 = m.m03();
        float _m_m13 = m.m13();
        float _sfx4 = 0.5f * java.lang.Math.fma(_t0, _t64, java.lang.Math.fma(_m_m03, _t66, _m_m13 * _t65));
        float _m_m23 = m.m23();
        float _sfx5 = 0.5f * java.lang.Math.fma(_m_m23, _t63, java.lang.Math.fma(_m_m13, _t66, -_m_m03 * _t65));
        float _sfx6 = 0.5f * java.lang.Math.fma(_m_m23, _t66, java.lang.Math.fma(_m_m03, _t64, -_m_m13 * _t63));
        float _sfx7 = 0.5f * java.lang.Math.fma(_t0, _t65, java.lang.Math.fma(-_m_m13, _t64, -_m_m03 * _t63));
        return new FloatDualQuat(_t63, _t64, _t65, _t66, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatDualQuat makeFromMatrix_s37d8ed81_tail2_mulAdd(float _t14, float _t15, Float4x4 m, float _t5, float _sp3, float _t4, float _sp1, float _t8, float _sp2, float _t10, float _t63, float _t64, float _t65, float _t0) {
        float _t66 = _t14 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t15) : m.m00() > _t5 ? _sp3 * _t4 : m.m11() > m.m22() ? _sp1 * _t8 : _sp2 * _t10;
        float _m_m03 = m.m03();
        float _m_m13 = m.m13();
        float _sfx4 = 0.5f * ((_t0) * (_t64) + (((_m_m03) * (_t66) + (_m_m13 * _t65))));
        float _m_m23 = m.m23();
        float _sfx5 = 0.5f * ((_m_m23) * (_t63) + (((_m_m13) * (_t66) - (_m_m03 * _t65))));
        float _sfx6 = 0.5f * ((_m_m23) * (_t66) + (((_m_m03) * (_t64) - (_m_m13 * _t63))));
        float _sfx7 = 0.5f * ((_t0) * (_t65) + (((-_m_m13) * (_t64) - (_m_m03 * _t63))));
        return new FloatDualQuat(_t63, _t64, _t65, _t66, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /**
     * Create the rigid motion of the given matrix: rotation from its upper-left 3x3 block and
     * translation from its last column.
     * <p>
     * Valid input: {@code m} must be a rigid transformation (a rotation with any translation).
     *
     * @param m the matrix to convert
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeFromMatrix(Float4x4 m) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeFromMatrix_mulAdd(m);
        float _m_m00 = m.m00();
        float _t2 = 1.0f - _m_m00;
        float _m_m22 = m.m22();
        float _m_m11 = m.m11();
        float _t14 = _m_m22 + (_m_m00 + _m_m11);
        float _t15 = 1.0f + _t14;
        float _t16 = _m_m00 + (1.0f - _m_m11 - _m_m22);
        float _t17 = _m_m11 + (_t2 - _m_m22);
        float _t18 = _m_m22 + (_t2 - _m_m11);
        float _m_m21 = m.m21();
        float _m_m12 = m.m12();
        float _m_m01 = m.m01();
        float _m_m10 = m.m10();
        float _m_m02 = m.m02();
        float _m_m20 = m.m20();
        return makeFromMatrix_s37d8ed81_tail_fma(_t14, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), _m_m21 - _m_m12, m, Math.max(_m_m11, _m_m22), _t16, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), _m_m01 + _m_m10, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t18)), _m_m02 + _m_m20, _m_m02 - _m_m20, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), _t17, _m_m12 + _m_m21, _m_m10 - _m_m01, _t18, _t15, -m.m23());
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static FloatDualQuat makeFromMatrix_mulAdd(Float4x4 m) {
        float _m_m00 = m.m00();
        float _t2 = 1.0f - _m_m00;
        float _m_m22 = m.m22();
        float _m_m11 = m.m11();
        float _t14 = _m_m22 + (_m_m00 + _m_m11);
        float _t15 = 1.0f + _t14;
        float _t16 = _m_m00 + (1.0f - _m_m11 - _m_m22);
        float _t17 = _m_m11 + (_t2 - _m_m22);
        float _t18 = _m_m22 + (_t2 - _m_m11);
        float _m_m21 = m.m21();
        float _m_m12 = m.m12();
        float _m_m01 = m.m01();
        float _m_m10 = m.m10();
        float _m_m02 = m.m02();
        float _m_m20 = m.m20();
        return makeFromMatrix_s37d8ed81_tail_mulAdd(_t14, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), _m_m21 - _m_m12, m, Math.max(_m_m11, _m_m22), _t16, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), _m_m01 + _m_m10, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t18)), _m_m02 + _m_m20, _m_m02 - _m_m20, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), _t17, _m_m12 + _m_m21, _m_m10 - _m_m01, _t18, _t15, -m.m23());
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatDualQuat makeFromMatrix_s37cad600_tail_fma(float _t14, float _sp0, float _t4, Float3x4 m, float _t5, float _t16, float _sp1, float _t6, float _sp2, float _t7, float _t8, float _sp3, float _t17, float _t9, float _t10, float _t18, float _t15, float _t0) {
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
        return makeFromMatrix_s37cad600_tail2_fma(_t14, _t15, m, _t5, _sp3, _t4, _sp1, _t8, _sp2, _t10, _t63, _t64, _t65, _t0);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatDualQuat makeFromMatrix_s37cad600_tail_mulAdd(float _t14, float _sp0, float _t4, Float3x4 m, float _t5, float _t16, float _sp1, float _t6, float _sp2, float _t7, float _t8, float _sp3, float _t17, float _t9, float _t10, float _t18, float _t15, float _t0) {
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
        return makeFromMatrix_s37cad600_tail2_mulAdd(_t14, _t15, m, _t5, _sp3, _t4, _sp1, _t8, _sp2, _t10, _t63, _t64, _t65, _t0);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatDualQuat makeFromMatrix_s37cad600_tail2_fma(float _t14, float _t15, Float3x4 m, float _t5, float _sp3, float _t4, float _sp1, float _t8, float _sp2, float _t10, float _t63, float _t64, float _t65, float _t0) {
        float _t66 = _t14 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t15) : m.m00() > _t5 ? _sp3 * _t4 : m.m11() > m.m22() ? _sp1 * _t8 : _sp2 * _t10;
        float _m_m03 = m.m03();
        float _m_m13 = m.m13();
        float _sfx4 = 0.5f * java.lang.Math.fma(_t0, _t64, java.lang.Math.fma(_m_m03, _t66, _m_m13 * _t65));
        float _m_m23 = m.m23();
        float _sfx5 = 0.5f * java.lang.Math.fma(_m_m23, _t63, java.lang.Math.fma(_m_m13, _t66, -_m_m03 * _t65));
        float _sfx6 = 0.5f * java.lang.Math.fma(_m_m23, _t66, java.lang.Math.fma(_m_m03, _t64, -_m_m13 * _t63));
        float _sfx7 = 0.5f * java.lang.Math.fma(_t0, _t65, java.lang.Math.fma(-_m_m13, _t64, -_m_m03 * _t63));
        return new FloatDualQuat(_t63, _t64, _t65, _t66, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatDualQuat makeFromMatrix_s37cad600_tail2_mulAdd(float _t14, float _t15, Float3x4 m, float _t5, float _sp3, float _t4, float _sp1, float _t8, float _sp2, float _t10, float _t63, float _t64, float _t65, float _t0) {
        float _t66 = _t14 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t15) : m.m00() > _t5 ? _sp3 * _t4 : m.m11() > m.m22() ? _sp1 * _t8 : _sp2 * _t10;
        float _m_m03 = m.m03();
        float _m_m13 = m.m13();
        float _sfx4 = 0.5f * ((_t0) * (_t64) + (((_m_m03) * (_t66) + (_m_m13 * _t65))));
        float _m_m23 = m.m23();
        float _sfx5 = 0.5f * ((_m_m23) * (_t63) + (((_m_m13) * (_t66) - (_m_m03 * _t65))));
        float _sfx6 = 0.5f * ((_m_m23) * (_t66) + (((_m_m03) * (_t64) - (_m_m13 * _t63))));
        float _sfx7 = 0.5f * ((_t0) * (_t65) + (((-_m_m13) * (_t64) - (_m_m03 * _t63))));
        return new FloatDualQuat(_t63, _t64, _t65, _t66, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /**
     * Create the rigid motion of the given matrix: rotation from its upper-left 3x3 block and
     * translation from its last column.
     * <p>
     * Valid input: {@code m} must be a rigid transformation (a rotation with any translation).
     *
     * @param m the matrix to convert
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeFromMatrix(Float3x4 m) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeFromMatrix_mulAdd(m);
        float _m_m00 = m.m00();
        float _t2 = 1.0f - _m_m00;
        float _m_m22 = m.m22();
        float _m_m11 = m.m11();
        float _t14 = _m_m22 + (_m_m00 + _m_m11);
        float _t15 = 1.0f + _t14;
        float _t16 = _m_m00 + (1.0f - _m_m11 - _m_m22);
        float _t17 = _m_m11 + (_t2 - _m_m22);
        float _t18 = _m_m22 + (_t2 - _m_m11);
        float _m_m21 = m.m21();
        float _m_m12 = m.m12();
        float _m_m01 = m.m01();
        float _m_m10 = m.m10();
        float _m_m02 = m.m02();
        float _m_m20 = m.m20();
        return makeFromMatrix_s37cad600_tail_fma(_t14, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), _m_m21 - _m_m12, m, Math.max(_m_m11, _m_m22), _t16, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), _m_m01 + _m_m10, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t18)), _m_m02 + _m_m20, _m_m02 - _m_m20, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), _t17, _m_m12 + _m_m21, _m_m10 - _m_m01, _t18, _t15, -m.m23());
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static FloatDualQuat makeFromMatrix_mulAdd(Float3x4 m) {
        float _m_m00 = m.m00();
        float _t2 = 1.0f - _m_m00;
        float _m_m22 = m.m22();
        float _m_m11 = m.m11();
        float _t14 = _m_m22 + (_m_m00 + _m_m11);
        float _t15 = 1.0f + _t14;
        float _t16 = _m_m00 + (1.0f - _m_m11 - _m_m22);
        float _t17 = _m_m11 + (_t2 - _m_m22);
        float _t18 = _m_m22 + (_t2 - _m_m11);
        float _m_m21 = m.m21();
        float _m_m12 = m.m12();
        float _m_m01 = m.m01();
        float _m_m10 = m.m10();
        float _m_m02 = m.m02();
        float _m_m20 = m.m20();
        return makeFromMatrix_s37cad600_tail_mulAdd(_t14, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), _m_m21 - _m_m12, m, Math.max(_m_m11, _m_m22), _t16, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), _m_m01 + _m_m10, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t18)), _m_m02 + _m_m20, _m_m02 - _m_m20, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), _t17, _m_m12 + _m_m21, _m_m10 - _m_m01, _t18, _t15, -m.m23());
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
        float _m_m00 = m.m00();
        float _t1 = 1.0f - _m_m00;
        float _m_m22 = m.m22();
        float _m_m11 = m.m11();
        float _t13 = _m_m22 + (_m_m00 + _m_m11);
        float _t14 = 1.0f + _t13;
        float _t15 = _m_m00 + (1.0f - _m_m11 - _m_m22);
        float _t16 = _m_m11 + (_t1 - _m_m22);
        float _t17 = _m_m22 + (_t1 - _m_m11);
        float _m_m21 = m.m21();
        float _m_m12 = m.m12();
        float _m_m01 = m.m01();
        float _m_m10 = m.m10();
        float _m_m02 = m.m02();
        float _m_m20 = m.m20();
        return makeFromMatrix_s37cad23f_tail(_t13, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), _m_m21 - _m_m12, m, Math.max(_m_m11, _m_m22), _t15, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), _m_m01 + _m_m10, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), _m_m02 + _m_m20, _m_m02 - _m_m20, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), _t16, _m_m12 + _m_m21, _m_m10 - _m_m01, _t17, _t14);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t4 = java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY) + java.lang.Math.fma(this.rZ, this.rZ, this.rW * this.rW);
            float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
            if (_t4 != 0.0f) {
                return new FloatDualQuat(this.rX * _t5, this.rY * _t5, this.rZ * _t5, this.rW * _t5, this.dX * _t5, this.dY * _t5, this.dZ * _t5, this.dW * _t5);
            } else {
                return FloatDualQuat.ZERO;
            }
        } else {
            float _t4 = ((this.rX) * (this.rX) + (this.rY * this.rY)) + ((this.rZ) * (this.rZ) + (this.rW * this.rW));
            float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
            if (_t4 != 0.0f) {
                return new FloatDualQuat(this.rX * _t5, this.rY * _t5, this.rZ * _t5, this.rW * _t5, this.dX * _t5, this.dY * _t5, this.dZ * _t5, this.dW * _t5);
            } else {
                return FloatDualQuat.ZERO;
            }
        }
    }

    /** Private tail of {@code pow}; reached only through it. */
    private FloatDualQuat pow_s5250ffb0_tail_fma(float _t19, float _t10, float _t8, float _t13, float t, float _t18, float _t11, float _t9, float _t21, float _t12, float _t14, float _t15) {
        float _t25 = _t19 * _t13;
        float _t26 = t * Math.atan2((float) java.lang.Math.sqrt(_t18), _t11);
        float _t27 = t * _t25;
        float _t29 = Math.cosWithSin(_t26);
        return pow_s5250ffb0_tail2_fma(_t18, t, _t9, _t21, Math.sinWithCos(_t26), _t10, _t19 * _t10, _t8, _t19 * _t8, _t29, _t12, _t25 * _t11, _t19, _t27 * _t29, _t14, _t15, _t13, _t27);
    }

    /** Private tail of {@code pow}; reached only through it. */
    private FloatDualQuat pow_s5250ffb0_tail_mulAdd(float _t19, float _t10, float _t8, float _t13, float t, float _t18, float _t11, float _t9, float _t21, float _t12, float _t14, float _t15) {
        float _t25 = _t19 * _t13;
        float _t26 = t * Math.atan2((float) java.lang.Math.sqrt(_t18), _t11);
        float _t27 = t * _t25;
        float _t29 = Math.cosWithSin(_t26);
        return pow_s5250ffb0_tail2_mulAdd(_t18, t, _t9, _t21, Math.sinWithCos(_t26), _t10, _t19 * _t10, _t8, _t19 * _t8, _t29, _t12, _t25 * _t11, _t19, _t27 * _t29, _t14, _t15, _t13, _t27);
    }

    /** Private tail of {@code pow}; reached only through it. */
    private FloatDualQuat pow_s5250ffb0_tail2_fma(float _t18, float t, float _t9, float _t21, float _t28, float _t10, float _t23, float _t8, float _t24, float _t29, float _t12, float _t30, float _t19, float _t31, float _t14, float _t15, float _t13, float _t27) {
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
            _sfx3 = _t29;
            _sfx4 = java.lang.Math.fma(java.lang.Math.fma(_t30, _t21, _t12) * _t19, _t28, -(_t31 * _t21));
            _sfx5 = java.lang.Math.fma(java.lang.Math.fma(_t30, _t23, _t14) * _t19, _t28, -(_t31 * _t23));
            _sfx6 = java.lang.Math.fma(java.lang.Math.fma(_t30, _t24, _t15) * _t19, _t28, -(_t31 * _t24));
            _sfx7 = _t27 * _t28;
        }
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code pow}; reached only through it. */
    private FloatDualQuat pow_s5250ffb0_tail2_mulAdd(float _t18, float t, float _t9, float _t21, float _t28, float _t10, float _t23, float _t8, float _t24, float _t29, float _t12, float _t30, float _t19, float _t31, float _t14, float _t15, float _t13, float _t27) {
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
            _sfx3 = _t29;
            _sfx4 = ((((_t30) * (_t21) + (_t12)) * _t19) * (_t28) - (_t31 * _t21));
            _sfx5 = ((((_t30) * (_t23) + (_t14)) * _t19) * (_t28) - (_t31 * _t23));
            _sfx6 = ((((_t30) * (_t24) + (_t15)) * _t19) * (_t28) - (_t31 * _t24));
            _sfx7 = _t27 * _t28;
        }
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /**
     * Raise this dual quaternion to the power of {@code t} (screw-motion power: {@code t = 0}
     * yields the identity, {@code t = 1} yields {@code this}), returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code t} must lie in
     * {@code [-4096, 4096]}.
     *
     * @param t the exponent
     * @return the resulting dual quaternion
     */
    public FloatDualQuat pow(float t) {
        if (!org.joml2.internal.FmaSwitch.ON) return pow_mulAdd(t);
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
        float _t18 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        return pow_s5250ffb0_tail_fma(_t19, _t10, _t8, _t13, t, _t18, _t11, _t9, _t19 * _t9, _t12, _t14, _t15);
    }

    /** {@code pow} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat pow_mulAdd(float t) {
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
        float _t18 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        return pow_s5250ffb0_tail_mulAdd(_t19, _t10, _t8, _t13, t, _t18, _t11, _t9, _t19 * _t9, _t12, _t14, _t15);
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
        if (!org.joml2.internal.FmaSwitch.ON) return setRotation_mulAdd(rotationX, rotationY, rotationZ, rotationW);
        float _t0 = -rotationY;
        float _t22 = 2.0f * (java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
        float _t23 = 2.0f * (java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW));
        float _t24 = 2.0f * (java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW));
        return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * java.lang.Math.fma(_t0, _t22, java.lang.Math.fma(rotationZ, _t23, rotationW * _t24)), 0.5f * java.lang.Math.fma(rotationX, _t22, java.lang.Math.fma(rotationW, _t23, -rotationZ * _t24)), 0.5f * java.lang.Math.fma(rotationW, _t22, java.lang.Math.fma(rotationY, _t24, -rotationX * _t23)), 0.5f * java.lang.Math.fma(-rotationZ, _t22, java.lang.Math.fma(_t0, _t23, -rotationX * _t24)));
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
        if (!org.joml2.internal.FmaSwitch.ON) return setRotation_mulAdd(rotationX, rotationY, rotationZ, rotationW);
        float _t0 = -rotationY;
        float _t22 = 2.0f * (java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
        float _t23 = 2.0f * (java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW));
        float _t24 = 2.0f * (java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW));
        return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * java.lang.Math.fma(_t0, _t22, java.lang.Math.fma(rotationZ, _t23, rotationW * _t24)), 0.5f * java.lang.Math.fma(rotationX, _t22, java.lang.Math.fma(rotationW, _t23, -rotationZ * _t24)), 0.5f * java.lang.Math.fma(rotationW, _t22, java.lang.Math.fma(rotationY, _t24, -rotationX * _t23)), 0.5f * java.lang.Math.fma(-rotationZ, _t22, java.lang.Math.fma(_t0, _t23, -rotationX * _t24)));
    }

    /** {@code setRotation} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat setRotation_mulAdd(float rotationX, float rotationY, float rotationZ, float rotationW) {
        float _t0 = -rotationY;
        float _t22 = 2.0f * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW)));
        float _t23 = 2.0f * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW)));
        float _t24 = 2.0f * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW)));
        return new FloatDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5f * ((_t0) * (_t22) + (((rotationZ) * (_t23) + (rotationW * _t24)))), 0.5f * ((rotationX) * (_t22) + (((rotationW) * (_t23) - (rotationZ * _t24)))), 0.5f * ((rotationW) * (_t22) + (((rotationY) * (_t24) - (rotationX * _t23)))), 0.5f * ((((_t0) * (_t23) - (rotationX * _t24))) - (rotationZ) * (_t22)));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = -this.rY;
            return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5f * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(this.rZ, translationY, this.rW * translationX)), 0.5f * java.lang.Math.fma(this.rX, translationZ, java.lang.Math.fma(this.rW, translationY, -this.rZ * translationX)), 0.5f * java.lang.Math.fma(this.rW, translationZ, java.lang.Math.fma(this.rY, translationX, -this.rX * translationY)), 0.5f * java.lang.Math.fma(-this.rZ, translationZ, java.lang.Math.fma(_t0, translationY, -this.rX * translationX)));
        } else {
            float _t0 = -this.rY;
            return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5f * ((_t0) * (translationZ) + (((this.rZ) * (translationY) + (this.rW * translationX)))), 0.5f * ((this.rX) * (translationZ) + (((this.rW) * (translationY) - (this.rZ * translationX)))), 0.5f * ((this.rW) * (translationZ) + (((this.rY) * (translationX) - (this.rX * translationY)))), 0.5f * ((((_t0) * (translationY) - (this.rX * translationX))) - (this.rZ) * (translationZ)));
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = -this.rY;
            return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5f * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(this.rZ, translationY, this.rW * translationX)), 0.5f * java.lang.Math.fma(this.rX, translationZ, java.lang.Math.fma(this.rW, translationY, -this.rZ * translationX)), 0.5f * java.lang.Math.fma(this.rW, translationZ, java.lang.Math.fma(this.rY, translationX, -this.rX * translationY)), 0.5f * java.lang.Math.fma(-this.rZ, translationZ, java.lang.Math.fma(_t0, translationY, -this.rX * translationX)));
        } else {
            float _t0 = -this.rY;
            return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5f * ((_t0) * (translationZ) + (((this.rZ) * (translationY) + (this.rW * translationX)))), 0.5f * ((this.rX) * (translationZ) + (((this.rW) * (translationY) - (this.rZ * translationX)))), 0.5f * ((this.rW) * (translationZ) + (((this.rY) * (translationX) - (this.rX * translationY)))), 0.5f * ((((_t0) * (translationY) - (this.rX * translationX))) - (this.rZ) * (translationZ)));
        }
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c0_fma(float _t0, float _t6, float _t2, float _t3, float _sp0, float[] _m) {
        _m[0] = java.lang.Math.fma(-2.0f, _t0, _t6);
        _m[4] = 2.0f * java.lang.Math.fma(this.rX, this.rY, _t2);
        _m[8] = java.lang.Math.fma(-2.0f, _t3, _sp0 * this.rZ);
        _m[12] = 0.0f;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c1_fma(float _t2, float _sp0, float _t4, float _t6, float _t5, float[] _m) {
        _m[1] = java.lang.Math.fma(-2.0f, _t2, _sp0 * this.rY);
        _m[5] = java.lang.Math.fma(-2.0f, _t4, _t6);
        _m[9] = 2.0f * java.lang.Math.fma(this.rX, this.rW, _t5);
        _m[13] = 0.0f;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c2_fma(float _t3, float _t5, float _t4, float _t0, float[] _m) {
        _m[2] = 2.0f * java.lang.Math.fma(this.rX, this.rZ, _t3);
        _m[6] = java.lang.Math.fma(-2.0f, this.rX * this.rW, _t5 + _t5);
        _m[10] = java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f));
        _m[14] = 0.0f;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c2_mulAdd(float _t3, float _t5, float _t4, float _t0, float[] _m) {
        _m[2] = 2.0f * ((this.rX) * (this.rZ) + (_t3));
        _m[6] = ((_t5 + _t5) - (2.0f) * (this.rX * this.rW));
        _m[10] = ((((1.0f) - (2.0f) * (_t0))) - (2.0f) * (_t4));
        _m[14] = 0.0f;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c3_fma(float[] _m) {
        _m[3] = 2.0f * (java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW));
        _m[7] = 2.0f * (java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW));
        _m[11] = 2.0f * (java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, this.rZ * -this.dW));
        _m[15] = 1.0f;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c3_mulAdd(float[] _m) {
        _m[3] = 2.0f * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW)));
        _m[7] = 2.0f * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW)));
        _m[11] = 2.0f * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW)));
        _m[15] = 1.0f;
    }

    /**
     * Compute the matrix representation of this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the resulting matrix
     */
    public Float4x4 toMatrix() {
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix_mulAdd();
        float _sp0 = this.rX + this.rX;
        float _t0 = this.rY * this.rY;
        float _t2 = this.rZ * this.rW;
        float _t3 = this.rY * this.rW;
        float _t4 = this.rX * this.rX;
        float _t5 = this.rY * this.rZ;
        float _t6 = java.lang.Math.fma(-2.0f, this.rZ * this.rZ, 1.0f);
        float[] _m = new float[16];
        toMatrix_s0_c0_fma(_t0, _t6, _t2, _t3, _sp0, _m);
        toMatrix_s0_c1_fma(_t2, _sp0, _t4, _t6, _t5, _m);
        toMatrix_s0_c2_fma(_t3, _t5, _t4, _t0, _m);
        toMatrix_s0_c3_fma(_m);
        return new Float4x4(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7], _m[8], _m[9], _m[10], _m[11], _m[12], _m[13], _m[14], _m[15], Joml.BIT_ORTHOGONAL);
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x4 toMatrix_mulAdd() {
        float _sp0 = this.rX + this.rX;
        float _t0 = this.rY * this.rY;
        float _t2 = this.rZ * this.rW;
        float _t3 = this.rY * this.rW;
        float _t4 = this.rX * this.rX;
        float _t5 = this.rY * this.rZ;
        float _t6 = ((1.0f) - (2.0f) * (this.rZ * this.rZ));
        float[] _m = new float[16];
        _m[0] = ((_t6) - (2.0f) * (_t0));
        _m[4] = 2.0f * ((this.rX) * (this.rY) + (_t2));
        _m[8] = ((_sp0 * this.rZ) - (2.0f) * (_t3));
        _m[12] = 0.0f;
        _m[1] = ((_sp0 * this.rY) - (2.0f) * (_t2));
        _m[5] = ((_t6) - (2.0f) * (_t4));
        _m[9] = 2.0f * ((this.rX) * (this.rW) + (_t5));
        _m[13] = 0.0f;
        toMatrix_s0_c2_mulAdd(_t3, _t5, _t4, _t0, _m);
        toMatrix_s0_c3_mulAdd(_m);
        return new Float4x4(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7], _m[8], _m[9], _m[10], _m[11], _m[12], _m[13], _m[14], _m[15], Joml.BIT_ORTHOGONAL);
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
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix3x3_mulAdd();
        float _sp0 = this.rX + this.rX;
        float _t0 = this.rY * this.rY;
        float _t2 = this.rZ * this.rW;
        float _t3 = this.rY * this.rW;
        float _t4 = this.rX * this.rX;
        float _t5 = this.rY * this.rZ;
        float _t6 = java.lang.Math.fma(-2.0f, this.rZ * this.rZ, 1.0f);
        return new Float3x3(java.lang.Math.fma(-2.0f, _t0, _t6), java.lang.Math.fma(-2.0f, _t2, _sp0 * this.rY), 2.0f * java.lang.Math.fma(this.rX, this.rZ, _t3), 2.0f * java.lang.Math.fma(this.rX, this.rY, _t2), java.lang.Math.fma(-2.0f, _t4, _t6), java.lang.Math.fma(-2.0f, this.rX * this.rW, _t5 + _t5), java.lang.Math.fma(-2.0f, _t3, _sp0 * this.rZ), 2.0f * java.lang.Math.fma(this.rX, this.rW, _t5), java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f)), 0);
    }

    /** {@code toMatrix3x3} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x3 toMatrix3x3_mulAdd() {
        float _sp0 = this.rX + this.rX;
        float _t0 = this.rY * this.rY;
        float _t2 = this.rZ * this.rW;
        float _t3 = this.rY * this.rW;
        float _t4 = this.rX * this.rX;
        float _t5 = this.rY * this.rZ;
        float _t6 = ((1.0f) - (2.0f) * (this.rZ * this.rZ));
        return new Float3x3(((_t6) - (2.0f) * (_t0)), ((_sp0 * this.rY) - (2.0f) * (_t2)), 2.0f * ((this.rX) * (this.rZ) + (_t3)), 2.0f * ((this.rX) * (this.rY) + (_t2)), ((_t6) - (2.0f) * (_t4)), ((_t5 + _t5) - (2.0f) * (this.rX * this.rW)), ((_sp0 * this.rZ) - (2.0f) * (_t3)), 2.0f * ((this.rX) * (this.rW) + (_t5)), ((((1.0f) - (2.0f) * (_t0))) - (2.0f) * (_t4)), 0);
    }

    /** Private per-column body of {@code toMatrix3x4}, stored into {@code _m}; reached only through it. */
    private void toMatrix3x4_s0_c0_fma(float _t0, float _t6, float _t2, float _t3, float _sp0, float[] _m) {
        _m[0] = java.lang.Math.fma(-2.0f, _t0, _t6);
        _m[4] = 2.0f * java.lang.Math.fma(this.rX, this.rY, _t2);
        _m[8] = java.lang.Math.fma(-2.0f, _t3, _sp0 * this.rZ);
    }

    /** Private per-column body of {@code toMatrix3x4}, stored into {@code _m}; reached only through it. */
    private void toMatrix3x4_s0_c1_fma(float _t2, float _sp0, float _t4, float _t6, float _t5, float[] _m) {
        _m[1] = java.lang.Math.fma(-2.0f, _t2, _sp0 * this.rY);
        _m[5] = java.lang.Math.fma(-2.0f, _t4, _t6);
        _m[9] = 2.0f * java.lang.Math.fma(this.rX, this.rW, _t5);
    }

    /** Private per-column body of {@code toMatrix3x4}, stored into {@code _m}; reached only through it. */
    private void toMatrix3x4_s0_c2_fma(float _t3, float _t5, float _t4, float _t0, float[] _m) {
        _m[2] = 2.0f * java.lang.Math.fma(this.rX, this.rZ, _t3);
        _m[6] = java.lang.Math.fma(-2.0f, this.rX * this.rW, _t5 + _t5);
        _m[10] = java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f));
    }

    /** Private per-column body of {@code toMatrix3x4}, stored into {@code _m}; reached only through it. */
    private void toMatrix3x4_s0_c3_fma(float[] _m) {
        _m[3] = 2.0f * (java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW));
        _m[7] = 2.0f * (java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW));
        _m[11] = 2.0f * (java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, this.rZ * -this.dW));
    }

    /** Private per-column body of {@code toMatrix3x4}, stored into {@code _m}; reached only through it. */
    private void toMatrix3x4_s0_c3_mulAdd(float[] _m) {
        _m[3] = 2.0f * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW)));
        _m[7] = 2.0f * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW)));
        _m[11] = 2.0f * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW)));
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
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix3x4_mulAdd();
        float _sp0 = this.rX + this.rX;
        float _t0 = this.rY * this.rY;
        float _t2 = this.rZ * this.rW;
        float _t3 = this.rY * this.rW;
        float _t4 = this.rX * this.rX;
        float _t5 = this.rY * this.rZ;
        float _t6 = java.lang.Math.fma(-2.0f, this.rZ * this.rZ, 1.0f);
        float[] _m = new float[12];
        toMatrix3x4_s0_c0_fma(_t0, _t6, _t2, _t3, _sp0, _m);
        toMatrix3x4_s0_c1_fma(_t2, _sp0, _t4, _t6, _t5, _m);
        toMatrix3x4_s0_c2_fma(_t3, _t5, _t4, _t0, _m);
        toMatrix3x4_s0_c3_fma(_m);
        return new Float3x4(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7], _m[8], _m[9], _m[10], _m[11], Joml.BIT_ORTHOGONAL);
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x4 toMatrix3x4_mulAdd() {
        float _sp0 = this.rX + this.rX;
        float _t0 = this.rY * this.rY;
        float _t2 = this.rZ * this.rW;
        float _t3 = this.rY * this.rW;
        float _t4 = this.rX * this.rX;
        float _t5 = this.rY * this.rZ;
        float _t6 = ((1.0f) - (2.0f) * (this.rZ * this.rZ));
        float[] _m = new float[12];
        _m[0] = ((_t6) - (2.0f) * (_t0));
        _m[4] = 2.0f * ((this.rX) * (this.rY) + (_t2));
        _m[8] = ((_sp0 * this.rZ) - (2.0f) * (_t3));
        _m[1] = ((_sp0 * this.rY) - (2.0f) * (_t2));
        _m[5] = ((_t6) - (2.0f) * (_t4));
        _m[9] = 2.0f * ((this.rX) * (this.rW) + (_t5));
        _m[2] = 2.0f * ((this.rX) * (this.rZ) + (_t3));
        _m[6] = ((_t5 + _t5) - (2.0f) * (this.rX * this.rW));
        _m[10] = ((((1.0f) - (2.0f) * (_t0))) - (2.0f) * (_t4));
        toMatrix3x4_s0_c3_mulAdd(_m);
        return new Float3x4(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7], _m[8], _m[9], _m[10], _m[11], Joml.BIT_ORTHOGONAL);
    }

    /**
     * Normalize this dual quaternion so that its real (rotation) part has unit length, unless that
     * part has zero length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the normalized dual quaternion, or an empty {@code Optional} when the real part has
     *        zero length
     */
    public java.util.Optional<FloatDualQuat> tryNormalize() {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _ct0 = java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY) + java.lang.Math.fma(this.rZ, this.rZ, this.rW * this.rW);
            if (!(_ct0 > 0.0f)) return java.util.Optional.empty();
            float _t6 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
            return java.util.Optional.of(new FloatDualQuat(this.rX * _t6, this.rY * _t6, this.rZ * _t6, this.rW * _t6, this.dX * _t6, this.dY * _t6, this.dZ * _t6, this.dW * _t6));
        } else {
            float _ct0 = ((this.rX) * (this.rX) + (this.rY * this.rY)) + ((this.rZ) * (this.rZ) + (this.rW * this.rW));
            if (!(_ct0 > 0.0f)) return java.util.Optional.empty();
            float _t6 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
            return java.util.Optional.of(new FloatDualQuat(this.rX * _t6, this.rY * _t6, this.rZ * _t6, this.rW * _t6, this.dX * _t6, this.dY * _t6, this.dZ * _t6, this.dW * _t6));
        }
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_lh_fma(Float3 dir, Float3 up) {
        return lookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_lh_mulAdd(Float3 dir, Float3 up) {
        return lookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        float _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        float _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        float _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return lookAlong_lh_s5402847e_1_fma(dirX, dirY, dirZ, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45, _t38 * _t45, java.lang.Math.fma(-_t36, _t45, 1.0f));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = ((upY) - (dirY) * (_sp0));
        float _t28 = ((upZ) - (dirZ) * (_sp0));
        float _t29 = ((upX) - (dirX) * (_sp0));
        float _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        float _t37 = ((dirY) * (_t29) - (dirX * _t27));
        float _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        float _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return lookAlong_lh_s5402847e_1_mulAdd(dirX, dirY, dirZ, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45, _t38 * _t45, ((1.0f) - (_t36) * (_t45)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_lh_s5402847e_1_fma(float dirX, float dirY, float dirZ, float _t15, float _t36, float _t37, float _t38, float _t45, float _t0, float _t16, float _t17, float _t18, float _t19, float _t21, float _t46, float _t47, float _t48, float _t49) {
        float _t64 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        float _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        float _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0f))));
        float _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0f))));
        float _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        float _t83 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49)));
        return lookAlong_lh_s5402847e_2_fma(dirZ, _t15, _t36, _t45, _t16, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t64), java.lang.Math.fma(dirY, _t15, -_t64), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), _t83, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t83)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_lh_s5402847e_1_mulAdd(float dirX, float dirY, float dirZ, float _t15, float _t36, float _t37, float _t38, float _t45, float _t0, float _t16, float _t17, float _t18, float _t19, float _t21, float _t46, float _t47, float _t48, float _t49) {
        float _t64 = ((_t17) * (_t48) - (_t18 * _t46));
        float _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        float _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0f))))))));
        float _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0f))))))));
        float _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        float _t83 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49))))));
        return lookAlong_lh_s5402847e_2_mulAdd(dirZ, _t15, _t36, _t45, _t16, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t64)), ((dirY) * (_t15) - (_t64)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), _t83, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t83)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_lh_s5402847e_2_fma(float dirZ, float _t15, float _t36, float _t45, float _t16, float _t46, float _t50, float _t54, float _t62, float _t68, float _t70, float _t73, float _t74, float _t77, float _t79, float _t80, float _sp1, float _sp4, float _t83, float _sp3, float _sp2) {
        float _t125, _t126, _t127, _t128;
        if (java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t36, _t45, _t62)) > 0.0f) {
            _t125 = _sp1 * _t70;
            _t126 = _sp1 * _t74;
            _t127 = _sp1 * _t54;
            _t128 = 0.5f * (float) java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _t125 = 0.5f * (float) java.lang.Math.sqrt(_t79);
                _t126 = _sp4 * _t50;
                _t127 = _sp4 * _t73;
                _t128 = _sp4 * _t70;
            } else {
                if (_t62 > _t16) {
                    _t125 = _sp2 * _t73;
                    _t126 = _sp2 * _t68;
                    _t127 = 0.5f * (float) java.lang.Math.sqrt(_t83);
                    _t128 = _sp2 * _t54;
                } else {
                    _t125 = _sp3 * _t50;
                    _t126 = 0.5f * (float) java.lang.Math.sqrt(_t80);
                    _t127 = _sp3 * _t68;
                    _t128 = _sp3 * _t74;
                }
            }
        }
        return lookAlong_lh_s5402847e_3_fma(_t125, _t126, _t127, _t128, java.lang.Math.fma(this.rX, _t128, this.rW * _t125) + java.lang.Math.fma(this.rY, _t126, -(this.rZ * _t127)), java.lang.Math.fma(this.rY, _t128, this.rZ * _t125) + java.lang.Math.fma(this.rW, _t127, -this.rX * _t126), java.lang.Math.fma(this.rX, _t127, this.rW * _t126) + java.lang.Math.fma(this.rZ, _t128, -(this.rY * _t125)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_lh_s5402847e_2_mulAdd(float dirZ, float _t15, float _t36, float _t45, float _t16, float _t46, float _t50, float _t54, float _t62, float _t68, float _t70, float _t73, float _t74, float _t77, float _t79, float _t80, float _sp1, float _sp4, float _t83, float _sp3, float _sp2) {
        float _t125, _t126, _t127, _t128;
        if (((dirZ) * (_t15) + (((_t36) * (_t45) + (_t62)))) > 0.0f) {
            _t125 = _sp1 * _t70;
            _t126 = _sp1 * _t74;
            _t127 = _sp1 * _t54;
            _t128 = 0.5f * (float) java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _t125 = 0.5f * (float) java.lang.Math.sqrt(_t79);
                _t126 = _sp4 * _t50;
                _t127 = _sp4 * _t73;
                _t128 = _sp4 * _t70;
            } else {
                if (_t62 > _t16) {
                    _t125 = _sp2 * _t73;
                    _t126 = _sp2 * _t68;
                    _t127 = 0.5f * (float) java.lang.Math.sqrt(_t83);
                    _t128 = _sp2 * _t54;
                } else {
                    _t125 = _sp3 * _t50;
                    _t126 = 0.5f * (float) java.lang.Math.sqrt(_t80);
                    _t127 = _sp3 * _t68;
                    _t128 = _sp3 * _t74;
                }
            }
        }
        return lookAlong_lh_s5402847e_3_mulAdd(_t125, _t126, _t127, _t128, ((this.rX) * (_t128) + (this.rW * _t125)) + ((this.rY) * (_t126) - (this.rZ * _t127)), ((this.rY) * (_t128) + (this.rZ * _t125)) + ((this.rW) * (_t127) - (this.rX * _t126)), ((this.rX) * (_t127) + (this.rW * _t126)) + ((this.rZ) * (_t128) - (this.rY * _t125)));
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_lh_s5402847e_3_fma(float _t125, float _t126, float _t127, float _t128, float _sfx0, float _sfx1, float _sfx2) {
        float _sfx3 = java.lang.Math.fma(this.rW, _t128, -this.rX * _t125) - java.lang.Math.fma(this.rY, _t127, this.rZ * _t126);
        float _sfx4 = java.lang.Math.fma(this.dX, _t128, this.dW * _t125) + java.lang.Math.fma(this.dY, _t126, -(this.dZ * _t127));
        float _sfx5 = java.lang.Math.fma(this.dY, _t128, this.dZ * _t125) + java.lang.Math.fma(this.dW, _t127, -this.dX * _t126);
        float _sfx6 = java.lang.Math.fma(this.dX, _t127, this.dW * _t126) + java.lang.Math.fma(this.dZ, _t128, -(this.dY * _t125));
        float _sfx7 = java.lang.Math.fma(this.dW, _t128, -this.dX * _t125) - java.lang.Math.fma(this.dY, _t127, this.dZ * _t126);
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_lh_s5402847e_3_mulAdd(float _t125, float _t126, float _t127, float _t128, float _sfx0, float _sfx1, float _sfx2) {
        float _el3 = ((this.rW) * (_t128) - (this.rX * _t125)) - ((this.rY) * (_t127) + (this.rZ * _t126));
        float _el4 = ((this.dX) * (_t128) + (this.dW * _t125)) + ((this.dY) * (_t126) - (this.dZ * _t127));
        float _el5 = ((this.dY) * (_t128) + (this.dZ * _t125)) + ((this.dW) * (_t127) - (this.dX * _t126));
        float _el6 = ((this.dX) * (_t127) + (this.dW * _t126)) + ((this.dZ) * (_t128) - (this.dY * _t125));
        float _el7 = ((this.dW) * (_t128) - (this.dX * _t125)) - ((this.dY) * (_t127) + (this.dZ * _t126));
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _el3, _el4, _el5, _el6, _el7);
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_lh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        float _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return lookAlong_lh_degenerate_sae57cfe3_2_fma(_t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_lh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t25) + (_t22));
        float _t46 = ((_t44) * (_t26) + (_t23));
        float _t47 = ((_t44) * (_t24) + (_t21));
        return lookAlong_lh_degenerate_sae57cfe3_2_mulAdd(_t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /**
     * Part 1 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_lh_degenerate_sae57cfe3_1_fma(float _t152, float _t149, float _t150, float _t151) {
        float _sfx0 = java.lang.Math.fma(this.rX, _t152, this.rW * _t149) + java.lang.Math.fma(this.rY, _t150, -(this.rZ * _t151));
        float _sfx1 = java.lang.Math.fma(this.rY, _t152, this.rZ * _t149) + java.lang.Math.fma(this.rW, _t151, -this.rX * _t150);
        float _sfx2 = java.lang.Math.fma(this.rX, _t151, this.rW * _t150) + java.lang.Math.fma(this.rZ, _t152, -(this.rY * _t149));
        float _sfx3 = java.lang.Math.fma(this.rW, _t152, -this.rX * _t149) - java.lang.Math.fma(this.rY, _t151, this.rZ * _t150);
        float _sfx4 = java.lang.Math.fma(this.dX, _t152, this.dW * _t149) + java.lang.Math.fma(this.dY, _t150, -(this.dZ * _t151));
        float _sfx5 = java.lang.Math.fma(this.dY, _t152, this.dZ * _t149) + java.lang.Math.fma(this.dW, _t151, -this.dX * _t150);
        float _sfx6 = java.lang.Math.fma(this.dX, _t151, this.dW * _t150) + java.lang.Math.fma(this.dZ, _t152, -(this.dY * _t149));
        float _sfx7 = java.lang.Math.fma(this.dW, _t152, -this.dX * _t149) - java.lang.Math.fma(this.dY, _t151, this.dZ * _t150);
        return new FloatDualQuat((_sfx0), (_sfx1), (_sfx2), (_sfx3), (_sfx4), (_sfx5), (_sfx6), (_sfx7));
    }

    /**
     * Part 1 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_lh_degenerate_sae57cfe3_1_mulAdd(float _t152, float _t149, float _t150, float _t151) {
        float _sfx0 = ((this.rX) * (_t152) + (this.rW * _t149)) + ((this.rY) * (_t150) - (this.rZ * _t151));
        float _sfx1 = ((this.rY) * (_t152) + (this.rZ * _t149)) + ((this.rW) * (_t151) - (this.rX * _t150));
        float _sfx2 = ((this.rX) * (_t151) + (this.rW * _t150)) + ((this.rZ) * (_t152) - (this.rY * _t149));
        float _sfx3 = ((this.rW) * (_t152) - (this.rX * _t149)) - ((this.rY) * (_t151) + (this.rZ * _t150));
        float _sfx4 = ((this.dX) * (_t152) + (this.dW * _t149)) + ((this.dY) * (_t150) - (this.dZ * _t151));
        float _sfx5 = ((this.dY) * (_t152) + (this.dZ * _t149)) + ((this.dW) * (_t151) - (this.dX * _t150));
        float _sfx6 = ((this.dX) * (_t151) + (this.dW * _t150)) + ((this.dZ) * (_t152) - (this.dY * _t149));
        float _sfx7 = ((this.dW) * (_t152) - (this.dX * _t149)) - ((this.dY) * (_t151) + (this.dZ * _t150));
        return new FloatDualQuat((_sfx0), (_sfx1), (_sfx2), (_sfx3), (_sfx4), (_sfx5), (_sfx6), (_sfx7));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_lh_degenerate_sae57cfe3_2_fma(float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t56, float _t57, float _t58) {
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        float _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33)));
        return lookAlong_lh_degenerate_sae57cfe3_3_fma(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))), _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_lh_degenerate_sae57cfe3_2_mulAdd(float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t56, float _t57, float _t58) {
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        float _t100 = ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33))))));
        return lookAlong_lh_degenerate_sae57cfe3_3_mulAdd(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))), _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_lh_degenerate_sae57cfe3_3_fma(float _t24, float _t25, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t78, float _t89, float _t93, float _t96, float _t97, float _t99, float _t100, float _sp3) {
        float _t103 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t104 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104));
        float _t115 = java.lang.Math.fma(_t67, _t65, _t93);
        float _t116 = java.lang.Math.fma(_t68, _t65, _t93);
        float _t149, _t150, _t151, _t152;
        if (java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t24))) > 0.0f) {
            _t149 = _sp0 * _t96;
            _t150 = _sp0 * _t116;
            _t151 = _sp0 * _t78;
            _t152 = 0.5f * (float) java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                _t149 = 0.5f * (float) java.lang.Math.sqrt(_t100);
                _t150 = _sp3 * _t74;
                _t151 = _sp3 * _t115;
                _t152 = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    _t149 = _sp1 * _t115;
                    _t150 = _sp1 * _t97;
                    _t151 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t152 = _sp1 * _t78;
                } else {
                    _t149 = _sp2 * _t74;
                    _t150 = 0.5f * (float) java.lang.Math.sqrt(_t104);
                    _t151 = _sp2 * _t97;
                    _t152 = _sp2 * _t116;
                }
            }
        }
        return lookAlong_lh_degenerate_sae57cfe3_1_fma(_t152, _t149, _t150, _t151);
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_lh_degenerate_sae57cfe3_3_mulAdd(float _t24, float _t25, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t78, float _t89, float _t93, float _t96, float _t97, float _t99, float _t100, float _sp3) {
        float _t103 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        float _t104 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104));
        float _t115 = ((_t67) * (_t65) + (_t93));
        float _t116 = ((_t68) * (_t65) + (_t93));
        float _t149, _t150, _t151, _t152;
        if (((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t24)))))) > 0.0f) {
            _t149 = _sp0 * _t96;
            _t150 = _sp0 * _t116;
            _t151 = _sp0 * _t78;
            _t152 = 0.5f * (float) java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                _t149 = 0.5f * (float) java.lang.Math.sqrt(_t100);
                _t150 = _sp3 * _t74;
                _t151 = _sp3 * _t115;
                _t152 = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    _t149 = _sp1 * _t115;
                    _t150 = _sp1 * _t97;
                    _t151 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t152 = _sp1 * _t78;
                } else {
                    _t149 = _sp2 * _t74;
                    _t150 = 0.5f * (float) java.lang.Math.sqrt(_t104);
                    _t151 = _sp2 * _t97;
                    _t152 = _sp2 * _t116;
                }
            }
        }
        return lookAlong_lh_degenerate_sae57cfe3_1_mulAdd(_t152, _t149, _t150, _t151);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_rh_fma(Float3 dir, Float3 up) {
        return lookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_rh_mulAdd(Float3 dir, Float3 up) {
        return lookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        float _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        float _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        float _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return lookAlong_rh_sa6198d78_1_fma(dirX, dirY, _t16, _t38, _t39, _t40, _t47, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0f), -_t38, _t39 * _t47, _t38 * _t47, _t40 * _t47);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        float _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return lookAlong_rh_sa6198d78_1_mulAdd(dirX, dirY, _t16, _t38, _t39, _t40, _t47, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0f)), -_t38, _t39 * _t47, _t38 * _t47, _t40 * _t47);
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_sa6198d78_1_fma(float dirX, float dirY, float _t16, float _t38, float _t39, float _t40, float _t47, float _t0, float _t17, float _t18, float _t19, float _t20, float _t22, float _t23, float _t41, float _t48, float _t49, float _t50) {
        float _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        float _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        float _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        float _t79 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0f))));
        float _t81 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t41, _t47, _t23)));
        float _t85 = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0f))));
        return lookAlong_rh_sa6198d78_2_fma(_t16, _t38, _t47, _t0, _t20, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, _t79, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), _t85, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t85)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_sa6198d78_1_mulAdd(float dirX, float dirY, float _t16, float _t38, float _t39, float _t40, float _t47, float _t0, float _t17, float _t18, float _t19, float _t20, float _t22, float _t23, float _t41, float _t48, float _t49, float _t50) {
        float _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        float _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        float _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        float _t79 = ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0f))))))));
        float _t81 = ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t41) * (_t47) + (_t23))))));
        float _t85 = ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0f))))))));
        return lookAlong_rh_sa6198d78_2_mulAdd(_t16, _t38, _t47, _t0, _t20, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, _t79, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), _t85, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t85)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_rh_sa6198d78_2_fma(float _t16, float _t38, float _t47, float _t0, float _t20, float _t49, float _t52, float _t56, float _t64, float _t70, float _t72, float _t74, float _t75, float _t78, float _t79, float _sp2, float _t81, float _sp4, float _t85, float _sp1, float _sp3) {
        float _t126, _t127, _t128, _t129;
        if (java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t38, _t47, _t64)) > 0.0f) {
            _t126 = _sp1 * _t52;
            _t127 = 0.5f * (float) java.lang.Math.sqrt(_t79);
            _t128 = _sp1 * _t72;
            _t129 = _sp1 * _t75;
        } else {
            if (_t49 > Math.max(_t64, _t20)) {
                _t126 = _sp2 * _t74;
                _t127 = _sp2 * _t72;
                _t128 = 0.5f * (float) java.lang.Math.sqrt(_t78);
                _t129 = _sp2 * _t56;
            } else {
                if (_t64 > _t20) {
                    _t126 = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    _t127 = _sp4 * _t52;
                    _t128 = _sp4 * _t74;
                    _t129 = _sp4 * _t70;
                } else {
                    _t126 = _sp3 * _t70;
                    _t127 = _sp3 * _t75;
                    _t128 = _sp3 * _t56;
                    _t129 = 0.5f * (float) java.lang.Math.sqrt(_t85);
                }
            }
        }
        return lookAlong_rh_sa6198d78_3_fma(_t126, _t127, _t128, _t129, java.lang.Math.fma(this.rX, _t127, this.rW * _t128) + java.lang.Math.fma(this.rY, _t129, -(this.rZ * _t126)), java.lang.Math.fma(this.rY, _t127, this.rZ * _t128) + java.lang.Math.fma(this.rW, _t126, -this.rX * _t129), java.lang.Math.fma(this.rX, _t126, this.rW * _t129) + java.lang.Math.fma(this.rZ, _t127, -(this.rY * _t128)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_rh_sa6198d78_2_mulAdd(float _t16, float _t38, float _t47, float _t0, float _t20, float _t49, float _t52, float _t56, float _t64, float _t70, float _t72, float _t74, float _t75, float _t78, float _t79, float _sp2, float _t81, float _sp4, float _t85, float _sp1, float _sp3) {
        float _t126, _t127, _t128, _t129;
        if (((_t0) * (_t16) + (((_t38) * (_t47) + (_t64)))) > 0.0f) {
            _t126 = _sp1 * _t52;
            _t127 = 0.5f * (float) java.lang.Math.sqrt(_t79);
            _t128 = _sp1 * _t72;
            _t129 = _sp1 * _t75;
        } else {
            if (_t49 > Math.max(_t64, _t20)) {
                _t126 = _sp2 * _t74;
                _t127 = _sp2 * _t72;
                _t128 = 0.5f * (float) java.lang.Math.sqrt(_t78);
                _t129 = _sp2 * _t56;
            } else {
                if (_t64 > _t20) {
                    _t126 = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    _t127 = _sp4 * _t52;
                    _t128 = _sp4 * _t74;
                    _t129 = _sp4 * _t70;
                } else {
                    _t126 = _sp3 * _t70;
                    _t127 = _sp3 * _t75;
                    _t128 = _sp3 * _t56;
                    _t129 = 0.5f * (float) java.lang.Math.sqrt(_t85);
                }
            }
        }
        return lookAlong_rh_sa6198d78_3_mulAdd(_t126, _t127, _t128, _t129, ((this.rX) * (_t127) + (this.rW * _t128)) + ((this.rY) * (_t129) - (this.rZ * _t126)), ((this.rY) * (_t127) + (this.rZ * _t128)) + ((this.rW) * (_t126) - (this.rX * _t129)), ((this.rX) * (_t126) + (this.rW * _t129)) + ((this.rZ) * (_t127) - (this.rY * _t128)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_rh_sa6198d78_3_fma(float _t126, float _t127, float _t128, float _t129, float _sfx0, float _sfx1, float _sfx2) {
        float _sfx3 = java.lang.Math.fma(this.rW, _t127, -this.rX * _t128) - java.lang.Math.fma(this.rY, _t126, this.rZ * _t129);
        float _sfx4 = java.lang.Math.fma(this.dX, _t127, this.dW * _t128) + java.lang.Math.fma(this.dY, _t129, -(this.dZ * _t126));
        float _sfx5 = java.lang.Math.fma(this.dY, _t127, this.dZ * _t128) + java.lang.Math.fma(this.dW, _t126, -this.dX * _t129);
        float _sfx6 = java.lang.Math.fma(this.dX, _t126, this.dW * _t129) + java.lang.Math.fma(this.dZ, _t127, -(this.dY * _t128));
        float _sfx7 = java.lang.Math.fma(this.dW, _t127, -this.dX * _t128) - java.lang.Math.fma(this.dY, _t126, this.dZ * _t129);
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_rh_sa6198d78_3_mulAdd(float _t126, float _t127, float _t128, float _t129, float _sfx0, float _sfx1, float _sfx2) {
        float _el3 = ((this.rW) * (_t127) - (this.rX * _t128)) - ((this.rY) * (_t126) + (this.rZ * _t129));
        float _el4 = ((this.dX) * (_t127) + (this.dW * _t128)) + ((this.dY) * (_t129) - (this.dZ * _t126));
        float _el5 = ((this.dY) * (_t127) + (this.dZ * _t128)) + ((this.dW) * (_t126) - (this.dX * _t129));
        float _el6 = ((this.dX) * (_t126) + (this.dW * _t129)) + ((this.dZ) * (_t127) - (this.dY * _t128));
        float _el7 = ((this.dW) * (_t127) - (this.dX * _t128)) - ((this.dY) * (_t126) + (this.dZ * _t129));
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _el3, _el4, _el5, _el6, _el7);
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_rh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0f) {
            _t21 = 0.0f;
            _t22 = 0.0f;
            _t23 = 1.0f;
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        float _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return lookAlong_rh_degenerate_sd3e64a41_1_fma(_t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t47, java.lang.Math.fma(_t45, _t25, _t46 * -_t26), java.lang.Math.fma(_t46, _t24, -(_t47 * _t25)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_rh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0f) {
            _t21 = 0.0f;
            _t22 = 0.0f;
            _t23 = 1.0f;
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t26) + (_t23));
        float _t46 = ((_t44) * (_t25) + (_t22));
        float _t47 = ((_t44) * (_t24) + (_t21));
        return lookAlong_rh_degenerate_sd3e64a41_1_mulAdd(_t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t47, ((_t45) * (_t25) - (_t46 * _t26)), ((_t46) * (_t24) - (_t47 * _t25)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_degenerate_sd3e64a41_1_fma(float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t47, float _t56, float _t57) {
        float _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_sd3e64a41_2_fma(_t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))), java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33))));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_degenerate_sd3e64a41_1_mulAdd(float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t47, float _t56, float _t57) {
        float _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_sd3e64a41_2_mulAdd(_t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))), ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33)))))));
    }

    /** Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_degenerate_sd3e64a41_2_fma(float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t96, float _t97, float _t99, float _t100) {
        float _t103 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t104 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_rh_degenerate_sd3e64a41_3_fma(_t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /** Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_degenerate_sd3e64a41_2_mulAdd(float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t96, float _t97, float _t99, float _t100) {
        float _t103 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        float _t104 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_rh_degenerate_sd3e64a41_3_mulAdd(_t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_rh_degenerate_sd3e64a41_3_fma(float _t24, float _t25, float _t30, float _t64, float _t67, float _t69, float _t70, float _t72, float _t74, float _t76, float _t88, float _t96, float _t97, float _t99, float _t100, float _sp1, float _t103, float _t104, float _sp0, float _sp3, float _sp2, float _t115, float _t116) {
        float _t149, _t150, _t151, _t152;
        if (java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t30))) > 0.0f) {
            _t149 = _sp0 * _t74;
            _t150 = 0.5f * (float) java.lang.Math.sqrt(_t99);
            _t151 = _sp0 * _t96;
            _t152 = _sp0 * _t116;
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                _t149 = _sp1 * _t115;
                _t150 = _sp1 * _t96;
                _t151 = 0.5f * (float) java.lang.Math.sqrt(_t100);
                _t152 = _sp1 * _t76;
            } else {
                if (_t88 > _t30) {
                    _t149 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t150 = _sp3 * _t74;
                    _t151 = _sp3 * _t115;
                    _t152 = _sp3 * _t97;
                } else {
                    _t149 = _sp2 * _t97;
                    _t150 = _sp2 * _t116;
                    _t151 = _sp2 * _t76;
                    _t152 = 0.5f * (float) java.lang.Math.sqrt(_t104);
                }
            }
        }
        return lookAlong_rh_sa6198d78_3_fma(_t149, _t150, _t151, _t152, java.lang.Math.fma(this.rX, _t150, this.rW * _t151) + java.lang.Math.fma(this.rY, _t152, -(this.rZ * _t149)), java.lang.Math.fma(this.rY, _t150, this.rZ * _t151) + java.lang.Math.fma(this.rW, _t149, -this.rX * _t152), java.lang.Math.fma(this.rX, _t149, this.rW * _t152) + java.lang.Math.fma(this.rZ, _t150, -(this.rY * _t151)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatDualQuat lookAlong_rh_degenerate_sd3e64a41_3_mulAdd(float _t24, float _t25, float _t30, float _t64, float _t67, float _t69, float _t70, float _t72, float _t74, float _t76, float _t88, float _t96, float _t97, float _t99, float _t100, float _sp1, float _t103, float _t104, float _sp0, float _sp3, float _sp2, float _t115, float _t116) {
        float _t149, _t150, _t151, _t152;
        if (((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t30)))))) > 0.0f) {
            _t149 = _sp0 * _t74;
            _t150 = 0.5f * (float) java.lang.Math.sqrt(_t99);
            _t151 = _sp0 * _t96;
            _t152 = _sp0 * _t116;
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                _t149 = _sp1 * _t115;
                _t150 = _sp1 * _t96;
                _t151 = 0.5f * (float) java.lang.Math.sqrt(_t100);
                _t152 = _sp1 * _t76;
            } else {
                if (_t88 > _t30) {
                    _t149 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _t150 = _sp3 * _t74;
                    _t151 = _sp3 * _t115;
                    _t152 = _sp3 * _t97;
                } else {
                    _t149 = _sp2 * _t97;
                    _t150 = _sp2 * _t116;
                    _t151 = _sp2 * _t76;
                    _t152 = 0.5f * (float) java.lang.Math.sqrt(_t104);
                }
            }
        }
        return lookAlong_rh_sa6198d78_3_mulAdd(_t149, _t150, _t151, _t152, ((this.rX) * (_t150) + (this.rW * _t151)) + ((this.rY) * (_t152) - (this.rZ * _t149)), ((this.rY) * (_t150) + (this.rZ * _t151)) + ((this.rW) * (_t149) - (this.rX * _t152)), ((this.rX) * (_t149) + (this.rW * _t152)) + ((this.rZ) * (_t150) - (this.rY * _t151)));
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this dual quaternion, returning the result
     * as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param handedness the handedness of the coordinate system to map into
     * @return the resulting dual quaternion
     */
    public FloatDualQuat lookAlong(Float3 dir, Float3 up, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dir, up) : lookAlong_lh_mulAdd(dir, up)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up) : lookAlong_rh_mulAdd(dir, up)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this dual
     * quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param handedness the handedness of the coordinate system to map into
     * @return the resulting dual quaternion
     */
    public FloatDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ) : lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this dual quaternion, returning the result
     * as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @return the resulting dual quaternion
     */
    public FloatDualQuat lookAlong(Float3 dir, Float3 up) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up) : lookAlong_rh_mulAdd(dir, up)); }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this dual
     * quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
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
    public FloatDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_lh_fma(Float3 dir, Float3 up, float epsilon) {
        return lookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_lh_mulAdd(Float3 dir, Float3 up, float epsilon) {
        return lookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        float _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        float _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        float _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return lookAlong_lh_sb225bd8a_1_fma(dirX, dirY, dirZ, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45, _t38 * _t45);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = ((upY) - (dirY) * (_sp0));
        float _t28 = ((upZ) - (dirZ) * (_sp0));
        float _t29 = ((upX) - (dirX) * (_sp0));
        float _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        float _t37 = ((dirY) * (_t29) - (dirX * _t27));
        float _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        float _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return lookAlong_lh_sb225bd8a_1_mulAdd(dirX, dirY, dirZ, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45, _t38 * _t45);
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_lh_sb225bd8a_1_fma(float dirX, float dirY, float dirZ, float _t15, float _t36, float _t37, float _t38, float _t45, float _t0, float _t16, float _t17, float _t18, float _t19, float _t21, float _t46, float _t47, float _t48) {
        float _t49 = java.lang.Math.fma(-_t36, _t45, 1.0f);
        float _t64 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        float _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        float _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0f))));
        float _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0f))));
        float _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        float _t83 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49)));
        return lookAlong_lh_s5402847e_2_fma(dirZ, _t15, _t36, _t45, _t16, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t64), java.lang.Math.fma(dirY, _t15, -_t64), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), _t83, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t83)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_lh_sb225bd8a_1_mulAdd(float dirX, float dirY, float dirZ, float _t15, float _t36, float _t37, float _t38, float _t45, float _t0, float _t16, float _t17, float _t18, float _t19, float _t21, float _t46, float _t47, float _t48) {
        float _t49 = ((1.0f) - (_t36) * (_t45));
        float _t64 = ((_t17) * (_t48) - (_t18 * _t46));
        float _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        float _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0f))))))));
        float _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0f))))))));
        float _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        float _t83 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49))))));
        return lookAlong_lh_s5402847e_2_mulAdd(dirZ, _t15, _t36, _t45, _t16, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t64)), ((dirY) * (_t15) - (_t64)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), _t83, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t83)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_lh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        float _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return lookAlong_lh_degenerate_scb91a409_2_fma(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_lh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t25) + (_t22));
        float _t46 = ((_t44) * (_t26) + (_t23));
        float _t47 = ((_t44) * (_t24) + (_t21));
        return lookAlong_lh_degenerate_scb91a409_2_mulAdd(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_lh_degenerate_scb91a409_2_fma(float epsilon, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t56, float _t57, float _t58) {
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        float _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33)));
        return lookAlong_lh_degenerate_sae57cfe3_3_fma(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))), _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_lh_degenerate_scb91a409_2_mulAdd(float epsilon, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t56, float _t57, float _t58) {
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        float _t100 = ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33))))));
        return lookAlong_lh_degenerate_sae57cfe3_3_mulAdd(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))), _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_rh_fma(Float3 dir, Float3 up, float epsilon) {
        return lookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_rh_mulAdd(Float3 dir, Float3 up, float epsilon) {
        return lookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        float _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        float _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        float _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return lookAlong_rh_sa76f63ac_1_fma(dirX, dirY, _t16, _t38, _t39, _t40, _t47, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0f), -_t38, _t39 * _t47, _t38 * _t47);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatDualQuat lookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        float _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return lookAlong_rh_sa76f63ac_1_mulAdd(dirX, dirY, _t16, _t38, _t39, _t40, _t47, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0f)), -_t38, _t39 * _t47, _t38 * _t47);
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_sa76f63ac_1_fma(float dirX, float dirY, float _t16, float _t38, float _t39, float _t40, float _t47, float _t0, float _t17, float _t18, float _t19, float _t20, float _t22, float _t23, float _t41, float _t48, float _t49) {
        float _t50 = _t40 * _t47;
        float _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        float _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        float _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        float _t79 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0f))));
        float _t81 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t41, _t47, _t23)));
        float _t85 = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0f))));
        return lookAlong_rh_sa6198d78_2_fma(_t16, _t38, _t47, _t0, _t20, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, _t79, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), _t85, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t85)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_sa76f63ac_1_mulAdd(float dirX, float dirY, float _t16, float _t38, float _t39, float _t40, float _t47, float _t0, float _t17, float _t18, float _t19, float _t20, float _t22, float _t23, float _t41, float _t48, float _t49) {
        float _t50 = _t40 * _t47;
        float _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        float _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        float _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        float _t79 = ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0f))))))));
        float _t81 = ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t41) * (_t47) + (_t23))))));
        float _t85 = ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0f))))))));
        return lookAlong_rh_sa6198d78_2_mulAdd(_t16, _t38, _t47, _t0, _t20, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, _t79, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), _t85, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t85)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_rh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0f) {
            _t21 = 0.0f;
            _t22 = 0.0f;
            _t23 = 1.0f;
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        return lookAlong_rh_degenerate_s31c84b27_1_fma(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatDualQuat lookAlong_rh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0f) {
            _t21 = 0.0f;
            _t22 = 0.0f;
            _t23 = 1.0f;
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t26) + (_t23));
        float _t46 = ((_t44) * (_t25) + (_t22));
        return lookAlong_rh_degenerate_s31c84b27_1_mulAdd(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_degenerate_s31c84b27_1_fma(float epsilon, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        float _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_s31c84b27_2_fma(_t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_degenerate_s31c84b27_1_mulAdd(float epsilon, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        float _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_s31c84b27_2_mulAdd(_t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))));
    }

    /** Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_degenerate_s31c84b27_2_fma(float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t96, float _t97, float _t99) {
        float _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        float _t103 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t104 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_rh_degenerate_sd3e64a41_3_fma(_t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /** Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatDualQuat lookAlong_rh_degenerate_s31c84b27_2_mulAdd(float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t96, float _t97, float _t99) {
        float _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        float _t103 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        float _t104 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_rh_degenerate_sd3e64a41_3_mulAdd(_t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this dual quaternion, returning the result
     * as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @return the resulting dual quaternion
     */
    public FloatDualQuat lookAlong(Float3 dir, Float3 up, float epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dir, up, epsilon) : lookAlong_lh_mulAdd(dir, up, epsilon)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, epsilon) : lookAlong_rh_mulAdd(dir, up, epsilon)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this dual
     * quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @return the resulting dual quaternion
     */
    public FloatDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this dual quaternion, returning the result
     * as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat lookAlong(Float3 dir, Float3 up, float epsilon) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dir, up, epsilon) : lookAlong_rh_mulAdd(dir, up, epsilon)); }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this dual
     * quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @return the resulting dual quaternion
     */
    public FloatDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) { return (org.joml2.internal.FmaSwitch.ON ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }

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
        float _t1 = Math.sinWithCos(_t0);
        return new FloatDualQuat(axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosWithSin(_t0), 0.0f, 0.0f, 0.0f, 0.0f);
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
        float _t1 = Math.sinWithCos(_t0);
        return new FloatDualQuat(axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosWithSin(_t0), 0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_fma(Float3 dir, Float3 up) {
        return makeRotationLookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_mulAdd(Float3 dir, Float3 up) {
        return makeRotationLookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        float _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        float _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        float _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return makeRotationLookAlong_lh_s96c6b39a_1_fma(dirX, dirY, dirZ, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45, _t38 * _t45, java.lang.Math.fma(-_t36, _t45, 1.0f));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = ((upY) - (dirY) * (_sp0));
        float _t28 = ((upZ) - (dirZ) * (_sp0));
        float _t29 = ((upX) - (dirX) * (_sp0));
        float _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        float _t37 = ((dirY) * (_t29) - (dirX * _t27));
        float _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        float _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return makeRotationLookAlong_lh_s96c6b39a_1_mulAdd(dirX, dirY, dirZ, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45, _t38 * _t45, ((1.0f) - (_t36) * (_t45)));
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_s96c6b39a_1_fma(float dirX, float dirY, float dirZ, float _t15, float _t36, float _t37, float _t38, float _t45, float _t0, float _t16, float _t17, float _t18, float _t19, float _t21, float _t46, float _t47, float _t48, float _t49) {
        float _t63 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        float _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        float _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0f))));
        float _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0f))));
        float _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        float _t81 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49)));
        return makeRotationLookAlong_lh_s96c6b39a_2_fma(dirZ, _t15, _t36, _t45, _t16, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t63), java.lang.Math.fma(dirY, _t15, -_t63), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), _t79, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)));
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_s96c6b39a_1_mulAdd(float dirX, float dirY, float dirZ, float _t15, float _t36, float _t37, float _t38, float _t45, float _t0, float _t16, float _t17, float _t18, float _t19, float _t21, float _t46, float _t47, float _t48, float _t49) {
        float _t63 = ((_t17) * (_t48) - (_t18 * _t46));
        float _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        float _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0f))))))));
        float _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0f))))))));
        float _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        float _t81 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49))))));
        return makeRotationLookAlong_lh_s96c6b39a_2_mulAdd(dirZ, _t15, _t36, _t45, _t16, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t63)), ((dirY) * (_t15) - (_t63)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), _t79, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_s96c6b39a_2_fma(float dirZ, float _t15, float _t36, float _t45, float _t16, float _t46, float _t50, float _t55, float _t62, float _t68, float _t69, float _t73, float _t74, float _t77, float _sp1, float _t79, float _t80, float _t81, float _sp3, float _sp4, float _sp2) {
        float _sfx0, _sfx1, _sfx2, _sfx3;
        if (java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t36, _t45, _t62)) > 0.0f) {
            _sfx0 = _sp1 * _t69;
            _sfx1 = _sp1 * _t55;
            _sfx2 = _sp1 * _t74;
            _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _sfx0 = 0.5f * (float) java.lang.Math.sqrt(_t79);
                _sfx1 = _sp4 * _t73;
                _sfx2 = _sp4 * _t50;
                _sfx3 = _sp4 * _t69;
            } else {
                if (_t62 > _t16) {
                    _sfx0 = _sp2 * _t73;
                    _sfx1 = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    _sfx2 = _sp2 * _t68;
                    _sfx3 = _sp2 * _t55;
                } else {
                    _sfx0 = _sp3 * _t50;
                    _sfx1 = _sp3 * _t68;
                    _sfx2 = 0.5f * (float) java.lang.Math.sqrt(_t80);
                    _sfx3 = _sp3 * _t74;
                }
            }
        }
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_s96c6b39a_2_mulAdd(float dirZ, float _t15, float _t36, float _t45, float _t16, float _t46, float _t50, float _t55, float _t62, float _t68, float _t69, float _t73, float _t74, float _t77, float _sp1, float _t79, float _t80, float _t81, float _sp3, float _sp4, float _sp2) {
        float _sfx0, _sfx1, _sfx2, _sfx3;
        if (((dirZ) * (_t15) + (((_t36) * (_t45) + (_t62)))) > 0.0f) {
            _sfx0 = _sp1 * _t69;
            _sfx1 = _sp1 * _t55;
            _sfx2 = _sp1 * _t74;
            _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _sfx0 = 0.5f * (float) java.lang.Math.sqrt(_t79);
                _sfx1 = _sp4 * _t73;
                _sfx2 = _sp4 * _t50;
                _sfx3 = _sp4 * _t69;
            } else {
                if (_t62 > _t16) {
                    _sfx0 = _sp2 * _t73;
                    _sfx1 = 0.5f * (float) java.lang.Math.sqrt(_t81);
                    _sfx2 = _sp2 * _t68;
                    _sfx3 = _sp2 * _t55;
                } else {
                    _sfx0 = _sp3 * _t50;
                    _sfx1 = _sp3 * _t68;
                    _sfx2 = 0.5f * (float) java.lang.Math.sqrt(_t80);
                    _sfx3 = _sp3 * _t74;
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
    private static FloatDualQuat makeRotationLookAlong_lh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        float _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_1_fma(_t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t25) + (_t22));
        float _t46 = ((_t44) * (_t26) + (_t23));
        float _t47 = ((_t44) * (_t24) + (_t21));
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_1_mulAdd(_t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private static FloatDualQuat makeRotationLookAlong_lh_degenerate_s2c1aa5ef_1_fma(float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t56, float _t57, float _t58) {
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        float _t99 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32)));
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_fma(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), _t99, java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private static FloatDualQuat makeRotationLookAlong_lh_degenerate_s2c1aa5ef_1_mulAdd(float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t56, float _t57, float _t58) {
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        float _t99 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32))))));
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_mulAdd(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), _t99, ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33)))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_fma(float _t24, float _t25, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t78, float _t89, float _t93, float _t96, float _t97, float _t99, float _t100, float _sp0) {
        float _t102 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t103 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103));
        float _t107 = java.lang.Math.fma(_t67, _t65, _t93);
        float _t108 = java.lang.Math.fma(_t68, _t65, _t93);
        float _sfx0, _sfx1, _sfx2, _sfx3;
        if (java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t24))) > 0.0f) {
            _sfx0 = _sp0 * _t96;
            _sfx1 = _sp0 * _t78;
            _sfx2 = _sp0 * _t108;
            _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                _sfx0 = 0.5f * (float) java.lang.Math.sqrt(_t100);
                _sfx1 = _sp3 * _t107;
                _sfx2 = _sp3 * _t74;
                _sfx3 = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    _sfx0 = _sp1 * _t107;
                    _sfx1 = 0.5f * (float) java.lang.Math.sqrt(_t102);
                    _sfx2 = _sp1 * _t97;
                    _sfx3 = _sp1 * _t78;
                } else {
                    _sfx0 = _sp2 * _t74;
                    _sfx1 = _sp2 * _t97;
                    _sfx2 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _sfx3 = _sp2 * _t108;
                }
            }
        }
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_mulAdd(float _t24, float _t25, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t78, float _t89, float _t93, float _t96, float _t97, float _t99, float _t100, float _sp0) {
        float _t102 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        float _t103 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103));
        float _t107 = ((_t67) * (_t65) + (_t93));
        float _t108 = ((_t68) * (_t65) + (_t93));
        float _sfx0, _sfx1, _sfx2, _sfx3;
        if (((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t24)))))) > 0.0f) {
            _sfx0 = _sp0 * _t96;
            _sfx1 = _sp0 * _t78;
            _sfx2 = _sp0 * _t108;
            _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                _sfx0 = 0.5f * (float) java.lang.Math.sqrt(_t100);
                _sfx1 = _sp3 * _t107;
                _sfx2 = _sp3 * _t74;
                _sfx3 = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    _sfx0 = _sp1 * _t107;
                    _sfx1 = 0.5f * (float) java.lang.Math.sqrt(_t102);
                    _sfx2 = _sp1 * _t97;
                    _sfx3 = _sp1 * _t78;
                } else {
                    _sfx0 = _sp2 * _t74;
                    _sfx1 = _sp2 * _t97;
                    _sfx2 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _sfx3 = _sp2 * _t108;
                }
            }
        }
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_fma(Float3 dir, Float3 up) {
        return makeRotationLookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_mulAdd(Float3 dir, Float3 up) {
        return makeRotationLookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        float _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        float _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        float _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return makeRotationLookAlong_rh_s1465dddc_1_fma(dirX, dirY, _t16, _t38, _t39, _t40, _t47, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0f), -_t38, _t39 * _t47, _t38 * _t47, _t40 * _t47);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        float _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return makeRotationLookAlong_rh_s1465dddc_1_mulAdd(dirX, dirY, _t16, _t38, _t39, _t40, _t47, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0f)), -_t38, _t39 * _t47, _t38 * _t47, _t40 * _t47);
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_s1465dddc_1_fma(float dirX, float dirY, float _t16, float _t38, float _t39, float _t40, float _t47, float _t0, float _t17, float _t18, float _t19, float _t21, float _t22, float _t23, float _t41, float _t48, float _t49, float _t50) {
        float _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        float _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        float _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        float _t79 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0f))));
        float _t80 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t41, _t47, _t23)));
        float _t84 = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0f))));
        return makeRotationLookAlong_rh_s1465dddc_2_fma(_t16, _t38, _t47, _t0, _t21, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), _t84, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84)));
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_s1465dddc_1_mulAdd(float dirX, float dirY, float _t16, float _t38, float _t39, float _t40, float _t47, float _t0, float _t17, float _t18, float _t19, float _t21, float _t22, float _t23, float _t41, float _t48, float _t49, float _t50) {
        float _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        float _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        float _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        float _t79 = ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0f))))))));
        float _t80 = ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t41) * (_t47) + (_t23))))));
        float _t84 = ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0f))))))));
        return makeRotationLookAlong_rh_s1465dddc_2_mulAdd(_t16, _t38, _t47, _t0, _t21, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), _t84, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_s1465dddc_2_fma(float _t16, float _t38, float _t47, float _t0, float _t21, float _t49, float _t52, float _t56, float _t64, float _t70, float _t71, float _t74, float _t75, float _t78, float _t79, float _t80, float _sp4, float _sp1, float _sp2, float _t84, float _sp3) {
        float _sfx0, _sfx1, _sfx2, _sfx3;
        if (java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t38, _t47, _t64)) > 0.0f) {
            _sfx0 = _sp1 * _t71;
            _sfx1 = _sp1 * _t52;
            _sfx2 = _sp1 * _t75;
            _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t79);
        } else {
            if (_t49 > Math.max(_t64, _t21)) {
                _sfx0 = 0.5f * (float) java.lang.Math.sqrt(_t78);
                _sfx1 = _sp4 * _t74;
                _sfx2 = _sp4 * _t56;
                _sfx3 = _sp4 * _t71;
            } else {
                if (_t64 > _t21) {
                    _sfx0 = _sp2 * _t74;
                    _sfx1 = 0.5f * (float) java.lang.Math.sqrt(_t80);
                    _sfx2 = _sp2 * _t70;
                    _sfx3 = _sp2 * _t52;
                } else {
                    _sfx0 = _sp3 * _t56;
                    _sfx1 = _sp3 * _t70;
                    _sfx2 = 0.5f * (float) java.lang.Math.sqrt(_t84);
                    _sfx3 = _sp3 * _t75;
                }
            }
        }
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_s1465dddc_2_mulAdd(float _t16, float _t38, float _t47, float _t0, float _t21, float _t49, float _t52, float _t56, float _t64, float _t70, float _t71, float _t74, float _t75, float _t78, float _t79, float _t80, float _sp4, float _sp1, float _sp2, float _t84, float _sp3) {
        float _sfx0, _sfx1, _sfx2, _sfx3;
        if (((_t0) * (_t16) + (((_t38) * (_t47) + (_t64)))) > 0.0f) {
            _sfx0 = _sp1 * _t71;
            _sfx1 = _sp1 * _t52;
            _sfx2 = _sp1 * _t75;
            _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t79);
        } else {
            if (_t49 > Math.max(_t64, _t21)) {
                _sfx0 = 0.5f * (float) java.lang.Math.sqrt(_t78);
                _sfx1 = _sp4 * _t74;
                _sfx2 = _sp4 * _t56;
                _sfx3 = _sp4 * _t71;
            } else {
                if (_t64 > _t21) {
                    _sfx0 = _sp2 * _t74;
                    _sfx1 = 0.5f * (float) java.lang.Math.sqrt(_t80);
                    _sfx2 = _sp2 * _t70;
                    _sfx3 = _sp2 * _t52;
                } else {
                    _sfx0 = _sp3 * _t56;
                    _sfx1 = _sp3 * _t70;
                    _sfx2 = 0.5f * (float) java.lang.Math.sqrt(_t84);
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
    private static FloatDualQuat makeRotationLookAlong_rh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0f) {
            _t21 = 0.0f;
            _t22 = 0.0f;
            _t23 = 1.0f;
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        float _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return makeRotationLookAlong_rh_degenerate_s57efa25_1_fma(_t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t47, java.lang.Math.fma(_t45, _t25, _t46 * -_t26), java.lang.Math.fma(_t46, _t24, -(_t47 * _t25)));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0f) {
            _t21 = 0.0f;
            _t22 = 0.0f;
            _t23 = 1.0f;
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t26) + (_t23));
        float _t46 = ((_t44) * (_t25) + (_t22));
        float _t47 = ((_t44) * (_t24) + (_t21));
        return makeRotationLookAlong_rh_degenerate_s57efa25_1_mulAdd(_t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t47, ((_t45) * (_t25) - (_t46 * _t26)), ((_t46) * (_t24) - (_t47 * _t25)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private static FloatDualQuat makeRotationLookAlong_rh_degenerate_s57efa25_1_fma(float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t47, float _t56, float _t57) {
        float _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_s57efa25_2_fma(_t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))), java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private static FloatDualQuat makeRotationLookAlong_rh_degenerate_s57efa25_1_mulAdd(float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t47, float _t56, float _t57) {
        float _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 1.4551915E-11f) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_s57efa25_2_mulAdd(_t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))), ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33)))))));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_degenerate_s57efa25_2_fma(float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t96, float _t97, float _t99, float _t100) {
        float _t102 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t103 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return makeRotationLookAlong_rh_degenerate_s57efa25_3_fma(_t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_degenerate_s57efa25_2_mulAdd(float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t96, float _t97, float _t99, float _t100) {
        float _t102 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        float _t103 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return makeRotationLookAlong_rh_degenerate_s57efa25_3_mulAdd(_t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_degenerate_s57efa25_3_fma(float _t24, float _t25, float _t30, float _t64, float _t67, float _t69, float _t70, float _t72, float _t74, float _t76, float _t88, float _t96, float _t97, float _t99, float _t100, float _sp0, float _t102, float _t103, float _sp3, float _sp1, float _sp2, float _t107, float _t108) {
        float _sfx0, _sfx1, _sfx2, _sfx3;
        if (java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t30))) > 0.0f) {
            _sfx0 = _sp0 * _t96;
            _sfx1 = _sp0 * _t74;
            _sfx2 = _sp0 * _t108;
            _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                _sfx0 = 0.5f * (float) java.lang.Math.sqrt(_t100);
                _sfx1 = _sp3 * _t107;
                _sfx2 = _sp3 * _t76;
                _sfx3 = _sp3 * _t96;
            } else {
                if (_t88 > _t30) {
                    _sfx0 = _sp1 * _t107;
                    _sfx1 = 0.5f * (float) java.lang.Math.sqrt(_t102);
                    _sfx2 = _sp1 * _t97;
                    _sfx3 = _sp1 * _t74;
                } else {
                    _sfx0 = _sp2 * _t76;
                    _sfx1 = _sp2 * _t97;
                    _sfx2 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _sfx3 = _sp2 * _t108;
                }
            }
        }
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_degenerate_s57efa25_3_mulAdd(float _t24, float _t25, float _t30, float _t64, float _t67, float _t69, float _t70, float _t72, float _t74, float _t76, float _t88, float _t96, float _t97, float _t99, float _t100, float _sp0, float _t102, float _t103, float _sp3, float _sp1, float _sp2, float _t107, float _t108) {
        float _sfx0, _sfx1, _sfx2, _sfx3;
        if (((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t30)))))) > 0.0f) {
            _sfx0 = _sp0 * _t96;
            _sfx1 = _sp0 * _t74;
            _sfx2 = _sp0 * _t108;
            _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                _sfx0 = 0.5f * (float) java.lang.Math.sqrt(_t100);
                _sfx1 = _sp3 * _t107;
                _sfx2 = _sp3 * _t76;
                _sfx3 = _sp3 * _t96;
            } else {
                if (_t88 > _t30) {
                    _sfx0 = _sp1 * _t107;
                    _sfx1 = 0.5f * (float) java.lang.Math.sqrt(_t102);
                    _sfx2 = _sp1 * _t97;
                    _sfx3 = _sp1 * _t74;
                } else {
                    _sfx0 = _sp2 * _t76;
                    _sfx1 = _sp2 * _t97;
                    _sfx2 = 0.5f * (float) java.lang.Math.sqrt(_t103);
                    _sfx3 = _sp2 * _t108;
                }
            }
        }
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Create a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param handedness the handedness of the coordinate system to map into
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationLookAlong(Float3 dir, Float3 up, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_lh_fma(dir, up) : makeRotationLookAlong_lh_mulAdd(dir, up)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dir, up) : makeRotationLookAlong_rh_mulAdd(dir, up)); }
        }
    }

    /**
     * Create a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param handedness the handedness of the coordinate system to map into
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ) : makeRotationLookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
        }
    }

    /**
     * Create a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationLookAlong(Float3 dir, Float3 up) { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dir, up) : makeRotationLookAlong_rh_mulAdd(dir, up)); }

    /**
     * Create a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
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
    public static FloatDualQuat makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_fma(Float3 dir, Float3 up, float epsilon) {
        return makeRotationLookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_mulAdd(Float3 dir, Float3 up, float epsilon) {
        return makeRotationLookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        float _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        float _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        float _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return makeRotationLookAlong_lh_s96c6b39a_1_fma(dirX, dirY, dirZ, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45, _t38 * _t45, java.lang.Math.fma(-_t36, _t45, 1.0f));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = ((upY) - (dirY) * (_sp0));
        float _t28 = ((upZ) - (dirZ) * (_sp0));
        float _t29 = ((upX) - (dirX) * (_sp0));
        float _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        float _t37 = ((dirY) * (_t29) - (dirX * _t27));
        float _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        float _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return makeRotationLookAlong_lh_s96c6b39a_1_mulAdd(dirX, dirY, dirZ, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45, _t38 * _t45, ((1.0f) - (_t36) * (_t45)));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        float _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return makeRotationLookAlong_lh_degenerate_sf46b18a5_1_fma(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static FloatDualQuat makeRotationLookAlong_lh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
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
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = -_t24;
        }
        float _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t25) + (_t22));
        float _t46 = ((_t44) * (_t26) + (_t23));
        float _t47 = ((_t44) * (_t24) + (_t21));
        return makeRotationLookAlong_lh_degenerate_sf46b18a5_1_mulAdd(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private static FloatDualQuat makeRotationLookAlong_lh_degenerate_sf46b18a5_1_fma(float epsilon, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t56, float _t57, float _t58) {
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        float _t99 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32)));
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_fma(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), _t99, java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private static FloatDualQuat makeRotationLookAlong_lh_degenerate_sf46b18a5_1_mulAdd(float epsilon, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t56, float _t57, float _t58) {
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        float _t99 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32))))));
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_mulAdd(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), _t99, ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33)))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_fma(Float3 dir, Float3 up, float epsilon) {
        return makeRotationLookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_mulAdd(Float3 dir, Float3 up, float epsilon) {
        return makeRotationLookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        float _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        float _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        float _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return makeRotationLookAlong_rh_s1465dddc_1_fma(dirX, dirY, _t16, _t38, _t39, _t40, _t47, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0f), -_t38, _t39 * _t47, _t38 * _t47, _t40 * _t47);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        float _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return makeRotationLookAlong_rh_s1465dddc_1_mulAdd(dirX, dirY, _t16, _t38, _t39, _t40, _t47, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0f)), -_t38, _t39 * _t47, _t38 * _t47, _t40 * _t47);
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0f) {
            _t21 = 0.0f;
            _t22 = 0.0f;
            _t23 = 1.0f;
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        float _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        float _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        float _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return makeRotationLookAlong_rh_degenerate_s65a2e42b_1_fma(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t47, java.lang.Math.fma(_t45, _t25, _t46 * -_t26), java.lang.Math.fma(_t46, _t24, -(_t47 * _t25)));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static FloatDualQuat makeRotationLookAlong_rh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0f) {
            _t21 = 0.0f;
            _t22 = 0.0f;
            _t23 = 1.0f;
            _t24 = -1.0f;
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
        float _t30 = -_t24;
        float _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0f;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0f;
            _t42 = _t30;
        }
        float _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        float _t45 = ((_t44) * (_t26) + (_t23));
        float _t46 = ((_t44) * (_t25) + (_t22));
        float _t47 = ((_t44) * (_t24) + (_t21));
        return makeRotationLookAlong_rh_degenerate_s65a2e42b_1_mulAdd(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t47, ((_t45) * (_t25) - (_t46 * _t26)), ((_t46) * (_t24) - (_t47 * _t25)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private static FloatDualQuat makeRotationLookAlong_rh_degenerate_s65a2e42b_1_fma(float epsilon, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t47, float _t56, float _t57) {
        float _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        float _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_s57efa25_2_fma(_t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))), java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private static FloatDualQuat makeRotationLookAlong_rh_degenerate_s65a2e42b_1_mulAdd(float epsilon, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t47, float _t56, float _t57) {
        float _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        float _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        float _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0f / (float) java.lang.Math.sqrt(_t62));
        }
        float _t69 = _t67 * _t63;
        float _t70 = _t67 * _t64;
        float _t71 = -_t69;
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_s57efa25_2_mulAdd(_t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))), ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33)))))));
    }

    /**
     * Create a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationLookAlong(Float3 dir, Float3 up, float epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_lh_fma(dir, up, epsilon) : makeRotationLookAlong_lh_mulAdd(dir, up, epsilon)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dir, up, epsilon) : makeRotationLookAlong_rh_mulAdd(dir, up, epsilon)); }
        }
    }

    /**
     * Create a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : makeRotationLookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
            default -> { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
        }
    }

    /**
     * Create a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationLookAlong(Float3 dir, Float3 up, float epsilon) { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dir, up, epsilon) : makeRotationLookAlong_rh_mulAdd(dir, up, epsilon)); }

    /**
     * Create a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param epsilon the squared sine of the angle between {@code up} and the direction at or below
     *        which they count as parallel (a direction perpendicular to the view then takes the
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) { return (org.joml2.internal.FmaSwitch.ON ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }

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
        return new FloatDualQuat(Math.sinWithCos(_t0), 0.0f, 0.0f, Math.cosWithSin(_t0), 0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Create a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationXYZ(float angleX, float angleY, float angleZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = 0.5f * angleX;
            float _t1 = 0.5f * angleY;
            float _t2 = 0.5f * angleZ;
            float _t3 = Math.sinWithCos(_t0);
            float _t4 = Math.cosWithSin(_t1);
            float _t5 = Math.cosWithSin(_t2);
            float _t6 = Math.sinWithCos(_t1);
            float _t7 = Math.cosWithSin(_t0);
            float _t8 = Math.sinWithCos(_t2);
            float _t9 = _t3 * _t4;
            float _t10 = _t6 * _t7;
            float _t11 = _t3 * _t6;
            float _t12 = _t7 * _t4;
            return new FloatDualQuat(java.lang.Math.fma(_t9, _t5, _t10 * _t8), java.lang.Math.fma(_t10, _t5, _t9 * -_t8), java.lang.Math.fma(_t11, _t5, _t12 * _t8), java.lang.Math.fma(_t12, _t5, _t11 * -_t8), 0.0f, 0.0f, 0.0f, 0.0f);
        } else {
            float _t0 = 0.5f * angleX;
            float _t1 = 0.5f * angleY;
            float _t2 = 0.5f * angleZ;
            float _t3 = Math.sinWithCos(_t0);
            float _t4 = Math.cosWithSin(_t1);
            float _t5 = Math.cosWithSin(_t2);
            float _t6 = Math.sinWithCos(_t1);
            float _t7 = Math.cosWithSin(_t0);
            float _t8 = Math.sinWithCos(_t2);
            float _t9 = _t3 * _t4;
            float _t10 = _t6 * _t7;
            float _t11 = _t3 * _t6;
            float _t12 = _t7 * _t4;
            return new FloatDualQuat(((_t9) * (_t5) + (_t10 * _t8)), ((_t10) * (_t5) - (_t9 * _t8)), ((_t11) * (_t5) + (_t12 * _t8)), ((_t12) * (_t5) - (_t11 * _t8)), 0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    /**
     * Create a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationXZY(float angleX, float angleZ, float angleY) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = 0.5f * angleX;
            float _t1 = 0.5f * angleZ;
            float _t2 = 0.5f * angleY;
            float _t3 = Math.sinWithCos(_t0);
            float _t4 = Math.cosWithSin(_t1);
            float _t5 = Math.cosWithSin(_t2);
            float _t6 = Math.sinWithCos(_t1);
            float _t7 = Math.cosWithSin(_t0);
            float _t8 = Math.sinWithCos(_t2);
            float _t9 = _t3 * _t4;
            float _t10 = _t6 * _t7;
            float _t11 = _t7 * _t4;
            float _t12 = _t3 * _t6;
            return new FloatDualQuat(java.lang.Math.fma(_t9, _t5, -(_t10 * _t8)), java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)), java.lang.Math.fma(_t9, _t8, _t10 * _t5), java.lang.Math.fma(_t12, _t8, _t11 * _t5), 0.0f, 0.0f, 0.0f, 0.0f);
        } else {
            float _t0 = 0.5f * angleX;
            float _t1 = 0.5f * angleZ;
            float _t2 = 0.5f * angleY;
            float _t3 = Math.sinWithCos(_t0);
            float _t4 = Math.cosWithSin(_t1);
            float _t5 = Math.cosWithSin(_t2);
            float _t6 = Math.sinWithCos(_t1);
            float _t7 = Math.cosWithSin(_t0);
            float _t8 = Math.sinWithCos(_t2);
            float _t9 = _t3 * _t4;
            float _t10 = _t6 * _t7;
            float _t11 = _t7 * _t4;
            float _t12 = _t3 * _t6;
            return new FloatDualQuat(((_t9) * (_t5) - (_t10 * _t8)), ((_t11) * (_t8) - (_t12 * _t5)), ((_t9) * (_t8) + (_t10 * _t5)), ((_t12) * (_t8) + (_t11 * _t5)), 0.0f, 0.0f, 0.0f, 0.0f);
        }
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
        return new FloatDualQuat(0.0f, Math.sinWithCos(_t0), 0.0f, Math.cosWithSin(_t0), 0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Create a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationYXZ(float angleY, float angleX, float angleZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = 0.5f * angleX;
            float _t1 = 0.5f * angleY;
            float _t2 = 0.5f * angleZ;
            float _t3 = Math.sinWithCos(_t0);
            float _t4 = Math.cosWithSin(_t1);
            float _t5 = Math.cosWithSin(_t2);
            float _t6 = Math.sinWithCos(_t1);
            float _t7 = Math.cosWithSin(_t0);
            float _t8 = Math.sinWithCos(_t2);
            float _t9 = _t3 * _t4;
            float _t10 = _t6 * _t7;
            float _t11 = _t7 * _t4;
            float _t12 = _t3 * _t6;
            return new FloatDualQuat(java.lang.Math.fma(_t9, _t5, _t10 * _t8), java.lang.Math.fma(_t10, _t5, -(_t9 * _t8)), java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)), java.lang.Math.fma(_t12, _t8, _t11 * _t5), 0.0f, 0.0f, 0.0f, 0.0f);
        } else {
            float _t0 = 0.5f * angleX;
            float _t1 = 0.5f * angleY;
            float _t2 = 0.5f * angleZ;
            float _t3 = Math.sinWithCos(_t0);
            float _t4 = Math.cosWithSin(_t1);
            float _t5 = Math.cosWithSin(_t2);
            float _t6 = Math.sinWithCos(_t1);
            float _t7 = Math.cosWithSin(_t0);
            float _t8 = Math.sinWithCos(_t2);
            float _t9 = _t3 * _t4;
            float _t10 = _t6 * _t7;
            float _t11 = _t7 * _t4;
            float _t12 = _t3 * _t6;
            return new FloatDualQuat(((_t9) * (_t5) + (_t10 * _t8)), ((_t10) * (_t5) - (_t9 * _t8)), ((_t11) * (_t8) - (_t12 * _t5)), ((_t12) * (_t8) + (_t11 * _t5)), 0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    /**
     * Create a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationYZX(float angleY, float angleZ, float angleX) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = 0.5f * angleY;
            float _t1 = 0.5f * angleZ;
            float _t2 = 0.5f * angleX;
            float _t3 = Math.sinWithCos(_t0);
            float _t4 = Math.sinWithCos(_t1);
            float _t5 = Math.cosWithSin(_t2);
            float _t6 = Math.cosWithSin(_t0);
            float _t7 = Math.cosWithSin(_t1);
            float _t8 = Math.sinWithCos(_t2);
            float _t9 = _t3 * _t4;
            float _t10 = _t6 * _t7;
            float _t11 = _t3 * _t7;
            float _t12 = _t4 * _t6;
            return new FloatDualQuat(java.lang.Math.fma(_t9, _t5, _t10 * _t8), java.lang.Math.fma(_t11, _t5, _t12 * _t8), java.lang.Math.fma(_t12, _t5, _t11 * -_t8), java.lang.Math.fma(_t10, _t5, _t9 * -_t8), 0.0f, 0.0f, 0.0f, 0.0f);
        } else {
            float _t0 = 0.5f * angleY;
            float _t1 = 0.5f * angleZ;
            float _t2 = 0.5f * angleX;
            float _t3 = Math.sinWithCos(_t0);
            float _t4 = Math.sinWithCos(_t1);
            float _t5 = Math.cosWithSin(_t2);
            float _t6 = Math.cosWithSin(_t0);
            float _t7 = Math.cosWithSin(_t1);
            float _t8 = Math.sinWithCos(_t2);
            float _t9 = _t3 * _t4;
            float _t10 = _t6 * _t7;
            float _t11 = _t3 * _t7;
            float _t12 = _t4 * _t6;
            return new FloatDualQuat(((_t9) * (_t5) + (_t10 * _t8)), ((_t11) * (_t5) + (_t12 * _t8)), ((_t12) * (_t5) - (_t11 * _t8)), ((_t10) * (_t5) - (_t9 * _t8)), 0.0f, 0.0f, 0.0f, 0.0f);
        }
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
        return new FloatDualQuat(0.0f, 0.0f, Math.sinWithCos(_t0), Math.cosWithSin(_t0), 0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Create a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationZXY(float angleZ, float angleX, float angleY) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = 0.5f * angleX;
            float _t1 = 0.5f * angleZ;
            float _t2 = 0.5f * angleY;
            float _t3 = Math.sinWithCos(_t0);
            float _t4 = Math.cosWithSin(_t1);
            float _t5 = Math.cosWithSin(_t2);
            float _t6 = Math.sinWithCos(_t1);
            float _t7 = Math.cosWithSin(_t0);
            float _t8 = Math.sinWithCos(_t2);
            float _t9 = _t3 * _t4;
            float _t10 = _t6 * _t7;
            float _t11 = _t3 * _t6;
            float _t12 = _t7 * _t4;
            return new FloatDualQuat(java.lang.Math.fma(_t9, _t5, _t10 * -_t8), java.lang.Math.fma(_t11, _t5, _t12 * _t8), java.lang.Math.fma(_t9, _t8, _t10 * _t5), java.lang.Math.fma(_t12, _t5, _t11 * -_t8), 0.0f, 0.0f, 0.0f, 0.0f);
        } else {
            float _t0 = 0.5f * angleX;
            float _t1 = 0.5f * angleZ;
            float _t2 = 0.5f * angleY;
            float _t3 = Math.sinWithCos(_t0);
            float _t4 = Math.cosWithSin(_t1);
            float _t5 = Math.cosWithSin(_t2);
            float _t6 = Math.sinWithCos(_t1);
            float _t7 = Math.cosWithSin(_t0);
            float _t8 = Math.sinWithCos(_t2);
            float _t9 = _t3 * _t4;
            float _t10 = _t6 * _t7;
            float _t11 = _t3 * _t6;
            float _t12 = _t7 * _t4;
            return new FloatDualQuat(((_t9) * (_t5) - (_t10 * _t8)), ((_t11) * (_t5) + (_t12 * _t8)), ((_t9) * (_t8) + (_t10 * _t5)), ((_t12) * (_t5) - (_t11 * _t8)), 0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    /**
     * Create a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return the resulting dual quaternion
     */
    public static FloatDualQuat makeRotationZYX(float angleZ, float angleY, float angleX) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = 0.5f * angleY;
            float _t1 = 0.5f * angleZ;
            float _t2 = 0.5f * angleX;
            float _t3 = Math.cosWithSin(_t0);
            float _t4 = Math.cosWithSin(_t1);
            float _t5 = Math.sinWithCos(_t2);
            float _t6 = Math.sinWithCos(_t0);
            float _t7 = Math.sinWithCos(_t1);
            float _t8 = Math.cosWithSin(_t2);
            float _t9 = _t3 * _t4;
            float _t10 = _t6 * _t7;
            float _t11 = _t6 * _t4;
            float _t12 = _t7 * _t3;
            return new FloatDualQuat(java.lang.Math.fma(_t9, _t5, -(_t10 * _t8)), java.lang.Math.fma(_t11, _t8, _t12 * _t5), java.lang.Math.fma(_t12, _t8, -(_t11 * _t5)), java.lang.Math.fma(_t10, _t5, _t9 * _t8), 0.0f, 0.0f, 0.0f, 0.0f);
        } else {
            float _t0 = 0.5f * angleY;
            float _t1 = 0.5f * angleZ;
            float _t2 = 0.5f * angleX;
            float _t3 = Math.cosWithSin(_t0);
            float _t4 = Math.cosWithSin(_t1);
            float _t5 = Math.sinWithCos(_t2);
            float _t6 = Math.sinWithCos(_t0);
            float _t7 = Math.sinWithCos(_t1);
            float _t8 = Math.cosWithSin(_t2);
            float _t9 = _t3 * _t4;
            float _t10 = _t6 * _t7;
            float _t11 = _t6 * _t4;
            float _t12 = _t7 * _t3;
            return new FloatDualQuat(((_t9) * (_t5) - (_t10 * _t8)), ((_t11) * (_t8) + (_t12 * _t5)), ((_t12) * (_t8) - (_t11 * _t5)), ((_t10) * (_t5) + (_t9 * _t8)), 0.0f, 0.0f, 0.0f, 0.0f);
        }
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
        return preRotate(rotation.x(), rotation.y(), rotation.z(), rotation.w());
    }

    /** Private tail of {@code preRotate}; reached only through it. */
    private FloatDualQuat preRotate_s16969c0a_tail_fma(float rotationX, float rotationW, float rotationY, float rotationZ, float _sfx0, float _sfx1, float _sfx2, float _sfx3) {
        float _sfx4 = java.lang.Math.fma(rotationX, this.dW, rotationW * this.dX) + java.lang.Math.fma(rotationY, this.dZ, -rotationZ * this.dY);
        float _sfx5 = java.lang.Math.fma(rotationY, this.dW, rotationZ * this.dX) + java.lang.Math.fma(rotationW, this.dY, -rotationX * this.dZ);
        float _sfx6 = java.lang.Math.fma(rotationX, this.dY, rotationW * this.dZ) + java.lang.Math.fma(rotationZ, this.dW, -rotationY * this.dX);
        float _sfx7 = java.lang.Math.fma(rotationW, this.dW, -rotationX * this.dX) - java.lang.Math.fma(rotationY, this.dY, rotationZ * this.dZ);
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code preRotate}; reached only through it. */
    private FloatDualQuat preRotate_s16969c0a_tail_mulAdd(float rotationX, float rotationW, float rotationY, float rotationZ, float _sfx0, float _sfx1, float _sfx2, float _sfx3) {
        float _sfx4 = ((rotationX) * (this.dW) + (rotationW * this.dX)) + ((rotationY) * (this.dZ) - (rotationZ * this.dY));
        float _sfx5 = ((rotationY) * (this.dW) + (rotationZ * this.dX)) + ((rotationW) * (this.dY) - (rotationX * this.dZ));
        float _sfx6 = ((rotationX) * (this.dY) + (rotationW * this.dZ)) + ((rotationZ) * (this.dW) - (rotationY * this.dX));
        float _sfx7 = ((rotationW) * (this.dW) - (rotationX * this.dX)) - ((rotationY) * (this.dY) + (rotationZ * this.dZ));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _sfx0 = java.lang.Math.fma(rotationX, this.rW, rotationW * this.rX) + java.lang.Math.fma(rotationY, this.rZ, -rotationZ * this.rY);
            float _sfx1 = java.lang.Math.fma(rotationY, this.rW, rotationZ * this.rX) + java.lang.Math.fma(rotationW, this.rY, -rotationX * this.rZ);
            float _sfx2 = java.lang.Math.fma(rotationX, this.rY, rotationW * this.rZ) + java.lang.Math.fma(rotationZ, this.rW, -rotationY * this.rX);
            float _sfx3 = java.lang.Math.fma(rotationW, this.rW, -rotationX * this.rX) - java.lang.Math.fma(rotationY, this.rY, rotationZ * this.rZ);
            return preRotate_s16969c0a_tail_fma(rotationX, rotationW, rotationY, rotationZ, _sfx0, _sfx1, _sfx2, _sfx3);
        } else {
            float _sfx0 = ((rotationX) * (this.rW) + (rotationW * this.rX)) + ((rotationY) * (this.rZ) - (rotationZ * this.rY));
            float _sfx1 = ((rotationY) * (this.rW) + (rotationZ * this.rX)) + ((rotationW) * (this.rY) - (rotationX * this.rZ));
            float _sfx2 = ((rotationX) * (this.rY) + (rotationW * this.rZ)) + ((rotationZ) * (this.rW) - (rotationY * this.rX));
            float _sfx3 = ((rotationW) * (this.rW) - (rotationX * this.rX)) - ((rotationY) * (this.rY) + (rotationZ * this.rZ));
            return preRotate_s16969c0a_tail_mulAdd(rotationX, rotationW, rotationY, rotationZ, _sfx0, _sfx1, _sfx2, _sfx3);
        }
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
        return rotate(rotation.x(), rotation.y(), rotation.z(), rotation.w());
    }

    /** Private tail of {@code rotate}; reached only through it. */
    private FloatDualQuat rotate_s16969c0a_tail_fma(float rotationX, float rotationW, float rotationZ, float rotationY, float _sfx0, float _sfx1, float _sfx2, float _sfx3) {
        float _sfx4 = java.lang.Math.fma(rotationX, this.dW, rotationW * this.dX) + java.lang.Math.fma(rotationZ, this.dY, -rotationY * this.dZ);
        float _sfx5 = java.lang.Math.fma(rotationX, this.dZ, rotationW * this.dY) + java.lang.Math.fma(rotationY, this.dW, -rotationZ * this.dX);
        float _sfx6 = java.lang.Math.fma(rotationY, this.dX, rotationZ * this.dW) + java.lang.Math.fma(rotationW, this.dZ, -rotationX * this.dY);
        float _sfx7 = java.lang.Math.fma(rotationW, this.dW, -rotationX * this.dX) - java.lang.Math.fma(rotationY, this.dY, rotationZ * this.dZ);
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code rotate}; reached only through it. */
    private FloatDualQuat rotate_s16969c0a_tail_mulAdd(float rotationX, float rotationW, float rotationZ, float rotationY, float _sfx0, float _sfx1, float _sfx2, float _sfx3) {
        float _sfx4 = ((rotationX) * (this.dW) + (rotationW * this.dX)) + ((rotationZ) * (this.dY) - (rotationY * this.dZ));
        float _sfx5 = ((rotationX) * (this.dZ) + (rotationW * this.dY)) + ((rotationY) * (this.dW) - (rotationZ * this.dX));
        float _sfx6 = ((rotationY) * (this.dX) + (rotationZ * this.dW)) + ((rotationW) * (this.dZ) - (rotationX * this.dY));
        float _sfx7 = ((rotationW) * (this.dW) - (rotationX * this.dX)) - ((rotationY) * (this.dY) + (rotationZ * this.dZ));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _sfx0 = java.lang.Math.fma(rotationX, this.rW, rotationW * this.rX) + java.lang.Math.fma(rotationZ, this.rY, -rotationY * this.rZ);
            float _sfx1 = java.lang.Math.fma(rotationX, this.rZ, rotationW * this.rY) + java.lang.Math.fma(rotationY, this.rW, -rotationZ * this.rX);
            float _sfx2 = java.lang.Math.fma(rotationY, this.rX, rotationZ * this.rW) + java.lang.Math.fma(rotationW, this.rZ, -rotationX * this.rY);
            float _sfx3 = java.lang.Math.fma(rotationW, this.rW, -rotationX * this.rX) - java.lang.Math.fma(rotationY, this.rY, rotationZ * this.rZ);
            return rotate_s16969c0a_tail_fma(rotationX, rotationW, rotationZ, rotationY, _sfx0, _sfx1, _sfx2, _sfx3);
        } else {
            float _sfx0 = ((rotationX) * (this.rW) + (rotationW * this.rX)) + ((rotationZ) * (this.rY) - (rotationY * this.rZ));
            float _sfx1 = ((rotationX) * (this.rZ) + (rotationW * this.rY)) + ((rotationY) * (this.rW) - (rotationZ * this.rX));
            float _sfx2 = ((rotationY) * (this.rX) + (rotationZ * this.rW)) + ((rotationW) * (this.rZ) - (rotationX * this.rY));
            float _sfx3 = ((rotationW) * (this.rW) - (rotationX * this.rX)) - ((rotationY) * (this.rY) + (rotationZ * this.rZ));
            return rotate_s16969c0a_tail_mulAdd(rotationX, rotationW, rotationZ, rotationY, _sfx0, _sfx1, _sfx2, _sfx3);
        }
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
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        if (org.joml2.internal.FmaSwitch.ON) return rotateAxis_fma(angle, axisX, axisY, axisZ);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ);
    }

    /** Private tail of {@code rotateAxis}; reached only through it. */
    private FloatDualQuat rotateAxis_s3b957671_tail_fma(float _t1, float _t3, float _t4, float _t5, float _sfx0, float _sfx1, float _sfx2, float _sfx3) {
        float _sfx4 = java.lang.Math.fma(this.dX, _t1, this.dW * _t3) + java.lang.Math.fma(this.dY, _t4, -(this.dZ * _t5));
        float _sfx5 = java.lang.Math.fma(this.dY, _t1, this.dZ * _t3) + java.lang.Math.fma(this.dW, _t5, -this.dX * _t4);
        float _sfx6 = java.lang.Math.fma(this.dX, _t5, this.dW * _t4) + java.lang.Math.fma(this.dZ, _t1, -(this.dY * _t3));
        float _sfx7 = java.lang.Math.fma(this.dW, _t1, -this.dX * _t3) - java.lang.Math.fma(this.dY, _t5, this.dZ * _t4);
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code rotateAxis}; reached only through it. */
    private FloatDualQuat rotateAxis_s3b957671_tail_mulAdd(float _t1, float _t3, float _t4, float _t5, float _sfx0, float _sfx1, float _sfx2, float _sfx3) {
        float _sfx4 = ((this.dX) * (_t1) + (this.dW * _t3)) + ((this.dY) * (_t4) - (this.dZ * _t5));
        float _sfx5 = ((this.dY) * (_t1) + (this.dZ * _t3)) + ((this.dW) * (_t5) - (this.dX * _t4));
        float _sfx6 = ((this.dX) * (_t5) + (this.dW * _t4)) + ((this.dZ) * (_t1) - (this.dY * _t3));
        float _sfx7 = ((this.dW) * (_t1) - (this.dX * _t3)) - ((this.dY) * (_t5) + (this.dZ * _t4));
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
        if (org.joml2.internal.FmaSwitch.ON) return rotateAxis_fma(angle, axisX, axisY, axisZ);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateAxis_fma(float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        float _sfx0 = java.lang.Math.fma(this.rX, _t1, this.rW * _t3) + java.lang.Math.fma(this.rY, _t4, -(this.rZ * _t5));
        float _sfx1 = java.lang.Math.fma(this.rY, _t1, this.rZ * _t3) + java.lang.Math.fma(this.rW, _t5, -this.rX * _t4);
        float _sfx2 = java.lang.Math.fma(this.rX, _t5, this.rW * _t4) + java.lang.Math.fma(this.rZ, _t1, -(this.rY * _t3));
        float _sfx3 = java.lang.Math.fma(this.rW, _t1, -this.rX * _t3) - java.lang.Math.fma(this.rY, _t5, this.rZ * _t4);
        return rotateAxis_s3b957671_tail_fma(_t1, _t3, _t4, _t5, _sfx0, _sfx1, _sfx2, _sfx3);
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateAxis_mulAdd(float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        float _sfx0 = ((this.rX) * (_t1) + (this.rW * _t3)) + ((this.rY) * (_t4) - (this.rZ * _t5));
        float _sfx1 = ((this.rY) * (_t1) + (this.rZ * _t3)) + ((this.rW) * (_t5) - (this.rX * _t4));
        float _sfx2 = ((this.rX) * (_t5) + (this.rW * _t4)) + ((this.rZ) * (_t1) - (this.rY * _t3));
        float _sfx3 = ((this.rW) * (_t1) - (this.rX * _t3)) - ((this.rY) * (_t5) + (this.rZ * _t4));
        return rotateAxis_s3b957671_tail_mulAdd(_t1, _t3, _t4, _t5, _sfx0, _sfx1, _sfx2, _sfx3);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            return new FloatDualQuat(java.lang.Math.fma(this.rX, _t1, this.rW * _t2), java.lang.Math.fma(this.rY, _t1, this.rZ * _t2), java.lang.Math.fma(this.rZ, _t1, -(this.rY * _t2)), java.lang.Math.fma(this.rW, _t1, -(this.rX * _t2)), java.lang.Math.fma(this.dX, _t1, this.dW * _t2), java.lang.Math.fma(this.dY, _t1, this.dZ * _t2), java.lang.Math.fma(this.dZ, _t1, -(this.dY * _t2)), java.lang.Math.fma(this.dW, _t1, -(this.dX * _t2)));
        } else {
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            return new FloatDualQuat(((this.rX) * (_t1) + (this.rW * _t2)), ((this.rY) * (_t1) + (this.rZ * _t2)), ((this.rZ) * (_t1) - (this.rY * _t2)), ((this.rW) * (_t1) - (this.rX * _t2)), ((this.dX) * (_t1) + (this.dW * _t2)), ((this.dY) * (_t1) + (this.dZ * _t2)), ((this.dZ) * (_t1) - (this.dY * _t2)), ((this.dW) * (_t1) - (this.dX * _t2)));
        }
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY}
     * and {@code rotateZYX}; reached only through them.
     */
    private FloatDualQuat rotateXYZ_s6e793366_tail_fma(float _t22, float _t20, float _t21, float _t19, float _sfx0, float _sfx1) {
        float _sfx2 = java.lang.Math.fma(this.rX, _t22, this.rW * _t20) + java.lang.Math.fma(this.rZ, _t21, -(this.rY * _t19));
        float _sfx3 = java.lang.Math.fma(this.rW, _t21, -this.rX * _t19) - java.lang.Math.fma(this.rY, _t22, this.rZ * _t20);
        float _sfx4 = java.lang.Math.fma(this.dX, _t21, this.dW * _t19) + java.lang.Math.fma(this.dY, _t20, -(this.dZ * _t22));
        float _sfx5 = java.lang.Math.fma(this.dY, _t21, this.dZ * _t19) + java.lang.Math.fma(this.dW, _t22, -this.dX * _t20);
        float _sfx6 = java.lang.Math.fma(this.dX, _t22, this.dW * _t20) + java.lang.Math.fma(this.dZ, _t21, -(this.dY * _t19));
        float _sfx7 = java.lang.Math.fma(this.dW, _t21, -this.dX * _t19) - java.lang.Math.fma(this.dY, _t22, this.dZ * _t20);
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY}
     * and {@code rotateZYX}; reached only through them.
     */
    private FloatDualQuat rotateXYZ_s6e793366_tail_mulAdd(float _t22, float _t20, float _t21, float _t19, float _sfx0, float _sfx1) {
        float _sfx2 = ((this.rX) * (_t22) + (this.rW * _t20)) + ((this.rZ) * (_t21) - (this.rY * _t19));
        float _sfx3 = ((this.rW) * (_t21) - (this.rX * _t19)) - ((this.rY) * (_t22) + (this.rZ * _t20));
        float _sfx4 = ((this.dX) * (_t21) + (this.dW * _t19)) + ((this.dY) * (_t20) - (this.dZ * _t22));
        float _sfx5 = ((this.dY) * (_t21) + (this.dZ * _t19)) + ((this.dW) * (_t22) - (this.dX * _t20));
        float _sfx6 = ((this.dX) * (_t22) + (this.dW * _t20)) + ((this.dZ) * (_t21) - (this.dY * _t19));
        float _sfx7 = ((this.dW) * (_t21) - (this.dX * _t19)) - ((this.dY) * (_t22) + (this.dZ * _t20));
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X), to this dual quaternion, returning the result as a value.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateXYZ_mulAdd(angleX, angleY, angleZ);
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        float _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        float _sfx0 = java.lang.Math.fma(this.rX, _t21, this.rW * _t19) + java.lang.Math.fma(this.rY, _t20, -(this.rZ * _t22));
        float _sfx1 = java.lang.Math.fma(this.rY, _t21, this.rZ * _t19) + java.lang.Math.fma(this.rW, _t22, -this.rX * _t20);
        return rotateXYZ_s6e793366_tail_fma(_t22, _t20, _t21, _t19, _sfx0, _sfx1);
    }

    /** {@code rotateXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateXYZ_mulAdd(float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = ((_t11) * (_t5) + (_t12 * _t8));
        float _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t12) * (_t5) - (_t11 * _t8));
        float _sfx0 = ((this.rX) * (_t21) + (this.rW * _t19)) + ((this.rY) * (_t20) - (this.rZ * _t22));
        float _sfx1 = ((this.rY) * (_t21) + (this.rZ * _t19)) + ((this.rW) * (_t22) - (this.rX * _t20));
        return rotateXYZ_s6e793366_tail_mulAdd(_t22, _t20, _t21, _t19, _sfx0, _sfx1);
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X), to this dual quaternion, returning the result as a value.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateXZY_mulAdd(angleX, angleZ, angleY);
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        float _t21 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        float _t22 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        float _sfx0 = java.lang.Math.fma(this.rX, _t19, this.rW * _t21) + java.lang.Math.fma(this.rY, _t20, -(this.rZ * _t22));
        float _sfx1 = java.lang.Math.fma(this.rY, _t19, this.rZ * _t21) + java.lang.Math.fma(this.rW, _t22, -this.rX * _t20);
        return rotateXYZ_s6e793366_tail_fma(_t22, _t20, _t19, _t21, _sfx0, _sfx1);
    }

    /** {@code rotateXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateXZY_mulAdd(float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t11) * (_t5) + (_t12 * _t8));
        float _t21 = ((_t11) * (_t8) - (_t12 * _t5));
        float _t22 = ((_t10) * (_t5) - (_t9 * _t8));
        float _sfx0 = ((this.rX) * (_t19) + (this.rW * _t21)) + ((this.rY) * (_t20) - (this.rZ * _t22));
        float _sfx1 = ((this.rY) * (_t19) + (this.rZ * _t21)) + ((this.rW) * (_t22) - (this.rX * _t20));
        return rotateXYZ_s6e793366_tail_mulAdd(_t22, _t20, _t19, _t21, _sfx0, _sfx1);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            return new FloatDualQuat(java.lang.Math.fma(this.rX, _t1, -(this.rZ * _t2)), java.lang.Math.fma(this.rY, _t1, this.rW * _t2), java.lang.Math.fma(this.rX, _t2, this.rZ * _t1), java.lang.Math.fma(this.rW, _t1, -(this.rY * _t2)), java.lang.Math.fma(this.dX, _t1, -(this.dZ * _t2)), java.lang.Math.fma(this.dY, _t1, this.dW * _t2), java.lang.Math.fma(this.dX, _t2, this.dZ * _t1), java.lang.Math.fma(this.dW, _t1, -(this.dY * _t2)));
        } else {
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            return new FloatDualQuat(((this.rX) * (_t1) - (this.rZ * _t2)), ((this.rY) * (_t1) + (this.rW * _t2)), ((this.rX) * (_t2) + (this.rZ * _t1)), ((this.rW) * (_t1) - (this.rY * _t2)), ((this.dX) * (_t1) - (this.dZ * _t2)), ((this.dY) * (_t1) + (this.dW * _t2)), ((this.dX) * (_t2) + (this.dZ * _t1)), ((this.dW) * (_t1) - (this.dY * _t2)));
        }
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y), to this dual quaternion, returning the result as a value.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateYXZ_mulAdd(angleY, angleX, angleZ);
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        float _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t12, _t8, -(_t11 * _t5));
        float _sfx0 = java.lang.Math.fma(this.rX, _t19, this.rW * _t20) + java.lang.Math.fma(this.rY, _t21, -(this.rZ * _t22));
        float _sfx1 = java.lang.Math.fma(this.rY, _t19, this.rZ * _t20) + java.lang.Math.fma(this.rW, _t22, -this.rX * _t21);
        return rotateXYZ_s6e793366_tail_fma(_t22, _t21, _t19, _t20, _sfx0, _sfx1);
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateYXZ_mulAdd(float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t11) * (_t8) + (_t12 * _t5));
        float _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t12) * (_t8) - (_t11 * _t5));
        float _sfx0 = ((this.rX) * (_t19) + (this.rW * _t20)) + ((this.rY) * (_t21) - (this.rZ * _t22));
        float _sfx1 = ((this.rY) * (_t19) + (this.rZ * _t20)) + ((this.rW) * (_t22) - (this.rX * _t21));
        return rotateXYZ_s6e793366_tail_mulAdd(_t22, _t21, _t19, _t20, _sfx0, _sfx1);
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y), to this dual quaternion, returning the result as a value.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateYZX_mulAdd(angleY, angleZ, angleX);
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t3;
        float _t12 = _t6 * _t4;
        float _t19 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t20 = java.lang.Math.fma(_t12, _t5, _t11 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        float _sfx0 = java.lang.Math.fma(this.rX, _t21, this.rW * _t19) + java.lang.Math.fma(this.rY, _t22, -(this.rZ * _t20));
        float _sfx1 = java.lang.Math.fma(this.rY, _t21, this.rZ * _t19) + java.lang.Math.fma(this.rW, _t20, -this.rX * _t22);
        return rotateXYZ_s6e793366_tail_fma(_t20, _t22, _t21, _t19, _sfx0, _sfx1);
    }

    /** {@code rotateYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateYZX_mulAdd(float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t3;
        float _t12 = _t6 * _t4;
        float _t19 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t20 = ((_t12) * (_t5) + (_t11 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        float _sfx0 = ((this.rX) * (_t21) + (this.rW * _t19)) + ((this.rY) * (_t22) - (this.rZ * _t20));
        float _sfx1 = ((this.rY) * (_t21) + (this.rZ * _t19)) + ((this.rW) * (_t20) - (this.rX * _t22));
        return rotateXYZ_s6e793366_tail_mulAdd(_t20, _t22, _t21, _t19, _sfx0, _sfx1);
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            return new FloatDualQuat(java.lang.Math.fma(this.rX, _t1, this.rY * _t2), java.lang.Math.fma(this.rY, _t1, -(this.rX * _t2)), java.lang.Math.fma(this.rZ, _t1, this.rW * _t2), java.lang.Math.fma(this.rW, _t1, -(this.rZ * _t2)), java.lang.Math.fma(this.dX, _t1, this.dY * _t2), java.lang.Math.fma(this.dY, _t1, -(this.dX * _t2)), java.lang.Math.fma(this.dZ, _t1, this.dW * _t2), java.lang.Math.fma(this.dW, _t1, -(this.dZ * _t2)));
        } else {
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            return new FloatDualQuat(((this.rX) * (_t1) + (this.rY * _t2)), ((this.rY) * (_t1) - (this.rX * _t2)), ((this.rZ) * (_t1) + (this.rW * _t2)), ((this.rW) * (_t1) - (this.rZ * _t2)), ((this.dX) * (_t1) + (this.dY * _t2)), ((this.dY) * (_t1) - (this.dX * _t2)), ((this.dZ) * (_t1) + (this.dW * _t2)), ((this.dW) * (_t1) - (this.dZ * _t2)));
        }
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z), to this dual quaternion, returning the result as a value.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateZXY_mulAdd(angleZ, angleX, angleY);
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        float _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        float _sfx0 = java.lang.Math.fma(this.rX, _t21, this.rW * _t22) + java.lang.Math.fma(this.rY, _t19, -(this.rZ * _t20));
        float _sfx1 = java.lang.Math.fma(this.rY, _t21, this.rZ * _t22) + java.lang.Math.fma(this.rW, _t20, -this.rX * _t19);
        return rotateXYZ_s6e793366_tail_fma(_t20, _t19, _t21, _t22, _sfx0, _sfx1);
    }

    /** {@code rotateZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateZXY_mulAdd(float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = ((_t11) * (_t8) + (_t12 * _t5));
        float _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        float _sfx0 = ((this.rX) * (_t21) + (this.rW * _t22)) + ((this.rY) * (_t19) - (this.rZ * _t20));
        float _sfx1 = ((this.rY) * (_t21) + (this.rZ * _t22)) + ((this.rW) * (_t20) - (this.rX * _t19));
        return rotateXYZ_s6e793366_tail_mulAdd(_t20, _t19, _t21, _t22, _sfx0, _sfx1);
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z), to this dual quaternion, returning the result as a value.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateZYX_mulAdd(angleZ, angleY, angleX);
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t3 * _t7;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        float _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        float _sfx0 = java.lang.Math.fma(this.rX, _t19, this.rW * _t21) + java.lang.Math.fma(this.rY, _t22, -(this.rZ * _t20));
        float _sfx1 = java.lang.Math.fma(this.rY, _t19, this.rZ * _t21) + java.lang.Math.fma(this.rW, _t20, -this.rX * _t22);
        return rotateXYZ_s6e793366_tail_fma(_t20, _t22, _t19, _t21, _sfx0, _sfx1);
    }

    /** {@code rotateZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat rotateZYX_mulAdd(float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t3 * _t7;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t12) * (_t8) + (_t11 * _t5));
        float _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t11) * (_t8) - (_t12 * _t5));
        float _sfx0 = ((this.rX) * (_t19) + (this.rW * _t21)) + ((this.rY) * (_t22) - (this.rZ * _t20));
        float _sfx1 = ((this.rY) * (_t19) + (this.rZ * _t21)) + ((this.rW) * (_t20) - (this.rX * _t22));
        return rotateXYZ_s6e793366_tail_mulAdd(_t20, _t22, _t19, _t21, _sfx0, _sfx1);
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
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        if (!org.joml2.internal.FmaSwitch.ON) return translate_mulAdd(translationX, translationY, translationZ);
        float _t0 = -this.rZ;
        float _t1 = -this.rX;
        float _t2 = -this.rY;
        float _t3 = 0.5f * translationZ;
        float _t4 = 0.5f * translationY;
        float _t5 = 0.5f * translationX;
        return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, java.lang.Math.fma(this.rY, _t3, java.lang.Math.fma(_t0, _t4, java.lang.Math.fma(this.rW, _t5, this.dX))), java.lang.Math.fma(this.rW, _t4, java.lang.Math.fma(_t1, _t3, java.lang.Math.fma(this.rZ, _t5, this.dY))), java.lang.Math.fma(this.rX, _t4, java.lang.Math.fma(this.rW, _t3, java.lang.Math.fma(_t2, _t5, this.dZ))), java.lang.Math.fma(_t1, _t5, java.lang.Math.fma(_t2, _t4, java.lang.Math.fma(_t0, _t3, this.dW))));
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
        if (!org.joml2.internal.FmaSwitch.ON) return translate_mulAdd(translationX, translationY, translationZ);
        float _t0 = -this.rZ;
        float _t1 = -this.rX;
        float _t2 = -this.rY;
        float _t3 = 0.5f * translationZ;
        float _t4 = 0.5f * translationY;
        float _t5 = 0.5f * translationX;
        return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, java.lang.Math.fma(this.rY, _t3, java.lang.Math.fma(_t0, _t4, java.lang.Math.fma(this.rW, _t5, this.dX))), java.lang.Math.fma(this.rW, _t4, java.lang.Math.fma(_t1, _t3, java.lang.Math.fma(this.rZ, _t5, this.dY))), java.lang.Math.fma(this.rX, _t4, java.lang.Math.fma(this.rW, _t3, java.lang.Math.fma(_t2, _t5, this.dZ))), java.lang.Math.fma(_t1, _t5, java.lang.Math.fma(_t2, _t4, java.lang.Math.fma(_t0, _t3, this.dW))));
    }

    /** {@code translate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat translate_mulAdd(float translationX, float translationY, float translationZ) {
        float _t0 = -this.rZ;
        float _t1 = -this.rX;
        float _t2 = -this.rY;
        float _t3 = 0.5f * translationZ;
        float _t4 = 0.5f * translationY;
        float _t5 = 0.5f * translationX;
        return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, ((this.rY) * (_t3) + (((_t0) * (_t4) + (((this.rW) * (_t5) + (this.dX)))))), ((this.rW) * (_t4) + (((_t1) * (_t3) + (((this.rZ) * (_t5) + (this.dY)))))), ((this.rX) * (_t4) + (((this.rW) * (_t3) + (((_t2) * (_t5) + (this.dZ)))))), ((_t1) * (_t5) + (((_t2) * (_t4) + (((_t0) * (_t3) + (this.dW)))))));
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
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (org.joml2.internal.FmaSwitch.ON) {
            return transform_s2b62622f_tail_fma(pZ, pY, 2.0f * java.lang.Math.fma(pY, this.rX, pX * -this.rY), 2.0f * java.lang.Math.fma(pX, this.rZ, pZ * -this.rX), pX);
        } else {
            return transform_s2b62622f_tail_mulAdd(pZ, pY, 2.0f * ((pY) * (this.rX) - (pX * this.rY)), 2.0f * ((pX) * (this.rZ) - (pZ * this.rX)), pX);
        }
    }

    /** Private tail of {@code transform}; reached only through it. */
    private Float3 transform_s2b62622f_tail_fma(float pZ, float pY, float _t9, float _t10, float pX) {
        float _t11 = 2.0f * java.lang.Math.fma(pZ, this.rY, pY * -this.rZ);
        return new Float3(java.lang.Math.fma(this.rY, _t9, java.lang.Math.fma(-this.rZ, _t10, java.lang.Math.fma(this.rW, _t11, java.lang.Math.fma(2.0f, java.lang.Math.fma(this.rY, this.dZ, -this.rZ * this.dY) + java.lang.Math.fma(this.rW, this.dX, -this.rX * this.dW), pX)))), java.lang.Math.fma(this.rZ, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, java.lang.Math.fma(2.0f, java.lang.Math.fma(this.rZ, this.dX, -this.rX * this.dZ) + java.lang.Math.fma(this.rW, this.dY, -this.rY * this.dW), pY)))), java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rY, _t11, java.lang.Math.fma(this.rW, _t9, java.lang.Math.fma(2.0f, java.lang.Math.fma(this.rX, this.dY, -this.rY * this.dX) + java.lang.Math.fma(this.rW, this.dZ, -this.rZ * this.dW), pZ)))));
    }

    /** Private tail of {@code transform}; reached only through it. */
    private Float3 transform_s2b62622f_tail_mulAdd(float pZ, float pY, float _t9, float _t10, float pX) {
        float _t11 = 2.0f * ((pZ) * (this.rY) - (pY * this.rZ));
        return new Float3(((this.rY) * (_t9) + (((((this.rW) * (_t11) + (((2.0f) * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW))) + (pX))))) - (this.rZ) * (_t10)))), ((this.rZ) * (_t11) + (((((this.rW) * (_t10) + (((2.0f) * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW))) + (pY))))) - (this.rX) * (_t9)))), ((this.rX) * (_t10) + (((((this.rW) * (_t9) + (((2.0f) * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW))) + (pZ))))) - (this.rY) * (_t11)))));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            return transform_s2b62622f_tail_fma(pZ, pY, 2.0f * java.lang.Math.fma(pY, this.rX, pX * -this.rY), 2.0f * java.lang.Math.fma(pX, this.rZ, pZ * -this.rX), pX);
        } else {
            return transform_s2b62622f_tail_mulAdd(pZ, pY, 2.0f * ((pY) * (this.rX) - (pX * this.rY)), 2.0f * ((pX) * (this.rZ) - (pZ * this.rX)), pX);
        }
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
        return transformDirection(v.x(), v.y(), v.z());
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t9 = 2.0f * java.lang.Math.fma(this.rX, vY, -(this.rY * vX));
            float _t10 = 2.0f * java.lang.Math.fma(this.rZ, vX, -(this.rX * vZ));
            float _t11 = 2.0f * java.lang.Math.fma(this.rY, vZ, -(this.rZ * vY));
            return new Float3(java.lang.Math.fma(this.rY, _t9, java.lang.Math.fma(-this.rZ, _t10, java.lang.Math.fma(this.rW, _t11, vX))), java.lang.Math.fma(this.rZ, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vY))), java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rY, _t11, java.lang.Math.fma(this.rW, _t9, vZ))));
        } else {
            float _t9 = 2.0f * ((this.rX) * (vY) - (this.rY * vX));
            float _t10 = 2.0f * ((this.rZ) * (vX) - (this.rX * vZ));
            float _t11 = 2.0f * ((this.rY) * (vZ) - (this.rZ * vY));
            return new Float3(((this.rY) * (_t9) + (((((this.rW) * (_t11) + (vX))) - (this.rZ) * (_t10)))), ((this.rZ) * (_t11) + (((((this.rW) * (_t10) + (vY))) - (this.rX) * (_t9)))), ((this.rX) * (_t10) + (((((this.rW) * (_t9) + (vZ))) - (this.rY) * (_t11)))));
        }
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
        return transformDirectionInverse(v.x(), v.y(), v.z());
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t9 = 2.0f * java.lang.Math.fma(this.rX, vZ, -(this.rZ * vX));
            float _t10 = 2.0f * java.lang.Math.fma(this.rY, vX, -(this.rX * vY));
            float _t11 = 2.0f * java.lang.Math.fma(this.rZ, vY, -(this.rY * vZ));
            return new Float3(java.lang.Math.fma(this.rZ, _t9, java.lang.Math.fma(-this.rY, _t10, java.lang.Math.fma(this.rW, _t11, vX))), java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rZ, _t11, java.lang.Math.fma(this.rW, _t9, vY))), java.lang.Math.fma(this.rY, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vZ))));
        } else {
            float _t9 = 2.0f * ((this.rX) * (vZ) - (this.rZ * vX));
            float _t10 = 2.0f * ((this.rY) * (vX) - (this.rX * vY));
            float _t11 = 2.0f * ((this.rZ) * (vY) - (this.rY * vZ));
            return new Float3(((this.rZ) * (_t9) + (((((this.rW) * (_t11) + (vX))) - (this.rY) * (_t10)))), ((this.rX) * (_t10) + (((((this.rW) * (_t9) + (vY))) - (this.rZ) * (_t11)))), ((this.rY) * (_t11) + (((((this.rW) * (_t10) + (vZ))) - (this.rX) * (_t9)))));
        }
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
    private Float3 transformInverse_s2b62622f_tail_fma(float _t21, float _t22, float _t23) {
        float _t33 = 2.0f * java.lang.Math.fma(this.rX, _t21, -(this.rZ * _t22));
        float _t34 = 2.0f * java.lang.Math.fma(this.rY, _t22, -(this.rX * _t23));
        float _t35 = 2.0f * java.lang.Math.fma(this.rZ, _t23, -(this.rY * _t21));
        return new Float3(java.lang.Math.fma(this.rZ, _t33, java.lang.Math.fma(-this.rY, _t34, java.lang.Math.fma(this.rW, _t35, _t22))), java.lang.Math.fma(this.rX, _t34, java.lang.Math.fma(-this.rZ, _t35, java.lang.Math.fma(this.rW, _t33, _t23))), java.lang.Math.fma(this.rY, _t35, java.lang.Math.fma(-this.rX, _t33, java.lang.Math.fma(this.rW, _t34, _t21))));
    }

    /** Private tail of {@code transformInverse}; reached only through it. */
    private Float3 transformInverse_s2b62622f_tail_mulAdd(float _t21, float _t22, float _t23) {
        float _t33 = 2.0f * ((this.rX) * (_t21) - (this.rZ * _t22));
        float _t34 = 2.0f * ((this.rY) * (_t22) - (this.rX * _t23));
        float _t35 = 2.0f * ((this.rZ) * (_t23) - (this.rY * _t21));
        return new Float3(((this.rZ) * (_t33) + (((((this.rW) * (_t35) + (_t22))) - (this.rY) * (_t34)))), ((this.rX) * (_t34) + (((((this.rW) * (_t33) + (_t23))) - (this.rZ) * (_t35)))), ((this.rY) * (_t35) + (((((this.rW) * (_t34) + (_t21))) - (this.rX) * (_t33)))));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            return transformInverse_s2b62622f_tail_fma(java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, this.rZ * -this.dW), pZ), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW), pX), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW), pY));
        } else {
            return transformInverse_s2b62622f_tail_mulAdd(((pZ) - (2.0f) * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW)))), ((pX) - (2.0f) * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW)))), ((pY) - (2.0f) * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW)))));
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            return transform_s2b62622f_tail_fma(pZ, pY, 2.0f * java.lang.Math.fma(pY, this.rX, pX * -this.rY), 2.0f * java.lang.Math.fma(pX, this.rZ, pZ * -this.rX), pX);
        } else {
            return transform_s2b62622f_tail_mulAdd(pZ, pY, 2.0f * ((pY) * (this.rX) - (pX * this.rY)), 2.0f * ((pX) * (this.rZ) - (pZ * this.rX)), pX);
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            return transformInverse_s2b62622f_tail_fma(java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, this.rZ * -this.dW), pZ), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW), pX), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW), pY));
        } else {
            return transformInverse_s2b62622f_tail_mulAdd(((pZ) - (2.0f) * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW)))), ((pX) - (2.0f) * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW)))), ((pY) - (2.0f) * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW)))));
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t9 = 2.0f * java.lang.Math.fma(this.rX, vY, -(this.rY * vX));
            float _t10 = 2.0f * java.lang.Math.fma(this.rZ, vX, -(this.rX * vZ));
            float _t11 = 2.0f * java.lang.Math.fma(this.rY, vZ, -(this.rZ * vY));
            return new Float3(java.lang.Math.fma(this.rY, _t9, java.lang.Math.fma(-this.rZ, _t10, java.lang.Math.fma(this.rW, _t11, vX))), java.lang.Math.fma(this.rZ, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vY))), java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rY, _t11, java.lang.Math.fma(this.rW, _t9, vZ))));
        } else {
            float _t9 = 2.0f * ((this.rX) * (vY) - (this.rY * vX));
            float _t10 = 2.0f * ((this.rZ) * (vX) - (this.rX * vZ));
            float _t11 = 2.0f * ((this.rY) * (vZ) - (this.rZ * vY));
            return new Float3(((this.rY) * (_t9) + (((((this.rW) * (_t11) + (vX))) - (this.rZ) * (_t10)))), ((this.rZ) * (_t11) + (((((this.rW) * (_t10) + (vY))) - (this.rX) * (_t9)))), ((this.rX) * (_t10) + (((((this.rW) * (_t9) + (vZ))) - (this.rY) * (_t11)))));
        }
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
        return "FloatDualQuat(" + this.rX + ", " + this.rY + ", " + this.rZ + ", " + this.rW + ", " + this.dX + ", " + this.dY + ", " + this.dZ + ", " + this.dW + ")";
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
    public boolean equalsEpsilon(FloatDualQuat other, float epsilon) {
        return java.lang.Math.abs(rX - other.rX) <= epsilon
            && java.lang.Math.abs(rY - other.rY) <= epsilon
            && java.lang.Math.abs(rZ - other.rZ) <= epsilon
            && java.lang.Math.abs(rW - other.rW) <= epsilon
            && java.lang.Math.abs(dX - other.dX) <= epsilon
            && java.lang.Math.abs(dY - other.dY) <= epsilon
            && java.lang.Math.abs(dZ - other.dZ) <= epsilon
            && java.lang.Math.abs(dW - other.dW) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatDualQuatSegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatDualQuatSegOpsUnsafe()
            : new FloatDualQuatSegOpsMS();
        static final FloatDualQuatBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatDualQuatBbOpsUnsafe()
            : new FloatDualQuatBbOpsApi();
        static final FloatDualQuatRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatDualQuatRawOpsUnsafe()
            : new FloatDualQuatRawOpsApi();
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
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat load(float[] src) { return load(src, 0); }

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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat load(FloatBuffer buf) {
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat loadAbsolute(int index, FloatBuffer buf) {
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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat loadAbsolute(int index, ByteBuffer buf) {
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
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public FloatDualQuat storeUnsafe(long address) {
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat loadUnsafe(long address) {
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat load(long offset, MemorySegment src) {
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
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat load(double[] src) { return load(src, 0); }

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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat load(DoubleBuffer buf) {
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat loadAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat loadDouble(ByteBuffer buf) {
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat loadDoubleAbsolute(int index, ByteBuffer buf) {
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
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public FloatDualQuat storeDoubleUnsafe(long address) {
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat loadDoubleUnsafe(long address) {
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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(0L, src); }

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
     * @return a new {@code FloatDualQuat} holding the loaded elements
     */
    public static FloatDualQuat loadDouble(long offset, MemorySegment src) {
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

    private abstract static class FloatDualQuatSegOps {
        public abstract MemorySegment store(FloatDualQuat self, long offset, MemorySegment dest);
        public abstract FloatDualQuat load(long offset, MemorySegment src);
        public abstract MemorySegment storeDouble(FloatDualQuat self, long offset, MemorySegment dest);
        public abstract FloatDualQuat loadDouble(long offset, MemorySegment src);
    }

    private static final class FloatDualQuatSegOpsMS extends FloatDualQuatSegOps {
        public MemorySegment store(FloatDualQuat self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.rX);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.rY);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.rZ);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.rW);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.dX);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.dY);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, self.dZ);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, self.dW);
            return dest;
        }

        public FloatDualQuat load(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            float _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            float _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
            float _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
            float _c3 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
            float _c4 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
            float _c5 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
            float _c6 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
            float _c7 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public MemorySegment storeDouble(FloatDualQuat self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.rX);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.rY);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.rZ);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.rW);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.dX);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.dY);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.dZ);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.dW);
            return dest;
        }

        public FloatDualQuat loadDouble(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            float _c0 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            float _c1 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
            float _c2 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
            float _c3 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
            float _c4 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
            float _c5 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
            float _c6 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
            float _c7 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }
    }

    private static final class FloatDualQuatSegOpsUnsafe extends FloatDualQuatSegOps {
        private static final FloatDualQuatSegOpsMS MS = new FloatDualQuatSegOpsMS();

        public MemorySegment store(FloatDualQuat self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.store(self, offset, dest);
            if (!dest.isNative()) return storeOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.rX);
            _U.putFloat(address + 4L, self.rY);
            _U.putFloat(address + 8L, self.rZ);
            _U.putFloat(address + 12L, self.rW);
            _U.putFloat(address + 16L, self.dX);
            _U.putFloat(address + 20L, self.dY);
            _U.putFloat(address + 24L, self.dZ);
            _U.putFloat(address + 28L, self.dW);
            return dest;
        }

        private static MemorySegment storeOnHeap(FloatDualQuat self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.store(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.rX);
            _U.putFloat(bytes, address + 4L, self.rY);
            _U.putFloat(bytes, address + 8L, self.rZ);
            _U.putFloat(bytes, address + 12L, self.rW);
            _U.putFloat(bytes, address + 16L, self.dX);
            _U.putFloat(bytes, address + 20L, self.dY);
            _U.putFloat(bytes, address + 24L, self.dZ);
            _U.putFloat(bytes, address + 28L, self.dW);
            return dest;
        }

        public FloatDualQuat load(long offset, MemorySegment src) {
            if (!src.isNative()) return loadOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            float _c6 = _U.getFloat(address + 24L);
            float _c7 = _U.getFloat(address + 28L);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static FloatDualQuat loadOnHeap(long offset, MemorySegment src) {
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
            float _c6 = _U.getFloat(bytes, address + 24L);
            float _c7 = _U.getFloat(bytes, address + 28L);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public MemorySegment storeDouble(FloatDualQuat self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
            if (!dest.isNative()) return storeDoubleOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.rX);
            _U.putDouble(address + 8L, self.rY);
            _U.putDouble(address + 16L, self.rZ);
            _U.putDouble(address + 24L, self.rW);
            _U.putDouble(address + 32L, self.dX);
            _U.putDouble(address + 40L, self.dY);
            _U.putDouble(address + 48L, self.dZ);
            _U.putDouble(address + 56L, self.dW);
            return dest;
        }

        private static MemorySegment storeDoubleOnHeap(FloatDualQuat self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeDouble(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.rX);
            _U.putDouble(bytes, address + 8L, self.rY);
            _U.putDouble(bytes, address + 16L, self.rZ);
            _U.putDouble(bytes, address + 24L, self.rW);
            _U.putDouble(bytes, address + 32L, self.dX);
            _U.putDouble(bytes, address + 40L, self.dY);
            _U.putDouble(bytes, address + 48L, self.dZ);
            _U.putDouble(bytes, address + 56L, self.dW);
            return dest;
        }

        public FloatDualQuat loadDouble(long offset, MemorySegment src) {
            if (!src.isNative()) return loadDoubleOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            float _c6 = (float) _U.getDouble(address + 48L);
            float _c7 = (float) _U.getDouble(address + 56L);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static FloatDualQuat loadDoubleOnHeap(long offset, MemorySegment src) {
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
            float _c6 = (float) _U.getDouble(bytes, address + 48L);
            float _c7 = (float) _U.getDouble(bytes, address + 56L);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }
    }

    private abstract static class FloatDualQuatBbOps {
        public abstract FloatBuffer storeAbsolute(FloatDualQuat self, int index, FloatBuffer buf);
        public abstract FloatDualQuat loadAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeAbsolute(FloatDualQuat self, int index, ByteBuffer buf);
        public abstract FloatDualQuat loadAbsolute(int index, ByteBuffer buf);
        public abstract DoubleBuffer storeAbsolute(FloatDualQuat self, int index, DoubleBuffer buf);
        public abstract FloatDualQuat loadAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeDoubleAbsolute(FloatDualQuat self, int index, ByteBuffer buf);
        public abstract FloatDualQuat loadDoubleAbsolute(int index, ByteBuffer buf);
    }

    private static final class FloatDualQuatBbOpsApi extends FloatDualQuatBbOps {
        public FloatBuffer storeAbsolute(FloatDualQuat self, int index, FloatBuffer buf) {
            buf.put(index, self.rX);
            buf.put(index + 1, self.rY);
            buf.put(index + 2, self.rZ);
            buf.put(index + 3, self.rW);
            buf.put(index + 4, self.dX);
            buf.put(index + 5, self.dY);
            buf.put(index + 6, self.dZ);
            buf.put(index + 7, self.dW);
            return buf;
        }

        public FloatDualQuat loadAbsolute(int index, FloatBuffer buf) {
            float _c0 = buf.get(index);
            float _c1 = buf.get(index + 1);
            float _c2 = buf.get(index + 2);
            float _c3 = buf.get(index + 3);
            float _c4 = buf.get(index + 4);
            float _c5 = buf.get(index + 5);
            float _c6 = buf.get(index + 6);
            float _c7 = buf.get(index + 7);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeAbsolute(FloatDualQuat self, int index, ByteBuffer buf) {
            buf.putFloat(index, self.rX);
            buf.putFloat(index + 4, self.rY);
            buf.putFloat(index + 8, self.rZ);
            buf.putFloat(index + 12, self.rW);
            buf.putFloat(index + 16, self.dX);
            buf.putFloat(index + 20, self.dY);
            buf.putFloat(index + 24, self.dZ);
            buf.putFloat(index + 28, self.dW);
            return buf;
        }

        public FloatDualQuat loadAbsolute(int index, ByteBuffer buf) {
            float _c0 = buf.getFloat(index);
            float _c1 = buf.getFloat(index + 4);
            float _c2 = buf.getFloat(index + 8);
            float _c3 = buf.getFloat(index + 12);
            float _c4 = buf.getFloat(index + 16);
            float _c5 = buf.getFloat(index + 20);
            float _c6 = buf.getFloat(index + 24);
            float _c7 = buf.getFloat(index + 28);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public DoubleBuffer storeAbsolute(FloatDualQuat self, int index, DoubleBuffer buf) {
            buf.put(index, self.rX);
            buf.put(index + 1, self.rY);
            buf.put(index + 2, self.rZ);
            buf.put(index + 3, self.rW);
            buf.put(index + 4, self.dX);
            buf.put(index + 5, self.dY);
            buf.put(index + 6, self.dZ);
            buf.put(index + 7, self.dW);
            return buf;
        }

        public FloatDualQuat loadAbsolute(int index, DoubleBuffer buf) {
            float _c0 = (float) buf.get(index);
            float _c1 = (float) buf.get(index + 1);
            float _c2 = (float) buf.get(index + 2);
            float _c3 = (float) buf.get(index + 3);
            float _c4 = (float) buf.get(index + 4);
            float _c5 = (float) buf.get(index + 5);
            float _c6 = (float) buf.get(index + 6);
            float _c7 = (float) buf.get(index + 7);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeDoubleAbsolute(FloatDualQuat self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.rX);
            buf.putDouble(index + 8, self.rY);
            buf.putDouble(index + 16, self.rZ);
            buf.putDouble(index + 24, self.rW);
            buf.putDouble(index + 32, self.dX);
            buf.putDouble(index + 40, self.dY);
            buf.putDouble(index + 48, self.dZ);
            buf.putDouble(index + 56, self.dW);
            return buf;
        }

        public FloatDualQuat loadDoubleAbsolute(int index, ByteBuffer buf) {
            float _c0 = (float) buf.getDouble(index);
            float _c1 = (float) buf.getDouble(index + 8);
            float _c2 = (float) buf.getDouble(index + 16);
            float _c3 = (float) buf.getDouble(index + 24);
            float _c4 = (float) buf.getDouble(index + 32);
            float _c5 = (float) buf.getDouble(index + 40);
            float _c6 = (float) buf.getDouble(index + 48);
            float _c7 = (float) buf.getDouble(index + 56);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }
    }

    private static final class FloatDualQuatBbOpsUnsafe extends FloatDualQuatBbOps {
        private static final FloatDualQuatBbOpsApi API = new FloatDualQuatBbOpsApi();

        public FloatBuffer storeAbsolute(FloatDualQuat self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            _U.putFloat(address, self.rX);
            _U.putFloat(address + 4L, self.rY);
            _U.putFloat(address + 8L, self.rZ);
            _U.putFloat(address + 12L, self.rW);
            _U.putFloat(address + 16L, self.dX);
            _U.putFloat(address + 20L, self.dY);
            _U.putFloat(address + 24L, self.dZ);
            _U.putFloat(address + 28L, self.dW);
            return buf;
        }

        public FloatDualQuat loadAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            float _c6 = _U.getFloat(address + 24L);
            float _c7 = _U.getFloat(address + 28L);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeAbsolute(FloatDualQuat self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(address, self.rX);
            _U.putFloat(address + 4L, self.rY);
            _U.putFloat(address + 8L, self.rZ);
            _U.putFloat(address + 12L, self.rW);
            _U.putFloat(address + 16L, self.dX);
            _U.putFloat(address + 20L, self.dY);
            _U.putFloat(address + 24L, self.dZ);
            _U.putFloat(address + 28L, self.dW);
            return buf;
        }

        private static ByteBuffer storeAbsoluteElse(FloatDualQuat self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(bytes, address, self.rX);
            _U.putFloat(bytes, address + 4L, self.rY);
            _U.putFloat(bytes, address + 8L, self.rZ);
            _U.putFloat(bytes, address + 12L, self.rW);
            _U.putFloat(bytes, address + 16L, self.dX);
            _U.putFloat(bytes, address + 20L, self.dY);
            _U.putFloat(bytes, address + 24L, self.dZ);
            _U.putFloat(bytes, address + 28L, self.dW);
            return buf;
        }

        public FloatDualQuat loadAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            float _c6 = _U.getFloat(address + 24L);
            float _c7 = _U.getFloat(address + 28L);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static FloatDualQuat loadAbsoluteElse(int index, ByteBuffer buf) {
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
            float _c6 = _U.getFloat(bytes, address + 24L);
            float _c7 = _U.getFloat(bytes, address + 28L);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public DoubleBuffer storeAbsolute(FloatDualQuat self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            _U.putDouble(address, self.rX);
            _U.putDouble(address + 8L, self.rY);
            _U.putDouble(address + 16L, self.rZ);
            _U.putDouble(address + 24L, self.rW);
            _U.putDouble(address + 32L, self.dX);
            _U.putDouble(address + 40L, self.dY);
            _U.putDouble(address + 48L, self.dZ);
            _U.putDouble(address + 56L, self.dW);
            return buf;
        }

        public FloatDualQuat loadAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            float _c6 = (float) _U.getDouble(address + 48L);
            float _c7 = (float) _U.getDouble(address + 56L);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeDoubleAbsolute(FloatDualQuat self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeDoubleAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(address, self.rX);
            _U.putDouble(address + 8L, self.rY);
            _U.putDouble(address + 16L, self.rZ);
            _U.putDouble(address + 24L, self.rW);
            _U.putDouble(address + 32L, self.dX);
            _U.putDouble(address + 40L, self.dY);
            _U.putDouble(address + 48L, self.dZ);
            _U.putDouble(address + 56L, self.dW);
            return buf;
        }

        private static ByteBuffer storeDoubleAbsoluteElse(FloatDualQuat self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeDoubleAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(bytes, address, self.rX);
            _U.putDouble(bytes, address + 8L, self.rY);
            _U.putDouble(bytes, address + 16L, self.rZ);
            _U.putDouble(bytes, address + 24L, self.rW);
            _U.putDouble(bytes, address + 32L, self.dX);
            _U.putDouble(bytes, address + 40L, self.dY);
            _U.putDouble(bytes, address + 48L, self.dZ);
            _U.putDouble(bytes, address + 56L, self.dW);
            return buf;
        }

        public FloatDualQuat loadDoubleAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadDoubleAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            float _c6 = (float) _U.getDouble(address + 48L);
            float _c7 = (float) _U.getDouble(address + 56L);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static FloatDualQuat loadDoubleAbsoluteElse(int index, ByteBuffer buf) {
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
            float _c6 = (float) _U.getDouble(bytes, address + 48L);
            float _c7 = (float) _U.getDouble(bytes, address + 56L);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }
    }

    private abstract static class FloatDualQuatRawOps {
        public abstract FloatDualQuat storeUnsafe(FloatDualQuat self, long address);
        public abstract FloatDualQuat loadUnsafe(long address);
        public abstract FloatDualQuat storeDoubleUnsafe(FloatDualQuat self, long address);
        public abstract FloatDualQuat loadDoubleUnsafe(long address);
    }

    private static final class FloatDualQuatRawOpsApi extends FloatDualQuatRawOps {
        public FloatDualQuat storeUnsafe(FloatDualQuat self, long address) {
            self.store(0L, virtualMemory().asSlice(address, 32L));
            return self;
        }

        public FloatDualQuat loadUnsafe(long address) {
            return FloatDualQuat.load(0L, virtualMemory().asSlice(address, 32L));
        }

        public FloatDualQuat storeDoubleUnsafe(FloatDualQuat self, long address) {
            self.storeDouble(0L, virtualMemory().asSlice(address, 64L));
            return self;
        }

        public FloatDualQuat loadDoubleUnsafe(long address) {
            return FloatDualQuat.loadDouble(0L, virtualMemory().asSlice(address, 64L));
        }
    }

    private static final class FloatDualQuatRawOpsUnsafe extends FloatDualQuatRawOps {
        public FloatDualQuat storeUnsafe(FloatDualQuat self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.rX);
            _U.putFloat(address + 4L, self.rY);
            _U.putFloat(address + 8L, self.rZ);
            _U.putFloat(address + 12L, self.rW);
            _U.putFloat(address + 16L, self.dX);
            _U.putFloat(address + 20L, self.dY);
            _U.putFloat(address + 24L, self.dZ);
            _U.putFloat(address + 28L, self.dW);
            return self;
        }

        public FloatDualQuat storeHeap(FloatDualQuat self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.rX);
            _U.putFloat(bytes, address + 4L, self.rY);
            _U.putFloat(bytes, address + 8L, self.rZ);
            _U.putFloat(bytes, address + 12L, self.rW);
            _U.putFloat(bytes, address + 16L, self.dX);
            _U.putFloat(bytes, address + 20L, self.dY);
            _U.putFloat(bytes, address + 24L, self.dZ);
            _U.putFloat(bytes, address + 28L, self.dW);
            return self;
        }

        public FloatDualQuat loadUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            float _c6 = _U.getFloat(address + 24L);
            float _c7 = _U.getFloat(address + 28L);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public FloatDualQuat loadHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4L);
            float _c2 = _U.getFloat(bytes, address + 8L);
            float _c3 = _U.getFloat(bytes, address + 12L);
            float _c4 = _U.getFloat(bytes, address + 16L);
            float _c5 = _U.getFloat(bytes, address + 20L);
            float _c6 = _U.getFloat(bytes, address + 24L);
            float _c7 = _U.getFloat(bytes, address + 28L);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public FloatDualQuat storeDoubleUnsafe(FloatDualQuat self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.rX);
            _U.putDouble(address + 8L, self.rY);
            _U.putDouble(address + 16L, self.rZ);
            _U.putDouble(address + 24L, self.rW);
            _U.putDouble(address + 32L, self.dX);
            _U.putDouble(address + 40L, self.dY);
            _U.putDouble(address + 48L, self.dZ);
            _U.putDouble(address + 56L, self.dW);
            return self;
        }

        public FloatDualQuat storeDoubleHeap(FloatDualQuat self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.rX);
            _U.putDouble(bytes, address + 8L, self.rY);
            _U.putDouble(bytes, address + 16L, self.rZ);
            _U.putDouble(bytes, address + 24L, self.rW);
            _U.putDouble(bytes, address + 32L, self.dX);
            _U.putDouble(bytes, address + 40L, self.dY);
            _U.putDouble(bytes, address + 48L, self.dZ);
            _U.putDouble(bytes, address + 56L, self.dW);
            return self;
        }

        public FloatDualQuat loadDoubleUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            float _c6 = (float) _U.getDouble(address + 48L);
            float _c7 = (float) _U.getDouble(address + 56L);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public FloatDualQuat loadDoubleHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8L);
            float _c2 = (float) _U.getDouble(bytes, address + 16L);
            float _c3 = (float) _U.getDouble(bytes, address + 24L);
            float _c4 = (float) _U.getDouble(bytes, address + 32L);
            float _c5 = (float) _U.getDouble(bytes, address + 40L);
            float _c6 = (float) _U.getDouble(bytes, address + 48L);
            float _c7 = (float) _U.getDouble(bytes, address + 56L);
            return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }
    }
}
