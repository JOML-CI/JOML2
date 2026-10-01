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
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link FloatRigid} backed by a {@code float[]} array, with Vector API
 * SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatRigidImpl implements FloatRigid {

    public float[] data;

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

    public FloatRigidImpl() {
        data = new float[7];
        data[6] = 1;
    }

    public FloatRigidImpl(float tX, float tY, float tZ, float rX, float rY, float rZ, float rW) {
        float[] dd = this.data = new float[7];
        dd[0] = tX;
        dd[1] = tY;
        dd[2] = tZ;
        dd[3] = rX;
        dd[4] = rY;
        dd[5] = rZ;
        dd[6] = rW;
    }

    public FloatRigidImpl(FloatRigidR src) {
        float[] dd = this.data = new float[7];
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
    public @Mutated FloatRigid makeFromAxisAngle(Float3R axis, float angle, Float3R translation) {
        return makeFromAxisAngle(axis.x(), axis.y(), axis.z(), angle, translation.x(), translation.y(), translation.z());
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
    @Mutated public FloatRigid makeFromAxisAngle(float axisX, float axisY, float axisZ, float angle, float translationX, float translationY, float translationZ) {
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        dd[0] = translationX;
        dd[1] = translationY;
        dd[2] = translationZ;
        dd[3] = axisX * _t1;
        dd[4] = axisY * _t1;
        dd[5] = axisZ * _t1;
        dd[6] = (float) Math.cosFromSin(_t1, _t0);
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
    public @Mutated FloatRigid makeTranslationRotation(Float3R translation, FloatQuatR rotation) {
        return makeTranslationRotation(translation.x(), translation.y(), translation.z(), rotation.x(), rotation.y(), rotation.z(), rotation.w());
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
    @Mutated public FloatRigid makeTranslationRotation(float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW) {
        float[] dd = this.data;
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
    public @Mutated FloatRigid set(FloatRigidR v) {
        return set(v.tX(), v.tY(), v.tZ(), v.rX(), v.rY(), v.rZ(), v.rW());
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
    @Mutated public FloatRigid set(float vTX, float vTY, float vTZ, float vRX, float vRY, float vRZ, float vRW) {
        float[] dd = this.data;
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
    public FloatRigid setRotation(FloatQuatR r, @Mutated FloatRigid dest) {
        return setRotation(r.x(), r.y(), r.z(), r.w(), dest);
    }


    /**
     * Set the rotation of this rigid transform to {@code r} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code r} must have unit length.
     *
     * @param r the new rotation
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid setRotation(FloatQuatR r, @Mutated DoubleRigid dest) {
        return setRotation(r.x(), r.y(), r.z(), r.w(), dest);
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
    public FloatRigid setRotation(float rX, float rY, float rZ, float rW, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleRigid setRotation(float rX, float rY, float rZ, float rW, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
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
    public FloatRigid setTranslation(Float3R t, @Mutated FloatRigid dest) {
        return setTranslation(t.x(), t.y(), t.z(), dest);
    }


    /**
     * Set the translation of this rigid transform to {@code t} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the translation vector
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid setTranslation(Float3R t, @Mutated DoubleRigid dest) {
        return setTranslation(t.x(), t.y(), t.z(), dest);
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
    public FloatRigid setTranslation(float tX, float tY, float tZ, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        dd[0] = tX;
        dd[1] = tY;
        dd[2] = tZ;
        FloatVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        return dest;
    }


    /**
     * Set the translation of this rigid transform to ({@code tX}, {@code tY}, {@code tZ}) and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param tX the {@code x} component of the vector {@code (tX, tY, tZ)}
     * @param tY the {@code y} component of the vector {@code (tX, tY, tZ)}
     * @param tZ the {@code z} component of the vector {@code (tX, tY, tZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid setTranslation(float tX, float tY, float tZ, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
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


    /**
     * Set this rigid transform to the rigid motion of the unit dual quaternion {@code dq} (an exact
     * conversion - both represent rotation plus translation).
     * <p>
     * Valid input: {@code dq} must be a unit dual quaternion.
     *
     * @param dq the dual quaternion to convert
     * @return this
     */
    public @Mutated FloatRigid makeFromDualQuat(FloatDualQuatR dq) {
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
    @Mutated public FloatRigid makeFromDualQuat(float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        float[] dd = this.data;
        dd[0] = 2.0f * (Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + Math.fma(dqRW, dqDX, -(dqRX * dqDW)));
        dd[1] = 2.0f * (Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + Math.fma(dqRW, dqDY, -(dqRY * dqDW)));
        dd[2] = 2.0f * (Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + Math.fma(dqRW, dqDZ, -(dqRZ * dqDW)));
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
    @Mutated public FloatRigid makeFromMatrix(Float3x3R m) {
        float[] dd = this.data;
        float[] mData = ((Float3x3Impl) m).data;
        return makeFromMatrix_s4398bb65_1(m, dd, mData, -mData[4], -mData[8], Math.fma(mData[5], mData[5], Math.fma(mData[3], mData[3], mData[4] * mData[4])));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatRigid makeFromMatrix_s4398bb65_1(Float3x3R m, float[] dd, float[] mData, float _t0, float _t1, float _ct0) {
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t15 = (1.0f / (float) Math.sqrt(_ct0));
        float _ct1 = Math.fma(mData[8], mData[8], Math.fma(mData[6], mData[6], mData[7] * mData[7]));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t16 = (1.0f / (float) Math.sqrt(_ct1));
        float _ct2 = Math.fma(mData[2], mData[2], Math.fma(mData[0], mData[0], mData[1] * mData[1]));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t17 = (1.0f / (float) Math.sqrt(_ct2));
        float _t20 = mData[7] * _t16;
        float _t23 = mData[5] * _t15;
        return makeFromMatrix_s4398bb65_2(dd, mData, _t0, _t1, _t15, _t16, mData[1] * _t17, mData[8] * _t16, _t20, mData[2] * _t17, _t23, mData[4] * _t15, mData[0] * _t17, Math.fma(mData[7], _t16, _t23), Math.fma(mData[5], _t15, -_t20));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatRigid makeFromMatrix_s4398bb65_2(float[] dd, float[] mData, float _t0, float _t1, float _t15, float _t16, float _t18, float _t19, float _t20, float _t21, float _t23, float _t24, float _t26, float _t31, float _t35) {
        float _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), mData[3] * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), mData[6] * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
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
        float _t63 = Math.fma(mData[4], _t15, Math.fma(mData[8], _t16, _t51));
        float _t65 = Math.fma(mData[4], _t15, Math.fma(_t1, _t16, _t52));
        float _t66 = Math.fma(mData[8], _t16, Math.fma(_t0, _t15, _t52));
        float _t67 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51));
        return makeFromMatrix_s4398bb65_3(dd, mData, _t15, _t16, _t19, _t24, _t31, _t35, _t47, Math.fma(mData[3], _t15, _t48), Math.fma(mData[6], _t16, _t49), Math.fma(mData[6], _t16, -_t49), Math.fma(-mData[3], _t15, _t48), _t63, 0.5f * (1.0f / (float) Math.sqrt(_t63)), _t65, _t66, _t67, 0.5f * (1.0f / (float) Math.sqrt(_t65)), 0.5f * (1.0f / (float) Math.sqrt(_t66)), 0.5f * (1.0f / (float) Math.sqrt(_t67)));
    }

    /** Piece 4 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatRigid makeFromMatrix_s4398bb65_3(float[] dd, float[] mData, float _t15, float _t16, float _t19, float _t24, float _t31, float _t35, float _t47, float _t54, float _t55, float _t56, float _t57, float _t63, float _sp0, float _t65, float _t66, float _t67, float _sp1, float _sp2, float _sp3) {
        if (Math.fma(mData[4], _t15, Math.fma(mData[8], _t16, _t47)) > 0.0f) {
            dd[3] = _sp0 * _t35;
            dd[4] = _sp0 * _t56;
            dd[5] = _sp0 * _t57;
            dd[6] = 0.5f * (float) Math.sqrt(_t63);
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dd[3] = 0.5f * (float) Math.sqrt(_t67);
                dd[4] = _sp3 * _t54;
                dd[5] = _sp3 * _t55;
                dd[6] = _sp3 * _t35;
            } else {
                if (_t24 > _t19) {
                    dd[3] = _sp1 * _t54;
                    dd[4] = 0.5f * (float) Math.sqrt(_t65);
                    dd[5] = _sp1 * _t31;
                    dd[6] = _sp1 * _t56;
                } else {
                    dd[3] = _sp2 * _t55;
                    dd[4] = _sp2 * _t31;
                    dd[5] = 0.5f * (float) Math.sqrt(_t66);
                    dd[6] = _sp2 * _t57;
                }
            }
        }
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        return this;
    }


    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatRigid makeFromMatrix_degenerate(Float3x3R m) {
        float[] dd = this.data;
        float[] mData = ((Float3x3Impl) m).data;
        float _t0 = unitScale(mData[3], mData[4], mData[5]);
        float _t1 = unitScale(mData[6], mData[7], mData[8]);
        float _t2 = unitScale(mData[0], mData[1], mData[2]);
        float _t12 = mData[5] * _t0;
        float _t13 = mData[3] * _t0;
        float _t14 = mData[4] * _t0;
        float _t15 = mData[8] * _t1;
        float _t16 = mData[6] * _t1;
        float _t17 = mData[7] * _t1;
        float _t18 = mData[2] * _t2;
        float _t19 = mData[0] * _t2;
        float _t20 = mData[1] * _t2;
        float _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
        float _t31 = (1.0f / (float) Math.sqrt(_t28));
        float _t32 = (1.0f / (float) Math.sqrt(_t27));
        float _t33 = _t30 * _t18;
        float _t34 = _t30 * _t19;
        float _t35 = _t30 * _t20;
        float _t36 = _t31 * _t17;
        float _t37 = _t31 * _t15;
        float _t38 = _t31 * _t16;
        float _t39 = _t32 * _t13;
        float _t40 = _t32 * _t12;
        float _t41 = _t32 * _t14;
        float _t72, _t75, _t87;
        if (Math.abs(_t33) < Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0f;
            _t87 = -_t34;
        } else {
            _t72 = 0.0f;
            _t75 = -_t35;
            _t87 = _t33;
        }
        float _t73, _t76, _t88;
        if (Math.abs(_t37) < Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0f;
            _t88 = -_t38;
        } else {
            _t73 = 0.0f;
            _t76 = -_t36;
            _t88 = _t37;
        }
        float _t74, _t77, _t89;
        if (Math.abs(_t40) < Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        float _t102 = _t99 * _t72;
        float _t103 = _t100 * _t73;
        float _t104 = _t101 * _t74;
        float _t105 = _t100 * _t76;
        float _t106 = _t99 * _t75;
        float _t107 = _t101 * _t77;
        float _t114 = _t100 * _t88;
        float _t115 = _t101 * _t89;
        float _t116 = _t99 * _t87;
        float _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = 0.0f;
                    _t167 = 1.0f;
                    _t170 = 0.0f;
                    _t166 = 0.0f;
                    _t168 = 0.0f;
                    _t171 = 1.0f;
                    _t169 = 0.0f;
                    _t172 = 0.0f;
                    _t173 = 1.0f;
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
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t182 = _t170 - _t166;
        float _t184 = _t170 + _t166;
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
        float _t199 = _t195 + _t165;
        float _t200 = _t196 + _t168;
        float _t201 = _t168 - _t196;
        float _t202 = _t195 - _t165;
        float _t206 = _t194 + _t167 + _t171;
        float _t207 = 1.0f + _t206;
        float _t208 = 1.0f + _t194 - _t167 - _t171;
        float _t209 = 1.0f + _t167 - _t194 - _t171;
        float _t210 = 1.0f + _t171 - _t194 - _t167;
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t208));
        if (_t206 > 0.0f) {
            dd[3] = _sp0 * _t182;
            dd[4] = _sp0 * _t201;
            dd[5] = _sp0 * _t202;
            dd[6] = 0.5f * (float) Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dd[3] = 0.5f * (float) Math.sqrt(_t208);
                dd[4] = _sp3 * _t199;
                dd[5] = _sp3 * _t200;
                dd[6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dd[3] = _sp1 * _t199;
                    dd[4] = 0.5f * (float) Math.sqrt(_t209);
                    dd[5] = _sp1 * _t184;
                    dd[6] = _sp1 * _t201;
                } else {
                    dd[3] = _sp2 * _t200;
                    dd[4] = _sp2 * _t184;
                    dd[5] = 0.5f * (float) Math.sqrt(_t210);
                    dd[6] = _sp2 * _t202;
                }
            }
        }
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
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
    @Mutated public FloatRigid makeFromMatrix(Float3x4R m) {
        float[] dd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        return makeFromMatrix_s7b6de20e_1(m, dd, mData, -mData[5], -mData[10], Math.fma(mData[9], mData[9], Math.fma(mData[1], mData[1], mData[5] * mData[5])));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatRigid makeFromMatrix_s7b6de20e_1(Float3x4R m, float[] dd, float[] mData, float _t0, float _t1, float _ct0) {
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t15 = (1.0f / (float) Math.sqrt(_ct0));
        float _ct1 = Math.fma(mData[10], mData[10], Math.fma(mData[2], mData[2], mData[6] * mData[6]));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t16 = (1.0f / (float) Math.sqrt(_ct1));
        float _ct2 = Math.fma(mData[8], mData[8], Math.fma(mData[0], mData[0], mData[4] * mData[4]));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t17 = (1.0f / (float) Math.sqrt(_ct2));
        float _t20 = mData[6] * _t16;
        float _t23 = mData[9] * _t15;
        return makeFromMatrix_s7b6de20e_2(dd, mData, _t0, _t1, _t15, _t16, mData[4] * _t17, mData[10] * _t16, _t20, mData[8] * _t17, _t23, mData[5] * _t15, mData[0] * _t17, Math.fma(mData[6], _t16, _t23), Math.fma(mData[9], _t15, -_t20));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatRigid makeFromMatrix_s7b6de20e_2(float[] dd, float[] mData, float _t0, float _t1, float _t15, float _t16, float _t18, float _t19, float _t20, float _t21, float _t23, float _t24, float _t26, float _t31, float _t35) {
        float _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), mData[1] * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), mData[2] * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
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
        float _t63 = Math.fma(mData[5], _t15, Math.fma(mData[10], _t16, _t51));
        float _t65 = Math.fma(mData[5], _t15, Math.fma(_t1, _t16, _t52));
        float _t66 = Math.fma(mData[10], _t16, Math.fma(_t0, _t15, _t52));
        float _t67 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51));
        return makeFromMatrix_s7b6de20e_3(dd, mData, _t15, _t16, _t19, _t24, _t31, _t35, _t47, Math.fma(mData[1], _t15, _t48), Math.fma(mData[2], _t16, _t49), Math.fma(mData[2], _t16, -_t49), Math.fma(-mData[1], _t15, _t48), _t63, 0.5f * (1.0f / (float) Math.sqrt(_t63)), _t65, _t66, _t67, 0.5f * (1.0f / (float) Math.sqrt(_t65)), 0.5f * (1.0f / (float) Math.sqrt(_t66)), 0.5f * (1.0f / (float) Math.sqrt(_t67)));
    }

    /** Piece 4 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatRigid makeFromMatrix_s7b6de20e_3(float[] dd, float[] mData, float _t15, float _t16, float _t19, float _t24, float _t31, float _t35, float _t47, float _t54, float _t55, float _t56, float _t57, float _t63, float _sp0, float _t65, float _t66, float _t67, float _sp1, float _sp2, float _sp3) {
        if (Math.fma(mData[5], _t15, Math.fma(mData[10], _t16, _t47)) > 0.0f) {
            dd[3] = _sp0 * _t35;
            dd[4] = _sp0 * _t56;
            dd[5] = _sp0 * _t57;
            dd[6] = 0.5f * (float) Math.sqrt(_t63);
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dd[3] = 0.5f * (float) Math.sqrt(_t67);
                dd[4] = _sp3 * _t54;
                dd[5] = _sp3 * _t55;
                dd[6] = _sp3 * _t35;
            } else {
                if (_t24 > _t19) {
                    dd[3] = _sp1 * _t54;
                    dd[4] = 0.5f * (float) Math.sqrt(_t65);
                    dd[5] = _sp1 * _t31;
                    dd[6] = _sp1 * _t56;
                } else {
                    dd[3] = _sp2 * _t55;
                    dd[4] = _sp2 * _t31;
                    dd[5] = 0.5f * (float) Math.sqrt(_t66);
                    dd[6] = _sp2 * _t57;
                }
            }
        }
        dd[0] = mData[3];
        dd[1] = mData[7];
        dd[2] = mData[11];
        return this;
    }


    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatRigid makeFromMatrix_degenerate(Float3x4R m) {
        float[] dd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        float _t0 = unitScale(mData[1], mData[5], mData[9]);
        float _t1 = unitScale(mData[2], mData[6], mData[10]);
        float _t2 = unitScale(mData[0], mData[4], mData[8]);
        float _t12 = mData[9] * _t0;
        float _t13 = mData[1] * _t0;
        float _t14 = mData[5] * _t0;
        float _t15 = mData[10] * _t1;
        float _t16 = mData[2] * _t1;
        float _t17 = mData[6] * _t1;
        float _t18 = mData[8] * _t2;
        float _t19 = mData[0] * _t2;
        float _t20 = mData[4] * _t2;
        float _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
        float _t31 = (1.0f / (float) Math.sqrt(_t28));
        float _t32 = (1.0f / (float) Math.sqrt(_t27));
        float _t33 = _t30 * _t18;
        float _t34 = _t30 * _t19;
        float _t35 = _t30 * _t20;
        float _t36 = _t31 * _t17;
        float _t37 = _t31 * _t15;
        float _t38 = _t31 * _t16;
        float _t39 = _t32 * _t13;
        float _t40 = _t32 * _t12;
        float _t41 = _t32 * _t14;
        float _t72, _t75, _t87;
        if (Math.abs(_t33) < Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0f;
            _t87 = -_t34;
        } else {
            _t72 = 0.0f;
            _t75 = -_t35;
            _t87 = _t33;
        }
        float _t73, _t76, _t88;
        if (Math.abs(_t37) < Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0f;
            _t88 = -_t38;
        } else {
            _t73 = 0.0f;
            _t76 = -_t36;
            _t88 = _t37;
        }
        float _t74, _t77, _t89;
        if (Math.abs(_t40) < Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        float _t102 = _t99 * _t72;
        float _t103 = _t100 * _t73;
        float _t104 = _t101 * _t74;
        float _t105 = _t100 * _t76;
        float _t106 = _t99 * _t75;
        float _t107 = _t101 * _t77;
        float _t114 = _t100 * _t88;
        float _t115 = _t101 * _t89;
        float _t116 = _t99 * _t87;
        float _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = 0.0f;
                    _t167 = 1.0f;
                    _t170 = 0.0f;
                    _t166 = 0.0f;
                    _t168 = 0.0f;
                    _t171 = 1.0f;
                    _t169 = 0.0f;
                    _t172 = 0.0f;
                    _t173 = 1.0f;
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
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t182 = _t170 - _t166;
        float _t184 = _t170 + _t166;
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
        float _t199 = _t195 + _t165;
        float _t200 = _t196 + _t168;
        float _t201 = _t168 - _t196;
        float _t202 = _t195 - _t165;
        float _t206 = _t194 + _t167 + _t171;
        float _t207 = 1.0f + _t206;
        float _t208 = 1.0f + _t194 - _t167 - _t171;
        float _t209 = 1.0f + _t167 - _t194 - _t171;
        float _t210 = 1.0f + _t171 - _t194 - _t167;
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t208));
        if (_t206 > 0.0f) {
            dd[3] = _sp0 * _t182;
            dd[4] = _sp0 * _t201;
            dd[5] = _sp0 * _t202;
            dd[6] = 0.5f * (float) Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dd[3] = 0.5f * (float) Math.sqrt(_t208);
                dd[4] = _sp3 * _t199;
                dd[5] = _sp3 * _t200;
                dd[6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dd[3] = _sp1 * _t199;
                    dd[4] = 0.5f * (float) Math.sqrt(_t209);
                    dd[5] = _sp1 * _t184;
                    dd[6] = _sp1 * _t201;
                } else {
                    dd[3] = _sp2 * _t200;
                    dd[4] = _sp2 * _t184;
                    dd[5] = 0.5f * (float) Math.sqrt(_t210);
                    dd[6] = _sp2 * _t202;
                }
            }
        }
        dd[0] = mData[3];
        dd[1] = mData[7];
        dd[2] = mData[11];
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
    @Mutated public FloatRigid makeFromMatrix(Float4x4R m) {
        float[] dd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        return makeFromMatrix_s40927f3d_1(m, dd, mData, -mData[5], -mData[10], Math.fma(mData[6], mData[6], Math.fma(mData[4], mData[4], mData[5] * mData[5])));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatRigid makeFromMatrix_s40927f3d_1(Float4x4R m, float[] dd, float[] mData, float _t0, float _t1, float _ct0) {
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t15 = (1.0f / (float) Math.sqrt(_ct0));
        float _ct1 = Math.fma(mData[10], mData[10], Math.fma(mData[8], mData[8], mData[9] * mData[9]));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t16 = (1.0f / (float) Math.sqrt(_ct1));
        float _ct2 = Math.fma(mData[2], mData[2], Math.fma(mData[0], mData[0], mData[1] * mData[1]));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t17 = (1.0f / (float) Math.sqrt(_ct2));
        float _t20 = mData[9] * _t16;
        float _t23 = mData[6] * _t15;
        return makeFromMatrix_s40927f3d_2(dd, mData, _t0, _t1, _t15, _t16, mData[1] * _t17, mData[10] * _t16, _t20, mData[2] * _t17, _t23, mData[5] * _t15, mData[0] * _t17, Math.fma(mData[9], _t16, _t23), Math.fma(mData[6], _t15, -_t20));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatRigid makeFromMatrix_s40927f3d_2(float[] dd, float[] mData, float _t0, float _t1, float _t15, float _t16, float _t18, float _t19, float _t20, float _t21, float _t23, float _t24, float _t26, float _t31, float _t35) {
        float _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), mData[4] * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), mData[8] * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
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
        float _t63 = Math.fma(mData[5], _t15, Math.fma(mData[10], _t16, _t51));
        float _t65 = Math.fma(mData[5], _t15, Math.fma(_t1, _t16, _t52));
        float _t66 = Math.fma(mData[10], _t16, Math.fma(_t0, _t15, _t52));
        float _t67 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51));
        return makeFromMatrix_s40927f3d_3(dd, mData, _t15, _t16, _t19, _t24, _t31, _t35, _t47, Math.fma(mData[4], _t15, _t48), Math.fma(mData[8], _t16, _t49), Math.fma(mData[8], _t16, -_t49), Math.fma(-mData[4], _t15, _t48), _t63, 0.5f * (1.0f / (float) Math.sqrt(_t63)), _t65, _t66, _t67, 0.5f * (1.0f / (float) Math.sqrt(_t65)), 0.5f * (1.0f / (float) Math.sqrt(_t66)), 0.5f * (1.0f / (float) Math.sqrt(_t67)));
    }

    /** Piece 4 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatRigid makeFromMatrix_s40927f3d_3(float[] dd, float[] mData, float _t15, float _t16, float _t19, float _t24, float _t31, float _t35, float _t47, float _t54, float _t55, float _t56, float _t57, float _t63, float _sp0, float _t65, float _t66, float _t67, float _sp1, float _sp2, float _sp3) {
        if (Math.fma(mData[5], _t15, Math.fma(mData[10], _t16, _t47)) > 0.0f) {
            dd[3] = _sp0 * _t35;
            dd[4] = _sp0 * _t56;
            dd[5] = _sp0 * _t57;
            dd[6] = 0.5f * (float) Math.sqrt(_t63);
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dd[3] = 0.5f * (float) Math.sqrt(_t67);
                dd[4] = _sp3 * _t54;
                dd[5] = _sp3 * _t55;
                dd[6] = _sp3 * _t35;
            } else {
                if (_t24 > _t19) {
                    dd[3] = _sp1 * _t54;
                    dd[4] = 0.5f * (float) Math.sqrt(_t65);
                    dd[5] = _sp1 * _t31;
                    dd[6] = _sp1 * _t56;
                } else {
                    dd[3] = _sp2 * _t55;
                    dd[4] = _sp2 * _t31;
                    dd[5] = 0.5f * (float) Math.sqrt(_t66);
                    dd[6] = _sp2 * _t57;
                }
            }
        }
        dd[0] = mData[12];
        dd[1] = mData[13];
        dd[2] = mData[14];
        return this;
    }


    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatRigid makeFromMatrix_degenerate(Float4x4R m) {
        float[] dd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        float _t0 = unitScale(mData[4], mData[5], mData[6]);
        float _t1 = unitScale(mData[8], mData[9], mData[10]);
        float _t2 = unitScale(mData[0], mData[1], mData[2]);
        float _t12 = mData[6] * _t0;
        float _t13 = mData[4] * _t0;
        float _t14 = mData[5] * _t0;
        float _t15 = mData[10] * _t1;
        float _t16 = mData[8] * _t1;
        float _t17 = mData[9] * _t1;
        float _t18 = mData[2] * _t2;
        float _t19 = mData[0] * _t2;
        float _t20 = mData[1] * _t2;
        float _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
        float _t31 = (1.0f / (float) Math.sqrt(_t28));
        float _t32 = (1.0f / (float) Math.sqrt(_t27));
        float _t33 = _t30 * _t18;
        float _t34 = _t30 * _t19;
        float _t35 = _t30 * _t20;
        float _t36 = _t31 * _t17;
        float _t37 = _t31 * _t15;
        float _t38 = _t31 * _t16;
        float _t39 = _t32 * _t13;
        float _t40 = _t32 * _t12;
        float _t41 = _t32 * _t14;
        float _t72, _t75, _t87;
        if (Math.abs(_t33) < Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0f;
            _t87 = -_t34;
        } else {
            _t72 = 0.0f;
            _t75 = -_t35;
            _t87 = _t33;
        }
        float _t73, _t76, _t88;
        if (Math.abs(_t37) < Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0f;
            _t88 = -_t38;
        } else {
            _t73 = 0.0f;
            _t76 = -_t36;
            _t88 = _t37;
        }
        float _t74, _t77, _t89;
        if (Math.abs(_t40) < Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        float _t102 = _t99 * _t72;
        float _t103 = _t100 * _t73;
        float _t104 = _t101 * _t74;
        float _t105 = _t100 * _t76;
        float _t106 = _t99 * _t75;
        float _t107 = _t101 * _t77;
        float _t114 = _t100 * _t88;
        float _t115 = _t101 * _t89;
        float _t116 = _t99 * _t87;
        float _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = 0.0f;
                    _t167 = 1.0f;
                    _t170 = 0.0f;
                    _t166 = 0.0f;
                    _t168 = 0.0f;
                    _t171 = 1.0f;
                    _t169 = 0.0f;
                    _t172 = 0.0f;
                    _t173 = 1.0f;
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
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t182 = _t170 - _t166;
        float _t184 = _t170 + _t166;
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
        float _t199 = _t195 + _t165;
        float _t200 = _t196 + _t168;
        float _t201 = _t168 - _t196;
        float _t202 = _t195 - _t165;
        float _t206 = _t194 + _t167 + _t171;
        float _t207 = 1.0f + _t206;
        float _t208 = 1.0f + _t194 - _t167 - _t171;
        float _t209 = 1.0f + _t167 - _t194 - _t171;
        float _t210 = 1.0f + _t171 - _t194 - _t167;
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t208));
        if (_t206 > 0.0f) {
            dd[3] = _sp0 * _t182;
            dd[4] = _sp0 * _t201;
            dd[5] = _sp0 * _t202;
            dd[6] = 0.5f * (float) Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dd[3] = 0.5f * (float) Math.sqrt(_t208);
                dd[4] = _sp3 * _t199;
                dd[5] = _sp3 * _t200;
                dd[6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dd[3] = _sp1 * _t199;
                    dd[4] = 0.5f * (float) Math.sqrt(_t209);
                    dd[5] = _sp1 * _t184;
                    dd[6] = _sp1 * _t201;
                } else {
                    dd[3] = _sp2 * _t200;
                    dd[4] = _sp2 * _t184;
                    dd[5] = 0.5f * (float) Math.sqrt(_t210);
                    dd[6] = _sp2 * _t202;
                }
            }
        }
        dd[0] = mData[12];
        dd[1] = mData[13];
        dd[2] = mData[14];
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
    public @Mutated FloatRigid makeFromTransform(FloatTransformR t) {
        return makeFromTransform(t.tX(), t.tY(), t.tZ(), t.rX(), t.rY(), t.rZ(), t.rW(), t.sX(), t.sY(), t.sZ());
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
    @Mutated public FloatRigid makeFromTransform(float tTX, float tTY, float tTZ, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        float[] dd = this.data;
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
     * Convert this rigid transform to {@code double} precision and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid toDouble(@Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
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
    public FloatDualQuat toDualQuat(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -sd[2];
        var _vcp0 = FloatVector.fromArray(COL_SPECIES, sd, 3);
        float _buf0 = 0.5f * Math.fma(_t0, sd[4], Math.fma(sd[0], sd[6], sd[1] * sd[5]));
        float _buf1 = 0.5f * Math.fma(sd[2], sd[3], Math.fma(sd[1], sd[6], -(sd[0] * sd[5])));
        dd[6] = 0.5f * Math.fma(sd[2], sd[6], Math.fma(sd[0], sd[4], -(sd[1] * sd[3])));
        dd[7] = 0.5f * Math.fma(_t0, sd[5], Math.fma(-sd[1], sd[4], -(sd[0] * sd[3])));
        _vcp0.intoArray(dd, 0);
        dd[4] = _buf0;
        dd[5] = _buf1;
        return dest;
    }


    /**
     * Convert this rigid transform to a unit dual quaternion encoding the same rigid motion (an
     * exact conversion) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat toDualQuat(@Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = -sd[2];
        float _buf0 = sd[3];
        float _buf1 = sd[4];
        float _buf2 = sd[5];
        float _buf3 = sd[6];
        float _buf4 = 0.5f * Math.fma(_t0, sd[4], Math.fma(sd[0], sd[6], sd[1] * sd[5]));
        float _buf5 = 0.5f * Math.fma(sd[2], sd[3], Math.fma(sd[1], sd[6], -(sd[0] * sd[5])));
        dd[6] = 0.5f * Math.fma(sd[2], sd[6], Math.fma(sd[0], sd[4], -(sd[1] * sd[3])));
        dd[7] = 0.5f * Math.fma(_t0, sd[5], Math.fma(-sd[1], sd[4], -(sd[0] * sd[3])));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
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
    public Float4x4 toMatrix(@Mutated Float4x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x4Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        float _buf0 = Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t0), 1.0f);
        float _buf1 = 2.0f * Math.fma(sd[3], sd[4], _t1);
        dd[2] = 2.0f * Math.fma(sd[3], sd[5], -_t2);
        dd[3] = 0.0f;
        float _buf2 = 2.0f * Math.fma(sd[3], sd[4], -_t1);
        float _buf3 = Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t0), 1.0f);
        dd[6] = 2.0f * Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        dd[7] = 0.0f;
        float _buf4 = 2.0f * Math.fma(sd[3], sd[5], _t2);
        dd[9] = 2.0f * Math.fma(sd[4], sd[5], -(sd[3] * sd[6]));
        dd[10] = Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        dd[11] = 0.0f;
        dd[12] = sd[0];
        dd[13] = sd[1];
        return toMatrix_s6357fbbf_1(dest, sd, dd, _buf0, _buf1, _buf2, _buf3, _buf4);
    }

    /** Piece 2 of {@code toMatrix}, split to fit the inline budget; reached only through it. */
    private Float4x4 toMatrix_s6357fbbf_1(Float4x4 dest, float[] sd, float[] dd, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        dd[14] = sd[2];
        dd[15] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[4] = _buf2;
        dd[5] = _buf3;
        dd[8] = _buf4;
        ((Float4x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Compute the matrix representation of this rigid transform and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4x4 toMatrix(@Mutated Double4x4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        float _buf0 = Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t0), 1.0f);
        float _buf1 = 2.0f * Math.fma(sd[3], sd[4], _t1);
        dd[2] = 2.0f * Math.fma(sd[3], sd[5], -_t2);
        dd[3] = 0.0f;
        float _buf2 = 2.0f * Math.fma(sd[3], sd[4], -_t1);
        float _buf3 = Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t0), 1.0f);
        dd[6] = 2.0f * Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        dd[7] = 0.0f;
        float _buf4 = 2.0f * Math.fma(sd[3], sd[5], _t2);
        dd[9] = 2.0f * Math.fma(sd[4], sd[5], -(sd[3] * sd[6]));
        dd[10] = Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        dd[11] = 0.0f;
        dd[12] = sd[0];
        dd[13] = sd[1];
        return toMatrix_s9c292502_1(dest, sd, dd, _buf0, _buf1, _buf2, _buf3, _buf4);
    }

    /** Piece 2 of {@code toMatrix}, split to fit the inline budget; reached only through it. */
    private Double4x4 toMatrix_s9c292502_1(Double4x4 dest, float[] sd, double[] dd, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4) {
        dd[14] = sd[2];
        dd[15] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[4] = _buf2;
        dd[5] = _buf3;
        dd[8] = _buf4;
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
    public Float3x3 toMatrix3x3(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        dd[0] = Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t0), 1.0f);
        float _buf0 = 2.0f * Math.fma(sd[3], sd[4], _t1);
        float _buf1 = 2.0f * Math.fma(sd[3], sd[5], -_t2);
        dd[3] = 2.0f * Math.fma(sd[3], sd[4], -_t1);
        float _buf2 = Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t0), 1.0f);
        dd[5] = 2.0f * Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        dd[6] = 2.0f * Math.fma(sd[3], sd[5], _t2);
        dd[7] = 2.0f * Math.fma(sd[4], sd[5], -(sd[3] * sd[6]));
        dd[8] = Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        dd[1] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Compute the 3x3 matrix representation of the rotation of this rigid transform (the
     * translation is dropped) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 toMatrix3x3(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        dd[0] = Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t0), 1.0f);
        float _buf0 = 2.0f * Math.fma(sd[3], sd[4], _t1);
        float _buf1 = 2.0f * Math.fma(sd[3], sd[5], -_t2);
        dd[3] = 2.0f * Math.fma(sd[3], sd[4], -_t1);
        float _buf2 = Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t0), 1.0f);
        dd[5] = 2.0f * Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        dd[6] = 2.0f * Math.fma(sd[3], sd[5], _t2);
        dd[7] = 2.0f * Math.fma(sd[4], sd[5], -(sd[3] * sd[6]));
        dd[8] = Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        dd[1] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
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
    public Float3x4 toMatrix3x4(@Mutated Float3x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x4Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        float _buf0 = Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t0), 1.0f);
        float _buf1 = 2.0f * Math.fma(sd[3], sd[4], -_t1);
        float _buf2 = 2.0f * Math.fma(sd[3], sd[5], _t2);
        float _buf3 = sd[0];
        float _buf4 = 2.0f * Math.fma(sd[3], sd[4], _t1);
        float _buf5 = Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t0), 1.0f);
        float _buf6 = 2.0f * Math.fma(sd[4], sd[5], -(sd[3] * sd[6]));
        dd[7] = sd[1];
        dd[8] = 2.0f * Math.fma(sd[3], sd[5], -_t2);
        dd[9] = 2.0f * Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        dd[10] = Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        dd[11] = sd[2];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        ((Float3x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }


    /**
     * Compute the 3x4 matrix representation of this rigid transform (the omitted last row is
     * implicitly {@code 0, 0, 0, 1}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x4 toMatrix3x4(@Mutated Double3x4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        float _buf0 = Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t0), 1.0f);
        float _buf1 = 2.0f * Math.fma(sd[3], sd[4], -_t1);
        float _buf2 = 2.0f * Math.fma(sd[3], sd[5], _t2);
        float _buf3 = sd[0];
        float _buf4 = 2.0f * Math.fma(sd[3], sd[4], _t1);
        float _buf5 = Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t0), 1.0f);
        float _buf6 = 2.0f * Math.fma(sd[4], sd[5], -(sd[3] * sd[6]));
        dd[7] = sd[1];
        dd[8] = 2.0f * Math.fma(sd[3], sd[5], -_t2);
        dd[9] = 2.0f * Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        dd[10] = Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        dd[11] = sd[2];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
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
    public FloatTransform toTransform(@Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
        return dest;
    }


    /**
     * Widen this rigid transform to a TRS transform (same translation and rotation, scale = 1) and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform toTransform(@Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
        return dest;
    }


    /**
     * Set this rigid transform to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public FloatRigid makeIdentity() {
        float[] dd = this.data;
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
    public @Mutated FloatRigid set(FloatQuatR rotation) {
        return set(rotation.x(), rotation.y(), rotation.z(), rotation.w());
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
    @Mutated public FloatRigid set(float rotationX, float rotationY, float rotationZ, float rotationW) {
        float[] dd = this.data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
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
    public @Mutated FloatRigid set(Float3R translation) {
        return set(translation.x(), translation.y(), translation.z());
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
    @Mutated public FloatRigid set(float translationX, float translationY, float translationZ) {
        float[] dd = this.data;
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
    public FloatRigid lerp(FloatRigidR other, float t, @Mutated FloatRigid dest) {
        return lerp(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), t, dest);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length; the rotation of
     * {@code other} must have unit length.
     *
     * @param other the rigid transform to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid lerp(FloatRigidR other, float t, @Mutated DoubleRigid dest) {
        return lerp(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), t, dest);
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
    public FloatRigid lerp(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float t, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t0 = 1.0f - t;
        float _t12 = Math.fma(otherRW, sd[6], Math.fma(otherRZ, sd[5], Math.fma(otherRX, sd[3], otherRY * sd[4])));
        float _t16 = (float) Math.acos(Math.min(1.0f, Math.abs(_t12)));
        float _t17 = (float) Math.sin(_t16);
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
        return lerp_s3c625002_1(otherTX, otherTY, otherTZ, t, dest, sd, dd, _t0, _t17, 1.0f / _t17, (float) Math.sin(t * _t16), _t21, _t22, _t23, _t24, (float) Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private FloatRigid lerp_s3c625002_1(float otherTX, float otherTY, float otherTZ, float t, FloatRigid dest, float[] sd, float[] dd, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
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
        float _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        float _t50 = (1.0f / (float) Math.sqrt(_t49));
        if (_t49 != 0.0f) {
            dd[3] = _t50 * _t44;
            dd[4] = _t50 * _t45;
            dd[5] = _t50 * _t43;
            dd[6] = _t50 * _t42;
        } else {
            dd[3] = 0.0f;
            dd[4] = 0.0f;
            dd[5] = 0.0f;
            dd[6] = 0.0f;
        }
        return lerp_s3c625002_2(otherTX, otherTY, otherTZ, t, dest, sd, dd);
    }

    /** Piece 3 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private FloatRigid lerp_s3c625002_2(float otherTX, float otherTY, float otherTZ, float t, FloatRigid dest, float[] sd, float[] dd) {
        dd[0] = Math.fma(t, otherTX - sd[0], sd[0]);
        dd[1] = Math.fma(t, otherTY - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherTZ - sd[2], sd[2]);
        return dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleRigid lerp(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float t, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t0 = 1.0f - t;
        float _t12 = Math.fma(otherRW, sd[6], Math.fma(otherRZ, sd[5], Math.fma(otherRX, sd[3], otherRY * sd[4])));
        float _t16 = (float) Math.acos(Math.min(1.0f, Math.abs(_t12)));
        float _t17 = (float) Math.sin(_t16);
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
        return lerp_sb5a433c7_1(otherTX, otherTY, otherTZ, t, dest, sd, dd, _t0, _t17, 1.0f / _t17, (float) Math.sin(t * _t16), _t21, _t22, _t23, _t24, (float) Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleRigid lerp_sb5a433c7_1(float otherTX, float otherTY, float otherTZ, float t, DoubleRigid dest, float[] sd, double[] dd, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
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
        float _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        float _t50 = (1.0f / (float) Math.sqrt(_t49));
        if (_t49 != 0.0f) {
            dd[3] = _t50 * _t44;
            dd[4] = _t50 * _t45;
            dd[5] = _t50 * _t43;
            dd[6] = _t50 * _t42;
        } else {
            dd[3] = 0.0f;
            dd[4] = 0.0f;
            dd[5] = 0.0f;
            dd[6] = 0.0f;
        }
        return lerp_sb5a433c7_2(otherTX, otherTY, otherTZ, t, dest, sd, dd);
    }

    /** Piece 3 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleRigid lerp_sb5a433c7_2(float otherTX, float otherTY, float otherTZ, float t, DoubleRigid dest, float[] sd, double[] dd) {
        dd[0] = Math.fma(t, otherTX - sd[0], sd[0]);
        dd[1] = Math.fma(t, otherTY - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherTZ - sd[2], sd[2]);
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
    public FloatRigid mul(FloatRigidR other, @Mutated FloatRigid dest) {
        return mul(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), dest);
    }


    /**
     * Multiply this rigid transform by {@code other} and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the operand, then the new rigid
     * transform will be {@code M * R}. So when transforming a vector {@code v} with the new rigid
     * transform by using {@code M * R * v}, the transformation of the operand will be applied
     * first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length; the rotation of
     * {@code other} must have unit length.
     *
     * @param other the right operand
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid mul(FloatRigidR other, @Mutated DoubleRigid dest) {
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
    public FloatRigid mul(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t9 = 2.0f * Math.fma(otherTY, sd[3], -(otherTX * sd[4]));
        float _t10 = 2.0f * Math.fma(otherTX, sd[5], -(otherTZ * sd[3]));
        float _t11 = 2.0f * Math.fma(otherTZ, sd[4], -(otherTY * sd[5]));
        dd[0] = Math.fma(sd[4], _t9, Math.fma(-sd[5], _t10, Math.fma(sd[6], _t11, sd[0] + otherTX)));
        dd[1] = Math.fma(sd[5], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, sd[1] + otherTY)));
        dd[2] = Math.fma(sd[3], _t10, Math.fma(-sd[4], _t11, Math.fma(sd[6], _t9, sd[2] + otherTZ)));
        return mul_s3a53318b_1(otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, Math.fma(otherRX, sd[6], otherRW * sd[3]) + Math.fma(otherRZ, sd[4], -(otherRY * sd[5])), Math.fma(otherRX, sd[5], otherRW * sd[4]) + Math.fma(otherRY, sd[6], -(otherRZ * sd[3])));
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private FloatRigid mul_s3a53318b_1(float otherRX, float otherRY, float otherRZ, float otherRW, FloatRigid dest, float[] sd, float[] dd, float _buf0, float _buf1) {
        float _buf2 = Math.fma(otherRY, sd[3], otherRZ * sd[6]) + Math.fma(otherRW, sd[5], -(otherRX * sd[4]));
        dd[6] = Math.fma(otherRW, sd[6], -(otherRX * sd[3])) - Math.fma(otherRY, sd[4], otherRZ * sd[5]);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleRigid mul(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t9 = 2.0f * Math.fma(otherTY, sd[3], -(otherTX * sd[4]));
        float _t10 = 2.0f * Math.fma(otherTX, sd[5], -(otherTZ * sd[3]));
        float _t11 = 2.0f * Math.fma(otherTZ, sd[4], -(otherTY * sd[5]));
        dd[0] = Math.fma(sd[4], _t9, Math.fma(-sd[5], _t10, Math.fma(sd[6], _t11, sd[0] + otherTX)));
        dd[1] = Math.fma(sd[5], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, sd[1] + otherTY)));
        dd[2] = Math.fma(sd[3], _t10, Math.fma(-sd[4], _t11, Math.fma(sd[6], _t9, sd[2] + otherTZ)));
        return mul_s413cf3cc_1(otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, Math.fma(otherRX, sd[6], otherRW * sd[3]) + Math.fma(otherRZ, sd[4], -(otherRY * sd[5])), Math.fma(otherRX, sd[5], otherRW * sd[4]) + Math.fma(otherRY, sd[6], -(otherRZ * sd[3])));
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private DoubleRigid mul_s413cf3cc_1(float otherRX, float otherRY, float otherRZ, float otherRW, DoubleRigid dest, float[] sd, double[] dd, float _buf0, float _buf1) {
        float _buf2 = Math.fma(otherRY, sd[3], otherRZ * sd[6]) + Math.fma(otherRW, sd[5], -(otherRX * sd[4]));
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
    public FloatRigid preMul(FloatRigidR other, @Mutated FloatRigid dest) {
        return preMul(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), dest);
    }


    /**
     * Pre-multiply {@code other} onto this rigid transform and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the operand, then the new rigid
     * transform will be {@code R * M}. So when transforming a vector {@code v} with the new rigid
     * transform by using {@code R * M * v}, the transformation of the operand will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length; the rotation of
     * {@code other} must have unit length.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid preMul(FloatRigidR other, @Mutated DoubleRigid dest) {
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
    public FloatRigid preMul(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t9 = 2.0f * Math.fma(otherRX, sd[1], -(otherRY * sd[0]));
        float _t10 = 2.0f * Math.fma(otherRZ, sd[0], -(otherRX * sd[2]));
        float _t11 = 2.0f * Math.fma(otherRY, sd[2], -(otherRZ * sd[1]));
        dd[0] = Math.fma(otherRY, _t9, Math.fma(-otherRZ, _t10, Math.fma(otherRW, _t11, otherTX + sd[0])));
        dd[1] = Math.fma(otherRZ, _t11, Math.fma(-otherRX, _t9, Math.fma(otherRW, _t10, otherTY + sd[1])));
        dd[2] = Math.fma(otherRX, _t10, Math.fma(-otherRY, _t11, Math.fma(otherRW, _t9, otherTZ + sd[2])));
        return preMul_sa1616d4e_1(otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, Math.fma(otherRX, sd[6], otherRW * sd[3]) + Math.fma(otherRY, sd[5], -(otherRZ * sd[4])), Math.fma(otherRY, sd[6], otherRZ * sd[3]) + Math.fma(otherRW, sd[4], -(otherRX * sd[5])), Math.fma(otherRX, sd[4], otherRW * sd[5]) + Math.fma(otherRZ, sd[6], -(otherRY * sd[3])));
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private FloatRigid preMul_sa1616d4e_1(float otherRX, float otherRY, float otherRZ, float otherRW, FloatRigid dest, float[] sd, float[] dd, float _buf0, float _buf1, float _buf2) {
        dd[6] = Math.fma(otherRW, sd[6], -(otherRX * sd[3])) - Math.fma(otherRY, sd[4], otherRZ * sd[5]);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleRigid preMul(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t9 = 2.0f * Math.fma(otherRX, sd[1], -(otherRY * sd[0]));
        float _t10 = 2.0f * Math.fma(otherRZ, sd[0], -(otherRX * sd[2]));
        float _t11 = 2.0f * Math.fma(otherRY, sd[2], -(otherRZ * sd[1]));
        dd[0] = Math.fma(otherRY, _t9, Math.fma(-otherRZ, _t10, Math.fma(otherRW, _t11, otherTX + sd[0])));
        dd[1] = Math.fma(otherRZ, _t11, Math.fma(-otherRX, _t9, Math.fma(otherRW, _t10, otherTY + sd[1])));
        dd[2] = Math.fma(otherRX, _t10, Math.fma(-otherRY, _t11, Math.fma(otherRW, _t9, otherTZ + sd[2])));
        return preMul_s8c3f2013_1(otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, Math.fma(otherRX, sd[6], otherRW * sd[3]) + Math.fma(otherRY, sd[5], -(otherRZ * sd[4])), Math.fma(otherRY, sd[6], otherRZ * sd[3]) + Math.fma(otherRW, sd[4], -(otherRX * sd[5])), Math.fma(otherRX, sd[4], otherRW * sd[5]) + Math.fma(otherRZ, sd[6], -(otherRY * sd[3])));
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private DoubleRigid preMul_s8c3f2013_1(float otherRX, float otherRY, float otherRZ, float otherRW, DoubleRigid dest, float[] sd, double[] dd, float _buf0, float _buf1, float _buf2) {
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
    public FloatRigid difference(FloatRigidR other, @Mutated FloatRigid dest) {
        return difference(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), dest);
    }


    /**
     * Compute the difference between this rigid transform and {@code other}, i.e. the rigid
     * transformation {@code D} with {@code this * D = other}, that is {@code D = this^-1 * other}
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param other the target rigid transform, reached by composing this rigid transform with the
     *        result
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid difference(FloatRigidR other, @Mutated DoubleRigid dest) {
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
    public FloatRigid difference(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t18 = 2.0f * Math.fma(otherTZ, sd[3], -(otherTX * sd[5]));
        float _t19 = 2.0f * Math.fma(otherTY, sd[5], -(otherTZ * sd[4]));
        float _t20 = 2.0f * Math.fma(otherTX, sd[4], -(otherTY * sd[3]));
        float _t21 = 2.0f * Math.fma(sd[0], sd[5], -(sd[2] * sd[3]));
        float _t22 = 2.0f * Math.fma(sd[1], sd[3], -(sd[0] * sd[4]));
        float _t23 = 2.0f * Math.fma(sd[2], sd[4], -(sd[1] * sd[5]));
        dd[0] = Math.fma(sd[5], _t18, otherTX) + Math.fma(sd[6], _t19, -(sd[4] * _t20)) + (Math.fma(sd[5], _t21, -(sd[4] * _t22)) + Math.fma(sd[6], _t23, -sd[0]));
        return difference_sad2ccb00_1(otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private FloatRigid difference_sad2ccb00_1(float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, FloatRigid dest, float[] sd, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        dd[1] = Math.fma(sd[3], _t20, otherTY) + Math.fma(sd[6], _t18, -(sd[5] * _t19)) + (Math.fma(sd[3], _t22, -(sd[5] * _t23)) + Math.fma(sd[6], _t21, -sd[1]));
        dd[2] = Math.fma(sd[4], _t19, otherTZ) + Math.fma(sd[6], _t20, -(sd[3] * _t18)) + (Math.fma(sd[4], _t23, -(sd[3] * _t21)) + Math.fma(sd[6], _t22, -sd[2]));
        float _buf0 = Math.fma(otherRX, sd[6], -(otherRW * sd[3])) + Math.fma(otherRY, sd[5], -(otherRZ * sd[4]));
        float _buf1 = Math.fma(otherRY, sd[6], otherRZ * sd[3]) + Math.fma(-otherRX, sd[5], -(otherRW * sd[4]));
        float _buf2 = Math.fma(otherRX, sd[4], -(otherRW * sd[5])) + Math.fma(otherRZ, sd[6], -(otherRY * sd[3]));
        dd[6] = Math.fma(otherRX, sd[3], otherRW * sd[6]) - Math.fma(-otherRZ, sd[5], -(otherRY * sd[4]));
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }


    /**
     * Compute the difference between this rigid transform and ({@code otherTX}, {@code otherTY},
     * {@code otherTZ}, {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}), i.e.
     * the rigid transformation {@code D} with
     * {@code this * D = (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}, that is
     * {@code D = this^-1 * (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)} and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleRigid difference(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t18 = 2.0f * Math.fma(otherTZ, sd[3], -(otherTX * sd[5]));
        float _t19 = 2.0f * Math.fma(otherTY, sd[5], -(otherTZ * sd[4]));
        float _t20 = 2.0f * Math.fma(otherTX, sd[4], -(otherTY * sd[3]));
        float _t21 = 2.0f * Math.fma(sd[0], sd[5], -(sd[2] * sd[3]));
        float _t22 = 2.0f * Math.fma(sd[1], sd[3], -(sd[0] * sd[4]));
        float _t23 = 2.0f * Math.fma(sd[2], sd[4], -(sd[1] * sd[5]));
        dd[0] = Math.fma(sd[5], _t18, otherTX) + Math.fma(sd[6], _t19, -(sd[4] * _t20)) + (Math.fma(sd[5], _t21, -(sd[4] * _t22)) + Math.fma(sd[6], _t23, -sd[0]));
        return difference_sc6961c19_1(otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleRigid difference_sc6961c19_1(float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, DoubleRigid dest, float[] sd, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        dd[1] = Math.fma(sd[3], _t20, otherTY) + Math.fma(sd[6], _t18, -(sd[5] * _t19)) + (Math.fma(sd[3], _t22, -(sd[5] * _t23)) + Math.fma(sd[6], _t21, -sd[1]));
        dd[2] = Math.fma(sd[4], _t19, otherTZ) + Math.fma(sd[6], _t20, -(sd[3] * _t18)) + (Math.fma(sd[4], _t23, -(sd[3] * _t21)) + Math.fma(sd[6], _t22, -sd[2]));
        float _buf0 = Math.fma(otherRX, sd[6], -(otherRW * sd[3])) + Math.fma(otherRY, sd[5], -(otherRZ * sd[4]));
        float _buf1 = Math.fma(otherRY, sd[6], otherRZ * sd[3]) + Math.fma(-otherRX, sd[5], -(otherRW * sd[4]));
        float _buf2 = Math.fma(otherRX, sd[4], -(otherRW * sd[5])) + Math.fma(otherRZ, sd[6], -(otherRY * sd[3]));
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
    public FloatRigid invert(@Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t0 = -sd[4];
        float _t1 = -sd[5];
        float _t2 = -sd[3];
        float _t12 = 2.0f * Math.fma(sd[0], sd[5], -(sd[2] * sd[3]));
        float _t13 = 2.0f * Math.fma(sd[1], sd[3], -(sd[0] * sd[4]));
        float _t14 = 2.0f * Math.fma(sd[2], sd[4], -(sd[1] * sd[5]));
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
     * Invert this rigid transform; exact for any rigid motion (no scale divisions) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid invert(@Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t0 = -sd[4];
        float _t1 = -sd[5];
        float _t2 = -sd[3];
        float _t12 = 2.0f * Math.fma(sd[0], sd[5], -(sd[2] * sd[3]));
        float _t13 = 2.0f * Math.fma(sd[1], sd[3], -(sd[0] * sd[4]));
        float _t14 = 2.0f * Math.fma(sd[2], sd[4], -(sd[1] * sd[5]));
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
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatRigid normalize(@Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t3 = Math.fma(sd[6], sd[6], Math.fma(sd[5], sd[5], Math.fma(sd[3], sd[3], sd[4] * sd[4])));
        float _t4 = (1.0f / (float) Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dd[3] = sd[3] * _t4;
            dd[4] = sd[4] * _t4;
            dd[5] = sd[5] * _t4;
            dd[6] = sd[6] * _t4;
        } else {
            dd[3] = 0.0f;
            dd[4] = 0.0f;
            dd[5] = 0.0f;
            dd[6] = 0.0f;
        }
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
    }


    /**
     * Normalize this rigid transform so that its rotation part has unit length, leaving its
     * translation unchanged (a zero-length rotation yields the zero quaternion) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid normalize(@Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t3 = Math.fma(sd[6], sd[6], Math.fma(sd[5], sd[5], Math.fma(sd[3], sd[3], sd[4] * sd[4])));
        float _t4 = (1.0f / (float) Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dd[3] = sd[3] * _t4;
            dd[4] = sd[4] * _t4;
            dd[5] = sd[5] * _t4;
            dd[6] = sd[6] * _t4;
        } else {
            dd[3] = 0.0f;
            dd[4] = 0.0f;
            dd[5] = 0.0f;
            dd[6] = 0.0f;
        }
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXYZ(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t3 = sd[5] * sd[5];
        float _t8 = 2.0f * Math.fma(sd[3], sd[5], sd[4] * sd[6]);
        float _t9 = 2.0f * Math.fma(sd[3], sd[6], -_t1);
        float _t10 = Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-7f) {
            dd[0] = (float) Math.atan2(2.0f * Math.fma(sd[3], sd[6], _t1), Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t3), 1.0f));
            dd[2] = 0.0f;
        } else {
            dd[0] = (float) Math.atan2(_t9, _t10);
            dd[2] = (float) Math.atan2(2.0f * Math.fma(sd[5], sd[6], -(sd[3] * sd[4])), Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t3), 1.0f));
        }
        dd[1] = (float) Math.atan2(_t8, (float) Math.sqrt(_t12));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXYZ(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t3 = sd[5] * sd[5];
        float _t8 = 2.0f * Math.fma(sd[3], sd[5], sd[4] * sd[6]);
        float _t9 = 2.0f * Math.fma(sd[3], sd[6], -_t1);
        float _t10 = Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-7f) {
            dd[0] = (float) Math.atan2(2.0f * Math.fma(sd[3], sd[6], _t1), Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t3), 1.0f));
            dd[2] = 0.0f;
        } else {
            dd[0] = (float) Math.atan2(_t9, _t10);
            dd[2] = (float) Math.atan2(2.0f * Math.fma(sd[5], sd[6], -(sd[3] * sd[4])), Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t3), 1.0f));
        }
        dd[1] = (float) Math.atan2(_t8, (float) Math.sqrt(_t12));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXZY(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[4] * sd[5];
        float _t7 = 2.0f * Math.fma(sd[3], sd[6], _t1);
        float _t8 = 2.0f * Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        float _t9 = Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            dd[0] = (float) Math.atan2(2.0f * Math.fma(sd[3], sd[6], -_t1), Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f));
            dd[1] = 0.0f;
        } else {
            dd[0] = (float) Math.atan2(_t7, _t9);
            dd[1] = (float) Math.atan2(2.0f * Math.fma(sd[3], sd[5], sd[4] * sd[6]), Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t0), 1.0f));
        }
        dd[2] = (float) Math.atan2(_t8, (float) Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXZY(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[4] * sd[5];
        float _t7 = 2.0f * Math.fma(sd[3], sd[6], _t1);
        float _t8 = 2.0f * Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        float _t9 = Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            dd[0] = (float) Math.atan2(2.0f * Math.fma(sd[3], sd[6], -_t1), Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f));
            dd[1] = 0.0f;
        } else {
            dd[0] = (float) Math.atan2(_t7, _t9);
            dd[1] = (float) Math.atan2(2.0f * Math.fma(sd[3], sd[5], sd[4] * sd[6]), Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t0), 1.0f));
        }
        dd[2] = (float) Math.atan2(_t8, (float) Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYXZ(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t3 = sd[5] * sd[5];
        float _t8 = 2.0f * Math.fma(sd[3], sd[5], sd[4] * sd[6]);
        float _t9 = 2.0f * Math.fma(sd[3], sd[6], -(sd[4] * sd[5]));
        float _t10 = Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            dd[1] = (float) Math.atan2(2.0f * Math.fma(sd[4], sd[6], -(sd[3] * sd[5])), Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t3), 1.0f));
            dd[2] = 0.0f;
        } else {
            dd[1] = (float) Math.atan2(_t8, _t10);
            dd[2] = (float) Math.atan2(2.0f * Math.fma(sd[3], sd[4], sd[5] * sd[6]), Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t3), 1.0f));
        }
        dd[0] = (float) Math.atan2(_t9, (float) Math.sqrt(_t12));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYXZ(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t3 = sd[5] * sd[5];
        float _t8 = 2.0f * Math.fma(sd[3], sd[5], sd[4] * sd[6]);
        float _t9 = 2.0f * Math.fma(sd[3], sd[6], -(sd[4] * sd[5]));
        float _t10 = Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            dd[1] = (float) Math.atan2(2.0f * Math.fma(sd[4], sd[6], -(sd[3] * sd[5])), Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t3), 1.0f));
            dd[2] = 0.0f;
        } else {
            dd[1] = (float) Math.atan2(_t8, _t10);
            dd[2] = (float) Math.atan2(2.0f * Math.fma(sd[3], sd[4], sd[5] * sd[6]), Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t3), 1.0f));
        }
        dd[0] = (float) Math.atan2(_t9, (float) Math.sqrt(_t12));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYZX(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t7 = 2.0f * Math.fma(sd[3], sd[4], sd[5] * sd[6]);
        float _t8 = 2.0f * Math.fma(sd[4], sd[6], -(sd[3] * sd[5]));
        float _t9 = Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            dd[0] = 0.0f;
            dd[1] = (float) Math.atan2(2.0f * Math.fma(sd[3], sd[5], sd[4] * sd[6]), Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f));
        } else {
            dd[0] = (float) Math.atan2(2.0f * Math.fma(sd[3], sd[6], -(sd[4] * sd[5])), Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t0), 1.0f));
            dd[1] = (float) Math.atan2(_t8, _t9);
        }
        dd[2] = (float) Math.atan2(_t7, (float) Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYZX(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t7 = 2.0f * Math.fma(sd[3], sd[4], sd[5] * sd[6]);
        float _t8 = 2.0f * Math.fma(sd[4], sd[6], -(sd[3] * sd[5]));
        float _t9 = Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            dd[0] = 0.0f;
            dd[1] = (float) Math.atan2(2.0f * Math.fma(sd[3], sd[5], sd[4] * sd[6]), Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f));
        } else {
            dd[0] = (float) Math.atan2(2.0f * Math.fma(sd[3], sd[6], -(sd[4] * sd[5])), Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t0), 1.0f));
            dd[1] = (float) Math.atan2(_t8, _t9);
        }
        dd[2] = (float) Math.atan2(_t7, (float) Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZXY(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[5] * sd[5];
        float _t7 = 2.0f * Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        float _t8 = 2.0f * Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        float _t9 = Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t1), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            dd[1] = 0.0f;
            dd[2] = (float) Math.atan2(2.0f * Math.fma(sd[3], sd[4], sd[5] * sd[6]), Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t1), 1.0f));
        } else {
            dd[1] = (float) Math.atan2(2.0f * Math.fma(sd[4], sd[6], -(sd[3] * sd[5])), Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f));
            dd[2] = (float) Math.atan2(_t8, _t9);
        }
        dd[0] = (float) Math.atan2(_t7, (float) Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZXY(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[5] * sd[5];
        float _t7 = 2.0f * Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        float _t8 = 2.0f * Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        float _t9 = Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t1), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            dd[1] = 0.0f;
            dd[2] = (float) Math.atan2(2.0f * Math.fma(sd[3], sd[4], sd[5] * sd[6]), Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t1), 1.0f));
        } else {
            dd[1] = (float) Math.atan2(2.0f * Math.fma(sd[4], sd[6], -(sd[3] * sd[5])), Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f));
            dd[2] = (float) Math.atan2(_t8, _t9);
        }
        dd[0] = (float) Math.atan2(_t7, (float) Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZYX(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t7 = 2.0f * Math.fma(sd[3], sd[4], sd[5] * sd[6]);
        float _t8 = 2.0f * Math.fma(sd[4], sd[6], -(sd[3] * sd[5]));
        float _t9 = Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            dd[0] = 0.0f;
            dd[2] = (float) Math.atan2(2.0f * Math.fma(sd[5], sd[6], -(sd[3] * sd[4])), Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t0), 1.0f));
        } else {
            dd[0] = (float) Math.atan2(2.0f * Math.fma(sd[3], sd[6], sd[4] * sd[5]), Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f));
            dd[2] = (float) Math.atan2(_t7, _t9);
        }
        dd[1] = (float) Math.atan2(_t8, (float) Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZYX(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t7 = 2.0f * Math.fma(sd[3], sd[4], sd[5] * sd[6]);
        float _t8 = 2.0f * Math.fma(sd[4], sd[6], -(sd[3] * sd[5]));
        float _t9 = Math.fma(-2.0f, Math.fma(sd[4], sd[4], _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            dd[0] = 0.0f;
            dd[2] = (float) Math.atan2(2.0f * Math.fma(sd[5], sd[6], -(sd[3] * sd[4])), Math.fma(-2.0f, Math.fma(sd[3], sd[3], _t0), 1.0f));
        } else {
            dd[0] = (float) Math.atan2(2.0f * Math.fma(sd[3], sd[6], sd[4] * sd[5]), Math.fma(-2.0f, Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f));
            dd[2] = (float) Math.atan2(_t7, _t9);
        }
        dd[1] = (float) Math.atan2(_t8, (float) Math.sqrt(_t11));
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
    public FloatQuat getRotation(@Mutated FloatQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatQuatImpl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 0);
        return dest;
    }


    /**
     * Get the rotation of this rigid transform and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat getRotation(@Mutated DoubleQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = sd[3];
        dd[1] = sd[4];
        dd[2] = sd[5];
        dd[3] = sd[6];
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
    public Float3 getTranslation(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
    }


    /**
     * Get the translation of this rigid transform and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getTranslation(@Mutated Double3 dest) {
        float[] sd = this.data;
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
    public @Mutated FloatRigid makeRotationAxis(float angle, Float3R axis) {
        return makeRotationAxis(angle, axis.x(), axis.y(), axis.z());
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
    @Mutated public FloatRigid makeRotationAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = axisX * _t1;
        dd[4] = axisY * _t1;
        dd[5] = axisZ * _t1;
        dd[6] = (float) Math.cosFromSin(_t1, _t0);
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
    @Mutated public FloatRigid makeRotationX(float angle) {
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = _t1;
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = (float) Math.cosFromSin(_t1, _t0);
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
    @Mutated public FloatRigid makeRotationXYZ(float angleX, float angleY, float angleZ) {
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
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
    @Mutated public FloatRigid makeRotationXZY(float angleX, float angleZ, float angleY) {
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
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
    @Mutated public FloatRigid makeRotationY(float angle) {
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        VEC_2.intoArray(dd, 0);
        dd[4] = _t1;
        dd[5] = 0.0f;
        dd[6] = (float) Math.cosFromSin(_t1, _t0);
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
    @Mutated public FloatRigid makeRotationYXZ(float angleY, float angleX, float angleZ) {
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
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
    @Mutated public FloatRigid makeRotationYZX(float angleY, float angleZ, float angleX) {
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
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
    @Mutated public FloatRigid makeRotationZ(float angle) {
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        VEC_2.intoArray(dd, 0);
        dd[4] = 0.0f;
        dd[5] = _t1;
        dd[6] = (float) Math.cosFromSin(_t1, _t0);
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
    @Mutated public FloatRigid makeRotationZXY(float angleZ, float angleX, float angleY) {
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
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
    @Mutated public FloatRigid makeRotationZYX(float angleZ, float angleY, float angleX) {
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
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
    public FloatRigid rotate(FloatQuatR rotation, @Mutated FloatRigid dest) {
        return rotate(rotation.x(), rotation.y(), rotation.z(), rotation.w(), dest);
    }


    /**
     * Apply the rotation represented by the quaternion {@code rotation} to this rigid transform and
     * store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotate(FloatQuatR rotation, @Mutated DoubleRigid dest) {
        return rotate(rotation.x(), rotation.y(), rotation.z(), rotation.w(), dest);
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
    public FloatRigid rotate(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = Math.fma(rotationX, sd[6], rotationW * sd[3]) + Math.fma(rotationZ, sd[4], -(rotationY * sd[5]));
        float _buf1 = Math.fma(rotationX, sd[5], rotationW * sd[4]) + Math.fma(rotationY, sd[6], -(rotationZ * sd[3]));
        float _buf2 = Math.fma(rotationY, sd[3], rotationZ * sd[6]) + Math.fma(rotationW, sd[5], -(rotationX * sd[4]));
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleRigid rotate(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = Math.fma(rotationX, sd[6], rotationW * sd[3]) + Math.fma(rotationZ, sd[4], -(rotationY * sd[5]));
        float _buf1 = Math.fma(rotationX, sd[5], rotationW * sd[4]) + Math.fma(rotationY, sd[6], -(rotationZ * sd[3]));
        float _buf2 = Math.fma(rotationY, sd[3], rotationZ * sd[6]) + Math.fma(rotationW, sd[5], -(rotationX * sd[4]));
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
    public FloatRigid rotateAxis(float angle, Float3R axis, @Mutated FloatRigid dest) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z(), dest);
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this rigid transform
     * and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateAxis(float angle, Float3R axis, @Mutated DoubleRigid dest) {
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
    public FloatRigid rotateAxis(float angle, float axisX, float axisY, float axisZ, @Mutated FloatRigid dest) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = (float) Math.cosFromSin(_t1, _t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateAxis_s58b7dfcb_1(dest, sd, dd, _t2, _t3, _t4, _t5, Math.fma(sd[3], _t5, sd[6] * _t2) + Math.fma(sd[4], _t3, -(sd[5] * _t4)), Math.fma(sd[4], _t5, sd[5] * _t2) + Math.fma(sd[6], _t4, -(sd[3] * _t3)), Math.fma(sd[3], _t4, sd[6] * _t3) + Math.fma(sd[5], _t5, -(sd[4] * _t2)));
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateAxis_s58b7dfcb_1(FloatRigid dest, float[] sd, float[] dd, float _t2, float _t3, float _t4, float _t5, float _buf0, float _buf1, float _buf2) {
        dd[6] = Math.fma(sd[6], _t5, -(sd[3] * _t2)) - Math.fma(sd[4], _t4, sd[5] * _t3);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) to this rigid transform and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} rigid transform and {@code R} the rotation rigid transform, then
     * the new rigid transform will be {@code M * R}. So when transforming a vector {@code v} with
     * the new rigid transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleRigid rotateAxis(float angle, float axisX, float axisY, float axisZ, @Mutated DoubleRigid dest) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = (float) Math.cosFromSin(_t1, _t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateAxis_saa5ea00c_1(dest, sd, dd, _t2, _t3, _t4, _t5, Math.fma(sd[3], _t5, sd[6] * _t2) + Math.fma(sd[4], _t3, -(sd[5] * _t4)), Math.fma(sd[4], _t5, sd[5] * _t2) + Math.fma(sd[6], _t4, -(sd[3] * _t3)), Math.fma(sd[3], _t4, sd[6] * _t3) + Math.fma(sd[5], _t5, -(sd[4] * _t2)));
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateAxis_saa5ea00c_1(DoubleRigid dest, float[] sd, double[] dd, float _t2, float _t3, float _t4, float _t5, float _buf0, float _buf1, float _buf2) {
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
    public FloatRigid rotateX(float angle, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = Math.fma(sd[3], _t2, sd[6] * _t1);
        float _buf1 = Math.fma(sd[4], _t2, sd[5] * _t1);
        dd[5] = Math.fma(sd[5], _t2, -(sd[4] * _t1));
        dd[6] = Math.fma(sd[6], _t2, -(sd[3] * _t1));
        dd[3] = _buf0;
        dd[4] = _buf1;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateX(float angle, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = Math.fma(sd[3], _t2, sd[6] * _t1);
        float _buf1 = Math.fma(sd[4], _t2, sd[5] * _t1);
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
    public FloatRigid rotateXYZ(float angleX, float angleY, float angleZ, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        float _t19 = Math.fma(_t10, _t8, _t11 * _t5);
        float _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateXYZ_s8702150_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t21, sd[6] * _t19) + Math.fma(sd[4], _t20, -(sd[5] * _t22)), Math.fma(sd[4], _t21, sd[5] * _t19) + Math.fma(sd[6], _t22, -(sd[3] * _t20)));
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateXYZ_s8702150_1(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[3], _t22, sd[6] * _t20) + Math.fma(sd[5], _t21, -(sd[4] * _t19));
        dd[6] = Math.fma(sd[6], _t21, -(sd[3] * _t19)) - Math.fma(sd[4], _t22, sd[5] * _t20);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateXYZ(float angleX, float angleY, float angleZ, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        float _t19 = Math.fma(_t10, _t8, _t11 * _t5);
        float _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateXYZ_s35646349_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t21, sd[6] * _t19) + Math.fma(sd[4], _t20, -(sd[5] * _t22)), Math.fma(sd[4], _t21, sd[5] * _t19) + Math.fma(sd[6], _t22, -(sd[3] * _t20)));
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateXYZ_s35646349_1(DoubleRigid dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[3], _t22, sd[6] * _t20) + Math.fma(sd[5], _t21, -(sd[4] * _t19));
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
    public FloatRigid rotateXZY(float angleX, float angleZ, float angleY, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t10, _t5, _t11 * _t8);
        float _t21 = Math.fma(_t10, _t8, -(_t11 * _t5));
        float _t22 = Math.fma(_t12, _t5, -(_t9 * _t8));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateXZY_s3be93c7a_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t19, sd[6] * _t21) + Math.fma(sd[4], _t20, -(sd[5] * _t22)), Math.fma(sd[4], _t19, sd[5] * _t21) + Math.fma(sd[6], _t22, -(sd[3] * _t20)));
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateXZY_s3be93c7a_1(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[3], _t22, sd[6] * _t20) + Math.fma(sd[5], _t19, -(sd[4] * _t21));
        dd[6] = Math.fma(sd[6], _t19, -(sd[3] * _t21)) - Math.fma(sd[4], _t22, sd[5] * _t20);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateXZY(float angleX, float angleZ, float angleY, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t10, _t5, _t11 * _t8);
        float _t21 = Math.fma(_t10, _t8, -(_t11 * _t5));
        float _t22 = Math.fma(_t12, _t5, -(_t9 * _t8));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateXZY_s4456c68f_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t19, sd[6] * _t21) + Math.fma(sd[4], _t20, -(sd[5] * _t22)), Math.fma(sd[4], _t19, sd[5] * _t21) + Math.fma(sd[6], _t22, -(sd[3] * _t20)));
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateXZY_s4456c68f_1(DoubleRigid dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[3], _t22, sd[6] * _t20) + Math.fma(sd[5], _t19, -(sd[4] * _t21));
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
    public FloatRigid rotateY(float angle, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = Math.fma(sd[3], _t2, -(sd[5] * _t1));
        float _buf1 = Math.fma(sd[4], _t2, sd[6] * _t1);
        dd[5] = Math.fma(sd[3], _t1, sd[5] * _t2);
        dd[6] = Math.fma(sd[6], _t2, -(sd[4] * _t1));
        dd[3] = _buf0;
        dd[4] = _buf1;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateY(float angle, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = Math.fma(sd[3], _t2, -(sd[5] * _t1));
        float _buf1 = Math.fma(sd[4], _t2, sd[6] * _t1);
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
    public FloatRigid rotateYXZ(float angleY, float angleX, float angleZ, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t10, _t8, _t11 * _t5);
        float _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateYXZ_scb425016_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t19, sd[6] * _t20) + Math.fma(sd[4], _t21, -(sd[5] * _t22)), Math.fma(sd[4], _t19, sd[5] * _t20) + Math.fma(sd[6], _t22, -(sd[3] * _t21)));
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateYXZ_scb425016_1(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[3], _t22, sd[6] * _t21) + Math.fma(sd[5], _t19, -(sd[4] * _t20));
        dd[6] = Math.fma(sd[6], _t19, -(sd[3] * _t20)) - Math.fma(sd[4], _t22, sd[5] * _t21);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateYXZ(float angleY, float angleX, float angleZ, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t10, _t8, _t11 * _t5);
        float _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateYXZ_sb92de5ab_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t19, sd[6] * _t20) + Math.fma(sd[4], _t21, -(sd[5] * _t22)), Math.fma(sd[4], _t19, sd[5] * _t20) + Math.fma(sd[6], _t22, -(sd[3] * _t21)));
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateYXZ_sb92de5ab_1(DoubleRigid dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[3], _t22, sd[6] * _t21) + Math.fma(sd[5], _t19, -(sd[4] * _t20));
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
    public FloatRigid rotateYZX(float angleY, float angleZ, float angleX, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        float _t19 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateYZX_sc1b8258e_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t21, sd[6] * _t19) + Math.fma(sd[4], _t22, -(sd[5] * _t20)), Math.fma(sd[4], _t21, sd[5] * _t19) + Math.fma(sd[6], _t20, -(sd[3] * _t22)));
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateYZX_sc1b8258e_1(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[3], _t20, sd[6] * _t22) + Math.fma(sd[5], _t21, -(sd[4] * _t19));
        dd[6] = Math.fma(sd[6], _t21, -(sd[3] * _t19)) - Math.fma(sd[4], _t20, sd[5] * _t22);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateYZX(float angleY, float angleZ, float angleX, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        float _t19 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateYZX_sd02c1f53_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t21, sd[6] * _t19) + Math.fma(sd[4], _t22, -(sd[5] * _t20)), Math.fma(sd[4], _t21, sd[5] * _t19) + Math.fma(sd[6], _t20, -(sd[3] * _t22)));
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateYZX_sd02c1f53_1(DoubleRigid dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[3], _t20, sd[6] * _t22) + Math.fma(sd[5], _t21, -(sd[4] * _t19));
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
    public FloatRigid rotateZ(float angle, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = Math.fma(sd[3], _t2, sd[4] * _t1);
        dd[4] = Math.fma(sd[4], _t2, -(sd[3] * _t1));
        float _buf1 = Math.fma(sd[5], _t2, sd[6] * _t1);
        dd[6] = Math.fma(sd[6], _t2, -(sd[5] * _t1));
        dd[3] = _buf0;
        dd[5] = _buf1;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateZ(float angle, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = Math.fma(sd[3], _t2, sd[4] * _t1);
        dd[4] = Math.fma(sd[4], _t2, -(sd[3] * _t1));
        float _buf1 = Math.fma(sd[5], _t2, sd[6] * _t1);
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
    public FloatRigid rotateZXY(float angleZ, float angleX, float angleY, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        float _t19 = Math.fma(_t10, _t5, _t11 * _t8);
        float _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateZXY_sa0fff6be_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t21, sd[6] * _t22) + Math.fma(sd[4], _t19, -(sd[5] * _t20)), Math.fma(sd[4], _t21, sd[5] * _t22) + Math.fma(sd[6], _t20, -(sd[3] * _t19)));
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateZXY_sa0fff6be_1(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[3], _t20, sd[6] * _t19) + Math.fma(sd[5], _t21, -(sd[4] * _t22));
        dd[6] = Math.fma(sd[6], _t21, -(sd[3] * _t22)) - Math.fma(sd[4], _t20, sd[5] * _t19);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateZXY(float angleZ, float angleX, float angleY, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        float _t19 = Math.fma(_t10, _t5, _t11 * _t8);
        float _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateZXY_s35a57e63_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t21, sd[6] * _t22) + Math.fma(sd[4], _t19, -(sd[5] * _t20)), Math.fma(sd[4], _t21, sd[5] * _t22) + Math.fma(sd[6], _t20, -(sd[3] * _t19)));
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateZXY_s35a57e63_1(DoubleRigid dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[3], _t20, sd[6] * _t19) + Math.fma(sd[5], _t21, -(sd[4] * _t22));
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
    public FloatRigid rotateZYX(float angleZ, float angleY, float angleX, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateZYX_sdc89e05c_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t19, sd[6] * _t21) + Math.fma(sd[4], _t22, -(sd[5] * _t20)), Math.fma(sd[4], _t19, sd[5] * _t21) + Math.fma(sd[6], _t20, -(sd[3] * _t22)));
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateZYX_sdc89e05c_1(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[3], _t20, sd[6] * _t22) + Math.fma(sd[5], _t19, -(sd[4] * _t21));
        dd[6] = Math.fma(sd[6], _t19, -(sd[3] * _t21)) - Math.fma(sd[4], _t20, sd[5] * _t22);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid rotateZYX(float angleZ, float angleY, float angleX, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateZYX_s2c1ce695_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t19, sd[6] * _t21) + Math.fma(sd[4], _t22, -(sd[5] * _t20)), Math.fma(sd[4], _t19, sd[5] * _t21) + Math.fma(sd[6], _t20, -(sd[3] * _t22)));
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateZYX_s2c1ce695_1(DoubleRigid dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[3], _t20, sd[6] * _t22) + Math.fma(sd[5], _t19, -(sd[4] * _t21));
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
    public FloatRigid translate(Float3R translation, @Mutated FloatRigid dest) {
        return translate(translation.x(), translation.y(), translation.z(), dest);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid translate(Float3R translation, @Mutated DoubleRigid dest) {
        return translate(translation.x(), translation.y(), translation.z(), dest);
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
    public FloatRigid translate(float translationX, float translationY, float translationZ, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[3], translationY, -(sd[4] * translationX));
        float _t10 = 2.0f * Math.fma(sd[5], translationX, -(sd[3] * translationZ));
        float _t11 = 2.0f * Math.fma(sd[4], translationZ, -(sd[5] * translationY));
        dd[0] = Math.fma(sd[4], _t9, Math.fma(-sd[5], _t10, Math.fma(sd[6], _t11, sd[0] + translationX)));
        dd[1] = Math.fma(sd[5], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, sd[1] + translationY)));
        dd[2] = Math.fma(sd[3], _t10, Math.fma(-sd[4], _t11, Math.fma(sd[6], _t9, sd[2] + translationZ)));
        FloatVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleRigid translate(float translationX, float translationY, float translationZ, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[3], translationY, -(sd[4] * translationX));
        float _t10 = 2.0f * Math.fma(sd[5], translationX, -(sd[3] * translationZ));
        float _t11 = 2.0f * Math.fma(sd[4], translationZ, -(sd[5] * translationY));
        dd[0] = Math.fma(sd[4], _t9, Math.fma(-sd[5], _t10, Math.fma(sd[6], _t11, sd[0] + translationX)));
        dd[1] = Math.fma(sd[5], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, sd[1] + translationY)));
        dd[2] = Math.fma(sd[3], _t10, Math.fma(-sd[4], _t11, Math.fma(sd[6], _t9, sd[2] + translationZ)));
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
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
    public Float3 transform(Float3R v, @Mutated Float3 dest) {
        return transform(v.x(), v.y(), v.z(), dest);
    }


    /**
     * Transform {@code v} by this rigid transform and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transform(Float3R v, @Mutated Double3 dest) {
        return transform(v.x(), v.y(), v.z(), dest);
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
    public Float3 transform(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[3], vY, -(sd[4] * vX));
        float _t10 = 2.0f * Math.fma(sd[5], vX, -(sd[3] * vZ));
        float _t11 = 2.0f * Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = Math.fma(sd[4], _t9, Math.fma(-sd[5], _t10, Math.fma(sd[6], _t11, sd[0] + vX)));
        dd[1] = Math.fma(sd[5], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, sd[1] + vY)));
        dd[2] = Math.fma(sd[3], _t10, Math.fma(-sd[4], _t11, Math.fma(sd[6], _t9, sd[2] + vZ)));
        return dest;
    }


    /**
     * Transform ({@code vX}, {@code vY}, {@code vZ}) by this rigid transform and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transform(float vX, float vY, float vZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[3], vY, -(sd[4] * vX));
        float _t10 = 2.0f * Math.fma(sd[5], vX, -(sd[3] * vZ));
        float _t11 = 2.0f * Math.fma(sd[4], vZ, -(sd[5] * vY));
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
    public Float3 transformDirection(Float3R v, @Mutated Float3 dest) {
        return transformDirection(v.x(), v.y(), v.z(), dest);
    }


    /**
     * Transform the given direction by the rotation part of this rigid transform, ignoring the
     * translation and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirection(Float3R v, @Mutated Double3 dest) {
        return transformDirection(v.x(), v.y(), v.z(), dest);
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
    public Float3 transformDirection(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[3], vY, -(sd[4] * vX));
        float _t10 = 2.0f * Math.fma(sd[5], vX, -(sd[3] * vZ));
        float _t11 = 2.0f * Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = Math.fma(sd[4], _t9, Math.fma(-sd[5], _t10, Math.fma(sd[6], _t11, vX)));
        dd[1] = Math.fma(sd[5], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, vY)));
        dd[2] = Math.fma(sd[3], _t10, Math.fma(-sd[4], _t11, Math.fma(sd[6], _t9, vZ)));
        return dest;
    }


    /**
     * Transform the given direction by the rotation part of this rigid transform, ignoring the
     * translation and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirection(float vX, float vY, float vZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[3], vY, -(sd[4] * vX));
        float _t10 = 2.0f * Math.fma(sd[5], vX, -(sd[3] * vZ));
        float _t11 = 2.0f * Math.fma(sd[4], vZ, -(sd[5] * vY));
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
    public Float3 transformDirectionInverse(Float3R v, @Mutated Float3 dest) {
        return transformDirectionInverse(v.x(), v.y(), v.z(), dest);
    }


    /**
     * Transform the given direction by the inverse of this rigid transform's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirectionInverse(Float3R v, @Mutated Double3 dest) {
        return transformDirectionInverse(v.x(), v.y(), v.z(), dest);
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
    public Float3 transformDirectionInverse(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[3], vZ, -(sd[5] * vX));
        float _t10 = 2.0f * Math.fma(sd[4], vX, -(sd[3] * vY));
        float _t11 = 2.0f * Math.fma(sd[5], vY, -(sd[4] * vZ));
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirectionInverse(float vX, float vY, float vZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[3], vZ, -(sd[5] * vX));
        float _t10 = 2.0f * Math.fma(sd[4], vX, -(sd[3] * vY));
        float _t11 = 2.0f * Math.fma(sd[5], vY, -(sd[4] * vZ));
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
    public Float3 transformInverse(Float3R p, @Mutated Float3 dest) {
        return transformInverse(p.x(), p.y(), p.z(), dest);
    }


    /**
     * Transform {@code p} by the inverse of this rigid transform and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformInverse(Float3R p, @Mutated Double3 dest) {
        return transformInverse(p.x(), p.y(), p.z(), dest);
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
    public Float3 transformInverse(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t12 = 2.0f * Math.fma(sd[3], _t0, -(sd[5] * _t1));
        float _t13 = 2.0f * Math.fma(sd[4], _t1, -(sd[3] * _t2));
        float _t14 = 2.0f * Math.fma(sd[5], _t2, -(sd[4] * _t0));
        dd[0] = Math.fma(sd[5], _t12, Math.fma(-sd[4], _t13, Math.fma(sd[6], _t14, _t1)));
        dd[1] = Math.fma(sd[3], _t13, Math.fma(-sd[5], _t14, Math.fma(sd[6], _t12, _t2)));
        dd[2] = Math.fma(sd[4], _t14, Math.fma(-sd[3], _t12, Math.fma(sd[6], _t13, _t0)));
        return dest;
    }


    /**
     * Transform ({@code pX}, {@code pY}, {@code pZ}) by the inverse of this rigid transform and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformInverse(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t12 = 2.0f * Math.fma(sd[3], _t0, -(sd[5] * _t1));
        float _t13 = 2.0f * Math.fma(sd[4], _t1, -(sd[3] * _t2));
        float _t14 = 2.0f * Math.fma(sd[5], _t2, -(sd[4] * _t0));
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
    public Float3 transformPosition(Float3R v, @Mutated Float3 dest) {
        return transform(v, dest);
    }


    /**
     * Transform the given position by this rigid transform, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param v the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPosition(Float3R v, @Mutated Double3 dest) {
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
    public Float3 transformPosition(float vX, float vY, float vZ, @Mutated Float3 dest) {
        return transform(vX, vY, vZ, dest);
    }


    /**
     * Transform the given position by this rigid transform, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPosition(float vX, float vY, float vZ, @Mutated Double3 dest) {
        return transform(vX, vY, vZ, dest);
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
    public Float3 transformPositionInverse(Float3R p, @Mutated Float3 dest) {
        return transformInverse(p, dest);
    }


    /**
     * Transform the given position by the inverse of this rigid transform (world to local), without
     * materializing {@code invert()} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPositionInverse(Float3R p, @Mutated Double3 dest) {
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
    public Float3 transformPositionInverse(float pX, float pY, float pZ, @Mutated Float3 dest) {
        return transformInverse(pX, pY, pZ, dest);
    }


    /**
     * Transform the given position by the inverse of this rigid transform (world to local), without
     * materializing {@code invert()} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPositionInverse(float pX, float pY, float pZ, @Mutated Double3 dest) {
        return transformInverse(pX, pY, pZ, dest);
    }

    public float tX() { return data[0]; }
    public float tY() { return data[1]; }
    public float tZ() { return data[2]; }
    public float rX() { return data[3]; }
    public float rY() { return data[4]; }
    public float rZ() { return data[5]; }
    public float rW() { return data[6]; }

    @Override public String toString() {
        return "FloatRigid(" + tX() + ", " + tY() + ", " + tZ() + ", " + rX() + ", " + rY() + ", " + rZ() + ", " + rW() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatRigidImpl)) return false;
        FloatRigidImpl o = (FloatRigidImpl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Float.isFinite(data[0])
            && Float.isFinite(data[1])
            && Float.isFinite(data[2])
            && Float.isFinite(data[3])
            && Float.isFinite(data[4])
            && Float.isFinite(data[5])
            && Float.isFinite(data[6]);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(data[0])
            || Float.isNaN(data[1])
            || Float.isNaN(data[2])
            || Float.isNaN(data[3])
            || Float.isNaN(data[4])
            || Float.isNaN(data[5])
            || Float.isNaN(data[6]);
    }

    @Override public boolean equalsEpsilon(FloatRigidR other, float epsilon) {
        return Math.abs(data[0] - other.tX()) <= epsilon
            && Math.abs(data[1] - other.tY()) <= epsilon
            && Math.abs(data[2] - other.tZ()) <= epsilon
            && Math.abs(data[3] - other.rX()) <= epsilon
            && Math.abs(data[4] - other.rY()) <= epsilon
            && Math.abs(data[5] - other.rZ()) <= epsilon
            && Math.abs(data[6] - other.rW()) <= epsilon;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        dest[offset + 6] = this.data[6];
        return dest;
    }
    public @Mutated FloatRigid load(float[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
        return this;
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public FloatRigid loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatRigid loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public FloatRigid storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public FloatRigid loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    public FloatRigid load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        dest[offset + 6] = this.data[6];
        return dest;
    }
    public @Mutated FloatRigid load(double[] src, int offset) {
        this.data[0] = (float) src[offset + 0];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        this.data[4] = (float) src[offset + 4];
        this.data[5] = (float) src[offset + 5];
        this.data[6] = (float) src[offset + 6];
        return this;
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public FloatRigid loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }
    public FloatRigid loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public FloatRigid storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public FloatRigid loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }
    public MemorySegment storeDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }
    public FloatRigid loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(this, offset, src);
    }

    private static final VectorSpecies<Float> COL_SPECIES = FloatVector.SPECIES_128;
    private static final FloatVector VEC_1 = FloatVector.fromArray(COL_SPECIES, new float[]{0.0f, 0.0f, 0.0f, 1.0f}, 0);
    private static final FloatVector VEC_2 = FloatVector.fromArray(COL_SPECIES, new float[]{0.0f, 0.0f, 0.0f, 0.0f}, 0);
    private static final float[] DATA_0 = new float[] {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};

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

    /** Double-precision twin of {@link #unitScale(float, float, float)}. */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }
}
