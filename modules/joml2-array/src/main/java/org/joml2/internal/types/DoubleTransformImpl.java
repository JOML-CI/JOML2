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
 * Generated implementation of {@link DoubleTransform} backed by a {@code double[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoubleTransformImpl implements DoubleTransform {

    public double[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoubleTransformSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleTransformSegOpsUnsafe()
                        : new DoubleTransformSegOpsMS();
        static final DoubleTransformBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleTransformBbOpsUnsafe()
                        : new DoubleTransformBbOpsApi();
        static final DoubleTransformRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleTransformRawOpsUnsafe()
                        : new DoubleTransformRawOpsApi();
    }

    public DoubleTransformImpl() {
        data = new double[10];
        data[6] = 1;
        data[7] = 1;
        data[8] = 1;
        data[9] = 1;
    }

    public DoubleTransformImpl(double tX, double tY, double tZ, double rX, double rY, double rZ, double rW, double sX, double sY, double sZ) {
        double[] dd = this.data = new double[10];
        dd[0] = tX;
        dd[1] = tY;
        dd[2] = tZ;
        dd[3] = rX;
        dd[4] = rY;
        dd[5] = rZ;
        dd[6] = rW;
        dd[7] = sX;
        dd[8] = sY;
        dd[9] = sZ;
    }

    public DoubleTransformImpl(DoubleTransformR src) {
        double[] dd = this.data = new double[10];
        dd[0] = src.tX();
        dd[1] = src.tY();
        dd[2] = src.tZ();
        dd[3] = src.rX();
        dd[4] = src.rY();
        dd[5] = src.rZ();
        dd[6] = src.rW();
        dd[7] = src.sX();
        dd[8] = src.sY();
        dd[9] = src.sZ();
    }


    /**
     * Set this transform to the rotation of {@code angle} radians about the axis {@code axis},
     * combined with a translation by {@code translation}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param axis the rotation axis
     * @param angle the angle in radians
     * @param translation the translation
     * @return this
     */
    public @Mutated DoubleTransform makeFromAxisAngle(Double3R axis, double angle, Double3R translation) {
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
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to the rotation of {@code angle} radians about the axis ({@code axisX},
     * {@code axisY}, {@code axisZ}), combined with a translation by ({@code translationX},
     * {@code translationY}, {@code translationZ}).
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
    @Mutated public DoubleTransform makeFromAxisAngle(double axisX, double axisY, double axisZ, double angle, double translationX, double translationY, double translationZ) {
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
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a rigid transformation that first rotates by {@code rotation} and then
     * translates by {@code translation} ({@code T * R}).
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param translation the translation
     * @param rotation the rotation
     * @return this
     */
    public @Mutated DoubleTransform makeTranslationRotation(Double3R translation, DoubleQuatR rotation) {
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
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a rigid transformation that first rotates by ({@code rotationX},
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
    @Mutated public DoubleTransform makeTranslationRotation(double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW) {
        double[] dd = this.data;
        dd[0] = translationX;
        dd[1] = translationY;
        dd[2] = translationZ;
        dd[3] = rotationX;
        dd[4] = rotationY;
        dd[5] = rotationZ;
        dd[6] = rotationW;
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a transformation composed of the given translation, rotation and scale,
     * applied in scale-rotation-translation order.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param translation the translation
     * @param rotation the rotation
     * @param scale the scale factors
     * @return this
     */
    public @Mutated DoubleTransform makeTranslationRotationScale(Double3R translation, DoubleQuatR rotation, Double3R scale) {
        double translationY = translation.y();
        double translationZ = translation.z();
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        double scaleX = scale.x();
        double scaleY = scale.y();
        double scaleZ = scale.z();
        double[] dd = this.data;
        dd[0] = translation.x();
        dd[1] = translationY;
        dd[2] = translationZ;
        dd[3] = rotationX;
        dd[4] = rotationY;
        dd[5] = rotationZ;
        dd[6] = rotationW;
        dd[7] = scaleX;
        dd[8] = scaleY;
        dd[9] = scaleZ;
        return this;
    }


    /**
     * Set this transform to a transformation composed of the given translation, rotation and scale,
     * applied in scale-rotation-translation order.
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
     * @param scaleX the {@code x} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param scaleY the {@code y} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param scaleZ the {@code z} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @return this
     */
    @Mutated public DoubleTransform makeTranslationRotationScale(double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        double[] dd = this.data;
        dd[0] = translationX;
        dd[1] = translationY;
        dd[2] = translationZ;
        dd[3] = rotationX;
        dd[4] = rotationY;
        dd[5] = rotationZ;
        dd[6] = rotationW;
        dd[7] = scaleX;
        dd[8] = scaleY;
        dd[9] = scaleZ;
        return this;
    }


    /**
     * Set this transform to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the transform to copy
     * @return this
     */
    public @Mutated DoubleTransform set(DoubleTransformR v) {
        double vTY = v.tY();
        double vTZ = v.tZ();
        double vRX = v.rX();
        double vRY = v.rY();
        double vRZ = v.rZ();
        double vRW = v.rW();
        double vSX = v.sX();
        double vSY = v.sY();
        double vSZ = v.sZ();
        double[] dd = this.data;
        dd[0] = v.tX();
        dd[1] = vTY;
        dd[2] = vTZ;
        dd[3] = vRX;
        dd[4] = vRY;
        dd[5] = vRZ;
        dd[6] = vRW;
        dd[7] = vSX;
        dd[8] = vSY;
        dd[9] = vSZ;
        return this;
    }


    /**
     * Set this transform to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vTX the {@code tX} component of the transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW, vSX, vSY, vSZ)}
     * @param vTY the {@code tY} component of the transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW, vSX, vSY, vSZ)}
     * @param vTZ the {@code tZ} component of the transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW, vSX, vSY, vSZ)}
     * @param vRX the {@code rX} component of the transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW, vSX, vSY, vSZ)}
     * @param vRY the {@code rY} component of the transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW, vSX, vSY, vSZ)}
     * @param vRZ the {@code rZ} component of the transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW, vSX, vSY, vSZ)}
     * @param vRW the {@code rW} component of the transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW, vSX, vSY, vSZ)}
     * @param vSX the {@code sX} component of the transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW, vSX, vSY, vSZ)}
     * @param vSY the {@code sY} component of the transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW, vSX, vSY, vSZ)}
     * @param vSZ the {@code sZ} component of the transform
     *        {@code (vTX, vTY, vTZ, vRX, vRY, vRZ, vRW, vSX, vSY, vSZ)}
     * @return this
     */
    @Mutated public DoubleTransform set(double vTX, double vTY, double vTZ, double vRX, double vRY, double vRZ, double vRW, double vSX, double vSY, double vSZ) {
        double[] dd = this.data;
        dd[0] = vTX;
        dd[1] = vTY;
        dd[2] = vTZ;
        dd[3] = vRX;
        dd[4] = vRY;
        dd[5] = vRZ;
        dd[6] = vRW;
        dd[7] = vSX;
        dd[8] = vSY;
        dd[9] = vSZ;
        return this;
    }


    /**
     * Set the rotation of this transform to {@code r} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code r} must have unit length.
     *
     * @param r the new rotation
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform setRotation(DoubleQuatR r, @Mutated DoubleTransform dest) {
        double rX = r.x();
        double rY = r.y();
        double rZ = r.z();
        double rW = r.w();
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = rX;
        dd[4] = rY;
        dd[5] = rZ;
        dd[6] = rW;
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Set the rotation of this transform to ({@code rX}, {@code rY}, {@code rZ}, {@code rW}) and
     * store the result in {@code dest}.
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
    public DoubleTransform setRotation(double rX, double rY, double rZ, double rW, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = rX;
        dd[4] = rY;
        dd[5] = rZ;
        dd[6] = rW;
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Set the scale of this transform to {@code s} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform setScale(Double3R s, @Mutated DoubleTransform dest) {
        double sX = s.x();
        double sY = s.y();
        double sZ = s.z();
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sX;
        dd[8] = sY;
        dd[9] = sZ;
        return dest;
    }


    /**
     * Set the scale of this transform to ({@code sX}, {@code sY}, {@code sZ}) and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sX the {@code x} component of the vector {@code (sX, sY, sZ)}
     * @param sY the {@code y} component of the vector {@code (sX, sY, sZ)}
     * @param sZ the {@code z} component of the vector {@code (sX, sY, sZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform setScale(double sX, double sY, double sZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sX;
        dd[8] = sY;
        dd[9] = sZ;
        return dest;
    }


    /**
     * Set the scale of this transform to {@code uniform} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param uniform the uniform scale factor
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform setScale(double uniform, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = uniform;
        dd[8] = uniform;
        dd[9] = uniform;
        return dest;
    }


    /**
     * Set the translation of this transform to {@code t} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the translation vector
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform setTranslation(Double3R t, @Mutated DoubleTransform dest) {
        double tY = t.y();
        double tZ = t.z();
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = t.x();
        dd[1] = tY;
        dd[2] = tZ;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Set the translation of this transform to ({@code tX}, {@code tY}, {@code tZ}) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param tX the {@code x} component of the vector {@code (tX, tY, tZ)}
     * @param tY the {@code y} component of the vector {@code (tX, tY, tZ)}
     * @param tZ the {@code z} component of the vector {@code (tX, tY, tZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform setTranslation(double tX, double tY, double tZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = tX;
        dd[1] = tY;
        dd[2] = tZ;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Set this transform to the rigid motion of the unit dual quaternion {@code dq} (translation
     * and rotation from {@code dq}, scale = 1).
     * <p>
     * Valid input: {@code dq} must be a unit dual quaternion.
     *
     * @param dq the dual quaternion to convert
     * @return this
     */
    public @Mutated DoubleTransform makeFromDualQuat(DoubleDualQuatR dq) {
        return makeFromDualQuat(dq.rX(), dq.rY(), dq.rZ(), dq.rW(), dq.dX(), dq.dY(), dq.dZ(), dq.dW());
    }


    /**
     * Set this transform to the rigid motion of the unit dual quaternion ({@code dqRX},
     * {@code dqRY}, {@code dqRZ}, {@code dqRW}, {@code dqDX}, {@code dqDY}, {@code dqDZ},
     * {@code dqDW}) (translation and rotation from {@code dq}, scale = 1).
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
    @Mutated public DoubleTransform makeFromDualQuat(double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        if (Math.useFma()) {
            double[] dd = this.data;
            dd[0] = 2.0 * (java.lang.Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + java.lang.Math.fma(dqRW, dqDX, -(dqRX * dqDW)));
            dd[1] = 2.0 * (java.lang.Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + java.lang.Math.fma(dqRW, dqDY, -(dqRY * dqDW)));
            dd[2] = 2.0 * (java.lang.Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + java.lang.Math.fma(dqRW, dqDZ, -(dqRZ * dqDW)));
            dd[3] = dqRX;
            dd[4] = dqRY;
            dd[5] = dqRZ;
            dd[6] = dqRW;
            dd[7] = 1.0;
            dd[8] = 1.0;
            dd[9] = 1.0;
            return this;
        } else {
            double[] dd = this.data;
            dd[0] = 2.0 * (((dqRY) * (dqDZ) - (dqRZ * dqDY)) + ((dqRW) * (dqDX) - (dqRX * dqDW)));
            dd[1] = 2.0 * (((dqRZ) * (dqDX) - (dqRX * dqDZ)) + ((dqRW) * (dqDY) - (dqRY * dqDW)));
            dd[2] = 2.0 * (((dqRX) * (dqDY) - (dqRY * dqDX)) + ((dqRW) * (dqDZ) - (dqRZ * dqDW)));
            dd[3] = dqRX;
            dd[4] = dqRY;
            dd[5] = dqRZ;
            dd[6] = dqRW;
            dd[7] = 1.0;
            dd[8] = 1.0;
            dd[9] = 1.0;
            return this;
        }
    }


    /**
     * Set this transform to the decomposition of the given matrix's linear {@code R * S} block,
     * with zero translation (scale is removed by normalizing the columns, but shear is not removed:
     * a sheared block yields a rotation quaternion that is not unit length).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public DoubleTransform makeFromMatrix(Double3x3R m) {
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform makeFromMatrix_fma(Double3x3R m) {
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
        double _t12 = java.lang.Math.fma(_ld2, _ld2, java.lang.Math.fma(_ld3, _ld3, _ld0 * _ld0));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t13 = java.lang.Math.fma(_ld1, _ld1, java.lang.Math.fma(_ld4, _ld4, _ld5 * _ld5));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t14 = java.lang.Math.fma(_ld6, _ld6, java.lang.Math.fma(_ld7, _ld7, _ld8 * _ld8));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t17 = 1.0 / java.lang.Math.sqrt(_t14);
        return makeFromMatrix_s81574d1e_6_fma(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _t12, _t13, _t14, _ld8 * _t17, _ld6 * _t17, _ld7 * _t17);
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform makeFromMatrix_mulAdd(Double3x3R m) {
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
        double _t12 = ((_ld2) * (_ld2) + (((_ld3) * (_ld3) + (_ld0 * _ld0))));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t13 = ((_ld1) * (_ld1) + (((_ld4) * (_ld4) + (_ld5 * _ld5))));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t14 = ((_ld6) * (_ld6) + (((_ld7) * (_ld7) + (_ld8 * _ld8))));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t17 = 1.0 / java.lang.Math.sqrt(_t14);
        return makeFromMatrix_s81574d1e_6_mulAdd(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _t12, _t13, _t14, _ld8 * _t17, _ld6 * _t17, _ld7 * _t17);
    }

    /**
     * Part 1 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 3 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private double makeFromMatrix_s81574d1e_1_fma(double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld6, double _ld7, double _ld8, double _t12, double _t13, double _t14) {
        double _t15 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t17 = 1.0 / (java.lang.Math.sqrt(_t14));
        double _t19 = _ld8 * _t17;
        double _t20 = _ld1 * _t16;
        double _t21 = _ld5 * _t16;
        double _t22 = _ld6 * _t17;
        double _t24 = _ld2 * _t15;
        double _t25 = _ld0 * _t15;
        return java.lang.Math.fma(-java.lang.Math.fma(_t19, _t20, -(_t21 * _t22)), _ld3 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t19, _t24, -(_t25 * _t22)), _ld4 * _t16, java.lang.Math.fma(_t25, _t20, -(_t21 * _t24)) * (_ld7 * _t17)));
    }

    /**
     * Part 1 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 3 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private double makeFromMatrix_s81574d1e_1_mulAdd(double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld6, double _ld7, double _ld8, double _t12, double _t13, double _t14) {
        double _t15 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t17 = 1.0 / (java.lang.Math.sqrt(_t14));
        double _t19 = _ld8 * _t17;
        double _t20 = _ld1 * _t16;
        double _t21 = _ld5 * _t16;
        double _t22 = _ld6 * _t17;
        double _t24 = _ld2 * _t15;
        double _t25 = _ld0 * _t15;
        return ((-((_t19) * (_t20) - (_t21 * _t22))) * (_ld3 * _t15) + (((((_t19) * (_t24) - (_t25 * _t22))) * (_ld4 * _t16) + (((_t25) * (_t20) - (_t21 * _t24)) * (_ld7 * _t17)))));
    }

    /** Part 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private void makeFromMatrix_s81574d1e_2_fma(double[] dd, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _t12, double _t13, double _t48, double _t49, double _t50) {
        double _t0 = -_ld0;
        double _t1 = -_ld1;
        double _t15 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t20 = _ld1 * _t16;
        double _t25 = _ld0 * _t15;
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = (java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t48))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t52)))))) * (java.lang.Math.fma(_ld2, _t15, -(_ld5 * _t16))) : _t48 > (java.lang.Math.max(_t25, _t20)) ? 0.5 * java.lang.Math.sqrt((java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t1, _t16, _t53)))))) * (java.lang.Math.fma(_ld3, _t15, _t49)) : (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld1, _t16, java.lang.Math.fma(_t0, _t15, _t53)))))) * (java.lang.Math.fma(_ld4, _t16, _t50));
    }

    /** Part 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private void makeFromMatrix_s81574d1e_2_mulAdd(double[] dd, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _t12, double _t13, double _t48, double _t49, double _t50) {
        double _t0 = -_ld0;
        double _t1 = -_ld1;
        double _t15 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t20 = _ld1 * _t16;
        double _t25 = _ld0 * _t15;
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = (((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t48))))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t52)))))))) * (((_ld2) * (_t15) - (_ld5 * _t16))) : _t48 > (java.lang.Math.max(_t25, _t20)) ? 0.5 * java.lang.Math.sqrt((((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_t1) * (_t16) + (_t53)))))))) * (((_ld3) * (_t15) + (_t49))) : (0.5 * (1.0 / java.lang.Math.sqrt((((_ld1) * (_t16) + (((_t0) * (_t15) + (_t53)))))))) * (((_ld4) * (_t16) + (_t50)));
    }

    /**
     * Part 3 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 3 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private void makeFromMatrix_s81574d1e_3_fma(double[] dd, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _t12, double _t13, double _t48, double _t50, double _t49) {
        double _t0 = -_ld0;
        double _t1 = -_ld1;
        double _t15 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t20 = _ld1 * _t16;
        double _t25 = _ld0 * _t15;
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        dd[4] = (java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t48))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t52)))))) * (java.lang.Math.fma(_ld4, _t16, -_t50)) : _t48 > (java.lang.Math.max(_t25, _t20)) ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)))))) * (java.lang.Math.fma(_ld3, _t15, _t49)) : _t25 > _t20 ? 0.5 * java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t1, _t16, _t53)))) : (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld1, _t16, java.lang.Math.fma(_t0, _t15, _t53)))))) * (java.lang.Math.fma(_ld5, _t16, (_ld2 * _t15)));
    }

    /**
     * Part 3 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 3 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private void makeFromMatrix_s81574d1e_3_mulAdd(double[] dd, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _t12, double _t13, double _t48, double _t50, double _t49) {
        double _t0 = -_ld0;
        double _t1 = -_ld1;
        double _t15 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t20 = _ld1 * _t16;
        double _t25 = _ld0 * _t15;
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        dd[4] = (((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t48))))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t52)))))))) * (((_ld4) * (_t16) - (_t50))) : _t48 > (java.lang.Math.max(_t25, _t20)) ? (0.5 * (1.0 / java.lang.Math.sqrt((((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))))))) * (((_ld3) * (_t15) + (_t49))) : _t25 > _t20 ? 0.5 * java.lang.Math.sqrt((((_ld0) * (_t15) + (((_t1) * (_t16) + (_t53)))))) : (0.5 * (1.0 / java.lang.Math.sqrt((((_ld1) * (_t16) + (((_t0) * (_t15) + (_t53)))))))) * (((_ld5) * (_t16) + ((_ld2 * _t15))));
    }

    /**
     * Part 4 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 3 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private void makeFromMatrix_s81574d1e_4_fma(double[] dd, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _t12, double _t13, double _t48, double _t49, double _t50) {
        double _t0 = -_ld0;
        double _t1 = -_ld1;
        double _t15 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t20 = _ld1 * _t16;
        double _t25 = _ld0 * _t15;
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        dd[5] = (java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t48))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t52)))))) * (java.lang.Math.fma(-_ld3, _t15, _t49)) : _t48 > (java.lang.Math.max(_t25, _t20)) ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)))))) * (java.lang.Math.fma(_ld4, _t16, _t50)) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t1, _t16, _t53)))))) * (java.lang.Math.fma(_ld5, _t16, (_ld2 * _t15))) : 0.5 * java.lang.Math.sqrt((java.lang.Math.fma(_ld1, _t16, java.lang.Math.fma(_t0, _t15, _t53))));
    }

    /**
     * Part 4 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 3 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private void makeFromMatrix_s81574d1e_4_mulAdd(double[] dd, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _t12, double _t13, double _t48, double _t49, double _t50) {
        double _t0 = -_ld0;
        double _t1 = -_ld1;
        double _t15 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t20 = _ld1 * _t16;
        double _t25 = _ld0 * _t15;
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        dd[5] = (((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t48))))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t52)))))))) * (((-_ld3) * (_t15) + (_t49))) : _t48 > (java.lang.Math.max(_t25, _t20)) ? (0.5 * (1.0 / java.lang.Math.sqrt((((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))))))) * (((_ld4) * (_t16) + (_t50))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_t1) * (_t16) + (_t53)))))))) * (((_ld5) * (_t16) + ((_ld2 * _t15)))) : 0.5 * java.lang.Math.sqrt((((_ld1) * (_t16) + (((_t0) * (_t15) + (_t53))))));
    }

    /**
     * Part 5 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 3 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private DoubleTransform makeFromMatrix_s81574d1e_5_fma(double[] dd, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _t12, double _t13, double _t14, double _t47, double _t48, double _t50, double _t49) {
        double _t0 = -_ld0;
        double _t1 = -_ld1;
        double _t15 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t20 = _ld1 * _t16;
        double _t25 = _ld0 * _t15;
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        dd[6] = (java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t48))) > 0.0 ? 0.5 * java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t52)))) : _t48 > (java.lang.Math.max(_t25, _t20)) ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)))))) * (java.lang.Math.fma(_ld2, _t15, -(_ld5 * _t16))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t1, _t16, _t53)))))) * (java.lang.Math.fma(_ld4, _t16, -_t50)) : (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld1, _t16, java.lang.Math.fma(_t0, _t15, _t53)))))) * (java.lang.Math.fma(-_ld3, _t15, _t49));
        dd[7] = _t47 < 0.0 ? -_t18 : _t18;
        dd[8] = java.lang.Math.sqrt(_t12);
        dd[9] = java.lang.Math.sqrt(_t13);
        return this;
    }

    /**
     * Part 5 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 3 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private DoubleTransform makeFromMatrix_s81574d1e_5_mulAdd(double[] dd, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _t12, double _t13, double _t14, double _t47, double _t48, double _t50, double _t49) {
        double _t0 = -_ld0;
        double _t1 = -_ld1;
        double _t15 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t20 = _ld1 * _t16;
        double _t25 = _ld0 * _t15;
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        dd[6] = (((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t48))))) > 0.0 ? 0.5 * java.lang.Math.sqrt((((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t52)))))) : _t48 > (java.lang.Math.max(_t25, _t20)) ? (0.5 * (1.0 / java.lang.Math.sqrt((((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))))))) * (((_ld2) * (_t15) - (_ld5 * _t16))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_t1) * (_t16) + (_t53)))))))) * (((_ld4) * (_t16) - (_t50))) : (0.5 * (1.0 / java.lang.Math.sqrt((((_ld1) * (_t16) + (((_t0) * (_t15) + (_t53)))))))) * (((-_ld3) * (_t15) + (_t49)));
        dd[7] = _t47 < 0.0 ? -_t18 : _t18;
        dd[8] = java.lang.Math.sqrt(_t12);
        dd[9] = java.lang.Math.sqrt(_t13);
        return this;
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleTransform makeFromMatrix_s81574d1e_6_fma(double[] dd, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld6, double _ld7, double _ld8, double _t12, double _t13, double _t14, double _t19, double _t22, double _t27) {
        double _t47 = makeFromMatrix_s81574d1e_1_fma(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _t12, _t13, _t14);
        double _t48, _t49, _t50;
        if (_t47 < 0.0) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        makeFromMatrix_s81574d1e_2_fma(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t48, _t49, _t50);
        makeFromMatrix_s81574d1e_3_fma(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t48, _t50, _t49);
        makeFromMatrix_s81574d1e_4_fma(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t48, _t49, _t50);
        return makeFromMatrix_s81574d1e_5_fma(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t14, _t47, _t48, _t50, _t49);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleTransform makeFromMatrix_s81574d1e_6_mulAdd(double[] dd, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld6, double _ld7, double _ld8, double _t12, double _t13, double _t14, double _t19, double _t22, double _t27) {
        double _t47 = makeFromMatrix_s81574d1e_1_mulAdd(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _t12, _t13, _t14);
        double _t48, _t49, _t50;
        if (_t47 < 0.0) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        makeFromMatrix_s81574d1e_2_mulAdd(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t48, _t49, _t50);
        makeFromMatrix_s81574d1e_3_mulAdd(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t48, _t50, _t49);
        makeFromMatrix_s81574d1e_4_mulAdd(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t48, _t49, _t50);
        return makeFromMatrix_s81574d1e_5_mulAdd(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t14, _t47, _t48, _t50, _t49);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private DoubleTransform makeFromMatrix_degenerate_fma(Double3x3R m) {
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
        double _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t35 = _t30 * _t18;
        double _t36 = _t30 * _t19;
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t56 = _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2;
        double _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0;
            _t90 = -_t36;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
            _t90 = _t35;
        }
        double _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0;
            _t91 = -_t40;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
            _t91 = _t39;
        }
        double _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0;
            _t92 = -_t41;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
            _t92 = _t42;
        }
        double _t102 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t78, _t78, java.lang.Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t92, _t92, _t77 * _t77))));
        double _t105 = _t102 * _t75;
        double _t106 = _t103 * _t76;
        double _t107 = _t104 * _t77;
        double _t108 = _t103 * _t79;
        double _t109 = _t102 * _t78;
        double _t110 = _t104 * _t80;
        double _t117 = _t103 * _t91;
        double _t118 = _t104 * _t92;
        double _t119 = _t102 * _t90;
        double _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t170 = 1.0;
                    _t173 = 0.0;
                    _t169 = 0.0;
                    _t171 = 0.0;
                    _t174 = 1.0;
                    _t172 = 0.0;
                    _t175 = 0.0;
                    _t176 = 1.0;
                } else {
                    _t168 = _t105;
                    _t170 = _t119;
                    _t173 = _t109;
                    _t169 = java.lang.Math.fma(_t35, _t105, -(_t36 * _t109));
                    _t171 = java.lang.Math.fma(_t37, _t109, -(_t35 * _t119));
                    _t174 = java.lang.Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = java.lang.Math.fma(_t38, _t108, -(_t39 * _t117));
                    _t170 = java.lang.Math.fma(_t39, _t106, -(_t40 * _t108));
                    _t173 = java.lang.Math.fma(_t40, _t117, -(_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = java.lang.Math.fma(_t35, _t38, -(_t37 * _t39));
                    _t170 = java.lang.Math.fma(_t36, _t39, -(_t35 * _t40));
                    _t173 = java.lang.Math.fma(_t37, _t40, -(_t36 * _t38));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        } else {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t118;
                    _t171 = _t107;
                    _t174 = _t110;
                    _t172 = java.lang.Math.fma(_t41, _t118, -(_t43 * _t107));
                    _t175 = java.lang.Math.fma(_t42, _t107, -(_t41 * _t110));
                    _t176 = java.lang.Math.fma(_t43, _t110, -(_t42 * _t118));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = java.lang.Math.fma(_t35, _t41, -(_t36 * _t42));
                    _t171 = java.lang.Math.fma(_t37, _t42, -(_t35 * _t43));
                    _t174 = java.lang.Math.fma(_t36, _t43, -(_t37 * _t41));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = java.lang.Math.fma(_t41, _t38, -(_t43 * _t40));
                    _t175 = java.lang.Math.fma(_t42, _t40, -(_t41 * _t39));
                    _t176 = java.lang.Math.fma(_t43, _t39, -(_t42 * _t38));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        }
        double _t185 = _t173 - _t169;
        double _t186 = java.lang.Math.max(_t170, _t174);
        double _t187 = _t173 + _t169;
        double _t196 = java.lang.Math.fma(java.lang.Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, java.lang.Math.fma(java.lang.Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, java.lang.Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
        double _t197, _t198, _t199;
        if (_t196 < 0.0) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        double _t202 = _t198 + _t168;
        double _t203 = _t199 + _t171;
        double _t204 = _t171 - _t199;
        double _t205 = _t198 - _t168;
        double _t209 = _t197 + _t170 + _t174;
        double _t210 = 1.0 + _t209;
        double _t211 = 1.0 + _t197 - _t170 - _t174;
        double _t212 = 1.0 + _t170 - _t197 - _t174;
        double _t213 = 1.0 + _t174 - _t197 - _t170;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t213));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t211));
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dd[4] = _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * java.lang.Math.sqrt(_t212) : _sp2 * _t187;
        dd[5] = _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * java.lang.Math.sqrt(_t213);
        dd[6] = _t209 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dd[7] = _t196 < 0.0 ? -_t56 : _t56;
        dd[8] = _t27 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t27) / _t0;
        dd[9] = _t28 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t28) / _t1;
        return this;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private DoubleTransform makeFromMatrix_degenerate_mulAdd(Double3x3R m) {
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
        double _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        double _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t35 = _t30 * _t18;
        double _t36 = _t30 * _t19;
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t56 = _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2;
        double _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0;
            _t90 = -_t36;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
            _t90 = _t35;
        }
        double _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0;
            _t91 = -_t40;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
            _t91 = _t39;
        }
        double _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0;
            _t92 = -_t41;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
            _t92 = _t42;
        }
        double _t102 = (1.0 / java.lang.Math.sqrt(((_t78) * (_t78) + (((_t90) * (_t90) + (_t75 * _t75))))));
        double _t103 = (1.0 / java.lang.Math.sqrt(((_t79) * (_t79) + (((_t91) * (_t91) + (_t76 * _t76))))));
        double _t104 = (1.0 / java.lang.Math.sqrt(((_t80) * (_t80) + (((_t92) * (_t92) + (_t77 * _t77))))));
        double _t105 = _t102 * _t75;
        double _t106 = _t103 * _t76;
        double _t107 = _t104 * _t77;
        double _t108 = _t103 * _t79;
        double _t109 = _t102 * _t78;
        double _t110 = _t104 * _t80;
        double _t117 = _t103 * _t91;
        double _t118 = _t104 * _t92;
        double _t119 = _t102 * _t90;
        double _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t170 = 1.0;
                    _t173 = 0.0;
                    _t169 = 0.0;
                    _t171 = 0.0;
                    _t174 = 1.0;
                    _t172 = 0.0;
                    _t175 = 0.0;
                    _t176 = 1.0;
                } else {
                    _t168 = _t105;
                    _t170 = _t119;
                    _t173 = _t109;
                    _t169 = ((_t35) * (_t105) - (_t36 * _t109));
                    _t171 = ((_t37) * (_t109) - (_t35 * _t119));
                    _t174 = ((_t36) * (_t119) - (_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = ((_t38) * (_t108) - (_t39 * _t117));
                    _t170 = ((_t39) * (_t106) - (_t40 * _t108));
                    _t173 = ((_t40) * (_t117) - (_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = ((_t35) * (_t38) - (_t37 * _t39));
                    _t170 = ((_t36) * (_t39) - (_t35 * _t40));
                    _t173 = ((_t37) * (_t40) - (_t36 * _t38));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        } else {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t118;
                    _t171 = _t107;
                    _t174 = _t110;
                    _t172 = ((_t41) * (_t118) - (_t43 * _t107));
                    _t175 = ((_t42) * (_t107) - (_t41 * _t110));
                    _t176 = ((_t43) * (_t110) - (_t42 * _t118));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = ((_t35) * (_t41) - (_t36 * _t42));
                    _t171 = ((_t37) * (_t42) - (_t35 * _t43));
                    _t174 = ((_t36) * (_t43) - (_t37 * _t41));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = ((_t41) * (_t38) - (_t43 * _t40));
                    _t175 = ((_t42) * (_t40) - (_t41 * _t39));
                    _t176 = ((_t43) * (_t39) - (_t42 * _t38));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        }
        double _t185 = _t173 - _t169;
        double _t186 = java.lang.Math.max(_t170, _t174);
        double _t187 = _t173 + _t169;
        double _t196 = ((((_t168) * (_t169) - (_t170 * _t171))) * (_t172) + (((((_t173) * (_t171) - (_t168 * _t174))) * (_t175) + (((_t170) * (_t174) - (_t173 * _t169)) * _t176))));
        double _t197, _t198, _t199;
        if (_t196 < 0.0) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        double _t202 = _t198 + _t168;
        double _t203 = _t199 + _t171;
        double _t204 = _t171 - _t199;
        double _t205 = _t198 - _t168;
        double _t209 = _t197 + _t170 + _t174;
        double _t210 = 1.0 + _t209;
        double _t211 = 1.0 + _t197 - _t170 - _t174;
        double _t212 = 1.0 + _t170 - _t197 - _t174;
        double _t213 = 1.0 + _t174 - _t197 - _t170;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t213));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t211));
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dd[4] = _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * java.lang.Math.sqrt(_t212) : _sp2 * _t187;
        dd[5] = _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * java.lang.Math.sqrt(_t213);
        dd[6] = _t209 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dd[7] = _t196 < 0.0 ? -_t56 : _t56;
        dd[8] = _t27 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t27) / _t0;
        dd[9] = _t28 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t28) / _t1;
        return this;
    }


    /**
     * Set this transform to the TRS decomposition of the given affine matrix: translation from the
     * last column, scale from the column lengths of the upper-left 3x3 block, rotation from the
     * column-normalized block (scale is removed by normalizing the columns, but shear is not
     * removed: a sheared block yields a rotation quaternion that is not unit length).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public DoubleTransform makeFromMatrix(Double3x4R m) {
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform makeFromMatrix_fma(Double3x4R m) {
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
        double _t12 = java.lang.Math.fma(_ld2, _ld2, java.lang.Math.fma(_ld3, _ld3, _ld0 * _ld0));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t13 = java.lang.Math.fma(_ld1, _ld1, java.lang.Math.fma(_ld4, _ld4, _ld5 * _ld5));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t14 = java.lang.Math.fma(_ld6, _ld6, java.lang.Math.fma(_ld7, _ld7, _ld8 * _ld8));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t17 = 1.0 / java.lang.Math.sqrt(_t14);
        return makeFromMatrix_s89828b35_6_fma(dd, mData, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ld10, _ld11, _t12, _t13, _t14, _ld8 * _t17, _ld6 * _t17, _ld7 * _t17);
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform makeFromMatrix_mulAdd(Double3x4R m) {
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
        double _t12 = ((_ld2) * (_ld2) + (((_ld3) * (_ld3) + (_ld0 * _ld0))));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t13 = ((_ld1) * (_ld1) + (((_ld4) * (_ld4) + (_ld5 * _ld5))));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t14 = ((_ld6) * (_ld6) + (((_ld7) * (_ld7) + (_ld8 * _ld8))));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t17 = 1.0 / java.lang.Math.sqrt(_t14);
        return makeFromMatrix_s89828b35_6_mulAdd(dd, mData, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ld10, _ld11, _t12, _t13, _t14, _ld8 * _t17, _ld6 * _t17, _ld7 * _t17);
    }

    /** Part 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private void makeFromMatrix_s89828b35_2_fma(double[] dd, double[] mData, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld10, double _ld11, double _t12, double _t13, double _t48, double _t49, double _t50) {
        double _t0 = -_ld0;
        double _t1 = -_ld1;
        double _t15 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t20 = _ld1 * _t16;
        double _t25 = _ld0 * _t15;
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        dd[0] = mData[3];
        dd[1] = _ld10;
        dd[2] = _ld11;
        dd[3] = (java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t48))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t52)))))) * (java.lang.Math.fma(_ld2, _t15, -(_ld5 * _t16))) : _t48 > (java.lang.Math.max(_t25, _t20)) ? 0.5 * java.lang.Math.sqrt((java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t1, _t16, _t53)))))) * (java.lang.Math.fma(_ld3, _t15, _t49)) : (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld1, _t16, java.lang.Math.fma(_t0, _t15, _t53)))))) * (java.lang.Math.fma(_ld4, _t16, _t50));
    }

    /** Part 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private void makeFromMatrix_s89828b35_2_mulAdd(double[] dd, double[] mData, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld10, double _ld11, double _t12, double _t13, double _t48, double _t49, double _t50) {
        double _t0 = -_ld0;
        double _t1 = -_ld1;
        double _t15 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t20 = _ld1 * _t16;
        double _t25 = _ld0 * _t15;
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        dd[0] = mData[3];
        dd[1] = _ld10;
        dd[2] = _ld11;
        dd[3] = (((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t48))))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t52)))))))) * (((_ld2) * (_t15) - (_ld5 * _t16))) : _t48 > (java.lang.Math.max(_t25, _t20)) ? 0.5 * java.lang.Math.sqrt((((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_t1) * (_t16) + (_t53)))))))) * (((_ld3) * (_t15) + (_t49))) : (0.5 * (1.0 / java.lang.Math.sqrt((((_ld1) * (_t16) + (((_t0) * (_t15) + (_t53)))))))) * (((_ld4) * (_t16) + (_t50)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleTransform makeFromMatrix_s89828b35_6_fma(double[] dd, double[] mData, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld6, double _ld7, double _ld8, double _ld10, double _ld11, double _t12, double _t13, double _t14, double _t19, double _t22, double _t27) {
        double _t47 = makeFromMatrix_s81574d1e_1_fma(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _t12, _t13, _t14);
        double _t48, _t49, _t50;
        if (_t47 < 0.0) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        makeFromMatrix_s89828b35_2_fma(dd, mData, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld10, _ld11, _t12, _t13, _t48, _t49, _t50);
        makeFromMatrix_s81574d1e_3_fma(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t48, _t50, _t49);
        makeFromMatrix_s81574d1e_4_fma(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t48, _t49, _t50);
        return makeFromMatrix_s81574d1e_5_fma(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t14, _t47, _t48, _t50, _t49);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleTransform makeFromMatrix_s89828b35_6_mulAdd(double[] dd, double[] mData, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld6, double _ld7, double _ld8, double _ld10, double _ld11, double _t12, double _t13, double _t14, double _t19, double _t22, double _t27) {
        double _t47 = makeFromMatrix_s81574d1e_1_mulAdd(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _t12, _t13, _t14);
        double _t48, _t49, _t50;
        if (_t47 < 0.0) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        makeFromMatrix_s89828b35_2_mulAdd(dd, mData, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld10, _ld11, _t12, _t13, _t48, _t49, _t50);
        makeFromMatrix_s81574d1e_3_mulAdd(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t48, _t50, _t49);
        makeFromMatrix_s81574d1e_4_mulAdd(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t48, _t49, _t50);
        return makeFromMatrix_s81574d1e_5_mulAdd(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t14, _t47, _t48, _t50, _t49);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private DoubleTransform makeFromMatrix_degenerate_fma(Double3x4R m) {
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
        double _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t35 = _t30 * _t18;
        double _t36 = _t30 * _t19;
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t56 = _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2;
        double _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0;
            _t90 = -_t36;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
            _t90 = _t35;
        }
        double _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0;
            _t91 = -_t40;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
            _t91 = _t39;
        }
        double _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0;
            _t92 = -_t41;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
            _t92 = _t42;
        }
        double _t102 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t78, _t78, java.lang.Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t92, _t92, _t77 * _t77))));
        double _t105 = _t102 * _t75;
        double _t106 = _t103 * _t76;
        double _t107 = _t104 * _t77;
        double _t108 = _t103 * _t79;
        double _t109 = _t102 * _t78;
        double _t110 = _t104 * _t80;
        double _t117 = _t103 * _t91;
        double _t118 = _t104 * _t92;
        double _t119 = _t102 * _t90;
        double _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t170 = 1.0;
                    _t173 = 0.0;
                    _t169 = 0.0;
                    _t171 = 0.0;
                    _t174 = 1.0;
                    _t172 = 0.0;
                    _t175 = 0.0;
                    _t176 = 1.0;
                } else {
                    _t168 = _t105;
                    _t170 = _t119;
                    _t173 = _t109;
                    _t169 = java.lang.Math.fma(_t35, _t105, -(_t36 * _t109));
                    _t171 = java.lang.Math.fma(_t37, _t109, -(_t35 * _t119));
                    _t174 = java.lang.Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = java.lang.Math.fma(_t38, _t108, -(_t39 * _t117));
                    _t170 = java.lang.Math.fma(_t39, _t106, -(_t40 * _t108));
                    _t173 = java.lang.Math.fma(_t40, _t117, -(_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = java.lang.Math.fma(_t35, _t38, -(_t37 * _t39));
                    _t170 = java.lang.Math.fma(_t36, _t39, -(_t35 * _t40));
                    _t173 = java.lang.Math.fma(_t37, _t40, -(_t36 * _t38));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        } else {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t118;
                    _t171 = _t107;
                    _t174 = _t110;
                    _t172 = java.lang.Math.fma(_t41, _t118, -(_t43 * _t107));
                    _t175 = java.lang.Math.fma(_t42, _t107, -(_t41 * _t110));
                    _t176 = java.lang.Math.fma(_t43, _t110, -(_t42 * _t118));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = java.lang.Math.fma(_t35, _t41, -(_t36 * _t42));
                    _t171 = java.lang.Math.fma(_t37, _t42, -(_t35 * _t43));
                    _t174 = java.lang.Math.fma(_t36, _t43, -(_t37 * _t41));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = java.lang.Math.fma(_t41, _t38, -(_t43 * _t40));
                    _t175 = java.lang.Math.fma(_t42, _t40, -(_t41 * _t39));
                    _t176 = java.lang.Math.fma(_t43, _t39, -(_t42 * _t38));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        }
        double _t185 = _t173 - _t169;
        double _t186 = java.lang.Math.max(_t170, _t174);
        double _t187 = _t173 + _t169;
        double _t196 = java.lang.Math.fma(java.lang.Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, java.lang.Math.fma(java.lang.Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, java.lang.Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
        double _t197, _t198, _t199;
        if (_t196 < 0.0) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        double _t202 = _t198 + _t168;
        double _t203 = _t199 + _t171;
        double _t204 = _t171 - _t199;
        double _t205 = _t198 - _t168;
        double _t209 = _t197 + _t170 + _t174;
        double _t210 = 1.0 + _t209;
        double _t211 = 1.0 + _t197 - _t170 - _t174;
        double _t212 = 1.0 + _t170 - _t197 - _t174;
        double _t213 = 1.0 + _t174 - _t197 - _t170;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t213));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t211));
        dd[0] = mData[3];
        dd[1] = mData[7];
        dd[2] = mData[11];
        dd[3] = _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dd[4] = _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * java.lang.Math.sqrt(_t212) : _sp2 * _t187;
        dd[5] = _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * java.lang.Math.sqrt(_t213);
        dd[6] = _t209 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dd[7] = _t196 < 0.0 ? -_t56 : _t56;
        dd[8] = _t27 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t27) / _t0;
        dd[9] = _t28 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t28) / _t1;
        return this;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private DoubleTransform makeFromMatrix_degenerate_mulAdd(Double3x4R m) {
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
        double _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        double _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t35 = _t30 * _t18;
        double _t36 = _t30 * _t19;
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t56 = _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2;
        double _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0;
            _t90 = -_t36;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
            _t90 = _t35;
        }
        double _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0;
            _t91 = -_t40;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
            _t91 = _t39;
        }
        double _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0;
            _t92 = -_t41;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
            _t92 = _t42;
        }
        double _t102 = (1.0 / java.lang.Math.sqrt(((_t78) * (_t78) + (((_t90) * (_t90) + (_t75 * _t75))))));
        double _t103 = (1.0 / java.lang.Math.sqrt(((_t79) * (_t79) + (((_t91) * (_t91) + (_t76 * _t76))))));
        double _t104 = (1.0 / java.lang.Math.sqrt(((_t80) * (_t80) + (((_t92) * (_t92) + (_t77 * _t77))))));
        double _t105 = _t102 * _t75;
        double _t106 = _t103 * _t76;
        double _t107 = _t104 * _t77;
        double _t108 = _t103 * _t79;
        double _t109 = _t102 * _t78;
        double _t110 = _t104 * _t80;
        double _t117 = _t103 * _t91;
        double _t118 = _t104 * _t92;
        double _t119 = _t102 * _t90;
        double _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t170 = 1.0;
                    _t173 = 0.0;
                    _t169 = 0.0;
                    _t171 = 0.0;
                    _t174 = 1.0;
                    _t172 = 0.0;
                    _t175 = 0.0;
                    _t176 = 1.0;
                } else {
                    _t168 = _t105;
                    _t170 = _t119;
                    _t173 = _t109;
                    _t169 = ((_t35) * (_t105) - (_t36 * _t109));
                    _t171 = ((_t37) * (_t109) - (_t35 * _t119));
                    _t174 = ((_t36) * (_t119) - (_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = ((_t38) * (_t108) - (_t39 * _t117));
                    _t170 = ((_t39) * (_t106) - (_t40 * _t108));
                    _t173 = ((_t40) * (_t117) - (_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = ((_t35) * (_t38) - (_t37 * _t39));
                    _t170 = ((_t36) * (_t39) - (_t35 * _t40));
                    _t173 = ((_t37) * (_t40) - (_t36 * _t38));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        } else {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t118;
                    _t171 = _t107;
                    _t174 = _t110;
                    _t172 = ((_t41) * (_t118) - (_t43 * _t107));
                    _t175 = ((_t42) * (_t107) - (_t41 * _t110));
                    _t176 = ((_t43) * (_t110) - (_t42 * _t118));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = ((_t35) * (_t41) - (_t36 * _t42));
                    _t171 = ((_t37) * (_t42) - (_t35 * _t43));
                    _t174 = ((_t36) * (_t43) - (_t37 * _t41));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = ((_t41) * (_t38) - (_t43 * _t40));
                    _t175 = ((_t42) * (_t40) - (_t41 * _t39));
                    _t176 = ((_t43) * (_t39) - (_t42 * _t38));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        }
        double _t185 = _t173 - _t169;
        double _t186 = java.lang.Math.max(_t170, _t174);
        double _t187 = _t173 + _t169;
        double _t196 = ((((_t168) * (_t169) - (_t170 * _t171))) * (_t172) + (((((_t173) * (_t171) - (_t168 * _t174))) * (_t175) + (((_t170) * (_t174) - (_t173 * _t169)) * _t176))));
        double _t197, _t198, _t199;
        if (_t196 < 0.0) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        double _t202 = _t198 + _t168;
        double _t203 = _t199 + _t171;
        double _t204 = _t171 - _t199;
        double _t205 = _t198 - _t168;
        double _t209 = _t197 + _t170 + _t174;
        double _t210 = 1.0 + _t209;
        double _t211 = 1.0 + _t197 - _t170 - _t174;
        double _t212 = 1.0 + _t170 - _t197 - _t174;
        double _t213 = 1.0 + _t174 - _t197 - _t170;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t213));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t211));
        dd[0] = mData[3];
        dd[1] = mData[7];
        dd[2] = mData[11];
        dd[3] = _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dd[4] = _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * java.lang.Math.sqrt(_t212) : _sp2 * _t187;
        dd[5] = _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * java.lang.Math.sqrt(_t213);
        dd[6] = _t209 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dd[7] = _t196 < 0.0 ? -_t56 : _t56;
        dd[8] = _t27 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t27) / _t0;
        dd[9] = _t28 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t28) / _t1;
        return this;
    }


    /**
     * Set this transform to the TRS decomposition of the given affine matrix: translation from the
     * last column, scale from the column lengths of the upper-left 3x3 block, rotation from the
     * column-normalized block (scale is removed by normalizing the columns, but shear is not
     * removed: a sheared block yields a rotation quaternion that is not unit length).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public DoubleTransform makeFromMatrix(Double4x4R m) {
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform makeFromMatrix_fma(Double4x4R m) {
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
        double _t12 = java.lang.Math.fma(_ld2, _ld2, java.lang.Math.fma(_ld3, _ld3, _ld0 * _ld0));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t13 = java.lang.Math.fma(_ld1, _ld1, java.lang.Math.fma(_ld4, _ld4, _ld5 * _ld5));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t14 = java.lang.Math.fma(_ld6, _ld6, java.lang.Math.fma(_ld7, _ld7, _ld8 * _ld8));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t17 = 1.0 / java.lang.Math.sqrt(_t14);
        return makeFromMatrix_sf64e4286_6_fma(dd, mData, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ld10, _ld11, _t12, _t13, _t14, _ld8 * _t17, _ld6 * _t17, _ld7 * _t17);
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform makeFromMatrix_mulAdd(Double4x4R m) {
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
        double _t12 = ((_ld2) * (_ld2) + (((_ld3) * (_ld3) + (_ld0 * _ld0))));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t13 = ((_ld1) * (_ld1) + (((_ld4) * (_ld4) + (_ld5 * _ld5))));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t14 = ((_ld6) * (_ld6) + (((_ld7) * (_ld7) + (_ld8 * _ld8))));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t17 = 1.0 / java.lang.Math.sqrt(_t14);
        return makeFromMatrix_sf64e4286_6_mulAdd(dd, mData, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _ld10, _ld11, _t12, _t13, _t14, _ld8 * _t17, _ld6 * _t17, _ld7 * _t17);
    }

    /** Part 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private void makeFromMatrix_sf64e4286_2_fma(double[] dd, double[] mData, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld10, double _ld11, double _t12, double _t13, double _t48, double _t49, double _t50) {
        double _t0 = -_ld0;
        double _t1 = -_ld1;
        double _t15 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t20 = _ld1 * _t16;
        double _t25 = _ld0 * _t15;
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        dd[0] = mData[12];
        dd[1] = _ld10;
        dd[2] = _ld11;
        dd[3] = (java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t48))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t52)))))) * (java.lang.Math.fma(_ld2, _t15, -(_ld5 * _t16))) : _t48 > (java.lang.Math.max(_t25, _t20)) ? 0.5 * java.lang.Math.sqrt((java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t1, _t16, _t53)))))) * (java.lang.Math.fma(_ld3, _t15, _t49)) : (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld1, _t16, java.lang.Math.fma(_t0, _t15, _t53)))))) * (java.lang.Math.fma(_ld4, _t16, _t50));
    }

    /** Part 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private void makeFromMatrix_sf64e4286_2_mulAdd(double[] dd, double[] mData, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld10, double _ld11, double _t12, double _t13, double _t48, double _t49, double _t50) {
        double _t0 = -_ld0;
        double _t1 = -_ld1;
        double _t15 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t20 = _ld1 * _t16;
        double _t25 = _ld0 * _t15;
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        dd[0] = mData[12];
        dd[1] = _ld10;
        dd[2] = _ld11;
        dd[3] = (((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t48))))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t52)))))))) * (((_ld2) * (_t15) - (_ld5 * _t16))) : _t48 > (java.lang.Math.max(_t25, _t20)) ? 0.5 * java.lang.Math.sqrt((((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_t1) * (_t16) + (_t53)))))))) * (((_ld3) * (_t15) + (_t49))) : (0.5 * (1.0 / java.lang.Math.sqrt((((_ld1) * (_t16) + (((_t0) * (_t15) + (_t53)))))))) * (((_ld4) * (_t16) + (_t50)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleTransform makeFromMatrix_sf64e4286_6_fma(double[] dd, double[] mData, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld6, double _ld7, double _ld8, double _ld10, double _ld11, double _t12, double _t13, double _t14, double _t19, double _t22, double _t27) {
        double _t47 = makeFromMatrix_s81574d1e_1_fma(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _t12, _t13, _t14);
        double _t48, _t49, _t50;
        if (_t47 < 0.0) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        makeFromMatrix_sf64e4286_2_fma(dd, mData, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld10, _ld11, _t12, _t13, _t48, _t49, _t50);
        makeFromMatrix_s81574d1e_3_fma(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t48, _t50, _t49);
        makeFromMatrix_s81574d1e_4_fma(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t48, _t49, _t50);
        return makeFromMatrix_s81574d1e_5_fma(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t14, _t47, _t48, _t50, _t49);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private DoubleTransform makeFromMatrix_sf64e4286_6_mulAdd(double[] dd, double[] mData, double _ld0, double _ld1, double _ld2, double _ld3, double _ld4, double _ld5, double _ld6, double _ld7, double _ld8, double _ld10, double _ld11, double _t12, double _t13, double _t14, double _t19, double _t22, double _t27) {
        double _t47 = makeFromMatrix_s81574d1e_1_mulAdd(_ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld6, _ld7, _ld8, _t12, _t13, _t14);
        double _t48, _t49, _t50;
        if (_t47 < 0.0) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        makeFromMatrix_sf64e4286_2_mulAdd(dd, mData, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _ld10, _ld11, _t12, _t13, _t48, _t49, _t50);
        makeFromMatrix_s81574d1e_3_mulAdd(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t48, _t50, _t49);
        makeFromMatrix_s81574d1e_4_mulAdd(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t48, _t49, _t50);
        return makeFromMatrix_s81574d1e_5_mulAdd(dd, _ld0, _ld1, _ld2, _ld3, _ld4, _ld5, _t12, _t13, _t14, _t47, _t48, _t50, _t49);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private DoubleTransform makeFromMatrix_degenerate_fma(Double4x4R m) {
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
        double _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t35 = _t30 * _t18;
        double _t36 = _t30 * _t19;
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t56 = _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2;
        double _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0;
            _t90 = -_t36;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
            _t90 = _t35;
        }
        double _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0;
            _t91 = -_t40;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
            _t91 = _t39;
        }
        double _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0;
            _t92 = -_t41;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
            _t92 = _t42;
        }
        double _t102 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t78, _t78, java.lang.Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t92, _t92, _t77 * _t77))));
        double _t105 = _t102 * _t75;
        double _t106 = _t103 * _t76;
        double _t107 = _t104 * _t77;
        double _t108 = _t103 * _t79;
        double _t109 = _t102 * _t78;
        double _t110 = _t104 * _t80;
        double _t117 = _t103 * _t91;
        double _t118 = _t104 * _t92;
        double _t119 = _t102 * _t90;
        double _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t170 = 1.0;
                    _t173 = 0.0;
                    _t169 = 0.0;
                    _t171 = 0.0;
                    _t174 = 1.0;
                    _t172 = 0.0;
                    _t175 = 0.0;
                    _t176 = 1.0;
                } else {
                    _t168 = _t105;
                    _t170 = _t119;
                    _t173 = _t109;
                    _t169 = java.lang.Math.fma(_t35, _t105, -(_t36 * _t109));
                    _t171 = java.lang.Math.fma(_t37, _t109, -(_t35 * _t119));
                    _t174 = java.lang.Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = java.lang.Math.fma(_t38, _t108, -(_t39 * _t117));
                    _t170 = java.lang.Math.fma(_t39, _t106, -(_t40 * _t108));
                    _t173 = java.lang.Math.fma(_t40, _t117, -(_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = java.lang.Math.fma(_t35, _t38, -(_t37 * _t39));
                    _t170 = java.lang.Math.fma(_t36, _t39, -(_t35 * _t40));
                    _t173 = java.lang.Math.fma(_t37, _t40, -(_t36 * _t38));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        } else {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t118;
                    _t171 = _t107;
                    _t174 = _t110;
                    _t172 = java.lang.Math.fma(_t41, _t118, -(_t43 * _t107));
                    _t175 = java.lang.Math.fma(_t42, _t107, -(_t41 * _t110));
                    _t176 = java.lang.Math.fma(_t43, _t110, -(_t42 * _t118));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = java.lang.Math.fma(_t35, _t41, -(_t36 * _t42));
                    _t171 = java.lang.Math.fma(_t37, _t42, -(_t35 * _t43));
                    _t174 = java.lang.Math.fma(_t36, _t43, -(_t37 * _t41));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = java.lang.Math.fma(_t41, _t38, -(_t43 * _t40));
                    _t175 = java.lang.Math.fma(_t42, _t40, -(_t41 * _t39));
                    _t176 = java.lang.Math.fma(_t43, _t39, -(_t42 * _t38));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        }
        double _t185 = _t173 - _t169;
        double _t186 = java.lang.Math.max(_t170, _t174);
        double _t187 = _t173 + _t169;
        double _t196 = java.lang.Math.fma(java.lang.Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, java.lang.Math.fma(java.lang.Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, java.lang.Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
        double _t197, _t198, _t199;
        if (_t196 < 0.0) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        double _t202 = _t198 + _t168;
        double _t203 = _t199 + _t171;
        double _t204 = _t171 - _t199;
        double _t205 = _t198 - _t168;
        double _t209 = _t197 + _t170 + _t174;
        double _t210 = 1.0 + _t209;
        double _t211 = 1.0 + _t197 - _t170 - _t174;
        double _t212 = 1.0 + _t170 - _t197 - _t174;
        double _t213 = 1.0 + _t174 - _t197 - _t170;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t213));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t211));
        dd[0] = mData[12];
        dd[1] = mData[13];
        dd[2] = mData[14];
        dd[3] = _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dd[4] = _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * java.lang.Math.sqrt(_t212) : _sp2 * _t187;
        dd[5] = _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * java.lang.Math.sqrt(_t213);
        dd[6] = _t209 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dd[7] = _t196 < 0.0 ? -_t56 : _t56;
        dd[8] = _t27 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t27) / _t0;
        dd[9] = _t28 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t28) / _t1;
        return this;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private DoubleTransform makeFromMatrix_degenerate_mulAdd(Double4x4R m) {
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
        double _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        double _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t35 = _t30 * _t18;
        double _t36 = _t30 * _t19;
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t56 = _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2;
        double _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0;
            _t90 = -_t36;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
            _t90 = _t35;
        }
        double _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0;
            _t91 = -_t40;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
            _t91 = _t39;
        }
        double _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0;
            _t92 = -_t41;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
            _t92 = _t42;
        }
        double _t102 = (1.0 / java.lang.Math.sqrt(((_t78) * (_t78) + (((_t90) * (_t90) + (_t75 * _t75))))));
        double _t103 = (1.0 / java.lang.Math.sqrt(((_t79) * (_t79) + (((_t91) * (_t91) + (_t76 * _t76))))));
        double _t104 = (1.0 / java.lang.Math.sqrt(((_t80) * (_t80) + (((_t92) * (_t92) + (_t77 * _t77))))));
        double _t105 = _t102 * _t75;
        double _t106 = _t103 * _t76;
        double _t107 = _t104 * _t77;
        double _t108 = _t103 * _t79;
        double _t109 = _t102 * _t78;
        double _t110 = _t104 * _t80;
        double _t117 = _t103 * _t91;
        double _t118 = _t104 * _t92;
        double _t119 = _t102 * _t90;
        double _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t170 = 1.0;
                    _t173 = 0.0;
                    _t169 = 0.0;
                    _t171 = 0.0;
                    _t174 = 1.0;
                    _t172 = 0.0;
                    _t175 = 0.0;
                    _t176 = 1.0;
                } else {
                    _t168 = _t105;
                    _t170 = _t119;
                    _t173 = _t109;
                    _t169 = ((_t35) * (_t105) - (_t36 * _t109));
                    _t171 = ((_t37) * (_t109) - (_t35 * _t119));
                    _t174 = ((_t36) * (_t119) - (_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = ((_t38) * (_t108) - (_t39 * _t117));
                    _t170 = ((_t39) * (_t106) - (_t40 * _t108));
                    _t173 = ((_t40) * (_t117) - (_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = ((_t35) * (_t38) - (_t37 * _t39));
                    _t170 = ((_t36) * (_t39) - (_t35 * _t40));
                    _t173 = ((_t37) * (_t40) - (_t36 * _t38));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        } else {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t118;
                    _t171 = _t107;
                    _t174 = _t110;
                    _t172 = ((_t41) * (_t118) - (_t43 * _t107));
                    _t175 = ((_t42) * (_t107) - (_t41 * _t110));
                    _t176 = ((_t43) * (_t110) - (_t42 * _t118));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = ((_t35) * (_t41) - (_t36 * _t42));
                    _t171 = ((_t37) * (_t42) - (_t35 * _t43));
                    _t174 = ((_t36) * (_t43) - (_t37 * _t41));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = ((_t41) * (_t38) - (_t43 * _t40));
                    _t175 = ((_t42) * (_t40) - (_t41 * _t39));
                    _t176 = ((_t43) * (_t39) - (_t42 * _t38));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        }
        double _t185 = _t173 - _t169;
        double _t186 = java.lang.Math.max(_t170, _t174);
        double _t187 = _t173 + _t169;
        double _t196 = ((((_t168) * (_t169) - (_t170 * _t171))) * (_t172) + (((((_t173) * (_t171) - (_t168 * _t174))) * (_t175) + (((_t170) * (_t174) - (_t173 * _t169)) * _t176))));
        double _t197, _t198, _t199;
        if (_t196 < 0.0) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        double _t202 = _t198 + _t168;
        double _t203 = _t199 + _t171;
        double _t204 = _t171 - _t199;
        double _t205 = _t198 - _t168;
        double _t209 = _t197 + _t170 + _t174;
        double _t210 = 1.0 + _t209;
        double _t211 = 1.0 + _t197 - _t170 - _t174;
        double _t212 = 1.0 + _t170 - _t197 - _t174;
        double _t213 = 1.0 + _t174 - _t197 - _t170;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t213));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t211));
        dd[0] = mData[12];
        dd[1] = mData[13];
        dd[2] = mData[14];
        dd[3] = _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dd[4] = _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * java.lang.Math.sqrt(_t212) : _sp2 * _t187;
        dd[5] = _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * java.lang.Math.sqrt(_t213);
        dd[6] = _t209 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dd[7] = _t196 < 0.0 ? -_t56 : _t56;
        dd[8] = _t27 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t27) / _t0;
        dd[9] = _t28 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t28) / _t1;
        return this;
    }


    /**
     * Set this transform to the given rigid transform's motion (translation and rotation), with
     * scale = 1.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param r the rigid transform to convert
     * @return this
     */
    public @Mutated DoubleTransform makeFromRigid(DoubleRigidR r) {
        double rTY = r.tY();
        double rTZ = r.tZ();
        double rRX = r.rX();
        double rRY = r.rY();
        double rRZ = r.rZ();
        double rRW = r.rW();
        double[] dd = this.data;
        dd[0] = r.tX();
        dd[1] = rTY;
        dd[2] = rTZ;
        dd[3] = rRX;
        dd[4] = rRY;
        dd[5] = rRZ;
        dd[6] = rRW;
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to the given rigid transform's motion (translation and rotation), with
     * scale = 1.
     * <p>
     * Valid input: the default range of the package documentation.
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
     * @return this
     */
    @Mutated public DoubleTransform makeFromRigid(double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        double[] dd = this.data;
        dd[0] = rTX;
        dd[1] = rTY;
        dd[2] = rTZ;
        dd[3] = rRX;
        dd[4] = rRY;
        dd[5] = rRZ;
        dd[6] = rRW;
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Convert this transform's rigid motion (rotation and translation) to a unit dual quaternion;
     * the scale is dropped (dual quaternions cannot represent it) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleDualQuat toDualQuat(@Mutated DoubleDualQuat dest) {
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


    /**
     * Compute the matrix representation of this transform and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4x4 toMatrix(@Mutated Double4x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        double _t0 = sd[7] + sd[7];
        double _t1 = sd[8] + sd[8];
        double _t2 = sd[9] + sd[9];
        double _t3 = sd[5] * sd[5];
        double _t4 = sd[5] * sd[6];
        double _t5 = sd[4] * sd[6];
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(-Math.fma(_rd1, _rd1, _t3), _t0, sd[7]);
        dd[1] = Math.fma(_rd0, _rd1, _t4) * _t0;
        dd[2] = Math.fma(_rd0, _rd2, -_t5) * _t0;
        dd[3] = 0.0;
        dd[4] = Math.fma(_rd0, _rd1, -_t4) * _t1;
        dd[5] = Math.fma(-Math.fma(_rd0, _rd0, _t3), _t1, sd[8]);
        dd[6] = Math.fma(_rd0, _rd3, _rd1 * _rd2) * _t1;
        dd[7] = 0.0;
        dd[8] = Math.fma(_rd0, _rd2, _t5) * _t2;
        dd[9] = Math.fma(_rd1, _rd2, -(_rd0 * _rd3)) * _t2;
        dd[10] = Math.fma(-Math.fma(_rd0, _rd0, _rd1 * _rd1), _t2, sd[9]);
        dd[11] = 0.0;
        dd[12] = sd[0];
        dd[13] = sd[1];
        dd[14] = sd[2];
        dd[15] = 1.0;
        ((Double4x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Compute the 3x3 linear block ({@code R * S}) of this transform (the translation is dropped)
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 toMatrix3x3(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = sd[7] + sd[7];
        double _t1 = sd[8] + sd[8];
        double _t2 = sd[9] + sd[9];
        double _t3 = sd[5] * sd[5];
        double _t4 = sd[5] * sd[6];
        double _t5 = sd[4] * sd[6];
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(-Math.fma(_rd1, _rd1, _t3), _t0, sd[7]);
        dd[1] = Math.fma(_rd0, _rd1, _t4) * _t0;
        dd[2] = Math.fma(_rd0, _rd2, -_t5) * _t0;
        dd[3] = Math.fma(_rd0, _rd1, -_t4) * _t1;
        dd[4] = Math.fma(-Math.fma(_rd0, _rd0, _t3), _t1, sd[8]);
        dd[5] = Math.fma(_rd0, _rd3, _rd1 * _rd2) * _t1;
        dd[6] = Math.fma(_rd0, _rd2, _t5) * _t2;
        dd[7] = Math.fma(_rd1, _rd2, -(_rd0 * _rd3)) * _t2;
        dd[8] = Math.fma(-Math.fma(_rd0, _rd0, _rd1 * _rd1), _t2, sd[9]);
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Compute the 3x4 matrix representation of this transform (the omitted last row is implicitly
     * {@code 0, 0, 0, 1}) and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x4 toMatrix3x4(@Mutated Double3x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        double _t0 = sd[7] + sd[7];
        double _t1 = sd[8] + sd[8];
        double _t2 = sd[9] + sd[9];
        double _t3 = sd[5] * sd[5];
        double _t4 = sd[5] * sd[6];
        double _t5 = sd[4] * sd[6];
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(-Math.fma(_rd1, _rd1, _t3), _t0, sd[7]);
        dd[1] = Math.fma(_rd0, _rd1, -_t4) * _t1;
        dd[2] = Math.fma(_rd0, _rd2, _t5) * _t2;
        dd[3] = sd[0];
        dd[4] = Math.fma(_rd0, _rd1, _t4) * _t0;
        dd[5] = Math.fma(-Math.fma(_rd0, _rd0, _t3), _t1, sd[8]);
        dd[6] = Math.fma(_rd1, _rd2, -(_rd0 * _rd3)) * _t2;
        dd[7] = sd[1];
        dd[8] = Math.fma(_rd0, _rd2, -_t5) * _t0;
        dd[9] = Math.fma(_rd0, _rd3, _rd1 * _rd2) * _t1;
        dd[10] = Math.fma(-Math.fma(_rd0, _rd0, _rd1 * _rd1), _t2, sd[9]);
        dd[11] = sd[2];
        ((Double3x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Narrow this transform to a rigid transform (translation and rotation; the scale is dropped)
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid toRigid(@Mutated DoubleRigid dest) {
        double[] sd = this.data;
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
     * Convert this transform to {@code float} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatTransform toFloat(@Mutated FloatTransform dest) {
        double[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        dd[3] = (float) (sd[3]);
        dd[4] = (float) (sd[4]);
        dd[5] = (float) (sd[5]);
        dd[6] = (float) (sd[6]);
        dd[7] = (float) (sd[7]);
        dd[8] = (float) (sd[8]);
        dd[9] = (float) (sd[9]);
        return dest;
    }


    /**
     * Set this transform to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public DoubleTransform makeIdentity() {
        double[] dd = this.data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 1.0;
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a pure rotation by {@code rotation} (zero translation, unit scale).
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @return this
     */
    public @Mutated DoubleTransform set(DoubleQuatR rotation) {
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
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a pure rotation by ({@code rotationX}, {@code rotationY},
     * {@code rotationZ}, {@code rotationW}) (zero translation, unit scale).
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
    @Mutated public DoubleTransform set(double rotationX, double rotationY, double rotationZ, double rotationW) {
        double[] dd = this.data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = rotationX;
        dd[4] = rotationY;
        dd[5] = rotationZ;
        dd[6] = rotationW;
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a pure translation by {@code translation} (identity rotation, unit
     * scale).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation
     * @return this
     */
    public @Mutated DoubleTransform set(Double3R translation) {
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
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a pure translation by ({@code translationX}, {@code translationY},
     * {@code translationZ}) (identity rotation, unit scale).
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
    @Mutated public DoubleTransform set(double translationX, double translationY, double translationZ) {
        double[] dd = this.data;
        dd[0] = translationX;
        dd[1] = translationY;
        dd[2] = translationZ;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 1.0;
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Interpolate between this transform and {@code other} using the interpolation factor
     * {@code t}, interpolating translation and scale linearly and the rotation via shortest-arc
     * slerp and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this transform (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the rotation of this transform must have unit length; the rotation of
     * {@code other} must have unit length.
     *
     * @param other the transform to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform lerp(DoubleTransformR other, double t, @Mutated DoubleTransform dest) {
        return lerp(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), other.sX(), other.sY(), other.sZ(), t, dest);
    }


    /**
     * Interpolate between this transform and ({@code otherTX}, {@code otherTY}, {@code otherTZ},
     * {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherSX},
     * {@code otherSY}, {@code otherSZ}) using the interpolation factor {@code t}, interpolating
     * translation and scale linearly and the rotation via shortest-arc slerp and store the result
     * in {@code dest}.
     * <p>
     * The interpolation starts at this transform (interpolation factor {@code 0}) and ends at
     * ({@code otherTX}, {@code otherTY}, {@code otherTZ}, {@code otherRX}, {@code otherRY},
     * {@code otherRZ}, {@code otherRW}, {@code otherSX}, {@code otherSY}, {@code otherSZ})
     * (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the rotation of this transform must have unit length;
     * {@code (otherRX, otherRY, otherRZ, otherRW)} must have unit length.
     *
     * @param otherTX the {@code tX} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherTY the {@code tY} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherTZ the {@code tZ} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherRX the {@code rX} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherRY the {@code rY} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherRZ the {@code rZ} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherRW the {@code rW} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherSX the {@code sX} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherSY the {@code sY} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherSZ the {@code sZ} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform lerp(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, double t, @Mutated DoubleTransform dest) {
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
        return (Math.useFma() ? lerp_s3232cb55_1_fma(otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ, t, dest, sd, ((DoubleTransformImpl) dest).data, _t0, _t17, 1.0 / _t17, Math.sin(t * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16)) : lerp_s3232cb55_1_mulAdd(otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ, t, dest, sd, ((DoubleTransformImpl) dest).data, _t0, _t17, 1.0 / _t17, Math.sin(t * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16)));
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleTransform lerp_s3232cb55_1_fma(double otherTX, double otherTY, double otherTZ, double otherSX, double otherSY, double otherSZ, double t, DoubleTransform dest, double[] sd, double[] dd, double _t0, double _t17, double _t17_inv, double _t19, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t42, _t43, _t44, _t45;
        if (_t17 > 0.0) {
            _t42 = java.lang.Math.fma(sd[6], _t25, _t19 * _t21) * _t17_inv;
            _t43 = java.lang.Math.fma(sd[5], _t25, _t19 * _t22) * _t17_inv;
            _t44 = java.lang.Math.fma(sd[3], _t25, _t19 * _t23) * _t17_inv;
            _t45 = java.lang.Math.fma(sd[4], _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = java.lang.Math.fma(t, _t21, sd[6] * _t0);
            _t43 = java.lang.Math.fma(t, _t22, sd[5] * _t0);
            _t44 = java.lang.Math.fma(t, _t23, sd[3] * _t0);
            _t45 = java.lang.Math.fma(t, _t24, sd[4] * _t0);
        }
        double _t49 = java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, _t45 * _t45)));
        dd[0] = java.lang.Math.fma(t, otherTX - sd[0], sd[0]);
        dd[1] = java.lang.Math.fma(t, otherTY - sd[1], sd[1]);
        dd[2] = java.lang.Math.fma(t, otherTZ - sd[2], sd[2]);
        return lerp_s3232cb55_2_fma(otherSX, otherSY, otherSZ, t, dest, sd, dd, _t42, _t43, _t44, _t45, _t49, (1.0 / java.lang.Math.sqrt(_t49)));
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleTransform lerp_s3232cb55_1_mulAdd(double otherTX, double otherTY, double otherTZ, double otherSX, double otherSY, double otherSZ, double t, DoubleTransform dest, double[] sd, double[] dd, double _t0, double _t17, double _t17_inv, double _t19, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t42, _t43, _t44, _t45;
        if (_t17 > 0.0) {
            _t42 = ((sd[6]) * (_t25) + (_t19 * _t21)) * _t17_inv;
            _t43 = ((sd[5]) * (_t25) + (_t19 * _t22)) * _t17_inv;
            _t44 = ((sd[3]) * (_t25) + (_t19 * _t23)) * _t17_inv;
            _t45 = ((sd[4]) * (_t25) + (_t19 * _t24)) * _t17_inv;
        } else {
            _t42 = ((t) * (_t21) + (sd[6] * _t0));
            _t43 = ((t) * (_t22) + (sd[5] * _t0));
            _t44 = ((t) * (_t23) + (sd[3] * _t0));
            _t45 = ((t) * (_t24) + (sd[4] * _t0));
        }
        double _t49 = ((_t42) * (_t42) + (((_t43) * (_t43) + (((_t44) * (_t44) + (_t45 * _t45))))));
        dd[0] = ((t) * (otherTX - sd[0]) + (sd[0]));
        dd[1] = ((t) * (otherTY - sd[1]) + (sd[1]));
        dd[2] = ((t) * (otherTZ - sd[2]) + (sd[2]));
        return lerp_s3232cb55_2_mulAdd(otherSX, otherSY, otherSZ, t, dest, sd, dd, _t42, _t43, _t44, _t45, _t49, (1.0 / java.lang.Math.sqrt(_t49)));
    }

    /** Piece 3 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleTransform lerp_s3232cb55_2_fma(double otherSX, double otherSY, double otherSZ, double t, DoubleTransform dest, double[] sd, double[] dd, double _t42, double _t43, double _t44, double _t45, double _t49, double _t50) {
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
        dd[7] = java.lang.Math.fma(t, otherSX - sd[7], sd[7]);
        dd[8] = java.lang.Math.fma(t, otherSY - sd[8], sd[8]);
        dd[9] = java.lang.Math.fma(t, otherSZ - sd[9], sd[9]);
        return dest;
    }

    /** Piece 3 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleTransform lerp_s3232cb55_2_mulAdd(double otherSX, double otherSY, double otherSZ, double t, DoubleTransform dest, double[] sd, double[] dd, double _t42, double _t43, double _t44, double _t45, double _t49, double _t50) {
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
        dd[7] = ((t) * (otherSX - sd[7]) + (sd[7]));
        dd[8] = ((t) * (otherSY - sd[8]) + (sd[8]));
        dd[9] = ((t) * (otherSZ - sd[9]) + (sd[9]));
        return dest;
    }


    /**
     * Multiply this transform by {@code other} and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the operand, then the new transform will
     * be {@code M * R}. So when transforming a vector {@code v} with the new transform by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: the rotation of this transform must have unit length; the rotation of
     * {@code other} must have unit length.
     *
     * @param other the right operand
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform mul(DoubleTransformR other, @Mutated DoubleTransform dest) {
        double otherTX = other.tX();
        double otherTY = other.tY();
        double otherTZ = other.tZ();
        double otherRX = other.rX();
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        double otherSX = other.sX();
        double otherSY = other.sY();
        double otherSZ = other.sZ();
        if (Math.useFma()) return mul_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest);
        return mul_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest);
    }


    /**
     * Multiply this transform by ({@code otherTX}, {@code otherTY}, {@code otherTZ},
     * {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherSX},
     * {@code otherSY}, {@code otherSZ}) and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the operand, then the new transform will
     * be {@code M * R}. So when transforming a vector {@code v} with the new transform by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: the rotation of this transform must have unit length;
     * {@code (otherRX, otherRY, otherRZ, otherRW)} must have unit length.
     *
     * @param otherTX the {@code tX} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherTY the {@code tY} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherTZ the {@code tZ} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherRX the {@code rX} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherRY the {@code rY} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherRZ the {@code rZ} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherRW the {@code rW} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherSX the {@code sX} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherSY the {@code sY} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherSZ the {@code sZ} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform mul(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, @Mutated DoubleTransform dest) {
        if (Math.useFma()) return mul_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest);
        return mul_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest);
    }

    /** {@code mul} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform mul_fma(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = otherTY * sd[8];
        double _t1 = otherTX * sd[7];
        double _t2 = otherTZ * sd[9];
        double _t12 = 2.0 * java.lang.Math.fma(sd[3], _t0, -(sd[4] * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(sd[5], _t1, -(sd[3] * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(sd[4], _t2, -(sd[5] * _t0));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        double _rd4 = sd[7];
        double _rd5 = sd[8];
        double _rd6 = sd[9];
        dd[0] = java.lang.Math.fma(_rd1, _t12, java.lang.Math.fma(-_rd2, _t13, java.lang.Math.fma(_rd3, _t14, java.lang.Math.fma(otherTX, _rd4, sd[0]))));
        dd[1] = java.lang.Math.fma(_rd2, _t14, java.lang.Math.fma(-_rd0, _t12, java.lang.Math.fma(_rd3, _t13, java.lang.Math.fma(otherTY, _rd5, sd[1]))));
        dd[2] = java.lang.Math.fma(_rd0, _t13, java.lang.Math.fma(-_rd1, _t14, java.lang.Math.fma(_rd3, _t12, java.lang.Math.fma(otherTZ, _rd6, sd[2]))));
        return mul_s26e3d2ef_1_fma(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6);
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform mul_mulAdd(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = otherTY * sd[8];
        double _t1 = otherTX * sd[7];
        double _t2 = otherTZ * sd[9];
        double _t12 = 2.0 * ((sd[3]) * (_t0) - (sd[4] * _t1));
        double _t13 = 2.0 * ((sd[5]) * (_t1) - (sd[3] * _t2));
        double _t14 = 2.0 * ((sd[4]) * (_t2) - (sd[5] * _t0));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        double _rd4 = sd[7];
        double _rd5 = sd[8];
        double _rd6 = sd[9];
        dd[0] = ((_rd1) * (_t12) + (((-_rd2) * (_t13) + (((_rd3) * (_t14) + (((otherTX) * (_rd4) + (sd[0]))))))));
        dd[1] = ((_rd2) * (_t14) + (((-_rd0) * (_t12) + (((_rd3) * (_t13) + (((otherTY) * (_rd5) + (sd[1]))))))));
        dd[2] = ((_rd0) * (_t13) + (((-_rd1) * (_t14) + (((_rd3) * (_t12) + (((otherTZ) * (_rd6) + (sd[2]))))))));
        return mul_s26e3d2ef_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6);
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private DoubleTransform mul_s26e3d2ef_1_fma(double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, DoubleTransform dest, double[] dd, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6) {
        dd[3] = java.lang.Math.fma(otherRX, _rd3, otherRW * _rd0) + java.lang.Math.fma(otherRZ, _rd1, -(otherRY * _rd2));
        dd[4] = java.lang.Math.fma(otherRY, _rd3, otherRW * _rd1) + java.lang.Math.fma(otherRX, _rd2, -(otherRZ * _rd0));
        dd[5] = java.lang.Math.fma(otherRZ, _rd3, otherRW * _rd2) + java.lang.Math.fma(otherRY, _rd0, -(otherRX * _rd1));
        dd[6] = java.lang.Math.fma(otherRW, _rd3, -(otherRX * _rd0)) - java.lang.Math.fma(otherRY, _rd1, otherRZ * _rd2);
        dd[7] = otherSX * _rd4;
        dd[8] = otherSY * _rd5;
        dd[9] = otherSZ * _rd6;
        return dest;
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private DoubleTransform mul_s26e3d2ef_1_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, DoubleTransform dest, double[] dd, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6) {
        dd[3] = ((otherRX) * (_rd3) + (otherRW * _rd0)) + ((otherRZ) * (_rd1) - (otherRY * _rd2));
        dd[4] = ((otherRY) * (_rd3) + (otherRW * _rd1)) + ((otherRX) * (_rd2) - (otherRZ * _rd0));
        dd[5] = ((otherRZ) * (_rd3) + (otherRW * _rd2)) + ((otherRY) * (_rd0) - (otherRX * _rd1));
        dd[6] = ((otherRW) * (_rd3) - (otherRX * _rd0)) - ((otherRY) * (_rd1) + (otherRZ * _rd2));
        dd[7] = otherSX * _rd4;
        dd[8] = otherSY * _rd5;
        dd[9] = otherSZ * _rd6;
        return dest;
    }


    /**
     * Pre-multiply {@code other} onto this transform and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the operand, then the new transform will
     * be {@code R * M}. So when transforming a vector {@code v} with the new transform by using
     * {@code R * M * v}, the transformation of the operand will be applied last.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: the rotation of this transform must have unit length; the rotation of
     * {@code other} must have unit length.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform preMul(DoubleTransformR other, @Mutated DoubleTransform dest) {
        double otherTX = other.tX();
        double otherTY = other.tY();
        double otherTZ = other.tZ();
        double otherRX = other.rX();
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        double otherSX = other.sX();
        double otherSY = other.sY();
        double otherSZ = other.sZ();
        if (Math.useFma()) return preMul_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest);
        return preMul_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest);
    }


    /**
     * Pre-multiply ({@code otherTX}, {@code otherTY}, {@code otherTZ}, {@code otherRX},
     * {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherSX}, {@code otherSY},
     * {@code otherSZ}) onto this transform and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the operand, then the new transform will
     * be {@code R * M}. So when transforming a vector {@code v} with the new transform by using
     * {@code R * M * v}, the transformation of the operand will be applied last.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: the rotation of this transform must have unit length;
     * {@code (otherRX, otherRY, otherRZ, otherRW)} must have unit length.
     *
     * @param otherTX the {@code tX} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherTY the {@code tY} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherTZ the {@code tZ} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherRX the {@code rX} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherRY the {@code rY} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherRZ the {@code rZ} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherRW the {@code rW} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherSX the {@code sX} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherSY the {@code sY} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherSZ the {@code sZ} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform preMul(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, @Mutated DoubleTransform dest) {
        if (Math.useFma()) return preMul_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest);
        return preMul_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest);
    }

    /** {@code preMul} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform preMul_fma(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = otherSY * sd[1];
        double _t1 = otherSX * sd[0];
        double _t2 = otherSZ * sd[2];
        double _t12 = 2.0 * java.lang.Math.fma(otherRX, _t0, -(otherRY * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(otherRZ, _t1, -(otherRX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(otherRY, _t2, -(otherRZ * _t0));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = java.lang.Math.fma(otherRY, _t12, java.lang.Math.fma(-otherRZ, _t13, java.lang.Math.fma(otherRW, _t14, java.lang.Math.fma(otherSX, sd[0], otherTX))));
        dd[1] = java.lang.Math.fma(otherRZ, _t14, java.lang.Math.fma(-otherRX, _t12, java.lang.Math.fma(otherRW, _t13, java.lang.Math.fma(otherSY, sd[1], otherTY))));
        dd[2] = java.lang.Math.fma(otherRX, _t13, java.lang.Math.fma(-otherRY, _t14, java.lang.Math.fma(otherRW, _t12, java.lang.Math.fma(otherSZ, sd[2], otherTZ))));
        dd[3] = java.lang.Math.fma(otherRX, _rd3, otherRW * _rd0) + java.lang.Math.fma(otherRY, _rd2, -(otherRZ * _rd1));
        return preMul_sa55bcc5e_1_fma(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, dd, _rd0, _rd1, _rd2, _rd3);
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform preMul_mulAdd(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = otherSY * sd[1];
        double _t1 = otherSX * sd[0];
        double _t2 = otherSZ * sd[2];
        double _t12 = 2.0 * ((otherRX) * (_t0) - (otherRY * _t1));
        double _t13 = 2.0 * ((otherRZ) * (_t1) - (otherRX * _t2));
        double _t14 = 2.0 * ((otherRY) * (_t2) - (otherRZ * _t0));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = ((otherRY) * (_t12) + (((-otherRZ) * (_t13) + (((otherRW) * (_t14) + (((otherSX) * (sd[0]) + (otherTX))))))));
        dd[1] = ((otherRZ) * (_t14) + (((-otherRX) * (_t12) + (((otherRW) * (_t13) + (((otherSY) * (sd[1]) + (otherTY))))))));
        dd[2] = ((otherRX) * (_t13) + (((-otherRY) * (_t14) + (((otherRW) * (_t12) + (((otherSZ) * (sd[2]) + (otherTZ))))))));
        dd[3] = ((otherRX) * (_rd3) + (otherRW * _rd0)) + ((otherRY) * (_rd2) - (otherRZ * _rd1));
        return preMul_sa55bcc5e_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, dd, _rd0, _rd1, _rd2, _rd3);
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private DoubleTransform preMul_sa55bcc5e_1_fma(double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, DoubleTransform dest, double[] sd, double[] dd, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[4] = java.lang.Math.fma(otherRY, _rd3, otherRW * _rd1) + java.lang.Math.fma(otherRZ, _rd0, -(otherRX * _rd2));
        dd[5] = java.lang.Math.fma(otherRZ, _rd3, otherRW * _rd2) + java.lang.Math.fma(otherRX, _rd1, -(otherRY * _rd0));
        dd[6] = java.lang.Math.fma(otherRW, _rd3, -(otherRX * _rd0)) - java.lang.Math.fma(otherRY, _rd1, otherRZ * _rd2);
        dd[7] = otherSX * sd[7];
        dd[8] = otherSY * sd[8];
        dd[9] = otherSZ * sd[9];
        return dest;
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private DoubleTransform preMul_sa55bcc5e_1_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, DoubleTransform dest, double[] sd, double[] dd, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[4] = ((otherRY) * (_rd3) + (otherRW * _rd1)) + ((otherRZ) * (_rd0) - (otherRX * _rd2));
        dd[5] = ((otherRZ) * (_rd3) + (otherRW * _rd2)) + ((otherRX) * (_rd1) - (otherRY * _rd0));
        dd[6] = ((otherRW) * (_rd3) - (otherRX * _rd0)) - ((otherRY) * (_rd1) + (otherRZ * _rd2));
        dd[7] = otherSX * sd[7];
        dd[8] = otherSY * sd[8];
        dd[9] = otherSZ * sd[9];
        return dest;
    }


    /**
     * Compute the difference between this transform and {@code other}, i.e. the
     * translation-rotation-scale transformation {@code D} with {@code this * D = other}, that is
     * {@code D = this^-1 * other} and store the result in {@code dest}.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: each component of the scale of this transform must be non-zero.
     *
     * @param other the target transform, reached by composing this transform with the result
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform difference(DoubleTransformR other, @Mutated DoubleTransform dest) {
        double otherTX = other.tX();
        double otherTY = other.tY();
        double otherTZ = other.tZ();
        double otherRX = other.rX();
        double otherRY = other.rY();
        double otherRZ = other.rZ();
        double otherRW = other.rW();
        double otherSX = other.sX();
        double otherSY = other.sY();
        double otherSZ = other.sZ();
        if (Math.useFma()) return difference_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest);
        return difference_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest);
    }


    /**
     * Compute the difference between this transform and ({@code otherTX}, {@code otherTY},
     * {@code otherTZ}, {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW},
     * {@code otherSX}, {@code otherSY}, {@code otherSZ}), i.e. the translation-rotation-scale
     * transformation {@code D} with
     * {@code this * D = (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)},
     * that is
     * {@code D = this^-1 * (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * and store the result in {@code dest}.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: each component of the scale of this transform must be non-zero.
     *
     * @param otherTX the {@code tX} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherTY the {@code tY} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherTZ the {@code tZ} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherRX the {@code rX} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherRY the {@code rY} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherRZ the {@code rZ} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherRW the {@code rW} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherSX the {@code sX} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherSY the {@code sY} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param otherSZ the {@code sZ} component of the transform
     *        {@code (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform difference(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, @Mutated DoubleTransform dest) {
        if (Math.useFma()) return difference_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest);
        return difference_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest);
    }

    /** {@code difference} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform difference_fma(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double _rcp0 = 1.0 / sd[9];
        double _sp4 = _rcp0 * sd[2];
        double _sp0 = otherTZ * _rcp0;
        double _rcp1 = 1.0 / sd[7];
        double _sp3 = _rcp1 * sd[0];
        double _sp1 = otherTX * _rcp1;
        double _rcp2 = 1.0 / sd[8];
        double _sp5 = _rcp2 * sd[1];
        double _sp2 = otherTY * _rcp2;
        return difference_s50dc31b8_1_fma(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, ((DoubleTransformImpl) dest).data, _rcp0, _sp4, _sp0, _rcp1, _sp3, _sp1, _rcp2, _sp5, _sp2, 2.0 * java.lang.Math.fma(_sp0, sd[3], -(_sp1 * sd[5])), 2.0 * java.lang.Math.fma(_sp1, sd[4], -(_sp2 * sd[3])), 2.0 * java.lang.Math.fma(_sp2, sd[5], -(_sp0 * sd[4])), 2.0 * java.lang.Math.fma(_sp3, sd[5], -(_sp4 * sd[3])), 2.0 * java.lang.Math.fma(_sp5, sd[3], -(_sp3 * sd[4])), 2.0 * java.lang.Math.fma(_sp4, sd[4], -(_sp5 * sd[5])), sd[3], sd[4], sd[5], sd[6]);
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform difference_mulAdd(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double _rcp0 = 1.0 / sd[9];
        double _sp4 = _rcp0 * sd[2];
        double _sp0 = otherTZ * _rcp0;
        double _rcp1 = 1.0 / sd[7];
        double _sp3 = _rcp1 * sd[0];
        double _sp1 = otherTX * _rcp1;
        double _rcp2 = 1.0 / sd[8];
        double _sp5 = _rcp2 * sd[1];
        double _sp2 = otherTY * _rcp2;
        return difference_s50dc31b8_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, ((DoubleTransformImpl) dest).data, _rcp0, _sp4, _sp0, _rcp1, _sp3, _sp1, _rcp2, _sp5, _sp2, 2.0 * ((_sp0) * (sd[3]) - (_sp1 * sd[5])), 2.0 * ((_sp1) * (sd[4]) - (_sp2 * sd[3])), 2.0 * ((_sp2) * (sd[5]) - (_sp0 * sd[4])), 2.0 * ((_sp3) * (sd[5]) - (_sp4 * sd[3])), 2.0 * ((_sp5) * (sd[3]) - (_sp3 * sd[4])), 2.0 * ((_sp4) * (sd[4]) - (_sp5 * sd[5])), sd[3], sd[4], sd[5], sd[6]);
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleTransform difference_s50dc31b8_1_fma(double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, DoubleTransform dest, double[] dd, double _rcp0, double _sp4, double _sp0, double _rcp1, double _sp3, double _sp1, double _rcp2, double _sp5, double _sp2, double _t30, double _t31, double _t32, double _t33, double _t34, double _t35, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[0] = java.lang.Math.fma(_rd2, _t30, -(_rd1 * _t31)) + java.lang.Math.fma(_rd3, _t32, _sp1) + (java.lang.Math.fma(_rd2, _t33, -(_rd1 * _t34)) + java.lang.Math.fma(_rd3, _t35, -_sp3));
        dd[1] = java.lang.Math.fma(_rd0, _t31, -(_rd2 * _t32)) + java.lang.Math.fma(_rd3, _t30, _sp2) + (java.lang.Math.fma(_rd0, _t34, -(_rd2 * _t35)) + java.lang.Math.fma(_rd3, _t33, -_sp5));
        dd[2] = java.lang.Math.fma(_rd1, _t32, -(_rd0 * _t30)) + java.lang.Math.fma(_rd3, _t31, _sp0) + (java.lang.Math.fma(_rd1, _t35, -(_rd0 * _t33)) + java.lang.Math.fma(_rd3, _t34, -_sp4));
        dd[3] = java.lang.Math.fma(otherRX, _rd3, -(otherRW * _rd0)) + java.lang.Math.fma(otherRY, _rd2, -(otherRZ * _rd1));
        dd[4] = java.lang.Math.fma(otherRY, _rd3, -(otherRW * _rd1)) + java.lang.Math.fma(otherRZ, _rd0, -(otherRX * _rd2));
        dd[5] = java.lang.Math.fma(otherRX, _rd1, -(otherRY * _rd0)) + java.lang.Math.fma(otherRZ, _rd3, -(otherRW * _rd2));
        dd[6] = java.lang.Math.fma(otherRX, _rd0, otherRW * _rd3) - java.lang.Math.fma(-otherRZ, _rd2, -(otherRY * _rd1));
        dd[7] = otherSX * _rcp1;
        dd[8] = otherSY * _rcp2;
        dd[9] = otherSZ * _rcp0;
        return dest;
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleTransform difference_s50dc31b8_1_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, DoubleTransform dest, double[] dd, double _rcp0, double _sp4, double _sp0, double _rcp1, double _sp3, double _sp1, double _rcp2, double _sp5, double _sp2, double _t30, double _t31, double _t32, double _t33, double _t34, double _t35, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[0] = ((_rd2) * (_t30) - (_rd1 * _t31)) + ((_rd3) * (_t32) + (_sp1)) + (((_rd2) * (_t33) - (_rd1 * _t34)) + ((_rd3) * (_t35) - (_sp3)));
        dd[1] = ((_rd0) * (_t31) - (_rd2 * _t32)) + ((_rd3) * (_t30) + (_sp2)) + (((_rd0) * (_t34) - (_rd2 * _t35)) + ((_rd3) * (_t33) - (_sp5)));
        dd[2] = ((_rd1) * (_t32) - (_rd0 * _t30)) + ((_rd3) * (_t31) + (_sp0)) + (((_rd1) * (_t35) - (_rd0 * _t33)) + ((_rd3) * (_t34) - (_sp4)));
        dd[3] = ((otherRX) * (_rd3) - (otherRW * _rd0)) + ((otherRY) * (_rd2) - (otherRZ * _rd1));
        dd[4] = ((otherRY) * (_rd3) - (otherRW * _rd1)) + ((otherRZ) * (_rd0) - (otherRX * _rd2));
        dd[5] = ((otherRX) * (_rd1) - (otherRY * _rd0)) + ((otherRZ) * (_rd3) - (otherRW * _rd2));
        dd[6] = ((otherRX) * (_rd0) + (otherRW * _rd3)) - ((-otherRZ) * (_rd2) - (otherRY * _rd1));
        dd[7] = otherSX * _rcp1;
        dd[8] = otherSY * _rcp2;
        dd[9] = otherSZ * _rcp0;
        return dest;
    }


    /**
     * Invert this transform within its shear-free translation-rotation-scale form
     * ({@code inverse.mul(this)} is the identity) and store the result in {@code dest}.
     * <p>
     * The result is the exact pointwise inverse only for a rigid or uniformly scaled transform:
     * under non-uniform scale, undoing {@code transformPosition} needs a shear that this type
     * cannot hold, so {@code this.mul(inverse)} is not the identity and the inverse does not map
     * transformed points back. {@code transformPositionInverse} and {@code transformVectorInverse}
     * do that exactly for any scale. A zero scale component has no inverse: the corresponding
     * inverse scale is infinite (with the sign of the zero) and the inverse translation is not
     * finite.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform invert(@Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _rcp0 = 1.0 / sd[7];
        double _sp0 = sd[0] * _rcp0;
        double _rcp1 = 1.0 / sd[9];
        double _sp1 = sd[2] * _rcp1;
        double _rcp2 = 1.0 / sd[8];
        double _sp2 = sd[1] * _rcp2;
        double _t0 = -sd[4];
        double _t1 = -sd[5];
        double _t2 = -sd[3];
        double _t18 = 2.0 * Math.fma(_sp0, sd[5], -(_sp1 * sd[3]));
        double _t19 = 2.0 * Math.fma(_sp2, sd[3], -(_sp0 * sd[4]));
        double _t20 = 2.0 * Math.fma(_sp1, sd[4], -(_sp2 * sd[5]));
        double _rd0 = sd[6];
        dd[0] = Math.fma(sd[5], _t18, Math.fma(_t0, _t19, Math.fma(_rd0, _t20, -_sp0)));
        dd[1] = Math.fma(sd[3], _t19, Math.fma(_t1, _t20, Math.fma(_rd0, _t18, -_sp2)));
        dd[2] = Math.fma(sd[4], _t20, Math.fma(_t2, _t18, Math.fma(_rd0, _t19, -_sp1)));
        dd[3] = _t2;
        dd[4] = _t0;
        dd[5] = _t1;
        dd[6] = _rd0;
        dd[7] = _rcp0;
        dd[8] = _rcp2;
        dd[9] = _rcp1;
        return dest;
    }


    /**
     * Normalize this transform so that its rotation part has unit length, leaving its translation
     * and scale unchanged (a zero-length rotation yields the zero quaternion) and store the result
     * in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform normalize(@Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Get the Euler angles in radians of this transform, to be applied about the X, Y and Z axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: the rotation of this transform must have unit length.
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
     * Get the Euler angles in radians of this transform, to be applied about the X, Z and Y axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: the rotation of this transform must have unit length.
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
     * Get the Euler angles in radians of this transform, to be applied about the Y, X and Z axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: the rotation of this transform must have unit length.
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
     * Get the Euler angles in radians of this transform, to be applied about the Y, Z and X axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: the rotation of this transform must have unit length.
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
     * Get the Euler angles in radians of this transform, to be applied about the Z, X and Y axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: the rotation of this transform must have unit length.
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
     * Get the Euler angles in radians of this transform, to be applied about the Z, Y and X axes,
     * in that order and store the result in {@code dest}.
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
     * Valid input: the rotation of this transform must have unit length.
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
     * Get the rotation of this transform and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat getRotation(@Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = sd[3];
        dd[1] = sd[4];
        dd[2] = sd[5];
        dd[3] = sd[6];
        return dest;
    }


    /**
     * Get the scaling factors of this transform and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getScale(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[7];
        dd[1] = sd[8];
        dd[2] = sd[9];
        return dest;
    }


    /**
     * Get the translation of this transform and store the result in {@code dest}.
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
     * Set this transform to a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return this
     */
    public @Mutated DoubleTransform makeRotationAxis(double angle, Double3R axis) {
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
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a rotation of {@code angle} radians about the axis ({@code axisX},
     * {@code axisY}, {@code axisZ}).
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return this
     */
    @Mutated public DoubleTransform makeRotationAxis(double angle, double axisX, double axisY, double axisZ) {
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
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public DoubleTransform makeRotationX(double angle) {
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
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians
     * about the X, Y and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a
     * vector is rotated about the Z axis first, then Y, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this
     */
    @Mutated public DoubleTransform makeRotationXYZ(double angleX, double angleY, double angleZ) {
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
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians
     * about the X, Z and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a
     * vector is rotated about the Y axis first, then Z, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this
     */
    @Mutated public DoubleTransform makeRotationXZY(double angleX, double angleZ, double angleY) {
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
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public DoubleTransform makeRotationY(double angle) {
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
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians
     * about the Y, X and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a
     * vector is rotated about the Z axis first, then X, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this
     */
    @Mutated public DoubleTransform makeRotationYXZ(double angleY, double angleX, double angleZ) {
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
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians
     * about the Y, Z and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a
     * vector is rotated about the X axis first, then Z, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this
     */
    @Mutated public DoubleTransform makeRotationYZX(double angleY, double angleZ, double angleX) {
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
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated public DoubleTransform makeRotationZ(double angle) {
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
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians
     * about the Z, X and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a
     * vector is rotated about the Y axis first, then X, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this
     */
    @Mutated public DoubleTransform makeRotationZXY(double angleZ, double angleX, double angleY) {
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
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians
     * about the Z, Y and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a
     * vector is rotated about the X axis first, then Y, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this
     */
    @Mutated public DoubleTransform makeRotationZYX(double angleZ, double angleY, double angleX) {
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
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }


    /**
     * Set this transform to a scaling transformation that scales by {@code scale}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scale the scale factors
     * @return this
     */
    public @Mutated DoubleTransform makeScaling(Double3R scale) {
        double scaleX = scale.x();
        double scaleY = scale.y();
        double scaleZ = scale.z();
        double[] dd = this.data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 1.0;
        dd[7] = scaleX;
        dd[8] = scaleY;
        dd[9] = scaleZ;
        return this;
    }


    /**
     * Set this transform to a scaling transformation that scales by ({@code scaleX},
     * {@code scaleY}, {@code scaleZ}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scaleX the {@code x} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param scaleY the {@code y} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param scaleZ the {@code z} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @return this
     */
    @Mutated public DoubleTransform makeScaling(double scaleX, double scaleY, double scaleZ) {
        double[] dd = this.data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 1.0;
        dd[7] = scaleX;
        dd[8] = scaleY;
        dd[9] = scaleZ;
        return this;
    }


    /**
     * Set this transform to a scaling transformation that scales by {@code scale}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scale the scale factor
     * @return this
     */
    @Mutated public DoubleTransform makeScaling(double scale) {
        double[] dd = this.data;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = 1.0;
        dd[7] = scale;
        dd[8] = scale;
        dd[9] = scale;
        return this;
    }


    /**
     * Apply the rotation represented by the quaternion {@code rotation} to this transform and store
     * the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the rotation transform, then the new
     * transform will be {@code M * R}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform rotate(DoubleQuatR rotation, @Mutated DoubleTransform dest) {
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = Math.fma(rotationX, _rd3, rotationW * _rd0) + Math.fma(rotationZ, _rd1, -(rotationY * _rd2));
        dd[4] = Math.fma(rotationY, _rd3, rotationW * _rd1) + Math.fma(rotationX, _rd2, -(rotationZ * _rd0));
        dd[5] = Math.fma(rotationZ, _rd3, rotationW * _rd2) + Math.fma(rotationY, _rd0, -(rotationX * _rd1));
        dd[6] = Math.fma(rotationW, _rd3, -(rotationX * _rd0)) - Math.fma(rotationY, _rd1, rotationZ * _rd2);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Apply the rotation represented by the quaternion ({@code rotationX}, {@code rotationY},
     * {@code rotationZ}, {@code rotationW}) to this transform and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the rotation transform, then the new
     * transform will be {@code M * R}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
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
    public DoubleTransform rotate(double rotationX, double rotationY, double rotationZ, double rotationW, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = Math.fma(rotationX, _rd3, rotationW * _rd0) + Math.fma(rotationZ, _rd1, -(rotationY * _rd2));
        dd[4] = Math.fma(rotationY, _rd3, rotationW * _rd1) + Math.fma(rotationX, _rd2, -(rotationZ * _rd0));
        dd[5] = Math.fma(rotationZ, _rd3, rotationW * _rd2) + Math.fma(rotationY, _rd0, -(rotationX * _rd1));
        dd[6] = Math.fma(rotationW, _rd3, -(rotationX * _rd0)) - Math.fma(rotationY, _rd1, rotationZ * _rd2);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this transform and
     * store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the rotation transform, then the new
     * transform will be {@code M * R}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform rotateAxis(double angle, Double3R axis, @Mutated DoubleTransform dest) {
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
     * Apply a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) to this transform and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the rotation transform, then the new
     * transform will be {@code M * R}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
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
    public DoubleTransform rotateAxis(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleTransform dest) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        if (Math.useFma()) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateAxis_fma(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(_rd0, _t5, _rd3 * _t2) + java.lang.Math.fma(_rd1, _t3, -(_rd2 * _t4));
        dd[4] = java.lang.Math.fma(_rd1, _t5, _rd3 * _t4) + java.lang.Math.fma(_rd2, _t2, -(_rd0 * _t3));
        return rotateAxis_s78d6c85b_1_fma(dest, sd, dd, _t2, _t3, _t4, _t5, _rd0, _rd1, _rd2, _rd3);
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateAxis_mulAdd(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((_rd0) * (_t5) + (_rd3 * _t2)) + ((_rd1) * (_t3) - (_rd2 * _t4));
        dd[4] = ((_rd1) * (_t5) + (_rd3 * _t4)) + ((_rd2) * (_t2) - (_rd0 * _t3));
        return rotateAxis_s78d6c85b_1_mulAdd(dest, sd, dd, _t2, _t3, _t4, _t5, _rd0, _rd1, _rd2, _rd3);
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateAxis_s78d6c85b_1_fma(DoubleTransform dest, double[] sd, double[] dd, double _t2, double _t3, double _t4, double _t5, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[5] = java.lang.Math.fma(_rd2, _t5, _rd3 * _t3) + java.lang.Math.fma(_rd0, _t4, -(_rd1 * _t2));
        dd[6] = java.lang.Math.fma(_rd3, _t5, -(_rd0 * _t2)) - java.lang.Math.fma(_rd1, _t4, _rd2 * _t3);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateAxis_s78d6c85b_1_mulAdd(DoubleTransform dest, double[] sd, double[] dd, double _t2, double _t3, double _t4, double _t5, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[5] = ((_rd2) * (_t5) + (_rd3 * _t3)) + ((_rd0) * (_t4) - (_rd1 * _t2));
        dd[6] = ((_rd3) * (_t5) - (_rd0 * _t2)) - ((_rd1) * (_t4) + (_rd2 * _t3));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Apply a rotation of {@code angle} radians about the X axis to this transform and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the rotation transform, then the new
     * transform will be {@code M * R}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform rotateX(double angle, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = Math.fma(_rd0, _t2, _rd3 * _t1);
        dd[4] = Math.fma(_rd1, _t2, _rd2 * _t1);
        dd[5] = Math.fma(_rd2, _t2, -(_rd1 * _t1));
        dd[6] = Math.fma(_rd3, _t2, -(_rd0 * _t1));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this transform and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the rotation transform, then the new
     * transform will be {@code M * R}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform rotateXYZ(double angleX, double angleY, double angleZ, @Mutated DoubleTransform dest) {
        if (Math.useFma()) return rotateXYZ_fma(angleX, angleY, angleZ, dest);
        return rotateXYZ_mulAdd(angleX, angleY, angleZ, dest);
    }

    /** {@code rotateXYZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateXYZ_fma(double angleX, double angleY, double angleZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        double _t21 = java.lang.Math.fma(_t14, _t8, -(_t9 * _t5));
        double _t22 = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(_rd0, _t21, _rd3 * _t19) + java.lang.Math.fma(_rd1, _t20, -(_rd2 * _t22));
        dd[4] = java.lang.Math.fma(_rd1, _t21, _rd3 * _t22) + java.lang.Math.fma(_rd2, _t19, -(_rd0 * _t20));
        return rotateXYZ_s1482cb77_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3);
    }

    /** {@code rotateXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateXYZ_mulAdd(double angleX, double angleY, double angleZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((_rd0) * (_t21) + (_rd3 * _t19)) + ((_rd1) * (_t20) - (_rd2 * _t22));
        dd[4] = ((_rd1) * (_t21) + (_rd3 * _t22)) + ((_rd2) * (_t19) - (_rd0 * _t20));
        return rotateXYZ_s1482cb77_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3);
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateXYZ_s1482cb77_1_fma(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[5] = java.lang.Math.fma(_rd2, _t21, _rd3 * _t20) + java.lang.Math.fma(_rd0, _t22, -(_rd1 * _t19));
        dd[6] = java.lang.Math.fma(_rd3, _t21, -(_rd0 * _t19)) - java.lang.Math.fma(_rd1, _t22, _rd2 * _t20);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateXYZ_s1482cb77_1_mulAdd(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[5] = ((_rd2) * (_t21) + (_rd3 * _t20)) + ((_rd0) * (_t22) - (_rd1 * _t19));
        dd[6] = ((_rd3) * (_t21) - (_rd0 * _t19)) - ((_rd1) * (_t22) + (_rd2 * _t20));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this transform and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the rotation transform, then the new
     * transform will be {@code M * R}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform rotateXZY(double angleX, double angleZ, double angleY, @Mutated DoubleTransform dest) {
        if (Math.useFma()) return rotateXZY_fma(angleX, angleZ, angleY, dest);
        return rotateXZY_mulAdd(angleX, angleZ, angleY, dest);
    }

    /** {@code rotateXZY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateXZY_fma(double angleX, double angleZ, double angleY, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(_rd0, _t19, _rd3 * _t21) + java.lang.Math.fma(_rd1, _t20, -(_rd2 * _t22));
        dd[4] = java.lang.Math.fma(_rd1, _t19, _rd3 * _t22) + java.lang.Math.fma(_rd2, _t21, -(_rd0 * _t20));
        return rotateXZY_s793a461b_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3);
    }

    /** {@code rotateXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateXZY_mulAdd(double angleX, double angleZ, double angleY, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((_rd0) * (_t19) + (_rd3 * _t21)) + ((_rd1) * (_t20) - (_rd2 * _t22));
        dd[4] = ((_rd1) * (_t19) + (_rd3 * _t22)) + ((_rd2) * (_t21) - (_rd0 * _t20));
        return rotateXZY_s793a461b_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3);
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateXZY_s793a461b_1_fma(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[5] = java.lang.Math.fma(_rd2, _t19, _rd3 * _t20) + java.lang.Math.fma(_rd0, _t22, -(_rd1 * _t21));
        dd[6] = java.lang.Math.fma(_rd3, _t19, -(_rd0 * _t21)) - java.lang.Math.fma(_rd1, _t22, _rd2 * _t20);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateXZY_s793a461b_1_mulAdd(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[5] = ((_rd2) * (_t19) + (_rd3 * _t20)) + ((_rd0) * (_t22) - (_rd1 * _t21));
        dd[6] = ((_rd3) * (_t19) - (_rd0 * _t21)) - ((_rd1) * (_t22) + (_rd2 * _t20));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Apply a rotation of {@code angle} radians about the Y axis to this transform and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the rotation transform, then the new
     * transform will be {@code M * R}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform rotateY(double angle, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = Math.fma(_rd0, _t2, -(_rd2 * _t1));
        dd[4] = Math.fma(_rd1, _t2, _rd3 * _t1);
        dd[5] = Math.fma(_rd0, _t1, _rd2 * _t2);
        dd[6] = Math.fma(_rd3, _t2, -(_rd1 * _t1));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this transform and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the rotation transform, then the new
     * transform will be {@code M * R}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform rotateYXZ(double angleY, double angleX, double angleZ, @Mutated DoubleTransform dest) {
        if (Math.useFma()) return rotateYXZ_fma(angleY, angleX, angleZ, dest);
        return rotateYXZ_mulAdd(angleY, angleX, angleZ, dest);
    }

    /** {@code rotateYXZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateYXZ_fma(double angleY, double angleX, double angleZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(_rd0, _t19, _rd3 * _t20) + java.lang.Math.fma(_rd1, _t21, -(_rd2 * _t22));
        dd[4] = java.lang.Math.fma(_rd1, _t19, _rd3 * _t22) + java.lang.Math.fma(_rd2, _t20, -(_rd0 * _t21));
        return rotateYXZ_sf37d62e3_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3);
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateYXZ_mulAdd(double angleY, double angleX, double angleZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((_rd0) * (_t19) + (_rd3 * _t20)) + ((_rd1) * (_t21) - (_rd2 * _t22));
        dd[4] = ((_rd1) * (_t19) + (_rd3 * _t22)) + ((_rd2) * (_t20) - (_rd0 * _t21));
        return rotateYXZ_sf37d62e3_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3);
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateYXZ_sf37d62e3_1_fma(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[5] = java.lang.Math.fma(_rd2, _t19, _rd3 * _t21) + java.lang.Math.fma(_rd0, _t22, -(_rd1 * _t20));
        dd[6] = java.lang.Math.fma(_rd3, _t19, -(_rd0 * _t20)) - java.lang.Math.fma(_rd1, _t22, _rd2 * _t21);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateYXZ_sf37d62e3_1_mulAdd(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[5] = ((_rd2) * (_t19) + (_rd3 * _t21)) + ((_rd0) * (_t22) - (_rd1 * _t20));
        dd[6] = ((_rd3) * (_t19) - (_rd0 * _t20)) - ((_rd1) * (_t22) + (_rd2 * _t21));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this transform and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the rotation transform, then the new
     * transform will be {@code M * R}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform rotateYZX(double angleY, double angleZ, double angleX, @Mutated DoubleTransform dest) {
        if (Math.useFma()) return rotateYZX_fma(angleY, angleZ, angleX, dest);
        return rotateYZX_mulAdd(angleY, angleZ, angleX, dest);
    }

    /** {@code rotateYZX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateYZX_fma(double angleY, double angleZ, double angleX, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        double _t21 = java.lang.Math.fma(_t14, _t8, -(_t9 * _t5));
        double _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(_rd0, _t21, _rd3 * _t19) + java.lang.Math.fma(_rd1, _t22, -(_rd2 * _t20));
        dd[4] = java.lang.Math.fma(_rd1, _t21, _rd3 * _t20) + java.lang.Math.fma(_rd2, _t19, -(_rd0 * _t22));
        return rotateYZX_s155e0a53_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3);
    }

    /** {@code rotateYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateYZX_mulAdd(double angleY, double angleZ, double angleX, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((_rd0) * (_t21) + (_rd3 * _t19)) + ((_rd1) * (_t22) - (_rd2 * _t20));
        dd[4] = ((_rd1) * (_t21) + (_rd3 * _t20)) + ((_rd2) * (_t19) - (_rd0 * _t22));
        return rotateYZX_s155e0a53_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3);
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateYZX_s155e0a53_1_fma(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[5] = java.lang.Math.fma(_rd2, _t21, _rd3 * _t22) + java.lang.Math.fma(_rd0, _t20, -(_rd1 * _t19));
        dd[6] = java.lang.Math.fma(_rd3, _t21, -(_rd0 * _t19)) - java.lang.Math.fma(_rd1, _t20, _rd2 * _t22);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateYZX_s155e0a53_1_mulAdd(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[5] = ((_rd2) * (_t21) + (_rd3 * _t22)) + ((_rd0) * (_t20) - (_rd1 * _t19));
        dd[6] = ((_rd3) * (_t21) - (_rd0 * _t19)) - ((_rd1) * (_t20) + (_rd2 * _t22));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Apply a rotation of {@code angle} radians about the Z axis to this transform and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the rotation transform, then the new
     * transform will be {@code M * R}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform rotateZ(double angle, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = Math.fma(_rd0, _t2, _rd1 * _t1);
        dd[4] = Math.fma(_rd1, _t2, -(_rd0 * _t1));
        dd[5] = Math.fma(_rd2, _t2, _rd3 * _t1);
        dd[6] = Math.fma(_rd3, _t2, -(_rd2 * _t1));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this transform and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the rotation transform, then the new
     * transform will be {@code M * R}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform rotateZXY(double angleZ, double angleX, double angleY, @Mutated DoubleTransform dest) {
        if (Math.useFma()) return rotateZXY_fma(angleZ, angleX, angleY, dest);
        return rotateZXY_mulAdd(angleZ, angleX, angleY, dest);
    }

    /** {@code rotateZXY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateZXY_fma(double angleZ, double angleX, double angleY, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        double _t21 = java.lang.Math.fma(_t14, _t8, -(_t9 * _t5));
        double _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(_rd0, _t21, _rd3 * _t22) + java.lang.Math.fma(_rd1, _t19, -(_rd2 * _t20));
        dd[4] = java.lang.Math.fma(_rd1, _t21, _rd3 * _t20) + java.lang.Math.fma(_rd2, _t22, -(_rd0 * _t19));
        return rotateZXY_s566c1933_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3);
    }

    /** {@code rotateZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateZXY_mulAdd(double angleZ, double angleX, double angleY, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((_rd0) * (_t21) + (_rd3 * _t22)) + ((_rd1) * (_t19) - (_rd2 * _t20));
        dd[4] = ((_rd1) * (_t21) + (_rd3 * _t20)) + ((_rd2) * (_t22) - (_rd0 * _t19));
        return rotateZXY_s566c1933_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3);
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateZXY_s566c1933_1_fma(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[5] = java.lang.Math.fma(_rd2, _t21, _rd3 * _t19) + java.lang.Math.fma(_rd0, _t20, -(_rd1 * _t22));
        dd[6] = java.lang.Math.fma(_rd3, _t21, -(_rd0 * _t22)) - java.lang.Math.fma(_rd1, _t20, _rd2 * _t19);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateZXY_s566c1933_1_mulAdd(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[5] = ((_rd2) * (_t21) + (_rd3 * _t19)) + ((_rd0) * (_t20) - (_rd1 * _t22));
        dd[6] = ((_rd3) * (_t21) - (_rd0 * _t22)) - ((_rd1) * (_t20) + (_rd2 * _t19));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this transform and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code R} the rotation transform, then the new
     * transform will be {@code M * R}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * R * v}, the rotation will be applied first.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform rotateZYX(double angleZ, double angleY, double angleX, @Mutated DoubleTransform dest) {
        if (Math.useFma()) return rotateZYX_fma(angleZ, angleY, angleX, dest);
        return rotateZYX_mulAdd(angleZ, angleY, angleX, dest);
    }

    /** {@code rotateZYX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateZYX_fma(double angleZ, double angleY, double angleX, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = java.lang.Math.fma(_rd0, _t19, _rd3 * _t21) + java.lang.Math.fma(_rd1, _t22, -(_rd2 * _t20));
        dd[4] = java.lang.Math.fma(_rd1, _t19, _rd3 * _t20) + java.lang.Math.fma(_rd2, _t21, -(_rd0 * _t22));
        return rotateZYX_sb3442857_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3);
    }

    /** {@code rotateZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateZYX_mulAdd(double angleZ, double angleY, double angleX, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = ((_rd0) * (_t19) + (_rd3 * _t21)) + ((_rd1) * (_t22) - (_rd2 * _t20));
        dd[4] = ((_rd1) * (_t19) + (_rd3 * _t20)) + ((_rd2) * (_t21) - (_rd0 * _t22));
        return rotateZYX_sb3442857_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, _rd0, _rd1, _rd2, _rd3);
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateZYX_sb3442857_1_fma(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[5] = java.lang.Math.fma(_rd2, _t19, _rd3 * _t22) + java.lang.Math.fma(_rd0, _t20, -(_rd1 * _t21));
        dd[6] = java.lang.Math.fma(_rd3, _t19, -(_rd0 * _t21)) - java.lang.Math.fma(_rd1, _t20, _rd2 * _t22);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateZYX_sb3442857_1_mulAdd(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _rd0, double _rd1, double _rd2, double _rd3) {
        dd[5] = ((_rd2) * (_t19) + (_rd3 * _t22)) + ((_rd0) * (_t20) - (_rd1 * _t21));
        dd[6] = ((_rd3) * (_t19) - (_rd0 * _t21)) - ((_rd1) * (_t20) + (_rd2 * _t22));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Apply a scaling by {@code scale} to this transform and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code S} the scaling transform, then the new
     * transform will be {@code M * S}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scale the scale factors
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform scale(Double3R scale, @Mutated DoubleTransform dest) {
        double scaleX = scale.x();
        double scaleY = scale.y();
        double scaleZ = scale.z();
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = scaleX * sd[7];
        dd[8] = scaleY * sd[8];
        dd[9] = scaleZ * sd[9];
        return dest;
    }


    /**
     * Apply a scaling by ({@code scaleX}, {@code scaleY}, {@code scaleZ}) to this transform and
     * store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code S} the scaling transform, then the new
     * transform will be {@code M * S}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scaleX the {@code x} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param scaleY the {@code y} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param scaleZ the {@code z} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform scale(double scaleX, double scaleY, double scaleZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = scaleX * sd[7];
        dd[8] = scaleY * sd[8];
        dd[9] = scaleZ * sd[9];
        return dest;
    }


    /**
     * Apply a scaling by {@code scale} to this transform and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code S} the scaling transform, then the new
     * transform will be {@code M * S}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scale the scale factor
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform scale(double scale, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = scale * sd[7];
        dd[8] = scale * sd[8];
        dd[9] = scale * sd[9];
        return dest;
    }


    /**
     * Apply a translation by {@code translation} to this transform and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code T} the translation transform, then the new
     * transform will be {@code M * T}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * T * v}, the translation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform translate(Double3R translation, @Mutated DoubleTransform dest) {
        return translate(translation.x(), translation.y(), translation.z(), dest);
    }


    /**
     * Apply a translation by ({@code translationX}, {@code translationY}, {@code translationZ}) to
     * this transform and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code T} the translation transform, then the new
     * transform will be {@code M * T}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * T * v}, the translation will be applied first.
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
    public DoubleTransform translate(double translationX, double translationY, double translationZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = sd[8] * translationY;
        double _t1 = sd[7] * translationX;
        double _t2 = sd[9] * translationZ;
        double _t12 = 2.0 * Math.fma(sd[3], _t0, -(sd[4] * _t1));
        double _t13 = 2.0 * Math.fma(sd[5], _t1, -(sd[3] * _t2));
        double _t14 = 2.0 * Math.fma(sd[4], _t2, -(sd[5] * _t0));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        double _rd4 = sd[7];
        double _rd5 = sd[8];
        double _rd6 = sd[9];
        dd[0] = Math.fma(_rd1, _t12, Math.fma(-_rd2, _t13, Math.fma(_rd3, _t14, Math.fma(_rd4, translationX, sd[0]))));
        dd[1] = Math.fma(_rd2, _t14, Math.fma(-_rd0, _t12, Math.fma(_rd3, _t13, Math.fma(_rd5, translationY, sd[1]))));
        dd[2] = Math.fma(_rd0, _t13, Math.fma(-_rd1, _t14, Math.fma(_rd3, _t12, Math.fma(_rd6, translationZ, sd[2]))));
        dd[3] = _rd0;
        dd[4] = _rd1;
        dd[5] = _rd2;
        dd[6] = _rd3;
        dd[7] = _rd4;
        dd[8] = _rd5;
        dd[9] = _rd6;
        return dest;
    }


    /**
     * Transform {@code v} by this transform and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
        double _t0 = sd[8] * vY;
        double _t1 = sd[7] * vX;
        double _t2 = sd[9] * vZ;
        double _t12 = 2.0 * Math.fma(sd[3], _t0, -(sd[4] * _t1));
        double _t13 = 2.0 * Math.fma(sd[5], _t1, -(sd[3] * _t2));
        double _t14 = 2.0 * Math.fma(sd[4], _t2, -(sd[5] * _t0));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(_rd1, _t12, Math.fma(-_rd2, _t13, Math.fma(_rd3, _t14, Math.fma(sd[7], vX, sd[0]))));
        dd[1] = Math.fma(_rd2, _t14, Math.fma(-_rd0, _t12, Math.fma(_rd3, _t13, Math.fma(sd[8], vY, sd[1]))));
        dd[2] = Math.fma(_rd0, _t13, Math.fma(-_rd1, _t14, Math.fma(_rd3, _t12, Math.fma(sd[9], vZ, sd[2]))));
        return dest;
    }


    /**
     * Transform ({@code vX}, {@code vY}, {@code vZ}) by this transform and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
        double _t0 = sd[8] * vY;
        double _t1 = sd[7] * vX;
        double _t2 = sd[9] * vZ;
        double _t12 = 2.0 * Math.fma(sd[3], _t0, -(sd[4] * _t1));
        double _t13 = 2.0 * Math.fma(sd[5], _t1, -(sd[3] * _t2));
        double _t14 = 2.0 * Math.fma(sd[4], _t2, -(sd[5] * _t0));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(_rd1, _t12, Math.fma(-_rd2, _t13, Math.fma(_rd3, _t14, Math.fma(sd[7], vX, sd[0]))));
        dd[1] = Math.fma(_rd2, _t14, Math.fma(-_rd0, _t12, Math.fma(_rd3, _t13, Math.fma(sd[8], vY, sd[1]))));
        dd[2] = Math.fma(_rd0, _t13, Math.fma(-_rd1, _t14, Math.fma(_rd3, _t12, Math.fma(sd[9], vZ, sd[2]))));
        return dest;
    }


    /**
     * Transform the given direction by the rotation part of this transform, ignoring translation
     * and scale and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(_rd1, _t9, Math.fma(-_rd2, _t10, Math.fma(_rd3, _t11, vX)));
        dd[1] = Math.fma(_rd2, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, vY)));
        dd[2] = Math.fma(_rd0, _t10, Math.fma(-_rd1, _t11, Math.fma(_rd3, _t9, vZ)));
        return dest;
    }


    /**
     * Transform the given direction by the rotation part of this transform, ignoring translation
     * and scale and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(_rd1, _t9, Math.fma(-_rd2, _t10, Math.fma(_rd3, _t11, vX)));
        dd[1] = Math.fma(_rd2, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, vY)));
        dd[2] = Math.fma(_rd0, _t10, Math.fma(-_rd1, _t11, Math.fma(_rd3, _t9, vZ)));
        return dest;
    }


    /**
     * Transform the given direction by the inverse of this transform's rotation (world to local),
     * ignoring translation and scale, without materializing {@code invert()} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(_rd2, _t9, Math.fma(-_rd1, _t10, Math.fma(_rd3, _t11, vX)));
        dd[1] = Math.fma(_rd0, _t10, Math.fma(-_rd2, _t11, Math.fma(_rd3, _t9, vY)));
        dd[2] = Math.fma(_rd1, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, vZ)));
        return dest;
    }


    /**
     * Transform the given direction by the inverse of this transform's rotation (world to local),
     * ignoring translation and scale, without materializing {@code invert()} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(_rd2, _t9, Math.fma(-_rd1, _t10, Math.fma(_rd3, _t11, vX)));
        dd[1] = Math.fma(_rd0, _t10, Math.fma(-_rd2, _t11, Math.fma(_rd3, _t9, vY)));
        dd[2] = Math.fma(_rd1, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, vZ)));
        return dest;
    }


    /**
     * Transform {@code p} by the inverse of this transform and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
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
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(_rd2, _t12, Math.fma(-_rd1, _t13, Math.fma(_rd3, _t14, _t1))) / sd[7];
        dd[1] = Math.fma(_rd0, _t13, Math.fma(-_rd2, _t14, Math.fma(_rd3, _t12, _t2))) / sd[8];
        dd[2] = Math.fma(_rd1, _t14, Math.fma(-_rd0, _t12, Math.fma(_rd3, _t13, _t0))) / sd[9];
        return dest;
    }


    /**
     * Transform ({@code pX}, {@code pY}, {@code pZ}) by the inverse of this transform and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
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
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(_rd2, _t12, Math.fma(-_rd1, _t13, Math.fma(_rd3, _t14, _t1))) / sd[7];
        dd[1] = Math.fma(_rd0, _t13, Math.fma(-_rd2, _t14, Math.fma(_rd3, _t12, _t2))) / sd[8];
        dd[2] = Math.fma(_rd1, _t14, Math.fma(-_rd0, _t12, Math.fma(_rd3, _t13, _t0))) / sd[9];
        return dest;
    }


    /**
     * Transform the given position by this transform, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPosition(Double3R v, @Mutated Double3 dest) {
        return transform(v, dest);
    }


    /**
     * Transform the given position by this transform, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
        double _t0 = sd[8] * vY;
        double _t1 = sd[7] * vX;
        double _t2 = sd[9] * vZ;
        double _t12 = 2.0 * Math.fma(sd[3], _t0, -(sd[4] * _t1));
        double _t13 = 2.0 * Math.fma(sd[5], _t1, -(sd[3] * _t2));
        double _t14 = 2.0 * Math.fma(sd[4], _t2, -(sd[5] * _t0));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(_rd1, _t12, Math.fma(-_rd2, _t13, Math.fma(_rd3, _t14, Math.fma(sd[7], vX, sd[0]))));
        dd[1] = Math.fma(_rd2, _t14, Math.fma(-_rd0, _t12, Math.fma(_rd3, _t13, Math.fma(sd[8], vY, sd[1]))));
        dd[2] = Math.fma(_rd0, _t13, Math.fma(-_rd1, _t14, Math.fma(_rd3, _t12, Math.fma(sd[9], vZ, sd[2]))));
        return dest;
    }


    /**
     * Transform the given position by the inverse of this transform (world to local), without
     * materializing {@code invert()} and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPositionInverse(Double3R p, @Mutated Double3 dest) {
        return transformInverse(p, dest);
    }


    /**
     * Transform the given position by the inverse of this transform (world to local), without
     * materializing {@code invert()} and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
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
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(_rd2, _t12, Math.fma(-_rd1, _t13, Math.fma(_rd3, _t14, _t1))) / sd[7];
        dd[1] = Math.fma(_rd0, _t13, Math.fma(-_rd2, _t14, Math.fma(_rd3, _t12, _t2))) / sd[8];
        dd[2] = Math.fma(_rd1, _t14, Math.fma(-_rd0, _t12, Math.fma(_rd3, _t13, _t0))) / sd[9];
        return dest;
    }


    /**
     * Transform the given vector by the linear part of this transform, i.e. apply its scale and
     * rotation but not its translation and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformVector(Double3R v, @Mutated Double3 dest) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[9] * vZ;
        double _t1 = sd[8] * vY;
        double _t2 = sd[7] * vX;
        double _t12 = 2.0 * Math.fma(sd[4], _t0, -(sd[5] * _t1));
        double _t13 = 2.0 * Math.fma(sd[3], _t1, -(sd[4] * _t2));
        double _t14 = 2.0 * Math.fma(sd[5], _t2, -(sd[3] * _t0));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(sd[7], vX, Math.fma(_rd3, _t12, Math.fma(_rd1, _t13, -(_rd2 * _t14))));
        dd[1] = Math.fma(sd[8], vY, Math.fma(_rd3, _t14, Math.fma(_rd2, _t12, -(_rd0 * _t13))));
        dd[2] = Math.fma(sd[9], vZ, Math.fma(_rd3, _t13, Math.fma(_rd0, _t14, -(_rd1 * _t12))));
        return dest;
    }


    /**
     * Transform the given vector by the linear part of this transform, i.e. apply its scale and
     * rotation but not its translation and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformVector(double vX, double vY, double vZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[9] * vZ;
        double _t1 = sd[8] * vY;
        double _t2 = sd[7] * vX;
        double _t12 = 2.0 * Math.fma(sd[4], _t0, -(sd[5] * _t1));
        double _t13 = 2.0 * Math.fma(sd[3], _t1, -(sd[4] * _t2));
        double _t14 = 2.0 * Math.fma(sd[5], _t2, -(sd[3] * _t0));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(sd[7], vX, Math.fma(_rd3, _t12, Math.fma(_rd1, _t13, -(_rd2 * _t14))));
        dd[1] = Math.fma(sd[8], vY, Math.fma(_rd3, _t14, Math.fma(_rd2, _t12, -(_rd0 * _t13))));
        dd[2] = Math.fma(sd[9], vZ, Math.fma(_rd3, _t13, Math.fma(_rd0, _t14, -(_rd1 * _t12))));
        return dest;
    }


    /**
     * Transform the given vector by the inverse of this transform's linear part (world to local),
     * i.e. undo its rotation and scale but not its translation, without materializing
     * {@code invert()} and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformVectorInverse(Double3R v, @Mutated Double3 dest) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[3], vZ, -(sd[5] * vX));
        double _t10 = 2.0 * Math.fma(sd[4], vX, -(sd[3] * vY));
        double _t11 = 2.0 * Math.fma(sd[5], vY, -(sd[4] * vZ));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(_rd2, _t9, Math.fma(-_rd1, _t10, Math.fma(_rd3, _t11, vX))) / sd[7];
        dd[1] = Math.fma(_rd0, _t10, Math.fma(-_rd2, _t11, Math.fma(_rd3, _t9, vY))) / sd[8];
        dd[2] = Math.fma(_rd1, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, vZ))) / sd[9];
        return dest;
    }


    /**
     * Transform the given vector by the inverse of this transform's linear part (world to local),
     * i.e. undo its rotation and scale but not its translation, without materializing
     * {@code invert()} and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformVectorInverse(double vX, double vY, double vZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * Math.fma(sd[3], vZ, -(sd[5] * vX));
        double _t10 = 2.0 * Math.fma(sd[4], vX, -(sd[3] * vY));
        double _t11 = 2.0 * Math.fma(sd[5], vY, -(sd[4] * vZ));
        double _rd0 = sd[3];
        double _rd1 = sd[4];
        double _rd2 = sd[5];
        double _rd3 = sd[6];
        dd[0] = Math.fma(_rd2, _t9, Math.fma(-_rd1, _t10, Math.fma(_rd3, _t11, vX))) / sd[7];
        dd[1] = Math.fma(_rd0, _t10, Math.fma(-_rd2, _t11, Math.fma(_rd3, _t9, vY))) / sd[8];
        dd[2] = Math.fma(_rd1, _t11, Math.fma(-_rd0, _t9, Math.fma(_rd3, _t10, vZ))) / sd[9];
        return dest;
    }

    public double tX() { return data[0]; }
    public double tY() { return data[1]; }
    public double tZ() { return data[2]; }
    public double rX() { return data[3]; }
    public double rY() { return data[4]; }
    public double rZ() { return data[5]; }
    public double rW() { return data[6]; }
    public double sX() { return data[7]; }
    public double sY() { return data[8]; }
    public double sZ() { return data[9]; }

    @Override public String toString() {
        return "DoubleTransform(" + tX() + ", " + tY() + ", " + tZ() + ", " + rX() + ", " + rY() + ", " + rZ() + ", " + rW() + ", " + sX() + ", " + sY() + ", " + sZ() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleTransformImpl)) return false;
        DoubleTransformImpl o = (DoubleTransformImpl) obj;
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
            && Double.isFinite(data[6])
            && Double.isFinite(data[7])
            && Double.isFinite(data[8])
            && Double.isFinite(data[9]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1])
            || Double.isNaN(data[2])
            || Double.isNaN(data[3])
            || Double.isNaN(data[4])
            || Double.isNaN(data[5])
            || Double.isNaN(data[6])
            || Double.isNaN(data[7])
            || Double.isNaN(data[8])
            || Double.isNaN(data[9]);
    }

    @Override public boolean equalsEpsilon(DoubleTransformR other, double epsilon) {
        return java.lang.Math.abs(data[0] - other.tX()) <= epsilon
            && java.lang.Math.abs(data[1] - other.tY()) <= epsilon
            && java.lang.Math.abs(data[2] - other.tZ()) <= epsilon
            && java.lang.Math.abs(data[3] - other.rX()) <= epsilon
            && java.lang.Math.abs(data[4] - other.rY()) <= epsilon
            && java.lang.Math.abs(data[5] - other.rZ()) <= epsilon
            && java.lang.Math.abs(data[6] - other.rW()) <= epsilon
            && java.lang.Math.abs(data[7] - other.sX()) <= epsilon
            && java.lang.Math.abs(data[8] - other.sY()) <= epsilon
            && java.lang.Math.abs(data[9] - other.sZ()) <= epsilon;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        dest[offset + 6] = this.data[6];
        dest[offset + 7] = this.data[7];
        dest[offset + 8] = this.data[8];
        dest[offset + 9] = this.data[9];
        return dest;
    }
    public @Mutated DoubleTransform load(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
        this.data[7] = src[offset + 7];
        this.data[8] = src[offset + 8];
        this.data[9] = src[offset + 9];
        return this;
    }
    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 10) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 10);
        return buf;
    }
    @Mutated public DoubleTransform load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public DoubleTransform loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public DoubleTransform loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 10) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 10);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 80) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 80);
        return buf;
    }
    public DoubleTransform load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public DoubleTransform loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public DoubleTransform loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 80) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleTransform r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 80);
        return r;
    }
    public DoubleTransform storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public DoubleTransform loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public DoubleTransform load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public DoubleTransform load(long offset, MemorySegment src) {
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
        dest[offset + 7] = (float) this.data[7];
        dest[offset + 8] = (float) this.data[8];
        dest[offset + 9] = (float) this.data[9];
        return dest;
    }
    public @Mutated DoubleTransform load(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
        this.data[7] = src[offset + 7];
        this.data[8] = src[offset + 8];
        this.data[9] = src[offset + 9];
        return this;
    }
    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 10) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 10);
        return buf;
    }
    @Mutated public DoubleTransform load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public DoubleTransform loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public DoubleTransform loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 10) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 10);
        return this;
    }
    public ByteBuffer storeFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 40) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 40);
        return buf;
    }
    public DoubleTransform loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, buf.position(), buf);
    }
    public DoubleTransform loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public DoubleTransform loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 40) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleTransform r = StoreLoad.BB_OPS.loadFloatAbsolute(this, pos, buf);
        buf.position(pos + 40);
        return r;
    }
    public DoubleTransform storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public DoubleTransform loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }
    public MemorySegment storeFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }
    public MemorySegment storeFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }
    @Mutated public DoubleTransform loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(this, 0L, src); }
    public DoubleTransform loadFloat(long offset, MemorySegment src) {
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
