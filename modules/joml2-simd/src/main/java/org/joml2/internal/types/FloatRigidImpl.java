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
import org.joml2.internal.unsafe.HeapSegments;
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
        static final FloatRigidSegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatRigidSegOpsUnsafe()
            : new FloatRigidSegOpsMS();
        static final FloatRigidBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatRigidBbOpsUnsafe()
            : new FloatRigidBbOpsApi();
        static final FloatRigidRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
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
        float[] srcData = ((FloatRigidImpl) src).data;
        float[] dd = this.data = new float[7];
        dd[0] = srcData[0];
        dd[1] = srcData[1];
        dd[2] = srcData[2];
        dd[3] = srcData[3];
        dd[4] = srcData[4];
        dd[5] = srcData[5];
        dd[6] = srcData[6];
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
    @Mutated public FloatRigid makeFromAxisAngle(Float3R axis, float angle, Float3R translation) {
        float[] axisData = ((Float3Impl) axis).data;
        float[] translationData = ((Float3Impl) translation).data;
        float axisX = axisData[0];
        float axisY = axisData[1];
        float axisZ = axisData[2];
        float translationY = translationData[1];
        float translationZ = translationData[2];
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        dd[0] = translationData[0];
        dd[1] = translationY;
        dd[2] = translationZ;
        dd[3] = axisX * _t1;
        dd[4] = axisY * _t1;
        dd[5] = axisZ * _t1;
        dd[6] = Math.cosWithSin(_t0);
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
    @Mutated public FloatRigid makeFromAxisAngle(float axisX, float axisY, float axisZ, float angle, float translationX, float translationY, float translationZ) {
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        dd[0] = translationX;
        dd[1] = translationY;
        dd[2] = translationZ;
        dd[3] = axisX * _t1;
        dd[4] = axisY * _t1;
        dd[5] = axisZ * _t1;
        dd[6] = Math.cosWithSin(_t0);
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
    @Mutated public FloatRigid makeTranslationRotation(Float3R translation, FloatQuatR rotation) {
        float[] translationData = ((Float3Impl) translation).data;
        float[] rotationData = ((FloatQuatImpl) rotation).data;
        float translationY = translationData[1];
        float translationZ = translationData[2];
        float rotationX = rotationData[0];
        float rotationY = rotationData[1];
        float rotationZ = rotationData[2];
        float rotationW = rotationData[3];
        float[] dd = this.data;
        dd[0] = translationData[0];
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
    @Mutated public FloatRigid set(FloatRigidR v) {
        float[] vData = ((FloatRigidImpl) v).data;
        float vTY = vData[1];
        float vTZ = vData[2];
        float vRX = vData[3];
        float vRY = vData[4];
        float vRZ = vData[5];
        float vRW = vData[6];
        float[] dd = this.data;
        dd[0] = vData[0];
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
        float[] rData = ((FloatQuatImpl) r).data;
        float rX = rData[0];
        float rY = rData[1];
        float rZ = rData[2];
        float rW = rData[3];
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
        float[] rData = ((FloatQuatImpl) r).data;
        float rX = rData[0];
        float rY = rData[1];
        float rZ = rData[2];
        float rW = rData[3];
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
        float[] tData = ((Float3Impl) t).data;
        float tY = tData[1];
        float tZ = tData[2];
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        dd[0] = tData[0];
        dd[1] = tY;
        dd[2] = tZ;
        FloatVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        return dest;
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
        float[] tData = ((Float3Impl) t).data;
        float tY = tData[1];
        float tZ = tData[2];
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = tData[0];
        dd[1] = tY;
        dd[2] = tZ;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
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
    @Mutated public FloatRigid makeFromDualQuat(FloatDualQuatR dq) {
        float[] dqData = ((FloatDualQuatImpl) dq).data;
        float dqRX = dqData[0];
        float dqRY = dqData[1];
        float dqRZ = dqData[2];
        float dqRW = dqData[3];
        float dqDX = dqData[4];
        float dqDY = dqData[5];
        float dqDZ = dqData[6];
        float dqDW = dqData[7];
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] dd = this.data;
            dd[0] = 2.0f * (java.lang.Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + java.lang.Math.fma(dqRW, dqDX, dqRX * -dqDW));
            dd[1] = 2.0f * (java.lang.Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + java.lang.Math.fma(dqRW, dqDY, dqRY * -dqDW));
            dd[2] = 2.0f * (java.lang.Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + java.lang.Math.fma(dqRW, dqDZ, dqRZ * -dqDW));
            dd[3] = dqRX;
            dd[4] = dqRY;
            dd[5] = dqRZ;
            dd[6] = dqRW;
            return this;
        } else {
            float[] dd = this.data;
            dd[0] = 2.0f * (((dqRY) * (dqDZ) - (dqRZ * dqDY)) + ((dqRW) * (dqDX) - (dqRX * dqDW)));
            dd[1] = 2.0f * (((dqRZ) * (dqDX) - (dqRX * dqDZ)) + ((dqRW) * (dqDY) - (dqRY * dqDW)));
            dd[2] = 2.0f * (((dqRX) * (dqDY) - (dqRY * dqDX)) + ((dqRW) * (dqDZ) - (dqRZ * dqDW)));
            dd[3] = dqRX;
            dd[4] = dqRY;
            dd[5] = dqRZ;
            dd[6] = dqRW;
            return this;
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] dd = this.data;
            dd[0] = 2.0f * (java.lang.Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + java.lang.Math.fma(dqRW, dqDX, dqRX * -dqDW));
            dd[1] = 2.0f * (java.lang.Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + java.lang.Math.fma(dqRW, dqDY, dqRY * -dqDW));
            dd[2] = 2.0f * (java.lang.Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + java.lang.Math.fma(dqRW, dqDZ, dqRZ * -dqDW));
            dd[3] = dqRX;
            dd[4] = dqRY;
            dd[5] = dqRZ;
            dd[6] = dqRW;
            return this;
        } else {
            float[] dd = this.data;
            dd[0] = 2.0f * (((dqRY) * (dqDZ) - (dqRZ * dqDY)) + ((dqRW) * (dqDX) - (dqRX * dqDW)));
            dd[1] = 2.0f * (((dqRZ) * (dqDX) - (dqRX * dqDZ)) + ((dqRW) * (dqDY) - (dqRY * dqDW)));
            dd[2] = 2.0f * (((dqRX) * (dqDY) - (dqRY * dqDX)) + ((dqRW) * (dqDZ) - (dqRZ * dqDW)));
            dd[3] = dqRX;
            dd[4] = dqRY;
            dd[5] = dqRZ;
            dd[6] = dqRW;
            return this;
        }
    }

    /**
     * Set this rigid transform to the rotation extracted from the given matrix, with zero
     * translation (scale is removed by normalizing the columns, but shear is not removed: a sheared
     * block yields a rotation quaternion that is not unit length; a mirroring block (negative
     * determinant) loses its mirror: the rotation is that of the block with its first column
     * negated).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public FloatRigid makeFromMatrix(Float3x3R m) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeFromMatrix_mulAdd(m);
        float[] dd = this.data;
        float[] mData = ((Float3x3Impl) m).data;
        float _ld0 = mData[4];
        float _ld1 = mData[8];
        float _ld2 = mData[5];
        float _ld3 = mData[3];
        float _ld4 = mData[6];
        float _ld5 = mData[7];
        float _ld6 = mData[2];
        float _ld7 = mData[0];
        float _ld8 = mData[1];
        float _ct0 = java.lang.Math.fma(_ld2, _ld2, java.lang.Math.fma(_ld3, _ld3, _ld0 * _ld0));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _ct1 = java.lang.Math.fma(_ld1, _ld1, java.lang.Math.fma(_ld4, _ld4, _ld5 * _ld5));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _ct2 = java.lang.Math.fma(_ld6, _ld6, java.lang.Math.fma(_ld7, _ld7, _ld8 * _ld8));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        makeFromMatrix_s4398bb65_520_fma(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ct0, _ct1, _ct2);
        return this;
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid makeFromMatrix_mulAdd(Float3x3R m) {
        float[] dd = this.data;
        float[] mData = ((Float3x3Impl) m).data;
        float _ld0 = mData[4];
        float _ld1 = mData[8];
        float _ld2 = mData[5];
        float _ld3 = mData[3];
        float _ld4 = mData[6];
        float _ld5 = mData[7];
        float _ld6 = mData[2];
        float _ld7 = mData[0];
        float _ld8 = mData[1];
        float _ct0 = ((_ld2) * (_ld2) + (((_ld3) * (_ld3) + (_ld0 * _ld0))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _ct1 = ((_ld1) * (_ld1) + (((_ld4) * (_ld4) + (_ld5 * _ld5))));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _ct2 = ((_ld6) * (_ld6) + (((_ld7) * (_ld7) + (_ld8 * _ld8))));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        makeFromMatrix_s4398bb65_520_mulAdd(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ct0, _ct1, _ct2);
        return this;
    }

    /**
     * Part of {@code makeFromMatrix}, split to fit the inline budget. Shared by 3 identical private
     * paths of {@code makeFromMatrix}; reached only through it.
     */
    private void makeFromMatrix_s4398bb65_520_fma(float[] dd, float _ld0, float _ld1, float _ld2, float _ld3, float _ld4, float _ld5, float _ld6, float _ld7, float _ld8, float _ct0, float _ct1, float _ct2) {
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        float _t18 = _ld8 * _t17;
        float _t19 = _ld1 * _t16;
        float _t20 = _ld5 * _t16;
        float _t21 = _ld6 * _t17;
        float _t23 = _ld2 * _t15;
        float _t24 = _ld0 * _t15;
        float _t26 = _ld7 * _t17;
        float _t47, _t48, _t49;
        if (java.lang.Math.fma(-java.lang.Math.fma(_t18, _t19, -(_t20 * _t21)), _ld3 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t18, _t23, -(_t24 * _t21)), _ld4 * _t16, java.lang.Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        float _t51 = 1.0f + _t47;
        float _t63 = java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t51));
        makeFromMatrix_s4398bb65_520_sd831e5a7_1_fma(dd, _ld0, _ld1, -_ld0, -_ld1, _t15, _t16, _t19, _t24, java.lang.Math.fma(_ld5, _t16, _t23), java.lang.Math.fma(_ld2, _t15, -_t20), _t47, _t51, 1.0f - _t47, java.lang.Math.fma(_ld3, _t15, _t48), java.lang.Math.fma(_ld4, _t16, _t49), java.lang.Math.fma(_ld4, _t16, -_t49), java.lang.Math.fma(-_ld3, _t15, _t48), _t63, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)));
    }

    /**
     * Part of {@code makeFromMatrix}, split to fit the inline budget. Shared by 3 identical private
     * paths of {@code makeFromMatrix}; reached only through it.
     */
    private void makeFromMatrix_s4398bb65_520_mulAdd(float[] dd, float _ld0, float _ld1, float _ld2, float _ld3, float _ld4, float _ld5, float _ld6, float _ld7, float _ld8, float _ct0, float _ct1, float _ct2) {
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        float _t18 = _ld8 * _t17;
        float _t19 = _ld1 * _t16;
        float _t20 = _ld5 * _t16;
        float _t21 = _ld6 * _t17;
        float _t23 = _ld2 * _t15;
        float _t24 = _ld0 * _t15;
        float _t26 = _ld7 * _t17;
        float _t47, _t48, _t49;
        if (((((((_t18) * (_t23) - (_t24 * _t21))) * (_ld4 * _t16) + (((_t24) * (_t19) - (_t20 * _t23)) * _t26))) - ((_t18) * (_t19) - (_t20 * _t21)) * (_ld3 * _t15)) < 0.0f) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        float _t51 = 1.0f + _t47;
        float _t63 = ((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t51))));
        makeFromMatrix_s4398bb65_520_sd831e5a7_1_mulAdd(dd, _ld0, _ld1, -_ld0, -_ld1, _t15, _t16, _t19, _t24, ((_ld5) * (_t16) + (_t23)), ((_ld2) * (_t15) - (_t20)), _t47, _t51, 1.0f - _t47, ((_ld3) * (_t15) + (_t48)), ((_ld4) * (_t16) + (_t49)), ((_ld4) * (_t16) - (_t49)), ((_t48) - (_ld3) * (_t15)), _t63, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)));
    }

    /**
     * Piece 2 of {@code makeFromMatrix_s4398bb65_520}, split to fit the inline budget. Shared by 3
     * identical private paths of {@code makeFromMatrix}; reached only through it.
     */
    private void makeFromMatrix_s4398bb65_520_sd831e5a7_1_fma(float[] dd, float _ld0, float _ld1, float _t0, float _t1, float _t15, float _t16, float _t19, float _t24, float _t31, float _t35, float _t47, float _t51, float _t52, float _t54, float _t55, float _t56, float _t57, float _t63, float _sp0) {
        float _t65 = java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        float _t66 = java.lang.Math.fma(_ld1, _t16, java.lang.Math.fma(_t0, _t15, _t52));
        float _t67 = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t51));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        if (java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t47)) > 0.0f) {
            dd[3] = _sp0 * _t35;
            dd[4] = _sp0 * _t56;
            dd[5] = _sp0 * _t57;
            dd[6] = 0.5f * (float) java.lang.Math.sqrt(_t63);
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t67);
                dd[4] = _sp3 * _t54;
                dd[5] = _sp3 * _t55;
                dd[6] = _sp3 * _t35;
            } else {
                if (_t24 > _t19) {
                    dd[3] = _sp1 * _t54;
                    dd[4] = 0.5f * (float) java.lang.Math.sqrt(_t65);
                    dd[5] = _sp1 * _t31;
                    dd[6] = _sp1 * _t56;
                } else {
                    dd[3] = _sp2 * _t55;
                    dd[4] = _sp2 * _t31;
                    dd[5] = 0.5f * (float) java.lang.Math.sqrt(_t66);
                    dd[6] = _sp2 * _t57;
                }
            }
        }
    }

    /**
     * Piece 2 of {@code makeFromMatrix_s4398bb65_520}, split to fit the inline budget. Shared by 3
     * identical private paths of {@code makeFromMatrix}; reached only through it.
     */
    private void makeFromMatrix_s4398bb65_520_sd831e5a7_1_mulAdd(float[] dd, float _ld0, float _ld1, float _t0, float _t1, float _t15, float _t16, float _t19, float _t24, float _t31, float _t35, float _t47, float _t51, float _t52, float _t54, float _t55, float _t56, float _t57, float _t63, float _sp0) {
        float _t65 = ((_ld0) * (_t15) + (((_t1) * (_t16) + (_t52))));
        float _t66 = ((_ld1) * (_t16) + (((_t0) * (_t15) + (_t52))));
        float _t67 = ((_t0) * (_t15) + (((_t1) * (_t16) + (_t51))));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        if (((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t47)))) > 0.0f) {
            dd[3] = _sp0 * _t35;
            dd[4] = _sp0 * _t56;
            dd[5] = _sp0 * _t57;
            dd[6] = 0.5f * (float) java.lang.Math.sqrt(_t63);
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t67);
                dd[4] = _sp3 * _t54;
                dd[5] = _sp3 * _t55;
                dd[6] = _sp3 * _t35;
            } else {
                if (_t24 > _t19) {
                    dd[3] = _sp1 * _t54;
                    dd[4] = 0.5f * (float) java.lang.Math.sqrt(_t65);
                    dd[5] = _sp1 * _t31;
                    dd[6] = _sp1 * _t56;
                } else {
                    dd[3] = _sp2 * _t55;
                    dd[4] = _sp2 * _t31;
                    dd[5] = 0.5f * (float) java.lang.Math.sqrt(_t66);
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
    @Mutated private FloatRigid makeFromMatrix_degenerate_fma(Float3x3R m) {
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
        float _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t27));
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
        float _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t75, _t75, java.lang.Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t76, _t76, java.lang.Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t89, _t89, _t74 * _t74))));
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
                    _t166 = java.lang.Math.fma(_t33, _t102, -(_t34 * _t106));
                    _t168 = java.lang.Math.fma(_t35, _t106, -_t33 * _t116);
                    _t171 = java.lang.Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t165 = java.lang.Math.fma(_t36, _t105, -_t37 * _t114);
                    _t167 = java.lang.Math.fma(_t37, _t103, -(_t38 * _t105));
                    _t170 = java.lang.Math.fma(_t38, _t114, -(_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = java.lang.Math.fma(_t33, _t36, _t35 * -_t37);
                    _t167 = java.lang.Math.fma(_t34, _t37, -_t33 * _t38);
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t115;
                    _t168 = _t104;
                    _t171 = _t107;
                    _t169 = java.lang.Math.fma(_t39, _t115, -(_t41 * _t104));
                    _t172 = java.lang.Math.fma(_t40, _t104, -(_t39 * _t107));
                    _t173 = java.lang.Math.fma(_t41, _t107, -_t40 * _t115);
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = java.lang.Math.fma(_t33, _t39, -(_t34 * _t40));
                    _t168 = java.lang.Math.fma(_t35, _t40, -_t33 * _t41);
                    _t171 = java.lang.Math.fma(_t34, _t41, -(_t35 * _t39));
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
                    _t169 = java.lang.Math.fma(_t39, _t36, -(_t41 * _t38));
                    _t172 = java.lang.Math.fma(_t40, _t38, -(_t39 * _t37));
                    _t173 = java.lang.Math.fma(_t41, _t37, -_t40 * _t36);
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
        if (java.lang.Math.fma(java.lang.Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, java.lang.Math.fma(java.lang.Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, java.lang.Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0f) {
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
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t208));
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        if (_t206 > 0.0f) {
            dd[3] = _sp0 * _t182;
            dd[4] = _sp0 * _t201;
            dd[5] = _sp0 * _t202;
            dd[6] = 0.5f * (float) java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t208);
                dd[4] = _sp3 * _t199;
                dd[5] = _sp3 * _t200;
                dd[6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dd[3] = _sp1 * _t199;
                    dd[4] = 0.5f * (float) java.lang.Math.sqrt(_t209);
                    dd[5] = _sp1 * _t184;
                    dd[6] = _sp1 * _t201;
                } else {
                    dd[3] = _sp2 * _t200;
                    dd[4] = _sp2 * _t184;
                    dd[5] = 0.5f * (float) java.lang.Math.sqrt(_t210);
                    dd[6] = _sp2 * _t202;
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
    @Mutated private FloatRigid makeFromMatrix_degenerate_mulAdd(Float3x3R m) {
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
        float _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        float _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t27));
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
        float _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) java.lang.Math.sqrt(((_t75) * (_t75) + (((_t87) * (_t87) + (_t72 * _t72))))));
        float _t100 = (1.0f / (float) java.lang.Math.sqrt(((_t76) * (_t76) + (((_t88) * (_t88) + (_t73 * _t73))))));
        float _t101 = (1.0f / (float) java.lang.Math.sqrt(((_t77) * (_t77) + (((_t89) * (_t89) + (_t74 * _t74))))));
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
                    _t166 = ((_t33) * (_t102) - (_t34 * _t106));
                    _t168 = ((_t35) * (_t106) - (_t33 * _t116));
                    _t171 = ((_t34) * (_t116) - (_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t182 = _t170 - _t166;
        float _t184 = _t170 + _t166;
        float _t194, _t195, _t196;
        if (((((_t165) * (_t166) - (_t167 * _t168))) * (_t169) + (((((_t170) * (_t168) - (_t165 * _t171))) * (_t172) + (((_t167) * (_t171) - (_t170 * _t166)) * _t173)))) < 0.0f) {
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
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t208));
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        if (_t206 > 0.0f) {
            dd[3] = _sp0 * _t182;
            dd[4] = _sp0 * _t201;
            dd[5] = _sp0 * _t202;
            dd[6] = 0.5f * (float) java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t208);
                dd[4] = _sp3 * _t199;
                dd[5] = _sp3 * _t200;
                dd[6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dd[3] = _sp1 * _t199;
                    dd[4] = 0.5f * (float) java.lang.Math.sqrt(_t209);
                    dd[5] = _sp1 * _t184;
                    dd[6] = _sp1 * _t201;
                } else {
                    dd[3] = _sp2 * _t200;
                    dd[4] = _sp2 * _t184;
                    dd[5] = 0.5f * (float) java.lang.Math.sqrt(_t210);
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
     * rotation quaternion that is not unit length; a mirroring block (negative determinant) loses
     * its mirror: the rotation is that of the block with its first column negated).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public FloatRigid makeFromMatrix(Float3x4R m) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeFromMatrix_mulAdd(m);
        float[] dd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        float _ld0 = mData[5];
        float _ld1 = mData[10];
        float _ld2 = mData[9];
        float _ld3 = mData[1];
        float _ld4 = mData[2];
        float _ld5 = mData[6];
        float _ld6 = mData[8];
        float _ld7 = mData[0];
        float _ld8 = mData[4];
        float _ld10 = mData[7];
        float _ld11 = mData[11];
        float _ct0 = java.lang.Math.fma(_ld2, _ld2, java.lang.Math.fma(_ld3, _ld3, _ld0 * _ld0));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _ct1 = java.lang.Math.fma(_ld1, _ld1, java.lang.Math.fma(_ld4, _ld4, _ld5 * _ld5));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _ct2 = java.lang.Math.fma(_ld6, _ld6, java.lang.Math.fma(_ld7, _ld7, _ld8 * _ld8));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        dd[0] = mData[3];
        dd[1] = _ld10;
        dd[2] = _ld11;
        makeFromMatrix_s4398bb65_520_fma(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ct0, _ct1, _ct2);
        return this;
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid makeFromMatrix_mulAdd(Float3x4R m) {
        float[] dd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        float _ld0 = mData[5];
        float _ld1 = mData[10];
        float _ld2 = mData[9];
        float _ld3 = mData[1];
        float _ld4 = mData[2];
        float _ld5 = mData[6];
        float _ld6 = mData[8];
        float _ld7 = mData[0];
        float _ld8 = mData[4];
        float _ld10 = mData[7];
        float _ld11 = mData[11];
        float _ct0 = ((_ld2) * (_ld2) + (((_ld3) * (_ld3) + (_ld0 * _ld0))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _ct1 = ((_ld1) * (_ld1) + (((_ld4) * (_ld4) + (_ld5 * _ld5))));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _ct2 = ((_ld6) * (_ld6) + (((_ld7) * (_ld7) + (_ld8 * _ld8))));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        dd[0] = mData[3];
        dd[1] = _ld10;
        dd[2] = _ld11;
        makeFromMatrix_s4398bb65_520_mulAdd(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ct0, _ct1, _ct2);
        return this;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatRigid makeFromMatrix_degenerate_fma(Float3x4R m) {
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
        float _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t27));
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
        float _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t75, _t75, java.lang.Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t76, _t76, java.lang.Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t89, _t89, _t74 * _t74))));
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
                    _t166 = java.lang.Math.fma(_t33, _t102, -(_t34 * _t106));
                    _t168 = java.lang.Math.fma(_t35, _t106, -_t33 * _t116);
                    _t171 = java.lang.Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t165 = java.lang.Math.fma(_t36, _t105, -_t37 * _t114);
                    _t167 = java.lang.Math.fma(_t37, _t103, -(_t38 * _t105));
                    _t170 = java.lang.Math.fma(_t38, _t114, -(_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = java.lang.Math.fma(_t33, _t36, _t35 * -_t37);
                    _t167 = java.lang.Math.fma(_t34, _t37, -_t33 * _t38);
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t115;
                    _t168 = _t104;
                    _t171 = _t107;
                    _t169 = java.lang.Math.fma(_t39, _t115, -(_t41 * _t104));
                    _t172 = java.lang.Math.fma(_t40, _t104, -(_t39 * _t107));
                    _t173 = java.lang.Math.fma(_t41, _t107, -_t40 * _t115);
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = java.lang.Math.fma(_t33, _t39, -(_t34 * _t40));
                    _t168 = java.lang.Math.fma(_t35, _t40, -_t33 * _t41);
                    _t171 = java.lang.Math.fma(_t34, _t41, -(_t35 * _t39));
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
                    _t169 = java.lang.Math.fma(_t39, _t36, -(_t41 * _t38));
                    _t172 = java.lang.Math.fma(_t40, _t38, -(_t39 * _t37));
                    _t173 = java.lang.Math.fma(_t41, _t37, -_t40 * _t36);
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
        if (java.lang.Math.fma(java.lang.Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, java.lang.Math.fma(java.lang.Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, java.lang.Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0f) {
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
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t208));
        dd[0] = mData[3];
        dd[1] = mData[7];
        dd[2] = mData[11];
        if (_t206 > 0.0f) {
            dd[3] = _sp0 * _t182;
            dd[4] = _sp0 * _t201;
            dd[5] = _sp0 * _t202;
            dd[6] = 0.5f * (float) java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t208);
                dd[4] = _sp3 * _t199;
                dd[5] = _sp3 * _t200;
                dd[6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dd[3] = _sp1 * _t199;
                    dd[4] = 0.5f * (float) java.lang.Math.sqrt(_t209);
                    dd[5] = _sp1 * _t184;
                    dd[6] = _sp1 * _t201;
                } else {
                    dd[3] = _sp2 * _t200;
                    dd[4] = _sp2 * _t184;
                    dd[5] = 0.5f * (float) java.lang.Math.sqrt(_t210);
                    dd[6] = _sp2 * _t202;
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
    @Mutated private FloatRigid makeFromMatrix_degenerate_mulAdd(Float3x4R m) {
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
        float _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        float _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t27));
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
        float _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) java.lang.Math.sqrt(((_t75) * (_t75) + (((_t87) * (_t87) + (_t72 * _t72))))));
        float _t100 = (1.0f / (float) java.lang.Math.sqrt(((_t76) * (_t76) + (((_t88) * (_t88) + (_t73 * _t73))))));
        float _t101 = (1.0f / (float) java.lang.Math.sqrt(((_t77) * (_t77) + (((_t89) * (_t89) + (_t74 * _t74))))));
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
                    _t166 = ((_t33) * (_t102) - (_t34 * _t106));
                    _t168 = ((_t35) * (_t106) - (_t33 * _t116));
                    _t171 = ((_t34) * (_t116) - (_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t182 = _t170 - _t166;
        float _t184 = _t170 + _t166;
        float _t194, _t195, _t196;
        if (((((_t165) * (_t166) - (_t167 * _t168))) * (_t169) + (((((_t170) * (_t168) - (_t165 * _t171))) * (_t172) + (((_t167) * (_t171) - (_t170 * _t166)) * _t173)))) < 0.0f) {
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
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t208));
        dd[0] = mData[3];
        dd[1] = mData[7];
        dd[2] = mData[11];
        if (_t206 > 0.0f) {
            dd[3] = _sp0 * _t182;
            dd[4] = _sp0 * _t201;
            dd[5] = _sp0 * _t202;
            dd[6] = 0.5f * (float) java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t208);
                dd[4] = _sp3 * _t199;
                dd[5] = _sp3 * _t200;
                dd[6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dd[3] = _sp1 * _t199;
                    dd[4] = 0.5f * (float) java.lang.Math.sqrt(_t209);
                    dd[5] = _sp1 * _t184;
                    dd[6] = _sp1 * _t201;
                } else {
                    dd[3] = _sp2 * _t200;
                    dd[4] = _sp2 * _t184;
                    dd[5] = 0.5f * (float) java.lang.Math.sqrt(_t210);
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
     * rotation quaternion that is not unit length; a mirroring block (negative determinant) loses
     * its mirror: the rotation is that of the block with its first column negated).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public FloatRigid makeFromMatrix(Float4x4R m) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeFromMatrix_mulAdd(m);
        float[] dd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        float _ld0 = mData[5];
        float _ld1 = mData[10];
        float _ld2 = mData[6];
        float _ld3 = mData[4];
        float _ld4 = mData[8];
        float _ld5 = mData[9];
        float _ld6 = mData[2];
        float _ld7 = mData[0];
        float _ld8 = mData[1];
        float _ld10 = mData[13];
        float _ld11 = mData[14];
        float _ct0 = java.lang.Math.fma(_ld2, _ld2, java.lang.Math.fma(_ld3, _ld3, _ld0 * _ld0));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _ct1 = java.lang.Math.fma(_ld1, _ld1, java.lang.Math.fma(_ld4, _ld4, _ld5 * _ld5));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _ct2 = java.lang.Math.fma(_ld6, _ld6, java.lang.Math.fma(_ld7, _ld7, _ld8 * _ld8));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        dd[0] = mData[12];
        dd[1] = _ld10;
        dd[2] = _ld11;
        makeFromMatrix_s4398bb65_520_fma(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ct0, _ct1, _ct2);
        return this;
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid makeFromMatrix_mulAdd(Float4x4R m) {
        float[] dd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        float _ld0 = mData[5];
        float _ld1 = mData[10];
        float _ld2 = mData[6];
        float _ld3 = mData[4];
        float _ld4 = mData[8];
        float _ld5 = mData[9];
        float _ld6 = mData[2];
        float _ld7 = mData[0];
        float _ld8 = mData[1];
        float _ld10 = mData[13];
        float _ld11 = mData[14];
        float _ct0 = ((_ld2) * (_ld2) + (((_ld3) * (_ld3) + (_ld0 * _ld0))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _ct1 = ((_ld1) * (_ld1) + (((_ld4) * (_ld4) + (_ld5 * _ld5))));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _ct2 = ((_ld6) * (_ld6) + (((_ld7) * (_ld7) + (_ld8 * _ld8))));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        dd[0] = mData[12];
        dd[1] = _ld10;
        dd[2] = _ld11;
        makeFromMatrix_s4398bb65_520_mulAdd(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ct0, _ct1, _ct2);
        return this;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatRigid makeFromMatrix_degenerate_fma(Float4x4R m) {
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
        float _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t27));
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
        float _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t75, _t75, java.lang.Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t76, _t76, java.lang.Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t89, _t89, _t74 * _t74))));
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
                    _t166 = java.lang.Math.fma(_t33, _t102, -(_t34 * _t106));
                    _t168 = java.lang.Math.fma(_t35, _t106, -_t33 * _t116);
                    _t171 = java.lang.Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t165 = java.lang.Math.fma(_t36, _t105, -_t37 * _t114);
                    _t167 = java.lang.Math.fma(_t37, _t103, -(_t38 * _t105));
                    _t170 = java.lang.Math.fma(_t38, _t114, -(_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = java.lang.Math.fma(_t33, _t36, _t35 * -_t37);
                    _t167 = java.lang.Math.fma(_t34, _t37, -_t33 * _t38);
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t115;
                    _t168 = _t104;
                    _t171 = _t107;
                    _t169 = java.lang.Math.fma(_t39, _t115, -(_t41 * _t104));
                    _t172 = java.lang.Math.fma(_t40, _t104, -(_t39 * _t107));
                    _t173 = java.lang.Math.fma(_t41, _t107, -_t40 * _t115);
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = java.lang.Math.fma(_t33, _t39, -(_t34 * _t40));
                    _t168 = java.lang.Math.fma(_t35, _t40, -_t33 * _t41);
                    _t171 = java.lang.Math.fma(_t34, _t41, -(_t35 * _t39));
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
                    _t169 = java.lang.Math.fma(_t39, _t36, -(_t41 * _t38));
                    _t172 = java.lang.Math.fma(_t40, _t38, -(_t39 * _t37));
                    _t173 = java.lang.Math.fma(_t41, _t37, -_t40 * _t36);
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
        if (java.lang.Math.fma(java.lang.Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, java.lang.Math.fma(java.lang.Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, java.lang.Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0f) {
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
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t208));
        dd[0] = mData[12];
        dd[1] = mData[13];
        dd[2] = mData[14];
        if (_t206 > 0.0f) {
            dd[3] = _sp0 * _t182;
            dd[4] = _sp0 * _t201;
            dd[5] = _sp0 * _t202;
            dd[6] = 0.5f * (float) java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t208);
                dd[4] = _sp3 * _t199;
                dd[5] = _sp3 * _t200;
                dd[6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dd[3] = _sp1 * _t199;
                    dd[4] = 0.5f * (float) java.lang.Math.sqrt(_t209);
                    dd[5] = _sp1 * _t184;
                    dd[6] = _sp1 * _t201;
                } else {
                    dd[3] = _sp2 * _t200;
                    dd[4] = _sp2 * _t184;
                    dd[5] = 0.5f * (float) java.lang.Math.sqrt(_t210);
                    dd[6] = _sp2 * _t202;
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
    @Mutated private FloatRigid makeFromMatrix_degenerate_mulAdd(Float4x4R m) {
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
        float _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        float _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t27));
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
        float _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) java.lang.Math.sqrt(((_t75) * (_t75) + (((_t87) * (_t87) + (_t72 * _t72))))));
        float _t100 = (1.0f / (float) java.lang.Math.sqrt(((_t76) * (_t76) + (((_t88) * (_t88) + (_t73 * _t73))))));
        float _t101 = (1.0f / (float) java.lang.Math.sqrt(((_t77) * (_t77) + (((_t89) * (_t89) + (_t74 * _t74))))));
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
                    _t166 = ((_t33) * (_t102) - (_t34 * _t106));
                    _t168 = ((_t35) * (_t106) - (_t33 * _t116));
                    _t171 = ((_t34) * (_t116) - (_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t182 = _t170 - _t166;
        float _t184 = _t170 + _t166;
        float _t194, _t195, _t196;
        if (((((_t165) * (_t166) - (_t167 * _t168))) * (_t169) + (((((_t170) * (_t168) - (_t165 * _t171))) * (_t172) + (((_t167) * (_t171) - (_t170 * _t166)) * _t173)))) < 0.0f) {
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
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t208));
        dd[0] = mData[12];
        dd[1] = mData[13];
        dd[2] = mData[14];
        if (_t206 > 0.0f) {
            dd[3] = _sp0 * _t182;
            dd[4] = _sp0 * _t201;
            dd[5] = _sp0 * _t202;
            dd[6] = 0.5f * (float) java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dd[3] = 0.5f * (float) java.lang.Math.sqrt(_t208);
                dd[4] = _sp3 * _t199;
                dd[5] = _sp3 * _t200;
                dd[6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dd[3] = _sp1 * _t199;
                    dd[4] = 0.5f * (float) java.lang.Math.sqrt(_t209);
                    dd[5] = _sp1 * _t184;
                    dd[6] = _sp1 * _t201;
                } else {
                    dd[3] = _sp2 * _t200;
                    dd[4] = _sp2 * _t184;
                    dd[5] = 0.5f * (float) java.lang.Math.sqrt(_t210);
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
    @Mutated public FloatRigid makeFromTransform(FloatTransformR t) {
        float[] tData = ((FloatTransformImpl) t).data;
        float tTY = tData[1];
        float tTZ = tData[2];
        float tRX = tData[3];
        float tRY = tData[4];
        float tRZ = tData[5];
        float tRW = tData[6];
        float tSX = tData[7];
        float tSY = tData[8];
        float tSZ = tData[9];
        float[] dd = this.data;
        dd[0] = tData[0];
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
        if (!org.joml2.internal.FmaSwitch.ON) return toDualQuat_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -sd[2];
        FloatVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 0);
        dd[4] = 0.5f * java.lang.Math.fma(_t0, sd[4], java.lang.Math.fma(sd[0], sd[6], sd[1] * sd[5]));
        dd[5] = 0.5f * java.lang.Math.fma(sd[2], sd[3], java.lang.Math.fma(sd[1], sd[6], -sd[0] * sd[5]));
        dd[6] = 0.5f * java.lang.Math.fma(sd[2], sd[6], java.lang.Math.fma(sd[0], sd[4], -sd[1] * sd[3]));
        dd[7] = 0.5f * java.lang.Math.fma(_t0, sd[5], java.lang.Math.fma(-sd[1], sd[4], -sd[0] * sd[3]));
        return dest;
    }

    /** {@code toDualQuat} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat toDualQuat_mulAdd(@Mutated FloatDualQuat dest) {
        float[] sd = this.data;
        float[] dd = ((FloatDualQuatImpl) dest).data;
        float _t0 = -sd[2];
        FloatVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 0);
        dd[4] = 0.5f * ((_t0) * (sd[4]) + (((sd[0]) * (sd[6]) + (sd[1] * sd[5]))));
        dd[5] = 0.5f * ((sd[2]) * (sd[3]) + (((sd[1]) * (sd[6]) - (sd[0] * sd[5]))));
        dd[6] = 0.5f * ((sd[2]) * (sd[6]) + (((sd[0]) * (sd[4]) - (sd[1] * sd[3]))));
        dd[7] = 0.5f * ((_t0) * (sd[5]) + (((-sd[1]) * (sd[4]) - (sd[0] * sd[3]))));
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
        if (!org.joml2.internal.FmaSwitch.ON) return toDualQuat_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = -sd[2];
        dd[0] = sd[3];
        dd[1] = sd[4];
        dd[2] = sd[5];
        dd[3] = sd[6];
        dd[4] = 0.5f * java.lang.Math.fma(_t0, sd[4], java.lang.Math.fma(sd[0], sd[6], sd[1] * sd[5]));
        dd[5] = 0.5f * java.lang.Math.fma(sd[2], sd[3], java.lang.Math.fma(sd[1], sd[6], -sd[0] * sd[5]));
        dd[6] = 0.5f * java.lang.Math.fma(sd[2], sd[6], java.lang.Math.fma(sd[0], sd[4], -sd[1] * sd[3]));
        dd[7] = 0.5f * java.lang.Math.fma(_t0, sd[5], java.lang.Math.fma(-sd[1], sd[4], -sd[0] * sd[3]));
        return dest;
    }

    /** {@code toDualQuat} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat toDualQuat_mulAdd(@Mutated DoubleDualQuat dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        float _t0 = -sd[2];
        dd[0] = sd[3];
        dd[1] = sd[4];
        dd[2] = sd[5];
        dd[3] = sd[6];
        dd[4] = 0.5f * ((_t0) * (sd[4]) + (((sd[0]) * (sd[6]) + (sd[1] * sd[5]))));
        dd[5] = 0.5f * ((sd[2]) * (sd[3]) + (((sd[1]) * (sd[6]) - (sd[0] * sd[5]))));
        dd[6] = 0.5f * ((sd[2]) * (sd[6]) + (((sd[0]) * (sd[4]) - (sd[1] * sd[3]))));
        dd[7] = 0.5f * ((_t0) * (sd[5]) + (((-sd[1]) * (sd[4]) - (sd[0] * sd[3]))));
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
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((Float4x4Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        dd[0] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], _t0), 1.0f);
        dd[1] = 2.0f * java.lang.Math.fma(sd[3], sd[4], _t1);
        dd[2] = 2.0f * java.lang.Math.fma(sd[3], sd[5], -_t2);
        dd[3] = 0.0f;
        dd[4] = 2.0f * java.lang.Math.fma(sd[3], sd[4], -_t1);
        dd[5] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], _t0), 1.0f);
        dd[6] = 2.0f * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        dd[7] = 0.0f;
        dd[8] = 2.0f * java.lang.Math.fma(sd[3], sd[5], _t2);
        dd[9] = 2.0f * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6]));
        dd[10] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        dd[11] = 0.0f;
        dd[12] = sd[0];
        dd[13] = sd[1];
        dd[14] = sd[2];
        dd[15] = 1.0f;
        ((Float4x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x4 toMatrix_mulAdd(@Mutated Float4x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x4Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        dd[0] = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (_t0))));
        dd[1] = 2.0f * ((sd[3]) * (sd[4]) + (_t1));
        dd[2] = 2.0f * ((sd[3]) * (sd[5]) - (_t2));
        dd[3] = 0.0f;
        dd[4] = 2.0f * ((sd[3]) * (sd[4]) - (_t1));
        dd[5] = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (_t0))));
        dd[6] = 2.0f * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]));
        dd[7] = 0.0f;
        dd[8] = 2.0f * ((sd[3]) * (sd[5]) + (_t2));
        dd[9] = 2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6]));
        dd[10] = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        dd[11] = 0.0f;
        dd[12] = sd[0];
        dd[13] = sd[1];
        dd[14] = sd[2];
        dd[15] = 1.0f;
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
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        dd[0] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], _t0), 1.0f);
        dd[1] = 2.0f * java.lang.Math.fma(sd[3], sd[4], _t1);
        dd[2] = 2.0f * java.lang.Math.fma(sd[3], sd[5], -_t2);
        dd[3] = 0.0f;
        dd[4] = 2.0f * java.lang.Math.fma(sd[3], sd[4], -_t1);
        dd[5] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], _t0), 1.0f);
        dd[6] = 2.0f * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        dd[7] = 0.0f;
        dd[8] = 2.0f * java.lang.Math.fma(sd[3], sd[5], _t2);
        dd[9] = 2.0f * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6]));
        dd[10] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        dd[11] = 0.0f;
        dd[12] = sd[0];
        dd[13] = sd[1];
        dd[14] = sd[2];
        dd[15] = 1.0f;
        ((Double4x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x4 toMatrix_mulAdd(@Mutated Double4x4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        dd[0] = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (_t0))));
        dd[1] = 2.0f * ((sd[3]) * (sd[4]) + (_t1));
        dd[2] = 2.0f * ((sd[3]) * (sd[5]) - (_t2));
        dd[3] = 0.0f;
        dd[4] = 2.0f * ((sd[3]) * (sd[4]) - (_t1));
        dd[5] = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (_t0))));
        dd[6] = 2.0f * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]));
        dd[7] = 0.0f;
        dd[8] = 2.0f * ((sd[3]) * (sd[5]) + (_t2));
        dd[9] = 2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6]));
        dd[10] = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        dd[11] = 0.0f;
        dd[12] = sd[0];
        dd[13] = sd[1];
        dd[14] = sd[2];
        dd[15] = 1.0f;
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
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix3x3_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        dd[0] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], _t0), 1.0f);
        dd[1] = 2.0f * java.lang.Math.fma(sd[3], sd[4], _t1);
        dd[2] = 2.0f * java.lang.Math.fma(sd[3], sd[5], -_t2);
        dd[3] = 2.0f * java.lang.Math.fma(sd[3], sd[4], -_t1);
        dd[4] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], _t0), 1.0f);
        dd[5] = 2.0f * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        dd[6] = 2.0f * java.lang.Math.fma(sd[3], sd[5], _t2);
        dd[7] = 2.0f * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6]));
        dd[8] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }

    /** {@code toMatrix3x3} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x3 toMatrix3x3_mulAdd(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        dd[0] = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (_t0))));
        dd[1] = 2.0f * ((sd[3]) * (sd[4]) + (_t1));
        dd[2] = 2.0f * ((sd[3]) * (sd[5]) - (_t2));
        dd[3] = 2.0f * ((sd[3]) * (sd[4]) - (_t1));
        dd[4] = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (_t0))));
        dd[5] = 2.0f * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]));
        dd[6] = 2.0f * ((sd[3]) * (sd[5]) + (_t2));
        dd[7] = 2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6]));
        dd[8] = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
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
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix3x3_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        dd[0] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], _t0), 1.0f);
        dd[1] = 2.0f * java.lang.Math.fma(sd[3], sd[4], _t1);
        dd[2] = 2.0f * java.lang.Math.fma(sd[3], sd[5], -_t2);
        dd[3] = 2.0f * java.lang.Math.fma(sd[3], sd[4], -_t1);
        dd[4] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], _t0), 1.0f);
        dd[5] = 2.0f * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        dd[6] = 2.0f * java.lang.Math.fma(sd[3], sd[5], _t2);
        dd[7] = 2.0f * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6]));
        dd[8] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }

    /** {@code toMatrix3x3} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x3 toMatrix3x3_mulAdd(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        dd[0] = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (_t0))));
        dd[1] = 2.0f * ((sd[3]) * (sd[4]) + (_t1));
        dd[2] = 2.0f * ((sd[3]) * (sd[5]) - (_t2));
        dd[3] = 2.0f * ((sd[3]) * (sd[4]) - (_t1));
        dd[4] = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (_t0))));
        dd[5] = 2.0f * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]));
        dd[6] = 2.0f * ((sd[3]) * (sd[5]) + (_t2));
        dd[7] = 2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6]));
        dd[8] = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
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
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix3x4_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((Float3x4Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        dd[0] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], _t0), 1.0f);
        dd[1] = 2.0f * java.lang.Math.fma(sd[3], sd[4], -_t1);
        dd[2] = 2.0f * java.lang.Math.fma(sd[3], sd[5], _t2);
        dd[3] = sd[0];
        dd[4] = 2.0f * java.lang.Math.fma(sd[3], sd[4], _t1);
        dd[5] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], _t0), 1.0f);
        dd[6] = 2.0f * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6]));
        dd[7] = sd[1];
        dd[8] = 2.0f * java.lang.Math.fma(sd[3], sd[5], -_t2);
        dd[9] = 2.0f * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        dd[10] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        dd[11] = sd[2];
        ((Float3x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x4 toMatrix3x4_mulAdd(@Mutated Float3x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x4Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        dd[0] = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (_t0))));
        dd[1] = 2.0f * ((sd[3]) * (sd[4]) - (_t1));
        dd[2] = 2.0f * ((sd[3]) * (sd[5]) + (_t2));
        dd[3] = sd[0];
        dd[4] = 2.0f * ((sd[3]) * (sd[4]) + (_t1));
        dd[5] = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (_t0))));
        dd[6] = 2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6]));
        dd[7] = sd[1];
        dd[8] = 2.0f * ((sd[3]) * (sd[5]) - (_t2));
        dd[9] = 2.0f * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]));
        dd[10] = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        dd[11] = sd[2];
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
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix3x4_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        dd[0] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], _t0), 1.0f);
        dd[1] = 2.0f * java.lang.Math.fma(sd[3], sd[4], -_t1);
        dd[2] = 2.0f * java.lang.Math.fma(sd[3], sd[5], _t2);
        dd[3] = sd[0];
        dd[4] = 2.0f * java.lang.Math.fma(sd[3], sd[4], _t1);
        dd[5] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], _t0), 1.0f);
        dd[6] = 2.0f * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6]));
        dd[7] = sd[1];
        dd[8] = 2.0f * java.lang.Math.fma(sd[3], sd[5], -_t2);
        dd[9] = 2.0f * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        dd[10] = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        dd[11] = sd[2];
        ((Double3x4Impl) dest).properties = Joml.BIT_ORTHOGONAL;
        return dest;
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x4 toMatrix3x4_mulAdd(@Mutated Double3x4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t2 = sd[4] * sd[6];
        dd[0] = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (_t0))));
        dd[1] = 2.0f * ((sd[3]) * (sd[4]) - (_t1));
        dd[2] = 2.0f * ((sd[3]) * (sd[5]) + (_t2));
        dd[3] = sd[0];
        dd[4] = 2.0f * ((sd[3]) * (sd[4]) + (_t1));
        dd[5] = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (_t0))));
        dd[6] = 2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6]));
        dd[7] = sd[1];
        dd[8] = 2.0f * ((sd[3]) * (sd[5]) - (_t2));
        dd[9] = 2.0f * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]));
        dd[10] = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
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
    @Mutated public FloatRigid set(FloatQuatR rotation) {
        float[] rotationData = ((FloatQuatImpl) rotation).data;
        float rotationX = rotationData[0];
        float rotationY = rotationData[1];
        float rotationZ = rotationData[2];
        float rotationW = rotationData[3];
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
    @Mutated public FloatRigid set(Float3R translation) {
        float[] translationData = ((Float3Impl) translation).data;
        float translationY = translationData[1];
        float translationZ = translationData[2];
        float[] dd = this.data;
        dd[0] = translationData[0];
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
     * {@code t}, interpolating the translation linearly and the rotation via shortest-arc slerp,
     * and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this rigid transform (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (1e8 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length; the rotation of
     * {@code other} must have unit length; {@code t} must lie in {@code [0, 1]}.
     *
     * @param other the rigid transform to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatRigid lerp(FloatRigidR other, float t, @Mutated FloatRigid dest) {
        float[] otherData = ((FloatRigidImpl) other).data;
        float otherTX = otherData[0];
        float otherTY = otherData[1];
        float otherTZ = otherData[2];
        float otherRX = otherData[3];
        float otherRY = otherData[4];
        float otherRZ = otherData[5];
        float otherRW = otherData[6];
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, t, dest);
        float[] sd = this.data;
        float _t12 = java.lang.Math.fma(otherRW, sd[6], java.lang.Math.fma(otherRZ, sd[5], java.lang.Math.fma(otherRX, sd[3], otherRY * sd[4])));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = t * Math.acos(_t15);
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
        float _t30 = (float) java.lang.Math.sqrt(java.lang.Math.fma(-_t15, _t15, 1.0f));
        float _t32 = Math.sinWithCos(_t18) / _t30;
        return lerp_s3c625002_1_fma(otherTX, otherTY, otherTZ, t, dest, sd, ((FloatRigidImpl) dest).data, 1.0f - t, _t21, _t22, _t23, _t24, _t30, _t32, java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18)));
    }

    /**
     * Interpolate between this rigid transform and {@code other} using the interpolation factor
     * {@code t}, interpolating the translation linearly and the rotation via shortest-arc slerp,
     * and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this rigid transform (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (1e8 towards 1 ends
     * at 0).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length; the rotation of
     * {@code other} must have unit length; {@code t} must lie in {@code [0, 1]}.
     *
     * @param other the rigid transform to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid lerp(FloatRigidR other, float t, @Mutated DoubleRigid dest) {
        float[] otherData = ((FloatRigidImpl) other).data;
        float otherTX = otherData[0];
        float otherTY = otherData[1];
        float otherTZ = otherData[2];
        float otherRX = otherData[3];
        float otherRY = otherData[4];
        float otherRZ = otherData[5];
        float otherRW = otherData[6];
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, t, dest);
        float[] sd = this.data;
        float _t12 = java.lang.Math.fma(otherRW, sd[6], java.lang.Math.fma(otherRZ, sd[5], java.lang.Math.fma(otherRX, sd[3], otherRY * sd[4])));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = t * Math.acos(_t15);
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
        float _t30 = (float) java.lang.Math.sqrt(java.lang.Math.fma(-_t15, _t15, 1.0f));
        float _t32 = Math.sinWithCos(_t18) / _t30;
        return lerp_sb5a433c7_1_fma(otherTX, otherTY, otherTZ, t, dest, sd, ((DoubleRigidImpl) dest).data, 1.0f - t, _t21, _t22, _t23, _t24, _t30, _t32, java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18)));
    }

    /**
     * Interpolate between this rigid transform and ({@code otherTX}, {@code otherTY},
     * {@code otherTZ}, {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}) using
     * the interpolation factor {@code t}, interpolating the translation linearly and the rotation
     * via shortest-arc slerp, and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this rigid transform (interpolation factor {@code 0}) and ends at
     * ({@code otherTX}, {@code otherTY}, {@code otherTZ}, {@code otherRX}, {@code otherRY},
     * {@code otherRZ}, {@code otherRW}) (interpolation factor {@code 1}). Each linearly
     * interpolated component is {@code this + (other - this) * t}, as in JOML and glMatrix:
     * monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the rounding
     * of {@code other - this}, which shows when this component is much larger in magnitude than the
     * other one (1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length;
     * {@code (otherRX, otherRY, otherRZ, otherRW)} must have unit length; {@code t} must lie in
     * {@code [0, 1]}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, t, dest);
        float[] sd = this.data;
        float _t12 = java.lang.Math.fma(otherRW, sd[6], java.lang.Math.fma(otherRZ, sd[5], java.lang.Math.fma(otherRX, sd[3], otherRY * sd[4])));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = t * Math.acos(_t15);
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
        float _t30 = (float) java.lang.Math.sqrt(java.lang.Math.fma(-_t15, _t15, 1.0f));
        float _t32 = Math.sinWithCos(_t18) / _t30;
        return lerp_s3c625002_1_fma(otherTX, otherTY, otherTZ, t, dest, sd, ((FloatRigidImpl) dest).data, 1.0f - t, _t21, _t22, _t23, _t24, _t30, _t32, java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18)));
    }

    /** {@code lerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid lerp_mulAdd(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float t, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float _t12 = ((otherRW) * (sd[6]) + (((otherRZ) * (sd[5]) + (((otherRX) * (sd[3]) + (otherRY * sd[4]))))));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = t * Math.acos(_t15);
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
        float _t30 = (float) java.lang.Math.sqrt(((1.0f) - (_t15) * (_t15)));
        float _t32 = Math.sinWithCos(_t18) / _t30;
        return lerp_s3c625002_1_mulAdd(otherTX, otherTY, otherTZ, t, dest, sd, ((FloatRigidImpl) dest).data, 1.0f - t, _t21, _t22, _t23, _t24, _t30, _t32, ((Math.cosWithSin(_t18)) - (_t32) * (_t15)));
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private FloatRigid lerp_s3c625002_1_fma(float otherTX, float otherTY, float otherTZ, float t, FloatRigid dest, float[] sd, float[] dd, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
        float _t43, _t44, _t45, _t46;
        if (_t30 > 0.0f) {
            _t43 = java.lang.Math.fma(sd[6], _t38, _t32 * _t21);
            _t44 = java.lang.Math.fma(sd[5], _t38, _t32 * _t22);
            _t45 = java.lang.Math.fma(sd[3], _t38, _t32 * _t23);
            _t46 = java.lang.Math.fma(sd[4], _t38, _t32 * _t24);
        } else {
            _t43 = java.lang.Math.fma(t, _t21, sd[6] * _t1);
            _t44 = java.lang.Math.fma(t, _t22, sd[5] * _t1);
            _t45 = java.lang.Math.fma(t, _t23, sd[3] * _t1);
            _t46 = java.lang.Math.fma(t, _t24, sd[4] * _t1);
        }
        float _t50 = java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, java.lang.Math.fma(_t45, _t45, _t46 * _t46)));
        float _t51 = (1.0f / (float) java.lang.Math.sqrt(_t50));
        dd[0] = java.lang.Math.fma(t, otherTX - sd[0], sd[0]);
        dd[1] = java.lang.Math.fma(t, otherTY - sd[1], sd[1]);
        dd[2] = java.lang.Math.fma(t, otherTZ - sd[2], sd[2]);
        if (_t50 != 0.0f) {
            dd[3] = _t51 * _t45;
            dd[4] = _t51 * _t46;
            dd[5] = _t51 * _t44;
            dd[6] = _t51 * _t43;
        } else {
            dd[3] = 0.0f;
            dd[4] = 0.0f;
            dd[5] = 0.0f;
            dd[6] = 0.0f;
        }
        return dest;
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private FloatRigid lerp_s3c625002_1_mulAdd(float otherTX, float otherTY, float otherTZ, float t, FloatRigid dest, float[] sd, float[] dd, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
        float _t43, _t44, _t45, _t46;
        if (_t30 > 0.0f) {
            _t43 = ((sd[6]) * (_t38) + (_t32 * _t21));
            _t44 = ((sd[5]) * (_t38) + (_t32 * _t22));
            _t45 = ((sd[3]) * (_t38) + (_t32 * _t23));
            _t46 = ((sd[4]) * (_t38) + (_t32 * _t24));
        } else {
            _t43 = ((t) * (_t21) + (sd[6] * _t1));
            _t44 = ((t) * (_t22) + (sd[5] * _t1));
            _t45 = ((t) * (_t23) + (sd[3] * _t1));
            _t46 = ((t) * (_t24) + (sd[4] * _t1));
        }
        float _t50 = ((_t43) * (_t43) + (((_t44) * (_t44) + (((_t45) * (_t45) + (_t46 * _t46))))));
        float _t51 = (1.0f / (float) java.lang.Math.sqrt(_t50));
        dd[0] = ((t) * (otherTX - sd[0]) + (sd[0]));
        dd[1] = ((t) * (otherTY - sd[1]) + (sd[1]));
        dd[2] = ((t) * (otherTZ - sd[2]) + (sd[2]));
        if (_t50 != 0.0f) {
            dd[3] = _t51 * _t45;
            dd[4] = _t51 * _t46;
            dd[5] = _t51 * _t44;
            dd[6] = _t51 * _t43;
        } else {
            dd[3] = 0.0f;
            dd[4] = 0.0f;
            dd[5] = 0.0f;
            dd[6] = 0.0f;
        }
        return dest;
    }

    /**
     * Interpolate between this rigid transform and ({@code otherTX}, {@code otherTY},
     * {@code otherTZ}, {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}) using
     * the interpolation factor {@code t}, interpolating the translation linearly and the rotation
     * via shortest-arc slerp, and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this rigid transform (interpolation factor {@code 0}) and ends at
     * ({@code otherTX}, {@code otherTY}, {@code otherTZ}, {@code otherRX}, {@code otherRY},
     * {@code otherRZ}, {@code otherRW}) (interpolation factor {@code 1}). Each linearly
     * interpolated component is {@code this + (other - this) * t}, as in JOML and glMatrix:
     * monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the rounding
     * of {@code other - this}, which shows when this component is much larger in magnitude than the
     * other one (1e8 towards 1 ends at 0).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length;
     * {@code (otherRX, otherRY, otherRZ, otherRW)} must have unit length; {@code t} must lie in
     * {@code [0, 1]}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, t, dest);
        float[] sd = this.data;
        float _t12 = java.lang.Math.fma(otherRW, sd[6], java.lang.Math.fma(otherRZ, sd[5], java.lang.Math.fma(otherRX, sd[3], otherRY * sd[4])));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = t * Math.acos(_t15);
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
        float _t30 = (float) java.lang.Math.sqrt(java.lang.Math.fma(-_t15, _t15, 1.0f));
        float _t32 = Math.sinWithCos(_t18) / _t30;
        return lerp_sb5a433c7_1_fma(otherTX, otherTY, otherTZ, t, dest, sd, ((DoubleRigidImpl) dest).data, 1.0f - t, _t21, _t22, _t23, _t24, _t30, _t32, java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18)));
    }

    /** {@code lerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid lerp_mulAdd(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float t, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        float _t12 = ((otherRW) * (sd[6]) + (((otherRZ) * (sd[5]) + (((otherRX) * (sd[3]) + (otherRY * sd[4]))))));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = t * Math.acos(_t15);
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
        float _t30 = (float) java.lang.Math.sqrt(((1.0f) - (_t15) * (_t15)));
        float _t32 = Math.sinWithCos(_t18) / _t30;
        return lerp_sb5a433c7_1_mulAdd(otherTX, otherTY, otherTZ, t, dest, sd, ((DoubleRigidImpl) dest).data, 1.0f - t, _t21, _t22, _t23, _t24, _t30, _t32, ((Math.cosWithSin(_t18)) - (_t32) * (_t15)));
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleRigid lerp_sb5a433c7_1_fma(float otherTX, float otherTY, float otherTZ, float t, DoubleRigid dest, float[] sd, double[] dd, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
        float _t43, _t44, _t45, _t46;
        if (_t30 > 0.0f) {
            _t43 = java.lang.Math.fma(sd[6], _t38, _t32 * _t21);
            _t44 = java.lang.Math.fma(sd[5], _t38, _t32 * _t22);
            _t45 = java.lang.Math.fma(sd[3], _t38, _t32 * _t23);
            _t46 = java.lang.Math.fma(sd[4], _t38, _t32 * _t24);
        } else {
            _t43 = java.lang.Math.fma(t, _t21, sd[6] * _t1);
            _t44 = java.lang.Math.fma(t, _t22, sd[5] * _t1);
            _t45 = java.lang.Math.fma(t, _t23, sd[3] * _t1);
            _t46 = java.lang.Math.fma(t, _t24, sd[4] * _t1);
        }
        float _t50 = java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, java.lang.Math.fma(_t45, _t45, _t46 * _t46)));
        dd[0] = java.lang.Math.fma(t, otherTX - sd[0], sd[0]);
        dd[1] = java.lang.Math.fma(t, otherTY - sd[1], sd[1]);
        dd[2] = java.lang.Math.fma(t, otherTZ - sd[2], sd[2]);
        return lerp_sb5a433c7_2(dest, dd, _t43, _t44, _t45, _t46, _t50, (1.0f / (float) java.lang.Math.sqrt(_t50)));
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleRigid lerp_sb5a433c7_1_mulAdd(float otherTX, float otherTY, float otherTZ, float t, DoubleRigid dest, float[] sd, double[] dd, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
        float _t43, _t44, _t45, _t46;
        if (_t30 > 0.0f) {
            _t43 = ((sd[6]) * (_t38) + (_t32 * _t21));
            _t44 = ((sd[5]) * (_t38) + (_t32 * _t22));
            _t45 = ((sd[3]) * (_t38) + (_t32 * _t23));
            _t46 = ((sd[4]) * (_t38) + (_t32 * _t24));
        } else {
            _t43 = ((t) * (_t21) + (sd[6] * _t1));
            _t44 = ((t) * (_t22) + (sd[5] * _t1));
            _t45 = ((t) * (_t23) + (sd[3] * _t1));
            _t46 = ((t) * (_t24) + (sd[4] * _t1));
        }
        float _t50 = ((_t43) * (_t43) + (((_t44) * (_t44) + (((_t45) * (_t45) + (_t46 * _t46))))));
        dd[0] = ((t) * (otherTX - sd[0]) + (sd[0]));
        dd[1] = ((t) * (otherTY - sd[1]) + (sd[1]));
        dd[2] = ((t) * (otherTZ - sd[2]) + (sd[2]));
        return lerp_sb5a433c7_2(dest, dd, _t43, _t44, _t45, _t46, _t50, (1.0f / (float) java.lang.Math.sqrt(_t50)));
    }

    /** Piece 3 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleRigid lerp_sb5a433c7_2(DoubleRigid dest, double[] dd, float _t43, float _t44, float _t45, float _t46, float _t50, float _t51) {
        if (_t50 != 0.0f) {
            dd[3] = _t51 * _t45;
            dd[4] = _t51 * _t46;
            dd[5] = _t51 * _t44;
            dd[6] = _t51 * _t43;
        } else {
            dd[3] = 0.0f;
            dd[4] = 0.0f;
            dd[5] = 0.0f;
            dd[6] = 0.0f;
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
    public FloatRigid mul(FloatRigidR other, @Mutated FloatRigid dest) {
        float[] otherData = ((FloatRigidImpl) other).data;
        return mul(otherData[0], otherData[1], otherData[2], otherData[3], otherData[4], otherData[5], otherData[6], dest);
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
        float[] otherData = ((FloatRigidImpl) other).data;
        float otherTX = otherData[0];
        float otherTY = otherData[1];
        float otherTZ = otherData[2];
        float otherRX = otherData[3];
        float otherRY = otherData[4];
        float otherRZ = otherData[5];
        float otherRW = otherData[6];
        if (org.joml2.internal.FmaSwitch.ON) return mul_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
        return mul_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(otherTY, sd[3], -(otherTX * sd[4]));
        float _t10 = 2.0f * java.lang.Math.fma(otherTX, sd[5], -(otherTZ * sd[3]));
        float _t11 = 2.0f * java.lang.Math.fma(otherTZ, sd[4], -(otherTY * sd[5]));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, sd[0] + otherTX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, sd[1] + otherTY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, sd[2] + otherTZ)));
        return mul_s3a53318b_1_fma(otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, java.lang.Math.fma(otherRX, sd[6], otherRW * sd[3]) + java.lang.Math.fma(otherRZ, sd[4], otherRY * -sd[5]), java.lang.Math.fma(otherRX, sd[5], otherRW * sd[4]) + java.lang.Math.fma(otherRY, sd[6], otherRZ * -sd[3]));
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid mul_mulAdd(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t9 = 2.0f * ((otherTY) * (sd[3]) - (otherTX * sd[4]));
        float _t10 = 2.0f * ((otherTX) * (sd[5]) - (otherTZ * sd[3]));
        float _t11 = 2.0f * ((otherTZ) * (sd[4]) - (otherTY * sd[5]));
        dd[0] = ((sd[4]) * (_t9) + (((((sd[6]) * (_t11) + (sd[0] + otherTX))) - (sd[5]) * (_t10))));
        dd[1] = ((sd[5]) * (_t11) + (((((sd[6]) * (_t10) + (sd[1] + otherTY))) - (sd[3]) * (_t9))));
        dd[2] = ((sd[3]) * (_t10) + (((((sd[6]) * (_t9) + (sd[2] + otherTZ))) - (sd[4]) * (_t11))));
        return mul_s3a53318b_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, ((otherRX) * (sd[6]) + (otherRW * sd[3])) + ((otherRZ) * (sd[4]) - (otherRY * sd[5])), ((otherRX) * (sd[5]) + (otherRW * sd[4])) + ((otherRY) * (sd[6]) - (otherRZ * sd[3])));
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private FloatRigid mul_s3a53318b_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, FloatRigid dest, float[] sd, float[] dd, float _buf0, float _buf1) {
        float _buf2 = java.lang.Math.fma(otherRY, sd[3], otherRZ * sd[6]) + java.lang.Math.fma(otherRW, sd[5], otherRX * -sd[4]);
        dd[6] = java.lang.Math.fma(otherRW, sd[6], otherRX * -sd[3]) - java.lang.Math.fma(otherRY, sd[4], otherRZ * sd[5]);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private FloatRigid mul_s3a53318b_1_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, FloatRigid dest, float[] sd, float[] dd, float _buf0, float _buf1) {
        float _buf2 = ((otherRY) * (sd[3]) + (otherRZ * sd[6])) + ((otherRW) * (sd[5]) - (otherRX * sd[4]));
        dd[6] = ((otherRW) * (sd[6]) - (otherRX * sd[3])) - ((otherRY) * (sd[4]) + (otherRZ * sd[5]));
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
        if (org.joml2.internal.FmaSwitch.ON) return mul_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
        return mul_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
    }

    /** {@code mul} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid mul_fma(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(otherTY, sd[3], -(otherTX * sd[4]));
        float _t10 = 2.0f * java.lang.Math.fma(otherTX, sd[5], -(otherTZ * sd[3]));
        float _t11 = 2.0f * java.lang.Math.fma(otherTZ, sd[4], -(otherTY * sd[5]));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, sd[0] + otherTX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, sd[1] + otherTY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, sd[2] + otherTZ)));
        dd[3] = java.lang.Math.fma(otherRX, sd[6], otherRW * sd[3]) + java.lang.Math.fma(otherRZ, sd[4], otherRY * -sd[5]);
        dd[4] = java.lang.Math.fma(otherRX, sd[5], otherRW * sd[4]) + java.lang.Math.fma(otherRY, sd[6], otherRZ * -sd[3]);
        return mul_s413cf3cc_1_fma(otherRX, otherRY, otherRZ, otherRW, dest, sd, dd);
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid mul_mulAdd(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t9 = 2.0f * ((otherTY) * (sd[3]) - (otherTX * sd[4]));
        float _t10 = 2.0f * ((otherTX) * (sd[5]) - (otherTZ * sd[3]));
        float _t11 = 2.0f * ((otherTZ) * (sd[4]) - (otherTY * sd[5]));
        dd[0] = ((sd[4]) * (_t9) + (((((sd[6]) * (_t11) + (sd[0] + otherTX))) - (sd[5]) * (_t10))));
        dd[1] = ((sd[5]) * (_t11) + (((((sd[6]) * (_t10) + (sd[1] + otherTY))) - (sd[3]) * (_t9))));
        dd[2] = ((sd[3]) * (_t10) + (((((sd[6]) * (_t9) + (sd[2] + otherTZ))) - (sd[4]) * (_t11))));
        dd[3] = ((otherRX) * (sd[6]) + (otherRW * sd[3])) + ((otherRZ) * (sd[4]) - (otherRY * sd[5]));
        dd[4] = ((otherRX) * (sd[5]) + (otherRW * sd[4])) + ((otherRY) * (sd[6]) - (otherRZ * sd[3]));
        return mul_s413cf3cc_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, dest, sd, dd);
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private DoubleRigid mul_s413cf3cc_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, DoubleRigid dest, float[] sd, double[] dd) {
        dd[5] = java.lang.Math.fma(otherRY, sd[3], otherRZ * sd[6]) + java.lang.Math.fma(otherRW, sd[5], otherRX * -sd[4]);
        dd[6] = java.lang.Math.fma(otherRW, sd[6], otherRX * -sd[3]) - java.lang.Math.fma(otherRY, sd[4], otherRZ * sd[5]);
        return dest;
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private DoubleRigid mul_s413cf3cc_1_mulAdd(float otherRX, float otherRY, float otherRZ, float otherRW, DoubleRigid dest, float[] sd, double[] dd) {
        dd[5] = ((otherRY) * (sd[3]) + (otherRZ * sd[6])) + ((otherRW) * (sd[5]) - (otherRX * sd[4]));
        dd[6] = ((otherRW) * (sd[6]) - (otherRX * sd[3])) - ((otherRY) * (sd[4]) + (otherRZ * sd[5]));
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
        float[] otherData = ((FloatRigidImpl) other).data;
        float otherTX = otherData[0];
        float otherTY = otherData[1];
        float otherTZ = otherData[2];
        float otherRX = otherData[3];
        float otherRY = otherData[4];
        float otherRZ = otherData[5];
        float otherRW = otherData[6];
        if (org.joml2.internal.FmaSwitch.ON) return preMul_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
        return preMul_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
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
        float[] otherData = ((FloatRigidImpl) other).data;
        return preMul(otherData[0], otherData[1], otherData[2], otherData[3], otherData[4], otherData[5], otherData[6], dest);
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
        if (org.joml2.internal.FmaSwitch.ON) return preMul_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
        return preMul_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
    }

    /** {@code preMul} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid preMul_fma(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(otherRX, sd[1], -(otherRY * sd[0]));
        float _t10 = 2.0f * java.lang.Math.fma(otherRZ, sd[0], -(otherRX * sd[2]));
        float _t11 = 2.0f * java.lang.Math.fma(otherRY, sd[2], -(otherRZ * sd[1]));
        dd[0] = java.lang.Math.fma(otherRY, _t9, java.lang.Math.fma(-otherRZ, _t10, java.lang.Math.fma(otherRW, _t11, otherTX + sd[0])));
        dd[1] = java.lang.Math.fma(otherRZ, _t11, java.lang.Math.fma(-otherRX, _t9, java.lang.Math.fma(otherRW, _t10, otherTY + sd[1])));
        dd[2] = java.lang.Math.fma(otherRX, _t10, java.lang.Math.fma(-otherRY, _t11, java.lang.Math.fma(otherRW, _t9, otherTZ + sd[2])));
        return preMul_sa1616d4e_1_fma(otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, java.lang.Math.fma(otherRX, sd[6], otherRW * sd[3]) + java.lang.Math.fma(otherRY, sd[5], -otherRZ * sd[4]), java.lang.Math.fma(otherRY, sd[6], otherRZ * sd[3]) + java.lang.Math.fma(otherRW, sd[4], -otherRX * sd[5]), java.lang.Math.fma(otherRX, sd[4], otherRW * sd[5]) + java.lang.Math.fma(otherRZ, sd[6], -otherRY * sd[3]));
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid preMul_mulAdd(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t9 = 2.0f * ((otherRX) * (sd[1]) - (otherRY * sd[0]));
        float _t10 = 2.0f * ((otherRZ) * (sd[0]) - (otherRX * sd[2]));
        float _t11 = 2.0f * ((otherRY) * (sd[2]) - (otherRZ * sd[1]));
        dd[0] = ((otherRY) * (_t9) + (((((otherRW) * (_t11) + (otherTX + sd[0]))) - (otherRZ) * (_t10))));
        dd[1] = ((otherRZ) * (_t11) + (((((otherRW) * (_t10) + (otherTY + sd[1]))) - (otherRX) * (_t9))));
        dd[2] = ((otherRX) * (_t10) + (((((otherRW) * (_t9) + (otherTZ + sd[2]))) - (otherRY) * (_t11))));
        float _buf0 = ((otherRX) * (sd[6]) + (otherRW * sd[3])) + ((otherRY) * (sd[5]) - (otherRZ * sd[4]));
        float _buf1 = ((otherRY) * (sd[6]) + (otherRZ * sd[3])) + ((otherRW) * (sd[4]) - (otherRX * sd[5]));
        float _buf2 = ((otherRX) * (sd[4]) + (otherRW * sd[5])) + ((otherRZ) * (sd[6]) - (otherRY * sd[3]));
        dd[6] = ((otherRW) * (sd[6]) - (otherRX * sd[3])) - ((otherRY) * (sd[4]) + (otherRZ * sd[5]));
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private FloatRigid preMul_sa1616d4e_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, FloatRigid dest, float[] sd, float[] dd, float _buf0, float _buf1, float _buf2) {
        dd[6] = java.lang.Math.fma(otherRW, sd[6], -otherRX * sd[3]) - java.lang.Math.fma(otherRY, sd[4], otherRZ * sd[5]);
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
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(otherRX, sd[1], -(otherRY * sd[0]));
        float _t10 = 2.0f * java.lang.Math.fma(otherRZ, sd[0], -(otherRX * sd[2]));
        float _t11 = 2.0f * java.lang.Math.fma(otherRY, sd[2], -(otherRZ * sd[1]));
        dd[0] = java.lang.Math.fma(otherRY, _t9, java.lang.Math.fma(-otherRZ, _t10, java.lang.Math.fma(otherRW, _t11, otherTX + sd[0])));
        dd[1] = java.lang.Math.fma(otherRZ, _t11, java.lang.Math.fma(-otherRX, _t9, java.lang.Math.fma(otherRW, _t10, otherTY + sd[1])));
        dd[2] = java.lang.Math.fma(otherRX, _t10, java.lang.Math.fma(-otherRY, _t11, java.lang.Math.fma(otherRW, _t9, otherTZ + sd[2])));
        dd[3] = java.lang.Math.fma(otherRX, sd[6], otherRW * sd[3]) + java.lang.Math.fma(otherRY, sd[5], -otherRZ * sd[4]);
        dd[4] = java.lang.Math.fma(otherRY, sd[6], otherRZ * sd[3]) + java.lang.Math.fma(otherRW, sd[4], -otherRX * sd[5]);
        return preMul_s8c3f2013_1_fma(otherRX, otherRY, otherRZ, otherRW, dest, sd, dd);
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid preMul_mulAdd(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t9 = 2.0f * ((otherRX) * (sd[1]) - (otherRY * sd[0]));
        float _t10 = 2.0f * ((otherRZ) * (sd[0]) - (otherRX * sd[2]));
        float _t11 = 2.0f * ((otherRY) * (sd[2]) - (otherRZ * sd[1]));
        dd[0] = ((otherRY) * (_t9) + (((((otherRW) * (_t11) + (otherTX + sd[0]))) - (otherRZ) * (_t10))));
        dd[1] = ((otherRZ) * (_t11) + (((((otherRW) * (_t10) + (otherTY + sd[1]))) - (otherRX) * (_t9))));
        dd[2] = ((otherRX) * (_t10) + (((((otherRW) * (_t9) + (otherTZ + sd[2]))) - (otherRY) * (_t11))));
        dd[3] = ((otherRX) * (sd[6]) + (otherRW * sd[3])) + ((otherRY) * (sd[5]) - (otherRZ * sd[4]));
        dd[4] = ((otherRY) * (sd[6]) + (otherRZ * sd[3])) + ((otherRW) * (sd[4]) - (otherRX * sd[5]));
        dd[5] = ((otherRX) * (sd[4]) + (otherRW * sd[5])) + ((otherRZ) * (sd[6]) - (otherRY * sd[3]));
        dd[6] = ((otherRW) * (sd[6]) - (otherRX * sd[3])) - ((otherRY) * (sd[4]) + (otherRZ * sd[5]));
        return dest;
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private DoubleRigid preMul_s8c3f2013_1_fma(float otherRX, float otherRY, float otherRZ, float otherRW, DoubleRigid dest, float[] sd, double[] dd) {
        dd[5] = java.lang.Math.fma(otherRX, sd[4], otherRW * sd[5]) + java.lang.Math.fma(otherRZ, sd[6], -otherRY * sd[3]);
        dd[6] = java.lang.Math.fma(otherRW, sd[6], -otherRX * sd[3]) - java.lang.Math.fma(otherRY, sd[4], otherRZ * sd[5]);
        return dest;
    }

    /**
     * Compute the difference between this rigid transform and {@code other}, i.e. the rigid
     * transformation {@code D} with {@code this * D = other}, that is {@code D = this^-1 * other},
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
        float[] otherData = ((FloatRigidImpl) other).data;
        return difference(otherData[0], otherData[1], otherData[2], otherData[3], otherData[4], otherData[5], otherData[6], dest);
    }

    /**
     * Compute the difference between this rigid transform and {@code other}, i.e. the rigid
     * transformation {@code D} with {@code this * D = other}, that is {@code D = this^-1 * other},
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
        float[] otherData = ((FloatRigidImpl) other).data;
        return difference(otherData[0], otherData[1], otherData[2], otherData[3], otherData[4], otherData[5], otherData[6], dest);
    }

    /**
     * Compute the difference between this rigid transform and ({@code otherTX}, {@code otherTY},
     * {@code otherTZ}, {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}), i.e.
     * the rigid transformation {@code D} with
     * {@code this * D = (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}, that is
     * {@code D = this^-1 * (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}, and
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
        if (!org.joml2.internal.FmaSwitch.ON) return difference_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t18 = 2.0f * java.lang.Math.fma(otherTZ, sd[3], -(otherTX * sd[5]));
        float _t19 = 2.0f * java.lang.Math.fma(otherTY, sd[5], -(otherTZ * sd[4]));
        float _t20 = 2.0f * java.lang.Math.fma(otherTX, sd[4], -(otherTY * sd[3]));
        float _t21 = 2.0f * java.lang.Math.fma(sd[0], sd[5], -(sd[2] * sd[3]));
        float _t22 = 2.0f * java.lang.Math.fma(sd[1], sd[3], -(sd[0] * sd[4]));
        float _t23 = 2.0f * java.lang.Math.fma(sd[2], sd[4], -(sd[1] * sd[5]));
        dd[0] = java.lang.Math.fma(sd[5], _t18, otherTX) + java.lang.Math.fma(sd[6], _t19, -sd[4] * _t20) + (java.lang.Math.fma(sd[5], _t21, -sd[4] * _t22) + java.lang.Math.fma(sd[6], _t23, -sd[0]));
        return difference_sad2ccb00_1_fma(otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid difference_mulAdd(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t18 = 2.0f * ((otherTZ) * (sd[3]) - (otherTX * sd[5]));
        float _t19 = 2.0f * ((otherTY) * (sd[5]) - (otherTZ * sd[4]));
        float _t20 = 2.0f * ((otherTX) * (sd[4]) - (otherTY * sd[3]));
        float _t21 = 2.0f * ((sd[0]) * (sd[5]) - (sd[2] * sd[3]));
        float _t22 = 2.0f * ((sd[1]) * (sd[3]) - (sd[0] * sd[4]));
        float _t23 = 2.0f * ((sd[2]) * (sd[4]) - (sd[1] * sd[5]));
        dd[0] = ((sd[5]) * (_t18) + (otherTX)) + ((sd[6]) * (_t19) - (sd[4] * _t20)) + (((sd[5]) * (_t21) - (sd[4] * _t22)) + ((sd[6]) * (_t23) - (sd[0])));
        return difference_sad2ccb00_1_mulAdd(otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private FloatRigid difference_sad2ccb00_1_fma(float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, FloatRigid dest, float[] sd, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        dd[1] = java.lang.Math.fma(sd[3], _t20, otherTY) + java.lang.Math.fma(sd[6], _t18, -sd[5] * _t19) + (java.lang.Math.fma(sd[3], _t22, -sd[5] * _t23) + java.lang.Math.fma(sd[6], _t21, -sd[1]));
        dd[2] = java.lang.Math.fma(sd[4], _t19, otherTZ) + java.lang.Math.fma(sd[6], _t20, -sd[3] * _t18) + (java.lang.Math.fma(sd[4], _t23, -sd[3] * _t21) + java.lang.Math.fma(sd[6], _t22, -sd[2]));
        float _buf0 = java.lang.Math.fma(otherRX, sd[6], otherRW * -sd[3]) + java.lang.Math.fma(otherRY, sd[5], otherRZ * -sd[4]);
        float _buf1 = java.lang.Math.fma(otherRY, sd[6], otherRZ * sd[3]) + java.lang.Math.fma(otherRX, -sd[5], otherRW * -sd[4]);
        float _buf2 = java.lang.Math.fma(otherRX, sd[4], otherRW * -sd[5]) + java.lang.Math.fma(otherRZ, sd[6], otherRY * -sd[3]);
        dd[6] = java.lang.Math.fma(otherRX, sd[3], otherRW * sd[6]) - java.lang.Math.fma(otherRZ, -sd[5], otherRY * -sd[4]);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private FloatRigid difference_sad2ccb00_1_mulAdd(float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, FloatRigid dest, float[] sd, float[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        dd[1] = ((sd[3]) * (_t20) + (otherTY)) + ((sd[6]) * (_t18) - (sd[5] * _t19)) + (((sd[3]) * (_t22) - (sd[5] * _t23)) + ((sd[6]) * (_t21) - (sd[1])));
        dd[2] = ((sd[4]) * (_t19) + (otherTZ)) + ((sd[6]) * (_t20) - (sd[3] * _t18)) + (((sd[4]) * (_t23) - (sd[3] * _t21)) + ((sd[6]) * (_t22) - (sd[2])));
        float _buf0 = ((otherRX) * (sd[6]) - (otherRW * sd[3])) + ((otherRY) * (sd[5]) - (otherRZ * sd[4]));
        float _buf1 = ((otherRY) * (sd[6]) + (otherRZ * sd[3])) + ((otherRX) * (-sd[5]) - (otherRW * sd[4]));
        float _buf2 = ((otherRX) * (sd[4]) - (otherRW * sd[5])) + ((otherRZ) * (sd[6]) - (otherRY * sd[3]));
        dd[6] = ((otherRX) * (sd[3]) + (otherRW * sd[6])) - ((otherRZ) * (-sd[5]) - (otherRY * sd[4]));
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
     * {@code D = this^-1 * (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW)}, and
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
        if (!org.joml2.internal.FmaSwitch.ON) return difference_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t18 = 2.0f * java.lang.Math.fma(otherTZ, sd[3], -(otherTX * sd[5]));
        float _t19 = 2.0f * java.lang.Math.fma(otherTY, sd[5], -(otherTZ * sd[4]));
        float _t20 = 2.0f * java.lang.Math.fma(otherTX, sd[4], -(otherTY * sd[3]));
        float _t21 = 2.0f * java.lang.Math.fma(sd[0], sd[5], -(sd[2] * sd[3]));
        float _t22 = 2.0f * java.lang.Math.fma(sd[1], sd[3], -(sd[0] * sd[4]));
        float _t23 = 2.0f * java.lang.Math.fma(sd[2], sd[4], -(sd[1] * sd[5]));
        dd[0] = java.lang.Math.fma(sd[5], _t18, otherTX) + java.lang.Math.fma(sd[6], _t19, -sd[4] * _t20) + (java.lang.Math.fma(sd[5], _t21, -sd[4] * _t22) + java.lang.Math.fma(sd[6], _t23, -sd[0]));
        return difference_sc6961c19_1_fma(otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid difference_mulAdd(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t18 = 2.0f * ((otherTZ) * (sd[3]) - (otherTX * sd[5]));
        float _t19 = 2.0f * ((otherTY) * (sd[5]) - (otherTZ * sd[4]));
        float _t20 = 2.0f * ((otherTX) * (sd[4]) - (otherTY * sd[3]));
        float _t21 = 2.0f * ((sd[0]) * (sd[5]) - (sd[2] * sd[3]));
        float _t22 = 2.0f * ((sd[1]) * (sd[3]) - (sd[0] * sd[4]));
        float _t23 = 2.0f * ((sd[2]) * (sd[4]) - (sd[1] * sd[5]));
        dd[0] = ((sd[5]) * (_t18) + (otherTX)) + ((sd[6]) * (_t19) - (sd[4] * _t20)) + (((sd[5]) * (_t21) - (sd[4] * _t22)) + ((sd[6]) * (_t23) - (sd[0])));
        return difference_sc6961c19_1_mulAdd(otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest, sd, dd, _t18, _t19, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleRigid difference_sc6961c19_1_fma(float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, DoubleRigid dest, float[] sd, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        dd[1] = java.lang.Math.fma(sd[3], _t20, otherTY) + java.lang.Math.fma(sd[6], _t18, -sd[5] * _t19) + (java.lang.Math.fma(sd[3], _t22, -sd[5] * _t23) + java.lang.Math.fma(sd[6], _t21, -sd[1]));
        dd[2] = java.lang.Math.fma(sd[4], _t19, otherTZ) + java.lang.Math.fma(sd[6], _t20, -sd[3] * _t18) + (java.lang.Math.fma(sd[4], _t23, -sd[3] * _t21) + java.lang.Math.fma(sd[6], _t22, -sd[2]));
        dd[3] = java.lang.Math.fma(otherRX, sd[6], otherRW * -sd[3]) + java.lang.Math.fma(otherRY, sd[5], otherRZ * -sd[4]);
        dd[4] = java.lang.Math.fma(otherRY, sd[6], otherRZ * sd[3]) + java.lang.Math.fma(otherRX, -sd[5], otherRW * -sd[4]);
        dd[5] = java.lang.Math.fma(otherRX, sd[4], otherRW * -sd[5]) + java.lang.Math.fma(otherRZ, sd[6], otherRY * -sd[3]);
        dd[6] = java.lang.Math.fma(otherRX, sd[3], otherRW * sd[6]) - java.lang.Math.fma(otherRZ, -sd[5], otherRY * -sd[4]);
        return dest;
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleRigid difference_sc6961c19_1_mulAdd(float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, DoubleRigid dest, float[] sd, double[] dd, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        dd[1] = ((sd[3]) * (_t20) + (otherTY)) + ((sd[6]) * (_t18) - (sd[5] * _t19)) + (((sd[3]) * (_t22) - (sd[5] * _t23)) + ((sd[6]) * (_t21) - (sd[1])));
        dd[2] = ((sd[4]) * (_t19) + (otherTZ)) + ((sd[6]) * (_t20) - (sd[3] * _t18)) + (((sd[4]) * (_t23) - (sd[3] * _t21)) + ((sd[6]) * (_t22) - (sd[2])));
        dd[3] = ((otherRX) * (sd[6]) - (otherRW * sd[3])) + ((otherRY) * (sd[5]) - (otherRZ * sd[4]));
        dd[4] = ((otherRY) * (sd[6]) + (otherRZ * sd[3])) + ((otherRX) * (-sd[5]) - (otherRW * sd[4]));
        dd[5] = ((otherRX) * (sd[4]) - (otherRW * sd[5])) + ((otherRZ) * (sd[6]) - (otherRY * sd[3]));
        dd[6] = ((otherRX) * (sd[3]) + (otherRW * sd[6])) - ((otherRZ) * (-sd[5]) - (otherRY * sd[4]));
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
        if (!org.joml2.internal.FmaSwitch.ON) return invert_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t0 = -sd[4];
        float _t1 = -sd[5];
        float _t2 = -sd[3];
        float _t12 = 2.0f * java.lang.Math.fma(sd[0], sd[5], -sd[2] * sd[3]);
        float _t13 = 2.0f * java.lang.Math.fma(sd[1], sd[3], -sd[0] * sd[4]);
        float _t14 = 2.0f * java.lang.Math.fma(sd[2], sd[4], -sd[1] * sd[5]);
        dd[0] = java.lang.Math.fma(sd[5], _t12, java.lang.Math.fma(_t0, _t13, java.lang.Math.fma(sd[6], _t14, -sd[0])));
        dd[1] = java.lang.Math.fma(sd[3], _t13, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(sd[6], _t12, -sd[1])));
        dd[2] = java.lang.Math.fma(sd[4], _t14, java.lang.Math.fma(_t2, _t12, java.lang.Math.fma(sd[6], _t13, -sd[2])));
        dd[3] = _t2;
        dd[4] = _t0;
        dd[5] = _t1;
        dd[6] = sd[6];
        return dest;
    }

    /** {@code invert} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid invert_mulAdd(@Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t0 = -sd[4];
        float _t1 = -sd[5];
        float _t2 = -sd[3];
        float _t12 = 2.0f * ((sd[0]) * (sd[5]) - (sd[2] * sd[3]));
        float _t13 = 2.0f * ((sd[1]) * (sd[3]) - (sd[0] * sd[4]));
        float _t14 = 2.0f * ((sd[2]) * (sd[4]) - (sd[1] * sd[5]));
        dd[0] = ((sd[5]) * (_t12) + (((_t0) * (_t13) + (((sd[6]) * (_t14) - (sd[0]))))));
        dd[1] = ((sd[3]) * (_t13) + (((_t1) * (_t14) + (((sd[6]) * (_t12) - (sd[1]))))));
        dd[2] = ((sd[4]) * (_t14) + (((_t2) * (_t12) + (((sd[6]) * (_t13) - (sd[2]))))));
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
        if (!org.joml2.internal.FmaSwitch.ON) return invert_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t0 = -sd[4];
        float _t1 = -sd[5];
        float _t2 = -sd[3];
        float _t12 = 2.0f * java.lang.Math.fma(sd[0], sd[5], -sd[2] * sd[3]);
        float _t13 = 2.0f * java.lang.Math.fma(sd[1], sd[3], -sd[0] * sd[4]);
        float _t14 = 2.0f * java.lang.Math.fma(sd[2], sd[4], -sd[1] * sd[5]);
        dd[0] = java.lang.Math.fma(sd[5], _t12, java.lang.Math.fma(_t0, _t13, java.lang.Math.fma(sd[6], _t14, -sd[0])));
        dd[1] = java.lang.Math.fma(sd[3], _t13, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(sd[6], _t12, -sd[1])));
        dd[2] = java.lang.Math.fma(sd[4], _t14, java.lang.Math.fma(_t2, _t12, java.lang.Math.fma(sd[6], _t13, -sd[2])));
        dd[3] = _t2;
        dd[4] = _t0;
        dd[5] = _t1;
        dd[6] = sd[6];
        return dest;
    }

    /** {@code invert} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid invert_mulAdd(@Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t0 = -sd[4];
        float _t1 = -sd[5];
        float _t2 = -sd[3];
        float _t12 = 2.0f * ((sd[0]) * (sd[5]) - (sd[2] * sd[3]));
        float _t13 = 2.0f * ((sd[1]) * (sd[3]) - (sd[0] * sd[4]));
        float _t14 = 2.0f * ((sd[2]) * (sd[4]) - (sd[1] * sd[5]));
        dd[0] = ((sd[5]) * (_t12) + (((_t0) * (_t13) + (((sd[6]) * (_t14) - (sd[0]))))));
        dd[1] = ((sd[3]) * (_t13) + (((_t1) * (_t14) + (((sd[6]) * (_t12) - (sd[1]))))));
        dd[2] = ((sd[4]) * (_t14) + (((_t2) * (_t12) + (((sd[6]) * (_t13) - (sd[2]))))));
        dd[3] = _t2;
        dd[4] = _t0;
        dd[5] = _t1;
        dd[6] = sd[6];
        return dest;
    }

    /**
     * Normalize this rigid transform so that its rotation part has unit length, leaving its
     * translation unchanged (a zero-length rotation yields the zero quaternion), and store the
     * result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatRigid normalize(@Mutated FloatRigid dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float[] dd = ((FloatRigidImpl) dest).data;
            float _t3 = java.lang.Math.fma(sd[6], sd[6], java.lang.Math.fma(sd[5], sd[5], java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4])));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
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
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((FloatRigidImpl) dest).data;
            float _t3 = ((sd[6]) * (sd[6]) + (((sd[5]) * (sd[5]) + (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))))));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
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
            return dest;
        }
    }

    /**
     * Normalize this rigid transform so that its rotation part has unit length, leaving its
     * translation unchanged (a zero-length rotation yields the zero quaternion), and store the
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((DoubleRigidImpl) dest).data;
            float _t3 = java.lang.Math.fma(sd[6], sd[6], java.lang.Math.fma(sd[5], sd[5], java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4])));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
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
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((DoubleRigidImpl) dest).data;
            float _t3 = ((sd[6]) * (sd[6]) + (((sd[5]) * (sd[5]) + (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))))));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
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
            return dest;
        }
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the X, Y and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X) and store the result in {@code dest}.
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
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXYZ(@Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t2 = sd[4] * sd[6];
        float _t10 = 2.0f * java.lang.Math.fma(sd[3], sd[5], _t2);
        float _t11 = 2.0f * java.lang.Math.fma(sd[3], sd[6], _t1);
        float _t12 = 2.0f * java.lang.Math.fma(sd[3], sd[6], -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[5] * sd[5]), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]), 2.0f * java.lang.Math.fma(sd[3], sd[5], -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
        }
        dd[1] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        return dest;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesXYZ_mulAdd(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t2 = sd[4] * sd[6];
        float _t10 = 2.0f * ((sd[3]) * (sd[5]) + (_t2));
        float _t11 = 2.0f * ((sd[3]) * (sd[6]) + (_t1));
        float _t12 = 2.0f * ((sd[3]) * (sd[6]) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        float _t14 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[5] * sd[5]))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(((_t13) * (2.0f * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]))) + (2.0f * ((sd[3]) * (sd[5]) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
        }
        dd[1] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the X, Y and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X) and store the result in {@code dest}.
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXYZ(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t2 = sd[4] * sd[6];
        float _t10 = 2.0f * java.lang.Math.fma(sd[3], sd[5], _t2);
        float _t11 = 2.0f * java.lang.Math.fma(sd[3], sd[6], _t1);
        float _t12 = 2.0f * java.lang.Math.fma(sd[3], sd[6], -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[5] * sd[5]), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]), 2.0f * java.lang.Math.fma(sd[3], sd[5], -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
        }
        dd[1] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        return dest;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t2 = sd[4] * sd[6];
        float _t10 = 2.0f * ((sd[3]) * (sd[5]) + (_t2));
        float _t11 = 2.0f * ((sd[3]) * (sd[6]) + (_t1));
        float _t12 = 2.0f * ((sd[3]) * (sd[6]) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        float _t14 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[5] * sd[5]))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(((_t13) * (2.0f * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]))) + (2.0f * ((sd[3]) * (sd[5]) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
        }
        dd[1] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the X, Y and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X) and store the result in {@code dest}.
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
     * Valid input: the rotation of this rigid transform must have unit length; {@code epsilon} must
     * lie in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXYZ(float epsilon, @Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_mulAdd(epsilon, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t2 = sd[4] * sd[6];
        float _t10 = 2.0f * java.lang.Math.fma(sd[3], sd[5], _t2);
        float _t11 = 2.0f * java.lang.Math.fma(sd[3], sd[6], _t1);
        float _t12 = 2.0f * java.lang.Math.fma(sd[3], sd[6], -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[5] * sd[5]), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]), 2.0f * java.lang.Math.fma(sd[3], sd[5], -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
        }
        dd[1] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        return dest;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesXYZ_mulAdd(float epsilon, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t2 = sd[4] * sd[6];
        float _t10 = 2.0f * ((sd[3]) * (sd[5]) + (_t2));
        float _t11 = 2.0f * ((sd[3]) * (sd[6]) + (_t1));
        float _t12 = 2.0f * ((sd[3]) * (sd[6]) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        float _t14 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[5] * sd[5]))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(((_t13) * (2.0f * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]))) + (2.0f * ((sd[3]) * (sd[5]) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
        }
        dd[1] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the X, Y and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X) and store the result in {@code dest}.
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length; {@code epsilon} must
     * lie in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXYZ(float epsilon, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_mulAdd(epsilon, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t2 = sd[4] * sd[6];
        float _t10 = 2.0f * java.lang.Math.fma(sd[3], sd[5], _t2);
        float _t11 = 2.0f * java.lang.Math.fma(sd[3], sd[6], _t1);
        float _t12 = 2.0f * java.lang.Math.fma(sd[3], sd[6], -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[5] * sd[5]), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]), 2.0f * java.lang.Math.fma(sd[3], sd[5], -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
        }
        dd[1] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        return dest;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd(float epsilon, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t2 = sd[4] * sd[6];
        float _t10 = 2.0f * ((sd[3]) * (sd[5]) + (_t2));
        float _t11 = 2.0f * ((sd[3]) * (sd[6]) + (_t1));
        float _t12 = 2.0f * ((sd[3]) * (sd[6]) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        float _t14 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[5] * sd[5]))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(((_t13) * (2.0f * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]))) + (2.0f * ((sd[3]) * (sd[5]) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
        }
        dd[1] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the X, Z and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X) and store the result in {@code dest}.
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
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXZY(@Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXZY_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], sd[6], _t1);
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[5] * sd[5]), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            dd[0] = Math.atan2(2.0f * java.lang.Math.fma(sd[3], sd[6], -_t1), _t12);
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]), _t9, _t11 * -2.0f * java.lang.Math.fma(sd[3], sd[5], sd[4] * -sd[6])), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(sd[4], sd[5], sd[3] * -sd[6])));
        }
        dd[2] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        return dest;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesXZY_mulAdd(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t9 = 2.0f * ((sd[3]) * (sd[6]) + (_t1));
        float _t10 = 2.0f * ((sd[5]) * (sd[6]) - (sd[3] * sd[4]));
        float _t11 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[5] * sd[5]))));
        float _t12 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            dd[0] = Math.atan2(2.0f * ((sd[3]) * (sd[6]) - (_t1)), _t12);
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(((2.0f * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]))) * (_t9) - (_t11 * 2.0f * ((sd[3]) * (sd[5]) - (sd[4] * sd[6])))), ((_t12) * (_t11) - (_t9 * 2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])))));
        }
        dd[2] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the X, Z and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X) and store the result in {@code dest}.
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXZY(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXZY_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], sd[6], _t1);
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[5] * sd[5]), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            dd[0] = Math.atan2(2.0f * java.lang.Math.fma(sd[3], sd[6], -_t1), _t12);
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]), _t9, _t11 * -2.0f * java.lang.Math.fma(sd[3], sd[5], sd[4] * -sd[6])), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(sd[4], sd[5], sd[3] * -sd[6])));
        }
        dd[2] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        return dest;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t9 = 2.0f * ((sd[3]) * (sd[6]) + (_t1));
        float _t10 = 2.0f * ((sd[5]) * (sd[6]) - (sd[3] * sd[4]));
        float _t11 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[5] * sd[5]))));
        float _t12 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            dd[0] = Math.atan2(2.0f * ((sd[3]) * (sd[6]) - (_t1)), _t12);
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(((2.0f * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]))) * (_t9) - (_t11 * 2.0f * ((sd[3]) * (sd[5]) - (sd[4] * sd[6])))), ((_t12) * (_t11) - (_t9 * 2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])))));
        }
        dd[2] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the X, Z and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X) and store the result in {@code dest}.
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
     * Valid input: the rotation of this rigid transform must have unit length; {@code epsilon} must
     * lie in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXZY(float epsilon, @Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXZY_mulAdd(epsilon, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], sd[6], _t1);
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[5] * sd[5]), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dd[0] = Math.atan2(2.0f * java.lang.Math.fma(sd[3], sd[6], -_t1), _t12);
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]), _t9, _t11 * -2.0f * java.lang.Math.fma(sd[3], sd[5], sd[4] * -sd[6])), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(sd[4], sd[5], sd[3] * -sd[6])));
        }
        dd[2] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        return dest;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesXZY_mulAdd(float epsilon, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t9 = 2.0f * ((sd[3]) * (sd[6]) + (_t1));
        float _t10 = 2.0f * ((sd[5]) * (sd[6]) - (sd[3] * sd[4]));
        float _t11 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[5] * sd[5]))));
        float _t12 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dd[0] = Math.atan2(2.0f * ((sd[3]) * (sd[6]) - (_t1)), _t12);
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(((2.0f * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]))) * (_t9) - (_t11 * 2.0f * ((sd[3]) * (sd[5]) - (sd[4] * sd[6])))), ((_t12) * (_t11) - (_t9 * 2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])))));
        }
        dd[2] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the X, Z and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X) and store the result in {@code dest}.
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length; {@code epsilon} must
     * lie in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXZY(float epsilon, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXZY_mulAdd(epsilon, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], sd[6], _t1);
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[5] * sd[5]), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dd[0] = Math.atan2(2.0f * java.lang.Math.fma(sd[3], sd[6], -_t1), _t12);
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]), _t9, _t11 * -2.0f * java.lang.Math.fma(sd[3], sd[5], sd[4] * -sd[6])), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(sd[4], sd[5], sd[3] * -sd[6])));
        }
        dd[2] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        return dest;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd(float epsilon, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[4] * sd[5];
        float _t9 = 2.0f * ((sd[3]) * (sd[6]) + (_t1));
        float _t10 = 2.0f * ((sd[5]) * (sd[6]) - (sd[3] * sd[4]));
        float _t11 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[5] * sd[5]))));
        float _t12 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dd[0] = Math.atan2(2.0f * ((sd[3]) * (sd[6]) - (_t1)), _t12);
            dd[1] = 0.0f;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(((2.0f * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]))) * (_t9) - (_t11 * 2.0f * ((sd[3]) * (sd[5]) - (sd[4] * sd[6])))), ((_t12) * (_t11) - (_t9 * 2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])))));
        }
        dd[2] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the Y, X and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y) and store the result in {@code dest}.
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
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYXZ(@Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYXZ_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[4] * sd[5];
        float _t2 = sd[4] * sd[6];
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], sd[5], _t2);
        float _t10 = 2.0f * java.lang.Math.fma(sd[3], sd[6], -_t0);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], sd[5] * sd[5]), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            dd[1] = Math.atan2(2.0f * java.lang.Math.fma(sd[4], sd[6], sd[3] * -sd[5]), _t12);
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(sd[3], sd[6], _t0), _t11 * -2.0f * java.lang.Math.fma(sd[3], sd[4], -sd[5] * sd[6])), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(sd[3], sd[5], -_t2)));
        }
        return dest;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesYXZ_mulAdd(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[4] * sd[5];
        float _t2 = sd[4] * sd[6];
        float _t9 = 2.0f * ((sd[3]) * (sd[5]) + (_t2));
        float _t10 = 2.0f * ((sd[3]) * (sd[6]) - (_t0));
        float _t11 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        float _t12 = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (sd[5] * sd[5]))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            dd[1] = Math.atan2(2.0f * ((sd[4]) * (sd[6]) - (sd[3] * sd[5])), _t12);
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(((_t9) * (2.0f * ((sd[3]) * (sd[6]) + (_t0))) - (_t11 * 2.0f * ((sd[3]) * (sd[4]) - (sd[5] * sd[6])))), ((_t11) * (_t12) - (_t9 * 2.0f * ((sd[3]) * (sd[5]) - (_t2)))));
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the Y, X and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y) and store the result in {@code dest}.
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYXZ(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYXZ_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[4] * sd[5];
        float _t2 = sd[4] * sd[6];
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], sd[5], _t2);
        float _t10 = 2.0f * java.lang.Math.fma(sd[3], sd[6], -_t0);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], sd[5] * sd[5]), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            dd[1] = Math.atan2(2.0f * java.lang.Math.fma(sd[4], sd[6], -(sd[3] * sd[5])), _t12);
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(sd[3], sd[6], _t0), _t11 * -2.0f * java.lang.Math.fma(sd[3], sd[4], -(sd[5] * sd[6]))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(sd[3], sd[5], -_t2)));
        }
        return dest;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[4] * sd[5];
        float _t2 = sd[4] * sd[6];
        float _t9 = 2.0f * ((sd[3]) * (sd[5]) + (_t2));
        float _t10 = 2.0f * ((sd[3]) * (sd[6]) - (_t0));
        float _t11 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        float _t12 = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (sd[5] * sd[5]))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            dd[1] = Math.atan2(2.0f * ((sd[4]) * (sd[6]) - (sd[3] * sd[5])), _t12);
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(((_t9) * (2.0f * ((sd[3]) * (sd[6]) + (_t0))) - (_t11 * 2.0f * ((sd[3]) * (sd[4]) - (sd[5] * sd[6])))), ((_t11) * (_t12) - (_t9 * 2.0f * ((sd[3]) * (sd[5]) - (_t2)))));
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the Y, X and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y) and store the result in {@code dest}.
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
     * Valid input: the rotation of this rigid transform must have unit length; {@code epsilon} must
     * lie in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYXZ(float epsilon, @Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYXZ_mulAdd(epsilon, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[4] * sd[5];
        float _t2 = sd[4] * sd[6];
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], sd[5], _t2);
        float _t10 = 2.0f * java.lang.Math.fma(sd[3], sd[6], -_t0);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], sd[5] * sd[5]), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dd[1] = Math.atan2(2.0f * java.lang.Math.fma(sd[4], sd[6], sd[3] * -sd[5]), _t12);
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(sd[3], sd[6], _t0), _t11 * -2.0f * java.lang.Math.fma(sd[3], sd[4], -sd[5] * sd[6])), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(sd[3], sd[5], -_t2)));
        }
        return dest;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesYXZ_mulAdd(float epsilon, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[4] * sd[5];
        float _t2 = sd[4] * sd[6];
        float _t9 = 2.0f * ((sd[3]) * (sd[5]) + (_t2));
        float _t10 = 2.0f * ((sd[3]) * (sd[6]) - (_t0));
        float _t11 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        float _t12 = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (sd[5] * sd[5]))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dd[1] = Math.atan2(2.0f * ((sd[4]) * (sd[6]) - (sd[3] * sd[5])), _t12);
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(((_t9) * (2.0f * ((sd[3]) * (sd[6]) + (_t0))) - (_t11 * 2.0f * ((sd[3]) * (sd[4]) - (sd[5] * sd[6])))), ((_t11) * (_t12) - (_t9 * 2.0f * ((sd[3]) * (sd[5]) - (_t2)))));
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the Y, X and Z
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y) and store the result in {@code dest}.
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length; {@code epsilon} must
     * lie in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYXZ(float epsilon, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYXZ_mulAdd(epsilon, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[4] * sd[5];
        float _t2 = sd[4] * sd[6];
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], sd[5], _t2);
        float _t10 = 2.0f * java.lang.Math.fma(sd[3], sd[6], -_t0);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], sd[5] * sd[5]), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dd[1] = Math.atan2(2.0f * java.lang.Math.fma(sd[4], sd[6], -(sd[3] * sd[5])), _t12);
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(sd[3], sd[6], _t0), _t11 * -2.0f * java.lang.Math.fma(sd[3], sd[4], -(sd[5] * sd[6]))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(sd[3], sd[5], -_t2)));
        }
        return dest;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd(float epsilon, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[4] * sd[5];
        float _t2 = sd[4] * sd[6];
        float _t9 = 2.0f * ((sd[3]) * (sd[5]) + (_t2));
        float _t10 = 2.0f * ((sd[3]) * (sd[6]) - (_t0));
        float _t11 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        float _t12 = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (sd[5] * sd[5]))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dd[1] = Math.atan2(2.0f * ((sd[4]) * (sd[6]) - (sd[3] * sd[5])), _t12);
            dd[2] = 0.0f;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(((_t9) * (2.0f * ((sd[3]) * (sd[6]) + (_t0))) - (_t11 * 2.0f * ((sd[3]) * (sd[4]) - (sd[5] * sd[6])))), ((_t11) * (_t12) - (_t9 * 2.0f * ((sd[3]) * (sd[5]) - (_t2)))));
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the Y, Z and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y) and store the result in {@code dest}.
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
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYZX(@Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYZX_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t2 = sd[5] * sd[6];
        float _t11 = 2.0f * java.lang.Math.fma(sd[3], sd[4], _t2);
        float _t12 = 2.0f * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]);
        float _t13 = 2.0f * java.lang.Math.fma(sd[4], sd[6], -(sd[3] * sd[5]));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], sd[5] * sd[5]), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= java.lang.Math.fma(_t11, _t11, _t17) * 2.2737368E-13f) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]), 2.0f * java.lang.Math.fma(sd[3], sd[4], -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17));
        return dest;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesYZX_mulAdd(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t2 = sd[5] * sd[6];
        float _t11 = 2.0f * ((sd[3]) * (sd[4]) + (_t2));
        float _t12 = 2.0f * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]));
        float _t13 = 2.0f * ((sd[4]) * (sd[6]) - (sd[3] * sd[5]));
        float _t14 = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (sd[5] * sd[5]))));
        float _t15 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= ((_t11) * (_t11) + (_t17)) * 2.2737368E-13f) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(((_t14) * (2.0f * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]))) + (2.0f * ((sd[3]) * (sd[4]) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13)));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the Y, Z and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y) and store the result in {@code dest}.
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYZX(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYZX_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t2 = sd[5] * sd[6];
        float _t11 = 2.0f * java.lang.Math.fma(sd[3], sd[4], _t2);
        float _t12 = 2.0f * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]);
        float _t13 = 2.0f * java.lang.Math.fma(sd[4], sd[6], -(sd[3] * sd[5]));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], sd[5] * sd[5]), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= java.lang.Math.fma(_t11, _t11, _t17) * 2.2737368E-13f) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]), 2.0f * java.lang.Math.fma(sd[3], sd[4], -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17));
        return dest;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t2 = sd[5] * sd[6];
        float _t11 = 2.0f * ((sd[3]) * (sd[4]) + (_t2));
        float _t12 = 2.0f * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]));
        float _t13 = 2.0f * ((sd[4]) * (sd[6]) - (sd[3] * sd[5]));
        float _t14 = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (sd[5] * sd[5]))));
        float _t15 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= ((_t11) * (_t11) + (_t17)) * 2.2737368E-13f) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(((_t14) * (2.0f * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]))) + (2.0f * ((sd[3]) * (sd[4]) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13)));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the Y, Z and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y) and store the result in {@code dest}.
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
     * Valid input: the rotation of this rigid transform must have unit length; {@code epsilon} must
     * lie in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYZX(float epsilon, @Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYZX_mulAdd(epsilon, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t2 = sd[5] * sd[6];
        float _t11 = 2.0f * java.lang.Math.fma(sd[3], sd[4], _t2);
        float _t12 = 2.0f * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]);
        float _t13 = 2.0f * java.lang.Math.fma(sd[4], sd[6], -(sd[3] * sd[5]));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], sd[5] * sd[5]), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= epsilon * java.lang.Math.fma(_t11, _t11, _t17)) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]), 2.0f * java.lang.Math.fma(sd[3], sd[4], -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17));
        return dest;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesYZX_mulAdd(float epsilon, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t2 = sd[5] * sd[6];
        float _t11 = 2.0f * ((sd[3]) * (sd[4]) + (_t2));
        float _t12 = 2.0f * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]));
        float _t13 = 2.0f * ((sd[4]) * (sd[6]) - (sd[3] * sd[5]));
        float _t14 = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (sd[5] * sd[5]))));
        float _t15 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= epsilon * ((_t11) * (_t11) + (_t17))) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(((_t14) * (2.0f * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]))) + (2.0f * ((sd[3]) * (sd[4]) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13)));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the Y, Z and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y) and store the result in {@code dest}.
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length; {@code epsilon} must
     * lie in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYZX(float epsilon, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYZX_mulAdd(epsilon, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t2 = sd[5] * sd[6];
        float _t11 = 2.0f * java.lang.Math.fma(sd[3], sd[4], _t2);
        float _t12 = 2.0f * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]);
        float _t13 = 2.0f * java.lang.Math.fma(sd[4], sd[6], -(sd[3] * sd[5]));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], sd[5] * sd[5]), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= epsilon * java.lang.Math.fma(_t11, _t11, _t17)) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]), 2.0f * java.lang.Math.fma(sd[3], sd[4], -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17));
        return dest;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd(float epsilon, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t2 = sd[5] * sd[6];
        float _t11 = 2.0f * ((sd[3]) * (sd[4]) + (_t2));
        float _t12 = 2.0f * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]));
        float _t13 = 2.0f * ((sd[4]) * (sd[6]) - (sd[3] * sd[5]));
        float _t14 = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (sd[5] * sd[5]))));
        float _t15 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= epsilon * ((_t11) * (_t11) + (_t17))) {
            dd[0] = 0.0f;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(((_t14) * (2.0f * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]))) + (2.0f * ((sd[3]) * (sd[4]) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13)));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the Z, X and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z) and store the result in {@code dest}.
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
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZXY(@Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZXY_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[5] * sd[5];
        float _t10 = 2.0f * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        float _t11 = 2.0f * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]);
        float _t12 = 2.0f * java.lang.Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]), 2.0f * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6])) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesZXY_mulAdd(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[5] * sd[5];
        float _t10 = 2.0f * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]));
        float _t11 = 2.0f * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]));
        float _t12 = 2.0f * ((sd[5]) * (sd[6]) - (sd[3] * sd[4]));
        float _t13 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(((_t13) * (2.0f * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]))) + (2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the Z, X and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z) and store the result in {@code dest}.
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZXY(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZXY_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[5] * sd[5];
        float _t10 = 2.0f * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        float _t11 = 2.0f * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]);
        float _t12 = 2.0f * java.lang.Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]), 2.0f * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6])) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[5] * sd[5];
        float _t10 = 2.0f * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]));
        float _t11 = 2.0f * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]));
        float _t12 = 2.0f * ((sd[5]) * (sd[6]) - (sd[3] * sd[4]));
        float _t13 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(((_t13) * (2.0f * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]))) + (2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the Z, X and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z) and store the result in {@code dest}.
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
     * Valid input: the rotation of this rigid transform must have unit length; {@code epsilon} must
     * lie in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZXY(float epsilon, @Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZXY_mulAdd(epsilon, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[5] * sd[5];
        float _t10 = 2.0f * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        float _t11 = 2.0f * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]);
        float _t12 = 2.0f * java.lang.Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]), 2.0f * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6])) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesZXY_mulAdd(float epsilon, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t1 = sd[5] * sd[5];
        float _t10 = 2.0f * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]));
        float _t11 = 2.0f * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]));
        float _t12 = 2.0f * ((sd[5]) * (sd[6]) - (sd[3] * sd[4]));
        float _t13 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(((_t13) * (2.0f * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]))) + (2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the Z, X and Y
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z) and store the result in {@code dest}.
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length; {@code epsilon} must
     * lie in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZXY(float epsilon, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZXY_mulAdd(epsilon, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[5] * sd[5];
        float _t10 = 2.0f * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        float _t11 = 2.0f * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]);
        float _t12 = 2.0f * java.lang.Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]), 2.0f * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6])) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd(float epsilon, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t1 = sd[5] * sd[5];
        float _t10 = 2.0f * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]));
        float _t11 = 2.0f * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]));
        float _t12 = 2.0f * ((sd[5]) * (sd[6]) - (sd[3] * sd[4]));
        float _t13 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        dd[0] = Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dd[1] = 0.0f;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(((_t13) * (2.0f * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]))) + (2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the Z, Y and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z) and store the result in {@code dest}.
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
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZYX(@Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZYX_mulAdd(dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t8 = 2.0f * java.lang.Math.fma(sd[3], sd[4], _t1);
        float _t9 = 2.0f * java.lang.Math.fma(sd[4], sd[6], -sd[3] * sd[5]);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], _t0), 1.0f);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], _t0), 1.0f);
        float _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= java.lang.Math.fma(_t9, _t9, _t13) * 2.2737368E-13f) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * java.lang.Math.fma(sd[5], sd[6], -sd[3] * sd[4]), _t11);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]), _t10 * -2.0f * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6]))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(sd[3], sd[4], -_t1)));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesZYX_mulAdd(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t8 = 2.0f * ((sd[3]) * (sd[4]) + (_t1));
        float _t9 = 2.0f * ((sd[4]) * (sd[6]) - (sd[3] * sd[5]));
        float _t10 = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (_t0))));
        float _t11 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (_t0))));
        float _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= ((_t9) * (_t9) + (_t13)) * 2.2737368E-13f) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * ((sd[5]) * (sd[6]) - (sd[3] * sd[4])), _t11);
        } else {
            dd[0] = Math.atan2(((_t8) * (2.0f * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]))) - (_t10 * 2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])))), ((_t11) * (_t10) - (_t8 * 2.0f * ((sd[3]) * (sd[4]) - (_t1)))));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the Z, Y and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z) and store the result in {@code dest}.
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZYX(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZYX_mulAdd(dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t8 = 2.0f * java.lang.Math.fma(sd[3], sd[4], _t1);
        float _t9 = 2.0f * java.lang.Math.fma(sd[4], sd[6], -sd[3] * sd[5]);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], _t0), 1.0f);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], _t0), 1.0f);
        float _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= java.lang.Math.fma(_t9, _t9, _t13) * 2.2737368E-13f) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * java.lang.Math.fma(sd[5], sd[6], -sd[3] * sd[4]), _t11);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]), _t10 * -2.0f * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6]))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(sd[3], sd[4], -_t1)));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd(@Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t8 = 2.0f * ((sd[3]) * (sd[4]) + (_t1));
        float _t9 = 2.0f * ((sd[4]) * (sd[6]) - (sd[3] * sd[5]));
        float _t10 = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (_t0))));
        float _t11 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (_t0))));
        float _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= ((_t9) * (_t9) + (_t13)) * 2.2737368E-13f) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * ((sd[5]) * (sd[6]) - (sd[3] * sd[4])), _t11);
        } else {
            dd[0] = Math.atan2(((_t8) * (2.0f * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]))) - (_t10 * 2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])))), ((_t11) * (_t10) - (_t8 * 2.0f * ((sd[3]) * (sd[4]) - (_t1)))));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the Z, Y and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z) and store the result in {@code dest}.
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
     * Valid input: the rotation of this rigid transform must have unit length; {@code epsilon} must
     * lie in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZYX(float epsilon, @Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZYX_mulAdd(epsilon, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t8 = 2.0f * java.lang.Math.fma(sd[3], sd[4], _t1);
        float _t9 = 2.0f * java.lang.Math.fma(sd[4], sd[6], -sd[3] * sd[5]);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], _t0), 1.0f);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], _t0), 1.0f);
        float _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= epsilon * java.lang.Math.fma(_t9, _t9, _t13)) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * java.lang.Math.fma(sd[5], sd[6], -sd[3] * sd[4]), _t11);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]), _t10 * -2.0f * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6]))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(sd[3], sd[4], -_t1)));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesZYX_mulAdd(float epsilon, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t8 = 2.0f * ((sd[3]) * (sd[4]) + (_t1));
        float _t9 = 2.0f * ((sd[4]) * (sd[6]) - (sd[3] * sd[5]));
        float _t10 = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (_t0))));
        float _t11 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (_t0))));
        float _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= epsilon * ((_t9) * (_t9) + (_t13))) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * ((sd[5]) * (sd[6]) - (sd[3] * sd[4])), _t11);
        } else {
            dd[0] = Math.atan2(((_t8) * (2.0f * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]))) - (_t10 * 2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])))), ((_t11) * (_t10) - (_t8 * 2.0f * ((sd[3]) * (sd[4]) - (_t1)))));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this rigid transform for rotations about the Z, Y and X
     * axes, in that order (intrinsic rotations, each about the axes the previous ones turned: the
     * matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z) and store the result in {@code dest}.
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length; {@code epsilon} must
     * lie in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZYX(float epsilon, @Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZYX_mulAdd(epsilon, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t8 = 2.0f * java.lang.Math.fma(sd[3], sd[4], _t1);
        float _t9 = 2.0f * java.lang.Math.fma(sd[4], sd[6], -sd[3] * sd[5]);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[4], sd[4], _t0), 1.0f);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(sd[3], sd[3], _t0), 1.0f);
        float _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= epsilon * java.lang.Math.fma(_t9, _t9, _t13)) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * java.lang.Math.fma(sd[5], sd[6], -sd[3] * sd[4]), _t11);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]), _t10 * -2.0f * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6]))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(sd[3], sd[4], -_t1)));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd(float epsilon, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[5] * sd[5];
        float _t1 = sd[5] * sd[6];
        float _t8 = 2.0f * ((sd[3]) * (sd[4]) + (_t1));
        float _t9 = 2.0f * ((sd[4]) * (sd[6]) - (sd[3] * sd[5]));
        float _t10 = ((1.0f) - (2.0f) * (((sd[4]) * (sd[4]) + (_t0))));
        float _t11 = ((1.0f) - (2.0f) * (((sd[3]) * (sd[3]) + (_t0))));
        float _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= epsilon * ((_t9) * (_t9) + (_t13))) {
            dd[0] = 0.0f;
            dd[2] = Math.atan2(2.0f * ((sd[5]) * (sd[6]) - (sd[3] * sd[4])), _t11);
        } else {
            dd[0] = Math.atan2(((_t8) * (2.0f * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]))) - (_t10 * 2.0f * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])))), ((_t11) * (_t10) - (_t8 * 2.0f * ((sd[3]) * (sd[4]) - (_t1)))));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13));
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
    @Mutated public FloatRigid makeRotationAxis(float angle, Float3R axis) {
        float[] axisData = ((Float3Impl) axis).data;
        float axisX = axisData[0];
        float axisY = axisData[1];
        float axisZ = axisData[2];
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = axisX * _t1;
        dd[4] = axisY * _t1;
        dd[5] = axisZ * _t1;
        dd[6] = Math.cosWithSin(_t0);
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
    @Mutated public FloatRigid makeRotationAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = axisX * _t1;
        dd[4] = axisY * _t1;
        dd[5] = axisZ * _t1;
        dd[6] = Math.cosWithSin(_t0);
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = Math.sinWithCos(_t0);
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = Math.cosWithSin(_t0);
        return this;
    }

    /**
     * Set this rigid transform to a rotation of {@code angleX}, {@code angleY} and {@code angleZ}
     * radians about the X, Y and Z axes, in that order (intrinsic rotations, each about the axes
     * the previous ones turned: the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the fixed Z axis first, then Y, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this
     */
    @Mutated public FloatRigid makeRotationXYZ(float angleX, float angleY, float angleZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationXYZ_mulAdd(angleX, angleY, angleZ);
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        dd[4] = java.lang.Math.fma(_t10, _t5, _t9 * -_t8);
        dd[5] = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        dd[6] = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        return this;
    }

    /** {@code makeRotationXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid makeRotationXYZ_mulAdd(float angleX, float angleY, float angleZ) {
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = ((_t9) * (_t5) + (_t10 * _t8));
        dd[4] = ((_t10) * (_t5) - (_t9 * _t8));
        dd[5] = ((_t11) * (_t5) + (_t12 * _t8));
        dd[6] = ((_t12) * (_t5) - (_t11 * _t8));
        return this;
    }

    /**
     * Set this rigid transform to a rotation of {@code angleX}, {@code angleZ} and {@code angleY}
     * radians about the X, Z and Y axes, in that order (intrinsic rotations, each about the axes
     * the previous ones turned: the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the fixed Y axis first, then Z, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this
     */
    @Mutated public FloatRigid makeRotationXZY(float angleX, float angleZ, float angleY) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationXZY_mulAdd(angleX, angleZ, angleY);
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = java.lang.Math.fma(_t9, _t5, -(_t10 * _t8));
        dd[4] = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        dd[5] = java.lang.Math.fma(_t9, _t8, _t10 * _t5);
        dd[6] = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        return this;
    }

    /** {@code makeRotationXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid makeRotationXZY_mulAdd(float angleX, float angleZ, float angleY) {
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = ((_t9) * (_t5) - (_t10 * _t8));
        dd[4] = ((_t11) * (_t8) - (_t12 * _t5));
        dd[5] = ((_t9) * (_t8) + (_t10 * _t5));
        dd[6] = ((_t12) * (_t8) + (_t11 * _t5));
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
        VEC_2.intoArray(dd, 0);
        dd[4] = Math.sinWithCos(_t0);
        dd[5] = 0.0f;
        dd[6] = Math.cosWithSin(_t0);
        return this;
    }

    /**
     * Set this rigid transform to a rotation of {@code angleY}, {@code angleX} and {@code angleZ}
     * radians about the Y, X and Z axes, in that order (intrinsic rotations, each about the axes
     * the previous ones turned: the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the fixed Z axis first, then X, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this
     */
    @Mutated public FloatRigid makeRotationYXZ(float angleY, float angleX, float angleZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationYXZ_mulAdd(angleY, angleX, angleZ);
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        dd[4] = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        dd[5] = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        dd[6] = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        return this;
    }

    /** {@code makeRotationYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid makeRotationYXZ_mulAdd(float angleY, float angleX, float angleZ) {
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = ((_t9) * (_t5) + (_t10 * _t8));
        dd[4] = ((_t10) * (_t5) - (_t9 * _t8));
        dd[5] = ((_t11) * (_t8) - (_t12 * _t5));
        dd[6] = ((_t12) * (_t8) + (_t11 * _t5));
        return this;
    }

    /**
     * Set this rigid transform to a rotation of {@code angleY}, {@code angleZ} and {@code angleX}
     * radians about the Y, Z and X axes, in that order (intrinsic rotations, each about the axes
     * the previous ones turned: the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the fixed X axis first, then Z, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this
     */
    @Mutated public FloatRigid makeRotationYZX(float angleY, float angleZ, float angleX) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationYZX_mulAdd(angleY, angleZ, angleX);
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        dd[4] = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        dd[5] = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        dd[6] = java.lang.Math.fma(_t10, _t5, _t9 * -_t8);
        return this;
    }

    /** {@code makeRotationYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid makeRotationYZX_mulAdd(float angleY, float angleZ, float angleX) {
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = ((_t9) * (_t5) + (_t10 * _t8));
        dd[4] = ((_t11) * (_t5) + (_t12 * _t8));
        dd[5] = ((_t12) * (_t5) - (_t11 * _t8));
        dd[6] = ((_t10) * (_t5) - (_t9 * _t8));
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
        VEC_2.intoArray(dd, 0);
        dd[4] = 0.0f;
        dd[5] = Math.sinWithCos(_t0);
        dd[6] = Math.cosWithSin(_t0);
        return this;
    }

    /**
     * Set this rigid transform to a rotation of {@code angleZ}, {@code angleX} and {@code angleY}
     * radians about the Z, X and Y axes, in that order (intrinsic rotations, each about the axes
     * the previous ones turned: the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the fixed Y axis first, then X, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this
     */
    @Mutated public FloatRigid makeRotationZXY(float angleZ, float angleX, float angleY) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationZXY_mulAdd(angleZ, angleX, angleY);
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        dd[4] = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        dd[5] = java.lang.Math.fma(_t9, _t8, _t10 * _t5);
        dd[6] = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        return this;
    }

    /** {@code makeRotationZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid makeRotationZXY_mulAdd(float angleZ, float angleX, float angleY) {
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = ((_t9) * (_t5) - (_t10 * _t8));
        dd[4] = ((_t11) * (_t5) + (_t12 * _t8));
        dd[5] = ((_t9) * (_t8) + (_t10 * _t5));
        dd[6] = ((_t12) * (_t5) - (_t11 * _t8));
        return this;
    }

    /**
     * Set this rigid transform to a rotation of {@code angleZ}, {@code angleY} and {@code angleX}
     * radians about the Z, Y and X axes, in that order (intrinsic rotations, each about the axes
     * the previous ones turned: the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the fixed X axis first, then Y, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this
     */
    @Mutated public FloatRigid makeRotationZYX(float angleZ, float angleY, float angleX) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationZYX_mulAdd(angleZ, angleY, angleX);
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = java.lang.Math.fma(_t9, _t5, -(_t10 * _t8));
        dd[4] = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        dd[5] = java.lang.Math.fma(_t12, _t8, -(_t11 * _t5));
        dd[6] = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        return this;
    }

    /** {@code makeRotationZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid makeRotationZYX_mulAdd(float angleZ, float angleY, float angleX) {
        float[] dd = this.data;
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
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = ((_t9) * (_t5) - (_t10 * _t8));
        dd[4] = ((_t11) * (_t8) + (_t12 * _t5));
        dd[5] = ((_t12) * (_t8) - (_t11 * _t5));
        dd[6] = ((_t10) * (_t5) + (_t9 * _t8));
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
        float[] rotationData = ((FloatQuatImpl) rotation).data;
        float rotationX = rotationData[0];
        float rotationY = rotationData[1];
        float rotationZ = rotationData[2];
        float rotationW = rotationData[3];
        if (!org.joml2.internal.FmaSwitch.ON) return rotate_mulAdd(rotationX, rotationY, rotationZ, rotationW, dest);
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = java.lang.Math.fma(rotationX, sd[6], rotationW * sd[3]) + java.lang.Math.fma(rotationZ, sd[4], -(rotationY * sd[5]));
        float _buf1 = java.lang.Math.fma(rotationX, sd[5], rotationW * sd[4]) + java.lang.Math.fma(rotationY, sd[6], rotationZ * -sd[3]);
        float _buf2 = java.lang.Math.fma(rotationY, sd[3], rotationZ * sd[6]) + java.lang.Math.fma(rotationW, sd[5], -(rotationX * sd[4]));
        dd[6] = java.lang.Math.fma(rotationW, sd[6], rotationX * -sd[3]) - java.lang.Math.fma(rotationY, sd[4], rotationZ * sd[5]);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
        float[] rotationData = ((FloatQuatImpl) rotation).data;
        float rotationX = rotationData[0];
        float rotationY = rotationData[1];
        float rotationZ = rotationData[2];
        float rotationW = rotationData[3];
        if (!org.joml2.internal.FmaSwitch.ON) return rotate_mulAdd(rotationX, rotationY, rotationZ, rotationW, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(rotationX, sd[6], rotationW * sd[3]) + java.lang.Math.fma(rotationZ, sd[4], -(rotationY * sd[5]));
        dd[4] = java.lang.Math.fma(rotationX, sd[5], rotationW * sd[4]) + java.lang.Math.fma(rotationY, sd[6], rotationZ * -sd[3]);
        dd[5] = java.lang.Math.fma(rotationY, sd[3], rotationZ * sd[6]) + java.lang.Math.fma(rotationW, sd[5], -(rotationX * sd[4]));
        dd[6] = java.lang.Math.fma(rotationW, sd[6], rotationX * -sd[3]) - java.lang.Math.fma(rotationY, sd[4], rotationZ * sd[5]);
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
    public FloatRigid rotate(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated FloatRigid dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return rotate_mulAdd(rotationX, rotationY, rotationZ, rotationW, dest);
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = java.lang.Math.fma(rotationX, sd[6], rotationW * sd[3]) + java.lang.Math.fma(rotationZ, sd[4], -(rotationY * sd[5]));
        float _buf1 = java.lang.Math.fma(rotationX, sd[5], rotationW * sd[4]) + java.lang.Math.fma(rotationY, sd[6], rotationZ * -sd[3]);
        float _buf2 = java.lang.Math.fma(rotationY, sd[3], rotationZ * sd[6]) + java.lang.Math.fma(rotationW, sd[5], -(rotationX * sd[4]));
        dd[6] = java.lang.Math.fma(rotationW, sd[6], rotationX * -sd[3]) - java.lang.Math.fma(rotationY, sd[4], rotationZ * sd[5]);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** {@code rotate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid rotate_mulAdd(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = ((rotationX) * (sd[6]) + (rotationW * sd[3])) + ((rotationZ) * (sd[4]) - (rotationY * sd[5]));
        float _buf1 = ((rotationX) * (sd[5]) + (rotationW * sd[4])) + ((rotationY) * (sd[6]) - (rotationZ * sd[3]));
        float _buf2 = ((rotationY) * (sd[3]) + (rotationZ * sd[6])) + ((rotationW) * (sd[5]) - (rotationX * sd[4]));
        dd[6] = ((rotationW) * (sd[6]) - (rotationX * sd[3])) - ((rotationY) * (sd[4]) + (rotationZ * sd[5]));
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotate_mulAdd(rotationX, rotationY, rotationZ, rotationW, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(rotationX, sd[6], rotationW * sd[3]) + java.lang.Math.fma(rotationZ, sd[4], -(rotationY * sd[5]));
        dd[4] = java.lang.Math.fma(rotationX, sd[5], rotationW * sd[4]) + java.lang.Math.fma(rotationY, sd[6], rotationZ * -sd[3]);
        dd[5] = java.lang.Math.fma(rotationY, sd[3], rotationZ * sd[6]) + java.lang.Math.fma(rotationW, sd[5], -(rotationX * sd[4]));
        dd[6] = java.lang.Math.fma(rotationW, sd[6], rotationX * -sd[3]) - java.lang.Math.fma(rotationY, sd[4], rotationZ * sd[5]);
        return dest;
    }

    /** {@code rotate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid rotate_mulAdd(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((rotationX) * (sd[6]) + (rotationW * sd[3])) + ((rotationZ) * (sd[4]) - (rotationY * sd[5]));
        dd[4] = ((rotationX) * (sd[5]) + (rotationW * sd[4])) + ((rotationY) * (sd[6]) - (rotationZ * sd[3]));
        dd[5] = ((rotationY) * (sd[3]) + (rotationZ * sd[6])) + ((rotationW) * (sd[5]) - (rotationX * sd[4]));
        dd[6] = ((rotationW) * (sd[6]) - (rotationX * sd[3])) - ((rotationY) * (sd[4]) + (rotationZ * sd[5]));
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
        float[] axisData = ((Float3Impl) axis).data;
        float axisX = axisData[0];
        float axisY = axisData[1];
        float axisZ = axisData[2];
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        if (org.joml2.internal.FmaSwitch.ON) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
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
        float[] axisData = ((Float3Impl) axis).data;
        return rotateAxis(angle, axisData[0], axisData[1], axisData[2], dest);
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
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        if (org.joml2.internal.FmaSwitch.ON) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid rotateAxis_fma(float angle, float axisX, float axisY, float axisZ, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateAxis_s58b7dfcb_1_fma(dest, sd, dd, _t1, _t3, _t4, _t5, java.lang.Math.fma(sd[3], _t1, sd[6] * _t3) + java.lang.Math.fma(sd[4], _t4, -(sd[5] * _t5)), java.lang.Math.fma(sd[4], _t1, sd[5] * _t3) + java.lang.Math.fma(sd[6], _t5, -sd[3] * _t4), java.lang.Math.fma(sd[3], _t5, sd[6] * _t4) + java.lang.Math.fma(sd[5], _t1, -(sd[4] * _t3)));
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid rotateAxis_mulAdd(float angle, float axisX, float axisY, float axisZ, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = ((sd[3]) * (_t1) + (sd[6] * _t3)) + ((sd[4]) * (_t4) - (sd[5] * _t5));
        float _buf1 = ((sd[4]) * (_t1) + (sd[5] * _t3)) + ((sd[6]) * (_t5) - (sd[3] * _t4));
        float _buf2 = ((sd[3]) * (_t5) + (sd[6] * _t4)) + ((sd[5]) * (_t1) - (sd[4] * _t3));
        dd[6] = ((sd[6]) * (_t1) - (sd[3] * _t3)) - ((sd[4]) * (_t5) + (sd[5] * _t4));
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateAxis_s58b7dfcb_1_fma(FloatRigid dest, float[] sd, float[] dd, float _t1, float _t3, float _t4, float _t5, float _buf0, float _buf1, float _buf2) {
        dd[6] = java.lang.Math.fma(sd[6], _t1, -sd[3] * _t3) - java.lang.Math.fma(sd[4], _t5, sd[5] * _t4);
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
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        if (!org.joml2.internal.FmaSwitch.ON) return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(sd[3], _t1, sd[6] * _t3) + java.lang.Math.fma(sd[4], _t4, -(sd[5] * _t5));
        dd[4] = java.lang.Math.fma(sd[4], _t1, sd[5] * _t3) + java.lang.Math.fma(sd[6], _t5, -sd[3] * _t4);
        return rotateAxis_saa5ea00c_1_fma(dest, sd, dd, _t1, _t3, _t4, _t5);
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid rotateAxis_mulAdd(float angle, float axisX, float axisY, float axisZ, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((sd[3]) * (_t1) + (sd[6] * _t3)) + ((sd[4]) * (_t4) - (sd[5] * _t5));
        dd[4] = ((sd[4]) * (_t1) + (sd[5] * _t3)) + ((sd[6]) * (_t5) - (sd[3] * _t4));
        return rotateAxis_saa5ea00c_1_mulAdd(dest, sd, dd, _t1, _t3, _t4, _t5);
    }

    /**
     * Piece 2 of {@code rotateAxis}, split to fit the inline budget. Shared by the identical
     * private paths of {@code rotateAxis} and {@code rotateYXZ}; reached only through them.
     */
    private DoubleRigid rotateAxis_saa5ea00c_1_fma(DoubleRigid dest, float[] sd, double[] dd, float _t1, float _t3, float _t4, float _t5) {
        dd[5] = java.lang.Math.fma(sd[3], _t5, sd[6] * _t4) + java.lang.Math.fma(sd[5], _t1, -(sd[4] * _t3));
        dd[6] = java.lang.Math.fma(sd[6], _t1, -sd[3] * _t3) - java.lang.Math.fma(sd[4], _t5, sd[5] * _t4);
        return dest;
    }

    /**
     * Piece 2 of {@code rotateAxis}, split to fit the inline budget. Shared by the identical
     * private paths of {@code rotateAxis} and {@code rotateYXZ}; reached only through them.
     */
    private DoubleRigid rotateAxis_saa5ea00c_1_mulAdd(DoubleRigid dest, float[] sd, double[] dd, float _t1, float _t3, float _t4, float _t5) {
        dd[5] = ((sd[3]) * (_t5) + (sd[6] * _t4)) + ((sd[5]) * (_t1) - (sd[4] * _t3));
        dd[6] = ((sd[6]) * (_t1) - (sd[3] * _t3)) - ((sd[4]) * (_t5) + (sd[5] * _t4));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float[] dd = ((FloatRigidImpl) dest).data;
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            float _buf0 = java.lang.Math.fma(sd[3], _t1, sd[6] * _t2);
            float _buf1 = java.lang.Math.fma(sd[4], _t1, sd[5] * _t2);
            dd[5] = java.lang.Math.fma(sd[5], _t1, -(sd[4] * _t2));
            dd[6] = java.lang.Math.fma(sd[6], _t1, -(sd[3] * _t2));
            dd[3] = _buf0;
            dd[4] = _buf1;
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((FloatRigidImpl) dest).data;
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            float _buf0 = ((sd[3]) * (_t1) + (sd[6] * _t2));
            float _buf1 = ((sd[4]) * (_t1) + (sd[5] * _t2));
            dd[5] = ((sd[5]) * (_t1) - (sd[4] * _t2));
            dd[6] = ((sd[6]) * (_t1) - (sd[3] * _t2));
            dd[3] = _buf0;
            dd[4] = _buf1;
            return dest;
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((DoubleRigidImpl) dest).data;
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            dd[3] = java.lang.Math.fma(sd[3], _t1, sd[6] * _t2);
            dd[4] = java.lang.Math.fma(sd[4], _t1, sd[5] * _t2);
            dd[5] = java.lang.Math.fma(sd[5], _t1, -(sd[4] * _t2));
            dd[6] = java.lang.Math.fma(sd[6], _t1, -(sd[3] * _t2));
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((DoubleRigidImpl) dest).data;
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            dd[3] = ((sd[3]) * (_t1) + (sd[6] * _t2));
            dd[4] = ((sd[4]) * (_t1) + (sd[5] * _t2));
            dd[5] = ((sd[5]) * (_t1) - (sd[4] * _t2));
            dd[6] = ((sd[6]) * (_t1) - (sd[3] * _t2));
            return dest;
        }
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X), to this rigid transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateXYZ_mulAdd(angleX, angleY, angleZ, dest);
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateXYZ_s8702150_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[3], _t21, sd[6] * _t19) + java.lang.Math.fma(sd[4], _t20, -(sd[5] * _t22)), java.lang.Math.fma(sd[4], _t21, sd[5] * _t19) + java.lang.Math.fma(sd[6], _t22, -sd[3] * _t20));
    }

    /** {@code rotateXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid rotateXYZ_mulAdd(float angleX, float angleY, float angleZ, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateXYZ_s8702150_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, ((sd[3]) * (_t21) + (sd[6] * _t19)) + ((sd[4]) * (_t20) - (sd[5] * _t22)), ((sd[4]) * (_t21) + (sd[5] * _t19)) + ((sd[6]) * (_t22) - (sd[3] * _t20)));
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateXYZ_s8702150_1_fma(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = java.lang.Math.fma(sd[3], _t22, sd[6] * _t20) + java.lang.Math.fma(sd[5], _t21, -(sd[4] * _t19));
        dd[6] = java.lang.Math.fma(sd[6], _t21, -sd[3] * _t19) - java.lang.Math.fma(sd[4], _t22, sd[5] * _t20);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateXYZ_s8702150_1_mulAdd(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = ((sd[3]) * (_t22) + (sd[6] * _t20)) + ((sd[5]) * (_t21) - (sd[4] * _t19));
        dd[6] = ((sd[6]) * (_t21) - (sd[3] * _t19)) - ((sd[4]) * (_t22) + (sd[5] * _t20));
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X), to this rigid transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateXYZ_mulAdd(angleX, angleY, angleZ, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(sd[3], _t21, sd[6] * _t19) + java.lang.Math.fma(sd[4], _t20, -(sd[5] * _t22));
        dd[4] = java.lang.Math.fma(sd[4], _t21, sd[5] * _t19) + java.lang.Math.fma(sd[6], _t22, -sd[3] * _t20);
        return rotateXYZ_s35646349_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** {@code rotateXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid rotateXYZ_mulAdd(float angleX, float angleY, float angleZ, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((sd[3]) * (_t21) + (sd[6] * _t19)) + ((sd[4]) * (_t20) - (sd[5] * _t22));
        dd[4] = ((sd[4]) * (_t21) + (sd[5] * _t19)) + ((sd[6]) * (_t22) - (sd[3] * _t20));
        return rotateXYZ_s35646349_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateXYZ_s35646349_1_fma(DoubleRigid dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[5] = java.lang.Math.fma(sd[3], _t22, sd[6] * _t20) + java.lang.Math.fma(sd[5], _t21, -(sd[4] * _t19));
        dd[6] = java.lang.Math.fma(sd[6], _t21, -sd[3] * _t19) - java.lang.Math.fma(sd[4], _t22, sd[5] * _t20);
        return dest;
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateXYZ_s35646349_1_mulAdd(DoubleRigid dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[5] = ((sd[3]) * (_t22) + (sd[6] * _t20)) + ((sd[5]) * (_t21) - (sd[4] * _t19));
        dd[6] = ((sd[6]) * (_t21) - (sd[3] * _t19)) - ((sd[4]) * (_t22) + (sd[5] * _t20));
        return dest;
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X), to this rigid transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateXZY_mulAdd(angleX, angleZ, angleY, dest);
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateXZY_s3be93c7a_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[3], _t19, sd[6] * _t21) + java.lang.Math.fma(sd[4], _t20, -(sd[5] * _t22)), java.lang.Math.fma(sd[4], _t19, sd[5] * _t21) + java.lang.Math.fma(sd[6], _t22, -sd[3] * _t20));
    }

    /** {@code rotateXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid rotateXZY_mulAdd(float angleX, float angleZ, float angleY, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateXZY_s3be93c7a_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, ((sd[3]) * (_t19) + (sd[6] * _t21)) + ((sd[4]) * (_t20) - (sd[5] * _t22)), ((sd[4]) * (_t19) + (sd[5] * _t21)) + ((sd[6]) * (_t22) - (sd[3] * _t20)));
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateXZY_s3be93c7a_1_fma(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = java.lang.Math.fma(sd[3], _t22, sd[6] * _t20) + java.lang.Math.fma(sd[5], _t19, -(sd[4] * _t21));
        dd[6] = java.lang.Math.fma(sd[6], _t19, -sd[3] * _t21) - java.lang.Math.fma(sd[4], _t22, sd[5] * _t20);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateXZY_s3be93c7a_1_mulAdd(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = ((sd[3]) * (_t22) + (sd[6] * _t20)) + ((sd[5]) * (_t19) - (sd[4] * _t21));
        dd[6] = ((sd[6]) * (_t19) - (sd[3] * _t21)) - ((sd[4]) * (_t22) + (sd[5] * _t20));
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X), to this rigid transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateXZY_mulAdd(angleX, angleZ, angleY, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(sd[3], _t19, sd[6] * _t21) + java.lang.Math.fma(sd[4], _t20, -(sd[5] * _t22));
        dd[4] = java.lang.Math.fma(sd[4], _t19, sd[5] * _t21) + java.lang.Math.fma(sd[6], _t22, -sd[3] * _t20);
        return rotateXZY_s4456c68f_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** {@code rotateXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid rotateXZY_mulAdd(float angleX, float angleZ, float angleY, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((sd[3]) * (_t19) + (sd[6] * _t21)) + ((sd[4]) * (_t20) - (sd[5] * _t22));
        dd[4] = ((sd[4]) * (_t19) + (sd[5] * _t21)) + ((sd[6]) * (_t22) - (sd[3] * _t20));
        return rotateXZY_s4456c68f_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateXZY_s4456c68f_1_fma(DoubleRigid dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[5] = java.lang.Math.fma(sd[3], _t22, sd[6] * _t20) + java.lang.Math.fma(sd[5], _t19, -(sd[4] * _t21));
        dd[6] = java.lang.Math.fma(sd[6], _t19, -sd[3] * _t21) - java.lang.Math.fma(sd[4], _t22, sd[5] * _t20);
        return dest;
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateXZY_s4456c68f_1_mulAdd(DoubleRigid dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[5] = ((sd[3]) * (_t22) + (sd[6] * _t20)) + ((sd[5]) * (_t19) - (sd[4] * _t21));
        dd[6] = ((sd[6]) * (_t19) - (sd[3] * _t21)) - ((sd[4]) * (_t22) + (sd[5] * _t20));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float[] dd = ((FloatRigidImpl) dest).data;
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            float _buf0 = java.lang.Math.fma(sd[3], _t1, -(sd[5] * _t2));
            float _buf1 = java.lang.Math.fma(sd[4], _t1, sd[6] * _t2);
            dd[5] = java.lang.Math.fma(sd[3], _t2, sd[5] * _t1);
            dd[6] = java.lang.Math.fma(sd[6], _t1, -(sd[4] * _t2));
            dd[3] = _buf0;
            dd[4] = _buf1;
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((FloatRigidImpl) dest).data;
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            float _buf0 = ((sd[3]) * (_t1) - (sd[5] * _t2));
            float _buf1 = ((sd[4]) * (_t1) + (sd[6] * _t2));
            dd[5] = ((sd[3]) * (_t2) + (sd[5] * _t1));
            dd[6] = ((sd[6]) * (_t1) - (sd[4] * _t2));
            dd[3] = _buf0;
            dd[4] = _buf1;
            return dest;
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((DoubleRigidImpl) dest).data;
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            dd[3] = java.lang.Math.fma(sd[3], _t1, -(sd[5] * _t2));
            dd[4] = java.lang.Math.fma(sd[4], _t1, sd[6] * _t2);
            dd[5] = java.lang.Math.fma(sd[3], _t2, sd[5] * _t1);
            dd[6] = java.lang.Math.fma(sd[6], _t1, -(sd[4] * _t2));
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((DoubleRigidImpl) dest).data;
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            dd[3] = ((sd[3]) * (_t1) - (sd[5] * _t2));
            dd[4] = ((sd[4]) * (_t1) + (sd[6] * _t2));
            dd[5] = ((sd[3]) * (_t2) + (sd[5] * _t1));
            dd[6] = ((sd[6]) * (_t1) - (sd[4] * _t2));
            return dest;
        }
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y), to this rigid transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateYXZ_mulAdd(angleY, angleX, angleZ, dest);
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateYXZ_scb425016_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[3], _t19, sd[6] * _t20) + java.lang.Math.fma(sd[4], _t21, -(sd[5] * _t22)), java.lang.Math.fma(sd[4], _t19, sd[5] * _t20) + java.lang.Math.fma(sd[6], _t22, -sd[3] * _t21));
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid rotateYXZ_mulAdd(float angleY, float angleX, float angleZ, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateYXZ_scb425016_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, ((sd[3]) * (_t19) + (sd[6] * _t20)) + ((sd[4]) * (_t21) - (sd[5] * _t22)), ((sd[4]) * (_t19) + (sd[5] * _t20)) + ((sd[6]) * (_t22) - (sd[3] * _t21)));
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateYXZ_scb425016_1_fma(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = java.lang.Math.fma(sd[3], _t22, sd[6] * _t21) + java.lang.Math.fma(sd[5], _t19, -(sd[4] * _t20));
        dd[6] = java.lang.Math.fma(sd[6], _t19, -sd[3] * _t20) - java.lang.Math.fma(sd[4], _t22, sd[5] * _t21);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateYXZ_scb425016_1_mulAdd(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = ((sd[3]) * (_t22) + (sd[6] * _t21)) + ((sd[5]) * (_t19) - (sd[4] * _t20));
        dd[6] = ((sd[6]) * (_t19) - (sd[3] * _t20)) - ((sd[4]) * (_t22) + (sd[5] * _t21));
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y), to this rigid transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateYXZ_mulAdd(angleY, angleX, angleZ, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(sd[3], _t19, sd[6] * _t20) + java.lang.Math.fma(sd[4], _t21, -(sd[5] * _t22));
        dd[4] = java.lang.Math.fma(sd[4], _t19, sd[5] * _t20) + java.lang.Math.fma(sd[6], _t22, -sd[3] * _t21);
        return rotateAxis_saa5ea00c_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid rotateYXZ_mulAdd(float angleY, float angleX, float angleZ, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((sd[3]) * (_t19) + (sd[6] * _t20)) + ((sd[4]) * (_t21) - (sd[5] * _t22));
        dd[4] = ((sd[4]) * (_t19) + (sd[5] * _t20)) + ((sd[6]) * (_t22) - (sd[3] * _t21));
        return rotateAxis_saa5ea00c_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y), to this rigid transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateYZX_mulAdd(angleY, angleZ, angleX, dest);
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateYZX_sc1b8258e_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[3], _t21, sd[6] * _t19) + java.lang.Math.fma(sd[4], _t22, -(sd[5] * _t20)), java.lang.Math.fma(sd[4], _t21, sd[5] * _t19) + java.lang.Math.fma(sd[6], _t20, -sd[3] * _t22));
    }

    /** {@code rotateYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid rotateYZX_mulAdd(float angleY, float angleZ, float angleX, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateYZX_sc1b8258e_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, ((sd[3]) * (_t21) + (sd[6] * _t19)) + ((sd[4]) * (_t22) - (sd[5] * _t20)), ((sd[4]) * (_t21) + (sd[5] * _t19)) + ((sd[6]) * (_t20) - (sd[3] * _t22)));
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateYZX_sc1b8258e_1_fma(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = java.lang.Math.fma(sd[3], _t20, sd[6] * _t22) + java.lang.Math.fma(sd[5], _t21, -(sd[4] * _t19));
        dd[6] = java.lang.Math.fma(sd[6], _t21, -sd[3] * _t19) - java.lang.Math.fma(sd[4], _t20, sd[5] * _t22);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateYZX_sc1b8258e_1_mulAdd(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = ((sd[3]) * (_t20) + (sd[6] * _t22)) + ((sd[5]) * (_t21) - (sd[4] * _t19));
        dd[6] = ((sd[6]) * (_t21) - (sd[3] * _t19)) - ((sd[4]) * (_t20) + (sd[5] * _t22));
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y), to this rigid transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateYZX_mulAdd(angleY, angleZ, angleX, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(sd[3], _t21, sd[6] * _t19) + java.lang.Math.fma(sd[4], _t22, -(sd[5] * _t20));
        dd[4] = java.lang.Math.fma(sd[4], _t21, sd[5] * _t19) + java.lang.Math.fma(sd[6], _t20, -sd[3] * _t22);
        return rotateYZX_sd02c1f53_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** {@code rotateYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid rotateYZX_mulAdd(float angleY, float angleZ, float angleX, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((sd[3]) * (_t21) + (sd[6] * _t19)) + ((sd[4]) * (_t22) - (sd[5] * _t20));
        dd[4] = ((sd[4]) * (_t21) + (sd[5] * _t19)) + ((sd[6]) * (_t20) - (sd[3] * _t22));
        return rotateYZX_sd02c1f53_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateYZX_sd02c1f53_1_fma(DoubleRigid dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[5] = java.lang.Math.fma(sd[3], _t20, sd[6] * _t22) + java.lang.Math.fma(sd[5], _t21, -(sd[4] * _t19));
        dd[6] = java.lang.Math.fma(sd[6], _t21, -sd[3] * _t19) - java.lang.Math.fma(sd[4], _t20, sd[5] * _t22);
        return dest;
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateYZX_sd02c1f53_1_mulAdd(DoubleRigid dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[5] = ((sd[3]) * (_t20) + (sd[6] * _t22)) + ((sd[5]) * (_t21) - (sd[4] * _t19));
        dd[6] = ((sd[6]) * (_t21) - (sd[3] * _t19)) - ((sd[4]) * (_t20) + (sd[5] * _t22));
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            float[] dd = ((FloatRigidImpl) dest).data;
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            float _buf0 = java.lang.Math.fma(sd[3], _t1, sd[4] * _t2);
            dd[4] = java.lang.Math.fma(sd[4], _t1, -(sd[3] * _t2));
            float _buf1 = java.lang.Math.fma(sd[5], _t1, sd[6] * _t2);
            dd[6] = java.lang.Math.fma(sd[6], _t1, -(sd[5] * _t2));
            dd[3] = _buf0;
            dd[5] = _buf1;
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((FloatRigidImpl) dest).data;
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            float _buf0 = ((sd[3]) * (_t1) + (sd[4] * _t2));
            dd[4] = ((sd[4]) * (_t1) - (sd[3] * _t2));
            float _buf1 = ((sd[5]) * (_t1) + (sd[6] * _t2));
            dd[6] = ((sd[6]) * (_t1) - (sd[5] * _t2));
            dd[3] = _buf0;
            dd[5] = _buf1;
            return dest;
        }
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
        if (org.joml2.internal.FmaSwitch.ON) {
            float[] sd = this.data;
            double[] dd = ((DoubleRigidImpl) dest).data;
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            dd[3] = java.lang.Math.fma(sd[3], _t1, sd[4] * _t2);
            dd[4] = java.lang.Math.fma(sd[4], _t1, -(sd[3] * _t2));
            dd[5] = java.lang.Math.fma(sd[5], _t1, sd[6] * _t2);
            dd[6] = java.lang.Math.fma(sd[6], _t1, -(sd[5] * _t2));
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((DoubleRigidImpl) dest).data;
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dd[0] = sd[0];
            dd[1] = sd[1];
            dd[2] = sd[2];
            dd[3] = ((sd[3]) * (_t1) + (sd[4] * _t2));
            dd[4] = ((sd[4]) * (_t1) - (sd[3] * _t2));
            dd[5] = ((sd[5]) * (_t1) + (sd[6] * _t2));
            dd[6] = ((sd[6]) * (_t1) - (sd[5] * _t2));
            return dest;
        }
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z), to this rigid transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateZXY_mulAdd(angleZ, angleX, angleY, dest);
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateZXY_sa0fff6be_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[3], _t21, sd[6] * _t22) + java.lang.Math.fma(sd[4], _t19, -(sd[5] * _t20)), java.lang.Math.fma(sd[4], _t21, sd[5] * _t22) + java.lang.Math.fma(sd[6], _t20, -sd[3] * _t19));
    }

    /** {@code rotateZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid rotateZXY_mulAdd(float angleZ, float angleX, float angleY, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateZXY_sa0fff6be_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, ((sd[3]) * (_t21) + (sd[6] * _t22)) + ((sd[4]) * (_t19) - (sd[5] * _t20)), ((sd[4]) * (_t21) + (sd[5] * _t22)) + ((sd[6]) * (_t20) - (sd[3] * _t19)));
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateZXY_sa0fff6be_1_fma(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = java.lang.Math.fma(sd[3], _t20, sd[6] * _t19) + java.lang.Math.fma(sd[5], _t21, -(sd[4] * _t22));
        dd[6] = java.lang.Math.fma(sd[6], _t21, -sd[3] * _t22) - java.lang.Math.fma(sd[4], _t20, sd[5] * _t19);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateZXY_sa0fff6be_1_mulAdd(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = ((sd[3]) * (_t20) + (sd[6] * _t19)) + ((sd[5]) * (_t21) - (sd[4] * _t22));
        dd[6] = ((sd[6]) * (_t21) - (sd[3] * _t22)) - ((sd[4]) * (_t20) + (sd[5] * _t19));
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z), to this rigid transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateZXY_mulAdd(angleZ, angleX, angleY, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(sd[3], _t21, sd[6] * _t22) + java.lang.Math.fma(sd[4], _t19, -(sd[5] * _t20));
        dd[4] = java.lang.Math.fma(sd[4], _t21, sd[5] * _t22) + java.lang.Math.fma(sd[6], _t20, -sd[3] * _t19);
        return rotateZXY_s35a57e63_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** {@code rotateZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid rotateZXY_mulAdd(float angleZ, float angleX, float angleY, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((sd[3]) * (_t21) + (sd[6] * _t22)) + ((sd[4]) * (_t19) - (sd[5] * _t20));
        dd[4] = ((sd[4]) * (_t21) + (sd[5] * _t22)) + ((sd[6]) * (_t20) - (sd[3] * _t19));
        return rotateZXY_s35a57e63_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateZXY_s35a57e63_1_fma(DoubleRigid dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[5] = java.lang.Math.fma(sd[3], _t20, sd[6] * _t19) + java.lang.Math.fma(sd[5], _t21, -(sd[4] * _t22));
        dd[6] = java.lang.Math.fma(sd[6], _t21, -sd[3] * _t22) - java.lang.Math.fma(sd[4], _t20, sd[5] * _t19);
        return dest;
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateZXY_s35a57e63_1_mulAdd(DoubleRigid dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[5] = ((sd[3]) * (_t20) + (sd[6] * _t19)) + ((sd[5]) * (_t21) - (sd[4] * _t22));
        dd[6] = ((sd[6]) * (_t21) - (sd[3] * _t22)) - ((sd[4]) * (_t20) + (sd[5] * _t19));
        return dest;
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z), to this rigid transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateZYX_mulAdd(angleZ, angleY, angleX, dest);
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateZYX_sdc89e05c_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[3], _t19, sd[6] * _t21) + java.lang.Math.fma(sd[4], _t22, -(sd[5] * _t20)), java.lang.Math.fma(sd[4], _t19, sd[5] * _t21) + java.lang.Math.fma(sd[6], _t20, -sd[3] * _t22));
    }

    /** {@code rotateZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid rotateZYX_mulAdd(float angleZ, float angleY, float angleX, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateZYX_sdc89e05c_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, ((sd[3]) * (_t19) + (sd[6] * _t21)) + ((sd[4]) * (_t22) - (sd[5] * _t20)), ((sd[4]) * (_t19) + (sd[5] * _t21)) + ((sd[6]) * (_t20) - (sd[3] * _t22)));
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateZYX_sdc89e05c_1_fma(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = java.lang.Math.fma(sd[3], _t20, sd[6] * _t22) + java.lang.Math.fma(sd[5], _t19, -(sd[4] * _t21));
        dd[6] = java.lang.Math.fma(sd[6], _t19, -sd[3] * _t21) - java.lang.Math.fma(sd[4], _t20, sd[5] * _t22);
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private FloatRigid rotateZYX_sdc89e05c_1_mulAdd(FloatRigid dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = ((sd[3]) * (_t20) + (sd[6] * _t22)) + ((sd[5]) * (_t19) - (sd[4] * _t21));
        dd[6] = ((sd[6]) * (_t19) - (sd[3] * _t21)) - ((sd[4]) * (_t20) + (sd[5] * _t22));
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z), to this rigid transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateZYX_mulAdd(angleZ, angleY, angleX, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(sd[3], _t19, sd[6] * _t21) + java.lang.Math.fma(sd[4], _t22, -(sd[5] * _t20));
        dd[4] = java.lang.Math.fma(sd[4], _t19, sd[5] * _t21) + java.lang.Math.fma(sd[6], _t20, -sd[3] * _t22);
        return rotateZYX_s2c1ce695_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** {@code rotateZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid rotateZYX_mulAdd(float angleZ, float angleY, float angleX, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
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
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((sd[3]) * (_t19) + (sd[6] * _t21)) + ((sd[4]) * (_t22) - (sd[5] * _t20));
        dd[4] = ((sd[4]) * (_t19) + (sd[5] * _t21)) + ((sd[6]) * (_t20) - (sd[3] * _t22));
        return rotateZYX_s2c1ce695_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateZYX_s2c1ce695_1_fma(DoubleRigid dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[5] = java.lang.Math.fma(sd[3], _t20, sd[6] * _t22) + java.lang.Math.fma(sd[5], _t19, -(sd[4] * _t21));
        dd[6] = java.lang.Math.fma(sd[6], _t19, -sd[3] * _t21) - java.lang.Math.fma(sd[4], _t20, sd[5] * _t22);
        return dest;
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleRigid rotateZYX_s2c1ce695_1_mulAdd(DoubleRigid dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22) {
        dd[5] = ((sd[3]) * (_t20) + (sd[6] * _t22)) + ((sd[5]) * (_t19) - (sd[4] * _t21));
        dd[6] = ((sd[6]) * (_t19) - (sd[3] * _t21)) - ((sd[4]) * (_t20) + (sd[5] * _t22));
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
        float[] translationData = ((Float3Impl) translation).data;
        float translationX = translationData[0];
        float translationY = translationData[1];
        float translationZ = translationData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return translate_mulAdd(translationX, translationY, translationZ, dest);
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], translationY, -(sd[4] * translationX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], translationX, -(sd[3] * translationZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[4], translationZ, -(sd[5] * translationY));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, sd[0] + translationX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, sd[1] + translationY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, sd[2] + translationZ)));
        FloatVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
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
        float[] translationData = ((Float3Impl) translation).data;
        float translationX = translationData[0];
        float translationY = translationData[1];
        float translationZ = translationData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return translate_mulAdd(translationX, translationY, translationZ, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], translationY, -(sd[4] * translationX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], translationX, -(sd[3] * translationZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[4], translationZ, -(sd[5] * translationY));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, sd[0] + translationX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, sd[1] + translationY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, sd[2] + translationZ)));
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
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
    public FloatRigid translate(float translationX, float translationY, float translationZ, @Mutated FloatRigid dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return translate_mulAdd(translationX, translationY, translationZ, dest);
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], translationY, -(sd[4] * translationX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], translationX, -(sd[3] * translationZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[4], translationZ, -(sd[5] * translationY));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, sd[0] + translationX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, sd[1] + translationY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, sd[2] + translationZ)));
        FloatVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        return dest;
    }

    /** {@code translate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid translate_mulAdd(float translationX, float translationY, float translationZ, @Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        float _t9 = 2.0f * ((sd[3]) * (translationY) - (sd[4] * translationX));
        float _t10 = 2.0f * ((sd[5]) * (translationX) - (sd[3] * translationZ));
        float _t11 = 2.0f * ((sd[4]) * (translationZ) - (sd[5] * translationY));
        dd[0] = ((sd[4]) * (_t9) + (((((sd[6]) * (_t11) + (sd[0] + translationX))) - (sd[5]) * (_t10))));
        dd[1] = ((sd[5]) * (_t11) + (((((sd[6]) * (_t10) + (sd[1] + translationY))) - (sd[3]) * (_t9))));
        dd[2] = ((sd[3]) * (_t10) + (((((sd[6]) * (_t9) + (sd[2] + translationZ))) - (sd[4]) * (_t11))));
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
        if (!org.joml2.internal.FmaSwitch.ON) return translate_mulAdd(translationX, translationY, translationZ, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], translationY, -(sd[4] * translationX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], translationX, -(sd[3] * translationZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[4], translationZ, -(sd[5] * translationY));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, sd[0] + translationX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, sd[1] + translationY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, sd[2] + translationZ)));
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        return dest;
    }

    /** {@code translate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid translate_mulAdd(float translationX, float translationY, float translationZ, @Mutated DoubleRigid dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        float _t9 = 2.0f * ((sd[3]) * (translationY) - (sd[4] * translationX));
        float _t10 = 2.0f * ((sd[5]) * (translationX) - (sd[3] * translationZ));
        float _t11 = 2.0f * ((sd[4]) * (translationZ) - (sd[5] * translationY));
        dd[0] = ((sd[4]) * (_t9) + (((((sd[6]) * (_t11) + (sd[0] + translationX))) - (sd[5]) * (_t10))));
        dd[1] = ((sd[5]) * (_t11) + (((((sd[6]) * (_t10) + (sd[1] + translationY))) - (sd[3]) * (_t9))));
        dd[2] = ((sd[3]) * (_t10) + (((((sd[6]) * (_t9) + (sd[2] + translationZ))) - (sd[4]) * (_t11))));
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
        float[] vData = ((Float3Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        float vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], vY, -(sd[4] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], vX, -(sd[3] * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, sd[0] + vX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, sd[1] + vY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, sd[2] + vZ)));
        return dest;
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
        float[] vData = ((Float3Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        float vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], vY, -(sd[4] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], vX, -(sd[3] * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, sd[0] + vX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, sd[1] + vY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, sd[2] + vZ)));
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
    public Float3 transform(float vX, float vY, float vZ, @Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], vY, -(sd[4] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], vX, -(sd[3] * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, sd[0] + vX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, sd[1] + vY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, sd[2] + vZ)));
        return dest;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transform_mulAdd(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * ((sd[3]) * (vY) - (sd[4] * vX));
        float _t10 = 2.0f * ((sd[5]) * (vX) - (sd[3] * vZ));
        float _t11 = 2.0f * ((sd[4]) * (vZ) - (sd[5] * vY));
        dd[0] = ((sd[4]) * (_t9) + (((((sd[6]) * (_t11) + (sd[0] + vX))) - (sd[5]) * (_t10))));
        dd[1] = ((sd[5]) * (_t11) + (((((sd[6]) * (_t10) + (sd[1] + vY))) - (sd[3]) * (_t9))));
        dd[2] = ((sd[3]) * (_t10) + (((((sd[6]) * (_t9) + (sd[2] + vZ))) - (sd[4]) * (_t11))));
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
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], vY, -(sd[4] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], vX, -(sd[3] * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, sd[0] + vX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, sd[1] + vY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, sd[2] + vZ)));
        return dest;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transform_mulAdd(float vX, float vY, float vZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * ((sd[3]) * (vY) - (sd[4] * vX));
        float _t10 = 2.0f * ((sd[5]) * (vX) - (sd[3] * vZ));
        float _t11 = 2.0f * ((sd[4]) * (vZ) - (sd[5] * vY));
        dd[0] = ((sd[4]) * (_t9) + (((((sd[6]) * (_t11) + (sd[0] + vX))) - (sd[5]) * (_t10))));
        dd[1] = ((sd[5]) * (_t11) + (((((sd[6]) * (_t10) + (sd[1] + vY))) - (sd[3]) * (_t9))));
        dd[2] = ((sd[3]) * (_t10) + (((((sd[6]) * (_t9) + (sd[2] + vZ))) - (sd[4]) * (_t11))));
        return dest;
    }

    /**
     * Transform the given direction by the rotation part of this rigid transform, ignoring the
     * translation, and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Float3 transformDirection(Float3R v, @Mutated Float3 dest) {
        float[] vData = ((Float3Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        float vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirection_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], vY, -(sd[4] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], vX, -(sd[3] * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, vY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, vZ)));
        return dest;
    }

    /**
     * Transform the given direction by the rotation part of this rigid transform, ignoring the
     * translation, and store the result in {@code dest}.
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
        float[] vData = ((Float3Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        float vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirection_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], vY, -(sd[4] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], vX, -(sd[3] * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, vY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, vZ)));
        return dest;
    }

    /**
     * Transform the given direction by the rotation part of this rigid transform, ignoring the
     * translation, and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirection_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], vY, -(sd[4] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], vX, -(sd[3] * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, vY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, vZ)));
        return dest;
    }

    /** {@code transformDirection} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transformDirection_mulAdd(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * ((sd[3]) * (vY) - (sd[4] * vX));
        float _t10 = 2.0f * ((sd[5]) * (vX) - (sd[3] * vZ));
        float _t11 = 2.0f * ((sd[4]) * (vZ) - (sd[5] * vY));
        dd[0] = ((sd[4]) * (_t9) + (((((sd[6]) * (_t11) + (vX))) - (sd[5]) * (_t10))));
        dd[1] = ((sd[5]) * (_t11) + (((((sd[6]) * (_t10) + (vY))) - (sd[3]) * (_t9))));
        dd[2] = ((sd[3]) * (_t10) + (((((sd[6]) * (_t9) + (vZ))) - (sd[4]) * (_t11))));
        return dest;
    }

    /**
     * Transform the given direction by the rotation part of this rigid transform, ignoring the
     * translation, and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirection_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], vY, -(sd[4] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], vX, -(sd[3] * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, vY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, vZ)));
        return dest;
    }

    /** {@code transformDirection} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformDirection_mulAdd(float vX, float vY, float vZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * ((sd[3]) * (vY) - (sd[4] * vX));
        float _t10 = 2.0f * ((sd[5]) * (vX) - (sd[3] * vZ));
        float _t11 = 2.0f * ((sd[4]) * (vZ) - (sd[5] * vY));
        dd[0] = ((sd[4]) * (_t9) + (((((sd[6]) * (_t11) + (vX))) - (sd[5]) * (_t10))));
        dd[1] = ((sd[5]) * (_t11) + (((((sd[6]) * (_t10) + (vY))) - (sd[3]) * (_t9))));
        dd[2] = ((sd[3]) * (_t10) + (((((sd[6]) * (_t9) + (vZ))) - (sd[4]) * (_t11))));
        return dest;
    }

    /**
     * Transform the given direction by the inverse of this rigid transform's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()}, and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Float3 transformDirectionInverse(Float3R v, @Mutated Float3 dest) {
        float[] vData = ((Float3Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        float vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirectionInverse_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], vZ, -(sd[5] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[4], vX, -(sd[3] * vY));
        float _t11 = 2.0f * java.lang.Math.fma(sd[5], vY, -(sd[4] * vZ));
        dd[0] = java.lang.Math.fma(sd[5], _t9, java.lang.Math.fma(-sd[4], _t10, java.lang.Math.fma(sd[6], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[5], _t11, java.lang.Math.fma(sd[6], _t9, vY)));
        dd[2] = java.lang.Math.fma(sd[4], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, vZ)));
        return dest;
    }

    /**
     * Transform the given direction by the inverse of this rigid transform's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()}, and store the
     * result in {@code dest}.
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
        float[] vData = ((Float3Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        float vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirectionInverse_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], vZ, -(sd[5] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[4], vX, -(sd[3] * vY));
        float _t11 = 2.0f * java.lang.Math.fma(sd[5], vY, -(sd[4] * vZ));
        dd[0] = java.lang.Math.fma(sd[5], _t9, java.lang.Math.fma(-sd[4], _t10, java.lang.Math.fma(sd[6], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[5], _t11, java.lang.Math.fma(sd[6], _t9, vY)));
        dd[2] = java.lang.Math.fma(sd[4], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, vZ)));
        return dest;
    }

    /**
     * Transform the given direction by the inverse of this rigid transform's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()}, and store the
     * result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirectionInverse_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], vZ, -(sd[5] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[4], vX, -(sd[3] * vY));
        float _t11 = 2.0f * java.lang.Math.fma(sd[5], vY, -(sd[4] * vZ));
        dd[0] = java.lang.Math.fma(sd[5], _t9, java.lang.Math.fma(-sd[4], _t10, java.lang.Math.fma(sd[6], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[5], _t11, java.lang.Math.fma(sd[6], _t9, vY)));
        dd[2] = java.lang.Math.fma(sd[4], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, vZ)));
        return dest;
    }

    /** {@code transformDirectionInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transformDirectionInverse_mulAdd(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * ((sd[3]) * (vZ) - (sd[5] * vX));
        float _t10 = 2.0f * ((sd[4]) * (vX) - (sd[3] * vY));
        float _t11 = 2.0f * ((sd[5]) * (vY) - (sd[4] * vZ));
        dd[0] = ((sd[5]) * (_t9) + (((((sd[6]) * (_t11) + (vX))) - (sd[4]) * (_t10))));
        dd[1] = ((sd[3]) * (_t10) + (((((sd[6]) * (_t9) + (vY))) - (sd[5]) * (_t11))));
        dd[2] = ((sd[4]) * (_t11) + (((((sd[6]) * (_t10) + (vZ))) - (sd[3]) * (_t9))));
        return dest;
    }

    /**
     * Transform the given direction by the inverse of this rigid transform's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()}, and store the
     * result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirectionInverse_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], vZ, -(sd[5] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[4], vX, -(sd[3] * vY));
        float _t11 = 2.0f * java.lang.Math.fma(sd[5], vY, -(sd[4] * vZ));
        dd[0] = java.lang.Math.fma(sd[5], _t9, java.lang.Math.fma(-sd[4], _t10, java.lang.Math.fma(sd[6], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[5], _t11, java.lang.Math.fma(sd[6], _t9, vY)));
        dd[2] = java.lang.Math.fma(sd[4], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, vZ)));
        return dest;
    }

    /** {@code transformDirectionInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformDirectionInverse_mulAdd(float vX, float vY, float vZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * ((sd[3]) * (vZ) - (sd[5] * vX));
        float _t10 = 2.0f * ((sd[4]) * (vX) - (sd[3] * vY));
        float _t11 = 2.0f * ((sd[5]) * (vY) - (sd[4] * vZ));
        dd[0] = ((sd[5]) * (_t9) + (((((sd[6]) * (_t11) + (vX))) - (sd[4]) * (_t10))));
        dd[1] = ((sd[3]) * (_t10) + (((((sd[6]) * (_t9) + (vY))) - (sd[5]) * (_t11))));
        dd[2] = ((sd[4]) * (_t11) + (((((sd[6]) * (_t10) + (vZ))) - (sd[3]) * (_t9))));
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
        float[] pData = ((Float3Impl) p).data;
        float pX = pData[0];
        float pY = pData[1];
        float pZ = pData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transformInverse_mulAdd(pX, pY, pZ, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t12 = 2.0f * java.lang.Math.fma(sd[3], _t0, -(sd[5] * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(sd[4], _t1, -(sd[3] * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(sd[5], _t2, -(sd[4] * _t0));
        dd[0] = java.lang.Math.fma(sd[5], _t12, java.lang.Math.fma(-sd[4], _t13, java.lang.Math.fma(sd[6], _t14, _t1)));
        dd[1] = java.lang.Math.fma(sd[3], _t13, java.lang.Math.fma(-sd[5], _t14, java.lang.Math.fma(sd[6], _t12, _t2)));
        dd[2] = java.lang.Math.fma(sd[4], _t14, java.lang.Math.fma(-sd[3], _t12, java.lang.Math.fma(sd[6], _t13, _t0)));
        return dest;
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
        float[] pData = ((Float3Impl) p).data;
        float pX = pData[0];
        float pY = pData[1];
        float pZ = pData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transformInverse_mulAdd(pX, pY, pZ, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t12 = 2.0f * java.lang.Math.fma(sd[3], _t0, -(sd[5] * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(sd[4], _t1, -(sd[3] * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(sd[5], _t2, -(sd[4] * _t0));
        dd[0] = java.lang.Math.fma(sd[5], _t12, java.lang.Math.fma(-sd[4], _t13, java.lang.Math.fma(sd[6], _t14, _t1)));
        dd[1] = java.lang.Math.fma(sd[3], _t13, java.lang.Math.fma(-sd[5], _t14, java.lang.Math.fma(sd[6], _t12, _t2)));
        dd[2] = java.lang.Math.fma(sd[4], _t14, java.lang.Math.fma(-sd[3], _t12, java.lang.Math.fma(sd[6], _t13, _t0)));
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
    public Float3 transformInverse(float pX, float pY, float pZ, @Mutated Float3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return transformInverse_mulAdd(pX, pY, pZ, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t12 = 2.0f * java.lang.Math.fma(sd[3], _t0, -(sd[5] * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(sd[4], _t1, -(sd[3] * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(sd[5], _t2, -(sd[4] * _t0));
        dd[0] = java.lang.Math.fma(sd[5], _t12, java.lang.Math.fma(-sd[4], _t13, java.lang.Math.fma(sd[6], _t14, _t1)));
        dd[1] = java.lang.Math.fma(sd[3], _t13, java.lang.Math.fma(-sd[5], _t14, java.lang.Math.fma(sd[6], _t12, _t2)));
        dd[2] = java.lang.Math.fma(sd[4], _t14, java.lang.Math.fma(-sd[3], _t12, java.lang.Math.fma(sd[6], _t13, _t0)));
        return dest;
    }

    /** {@code transformInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transformInverse_mulAdd(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t12 = 2.0f * ((sd[3]) * (_t0) - (sd[5] * _t1));
        float _t13 = 2.0f * ((sd[4]) * (_t1) - (sd[3] * _t2));
        float _t14 = 2.0f * ((sd[5]) * (_t2) - (sd[4] * _t0));
        dd[0] = ((sd[5]) * (_t12) + (((((sd[6]) * (_t14) + (_t1))) - (sd[4]) * (_t13))));
        dd[1] = ((sd[3]) * (_t13) + (((((sd[6]) * (_t12) + (_t2))) - (sd[5]) * (_t14))));
        dd[2] = ((sd[4]) * (_t14) + (((((sd[6]) * (_t13) + (_t0))) - (sd[3]) * (_t12))));
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformInverse_mulAdd(pX, pY, pZ, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t12 = 2.0f * java.lang.Math.fma(sd[3], _t0, -(sd[5] * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(sd[4], _t1, -(sd[3] * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(sd[5], _t2, -(sd[4] * _t0));
        dd[0] = java.lang.Math.fma(sd[5], _t12, java.lang.Math.fma(-sd[4], _t13, java.lang.Math.fma(sd[6], _t14, _t1)));
        dd[1] = java.lang.Math.fma(sd[3], _t13, java.lang.Math.fma(-sd[5], _t14, java.lang.Math.fma(sd[6], _t12, _t2)));
        dd[2] = java.lang.Math.fma(sd[4], _t14, java.lang.Math.fma(-sd[3], _t12, java.lang.Math.fma(sd[6], _t13, _t0)));
        return dest;
    }

    /** {@code transformInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformInverse_mulAdd(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t12 = 2.0f * ((sd[3]) * (_t0) - (sd[5] * _t1));
        float _t13 = 2.0f * ((sd[4]) * (_t1) - (sd[3] * _t2));
        float _t14 = 2.0f * ((sd[5]) * (_t2) - (sd[4] * _t0));
        dd[0] = ((sd[5]) * (_t12) + (((((sd[6]) * (_t14) + (_t1))) - (sd[4]) * (_t13))));
        dd[1] = ((sd[3]) * (_t13) + (((((sd[6]) * (_t12) + (_t2))) - (sd[5]) * (_t14))));
        dd[2] = ((sd[4]) * (_t14) + (((((sd[6]) * (_t13) + (_t0))) - (sd[3]) * (_t12))));
        return dest;
    }

    /**
     * Transform the given position by this rigid transform, treating it as a point with an implicit
     * {@code w = 1}, and store the result in {@code dest}.
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
     * {@code w = 1}, and store the result in {@code dest}.
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
     * {@code w = 1}, and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], vY, -(sd[4] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], vX, -(sd[3] * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, sd[0] + vX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, sd[1] + vY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, sd[2] + vZ)));
        return dest;
    }

    /**
     * Transform the given position by this rigid transform, treating it as a point with an implicit
     * {@code w = 1}, and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(vX, vY, vZ, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * java.lang.Math.fma(sd[3], vY, -(sd[4] * vX));
        float _t10 = 2.0f * java.lang.Math.fma(sd[5], vX, -(sd[3] * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, sd[0] + vX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, sd[1] + vY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, sd[2] + vZ)));
        return dest;
    }

    /**
     * Transform the given position by the inverse of this rigid transform (world to local), without
     * materializing {@code invert()}, and store the result in {@code dest}.
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
     * materializing {@code invert()}, and store the result in {@code dest}.
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
     * materializing {@code invert()}, and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformInverse_mulAdd(pX, pY, pZ, dest);
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t12 = 2.0f * java.lang.Math.fma(sd[3], _t0, -(sd[5] * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(sd[4], _t1, -(sd[3] * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(sd[5], _t2, -(sd[4] * _t0));
        dd[0] = java.lang.Math.fma(sd[5], _t12, java.lang.Math.fma(-sd[4], _t13, java.lang.Math.fma(sd[6], _t14, _t1)));
        dd[1] = java.lang.Math.fma(sd[3], _t13, java.lang.Math.fma(-sd[5], _t14, java.lang.Math.fma(sd[6], _t12, _t2)));
        dd[2] = java.lang.Math.fma(sd[4], _t14, java.lang.Math.fma(-sd[3], _t12, java.lang.Math.fma(sd[6], _t13, _t0)));
        return dest;
    }

    /**
     * Transform the given position by the inverse of this rigid transform (world to local), without
     * materializing {@code invert()}, and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformInverse_mulAdd(pX, pY, pZ, dest);
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t12 = 2.0f * java.lang.Math.fma(sd[3], _t0, -(sd[5] * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(sd[4], _t1, -(sd[3] * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(sd[5], _t2, -(sd[4] * _t0));
        dd[0] = java.lang.Math.fma(sd[5], _t12, java.lang.Math.fma(-sd[4], _t13, java.lang.Math.fma(sd[6], _t14, _t1)));
        dd[1] = java.lang.Math.fma(sd[3], _t13, java.lang.Math.fma(-sd[5], _t14, java.lang.Math.fma(sd[6], _t12, _t2)));
        dd[2] = java.lang.Math.fma(sd[4], _t14, java.lang.Math.fma(-sd[3], _t12, java.lang.Math.fma(sd[6], _t13, _t0)));
        return dest;
    }

    public float tX() { return data[0]; }
    public float tY() { return data[1]; }
    public float tZ() { return data[2]; }
    public float rX() { return data[3]; }
    public float rY() { return data[4]; }
    public float rZ() { return data[5]; }
    public float rW() { return data[6]; }

    @Override public String toString() {
        return "FloatRigid(" + this.data[0] + ", " + this.data[1] + ", " + this.data[2] + ", " + this.data[3] + ", " + this.data[4] + ", " + this.data[5] + ", " + this.data[6] + ")";
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
        float[] otherData = ((FloatRigidImpl) other).data;
        float[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherData[0]) <= epsilon
            && java.lang.Math.abs(sd[1] - otherData[1]) <= epsilon
            && java.lang.Math.abs(sd[2] - otherData[2]) <= epsilon
            && java.lang.Math.abs(sd[3] - otherData[3]) <= epsilon
            && java.lang.Math.abs(sd[4] - otherData[4]) <= epsilon
            && java.lang.Math.abs(sd[5] - otherData[5]) <= epsilon
            && java.lang.Math.abs(sd[6] - otherData[6]) <= epsilon;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        dest[offset + 6] = this.data[6];
        return dest;
    }

    @Mutated public FloatRigid load(float[] src, int offset) {
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

    @Mutated public FloatRigid load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatRigid loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public FloatRigid loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 7) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 7);
        return this;
    }

    public ByteBuffer store(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public ByteBuffer storeRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 28) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 28);
        return buf;
    }

    @Mutated public FloatRigid load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatRigid loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public FloatRigid loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 28) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 28);
        return this;
    }

    public FloatRigid storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    @Mutated public FloatRigid loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }

    public MemorySegment store(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }

    @Mutated public FloatRigid load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }

    @Mutated public FloatRigid load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
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

    @Mutated public FloatRigid load(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        this.data[4] = (float) src[offset + 4];
        this.data[5] = (float) src[offset + 5];
        this.data[6] = (float) src[offset + 6];
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

    @Mutated public FloatRigid load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatRigid loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public FloatRigid loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 7) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 7);
        return this;
    }

    public ByteBuffer storeDouble(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeDoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeDoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 56) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 56);
        return buf;
    }

    @Mutated public FloatRigid loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatRigid loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }

    @Mutated public FloatRigid loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 56) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 56);
        return this;
    }

    public FloatRigid storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    @Mutated public FloatRigid loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }

    public MemorySegment storeDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }

    public MemorySegment storeDouble(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }

    @Mutated public FloatRigid loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(this, 0L, src); }

    @Mutated public FloatRigid loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(this, offset, src);
    }

    private static final VectorSpecies<Float> COL_SPECIES = FloatVector.SPECIES_128;
    private static final FloatVector VEC_1 = FloatVector.fromArray(COL_SPECIES, new float[] {0.0f, 0.0f, 0.0f, 1.0f}, 0);
    private static final FloatVector VEC_2 = FloatVector.fromArray(COL_SPECIES, new float[] {0.0f, 0.0f, 0.0f, 0.0f}, 0);
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
