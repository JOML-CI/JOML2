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
 * Immutable quaternion of single-precision {@code float} components, declared as a value record.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance; as a value class, instances have no identity and may be flattened by the JVM.
 * <p>
 * A rotation is a unit quaternion. The operations that apply this quaternion as a rotation - the
 * transforms, the matrix conversions, the Euler angles, {@code angleTo} and {@code rotateTowards} -
 * assume unit length and do not divide it out. A value that has drifted from unit length (after
 * many multiplications, say) gives wrong results rather than an error: {@code normalize} it first.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns).
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 *
 * @param x the {@code x} component
 * @param y the {@code y} component
 * @param z the {@code z} component
 * @param w the {@code w} component
 */
@jdk.internal.vm.annotation.LooselyConsistentValue
public value record FloatQuat(float x, float y, float z, float w) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int SIZE_BYTES = 16;

    /** The zero quaternion (all components 0). */
    public static final FloatQuat ZERO = new FloatQuat(0, 0, 0, 0);

    /** The identity quaternion. */
    public static final FloatQuat IDENTITY = new FloatQuat();

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
    public FloatQuat(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Create a new instance initialized to the identity.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public FloatQuat() {
        this(0, 0, 0, 1);
    }

    /** {@return the {@code x} component} <p>Valid input: any value, NaN and the infinities included. */
    public float x() { return x; }
    /** {@return the {@code y} component} <p>Valid input: any value, NaN and the infinities included. */
    public float y() { return y; }
    /** {@return the {@code z} component} <p>Valid input: any value, NaN and the infinities included. */
    public float z() { return z; }
    /** {@return the {@code w} component} <p>Valid input: any value, NaN and the infinities included. */
    public float w() { return w; }

    /**
     * Create a rotation uniformly distributed over all rotations, drawing the 3 samples of
     * {@code makeUniformRotation} from {@code rng}, each with {@code rng.nextFloat()}, in parameter
     * order.
     * <p>
     * Valid input: any value.
     *
     * @param rng the random number generator to draw the 3 samples from
     * @return the resulting quaternion
     */
    public static FloatQuat makeRandomRotation(java.util.Random rng) {
        return makeUniformRotation(rng.nextFloat(), rng.nextFloat(), rng.nextFloat());
    }


    /**
     * Invert this quaternion, returning the result as a value.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @return the resulting quaternion
     */
    public FloatQuat invert() {
        float _t3_inv = 1.0f / Math.fma(this.w, this.w, Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y)));
        return new FloatQuat(-(this.x * _t3_inv), -(this.y * _t3_inv), -(this.z * _t3_inv), this.w * _t3_inv);
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
    public FloatQuat invertProduct(FloatQuat other) {
        return invertProduct(other.x(), other.y(), other.z(), other.w());
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
    public FloatQuat invertProduct(float otherX, float otherY, float otherZ, float otherW) {
        float _t20 = Math.fma(otherX, this.w, otherW * this.x) + Math.fma(otherZ, this.y, -(otherY * this.z));
        float _t21 = Math.fma(otherW, this.w, -(otherX * this.x)) - Math.fma(otherY, this.y, otherZ * this.z);
        float _t22 = Math.fma(otherY, this.x, otherZ * this.w) + Math.fma(otherW, this.z, -(otherX * this.y));
        float _t23 = Math.fma(otherX, this.z, otherW * this.y) + Math.fma(otherY, this.w, -(otherZ * this.x));
        float _t27_inv = 1.0f / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
        return new FloatQuat(-(_t20 * _t27_inv), -(_t23 * _t27_inv), -(_t22 * _t27_inv), _t21 * _t27_inv);
    }


    /**
     * Add {@code other} to this quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to add
     * @return the resulting quaternion
     */
    public FloatQuat add(FloatQuat other) {
        return add(other.x(), other.y(), other.z(), other.w());
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
    public FloatQuat add(float otherX, float otherY, float otherZ, float otherW) {
        return new FloatQuat(otherX + this.x, otherY + this.y, otherZ + this.z, otherW + this.w);
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
    public FloatQuat mul(float scalar) {
        return new FloatQuat(scalar * this.x, scalar * this.y, scalar * this.z, scalar * this.w);
    }


    /**
     * Negate this quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting quaternion
     */
    public FloatQuat negate() {
        return new FloatQuat(-this.x, -this.y, -this.z, -this.w);
    }


    /**
     * Subtract {@code other} from this quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to subtract
     * @return the resulting quaternion
     */
    public FloatQuat sub(FloatQuat other) {
        return sub(other.x(), other.y(), other.z(), other.w());
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
    public FloatQuat sub(float otherX, float otherY, float otherZ, float otherW) {
        return new FloatQuat(this.x - otherX, this.y - otherY, this.z - otherZ, this.w - otherW);
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
    public static FloatQuat makeUniformRotation(float u1, float u2, float u3) {
        float _t0 = (float) Math.sqrt(u1);
        float _t1 = u2 * 6.2831855f;
        float _t3 = u3 * 6.2831855f;
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sqrt(1.0f - u1);
        float _t6 = (float) Math.sin(_t3);
        return new FloatQuat(_t4 * _t5, (float) Math.cosFromSin(_t4, _t1) * _t5, _t6 * _t0, (float) Math.cosFromSin(_t6, _t3) * _t0);
    }


    /**
     * Create a new quaternion from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the quaternion to copy
     * @return the resulting quaternion
     */
    public FloatQuat set(FloatQuat v) {
        return set(v.x(), v.y(), v.z(), v.w());
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
    public FloatQuat set(float vX, float vY, float vZ, float vW) {
        return new FloatQuat(vX, vY, vZ, vW);
    }


    /**
     * Convert this quaternion to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code DoubleQuat} holding the result
     */
    public DoubleQuat toDouble() {
        return new DoubleQuat(this.x, this.y, this.z, this.w);
    }


    /**
     * Create the rotation (real) part of the unit dual quaternion {@code dq}.
     * <p>
     * Valid input: {@code dq} must be a unit dual quaternion.
     *
     * @param dq the dual quaternion to convert
     * @return the resulting quaternion
     */
    public static FloatQuat makeFromDualQuat(FloatDualQuat dq) {
        return makeFromDualQuat(dq.rX(), dq.rY(), dq.rZ(), dq.rW(), dq.dX(), dq.dY(), dq.dZ(), dq.dW());
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
    public static FloatQuat makeFromDualQuat(float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        return new FloatQuat(dqRX, dqRY, dqRZ, dqRW);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatQuat makeFromMatrix_s37cad23f_tail(float _t10, float _sp0, float _t1, Float3x3 m, float _t2, float _t15, float _sp1, float _t4, float _sp2, float _t6, float _t7, float _sp3, float _t16, float _t8, float _t9, float _t17, float _t14) {
        float _sfx0, _sfx1, _sfx2;
        if (_t10 > 0.0f) {
            _sfx0 = _sp0 * _t1;
            _sfx1 = _sp0 * _t7;
            _sfx2 = _sp0 * _t9;
        } else {
            if (m.m00() > _t2) {
                _sfx0 = 0.5f * (float) Math.sqrt(_t15);
                _sfx1 = _sp3 * _t4;
                _sfx2 = _sp3 * _t6;
            } else {
                if (m.m11() > m.m22()) {
                    _sfx0 = _sp1 * _t4;
                    _sfx1 = 0.5f * (float) Math.sqrt(_t16);
                    _sfx2 = _sp1 * _t8;
                } else {
                    _sfx0 = _sp2 * _t6;
                    _sfx1 = _sp2 * _t8;
                    _sfx2 = 0.5f * (float) Math.sqrt(_t17);
                }
            }
        }
        return makeFromMatrix_s37cad23f_tail2(_t10, _t14, m, _t2, _sp3, _t1, _sp1, _t7, _sp2, _t9, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatQuat makeFromMatrix_s37cad23f_tail2(float _t10, float _t14, Float3x3 m, float _t2, float _sp3, float _t1, float _sp1, float _t7, float _sp2, float _t9, float _sfx0, float _sfx1, float _sfx2) {
        float _sfx3 = _t10 > 0.0f ? 0.5f * (float) Math.sqrt(_t14) : m.m00() > _t2 ? _sp3 * _t1 : m.m11() > m.m22() ? _sp1 * _t7 : _sp2 * _t9;
        return new FloatQuat(_sfx0, _sfx1, _sfx2, _sfx3);
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
    public static FloatQuat makeFromMatrix(Float3x3 m) {
        float _t0 = m.m00() + m.m11();
        float _t10 = m.m22() + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (m.m00() - (m.m11() + m.m22()));
        float _t16 = 1.0f + (m.m11() - (m.m00() + m.m22()));
        float _t17 = 1.0f + (m.m22() - _t0);
        return makeFromMatrix_s37cad23f_tail(_t10, 0.5f * (1.0f / (float) Math.sqrt(_t14)), m.m21() - m.m12(), m, Math.max(m.m11(), m.m22()), _t15, 0.5f * (1.0f / (float) Math.sqrt(_t16)), m.m01() + m.m10(), 0.5f * (1.0f / (float) Math.sqrt(_t17)), m.m02() + m.m20(), m.m02() - m.m20(), 0.5f * (1.0f / (float) Math.sqrt(_t15)), _t16, m.m12() + m.m21(), m.m10() - m.m01(), _t17, _t14);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatQuat makeFromMatrix_s37cad600_tail(float _t10, float _sp0, float _t1, Float3x4 m, float _t2, float _t15, float _sp1, float _t4, float _sp2, float _t6, float _t7, float _sp3, float _t16, float _t8, float _t9, float _t17, float _t14) {
        float _sfx0, _sfx1, _sfx2;
        if (_t10 > 0.0f) {
            _sfx0 = _sp0 * _t1;
            _sfx1 = _sp0 * _t7;
            _sfx2 = _sp0 * _t9;
        } else {
            if (m.m00() > _t2) {
                _sfx0 = 0.5f * (float) Math.sqrt(_t15);
                _sfx1 = _sp3 * _t4;
                _sfx2 = _sp3 * _t6;
            } else {
                if (m.m11() > m.m22()) {
                    _sfx0 = _sp1 * _t4;
                    _sfx1 = 0.5f * (float) Math.sqrt(_t16);
                    _sfx2 = _sp1 * _t8;
                } else {
                    _sfx0 = _sp2 * _t6;
                    _sfx1 = _sp2 * _t8;
                    _sfx2 = 0.5f * (float) Math.sqrt(_t17);
                }
            }
        }
        return makeFromMatrix_s37cad600_tail2(_t10, _t14, m, _t2, _sp3, _t1, _sp1, _t7, _sp2, _t9, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatQuat makeFromMatrix_s37cad600_tail2(float _t10, float _t14, Float3x4 m, float _t2, float _sp3, float _t1, float _sp1, float _t7, float _sp2, float _t9, float _sfx0, float _sfx1, float _sfx2) {
        float _sfx3 = _t10 > 0.0f ? 0.5f * (float) Math.sqrt(_t14) : m.m00() > _t2 ? _sp3 * _t1 : m.m11() > m.m22() ? _sp1 * _t7 : _sp2 * _t9;
        return new FloatQuat(_sfx0, _sfx1, _sfx2, _sfx3);
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
    public static FloatQuat makeFromMatrix(Float3x4 m) {
        float _t0 = m.m00() + m.m11();
        float _t10 = m.m22() + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (m.m00() - (m.m11() + m.m22()));
        float _t16 = 1.0f + (m.m11() - (m.m00() + m.m22()));
        float _t17 = 1.0f + (m.m22() - _t0);
        return makeFromMatrix_s37cad600_tail(_t10, 0.5f * (1.0f / (float) Math.sqrt(_t14)), m.m21() - m.m12(), m, Math.max(m.m11(), m.m22()), _t15, 0.5f * (1.0f / (float) Math.sqrt(_t16)), m.m01() + m.m10(), 0.5f * (1.0f / (float) Math.sqrt(_t17)), m.m02() + m.m20(), m.m02() - m.m20(), 0.5f * (1.0f / (float) Math.sqrt(_t15)), _t16, m.m12() + m.m21(), m.m10() - m.m01(), _t17, _t14);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatQuat makeFromMatrix_s37d8ed81_tail(float _t10, float _sp0, float _t1, Float4x4 m, float _t2, float _t15, float _sp1, float _t4, float _sp2, float _t6, float _t7, float _sp3, float _t16, float _t8, float _t9, float _t17, float _t14) {
        float _sfx0, _sfx1, _sfx2;
        if (_t10 > 0.0f) {
            _sfx0 = _sp0 * _t1;
            _sfx1 = _sp0 * _t7;
            _sfx2 = _sp0 * _t9;
        } else {
            if (m.m00() > _t2) {
                _sfx0 = 0.5f * (float) Math.sqrt(_t15);
                _sfx1 = _sp3 * _t4;
                _sfx2 = _sp3 * _t6;
            } else {
                if (m.m11() > m.m22()) {
                    _sfx0 = _sp1 * _t4;
                    _sfx1 = 0.5f * (float) Math.sqrt(_t16);
                    _sfx2 = _sp1 * _t8;
                } else {
                    _sfx0 = _sp2 * _t6;
                    _sfx1 = _sp2 * _t8;
                    _sfx2 = 0.5f * (float) Math.sqrt(_t17);
                }
            }
        }
        return makeFromMatrix_s37d8ed81_tail2(_t10, _t14, m, _t2, _sp3, _t1, _sp1, _t7, _sp2, _t9, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code makeFromMatrix}; reached only through it. */
    private static FloatQuat makeFromMatrix_s37d8ed81_tail2(float _t10, float _t14, Float4x4 m, float _t2, float _sp3, float _t1, float _sp1, float _t7, float _sp2, float _t9, float _sfx0, float _sfx1, float _sfx2) {
        float _sfx3 = _t10 > 0.0f ? 0.5f * (float) Math.sqrt(_t14) : m.m00() > _t2 ? _sp3 * _t1 : m.m11() > m.m22() ? _sp1 * _t7 : _sp2 * _t9;
        return new FloatQuat(_sfx0, _sfx1, _sfx2, _sfx3);
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
    public static FloatQuat makeFromMatrix(Float4x4 m) {
        float _t0 = m.m00() + m.m11();
        float _t10 = m.m22() + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (m.m00() - (m.m11() + m.m22()));
        float _t16 = 1.0f + (m.m11() - (m.m00() + m.m22()));
        float _t17 = 1.0f + (m.m22() - _t0);
        return makeFromMatrix_s37d8ed81_tail(_t10, 0.5f * (1.0f / (float) Math.sqrt(_t14)), m.m21() - m.m12(), m, Math.max(m.m11(), m.m22()), _t15, 0.5f * (1.0f / (float) Math.sqrt(_t16)), m.m01() + m.m10(), 0.5f * (1.0f / (float) Math.sqrt(_t17)), m.m02() + m.m20(), m.m02() - m.m20(), 0.5f * (1.0f / (float) Math.sqrt(_t15)), _t16, m.m12() + m.m21(), m.m10() - m.m01(), _t17, _t14);
    }


    /**
     * Convert this quaternion to a pure-rotation dual quaternion (zero dual part), returning the
     * result as a value.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting dual quaternion
     */
    public FloatDualQuat toDualQuat() {
        return new FloatDualQuat(this.x, this.y, this.z, this.w, 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Compute the matrix representation of this quaternion, returning the result as a value.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting matrix
     */
    public Float4x4 toMatrix() {
        float _t0 = this.z * this.z;
        float _t1 = this.z * this.w;
        float _t2 = this.y * this.w;
        return new Float4x4(Math.fma(-2.0f, Math.fma(this.y, this.y, _t0), 1.0f), 2.0f * Math.fma(this.x, this.y, -_t1), 2.0f * Math.fma(this.x, this.z, _t2), 0.0f, 2.0f * Math.fma(this.x, this.y, _t1), Math.fma(-2.0f, Math.fma(this.x, this.x, _t0), 1.0f), 2.0f * Math.fma(this.y, this.z, -(this.x * this.w)), 0.0f, 2.0f * Math.fma(this.x, this.z, -_t2), 2.0f * Math.fma(this.x, this.w, this.y * this.z), Math.fma(-2.0f, Math.fma(this.x, this.x, this.y * this.y), 1.0f), 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Compute the 3x3 rotation matrix representation of this quaternion, returning the result as a
     * value.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting matrix
     */
    public Float3x3 toMatrix3x3() {
        float _t0 = this.z * this.z;
        float _t1 = this.z * this.w;
        float _t2 = this.y * this.w;
        return new Float3x3(Math.fma(-2.0f, Math.fma(this.y, this.y, _t0), 1.0f), 2.0f * Math.fma(this.x, this.y, -_t1), 2.0f * Math.fma(this.x, this.z, _t2), 2.0f * Math.fma(this.x, this.y, _t1), Math.fma(-2.0f, Math.fma(this.x, this.x, _t0), 1.0f), 2.0f * Math.fma(this.y, this.z, -(this.x * this.w)), 2.0f * Math.fma(this.x, this.z, -_t2), 2.0f * Math.fma(this.x, this.w, this.y * this.z), Math.fma(-2.0f, Math.fma(this.x, this.x, this.y * this.y), 1.0f), 0);
    }


    /**
     * Compute the 3x4 matrix representation of this quaternion (the omitted last row is implicitly
     * {@code 0, 0, 0, 1}), returning the result as a value.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @return the resulting matrix
     */
    public Float3x4 toMatrix3x4() {
        float _t0 = this.z * this.z;
        float _t1 = this.z * this.w;
        float _t2 = this.y * this.w;
        return new Float3x4(Math.fma(-2.0f, Math.fma(this.y, this.y, _t0), 1.0f), 2.0f * Math.fma(this.x, this.y, -_t1), 2.0f * Math.fma(this.x, this.z, _t2), 0.0f, 2.0f * Math.fma(this.x, this.y, _t1), Math.fma(-2.0f, Math.fma(this.x, this.x, _t0), 1.0f), 2.0f * Math.fma(this.y, this.z, -(this.x * this.w)), 0.0f, 2.0f * Math.fma(this.x, this.z, -_t2), 2.0f * Math.fma(this.x, this.w, this.y * this.z), Math.fma(-2.0f, Math.fma(this.x, this.x, this.y * this.y), 1.0f), 0.0f, Joml.BIT_ORTHOGONAL);
    }

    /**
     * Result value of {@code decomposeSwingTwist}.
     *
     * @param swing the swing
     * @param twist the twist
     */
    @jdk.internal.vm.annotation.LooselyConsistentValue
    public value record DecomposeSwingTwistResult(FloatQuat swing, FloatQuat twist) {
        /**
         * Canonical constructor.
         * <p>
         * Valid input: any value, NaN and the infinities included.
         *
         * @param swing the swing
         * @param twist the twist
         */
        public DecomposeSwingTwistResult(FloatQuat swing, FloatQuat twist) {
            this.swing = swing;
            this.twist = twist;
        }
        /** {@return the {@code swing} component} <p>Valid input: any value, NaN and the infinities included. */
        public FloatQuat swing() { return swing; }
        /** {@return the {@code twist} component} <p>Valid input: any value, NaN and the infinities included. */
        public FloatQuat twist() { return twist; }
    }

    /** Private tail of {@code decomposeSwingTwist}; reached only through it. */
    private DecomposeSwingTwistResult decomposeSwingTwist_s3ccd94aa_tail(float _t4, float _t5, Float3 axis, float _t7) {
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = this.w * _t5;
            _t12 = axis.x() * _t7;
            _t13 = axis.y() * _t7;
            _t14 = axis.z() * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        return new DecomposeSwingTwistResult(new FloatQuat(Math.fma(this.x, _t11, -(this.w * _t12)) + Math.fma(this.z, _t13, -(this.y * _t14)), Math.fma(this.x, _t14, -(this.w * _t13)) + Math.fma(this.y, _t11, -(this.z * _t12)), Math.fma(this.y, _t12, this.z * _t11) + Math.fma(-this.x, _t13, -(this.w * _t14)), Math.fma(this.x, _t12, this.w * _t11) - Math.fma(-this.z, _t14, -(this.y * _t13))), new FloatQuat(_t12, _t13, _t14, _t11));
    }


    /**
     * Decompose this quaternion into a swing about an axis perpendicular to {@code axis} followed
     * by a twist about {@code axis}, such that {@code swing * twist} is this rotation.
     * <p>
     * Equivalent to calling {@code getSwing} and {@code getTwist} separately, but shares the work.
     * The twist is the identity when the rotation is a pure swing, including the 180-degree
     * perpendicular case where it is undefined.
     * <p>
     * Valid input: {@code axis} must have unit length; this quaternion must have unit length.
     *
     * @param axis the rotation axis
     * @return a new result value holding the swing and the twist
     */
    public DecomposeSwingTwistResult decomposeSwingTwist(Float3 axis) {
        float _t2 = Math.fma(axis.z(), this.z, Math.fma(axis.x(), this.x, axis.y() * this.y));
        float _t4 = Math.fma(this.w, this.w, _t2 * _t2);
        float _t5 = (1.0f / (float) Math.sqrt(_t4));
        return decomposeSwingTwist_s3ccd94aa_tail(_t4, _t5, axis, _t2 * _t5);
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
    public FloatQuat getSwing(Float3 axis) {
        return getSwing(axis.x(), axis.y(), axis.z());
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
    public FloatQuat getSwing(float axisX, float axisY, float axisZ) {
        float _t2 = Math.fma(axisZ, this.z, Math.fma(axisX, this.x, axisY * this.y));
        float _t4 = Math.fma(this.w, this.w, _t2 * _t2);
        float _t5 = (1.0f / (float) Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = this.w * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        return new FloatQuat(Math.fma(this.x, _t11, -(this.w * _t12)) + Math.fma(this.z, _t13, -(this.y * _t14)), Math.fma(this.x, _t14, -(this.w * _t13)) + Math.fma(this.y, _t11, -(this.z * _t12)), Math.fma(this.y, _t12, this.z * _t11) + Math.fma(-this.x, _t13, -(this.w * _t14)), Math.fma(this.x, _t12, this.w * _t11) - Math.fma(-this.z, _t14, -(this.y * _t13)));
    }


    /**
     * Extract the twist component of this quaternion: the rotation about {@code axis} in the
     * swing-twist decomposition, returning the result as a value.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity when the rotation is a
     * pure swing, including the 180-degree perpendicular case where the twist angle is undefined.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param axis the rotation axis
     * @return the resulting quaternion
     */
    public FloatQuat getTwist(Float3 axis) {
        return getTwist(axis.x(), axis.y(), axis.z());
    }


    /**
     * Extract the twist component of this quaternion: the rotation about ({@code axisX},
     * {@code axisY}, {@code axisZ}) in the swing-twist decomposition, returning the result as a
     * value.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity when the rotation is a
     * pure swing, including the 180-degree perpendicular case where the twist angle is undefined.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return the resulting quaternion
     */
    public FloatQuat getTwist(float axisX, float axisY, float axisZ) {
        float _t2 = Math.fma(axisZ, this.z, Math.fma(axisX, this.x, axisY * this.y));
        float _t4 = Math.fma(this.w, this.w, _t2 * _t2);
        float _t5 = (1.0f / (float) Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            return new FloatQuat(axisX * _t6, axisY * _t6, axisZ * _t6, this.w * _t5);
        } else {
            return new FloatQuat(0.0f, 0.0f, 0.0f, 1.0f);
        }
    }


    /**
     * Create an identity quaternion.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting quaternion
     */
    public static FloatQuat makeIdentity() {
        return new FloatQuat(0.0f, 0.0f, 0.0f, 1.0f);
    }


    /**
     * Create an all-zero quaternion.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting quaternion
     */
    public static FloatQuat makeZero() {
        return FloatQuat.ZERO;
    }


    /**
     * Linearly interpolate between this quaternion and {@code other} using the interpolation factor
     * {@code t}, returning the result as a value.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting quaternion
     */
    public FloatQuat lerp(FloatQuat other, float t) {
        return lerp(other.x(), other.y(), other.z(), other.w(), t);
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
     * magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
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
    public FloatQuat lerp(float otherX, float otherY, float otherZ, float otherW, float t) {
        return new FloatQuat(Math.fma(t, otherX - this.x, this.x), Math.fma(t, otherY - this.y, this.y), Math.fma(t, otherZ - this.z, this.z), Math.fma(t, otherW - this.w, this.w));
    }


    /**
     * Interpolate between this quaternion and {@code target} using the interpolation factor
     * {@code alpha} and normalize the result, returning the result as a value.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting quaternion
     */
    public FloatQuat nlerp(FloatQuat target, float alpha) {
        return nlerp(target.x(), target.y(), target.z(), target.w(), alpha);
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
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
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
    public FloatQuat nlerp(float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t4 = Math.fma(alpha, targetW - this.w, this.w);
        float _t5 = Math.fma(alpha, targetZ - this.z, this.z);
        float _t6 = Math.fma(alpha, targetX - this.x, this.x);
        float _t7 = Math.fma(alpha, targetY - this.y, this.y);
        float _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        float _t12 = (1.0f / (float) Math.sqrt(_t11));
        if (_t11 != 0.0f) {
            return new FloatQuat(_t6 * _t12, _t7 * _t12, _t5 * _t12, _t4 * _t12);
        } else {
            return FloatQuat.ZERO;
        }
    }


    /**
     * Interpolate along the shortest path between this quaternion and {@code target} using the
     * interpolation factor {@code alpha} and normalize the result, returning the result as a value.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting quaternion
     */
    public FloatQuat nlerpShortest(FloatQuat target, float alpha) {
        return nlerpShortest(target.x(), target.y(), target.z(), target.w(), alpha);
    }


    /**
     * Interpolate along the shortest path between this quaternion and ({@code targetX},
     * {@code targetY}, {@code targetZ}, {@code targetW}) using the interpolation factor
     * {@code alpha} and normalize the result, returning the result as a value.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
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
    public FloatQuat nlerpShortest(float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t17, _t18, _t19, _t20;
        if (-Math.fma(this.w, targetW, Math.fma(this.z, targetZ, Math.fma(this.x, targetX, this.y * targetY))) > 0.0f) {
            _t17 = Math.fma(alpha, -targetW - this.w, this.w);
            _t18 = Math.fma(alpha, -targetZ - this.z, this.z);
            _t19 = Math.fma(alpha, -targetX - this.x, this.x);
            _t20 = Math.fma(alpha, -targetY - this.y, this.y);
        } else {
            _t17 = Math.fma(alpha, targetW - this.w, this.w);
            _t18 = Math.fma(alpha, targetZ - this.z, this.z);
            _t19 = Math.fma(alpha, targetX - this.x, this.x);
            _t20 = Math.fma(alpha, targetY - this.y, this.y);
        }
        float _t24 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        float _t25 = (1.0f / (float) Math.sqrt(_t24));
        if (_t24 != 0.0f) {
            return new FloatQuat(_t19 * _t25, _t20 * _t25, _t18 * _t25, _t17 * _t25);
        } else {
            return FloatQuat.ZERO;
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
    public FloatQuat slerp(FloatQuat target, float alpha) {
        return slerp(target.x(), target.y(), target.z(), target.w(), alpha);
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
    public FloatQuat slerp(float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t1 = this.w + targetW;
        float _t2 = this.z + targetZ;
        float _t3 = this.x + targetX;
        float _t4 = this.y + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _t26, _t27;
        if (_t19 > 2.0E-6f) {
            _t26 = _t21 * (float) Math.sin(alpha * _t12);
            _t27 = _t21 * (float) Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0f - _t5;
                _t27 = _t5;
            }
        }
        return new FloatQuat(Math.fma(this.x, _t27, targetX * _t26), Math.fma(this.y, _t27, targetY * _t26), Math.fma(this.z, _t27, targetZ * _t26), Math.fma(this.w, _t27, targetW * _t26));
    }


    /**
     * Spherically interpolate along the shortest path between this quaternion and {@code target}
     * using the interpolation factor {@code alpha}, returning the result as a value.
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
    public FloatQuat slerpShortest(FloatQuat target, float alpha) {
        return slerpShortest(target.x(), target.y(), target.z(), target.w(), alpha);
    }

    /** Private tail of {@code slerpShortest}; reached only through it. */
    private FloatQuat slerpShortest_s9ffd1c_tail(float _t17, float _t25, float _t19, float _t21, float _t17_inv, float alpha, float _t0, float _t22, float _t23, float _t24) {
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
            _t42 = Math.fma(this.w, _t25, _t19 * _t21) * _t17_inv;
            _t43 = Math.fma(this.z, _t25, _t19 * _t22) * _t17_inv;
            _t44 = Math.fma(this.x, _t25, _t19 * _t23) * _t17_inv;
            _t45 = Math.fma(this.y, _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = Math.fma(alpha, _t21, this.w * _t0);
            _t43 = Math.fma(alpha, _t22, this.z * _t0);
            _t44 = Math.fma(alpha, _t23, this.x * _t0);
            _t45 = Math.fma(alpha, _t24, this.y * _t0);
        }
        float _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        return slerpShortest_s9ffd1c_tail2(_t49, (1.0f / (float) Math.sqrt(_t49)), _t44, _t45, _t43, _t42);
    }

    /**
     * Private tail of {@code slerpShortest}. Shared by the identical private paths of
     * {@code slerpShortest} and {@code rotateTowards}; reached only through them.
     */
    private FloatQuat slerpShortest_s9ffd1c_tail2(float _t49, float _t50, float _t44, float _t45, float _t43, float _t42) {
        float _sfx0, _sfx1, _sfx2, _sfx3;
        if (_t49 != 0.0f) {
            _sfx0 = _t50 * _t44;
            _sfx1 = _t50 * _t45;
            _sfx2 = _t50 * _t43;
            _sfx3 = _t50 * _t42;
        } else {
            _sfx0 = 0.0f;
            _sfx1 = 0.0f;
            _sfx2 = 0.0f;
            _sfx3 = 0.0f;
        }
        return new FloatQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Spherically interpolate along the shortest path between this quaternion and ({@code targetX},
     * {@code targetY}, {@code targetZ}, {@code targetW}) using the interpolation factor
     * {@code alpha}, returning the result as a value.
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
    public FloatQuat slerpShortest(float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t12 = Math.fma(this.w, targetW, Math.fma(this.z, targetZ, Math.fma(this.x, targetX, this.y * targetY)));
        float _t16 = (float) Math.acos(Math.min(1.0f, Math.abs(_t12)));
        float _t17 = (float) Math.sin(_t16);
        float _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0f) {
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
        return slerpShortest_s9ffd1c_tail(_t17, (float) Math.sin(_t0 * _t16), (float) Math.sin(alpha * _t16), _t21, 1.0f / _t17, alpha, _t0, _t22, _t23, _t24);
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
    public FloatQuat squad(FloatQuat control0, FloatQuat control1, FloatQuat target, float t) {
        return squad(control0.x(), control0.y(), control0.z(), control0.w(), control1.x(), control1.y(), control1.z(), control1.w(), target.x(), target.y(), target.z(), target.w(), t);
    }

    /** Private tail of {@code squad}; reached only through it. */
    private FloatQuat squad_s7f857976_tail(float _t29, float _t25, float _t30, float _t26, float t, float _t27, float _t12, float _t28, float _t0, float _t7, float control0X, float control1X, float control0W, float control1W, float targetW, float control0Z, float control1Z, float targetZ, float targetX, float control0Y, float control1Y, float targetY, float _t13, float _t17, float _t14) {
        float _t41 = (float) Math.sqrt(_t29 * _t25);
        float _t43 = (float) Math.sqrt(_t30 * _t26);
        float _t45 = 2.0f / _t41;
        float _t46 = 2.0f / _t43;
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
            _t55 = _t45 * (float) Math.sin(t * _t27);
            _t57 = _t45 * (float) Math.sin(_t0 * _t27);
        } else {
            if (_t25 > _t29) {
                _t55 = t;
                _t57 = _t0;
            } else {
                _t55 = _t12;
                _t57 = _t7;
            }
        }
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
            _t56 = _t46 * (float) Math.sin(t * _t28);
            _t58 = _t46 * (float) Math.sin(_t0 * _t28);
        } else {
            if (_t26 > _t30) {
                _t56 = t;
                _t58 = _t0;
            } else {
                _t56 = _t12;
                _t58 = _t7;
            }
        }
        return squad_s7f857976_tail2(control0W, _t57, control1W, _t55, _t58, targetW, _t56, control0Z, control1Z, targetZ, targetX, control0Y, control1Y, targetY, Math.fma(control0X, _t57, control1X * _t55), _t13, _t17, _t14);
    }

    /** Private tail of {@code squad}; reached only through it. */
    private FloatQuat squad_s7f857976_tail2(float control0W, float _t57, float control1W, float _t55, float _t58, float targetW, float _t56, float control0Z, float control1Z, float targetZ, float targetX, float control0Y, float control1Y, float targetY, float _t67, float _t13, float _t17, float _t14) {
        float _t68 = Math.fma(control0W, _t57, control1W * _t55);
        float _t69 = Math.fma(this.w, _t58, targetW * _t56);
        float _t70 = Math.fma(control0Z, _t57, control1Z * _t55);
        float _t71 = Math.fma(this.z, _t58, targetZ * _t56);
        float _t72 = Math.fma(this.x, _t58, targetX * _t56);
        float _t73 = Math.fma(control0Y, _t57, control1Y * _t55);
        float _t74 = Math.fma(this.y, _t58, targetY * _t56);
        float _t75 = _t68 + _t69;
        float _t76 = _t70 + _t71;
        float _t77 = _t67 + _t72;
        float _t78 = _t73 + _t74;
        float _t83 = Math.min(4.0f, Math.fma(_t75, _t75, Math.fma(_t76, _t76, Math.fma(_t77, _t77, _t78 * _t78))));
        float _t85 = 4.0f - _t83;
        float _t90 = _t85 * _t83;
        return squad_s7f857976_tail3((float) Math.sqrt(_t90), 2.0f * (1.0f / (float) Math.sqrt(_t90)), _t13, quatArcAngle(_t83), _t83, _t85, _t17, _t14, _t67, _t72, _t73, _t74, _t70, _t71, _t68, _t69);
    }

    /** Private tail of {@code squad}; reached only through it. */
    private FloatQuat squad_s7f857976_tail3(float _t91, float _t93, float _t13, float _t84, float _t83, float _t85, float _t17, float _t14, float _t67, float _t72, float _t73, float _t74, float _t70, float _t71, float _t68, float _t69) {
        float _t98, _t99;
        if (_t91 > 2.0E-6f) {
            _t98 = _t93 * (float) Math.sin(_t13 * _t84);
            _t99 = _t93 * (float) Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0f - _t17;
                _t99 = _t17;
            }
        }
        float _sfx0 = Math.fma(_t67, _t98, _t72 * _t99);
        float _sfx1 = Math.fma(_t73, _t98, _t74 * _t99);
        float _sfx2 = Math.fma(_t70, _t98, _t71 * _t99);
        float _sfx3 = Math.fma(_t68, _t98, _t69 * _t99);
        return new FloatQuat(_sfx0, _sfx1, _sfx2, _sfx3);
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
    public FloatQuat squad(float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        float _t0 = 1.0f - t;
        float _t1 = t + t;
        float _t3 = control0W + control1W;
        float _t4 = control0Z + control1Z;
        float _t5 = control0X + control1X;
        float _t6 = control0Y + control1Y;
        float _t7 = t < 0.5f ? 1.0f : 0.0f;
        float _t8 = this.w + targetW;
        float _t9 = this.z + targetZ;
        float _t10 = this.x + targetX;
        float _t11 = this.y + targetY;
        float _t13 = _t0 * _t1;
        float _t25 = Math.min(4.0f, Math.fma(_t3, _t3, Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6))));
        float _t26 = Math.min(4.0f, Math.fma(_t8, _t8, Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11))));
        return squad_s7f857976_tail(4.0f - _t25, _t25, 4.0f - _t26, _t26, t, quatArcAngle(_t25), 1.0f - _t7, quatArcAngle(_t26), _t0, _t7, control0X, control1X, control0W, control1W, targetW, control0Z, control1Z, targetZ, targetX, control0Y, control1Y, targetY, _t13, _t13 < 0.5f ? 1.0f : 0.0f, Math.fma(-_t0, _t1, 1.0f));
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
    public FloatQuat mul(FloatQuat other) {
        return mul(other.x(), other.y(), other.z(), other.w());
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
    public FloatQuat mul(float otherX, float otherY, float otherZ, float otherW) {
        return new FloatQuat(Math.fma(otherX, this.w, otherW * this.x) + Math.fma(otherZ, this.y, -(otherY * this.z)), Math.fma(otherX, this.z, otherW * this.y) + Math.fma(otherY, this.w, -(otherZ * this.x)), Math.fma(otherY, this.x, otherZ * this.w) + Math.fma(otherW, this.z, -(otherX * this.y)), Math.fma(otherW, this.w, -(otherX * this.x)) - Math.fma(otherY, this.y, otherZ * this.z));
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
    public FloatQuat preMul(FloatQuat other) {
        return preMul(other.x(), other.y(), other.z(), other.w());
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
    public FloatQuat preMul(float otherX, float otherY, float otherZ, float otherW) {
        return new FloatQuat(Math.fma(otherX, this.w, otherW * this.x) + Math.fma(otherY, this.z, -(otherZ * this.y)), Math.fma(otherY, this.w, otherZ * this.x) + Math.fma(otherW, this.y, -(otherX * this.z)), Math.fma(otherX, this.y, otherW * this.z) + Math.fma(otherZ, this.w, -(otherY * this.x)), Math.fma(otherW, this.w, -(otherX * this.x)) - Math.fma(otherY, this.y, otherZ * this.z));
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
    public FloatQuat addScaled(FloatQuat other, float weight) {
        return addScaled(other.x(), other.y(), other.z(), other.w(), weight);
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
    public FloatQuat addScaled(float otherX, float otherY, float otherZ, float otherW, float weight) {
        return new FloatQuat(Math.fma(weight, otherX, this.x), Math.fma(weight, otherY, this.y), Math.fma(weight, otherZ, this.z), Math.fma(weight, otherW, this.w));
    }


    /**
     * Compute the rotation angle in radians of this quaternion, within {@code [0, 2*PI]}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the rotation angle in radians of this quaternion, within {@code [0, 2*PI]}
     */
    public float angle() {
        return 2.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y))), this.w);
    }


    /**
     * Compute the angle in radians between this quaternion and {@code other}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: this quaternion must have unit length; {@code other} must have unit length.
     *
     * @param other the quaternion to measure the angle to
     * @return the angle in radians between this quaternion and {@code other}
     */
    public float angleTo(FloatQuat other) {
        return angleTo(other.x(), other.y(), other.z(), other.w());
    }


    /**
     * Compute the angle in radians between this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
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
     * @return the angle in radians between this quaternion and ({@code otherX}, {@code otherY},
     *        {@code otherZ}, {@code otherW})
     */
    public float angleTo(float otherX, float otherY, float otherZ, float otherW) {
        float _t9, _t10, _t11, _t12;
        if (-Math.fma(otherW, this.w, Math.fma(otherZ, this.z, Math.fma(otherX, this.x, otherY * this.y))) > 0.0f) {
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
        float _t13 = this.w - _t9;
        float _t14 = this.z - _t10;
        float _t15 = this.x - _t11;
        float _t16 = this.y - _t12;
        float _t17 = this.w + _t9;
        float _t18 = this.z + _t10;
        float _t19 = this.x + _t11;
        float _t20 = this.y + _t12;
        return 4.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16)))), (float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))));
    }


    /**
     * Get the normalized rotation axis of this quaternion (zero when the rotation angle is zero),
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 axis() {
        float _t2 = Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            return new Float3(this.x * _t3, this.y * _t3, this.z * _t3);
        } else {
            return Float3.ZERO;
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
    public FloatQuat calculateW() {
        return new FloatQuat(this.x, this.y, this.z, (float) Math.sqrt(Math.max(0.0f, Math.fma(-this.x, this.x, Math.fma(-this.y, this.y, Math.fma(-this.z, this.z, 1.0f))))));
    }


    /**
     * Conjugate this quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting quaternion
     */
    public FloatQuat conjugate() {
        return new FloatQuat(-this.x, -this.y, -this.z, this.w);
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
    public FloatQuat conjugateBy(FloatQuat q) {
        return conjugateBy(q.x(), q.y(), q.z(), q.w());
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
    public FloatQuat conjugateBy(float qX, float qY, float qZ, float qW) {
        float _t20 = Math.fma(qX, this.y, qW * this.z) + Math.fma(qZ, this.w, -(qY * this.x));
        float _t21 = Math.fma(qY, this.w, qZ * this.x) + Math.fma(qW, this.y, -(qX * this.z));
        float _t22 = Math.fma(qX, this.w, qW * this.x) + Math.fma(qY, this.z, -(qZ * this.y));
        float _t23 = Math.fma(qW, this.w, -(qX * this.x)) - Math.fma(qY, this.y, qZ * this.z);
        return new FloatQuat(Math.fma(qY, _t20, -(qZ * _t21)) + Math.fma(qW, _t22, -(qX * _t23)), Math.fma(qZ, _t22, -(qY * _t23)) + Math.fma(qW, _t21, -(qX * _t20)), Math.fma(qX, _t21, qW * _t20) + Math.fma(-qY, _t22, -(qZ * _t23)), Math.fma(qX, _t22, qW * _t23) - Math.fma(-qZ, _t20, -(qY * _t21)));
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
    public FloatQuat difference(FloatQuat other) {
        return difference(other.x(), other.y(), other.z(), other.w());
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
    public FloatQuat difference(float otherX, float otherY, float otherZ, float otherW) {
        float _t3_inv = 1.0f / Math.fma(this.w, this.w, Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y)));
        float _sp1 = _t3_inv * this.z;
        float _sp0 = this.y * _t3_inv;
        return new FloatQuat((Math.fma(otherX, this.w, -(otherW * this.x)) + Math.fma(otherY, this.z, -(otherZ * this.y))) * _t3_inv, Math.fma(Math.fma(otherY, this.w, otherZ * this.x), _t3_inv, Math.fma(-otherX, _sp1, -(otherW * _sp0))), (Math.fma(otherX, this.y, -(otherW * this.z)) + Math.fma(otherZ, this.w, -(otherY * this.x))) * _t3_inv, Math.fma(Math.fma(otherX, this.x, otherW * this.w), _t3_inv, -Math.fma(-otherZ, _sp1, -(otherY * _sp0))));
    }


    /**
     * Compute the dot product of this quaternion and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this quaternion and {@code other}
     */
    public float dot(FloatQuat other) {
        return dot(other.x(), other.y(), other.z(), other.w());
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
    public float dot(float otherX, float otherY, float otherZ, float otherW) {
        return Math.fma(otherW, this.w, Math.fma(otherZ, this.z, Math.fma(otherX, this.x, otherY * this.y)));
    }


    /**
     * Compute the exponential of this quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting quaternion
     */
    public FloatQuat exp() {
        float _t2 = Math.min((float) Math.exp(this.w), 3.4028235E38f);
        float _t4 = Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y));
        float _t5 = (float) Math.sqrt(_t4);
        float _t7 = (float) Math.sin(_t5);
        float _t10 = _t2 * (_t7 / _t5);
        if (_t4 > 0.0f) {
            return new FloatQuat(this.x * _t10, this.y * _t10, this.z * _t10, (float) Math.cosFromSin(_t7, _t5) * _t2);
        } else {
            return new FloatQuat(0.0f, 0.0f, 0.0f, (float) Math.cosFromSin(_t7, _t5) * _t2);
        }
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the X, Y and Z axes,
     * in that order, returning the result as a value.
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
     * Valid input: this quaternion must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesXYZ() {
        float _t1 = this.y * this.z;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * Math.fma(this.x, this.z, this.y * this.w);
        float _t9 = 2.0f * Math.fma(this.x, this.w, -_t1);
        float _t10 = Math.fma(-2.0f, Math.fma(this.x, this.x, this.y * this.y), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-7f) {
            return new Float3((float) Math.atan2(2.0f * Math.fma(this.x, this.w, _t1), Math.fma(-2.0f, Math.fma(this.x, this.x, _t3), 1.0f)), (float) Math.atan2(_t8, (float) Math.sqrt(_t12)), 0.0f);
        } else {
            return new Float3((float) Math.atan2(_t9, _t10), (float) Math.atan2(_t8, (float) Math.sqrt(_t12)), (float) Math.atan2(2.0f * Math.fma(this.z, this.w, -(this.x * this.y)), Math.fma(-2.0f, Math.fma(this.y, this.y, _t3), 1.0f)));
        }
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the X, Z and Y axes,
     * in that order, returning the result as a value.
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
     * Valid input: this quaternion must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesXZY() {
        float _t0 = this.z * this.z;
        float _t1 = this.y * this.z;
        float _t7 = 2.0f * Math.fma(this.x, this.w, _t1);
        float _t8 = 2.0f * Math.fma(this.z, this.w, -(this.x * this.y));
        float _t9 = Math.fma(-2.0f, Math.fma(this.x, this.x, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            return new Float3((float) Math.atan2(2.0f * Math.fma(this.x, this.w, -_t1), Math.fma(-2.0f, Math.fma(this.x, this.x, this.y * this.y), 1.0f)), 0.0f, (float) Math.atan2(_t8, (float) Math.sqrt(_t11)));
        } else {
            return new Float3((float) Math.atan2(_t7, _t9), (float) Math.atan2(2.0f * Math.fma(this.x, this.z, this.y * this.w), Math.fma(-2.0f, Math.fma(this.y, this.y, _t0), 1.0f)), (float) Math.atan2(_t8, (float) Math.sqrt(_t11)));
        }
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Y, X and Z axes,
     * in that order, returning the result as a value.
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
     * Valid input: this quaternion must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesYXZ() {
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * Math.fma(this.x, this.z, this.y * this.w);
        float _t9 = 2.0f * Math.fma(this.x, this.w, -(this.y * this.z));
        float _t10 = Math.fma(-2.0f, Math.fma(this.x, this.x, this.y * this.y), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            return new Float3((float) Math.atan2(_t9, (float) Math.sqrt(_t12)), (float) Math.atan2(2.0f * Math.fma(this.y, this.w, -(this.x * this.z)), Math.fma(-2.0f, Math.fma(this.y, this.y, _t3), 1.0f)), 0.0f);
        } else {
            return new Float3((float) Math.atan2(_t9, (float) Math.sqrt(_t12)), (float) Math.atan2(_t8, _t10), (float) Math.atan2(2.0f * Math.fma(this.x, this.y, this.z * this.w), Math.fma(-2.0f, Math.fma(this.x, this.x, _t3), 1.0f)));
        }
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Y, Z and X axes,
     * in that order, returning the result as a value.
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
     * Valid input: this quaternion must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesYZX() {
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * Math.fma(this.x, this.y, this.z * this.w);
        float _t8 = 2.0f * Math.fma(this.y, this.w, -(this.x * this.z));
        float _t9 = Math.fma(-2.0f, Math.fma(this.y, this.y, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            return new Float3(0.0f, (float) Math.atan2(2.0f * Math.fma(this.x, this.z, this.y * this.w), Math.fma(-2.0f, Math.fma(this.x, this.x, this.y * this.y), 1.0f)), (float) Math.atan2(_t7, (float) Math.sqrt(_t11)));
        } else {
            return new Float3((float) Math.atan2(2.0f * Math.fma(this.x, this.w, -(this.y * this.z)), Math.fma(-2.0f, Math.fma(this.x, this.x, _t0), 1.0f)), (float) Math.atan2(_t8, _t9), (float) Math.atan2(_t7, (float) Math.sqrt(_t11)));
        }
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Z, X and Y axes,
     * in that order, returning the result as a value.
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
     * Valid input: this quaternion must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesZXY() {
        float _t1 = this.z * this.z;
        float _t7 = 2.0f * Math.fma(this.x, this.w, this.y * this.z);
        float _t8 = 2.0f * Math.fma(this.z, this.w, -(this.x * this.y));
        float _t9 = Math.fma(-2.0f, Math.fma(this.x, this.x, _t1), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            return new Float3((float) Math.atan2(_t7, (float) Math.sqrt(_t11)), 0.0f, (float) Math.atan2(2.0f * Math.fma(this.x, this.y, this.z * this.w), Math.fma(-2.0f, Math.fma(this.y, this.y, _t1), 1.0f)));
        } else {
            return new Float3((float) Math.atan2(_t7, (float) Math.sqrt(_t11)), (float) Math.atan2(2.0f * Math.fma(this.y, this.w, -(this.x * this.z)), Math.fma(-2.0f, Math.fma(this.x, this.x, this.y * this.y), 1.0f)), (float) Math.atan2(_t8, _t9));
        }
    }


    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Z, Y and X axes,
     * in that order, returning the result as a value.
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
     * Valid input: this quaternion must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesZYX() {
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * Math.fma(this.x, this.y, this.z * this.w);
        float _t8 = 2.0f * Math.fma(this.y, this.w, -(this.x * this.z));
        float _t9 = Math.fma(-2.0f, Math.fma(this.y, this.y, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            return new Float3(0.0f, (float) Math.atan2(_t8, (float) Math.sqrt(_t11)), (float) Math.atan2(2.0f * Math.fma(this.z, this.w, -(this.x * this.y)), Math.fma(-2.0f, Math.fma(this.x, this.x, _t0), 1.0f)));
        } else {
            return new Float3((float) Math.atan2(2.0f * Math.fma(this.x, this.w, this.y * this.z), Math.fma(-2.0f, Math.fma(this.x, this.x, this.y * this.y), 1.0f)), (float) Math.atan2(_t8, (float) Math.sqrt(_t11)), (float) Math.atan2(_t7, _t9));
        }
    }


    /**
     * Integrate the given angular velocity over the given time step and apply the resulting
     * rotation to this quaternion, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angularVel the angular velocity, in radians per second, applied in the reference frame
     * @param dt the time step
     * @return the resulting quaternion
     */
    public FloatQuat integrate(Float3 angularVel, float dt) {
        return integrate(angularVel.x(), angularVel.y(), angularVel.z(), dt);
    }


    /**
     * Integrate the given angular velocity over the given time step and apply the resulting
     * rotation to this quaternion, returning the result as a value.
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
    public FloatQuat integrate(float angularVelX, float angularVelY, float angularVelZ, float dt) {
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) Math.sqrt(_t6);
        float _t9 = (float) Math.sin(_t7);
        float _t10 = (float) Math.cosFromSin(_t9, _t7);
        float _t11 = _t9 / _t7;
        float _t15, _t16, _t17;
        if (_t6 > 0.0f) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        return new FloatQuat(Math.fma(this.x, _t10, this.w * _t15) + Math.fma(this.z, _t16, -(this.y * _t17)), Math.fma(this.x, _t17, this.w * _t16) + Math.fma(this.y, _t10, -(this.z * _t15)), Math.fma(this.y, _t15, this.z * _t10) + Math.fma(this.w, _t17, -(this.x * _t16)), Math.fma(this.w, _t10, -(this.x * _t15)) - Math.fma(this.y, _t16, this.z * _t17));
    }


    /**
     * Obtain the direction of {@code -X} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting vector
     */
    public Float3 invNegativeX() {
        float _t9 = 2.0f * Math.fma(this.x, this.z, this.y * this.w);
        float _t10 = 2.0f * Math.fma(this.x, this.y, -(this.z * this.w));
        float _t12 = Math.fma(-this.z, this.z, Math.fma(-this.y, this.y, Math.fma(this.x, this.x, this.w * this.w)));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            return new Float3(-(_t12 * _t16), -(_t10 * _t16), -(_t9 * _t16));
        } else {
            return Float3.ZERO;
        }
    }


    /**
     * Obtain the direction of {@code -Y} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting vector
     */
    public Float3 invNegativeY() {
        float _t9 = 2.0f * Math.fma(this.x, this.y, this.z * this.w);
        float _t10 = 2.0f * Math.fma(this.y, this.z, -(this.x * this.w));
        float _t12 = Math.fma(-this.z, this.z, Math.fma(this.y, this.y, Math.fma(this.w, this.w, -(this.x * this.x))));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            return new Float3(-(_t9 * _t16), -(_t12 * _t16), -(_t10 * _t16));
        } else {
            return Float3.ZERO;
        }
    }


    /**
     * Obtain the direction of {@code -Z} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting vector
     */
    public Float3 invNegativeZ() {
        float _t9 = 2.0f * Math.fma(this.x, this.w, this.y * this.z);
        float _t10 = 2.0f * Math.fma(this.x, this.z, -(this.y * this.w));
        float _t12 = Math.fma(this.z, this.z, Math.fma(-this.y, this.y, Math.fma(this.w, this.w, -(this.x * this.x))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            return new Float3(-(_t10 * _t16), -(_t9 * _t16), -(_t12 * _t16));
        } else {
            return Float3.ZERO;
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
    public Float3 invNormalizedNegativeX() {
        return new Float3(Math.fma(2.0f, Math.fma(this.y, this.y, this.z * this.z), -1.0f), -(2.0f * Math.fma(this.x, this.y, -(this.z * this.w))), -(2.0f * Math.fma(this.x, this.z, this.y * this.w)));
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
    public Float3 invNormalizedNegativeY() {
        return new Float3(-(2.0f * Math.fma(this.x, this.y, this.z * this.w)), Math.fma(2.0f, Math.fma(this.x, this.x, this.z * this.z), -1.0f), -(2.0f * Math.fma(this.y, this.z, -(this.x * this.w))));
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
    public Float3 invNormalizedNegativeZ() {
        return new Float3(-(2.0f * Math.fma(this.x, this.z, -(this.y * this.w))), -(2.0f * Math.fma(this.x, this.w, this.y * this.z)), Math.fma(2.0f, Math.fma(this.x, this.x, this.y * this.y), -1.0f));
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
    public Float3 invNormalizedPositiveX() {
        return new Float3(Math.fma(-2.0f, Math.fma(this.y, this.y, this.z * this.z), 1.0f), 2.0f * Math.fma(this.x, this.y, -(this.z * this.w)), 2.0f * Math.fma(this.x, this.z, this.y * this.w));
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
    public Float3 invNormalizedPositiveY() {
        return new Float3(2.0f * Math.fma(this.x, this.y, this.z * this.w), Math.fma(-2.0f, Math.fma(this.x, this.x, this.z * this.z), 1.0f), 2.0f * Math.fma(this.y, this.z, -(this.x * this.w)));
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
    public Float3 invNormalizedPositiveZ() {
        return new Float3(2.0f * Math.fma(this.x, this.z, -(this.y * this.w)), 2.0f * Math.fma(this.x, this.w, this.y * this.z), Math.fma(-2.0f, Math.fma(this.x, this.x, this.y * this.y), 1.0f));
    }


    /**
     * Obtain the direction of {@code +X} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting vector
     */
    public Float3 invPositiveX() {
        float _t9 = 2.0f * Math.fma(this.x, this.z, this.y * this.w);
        float _t10 = 2.0f * Math.fma(this.x, this.y, -(this.z * this.w));
        float _t12 = Math.fma(-this.z, this.z, Math.fma(-this.y, this.y, Math.fma(this.x, this.x, this.w * this.w)));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            return new Float3(_t12 * _t16, _t10 * _t16, _t9 * _t16);
        } else {
            return Float3.ZERO;
        }
    }


    /**
     * Obtain the direction of {@code +Y} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting vector
     */
    public Float3 invPositiveY() {
        float _t9 = 2.0f * Math.fma(this.x, this.y, this.z * this.w);
        float _t10 = 2.0f * Math.fma(this.y, this.z, -(this.x * this.w));
        float _t12 = Math.fma(-this.z, this.z, Math.fma(this.y, this.y, Math.fma(this.w, this.w, -(this.x * this.x))));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            return new Float3(_t9 * _t16, _t12 * _t16, _t10 * _t16);
        } else {
            return Float3.ZERO;
        }
    }


    /**
     * Obtain the direction of {@code +Z} before the transformation represented by this quaternion
     * is applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting vector
     */
    public Float3 invPositiveZ() {
        float _t9 = 2.0f * Math.fma(this.x, this.w, this.y * this.z);
        float _t10 = 2.0f * Math.fma(this.x, this.z, -(this.y * this.w));
        float _t12 = Math.fma(this.z, this.z, Math.fma(-this.y, this.y, Math.fma(this.w, this.w, -(this.x * this.x))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            return new Float3(_t10 * _t16, _t9 * _t16, _t12 * _t16);
        } else {
            return Float3.ZERO;
        }
    }


    /**
     * Compute the length of this quaternion.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the length of this quaternion
     */
    public float length() {
        return (float) Math.sqrt(Math.fma(this.w, this.w, Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y))));
    }


    /**
     * Compute the squared length of this quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this quaternion
     */
    public float lengthSquared() {
        return Math.fma(this.w, this.w, Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y)));
    }


    /**
     * Compute the natural logarithm of this quaternion, returning the result as a value.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @return the resulting quaternion
     */
    public FloatQuat log() {
        float _t2 = Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y));
        float _t6 = (float) Math.atan2((float) Math.sqrt(_t2), this.w) * (1.0f / (float) Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            return new FloatQuat(this.x * _t6, this.y * _t6, this.z * _t6, (float) Math.log((float) Math.sqrt(Math.fma(this.w, this.w, _t2))));
        } else {
            return new FloatQuat(0.0f, 0.0f, 0.0f, (float) Math.log((float) Math.sqrt(Math.fma(this.w, this.w, _t2))));
        }
    }


    /**
     * Obtain the direction of {@code -X} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting vector
     */
    public Float3 negativeX() {
        float _t9 = 2.0f * Math.fma(this.x, this.y, this.z * this.w);
        float _t10 = 2.0f * Math.fma(this.x, this.z, -(this.y * this.w));
        float _t12 = Math.fma(-this.z, this.z, Math.fma(-this.y, this.y, Math.fma(this.x, this.x, this.w * this.w)));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            return new Float3(-(_t12 * _t16), -(_t9 * _t16), -(_t10 * _t16));
        } else {
            return Float3.ZERO;
        }
    }


    /**
     * Obtain the direction of {@code -Y} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting vector
     */
    public Float3 negativeY() {
        float _t9 = 2.0f * Math.fma(this.x, this.w, this.y * this.z);
        float _t10 = 2.0f * Math.fma(this.x, this.y, -(this.z * this.w));
        float _t12 = Math.fma(-this.z, this.z, Math.fma(this.y, this.y, Math.fma(this.w, this.w, -(this.x * this.x))));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            return new Float3(-(_t10 * _t16), -(_t12 * _t16), -(_t9 * _t16));
        } else {
            return Float3.ZERO;
        }
    }


    /**
     * Obtain the direction of {@code -Z} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting vector
     */
    public Float3 negativeZ() {
        float _t9 = 2.0f * Math.fma(this.x, this.z, this.y * this.w);
        float _t10 = 2.0f * Math.fma(this.y, this.z, -(this.x * this.w));
        float _t12 = Math.fma(this.z, this.z, Math.fma(-this.y, this.y, Math.fma(this.w, this.w, -(this.x * this.x))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            return new Float3(-(_t9 * _t16), -(_t10 * _t16), -(_t12 * _t16));
        } else {
            return Float3.ZERO;
        }
    }


    /**
     * Normalize this quaternion to unit length, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting quaternion
     */
    public FloatQuat normalize() {
        float _t3 = Math.fma(this.w, this.w, Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y)));
        float _t4 = (1.0f / (float) Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            return new FloatQuat(this.x * _t4, this.y * _t4, this.z * _t4, this.w * _t4);
        } else {
            return FloatQuat.ZERO;
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
    public Float3 normalizedNegativeX() {
        return new Float3(Math.fma(2.0f, Math.fma(this.y, this.y, this.z * this.z), -1.0f), -(2.0f * Math.fma(this.x, this.y, this.z * this.w)), -(2.0f * Math.fma(this.x, this.z, -(this.y * this.w))));
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
    public Float3 normalizedNegativeY() {
        return new Float3(-(2.0f * Math.fma(this.x, this.y, -(this.z * this.w))), Math.fma(2.0f, Math.fma(this.x, this.x, this.z * this.z), -1.0f), -(2.0f * Math.fma(this.x, this.w, this.y * this.z)));
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
    public Float3 normalizedNegativeZ() {
        return new Float3(-(2.0f * Math.fma(this.x, this.z, this.y * this.w)), -(2.0f * Math.fma(this.y, this.z, -(this.x * this.w))), Math.fma(2.0f, Math.fma(this.x, this.x, this.y * this.y), -1.0f));
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
    public Float3 normalizedPositiveX() {
        return new Float3(Math.fma(-2.0f, Math.fma(this.y, this.y, this.z * this.z), 1.0f), 2.0f * Math.fma(this.x, this.y, this.z * this.w), 2.0f * Math.fma(this.x, this.z, -(this.y * this.w)));
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
    public Float3 normalizedPositiveY() {
        return new Float3(2.0f * Math.fma(this.x, this.y, -(this.z * this.w)), Math.fma(-2.0f, Math.fma(this.x, this.x, this.z * this.z), 1.0f), 2.0f * Math.fma(this.x, this.w, this.y * this.z));
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
    public Float3 normalizedPositiveZ() {
        return new Float3(2.0f * Math.fma(this.x, this.z, this.y * this.w), 2.0f * Math.fma(this.y, this.z, -(this.x * this.w)), Math.fma(-2.0f, Math.fma(this.x, this.x, this.y * this.y), 1.0f));
    }


    /**
     * Obtain the direction of {@code +X} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting vector
     */
    public Float3 positiveX() {
        float _t9 = 2.0f * Math.fma(this.x, this.y, this.z * this.w);
        float _t10 = 2.0f * Math.fma(this.x, this.z, -(this.y * this.w));
        float _t12 = Math.fma(-this.z, this.z, Math.fma(-this.y, this.y, Math.fma(this.x, this.x, this.w * this.w)));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            return new Float3(_t12 * _t16, _t9 * _t16, _t10 * _t16);
        } else {
            return Float3.ZERO;
        }
    }


    /**
     * Obtain the direction of {@code +Y} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting vector
     */
    public Float3 positiveY() {
        float _t9 = 2.0f * Math.fma(this.x, this.w, this.y * this.z);
        float _t10 = 2.0f * Math.fma(this.x, this.y, -(this.z * this.w));
        float _t12 = Math.fma(-this.z, this.z, Math.fma(this.y, this.y, Math.fma(this.w, this.w, -(this.x * this.x))));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            return new Float3(_t10 * _t16, _t12 * _t16, _t9 * _t16);
        } else {
            return Float3.ZERO;
        }
    }


    /**
     * Obtain the direction of {@code +Z} after the transformation represented by this quaternion is
     * applied, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting vector
     */
    public Float3 positiveZ() {
        float _t9 = 2.0f * Math.fma(this.x, this.z, this.y * this.w);
        float _t10 = 2.0f * Math.fma(this.y, this.z, -(this.x * this.w));
        float _t12 = Math.fma(this.z, this.z, Math.fma(-this.y, this.y, Math.fma(this.w, this.w, -(this.x * this.x))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            return new Float3(_t9 * _t16, _t10 * _t16, _t12 * _t16);
        } else {
            return Float3.ZERO;
        }
    }


    /**
     * Raise this quaternion to the power of {@code t}, i.e. compute {@code exp(t * log(this))},
     * returning the result as a value.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the exponent
     * @return the resulting quaternion
     */
    public FloatQuat pow(float t) {
        float _t2 = Math.fma(this.z, this.z, Math.fma(this.x, this.x, this.y * this.y));
        float _t12 = Math.min((float) Math.exp(t == 0.0f ? 0.0f : t * (float) Math.log((float) Math.sqrt(Math.fma(this.w, this.w, _t2)))), 3.4028235E38f);
        float _t13 = (float) Math.atan2((float) Math.sqrt(_t2), this.w) * (1.0f / (float) Math.sqrt(_t2));
        float _t20, _t21, _t22;
        if (_t2 > 0.0f) {
            _t20 = t * this.z * _t13;
            _t21 = t * this.x * _t13;
            _t22 = t * this.y * _t13;
        } else {
            _t20 = t * 0.0f;
            _t21 = t * 0.0f;
            _t22 = t * 0.0f;
        }
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (float) Math.sqrt(_t25);
        float _t28 = (float) Math.sin(_t26);
        float _t31 = _t12 * (_t28 / _t26);
        if (_t25 > 0.0f) {
            return new FloatQuat(_t21 * _t31, _t22 * _t31, _t20 * _t31, (float) Math.cosFromSin(_t28, _t26) * _t12);
        } else {
            return new FloatQuat(0.0f, 0.0f, 0.0f, (float) Math.cosFromSin(_t28, _t26) * _t12);
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
    public FloatQuat premul(FloatQuat other) {
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
    public FloatQuat premul(float otherX, float otherY, float otherZ, float otherW) {
        return preMul(otherX, otherY, otherZ, otherW);
    }


    /**
     * Rotate this quaternion towards {@code target}, by at most the given maximum angle, returning
     * the result as a value.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: this quaternion must have unit length; {@code target} must have unit length.
     *
     * @param target the target rotation
     * @param step the maximum rotation angle in radians
     * @return the resulting quaternion
     */
    public FloatQuat rotateTowards(FloatQuat target, float step) {
        return rotateTowards(target.x(), target.y(), target.z(), target.w(), step);
    }

    /** Private tail of {@code rotateTowards}; reached only through it. */
    private FloatQuat rotateTowards_s6b684b4e_tail(float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float step, float _t11, float _t12, float _t13, float _t12_inv, float _t14, float _t15, float _t16) {
        float _t36 = 4.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        float _t39 = _t36 > 0.0f ? Math.min(1.0f, step / _t36) : 0.0f;
        float _t40 = 1.0f - _t39;
        return rotateTowards_s6b684b4e_tail2(_t12, (float) Math.sin(_t40 * _t11), (float) Math.sin(_t11 * _t39), _t13, _t12_inv, _t40, _t39, _t14, _t15, _t16);
    }

    /** Private tail of {@code rotateTowards}; reached only through it. */
    private FloatQuat rotateTowards_s6b684b4e_tail2(float _t12, float _t44, float _t42, float _t13, float _t12_inv, float _t40, float _t39, float _t14, float _t15, float _t16) {
        float _t65, _t66, _t67, _t68;
        if (_t12 > 0.0f) {
            _t65 = Math.fma(this.w, _t44, _t42 * _t13) * _t12_inv;
            _t66 = Math.fma(this.z, _t44, _t42 * _t14) * _t12_inv;
            _t67 = Math.fma(this.x, _t44, _t42 * _t15) * _t12_inv;
            _t68 = Math.fma(this.y, _t44, _t42 * _t16) * _t12_inv;
        } else {
            _t65 = Math.fma(this.w, _t40, _t13 * _t39);
            _t66 = Math.fma(this.z, _t40, _t14 * _t39);
            _t67 = Math.fma(this.x, _t40, _t15 * _t39);
            _t68 = Math.fma(this.y, _t40, _t16 * _t39);
        }
        float _t72 = Math.fma(_t65, _t65, Math.fma(_t66, _t66, Math.fma(_t67, _t67, _t68 * _t68)));
        return slerpShortest_s9ffd1c_tail2(_t72, (1.0f / (float) Math.sqrt(_t72)), _t67, _t68, _t66, _t65);
    }


    /**
     * Rotate this quaternion towards ({@code targetX}, {@code targetY}, {@code targetZ},
     * {@code targetW}), by at most the given maximum angle, returning the result as a value.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
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
    public FloatQuat rotateTowards(float targetX, float targetY, float targetZ, float targetW, float step) {
        float _t7 = Math.fma(this.w, targetW, Math.fma(this.z, targetZ, Math.fma(this.x, targetX, this.y * targetY)));
        float _t11 = (float) Math.acos(Math.min(1.0f, Math.abs(_t7)));
        float _t12 = (float) Math.sin(_t11);
        float _t13, _t14, _t15, _t16;
        if (-_t7 > 0.0f) {
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
        return rotateTowards_s6b684b4e_tail(this.w - _t13, this.z - _t14, this.x - _t15, this.y - _t16, this.w + _t13, this.z + _t14, this.x + _t15, this.y + _t16, step, _t11, _t12, _t13, 1.0f / _t12, _t14, _t15, _t16);
    }


    /**
     * Apply a rotation transformation that makes {@code +z} point along {@code dir} to this
     * quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
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
     * @return the resulting quaternion
     */
    public FloatQuat lookAlong(Float3 dir, Float3 up) {
        return lookAlong(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /** Private tail of {@code lookAlong}; reached only through it. */
    private FloatQuat lookAlong_s524747ee_tail(float dirZ, float _t16, float dirX, float dirY, float _t37, float _t45, float _t38, float _t39, float _t1) {
        float _t17 = dirZ * _t16;
        float _t18 = dirX * _t16;
        float _t19 = dirY * _t16;
        float _t20 = -_t18;
        float _t46 = _t37 * _t45;
        float _t47 = _t38 * _t45;
        float _t48 = _t39 * _t45;
        float _t63 = Math.fma(_t17, _t46, -(_t18 * _t47));
        float _t65 = Math.fma(_t18, _t48, -(_t19 * _t46));
        float _t66 = Math.fma(_t19, _t47, -(_t17 * _t48));
        return lookAlong_s524747ee_tail2(_t37, _t45, -_t17, _t46, _t18, _t47, _t1, _t16, dirZ, Math.fma(-_t37, _t45, 1.0f), Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t37, _t45, Math.fma(dirZ, _t16, 1.0f)))), _t17, _t20, Math.fma(dirZ, _t16, Math.fma(_t37, _t45, _t63)), Math.fma(-dirY, _t16, _t65), Math.max(_t63, _t17), _t63, Math.fma(_t39, _t45, _t66), Math.fma(dirX, _t16, _t47), Math.fma(_t39, _t45, -_t66), Math.fma(dirY, _t16, _t65), Math.fma(dirX, _t16, -_t47));
    }

    /** Private tail of {@code lookAlong}; reached only through it. */
    private FloatQuat lookAlong_s524747ee_tail2(float _t37, float _t45, float _t22, float _t46, float _t18, float _t47, float _t1, float _t16, float dirZ, float _t50, float _t78, float _t17, float _t20, float _t77, float _t71, float _t70, float _t63, float _t74, float _t51, float _t75, float _t69, float _t55) {
        float _t80 = Math.fma(_t37, _t45, Math.fma(_t22, _t46, Math.fma(_t18, _t47, Math.fma(_t1, _t16, 1.0f))));
        float _t81 = Math.fma(dirZ, _t16, Math.fma(_t22, _t46, Math.fma(_t18, _t47, _t50)));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t78));
        float _t84 = Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t1, _t16, _t50)));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t81));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t84));
        return lookAlong_s524747ee_tail3(_t77, _sp1, _t75, _t46, _t70, 0.5f * (1.0f / (float) Math.sqrt(_t80)), _t51, _t63, _t17, _sp2, _t69, _t81, _t55, _t74, _t84, _sp3, _t78, _t71, _t77 > 0.0f ? _sp1 * _t71 : _t46 > _t70 ? 0.5f * (float) Math.sqrt(_t80) : _t63 > _t17 ? _sp2 * _t74 : _sp3 * _t51);
    }

    /**
     * Private tail of {@code lookAlong}. Shared by 2 identical private paths of {@code lookAlong};
     * reached only through it.
     */
    private FloatQuat lookAlong_s524747ee_tail3(float _t77, float _sp1, float _t75, float _t46, float _t70, float _sp4, float _t51, float _t63, float _t17, float _sp2, float _t69, float _t81, float _t55, float _t74, float _t84, float _sp3, float _t78, float _t71, float _t126) {
        float _t127, _t128, _t129;
        if (_t77 > 0.0f) {
            _t127 = _sp1 * _t75;
            _t128 = _sp1 * _t55;
            _t129 = 0.5f * (float) Math.sqrt(_t78);
        } else {
            if (_t46 > _t70) {
                _t127 = _sp4 * _t51;
                _t128 = _sp4 * _t74;
                _t129 = _sp4 * _t71;
            } else {
                if (_t63 > _t17) {
                    _t127 = _sp2 * _t69;
                    _t128 = 0.5f * (float) Math.sqrt(_t84);
                    _t129 = _sp2 * _t55;
                } else {
                    _t127 = 0.5f * (float) Math.sqrt(_t81);
                    _t128 = _sp3 * _t69;
                    _t129 = _sp3 * _t75;
                }
            }
        }
        float _sfx0 = Math.fma(this.x, _t129, this.w * _t126) + Math.fma(this.y, _t127, -(this.z * _t128));
        return lookAlong_s524747ee_tail4(_t129, _t126, _t128, _t127, _sfx0);
    }

    /**
     * Private tail of {@code lookAlong}. Shared by 2 identical private paths of {@code lookAlong};
     * reached only through it.
     */
    private FloatQuat lookAlong_s524747ee_tail4(float _t129, float _t126, float _t128, float _t127, float _sfx0) {
        float _sfx1 = Math.fma(this.y, _t129, this.z * _t126) + Math.fma(this.w, _t128, -(this.x * _t127));
        float _sfx2 = Math.fma(this.x, _t128, this.w * _t127) + Math.fma(this.z, _t129, -(this.y * _t126));
        float _sfx3 = Math.fma(this.w, _t129, -(this.x * _t126)) - Math.fma(this.y, _t128, this.z * _t127);
        return new FloatQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Apply a rotation transformation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}) to this quaternion, returning the result as a value.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
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
     * @return the resulting quaternion
     */
    public FloatQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t8 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t8;
        float _t16 = _t8;
        if (!(_t16 > 1.1754944E-38f && _t16 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        float _t28 = Math.fma(-dirY, _sp0, upY);
        float _t29 = Math.fma(-dirZ, _sp0, upZ);
        float _t30 = Math.fma(-dirX, _sp0, upX);
        float _t37 = Math.fma(dirZ, _t28, -(dirY * _t29));
        float _t38 = Math.fma(dirY, _t30, -(dirX * _t28));
        float _t39 = Math.fma(dirX, _t29, -(dirZ * _t30));
        float _t45 = Math.fma(_t38, _t38, Math.fma(_t39, _t39, _t37 * _t37));
        if (!(_t45 > Math.fma(_t8, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _t45 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_s524747ee_tail(dirZ, (1.0f / (float) Math.sqrt(_t16)), dirX, dirY, _t37, (1.0f / (float) Math.sqrt(_t45)), _t38, _t39, -dirZ);
    }


    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatQuat lookAlong_degenerate(Float3 dir, Float3 up) {
        return lookAlong_degenerate(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /** Private tail of {@code lookAlong_degenerate}; reached only through it. */
    private FloatQuat lookAlong_degenerate_s524747ee_tail(float _t27, float _t28, float _t26, float _t29, float _t21, float _t22, float _t23, float _t25, float _t24, float _t31, float _t32) {
        float _t36, _t37, _t41;
        if (_t27 > _t28) {
            _t36 = 0.0f;
            _t37 = _t29;
            _t41 = _t25;
        } else {
            _t36 = _t26;
            _t37 = 0.0f;
            _t41 = -_t24;
        }
        float _t40 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f;
        float _t43 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        float _t44 = Math.fma(_t43, _t25, _t22);
        float _t45 = Math.fma(_t43, _t26, _t23);
        float _t46 = Math.fma(_t43, _t24, _t21);
        float _t55 = Math.fma(_t44, _t26, -(_t45 * _t25));
        float _t56 = Math.fma(_t46, _t25, -(_t44 * _t24));
        float _t57 = Math.fma(_t45, _t24, -(_t46 * _t26));
        float _t61 = Math.fma(_t55, _t55, Math.fma(_t56, _t56, _t57 * _t57));
        float _t62, _t63;
        if (_t61 <= _t40) {
            _t62 = _t36;
            _t63 = _t37;
        } else {
            _t62 = _t55;
            _t63 = _t57;
        }
        return lookAlong_degenerate_s524747ee_tail2(_t61, _t40, _t41, _t56, _t36, _t37, _t62, _t63, _t25, _t24, _t26, _t29, _t31, _t32);
    }

    /** Private tail of {@code lookAlong_degenerate}; reached only through it. */
    private FloatQuat lookAlong_degenerate_s524747ee_tail2(float _t61, float _t40, float _t41, float _t56, float _t36, float _t37, float _t62, float _t63, float _t25, float _t24, float _t26, float _t29, float _t31, float _t32) {
        float _t64, _t66;
        if (_t61 <= _t40) {
            _t64 = _t41;
            _t66 = (1.0f / (float) Math.sqrt(Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t41 * _t41))));
        } else {
            _t64 = _t56;
            _t66 = (1.0f / (float) Math.sqrt(_t61));
        }
        float _t67 = -_t66;
        float _t68 = _t66 * _t62;
        float _t69 = _t66 * _t63;
        float _t70 = -_t68;
        float _t71 = -_t69;
        float _t72 = _t66 * _t64;
        float _t87 = Math.fma(_t69, _t24, -(_t68 * _t25));
        return lookAlong_degenerate_s524747ee_tail3(_t66, _t63, _t71, _t24, _t68, _t25, _t32, _t69, _t70, _t67, _t31, Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t31))), _t64, Math.fma(_t68, _t26, -(_t72 * _t24)), Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t24))), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t29)), Math.max(_t87, _t24), _t87, Math.fma(_t66, _t62, _t25), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t26)), Math.fma(_t67, _t62, _t25));
    }

    /** Private tail of {@code lookAlong_degenerate}; reached only through it. */
    private FloatQuat lookAlong_degenerate_s524747ee_tail3(float _t66, float _t63, float _t71, float _t24, float _t68, float _t25, float _t32, float _t69, float _t70, float _t67, float _t31, float _t98, float _t64, float _t91, float _t97, float _t96, float _t93, float _t87, float _t73, float _t95, float _t76) {
        float _t99 = Math.fma(_t66, _t63, Math.fma(_t71, _t24, Math.fma(_t68, _t25, _t32)));
        float _t102 = Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t67, _t63, _t32)));
        float _t103 = Math.fma(_t71, _t24, Math.fma(_t68, _t25, Math.fma(_t67, _t63, _t31)));
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t98));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t102));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t103));
        float _t114 = Math.fma(_t66, _t64, _t91);
        return lookAlong_s524747ee_tail3(_t97, _sp0, Math.fma(_t66, _t64, -_t91), _t69, _t93, 0.5f * (1.0f / (float) Math.sqrt(_t99)), _t73, _t87, _t24, _sp1, _t95, _t103, _t76, _t114, _t102, _sp2, _t98, _t96, _t97 > 0.0f ? _sp0 * _t96 : _t69 > _t93 ? 0.5f * (float) Math.sqrt(_t99) : _t87 > _t24 ? _sp1 * _t114 : _sp2 * _t73);
    }


    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatQuat lookAlong_degenerate(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        float _t17 = (1.0f / (float) Math.sqrt(_t16));
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
        return lookAlong_degenerate_s524747ee_tail(Math.abs(_t25), Math.abs(_t24), _t26, -_t26, _t21, _t22, _t23, _t25, _t24, 1.0f + _t24, 1.0f - _t24);
    }


    /**
     * Create a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return the resulting quaternion
     */
    public static FloatQuat makeRotationAxis(float angle, Float3 axis) {
        return makeRotationAxis(angle, axis.x(), axis.y(), axis.z());
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
    public static FloatQuat makeRotationAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        return new FloatQuat(axisX * _t1, axisY * _t1, axisZ * _t1, (float) Math.cosFromSin(_t1, _t0));
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
     * @return the resulting quaternion
     */
    public static FloatQuat makeRotationLookAlong(Float3 dir, Float3 up) {
        return makeRotationLookAlong(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /** Private tail of {@code makeRotationLookAlong}; reached only through it. */
    private static FloatQuat makeRotationLookAlong_s524747ee_tail(float dirZ, float _t16, float dirX, float dirY, float _t37, float _t45, float _t38, float _t39, float _t1) {
        float _t17 = dirZ * _t16;
        float _t18 = dirX * _t16;
        float _t19 = dirY * _t16;
        float _t20 = -_t18;
        float _t46 = _t37 * _t45;
        float _t47 = _t38 * _t45;
        float _t48 = _t39 * _t45;
        float _t63 = Math.fma(_t17, _t46, -(_t18 * _t47));
        float _t64 = Math.fma(_t18, _t48, -(_t19 * _t46));
        float _t66 = Math.fma(_t19, _t47, -(_t17 * _t48));
        float _t78 = Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t37, _t45, Math.fma(dirZ, _t16, 1.0f))));
        return makeRotationLookAlong_s524747ee_tail2(_t37, _t45, -_t17, _t46, _t18, _t47, _t1, _t16, dirZ, Math.fma(-_t37, _t45, 1.0f), _t17, _t20, Math.fma(dirZ, _t16, Math.fma(_t37, _t45, _t63)), 0.5f * (1.0f / (float) Math.sqrt(_t78)), Math.fma(-dirY, _t16, _t64), Math.max(_t63, _t17), _t63, Math.fma(_t39, _t45, _t66), Math.fma(dirX, _t16, _t47), Math.fma(dirX, _t16, -_t47), Math.fma(dirY, _t16, _t64), Math.fma(_t39, _t45, -_t66), _t78);
    }

    /** Private tail of {@code makeRotationLookAlong}; reached only through it. */
    private static FloatQuat makeRotationLookAlong_s524747ee_tail2(float _t37, float _t45, float _t22, float _t46, float _t18, float _t47, float _t1, float _t16, float dirZ, float _t50, float _t17, float _t20, float _t77, float _sp1, float _t70, float _t71, float _t63, float _t74, float _t51, float _t56, float _t69, float _t75, float _t78) {
        float _t80 = Math.fma(_t37, _t45, Math.fma(_t22, _t46, Math.fma(_t18, _t47, Math.fma(_t1, _t16, 1.0f))));
        float _t81 = Math.fma(dirZ, _t16, Math.fma(_t22, _t46, Math.fma(_t18, _t47, _t50)));
        float _t82 = Math.fma(_t17, _t46, Math.fma(_t20, _t47, Math.fma(_t1, _t16, _t50)));
        return makeRotationLookAlong_s524747ee_tail3(_t77, _sp1, _t70, _t46, _t71, _t80, _t63, _t17, 0.5f * (1.0f / (float) Math.sqrt(_t82)), _t74, 0.5f * (1.0f / (float) Math.sqrt(_t81)), _t51, _t56, 0.5f * (1.0f / (float) Math.sqrt(_t80)), _t82, _t69, _t75, _t81, _t78);
    }

    /**
     * Private tail of {@code makeRotationLookAlong}. Shared by 2 identical private paths of
     * {@code makeRotationLookAlong}; reached only through it.
     */
    private static FloatQuat makeRotationLookAlong_s524747ee_tail3(float _t77, float _sp1, float _t70, float _t46, float _t71, float _t80, float _t63, float _t17, float _sp2, float _t74, float _sp3, float _t51, float _t56, float _sp4, float _t82, float _t69, float _t75, float _t81, float _t78) {
        float _sfx0, _sfx1, _sfx2, _sfx3;
        if (_t77 > 0.0f) {
            _sfx0 = _sp1 * _t70;
            _sfx1 = _sp1 * _t56;
            _sfx2 = _sp1 * _t75;
            _sfx3 = 0.5f * (float) Math.sqrt(_t78);
        } else {
            if (_t46 > _t71) {
                _sfx0 = 0.5f * (float) Math.sqrt(_t80);
                _sfx1 = _sp4 * _t74;
                _sfx2 = _sp4 * _t51;
                _sfx3 = _sp4 * _t70;
            } else {
                if (_t63 > _t17) {
                    _sfx0 = _sp2 * _t74;
                    _sfx1 = 0.5f * (float) Math.sqrt(_t82);
                    _sfx2 = _sp2 * _t69;
                    _sfx3 = _sp2 * _t56;
                } else {
                    _sfx0 = _sp3 * _t51;
                    _sfx1 = _sp3 * _t69;
                    _sfx2 = 0.5f * (float) Math.sqrt(_t81);
                    _sfx3 = _sp3 * _t75;
                }
            }
        }
        return new FloatQuat(_sfx0, _sfx1, _sfx2, _sfx3);
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
     * @return the resulting quaternion
     */
    public static FloatQuat makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t8 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t8;
        float _t16 = _t8;
        if (!(_t16 > 1.1754944E-38f && _t16 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        float _t28 = Math.fma(-dirY, _sp0, upY);
        float _t29 = Math.fma(-dirZ, _sp0, upZ);
        float _t30 = Math.fma(-dirX, _sp0, upX);
        float _t37 = Math.fma(dirZ, _t28, -(dirY * _t29));
        float _t38 = Math.fma(dirY, _t30, -(dirX * _t28));
        float _t39 = Math.fma(dirX, _t29, -(dirZ * _t30));
        float _t45 = Math.fma(_t38, _t38, Math.fma(_t39, _t39, _t37 * _t37));
        if (!(_t45 > Math.fma(_t8, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _t45 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_s524747ee_tail(dirZ, (1.0f / (float) Math.sqrt(_t16)), dirX, dirY, _t37, (1.0f / (float) Math.sqrt(_t45)), _t38, _t39, -dirZ);
    }


    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static FloatQuat makeRotationLookAlong_degenerate(Float3 dir, Float3 up) {
        return makeRotationLookAlong_degenerate(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /** Private tail of {@code makeRotationLookAlong_degenerate}; reached only through it. */
    private static FloatQuat makeRotationLookAlong_degenerate_s524747ee_tail(float _t27, float _t28, float _t26, float _t29, float _t21, float _t22, float _t23, float _t25, float _t24, float _t31, float _t32) {
        float _t36, _t37, _t41;
        if (_t27 > _t28) {
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
        float _t55 = Math.fma(_t44, _t26, -(_t45 * _t25));
        float _t56 = Math.fma(_t46, _t25, -(_t44 * _t24));
        float _t57 = Math.fma(_t45, _t24, -(_t46 * _t26));
        return makeRotationLookAlong_degenerate_s524747ee_tail2(Math.fma(_t55, _t55, Math.fma(_t56, _t56, _t57 * _t57)), Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f, _t36, _t55, _t37, _t57, _t41, _t56, _t25, _t24, _t26, _t29, _t31, _t32);
    }

    /** Private tail of {@code makeRotationLookAlong_degenerate}; reached only through it. */
    private static FloatQuat makeRotationLookAlong_degenerate_s524747ee_tail2(float _t61, float _t40, float _t36, float _t55, float _t37, float _t57, float _t41, float _t56, float _t25, float _t24, float _t26, float _t29, float _t31, float _t32) {
        float _t62, _t63, _t64, _t66;
        if (_t61 <= _t40) {
            _t62 = _t36;
            _t63 = _t37;
            _t64 = _t41;
            _t66 = (1.0f / (float) Math.sqrt(Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t41 * _t41))));
        } else {
            _t62 = _t55;
            _t63 = _t57;
            _t64 = _t56;
            _t66 = (1.0f / (float) Math.sqrt(_t61));
        }
        float _t67 = -_t66;
        float _t68 = _t66 * _t62;
        float _t69 = _t66 * _t63;
        float _t70 = -_t68;
        float _t71 = -_t69;
        float _t72 = _t66 * _t64;
        float _t87 = Math.fma(_t69, _t24, -(_t68 * _t25));
        return makeRotationLookAlong_degenerate_s524747ee_tail3(_t69, _t24, _t70, _t25, _t66, _t63, _t31, _t71, _t68, _t32, _t67, _t64, Math.fma(_t68, _t26, -(_t72 * _t24)), Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t24))), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t29)), Math.max(_t87, _t24), _t87, Math.fma(_t66, _t62, _t25), Math.fma(_t67, _t62, _t25), Math.fma(_t72, _t25, Math.fma(_t71, _t26, _t26)));
    }

    /** Private tail of {@code makeRotationLookAlong_degenerate}; reached only through it. */
    private static FloatQuat makeRotationLookAlong_degenerate_s524747ee_tail3(float _t69, float _t24, float _t70, float _t25, float _t66, float _t63, float _t31, float _t71, float _t68, float _t32, float _t67, float _t64, float _t91, float _t97, float _t96, float _t93, float _t87, float _t73, float _t76, float _t95) {
        float _t98 = Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t66, _t63, _t31)));
        float _t99 = Math.fma(_t66, _t63, Math.fma(_t71, _t24, Math.fma(_t68, _t25, _t32)));
        float _t101 = Math.fma(_t69, _t24, Math.fma(_t70, _t25, Math.fma(_t67, _t63, _t32)));
        float _t102 = Math.fma(_t71, _t24, Math.fma(_t68, _t25, Math.fma(_t67, _t63, _t31)));
        return makeRotationLookAlong_s524747ee_tail3(_t97, 0.5f * (1.0f / (float) Math.sqrt(_t98)), _t96, _t69, _t93, _t99, _t87, _t24, 0.5f * (1.0f / (float) Math.sqrt(_t101)), Math.fma(_t66, _t64, _t91), 0.5f * (1.0f / (float) Math.sqrt(_t102)), _t73, _t76, 0.5f * (1.0f / (float) Math.sqrt(_t99)), _t101, _t95, Math.fma(_t66, _t64, -_t91), _t102, _t98);
    }


    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static FloatQuat makeRotationLookAlong_degenerate(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        float _t17 = (1.0f / (float) Math.sqrt(_t16));
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
        return makeRotationLookAlong_degenerate_s524747ee_tail(Math.abs(_t25), Math.abs(_t24), _t26, -_t26, _t21, _t22, _t23, _t25, _t24, 1.0f + _t24, 1.0f - _t24);
    }


    /**
     * Create the rotation that rotates {@code fromDir} onto {@code toDir} (for opposite vectors an
     * arbitrary perpendicular rotation axis is chosen).
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length.
     *
     * @param fromDir the direction to rotate from (must be a unit vector)
     * @param toDir the direction to rotate onto (must be a unit vector)
     * @return the resulting quaternion
     */
    public static FloatQuat makeRotationTo(Float3 fromDir, Float3 toDir) {
        return makeRotationTo(fromDir.x(), fromDir.y(), fromDir.z(), toDir.x(), toDir.y(), toDir.z());
    }

    /** Private tail of {@code makeRotationTo}; reached only through it. */
    private static FloatQuat makeRotationTo_sb47fb53_tail(float _t29, float _t23, float _t16, float _t15, float _t17, float _t24, float _t13, float _t19, float _t18) {
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
        float _t32 = (1.0f / (float) Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17)))));
        if (_t24 > 6.0E-8f) {
            return new FloatQuat(_t15 * _t32, _t17 * _t32, _t16 * _t32, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                return new FloatQuat(_t30 * _t13, _t30 * _t19, _t30 * _t18, 0.0f);
            } else {
                return FloatQuat.ZERO;
            }
        }
    }


    /**
     * Create the rotation that rotates ({@code fromDirX}, {@code fromDirY}, {@code fromDirZ}) onto
     * ({@code toDirX}, {@code toDirY}, {@code toDirZ}) (for opposite vectors an arbitrary
     * perpendicular rotation axis is chosen).
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
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
    public static FloatQuat makeRotationTo(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (Math.abs(fromDirZ) < Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        return makeRotationTo_sb47fb53_tail(Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19)), _t23, Math.fma(fromDirX, toDirY, -(fromDirY * toDirX)), Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY)), Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ)), 0.5f * _t23, _t13, _t19, _t18);
    }


    /**
     * Create a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting quaternion
     */
    public static FloatQuat makeRotationX(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        return new FloatQuat(_t1, 0.0f, 0.0f, (float) Math.cosFromSin(_t1, _t0));
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
     * @return the resulting quaternion
     */
    public static FloatQuat makeRotationXYZ(float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t4, _t1);
        float _t7 = (float) Math.cosFromSin(_t5, _t2);
        float _t8 = (float) Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        return new FloatQuat(Math.fma(_t10, _t7, _t11 * _t5), Math.fma(_t11, _t7, -(_t10 * _t5)), Math.fma(_t9, _t7, _t12 * _t5), Math.fma(_t12, _t7, -(_t9 * _t5)));
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
     * @return the resulting quaternion
     */
    public static FloatQuat makeRotationXZY(float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t4, _t1);
        float _t7 = (float) Math.cosFromSin(_t5, _t2);
        float _t8 = (float) Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        return new FloatQuat(Math.fma(_t10, _t7, -(_t11 * _t5)), Math.fma(_t12, _t5, -(_t9 * _t7)), Math.fma(_t10, _t5, _t11 * _t7), Math.fma(_t9, _t5, _t12 * _t7));
    }


    /**
     * Create a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting quaternion
     */
    public static FloatQuat makeRotationY(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        return new FloatQuat(0.0f, _t1, 0.0f, (float) Math.cosFromSin(_t1, _t0));
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
     * @return the resulting quaternion
     */
    public static FloatQuat makeRotationYXZ(float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t4, _t1);
        float _t7 = (float) Math.cosFromSin(_t5, _t2);
        float _t8 = (float) Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        return new FloatQuat(Math.fma(_t10, _t7, _t11 * _t5), Math.fma(_t11, _t7, -(_t10 * _t5)), Math.fma(_t12, _t5, -(_t9 * _t7)), Math.fma(_t9, _t5, _t12 * _t7));
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
     * @return the resulting quaternion
     */
    public static FloatQuat makeRotationYZX(float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t5, _t2);
        float _t7 = (float) Math.cosFromSin(_t3, _t0);
        float _t8 = (float) Math.cosFromSin(_t4, _t1);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t8;
        float _t11 = _t4 * _t7;
        float _t12 = _t7 * _t8;
        return new FloatQuat(Math.fma(_t9, _t6, _t12 * _t5), Math.fma(_t10, _t6, _t11 * _t5), Math.fma(_t11, _t6, -(_t10 * _t5)), Math.fma(_t12, _t6, -(_t9 * _t5)));
    }


    /**
     * Create a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting quaternion
     */
    public static FloatQuat makeRotationZ(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        return new FloatQuat(0.0f, 0.0f, _t1, (float) Math.cosFromSin(_t1, _t0));
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
     * @return the resulting quaternion
     */
    public static FloatQuat makeRotationZXY(float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t4, _t1);
        float _t7 = (float) Math.cosFromSin(_t5, _t2);
        float _t8 = (float) Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        return new FloatQuat(Math.fma(_t10, _t7, -(_t11 * _t5)), Math.fma(_t9, _t7, _t12 * _t5), Math.fma(_t10, _t5, _t11 * _t7), Math.fma(_t12, _t7, -(_t9 * _t5)));
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
     * @return the resulting quaternion
     */
    public static FloatQuat makeRotationZYX(float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        return new FloatQuat(Math.fma(_t12, _t5, -(_t9 * _t8)), Math.fma(_t10, _t8, _t11 * _t5), Math.fma(_t11, _t8, -(_t10 * _t5)), Math.fma(_t9, _t5, _t12 * _t8));
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
    public FloatQuat preRotateX(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        return new FloatQuat(Math.fma(this.x, _t2, this.w * _t1), Math.fma(this.y, _t2, -(this.z * _t1)), Math.fma(this.y, _t1, this.z * _t2), Math.fma(this.w, _t2, -(this.x * _t1)));
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
    public FloatQuat preRotateY(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        return new FloatQuat(Math.fma(this.x, _t2, this.z * _t1), Math.fma(this.y, _t2, this.w * _t1), Math.fma(this.z, _t2, -(this.x * _t1)), Math.fma(this.w, _t2, -(this.y * _t1)));
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
    public FloatQuat preRotateZ(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        return new FloatQuat(Math.fma(this.x, _t2, -(this.y * _t1)), Math.fma(this.x, _t1, this.y * _t2), Math.fma(this.z, _t2, this.w * _t1), Math.fma(this.w, _t2, -(this.z * _t1)));
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
    public FloatQuat rotateAxis(float angle, Float3 axis) {
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
    public FloatQuat rotateAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = (float) Math.cosFromSin(_t1, _t0);
        return new FloatQuat(Math.fma(this.x, _t5, this.w * _t2) + Math.fma(this.y, _t3, -(this.z * _t4)), Math.fma(this.y, _t5, this.z * _t2) + Math.fma(this.w, _t4, -(this.x * _t3)), Math.fma(this.x, _t4, this.w * _t3) + Math.fma(this.z, _t5, -(this.y * _t2)), Math.fma(this.w, _t5, -(this.x * _t2)) - Math.fma(this.y, _t4, this.z * _t3));
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
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length.
     *
     * @param fromDir the direction to rotate from (must be a unit vector)
     * @param toDir the direction to rotate onto (must be a unit vector)
     * @return the resulting quaternion
     */
    public FloatQuat rotateTo(Float3 fromDir, Float3 toDir) {
        return rotateTo(fromDir.x(), fromDir.y(), fromDir.z(), toDir.x(), toDir.y(), toDir.z());
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private FloatQuat rotateTo_sb47fb53_tail(float _t24, float _t35, float _t16, float _t29, float _t30, float _t13, float _t15, float _t18, float _t17, float _t19) {
        float _t44, _t45, _t46, _t47;
        if (_t24 > 6.0E-8f) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        return new FloatQuat(Math.fma(this.x, _t44, this.w * _t45) + Math.fma(this.y, _t46, -(this.z * _t47)), Math.fma(this.y, _t44, this.z * _t45) + Math.fma(this.w, _t47, -(this.x * _t46)), Math.fma(this.x, _t47, this.w * _t46) + Math.fma(this.z, _t44, -(this.y * _t45)), Math.fma(this.w, _t44, -(this.x * _t45)) - Math.fma(this.y, _t47, this.z * _t46));
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
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
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
    public FloatQuat rotateTo(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (Math.abs(fromDirZ) < Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = Math.fma(fromDirX, toDirY, -(fromDirY * toDirX));
        float _t16 = Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t17 = Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ));
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_sb47fb53_tail(0.5f * _t23, (1.0f / (float) Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17))))), _t16, _t29, (1.0f / (float) Math.sqrt(_t29)), _t13, _t15, _t18, _t17, _t19);
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
    public FloatQuat rotateX(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        return new FloatQuat(Math.fma(this.x, _t2, this.w * _t1), Math.fma(this.y, _t2, this.z * _t1), Math.fma(this.z, _t2, -(this.y * _t1)), Math.fma(this.w, _t2, -(this.x * _t1)));
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private FloatQuat rotateXYZ_s6e793366_tail(float _t11, float _t8, float _t10, float _t5, float _t21, float _t19, float _t20) {
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        return new FloatQuat(Math.fma(this.x, _t21, this.w * _t19) + Math.fma(this.y, _t20, -(this.z * _t22)), Math.fma(this.y, _t21, this.z * _t19) + Math.fma(this.w, _t22, -(this.x * _t20)), Math.fma(this.x, _t22, this.w * _t20) + Math.fma(this.z, _t21, -(this.y * _t19)), Math.fma(this.w, _t21, -(this.x * _t19)) - Math.fma(this.y, _t22, this.z * _t20));
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this quaternion, returning the result as a value.
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
    public FloatQuat rotateXYZ(float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        return rotateXYZ_s6e793366_tail(_t11, _t8, _t10, _t5, Math.fma(_t14, _t8, -(_t9 * _t5)), Math.fma(_t10, _t8, _t11 * _t5), Math.fma(_t9, _t8, _t14 * _t5));
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this quaternion, returning the result as a value.
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
    public FloatQuat rotateXZY(float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        return rotateXYZ_s6e793366_tail(_t12, _t5, _t9, _t8, Math.fma(_t9, _t5, _t12 * _t8), Math.fma(_t10, _t8, -(_t11 * _t5)), Math.fma(_t10, _t5, _t11 * _t8));
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
    public FloatQuat rotateY(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        return new FloatQuat(Math.fma(this.x, _t2, -(this.z * _t1)), Math.fma(this.y, _t2, this.w * _t1), Math.fma(this.x, _t1, this.z * _t2), Math.fma(this.w, _t2, -(this.y * _t1)));
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this quaternion, returning the result as a value.
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
    public FloatQuat rotateYXZ(float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        return rotateXYZ_s6e793366_tail(_t11, _t8, _t10, _t5, Math.fma(_t9, _t5, _t12 * _t8), Math.fma(_t10, _t8, _t11 * _t5), Math.fma(_t12, _t5, -(_t9 * _t8)));
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private FloatQuat rotateYZX_s71a48226_tail(float _t10, float _t8, float _t11, float _t5, float _t21, float _t19, float _t20) {
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return new FloatQuat(Math.fma(this.x, _t21, this.w * _t19) + Math.fma(this.y, _t22, -(this.z * _t20)), Math.fma(this.y, _t21, this.z * _t19) + Math.fma(this.w, _t20, -(this.x * _t22)), Math.fma(this.x, _t20, this.w * _t22) + Math.fma(this.z, _t21, -(this.y * _t19)), Math.fma(this.w, _t21, -(this.x * _t19)) - Math.fma(this.y, _t20, this.z * _t22));
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this quaternion, returning the result as a value.
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
    public FloatQuat rotateYZX(float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t14 = _t6 * _t7;
        return rotateYZX_s71a48226_tail(_t10, _t8, _t11, _t5, Math.fma(_t14, _t8, -(_t9 * _t5)), Math.fma(_t9, _t8, _t14 * _t5), Math.fma(_t11, _t8, _t10 * _t5));
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
    public FloatQuat rotateZ(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        return new FloatQuat(Math.fma(this.x, _t2, this.y * _t1), Math.fma(this.y, _t2, -(this.x * _t1)), Math.fma(this.z, _t2, this.w * _t1), Math.fma(this.w, _t2, -(this.z * _t1)));
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private FloatQuat rotateZXY_s673d9226_tail(float _t10, float _t8, float _t11, float _t5, float _t21, float _t19, float _t20) {
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return new FloatQuat(Math.fma(this.x, _t21, this.w * _t22) + Math.fma(this.y, _t19, -(this.z * _t20)), Math.fma(this.y, _t21, this.z * _t22) + Math.fma(this.w, _t20, -(this.x * _t19)), Math.fma(this.x, _t20, this.w * _t19) + Math.fma(this.z, _t21, -(this.y * _t22)), Math.fma(this.w, _t21, -(this.x * _t22)) - Math.fma(this.y, _t20, this.z * _t19));
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this quaternion, returning the result as a value.
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
    public FloatQuat rotateZXY(float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        return rotateZXY_s673d9226_tail(_t10, _t8, _t11, _t5, Math.fma(_t14, _t8, -(_t9 * _t5)), Math.fma(_t10, _t5, _t11 * _t8), Math.fma(_t9, _t8, _t14 * _t5));
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this quaternion, returning the result as a value.
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
    public FloatQuat rotateZYX(float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t12 = _t6 * _t7;
        return rotateYZX_s71a48226_tail(_t10, _t8, _t11, _t5, Math.fma(_t9, _t5, _t12 * _t8), Math.fma(_t12, _t5, -(_t9 * _t8)), Math.fma(_t11, _t8, _t10 * _t5));
    }


    /**
     * Transform {@code v} by this quaternion, returning the result as a value.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param v the vector to transform
     * @return the resulting vector
     */
    public Float3 transform(Float3 v) {
        return transform(v.x(), v.y(), v.z());
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
    public Float3 transform(float vX, float vY, float vZ) {
        float _t9 = 2.0f * Math.fma(this.x, vY, -(this.y * vX));
        float _t10 = 2.0f * Math.fma(this.z, vX, -(this.x * vZ));
        float _t11 = 2.0f * Math.fma(this.y, vZ, -(this.z * vY));
        return new Float3(Math.fma(this.y, _t9, Math.fma(-this.z, _t10, Math.fma(this.w, _t11, vX))), Math.fma(this.z, _t11, Math.fma(-this.x, _t9, Math.fma(this.w, _t10, vY))), Math.fma(this.x, _t10, Math.fma(-this.y, _t11, Math.fma(this.w, _t9, vZ))));
    }


    /**
     * Transform {@code v} by the inverse of this quaternion, returning the result as a value.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param v the vector to transform
     * @return the resulting vector
     */
    public Float3 transformInverse(Float3 v) {
        return transformInverse(v.x(), v.y(), v.z());
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
    public Float3 transformInverse(float vX, float vY, float vZ) {
        float _t9 = 2.0f * Math.fma(this.x, vZ, -(this.z * vX));
        float _t10 = 2.0f * Math.fma(this.y, vX, -(this.x * vY));
        float _t11 = 2.0f * Math.fma(this.z, vY, -(this.y * vZ));
        return new Float3(Math.fma(this.z, _t9, Math.fma(-this.y, _t10, Math.fma(this.w, _t11, vX))), Math.fma(this.x, _t10, Math.fma(-this.z, _t11, Math.fma(this.w, _t9, vY))), Math.fma(this.y, _t11, Math.fma(-this.x, _t9, Math.fma(this.w, _t10, vZ))));
    }

    /**
     * {@return a copy with the {@code x} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code x} component
     */
    public FloatQuat withX(float v) {
        return new FloatQuat(v, y, z, w);
    }

    /**
     * {@return a copy with the {@code y} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code y} component
     */
    public FloatQuat withY(float v) {
        return new FloatQuat(x, v, z, w);
    }

    /**
     * {@return a copy with the {@code z} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code z} component
     */
    public FloatQuat withZ(float v) {
        return new FloatQuat(x, y, v, w);
    }

    /**
     * {@return a copy with the {@code w} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code w} component
     */
    public FloatQuat withW(float v) {
        return new FloatQuat(x, y, z, v);
    }

    @Override public String toString() {
        return "FloatQuat(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatQuat)) return false;
        FloatQuat o = (FloatQuat) obj;
        return Float.floatToIntBits(x) == Float.floatToIntBits(o.x)
            && Float.floatToIntBits(y) == Float.floatToIntBits(o.y)
            && Float.floatToIntBits(z) == Float.floatToIntBits(o.z)
            && Float.floatToIntBits(w) == Float.floatToIntBits(o.w);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(x);
        h = 31 * h + Float.floatToIntBits(y);
        h = 31 * h + Float.floatToIntBits(z);
        h = 31 * h + Float.floatToIntBits(w);
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Float.isFinite(x)
            && Float.isFinite(y)
            && Float.isFinite(z)
            && Float.isFinite(w);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Float.isNaN(x)
            || Float.isNaN(y)
            || Float.isNaN(z)
            || Float.isNaN(w);
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
    public boolean equalsEpsilon(FloatQuat other, float epsilon) {
        return Math.abs(x - other.x()) <= epsilon
            && Math.abs(y - other.y()) <= epsilon
            && Math.abs(z - other.z()) <= epsilon
            && Math.abs(w - other.w()) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatQuatSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatQuatSegOpsUnsafe()
                        : new FloatQuatSegOpsMS();
        static final FloatQuatBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatQuatBbOpsUnsafe()
                        : new FloatQuatBbOpsApi();
        static final FloatQuatRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatQuatRawOpsUnsafe()
                        : new FloatQuatRawOpsApi();
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
        dest[offset + 0] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
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
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat load(float[] src, int offset) {
        float _c0 = src[offset + 0];
        float _c1 = src[offset + 1];
        float _c2 = src[offset + 2];
        float _c3 = src[offset + 3];
        return new FloatQuat(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat load(float[] src) { return load(src, 0); }

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
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 4);
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
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat load(FloatBuffer buf) {
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
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat loadAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code FloatQuat} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatQuat loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatQuat r = loadAbsolute(pos, buf);
        buf.position(pos + 4);
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
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 16);
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
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat load(ByteBuffer buf) {
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
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code FloatQuat} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatQuat loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatQuat r = loadAbsolute(pos, buf);
        buf.position(pos + 16);
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
    public FloatQuat storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat loadUnsafe(long address) {
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
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat load(MemorySegment src) { return load(0L, src); }

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
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat load(long offset, MemorySegment src) {
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
        dest[offset + 0] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
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
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat load(double[] src, int offset) {
        float _c0 = (float) src[offset + 0];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[offset + 2];
        float _c3 = (float) src[offset + 3];
        return new FloatQuat(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array, converting each element from {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat load(double[] src) { return load(src, 0); }

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
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 4);
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
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat load(DoubleBuffer buf) {
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
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat loadAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code FloatQuat} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatQuat loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatQuat r = loadAbsolute(pos, buf);
        buf.position(pos + 4);
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
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeDoubleAbsolute(pos, buf);
        buf.position(pos + 32);
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
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat loadDouble(ByteBuffer buf) {
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
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat loadDoubleAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code FloatQuat} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatQuat loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatQuat r = loadDoubleAbsolute(pos, buf);
        buf.position(pos + 32);
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
    public FloatQuat storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat loadDoubleUnsafe(long address) {
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
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat loadDouble(MemorySegment src) { return loadDouble(0L, src); }

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
     * @return a new {@code FloatQuat} holding the loaded elements
     */
    public static FloatQuat loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(offset, src);
    }

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]:
     * 2 asin(|a-b|/2) up to pi/2 and pi - 2 asin(|a+b|/2) beyond, so asin always sees an
     * argument of at most sqrt(2)/2 and the angle stays accurate at both ends.
     */
    private static float quatArcAngle(float s) {
        float d = 4.0f - s;
        return s > d ? 2.0f * (float) Math.asin(0.5f * (float) Math.sqrt(d))
                : (float) Math.PI - 2.0f * (float) Math.asin(0.5f * (float) Math.sqrt(s));
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
