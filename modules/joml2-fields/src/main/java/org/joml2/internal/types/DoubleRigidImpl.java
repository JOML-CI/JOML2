// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link DoubleRigid} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoubleRigidImpl implements DoubleRigid {

    public double tX;
    public double tY;
    public double tZ;
    public double rX;
    public double rY;
    public double rZ;
    public double rW;

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

    public DoubleRigidImpl() {
        rW = 1;
    }

    public DoubleRigidImpl(double tX, double tY, double tZ, double rX, double rY, double rZ, double rW) {
        this.tX = tX;
        this.tY = tY;
        this.tZ = tZ;
        this.rX = rX;
        this.rY = rY;
        this.rZ = rZ;
        this.rW = rW;
    }

    public DoubleRigidImpl(DoubleRigidR src) {
        this.tX = src.tX();
        this.tY = src.tY();
        this.tZ = src.tZ();
        this.rX = src.rX();
        this.rY = src.rY();
        this.rZ = src.rZ();
        this.rW = src.rW();
    }


    /**
     * Set this rigid transform to the rotation of {@code angle} radians about the axis
     * {@code axis}, combined with a translation by {@code translation}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param axis the rotation axis
     * @param angle the angle in radians
     * @param translation the translation
     * @return this
     */
    public @Mutated DoubleRigid makeFromAxisAngle(Double3R axis, double angle, Double3R translation) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        double translationY = translation.y();
        double translationZ = translation.z();
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        this.tX = translation.x();
        this.tY = translationY;
        this.tZ = translationZ;
        this.rX = axisX * _t1;
        this.rY = axisY * _t1;
        this.rZ = axisZ * _t1;
        this.rW = Math.cosFromSin(_t1, _t0);
        return this;
    }


    /**
     * Set this rigid transform to the rotation of {@code angle} radians about the axis
     * ({@code axisX}, {@code axisY}, {@code axisZ}), combined with a translation by
     * ({@code translationX}, {@code translationY}, {@code translationZ}).
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
     * @return this
     */
    @Mutated public DoubleRigid makeFromAxisAngle(double axisX, double axisY, double axisZ, double angle, double translationX, double translationY, double translationZ) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        this.tX = translationX;
        this.tY = translationY;
        this.tZ = translationZ;
        this.rX = axisX * _t1;
        this.rY = axisY * _t1;
        this.rZ = axisZ * _t1;
        this.rW = Math.cosFromSin(_t1, _t0);
        return this;
    }


    /**
     * Set this rigid transform to a rigid transformation that first rotates by {@code rotation} and
     * then translates by {@code translation} ({@code T * R}).
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param translation the translation
     * @param rotation the rotation
     * @return this
     */
    public @Mutated DoubleRigid makeTranslationRotation(Double3R translation, DoubleQuatR rotation) {
        double translationY = translation.y();
        double translationZ = translation.z();
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        this.tX = translation.x();
        this.tY = translationY;
        this.tZ = translationZ;
        this.rX = rotationX;
        this.rY = rotationY;
        this.rZ = rotationZ;
        this.rW = rotationW;
        return this;
    }


    /**
     * Set this rigid transform to a rigid transformation that first rotates by ({@code rotationX},
     * {@code rotationY}, {@code rotationZ}, {@code rotationW}) and then translates by
     * ({@code translationX}, {@code translationY}, {@code translationZ}) ({@code T * R}).
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
     * @return this
     */
    @Mutated public DoubleRigid makeTranslationRotation(double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW) {
        this.tX = translationX;
        this.tY = translationY;
        this.tZ = translationZ;
        this.rX = rotationX;
        this.rY = rotationY;
        this.rZ = rotationZ;
        this.rW = rotationW;
        return this;
    }


    /**
     * Set this rigid transform to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the rigid transform to copy
     * @return this
     */
    public @Mutated DoubleRigid set(DoubleRigidR v) {
        double vTY = v.tY();
        double vTZ = v.tZ();
        double vRX = v.rX();
        double vRY = v.rY();
        double vRZ = v.rZ();
        double vRW = v.rW();
        this.tX = v.tX();
        this.tY = vTY;
        this.tZ = vTZ;
        this.rX = vRX;
        this.rY = vRY;
        this.rZ = vRZ;
        this.rW = vRW;
        return this;
    }


    /**
     * Set this rigid transform to the given values.
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
     * @return this
     */
    @Mutated public DoubleRigid set(double vTX, double vTY, double vTZ, double vRX, double vRY, double vRZ, double vRW) {
        this.tX = vTX;
        this.tY = vTY;
        this.tZ = vTZ;
        this.rX = vRX;
        this.rY = vRY;
        this.rZ = vRZ;
        this.rW = vRW;
        return this;
    }


    /**
     * Set the rotation of this rigid transform to {@code r} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code r} must have unit length.
     *
     * @param r the new rotation
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid setRotation(DoubleQuatR r, @Mutated DoubleRigid dest) {
        double rX = r.x();
        double rY = r.y();
        double rZ = r.z();
        double rW = r.w();
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = rX;
        d.rY = rY;
        d.rZ = rZ;
        d.rW = rW;
        return d;
    }


    /**
     * Set the rotation of this rigid transform to ({@code rX}, {@code rY}, {@code rZ}, {@code rW})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (rX, rY, rZ, rW)} must have unit length.
     *
     * @param rX the {@code x} component of the quaternion {@code (rX, rY, rZ, rW)}
     * @param rY the {@code y} component of the quaternion {@code (rX, rY, rZ, rW)}
     * @param rZ the {@code z} component of the quaternion {@code (rX, rY, rZ, rW)}
     * @param rW the {@code w} component of the quaternion {@code (rX, rY, rZ, rW)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid setRotation(double rX, double rY, double rZ, double rW, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = rX;
        d.rY = rY;
        d.rZ = rZ;
        d.rW = rW;
        return d;
    }


    /**
     * Set the translation of this rigid transform to {@code t} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the translation vector
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid setTranslation(Double3R t, @Mutated DoubleRigid dest) {
        double tY = t.y();
        double tZ = t.z();
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        d.tX = t.x();
        d.tY = tY;
        d.tZ = tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        return d;
    }


    /**
     * Set the translation of this rigid transform to ({@code tX}, {@code tY}, {@code tZ}) and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param tX the {@code x} component of the vector {@code (tX, tY, tZ)}
     * @param tY the {@code y} component of the vector {@code (tX, tY, tZ)}
     * @param tZ the {@code z} component of the vector {@code (tX, tY, tZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid setTranslation(double tX, double tY, double tZ, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        d.tX = tX;
        d.tY = tY;
        d.tZ = tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        return d;
    }


    /**
     * Set this rigid transform to the rigid motion of the unit dual quaternion {@code dq} (an exact
     * conversion - both represent rotation plus translation).
     * <p>
     * Valid input: {@code dq} must be a unit dual quaternion.
     *
     * @param dq the dual quaternion to convert
     * @return this
     */
    public @Mutated DoubleRigid makeFromDualQuat(DoubleDualQuatR dq) {
        return makeFromDualQuat(dq.rX(), dq.rY(), dq.rZ(), dq.rW(), dq.dX(), dq.dY(), dq.dZ(), dq.dW());
    }


    /**
     * Set this rigid transform to the rigid motion of the unit dual quaternion ({@code dqRX},
     * {@code dqRY}, {@code dqRZ}, {@code dqRW}, {@code dqDX}, {@code dqDY}, {@code dqDZ},
     * {@code dqDW}) (an exact conversion - both represent rotation plus translation).
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
     * @return this
     */
    @Mutated public DoubleRigid makeFromDualQuat(double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        if (Math.useFma()) {
            this.tX = 2.0 * (java.lang.Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + java.lang.Math.fma(dqRW, dqDX, -(dqRX * dqDW)));
            this.tY = 2.0 * (java.lang.Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + java.lang.Math.fma(dqRW, dqDY, -(dqRY * dqDW)));
            this.tZ = 2.0 * (java.lang.Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + java.lang.Math.fma(dqRW, dqDZ, -(dqRZ * dqDW)));
            this.rX = dqRX;
            this.rY = dqRY;
            this.rZ = dqRZ;
            this.rW = dqRW;
            return this;
        } else {
            this.tX = 2.0 * (((dqRY) * (dqDZ) - (dqRZ * dqDY)) + ((dqRW) * (dqDX) - (dqRX * dqDW)));
            this.tY = 2.0 * (((dqRZ) * (dqDX) - (dqRX * dqDZ)) + ((dqRW) * (dqDY) - (dqRY * dqDW)));
            this.tZ = 2.0 * (((dqRX) * (dqDY) - (dqRY * dqDX)) + ((dqRW) * (dqDZ) - (dqRZ * dqDW)));
            this.rX = dqRX;
            this.rY = dqRY;
            this.rZ = dqRZ;
            this.rW = dqRW;
            return this;
        }
    }


    /**
     * Set this rigid transform to the rotation extracted from the given matrix, with zero
     * translation (scale is removed by normalizing the columns, but shear is not removed: a sheared
     * block yields a rotation quaternion that is not unit length).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public DoubleRigid makeFromMatrix(Double3x3R m) {
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid makeFromMatrix_fma(Double3x3R m) {
        double _r0 = m.m11();
        double _r1 = m.m22();
        double _r2 = m.m21();
        double _r3 = m.m01();
        double _r4 = m.m02();
        double _r5 = m.m12();
        double _r6 = m.m20();
        double _r7 = m.m00();
        double _r8 = m.m10();
        double _ct0 = java.lang.Math.fma(_r2, _r2, java.lang.Math.fma(_r3, _r3, _r0 * _r0));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t15 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _ct1 = java.lang.Math.fma(_r1, _r1, java.lang.Math.fma(_r4, _r4, _r5 * _r5));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _ct2 = java.lang.Math.fma(_r6, _r6, java.lang.Math.fma(_r7, _r7, _r8 * _r8));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        double _t23 = _r2 * _t15;
        return makeFromMatrix_s81574d1e_1_fma(this, _r0, _r1, _r2, _r3, _r4, _t15, _t16, -_r0, -_r1, _r8 * _t17, _r1 * _t16, _r5 * _t16, _r6 * _t17, _t23, _r0 * _t15, _r7 * _t17, java.lang.Math.fma(_r5, _t16, _t23));
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid makeFromMatrix_mulAdd(Double3x3R m) {
        double _r0 = m.m11();
        double _r1 = m.m22();
        double _r2 = m.m21();
        double _r3 = m.m01();
        double _r4 = m.m02();
        double _r5 = m.m12();
        double _r6 = m.m20();
        double _r7 = m.m00();
        double _r8 = m.m10();
        double _ct0 = ((_r2) * (_r2) + (((_r3) * (_r3) + (_r0 * _r0))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t15 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _ct1 = ((_r1) * (_r1) + (((_r4) * (_r4) + (_r5 * _r5))));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _ct2 = ((_r6) * (_r6) + (((_r7) * (_r7) + (_r8 * _r8))));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        double _t23 = _r2 * _t15;
        return makeFromMatrix_s81574d1e_1_mulAdd(this, _r0, _r1, _r2, _r3, _r4, _t15, _t16, -_r0, -_r1, _r8 * _t17, _r1 * _t16, _r5 * _t16, _r6 * _t17, _t23, _r0 * _t15, _r7 * _t17, ((_r5) * (_t16) + (_t23)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleRigid makeFromMatrix_s81574d1e_1_fma(DoubleRigidImpl d, double _r0, double _r1, double _r2, double _r3, double _r4, double _t15, double _t16, double _t0, double _t1, double _t18, double _t19, double _t20, double _t21, double _t23, double _t24, double _t26, double _t31) {
        double _t47, _t48, _t49;
        if (java.lang.Math.fma(-java.lang.Math.fma(_t18, _t19, -(_t20 * _t21)), _r3 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t18, _t23, -(_t24 * _t21)), _r4 * _t16, java.lang.Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0) {
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
        double _t63 = java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_r1, _t16, _t51));
        double _t65 = java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        double _t66 = java.lang.Math.fma(_r1, _t16, java.lang.Math.fma(_t0, _t15, _t52));
        return makeFromMatrix_s81574d1e_2(d, _t19, _t24, _t31, java.lang.Math.fma(_r2, _t15, -_t20), java.lang.Math.max(_t24, _t19), _t47, java.lang.Math.fma(_r3, _t15, _t48), java.lang.Math.fma(_r4, _t16, _t49), java.lang.Math.fma(_r4, _t16, -_t49), java.lang.Math.fma(-_r3, _t15, _t48), java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_r1, _t16, _t47)), _t63, 0.5 * (1.0 / java.lang.Math.sqrt(_t63)), _t65, _t66, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t51)), 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t66)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleRigid makeFromMatrix_s81574d1e_1_mulAdd(DoubleRigidImpl d, double _r0, double _r1, double _r2, double _r3, double _r4, double _t15, double _t16, double _t0, double _t1, double _t18, double _t19, double _t20, double _t21, double _t23, double _t24, double _t26, double _t31) {
        double _t47, _t48, _t49;
        if (((-((_t18) * (_t19) - (_t20 * _t21))) * (_r3 * _t15) + (((((_t18) * (_t23) - (_t24 * _t21))) * (_r4 * _t16) + (((_t24) * (_t19) - (_t20 * _t23)) * _t26)))) < 0.0) {
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
        double _t63 = ((_r0) * (_t15) + (((_r1) * (_t16) + (_t51))));
        double _t65 = ((_r0) * (_t15) + (((_t1) * (_t16) + (_t52))));
        double _t66 = ((_r1) * (_t16) + (((_t0) * (_t15) + (_t52))));
        return makeFromMatrix_s81574d1e_2(d, _t19, _t24, _t31, ((_r2) * (_t15) - (_t20)), java.lang.Math.max(_t24, _t19), _t47, ((_r3) * (_t15) + (_t48)), ((_r4) * (_t16) + (_t49)), ((_r4) * (_t16) - (_t49)), ((-_r3) * (_t15) + (_t48)), ((_r0) * (_t15) + (((_r1) * (_t16) + (_t47)))), _t63, 0.5 * (1.0 / java.lang.Math.sqrt(_t63)), _t65, _t66, ((_t0) * (_t15) + (((_t1) * (_t16) + (_t51)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t66)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleRigid makeFromMatrix_s81574d1e_2(DoubleRigidImpl d, double _t19, double _t24, double _t31, double _t35, double _t36, double _t47, double _t54, double _t55, double _t56, double _t57, double _t62, double _t63, double _sp0, double _t65, double _t66, double _t67, double _sp1, double _sp2) {
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        d.tX = 0.0;
        d.tY = 0.0;
        d.tZ = 0.0;
        d.rX = _t62 > 0.0 ? _sp0 * _t35 : _t47 > _t36 ? 0.5 * java.lang.Math.sqrt(_t67) : _t24 > _t19 ? _sp1 * _t54 : _sp2 * _t55;
        d.rY = _t62 > 0.0 ? _sp0 * _t56 : _t47 > _t36 ? _sp3 * _t54 : _t24 > _t19 ? 0.5 * java.lang.Math.sqrt(_t65) : _sp2 * _t31;
        d.rZ = _t62 > 0.0 ? _sp0 * _t57 : _t47 > _t36 ? _sp3 * _t55 : _t24 > _t19 ? _sp1 * _t31 : 0.5 * java.lang.Math.sqrt(_t66);
        d.rW = _t62 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t63) : _t47 > _t36 ? _sp3 * _t35 : _t24 > _t19 ? _sp1 * _t56 : _sp2 * _t57;
        return d;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private DoubleRigid makeFromMatrix_degenerate_fma(Double3x3R m) {
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
        double _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t33 = _t30 * _t18;
        double _t34 = _t30 * _t19;
        double _t35 = _t30 * _t20;
        double _t36 = _t31 * _t17;
        double _t37 = _t31 * _t15;
        double _t38 = _t31 * _t16;
        double _t39 = _t32 * _t13;
        double _t40 = _t32 * _t12;
        double _t41 = _t32 * _t14;
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
        double _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0;
            _t89 = -_t39;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
            _t89 = _t40;
        }
        double _t99 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t75, _t75, java.lang.Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t76, _t76, java.lang.Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t89, _t89, _t74 * _t74))));
        double _t102 = _t99 * _t72;
        double _t103 = _t100 * _t73;
        double _t104 = _t101 * _t74;
        double _t105 = _t100 * _t76;
        double _t106 = _t99 * _t75;
        double _t107 = _t101 * _t77;
        double _t114 = _t100 * _t88;
        double _t115 = _t101 * _t89;
        double _t116 = _t99 * _t87;
        double _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = 0.0;
                    _t167 = 1.0;
                    _t170 = 0.0;
                    _t166 = 0.0;
                    _t168 = 0.0;
                    _t171 = 1.0;
                    _t169 = 0.0;
                    _t172 = 0.0;
                    _t173 = 1.0;
                } else {
                    _t165 = _t102;
                    _t167 = _t116;
                    _t170 = _t106;
                    _t166 = java.lang.Math.fma(_t33, _t102, -(_t34 * _t106));
                    _t168 = java.lang.Math.fma(_t35, _t106, -(_t33 * _t116));
                    _t171 = java.lang.Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = java.lang.Math.fma(_t36, _t105, -(_t37 * _t114));
                    _t167 = java.lang.Math.fma(_t37, _t103, -(_t38 * _t105));
                    _t170 = java.lang.Math.fma(_t38, _t114, -(_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = java.lang.Math.fma(_t33, _t36, -(_t35 * _t37));
                    _t167 = java.lang.Math.fma(_t34, _t37, -(_t33 * _t38));
                    _t170 = java.lang.Math.fma(_t35, _t38, -(_t34 * _t36));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        } else {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t115;
                    _t168 = _t104;
                    _t171 = _t107;
                    _t169 = java.lang.Math.fma(_t39, _t115, -(_t41 * _t104));
                    _t172 = java.lang.Math.fma(_t40, _t104, -(_t39 * _t107));
                    _t173 = java.lang.Math.fma(_t41, _t107, -(_t40 * _t115));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = java.lang.Math.fma(_t33, _t39, -(_t34 * _t40));
                    _t168 = java.lang.Math.fma(_t35, _t40, -(_t33 * _t41));
                    _t171 = java.lang.Math.fma(_t34, _t41, -(_t35 * _t39));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = java.lang.Math.fma(_t39, _t36, -(_t41 * _t38));
                    _t172 = java.lang.Math.fma(_t40, _t38, -(_t39 * _t37));
                    _t173 = java.lang.Math.fma(_t41, _t37, -(_t40 * _t36));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        }
        double _t182 = _t170 - _t166;
        double _t184 = _t170 + _t166;
        double _t194, _t195, _t196;
        if (java.lang.Math.fma(java.lang.Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, java.lang.Math.fma(java.lang.Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, java.lang.Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        double _t199 = _t195 + _t165;
        double _t200 = _t196 + _t168;
        double _t201 = _t168 - _t196;
        double _t202 = _t195 - _t165;
        double _t206 = _t194 + _t167 + _t171;
        double _t207 = 1.0 + _t206;
        double _t208 = 1.0 + _t194 - _t167 - _t171;
        double _t209 = 1.0 + _t167 - _t194 - _t171;
        double _t210 = 1.0 + _t171 - _t194 - _t167;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t208));
        this.tX = 0.0;
        this.tY = 0.0;
        this.tZ = 0.0;
        if (_t206 > 0.0) {
            this.rX = _sp0 * _t182;
            this.rY = _sp0 * _t201;
            this.rZ = _sp0 * _t202;
            this.rW = 0.5 * java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                this.rX = 0.5 * java.lang.Math.sqrt(_t208);
                this.rY = _sp3 * _t199;
                this.rZ = _sp3 * _t200;
                this.rW = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    this.rX = _sp1 * _t199;
                    this.rY = 0.5 * java.lang.Math.sqrt(_t209);
                    this.rZ = _sp1 * _t184;
                    this.rW = _sp1 * _t201;
                } else {
                    this.rX = _sp2 * _t200;
                    this.rY = _sp2 * _t184;
                    this.rZ = 0.5 * java.lang.Math.sqrt(_t210);
                    this.rW = _sp2 * _t202;
                }
            }
        }
        return this;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private DoubleRigid makeFromMatrix_degenerate_mulAdd(Double3x3R m) {
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
        double _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        double _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t33 = _t30 * _t18;
        double _t34 = _t30 * _t19;
        double _t35 = _t30 * _t20;
        double _t36 = _t31 * _t17;
        double _t37 = _t31 * _t15;
        double _t38 = _t31 * _t16;
        double _t39 = _t32 * _t13;
        double _t40 = _t32 * _t12;
        double _t41 = _t32 * _t14;
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
        double _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0;
            _t89 = -_t39;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
            _t89 = _t40;
        }
        double _t99 = (1.0 / java.lang.Math.sqrt(((_t75) * (_t75) + (((_t87) * (_t87) + (_t72 * _t72))))));
        double _t100 = (1.0 / java.lang.Math.sqrt(((_t76) * (_t76) + (((_t88) * (_t88) + (_t73 * _t73))))));
        double _t101 = (1.0 / java.lang.Math.sqrt(((_t77) * (_t77) + (((_t89) * (_t89) + (_t74 * _t74))))));
        double _t102 = _t99 * _t72;
        double _t103 = _t100 * _t73;
        double _t104 = _t101 * _t74;
        double _t105 = _t100 * _t76;
        double _t106 = _t99 * _t75;
        double _t107 = _t101 * _t77;
        double _t114 = _t100 * _t88;
        double _t115 = _t101 * _t89;
        double _t116 = _t99 * _t87;
        double _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = 0.0;
                    _t167 = 1.0;
                    _t170 = 0.0;
                    _t166 = 0.0;
                    _t168 = 0.0;
                    _t171 = 1.0;
                    _t169 = 0.0;
                    _t172 = 0.0;
                    _t173 = 1.0;
                } else {
                    _t165 = _t102;
                    _t167 = _t116;
                    _t170 = _t106;
                    _t166 = ((_t33) * (_t102) - (_t34 * _t106));
                    _t168 = ((_t35) * (_t106) - (_t33 * _t116));
                    _t171 = ((_t34) * (_t116) - (_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = ((_t36) * (_t105) - (_t37 * _t114));
                    _t167 = ((_t37) * (_t103) - (_t38 * _t105));
                    _t170 = ((_t38) * (_t114) - (_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = ((_t33) * (_t36) - (_t35 * _t37));
                    _t167 = ((_t34) * (_t37) - (_t33 * _t38));
                    _t170 = ((_t35) * (_t38) - (_t34 * _t36));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        } else {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t115;
                    _t168 = _t104;
                    _t171 = _t107;
                    _t169 = ((_t39) * (_t115) - (_t41 * _t104));
                    _t172 = ((_t40) * (_t104) - (_t39 * _t107));
                    _t173 = ((_t41) * (_t107) - (_t40 * _t115));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = ((_t33) * (_t39) - (_t34 * _t40));
                    _t168 = ((_t35) * (_t40) - (_t33 * _t41));
                    _t171 = ((_t34) * (_t41) - (_t35 * _t39));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = ((_t39) * (_t36) - (_t41 * _t38));
                    _t172 = ((_t40) * (_t38) - (_t39 * _t37));
                    _t173 = ((_t41) * (_t37) - (_t40 * _t36));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        }
        double _t182 = _t170 - _t166;
        double _t184 = _t170 + _t166;
        double _t194, _t195, _t196;
        if (((((_t165) * (_t166) - (_t167 * _t168))) * (_t169) + (((((_t170) * (_t168) - (_t165 * _t171))) * (_t172) + (((_t167) * (_t171) - (_t170 * _t166)) * _t173)))) < 0.0) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        double _t199 = _t195 + _t165;
        double _t200 = _t196 + _t168;
        double _t201 = _t168 - _t196;
        double _t202 = _t195 - _t165;
        double _t206 = _t194 + _t167 + _t171;
        double _t207 = 1.0 + _t206;
        double _t208 = 1.0 + _t194 - _t167 - _t171;
        double _t209 = 1.0 + _t167 - _t194 - _t171;
        double _t210 = 1.0 + _t171 - _t194 - _t167;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t208));
        this.tX = 0.0;
        this.tY = 0.0;
        this.tZ = 0.0;
        if (_t206 > 0.0) {
            this.rX = _sp0 * _t182;
            this.rY = _sp0 * _t201;
            this.rZ = _sp0 * _t202;
            this.rW = 0.5 * java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                this.rX = 0.5 * java.lang.Math.sqrt(_t208);
                this.rY = _sp3 * _t199;
                this.rZ = _sp3 * _t200;
                this.rW = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    this.rX = _sp1 * _t199;
                    this.rY = 0.5 * java.lang.Math.sqrt(_t209);
                    this.rZ = _sp1 * _t184;
                    this.rW = _sp1 * _t201;
                } else {
                    this.rX = _sp2 * _t200;
                    this.rY = _sp2 * _t184;
                    this.rZ = 0.5 * java.lang.Math.sqrt(_t210);
                    this.rW = _sp2 * _t202;
                }
            }
        }
        return this;
    }


    /**
     * Set this rigid transform to the rigid decomposition of the given affine matrix: translation
     * from the last column, rotation from the column-normalized upper-left 3x3 block (scale is
     * removed by normalizing the columns, but shear is not removed: a sheared block yields a
     * rotation quaternion that is not unit length).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public DoubleRigid makeFromMatrix(Double3x4R m) {
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid makeFromMatrix_fma(Double3x4R m) {
        double _r0 = m.m11();
        double _r1 = m.m22();
        double _r2 = m.m21();
        double _r3 = m.m01();
        double _r4 = m.m02();
        double _r5 = m.m12();
        double _r6 = m.m20();
        double _r7 = m.m00();
        double _r8 = m.m10();
        double _ct0 = java.lang.Math.fma(_r2, _r2, java.lang.Math.fma(_r3, _r3, _r0 * _r0));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _ct1 = java.lang.Math.fma(_r1, _r1, java.lang.Math.fma(_r4, _r4, _r5 * _r5));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _ct2 = java.lang.Math.fma(_r6, _r6, java.lang.Math.fma(_r7, _r7, _r8 * _r8));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        double _r9 = m.m03();
        double _r10 = m.m13();
        return makeFromMatrix_s89828b35_1_fma(this, _r0, _r1, _r2, _r3, _r4, _r5, _r7, (1.0 / java.lang.Math.sqrt(_ct0)), _t16, _t17, _r9, _r10, m.m23(), -_r0, -_r1, _r8 * _t17, _r1 * _t16, _r5 * _t16, _r6 * _t17);
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid makeFromMatrix_mulAdd(Double3x4R m) {
        double _r0 = m.m11();
        double _r1 = m.m22();
        double _r2 = m.m21();
        double _r3 = m.m01();
        double _r4 = m.m02();
        double _r5 = m.m12();
        double _r6 = m.m20();
        double _r7 = m.m00();
        double _r8 = m.m10();
        double _ct0 = ((_r2) * (_r2) + (((_r3) * (_r3) + (_r0 * _r0))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _ct1 = ((_r1) * (_r1) + (((_r4) * (_r4) + (_r5 * _r5))));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _ct2 = ((_r6) * (_r6) + (((_r7) * (_r7) + (_r8 * _r8))));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        double _r9 = m.m03();
        double _r10 = m.m13();
        return makeFromMatrix_s89828b35_1_mulAdd(this, _r0, _r1, _r2, _r3, _r4, _r5, _r7, (1.0 / java.lang.Math.sqrt(_ct0)), _t16, _t17, _r9, _r10, m.m23(), -_r0, -_r1, _r8 * _t17, _r1 * _t16, _r5 * _t16, _r6 * _t17);
    }

    /**
     * Piece 2 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private DoubleRigid makeFromMatrix_s89828b35_1_fma(DoubleRigidImpl d, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r7, double _t15, double _t16, double _t17, double _r9, double _r10, double _r11, double _t0, double _t1, double _t18, double _t19, double _t20, double _t21) {
        double _t23 = _r2 * _t15;
        double _t24 = _r0 * _t15;
        double _t26 = _r7 * _t17;
        double _t47, _t48, _t49;
        if (java.lang.Math.fma(-java.lang.Math.fma(_t18, _t19, -(_t20 * _t21)), _r3 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t18, _t23, -(_t24 * _t21)), _r4 * _t16, java.lang.Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0) {
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
        double _t63 = java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_r1, _t16, _t51));
        return makeFromMatrix_s89828b35_2(d, _r9, _r10, _r11, _t19, _t24, java.lang.Math.fma(_r5, _t16, _t23), java.lang.Math.fma(_r2, _t15, -_t20), java.lang.Math.max(_t24, _t19), _t47, java.lang.Math.fma(_r3, _t15, _t48), java.lang.Math.fma(_r4, _t16, _t49), java.lang.Math.fma(_r4, _t16, -_t49), java.lang.Math.fma(-_r3, _t15, _t48), java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_r1, _t16, _t47)), _t63, 0.5 * (1.0 / java.lang.Math.sqrt(_t63)), java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_t1, _t16, _t52)), java.lang.Math.fma(_r1, _t16, java.lang.Math.fma(_t0, _t15, _t52)), java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t51)));
    }

    /**
     * Piece 2 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private DoubleRigid makeFromMatrix_s89828b35_1_mulAdd(DoubleRigidImpl d, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r7, double _t15, double _t16, double _t17, double _r9, double _r10, double _r11, double _t0, double _t1, double _t18, double _t19, double _t20, double _t21) {
        double _t23 = _r2 * _t15;
        double _t24 = _r0 * _t15;
        double _t26 = _r7 * _t17;
        double _t47, _t48, _t49;
        if (((-((_t18) * (_t19) - (_t20 * _t21))) * (_r3 * _t15) + (((((_t18) * (_t23) - (_t24 * _t21))) * (_r4 * _t16) + (((_t24) * (_t19) - (_t20 * _t23)) * _t26)))) < 0.0) {
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
        double _t63 = ((_r0) * (_t15) + (((_r1) * (_t16) + (_t51))));
        return makeFromMatrix_s89828b35_2(d, _r9, _r10, _r11, _t19, _t24, ((_r5) * (_t16) + (_t23)), ((_r2) * (_t15) - (_t20)), java.lang.Math.max(_t24, _t19), _t47, ((_r3) * (_t15) + (_t48)), ((_r4) * (_t16) + (_t49)), ((_r4) * (_t16) - (_t49)), ((-_r3) * (_t15) + (_t48)), ((_r0) * (_t15) + (((_r1) * (_t16) + (_t47)))), _t63, 0.5 * (1.0 / java.lang.Math.sqrt(_t63)), ((_r0) * (_t15) + (((_t1) * (_t16) + (_t52)))), ((_r1) * (_t16) + (((_t0) * (_t15) + (_t52)))), ((_t0) * (_t15) + (((_t1) * (_t16) + (_t51)))));
    }

    /**
     * Piece 3 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private DoubleRigid makeFromMatrix_s89828b35_2(DoubleRigidImpl d, double _r9, double _r10, double _r11, double _t19, double _t24, double _t31, double _t35, double _t36, double _t47, double _t54, double _t55, double _t56, double _t57, double _t62, double _t63, double _sp0, double _t65, double _t66, double _t67) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t65));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        d.tX = _r9;
        d.tY = _r10;
        d.tZ = _r11;
        d.rX = _t62 > 0.0 ? _sp0 * _t35 : _t47 > _t36 ? 0.5 * java.lang.Math.sqrt(_t67) : _t24 > _t19 ? _sp1 * _t54 : _sp2 * _t55;
        d.rY = _t62 > 0.0 ? _sp0 * _t56 : _t47 > _t36 ? _sp3 * _t54 : _t24 > _t19 ? 0.5 * java.lang.Math.sqrt(_t65) : _sp2 * _t31;
        d.rZ = _t62 > 0.0 ? _sp0 * _t57 : _t47 > _t36 ? _sp3 * _t55 : _t24 > _t19 ? _sp1 * _t31 : 0.5 * java.lang.Math.sqrt(_t66);
        d.rW = _t62 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t63) : _t47 > _t36 ? _sp3 * _t35 : _t24 > _t19 ? _sp1 * _t56 : _sp2 * _t57;
        return d;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private DoubleRigid makeFromMatrix_degenerate_fma(Double3x4R m) {
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
        double _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t33 = _t30 * _t18;
        double _t34 = _t30 * _t19;
        double _t35 = _t30 * _t20;
        double _t36 = _t31 * _t17;
        double _t37 = _t31 * _t15;
        double _t38 = _t31 * _t16;
        double _t39 = _t32 * _t13;
        double _t40 = _t32 * _t12;
        double _t41 = _t32 * _t14;
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
        double _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0;
            _t89 = -_t39;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
            _t89 = _t40;
        }
        double _t99 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t75, _t75, java.lang.Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t76, _t76, java.lang.Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t89, _t89, _t74 * _t74))));
        double _t102 = _t99 * _t72;
        double _t103 = _t100 * _t73;
        double _t104 = _t101 * _t74;
        double _t105 = _t100 * _t76;
        double _t106 = _t99 * _t75;
        double _t107 = _t101 * _t77;
        double _t114 = _t100 * _t88;
        double _t115 = _t101 * _t89;
        double _t116 = _t99 * _t87;
        double _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = 0.0;
                    _t167 = 1.0;
                    _t170 = 0.0;
                    _t166 = 0.0;
                    _t168 = 0.0;
                    _t171 = 1.0;
                    _t169 = 0.0;
                    _t172 = 0.0;
                    _t173 = 1.0;
                } else {
                    _t165 = _t102;
                    _t167 = _t116;
                    _t170 = _t106;
                    _t166 = java.lang.Math.fma(_t33, _t102, -(_t34 * _t106));
                    _t168 = java.lang.Math.fma(_t35, _t106, -(_t33 * _t116));
                    _t171 = java.lang.Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = java.lang.Math.fma(_t36, _t105, -(_t37 * _t114));
                    _t167 = java.lang.Math.fma(_t37, _t103, -(_t38 * _t105));
                    _t170 = java.lang.Math.fma(_t38, _t114, -(_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = java.lang.Math.fma(_t33, _t36, -(_t35 * _t37));
                    _t167 = java.lang.Math.fma(_t34, _t37, -(_t33 * _t38));
                    _t170 = java.lang.Math.fma(_t35, _t38, -(_t34 * _t36));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        } else {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t115;
                    _t168 = _t104;
                    _t171 = _t107;
                    _t169 = java.lang.Math.fma(_t39, _t115, -(_t41 * _t104));
                    _t172 = java.lang.Math.fma(_t40, _t104, -(_t39 * _t107));
                    _t173 = java.lang.Math.fma(_t41, _t107, -(_t40 * _t115));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = java.lang.Math.fma(_t33, _t39, -(_t34 * _t40));
                    _t168 = java.lang.Math.fma(_t35, _t40, -(_t33 * _t41));
                    _t171 = java.lang.Math.fma(_t34, _t41, -(_t35 * _t39));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = java.lang.Math.fma(_t39, _t36, -(_t41 * _t38));
                    _t172 = java.lang.Math.fma(_t40, _t38, -(_t39 * _t37));
                    _t173 = java.lang.Math.fma(_t41, _t37, -(_t40 * _t36));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        }
        double _t182 = _t170 - _t166;
        double _t184 = _t170 + _t166;
        double _t194, _t195, _t196;
        if (java.lang.Math.fma(java.lang.Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, java.lang.Math.fma(java.lang.Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, java.lang.Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        double _t199 = _t195 + _t165;
        double _t200 = _t196 + _t168;
        double _t201 = _t168 - _t196;
        double _t202 = _t195 - _t165;
        double _t206 = _t194 + _t167 + _t171;
        double _t207 = 1.0 + _t206;
        double _t208 = 1.0 + _t194 - _t167 - _t171;
        double _t209 = 1.0 + _t167 - _t194 - _t171;
        double _t210 = 1.0 + _t171 - _t194 - _t167;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t208));
        this.tX = m.m03();
        this.tY = m.m13();
        this.tZ = m.m23();
        if (_t206 > 0.0) {
            this.rX = _sp0 * _t182;
            this.rY = _sp0 * _t201;
            this.rZ = _sp0 * _t202;
            this.rW = 0.5 * java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                this.rX = 0.5 * java.lang.Math.sqrt(_t208);
                this.rY = _sp3 * _t199;
                this.rZ = _sp3 * _t200;
                this.rW = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    this.rX = _sp1 * _t199;
                    this.rY = 0.5 * java.lang.Math.sqrt(_t209);
                    this.rZ = _sp1 * _t184;
                    this.rW = _sp1 * _t201;
                } else {
                    this.rX = _sp2 * _t200;
                    this.rY = _sp2 * _t184;
                    this.rZ = 0.5 * java.lang.Math.sqrt(_t210);
                    this.rW = _sp2 * _t202;
                }
            }
        }
        return this;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private DoubleRigid makeFromMatrix_degenerate_mulAdd(Double3x4R m) {
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
        double _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        double _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t33 = _t30 * _t18;
        double _t34 = _t30 * _t19;
        double _t35 = _t30 * _t20;
        double _t36 = _t31 * _t17;
        double _t37 = _t31 * _t15;
        double _t38 = _t31 * _t16;
        double _t39 = _t32 * _t13;
        double _t40 = _t32 * _t12;
        double _t41 = _t32 * _t14;
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
        double _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0;
            _t89 = -_t39;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
            _t89 = _t40;
        }
        double _t99 = (1.0 / java.lang.Math.sqrt(((_t75) * (_t75) + (((_t87) * (_t87) + (_t72 * _t72))))));
        double _t100 = (1.0 / java.lang.Math.sqrt(((_t76) * (_t76) + (((_t88) * (_t88) + (_t73 * _t73))))));
        double _t101 = (1.0 / java.lang.Math.sqrt(((_t77) * (_t77) + (((_t89) * (_t89) + (_t74 * _t74))))));
        double _t102 = _t99 * _t72;
        double _t103 = _t100 * _t73;
        double _t104 = _t101 * _t74;
        double _t105 = _t100 * _t76;
        double _t106 = _t99 * _t75;
        double _t107 = _t101 * _t77;
        double _t114 = _t100 * _t88;
        double _t115 = _t101 * _t89;
        double _t116 = _t99 * _t87;
        double _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = 0.0;
                    _t167 = 1.0;
                    _t170 = 0.0;
                    _t166 = 0.0;
                    _t168 = 0.0;
                    _t171 = 1.0;
                    _t169 = 0.0;
                    _t172 = 0.0;
                    _t173 = 1.0;
                } else {
                    _t165 = _t102;
                    _t167 = _t116;
                    _t170 = _t106;
                    _t166 = ((_t33) * (_t102) - (_t34 * _t106));
                    _t168 = ((_t35) * (_t106) - (_t33 * _t116));
                    _t171 = ((_t34) * (_t116) - (_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = ((_t36) * (_t105) - (_t37 * _t114));
                    _t167 = ((_t37) * (_t103) - (_t38 * _t105));
                    _t170 = ((_t38) * (_t114) - (_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = ((_t33) * (_t36) - (_t35 * _t37));
                    _t167 = ((_t34) * (_t37) - (_t33 * _t38));
                    _t170 = ((_t35) * (_t38) - (_t34 * _t36));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        } else {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t115;
                    _t168 = _t104;
                    _t171 = _t107;
                    _t169 = ((_t39) * (_t115) - (_t41 * _t104));
                    _t172 = ((_t40) * (_t104) - (_t39 * _t107));
                    _t173 = ((_t41) * (_t107) - (_t40 * _t115));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = ((_t33) * (_t39) - (_t34 * _t40));
                    _t168 = ((_t35) * (_t40) - (_t33 * _t41));
                    _t171 = ((_t34) * (_t41) - (_t35 * _t39));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = ((_t39) * (_t36) - (_t41 * _t38));
                    _t172 = ((_t40) * (_t38) - (_t39 * _t37));
                    _t173 = ((_t41) * (_t37) - (_t40 * _t36));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        }
        double _t182 = _t170 - _t166;
        double _t184 = _t170 + _t166;
        double _t194, _t195, _t196;
        if (((((_t165) * (_t166) - (_t167 * _t168))) * (_t169) + (((((_t170) * (_t168) - (_t165 * _t171))) * (_t172) + (((_t167) * (_t171) - (_t170 * _t166)) * _t173)))) < 0.0) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        double _t199 = _t195 + _t165;
        double _t200 = _t196 + _t168;
        double _t201 = _t168 - _t196;
        double _t202 = _t195 - _t165;
        double _t206 = _t194 + _t167 + _t171;
        double _t207 = 1.0 + _t206;
        double _t208 = 1.0 + _t194 - _t167 - _t171;
        double _t209 = 1.0 + _t167 - _t194 - _t171;
        double _t210 = 1.0 + _t171 - _t194 - _t167;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t208));
        this.tX = m.m03();
        this.tY = m.m13();
        this.tZ = m.m23();
        if (_t206 > 0.0) {
            this.rX = _sp0 * _t182;
            this.rY = _sp0 * _t201;
            this.rZ = _sp0 * _t202;
            this.rW = 0.5 * java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                this.rX = 0.5 * java.lang.Math.sqrt(_t208);
                this.rY = _sp3 * _t199;
                this.rZ = _sp3 * _t200;
                this.rW = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    this.rX = _sp1 * _t199;
                    this.rY = 0.5 * java.lang.Math.sqrt(_t209);
                    this.rZ = _sp1 * _t184;
                    this.rW = _sp1 * _t201;
                } else {
                    this.rX = _sp2 * _t200;
                    this.rY = _sp2 * _t184;
                    this.rZ = 0.5 * java.lang.Math.sqrt(_t210);
                    this.rW = _sp2 * _t202;
                }
            }
        }
        return this;
    }


    /**
     * Set this rigid transform to the rigid decomposition of the given affine matrix: translation
     * from the last column, rotation from the column-normalized upper-left 3x3 block (scale is
     * removed by normalizing the columns, but shear is not removed: a sheared block yields a
     * rotation quaternion that is not unit length).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public DoubleRigid makeFromMatrix(Double4x4R m) {
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid makeFromMatrix_fma(Double4x4R m) {
        double _r0 = m.m11();
        double _r1 = m.m22();
        double _r2 = m.m21();
        double _r3 = m.m01();
        double _r4 = m.m02();
        double _r5 = m.m12();
        double _r6 = m.m20();
        double _r7 = m.m00();
        double _r8 = m.m10();
        double _ct0 = java.lang.Math.fma(_r2, _r2, java.lang.Math.fma(_r3, _r3, _r0 * _r0));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _ct1 = java.lang.Math.fma(_r1, _r1, java.lang.Math.fma(_r4, _r4, _r5 * _r5));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _ct2 = java.lang.Math.fma(_r6, _r6, java.lang.Math.fma(_r7, _r7, _r8 * _r8));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        double _r9 = m.m03();
        double _r10 = m.m13();
        return makeFromMatrix_s89828b35_1_fma(this, _r0, _r1, _r2, _r3, _r4, _r5, _r7, (1.0 / java.lang.Math.sqrt(_ct0)), _t16, _t17, _r9, _r10, m.m23(), -_r0, -_r1, _r8 * _t17, _r1 * _t16, _r5 * _t16, _r6 * _t17);
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid makeFromMatrix_mulAdd(Double4x4R m) {
        double _r0 = m.m11();
        double _r1 = m.m22();
        double _r2 = m.m21();
        double _r3 = m.m01();
        double _r4 = m.m02();
        double _r5 = m.m12();
        double _r6 = m.m20();
        double _r7 = m.m00();
        double _r8 = m.m10();
        double _ct0 = ((_r2) * (_r2) + (((_r3) * (_r3) + (_r0 * _r0))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _ct1 = ((_r1) * (_r1) + (((_r4) * (_r4) + (_r5 * _r5))));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _ct2 = ((_r6) * (_r6) + (((_r7) * (_r7) + (_r8 * _r8))));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        double _r9 = m.m03();
        double _r10 = m.m13();
        return makeFromMatrix_s89828b35_1_mulAdd(this, _r0, _r1, _r2, _r3, _r4, _r5, _r7, (1.0 / java.lang.Math.sqrt(_ct0)), _t16, _t17, _r9, _r10, m.m23(), -_r0, -_r1, _r8 * _t17, _r1 * _t16, _r5 * _t16, _r6 * _t17);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private DoubleRigid makeFromMatrix_degenerate_fma(Double4x4R m) {
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
        double _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t33 = _t30 * _t18;
        double _t34 = _t30 * _t19;
        double _t35 = _t30 * _t20;
        double _t36 = _t31 * _t17;
        double _t37 = _t31 * _t15;
        double _t38 = _t31 * _t16;
        double _t39 = _t32 * _t13;
        double _t40 = _t32 * _t12;
        double _t41 = _t32 * _t14;
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
        double _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0;
            _t89 = -_t39;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
            _t89 = _t40;
        }
        double _t99 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t75, _t75, java.lang.Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t76, _t76, java.lang.Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t89, _t89, _t74 * _t74))));
        double _t102 = _t99 * _t72;
        double _t103 = _t100 * _t73;
        double _t104 = _t101 * _t74;
        double _t105 = _t100 * _t76;
        double _t106 = _t99 * _t75;
        double _t107 = _t101 * _t77;
        double _t114 = _t100 * _t88;
        double _t115 = _t101 * _t89;
        double _t116 = _t99 * _t87;
        double _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = 0.0;
                    _t167 = 1.0;
                    _t170 = 0.0;
                    _t166 = 0.0;
                    _t168 = 0.0;
                    _t171 = 1.0;
                    _t169 = 0.0;
                    _t172 = 0.0;
                    _t173 = 1.0;
                } else {
                    _t165 = _t102;
                    _t167 = _t116;
                    _t170 = _t106;
                    _t166 = java.lang.Math.fma(_t33, _t102, -(_t34 * _t106));
                    _t168 = java.lang.Math.fma(_t35, _t106, -(_t33 * _t116));
                    _t171 = java.lang.Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = java.lang.Math.fma(_t36, _t105, -(_t37 * _t114));
                    _t167 = java.lang.Math.fma(_t37, _t103, -(_t38 * _t105));
                    _t170 = java.lang.Math.fma(_t38, _t114, -(_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = java.lang.Math.fma(_t33, _t36, -(_t35 * _t37));
                    _t167 = java.lang.Math.fma(_t34, _t37, -(_t33 * _t38));
                    _t170 = java.lang.Math.fma(_t35, _t38, -(_t34 * _t36));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        } else {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t115;
                    _t168 = _t104;
                    _t171 = _t107;
                    _t169 = java.lang.Math.fma(_t39, _t115, -(_t41 * _t104));
                    _t172 = java.lang.Math.fma(_t40, _t104, -(_t39 * _t107));
                    _t173 = java.lang.Math.fma(_t41, _t107, -(_t40 * _t115));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = java.lang.Math.fma(_t33, _t39, -(_t34 * _t40));
                    _t168 = java.lang.Math.fma(_t35, _t40, -(_t33 * _t41));
                    _t171 = java.lang.Math.fma(_t34, _t41, -(_t35 * _t39));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = java.lang.Math.fma(_t39, _t36, -(_t41 * _t38));
                    _t172 = java.lang.Math.fma(_t40, _t38, -(_t39 * _t37));
                    _t173 = java.lang.Math.fma(_t41, _t37, -(_t40 * _t36));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        }
        double _t182 = _t170 - _t166;
        double _t184 = _t170 + _t166;
        double _t194, _t195, _t196;
        if (java.lang.Math.fma(java.lang.Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, java.lang.Math.fma(java.lang.Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, java.lang.Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        double _t199 = _t195 + _t165;
        double _t200 = _t196 + _t168;
        double _t201 = _t168 - _t196;
        double _t202 = _t195 - _t165;
        double _t206 = _t194 + _t167 + _t171;
        double _t207 = 1.0 + _t206;
        double _t208 = 1.0 + _t194 - _t167 - _t171;
        double _t209 = 1.0 + _t167 - _t194 - _t171;
        double _t210 = 1.0 + _t171 - _t194 - _t167;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t208));
        this.tX = m.m03();
        this.tY = m.m13();
        this.tZ = m.m23();
        if (_t206 > 0.0) {
            this.rX = _sp0 * _t182;
            this.rY = _sp0 * _t201;
            this.rZ = _sp0 * _t202;
            this.rW = 0.5 * java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                this.rX = 0.5 * java.lang.Math.sqrt(_t208);
                this.rY = _sp3 * _t199;
                this.rZ = _sp3 * _t200;
                this.rW = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    this.rX = _sp1 * _t199;
                    this.rY = 0.5 * java.lang.Math.sqrt(_t209);
                    this.rZ = _sp1 * _t184;
                    this.rW = _sp1 * _t201;
                } else {
                    this.rX = _sp2 * _t200;
                    this.rY = _sp2 * _t184;
                    this.rZ = 0.5 * java.lang.Math.sqrt(_t210);
                    this.rW = _sp2 * _t202;
                }
            }
        }
        return this;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private DoubleRigid makeFromMatrix_degenerate_mulAdd(Double4x4R m) {
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
        double _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        double _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t33 = _t30 * _t18;
        double _t34 = _t30 * _t19;
        double _t35 = _t30 * _t20;
        double _t36 = _t31 * _t17;
        double _t37 = _t31 * _t15;
        double _t38 = _t31 * _t16;
        double _t39 = _t32 * _t13;
        double _t40 = _t32 * _t12;
        double _t41 = _t32 * _t14;
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
        double _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0;
            _t89 = -_t39;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
            _t89 = _t40;
        }
        double _t99 = (1.0 / java.lang.Math.sqrt(((_t75) * (_t75) + (((_t87) * (_t87) + (_t72 * _t72))))));
        double _t100 = (1.0 / java.lang.Math.sqrt(((_t76) * (_t76) + (((_t88) * (_t88) + (_t73 * _t73))))));
        double _t101 = (1.0 / java.lang.Math.sqrt(((_t77) * (_t77) + (((_t89) * (_t89) + (_t74 * _t74))))));
        double _t102 = _t99 * _t72;
        double _t103 = _t100 * _t73;
        double _t104 = _t101 * _t74;
        double _t105 = _t100 * _t76;
        double _t106 = _t99 * _t75;
        double _t107 = _t101 * _t77;
        double _t114 = _t100 * _t88;
        double _t115 = _t101 * _t89;
        double _t116 = _t99 * _t87;
        double _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = 0.0;
                    _t167 = 1.0;
                    _t170 = 0.0;
                    _t166 = 0.0;
                    _t168 = 0.0;
                    _t171 = 1.0;
                    _t169 = 0.0;
                    _t172 = 0.0;
                    _t173 = 1.0;
                } else {
                    _t165 = _t102;
                    _t167 = _t116;
                    _t170 = _t106;
                    _t166 = ((_t33) * (_t102) - (_t34 * _t106));
                    _t168 = ((_t35) * (_t106) - (_t33 * _t116));
                    _t171 = ((_t34) * (_t116) - (_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = ((_t36) * (_t105) - (_t37 * _t114));
                    _t167 = ((_t37) * (_t103) - (_t38 * _t105));
                    _t170 = ((_t38) * (_t114) - (_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = ((_t33) * (_t36) - (_t35 * _t37));
                    _t167 = ((_t34) * (_t37) - (_t33 * _t38));
                    _t170 = ((_t35) * (_t38) - (_t34 * _t36));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        } else {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t115;
                    _t168 = _t104;
                    _t171 = _t107;
                    _t169 = ((_t39) * (_t115) - (_t41 * _t104));
                    _t172 = ((_t40) * (_t104) - (_t39 * _t107));
                    _t173 = ((_t41) * (_t107) - (_t40 * _t115));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = ((_t33) * (_t39) - (_t34 * _t40));
                    _t168 = ((_t35) * (_t40) - (_t33 * _t41));
                    _t171 = ((_t34) * (_t41) - (_t35 * _t39));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = ((_t39) * (_t36) - (_t41 * _t38));
                    _t172 = ((_t40) * (_t38) - (_t39 * _t37));
                    _t173 = ((_t41) * (_t37) - (_t40 * _t36));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        }
        double _t182 = _t170 - _t166;
        double _t184 = _t170 + _t166;
        double _t194, _t195, _t196;
        if (((((_t165) * (_t166) - (_t167 * _t168))) * (_t169) + (((((_t170) * (_t168) - (_t165 * _t171))) * (_t172) + (((_t167) * (_t171) - (_t170 * _t166)) * _t173)))) < 0.0) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        double _t199 = _t195 + _t165;
        double _t200 = _t196 + _t168;
        double _t201 = _t168 - _t196;
        double _t202 = _t195 - _t165;
        double _t206 = _t194 + _t167 + _t171;
        double _t207 = 1.0 + _t206;
        double _t208 = 1.0 + _t194 - _t167 - _t171;
        double _t209 = 1.0 + _t167 - _t194 - _t171;
        double _t210 = 1.0 + _t171 - _t194 - _t167;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t208));
        this.tX = m.m03();
        this.tY = m.m13();
        this.tZ = m.m23();
        if (_t206 > 0.0) {
            this.rX = _sp0 * _t182;
            this.rY = _sp0 * _t201;
            this.rZ = _sp0 * _t202;
            this.rW = 0.5 * java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                this.rX = 0.5 * java.lang.Math.sqrt(_t208);
                this.rY = _sp3 * _t199;
                this.rZ = _sp3 * _t200;
                this.rW = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    this.rX = _sp1 * _t199;
                    this.rY = 0.5 * java.lang.Math.sqrt(_t209);
                    this.rZ = _sp1 * _t184;
                    this.rW = _sp1 * _t201;
                } else {
                    this.rX = _sp2 * _t200;
                    this.rY = _sp2 * _t184;
                    this.rZ = 0.5 * java.lang.Math.sqrt(_t210);
                    this.rW = _sp2 * _t202;
                }
            }
        }
        return this;
    }


    /**
     * Set this rigid transform to the rigid motion (rotation and translation) of the given
     * transform; the scale is dropped (a rigid transform cannot represent it).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the transform to convert
     * @return this
     */
    public @Mutated DoubleRigid makeFromTransform(DoubleTransformR t) {
        double tTY = t.tY();
        double tTZ = t.tZ();
        double tRX = t.rX();
        double tRY = t.rY();
        double tRZ = t.rZ();
        double tRW = t.rW();
        this.tX = t.tX();
        this.tY = tTY;
        this.tZ = tTZ;
        this.rX = tRX;
        this.rY = tRY;
        this.rZ = tRZ;
        this.rW = tRW;
        return this;
    }


    /**
     * Set this rigid transform to the rigid motion (rotation and translation) of the given
     * transform; the scale is dropped (a rigid transform cannot represent it).
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
     * @return this
     */
    @Mutated public DoubleRigid makeFromTransform(double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        this.tX = tTX;
        this.tY = tTY;
        this.tZ = tTZ;
        this.rX = tRX;
        this.rY = tRY;
        this.rZ = tRZ;
        this.rW = tRW;
        return this;
    }


    /**
     * Convert this rigid transform to {@code float} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatRigid toFloat(@Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        d.tX = (float) (this.tX);
        d.tY = (float) (this.tY);
        d.tZ = (float) (this.tZ);
        d.rX = (float) (this.rX);
        d.rY = (float) (this.rY);
        d.rZ = (float) (this.rZ);
        d.rW = (float) (this.rW);
        return d;
    }


    /**
     * Convert this rigid transform to a unit dual quaternion encoding the same rigid motion (an
     * exact conversion) and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat toDualQuat(@Mutated DoubleDualQuat dest) {
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        double _t0 = -this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.dX = 0.5 * Math.fma(_t0, this.rY, Math.fma(this.tX, this.rW, this.tY * this.rZ));
        d.dY = 0.5 * Math.fma(this.tZ, this.rX, Math.fma(this.tY, this.rW, -(this.tX * this.rZ)));
        d.dZ = 0.5 * Math.fma(this.tZ, this.rW, Math.fma(this.tX, this.rY, -(this.tY * this.rX)));
        d.dW = 0.5 * Math.fma(_t0, this.rZ, Math.fma(-this.tY, this.rY, -(this.tX * this.rX)));
        return d;
    }


    /**
     * Compute the matrix representation of this rigid transform and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4x4 toMatrix(@Mutated Double4x4 dest) {
        Double4x4Impl d = (Double4x4Impl) dest;
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rZ * this.rW;
        double _t2 = this.rY * this.rW;
        d.m00 = Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0);
        d.m10 = 2.0 * Math.fma(this.rX, this.rY, _t1);
        d.m20 = 2.0 * Math.fma(this.rX, this.rZ, -_t2);
        d.m30 = 0.0;
        d.m01 = 2.0 * Math.fma(this.rX, this.rY, -_t1);
        d.m11 = Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0);
        d.m21 = 2.0 * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        d.m31 = 0.0;
        d.m02 = 2.0 * Math.fma(this.rX, this.rZ, _t2);
        d.m12 = 2.0 * Math.fma(this.rY, this.rZ, -(this.rX * this.rW));
        d.m22 = Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        d.m32 = 0.0;
        d.m03 = this.tX;
        d.m13 = this.tY;
        d.m23 = this.tZ;
        d.m33 = 1.0;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Compute the 3x3 matrix representation of the rotation of this rigid transform (the
     * translation is dropped) and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 toMatrix3x3(@Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rZ * this.rW;
        double _t2 = this.rY * this.rW;
        d.m00 = Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0);
        d.m10 = 2.0 * Math.fma(this.rX, this.rY, _t1);
        d.m20 = 2.0 * Math.fma(this.rX, this.rZ, -_t2);
        d.m01 = 2.0 * Math.fma(this.rX, this.rY, -_t1);
        d.m11 = Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0);
        d.m21 = 2.0 * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        d.m02 = 2.0 * Math.fma(this.rX, this.rZ, _t2);
        d.m12 = 2.0 * Math.fma(this.rY, this.rZ, -(this.rX * this.rW));
        d.m22 = Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        d.properties = 0;
        return d;
    }


    /**
     * Compute the 3x4 matrix representation of this rigid transform (the omitted last row is
     * implicitly {@code 0, 0, 0, 1}) and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x4 toMatrix3x4(@Mutated Double3x4 dest) {
        Double3x4Impl d = (Double3x4Impl) dest;
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rZ * this.rW;
        double _t2 = this.rY * this.rW;
        d.m00 = Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0);
        d.m01 = 2.0 * Math.fma(this.rX, this.rY, -_t1);
        d.m02 = 2.0 * Math.fma(this.rX, this.rZ, _t2);
        d.m03 = this.tX;
        d.m10 = 2.0 * Math.fma(this.rX, this.rY, _t1);
        d.m11 = Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0);
        d.m12 = 2.0 * Math.fma(this.rY, this.rZ, -(this.rX * this.rW));
        d.m13 = this.tY;
        d.m20 = 2.0 * Math.fma(this.rX, this.rZ, -_t2);
        d.m21 = 2.0 * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        d.m22 = Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        d.m23 = this.tZ;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }


    /**
     * Widen this rigid transform to a TRS transform (same translation and rotation, scale = 1) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform toTransform(@Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = 1.0;
        d.sY = 1.0;
        d.sZ = 1.0;
        return d;
    }


    /**
     * Set this rigid transform to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public DoubleRigid makeIdentity() {
        this.tX = 0.0;
        this.tY = 0.0;
        this.tZ = 0.0;
        this.rX = 0.0;
        this.rY = 0.0;
        this.rZ = 0.0;
        this.rW = 1.0;
        return this;
    }


    /**
     * Set this rigid transform to a pure rotation by {@code rotation} (zero translation).
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @return this
     */
    public @Mutated DoubleRigid set(DoubleQuatR rotation) {
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        this.tX = 0.0;
        this.tY = 0.0;
        this.tZ = 0.0;
        this.rX = rotationX;
        this.rY = rotationY;
        this.rZ = rotationZ;
        this.rW = rotationW;
        return this;
    }


    /**
     * Set this rigid transform to a pure rotation by ({@code rotationX}, {@code rotationY},
     * {@code rotationZ}, {@code rotationW}) (zero translation).
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
     * @return this
     */
    @Mutated public DoubleRigid set(double rotationX, double rotationY, double rotationZ, double rotationW) {
        this.tX = 0.0;
        this.tY = 0.0;
        this.tZ = 0.0;
        this.rX = rotationX;
        this.rY = rotationY;
        this.rZ = rotationZ;
        this.rW = rotationW;
        return this;
    }


    /**
     * Set this rigid transform to a pure translation by {@code translation} (identity rotation).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation
     * @return this
     */
    public @Mutated DoubleRigid set(Double3R translation) {
        double translationY = translation.y();
        double translationZ = translation.z();
        this.tX = translation.x();
        this.tY = translationY;
        this.tZ = translationZ;
        this.rX = 0.0;
        this.rY = 0.0;
        this.rZ = 0.0;
        this.rW = 1.0;
        return this;
    }


    /**
     * Set this rigid transform to a pure translation by ({@code translationX},
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
     * @return this
     */
    @Mutated public DoubleRigid set(double translationX, double translationY, double translationZ) {
        this.tX = translationX;
        this.tY = translationY;
        this.tZ = translationZ;
        this.rX = 0.0;
        this.rY = 0.0;
        this.rZ = 0.0;
        this.rW = 1.0;
        return this;
    }


    /**
     * Interpolate between this rigid transform and {@code other} using the interpolation factor
     * {@code t}, interpolating the translation linearly and the rotation via shortest-arc slerp and
     * store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid lerp(DoubleRigidR other, double t, @Mutated DoubleRigid dest) {
        return lerp(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), t, dest);
    }

    /** Private store group 0 of {@code lerp}: computes and stores it; reached only through it. */
    private void lerp_s3b4e3a15_c0_fma(DoubleRigidImpl _dst, double t, double otherTX, double _r4, double otherTY, double _r5, double otherTZ, double _r6, double _t49, double _t50, double _t44) {
        _dst.tX = java.lang.Math.fma(t, otherTX - _r4, _r4);
        _dst.tY = java.lang.Math.fma(t, otherTY - _r5, _r5);
        _dst.tZ = java.lang.Math.fma(t, otherTZ - _r6, _r6);
        _dst.rX = _t49 != 0.0 ? _t50 * _t44 : 0.0;
    }

    /** Private store group 0 of {@code lerp}: computes and stores it; reached only through it. */
    private void lerp_s3b4e3a15_c0_mulAdd(DoubleRigidImpl _dst, double t, double otherTX, double _r4, double otherTY, double _r5, double otherTZ, double _r6, double _t49, double _t50, double _t44) {
        _dst.tX = ((t) * (otherTX - _r4) + (_r4));
        _dst.tY = ((t) * (otherTY - _r5) + (_r5));
        _dst.tZ = ((t) * (otherTZ - _r6) + (_r6));
        _dst.rX = _t49 != 0.0 ? _t50 * _t44 : 0.0;
    }

    /** Private store group 1 of {@code lerp}: computes and stores it; reached only through it. */
    private void lerp_s3b4e3a15_c1(DoubleRigidImpl _dst, double _t49, double _t50, double _t45, double _t43, double _t42) {
        _dst.rY = _t49 != 0.0 ? _t50 * _t45 : 0.0;
        _dst.rZ = _t49 != 0.0 ? _t50 * _t43 : 0.0;
        _dst.rW = _t49 != 0.0 ? _t50 * _t42 : 0.0;
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private void lerp_s3b4e3a15_tail_fma(DoubleRigidImpl _dst, double _t0, double _t16, double _t17, double _r0, double _t19, double _t21, double _t17_inv, double t, double _r1, double _t22, double _r2, double _t23, double _r3, double _t24, double otherTX, double _r4, double otherTY, double _r5, double otherTZ, double _r6) {
        double _t25 = Math.sin(_t0 * _t16);
        double _t42, _t43, _t44, _t45;
        if (_t17 > 0.0) {
            _t42 = java.lang.Math.fma(_r0, _t25, _t19 * _t21) * _t17_inv;
            _t43 = java.lang.Math.fma(_r1, _t25, _t19 * _t22) * _t17_inv;
            _t44 = java.lang.Math.fma(_r2, _t25, _t19 * _t23) * _t17_inv;
            _t45 = java.lang.Math.fma(_r3, _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = java.lang.Math.fma(t, _t21, _r0 * _t0);
            _t43 = java.lang.Math.fma(t, _t22, _r1 * _t0);
            _t44 = java.lang.Math.fma(t, _t23, _r2 * _t0);
            _t45 = java.lang.Math.fma(t, _t24, _r3 * _t0);
        }
        double _t49 = java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, _t45 * _t45)));
        double _t50 = (1.0 / java.lang.Math.sqrt(_t49));
        lerp_s3b4e3a15_c0_fma(_dst, t, otherTX, _r4, otherTY, _r5, otherTZ, _r6, _t49, _t50, _t44);
        lerp_s3b4e3a15_c1(_dst, _t49, _t50, _t45, _t43, _t42);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private void lerp_s3b4e3a15_tail_mulAdd(DoubleRigidImpl _dst, double _t0, double _t16, double _t17, double _r0, double _t19, double _t21, double _t17_inv, double t, double _r1, double _t22, double _r2, double _t23, double _r3, double _t24, double otherTX, double _r4, double otherTY, double _r5, double otherTZ, double _r6) {
        double _t25 = Math.sin(_t0 * _t16);
        double _t42, _t43, _t44, _t45;
        if (_t17 > 0.0) {
            _t42 = ((_r0) * (_t25) + (_t19 * _t21)) * _t17_inv;
            _t43 = ((_r1) * (_t25) + (_t19 * _t22)) * _t17_inv;
            _t44 = ((_r2) * (_t25) + (_t19 * _t23)) * _t17_inv;
            _t45 = ((_r3) * (_t25) + (_t19 * _t24)) * _t17_inv;
        } else {
            _t42 = ((t) * (_t21) + (_r0 * _t0));
            _t43 = ((t) * (_t22) + (_r1 * _t0));
            _t44 = ((t) * (_t23) + (_r2 * _t0));
            _t45 = ((t) * (_t24) + (_r3 * _t0));
        }
        double _t49 = ((_t42) * (_t42) + (((_t43) * (_t43) + (((_t44) * (_t44) + (_t45 * _t45))))));
        double _t50 = (1.0 / java.lang.Math.sqrt(_t49));
        lerp_s3b4e3a15_c0_mulAdd(_dst, t, otherTX, _r4, otherTY, _r5, otherTZ, _r6, _t49, _t50, _t44);
        lerp_s3b4e3a15_c1(_dst, _t49, _t50, _t45, _t43, _t42);
    }


    /**
     * Interpolate between this rigid transform and ({@code otherTX}, {@code otherTY},
     * {@code otherTZ}, {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}) using
     * the interpolation factor {@code t}, interpolating the translation linearly and the rotation
     * via shortest-arc slerp and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid lerp(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double t, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _r0 = this.rW;
        double _r1 = this.rZ;
        double _r2 = this.rX;
        double _r3 = this.rY;
        double _r4 = this.tX;
        double _r5 = this.tY;
        double _r6 = this.tZ;
        double _t12 = Math.fma(otherRW, _r0, Math.fma(otherRZ, _r1, Math.fma(otherRX, _r2, otherRY * _r3)));
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
        if (Math.useFma()) lerp_s3b4e3a15_tail_fma(d, 1.0 - t, _t16, _t17, _r0, Math.sin(t * _t16), _t21, 1.0 / _t17, t, _r1, _t22, _r2, _t23, _r3, _t24, otherTX, _r4, otherTY, _r5, otherTZ, _r6); else lerp_s3b4e3a15_tail_mulAdd(d, 1.0 - t, _t16, _t17, _r0, Math.sin(t * _t16), _t21, 1.0 / _t17, t, _r1, _t22, _r2, _t23, _r3, _t24, otherTX, _r4, otherTY, _r5, otherTZ, _r6);
        return d;
    }


    /**
     * Multiply this rigid transform by {@code other} and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid mul(DoubleRigidR other, @Mutated DoubleRigid dest) {
        return mul(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), dest);
    }

    /** Private store group 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s1abfb90e_c0_fma(DoubleRigidImpl _dst, double _r1, double _t9, double _r2, double _t10, double _r3, double _t11, double _r4, double otherTX, double _r0, double _r5, double otherTY, double _r6, double otherTZ, double otherRX, double otherRW, double otherRZ, double otherRY) {
        _dst.tX = java.lang.Math.fma(_r1, _t9, java.lang.Math.fma(-_r2, _t10, java.lang.Math.fma(_r3, _t11, _r4 + otherTX)));
        _dst.tY = java.lang.Math.fma(_r2, _t11, java.lang.Math.fma(-_r0, _t9, java.lang.Math.fma(_r3, _t10, _r5 + otherTY)));
        _dst.tZ = java.lang.Math.fma(_r0, _t10, java.lang.Math.fma(-_r1, _t11, java.lang.Math.fma(_r3, _t9, _r6 + otherTZ)));
        _dst.rX = java.lang.Math.fma(otherRX, _r3, otherRW * _r0) + java.lang.Math.fma(otherRZ, _r1, -(otherRY * _r2));
    }

    /** Private store group 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s1abfb90e_c0_mulAdd(DoubleRigidImpl _dst, double _r1, double _t9, double _r2, double _t10, double _r3, double _t11, double _r4, double otherTX, double _r0, double _r5, double otherTY, double _r6, double otherTZ, double otherRX, double otherRW, double otherRZ, double otherRY) {
        _dst.tX = ((_r1) * (_t9) + (((-_r2) * (_t10) + (((_r3) * (_t11) + (_r4 + otherTX))))));
        _dst.tY = ((_r2) * (_t11) + (((-_r0) * (_t9) + (((_r3) * (_t10) + (_r5 + otherTY))))));
        _dst.tZ = ((_r0) * (_t10) + (((-_r1) * (_t11) + (((_r3) * (_t9) + (_r6 + otherTZ))))));
        _dst.rX = ((otherRX) * (_r3) + (otherRW * _r0)) + ((otherRZ) * (_r1) - (otherRY * _r2));
    }

    /** Private store group 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s1abfb90e_c1_fma(DoubleRigidImpl _dst, double otherRX, double _r2, double otherRW, double _r1, double otherRY, double _r3, double otherRZ, double _r0) {
        _dst.rY = java.lang.Math.fma(otherRX, _r2, otherRW * _r1) + java.lang.Math.fma(otherRY, _r3, -(otherRZ * _r0));
        _dst.rZ = java.lang.Math.fma(otherRY, _r0, otherRZ * _r3) + java.lang.Math.fma(otherRW, _r2, -(otherRX * _r1));
        _dst.rW = java.lang.Math.fma(otherRW, _r3, -(otherRX * _r0)) - java.lang.Math.fma(otherRY, _r1, otherRZ * _r2);
    }

    /** Private store group 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s1abfb90e_c1_mulAdd(DoubleRigidImpl _dst, double otherRX, double _r2, double otherRW, double _r1, double otherRY, double _r3, double otherRZ, double _r0) {
        _dst.rY = ((otherRX) * (_r2) + (otherRW * _r1)) + ((otherRY) * (_r3) - (otherRZ * _r0));
        _dst.rZ = ((otherRY) * (_r0) + (otherRZ * _r3)) + ((otherRW) * (_r2) - (otherRX * _r1));
        _dst.rW = ((otherRW) * (_r3) - (otherRX * _r0)) - ((otherRY) * (_r1) + (otherRZ * _r2));
    }


    /**
     * Multiply this rigid transform by ({@code otherTX}, {@code otherTY}, {@code otherTZ},
     * {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}) and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid mul(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _r0 = this.rX;
        double _r1 = this.rY;
        double _r2 = this.rZ;
        double _r3 = this.rW;
        double _r4 = this.tX;
        double _r5 = this.tY;
        double _r6 = this.tZ;
        if (Math.useFma()) mul_s1abfb90e_c0_fma(d, _r1, 2.0 * java.lang.Math.fma(otherTY, _r0, -(otherTX * _r1)), _r2, 2.0 * java.lang.Math.fma(otherTX, _r2, -(otherTZ * _r0)), _r3, 2.0 * java.lang.Math.fma(otherTZ, _r1, -(otherTY * _r2)), _r4, otherTX, _r0, _r5, otherTY, _r6, otherTZ, otherRX, otherRW, otherRZ, otherRY); else mul_s1abfb90e_c0_mulAdd(d, _r1, 2.0 * ((otherTY) * (_r0) - (otherTX * _r1)), _r2, 2.0 * ((otherTX) * (_r2) - (otherTZ * _r0)), _r3, 2.0 * ((otherTZ) * (_r1) - (otherTY * _r2)), _r4, otherTX, _r0, _r5, otherTY, _r6, otherTZ, otherRX, otherRW, otherRZ, otherRY);
        if (Math.useFma()) mul_s1abfb90e_c1_fma(d, otherRX, _r2, otherRW, _r1, otherRY, _r3, otherRZ, _r0); else mul_s1abfb90e_c1_mulAdd(d, otherRX, _r2, otherRW, _r1, otherRY, _r3, otherRZ, _r0);
        return d;
    }


    /**
     * Pre-multiply {@code other} onto this rigid transform and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the operand, then the new rigid
     * transform will be {@code R * M}. So when transforming a vector {@code v} with the new rigid
     * transform by using {@code R * M * v}, the transformation of the operand will be applied last.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length; the rotation of
     * {@code other} must have unit length.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid preMul(DoubleRigidR other, @Mutated DoubleRigid dest) {
        return preMul(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), dest);
    }

    /** Private store group 0 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s1abfb90e_c0_fma(DoubleRigidImpl _dst, double otherRY, double _t9, double otherRZ, double _t10, double otherRW, double _t11, double otherTX, double _r1, double otherRX, double otherTY, double _r0, double otherTZ, double _r2, double _r3, double _r4, double _r5, double _r6) {
        _dst.tX = java.lang.Math.fma(otherRY, _t9, java.lang.Math.fma(-otherRZ, _t10, java.lang.Math.fma(otherRW, _t11, otherTX + _r1)));
        _dst.tY = java.lang.Math.fma(otherRZ, _t11, java.lang.Math.fma(-otherRX, _t9, java.lang.Math.fma(otherRW, _t10, otherTY + _r0)));
        _dst.tZ = java.lang.Math.fma(otherRX, _t10, java.lang.Math.fma(-otherRY, _t11, java.lang.Math.fma(otherRW, _t9, otherTZ + _r2)));
        _dst.rX = java.lang.Math.fma(otherRX, _r3, otherRW * _r4) + java.lang.Math.fma(otherRY, _r5, -(otherRZ * _r6));
    }

    /** Private store group 0 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s1abfb90e_c0_mulAdd(DoubleRigidImpl _dst, double otherRY, double _t9, double otherRZ, double _t10, double otherRW, double _t11, double otherTX, double _r1, double otherRX, double otherTY, double _r0, double otherTZ, double _r2, double _r3, double _r4, double _r5, double _r6) {
        _dst.tX = ((otherRY) * (_t9) + (((-otherRZ) * (_t10) + (((otherRW) * (_t11) + (otherTX + _r1))))));
        _dst.tY = ((otherRZ) * (_t11) + (((-otherRX) * (_t9) + (((otherRW) * (_t10) + (otherTY + _r0))))));
        _dst.tZ = ((otherRX) * (_t10) + (((-otherRY) * (_t11) + (((otherRW) * (_t9) + (otherTZ + _r2))))));
        _dst.rX = ((otherRX) * (_r3) + (otherRW * _r4)) + ((otherRY) * (_r5) - (otherRZ * _r6));
    }

    /**
     * Private store group 1 of {@code preMul}: computes and stores it. Shared by the identical
     * private paths of {@code preMul}, {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY},
     * {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only
     * through them.
     */
    private void preMul_s1abfb90e_c1_fma(DoubleRigidImpl _dst, double otherRY, double _r3, double otherRZ, double _r4, double otherRW, double _r6, double otherRX, double _r5) {
        _dst.rY = java.lang.Math.fma(otherRY, _r3, otherRZ * _r4) + java.lang.Math.fma(otherRW, _r6, -(otherRX * _r5));
        _dst.rZ = java.lang.Math.fma(otherRX, _r6, otherRW * _r5) + java.lang.Math.fma(otherRZ, _r3, -(otherRY * _r4));
        _dst.rW = java.lang.Math.fma(otherRW, _r3, -(otherRX * _r4)) - java.lang.Math.fma(otherRY, _r6, otherRZ * _r5);
    }

    /**
     * Private store group 1 of {@code preMul}: computes and stores it. Shared by the identical
     * private paths of {@code preMul}, {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY},
     * {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only
     * through them.
     */
    private void preMul_s1abfb90e_c1_mulAdd(DoubleRigidImpl _dst, double otherRY, double _r3, double otherRZ, double _r4, double otherRW, double _r6, double otherRX, double _r5) {
        _dst.rY = ((otherRY) * (_r3) + (otherRZ * _r4)) + ((otherRW) * (_r6) - (otherRX * _r5));
        _dst.rZ = ((otherRX) * (_r6) + (otherRW * _r5)) + ((otherRZ) * (_r3) - (otherRY * _r4));
        _dst.rW = ((otherRW) * (_r3) - (otherRX * _r4)) - ((otherRY) * (_r6) + (otherRZ * _r5));
    }


    /**
     * Pre-multiply ({@code otherTX}, {@code otherTY}, {@code otherTZ}, {@code otherRX},
     * {@code otherRY}, {@code otherRZ}, {@code otherRW}) onto this rigid transform and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid preMul(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _r0 = this.tY;
        double _r1 = this.tX;
        double _r2 = this.tZ;
        double _r3 = this.rW;
        double _r4 = this.rX;
        double _r5 = this.rZ;
        double _r6 = this.rY;
        if (Math.useFma()) preMul_s1abfb90e_c0_fma(d, otherRY, 2.0 * java.lang.Math.fma(otherRX, _r0, -(otherRY * _r1)), otherRZ, 2.0 * java.lang.Math.fma(otherRZ, _r1, -(otherRX * _r2)), otherRW, 2.0 * java.lang.Math.fma(otherRY, _r2, -(otherRZ * _r0)), otherTX, _r1, otherRX, otherTY, _r0, otherTZ, _r2, _r3, _r4, _r5, _r6); else preMul_s1abfb90e_c0_mulAdd(d, otherRY, 2.0 * ((otherRX) * (_r0) - (otherRY * _r1)), otherRZ, 2.0 * ((otherRZ) * (_r1) - (otherRX * _r2)), otherRW, 2.0 * ((otherRY) * (_r2) - (otherRZ * _r0)), otherTX, _r1, otherRX, otherTY, _r0, otherTZ, _r2, _r3, _r4, _r5, _r6);
        if (Math.useFma()) preMul_s1abfb90e_c1_fma(d, otherRY, _r3, otherRZ, _r4, otherRW, _r6, otherRX, _r5); else preMul_s1abfb90e_c1_mulAdd(d, otherRY, _r3, otherRZ, _r4, otherRW, _r6, otherRX, _r5);
        return d;
    }


    /**
     * Compute the difference between this rigid transform and {@code other}, i.e. the rigid
     * transformation {@code D} with {@code this * D = other}, that is {@code D = this^-1 * other}
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param other the target rigid transform, reached by composing this rigid transform with the
     *        result
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid difference(DoubleRigidR other, @Mutated DoubleRigid dest) {
        double otherTX = other.tX();
        double otherTY = other.tY();
        double otherTZ = other.tZ();
        double otherRX = other.rX();
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        if (Math.useFma()) return difference_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
        return difference_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
    }

    /** Private store group 0 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s1abfb90e_c0_fma(DoubleRigidImpl _dst, double _r1, double _t18, double otherTX, double _r6, double _t19, double _r2, double _t20, double _t21, double _t22, double _t23, double _r3, double _r0, double otherTY, double _r5, double otherTZ, double _r4, double otherRX, double otherRW, double otherRY, double otherRZ) {
        _dst.tX = java.lang.Math.fma(_r1, _t18, otherTX) + java.lang.Math.fma(_r6, _t19, -(_r2 * _t20)) + (java.lang.Math.fma(_r1, _t21, -(_r2 * _t22)) + java.lang.Math.fma(_r6, _t23, -_r3));
        _dst.tY = java.lang.Math.fma(_r0, _t20, otherTY) + java.lang.Math.fma(_r6, _t18, -(_r1 * _t19)) + (java.lang.Math.fma(_r0, _t22, -(_r1 * _t23)) + java.lang.Math.fma(_r6, _t21, -_r5));
        _dst.tZ = java.lang.Math.fma(_r2, _t19, otherTZ) + java.lang.Math.fma(_r6, _t20, -(_r0 * _t18)) + (java.lang.Math.fma(_r2, _t23, -(_r0 * _t21)) + java.lang.Math.fma(_r6, _t22, -_r4));
        _dst.rX = java.lang.Math.fma(otherRX, _r6, -(otherRW * _r0)) + java.lang.Math.fma(otherRY, _r1, -(otherRZ * _r2));
    }

    /** Private store group 0 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s1abfb90e_c0_mulAdd(DoubleRigidImpl _dst, double _r1, double _t18, double otherTX, double _r6, double _t19, double _r2, double _t20, double _t21, double _t22, double _t23, double _r3, double _r0, double otherTY, double _r5, double otherTZ, double _r4, double otherRX, double otherRW, double otherRY, double otherRZ) {
        _dst.tX = ((_r1) * (_t18) + (otherTX)) + ((_r6) * (_t19) - (_r2 * _t20)) + (((_r1) * (_t21) - (_r2 * _t22)) + ((_r6) * (_t23) - (_r3)));
        _dst.tY = ((_r0) * (_t20) + (otherTY)) + ((_r6) * (_t18) - (_r1 * _t19)) + (((_r0) * (_t22) - (_r1 * _t23)) + ((_r6) * (_t21) - (_r5)));
        _dst.tZ = ((_r2) * (_t19) + (otherTZ)) + ((_r6) * (_t20) - (_r0 * _t18)) + (((_r2) * (_t23) - (_r0 * _t21)) + ((_r6) * (_t22) - (_r4)));
        _dst.rX = ((otherRX) * (_r6) - (otherRW * _r0)) + ((otherRY) * (_r1) - (otherRZ * _r2));
    }

    /** Private store group 1 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s1abfb90e_c1_fma(DoubleRigidImpl _dst, double otherRY, double _r6, double otherRZ, double _r0, double otherRX, double _r1, double otherRW, double _r2) {
        _dst.rY = java.lang.Math.fma(otherRY, _r6, otherRZ * _r0) + java.lang.Math.fma(-otherRX, _r1, -(otherRW * _r2));
        _dst.rZ = java.lang.Math.fma(otherRX, _r2, -(otherRW * _r1)) + java.lang.Math.fma(otherRZ, _r6, -(otherRY * _r0));
        _dst.rW = java.lang.Math.fma(otherRX, _r0, otherRW * _r6) - java.lang.Math.fma(-otherRZ, _r1, -(otherRY * _r2));
    }

    /** Private store group 1 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s1abfb90e_c1_mulAdd(DoubleRigidImpl _dst, double otherRY, double _r6, double otherRZ, double _r0, double otherRX, double _r1, double otherRW, double _r2) {
        _dst.rY = ((otherRY) * (_r6) + (otherRZ * _r0)) + ((-otherRX) * (_r1) - (otherRW * _r2));
        _dst.rZ = ((otherRX) * (_r2) - (otherRW * _r1)) + ((otherRZ) * (_r6) - (otherRY * _r0));
        _dst.rW = ((otherRX) * (_r0) + (otherRW * _r6)) - ((-otherRZ) * (_r1) - (otherRY * _r2));
    }


    /**
     * Compute the difference between this rigid transform and ({@code otherTX}, {@code otherTY},
     * {@code otherTZ}, {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}), i.e.
     * the rigid transformation {@code D} with
     * {@code this * D = (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}, that is
     * {@code D = this^-1 * (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)} and
     * store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid difference(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, @Mutated DoubleRigid dest) {
        if (Math.useFma()) return difference_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
        return difference_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
    }

    /** {@code difference} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid difference_fma(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _r0 = this.rX;
        double _r1 = this.rZ;
        double _r2 = this.rY;
        double _r3 = this.tX;
        double _r4 = this.tZ;
        double _r5 = this.tY;
        double _r6 = this.rW;
        difference_s1abfb90e_c0_fma(d, _r1, 2.0 * java.lang.Math.fma(otherTZ, _r0, -(otherTX * _r1)), otherTX, _r6, 2.0 * java.lang.Math.fma(otherTY, _r1, -(otherTZ * _r2)), _r2, 2.0 * java.lang.Math.fma(otherTX, _r2, -(otherTY * _r0)), 2.0 * java.lang.Math.fma(_r3, _r1, -(_r4 * _r0)), 2.0 * java.lang.Math.fma(_r5, _r0, -(_r3 * _r2)), 2.0 * java.lang.Math.fma(_r4, _r2, -(_r5 * _r1)), _r3, _r0, otherTY, _r5, otherTZ, _r4, otherRX, otherRW, otherRY, otherRZ);
        difference_s1abfb90e_c1_fma(d, otherRY, _r6, otherRZ, _r0, otherRX, _r1, otherRW, _r2);
        return d;
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid difference_mulAdd(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _r0 = this.rX;
        double _r1 = this.rZ;
        double _r2 = this.rY;
        double _r3 = this.tX;
        double _r4 = this.tZ;
        double _r5 = this.tY;
        double _r6 = this.rW;
        difference_s1abfb90e_c0_mulAdd(d, _r1, 2.0 * ((otherTZ) * (_r0) - (otherTX * _r1)), otherTX, _r6, 2.0 * ((otherTY) * (_r1) - (otherTZ * _r2)), _r2, 2.0 * ((otherTX) * (_r2) - (otherTY * _r0)), 2.0 * ((_r3) * (_r1) - (_r4 * _r0)), 2.0 * ((_r5) * (_r0) - (_r3 * _r2)), 2.0 * ((_r4) * (_r2) - (_r5 * _r1)), _r3, _r0, otherTY, _r5, otherTZ, _r4, otherRX, otherRW, otherRY, otherRZ);
        difference_s1abfb90e_c1_mulAdd(d, otherRY, _r6, otherRZ, _r0, otherRX, _r1, otherRW, _r2);
        return d;
    }


    /**
     * Invert this rigid transform; exact for any rigid motion (no scale divisions) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid invert(@Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _t0 = -this.rY;
        double _t1 = -this.rZ;
        double _t2 = -this.rX;
        double _t12 = 2.0 * Math.fma(this.tX, this.rZ, -(this.tZ * this.rX));
        double _t13 = 2.0 * Math.fma(this.tY, this.rX, -(this.tX * this.rY));
        double _t14 = 2.0 * Math.fma(this.tZ, this.rY, -(this.tY * this.rZ));
        d.tX = Math.fma(this.rZ, _t12, Math.fma(_t0, _t13, Math.fma(this.rW, _t14, -this.tX)));
        d.tY = Math.fma(this.rX, _t13, Math.fma(_t1, _t14, Math.fma(this.rW, _t12, -this.tY)));
        d.tZ = Math.fma(this.rY, _t14, Math.fma(_t2, _t12, Math.fma(this.rW, _t13, -this.tZ)));
        d.rX = _t2;
        d.rY = _t0;
        d.rZ = _t1;
        d.rW = this.rW;
        return d;
    }


    /**
     * Normalize this rigid transform so that its rotation part has unit length, leaving its
     * translation unchanged (a zero-length rotation yields the zero quaternion) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid normalize(@Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _t3 = Math.fma(this.rW, this.rW, Math.fma(this.rZ, this.rZ, Math.fma(this.rX, this.rX, this.rY * this.rY)));
        double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        if (_t3 != 0.0) {
            d.rX = this.rX * _t4;
            d.rY = this.rY * _t4;
            d.rZ = this.rZ * _t4;
            d.rW = this.rW * _t4;
        } else {
            d.rX = 0.0;
            d.rY = 0.0;
            d.rZ = 0.0;
            d.rW = 0.0;
        }
        return d;
    }


    /**
     * Get the Euler angles in radians of this rigid transform, to be applied about the X, Y and Z
     * axes, in that order and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXYZ(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t1 = this.rY * this.rZ;
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        double _t9 = 2.0 * Math.fma(this.rX, this.rW, -_t1);
        double _t10 = Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-15) {
            d.x = Math.atan2(2.0 * Math.fma(this.rX, this.rW, _t1), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t3), 1.0));
            d.z = 0.0;
        } else {
            d.x = Math.atan2(_t9, _t10);
            d.z = Math.atan2(2.0 * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0, Math.fma(this.rY, this.rY, _t3), 1.0));
        }
        d.y = Math.atan2(_t8, java.lang.Math.sqrt(_t12));
        return d;
    }


    /**
     * Get the Euler angles in radians of this rigid transform, to be applied about the X, Z and Y
     * axes, in that order and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXZY(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rY * this.rZ;
        double _t7 = 2.0 * Math.fma(this.rX, this.rW, _t1);
        double _t8 = 2.0 * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        double _t9 = Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            d.x = Math.atan2(2.0 * Math.fma(this.rX, this.rW, -_t1), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0));
            d.y = 0.0;
        } else {
            d.x = Math.atan2(_t7, _t9);
            d.y = Math.atan2(2.0 * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0));
        }
        d.z = Math.atan2(_t8, java.lang.Math.sqrt(_t11));
        return d;
    }


    /**
     * Get the Euler angles in radians of this rigid transform, to be applied about the Y, X and Z
     * axes, in that order and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYXZ(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        double _t9 = 2.0 * Math.fma(this.rX, this.rW, -(this.rY * this.rZ));
        double _t10 = Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        d.x = Math.atan2(_t9, java.lang.Math.sqrt(_t12));
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-15) {
            d.y = Math.atan2(2.0 * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0, Math.fma(this.rY, this.rY, _t3), 1.0));
            d.z = 0.0;
        } else {
            d.y = Math.atan2(_t8, _t10);
            d.z = Math.atan2(2.0 * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t3), 1.0));
        }
        return d;
    }


    /**
     * Get the Euler angles in radians of this rigid transform, to be applied about the Y, Z and X
     * axes, in that order and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYZX(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        double _t8 = 2.0 * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        double _t9 = Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            d.x = 0.0;
            d.y = Math.atan2(2.0 * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0));
        } else {
            d.x = Math.atan2(2.0 * Math.fma(this.rX, this.rW, -(this.rY * this.rZ)), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0));
            d.y = Math.atan2(_t8, _t9);
        }
        d.z = Math.atan2(_t7, java.lang.Math.sqrt(_t11));
        return d;
    }


    /**
     * Get the Euler angles in radians of this rigid transform, to be applied about the Z, X and Y
     * axes, in that order and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZXY(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t1 = this.rZ * this.rZ;
        double _t7 = 2.0 * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        double _t8 = 2.0 * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        double _t9 = Math.fma(-2.0, Math.fma(this.rX, this.rX, _t1), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        d.x = Math.atan2(_t7, java.lang.Math.sqrt(_t11));
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            d.y = 0.0;
            d.z = Math.atan2(2.0 * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0, Math.fma(this.rY, this.rY, _t1), 1.0));
        } else {
            d.y = Math.atan2(2.0 * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0));
            d.z = Math.atan2(_t8, _t9);
        }
        return d;
    }


    /**
     * Get the Euler angles in radians of this rigid transform, to be applied about the Z, Y and X
     * axes, in that order and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZYX(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        double _t8 = 2.0 * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        double _t9 = Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            d.x = 0.0;
            d.z = Math.atan2(2.0 * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0));
        } else {
            d.x = Math.atan2(2.0 * Math.fma(this.rX, this.rW, this.rY * this.rZ), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0));
            d.z = Math.atan2(_t7, _t9);
        }
        d.y = Math.atan2(_t8, java.lang.Math.sqrt(_t11));
        return d;
    }


    /**
     * Get the rotation of this rigid transform and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat getRotation(@Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = this.rX;
        d.y = this.rY;
        d.z = this.rZ;
        d.w = this.rW;
        return d;
    }


    /**
     * Get the translation of this rigid transform and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getTranslation(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        d.x = this.tX;
        d.y = this.tY;
        d.z = this.tZ;
        return d;
    }


    /**
     * Set this rigid transform to a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return this
     */
    public @Mutated DoubleRigid makeRotationAxis(double angle, Double3R axis) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        this.tX = 0.0;
        this.tY = 0.0;
        this.tZ = 0.0;
        this.rX = axisX * _t1;
        this.rY = axisY * _t1;
        this.rZ = axisZ * _t1;
        this.rW = Math.cosFromSin(_t1, _t0);
        return this;
    }


    /**
     * Set this rigid transform to a rotation of {@code angle} radians about the axis
     * ({@code axisX}, {@code axisY}, {@code axisZ}).
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return this
     */
    @Mutated public DoubleRigid makeRotationAxis(double angle, double axisX, double axisY, double axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        this.tX = 0.0;
        this.tY = 0.0;
        this.tZ = 0.0;
        this.rX = axisX * _t1;
        this.rY = axisY * _t1;
        this.rZ = axisZ * _t1;
        this.rW = Math.cosFromSin(_t1, _t0);
        return this;
    }


    /**
     * Set this rigid transform to a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public DoubleRigid makeRotationX(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        this.tX = 0.0;
        this.tY = 0.0;
        this.tZ = 0.0;
        this.rX = _t1;
        this.rY = 0.0;
        this.rZ = 0.0;
        this.rW = Math.cosFromSin(_t1, _t0);
        return this;
    }


    /**
     * Set this rigid transform to a rotation of {@code angleX}, {@code angleY} and {@code angleZ}
     * radians about the X, Y and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so
     * a vector is rotated about the Z axis first, then Y, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this
     */
    @Mutated public DoubleRigid makeRotationXYZ(double angleX, double angleY, double angleZ) {
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
        this.tX = 0.0;
        this.tY = 0.0;
        this.tZ = 0.0;
        this.rX = Math.fma(_t10, _t7, _t11 * _t5);
        this.rY = Math.fma(_t11, _t7, -(_t10 * _t5));
        this.rZ = Math.fma(_t9, _t7, _t12 * _t5);
        this.rW = Math.fma(_t12, _t7, -(_t9 * _t5));
        return this;
    }


    /**
     * Set this rigid transform to a rotation of {@code angleX}, {@code angleZ} and {@code angleY}
     * radians about the X, Z and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so
     * a vector is rotated about the Y axis first, then Z, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this
     */
    @Mutated public DoubleRigid makeRotationXZY(double angleX, double angleZ, double angleY) {
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
        this.tX = 0.0;
        this.tY = 0.0;
        this.tZ = 0.0;
        this.rX = Math.fma(_t10, _t7, -(_t11 * _t5));
        this.rY = Math.fma(_t12, _t5, -(_t9 * _t7));
        this.rZ = Math.fma(_t10, _t5, _t11 * _t7);
        this.rW = Math.fma(_t9, _t5, _t12 * _t7);
        return this;
    }


    /**
     * Set this rigid transform to a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public DoubleRigid makeRotationY(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        this.tX = 0.0;
        this.tY = 0.0;
        this.tZ = 0.0;
        this.rX = 0.0;
        this.rY = _t1;
        this.rZ = 0.0;
        this.rW = Math.cosFromSin(_t1, _t0);
        return this;
    }


    /**
     * Set this rigid transform to a rotation of {@code angleY}, {@code angleX} and {@code angleZ}
     * radians about the Y, X and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so
     * a vector is rotated about the Z axis first, then X, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this
     */
    @Mutated public DoubleRigid makeRotationYXZ(double angleY, double angleX, double angleZ) {
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
        this.tX = 0.0;
        this.tY = 0.0;
        this.tZ = 0.0;
        this.rX = Math.fma(_t10, _t7, _t11 * _t5);
        this.rY = Math.fma(_t11, _t7, -(_t10 * _t5));
        this.rZ = Math.fma(_t12, _t5, -(_t9 * _t7));
        this.rW = Math.fma(_t9, _t5, _t12 * _t7);
        return this;
    }


    /**
     * Set this rigid transform to a rotation of {@code angleY}, {@code angleZ} and {@code angleX}
     * radians about the Y, Z and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so
     * a vector is rotated about the X axis first, then Z, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this
     */
    @Mutated public DoubleRigid makeRotationYZX(double angleY, double angleZ, double angleX) {
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
        this.tX = 0.0;
        this.tY = 0.0;
        this.tZ = 0.0;
        this.rX = Math.fma(_t9, _t6, _t12 * _t5);
        this.rY = Math.fma(_t10, _t6, _t11 * _t5);
        this.rZ = Math.fma(_t11, _t6, -(_t10 * _t5));
        this.rW = Math.fma(_t12, _t6, -(_t9 * _t5));
        return this;
    }


    /**
     * Set this rigid transform to a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public DoubleRigid makeRotationZ(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        this.tX = 0.0;
        this.tY = 0.0;
        this.tZ = 0.0;
        this.rX = 0.0;
        this.rY = 0.0;
        this.rZ = _t1;
        this.rW = Math.cosFromSin(_t1, _t0);
        return this;
    }


    /**
     * Set this rigid transform to a rotation of {@code angleZ}, {@code angleX} and {@code angleY}
     * radians about the Z, X and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so
     * a vector is rotated about the Y axis first, then X, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this
     */
    @Mutated public DoubleRigid makeRotationZXY(double angleZ, double angleX, double angleY) {
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
        this.tX = 0.0;
        this.tY = 0.0;
        this.tZ = 0.0;
        this.rX = Math.fma(_t10, _t7, -(_t11 * _t5));
        this.rY = Math.fma(_t9, _t7, _t12 * _t5);
        this.rZ = Math.fma(_t10, _t5, _t11 * _t7);
        this.rW = Math.fma(_t12, _t7, -(_t9 * _t5));
        return this;
    }


    /**
     * Set this rigid transform to a rotation of {@code angleZ}, {@code angleY} and {@code angleX}
     * radians about the Z, Y and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so
     * a vector is rotated about the X axis first, then Y, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this
     */
    @Mutated public DoubleRigid makeRotationZYX(double angleZ, double angleY, double angleX) {
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
        this.tX = 0.0;
        this.tY = 0.0;
        this.tZ = 0.0;
        this.rX = Math.fma(_t12, _t5, -(_t9 * _t8));
        this.rY = Math.fma(_t10, _t8, _t11 * _t5);
        this.rZ = Math.fma(_t11, _t8, -(_t10 * _t5));
        this.rW = Math.fma(_t9, _t5, _t12 * _t8);
        return this;
    }


    /**
     * Apply the rotation represented by the quaternion {@code rotation} to this rigid transform and
     * store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotate(DoubleQuatR rotation, @Mutated DoubleRigid dest) {
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _rd0 = this.rX;
        double _rd1 = this.rY;
        double _rd2 = this.rZ;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = Math.fma(rotationX, this.rW, rotationW * _rd0) + Math.fma(rotationZ, _rd1, -(rotationY * _rd2));
        d.rY = Math.fma(rotationX, _rd2, rotationW * _rd1) + Math.fma(rotationY, this.rW, -(rotationZ * _rd0));
        d.rZ = Math.fma(rotationY, _rd0, rotationZ * this.rW) + Math.fma(rotationW, _rd2, -(rotationX * _rd1));
        d.rW = Math.fma(rotationW, this.rW, -(rotationX * _rd0)) - Math.fma(rotationY, _rd1, rotationZ * _rd2);
        return d;
    }


    /**
     * Apply the rotation represented by the quaternion ({@code rotationX}, {@code rotationY},
     * {@code rotationZ}, {@code rotationW}) to this rigid transform and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotate(double rotationX, double rotationY, double rotationZ, double rotationW, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _rd0 = this.rX;
        double _rd1 = this.rY;
        double _rd2 = this.rZ;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = Math.fma(rotationX, this.rW, rotationW * _rd0) + Math.fma(rotationZ, _rd1, -(rotationY * _rd2));
        d.rY = Math.fma(rotationX, _rd2, rotationW * _rd1) + Math.fma(rotationY, this.rW, -(rotationZ * _rd0));
        d.rZ = Math.fma(rotationY, _rd0, rotationZ * this.rW) + Math.fma(rotationW, _rd2, -(rotationX * _rd1));
        d.rW = Math.fma(rotationW, this.rW, -(rotationX * _rd0)) - Math.fma(rotationY, _rd1, rotationZ * _rd2);
        return d;
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this rigid transform
     * and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateAxis(double angle, Double3R axis, @Mutated DoubleRigid dest) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        if (Math.useFma()) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
    }

    /**
     * Private store group 0 of {@code rotateAxis}: computes and stores it. Shared by the identical
     * private paths of {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ},
     * {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private void rotateAxis_s5fbf191b_c0_fma(DoubleRigidImpl _dst, double _r0, double _r1, double _r2, double _r3, double _t5, double _r4, double _t2, double _r5, double _t3, double _r6, double _t4) {
        _dst.tX = _r0;
        _dst.tY = _r1;
        _dst.tZ = _r2;
        _dst.rX = java.lang.Math.fma(_r3, _t5, _r4 * _t2) + java.lang.Math.fma(_r5, _t3, -(_r6 * _t4));
    }

    /**
     * Private store group 0 of {@code rotateAxis}: computes and stores it. Shared by the identical
     * private paths of {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ},
     * {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private void rotateAxis_s5fbf191b_c0_mulAdd(DoubleRigidImpl _dst, double _r0, double _r1, double _r2, double _r3, double _t5, double _r4, double _t2, double _r5, double _t3, double _r6, double _t4) {
        _dst.tX = _r0;
        _dst.tY = _r1;
        _dst.tZ = _r2;
        _dst.rX = ((_r3) * (_t5) + (_r4 * _t2)) + ((_r5) * (_t3) - (_r6 * _t4));
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) to this rigid transform and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateAxis(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleRigid dest) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        if (Math.useFma()) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid rotateAxis_fma(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _r0 = this.tX;
        double _r1 = this.tY;
        double _r2 = this.tZ;
        double _r3 = this.rX;
        double _r4 = this.rW;
        double _r5 = this.rY;
        double _r6 = this.rZ;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        rotateAxis_s5fbf191b_c0_fma(d, _r0, _r1, _r2, _r3, _t5, _r4, _t2, _r5, _t3, _r6, _t4);
        preMul_s1abfb90e_c1_fma(d, _r5, _t5, _r6, _t2, _r4, _t4, _r3, _t3);
        return d;
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid rotateAxis_mulAdd(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _r0 = this.tX;
        double _r1 = this.tY;
        double _r2 = this.tZ;
        double _r3 = this.rX;
        double _r4 = this.rW;
        double _r5 = this.rY;
        double _r6 = this.rZ;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        rotateAxis_s5fbf191b_c0_mulAdd(d, _r0, _r1, _r2, _r3, _t5, _r4, _t2, _r5, _t3, _r6, _t4);
        preMul_s1abfb90e_c1_mulAdd(d, _r5, _t5, _r6, _t2, _r4, _t4, _r3, _t3);
        return d;
    }


    /**
     * Apply a rotation of {@code angle} radians about the X axis to this rigid transform and store
     * the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateX(double angle, @Mutated DoubleRigid dest) {
        if (Math.useFma()) {
            DoubleRigidImpl d = (DoubleRigidImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.rX;
            double _rd1 = this.rY;
            d.tX = this.tX;
            d.tY = this.tY;
            d.tZ = this.tZ;
            d.rX = java.lang.Math.fma(_rd0, _t2, this.rW * _t1);
            d.rY = java.lang.Math.fma(_rd1, _t2, this.rZ * _t1);
            d.rZ = java.lang.Math.fma(this.rZ, _t2, -(_rd1 * _t1));
            d.rW = java.lang.Math.fma(this.rW, _t2, -(_rd0 * _t1));
            return d;
        } else {
            DoubleRigidImpl d = (DoubleRigidImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.rX;
            double _rd1 = this.rY;
            d.tX = this.tX;
            d.tY = this.tY;
            d.tZ = this.tZ;
            d.rX = ((_rd0) * (_t2) + (this.rW * _t1));
            d.rY = ((_rd1) * (_t2) + (this.rZ * _t1));
            d.rZ = ((this.rZ) * (_t2) - (_rd1 * _t1));
            d.rW = ((this.rW) * (_t2) - (_rd0 * _t1));
            return d;
        }
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void rotateXYZ_s6c64e0a1_tail_fma(DoubleRigidImpl _dst, double _t11, double _t8, double _t10, double _t5, double _r0, double _r1, double _r2, double _r3, double _t21, double _r4, double _t19, double _r5, double _t20, double _r6) {
        double _t22 = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        rotateAxis_s5fbf191b_c0_fma(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t20, _r6, _t22);
        preMul_s1abfb90e_c1_fma(_dst, _r5, _t21, _r6, _t19, _r4, _t22, _r3, _t20);
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void rotateXYZ_s6c64e0a1_tail_mulAdd(DoubleRigidImpl _dst, double _t11, double _t8, double _t10, double _t5, double _r0, double _r1, double _r2, double _r3, double _t21, double _r4, double _t19, double _r5, double _t20, double _r6) {
        double _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        rotateAxis_s5fbf191b_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t20, _r6, _t22);
        preMul_s1abfb90e_c1_mulAdd(_dst, _r5, _t21, _r6, _t19, _r4, _t22, _r3, _t20);
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this rigid transform and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateXYZ(double angleX, double angleY, double angleZ, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _r0 = this.tX;
        double _r1 = this.tY;
        double _r2 = this.tZ;
        double _r3 = this.rX;
        double _r4 = this.rW;
        double _r5 = this.rY;
        double _r6 = this.rZ;
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
        if (Math.useFma()) rotateXYZ_s6c64e0a1_tail_fma(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r4, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r5, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r6); else rotateXYZ_s6c64e0a1_tail_mulAdd(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, ((_t14) * (_t8) - (_t9 * _t5)), _r4, ((_t10) * (_t8) + (_t11 * _t5)), _r5, ((_t9) * (_t8) + (_t14 * _t5)), _r6);
        return d;
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this rigid transform and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateXZY(double angleX, double angleZ, double angleY, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _r0 = this.tX;
        double _r1 = this.tY;
        double _r2 = this.tZ;
        double _r3 = this.rX;
        double _r4 = this.rW;
        double _r5 = this.rY;
        double _r6 = this.rZ;
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
        if (Math.useFma()) rotateXYZ_s6c64e0a1_tail_fma(d, _t12, _t5, _t9, _t8, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r4, java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), _r5, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r6); else rotateXYZ_s6c64e0a1_tail_mulAdd(d, _t12, _t5, _t9, _t8, _r0, _r1, _r2, _r3, ((_t9) * (_t5) + (_t12 * _t8)), _r4, ((_t10) * (_t8) - (_t11 * _t5)), _r5, ((_t10) * (_t5) + (_t11 * _t8)), _r6);
        return d;
    }


    /**
     * Apply a rotation of {@code angle} radians about the Y axis to this rigid transform and store
     * the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateY(double angle, @Mutated DoubleRigid dest) {
        if (Math.useFma()) {
            DoubleRigidImpl d = (DoubleRigidImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.rX;
            double _rd1 = this.rY;
            d.tX = this.tX;
            d.tY = this.tY;
            d.tZ = this.tZ;
            d.rX = java.lang.Math.fma(_rd0, _t2, -(this.rZ * _t1));
            d.rY = java.lang.Math.fma(_rd1, _t2, this.rW * _t1);
            d.rZ = java.lang.Math.fma(_rd0, _t1, this.rZ * _t2);
            d.rW = java.lang.Math.fma(this.rW, _t2, -(_rd1 * _t1));
            return d;
        } else {
            DoubleRigidImpl d = (DoubleRigidImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.rX;
            double _rd1 = this.rY;
            d.tX = this.tX;
            d.tY = this.tY;
            d.tZ = this.tZ;
            d.rX = ((_rd0) * (_t2) - (this.rZ * _t1));
            d.rY = ((_rd1) * (_t2) + (this.rW * _t1));
            d.rZ = ((_rd0) * (_t1) + (this.rZ * _t2));
            d.rW = ((this.rW) * (_t2) - (_rd1 * _t1));
            return d;
        }
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this rigid transform and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateYXZ(double angleY, double angleX, double angleZ, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _r0 = this.tX;
        double _r1 = this.tY;
        double _r2 = this.tZ;
        double _r3 = this.rX;
        double _r4 = this.rW;
        double _r5 = this.rY;
        double _r6 = this.rZ;
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
        if (Math.useFma()) rotateXYZ_s6c64e0a1_tail_fma(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r4, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r5, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r6); else rotateXYZ_s6c64e0a1_tail_mulAdd(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, ((_t9) * (_t5) + (_t12 * _t8)), _r4, ((_t10) * (_t8) + (_t11 * _t5)), _r5, ((_t12) * (_t5) - (_t9 * _t8)), _r6);
        return d;
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void rotateYZX_s7cf157c3_tail_fma(DoubleRigidImpl _dst, double _t10, double _t8, double _t11, double _t5, double _r0, double _r1, double _r2, double _r3, double _t21, double _r4, double _t19, double _r5, double _r6, double _t20) {
        double _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        rotateAxis_s5fbf191b_c0_fma(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t22, _r6, _t20);
        preMul_s1abfb90e_c1_fma(_dst, _r5, _t21, _r6, _t19, _r4, _t20, _r3, _t22);
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void rotateYZX_s7cf157c3_tail_mulAdd(DoubleRigidImpl _dst, double _t10, double _t8, double _t11, double _t5, double _r0, double _r1, double _r2, double _r3, double _t21, double _r4, double _t19, double _r5, double _r6, double _t20) {
        double _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        rotateAxis_s5fbf191b_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t22, _r6, _t20);
        preMul_s1abfb90e_c1_mulAdd(_dst, _r5, _t21, _r6, _t19, _r4, _t20, _r3, _t22);
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this rigid transform and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateYZX(double angleY, double angleZ, double angleX, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _r0 = this.tX;
        double _r1 = this.tY;
        double _r2 = this.tZ;
        double _r3 = this.rX;
        double _r4 = this.rW;
        double _r5 = this.rY;
        double _r6 = this.rZ;
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
        if (Math.useFma()) rotateYZX_s7cf157c3_tail_fma(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r4, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r5, _r6, java.lang.Math.fma(_t11, _t8, _t10 * _t5)); else rotateYZX_s7cf157c3_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, ((_t14) * (_t8) - (_t9 * _t5)), _r4, ((_t9) * (_t8) + (_t14 * _t5)), _r5, _r6, ((_t11) * (_t8) + (_t10 * _t5)));
        return d;
    }


    /**
     * Apply a rotation of {@code angle} radians about the Z axis to this rigid transform and store
     * the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateZ(double angle, @Mutated DoubleRigid dest) {
        if (Math.useFma()) {
            DoubleRigidImpl d = (DoubleRigidImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.rX;
            double _rd1 = this.rZ;
            d.tX = this.tX;
            d.tY = this.tY;
            d.tZ = this.tZ;
            d.rX = java.lang.Math.fma(_rd0, _t2, this.rY * _t1);
            d.rY = java.lang.Math.fma(this.rY, _t2, -(_rd0 * _t1));
            d.rZ = java.lang.Math.fma(_rd1, _t2, this.rW * _t1);
            d.rW = java.lang.Math.fma(this.rW, _t2, -(_rd1 * _t1));
            return d;
        } else {
            DoubleRigidImpl d = (DoubleRigidImpl) dest;
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            double _rd0 = this.rX;
            double _rd1 = this.rZ;
            d.tX = this.tX;
            d.tY = this.tY;
            d.tZ = this.tZ;
            d.rX = ((_rd0) * (_t2) + (this.rY * _t1));
            d.rY = ((this.rY) * (_t2) - (_rd0 * _t1));
            d.rZ = ((_rd1) * (_t2) + (this.rW * _t1));
            d.rW = ((this.rW) * (_t2) - (_rd1 * _t1));
            return d;
        }
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s302c98bf_tail_fma(DoubleRigidImpl _dst, double _t10, double _t8, double _t11, double _t5, double _r0, double _r1, double _r2, double _r3, double _t21, double _r4, double _r5, double _t19, double _r6, double _t20) {
        double _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        rotateAxis_s5fbf191b_c0_fma(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t22, _r5, _t19, _r6, _t20);
        preMul_s1abfb90e_c1_fma(_dst, _r5, _t21, _r6, _t22, _r4, _t20, _r3, _t19);
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s302c98bf_tail_mulAdd(DoubleRigidImpl _dst, double _t10, double _t8, double _t11, double _t5, double _r0, double _r1, double _r2, double _r3, double _t21, double _r4, double _r5, double _t19, double _r6, double _t20) {
        double _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        rotateAxis_s5fbf191b_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t22, _r5, _t19, _r6, _t20);
        preMul_s1abfb90e_c1_mulAdd(_dst, _r5, _t21, _r6, _t22, _r4, _t20, _r3, _t19);
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this rigid transform and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateZXY(double angleZ, double angleX, double angleY, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _r0 = this.tX;
        double _r1 = this.tY;
        double _r2 = this.tZ;
        double _r3 = this.rX;
        double _r4 = this.rW;
        double _r5 = this.rY;
        double _r6 = this.rZ;
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
        if (Math.useFma()) rotateZXY_s302c98bf_tail_fma(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r4, _r5, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r6, java.lang.Math.fma(_t9, _t8, _t14 * _t5)); else rotateZXY_s302c98bf_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, ((_t14) * (_t8) - (_t9 * _t5)), _r4, _r5, ((_t10) * (_t5) + (_t11 * _t8)), _r6, ((_t9) * (_t8) + (_t14 * _t5)));
        return d;
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this rigid transform and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateZYX(double angleZ, double angleY, double angleX, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _r0 = this.tX;
        double _r1 = this.tY;
        double _r2 = this.tZ;
        double _r3 = this.rX;
        double _r4 = this.rW;
        double _r5 = this.rY;
        double _r6 = this.rZ;
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
        if (Math.useFma()) rotateYZX_s7cf157c3_tail_fma(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r4, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r5, _r6, java.lang.Math.fma(_t11, _t8, _t10 * _t5)); else rotateYZX_s7cf157c3_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, ((_t9) * (_t5) + (_t12 * _t8)), _r4, ((_t12) * (_t5) - (_t9 * _t8)), _r5, _r6, ((_t11) * (_t8) + (_t10 * _t5)));
        return d;
    }


    /**
     * Apply a translation by {@code translation} to this rigid transform and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code T} the translation rigid transform,
     * then the new rigid transform will be {@code M * T}. So when transforming a vector {@code v}
     * with the new rigid transform by using {@code M * T * v}, the translation will be applied
     * first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid translate(Double3R translation, @Mutated DoubleRigid dest) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _t9 = 2.0 * Math.fma(this.rX, translationY, -(this.rY * translationX));
        double _t10 = 2.0 * Math.fma(this.rZ, translationX, -(this.rX * translationZ));
        double _t11 = 2.0 * Math.fma(this.rY, translationZ, -(this.rZ * translationY));
        d.tX = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + translationX)));
        d.tY = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + translationY)));
        d.tZ = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + translationZ)));
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        return d;
    }


    /**
     * Apply a translation by ({@code translationX}, {@code translationY}, {@code translationZ}) to
     * this rigid transform and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid translate(double translationX, double translationY, double translationZ, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        double _t9 = 2.0 * Math.fma(this.rX, translationY, -(this.rY * translationX));
        double _t10 = 2.0 * Math.fma(this.rZ, translationX, -(this.rX * translationZ));
        double _t11 = 2.0 * Math.fma(this.rY, translationZ, -(this.rZ * translationY));
        d.tX = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + translationX)));
        d.tY = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + translationY)));
        d.tZ = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + translationZ)));
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        return d;
    }


    /**
     * Transform {@code v} by this rigid transform and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transform(Double3R v, @Mutated Double3 dest) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + vZ)));
        return d;
    }


    /**
     * Transform ({@code vX}, {@code vY}, {@code vZ}) by this rigid transform and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transform(double vX, double vY, double vZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + vZ)));
        return d;
    }


    /**
     * Transform the given direction by the rotation part of this rigid transform, ignoring the
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirection(Double3R v, @Mutated Double3 dest) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ)));
        return d;
    }


    /**
     * Transform the given direction by the rotation part of this rigid transform, ignoring the
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirection(double vX, double vY, double vZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ)));
        return d;
    }


    /**
     * Transform the given direction by the inverse of this rigid transform's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirectionInverse(Double3R v, @Mutated Double3 dest) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.rX, vZ, -(this.rZ * vX));
        double _t10 = 2.0 * Math.fma(this.rY, vX, -(this.rX * vY));
        double _t11 = 2.0 * Math.fma(this.rZ, vY, -(this.rY * vZ));
        d.x = Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX)));
        d.y = Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY)));
        d.z = Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ)));
        return d;
    }


    /**
     * Transform the given direction by the inverse of this rigid transform's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirectionInverse(double vX, double vY, double vZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.rX, vZ, -(this.rZ * vX));
        double _t10 = 2.0 * Math.fma(this.rY, vX, -(this.rX * vY));
        double _t11 = 2.0 * Math.fma(this.rZ, vY, -(this.rY * vZ));
        d.x = Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX)));
        d.y = Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY)));
        d.z = Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ)));
        return d;
    }


    /**
     * Transform {@code p} by the inverse of this rigid transform and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformInverse(Double3R p, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = p.z() - this.tZ;
        double _t1 = p.x() - this.tX;
        double _t2 = p.y() - this.tY;
        double _t12 = 2.0 * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        double _t13 = 2.0 * Math.fma(this.rY, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        d.x = Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1)));
        d.y = Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2)));
        d.z = Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0)));
        return d;
    }


    /**
     * Transform ({@code pX}, {@code pY}, {@code pZ}) by the inverse of this rigid transform and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformInverse(double pX, double pY, double pZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = pZ - this.tZ;
        double _t1 = pX - this.tX;
        double _t2 = pY - this.tY;
        double _t12 = 2.0 * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        double _t13 = 2.0 * Math.fma(this.rY, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        d.x = Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1)));
        d.y = Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2)));
        d.z = Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0)));
        return d;
    }


    /**
     * Transform the given position by this rigid transform, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param v the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPosition(Double3R v, @Mutated Double3 dest) {
        return transform(v, dest);
    }


    /**
     * Transform the given position by this rigid transform, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPosition(double vX, double vY, double vZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t9 = 2.0 * Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + vZ)));
        return d;
    }


    /**
     * Transform the given position by the inverse of this rigid transform (world to local), without
     * materializing {@code invert()} and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPositionInverse(Double3R p, @Mutated Double3 dest) {
        return transformInverse(p, dest);
    }


    /**
     * Transform the given position by the inverse of this rigid transform (world to local), without
     * materializing {@code invert()} and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPositionInverse(double pX, double pY, double pZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _t0 = pZ - this.tZ;
        double _t1 = pX - this.tX;
        double _t2 = pY - this.tY;
        double _t12 = 2.0 * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        double _t13 = 2.0 * Math.fma(this.rY, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        d.x = Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1)));
        d.y = Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2)));
        d.z = Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0)));
        return d;
    }

    public double tX() { return this.tX; }
    public double tY() { return this.tY; }
    public double tZ() { return this.tZ; }
    public double rX() { return this.rX; }
    public double rY() { return this.rY; }
    public double rZ() { return this.rZ; }
    public double rW() { return this.rW; }

    @Override public String toString() {
        return "DoubleRigid(" + tX() + ", " + tY() + ", " + tZ() + ", " + rX() + ", " + rY() + ", " + rZ() + ", " + rW() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleRigidImpl)) return false;
        DoubleRigidImpl o = (DoubleRigidImpl) obj;
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

    @Override public boolean isFinite() {
        return Double.isFinite(tX)
            && Double.isFinite(tY)
            && Double.isFinite(tZ)
            && Double.isFinite(rX)
            && Double.isFinite(rY)
            && Double.isFinite(rZ)
            && Double.isFinite(rW);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(tX)
            || Double.isNaN(tY)
            || Double.isNaN(tZ)
            || Double.isNaN(rX)
            || Double.isNaN(rY)
            || Double.isNaN(rZ)
            || Double.isNaN(rW);
    }

    @Override public boolean equalsEpsilon(DoubleRigidR other, double epsilon) {
        return java.lang.Math.abs(tX - other.tX()) <= epsilon
            && java.lang.Math.abs(tY - other.tY()) <= epsilon
            && java.lang.Math.abs(tZ - other.tZ()) <= epsilon
            && java.lang.Math.abs(rX - other.rX()) <= epsilon
            && java.lang.Math.abs(rY - other.rY()) <= epsilon
            && java.lang.Math.abs(rZ - other.rZ()) <= epsilon
            && java.lang.Math.abs(rW - other.rW()) <= epsilon;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.tX;
        dest[offset + 1] = this.tY;
        dest[offset + 2] = this.tZ;
        dest[offset + 3] = this.rX;
        dest[offset + 4] = this.rY;
        dest[offset + 5] = this.rZ;
        dest[offset + 6] = this.rW;
        return dest;
    }
    public @Mutated DoubleRigid load(double[] src, int offset) {
        this.tX = src[offset];
        this.tY = src[offset + 1];
        this.tZ = src[offset + 2];
        this.rX = src[offset + 3];
        this.rY = src[offset + 4];
        this.rZ = src[offset + 5];
        this.rW = src[offset + 6];
        return this;
    }
    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 7) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 7);
        return buf;
    }
    @Mutated public DoubleRigid load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public DoubleRigid loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public DoubleRigid loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 7) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 7);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 56) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 56);
        return buf;
    }
    public DoubleRigid load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public DoubleRigid loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public DoubleRigid loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 56) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleRigid r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 56);
        return r;
    }
    public DoubleRigid storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public DoubleRigid loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public DoubleRigid load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public DoubleRigid load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.tX;
        dest[offset + 1] = (float) this.tY;
        dest[offset + 2] = (float) this.tZ;
        dest[offset + 3] = (float) this.rX;
        dest[offset + 4] = (float) this.rY;
        dest[offset + 5] = (float) this.rZ;
        dest[offset + 6] = (float) this.rW;
        return dest;
    }
    public @Mutated DoubleRigid load(float[] src, int offset) {
        this.tX = src[offset];
        this.tY = src[offset + 1];
        this.tZ = src[offset + 2];
        this.rX = src[offset + 3];
        this.rY = src[offset + 4];
        this.rZ = src[offset + 5];
        this.rW = src[offset + 6];
        return this;
    }
    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 7) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 7);
        return buf;
    }
    @Mutated public DoubleRigid load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public DoubleRigid loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public DoubleRigid loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 7) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 7);
        return this;
    }
    public ByteBuffer storeFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 28) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 28);
        return buf;
    }
    public DoubleRigid loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, buf.position(), buf);
    }
    public DoubleRigid loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public DoubleRigid loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 28) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleRigid r = StoreLoad.BB_OPS.loadFloatAbsolute(this, pos, buf);
        buf.position(pos + 28);
        return r;
    }
    public DoubleRigid storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public DoubleRigid loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }
    public MemorySegment storeFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }
    public MemorySegment storeFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }
    @Mutated public DoubleRigid loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(this, 0L, src); }
    public DoubleRigid loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(this, offset, src);
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
