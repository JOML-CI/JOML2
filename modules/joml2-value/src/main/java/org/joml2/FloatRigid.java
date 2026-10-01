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
 * Immutable rigid transform of single-precision {@code float} components, declared as a value
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
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
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
public value record FloatRigid(float tX, float tY, float tZ, float rX, float rY, float rZ, float rW) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 28;

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
    public FloatRigid(float tX, float tY, float tZ, float rX, float rY, float rZ, float rW) {
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
    public FloatRigid() {
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
    public FloatRigid(Float3 translation, FloatQuat rotation) {
        this(translation.x(), translation.y(), translation.z(), rotation.x(), rotation.y(), rotation.z(), rotation.w());
    }

    /** {@return the {@code tX} component} <p>Valid input: any value, NaN and the infinities included. */
    public float tX() { return tX; }
    /** {@return the {@code tY} component} <p>Valid input: any value, NaN and the infinities included. */
    public float tY() { return tY; }
    /** {@return the {@code tZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public float tZ() { return tZ; }
    /** {@return the {@code rX} component} <p>Valid input: any value, NaN and the infinities included. */
    public float rX() { return rX; }
    /** {@return the {@code rY} component} <p>Valid input: any value, NaN and the infinities included. */
    public float rY() { return rY; }
    /** {@return the {@code rZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public float rZ() { return rZ; }
    /** {@return the {@code rW} component} <p>Valid input: any value, NaN and the infinities included. */
    public float rW() { return rW; }

    /**
     * Create a new rigid transform from its translation and rotation.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param translation the translation
     * @param rotation the rotation quaternion, taken as given (not normalized)
     * @return the resulting rigid transform
     */
    public FloatRigid set(Float3 translation, FloatQuat rotation) {
        return new FloatRigid(translation, rotation);
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
    public static FloatRigid makeFromAxisAngle(Float3 axis, float angle, Float3 translation) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        return new FloatRigid(translationX, translationY, translationZ, axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0));
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
    public static FloatRigid makeFromAxisAngle(float axisX, float axisY, float axisZ, float angle, float translationX, float translationY, float translationZ) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        return new FloatRigid(translationX, translationY, translationZ, axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0));
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
    public static FloatRigid makeTranslationRotation(Float3 translation, FloatQuat rotation) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        return new FloatRigid(translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW);
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
    public static FloatRigid makeTranslationRotation(float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW) {
        return new FloatRigid(translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW);
    }


    /**
     * Create a new rigid transform from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the rigid transform to copy
     * @return the resulting rigid transform
     */
    public FloatRigid set(FloatRigid v) {
        float vTX = v.tX();
        float vTY = v.tY();
        float vTZ = v.tZ();
        float vRX = v.rX();
        float vRY = v.rY();
        float vRZ = v.rZ();
        float vRW = v.rW();
        return new FloatRigid(vTX, vTY, vTZ, vRX, vRY, vRZ, vRW);
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
    public FloatRigid set(float vTX, float vTY, float vTZ, float vRX, float vRY, float vRZ, float vRW) {
        return new FloatRigid(vTX, vTY, vTZ, vRX, vRY, vRZ, vRW);
    }


    /**
     * Set the rotation of this rigid transform to {@code r}, returning the result as a value.
     * <p>
     * Valid input: {@code r} must have unit length.
     *
     * @param r the new rotation
     * @return the resulting rigid transform
     */
    public FloatRigid setRotation(FloatQuat r) {
        float rX = r.x();
        float rY = r.y();
        float rZ = r.z();
        float rW = r.w();
        return new FloatRigid(this.tX, this.tY, this.tZ, rX, rY, rZ, rW);
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
    public FloatRigid setRotation(float rX, float rY, float rZ, float rW) {
        return new FloatRigid(this.tX, this.tY, this.tZ, rX, rY, rZ, rW);
    }


    /**
     * Set the translation of this rigid transform to {@code t}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the translation vector
     * @return the resulting rigid transform
     */
    public FloatRigid setTranslation(Float3 t) {
        float tX = t.x();
        float tY = t.y();
        float tZ = t.z();
        return new FloatRigid(tX, tY, tZ, this.rX, this.rY, this.rZ, this.rW);
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
    public FloatRigid setTranslation(float tX, float tY, float tZ) {
        return new FloatRigid(tX, tY, tZ, this.rX, this.rY, this.rZ, this.rW);
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
    public static FloatRigid makeFromDualQuat(FloatDualQuat dq) {
        float dqRX = dq.rX();
        float dqRY = dq.rY();
        float dqRZ = dq.rZ();
        float dqRW = dq.rW();
        float dqDX = dq.dX();
        float dqDY = dq.dY();
        float dqDZ = dq.dZ();
        float dqDW = dq.dW();
        return new FloatRigid(2.0f * (Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + Math.fma(dqRW, dqDX, -(dqRX * dqDW))), 2.0f * (Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + Math.fma(dqRW, dqDY, -(dqRY * dqDW))), 2.0f * (Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + Math.fma(dqRW, dqDZ, -(dqRZ * dqDW))), dqRX, dqRY, dqRZ, dqRW);
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
    public static FloatRigid makeFromDualQuat(float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        return new FloatRigid(2.0f * (Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + Math.fma(dqRW, dqDX, -(dqRX * dqDW))), 2.0f * (Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + Math.fma(dqRW, dqDY, -(dqRY * dqDW))), 2.0f * (Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + Math.fma(dqRW, dqDZ, -(dqRZ * dqDW))), dqRX, dqRY, dqRZ, dqRW);
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
    public static FloatRigid makeFromMatrix(Float3x3 m) {
        float _ct0 = Math.fma(m.m21(), m.m21(), Math.fma(m.m01(), m.m01(), m.m11() * m.m11()));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _ct1 = Math.fma(m.m22(), m.m22(), Math.fma(m.m02(), m.m02(), m.m12() * m.m12()));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = Math.fma(m.m20(), m.m20(), Math.fma(m.m00(), m.m00(), m.m10() * m.m10()));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        return makeFromMatrix_s6b8df64f_1(m, _t15, _t16, -m.m11(), -m.m22(), m.m10() * _t17, m.m22() * _t16, m.m12() * _t16, m.m20() * _t17, m.m21() * _t15, m.m11() * _t15, m.m00() * _t17);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatRigid makeFromMatrix_s6b8df64f_1(Float3x3 m, float _t15, float _t16, float _t0, float _t1, float _t18, float _t19, float _t20, float _t21, float _t23, float _t24, float _t26) {
        float _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), m.m01() * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), m.m02() * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        float _t51 = 1.0f + _t47;
        float _t52 = 1.0f - _t47;
        float _t63 = Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t51));
        float _t65 = Math.fma(m.m11(), _t15, Math.fma(_t1, _t16, _t52));
        return makeFromMatrix_s6b8df64f_2(m, _t15, _t16, _t19, _t24, Math.fma(m.m12(), _t16, _t23), Math.fma(m.m21(), _t15, -_t20), _t47, Math.fma(m.m01(), _t15, _t48), Math.fma(m.m02(), _t16, _t49), Math.fma(m.m02(), _t16, -_t49), Math.fma(-m.m01(), _t15, _t48), _t63, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)), _t65, Math.fma(m.m22(), _t16, Math.fma(_t0, _t15, _t52)), Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatRigid makeFromMatrix_s6b8df64f_2(Float3x3 m, float _t15, float _t16, float _t19, float _t24, float _t31, float _t35, float _t47, float _t54, float _t55, float _t56, float _t57, float _t63, float _sp0, float _t65, float _t66, float _t67, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        float _sfx3, _sfx4, _sfx5, _sfx6;
        if (Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t47)) > 0.0f) {
            _sfx3 = _sp0 * _t35;
            _sfx4 = _sp0 * _t56;
            _sfx5 = _sp0 * _t57;
            _sfx6 = 0.5f * (float) java.lang.Math.sqrt(_t63);
        } else {
            if (_t47 > java.lang.Math.max(_t24, _t19)) {
                _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t67);
                _sfx4 = _sp3 * _t54;
                _sfx5 = _sp3 * _t55;
                _sfx6 = _sp3 * _t35;
            } else {
                if (_t24 > _t19) {
                    _sfx3 = _sp1 * _t54;
                    _sfx4 = 0.5f * (float) java.lang.Math.sqrt(_t65);
                    _sfx5 = _sp1 * _t31;
                    _sfx6 = _sp1 * _t56;
                } else {
                    _sfx3 = _sp2 * _t55;
                    _sfx4 = _sp2 * _t31;
                    _sfx5 = 0.5f * (float) java.lang.Math.sqrt(_t66);
                    _sfx6 = _sp2 * _t57;
                }
            }
        }
        return new FloatRigid(0.0f, 0.0f, 0.0f, _sfx3, _sfx4, _sfx5, _sfx6);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37cad23f_tail(float _t31, float _t15, float _t16, float _t32, float _t13, float _t12, float _t14, float _t33, float _t34, float _t35, float _t36, float _t27, float _t28, float _t29) {
        float _t37 = _t31 * _t15;
        float _t38 = _t31 * _t16;
        float _t39 = _t32 * _t13;
        float _t40 = _t32 * _t12;
        float _t41 = _t32 * _t14;
        float _t50 = java.lang.Math.abs(_t40);
        float _t51 = java.lang.Math.abs(_t39);
        float _t72, _t75, _t87;
        if (java.lang.Math.abs(_t33) < java.lang.Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0f;
            _t87 = -_t34;
        } else {
            _t72 = 0.0f;
            _t75 = -_t35;
            _t87 = _t33;
        }
        float _t73, _t76, _t88;
        if (java.lang.Math.abs(_t37) < java.lang.Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0f;
            _t88 = -_t38;
        } else {
            _t73 = 0.0f;
            _t76 = -_t36;
            _t88 = _t37;
        }
        float _t74, _t77;
        if (_t50 < _t51) {
            _t74 = _t41;
            _t77 = 0.0f;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
        }
        return makeFromMatrix_degenerate_s37cad23f_tail2(_t50, _t51, _t39, _t40, _t75, _t87, _t72, _t76, _t88, _t73, _t77, _t74, _t27, _t28, _t29, _t36, _t37, _t33, _t35, _t34, _t38, _t41);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37cad23f_tail2(float _t50, float _t51, float _t39, float _t40, float _t75, float _t87, float _t72, float _t76, float _t88, float _t73, float _t77, float _t74, float _t27, float _t28, float _t29, float _t36, float _t37, float _t33, float _t35, float _t34, float _t38, float _t41) {
        float _t89 = _t50 < _t51 ? -_t39 : _t40;
        float _t99 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        return makeFromMatrix_degenerate_s37cad23f_tail3(_t27, _t28, _t29, _t99 * _t72, _t36, _t100 * _t76, _t37, _t100 * _t88, _t33, _t35, _t39, _t101 * _t89, _t34, _t99 * _t75, _t40, _t99 * _t87, _t100 * _t73, _t38, _t41, _t101 * _t74, _t101 * _t77);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37cad23f_tail3(float _t27, float _t28, float _t29, float _t102, float _t36, float _t105, float _t37, float _t114, float _t33, float _t35, float _t39, float _t115, float _t34, float _t106, float _t40, float _t116, float _t103, float _t38, float _t41, float _t104, float _t107) {
        float _t165, _t166;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = 0.0f;
                    _t166 = 0.0f;
                } else {
                    _t165 = _t102;
                    _t166 = Math.fma(_t33, _t102, -(_t34 * _t106));
                }
            } else {
                _t165 = _t29 <= 0.0f ? Math.fma(_t36, _t105, -(_t37 * _t114)) : Math.fma(_t33, _t36, -(_t35 * _t37));
                _t166 = _t36;
            }
        } else {
            _t165 = _t39;
            _t166 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t115 : Math.fma(_t33, _t39, -(_t34 * _t40)) : _t36;
        }
        return makeFromMatrix_degenerate_s37cad23f_tail4(_t27, _t28, _t29, _t116, _t37, _t103, _t38, _t105, _t34, _t33, _t41, _t104, _t35, _t106, _t40, _t39, _t115, _t36, _t114, _t107, _t102, _t166, _t165);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37cad23f_tail4(float _t27, float _t28, float _t29, float _t116, float _t37, float _t103, float _t38, float _t105, float _t34, float _t33, float _t41, float _t104, float _t35, float _t106, float _t40, float _t39, float _t115, float _t36, float _t114, float _t107, float _t102, float _t166, float _t165) {
        float _t167, _t168;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t167 = 1.0f;
                    _t168 = 0.0f;
                } else {
                    _t167 = _t116;
                    _t168 = Math.fma(_t35, _t106, -(_t33 * _t116));
                }
            } else {
                _t167 = _t29 <= 0.0f ? Math.fma(_t37, _t103, -(_t38 * _t105)) : Math.fma(_t34, _t37, -(_t33 * _t38));
                _t168 = _t38;
            }
        } else {
            _t167 = _t41;
            _t168 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t104 : Math.fma(_t35, _t40, -(_t33 * _t41)) : _t38;
        }
        return makeFromMatrix_degenerate_s37cad23f_tail5(_t29, _t27, _t28, _t105, _t39, _t115, _t41, _t104, _t36, _t38, _t33, _t106, _t114, _t103, _t35, _t34, _t40, _t107, _t116, _t102, _t37, _t166, _t167, _t165, _t168);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37cad23f_tail5(float _t29, float _t27, float _t28, float _t105, float _t39, float _t115, float _t41, float _t104, float _t36, float _t38, float _t33, float _t106, float _t114, float _t103, float _t35, float _t34, float _t40, float _t107, float _t116, float _t102, float _t37, float _t166, float _t167, float _t165, float _t168) {
        float _t169, _t170;
        if (_t29 <= 0.0f) {
            if (_t27 <= 0.0f) {
                if (_t28 <= 0.0f) {
                    _t169 = 0.0f;
                    _t170 = 0.0f;
                } else {
                    _t169 = _t105;
                    _t170 = Math.fma(_t38, _t114, -(_t36 * _t103));
                }
            } else {
                _t169 = _t28 <= 0.0f ? Math.fma(_t39, _t115, -(_t41 * _t104)) : Math.fma(_t39, _t36, -(_t41 * _t38));
                _t170 = _t40;
            }
        } else {
            _t169 = _t33;
            _t170 = _t27 <= 0.0f ? _t28 <= 0.0f ? _t106 : Math.fma(_t35, _t38, -(_t34 * _t36)) : _t40;
        }
        return makeFromMatrix_degenerate_s37cad23f_tail6(_t28, _t29, _t27, _t107, _t34, _t116, _t35, _t102, _t41, _t39, _t37, _t114, _t40, _t104, _t38, _t103, _t115, _t36, _t170, _t166, _t167, _t165, _t168, _t169);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37cad23f_tail6(float _t28, float _t29, float _t27, float _t107, float _t34, float _t116, float _t35, float _t102, float _t41, float _t39, float _t37, float _t114, float _t40, float _t104, float _t38, float _t103, float _t115, float _t36, float _t170, float _t166, float _t167, float _t165, float _t168, float _t169) {
        float _t171, _t172;
        if (_t28 <= 0.0f) {
            if (_t29 <= 0.0f) {
                if (_t27 <= 0.0f) {
                    _t171 = 1.0f;
                    _t172 = 0.0f;
                } else {
                    _t171 = _t107;
                    _t172 = Math.fma(_t40, _t104, -(_t39 * _t107));
                }
            } else {
                _t171 = _t27 <= 0.0f ? Math.fma(_t34, _t116, -(_t35 * _t102)) : Math.fma(_t34, _t41, -(_t35 * _t39));
                _t172 = _t35;
            }
        } else {
            _t171 = _t37;
            _t172 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t114 : Math.fma(_t40, _t38, -(_t39 * _t37)) : _t35;
        }
        return makeFromMatrix_degenerate_s37cad23f_tail7(_t29, _t27, _t28, _t103, _t41, _t107, _t40, _t115, _t37, _t36, _t34, _t170, _t166, _t167, _t171, _t165, _t168, _t169, _t172);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37cad23f_tail7(float _t29, float _t27, float _t28, float _t103, float _t41, float _t107, float _t40, float _t115, float _t37, float _t36, float _t34, float _t170, float _t166, float _t167, float _t171, float _t165, float _t168, float _t169, float _t172) {
        float _t173 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t28 <= 0.0f ? 1.0f : _t103 : _t28 <= 0.0f ? Math.fma(_t41, _t107, -(_t40 * _t115)) : Math.fma(_t41, _t37, -(_t40 * _t36)) : _t34;
        float _t194, _t195, _t196;
        if (Math.fma(Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, Math.fma(Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0f) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        return makeFromMatrix_degenerate_s37cad23f_tail8(_t195, _t165, _t194, _t167, _t171, _t170 - _t166, java.lang.Math.max(_t167, _t171), _t195 + _t165, _t196 + _t168, _t168 - _t196, _t170 + _t166);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37cad23f_tail8(float _t195, float _t165, float _t194, float _t167, float _t171, float _t182, float _t183, float _t199, float _t200, float _t201, float _t184) {
        float _t206 = _t194 + _t167 + _t171;
        float _t207 = 1.0f + _t206;
        float _t208 = 1.0f + _t194 - _t167 - _t171;
        float _t209 = 1.0f + _t167 - _t194 - _t171;
        float _t210 = 1.0f + _t171 - _t194 - _t167;
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sfx3 = _t206 > 0.0f ? _sp0 * _t182 : _t194 > _t183 ? 0.5f * (float) java.lang.Math.sqrt(_t208) : _t167 > _t171 ? _sp1 * _t199 : _sp2 * _t200;
        return makeFromMatrix_degenerate_s37cad23f_tail9(_t206, _sp0, _t201, _t194, _t183, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t208)), _t199, _t167, _t171, _t209, _sp2, _t184, _t195 - _t165, _t200, _sp1, _t210, _t207, _t182, 0.0f, 0.0f, 0.0f, _sfx3);
    }

    /**
     * Private tail of {@code makeFromMatrix_degenerate}. Shared by 3 identical private paths of
     * {@code makeFromMatrix}; reached only through it.
     */
    private static FloatRigid makeFromMatrix_degenerate_s37cad23f_tail9(float _t206, float _sp0, float _t201, float _t194, float _t183, float _sp3, float _t199, float _t167, float _t171, float _t209, float _sp2, float _t184, float _t202, float _t200, float _sp1, float _t210, float _t207, float _t182, float _sfx0, float _sfx1, float _sfx2, float _sfx3) {
        float _sfx4, _sfx5, _sfx6;
        if (_t206 > 0.0f) {
            _sfx4 = _sp0 * _t201;
            _sfx5 = _sp0 * _t202;
            _sfx6 = 0.5f * (float) java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > _t183) {
                _sfx4 = _sp3 * _t199;
                _sfx5 = _sp3 * _t200;
                _sfx6 = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    _sfx4 = 0.5f * (float) java.lang.Math.sqrt(_t209);
                    _sfx5 = _sp1 * _t184;
                    _sfx6 = _sp1 * _t201;
                } else {
                    _sfx4 = _sp2 * _t184;
                    _sfx5 = 0.5f * (float) java.lang.Math.sqrt(_t210);
                    _sfx6 = _sp2 * _t202;
                }
            }
        }
        return new FloatRigid(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6);
    }


    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static FloatRigid makeFromMatrix_degenerate(Float3x3 m) {
        float _t0 = unitScale(m.m01(), m.m11(), m.m21());
        float _t1 = unitScale(m.m02(), m.m12(), m.m22());
        float _t2 = unitScale(m.m00(), m.m10(), m.m20());
        float _t12 = m.m21() * _t0;
        float _t13 = m.m01() * _t0;
        float _t14 = m.m11() * _t0;
        float _t15 = m.m22() * _t1;
        float _t16 = m.m02() * _t1;
        float _t17 = m.m12() * _t1;
        float _t18 = m.m20() * _t2;
        float _t19 = m.m00() * _t2;
        float _t20 = m.m10() * _t2;
        float _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        return makeFromMatrix_degenerate_s37cad23f_tail(_t31, _t15, _t16, (1.0f / (float) java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t30 * _t20, _t31 * _t17, _t27, _t28, _t29);
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
    public static FloatRigid makeFromMatrix(Float3x4 m) {
        float _ct0 = Math.fma(m.m21(), m.m21(), Math.fma(m.m01(), m.m01(), m.m11() * m.m11()));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _ct1 = Math.fma(m.m22(), m.m22(), Math.fma(m.m02(), m.m02(), m.m12() * m.m12()));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = Math.fma(m.m20(), m.m20(), Math.fma(m.m00(), m.m00(), m.m10() * m.m10()));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        return makeFromMatrix_s69dd4b8e_1(m, _t15, _t16, -m.m11(), -m.m22(), m.m10() * _t17, m.m22() * _t16, m.m12() * _t16, m.m20() * _t17, m.m21() * _t15, m.m11() * _t15, m.m00() * _t17);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatRigid makeFromMatrix_s69dd4b8e_1(Float3x4 m, float _t15, float _t16, float _t0, float _t1, float _t18, float _t19, float _t20, float _t21, float _t23, float _t24, float _t26) {
        float _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), m.m01() * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), m.m02() * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        float _t51 = 1.0f + _t47;
        float _t52 = 1.0f - _t47;
        float _t63 = Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t51));
        float _t65 = Math.fma(m.m11(), _t15, Math.fma(_t1, _t16, _t52));
        return makeFromMatrix_s69dd4b8e_2(m, _t15, _t16, _t19, _t24, Math.fma(m.m12(), _t16, _t23), Math.fma(m.m21(), _t15, -_t20), _t47, Math.fma(m.m01(), _t15, _t48), Math.fma(m.m02(), _t16, _t49), Math.fma(m.m02(), _t16, -_t49), Math.fma(-m.m01(), _t15, _t48), _t63, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)), _t65, Math.fma(m.m22(), _t16, Math.fma(_t0, _t15, _t52)), Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatRigid makeFromMatrix_s69dd4b8e_2(Float3x4 m, float _t15, float _t16, float _t19, float _t24, float _t31, float _t35, float _t47, float _t54, float _t55, float _t56, float _t57, float _t63, float _sp0, float _t65, float _t66, float _t67, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        float _sfx0 = m.m03();
        float _sfx1 = m.m13();
        float _sfx2 = m.m23();
        float _sfx3, _sfx4, _sfx5, _sfx6;
        if (Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t47)) > 0.0f) {
            _sfx3 = _sp0 * _t35;
            _sfx4 = _sp0 * _t56;
            _sfx5 = _sp0 * _t57;
            _sfx6 = 0.5f * (float) java.lang.Math.sqrt(_t63);
        } else {
            if (_t47 > java.lang.Math.max(_t24, _t19)) {
                _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t67);
                _sfx4 = _sp3 * _t54;
                _sfx5 = _sp3 * _t55;
                _sfx6 = _sp3 * _t35;
            } else {
                if (_t24 > _t19) {
                    _sfx3 = _sp1 * _t54;
                    _sfx4 = 0.5f * (float) java.lang.Math.sqrt(_t65);
                    _sfx5 = _sp1 * _t31;
                    _sfx6 = _sp1 * _t56;
                } else {
                    _sfx3 = _sp2 * _t55;
                    _sfx4 = _sp2 * _t31;
                    _sfx5 = 0.5f * (float) java.lang.Math.sqrt(_t66);
                    _sfx6 = _sp2 * _t57;
                }
            }
        }
        return new FloatRigid(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37cad600_tail(float _t31, float _t17, float _t15, float _t16, float _t32, float _t13, float _t12, float _t14, float _t33, float _t34, float _t35, float _t27, float _t28, float _t29, Float3x4 m) {
        float _t36 = _t31 * _t17;
        float _t37 = _t31 * _t15;
        float _t38 = _t31 * _t16;
        float _t39 = _t32 * _t13;
        float _t40 = _t32 * _t12;
        float _t41 = _t32 * _t14;
        float _t50 = java.lang.Math.abs(_t40);
        float _t51 = java.lang.Math.abs(_t39);
        float _t72, _t75, _t87;
        if (java.lang.Math.abs(_t33) < java.lang.Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0f;
            _t87 = -_t34;
        } else {
            _t72 = 0.0f;
            _t75 = -_t35;
            _t87 = _t33;
        }
        float _t73, _t76, _t88;
        if (java.lang.Math.abs(_t37) < java.lang.Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0f;
            _t88 = -_t38;
        } else {
            _t73 = 0.0f;
            _t76 = -_t36;
            _t88 = _t37;
        }
        float _t74, _t77;
        if (_t50 < _t51) {
            _t74 = _t41;
            _t77 = 0.0f;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
        }
        return makeFromMatrix_degenerate_s37cad600_tail2(_t50, _t51, _t39, _t40, _t75, _t87, _t72, _t76, _t88, _t73, _t77, _t74, _t27, _t28, _t29, _t36, _t37, _t33, _t35, _t34, _t38, _t41, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37cad600_tail2(float _t50, float _t51, float _t39, float _t40, float _t75, float _t87, float _t72, float _t76, float _t88, float _t73, float _t77, float _t74, float _t27, float _t28, float _t29, float _t36, float _t37, float _t33, float _t35, float _t34, float _t38, float _t41, Float3x4 m) {
        float _t89 = _t50 < _t51 ? -_t39 : _t40;
        float _t99 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        return makeFromMatrix_degenerate_s37cad600_tail3(_t27, _t28, _t29, _t99 * _t72, _t36, _t100 * _t76, _t37, _t100 * _t88, _t33, _t35, _t39, _t101 * _t89, _t34, _t99 * _t75, _t40, _t99 * _t87, _t100 * _t73, _t38, _t41, _t101 * _t74, _t101 * _t77, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37cad600_tail3(float _t27, float _t28, float _t29, float _t102, float _t36, float _t105, float _t37, float _t114, float _t33, float _t35, float _t39, float _t115, float _t34, float _t106, float _t40, float _t116, float _t103, float _t38, float _t41, float _t104, float _t107, Float3x4 m) {
        float _t165, _t166;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = 0.0f;
                    _t166 = 0.0f;
                } else {
                    _t165 = _t102;
                    _t166 = Math.fma(_t33, _t102, -(_t34 * _t106));
                }
            } else {
                _t165 = _t29 <= 0.0f ? Math.fma(_t36, _t105, -(_t37 * _t114)) : Math.fma(_t33, _t36, -(_t35 * _t37));
                _t166 = _t36;
            }
        } else {
            _t165 = _t39;
            _t166 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t115 : Math.fma(_t33, _t39, -(_t34 * _t40)) : _t36;
        }
        return makeFromMatrix_degenerate_s37cad600_tail4(_t27, _t28, _t29, _t116, _t37, _t103, _t38, _t105, _t34, _t33, _t41, _t104, _t35, _t106, _t40, _t39, _t115, _t36, _t114, _t107, _t102, _t166, _t165, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37cad600_tail4(float _t27, float _t28, float _t29, float _t116, float _t37, float _t103, float _t38, float _t105, float _t34, float _t33, float _t41, float _t104, float _t35, float _t106, float _t40, float _t39, float _t115, float _t36, float _t114, float _t107, float _t102, float _t166, float _t165, Float3x4 m) {
        float _t167, _t168;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t167 = 1.0f;
                    _t168 = 0.0f;
                } else {
                    _t167 = _t116;
                    _t168 = Math.fma(_t35, _t106, -(_t33 * _t116));
                }
            } else {
                _t167 = _t29 <= 0.0f ? Math.fma(_t37, _t103, -(_t38 * _t105)) : Math.fma(_t34, _t37, -(_t33 * _t38));
                _t168 = _t38;
            }
        } else {
            _t167 = _t41;
            _t168 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t104 : Math.fma(_t35, _t40, -(_t33 * _t41)) : _t38;
        }
        return makeFromMatrix_degenerate_s37cad600_tail5(_t29, _t27, _t28, _t105, _t39, _t115, _t41, _t104, _t36, _t38, _t33, _t106, _t114, _t103, _t35, _t34, _t40, _t107, _t116, _t102, _t37, _t166, _t167, _t165, _t168, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37cad600_tail5(float _t29, float _t27, float _t28, float _t105, float _t39, float _t115, float _t41, float _t104, float _t36, float _t38, float _t33, float _t106, float _t114, float _t103, float _t35, float _t34, float _t40, float _t107, float _t116, float _t102, float _t37, float _t166, float _t167, float _t165, float _t168, Float3x4 m) {
        float _t169, _t170;
        if (_t29 <= 0.0f) {
            if (_t27 <= 0.0f) {
                if (_t28 <= 0.0f) {
                    _t169 = 0.0f;
                    _t170 = 0.0f;
                } else {
                    _t169 = _t105;
                    _t170 = Math.fma(_t38, _t114, -(_t36 * _t103));
                }
            } else {
                _t169 = _t28 <= 0.0f ? Math.fma(_t39, _t115, -(_t41 * _t104)) : Math.fma(_t39, _t36, -(_t41 * _t38));
                _t170 = _t40;
            }
        } else {
            _t169 = _t33;
            _t170 = _t27 <= 0.0f ? _t28 <= 0.0f ? _t106 : Math.fma(_t35, _t38, -(_t34 * _t36)) : _t40;
        }
        return makeFromMatrix_degenerate_s37cad600_tail6(_t28, _t29, _t27, _t107, _t34, _t116, _t35, _t102, _t41, _t39, _t37, _t114, _t40, _t104, _t38, _t103, _t115, _t36, _t170, _t166, _t167, _t165, _t168, _t169, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37cad600_tail6(float _t28, float _t29, float _t27, float _t107, float _t34, float _t116, float _t35, float _t102, float _t41, float _t39, float _t37, float _t114, float _t40, float _t104, float _t38, float _t103, float _t115, float _t36, float _t170, float _t166, float _t167, float _t165, float _t168, float _t169, Float3x4 m) {
        float _t171, _t172;
        if (_t28 <= 0.0f) {
            if (_t29 <= 0.0f) {
                if (_t27 <= 0.0f) {
                    _t171 = 1.0f;
                    _t172 = 0.0f;
                } else {
                    _t171 = _t107;
                    _t172 = Math.fma(_t40, _t104, -(_t39 * _t107));
                }
            } else {
                _t171 = _t27 <= 0.0f ? Math.fma(_t34, _t116, -(_t35 * _t102)) : Math.fma(_t34, _t41, -(_t35 * _t39));
                _t172 = _t35;
            }
        } else {
            _t171 = _t37;
            _t172 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t114 : Math.fma(_t40, _t38, -(_t39 * _t37)) : _t35;
        }
        return makeFromMatrix_degenerate_s37cad600_tail7(_t29, _t27, _t28, _t103, _t41, _t107, _t40, _t115, _t37, _t36, _t34, _t170, _t166, _t167, _t171, _t165, _t168, _t169, _t172, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37cad600_tail7(float _t29, float _t27, float _t28, float _t103, float _t41, float _t107, float _t40, float _t115, float _t37, float _t36, float _t34, float _t170, float _t166, float _t167, float _t171, float _t165, float _t168, float _t169, float _t172, Float3x4 m) {
        float _t173 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t28 <= 0.0f ? 1.0f : _t103 : _t28 <= 0.0f ? Math.fma(_t41, _t107, -(_t40 * _t115)) : Math.fma(_t41, _t37, -(_t40 * _t36)) : _t34;
        float _t194, _t195, _t196;
        if (Math.fma(Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, Math.fma(Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0f) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        return makeFromMatrix_degenerate_s37cad600_tail8(_t195, _t165, _t194, _t167, _t171, m, _t170 - _t166, java.lang.Math.max(_t167, _t171), _t195 + _t165, _t196 + _t168, _t168 - _t196, _t170 + _t166);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37cad600_tail8(float _t195, float _t165, float _t194, float _t167, float _t171, Float3x4 m, float _t182, float _t183, float _t199, float _t200, float _t201, float _t184) {
        float _t206 = _t194 + _t167 + _t171;
        float _t207 = 1.0f + _t206;
        float _t208 = 1.0f + _t194 - _t167 - _t171;
        float _t209 = 1.0f + _t167 - _t194 - _t171;
        float _t210 = 1.0f + _t171 - _t194 - _t167;
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sfx0 = m.m03();
        float _sfx1 = m.m13();
        float _sfx2 = m.m23();
        float _sfx3 = _t206 > 0.0f ? _sp0 * _t182 : _t194 > _t183 ? 0.5f * (float) java.lang.Math.sqrt(_t208) : _t167 > _t171 ? _sp1 * _t199 : _sp2 * _t200;
        return makeFromMatrix_degenerate_s37cad23f_tail9(_t206, _sp0, _t201, _t194, _t183, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t208)), _t199, _t167, _t171, _t209, _sp2, _t184, _t195 - _t165, _t200, _sp1, _t210, _t207, _t182, _sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static FloatRigid makeFromMatrix_degenerate(Float3x4 m) {
        float _t0 = unitScale(m.m01(), m.m11(), m.m21());
        float _t1 = unitScale(m.m02(), m.m12(), m.m22());
        float _t2 = unitScale(m.m00(), m.m10(), m.m20());
        float _t12 = m.m21() * _t0;
        float _t13 = m.m01() * _t0;
        float _t14 = m.m11() * _t0;
        float _t15 = m.m22() * _t1;
        float _t16 = m.m02() * _t1;
        float _t17 = m.m12() * _t1;
        float _t18 = m.m20() * _t2;
        float _t19 = m.m00() * _t2;
        float _t20 = m.m10() * _t2;
        float _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_s37cad600_tail((1.0f / (float) java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0f / (float) java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t30 * _t20, _t27, _t28, _t29, m);
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
    public static FloatRigid makeFromMatrix(Float4x4 m) {
        float _ct0 = Math.fma(m.m21(), m.m21(), Math.fma(m.m01(), m.m01(), m.m11() * m.m11()));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _ct1 = Math.fma(m.m22(), m.m22(), Math.fma(m.m02(), m.m02(), m.m12() * m.m12()));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = Math.fma(m.m20(), m.m20(), Math.fma(m.m00(), m.m00(), m.m10() * m.m10()));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        return makeFromMatrix_s4d2e9ff7_1(m, _t15, _t16, -m.m11(), -m.m22(), m.m10() * _t17, m.m22() * _t16, m.m12() * _t16, m.m20() * _t17, m.m21() * _t15, m.m11() * _t15, m.m00() * _t17);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatRigid makeFromMatrix_s4d2e9ff7_1(Float4x4 m, float _t15, float _t16, float _t0, float _t1, float _t18, float _t19, float _t20, float _t21, float _t23, float _t24, float _t26) {
        float _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), m.m01() * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), m.m02() * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        float _t51 = 1.0f + _t47;
        float _t52 = 1.0f - _t47;
        float _t63 = Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t51));
        float _t65 = Math.fma(m.m11(), _t15, Math.fma(_t1, _t16, _t52));
        return makeFromMatrix_s4d2e9ff7_2(m, _t15, _t16, _t19, _t24, Math.fma(m.m12(), _t16, _t23), Math.fma(m.m21(), _t15, -_t20), _t47, Math.fma(m.m01(), _t15, _t48), Math.fma(m.m02(), _t16, _t49), Math.fma(m.m02(), _t16, -_t49), Math.fma(-m.m01(), _t15, _t48), _t63, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)), _t65, Math.fma(m.m22(), _t16, Math.fma(_t0, _t15, _t52)), Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatRigid makeFromMatrix_s4d2e9ff7_2(Float4x4 m, float _t15, float _t16, float _t19, float _t24, float _t31, float _t35, float _t47, float _t54, float _t55, float _t56, float _t57, float _t63, float _sp0, float _t65, float _t66, float _t67, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        float _sfx0 = m.m03();
        float _sfx1 = m.m13();
        float _sfx2 = m.m23();
        float _sfx3, _sfx4, _sfx5, _sfx6;
        if (Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t47)) > 0.0f) {
            _sfx3 = _sp0 * _t35;
            _sfx4 = _sp0 * _t56;
            _sfx5 = _sp0 * _t57;
            _sfx6 = 0.5f * (float) java.lang.Math.sqrt(_t63);
        } else {
            if (_t47 > java.lang.Math.max(_t24, _t19)) {
                _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t67);
                _sfx4 = _sp3 * _t54;
                _sfx5 = _sp3 * _t55;
                _sfx6 = _sp3 * _t35;
            } else {
                if (_t24 > _t19) {
                    _sfx3 = _sp1 * _t54;
                    _sfx4 = 0.5f * (float) java.lang.Math.sqrt(_t65);
                    _sfx5 = _sp1 * _t31;
                    _sfx6 = _sp1 * _t56;
                } else {
                    _sfx3 = _sp2 * _t55;
                    _sfx4 = _sp2 * _t31;
                    _sfx5 = 0.5f * (float) java.lang.Math.sqrt(_t66);
                    _sfx6 = _sp2 * _t57;
                }
            }
        }
        return new FloatRigid(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37d8ed81_tail(float _t31, float _t17, float _t15, float _t16, float _t32, float _t13, float _t12, float _t14, float _t33, float _t34, float _t35, float _t27, float _t28, float _t29, Float4x4 m) {
        float _t36 = _t31 * _t17;
        float _t37 = _t31 * _t15;
        float _t38 = _t31 * _t16;
        float _t39 = _t32 * _t13;
        float _t40 = _t32 * _t12;
        float _t41 = _t32 * _t14;
        float _t50 = java.lang.Math.abs(_t40);
        float _t51 = java.lang.Math.abs(_t39);
        float _t72, _t75, _t87;
        if (java.lang.Math.abs(_t33) < java.lang.Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0f;
            _t87 = -_t34;
        } else {
            _t72 = 0.0f;
            _t75 = -_t35;
            _t87 = _t33;
        }
        float _t73, _t76, _t88;
        if (java.lang.Math.abs(_t37) < java.lang.Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0f;
            _t88 = -_t38;
        } else {
            _t73 = 0.0f;
            _t76 = -_t36;
            _t88 = _t37;
        }
        float _t74, _t77;
        if (_t50 < _t51) {
            _t74 = _t41;
            _t77 = 0.0f;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
        }
        return makeFromMatrix_degenerate_s37d8ed81_tail2(_t50, _t51, _t39, _t40, _t75, _t87, _t72, _t76, _t88, _t73, _t77, _t74, _t27, _t28, _t29, _t36, _t37, _t33, _t35, _t34, _t38, _t41, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37d8ed81_tail2(float _t50, float _t51, float _t39, float _t40, float _t75, float _t87, float _t72, float _t76, float _t88, float _t73, float _t77, float _t74, float _t27, float _t28, float _t29, float _t36, float _t37, float _t33, float _t35, float _t34, float _t38, float _t41, Float4x4 m) {
        float _t89 = _t50 < _t51 ? -_t39 : _t40;
        float _t99 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        return makeFromMatrix_degenerate_s37d8ed81_tail3(_t27, _t28, _t29, _t99 * _t72, _t36, _t100 * _t76, _t37, _t100 * _t88, _t33, _t35, _t39, _t101 * _t89, _t34, _t99 * _t75, _t40, _t99 * _t87, _t100 * _t73, _t38, _t41, _t101 * _t74, _t101 * _t77, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37d8ed81_tail3(float _t27, float _t28, float _t29, float _t102, float _t36, float _t105, float _t37, float _t114, float _t33, float _t35, float _t39, float _t115, float _t34, float _t106, float _t40, float _t116, float _t103, float _t38, float _t41, float _t104, float _t107, Float4x4 m) {
        float _t165, _t166;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = 0.0f;
                    _t166 = 0.0f;
                } else {
                    _t165 = _t102;
                    _t166 = Math.fma(_t33, _t102, -(_t34 * _t106));
                }
            } else {
                _t165 = _t29 <= 0.0f ? Math.fma(_t36, _t105, -(_t37 * _t114)) : Math.fma(_t33, _t36, -(_t35 * _t37));
                _t166 = _t36;
            }
        } else {
            _t165 = _t39;
            _t166 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t115 : Math.fma(_t33, _t39, -(_t34 * _t40)) : _t36;
        }
        return makeFromMatrix_degenerate_s37d8ed81_tail4(_t27, _t28, _t29, _t116, _t37, _t103, _t38, _t105, _t34, _t33, _t41, _t104, _t35, _t106, _t40, _t39, _t115, _t36, _t114, _t107, _t102, _t166, _t165, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37d8ed81_tail4(float _t27, float _t28, float _t29, float _t116, float _t37, float _t103, float _t38, float _t105, float _t34, float _t33, float _t41, float _t104, float _t35, float _t106, float _t40, float _t39, float _t115, float _t36, float _t114, float _t107, float _t102, float _t166, float _t165, Float4x4 m) {
        float _t167, _t168;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t167 = 1.0f;
                    _t168 = 0.0f;
                } else {
                    _t167 = _t116;
                    _t168 = Math.fma(_t35, _t106, -(_t33 * _t116));
                }
            } else {
                _t167 = _t29 <= 0.0f ? Math.fma(_t37, _t103, -(_t38 * _t105)) : Math.fma(_t34, _t37, -(_t33 * _t38));
                _t168 = _t38;
            }
        } else {
            _t167 = _t41;
            _t168 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t104 : Math.fma(_t35, _t40, -(_t33 * _t41)) : _t38;
        }
        return makeFromMatrix_degenerate_s37d8ed81_tail5(_t29, _t27, _t28, _t105, _t39, _t115, _t41, _t104, _t36, _t38, _t33, _t106, _t114, _t103, _t35, _t34, _t40, _t107, _t116, _t102, _t37, _t166, _t167, _t165, _t168, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37d8ed81_tail5(float _t29, float _t27, float _t28, float _t105, float _t39, float _t115, float _t41, float _t104, float _t36, float _t38, float _t33, float _t106, float _t114, float _t103, float _t35, float _t34, float _t40, float _t107, float _t116, float _t102, float _t37, float _t166, float _t167, float _t165, float _t168, Float4x4 m) {
        float _t169, _t170;
        if (_t29 <= 0.0f) {
            if (_t27 <= 0.0f) {
                if (_t28 <= 0.0f) {
                    _t169 = 0.0f;
                    _t170 = 0.0f;
                } else {
                    _t169 = _t105;
                    _t170 = Math.fma(_t38, _t114, -(_t36 * _t103));
                }
            } else {
                _t169 = _t28 <= 0.0f ? Math.fma(_t39, _t115, -(_t41 * _t104)) : Math.fma(_t39, _t36, -(_t41 * _t38));
                _t170 = _t40;
            }
        } else {
            _t169 = _t33;
            _t170 = _t27 <= 0.0f ? _t28 <= 0.0f ? _t106 : Math.fma(_t35, _t38, -(_t34 * _t36)) : _t40;
        }
        return makeFromMatrix_degenerate_s37d8ed81_tail6(_t28, _t29, _t27, _t107, _t34, _t116, _t35, _t102, _t41, _t39, _t37, _t114, _t40, _t104, _t38, _t103, _t115, _t36, _t170, _t166, _t167, _t165, _t168, _t169, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37d8ed81_tail6(float _t28, float _t29, float _t27, float _t107, float _t34, float _t116, float _t35, float _t102, float _t41, float _t39, float _t37, float _t114, float _t40, float _t104, float _t38, float _t103, float _t115, float _t36, float _t170, float _t166, float _t167, float _t165, float _t168, float _t169, Float4x4 m) {
        float _t171, _t172;
        if (_t28 <= 0.0f) {
            if (_t29 <= 0.0f) {
                if (_t27 <= 0.0f) {
                    _t171 = 1.0f;
                    _t172 = 0.0f;
                } else {
                    _t171 = _t107;
                    _t172 = Math.fma(_t40, _t104, -(_t39 * _t107));
                }
            } else {
                _t171 = _t27 <= 0.0f ? Math.fma(_t34, _t116, -(_t35 * _t102)) : Math.fma(_t34, _t41, -(_t35 * _t39));
                _t172 = _t35;
            }
        } else {
            _t171 = _t37;
            _t172 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t114 : Math.fma(_t40, _t38, -(_t39 * _t37)) : _t35;
        }
        return makeFromMatrix_degenerate_s37d8ed81_tail7(_t29, _t27, _t28, _t103, _t41, _t107, _t40, _t115, _t37, _t36, _t34, _t170, _t166, _t167, _t171, _t165, _t168, _t169, _t172, m);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37d8ed81_tail7(float _t29, float _t27, float _t28, float _t103, float _t41, float _t107, float _t40, float _t115, float _t37, float _t36, float _t34, float _t170, float _t166, float _t167, float _t171, float _t165, float _t168, float _t169, float _t172, Float4x4 m) {
        float _t173 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t28 <= 0.0f ? 1.0f : _t103 : _t28 <= 0.0f ? Math.fma(_t41, _t107, -(_t40 * _t115)) : Math.fma(_t41, _t37, -(_t40 * _t36)) : _t34;
        float _t194, _t195, _t196;
        if (Math.fma(Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, Math.fma(Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0f) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        return makeFromMatrix_degenerate_s37d8ed81_tail8(_t195, _t165, _t194, _t167, _t171, m, _t170 - _t166, java.lang.Math.max(_t167, _t171), _t195 + _t165, _t196 + _t168, _t168 - _t196, _t170 + _t166);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatRigid makeFromMatrix_degenerate_s37d8ed81_tail8(float _t195, float _t165, float _t194, float _t167, float _t171, Float4x4 m, float _t182, float _t183, float _t199, float _t200, float _t201, float _t184) {
        float _t206 = _t194 + _t167 + _t171;
        float _t207 = 1.0f + _t206;
        float _t208 = 1.0f + _t194 - _t167 - _t171;
        float _t209 = 1.0f + _t167 - _t194 - _t171;
        float _t210 = 1.0f + _t171 - _t194 - _t167;
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sfx0 = m.m03();
        float _sfx1 = m.m13();
        float _sfx2 = m.m23();
        float _sfx3 = _t206 > 0.0f ? _sp0 * _t182 : _t194 > _t183 ? 0.5f * (float) java.lang.Math.sqrt(_t208) : _t167 > _t171 ? _sp1 * _t199 : _sp2 * _t200;
        return makeFromMatrix_degenerate_s37cad23f_tail9(_t206, _sp0, _t201, _t194, _t183, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t208)), _t199, _t167, _t171, _t209, _sp2, _t184, _t195 - _t165, _t200, _sp1, _t210, _t207, _t182, _sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static FloatRigid makeFromMatrix_degenerate(Float4x4 m) {
        float _t0 = unitScale(m.m01(), m.m11(), m.m21());
        float _t1 = unitScale(m.m02(), m.m12(), m.m22());
        float _t2 = unitScale(m.m00(), m.m10(), m.m20());
        float _t12 = m.m21() * _t0;
        float _t13 = m.m01() * _t0;
        float _t14 = m.m11() * _t0;
        float _t15 = m.m22() * _t1;
        float _t16 = m.m02() * _t1;
        float _t17 = m.m12() * _t1;
        float _t18 = m.m20() * _t2;
        float _t19 = m.m00() * _t2;
        float _t20 = m.m10() * _t2;
        float _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_s37d8ed81_tail((1.0f / (float) java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0f / (float) java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t30 * _t20, _t27, _t28, _t29, m);
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
    public static FloatRigid makeFromTransform(FloatTransform t) {
        float tTX = t.tX();
        float tTY = t.tY();
        float tTZ = t.tZ();
        float tRX = t.rX();
        float tRY = t.rY();
        float tRZ = t.rZ();
        float tRW = t.rW();
        return new FloatRigid(tTX, tTY, tTZ, tRX, tRY, tRZ, tRW);
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
    public static FloatRigid makeFromTransform(float tTX, float tTY, float tTZ, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        return new FloatRigid(tTX, tTY, tTZ, tRX, tRY, tRZ, tRW);
    }


    /**
     * Convert this rigid transform to {@code double} precision, returning the result as a new
     * instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code DoubleRigid} holding the result
     */
    public DoubleRigid toDouble() {
        return new DoubleRigid(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW);
    }


    /**
     * Convert this rigid transform to a unit dual quaternion encoding the same rigid motion (an
     * exact conversion), returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @return the resulting dual quaternion
     */
    public FloatDualQuat toDualQuat() {
        float _t0 = -this.tZ;
        return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5f * Math.fma(_t0, this.rY, Math.fma(this.tX, this.rW, this.tY * this.rZ)), 0.5f * Math.fma(this.tZ, this.rX, Math.fma(this.tY, this.rW, -(this.tX * this.rZ))), 0.5f * Math.fma(this.tZ, this.rW, Math.fma(this.tX, this.rY, -(this.tY * this.rX))), 0.5f * Math.fma(_t0, this.rZ, Math.fma(-this.tY, this.rY, -(this.tX * this.rX))));
    }


    /**
     * Compute the matrix representation of this rigid transform, returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @return the resulting matrix
     */
    public Float4x4 toMatrix() {
        float _t0 = this.rZ * this.rZ;
        float _t1 = this.rZ * this.rW;
        float _t2 = this.rY * this.rW;
        return new Float4x4(Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f), 2.0f * Math.fma(this.rX, this.rY, -_t1), 2.0f * Math.fma(this.rX, this.rZ, _t2), this.tX, 2.0f * Math.fma(this.rX, this.rY, _t1), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f), 2.0f * Math.fma(this.rY, this.rZ, -(this.rX * this.rW)), this.tY, 2.0f * Math.fma(this.rX, this.rZ, -_t2), 2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f), this.tZ, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Compute the 3x3 matrix representation of the rotation of this rigid transform (the
     * translation is dropped), returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @return the resulting matrix
     */
    public Float3x3 toMatrix3x3() {
        float _t0 = this.rZ * this.rZ;
        float _t1 = this.rZ * this.rW;
        float _t2 = this.rY * this.rW;
        return new Float3x3(Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f), 2.0f * Math.fma(this.rX, this.rY, -_t1), 2.0f * Math.fma(this.rX, this.rZ, _t2), 2.0f * Math.fma(this.rX, this.rY, _t1), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f), 2.0f * Math.fma(this.rY, this.rZ, -(this.rX * this.rW)), 2.0f * Math.fma(this.rX, this.rZ, -_t2), 2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f), 0);
    }


    /**
     * Compute the 3x4 matrix representation of this rigid transform (the omitted last row is
     * implicitly {@code 0, 0, 0, 1}), returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @return the resulting matrix
     */
    public Float3x4 toMatrix3x4() {
        float _t0 = this.rZ * this.rZ;
        float _t1 = this.rZ * this.rW;
        float _t2 = this.rY * this.rW;
        return new Float3x4(Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f), 2.0f * Math.fma(this.rX, this.rY, -_t1), 2.0f * Math.fma(this.rX, this.rZ, _t2), this.tX, 2.0f * Math.fma(this.rX, this.rY, _t1), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f), 2.0f * Math.fma(this.rY, this.rZ, -(this.rX * this.rW)), this.tY, 2.0f * Math.fma(this.rX, this.rZ, -_t2), 2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f), this.tZ, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Widen this rigid transform to a TRS transform (same translation and rotation, scale = 1),
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting transform
     */
    public FloatTransform toTransform() {
        return new FloatTransform(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW, 1.0f, 1.0f, 1.0f);
    }


    /**
     * Create an identity rigid transform.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting rigid transform
     */
    public static FloatRigid makeIdentity() {
        return new FloatRigid(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
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
    public FloatRigid set(FloatQuat rotation) {
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        return new FloatRigid(0.0f, 0.0f, 0.0f, rotationX, rotationY, rotationZ, rotationW);
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
    public FloatRigid set(float rotationX, float rotationY, float rotationZ, float rotationW) {
        return new FloatRigid(0.0f, 0.0f, 0.0f, rotationX, rotationY, rotationZ, rotationW);
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
    public static FloatRigid makeRotation(FloatQuat rotation) {
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        return new FloatRigid(0.0f, 0.0f, 0.0f, rotationX, rotationY, rotationZ, rotationW);
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
    public static FloatRigid makeRotation(float rotationX, float rotationY, float rotationZ, float rotationW) {
        return new FloatRigid(0.0f, 0.0f, 0.0f, rotationX, rotationY, rotationZ, rotationW);
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
    public FloatRigid set(Float3 translation) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        return new FloatRigid(translationX, translationY, translationZ, 0.0f, 0.0f, 0.0f, 1.0f);
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
    public FloatRigid set(float translationX, float translationY, float translationZ) {
        return new FloatRigid(translationX, translationY, translationZ, 0.0f, 0.0f, 0.0f, 1.0f);
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
    public static FloatRigid makeTranslation(Float3 translation) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        return new FloatRigid(translationX, translationY, translationZ, 0.0f, 0.0f, 0.0f, 1.0f);
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
    public static FloatRigid makeTranslation(float translationX, float translationY, float translationZ) {
        return new FloatRigid(translationX, translationY, translationZ, 0.0f, 0.0f, 0.0f, 1.0f);
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
    public FloatRigid lerp(FloatRigid other, float t) {
        float otherTX = other.tX();
        float otherTY = other.tY();
        float otherTZ = other.tZ();
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float _t0 = 1.0f - t;
        float _t12 = Math.fma(otherRW, this.rW, Math.fma(otherRZ, this.rZ, Math.fma(otherRX, this.rX, otherRY * this.rY)));
        float _t16 = Math.acos(java.lang.Math.min(1.0f, java.lang.Math.abs(_t12)));
        float _t17 = Math.sin(_t16);
        float _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0f) {
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
        return lerp_s49cf416d_tail(_t17, Math.sin(_t0 * _t16), Math.sin(t * _t16), _t21, 1.0f / _t17, t, _t0, _t22, _t23, _t24, otherTX, otherTY, otherTZ);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private FloatRigid lerp_s49cf416d_tail(float _t17, float _t25, float _t19, float _t21, float _t17_inv, float t, float _t0, float _t22, float _t23, float _t24, float otherTX, float otherTY, float otherTZ) {
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
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
        float _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        float _sfx0 = Math.fma(t, otherTX - this.tX, this.tX);
        return lerp_s49cf416d_tail2(t, otherTY, otherTZ, _t49, (1.0f / (float) java.lang.Math.sqrt(_t49)), _t44, _t45, _t43, _t42, _sfx0);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private FloatRigid lerp_s49cf416d_tail2(float t, float otherTY, float otherTZ, float _t49, float _t50, float _t44, float _t45, float _t43, float _t42, float _sfx0) {
        float _sfx1 = Math.fma(t, otherTY - this.tY, this.tY);
        float _sfx2 = Math.fma(t, otherTZ - this.tZ, this.tZ);
        float _sfx3, _sfx4, _sfx5, _sfx6;
        if (_t49 != 0.0f) {
            _sfx3 = _t50 * _t44;
            _sfx4 = _t50 * _t45;
            _sfx5 = _t50 * _t43;
            _sfx6 = _t50 * _t42;
        } else {
            _sfx3 = 0.0f;
            _sfx4 = 0.0f;
            _sfx5 = 0.0f;
            _sfx6 = 0.0f;
        }
        return new FloatRigid(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6);
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
    public FloatRigid lerp(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float t) {
        float _t0 = 1.0f - t;
        float _t12 = Math.fma(otherRW, this.rW, Math.fma(otherRZ, this.rZ, Math.fma(otherRX, this.rX, otherRY * this.rY)));
        float _t16 = Math.acos(java.lang.Math.min(1.0f, java.lang.Math.abs(_t12)));
        float _t17 = Math.sin(_t16);
        float _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0f) {
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
        return lerp_s49cf416d_tail(_t17, Math.sin(_t0 * _t16), Math.sin(t * _t16), _t21, 1.0f / _t17, t, _t0, _t22, _t23, _t24, otherTX, otherTY, otherTZ);
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
    public FloatRigid mul(FloatRigid other) {
        return mul(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW());
    }

    /** Private tail of {@code mul}; reached only through it. */
    private FloatRigid mul_s183b932f_tail(float otherTZ, float otherTY, float _t9, float _t10, float otherTX, float otherRX, float otherRW, float otherRZ, float otherRY) {
        float _t11 = 2.0f * Math.fma(otherTZ, this.rY, -(otherTY * this.rZ));
        return new FloatRigid(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + otherTX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + otherTY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + otherTZ))), Math.fma(otherRX, this.rW, otherRW * this.rX) + Math.fma(otherRZ, this.rY, -(otherRY * this.rZ)), Math.fma(otherRX, this.rZ, otherRW * this.rY) + Math.fma(otherRY, this.rW, -(otherRZ * this.rX)), Math.fma(otherRY, this.rX, otherRZ * this.rW) + Math.fma(otherRW, this.rZ, -(otherRX * this.rY)), Math.fma(otherRW, this.rW, -(otherRX * this.rX)) - Math.fma(otherRY, this.rY, otherRZ * this.rZ));
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
    public FloatRigid mul(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW) {
        return mul_s183b932f_tail(otherTZ, otherTY, 2.0f * Math.fma(otherTY, this.rX, -(otherTX * this.rY)), 2.0f * Math.fma(otherTX, this.rZ, -(otherTZ * this.rX)), otherTX, otherRX, otherRW, otherRZ, otherRY);
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
    public FloatRigid preMul(FloatRigid other) {
        return preMul(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW());
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private FloatRigid preMul_s183b932f_tail(float otherRY, float otherRZ, float _t9, float _t10, float otherRW, float otherTX, float otherRX, float otherTY, float otherTZ) {
        float _t11 = 2.0f * Math.fma(otherRY, this.tZ, -(otherRZ * this.tY));
        return new FloatRigid(Math.fma(otherRY, _t9, Math.fma(-otherRZ, _t10, Math.fma(otherRW, _t11, otherTX + this.tX))), Math.fma(otherRZ, _t11, Math.fma(-otherRX, _t9, Math.fma(otherRW, _t10, otherTY + this.tY))), Math.fma(otherRX, _t10, Math.fma(-otherRY, _t11, Math.fma(otherRW, _t9, otherTZ + this.tZ))), Math.fma(otherRX, this.rW, otherRW * this.rX) + Math.fma(otherRY, this.rZ, -(otherRZ * this.rY)), Math.fma(otherRY, this.rW, otherRZ * this.rX) + Math.fma(otherRW, this.rY, -(otherRX * this.rZ)), Math.fma(otherRX, this.rY, otherRW * this.rZ) + Math.fma(otherRZ, this.rW, -(otherRY * this.rX)), Math.fma(otherRW, this.rW, -(otherRX * this.rX)) - Math.fma(otherRY, this.rY, otherRZ * this.rZ));
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
    public FloatRigid preMul(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW) {
        return preMul_s183b932f_tail(otherRY, otherRZ, 2.0f * Math.fma(otherRX, this.tY, -(otherRY * this.tX)), 2.0f * Math.fma(otherRZ, this.tX, -(otherRX * this.tZ)), otherRW, otherTX, otherRX, otherTY, otherTZ);
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
    public FloatRigid difference(FloatRigid other) {
        float otherTX = other.tX();
        float otherTY = other.tY();
        float otherTZ = other.tZ();
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        float _t18 = 2.0f * Math.fma(otherTZ, this.rX, -(otherTX * this.rZ));
        float _t19 = 2.0f * Math.fma(otherTY, this.rZ, -(otherTZ * this.rY));
        float _t20 = 2.0f * Math.fma(otherTX, this.rY, -(otherTY * this.rX));
        float _t21 = 2.0f * Math.fma(this.tX, this.rZ, -(this.tZ * this.rX));
        float _t22 = 2.0f * Math.fma(this.tY, this.rX, -(this.tX * this.rY));
        float _t23 = 2.0f * Math.fma(this.tZ, this.rY, -(this.tY * this.rZ));
        float _sfx0 = Math.fma(this.rZ, _t18, otherTX) + Math.fma(this.rW, _t19, -(this.rY * _t20)) + (Math.fma(this.rZ, _t21, -(this.rY * _t22)) + Math.fma(this.rW, _t23, -this.tX));
        return difference_s183b932f_tail(_t20, otherTY, _t18, _t19, _t22, _t23, _t21, otherTZ, otherRX, otherRW, otherRY, otherRZ, _sfx0);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private FloatRigid difference_s183b932f_tail(float _t20, float otherTY, float _t18, float _t19, float _t22, float _t23, float _t21, float otherTZ, float otherRX, float otherRW, float otherRY, float otherRZ, float _sfx0) {
        float _sfx1 = Math.fma(this.rX, _t20, otherTY) + Math.fma(this.rW, _t18, -(this.rZ * _t19)) + (Math.fma(this.rX, _t22, -(this.rZ * _t23)) + Math.fma(this.rW, _t21, -this.tY));
        float _sfx2 = Math.fma(this.rY, _t19, otherTZ) + Math.fma(this.rW, _t20, -(this.rX * _t18)) + (Math.fma(this.rY, _t23, -(this.rX * _t21)) + Math.fma(this.rW, _t22, -this.tZ));
        float _sfx3 = Math.fma(otherRX, this.rW, -(otherRW * this.rX)) + Math.fma(otherRY, this.rZ, -(otherRZ * this.rY));
        float _sfx4 = Math.fma(otherRY, this.rW, otherRZ * this.rX) + Math.fma(-otherRX, this.rZ, -(otherRW * this.rY));
        return difference_s183b932f_tail2(otherRX, otherRW, otherRZ, otherRY, _sfx0, _sfx1, _sfx2, _sfx3, _sfx4);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private FloatRigid difference_s183b932f_tail2(float otherRX, float otherRW, float otherRZ, float otherRY, float _sfx0, float _sfx1, float _sfx2, float _sfx3, float _sfx4) {
        float _sfx5 = Math.fma(otherRX, this.rY, -(otherRW * this.rZ)) + Math.fma(otherRZ, this.rW, -(otherRY * this.rX));
        float _sfx6 = Math.fma(otherRX, this.rX, otherRW * this.rW) - Math.fma(-otherRZ, this.rZ, -(otherRY * this.rY));
        return new FloatRigid(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6);
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
    public FloatRigid difference(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW) {
        float _t18 = 2.0f * Math.fma(otherTZ, this.rX, -(otherTX * this.rZ));
        float _t19 = 2.0f * Math.fma(otherTY, this.rZ, -(otherTZ * this.rY));
        float _t20 = 2.0f * Math.fma(otherTX, this.rY, -(otherTY * this.rX));
        float _t21 = 2.0f * Math.fma(this.tX, this.rZ, -(this.tZ * this.rX));
        float _t22 = 2.0f * Math.fma(this.tY, this.rX, -(this.tX * this.rY));
        float _t23 = 2.0f * Math.fma(this.tZ, this.rY, -(this.tY * this.rZ));
        float _sfx0 = Math.fma(this.rZ, _t18, otherTX) + Math.fma(this.rW, _t19, -(this.rY * _t20)) + (Math.fma(this.rZ, _t21, -(this.rY * _t22)) + Math.fma(this.rW, _t23, -this.tX));
        return difference_s183b932f_tail(_t20, otherTY, _t18, _t19, _t22, _t23, _t21, otherTZ, otherRX, otherRW, otherRY, otherRZ, _sfx0);
    }


    /**
     * Invert this rigid transform; exact for any rigid motion (no scale divisions), returning the
     * result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @return the resulting rigid transform
     */
    public FloatRigid invert() {
        float _t0 = -this.rY;
        float _t1 = -this.rZ;
        float _t2 = -this.rX;
        float _t12 = 2.0f * Math.fma(this.tX, this.rZ, -(this.tZ * this.rX));
        float _t13 = 2.0f * Math.fma(this.tY, this.rX, -(this.tX * this.rY));
        float _t14 = 2.0f * Math.fma(this.tZ, this.rY, -(this.tY * this.rZ));
        return new FloatRigid(Math.fma(this.rZ, _t12, Math.fma(_t0, _t13, Math.fma(this.rW, _t14, -this.tX))), Math.fma(this.rX, _t13, Math.fma(_t1, _t14, Math.fma(this.rW, _t12, -this.tY))), Math.fma(this.rY, _t14, Math.fma(_t2, _t12, Math.fma(this.rW, _t13, -this.tZ))), _t2, _t0, _t1, this.rW);
    }


    /**
     * Normalize this rigid transform so that its rotation part has unit length, leaving its
     * translation unchanged (a zero-length rotation yields the zero quaternion), returning the
     * result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting rigid transform
     */
    public FloatRigid normalize() {
        float _t3 = Math.fma(this.rW, this.rW, Math.fma(this.rZ, this.rZ, Math.fma(this.rX, this.rX, this.rY * this.rY)));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            return new FloatRigid(this.tX, this.tY, this.tZ, this.rX * _t4, this.rY * _t4, this.rZ * _t4, this.rW * _t4);
        } else {
            return new FloatRigid(this.tX, this.tY, this.tZ, 0.0f, 0.0f, 0.0f, 0.0f);
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
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
     * Get the rotation of this rigid transform, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting quaternion
     */
    public FloatQuat getRotation() {
        return new FloatQuat(this.rX, this.rY, this.rZ, this.rW);
    }


    /**
     * Get the translation of this rigid transform, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getTranslation() {
        return new Float3(this.tX, this.tY, this.tZ);
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
    public static FloatRigid makeRotationAxis(float angle, Float3 axis) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        return new FloatRigid(0.0f, 0.0f, 0.0f, axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0));
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
    public static FloatRigid makeRotationAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        return new FloatRigid(0.0f, 0.0f, 0.0f, axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0));
    }


    /**
     * Create a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting rigid transform
     */
    public static FloatRigid makeRotationX(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        return new FloatRigid(0.0f, 0.0f, 0.0f, _t1, 0.0f, 0.0f, Math.cosFromSin(_t1, _t0));
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
    public static FloatRigid makeRotationXYZ(float angleX, float angleY, float angleZ) {
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
        return new FloatRigid(0.0f, 0.0f, 0.0f, Math.fma(_t10, _t7, _t11 * _t5), Math.fma(_t11, _t7, -(_t10 * _t5)), Math.fma(_t9, _t7, _t12 * _t5), Math.fma(_t12, _t7, -(_t9 * _t5)));
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
    public static FloatRigid makeRotationXZY(float angleX, float angleZ, float angleY) {
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
        return new FloatRigid(0.0f, 0.0f, 0.0f, Math.fma(_t10, _t7, -(_t11 * _t5)), Math.fma(_t12, _t5, -(_t9 * _t7)), Math.fma(_t10, _t5, _t11 * _t7), Math.fma(_t9, _t5, _t12 * _t7));
    }


    /**
     * Create a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting rigid transform
     */
    public static FloatRigid makeRotationY(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        return new FloatRigid(0.0f, 0.0f, 0.0f, 0.0f, _t1, 0.0f, Math.cosFromSin(_t1, _t0));
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
    public static FloatRigid makeRotationYXZ(float angleY, float angleX, float angleZ) {
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
        return new FloatRigid(0.0f, 0.0f, 0.0f, Math.fma(_t10, _t7, _t11 * _t5), Math.fma(_t11, _t7, -(_t10 * _t5)), Math.fma(_t12, _t5, -(_t9 * _t7)), Math.fma(_t9, _t5, _t12 * _t7));
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
    public static FloatRigid makeRotationYZX(float angleY, float angleZ, float angleX) {
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
        return new FloatRigid(0.0f, 0.0f, 0.0f, Math.fma(_t9, _t6, _t12 * _t5), Math.fma(_t10, _t6, _t11 * _t5), Math.fma(_t11, _t6, -(_t10 * _t5)), Math.fma(_t12, _t6, -(_t9 * _t5)));
    }


    /**
     * Create a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting rigid transform
     */
    public static FloatRigid makeRotationZ(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        return new FloatRigid(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, _t1, Math.cosFromSin(_t1, _t0));
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
    public static FloatRigid makeRotationZXY(float angleZ, float angleX, float angleY) {
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
        return new FloatRigid(0.0f, 0.0f, 0.0f, Math.fma(_t10, _t7, -(_t11 * _t5)), Math.fma(_t9, _t7, _t12 * _t5), Math.fma(_t10, _t5, _t11 * _t7), Math.fma(_t12, _t7, -(_t9 * _t5)));
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
    public static FloatRigid makeRotationZYX(float angleZ, float angleY, float angleX) {
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
        return new FloatRigid(0.0f, 0.0f, 0.0f, Math.fma(_t12, _t5, -(_t9 * _t8)), Math.fma(_t10, _t8, _t11 * _t5), Math.fma(_t11, _t8, -(_t10 * _t5)), Math.fma(_t9, _t5, _t12 * _t8));
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
    public FloatRigid rotate(FloatQuat rotation) {
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        return new FloatRigid(this.tX, this.tY, this.tZ, Math.fma(rotationX, this.rW, rotationW * this.rX) + Math.fma(rotationZ, this.rY, -(rotationY * this.rZ)), Math.fma(rotationX, this.rZ, rotationW * this.rY) + Math.fma(rotationY, this.rW, -(rotationZ * this.rX)), Math.fma(rotationY, this.rX, rotationZ * this.rW) + Math.fma(rotationW, this.rZ, -(rotationX * this.rY)), Math.fma(rotationW, this.rW, -(rotationX * this.rX)) - Math.fma(rotationY, this.rY, rotationZ * this.rZ));
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
    public FloatRigid rotate(float rotationX, float rotationY, float rotationZ, float rotationW) {
        return new FloatRigid(this.tX, this.tY, this.tZ, Math.fma(rotationX, this.rW, rotationW * this.rX) + Math.fma(rotationZ, this.rY, -(rotationY * this.rZ)), Math.fma(rotationX, this.rZ, rotationW * this.rY) + Math.fma(rotationY, this.rW, -(rotationZ * this.rX)), Math.fma(rotationY, this.rX, rotationZ * this.rW) + Math.fma(rotationW, this.rZ, -(rotationX * this.rY)), Math.fma(rotationW, this.rW, -(rotationX * this.rX)) - Math.fma(rotationY, this.rY, rotationZ * this.rZ));
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
    public FloatRigid rotateAxis(float angle, Float3 axis) {
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
    public FloatRigid rotateAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        return new FloatRigid(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t5, this.rW * _t2) + Math.fma(this.rY, _t3, -(this.rZ * _t4)), Math.fma(this.rY, _t5, this.rZ * _t2) + Math.fma(this.rW, _t4, -(this.rX * _t3)), Math.fma(this.rX, _t4, this.rW * _t3) + Math.fma(this.rZ, _t5, -(this.rY * _t2)), Math.fma(this.rW, _t5, -(this.rX * _t2)) - Math.fma(this.rY, _t4, this.rZ * _t3));
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
    public FloatRigid rotateX(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        return new FloatRigid(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t2, this.rW * _t1), Math.fma(this.rY, _t2, this.rZ * _t1), Math.fma(this.rZ, _t2, -(this.rY * _t1)), Math.fma(this.rW, _t2, -(this.rX * _t1)));
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private FloatRigid rotateXYZ_s6e793366_tail(float _t11, float _t8, float _t10, float _t5, float _t21, float _t19, float _t20) {
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        return new FloatRigid(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t21, this.rW * _t19) + Math.fma(this.rY, _t20, -(this.rZ * _t22)), Math.fma(this.rY, _t21, this.rZ * _t19) + Math.fma(this.rW, _t22, -(this.rX * _t20)), Math.fma(this.rX, _t22, this.rW * _t20) + Math.fma(this.rZ, _t21, -(this.rY * _t19)), Math.fma(this.rW, _t21, -(this.rX * _t19)) - Math.fma(this.rY, _t22, this.rZ * _t20));
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
    public FloatRigid rotateXYZ(float angleX, float angleY, float angleZ) {
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
        return rotateXYZ_s6e793366_tail(_t11, _t8, _t10, _t5, Math.fma(_t14, _t8, -(_t9 * _t5)), Math.fma(_t10, _t8, _t11 * _t5), Math.fma(_t9, _t8, _t14 * _t5));
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
    public FloatRigid rotateXZY(float angleX, float angleZ, float angleY) {
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
        return rotateXYZ_s6e793366_tail(_t12, _t5, _t9, _t8, Math.fma(_t9, _t5, _t12 * _t8), Math.fma(_t10, _t8, -(_t11 * _t5)), Math.fma(_t10, _t5, _t11 * _t8));
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
    public FloatRigid rotateY(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        return new FloatRigid(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t2, -(this.rZ * _t1)), Math.fma(this.rY, _t2, this.rW * _t1), Math.fma(this.rX, _t1, this.rZ * _t2), Math.fma(this.rW, _t2, -(this.rY * _t1)));
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
    public FloatRigid rotateYXZ(float angleY, float angleX, float angleZ) {
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
        return rotateXYZ_s6e793366_tail(_t11, _t8, _t10, _t5, Math.fma(_t9, _t5, _t12 * _t8), Math.fma(_t10, _t8, _t11 * _t5), Math.fma(_t12, _t5, -(_t9 * _t8)));
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private FloatRigid rotateYZX_s71a48226_tail(float _t10, float _t8, float _t11, float _t5, float _t21, float _t19, float _t20) {
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return new FloatRigid(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t21, this.rW * _t19) + Math.fma(this.rY, _t22, -(this.rZ * _t20)), Math.fma(this.rY, _t21, this.rZ * _t19) + Math.fma(this.rW, _t20, -(this.rX * _t22)), Math.fma(this.rX, _t20, this.rW * _t22) + Math.fma(this.rZ, _t21, -(this.rY * _t19)), Math.fma(this.rW, _t21, -(this.rX * _t19)) - Math.fma(this.rY, _t20, this.rZ * _t22));
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
    public FloatRigid rotateYZX(float angleY, float angleZ, float angleX) {
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
        return rotateYZX_s71a48226_tail(_t10, _t8, _t11, _t5, Math.fma(_t14, _t8, -(_t9 * _t5)), Math.fma(_t9, _t8, _t14 * _t5), Math.fma(_t11, _t8, _t10 * _t5));
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
    public FloatRigid rotateZ(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        return new FloatRigid(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t2, this.rY * _t1), Math.fma(this.rY, _t2, -(this.rX * _t1)), Math.fma(this.rZ, _t2, this.rW * _t1), Math.fma(this.rW, _t2, -(this.rZ * _t1)));
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private FloatRigid rotateZXY_s673d9226_tail(float _t10, float _t8, float _t11, float _t5, float _t21, float _t19, float _t20) {
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return new FloatRigid(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t21, this.rW * _t22) + Math.fma(this.rY, _t19, -(this.rZ * _t20)), Math.fma(this.rY, _t21, this.rZ * _t22) + Math.fma(this.rW, _t20, -(this.rX * _t19)), Math.fma(this.rX, _t20, this.rW * _t19) + Math.fma(this.rZ, _t21, -(this.rY * _t22)), Math.fma(this.rW, _t21, -(this.rX * _t22)) - Math.fma(this.rY, _t20, this.rZ * _t19));
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
    public FloatRigid rotateZXY(float angleZ, float angleX, float angleY) {
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
        return rotateZXY_s673d9226_tail(_t10, _t8, _t11, _t5, Math.fma(_t14, _t8, -(_t9 * _t5)), Math.fma(_t10, _t5, _t11 * _t8), Math.fma(_t9, _t8, _t14 * _t5));
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
    public FloatRigid rotateZYX(float angleZ, float angleY, float angleX) {
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
        return rotateYZX_s71a48226_tail(_t10, _t8, _t11, _t5, Math.fma(_t9, _t5, _t12 * _t8), Math.fma(_t12, _t5, -(_t9 * _t8)), Math.fma(_t11, _t8, _t10 * _t5));
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
    public FloatRigid translate(Float3 translation) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        float _t9 = 2.0f * Math.fma(this.rX, translationY, -(this.rY * translationX));
        float _t10 = 2.0f * Math.fma(this.rZ, translationX, -(this.rX * translationZ));
        float _t11 = 2.0f * Math.fma(this.rY, translationZ, -(this.rZ * translationY));
        return new FloatRigid(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + translationX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + translationY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + translationZ))), this.rX, this.rY, this.rZ, this.rW);
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
    public FloatRigid translate(float translationX, float translationY, float translationZ) {
        float _t9 = 2.0f * Math.fma(this.rX, translationY, -(this.rY * translationX));
        float _t10 = 2.0f * Math.fma(this.rZ, translationX, -(this.rX * translationZ));
        float _t11 = 2.0f * Math.fma(this.rY, translationZ, -(this.rZ * translationY));
        return new FloatRigid(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + translationX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + translationY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + translationZ))), this.rX, this.rY, this.rZ, this.rW);
    }


    /**
     * Transform {@code v} by this rigid transform, returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param v the vector to transform
     * @return the resulting vector
     */
    public Float3 transform(Float3 v) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Float3(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + vX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + vY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + vZ))));
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
    public Float3 transform(float vX, float vY, float vZ) {
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Float3(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + vX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + vY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + vZ))));
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
    public Float3 transformDirection(float vX, float vY, float vZ) {
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Float3(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ))));
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
    public Float3 transformDirectionInverse(float vX, float vY, float vZ) {
        float _t9 = 2.0f * Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Float3(Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX))), Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY))), Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ))));
    }


    /**
     * Transform {@code p} by the inverse of this rigid transform, returning the result as a value.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param p the position to transform
     * @return the resulting vector
     */
    public Float3 transformInverse(Float3 p) {
        float _t0 = p.z() - this.tZ;
        float _t1 = p.x() - this.tX;
        float _t2 = p.y() - this.tY;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        return new Float3(Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1))), Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2))), Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0))));
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
    public Float3 transformInverse(float pX, float pY, float pZ) {
        float _t0 = pZ - this.tZ;
        float _t1 = pX - this.tX;
        float _t2 = pY - this.tY;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        return new Float3(Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1))), Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2))), Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0))));
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
    public Float3 transformPosition(Float3 v) {
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
    public Float3 transformPosition(float vX, float vY, float vZ) {
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Float3(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + vX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + vY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + vZ))));
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
    public Float3 transformPositionInverse(Float3 p) {
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
    public Float3 transformPositionInverse(float pX, float pY, float pZ) {
        float _t0 = pZ - this.tZ;
        float _t1 = pX - this.tX;
        float _t2 = pY - this.tY;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        return new Float3(Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1))), Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2))), Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0))));
    }

    /**
     * {@return a copy with the {@code tX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code tX} component
     */
    public FloatRigid withTX(float v) {
        return new FloatRigid(v, tY, tZ, rX, rY, rZ, rW);
    }

    /**
     * {@return a copy with the {@code tY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code tY} component
     */
    public FloatRigid withTY(float v) {
        return new FloatRigid(tX, v, tZ, rX, rY, rZ, rW);
    }

    /**
     * {@return a copy with the {@code tZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code tZ} component
     */
    public FloatRigid withTZ(float v) {
        return new FloatRigid(tX, tY, v, rX, rY, rZ, rW);
    }

    /**
     * {@return a copy with the {@code rX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rX} component
     */
    public FloatRigid withRX(float v) {
        return new FloatRigid(tX, tY, tZ, v, rY, rZ, rW);
    }

    /**
     * {@return a copy with the {@code rY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rY} component
     */
    public FloatRigid withRY(float v) {
        return new FloatRigid(tX, tY, tZ, rX, v, rZ, rW);
    }

    /**
     * {@return a copy with the {@code rZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rZ} component
     */
    public FloatRigid withRZ(float v) {
        return new FloatRigid(tX, tY, tZ, rX, rY, v, rW);
    }

    /**
     * {@return a copy with the {@code rW} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rW} component
     */
    public FloatRigid withRW(float v) {
        return new FloatRigid(tX, tY, tZ, rX, rY, rZ, v);
    }

    @Override public String toString() {
        return "FloatRigid(" + tX() + ", " + tY() + ", " + tZ() + ", " + rX() + ", " + rY() + ", " + rZ() + ", " + rW() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatRigid)) return false;
        FloatRigid o = (FloatRigid) obj;
        return Float.floatToIntBits(tX) == Float.floatToIntBits(o.tX)
            && Float.floatToIntBits(tY) == Float.floatToIntBits(o.tY)
            && Float.floatToIntBits(tZ) == Float.floatToIntBits(o.tZ)
            && Float.floatToIntBits(rX) == Float.floatToIntBits(o.rX)
            && Float.floatToIntBits(rY) == Float.floatToIntBits(o.rY)
            && Float.floatToIntBits(rZ) == Float.floatToIntBits(o.rZ)
            && Float.floatToIntBits(rW) == Float.floatToIntBits(o.rW);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(tX);
        h = 31 * h + Float.floatToIntBits(tY);
        h = 31 * h + Float.floatToIntBits(tZ);
        h = 31 * h + Float.floatToIntBits(rX);
        h = 31 * h + Float.floatToIntBits(rY);
        h = 31 * h + Float.floatToIntBits(rZ);
        h = 31 * h + Float.floatToIntBits(rW);
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Float.isFinite(tX)
            && Float.isFinite(tY)
            && Float.isFinite(tZ)
            && Float.isFinite(rX)
            && Float.isFinite(rY)
            && Float.isFinite(rZ)
            && Float.isFinite(rW);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Float.isNaN(tX)
            || Float.isNaN(tY)
            || Float.isNaN(tZ)
            || Float.isNaN(rX)
            || Float.isNaN(rY)
            || Float.isNaN(rZ)
            || Float.isNaN(rW);
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
    public boolean equalsEpsilon(FloatRigid other, float epsilon) {
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
        static final FloatRigidSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatRigidSegOpsUnsafe()
                        : new FloatRigidSegOpsMS();
        static final FloatRigidBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatRigidBbOpsUnsafe()
                        : new FloatRigidBbOpsApi();
        static final FloatRigidRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatRigidRawOpsUnsafe()
                        : new FloatRigidRawOpsApi();
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
    public float[] store(float[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid load(float[] src, int offset) {
        float _c0 = src[offset];
        float _c1 = src[offset + 1];
        float _c2 = src[offset + 2];
        float _c3 = src[offset + 3];
        float _c4 = src[offset + 4];
        float _c5 = src[offset + 5];
        float _c6 = src[offset + 6];
        return new FloatRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid load(float[] src) { return load(src, 0); }

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
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid load(FloatBuffer buf) {
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
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid loadAbsolute(int index, FloatBuffer buf) {
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
     * @return a new {@code FloatRigid} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatRigid loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 7) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatRigid r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
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
        if (buf.remaining() < 28) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 28);
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
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid load(ByteBuffer buf) {
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
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code FloatRigid} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatRigid loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 28) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatRigid r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 28);
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
    public FloatRigid storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid loadUnsafe(long address) {
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
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

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
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid load(long offset, MemorySegment src) {
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
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid load(double[] src, int offset) {
        float _c0 = (float) src[offset];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[offset + 2];
        float _c3 = (float) src[offset + 3];
        float _c4 = (float) src[offset + 4];
        float _c5 = (float) src[offset + 5];
        float _c6 = (float) src[offset + 6];
        return new FloatRigid(_c0, _c1, _c2, _c3, _c4, _c5, _c6);
    }

    /**
     * Load the elements from the given array, converting each element from {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid load(double[] src) { return load(src, 0); }

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
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
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
        if (buf.remaining() < 7) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 7);
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
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
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
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid loadAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code FloatRigid} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatRigid loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 7) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatRigid r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 7);
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
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
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
        if (buf.remaining() < 56) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 56);
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
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(buf.position(), buf);
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
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid loadDoubleAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code FloatRigid} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatRigid loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 56) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatRigid r = StoreLoad.BB_OPS.loadDoubleAbsolute(pos, buf);
        buf.position(pos + 56);
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
    public FloatRigid storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid loadDoubleUnsafe(long address) {
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
    public MemorySegment storeDouble(MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }

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
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(0L, src); }

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
     * @return a new {@code FloatRigid} holding the loaded elements
     */
    public static FloatRigid loadDouble(long offset, MemorySegment src) {
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
