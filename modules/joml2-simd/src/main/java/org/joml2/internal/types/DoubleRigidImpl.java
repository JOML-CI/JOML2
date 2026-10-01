// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import jdk.incubator.vector.*;
import org.joml2.internal.simd.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link DoubleRigid} backed by a {@code double[]} array, with Vector
 * API SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoubleRigidImpl implements DoubleRigid {

    public double[] data;

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
        data = new double[7];
        data[6] = 1;
    }

    public DoubleRigidImpl(double tX, double tY, double tZ, double rX, double rY, double rZ, double rW) {
        double[] dd = this.data = new double[7];
        dd[0] = tX;
        dd[1] = tY;
        dd[2] = tZ;
        dd[3] = rX;
        dd[4] = rY;
        dd[5] = rZ;
        dd[6] = rW;
    }

    public DoubleRigidImpl(DoubleRigidR src) {
        double[] dd = this.data = new double[7];
        dd[0] = src.tX();
        dd[1] = src.tY();
        dd[2] = src.tZ();
        dd[3] = src.rX();
        dd[4] = src.rY();
        dd[5] = src.rZ();
        dd[6] = src.rW();
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
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = translation.x();
        dd[1] = translationY;
        dd[2] = translationZ;
        dd[3] = axisX * _t1;
        dd[4] = axisY * _t1;
        dd[5] = axisZ * _t1;
        dd[6] = Math.cosFromSin(_t1, _t0);
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
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = translationX;
        dd[1] = translationY;
        dd[2] = translationZ;
        dd[3] = axisX * _t1;
        dd[4] = axisY * _t1;
        dd[5] = axisZ * _t1;
        dd[6] = Math.cosFromSin(_t1, _t0);
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
        double[] dd = this.data;
        dd[0] = translation.x();
        dd[1] = translationY;
        dd[2] = translationZ;
        dd[3] = rotationX;
        dd[4] = rotationY;
        dd[5] = rotationZ;
        dd[6] = rotationW;
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
        double[] dd = this.data;
        dd[0] = translationX;
        dd[1] = translationY;
        dd[2] = translationZ;
        dd[3] = rotationX;
        dd[4] = rotationY;
        dd[5] = rotationZ;
        dd[6] = rotationW;
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
        double[] dd = this.data;
        dd[0] = v.tX();
        dd[1] = vTY;
        dd[2] = vTZ;
        dd[3] = vRX;
        dd[4] = vRY;
        dd[5] = vRZ;
        dd[6] = vRW;
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
        double[] dd = this.data;
        dd[0] = vTX;
        dd[1] = vTY;
        dd[2] = vTZ;
        dd[3] = vRX;
        dd[4] = vRY;
        dd[5] = vRZ;
        dd[6] = vRW;
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = rX;
        dd[4] = rY;
        dd[5] = rZ;
        dd[6] = rW;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = rX;
        dd[4] = rY;
        dd[5] = rZ;
        dd[6] = rW;
        return dest;
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
        if (COL_NARROW) return setTranslation_narrow(t, dest);
        double tY = t.y();
        double tZ = t.z();
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = t.x();
        dd[1] = tY;
        dd[2] = tZ;
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        return dest;
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
        if (COL_NARROW) return setTranslation_narrow(tX, tY, tZ, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = tX;
        dd[1] = tY;
        dd[2] = tZ;
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        return dest;
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
        double dqRX = dq.rX();
        double dqRY = dq.rY();
        double dqRZ = dq.rZ();
        double dqRW = dq.rW();
        double dqDX = dq.dX();
        double dqDY = dq.dY();
        double dqDZ = dq.dZ();
        double dqDW = dq.dW();
        double[] dd = this.data;
        dd[0] = 2.0 * (Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + Math.fma(dqRW, dqDX, -(dqRX * dqDW)));
        dd[1] = 2.0 * (Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + Math.fma(dqRW, dqDY, -(dqRY * dqDW)));
        dd[2] = 2.0 * (Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + Math.fma(dqRW, dqDZ, -(dqRZ * dqDW)));
        dd[3] = dqRX;
        dd[4] = dqRY;
        dd[5] = dqRZ;
        dd[6] = dqRW;
        return this;
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
        double[] dd = this.data;
        dd[0] = 2.0 * (Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + Math.fma(dqRW, dqDX, -(dqRX * dqDW)));
        dd[1] = 2.0 * (Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + Math.fma(dqRW, dqDY, -(dqRY * dqDW)));
        dd[2] = 2.0 * (Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + Math.fma(dqRW, dqDZ, -(dqRZ * dqDW)));
        dd[3] = dqRX;
        dd[4] = dqRY;
        dd[5] = dqRZ;
        dd[6] = dqRW;
        return this;
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
        double[] dd = this.data;
        double[] mData = ((Double3x3Impl) m).data;
        double _ld0 = mData[4];
        double _ld1 = mData[8];
        double _ld2 = mData[5];
        double _ld3 = mData[3];
        double _ld4 = mData[6];
        double _ld5 = mData[7];
        double _ld6 = mData[2];
        double _ld7 = mData[0];
        double _ld8 = mData[1];
        double _ct0 = Math.fma(_ld2, _ld2, Math.fma(_ld3, _ld3, _ld0 * _ld0));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _ct1 = Math.fma(_ld1, _ld1, Math.fma(_ld4, _ld4, _ld5 * _ld5));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _ct2 = Math.fma(_ld6, _ld6, Math.fma(_ld7, _ld7, _ld8 * _ld8));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        makeFromMatrix_s81574d1e_520(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ct0, _ct1, _ct2);
        return this;
    }

    /**
     * Part of {@code makeFromMatrix}, split to fit the inline budget. Shared by 3 identical private
     * paths of {@code makeFromMatrix}; reached only through it.
     */
    private void makeFromMatrix_s81574d1e_520(double[] dd, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld6, double _ld7, double _ld8, double _ct0, double _ct1, double _ct2) {
        double _t15 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        double _t18 = _ld8 * _t17;
        double _t19 = _ld1 * _t16;
        double _t20 = _ld5 * _t16;
        double _t21 = _ld6 * _t17;
        double _t23 = _ld2 * _t15;
        double _t24 = _ld0 * _t15;
        double _t26 = _ld7 * _t17;
        double _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), _ld3 * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), _ld4 * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        double _t51 = 1.0 + _t47;
        double _t63 = Math.fma(_ld0, _t15, Math.fma(_ld1, _t16, _t51));
        makeFromMatrix_s81574d1e_520_s5844c544_1(dd, _ld0, _ld1, -_ld0, -_ld1, _t15, _t16, _t19, _t24, Math.fma(_ld5, _t16, _t23), Math.fma(_ld2, _t15, -_t20), _t47, _t51, 1.0 - _t47, Math.fma(_ld3, _t15, _t48), Math.fma(_ld4, _t16, _t49), Math.fma(_ld4, _t16, -_t49), Math.fma(-_ld3, _t15, _t48), _t63, 0.5 * (1.0 / java.lang.Math.sqrt(_t63)));
    }

    /**
     * Piece 2 of {@code makeFromMatrix_s81574d1e_520}, split to fit the inline budget. Shared by 3
     * identical private paths of {@code makeFromMatrix}; reached only through it.
     */
    private void makeFromMatrix_s81574d1e_520_s5844c544_1(double[] dd, double _ld0, double _ld1, double _t0, double _t1, double _t15, double _t16, double _t19, double _t24, double _t31, double _t35, double _t47, double _t51, double _t52, double _t54, double _t55, double _t56, double _t57, double _t63, double _sp0) {
        double _t65 = Math.fma(_ld0, _t15, Math.fma(_t1, _t16, _t52));
        double _t66 = Math.fma(_ld1, _t16, Math.fma(_t0, _t15, _t52));
        double _t67 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t65));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        if (Math.fma(_ld0, _t15, Math.fma(_ld1, _t16, _t47)) > 0.0) {
            dd[3] = _sp0 * _t35;
            dd[4] = _sp0 * _t56;
            dd[5] = _sp0 * _t57;
            dd[6] = 0.5 * java.lang.Math.sqrt(_t63);
        } else {
            if (_t47 > java.lang.Math.max(_t24, _t19)) {
                dd[3] = 0.5 * java.lang.Math.sqrt(_t67);
                dd[4] = _sp3 * _t54;
                dd[5] = _sp3 * _t55;
                dd[6] = _sp3 * _t35;
            } else {
                if (_t24 > _t19) {
                    dd[3] = _sp1 * _t54;
                    dd[4] = 0.5 * java.lang.Math.sqrt(_t65);
                    dd[5] = _sp1 * _t31;
                    dd[6] = _sp1 * _t56;
                } else {
                    dd[3] = _sp2 * _t55;
                    dd[4] = _sp2 * _t31;
                    dd[5] = 0.5 * java.lang.Math.sqrt(_t66);
                    dd[6] = _sp2 * _t57;
                }
            }
        }
    }


    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private DoubleRigid makeFromMatrix_degenerate(Double3x3R m) {
        double[] dd = this.data;
        double[] mData = ((Double3x3Impl) m).data;
        double _t0 = unitScale(mData[3], mData[4], mData[5]);
        double _t1 = unitScale(mData[6], mData[7], mData[8]);
        double _t2 = unitScale(mData[0], mData[1], mData[2]);
        double _t12 = mData[5] * _t0;
        double _t13 = mData[3] * _t0;
        double _t14 = mData[4] * _t0;
        double _t15 = mData[8] * _t1;
        double _t16 = mData[6] * _t1;
        double _t17 = mData[7] * _t1;
        double _t18 = mData[2] * _t2;
        double _t19 = mData[0] * _t2;
        double _t20 = mData[1] * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
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
        double _t99 = (1.0 / java.lang.Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / java.lang.Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / java.lang.Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
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
                    _t166 = Math.fma(_t33, _t102, -(_t34 * _t106));
                    _t168 = Math.fma(_t35, _t106, -(_t33 * _t116));
                    _t171 = Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = Math.fma(_t36, _t105, -(_t37 * _t114));
                    _t167 = Math.fma(_t37, _t103, -(_t38 * _t105));
                    _t170 = Math.fma(_t38, _t114, -(_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = Math.fma(_t33, _t36, -(_t35 * _t37));
                    _t167 = Math.fma(_t34, _t37, -(_t33 * _t38));
                    _t170 = Math.fma(_t35, _t38, -(_t34 * _t36));
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
                    _t169 = Math.fma(_t39, _t115, -(_t41 * _t104));
                    _t172 = Math.fma(_t40, _t104, -(_t39 * _t107));
                    _t173 = Math.fma(_t41, _t107, -(_t40 * _t115));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = Math.fma(_t33, _t39, -(_t34 * _t40));
                    _t168 = Math.fma(_t35, _t40, -(_t33 * _t41));
                    _t171 = Math.fma(_t34, _t41, -(_t35 * _t39));
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
                    _t169 = Math.fma(_t39, _t36, -(_t41 * _t38));
                    _t172 = Math.fma(_t40, _t38, -(_t39 * _t37));
                    _t173 = Math.fma(_t41, _t37, -(_t40 * _t36));
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
        if (Math.fma(Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, Math.fma(Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0) {
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
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        if (_t206 > 0.0) {
            dd[3] = _sp0 * _t182;
            dd[4] = _sp0 * _t201;
            dd[5] = _sp0 * _t202;
            dd[6] = 0.5 * java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                dd[3] = 0.5 * java.lang.Math.sqrt(_t208);
                dd[4] = _sp3 * _t199;
                dd[5] = _sp3 * _t200;
                dd[6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dd[3] = _sp1 * _t199;
                    dd[4] = 0.5 * java.lang.Math.sqrt(_t209);
                    dd[5] = _sp1 * _t184;
                    dd[6] = _sp1 * _t201;
                } else {
                    dd[3] = _sp2 * _t200;
                    dd[4] = _sp2 * _t184;
                    dd[5] = 0.5 * java.lang.Math.sqrt(_t210);
                    dd[6] = _sp2 * _t202;
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
        double[] dd = this.data;
        double[] mData = ((Double3x4Impl) m).data;
        double _ld0 = mData[5];
        double _ld1 = mData[10];
        double _ld2 = mData[9];
        double _ld3 = mData[1];
        double _ld4 = mData[2];
        double _ld5 = mData[6];
        double _ld6 = mData[8];
        double _ld7 = mData[0];
        double _ld8 = mData[4];
        double _ld10 = mData[7];
        double _ld11 = mData[11];
        double _ct0 = Math.fma(_ld2, _ld2, Math.fma(_ld3, _ld3, _ld0 * _ld0));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _ct1 = Math.fma(_ld1, _ld1, Math.fma(_ld4, _ld4, _ld5 * _ld5));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _ct2 = Math.fma(_ld6, _ld6, Math.fma(_ld7, _ld7, _ld8 * _ld8));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        dd[0] = mData[3];
        dd[1] = _ld10;
        dd[2] = _ld11;
        makeFromMatrix_s81574d1e_520(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ct0, _ct1, _ct2);
        return this;
    }


    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private DoubleRigid makeFromMatrix_degenerate(Double3x4R m) {
        double[] dd = this.data;
        double[] mData = ((Double3x4Impl) m).data;
        double _t0 = unitScale(mData[1], mData[5], mData[9]);
        double _t1 = unitScale(mData[2], mData[6], mData[10]);
        double _t2 = unitScale(mData[0], mData[4], mData[8]);
        double _t12 = mData[9] * _t0;
        double _t13 = mData[1] * _t0;
        double _t14 = mData[5] * _t0;
        double _t15 = mData[10] * _t1;
        double _t16 = mData[2] * _t1;
        double _t17 = mData[6] * _t1;
        double _t18 = mData[8] * _t2;
        double _t19 = mData[0] * _t2;
        double _t20 = mData[4] * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
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
        double _t99 = (1.0 / java.lang.Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / java.lang.Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / java.lang.Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
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
                    _t166 = Math.fma(_t33, _t102, -(_t34 * _t106));
                    _t168 = Math.fma(_t35, _t106, -(_t33 * _t116));
                    _t171 = Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = Math.fma(_t36, _t105, -(_t37 * _t114));
                    _t167 = Math.fma(_t37, _t103, -(_t38 * _t105));
                    _t170 = Math.fma(_t38, _t114, -(_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = Math.fma(_t33, _t36, -(_t35 * _t37));
                    _t167 = Math.fma(_t34, _t37, -(_t33 * _t38));
                    _t170 = Math.fma(_t35, _t38, -(_t34 * _t36));
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
                    _t169 = Math.fma(_t39, _t115, -(_t41 * _t104));
                    _t172 = Math.fma(_t40, _t104, -(_t39 * _t107));
                    _t173 = Math.fma(_t41, _t107, -(_t40 * _t115));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = Math.fma(_t33, _t39, -(_t34 * _t40));
                    _t168 = Math.fma(_t35, _t40, -(_t33 * _t41));
                    _t171 = Math.fma(_t34, _t41, -(_t35 * _t39));
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
                    _t169 = Math.fma(_t39, _t36, -(_t41 * _t38));
                    _t172 = Math.fma(_t40, _t38, -(_t39 * _t37));
                    _t173 = Math.fma(_t41, _t37, -(_t40 * _t36));
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
        if (Math.fma(Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, Math.fma(Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0) {
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
        dd[0] = mData[3];
        dd[1] = mData[7];
        dd[2] = mData[11];
        if (_t206 > 0.0) {
            dd[3] = _sp0 * _t182;
            dd[4] = _sp0 * _t201;
            dd[5] = _sp0 * _t202;
            dd[6] = 0.5 * java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                dd[3] = 0.5 * java.lang.Math.sqrt(_t208);
                dd[4] = _sp3 * _t199;
                dd[5] = _sp3 * _t200;
                dd[6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dd[3] = _sp1 * _t199;
                    dd[4] = 0.5 * java.lang.Math.sqrt(_t209);
                    dd[5] = _sp1 * _t184;
                    dd[6] = _sp1 * _t201;
                } else {
                    dd[3] = _sp2 * _t200;
                    dd[4] = _sp2 * _t184;
                    dd[5] = 0.5 * java.lang.Math.sqrt(_t210);
                    dd[6] = _sp2 * _t202;
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
        double[] dd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        double _ld0 = mData[5];
        double _ld1 = mData[10];
        double _ld2 = mData[6];
        double _ld3 = mData[4];
        double _ld4 = mData[8];
        double _ld5 = mData[9];
        double _ld6 = mData[2];
        double _ld7 = mData[0];
        double _ld8 = mData[1];
        double _ld10 = mData[13];
        double _ld11 = mData[14];
        double _ct0 = Math.fma(_ld2, _ld2, Math.fma(_ld3, _ld3, _ld0 * _ld0));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _ct1 = Math.fma(_ld1, _ld1, Math.fma(_ld4, _ld4, _ld5 * _ld5));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _ct2 = Math.fma(_ld6, _ld6, Math.fma(_ld7, _ld7, _ld8 * _ld8));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        dd[0] = mData[12];
        dd[1] = _ld10;
        dd[2] = _ld11;
        makeFromMatrix_s81574d1e_520(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ct0, _ct1, _ct2);
        return this;
    }


    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private DoubleRigid makeFromMatrix_degenerate(Double4x4R m) {
        double[] dd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        double _t0 = unitScale(mData[4], mData[5], mData[6]);
        double _t1 = unitScale(mData[8], mData[9], mData[10]);
        double _t2 = unitScale(mData[0], mData[1], mData[2]);
        double _t12 = mData[6] * _t0;
        double _t13 = mData[4] * _t0;
        double _t14 = mData[5] * _t0;
        double _t15 = mData[10] * _t1;
        double _t16 = mData[8] * _t1;
        double _t17 = mData[9] * _t1;
        double _t18 = mData[2] * _t2;
        double _t19 = mData[0] * _t2;
        double _t20 = mData[1] * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
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
        double _t99 = (1.0 / java.lang.Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / java.lang.Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / java.lang.Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
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
                    _t166 = Math.fma(_t33, _t102, -(_t34 * _t106));
                    _t168 = Math.fma(_t35, _t106, -(_t33 * _t116));
                    _t171 = Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = Math.fma(_t36, _t105, -(_t37 * _t114));
                    _t167 = Math.fma(_t37, _t103, -(_t38 * _t105));
                    _t170 = Math.fma(_t38, _t114, -(_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = Math.fma(_t33, _t36, -(_t35 * _t37));
                    _t167 = Math.fma(_t34, _t37, -(_t33 * _t38));
                    _t170 = Math.fma(_t35, _t38, -(_t34 * _t36));
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
                    _t169 = Math.fma(_t39, _t115, -(_t41 * _t104));
                    _t172 = Math.fma(_t40, _t104, -(_t39 * _t107));
                    _t173 = Math.fma(_t41, _t107, -(_t40 * _t115));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = Math.fma(_t33, _t39, -(_t34 * _t40));
                    _t168 = Math.fma(_t35, _t40, -(_t33 * _t41));
                    _t171 = Math.fma(_t34, _t41, -(_t35 * _t39));
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
                    _t169 = Math.fma(_t39, _t36, -(_t41 * _t38));
                    _t172 = Math.fma(_t40, _t38, -(_t39 * _t37));
                    _t173 = Math.fma(_t41, _t37, -(_t40 * _t36));
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
        if (Math.fma(Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, Math.fma(Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0) {
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
        dd[0] = mData[12];
        dd[1] = mData[13];
        dd[2] = mData[14];
        if (_t206 > 0.0) {
            dd[3] = _sp0 * _t182;
            dd[4] = _sp0 * _t201;
            dd[5] = _sp0 * _t202;
            dd[6] = 0.5 * java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                dd[3] = 0.5 * java.lang.Math.sqrt(_t208);
                dd[4] = _sp3 * _t199;
                dd[5] = _sp3 * _t200;
                dd[6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dd[3] = _sp1 * _t199;
                    dd[4] = 0.5 * java.lang.Math.sqrt(_t209);
                    dd[5] = _sp1 * _t184;
                    dd[6] = _sp1 * _t201;
                } else {
                    dd[3] = _sp2 * _t200;
                    dd[4] = _sp2 * _t184;
                    dd[5] = 0.5 * java.lang.Math.sqrt(_t210);
                    dd[6] = _sp2 * _t202;
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
        double[] dd = this.data;
        dd[0] = t.tX();
        dd[1] = tTY;
        dd[2] = tTZ;
        dd[3] = tRX;
        dd[4] = tRY;
        dd[5] = tRZ;
        dd[6] = tRW;
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
        double[] dd = this.data;
        dd[0] = tTX;
        dd[1] = tTY;
        dd[2] = tTZ;
        dd[3] = tRX;
        dd[4] = tRY;
        dd[5] = tRZ;
        dd[6] = tRW;
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
        double[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        dd[3] = (float) (sd[3]);
        dd[4] = (float) (sd[4]);
        dd[5] = (float) (sd[5]);
        dd[6] = (float) (sd[6]);
        return dest;
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
        if (COL_NARROW) return toDualQuat_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = -sd[2];
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 0);
        dd[4] = 0.5 * Math.fma(_t0, sd[4], Math.fma(sd[0], sd[6], sd[1] * sd[5]));
        dd[5] = 0.5 * Math.fma(sd[2], sd[3], Math.fma(sd[1], sd[6], -(sd[0] * sd[5])));
        dd[6] = 0.5 * Math.fma(sd[2], sd[6], Math.fma(sd[0], sd[4], -(sd[1] * sd[3])));
        dd[7] = 0.5 * Math.fma(_t0, sd[5], Math.fma(-sd[1], sd[4], -(sd[0] * sd[3])));
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        double _t0 = sd[5] * sd[5];
        double _t1 = sd[5] * sd[6];
        double _t2 = sd[4] * sd[6];
        dd[0] = Math.fma(-2.0, Math.fma(sd[4], sd[4], _t0), 1.0);
        dd[1] = 2.0 * Math.fma(sd[3], sd[4], _t1);
        dd[2] = 2.0 * Math.fma(sd[3], sd[5], -_t2);
        dd[3] = 0.0;
        dd[4] = 2.0 * Math.fma(sd[3], sd[4], -_t1);
        dd[5] = Math.fma(-2.0, Math.fma(sd[3], sd[3], _t0), 1.0);
        dd[6] = 2.0 * Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        dd[7] = 0.0;
        dd[8] = 2.0 * Math.fma(sd[3], sd[5], _t2);
        dd[9] = 2.0 * Math.fma(sd[4], sd[5], -(sd[3] * sd[6]));
        dd[10] = Math.fma(-2.0, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0);
        dd[11] = 0.0;
        dd[12] = sd[0];
        dd[13] = sd[1];
        dd[14] = sd[2];
        dd[15] = 1.0;
        ((Double4x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = sd[5] * sd[5];
        double _t1 = sd[5] * sd[6];
        double _t2 = sd[4] * sd[6];
        dd[0] = Math.fma(-2.0, Math.fma(sd[4], sd[4], _t0), 1.0);
        dd[1] = 2.0 * Math.fma(sd[3], sd[4], _t1);
        dd[2] = 2.0 * Math.fma(sd[3], sd[5], -_t2);
        dd[3] = 2.0 * Math.fma(sd[3], sd[4], -_t1);
        dd[4] = Math.fma(-2.0, Math.fma(sd[3], sd[3], _t0), 1.0);
        dd[5] = 2.0 * Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        dd[6] = 2.0 * Math.fma(sd[3], sd[5], _t2);
        dd[7] = 2.0 * Math.fma(sd[4], sd[5], -(sd[3] * sd[6]));
        dd[8] = Math.fma(-2.0, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0);
        ((Double3x3Impl) dest).properties = 0;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        double _t0 = sd[5] * sd[5];
        double _t1 = sd[5] * sd[6];
        double _t2 = sd[4] * sd[6];
        dd[0] = Math.fma(-2.0, Math.fma(sd[4], sd[4], _t0), 1.0);
        dd[1] = 2.0 * Math.fma(sd[3], sd[4], -_t1);
        dd[2] = 2.0 * Math.fma(sd[3], sd[5], _t2);
        dd[3] = sd[0];
        dd[4] = 2.0 * Math.fma(sd[3], sd[4], _t1);
        dd[5] = Math.fma(-2.0, Math.fma(sd[3], sd[3], _t0), 1.0);
        dd[6] = 2.0 * Math.fma(sd[4], sd[5], -(sd[3] * sd[6]));
        dd[7] = sd[1];
        dd[8] = 2.0 * Math.fma(sd[3], sd[5], -_t2);
        dd[9] = 2.0 * Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        dd[10] = Math.fma(-2.0, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0);
        dd[11] = sd[2];
        ((Double3x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
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
        if (COL_NARROW) return toTransform_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return dest;
    }


    /**
     * Set this rigid transform to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public DoubleRigid makeIdentity() {
        double[] dd = this.data;
        System.arraycopy(DATA_0, 0, dd, 0, 7);
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
        double[] dd = this.data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = rotationX;
        dd[4] = rotationY;
        dd[5] = rotationZ;
        dd[6] = rotationW;
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
        double[] dd = this.data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = rotationX;
        dd[4] = rotationY;
        dd[5] = rotationZ;
        dd[6] = rotationW;
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
        if (COL_NARROW) return set_narrow(translation);
        double translationY = translation.y();
        double translationZ = translation.z();
        double[] dd = this.data;
        dd[0] = translation.x();
        dd[1] = translationY;
        dd[2] = translationZ;
        VEC_1.intoArray(dd, 3);
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
        if (COL_NARROW) return set_narrow(translationX, translationY, translationZ);
        double[] dd = this.data;
        dd[0] = translationX;
        dd[1] = translationY;
        dd[2] = translationZ;
        VEC_1.intoArray(dd, 3);
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
        double otherTX = other.tX();
        double otherTY = other.tY();
        double otherTZ = other.tZ();
        double otherRX = other.rX();
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        double[] sd = this.data;
        double _t0 = 1.0 - t;
        double _t12 = Math.fma(otherRW, sd[6], Math.fma(otherRZ, sd[5], Math.fma(otherRX, sd[3], otherRY * sd[4])));
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
        return lerp_s649c5e17_1(otherTX, otherTY, otherTZ, t, dest, sd, ((DoubleRigidImpl) dest).data, _t0, _t17, 1.0 / _t17, Math.sin(t * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16));
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
        double[] sd = this.data;
        double _t0 = 1.0 - t;
        double _t12 = Math.fma(otherRW, sd[6], Math.fma(otherRZ, sd[5], Math.fma(otherRX, sd[3], otherRY * sd[4])));
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
        return lerp_s649c5e17_1(otherTX, otherTY, otherTZ, t, dest, sd, ((DoubleRigidImpl) dest).data, _t0, _t17, 1.0 / _t17, Math.sin(t * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleRigid lerp_s649c5e17_1(double otherTX, double otherTY, double otherTZ, double t, DoubleRigid dest, double[] sd, double[] dd, double _t0, double _t17, double _t17_inv, double _t19, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t42, _t43, _t44, _t45;
        if (_t17 > 0.0) {
            _t42 = Math.fma(sd[6], _t25, _t19 * _t21) * _t17_inv;
            _t43 = Math.fma(sd[5], _t25, _t19 * _t22) * _t17_inv;
            _t44 = Math.fma(sd[3], _t25, _t19 * _t23) * _t17_inv;
            _t45 = Math.fma(sd[4], _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = Math.fma(t, _t21, sd[6] * _t0);
            _t43 = Math.fma(t, _t22, sd[5] * _t0);
            _t44 = Math.fma(t, _t23, sd[3] * _t0);
            _t45 = Math.fma(t, _t24, sd[4] * _t0);
        }
        double _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        dd[0] = Math.fma(t, otherTX - sd[0], sd[0]);
        dd[1] = Math.fma(t, otherTY - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherTZ - sd[2], sd[2]);
        return lerp_s649c5e17_2(dest, dd, _t42, _t43, _t44, _t45, _t49, (1.0 / java.lang.Math.sqrt(_t49)));
    }

    /** Piece 3 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleRigid lerp_s649c5e17_2(DoubleRigid dest, double[] dd, double _t42, double _t43, double _t44, double _t45, double _t49, double _t50) {
        if (_t49 != 0.0) {
            dd[3] = _t50 * _t44;
            dd[4] = _t50 * _t45;
            dd[5] = _t50 * _t43;
            dd[6] = _t50 * _t42;
        } else {
            dd[3] = 0.0;
            dd[4] = 0.0;
            dd[5] = 0.0;
            dd[6] = 0.0;
        }
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _t9 = 2.0 * Math.fma(otherTY, sd[3], -(otherTX * sd[4]));
        double _t10 = 2.0 * Math.fma(otherTX, sd[5], -(otherTZ * sd[3]));
        double _t11 = 2.0 * Math.fma(otherTZ, sd[4], -(otherTY * sd[5]));
        dd[0] = Math.fma(sd[4], _t9, Math.fma(-sd[5], _t10, Math.fma(sd[6], _t11, sd[0] + otherTX)));
        dd[1] = Math.fma(sd[5], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, sd[1] + otherTY)));
        dd[2] = Math.fma(sd[3], _t10, Math.fma(-sd[4], _t11, Math.fma(sd[6], _t9, sd[2] + otherTZ)));
        return mul_s4beb5f4d_1(otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, Math.fma(otherRX, sd[6], otherRW * sd[3]) + Math.fma(otherRZ, sd[4], -(otherRY * sd[5])), Math.fma(otherRX, sd[5], otherRW * sd[4]) + Math.fma(otherRY, sd[6], -(otherRZ * sd[3])));
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private DoubleRigid mul_s4beb5f4d_1(double otherRX, double otherRY, double otherRZ, double otherRW, DoubleRigid dest, double[] sd, double[] dd, double _buf0, double _buf1) {
        double _buf2 = Math.fma(otherRY, sd[3], otherRZ * sd[6]) + Math.fma(otherRW, sd[5], -(otherRX * sd[4]));
        dd[6] = Math.fma(otherRW, sd[6], -(otherRX * sd[3])) - Math.fma(otherRY, sd[4], otherRZ * sd[5]);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _t9 = 2.0 * Math.fma(otherRX, sd[1], -(otherRY * sd[0]));
        double _t10 = 2.0 * Math.fma(otherRZ, sd[0], -(otherRX * sd[2]));
        double _t11 = 2.0 * Math.fma(otherRY, sd[2], -(otherRZ * sd[1]));
        dd[0] = Math.fma(otherRY, _t9, Math.fma(-otherRZ, _t10, Math.fma(otherRW, _t11, otherTX + sd[0])));
        dd[1] = Math.fma(otherRZ, _t11, Math.fma(-otherRX, _t9, Math.fma(otherRW, _t10, otherTY + sd[1])));
        dd[2] = Math.fma(otherRX, _t10, Math.fma(-otherRY, _t11, Math.fma(otherRW, _t9, otherTZ + sd[2])));
        return preMul_sb8a5841c_1(otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, Math.fma(otherRX, sd[6], otherRW * sd[3]) + Math.fma(otherRY, sd[5], -(otherRZ * sd[4])), Math.fma(otherRY, sd[6], otherRZ * sd[3]) + Math.fma(otherRW, sd[4], -(otherRX * sd[5])), Math.fma(otherRX, sd[4], otherRW * sd[5]) + Math.fma(otherRZ, sd[6], -(otherRY * sd[3])));
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private DoubleRigid preMul_sb8a5841c_1(double otherRX, double otherRY, double otherRZ, double otherRW, DoubleRigid dest, double[] sd, double[] dd, double _buf0, double _buf1, double _buf2) {
        dd[6] = Math.fma(otherRW, sd[6], -(otherRX * sd[3])) - Math.fma(otherRY, sd[4], otherRZ * sd[5]);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
        return difference(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), dest);
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _t18 = 2.0 * Math.fma(otherTZ, sd[3], -(otherTX * sd[5]));
        double _t19 = 2.0 * Math.fma(otherTY, sd[5], -(otherTZ * sd[4]));
        double _t20 = 2.0 * Math.fma(otherTX, sd[4], -(otherTY * sd[3]));
        double _t21 = 2.0 * Math.fma(sd[0], sd[5], -(sd[2] * sd[3]));
        double _t22 = 2.0 * Math.fma(sd[1], sd[3], -(sd[0] * sd[4]));
        double _t23 = 2.0 * Math.fma(sd[2], sd[4], -(sd[1] * sd[5]));
        dd[0] = Math.fma(sd[5], _t18, otherTX) + Math.fma(sd[6], _t19, -(sd[4] * _t20)) + (Math.fma(sd[5], _t21, -(sd[4] * _t22)) + Math.fma(sd[6], _t23, -sd[0]));
        return difference_s8691f906_1(otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleRigid difference_s8691f906_1(double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, DoubleRigid dest, double[] sd, double[] dd, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        dd[1] = Math.fma(sd[3], _t20, otherTY) + Math.fma(sd[6], _t18, -(sd[5] * _t19)) + (Math.fma(sd[3], _t22, -(sd[5] * _t23)) + Math.fma(sd[6], _t21, -sd[1]));
        dd[2] = Math.fma(sd[4], _t19, otherTZ) + Math.fma(sd[6], _t20, -(sd[3] * _t18)) + (Math.fma(sd[4], _t23, -(sd[3] * _t21)) + Math.fma(sd[6], _t22, -sd[2]));
        return difference_s8691f906_2(otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, Math.fma(otherRX, sd[6], -(otherRW * sd[3])) + Math.fma(otherRY, sd[5], -(otherRZ * sd[4])), Math.fma(otherRY, sd[6], otherRZ * sd[3]) + Math.fma(-otherRX, sd[5], -(otherRW * sd[4])), Math.fma(otherRX, sd[4], -(otherRW * sd[5])) + Math.fma(otherRZ, sd[6], -(otherRY * sd[3])));
    }

    /** Piece 3 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleRigid difference_s8691f906_2(double otherRX, double otherRY, double otherRZ, double otherRW, DoubleRigid dest, double[] sd, double[] dd, double _buf0, double _buf1, double _buf2) {
        dd[6] = Math.fma(otherRX, sd[3], otherRW * sd[6]) - Math.fma(-otherRZ, sd[5], -(otherRY * sd[4]));
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _t0 = -sd[4];
        double _t1 = -sd[5];
        double _t2 = -sd[3];
        double _t12 = 2.0 * Math.fma(sd[0], sd[5], -(sd[2] * sd[3]));
        double _t13 = 2.0 * Math.fma(sd[1], sd[3], -(sd[0] * sd[4]));
        double _t14 = 2.0 * Math.fma(sd[2], sd[4], -(sd[1] * sd[5]));
        dd[0] = Math.fma(sd[5], _t12, Math.fma(_t0, _t13, Math.fma(sd[6], _t14, -sd[0])));
        dd[1] = Math.fma(sd[3], _t13, Math.fma(_t1, _t14, Math.fma(sd[6], _t12, -sd[1])));
        dd[2] = Math.fma(sd[4], _t14, Math.fma(_t2, _t12, Math.fma(sd[6], _t13, -sd[2])));
        dd[3] = _t2;
        dd[4] = _t0;
        dd[5] = _t1;
        dd[6] = sd[6];
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _t3 = Math.fma(sd[6], sd[6], Math.fma(sd[5], sd[5], Math.fma(sd[3], sd[3], sd[4] * sd[4])));
        double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        if (_t3 != 0.0) {
            dd[3] = sd[3] * _t4;
            dd[4] = sd[4] * _t4;
            dd[5] = sd[5] * _t4;
            dd[6] = sd[6] * _t4;
        } else {
            dd[3] = 0.0;
            dd[4] = 0.0;
            dd[5] = 0.0;
            dd[6] = 0.0;
        }
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[4] * sd[5];
        double _t3 = sd[5] * sd[5];
        double _t8 = 2.0 * Math.fma(sd[3], sd[5], sd[4] * sd[6]);
        double _t9 = 2.0 * Math.fma(sd[3], sd[6], -_t1);
        double _t10 = Math.fma(-2.0, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-15) {
            dd[0] = Math.atan2(2.0 * Math.fma(sd[3], sd[6], _t1), Math.fma(-2.0, Math.fma(sd[3], sd[3], _t3), 1.0));
            dd[2] = 0.0;
        } else {
            dd[0] = Math.atan2(_t9, _t10);
            dd[2] = Math.atan2(2.0 * Math.fma(sd[5], sd[6], -(sd[3] * sd[4])), Math.fma(-2.0, Math.fma(sd[4], sd[4], _t3), 1.0));
        }
        dd[1] = Math.atan2(_t8, java.lang.Math.sqrt(_t12));
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[5] * sd[5];
        double _t1 = sd[4] * sd[5];
        double _t7 = 2.0 * Math.fma(sd[3], sd[6], _t1);
        double _t8 = 2.0 * Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        double _t9 = Math.fma(-2.0, Math.fma(sd[3], sd[3], _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            dd[0] = Math.atan2(2.0 * Math.fma(sd[3], sd[6], -_t1), Math.fma(-2.0, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0));
            dd[1] = 0.0;
        } else {
            dd[0] = Math.atan2(_t7, _t9);
            dd[1] = Math.atan2(2.0 * Math.fma(sd[3], sd[5], sd[4] * sd[6]), Math.fma(-2.0, Math.fma(sd[4], sd[4], _t0), 1.0));
        }
        dd[2] = Math.atan2(_t8, java.lang.Math.sqrt(_t11));
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t3 = sd[5] * sd[5];
        double _t8 = 2.0 * Math.fma(sd[3], sd[5], sd[4] * sd[6]);
        double _t9 = 2.0 * Math.fma(sd[3], sd[6], -(sd[4] * sd[5]));
        double _t10 = Math.fma(-2.0, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        dd[0] = Math.atan2(_t9, java.lang.Math.sqrt(_t12));
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-15) {
            dd[1] = Math.atan2(2.0 * Math.fma(sd[4], sd[6], -(sd[3] * sd[5])), Math.fma(-2.0, Math.fma(sd[4], sd[4], _t3), 1.0));
            dd[2] = 0.0;
        } else {
            dd[1] = Math.atan2(_t8, _t10);
            dd[2] = Math.atan2(2.0 * Math.fma(sd[3], sd[4], sd[5] * sd[6]), Math.fma(-2.0, Math.fma(sd[3], sd[3], _t3), 1.0));
        }
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[5] * sd[5];
        double _t7 = 2.0 * Math.fma(sd[3], sd[4], sd[5] * sd[6]);
        double _t8 = 2.0 * Math.fma(sd[4], sd[6], -(sd[3] * sd[5]));
        double _t9 = Math.fma(-2.0, Math.fma(sd[4], sd[4], _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            dd[0] = 0.0;
            dd[1] = Math.atan2(2.0 * Math.fma(sd[3], sd[5], sd[4] * sd[6]), Math.fma(-2.0, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0));
        } else {
            dd[0] = Math.atan2(2.0 * Math.fma(sd[3], sd[6], -(sd[4] * sd[5])), Math.fma(-2.0, Math.fma(sd[3], sd[3], _t0), 1.0));
            dd[1] = Math.atan2(_t8, _t9);
        }
        dd[2] = Math.atan2(_t7, java.lang.Math.sqrt(_t11));
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[5] * sd[5];
        double _t7 = 2.0 * Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        double _t8 = 2.0 * Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        double _t9 = Math.fma(-2.0, Math.fma(sd[3], sd[3], _t1), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        dd[0] = Math.atan2(_t7, java.lang.Math.sqrt(_t11));
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            dd[1] = 0.0;
            dd[2] = Math.atan2(2.0 * Math.fma(sd[3], sd[4], sd[5] * sd[6]), Math.fma(-2.0, Math.fma(sd[4], sd[4], _t1), 1.0));
        } else {
            dd[1] = Math.atan2(2.0 * Math.fma(sd[4], sd[6], -(sd[3] * sd[5])), Math.fma(-2.0, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0));
            dd[2] = Math.atan2(_t8, _t9);
        }
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[5] * sd[5];
        double _t7 = 2.0 * Math.fma(sd[3], sd[4], sd[5] * sd[6]);
        double _t8 = 2.0 * Math.fma(sd[4], sd[6], -(sd[3] * sd[5]));
        double _t9 = Math.fma(-2.0, Math.fma(sd[4], sd[4], _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            dd[0] = 0.0;
            dd[2] = Math.atan2(2.0 * Math.fma(sd[5], sd[6], -(sd[3] * sd[4])), Math.fma(-2.0, Math.fma(sd[3], sd[3], _t0), 1.0));
        } else {
            dd[0] = Math.atan2(2.0 * Math.fma(sd[3], sd[6], sd[4] * sd[5]), Math.fma(-2.0, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0));
            dd[2] = Math.atan2(_t7, _t9);
        }
        dd[1] = Math.atan2(_t8, java.lang.Math.sqrt(_t11));
        return dest;
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
        if (COL_NARROW) return getRotation_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 0);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
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
        if (COL_NARROW) return makeRotationAxis_narrow(angle, axis);
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = axisX * _t1;
        dd[4] = axisY * _t1;
        dd[5] = axisZ * _t1;
        dd[6] = Math.cosFromSin(_t1, _t0);
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
        if (COL_NARROW) return makeRotationAxis_narrow(angle, axisX, axisY, axisZ);
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = axisX * _t1;
        dd[4] = axisY * _t1;
        dd[5] = axisZ * _t1;
        dd[6] = Math.cosFromSin(_t1, _t0);
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
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = _t1;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = Math.cosFromSin(_t1, _t0);
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
        double[] dd = this.data;
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
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = Math.fma(_t10, _t7, _t11 * _t5);
        dd[4] = Math.fma(_t11, _t7, -(_t10 * _t5));
        dd[5] = Math.fma(_t9, _t7, _t12 * _t5);
        dd[6] = Math.fma(_t12, _t7, -(_t9 * _t5));
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
        double[] dd = this.data;
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
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = Math.fma(_t10, _t7, -(_t11 * _t5));
        dd[4] = Math.fma(_t12, _t5, -(_t9 * _t7));
        dd[5] = Math.fma(_t10, _t5, _t11 * _t7);
        dd[6] = Math.fma(_t9, _t5, _t12 * _t7);
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
        if (COL_NARROW) return makeRotationY_narrow(angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        VEC_2.intoArray(dd, 0);
        dd[4] = _t1;
        dd[5] = 0.0;
        dd[6] = Math.cosFromSin(_t1, _t0);
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
        double[] dd = this.data;
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
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = Math.fma(_t10, _t7, _t11 * _t5);
        dd[4] = Math.fma(_t11, _t7, -(_t10 * _t5));
        dd[5] = Math.fma(_t12, _t5, -(_t9 * _t7));
        dd[6] = Math.fma(_t9, _t5, _t12 * _t7);
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
        double[] dd = this.data;
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
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = Math.fma(_t9, _t6, _t12 * _t5);
        dd[4] = Math.fma(_t10, _t6, _t11 * _t5);
        dd[5] = Math.fma(_t11, _t6, -(_t10 * _t5));
        dd[6] = Math.fma(_t12, _t6, -(_t9 * _t5));
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
        if (COL_NARROW) return makeRotationZ_narrow(angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        VEC_2.intoArray(dd, 0);
        dd[4] = 0.0;
        dd[5] = _t1;
        dd[6] = Math.cosFromSin(_t1, _t0);
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
        double[] dd = this.data;
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
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = Math.fma(_t10, _t7, -(_t11 * _t5));
        dd[4] = Math.fma(_t9, _t7, _t12 * _t5);
        dd[5] = Math.fma(_t10, _t5, _t11 * _t7);
        dd[6] = Math.fma(_t12, _t7, -(_t9 * _t5));
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
        double[] dd = this.data;
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
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = Math.fma(_t12, _t5, -(_t9 * _t8));
        dd[4] = Math.fma(_t10, _t8, _t11 * _t5);
        dd[5] = Math.fma(_t11, _t8, -(_t10 * _t5));
        dd[6] = Math.fma(_t9, _t5, _t12 * _t8);
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = Math.fma(rotationX, sd[6], rotationW * sd[3]) + Math.fma(rotationZ, sd[4], -(rotationY * sd[5]));
        double _buf1 = Math.fma(rotationX, sd[5], rotationW * sd[4]) + Math.fma(rotationY, sd[6], -(rotationZ * sd[3]));
        double _buf2 = Math.fma(rotationY, sd[3], rotationZ * sd[6]) + Math.fma(rotationW, sd[5], -(rotationX * sd[4]));
        dd[6] = Math.fma(rotationW, sd[6], -(rotationX * sd[3])) - Math.fma(rotationY, sd[4], rotationZ * sd[5]);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = Math.fma(rotationX, sd[6], rotationW * sd[3]) + Math.fma(rotationZ, sd[4], -(rotationY * sd[5]));
        double _buf1 = Math.fma(rotationX, sd[5], rotationW * sd[4]) + Math.fma(rotationY, sd[6], -(rotationZ * sd[3]));
        double _buf2 = Math.fma(rotationY, sd[3], rotationZ * sd[6]) + Math.fma(rotationW, sd[5], -(rotationX * sd[4]));
        dd[6] = Math.fma(rotationW, sd[6], -(rotationX * sd[3])) - Math.fma(rotationY, sd[4], rotationZ * sd[5]);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
        return rotateAxis(angle, axis.x(), axis.y(), axis.z(), dest);
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateAxis_s9b614aa2_1(dest, sd, dd, _t2, _t3, _t4, _t5, Math.fma(sd[3], _t5, sd[6] * _t2) + Math.fma(sd[4], _t3, -(sd[5] * _t4)), Math.fma(sd[4], _t5, sd[5] * _t2) + Math.fma(sd[6], _t4, -(sd[3] * _t3)), Math.fma(sd[3], _t4, sd[6] * _t3) + Math.fma(sd[5], _t5, -(sd[4] * _t2)));
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateAxis_s9b614aa2_1(DoubleRigid dest, double[] sd, double[] dd, double _t2, double _t3, double _t4, double _t5, double _buf0, double _buf1, double _buf2) {
        dd[6] = Math.fma(sd[6], _t5, -(sd[3] * _t2)) - Math.fma(sd[4], _t4, sd[5] * _t3);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = Math.fma(sd[3], _t2, sd[6] * _t1);
        double _buf1 = Math.fma(sd[4], _t2, sd[5] * _t1);
        dd[5] = Math.fma(sd[5], _t2, -(sd[4] * _t1));
        dd[6] = Math.fma(sd[6], _t2, -(sd[3] * _t1));
        dd[3] = _buf0;
        dd[4] = _buf1;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        double _t19 = Math.fma(_t10, _t8, _t11 * _t5);
        double _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        double _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        double _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateXYZ_s93f54fb6_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t21, sd[6] * _t19) + Math.fma(sd[4], _t20, -(sd[5] * _t22)), Math.fma(sd[4], _t21, sd[5] * _t19) + Math.fma(sd[6], _t22, -(sd[3] * _t20)));
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateXYZ_s93f54fb6_1(DoubleRigid dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = Math.fma(sd[3], _t22, sd[6] * _t20) + Math.fma(sd[5], _t21, -(sd[4] * _t19));
        dd[6] = Math.fma(sd[6], _t21, -(sd[3] * _t19)) - Math.fma(sd[4], _t22, sd[5] * _t20);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        double _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        double _t20 = Math.fma(_t10, _t5, _t11 * _t8);
        double _t21 = Math.fma(_t10, _t8, -(_t11 * _t5));
        double _t22 = Math.fma(_t12, _t5, -(_t9 * _t8));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateXZY_s33632662_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t19, sd[6] * _t21) + Math.fma(sd[4], _t20, -(sd[5] * _t22)), Math.fma(sd[4], _t19, sd[5] * _t21) + Math.fma(sd[6], _t22, -(sd[3] * _t20)));
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateXZY_s33632662_1(DoubleRigid dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = Math.fma(sd[3], _t22, sd[6] * _t20) + Math.fma(sd[5], _t19, -(sd[4] * _t21));
        dd[6] = Math.fma(sd[6], _t19, -(sd[3] * _t21)) - Math.fma(sd[4], _t22, sd[5] * _t20);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = Math.fma(sd[3], _t2, -(sd[5] * _t1));
        double _buf1 = Math.fma(sd[4], _t2, sd[6] * _t1);
        dd[5] = Math.fma(sd[3], _t1, sd[5] * _t2);
        dd[6] = Math.fma(sd[6], _t2, -(sd[4] * _t1));
        dd[3] = _buf0;
        dd[4] = _buf1;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        double _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        double _t20 = Math.fma(_t10, _t8, _t11 * _t5);
        double _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        double _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateYXZ_se78e1e3a_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t19, sd[6] * _t20) + Math.fma(sd[4], _t21, -(sd[5] * _t22)), Math.fma(sd[4], _t19, sd[5] * _t20) + Math.fma(sd[6], _t22, -(sd[3] * _t21)));
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateYXZ_se78e1e3a_1(DoubleRigid dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = Math.fma(sd[3], _t22, sd[6] * _t21) + Math.fma(sd[5], _t19, -(sd[4] * _t20));
        dd[6] = Math.fma(sd[6], _t19, -(sd[3] * _t20)) - Math.fma(sd[4], _t22, sd[5] * _t21);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        double _t19 = Math.fma(_t9, _t8, _t14 * _t5);
        double _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        double _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        double _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateYZX_s63c1344a_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t21, sd[6] * _t19) + Math.fma(sd[4], _t22, -(sd[5] * _t20)), Math.fma(sd[4], _t21, sd[5] * _t19) + Math.fma(sd[6], _t20, -(sd[3] * _t22)));
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateYZX_s63c1344a_1(DoubleRigid dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = Math.fma(sd[3], _t20, sd[6] * _t22) + Math.fma(sd[5], _t21, -(sd[4] * _t19));
        dd[6] = Math.fma(sd[6], _t21, -(sd[3] * _t19)) - Math.fma(sd[4], _t20, sd[5] * _t22);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = Math.fma(sd[3], _t2, sd[4] * _t1);
        dd[4] = Math.fma(sd[4], _t2, -(sd[3] * _t1));
        double _buf1 = Math.fma(sd[5], _t2, sd[6] * _t1);
        dd[6] = Math.fma(sd[6], _t2, -(sd[5] * _t1));
        dd[3] = _buf0;
        dd[5] = _buf1;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        double _t19 = Math.fma(_t10, _t5, _t11 * _t8);
        double _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        double _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        double _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateZXY_s439874aa_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t21, sd[6] * _t22) + Math.fma(sd[4], _t19, -(sd[5] * _t20)), Math.fma(sd[4], _t21, sd[5] * _t22) + Math.fma(sd[6], _t20, -(sd[3] * _t19)));
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateZXY_s439874aa_1(DoubleRigid dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = Math.fma(sd[3], _t20, sd[6] * _t19) + Math.fma(sd[5], _t21, -(sd[4] * _t22));
        dd[6] = Math.fma(sd[6], _t21, -(sd[3] * _t22)) - Math.fma(sd[4], _t20, sd[5] * _t19);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        double _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        double _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        double _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        double _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateZYX_s1e9fb416_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t19, sd[6] * _t21) + Math.fma(sd[4], _t22, -(sd[5] * _t20)), Math.fma(sd[4], _t19, sd[5] * _t21) + Math.fma(sd[6], _t20, -(sd[3] * _t22)));
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateZYX_s1e9fb416_1(DoubleRigid dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = Math.fma(sd[3], _t20, sd[6] * _t22) + Math.fma(sd[5], _t19, -(sd[4] * _t21));
        dd[6] = Math.fma(sd[6], _t19, -(sd[3] * _t21)) - Math.fma(sd[4], _t20, sd[5] * _t22);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
        if (COL_NARROW) return translate_narrow(translation, dest);
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[3], translationY, -(sd[4] * translationX));
        double _t10 = 2.0 * Math.fma(sd[5], translationX, -(sd[3] * translationZ));
        double _t11 = 2.0 * Math.fma(sd[4], translationZ, -(sd[5] * translationY));
        dd[0] = Math.fma(sd[4], _t9, Math.fma(-sd[5], _t10, Math.fma(sd[6], _t11, sd[0] + translationX)));
        dd[1] = Math.fma(sd[5], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, sd[1] + translationY)));
        dd[2] = Math.fma(sd[3], _t10, Math.fma(-sd[4], _t11, Math.fma(sd[6], _t9, sd[2] + translationZ)));
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        return dest;
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
        if (COL_NARROW) return translate_narrow(translationX, translationY, translationZ, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[3], translationY, -(sd[4] * translationX));
        double _t10 = 2.0 * Math.fma(sd[5], translationX, -(sd[3] * translationZ));
        double _t11 = 2.0 * Math.fma(sd[4], translationZ, -(sd[5] * translationY));
        dd[0] = Math.fma(sd[4], _t9, Math.fma(-sd[5], _t10, Math.fma(sd[6], _t11, sd[0] + translationX)));
        dd[1] = Math.fma(sd[5], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, sd[1] + translationY)));
        dd[2] = Math.fma(sd[3], _t10, Math.fma(-sd[4], _t11, Math.fma(sd[6], _t9, sd[2] + translationZ)));
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[3], vY, -(sd[4] * vX));
        double _t10 = 2.0 * Math.fma(sd[5], vX, -(sd[3] * vZ));
        double _t11 = 2.0 * Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = Math.fma(sd[4], _t9, Math.fma(-sd[5], _t10, Math.fma(sd[6], _t11, sd[0] + vX)));
        dd[1] = Math.fma(sd[5], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, sd[1] + vY)));
        dd[2] = Math.fma(sd[3], _t10, Math.fma(-sd[4], _t11, Math.fma(sd[6], _t9, sd[2] + vZ)));
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[3], vY, -(sd[4] * vX));
        double _t10 = 2.0 * Math.fma(sd[5], vX, -(sd[3] * vZ));
        double _t11 = 2.0 * Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = Math.fma(sd[4], _t9, Math.fma(-sd[5], _t10, Math.fma(sd[6], _t11, sd[0] + vX)));
        dd[1] = Math.fma(sd[5], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, sd[1] + vY)));
        dd[2] = Math.fma(sd[3], _t10, Math.fma(-sd[4], _t11, Math.fma(sd[6], _t9, sd[2] + vZ)));
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[3], vY, -(sd[4] * vX));
        double _t10 = 2.0 * Math.fma(sd[5], vX, -(sd[3] * vZ));
        double _t11 = 2.0 * Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = Math.fma(sd[4], _t9, Math.fma(-sd[5], _t10, Math.fma(sd[6], _t11, vX)));
        dd[1] = Math.fma(sd[5], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, vY)));
        dd[2] = Math.fma(sd[3], _t10, Math.fma(-sd[4], _t11, Math.fma(sd[6], _t9, vZ)));
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[3], vY, -(sd[4] * vX));
        double _t10 = 2.0 * Math.fma(sd[5], vX, -(sd[3] * vZ));
        double _t11 = 2.0 * Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = Math.fma(sd[4], _t9, Math.fma(-sd[5], _t10, Math.fma(sd[6], _t11, vX)));
        dd[1] = Math.fma(sd[5], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, vY)));
        dd[2] = Math.fma(sd[3], _t10, Math.fma(-sd[4], _t11, Math.fma(sd[6], _t9, vZ)));
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[3], vZ, -(sd[5] * vX));
        double _t10 = 2.0 * Math.fma(sd[4], vX, -(sd[3] * vY));
        double _t11 = 2.0 * Math.fma(sd[5], vY, -(sd[4] * vZ));
        dd[0] = Math.fma(sd[5], _t9, Math.fma(-sd[4], _t10, Math.fma(sd[6], _t11, vX)));
        dd[1] = Math.fma(sd[3], _t10, Math.fma(-sd[5], _t11, Math.fma(sd[6], _t9, vY)));
        dd[2] = Math.fma(sd[4], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, vZ)));
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[3], vZ, -(sd[5] * vX));
        double _t10 = 2.0 * Math.fma(sd[4], vX, -(sd[3] * vY));
        double _t11 = 2.0 * Math.fma(sd[5], vY, -(sd[4] * vZ));
        dd[0] = Math.fma(sd[5], _t9, Math.fma(-sd[4], _t10, Math.fma(sd[6], _t11, vX)));
        dd[1] = Math.fma(sd[3], _t10, Math.fma(-sd[5], _t11, Math.fma(sd[6], _t9, vY)));
        dd[2] = Math.fma(sd[4], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, vZ)));
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = p.z() - sd[2];
        double _t1 = p.x() - sd[0];
        double _t2 = p.y() - sd[1];
        double _t12 = 2.0 * Math.fma(sd[3], _t0, -(sd[5] * _t1));
        double _t13 = 2.0 * Math.fma(sd[4], _t1, -(sd[3] * _t2));
        double _t14 = 2.0 * Math.fma(sd[5], _t2, -(sd[4] * _t0));
        dd[0] = Math.fma(sd[5], _t12, Math.fma(-sd[4], _t13, Math.fma(sd[6], _t14, _t1)));
        dd[1] = Math.fma(sd[3], _t13, Math.fma(-sd[5], _t14, Math.fma(sd[6], _t12, _t2)));
        dd[2] = Math.fma(sd[4], _t14, Math.fma(-sd[3], _t12, Math.fma(sd[6], _t13, _t0)));
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        double _t12 = 2.0 * Math.fma(sd[3], _t0, -(sd[5] * _t1));
        double _t13 = 2.0 * Math.fma(sd[4], _t1, -(sd[3] * _t2));
        double _t14 = 2.0 * Math.fma(sd[5], _t2, -(sd[4] * _t0));
        dd[0] = Math.fma(sd[5], _t12, Math.fma(-sd[4], _t13, Math.fma(sd[6], _t14, _t1)));
        dd[1] = Math.fma(sd[3], _t13, Math.fma(-sd[5], _t14, Math.fma(sd[6], _t12, _t2)));
        dd[2] = Math.fma(sd[4], _t14, Math.fma(-sd[3], _t12, Math.fma(sd[6], _t13, _t0)));
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[3], vY, -(sd[4] * vX));
        double _t10 = 2.0 * Math.fma(sd[5], vX, -(sd[3] * vZ));
        double _t11 = 2.0 * Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = Math.fma(sd[4], _t9, Math.fma(-sd[5], _t10, Math.fma(sd[6], _t11, sd[0] + vX)));
        dd[1] = Math.fma(sd[5], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, sd[1] + vY)));
        dd[2] = Math.fma(sd[3], _t10, Math.fma(-sd[4], _t11, Math.fma(sd[6], _t9, sd[2] + vZ)));
        return dest;
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
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        double _t12 = 2.0 * Math.fma(sd[3], _t0, -(sd[5] * _t1));
        double _t13 = 2.0 * Math.fma(sd[4], _t1, -(sd[3] * _t2));
        double _t14 = 2.0 * Math.fma(sd[5], _t2, -(sd[4] * _t0));
        dd[0] = Math.fma(sd[5], _t12, Math.fma(-sd[4], _t13, Math.fma(sd[6], _t14, _t1)));
        dd[1] = Math.fma(sd[3], _t13, Math.fma(-sd[5], _t14, Math.fma(sd[6], _t12, _t2)));
        dd[2] = Math.fma(sd[4], _t14, Math.fma(-sd[3], _t12, Math.fma(sd[6], _t13, _t0)));
        return dest;
    }

    public double tX() { return data[0]; }
    public double tY() { return data[1]; }
    public double tZ() { return data[2]; }
    public double rX() { return data[3]; }
    public double rY() { return data[4]; }
    public double rZ() { return data[5]; }
    public double rW() { return data[6]; }

    @Override public String toString() {
        return "DoubleRigid(" + tX() + ", " + tY() + ", " + tZ() + ", " + rX() + ", " + rY() + ", " + rZ() + ", " + rW() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleRigidImpl)) return false;
        DoubleRigidImpl o = (DoubleRigidImpl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Double.isFinite(data[0])
            && Double.isFinite(data[1])
            && Double.isFinite(data[2])
            && Double.isFinite(data[3])
            && Double.isFinite(data[4])
            && Double.isFinite(data[5])
            && Double.isFinite(data[6]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1])
            || Double.isNaN(data[2])
            || Double.isNaN(data[3])
            || Double.isNaN(data[4])
            || Double.isNaN(data[5])
            || Double.isNaN(data[6]);
    }

    @Override public boolean equalsEpsilon(DoubleRigidR other, double epsilon) {
        return java.lang.Math.abs(data[0] - other.tX()) <= epsilon
            && java.lang.Math.abs(data[1] - other.tY()) <= epsilon
            && java.lang.Math.abs(data[2] - other.tZ()) <= epsilon
            && java.lang.Math.abs(data[3] - other.rX()) <= epsilon
            && java.lang.Math.abs(data[4] - other.rY()) <= epsilon
            && java.lang.Math.abs(data[5] - other.rZ()) <= epsilon
            && java.lang.Math.abs(data[6] - other.rW()) <= epsilon;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        dest[offset + 6] = this.data[6];
        return dest;
    }
    public @Mutated DoubleRigid load(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
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
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        dest[offset + 4] = (float) this.data[4];
        dest[offset + 5] = (float) this.data[5];
        dest[offset + 6] = (float) this.data[6];
        return dest;
    }
    public @Mutated DoubleRigid load(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
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

    private static final VectorSpecies<Double> COL_SPECIES = DoubleVector.SPECIES_256;
    private static final DoubleVector VEC_1 = DoubleVector.fromArray(COL_SPECIES, new double[]{0.0, 0.0, 0.0, 1.0}, 0);
    private static final DoubleVector VEC_2 = DoubleVector.fromArray(COL_SPECIES, new double[]{0.0, 0.0, 0.0, 0.0}, 0);
    private static final double[] DATA_0 = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};

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

    /** The column species is wider than this machine's vector unit: take the scalar twins. */
    private static final boolean COL_NARROW = COL_SPECIES.length() > DoubleVector.SPECIES_PREFERRED.length();

    private DoubleRigid setTranslation_narrow(Double3R t, DoubleRigid dest) {
        double tY = t.y();
        double tZ = t.z();
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = t.x();
        dd[1] = tY;
        dd[2] = tZ;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        return dest;
    }

    private DoubleRigid setTranslation_narrow(double tX, double tY, double tZ, DoubleRigid dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = tX;
        dd[1] = tY;
        dd[2] = tZ;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        return dest;
    }

    private DoubleDualQuat toDualQuat_narrow(DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = -sd[2];
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        dd[0] = _rd3;
        dd[1] = _rd4;
        dd[2] = _rd5;
        dd[3] = _rd6;
        dd[4] = 0.5 * Math.fma(_t0, _rd4, Math.fma(_rd0, _rd6, _rd1 * _rd5));
        dd[5] = 0.5 * Math.fma(_rd2, _rd3, Math.fma(_rd1, _rd6, -(_rd0 * _rd5)));
        dd[6] = 0.5 * Math.fma(_rd2, _rd6, Math.fma(_rd0, _rd4, -(_rd1 * _rd3)));
        dd[7] = 0.5 * Math.fma(_t0, _rd5, Math.fma(-_rd1, _rd4, -(_rd0 * _rd3)));
        return dest;
    }

    private DoubleTransform toTransform_narrow(DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return dest;
    }

    private DoubleRigid set_narrow(Double3R translation) {
        double translationY = translation.y();
        double translationZ = translation.z();
        double[] dd = this.data;
        dd[0] = translation.x();
        dd[1] = translationY;
        dd[2] = translationZ;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 1.0;
        return this;
    }

    private DoubleRigid set_narrow(double translationX, double translationY, double translationZ) {
        double[] dd = this.data;
        dd[0] = translationX;
        dd[1] = translationY;
        dd[2] = translationZ;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 1.0;
        return this;
    }

    private DoubleQuat getRotation_narrow(DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = sd[3];
        dd[1] = sd[4];
        dd[2] = sd[5];
        dd[3] = sd[6];
        return dest;
    }

    private DoubleRigid makeRotationY_narrow(double angle) {
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = _t1;
        dd[5] = 0.0;
        dd[6] = Math.cosFromSin(_t1, _t0);
        return this;
    }

    private DoubleRigid makeRotationZ_narrow(double angle) {
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = _t1;
        dd[6] = Math.cosFromSin(_t1, _t0);
        return this;
    }

    private DoubleRigid translate_narrow(Double3R translation, DoubleRigid dest) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[3], translationY, -(sd[4] * translationX));
        double _t10 = 2.0 * Math.fma(sd[5], translationX, -(sd[3] * translationZ));
        double _t11 = 2.0 * Math.fma(sd[4], translationZ, -(sd[5] * translationY));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(_rd1, _t9, Math.fma(-_rd2, _t10, Math.fma(_rd3, _t11, sd[0] + translationX)));
        dd[1] = Math.fma(_rd2, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, sd[1] + translationY)));
        dd[2] = Math.fma(_rd0, _t10, Math.fma(-_rd1, _t11, Math.fma(_rd3, _t9, sd[2] + translationZ)));
        dd[3] = _rd0;
        dd[4] = _rd1;
        dd[5] = _rd2;
        dd[6] = _rd3;
        return dest;
    }

    private DoubleRigid translate_narrow(double translationX, double translationY, double translationZ, DoubleRigid dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[3], translationY, -(sd[4] * translationX));
        double _t10 = 2.0 * Math.fma(sd[5], translationX, -(sd[3] * translationZ));
        double _t11 = 2.0 * Math.fma(sd[4], translationZ, -(sd[5] * translationY));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(_rd1, _t9, Math.fma(-_rd2, _t10, Math.fma(_rd3, _t11, sd[0] + translationX)));
        dd[1] = Math.fma(_rd2, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, sd[1] + translationY)));
        dd[2] = Math.fma(_rd0, _t10, Math.fma(-_rd1, _t11, Math.fma(_rd3, _t9, sd[2] + translationZ)));
        dd[3] = _rd0;
        dd[4] = _rd1;
        dd[5] = _rd2;
        dd[6] = _rd3;
        return dest;
    }

    private DoubleRigid makeRotationAxis_narrow(double angle, Double3R axis) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = axisX * _t1;
        dd[4] = axisY * _t1;
        dd[5] = axisZ * _t1;
        dd[6] = Math.cosFromSin(_t1, _t0);
        return this;
    }

    private DoubleRigid makeRotationAxis_narrow(double angle, double axisX, double axisY, double axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = axisX * _t1;
        dd[4] = axisY * _t1;
        dd[5] = axisZ * _t1;
        dd[6] = Math.cosFromSin(_t1, _t0);
        return this;
    }
}
