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
 * Immutable dual quaternion of double-precision {@code double} components.
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
 * {@code Double.doubleToLongBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
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
public record DoubleDualQuat(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW) {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 64;

    /** The zero dual quaternion (all components 0). */
    public static final DoubleDualQuat ZERO = new DoubleDualQuat(0, 0, 0, 0, 0, 0, 0, 0);

    /** The identity dual quaternion. */
    public static final DoubleDualQuat IDENTITY = new DoubleDualQuat();

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
    public DoubleDualQuat(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW) {
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
    public DoubleDualQuat() {
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
    public DoubleDualQuat(DoubleQuat real, DoubleQuat dual) {
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
    public static DoubleDualQuat[] newArray(int length) {
        DoubleDualQuat[] a = new DoubleDualQuat[length];
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
    public static DoubleDualQuat[] newArray(int length, DoubleDualQuat init) {
        java.util.Objects.requireNonNull(init, "init");
        DoubleDualQuat[] a = new DoubleDualQuat[length];
        java.util.Arrays.fill(a, init);
        return a;
    }

    /** {@return the {@code rX} component} <p>Valid input: any value, NaN and the infinities included. */
    public double rX() { return rX; }

    /** {@return the {@code rY} component} <p>Valid input: any value, NaN and the infinities included. */
    public double rY() { return rY; }

    /** {@return the {@code rZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public double rZ() { return rZ; }

    /** {@return the {@code rW} component} <p>Valid input: any value, NaN and the infinities included. */
    public double rW() { return rW; }

    /** {@return the {@code dX} component} <p>Valid input: any value, NaN and the infinities included. */
    public double dX() { return dX; }

    /** {@return the {@code dY} component} <p>Valid input: any value, NaN and the infinities included. */
    public double dY() { return dY; }

    /** {@return the {@code dZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public double dZ() { return dZ; }

    /** {@return the {@code dW} component} <p>Valid input: any value, NaN and the infinities included. */
    public double dW() { return dW; }

    /**
     * Create a new dual quaternion from its real and dual parts.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param real the real part, i.e. the rotation quaternion
     * @param dual the dual part, carrying the translation
     * @return the resulting dual quaternion
     */
    public DoubleDualQuat set(DoubleQuat real, DoubleQuat dual) {
        return new DoubleDualQuat(real, dual);
    }

    /**
     * Add {@code other} to this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to add
     * @return the resulting dual quaternion
     */
    public DoubleDualQuat add(DoubleDualQuat other) {
        double otherRX = other.rX;
        double otherRY = other.rY;
        double otherRZ = other.rZ;
        double otherRW = other.rW;
        double otherDX = other.dX;
        double otherDY = other.dY;
        double otherDZ = other.dZ;
        double otherDW = other.dW;
        return new DoubleDualQuat(otherRX + this.rX, otherRY + this.rY, otherRZ + this.rZ, otherRW + this.rW, otherDX + this.dX, otherDY + this.dY, otherDZ + this.dZ, otherDW + this.dW);
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
    public DoubleDualQuat add(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW) {
        return new DoubleDualQuat(otherRX + this.rX, otherRY + this.rY, otherRZ + this.rZ, otherRW + this.rW, otherDX + this.dX, otherDY + this.dY, otherDZ + this.dZ, otherDW + this.dW);
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
    public DoubleDualQuat mul(double scalar) {
        return new DoubleDualQuat(scalar * this.rX, scalar * this.rY, scalar * this.rZ, scalar * this.rW, scalar * this.dX, scalar * this.dY, scalar * this.dZ, scalar * this.dW);
    }

    /**
     * Negate this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting dual quaternion
     */
    public DoubleDualQuat negate() {
        return new DoubleDualQuat(-this.rX, -this.rY, -this.rZ, -this.rW, -this.dX, -this.dY, -this.dZ, -this.dW);
    }

    /**
     * Subtract {@code other} from this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to subtract
     * @return the resulting dual quaternion
     */
    public DoubleDualQuat sub(DoubleDualQuat other) {
        double otherRX = other.rX;
        double otherRY = other.rY;
        double otherRZ = other.rZ;
        double otherRW = other.rW;
        double otherDX = other.dX;
        double otherDY = other.dY;
        double otherDZ = other.dZ;
        double otherDW = other.dW;
        return new DoubleDualQuat(this.rX - otherRX, this.rY - otherRY, this.rZ - otherRZ, this.rW - otherRW, this.dX - otherDX, this.dY - otherDY, this.dZ - otherDZ, this.dW - otherDW);
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
    public DoubleDualQuat sub(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW) {
        return new DoubleDualQuat(this.rX - otherRX, this.rY - otherRY, this.rZ - otherRZ, this.rW - otherRW, this.dX - otherDX, this.dY - otherDY, this.dZ - otherDZ, this.dW - otherDW);
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
    public DoubleDualQuat set(DoubleDualQuat v) {
        double vRX = v.rX;
        double vRY = v.rY;
        double vRZ = v.rZ;
        double vRW = v.rW;
        double vDX = v.dX;
        double vDY = v.dY;
        double vDZ = v.dZ;
        double vDW = v.dW;
        return new DoubleDualQuat(vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW);
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
    public DoubleDualQuat set(double vRX, double vRY, double vRZ, double vRW, double vDX, double vDY, double vDZ, double vDW) {
        return new DoubleDualQuat(vRX, vRY, vRZ, vRW, vDX, vDY, vDZ, vDW);
    }

    /**
     * Convert this dual quaternion to {@code float} precision, returning the result as a new
     * instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code FloatDualQuat} holding the result
     */
    public FloatDualQuat toFloat() {
        return new FloatDualQuat((float) (this.rX), (float) (this.rY), (float) (this.rZ), (float) (this.rW), (float) (this.dX), (float) (this.dY), (float) (this.dZ), (float) (this.dW));
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
    public static DoubleDualQuat makeFromRigid(DoubleRigid r) {
        double rTX = r.tX();
        double rTY = r.tY();
        double rTZ = r.tZ();
        double rRX = r.rX();
        double rRY = r.rY();
        double rRZ = r.rZ();
        double rRW = r.rW();
        if (Math.useFma()) {
            double _t0 = -rTZ;
            return new DoubleDualQuat(rRX, rRY, rRZ, rRW, 0.5 * java.lang.Math.fma(_t0, rRY, java.lang.Math.fma(rTX, rRW, rTY * rRZ)), 0.5 * java.lang.Math.fma(rTZ, rRX, java.lang.Math.fma(rTY, rRW, -rTX * rRZ)), 0.5 * java.lang.Math.fma(rTZ, rRW, java.lang.Math.fma(rTX, rRY, -rTY * rRX)), 0.5 * java.lang.Math.fma(_t0, rRZ, java.lang.Math.fma(-rTY, rRY, -rTX * rRX)));
        } else {
            double _t0 = -rTZ;
            return new DoubleDualQuat(rRX, rRY, rRZ, rRW, 0.5 * ((_t0) * (rRY) + (((rTX) * (rRW) + (rTY * rRZ)))), 0.5 * ((rTZ) * (rRX) + (((rTY) * (rRW) - (rTX * rRZ)))), 0.5 * ((rTZ) * (rRW) + (((rTX) * (rRY) - (rTY * rRX)))), 0.5 * ((_t0) * (rRZ) + (((-rTY) * (rRY) - (rTX * rRX)))));
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
    public static DoubleDualQuat makeFromRigid(double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        if (Math.useFma()) {
            double _t0 = -rTZ;
            return new DoubleDualQuat(rRX, rRY, rRZ, rRW, 0.5 * java.lang.Math.fma(_t0, rRY, java.lang.Math.fma(rTX, rRW, rTY * rRZ)), 0.5 * java.lang.Math.fma(rTZ, rRX, java.lang.Math.fma(rTY, rRW, -rTX * rRZ)), 0.5 * java.lang.Math.fma(rTZ, rRW, java.lang.Math.fma(rTX, rRY, -rTY * rRX)), 0.5 * java.lang.Math.fma(_t0, rRZ, java.lang.Math.fma(-rTY, rRY, -rTX * rRX)));
        } else {
            double _t0 = -rTZ;
            return new DoubleDualQuat(rRX, rRY, rRZ, rRW, 0.5 * ((_t0) * (rRY) + (((rTX) * (rRW) + (rTY * rRZ)))), 0.5 * ((rTZ) * (rRX) + (((rTY) * (rRW) - (rTX * rRZ)))), 0.5 * ((rTZ) * (rRW) + (((rTX) * (rRY) - (rTY * rRX)))), 0.5 * ((_t0) * (rRZ) + (((-rTY) * (rRY) - (rTX * rRX)))));
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
    public static DoubleDualQuat makeFromTransform(DoubleTransform t) {
        double tTX = t.tX();
        double tTY = t.tY();
        double tTZ = t.tZ();
        double tRX = t.rX();
        double tRY = t.rY();
        double tRZ = t.rZ();
        double tRW = t.rW();
        if (Math.useFma()) {
            double _t0 = -tTZ;
            return new DoubleDualQuat(tRX, tRY, tRZ, tRW, 0.5 * java.lang.Math.fma(_t0, tRY, java.lang.Math.fma(tTX, tRW, tTY * tRZ)), 0.5 * java.lang.Math.fma(tTZ, tRX, java.lang.Math.fma(tTY, tRW, -tTX * tRZ)), 0.5 * java.lang.Math.fma(tTZ, tRW, java.lang.Math.fma(tTX, tRY, -tTY * tRX)), 0.5 * java.lang.Math.fma(_t0, tRZ, java.lang.Math.fma(-tTY, tRY, -tTX * tRX)));
        } else {
            double _t0 = -tTZ;
            return new DoubleDualQuat(tRX, tRY, tRZ, tRW, 0.5 * ((_t0) * (tRY) + (((tTX) * (tRW) + (tTY * tRZ)))), 0.5 * ((tTZ) * (tRX) + (((tTY) * (tRW) - (tTX * tRZ)))), 0.5 * ((tTZ) * (tRW) + (((tTX) * (tRY) - (tTY * tRX)))), 0.5 * ((_t0) * (tRZ) + (((-tTY) * (tRY) - (tTX * tRX)))));
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
    public static DoubleDualQuat makeFromTransform(double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        if (Math.useFma()) {
            double _t0 = -tTZ;
            return new DoubleDualQuat(tRX, tRY, tRZ, tRW, 0.5 * java.lang.Math.fma(_t0, tRY, java.lang.Math.fma(tTX, tRW, tTY * tRZ)), 0.5 * java.lang.Math.fma(tTZ, tRX, java.lang.Math.fma(tTY, tRW, -tTX * tRZ)), 0.5 * java.lang.Math.fma(tTZ, tRW, java.lang.Math.fma(tTX, tRY, -tTY * tRX)), 0.5 * java.lang.Math.fma(_t0, tRZ, java.lang.Math.fma(-tTY, tRY, -tTX * tRX)));
        } else {
            double _t0 = -tTZ;
            return new DoubleDualQuat(tRX, tRY, tRZ, tRW, 0.5 * ((_t0) * (tRY) + (((tTX) * (tRW) + (tTY * tRZ)))), 0.5 * ((tTZ) * (tRX) + (((tTY) * (tRW) - (tTX * tRZ)))), 0.5 * ((tTZ) * (tRW) + (((tTX) * (tRY) - (tTY * tRX)))), 0.5 * ((_t0) * (tRZ) + (((-tTY) * (tRY) - (tTX * tRX)))));
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
    public DoubleRigid toRigid() {
        if (!Math.useFma()) return toRigid_mulAdd();
        return new DoubleRigid(2.0 * (java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW)), 2.0 * (java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW)), 2.0 * (java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, this.rZ * -this.dW)), this.rX, this.rY, this.rZ, this.rW);
    }

    /** {@code toRigid} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid toRigid_mulAdd() {
        return new DoubleRigid(2.0 * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW))), 2.0 * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW))), 2.0 * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW))), this.rX, this.rY, this.rZ, this.rW);
    }

    /**
     * Convert this unit dual quaternion to a TRS transform (translation and rotation from the rigid
     * motion, scale = 1), returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the resulting transform
     */
    public DoubleTransform toTransform() {
        if (!Math.useFma()) return toTransform_mulAdd();
        return new DoubleTransform(2.0 * (java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW)), 2.0 * (java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW)), 2.0 * (java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, this.rZ * -this.dW)), this.rX, this.rY, this.rZ, this.rW, 1.0, 1.0, 1.0);
    }

    /** {@code toTransform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform toTransform_mulAdd() {
        return new DoubleTransform(2.0 * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW))), 2.0 * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW))), 2.0 * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW))), this.rX, this.rY, this.rZ, this.rW, 1.0, 1.0, 1.0);
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
    public boolean isUnit(double epsilon) {
        if (Math.useFma()) {
            return java.lang.Math.abs(java.lang.Math.fma(this.rX, this.rX, java.lang.Math.fma(this.rY, this.rY, java.lang.Math.fma(this.rZ, this.rZ, java.lang.Math.fma(this.rW, this.rW, -1.0))))) <= epsilon;
        } else {
            return java.lang.Math.abs(((this.rX) * (this.rX) + (((this.rY) * (this.rY) + (((this.rZ) * (this.rZ) + (((this.rW) * (this.rW) - (1.0))))))))) <= epsilon;
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
    public static DoubleDualQuat makeFromAxisAngle(Double3 axis, double angle, Double3 translation) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        if (!Math.useFma()) return makeFromAxisAngle_mulAdd(axisX, axisY, axisZ, angle, translationX, translationY, translationZ);
        double _t0 = 0.5 * angle;
        double _t1 = -translationZ;
        double _t2 = Math.sin(_t0);
        double _t3 = axisX * _t2;
        double _t4 = axisY * _t2;
        double _t5 = axisZ * _t2;
        double _t6 = Math.cosFromSin(_t2, _t0);
        return new DoubleDualQuat(_t3, _t4, _t5, _t6, 0.5 * java.lang.Math.fma(_t1, _t4, java.lang.Math.fma(translationX, _t6, translationY * _t5)), 0.5 * java.lang.Math.fma(translationZ, _t3, java.lang.Math.fma(translationY, _t6, -translationX * _t5)), 0.5 * java.lang.Math.fma(translationZ, _t6, java.lang.Math.fma(translationX, _t4, -translationY * _t3)), 0.5 * java.lang.Math.fma(_t1, _t5, java.lang.Math.fma(-translationY, _t4, -translationX * _t3)));
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
    public static DoubleDualQuat makeFromAxisAngle(double axisX, double axisY, double axisZ, double angle, double translationX, double translationY, double translationZ) {
        if (!Math.useFma()) return makeFromAxisAngle_mulAdd(axisX, axisY, axisZ, angle, translationX, translationY, translationZ);
        double _t0 = 0.5 * angle;
        double _t1 = -translationZ;
        double _t2 = Math.sin(_t0);
        double _t3 = axisX * _t2;
        double _t4 = axisY * _t2;
        double _t5 = axisZ * _t2;
        double _t6 = Math.cosFromSin(_t2, _t0);
        return new DoubleDualQuat(_t3, _t4, _t5, _t6, 0.5 * java.lang.Math.fma(_t1, _t4, java.lang.Math.fma(translationX, _t6, translationY * _t5)), 0.5 * java.lang.Math.fma(translationZ, _t3, java.lang.Math.fma(translationY, _t6, -translationX * _t5)), 0.5 * java.lang.Math.fma(translationZ, _t6, java.lang.Math.fma(translationX, _t4, -translationY * _t3)), 0.5 * java.lang.Math.fma(_t1, _t5, java.lang.Math.fma(-translationY, _t4, -translationX * _t3)));
    }

    /** {@code makeFromAxisAngle} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleDualQuat makeFromAxisAngle_mulAdd(double axisX, double axisY, double axisZ, double angle, double translationX, double translationY, double translationZ) {
        double _t0 = 0.5 * angle;
        double _t1 = -translationZ;
        double _t2 = Math.sin(_t0);
        double _t3 = axisX * _t2;
        double _t4 = axisY * _t2;
        double _t5 = axisZ * _t2;
        double _t6 = Math.cosFromSin(_t2, _t0);
        return new DoubleDualQuat(_t3, _t4, _t5, _t6, 0.5 * ((_t1) * (_t4) + (((translationX) * (_t6) + (translationY * _t5)))), 0.5 * ((translationZ) * (_t3) + (((translationY) * (_t6) - (translationX * _t5)))), 0.5 * ((translationZ) * (_t6) + (((translationX) * (_t4) - (translationY * _t3)))), 0.5 * ((_t1) * (_t5) + (((-translationY) * (_t4) - (translationX * _t3)))));
    }

    /**
     * Create an identity dual quaternion.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting dual quaternion
     */
    public static DoubleDualQuat makeIdentity() {
        return new DoubleDualQuat(0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0);
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
    public static DoubleDualQuat makeTranslationRotation(Double3 translation, DoubleQuat rotation) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        if (Math.useFma()) {
            double _t0 = -rotationY;
            return new DoubleDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5 * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX)), 0.5 * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -rotationZ * translationX)), 0.5 * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -rotationX * translationY)), 0.5 * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -rotationX * translationX)));
        } else {
            double _t0 = -rotationY;
            return new DoubleDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5 * ((_t0) * (translationZ) + (((rotationZ) * (translationY) + (rotationW * translationX)))), 0.5 * ((rotationX) * (translationZ) + (((rotationW) * (translationY) - (rotationZ * translationX)))), 0.5 * ((rotationW) * (translationZ) + (((rotationY) * (translationX) - (rotationX * translationY)))), 0.5 * ((((_t0) * (translationY) - (rotationX * translationX))) - (rotationZ) * (translationZ)));
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
    public static DoubleDualQuat makeTranslationRotation(double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW) {
        if (Math.useFma()) {
            double _t0 = -rotationY;
            return new DoubleDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5 * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX)), 0.5 * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -rotationZ * translationX)), 0.5 * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -rotationX * translationY)), 0.5 * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -rotationX * translationX)));
        } else {
            double _t0 = -rotationY;
            return new DoubleDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5 * ((_t0) * (translationZ) + (((rotationZ) * (translationY) + (rotationW * translationX)))), 0.5 * ((rotationX) * (translationZ) + (((rotationW) * (translationY) - (rotationZ * translationX)))), 0.5 * ((rotationW) * (translationZ) + (((rotationY) * (translationX) - (rotationX * translationY)))), 0.5 * ((((_t0) * (translationY) - (rotationX * translationX))) - (rotationZ) * (translationZ)));
        }
    }

    /**
     * Create an all-zero dual quaternion.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting dual quaternion
     */
    public static DoubleDualQuat makeZero() {
        return DoubleDualQuat.ZERO;
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
    public DoubleDualQuat set(DoubleQuat rotation) {
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        return new DoubleDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.0, 0.0, 0.0, 0.0);
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
    public DoubleDualQuat set(double rotationX, double rotationY, double rotationZ, double rotationW) {
        return new DoubleDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.0, 0.0, 0.0, 0.0);
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
    public static DoubleDualQuat makeRotation(DoubleQuat rotation) {
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        return new DoubleDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.0, 0.0, 0.0, 0.0);
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
    public static DoubleDualQuat makeRotation(double rotationX, double rotationY, double rotationZ, double rotationW) {
        return new DoubleDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.0, 0.0, 0.0, 0.0);
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
    public DoubleDualQuat set(DoubleQuat rotation, Double3 translation) {
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        if (Math.useFma()) {
            double _t0 = -rotationY;
            return new DoubleDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5 * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX)), 0.5 * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -rotationZ * translationX)), 0.5 * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -rotationX * translationY)), 0.5 * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -rotationX * translationX)));
        } else {
            double _t0 = -rotationY;
            return new DoubleDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5 * ((_t0) * (translationZ) + (((rotationZ) * (translationY) + (rotationW * translationX)))), 0.5 * ((rotationX) * (translationZ) + (((rotationW) * (translationY) - (rotationZ * translationX)))), 0.5 * ((rotationW) * (translationZ) + (((rotationY) * (translationX) - (rotationX * translationY)))), 0.5 * ((((_t0) * (translationY) - (rotationX * translationX))) - (rotationZ) * (translationZ)));
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
    public DoubleDualQuat set(double rotationX, double rotationY, double rotationZ, double rotationW, double translationX, double translationY, double translationZ) {
        if (Math.useFma()) {
            double _t0 = -rotationY;
            return new DoubleDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5 * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(rotationZ, translationY, rotationW * translationX)), 0.5 * java.lang.Math.fma(rotationX, translationZ, java.lang.Math.fma(rotationW, translationY, -rotationZ * translationX)), 0.5 * java.lang.Math.fma(rotationW, translationZ, java.lang.Math.fma(rotationY, translationX, -rotationX * translationY)), 0.5 * java.lang.Math.fma(-rotationZ, translationZ, java.lang.Math.fma(_t0, translationY, -rotationX * translationX)));
        } else {
            double _t0 = -rotationY;
            return new DoubleDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5 * ((_t0) * (translationZ) + (((rotationZ) * (translationY) + (rotationW * translationX)))), 0.5 * ((rotationX) * (translationZ) + (((rotationW) * (translationY) - (rotationZ * translationX)))), 0.5 * ((rotationW) * (translationZ) + (((rotationY) * (translationX) - (rotationX * translationY)))), 0.5 * ((((_t0) * (translationY) - (rotationX * translationX))) - (rotationZ) * (translationZ)));
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
    public DoubleDualQuat set(Double3 translation) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        return new DoubleDualQuat(0.0, 0.0, 0.0, 1.0, 0.5 * translationX, 0.5 * translationY, 0.5 * translationZ, 0.0);
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
    public DoubleDualQuat set(double translationX, double translationY, double translationZ) {
        return new DoubleDualQuat(0.0, 0.0, 0.0, 1.0, 0.5 * translationX, 0.5 * translationY, 0.5 * translationZ, 0.0);
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
    public static DoubleDualQuat makeTranslation(Double3 translation) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        return new DoubleDualQuat(0.0, 0.0, 0.0, 1.0, 0.5 * translationX, 0.5 * translationY, 0.5 * translationZ, 0.0);
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
    public static DoubleDualQuat makeTranslation(double translationX, double translationY, double translationZ) {
        return new DoubleDualQuat(0.0, 0.0, 0.0, 1.0, 0.5 * translationX, 0.5 * translationY, 0.5 * translationZ, 0.0);
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
    public DoubleDualQuat dlb(DoubleDualQuat other, double t) {
        return dlb(other.rX, other.rY, other.rZ, other.rW, other.dX, other.dY, other.dZ, other.dW, t);
    }

    /** Private tail of {@code dlb}; reached only through it. */
    private DoubleDualQuat dlb_s1d9843f9_tail_fma(double _t14, double _t15, double _t16, double _t17, double t, double otherDX, double _t9, double otherDY, double otherDZ, double otherDW) {
        double _t23 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t14, _t14, _t15 * _t15) + java.lang.Math.fma(_t16, _t16, _t17 * _t17)));
        return new DoubleDualQuat(_t14 * _t23, _t15 * _t23, _t16 * _t23, _t17 * _t23, java.lang.Math.fma(t, java.lang.Math.fma(otherDX, _t9, -this.dX), this.dX) * _t23, java.lang.Math.fma(t, java.lang.Math.fma(otherDY, _t9, -this.dY), this.dY) * _t23, java.lang.Math.fma(t, java.lang.Math.fma(otherDZ, _t9, -this.dZ), this.dZ) * _t23, java.lang.Math.fma(t, java.lang.Math.fma(otherDW, _t9, -this.dW), this.dW) * _t23);
    }

    /** Private tail of {@code dlb}; reached only through it. */
    private DoubleDualQuat dlb_s1d9843f9_tail_mulAdd(double _t14, double _t15, double _t16, double _t17, double t, double otherDX, double _t9, double otherDY, double otherDZ, double otherDW) {
        double _t23 = (1.0 / java.lang.Math.sqrt(((_t14) * (_t14) + (_t15 * _t15)) + ((_t16) * (_t16) + (_t17 * _t17))));
        return new DoubleDualQuat(_t14 * _t23, _t15 * _t23, _t16 * _t23, _t17 * _t23, ((t) * (((otherDX) * (_t9) - (this.dX))) + (this.dX)) * _t23, ((t) * (((otherDY) * (_t9) - (this.dY))) + (this.dY)) * _t23, ((t) * (((otherDZ) * (_t9) - (this.dZ))) + (this.dZ)) * _t23, ((t) * (((otherDW) * (_t9) - (this.dW))) + (this.dW)) * _t23);
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
    public DoubleDualQuat dlb(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, double t) {
        if (Math.useFma()) {
            double _t9 = java.lang.Math.fma(otherRX, this.rX, otherRY * this.rY) + java.lang.Math.fma(otherRZ, this.rZ, otherRW * this.rW) < 0.0 ? -1.0 : 1.0;
            return dlb_s1d9843f9_tail_fma(java.lang.Math.fma(t, java.lang.Math.fma(otherRX, _t9, -this.rX), this.rX), java.lang.Math.fma(t, java.lang.Math.fma(otherRY, _t9, -this.rY), this.rY), java.lang.Math.fma(t, java.lang.Math.fma(otherRZ, _t9, -this.rZ), this.rZ), java.lang.Math.fma(t, java.lang.Math.fma(otherRW, _t9, -this.rW), this.rW), t, otherDX, _t9, otherDY, otherDZ, otherDW);
        } else {
            double _t9 = ((otherRX) * (this.rX) + (otherRY * this.rY)) + ((otherRZ) * (this.rZ) + (otherRW * this.rW)) < 0.0 ? -1.0 : 1.0;
            return dlb_s1d9843f9_tail_mulAdd(((t) * (((otherRX) * (_t9) - (this.rX))) + (this.rX)), ((t) * (((otherRY) * (_t9) - (this.rY))) + (this.rY)), ((t) * (((otherRZ) * (_t9) - (this.rZ))) + (this.rZ)), ((t) * (((otherRW) * (_t9) - (this.rW))) + (this.rW)), t, otherDX, _t9, otherDY, otherDZ, otherDW);
        }
    }

    /**
     * Linearly interpolate between this dual quaternion and {@code other} using the interpolation
     * factor {@code t}, returning the result as a value.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (1e17 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting dual quaternion
     */
    public DoubleDualQuat lerp(DoubleDualQuat other, double t) {
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
     * than the other one (1e17 towards 1 ends at 0).
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
    public DoubleDualQuat lerp(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, double t) {
        if (Math.useFma()) {
            return new DoubleDualQuat(java.lang.Math.fma(t, otherRX - this.rX, this.rX), java.lang.Math.fma(t, otherRY - this.rY, this.rY), java.lang.Math.fma(t, otherRZ - this.rZ, this.rZ), java.lang.Math.fma(t, otherRW - this.rW, this.rW), java.lang.Math.fma(t, otherDX - this.dX, this.dX), java.lang.Math.fma(t, otherDY - this.dY, this.dY), java.lang.Math.fma(t, otherDZ - this.dZ, this.dZ), java.lang.Math.fma(t, otherDW - this.dW, this.dW));
        } else {
            return new DoubleDualQuat(((t) * (otherRX - this.rX) + (this.rX)), ((t) * (otherRY - this.rY) + (this.rY)), ((t) * (otherRZ - this.rZ) + (this.rZ)), ((t) * (otherRW - this.rW) + (this.rW)), ((t) * (otherDX - this.dX) + (this.dX)), ((t) * (otherDY - this.dY) + (this.dY)), ((t) * (otherDZ - this.dZ) + (this.dZ)), ((t) * (otherDW - this.dW) + (this.dW)));
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
     * unit dual quaternion.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting dual quaternion
     */
    public DoubleDualQuat sclerp(DoubleDualQuat other, double t) {
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
    public DoubleDualQuat sclerp(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, double t) {
        if (!Math.useFma()) return sclerp_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW, t);
        double _t0 = -this.rZ;
        double _t8 = java.lang.Math.fma(otherRX, this.rX, otherRY * this.rY) + java.lang.Math.fma(otherRZ, this.rZ, otherRW * this.rW) < 0.0 ? -1.0 : 1.0;
        double _t9 = otherRX * _t8;
        double _t10 = otherRW * _t8;
        double _t11 = otherRZ * _t8;
        double _t12 = otherRY * _t8;
        double _t57 = java.lang.Math.fma(this.rX, _t9, this.rW * _t10);
        double _t77 = java.lang.Math.fma(_t0, _t11, -(this.rY * _t12));
        return sclerp_s5833b117_5_fma(t, _t0, _t9, _t10, _t11, _t12, otherDX * _t8, otherDW * _t8, otherDY * _t8, otherDZ * _t8, _t57, _t77, _t57 - _t77, java.lang.Math.fma(this.rY, _t9, -this.rZ * _t10) + java.lang.Math.fma(this.rW, _t11, -(this.rX * _t12)), java.lang.Math.fma(this.rX, _t11, this.rW * _t12) + java.lang.Math.fma(_t0, _t9, -(this.rY * _t10)), java.lang.Math.fma(this.rZ, _t12, -(this.rY * _t11)) + java.lang.Math.fma(this.rW, _t9, -(this.rX * _t10)));
    }

    /** {@code sclerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat sclerp_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, double t) {
        double _t0 = -this.rZ;
        double _t8 = ((otherRX) * (this.rX) + (otherRY * this.rY)) + ((otherRZ) * (this.rZ) + (otherRW * this.rW)) < 0.0 ? -1.0 : 1.0;
        double _t9 = otherRX * _t8;
        double _t10 = otherRW * _t8;
        double _t11 = otherRZ * _t8;
        double _t12 = otherRY * _t8;
        double _t57 = ((this.rX) * (_t9) + (this.rW * _t10));
        double _t77 = ((_t0) * (_t11) - (this.rY * _t12));
        return sclerp_s5833b117_5_mulAdd(t, _t0, _t9, _t10, _t11, _t12, otherDX * _t8, otherDW * _t8, otherDY * _t8, otherDZ * _t8, _t57, _t77, _t57 - _t77, ((this.rY) * (_t9) - (this.rZ * _t10)) + ((this.rW) * (_t11) - (this.rX * _t12)), ((this.rX) * (_t11) + (this.rW * _t12)) + ((_t0) * (_t9) - (this.rY * _t10)), ((this.rZ) * (_t12) - (this.rY * _t11)) + ((this.rW) * (_t9) - (this.rX * _t10)));
    }

    /** Part 1 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private double[] sclerp_s5833b117_1_fma(double t, double _t106, double _t107, double _t108, double _t105, double _t112) {
        double _t122 = java.lang.Math.fma(_t106, _t106, java.lang.Math.fma(_t107, _t107, _t108 * _t108));
        double _t124 = (1.0 / java.lang.Math.sqrt(_t122));
        double _t126 = _t124 * _t108;
        double _t127 = _t124 * _t106;
        double _t128 = _t124 * _t107;
        double _t129 = t * Math.atan2(java.lang.Math.sqrt(_t122), _t105);
        double _t130 = Math.sin(_t129);
        double _t131 = _t124 * _t112;
        double _t137 = Math.cosFromSin(_t130, _t129);
        double _t142, _t143, _t144, _t145, _t147;
        if (_t122 < 1.0E-28) {
            _t142 = 1.0;
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
        return new double[] {_t122, _t126, _t127, _t128, _t129, _t131, _t137, _t142, _t143, _t144, _t145, _t147};
    }

    /** Part 1 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private double[] sclerp_s5833b117_1_mulAdd(double t, double _t106, double _t107, double _t108, double _t105, double _t112) {
        double _t122 = ((_t106) * (_t106) + (((_t107) * (_t107) + (_t108 * _t108))));
        double _t124 = (1.0 / java.lang.Math.sqrt(_t122));
        double _t126 = _t124 * _t108;
        double _t127 = _t124 * _t106;
        double _t128 = _t124 * _t107;
        double _t129 = t * Math.atan2(java.lang.Math.sqrt(_t122), _t105);
        double _t130 = Math.sin(_t129);
        double _t131 = _t124 * _t112;
        double _t137 = Math.cosFromSin(_t130, _t129);
        double _t142, _t143, _t144, _t145, _t147;
        if (_t122 < 1.0E-28) {
            _t142 = 1.0;
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
        return new double[] {_t122, _t126, _t127, _t128, _t129, _t131, _t137, _t142, _t143, _t144, _t145, _t147};
    }

    /** Part 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private double[] sclerp_s5833b117_2_fma(double t, double _t114, double _t115, double _t116, double _t105, double[] _bundle0) {
        double _t122 = _bundle0[0];
        double _t126 = _bundle0[1];
        double _t127 = _bundle0[2];
        double _t128 = _bundle0[3];
        double _t131 = _bundle0[5];
        double _t124 = (1.0 / java.lang.Math.sqrt(_t122));
        double _t130 = Math.sin(_bundle0[4]);
        double _t133 = _t131 * _t105;
        double _t146 = (t * _t131) * _bundle0[6];
        double _t160, _t161, _t162;
        if (_t122 < 1.0E-28) {
            _t160 = t * _t114;
            _t161 = t * _t115;
            _t162 = t * _t116;
        } else {
            _t160 = java.lang.Math.fma(java.lang.Math.fma(_t133, _t126, _t114) * _t124, _t130, -(_t146 * _t126));
            _t161 = java.lang.Math.fma(java.lang.Math.fma(_t133, _t127, _t115) * _t124, _t130, -(_t146 * _t127));
            _t162 = java.lang.Math.fma(java.lang.Math.fma(_t133, _t128, _t116) * _t124, _t130, -(_t146 * _t128));
        }
        return new double[] {_t160, _t161, _t162};
    }

    /** Part 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private double[] sclerp_s5833b117_2_mulAdd(double t, double _t114, double _t115, double _t116, double _t105, double[] _bundle0) {
        double _t122 = _bundle0[0];
        double _t126 = _bundle0[1];
        double _t127 = _bundle0[2];
        double _t128 = _bundle0[3];
        double _t131 = _bundle0[5];
        double _t124 = (1.0 / java.lang.Math.sqrt(_t122));
        double _t130 = Math.sin(_bundle0[4]);
        double _t133 = _t131 * _t105;
        double _t146 = (t * _t131) * _bundle0[6];
        double _t160, _t161, _t162;
        if (_t122 < 1.0E-28) {
            _t160 = t * _t114;
            _t161 = t * _t115;
            _t162 = t * _t116;
        } else {
            _t160 = ((((_t133) * (_t126) + (_t114)) * _t124) * (_t130) - (_t146 * _t126));
            _t161 = ((((_t133) * (_t127) + (_t115)) * _t124) * (_t130) - (_t146 * _t127));
            _t162 = ((((_t133) * (_t128) + (_t116)) * _t124) * (_t130) - (_t146 * _t128));
        }
        return new double[] {_t160, _t161, _t162};
    }

    /** Part 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private double[] sclerp_s5833b117_3_fma(double[] _bundle0, double[] _bundle1) {
        double _t142 = _bundle0[7];
        double _t143 = _bundle0[8];
        double _t144 = _bundle0[9];
        double _t145 = _bundle0[10];
        return new double[] {java.lang.Math.fma(this.rX, _t142, this.rW * _t143) + java.lang.Math.fma(this.rY, _t144, -(this.rZ * _t145)), java.lang.Math.fma(this.rY, _t142, this.rZ * _t143) + java.lang.Math.fma(this.rW, _t145, -this.rX * _t144), java.lang.Math.fma(this.rX, _t145, this.rW * _t144) + java.lang.Math.fma(this.rZ, _t142, -this.rY * _t143), java.lang.Math.fma(this.rW, _t142, -this.rX * _t143) - java.lang.Math.fma(this.rY, _t145, this.rZ * _t144), java.lang.Math.fma(this.rX, _bundle0[11], this.rW * _bundle1[0]) + java.lang.Math.fma(this.rY, _bundle1[1], -(this.rZ * _bundle1[2])) + (java.lang.Math.fma(this.dX, _t142, this.dW * _t143) + java.lang.Math.fma(this.dY, _t144, -(this.dZ * _t145)))};
    }

    /** Part 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private double[] sclerp_s5833b117_3_mulAdd(double[] _bundle0, double[] _bundle1) {
        double _t142 = _bundle0[7];
        double _t143 = _bundle0[8];
        double _t144 = _bundle0[9];
        double _t145 = _bundle0[10];
        return new double[] {((this.rX) * (_t142) + (this.rW * _t143)) + ((this.rY) * (_t144) - (this.rZ * _t145)), ((this.rY) * (_t142) + (this.rZ * _t143)) + ((this.rW) * (_t145) - (this.rX * _t144)), ((this.rX) * (_t145) + (this.rW * _t144)) + ((this.rZ) * (_t142) - (this.rY * _t143)), ((this.rW) * (_t142) - (this.rX * _t143)) - ((this.rY) * (_t145) + (this.rZ * _t144)), ((this.rX) * (_bundle0[11]) + (this.rW * _bundle1[0])) + ((this.rY) * (_bundle1[1]) - (this.rZ * _bundle1[2])) + (((this.dX) * (_t142) + (this.dW * _t143)) + ((this.dY) * (_t144) - (this.dZ * _t145)))};
    }

    /** Part 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_s5833b117_4_fma(double[] _bundle0, double[] _bundle1, double[] _bundle2) {
        double _t147 = _bundle0[11];
        double _t142 = _bundle0[7];
        double _t143 = _bundle0[8];
        double _t145 = _bundle0[10];
        double _t144 = _bundle0[9];
        double _t160 = _bundle1[0];
        double _t162 = _bundle1[2];
        double _t161 = _bundle1[1];
        double _sfx5 = java.lang.Math.fma(this.rY, _t147, this.rZ * _t160) + java.lang.Math.fma(this.rW, _t162, -this.rX * _t161) + (java.lang.Math.fma(this.dY, _t142, this.dZ * _t143) + java.lang.Math.fma(this.dW, _t145, -this.dX * _t144));
        double _sfx6 = java.lang.Math.fma(this.rX, _t162, this.rW * _t161) + java.lang.Math.fma(this.rZ, _t147, -this.rY * _t160) + (java.lang.Math.fma(this.dX, _t145, this.dW * _t144) + java.lang.Math.fma(this.dZ, _t142, -this.dY * _t143));
        double _sfx7 = java.lang.Math.fma(this.rW, _t147, -this.rX * _t160) + java.lang.Math.fma((-this.rZ), _t161, -this.rY * _t162) + (java.lang.Math.fma(this.dW, _t142, -this.dX * _t143) + java.lang.Math.fma((-this.dZ), _t144, -this.dY * _t145));
        return new DoubleDualQuat((_bundle2[0]), (_bundle2[1]), (_bundle2[2]), (_bundle2[3]), (_bundle2[4]), (_sfx5), (_sfx6), (_sfx7));
    }

    /** Part 4 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_s5833b117_4_mulAdd(double[] _bundle0, double[] _bundle1, double[] _bundle2) {
        double _t147 = _bundle0[11];
        double _t142 = _bundle0[7];
        double _t143 = _bundle0[8];
        double _t145 = _bundle0[10];
        double _t144 = _bundle0[9];
        double _t160 = _bundle1[0];
        double _t162 = _bundle1[2];
        double _t161 = _bundle1[1];
        double _sfx5 = ((this.rY) * (_t147) + (this.rZ * _t160)) + ((this.rW) * (_t162) - (this.rX * _t161)) + (((this.dY) * (_t142) + (this.dZ * _t143)) + ((this.dW) * (_t145) - (this.dX * _t144)));
        double _sfx6 = ((this.rX) * (_t162) + (this.rW * _t161)) + ((this.rZ) * (_t147) - (this.rY * _t160)) + (((this.dX) * (_t145) + (this.dW * _t144)) + ((this.dZ) * (_t142) - (this.dY * _t143)));
        double _sfx7 = ((this.rW) * (_t147) - (this.rX * _t160)) + (((-this.rZ)) * (_t161) - (this.rY * _t162)) + (((this.dW) * (_t142) - (this.dX * _t143)) + (((-this.dZ)) * (_t144) - (this.dY * _t145)));
        return new DoubleDualQuat((_bundle2[0]), (_bundle2[1]), (_bundle2[2]), (_bundle2[3]), (_bundle2[4]), (_sfx5), (_sfx6), (_sfx7));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_s5833b117_5_fma(double t, double _t0, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t57, double _t77, double _t83, double _t84, double _t85, double _t86) {
        return sclerp_s5833b117_6_fma(t, _t57, _t77, _t83, _t84, _t85, _t86, java.lang.Math.fma(this.rX, _t13, this.rW * _t14) + java.lang.Math.fma(this.rY, _t15, this.rZ * _t16) + (java.lang.Math.fma(this.dX, _t9, this.dW * _t10) + java.lang.Math.fma(this.dY, _t12, this.dZ * _t11)), java.lang.Math.fma(this.rZ, _t15, -(this.rY * _t16)) + java.lang.Math.fma(this.rW, _t13, -(this.rX * _t14)) + (java.lang.Math.fma(this.dZ, _t12, -(this.dY * _t11)) + java.lang.Math.fma(this.dW, _t9, -(this.dX * _t10))), java.lang.Math.fma(this.rY, _t13, -(this.rZ * _t14)) + java.lang.Math.fma(this.rW, _t16, -(this.rX * _t15)) + (java.lang.Math.fma(this.dY, _t9, -this.dZ * _t10) + java.lang.Math.fma(this.dW, _t11, -(this.dX * _t12))), java.lang.Math.fma(this.rX, _t16, this.rW * _t15) + java.lang.Math.fma(_t0, _t13, -(this.rY * _t14)) + (java.lang.Math.fma(this.dX, _t11, this.dW * _t12) + java.lang.Math.fma((-this.dZ), _t9, -(this.dY * _t10))));
    }

    /** Piece 2 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_s5833b117_5_mulAdd(double t, double _t0, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t57, double _t77, double _t83, double _t84, double _t85, double _t86) {
        return sclerp_s5833b117_6_mulAdd(t, _t57, _t77, _t83, _t84, _t85, _t86, ((this.rX) * (_t13) + (this.rW * _t14)) + ((this.rY) * (_t15) + (this.rZ * _t16)) + (((this.dX) * (_t9) + (this.dW * _t10)) + ((this.dY) * (_t12) + (this.dZ * _t11))), ((this.rZ) * (_t15) - (this.rY * _t16)) + ((this.rW) * (_t13) - (this.rX * _t14)) + (((this.dZ) * (_t12) - (this.dY * _t11)) + ((this.dW) * (_t9) - (this.dX * _t10))), ((this.rY) * (_t13) - (this.rZ * _t14)) + ((this.rW) * (_t16) - (this.rX * _t15)) + (((this.dY) * (_t9) - (this.dZ * _t10)) + ((this.dW) * (_t11) - (this.dX * _t12))), ((this.rX) * (_t16) + (this.rW * _t15)) + ((_t0) * (_t13) - (this.rY * _t14)) + (((this.dX) * (_t11) + (this.dW * _t12)) + (((-this.dZ)) * (_t9) - (this.dY * _t10))));
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_s5833b117_6_fma(double t, double _t57, double _t77, double _t83, double _t84, double _t85, double _t86, double _t97, double _t99, double _t100, double _t101) {
        double _t105, _t106, _t107, _t108, _t112, _t114, _t115, _t116;
        if (_t83 < 0.0) {
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
        double[] _bundle0 = sclerp_s5833b117_1_fma(t, _t106, _t107, _t108, _t105, _t112);
        double[] _bundle1 = sclerp_s5833b117_2_fma(t, _t114, _t115, _t116, _t105, _bundle0);
        double[] _bundle2 = sclerp_s5833b117_3_fma(_bundle0, _bundle1);
        return sclerp_s5833b117_4_fma(_bundle0, _bundle1, _bundle2);
    }

    /** Piece 3 of {@code sclerp}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat sclerp_s5833b117_6_mulAdd(double t, double _t57, double _t77, double _t83, double _t84, double _t85, double _t86, double _t97, double _t99, double _t100, double _t101) {
        double _t105, _t106, _t107, _t108, _t112, _t114, _t115, _t116;
        if (_t83 < 0.0) {
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
        double[] _bundle0 = sclerp_s5833b117_1_mulAdd(t, _t106, _t107, _t108, _t105, _t112);
        double[] _bundle1 = sclerp_s5833b117_2_mulAdd(t, _t114, _t115, _t116, _t105, _bundle0);
        double[] _bundle2 = sclerp_s5833b117_3_mulAdd(_bundle0, _bundle1);
        return sclerp_s5833b117_4_mulAdd(_bundle0, _bundle1, _bundle2);
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
    public DoubleDualQuat mul(DoubleDualQuat other) {
        return mul(other.rX, other.rY, other.rZ, other.rW, other.dX, other.dY, other.dZ, other.dW);
    }

    /** Private tail of {@code mul}; reached only through it. */
    private DoubleDualQuat mul_s63cfeaa0_tail_fma(double otherRX, double otherRW, double otherRY, double otherRZ, double otherDX, double otherDW, double otherDY, double otherDZ, double _sfx0, double _sfx1, double _sfx2, double _sfx3, double _sfx4) {
        double _sfx5 = java.lang.Math.fma(otherRX, this.dZ, otherRW * this.dY) + java.lang.Math.fma(otherRY, this.dW, -otherRZ * this.dX) + (java.lang.Math.fma(otherDX, this.rZ, otherDW * this.rY) + java.lang.Math.fma(otherDY, this.rW, otherDZ * -this.rX));
        double _sfx6 = java.lang.Math.fma(otherRY, this.dX, otherRZ * this.dW) + java.lang.Math.fma(otherRW, this.dZ, -otherRX * this.dY) + (java.lang.Math.fma(otherDY, this.rX, otherDZ * this.rW) + java.lang.Math.fma(otherDW, this.rZ, otherDX * -this.rY));
        double _sfx7 = java.lang.Math.fma(otherRW, this.dW, -otherRX * this.dX) + java.lang.Math.fma(-otherRZ, this.dZ, -otherRY * this.dY) + (java.lang.Math.fma(otherDW, this.rW, otherDX * -this.rX) + java.lang.Math.fma(otherDZ, -this.rZ, otherDY * -this.rY));
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code mul}; reached only through it. */
    private DoubleDualQuat mul_s63cfeaa0_tail_mulAdd(double otherRX, double otherRW, double otherRY, double otherRZ, double otherDX, double otherDW, double otherDY, double otherDZ, double _sfx0, double _sfx1, double _sfx2, double _sfx3, double _sfx4) {
        double _sfx5 = ((otherRX) * (this.dZ) + (otherRW * this.dY)) + ((otherRY) * (this.dW) - (otherRZ * this.dX)) + (((otherDX) * (this.rZ) + (otherDW * this.rY)) + ((otherDY) * (this.rW) - (otherDZ * this.rX)));
        double _sfx6 = ((otherRY) * (this.dX) + (otherRZ * this.dW)) + ((otherRW) * (this.dZ) - (otherRX * this.dY)) + (((otherDY) * (this.rX) + (otherDZ * this.rW)) + ((otherDW) * (this.rZ) - (otherDX * this.rY)));
        double _sfx7 = ((otherRW) * (this.dW) - (otherRX * this.dX)) + ((-otherRZ) * (this.dZ) - (otherRY * this.dY)) + (((otherDW) * (this.rW) - (otherDX * this.rX)) + ((otherDZ) * (-this.rZ) - (otherDY * this.rY)));
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
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
    public DoubleDualQuat mul(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW) {
        if (!Math.useFma()) return mul_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW);
        double _sfx0 = java.lang.Math.fma(otherRX, this.rW, otherRW * this.rX) + java.lang.Math.fma(otherRZ, this.rY, -otherRY * this.rZ);
        double _sfx1 = java.lang.Math.fma(otherRX, this.rZ, otherRW * this.rY) + java.lang.Math.fma(otherRY, this.rW, otherRZ * -this.rX);
        double _sfx2 = java.lang.Math.fma(otherRY, this.rX, otherRZ * this.rW) + java.lang.Math.fma(otherRW, this.rZ, -otherRX * this.rY);
        double _sfx3 = java.lang.Math.fma(otherRW, this.rW, otherRX * -this.rX) - java.lang.Math.fma(otherRY, this.rY, otherRZ * this.rZ);
        double _sfx4 = java.lang.Math.fma(otherRX, this.dW, otherRW * this.dX) + java.lang.Math.fma(otherRZ, this.dY, -otherRY * this.dZ) + (java.lang.Math.fma(otherDX, this.rW, otherDW * this.rX) + java.lang.Math.fma(otherDZ, this.rY, otherDY * -this.rZ));
        return mul_s63cfeaa0_tail_fma(otherRX, otherRW, otherRY, otherRZ, otherDX, otherDW, otherDY, otherDZ, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat mul_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW) {
        double _sfx0 = ((otherRX) * (this.rW) + (otherRW * this.rX)) + ((otherRZ) * (this.rY) - (otherRY * this.rZ));
        double _sfx1 = ((otherRX) * (this.rZ) + (otherRW * this.rY)) + ((otherRY) * (this.rW) - (otherRZ * this.rX));
        double _sfx2 = ((otherRY) * (this.rX) + (otherRZ * this.rW)) + ((otherRW) * (this.rZ) - (otherRX * this.rY));
        double _sfx3 = ((otherRW) * (this.rW) - (otherRX * this.rX)) - ((otherRY) * (this.rY) + (otherRZ * this.rZ));
        double _sfx4 = ((otherRX) * (this.dW) + (otherRW * this.dX)) + ((otherRZ) * (this.dY) - (otherRY * this.dZ)) + (((otherDX) * (this.rW) + (otherDW * this.rX)) + ((otherDZ) * (this.rY) - (otherDY * this.rZ)));
        return mul_s63cfeaa0_tail_mulAdd(otherRX, otherRW, otherRY, otherRZ, otherDX, otherDW, otherDY, otherDZ, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
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
    public DoubleDualQuat preMul(DoubleDualQuat other) {
        return preMul(other.rX, other.rY, other.rZ, other.rW, other.dX, other.dY, other.dZ, other.dW);
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private DoubleDualQuat preMul_s63cfeaa0_tail_fma(double otherRY, double otherRZ, double otherRW, double otherRX, double otherDY, double otherDZ, double otherDW, double otherDX, double _sfx0, double _sfx1, double _sfx2, double _sfx3, double _sfx4) {
        double _sfx5 = java.lang.Math.fma(otherRY, this.dW, otherRZ * this.dX) + java.lang.Math.fma(otherRW, this.dY, -otherRX * this.dZ) + (java.lang.Math.fma(otherDY, this.rW, otherDZ * this.rX) + java.lang.Math.fma(otherDW, this.rY, otherDX * -this.rZ));
        double _sfx6 = java.lang.Math.fma(otherRX, this.dY, otherRW * this.dZ) + java.lang.Math.fma(otherRZ, this.dW, -otherRY * this.dX) + (java.lang.Math.fma(otherDX, this.rY, otherDW * this.rZ) + java.lang.Math.fma(otherDZ, this.rW, otherDY * -this.rX));
        double _sfx7 = java.lang.Math.fma(otherRW, this.dW, -otherRX * this.dX) + java.lang.Math.fma(-otherRZ, this.dZ, -otherRY * this.dY) + (java.lang.Math.fma(otherDW, this.rW, otherDX * -this.rX) + java.lang.Math.fma(otherDZ, -this.rZ, otherDY * -this.rY));
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private DoubleDualQuat preMul_s63cfeaa0_tail_mulAdd(double otherRY, double otherRZ, double otherRW, double otherRX, double otherDY, double otherDZ, double otherDW, double otherDX, double _sfx0, double _sfx1, double _sfx2, double _sfx3, double _sfx4) {
        double _sfx5 = ((otherRY) * (this.dW) + (otherRZ * this.dX)) + ((otherRW) * (this.dY) - (otherRX * this.dZ)) + (((otherDY) * (this.rW) + (otherDZ * this.rX)) + ((otherDW) * (this.rY) - (otherDX * this.rZ)));
        double _sfx6 = ((otherRX) * (this.dY) + (otherRW * this.dZ)) + ((otherRZ) * (this.dW) - (otherRY * this.dX)) + (((otherDX) * (this.rY) + (otherDW * this.rZ)) + ((otherDZ) * (this.rW) - (otherDY * this.rX)));
        double _sfx7 = ((otherRW) * (this.dW) - (otherRX * this.dX)) + ((-otherRZ) * (this.dZ) - (otherRY * this.dY)) + (((otherDW) * (this.rW) - (otherDX * this.rX)) + ((otherDZ) * (-this.rZ) - (otherDY * this.rY)));
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
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
    public DoubleDualQuat preMul(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW) {
        if (!Math.useFma()) return preMul_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW);
        double _sfx0 = java.lang.Math.fma(otherRX, this.rW, otherRW * this.rX) + java.lang.Math.fma(otherRY, this.rZ, -otherRZ * this.rY);
        double _sfx1 = java.lang.Math.fma(otherRY, this.rW, otherRZ * this.rX) + java.lang.Math.fma(otherRW, this.rY, -otherRX * this.rZ);
        double _sfx2 = java.lang.Math.fma(otherRX, this.rY, otherRW * this.rZ) + java.lang.Math.fma(otherRZ, this.rW, -otherRY * this.rX);
        double _sfx3 = java.lang.Math.fma(otherRW, this.rW, -otherRX * this.rX) - java.lang.Math.fma(otherRY, this.rY, otherRZ * this.rZ);
        double _sfx4 = java.lang.Math.fma(otherRX, this.dW, otherRW * this.dX) + java.lang.Math.fma(otherRY, this.dZ, -otherRZ * this.dY) + (java.lang.Math.fma(otherDX, this.rW, otherDW * this.rX) + java.lang.Math.fma(otherDY, this.rZ, otherDZ * -this.rY));
        return preMul_s63cfeaa0_tail_fma(otherRY, otherRZ, otherRW, otherRX, otherDY, otherDZ, otherDW, otherDX, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat preMul_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW) {
        double _sfx0 = ((otherRX) * (this.rW) + (otherRW * this.rX)) + ((otherRY) * (this.rZ) - (otherRZ * this.rY));
        double _sfx1 = ((otherRY) * (this.rW) + (otherRZ * this.rX)) + ((otherRW) * (this.rY) - (otherRX * this.rZ));
        double _sfx2 = ((otherRX) * (this.rY) + (otherRW * this.rZ)) + ((otherRZ) * (this.rW) - (otherRY * this.rX));
        double _sfx3 = ((otherRW) * (this.rW) - (otherRX * this.rX)) - ((otherRY) * (this.rY) + (otherRZ * this.rZ));
        double _sfx4 = ((otherRX) * (this.dW) + (otherRW * this.dX)) + ((otherRY) * (this.dZ) - (otherRZ * this.dY)) + (((otherDX) * (this.rW) + (otherDW * this.rX)) + ((otherDY) * (this.rZ) - (otherDZ * this.rY)));
        return preMul_s63cfeaa0_tail_mulAdd(otherRY, otherRZ, otherRW, otherRX, otherDY, otherDZ, otherDW, otherDX, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
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
    public DoubleDualQuat addScaled(DoubleDualQuat other, double weight) {
        double otherRX = other.rX;
        double otherRY = other.rY;
        double otherRZ = other.rZ;
        double otherRW = other.rW;
        double otherDX = other.dX;
        double otherDY = other.dY;
        double otherDZ = other.dZ;
        double otherDW = other.dW;
        if (Math.useFma()) {
            return new DoubleDualQuat(java.lang.Math.fma(weight, otherRX, this.rX), java.lang.Math.fma(weight, otherRY, this.rY), java.lang.Math.fma(weight, otherRZ, this.rZ), java.lang.Math.fma(weight, otherRW, this.rW), java.lang.Math.fma(weight, otherDX, this.dX), java.lang.Math.fma(weight, otherDY, this.dY), java.lang.Math.fma(weight, otherDZ, this.dZ), java.lang.Math.fma(weight, otherDW, this.dW));
        } else {
            return new DoubleDualQuat(((weight) * (otherRX) + (this.rX)), ((weight) * (otherRY) + (this.rY)), ((weight) * (otherRZ) + (this.rZ)), ((weight) * (otherRW) + (this.rW)), ((weight) * (otherDX) + (this.dX)), ((weight) * (otherDY) + (this.dY)), ((weight) * (otherDZ) + (this.dZ)), ((weight) * (otherDW) + (this.dW)));
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
    public DoubleDualQuat addScaled(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW, double weight) {
        if (Math.useFma()) {
            return new DoubleDualQuat(java.lang.Math.fma(weight, otherRX, this.rX), java.lang.Math.fma(weight, otherRY, this.rY), java.lang.Math.fma(weight, otherRZ, this.rZ), java.lang.Math.fma(weight, otherRW, this.rW), java.lang.Math.fma(weight, otherDX, this.dX), java.lang.Math.fma(weight, otherDY, this.dY), java.lang.Math.fma(weight, otherDZ, this.dZ), java.lang.Math.fma(weight, otherDW, this.dW));
        } else {
            return new DoubleDualQuat(((weight) * (otherRX) + (this.rX)), ((weight) * (otherRY) + (this.rY)), ((weight) * (otherRZ) + (this.rZ)), ((weight) * (otherRW) + (this.rW)), ((weight) * (otherDX) + (this.dX)), ((weight) * (otherDY) + (this.dY)), ((weight) * (otherDZ) + (this.dZ)), ((weight) * (otherDW) + (this.dW)));
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
    public DoubleDualQuat conjugate() {
        return new DoubleDualQuat(-this.rX, -this.rY, -this.rZ, this.rW, -this.dX, -this.dY, -this.dZ, this.dW);
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
    public DoubleDualQuat difference(DoubleDualQuat other) {
        return difference(other.rX, other.rY, other.rZ, other.rW, other.dX, other.dY, other.dZ, other.dW);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private DoubleDualQuat difference_s63cfeaa0_tail_fma(double otherRY, double otherRZ, double _t0, double otherRW, double otherDY, double otherDZ, double otherDX, double otherDW, double otherRX, double _sfx0, double _sfx1, double _sfx2, double _sfx3, double _sfx4) {
        double _sfx5 = java.lang.Math.fma(otherRY, this.dW, otherRZ * this.dX) + java.lang.Math.fma(_t0, this.dZ, -otherRW * this.dY) + (java.lang.Math.fma(otherDY, this.rW, otherDZ * this.rX) + java.lang.Math.fma(otherDX, -this.rZ, otherDW * -this.rY));
        double _sfx6 = java.lang.Math.fma(otherRX, this.dY, -otherRW * this.dZ) + java.lang.Math.fma(otherRZ, this.dW, -(otherRY * this.dX)) + (java.lang.Math.fma(otherDX, this.rY, otherDW * -this.rZ) + java.lang.Math.fma(otherDZ, this.rW, otherDY * -this.rX));
        double _sfx7 = java.lang.Math.fma(otherRX, this.dX, otherRW * this.dW) + java.lang.Math.fma(otherRY, this.dY, otherRZ * this.dZ) + (java.lang.Math.fma(otherDX, this.rX, otherDW * this.rW) + java.lang.Math.fma(otherDY, this.rY, otherDZ * this.rZ));
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private DoubleDualQuat difference_s63cfeaa0_tail_mulAdd(double otherRY, double otherRZ, double _t0, double otherRW, double otherDY, double otherDZ, double otherDX, double otherDW, double otherRX, double _sfx0, double _sfx1, double _sfx2, double _sfx3, double _sfx4) {
        double _sfx5 = ((otherRY) * (this.dW) + (otherRZ * this.dX)) + ((_t0) * (this.dZ) - (otherRW * this.dY)) + (((otherDY) * (this.rW) + (otherDZ * this.rX)) + ((otherDX) * (-this.rZ) - (otherDW * this.rY)));
        double _sfx6 = ((otherRX) * (this.dY) - (otherRW * this.dZ)) + ((otherRZ) * (this.dW) - (otherRY * this.dX)) + (((otherDX) * (this.rY) - (otherDW * this.rZ)) + ((otherDZ) * (this.rW) - (otherDY * this.rX)));
        double _sfx7 = ((otherRX) * (this.dX) + (otherRW * this.dW)) + ((otherRY) * (this.dY) + (otherRZ * this.dZ)) + (((otherDX) * (this.rX) + (otherDW * this.rW)) + ((otherDY) * (this.rY) + (otherDZ * this.rZ)));
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
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
    public DoubleDualQuat difference(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW) {
        if (!Math.useFma()) return difference_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherDX, otherDY, otherDZ, otherDW);
        double _t0 = -otherRX;
        double _sfx0 = java.lang.Math.fma(otherRX, this.rW, -otherRW * this.rX) + java.lang.Math.fma(otherRY, this.rZ, otherRZ * -this.rY);
        double _sfx1 = java.lang.Math.fma(otherRY, this.rW, otherRZ * this.rX) + java.lang.Math.fma(_t0, this.rZ, -otherRW * this.rY);
        double _sfx2 = java.lang.Math.fma(otherRX, this.rY, -otherRW * this.rZ) + java.lang.Math.fma(otherRZ, this.rW, otherRY * -this.rX);
        double _sfx3 = java.lang.Math.fma(otherRX, this.rX, otherRW * this.rW) - java.lang.Math.fma(otherRZ, -this.rZ, otherRY * -this.rY);
        double _sfx4 = java.lang.Math.fma(otherRX, this.dW, -otherRW * this.dX) + java.lang.Math.fma(otherRY, this.dZ, -(otherRZ * this.dY)) + (java.lang.Math.fma(otherDX, this.rW, otherDW * -this.rX) + java.lang.Math.fma(otherDY, this.rZ, otherDZ * -this.rY));
        return difference_s63cfeaa0_tail_fma(otherRY, otherRZ, _t0, otherRW, otherDY, otherDZ, otherDX, otherDW, otherRX, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat difference_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW) {
        double _t0 = -otherRX;
        double _sfx0 = ((otherRX) * (this.rW) - (otherRW * this.rX)) + ((otherRY) * (this.rZ) - (otherRZ * this.rY));
        double _sfx1 = ((otherRY) * (this.rW) + (otherRZ * this.rX)) + ((_t0) * (this.rZ) - (otherRW * this.rY));
        double _sfx2 = ((otherRX) * (this.rY) - (otherRW * this.rZ)) + ((otherRZ) * (this.rW) - (otherRY * this.rX));
        double _sfx3 = ((otherRX) * (this.rX) + (otherRW * this.rW)) - ((otherRZ) * (-this.rZ) - (otherRY * this.rY));
        double _sfx4 = ((otherRX) * (this.dW) - (otherRW * this.dX)) + ((otherRY) * (this.dZ) - (otherRZ * this.dY)) + (((otherDX) * (this.rW) - (otherDW * this.rX)) + ((otherDY) * (this.rZ) - (otherDZ * this.rY)));
        return difference_s63cfeaa0_tail_mulAdd(otherRY, otherRZ, _t0, otherRW, otherDY, otherDZ, otherDX, otherDW, otherRX, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }

    /**
     * Compute the dot product of this dual quaternion and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this dual quaternion and {@code other}
     */
    public double dot(DoubleDualQuat other) {
        double otherRX = other.rX;
        double otherRY = other.rY;
        double otherRZ = other.rZ;
        double otherRW = other.rW;
        double otherDX = other.dX;
        double otherDY = other.dY;
        double otherDZ = other.dZ;
        double otherDW = other.dW;
        if (Math.useFma()) {
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
    public double dot(double otherRX, double otherRY, double otherRZ, double otherRW, double otherDX, double otherDY, double otherDZ, double otherDW) {
        if (Math.useFma()) {
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
    public DoubleDualQuat dualConjugate() {
        return new DoubleDualQuat(this.rX, this.rY, this.rZ, this.rW, -this.dX, -this.dY, -this.dZ, -this.dW);
    }

    /** Private tail of {@code exp}; reached only through it. */
    private DoubleDualQuat exp_s0_tail_fma(double _t12, double _t13, double _t4, double _t9, double _t8, double _t10, double _t11, double _sp0, double _t5) {
        double _t14 = _t12 * _t13;
        if (_t4 < 1.0E-28) {
            return new DoubleDualQuat(this.rX, this.rY, this.rZ, 1.0, this.dX, this.dY, this.dZ, -_t5);
        } else {
            return new DoubleDualQuat(_t9 * _t8, _t10 * _t8, _t11 * _t8, _t13, java.lang.Math.fma(_t9, _t14, java.lang.Math.fma(_t9, -_t12, this.dX) * _sp0), java.lang.Math.fma(_t10, _t14, java.lang.Math.fma(_t10, -_t12, this.dY) * _sp0), java.lang.Math.fma(_t11, _t14, java.lang.Math.fma(_t11, -_t12, this.dZ) * _sp0), -_t12 * _t8);
        }
    }

    /** Private tail of {@code exp}; reached only through it. */
    private DoubleDualQuat exp_s0_tail_mulAdd(double _t12, double _t13, double _t4, double _t9, double _t8, double _t10, double _t11, double _sp0, double _t5) {
        double _t14 = _t12 * _t13;
        if (_t4 < 1.0E-28) {
            return new DoubleDualQuat(this.rX, this.rY, this.rZ, 1.0, this.dX, this.dY, this.dZ, -_t5);
        } else {
            return new DoubleDualQuat(_t9 * _t8, _t10 * _t8, _t11 * _t8, _t13, ((_t9) * (_t14) + (((this.dX) - (_t9) * (_t12)) * _sp0)), ((_t10) * (_t14) + (((this.dY) - (_t10) * (_t12)) * _sp0)), ((_t11) * (_t14) + (((this.dZ) - (_t11) * (_t12)) * _sp0)), -_t12 * _t8);
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
    public DoubleDualQuat exp() {
        if (Math.useFma()) {
            double _t4 = java.lang.Math.fma(this.rZ, this.rZ, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY));
            double _t5 = java.lang.Math.fma(this.rZ, this.dZ, java.lang.Math.fma(this.rX, this.dX, this.rY * this.dY));
            double _t7 = java.lang.Math.sqrt(_t4);
            double _t6 = 1.0 / _t7;
            double _t8 = Math.sin(_t7);
            return exp_s0_tail_fma(_t5 * _t6, Math.cosFromSin(_t8, _t7), _t4, this.rX * _t6, _t8, this.rY * _t6, this.rZ * _t6, _t6 * _t8, _t5);
        } else {
            double _t4 = ((this.rZ) * (this.rZ) + (((this.rX) * (this.rX) + (this.rY * this.rY))));
            double _t5 = ((this.rZ) * (this.dZ) + (((this.rX) * (this.dX) + (this.rY * this.dY))));
            double _t7 = java.lang.Math.sqrt(_t4);
            double _t6 = 1.0 / _t7;
            double _t8 = Math.sin(_t7);
            return exp_s0_tail_mulAdd(_t5 * _t6, Math.cosFromSin(_t8, _t7), _t4, this.rX * _t6, _t8, this.rY * _t6, this.rZ * _t6, _t6 * _t8, _t5);
        }
    }

    /**
     * Get the dual part of this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting quaternion
     */
    public DoubleQuat getDual() {
        return new DoubleQuat(this.dX, this.dY, this.dZ, this.dW);
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesXYZ() {
        if (!Math.useFma()) return getEulerAnglesXYZ_mulAdd();
        double _t1 = this.rY * this.rZ;
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, this.rW, -_t1);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        double _t12 = java.lang.Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < java.lang.Math.fma(_t8, _t8, _t12) * 1.0E-15) {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rW, _t1), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t3), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, _t10), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t3), 1.0)));
        }
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd() {
        double _t1 = this.rY * this.rZ;
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * ((this.rX) * (this.rZ) + (this.rY * this.rW));
        double _t9 = 2.0 * ((this.rX) * (this.rW) - (_t1));
        double _t10 = ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
        double _t12 = ((_t10) * (_t10) + (_t9 * _t9));
        if (_t12 < ((_t8) * (_t8) + (_t12)) * 1.0E-15) {
            return new Double3(Math.atan2(2.0 * ((this.rX) * (this.rW) + (_t1)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t3))))), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, _t10), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * ((this.rZ) * (this.rW) - (this.rX * this.rY)), ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t3))))));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must not be
     * negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-15}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesXYZ(double epsilon) {
        if (!Math.useFma()) return getEulerAnglesXYZ_mulAdd(epsilon);
        double _t1 = this.rY * this.rZ;
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, this.rW, -_t1);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        double _t12 = java.lang.Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < epsilon * java.lang.Math.fma(_t8, _t8, _t12)) {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rW, _t1), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t3), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, _t10), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t3), 1.0)));
        }
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd(double epsilon) {
        double _t1 = this.rY * this.rZ;
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * ((this.rX) * (this.rZ) + (this.rY * this.rW));
        double _t9 = 2.0 * ((this.rX) * (this.rW) - (_t1));
        double _t10 = ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
        double _t12 = ((_t10) * (_t10) + (_t9 * _t9));
        if (_t12 < epsilon * ((_t8) * (_t8) + (_t12))) {
            return new Double3(Math.atan2(2.0 * ((this.rX) * (this.rW) + (_t1)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t3))))), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, _t10), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * ((this.rZ) * (this.rW) - (this.rX * this.rY)), ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t3))))));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesXZY() {
        if (!Math.useFma()) return getEulerAnglesXZY_mulAdd();
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rY * this.rZ;
        double _t7 = 2.0 * java.lang.Math.fma(this.rX, this.rW, _t1);
        double _t8 = 2.0 * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < java.lang.Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rW, -_t1), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), 0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(_t7, _t9), Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t0), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        }
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd() {
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rY * this.rZ;
        double _t7 = 2.0 * ((this.rX) * (this.rW) + (_t1));
        double _t8 = 2.0 * ((this.rZ) * (this.rW) - (this.rX * this.rY));
        double _t9 = ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < ((_t8) * (_t8) + (_t11)) * 1.0E-15) {
            return new Double3(Math.atan2(2.0 * ((this.rX) * (this.rW) - (_t1)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))))), 0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(_t7, _t9), Math.atan2(2.0 * ((this.rX) * (this.rZ) + (this.rY * this.rW)), ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t0))))), Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must not be
     * negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-15}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesXZY(double epsilon) {
        if (!Math.useFma()) return getEulerAnglesXZY_mulAdd(epsilon);
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rY * this.rZ;
        double _t7 = 2.0 * java.lang.Math.fma(this.rX, this.rW, _t1);
        double _t8 = 2.0 * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < epsilon * java.lang.Math.fma(_t8, _t8, _t11)) {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rW, -_t1), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), 0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(_t7, _t9), Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t0), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        }
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd(double epsilon) {
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rY * this.rZ;
        double _t7 = 2.0 * ((this.rX) * (this.rW) + (_t1));
        double _t8 = 2.0 * ((this.rZ) * (this.rW) - (this.rX * this.rY));
        double _t9 = ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < epsilon * ((_t8) * (_t8) + (_t11))) {
            return new Double3(Math.atan2(2.0 * ((this.rX) * (this.rW) - (_t1)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))))), 0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(_t7, _t9), Math.atan2(2.0 * ((this.rX) * (this.rZ) + (this.rY * this.rW)), ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t0))))), Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesYXZ() {
        if (!Math.useFma()) return getEulerAnglesYXZ_mulAdd();
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, this.rW, this.rY * -this.rZ);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        double _t12 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < java.lang.Math.fma(_t9, _t9, _t12) * 1.0E-15) {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * java.lang.Math.fma(this.rY, this.rW, this.rX * -this.rZ), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t3), 1.0)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(_t8, _t10), Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t3), 1.0)));
        }
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd() {
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * ((this.rX) * (this.rZ) + (this.rY * this.rW));
        double _t9 = 2.0 * ((this.rX) * (this.rW) - (this.rY * this.rZ));
        double _t10 = ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
        double _t12 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t12 < ((_t9) * (_t9) + (_t12)) * 1.0E-15) {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * ((this.rY) * (this.rW) - (this.rX * this.rZ)), ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t3))))), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(_t8, _t10), Math.atan2(2.0 * ((this.rX) * (this.rY) + (this.rZ * this.rW)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t3))))));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must not be
     * negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-15}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesYXZ(double epsilon) {
        if (!Math.useFma()) return getEulerAnglesYXZ_mulAdd(epsilon);
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, this.rW, this.rY * -this.rZ);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        double _t12 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < epsilon * java.lang.Math.fma(_t9, _t9, _t12)) {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * java.lang.Math.fma(this.rY, this.rW, this.rX * -this.rZ), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t3), 1.0)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(_t8, _t10), Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t3), 1.0)));
        }
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd(double epsilon) {
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * ((this.rX) * (this.rZ) + (this.rY * this.rW));
        double _t9 = 2.0 * ((this.rX) * (this.rW) - (this.rY * this.rZ));
        double _t10 = ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
        double _t12 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t12 < epsilon * ((_t9) * (_t9) + (_t12))) {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * ((this.rY) * (this.rW) - (this.rX * this.rZ)), ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t3))))), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(_t8, _t10), Math.atan2(2.0 * ((this.rX) * (this.rY) + (this.rZ * this.rW)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t3))))));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesYZX() {
        if (!Math.useFma()) return getEulerAnglesYZX_mulAdd();
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW);
        double _t8 = 2.0 * java.lang.Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < java.lang.Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rW, -(this.rY * this.rZ)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t0), 1.0)), Math.atan2(_t8, _t9), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        }
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd() {
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * ((this.rX) * (this.rY) + (this.rZ * this.rW));
        double _t8 = 2.0 * ((this.rY) * (this.rW) - (this.rX * this.rZ));
        double _t9 = ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        if (_t11 < ((_t7) * (_t7) + (_t11)) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(2.0 * ((this.rX) * (this.rZ) + (this.rY * this.rW)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))))), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(2.0 * ((this.rX) * (this.rW) - (this.rY * this.rZ)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t0))))), Math.atan2(_t8, _t9), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must not be
     * negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-15}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesYZX(double epsilon) {
        if (!Math.useFma()) return getEulerAnglesYZX_mulAdd(epsilon);
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW);
        double _t8 = 2.0 * java.lang.Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < epsilon * java.lang.Math.fma(_t7, _t7, _t11)) {
            return new Double3(0.0, Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rW, -(this.rY * this.rZ)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t0), 1.0)), Math.atan2(_t8, _t9), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        }
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd(double epsilon) {
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * ((this.rX) * (this.rY) + (this.rZ * this.rW));
        double _t8 = 2.0 * ((this.rY) * (this.rW) - (this.rX * this.rZ));
        double _t9 = ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        if (_t11 < epsilon * ((_t7) * (_t7) + (_t11))) {
            return new Double3(0.0, Math.atan2(2.0 * ((this.rX) * (this.rZ) + (this.rY * this.rW)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))))), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(2.0 * ((this.rX) * (this.rW) - (this.rY * this.rZ)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t0))))), Math.atan2(_t8, _t9), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesZXY() {
        if (!Math.useFma()) return getEulerAnglesZXY_mulAdd();
        double _t1 = this.rZ * this.rZ;
        double _t7 = 2.0 * java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ);
        double _t8 = 2.0 * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t1), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < java.lang.Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), 0.0, Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t1), 1.0)));
        } else {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * java.lang.Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t8, _t9));
        }
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd() {
        double _t1 = this.rZ * this.rZ;
        double _t7 = 2.0 * ((this.rX) * (this.rW) + (this.rY * this.rZ));
        double _t8 = 2.0 * ((this.rZ) * (this.rW) - (this.rX * this.rY));
        double _t9 = ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t1))));
        double _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        if (_t11 < ((_t7) * (_t7) + (_t11)) * 1.0E-15) {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), 0.0, Math.atan2(2.0 * ((this.rX) * (this.rY) + (this.rZ * this.rW)), ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t1))))));
        } else {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * ((this.rY) * (this.rW) - (this.rX * this.rZ)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))))), Math.atan2(_t8, _t9));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must not be
     * negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-15}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesZXY(double epsilon) {
        if (!Math.useFma()) return getEulerAnglesZXY_mulAdd(epsilon);
        double _t1 = this.rZ * this.rZ;
        double _t7 = 2.0 * java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ);
        double _t8 = 2.0 * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t1), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < epsilon * java.lang.Math.fma(_t7, _t7, _t11)) {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), 0.0, Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t1), 1.0)));
        } else {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * java.lang.Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t8, _t9));
        }
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd(double epsilon) {
        double _t1 = this.rZ * this.rZ;
        double _t7 = 2.0 * ((this.rX) * (this.rW) + (this.rY * this.rZ));
        double _t8 = 2.0 * ((this.rZ) * (this.rW) - (this.rX * this.rY));
        double _t9 = ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t1))));
        double _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        if (_t11 < epsilon * ((_t7) * (_t7) + (_t11))) {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), 0.0, Math.atan2(2.0 * ((this.rX) * (this.rY) + (this.rZ * this.rW)), ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t1))))));
        } else {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * ((this.rY) * (this.rW) - (this.rX * this.rZ)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))))), Math.atan2(_t8, _t9));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesZYX() {
        if (!Math.useFma()) return getEulerAnglesZYX_mulAdd();
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW);
        double _t8 = 2.0 * java.lang.Math.fma(this.rY, this.rW, -this.rX * this.rZ);
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < java.lang.Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * java.lang.Math.fma(this.rZ, this.rW, -this.rX * this.rY), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t0), 1.0)));
        } else {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(_t7, _t9));
        }
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd() {
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * ((this.rX) * (this.rY) + (this.rZ * this.rW));
        double _t8 = 2.0 * ((this.rY) * (this.rW) - (this.rX * this.rZ));
        double _t9 = ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < ((_t8) * (_t8) + (_t11)) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * ((this.rZ) * (this.rW) - (this.rX * this.rY)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t0))))));
        } else {
            return new Double3(Math.atan2(2.0 * ((this.rX) * (this.rW) + (this.rY * this.rZ)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))))), Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(_t7, _t9));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code epsilon} must not be
     * negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-15}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesZYX(double epsilon) {
        if (!Math.useFma()) return getEulerAnglesZYX_mulAdd(epsilon);
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW);
        double _t8 = 2.0 * java.lang.Math.fma(this.rY, this.rW, -this.rX * this.rZ);
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < epsilon * java.lang.Math.fma(_t8, _t8, _t11)) {
            return new Double3(0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * java.lang.Math.fma(this.rZ, this.rW, -this.rX * this.rY), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t0), 1.0)));
        } else {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(_t7, _t9));
        }
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd(double epsilon) {
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * ((this.rX) * (this.rY) + (this.rZ * this.rW));
        double _t8 = 2.0 * ((this.rY) * (this.rW) - (this.rX * this.rZ));
        double _t9 = ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < epsilon * ((_t8) * (_t8) + (_t11))) {
            return new Double3(0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * ((this.rZ) * (this.rW) - (this.rX * this.rY)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t0))))));
        } else {
            return new Double3(Math.atan2(2.0 * ((this.rX) * (this.rW) + (this.rY * this.rZ)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))))), Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(_t7, _t9));
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
    public DoubleQuat getRotation() {
        return new DoubleQuat(this.rX, this.rY, this.rZ, this.rW);
    }

    /**
     * Get the translation of this dual quaternion, i.e. {@code 2 * dual * conj(real)} (the actual
     * translation only when this dual quaternion has unit length), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getTranslation() {
        if (Math.useFma()) {
            return new Double3(2.0 * (java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW)), 2.0 * (java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW)), 2.0 * (java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, this.rZ * -this.dW)));
        } else {
            return new Double3(2.0 * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW))), 2.0 * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW))), 2.0 * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW))));
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
    public DoubleDualQuat inverseUnit() {
        return new DoubleDualQuat(-this.rX, -this.rY, -this.rZ, this.rW, -this.dX, -this.dY, -this.dZ, this.dW);
    }

    /**
     * Invert this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: the real part of this dual quaternion must be non-zero.
     *
     * @return the resulting dual quaternion
     */
    public DoubleDualQuat invert() {
        if (!Math.useFma()) return invert_mulAdd();
        double _t8 = java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY) + java.lang.Math.fma(this.rZ, this.rZ, this.rW * this.rW);
        double _t8_inv = 1.0 / _t8;
        double _sp0 = 2.0 * (java.lang.Math.fma(this.rX, this.dX, this.rY * this.dY) + java.lang.Math.fma(this.rZ, this.dZ, this.rW * this.dW)) / (_t8 * _t8);
        return new DoubleDualQuat(-(this.rX * _t8_inv), -(this.rY * _t8_inv), -(this.rZ * _t8_inv), this.rW * _t8_inv, java.lang.Math.fma(this.rX, _sp0, -(this.dX * _t8_inv)), java.lang.Math.fma(this.rY, _sp0, -(this.dY * _t8_inv)), java.lang.Math.fma(this.rZ, _sp0, -(this.dZ * _t8_inv)), java.lang.Math.fma(this.dW, _t8_inv, -(this.rW * _sp0)));
    }

    /** {@code invert} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat invert_mulAdd() {
        double _t8 = ((this.rX) * (this.rX) + (this.rY * this.rY)) + ((this.rZ) * (this.rZ) + (this.rW * this.rW));
        double _t8_inv = 1.0 / _t8;
        double _sp0 = 2.0 * (((this.rX) * (this.dX) + (this.rY * this.dY)) + ((this.rZ) * (this.dZ) + (this.rW * this.dW))) / (_t8 * _t8);
        return new DoubleDualQuat(-(this.rX * _t8_inv), -(this.rY * _t8_inv), -(this.rZ * _t8_inv), this.rW * _t8_inv, ((this.rX) * (_sp0) - (this.dX * _t8_inv)), ((this.rY) * (_sp0) - (this.dY * _t8_inv)), ((this.rZ) * (_sp0) - (this.dZ * _t8_inv)), ((this.dW) * (_t8_inv) - (this.rW * _sp0)));
    }

    /**
     * Compute the length of this dual quaternion's real (rotation) part.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the length of this dual quaternion's real (rotation) part
     */
    public double length() {
        if (Math.useFma()) {
            return java.lang.Math.sqrt(java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY) + java.lang.Math.fma(this.rZ, this.rZ, this.rW * this.rW));
        } else {
            return java.lang.Math.sqrt(((this.rX) * (this.rX) + (this.rY * this.rY)) + ((this.rZ) * (this.rZ) + (this.rW * this.rW)));
        }
    }

    /**
     * Compute the squared length of this dual quaternion's real (rotation) part.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this dual quaternion's real (rotation) part
     */
    public double lengthSquared() {
        if (Math.useFma()) {
            return java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY) + java.lang.Math.fma(this.rZ, this.rZ, this.rW * this.rW);
        } else {
            return ((this.rX) * (this.rX) + (this.rY * this.rY)) + ((this.rZ) * (this.rZ) + (this.rW * this.rW));
        }
    }

    /** Private tail of {@code log}; reached only through it. */
    private DoubleDualQuat log_s0_tail_fma(double _t19, double _t10, double _t8, double _t11, double _t18, double _t9, double _t21, double _t22, double _t12, double _t14, double _t15) {
        double _t23 = _t19 * _t10;
        double _t24 = _t19 * _t8;
        double _t25 = _t19 * (this.rW < 0.0 ? -this.dW : this.dW);
        double _t26 = _t25 * _t11;
        if (_t18 < 1.0E-28) {
            return new DoubleDualQuat(_t9, _t10, _t8, 0.0, _t12, _t14, _t15, 0.0);
        } else {
            return new DoubleDualQuat(_t21 * _t22, _t23 * _t22, _t24 * _t22, 0.0, java.lang.Math.fma(java.lang.Math.fma(_t26, _t21, _t12) * _t19, _t22, _t21 * -_t25), java.lang.Math.fma(java.lang.Math.fma(_t26, _t23, _t14) * _t19, _t22, _t23 * -_t25), java.lang.Math.fma(java.lang.Math.fma(_t26, _t24, _t15) * _t19, _t22, _t24 * -_t25), 0.0);
        }
    }

    /** Private tail of {@code log}; reached only through it. */
    private DoubleDualQuat log_s0_tail_mulAdd(double _t19, double _t10, double _t8, double _t11, double _t18, double _t9, double _t21, double _t22, double _t12, double _t14, double _t15) {
        double _t23 = _t19 * _t10;
        double _t24 = _t19 * _t8;
        double _t25 = _t19 * (this.rW < 0.0 ? -this.dW : this.dW);
        double _t26 = _t25 * _t11;
        if (_t18 < 1.0E-28) {
            return new DoubleDualQuat(_t9, _t10, _t8, 0.0, _t12, _t14, _t15, 0.0);
        } else {
            return new DoubleDualQuat(_t21 * _t22, _t23 * _t22, _t24 * _t22, 0.0, ((((_t26) * (_t21) + (_t12)) * _t19) * (_t22) - (_t21 * _t25)), ((((_t26) * (_t23) + (_t14)) * _t19) * (_t22) - (_t23 * _t25)), ((((_t26) * (_t24) + (_t15)) * _t19) * (_t22) - (_t24 * _t25)), 0.0);
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
    public DoubleDualQuat log() {
        if (!Math.useFma()) return log_mulAdd();
        double _t8, _t9, _t10, _t11, _t12, _t14, _t15;
        if (this.rW < 0.0) {
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
        double _t18 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t19 = (1.0 / java.lang.Math.sqrt(_t18));
        return log_s0_tail_fma(_t19, _t10, _t8, _t11, _t18, _t9, _t19 * _t9, Math.atan2(java.lang.Math.sqrt(_t18), _t11), _t12, _t14, _t15);
    }

    /** {@code log} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat log_mulAdd() {
        double _t8, _t9, _t10, _t11, _t12, _t14, _t15;
        if (this.rW < 0.0) {
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
        double _t18 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t19 = (1.0 / java.lang.Math.sqrt(_t18));
        return log_s0_tail_mulAdd(_t19, _t10, _t8, _t11, _t18, _t9, _t19 * _t9, Math.atan2(java.lang.Math.sqrt(_t18), _t11), _t12, _t14, _t15);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static DoubleDualQuat makeFromMatrix_sa000ec_tail_fma(double _t14, double _sp0, double _t4, Double4x4 m, double _t5, double _t16, double _sp1, double _t6, double _sp2, double _t7, double _t8, double _sp3, double _t17, double _t9, double _t10, double _t18, double _t15, double _t0) {
        double _t63, _t64, _t65;
        if (_t14 > 0.0) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
        } else {
            if (m.m00() > _t5) {
                _t63 = 0.5 * java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
            } else {
                if (m.m11() > m.m22()) {
                    _t63 = _sp1 * _t6;
                    _t64 = 0.5 * java.lang.Math.sqrt(_t17);
                    _t65 = _sp1 * _t9;
                } else {
                    _t63 = _sp2 * _t7;
                    _t64 = _sp2 * _t9;
                    _t65 = 0.5 * java.lang.Math.sqrt(_t18);
                }
            }
        }
        return makeFromMatrix_sa000ec_tail2_fma(_t14, _t15, m, _t5, _sp3, _t4, _sp1, _t8, _sp2, _t10, _t63, _t64, _t65, _t0);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static DoubleDualQuat makeFromMatrix_sa000ec_tail_mulAdd(double _t14, double _sp0, double _t4, Double4x4 m, double _t5, double _t16, double _sp1, double _t6, double _sp2, double _t7, double _t8, double _sp3, double _t17, double _t9, double _t10, double _t18, double _t15, double _t0) {
        double _t63, _t64, _t65;
        if (_t14 > 0.0) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
        } else {
            if (m.m00() > _t5) {
                _t63 = 0.5 * java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
            } else {
                if (m.m11() > m.m22()) {
                    _t63 = _sp1 * _t6;
                    _t64 = 0.5 * java.lang.Math.sqrt(_t17);
                    _t65 = _sp1 * _t9;
                } else {
                    _t63 = _sp2 * _t7;
                    _t64 = _sp2 * _t9;
                    _t65 = 0.5 * java.lang.Math.sqrt(_t18);
                }
            }
        }
        return makeFromMatrix_sa000ec_tail2_mulAdd(_t14, _t15, m, _t5, _sp3, _t4, _sp1, _t8, _sp2, _t10, _t63, _t64, _t65, _t0);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static DoubleDualQuat makeFromMatrix_sa000ec_tail2_fma(double _t14, double _t15, Double4x4 m, double _t5, double _sp3, double _t4, double _sp1, double _t8, double _sp2, double _t10, double _t63, double _t64, double _t65, double _t0) {
        double _t66 = _t14 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t15) : m.m00() > _t5 ? _sp3 * _t4 : m.m11() > m.m22() ? _sp1 * _t8 : _sp2 * _t10;
        double _m_m03 = m.m03();
        double _m_m13 = m.m13();
        double _sfx4 = 0.5 * java.lang.Math.fma(_t0, _t64, java.lang.Math.fma(_m_m03, _t66, _m_m13 * _t65));
        double _m_m23 = m.m23();
        double _sfx5 = 0.5 * java.lang.Math.fma(_m_m23, _t63, java.lang.Math.fma(_m_m13, _t66, -_m_m03 * _t65));
        double _sfx6 = 0.5 * java.lang.Math.fma(_m_m23, _t66, java.lang.Math.fma(_m_m03, _t64, -_m_m13 * _t63));
        double _sfx7 = 0.5 * java.lang.Math.fma(_t0, _t65, java.lang.Math.fma(-_m_m13, _t64, -_m_m03 * _t63));
        return new DoubleDualQuat(_t63, _t64, _t65, _t66, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static DoubleDualQuat makeFromMatrix_sa000ec_tail2_mulAdd(double _t14, double _t15, Double4x4 m, double _t5, double _sp3, double _t4, double _sp1, double _t8, double _sp2, double _t10, double _t63, double _t64, double _t65, double _t0) {
        double _t66 = _t14 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t15) : m.m00() > _t5 ? _sp3 * _t4 : m.m11() > m.m22() ? _sp1 * _t8 : _sp2 * _t10;
        double _m_m03 = m.m03();
        double _m_m13 = m.m13();
        double _sfx4 = 0.5 * ((_t0) * (_t64) + (((_m_m03) * (_t66) + (_m_m13 * _t65))));
        double _m_m23 = m.m23();
        double _sfx5 = 0.5 * ((_m_m23) * (_t63) + (((_m_m13) * (_t66) - (_m_m03 * _t65))));
        double _sfx6 = 0.5 * ((_m_m23) * (_t66) + (((_m_m03) * (_t64) - (_m_m13 * _t63))));
        double _sfx7 = 0.5 * ((_t0) * (_t65) + (((-_m_m13) * (_t64) - (_m_m03 * _t63))));
        return new DoubleDualQuat(_t63, _t64, _t65, _t66, _sfx4, _sfx5, _sfx6, _sfx7);
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
    public static DoubleDualQuat makeFromMatrix(Double4x4 m) {
        if (!Math.useFma()) return makeFromMatrix_mulAdd(m);
        double _m_m00 = m.m00();
        double _t2 = 1.0 - _m_m00;
        double _m_m22 = m.m22();
        double _m_m11 = m.m11();
        double _t14 = _m_m22 + (_m_m00 + _m_m11);
        double _t15 = 1.0 + _t14;
        double _t16 = _m_m00 + (1.0 - _m_m11 - _m_m22);
        double _t17 = _m_m11 + (_t2 - _m_m22);
        double _t18 = _m_m22 + (_t2 - _m_m11);
        double _m_m21 = m.m21();
        double _m_m12 = m.m12();
        double _m_m01 = m.m01();
        double _m_m10 = m.m10();
        double _m_m02 = m.m02();
        double _m_m20 = m.m20();
        return makeFromMatrix_sa000ec_tail_fma(_t14, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), _m_m21 - _m_m12, m, Math.max(_m_m11, _m_m22), _t16, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), _m_m01 + _m_m10, 0.5 * (1.0 / java.lang.Math.sqrt(_t18)), _m_m02 + _m_m20, _m_m02 - _m_m20, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), _t17, _m_m12 + _m_m21, _m_m10 - _m_m01, _t18, _t15, -m.m23());
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleDualQuat makeFromMatrix_mulAdd(Double4x4 m) {
        double _m_m00 = m.m00();
        double _t2 = 1.0 - _m_m00;
        double _m_m22 = m.m22();
        double _m_m11 = m.m11();
        double _t14 = _m_m22 + (_m_m00 + _m_m11);
        double _t15 = 1.0 + _t14;
        double _t16 = _m_m00 + (1.0 - _m_m11 - _m_m22);
        double _t17 = _m_m11 + (_t2 - _m_m22);
        double _t18 = _m_m22 + (_t2 - _m_m11);
        double _m_m21 = m.m21();
        double _m_m12 = m.m12();
        double _m_m01 = m.m01();
        double _m_m10 = m.m10();
        double _m_m02 = m.m02();
        double _m_m20 = m.m20();
        return makeFromMatrix_sa000ec_tail_mulAdd(_t14, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), _m_m21 - _m_m12, m, Math.max(_m_m11, _m_m22), _t16, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), _m_m01 + _m_m10, 0.5 * (1.0 / java.lang.Math.sqrt(_t18)), _m_m02 + _m_m20, _m_m02 - _m_m20, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), _t17, _m_m12 + _m_m21, _m_m10 - _m_m01, _t18, _t15, -m.m23());
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static DoubleDualQuat makeFromMatrix_s91e96b_tail_fma(double _t14, double _sp0, double _t4, Double3x4 m, double _t5, double _t16, double _sp1, double _t6, double _sp2, double _t7, double _t8, double _sp3, double _t17, double _t9, double _t10, double _t18, double _t15, double _t0) {
        double _t63, _t64, _t65;
        if (_t14 > 0.0) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
        } else {
            if (m.m00() > _t5) {
                _t63 = 0.5 * java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
            } else {
                if (m.m11() > m.m22()) {
                    _t63 = _sp1 * _t6;
                    _t64 = 0.5 * java.lang.Math.sqrt(_t17);
                    _t65 = _sp1 * _t9;
                } else {
                    _t63 = _sp2 * _t7;
                    _t64 = _sp2 * _t9;
                    _t65 = 0.5 * java.lang.Math.sqrt(_t18);
                }
            }
        }
        return makeFromMatrix_s91e96b_tail2_fma(_t14, _t15, m, _t5, _sp3, _t4, _sp1, _t8, _sp2, _t10, _t63, _t64, _t65, _t0);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static DoubleDualQuat makeFromMatrix_s91e96b_tail_mulAdd(double _t14, double _sp0, double _t4, Double3x4 m, double _t5, double _t16, double _sp1, double _t6, double _sp2, double _t7, double _t8, double _sp3, double _t17, double _t9, double _t10, double _t18, double _t15, double _t0) {
        double _t63, _t64, _t65;
        if (_t14 > 0.0) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
        } else {
            if (m.m00() > _t5) {
                _t63 = 0.5 * java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
            } else {
                if (m.m11() > m.m22()) {
                    _t63 = _sp1 * _t6;
                    _t64 = 0.5 * java.lang.Math.sqrt(_t17);
                    _t65 = _sp1 * _t9;
                } else {
                    _t63 = _sp2 * _t7;
                    _t64 = _sp2 * _t9;
                    _t65 = 0.5 * java.lang.Math.sqrt(_t18);
                }
            }
        }
        return makeFromMatrix_s91e96b_tail2_mulAdd(_t14, _t15, m, _t5, _sp3, _t4, _sp1, _t8, _sp2, _t10, _t63, _t64, _t65, _t0);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static DoubleDualQuat makeFromMatrix_s91e96b_tail2_fma(double _t14, double _t15, Double3x4 m, double _t5, double _sp3, double _t4, double _sp1, double _t8, double _sp2, double _t10, double _t63, double _t64, double _t65, double _t0) {
        double _t66 = _t14 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t15) : m.m00() > _t5 ? _sp3 * _t4 : m.m11() > m.m22() ? _sp1 * _t8 : _sp2 * _t10;
        double _m_m03 = m.m03();
        double _m_m13 = m.m13();
        double _sfx4 = 0.5 * java.lang.Math.fma(_t0, _t64, java.lang.Math.fma(_m_m03, _t66, _m_m13 * _t65));
        double _m_m23 = m.m23();
        double _sfx5 = 0.5 * java.lang.Math.fma(_m_m23, _t63, java.lang.Math.fma(_m_m13, _t66, -_m_m03 * _t65));
        double _sfx6 = 0.5 * java.lang.Math.fma(_m_m23, _t66, java.lang.Math.fma(_m_m03, _t64, -_m_m13 * _t63));
        double _sfx7 = 0.5 * java.lang.Math.fma(_t0, _t65, java.lang.Math.fma(-_m_m13, _t64, -_m_m03 * _t63));
        return new DoubleDualQuat(_t63, _t64, _t65, _t66, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static DoubleDualQuat makeFromMatrix_s91e96b_tail2_mulAdd(double _t14, double _t15, Double3x4 m, double _t5, double _sp3, double _t4, double _sp1, double _t8, double _sp2, double _t10, double _t63, double _t64, double _t65, double _t0) {
        double _t66 = _t14 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t15) : m.m00() > _t5 ? _sp3 * _t4 : m.m11() > m.m22() ? _sp1 * _t8 : _sp2 * _t10;
        double _m_m03 = m.m03();
        double _m_m13 = m.m13();
        double _sfx4 = 0.5 * ((_t0) * (_t64) + (((_m_m03) * (_t66) + (_m_m13 * _t65))));
        double _m_m23 = m.m23();
        double _sfx5 = 0.5 * ((_m_m23) * (_t63) + (((_m_m13) * (_t66) - (_m_m03 * _t65))));
        double _sfx6 = 0.5 * ((_m_m23) * (_t66) + (((_m_m03) * (_t64) - (_m_m13 * _t63))));
        double _sfx7 = 0.5 * ((_t0) * (_t65) + (((-_m_m13) * (_t64) - (_m_m03 * _t63))));
        return new DoubleDualQuat(_t63, _t64, _t65, _t66, _sfx4, _sfx5, _sfx6, _sfx7);
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
    public static DoubleDualQuat makeFromMatrix(Double3x4 m) {
        if (!Math.useFma()) return makeFromMatrix_mulAdd(m);
        double _m_m00 = m.m00();
        double _t2 = 1.0 - _m_m00;
        double _m_m22 = m.m22();
        double _m_m11 = m.m11();
        double _t14 = _m_m22 + (_m_m00 + _m_m11);
        double _t15 = 1.0 + _t14;
        double _t16 = _m_m00 + (1.0 - _m_m11 - _m_m22);
        double _t17 = _m_m11 + (_t2 - _m_m22);
        double _t18 = _m_m22 + (_t2 - _m_m11);
        double _m_m21 = m.m21();
        double _m_m12 = m.m12();
        double _m_m01 = m.m01();
        double _m_m10 = m.m10();
        double _m_m02 = m.m02();
        double _m_m20 = m.m20();
        return makeFromMatrix_s91e96b_tail_fma(_t14, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), _m_m21 - _m_m12, m, Math.max(_m_m11, _m_m22), _t16, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), _m_m01 + _m_m10, 0.5 * (1.0 / java.lang.Math.sqrt(_t18)), _m_m02 + _m_m20, _m_m02 - _m_m20, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), _t17, _m_m12 + _m_m21, _m_m10 - _m_m01, _t18, _t15, -m.m23());
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleDualQuat makeFromMatrix_mulAdd(Double3x4 m) {
        double _m_m00 = m.m00();
        double _t2 = 1.0 - _m_m00;
        double _m_m22 = m.m22();
        double _m_m11 = m.m11();
        double _t14 = _m_m22 + (_m_m00 + _m_m11);
        double _t15 = 1.0 + _t14;
        double _t16 = _m_m00 + (1.0 - _m_m11 - _m_m22);
        double _t17 = _m_m11 + (_t2 - _m_m22);
        double _t18 = _m_m22 + (_t2 - _m_m11);
        double _m_m21 = m.m21();
        double _m_m12 = m.m12();
        double _m_m01 = m.m01();
        double _m_m10 = m.m10();
        double _m_m02 = m.m02();
        double _m_m20 = m.m20();
        return makeFromMatrix_s91e96b_tail_mulAdd(_t14, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), _m_m21 - _m_m12, m, Math.max(_m_m11, _m_m22), _t16, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), _m_m01 + _m_m10, 0.5 * (1.0 / java.lang.Math.sqrt(_t18)), _m_m02 + _m_m20, _m_m02 - _m_m20, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), _t17, _m_m12 + _m_m21, _m_m10 - _m_m01, _t18, _t15, -m.m23());
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static DoubleDualQuat makeFromMatrix_s91e5aa_tail(double _t13, double _sp0, double _t3, Double3x3 m, double _t4, double _t15, double _sp1, double _t5, double _sp2, double _t6, double _t7, double _sp3, double _t16, double _t8, double _t9, double _t17, double _t14) {
        double _sfx0, _sfx1, _sfx2;
        if (_t13 > 0.0) {
            _sfx0 = _sp0 * _t3;
            _sfx1 = _sp0 * _t7;
            _sfx2 = _sp0 * _t9;
        } else {
            if (m.m00() > _t4) {
                _sfx0 = 0.5 * java.lang.Math.sqrt(_t15);
                _sfx1 = _sp3 * _t5;
                _sfx2 = _sp3 * _t6;
            } else {
                if (m.m11() > m.m22()) {
                    _sfx0 = _sp1 * _t5;
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t16);
                    _sfx2 = _sp1 * _t8;
                } else {
                    _sfx0 = _sp2 * _t6;
                    _sfx1 = _sp2 * _t8;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t17);
                }
            }
        }
        return makeFromMatrix_s91e5aa_tail2(_t13, _t14, m, _t4, _sp3, _t3, _sp1, _t7, _sp2, _t9, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static DoubleDualQuat makeFromMatrix_s91e5aa_tail2(double _t13, double _t14, Double3x3 m, double _t4, double _sp3, double _t3, double _sp1, double _t7, double _sp2, double _t9, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3 = _t13 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t14) : m.m00() > _t4 ? _sp3 * _t3 : m.m11() > m.m22() ? _sp1 * _t7 : _sp2 * _t9;
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0, 0.0, 0.0, 0.0);
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
    public static DoubleDualQuat makeFromMatrix(Double3x3 m) {
        double _m_m00 = m.m00();
        double _t1 = 1.0 - _m_m00;
        double _m_m22 = m.m22();
        double _m_m11 = m.m11();
        double _t13 = _m_m22 + (_m_m00 + _m_m11);
        double _t14 = 1.0 + _t13;
        double _t15 = _m_m00 + (1.0 - _m_m11 - _m_m22);
        double _t16 = _m_m11 + (_t1 - _m_m22);
        double _t17 = _m_m22 + (_t1 - _m_m11);
        double _m_m21 = m.m21();
        double _m_m12 = m.m12();
        double _m_m01 = m.m01();
        double _m_m10 = m.m10();
        double _m_m02 = m.m02();
        double _m_m20 = m.m20();
        return makeFromMatrix_s91e5aa_tail(_t13, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), _m_m21 - _m_m12, m, Math.max(_m_m11, _m_m22), _t15, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), _m_m01 + _m_m10, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), _m_m02 + _m_m20, _m_m02 - _m_m20, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), _t16, _m_m12 + _m_m21, _m_m10 - _m_m01, _t17, _t14);
    }

    /**
     * Normalize this dual quaternion so that its real (rotation) part has unit length (a zero real
     * part yields the zero dual quaternion), returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting dual quaternion
     */
    public DoubleDualQuat normalize() {
        if (Math.useFma()) {
            double _t4 = java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY) + java.lang.Math.fma(this.rZ, this.rZ, this.rW * this.rW);
            double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
            if (_t4 != 0.0) {
                return new DoubleDualQuat(this.rX * _t5, this.rY * _t5, this.rZ * _t5, this.rW * _t5, this.dX * _t5, this.dY * _t5, this.dZ * _t5, this.dW * _t5);
            } else {
                return DoubleDualQuat.ZERO;
            }
        } else {
            double _t4 = ((this.rX) * (this.rX) + (this.rY * this.rY)) + ((this.rZ) * (this.rZ) + (this.rW * this.rW));
            double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
            if (_t4 != 0.0) {
                return new DoubleDualQuat(this.rX * _t5, this.rY * _t5, this.rZ * _t5, this.rW * _t5, this.dX * _t5, this.dY * _t5, this.dZ * _t5, this.dW * _t5);
            } else {
                return DoubleDualQuat.ZERO;
            }
        }
    }

    /** Private tail of {@code pow}; reached only through it. */
    private DoubleDualQuat pow_s5107cfe5_tail_fma(double _t19, double _t10, double _t8, double _t13, double t, double _t18, double _t11, double _t9, double _t21, double _t12, double _t14, double _t15) {
        double _t25 = _t19 * _t13;
        double _t26 = t * Math.atan2(java.lang.Math.sqrt(_t18), _t11);
        double _t27 = t * _t25;
        double _t28 = Math.sin(_t26);
        double _t30 = Math.cosFromSin(_t28, _t26);
        return pow_s5107cfe5_tail2_fma(_t18, t, _t9, _t21, _t28, _t10, _t19 * _t10, _t8, _t19 * _t8, _t30, _t12, _t25 * _t11, _t19, _t27 * _t30, _t14, _t15, _t13, _t27);
    }

    /** Private tail of {@code pow}; reached only through it. */
    private DoubleDualQuat pow_s5107cfe5_tail_mulAdd(double _t19, double _t10, double _t8, double _t13, double t, double _t18, double _t11, double _t9, double _t21, double _t12, double _t14, double _t15) {
        double _t25 = _t19 * _t13;
        double _t26 = t * Math.atan2(java.lang.Math.sqrt(_t18), _t11);
        double _t27 = t * _t25;
        double _t28 = Math.sin(_t26);
        double _t30 = Math.cosFromSin(_t28, _t26);
        return pow_s5107cfe5_tail2_mulAdd(_t18, t, _t9, _t21, _t28, _t10, _t19 * _t10, _t8, _t19 * _t8, _t30, _t12, _t25 * _t11, _t19, _t27 * _t30, _t14, _t15, _t13, _t27);
    }

    /** Private tail of {@code pow}; reached only through it. */
    private DoubleDualQuat pow_s5107cfe5_tail2_fma(double _t18, double t, double _t9, double _t21, double _t28, double _t10, double _t23, double _t8, double _t24, double _t30, double _t12, double _t29, double _t19, double _t31, double _t14, double _t15, double _t13, double _t27) {
        double _sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7;
        if (_t18 < 1.0E-28) {
            _sfx0 = t * _t9;
            _sfx1 = t * _t10;
            _sfx2 = t * _t8;
            _sfx3 = 1.0;
            _sfx4 = t * _t12;
            _sfx5 = t * _t14;
            _sfx6 = t * _t15;
            _sfx7 = t * t * _t13;
        } else {
            _sfx0 = _t21 * _t28;
            _sfx1 = _t23 * _t28;
            _sfx2 = _t24 * _t28;
            _sfx3 = _t30;
            _sfx4 = java.lang.Math.fma(java.lang.Math.fma(_t29, _t21, _t12) * _t19, _t28, -(_t31 * _t21));
            _sfx5 = java.lang.Math.fma(java.lang.Math.fma(_t29, _t23, _t14) * _t19, _t28, -(_t31 * _t23));
            _sfx6 = java.lang.Math.fma(java.lang.Math.fma(_t29, _t24, _t15) * _t19, _t28, -(_t31 * _t24));
            _sfx7 = _t27 * _t28;
        }
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code pow}; reached only through it. */
    private DoubleDualQuat pow_s5107cfe5_tail2_mulAdd(double _t18, double t, double _t9, double _t21, double _t28, double _t10, double _t23, double _t8, double _t24, double _t30, double _t12, double _t29, double _t19, double _t31, double _t14, double _t15, double _t13, double _t27) {
        double _sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7;
        if (_t18 < 1.0E-28) {
            _sfx0 = t * _t9;
            _sfx1 = t * _t10;
            _sfx2 = t * _t8;
            _sfx3 = 1.0;
            _sfx4 = t * _t12;
            _sfx5 = t * _t14;
            _sfx6 = t * _t15;
            _sfx7 = t * t * _t13;
        } else {
            _sfx0 = _t21 * _t28;
            _sfx1 = _t23 * _t28;
            _sfx2 = _t24 * _t28;
            _sfx3 = _t30;
            _sfx4 = ((((_t29) * (_t21) + (_t12)) * _t19) * (_t28) - (_t31 * _t21));
            _sfx5 = ((((_t29) * (_t23) + (_t14)) * _t19) * (_t28) - (_t31 * _t23));
            _sfx6 = ((((_t29) * (_t24) + (_t15)) * _t19) * (_t28) - (_t31 * _t24));
            _sfx7 = _t27 * _t28;
        }
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
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
    public DoubleDualQuat pow(double t) {
        if (!Math.useFma()) return pow_mulAdd(t);
        double _t8, _t9, _t10, _t11, _t12, _t13, _t14, _t15;
        if (this.rW < 0.0) {
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
        double _t18 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t19 = (1.0 / java.lang.Math.sqrt(_t18));
        return pow_s5107cfe5_tail_fma(_t19, _t10, _t8, _t13, t, _t18, _t11, _t9, _t19 * _t9, _t12, _t14, _t15);
    }

    /** {@code pow} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat pow_mulAdd(double t) {
        double _t8, _t9, _t10, _t11, _t12, _t13, _t14, _t15;
        if (this.rW < 0.0) {
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
        double _t18 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t19 = (1.0 / java.lang.Math.sqrt(_t18));
        return pow_s5107cfe5_tail_mulAdd(_t19, _t10, _t8, _t13, t, _t18, _t11, _t9, _t19 * _t9, _t12, _t14, _t15);
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
    public DoubleDualQuat setDual(DoubleQuat dual) {
        double dualX = dual.x();
        double dualY = dual.y();
        double dualZ = dual.z();
        double dualW = dual.w();
        return new DoubleDualQuat(this.rX, this.rY, this.rZ, this.rW, dualX, dualY, dualZ, dualW);
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
    public DoubleDualQuat setDual(double dualX, double dualY, double dualZ, double dualW) {
        return new DoubleDualQuat(this.rX, this.rY, this.rZ, this.rW, dualX, dualY, dualZ, dualW);
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
    public DoubleDualQuat setReal(DoubleQuat real) {
        double realX = real.x();
        double realY = real.y();
        double realZ = real.z();
        double realW = real.w();
        return new DoubleDualQuat(realX, realY, realZ, realW, this.dX, this.dY, this.dZ, this.dW);
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
    public DoubleDualQuat setReal(double realX, double realY, double realZ, double realW) {
        return new DoubleDualQuat(realX, realY, realZ, realW, this.dX, this.dY, this.dZ, this.dW);
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
    public DoubleDualQuat setRotation(DoubleQuat rotation) {
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        if (!Math.useFma()) return setRotation_mulAdd(rotationX, rotationY, rotationZ, rotationW);
        double _t0 = -rotationY;
        double _t22 = 2.0 * (java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
        double _t23 = 2.0 * (java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW));
        double _t24 = 2.0 * (java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW));
        return new DoubleDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5 * java.lang.Math.fma(_t0, _t22, java.lang.Math.fma(rotationZ, _t23, rotationW * _t24)), 0.5 * java.lang.Math.fma(rotationX, _t22, java.lang.Math.fma(rotationW, _t23, -rotationZ * _t24)), 0.5 * java.lang.Math.fma(rotationW, _t22, java.lang.Math.fma(rotationY, _t24, -rotationX * _t23)), 0.5 * java.lang.Math.fma(-rotationZ, _t22, java.lang.Math.fma(_t0, _t23, -rotationX * _t24)));
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
    public DoubleDualQuat setRotation(double rotationX, double rotationY, double rotationZ, double rotationW) {
        if (!Math.useFma()) return setRotation_mulAdd(rotationX, rotationY, rotationZ, rotationW);
        double _t0 = -rotationY;
        double _t22 = 2.0 * (java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, -(this.rZ * this.dW)));
        double _t23 = 2.0 * (java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW));
        double _t24 = 2.0 * (java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW));
        return new DoubleDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5 * java.lang.Math.fma(_t0, _t22, java.lang.Math.fma(rotationZ, _t23, rotationW * _t24)), 0.5 * java.lang.Math.fma(rotationX, _t22, java.lang.Math.fma(rotationW, _t23, -rotationZ * _t24)), 0.5 * java.lang.Math.fma(rotationW, _t22, java.lang.Math.fma(rotationY, _t24, -rotationX * _t23)), 0.5 * java.lang.Math.fma(-rotationZ, _t22, java.lang.Math.fma(_t0, _t23, -rotationX * _t24)));
    }

    /** {@code setRotation} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat setRotation_mulAdd(double rotationX, double rotationY, double rotationZ, double rotationW) {
        double _t0 = -rotationY;
        double _t22 = 2.0 * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW)));
        double _t23 = 2.0 * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW)));
        double _t24 = 2.0 * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW)));
        return new DoubleDualQuat(rotationX, rotationY, rotationZ, rotationW, 0.5 * ((_t0) * (_t22) + (((rotationZ) * (_t23) + (rotationW * _t24)))), 0.5 * ((rotationX) * (_t22) + (((rotationW) * (_t23) - (rotationZ * _t24)))), 0.5 * ((rotationW) * (_t22) + (((rotationY) * (_t24) - (rotationX * _t23)))), 0.5 * ((((_t0) * (_t23) - (rotationX * _t24))) - (rotationZ) * (_t22)));
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
    public DoubleDualQuat setTranslation(Double3 translation) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        if (Math.useFma()) {
            double _t0 = -this.rY;
            return new DoubleDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5 * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(this.rZ, translationY, this.rW * translationX)), 0.5 * java.lang.Math.fma(this.rX, translationZ, java.lang.Math.fma(this.rW, translationY, -this.rZ * translationX)), 0.5 * java.lang.Math.fma(this.rW, translationZ, java.lang.Math.fma(this.rY, translationX, -this.rX * translationY)), 0.5 * java.lang.Math.fma(-this.rZ, translationZ, java.lang.Math.fma(_t0, translationY, -this.rX * translationX)));
        } else {
            double _t0 = -this.rY;
            return new DoubleDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5 * ((_t0) * (translationZ) + (((this.rZ) * (translationY) + (this.rW * translationX)))), 0.5 * ((this.rX) * (translationZ) + (((this.rW) * (translationY) - (this.rZ * translationX)))), 0.5 * ((this.rW) * (translationZ) + (((this.rY) * (translationX) - (this.rX * translationY)))), 0.5 * ((((_t0) * (translationY) - (this.rX * translationX))) - (this.rZ) * (translationZ)));
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
    public DoubleDualQuat setTranslation(double translationX, double translationY, double translationZ) {
        if (Math.useFma()) {
            double _t0 = -this.rY;
            return new DoubleDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5 * java.lang.Math.fma(_t0, translationZ, java.lang.Math.fma(this.rZ, translationY, this.rW * translationX)), 0.5 * java.lang.Math.fma(this.rX, translationZ, java.lang.Math.fma(this.rW, translationY, -this.rZ * translationX)), 0.5 * java.lang.Math.fma(this.rW, translationZ, java.lang.Math.fma(this.rY, translationX, -this.rX * translationY)), 0.5 * java.lang.Math.fma(-this.rZ, translationZ, java.lang.Math.fma(_t0, translationY, -this.rX * translationX)));
        } else {
            double _t0 = -this.rY;
            return new DoubleDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5 * ((_t0) * (translationZ) + (((this.rZ) * (translationY) + (this.rW * translationX)))), 0.5 * ((this.rX) * (translationZ) + (((this.rW) * (translationY) - (this.rZ * translationX)))), 0.5 * ((this.rW) * (translationZ) + (((this.rY) * (translationX) - (this.rX * translationY)))), 0.5 * ((((_t0) * (translationY) - (this.rX * translationX))) - (this.rZ) * (translationZ)));
        }
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c0_fma(double _t0, double _t6, double _t2, double _t3, double _sp0, double[] _m) {
        _m[0] = java.lang.Math.fma(-2.0, _t0, _t6);
        _m[4] = 2.0 * java.lang.Math.fma(this.rX, this.rY, _t2);
        _m[8] = java.lang.Math.fma(-2.0, _t3, _sp0 * this.rZ);
        _m[12] = 0.0;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c0_mulAdd(double _t0, double _t6, double _t2, double _t3, double _sp0, double[] _m) {
        _m[0] = ((_t6) - (2.0) * (_t0));
        _m[4] = 2.0 * ((this.rX) * (this.rY) + (_t2));
        _m[8] = ((_sp0 * this.rZ) - (2.0) * (_t3));
        _m[12] = 0.0;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c1_fma(double _t2, double _sp0, double _t4, double _t6, double _t5, double[] _m) {
        _m[1] = java.lang.Math.fma(-2.0, _t2, _sp0 * this.rY);
        _m[5] = java.lang.Math.fma(-2.0, _t4, _t6);
        _m[9] = 2.0 * java.lang.Math.fma(this.rX, this.rW, _t5);
        _m[13] = 0.0;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c1_mulAdd(double _t2, double _sp0, double _t4, double _t6, double _t5, double[] _m) {
        _m[1] = ((_sp0 * this.rY) - (2.0) * (_t2));
        _m[5] = ((_t6) - (2.0) * (_t4));
        _m[9] = 2.0 * ((this.rX) * (this.rW) + (_t5));
        _m[13] = 0.0;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c2_fma(double _t3, double _t5, double _t4, double _t0, double[] _m) {
        _m[2] = 2.0 * java.lang.Math.fma(this.rX, this.rZ, _t3);
        _m[6] = java.lang.Math.fma(-2.0, this.rX * this.rW, _t5 + _t5);
        _m[10] = java.lang.Math.fma(-2.0, _t4, java.lang.Math.fma(-2.0, _t0, 1.0));
        _m[14] = 0.0;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c2_mulAdd(double _t3, double _t5, double _t4, double _t0, double[] _m) {
        _m[2] = 2.0 * ((this.rX) * (this.rZ) + (_t3));
        _m[6] = ((_t5 + _t5) - (2.0) * (this.rX * this.rW));
        _m[10] = ((((1.0) - (2.0) * (_t0))) - (2.0) * (_t4));
        _m[14] = 0.0;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c3_fma(double[] _m) {
        _m[3] = 2.0 * (java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW));
        _m[7] = 2.0 * (java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW));
        _m[11] = 2.0 * (java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, this.rZ * -this.dW));
        _m[15] = 1.0;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c3_mulAdd(double[] _m) {
        _m[3] = 2.0 * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW)));
        _m[7] = 2.0 * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW)));
        _m[11] = 2.0 * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW)));
        _m[15] = 1.0;
    }

    /**
     * Compute the matrix representation of this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the resulting matrix
     */
    public Double4x4 toMatrix() {
        if (!Math.useFma()) return toMatrix_mulAdd();
        double _sp0 = this.rX + this.rX;
        double _t0 = this.rY * this.rY;
        double _t2 = this.rZ * this.rW;
        double _t3 = this.rY * this.rW;
        double _t4 = this.rX * this.rX;
        double _t5 = this.rY * this.rZ;
        double _t6 = java.lang.Math.fma(-2.0, this.rZ * this.rZ, 1.0);
        double[] _m = new double[16];
        toMatrix_s0_c0_fma(_t0, _t6, _t2, _t3, _sp0, _m);
        toMatrix_s0_c1_fma(_t2, _sp0, _t4, _t6, _t5, _m);
        toMatrix_s0_c2_fma(_t3, _t5, _t4, _t0, _m);
        toMatrix_s0_c3_fma(_m);
        return new Double4x4(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7], _m[8], _m[9], _m[10], _m[11], _m[12], _m[13], _m[14], _m[15], Joml.BIT_ORTHOGONAL);
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x4 toMatrix_mulAdd() {
        double _sp0 = this.rX + this.rX;
        double _t0 = this.rY * this.rY;
        double _t2 = this.rZ * this.rW;
        double _t3 = this.rY * this.rW;
        double _t4 = this.rX * this.rX;
        double _t5 = this.rY * this.rZ;
        double _t6 = ((1.0) - (2.0) * (this.rZ * this.rZ));
        double[] _m = new double[16];
        toMatrix_s0_c0_mulAdd(_t0, _t6, _t2, _t3, _sp0, _m);
        toMatrix_s0_c1_mulAdd(_t2, _sp0, _t4, _t6, _t5, _m);
        toMatrix_s0_c2_mulAdd(_t3, _t5, _t4, _t0, _m);
        toMatrix_s0_c3_mulAdd(_m);
        return new Double4x4(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7], _m[8], _m[9], _m[10], _m[11], _m[12], _m[13], _m[14], _m[15], Joml.BIT_ORTHOGONAL);
    }

    /**
     * Compute the 3x3 matrix representation of the rotation part of this dual quaternion (the
     * encoded translation is dropped), returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the resulting matrix
     */
    public Double3x3 toMatrix3x3() {
        if (!Math.useFma()) return toMatrix3x3_mulAdd();
        double _sp0 = this.rX + this.rX;
        double _t0 = this.rY * this.rY;
        double _t2 = this.rZ * this.rW;
        double _t3 = this.rY * this.rW;
        double _t4 = this.rX * this.rX;
        double _t5 = this.rY * this.rZ;
        double _t6 = java.lang.Math.fma(-2.0, this.rZ * this.rZ, 1.0);
        return new Double3x3(java.lang.Math.fma(-2.0, _t0, _t6), java.lang.Math.fma(-2.0, _t2, _sp0 * this.rY), 2.0 * java.lang.Math.fma(this.rX, this.rZ, _t3), 2.0 * java.lang.Math.fma(this.rX, this.rY, _t2), java.lang.Math.fma(-2.0, _t4, _t6), java.lang.Math.fma(-2.0, this.rX * this.rW, _t5 + _t5), java.lang.Math.fma(-2.0, _t3, _sp0 * this.rZ), 2.0 * java.lang.Math.fma(this.rX, this.rW, _t5), java.lang.Math.fma(-2.0, _t4, java.lang.Math.fma(-2.0, _t0, 1.0)), 0);
    }

    /** {@code toMatrix3x3} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x3 toMatrix3x3_mulAdd() {
        double _sp0 = this.rX + this.rX;
        double _t0 = this.rY * this.rY;
        double _t2 = this.rZ * this.rW;
        double _t3 = this.rY * this.rW;
        double _t4 = this.rX * this.rX;
        double _t5 = this.rY * this.rZ;
        double _t6 = ((1.0) - (2.0) * (this.rZ * this.rZ));
        return new Double3x3(((_t6) - (2.0) * (_t0)), ((_sp0 * this.rY) - (2.0) * (_t2)), 2.0 * ((this.rX) * (this.rZ) + (_t3)), 2.0 * ((this.rX) * (this.rY) + (_t2)), ((_t6) - (2.0) * (_t4)), ((_t5 + _t5) - (2.0) * (this.rX * this.rW)), ((_sp0 * this.rZ) - (2.0) * (_t3)), 2.0 * ((this.rX) * (this.rW) + (_t5)), ((((1.0) - (2.0) * (_t0))) - (2.0) * (_t4)), 0);
    }

    /** Private per-column body of {@code toMatrix3x4}, stored into {@code _m}; reached only through it. */
    private void toMatrix3x4_s0_c0_fma(double _t0, double _t6, double _t2, double _t3, double _sp0, double[] _m) {
        _m[0] = java.lang.Math.fma(-2.0, _t0, _t6);
        _m[4] = 2.0 * java.lang.Math.fma(this.rX, this.rY, _t2);
        _m[8] = java.lang.Math.fma(-2.0, _t3, _sp0 * this.rZ);
    }

    /** Private per-column body of {@code toMatrix3x4}, stored into {@code _m}; reached only through it. */
    private void toMatrix3x4_s0_c0_mulAdd(double _t0, double _t6, double _t2, double _t3, double _sp0, double[] _m) {
        _m[0] = ((_t6) - (2.0) * (_t0));
        _m[4] = 2.0 * ((this.rX) * (this.rY) + (_t2));
        _m[8] = ((_sp0 * this.rZ) - (2.0) * (_t3));
    }

    /** Private per-column body of {@code toMatrix3x4}, stored into {@code _m}; reached only through it. */
    private void toMatrix3x4_s0_c1_fma(double _t2, double _sp0, double _t4, double _t6, double _t5, double[] _m) {
        _m[1] = java.lang.Math.fma(-2.0, _t2, _sp0 * this.rY);
        _m[5] = java.lang.Math.fma(-2.0, _t4, _t6);
        _m[9] = 2.0 * java.lang.Math.fma(this.rX, this.rW, _t5);
    }

    /** Private per-column body of {@code toMatrix3x4}, stored into {@code _m}; reached only through it. */
    private void toMatrix3x4_s0_c1_mulAdd(double _t2, double _sp0, double _t4, double _t6, double _t5, double[] _m) {
        _m[1] = ((_sp0 * this.rY) - (2.0) * (_t2));
        _m[5] = ((_t6) - (2.0) * (_t4));
        _m[9] = 2.0 * ((this.rX) * (this.rW) + (_t5));
    }

    /** Private per-column body of {@code toMatrix3x4}, stored into {@code _m}; reached only through it. */
    private void toMatrix3x4_s0_c2_fma(double _t3, double _t5, double _t4, double _t0, double[] _m) {
        _m[2] = 2.0 * java.lang.Math.fma(this.rX, this.rZ, _t3);
        _m[6] = java.lang.Math.fma(-2.0, this.rX * this.rW, _t5 + _t5);
        _m[10] = java.lang.Math.fma(-2.0, _t4, java.lang.Math.fma(-2.0, _t0, 1.0));
    }

    /** Private per-column body of {@code toMatrix3x4}, stored into {@code _m}; reached only through it. */
    private void toMatrix3x4_s0_c2_mulAdd(double _t3, double _t5, double _t4, double _t0, double[] _m) {
        _m[2] = 2.0 * ((this.rX) * (this.rZ) + (_t3));
        _m[6] = ((_t5 + _t5) - (2.0) * (this.rX * this.rW));
        _m[10] = ((((1.0) - (2.0) * (_t0))) - (2.0) * (_t4));
    }

    /** Private per-column body of {@code toMatrix3x4}, stored into {@code _m}; reached only through it. */
    private void toMatrix3x4_s0_c3_fma(double[] _m) {
        _m[3] = 2.0 * (java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW));
        _m[7] = 2.0 * (java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW));
        _m[11] = 2.0 * (java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, this.rZ * -this.dW));
    }

    /** Private per-column body of {@code toMatrix3x4}, stored into {@code _m}; reached only through it. */
    private void toMatrix3x4_s0_c3_mulAdd(double[] _m) {
        _m[3] = 2.0 * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW)));
        _m[7] = 2.0 * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW)));
        _m[11] = 2.0 * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW)));
    }

    /**
     * Compute the 3x4 matrix representation of this dual quaternion (the omitted last row is
     * implicitly {@code 0, 0, 0, 1}), returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return the resulting matrix
     */
    public Double3x4 toMatrix3x4() {
        if (!Math.useFma()) return toMatrix3x4_mulAdd();
        double _sp0 = this.rX + this.rX;
        double _t0 = this.rY * this.rY;
        double _t2 = this.rZ * this.rW;
        double _t3 = this.rY * this.rW;
        double _t4 = this.rX * this.rX;
        double _t5 = this.rY * this.rZ;
        double _t6 = java.lang.Math.fma(-2.0, this.rZ * this.rZ, 1.0);
        double[] _m = new double[12];
        toMatrix3x4_s0_c0_fma(_t0, _t6, _t2, _t3, _sp0, _m);
        toMatrix3x4_s0_c1_fma(_t2, _sp0, _t4, _t6, _t5, _m);
        toMatrix3x4_s0_c2_fma(_t3, _t5, _t4, _t0, _m);
        toMatrix3x4_s0_c3_fma(_m);
        return new Double3x4(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7], _m[8], _m[9], _m[10], _m[11], Joml.BIT_ORTHOGONAL);
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x4 toMatrix3x4_mulAdd() {
        double _sp0 = this.rX + this.rX;
        double _t0 = this.rY * this.rY;
        double _t2 = this.rZ * this.rW;
        double _t3 = this.rY * this.rW;
        double _t4 = this.rX * this.rX;
        double _t5 = this.rY * this.rZ;
        double _t6 = ((1.0) - (2.0) * (this.rZ * this.rZ));
        double[] _m = new double[12];
        toMatrix3x4_s0_c0_mulAdd(_t0, _t6, _t2, _t3, _sp0, _m);
        toMatrix3x4_s0_c1_mulAdd(_t2, _sp0, _t4, _t6, _t5, _m);
        toMatrix3x4_s0_c2_mulAdd(_t3, _t5, _t4, _t0, _m);
        toMatrix3x4_s0_c3_mulAdd(_m);
        return new Double3x4(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7], _m[8], _m[9], _m[10], _m[11], Joml.BIT_ORTHOGONAL);
    }

    /**
     * Normalize this dual quaternion so that its real (rotation) part has unit length, unless that
     * part has zero length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the normalized dual quaternion, or an empty {@code Optional} when the real part has
     *        zero length
     */
    public java.util.Optional<DoubleDualQuat> tryNormalize() {
        if (Math.useFma()) {
            double _ct0 = java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY) + java.lang.Math.fma(this.rZ, this.rZ, this.rW * this.rW);
            if (!(_ct0 > 0.0)) return java.util.Optional.empty();
            double _t6 = (1.0 / java.lang.Math.sqrt(_ct0));
            return java.util.Optional.of(new DoubleDualQuat(this.rX * _t6, this.rY * _t6, this.rZ * _t6, this.rW * _t6, this.dX * _t6, this.dY * _t6, this.dZ * _t6, this.dW * _t6));
        } else {
            double _ct0 = ((this.rX) * (this.rX) + (this.rY * this.rY)) + ((this.rZ) * (this.rZ) + (this.rW * this.rW));
            if (!(_ct0 > 0.0)) return java.util.Optional.empty();
            double _t6 = (1.0 / java.lang.Math.sqrt(_ct0));
            return java.util.Optional.of(new DoubleDualQuat(this.rX * _t6, this.rY * _t6, this.rZ * _t6, this.rW * _t6, this.dX * _t6, this.dY * _t6, this.dZ * _t6, this.dW * _t6));
        }
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_lh_fma(Double3 dir, Double3 up) {
        return lookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_lh_mulAdd(Double3 dir, Double3 up) {
        return lookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        double _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        double _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        double _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        return lookAlong_lh_sd9f2f6ce_1_fma(dirX, dirY, dirZ, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t27 = ((upY) - (dirY) * (_sp0));
        double _t28 = ((upZ) - (dirZ) * (_sp0));
        double _t29 = ((upX) - (dirX) * (_sp0));
        double _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        double _t37 = ((dirY) * (_t29) - (dirX * _t27));
        double _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        double _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        return lookAlong_lh_sd9f2f6ce_1_mulAdd(dirX, dirY, dirZ, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45);
    }

    /**
     * Piece 2 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_sd9f2f6ce_1_fma(double dirX, double dirY, double dirZ, double _t15, double _t36, double _t37, double _t38, double _t45, double _t0, double _t16, double _t17, double _t18, double _t19, double _t21, double _t46, double _t47) {
        double _t48 = _t38 * _t45;
        double _t49 = java.lang.Math.fma(-_t36, _t45, 1.0);
        double _t64 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        double _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        double _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0))));
        double _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0))));
        double _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        double _t83 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49)));
        return lookAlong_lh_sd9f2f6ce_2_fma(dirZ, _t15, _t36, _t45, _t16, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t64), java.lang.Math.fma(dirY, _t15, -_t64), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, _t79, _t80, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), _t83, 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t83)));
    }

    /**
     * Piece 2 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_sd9f2f6ce_1_mulAdd(double dirX, double dirY, double dirZ, double _t15, double _t36, double _t37, double _t38, double _t45, double _t0, double _t16, double _t17, double _t18, double _t19, double _t21, double _t46, double _t47) {
        double _t48 = _t38 * _t45;
        double _t49 = ((1.0) - (_t36) * (_t45));
        double _t64 = ((_t17) * (_t48) - (_t18 * _t46));
        double _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        double _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0))))))));
        double _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0))))))));
        double _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        double _t83 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49))))));
        return lookAlong_lh_sd9f2f6ce_2_mulAdd(dirZ, _t15, _t36, _t45, _t16, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t64)), ((dirY) * (_t15) - (_t64)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, _t79, _t80, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), _t83, 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t83)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_sd9f2f6ce_2_fma(double dirZ, double _t15, double _t36, double _t45, double _t16, double _t46, double _t50, double _t54, double _t62, double _t68, double _t70, double _t73, double _t74, double _t77, double _t79, double _t80, double _sp1, double _sp4, double _t83, double _sp3, double _sp2) {
        double _t125, _t126, _t127, _t128;
        if (java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t36, _t45, _t62)) > 0.0) {
            _t125 = _sp1 * _t70;
            _t126 = _sp1 * _t74;
            _t127 = _sp1 * _t54;
            _t128 = 0.5 * java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _t125 = 0.5 * java.lang.Math.sqrt(_t79);
                _t126 = _sp4 * _t50;
                _t127 = _sp4 * _t73;
                _t128 = _sp4 * _t70;
            } else {
                if (_t62 > _t16) {
                    _t125 = _sp2 * _t73;
                    _t126 = _sp2 * _t68;
                    _t127 = 0.5 * java.lang.Math.sqrt(_t83);
                    _t128 = _sp2 * _t54;
                } else {
                    _t125 = _sp3 * _t50;
                    _t126 = 0.5 * java.lang.Math.sqrt(_t80);
                    _t127 = _sp3 * _t68;
                    _t128 = _sp3 * _t74;
                }
            }
        }
        return lookAlong_lh_sd9f2f6ce_3_fma(_t125, _t126, _t127, _t128, java.lang.Math.fma(this.rX, _t128, this.rW * _t125) + java.lang.Math.fma(this.rY, _t126, -(this.rZ * _t127)), java.lang.Math.fma(this.rY, _t128, this.rZ * _t125) + java.lang.Math.fma(this.rW, _t127, -this.rX * _t126), java.lang.Math.fma(this.rX, _t127, this.rW * _t126) + java.lang.Math.fma(this.rZ, _t128, -(this.rY * _t125)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_sd9f2f6ce_2_mulAdd(double dirZ, double _t15, double _t36, double _t45, double _t16, double _t46, double _t50, double _t54, double _t62, double _t68, double _t70, double _t73, double _t74, double _t77, double _t79, double _t80, double _sp1, double _sp4, double _t83, double _sp3, double _sp2) {
        double _t125, _t126, _t127, _t128;
        if (((dirZ) * (_t15) + (((_t36) * (_t45) + (_t62)))) > 0.0) {
            _t125 = _sp1 * _t70;
            _t126 = _sp1 * _t74;
            _t127 = _sp1 * _t54;
            _t128 = 0.5 * java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _t125 = 0.5 * java.lang.Math.sqrt(_t79);
                _t126 = _sp4 * _t50;
                _t127 = _sp4 * _t73;
                _t128 = _sp4 * _t70;
            } else {
                if (_t62 > _t16) {
                    _t125 = _sp2 * _t73;
                    _t126 = _sp2 * _t68;
                    _t127 = 0.5 * java.lang.Math.sqrt(_t83);
                    _t128 = _sp2 * _t54;
                } else {
                    _t125 = _sp3 * _t50;
                    _t126 = 0.5 * java.lang.Math.sqrt(_t80);
                    _t127 = _sp3 * _t68;
                    _t128 = _sp3 * _t74;
                }
            }
        }
        return lookAlong_lh_sd9f2f6ce_3_mulAdd(_t125, _t126, _t127, _t128, ((this.rX) * (_t128) + (this.rW * _t125)) + ((this.rY) * (_t126) - (this.rZ * _t127)), ((this.rY) * (_t128) + (this.rZ * _t125)) + ((this.rW) * (_t127) - (this.rX * _t126)), ((this.rX) * (_t127) + (this.rW * _t126)) + ((this.rZ) * (_t128) - (this.rY * _t125)));
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_sd9f2f6ce_3_fma(double _t125, double _t126, double _t127, double _t128, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3 = java.lang.Math.fma(this.rW, _t128, -this.rX * _t125) - java.lang.Math.fma(this.rY, _t127, this.rZ * _t126);
        double _sfx4 = java.lang.Math.fma(this.dX, _t128, this.dW * _t125) + java.lang.Math.fma(this.dY, _t126, -(this.dZ * _t127));
        double _sfx5 = java.lang.Math.fma(this.dY, _t128, this.dZ * _t125) + java.lang.Math.fma(this.dW, _t127, -this.dX * _t126);
        double _sfx6 = java.lang.Math.fma(this.dX, _t127, this.dW * _t126) + java.lang.Math.fma(this.dZ, _t128, -(this.dY * _t125));
        double _sfx7 = java.lang.Math.fma(this.dW, _t128, -this.dX * _t125) - java.lang.Math.fma(this.dY, _t127, this.dZ * _t126);
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_sd9f2f6ce_3_mulAdd(double _t125, double _t126, double _t127, double _t128, double _sfx0, double _sfx1, double _sfx2) {
        double _el3 = ((this.rW) * (_t128) - (this.rX * _t125)) - ((this.rY) * (_t127) + (this.rZ * _t126));
        double _el4 = ((this.dX) * (_t128) + (this.dW * _t125)) + ((this.dY) * (_t126) - (this.dZ * _t127));
        double _el5 = ((this.dY) * (_t128) + (this.dZ * _t125)) + ((this.dW) * (_t127) - (this.dX * _t126));
        double _el6 = ((this.dX) * (_t127) + (this.dW * _t126)) + ((this.dZ) * (_t128) - (this.dY * _t125));
        double _el7 = ((this.dW) * (_t128) - (this.dX * _t125)) - ((this.dY) * (_t127) + (this.dZ * _t126));
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _el3, _el4, _el5, _el6, _el7);
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = 1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        double _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        double _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return lookAlong_lh_degenerate_sc9fd6f9f_2_fma(_t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = 1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        double _t45 = ((_t44) * (_t25) + (_t22));
        double _t46 = ((_t44) * (_t26) + (_t23));
        double _t47 = ((_t44) * (_t24) + (_t21));
        return lookAlong_lh_degenerate_sc9fd6f9f_2_mulAdd(_t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /**
     * Part 1 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_sc9fd6f9f_1_fma(double _t152, double _t149, double _t150, double _t151) {
        double _sfx0 = java.lang.Math.fma(this.rX, _t152, this.rW * _t149) + java.lang.Math.fma(this.rY, _t150, -(this.rZ * _t151));
        double _sfx1 = java.lang.Math.fma(this.rY, _t152, this.rZ * _t149) + java.lang.Math.fma(this.rW, _t151, -this.rX * _t150);
        double _sfx2 = java.lang.Math.fma(this.rX, _t151, this.rW * _t150) + java.lang.Math.fma(this.rZ, _t152, -(this.rY * _t149));
        double _sfx3 = java.lang.Math.fma(this.rW, _t152, -this.rX * _t149) - java.lang.Math.fma(this.rY, _t151, this.rZ * _t150);
        double _sfx4 = java.lang.Math.fma(this.dX, _t152, this.dW * _t149) + java.lang.Math.fma(this.dY, _t150, -(this.dZ * _t151));
        double _sfx5 = java.lang.Math.fma(this.dY, _t152, this.dZ * _t149) + java.lang.Math.fma(this.dW, _t151, -this.dX * _t150);
        double _sfx6 = java.lang.Math.fma(this.dX, _t151, this.dW * _t150) + java.lang.Math.fma(this.dZ, _t152, -(this.dY * _t149));
        double _sfx7 = java.lang.Math.fma(this.dW, _t152, -this.dX * _t149) - java.lang.Math.fma(this.dY, _t151, this.dZ * _t150);
        return new DoubleDualQuat((_sfx0), (_sfx1), (_sfx2), (_sfx3), (_sfx4), (_sfx5), (_sfx6), (_sfx7));
    }

    /**
     * Part 1 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_sc9fd6f9f_1_mulAdd(double _t152, double _t149, double _t150, double _t151) {
        double _sfx0 = ((this.rX) * (_t152) + (this.rW * _t149)) + ((this.rY) * (_t150) - (this.rZ * _t151));
        double _sfx1 = ((this.rY) * (_t152) + (this.rZ * _t149)) + ((this.rW) * (_t151) - (this.rX * _t150));
        double _sfx2 = ((this.rX) * (_t151) + (this.rW * _t150)) + ((this.rZ) * (_t152) - (this.rY * _t149));
        double _sfx3 = ((this.rW) * (_t152) - (this.rX * _t149)) - ((this.rY) * (_t151) + (this.rZ * _t150));
        double _sfx4 = ((this.dX) * (_t152) + (this.dW * _t149)) + ((this.dY) * (_t150) - (this.dZ * _t151));
        double _sfx5 = ((this.dY) * (_t152) + (this.dZ * _t149)) + ((this.dW) * (_t151) - (this.dX * _t150));
        double _sfx6 = ((this.dX) * (_t151) + (this.dW * _t150)) + ((this.dZ) * (_t152) - (this.dY * _t149));
        double _sfx7 = ((this.dW) * (_t152) - (this.dX * _t149)) - ((this.dY) * (_t151) + (this.dZ * _t150));
        return new DoubleDualQuat((_sfx0), (_sfx1), (_sfx2), (_sfx3), (_sfx4), (_sfx5), (_sfx6), (_sfx7));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_lh_degenerate_sc9fd6f9f_2_fma(double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t71 = -_t69;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33)));
        return lookAlong_lh_degenerate_sc9fd6f9f_3_fma(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))), _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_lh_degenerate_sc9fd6f9f_2_mulAdd(double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t71 = -_t69;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        double _t100 = ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33))))));
        return lookAlong_lh_degenerate_sc9fd6f9f_3_mulAdd(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))), _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_sc9fd6f9f_3_fma(double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97, double _t99, double _t100, double _sp3) {
        double _t103 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t104 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t99));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t103));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t104));
        double _t115 = java.lang.Math.fma(_t67, _t65, _t93);
        double _t116 = java.lang.Math.fma(_t68, _t65, _t93);
        double _t149, _t150, _t151, _t152;
        if (java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t24))) > 0.0) {
            _t149 = _sp0 * _t96;
            _t150 = _sp0 * _t116;
            _t151 = _sp0 * _t78;
            _t152 = 0.5 * java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                _t149 = 0.5 * java.lang.Math.sqrt(_t100);
                _t150 = _sp3 * _t74;
                _t151 = _sp3 * _t115;
                _t152 = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    _t149 = _sp1 * _t115;
                    _t150 = _sp1 * _t97;
                    _t151 = 0.5 * java.lang.Math.sqrt(_t103);
                    _t152 = _sp1 * _t78;
                } else {
                    _t149 = _sp2 * _t74;
                    _t150 = 0.5 * java.lang.Math.sqrt(_t104);
                    _t151 = _sp2 * _t97;
                    _t152 = _sp2 * _t116;
                }
            }
        }
        return lookAlong_lh_degenerate_sc9fd6f9f_1_fma(_t152, _t149, _t150, _t151);
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_sc9fd6f9f_3_mulAdd(double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97, double _t99, double _t100, double _sp3) {
        double _t103 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        double _t104 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t99));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t103));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t104));
        double _t115 = ((_t67) * (_t65) + (_t93));
        double _t116 = ((_t68) * (_t65) + (_t93));
        double _t149, _t150, _t151, _t152;
        if (((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t24)))))) > 0.0) {
            _t149 = _sp0 * _t96;
            _t150 = _sp0 * _t116;
            _t151 = _sp0 * _t78;
            _t152 = 0.5 * java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                _t149 = 0.5 * java.lang.Math.sqrt(_t100);
                _t150 = _sp3 * _t74;
                _t151 = _sp3 * _t115;
                _t152 = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    _t149 = _sp1 * _t115;
                    _t150 = _sp1 * _t97;
                    _t151 = 0.5 * java.lang.Math.sqrt(_t103);
                    _t152 = _sp1 * _t78;
                } else {
                    _t149 = _sp2 * _t74;
                    _t150 = 0.5 * java.lang.Math.sqrt(_t104);
                    _t151 = _sp2 * _t97;
                    _t152 = _sp2 * _t116;
                }
            }
        }
        return lookAlong_lh_degenerate_sc9fd6f9f_1_mulAdd(_t152, _t149, _t150, _t151);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_rh_fma(Double3 dir, Double3 up) {
        return lookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_rh_mulAdd(Double3 dir, Double3 up) {
        return lookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        double _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        double _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        double _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        return lookAlong_rh_sbfc82748_1_fma(dirX, dirY, _t16, _t38, _t39, _t40, (1.0 / java.lang.Math.sqrt(_ct0)), -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0), -_t38);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t29 = ((upZ) - (dirZ) * (_sp0));
        double _t30 = ((upY) - (dirY) * (_sp0));
        double _t31 = ((upX) - (dirX) * (_sp0));
        double _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        double _t39 = ((dirX) * (_t30) - (dirY * _t31));
        double _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        double _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        return lookAlong_rh_sbfc82748_1_mulAdd(dirX, dirY, _t16, _t38, _t39, _t40, (1.0 / java.lang.Math.sqrt(_ct0)), -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0)), -_t38);
    }

    /**
     * Piece 2 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_sbfc82748_1_fma(double dirX, double dirY, double _t16, double _t38, double _t39, double _t40, double _t47, double _t0, double _t17, double _t18, double _t19, double _t20, double _t22, double _t23, double _t41) {
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        double _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        double _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        double _t79 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0))));
        double _t81 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t41, _t47, _t23)));
        double _t85 = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0))));
        return lookAlong_rh_sbfc82748_2_fma(_t16, _t38, _t47, _t0, _t20, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, _t79, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), _t81, 0.5 * (1.0 / java.lang.Math.sqrt(_t81)), _t85, 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), 0.5 * (1.0 / java.lang.Math.sqrt(_t85)));
    }

    /**
     * Piece 2 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_sbfc82748_1_mulAdd(double dirX, double dirY, double _t16, double _t38, double _t39, double _t40, double _t47, double _t0, double _t17, double _t18, double _t19, double _t20, double _t22, double _t23, double _t41) {
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        double _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        double _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        double _t79 = ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0))))))));
        double _t81 = ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t41) * (_t47) + (_t23))))));
        double _t85 = ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0))))))));
        return lookAlong_rh_sbfc82748_2_mulAdd(_t16, _t38, _t47, _t0, _t20, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, _t79, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), _t81, 0.5 * (1.0 / java.lang.Math.sqrt(_t81)), _t85, 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), 0.5 * (1.0 / java.lang.Math.sqrt(_t85)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_sbfc82748_2_fma(double _t16, double _t38, double _t47, double _t0, double _t20, double _t49, double _t52, double _t56, double _t64, double _t70, double _t72, double _t74, double _t75, double _t78, double _t79, double _sp2, double _t81, double _sp4, double _t85, double _sp1, double _sp3) {
        double _t126, _t127, _t128, _t129;
        if (java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t38, _t47, _t64)) > 0.0) {
            _t126 = _sp1 * _t52;
            _t127 = 0.5 * java.lang.Math.sqrt(_t79);
            _t128 = _sp1 * _t72;
            _t129 = _sp1 * _t75;
        } else {
            if (_t49 > Math.max(_t64, _t20)) {
                _t126 = _sp2 * _t74;
                _t127 = _sp2 * _t72;
                _t128 = 0.5 * java.lang.Math.sqrt(_t78);
                _t129 = _sp2 * _t56;
            } else {
                if (_t64 > _t20) {
                    _t126 = 0.5 * java.lang.Math.sqrt(_t81);
                    _t127 = _sp4 * _t52;
                    _t128 = _sp4 * _t74;
                    _t129 = _sp4 * _t70;
                } else {
                    _t126 = _sp3 * _t70;
                    _t127 = _sp3 * _t75;
                    _t128 = _sp3 * _t56;
                    _t129 = 0.5 * java.lang.Math.sqrt(_t85);
                }
            }
        }
        return lookAlong_rh_sbfc82748_3_fma(_t126, _t127, _t128, _t129, java.lang.Math.fma(this.rX, _t127, this.rW * _t128) + java.lang.Math.fma(this.rY, _t129, -(this.rZ * _t126)), java.lang.Math.fma(this.rY, _t127, this.rZ * _t128) + java.lang.Math.fma(this.rW, _t126, -this.rX * _t129), java.lang.Math.fma(this.rX, _t126, this.rW * _t129) + java.lang.Math.fma(this.rZ, _t127, -(this.rY * _t128)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_sbfc82748_2_mulAdd(double _t16, double _t38, double _t47, double _t0, double _t20, double _t49, double _t52, double _t56, double _t64, double _t70, double _t72, double _t74, double _t75, double _t78, double _t79, double _sp2, double _t81, double _sp4, double _t85, double _sp1, double _sp3) {
        double _t126, _t127, _t128, _t129;
        if (((_t0) * (_t16) + (((_t38) * (_t47) + (_t64)))) > 0.0) {
            _t126 = _sp1 * _t52;
            _t127 = 0.5 * java.lang.Math.sqrt(_t79);
            _t128 = _sp1 * _t72;
            _t129 = _sp1 * _t75;
        } else {
            if (_t49 > Math.max(_t64, _t20)) {
                _t126 = _sp2 * _t74;
                _t127 = _sp2 * _t72;
                _t128 = 0.5 * java.lang.Math.sqrt(_t78);
                _t129 = _sp2 * _t56;
            } else {
                if (_t64 > _t20) {
                    _t126 = 0.5 * java.lang.Math.sqrt(_t81);
                    _t127 = _sp4 * _t52;
                    _t128 = _sp4 * _t74;
                    _t129 = _sp4 * _t70;
                } else {
                    _t126 = _sp3 * _t70;
                    _t127 = _sp3 * _t75;
                    _t128 = _sp3 * _t56;
                    _t129 = 0.5 * java.lang.Math.sqrt(_t85);
                }
            }
        }
        return lookAlong_rh_sbfc82748_3_mulAdd(_t126, _t127, _t128, _t129, ((this.rX) * (_t127) + (this.rW * _t128)) + ((this.rY) * (_t129) - (this.rZ * _t126)), ((this.rY) * (_t127) + (this.rZ * _t128)) + ((this.rW) * (_t126) - (this.rX * _t129)), ((this.rX) * (_t126) + (this.rW * _t129)) + ((this.rZ) * (_t127) - (this.rY * _t128)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_sbfc82748_3_fma(double _t126, double _t127, double _t128, double _t129, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3 = java.lang.Math.fma(this.rW, _t127, -this.rX * _t128) - java.lang.Math.fma(this.rY, _t126, this.rZ * _t129);
        double _sfx4 = java.lang.Math.fma(this.dX, _t127, this.dW * _t128) + java.lang.Math.fma(this.dY, _t129, -(this.dZ * _t126));
        double _sfx5 = java.lang.Math.fma(this.dY, _t127, this.dZ * _t128) + java.lang.Math.fma(this.dW, _t126, -this.dX * _t129);
        double _sfx6 = java.lang.Math.fma(this.dX, _t126, this.dW * _t129) + java.lang.Math.fma(this.dZ, _t127, -(this.dY * _t128));
        double _sfx7 = java.lang.Math.fma(this.dW, _t127, -this.dX * _t128) - java.lang.Math.fma(this.dY, _t126, this.dZ * _t129);
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_sbfc82748_3_mulAdd(double _t126, double _t127, double _t128, double _t129, double _sfx0, double _sfx1, double _sfx2) {
        double _el3 = ((this.rW) * (_t127) - (this.rX * _t128)) - ((this.rY) * (_t126) + (this.rZ * _t129));
        double _el4 = ((this.dX) * (_t127) + (this.dW * _t128)) + ((this.dY) * (_t129) - (this.dZ * _t126));
        double _el5 = ((this.dY) * (_t127) + (this.dZ * _t128)) + ((this.dW) * (_t126) - (this.dX * _t129));
        double _el6 = ((this.dX) * (_t126) + (this.dW * _t129)) + ((this.dZ) * (_t127) - (this.dY * _t128));
        double _el7 = ((this.dW) * (_t127) - (this.dX * _t128)) - ((this.dY) * (_t126) + (this.dZ * _t129));
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _el3, _el4, _el5, _el6, _el7);
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = -1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t29 = -_t26;
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        double _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        return lookAlong_rh_degenerate_s50196d65_1_fma(_t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = -1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t29 = -_t26;
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        double _t45 = ((_t44) * (_t26) + (_t23));
        double _t46 = ((_t44) * (_t25) + (_t22));
        return lookAlong_rh_degenerate_s50196d65_1_mulAdd(_t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_rh_degenerate_s50196d65_1_fma(double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        double _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_s50196d65_2_fma(_t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_rh_degenerate_s50196d65_1_mulAdd(double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        double _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_s50196d65_2_mulAdd(_t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_s50196d65_2_fma(double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t96, double _t97, double _t99) {
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        double _t103 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t104 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_rh_degenerate_s50196d65_3_fma(_t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_s50196d65_2_mulAdd(double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t96, double _t97, double _t99) {
        double _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        double _t103 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        double _t104 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_rh_degenerate_s50196d65_3_mulAdd(_t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_s50196d65_3_fma(double _t24, double _t25, double _t30, double _t64, double _t67, double _t69, double _t70, double _t72, double _t74, double _t76, double _t88, double _t96, double _t97, double _t99, double _t100, double _sp1, double _t103, double _t104, double _sp0, double _sp3, double _sp2, double _t115, double _t116) {
        double _t149, _t150, _t151, _t152;
        if (java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t30))) > 0.0) {
            _t149 = _sp0 * _t74;
            _t150 = 0.5 * java.lang.Math.sqrt(_t99);
            _t151 = _sp0 * _t96;
            _t152 = _sp0 * _t116;
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                _t149 = _sp1 * _t115;
                _t150 = _sp1 * _t96;
                _t151 = 0.5 * java.lang.Math.sqrt(_t100);
                _t152 = _sp1 * _t76;
            } else {
                if (_t88 > _t30) {
                    _t149 = 0.5 * java.lang.Math.sqrt(_t103);
                    _t150 = _sp3 * _t74;
                    _t151 = _sp3 * _t115;
                    _t152 = _sp3 * _t97;
                } else {
                    _t149 = _sp2 * _t97;
                    _t150 = _sp2 * _t116;
                    _t151 = _sp2 * _t76;
                    _t152 = 0.5 * java.lang.Math.sqrt(_t104);
                }
            }
        }
        return lookAlong_rh_sbfc82748_3_fma(_t149, _t150, _t151, _t152, java.lang.Math.fma(this.rX, _t150, this.rW * _t151) + java.lang.Math.fma(this.rY, _t152, -(this.rZ * _t149)), java.lang.Math.fma(this.rY, _t150, this.rZ * _t151) + java.lang.Math.fma(this.rW, _t149, -this.rX * _t152), java.lang.Math.fma(this.rX, _t149, this.rW * _t152) + java.lang.Math.fma(this.rZ, _t150, -(this.rY * _t151)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_s50196d65_3_mulAdd(double _t24, double _t25, double _t30, double _t64, double _t67, double _t69, double _t70, double _t72, double _t74, double _t76, double _t88, double _t96, double _t97, double _t99, double _t100, double _sp1, double _t103, double _t104, double _sp0, double _sp3, double _sp2, double _t115, double _t116) {
        double _t149, _t150, _t151, _t152;
        if (((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t30)))))) > 0.0) {
            _t149 = _sp0 * _t74;
            _t150 = 0.5 * java.lang.Math.sqrt(_t99);
            _t151 = _sp0 * _t96;
            _t152 = _sp0 * _t116;
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                _t149 = _sp1 * _t115;
                _t150 = _sp1 * _t96;
                _t151 = 0.5 * java.lang.Math.sqrt(_t100);
                _t152 = _sp1 * _t76;
            } else {
                if (_t88 > _t30) {
                    _t149 = 0.5 * java.lang.Math.sqrt(_t103);
                    _t150 = _sp3 * _t74;
                    _t151 = _sp3 * _t115;
                    _t152 = _sp3 * _t97;
                } else {
                    _t149 = _sp2 * _t97;
                    _t150 = _sp2 * _t116;
                    _t151 = _sp2 * _t76;
                    _t152 = 0.5 * java.lang.Math.sqrt(_t104);
                }
            }
        }
        return lookAlong_rh_sbfc82748_3_mulAdd(_t149, _t150, _t151, _t152, ((this.rX) * (_t150) + (this.rW * _t151)) + ((this.rY) * (_t152) - (this.rZ * _t149)), ((this.rY) * (_t150) + (this.rZ * _t151)) + ((this.rW) * (_t149) - (this.rX * _t152)), ((this.rX) * (_t149) + (this.rW * _t152)) + ((this.rZ) * (_t150) - (this.rY * _t151)));
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
    public DoubleDualQuat lookAlong(Double3 dir, Double3 up, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (Math.useFma() ? lookAlong_lh_fma(dir, up) : lookAlong_lh_mulAdd(dir, up)); }
            default -> { return (Math.useFma() ? lookAlong_rh_fma(dir, up) : lookAlong_rh_mulAdd(dir, up)); }
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
    public DoubleDualQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (Math.useFma() ? lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ) : lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
            default -> { return (Math.useFma() ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
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
    public DoubleDualQuat lookAlong(Double3 dir, Double3 up) { return (Math.useFma() ? lookAlong_rh_fma(dir, up) : lookAlong_rh_mulAdd(dir, up)); }

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
    public DoubleDualQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) { return (Math.useFma() ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_lh_fma(Double3 dir, Double3 up, double epsilon) {
        return lookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_lh_mulAdd(Double3 dir, Double3 up, double epsilon) {
        return lookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        double _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        double _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        double _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        return lookAlong_lh_sd9f2f6ce_1_fma(dirX, dirY, dirZ, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t27 = ((upY) - (dirY) * (_sp0));
        double _t28 = ((upZ) - (dirZ) * (_sp0));
        double _t29 = ((upX) - (dirX) * (_sp0));
        double _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        double _t37 = ((dirY) * (_t29) - (dirX * _t27));
        double _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        double _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        return lookAlong_lh_sd9f2f6ce_1_mulAdd(dirX, dirY, dirZ, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45);
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = 1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        double _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        double _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return lookAlong_lh_degenerate_s18e2215c_2_fma(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_lh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = 1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        double _t45 = ((_t44) * (_t25) + (_t22));
        double _t46 = ((_t44) * (_t26) + (_t23));
        double _t47 = ((_t44) * (_t24) + (_t21));
        return lookAlong_lh_degenerate_s18e2215c_2_mulAdd(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_lh_degenerate_s18e2215c_2_fma(double epsilon, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t71 = -_t69;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33)));
        return lookAlong_lh_degenerate_sc9fd6f9f_3_fma(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))), _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_lh_degenerate_s18e2215c_2_mulAdd(double epsilon, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t71 = -_t69;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        double _t100 = ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33))))));
        return lookAlong_lh_degenerate_sc9fd6f9f_3_mulAdd(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))), _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_rh_fma(Double3 dir, Double3 up, double epsilon) {
        return lookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_rh_mulAdd(Double3 dir, Double3 up, double epsilon) {
        return lookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        double _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        double _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        double _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        return lookAlong_rh_sbfc82748_1_fma(dirX, dirY, _t16, _t38, _t39, _t40, (1.0 / java.lang.Math.sqrt(_ct0)), -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0), -_t38);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleDualQuat lookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t29 = ((upZ) - (dirZ) * (_sp0));
        double _t30 = ((upY) - (dirY) * (_sp0));
        double _t31 = ((upX) - (dirX) * (_sp0));
        double _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        double _t39 = ((dirX) * (_t30) - (dirY * _t31));
        double _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        double _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        return lookAlong_rh_sbfc82748_1_mulAdd(dirX, dirY, _t16, _t38, _t39, _t40, (1.0 / java.lang.Math.sqrt(_ct0)), -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0)), -_t38);
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = -1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t29 = -_t26;
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        double _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        return lookAlong_rh_degenerate_se8f60756_1_fma(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleDualQuat lookAlong_rh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = -1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t29 = -_t26;
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        double _t45 = ((_t44) * (_t26) + (_t23));
        double _t46 = ((_t44) * (_t25) + (_t22));
        return lookAlong_rh_degenerate_se8f60756_1_mulAdd(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_rh_degenerate_se8f60756_1_fma(double epsilon, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        double _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_s50196d65_2_fma(_t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleDualQuat lookAlong_rh_degenerate_se8f60756_1_mulAdd(double epsilon, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        double _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return lookAlong_rh_degenerate_s50196d65_2_mulAdd(_t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))));
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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @param handedness the handedness of the coordinate system to map into
     * @return the resulting dual quaternion
     */
    public DoubleDualQuat lookAlong(Double3 dir, Double3 up, double epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (Math.useFma() ? lookAlong_lh_fma(dir, up, epsilon) : lookAlong_lh_mulAdd(dir, up, epsilon)); }
            default -> { return (Math.useFma() ? lookAlong_rh_fma(dir, up, epsilon) : lookAlong_rh_mulAdd(dir, up, epsilon)); }
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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @param handedness the handedness of the coordinate system to map into
     * @return the resulting dual quaternion
     */
    public DoubleDualQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (Math.useFma() ? lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
            default -> { return (Math.useFma() ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @return the resulting dual quaternion
     */
    public DoubleDualQuat lookAlong(Double3 dir, Double3 up, double epsilon) { return (Math.useFma() ? lookAlong_rh_fma(dir, up, epsilon) : lookAlong_rh_mulAdd(dir, up, epsilon)); }

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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @return the resulting dual quaternion
     */
    public DoubleDualQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) { return (Math.useFma() ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }

    /**
     * Create a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return the resulting dual quaternion
     */
    public static DoubleDualQuat makeRotationAxis(double angle, Double3 axis) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleDualQuat(axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0), 0.0, 0.0, 0.0, 0.0);
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
    public static DoubleDualQuat makeRotationAxis(double angle, double axisX, double axisY, double axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleDualQuat(axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0), 0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleDualQuat makeRotationLookAlong_lh_fma(Double3 dir, Double3 up) {
        return makeRotationLookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleDualQuat makeRotationLookAlong_lh_mulAdd(Double3 dir, Double3 up) {
        return makeRotationLookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleDualQuat makeRotationLookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        double _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        double _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        double _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        return makeRotationLookAlong_lh_s5c9915aa_1_fma(dirX, dirY, dirZ, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45, _t38 * _t45);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleDualQuat makeRotationLookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t27 = ((upY) - (dirY) * (_sp0));
        double _t28 = ((upZ) - (dirZ) * (_sp0));
        double _t29 = ((upX) - (dirX) * (_sp0));
        double _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        double _t37 = ((dirY) * (_t29) - (dirX * _t27));
        double _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        double _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        return makeRotationLookAlong_lh_s5c9915aa_1_mulAdd(dirX, dirY, dirZ, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45, _t38 * _t45);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static DoubleDualQuat makeRotationLookAlong_lh_s5c9915aa_1_fma(double dirX, double dirY, double dirZ, double _t15, double _t36, double _t37, double _t38, double _t45, double _t0, double _t16, double _t17, double _t18, double _t19, double _t21, double _t46, double _t47, double _t48) {
        double _t49 = java.lang.Math.fma(-_t36, _t45, 1.0);
        double _t63 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        double _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        double _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0))));
        double _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0))));
        double _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        double _t81 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49)));
        return makeRotationLookAlong_lh_s5c9915aa_2_fma(dirZ, _t15, _t36, _t45, _t16, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t63), java.lang.Math.fma(dirY, _t15, -_t63), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), _t79, _t80, _t81, 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), 0.5 * (1.0 / java.lang.Math.sqrt(_t81)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static DoubleDualQuat makeRotationLookAlong_lh_s5c9915aa_1_mulAdd(double dirX, double dirY, double dirZ, double _t15, double _t36, double _t37, double _t38, double _t45, double _t0, double _t16, double _t17, double _t18, double _t19, double _t21, double _t46, double _t47, double _t48) {
        double _t49 = ((1.0) - (_t36) * (_t45));
        double _t63 = ((_t17) * (_t48) - (_t18 * _t46));
        double _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        double _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0))))))));
        double _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0))))))));
        double _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        double _t81 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49))))));
        return makeRotationLookAlong_lh_s5c9915aa_2_mulAdd(dirZ, _t15, _t36, _t45, _t16, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t63)), ((dirY) * (_t15) - (_t63)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), _t79, _t80, _t81, 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), 0.5 * (1.0 / java.lang.Math.sqrt(_t81)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static DoubleDualQuat makeRotationLookAlong_lh_s5c9915aa_2_fma(double dirZ, double _t15, double _t36, double _t45, double _t16, double _t46, double _t50, double _t55, double _t62, double _t68, double _t69, double _t73, double _t74, double _t77, double _sp1, double _t79, double _t80, double _t81, double _sp3, double _sp4, double _sp2) {
        double _sfx0, _sfx1, _sfx2, _sfx3;
        if (java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t36, _t45, _t62)) > 0.0) {
            _sfx0 = _sp1 * _t69;
            _sfx1 = _sp1 * _t55;
            _sfx2 = _sp1 * _t74;
            _sfx3 = 0.5 * java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _sfx0 = 0.5 * java.lang.Math.sqrt(_t79);
                _sfx1 = _sp4 * _t73;
                _sfx2 = _sp4 * _t50;
                _sfx3 = _sp4 * _t69;
            } else {
                if (_t62 > _t16) {
                    _sfx0 = _sp2 * _t73;
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t81);
                    _sfx2 = _sp2 * _t68;
                    _sfx3 = _sp2 * _t55;
                } else {
                    _sfx0 = _sp3 * _t50;
                    _sfx1 = _sp3 * _t68;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t80);
                    _sfx3 = _sp3 * _t74;
                }
            }
        }
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static DoubleDualQuat makeRotationLookAlong_lh_s5c9915aa_2_mulAdd(double dirZ, double _t15, double _t36, double _t45, double _t16, double _t46, double _t50, double _t55, double _t62, double _t68, double _t69, double _t73, double _t74, double _t77, double _sp1, double _t79, double _t80, double _t81, double _sp3, double _sp4, double _sp2) {
        double _sfx0, _sfx1, _sfx2, _sfx3;
        if (((dirZ) * (_t15) + (((_t36) * (_t45) + (_t62)))) > 0.0) {
            _sfx0 = _sp1 * _t69;
            _sfx1 = _sp1 * _t55;
            _sfx2 = _sp1 * _t74;
            _sfx3 = 0.5 * java.lang.Math.sqrt(_t77);
        } else {
            if (_t46 > Math.max(_t62, _t16)) {
                _sfx0 = 0.5 * java.lang.Math.sqrt(_t79);
                _sfx1 = _sp4 * _t73;
                _sfx2 = _sp4 * _t50;
                _sfx3 = _sp4 * _t69;
            } else {
                if (_t62 > _t16) {
                    _sfx0 = _sp2 * _t73;
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t81);
                    _sfx2 = _sp2 * _t68;
                    _sfx3 = _sp2 * _t55;
                } else {
                    _sfx0 = _sp3 * _t50;
                    _sfx1 = _sp3 * _t68;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t80);
                    _sfx3 = _sp3 * _t74;
                }
            }
        }
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static DoubleDualQuat makeRotationLookAlong_lh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = 1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        double _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        double _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return makeRotationLookAlong_lh_degenerate_s4577925b_1_fma(_t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static DoubleDualQuat makeRotationLookAlong_lh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = 1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        double _t45 = ((_t44) * (_t25) + (_t22));
        double _t46 = ((_t44) * (_t26) + (_t23));
        double _t47 = ((_t44) * (_t24) + (_t21));
        return makeRotationLookAlong_lh_degenerate_s4577925b_1_mulAdd(_t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private static DoubleDualQuat makeRotationLookAlong_lh_degenerate_s4577925b_1_fma(double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t71 = -_t69;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        double _t99 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32)));
        return makeRotationLookAlong_lh_degenerate_s4577925b_2_fma(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), _t99, java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33))), 0.5 * (1.0 / java.lang.Math.sqrt(_t99)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private static DoubleDualQuat makeRotationLookAlong_lh_degenerate_s4577925b_1_mulAdd(double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t71 = -_t69;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        double _t99 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32))))));
        return makeRotationLookAlong_lh_degenerate_s4577925b_2_mulAdd(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), _t99, ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33)))))), 0.5 * (1.0 / java.lang.Math.sqrt(_t99)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private static DoubleDualQuat makeRotationLookAlong_lh_degenerate_s4577925b_2_fma(double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97, double _t99, double _t100, double _sp0) {
        double _t102 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t103 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t100));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t102));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t103));
        double _t107 = java.lang.Math.fma(_t67, _t65, _t93);
        double _t108 = java.lang.Math.fma(_t68, _t65, _t93);
        double _sfx0, _sfx1, _sfx2, _sfx3;
        if (java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t24))) > 0.0) {
            _sfx0 = _sp0 * _t96;
            _sfx1 = _sp0 * _t78;
            _sfx2 = _sp0 * _t108;
            _sfx3 = 0.5 * java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                _sfx0 = 0.5 * java.lang.Math.sqrt(_t100);
                _sfx1 = _sp3 * _t107;
                _sfx2 = _sp3 * _t74;
                _sfx3 = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    _sfx0 = _sp1 * _t107;
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t102);
                    _sfx2 = _sp1 * _t97;
                    _sfx3 = _sp1 * _t78;
                } else {
                    _sfx0 = _sp2 * _t74;
                    _sfx1 = _sp2 * _t97;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t103);
                    _sfx3 = _sp2 * _t108;
                }
            }
        }
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private static DoubleDualQuat makeRotationLookAlong_lh_degenerate_s4577925b_2_mulAdd(double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97, double _t99, double _t100, double _sp0) {
        double _t102 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        double _t103 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t100));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t102));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t103));
        double _t107 = ((_t67) * (_t65) + (_t93));
        double _t108 = ((_t68) * (_t65) + (_t93));
        double _sfx0, _sfx1, _sfx2, _sfx3;
        if (((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t24)))))) > 0.0) {
            _sfx0 = _sp0 * _t96;
            _sfx1 = _sp0 * _t78;
            _sfx2 = _sp0 * _t108;
            _sfx3 = 0.5 * java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t89, _t24)) {
                _sfx0 = 0.5 * java.lang.Math.sqrt(_t100);
                _sfx1 = _sp3 * _t107;
                _sfx2 = _sp3 * _t74;
                _sfx3 = _sp3 * _t96;
            } else {
                if (_t89 > _t24) {
                    _sfx0 = _sp1 * _t107;
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t102);
                    _sfx2 = _sp1 * _t97;
                    _sfx3 = _sp1 * _t78;
                } else {
                    _sfx0 = _sp2 * _t74;
                    _sfx1 = _sp2 * _t97;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t103);
                    _sfx3 = _sp2 * _t108;
                }
            }
        }
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_fma(Double3 dir, Double3 up) {
        return makeRotationLookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_mulAdd(Double3 dir, Double3 up) {
        return makeRotationLookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        double _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        double _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        double _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        double _t47 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        return makeRotationLookAlong_rh_sf0b2d43c_1_fma(dirX, dirY, _t16, _t38, _t39, _t40, _t47, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0), -_t38, _t39 * _t47);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t29 = ((upZ) - (dirZ) * (_sp0));
        double _t30 = ((upY) - (dirY) * (_sp0));
        double _t31 = ((upX) - (dirX) * (_sp0));
        double _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        double _t39 = ((dirX) * (_t30) - (dirY * _t31));
        double _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        double _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 5.048709793414476E-29) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        double _t47 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        return makeRotationLookAlong_rh_sf0b2d43c_1_mulAdd(dirX, dirY, _t16, _t38, _t39, _t40, _t47, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0)), -_t38, _t39 * _t47);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static DoubleDualQuat makeRotationLookAlong_rh_sf0b2d43c_1_fma(double dirX, double dirY, double _t16, double _t38, double _t39, double _t40, double _t47, double _t0, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t41, double _t48) {
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        double _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        double _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        double _t79 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0))));
        double _t80 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t41, _t47, _t23)));
        double _t84 = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0))));
        return makeRotationLookAlong_rh_sf0b2d43c_2_fma(_t16, _t38, _t47, _t0, _t21, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, _t79, _t80, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), _t84, 0.5 * (1.0 / java.lang.Math.sqrt(_t84)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static DoubleDualQuat makeRotationLookAlong_rh_sf0b2d43c_1_mulAdd(double dirX, double dirY, double _t16, double _t38, double _t39, double _t40, double _t47, double _t0, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t41, double _t48) {
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        double _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        double _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        double _t79 = ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0))))))));
        double _t80 = ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t41) * (_t47) + (_t23))))));
        double _t84 = ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0))))))));
        return makeRotationLookAlong_rh_sf0b2d43c_2_mulAdd(_t16, _t38, _t47, _t0, _t21, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, _t79, _t80, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), _t84, 0.5 * (1.0 / java.lang.Math.sqrt(_t84)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_sf0b2d43c_2_fma(double _t16, double _t38, double _t47, double _t0, double _t21, double _t49, double _t52, double _t56, double _t64, double _t70, double _t71, double _t74, double _t75, double _t78, double _t79, double _t80, double _sp4, double _sp1, double _sp2, double _t84, double _sp3) {
        double _sfx0, _sfx1, _sfx2, _sfx3;
        if (java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t38, _t47, _t64)) > 0.0) {
            _sfx0 = _sp1 * _t71;
            _sfx1 = _sp1 * _t52;
            _sfx2 = _sp1 * _t75;
            _sfx3 = 0.5 * java.lang.Math.sqrt(_t79);
        } else {
            if (_t49 > Math.max(_t64, _t21)) {
                _sfx0 = 0.5 * java.lang.Math.sqrt(_t78);
                _sfx1 = _sp4 * _t74;
                _sfx2 = _sp4 * _t56;
                _sfx3 = _sp4 * _t71;
            } else {
                if (_t64 > _t21) {
                    _sfx0 = _sp2 * _t74;
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t80);
                    _sfx2 = _sp2 * _t70;
                    _sfx3 = _sp2 * _t52;
                } else {
                    _sfx0 = _sp3 * _t56;
                    _sfx1 = _sp3 * _t70;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t84);
                    _sfx3 = _sp3 * _t75;
                }
            }
        }
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_sf0b2d43c_2_mulAdd(double _t16, double _t38, double _t47, double _t0, double _t21, double _t49, double _t52, double _t56, double _t64, double _t70, double _t71, double _t74, double _t75, double _t78, double _t79, double _t80, double _sp4, double _sp1, double _sp2, double _t84, double _sp3) {
        double _sfx0, _sfx1, _sfx2, _sfx3;
        if (((_t0) * (_t16) + (((_t38) * (_t47) + (_t64)))) > 0.0) {
            _sfx0 = _sp1 * _t71;
            _sfx1 = _sp1 * _t52;
            _sfx2 = _sp1 * _t75;
            _sfx3 = 0.5 * java.lang.Math.sqrt(_t79);
        } else {
            if (_t49 > Math.max(_t64, _t21)) {
                _sfx0 = 0.5 * java.lang.Math.sqrt(_t78);
                _sfx1 = _sp4 * _t74;
                _sfx2 = _sp4 * _t56;
                _sfx3 = _sp4 * _t71;
            } else {
                if (_t64 > _t21) {
                    _sfx0 = _sp2 * _t74;
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t80);
                    _sfx2 = _sp2 * _t70;
                    _sfx3 = _sp2 * _t52;
                } else {
                    _sfx0 = _sp3 * _t56;
                    _sfx1 = _sp3 * _t70;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t84);
                    _sfx3 = _sp3 * _t75;
                }
            }
        }
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = -1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t29 = -_t26;
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        double _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        return makeRotationLookAlong_rh_degenerate_sbd49e209_1_fma(_t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = -1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t29 = -_t26;
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        double _t45 = ((_t44) * (_t26) + (_t23));
        double _t46 = ((_t44) * (_t25) + (_t22));
        return makeRotationLookAlong_rh_degenerate_sbd49e209_1_mulAdd(_t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private static DoubleDualQuat makeRotationLookAlong_rh_degenerate_sbd49e209_1_fma(double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        double _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_sbd49e209_2_fma(_t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private static DoubleDualQuat makeRotationLookAlong_rh_degenerate_sbd49e209_1_mulAdd(double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        double _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23)))) * 5.048709793414476E-29) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_sbd49e209_2_mulAdd(_t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_degenerate_sbd49e209_2_fma(double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t96, double _t97, double _t99) {
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        double _t102 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t103 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return makeRotationLookAlong_rh_degenerate_sbd49e209_3_fma(_t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_degenerate_sbd49e209_2_mulAdd(double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t96, double _t97, double _t99) {
        double _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        double _t102 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        double _t103 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return makeRotationLookAlong_rh_degenerate_sbd49e209_3_mulAdd(_t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_degenerate_sbd49e209_3_fma(double _t24, double _t25, double _t30, double _t64, double _t67, double _t69, double _t70, double _t72, double _t74, double _t76, double _t88, double _t96, double _t97, double _t99, double _t100, double _sp0, double _t102, double _t103, double _sp3, double _sp1, double _sp2, double _t107, double _t108) {
        double _sfx0, _sfx1, _sfx2, _sfx3;
        if (java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t30))) > 0.0) {
            _sfx0 = _sp0 * _t96;
            _sfx1 = _sp0 * _t74;
            _sfx2 = _sp0 * _t108;
            _sfx3 = 0.5 * java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                _sfx0 = 0.5 * java.lang.Math.sqrt(_t100);
                _sfx1 = _sp3 * _t107;
                _sfx2 = _sp3 * _t76;
                _sfx3 = _sp3 * _t96;
            } else {
                if (_t88 > _t30) {
                    _sfx0 = _sp1 * _t107;
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t102);
                    _sfx2 = _sp1 * _t97;
                    _sfx3 = _sp1 * _t74;
                } else {
                    _sfx0 = _sp2 * _t76;
                    _sfx1 = _sp2 * _t97;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t103);
                    _sfx3 = _sp2 * _t108;
                }
            }
        }
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_degenerate_sbd49e209_3_mulAdd(double _t24, double _t25, double _t30, double _t64, double _t67, double _t69, double _t70, double _t72, double _t74, double _t76, double _t88, double _t96, double _t97, double _t99, double _t100, double _sp0, double _t102, double _t103, double _sp3, double _sp1, double _sp2, double _t107, double _t108) {
        double _sfx0, _sfx1, _sfx2, _sfx3;
        if (((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t30)))))) > 0.0) {
            _sfx0 = _sp0 * _t96;
            _sfx1 = _sp0 * _t74;
            _sfx2 = _sp0 * _t108;
            _sfx3 = 0.5 * java.lang.Math.sqrt(_t99);
        } else {
            if (_t70 > Math.max(_t88, _t30)) {
                _sfx0 = 0.5 * java.lang.Math.sqrt(_t100);
                _sfx1 = _sp3 * _t107;
                _sfx2 = _sp3 * _t76;
                _sfx3 = _sp3 * _t96;
            } else {
                if (_t88 > _t30) {
                    _sfx0 = _sp1 * _t107;
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t102);
                    _sfx2 = _sp1 * _t97;
                    _sfx3 = _sp1 * _t74;
                } else {
                    _sfx0 = _sp2 * _t76;
                    _sfx1 = _sp2 * _t97;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t103);
                    _sfx3 = _sp2 * _t108;
                }
            }
        }
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0, 0.0, 0.0, 0.0);
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
    public static DoubleDualQuat makeRotationLookAlong(Double3 dir, Double3 up, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (Math.useFma() ? makeRotationLookAlong_lh_fma(dir, up) : makeRotationLookAlong_lh_mulAdd(dir, up)); }
            default -> { return (Math.useFma() ? makeRotationLookAlong_rh_fma(dir, up) : makeRotationLookAlong_rh_mulAdd(dir, up)); }
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
    public static DoubleDualQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (Math.useFma() ? makeRotationLookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ) : makeRotationLookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
            default -> { return (Math.useFma() ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
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
    public static DoubleDualQuat makeRotationLookAlong(Double3 dir, Double3 up) { return (Math.useFma() ? makeRotationLookAlong_rh_fma(dir, up) : makeRotationLookAlong_rh_mulAdd(dir, up)); }

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
    public static DoubleDualQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) { return (Math.useFma() ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleDualQuat makeRotationLookAlong_lh_fma(Double3 dir, Double3 up, double epsilon) {
        return makeRotationLookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleDualQuat makeRotationLookAlong_lh_mulAdd(Double3 dir, Double3 up, double epsilon) {
        return makeRotationLookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleDualQuat makeRotationLookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        double _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        double _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        double _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        return makeRotationLookAlong_lh_s4c0dc561_1_fma(dirX, dirY, dirZ, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleDualQuat makeRotationLookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t27 = ((upY) - (dirY) * (_sp0));
        double _t28 = ((upZ) - (dirZ) * (_sp0));
        double _t29 = ((upX) - (dirX) * (_sp0));
        double _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        double _t37 = ((dirY) * (_t29) - (dirX * _t27));
        double _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        double _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t45 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = dirZ * _t15;
        double _t17 = dirX * _t15;
        return makeRotationLookAlong_lh_s4c0dc561_1_mulAdd(dirX, dirY, dirZ, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static DoubleDualQuat makeRotationLookAlong_lh_s4c0dc561_1_fma(double dirX, double dirY, double dirZ, double _t15, double _t36, double _t37, double _t38, double _t45, double _t0, double _t16, double _t17, double _t18, double _t19, double _t21, double _t46, double _t47) {
        double _t48 = _t38 * _t45;
        double _t49 = java.lang.Math.fma(-_t36, _t45, 1.0);
        double _t63 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        double _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        double _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0))));
        double _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0))));
        double _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        double _t81 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49)));
        return makeRotationLookAlong_lh_s5c9915aa_2_fma(dirZ, _t15, _t36, _t45, _t16, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t63), java.lang.Math.fma(dirY, _t15, -_t63), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), _t79, _t80, _t81, 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), 0.5 * (1.0 / java.lang.Math.sqrt(_t81)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static DoubleDualQuat makeRotationLookAlong_lh_s4c0dc561_1_mulAdd(double dirX, double dirY, double dirZ, double _t15, double _t36, double _t37, double _t38, double _t45, double _t0, double _t16, double _t17, double _t18, double _t19, double _t21, double _t46, double _t47) {
        double _t48 = _t38 * _t45;
        double _t49 = ((1.0) - (_t36) * (_t45));
        double _t63 = ((_t17) * (_t48) - (_t18 * _t46));
        double _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        double _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0))))))));
        double _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0))))))));
        double _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        double _t81 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49))))));
        return makeRotationLookAlong_lh_s5c9915aa_2_mulAdd(dirZ, _t15, _t36, _t45, _t16, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t63)), ((dirY) * (_t15) - (_t63)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, 0.5 * (1.0 / java.lang.Math.sqrt(_t77)), _t79, _t80, _t81, 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), 0.5 * (1.0 / java.lang.Math.sqrt(_t81)));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static DoubleDualQuat makeRotationLookAlong_lh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = 1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -java.lang.Math.fma(_t21, _t24, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        double _t45 = java.lang.Math.fma(_t44, _t25, _t22);
        double _t46 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return makeRotationLookAlong_lh_degenerate_s76e68778_1_fma(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static DoubleDualQuat makeRotationLookAlong_lh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = 1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = -_t26;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = -_t24;
        }
        double _t44 = -((_t21) * (_t24) + (((_t25) * (_t22) + (_t26 * _t23))));
        double _t45 = ((_t44) * (_t25) + (_t22));
        double _t46 = ((_t44) * (_t26) + (_t23));
        double _t47 = ((_t44) * (_t24) + (_t21));
        return makeRotationLookAlong_lh_degenerate_s76e68778_1_mulAdd(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private static DoubleDualQuat makeRotationLookAlong_lh_degenerate_s76e68778_1_fma(double epsilon, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t71 = -_t69;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        double _t99 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32)));
        return makeRotationLookAlong_lh_degenerate_s4577925b_2_fma(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), _t99, java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33))), 0.5 * (1.0 / java.lang.Math.sqrt(_t99)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private static DoubleDualQuat makeRotationLookAlong_lh_degenerate_s76e68778_1_mulAdd(double epsilon, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t71 = -_t69;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        double _t99 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32))))));
        return makeRotationLookAlong_lh_degenerate_s4577925b_2_mulAdd(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), _t99, ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33)))))), 0.5 * (1.0 / java.lang.Math.sqrt(_t99)));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_fma(Double3 dir, Double3 up, double epsilon) {
        return makeRotationLookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_mulAdd(Double3 dir, Double3 up, double epsilon) {
        return makeRotationLookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        double _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        double _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        double _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        double _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        double _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        double _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        return makeRotationLookAlong_rh_sacb5e2cf_1_fma(dirX, dirY, _t16, _t38, _t39, _t40, (1.0 / java.lang.Math.sqrt(_ct0)), -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0), -_t38);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t29 = ((upZ) - (dirZ) * (_sp0));
        double _t30 = ((upY) - (dirY) * (_sp0));
        double _t31 = ((upX) - (dirX) * (_sp0));
        double _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        double _t39 = ((dirX) * (_t30) - (dirY * _t31));
        double _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        double _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308)) && _ct0 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        double _t17 = dirX * _t16;
        double _t18 = dirZ * _t16;
        return makeRotationLookAlong_rh_sacb5e2cf_1_mulAdd(dirX, dirY, _t16, _t38, _t39, _t40, (1.0 / java.lang.Math.sqrt(_ct0)), -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0)), -_t38);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static DoubleDualQuat makeRotationLookAlong_rh_sacb5e2cf_1_fma(double dirX, double dirY, double _t16, double _t38, double _t39, double _t40, double _t47, double _t0, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t41) {
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        double _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        double _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        double _t79 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0))));
        double _t80 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t41, _t47, _t23)));
        double _t84 = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0))));
        return makeRotationLookAlong_rh_sf0b2d43c_2_fma(_t16, _t38, _t47, _t0, _t21, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, _t79, _t80, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), _t84, 0.5 * (1.0 / java.lang.Math.sqrt(_t84)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static DoubleDualQuat makeRotationLookAlong_rh_sacb5e2cf_1_mulAdd(double dirX, double dirY, double _t16, double _t38, double _t39, double _t40, double _t47, double _t0, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t41) {
        double _t48 = _t39 * _t47;
        double _t49 = _t38 * _t47;
        double _t50 = _t40 * _t47;
        double _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        double _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        double _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        double _t79 = ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0))))))));
        double _t80 = ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t41) * (_t47) + (_t23))))));
        double _t84 = ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0))))))));
        return makeRotationLookAlong_rh_sf0b2d43c_2_mulAdd(_t16, _t38, _t47, _t0, _t21, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, _t79, _t80, 0.5 * (1.0 / java.lang.Math.sqrt(_t78)), 0.5 * (1.0 / java.lang.Math.sqrt(_t79)), 0.5 * (1.0 / java.lang.Math.sqrt(_t80)), _t84, 0.5 * (1.0 / java.lang.Math.sqrt(_t84)));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = -1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t29 = -_t26;
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -java.lang.Math.fma(_t24, _t21, java.lang.Math.fma(_t25, _t22, _t26 * _t23));
        double _t45 = java.lang.Math.fma(_t44, _t26, _t23);
        double _t46 = java.lang.Math.fma(_t44, _t25, _t22);
        return makeRotationLookAlong_rh_degenerate_sf59a41da_1_fma(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static DoubleDualQuat makeRotationLookAlong_rh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = ((_t8) * (_t8) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = -1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t29 = -_t26;
        double _t30 = -_t24;
        double _t37, _t38, _t42;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t37 = 0.0;
            _t38 = _t29;
            _t42 = _t25;
        } else {
            _t37 = _t26;
            _t38 = 0.0;
            _t42 = _t30;
        }
        double _t44 = -((_t24) * (_t21) + (((_t25) * (_t22) + (_t26 * _t23))));
        double _t45 = ((_t44) * (_t26) + (_t23));
        double _t46 = ((_t44) * (_t25) + (_t22));
        return makeRotationLookAlong_rh_degenerate_sf59a41da_1_mulAdd(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0 - _t24, 1.0 + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private static DoubleDualQuat makeRotationLookAlong_rh_degenerate_sf59a41da_1_fma(double epsilon, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = java.lang.Math.fma(_t46, _t24, -(_t47 * _t25));
        double _t58 = java.lang.Math.fma(_t47, _t26, _t45 * -_t24);
        double _t62 = java.lang.Math.fma(_t56, _t56, java.lang.Math.fma(_t57, _t57, _t58 * _t58));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, _t23 * _t23))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t42 * _t42))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_sbd49e209_2_fma(_t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private static DoubleDualQuat makeRotationLookAlong_rh_degenerate_sf59a41da_1_mulAdd(double epsilon, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
        double _t57 = ((_t46) * (_t24) - (_t47 * _t25));
        double _t58 = ((_t47) * (_t26) - (_t45 * _t24));
        double _t62 = ((_t56) * (_t56) + (((_t57) * (_t57) + (_t58 * _t58))));
        double _t63, _t64, _t65, _t67;
        if (_t62 <= epsilon * ((_t21) * (_t21) + (((_t22) * (_t22) + (_t23 * _t23))))) {
            _t63 = _t37;
            _t64 = _t38;
            _t65 = _t42;
            _t67 = (1.0 / java.lang.Math.sqrt(((_t37) * (_t37) + (((_t38) * (_t38) + (_t42 * _t42))))));
        } else {
            _t63 = _t56;
            _t64 = _t58;
            _t65 = _t57;
            _t67 = (1.0 / java.lang.Math.sqrt(_t62));
        }
        double _t69 = _t67 * _t63;
        double _t70 = _t67 * _t64;
        double _t72 = -_t70;
        double _t73 = _t67 * _t65;
        return makeRotationLookAlong_rh_degenerate_sbd49e209_2_mulAdd(_t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32)))))));
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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @param handedness the handedness of the coordinate system to map into
     * @return the resulting dual quaternion
     */
    public static DoubleDualQuat makeRotationLookAlong(Double3 dir, Double3 up, double epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (Math.useFma() ? makeRotationLookAlong_lh_fma(dir, up, epsilon) : makeRotationLookAlong_lh_mulAdd(dir, up, epsilon)); }
            default -> { return (Math.useFma() ? makeRotationLookAlong_rh_fma(dir, up, epsilon) : makeRotationLookAlong_rh_mulAdd(dir, up, epsilon)); }
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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @param handedness the handedness of the coordinate system to map into
     * @return the resulting dual quaternion
     */
    public static DoubleDualQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (Math.useFma() ? makeRotationLookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : makeRotationLookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
            default -> { return (Math.useFma() ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @return the resulting dual quaternion
     */
    public static DoubleDualQuat makeRotationLookAlong(Double3 dir, Double3 up, double epsilon) { return (Math.useFma() ? makeRotationLookAlong_rh_fma(dir, up, epsilon) : makeRotationLookAlong_rh_mulAdd(dir, up, epsilon)); }

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
     *        place of {@code up}); the overload without it uses {@code 2^-94}
     * @return the resulting dual quaternion
     */
    public static DoubleDualQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) { return (Math.useFma() ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }

    /**
     * Create a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting dual quaternion
     */
    public static DoubleDualQuat makeRotationX(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleDualQuat(_t1, 0.0, 0.0, Math.cosFromSin(_t1, _t0), 0.0, 0.0, 0.0, 0.0);
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
    public static DoubleDualQuat makeRotationXYZ(double angleX, double angleY, double angleZ) {
        if (!Math.useFma()) return makeRotationXYZ_mulAdd(angleX, angleY, angleZ);
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        return new DoubleDualQuat(java.lang.Math.fma(_t10, _t7, _t11 * _t5), java.lang.Math.fma(_t11, _t7, _t10 * -_t5), java.lang.Math.fma(_t9, _t7, _t12 * _t5), java.lang.Math.fma(_t12, _t7, _t9 * -_t5), 0.0, 0.0, 0.0, 0.0);
    }

    /** {@code makeRotationXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleDualQuat makeRotationXYZ_mulAdd(double angleX, double angleY, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        return new DoubleDualQuat(((_t10) * (_t7) + (_t11 * _t5)), ((_t11) * (_t7) - (_t10 * _t5)), ((_t9) * (_t7) + (_t12 * _t5)), ((_t12) * (_t7) - (_t9 * _t5)), 0.0, 0.0, 0.0, 0.0);
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
    public static DoubleDualQuat makeRotationXZY(double angleX, double angleZ, double angleY) {
        if (!Math.useFma()) return makeRotationXZY_mulAdd(angleX, angleZ, angleY);
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        return new DoubleDualQuat(java.lang.Math.fma(_t10, _t7, -(_t11 * _t5)), java.lang.Math.fma(_t12, _t5, -(_t9 * _t7)), java.lang.Math.fma(_t10, _t5, _t11 * _t7), java.lang.Math.fma(_t9, _t5, _t12 * _t7), 0.0, 0.0, 0.0, 0.0);
    }

    /** {@code makeRotationXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleDualQuat makeRotationXZY_mulAdd(double angleX, double angleZ, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        return new DoubleDualQuat(((_t10) * (_t7) - (_t11 * _t5)), ((_t12) * (_t5) - (_t9 * _t7)), ((_t10) * (_t5) + (_t11 * _t7)), ((_t9) * (_t5) + (_t12 * _t7)), 0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Create a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting dual quaternion
     */
    public static DoubleDualQuat makeRotationY(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleDualQuat(0.0, _t1, 0.0, Math.cosFromSin(_t1, _t0), 0.0, 0.0, 0.0, 0.0);
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
    public static DoubleDualQuat makeRotationYXZ(double angleY, double angleX, double angleZ) {
        if (!Math.useFma()) return makeRotationYXZ_mulAdd(angleY, angleX, angleZ);
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        return new DoubleDualQuat(java.lang.Math.fma(_t10, _t7, _t11 * _t5), java.lang.Math.fma(_t11, _t7, -(_t10 * _t5)), java.lang.Math.fma(_t12, _t5, -(_t9 * _t7)), java.lang.Math.fma(_t9, _t5, _t12 * _t7), 0.0, 0.0, 0.0, 0.0);
    }

    /** {@code makeRotationYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleDualQuat makeRotationYXZ_mulAdd(double angleY, double angleX, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        return new DoubleDualQuat(((_t10) * (_t7) + (_t11 * _t5)), ((_t11) * (_t7) - (_t10 * _t5)), ((_t12) * (_t5) - (_t9 * _t7)), ((_t9) * (_t5) + (_t12 * _t7)), 0.0, 0.0, 0.0, 0.0);
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
    public static DoubleDualQuat makeRotationYZX(double angleY, double angleZ, double angleX) {
        if (!Math.useFma()) return makeRotationYZX_mulAdd(angleY, angleZ, angleX);
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t5, _t2);
        double _t7 = Math.cosFromSin(_t3, _t0);
        double _t8 = Math.cosFromSin(_t4, _t1);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t8;
        double _t11 = _t4 * _t7;
        double _t12 = _t7 * _t8;
        return new DoubleDualQuat(java.lang.Math.fma(_t9, _t6, _t12 * _t5), java.lang.Math.fma(_t10, _t6, _t11 * _t5), java.lang.Math.fma(_t11, _t6, _t10 * -_t5), java.lang.Math.fma(_t12, _t6, _t9 * -_t5), 0.0, 0.0, 0.0, 0.0);
    }

    /** {@code makeRotationYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleDualQuat makeRotationYZX_mulAdd(double angleY, double angleZ, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t5, _t2);
        double _t7 = Math.cosFromSin(_t3, _t0);
        double _t8 = Math.cosFromSin(_t4, _t1);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t8;
        double _t11 = _t4 * _t7;
        double _t12 = _t7 * _t8;
        return new DoubleDualQuat(((_t9) * (_t6) + (_t12 * _t5)), ((_t10) * (_t6) + (_t11 * _t5)), ((_t11) * (_t6) - (_t10 * _t5)), ((_t12) * (_t6) - (_t9 * _t5)), 0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Create a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting dual quaternion
     */
    public static DoubleDualQuat makeRotationZ(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleDualQuat(0.0, 0.0, _t1, Math.cosFromSin(_t1, _t0), 0.0, 0.0, 0.0, 0.0);
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
    public static DoubleDualQuat makeRotationZXY(double angleZ, double angleX, double angleY) {
        if (!Math.useFma()) return makeRotationZXY_mulAdd(angleZ, angleX, angleY);
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        return new DoubleDualQuat(java.lang.Math.fma(_t10, _t7, _t11 * -_t5), java.lang.Math.fma(_t9, _t7, _t12 * _t5), java.lang.Math.fma(_t10, _t5, _t11 * _t7), java.lang.Math.fma(_t12, _t7, _t9 * -_t5), 0.0, 0.0, 0.0, 0.0);
    }

    /** {@code makeRotationZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleDualQuat makeRotationZXY_mulAdd(double angleZ, double angleX, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        return new DoubleDualQuat(((_t10) * (_t7) - (_t11 * _t5)), ((_t9) * (_t7) + (_t12 * _t5)), ((_t10) * (_t5) + (_t11 * _t7)), ((_t12) * (_t7) - (_t9 * _t5)), 0.0, 0.0, 0.0, 0.0);
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
    public static DoubleDualQuat makeRotationZYX(double angleZ, double angleY, double angleX) {
        if (!Math.useFma()) return makeRotationZYX_mulAdd(angleZ, angleY, angleX);
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t6 * _t7;
        return new DoubleDualQuat(java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), java.lang.Math.fma(_t10, _t8, _t11 * _t5), java.lang.Math.fma(_t11, _t8, -(_t10 * _t5)), java.lang.Math.fma(_t9, _t5, _t12 * _t8), 0.0, 0.0, 0.0, 0.0);
    }

    /** {@code makeRotationZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleDualQuat makeRotationZYX_mulAdd(double angleZ, double angleY, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t6 * _t7;
        return new DoubleDualQuat(((_t12) * (_t5) - (_t9 * _t8)), ((_t10) * (_t8) + (_t11 * _t5)), ((_t11) * (_t8) - (_t10 * _t5)), ((_t9) * (_t5) + (_t12 * _t8)), 0.0, 0.0, 0.0, 0.0);
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
    public DoubleDualQuat preRotate(DoubleQuat rotation) {
        return preRotate(rotation.x(), rotation.y(), rotation.z(), rotation.w());
    }

    /** Private tail of {@code preRotate}; reached only through it. */
    private DoubleDualQuat preRotate_s3241000a_tail_fma(double rotationX, double rotationW, double rotationY, double rotationZ, double _sfx0, double _sfx1, double _sfx2, double _sfx3) {
        double _sfx4 = java.lang.Math.fma(rotationX, this.dW, rotationW * this.dX) + java.lang.Math.fma(rotationY, this.dZ, -rotationZ * this.dY);
        double _sfx5 = java.lang.Math.fma(rotationY, this.dW, rotationZ * this.dX) + java.lang.Math.fma(rotationW, this.dY, -rotationX * this.dZ);
        double _sfx6 = java.lang.Math.fma(rotationX, this.dY, rotationW * this.dZ) + java.lang.Math.fma(rotationZ, this.dW, -rotationY * this.dX);
        double _sfx7 = java.lang.Math.fma(rotationW, this.dW, -rotationX * this.dX) - java.lang.Math.fma(rotationY, this.dY, rotationZ * this.dZ);
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code preRotate}; reached only through it. */
    private DoubleDualQuat preRotate_s3241000a_tail_mulAdd(double rotationX, double rotationW, double rotationY, double rotationZ, double _sfx0, double _sfx1, double _sfx2, double _sfx3) {
        double _sfx4 = ((rotationX) * (this.dW) + (rotationW * this.dX)) + ((rotationY) * (this.dZ) - (rotationZ * this.dY));
        double _sfx5 = ((rotationY) * (this.dW) + (rotationZ * this.dX)) + ((rotationW) * (this.dY) - (rotationX * this.dZ));
        double _sfx6 = ((rotationX) * (this.dY) + (rotationW * this.dZ)) + ((rotationZ) * (this.dW) - (rotationY * this.dX));
        double _sfx7 = ((rotationW) * (this.dW) - (rotationX * this.dX)) - ((rotationY) * (this.dY) + (rotationZ * this.dZ));
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
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
    public DoubleDualQuat preRotate(double rotationX, double rotationY, double rotationZ, double rotationW) {
        if (Math.useFma()) {
            double _sfx0 = java.lang.Math.fma(rotationX, this.rW, rotationW * this.rX) + java.lang.Math.fma(rotationY, this.rZ, -rotationZ * this.rY);
            double _sfx1 = java.lang.Math.fma(rotationY, this.rW, rotationZ * this.rX) + java.lang.Math.fma(rotationW, this.rY, -rotationX * this.rZ);
            double _sfx2 = java.lang.Math.fma(rotationX, this.rY, rotationW * this.rZ) + java.lang.Math.fma(rotationZ, this.rW, -rotationY * this.rX);
            double _sfx3 = java.lang.Math.fma(rotationW, this.rW, -rotationX * this.rX) - java.lang.Math.fma(rotationY, this.rY, rotationZ * this.rZ);
            return preRotate_s3241000a_tail_fma(rotationX, rotationW, rotationY, rotationZ, _sfx0, _sfx1, _sfx2, _sfx3);
        } else {
            double _sfx0 = ((rotationX) * (this.rW) + (rotationW * this.rX)) + ((rotationY) * (this.rZ) - (rotationZ * this.rY));
            double _sfx1 = ((rotationY) * (this.rW) + (rotationZ * this.rX)) + ((rotationW) * (this.rY) - (rotationX * this.rZ));
            double _sfx2 = ((rotationX) * (this.rY) + (rotationW * this.rZ)) + ((rotationZ) * (this.rW) - (rotationY * this.rX));
            double _sfx3 = ((rotationW) * (this.rW) - (rotationX * this.rX)) - ((rotationY) * (this.rY) + (rotationZ * this.rZ));
            return preRotate_s3241000a_tail_mulAdd(rotationX, rotationW, rotationY, rotationZ, _sfx0, _sfx1, _sfx2, _sfx3);
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
    public DoubleDualQuat rotate(DoubleQuat rotation) {
        return rotate(rotation.x(), rotation.y(), rotation.z(), rotation.w());
    }

    /** Private tail of {@code rotate}; reached only through it. */
    private DoubleDualQuat rotate_s3241000a_tail_fma(double rotationX, double rotationW, double rotationZ, double rotationY, double _sfx0, double _sfx1, double _sfx2, double _sfx3) {
        double _sfx4 = java.lang.Math.fma(rotationX, this.dW, rotationW * this.dX) + java.lang.Math.fma(rotationZ, this.dY, -rotationY * this.dZ);
        double _sfx5 = java.lang.Math.fma(rotationX, this.dZ, rotationW * this.dY) + java.lang.Math.fma(rotationY, this.dW, -rotationZ * this.dX);
        double _sfx6 = java.lang.Math.fma(rotationY, this.dX, rotationZ * this.dW) + java.lang.Math.fma(rotationW, this.dZ, -rotationX * this.dY);
        double _sfx7 = java.lang.Math.fma(rotationW, this.dW, -rotationX * this.dX) - java.lang.Math.fma(rotationY, this.dY, rotationZ * this.dZ);
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code rotate}; reached only through it. */
    private DoubleDualQuat rotate_s3241000a_tail_mulAdd(double rotationX, double rotationW, double rotationZ, double rotationY, double _sfx0, double _sfx1, double _sfx2, double _sfx3) {
        double _sfx4 = ((rotationX) * (this.dW) + (rotationW * this.dX)) + ((rotationZ) * (this.dY) - (rotationY * this.dZ));
        double _sfx5 = ((rotationX) * (this.dZ) + (rotationW * this.dY)) + ((rotationY) * (this.dW) - (rotationZ * this.dX));
        double _sfx6 = ((rotationY) * (this.dX) + (rotationZ * this.dW)) + ((rotationW) * (this.dZ) - (rotationX * this.dY));
        double _sfx7 = ((rotationW) * (this.dW) - (rotationX * this.dX)) - ((rotationY) * (this.dY) + (rotationZ * this.dZ));
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
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
    public DoubleDualQuat rotate(double rotationX, double rotationY, double rotationZ, double rotationW) {
        if (Math.useFma()) {
            double _sfx0 = java.lang.Math.fma(rotationX, this.rW, rotationW * this.rX) + java.lang.Math.fma(rotationZ, this.rY, -rotationY * this.rZ);
            double _sfx1 = java.lang.Math.fma(rotationX, this.rZ, rotationW * this.rY) + java.lang.Math.fma(rotationY, this.rW, -rotationZ * this.rX);
            double _sfx2 = java.lang.Math.fma(rotationY, this.rX, rotationZ * this.rW) + java.lang.Math.fma(rotationW, this.rZ, -rotationX * this.rY);
            double _sfx3 = java.lang.Math.fma(rotationW, this.rW, -rotationX * this.rX) - java.lang.Math.fma(rotationY, this.rY, rotationZ * this.rZ);
            return rotate_s3241000a_tail_fma(rotationX, rotationW, rotationZ, rotationY, _sfx0, _sfx1, _sfx2, _sfx3);
        } else {
            double _sfx0 = ((rotationX) * (this.rW) + (rotationW * this.rX)) + ((rotationZ) * (this.rY) - (rotationY * this.rZ));
            double _sfx1 = ((rotationX) * (this.rZ) + (rotationW * this.rY)) + ((rotationY) * (this.rW) - (rotationZ * this.rX));
            double _sfx2 = ((rotationY) * (this.rX) + (rotationZ * this.rW)) + ((rotationW) * (this.rZ) - (rotationX * this.rY));
            double _sfx3 = ((rotationW) * (this.rW) - (rotationX * this.rX)) - ((rotationY) * (this.rY) + (rotationZ * this.rZ));
            return rotate_s3241000a_tail_mulAdd(rotationX, rotationW, rotationZ, rotationY, _sfx0, _sfx1, _sfx2, _sfx3);
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
    public DoubleDualQuat rotateAxis(double angle, Double3 axis) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        if (Math.useFma()) return rotateAxis_fma(angle, axisX, axisY, axisZ);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ);
    }

    /** Private tail of {@code rotateAxis}; reached only through it. */
    private DoubleDualQuat rotateAxis_s56e2ebbb_tail_fma(double _t5, double _t2, double _t3, double _t4, double _sfx0, double _sfx1, double _sfx2, double _sfx3) {
        double _sfx4 = java.lang.Math.fma(this.dX, _t5, this.dW * _t2) + java.lang.Math.fma(this.dY, _t3, -(this.dZ * _t4));
        double _sfx5 = java.lang.Math.fma(this.dY, _t5, this.dZ * _t2) + java.lang.Math.fma(this.dW, _t4, -this.dX * _t3);
        double _sfx6 = java.lang.Math.fma(this.dX, _t4, this.dW * _t3) + java.lang.Math.fma(this.dZ, _t5, -(this.dY * _t2));
        double _sfx7 = java.lang.Math.fma(this.dW, _t5, -this.dX * _t2) - java.lang.Math.fma(this.dY, _t4, this.dZ * _t3);
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /** Private tail of {@code rotateAxis}; reached only through it. */
    private DoubleDualQuat rotateAxis_s56e2ebbb_tail_mulAdd(double _t5, double _t2, double _t3, double _t4, double _sfx0, double _sfx1, double _sfx2, double _sfx3) {
        double _sfx4 = ((this.dX) * (_t5) + (this.dW * _t2)) + ((this.dY) * (_t3) - (this.dZ * _t4));
        double _sfx5 = ((this.dY) * (_t5) + (this.dZ * _t2)) + ((this.dW) * (_t4) - (this.dX * _t3));
        double _sfx6 = ((this.dX) * (_t4) + (this.dW * _t3)) + ((this.dZ) * (_t5) - (this.dY * _t2));
        double _sfx7 = ((this.dW) * (_t5) - (this.dX * _t2)) - ((this.dY) * (_t4) + (this.dZ * _t3));
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
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
    public DoubleDualQuat rotateAxis(double angle, double axisX, double axisY, double axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        if (Math.useFma()) return rotateAxis_fma(angle, axisX, axisY, axisZ);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateAxis_fma(double angle, double axisX, double axisY, double axisZ) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        double _sfx0 = java.lang.Math.fma(this.rX, _t5, this.rW * _t2) + java.lang.Math.fma(this.rY, _t3, -(this.rZ * _t4));
        double _sfx1 = java.lang.Math.fma(this.rY, _t5, this.rZ * _t2) + java.lang.Math.fma(this.rW, _t4, -this.rX * _t3);
        double _sfx2 = java.lang.Math.fma(this.rX, _t4, this.rW * _t3) + java.lang.Math.fma(this.rZ, _t5, -(this.rY * _t2));
        double _sfx3 = java.lang.Math.fma(this.rW, _t5, -this.rX * _t2) - java.lang.Math.fma(this.rY, _t4, this.rZ * _t3);
        return rotateAxis_s56e2ebbb_tail_fma(_t5, _t2, _t3, _t4, _sfx0, _sfx1, _sfx2, _sfx3);
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateAxis_mulAdd(double angle, double axisX, double axisY, double axisZ) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        double _sfx0 = ((this.rX) * (_t5) + (this.rW * _t2)) + ((this.rY) * (_t3) - (this.rZ * _t4));
        double _sfx1 = ((this.rY) * (_t5) + (this.rZ * _t2)) + ((this.rW) * (_t4) - (this.rX * _t3));
        double _sfx2 = ((this.rX) * (_t4) + (this.rW * _t3)) + ((this.rZ) * (_t5) - (this.rY * _t2));
        double _sfx3 = ((this.rW) * (_t5) - (this.rX * _t2)) - ((this.rY) * (_t4) + (this.rZ * _t3));
        return rotateAxis_s56e2ebbb_tail_mulAdd(_t5, _t2, _t3, _t4, _sfx0, _sfx1, _sfx2, _sfx3);
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
    public DoubleDualQuat rotateX(double angle) {
        if (!Math.useFma()) return rotateX_mulAdd(angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        return new DoubleDualQuat(java.lang.Math.fma(this.rX, _t2, this.rW * _t1), java.lang.Math.fma(this.rY, _t2, this.rZ * _t1), java.lang.Math.fma(this.rZ, _t2, -(this.rY * _t1)), java.lang.Math.fma(this.rW, _t2, -(this.rX * _t1)), java.lang.Math.fma(this.dX, _t2, this.dW * _t1), java.lang.Math.fma(this.dY, _t2, this.dZ * _t1), java.lang.Math.fma(this.dZ, _t2, -(this.dY * _t1)), java.lang.Math.fma(this.dW, _t2, -(this.dX * _t1)));
    }

    /** {@code rotateX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateX_mulAdd(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        return new DoubleDualQuat(((this.rX) * (_t2) + (this.rW * _t1)), ((this.rY) * (_t2) + (this.rZ * _t1)), ((this.rZ) * (_t2) - (this.rY * _t1)), ((this.rW) * (_t2) - (this.rX * _t1)), ((this.dX) * (_t2) + (this.dW * _t1)), ((this.dY) * (_t2) + (this.dZ * _t1)), ((this.dZ) * (_t2) - (this.dY * _t1)), ((this.dW) * (_t2) - (this.dX * _t1)));
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY}
     * and {@code rotateZYX}; reached only through them.
     */
    private DoubleDualQuat rotateXYZ_s361a4ff5_tail_fma(double _t22, double _t20, double _t21, double _t19, double _sfx0, double _sfx1) {
        double _sfx2 = java.lang.Math.fma(this.rX, _t22, this.rW * _t20) + java.lang.Math.fma(this.rZ, _t21, -(this.rY * _t19));
        double _sfx3 = java.lang.Math.fma(this.rW, _t21, -this.rX * _t19) - java.lang.Math.fma(this.rY, _t22, this.rZ * _t20);
        double _sfx4 = java.lang.Math.fma(this.dX, _t21, this.dW * _t19) + java.lang.Math.fma(this.dY, _t20, -(this.dZ * _t22));
        double _sfx5 = java.lang.Math.fma(this.dY, _t21, this.dZ * _t19) + java.lang.Math.fma(this.dW, _t22, -this.dX * _t20);
        double _sfx6 = java.lang.Math.fma(this.dX, _t22, this.dW * _t20) + java.lang.Math.fma(this.dZ, _t21, -(this.dY * _t19));
        double _sfx7 = java.lang.Math.fma(this.dW, _t21, -this.dX * _t19) - java.lang.Math.fma(this.dY, _t22, this.dZ * _t20);
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY}
     * and {@code rotateZYX}; reached only through them.
     */
    private DoubleDualQuat rotateXYZ_s361a4ff5_tail_mulAdd(double _t22, double _t20, double _t21, double _t19, double _sfx0, double _sfx1) {
        double _sfx2 = ((this.rX) * (_t22) + (this.rW * _t20)) + ((this.rZ) * (_t21) - (this.rY * _t19));
        double _sfx3 = ((this.rW) * (_t21) - (this.rX * _t19)) - ((this.rY) * (_t22) + (this.rZ * _t20));
        double _sfx4 = ((this.dX) * (_t21) + (this.dW * _t19)) + ((this.dY) * (_t20) - (this.dZ * _t22));
        double _sfx5 = ((this.dY) * (_t21) + (this.dZ * _t19)) + ((this.dW) * (_t22) - (this.dX * _t20));
        double _sfx6 = ((this.dX) * (_t22) + (this.dW * _t20)) + ((this.dZ) * (_t21) - (this.dY * _t19));
        double _sfx7 = ((this.dW) * (_t21) - (this.dX * _t19)) - ((this.dY) * (_t22) + (this.dZ * _t20));
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7);
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
    public DoubleDualQuat rotateXYZ(double angleX, double angleY, double angleZ) {
        if (!Math.useFma()) return rotateXYZ_mulAdd(angleX, angleY, angleZ);
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t14 = _t6 * _t7;
        double _t19 = java.lang.Math.fma(_t10, _t8, _t11 * _t5);
        double _t20 = java.lang.Math.fma(_t9, _t8, _t14 * _t5);
        double _t21 = java.lang.Math.fma(_t14, _t8, _t9 * -_t5);
        double _t22 = java.lang.Math.fma(_t11, _t8, _t10 * -_t5);
        double _sfx0 = java.lang.Math.fma(this.rX, _t21, this.rW * _t19) + java.lang.Math.fma(this.rY, _t20, -(this.rZ * _t22));
        double _sfx1 = java.lang.Math.fma(this.rY, _t21, this.rZ * _t19) + java.lang.Math.fma(this.rW, _t22, -this.rX * _t20);
        return rotateXYZ_s361a4ff5_tail_fma(_t22, _t20, _t21, _t19, _sfx0, _sfx1);
    }

    /** {@code rotateXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateXYZ_mulAdd(double angleX, double angleY, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t14 = _t6 * _t7;
        double _t19 = ((_t10) * (_t8) + (_t11 * _t5));
        double _t20 = ((_t9) * (_t8) + (_t14 * _t5));
        double _t21 = ((_t14) * (_t8) - (_t9 * _t5));
        double _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        double _sfx0 = ((this.rX) * (_t21) + (this.rW * _t19)) + ((this.rY) * (_t20) - (this.rZ * _t22));
        double _sfx1 = ((this.rY) * (_t21) + (this.rZ * _t19)) + ((this.rW) * (_t22) - (this.rX * _t20));
        return rotateXYZ_s361a4ff5_tail_mulAdd(_t22, _t20, _t21, _t19, _sfx0, _sfx1);
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
    public DoubleDualQuat rotateXZY(double angleX, double angleZ, double angleY) {
        if (!Math.useFma()) return rotateXZY_mulAdd(angleX, angleZ, angleY);
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t6 * _t7;
        double _t19 = java.lang.Math.fma(_t9, _t5, _t12 * _t8);
        double _t20 = java.lang.Math.fma(_t10, _t5, _t11 * _t8);
        double _t21 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        double _t22 = java.lang.Math.fma(_t12, _t5, -(_t9 * _t8));
        double _sfx0 = java.lang.Math.fma(this.rX, _t19, this.rW * _t21) + java.lang.Math.fma(this.rY, _t20, -(this.rZ * _t22));
        double _sfx1 = java.lang.Math.fma(this.rY, _t19, this.rZ * _t21) + java.lang.Math.fma(this.rW, _t22, -this.rX * _t20);
        return rotateXYZ_s361a4ff5_tail_fma(_t22, _t20, _t19, _t21, _sfx0, _sfx1);
    }

    /** {@code rotateXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateXZY_mulAdd(double angleX, double angleZ, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t6 * _t7;
        double _t19 = ((_t9) * (_t5) + (_t12 * _t8));
        double _t20 = ((_t10) * (_t5) + (_t11 * _t8));
        double _t21 = ((_t10) * (_t8) - (_t11 * _t5));
        double _t22 = ((_t12) * (_t5) - (_t9 * _t8));
        double _sfx0 = ((this.rX) * (_t19) + (this.rW * _t21)) + ((this.rY) * (_t20) - (this.rZ * _t22));
        double _sfx1 = ((this.rY) * (_t19) + (this.rZ * _t21)) + ((this.rW) * (_t22) - (this.rX * _t20));
        return rotateXYZ_s361a4ff5_tail_mulAdd(_t22, _t20, _t19, _t21, _sfx0, _sfx1);
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
    public DoubleDualQuat rotateY(double angle) {
        if (!Math.useFma()) return rotateY_mulAdd(angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        return new DoubleDualQuat(java.lang.Math.fma(this.rX, _t2, -(this.rZ * _t1)), java.lang.Math.fma(this.rY, _t2, this.rW * _t1), java.lang.Math.fma(this.rX, _t1, this.rZ * _t2), java.lang.Math.fma(this.rW, _t2, -(this.rY * _t1)), java.lang.Math.fma(this.dX, _t2, -(this.dZ * _t1)), java.lang.Math.fma(this.dY, _t2, this.dW * _t1), java.lang.Math.fma(this.dX, _t1, this.dZ * _t2), java.lang.Math.fma(this.dW, _t2, -(this.dY * _t1)));
    }

    /** {@code rotateY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateY_mulAdd(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        return new DoubleDualQuat(((this.rX) * (_t2) - (this.rZ * _t1)), ((this.rY) * (_t2) + (this.rW * _t1)), ((this.rX) * (_t1) + (this.rZ * _t2)), ((this.rW) * (_t2) - (this.rY * _t1)), ((this.dX) * (_t2) - (this.dZ * _t1)), ((this.dY) * (_t2) + (this.dW * _t1)), ((this.dX) * (_t1) + (this.dZ * _t2)), ((this.dW) * (_t2) - (this.dY * _t1)));
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
    public DoubleDualQuat rotateYXZ(double angleY, double angleX, double angleZ) {
        if (!Math.useFma()) return rotateYXZ_mulAdd(angleY, angleX, angleZ);
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t6 * _t7;
        double _t19 = java.lang.Math.fma(_t9, _t5, _t12 * _t8);
        double _t20 = java.lang.Math.fma(_t10, _t8, _t11 * _t5);
        double _t21 = java.lang.Math.fma(_t12, _t5, -(_t9 * _t8));
        double _t22 = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        double _sfx0 = java.lang.Math.fma(this.rX, _t19, this.rW * _t20) + java.lang.Math.fma(this.rY, _t21, -(this.rZ * _t22));
        double _sfx1 = java.lang.Math.fma(this.rY, _t19, this.rZ * _t20) + java.lang.Math.fma(this.rW, _t22, -this.rX * _t21);
        return rotateXYZ_s361a4ff5_tail_fma(_t22, _t21, _t19, _t20, _sfx0, _sfx1);
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateYXZ_mulAdd(double angleY, double angleX, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t6 * _t7;
        double _t19 = ((_t9) * (_t5) + (_t12 * _t8));
        double _t20 = ((_t10) * (_t8) + (_t11 * _t5));
        double _t21 = ((_t12) * (_t5) - (_t9 * _t8));
        double _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        double _sfx0 = ((this.rX) * (_t19) + (this.rW * _t20)) + ((this.rY) * (_t21) - (this.rZ * _t22));
        double _sfx1 = ((this.rY) * (_t19) + (this.rZ * _t20)) + ((this.rW) * (_t22) - (this.rX * _t21));
        return rotateXYZ_s361a4ff5_tail_mulAdd(_t22, _t21, _t19, _t20, _sfx0, _sfx1);
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
    public DoubleDualQuat rotateYZX(double angleY, double angleZ, double angleX) {
        if (!Math.useFma()) return rotateYZX_mulAdd(angleY, angleZ, angleX);
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t4 * _t6;
        double _t11 = _t3 * _t7;
        double _t14 = _t6 * _t7;
        double _t19 = java.lang.Math.fma(_t9, _t8, _t14 * _t5);
        double _t20 = java.lang.Math.fma(_t11, _t8, _t10 * _t5);
        double _t21 = java.lang.Math.fma(_t14, _t8, _t9 * -_t5);
        double _t22 = java.lang.Math.fma(_t10, _t8, _t11 * -_t5);
        double _sfx0 = java.lang.Math.fma(this.rX, _t21, this.rW * _t19) + java.lang.Math.fma(this.rY, _t22, -(this.rZ * _t20));
        double _sfx1 = java.lang.Math.fma(this.rY, _t21, this.rZ * _t19) + java.lang.Math.fma(this.rW, _t20, -this.rX * _t22);
        return rotateXYZ_s361a4ff5_tail_fma(_t20, _t22, _t21, _t19, _sfx0, _sfx1);
    }

    /** {@code rotateYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateYZX_mulAdd(double angleY, double angleZ, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t4 * _t6;
        double _t11 = _t3 * _t7;
        double _t14 = _t6 * _t7;
        double _t19 = ((_t9) * (_t8) + (_t14 * _t5));
        double _t20 = ((_t11) * (_t8) + (_t10 * _t5));
        double _t21 = ((_t14) * (_t8) - (_t9 * _t5));
        double _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        double _sfx0 = ((this.rX) * (_t21) + (this.rW * _t19)) + ((this.rY) * (_t22) - (this.rZ * _t20));
        double _sfx1 = ((this.rY) * (_t21) + (this.rZ * _t19)) + ((this.rW) * (_t20) - (this.rX * _t22));
        return rotateXYZ_s361a4ff5_tail_mulAdd(_t20, _t22, _t21, _t19, _sfx0, _sfx1);
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
    public DoubleDualQuat rotateZ(double angle) {
        if (!Math.useFma()) return rotateZ_mulAdd(angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        return new DoubleDualQuat(java.lang.Math.fma(this.rX, _t2, this.rY * _t1), java.lang.Math.fma(this.rY, _t2, -(this.rX * _t1)), java.lang.Math.fma(this.rZ, _t2, this.rW * _t1), java.lang.Math.fma(this.rW, _t2, -(this.rZ * _t1)), java.lang.Math.fma(this.dX, _t2, this.dY * _t1), java.lang.Math.fma(this.dY, _t2, -(this.dX * _t1)), java.lang.Math.fma(this.dZ, _t2, this.dW * _t1), java.lang.Math.fma(this.dW, _t2, -(this.dZ * _t1)));
    }

    /** {@code rotateZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateZ_mulAdd(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        return new DoubleDualQuat(((this.rX) * (_t2) + (this.rY * _t1)), ((this.rY) * (_t2) - (this.rX * _t1)), ((this.rZ) * (_t2) + (this.rW * _t1)), ((this.rW) * (_t2) - (this.rZ * _t1)), ((this.dX) * (_t2) + (this.dY * _t1)), ((this.dY) * (_t2) - (this.dX * _t1)), ((this.dZ) * (_t2) + (this.dW * _t1)), ((this.dW) * (_t2) - (this.dZ * _t1)));
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
    public DoubleDualQuat rotateZXY(double angleZ, double angleX, double angleY) {
        if (!Math.useFma()) return rotateZXY_mulAdd(angleZ, angleX, angleY);
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t14 = _t6 * _t7;
        double _t19 = java.lang.Math.fma(_t10, _t5, _t11 * _t8);
        double _t20 = java.lang.Math.fma(_t9, _t8, _t14 * _t5);
        double _t21 = java.lang.Math.fma(_t14, _t8, _t9 * -_t5);
        double _t22 = java.lang.Math.fma(_t10, _t8, _t11 * -_t5);
        double _sfx0 = java.lang.Math.fma(this.rX, _t21, this.rW * _t22) + java.lang.Math.fma(this.rY, _t19, -(this.rZ * _t20));
        double _sfx1 = java.lang.Math.fma(this.rY, _t21, this.rZ * _t22) + java.lang.Math.fma(this.rW, _t20, -this.rX * _t19);
        return rotateXYZ_s361a4ff5_tail_fma(_t20, _t19, _t21, _t22, _sfx0, _sfx1);
    }

    /** {@code rotateZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateZXY_mulAdd(double angleZ, double angleX, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t14 = _t6 * _t7;
        double _t19 = ((_t10) * (_t5) + (_t11 * _t8));
        double _t20 = ((_t9) * (_t8) + (_t14 * _t5));
        double _t21 = ((_t14) * (_t8) - (_t9 * _t5));
        double _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        double _sfx0 = ((this.rX) * (_t21) + (this.rW * _t22)) + ((this.rY) * (_t19) - (this.rZ * _t20));
        double _sfx1 = ((this.rY) * (_t21) + (this.rZ * _t22)) + ((this.rW) * (_t20) - (this.rX * _t19));
        return rotateXYZ_s361a4ff5_tail_mulAdd(_t20, _t19, _t21, _t22, _sfx0, _sfx1);
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
    public DoubleDualQuat rotateZYX(double angleZ, double angleY, double angleX) {
        if (!Math.useFma()) return rotateZYX_mulAdd(angleZ, angleY, angleX);
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t4 * _t6;
        double _t11 = _t3 * _t7;
        double _t12 = _t6 * _t7;
        double _t19 = java.lang.Math.fma(_t9, _t5, _t12 * _t8);
        double _t20 = java.lang.Math.fma(_t11, _t8, _t10 * _t5);
        double _t21 = java.lang.Math.fma(_t12, _t5, -(_t9 * _t8));
        double _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        double _sfx0 = java.lang.Math.fma(this.rX, _t19, this.rW * _t21) + java.lang.Math.fma(this.rY, _t22, -(this.rZ * _t20));
        double _sfx1 = java.lang.Math.fma(this.rY, _t19, this.rZ * _t21) + java.lang.Math.fma(this.rW, _t20, -this.rX * _t22);
        return rotateXYZ_s361a4ff5_tail_fma(_t20, _t22, _t19, _t21, _sfx0, _sfx1);
    }

    /** {@code rotateZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat rotateZYX_mulAdd(double angleZ, double angleY, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t4 * _t6;
        double _t11 = _t3 * _t7;
        double _t12 = _t6 * _t7;
        double _t19 = ((_t9) * (_t5) + (_t12 * _t8));
        double _t20 = ((_t11) * (_t8) + (_t10 * _t5));
        double _t21 = ((_t12) * (_t5) - (_t9 * _t8));
        double _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        double _sfx0 = ((this.rX) * (_t19) + (this.rW * _t21)) + ((this.rY) * (_t22) - (this.rZ * _t20));
        double _sfx1 = ((this.rY) * (_t19) + (this.rZ * _t21)) + ((this.rW) * (_t20) - (this.rX * _t22));
        return rotateXYZ_s361a4ff5_tail_mulAdd(_t20, _t22, _t19, _t21, _sfx0, _sfx1);
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
    public DoubleDualQuat translate(Double3 translation) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        if (!Math.useFma()) return translate_mulAdd(translationX, translationY, translationZ);
        double _t0 = -this.rZ;
        double _t1 = -this.rX;
        double _t2 = -this.rY;
        double _t3 = 0.5 * translationZ;
        double _t4 = 0.5 * translationY;
        double _t5 = 0.5 * translationX;
        return new DoubleDualQuat(this.rX, this.rY, this.rZ, this.rW, java.lang.Math.fma(this.rY, _t3, java.lang.Math.fma(_t0, _t4, java.lang.Math.fma(this.rW, _t5, this.dX))), java.lang.Math.fma(this.rW, _t4, java.lang.Math.fma(_t1, _t3, java.lang.Math.fma(this.rZ, _t5, this.dY))), java.lang.Math.fma(this.rX, _t4, java.lang.Math.fma(this.rW, _t3, java.lang.Math.fma(_t2, _t5, this.dZ))), java.lang.Math.fma(_t1, _t5, java.lang.Math.fma(_t2, _t4, java.lang.Math.fma(_t0, _t3, this.dW))));
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
    public DoubleDualQuat translate(double translationX, double translationY, double translationZ) {
        if (!Math.useFma()) return translate_mulAdd(translationX, translationY, translationZ);
        double _t0 = -this.rZ;
        double _t1 = -this.rX;
        double _t2 = -this.rY;
        double _t3 = 0.5 * translationZ;
        double _t4 = 0.5 * translationY;
        double _t5 = 0.5 * translationX;
        return new DoubleDualQuat(this.rX, this.rY, this.rZ, this.rW, java.lang.Math.fma(this.rY, _t3, java.lang.Math.fma(_t0, _t4, java.lang.Math.fma(this.rW, _t5, this.dX))), java.lang.Math.fma(this.rW, _t4, java.lang.Math.fma(_t1, _t3, java.lang.Math.fma(this.rZ, _t5, this.dY))), java.lang.Math.fma(this.rX, _t4, java.lang.Math.fma(this.rW, _t3, java.lang.Math.fma(_t2, _t5, this.dZ))), java.lang.Math.fma(_t1, _t5, java.lang.Math.fma(_t2, _t4, java.lang.Math.fma(_t0, _t3, this.dW))));
    }

    /** {@code translate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat translate_mulAdd(double translationX, double translationY, double translationZ) {
        double _t0 = -this.rZ;
        double _t1 = -this.rX;
        double _t2 = -this.rY;
        double _t3 = 0.5 * translationZ;
        double _t4 = 0.5 * translationY;
        double _t5 = 0.5 * translationX;
        return new DoubleDualQuat(this.rX, this.rY, this.rZ, this.rW, ((this.rY) * (_t3) + (((_t0) * (_t4) + (((this.rW) * (_t5) + (this.dX)))))), ((this.rW) * (_t4) + (((_t1) * (_t3) + (((this.rZ) * (_t5) + (this.dY)))))), ((this.rX) * (_t4) + (((this.rW) * (_t3) + (((_t2) * (_t5) + (this.dZ)))))), ((_t1) * (_t5) + (((_t2) * (_t4) + (((_t0) * (_t3) + (this.dW)))))));
    }

    /**
     * Transform {@code p} by this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @return the resulting vector
     */
    public Double3 transform(Double3 p) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        if (Math.useFma()) {
            return transform_s1948e2d8_tail_fma(pZ, pY, 2.0 * java.lang.Math.fma(pY, this.rX, pX * -this.rY), 2.0 * java.lang.Math.fma(pX, this.rZ, pZ * -this.rX), pX);
        } else {
            return transform_s1948e2d8_tail_mulAdd(pZ, pY, 2.0 * ((pY) * (this.rX) - (pX * this.rY)), 2.0 * ((pX) * (this.rZ) - (pZ * this.rX)), pX);
        }
    }

    /** Private tail of {@code transform}; reached only through it. */
    private Double3 transform_s1948e2d8_tail_fma(double pZ, double pY, double _t9, double _t10, double pX) {
        double _t11 = 2.0 * java.lang.Math.fma(pZ, this.rY, pY * -this.rZ);
        return new Double3(java.lang.Math.fma(this.rY, _t9, java.lang.Math.fma(-this.rZ, _t10, java.lang.Math.fma(this.rW, _t11, java.lang.Math.fma(2.0, java.lang.Math.fma(this.rY, this.dZ, -this.rZ * this.dY) + java.lang.Math.fma(this.rW, this.dX, -this.rX * this.dW), pX)))), java.lang.Math.fma(this.rZ, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, java.lang.Math.fma(2.0, java.lang.Math.fma(this.rZ, this.dX, -this.rX * this.dZ) + java.lang.Math.fma(this.rW, this.dY, -this.rY * this.dW), pY)))), java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rY, _t11, java.lang.Math.fma(this.rW, _t9, java.lang.Math.fma(2.0, java.lang.Math.fma(this.rX, this.dY, -this.rY * this.dX) + java.lang.Math.fma(this.rW, this.dZ, -this.rZ * this.dW), pZ)))));
    }

    /** Private tail of {@code transform}; reached only through it. */
    private Double3 transform_s1948e2d8_tail_mulAdd(double pZ, double pY, double _t9, double _t10, double pX) {
        double _t11 = 2.0 * ((pZ) * (this.rY) - (pY * this.rZ));
        return new Double3(((this.rY) * (_t9) + (((((this.rW) * (_t11) + (((2.0) * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW))) + (pX))))) - (this.rZ) * (_t10)))), ((this.rZ) * (_t11) + (((((this.rW) * (_t10) + (((2.0) * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW))) + (pY))))) - (this.rX) * (_t9)))), ((this.rX) * (_t10) + (((((this.rW) * (_t9) + (((2.0) * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW))) + (pZ))))) - (this.rY) * (_t11)))));
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
    public Double3 transform(double pX, double pY, double pZ) {
        if (Math.useFma()) {
            return transform_s1948e2d8_tail_fma(pZ, pY, 2.0 * java.lang.Math.fma(pY, this.rX, pX * -this.rY), 2.0 * java.lang.Math.fma(pX, this.rZ, pZ * -this.rX), pX);
        } else {
            return transform_s1948e2d8_tail_mulAdd(pZ, pY, 2.0 * ((pY) * (this.rX) - (pX * this.rY)), 2.0 * ((pX) * (this.rZ) - (pZ * this.rX)), pX);
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
    public Double3 transformDirection(Double3 v) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        if (!Math.useFma()) return transformDirection_mulAdd(vX, vY, vZ);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * java.lang.Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Double3(java.lang.Math.fma(this.rY, _t9, java.lang.Math.fma(-this.rZ, _t10, java.lang.Math.fma(this.rW, _t11, vX))), java.lang.Math.fma(this.rZ, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vY))), java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rY, _t11, java.lang.Math.fma(this.rW, _t9, vZ))));
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
    public Double3 transformDirection(double vX, double vY, double vZ) {
        if (!Math.useFma()) return transformDirection_mulAdd(vX, vY, vZ);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * java.lang.Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Double3(java.lang.Math.fma(this.rY, _t9, java.lang.Math.fma(-this.rZ, _t10, java.lang.Math.fma(this.rW, _t11, vX))), java.lang.Math.fma(this.rZ, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vY))), java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rY, _t11, java.lang.Math.fma(this.rW, _t9, vZ))));
    }

    /** {@code transformDirection} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformDirection_mulAdd(double vX, double vY, double vZ) {
        double _t9 = 2.0 * ((this.rX) * (vY) - (this.rY * vX));
        double _t10 = 2.0 * ((this.rZ) * (vX) - (this.rX * vZ));
        double _t11 = 2.0 * ((this.rY) * (vZ) - (this.rZ * vY));
        return new Double3(((this.rY) * (_t9) + (((((this.rW) * (_t11) + (vX))) - (this.rZ) * (_t10)))), ((this.rZ) * (_t11) + (((((this.rW) * (_t10) + (vY))) - (this.rX) * (_t9)))), ((this.rX) * (_t10) + (((((this.rW) * (_t9) + (vZ))) - (this.rY) * (_t11)))));
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
    public Double3 transformDirectionInverse(Double3 v) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        if (!Math.useFma()) return transformDirectionInverse_mulAdd(vX, vY, vZ);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, vZ, -(this.rZ * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.rY, vX, -(this.rX * vY));
        double _t11 = 2.0 * java.lang.Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Double3(java.lang.Math.fma(this.rZ, _t9, java.lang.Math.fma(-this.rY, _t10, java.lang.Math.fma(this.rW, _t11, vX))), java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rZ, _t11, java.lang.Math.fma(this.rW, _t9, vY))), java.lang.Math.fma(this.rY, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vZ))));
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
    public Double3 transformDirectionInverse(double vX, double vY, double vZ) {
        if (!Math.useFma()) return transformDirectionInverse_mulAdd(vX, vY, vZ);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, vZ, -(this.rZ * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.rY, vX, -(this.rX * vY));
        double _t11 = 2.0 * java.lang.Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Double3(java.lang.Math.fma(this.rZ, _t9, java.lang.Math.fma(-this.rY, _t10, java.lang.Math.fma(this.rW, _t11, vX))), java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rZ, _t11, java.lang.Math.fma(this.rW, _t9, vY))), java.lang.Math.fma(this.rY, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vZ))));
    }

    /** {@code transformDirectionInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformDirectionInverse_mulAdd(double vX, double vY, double vZ) {
        double _t9 = 2.0 * ((this.rX) * (vZ) - (this.rZ * vX));
        double _t10 = 2.0 * ((this.rY) * (vX) - (this.rX * vY));
        double _t11 = 2.0 * ((this.rZ) * (vY) - (this.rY * vZ));
        return new Double3(((this.rZ) * (_t9) + (((((this.rW) * (_t11) + (vX))) - (this.rY) * (_t10)))), ((this.rX) * (_t10) + (((((this.rW) * (_t9) + (vY))) - (this.rZ) * (_t11)))), ((this.rY) * (_t11) + (((((this.rW) * (_t10) + (vZ))) - (this.rX) * (_t9)))));
    }

    /**
     * Transform {@code p} by the inverse of this dual quaternion, returning the result as a value.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @return the resulting vector
     */
    public Double3 transformInverse(Double3 p) {
        return transformInverse(p.x(), p.y(), p.z());
    }

    /** Private tail of {@code transformInverse}; reached only through it. */
    private Double3 transformInverse_s1948e2d8_tail_fma(double _t21, double _t22, double _t23) {
        double _t33 = 2.0 * java.lang.Math.fma(this.rX, _t21, -(this.rZ * _t22));
        double _t34 = 2.0 * java.lang.Math.fma(this.rY, _t22, -(this.rX * _t23));
        double _t35 = 2.0 * java.lang.Math.fma(this.rZ, _t23, -(this.rY * _t21));
        return new Double3(java.lang.Math.fma(this.rZ, _t33, java.lang.Math.fma(-this.rY, _t34, java.lang.Math.fma(this.rW, _t35, _t22))), java.lang.Math.fma(this.rX, _t34, java.lang.Math.fma(-this.rZ, _t35, java.lang.Math.fma(this.rW, _t33, _t23))), java.lang.Math.fma(this.rY, _t35, java.lang.Math.fma(-this.rX, _t33, java.lang.Math.fma(this.rW, _t34, _t21))));
    }

    /** Private tail of {@code transformInverse}; reached only through it. */
    private Double3 transformInverse_s1948e2d8_tail_mulAdd(double _t21, double _t22, double _t23) {
        double _t33 = 2.0 * ((this.rX) * (_t21) - (this.rZ * _t22));
        double _t34 = 2.0 * ((this.rY) * (_t22) - (this.rX * _t23));
        double _t35 = 2.0 * ((this.rZ) * (_t23) - (this.rY * _t21));
        return new Double3(((this.rZ) * (_t33) + (((((this.rW) * (_t35) + (_t22))) - (this.rY) * (_t34)))), ((this.rX) * (_t34) + (((((this.rW) * (_t33) + (_t23))) - (this.rZ) * (_t35)))), ((this.rY) * (_t35) + (((((this.rW) * (_t34) + (_t21))) - (this.rX) * (_t33)))));
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
    public Double3 transformInverse(double pX, double pY, double pZ) {
        if (Math.useFma()) {
            return transformInverse_s1948e2d8_tail_fma(java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, this.rZ * -this.dW), pZ), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW), pX), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW), pY));
        } else {
            return transformInverse_s1948e2d8_tail_mulAdd(((pZ) - (2.0) * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW)))), ((pX) - (2.0) * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW)))), ((pY) - (2.0) * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW)))));
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
    public Double3 transformPosition(Double3 p) {
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
    public Double3 transformPosition(double pX, double pY, double pZ) {
        if (Math.useFma()) {
            return transform_s1948e2d8_tail_fma(pZ, pY, 2.0 * java.lang.Math.fma(pY, this.rX, pX * -this.rY), 2.0 * java.lang.Math.fma(pX, this.rZ, pZ * -this.rX), pX);
        } else {
            return transform_s1948e2d8_tail_mulAdd(pZ, pY, 2.0 * ((pY) * (this.rX) - (pX * this.rY)), 2.0 * ((pX) * (this.rZ) - (pZ * this.rX)), pX);
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
    public Double3 transformPositionInverse(Double3 p) {
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
    public Double3 transformPositionInverse(double pX, double pY, double pZ) {
        if (Math.useFma()) {
            return transformInverse_s1948e2d8_tail_fma(java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.dY, -(this.rY * this.dX)) + java.lang.Math.fma(this.rW, this.dZ, this.rZ * -this.dW), pZ), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.dZ, -(this.rZ * this.dY)) + java.lang.Math.fma(this.rW, this.dX, this.rX * -this.dW), pX), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rZ, this.dX, -(this.rX * this.dZ)) + java.lang.Math.fma(this.rW, this.dY, this.rY * -this.dW), pY));
        } else {
            return transformInverse_s1948e2d8_tail_mulAdd(((pZ) - (2.0) * (((this.rX) * (this.dY) - (this.rY * this.dX)) + ((this.rW) * (this.dZ) - (this.rZ * this.dW)))), ((pX) - (2.0) * (((this.rY) * (this.dZ) - (this.rZ * this.dY)) + ((this.rW) * (this.dX) - (this.rX * this.dW)))), ((pY) - (2.0) * (((this.rZ) * (this.dX) - (this.rX * this.dZ)) + ((this.rW) * (this.dY) - (this.rY * this.dW)))));
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
    public Double3 transformVector(Double3 v) {
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
    public Double3 transformVector(double vX, double vY, double vZ) {
        if (!Math.useFma()) return transformDirection_mulAdd(vX, vY, vZ);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * java.lang.Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Double3(java.lang.Math.fma(this.rY, _t9, java.lang.Math.fma(-this.rZ, _t10, java.lang.Math.fma(this.rW, _t11, vX))), java.lang.Math.fma(this.rZ, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vY))), java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rY, _t11, java.lang.Math.fma(this.rW, _t9, vZ))));
    }

    /**
     * {@return a copy with the {@code rX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rX} component
     */
    public DoubleDualQuat withRX(double v) {
        return new DoubleDualQuat(v, rY, rZ, rW, dX, dY, dZ, dW);
    }

    /**
     * {@return a copy with the {@code rY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rY} component
     */
    public DoubleDualQuat withRY(double v) {
        return new DoubleDualQuat(rX, v, rZ, rW, dX, dY, dZ, dW);
    }

    /**
     * {@return a copy with the {@code rZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rZ} component
     */
    public DoubleDualQuat withRZ(double v) {
        return new DoubleDualQuat(rX, rY, v, rW, dX, dY, dZ, dW);
    }

    /**
     * {@return a copy with the {@code rW} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rW} component
     */
    public DoubleDualQuat withRW(double v) {
        return new DoubleDualQuat(rX, rY, rZ, v, dX, dY, dZ, dW);
    }

    /**
     * {@return a copy with the {@code dX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code dX} component
     */
    public DoubleDualQuat withDX(double v) {
        return new DoubleDualQuat(rX, rY, rZ, rW, v, dY, dZ, dW);
    }

    /**
     * {@return a copy with the {@code dY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code dY} component
     */
    public DoubleDualQuat withDY(double v) {
        return new DoubleDualQuat(rX, rY, rZ, rW, dX, v, dZ, dW);
    }

    /**
     * {@return a copy with the {@code dZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code dZ} component
     */
    public DoubleDualQuat withDZ(double v) {
        return new DoubleDualQuat(rX, rY, rZ, rW, dX, dY, v, dW);
    }

    /**
     * {@return a copy with the {@code dW} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code dW} component
     */
    public DoubleDualQuat withDW(double v) {
        return new DoubleDualQuat(rX, rY, rZ, rW, dX, dY, dZ, v);
    }

    @Override public String toString() {
        return "DoubleDualQuat(" + this.rX + ", " + this.rY + ", " + this.rZ + ", " + this.rW + ", " + this.dX + ", " + this.dY + ", " + this.dZ + ", " + this.dW + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleDualQuat)) return false;
        DoubleDualQuat o = (DoubleDualQuat) obj;
        return Double.doubleToLongBits(rX) == Double.doubleToLongBits(o.rX)
            && Double.doubleToLongBits(rY) == Double.doubleToLongBits(o.rY)
            && Double.doubleToLongBits(rZ) == Double.doubleToLongBits(o.rZ)
            && Double.doubleToLongBits(rW) == Double.doubleToLongBits(o.rW)
            && Double.doubleToLongBits(dX) == Double.doubleToLongBits(o.dX)
            && Double.doubleToLongBits(dY) == Double.doubleToLongBits(o.dY)
            && Double.doubleToLongBits(dZ) == Double.doubleToLongBits(o.dZ)
            && Double.doubleToLongBits(dW) == Double.doubleToLongBits(o.dW);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int) (Double.doubleToLongBits(rX) ^ (Double.doubleToLongBits(rX) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(rY) ^ (Double.doubleToLongBits(rY) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(rZ) ^ (Double.doubleToLongBits(rZ) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(rW) ^ (Double.doubleToLongBits(rW) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(dX) ^ (Double.doubleToLongBits(dX) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(dY) ^ (Double.doubleToLongBits(dY) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(dZ) ^ (Double.doubleToLongBits(dZ) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(dW) ^ (Double.doubleToLongBits(dW) >>> 32));
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Double.isFinite(rX)
            && Double.isFinite(rY)
            && Double.isFinite(rZ)
            && Double.isFinite(rW)
            && Double.isFinite(dX)
            && Double.isFinite(dY)
            && Double.isFinite(dZ)
            && Double.isFinite(dW);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Double.isNaN(rX)
            || Double.isNaN(rY)
            || Double.isNaN(rZ)
            || Double.isNaN(rW)
            || Double.isNaN(dX)
            || Double.isNaN(dY)
            || Double.isNaN(dZ)
            || Double.isNaN(dW);
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
    public boolean equalsEpsilon(DoubleDualQuat other, double epsilon) {
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
        static final DoubleDualQuatBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleDualQuatBbOpsUnsafe()
            : new DoubleDualQuatBbOpsApi();
        static final DoubleDualQuatRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleDualQuatRawOpsUnsafe()
            : new DoubleDualQuatRawOpsApi();
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
    public double[] store(double[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     */
    public static DoubleDualQuat load(double[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        double _c6 = src[offset + 6];
        double _c7 = src[offset + 7];
        return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     */
    public static DoubleDualQuat load(double[] src) { return load(src, 0); }

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
     * With the UNSAFE backend, offsets into direct buffers and native-order heap
     * {@code ByteBuffer}s are not bounds-checked; the API backend goes through the buffer's own
     * {@code get}/{@code put} methods and performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     */
    public static DoubleDualQuat load(DoubleBuffer buf) {
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
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     */
    public static DoubleDualQuat loadAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleDualQuat loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleDualQuat r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
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
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 64);
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
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     */
    public static DoubleDualQuat load(ByteBuffer buf) {
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
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     */
    public static DoubleDualQuat loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleDualQuat loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleDualQuat r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 64);
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
    public DoubleDualQuat storeUnsafe(long address) {
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
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static DoubleDualQuat loadUnsafe(long address) {
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
        dest[offset] = (float) this.rX;
        dest[offset + 1] = (float) this.rY;
        dest[offset + 2] = (float) this.rZ;
        dest[offset + 3] = (float) this.rW;
        dest[offset + 4] = (float) this.dX;
        dest[offset + 5] = (float) this.dY;
        dest[offset + 6] = (float) this.dZ;
        dest[offset + 7] = (float) this.dW;
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
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     */
    public static DoubleDualQuat load(float[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        double _c6 = src[offset + 6];
        double _c7 = src[offset + 7];
        return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    /**
     * Load the elements from the given array, converting each element from {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     */
    public static DoubleDualQuat load(float[] src) { return load(src, 0); }

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
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 8);
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
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     */
    public static DoubleDualQuat load(FloatBuffer buf) {
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
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     */
    public static DoubleDualQuat loadAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleDualQuat loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleDualQuat r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 8);
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
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 32);
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
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     */
    public static DoubleDualQuat loadFloat(ByteBuffer buf) {
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
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     */
    public static DoubleDualQuat loadFloatAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleDualQuat loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleDualQuat r = StoreLoad.BB_OPS.loadFloatAbsolute(pos, buf);
        buf.position(pos + 32);
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
    public DoubleDualQuat storeFloatUnsafe(long address) {
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
     * @return a new {@code DoubleDualQuat} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static DoubleDualQuat loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(address);
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

    private abstract static class DoubleDualQuatBbOps {
        public abstract DoubleBuffer storeAbsolute(DoubleDualQuat self, int index, DoubleBuffer buf);
        public abstract DoubleDualQuat loadAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeAbsolute(DoubleDualQuat self, int index, ByteBuffer buf);
        public abstract DoubleDualQuat loadAbsolute(int index, ByteBuffer buf);
        public abstract FloatBuffer storeAbsolute(DoubleDualQuat self, int index, FloatBuffer buf);
        public abstract DoubleDualQuat loadAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeFloatAbsolute(DoubleDualQuat self, int index, ByteBuffer buf);
        public abstract DoubleDualQuat loadFloatAbsolute(int index, ByteBuffer buf);
    }

    private static final class DoubleDualQuatBbOpsApi extends DoubleDualQuatBbOps {
        public DoubleBuffer storeAbsolute(DoubleDualQuat self, int index, DoubleBuffer buf) {
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

        public DoubleDualQuat loadAbsolute(int index, DoubleBuffer buf) {
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(index + 2);
            double _c3 = buf.get(index + 3);
            double _c4 = buf.get(index + 4);
            double _c5 = buf.get(index + 5);
            double _c6 = buf.get(index + 6);
            double _c7 = buf.get(index + 7);
            return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeAbsolute(DoubleDualQuat self, int index, ByteBuffer buf) {
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

        public DoubleDualQuat loadAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getDouble(index);
            double _c1 = buf.getDouble(index + 8);
            double _c2 = buf.getDouble(index + 16);
            double _c3 = buf.getDouble(index + 24);
            double _c4 = buf.getDouble(index + 32);
            double _c5 = buf.getDouble(index + 40);
            double _c6 = buf.getDouble(index + 48);
            double _c7 = buf.getDouble(index + 56);
            return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public FloatBuffer storeAbsolute(DoubleDualQuat self, int index, FloatBuffer buf) {
            buf.put(index, (float) self.rX);
            buf.put(index + 1, (float) self.rY);
            buf.put(index + 2, (float) self.rZ);
            buf.put(index + 3, (float) self.rW);
            buf.put(index + 4, (float) self.dX);
            buf.put(index + 5, (float) self.dY);
            buf.put(index + 6, (float) self.dZ);
            buf.put(index + 7, (float) self.dW);
            return buf;
        }

        public DoubleDualQuat loadAbsolute(int index, FloatBuffer buf) {
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(index + 2);
            double _c3 = buf.get(index + 3);
            double _c4 = buf.get(index + 4);
            double _c5 = buf.get(index + 5);
            double _c6 = buf.get(index + 6);
            double _c7 = buf.get(index + 7);
            return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeFloatAbsolute(DoubleDualQuat self, int index, ByteBuffer buf) {
            buf.putFloat(index, (float) self.rX);
            buf.putFloat(index + 4, (float) self.rY);
            buf.putFloat(index + 8, (float) self.rZ);
            buf.putFloat(index + 12, (float) self.rW);
            buf.putFloat(index + 16, (float) self.dX);
            buf.putFloat(index + 20, (float) self.dY);
            buf.putFloat(index + 24, (float) self.dZ);
            buf.putFloat(index + 28, (float) self.dW);
            return buf;
        }

        public DoubleDualQuat loadFloatAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getFloat(index);
            double _c1 = buf.getFloat(index + 4);
            double _c2 = buf.getFloat(index + 8);
            double _c3 = buf.getFloat(index + 12);
            double _c4 = buf.getFloat(index + 16);
            double _c5 = buf.getFloat(index + 20);
            double _c6 = buf.getFloat(index + 24);
            double _c7 = buf.getFloat(index + 28);
            return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }
    }

    private static final class DoubleDualQuatBbOpsUnsafe extends DoubleDualQuatBbOps {
        private static final DoubleDualQuatBbOpsApi API = new DoubleDualQuatBbOpsApi();

        public DoubleBuffer storeAbsolute(DoubleDualQuat self, int index, DoubleBuffer buf) {
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

        public DoubleDualQuat loadAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            double _c4 = _U.getDouble(address + 32L);
            double _c5 = _U.getDouble(address + 40L);
            double _c6 = _U.getDouble(address + 48L);
            double _c7 = _U.getDouble(address + 56L);
            return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeAbsolute(DoubleDualQuat self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
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

        private static ByteBuffer storeAbsoluteElse(DoubleDualQuat self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeAbsolute(self, index, buf);
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

        public DoubleDualQuat loadAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            double _c4 = _U.getDouble(address + 32L);
            double _c5 = _U.getDouble(address + 40L);
            double _c6 = _U.getDouble(address + 48L);
            double _c7 = _U.getDouble(address + 56L);
            return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static DoubleDualQuat loadAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8L);
            double _c2 = _U.getDouble(bytes, address + 16L);
            double _c3 = _U.getDouble(bytes, address + 24L);
            double _c4 = _U.getDouble(bytes, address + 32L);
            double _c5 = _U.getDouble(bytes, address + 40L);
            double _c6 = _U.getDouble(bytes, address + 48L);
            double _c7 = _U.getDouble(bytes, address + 56L);
            return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public FloatBuffer storeAbsolute(DoubleDualQuat self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            _U.putFloat(address, (float) self.rX);
            _U.putFloat(address + 4L, (float) self.rY);
            _U.putFloat(address + 8L, (float) self.rZ);
            _U.putFloat(address + 12L, (float) self.rW);
            _U.putFloat(address + 16L, (float) self.dX);
            _U.putFloat(address + 20L, (float) self.dY);
            _U.putFloat(address + 24L, (float) self.dZ);
            _U.putFloat(address + 28L, (float) self.dW);
            return buf;
        }

        public DoubleDualQuat loadAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            double _c4 = _U.getFloat(address + 16L);
            double _c5 = _U.getFloat(address + 20L);
            double _c6 = _U.getFloat(address + 24L);
            double _c7 = _U.getFloat(address + 28L);
            return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public ByteBuffer storeFloatAbsolute(DoubleDualQuat self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeFloatAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(address, (float) self.rX);
            _U.putFloat(address + 4L, (float) self.rY);
            _U.putFloat(address + 8L, (float) self.rZ);
            _U.putFloat(address + 12L, (float) self.rW);
            _U.putFloat(address + 16L, (float) self.dX);
            _U.putFloat(address + 20L, (float) self.dY);
            _U.putFloat(address + 24L, (float) self.dZ);
            _U.putFloat(address + 28L, (float) self.dW);
            return buf;
        }

        private static ByteBuffer storeFloatAbsoluteElse(DoubleDualQuat self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeFloatAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(bytes, address, (float) self.rX);
            _U.putFloat(bytes, address + 4L, (float) self.rY);
            _U.putFloat(bytes, address + 8L, (float) self.rZ);
            _U.putFloat(bytes, address + 12L, (float) self.rW);
            _U.putFloat(bytes, address + 16L, (float) self.dX);
            _U.putFloat(bytes, address + 20L, (float) self.dY);
            _U.putFloat(bytes, address + 24L, (float) self.dZ);
            _U.putFloat(bytes, address + 28L, (float) self.dW);
            return buf;
        }

        public DoubleDualQuat loadFloatAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadFloatAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            double _c4 = _U.getFloat(address + 16L);
            double _c5 = _U.getFloat(address + 20L);
            double _c6 = _U.getFloat(address + 24L);
            double _c7 = _U.getFloat(address + 28L);
            return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        private static DoubleDualQuat loadFloatAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadFloatAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4L);
            double _c2 = _U.getFloat(bytes, address + 8L);
            double _c3 = _U.getFloat(bytes, address + 12L);
            double _c4 = _U.getFloat(bytes, address + 16L);
            double _c5 = _U.getFloat(bytes, address + 20L);
            double _c6 = _U.getFloat(bytes, address + 24L);
            double _c7 = _U.getFloat(bytes, address + 28L);
            return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }
    }

    private abstract static class DoubleDualQuatRawOps {
        public abstract DoubleDualQuat storeUnsafe(DoubleDualQuat self, long address);
        public abstract DoubleDualQuat loadUnsafe(long address);
        public abstract DoubleDualQuat storeFloatUnsafe(DoubleDualQuat self, long address);
        public abstract DoubleDualQuat loadFloatUnsafe(long address);
    }

    /**
     * API-backend raw-address kernels of a target without {@code java.lang.foreign.MemorySegment}:
     * every method throws {@link UnsupportedOperationException} - without a segment view there is no
     * portable way to dereference a raw {@code long} address. Select {@code StoreLoadBackend.UNSAFE}
     * to use the raw-address forms on this variant.
     */
    private static final class DoubleDualQuatRawOpsApi extends DoubleDualQuatRawOps {
        public DoubleDualQuat storeUnsafe(DoubleDualQuat self, long address) {
            throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
        }

        public DoubleDualQuat loadUnsafe(long address) {
            throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
        }

        public DoubleDualQuat storeFloatUnsafe(DoubleDualQuat self, long address) {
            throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
        }

        public DoubleDualQuat loadFloatUnsafe(long address) {
            throw new UnsupportedOperationException("raw-address store/load requires storeLoadBackend=UNSAFE on this variant: without java.lang.foreign.MemorySegment (JDK 9 / JDK 17 targets) the API backend cannot dereference a raw long address");
        }
    }

    private static final class DoubleDualQuatRawOpsUnsafe extends DoubleDualQuatRawOps {
        public DoubleDualQuat storeUnsafe(DoubleDualQuat self, long address) {
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

        public DoubleDualQuat storeHeap(DoubleDualQuat self, byte[] bytes, long address) {
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

        public DoubleDualQuat loadUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            double _c4 = _U.getDouble(address + 32L);
            double _c5 = _U.getDouble(address + 40L);
            double _c6 = _U.getDouble(address + 48L);
            double _c7 = _U.getDouble(address + 56L);
            return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public DoubleDualQuat loadHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8L);
            double _c2 = _U.getDouble(bytes, address + 16L);
            double _c3 = _U.getDouble(bytes, address + 24L);
            double _c4 = _U.getDouble(bytes, address + 32L);
            double _c5 = _U.getDouble(bytes, address + 40L);
            double _c6 = _U.getDouble(bytes, address + 48L);
            double _c7 = _U.getDouble(bytes, address + 56L);
            return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public DoubleDualQuat storeFloatUnsafe(DoubleDualQuat self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.rX);
            _U.putFloat(address + 4L, (float) self.rY);
            _U.putFloat(address + 8L, (float) self.rZ);
            _U.putFloat(address + 12L, (float) self.rW);
            _U.putFloat(address + 16L, (float) self.dX);
            _U.putFloat(address + 20L, (float) self.dY);
            _U.putFloat(address + 24L, (float) self.dZ);
            _U.putFloat(address + 28L, (float) self.dW);
            return self;
        }

        public DoubleDualQuat storeFloatHeap(DoubleDualQuat self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.rX);
            _U.putFloat(bytes, address + 4L, (float) self.rY);
            _U.putFloat(bytes, address + 8L, (float) self.rZ);
            _U.putFloat(bytes, address + 12L, (float) self.rW);
            _U.putFloat(bytes, address + 16L, (float) self.dX);
            _U.putFloat(bytes, address + 20L, (float) self.dY);
            _U.putFloat(bytes, address + 24L, (float) self.dZ);
            _U.putFloat(bytes, address + 28L, (float) self.dW);
            return self;
        }

        public DoubleDualQuat loadFloatUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            double _c4 = _U.getFloat(address + 16L);
            double _c5 = _U.getFloat(address + 20L);
            double _c6 = _U.getFloat(address + 24L);
            double _c7 = _U.getFloat(address + 28L);
            return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }

        public DoubleDualQuat loadFloatHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4L);
            double _c2 = _U.getFloat(bytes, address + 8L);
            double _c3 = _U.getFloat(bytes, address + 12L);
            double _c4 = _U.getFloat(bytes, address + 16L);
            double _c5 = _U.getFloat(bytes, address + 20L);
            double _c6 = _U.getFloat(bytes, address + 24L);
            double _c7 = _U.getFloat(bytes, address + 28L);
            return new DoubleDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
        }
    }
}
