// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Immutable rigid transform of double-precision {@code double} components, declared as a value
 * record.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance; as a value class, instances have no identity and may be flattened by the JVM.
 * <p>
 * Its rotation is a unit quaternion. Every operation that applies, composes, inverts or converts
 * this rigid transform assumes its rotation has unit length and does not divide it out. A value
 * that has drifted from unit length (after many multiplications, say) gives wrong results rather
 * than an error: {@code normalize} it first.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Double.doubleToLongBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns).
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 *
 * @param tX the {@code tX} component
 * @param tY the {@code tY} component
 * @param tZ the {@code tZ} component
 * @param rX the {@code rX} component
 * @param rY the {@code rY} component
 * @param rZ the {@code rZ} component
 * @param rW the {@code rW} component
 */
@jdk.internal.vm.annotation.LooselyConsistentValue
public value record DoubleRigid(double tX, double tY, double tZ, double rX, double rY, double rZ, double rW) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 56;

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param tX the {@code tX} component
     * @param tY the {@code tY} component
     * @param tZ the {@code tZ} component
     * @param rX the {@code rX} component
     * @param rY the {@code rY} component
     * @param rZ the {@code rZ} component
     * @param rW the {@code rW} component
     */
    public DoubleRigid(double tX, double tY, double tZ, double rX, double rY, double rZ, double rW) {
        this.tX = tX;
        this.tY = tY;
        this.tZ = tZ;
        this.rX = rX;
        this.rY = rY;
        this.rZ = rZ;
        this.rW = rW;
    }

    /**
     * Create a new instance initialized to the identity transform.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public DoubleRigid() {
        this(0, 0, 0, 0, 0, 0, 1);
    }

    /**
     * Create a rigid transform from its translation and rotation.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param translation the translation
     * @param rotation the rotation quaternion, taken as given (not normalized)
     */
    public DoubleRigid(Double3 translation, DoubleQuat rotation) {
        this(translation.x(), translation.y(), translation.z(), rotation.x(), rotation.y(), rotation.z(), rotation.w());
    }

    /** {@return the {@code tX} component} <p>Valid input: any value, NaN and the infinities included. */
    public double tX() { return tX; }
    /** {@return the {@code tY} component} <p>Valid input: any value, NaN and the infinities included. */
    public double tY() { return tY; }
    /** {@return the {@code tZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public double tZ() { return tZ; }
    /** {@return the {@code rX} component} <p>Valid input: any value, NaN and the infinities included. */
    public double rX() { return rX; }
    /** {@return the {@code rY} component} <p>Valid input: any value, NaN and the infinities included. */
    public double rY() { return rY; }
    /** {@return the {@code rZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public double rZ() { return rZ; }
    /** {@return the {@code rW} component} <p>Valid input: any value, NaN and the infinities included. */
    public double rW() { return rW; }

    /**
     * Create a new rigid transform from its translation and rotation.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param translation the translation
     * @param rotation the rotation quaternion, taken as given (not normalized)
     * @return the resulting rigid transform
     */
    public DoubleRigid set(Double3 translation, DoubleQuat rotation) {
        return new DoubleRigid(translation, rotation);
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
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeFromAxisAngle(Double3 axis, double angle, Double3 translation) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleRigid(translationX, translationY, translationZ, axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0));
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
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeFromAxisAngle(double axisX, double axisY, double axisZ, double angle, double translationX, double translationY, double translationZ) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleRigid(translationX, translationY, translationZ, axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0));
    }


    /**
     * Create a rigid transformation that first rotates by {@code rotation} and then translates by
     * {@code translation} ({@code T * R}).
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param translation the translation
     * @param rotation the rotation
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeTranslationRotation(Double3 translation, DoubleQuat rotation) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        return new DoubleRigid(translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW);
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
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeTranslationRotation(double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW) {
        return new DoubleRigid(translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW);
    }


    /**
     * Create a new rigid transform from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the rigid transform to copy
     * @return the resulting rigid transform
     */
    public DoubleRigid set(DoubleRigid v) {
        double vTX = v.tX();
        double vTY = v.tY();
        double vTZ = v.tZ();
        double vRX = v.rX();
        double vRY = v.rY();
        double vRZ = v.rZ();
        double vRW = v.rW();
        return new DoubleRigid(vTX, vTY, vTZ, vRX, vRY, vRZ, vRW);
    }


    /**
     * Create a new rigid transform from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vTX the {@code tX} component of the rigid transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW)}
     * @param vTY the {@code tY} component of the rigid transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW)}
     * @param vTZ the {@code tZ} component of the rigid transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW)}
     * @param vRX the {@code rX} component of the rigid transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW)}
     * @param vRY the {@code rY} component of the rigid transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW)}
     * @param vRZ the {@code rZ} component of the rigid transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW)}
     * @param vRW the {@code rW} component of the rigid transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW)}
     * @return the resulting rigid transform
     */
    public DoubleRigid set(double vTX, double vTY, double vTZ, double vRX, double vRY, double vRZ, double vRW) {
        return new DoubleRigid(vTX, vTY, vTZ, vRX, vRY, vRZ, vRW);
    }


    /**
     * Set the rotation of this rigid transform to {@code r}, returning the result as a value.
     * <p>
     * Valid input: {@code r} must have unit length.
     *
     * @param r the new rotation
     * @return the resulting rigid transform
     */
    public DoubleRigid setRotation(DoubleQuat r) {
        double rX = r.x();
        double rY = r.y();
        double rZ = r.z();
        double rW = r.w();
        return new DoubleRigid(this.tX, this.tY, this.tZ, rX, rY, rZ, rW);
    }


    /**
     * Set the rotation of this rigid transform to ({@code rX}, {@code rY}, {@code rZ}, {@code rW}),
     * returning the result as a value.
     * <p>
     * Valid input: {@code (rX, rY, rZ, rW)} must have unit length.
     *
     * @param rX the {@code x} component of the quaternion {@code (rX, rY, rZ, rW)}
     * @param rY the {@code y} component of the quaternion {@code (rX, rY, rZ, rW)}
     * @param rZ the {@code z} component of the quaternion {@code (rX, rY, rZ, rW)}
     * @param rW the {@code w} component of the quaternion {@code (rX, rY, rZ, rW)}
     * @return the resulting rigid transform
     */
    public DoubleRigid setRotation(double rX, double rY, double rZ, double rW) {
        return new DoubleRigid(this.tX, this.tY, this.tZ, rX, rY, rZ, rW);
    }


    /**
     * Set the translation of this rigid transform to {@code t}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the translation vector
     * @return the resulting rigid transform
     */
    public DoubleRigid setTranslation(Double3 t) {
        double tX = t.x();
        double tY = t.y();
        double tZ = t.z();
        return new DoubleRigid(tX, tY, tZ, this.rX, this.rY, this.rZ, this.rW);
    }


    /**
     * Set the translation of this rigid transform to ({@code tX}, {@code tY}, {@code tZ}),
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param tX the {@code x} component of the vector {@code (tX, tY, tZ)}
     * @param tY the {@code y} component of the vector {@code (tX, tY, tZ)}
     * @param tZ the {@code z} component of the vector {@code (tX, tY, tZ)}
     * @return the resulting rigid transform
     */
    public DoubleRigid setTranslation(double tX, double tY, double tZ) {
        return new DoubleRigid(tX, tY, tZ, this.rX, this.rY, this.rZ, this.rW);
    }


    /**
     * Create the rigid motion of the unit dual quaternion {@code dq} (an exact conversion - both
     * represent rotation plus translation).
     * <p>
     * Valid input: {@code dq} must be a unit dual quaternion.
     *
     * @param dq the dual quaternion to convert
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeFromDualQuat(DoubleDualQuat dq) {
        double dqRX = dq.rX();
        double dqRY = dq.rY();
        double dqRZ = dq.rZ();
        double dqRW = dq.rW();
        double dqDX = dq.dX();
        double dqDY = dq.dY();
        double dqDZ = dq.dZ();
        double dqDW = dq.dW();
        return new DoubleRigid(2.0 * (Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + Math.fma(dqRW, dqDX, -(dqRX * dqDW))), 2.0 * (Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + Math.fma(dqRW, dqDY, -(dqRY * dqDW))), 2.0 * (Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + Math.fma(dqRW, dqDZ, -(dqRZ * dqDW))), dqRX, dqRY, dqRZ, dqRW);
    }


    /**
     * Create the rigid motion of the unit dual quaternion ({@code dqRX}, {@code dqRY},
     * {@code dqRZ}, {@code dqRW}, {@code dqDX}, {@code dqDY}, {@code dqDZ}, {@code dqDW}) (an exact
     * conversion - both represent rotation plus translation).
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
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeFromDualQuat(double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        return new DoubleRigid(2.0 * (Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + Math.fma(dqRW, dqDX, -(dqRX * dqDW))), 2.0 * (Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + Math.fma(dqRW, dqDY, -(dqRY * dqDW))), 2.0 * (Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + Math.fma(dqRW, dqDZ, -(dqRZ * dqDW))), dqRX, dqRY, dqRZ, dqRW);
    }


    /**
     * Create the rotation extracted from the given matrix, with zero translation (scale is removed
     * by normalizing the columns, but shear is not removed: a sheared block yields a rotation
     * quaternion that is not unit length).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeFromMatrix(Double3x3 m) {
        double _ct0 = Math.fma(m.m21(), m.m21(), Math.fma(m.m01(), m.m01(), m.m11() * m.m11()));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t15 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _ct1 = Math.fma(m.m22(), m.m22(), Math.fma(m.m02(), m.m02(), m.m12() * m.m12()));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _ct2 = Math.fma(m.m20(), m.m20(), Math.fma(m.m00(), m.m00(), m.m10() * m.m10()));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        return makeFromMatrix_s4b73775e_1(m, _t15, _t16, _t17, -m.m11(), -m.m22(), m.m10() * _t17, m.m22() * _t16, m.m12() * _t16, m.m20() * _t17, m.m21() * _t15, m.m11() * _t15);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleRigid makeFromMatrix_s4b73775e_1(Double3x3 m, double _t15, double _t16, double _t17, double _t0, double _t1, double _t18, double _t19, double _t20, double _t21, double _t23, double _t24) {
        double _t26 = m.m00() * _t17;
        double _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), m.m01() * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), m.m02() * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        double _t51 = 1.0 + _t47;
        double _t52 = 1.0 - _t47;
        double _t63 = Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t51));
        return makeFromMatrix_s4b73775e_2(m, _t15, _t16, _t19, _t24, Math.fma(m.m12(), _t16, _t23), Math.fma(m.m21(), _t15, -_t20), _t47, Math.fma(m.m01(), _t15, _t48), Math.fma(m.m02(), _t16, _t49), Math.fma(m.m02(), _t16, -_t49), Math.fma(-m.m01(), _t15, _t48), _t63, 0.5 * (1.0 / java.lang.Math.sqrt(_t63)), Math.fma(m.m11(), _t15, Math.fma(_t1, _t16, _t52)), Math.fma(m.m22(), _t16, Math.fma(_t0, _t15, _t52)), Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleRigid makeFromMatrix_s4b73775e_2(Double3x3 m, double _t15, double _t16, double _t19, double _t24, double _t31, double _t35, double _t47, double _t54, double _t55, double _t56, double _t57, double _t63, double _sp0, double _t65, double _t66, double _t67) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t65));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t47)) > 0.0) {
            _sfx3 = _sp0 * _t35;
            _sfx4 = _sp0 * _t56;
            _sfx5 = _sp0 * _t57;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t63);
        } else {
            if (_t47 > java.lang.Math.max(_t24, _t19)) {
                _sfx3 = 0.5 * java.lang.Math.sqrt(_t67);
                _sfx4 = _sp3 * _t54;
                _sfx5 = _sp3 * _t55;
                _sfx6 = _sp3 * _t35;
            } else {
                if (_t24 > _t19) {
                    _sfx3 = _sp1 * _t54;
                    _sfx4 = 0.5 * java.lang.Math.sqrt(_t65);
                    _sfx5 = _sp1 * _t31;
                    _sfx6 = _sp1 * _t56;
                } else {
                    _sfx3 = _sp2 * _t55;
                    _sfx4 = _sp2 * _t31;
                    _sfx5 = 0.5 * java.lang.Math.sqrt(_t66);
                    _sfx6 = _sp2 * _t57;
                }
            }
        }
        return new DoubleRigid(0.0, 0.0, 0.0, _sfx3, _sfx4, _sfx5, _sfx6);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_s91e5aa_tail(double _t31, double _t15, double _t16, double _t32, double _t13, double _t12, double _t14, double _t33, double _t34, double _t35, double _t36, double _t27, double _t28, double _t29) {
        double _t37 = _t31 * _t15;
        double _t38 = _t31 * _t16;
        double _t39 = _t32 * _t13;
        double _t40 = _t32 * _t12;
        double _t41 = _t32 * _t14;
        double _t50 = java.lang.Math.abs(_t40);
        double _t51 = java.lang.Math.abs(_t39);
        double _t72, _t75, _t87;
        if (java.lang.Math.abs(_t33) < java.lang.Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0;
            _t87 = -_t34;
        } else {
            _t72 = 0.0;
            _t75 = -_t35;
            _t87 = _t33;
        }
        double _t73, _t76, _t88;
        if (java.lang.Math.abs(_t37) < java.lang.Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0;
            _t88 = -_t38;
        } else {
            _t73 = 0.0;
            _t76 = -_t36;
            _t88 = _t37;
        }
        double _t74, _t77;
        if (_t50 < _t51) {
            _t74 = _t41;
            _t77 = 0.0;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail2(_t50, _t51, _t39, _t40, _t75, _t87, _t72, _t76, _t88, _t73, _t77, _t74, _t27, _t28, _t29, _t36, _t37, _t33, _t35, _t34, _t38, _t41);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_s91e5aa_tail2(double _t50, double _t51, double _t39, double _t40, double _t75, double _t87, double _t72, double _t76, double _t88, double _t73, double _t77, double _t74, double _t27, double _t28, double _t29, double _t36, double _t37, double _t33, double _t35, double _t34, double _t38, double _t41) {
        double _t89 = _t50 < _t51 ? -_t39 : _t40;
        double _t99 = (1.0 / java.lang.Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / java.lang.Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / java.lang.Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        return makeFromMatrix_degenerate_s91e5aa_tail3(_t27, _t28, _t29, _t99 * _t72, _t36, _t100 * _t76, _t37, _t100 * _t88, _t33, _t35, _t39, _t101 * _t89, _t34, _t99 * _t75, _t40, _t99 * _t87, _t100 * _t73, _t38, _t41, _t101 * _t74, _t101 * _t77);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_s91e5aa_tail3(double _t27, double _t28, double _t29, double _t102, double _t36, double _t105, double _t37, double _t114, double _t33, double _t35, double _t39, double _t115, double _t34, double _t106, double _t40, double _t116, double _t103, double _t38, double _t41, double _t104, double _t107) {
        double _t165, _t166;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = 0.0;
                    _t166 = 0.0;
                } else {
                    _t165 = _t102;
                    _t166 = Math.fma(_t33, _t102, -(_t34 * _t106));
                }
            } else {
                _t165 = _t29 <= 0.0 ? Math.fma(_t36, _t105, -(_t37 * _t114)) : Math.fma(_t33, _t36, -(_t35 * _t37));
                _t166 = _t36;
            }
        } else {
            _t165 = _t39;
            _t166 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t115 : Math.fma(_t33, _t39, -(_t34 * _t40)) : _t36;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail4(_t27, _t28, _t29, _t116, _t37, _t103, _t38, _t105, _t34, _t33, _t41, _t104, _t35, _t106, _t40, _t39, _t115, _t36, _t114, _t107, _t102, _t166, _t165);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_s91e5aa_tail4(double _t27, double _t28, double _t29, double _t116, double _t37, double _t103, double _t38, double _t105, double _t34, double _t33, double _t41, double _t104, double _t35, double _t106, double _t40, double _t39, double _t115, double _t36, double _t114, double _t107, double _t102, double _t166, double _t165) {
        double _t167, _t168;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t167 = 1.0;
                    _t168 = 0.0;
                } else {
                    _t167 = _t116;
                    _t168 = Math.fma(_t35, _t106, -(_t33 * _t116));
                }
            } else {
                _t167 = _t29 <= 0.0 ? Math.fma(_t37, _t103, -(_t38 * _t105)) : Math.fma(_t34, _t37, -(_t33 * _t38));
                _t168 = _t38;
            }
        } else {
            _t167 = _t41;
            _t168 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t104 : Math.fma(_t35, _t40, -(_t33 * _t41)) : _t38;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail5(_t29, _t27, _t28, _t105, _t39, _t115, _t41, _t104, _t36, _t38, _t33, _t106, _t114, _t103, _t35, _t34, _t40, _t107, _t116, _t102, _t37, _t166, _t167, _t165, _t168);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_s91e5aa_tail5(double _t29, double _t27, double _t28, double _t105, double _t39, double _t115, double _t41, double _t104, double _t36, double _t38, double _t33, double _t106, double _t114, double _t103, double _t35, double _t34, double _t40, double _t107, double _t116, double _t102, double _t37, double _t166, double _t167, double _t165, double _t168) {
        double _t169, _t170;
        if (_t29 <= 0.0) {
            if (_t27 <= 0.0) {
                if (_t28 <= 0.0) {
                    _t169 = 0.0;
                    _t170 = 0.0;
                } else {
                    _t169 = _t105;
                    _t170 = Math.fma(_t38, _t114, -(_t36 * _t103));
                }
            } else {
                _t169 = _t28 <= 0.0 ? Math.fma(_t39, _t115, -(_t41 * _t104)) : Math.fma(_t39, _t36, -(_t41 * _t38));
                _t170 = _t40;
            }
        } else {
            _t169 = _t33;
            _t170 = _t27 <= 0.0 ? _t28 <= 0.0 ? _t106 : Math.fma(_t35, _t38, -(_t34 * _t36)) : _t40;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail6(_t28, _t29, _t27, _t107, _t34, _t116, _t35, _t102, _t41, _t39, _t37, _t114, _t40, _t104, _t38, _t103, _t115, _t36, _t170, _t166, _t167, _t165, _t168, _t169);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_s91e5aa_tail6(double _t28, double _t29, double _t27, double _t107, double _t34, double _t116, double _t35, double _t102, double _t41, double _t39, double _t37, double _t114, double _t40, double _t104, double _t38, double _t103, double _t115, double _t36, double _t170, double _t166, double _t167, double _t165, double _t168, double _t169) {
        double _t171, _t172;
        if (_t28 <= 0.0) {
            if (_t29 <= 0.0) {
                if (_t27 <= 0.0) {
                    _t171 = 1.0;
                    _t172 = 0.0;
                } else {
                    _t171 = _t107;
                    _t172 = Math.fma(_t40, _t104, -(_t39 * _t107));
                }
            } else {
                _t171 = _t27 <= 0.0 ? Math.fma(_t34, _t116, -(_t35 * _t102)) : Math.fma(_t34, _t41, -(_t35 * _t39));
                _t172 = _t35;
            }
        } else {
            _t171 = _t37;
            _t172 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t114 : Math.fma(_t40, _t38, -(_t39 * _t37)) : _t35;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail7(_t29, _t27, _t28, _t103, _t41, _t107, _t40, _t115, _t37, _t36, _t34, _t170, _t166, _t167, _t171, _t165, _t168, _t169, _t172);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_s91e5aa_tail7(double _t29, double _t27, double _t28, double _t103, double _t41, double _t107, double _t40, double _t115, double _t37, double _t36, double _t34, double _t170, double _t166, double _t167, double _t171, double _t165, double _t168, double _t169, double _t172) {
        double _t173 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t28 <= 0.0 ? 1.0 : _t103 : _t28 <= 0.0 ? Math.fma(_t41, _t107, -(_t40 * _t115)) : Math.fma(_t41, _t37, -(_t40 * _t36)) : _t34;
        double _t194, _t195, _t196;
        if (Math.fma(Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, Math.fma(Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail8(_t195, _t165, _t194, _t167, _t171, _t170 - _t166, java.lang.Math.max(_t167, _t171), _t195 + _t165, _t196 + _t168, _t168 - _t196, _t170 + _t166);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_s91e5aa_tail8(double _t195, double _t165, double _t194, double _t167, double _t171, double _t182, double _t183, double _t199, double _t200, double _t201, double _t184) {
        double _t206 = _t194 + _t167 + _t171;
        double _t207 = 1.0 + _t206;
        double _t208 = 1.0 + _t194 - _t167 - _t171;
        double _t209 = 1.0 + _t167 - _t194 - _t171;
        double _t210 = 1.0 + _t171 - _t194 - _t167;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sfx3 = _t206 > 0.0 ? _sp0 * _t182 : _t194 > _t183 ? 0.5 * java.lang.Math.sqrt(_t208) : _t167 > _t171 ? _sp1 * _t199 : _sp2 * _t200;
        return makeFromMatrix_degenerate_s91e5aa_tail9(_t206, _sp0, _t201, _t194, _t183, 0.5 * (1.0 / java.lang.Math.sqrt(_t208)), _t199, _t167, _t171, _t209, _sp2, _t184, _t195 - _t165, _t200, _sp1, _t210, _t207, _t182, 0.0, 0.0, 0.0, _sfx3);
    }

    /**
     * Private tail of {@code makeFromMatrix_degenerate}. Shared by 3 identical private paths of
     * {@code makeFromMatrix}; reached only through it.
     */
    private static DoubleRigid makeFromMatrix_degenerate_s91e5aa_tail9(double _t206, double _sp0, double _t201, double _t194, double _t183, double _sp3, double _t199, double _t167, double _t171, double _t209, double _sp2, double _t184, double _t202, double _t200, double _sp1, double _t210, double _t207, double _t182, double _sfx0, double _sfx1, double _sfx2, double _sfx3) {
        double _sfx4, _sfx5, _sfx6;
        if (_t206 > 0.0) {
            _sfx4 = _sp0 * _t201;
            _sfx5 = _sp0 * _t202;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > _t183) {
                _sfx4 = _sp3 * _t199;
                _sfx5 = _sp3 * _t200;
                _sfx6 = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    _sfx4 = 0.5 * java.lang.Math.sqrt(_t209);
                    _sfx5 = _sp1 * _t184;
                    _sfx6 = _sp1 * _t201;
                } else {
                    _sfx4 = _sp2 * _t184;
                    _sfx5 = 0.5 * java.lang.Math.sqrt(_t210);
                    _sfx6 = _sp2 * _t202;
                }
            }
        }
        return new DoubleRigid(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6);
    }


    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static DoubleRigid makeFromMatrix_degenerate(Double3x3 m) {
        double _t0 = unitScale(m.m01(), m.m11(), m.m21());
        double _t1 = unitScale(m.m02(), m.m12(), m.m22());
        double _t2 = unitScale(m.m00(), m.m10(), m.m20());
        double _t12 = m.m21() * _t0;
        double _t13 = m.m01() * _t0;
        double _t14 = m.m11() * _t0;
        double _t15 = m.m22() * _t1;
        double _t16 = m.m02() * _t1;
        double _t17 = m.m12() * _t1;
        double _t18 = m.m20() * _t2;
        double _t19 = m.m00() * _t2;
        double _t20 = m.m10() * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        return makeFromMatrix_degenerate_s91e5aa_tail(_t31, _t15, _t16, (1.0 / java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t30 * _t20, _t31 * _t17, _t27, _t28, _t29);
    }


    /**
     * Create the rigid decomposition of the given affine matrix: translation from the last column,
     * rotation from the column-normalized upper-left 3x3 block (scale is removed by normalizing the
     * columns, but shear is not removed: a sheared block yields a rotation quaternion that is not
     * unit length).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeFromMatrix(Double3x4 m) {
        double _ct0 = Math.fma(m.m21(), m.m21(), Math.fma(m.m01(), m.m01(), m.m11() * m.m11()));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t15 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _ct1 = Math.fma(m.m22(), m.m22(), Math.fma(m.m02(), m.m02(), m.m12() * m.m12()));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _ct2 = Math.fma(m.m20(), m.m20(), Math.fma(m.m00(), m.m00(), m.m10() * m.m10()));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        return makeFromMatrix_s4d24221f_1(m, _t15, _t16, _t17, -m.m11(), -m.m22(), m.m10() * _t17, m.m22() * _t16, m.m12() * _t16, m.m20() * _t17, m.m21() * _t15, m.m11() * _t15);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleRigid makeFromMatrix_s4d24221f_1(Double3x4 m, double _t15, double _t16, double _t17, double _t0, double _t1, double _t18, double _t19, double _t20, double _t21, double _t23, double _t24) {
        double _t26 = m.m00() * _t17;
        double _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), m.m01() * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), m.m02() * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        double _t51 = 1.0 + _t47;
        double _t52 = 1.0 - _t47;
        double _t63 = Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t51));
        return makeFromMatrix_s4d24221f_2(m, _t15, _t16, _t19, _t24, Math.fma(m.m12(), _t16, _t23), Math.fma(m.m21(), _t15, -_t20), _t47, Math.fma(m.m01(), _t15, _t48), Math.fma(m.m02(), _t16, _t49), Math.fma(m.m02(), _t16, -_t49), Math.fma(-m.m01(), _t15, _t48), _t63, 0.5 * (1.0 / java.lang.Math.sqrt(_t63)), Math.fma(m.m11(), _t15, Math.fma(_t1, _t16, _t52)), Math.fma(m.m22(), _t16, Math.fma(_t0, _t15, _t52)), Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleRigid makeFromMatrix_s4d24221f_2(Double3x4 m, double _t15, double _t16, double _t19, double _t24, double _t31, double _t35, double _t47, double _t54, double _t55, double _t56, double _t57, double _t63, double _sp0, double _t65, double _t66, double _t67) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t65));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        double _sfx0 = m.m03();
        double _sfx1 = m.m13();
        double _sfx2 = m.m23();
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t47)) > 0.0) {
            _sfx3 = _sp0 * _t35;
            _sfx4 = _sp0 * _t56;
            _sfx5 = _sp0 * _t57;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t63);
        } else {
            if (_t47 > java.lang.Math.max(_t24, _t19)) {
                _sfx3 = 0.5 * java.lang.Math.sqrt(_t67);
                _sfx4 = _sp3 * _t54;
                _sfx5 = _sp3 * _t55;
                _sfx6 = _sp3 * _t35;
            } else {
                if (_t24 > _t19) {
                    _sfx3 = _sp1 * _t54;
                    _sfx4 = 0.5 * java.lang.Math.sqrt(_t65);
                    _sfx5 = _sp1 * _t31;
                    _sfx6 = _sp1 * _t56;
                } else {
                    _sfx3 = _sp2 * _t55;
                    _sfx4 = _sp2 * _t31;
                    _sfx5 = 0.5 * java.lang.Math.sqrt(_t66);
                    _sfx6 = _sp2 * _t57;
                }
            }
        }
        return new DoubleRigid(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_s91e96b_tail(double _t31, double _t17, double _t15, double _t16, double _t32, double _t13, double _t12, double _t14, double _t33, double _t34, double _t35, double _t27, double _t28, double _t29, Double3x4 m) {
        double _t36 = _t31 * _t17;
        double _t37 = _t31 * _t15;
        double _t38 = _t31 * _t16;
        double _t39 = _t32 * _t13;
        double _t40 = _t32 * _t12;
        double _t41 = _t32 * _t14;
        double _t50 = java.lang.Math.abs(_t40);
        double _t51 = java.lang.Math.abs(_t39);
        double _t72, _t75, _t87;
        if (java.lang.Math.abs(_t33) < java.lang.Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0;
            _t87 = -_t34;
        } else {
            _t72 = 0.0;
            _t75 = -_t35;
            _t87 = _t33;
        }
        double _t73, _t76, _t88;
        if (java.lang.Math.abs(_t37) < java.lang.Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0;
            _t88 = -_t38;
        } else {
            _t73 = 0.0;
            _t76 = -_t36;
            _t88 = _t37;
        }
        double _t74, _t77;
        if (_t50 < _t51) {
            _t74 = _t41;
            _t77 = 0.0;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
        }
        return makeFromMatrix_degenerate_s91e96b_tail2(_t50, _t51, _t39, _t40, _t75, _t87, _t72, _t76, _t88, _t73, _t77, _t74, _t27, _t28, _t29, _t36, _t37, _t33, _t35, _t34, _t38, _t41, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_s91e96b_tail2(double _t50, double _t51, double _t39, double _t40, double _t75, double _t87, double _t72, double _t76, double _t88, double _t73, double _t77, double _t74, double _t27, double _t28, double _t29, double _t36, double _t37, double _t33, double _t35, double _t34, double _t38, double _t41, Double3x4 m) {
        double _t89 = _t50 < _t51 ? -_t39 : _t40;
        double _t99 = (1.0 / java.lang.Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / java.lang.Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / java.lang.Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        return makeFromMatrix_degenerate_s91e96b_tail3(_t27, _t28, _t29, _t99 * _t72, _t36, _t100 * _t76, _t37, _t100 * _t88, _t33, _t35, _t39, _t101 * _t89, _t34, _t99 * _t75, _t40, _t99 * _t87, _t100 * _t73, _t38, _t41, _t101 * _t74, _t101 * _t77, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_s91e96b_tail3(double _t27, double _t28, double _t29, double _t102, double _t36, double _t105, double _t37, double _t114, double _t33, double _t35, double _t39, double _t115, double _t34, double _t106, double _t40, double _t116, double _t103, double _t38, double _t41, double _t104, double _t107, Double3x4 m) {
        double _t165, _t166;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = 0.0;
                    _t166 = 0.0;
                } else {
                    _t165 = _t102;
                    _t166 = Math.fma(_t33, _t102, -(_t34 * _t106));
                }
            } else {
                _t165 = _t29 <= 0.0 ? Math.fma(_t36, _t105, -(_t37 * _t114)) : Math.fma(_t33, _t36, -(_t35 * _t37));
                _t166 = _t36;
            }
        } else {
            _t165 = _t39;
            _t166 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t115 : Math.fma(_t33, _t39, -(_t34 * _t40)) : _t36;
        }
        return makeFromMatrix_degenerate_s91e96b_tail4(_t27, _t28, _t29, _t116, _t37, _t103, _t38, _t105, _t34, _t33, _t41, _t104, _t35, _t106, _t40, _t39, _t115, _t36, _t114, _t107, _t102, _t166, _t165, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_s91e96b_tail4(double _t27, double _t28, double _t29, double _t116, double _t37, double _t103, double _t38, double _t105, double _t34, double _t33, double _t41, double _t104, double _t35, double _t106, double _t40, double _t39, double _t115, double _t36, double _t114, double _t107, double _t102, double _t166, double _t165, Double3x4 m) {
        double _t167, _t168;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t167 = 1.0;
                    _t168 = 0.0;
                } else {
                    _t167 = _t116;
                    _t168 = Math.fma(_t35, _t106, -(_t33 * _t116));
                }
            } else {
                _t167 = _t29 <= 0.0 ? Math.fma(_t37, _t103, -(_t38 * _t105)) : Math.fma(_t34, _t37, -(_t33 * _t38));
                _t168 = _t38;
            }
        } else {
            _t167 = _t41;
            _t168 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t104 : Math.fma(_t35, _t40, -(_t33 * _t41)) : _t38;
        }
        return makeFromMatrix_degenerate_s91e96b_tail5(_t29, _t27, _t28, _t105, _t39, _t115, _t41, _t104, _t36, _t38, _t33, _t106, _t114, _t103, _t35, _t34, _t40, _t107, _t116, _t102, _t37, _t166, _t167, _t165, _t168, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_s91e96b_tail5(double _t29, double _t27, double _t28, double _t105, double _t39, double _t115, double _t41, double _t104, double _t36, double _t38, double _t33, double _t106, double _t114, double _t103, double _t35, double _t34, double _t40, double _t107, double _t116, double _t102, double _t37, double _t166, double _t167, double _t165, double _t168, Double3x4 m) {
        double _t169, _t170;
        if (_t29 <= 0.0) {
            if (_t27 <= 0.0) {
                if (_t28 <= 0.0) {
                    _t169 = 0.0;
                    _t170 = 0.0;
                } else {
                    _t169 = _t105;
                    _t170 = Math.fma(_t38, _t114, -(_t36 * _t103));
                }
            } else {
                _t169 = _t28 <= 0.0 ? Math.fma(_t39, _t115, -(_t41 * _t104)) : Math.fma(_t39, _t36, -(_t41 * _t38));
                _t170 = _t40;
            }
        } else {
            _t169 = _t33;
            _t170 = _t27 <= 0.0 ? _t28 <= 0.0 ? _t106 : Math.fma(_t35, _t38, -(_t34 * _t36)) : _t40;
        }
        return makeFromMatrix_degenerate_s91e96b_tail6(_t28, _t29, _t27, _t107, _t34, _t116, _t35, _t102, _t41, _t39, _t37, _t114, _t40, _t104, _t38, _t103, _t115, _t36, _t170, _t166, _t167, _t165, _t168, _t169, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_s91e96b_tail6(double _t28, double _t29, double _t27, double _t107, double _t34, double _t116, double _t35, double _t102, double _t41, double _t39, double _t37, double _t114, double _t40, double _t104, double _t38, double _t103, double _t115, double _t36, double _t170, double _t166, double _t167, double _t165, double _t168, double _t169, Double3x4 m) {
        double _t171, _t172;
        if (_t28 <= 0.0) {
            if (_t29 <= 0.0) {
                if (_t27 <= 0.0) {
                    _t171 = 1.0;
                    _t172 = 0.0;
                } else {
                    _t171 = _t107;
                    _t172 = Math.fma(_t40, _t104, -(_t39 * _t107));
                }
            } else {
                _t171 = _t27 <= 0.0 ? Math.fma(_t34, _t116, -(_t35 * _t102)) : Math.fma(_t34, _t41, -(_t35 * _t39));
                _t172 = _t35;
            }
        } else {
            _t171 = _t37;
            _t172 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t114 : Math.fma(_t40, _t38, -(_t39 * _t37)) : _t35;
        }
        return makeFromMatrix_degenerate_s91e96b_tail7(_t29, _t27, _t28, _t103, _t41, _t107, _t40, _t115, _t37, _t36, _t34, _t170, _t166, _t167, _t171, _t165, _t168, _t169, _t172, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_s91e96b_tail7(double _t29, double _t27, double _t28, double _t103, double _t41, double _t107, double _t40, double _t115, double _t37, double _t36, double _t34, double _t170, double _t166, double _t167, double _t171, double _t165, double _t168, double _t169, double _t172, Double3x4 m) {
        double _t173 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t28 <= 0.0 ? 1.0 : _t103 : _t28 <= 0.0 ? Math.fma(_t41, _t107, -(_t40 * _t115)) : Math.fma(_t41, _t37, -(_t40 * _t36)) : _t34;
        double _t194, _t195, _t196;
        if (Math.fma(Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, Math.fma(Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        return makeFromMatrix_degenerate_s91e96b_tail8(_t195, _t165, _t194, _t167, _t171, m, _t170 - _t166, java.lang.Math.max(_t167, _t171), _t195 + _t165, _t196 + _t168, _t168 - _t196, _t170 + _t166);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_s91e96b_tail8(double _t195, double _t165, double _t194, double _t167, double _t171, Double3x4 m, double _t182, double _t183, double _t199, double _t200, double _t201, double _t184) {
        double _t206 = _t194 + _t167 + _t171;
        double _t207 = 1.0 + _t206;
        double _t208 = 1.0 + _t194 - _t167 - _t171;
        double _t209 = 1.0 + _t167 - _t194 - _t171;
        double _t210 = 1.0 + _t171 - _t194 - _t167;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sfx0 = m.m03();
        double _sfx1 = m.m13();
        double _sfx2 = m.m23();
        double _sfx3 = _t206 > 0.0 ? _sp0 * _t182 : _t194 > _t183 ? 0.5 * java.lang.Math.sqrt(_t208) : _t167 > _t171 ? _sp1 * _t199 : _sp2 * _t200;
        return makeFromMatrix_degenerate_s91e5aa_tail9(_t206, _sp0, _t201, _t194, _t183, 0.5 * (1.0 / java.lang.Math.sqrt(_t208)), _t199, _t167, _t171, _t209, _sp2, _t184, _t195 - _t165, _t200, _sp1, _t210, _t207, _t182, _sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static DoubleRigid makeFromMatrix_degenerate(Double3x4 m) {
        double _t0 = unitScale(m.m01(), m.m11(), m.m21());
        double _t1 = unitScale(m.m02(), m.m12(), m.m22());
        double _t2 = unitScale(m.m00(), m.m10(), m.m20());
        double _t12 = m.m21() * _t0;
        double _t13 = m.m01() * _t0;
        double _t14 = m.m11() * _t0;
        double _t15 = m.m22() * _t1;
        double _t16 = m.m02() * _t1;
        double _t17 = m.m12() * _t1;
        double _t18 = m.m20() * _t2;
        double _t19 = m.m00() * _t2;
        double _t20 = m.m10() * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_s91e96b_tail((1.0 / java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0 / java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t30 * _t20, _t27, _t28, _t29, m);
    }


    /**
     * Create the rigid decomposition of the given affine matrix: translation from the last column,
     * rotation from the column-normalized upper-left 3x3 block (scale is removed by normalizing the
     * columns, but shear is not removed: a sheared block yields a rotation quaternion that is not
     * unit length).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeFromMatrix(Double4x4 m) {
        double _ct0 = Math.fma(m.m21(), m.m21(), Math.fma(m.m01(), m.m01(), m.m11() * m.m11()));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t15 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _ct1 = Math.fma(m.m22(), m.m22(), Math.fma(m.m02(), m.m02(), m.m12() * m.m12()));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _ct2 = Math.fma(m.m20(), m.m20(), Math.fma(m.m00(), m.m00(), m.m10() * m.m10()));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        return makeFromMatrix_sf93a4716_1(m, _t15, _t16, _t17, -m.m11(), -m.m22(), m.m10() * _t17, m.m22() * _t16, m.m12() * _t16, m.m20() * _t17, m.m21() * _t15, m.m11() * _t15);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleRigid makeFromMatrix_sf93a4716_1(Double4x4 m, double _t15, double _t16, double _t17, double _t0, double _t1, double _t18, double _t19, double _t20, double _t21, double _t23, double _t24) {
        double _t26 = m.m00() * _t17;
        double _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), m.m01() * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), m.m02() * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        double _t51 = 1.0 + _t47;
        double _t52 = 1.0 - _t47;
        double _t63 = Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t51));
        return makeFromMatrix_sf93a4716_2(m, _t15, _t16, _t19, _t24, Math.fma(m.m12(), _t16, _t23), Math.fma(m.m21(), _t15, -_t20), _t47, Math.fma(m.m01(), _t15, _t48), Math.fma(m.m02(), _t16, _t49), Math.fma(m.m02(), _t16, -_t49), Math.fma(-m.m01(), _t15, _t48), _t63, 0.5 * (1.0 / java.lang.Math.sqrt(_t63)), Math.fma(m.m11(), _t15, Math.fma(_t1, _t16, _t52)), Math.fma(m.m22(), _t16, Math.fma(_t0, _t15, _t52)), Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleRigid makeFromMatrix_sf93a4716_2(Double4x4 m, double _t15, double _t16, double _t19, double _t24, double _t31, double _t35, double _t47, double _t54, double _t55, double _t56, double _t57, double _t63, double _sp0, double _t65, double _t66, double _t67) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t65));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        double _sfx0 = m.m03();
        double _sfx1 = m.m13();
        double _sfx2 = m.m23();
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t47)) > 0.0) {
            _sfx3 = _sp0 * _t35;
            _sfx4 = _sp0 * _t56;
            _sfx5 = _sp0 * _t57;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t63);
        } else {
            if (_t47 > java.lang.Math.max(_t24, _t19)) {
                _sfx3 = 0.5 * java.lang.Math.sqrt(_t67);
                _sfx4 = _sp3 * _t54;
                _sfx5 = _sp3 * _t55;
                _sfx6 = _sp3 * _t35;
            } else {
                if (_t24 > _t19) {
                    _sfx3 = _sp1 * _t54;
                    _sfx4 = 0.5 * java.lang.Math.sqrt(_t65);
                    _sfx5 = _sp1 * _t31;
                    _sfx6 = _sp1 * _t56;
                } else {
                    _sfx3 = _sp2 * _t55;
                    _sfx4 = _sp2 * _t31;
                    _sfx5 = 0.5 * java.lang.Math.sqrt(_t66);
                    _sfx6 = _sp2 * _t57;
                }
            }
        }
        return new DoubleRigid(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_sa000ec_tail(double _t31, double _t17, double _t15, double _t16, double _t32, double _t13, double _t12, double _t14, double _t33, double _t34, double _t35, double _t27, double _t28, double _t29, Double4x4 m) {
        double _t36 = _t31 * _t17;
        double _t37 = _t31 * _t15;
        double _t38 = _t31 * _t16;
        double _t39 = _t32 * _t13;
        double _t40 = _t32 * _t12;
        double _t41 = _t32 * _t14;
        double _t50 = java.lang.Math.abs(_t40);
        double _t51 = java.lang.Math.abs(_t39);
        double _t72, _t75, _t87;
        if (java.lang.Math.abs(_t33) < java.lang.Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0;
            _t87 = -_t34;
        } else {
            _t72 = 0.0;
            _t75 = -_t35;
            _t87 = _t33;
        }
        double _t73, _t76, _t88;
        if (java.lang.Math.abs(_t37) < java.lang.Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0;
            _t88 = -_t38;
        } else {
            _t73 = 0.0;
            _t76 = -_t36;
            _t88 = _t37;
        }
        double _t74, _t77;
        if (_t50 < _t51) {
            _t74 = _t41;
            _t77 = 0.0;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
        }
        return makeFromMatrix_degenerate_sa000ec_tail2(_t50, _t51, _t39, _t40, _t75, _t87, _t72, _t76, _t88, _t73, _t77, _t74, _t27, _t28, _t29, _t36, _t37, _t33, _t35, _t34, _t38, _t41, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_sa000ec_tail2(double _t50, double _t51, double _t39, double _t40, double _t75, double _t87, double _t72, double _t76, double _t88, double _t73, double _t77, double _t74, double _t27, double _t28, double _t29, double _t36, double _t37, double _t33, double _t35, double _t34, double _t38, double _t41, Double4x4 m) {
        double _t89 = _t50 < _t51 ? -_t39 : _t40;
        double _t99 = (1.0 / java.lang.Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / java.lang.Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / java.lang.Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        return makeFromMatrix_degenerate_sa000ec_tail3(_t27, _t28, _t29, _t99 * _t72, _t36, _t100 * _t76, _t37, _t100 * _t88, _t33, _t35, _t39, _t101 * _t89, _t34, _t99 * _t75, _t40, _t99 * _t87, _t100 * _t73, _t38, _t41, _t101 * _t74, _t101 * _t77, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_sa000ec_tail3(double _t27, double _t28, double _t29, double _t102, double _t36, double _t105, double _t37, double _t114, double _t33, double _t35, double _t39, double _t115, double _t34, double _t106, double _t40, double _t116, double _t103, double _t38, double _t41, double _t104, double _t107, Double4x4 m) {
        double _t165, _t166;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = 0.0;
                    _t166 = 0.0;
                } else {
                    _t165 = _t102;
                    _t166 = Math.fma(_t33, _t102, -(_t34 * _t106));
                }
            } else {
                _t165 = _t29 <= 0.0 ? Math.fma(_t36, _t105, -(_t37 * _t114)) : Math.fma(_t33, _t36, -(_t35 * _t37));
                _t166 = _t36;
            }
        } else {
            _t165 = _t39;
            _t166 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t115 : Math.fma(_t33, _t39, -(_t34 * _t40)) : _t36;
        }
        return makeFromMatrix_degenerate_sa000ec_tail4(_t27, _t28, _t29, _t116, _t37, _t103, _t38, _t105, _t34, _t33, _t41, _t104, _t35, _t106, _t40, _t39, _t115, _t36, _t114, _t107, _t102, _t166, _t165, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_sa000ec_tail4(double _t27, double _t28, double _t29, double _t116, double _t37, double _t103, double _t38, double _t105, double _t34, double _t33, double _t41, double _t104, double _t35, double _t106, double _t40, double _t39, double _t115, double _t36, double _t114, double _t107, double _t102, double _t166, double _t165, Double4x4 m) {
        double _t167, _t168;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t167 = 1.0;
                    _t168 = 0.0;
                } else {
                    _t167 = _t116;
                    _t168 = Math.fma(_t35, _t106, -(_t33 * _t116));
                }
            } else {
                _t167 = _t29 <= 0.0 ? Math.fma(_t37, _t103, -(_t38 * _t105)) : Math.fma(_t34, _t37, -(_t33 * _t38));
                _t168 = _t38;
            }
        } else {
            _t167 = _t41;
            _t168 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t104 : Math.fma(_t35, _t40, -(_t33 * _t41)) : _t38;
        }
        return makeFromMatrix_degenerate_sa000ec_tail5(_t29, _t27, _t28, _t105, _t39, _t115, _t41, _t104, _t36, _t38, _t33, _t106, _t114, _t103, _t35, _t34, _t40, _t107, _t116, _t102, _t37, _t166, _t167, _t165, _t168, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_sa000ec_tail5(double _t29, double _t27, double _t28, double _t105, double _t39, double _t115, double _t41, double _t104, double _t36, double _t38, double _t33, double _t106, double _t114, double _t103, double _t35, double _t34, double _t40, double _t107, double _t116, double _t102, double _t37, double _t166, double _t167, double _t165, double _t168, Double4x4 m) {
        double _t169, _t170;
        if (_t29 <= 0.0) {
            if (_t27 <= 0.0) {
                if (_t28 <= 0.0) {
                    _t169 = 0.0;
                    _t170 = 0.0;
                } else {
                    _t169 = _t105;
                    _t170 = Math.fma(_t38, _t114, -(_t36 * _t103));
                }
            } else {
                _t169 = _t28 <= 0.0 ? Math.fma(_t39, _t115, -(_t41 * _t104)) : Math.fma(_t39, _t36, -(_t41 * _t38));
                _t170 = _t40;
            }
        } else {
            _t169 = _t33;
            _t170 = _t27 <= 0.0 ? _t28 <= 0.0 ? _t106 : Math.fma(_t35, _t38, -(_t34 * _t36)) : _t40;
        }
        return makeFromMatrix_degenerate_sa000ec_tail6(_t28, _t29, _t27, _t107, _t34, _t116, _t35, _t102, _t41, _t39, _t37, _t114, _t40, _t104, _t38, _t103, _t115, _t36, _t170, _t166, _t167, _t165, _t168, _t169, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_sa000ec_tail6(double _t28, double _t29, double _t27, double _t107, double _t34, double _t116, double _t35, double _t102, double _t41, double _t39, double _t37, double _t114, double _t40, double _t104, double _t38, double _t103, double _t115, double _t36, double _t170, double _t166, double _t167, double _t165, double _t168, double _t169, Double4x4 m) {
        double _t171, _t172;
        if (_t28 <= 0.0) {
            if (_t29 <= 0.0) {
                if (_t27 <= 0.0) {
                    _t171 = 1.0;
                    _t172 = 0.0;
                } else {
                    _t171 = _t107;
                    _t172 = Math.fma(_t40, _t104, -(_t39 * _t107));
                }
            } else {
                _t171 = _t27 <= 0.0 ? Math.fma(_t34, _t116, -(_t35 * _t102)) : Math.fma(_t34, _t41, -(_t35 * _t39));
                _t172 = _t35;
            }
        } else {
            _t171 = _t37;
            _t172 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t114 : Math.fma(_t40, _t38, -(_t39 * _t37)) : _t35;
        }
        return makeFromMatrix_degenerate_sa000ec_tail7(_t29, _t27, _t28, _t103, _t41, _t107, _t40, _t115, _t37, _t36, _t34, _t170, _t166, _t167, _t171, _t165, _t168, _t169, _t172, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_sa000ec_tail7(double _t29, double _t27, double _t28, double _t103, double _t41, double _t107, double _t40, double _t115, double _t37, double _t36, double _t34, double _t170, double _t166, double _t167, double _t171, double _t165, double _t168, double _t169, double _t172, Double4x4 m) {
        double _t173 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t28 <= 0.0 ? 1.0 : _t103 : _t28 <= 0.0 ? Math.fma(_t41, _t107, -(_t40 * _t115)) : Math.fma(_t41, _t37, -(_t40 * _t36)) : _t34;
        double _t194, _t195, _t196;
        if (Math.fma(Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, Math.fma(Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        return makeFromMatrix_degenerate_sa000ec_tail8(_t195, _t165, _t194, _t167, _t171, m, _t170 - _t166, java.lang.Math.max(_t167, _t171), _t195 + _t165, _t196 + _t168, _t168 - _t196, _t170 + _t166);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleRigid makeFromMatrix_degenerate_sa000ec_tail8(double _t195, double _t165, double _t194, double _t167, double _t171, Double4x4 m, double _t182, double _t183, double _t199, double _t200, double _t201, double _t184) {
        double _t206 = _t194 + _t167 + _t171;
        double _t207 = 1.0 + _t206;
        double _t208 = 1.0 + _t194 - _t167 - _t171;
        double _t209 = 1.0 + _t167 - _t194 - _t171;
        double _t210 = 1.0 + _t171 - _t194 - _t167;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sfx0 = m.m03();
        double _sfx1 = m.m13();
        double _sfx2 = m.m23();
        double _sfx3 = _t206 > 0.0 ? _sp0 * _t182 : _t194 > _t183 ? 0.5 * java.lang.Math.sqrt(_t208) : _t167 > _t171 ? _sp1 * _t199 : _sp2 * _t200;
        return makeFromMatrix_degenerate_s91e5aa_tail9(_t206, _sp0, _t201, _t194, _t183, 0.5 * (1.0 / java.lang.Math.sqrt(_t208)), _t199, _t167, _t171, _t209, _sp2, _t184, _t195 - _t165, _t200, _sp1, _t210, _t207, _t182, _sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static DoubleRigid makeFromMatrix_degenerate(Double4x4 m) {
        double _t0 = unitScale(m.m01(), m.m11(), m.m21());
        double _t1 = unitScale(m.m02(), m.m12(), m.m22());
        double _t2 = unitScale(m.m00(), m.m10(), m.m20());
        double _t12 = m.m21() * _t0;
        double _t13 = m.m01() * _t0;
        double _t14 = m.m11() * _t0;
        double _t15 = m.m22() * _t1;
        double _t16 = m.m02() * _t1;
        double _t17 = m.m12() * _t1;
        double _t18 = m.m20() * _t2;
        double _t19 = m.m00() * _t2;
        double _t20 = m.m10() * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_sa000ec_tail((1.0 / java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0 / java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t30 * _t20, _t27, _t28, _t29, m);
    }


    /**
     * Create the rigid motion (rotation and translation) of the given transform; the scale is
     * dropped (a rigid transform cannot represent it).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the transform to convert
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeFromTransform(DoubleTransform t) {
        double tTX = t.tX();
        double tTY = t.tY();
        double tTZ = t.tZ();
        double tRX = t.rX();
        double tRY = t.rY();
        double tRZ = t.rZ();
        double tRW = t.rW();
        return new DoubleRigid(tTX, tTY, tTZ, tRX, tRY, tRZ, tRW);
    }


    /**
     * Create the rigid motion (rotation and translation) of the given transform; the scale is
     * dropped (a rigid transform cannot represent it).
     * <p>
     * Valid input: the default range of the package documentation.
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
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeFromTransform(double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        return new DoubleRigid(tTX, tTY, tTZ, tRX, tRY, tRZ, tRW);
    }


    /**
     * Convert this rigid transform to {@code float} precision, returning the result as a new
     * instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code FloatRigid} holding the result
     */
    public FloatRigid toFloat() {
        return new FloatRigid((float) (this.tX), (float) (this.tY), (float) (this.tZ), (float) (this.rX), (float) (this.rY), (float) (this.rZ), (float) (this.rW));
    }


    /**
     * Convert this rigid transform to a unit dual quaternion encoding the same rigid motion (an
     * exact conversion), returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @return the resulting dual quaternion
     */
    public DoubleDualQuat toDualQuat() {
        double _t0 = -this.tZ;
        return new DoubleDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5 * Math.fma(_t0, this.rY, Math.fma(this.tX, this.rW, this.tY * this.rZ)), 0.5 * Math.fma(this.tZ, this.rX, Math.fma(this.tY, this.rW, -(this.tX * this.rZ))), 0.5 * Math.fma(this.tZ, this.rW, Math.fma(this.tX, this.rY, -(this.tY * this.rX))), 0.5 * Math.fma(_t0, this.rZ, Math.fma(-this.tY, this.rY, -(this.tX * this.rX))));
    }


    /**
     * Compute the matrix representation of this rigid transform, returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @return the resulting matrix
     */
    public Double4x4 toMatrix() {
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rZ * this.rW;
        double _t2 = this.rY * this.rW;
        return new Double4x4(Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0), 2.0 * Math.fma(this.rX, this.rY, -_t1), 2.0 * Math.fma(this.rX, this.rZ, _t2), this.tX, 2.0 * Math.fma(this.rX, this.rY, _t1), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0), 2.0 * Math.fma(this.rY, this.rZ, -(this.rX * this.rW)), this.tY, 2.0 * Math.fma(this.rX, this.rZ, -_t2), 2.0 * Math.fma(this.rX, this.rW, this.rY * this.rZ), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0), this.tZ, 0.0, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Compute the 3x3 matrix representation of the rotation of this rigid transform (the
     * translation is dropped), returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @return the resulting matrix
     */
    public Double3x3 toMatrix3x3() {
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rZ * this.rW;
        double _t2 = this.rY * this.rW;
        return new Double3x3(Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0), 2.0 * Math.fma(this.rX, this.rY, -_t1), 2.0 * Math.fma(this.rX, this.rZ, _t2), 2.0 * Math.fma(this.rX, this.rY, _t1), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0), 2.0 * Math.fma(this.rY, this.rZ, -(this.rX * this.rW)), 2.0 * Math.fma(this.rX, this.rZ, -_t2), 2.0 * Math.fma(this.rX, this.rW, this.rY * this.rZ), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0), 0);
    }


    /**
     * Compute the 3x4 matrix representation of this rigid transform (the omitted last row is
     * implicitly {@code 0, 0, 0, 1}), returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @return the resulting matrix
     */
    public Double3x4 toMatrix3x4() {
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rZ * this.rW;
        double _t2 = this.rY * this.rW;
        return new Double3x4(Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0), 2.0 * Math.fma(this.rX, this.rY, -_t1), 2.0 * Math.fma(this.rX, this.rZ, _t2), this.tX, 2.0 * Math.fma(this.rX, this.rY, _t1), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0), 2.0 * Math.fma(this.rY, this.rZ, -(this.rX * this.rW)), this.tY, 2.0 * Math.fma(this.rX, this.rZ, -_t2), 2.0 * Math.fma(this.rX, this.rW, this.rY * this.rZ), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0), this.tZ, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Widen this rigid transform to a TRS transform (same translation and rotation, scale = 1),
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting transform
     */
    public DoubleTransform toTransform() {
        return new DoubleTransform(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW, 1.0, 1.0, 1.0);
    }


    /**
     * Create an identity rigid transform.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeIdentity() {
        return new DoubleRigid(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0);
    }


    /**
     * Create a new rigid transform representing a pure rotation by {@code rotation} (zero
     * translation).
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @return the resulting rigid transform
     */
    public DoubleRigid set(DoubleQuat rotation) {
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        return new DoubleRigid(0.0, 0.0, 0.0, rotationX, rotationY, rotationZ, rotationW);
    }


    /**
     * Create a new rigid transform representing a pure rotation by ({@code rotationX},
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
     * @return the resulting rigid transform
     */
    public DoubleRigid set(double rotationX, double rotationY, double rotationZ, double rotationW) {
        return new DoubleRigid(0.0, 0.0, 0.0, rotationX, rotationY, rotationZ, rotationW);
    }


    /**
     * Create a new rigid transform representing a pure rotation by {@code rotation} (zero
     * translation).
     * <p>
     * Alias for {@code set}.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeRotation(DoubleQuat rotation) {
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        return new DoubleRigid(0.0, 0.0, 0.0, rotationX, rotationY, rotationZ, rotationW);
    }


    /**
     * Create a new rigid transform representing a pure rotation by ({@code rotationX},
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
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeRotation(double rotationX, double rotationY, double rotationZ, double rotationW) {
        return new DoubleRigid(0.0, 0.0, 0.0, rotationX, rotationY, rotationZ, rotationW);
    }


    /**
     * Create a new rigid transform representing a pure translation by {@code translation} (identity
     * rotation).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation
     * @return the resulting rigid transform
     */
    public DoubleRigid set(Double3 translation) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        return new DoubleRigid(translationX, translationY, translationZ, 0.0, 0.0, 0.0, 1.0);
    }


    /**
     * Create a new rigid transform representing a pure translation by ({@code translationX},
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
     * @return the resulting rigid transform
     */
    public DoubleRigid set(double translationX, double translationY, double translationZ) {
        return new DoubleRigid(translationX, translationY, translationZ, 0.0, 0.0, 0.0, 1.0);
    }


    /**
     * Create a new rigid transform representing a pure translation by {@code translation} (identity
     * rotation).
     * <p>
     * Alias for {@code set}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeTranslation(Double3 translation) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        return new DoubleRigid(translationX, translationY, translationZ, 0.0, 0.0, 0.0, 1.0);
    }


    /**
     * Create a new rigid transform representing a pure translation by ({@code translationX},
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
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeTranslation(double translationX, double translationY, double translationZ) {
        return new DoubleRigid(translationX, translationY, translationZ, 0.0, 0.0, 0.0, 1.0);
    }


    /**
     * Interpolate between this rigid transform and {@code other} using the interpolation factor
     * {@code t}, interpolating the translation linearly and the rotation via shortest-arc slerp,
     * returning the result as a value.
     * <p>
     * The interpolation starts at this rigid transform (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length; the rotation of
     * {@code other} must have unit length.
     *
     * @param other the rigid transform to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting rigid transform
     */
    public DoubleRigid lerp(DoubleRigid other, double t) {
        double otherTX = other.tX();
        double otherTY = other.tY();
        double otherTZ = other.tZ();
        double otherRX = other.rX();
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        double _t0 = 1.0 - t;
        double _t12 = Math.fma(otherRW, this.rW, Math.fma(otherRZ, this.rZ, Math.fma(otherRX, this.rX, otherRY * this.rY)));
        double _t16 = Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t12)));
        double _t17 = Math.sin(_t16);
        double _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0) {
            _t21 = -otherRW;
            _t22 = -otherRZ;
            _t23 = -otherRX;
            _t24 = -otherRY;
        } else {
            _t21 = otherRW;
            _t22 = otherRZ;
            _t23 = otherRX;
            _t24 = otherRY;
        }
        return lerp_s2e31ef01_tail(_t17, Math.sin(_t0 * _t16), Math.sin(t * _t16), _t21, 1.0 / _t17, t, _t0, _t22, _t23, _t24, otherTX, otherTY, otherTZ);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private DoubleRigid lerp_s2e31ef01_tail(double _t17, double _t25, double _t19, double _t21, double _t17_inv, double t, double _t0, double _t22, double _t23, double _t24, double otherTX, double otherTY, double otherTZ) {
        double _t42, _t43, _t44, _t45;
        if (_t17 > 0.0) {
            _t42 = Math.fma(this.rW, _t25, _t19 * _t21) * _t17_inv;
            _t43 = Math.fma(this.rZ, _t25, _t19 * _t22) * _t17_inv;
            _t44 = Math.fma(this.rX, _t25, _t19 * _t23) * _t17_inv;
            _t45 = Math.fma(this.rY, _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = Math.fma(t, _t21, this.rW * _t0);
            _t43 = Math.fma(t, _t22, this.rZ * _t0);
            _t44 = Math.fma(t, _t23, this.rX * _t0);
            _t45 = Math.fma(t, _t24, this.rY * _t0);
        }
        double _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        double _sfx0 = Math.fma(t, otherTX - this.tX, this.tX);
        return lerp_s2e31ef01_tail2(t, otherTY, otherTZ, _t49, (1.0 / java.lang.Math.sqrt(_t49)), _t44, _t45, _t43, _t42, _sfx0);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private DoubleRigid lerp_s2e31ef01_tail2(double t, double otherTY, double otherTZ, double _t49, double _t50, double _t44, double _t45, double _t43, double _t42, double _sfx0) {
        double _sfx1 = Math.fma(t, otherTY - this.tY, this.tY);
        double _sfx2 = Math.fma(t, otherTZ - this.tZ, this.tZ);
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (_t49 != 0.0) {
            _sfx3 = _t50 * _t44;
            _sfx4 = _t50 * _t45;
            _sfx5 = _t50 * _t43;
            _sfx6 = _t50 * _t42;
        } else {
            _sfx3 = 0.0;
            _sfx4 = 0.0;
            _sfx5 = 0.0;
            _sfx6 = 0.0;
        }
        return new DoubleRigid(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6);
    }


    /**
     * Interpolate between this rigid transform and ({@code otherTX}, {@code otherTY},
     * {@code otherTZ}, {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}) using
     * the interpolation factor {@code t}, interpolating the translation linearly and the rotation
     * via shortest-arc slerp, returning the result as a value.
     * <p>
     * The interpolation starts at this rigid transform (interpolation factor {@code 0}) and ends at
     * ({@code otherTX}, {@code otherTY}, {@code otherTZ}, {@code otherRX}, {@code otherRY},
     * {@code otherRZ}, {@code otherRW}) (interpolation factor {@code 1}). Each linearly
     * interpolated component is {@code this + (other - this) * t}, as in JOML and glMatrix:
     * monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the rounding
     * of {@code other - this}, which shows when this component is much larger in magnitude than the
     * other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length;
     * {@code (otherRX, otherRY, otherRZ, otherRW)} must have unit length.
     *
     * @param otherTX the {@code tX} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherTY the {@code tY} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherTZ the {@code tZ} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherRX the {@code rX} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherRY the {@code rY} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherRZ the {@code rZ} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherRW the {@code rW} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting rigid transform
     */
    public DoubleRigid lerp(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double t) {
        double _t0 = 1.0 - t;
        double _t12 = Math.fma(otherRW, this.rW, Math.fma(otherRZ, this.rZ, Math.fma(otherRX, this.rX, otherRY * this.rY)));
        double _t16 = Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t12)));
        double _t17 = Math.sin(_t16);
        double _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0) {
            _t21 = -otherRW;
            _t22 = -otherRZ;
            _t23 = -otherRX;
            _t24 = -otherRY;
        } else {
            _t21 = otherRW;
            _t22 = otherRZ;
            _t23 = otherRX;
            _t24 = otherRY;
        }
        return lerp_s2e31ef01_tail(_t17, Math.sin(_t0 * _t16), Math.sin(t * _t16), _t21, 1.0 / _t17, t, _t0, _t22, _t23, _t24, otherTX, otherTY, otherTZ);
    }


    /**
     * Multiply this rigid transform by {@code other}, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the operand, then the new rigid
     * transform will be {@code M * R}. So when transforming a vector {@code v} with the new rigid
     * transform by using {@code M * R * v}, the transformation of the operand will be applied
     * first.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length; the rotation of
     * {@code other} must have unit length.
     *
     * @param other the right operand
     * @return the resulting rigid transform
     */
    public DoubleRigid mul(DoubleRigid other) {
        return mul(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW());
    }

    /** Private tail of {@code mul}; reached only through it. */
    private DoubleRigid mul_s566a3fa8_tail(double otherTZ, double otherTY, double _t9, double _t10, double otherTX, double otherRX, double otherRW, double otherRZ, double otherRY) {
        double _t11 = 2.0 * Math.fma(otherTZ, this.rY, -(otherTY * this.rZ));
        return new DoubleRigid(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + otherTX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + otherTY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + otherTZ))), Math.fma(otherRX, this.rW, otherRW * this.rX) + Math.fma(otherRZ, this.rY, -(otherRY * this.rZ)), Math.fma(otherRX, this.rZ, otherRW * this.rY) + Math.fma(otherRY, this.rW, -(otherRZ * this.rX)), Math.fma(otherRY, this.rX, otherRZ * this.rW) + Math.fma(otherRW, this.rZ, -(otherRX * this.rY)), Math.fma(otherRW, this.rW, -(otherRX * this.rX)) - Math.fma(otherRY, this.rY, otherRZ * this.rZ));
    }


    /**
     * Multiply this rigid transform by ({@code otherTX}, {@code otherTY}, {@code otherTZ},
     * {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}), returning the result as
     * a value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the operand, then the new rigid
     * transform will be {@code M * R}. So when transforming a vector {@code v} with the new rigid
     * transform by using {@code M * R * v}, the transformation of the operand will be applied
     * first.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length;
     * {@code (otherRX, otherRY, otherRZ, otherRW)} must have unit length.
     *
     * @param otherTX the {@code tX} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherTY the {@code tY} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherTZ the {@code tZ} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherRX the {@code rX} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherRY the {@code rY} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherRZ the {@code rZ} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherRW the {@code rW} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @return the resulting rigid transform
     */
    public DoubleRigid mul(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW) {
        return mul_s566a3fa8_tail(otherTZ, otherTY, 2.0 * Math.fma(otherTY, this.rX, -(otherTX * this.rY)), 2.0 * Math.fma(otherTX, this.rZ, -(otherTZ * this.rX)), otherTX, otherRX, otherRW, otherRZ, otherRY);
    }


    /**
     * Pre-multiply {@code other} onto this rigid transform, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the operand, then the new rigid
     * transform will be {@code R * M}. So when transforming a vector {@code v} with the new rigid
     * transform by using {@code R * M * v}, the transformation of the operand will be applied last.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length; the rotation of
     * {@code other} must have unit length.
     *
     * @param other the left operand
     * @return the resulting rigid transform
     */
    public DoubleRigid preMul(DoubleRigid other) {
        return preMul(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW());
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private DoubleRigid preMul_s566a3fa8_tail(double otherRY, double otherRZ, double _t9, double _t10, double otherRW, double otherTX, double otherRX, double otherTY, double otherTZ) {
        double _t11 = 2.0 * Math.fma(otherRY, this.tZ, -(otherRZ * this.tY));
        return new DoubleRigid(Math.fma(otherRY, _t9, Math.fma(-otherRZ, _t10, Math.fma(otherRW, _t11, otherTX + this.tX))), Math.fma(otherRZ, _t11, Math.fma(-otherRX, _t9, Math.fma(otherRW, _t10, otherTY + this.tY))), Math.fma(otherRX, _t10, Math.fma(-otherRY, _t11, Math.fma(otherRW, _t9, otherTZ + this.tZ))), Math.fma(otherRX, this.rW, otherRW * this.rX) + Math.fma(otherRY, this.rZ, -(otherRZ * this.rY)), Math.fma(otherRY, this.rW, otherRZ * this.rX) + Math.fma(otherRW, this.rY, -(otherRX * this.rZ)), Math.fma(otherRX, this.rY, otherRW * this.rZ) + Math.fma(otherRZ, this.rW, -(otherRY * this.rX)), Math.fma(otherRW, this.rW, -(otherRX * this.rX)) - Math.fma(otherRY, this.rY, otherRZ * this.rZ));
    }


    /**
     * Pre-multiply ({@code otherTX}, {@code otherTY}, {@code otherTZ}, {@code otherRX},
     * {@code otherRY}, {@code otherRZ}, {@code otherRW}) onto this rigid transform, returning the
     * result as a value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the operand, then the new rigid
     * transform will be {@code R * M}. So when transforming a vector {@code v} with the new rigid
     * transform by using {@code R * M * v}, the transformation of the operand will be applied last.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length;
     * {@code (otherRX, otherRY, otherRZ, otherRW)} must have unit length.
     *
     * @param otherTX the {@code tX} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherTY the {@code tY} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherTZ the {@code tZ} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherRX the {@code rX} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherRY the {@code rY} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherRZ the {@code rZ} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherRW the {@code rW} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @return the resulting rigid transform
     */
    public DoubleRigid preMul(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW) {
        return preMul_s566a3fa8_tail(otherRY, otherRZ, 2.0 * Math.fma(otherRX, this.tY, -(otherRY * this.tX)), 2.0 * Math.fma(otherRZ, this.tX, -(otherRX * this.tZ)), otherRW, otherTX, otherRX, otherTY, otherTZ);
    }


    /**
     * Compute the difference between this rigid transform and {@code other}, i.e. the rigid
     * transformation {@code D} with {@code this * D = other}, that is {@code D = this^-1 * other},
     * returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param other the target rigid transform, reached by composing this rigid transform with the
     *        result
     * @return the resulting rigid transform
     */
    public DoubleRigid difference(DoubleRigid other) {
        double otherTX = other.tX();
        double otherTY = other.tY();
        double otherTZ = other.tZ();
        double otherRX = other.rX();
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        double _t18 = 2.0 * Math.fma(otherTZ, this.rX, -(otherTX * this.rZ));
        double _t19 = 2.0 * Math.fma(otherTY, this.rZ, -(otherTZ * this.rY));
        double _t20 = 2.0 * Math.fma(otherTX, this.rY, -(otherTY * this.rX));
        double _t21 = 2.0 * Math.fma(this.tX, this.rZ, -(this.tZ * this.rX));
        double _t22 = 2.0 * Math.fma(this.tY, this.rX, -(this.tX * this.rY));
        double _t23 = 2.0 * Math.fma(this.tZ, this.rY, -(this.tY * this.rZ));
        double _sfx0 = Math.fma(this.rZ, _t18, otherTX) + Math.fma(this.rW, _t19, -(this.rY * _t20)) + (Math.fma(this.rZ, _t21, -(this.rY * _t22)) + Math.fma(this.rW, _t23, -this.tX));
        return difference_s566a3fa8_tail(_t20, otherTY, _t18, _t19, _t22, _t23, _t21, otherTZ, otherRX, otherRW, otherRY, otherRZ, _sfx0);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private DoubleRigid difference_s566a3fa8_tail(double _t20, double otherTY, double _t18, double _t19, double _t22, double _t23, double _t21, double otherTZ, double otherRX, double otherRW, double otherRY, double otherRZ, double _sfx0) {
        double _sfx1 = Math.fma(this.rX, _t20, otherTY) + Math.fma(this.rW, _t18, -(this.rZ * _t19)) + (Math.fma(this.rX, _t22, -(this.rZ * _t23)) + Math.fma(this.rW, _t21, -this.tY));
        double _sfx2 = Math.fma(this.rY, _t19, otherTZ) + Math.fma(this.rW, _t20, -(this.rX * _t18)) + (Math.fma(this.rY, _t23, -(this.rX * _t21)) + Math.fma(this.rW, _t22, -this.tZ));
        double _sfx3 = Math.fma(otherRX, this.rW, -(otherRW * this.rX)) + Math.fma(otherRY, this.rZ, -(otherRZ * this.rY));
        double _sfx4 = Math.fma(otherRY, this.rW, otherRZ * this.rX) + Math.fma(-otherRX, this.rZ, -(otherRW * this.rY));
        return difference_s566a3fa8_tail2(otherRX, otherRW, otherRZ, otherRY, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private DoubleRigid difference_s566a3fa8_tail2(double otherRX, double otherRW, double otherRZ, double otherRY, double _sfx0, double _sfx1, double _sfx2, double _sfx3, double _sfx4) {
        double _sfx5 = Math.fma(otherRX, this.rY, -(otherRW * this.rZ)) + Math.fma(otherRZ, this.rW, -(otherRY * this.rX));
        double _sfx6 = Math.fma(otherRX, this.rX, otherRW * this.rW) - Math.fma(-otherRZ, this.rZ, -(otherRY * this.rY));
        return new DoubleRigid(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6);
    }


    /**
     * Compute the difference between this rigid transform and ({@code otherTX}, {@code otherTY},
     * {@code otherTZ}, {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}), i.e.
     * the rigid transformation {@code D} with
     * {@code this * D = (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}, that is
     * {@code D = this^-1 * (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)},
     * returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param otherTX the {@code tX} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherTY the {@code tY} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherTZ the {@code tZ} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherRX the {@code rX} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherRY the {@code rY} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherRZ the {@code rZ} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @param otherRW the {@code rW} component of the rigid transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}
     * @return the resulting rigid transform
     */
    public DoubleRigid difference(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW) {
        double _t18 = 2.0 * Math.fma(otherTZ, this.rX, -(otherTX * this.rZ));
        double _t19 = 2.0 * Math.fma(otherTY, this.rZ, -(otherTZ * this.rY));
        double _t20 = 2.0 * Math.fma(otherTX, this.rY, -(otherTY * this.rX));
        double _t21 = 2.0 * Math.fma(this.tX, this.rZ, -(this.tZ * this.rX));
        double _t22 = 2.0 * Math.fma(this.tY, this.rX, -(this.tX * this.rY));
        double _t23 = 2.0 * Math.fma(this.tZ, this.rY, -(this.tY * this.rZ));
        double _sfx0 = Math.fma(this.rZ, _t18, otherTX) + Math.fma(this.rW, _t19, -(this.rY * _t20)) + (Math.fma(this.rZ, _t21, -(this.rY * _t22)) + Math.fma(this.rW, _t23, -this.tX));
        return difference_s566a3fa8_tail(_t20, otherTY, _t18, _t19, _t22, _t23, _t21, otherTZ, otherRX, otherRW, otherRY, otherRZ, _sfx0);
    }


    /**
     * Invert this rigid transform; exact for any rigid motion (no scale divisions), returning the
     * result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @return the resulting rigid transform
     */
    public DoubleRigid invert() {
        double _t0 = -this.rY;
        double _t1 = -this.rZ;
        double _t2 = -this.rX;
        double _t12 = 2.0 * Math.fma(this.tX, this.rZ, -(this.tZ * this.rX));
        double _t13 = 2.0 * Math.fma(this.tY, this.rX, -(this.tX * this.rY));
        double _t14 = 2.0 * Math.fma(this.tZ, this.rY, -(this.tY * this.rZ));
        return new DoubleRigid(Math.fma(this.rZ, _t12, Math.fma(_t0, _t13, Math.fma(this.rW, _t14, -this.tX))), Math.fma(this.rX, _t13, Math.fma(_t1, _t14, Math.fma(this.rW, _t12, -this.tY))), Math.fma(this.rY, _t14, Math.fma(_t2, _t12, Math.fma(this.rW, _t13, -this.tZ))), _t2, _t0, _t1, this.rW);
    }


    /**
     * Normalize this rigid transform so that its rotation part has unit length, leaving its
     * translation unchanged (a zero-length rotation yields the zero quaternion), returning the
     * result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting rigid transform
     */
    public DoubleRigid normalize() {
        double _t3 = Math.fma(this.rW, this.rW, Math.fma(this.rZ, this.rZ, Math.fma(this.rX, this.rX, this.rY * this.rY)));
        double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0) {
            return new DoubleRigid(this.tX, this.tY, this.tZ, this.rX * _t4, this.rY * _t4, this.rZ * _t4, this.rW * _t4);
        } else {
            return new DoubleRigid(this.tX, this.tY, this.tZ, 0.0, 0.0, 0.0, 0.0);
        }
    }


    /**
     * Get the Euler angles in radians of this rigid transform, to be applied about the X, Y and Z
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesXYZ() {
        double _t1 = this.rY * this.rZ;
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        double _t9 = 2.0 * Math.fma(this.rX, this.rW, -_t1);
        double _t10 = Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-15) {
            return new Double3(Math.atan2(2.0 * Math.fma(this.rX, this.rW, _t1), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t3), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, _t10), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0, Math.fma(this.rY, this.rY, _t3), 1.0)));
        }
    }


    /**
     * Get the Euler angles in radians of this rigid transform, to be applied about the X, Z and Y
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesXZY() {
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rY * this.rZ;
        double _t7 = 2.0 * Math.fma(this.rX, this.rW, _t1);
        double _t8 = 2.0 * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        double _t9 = Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            return new Double3(Math.atan2(2.0 * Math.fma(this.rX, this.rW, -_t1), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), 0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(_t7, _t9), Math.atan2(2.0 * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        }
    }


    /**
     * Get the Euler angles in radians of this rigid transform, to be applied about the Y, X and Z
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesYXZ() {
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        double _t9 = 2.0 * Math.fma(this.rX, this.rW, -(this.rY * this.rZ));
        double _t10 = Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-15) {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0, Math.fma(this.rY, this.rY, _t3), 1.0)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(_t8, _t10), Math.atan2(2.0 * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t3), 1.0)));
        }
    }


    /**
     * Get the Euler angles in radians of this rigid transform, to be applied about the Y, Z and X
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesYZX() {
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        double _t8 = 2.0 * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        double _t9 = Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(2.0 * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(2.0 * Math.fma(this.rX, this.rW, -(this.rY * this.rZ)), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0)), Math.atan2(_t8, _t9), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        }
    }


    /**
     * Get the Euler angles in radians of this rigid transform, to be applied about the Z, X and Y
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesZXY() {
        double _t1 = this.rZ * this.rZ;
        double _t7 = 2.0 * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        double _t8 = 2.0 * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        double _t9 = Math.fma(-2.0, Math.fma(this.rX, this.rX, _t1), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), 0.0, Math.atan2(2.0 * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0, Math.fma(this.rY, this.rY, _t1), 1.0)));
        } else {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t8, _t9));
        }
    }


    /**
     * Get the Euler angles in radians of this rigid transform, to be applied about the Z, Y and X
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesZYX() {
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        double _t8 = 2.0 * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        double _t9 = Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0)));
        } else {
            return new Double3(Math.atan2(2.0 * Math.fma(this.rX, this.rW, this.rY * this.rZ), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(_t7, _t9));
        }
    }


    /**
     * Get the rotation of this rigid transform, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting quaternion
     */
    public DoubleQuat getRotation() {
        return new DoubleQuat(this.rX, this.rY, this.rZ, this.rW);
    }


    /**
     * Get the translation of this rigid transform, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getTranslation() {
        return new Double3(this.tX, this.tY, this.tZ);
    }


    /**
     * Create a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeRotationAxis(double angle, Double3 axis) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleRigid(0.0, 0.0, 0.0, axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0));
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
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeRotationAxis(double angle, double axisX, double axisY, double axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleRigid(0.0, 0.0, 0.0, axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0));
    }


    /**
     * Create a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeRotationX(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleRigid(0.0, 0.0, 0.0, _t1, 0.0, 0.0, Math.cosFromSin(_t1, _t0));
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
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeRotationXYZ(double angleX, double angleY, double angleZ) {
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
        return new DoubleRigid(0.0, 0.0, 0.0, Math.fma(_t10, _t7, _t11 * _t5), Math.fma(_t11, _t7, -(_t10 * _t5)), Math.fma(_t9, _t7, _t12 * _t5), Math.fma(_t12, _t7, -(_t9 * _t5)));
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
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeRotationXZY(double angleX, double angleZ, double angleY) {
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
        return new DoubleRigid(0.0, 0.0, 0.0, Math.fma(_t10, _t7, -(_t11 * _t5)), Math.fma(_t12, _t5, -(_t9 * _t7)), Math.fma(_t10, _t5, _t11 * _t7), Math.fma(_t9, _t5, _t12 * _t7));
    }


    /**
     * Create a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeRotationY(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleRigid(0.0, 0.0, 0.0, 0.0, _t1, 0.0, Math.cosFromSin(_t1, _t0));
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
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeRotationYXZ(double angleY, double angleX, double angleZ) {
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
        return new DoubleRigid(0.0, 0.0, 0.0, Math.fma(_t10, _t7, _t11 * _t5), Math.fma(_t11, _t7, -(_t10 * _t5)), Math.fma(_t12, _t5, -(_t9 * _t7)), Math.fma(_t9, _t5, _t12 * _t7));
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
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeRotationYZX(double angleY, double angleZ, double angleX) {
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
        return new DoubleRigid(0.0, 0.0, 0.0, Math.fma(_t9, _t6, _t12 * _t5), Math.fma(_t10, _t6, _t11 * _t5), Math.fma(_t11, _t6, -(_t10 * _t5)), Math.fma(_t12, _t6, -(_t9 * _t5)));
    }


    /**
     * Create a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeRotationZ(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleRigid(0.0, 0.0, 0.0, 0.0, 0.0, _t1, Math.cosFromSin(_t1, _t0));
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
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeRotationZXY(double angleZ, double angleX, double angleY) {
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
        return new DoubleRigid(0.0, 0.0, 0.0, Math.fma(_t10, _t7, -(_t11 * _t5)), Math.fma(_t9, _t7, _t12 * _t5), Math.fma(_t10, _t5, _t11 * _t7), Math.fma(_t12, _t7, -(_t9 * _t5)));
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
     * @return the resulting rigid transform
     */
    public static DoubleRigid makeRotationZYX(double angleZ, double angleY, double angleX) {
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
        return new DoubleRigid(0.0, 0.0, 0.0, Math.fma(_t12, _t5, -(_t9 * _t8)), Math.fma(_t10, _t8, _t11 * _t5), Math.fma(_t11, _t8, -(_t10 * _t5)), Math.fma(_t9, _t5, _t12 * _t8));
    }


    /**
     * Apply the rotation represented by the quaternion {@code rotation} to this rigid transform,
     * returning the result as a value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation to apply
     * @return the resulting rigid transform
     */
    public DoubleRigid rotate(DoubleQuat rotation) {
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        return new DoubleRigid(this.tX, this.tY, this.tZ, Math.fma(rotationX, this.rW, rotationW * this.rX) + Math.fma(rotationZ, this.rY, -(rotationY * this.rZ)), Math.fma(rotationX, this.rZ, rotationW * this.rY) + Math.fma(rotationY, this.rW, -(rotationZ * this.rX)), Math.fma(rotationY, this.rX, rotationZ * this.rW) + Math.fma(rotationW, this.rZ, -(rotationX * this.rY)), Math.fma(rotationW, this.rW, -(rotationX * this.rX)) - Math.fma(rotationY, this.rY, rotationZ * this.rZ));
    }


    /**
     * Apply the rotation represented by the quaternion ({@code rotationX}, {@code rotationY},
     * {@code rotationZ}, {@code rotationW}) to this rigid transform, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
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
     * @return the resulting rigid transform
     */
    public DoubleRigid rotate(double rotationX, double rotationY, double rotationZ, double rotationW) {
        return new DoubleRigid(this.tX, this.tY, this.tZ, Math.fma(rotationX, this.rW, rotationW * this.rX) + Math.fma(rotationZ, this.rY, -(rotationY * this.rZ)), Math.fma(rotationX, this.rZ, rotationW * this.rY) + Math.fma(rotationY, this.rW, -(rotationZ * this.rX)), Math.fma(rotationY, this.rX, rotationZ * this.rW) + Math.fma(rotationW, this.rZ, -(rotationX * this.rY)), Math.fma(rotationW, this.rW, -(rotationX * this.rX)) - Math.fma(rotationY, this.rY, rotationZ * this.rZ));
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this rigid
     * transform, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return the resulting rigid transform
     */
    public DoubleRigid rotateAxis(double angle, Double3 axis) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z());
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) to this rigid transform, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return the resulting rigid transform
     */
    public DoubleRigid rotateAxis(double angle, double axisX, double axisY, double axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        return new DoubleRigid(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t5, this.rW * _t2) + Math.fma(this.rY, _t3, -(this.rZ * _t4)), Math.fma(this.rY, _t5, this.rZ * _t2) + Math.fma(this.rW, _t4, -(this.rX * _t3)), Math.fma(this.rX, _t4, this.rW * _t3) + Math.fma(this.rZ, _t5, -(this.rY * _t2)), Math.fma(this.rW, _t5, -(this.rX * _t2)) - Math.fma(this.rY, _t4, this.rZ * _t3));
    }


    /**
     * Apply a rotation of {@code angle} radians about the X axis to this rigid transform, returning
     * the result as a value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting rigid transform
     */
    public DoubleRigid rotateX(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        return new DoubleRigid(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t2, this.rW * _t1), Math.fma(this.rY, _t2, this.rZ * _t1), Math.fma(this.rZ, _t2, -(this.rY * _t1)), Math.fma(this.rW, _t2, -(this.rX * _t1)));
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private DoubleRigid rotateXYZ_s361a4ff5_tail(double _t11, double _t8, double _t10, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        return new DoubleRigid(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t21, this.rW * _t19) + Math.fma(this.rY, _t20, -(this.rZ * _t22)), Math.fma(this.rY, _t21, this.rZ * _t19) + Math.fma(this.rW, _t22, -(this.rX * _t20)), Math.fma(this.rX, _t22, this.rW * _t20) + Math.fma(this.rZ, _t21, -(this.rY * _t19)), Math.fma(this.rW, _t21, -(this.rX * _t19)) - Math.fma(this.rY, _t22, this.rZ * _t20));
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this rigid transform, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return the resulting rigid transform
     */
    public DoubleRigid rotateXYZ(double angleX, double angleY, double angleZ) {
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
        return rotateXYZ_s361a4ff5_tail(_t11, _t8, _t10, _t5, Math.fma(_t14, _t8, -(_t9 * _t5)), Math.fma(_t10, _t8, _t11 * _t5), Math.fma(_t9, _t8, _t14 * _t5));
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this rigid transform, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return the resulting rigid transform
     */
    public DoubleRigid rotateXZY(double angleX, double angleZ, double angleY) {
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
        return rotateXYZ_s361a4ff5_tail(_t12, _t5, _t9, _t8, Math.fma(_t9, _t5, _t12 * _t8), Math.fma(_t10, _t8, -(_t11 * _t5)), Math.fma(_t10, _t5, _t11 * _t8));
    }


    /**
     * Apply a rotation of {@code angle} radians about the Y axis to this rigid transform, returning
     * the result as a value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting rigid transform
     */
    public DoubleRigid rotateY(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        return new DoubleRigid(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t2, -(this.rZ * _t1)), Math.fma(this.rY, _t2, this.rW * _t1), Math.fma(this.rX, _t1, this.rZ * _t2), Math.fma(this.rW, _t2, -(this.rY * _t1)));
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this rigid transform, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return the resulting rigid transform
     */
    public DoubleRigid rotateYXZ(double angleY, double angleX, double angleZ) {
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
        return rotateXYZ_s361a4ff5_tail(_t11, _t8, _t10, _t5, Math.fma(_t9, _t5, _t12 * _t8), Math.fma(_t10, _t8, _t11 * _t5), Math.fma(_t12, _t5, -(_t9 * _t8)));
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private DoubleRigid rotateYZX_s2c94f613_tail(double _t10, double _t8, double _t11, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return new DoubleRigid(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t21, this.rW * _t19) + Math.fma(this.rY, _t22, -(this.rZ * _t20)), Math.fma(this.rY, _t21, this.rZ * _t19) + Math.fma(this.rW, _t20, -(this.rX * _t22)), Math.fma(this.rX, _t20, this.rW * _t22) + Math.fma(this.rZ, _t21, -(this.rY * _t19)), Math.fma(this.rW, _t21, -(this.rX * _t19)) - Math.fma(this.rY, _t20, this.rZ * _t22));
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this rigid transform, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return the resulting rigid transform
     */
    public DoubleRigid rotateYZX(double angleY, double angleZ, double angleX) {
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
        return rotateYZX_s2c94f613_tail(_t10, _t8, _t11, _t5, Math.fma(_t14, _t8, -(_t9 * _t5)), Math.fma(_t9, _t8, _t14 * _t5), Math.fma(_t11, _t8, _t10 * _t5));
    }


    /**
     * Apply a rotation of {@code angle} radians about the Z axis to this rigid transform, returning
     * the result as a value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting rigid transform
     */
    public DoubleRigid rotateZ(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        return new DoubleRigid(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t2, this.rY * _t1), Math.fma(this.rY, _t2, -(this.rX * _t1)), Math.fma(this.rZ, _t2, this.rW * _t1), Math.fma(this.rW, _t2, -(this.rZ * _t1)));
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private DoubleRigid rotateZXY_s7e5a0297_tail(double _t10, double _t8, double _t11, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return new DoubleRigid(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t21, this.rW * _t22) + Math.fma(this.rY, _t19, -(this.rZ * _t20)), Math.fma(this.rY, _t21, this.rZ * _t22) + Math.fma(this.rW, _t20, -(this.rX * _t19)), Math.fma(this.rX, _t20, this.rW * _t19) + Math.fma(this.rZ, _t21, -(this.rY * _t22)), Math.fma(this.rW, _t21, -(this.rX * _t22)) - Math.fma(this.rY, _t20, this.rZ * _t19));
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this rigid transform, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return the resulting rigid transform
     */
    public DoubleRigid rotateZXY(double angleZ, double angleX, double angleY) {
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
        return rotateZXY_s7e5a0297_tail(_t10, _t8, _t11, _t5, Math.fma(_t14, _t8, -(_t9 * _t5)), Math.fma(_t10, _t5, _t11 * _t8), Math.fma(_t9, _t8, _t14 * _t5));
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this rigid transform, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return the resulting rigid transform
     */
    public DoubleRigid rotateZYX(double angleZ, double angleY, double angleX) {
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
        return rotateYZX_s2c94f613_tail(_t10, _t8, _t11, _t5, Math.fma(_t9, _t5, _t12 * _t8), Math.fma(_t12, _t5, -(_t9 * _t8)), Math.fma(_t11, _t8, _t10 * _t5));
    }


    /**
     * Apply a translation by {@code translation} to this rigid transform, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code T} the translation rigid transform,
     * then the new rigid transform will be {@code M * T}. So when transforming a vector {@code v}
     * with the new rigid transform by using {@code M * T * v}, the translation will be applied
     * first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation offsets
     * @return the resulting rigid transform
     */
    public DoubleRigid translate(Double3 translation) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        double _t9 = 2.0 * Math.fma(this.rX, translationY, -(this.rY * translationX));
        double _t10 = 2.0 * Math.fma(this.rZ, translationX, -(this.rX * translationZ));
        double _t11 = 2.0 * Math.fma(this.rY, translationZ, -(this.rZ * translationY));
        return new DoubleRigid(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + translationX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + translationY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + translationZ))), this.rX, this.rY, this.rZ, this.rW);
    }


    /**
     * Apply a translation by ({@code translationX}, {@code translationY}, {@code translationZ}) to
     * this rigid transform, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code T} the translation rigid transform,
     * then the new rigid transform will be {@code M * T}. So when transforming a vector {@code v}
     * with the new rigid transform by using {@code M * T * v}, the translation will be applied
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
     * @return the resulting rigid transform
     */
    public DoubleRigid translate(double translationX, double translationY, double translationZ) {
        double _t9 = 2.0 * Math.fma(this.rX, translationY, -(this.rY * translationX));
        double _t10 = 2.0 * Math.fma(this.rZ, translationX, -(this.rX * translationZ));
        double _t11 = 2.0 * Math.fma(this.rY, translationZ, -(this.rZ * translationY));
        return new DoubleRigid(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + translationX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + translationY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + translationZ))), this.rX, this.rY, this.rZ, this.rW);
    }


    /**
     * Transform {@code v} by this rigid transform, returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param v the vector to transform
     * @return the resulting vector
     */
    public Double3 transform(Double3 v) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        double _t9 = 2.0 * Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Double3(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + vX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + vY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + vZ))));
    }


    /**
     * Transform ({@code vX}, {@code vY}, {@code vZ}) by this rigid transform, returning the result
     * as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Double3 transform(double vX, double vY, double vZ) {
        double _t9 = 2.0 * Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Double3(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + vX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + vY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + vZ))));
    }


    /**
     * Transform the given direction by the rotation part of this rigid transform, ignoring the
     * translation, returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param v the direction to transform
     * @return the resulting vector
     */
    public Double3 transformDirection(Double3 v) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        double _t9 = 2.0 * Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Double3(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ))));
    }


    /**
     * Transform the given direction by the rotation part of this rigid transform, ignoring the
     * translation, returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Double3 transformDirection(double vX, double vY, double vZ) {
        double _t9 = 2.0 * Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Double3(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ))));
    }


    /**
     * Transform the given direction by the inverse of this rigid transform's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()}, returning the
     * result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param v the direction to transform
     * @return the resulting vector
     */
    public Double3 transformDirectionInverse(Double3 v) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        double _t9 = 2.0 * Math.fma(this.rX, vZ, -(this.rZ * vX));
        double _t10 = 2.0 * Math.fma(this.rY, vX, -(this.rX * vY));
        double _t11 = 2.0 * Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Double3(Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX))), Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY))), Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ))));
    }


    /**
     * Transform the given direction by the inverse of this rigid transform's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()}, returning the
     * result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Double3 transformDirectionInverse(double vX, double vY, double vZ) {
        double _t9 = 2.0 * Math.fma(this.rX, vZ, -(this.rZ * vX));
        double _t10 = 2.0 * Math.fma(this.rY, vX, -(this.rX * vY));
        double _t11 = 2.0 * Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Double3(Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX))), Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY))), Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ))));
    }


    /**
     * Transform {@code p} by the inverse of this rigid transform, returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param p the position to transform
     * @return the resulting vector
     */
    public Double3 transformInverse(Double3 p) {
        double _t0 = p.z() - this.tZ;
        double _t1 = p.x() - this.tX;
        double _t2 = p.y() - this.tY;
        double _t12 = 2.0 * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        double _t13 = 2.0 * Math.fma(this.rY, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        return new Double3(Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1))), Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2))), Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0))));
    }


    /**
     * Transform ({@code pX}, {@code pY}, {@code pZ}) by the inverse of this rigid transform,
     * returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @return the resulting vector
     */
    public Double3 transformInverse(double pX, double pY, double pZ) {
        double _t0 = pZ - this.tZ;
        double _t1 = pX - this.tX;
        double _t2 = pY - this.tY;
        double _t12 = 2.0 * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        double _t13 = 2.0 * Math.fma(this.rY, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        return new Double3(Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1))), Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2))), Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0))));
    }


    /**
     * Transform the given position by this rigid transform, treating it as a point with an implicit
     * {@code w = 1}, returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param v the position to transform
     * @return the resulting vector
     */
    public Double3 transformPosition(Double3 v) {
        return transform(v);
    }


    /**
     * Transform the given position by this rigid transform, treating it as a point with an implicit
     * {@code w = 1}, returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Double3 transformPosition(double vX, double vY, double vZ) {
        double _t9 = 2.0 * Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Double3(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + vX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + vY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + vZ))));
    }


    /**
     * Transform the given position by the inverse of this rigid transform (world to local), without
     * materializing {@code invert()}, returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param p the position to transform
     * @return the resulting vector
     */
    public Double3 transformPositionInverse(Double3 p) {
        return transformInverse(p);
    }


    /**
     * Transform the given position by the inverse of this rigid transform (world to local), without
     * materializing {@code invert()}, returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @return the resulting vector
     */
    public Double3 transformPositionInverse(double pX, double pY, double pZ) {
        double _t0 = pZ - this.tZ;
        double _t1 = pX - this.tX;
        double _t2 = pY - this.tY;
        double _t12 = 2.0 * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        double _t13 = 2.0 * Math.fma(this.rY, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        return new Double3(Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1))), Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2))), Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0))));
    }

    /**
     * {@return a copy with the {@code tX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code tX} component
     */
    public DoubleRigid withTX(double v) {
        return new DoubleRigid(v, tY, tZ, rX, rY, rZ, rW);
    }

    /**
     * {@return a copy with the {@code tY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code tY} component
     */
    public DoubleRigid withTY(double v) {
        return new DoubleRigid(tX, v, tZ, rX, rY, rZ, rW);
    }

    /**
     * {@return a copy with the {@code tZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code tZ} component
     */
    public DoubleRigid withTZ(double v) {
        return new DoubleRigid(tX, tY, v, rX, rY, rZ, rW);
    }

    /**
     * {@return a copy with the {@code rX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rX} component
     */
    public DoubleRigid withRX(double v) {
        return new DoubleRigid(tX, tY, tZ, v, rY, rZ, rW);
    }

    /**
     * {@return a copy with the {@code rY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rY} component
     */
    public DoubleRigid withRY(double v) {
        return new DoubleRigid(tX, tY, tZ, rX, v, rZ, rW);
    }

    /**
     * {@return a copy with the {@code rZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rZ} component
     */
    public DoubleRigid withRZ(double v) {
        return new DoubleRigid(tX, tY, tZ, rX, rY, v, rW);
    }

    /**
     * {@return a copy with the {@code rW} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rW} component
     */
    public DoubleRigid withRW(double v) {
        return new DoubleRigid(tX, tY, tZ, rX, rY, rZ, v);
    }

    @Override public String toString() {
        return "DoubleRigid(" + tX() + ", " + tY() + ", " + tZ() + ", " + rX() + ", " + rY() + ", " + rZ() + ", " + rW() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleRigid)) return false;
        DoubleRigid o = (DoubleRigid) obj;
        return Double.doubleToLongBits(tX) == Double.doubleToLongBits(o.tX)
            && Double.doubleToLongBits(tY) == Double.doubleToLongBits(o.tY)
            && Double.doubleToLongBits(tZ) == Double.doubleToLongBits(o.tZ)
            && Double.doubleToLongBits(rX) == Double.doubleToLongBits(o.rX)
            && Double.doubleToLongBits(rY) == Double.doubleToLongBits(o.rY)
            && Double.doubleToLongBits(rZ) == Double.doubleToLongBits(o.rZ)
            && Double.doubleToLongBits(rW) == Double.doubleToLongBits(o.rW);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(Double.doubleToLongBits(tX) ^ (Double.doubleToLongBits(tX) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(tY) ^ (Double.doubleToLongBits(tY) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(tZ) ^ (Double.doubleToLongBits(tZ) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(rX) ^ (Double.doubleToLongBits(rX) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(rY) ^ (Double.doubleToLongBits(rY) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(rZ) ^ (Double.doubleToLongBits(rZ) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(rW) ^ (Double.doubleToLongBits(rW) >>> 32));
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Double.isFinite(tX)
            && Double.isFinite(tY)
            && Double.isFinite(tZ)
            && Double.isFinite(rX)
            && Double.isFinite(rY)
            && Double.isFinite(rZ)
            && Double.isFinite(rW);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Double.isNaN(tX)
            || Double.isNaN(tY)
            || Double.isNaN(tZ)
            || Double.isNaN(rX)
            || Double.isNaN(rY)
            || Double.isNaN(rZ)
            || Double.isNaN(rW);
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
    public boolean equalsEpsilon(DoubleRigid other, double epsilon) {
        return java.lang.Math.abs(tX - other.tX()) <= epsilon
            && java.lang.Math.abs(tY - other.tY()) <= epsilon
            && java.lang.Math.abs(tZ - other.tZ()) <= epsilon
            && java.lang.Math.abs(rX - other.rX()) <= epsilon
            && java.lang.Math.abs(rY - other.rY()) <= epsilon
            && java.lang.Math.abs(rZ - other.rZ()) <= epsilon
            && java.lang.Math.abs(rW - other.rW()) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoubleRigidSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleRigidSegOpsUnsafe()
                        : new DoubleRigidSegOpsMS();
        static final DoubleRigidBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleRigidBbOpsUnsafe()
                        : new DoubleRigidBbOpsApi();
        static final DoubleRigidRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleRigidRawOpsUnsafe()
                        : new DoubleRigidRawOpsApi();
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
    public double[] store(double[] dest, int offset) {
        dest[offset] = this.tX;
        dest[offset + 1] = this.tY;
        dest[offset + 2] = this.tZ;
        dest[offset + 3] = this.rX;
        dest[offset + 4] = this.rY;
        dest[offset + 5] = this.rZ;
        dest[offset + 6] = this.rW;
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
    public double[] store(double[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid load(double[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        double _c6 = src[offset + 6];
        return new DoubleRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid load(double[] src) { return load(src, 0); }

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
    public DoubleBuffer store(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
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
    public DoubleBuffer storeAbsolute(int index, DoubleBuffer buf) {
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
    public DoubleBuffer storeRelative(DoubleBuffer buf) {
        if (buf.remaining() < 7) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 7);
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
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
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
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid loadAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code DoubleRigid} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleRigid loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 7) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleRigid r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 7);
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
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
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
        if (buf.remaining() < 56) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 56);
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
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
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
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code DoubleRigid} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleRigid loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 56) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleRigid r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 56);
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
    public DoubleRigid storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid loadUnsafe(long address) {
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
    public MemorySegment store(MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }

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
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

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
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(offset, src);
    }


    /**
     * Store the elements into the given array, converting each element to {@code float}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] store(float[] dest, int offset) {
        dest[offset] = (float) this.tX;
        dest[offset + 1] = (float) this.tY;
        dest[offset + 2] = (float) this.tZ;
        dest[offset + 3] = (float) this.rX;
        dest[offset + 4] = (float) this.rY;
        dest[offset + 5] = (float) this.rZ;
        dest[offset + 6] = (float) this.rW;
        return dest;
    }

    /**
     * Store the elements into the given array, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store(float[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code float}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid load(float[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        double _c6 = src[offset + 6];
        return new DoubleRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }

    /**
     * Load the elements from the given array, converting each element from {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid load(float[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code float}, starting
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
    public FloatBuffer store(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code float}, starting
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
    public FloatBuffer storeAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code float}, starting
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
    public FloatBuffer storeRelative(FloatBuffer buf) {
        if (buf.remaining() < 7) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 7);
        return buf;
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
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
     * @param buf the source buffer
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
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
     * @param buf the source buffer
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
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
     * @param buf the source buffer
     * @return a new {@code DoubleRigid} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleRigid loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 7) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleRigid r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 7);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
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
    public ByteBuffer storeFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
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
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
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
    public ByteBuffer storeFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 28) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 28);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
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
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
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
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
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
     * @return a new {@code DoubleRigid} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleRigid loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 28) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleRigid r = StoreLoad.BB_OPS.loadFloatAbsolute(pos, buf);
        buf.position(pos + 28);
        return r;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public DoubleRigid storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment, converting each element to {@code float}.
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
    public MemorySegment storeFloat(MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code float},
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
    public MemorySegment storeFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment, converting each element from {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(0L, src); }

    /**
     * Load the elements from the given memory segment, converting each element from {@code float},
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
     * @return a new {@code DoubleRigid} holding the loaded elements
     */
    public static DoubleRigid loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(offset, src);
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
}
