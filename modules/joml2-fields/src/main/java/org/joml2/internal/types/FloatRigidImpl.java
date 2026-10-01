// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link FloatRigid} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatRigidImpl implements FloatRigid {

    public float tX;
    public float tY;
    public float tZ;
    public float rX;
    public float rY;
    public float rZ;
    public float rW;

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
        rW = 1;
    }

    public FloatRigidImpl(float tX, float tY, float tZ, float rX, float rY, float rZ, float rW) {
        this.tX = tX;
        this.tY = tY;
        this.tZ = tZ;
        this.rX = rX;
        this.rY = rY;
        this.rZ = rZ;
        this.rW = rW;
    }

    public FloatRigidImpl(FloatRigidR src) {
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
    public @Mutated FloatRigid makeFromAxisAngle(Float3R axis, float angle, Float3R translation) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        float translationY = translation.y();
        float translationZ = translation.z();
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
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
    @Mutated public FloatRigid makeFromAxisAngle(float axisX, float axisY, float axisZ, float angle, float translationX, float translationY, float translationZ) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
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
    public @Mutated FloatRigid makeTranslationRotation(Float3R translation, FloatQuatR rotation) {
        float translationY = translation.y();
        float translationZ = translation.z();
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
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
    @Mutated public FloatRigid makeTranslationRotation(float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW) {
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
    public @Mutated FloatRigid set(FloatRigidR v) {
        float vTY = v.tY();
        float vTZ = v.tZ();
        float vRX = v.rX();
        float vRY = v.rY();
        float vRZ = v.rZ();
        float vRW = v.rW();
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
    @Mutated public FloatRigid set(float vTX, float vTY, float vTZ, float vRX, float vRY, float vRZ, float vRW) {
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
    public FloatRigid setRotation(FloatQuatR r, @Mutated FloatRigid dest) {
        float rX = r.x();
        float rY = r.y();
        float rZ = r.z();
        float rW = r.w();
        FloatRigidImpl d = (FloatRigidImpl) dest;
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
        float rX = r.x();
        float rY = r.y();
        float rZ = r.z();
        float rW = r.w();
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
    public FloatRigid setRotation(float rX, float rY, float rZ, float rW, @Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
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
    public FloatRigid setTranslation(Float3R t, @Mutated FloatRigid dest) {
        float tY = t.y();
        float tZ = t.z();
        FloatRigidImpl d = (FloatRigidImpl) dest;
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
        float tY = t.y();
        float tZ = t.z();
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
    public FloatRigid setTranslation(float tX, float tY, float tZ, @Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
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
    public @Mutated FloatRigid makeFromDualQuat(FloatDualQuatR dq) {
        float dqRX = dq.rX();
        float dqRY = dq.rY();
        float dqRZ = dq.rZ();
        float dqRW = dq.rW();
        float dqDX = dq.dX();
        float dqDY = dq.dY();
        float dqDZ = dq.dZ();
        float dqDW = dq.dW();
        if (Math.useFma()) {
            this.tX = 2.0f * (java.lang.Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + java.lang.Math.fma(dqRW, dqDX, -(dqRX * dqDW)));
            this.tY = 2.0f * (java.lang.Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + java.lang.Math.fma(dqRW, dqDY, -(dqRY * dqDW)));
            this.tZ = 2.0f * (java.lang.Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + java.lang.Math.fma(dqRW, dqDZ, -(dqRZ * dqDW)));
            this.rX = dqRX;
            this.rY = dqRY;
            this.rZ = dqRZ;
            this.rW = dqRW;
            return this;
        } else {
            this.tX = 2.0f * (((dqRY) * (dqDZ) - (dqRZ * dqDY)) + ((dqRW) * (dqDX) - (dqRX * dqDW)));
            this.tY = 2.0f * (((dqRZ) * (dqDX) - (dqRX * dqDZ)) + ((dqRW) * (dqDY) - (dqRY * dqDW)));
            this.tZ = 2.0f * (((dqRX) * (dqDY) - (dqRY * dqDX)) + ((dqRW) * (dqDZ) - (dqRZ * dqDW)));
            this.rX = dqRX;
            this.rY = dqRY;
            this.rZ = dqRZ;
            this.rW = dqRW;
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
        if (Math.useFma()) {
            this.tX = 2.0f * (java.lang.Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + java.lang.Math.fma(dqRW, dqDX, -(dqRX * dqDW)));
            this.tY = 2.0f * (java.lang.Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + java.lang.Math.fma(dqRW, dqDY, -(dqRY * dqDW)));
            this.tZ = 2.0f * (java.lang.Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + java.lang.Math.fma(dqRW, dqDZ, -(dqRZ * dqDW)));
            this.rX = dqRX;
            this.rY = dqRY;
            this.rZ = dqRZ;
            this.rW = dqRW;
            return this;
        } else {
            this.tX = 2.0f * (((dqRY) * (dqDZ) - (dqRZ * dqDY)) + ((dqRW) * (dqDX) - (dqRX * dqDW)));
            this.tY = 2.0f * (((dqRZ) * (dqDX) - (dqRX * dqDZ)) + ((dqRW) * (dqDY) - (dqRY * dqDW)));
            this.tZ = 2.0f * (((dqRX) * (dqDY) - (dqRY * dqDX)) + ((dqRW) * (dqDZ) - (dqRZ * dqDW)));
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
    @Mutated public FloatRigid makeFromMatrix(Float3x3R m) {
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid makeFromMatrix_fma(Float3x3R m) {
        float _r0 = m.m11();
        float _r1 = m.m22();
        float _r2 = m.m21();
        float _r3 = m.m01();
        float _r4 = m.m02();
        float _r5 = m.m12();
        float _r6 = m.m20();
        float _r7 = m.m00();
        float _r8 = m.m10();
        float _ct0 = java.lang.Math.fma(_r2, _r2, java.lang.Math.fma(_r3, _r3, _r0 * _r0));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _ct1 = java.lang.Math.fma(_r1, _r1, java.lang.Math.fma(_r4, _r4, _r5 * _r5));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = java.lang.Math.fma(_r6, _r6, java.lang.Math.fma(_r7, _r7, _r8 * _r8));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        float _t20 = _r5 * _t16;
        float _t23 = _r2 * _t15;
        return makeFromMatrix_s4398bb65_1_fma(this, _r0, _r1, _r3, _r4, _t15, _t16, -_r0, -_r1, _r8 * _t17, _r1 * _t16, _t20, _r6 * _t17, _t23, _r0 * _t15, _r7 * _t17, java.lang.Math.fma(_r5, _t16, _t23), java.lang.Math.fma(_r2, _t15, -_t20));
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid makeFromMatrix_mulAdd(Float3x3R m) {
        float _r0 = m.m11();
        float _r1 = m.m22();
        float _r2 = m.m21();
        float _r3 = m.m01();
        float _r4 = m.m02();
        float _r5 = m.m12();
        float _r6 = m.m20();
        float _r7 = m.m00();
        float _r8 = m.m10();
        float _ct0 = ((_r2) * (_r2) + (((_r3) * (_r3) + (_r0 * _r0))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _ct1 = ((_r1) * (_r1) + (((_r4) * (_r4) + (_r5 * _r5))));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = ((_r6) * (_r6) + (((_r7) * (_r7) + (_r8 * _r8))));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        float _t20 = _r5 * _t16;
        float _t23 = _r2 * _t15;
        return makeFromMatrix_s4398bb65_1_mulAdd(this, _r0, _r1, _r3, _r4, _t15, _t16, -_r0, -_r1, _r8 * _t17, _r1 * _t16, _t20, _r6 * _t17, _t23, _r0 * _t15, _r7 * _t17, ((_r5) * (_t16) + (_t23)), ((_r2) * (_t15) - (_t20)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatRigid makeFromMatrix_s4398bb65_1_fma(FloatRigidImpl d, float _r0, float _r1, float _r3, float _r4, float _t15, float _t16, float _t0, float _t1, float _t18, float _t19, float _t20, float _t21, float _t23, float _t24, float _t26, float _t31, float _t35) {
        float _t47, _t48, _t49;
        if (java.lang.Math.fma(-java.lang.Math.fma(_t18, _t19, -(_t20 * _t21)), _r3 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t18, _t23, -(_t24 * _t21)), _r4 * _t16, java.lang.Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
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
        float _t63 = java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_r1, _t16, _t51));
        float _t65 = java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        float _t66 = java.lang.Math.fma(_r1, _t16, java.lang.Math.fma(_t0, _t15, _t52));
        float _t67 = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t51));
        d.tX = 0.0f;
        return makeFromMatrix_s4398bb65_2(d, _t19, _t24, _t31, _t35, java.lang.Math.max(_t24, _t19), _t47, java.lang.Math.fma(_r3, _t15, _t48), java.lang.Math.fma(_r4, _t16, _t49), java.lang.Math.fma(_r4, _t16, -_t49), java.lang.Math.fma(-_r3, _t15, _t48), java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_r1, _t16, _t47)), _t63, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)), _t65, _t66, _t67, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatRigid makeFromMatrix_s4398bb65_1_mulAdd(FloatRigidImpl d, float _r0, float _r1, float _r3, float _r4, float _t15, float _t16, float _t0, float _t1, float _t18, float _t19, float _t20, float _t21, float _t23, float _t24, float _t26, float _t31, float _t35) {
        float _t47, _t48, _t49;
        if (((-((_t18) * (_t19) - (_t20 * _t21))) * (_r3 * _t15) + (((((_t18) * (_t23) - (_t24 * _t21))) * (_r4 * _t16) + (((_t24) * (_t19) - (_t20 * _t23)) * _t26)))) < 0.0f) {
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
        float _t63 = ((_r0) * (_t15) + (((_r1) * (_t16) + (_t51))));
        float _t65 = ((_r0) * (_t15) + (((_t1) * (_t16) + (_t52))));
        float _t66 = ((_r1) * (_t16) + (((_t0) * (_t15) + (_t52))));
        float _t67 = ((_t0) * (_t15) + (((_t1) * (_t16) + (_t51))));
        d.tX = 0.0f;
        return makeFromMatrix_s4398bb65_2(d, _t19, _t24, _t31, _t35, java.lang.Math.max(_t24, _t19), _t47, ((_r3) * (_t15) + (_t48)), ((_r4) * (_t16) + (_t49)), ((_r4) * (_t16) - (_t49)), ((-_r3) * (_t15) + (_t48)), ((_r0) * (_t15) + (((_r1) * (_t16) + (_t47)))), _t63, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)), _t65, _t66, _t67, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatRigid makeFromMatrix_s4398bb65_2(FloatRigidImpl d, float _t19, float _t24, float _t31, float _t35, float _t36, float _t47, float _t54, float _t55, float _t56, float _t57, float _t62, float _t63, float _sp0, float _t65, float _t66, float _t67, float _sp1, float _sp2, float _sp3) {
        d.tY = 0.0f;
        d.tZ = 0.0f;
        d.rX = _t62 > 0.0f ? _sp0 * _t35 : _t47 > _t36 ? 0.5f * (float) java.lang.Math.sqrt(_t67) : _t24 > _t19 ? _sp1 * _t54 : _sp2 * _t55;
        d.rY = _t62 > 0.0f ? _sp0 * _t56 : _t47 > _t36 ? _sp3 * _t54 : _t24 > _t19 ? 0.5f * (float) java.lang.Math.sqrt(_t65) : _sp2 * _t31;
        d.rZ = _t62 > 0.0f ? _sp0 * _t57 : _t47 > _t36 ? _sp3 * _t55 : _t24 > _t19 ? _sp1 * _t31 : 0.5f * (float) java.lang.Math.sqrt(_t66);
        d.rW = _t62 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t63) : _t47 > _t36 ? _sp3 * _t35 : _t24 > _t19 ? _sp1 * _t56 : _sp2 * _t57;
        return d;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatRigid makeFromMatrix_degenerate_fma(Float3x3R m) {
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
                    _t168 = java.lang.Math.fma(_t35, _t106, -(_t33 * _t116));
                    _t171 = java.lang.Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
        if (_t206 > 0.0f) {
            this.rX = _sp0 * _t182;
            this.rY = _sp0 * _t201;
            this.rZ = _sp0 * _t202;
            this.rW = 0.5f * (float) java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                this.rX = 0.5f * (float) java.lang.Math.sqrt(_t208);
                this.rY = _sp3 * _t199;
                this.rZ = _sp3 * _t200;
                this.rW = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    this.rX = _sp1 * _t199;
                    this.rY = 0.5f * (float) java.lang.Math.sqrt(_t209);
                    this.rZ = _sp1 * _t184;
                    this.rW = _sp1 * _t201;
                } else {
                    this.rX = _sp2 * _t200;
                    this.rY = _sp2 * _t184;
                    this.rZ = 0.5f * (float) java.lang.Math.sqrt(_t210);
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
    @Mutated private FloatRigid makeFromMatrix_degenerate_mulAdd(Float3x3R m) {
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
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
        if (_t206 > 0.0f) {
            this.rX = _sp0 * _t182;
            this.rY = _sp0 * _t201;
            this.rZ = _sp0 * _t202;
            this.rW = 0.5f * (float) java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                this.rX = 0.5f * (float) java.lang.Math.sqrt(_t208);
                this.rY = _sp3 * _t199;
                this.rZ = _sp3 * _t200;
                this.rW = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    this.rX = _sp1 * _t199;
                    this.rY = 0.5f * (float) java.lang.Math.sqrt(_t209);
                    this.rZ = _sp1 * _t184;
                    this.rW = _sp1 * _t201;
                } else {
                    this.rX = _sp2 * _t200;
                    this.rY = _sp2 * _t184;
                    this.rZ = 0.5f * (float) java.lang.Math.sqrt(_t210);
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
    @Mutated public FloatRigid makeFromMatrix(Float3x4R m) {
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid makeFromMatrix_fma(Float3x4R m) {
        float _r0 = m.m11();
        float _r1 = m.m22();
        float _r2 = m.m21();
        float _r3 = m.m01();
        float _r4 = m.m02();
        float _r5 = m.m12();
        float _r6 = m.m20();
        float _r7 = m.m00();
        float _r8 = m.m10();
        float _ct0 = java.lang.Math.fma(_r2, _r2, java.lang.Math.fma(_r3, _r3, _r0 * _r0));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _ct1 = java.lang.Math.fma(_r1, _r1, java.lang.Math.fma(_r4, _r4, _r5 * _r5));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = java.lang.Math.fma(_r6, _r6, java.lang.Math.fma(_r7, _r7, _r8 * _r8));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        float _r9 = m.m03();
        float _r10 = m.m13();
        return makeFromMatrix_s7b6de20e_1_fma(this, _r0, _r1, _r2, _r3, _r4, _r5, _r7, _t15, _t16, _t17, _r9, _r10, m.m23(), -_r0, -_r1, _r8 * _t17, _r1 * _t16, _r5 * _t16, _r6 * _t17, _r2 * _t15);
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid makeFromMatrix_mulAdd(Float3x4R m) {
        float _r0 = m.m11();
        float _r1 = m.m22();
        float _r2 = m.m21();
        float _r3 = m.m01();
        float _r4 = m.m02();
        float _r5 = m.m12();
        float _r6 = m.m20();
        float _r7 = m.m00();
        float _r8 = m.m10();
        float _ct0 = ((_r2) * (_r2) + (((_r3) * (_r3) + (_r0 * _r0))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _ct1 = ((_r1) * (_r1) + (((_r4) * (_r4) + (_r5 * _r5))));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = ((_r6) * (_r6) + (((_r7) * (_r7) + (_r8 * _r8))));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        float _r9 = m.m03();
        float _r10 = m.m13();
        return makeFromMatrix_s7b6de20e_1_mulAdd(this, _r0, _r1, _r2, _r3, _r4, _r5, _r7, _t15, _t16, _t17, _r9, _r10, m.m23(), -_r0, -_r1, _r8 * _t17, _r1 * _t16, _r5 * _t16, _r6 * _t17, _r2 * _t15);
    }

    /**
     * Piece 2 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private FloatRigid makeFromMatrix_s7b6de20e_1_fma(FloatRigidImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r7, float _t15, float _t16, float _t17, float _r9, float _r10, float _r11, float _t0, float _t1, float _t18, float _t19, float _t20, float _t21, float _t23) {
        float _t24 = _r0 * _t15;
        float _t26 = _r7 * _t17;
        float _t47, _t48, _t49;
        if (java.lang.Math.fma(-java.lang.Math.fma(_t18, _t19, -(_t20 * _t21)), _r3 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t18, _t23, -(_t24 * _t21)), _r4 * _t16, java.lang.Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
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
        float _t63 = java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_r1, _t16, _t51));
        float _t65 = java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        return makeFromMatrix_s7b6de20e_2(d, _r9, _r10, _r11, _t19, _t24, java.lang.Math.fma(_r5, _t16, _t23), java.lang.Math.fma(_r2, _t15, -_t20), java.lang.Math.max(_t24, _t19), _t47, java.lang.Math.fma(_r3, _t15, _t48), java.lang.Math.fma(_r4, _t16, _t49), java.lang.Math.fma(_r4, _t16, -_t49), java.lang.Math.fma(-_r3, _t15, _t48), java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_r1, _t16, _t47)), _t63, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)), _t65, java.lang.Math.fma(_r1, _t16, java.lang.Math.fma(_t0, _t15, _t52)), java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t51)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65)));
    }

    /**
     * Piece 2 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private FloatRigid makeFromMatrix_s7b6de20e_1_mulAdd(FloatRigidImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r7, float _t15, float _t16, float _t17, float _r9, float _r10, float _r11, float _t0, float _t1, float _t18, float _t19, float _t20, float _t21, float _t23) {
        float _t24 = _r0 * _t15;
        float _t26 = _r7 * _t17;
        float _t47, _t48, _t49;
        if (((-((_t18) * (_t19) - (_t20 * _t21))) * (_r3 * _t15) + (((((_t18) * (_t23) - (_t24 * _t21))) * (_r4 * _t16) + (((_t24) * (_t19) - (_t20 * _t23)) * _t26)))) < 0.0f) {
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
        float _t63 = ((_r0) * (_t15) + (((_r1) * (_t16) + (_t51))));
        float _t65 = ((_r0) * (_t15) + (((_t1) * (_t16) + (_t52))));
        return makeFromMatrix_s7b6de20e_2(d, _r9, _r10, _r11, _t19, _t24, ((_r5) * (_t16) + (_t23)), ((_r2) * (_t15) - (_t20)), java.lang.Math.max(_t24, _t19), _t47, ((_r3) * (_t15) + (_t48)), ((_r4) * (_t16) + (_t49)), ((_r4) * (_t16) - (_t49)), ((-_r3) * (_t15) + (_t48)), ((_r0) * (_t15) + (((_r1) * (_t16) + (_t47)))), _t63, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)), _t65, ((_r1) * (_t16) + (((_t0) * (_t15) + (_t52)))), ((_t0) * (_t15) + (((_t1) * (_t16) + (_t51)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65)));
    }

    /**
     * Piece 3 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private FloatRigid makeFromMatrix_s7b6de20e_2(FloatRigidImpl d, float _r9, float _r10, float _r11, float _t19, float _t24, float _t31, float _t35, float _t36, float _t47, float _t54, float _t55, float _t56, float _t57, float _t62, float _t63, float _sp0, float _t65, float _t66, float _t67, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        d.tX = _r9;
        d.tY = _r10;
        d.tZ = _r11;
        d.rX = _t62 > 0.0f ? _sp0 * _t35 : _t47 > _t36 ? 0.5f * (float) java.lang.Math.sqrt(_t67) : _t24 > _t19 ? _sp1 * _t54 : _sp2 * _t55;
        d.rY = _t62 > 0.0f ? _sp0 * _t56 : _t47 > _t36 ? _sp3 * _t54 : _t24 > _t19 ? 0.5f * (float) java.lang.Math.sqrt(_t65) : _sp2 * _t31;
        d.rZ = _t62 > 0.0f ? _sp0 * _t57 : _t47 > _t36 ? _sp3 * _t55 : _t24 > _t19 ? _sp1 * _t31 : 0.5f * (float) java.lang.Math.sqrt(_t66);
        d.rW = _t62 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t63) : _t47 > _t36 ? _sp3 * _t35 : _t24 > _t19 ? _sp1 * _t56 : _sp2 * _t57;
        return d;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatRigid makeFromMatrix_degenerate_fma(Float3x4R m) {
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
                    _t168 = java.lang.Math.fma(_t35, _t106, -(_t33 * _t116));
                    _t171 = java.lang.Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        this.tX = m.m03();
        this.tY = m.m13();
        this.tZ = m.m23();
        if (_t206 > 0.0f) {
            this.rX = _sp0 * _t182;
            this.rY = _sp0 * _t201;
            this.rZ = _sp0 * _t202;
            this.rW = 0.5f * (float) java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                this.rX = 0.5f * (float) java.lang.Math.sqrt(_t208);
                this.rY = _sp3 * _t199;
                this.rZ = _sp3 * _t200;
                this.rW = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    this.rX = _sp1 * _t199;
                    this.rY = 0.5f * (float) java.lang.Math.sqrt(_t209);
                    this.rZ = _sp1 * _t184;
                    this.rW = _sp1 * _t201;
                } else {
                    this.rX = _sp2 * _t200;
                    this.rY = _sp2 * _t184;
                    this.rZ = 0.5f * (float) java.lang.Math.sqrt(_t210);
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
    @Mutated private FloatRigid makeFromMatrix_degenerate_mulAdd(Float3x4R m) {
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
        this.tX = m.m03();
        this.tY = m.m13();
        this.tZ = m.m23();
        if (_t206 > 0.0f) {
            this.rX = _sp0 * _t182;
            this.rY = _sp0 * _t201;
            this.rZ = _sp0 * _t202;
            this.rW = 0.5f * (float) java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                this.rX = 0.5f * (float) java.lang.Math.sqrt(_t208);
                this.rY = _sp3 * _t199;
                this.rZ = _sp3 * _t200;
                this.rW = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    this.rX = _sp1 * _t199;
                    this.rY = 0.5f * (float) java.lang.Math.sqrt(_t209);
                    this.rZ = _sp1 * _t184;
                    this.rW = _sp1 * _t201;
                } else {
                    this.rX = _sp2 * _t200;
                    this.rY = _sp2 * _t184;
                    this.rZ = 0.5f * (float) java.lang.Math.sqrt(_t210);
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
    @Mutated public FloatRigid makeFromMatrix(Float4x4R m) {
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid makeFromMatrix_fma(Float4x4R m) {
        float _r0 = m.m11();
        float _r1 = m.m22();
        float _r2 = m.m21();
        float _r3 = m.m01();
        float _r4 = m.m02();
        float _r5 = m.m12();
        float _r6 = m.m20();
        float _r7 = m.m00();
        float _r8 = m.m10();
        float _ct0 = java.lang.Math.fma(_r2, _r2, java.lang.Math.fma(_r3, _r3, _r0 * _r0));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _ct1 = java.lang.Math.fma(_r1, _r1, java.lang.Math.fma(_r4, _r4, _r5 * _r5));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = java.lang.Math.fma(_r6, _r6, java.lang.Math.fma(_r7, _r7, _r8 * _r8));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        float _r9 = m.m03();
        float _r10 = m.m13();
        return makeFromMatrix_s7b6de20e_1_fma(this, _r0, _r1, _r2, _r3, _r4, _r5, _r7, _t15, _t16, _t17, _r9, _r10, m.m23(), -_r0, -_r1, _r8 * _t17, _r1 * _t16, _r5 * _t16, _r6 * _t17, _r2 * _t15);
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid makeFromMatrix_mulAdd(Float4x4R m) {
        float _r0 = m.m11();
        float _r1 = m.m22();
        float _r2 = m.m21();
        float _r3 = m.m01();
        float _r4 = m.m02();
        float _r5 = m.m12();
        float _r6 = m.m20();
        float _r7 = m.m00();
        float _r8 = m.m10();
        float _ct0 = ((_r2) * (_r2) + (((_r3) * (_r3) + (_r0 * _r0))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _ct1 = ((_r1) * (_r1) + (((_r4) * (_r4) + (_r5 * _r5))));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = ((_r6) * (_r6) + (((_r7) * (_r7) + (_r8 * _r8))));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        float _r9 = m.m03();
        float _r10 = m.m13();
        return makeFromMatrix_s7b6de20e_1_mulAdd(this, _r0, _r1, _r2, _r3, _r4, _r5, _r7, _t15, _t16, _t17, _r9, _r10, m.m23(), -_r0, -_r1, _r8 * _t17, _r1 * _t16, _r5 * _t16, _r6 * _t17, _r2 * _t15);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatRigid makeFromMatrix_degenerate_fma(Float4x4R m) {
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
                    _t168 = java.lang.Math.fma(_t35, _t106, -(_t33 * _t116));
                    _t171 = java.lang.Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        this.tX = m.m03();
        this.tY = m.m13();
        this.tZ = m.m23();
        if (_t206 > 0.0f) {
            this.rX = _sp0 * _t182;
            this.rY = _sp0 * _t201;
            this.rZ = _sp0 * _t202;
            this.rW = 0.5f * (float) java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                this.rX = 0.5f * (float) java.lang.Math.sqrt(_t208);
                this.rY = _sp3 * _t199;
                this.rZ = _sp3 * _t200;
                this.rW = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    this.rX = _sp1 * _t199;
                    this.rY = 0.5f * (float) java.lang.Math.sqrt(_t209);
                    this.rZ = _sp1 * _t184;
                    this.rW = _sp1 * _t201;
                } else {
                    this.rX = _sp2 * _t200;
                    this.rY = _sp2 * _t184;
                    this.rZ = 0.5f * (float) java.lang.Math.sqrt(_t210);
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
    @Mutated private FloatRigid makeFromMatrix_degenerate_mulAdd(Float4x4R m) {
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
        this.tX = m.m03();
        this.tY = m.m13();
        this.tZ = m.m23();
        if (_t206 > 0.0f) {
            this.rX = _sp0 * _t182;
            this.rY = _sp0 * _t201;
            this.rZ = _sp0 * _t202;
            this.rW = 0.5f * (float) java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                this.rX = 0.5f * (float) java.lang.Math.sqrt(_t208);
                this.rY = _sp3 * _t199;
                this.rZ = _sp3 * _t200;
                this.rW = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    this.rX = _sp1 * _t199;
                    this.rY = 0.5f * (float) java.lang.Math.sqrt(_t209);
                    this.rZ = _sp1 * _t184;
                    this.rW = _sp1 * _t201;
                } else {
                    this.rX = _sp2 * _t200;
                    this.rY = _sp2 * _t184;
                    this.rZ = 0.5f * (float) java.lang.Math.sqrt(_t210);
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
    public @Mutated FloatRigid makeFromTransform(FloatTransformR t) {
        float tTY = t.tY();
        float tTZ = t.tZ();
        float tRX = t.rX();
        float tRY = t.rY();
        float tRZ = t.rZ();
        float tRW = t.rW();
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
    @Mutated public FloatRigid makeFromTransform(float tTX, float tTY, float tTZ, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
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
     * Convert this rigid transform to {@code double} precision and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid toDouble(@Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
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
    public FloatDualQuat toDualQuat(@Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        float _t0 = -this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.dX = 0.5f * Math.fma(_t0, this.rY, Math.fma(this.tX, this.rW, this.tY * this.rZ));
        d.dY = 0.5f * Math.fma(this.tZ, this.rX, Math.fma(this.tY, this.rW, -(this.tX * this.rZ)));
        d.dZ = 0.5f * Math.fma(this.tZ, this.rW, Math.fma(this.tX, this.rY, -(this.tY * this.rX)));
        d.dW = 0.5f * Math.fma(_t0, this.rZ, Math.fma(-this.tY, this.rY, -(this.tX * this.rX)));
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        float _t0 = -this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.dX = 0.5f * Math.fma(_t0, this.rY, Math.fma(this.tX, this.rW, this.tY * this.rZ));
        d.dY = 0.5f * Math.fma(this.tZ, this.rX, Math.fma(this.tY, this.rW, -(this.tX * this.rZ)));
        d.dZ = 0.5f * Math.fma(this.tZ, this.rW, Math.fma(this.tX, this.rY, -(this.tY * this.rX)));
        d.dW = 0.5f * Math.fma(_t0, this.rZ, Math.fma(-this.tY, this.rY, -(this.tX * this.rX)));
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
    public Float4x4 toMatrix(@Mutated Float4x4 dest) {
        Float4x4Impl d = (Float4x4Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t1 = this.rZ * this.rW;
        float _t2 = this.rY * this.rW;
        d.m00 = Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f);
        d.m10 = 2.0f * Math.fma(this.rX, this.rY, _t1);
        d.m20 = 2.0f * Math.fma(this.rX, this.rZ, -_t2);
        d.m30 = 0.0f;
        d.m01 = 2.0f * Math.fma(this.rX, this.rY, -_t1);
        d.m11 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f);
        d.m21 = 2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        d.m31 = 0.0f;
        d.m02 = 2.0f * Math.fma(this.rX, this.rZ, _t2);
        d.m12 = 2.0f * Math.fma(this.rY, this.rZ, -(this.rX * this.rW));
        d.m22 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        d.m32 = 0.0f;
        d.m03 = this.tX;
        d.m13 = this.tY;
        d.m23 = this.tZ;
        d.m33 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
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
        Double4x4Impl d = (Double4x4Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t1 = this.rZ * this.rW;
        float _t2 = this.rY * this.rW;
        d.m00 = Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f);
        d.m10 = 2.0f * Math.fma(this.rX, this.rY, _t1);
        d.m20 = 2.0f * Math.fma(this.rX, this.rZ, -_t2);
        d.m30 = 0.0f;
        d.m01 = 2.0f * Math.fma(this.rX, this.rY, -_t1);
        d.m11 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f);
        d.m21 = 2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        d.m31 = 0.0f;
        d.m02 = 2.0f * Math.fma(this.rX, this.rZ, _t2);
        d.m12 = 2.0f * Math.fma(this.rY, this.rZ, -(this.rX * this.rW));
        d.m22 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        d.m32 = 0.0f;
        d.m03 = this.tX;
        d.m13 = this.tY;
        d.m23 = this.tZ;
        d.m33 = 1.0f;
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
    public Float3x3 toMatrix3x3(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t1 = this.rZ * this.rW;
        float _t2 = this.rY * this.rW;
        d.m00 = Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f);
        d.m10 = 2.0f * Math.fma(this.rX, this.rY, _t1);
        d.m20 = 2.0f * Math.fma(this.rX, this.rZ, -_t2);
        d.m01 = 2.0f * Math.fma(this.rX, this.rY, -_t1);
        d.m11 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f);
        d.m21 = 2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        d.m02 = 2.0f * Math.fma(this.rX, this.rZ, _t2);
        d.m12 = 2.0f * Math.fma(this.rY, this.rZ, -(this.rX * this.rW));
        d.m22 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t1 = this.rZ * this.rW;
        float _t2 = this.rY * this.rW;
        d.m00 = Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f);
        d.m10 = 2.0f * Math.fma(this.rX, this.rY, _t1);
        d.m20 = 2.0f * Math.fma(this.rX, this.rZ, -_t2);
        d.m01 = 2.0f * Math.fma(this.rX, this.rY, -_t1);
        d.m11 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f);
        d.m21 = 2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        d.m02 = 2.0f * Math.fma(this.rX, this.rZ, _t2);
        d.m12 = 2.0f * Math.fma(this.rY, this.rZ, -(this.rX * this.rW));
        d.m22 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
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
    public Float3x4 toMatrix3x4(@Mutated Float3x4 dest) {
        Float3x4Impl d = (Float3x4Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t1 = this.rZ * this.rW;
        float _t2 = this.rY * this.rW;
        d.m00 = Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f);
        d.m01 = 2.0f * Math.fma(this.rX, this.rY, -_t1);
        d.m02 = 2.0f * Math.fma(this.rX, this.rZ, _t2);
        d.m03 = this.tX;
        d.m10 = 2.0f * Math.fma(this.rX, this.rY, _t1);
        d.m11 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f);
        d.m12 = 2.0f * Math.fma(this.rY, this.rZ, -(this.rX * this.rW));
        d.m13 = this.tY;
        d.m20 = 2.0f * Math.fma(this.rX, this.rZ, -_t2);
        d.m21 = 2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        d.m22 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        d.m23 = this.tZ;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
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
        Double3x4Impl d = (Double3x4Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t1 = this.rZ * this.rW;
        float _t2 = this.rY * this.rW;
        d.m00 = Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f);
        d.m01 = 2.0f * Math.fma(this.rX, this.rY, -_t1);
        d.m02 = 2.0f * Math.fma(this.rX, this.rZ, _t2);
        d.m03 = this.tX;
        d.m10 = 2.0f * Math.fma(this.rX, this.rY, _t1);
        d.m11 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f);
        d.m12 = 2.0f * Math.fma(this.rY, this.rZ, -(this.rX * this.rW));
        d.m13 = this.tY;
        d.m20 = 2.0f * Math.fma(this.rX, this.rZ, -_t2);
        d.m21 = 2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        d.m22 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
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
    public FloatTransform toTransform(@Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = 1.0f;
        d.sY = 1.0f;
        d.sZ = 1.0f;
        return d;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = 1.0f;
        d.sY = 1.0f;
        d.sZ = 1.0f;
        return d;
    }


    /**
     * Set this rigid transform to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public FloatRigid makeIdentity() {
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
        this.rX = 0.0f;
        this.rY = 0.0f;
        this.rZ = 0.0f;
        this.rW = 1.0f;
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
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
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
    @Mutated public FloatRigid set(float rotationX, float rotationY, float rotationZ, float rotationW) {
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
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
    public @Mutated FloatRigid set(Float3R translation) {
        float translationY = translation.y();
        float translationZ = translation.z();
        this.tX = translation.x();
        this.tY = translationY;
        this.tZ = translationZ;
        this.rX = 0.0f;
        this.rY = 0.0f;
        this.rZ = 0.0f;
        this.rW = 1.0f;
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
        this.tX = translationX;
        this.tY = translationY;
        this.tZ = translationZ;
        this.rX = 0.0f;
        this.rY = 0.0f;
        this.rZ = 0.0f;
        this.rW = 1.0f;
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

    /** Private store group 0 of {@code lerp}: computes and stores it; reached only through it. */
    private void lerp_s525d8d2_c0_fma(FloatRigidImpl _dst, float t, float otherTX, float _r4, float otherTY, float _r5, float otherTZ, float _r6, float _t49, float _t50, float _t44) {
        _dst.tX = java.lang.Math.fma(t, otherTX - _r4, _r4);
        _dst.tY = java.lang.Math.fma(t, otherTY - _r5, _r5);
        _dst.tZ = java.lang.Math.fma(t, otherTZ - _r6, _r6);
        _dst.rX = _t49 != 0.0f ? _t50 * _t44 : 0.0f;
    }

    /** Private store group 0 of {@code lerp}: computes and stores it; reached only through it. */
    private void lerp_s525d8d2_c0_mulAdd(FloatRigidImpl _dst, float t, float otherTX, float _r4, float otherTY, float _r5, float otherTZ, float _r6, float _t49, float _t50, float _t44) {
        _dst.tX = ((t) * (otherTX - _r4) + (_r4));
        _dst.tY = ((t) * (otherTY - _r5) + (_r5));
        _dst.tZ = ((t) * (otherTZ - _r6) + (_r6));
        _dst.rX = _t49 != 0.0f ? _t50 * _t44 : 0.0f;
    }

    /** Private store group 1 of {@code lerp}: computes and stores it; reached only through it. */
    private void lerp_s525d8d2_c1(FloatRigidImpl _dst, float _t49, float _t50, float _t45, float _t43, float _t42) {
        _dst.rY = _t49 != 0.0f ? _t50 * _t45 : 0.0f;
        _dst.rZ = _t49 != 0.0f ? _t50 * _t43 : 0.0f;
        _dst.rW = _t49 != 0.0f ? _t50 * _t42 : 0.0f;
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private void lerp_s525d8d2_tail_fma(FloatRigidImpl _dst, float _t0, float _t16, float _t17, float _r0, float _t19, float _t21, float _t17_inv, float t, float _r1, float _t22, float _r2, float _t23, float _r3, float _t24, float otherTX, float _r4, float otherTY, float _r5, float otherTZ, float _r6) {
        float _t25 = Math.sin(_t0 * _t16);
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
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
        float _t49 = java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, _t45 * _t45)));
        float _t50 = (1.0f / (float) java.lang.Math.sqrt(_t49));
        lerp_s525d8d2_c0_fma(_dst, t, otherTX, _r4, otherTY, _r5, otherTZ, _r6, _t49, _t50, _t44);
        lerp_s525d8d2_c1(_dst, _t49, _t50, _t45, _t43, _t42);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private void lerp_s525d8d2_tail_mulAdd(FloatRigidImpl _dst, float _t0, float _t16, float _t17, float _r0, float _t19, float _t21, float _t17_inv, float t, float _r1, float _t22, float _r2, float _t23, float _r3, float _t24, float otherTX, float _r4, float otherTY, float _r5, float otherTZ, float _r6) {
        float _t25 = Math.sin(_t0 * _t16);
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
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
        float _t49 = ((_t42) * (_t42) + (((_t43) * (_t43) + (((_t44) * (_t44) + (_t45 * _t45))))));
        float _t50 = (1.0f / (float) java.lang.Math.sqrt(_t49));
        lerp_s525d8d2_c0_mulAdd(_dst, t, otherTX, _r4, otherTY, _r5, otherTZ, _r6, _t49, _t50, _t44);
        lerp_s525d8d2_c1(_dst, _t49, _t50, _t45, _t43, _t42);
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
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _r0 = this.rW;
        float _r1 = this.rZ;
        float _r2 = this.rX;
        float _r3 = this.rY;
        float _r4 = this.tX;
        float _r5 = this.tY;
        float _r6 = this.tZ;
        float _t12 = Math.fma(otherRW, _r0, Math.fma(otherRZ, _r1, Math.fma(otherRX, _r2, otherRY * _r3)));
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
        if (Math.useFma()) lerp_s525d8d2_tail_fma(d, 1.0f - t, _t16, _t17, _r0, Math.sin(t * _t16), _t21, 1.0f / _t17, t, _r1, _t22, _r2, _t23, _r3, _t24, otherTX, _r4, otherTY, _r5, otherTZ, _r6); else lerp_s525d8d2_tail_mulAdd(d, 1.0f - t, _t16, _t17, _r0, Math.sin(t * _t16), _t21, 1.0f / _t17, t, _r1, _t22, _r2, _t23, _r3, _t24, otherTX, _r4, otherTY, _r5, otherTZ, _r6);
        return d;
    }

    /** Private store group 0 of {@code lerp}: computes and stores it; reached only through it. */
    private void lerp_s521cc429_c0_fma(DoubleRigidImpl _dst, float t, float otherTX, float _r4, float otherTY, float _r5, float otherTZ, float _r6, float _t49, float _t50, float _t44) {
        _dst.tX = java.lang.Math.fma(t, otherTX - _r4, _r4);
        _dst.tY = java.lang.Math.fma(t, otherTY - _r5, _r5);
        _dst.tZ = java.lang.Math.fma(t, otherTZ - _r6, _r6);
        _dst.rX = _t49 != 0.0f ? _t50 * _t44 : 0.0f;
    }

    /** Private store group 0 of {@code lerp}: computes and stores it; reached only through it. */
    private void lerp_s521cc429_c0_mulAdd(DoubleRigidImpl _dst, float t, float otherTX, float _r4, float otherTY, float _r5, float otherTZ, float _r6, float _t49, float _t50, float _t44) {
        _dst.tX = ((t) * (otherTX - _r4) + (_r4));
        _dst.tY = ((t) * (otherTY - _r5) + (_r5));
        _dst.tZ = ((t) * (otherTZ - _r6) + (_r6));
        _dst.rX = _t49 != 0.0f ? _t50 * _t44 : 0.0f;
    }

    /** Private store group 1 of {@code lerp}: computes and stores it; reached only through it. */
    private void lerp_s521cc429_c1(DoubleRigidImpl _dst, float _t49, float _t50, float _t45, float _t43, float _t42) {
        _dst.rY = _t49 != 0.0f ? _t50 * _t45 : 0.0f;
        _dst.rZ = _t49 != 0.0f ? _t50 * _t43 : 0.0f;
        _dst.rW = _t49 != 0.0f ? _t50 * _t42 : 0.0f;
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private void lerp_s521cc429_tail_fma(DoubleRigidImpl _dst, float _t0, float _t16, float _t17, float _r0, float _t19, float _t21, float _t17_inv, float t, float _r1, float _t22, float _r2, float _t23, float _r3, float _t24, float otherTX, float _r4, float otherTY, float _r5, float otherTZ, float _r6) {
        float _t25 = Math.sin(_t0 * _t16);
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
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
        float _t49 = java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, _t45 * _t45)));
        float _t50 = (1.0f / (float) java.lang.Math.sqrt(_t49));
        lerp_s521cc429_c0_fma(_dst, t, otherTX, _r4, otherTY, _r5, otherTZ, _r6, _t49, _t50, _t44);
        lerp_s521cc429_c1(_dst, _t49, _t50, _t45, _t43, _t42);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private void lerp_s521cc429_tail_mulAdd(DoubleRigidImpl _dst, float _t0, float _t16, float _t17, float _r0, float _t19, float _t21, float _t17_inv, float t, float _r1, float _t22, float _r2, float _t23, float _r3, float _t24, float otherTX, float _r4, float otherTY, float _r5, float otherTZ, float _r6) {
        float _t25 = Math.sin(_t0 * _t16);
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
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
        float _t49 = ((_t42) * (_t42) + (((_t43) * (_t43) + (((_t44) * (_t44) + (_t45 * _t45))))));
        float _t50 = (1.0f / (float) java.lang.Math.sqrt(_t49));
        lerp_s521cc429_c0_mulAdd(_dst, t, otherTX, _r4, otherTY, _r5, otherTZ, _r6, _t49, _t50, _t44);
        lerp_s521cc429_c1(_dst, _t49, _t50, _t45, _t43, _t42);
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
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        float _r0 = this.rW;
        float _r1 = this.rZ;
        float _r2 = this.rX;
        float _r3 = this.rY;
        float _r4 = this.tX;
        float _r5 = this.tY;
        float _r6 = this.tZ;
        float _t12 = Math.fma(otherRW, _r0, Math.fma(otherRZ, _r1, Math.fma(otherRX, _r2, otherRY * _r3)));
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
        if (Math.useFma()) lerp_s521cc429_tail_fma(d, 1.0f - t, _t16, _t17, _r0, Math.sin(t * _t16), _t21, 1.0f / _t17, t, _r1, _t22, _r2, _t23, _r3, _t24, otherTX, _r4, otherTY, _r5, otherTZ, _r6); else lerp_s521cc429_tail_mulAdd(d, 1.0f - t, _t16, _t17, _r0, Math.sin(t * _t16), _t21, 1.0f / _t17, t, _r1, _t22, _r2, _t23, _r3, _t24, otherTX, _r4, otherTY, _r5, otherTZ, _r6);
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

    /** Private store group 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s7a4d9c14_c0_fma(FloatRigidImpl _dst, float _r1, float _t9, float _r2, float _t10, float _r3, float _t11, float _r4, float otherTX, float _r0, float _r5, float otherTY, float _r6, float otherTZ, float otherRX, float otherRW, float otherRZ, float otherRY) {
        _dst.tX = java.lang.Math.fma(_r1, _t9, java.lang.Math.fma(-_r2, _t10, java.lang.Math.fma(_r3, _t11, _r4 + otherTX)));
        _dst.tY = java.lang.Math.fma(_r2, _t11, java.lang.Math.fma(-_r0, _t9, java.lang.Math.fma(_r3, _t10, _r5 + otherTY)));
        _dst.tZ = java.lang.Math.fma(_r0, _t10, java.lang.Math.fma(-_r1, _t11, java.lang.Math.fma(_r3, _t9, _r6 + otherTZ)));
        _dst.rX = java.lang.Math.fma(otherRX, _r3, otherRW * _r0) + java.lang.Math.fma(otherRZ, _r1, -(otherRY * _r2));
    }

    /** Private store group 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s7a4d9c14_c0_mulAdd(FloatRigidImpl _dst, float _r1, float _t9, float _r2, float _t10, float _r3, float _t11, float _r4, float otherTX, float _r0, float _r5, float otherTY, float _r6, float otherTZ, float otherRX, float otherRW, float otherRZ, float otherRY) {
        _dst.tX = ((_r1) * (_t9) + (((-_r2) * (_t10) + (((_r3) * (_t11) + (_r4 + otherTX))))));
        _dst.tY = ((_r2) * (_t11) + (((-_r0) * (_t9) + (((_r3) * (_t10) + (_r5 + otherTY))))));
        _dst.tZ = ((_r0) * (_t10) + (((-_r1) * (_t11) + (((_r3) * (_t9) + (_r6 + otherTZ))))));
        _dst.rX = ((otherRX) * (_r3) + (otherRW * _r0)) + ((otherRZ) * (_r1) - (otherRY * _r2));
    }

    /** Private store group 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s7a4d9c14_c1_fma(FloatRigidImpl _dst, float otherRX, float _r2, float otherRW, float _r1, float otherRY, float _r3, float otherRZ, float _r0) {
        _dst.rY = java.lang.Math.fma(otherRX, _r2, otherRW * _r1) + java.lang.Math.fma(otherRY, _r3, -(otherRZ * _r0));
        _dst.rZ = java.lang.Math.fma(otherRY, _r0, otherRZ * _r3) + java.lang.Math.fma(otherRW, _r2, -(otherRX * _r1));
        _dst.rW = java.lang.Math.fma(otherRW, _r3, -(otherRX * _r0)) - java.lang.Math.fma(otherRY, _r1, otherRZ * _r2);
    }

    /** Private store group 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s7a4d9c14_c1_mulAdd(FloatRigidImpl _dst, float otherRX, float _r2, float otherRW, float _r1, float otherRY, float _r3, float otherRZ, float _r0) {
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
    public FloatRigid mul(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated FloatRigid dest) {
        if (Math.useFma()) {
            FloatRigidImpl d = (FloatRigidImpl) dest;
            float _r0 = this.rX;
            float _r1 = this.rY;
            float _r2 = this.rZ;
            float _r3 = this.rW;
            float _r4 = this.tX;
            float _r5 = this.tY;
            float _r6 = this.tZ;
            mul_s7a4d9c14_c0_fma(d, _r1, 2.0f * java.lang.Math.fma(otherTY, _r0, -(otherTX * _r1)), _r2, 2.0f * java.lang.Math.fma(otherTX, _r2, -(otherTZ * _r0)), _r3, 2.0f * java.lang.Math.fma(otherTZ, _r1, -(otherTY * _r2)), _r4, otherTX, _r0, _r5, otherTY, _r6, otherTZ, otherRX, otherRW, otherRZ, otherRY);
            mul_s7a4d9c14_c1_fma(d, otherRX, _r2, otherRW, _r1, otherRY, _r3, otherRZ, _r0);
            return d;
        } else {
            FloatRigidImpl d = (FloatRigidImpl) dest;
            float _r0 = this.rX;
            float _r1 = this.rY;
            float _r2 = this.rZ;
            float _r3 = this.rW;
            float _r4 = this.tX;
            float _r5 = this.tY;
            float _r6 = this.tZ;
            mul_s7a4d9c14_c0_mulAdd(d, _r1, 2.0f * ((otherTY) * (_r0) - (otherTX * _r1)), _r2, 2.0f * ((otherTX) * (_r2) - (otherTZ * _r0)), _r3, 2.0f * ((otherTZ) * (_r1) - (otherTY * _r2)), _r4, otherTX, _r0, _r5, otherTY, _r6, otherTZ, otherRX, otherRW, otherRZ, otherRY);
            mul_s7a4d9c14_c1_mulAdd(d, otherRX, _r2, otherRW, _r1, otherRY, _r3, otherRZ, _r0);
            return d;
        }
    }

    /** Private store group 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s1ed6927_c0_fma(DoubleRigidImpl _dst, float _r1, float _t9, float _r2, float _t10, float _r3, float _t11, float _r4, float otherTX, float _r0, float _r5, float otherTY, float _r6, float otherTZ, float otherRX, float otherRW, float otherRZ, float otherRY) {
        _dst.tX = java.lang.Math.fma(_r1, _t9, java.lang.Math.fma(-_r2, _t10, java.lang.Math.fma(_r3, _t11, _r4 + otherTX)));
        _dst.tY = java.lang.Math.fma(_r2, _t11, java.lang.Math.fma(-_r0, _t9, java.lang.Math.fma(_r3, _t10, _r5 + otherTY)));
        _dst.tZ = java.lang.Math.fma(_r0, _t10, java.lang.Math.fma(-_r1, _t11, java.lang.Math.fma(_r3, _t9, _r6 + otherTZ)));
        _dst.rX = java.lang.Math.fma(otherRX, _r3, otherRW * _r0) + java.lang.Math.fma(otherRZ, _r1, -(otherRY * _r2));
    }

    /** Private store group 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s1ed6927_c0_mulAdd(DoubleRigidImpl _dst, float _r1, float _t9, float _r2, float _t10, float _r3, float _t11, float _r4, float otherTX, float _r0, float _r5, float otherTY, float _r6, float otherTZ, float otherRX, float otherRW, float otherRZ, float otherRY) {
        _dst.tX = ((_r1) * (_t9) + (((-_r2) * (_t10) + (((_r3) * (_t11) + (_r4 + otherTX))))));
        _dst.tY = ((_r2) * (_t11) + (((-_r0) * (_t9) + (((_r3) * (_t10) + (_r5 + otherTY))))));
        _dst.tZ = ((_r0) * (_t10) + (((-_r1) * (_t11) + (((_r3) * (_t9) + (_r6 + otherTZ))))));
        _dst.rX = ((otherRX) * (_r3) + (otherRW * _r0)) + ((otherRZ) * (_r1) - (otherRY * _r2));
    }

    /** Private store group 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s1ed6927_c1_fma(DoubleRigidImpl _dst, float otherRX, float _r2, float otherRW, float _r1, float otherRY, float _r3, float otherRZ, float _r0) {
        _dst.rY = java.lang.Math.fma(otherRX, _r2, otherRW * _r1) + java.lang.Math.fma(otherRY, _r3, -(otherRZ * _r0));
        _dst.rZ = java.lang.Math.fma(otherRY, _r0, otherRZ * _r3) + java.lang.Math.fma(otherRW, _r2, -(otherRX * _r1));
        _dst.rW = java.lang.Math.fma(otherRW, _r3, -(otherRX * _r0)) - java.lang.Math.fma(otherRY, _r1, otherRZ * _r2);
    }

    /** Private store group 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s1ed6927_c1_mulAdd(DoubleRigidImpl _dst, float otherRX, float _r2, float otherRW, float _r1, float otherRY, float _r3, float otherRZ, float _r0) {
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
        if (Math.useFma()) {
            DoubleRigidImpl d = (DoubleRigidImpl) dest;
            float _r0 = this.rX;
            float _r1 = this.rY;
            float _r2 = this.rZ;
            float _r3 = this.rW;
            float _r4 = this.tX;
            float _r5 = this.tY;
            float _r6 = this.tZ;
            mul_s1ed6927_c0_fma(d, _r1, 2.0f * java.lang.Math.fma(otherTY, _r0, -(otherTX * _r1)), _r2, 2.0f * java.lang.Math.fma(otherTX, _r2, -(otherTZ * _r0)), _r3, 2.0f * java.lang.Math.fma(otherTZ, _r1, -(otherTY * _r2)), _r4, otherTX, _r0, _r5, otherTY, _r6, otherTZ, otherRX, otherRW, otherRZ, otherRY);
            mul_s1ed6927_c1_fma(d, otherRX, _r2, otherRW, _r1, otherRY, _r3, otherRZ, _r0);
            return d;
        } else {
            DoubleRigidImpl d = (DoubleRigidImpl) dest;
            float _r0 = this.rX;
            float _r1 = this.rY;
            float _r2 = this.rZ;
            float _r3 = this.rW;
            float _r4 = this.tX;
            float _r5 = this.tY;
            float _r6 = this.tZ;
            mul_s1ed6927_c0_mulAdd(d, _r1, 2.0f * ((otherTY) * (_r0) - (otherTX * _r1)), _r2, 2.0f * ((otherTX) * (_r2) - (otherTZ * _r0)), _r3, 2.0f * ((otherTZ) * (_r1) - (otherTY * _r2)), _r4, otherTX, _r0, _r5, otherTY, _r6, otherTZ, otherRX, otherRW, otherRZ, otherRY);
            mul_s1ed6927_c1_mulAdd(d, otherRX, _r2, otherRW, _r1, otherRY, _r3, otherRZ, _r0);
            return d;
        }
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

    /** Private store group 0 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s7a4d9c14_c0_fma(FloatRigidImpl _dst, float otherRY, float _t9, float otherRZ, float _t10, float otherRW, float _t11, float otherTX, float _r1, float otherRX, float otherTY, float _r0, float otherTZ, float _r2, float _r3, float _r4, float _r5, float _r6) {
        _dst.tX = java.lang.Math.fma(otherRY, _t9, java.lang.Math.fma(-otherRZ, _t10, java.lang.Math.fma(otherRW, _t11, otherTX + _r1)));
        _dst.tY = java.lang.Math.fma(otherRZ, _t11, java.lang.Math.fma(-otherRX, _t9, java.lang.Math.fma(otherRW, _t10, otherTY + _r0)));
        _dst.tZ = java.lang.Math.fma(otherRX, _t10, java.lang.Math.fma(-otherRY, _t11, java.lang.Math.fma(otherRW, _t9, otherTZ + _r2)));
        _dst.rX = java.lang.Math.fma(otherRX, _r3, otherRW * _r4) + java.lang.Math.fma(otherRY, _r5, -(otherRZ * _r6));
    }

    /** Private store group 0 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s7a4d9c14_c0_mulAdd(FloatRigidImpl _dst, float otherRY, float _t9, float otherRZ, float _t10, float otherRW, float _t11, float otherTX, float _r1, float otherRX, float otherTY, float _r0, float otherTZ, float _r2, float _r3, float _r4, float _r5, float _r6) {
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
    private void preMul_s7a4d9c14_c1_fma(FloatRigidImpl _dst, float otherRY, float _r3, float otherRZ, float _r4, float otherRW, float _r6, float otherRX, float _r5) {
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
    private void preMul_s7a4d9c14_c1_mulAdd(FloatRigidImpl _dst, float otherRY, float _r3, float otherRZ, float _r4, float otherRW, float _r6, float otherRX, float _r5) {
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
    public FloatRigid preMul(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated FloatRigid dest) {
        if (Math.useFma()) {
            FloatRigidImpl d = (FloatRigidImpl) dest;
            float _r0 = this.tY;
            float _r1 = this.tX;
            float _r2 = this.tZ;
            float _r3 = this.rW;
            float _r4 = this.rX;
            float _r5 = this.rZ;
            float _r6 = this.rY;
            preMul_s7a4d9c14_c0_fma(d, otherRY, 2.0f * java.lang.Math.fma(otherRX, _r0, -(otherRY * _r1)), otherRZ, 2.0f * java.lang.Math.fma(otherRZ, _r1, -(otherRX * _r2)), otherRW, 2.0f * java.lang.Math.fma(otherRY, _r2, -(otherRZ * _r0)), otherTX, _r1, otherRX, otherTY, _r0, otherTZ, _r2, _r3, _r4, _r5, _r6);
            preMul_s7a4d9c14_c1_fma(d, otherRY, _r3, otherRZ, _r4, otherRW, _r6, otherRX, _r5);
            return d;
        } else {
            FloatRigidImpl d = (FloatRigidImpl) dest;
            float _r0 = this.tY;
            float _r1 = this.tX;
            float _r2 = this.tZ;
            float _r3 = this.rW;
            float _r4 = this.rX;
            float _r5 = this.rZ;
            float _r6 = this.rY;
            preMul_s7a4d9c14_c0_mulAdd(d, otherRY, 2.0f * ((otherRX) * (_r0) - (otherRY * _r1)), otherRZ, 2.0f * ((otherRZ) * (_r1) - (otherRX * _r2)), otherRW, 2.0f * ((otherRY) * (_r2) - (otherRZ * _r0)), otherTX, _r1, otherRX, otherTY, _r0, otherTZ, _r2, _r3, _r4, _r5, _r6);
            preMul_s7a4d9c14_c1_mulAdd(d, otherRY, _r3, otherRZ, _r4, otherRW, _r6, otherRX, _r5);
            return d;
        }
    }

    /** Private store group 0 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s1ed6927_c0_fma(DoubleRigidImpl _dst, float otherRY, float _t9, float otherRZ, float _t10, float otherRW, float _t11, float otherTX, float _r1, float otherRX, float otherTY, float _r0, float otherTZ, float _r2, float _r3, float _r4, float _r5, float _r6) {
        _dst.tX = java.lang.Math.fma(otherRY, _t9, java.lang.Math.fma(-otherRZ, _t10, java.lang.Math.fma(otherRW, _t11, otherTX + _r1)));
        _dst.tY = java.lang.Math.fma(otherRZ, _t11, java.lang.Math.fma(-otherRX, _t9, java.lang.Math.fma(otherRW, _t10, otherTY + _r0)));
        _dst.tZ = java.lang.Math.fma(otherRX, _t10, java.lang.Math.fma(-otherRY, _t11, java.lang.Math.fma(otherRW, _t9, otherTZ + _r2)));
        _dst.rX = java.lang.Math.fma(otherRX, _r3, otherRW * _r4) + java.lang.Math.fma(otherRY, _r5, -(otherRZ * _r6));
    }

    /** Private store group 0 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s1ed6927_c0_mulAdd(DoubleRigidImpl _dst, float otherRY, float _t9, float otherRZ, float _t10, float otherRW, float _t11, float otherTX, float _r1, float otherRX, float otherTY, float _r0, float otherTZ, float _r2, float _r3, float _r4, float _r5, float _r6) {
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
    private void preMul_s1ed6927_c1_fma(DoubleRigidImpl _dst, float otherRY, float _r3, float otherRZ, float _r4, float otherRW, float _r6, float otherRX, float _r5) {
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
    private void preMul_s1ed6927_c1_mulAdd(DoubleRigidImpl _dst, float otherRY, float _r3, float otherRZ, float _r4, float otherRW, float _r6, float otherRX, float _r5) {
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
        if (Math.useFma()) {
            DoubleRigidImpl d = (DoubleRigidImpl) dest;
            float _r0 = this.tY;
            float _r1 = this.tX;
            float _r2 = this.tZ;
            float _r3 = this.rW;
            float _r4 = this.rX;
            float _r5 = this.rZ;
            float _r6 = this.rY;
            preMul_s1ed6927_c0_fma(d, otherRY, 2.0f * java.lang.Math.fma(otherRX, _r0, -(otherRY * _r1)), otherRZ, 2.0f * java.lang.Math.fma(otherRZ, _r1, -(otherRX * _r2)), otherRW, 2.0f * java.lang.Math.fma(otherRY, _r2, -(otherRZ * _r0)), otherTX, _r1, otherRX, otherTY, _r0, otherTZ, _r2, _r3, _r4, _r5, _r6);
            preMul_s1ed6927_c1_fma(d, otherRY, _r3, otherRZ, _r4, otherRW, _r6, otherRX, _r5);
            return d;
        } else {
            DoubleRigidImpl d = (DoubleRigidImpl) dest;
            float _r0 = this.tY;
            float _r1 = this.tX;
            float _r2 = this.tZ;
            float _r3 = this.rW;
            float _r4 = this.rX;
            float _r5 = this.rZ;
            float _r6 = this.rY;
            preMul_s1ed6927_c0_mulAdd(d, otherRY, 2.0f * ((otherRX) * (_r0) - (otherRY * _r1)), otherRZ, 2.0f * ((otherRZ) * (_r1) - (otherRX * _r2)), otherRW, 2.0f * ((otherRY) * (_r2) - (otherRZ * _r0)), otherTX, _r1, otherRX, otherTY, _r0, otherTZ, _r2, _r3, _r4, _r5, _r6);
            preMul_s1ed6927_c1_mulAdd(d, otherRY, _r3, otherRZ, _r4, otherRW, _r6, otherRX, _r5);
            return d;
        }
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
        float otherTX = other.tX();
        float otherTY = other.tY();
        float otherTZ = other.tZ();
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        if (Math.useFma()) return difference_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
        return difference_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
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
        float otherTX = other.tX();
        float otherTY = other.tY();
        float otherTZ = other.tZ();
        float otherRX = other.rX();
        float otherRY = other.rY();
        float otherRZ = other.rZ();
        float otherRW = other.rW();
        if (Math.useFma()) return difference_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
        return difference_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
    }

    /** Private store group 0 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s7a4d9c14_c0_fma(FloatRigidImpl _dst, float _r1, float _t18, float otherTX, float _r6, float _t19, float _r2, float _t20, float _t21, float _t22, float _t23, float _r3, float _r0, float otherTY, float _r5, float otherTZ, float _r4, float otherRX, float otherRW, float otherRY, float otherRZ) {
        _dst.tX = java.lang.Math.fma(_r1, _t18, otherTX) + java.lang.Math.fma(_r6, _t19, -(_r2 * _t20)) + (java.lang.Math.fma(_r1, _t21, -(_r2 * _t22)) + java.lang.Math.fma(_r6, _t23, -_r3));
        _dst.tY = java.lang.Math.fma(_r0, _t20, otherTY) + java.lang.Math.fma(_r6, _t18, -(_r1 * _t19)) + (java.lang.Math.fma(_r0, _t22, -(_r1 * _t23)) + java.lang.Math.fma(_r6, _t21, -_r5));
        _dst.tZ = java.lang.Math.fma(_r2, _t19, otherTZ) + java.lang.Math.fma(_r6, _t20, -(_r0 * _t18)) + (java.lang.Math.fma(_r2, _t23, -(_r0 * _t21)) + java.lang.Math.fma(_r6, _t22, -_r4));
        _dst.rX = java.lang.Math.fma(otherRX, _r6, -(otherRW * _r0)) + java.lang.Math.fma(otherRY, _r1, -(otherRZ * _r2));
    }

    /** Private store group 0 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s7a4d9c14_c0_mulAdd(FloatRigidImpl _dst, float _r1, float _t18, float otherTX, float _r6, float _t19, float _r2, float _t20, float _t21, float _t22, float _t23, float _r3, float _r0, float otherTY, float _r5, float otherTZ, float _r4, float otherRX, float otherRW, float otherRY, float otherRZ) {
        _dst.tX = ((_r1) * (_t18) + (otherTX)) + ((_r6) * (_t19) - (_r2 * _t20)) + (((_r1) * (_t21) - (_r2 * _t22)) + ((_r6) * (_t23) - (_r3)));
        _dst.tY = ((_r0) * (_t20) + (otherTY)) + ((_r6) * (_t18) - (_r1 * _t19)) + (((_r0) * (_t22) - (_r1 * _t23)) + ((_r6) * (_t21) - (_r5)));
        _dst.tZ = ((_r2) * (_t19) + (otherTZ)) + ((_r6) * (_t20) - (_r0 * _t18)) + (((_r2) * (_t23) - (_r0 * _t21)) + ((_r6) * (_t22) - (_r4)));
        _dst.rX = ((otherRX) * (_r6) - (otherRW * _r0)) + ((otherRY) * (_r1) - (otherRZ * _r2));
    }

    /** Private store group 1 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s7a4d9c14_c1_fma(FloatRigidImpl _dst, float otherRY, float _r6, float otherRZ, float _r0, float otherRX, float _r1, float otherRW, float _r2) {
        _dst.rY = java.lang.Math.fma(otherRY, _r6, otherRZ * _r0) + java.lang.Math.fma(-otherRX, _r1, -(otherRW * _r2));
        _dst.rZ = java.lang.Math.fma(otherRX, _r2, -(otherRW * _r1)) + java.lang.Math.fma(otherRZ, _r6, -(otherRY * _r0));
        _dst.rW = java.lang.Math.fma(otherRX, _r0, otherRW * _r6) - java.lang.Math.fma(-otherRZ, _r1, -(otherRY * _r2));
    }

    /** Private store group 1 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s7a4d9c14_c1_mulAdd(FloatRigidImpl _dst, float otherRY, float _r6, float otherRZ, float _r0, float otherRX, float _r1, float otherRW, float _r2) {
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
    public FloatRigid difference(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated FloatRigid dest) {
        if (Math.useFma()) return difference_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
        return difference_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
    }

    /** {@code difference} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid difference_fma(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rZ;
        float _r2 = this.rY;
        float _r3 = this.tX;
        float _r4 = this.tZ;
        float _r5 = this.tY;
        float _r6 = this.rW;
        difference_s7a4d9c14_c0_fma(d, _r1, 2.0f * java.lang.Math.fma(otherTZ, _r0, -(otherTX * _r1)), otherTX, _r6, 2.0f * java.lang.Math.fma(otherTY, _r1, -(otherTZ * _r2)), _r2, 2.0f * java.lang.Math.fma(otherTX, _r2, -(otherTY * _r0)), 2.0f * java.lang.Math.fma(_r3, _r1, -(_r4 * _r0)), 2.0f * java.lang.Math.fma(_r5, _r0, -(_r3 * _r2)), 2.0f * java.lang.Math.fma(_r4, _r2, -(_r5 * _r1)), _r3, _r0, otherTY, _r5, otherTZ, _r4, otherRX, otherRW, otherRY, otherRZ);
        difference_s7a4d9c14_c1_fma(d, otherRY, _r6, otherRZ, _r0, otherRX, _r1, otherRW, _r2);
        return d;
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid difference_mulAdd(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rZ;
        float _r2 = this.rY;
        float _r3 = this.tX;
        float _r4 = this.tZ;
        float _r5 = this.tY;
        float _r6 = this.rW;
        difference_s7a4d9c14_c0_mulAdd(d, _r1, 2.0f * ((otherTZ) * (_r0) - (otherTX * _r1)), otherTX, _r6, 2.0f * ((otherTY) * (_r1) - (otherTZ * _r2)), _r2, 2.0f * ((otherTX) * (_r2) - (otherTY * _r0)), 2.0f * ((_r3) * (_r1) - (_r4 * _r0)), 2.0f * ((_r5) * (_r0) - (_r3 * _r2)), 2.0f * ((_r4) * (_r2) - (_r5 * _r1)), _r3, _r0, otherTY, _r5, otherTZ, _r4, otherRX, otherRW, otherRY, otherRZ);
        difference_s7a4d9c14_c1_mulAdd(d, otherRY, _r6, otherRZ, _r0, otherRX, _r1, otherRW, _r2);
        return d;
    }

    /** Private store group 0 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s1ed6927_c0_fma(DoubleRigidImpl _dst, float _r1, float _t18, float otherTX, float _r6, float _t19, float _r2, float _t20, float _t21, float _t22, float _t23, float _r3, float _r0, float otherTY, float _r5, float otherTZ, float _r4, float otherRX, float otherRW, float otherRY, float otherRZ) {
        _dst.tX = java.lang.Math.fma(_r1, _t18, otherTX) + java.lang.Math.fma(_r6, _t19, -(_r2 * _t20)) + (java.lang.Math.fma(_r1, _t21, -(_r2 * _t22)) + java.lang.Math.fma(_r6, _t23, -_r3));
        _dst.tY = java.lang.Math.fma(_r0, _t20, otherTY) + java.lang.Math.fma(_r6, _t18, -(_r1 * _t19)) + (java.lang.Math.fma(_r0, _t22, -(_r1 * _t23)) + java.lang.Math.fma(_r6, _t21, -_r5));
        _dst.tZ = java.lang.Math.fma(_r2, _t19, otherTZ) + java.lang.Math.fma(_r6, _t20, -(_r0 * _t18)) + (java.lang.Math.fma(_r2, _t23, -(_r0 * _t21)) + java.lang.Math.fma(_r6, _t22, -_r4));
        _dst.rX = java.lang.Math.fma(otherRX, _r6, -(otherRW * _r0)) + java.lang.Math.fma(otherRY, _r1, -(otherRZ * _r2));
    }

    /** Private store group 0 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s1ed6927_c0_mulAdd(DoubleRigidImpl _dst, float _r1, float _t18, float otherTX, float _r6, float _t19, float _r2, float _t20, float _t21, float _t22, float _t23, float _r3, float _r0, float otherTY, float _r5, float otherTZ, float _r4, float otherRX, float otherRW, float otherRY, float otherRZ) {
        _dst.tX = ((_r1) * (_t18) + (otherTX)) + ((_r6) * (_t19) - (_r2 * _t20)) + (((_r1) * (_t21) - (_r2 * _t22)) + ((_r6) * (_t23) - (_r3)));
        _dst.tY = ((_r0) * (_t20) + (otherTY)) + ((_r6) * (_t18) - (_r1 * _t19)) + (((_r0) * (_t22) - (_r1 * _t23)) + ((_r6) * (_t21) - (_r5)));
        _dst.tZ = ((_r2) * (_t19) + (otherTZ)) + ((_r6) * (_t20) - (_r0 * _t18)) + (((_r2) * (_t23) - (_r0 * _t21)) + ((_r6) * (_t22) - (_r4)));
        _dst.rX = ((otherRX) * (_r6) - (otherRW * _r0)) + ((otherRY) * (_r1) - (otherRZ * _r2));
    }

    /** Private store group 1 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s1ed6927_c1_fma(DoubleRigidImpl _dst, float otherRY, float _r6, float otherRZ, float _r0, float otherRX, float _r1, float otherRW, float _r2) {
        _dst.rY = java.lang.Math.fma(otherRY, _r6, otherRZ * _r0) + java.lang.Math.fma(-otherRX, _r1, -(otherRW * _r2));
        _dst.rZ = java.lang.Math.fma(otherRX, _r2, -(otherRW * _r1)) + java.lang.Math.fma(otherRZ, _r6, -(otherRY * _r0));
        _dst.rW = java.lang.Math.fma(otherRX, _r0, otherRW * _r6) - java.lang.Math.fma(-otherRZ, _r1, -(otherRY * _r2));
    }

    /** Private store group 1 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s1ed6927_c1_mulAdd(DoubleRigidImpl _dst, float otherRY, float _r6, float otherRZ, float _r0, float otherRX, float _r1, float otherRW, float _r2) {
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
        if (Math.useFma()) return difference_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
        return difference_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, dest);
    }

    /** {@code difference} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid difference_fma(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rZ;
        float _r2 = this.rY;
        float _r3 = this.tX;
        float _r4 = this.tZ;
        float _r5 = this.tY;
        float _r6 = this.rW;
        difference_s1ed6927_c0_fma(d, _r1, 2.0f * java.lang.Math.fma(otherTZ, _r0, -(otherTX * _r1)), otherTX, _r6, 2.0f * java.lang.Math.fma(otherTY, _r1, -(otherTZ * _r2)), _r2, 2.0f * java.lang.Math.fma(otherTX, _r2, -(otherTY * _r0)), 2.0f * java.lang.Math.fma(_r3, _r1, -(_r4 * _r0)), 2.0f * java.lang.Math.fma(_r5, _r0, -(_r3 * _r2)), 2.0f * java.lang.Math.fma(_r4, _r2, -(_r5 * _r1)), _r3, _r0, otherTY, _r5, otherTZ, _r4, otherRX, otherRW, otherRY, otherRZ);
        difference_s1ed6927_c1_fma(d, otherRY, _r6, otherRZ, _r0, otherRX, _r1, otherRW, _r2);
        return d;
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid difference_mulAdd(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        float _r0 = this.rX;
        float _r1 = this.rZ;
        float _r2 = this.rY;
        float _r3 = this.tX;
        float _r4 = this.tZ;
        float _r5 = this.tY;
        float _r6 = this.rW;
        difference_s1ed6927_c0_mulAdd(d, _r1, 2.0f * ((otherTZ) * (_r0) - (otherTX * _r1)), otherTX, _r6, 2.0f * ((otherTY) * (_r1) - (otherTZ * _r2)), _r2, 2.0f * ((otherTX) * (_r2) - (otherTY * _r0)), 2.0f * ((_r3) * (_r1) - (_r4 * _r0)), 2.0f * ((_r5) * (_r0) - (_r3 * _r2)), 2.0f * ((_r4) * (_r2) - (_r5 * _r1)), _r3, _r0, otherTY, _r5, otherTZ, _r4, otherRX, otherRW, otherRY, otherRZ);
        difference_s1ed6927_c1_mulAdd(d, otherRY, _r6, otherRZ, _r0, otherRX, _r1, otherRW, _r2);
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
    public FloatRigid invert(@Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _t0 = -this.rY;
        float _t1 = -this.rZ;
        float _t2 = -this.rX;
        float _t12 = 2.0f * Math.fma(this.tX, this.rZ, -(this.tZ * this.rX));
        float _t13 = 2.0f * Math.fma(this.tY, this.rX, -(this.tX * this.rY));
        float _t14 = 2.0f * Math.fma(this.tZ, this.rY, -(this.tY * this.rZ));
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
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        float _t0 = -this.rY;
        float _t1 = -this.rZ;
        float _t2 = -this.rX;
        float _t12 = 2.0f * Math.fma(this.tX, this.rZ, -(this.tZ * this.rX));
        float _t13 = 2.0f * Math.fma(this.tY, this.rX, -(this.tX * this.rY));
        float _t14 = 2.0f * Math.fma(this.tZ, this.rY, -(this.tY * this.rZ));
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
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatRigid normalize(@Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _t3 = Math.fma(this.rW, this.rW, Math.fma(this.rZ, this.rZ, Math.fma(this.rX, this.rX, this.rY * this.rY)));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        if (_t3 != 0.0f) {
            d.rX = this.rX * _t4;
            d.rY = this.rY * _t4;
            d.rZ = this.rZ * _t4;
            d.rW = this.rW * _t4;
        } else {
            d.rX = 0.0f;
            d.rY = 0.0f;
            d.rZ = 0.0f;
            d.rW = 0.0f;
        }
        return d;
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
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        float _t3 = Math.fma(this.rW, this.rW, Math.fma(this.rZ, this.rZ, Math.fma(this.rX, this.rX, this.rY * this.rY)));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        if (_t3 != 0.0f) {
            d.rX = this.rX * _t4;
            d.rY = this.rY * _t4;
            d.rZ = this.rZ * _t4;
            d.rW = this.rW * _t4;
        } else {
            d.rX = 0.0f;
            d.rY = 0.0f;
            d.rZ = 0.0f;
            d.rW = 0.0f;
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXYZ(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t1 = this.rY * this.rZ;
        float _t3 = this.rZ * this.rZ;
        float _t8 = 2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        float _t9 = 2.0f * Math.fma(this.rX, this.rW, -_t1);
        float _t10 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-7f) {
            d.x = Math.atan2(2.0f * Math.fma(this.rX, this.rW, _t1), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t3), 1.0f));
            d.z = 0.0f;
        } else {
            d.x = Math.atan2(_t9, _t10);
            d.z = Math.atan2(2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t3), 1.0f));
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12));
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
        Double3Impl d = (Double3Impl) dest;
        float _t1 = this.rY * this.rZ;
        float _t3 = this.rZ * this.rZ;
        float _t8 = 2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        float _t9 = 2.0f * Math.fma(this.rX, this.rW, -_t1);
        float _t10 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-7f) {
            d.x = Math.atan2(2.0f * Math.fma(this.rX, this.rW, _t1), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t3), 1.0f));
            d.z = 0.0f;
        } else {
            d.x = Math.atan2(_t9, _t10);
            d.z = Math.atan2(2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t3), 1.0f));
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXZY(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t1 = this.rY * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rW, _t1);
        float _t8 = 2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            d.x = Math.atan2(2.0f * Math.fma(this.rX, this.rW, -_t1), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f));
            d.y = 0.0f;
        } else {
            d.x = Math.atan2(_t7, _t9);
            d.y = Math.atan2(2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f));
        }
        d.z = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t1 = this.rY * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rW, _t1);
        float _t8 = 2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            d.x = Math.atan2(2.0f * Math.fma(this.rX, this.rW, -_t1), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f));
            d.y = 0.0f;
        } else {
            d.x = Math.atan2(_t7, _t9);
            d.y = Math.atan2(2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f));
        }
        d.z = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYXZ(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t3 = this.rZ * this.rZ;
        float _t8 = 2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        float _t9 = 2.0f * Math.fma(this.rX, this.rW, -(this.rY * this.rZ));
        float _t10 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        d.x = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12));
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            d.y = Math.atan2(2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t3), 1.0f));
            d.z = 0.0f;
        } else {
            d.y = Math.atan2(_t8, _t10);
            d.z = Math.atan2(2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t3), 1.0f));
        }
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
        Double3Impl d = (Double3Impl) dest;
        float _t3 = this.rZ * this.rZ;
        float _t8 = 2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        float _t9 = 2.0f * Math.fma(this.rX, this.rW, -(this.rY * this.rZ));
        float _t10 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        d.x = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12));
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            d.y = Math.atan2(2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t3), 1.0f));
            d.z = 0.0f;
        } else {
            d.y = Math.atan2(_t8, _t10);
            d.z = Math.atan2(2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t3), 1.0f));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYZX(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        float _t8 = 2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            d.x = 0.0f;
            d.y = Math.atan2(2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f));
        } else {
            d.x = Math.atan2(2.0f * Math.fma(this.rX, this.rW, -(this.rY * this.rZ)), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f));
            d.y = Math.atan2(_t8, _t9);
        }
        d.z = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
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
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        float _t8 = 2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            d.x = 0.0f;
            d.y = Math.atan2(2.0f * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f));
        } else {
            d.x = Math.atan2(2.0f * Math.fma(this.rX, this.rW, -(this.rY * this.rZ)), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f));
            d.y = Math.atan2(_t8, _t9);
        }
        d.z = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZXY(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t1 = this.rZ * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        float _t8 = 2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t1), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        d.x = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            d.y = 0.0f;
            d.z = Math.atan2(2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t1), 1.0f));
        } else {
            d.y = Math.atan2(2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f));
            d.z = Math.atan2(_t8, _t9);
        }
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
        Double3Impl d = (Double3Impl) dest;
        float _t1 = this.rZ * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        float _t8 = 2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t1), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        d.x = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            d.y = 0.0f;
            d.z = Math.atan2(2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t1), 1.0f));
        } else {
            d.y = Math.atan2(2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this rigid transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZYX(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        float _t8 = 2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            d.x = 0.0f;
            d.z = Math.atan2(2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f));
        } else {
            d.x = Math.atan2(2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f));
            d.z = Math.atan2(_t7, _t9);
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.rZ * this.rZ;
        float _t7 = 2.0f * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        float _t8 = 2.0f * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        float _t9 = Math.fma(-2.0f, Math.fma(this.rY, this.rY, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            d.x = 0.0f;
            d.z = Math.atan2(2.0f * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0f, Math.fma(this.rX, this.rX, _t0), 1.0f));
        } else {
            d.x = Math.atan2(2.0f * Math.fma(this.rX, this.rW, this.rY * this.rZ), Math.fma(-2.0f, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f));
            d.z = Math.atan2(_t7, _t9);
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
    public FloatQuat getRotation(@Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        d.x = this.rX;
        d.y = this.rY;
        d.z = this.rZ;
        d.w = this.rW;
        return d;
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
    public Float3 getTranslation(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        d.x = this.tX;
        d.y = this.tY;
        d.z = this.tZ;
        return d;
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
    public @Mutated FloatRigid makeRotationAxis(float angle, Float3R axis) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
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
    @Mutated public FloatRigid makeRotationAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
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
    @Mutated public FloatRigid makeRotationX(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
        this.rX = _t1;
        this.rY = 0.0f;
        this.rZ = 0.0f;
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
    @Mutated public FloatRigid makeRotationXYZ(float angleX, float angleY, float angleZ) {
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
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
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
    @Mutated public FloatRigid makeRotationXZY(float angleX, float angleZ, float angleY) {
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
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
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
    @Mutated public FloatRigid makeRotationY(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
        this.rX = 0.0f;
        this.rY = _t1;
        this.rZ = 0.0f;
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
    @Mutated public FloatRigid makeRotationYXZ(float angleY, float angleX, float angleZ) {
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
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
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
    @Mutated public FloatRigid makeRotationYZX(float angleY, float angleZ, float angleX) {
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
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
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
    @Mutated public FloatRigid makeRotationZ(float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
        this.rX = 0.0f;
        this.rY = 0.0f;
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
    @Mutated public FloatRigid makeRotationZXY(float angleZ, float angleX, float angleY) {
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
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
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
    @Mutated public FloatRigid makeRotationZYX(float angleZ, float angleY, float angleX) {
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
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
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
    public FloatRigid rotate(FloatQuatR rotation, @Mutated FloatRigid dest) {
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.rZ;
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
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = Math.fma(rotationX, this.rW, rotationW * this.rX) + Math.fma(rotationZ, this.rY, -(rotationY * this.rZ));
        d.rY = Math.fma(rotationX, this.rZ, rotationW * this.rY) + Math.fma(rotationY, this.rW, -(rotationZ * this.rX));
        d.rZ = Math.fma(rotationY, this.rX, rotationZ * this.rW) + Math.fma(rotationW, this.rZ, -(rotationX * this.rY));
        d.rW = Math.fma(rotationW, this.rW, -(rotationX * this.rX)) - Math.fma(rotationY, this.rY, rotationZ * this.rZ);
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
    public FloatRigid rotate(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.rZ;
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
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = Math.fma(rotationX, this.rW, rotationW * this.rX) + Math.fma(rotationZ, this.rY, -(rotationY * this.rZ));
        d.rY = Math.fma(rotationX, this.rZ, rotationW * this.rY) + Math.fma(rotationY, this.rW, -(rotationZ * this.rX));
        d.rZ = Math.fma(rotationY, this.rX, rotationZ * this.rW) + Math.fma(rotationW, this.rZ, -(rotationX * this.rY));
        d.rW = Math.fma(rotationW, this.rW, -(rotationX * this.rX)) - Math.fma(rotationY, this.rY, rotationZ * this.rZ);
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
    public FloatRigid rotateAxis(float angle, Float3R axis, @Mutated FloatRigid dest) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        if (Math.useFma()) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
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
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
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
    private void rotateAxis_s592810d6_c0_fma(FloatRigidImpl _dst, float _r0, float _r1, float _r2, float _r3, float _t5, float _r4, float _t2, float _r5, float _t3, float _r6, float _t4) {
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
    private void rotateAxis_s592810d6_c0_mulAdd(FloatRigidImpl _dst, float _r0, float _r1, float _r2, float _r3, float _t5, float _r4, float _t2, float _r5, float _t3, float _r6, float _t4) {
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
    public FloatRigid rotateAxis(float angle, float axisX, float axisY, float axisZ, @Mutated FloatRigid dest) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        if (Math.useFma()) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid rotateAxis_fma(float angle, float axisX, float axisY, float axisZ, @Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        rotateAxis_s592810d6_c0_fma(d, _r0, _r1, _r2, _r3, _t5, _r4, _t2, _r5, _t3, _r6, _t4);
        preMul_s7a4d9c14_c1_fma(d, _r5, _t5, _r6, _t2, _r4, _t4, _r3, _t3);
        return d;
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatRigid rotateAxis_mulAdd(float angle, float axisX, float axisY, float axisZ, @Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        rotateAxis_s592810d6_c0_mulAdd(d, _r0, _r1, _r2, _r3, _t5, _r4, _t2, _r5, _t3, _r6, _t4);
        preMul_s7a4d9c14_c1_mulAdd(d, _r5, _t5, _r6, _t2, _r4, _t4, _r3, _t3);
        return d;
    }

    /**
     * Private store group 0 of {@code rotateAxis}: computes and stores it. Shared by the identical
     * private paths of {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ},
     * {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private void rotateAxis_s7e618ca5_c0_fma(DoubleRigidImpl _dst, float _r0, float _r1, float _r2, float _r3, float _t5, float _r4, float _t2, float _r5, float _t3, float _r6, float _t4) {
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
    private void rotateAxis_s7e618ca5_c0_mulAdd(DoubleRigidImpl _dst, float _r0, float _r1, float _r2, float _r3, float _t5, float _r4, float _t2, float _r5, float _t3, float _r6, float _t4) {
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
        if (Math.useFma()) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid rotateAxis_fma(float angle, float axisX, float axisY, float axisZ, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        rotateAxis_s7e618ca5_c0_fma(d, _r0, _r1, _r2, _r3, _t5, _r4, _t2, _r5, _t3, _r6, _t4);
        preMul_s1ed6927_c1_fma(d, _r5, _t5, _r6, _t2, _r4, _t4, _r3, _t3);
        return d;
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleRigid rotateAxis_mulAdd(float angle, float axisX, float axisY, float axisZ, @Mutated DoubleRigid dest) {
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        rotateAxis_s7e618ca5_c0_mulAdd(d, _r0, _r1, _r2, _r3, _t5, _r4, _t2, _r5, _t3, _r6, _t4);
        preMul_s1ed6927_c1_mulAdd(d, _r5, _t5, _r6, _t2, _r4, _t4, _r3, _t3);
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
    public FloatRigid rotateX(float angle, @Mutated FloatRigid dest) {
        if (Math.useFma()) {
            FloatRigidImpl d = (FloatRigidImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.rX;
            float _rd1 = this.rY;
            d.tX = this.tX;
            d.tY = this.tY;
            d.tZ = this.tZ;
            d.rX = java.lang.Math.fma(_rd0, _t2, this.rW * _t1);
            d.rY = java.lang.Math.fma(_rd1, _t2, this.rZ * _t1);
            d.rZ = java.lang.Math.fma(this.rZ, _t2, -(_rd1 * _t1));
            d.rW = java.lang.Math.fma(this.rW, _t2, -(_rd0 * _t1));
            return d;
        } else {
            FloatRigidImpl d = (FloatRigidImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.rX;
            float _rd1 = this.rY;
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
        if (Math.useFma()) {
            DoubleRigidImpl d = (DoubleRigidImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.tX = this.tX;
            d.tY = this.tY;
            d.tZ = this.tZ;
            d.rX = java.lang.Math.fma(this.rX, _t2, this.rW * _t1);
            d.rY = java.lang.Math.fma(this.rY, _t2, this.rZ * _t1);
            d.rZ = java.lang.Math.fma(this.rZ, _t2, -(this.rY * _t1));
            d.rW = java.lang.Math.fma(this.rW, _t2, -(this.rX * _t1));
            return d;
        } else {
            DoubleRigidImpl d = (DoubleRigidImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.tX = this.tX;
            d.tY = this.tY;
            d.tZ = this.tZ;
            d.rX = ((this.rX) * (_t2) + (this.rW * _t1));
            d.rY = ((this.rY) * (_t2) + (this.rZ * _t1));
            d.rZ = ((this.rZ) * (_t2) - (this.rY * _t1));
            d.rW = ((this.rW) * (_t2) - (this.rX * _t1));
            return d;
        }
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void rotateXYZ_s492dd58b_tail_fma(FloatRigidImpl _dst, float _t11, float _t8, float _t10, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _t19, float _r5, float _t20, float _r6) {
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        rotateAxis_s592810d6_c0_fma(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t20, _r6, _t22);
        preMul_s7a4d9c14_c1_fma(_dst, _r5, _t21, _r6, _t19, _r4, _t22, _r3, _t20);
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void rotateXYZ_s492dd58b_tail_mulAdd(FloatRigidImpl _dst, float _t11, float _t8, float _t10, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _t19, float _r5, float _t20, float _r6) {
        float _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        rotateAxis_s592810d6_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t20, _r6, _t22);
        preMul_s7a4d9c14_c1_mulAdd(_dst, _r5, _t21, _r6, _t19, _r4, _t22, _r3, _t20);
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
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
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
        if (Math.useFma()) rotateXYZ_s492dd58b_tail_fma(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r4, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r5, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r6); else rotateXYZ_s492dd58b_tail_mulAdd(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, ((_t14) * (_t8) - (_t9 * _t5)), _r4, ((_t10) * (_t8) + (_t11 * _t5)), _r5, ((_t9) * (_t8) + (_t14 * _t5)), _r6);
        return d;
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void rotateXYZ_sf145e90_tail_fma(DoubleRigidImpl _dst, float _t11, float _t8, float _t10, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _t19, float _r5, float _t20, float _r6) {
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        rotateAxis_s7e618ca5_c0_fma(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t20, _r6, _t22);
        preMul_s1ed6927_c1_fma(_dst, _r5, _t21, _r6, _t19, _r4, _t22, _r3, _t20);
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void rotateXYZ_sf145e90_tail_mulAdd(DoubleRigidImpl _dst, float _t11, float _t8, float _t10, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _t19, float _r5, float _t20, float _r6) {
        float _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        rotateAxis_s7e618ca5_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t20, _r6, _t22);
        preMul_s1ed6927_c1_mulAdd(_dst, _r5, _t21, _r6, _t19, _r4, _t22, _r3, _t20);
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
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
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
        if (Math.useFma()) rotateXYZ_sf145e90_tail_fma(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r4, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r5, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r6); else rotateXYZ_sf145e90_tail_mulAdd(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, ((_t14) * (_t8) - (_t9 * _t5)), _r4, ((_t10) * (_t8) + (_t11 * _t5)), _r5, ((_t9) * (_t8) + (_t14 * _t5)), _r6);
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
    public FloatRigid rotateXZY(float angleX, float angleZ, float angleY, @Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
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
        if (Math.useFma()) rotateXYZ_s492dd58b_tail_fma(d, _t12, _t5, _t9, _t8, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r4, java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), _r5, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r6); else rotateXYZ_s492dd58b_tail_mulAdd(d, _t12, _t5, _t9, _t8, _r0, _r1, _r2, _r3, ((_t9) * (_t5) + (_t12 * _t8)), _r4, ((_t10) * (_t8) - (_t11 * _t5)), _r5, ((_t10) * (_t5) + (_t11 * _t8)), _r6);
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
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
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
        if (Math.useFma()) rotateXYZ_sf145e90_tail_fma(d, _t12, _t5, _t9, _t8, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r4, java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), _r5, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r6); else rotateXYZ_sf145e90_tail_mulAdd(d, _t12, _t5, _t9, _t8, _r0, _r1, _r2, _r3, ((_t9) * (_t5) + (_t12 * _t8)), _r4, ((_t10) * (_t8) - (_t11 * _t5)), _r5, ((_t10) * (_t5) + (_t11 * _t8)), _r6);
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
    public FloatRigid rotateY(float angle, @Mutated FloatRigid dest) {
        if (Math.useFma()) {
            FloatRigidImpl d = (FloatRigidImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.rX;
            float _rd1 = this.rY;
            d.tX = this.tX;
            d.tY = this.tY;
            d.tZ = this.tZ;
            d.rX = java.lang.Math.fma(_rd0, _t2, -(this.rZ * _t1));
            d.rY = java.lang.Math.fma(_rd1, _t2, this.rW * _t1);
            d.rZ = java.lang.Math.fma(_rd0, _t1, this.rZ * _t2);
            d.rW = java.lang.Math.fma(this.rW, _t2, -(_rd1 * _t1));
            return d;
        } else {
            FloatRigidImpl d = (FloatRigidImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.rX;
            float _rd1 = this.rY;
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
        if (Math.useFma()) {
            DoubleRigidImpl d = (DoubleRigidImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.tX = this.tX;
            d.tY = this.tY;
            d.tZ = this.tZ;
            d.rX = java.lang.Math.fma(this.rX, _t2, -(this.rZ * _t1));
            d.rY = java.lang.Math.fma(this.rY, _t2, this.rW * _t1);
            d.rZ = java.lang.Math.fma(this.rX, _t1, this.rZ * _t2);
            d.rW = java.lang.Math.fma(this.rW, _t2, -(this.rY * _t1));
            return d;
        } else {
            DoubleRigidImpl d = (DoubleRigidImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.tX = this.tX;
            d.tY = this.tY;
            d.tZ = this.tZ;
            d.rX = ((this.rX) * (_t2) - (this.rZ * _t1));
            d.rY = ((this.rY) * (_t2) + (this.rW * _t1));
            d.rZ = ((this.rX) * (_t1) + (this.rZ * _t2));
            d.rW = ((this.rW) * (_t2) - (this.rY * _t1));
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
    public FloatRigid rotateYXZ(float angleY, float angleX, float angleZ, @Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
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
        if (Math.useFma()) rotateXYZ_s492dd58b_tail_fma(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r4, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r5, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r6); else rotateXYZ_s492dd58b_tail_mulAdd(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, ((_t9) * (_t5) + (_t12 * _t8)), _r4, ((_t10) * (_t8) + (_t11 * _t5)), _r5, ((_t12) * (_t5) - (_t9 * _t8)), _r6);
        return d;
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
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
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
        if (Math.useFma()) rotateXYZ_sf145e90_tail_fma(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r4, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r5, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r6); else rotateXYZ_sf145e90_tail_mulAdd(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, ((_t9) * (_t5) + (_t12 * _t8)), _r4, ((_t10) * (_t8) + (_t11 * _t5)), _r5, ((_t12) * (_t5) - (_t9 * _t8)), _r6);
        return d;
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void rotateYZX_sc80344b_tail_fma(FloatRigidImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _t19, float _r5, float _r6, float _t20) {
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        rotateAxis_s592810d6_c0_fma(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t22, _r6, _t20);
        preMul_s7a4d9c14_c1_fma(_dst, _r5, _t21, _r6, _t19, _r4, _t20, _r3, _t22);
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void rotateYZX_sc80344b_tail_mulAdd(FloatRigidImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _t19, float _r5, float _r6, float _t20) {
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        rotateAxis_s592810d6_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t22, _r6, _t20);
        preMul_s7a4d9c14_c1_mulAdd(_dst, _r5, _t21, _r6, _t19, _r4, _t20, _r3, _t22);
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
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
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
        if (Math.useFma()) rotateYZX_sc80344b_tail_fma(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r4, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r5, _r6, java.lang.Math.fma(_t11, _t8, _t10 * _t5)); else rotateYZX_sc80344b_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, ((_t14) * (_t8) - (_t9 * _t5)), _r4, ((_t9) * (_t8) + (_t14 * _t5)), _r5, _r6, ((_t11) * (_t8) + (_t10 * _t5)));
        return d;
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void rotateYZX_s360dd7d0_tail_fma(DoubleRigidImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _t19, float _r5, float _r6, float _t20) {
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        rotateAxis_s7e618ca5_c0_fma(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t22, _r6, _t20);
        preMul_s1ed6927_c1_fma(_dst, _r5, _t21, _r6, _t19, _r4, _t20, _r3, _t22);
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void rotateYZX_s360dd7d0_tail_mulAdd(DoubleRigidImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _t19, float _r5, float _r6, float _t20) {
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        rotateAxis_s7e618ca5_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t22, _r6, _t20);
        preMul_s1ed6927_c1_mulAdd(_dst, _r5, _t21, _r6, _t19, _r4, _t20, _r3, _t22);
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
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
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
        if (Math.useFma()) rotateYZX_s360dd7d0_tail_fma(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r4, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r5, _r6, java.lang.Math.fma(_t11, _t8, _t10 * _t5)); else rotateYZX_s360dd7d0_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, ((_t14) * (_t8) - (_t9 * _t5)), _r4, ((_t9) * (_t8) + (_t14 * _t5)), _r5, _r6, ((_t11) * (_t8) + (_t10 * _t5)));
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
    public FloatRigid rotateZ(float angle, @Mutated FloatRigid dest) {
        if (Math.useFma()) {
            FloatRigidImpl d = (FloatRigidImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.rX;
            float _rd1 = this.rZ;
            d.tX = this.tX;
            d.tY = this.tY;
            d.tZ = this.tZ;
            d.rX = java.lang.Math.fma(_rd0, _t2, this.rY * _t1);
            d.rY = java.lang.Math.fma(this.rY, _t2, -(_rd0 * _t1));
            d.rZ = java.lang.Math.fma(_rd1, _t2, this.rW * _t1);
            d.rW = java.lang.Math.fma(this.rW, _t2, -(_rd1 * _t1));
            return d;
        } else {
            FloatRigidImpl d = (FloatRigidImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.rX;
            float _rd1 = this.rZ;
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
        if (Math.useFma()) {
            DoubleRigidImpl d = (DoubleRigidImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.tX = this.tX;
            d.tY = this.tY;
            d.tZ = this.tZ;
            d.rX = java.lang.Math.fma(this.rX, _t2, this.rY * _t1);
            d.rY = java.lang.Math.fma(this.rY, _t2, -(this.rX * _t1));
            d.rZ = java.lang.Math.fma(this.rZ, _t2, this.rW * _t1);
            d.rW = java.lang.Math.fma(this.rW, _t2, -(this.rZ * _t1));
            return d;
        } else {
            DoubleRigidImpl d = (DoubleRigidImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.tX = this.tX;
            d.tY = this.tY;
            d.tZ = this.tZ;
            d.rX = ((this.rX) * (_t2) + (this.rY * _t1));
            d.rY = ((this.rY) * (_t2) - (this.rX * _t1));
            d.rZ = ((this.rZ) * (_t2) + (this.rW * _t1));
            d.rW = ((this.rW) * (_t2) - (this.rZ * _t1));
            return d;
        }
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s55e5444b_tail_fma(FloatRigidImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _r5, float _t19, float _r6, float _t20) {
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        rotateAxis_s592810d6_c0_fma(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t22, _r5, _t19, _r6, _t20);
        preMul_s7a4d9c14_c1_fma(_dst, _r5, _t21, _r6, _t22, _r4, _t20, _r3, _t19);
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s55e5444b_tail_mulAdd(FloatRigidImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _r5, float _t19, float _r6, float _t20) {
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        rotateAxis_s592810d6_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t22, _r5, _t19, _r6, _t20);
        preMul_s7a4d9c14_c1_mulAdd(_dst, _r5, _t21, _r6, _t22, _r4, _t20, _r3, _t19);
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
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
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
        if (Math.useFma()) rotateZXY_s55e5444b_tail_fma(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r4, _r5, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r6, java.lang.Math.fma(_t9, _t8, _t14 * _t5)); else rotateZXY_s55e5444b_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, ((_t14) * (_t8) - (_t9 * _t5)), _r4, _r5, ((_t10) * (_t5) + (_t11 * _t8)), _r6, ((_t9) * (_t8) + (_t14 * _t5)));
        return d;
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s194ac7d0_tail_fma(DoubleRigidImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _r5, float _t19, float _r6, float _t20) {
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        rotateAxis_s7e618ca5_c0_fma(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t22, _r5, _t19, _r6, _t20);
        preMul_s1ed6927_c1_fma(_dst, _r5, _t21, _r6, _t22, _r4, _t20, _r3, _t19);
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s194ac7d0_tail_mulAdd(DoubleRigidImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _r5, float _t19, float _r6, float _t20) {
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        rotateAxis_s7e618ca5_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t22, _r5, _t19, _r6, _t20);
        preMul_s1ed6927_c1_mulAdd(_dst, _r5, _t21, _r6, _t22, _r4, _t20, _r3, _t19);
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
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
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
        if (Math.useFma()) rotateZXY_s194ac7d0_tail_fma(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r4, _r5, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r6, java.lang.Math.fma(_t9, _t8, _t14 * _t5)); else rotateZXY_s194ac7d0_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, ((_t14) * (_t8) - (_t9 * _t5)), _r4, _r5, ((_t10) * (_t5) + (_t11 * _t8)), _r6, ((_t9) * (_t8) + (_t14 * _t5)));
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
    public FloatRigid rotateZYX(float angleZ, float angleY, float angleX, @Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
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
        if (Math.useFma()) rotateYZX_sc80344b_tail_fma(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r4, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r5, _r6, java.lang.Math.fma(_t11, _t8, _t10 * _t5)); else rotateYZX_sc80344b_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, ((_t9) * (_t5) + (_t12 * _t8)), _r4, ((_t12) * (_t5) - (_t9 * _t8)), _r5, _r6, ((_t11) * (_t8) + (_t10 * _t5)));
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
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
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
        if (Math.useFma()) rotateYZX_s360dd7d0_tail_fma(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r4, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r5, _r6, java.lang.Math.fma(_t11, _t8, _t10 * _t5)); else rotateYZX_s360dd7d0_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, ((_t9) * (_t5) + (_t12 * _t8)), _r4, ((_t12) * (_t5) - (_t9 * _t8)), _r5, _r6, ((_t11) * (_t8) + (_t10 * _t5)));
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
    public FloatRigid translate(Float3R translation, @Mutated FloatRigid dest) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, translationY, -(this.rY * translationX));
        float _t10 = 2.0f * Math.fma(this.rZ, translationX, -(this.rX * translationZ));
        float _t11 = 2.0f * Math.fma(this.rY, translationZ, -(this.rZ * translationY));
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
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, translationY, -(this.rY * translationX));
        float _t10 = 2.0f * Math.fma(this.rZ, translationX, -(this.rX * translationZ));
        float _t11 = 2.0f * Math.fma(this.rY, translationZ, -(this.rZ * translationY));
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
    public FloatRigid translate(float translationX, float translationY, float translationZ, @Mutated FloatRigid dest) {
        FloatRigidImpl d = (FloatRigidImpl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, translationY, -(this.rY * translationX));
        float _t10 = 2.0f * Math.fma(this.rZ, translationX, -(this.rX * translationZ));
        float _t11 = 2.0f * Math.fma(this.rY, translationZ, -(this.rZ * translationY));
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
        DoubleRigidImpl d = (DoubleRigidImpl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, translationY, -(this.rY * translationX));
        float _t10 = 2.0f * Math.fma(this.rZ, translationX, -(this.rX * translationZ));
        float _t11 = 2.0f * Math.fma(this.rY, translationZ, -(this.rZ * translationY));
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
    public Float3 transform(Float3R v, @Mutated Float3 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + vZ)));
        return d;
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
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
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
    public Float3 transform(float vX, float vY, float vZ, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + vZ)));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
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
    public Float3 transformDirection(Float3R v, @Mutated Float3 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ)));
        return d;
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
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
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
    public Float3 transformDirection(float vX, float vY, float vZ, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ)));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
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
    public Float3 transformDirectionInverse(Float3R v, @Mutated Float3 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * Math.fma(this.rZ, vY, -(this.rY * vZ));
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
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * Math.fma(this.rZ, vY, -(this.rY * vZ));
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
    public Float3 transformDirectionInverse(float vX, float vY, float vZ, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * Math.fma(this.rZ, vY, -(this.rY * vZ));
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
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * Math.fma(this.rZ, vY, -(this.rY * vZ));
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
    public Float3 transformInverse(Float3R p, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = p.z() - this.tZ;
        float _t1 = p.x() - this.tX;
        float _t2 = p.y() - this.tY;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        d.x = Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1)));
        d.y = Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2)));
        d.z = Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0)));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t0 = p.z() - this.tZ;
        float _t1 = p.x() - this.tX;
        float _t2 = p.y() - this.tY;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rY * _t0));
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
    public Float3 transformInverse(float pX, float pY, float pZ, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = pZ - this.tZ;
        float _t1 = pX - this.tX;
        float _t2 = pY - this.tY;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        d.x = Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1)));
        d.y = Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2)));
        d.z = Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0)));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t0 = pZ - this.tZ;
        float _t1 = pX - this.tX;
        float _t2 = pY - this.tY;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rY * _t0));
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
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
        d.x = Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, this.tX + vX)));
        d.y = Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, this.tY + vY)));
        d.z = Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, this.tZ + vZ)));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vY, -(this.rY * vX));
        float _t10 = 2.0f * Math.fma(this.rZ, vX, -(this.rX * vZ));
        float _t11 = 2.0f * Math.fma(this.rY, vZ, -(this.rZ * vY));
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
        Float3Impl d = (Float3Impl) dest;
        float _t0 = pZ - this.tZ;
        float _t1 = pX - this.tX;
        float _t2 = pY - this.tY;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        d.x = Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1)));
        d.y = Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2)));
        d.z = Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0)));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t0 = pZ - this.tZ;
        float _t1 = pX - this.tX;
        float _t2 = pY - this.tY;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        d.x = Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1)));
        d.y = Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2)));
        d.z = Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0)));
        return d;
    }

    public float tX() { return this.tX; }
    public float tY() { return this.tY; }
    public float tZ() { return this.tZ; }
    public float rX() { return this.rX; }
    public float rY() { return this.rY; }
    public float rZ() { return this.rZ; }
    public float rW() { return this.rW; }

    @Override public String toString() {
        return "FloatRigid(" + tX() + ", " + tY() + ", " + tZ() + ", " + rX() + ", " + rY() + ", " + rZ() + ", " + rW() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatRigidImpl)) return false;
        FloatRigidImpl o = (FloatRigidImpl) obj;
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

    @Override public boolean isFinite() {
        return Float.isFinite(tX)
            && Float.isFinite(tY)
            && Float.isFinite(tZ)
            && Float.isFinite(rX)
            && Float.isFinite(rY)
            && Float.isFinite(rZ)
            && Float.isFinite(rW);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(tX)
            || Float.isNaN(tY)
            || Float.isNaN(tZ)
            || Float.isNaN(rX)
            || Float.isNaN(rY)
            || Float.isNaN(rZ)
            || Float.isNaN(rW);
    }

    @Override public boolean equalsEpsilon(FloatRigidR other, float epsilon) {
        return java.lang.Math.abs(tX - other.tX()) <= epsilon
            && java.lang.Math.abs(tY - other.tY()) <= epsilon
            && java.lang.Math.abs(tZ - other.tZ()) <= epsilon
            && java.lang.Math.abs(rX - other.rX()) <= epsilon
            && java.lang.Math.abs(rY - other.rY()) <= epsilon
            && java.lang.Math.abs(rZ - other.rZ()) <= epsilon
            && java.lang.Math.abs(rW - other.rW()) <= epsilon;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = this.tX;
        dest[offset + 1] = this.tY;
        dest[offset + 2] = this.tZ;
        dest[offset + 3] = this.rX;
        dest[offset + 4] = this.rY;
        dest[offset + 5] = this.rZ;
        dest[offset + 6] = this.rW;
        return dest;
    }
    public @Mutated FloatRigid load(float[] src, int offset) {
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
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 28) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 28);
        return buf;
    }
    public FloatRigid load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public FloatRigid loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public FloatRigid loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 28) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatRigid r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 28);
        return r;
    }
    public FloatRigid storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public FloatRigid loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public FloatRigid load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public FloatRigid load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
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
    public @Mutated FloatRigid load(double[] src, int offset) {
        this.tX = (float) src[offset];
        this.tY = (float) src[offset + 1];
        this.tZ = (float) src[offset + 2];
        this.rX = (float) src[offset + 3];
        this.rY = (float) src[offset + 4];
        this.rZ = (float) src[offset + 5];
        this.rW = (float) src[offset + 6];
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
    public ByteBuffer storeDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 56) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 56);
        return buf;
    }
    public FloatRigid loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }
    public FloatRigid loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public FloatRigid loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 56) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatRigid r = StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 56);
        return r;
    }
    public FloatRigid storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public FloatRigid loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }
    public MemorySegment storeDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }
    public MemorySegment storeDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }
    @Mutated public FloatRigid loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(this, 0L, src); }
    public FloatRigid loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(this, offset, src);
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
