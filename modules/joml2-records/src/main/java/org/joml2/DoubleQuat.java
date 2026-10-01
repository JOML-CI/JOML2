// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;
import java.nio.ByteOrder;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

/**
 * Immutable quaternion of double-precision {@code double} components.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance.
 * <p>
 * A rotation is a unit quaternion. The operations that apply this quaternion as a rotation - the
 * transforms, the matrix conversions, the Euler angles, {@code angleTo} and {@code rotateTowards} -
 * assume unit length and do not divide it out. A value that has drifted from unit length (after
 * many multiplications, say) gives wrong results rather than an error: {@code normalize} it first.
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
 * @param w the {@code w} component
 */
public record DoubleQuat(double x, double y, double z, double w) {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 32;

    /** The zero quaternion (all components 0). */
    public static final DoubleQuat ZERO = new DoubleQuat(0, 0, 0, 0);

    /** The identity quaternion. */
    public static final DoubleQuat IDENTITY = new DoubleQuat();

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component
     * @param y the {@code y} component
     * @param z the {@code z} component
     * @param w the {@code w} component
     */
    public DoubleQuat(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Create a new instance initialized to the identity.
     * <p>
     * Valid input: the method reads no input.
     */
    public DoubleQuat() {
        this(0, 0, 0, 1);
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
    public static DoubleQuat[] newArray(int length) {
        DoubleQuat[] a = new DoubleQuat[length];
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
    public static DoubleQuat[] newArray(int length, DoubleQuat init) {
        java.util.Objects.requireNonNull(init, "init");
        DoubleQuat[] a = new DoubleQuat[length];
        java.util.Arrays.fill(a, init);
        return a;
    }

    /** {@return the {@code x} component} <p>Valid input: any value, NaN and the infinities included. */
    public double x() { return x; }

    /** {@return the {@code y} component} <p>Valid input: any value, NaN and the infinities included. */
    public double y() { return y; }

    /** {@return the {@code z} component} <p>Valid input: any value, NaN and the infinities included. */
    public double z() { return z; }

    /** {@return the {@code w} component} <p>Valid input: any value, NaN and the infinities included. */
    public double w() { return w; }

    /**
     * Create a rotation uniformly distributed over all rotations, drawing the 3 samples of
     * {@code makeUniformRotation} from {@code rng}, each with {@code rng.nextDouble()}, in
     * parameter order.
     * <p>
     * Valid input: any value.
     *
     * @param rng the random number generator to draw the 3 samples from
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRandomRotation(java.util.Random rng) {
        return makeUniformRotation(rng.nextDouble(), rng.nextDouble(), rng.nextDouble());
    }

    /**
     * Invert this quaternion, returning the result as a value.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @return the resulting quaternion
     */
    public DoubleQuat invert() {
        if (Math.useFma()) {
            double _t3_inv = 1.0 / java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
            return new DoubleQuat(-(this.x * _t3_inv), -(this.y * _t3_inv), -(this.z * _t3_inv), this.w * _t3_inv);
        } else {
            double _t3_inv = 1.0 / ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
            return new DoubleQuat(-(this.x * _t3_inv), -(this.y * _t3_inv), -(this.z * _t3_inv), this.w * _t3_inv);
        }
    }

    /**
     * Compute the inverse of the product of this quaternion and {@code other}, i.e.
     * {@code (this * other)^-1}, returning the result as a value.
     * <p>
     * Valid input: this quaternion must be non-zero; {@code other} must be non-zero.
     *
     * @param other the right factor of the product
     * @return the resulting quaternion
     */
    public DoubleQuat invertProduct(DoubleQuat other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        double otherW = other.w;
        if (!Math.useFma()) return invertProduct_mulAdd(otherX, otherY, otherZ, otherW);
        double _t20 = java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherZ, this.y, -(otherY * this.z));
        double _t21 = java.lang.Math.fma(otherW, this.w, -otherX * this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z);
        double _t22 = java.lang.Math.fma(otherY, this.x, otherZ * this.w) + java.lang.Math.fma(otherW, this.z, -otherX * this.y);
        double _t23 = java.lang.Math.fma(otherX, this.z, otherW * this.y) + java.lang.Math.fma(otherY, this.w, -(otherZ * this.x));
        double _t27_inv = 1.0 / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        return new DoubleQuat(-(_t20 * _t27_inv), -(_t23 * _t27_inv), -(_t22 * _t27_inv), _t21 * _t27_inv);
    }

    /**
     * Compute the inverse of the product of this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}), returning the result as a value.
     * <p>
     * Valid input: this quaternion must be non-zero; {@code (otherX, otherY, otherZ, otherW)} must
     * be non-zero.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting quaternion
     */
    public DoubleQuat invertProduct(double otherX, double otherY, double otherZ, double otherW) {
        if (!Math.useFma()) return invertProduct_mulAdd(otherX, otherY, otherZ, otherW);
        double _t20 = java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherZ, this.y, -(otherY * this.z));
        double _t21 = java.lang.Math.fma(otherW, this.w, -otherX * this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z);
        double _t22 = java.lang.Math.fma(otherY, this.x, otherZ * this.w) + java.lang.Math.fma(otherW, this.z, -otherX * this.y);
        double _t23 = java.lang.Math.fma(otherX, this.z, otherW * this.y) + java.lang.Math.fma(otherY, this.w, -(otherZ * this.x));
        double _t27_inv = 1.0 / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        return new DoubleQuat(-(_t20 * _t27_inv), -(_t23 * _t27_inv), -(_t22 * _t27_inv), _t21 * _t27_inv);
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat invertProduct_mulAdd(double otherX, double otherY, double otherZ, double otherW) {
        double _t20 = ((otherX) * (this.w) + (otherW * this.x)) + ((otherZ) * (this.y) - (otherY * this.z));
        double _t21 = ((otherW) * (this.w) - (otherX * this.x)) - ((otherY) * (this.y) + (otherZ * this.z));
        double _t22 = ((otherY) * (this.x) + (otherZ * this.w)) + ((otherW) * (this.z) - (otherX * this.y));
        double _t23 = ((otherX) * (this.z) + (otherW * this.y)) + ((otherY) * (this.w) - (otherZ * this.x));
        double _t27_inv = 1.0 / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
        return new DoubleQuat(-(_t20 * _t27_inv), -(_t23 * _t27_inv), -(_t22 * _t27_inv), _t21 * _t27_inv);
    }

    /**
     * Add {@code other} to this quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to add
     * @return the resulting quaternion
     */
    public DoubleQuat add(DoubleQuat other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        double otherW = other.w;
        return new DoubleQuat(otherX + this.x, otherY + this.y, otherZ + this.z, otherW + this.w);
    }

    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this quaternion,
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting quaternion
     */
    public DoubleQuat add(double otherX, double otherY, double otherZ, double otherW) {
        return new DoubleQuat(otherX + this.x, otherY + this.y, otherZ + this.z, otherW + this.w);
    }

    /**
     * Multiply each component of this quaternion by {@code scalar}, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting quaternion
     */
    public DoubleQuat mul(double scalar) {
        return new DoubleQuat(scalar * this.x, scalar * this.y, scalar * this.z, scalar * this.w);
    }

    /**
     * Negate this quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting quaternion
     */
    public DoubleQuat negate() {
        return new DoubleQuat(-this.x, -this.y, -this.z, -this.w);
    }

    /**
     * Subtract {@code other} from this quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to subtract
     * @return the resulting quaternion
     */
    public DoubleQuat sub(DoubleQuat other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        double otherW = other.w;
        return new DoubleQuat(this.x - otherX, this.y - otherY, this.z - otherZ, this.w - otherW);
    }

    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this
     * quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting quaternion
     */
    public DoubleQuat sub(double otherX, double otherY, double otherZ, double otherW) {
        return new DoubleQuat(this.x - otherX, this.y - otherY, this.z - otherZ, this.w - otherW);
    }

    /**
     * Create the unit quaternion
     * {@code (sqrt(1 - u1) sin(2 PI u2), sqrt(1 - u1) cos(2 PI u2), sqrt(u1) sin(2 PI u3), sqrt(u1) cos(2 PI u3))},
     * Shoemake's construction: samples uniformly distributed in {@code [0, 1)} give a rotation
     * uniformly distributed over all rotations ({@code makeRandomRotation} draws them from a
     * {@link java.util.Random}).
     * <p>
     * Valid input: {@code u1} must lie in {@code [0, 1]}.
     *
     * @param u1 the sample that splits the unit length between {@code (x, y)} and {@code (z, w)},
     *        uniformly distributed in {@code [0, 1)} for a uniformly distributed rotation
     * @param u2 the fraction of a full turn of {@code (x, y)}, uniformly distributed in
     *        {@code [0, 1)} for a uniformly distributed rotation
     * @param u3 the fraction of a full turn of {@code (z, w)}, uniformly distributed in
     *        {@code [0, 1)} for a uniformly distributed rotation
     * @return the resulting quaternion
     */
    public static DoubleQuat makeUniformRotation(double u1, double u2, double u3) {
        double _t0 = java.lang.Math.sqrt(u1);
        double _t1 = u2 * 6.283185307179586;
        double _t3 = u3 * 6.283185307179586;
        double _t4 = Math.sin(_t1);
        double _t5 = java.lang.Math.sqrt(1.0 - u1);
        double _t6 = Math.sin(_t3);
        return new DoubleQuat(_t4 * _t5, Math.cosFromSin(_t4, _t1) * _t5, _t6 * _t0, Math.cosFromSin(_t6, _t3) * _t0);
    }

    /**
     * Return a quaternion with the values of {@code v}: that quaternion itself (an immutable value
     * needs no copy).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the quaternion to copy
     * @return the resulting quaternion
     */
    public DoubleQuat set(DoubleQuat v) {
        double vX = v.x;
        double vY = v.y;
        double vZ = v.z;
        double vW = v.w;
        return new DoubleQuat(vX, vY, vZ, vW);
    }

    /**
     * Create a new quaternion from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the quaternion {@code (vX, vY, vZ, vW)}
     * @param vY the {@code y} component of the quaternion {@code (vX, vY, vZ, vW)}
     * @param vZ the {@code z} component of the quaternion {@code (vX, vY, vZ, vW)}
     * @param vW the {@code w} component of the quaternion {@code (vX, vY, vZ, vW)}
     * @return the resulting quaternion
     */
    public DoubleQuat set(double vX, double vY, double vZ, double vW) {
        return new DoubleQuat(vX, vY, vZ, vW);
    }

    /**
     * Convert this quaternion to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code FloatQuat} holding the result
     */
    public FloatQuat toFloat() {
        return new FloatQuat((float) (this.x), (float) (this.y), (float) (this.z), (float) (this.w));
    }

    /**
     * Create the rotation (real) part of the unit dual quaternion {@code dq}.
     * <p>
     * Valid input: {@code dq} must be a unit dual quaternion.
     *
     * @param dq the dual quaternion to convert
     * @return the resulting quaternion
     */
    public static DoubleQuat makeFromDualQuat(DoubleDualQuat dq) {
        double dqRX = dq.rX();
        double dqRY = dq.rY();
        double dqRZ = dq.rZ();
        double dqRW = dq.rW();
        return new DoubleQuat(dqRX, dqRY, dqRZ, dqRW);
    }

    /**
     * Create the rotation (real) part of the unit dual quaternion ({@code dqRX}, {@code dqRY},
     * {@code dqRZ}, {@code dqRW}, {@code dqDX}, {@code dqDY}, {@code dqDZ}, {@code dqDW}).
     * <p>
     * Valid input: {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)} must be a unit dual
     * quaternion.
     *
     * @param dqRX the {@code rX} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqRY the {@code rY} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqRZ the {@code rZ} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqRW the {@code rW} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqDX the {@code dX} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqDY the {@code dY} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqDZ the {@code dZ} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqDW the {@code dW} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @return the resulting quaternion
     */
    public static DoubleQuat makeFromDualQuat(double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        return new DoubleQuat(dqRX, dqRY, dqRZ, dqRW);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static DoubleQuat makeFromMatrix_s91e5aa_tail(double _t10, double _sp0, double _t1, Double3x3 m, double _t2, double _t15, double _sp1, double _t4, double _sp2, double _t6, double _t7, double _sp3, double _t16, double _t8, double _t9, double _t17, double _t14) {
        double _sfx0, _sfx1, _sfx2;
        if (_t10 > 0.0) {
            _sfx0 = _sp0 * _t1;
            _sfx1 = _sp0 * _t7;
            _sfx2 = _sp0 * _t9;
        } else {
            if (m.m00() > _t2) {
                _sfx0 = 0.5 * java.lang.Math.sqrt(_t15);
                _sfx1 = _sp3 * _t4;
                _sfx2 = _sp3 * _t6;
            } else {
                if (m.m11() > m.m22()) {
                    _sfx0 = _sp1 * _t4;
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t16);
                    _sfx2 = _sp1 * _t8;
                } else {
                    _sfx0 = _sp2 * _t6;
                    _sfx1 = _sp2 * _t8;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t17);
                }
            }
        }
        return makeFromMatrix_s91e5aa_tail2(_t10, _t14, m, _t2, _sp3, _t1, _sp1, _t7, _sp2, _t9, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static DoubleQuat makeFromMatrix_s91e5aa_tail2(double _t10, double _t14, Double3x3 m, double _t2, double _sp3, double _t1, double _sp1, double _t7, double _sp2, double _t9, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3 = _t10 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t14) : m.m00() > _t2 ? _sp3 * _t1 : m.m11() > m.m22() ? _sp1 * _t7 : _sp2 * _t9;
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Create the rotation represented by the given matrix (which must be a rotation: orthonormal,
     * with determinant +1 - a scaled or sheared block gives a wrong quaternion, not a longer one;
     * {@code getNormalizedRotation} strips scale first).
     * <p>
     * Valid input: {@code m} must be a rotation matrix.
     *
     * @param m the matrix to convert
     * @return the resulting quaternion
     */
    public static DoubleQuat makeFromMatrix(Double3x3 m) {
        double _m_m00 = m.m00();
        double _m_m11 = m.m11();
        double _t0 = _m_m00 + _m_m11;
        double _m_m22 = m.m22();
        double _t10 = _m_m22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_m_m00 - (_m_m11 + _m_m22));
        double _t16 = 1.0 + (_m_m11 - (_m_m00 + _m_m22));
        double _t17 = 1.0 + (_m_m22 - _t0);
        double _m_m21 = m.m21();
        double _m_m12 = m.m12();
        double _m_m01 = m.m01();
        double _m_m10 = m.m10();
        double _m_m02 = m.m02();
        double _m_m20 = m.m20();
        return makeFromMatrix_s91e5aa_tail(_t10, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), _m_m21 - _m_m12, m, Math.max(_m_m11, _m_m22), _t15, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), _m_m01 + _m_m10, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), _m_m02 + _m_m20, _m_m02 - _m_m20, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), _t16, _m_m12 + _m_m21, _m_m10 - _m_m01, _t17, _t14);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static DoubleQuat makeFromMatrix_s91e96b_tail(double _t10, double _sp0, double _t1, Double3x4 m, double _t2, double _t15, double _sp1, double _t4, double _sp2, double _t6, double _t7, double _sp3, double _t16, double _t8, double _t9, double _t17, double _t14) {
        double _sfx0, _sfx1, _sfx2;
        if (_t10 > 0.0) {
            _sfx0 = _sp0 * _t1;
            _sfx1 = _sp0 * _t7;
            _sfx2 = _sp0 * _t9;
        } else {
            if (m.m00() > _t2) {
                _sfx0 = 0.5 * java.lang.Math.sqrt(_t15);
                _sfx1 = _sp3 * _t4;
                _sfx2 = _sp3 * _t6;
            } else {
                if (m.m11() > m.m22()) {
                    _sfx0 = _sp1 * _t4;
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t16);
                    _sfx2 = _sp1 * _t8;
                } else {
                    _sfx0 = _sp2 * _t6;
                    _sfx1 = _sp2 * _t8;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t17);
                }
            }
        }
        return makeFromMatrix_s91e96b_tail2(_t10, _t14, m, _t2, _sp3, _t1, _sp1, _t7, _sp2, _t9, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static DoubleQuat makeFromMatrix_s91e96b_tail2(double _t10, double _t14, Double3x4 m, double _t2, double _sp3, double _t1, double _sp1, double _t7, double _sp2, double _t9, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3 = _t10 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t14) : m.m00() > _t2 ? _sp3 * _t1 : m.m11() > m.m22() ? _sp1 * _t7 : _sp2 * _t9;
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Create the rotation represented by the given matrix (which must be a rotation: orthonormal,
     * with determinant +1 - a scaled or sheared block gives a wrong quaternion, not a longer one;
     * {@code getNormalizedRotation} strips scale first).
     * <p>
     * Valid input: {@code m} must be a rigid transformation (a rotation with any translation).
     *
     * @param m the matrix to convert
     * @return the resulting quaternion
     */
    public static DoubleQuat makeFromMatrix(Double3x4 m) {
        double _m_m00 = m.m00();
        double _m_m11 = m.m11();
        double _t0 = _m_m00 + _m_m11;
        double _m_m22 = m.m22();
        double _t10 = _m_m22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_m_m00 - (_m_m11 + _m_m22));
        double _t16 = 1.0 + (_m_m11 - (_m_m00 + _m_m22));
        double _t17 = 1.0 + (_m_m22 - _t0);
        double _m_m21 = m.m21();
        double _m_m12 = m.m12();
        double _m_m01 = m.m01();
        double _m_m10 = m.m10();
        double _m_m02 = m.m02();
        double _m_m20 = m.m20();
        return makeFromMatrix_s91e96b_tail(_t10, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), _m_m21 - _m_m12, m, Math.max(_m_m11, _m_m22), _t15, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), _m_m01 + _m_m10, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), _m_m02 + _m_m20, _m_m02 - _m_m20, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), _t16, _m_m12 + _m_m21, _m_m10 - _m_m01, _t17, _t14);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static DoubleQuat makeFromMatrix_sa000ec_tail(double _t10, double _sp0, double _t1, Double4x4 m, double _t2, double _t15, double _sp1, double _t4, double _sp2, double _t6, double _t7, double _sp3, double _t16, double _t8, double _t9, double _t17, double _t14) {
        double _sfx0, _sfx1, _sfx2;
        if (_t10 > 0.0) {
            _sfx0 = _sp0 * _t1;
            _sfx1 = _sp0 * _t7;
            _sfx2 = _sp0 * _t9;
        } else {
            if (m.m00() > _t2) {
                _sfx0 = 0.5 * java.lang.Math.sqrt(_t15);
                _sfx1 = _sp3 * _t4;
                _sfx2 = _sp3 * _t6;
            } else {
                if (m.m11() > m.m22()) {
                    _sfx0 = _sp1 * _t4;
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t16);
                    _sfx2 = _sp1 * _t8;
                } else {
                    _sfx0 = _sp2 * _t6;
                    _sfx1 = _sp2 * _t8;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t17);
                }
            }
        }
        return makeFromMatrix_sa000ec_tail2(_t10, _t14, m, _t2, _sp3, _t1, _sp1, _t7, _sp2, _t9, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static DoubleQuat makeFromMatrix_sa000ec_tail2(double _t10, double _t14, Double4x4 m, double _t2, double _sp3, double _t1, double _sp1, double _t7, double _sp2, double _t9, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3 = _t10 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t14) : m.m00() > _t2 ? _sp3 * _t1 : m.m11() > m.m22() ? _sp1 * _t7 : _sp2 * _t9;
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Create the rotation represented by the given matrix (which must be a rotation: orthonormal,
     * with determinant +1 - a scaled or sheared block gives a wrong quaternion, not a longer one;
     * {@code getNormalizedRotation} strips scale first).
     * <p>
     * Valid input: {@code m} must be a rigid transformation (a rotation with any translation).
     *
     * @param m the matrix to convert
     * @return the resulting quaternion
     */
    public static DoubleQuat makeFromMatrix(Double4x4 m) {
        double _m_m00 = m.m00();
        double _m_m11 = m.m11();
        double _t0 = _m_m00 + _m_m11;
        double _m_m22 = m.m22();
        double _t10 = _m_m22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_m_m00 - (_m_m11 + _m_m22));
        double _t16 = 1.0 + (_m_m11 - (_m_m00 + _m_m22));
        double _t17 = 1.0 + (_m_m22 - _t0);
        double _m_m21 = m.m21();
        double _m_m12 = m.m12();
        double _m_m01 = m.m01();
        double _m_m10 = m.m10();
        double _m_m02 = m.m02();
        double _m_m20 = m.m20();
        return makeFromMatrix_sa000ec_tail(_t10, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), _m_m21 - _m_m12, m, Math.max(_m_m11, _m_m22), _t15, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), _m_m01 + _m_m10, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), _m_m02 + _m_m20, _m_m02 - _m_m20, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), _t16, _m_m12 + _m_m21, _m_m10 - _m_m01, _t17, _t14);
    }

    /**
     * Convert this quaternion to a pure-rotation dual quaternion (zero dual part), returning the
     * result as a value.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting dual quaternion
     */
    public DoubleDualQuat toDualQuat() {
        return new DoubleDualQuat(this.x, this.y, this.z, this.w, 0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Compute the matrix representation of this quaternion, returning the result as a value.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting matrix
     */
    public Double4x4 toMatrix() {
        if (!Math.useFma()) return toMatrix_mulAdd();
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t2 = this.y * this.w;
        return new Double4x4(java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t0), 1.0), 2.0 * java.lang.Math.fma(this.x, this.y, -_t1), 2.0 * java.lang.Math.fma(this.x, this.z, _t2), 0.0, 2.0 * java.lang.Math.fma(this.x, this.y, _t1), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t0), 1.0), 2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w)), 0.0, 2.0 * java.lang.Math.fma(this.x, this.z, -_t2), 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0), 0.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x4 toMatrix_mulAdd() {
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t2 = this.y * this.w;
        return new Double4x4(((1.0) - (2.0) * (((this.y) * (this.y) + (_t0)))), 2.0 * ((this.x) * (this.y) - (_t1)), 2.0 * ((this.x) * (this.z) + (_t2)), 0.0, 2.0 * ((this.x) * (this.y) + (_t1)), ((1.0) - (2.0) * (((this.x) * (this.x) + (_t0)))), 2.0 * ((this.y) * (this.z) - (this.x * this.w)), 0.0, 2.0 * ((this.x) * (this.z) - (_t2)), 2.0 * ((this.x) * (this.w) + (this.y * this.z)), ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y)))), 0.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }

    /**
     * Compute the 3x3 rotation matrix representation of this quaternion, returning the result as a
     * value.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting matrix
     */
    public Double3x3 toMatrix3x3() {
        if (!Math.useFma()) return toMatrix3x3_mulAdd();
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t2 = this.y * this.w;
        return new Double3x3(java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t0), 1.0), 2.0 * java.lang.Math.fma(this.x, this.y, -_t1), 2.0 * java.lang.Math.fma(this.x, this.z, _t2), 2.0 * java.lang.Math.fma(this.x, this.y, _t1), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t0), 1.0), 2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w)), 2.0 * java.lang.Math.fma(this.x, this.z, -_t2), 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0), 0);
    }

    /** {@code toMatrix3x3} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x3 toMatrix3x3_mulAdd() {
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t2 = this.y * this.w;
        return new Double3x3(((1.0) - (2.0) * (((this.y) * (this.y) + (_t0)))), 2.0 * ((this.x) * (this.y) - (_t1)), 2.0 * ((this.x) * (this.z) + (_t2)), 2.0 * ((this.x) * (this.y) + (_t1)), ((1.0) - (2.0) * (((this.x) * (this.x) + (_t0)))), 2.0 * ((this.y) * (this.z) - (this.x * this.w)), 2.0 * ((this.x) * (this.z) - (_t2)), 2.0 * ((this.x) * (this.w) + (this.y * this.z)), ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y)))), 0);
    }

    /**
     * Compute the 3x4 matrix representation of this quaternion (the omitted last row is implicitly
     * {@code 0, 0, 0, 1}), returning the result as a value.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting matrix
     */
    public Double3x4 toMatrix3x4() {
        if (!Math.useFma()) return toMatrix3x4_mulAdd();
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t2 = this.y * this.w;
        return new Double3x4(java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t0), 1.0), 2.0 * java.lang.Math.fma(this.x, this.y, -_t1), 2.0 * java.lang.Math.fma(this.x, this.z, _t2), 0.0, 2.0 * java.lang.Math.fma(this.x, this.y, _t1), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t0), 1.0), 2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w)), 0.0, 2.0 * java.lang.Math.fma(this.x, this.z, -_t2), 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0), 0.0, Joml.BIT_ORTHOGONAL);
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x4 toMatrix3x4_mulAdd() {
        double _t0 = this.z * this.z;
        double _t1 = this.z * this.w;
        double _t2 = this.y * this.w;
        return new Double3x4(((1.0) - (2.0) * (((this.y) * (this.y) + (_t0)))), 2.0 * ((this.x) * (this.y) - (_t1)), 2.0 * ((this.x) * (this.z) + (_t2)), 0.0, 2.0 * ((this.x) * (this.y) + (_t1)), ((1.0) - (2.0) * (((this.x) * (this.x) + (_t0)))), 2.0 * ((this.y) * (this.z) - (this.x * this.w)), 0.0, 2.0 * ((this.x) * (this.z) - (_t2)), 2.0 * ((this.x) * (this.w) + (this.y * this.z)), ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y)))), 0.0, Joml.BIT_ORTHOGONAL);
    }

    /**
     * Result value of {@code decomposeSwingTwist}.
     *
     * @param swing the swing
     * @param twist the twist
     */
    public record DecomposeSwingTwistResult(DoubleQuat swing, DoubleQuat twist) {
        /**
         * Canonical constructor.
         * <p>
         * Valid input: any value, NaN and the infinities included.
         *
         * @param swing the swing
         * @param twist the twist
         */
        public DecomposeSwingTwistResult(DoubleQuat swing, DoubleQuat twist) {
            this.swing = swing;
            this.twist = twist;
        }

        /** {@return the {@code swing} component} <p>Valid input: any value, NaN and the infinities included. */
        public DoubleQuat swing() { return swing; }

        /** {@return the {@code twist} component} <p>Valid input: any value, NaN and the infinities included. */
        public DoubleQuat twist() { return twist; }
    }

    /** Private tail of {@code decomposeSwingTwist}; reached only through it. */
    private DecomposeSwingTwistResult decomposeSwingTwist_sce8ee9f_tail_fma(double _t4, double _t5, Double3 axis, double _t7) {
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = this.w * _t5;
            _t12 = axis.x() * _t7;
            _t13 = axis.y() * _t7;
            _t14 = axis.z() * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        return new DecomposeSwingTwistResult(new DoubleQuat(java.lang.Math.fma(this.x, _t11, -this.w * _t12) + java.lang.Math.fma(this.z, _t13, -this.y * _t14), java.lang.Math.fma(this.x, _t14, -this.w * _t13) + java.lang.Math.fma(this.y, _t11, -this.z * _t12), java.lang.Math.fma(this.y, _t12, this.z * _t11) + java.lang.Math.fma(-this.x, _t13, -this.w * _t14), java.lang.Math.fma(this.x, _t12, this.w * _t11) - java.lang.Math.fma(-this.z, _t14, -this.y * _t13)), new DoubleQuat(_t12, _t13, _t14, _t11));
    }

    /** Private tail of {@code decomposeSwingTwist}; reached only through it. */
    private DecomposeSwingTwistResult decomposeSwingTwist_sce8ee9f_tail_mulAdd(double _t4, double _t5, Double3 axis, double _t7) {
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = this.w * _t5;
            _t12 = axis.x() * _t7;
            _t13 = axis.y() * _t7;
            _t14 = axis.z() * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        return new DecomposeSwingTwistResult(new DoubleQuat(((this.x) * (_t11) - (this.w * _t12)) + ((this.z) * (_t13) - (this.y * _t14)), ((this.x) * (_t14) - (this.w * _t13)) + ((this.y) * (_t11) - (this.z * _t12)), ((this.y) * (_t12) + (this.z * _t11)) + ((-this.x) * (_t13) - (this.w * _t14)), ((this.x) * (_t12) + (this.w * _t11)) - ((-this.z) * (_t14) - (this.y * _t13))), new DoubleQuat(_t12, _t13, _t14, _t11));
    }

    /**
     * Decompose this quaternion into a twist about {@code axis} followed by a swing about an axis
     * perpendicular to it, such that {@code swing * twist} is this rotation (the twist acts on a
     * vector first).
     * <p>
     * Equivalent to calling {@code getSwing} and {@code getTwist} separately, but shares the work.
     * The twist is the identity rotation ({@code IDENTITY} or its negation) when the rotation is a
     * pure swing; in the 180-degree perpendicular case, where it is undefined, it is
     * {@code IDENTITY}.
     * <p>
     * Valid input: {@code axis} must have unit length; this quaternion must have unit length.
     *
     * @param axis the rotation axis
     * @return a new result value holding the swing and the twist
     */
    public DecomposeSwingTwistResult decomposeSwingTwist(Double3 axis) {
        double _axis_z = axis.z();
        double _axis_x = axis.x();
        double _axis_y = axis.y();
        if (Math.useFma()) {
            double _t2 = java.lang.Math.fma(_axis_z, this.z, java.lang.Math.fma(_axis_x, this.x, _axis_y * this.y));
            double _t4 = java.lang.Math.fma(this.w, this.w, _t2 * _t2);
            double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
            return decomposeSwingTwist_sce8ee9f_tail_fma(_t4, _t5, axis, _t2 * _t5);
        } else {
            double _t2 = ((_axis_z) * (this.z) + (((_axis_x) * (this.x) + (_axis_y * this.y))));
            double _t4 = ((this.w) * (this.w) + (_t2 * _t2));
            double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
            return decomposeSwingTwist_sce8ee9f_tail_mulAdd(_t4, _t5, axis, _t2 * _t5);
        }
    }

    /**
     * Extract the swing component of this quaternion: the rotation perpendicular to {@code axis} in
     * the swing-twist decomposition, returning the result as a value.
     * <p>
     * The swing carries the rotation that tilts the axis itself; its own axis is perpendicular to
     * the given one.
     * <p>
     * Valid input: {@code axis} must have unit length; this quaternion must have unit length.
     *
     * @param axis the rotation axis
     * @return the resulting quaternion
     */
    public DoubleQuat getSwing(Double3 axis) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        if (!Math.useFma()) return getSwing_mulAdd(axisX, axisY, axisZ);
        double _t2 = java.lang.Math.fma(axisZ, this.z, java.lang.Math.fma(axisX, this.x, axisY * this.y));
        double _t4 = java.lang.Math.fma(this.w, this.w, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = this.w * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        return new DoubleQuat(java.lang.Math.fma(this.x, _t11, -this.w * _t12) + java.lang.Math.fma(this.z, _t13, -this.y * _t14), java.lang.Math.fma(this.x, _t14, -this.w * _t13) + java.lang.Math.fma(this.y, _t11, -this.z * _t12), java.lang.Math.fma(this.y, _t12, this.z * _t11) + java.lang.Math.fma(-this.x, _t13, -this.w * _t14), java.lang.Math.fma(this.x, _t12, this.w * _t11) - java.lang.Math.fma(-this.z, _t14, -this.y * _t13));
    }

    /**
     * Extract the swing component of this quaternion: the rotation perpendicular to ({@code axisX},
     * {@code axisY}, {@code axisZ}) in the swing-twist decomposition, returning the result as a
     * value.
     * <p>
     * The swing carries the rotation that tilts the axis itself; its own axis is perpendicular to
     * the given one.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length; this quaternion must have
     * unit length.
     *
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return the resulting quaternion
     */
    public DoubleQuat getSwing(double axisX, double axisY, double axisZ) {
        if (!Math.useFma()) return getSwing_mulAdd(axisX, axisY, axisZ);
        double _t2 = java.lang.Math.fma(axisZ, this.z, java.lang.Math.fma(axisX, this.x, axisY * this.y));
        double _t4 = java.lang.Math.fma(this.w, this.w, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = this.w * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        return new DoubleQuat(java.lang.Math.fma(this.x, _t11, -this.w * _t12) + java.lang.Math.fma(this.z, _t13, -this.y * _t14), java.lang.Math.fma(this.x, _t14, -this.w * _t13) + java.lang.Math.fma(this.y, _t11, -this.z * _t12), java.lang.Math.fma(this.y, _t12, this.z * _t11) + java.lang.Math.fma(-this.x, _t13, -this.w * _t14), java.lang.Math.fma(this.x, _t12, this.w * _t11) - java.lang.Math.fma(-this.z, _t14, -this.y * _t13));
    }

    /** {@code getSwing} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat getSwing_mulAdd(double axisX, double axisY, double axisZ) {
        double _t2 = ((axisZ) * (this.z) + (((axisX) * (this.x) + (axisY * this.y))));
        double _t4 = ((this.w) * (this.w) + (_t2 * _t2));
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = this.w * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        return new DoubleQuat(((this.x) * (_t11) - (this.w * _t12)) + ((this.z) * (_t13) - (this.y * _t14)), ((this.x) * (_t14) - (this.w * _t13)) + ((this.y) * (_t11) - (this.z * _t12)), ((this.y) * (_t12) + (this.z * _t11)) + ((-this.x) * (_t13) - (this.w * _t14)), ((this.x) * (_t12) + (this.w * _t11)) - ((-this.z) * (_t14) - (this.y * _t13)));
    }

    /**
     * Extract the twist component of this quaternion: the rotation about {@code axis} in the
     * swing-twist decomposition, returning the result as a value.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity rotation ({@code IDENTITY}
     * or its negation) when the rotation is a pure swing, and {@code IDENTITY} in the 180-degree
     * perpendicular case where the twist angle is undefined.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param axis the rotation axis
     * @return the resulting quaternion
     */
    public DoubleQuat getTwist(Double3 axis) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        if (Math.useFma()) {
            double _t2 = java.lang.Math.fma(axisZ, this.z, java.lang.Math.fma(axisX, this.x, axisY * this.y));
            double _t4 = java.lang.Math.fma(this.w, this.w, _t2 * _t2);
            double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
            double _t6 = _t2 * _t5;
            if (_t4 > 1.0E-30) {
                return new DoubleQuat(axisX * _t6, axisY * _t6, axisZ * _t6, this.w * _t5);
            } else {
                return new DoubleQuat(0.0, 0.0, 0.0, 1.0);
            }
        } else {
            double _t2 = ((axisZ) * (this.z) + (((axisX) * (this.x) + (axisY * this.y))));
            double _t4 = ((this.w) * (this.w) + (_t2 * _t2));
            double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
            double _t6 = _t2 * _t5;
            if (_t4 > 1.0E-30) {
                return new DoubleQuat(axisX * _t6, axisY * _t6, axisZ * _t6, this.w * _t5);
            } else {
                return new DoubleQuat(0.0, 0.0, 0.0, 1.0);
            }
        }
    }

    /**
     * Extract the twist component of this quaternion: the rotation about ({@code axisX},
     * {@code axisY}, {@code axisZ}) in the swing-twist decomposition, returning the result as a
     * value.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity rotation ({@code IDENTITY}
     * or its negation) when the rotation is a pure swing, and {@code IDENTITY} in the 180-degree
     * perpendicular case where the twist angle is undefined.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return the resulting quaternion
     */
    public DoubleQuat getTwist(double axisX, double axisY, double axisZ) {
        if (Math.useFma()) {
            double _t2 = java.lang.Math.fma(axisZ, this.z, java.lang.Math.fma(axisX, this.x, axisY * this.y));
            double _t4 = java.lang.Math.fma(this.w, this.w, _t2 * _t2);
            double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
            double _t6 = _t2 * _t5;
            if (_t4 > 1.0E-30) {
                return new DoubleQuat(axisX * _t6, axisY * _t6, axisZ * _t6, this.w * _t5);
            } else {
                return new DoubleQuat(0.0, 0.0, 0.0, 1.0);
            }
        } else {
            double _t2 = ((axisZ) * (this.z) + (((axisX) * (this.x) + (axisY * this.y))));
            double _t4 = ((this.w) * (this.w) + (_t2 * _t2));
            double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
            double _t6 = _t2 * _t5;
            if (_t4 > 1.0E-30) {
                return new DoubleQuat(axisX * _t6, axisY * _t6, axisZ * _t6, this.w * _t5);
            } else {
                return new DoubleQuat(0.0, 0.0, 0.0, 1.0);
            }
        }
    }

    /**
     * Create an identity quaternion.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting quaternion
     */
    public static DoubleQuat makeIdentity() {
        return new DoubleQuat(0.0, 0.0, 0.0, 1.0);
    }

    /**
     * Create an all-zero quaternion.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting quaternion
     */
    public static DoubleQuat makeZero() {
        return DoubleQuat.ZERO;
    }

    /**
     * Linearly interpolate between this quaternion and {@code other} using the interpolation factor
     * {@code t}, returning the result as a value.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (1e17 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting quaternion
     */
    public DoubleQuat lerp(DoubleQuat other, double t) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        double otherW = other.w;
        if (Math.useFma()) {
            return new DoubleQuat(java.lang.Math.fma(t, otherX - this.x, this.x), java.lang.Math.fma(t, otherY - this.y, this.y), java.lang.Math.fma(t, otherZ - this.z, this.z), java.lang.Math.fma(t, otherW - this.w, this.w));
        } else {
            return new DoubleQuat(((t) * (otherX - this.x) + (this.x)), ((t) * (otherY - this.y) + (this.y)), ((t) * (otherZ - this.z) + (this.z)), ((t) * (otherW - this.w) + (this.w)));
        }
    }

    /**
     * Linearly interpolate between this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) using the interpolation factor {@code t}, returning the
     * result as a value.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) (interpolation factor
     * {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t}, as in
     * JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only
     * up to the rounding of {@code other - this}, which shows when this component is much larger in
     * magnitude than the other one (1e17 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting quaternion
     */
    public DoubleQuat lerp(double otherX, double otherY, double otherZ, double otherW, double t) {
        if (Math.useFma()) {
            return new DoubleQuat(java.lang.Math.fma(t, otherX - this.x, this.x), java.lang.Math.fma(t, otherY - this.y, this.y), java.lang.Math.fma(t, otherZ - this.z, this.z), java.lang.Math.fma(t, otherW - this.w, this.w));
        } else {
            return new DoubleQuat(((t) * (otherX - this.x) + (this.x)), ((t) * (otherY - this.y) + (this.y)), ((t) * (otherZ - this.z) + (this.z)), ((t) * (otherW - this.w) + (this.w)));
        }
    }

    /**
     * Interpolate between this quaternion and {@code target} using the interpolation factor
     * {@code alpha} and normalize the result, returning the result as a value.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting quaternion
     */
    public DoubleQuat nlerp(DoubleQuat target, double alpha) {
        return nlerp(target.x, target.y, target.z, target.w, alpha);
    }

    /**
     * Interpolate between this quaternion and ({@code targetX}, {@code targetY}, {@code targetZ},
     * {@code targetW}) using the interpolation factor {@code alpha} and normalize the result,
     * returning the result as a value.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting quaternion
     */
    public DoubleQuat nlerp(double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (Math.useFma()) {
            double _t4 = java.lang.Math.fma(alpha, targetW - this.w, this.w);
            double _t5 = java.lang.Math.fma(alpha, targetZ - this.z, this.z);
            double _t6 = java.lang.Math.fma(alpha, targetX - this.x, this.x);
            double _t7 = java.lang.Math.fma(alpha, targetY - this.y, this.y);
            double _t11 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, _t7 * _t7)));
            double _t12 = (1.0 / java.lang.Math.sqrt(_t11));
            if (_t11 != 0.0) {
                return new DoubleQuat(_t6 * _t12, _t7 * _t12, _t5 * _t12, _t4 * _t12);
            } else {
                return DoubleQuat.ZERO;
            }
        } else {
            double _t4 = ((alpha) * (targetW - this.w) + (this.w));
            double _t5 = ((alpha) * (targetZ - this.z) + (this.z));
            double _t6 = ((alpha) * (targetX - this.x) + (this.x));
            double _t7 = ((alpha) * (targetY - this.y) + (this.y));
            double _t11 = ((_t4) * (_t4) + (((_t5) * (_t5) + (((_t6) * (_t6) + (_t7 * _t7))))));
            double _t12 = (1.0 / java.lang.Math.sqrt(_t11));
            if (_t11 != 0.0) {
                return new DoubleQuat(_t6 * _t12, _t7 * _t12, _t5 * _t12, _t4 * _t12);
            } else {
                return DoubleQuat.ZERO;
            }
        }
    }

    /**
     * Interpolate along the shortest path between this quaternion and {@code target} using the
     * interpolation factor {@code alpha} and normalize the result, returning the result as a value.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}), or at its negation - the same rotation -
     * when the two quaternions' dot product is negative.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting quaternion
     */
    public DoubleQuat nlerpShortest(DoubleQuat target, double alpha) {
        return nlerpShortest(target.x, target.y, target.z, target.w, alpha);
    }

    /**
     * Interpolate along the shortest path between this quaternion and ({@code targetX},
     * {@code targetY}, {@code targetZ}, {@code targetW}) using the interpolation factor
     * {@code alpha} and normalize the result, returning the result as a value.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}), or at its negation - the same rotation - when the two quaternions' dot product is
     * negative.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting quaternion
     */
    public DoubleQuat nlerpShortest(double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (!Math.useFma()) return nlerpShortest_mulAdd(targetX, targetY, targetZ, targetW, alpha);
        double _t17, _t18, _t19, _t20;
        if (-java.lang.Math.fma(this.w, targetW, java.lang.Math.fma(this.z, targetZ, java.lang.Math.fma(this.x, targetX, this.y * targetY))) > 0.0) {
            _t17 = java.lang.Math.fma(alpha, -targetW - this.w, this.w);
            _t18 = java.lang.Math.fma(alpha, -targetZ - this.z, this.z);
            _t19 = java.lang.Math.fma(alpha, -targetX - this.x, this.x);
            _t20 = java.lang.Math.fma(alpha, -targetY - this.y, this.y);
        } else {
            _t17 = java.lang.Math.fma(alpha, targetW - this.w, this.w);
            _t18 = java.lang.Math.fma(alpha, targetZ - this.z, this.z);
            _t19 = java.lang.Math.fma(alpha, targetX - this.x, this.x);
            _t20 = java.lang.Math.fma(alpha, targetY - this.y, this.y);
        }
        double _t24 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t25 = (1.0 / java.lang.Math.sqrt(_t24));
        if (_t24 != 0.0) {
            return new DoubleQuat(_t19 * _t25, _t20 * _t25, _t18 * _t25, _t17 * _t25);
        } else {
            return DoubleQuat.ZERO;
        }
    }

    /** {@code nlerpShortest} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat nlerpShortest_mulAdd(double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _t17, _t18, _t19, _t20;
        if (-((this.w) * (targetW) + (((this.z) * (targetZ) + (((this.x) * (targetX) + (this.y * targetY)))))) > 0.0) {
            _t17 = ((alpha) * (-targetW - this.w) + (this.w));
            _t18 = ((alpha) * (-targetZ - this.z) + (this.z));
            _t19 = ((alpha) * (-targetX - this.x) + (this.x));
            _t20 = ((alpha) * (-targetY - this.y) + (this.y));
        } else {
            _t17 = ((alpha) * (targetW - this.w) + (this.w));
            _t18 = ((alpha) * (targetZ - this.z) + (this.z));
            _t19 = ((alpha) * (targetX - this.x) + (this.x));
            _t20 = ((alpha) * (targetY - this.y) + (this.y));
        }
        double _t24 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t25 = (1.0 / java.lang.Math.sqrt(_t24));
        if (_t24 != 0.0) {
            return new DoubleQuat(_t19 * _t25, _t20 * _t25, _t18 * _t25, _t17 * _t25);
        } else {
            return DoubleQuat.ZERO;
        }
    }

    /**
     * Spherically interpolate between this quaternion and {@code target} using the interpolation
     * factor {@code alpha}, returning the result as a value.
     * <p>
     * This method interpolates along the arc as given: when the two quaternions' dot product is
     * negative, the longer path around the sphere is taken. Use {@link #slerpShortest} (or negate
     * one operand) to always interpolate along the shorter arc.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code target} must have unit length; this quaternion must have unit length.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting quaternion
     */
    public DoubleQuat slerp(DoubleQuat target, double alpha) {
        double targetX = target.x;
        double targetY = target.y;
        double targetZ = target.z;
        double targetW = target.w;
        if (!Math.useFma()) return slerp_mulAdd(targetX, targetY, targetZ, targetW, alpha);
        double _t0 = 1.0 - alpha;
        double _t1 = this.w + targetW;
        double _t2 = this.z + targetZ;
        double _t3 = this.x + targetX;
        double _t4 = this.y + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        double _t12 = quatArcAngle(_t11);
        double _t13 = 4.0 - _t11;
        double _t19 = java.lang.Math.sqrt(_t13 * _t11);
        double _t21 = 2.0 / _t19;
        double _t26, _t27;
        if (_t19 > 2.0E-14) {
            _t26 = _t21 * Math.sin(alpha * _t12);
            _t27 = _t21 * Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0 - _t5;
                _t27 = _t5;
            }
        }
        return new DoubleQuat(java.lang.Math.fma(this.x, _t27, targetX * _t26), java.lang.Math.fma(this.y, _t27, targetY * _t26), java.lang.Math.fma(this.z, _t27, targetZ * _t26), java.lang.Math.fma(this.w, _t27, targetW * _t26));
    }

    /**
     * Spherically interpolate between this quaternion and ({@code targetX}, {@code targetY},
     * {@code targetZ}, {@code targetW}) using the interpolation factor {@code alpha}, returning the
     * result as a value.
     * <p>
     * This method interpolates along the arc as given: when the two quaternions' dot product is
     * negative, the longer path around the sphere is taken. Use {@link #slerpShortest} (or negate
     * one operand) to always interpolate along the shorter arc.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}).
     * <p>
     * Valid input: {@code (targetX, targetY, targetZ, targetW)} must have unit length; this
     * quaternion must have unit length.
     *
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting quaternion
     */
    public DoubleQuat slerp(double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (!Math.useFma()) return slerp_mulAdd(targetX, targetY, targetZ, targetW, alpha);
        double _t0 = 1.0 - alpha;
        double _t1 = this.w + targetW;
        double _t2 = this.z + targetZ;
        double _t3 = this.x + targetX;
        double _t4 = this.y + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        double _t12 = quatArcAngle(_t11);
        double _t13 = 4.0 - _t11;
        double _t19 = java.lang.Math.sqrt(_t13 * _t11);
        double _t21 = 2.0 / _t19;
        double _t26, _t27;
        if (_t19 > 2.0E-14) {
            _t26 = _t21 * Math.sin(alpha * _t12);
            _t27 = _t21 * Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0 - _t5;
                _t27 = _t5;
            }
        }
        return new DoubleQuat(java.lang.Math.fma(this.x, _t27, targetX * _t26), java.lang.Math.fma(this.y, _t27, targetY * _t26), java.lang.Math.fma(this.z, _t27, targetZ * _t26), java.lang.Math.fma(this.w, _t27, targetW * _t26));
    }

    /** {@code slerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat slerp_mulAdd(double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _t0 = 1.0 - alpha;
        double _t1 = this.w + targetW;
        double _t2 = this.z + targetZ;
        double _t3 = this.x + targetX;
        double _t4 = this.y + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        double _t12 = quatArcAngle(_t11);
        double _t13 = 4.0 - _t11;
        double _t19 = java.lang.Math.sqrt(_t13 * _t11);
        double _t21 = 2.0 / _t19;
        double _t26, _t27;
        if (_t19 > 2.0E-14) {
            _t26 = _t21 * Math.sin(alpha * _t12);
            _t27 = _t21 * Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0 - _t5;
                _t27 = _t5;
            }
        }
        return new DoubleQuat(((this.x) * (_t27) + (targetX * _t26)), ((this.y) * (_t27) + (targetY * _t26)), ((this.z) * (_t27) + (targetZ * _t26)), ((this.w) * (_t27) + (targetW * _t26)));
    }

    /**
     * Spherically interpolate along the shortest path between this quaternion and {@code target}
     * using the interpolation factor {@code alpha}, returning the result as a value.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}), or at its negation - the same rotation -
     * when the two quaternions' dot product is negative.
     * <p>
     * Valid input: {@code target} must have unit length; this quaternion must have unit length.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting quaternion
     */
    public DoubleQuat slerpShortest(DoubleQuat target, double alpha) {
        return slerpShortest(target.x, target.y, target.z, target.w, alpha);
    }

    /** Private tail of {@code slerpShortest}; reached only through it. */
    private DoubleQuat slerpShortest_s66703101_tail_fma(double _t17, double _t25, double _t19, double _t21, double _t17_inv, double alpha, double _t0, double _t22, double _t23, double _t24) {
        double _t42, _t43, _t44, _t45;
        if (_t17 > 0.0) {
            _t42 = java.lang.Math.fma(this.w, _t25, _t19 * _t21) * _t17_inv;
            _t43 = java.lang.Math.fma(this.z, _t25, _t19 * _t22) * _t17_inv;
            _t44 = java.lang.Math.fma(this.x, _t25, _t19 * _t23) * _t17_inv;
            _t45 = java.lang.Math.fma(this.y, _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = java.lang.Math.fma(alpha, _t21, this.w * _t0);
            _t43 = java.lang.Math.fma(alpha, _t22, this.z * _t0);
            _t44 = java.lang.Math.fma(alpha, _t23, this.x * _t0);
            _t45 = java.lang.Math.fma(alpha, _t24, this.y * _t0);
        }
        double _t49 = java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, _t45 * _t45)));
        return slerpShortest_s66703101_tail2(_t49, (1.0 / java.lang.Math.sqrt(_t49)), _t44, _t45, _t43, _t42);
    }

    /** Private tail of {@code slerpShortest}; reached only through it. */
    private DoubleQuat slerpShortest_s66703101_tail_mulAdd(double _t17, double _t25, double _t19, double _t21, double _t17_inv, double alpha, double _t0, double _t22, double _t23, double _t24) {
        double _t42, _t43, _t44, _t45;
        if (_t17 > 0.0) {
            _t42 = ((this.w) * (_t25) + (_t19 * _t21)) * _t17_inv;
            _t43 = ((this.z) * (_t25) + (_t19 * _t22)) * _t17_inv;
            _t44 = ((this.x) * (_t25) + (_t19 * _t23)) * _t17_inv;
            _t45 = ((this.y) * (_t25) + (_t19 * _t24)) * _t17_inv;
        } else {
            _t42 = ((alpha) * (_t21) + (this.w * _t0));
            _t43 = ((alpha) * (_t22) + (this.z * _t0));
            _t44 = ((alpha) * (_t23) + (this.x * _t0));
            _t45 = ((alpha) * (_t24) + (this.y * _t0));
        }
        double _t49 = ((_t42) * (_t42) + (((_t43) * (_t43) + (((_t44) * (_t44) + (_t45 * _t45))))));
        return slerpShortest_s66703101_tail2(_t49, (1.0 / java.lang.Math.sqrt(_t49)), _t44, _t45, _t43, _t42);
    }

    /** Private tail of {@code slerpShortest}; reached only through it. */
    private DoubleQuat slerpShortest_s66703101_tail2(double _t49, double _t50, double _t44, double _t45, double _t43, double _t42) {
        double _sfx0, _sfx1, _sfx2, _sfx3;
        if (_t49 != 0.0) {
            _sfx0 = _t50 * _t44;
            _sfx1 = _t50 * _t45;
            _sfx2 = _t50 * _t43;
            _sfx3 = _t50 * _t42;
        } else {
            _sfx0 = 0.0;
            _sfx1 = 0.0;
            _sfx2 = 0.0;
            _sfx3 = 0.0;
        }
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Spherically interpolate along the shortest path between this quaternion and ({@code targetX},
     * {@code targetY}, {@code targetZ}, {@code targetW}) using the interpolation factor
     * {@code alpha}, returning the result as a value.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}), or at its negation - the same rotation - when the two quaternions' dot product is
     * negative.
     * <p>
     * Valid input: {@code (targetX, targetY, targetZ, targetW)} must have unit length; this
     * quaternion must have unit length.
     *
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting quaternion
     */
    public DoubleQuat slerpShortest(double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (Math.useFma()) {
            double _t0 = 1.0 - alpha;
            double _t12 = java.lang.Math.fma(this.w, targetW, java.lang.Math.fma(this.z, targetZ, java.lang.Math.fma(this.x, targetX, this.y * targetY)));
            double _t16 = Math.acos(Math.min(1.0, java.lang.Math.abs(_t12)));
            double _t17 = Math.sin(_t16);
            double _t21, _t22, _t23, _t24;
            if (-_t12 > 0.0) {
                _t21 = -targetW;
                _t22 = -targetZ;
                _t23 = -targetX;
                _t24 = -targetY;
            } else {
                _t21 = targetW;
                _t22 = targetZ;
                _t23 = targetX;
                _t24 = targetY;
            }
            return slerpShortest_s66703101_tail_fma(_t17, Math.sin(_t0 * _t16), Math.sin(alpha * _t16), _t21, 1.0 / _t17, alpha, _t0, _t22, _t23, _t24);
        } else {
            double _t0 = 1.0 - alpha;
            double _t12 = ((this.w) * (targetW) + (((this.z) * (targetZ) + (((this.x) * (targetX) + (this.y * targetY))))));
            double _t16 = Math.acos(Math.min(1.0, java.lang.Math.abs(_t12)));
            double _t17 = Math.sin(_t16);
            double _t21, _t22, _t23, _t24;
            if (-_t12 > 0.0) {
                _t21 = -targetW;
                _t22 = -targetZ;
                _t23 = -targetX;
                _t24 = -targetY;
            } else {
                _t21 = targetW;
                _t22 = targetZ;
                _t23 = targetX;
                _t24 = targetY;
            }
            return slerpShortest_s66703101_tail_mulAdd(_t17, Math.sin(_t0 * _t16), Math.sin(alpha * _t16), _t21, 1.0 / _t17, alpha, _t0, _t22, _t23, _t24);
        }
    }

    /**
     * Perform spherical quadrangle interpolation (SQUAD) between this quaternion (the start
     * rotation) and the target rotation, shaped by the two inner control quaternions, returning the
     * result as a value.
     * <p>
     * Valid input: this quaternion must have unit length; {@code control0} must have unit length;
     * {@code control1} must have unit length; {@code target} must have unit length.
     *
     * @param control0 the inner control quaternion associated with the start rotation
     * @param control1 the inner control quaternion associated with the end rotation
     * @param target the target rotation
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting quaternion
     */
    public DoubleQuat squad(DoubleQuat control0, DoubleQuat control1, DoubleQuat target, double t) {
        double control0X = control0.x;
        double control0Y = control0.y;
        double control0Z = control0.z;
        double control0W = control0.w;
        double control1X = control1.x;
        double control1Y = control1.y;
        double control1Z = control1.z;
        double control1W = control1.w;
        double targetX = target.x;
        double targetY = target.y;
        double targetZ = target.z;
        double targetW = target.w;
        if (!Math.useFma()) return squad_mulAdd(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        double[] _bundle0 = squad_sa7490d9e_1_fma(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, t);
        double[] _bundle1 = squad_sa7490d9e_2_fma(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _bundle0[0], _bundle0[1], _bundle0[2], _bundle0[4], _bundle0[3]);
        double[] _bundle2 = squad_sa7490d9e_3_fma(t, _bundle0[0], _bundle1[2], _bundle1[3], _bundle1[4], _bundle1[5], _bundle1[6], _bundle1[7], _bundle1[8], java.lang.Math.fma(this.y, _bundle1[1], targetY * _bundle1[0]));
        return new DoubleQuat(_bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3]);
    }

    /**
     * Perform spherical quadrangle interpolation (SQUAD) between this quaternion (the start
     * rotation) and the target rotation, shaped by the two inner control quaternions, returning the
     * result as a value.
     * <p>
     * Valid input: this quaternion must have unit length;
     * {@code (control0X, control0Y, control0Z, control0W)} must have unit length;
     * {@code (control1X, control1Y, control1Z, control1W)} must have unit length;
     * {@code (targetX, targetY, targetZ, targetW)} must have unit length.
     *
     * @param control0X the {@code x} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0Y the {@code y} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0Z the {@code z} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0W the {@code w} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control1X the {@code x} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1Y the {@code y} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1Z the {@code z} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1W the {@code w} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting quaternion
     */
    public DoubleQuat squad(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t) {
        if (!Math.useFma()) return squad_mulAdd(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        double[] _bundle0 = squad_sa7490d9e_1_fma(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, t);
        double[] _bundle1 = squad_sa7490d9e_2_fma(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _bundle0[0], _bundle0[1], _bundle0[2], _bundle0[4], _bundle0[3]);
        double[] _bundle2 = squad_sa7490d9e_3_fma(t, _bundle0[0], _bundle1[2], _bundle1[3], _bundle1[4], _bundle1[5], _bundle1[6], _bundle1[7], _bundle1[8], java.lang.Math.fma(this.y, _bundle1[1], targetY * _bundle1[0]));
        return new DoubleQuat(_bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3]);
    }

    /** {@code squad} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat squad_mulAdd(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t) {
        double[] _bundle0 = squad_sa7490d9e_1_mulAdd(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, t);
        double[] _bundle1 = squad_sa7490d9e_2_mulAdd(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _bundle0[0], _bundle0[1], _bundle0[2], _bundle0[4], _bundle0[3]);
        double[] _bundle2 = squad_sa7490d9e_3_mulAdd(t, _bundle0[0], _bundle1[2], _bundle1[3], _bundle1[4], _bundle1[5], _bundle1[6], _bundle1[7], _bundle1[8], ((this.y) * (_bundle1[1]) + (targetY * _bundle1[0])));
        return new DoubleQuat(_bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3]);
    }

    /** Part 1 of {@code squad}, split to fit the inline budget; reached only through it. */
    private double[] squad_sa7490d9e_1_fma(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double t) {
        double _t0 = 1.0 - t;
        double _t3 = control0W + control1W;
        double _t4 = control0Z + control1Z;
        double _t5 = control0X + control1X;
        double _t6 = control0Y + control1Y;
        double _t7 = t < 0.5 ? 1.0 : 0.0;
        double _t12 = 1.0 - _t7;
        double _t25 = Math.min(4.0, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6))));
        double _t27 = quatArcAngle(_t25);
        double _t29 = 4.0 - _t25;
        double _t41 = java.lang.Math.sqrt(_t29 * _t25);
        double _t45 = 2.0 / _t41;
        double _t55, _t57;
        if (_t41 > 2.0E-14) {
            _t55 = _t45 * Math.sin(t * _t27);
            _t57 = _t45 * Math.sin(_t0 * _t27);
        } else {
            if (_t25 > _t29) {
                _t55 = t;
                _t57 = _t0;
            } else {
                _t55 = _t12;
                _t57 = _t7;
            }
        }
        return new double[] {_t0, _t7, _t12, _t55, _t57};
    }

    /** Part 1 of {@code squad}, split to fit the inline budget; reached only through it. */
    private double[] squad_sa7490d9e_1_mulAdd(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double t) {
        double _t0 = 1.0 - t;
        double _t3 = control0W + control1W;
        double _t4 = control0Z + control1Z;
        double _t5 = control0X + control1X;
        double _t6 = control0Y + control1Y;
        double _t7 = t < 0.5 ? 1.0 : 0.0;
        double _t12 = 1.0 - _t7;
        double _t25 = Math.min(4.0, ((_t3) * (_t3) + (((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6)))))));
        double _t27 = quatArcAngle(_t25);
        double _t29 = 4.0 - _t25;
        double _t41 = java.lang.Math.sqrt(_t29 * _t25);
        double _t45 = 2.0 / _t41;
        double _t55, _t57;
        if (_t41 > 2.0E-14) {
            _t55 = _t45 * Math.sin(t * _t27);
            _t57 = _t45 * Math.sin(_t0 * _t27);
        } else {
            if (_t25 > _t29) {
                _t55 = t;
                _t57 = _t0;
            } else {
                _t55 = _t12;
                _t57 = _t7;
            }
        }
        return new double[] {_t0, _t7, _t12, _t55, _t57};
    }

    /** Part 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private double[] squad_sa7490d9e_2_fma(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, double _t0, double _t7, double _t12, double _t57, double _t55) {
        double _t8 = this.w + targetW;
        double _t9 = this.z + targetZ;
        double _t10 = this.x + targetX;
        double _t11 = this.y + targetY;
        double _t26 = Math.min(4.0, java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11))));
        double _t28 = quatArcAngle(_t26);
        double _t30 = 4.0 - _t26;
        double _t43 = java.lang.Math.sqrt(_t30 * _t26);
        double _t46 = 2.0 / _t43;
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t46 * Math.sin(t * _t28);
            _t58 = _t46 * Math.sin(_t0 * _t28);
        } else {
            if (_t26 > _t30) {
                _t56 = t;
                _t58 = _t0;
            } else {
                _t56 = _t12;
                _t58 = _t7;
            }
        }
        return new double[] {_t56, _t58, java.lang.Math.fma(control0X, _t57, control1X * _t55), java.lang.Math.fma(control0W, _t57, control1W * _t55), java.lang.Math.fma(this.w, _t58, targetW * _t56), java.lang.Math.fma(control0Z, _t57, control1Z * _t55), java.lang.Math.fma(this.z, _t58, targetZ * _t56), java.lang.Math.fma(this.x, _t58, targetX * _t56), java.lang.Math.fma(control0Y, _t57, control1Y * _t55)};
    }

    /** Part 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private double[] squad_sa7490d9e_2_mulAdd(double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, double _t0, double _t7, double _t12, double _t57, double _t55) {
        double _t8 = this.w + targetW;
        double _t9 = this.z + targetZ;
        double _t10 = this.x + targetX;
        double _t11 = this.y + targetY;
        double _t26 = Math.min(4.0, ((_t8) * (_t8) + (((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))))));
        double _t28 = quatArcAngle(_t26);
        double _t30 = 4.0 - _t26;
        double _t43 = java.lang.Math.sqrt(_t30 * _t26);
        double _t46 = 2.0 / _t43;
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t46 * Math.sin(t * _t28);
            _t58 = _t46 * Math.sin(_t0 * _t28);
        } else {
            if (_t26 > _t30) {
                _t56 = t;
                _t58 = _t0;
            } else {
                _t56 = _t12;
                _t58 = _t7;
            }
        }
        return new double[] {_t56, _t58, ((control0X) * (_t57) + (control1X * _t55)), ((control0W) * (_t57) + (control1W * _t55)), ((this.w) * (_t58) + (targetW * _t56)), ((control0Z) * (_t57) + (control1Z * _t55)), ((this.z) * (_t58) + (targetZ * _t56)), ((this.x) * (_t58) + (targetX * _t56)), ((control0Y) * (_t57) + (control1Y * _t55))};
    }

    /** Part 3 of {@code squad}, split to fit the inline budget; reached only through it. */
    private double[] squad_sa7490d9e_3_fma(double t, double _t0, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t73, double _t74) {
        double _t1 = t + t;
        double _t13 = _t0 * _t1;
        double _t14 = java.lang.Math.fma(-_t0, _t1, 1.0);
        double _t17 = _t13 < 0.5 ? 1.0 : 0.0;
        double _t75 = _t68 + _t69;
        double _t76 = _t70 + _t71;
        double _t77 = _t67 + _t72;
        double _t78 = _t73 + _t74;
        double _t83 = Math.min(4.0, java.lang.Math.fma(_t75, _t75, java.lang.Math.fma(_t76, _t76, java.lang.Math.fma(_t77, _t77, _t78 * _t78))));
        double _t84 = quatArcAngle(_t83);
        double _t85 = 4.0 - _t83;
        double _t91 = java.lang.Math.sqrt(_t85 * _t83);
        double _t93 = 2.0 / _t91;
        double _t98, _t99;
        if (_t91 > 2.0E-14) {
            _t98 = _t93 * Math.sin(_t13 * _t84);
            _t99 = _t93 * Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0 - _t17;
                _t99 = _t17;
            }
        }
        double _sfx0 = java.lang.Math.fma(_t67, _t98, _t72 * _t99);
        double _sfx1 = java.lang.Math.fma(_t73, _t98, _t74 * _t99);
        double _sfx2 = java.lang.Math.fma(_t70, _t98, _t71 * _t99);
        double _sfx3 = java.lang.Math.fma(_t68, _t98, _t69 * _t99);
        return new double[] {_sfx0, _sfx1, _sfx2, _sfx3};
    }

    /** Part 3 of {@code squad}, split to fit the inline budget; reached only through it. */
    private double[] squad_sa7490d9e_3_mulAdd(double t, double _t0, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t73, double _t74) {
        double _t1 = t + t;
        double _t13 = _t0 * _t1;
        double _t14 = ((1.0) - (_t0) * (_t1));
        double _t17 = _t13 < 0.5 ? 1.0 : 0.0;
        double _t75 = _t68 + _t69;
        double _t76 = _t70 + _t71;
        double _t77 = _t67 + _t72;
        double _t78 = _t73 + _t74;
        double _t83 = Math.min(4.0, ((_t75) * (_t75) + (((_t76) * (_t76) + (((_t77) * (_t77) + (_t78 * _t78)))))));
        double _t84 = quatArcAngle(_t83);
        double _t85 = 4.0 - _t83;
        double _t91 = java.lang.Math.sqrt(_t85 * _t83);
        double _t93 = 2.0 / _t91;
        double _t98, _t99;
        if (_t91 > 2.0E-14) {
            _t98 = _t93 * Math.sin(_t13 * _t84);
            _t99 = _t93 * Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0 - _t17;
                _t99 = _t17;
            }
        }
        double _sfx0 = ((_t67) * (_t98) + (_t72 * _t99));
        double _sfx1 = ((_t73) * (_t98) + (_t74 * _t99));
        double _sfx2 = ((_t70) * (_t98) + (_t71 * _t99));
        double _sfx3 = ((_t68) * (_t98) + (_t69 * _t99));
        return new double[] {_sfx0, _sfx1, _sfx2, _sfx3};
    }

    /**
     * Multiply this quaternion by {@code other}, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code Q * R}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code Q * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand
     * @return the resulting quaternion
     */
    public DoubleQuat mul(DoubleQuat other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        double otherW = other.w;
        if (Math.useFma()) {
            return new DoubleQuat(java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherZ, this.y, -(otherY * this.z)), java.lang.Math.fma(otherX, this.z, otherW * this.y) + java.lang.Math.fma(otherY, this.w, otherZ * -this.x), java.lang.Math.fma(otherY, this.x, otherZ * this.w) + java.lang.Math.fma(otherW, this.z, -(otherX * this.y)), java.lang.Math.fma(otherW, this.w, otherX * -this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z));
        } else {
            return new DoubleQuat(((otherX) * (this.w) + (otherW * this.x)) + ((otherZ) * (this.y) - (otherY * this.z)), ((otherX) * (this.z) + (otherW * this.y)) + ((otherY) * (this.w) - (otherZ * this.x)), ((otherY) * (this.x) + (otherZ * this.w)) + ((otherW) * (this.z) - (otherX * this.y)), ((otherW) * (this.w) - (otherX * this.x)) - ((otherY) * (this.y) + (otherZ * this.z)));
        }
    }

    /**
     * Multiply this quaternion by ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}),
     * returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code Q * R}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code Q * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting quaternion
     */
    public DoubleQuat mul(double otherX, double otherY, double otherZ, double otherW) {
        if (Math.useFma()) {
            return new DoubleQuat(java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherZ, this.y, -(otherY * this.z)), java.lang.Math.fma(otherX, this.z, otherW * this.y) + java.lang.Math.fma(otherY, this.w, otherZ * -this.x), java.lang.Math.fma(otherY, this.x, otherZ * this.w) + java.lang.Math.fma(otherW, this.z, -(otherX * this.y)), java.lang.Math.fma(otherW, this.w, otherX * -this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z));
        } else {
            return new DoubleQuat(((otherX) * (this.w) + (otherW * this.x)) + ((otherZ) * (this.y) - (otherY * this.z)), ((otherX) * (this.z) + (otherW * this.y)) + ((otherY) * (this.w) - (otherZ * this.x)), ((otherY) * (this.x) + (otherZ * this.w)) + ((otherW) * (this.z) - (otherX * this.y)), ((otherW) * (this.w) - (otherX * this.x)) - ((otherY) * (this.y) + (otherZ * this.z)));
        }
    }

    /**
     * Pre-multiply the transformation {@code other} onto this quaternion, returning the result as a
     * value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code T} the given transformation quaternion,
     * then the new quaternion will be {@code T * Q}. So when transforming a vector {@code v} with
     * the new quaternion by using {@code T * Q * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @return the resulting quaternion
     */
    public DoubleQuat preMul(DoubleQuat other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        double otherW = other.w;
        if (Math.useFma()) {
            return new DoubleQuat(java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherY, this.z, -(otherZ * this.y)), java.lang.Math.fma(otherY, this.w, otherZ * this.x) + java.lang.Math.fma(otherW, this.y, -otherX * this.z), java.lang.Math.fma(otherX, this.y, otherW * this.z) + java.lang.Math.fma(otherZ, this.w, -(otherY * this.x)), java.lang.Math.fma(otherW, this.w, -otherX * this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z));
        } else {
            return new DoubleQuat(((otherX) * (this.w) + (otherW * this.x)) + ((otherY) * (this.z) - (otherZ * this.y)), ((otherY) * (this.w) + (otherZ * this.x)) + ((otherW) * (this.y) - (otherX * this.z)), ((otherX) * (this.y) + (otherW * this.z)) + ((otherZ) * (this.w) - (otherY * this.x)), ((otherW) * (this.w) - (otherX * this.x)) - ((otherY) * (this.y) + (otherZ * this.z)));
        }
    }

    /**
     * Pre-multiply the transformation ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) onto this quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code T} the given transformation quaternion,
     * then the new quaternion will be {@code T * Q}. So when transforming a vector {@code v} with
     * the new quaternion by using {@code T * Q * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting quaternion
     */
    public DoubleQuat preMul(double otherX, double otherY, double otherZ, double otherW) {
        if (Math.useFma()) {
            return new DoubleQuat(java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherY, this.z, -(otherZ * this.y)), java.lang.Math.fma(otherY, this.w, otherZ * this.x) + java.lang.Math.fma(otherW, this.y, -otherX * this.z), java.lang.Math.fma(otherX, this.y, otherW * this.z) + java.lang.Math.fma(otherZ, this.w, -(otherY * this.x)), java.lang.Math.fma(otherW, this.w, -otherX * this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z));
        } else {
            return new DoubleQuat(((otherX) * (this.w) + (otherW * this.x)) + ((otherY) * (this.z) - (otherZ * this.y)), ((otherY) * (this.w) + (otherZ * this.x)) + ((otherW) * (this.y) - (otherX * this.z)), ((otherX) * (this.y) + (otherW * this.z)) + ((otherZ) * (this.w) - (otherY * this.x)), ((otherW) * (this.w) - (otherX * this.x)) - ((otherY) * (this.y) + (otherZ * this.z)));
        }
    }

    /**
     * Add {@code other} scaled by {@code weight} to this quaternion, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @return the resulting quaternion
     */
    public DoubleQuat addScaled(DoubleQuat other, double weight) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        double otherW = other.w;
        if (Math.useFma()) {
            return new DoubleQuat(java.lang.Math.fma(weight, otherX, this.x), java.lang.Math.fma(weight, otherY, this.y), java.lang.Math.fma(weight, otherZ, this.z), java.lang.Math.fma(weight, otherW, this.w));
        } else {
            return new DoubleQuat(((weight) * (otherX) + (this.x)), ((weight) * (otherY) + (this.y)), ((weight) * (otherZ) + (this.z)), ((weight) * (otherW) + (this.w)));
        }
    }

    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) scaled by {@code weight}
     * to this quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param weight the factor to scale ({@code otherX}, {@code otherY}, {@code otherZ},
     *        {@code otherW}) by before adding
     * @return the resulting quaternion
     */
    public DoubleQuat addScaled(double otherX, double otherY, double otherZ, double otherW, double weight) {
        if (Math.useFma()) {
            return new DoubleQuat(java.lang.Math.fma(weight, otherX, this.x), java.lang.Math.fma(weight, otherY, this.y), java.lang.Math.fma(weight, otherZ, this.z), java.lang.Math.fma(weight, otherW, this.w));
        } else {
            return new DoubleQuat(((weight) * (otherX) + (this.x)), ((weight) * (otherY) + (this.y)), ((weight) * (otherZ) + (this.z)), ((weight) * (otherW) + (this.w)));
        }
    }

    /**
     * Compute the rotation angle in radians of this quaternion, within {@code [0, 2*PI]}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the rotation angle in radians of this quaternion, within {@code [0, 2*PI]}
     */
    public double angle() {
        if (Math.useFma()) {
            return 2.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y))), this.w);
        } else {
            return 2.0 * Math.atan2(java.lang.Math.sqrt(((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))), this.w);
        }
    }

    /**
     * Compute the angle in radians of the rotation that takes this quaternion to {@code other},
     * within {@code [0, PI]} (a quaternion and its negation, the same rotation, give 0).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: this quaternion must have unit length; {@code other} must have unit length.
     *
     * @param other the quaternion to measure the angle to
     * @return the angle in radians of the rotation that takes this quaternion to {@code other},
     *        within {@code [0, PI]} (a quaternion and its negation, the same rotation, give 0)
     */
    public double angleTo(DoubleQuat other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        double otherW = other.w;
        if (!Math.useFma()) return angleTo_mulAdd(otherX, otherY, otherZ, otherW);
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(otherW, this.w, java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y))) > 0.0) {
            _t9 = -otherW;
            _t10 = -otherZ;
            _t11 = -otherX;
            _t12 = -otherY;
        } else {
            _t9 = otherW;
            _t10 = otherZ;
            _t11 = otherX;
            _t12 = otherY;
        }
        double _t13 = this.w - _t9;
        double _t14 = this.z - _t10;
        double _t15 = this.x - _t11;
        double _t16 = this.y - _t12;
        double _t17 = this.w + _t9;
        double _t18 = this.z + _t10;
        double _t19 = this.x + _t11;
        double _t20 = this.y + _t12;
        return 4.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)))), java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /**
     * Compute the angle in radians of the rotation that takes this quaternion to ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}), within {@code [0, PI]} (a quaternion and its
     * negation, the same rotation, give 0).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: this quaternion must have unit length; {@code (otherX, otherY, otherZ, otherW)}
     * must have unit length.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return the angle in radians of the rotation that takes this quaternion to ({@code otherX},
     *        {@code otherY}, {@code otherZ}, {@code otherW}), within {@code [0, PI]} (a quaternion
     *        and its negation, the same rotation, give 0)
     */
    public double angleTo(double otherX, double otherY, double otherZ, double otherW) {
        if (!Math.useFma()) return angleTo_mulAdd(otherX, otherY, otherZ, otherW);
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(otherW, this.w, java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y))) > 0.0) {
            _t9 = -otherW;
            _t10 = -otherZ;
            _t11 = -otherX;
            _t12 = -otherY;
        } else {
            _t9 = otherW;
            _t10 = otherZ;
            _t11 = otherX;
            _t12 = otherY;
        }
        double _t13 = this.w - _t9;
        double _t14 = this.z - _t10;
        double _t15 = this.x - _t11;
        double _t16 = this.y - _t12;
        double _t17 = this.w + _t9;
        double _t18 = this.z + _t10;
        double _t19 = this.x + _t11;
        double _t20 = this.y + _t12;
        return 4.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)))), java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /** {@code angleTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private double angleTo_mulAdd(double otherX, double otherY, double otherZ, double otherW) {
        double _t9, _t10, _t11, _t12;
        if (-((otherW) * (this.w) + (((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y)))))) > 0.0) {
            _t9 = -otherW;
            _t10 = -otherZ;
            _t11 = -otherX;
            _t12 = -otherY;
        } else {
            _t9 = otherW;
            _t10 = otherZ;
            _t11 = otherX;
            _t12 = otherY;
        }
        double _t13 = this.w - _t9;
        double _t14 = this.z - _t10;
        double _t15 = this.x - _t11;
        double _t16 = this.y - _t12;
        double _t17 = this.w + _t9;
        double _t18 = this.z + _t10;
        double _t19 = this.x + _t11;
        double _t20 = this.y + _t12;
        return 4.0 * Math.atan2(java.lang.Math.sqrt(((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))))), java.lang.Math.sqrt(((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))))));
    }

    /**
     * Get the normalized rotation axis of this quaternion (zero when the rotation angle is zero),
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 axis() {
        if (Math.useFma()) {
            double _t2 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0) {
                return new Double3(this.x * _t3, this.y * _t3, this.z * _t3);
            } else {
                return Double3.ZERO;
            }
        } else {
            double _t2 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0) {
                return new Double3(this.x * _t3, this.y * _t3, this.z * _t3);
            } else {
                return Double3.ZERO;
            }
        }
    }

    /**
     * Recompute the {@code w} component of this quaternion from {@code x}, {@code y} and {@code z},
     * assuming unit length (the positive square root is chosen), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting quaternion
     */
    public DoubleQuat calculateW() {
        if (Math.useFma()) {
            return new DoubleQuat(this.x, this.y, this.z, java.lang.Math.sqrt(Math.max(0.0, java.lang.Math.fma(-this.x, this.x, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(-this.z, this.z, 1.0))))));
        } else {
            return new DoubleQuat(this.x, this.y, this.z, java.lang.Math.sqrt(Math.max(0.0, ((((((1.0) - (this.z) * (this.z))) - (this.y) * (this.y))) - (this.x) * (this.x)))));
        }
    }

    /**
     * Conjugate this quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting quaternion
     */
    public DoubleQuat conjugate() {
        return new DoubleQuat(-this.x, -this.y, -this.z, this.w);
    }

    /**
     * Conjugate this quaternion by {@code q}, i.e. compute {@code q * this * conj(q)} where
     * {@code q} is the given quaternion (equal to {@code q * this * q^-1} when it has unit length),
     * returning the result as a value.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the quaternion to conjugate by
     * @return the resulting quaternion
     */
    public DoubleQuat conjugateBy(DoubleQuat q) {
        double qX = q.x;
        double qY = q.y;
        double qZ = q.z;
        double qW = q.w;
        if (!Math.useFma()) return conjugateBy_mulAdd(qX, qY, qZ, qW);
        double _t20 = java.lang.Math.fma(qX, this.y, qW * this.z) + java.lang.Math.fma(qZ, this.w, qY * -this.x);
        double _t21 = java.lang.Math.fma(qY, this.w, qZ * this.x) + java.lang.Math.fma(qW, this.y, -(qX * this.z));
        double _t22 = java.lang.Math.fma(qX, this.w, qW * this.x) + java.lang.Math.fma(qY, this.z, -(qZ * this.y));
        double _t23 = java.lang.Math.fma(qW, this.w, qX * -this.x) - java.lang.Math.fma(qY, this.y, qZ * this.z);
        return new DoubleQuat(java.lang.Math.fma(qY, _t20, -qZ * _t21) + java.lang.Math.fma(qW, _t22, -qX * _t23), java.lang.Math.fma(qZ, _t22, -qY * _t23) + java.lang.Math.fma(qW, _t21, -qX * _t20), java.lang.Math.fma(qX, _t21, qW * _t20) + java.lang.Math.fma(-qY, _t22, -qZ * _t23), java.lang.Math.fma(qX, _t22, qW * _t23) - java.lang.Math.fma(-qZ, _t20, -qY * _t21));
    }

    /**
     * Conjugate this quaternion by ({@code qX}, {@code qY}, {@code qZ}, {@code qW}), i.e. compute
     * {@code q * this * conj(q)} where {@code q} is the given quaternion (equal to
     * {@code q * this * q^-1} when it has unit length), returning the result as a value.
     * <p>
     * Valid input: {@code (qX, qY, qZ, qW)} must have unit length.
     *
     * @param qX the {@code x} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qY the {@code y} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qZ the {@code z} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qW the {@code w} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @return the resulting quaternion
     */
    public DoubleQuat conjugateBy(double qX, double qY, double qZ, double qW) {
        if (!Math.useFma()) return conjugateBy_mulAdd(qX, qY, qZ, qW);
        double _t20 = java.lang.Math.fma(qX, this.y, qW * this.z) + java.lang.Math.fma(qZ, this.w, qY * -this.x);
        double _t21 = java.lang.Math.fma(qY, this.w, qZ * this.x) + java.lang.Math.fma(qW, this.y, -(qX * this.z));
        double _t22 = java.lang.Math.fma(qX, this.w, qW * this.x) + java.lang.Math.fma(qY, this.z, -(qZ * this.y));
        double _t23 = java.lang.Math.fma(qW, this.w, qX * -this.x) - java.lang.Math.fma(qY, this.y, qZ * this.z);
        return new DoubleQuat(java.lang.Math.fma(qY, _t20, -qZ * _t21) + java.lang.Math.fma(qW, _t22, -qX * _t23), java.lang.Math.fma(qZ, _t22, -qY * _t23) + java.lang.Math.fma(qW, _t21, -qX * _t20), java.lang.Math.fma(qX, _t21, qW * _t20) + java.lang.Math.fma(-qY, _t22, -qZ * _t23), java.lang.Math.fma(qX, _t22, qW * _t23) - java.lang.Math.fma(-qZ, _t20, -qY * _t21));
    }

    /** {@code conjugateBy} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat conjugateBy_mulAdd(double qX, double qY, double qZ, double qW) {
        double _t20 = ((qX) * (this.y) + (qW * this.z)) + ((qZ) * (this.w) - (qY * this.x));
        double _t21 = ((qY) * (this.w) + (qZ * this.x)) + ((qW) * (this.y) - (qX * this.z));
        double _t22 = ((qX) * (this.w) + (qW * this.x)) + ((qY) * (this.z) - (qZ * this.y));
        double _t23 = ((qW) * (this.w) - (qX * this.x)) - ((qY) * (this.y) + (qZ * this.z));
        return new DoubleQuat(((qY) * (_t20) - (qZ * _t21)) + ((qW) * (_t22) - (qX * _t23)), ((qZ) * (_t22) - (qY * _t23)) + ((qW) * (_t21) - (qX * _t20)), ((qX) * (_t21) + (qW * _t20)) + ((-qY) * (_t22) - (qZ * _t23)), ((qX) * (_t22) + (qW * _t23)) - ((-qZ) * (_t20) - (qY * _t21)));
    }

    /**
     * Compute the difference between this quaternion and {@code other}, i.e. the rotation {@code D}
     * with {@code this * D = other}, that is {@code D = this^-1 * other}, returning the result as a
     * value.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param other the target quaternion, reached by composing this quaternion with the result
     * @return the resulting quaternion
     */
    public DoubleQuat difference(DoubleQuat other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        double otherW = other.w;
        if (!Math.useFma()) return difference_mulAdd(otherX, otherY, otherZ, otherW);
        double _t3_inv = 1.0 / java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
        double _sp1 = _t3_inv * this.z;
        double _sp0 = this.y * _t3_inv;
        return new DoubleQuat((java.lang.Math.fma(otherX, this.w, -otherW * this.x) + java.lang.Math.fma(otherY, this.z, -otherZ * this.y)) * _t3_inv, java.lang.Math.fma(java.lang.Math.fma(otherY, this.w, otherZ * this.x), _t3_inv, java.lang.Math.fma(-otherX, _sp1, -otherW * _sp0)), (java.lang.Math.fma(otherX, this.y, -otherW * this.z) + java.lang.Math.fma(otherZ, this.w, -otherY * this.x)) * _t3_inv, java.lang.Math.fma(java.lang.Math.fma(otherX, this.x, otherW * this.w), _t3_inv, java.lang.Math.fma(otherZ, _sp1, otherY * _sp0)));
    }

    /**
     * Compute the difference between this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}), i.e. the rotation {@code D} with
     * {@code this * D = (otherX, otherY, otherZ, otherW)}, that is
     * {@code D = this^-1 * (otherX, otherY, otherZ, otherW)}, returning the result as a value.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting quaternion
     */
    public DoubleQuat difference(double otherX, double otherY, double otherZ, double otherW) {
        if (!Math.useFma()) return difference_mulAdd(otherX, otherY, otherZ, otherW);
        double _t3_inv = 1.0 / java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
        double _sp1 = _t3_inv * this.z;
        double _sp0 = this.y * _t3_inv;
        return new DoubleQuat((java.lang.Math.fma(otherX, this.w, -otherW * this.x) + java.lang.Math.fma(otherY, this.z, -otherZ * this.y)) * _t3_inv, java.lang.Math.fma(java.lang.Math.fma(otherY, this.w, otherZ * this.x), _t3_inv, java.lang.Math.fma(-otherX, _sp1, -otherW * _sp0)), (java.lang.Math.fma(otherX, this.y, -otherW * this.z) + java.lang.Math.fma(otherZ, this.w, -otherY * this.x)) * _t3_inv, java.lang.Math.fma(java.lang.Math.fma(otherX, this.x, otherW * this.w), _t3_inv, java.lang.Math.fma(otherZ, _sp1, otherY * _sp0)));
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat difference_mulAdd(double otherX, double otherY, double otherZ, double otherW) {
        double _t3_inv = 1.0 / ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
        double _sp1 = _t3_inv * this.z;
        double _sp0 = this.y * _t3_inv;
        return new DoubleQuat((((otherX) * (this.w) - (otherW * this.x)) + ((otherY) * (this.z) - (otherZ * this.y))) * _t3_inv, ((((otherY) * (this.w) + (otherZ * this.x))) * (_t3_inv) + (((-otherX) * (_sp1) - (otherW * _sp0)))), (((otherX) * (this.y) - (otherW * this.z)) + ((otherZ) * (this.w) - (otherY * this.x))) * _t3_inv, ((((otherX) * (this.x) + (otherW * this.w))) * (_t3_inv) + (((otherZ) * (_sp1) + (otherY * _sp0)))));
    }

    /**
     * Compute the dot product of this quaternion and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this quaternion and {@code other}
     */
    public double dot(DoubleQuat other) {
        double otherX = other.x;
        double otherY = other.y;
        double otherZ = other.z;
        double otherW = other.w;
        if (Math.useFma()) {
            return java.lang.Math.fma(otherW, this.w, java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y)));
        } else {
            return ((otherW) * (this.w) + (((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y))))));
        }
    }

    /**
     * Compute the dot product of this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return the dot product of this quaternion and ({@code otherX}, {@code otherY},
     *        {@code otherZ}, {@code otherW})
     */
    public double dot(double otherX, double otherY, double otherZ, double otherW) {
        if (Math.useFma()) {
            return java.lang.Math.fma(otherW, this.w, java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y)));
        } else {
            return ((otherW) * (this.w) + (((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y))))));
        }
    }

    /**
     * Compute the exponential of this quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting quaternion
     */
    public DoubleQuat exp() {
        if (Math.useFma()) {
            double _t2 = Math.min(Math.exp(this.w), 1.7976931348623157E308);
            double _t4 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            double _t5 = java.lang.Math.sqrt(_t4);
            double _t7 = Math.sin(_t5);
            double _t10 = _t2 * (_t7 / _t5);
            if (_t4 > 0.0) {
                return new DoubleQuat(this.x * _t10, this.y * _t10, this.z * _t10, Math.cosFromSin(_t7, _t5) * _t2);
            } else {
                return new DoubleQuat(0.0, 0.0, 0.0, Math.cosFromSin(_t7, _t5) * _t2);
            }
        } else {
            double _t2 = Math.min(Math.exp(this.w), 1.7976931348623157E308);
            double _t4 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            double _t5 = java.lang.Math.sqrt(_t4);
            double _t7 = Math.sin(_t5);
            double _t10 = _t2 * (_t7 / _t5);
            if (_t4 > 0.0) {
                return new DoubleQuat(this.x * _t10, this.y * _t10, this.z * _t10, Math.cosFromSin(_t7, _t5) * _t2);
            } else {
                return new DoubleQuat(0.0, 0.0, 0.0, Math.cosFromSin(_t7, _t5) * _t2);
            }
        }
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the X, Y and Z axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first, then Y,
     * then X), returning the result as a value.
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
     * Valid input: this quaternion must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesXYZ() {
        if (!Math.useFma()) return getEulerAnglesXYZ_mulAdd();
        double _t1 = this.y * this.z;
        double _t3 = this.z * this.z;
        double _t8 = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.w, -_t1);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0);
        double _t12 = java.lang.Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < java.lang.Math.fma(_t8, _t8, _t12) * 1.0E-15) {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.x, this.w, _t1), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t3), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, _t10), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * java.lang.Math.fma(this.z, this.w, -(this.x * this.y)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t3), 1.0)));
        }
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd() {
        double _t1 = this.y * this.z;
        double _t3 = this.z * this.z;
        double _t8 = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
        double _t9 = 2.0 * ((this.x) * (this.w) - (_t1));
        double _t10 = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))));
        double _t12 = ((_t10) * (_t10) + (_t9 * _t9));
        if (_t12 < ((_t8) * (_t8) + (_t12)) * 1.0E-15) {
            return new Double3(Math.atan2(2.0 * ((this.x) * (this.w) + (_t1)), ((1.0) - (2.0) * (((this.x) * (this.x) + (_t3))))), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, _t10), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * ((this.z) * (this.w) - (this.x * this.y)), ((1.0) - (2.0) * (((this.y) * (this.y) + (_t3))))));
        }
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the X, Y and Z axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first, then Y,
     * then X), returning the result as a value.
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
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
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
        double _t1 = this.y * this.z;
        double _t3 = this.z * this.z;
        double _t8 = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.w, -_t1);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0);
        double _t12 = java.lang.Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < epsilon * java.lang.Math.fma(_t8, _t8, _t12)) {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.x, this.w, _t1), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t3), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, _t10), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * java.lang.Math.fma(this.z, this.w, -(this.x * this.y)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t3), 1.0)));
        }
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd(double epsilon) {
        double _t1 = this.y * this.z;
        double _t3 = this.z * this.z;
        double _t8 = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
        double _t9 = 2.0 * ((this.x) * (this.w) - (_t1));
        double _t10 = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))));
        double _t12 = ((_t10) * (_t10) + (_t9 * _t9));
        if (_t12 < epsilon * ((_t8) * (_t8) + (_t12))) {
            return new Double3(Math.atan2(2.0 * ((this.x) * (this.w) + (_t1)), ((1.0) - (2.0) * (((this.x) * (this.x) + (_t3))))), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, _t10), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * ((this.z) * (this.w) - (this.x * this.y)), ((1.0) - (2.0) * (((this.y) * (this.y) + (_t3))))));
        }
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the X, Z and Y axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first, then Z,
     * then X), returning the result as a value.
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
     * Valid input: this quaternion must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesXZY() {
        if (!Math.useFma()) return getEulerAnglesXZY_mulAdd();
        double _t0 = this.z * this.z;
        double _t1 = this.y * this.z;
        double _t7 = 2.0 * java.lang.Math.fma(this.x, this.w, _t1);
        double _t8 = 2.0 * java.lang.Math.fma(this.z, this.w, -(this.x * this.y));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < java.lang.Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.x, this.w, -_t1), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0)), 0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(_t7, _t9), Math.atan2(2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t0), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        }
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd() {
        double _t0 = this.z * this.z;
        double _t1 = this.y * this.z;
        double _t7 = 2.0 * ((this.x) * (this.w) + (_t1));
        double _t8 = 2.0 * ((this.z) * (this.w) - (this.x * this.y));
        double _t9 = ((1.0) - (2.0) * (((this.x) * (this.x) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < ((_t8) * (_t8) + (_t11)) * 1.0E-15) {
            return new Double3(Math.atan2(2.0 * ((this.x) * (this.w) - (_t1)), ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))))), 0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(_t7, _t9), Math.atan2(2.0 * ((this.x) * (this.z) + (this.y * this.w)), ((1.0) - (2.0) * (((this.y) * (this.y) + (_t0))))), Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        }
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the X, Z and Y axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first, then Z,
     * then X), returning the result as a value.
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
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
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
        double _t0 = this.z * this.z;
        double _t1 = this.y * this.z;
        double _t7 = 2.0 * java.lang.Math.fma(this.x, this.w, _t1);
        double _t8 = 2.0 * java.lang.Math.fma(this.z, this.w, -(this.x * this.y));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < epsilon * java.lang.Math.fma(_t8, _t8, _t11)) {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.x, this.w, -_t1), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0)), 0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(_t7, _t9), Math.atan2(2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t0), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        }
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd(double epsilon) {
        double _t0 = this.z * this.z;
        double _t1 = this.y * this.z;
        double _t7 = 2.0 * ((this.x) * (this.w) + (_t1));
        double _t8 = 2.0 * ((this.z) * (this.w) - (this.x * this.y));
        double _t9 = ((1.0) - (2.0) * (((this.x) * (this.x) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < epsilon * ((_t8) * (_t8) + (_t11))) {
            return new Double3(Math.atan2(2.0 * ((this.x) * (this.w) - (_t1)), ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))))), 0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(_t7, _t9), Math.atan2(2.0 * ((this.x) * (this.z) + (this.y * this.w)), ((1.0) - (2.0) * (((this.y) * (this.y) + (_t0))))), Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        }
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the Y, X and Z axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first, then X,
     * then Y), returning the result as a value.
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
     * Valid input: this quaternion must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesYXZ() {
        if (!Math.useFma()) return getEulerAnglesYXZ_mulAdd();
        double _t3 = this.z * this.z;
        double _t8 = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * -this.z);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0);
        double _t12 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < java.lang.Math.fma(_t9, _t9, _t12) * 1.0E-15) {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * java.lang.Math.fma(this.y, this.w, this.x * -this.z), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t3), 1.0)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(_t8, _t10), Math.atan2(2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t3), 1.0)));
        }
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd() {
        double _t3 = this.z * this.z;
        double _t8 = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
        double _t9 = 2.0 * ((this.x) * (this.w) - (this.y * this.z));
        double _t10 = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))));
        double _t12 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t12 < ((_t9) * (_t9) + (_t12)) * 1.0E-15) {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * ((this.y) * (this.w) - (this.x * this.z)), ((1.0) - (2.0) * (((this.y) * (this.y) + (_t3))))), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(_t8, _t10), Math.atan2(2.0 * ((this.x) * (this.y) + (this.z * this.w)), ((1.0) - (2.0) * (((this.x) * (this.x) + (_t3))))));
        }
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the Y, X and Z axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first, then X,
     * then Y), returning the result as a value.
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
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
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
        double _t3 = this.z * this.z;
        double _t8 = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * -this.z);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0);
        double _t12 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < epsilon * java.lang.Math.fma(_t9, _t9, _t12)) {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * java.lang.Math.fma(this.y, this.w, this.x * -this.z), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t3), 1.0)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(_t8, _t10), Math.atan2(2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t3), 1.0)));
        }
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd(double epsilon) {
        double _t3 = this.z * this.z;
        double _t8 = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
        double _t9 = 2.0 * ((this.x) * (this.w) - (this.y * this.z));
        double _t10 = ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))));
        double _t12 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t12 < epsilon * ((_t9) * (_t9) + (_t12))) {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * ((this.y) * (this.w) - (this.x * this.z)), ((1.0) - (2.0) * (((this.y) * (this.y) + (_t3))))), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(_t8, _t10), Math.atan2(2.0 * ((this.x) * (this.y) + (this.z * this.w)), ((1.0) - (2.0) * (((this.x) * (this.x) + (_t3))))));
        }
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the Y, Z and X axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first, then Z,
     * then Y), returning the result as a value.
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
     * Valid input: this quaternion must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesYZX() {
        if (!Math.useFma()) return getEulerAnglesYZX_mulAdd();
        double _t0 = this.z * this.z;
        double _t7 = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        double _t8 = 2.0 * java.lang.Math.fma(this.y, this.w, -(this.x * this.z));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < java.lang.Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0)), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.x, this.w, -(this.y * this.z)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t0), 1.0)), Math.atan2(_t8, _t9), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        }
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd() {
        double _t0 = this.z * this.z;
        double _t7 = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
        double _t8 = 2.0 * ((this.y) * (this.w) - (this.x * this.z));
        double _t9 = ((1.0) - (2.0) * (((this.y) * (this.y) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        if (_t11 < ((_t7) * (_t7) + (_t11)) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(2.0 * ((this.x) * (this.z) + (this.y * this.w)), ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))))), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(2.0 * ((this.x) * (this.w) - (this.y * this.z)), ((1.0) - (2.0) * (((this.x) * (this.x) + (_t0))))), Math.atan2(_t8, _t9), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        }
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the Y, Z and X axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first, then Z,
     * then Y), returning the result as a value.
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
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
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
        double _t0 = this.z * this.z;
        double _t7 = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        double _t8 = 2.0 * java.lang.Math.fma(this.y, this.w, -(this.x * this.z));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < epsilon * java.lang.Math.fma(_t7, _t7, _t11)) {
            return new Double3(0.0, Math.atan2(2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0)), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.x, this.w, -(this.y * this.z)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t0), 1.0)), Math.atan2(_t8, _t9), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        }
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd(double epsilon) {
        double _t0 = this.z * this.z;
        double _t7 = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
        double _t8 = 2.0 * ((this.y) * (this.w) - (this.x * this.z));
        double _t9 = ((1.0) - (2.0) * (((this.y) * (this.y) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        if (_t11 < epsilon * ((_t7) * (_t7) + (_t11))) {
            return new Double3(0.0, Math.atan2(2.0 * ((this.x) * (this.z) + (this.y * this.w)), ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))))), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(2.0 * ((this.x) * (this.w) - (this.y * this.z)), ((1.0) - (2.0) * (((this.x) * (this.x) + (_t0))))), Math.atan2(_t8, _t9), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        }
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the Z, X and Y axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first, then X,
     * then Z), returning the result as a value.
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
     * Valid input: this quaternion must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesZXY() {
        if (!Math.useFma()) return getEulerAnglesZXY_mulAdd();
        double _t1 = this.z * this.z;
        double _t7 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        double _t8 = 2.0 * java.lang.Math.fma(this.z, this.w, -(this.x * this.y));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t1), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < java.lang.Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), 0.0, Math.atan2(2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t1), 1.0)));
        } else {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * java.lang.Math.fma(this.y, this.w, -(this.x * this.z)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0)), Math.atan2(_t8, _t9));
        }
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd() {
        double _t1 = this.z * this.z;
        double _t7 = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
        double _t8 = 2.0 * ((this.z) * (this.w) - (this.x * this.y));
        double _t9 = ((1.0) - (2.0) * (((this.x) * (this.x) + (_t1))));
        double _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        if (_t11 < ((_t7) * (_t7) + (_t11)) * 1.0E-15) {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), 0.0, Math.atan2(2.0 * ((this.x) * (this.y) + (this.z * this.w)), ((1.0) - (2.0) * (((this.y) * (this.y) + (_t1))))));
        } else {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * ((this.y) * (this.w) - (this.x * this.z)), ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))))), Math.atan2(_t8, _t9));
        }
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the Z, X and Y axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first, then X,
     * then Z), returning the result as a value.
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
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
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
        double _t1 = this.z * this.z;
        double _t7 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        double _t8 = 2.0 * java.lang.Math.fma(this.z, this.w, -(this.x * this.y));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t1), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < epsilon * java.lang.Math.fma(_t7, _t7, _t11)) {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), 0.0, Math.atan2(2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t1), 1.0)));
        } else {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * java.lang.Math.fma(this.y, this.w, -(this.x * this.z)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0)), Math.atan2(_t8, _t9));
        }
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd(double epsilon) {
        double _t1 = this.z * this.z;
        double _t7 = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
        double _t8 = 2.0 * ((this.z) * (this.w) - (this.x * this.y));
        double _t9 = ((1.0) - (2.0) * (((this.x) * (this.x) + (_t1))));
        double _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        if (_t11 < epsilon * ((_t7) * (_t7) + (_t11))) {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), 0.0, Math.atan2(2.0 * ((this.x) * (this.y) + (this.z * this.w)), ((1.0) - (2.0) * (((this.y) * (this.y) + (_t1))))));
        } else {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * ((this.y) * (this.w) - (this.x * this.z)), ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))))), Math.atan2(_t8, _t9));
        }
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the Z, Y and X axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first, then Y,
     * then Z), returning the result as a value.
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
     * Valid input: this quaternion must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesZYX() {
        if (!Math.useFma()) return getEulerAnglesZYX_mulAdd();
        double _t0 = this.z * this.z;
        double _t7 = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        double _t8 = 2.0 * java.lang.Math.fma(this.y, this.w, -this.x * this.z);
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < java.lang.Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * java.lang.Math.fma(this.z, this.w, -this.x * this.y), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t0), 1.0)));
        } else {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(_t7, _t9));
        }
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd() {
        double _t0 = this.z * this.z;
        double _t7 = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
        double _t8 = 2.0 * ((this.y) * (this.w) - (this.x * this.z));
        double _t9 = ((1.0) - (2.0) * (((this.y) * (this.y) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < ((_t8) * (_t8) + (_t11)) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * ((this.z) * (this.w) - (this.x * this.y)), ((1.0) - (2.0) * (((this.x) * (this.x) + (_t0))))));
        } else {
            return new Double3(Math.atan2(2.0 * ((this.x) * (this.w) + (this.y * this.z)), ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))))), Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(_t7, _t9));
        }
    }

    /**
     * Get the Euler angles in radians of this quaternion for rotations about the Z, Y and X axes,
     * in that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first, then Y,
     * then Z), returning the result as a value.
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
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
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
        double _t0 = this.z * this.z;
        double _t7 = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        double _t8 = 2.0 * java.lang.Math.fma(this.y, this.w, -this.x * this.z);
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < epsilon * java.lang.Math.fma(_t8, _t8, _t11)) {
            return new Double3(0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * java.lang.Math.fma(this.z, this.w, -this.x * this.y), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, _t0), 1.0)));
        } else {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(_t7, _t9));
        }
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd(double epsilon) {
        double _t0 = this.z * this.z;
        double _t7 = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
        double _t8 = 2.0 * ((this.y) * (this.w) - (this.x * this.z));
        double _t9 = ((1.0) - (2.0) * (((this.y) * (this.y) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < epsilon * ((_t8) * (_t8) + (_t11))) {
            return new Double3(0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * ((this.z) * (this.w) - (this.x * this.y)), ((1.0) - (2.0) * (((this.x) * (this.x) + (_t0))))));
        } else {
            return new Double3(Math.atan2(2.0 * ((this.x) * (this.w) + (this.y * this.z)), ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y))))), Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(_t7, _t9));
        }
    }

    /**
     * Integrate the given angular velocity over the given time step and pre-multiply the resulting
     * rotation onto this quaternion ({@code dq * this}: the angular velocity is given in the
     * reference frame, not in this quaternion's local one), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angularVel the angular velocity, in radians per second, applied in the reference frame
     * @param dt the time step
     * @return the resulting quaternion
     */
    public DoubleQuat integrate(Double3 angularVel, double dt) {
        double angularVelX = angularVel.x();
        double angularVelY = angularVel.y();
        double angularVelZ = angularVel.z();
        if (!Math.useFma()) return integrate_mulAdd(angularVelX, angularVelY, angularVelZ, dt);
        double _t0 = 0.5 * dt;
        double _t1 = angularVelZ * _t0;
        double _t2 = angularVelX * _t0;
        double _t3 = angularVelY * _t0;
        double _t6 = java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.sin(_t7);
        double _t10 = Math.cosFromSin(_t9, _t7);
        double _t11 = _t9 / _t7;
        double _t15, _t16, _t17;
        if (_t6 > 0.0) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        return new DoubleQuat(java.lang.Math.fma(this.x, _t10, this.w * _t15) + java.lang.Math.fma(this.z, _t16, -(this.y * _t17)), java.lang.Math.fma(this.x, _t17, this.w * _t16) + java.lang.Math.fma(this.y, _t10, -(this.z * _t15)), java.lang.Math.fma(this.y, _t15, this.z * _t10) + java.lang.Math.fma(this.w, _t17, -this.x * _t16), java.lang.Math.fma(this.w, _t10, -this.x * _t15) - java.lang.Math.fma(this.y, _t16, this.z * _t17));
    }

    /**
     * Integrate the given angular velocity over the given time step and pre-multiply the resulting
     * rotation onto this quaternion ({@code dq * this}: the angular velocity is given in the
     * reference frame, not in this quaternion's local one), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angularVelX the {@code x} component of the vector
     *        {@code (angularVelX, angularVelY, angularVelZ)}
     * @param angularVelY the {@code y} component of the vector
     *        {@code (angularVelX, angularVelY, angularVelZ)}
     * @param angularVelZ the {@code z} component of the vector
     *        {@code (angularVelX, angularVelY, angularVelZ)}
     * @param dt the time step
     * @return the resulting quaternion
     */
    public DoubleQuat integrate(double angularVelX, double angularVelY, double angularVelZ, double dt) {
        if (!Math.useFma()) return integrate_mulAdd(angularVelX, angularVelY, angularVelZ, dt);
        double _t0 = 0.5 * dt;
        double _t1 = angularVelZ * _t0;
        double _t2 = angularVelX * _t0;
        double _t3 = angularVelY * _t0;
        double _t6 = java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.sin(_t7);
        double _t10 = Math.cosFromSin(_t9, _t7);
        double _t11 = _t9 / _t7;
        double _t15, _t16, _t17;
        if (_t6 > 0.0) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        return new DoubleQuat(java.lang.Math.fma(this.x, _t10, this.w * _t15) + java.lang.Math.fma(this.z, _t16, -(this.y * _t17)), java.lang.Math.fma(this.x, _t17, this.w * _t16) + java.lang.Math.fma(this.y, _t10, -(this.z * _t15)), java.lang.Math.fma(this.y, _t15, this.z * _t10) + java.lang.Math.fma(this.w, _t17, -this.x * _t16), java.lang.Math.fma(this.w, _t10, -this.x * _t15) - java.lang.Math.fma(this.y, _t16, this.z * _t17));
    }

    /** {@code integrate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat integrate_mulAdd(double angularVelX, double angularVelY, double angularVelZ, double dt) {
        double _t0 = 0.5 * dt;
        double _t1 = angularVelZ * _t0;
        double _t2 = angularVelX * _t0;
        double _t3 = angularVelY * _t0;
        double _t6 = ((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.sin(_t7);
        double _t10 = Math.cosFromSin(_t9, _t7);
        double _t11 = _t9 / _t7;
        double _t15, _t16, _t17;
        if (_t6 > 0.0) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        return new DoubleQuat(((this.x) * (_t10) + (this.w * _t15)) + ((this.z) * (_t16) - (this.y * _t17)), ((this.x) * (_t17) + (this.w * _t16)) + ((this.y) * (_t10) - (this.z * _t15)), ((this.y) * (_t15) + (this.z * _t10)) + ((this.w) * (_t17) - (this.x * _t16)), ((this.w) * (_t10) - (this.x * _t15)) - ((this.y) * (_t16) + (this.z * _t17)));
    }

    /**
     * Obtain the direction of {@code -X} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting vector
     */
    public Double3 invNegativeX() {
        if (!Math.useFma()) return invNegativeX_mulAdd();
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
        double _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.x, this.x, this.w * this.w)));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            return new Double3(-(_t12 * _t16), -(_t10 * _t16), -(_t9 * _t16));
        } else {
            return Double3.ZERO;
        }
    }

    /** {@code invNegativeX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invNegativeX_mulAdd() {
        double _t9 = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
        double _t10 = 2.0 * ((this.x) * (this.y) - (this.z * this.w));
        double _t12 = ((((((this.x) * (this.x) + (this.w * this.w))) - (this.y) * (this.y))) - (this.z) * (this.z));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            return new Double3(-(_t12 * _t16), -(_t10 * _t16), -(_t9 * _t16));
        } else {
            return Double3.ZERO;
        }
    }

    /**
     * Obtain the direction of {@code -Y} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting vector
     */
    public Double3 invNegativeY() {
        if (!Math.useFma()) return invNegativeY_mulAdd();
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        double _t10 = 2.0 * java.lang.Math.fma(this.y, this.z, -this.x * this.w);
        double _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            return new Double3(-(_t9 * _t16), -(_t12 * _t16), -(_t10 * _t16));
        } else {
            return Double3.ZERO;
        }
    }

    /** {@code invNegativeY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invNegativeY_mulAdd() {
        double _t9 = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
        double _t10 = 2.0 * ((this.y) * (this.z) - (this.x * this.w));
        double _t12 = ((((this.y) * (this.y) + (((this.w) * (this.w) - (this.x * this.x))))) - (this.z) * (this.z));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            return new Double3(-(_t9 * _t16), -(_t12 * _t16), -(_t10 * _t16));
        } else {
            return Double3.ZERO;
        }
    }

    /**
     * Obtain the direction of {@code -Z} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting vector
     */
    public Double3 invNegativeZ() {
        if (!Math.useFma()) return invNegativeZ_mulAdd();
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
        double _t12 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            return new Double3(-(_t10 * _t16), -(_t9 * _t16), -(_t12 * _t16));
        } else {
            return Double3.ZERO;
        }
    }

    /** {@code invNegativeZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invNegativeZ_mulAdd() {
        double _t9 = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
        double _t10 = 2.0 * ((this.x) * (this.z) - (this.y * this.w));
        double _t12 = ((this.z) * (this.z) + (((((this.w) * (this.w) - (this.x * this.x))) - (this.y) * (this.y))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            return new Double3(-(_t10 * _t16), -(_t9 * _t16), -(_t12 * _t16));
        } else {
            return Double3.ZERO;
        }
    }

    /**
     * Obtain the direction of {@code -X} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting vector
     */
    public Double3 invNormalizedNegativeX() {
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(2.0, java.lang.Math.fma(this.y, this.y, this.z * this.z), -1.0), -2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w)), -2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w));
        } else {
            return new Double3(((2.0) * (((this.y) * (this.y) + (this.z * this.z))) - (1.0)), -2.0 * ((this.x) * (this.y) - (this.z * this.w)), -2.0 * ((this.x) * (this.z) + (this.y * this.w)));
        }
    }

    /**
     * Obtain the direction of {@code -Y} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting vector
     */
    public Double3 invNormalizedNegativeY() {
        if (Math.useFma()) {
            return new Double3(-2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w), java.lang.Math.fma(2.0, java.lang.Math.fma(this.x, this.x, this.z * this.z), -1.0), -2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w)));
        } else {
            return new Double3(-2.0 * ((this.x) * (this.y) + (this.z * this.w)), ((2.0) * (((this.x) * (this.x) + (this.z * this.z))) - (1.0)), -2.0 * ((this.y) * (this.z) - (this.x * this.w)));
        }
    }

    /**
     * Obtain the direction of {@code -Z} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting vector
     */
    public Double3 invNormalizedNegativeZ() {
        if (Math.useFma()) {
            return new Double3(-2.0 * java.lang.Math.fma(this.x, this.z, -(this.y * this.w)), -2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z), java.lang.Math.fma(2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), -1.0));
        } else {
            return new Double3(-2.0 * ((this.x) * (this.z) - (this.y * this.w)), -2.0 * ((this.x) * (this.w) + (this.y * this.z)), ((2.0) * (((this.x) * (this.x) + (this.y * this.y))) - (1.0)));
        }
    }

    /**
     * Obtain the direction of {@code +X} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting vector
     */
    public Double3 invNormalizedPositiveX() {
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, this.z * this.z), 1.0), 2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w)), 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w));
        } else {
            return new Double3(((1.0) - (2.0) * (((this.y) * (this.y) + (this.z * this.z)))), 2.0 * ((this.x) * (this.y) - (this.z * this.w)), 2.0 * ((this.x) * (this.z) + (this.y * this.w)));
        }
    }

    /**
     * Obtain the direction of {@code +Y} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting vector
     */
    public Double3 invNormalizedPositiveY() {
        if (Math.useFma()) {
            return new Double3(2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.z * this.z), 1.0), 2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w)));
        } else {
            return new Double3(2.0 * ((this.x) * (this.y) + (this.z * this.w)), ((1.0) - (2.0) * (((this.x) * (this.x) + (this.z * this.z)))), 2.0 * ((this.y) * (this.z) - (this.x * this.w)));
        }
    }

    /**
     * Obtain the direction of {@code +Z} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting vector
     */
    public Double3 invNormalizedPositiveZ() {
        if (Math.useFma()) {
            return new Double3(2.0 * java.lang.Math.fma(this.x, this.z, -(this.y * this.w)), 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0));
        } else {
            return new Double3(2.0 * ((this.x) * (this.z) - (this.y * this.w)), 2.0 * ((this.x) * (this.w) + (this.y * this.z)), ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y)))));
        }
    }

    /**
     * Obtain the direction of {@code +X} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting vector
     */
    public Double3 invPositiveX() {
        if (Math.useFma()) {
            double _t9 = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
            double _t10 = 2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
            double _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.x, this.x, this.w * this.w)));
            double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
            double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
            if (_t15 != 0.0) {
                return new Double3(_t12 * _t16, _t10 * _t16, _t9 * _t16);
            } else {
                return Double3.ZERO;
            }
        } else {
            double _t9 = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
            double _t10 = 2.0 * ((this.x) * (this.y) - (this.z * this.w));
            double _t12 = ((((((this.x) * (this.x) + (this.w * this.w))) - (this.y) * (this.y))) - (this.z) * (this.z));
            double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
            double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
            if (_t15 != 0.0) {
                return new Double3(_t12 * _t16, _t10 * _t16, _t9 * _t16);
            } else {
                return Double3.ZERO;
            }
        }
    }

    /**
     * Obtain the direction of {@code +Y} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting vector
     */
    public Double3 invPositiveY() {
        if (Math.useFma()) {
            double _t9 = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
            double _t10 = 2.0 * java.lang.Math.fma(this.y, this.z, -this.x * this.w);
            double _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
            double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
            double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
            if (_t15 != 0.0) {
                return new Double3(_t9 * _t16, _t12 * _t16, _t10 * _t16);
            } else {
                return Double3.ZERO;
            }
        } else {
            double _t9 = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
            double _t10 = 2.0 * ((this.y) * (this.z) - (this.x * this.w));
            double _t12 = ((((this.y) * (this.y) + (((this.w) * (this.w) - (this.x * this.x))))) - (this.z) * (this.z));
            double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
            double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
            if (_t15 != 0.0) {
                return new Double3(_t9 * _t16, _t12 * _t16, _t10 * _t16);
            } else {
                return Double3.ZERO;
            }
        }
    }

    /**
     * Obtain the direction of {@code +Z} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting vector
     */
    public Double3 invPositiveZ() {
        if (Math.useFma()) {
            double _t9 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
            double _t10 = 2.0 * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
            double _t12 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
            double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
            double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
            if (_t15 != 0.0) {
                return new Double3(_t10 * _t16, _t9 * _t16, _t12 * _t16);
            } else {
                return Double3.ZERO;
            }
        } else {
            double _t9 = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
            double _t10 = 2.0 * ((this.x) * (this.z) - (this.y * this.w));
            double _t12 = ((this.z) * (this.z) + (((((this.w) * (this.w) - (this.x * this.x))) - (this.y) * (this.y))));
            double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
            double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
            if (_t15 != 0.0) {
                return new Double3(_t10 * _t16, _t9 * _t16, _t12 * _t16);
            } else {
                return Double3.ZERO;
            }
        }
    }

    /**
     * Compute the length of this quaternion.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the length of this quaternion
     */
    public double length() {
        if (Math.useFma()) {
            return java.lang.Math.sqrt(java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y))));
        } else {
            return java.lang.Math.sqrt(((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y)))))));
        }
    }

    /**
     * Compute the squared length of this quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this quaternion
     */
    public double lengthSquared() {
        if (Math.useFma()) {
            return java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
        } else {
            return ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
        }
    }

    /**
     * Compute the natural logarithm of this quaternion, returning the result as a value.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code double}
     * resolution down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @return the resulting quaternion
     */
    public DoubleQuat log() {
        if (Math.useFma()) {
            double _t2 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            double _t6 = Math.atan2(java.lang.Math.sqrt(_t2), this.w) * (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0) {
                return new DoubleQuat(this.x * _t6, this.y * _t6, this.z * _t6, Math.log(java.lang.Math.sqrt(java.lang.Math.fma(this.w, this.w, _t2))));
            } else {
                return new DoubleQuat(0.0, 0.0, 0.0, Math.log(java.lang.Math.sqrt(java.lang.Math.fma(this.w, this.w, _t2))));
            }
        } else {
            double _t2 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            double _t6 = Math.atan2(java.lang.Math.sqrt(_t2), this.w) * (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0) {
                return new DoubleQuat(this.x * _t6, this.y * _t6, this.z * _t6, Math.log(java.lang.Math.sqrt(((this.w) * (this.w) + (_t2)))));
            } else {
                return new DoubleQuat(0.0, 0.0, 0.0, Math.log(java.lang.Math.sqrt(((this.w) * (this.w) + (_t2)))));
            }
        }
    }

    /**
     * Obtain the direction of {@code -X} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting vector
     */
    public Double3 negativeX() {
        if (!Math.useFma()) return negativeX_mulAdd();
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.z, -this.y * this.w);
        double _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.x, this.x, this.w * this.w)));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            return new Double3(-(_t12 * _t16), -(_t9 * _t16), -(_t10 * _t16));
        } else {
            return Double3.ZERO;
        }
    }

    /** {@code negativeX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 negativeX_mulAdd() {
        double _t9 = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
        double _t10 = 2.0 * ((this.x) * (this.z) - (this.y * this.w));
        double _t12 = ((((((this.x) * (this.x) + (this.w * this.w))) - (this.y) * (this.y))) - (this.z) * (this.z));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            return new Double3(-(_t12 * _t16), -(_t9 * _t16), -(_t10 * _t16));
        } else {
            return Double3.ZERO;
        }
    }

    /**
     * Obtain the direction of {@code -Y} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting vector
     */
    public Double3 negativeY() {
        if (!Math.useFma()) return negativeY_mulAdd();
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        double _t10 = 2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
        double _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            return new Double3(-(_t10 * _t16), -(_t12 * _t16), -(_t9 * _t16));
        } else {
            return Double3.ZERO;
        }
    }

    /** {@code negativeY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 negativeY_mulAdd() {
        double _t9 = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
        double _t10 = 2.0 * ((this.x) * (this.y) - (this.z * this.w));
        double _t12 = ((((this.y) * (this.y) + (((this.w) * (this.w) - (this.x * this.x))))) - (this.z) * (this.z));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            return new Double3(-(_t10 * _t16), -(_t12 * _t16), -(_t9 * _t16));
        } else {
            return Double3.ZERO;
        }
    }

    /**
     * Obtain the direction of {@code -Z} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting vector
     */
    public Double3 negativeZ() {
        if (!Math.useFma()) return negativeZ_mulAdd();
        double _t9 = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        double _t10 = 2.0 * java.lang.Math.fma(this.y, this.z, -this.x * this.w);
        double _t12 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            return new Double3(-(_t9 * _t16), -(_t10 * _t16), -(_t12 * _t16));
        } else {
            return Double3.ZERO;
        }
    }

    /** {@code negativeZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 negativeZ_mulAdd() {
        double _t9 = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
        double _t10 = 2.0 * ((this.y) * (this.z) - (this.x * this.w));
        double _t12 = ((this.z) * (this.z) + (((((this.w) * (this.w) - (this.x * this.x))) - (this.y) * (this.y))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            return new Double3(-(_t9 * _t16), -(_t10 * _t16), -(_t12 * _t16));
        } else {
            return Double3.ZERO;
        }
    }

    /**
     * Normalize this quaternion to unit length, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting quaternion
     */
    public DoubleQuat normalize() {
        if (Math.useFma()) {
            double _t3 = java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
            double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0) {
                return new DoubleQuat(this.x * _t4, this.y * _t4, this.z * _t4, this.w * _t4);
            } else {
                return DoubleQuat.ZERO;
            }
        } else {
            double _t3 = ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
            double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0) {
                return new DoubleQuat(this.x * _t4, this.y * _t4, this.z * _t4, this.w * _t4);
            } else {
                return DoubleQuat.ZERO;
            }
        }
    }

    /**
     * Obtain the direction of {@code -X} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting vector
     */
    public Double3 normalizedNegativeX() {
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(2.0, java.lang.Math.fma(this.y, this.y, this.z * this.z), -1.0), -2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w), -2.0 * java.lang.Math.fma(this.x, this.z, -(this.y * this.w)));
        } else {
            return new Double3(((2.0) * (((this.y) * (this.y) + (this.z * this.z))) - (1.0)), -2.0 * ((this.x) * (this.y) + (this.z * this.w)), -2.0 * ((this.x) * (this.z) - (this.y * this.w)));
        }
    }

    /**
     * Obtain the direction of {@code -Y} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting vector
     */
    public Double3 normalizedNegativeY() {
        if (Math.useFma()) {
            return new Double3(-2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w)), java.lang.Math.fma(2.0, java.lang.Math.fma(this.x, this.x, this.z * this.z), -1.0), -2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z));
        } else {
            return new Double3(-2.0 * ((this.x) * (this.y) - (this.z * this.w)), ((2.0) * (((this.x) * (this.x) + (this.z * this.z))) - (1.0)), -2.0 * ((this.x) * (this.w) + (this.y * this.z)));
        }
    }

    /**
     * Obtain the direction of {@code -Z} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting vector
     */
    public Double3 normalizedNegativeZ() {
        if (Math.useFma()) {
            return new Double3(-2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w), -2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w)), java.lang.Math.fma(2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), -1.0));
        } else {
            return new Double3(-2.0 * ((this.x) * (this.z) + (this.y * this.w)), -2.0 * ((this.y) * (this.z) - (this.x * this.w)), ((2.0) * (((this.x) * (this.x) + (this.y * this.y))) - (1.0)));
        }
    }

    /**
     * Obtain the direction of {@code +X} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting vector
     */
    public Double3 normalizedPositiveX() {
        if (Math.useFma()) {
            return new Double3(java.lang.Math.fma(-2.0, java.lang.Math.fma(this.y, this.y, this.z * this.z), 1.0), 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w), 2.0 * java.lang.Math.fma(this.x, this.z, -(this.y * this.w)));
        } else {
            return new Double3(((1.0) - (2.0) * (((this.y) * (this.y) + (this.z * this.z)))), 2.0 * ((this.x) * (this.y) + (this.z * this.w)), 2.0 * ((this.x) * (this.z) - (this.y * this.w)));
        }
    }

    /**
     * Obtain the direction of {@code +Y} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting vector
     */
    public Double3 normalizedPositiveY() {
        if (Math.useFma()) {
            return new Double3(2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.z * this.z), 1.0), 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z));
        } else {
            return new Double3(2.0 * ((this.x) * (this.y) - (this.z * this.w)), ((1.0) - (2.0) * (((this.x) * (this.x) + (this.z * this.z)))), 2.0 * ((this.x) * (this.w) + (this.y * this.z)));
        }
    }

    /**
     * Obtain the direction of {@code +Z} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting vector
     */
    public Double3 normalizedPositiveZ() {
        if (Math.useFma()) {
            return new Double3(2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w), 2.0 * java.lang.Math.fma(this.y, this.z, -(this.x * this.w)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0));
        } else {
            return new Double3(2.0 * ((this.x) * (this.z) + (this.y * this.w)), 2.0 * ((this.y) * (this.z) - (this.x * this.w)), ((1.0) - (2.0) * (((this.x) * (this.x) + (this.y * this.y)))));
        }
    }

    /**
     * Obtain the direction of {@code +X} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting vector
     */
    public Double3 positiveX() {
        if (Math.useFma()) {
            double _t9 = 2.0 * java.lang.Math.fma(this.x, this.y, this.z * this.w);
            double _t10 = 2.0 * java.lang.Math.fma(this.x, this.z, -this.y * this.w);
            double _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.x, this.x, this.w * this.w)));
            double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
            double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
            if (_t15 != 0.0) {
                return new Double3(_t12 * _t16, _t9 * _t16, _t10 * _t16);
            } else {
                return Double3.ZERO;
            }
        } else {
            double _t9 = 2.0 * ((this.x) * (this.y) + (this.z * this.w));
            double _t10 = 2.0 * ((this.x) * (this.z) - (this.y * this.w));
            double _t12 = ((((((this.x) * (this.x) + (this.w * this.w))) - (this.y) * (this.y))) - (this.z) * (this.z));
            double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
            double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
            if (_t15 != 0.0) {
                return new Double3(_t12 * _t16, _t9 * _t16, _t10 * _t16);
            } else {
                return Double3.ZERO;
            }
        }
    }

    /**
     * Obtain the direction of {@code +Y} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting vector
     */
    public Double3 positiveY() {
        if (Math.useFma()) {
            double _t9 = 2.0 * java.lang.Math.fma(this.x, this.w, this.y * this.z);
            double _t10 = 2.0 * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
            double _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
            double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
            double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
            if (_t15 != 0.0) {
                return new Double3(_t10 * _t16, _t12 * _t16, _t9 * _t16);
            } else {
                return Double3.ZERO;
            }
        } else {
            double _t9 = 2.0 * ((this.x) * (this.w) + (this.y * this.z));
            double _t10 = 2.0 * ((this.x) * (this.y) - (this.z * this.w));
            double _t12 = ((((this.y) * (this.y) + (((this.w) * (this.w) - (this.x * this.x))))) - (this.z) * (this.z));
            double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
            double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
            if (_t15 != 0.0) {
                return new Double3(_t10 * _t16, _t12 * _t16, _t9 * _t16);
            } else {
                return Double3.ZERO;
            }
        }
    }

    /**
     * Obtain the direction of {@code +Z} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting vector
     */
    public Double3 positiveZ() {
        if (Math.useFma()) {
            double _t9 = 2.0 * java.lang.Math.fma(this.x, this.z, this.y * this.w);
            double _t10 = 2.0 * java.lang.Math.fma(this.y, this.z, -this.x * this.w);
            double _t12 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
            double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
            double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
            if (_t15 != 0.0) {
                return new Double3(_t9 * _t16, _t10 * _t16, _t12 * _t16);
            } else {
                return Double3.ZERO;
            }
        } else {
            double _t9 = 2.0 * ((this.x) * (this.z) + (this.y * this.w));
            double _t10 = 2.0 * ((this.y) * (this.z) - (this.x * this.w));
            double _t12 = ((this.z) * (this.z) + (((((this.w) * (this.w) - (this.x * this.x))) - (this.y) * (this.y))));
            double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
            double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
            if (_t15 != 0.0) {
                return new Double3(_t9 * _t16, _t10 * _t16, _t12 * _t16);
            } else {
                return Double3.ZERO;
            }
        }
    }

    /**
     * Raise this quaternion to the power of {@code t}, i.e. compute {@code exp(t * log(this))},
     * returning the result as a value.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code double}
     * resolution down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the exponent
     * @return the resulting quaternion
     */
    public DoubleQuat pow(double t) {
        if (!Math.useFma()) return pow_mulAdd(t);
        double _t2 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
        double _t12 = Math.min(Math.exp(t == 0.0 ? 0.0 : t * Math.log(java.lang.Math.sqrt(java.lang.Math.fma(this.w, this.w, _t2)))), 1.7976931348623157E308);
        double _t13 = Math.atan2(java.lang.Math.sqrt(_t2), this.w) * (1.0 / java.lang.Math.sqrt(_t2));
        double _t20, _t21, _t22;
        if (_t2 > 0.0) {
            _t20 = t * this.z * _t13;
            _t21 = t * this.x * _t13;
            _t22 = t * this.y * _t13;
        } else {
            _t20 = t * 0.0;
            _t21 = t * 0.0;
            _t22 = t * 0.0;
        }
        double _t25 = java.lang.Math.fma(_t20, _t20, java.lang.Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = java.lang.Math.sqrt(_t25);
        double _t28 = Math.sin(_t26);
        double _t31 = _t12 * (_t28 / _t26);
        if (_t25 > 0.0) {
            return new DoubleQuat(_t21 * _t31, _t22 * _t31, _t20 * _t31, Math.cosFromSin(_t28, _t26) * _t12);
        } else {
            return new DoubleQuat(0.0, 0.0, 0.0, Math.cosFromSin(_t28, _t26) * _t12);
        }
    }

    /** {@code pow} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat pow_mulAdd(double t) {
        double _t2 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
        double _t12 = Math.min(Math.exp(t == 0.0 ? 0.0 : t * Math.log(java.lang.Math.sqrt(((this.w) * (this.w) + (_t2))))), 1.7976931348623157E308);
        double _t13 = Math.atan2(java.lang.Math.sqrt(_t2), this.w) * (1.0 / java.lang.Math.sqrt(_t2));
        double _t20, _t21, _t22;
        if (_t2 > 0.0) {
            _t20 = t * this.z * _t13;
            _t21 = t * this.x * _t13;
            _t22 = t * this.y * _t13;
        } else {
            _t20 = t * 0.0;
            _t21 = t * 0.0;
            _t22 = t * 0.0;
        }
        double _t25 = ((_t20) * (_t20) + (((_t21) * (_t21) + (_t22 * _t22))));
        double _t26 = java.lang.Math.sqrt(_t25);
        double _t28 = Math.sin(_t26);
        double _t31 = _t12 * (_t28 / _t26);
        if (_t25 > 0.0) {
            return new DoubleQuat(_t21 * _t31, _t22 * _t31, _t20 * _t31, Math.cosFromSin(_t28, _t26) * _t12);
        } else {
            return new DoubleQuat(0.0, 0.0, 0.0, Math.cosFromSin(_t28, _t26) * _t12);
        }
    }

    /**
     * Pre-multiply {@code other} onto this quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code R * Q}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code R * Q * v}, the transformation of the operand will be applied last.
     * <p>
     * Identical to {@link #preMul}; the lower-case spelling is kept for JOML 1 source
     * compatibility.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @return the resulting quaternion
     */
    public DoubleQuat premul(DoubleQuat other) {
        return preMul(other);
    }

    /**
     * Pre-multiply ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) onto this
     * quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code R * Q}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code R * Q * v}, the transformation of the operand will be applied last.
     * <p>
     * Identical to {@link #preMul}; the lower-case spelling is kept for JOML 1 source
     * compatibility.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return the resulting quaternion
     */
    public DoubleQuat premul(double otherX, double otherY, double otherZ, double otherW) {
        if (Math.useFma()) {
            return new DoubleQuat(java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherY, this.z, -(otherZ * this.y)), java.lang.Math.fma(otherY, this.w, otherZ * this.x) + java.lang.Math.fma(otherW, this.y, -otherX * this.z), java.lang.Math.fma(otherX, this.y, otherW * this.z) + java.lang.Math.fma(otherZ, this.w, -(otherY * this.x)), java.lang.Math.fma(otherW, this.w, -otherX * this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z));
        } else {
            return new DoubleQuat(((otherX) * (this.w) + (otherW * this.x)) + ((otherY) * (this.z) - (otherZ * this.y)), ((otherY) * (this.w) + (otherZ * this.x)) + ((otherW) * (this.y) - (otherX * this.z)), ((otherX) * (this.y) + (otherW * this.z)) + ((otherZ) * (this.w) - (otherY * this.x)), ((otherW) * (this.w) - (otherX * this.x)) - ((otherY) * (this.y) + (otherZ * this.z)));
        }
    }

    /**
     * Rotate this quaternion towards {@code target}, by at most the given maximum angle, returning
     * the result as a value.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code double}
     * resolution down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: this quaternion must have unit length; {@code target} must have unit length.
     *
     * @param target the target rotation
     * @param step the maximum rotation angle in radians
     * @return the resulting quaternion
     */
    public DoubleQuat rotateTowards(DoubleQuat target, double step) {
        double targetX = target.x;
        double targetY = target.y;
        double targetZ = target.z;
        double targetW = target.w;
        if (Math.useFma()) return rotateTowards_fma(targetX, targetY, targetZ, targetW, step);
        return rotateTowards_mulAdd(targetX, targetY, targetZ, targetW, step);
    }

    /**
     * Rotate this quaternion towards ({@code targetX}, {@code targetY}, {@code targetZ},
     * {@code targetW}), by at most the given maximum angle, returning the result as a value.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code double}
     * resolution down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: this quaternion must have unit length;
     * {@code (targetX, targetY, targetZ, targetW)} must have unit length.
     *
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param step the maximum rotation angle in radians
     * @return the resulting quaternion
     */
    public DoubleQuat rotateTowards(double targetX, double targetY, double targetZ, double targetW, double step) {
        if (Math.useFma()) return rotateTowards_fma(targetX, targetY, targetZ, targetW, step);
        return rotateTowards_mulAdd(targetX, targetY, targetZ, targetW, step);
    }

    /** {@code rotateTowards} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTowards_fma(double targetX, double targetY, double targetZ, double targetW, double step) {
        double _t7 = java.lang.Math.fma(this.w, targetW, java.lang.Math.fma(this.z, targetZ, java.lang.Math.fma(this.x, targetX, this.y * targetY)));
        double _t11 = Math.acos(Math.min(1.0, java.lang.Math.abs(_t7)));
        double _t12 = Math.sin(_t11);
        double _t13, _t14, _t15, _t16;
        if (-_t7 > 0.0) {
            _t13 = -targetW;
            _t14 = -targetZ;
            _t15 = -targetX;
            _t16 = -targetY;
        } else {
            _t13 = targetW;
            _t14 = targetZ;
            _t15 = targetX;
            _t16 = targetY;
        }
        double _t17 = this.w - _t13;
        double _t18 = this.z - _t14;
        double _t19 = this.x - _t15;
        double _t20 = this.y - _t16;
        double _t21 = this.w + _t13;
        double _t22 = this.z + _t14;
        double _t23 = this.x + _t15;
        double _t24 = this.y + _t16;
        double _t36 = 4.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))), java.lang.Math.sqrt(java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)))));
        double _t39 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        double _t40 = 1.0 - _t39;
        return rotateTowards_sbc9e42f3_1_fma(_t12, 1.0 / _t12, _t13, _t14, _t15, _t16, _t39, _t40, Math.sin(_t11 * _t39), Math.sin(_t40 * _t11));
    }

    /** {@code rotateTowards} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTowards_mulAdd(double targetX, double targetY, double targetZ, double targetW, double step) {
        double _t7 = ((this.w) * (targetW) + (((this.z) * (targetZ) + (((this.x) * (targetX) + (this.y * targetY))))));
        double _t11 = Math.acos(Math.min(1.0, java.lang.Math.abs(_t7)));
        double _t12 = Math.sin(_t11);
        double _t13, _t14, _t15, _t16;
        if (-_t7 > 0.0) {
            _t13 = -targetW;
            _t14 = -targetZ;
            _t15 = -targetX;
            _t16 = -targetY;
        } else {
            _t13 = targetW;
            _t14 = targetZ;
            _t15 = targetX;
            _t16 = targetY;
        }
        double _t17 = this.w - _t13;
        double _t18 = this.z - _t14;
        double _t19 = this.x - _t15;
        double _t20 = this.y - _t16;
        double _t21 = this.w + _t13;
        double _t22 = this.z + _t14;
        double _t23 = this.x + _t15;
        double _t24 = this.y + _t16;
        double _t36 = 4.0 * Math.atan2(java.lang.Math.sqrt(((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))))), java.lang.Math.sqrt(((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))))));
        double _t39 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        double _t40 = 1.0 - _t39;
        return rotateTowards_sbc9e42f3_1_mulAdd(_t12, 1.0 / _t12, _t13, _t14, _t15, _t16, _t39, _t40, Math.sin(_t11 * _t39), Math.sin(_t40 * _t11));
    }

    /** Piece 2 of {@code rotateTowards}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateTowards_sbc9e42f3_1_fma(double _t12, double _t12_inv, double _t13, double _t14, double _t15, double _t16, double _t39, double _t40, double _t42, double _t44) {
        double _t65, _t66, _t67, _t68;
        if (_t12 > 0.0) {
            _t65 = java.lang.Math.fma(this.w, _t44, _t42 * _t13) * _t12_inv;
            _t66 = java.lang.Math.fma(this.z, _t44, _t42 * _t14) * _t12_inv;
            _t67 = java.lang.Math.fma(this.x, _t44, _t42 * _t15) * _t12_inv;
            _t68 = java.lang.Math.fma(this.y, _t44, _t42 * _t16) * _t12_inv;
        } else {
            _t65 = java.lang.Math.fma(this.w, _t40, _t13 * _t39);
            _t66 = java.lang.Math.fma(this.z, _t40, _t14 * _t39);
            _t67 = java.lang.Math.fma(this.x, _t40, _t15 * _t39);
            _t68 = java.lang.Math.fma(this.y, _t40, _t16 * _t39);
        }
        double _t72 = java.lang.Math.fma(_t65, _t65, java.lang.Math.fma(_t66, _t66, java.lang.Math.fma(_t67, _t67, _t68 * _t68)));
        double _t73 = (1.0 / java.lang.Math.sqrt(_t72));
        double _sfx0, _sfx1, _sfx2, _sfx3;
        if (_t72 != 0.0) {
            _sfx0 = _t73 * _t67;
            _sfx1 = _t73 * _t68;
            _sfx2 = _t73 * _t66;
            _sfx3 = _t73 * _t65;
        } else {
            _sfx0 = 0.0;
            _sfx1 = 0.0;
            _sfx2 = 0.0;
            _sfx3 = 0.0;
        }
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /** Piece 2 of {@code rotateTowards}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateTowards_sbc9e42f3_1_mulAdd(double _t12, double _t12_inv, double _t13, double _t14, double _t15, double _t16, double _t39, double _t40, double _t42, double _t44) {
        double _t65, _t66, _t67, _t68;
        if (_t12 > 0.0) {
            _t65 = ((this.w) * (_t44) + (_t42 * _t13)) * _t12_inv;
            _t66 = ((this.z) * (_t44) + (_t42 * _t14)) * _t12_inv;
            _t67 = ((this.x) * (_t44) + (_t42 * _t15)) * _t12_inv;
            _t68 = ((this.y) * (_t44) + (_t42 * _t16)) * _t12_inv;
        } else {
            _t65 = ((this.w) * (_t40) + (_t13 * _t39));
            _t66 = ((this.z) * (_t40) + (_t14 * _t39));
            _t67 = ((this.x) * (_t40) + (_t15 * _t39));
            _t68 = ((this.y) * (_t40) + (_t16 * _t39));
        }
        double _t72 = ((_t65) * (_t65) + (((_t66) * (_t66) + (((_t67) * (_t67) + (_t68 * _t68))))));
        double _t73 = (1.0 / java.lang.Math.sqrt(_t72));
        double _sfx0, _sfx1, _sfx2, _sfx3;
        if (_t72 != 0.0) {
            _sfx0 = _t73 * _t67;
            _sfx1 = _t73 * _t68;
            _sfx2 = _t73 * _t66;
            _sfx3 = _t73 * _t65;
        } else {
            _sfx0 = 0.0;
            _sfx1 = 0.0;
            _sfx2 = 0.0;
            _sfx3 = 0.0;
        }
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Get the normalized rotation axis of this quaternion, unless the rotation angle is zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the rotation axis, or an empty {@code Optional} when the rotation angle is zero
     */
    public java.util.Optional<Double3> tryAxis() {
        if (Math.useFma()) {
            double _ct0 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            if (!(_ct0 > 0.0)) return java.util.Optional.empty();
            double _t4 = (1.0 / java.lang.Math.sqrt(_ct0));
            return java.util.Optional.of(new Double3(this.x * _t4, this.y * _t4, this.z * _t4));
        } else {
            double _ct0 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            if (!(_ct0 > 0.0)) return java.util.Optional.empty();
            double _t4 = (1.0 / java.lang.Math.sqrt(_ct0));
            return java.util.Optional.of(new Double3(this.x * _t4, this.y * _t4, this.z * _t4));
        }
    }

    /**
     * Recompute the {@code w} component of this quaternion from {@code x}, {@code y} and {@code z},
     * assuming unit length (the positive square root is chosen), unless no unit quaternion has
     * these {@code x}, {@code y} and {@code z}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this quaternion with the recomputed {@code w}, or an empty {@code Optional} when
     *        {@code x² + y² + z²} exceeds 1 by more than 8 units in the last place of 1 (no unit
     *        quaternion has these components); within that tolerance, {@code w} is 0
     */
    public java.util.Optional<DoubleQuat> tryCalculateW() {
        if (Math.useFma()) {
            double _ct0 = java.lang.Math.fma(-this.x, this.x, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(-this.z, this.z, 1.0)));
            if (!(_ct0 > -1.7763568394002505E-15 && _ct0 < Double.POSITIVE_INFINITY)) return java.util.Optional.empty();
            return java.util.Optional.of(new DoubleQuat(this.x, this.y, this.z, java.lang.Math.sqrt(Math.max(0.0, _ct0))));
        } else {
            double _ct0 = ((((((1.0) - (this.z) * (this.z))) - (this.y) * (this.y))) - (this.x) * (this.x));
            if (!(_ct0 > -1.7763568394002505E-15 && _ct0 < Double.POSITIVE_INFINITY)) return java.util.Optional.empty();
            return java.util.Optional.of(new DoubleQuat(this.x, this.y, this.z, java.lang.Math.sqrt(Math.max(0.0, _ct0))));
        }
    }

    /**
     * Recompute the {@code w} component of this quaternion from {@code x}, {@code y} and {@code z},
     * assuming unit length (the positive square root is chosen), unless no unit quaternion has
     * these {@code x}, {@code y} and {@code z}.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param epsilon how far {@code x² + y² + z²} may exceed 1 for {@code w} to be 0 rather than
     *        the result empty; the overload without it uses {@code 8 * 2^-52}
     * @return this quaternion with the recomputed {@code w}, or an empty {@code Optional} when
     *        {@code x² + y² + z²} exceeds 1 by more than {@code epsilon} (no unit quaternion has
     *        these components); within that tolerance, {@code w} is 0
     */
    public java.util.Optional<DoubleQuat> tryCalculateW(double epsilon) {
        if (Math.useFma()) {
            double _ct0 = java.lang.Math.fma(-this.x, this.x, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(-this.z, this.z, 1.0)));
            if (!(_ct0 > -epsilon && _ct0 < Double.POSITIVE_INFINITY)) return java.util.Optional.empty();
            return java.util.Optional.of(new DoubleQuat(this.x, this.y, this.z, java.lang.Math.sqrt(Math.max(0.0, _ct0))));
        } else {
            double _ct0 = ((((((1.0) - (this.z) * (this.z))) - (this.y) * (this.y))) - (this.x) * (this.x));
            if (!(_ct0 > -epsilon && _ct0 < Double.POSITIVE_INFINITY)) return java.util.Optional.empty();
            return java.util.Optional.of(new DoubleQuat(this.x, this.y, this.z, java.lang.Math.sqrt(Math.max(0.0, _ct0))));
        }
    }

    /**
     * Normalize this quaternion to unit length, unless it has zero length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the normalized quaternion, or an empty {@code Optional} when this quaternion has zero
     *        length
     */
    public java.util.Optional<DoubleQuat> tryNormalize() {
        if (Math.useFma()) {
            double _ct0 = java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
            if (!(_ct0 > 0.0)) return java.util.Optional.empty();
            double _t5 = (1.0 / java.lang.Math.sqrt(_ct0));
            return java.util.Optional.of(new DoubleQuat(this.x * _t5, this.y * _t5, this.z * _t5, this.w * _t5));
        } else {
            double _ct0 = ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
            if (!(_ct0 > 0.0)) return java.util.Optional.empty();
            double _t5 = (1.0 / java.lang.Math.sqrt(_ct0));
            return java.util.Optional.of(new DoubleQuat(this.x * _t5, this.y * _t5, this.z * _t5, this.w * _t5));
        }
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_fma(Double3 dir, Double3 up) {
        return lookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_mulAdd(Double3 dir, Double3 up) {
        return lookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private DoubleQuat lookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private DoubleQuat lookAlong_lh_sd9f2f6ce_1_fma(double dirX, double dirY, double dirZ, double _t15, double _t36, double _t37, double _t38, double _t45, double _t0, double _t16, double _t17, double _t18, double _t19, double _t21, double _t46, double _t47) {
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
    private DoubleQuat lookAlong_lh_sd9f2f6ce_1_mulAdd(double dirX, double dirY, double dirZ, double _t15, double _t36, double _t37, double _t38, double _t45, double _t0, double _t16, double _t17, double _t18, double _t19, double _t21, double _t46, double _t47) {
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
    private DoubleQuat lookAlong_lh_sd9f2f6ce_2_fma(double dirZ, double _t15, double _t36, double _t45, double _t16, double _t46, double _t50, double _t54, double _t62, double _t68, double _t70, double _t73, double _t74, double _t77, double _t79, double _t80, double _sp1, double _sp4, double _t83, double _sp3, double _sp2) {
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
        return lookAlong_lh_sd9f2f6ce_3_fma(_t125, _t126, _t127, _t128, java.lang.Math.fma(this.x, _t128, this.w * _t125) + java.lang.Math.fma(this.y, _t126, -(this.z * _t127)), java.lang.Math.fma(this.y, _t128, this.z * _t125) + java.lang.Math.fma(this.w, _t127, -this.x * _t126), java.lang.Math.fma(this.x, _t127, this.w * _t126) + java.lang.Math.fma(this.z, _t128, -(this.y * _t125)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sd9f2f6ce_2_mulAdd(double dirZ, double _t15, double _t36, double _t45, double _t16, double _t46, double _t50, double _t54, double _t62, double _t68, double _t70, double _t73, double _t74, double _t77, double _t79, double _t80, double _sp1, double _sp4, double _t83, double _sp3, double _sp2) {
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
        return lookAlong_lh_sd9f2f6ce_3_mulAdd(_t125, _t126, _t127, _t128, ((this.x) * (_t128) + (this.w * _t125)) + ((this.y) * (_t126) - (this.z * _t127)), ((this.y) * (_t128) + (this.z * _t125)) + ((this.w) * (_t127) - (this.x * _t126)), ((this.x) * (_t127) + (this.w * _t126)) + ((this.z) * (_t128) - (this.y * _t125)));
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sd9f2f6ce_3_fma(double _t125, double _t126, double _t127, double _t128, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3 = java.lang.Math.fma(this.w, _t128, -this.x * _t125) - java.lang.Math.fma(this.y, _t127, this.z * _t126);
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sd9f2f6ce_3_mulAdd(double _t125, double _t126, double _t127, double _t128, double _sfx0, double _sfx1, double _sfx2) {
        double _el3 = ((this.w) * (_t128) - (this.x * _t125)) - ((this.y) * (_t127) + (this.z * _t126));
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _el3);
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_lh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
        return lookAlong_lh_degenerate_sc9fd6f9f_1_fma(_t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_lh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
        return lookAlong_lh_degenerate_sc9fd6f9f_1_mulAdd(_t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_sc9fd6f9f_1_fma(double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
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
        return lookAlong_lh_degenerate_sc9fd6f9f_2_fma(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))), _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_sc9fd6f9f_1_mulAdd(double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
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
        return lookAlong_lh_degenerate_sc9fd6f9f_2_mulAdd(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))), _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_sc9fd6f9f_2_fma(double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97, double _t99, double _t100, double _sp3) {
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
        return lookAlong_lh_degenerate_sc9fd6f9f_3_fma(_t149, _t150, _t151, _t152);
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_sc9fd6f9f_2_mulAdd(double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97, double _t99, double _t100, double _sp3) {
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
        return lookAlong_lh_degenerate_sc9fd6f9f_3_mulAdd(_t149, _t150, _t151, _t152);
    }

    /**
     * Piece 4 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_sc9fd6f9f_3_fma(double _t149, double _t150, double _t151, double _t152) {
        double _sfx0 = java.lang.Math.fma(this.x, _t152, this.w * _t149) + java.lang.Math.fma(this.y, _t150, -(this.z * _t151));
        double _sfx1 = java.lang.Math.fma(this.y, _t152, this.z * _t149) + java.lang.Math.fma(this.w, _t151, -this.x * _t150);
        double _sfx2 = java.lang.Math.fma(this.x, _t151, this.w * _t150) + java.lang.Math.fma(this.z, _t152, -(this.y * _t149));
        double _sfx3 = java.lang.Math.fma(this.w, _t152, -this.x * _t149) - java.lang.Math.fma(this.y, _t151, this.z * _t150);
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Piece 4 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_sc9fd6f9f_3_mulAdd(double _t149, double _t150, double _t151, double _t152) {
        double _el0 = ((this.x) * (_t152) + (this.w * _t149)) + ((this.y) * (_t150) - (this.z * _t151));
        double _el1 = ((this.y) * (_t152) + (this.z * _t149)) + ((this.w) * (_t151) - (this.x * _t150));
        double _el2 = ((this.x) * (_t151) + (this.w * _t150)) + ((this.z) * (_t152) - (this.y * _t149));
        double _el3 = ((this.w) * (_t152) - (this.x * _t149)) - ((this.y) * (_t151) + (this.z * _t150));
        return new DoubleQuat(_el0, _el1, _el2, _el3);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_fma(Double3 dir, Double3 up) {
        return lookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_mulAdd(Double3 dir, Double3 up) {
        return lookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private DoubleQuat lookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private DoubleQuat lookAlong_rh_sbfc82748_1_fma(double dirX, double dirY, double _t16, double _t38, double _t39, double _t40, double _t47, double _t0, double _t17, double _t18, double _t19, double _t20, double _t22, double _t23, double _t41) {
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
    private DoubleQuat lookAlong_rh_sbfc82748_1_mulAdd(double dirX, double dirY, double _t16, double _t38, double _t39, double _t40, double _t47, double _t0, double _t17, double _t18, double _t19, double _t20, double _t22, double _t23, double _t41) {
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
    private DoubleQuat lookAlong_rh_sbfc82748_2_fma(double _t16, double _t38, double _t47, double _t0, double _t20, double _t49, double _t52, double _t56, double _t64, double _t70, double _t72, double _t74, double _t75, double _t78, double _t79, double _sp2, double _t81, double _sp4, double _t85, double _sp1, double _sp3) {
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
        return lookAlong_rh_sbfc82748_3_fma(_t126, _t127, _t128, _t129, java.lang.Math.fma(this.x, _t127, this.w * _t128) + java.lang.Math.fma(this.y, _t129, -(this.z * _t126)), java.lang.Math.fma(this.y, _t127, this.z * _t128) + java.lang.Math.fma(this.w, _t126, -this.x * _t129), java.lang.Math.fma(this.x, _t126, this.w * _t129) + java.lang.Math.fma(this.z, _t127, -(this.y * _t128)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_sbfc82748_2_mulAdd(double _t16, double _t38, double _t47, double _t0, double _t20, double _t49, double _t52, double _t56, double _t64, double _t70, double _t72, double _t74, double _t75, double _t78, double _t79, double _sp2, double _t81, double _sp4, double _t85, double _sp1, double _sp3) {
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
        return lookAlong_rh_sbfc82748_3_mulAdd(_t126, _t127, _t128, _t129, ((this.x) * (_t127) + (this.w * _t128)) + ((this.y) * (_t129) - (this.z * _t126)), ((this.y) * (_t127) + (this.z * _t128)) + ((this.w) * (_t126) - (this.x * _t129)), ((this.x) * (_t126) + (this.w * _t129)) + ((this.z) * (_t127) - (this.y * _t128)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_sbfc82748_3_fma(double _t126, double _t127, double _t128, double _t129, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3 = java.lang.Math.fma(this.w, _t127, -this.x * _t128) - java.lang.Math.fma(this.y, _t126, this.z * _t129);
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_sbfc82748_3_mulAdd(double _t126, double _t127, double _t128, double _t129, double _sfx0, double _sfx1, double _sfx2) {
        double _el3 = ((this.w) * (_t127) - (this.x * _t128)) - ((this.y) * (_t126) + (this.z * _t129));
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _el3);
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_rh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private DoubleQuat lookAlong_rh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private DoubleQuat lookAlong_rh_degenerate_s50196d65_1_fma(double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
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
    private DoubleQuat lookAlong_rh_degenerate_s50196d65_1_mulAdd(double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
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
    private DoubleQuat lookAlong_rh_degenerate_s50196d65_2_fma(double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t96, double _t97, double _t99) {
        double _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        double _t103 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t104 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_rh_degenerate_s50196d65_3_fma(_t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_degenerate_s50196d65_2_mulAdd(double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t96, double _t97, double _t99) {
        double _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        double _t103 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        double _t104 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_rh_degenerate_s50196d65_3_mulAdd(_t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5 * (1.0 / java.lang.Math.sqrt(_t99)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), 0.5 * (1.0 / java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_degenerate_s50196d65_3_fma(double _t24, double _t25, double _t30, double _t64, double _t67, double _t69, double _t70, double _t72, double _t74, double _t76, double _t88, double _t96, double _t97, double _t99, double _t100, double _sp1, double _t103, double _t104, double _sp0, double _sp3, double _sp2, double _t115, double _t116) {
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
        return lookAlong_rh_sbfc82748_3_fma(_t149, _t150, _t151, _t152, java.lang.Math.fma(this.x, _t150, this.w * _t151) + java.lang.Math.fma(this.y, _t152, -(this.z * _t149)), java.lang.Math.fma(this.y, _t150, this.z * _t151) + java.lang.Math.fma(this.w, _t149, -this.x * _t152), java.lang.Math.fma(this.x, _t149, this.w * _t152) + java.lang.Math.fma(this.z, _t150, -(this.y * _t151)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_degenerate_s50196d65_3_mulAdd(double _t24, double _t25, double _t30, double _t64, double _t67, double _t69, double _t70, double _t72, double _t74, double _t76, double _t88, double _t96, double _t97, double _t99, double _t100, double _sp1, double _t103, double _t104, double _sp0, double _sp3, double _sp2, double _t115, double _t116) {
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
        return lookAlong_rh_sbfc82748_3_mulAdd(_t149, _t150, _t151, _t152, ((this.x) * (_t150) + (this.w * _t151)) + ((this.y) * (_t152) - (this.z * _t149)), ((this.y) * (_t150) + (this.z * _t151)) + ((this.w) * (_t149) - (this.x * _t152)), ((this.x) * (_t149) + (this.w * _t152)) + ((this.z) * (_t150) - (this.y * _t151)));
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this quaternion, returning the result as a
     * value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param handedness the handedness of the coordinate system to map into
     * @return the resulting quaternion
     */
    public DoubleQuat lookAlong(Double3 dir, Double3 up, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (Math.useFma() ? lookAlong_lh_fma(dir, up) : lookAlong_lh_mulAdd(dir, up)); }
            default -> { return (Math.useFma() ? lookAlong_rh_fma(dir, up) : lookAlong_rh_mulAdd(dir, up)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this
     * quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     * @return the resulting quaternion
     */
    public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (Math.useFma() ? lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ) : lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
            default -> { return (Math.useFma() ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this quaternion, returning the result as a
     * value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @return the resulting quaternion
     */
    public DoubleQuat lookAlong(Double3 dir, Double3 up) { return (Math.useFma() ? lookAlong_rh_fma(dir, up) : lookAlong_rh_mulAdd(dir, up)); }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this
     * quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     * @return the resulting quaternion
     */
    public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) { return (Math.useFma() ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_fma(Double3 dir, Double3 up, double epsilon) {
        return lookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_mulAdd(Double3 dir, Double3 up, double epsilon) {
        return lookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
    private DoubleQuat lookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
    private DoubleQuat lookAlong_lh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
        return lookAlong_lh_degenerate_s18e2215c_1_fma(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_lh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
        return lookAlong_lh_degenerate_s18e2215c_1_mulAdd(epsilon, _t21, _t22, _t23, _t24, _t25, _t26, 1.0 + _t24, 1.0 - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_s18e2215c_1_fma(double epsilon, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
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
        return lookAlong_lh_degenerate_sc9fd6f9f_2_fma(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))), _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_s18e2215c_1_mulAdd(double epsilon, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
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
        return lookAlong_lh_degenerate_sc9fd6f9f_2_mulAdd(_t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))), _t100, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_fma(Double3 dir, Double3 up, double epsilon) {
        return lookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_mulAdd(Double3 dir, Double3 up, double epsilon) {
        return lookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
    private DoubleQuat lookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
    private DoubleQuat lookAlong_rh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
    private DoubleQuat lookAlong_rh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
    private DoubleQuat lookAlong_rh_degenerate_se8f60756_1_fma(double epsilon, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
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
    private DoubleQuat lookAlong_rh_degenerate_se8f60756_1_mulAdd(double epsilon, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
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
     * view transformation with the eye at the origin) to this quaternion, returning the result as a
     * value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     * @return the resulting quaternion
     */
    public DoubleQuat lookAlong(Double3 dir, Double3 up, double epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (Math.useFma() ? lookAlong_lh_fma(dir, up, epsilon) : lookAlong_lh_mulAdd(dir, up, epsilon)); }
            default -> { return (Math.useFma() ? lookAlong_rh_fma(dir, up, epsilon) : lookAlong_rh_mulAdd(dir, up, epsilon)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this
     * quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     * @return the resulting quaternion
     */
    public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return (Math.useFma() ? lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
            default -> { return (Math.useFma() ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }
        }
    }

    /**
     * Apply a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin) to this quaternion, returning the result as a
     * value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     * @return the resulting quaternion
     */
    public DoubleQuat lookAlong(Double3 dir, Double3 up, double epsilon) { return (Math.useFma() ? lookAlong_rh_fma(dir, up, epsilon) : lookAlong_rh_mulAdd(dir, up, epsilon)); }

    /**
     * Apply a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin) to this
     * quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     * @return the resulting quaternion
     */
    public DoubleQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) { return (Math.useFma() ? lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }

    /**
     * Create a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationAxis(double angle, Double3 axis) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleQuat(axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0));
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
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationAxis(double angle, double axisX, double axisY, double axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleQuat(axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleQuat makeRotationLookAlong_lh_fma(Double3 dir, Double3 up) {
        return makeRotationLookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleQuat makeRotationLookAlong_lh_mulAdd(Double3 dir, Double3 up) {
        return makeRotationLookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleQuat makeRotationLookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private static DoubleQuat makeRotationLookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private static DoubleQuat makeRotationLookAlong_lh_s5c9915aa_1_fma(double dirX, double dirY, double dirZ, double _t15, double _t36, double _t37, double _t38, double _t45, double _t0, double _t16, double _t17, double _t18, double _t19, double _t21, double _t46, double _t47, double _t48) {
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
    private static DoubleQuat makeRotationLookAlong_lh_s5c9915aa_1_mulAdd(double dirX, double dirY, double dirZ, double _t15, double _t36, double _t37, double _t38, double _t45, double _t0, double _t16, double _t17, double _t18, double _t19, double _t21, double _t46, double _t47, double _t48) {
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
    private static DoubleQuat makeRotationLookAlong_lh_s5c9915aa_2_fma(double dirZ, double _t15, double _t36, double _t45, double _t16, double _t46, double _t50, double _t55, double _t62, double _t68, double _t69, double _t73, double _t74, double _t77, double _sp1, double _t79, double _t80, double _t81, double _sp3, double _sp4, double _sp2) {
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
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static DoubleQuat makeRotationLookAlong_lh_s5c9915aa_2_mulAdd(double dirZ, double _t15, double _t36, double _t45, double _t16, double _t46, double _t50, double _t55, double _t62, double _t68, double _t69, double _t73, double _t74, double _t77, double _sp1, double _t79, double _t80, double _t81, double _sp3, double _sp4, double _sp2) {
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
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static DoubleQuat makeRotationLookAlong_lh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private static DoubleQuat makeRotationLookAlong_lh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private static DoubleQuat makeRotationLookAlong_lh_degenerate_s4577925b_1_fma(double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
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
    private static DoubleQuat makeRotationLookAlong_lh_degenerate_s4577925b_1_mulAdd(double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
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
    private static DoubleQuat makeRotationLookAlong_lh_degenerate_s4577925b_2_fma(double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97, double _t99, double _t100, double _sp0) {
        double _t102 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        double _t103 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        return makeRotationLookAlong_lh_degenerate_s4577925b_3_fma(_t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, _sp0, _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private static DoubleQuat makeRotationLookAlong_lh_degenerate_s4577925b_2_mulAdd(double _t24, double _t25, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t78, double _t89, double _t93, double _t96, double _t97, double _t99, double _t100, double _sp0) {
        double _t102 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        double _t103 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        return makeRotationLookAlong_lh_degenerate_s4577925b_3_mulAdd(_t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, _sp0, _t102, _t103, 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t102)), 0.5 * (1.0 / java.lang.Math.sqrt(_t103)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private static DoubleQuat makeRotationLookAlong_lh_degenerate_s4577925b_3_fma(double _t24, double _t25, double _t64, double _t67, double _t70, double _t71, double _t74, double _t78, double _t89, double _t96, double _t97, double _t99, double _t100, double _sp0, double _t102, double _t103, double _sp3, double _sp1, double _sp2, double _t107, double _t108) {
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
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private static DoubleQuat makeRotationLookAlong_lh_degenerate_s4577925b_3_mulAdd(double _t24, double _t25, double _t64, double _t67, double _t70, double _t71, double _t74, double _t78, double _t89, double _t96, double _t97, double _t99, double _t100, double _sp0, double _t102, double _t103, double _sp3, double _sp1, double _sp2, double _t107, double _t108) {
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
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleQuat makeRotationLookAlong_rh_fma(Double3 dir, Double3 up) {
        return makeRotationLookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleQuat makeRotationLookAlong_rh_mulAdd(Double3 dir, Double3 up) {
        return makeRotationLookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleQuat makeRotationLookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private static DoubleQuat makeRotationLookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private static DoubleQuat makeRotationLookAlong_rh_sf0b2d43c_1_fma(double dirX, double dirY, double _t16, double _t38, double _t39, double _t40, double _t47, double _t0, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t41, double _t48) {
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
    private static DoubleQuat makeRotationLookAlong_rh_sf0b2d43c_1_mulAdd(double dirX, double dirY, double _t16, double _t38, double _t39, double _t40, double _t47, double _t0, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t41, double _t48) {
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
    private static DoubleQuat makeRotationLookAlong_rh_sf0b2d43c_2_fma(double _t16, double _t38, double _t47, double _t0, double _t21, double _t49, double _t52, double _t56, double _t64, double _t70, double _t71, double _t74, double _t75, double _t78, double _t79, double _t80, double _sp4, double _sp1, double _sp2, double _t84, double _sp3) {
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
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static DoubleQuat makeRotationLookAlong_rh_sf0b2d43c_2_mulAdd(double _t16, double _t38, double _t47, double _t0, double _t21, double _t49, double _t52, double _t56, double _t64, double _t70, double _t71, double _t74, double _t75, double _t78, double _t79, double _t80, double _sp4, double _sp1, double _sp2, double _t84, double _sp3) {
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
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static DoubleQuat makeRotationLookAlong_rh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private static DoubleQuat makeRotationLookAlong_rh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private static DoubleQuat makeRotationLookAlong_rh_degenerate_sbd49e209_1_fma(double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
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
    private static DoubleQuat makeRotationLookAlong_rh_degenerate_sbd49e209_1_mulAdd(double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
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
    private static DoubleQuat makeRotationLookAlong_rh_degenerate_sbd49e209_2_fma(double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t96, double _t97, double _t99) {
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
    private static DoubleQuat makeRotationLookAlong_rh_degenerate_sbd49e209_2_mulAdd(double _t24, double _t25, double _t30, double _t32, double _t33, double _t64, double _t65, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t74, double _t76, double _t88, double _t93, double _t96, double _t97, double _t99) {
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
    private static DoubleQuat makeRotationLookAlong_rh_degenerate_sbd49e209_3_fma(double _t24, double _t25, double _t30, double _t64, double _t67, double _t69, double _t70, double _t72, double _t74, double _t76, double _t88, double _t96, double _t97, double _t99, double _t100, double _sp0, double _t102, double _t103, double _sp3, double _sp1, double _sp2, double _t107, double _t108) {
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
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private static DoubleQuat makeRotationLookAlong_rh_degenerate_sbd49e209_3_mulAdd(double _t24, double _t25, double _t30, double _t64, double _t67, double _t69, double _t70, double _t72, double _t74, double _t76, double _t88, double _t96, double _t97, double _t99, double _t100, double _sp0, double _t102, double _t103, double _sp3, double _sp1, double _sp2, double _t107, double _t108) {
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
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Create a "look along" view rotation looking along {@code dir} (the rotation of a "look at"
     * view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @param handedness the handedness of the coordinate system to map into
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationLookAlong(Double3 dir, Double3 up, Handedness handedness) {
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
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, Handedness handedness) {
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
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction mapped to {@code -z}
     *        (right-handed) or {@code +z} (left-handed)
     * @param up the direction of "up"
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationLookAlong(Double3 dir, Double3 up) { return (Math.useFma() ? makeRotationLookAlong_rh_fma(dir, up) : makeRotationLookAlong_rh_mulAdd(dir, up)); }

    /**
     * Create a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within an angle of about 32 units in the last place of 1) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) { return (Math.useFma() ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ)); }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleQuat makeRotationLookAlong_lh_fma(Double3 dir, Double3 up, double epsilon) {
        return makeRotationLookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleQuat makeRotationLookAlong_lh_mulAdd(Double3 dir, Double3 up, double epsilon) {
        return makeRotationLookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleQuat makeRotationLookAlong_lh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
    private static DoubleQuat makeRotationLookAlong_lh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
    private static DoubleQuat makeRotationLookAlong_lh_s4c0dc561_1_fma(double dirX, double dirY, double dirZ, double _t15, double _t36, double _t37, double _t38, double _t45, double _t0, double _t16, double _t17, double _t18, double _t19, double _t21, double _t46, double _t47) {
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
    private static DoubleQuat makeRotationLookAlong_lh_s4c0dc561_1_mulAdd(double dirX, double dirY, double dirZ, double _t15, double _t36, double _t37, double _t38, double _t45, double _t0, double _t16, double _t17, double _t18, double _t19, double _t21, double _t46, double _t47) {
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
    private static DoubleQuat makeRotationLookAlong_lh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
    private static DoubleQuat makeRotationLookAlong_lh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
    private static DoubleQuat makeRotationLookAlong_lh_degenerate_s76e68778_1_fma(double epsilon, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
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
    private static DoubleQuat makeRotationLookAlong_lh_degenerate_s76e68778_1_mulAdd(double epsilon, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t32, double _t33, double _t37, double _t38, double _t42, double _t56, double _t57, double _t58) {
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
    private static DoubleQuat makeRotationLookAlong_rh_fma(Double3 dir, Double3 up, double epsilon) {
        return makeRotationLookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleQuat makeRotationLookAlong_rh_mulAdd(Double3 dir, Double3 up, double epsilon) {
        return makeRotationLookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    private static DoubleQuat makeRotationLookAlong_rh_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
    private static DoubleQuat makeRotationLookAlong_rh_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
    private static DoubleQuat makeRotationLookAlong_rh_sacb5e2cf_1_fma(double dirX, double dirY, double _t16, double _t38, double _t39, double _t40, double _t47, double _t0, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t41) {
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
    private static DoubleQuat makeRotationLookAlong_rh_sacb5e2cf_1_mulAdd(double dirX, double dirY, double _t16, double _t38, double _t39, double _t40, double _t47, double _t0, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t41) {
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
    private static DoubleQuat makeRotationLookAlong_rh_degenerate_fma(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
    private static DoubleQuat makeRotationLookAlong_rh_degenerate_mulAdd(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
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
    private static DoubleQuat makeRotationLookAlong_rh_degenerate_sf59a41da_1_fma(double epsilon, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
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
    private static DoubleQuat makeRotationLookAlong_rh_degenerate_sf59a41da_1_mulAdd(double epsilon, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t29, double _t30, double _t32, double _t33, double _t37, double _t38, double _t42, double _t45, double _t46, double _t47, double _t56) {
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
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationLookAlong(Double3 dir, Double3 up, double epsilon, Handedness handedness) {
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
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon, Handedness handedness) {
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
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationLookAlong(Double3 dir, Double3 up, double epsilon) { return (Math.useFma() ? makeRotationLookAlong_rh_fma(dir, up, epsilon) : makeRotationLookAlong_rh_mulAdd(dir, up, epsilon)); }

    /**
     * Create a "look along" view rotation looking along ({@code dirX}, {@code dirY}, {@code dirZ})
     * (the rotation of a "look at" view transformation with the eye at the origin).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within the angle whose squared sine is {@code epsilon}) or zero is replaced by one
     * perpendicular to it, and a zero view direction gives the identity orientation; NaN input
     * gives NaN. The rotation is orthonormal to working precision for vectors of any finite length,
     * an up vector however close to the view direction included. (The raw-storage {@code *Ops}
     * kernels need a non-zero direction and up vector that are not parallel.)
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
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) { return (Math.useFma() ? makeRotationLookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon) : makeRotationLookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon)); }

    /**
     * Create the rotation that rotates {@code fromDir} onto {@code toDir} (for opposite vectors an
     * arbitrary perpendicular rotation axis is chosen).
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 1e-13}
     * (about 4.5e-7 radians from opposite); only there is the perpendicular axis chosen
     * arbitrarily, and the result is then off by at most that angle. The threshold also covers
     * directions normalized only to {@code float} precision.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length.
     *
     * @param fromDir the direction to rotate from (must be a unit vector)
     * @param toDir the direction to rotate onto (must be a unit vector)
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationTo(Double3 fromDir, Double3 toDir) {
        return makeRotationTo(fromDir.x(), fromDir.y(), fromDir.z(), toDir.x(), toDir.y(), toDir.z());
    }

    /** Private tail of {@code makeRotationTo}; reached only through it. */
    private static DoubleQuat makeRotationTo_s6ca4b61d_tail_fma(double _t29, double _t23, double _t16, double _t15, double _t17, double _t24, double _t13, double _t19, double _t18) {
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t32 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17)))));
        if (_t24 > 1.0E-13) {
            return new DoubleQuat(_t15 * _t32, _t17 * _t32, _t16 * _t32, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                return new DoubleQuat(_t30 * _t13, _t30 * _t19, _t30 * _t18, 0.0);
            } else {
                return DoubleQuat.ZERO;
            }
        }
    }

    /** Private tail of {@code makeRotationTo}; reached only through it. */
    private static DoubleQuat makeRotationTo_s6ca4b61d_tail_mulAdd(double _t29, double _t23, double _t16, double _t15, double _t17, double _t24, double _t13, double _t19, double _t18) {
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t32 = (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17))))))));
        if (_t24 > 1.0E-13) {
            return new DoubleQuat(_t15 * _t32, _t17 * _t32, _t16 * _t32, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                return new DoubleQuat(_t30 * _t13, _t30 * _t19, _t30 * _t18, 0.0);
            } else {
                return DoubleQuat.ZERO;
            }
        }
    }

    /**
     * Create the rotation that rotates ({@code fromDirX}, {@code fromDirY}, {@code fromDirZ}) onto
     * ({@code toDirX}, {@code toDirY}, {@code toDirZ}) (for opposite vectors an arbitrary
     * perpendicular rotation axis is chosen).
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 1e-13}
     * (about 4.5e-7 radians from opposite); only there is the perpendicular axis chosen
     * arbitrarily, and the result is then off by at most that angle. The threshold also covers
     * directions normalized only to {@code float} precision.
     * <p>
     * Valid input: {@code (fromDirX, fromDirY, fromDirZ)} must have unit length;
     * {@code (toDirX, toDirY, toDirZ)} must have unit length.
     *
     * @param fromDirX the {@code x} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirY the {@code y} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirZ the {@code z} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param toDirX the {@code x} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirY the {@code y} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirZ the {@code z} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationTo(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        if (Math.useFma()) {
            double _t4 = fromDirZ + toDirZ;
            double _t5 = fromDirX + toDirX;
            double _t6 = fromDirY + toDirY;
            double _t13, _t18, _t19;
            if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
                _t13 = fromDirY;
                _t18 = 0.0;
                _t19 = -fromDirX;
            } else {
                _t13 = 0.0;
                _t18 = -fromDirY;
                _t19 = fromDirZ;
            }
            double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
            return makeRotationTo_s6ca4b61d_tail_fma(java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19)), _t23, java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX), java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY)), java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ), 0.5 * _t23, _t13, _t19, _t18);
        } else {
            double _t4 = fromDirZ + toDirZ;
            double _t5 = fromDirX + toDirX;
            double _t6 = fromDirY + toDirY;
            double _t13, _t18, _t19;
            if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
                _t13 = fromDirY;
                _t18 = 0.0;
                _t19 = -fromDirX;
            } else {
                _t13 = 0.0;
                _t18 = -fromDirY;
                _t19 = fromDirZ;
            }
            double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
            return makeRotationTo_s6ca4b61d_tail_mulAdd(((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19)))), _t23, ((fromDirX) * (toDirY) - (fromDirY * toDirX)), ((fromDirY) * (toDirZ) - (fromDirZ * toDirY)), ((fromDirZ) * (toDirX) - (fromDirX * toDirZ)), 0.5 * _t23, _t13, _t19, _t18);
        }
    }

    /**
     * Create the rotation that rotates {@code fromDir} onto {@code toDir} (for opposite vectors an
     * arbitrary perpendicular rotation axis is chosen).
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than
     * {@code epsilon}; only there is the perpendicular axis chosen arbitrarily.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length;
     * {@code epsilon} must not be negative.
     *
     * @param fromDir the direction to rotate from (must be a unit vector)
     * @param toDir the direction to rotate onto (must be a unit vector)
     * @param epsilon the value of {@code 1 + dot(fromDir, toDir)} at or below which the directions
     *        count as opposite and an arbitrary perpendicular rotation axis is chosen; the overload
     *        without it uses {@code 1e-13}
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationTo(Double3 fromDir, Double3 toDir, double epsilon) {
        return makeRotationTo(fromDir.x(), fromDir.y(), fromDir.z(), toDir.x(), toDir.y(), toDir.z(), epsilon);
    }

    /** Private tail of {@code makeRotationTo}; reached only through it. */
    private static DoubleQuat makeRotationTo_s640110ac_tail_fma(double _t29, double _t23, double _t16, double _t15, double _t17, double _t24, double epsilon, double _t13, double _t19, double _t18) {
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t32 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17)))));
        if (_t24 > epsilon) {
            return new DoubleQuat(_t15 * _t32, _t17 * _t32, _t16 * _t32, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                return new DoubleQuat(_t30 * _t13, _t30 * _t19, _t30 * _t18, 0.0);
            } else {
                return DoubleQuat.ZERO;
            }
        }
    }

    /** Private tail of {@code makeRotationTo}; reached only through it. */
    private static DoubleQuat makeRotationTo_s640110ac_tail_mulAdd(double _t29, double _t23, double _t16, double _t15, double _t17, double _t24, double epsilon, double _t13, double _t19, double _t18) {
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t32 = (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17))))))));
        if (_t24 > epsilon) {
            return new DoubleQuat(_t15 * _t32, _t17 * _t32, _t16 * _t32, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                return new DoubleQuat(_t30 * _t13, _t30 * _t19, _t30 * _t18, 0.0);
            } else {
                return DoubleQuat.ZERO;
            }
        }
    }

    /**
     * Create the rotation that rotates ({@code fromDirX}, {@code fromDirY}, {@code fromDirZ}) onto
     * ({@code toDirX}, {@code toDirY}, {@code toDirZ}) (for opposite vectors an arbitrary
     * perpendicular rotation axis is chosen).
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than
     * {@code epsilon}; only there is the perpendicular axis chosen arbitrarily.
     * <p>
     * Valid input: {@code (fromDirX, fromDirY, fromDirZ)} must have unit length;
     * {@code (toDirX, toDirY, toDirZ)} must have unit length; {@code epsilon} must not be negative.
     *
     * @param fromDirX the {@code x} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirY the {@code y} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirZ the {@code z} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param toDirX the {@code x} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirY the {@code y} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirZ the {@code z} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param epsilon the value of {@code 1 + dot(fromDir, toDir)} at or below which the directions
     *        count as opposite and an arbitrary perpendicular rotation axis is chosen; the overload
     *        without it uses {@code 1e-13}
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationTo(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        if (Math.useFma()) {
            double _t4 = fromDirZ + toDirZ;
            double _t5 = fromDirX + toDirX;
            double _t6 = fromDirY + toDirY;
            double _t13, _t18, _t19;
            if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
                _t13 = fromDirY;
                _t18 = 0.0;
                _t19 = -fromDirX;
            } else {
                _t13 = 0.0;
                _t18 = -fromDirY;
                _t19 = fromDirZ;
            }
            double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
            return makeRotationTo_s640110ac_tail_fma(java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19)), _t23, java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX), java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY)), java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ), 0.5 * _t23, epsilon, _t13, _t19, _t18);
        } else {
            double _t4 = fromDirZ + toDirZ;
            double _t5 = fromDirX + toDirX;
            double _t6 = fromDirY + toDirY;
            double _t13, _t18, _t19;
            if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
                _t13 = fromDirY;
                _t18 = 0.0;
                _t19 = -fromDirX;
            } else {
                _t13 = 0.0;
                _t18 = -fromDirY;
                _t19 = fromDirZ;
            }
            double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
            return makeRotationTo_s640110ac_tail_mulAdd(((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19)))), _t23, ((fromDirX) * (toDirY) - (fromDirY * toDirX)), ((fromDirY) * (toDirZ) - (fromDirZ * toDirY)), ((fromDirZ) * (toDirX) - (fromDirX * toDirZ)), 0.5 * _t23, epsilon, _t13, _t19, _t18);
        }
    }

    /**
     * Create a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationX(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleQuat(_t1, 0.0, 0.0, Math.cosFromSin(_t1, _t0));
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
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationXYZ(double angleX, double angleY, double angleZ) {
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
        return new DoubleQuat(java.lang.Math.fma(_t10, _t7, _t11 * _t5), java.lang.Math.fma(_t11, _t7, _t10 * -_t5), java.lang.Math.fma(_t9, _t7, _t12 * _t5), java.lang.Math.fma(_t12, _t7, _t9 * -_t5));
    }

    /** {@code makeRotationXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleQuat makeRotationXYZ_mulAdd(double angleX, double angleY, double angleZ) {
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
        return new DoubleQuat(((_t10) * (_t7) + (_t11 * _t5)), ((_t11) * (_t7) - (_t10 * _t5)), ((_t9) * (_t7) + (_t12 * _t5)), ((_t12) * (_t7) - (_t9 * _t5)));
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
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationXZY(double angleX, double angleZ, double angleY) {
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
        return new DoubleQuat(java.lang.Math.fma(_t10, _t7, -(_t11 * _t5)), java.lang.Math.fma(_t12, _t5, -(_t9 * _t7)), java.lang.Math.fma(_t10, _t5, _t11 * _t7), java.lang.Math.fma(_t9, _t5, _t12 * _t7));
    }

    /** {@code makeRotationXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleQuat makeRotationXZY_mulAdd(double angleX, double angleZ, double angleY) {
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
        return new DoubleQuat(((_t10) * (_t7) - (_t11 * _t5)), ((_t12) * (_t5) - (_t9 * _t7)), ((_t10) * (_t5) + (_t11 * _t7)), ((_t9) * (_t5) + (_t12 * _t7)));
    }

    /**
     * Create a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationY(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleQuat(0.0, _t1, 0.0, Math.cosFromSin(_t1, _t0));
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
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationYXZ(double angleY, double angleX, double angleZ) {
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
        return new DoubleQuat(java.lang.Math.fma(_t10, _t7, _t11 * _t5), java.lang.Math.fma(_t11, _t7, -(_t10 * _t5)), java.lang.Math.fma(_t12, _t5, -(_t9 * _t7)), java.lang.Math.fma(_t9, _t5, _t12 * _t7));
    }

    /** {@code makeRotationYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleQuat makeRotationYXZ_mulAdd(double angleY, double angleX, double angleZ) {
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
        return new DoubleQuat(((_t10) * (_t7) + (_t11 * _t5)), ((_t11) * (_t7) - (_t10 * _t5)), ((_t12) * (_t5) - (_t9 * _t7)), ((_t9) * (_t5) + (_t12 * _t7)));
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
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationYZX(double angleY, double angleZ, double angleX) {
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
        return new DoubleQuat(java.lang.Math.fma(_t9, _t6, _t12 * _t5), java.lang.Math.fma(_t10, _t6, _t11 * _t5), java.lang.Math.fma(_t11, _t6, _t10 * -_t5), java.lang.Math.fma(_t12, _t6, _t9 * -_t5));
    }

    /** {@code makeRotationYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleQuat makeRotationYZX_mulAdd(double angleY, double angleZ, double angleX) {
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
        return new DoubleQuat(((_t9) * (_t6) + (_t12 * _t5)), ((_t10) * (_t6) + (_t11 * _t5)), ((_t11) * (_t6) - (_t10 * _t5)), ((_t12) * (_t6) - (_t9 * _t5)));
    }

    /**
     * Create a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationZ(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleQuat(0.0, 0.0, _t1, Math.cosFromSin(_t1, _t0));
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
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationZXY(double angleZ, double angleX, double angleY) {
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
        return new DoubleQuat(java.lang.Math.fma(_t10, _t7, _t11 * -_t5), java.lang.Math.fma(_t9, _t7, _t12 * _t5), java.lang.Math.fma(_t10, _t5, _t11 * _t7), java.lang.Math.fma(_t12, _t7, _t9 * -_t5));
    }

    /** {@code makeRotationZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleQuat makeRotationZXY_mulAdd(double angleZ, double angleX, double angleY) {
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
        return new DoubleQuat(((_t10) * (_t7) - (_t11 * _t5)), ((_t9) * (_t7) + (_t12 * _t5)), ((_t10) * (_t5) + (_t11 * _t7)), ((_t12) * (_t7) - (_t9 * _t5)));
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
     * @return the resulting quaternion
     */
    public static DoubleQuat makeRotationZYX(double angleZ, double angleY, double angleX) {
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
        return new DoubleQuat(java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), java.lang.Math.fma(_t10, _t8, _t11 * _t5), java.lang.Math.fma(_t11, _t8, -(_t10 * _t5)), java.lang.Math.fma(_t9, _t5, _t12 * _t8));
    }

    /** {@code makeRotationZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleQuat makeRotationZYX_mulAdd(double angleZ, double angleY, double angleX) {
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
        return new DoubleQuat(((_t12) * (_t5) - (_t9 * _t8)), ((_t10) * (_t8) + (_t11 * _t5)), ((_t11) * (_t8) - (_t10 * _t5)), ((_t9) * (_t5) + (_t12 * _t8)));
    }

    /**
     * Pre-multiply a rotation of {@code angle} radians about the X axis onto this quaternion,
     * returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting quaternion
     */
    public DoubleQuat preRotateX(double angle) {
        if (Math.useFma()) {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleQuat(java.lang.Math.fma(this.x, _t2, this.w * _t1), java.lang.Math.fma(this.y, _t2, -(this.z * _t1)), java.lang.Math.fma(this.y, _t1, this.z * _t2), java.lang.Math.fma(this.w, _t2, -(this.x * _t1)));
        } else {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleQuat(((this.x) * (_t2) + (this.w * _t1)), ((this.y) * (_t2) - (this.z * _t1)), ((this.y) * (_t1) + (this.z * _t2)), ((this.w) * (_t2) - (this.x * _t1)));
        }
    }

    /**
     * Pre-multiply a rotation of {@code angle} radians about the Y axis onto this quaternion,
     * returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting quaternion
     */
    public DoubleQuat preRotateY(double angle) {
        if (Math.useFma()) {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleQuat(java.lang.Math.fma(this.x, _t2, this.z * _t1), java.lang.Math.fma(this.y, _t2, this.w * _t1), java.lang.Math.fma(this.z, _t2, -(this.x * _t1)), java.lang.Math.fma(this.w, _t2, -(this.y * _t1)));
        } else {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleQuat(((this.x) * (_t2) + (this.z * _t1)), ((this.y) * (_t2) + (this.w * _t1)), ((this.z) * (_t2) - (this.x * _t1)), ((this.w) * (_t2) - (this.y * _t1)));
        }
    }

    /**
     * Pre-multiply a rotation of {@code angle} radians about the Z axis onto this quaternion,
     * returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting quaternion
     */
    public DoubleQuat preRotateZ(double angle) {
        if (Math.useFma()) {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleQuat(java.lang.Math.fma(this.x, _t2, -(this.y * _t1)), java.lang.Math.fma(this.x, _t1, this.y * _t2), java.lang.Math.fma(this.z, _t2, this.w * _t1), java.lang.Math.fma(this.w, _t2, -(this.z * _t1)));
        } else {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleQuat(((this.x) * (_t2) - (this.y * _t1)), ((this.x) * (_t1) + (this.y * _t2)), ((this.z) * (_t2) + (this.w * _t1)), ((this.w) * (_t2) - (this.z * _t1)));
        }
    }

    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this quaternion,
     * returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return the resulting quaternion
     */
    public DoubleQuat rotateAxis(double angle, Double3 axis) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z());
    }

    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) to this quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return the resulting quaternion
     */
    public DoubleQuat rotateAxis(double angle, double axisX, double axisY, double axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        if (!Math.useFma()) return rotateAxis_mulAdd(angle, axisX, axisY, axisZ);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        return new DoubleQuat(java.lang.Math.fma(this.x, _t5, this.w * _t2) + java.lang.Math.fma(this.y, _t3, -(this.z * _t4)), java.lang.Math.fma(this.y, _t5, this.z * _t2) + java.lang.Math.fma(this.w, _t4, -this.x * _t3), java.lang.Math.fma(this.x, _t4, this.w * _t3) + java.lang.Math.fma(this.z, _t5, -(this.y * _t2)), java.lang.Math.fma(this.w, _t5, -this.x * _t2) - java.lang.Math.fma(this.y, _t4, this.z * _t3));
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateAxis_mulAdd(double angle, double axisX, double axisY, double axisZ) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        return new DoubleQuat(((this.x) * (_t5) + (this.w * _t2)) + ((this.y) * (_t3) - (this.z * _t4)), ((this.y) * (_t5) + (this.z * _t2)) + ((this.w) * (_t4) - (this.x * _t3)), ((this.x) * (_t4) + (this.w * _t3)) + ((this.z) * (_t5) - (this.y * _t2)), ((this.w) * (_t5) - (this.x * _t2)) - ((this.y) * (_t4) + (this.z * _t3)));
    }

    /**
     * Apply the rotation that rotates {@code fromDir} onto {@code toDir} (for opposite vectors an
     * arbitrary perpendicular rotation axis is chosen) to this quaternion, returning the result as
     * a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 1e-13}
     * (about 4.5e-7 radians from opposite); only there is the perpendicular axis chosen
     * arbitrarily, and the result is then off by at most that angle. The threshold also covers
     * directions normalized only to {@code float} precision.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length.
     *
     * @param fromDir the direction to rotate from (must be a unit vector)
     * @param toDir the direction to rotate onto (must be a unit vector)
     * @return the resulting quaternion
     */
    public DoubleQuat rotateTo(Double3 fromDir, Double3 toDir) {
        double fromDirX = fromDir.x();
        double fromDirY = fromDir.y();
        double fromDirZ = fromDir.z();
        double toDirX = toDir.x();
        double toDirY = toDir.y();
        double toDirZ = toDir.z();
        if (!Math.useFma()) return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        double _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_s6ca4b61d_tail_fma(0.5 * _t23, (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))), _t16, _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t15, _t18, _t17, _t19);
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private DoubleQuat rotateTo_s6ca4b61d_tail_fma(double _t24, double _t35, double _t16, double _t29, double _t30, double _t13, double _t15, double _t18, double _t17, double _t19) {
        double _t44, _t45, _t46, _t47;
        if (_t24 > 1.0E-13) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        return new DoubleQuat(java.lang.Math.fma(this.x, _t44, this.w * _t45) + java.lang.Math.fma(this.y, _t46, -(this.z * _t47)), java.lang.Math.fma(this.y, _t44, this.z * _t45) + java.lang.Math.fma(this.w, _t47, -this.x * _t46), java.lang.Math.fma(this.x, _t47, this.w * _t46) + java.lang.Math.fma(this.z, _t44, -(this.y * _t45)), java.lang.Math.fma(this.w, _t44, -this.x * _t45) - java.lang.Math.fma(this.y, _t47, this.z * _t46));
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private DoubleQuat rotateTo_s6ca4b61d_tail_mulAdd(double _t24, double _t35, double _t16, double _t29, double _t30, double _t13, double _t15, double _t18, double _t17, double _t19) {
        double _t44, _t45, _t46, _t47;
        if (_t24 > 1.0E-13) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        return new DoubleQuat(((this.x) * (_t44) + (this.w * _t45)) + ((this.y) * (_t46) - (this.z * _t47)), ((this.y) * (_t44) + (this.z * _t45)) + ((this.w) * (_t47) - (this.x * _t46)), ((this.x) * (_t47) + (this.w * _t46)) + ((this.z) * (_t44) - (this.y * _t45)), ((this.w) * (_t44) - (this.x * _t45)) - ((this.y) * (_t47) + (this.z * _t46)));
    }

    /**
     * Apply the rotation that rotates ({@code fromDirX}, {@code fromDirY}, {@code fromDirZ}) onto
     * ({@code toDirX}, {@code toDirY}, {@code toDirZ}) (for opposite vectors an arbitrary
     * perpendicular rotation axis is chosen) to this quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 1e-13}
     * (about 4.5e-7 radians from opposite); only there is the perpendicular axis chosen
     * arbitrarily, and the result is then off by at most that angle. The threshold also covers
     * directions normalized only to {@code float} precision.
     * <p>
     * Valid input: {@code (fromDirX, fromDirY, fromDirZ)} must have unit length;
     * {@code (toDirX, toDirY, toDirZ)} must have unit length.
     *
     * @param fromDirX the {@code x} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirY the {@code y} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirZ the {@code z} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param toDirX the {@code x} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirY the {@code y} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirZ the {@code z} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @return the resulting quaternion
     */
    public DoubleQuat rotateTo(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        if (!Math.useFma()) return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        double _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_s6ca4b61d_tail_fma(0.5 * _t23, (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))), _t16, _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t15, _t18, _t17, _t19);
    }

    /** {@code rotateTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTo_mulAdd(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        double _t16 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        double _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_s6ca4b61d_tail_mulAdd(0.5 * _t23, (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17)))))))), _t16, _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t15, _t18, _t17, _t19);
    }

    /**
     * Apply the rotation that rotates {@code fromDir} onto {@code toDir} (for opposite vectors an
     * arbitrary perpendicular rotation axis is chosen) to this quaternion, returning the result as
     * a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than
     * {@code epsilon}; only there is the perpendicular axis chosen arbitrarily.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length;
     * {@code epsilon} must not be negative.
     *
     * @param fromDir the direction to rotate from (must be a unit vector)
     * @param toDir the direction to rotate onto (must be a unit vector)
     * @param epsilon the value of {@code 1 + dot(fromDir, toDir)} at or below which the directions
     *        count as opposite and an arbitrary perpendicular rotation axis is chosen; the overload
     *        without it uses {@code 1e-13}
     * @return the resulting quaternion
     */
    public DoubleQuat rotateTo(Double3 fromDir, Double3 toDir, double epsilon) {
        double fromDirX = fromDir.x();
        double fromDirY = fromDir.y();
        double fromDirZ = fromDir.z();
        double toDirX = toDir.x();
        double toDirY = toDir.y();
        double toDirZ = toDir.z();
        if (!Math.useFma()) return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        double _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_s640110ac_tail_fma(0.5 * _t23, epsilon, (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))), _t16, _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t15, _t18, _t17, _t19);
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private DoubleQuat rotateTo_s640110ac_tail_fma(double _t24, double epsilon, double _t35, double _t16, double _t29, double _t30, double _t13, double _t15, double _t18, double _t17, double _t19) {
        double _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        return new DoubleQuat(java.lang.Math.fma(this.x, _t44, this.w * _t45) + java.lang.Math.fma(this.y, _t46, -(this.z * _t47)), java.lang.Math.fma(this.y, _t44, this.z * _t45) + java.lang.Math.fma(this.w, _t47, -this.x * _t46), java.lang.Math.fma(this.x, _t47, this.w * _t46) + java.lang.Math.fma(this.z, _t44, -(this.y * _t45)), java.lang.Math.fma(this.w, _t44, -this.x * _t45) - java.lang.Math.fma(this.y, _t47, this.z * _t46));
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private DoubleQuat rotateTo_s640110ac_tail_mulAdd(double _t24, double epsilon, double _t35, double _t16, double _t29, double _t30, double _t13, double _t15, double _t18, double _t17, double _t19) {
        double _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        return new DoubleQuat(((this.x) * (_t44) + (this.w * _t45)) + ((this.y) * (_t46) - (this.z * _t47)), ((this.y) * (_t44) + (this.z * _t45)) + ((this.w) * (_t47) - (this.x * _t46)), ((this.x) * (_t47) + (this.w * _t46)) + ((this.z) * (_t44) - (this.y * _t45)), ((this.w) * (_t44) - (this.x * _t45)) - ((this.y) * (_t47) + (this.z * _t46)));
    }

    /**
     * Apply the rotation that rotates ({@code fromDirX}, {@code fromDirY}, {@code fromDirZ}) onto
     * ({@code toDirX}, {@code toDirY}, {@code toDirZ}) (for opposite vectors an arbitrary
     * perpendicular rotation axis is chosen) to this quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than
     * {@code epsilon}; only there is the perpendicular axis chosen arbitrarily.
     * <p>
     * Valid input: {@code (fromDirX, fromDirY, fromDirZ)} must have unit length;
     * {@code (toDirX, toDirY, toDirZ)} must have unit length; {@code epsilon} must not be negative.
     *
     * @param fromDirX the {@code x} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirY the {@code y} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirZ the {@code z} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param toDirX the {@code x} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirY the {@code y} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirZ the {@code z} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param epsilon the value of {@code 1 + dot(fromDir, toDir)} at or below which the directions
     *        count as opposite and an arbitrary perpendicular rotation axis is chosen; the overload
     *        without it uses {@code 1e-13}
     * @return the resulting quaternion
     */
    public DoubleQuat rotateTo(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        if (!Math.useFma()) return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        double _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_s640110ac_tail_fma(0.5 * _t23, epsilon, (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))), _t16, _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t15, _t18, _t17, _t19);
    }

    /** {@code rotateTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTo_mulAdd(double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        double _t16 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        double _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_s640110ac_tail_mulAdd(0.5 * _t23, epsilon, (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17)))))))), _t16, _t29, (1.0 / java.lang.Math.sqrt(_t29)), _t13, _t15, _t18, _t17, _t19);
    }

    /**
     * Rotate this quaternion by {@code angle} radians about the local X axis, returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting quaternion
     */
    public DoubleQuat rotateX(double angle) {
        if (Math.useFma()) {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleQuat(java.lang.Math.fma(this.x, _t2, this.w * _t1), java.lang.Math.fma(this.y, _t2, this.z * _t1), java.lang.Math.fma(this.z, _t2, -(this.y * _t1)), java.lang.Math.fma(this.w, _t2, -(this.x * _t1)));
        } else {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleQuat(((this.x) * (_t2) + (this.w * _t1)), ((this.y) * (_t2) + (this.z * _t1)), ((this.z) * (_t2) - (this.y * _t1)), ((this.w) * (_t2) - (this.x * _t1)));
        }
    }

    /** Private tail of {@code rotateXYZ}; reached only through it. */
    private DoubleQuat rotateXYZ_s361a4ff5_tail_fma(double _t11, double _t8, double _t10, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = java.lang.Math.fma(_t11, _t8, _t10 * -_t5);
        return new DoubleQuat(java.lang.Math.fma(this.x, _t21, this.w * _t19) + java.lang.Math.fma(this.y, _t20, -(this.z * _t22)), java.lang.Math.fma(this.y, _t21, this.z * _t19) + java.lang.Math.fma(this.w, _t22, -this.x * _t20), java.lang.Math.fma(this.x, _t22, this.w * _t20) + java.lang.Math.fma(this.z, _t21, -(this.y * _t19)), java.lang.Math.fma(this.w, _t21, -this.x * _t19) - java.lang.Math.fma(this.y, _t22, this.z * _t20));
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of {@code rotateXYZ}
     * and {@code rotateXZY}; reached only through them.
     */
    private DoubleQuat rotateXYZ_s361a4ff5_tail_mulAdd(double _t11, double _t8, double _t10, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        return new DoubleQuat(((this.x) * (_t21) + (this.w * _t19)) + ((this.y) * (_t20) - (this.z * _t22)), ((this.y) * (_t21) + (this.z * _t19)) + ((this.w) * (_t22) - (this.x * _t20)), ((this.x) * (_t22) + (this.w * _t20)) + ((this.z) * (_t21) - (this.y * _t19)), ((this.w) * (_t21) - (this.x * _t19)) - ((this.y) * (_t22) + (this.z * _t20)));
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X), to this quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return the resulting quaternion
     */
    public DoubleQuat rotateXYZ(double angleX, double angleY, double angleZ) {
        if (Math.useFma()) {
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
            return rotateXYZ_s361a4ff5_tail_fma(_t11, _t8, _t10, _t5, java.lang.Math.fma(_t14, _t8, _t9 * -_t5), java.lang.Math.fma(_t10, _t8, _t11 * _t5), java.lang.Math.fma(_t9, _t8, _t14 * _t5));
        } else {
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
            return rotateXYZ_s361a4ff5_tail_mulAdd(_t11, _t8, _t10, _t5, ((_t14) * (_t8) - (_t9 * _t5)), ((_t10) * (_t8) + (_t11 * _t5)), ((_t9) * (_t8) + (_t14 * _t5)));
        }
    }

    /**
     * Private tail of {@code rotateXZY}. Shared by the identical private paths of {@code rotateXZY}
     * and {@code rotateYXZ}; reached only through them.
     */
    private DoubleQuat rotateXZY_s17ac2dd3_tail_fma(double _t12, double _t5, double _t9, double _t8, double _t19, double _t21, double _t20) {
        double _t22 = java.lang.Math.fma(_t12, _t5, -(_t9 * _t8));
        return new DoubleQuat(java.lang.Math.fma(this.x, _t19, this.w * _t21) + java.lang.Math.fma(this.y, _t20, -(this.z * _t22)), java.lang.Math.fma(this.y, _t19, this.z * _t21) + java.lang.Math.fma(this.w, _t22, -this.x * _t20), java.lang.Math.fma(this.x, _t22, this.w * _t20) + java.lang.Math.fma(this.z, _t19, -(this.y * _t21)), java.lang.Math.fma(this.w, _t19, -this.x * _t21) - java.lang.Math.fma(this.y, _t22, this.z * _t20));
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X), to this quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return the resulting quaternion
     */
    public DoubleQuat rotateXZY(double angleX, double angleZ, double angleY) {
        if (Math.useFma()) {
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
            return rotateXZY_s17ac2dd3_tail_fma(_t12, _t5, _t9, _t8, java.lang.Math.fma(_t9, _t5, _t12 * _t8), java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), java.lang.Math.fma(_t10, _t5, _t11 * _t8));
        } else {
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
            return rotateXYZ_s361a4ff5_tail_mulAdd(_t12, _t5, _t9, _t8, ((_t9) * (_t5) + (_t12 * _t8)), ((_t10) * (_t8) - (_t11 * _t5)), ((_t10) * (_t5) + (_t11 * _t8)));
        }
    }

    /**
     * Rotate this quaternion by {@code angle} radians about the local Y axis, returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting quaternion
     */
    public DoubleQuat rotateY(double angle) {
        if (Math.useFma()) {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleQuat(java.lang.Math.fma(this.x, _t2, -(this.z * _t1)), java.lang.Math.fma(this.y, _t2, this.w * _t1), java.lang.Math.fma(this.x, _t1, this.z * _t2), java.lang.Math.fma(this.w, _t2, -(this.y * _t1)));
        } else {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleQuat(((this.x) * (_t2) - (this.z * _t1)), ((this.y) * (_t2) + (this.w * _t1)), ((this.x) * (_t1) + (this.z * _t2)), ((this.w) * (_t2) - (this.y * _t1)));
        }
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y), to this quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return the resulting quaternion
     */
    public DoubleQuat rotateYXZ(double angleY, double angleX, double angleZ) {
        if (Math.useFma()) {
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
            return rotateXZY_s17ac2dd3_tail_fma(_t11, _t8, _t10, _t5, java.lang.Math.fma(_t9, _t5, _t12 * _t8), java.lang.Math.fma(_t10, _t8, _t11 * _t5), java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)));
        } else {
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
            return rotateXYZ_s361a4ff5_tail_mulAdd(_t11, _t8, _t10, _t5, ((_t9) * (_t5) + (_t12 * _t8)), ((_t10) * (_t8) + (_t11 * _t5)), ((_t12) * (_t5) - (_t9 * _t8)));
        }
    }

    /** Private tail of {@code rotateYZX}; reached only through it. */
    private DoubleQuat rotateYZX_s2c94f613_tail_fma(double _t10, double _t8, double _t11, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = java.lang.Math.fma(_t10, _t8, _t11 * -_t5);
        return new DoubleQuat(java.lang.Math.fma(this.x, _t21, this.w * _t19) + java.lang.Math.fma(this.y, _t22, -(this.z * _t20)), java.lang.Math.fma(this.y, _t21, this.z * _t19) + java.lang.Math.fma(this.w, _t20, -this.x * _t22), java.lang.Math.fma(this.x, _t20, this.w * _t22) + java.lang.Math.fma(this.z, _t21, -(this.y * _t19)), java.lang.Math.fma(this.w, _t21, -this.x * _t19) - java.lang.Math.fma(this.y, _t20, this.z * _t22));
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private DoubleQuat rotateYZX_s2c94f613_tail_mulAdd(double _t10, double _t8, double _t11, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        return new DoubleQuat(((this.x) * (_t21) + (this.w * _t19)) + ((this.y) * (_t22) - (this.z * _t20)), ((this.y) * (_t21) + (this.z * _t19)) + ((this.w) * (_t20) - (this.x * _t22)), ((this.x) * (_t20) + (this.w * _t22)) + ((this.z) * (_t21) - (this.y * _t19)), ((this.w) * (_t21) - (this.x * _t19)) - ((this.y) * (_t20) + (this.z * _t22)));
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y), to this quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return the resulting quaternion
     */
    public DoubleQuat rotateYZX(double angleY, double angleZ, double angleX) {
        if (Math.useFma()) {
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
            return rotateYZX_s2c94f613_tail_fma(_t10, _t8, _t11, _t5, java.lang.Math.fma(_t14, _t8, _t9 * -_t5), java.lang.Math.fma(_t9, _t8, _t14 * _t5), java.lang.Math.fma(_t11, _t8, _t10 * _t5));
        } else {
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
            return rotateYZX_s2c94f613_tail_mulAdd(_t10, _t8, _t11, _t5, ((_t14) * (_t8) - (_t9 * _t5)), ((_t9) * (_t8) + (_t14 * _t5)), ((_t11) * (_t8) + (_t10 * _t5)));
        }
    }

    /**
     * Rotate this quaternion by {@code angle} radians about the local Z axis, returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting quaternion
     */
    public DoubleQuat rotateZ(double angle) {
        if (Math.useFma()) {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleQuat(java.lang.Math.fma(this.x, _t2, this.y * _t1), java.lang.Math.fma(this.y, _t2, -(this.x * _t1)), java.lang.Math.fma(this.z, _t2, this.w * _t1), java.lang.Math.fma(this.w, _t2, -(this.z * _t1)));
        } else {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleQuat(((this.x) * (_t2) + (this.y * _t1)), ((this.y) * (_t2) - (this.x * _t1)), ((this.z) * (_t2) + (this.w * _t1)), ((this.w) * (_t2) - (this.z * _t1)));
        }
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private DoubleQuat rotateZXY_s7e5a0297_tail_fma(double _t10, double _t8, double _t11, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = java.lang.Math.fma(_t10, _t8, _t11 * -_t5);
        return new DoubleQuat(java.lang.Math.fma(this.x, _t21, this.w * _t22) + java.lang.Math.fma(this.y, _t19, -(this.z * _t20)), java.lang.Math.fma(this.y, _t21, this.z * _t22) + java.lang.Math.fma(this.w, _t20, -this.x * _t19), java.lang.Math.fma(this.x, _t20, this.w * _t19) + java.lang.Math.fma(this.z, _t21, -(this.y * _t22)), java.lang.Math.fma(this.w, _t21, -this.x * _t22) - java.lang.Math.fma(this.y, _t20, this.z * _t19));
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private DoubleQuat rotateZXY_s7e5a0297_tail_mulAdd(double _t10, double _t8, double _t11, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        return new DoubleQuat(((this.x) * (_t21) + (this.w * _t22)) + ((this.y) * (_t19) - (this.z * _t20)), ((this.y) * (_t21) + (this.z * _t22)) + ((this.w) * (_t20) - (this.x * _t19)), ((this.x) * (_t20) + (this.w * _t19)) + ((this.z) * (_t21) - (this.y * _t22)), ((this.w) * (_t21) - (this.x * _t22)) - ((this.y) * (_t20) + (this.z * _t19)));
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z), to this quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return the resulting quaternion
     */
    public DoubleQuat rotateZXY(double angleZ, double angleX, double angleY) {
        if (Math.useFma()) {
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
            return rotateZXY_s7e5a0297_tail_fma(_t10, _t8, _t11, _t5, java.lang.Math.fma(_t14, _t8, _t9 * -_t5), java.lang.Math.fma(_t10, _t5, _t11 * _t8), java.lang.Math.fma(_t9, _t8, _t14 * _t5));
        } else {
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
            return rotateZXY_s7e5a0297_tail_mulAdd(_t10, _t8, _t11, _t5, ((_t14) * (_t8) - (_t9 * _t5)), ((_t10) * (_t5) + (_t11 * _t8)), ((_t9) * (_t8) + (_t14 * _t5)));
        }
    }

    /** Private tail of {@code rotateZYX}; reached only through it. */
    private DoubleQuat rotateZYX_s5febe075_tail_fma(double _t10, double _t8, double _t11, double _t5, double _t19, double _t21, double _t20) {
        double _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        return new DoubleQuat(java.lang.Math.fma(this.x, _t19, this.w * _t21) + java.lang.Math.fma(this.y, _t22, -(this.z * _t20)), java.lang.Math.fma(this.y, _t19, this.z * _t21) + java.lang.Math.fma(this.w, _t20, -this.x * _t22), java.lang.Math.fma(this.x, _t20, this.w * _t22) + java.lang.Math.fma(this.z, _t19, -(this.y * _t21)), java.lang.Math.fma(this.w, _t19, -this.x * _t21) - java.lang.Math.fma(this.y, _t20, this.z * _t22));
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z), to this quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return the resulting quaternion
     */
    public DoubleQuat rotateZYX(double angleZ, double angleY, double angleX) {
        if (Math.useFma()) {
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
            return rotateZYX_s5febe075_tail_fma(_t10, _t8, _t11, _t5, java.lang.Math.fma(_t9, _t5, _t12 * _t8), java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), java.lang.Math.fma(_t11, _t8, _t10 * _t5));
        } else {
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
            return rotateYZX_s2c94f613_tail_mulAdd(_t10, _t8, _t11, _t5, ((_t9) * (_t5) + (_t12 * _t8)), ((_t12) * (_t5) - (_t9 * _t8)), ((_t11) * (_t8) + (_t10 * _t5)));
        }
    }

    /**
     * Transform {@code v} by this quaternion, returning the result as a value.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param v the vector to transform
     * @return the resulting vector
     */
    public Double3 transform(Double3 v) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        if (!Math.useFma()) return transform_mulAdd(vX, vY, vZ);
        double _t9 = 2.0 * java.lang.Math.fma(this.x, vY, -(this.y * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.z, vX, -(this.x * vZ));
        double _t11 = 2.0 * java.lang.Math.fma(this.y, vZ, -(this.z * vY));
        return new Double3(java.lang.Math.fma(this.y, _t9, java.lang.Math.fma(-this.z, _t10, java.lang.Math.fma(this.w, _t11, vX))), java.lang.Math.fma(this.z, _t11, java.lang.Math.fma(-this.x, _t9, java.lang.Math.fma(this.w, _t10, vY))), java.lang.Math.fma(this.x, _t10, java.lang.Math.fma(-this.y, _t11, java.lang.Math.fma(this.w, _t9, vZ))));
    }

    /**
     * Transform ({@code vX}, {@code vY}, {@code vZ}) by this quaternion, returning the result as a
     * value.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Double3 transform(double vX, double vY, double vZ) {
        if (!Math.useFma()) return transform_mulAdd(vX, vY, vZ);
        double _t9 = 2.0 * java.lang.Math.fma(this.x, vY, -(this.y * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.z, vX, -(this.x * vZ));
        double _t11 = 2.0 * java.lang.Math.fma(this.y, vZ, -(this.z * vY));
        return new Double3(java.lang.Math.fma(this.y, _t9, java.lang.Math.fma(-this.z, _t10, java.lang.Math.fma(this.w, _t11, vX))), java.lang.Math.fma(this.z, _t11, java.lang.Math.fma(-this.x, _t9, java.lang.Math.fma(this.w, _t10, vY))), java.lang.Math.fma(this.x, _t10, java.lang.Math.fma(-this.y, _t11, java.lang.Math.fma(this.w, _t9, vZ))));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transform_mulAdd(double vX, double vY, double vZ) {
        double _t9 = 2.0 * ((this.x) * (vY) - (this.y * vX));
        double _t10 = 2.0 * ((this.z) * (vX) - (this.x * vZ));
        double _t11 = 2.0 * ((this.y) * (vZ) - (this.z * vY));
        return new Double3(((this.y) * (_t9) + (((((this.w) * (_t11) + (vX))) - (this.z) * (_t10)))), ((this.z) * (_t11) + (((((this.w) * (_t10) + (vY))) - (this.x) * (_t9)))), ((this.x) * (_t10) + (((((this.w) * (_t9) + (vZ))) - (this.y) * (_t11)))));
    }

    /**
     * Transform {@code v} by the inverse of this quaternion, returning the result as a value.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param v the vector to transform
     * @return the resulting vector
     */
    public Double3 transformInverse(Double3 v) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        if (!Math.useFma()) return transformInverse_mulAdd(vX, vY, vZ);
        double _t9 = 2.0 * java.lang.Math.fma(this.x, vZ, -(this.z * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.y, vX, -(this.x * vY));
        double _t11 = 2.0 * java.lang.Math.fma(this.z, vY, -(this.y * vZ));
        return new Double3(java.lang.Math.fma(this.z, _t9, java.lang.Math.fma(-this.y, _t10, java.lang.Math.fma(this.w, _t11, vX))), java.lang.Math.fma(this.x, _t10, java.lang.Math.fma(-this.z, _t11, java.lang.Math.fma(this.w, _t9, vY))), java.lang.Math.fma(this.y, _t11, java.lang.Math.fma(-this.x, _t9, java.lang.Math.fma(this.w, _t10, vZ))));
    }

    /**
     * Transform ({@code vX}, {@code vY}, {@code vZ}) by the inverse of this quaternion, returning
     * the result as a value.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Double3 transformInverse(double vX, double vY, double vZ) {
        if (!Math.useFma()) return transformInverse_mulAdd(vX, vY, vZ);
        double _t9 = 2.0 * java.lang.Math.fma(this.x, vZ, -(this.z * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.y, vX, -(this.x * vY));
        double _t11 = 2.0 * java.lang.Math.fma(this.z, vY, -(this.y * vZ));
        return new Double3(java.lang.Math.fma(this.z, _t9, java.lang.Math.fma(-this.y, _t10, java.lang.Math.fma(this.w, _t11, vX))), java.lang.Math.fma(this.x, _t10, java.lang.Math.fma(-this.z, _t11, java.lang.Math.fma(this.w, _t9, vY))), java.lang.Math.fma(this.y, _t11, java.lang.Math.fma(-this.x, _t9, java.lang.Math.fma(this.w, _t10, vZ))));
    }

    /** {@code transformInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformInverse_mulAdd(double vX, double vY, double vZ) {
        double _t9 = 2.0 * ((this.x) * (vZ) - (this.z * vX));
        double _t10 = 2.0 * ((this.y) * (vX) - (this.x * vY));
        double _t11 = 2.0 * ((this.z) * (vY) - (this.y * vZ));
        return new Double3(((this.z) * (_t9) + (((((this.w) * (_t11) + (vX))) - (this.y) * (_t10)))), ((this.x) * (_t10) + (((((this.w) * (_t9) + (vY))) - (this.z) * (_t11)))), ((this.y) * (_t11) + (((((this.w) * (_t10) + (vZ))) - (this.x) * (_t9)))));
    }

    /**
     * {@return a copy with the {@code x} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code x} component
     */
    public DoubleQuat withX(double v) {
        return new DoubleQuat(v, y, z, w);
    }

    /**
     * {@return a copy with the {@code y} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code y} component
     */
    public DoubleQuat withY(double v) {
        return new DoubleQuat(x, v, z, w);
    }

    /**
     * {@return a copy with the {@code z} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code z} component
     */
    public DoubleQuat withZ(double v) {
        return new DoubleQuat(x, y, v, w);
    }

    /**
     * {@return a copy with the {@code w} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code w} component
     */
    public DoubleQuat withW(double v) {
        return new DoubleQuat(x, y, z, v);
    }

    @Override public String toString() {
        return "DoubleQuat(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleQuat)) return false;
        DoubleQuat o = (DoubleQuat) obj;
        return Double.doubleToLongBits(x) == Double.doubleToLongBits(o.x)
            && Double.doubleToLongBits(y) == Double.doubleToLongBits(o.y)
            && Double.doubleToLongBits(z) == Double.doubleToLongBits(o.z)
            && Double.doubleToLongBits(w) == Double.doubleToLongBits(o.w);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int) (Double.doubleToLongBits(x) ^ (Double.doubleToLongBits(x) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(y) ^ (Double.doubleToLongBits(y) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(z) ^ (Double.doubleToLongBits(z) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(w) ^ (Double.doubleToLongBits(w) >>> 32));
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Double.isFinite(x)
            && Double.isFinite(y)
            && Double.isFinite(z)
            && Double.isFinite(w);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Double.isNaN(x)
            || Double.isNaN(y)
            || Double.isNaN(z)
            || Double.isNaN(w);
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
    public boolean equalsEpsilon(DoubleQuat other, double epsilon) {
        return java.lang.Math.abs(x - other.x) <= epsilon
            && java.lang.Math.abs(y - other.y) <= epsilon
            && java.lang.Math.abs(z - other.z) <= epsilon
            && java.lang.Math.abs(w - other.w) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoubleQuatSegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleQuatSegOpsUnsafe()
            : new DoubleQuatSegOpsMS();
        static final DoubleQuatBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleQuatBbOpsUnsafe()
            : new DoubleQuatBbOpsApi();
        static final DoubleQuatRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleQuatRawOpsUnsafe()
            : new DoubleQuatRawOpsApi();
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
        dest[offset + 3] = this.w;
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
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat load(double[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        return new DoubleQuat(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat load(double[] src) { return load(src, 0); }

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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat load(DoubleBuffer buf) {
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
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat loadAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code DoubleQuat} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleQuat loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleQuat r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
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
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat load(ByteBuffer buf) {
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
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code DoubleQuat} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleQuat loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleQuat r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
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
    public DoubleQuat storeUnsafe(long address) {
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
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat loadUnsafe(long address) {
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
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

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
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(offset, src);
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
        dest[offset + 3] = (float) this.w;
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
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat load(float[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        return new DoubleQuat(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array, converting each element from {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat load(float[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code float}, starting
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
     * Store the elements into the given buffer, converting each element to {@code float}, starting
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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at the given absolute index (the position is not used or modified).
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
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at its current position and advancing the position accordingly.
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
     * @return a new {@code DoubleQuat} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleQuat loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleQuat r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
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
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
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
     * @return a new {@code DoubleQuat} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleQuat loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleQuat r = StoreLoad.BB_OPS.loadFloatAbsolute(pos, buf);
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
     */
    public DoubleQuat storeFloatUnsafe(long address) {
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
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment, converting each element to {@code float}.
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
    public MemorySegment storeFloat(MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code float},
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
    public MemorySegment storeFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment, converting each element from {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(0L, src); }

    /**
     * Load the elements from the given memory segment, converting each element from {@code float},
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
     * @return a new {@code DoubleQuat} holding the loaded elements
     */
    public static DoubleQuat loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(offset, src);
    }

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]:
     * 2 asin(|a-b|/2) up to pi/2 and pi - 2 asin(|a+b|/2) beyond, so asin always sees an
     * argument of at most sqrt(2)/2 and the angle stays accurate at both ends.
     */
    private static double quatArcAngle(double s) {
        double d = 4.0 - s;
        return s > d ? 2.0 * Math.asin(0.5 * java.lang.Math.sqrt(d)) : Math.PI - 2.0 * Math.asin(0.5 * java.lang.Math.sqrt(s));
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

    private abstract static class DoubleQuatSegOps {
        public abstract MemorySegment store(DoubleQuat self, long offset, MemorySegment dest);
        public abstract DoubleQuat load(long offset, MemorySegment src);
        public abstract MemorySegment storeFloat(DoubleQuat self, long offset, MemorySegment dest);
        public abstract DoubleQuat loadFloat(long offset, MemorySegment src);
    }

    private static final class DoubleQuatSegOpsMS extends DoubleQuatSegOps {
        public MemorySegment store(DoubleQuat self, long offset, MemorySegment dest) {
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset, self.x);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L, self.y);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L, self.z);
            dest.set(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L, self.w);
            return dest;
        }

        public DoubleQuat load(long offset, MemorySegment src) {
            double _c0 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset);
            double _c1 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 8L);
            double _c2 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 16L);
            double _c3 = src.get(ValueLayout.JAVA_DOUBLE_UNALIGNED, offset + 24L);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeFloat(DoubleQuat self, long offset, MemorySegment dest) {
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset, (float) self.x);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.y);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.z);
            dest.set(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.w);
            return dest;
        }

        public DoubleQuat loadFloat(long offset, MemorySegment src) {
            double _c0 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset);
            double _c1 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 4L);
            double _c2 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 8L);
            double _c3 = src.get(ValueLayout.JAVA_FLOAT_UNALIGNED, offset + 12L);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }
    }

    private static final class DoubleQuatSegOpsUnsafe extends DoubleQuatSegOps {
        private static final DoubleQuatSegOpsMS MS = new DoubleQuatSegOpsMS();

        public MemorySegment store(DoubleQuat self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.store(self, offset, dest);
            if (!dest.isNative()) return storeOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            U.putDouble(address, self.x);
            U.putDouble(address + 8L, self.y);
            U.putDouble(address + 16L, self.z);
            U.putDouble(address + 24L, self.w);
            return dest;
        }

        private static MemorySegment storeOnHeap(DoubleQuat self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.store(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            U.putDouble(bytes, address, self.x);
            U.putDouble(bytes, address + 8L, self.y);
            U.putDouble(bytes, address + 16L, self.z);
            U.putDouble(bytes, address + 24L, self.w);
            return dest;
        }

        public DoubleQuat load(long offset, MemorySegment src) {
            if (!src.isNative()) return loadOnHeap(offset, src);
            long address = src.address() + offset;
            double _c0 = U.getDouble(address);
            double _c1 = U.getDouble(address + 8L);
            double _c2 = U.getDouble(address + 16L);
            double _c3 = U.getDouble(address + 24L);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }

        private static DoubleQuat loadOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.load(offset, src);
            long address = HeapSegments.offset(src) + offset;
            double _c0 = U.getDouble(bytes, address);
            double _c1 = U.getDouble(bytes, address + 8L);
            double _c2 = U.getDouble(bytes, address + 16L);
            double _c3 = U.getDouble(bytes, address + 24L);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }

        public MemorySegment storeFloat(DoubleQuat self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
            if (!dest.isNative()) return storeFloatOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            U.putFloat(address, (float) self.x);
            U.putFloat(address + 4L, (float) self.y);
            U.putFloat(address + 8L, (float) self.z);
            U.putFloat(address + 12L, (float) self.w);
            return dest;
        }

        private static MemorySegment storeFloatOnHeap(DoubleQuat self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeFloat(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            U.putFloat(bytes, address, (float) self.x);
            U.putFloat(bytes, address + 4L, (float) self.y);
            U.putFloat(bytes, address + 8L, (float) self.z);
            U.putFloat(bytes, address + 12L, (float) self.w);
            return dest;
        }

        public DoubleQuat loadFloat(long offset, MemorySegment src) {
            if (!src.isNative()) return loadFloatOnHeap(offset, src);
            long address = src.address() + offset;
            double _c0 = U.getFloat(address);
            double _c1 = U.getFloat(address + 4L);
            double _c2 = U.getFloat(address + 8L);
            double _c3 = U.getFloat(address + 12L);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }

        private static DoubleQuat loadFloatOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadFloat(offset, src);
            long address = HeapSegments.offset(src) + offset;
            double _c0 = U.getFloat(bytes, address);
            double _c1 = U.getFloat(bytes, address + 4L);
            double _c2 = U.getFloat(bytes, address + 8L);
            double _c3 = U.getFloat(bytes, address + 12L);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }
    }

    private abstract static class DoubleQuatBbOps {
        public abstract DoubleBuffer storeAbsolute(DoubleQuat self, int index, DoubleBuffer buf);
        public abstract DoubleQuat loadAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeAbsolute(DoubleQuat self, int index, ByteBuffer buf);
        public abstract DoubleQuat loadAbsolute(int index, ByteBuffer buf);
        public abstract FloatBuffer storeAbsolute(DoubleQuat self, int index, FloatBuffer buf);
        public abstract DoubleQuat loadAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeFloatAbsolute(DoubleQuat self, int index, ByteBuffer buf);
        public abstract DoubleQuat loadFloatAbsolute(int index, ByteBuffer buf);
    }

    private static final class DoubleQuatBbOpsApi extends DoubleQuatBbOps {
        public DoubleBuffer storeAbsolute(DoubleQuat self, int index, DoubleBuffer buf) {
            buf.put(index, self.x);
            buf.put(index + 1, self.y);
            buf.put(index + 2, self.z);
            buf.put(index + 3, self.w);
            return buf;
        }

        public DoubleQuat loadAbsolute(int index, DoubleBuffer buf) {
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(index + 2);
            double _c3 = buf.get(index + 3);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeAbsolute(DoubleQuat self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.x);
            buf.putDouble(index + 8, self.y);
            buf.putDouble(index + 16, self.z);
            buf.putDouble(index + 24, self.w);
            return buf;
        }

        public DoubleQuat loadAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getDouble(index);
            double _c1 = buf.getDouble(index + 8);
            double _c2 = buf.getDouble(index + 16);
            double _c3 = buf.getDouble(index + 24);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }

        public FloatBuffer storeAbsolute(DoubleQuat self, int index, FloatBuffer buf) {
            buf.put(index, (float) self.x);
            buf.put(index + 1, (float) self.y);
            buf.put(index + 2, (float) self.z);
            buf.put(index + 3, (float) self.w);
            return buf;
        }

        public DoubleQuat loadAbsolute(int index, FloatBuffer buf) {
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(index + 2);
            double _c3 = buf.get(index + 3);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeFloatAbsolute(DoubleQuat self, int index, ByteBuffer buf) {
            buf.putFloat(index, (float) self.x);
            buf.putFloat(index + 4, (float) self.y);
            buf.putFloat(index + 8, (float) self.z);
            buf.putFloat(index + 12, (float) self.w);
            return buf;
        }

        public DoubleQuat loadFloatAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getFloat(index);
            double _c1 = buf.getFloat(index + 4);
            double _c2 = buf.getFloat(index + 8);
            double _c3 = buf.getFloat(index + 12);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }
    }

    private static final class DoubleQuatBbOpsUnsafe extends DoubleQuatBbOps {
        private static final DoubleQuatBbOpsApi API = new DoubleQuatBbOpsApi();

        public DoubleBuffer storeAbsolute(DoubleQuat self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            U.putDouble(address, self.x);
            U.putDouble(address + 8L, self.y);
            U.putDouble(address + 16L, self.z);
            U.putDouble(address + 24L, self.w);
            return buf;
        }

        public DoubleQuat loadAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeAbsolute(DoubleQuat self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putDouble(address, self.x);
            U.putDouble(address + 8L, self.y);
            U.putDouble(address + 16L, self.z);
            U.putDouble(address + 24L, self.w);
            return buf;
        }

        private static ByteBuffer storeAbsoluteElse(DoubleQuat self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putDouble(bytes, address, self.x);
            U.putDouble(bytes, address + 8L, self.y);
            U.putDouble(bytes, address + 16L, self.z);
            U.putDouble(bytes, address + 24L, self.w);
            return buf;
        }

        public DoubleQuat loadAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }

        private static DoubleQuat loadAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8L);
            double _c2 = _U.getDouble(bytes, address + 16L);
            double _c3 = _U.getDouble(bytes, address + 24L);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }

        public FloatBuffer storeAbsolute(DoubleQuat self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            U.putFloat(address, (float) self.x);
            U.putFloat(address + 4L, (float) self.y);
            U.putFloat(address + 8L, (float) self.z);
            U.putFloat(address + 12L, (float) self.w);
            return buf;
        }

        public DoubleQuat loadAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }

        public ByteBuffer storeFloatAbsolute(DoubleQuat self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeFloatAbsoluteElse(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putFloat(address, (float) self.x);
            U.putFloat(address + 4L, (float) self.y);
            U.putFloat(address + 8L, (float) self.z);
            U.putFloat(address + 12L, (float) self.w);
            return buf;
        }

        private static ByteBuffer storeFloatAbsoluteElse(DoubleQuat self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeFloatAbsolute(self, index, buf);
            long address = U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            U.putFloat(bytes, address, (float) self.x);
            U.putFloat(bytes, address + 4L, (float) self.y);
            U.putFloat(bytes, address + 8L, (float) self.z);
            U.putFloat(bytes, address + 12L, (float) self.w);
            return buf;
        }

        public DoubleQuat loadFloatAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadFloatAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }

        private static DoubleQuat loadFloatAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadFloatAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4L);
            double _c2 = _U.getFloat(bytes, address + 8L);
            double _c3 = _U.getFloat(bytes, address + 12L);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }
    }

    private abstract static class DoubleQuatRawOps {
        public abstract DoubleQuat storeUnsafe(DoubleQuat self, long address);
        public abstract DoubleQuat loadUnsafe(long address);
        public abstract DoubleQuat storeFloatUnsafe(DoubleQuat self, long address);
        public abstract DoubleQuat loadFloatUnsafe(long address);
    }

    private static final class DoubleQuatRawOpsApi extends DoubleQuatRawOps {
        public DoubleQuat storeUnsafe(DoubleQuat self, long address) {
            self.store(0L, virtualMemory().asSlice(address, 32L));
            return self;
        }

        public DoubleQuat loadUnsafe(long address) {
            return DoubleQuat.load(0L, virtualMemory().asSlice(address, 32L));
        }

        public DoubleQuat storeFloatUnsafe(DoubleQuat self, long address) {
            self.storeFloat(0L, virtualMemory().asSlice(address, 16L));
            return self;
        }

        public DoubleQuat loadFloatUnsafe(long address) {
            return DoubleQuat.loadFloat(0L, virtualMemory().asSlice(address, 16L));
        }
    }

    private static final class DoubleQuatRawOpsUnsafe extends DoubleQuatRawOps {
        public DoubleQuat storeUnsafe(DoubleQuat self, long address) {
            U.putDouble(address, self.x);
            U.putDouble(address + 8L, self.y);
            U.putDouble(address + 16L, self.z);
            U.putDouble(address + 24L, self.w);
            return self;
        }

        public DoubleQuat storeHeap(DoubleQuat self, byte[] bytes, long address) {
            U.putDouble(bytes, address, self.x);
            U.putDouble(bytes, address + 8L, self.y);
            U.putDouble(bytes, address + 16L, self.z);
            U.putDouble(bytes, address + 24L, self.w);
            return self;
        }

        public DoubleQuat loadUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }

        public DoubleQuat loadHeap(byte[] bytes, long address) {
            double _c0 = U.getDouble(bytes, address);
            double _c1 = U.getDouble(bytes, address + 8L);
            double _c2 = U.getDouble(bytes, address + 16L);
            double _c3 = U.getDouble(bytes, address + 24L);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }

        public DoubleQuat storeFloatUnsafe(DoubleQuat self, long address) {
            U.putFloat(address, (float) self.x);
            U.putFloat(address + 4L, (float) self.y);
            U.putFloat(address + 8L, (float) self.z);
            U.putFloat(address + 12L, (float) self.w);
            return self;
        }

        public DoubleQuat storeFloatHeap(DoubleQuat self, byte[] bytes, long address) {
            U.putFloat(bytes, address, (float) self.x);
            U.putFloat(bytes, address + 4L, (float) self.y);
            U.putFloat(bytes, address + 8L, (float) self.z);
            U.putFloat(bytes, address + 12L, (float) self.w);
            return self;
        }

        public DoubleQuat loadFloatUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }

        public DoubleQuat loadFloatHeap(byte[] bytes, long address) {
            double _c0 = U.getFloat(bytes, address);
            double _c1 = U.getFloat(bytes, address + 4L);
            double _c2 = U.getFloat(bytes, address + 8L);
            double _c3 = U.getFloat(bytes, address + 12L);
            return new DoubleQuat(_c0, _c1, _c2, _c3);
        }
    }
}
