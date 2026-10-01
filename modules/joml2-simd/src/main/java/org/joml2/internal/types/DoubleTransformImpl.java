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
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link DoubleTransform} backed by a {@code double[]} array, with
 * Vector API SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoubleTransformImpl implements DoubleTransform {
    public double[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoubleTransformSegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleTransformSegOpsUnsafe()
            : new DoubleTransformSegOpsMS();
        static final DoubleTransformBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new DoubleTransformBbOpsUnsafe()
            : new DoubleTransformBbOpsApi();
        static final DoubleTransformRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
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
        double[] srcData = ((DoubleTransformImpl) src).data;
        double[] dd = this.data = new double[10];
        dd[0] = srcData[0];
        dd[1] = srcData[1];
        dd[2] = srcData[2];
        dd[3] = srcData[3];
        dd[4] = srcData[4];
        dd[5] = srcData[5];
        dd[6] = srcData[6];
        dd[7] = srcData[7];
        dd[8] = srcData[8];
        dd[9] = srcData[9];
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
    @Mutated public DoubleTransform makeFromAxisAngle(Double3R axis, double angle, Double3R translation) {
        double[] axisData = ((Double3Impl) axis).data;
        double[] translationData = ((Double3Impl) translation).data;
        double axisX = axisData[0];
        double axisY = axisData[1];
        double axisZ = axisData[2];
        double translationY = translationData[1];
        double translationZ = translationData[2];
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sinWithCos(_t0);
        dd[0] = translationData[0];
        dd[1] = translationY;
        dd[2] = translationZ;
        dd[3] = axisX * _t1;
        dd[4] = axisY * _t1;
        dd[5] = axisZ * _t1;
        dd[6] = Math.cosWithSin(_t0);
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
        double _t1 = Math.sinWithCos(_t0);
        dd[0] = translationX;
        dd[1] = translationY;
        dd[2] = translationZ;
        dd[3] = axisX * _t1;
        dd[4] = axisY * _t1;
        dd[5] = axisZ * _t1;
        dd[6] = Math.cosWithSin(_t0);
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
    @Mutated public DoubleTransform makeTranslationRotation(Double3R translation, DoubleQuatR rotation) {
        double[] translationData = ((Double3Impl) translation).data;
        double[] rotationData = ((DoubleQuatImpl) rotation).data;
        double translationY = translationData[1];
        double translationZ = translationData[2];
        double rotationX = rotationData[0];
        double rotationY = rotationData[1];
        double rotationZ = rotationData[2];
        double rotationW = rotationData[3];
        double[] dd = this.data;
        dd[0] = translationData[0];
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
    @Mutated public DoubleTransform makeTranslationRotationScale(Double3R translation, DoubleQuatR rotation, Double3R scale) {
        double[] translationData = ((Double3Impl) translation).data;
        double[] rotationData = ((DoubleQuatImpl) rotation).data;
        double[] scaleData = ((Double3Impl) scale).data;
        double translationY = translationData[1];
        double translationZ = translationData[2];
        double rotationX = rotationData[0];
        double rotationY = rotationData[1];
        double rotationZ = rotationData[2];
        double rotationW = rotationData[3];
        double scaleX = scaleData[0];
        double scaleY = scaleData[1];
        double scaleZ = scaleData[2];
        double[] dd = this.data;
        dd[0] = translationData[0];
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
    @Mutated public DoubleTransform set(DoubleTransformR v) {
        double[] vData = ((DoubleTransformImpl) v).data;
        double vTY = vData[1];
        double vTZ = vData[2];
        double vRX = vData[3];
        double vRY = vData[4];
        double vRZ = vData[5];
        double vRW = vData[6];
        double vSX = vData[7];
        double vSY = vData[8];
        double vSZ = vData[9];
        double[] dd = this.data;
        dd[0] = vData[0];
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
        double[] rData = ((DoubleQuatImpl) r).data;
        double rX = rData[0];
        double rY = rData[1];
        double rZ = rData[2];
        double rW = rData[3];
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
        double[] sData = ((Double3Impl) s).data;
        if (COL_NARROW) return setScale_narrow(s, dest);
        double sX = sData[0];
        double sY = sData[1];
        double sZ = sData[2];
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
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
        if (COL_NARROW) return setScale_narrow(sX, sY, sZ, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
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
        if (COL_NARROW) return setScale_narrow(uniform, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
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
        double[] tData = ((Double3Impl) t).data;
        if (COL_NARROW) return setTranslation_narrow(t, dest);
        double tY = tData[1];
        double tZ = tData[2];
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = tData[0];
        dd[1] = tY;
        dd[2] = tZ;
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
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
        if (COL_NARROW) return setTranslation_narrow(tX, tY, tZ, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = tX;
        dd[1] = tY;
        dd[2] = tZ;
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }

    /**
     * Set this transform to the rigid motion of the unit dual quaternion {@code dq} (its
     * translation and rotation, scale = 1).
     * <p>
     * Valid input: {@code dq} must be a unit dual quaternion.
     *
     * @param dq the dual quaternion to convert
     * @return this
     */
    @Mutated public DoubleTransform makeFromDualQuat(DoubleDualQuatR dq) {
        double[] dqData = ((DoubleDualQuatImpl) dq).data;
        return makeFromDualQuat(dqData[0], dqData[1], dqData[2], dqData[3], dqData[4], dqData[5], dqData[6], dqData[7]);
    }

    /**
     * Set this transform to the rigid motion of the unit dual quaternion ({@code dqRX},
     * {@code dqRY}, {@code dqRZ}, {@code dqRW}, {@code dqDX}, {@code dqDY}, {@code dqDZ},
     * {@code dqDW}) (its translation and rotation, scale = 1).
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
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] dd = this.data;
            dd[0] = 2.0 * (java.lang.Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + java.lang.Math.fma(dqRW, dqDX, dqRX * -dqDW));
            dd[1] = 2.0 * (java.lang.Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + java.lang.Math.fma(dqRW, dqDY, dqRY * -dqDW));
            dd[2] = 2.0 * (java.lang.Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + java.lang.Math.fma(dqRW, dqDZ, dqRZ * -dqDW));
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
     * a sheared block yields a rotation quaternion that is not unit length; a mirroring block
     * (negative determinant) gives a negative x scale, and the rotation of the block with its first
     * column negated).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public DoubleTransform makeFromMatrix(Double3x3R m) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeFromMatrix_mulAdd(m);
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
        return ((((((_t19) * (_t24) - (_t25 * _t22))) * (_ld4 * _t16) + (((_t25) * (_t20) - (_t21 * _t24)) * (_ld7 * _t17)))) - ((_t19) * (_t20) - (_t21 * _t22)) * (_ld3 * _t15));
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
        dd[3] = (java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t48))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t52)))))) * (java.lang.Math.fma(_ld2, _t15, -(_ld5 * _t16))) : _t48 > (Math.max(_t25, _t20)) ? 0.5 * java.lang.Math.sqrt((java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t1, _t16, _t53)))))) * (java.lang.Math.fma(_ld3, _t15, _t49)) : (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld1, _t16, java.lang.Math.fma(_t0, _t15, _t53)))))) * (java.lang.Math.fma(_ld4, _t16, _t50));
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
        dd[3] = (((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t48))))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t52)))))))) * (((_ld2) * (_t15) - (_ld5 * _t16))) : _t48 > (Math.max(_t25, _t20)) ? 0.5 * java.lang.Math.sqrt((((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_t1) * (_t16) + (_t53)))))))) * (((_ld3) * (_t15) + (_t49))) : (0.5 * (1.0 / java.lang.Math.sqrt((((_ld1) * (_t16) + (((_t0) * (_t15) + (_t53)))))))) * (((_ld4) * (_t16) + (_t50)));
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
        dd[4] = (java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t48))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t52)))))) * (java.lang.Math.fma(_ld4, _t16, -_t50)) : _t48 > (Math.max(_t25, _t20)) ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)))))) * (java.lang.Math.fma(_ld3, _t15, _t49)) : _t25 > _t20 ? 0.5 * java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t1, _t16, _t53)))) : (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld1, _t16, java.lang.Math.fma(_t0, _t15, _t53)))))) * (java.lang.Math.fma(_ld5, _t16, (_ld2 * _t15)));
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
        dd[4] = (((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t48))))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t52)))))))) * (((_ld4) * (_t16) - (_t50))) : _t48 > (Math.max(_t25, _t20)) ? (0.5 * (1.0 / java.lang.Math.sqrt((((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))))))) * (((_ld3) * (_t15) + (_t49))) : _t25 > _t20 ? 0.5 * java.lang.Math.sqrt((((_ld0) * (_t15) + (((_t1) * (_t16) + (_t53)))))) : (0.5 * (1.0 / java.lang.Math.sqrt((((_ld1) * (_t16) + (((_t0) * (_t15) + (_t53)))))))) * (((_ld5) * (_t16) + ((_ld2 * _t15))));
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
        dd[5] = (java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t48))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t52)))))) * (java.lang.Math.fma(-_ld3, _t15, _t49)) : _t48 > (Math.max(_t25, _t20)) ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)))))) * (java.lang.Math.fma(_ld4, _t16, _t50)) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t1, _t16, _t53)))))) * (java.lang.Math.fma(_ld5, _t16, (_ld2 * _t15))) : 0.5 * java.lang.Math.sqrt((java.lang.Math.fma(_ld1, _t16, java.lang.Math.fma(_t0, _t15, _t53))));
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
        dd[5] = (((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t48))))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t52)))))))) * (((_t49) - (_ld3) * (_t15))) : _t48 > (Math.max(_t25, _t20)) ? (0.5 * (1.0 / java.lang.Math.sqrt((((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))))))) * (((_ld4) * (_t16) + (_t50))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_t1) * (_t16) + (_t53)))))))) * (((_ld5) * (_t16) + ((_ld2 * _t15)))) : 0.5 * java.lang.Math.sqrt((((_ld1) * (_t16) + (((_t0) * (_t15) + (_t53))))));
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
        dd[6] = (java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t48))) > 0.0 ? 0.5 * java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t52)))) : _t48 > (Math.max(_t25, _t20)) ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)))))) * (java.lang.Math.fma(_ld2, _t15, -(_ld5 * _t16))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t1, _t16, _t53)))))) * (java.lang.Math.fma(_ld4, _t16, -_t50)) : (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld1, _t16, java.lang.Math.fma(_t0, _t15, _t53)))))) * (java.lang.Math.fma(-_ld3, _t15, _t49));
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
        dd[6] = (((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t48))))) > 0.0 ? 0.5 * java.lang.Math.sqrt((((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t52)))))) : _t48 > (Math.max(_t25, _t20)) ? (0.5 * (1.0 / java.lang.Math.sqrt((((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))))))) * (((_ld2) * (_t15) - (_ld5 * _t16))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_t1) * (_t16) + (_t53)))))))) * (((_ld4) * (_t16) - (_t50))) : (0.5 * (1.0 / java.lang.Math.sqrt((((_ld1) * (_t16) + (((_t0) * (_t15) + (_t53)))))))) * (((_t49) - (_ld3) * (_t15)));
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
                    _t171 = java.lang.Math.fma(_t37, _t109, -_t35 * _t119);
                    _t174 = java.lang.Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = java.lang.Math.fma(_t38, _t108, -_t39 * _t117);
                    _t170 = java.lang.Math.fma(_t39, _t106, -(_t40 * _t108));
                    _t173 = java.lang.Math.fma(_t40, _t117, -(_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = java.lang.Math.fma(_t35, _t38, _t37 * -_t39);
                    _t170 = java.lang.Math.fma(_t36, _t39, -_t35 * _t40);
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
                    _t176 = java.lang.Math.fma(_t43, _t110, -_t42 * _t118);
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = java.lang.Math.fma(_t35, _t41, -(_t36 * _t42));
                    _t171 = java.lang.Math.fma(_t37, _t42, -_t35 * _t43);
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
                    _t176 = java.lang.Math.fma(_t43, _t39, -_t42 * _t38);
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
        double _t186 = Math.max(_t170, _t174);
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
        double _t186 = Math.max(_t170, _t174);
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
     * removed: a sheared block yields a rotation quaternion that is not unit length; a mirroring
     * block (negative determinant) gives a negative x scale, and the rotation of the block with its
     * first column negated).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public DoubleTransform makeFromMatrix(Double3x4R m) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeFromMatrix_mulAdd(m);
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
        dd[3] = (java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t48))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t52)))))) * (java.lang.Math.fma(_ld2, _t15, -(_ld5 * _t16))) : _t48 > (Math.max(_t25, _t20)) ? 0.5 * java.lang.Math.sqrt((java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t1, _t16, _t53)))))) * (java.lang.Math.fma(_ld3, _t15, _t49)) : (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld1, _t16, java.lang.Math.fma(_t0, _t15, _t53)))))) * (java.lang.Math.fma(_ld4, _t16, _t50));
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
        dd[3] = (((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t48))))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t52)))))))) * (((_ld2) * (_t15) - (_ld5 * _t16))) : _t48 > (Math.max(_t25, _t20)) ? 0.5 * java.lang.Math.sqrt((((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_t1) * (_t16) + (_t53)))))))) * (((_ld3) * (_t15) + (_t49))) : (0.5 * (1.0 / java.lang.Math.sqrt((((_ld1) * (_t16) + (((_t0) * (_t15) + (_t53)))))))) * (((_ld4) * (_t16) + (_t50)));
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
                    _t171 = java.lang.Math.fma(_t37, _t109, -_t35 * _t119);
                    _t174 = java.lang.Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = java.lang.Math.fma(_t38, _t108, -_t39 * _t117);
                    _t170 = java.lang.Math.fma(_t39, _t106, -(_t40 * _t108));
                    _t173 = java.lang.Math.fma(_t40, _t117, -(_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = java.lang.Math.fma(_t35, _t38, _t37 * -_t39);
                    _t170 = java.lang.Math.fma(_t36, _t39, -_t35 * _t40);
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
                    _t176 = java.lang.Math.fma(_t43, _t110, -_t42 * _t118);
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = java.lang.Math.fma(_t35, _t41, -(_t36 * _t42));
                    _t171 = java.lang.Math.fma(_t37, _t42, -_t35 * _t43);
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
                    _t176 = java.lang.Math.fma(_t43, _t39, -_t42 * _t38);
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
        double _t186 = Math.max(_t170, _t174);
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
        double _t186 = Math.max(_t170, _t174);
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
     * removed: a sheared block yields a rotation quaternion that is not unit length; a mirroring
     * block (negative determinant) gives a negative x scale, and the rotation of the block with its
     * first column negated).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated public DoubleTransform makeFromMatrix(Double4x4R m) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeFromMatrix_mulAdd(m);
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
        dd[3] = (java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t48))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_ld1, _t16, _t52)))))) * (java.lang.Math.fma(_ld2, _t15, -(_ld5 * _t16))) : _t48 > (Math.max(_t25, _t20)) ? 0.5 * java.lang.Math.sqrt((java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld0, _t15, java.lang.Math.fma(_t1, _t16, _t53)))))) * (java.lang.Math.fma(_ld3, _t15, _t49)) : (0.5 * (1.0 / java.lang.Math.sqrt((java.lang.Math.fma(_ld1, _t16, java.lang.Math.fma(_t0, _t15, _t53)))))) * (java.lang.Math.fma(_ld4, _t16, _t50));
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
        dd[3] = (((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t48))))) > 0.0 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_ld1) * (_t16) + (_t52)))))))) * (((_ld2) * (_t15) - (_ld5 * _t16))) : _t48 > (Math.max(_t25, _t20)) ? 0.5 * java.lang.Math.sqrt((((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))))) : _t25 > _t20 ? (0.5 * (1.0 / java.lang.Math.sqrt((((_ld0) * (_t15) + (((_t1) * (_t16) + (_t53)))))))) * (((_ld3) * (_t15) + (_t49))) : (0.5 * (1.0 / java.lang.Math.sqrt((((_ld1) * (_t16) + (((_t0) * (_t15) + (_t53)))))))) * (((_ld4) * (_t16) + (_t50)));
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
                    _t171 = java.lang.Math.fma(_t37, _t109, -_t35 * _t119);
                    _t174 = java.lang.Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = java.lang.Math.fma(_t38, _t108, -_t39 * _t117);
                    _t170 = java.lang.Math.fma(_t39, _t106, -(_t40 * _t108));
                    _t173 = java.lang.Math.fma(_t40, _t117, -(_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = java.lang.Math.fma(_t35, _t38, _t37 * -_t39);
                    _t170 = java.lang.Math.fma(_t36, _t39, -_t35 * _t40);
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
                    _t176 = java.lang.Math.fma(_t43, _t110, -_t42 * _t118);
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = java.lang.Math.fma(_t35, _t41, -(_t36 * _t42));
                    _t171 = java.lang.Math.fma(_t37, _t42, -_t35 * _t43);
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
                    _t176 = java.lang.Math.fma(_t43, _t39, -_t42 * _t38);
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
        double _t186 = Math.max(_t170, _t174);
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
        double _t186 = Math.max(_t170, _t174);
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
    @Mutated public DoubleTransform makeFromRigid(DoubleRigidR r) {
        double[] rData = ((DoubleRigidImpl) r).data;
        double rTY = rData[1];
        double rTZ = rData[2];
        double rRX = rData[3];
        double rRY = rData[4];
        double rRZ = rData[5];
        double rRW = rData[6];
        double[] dd = this.data;
        dd[0] = rData[0];
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
        if (COL_NARROW) return toDualQuat_narrow(dest);
        if (!org.joml2.internal.FmaSwitch.ON) return toDualQuat_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = -sd[2];
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 0);
        dd[4] = 0.5 * java.lang.Math.fma(_t0, sd[4], java.lang.Math.fma(sd[0], sd[6], sd[1] * sd[5]));
        dd[5] = 0.5 * java.lang.Math.fma(sd[2], sd[3], java.lang.Math.fma(sd[1], sd[6], -sd[0] * sd[5]));
        dd[6] = 0.5 * java.lang.Math.fma(sd[2], sd[6], java.lang.Math.fma(sd[0], sd[4], -sd[1] * sd[3]));
        dd[7] = 0.5 * java.lang.Math.fma(_t0, sd[5], java.lang.Math.fma(-sd[1], sd[4], -sd[0] * sd[3]));
        return dest;
    }

    /** {@code toDualQuat} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat toDualQuat_mulAdd(@Mutated DoubleDualQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleDualQuatImpl) dest).data;
        double _t0 = -sd[2];
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 0);
        dd[4] = 0.5 * ((_t0) * (sd[4]) + (((sd[0]) * (sd[6]) + (sd[1] * sd[5]))));
        dd[5] = 0.5 * ((sd[2]) * (sd[3]) + (((sd[1]) * (sd[6]) - (sd[0] * sd[5]))));
        dd[6] = 0.5 * ((sd[2]) * (sd[6]) + (((sd[0]) * (sd[4]) - (sd[1] * sd[3]))));
        dd[7] = 0.5 * ((_t0) * (sd[5]) + (((-sd[1]) * (sd[4]) - (sd[0] * sd[3]))));
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
        if (org.joml2.internal.FmaSwitch.ON) return toMatrix_fma(dest);
        return toMatrix_mulAdd(dest);
    }

    /** {@code toMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x4 toMatrix_fma(@Mutated Double4x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        double _t0 = sd[7] + sd[7];
        double _t1 = sd[8] + sd[8];
        double _t2 = sd[9] + sd[9];
        double _t3 = sd[5] * sd[5];
        double _t4 = sd[5] * sd[6];
        double _t5 = sd[4] * sd[6];
        dd[0] = java.lang.Math.fma(-java.lang.Math.fma(sd[4], sd[4], _t3), _t0, sd[7]);
        dd[1] = java.lang.Math.fma(sd[3], sd[4], _t4) * _t0;
        dd[2] = java.lang.Math.fma(sd[3], sd[5], -_t5) * _t0;
        dd[3] = 0.0;
        dd[4] = java.lang.Math.fma(sd[3], sd[4], -_t4) * _t1;
        dd[5] = java.lang.Math.fma(-java.lang.Math.fma(sd[3], sd[3], _t3), _t1, sd[8]);
        dd[6] = java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]) * _t1;
        dd[7] = 0.0;
        dd[8] = java.lang.Math.fma(sd[3], sd[5], _t5) * _t2;
        dd[9] = java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6])) * _t2;
        dd[10] = java.lang.Math.fma(-java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), _t2, sd[9]);
        dd[11] = 0.0;
        dd[12] = sd[0];
        dd[13] = sd[1];
        dd[14] = sd[2];
        dd[15] = 1.0;
        ((Double4x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x4 toMatrix_mulAdd(@Mutated Double4x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        double _t0 = sd[7] + sd[7];
        double _t1 = sd[8] + sd[8];
        double _t2 = sd[9] + sd[9];
        double _t3 = sd[5] * sd[5];
        double _t4 = sd[5] * sd[6];
        double _t5 = sd[4] * sd[6];
        dd[0] = ((sd[7]) - ((sd[4]) * (sd[4]) + (_t3)) * (_t0));
        dd[1] = ((sd[3]) * (sd[4]) + (_t4)) * _t0;
        dd[2] = ((sd[3]) * (sd[5]) - (_t5)) * _t0;
        dd[3] = 0.0;
        dd[4] = ((sd[3]) * (sd[4]) - (_t4)) * _t1;
        dd[5] = ((sd[8]) - ((sd[3]) * (sd[3]) + (_t3)) * (_t1));
        dd[6] = ((sd[3]) * (sd[6]) + (sd[4] * sd[5])) * _t1;
        dd[7] = 0.0;
        dd[8] = ((sd[3]) * (sd[5]) + (_t5)) * _t2;
        dd[9] = ((sd[4]) * (sd[5]) - (sd[3] * sd[6])) * _t2;
        dd[10] = ((sd[9]) - ((sd[3]) * (sd[3]) + (sd[4] * sd[4])) * (_t2));
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
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix3x3_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = sd[7] + sd[7];
        double _t1 = sd[8] + sd[8];
        double _t2 = sd[9] + sd[9];
        double _t3 = sd[5] * sd[5];
        double _t4 = sd[5] * sd[6];
        double _t5 = sd[4] * sd[6];
        dd[0] = java.lang.Math.fma(-java.lang.Math.fma(sd[4], sd[4], _t3), _t0, sd[7]);
        dd[1] = java.lang.Math.fma(sd[3], sd[4], _t4) * _t0;
        dd[2] = java.lang.Math.fma(sd[3], sd[5], -_t5) * _t0;
        dd[3] = java.lang.Math.fma(sd[3], sd[4], -_t4) * _t1;
        dd[4] = java.lang.Math.fma(-java.lang.Math.fma(sd[3], sd[3], _t3), _t1, sd[8]);
        dd[5] = java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]) * _t1;
        dd[6] = java.lang.Math.fma(sd[3], sd[5], _t5) * _t2;
        dd[7] = java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6])) * _t2;
        dd[8] = java.lang.Math.fma(-java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), _t2, sd[9]);
        ((Double3x3Impl) dest).properties = 0;
        return dest;
    }

    /** {@code toMatrix3x3} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x3 toMatrix3x3_mulAdd(@Mutated Double3x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        double _t0 = sd[7] + sd[7];
        double _t1 = sd[8] + sd[8];
        double _t2 = sd[9] + sd[9];
        double _t3 = sd[5] * sd[5];
        double _t4 = sd[5] * sd[6];
        double _t5 = sd[4] * sd[6];
        dd[0] = ((sd[7]) - ((sd[4]) * (sd[4]) + (_t3)) * (_t0));
        dd[1] = ((sd[3]) * (sd[4]) + (_t4)) * _t0;
        dd[2] = ((sd[3]) * (sd[5]) - (_t5)) * _t0;
        dd[3] = ((sd[3]) * (sd[4]) - (_t4)) * _t1;
        dd[4] = ((sd[8]) - ((sd[3]) * (sd[3]) + (_t3)) * (_t1));
        dd[5] = ((sd[3]) * (sd[6]) + (sd[4] * sd[5])) * _t1;
        dd[6] = ((sd[3]) * (sd[5]) + (_t5)) * _t2;
        dd[7] = ((sd[4]) * (sd[5]) - (sd[3] * sd[6])) * _t2;
        dd[8] = ((sd[9]) - ((sd[3]) * (sd[3]) + (sd[4] * sd[4])) * (_t2));
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
        if (org.joml2.internal.FmaSwitch.ON) return toMatrix3x4_fma(dest);
        return toMatrix3x4_mulAdd(dest);
    }

    /** {@code toMatrix3x4} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x4 toMatrix3x4_fma(@Mutated Double3x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        double _t0 = sd[7] + sd[7];
        double _t1 = sd[8] + sd[8];
        double _t2 = sd[9] + sd[9];
        double _t3 = sd[5] * sd[5];
        double _t4 = sd[5] * sd[6];
        double _t5 = sd[4] * sd[6];
        dd[0] = java.lang.Math.fma(-java.lang.Math.fma(sd[4], sd[4], _t3), _t0, sd[7]);
        dd[1] = java.lang.Math.fma(sd[3], sd[4], -_t4) * _t1;
        dd[2] = java.lang.Math.fma(sd[3], sd[5], _t5) * _t2;
        dd[3] = sd[0];
        dd[4] = java.lang.Math.fma(sd[3], sd[4], _t4) * _t0;
        dd[5] = java.lang.Math.fma(-java.lang.Math.fma(sd[3], sd[3], _t3), _t1, sd[8]);
        dd[6] = java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6])) * _t2;
        dd[7] = sd[1];
        dd[8] = java.lang.Math.fma(sd[3], sd[5], -_t5) * _t0;
        dd[9] = java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]) * _t1;
        dd[10] = java.lang.Math.fma(-java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), _t2, sd[9]);
        dd[11] = sd[2];
        ((Double3x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x4 toMatrix3x4_mulAdd(@Mutated Double3x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        double _t0 = sd[7] + sd[7];
        double _t1 = sd[8] + sd[8];
        double _t2 = sd[9] + sd[9];
        double _t3 = sd[5] * sd[5];
        double _t4 = sd[5] * sd[6];
        double _t5 = sd[4] * sd[6];
        dd[0] = ((sd[7]) - ((sd[4]) * (sd[4]) + (_t3)) * (_t0));
        dd[1] = ((sd[3]) * (sd[4]) - (_t4)) * _t1;
        dd[2] = ((sd[3]) * (sd[5]) + (_t5)) * _t2;
        dd[3] = sd[0];
        dd[4] = ((sd[3]) * (sd[4]) + (_t4)) * _t0;
        dd[5] = ((sd[8]) - ((sd[3]) * (sd[3]) + (_t3)) * (_t1));
        dd[6] = ((sd[4]) * (sd[5]) - (sd[3] * sd[6])) * _t2;
        dd[7] = sd[1];
        dd[8] = ((sd[3]) * (sd[5]) - (_t5)) * _t0;
        dd[9] = ((sd[3]) * (sd[6]) + (sd[4] * sd[5])) * _t1;
        dd[10] = ((sd[9]) - ((sd[3]) * (sd[3]) + (sd[4] * sd[4])) * (_t2));
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
        if (COL_NARROW) return toRigid_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleRigidImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
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
        System.arraycopy(DATA_0, 0, dd, 0, 10);
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
    @Mutated public DoubleTransform set(DoubleQuatR rotation) {
        double[] rotationData = ((DoubleQuatImpl) rotation).data;
        double rotationX = rotationData[0];
        double rotationY = rotationData[1];
        double rotationZ = rotationData[2];
        double rotationW = rotationData[3];
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
    @Mutated public DoubleTransform set(Double3R translation) {
        double[] translationData = ((Double3Impl) translation).data;
        if (COL_NARROW) return set_narrow(translation);
        double translationY = translationData[1];
        double translationZ = translationData[2];
        double[] dd = this.data;
        dd[0] = translationData[0];
        dd[1] = translationY;
        dd[2] = translationZ;
        VEC_1.intoArray(dd, 3);
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
        if (COL_NARROW) return set_narrow(translationX, translationY, translationZ);
        double[] dd = this.data;
        dd[0] = translationX;
        dd[1] = translationY;
        dd[2] = translationZ;
        VEC_1.intoArray(dd, 3);
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }

    /**
     * Interpolate between this transform and {@code other} using the interpolation factor
     * {@code t}, interpolating translation and scale linearly and the rotation via shortest-arc
     * slerp, and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this transform (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (1e17 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the rotation of this transform must have unit length; the rotation of
     * {@code other} must have unit length; {@code t} must lie in {@code [0, 1]}.
     *
     * @param other the transform to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform lerp(DoubleTransformR other, double t, @Mutated DoubleTransform dest) {
        double[] otherData = ((DoubleTransformImpl) other).data;
        return lerp(otherData[0], otherData[1], otherData[2], otherData[3], otherData[4], otherData[5], otherData[6], otherData[7], otherData[8], otherData[9], t, dest);
    }

    /**
     * Interpolate between this transform and ({@code otherTX}, {@code otherTY}, {@code otherTZ},
     * {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherSX},
     * {@code otherSY}, {@code otherSZ}) using the interpolation factor {@code t}, interpolating
     * translation and scale linearly and the rotation via shortest-arc slerp, and store the result
     * in {@code dest}.
     * <p>
     * The interpolation starts at this transform (interpolation factor {@code 0}) and ends at
     * ({@code otherTX}, {@code otherTY}, {@code otherTZ}, {@code otherRX}, {@code otherRY},
     * {@code otherRZ}, {@code otherRW}, {@code otherSX}, {@code otherSY}, {@code otherSZ})
     * (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (1e17 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the rotation of this transform must have unit length;
     * {@code (otherRX, otherRY, otherRZ, otherRW)} must have unit length; {@code t} must lie in
     * {@code [0, 1]}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, t, dest);
        double[] sd = this.data;
        double _t0 = 1.0 - t;
        double _t12 = java.lang.Math.fma(otherRW, sd[6], java.lang.Math.fma(otherRZ, sd[5], java.lang.Math.fma(otherRX, sd[3], otherRY * sd[4])));
        double _t15 = Math.min(1.0, java.lang.Math.abs(_t12));
        double _t17 = Math.acos(_t15);
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
        double _t31 = java.lang.Math.sqrt(java.lang.Math.fma(-_t15, _t15, 1.0));
        double _t32 = 1.0 / _t31;
        return lerp_s3232cb55_1_fma(otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ, t, dest, sd, ((DoubleTransformImpl) dest).data, _t0, _t21, _t22, _t23, _t24, _t31, Math.sin(t * _t17) * _t32, Math.sin(_t0 * _t17) * _t32);
    }

    /** {@code lerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform lerp_mulAdd(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, double t, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double _t0 = 1.0 - t;
        double _t12 = ((otherRW) * (sd[6]) + (((otherRZ) * (sd[5]) + (((otherRX) * (sd[3]) + (otherRY * sd[4]))))));
        double _t15 = Math.min(1.0, java.lang.Math.abs(_t12));
        double _t17 = Math.acos(_t15);
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
        double _t31 = java.lang.Math.sqrt(((1.0) - (_t15) * (_t15)));
        double _t32 = 1.0 / _t31;
        return lerp_s3232cb55_1_mulAdd(otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ, t, dest, sd, ((DoubleTransformImpl) dest).data, _t0, _t21, _t22, _t23, _t24, _t31, Math.sin(t * _t17) * _t32, Math.sin(_t0 * _t17) * _t32);
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleTransform lerp_s3232cb55_1_fma(double otherTX, double otherTY, double otherTZ, double otherSX, double otherSY, double otherSZ, double t, DoubleTransform dest, double[] sd, double[] dd, double _t0, double _t21, double _t22, double _t23, double _t24, double _t31, double _t33, double _t34) {
        double _t43, _t44, _t45, _t46;
        if (_t31 > 0.0) {
            _t43 = java.lang.Math.fma(sd[6], _t34, _t33 * _t21);
            _t44 = java.lang.Math.fma(sd[5], _t34, _t33 * _t22);
            _t45 = java.lang.Math.fma(sd[3], _t34, _t33 * _t23);
            _t46 = java.lang.Math.fma(sd[4], _t34, _t33 * _t24);
        } else {
            _t43 = java.lang.Math.fma(t, _t21, sd[6] * _t0);
            _t44 = java.lang.Math.fma(t, _t22, sd[5] * _t0);
            _t45 = java.lang.Math.fma(t, _t23, sd[3] * _t0);
            _t46 = java.lang.Math.fma(t, _t24, sd[4] * _t0);
        }
        double _t50 = java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, java.lang.Math.fma(_t45, _t45, _t46 * _t46)));
        dd[0] = java.lang.Math.fma(t, otherTX - sd[0], sd[0]);
        dd[1] = java.lang.Math.fma(t, otherTY - sd[1], sd[1]);
        dd[2] = java.lang.Math.fma(t, otherTZ - sd[2], sd[2]);
        return lerp_s3232cb55_2_fma(otherSX, otherSY, otherSZ, t, dest, sd, dd, _t43, _t44, _t45, _t46, _t50, (1.0 / java.lang.Math.sqrt(_t50)));
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleTransform lerp_s3232cb55_1_mulAdd(double otherTX, double otherTY, double otherTZ, double otherSX, double otherSY, double otherSZ, double t, DoubleTransform dest, double[] sd, double[] dd, double _t0, double _t21, double _t22, double _t23, double _t24, double _t31, double _t33, double _t34) {
        double _t43, _t44, _t45, _t46;
        if (_t31 > 0.0) {
            _t43 = ((sd[6]) * (_t34) + (_t33 * _t21));
            _t44 = ((sd[5]) * (_t34) + (_t33 * _t22));
            _t45 = ((sd[3]) * (_t34) + (_t33 * _t23));
            _t46 = ((sd[4]) * (_t34) + (_t33 * _t24));
        } else {
            _t43 = ((t) * (_t21) + (sd[6] * _t0));
            _t44 = ((t) * (_t22) + (sd[5] * _t0));
            _t45 = ((t) * (_t23) + (sd[3] * _t0));
            _t46 = ((t) * (_t24) + (sd[4] * _t0));
        }
        double _t50 = ((_t43) * (_t43) + (((_t44) * (_t44) + (((_t45) * (_t45) + (_t46 * _t46))))));
        dd[0] = ((t) * (otherTX - sd[0]) + (sd[0]));
        dd[1] = ((t) * (otherTY - sd[1]) + (sd[1]));
        dd[2] = ((t) * (otherTZ - sd[2]) + (sd[2]));
        return lerp_s3232cb55_2_mulAdd(otherSX, otherSY, otherSZ, t, dest, sd, dd, _t43, _t44, _t45, _t46, _t50, (1.0 / java.lang.Math.sqrt(_t50)));
    }

    /** Piece 3 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleTransform lerp_s3232cb55_2_fma(double otherSX, double otherSY, double otherSZ, double t, DoubleTransform dest, double[] sd, double[] dd, double _t43, double _t44, double _t45, double _t46, double _t50, double _t51) {
        if (_t50 != 0.0) {
            dd[3] = _t51 * _t45;
            dd[4] = _t51 * _t46;
            dd[5] = _t51 * _t44;
            dd[6] = _t51 * _t43;
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
    private DoubleTransform lerp_s3232cb55_2_mulAdd(double otherSX, double otherSY, double otherSZ, double t, DoubleTransform dest, double[] sd, double[] dd, double _t43, double _t44, double _t45, double _t46, double _t50, double _t51) {
        if (_t50 != 0.0) {
            dd[3] = _t51 * _t45;
            dd[4] = _t51 * _t46;
            dd[5] = _t51 * _t44;
            dd[6] = _t51 * _t43;
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
        double[] otherData = ((DoubleTransformImpl) other).data;
        return mul(otherData[0], otherData[1], otherData[2], otherData[3], otherData[4], otherData[5], otherData[6], otherData[7], otherData[8], otherData[9], dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = otherTY * sd[8];
        double _t1 = otherTX * sd[7];
        double _t2 = otherTZ * sd[9];
        double _t12 = 2.0 * java.lang.Math.fma(sd[3], _t0, -(sd[4] * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(sd[5], _t1, -(sd[3] * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(sd[4], _t2, -(sd[5] * _t0));
        dd[0] = java.lang.Math.fma(sd[4], _t12, java.lang.Math.fma(-sd[5], _t13, java.lang.Math.fma(sd[6], _t14, java.lang.Math.fma(otherTX, sd[7], sd[0]))));
        dd[1] = java.lang.Math.fma(sd[5], _t14, java.lang.Math.fma(-sd[3], _t12, java.lang.Math.fma(sd[6], _t13, java.lang.Math.fma(otherTY, sd[8], sd[1]))));
        dd[2] = java.lang.Math.fma(sd[3], _t13, java.lang.Math.fma(-sd[4], _t14, java.lang.Math.fma(sd[6], _t12, java.lang.Math.fma(otherTZ, sd[9], sd[2]))));
        return mul_s26e3d2ef_1_fma(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, dd);
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
        dd[0] = ((sd[4]) * (_t12) + (((((sd[6]) * (_t14) + (((otherTX) * (sd[7]) + (sd[0]))))) - (sd[5]) * (_t13))));
        dd[1] = ((sd[5]) * (_t14) + (((((sd[6]) * (_t13) + (((otherTY) * (sd[8]) + (sd[1]))))) - (sd[3]) * (_t12))));
        dd[2] = ((sd[3]) * (_t13) + (((((sd[6]) * (_t12) + (((otherTZ) * (sd[9]) + (sd[2]))))) - (sd[4]) * (_t14))));
        return mul_s26e3d2ef_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, dd);
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private DoubleTransform mul_s26e3d2ef_1_fma(double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, DoubleTransform dest, double[] sd, double[] dd) {
        double _buf0 = java.lang.Math.fma(otherRX, sd[6], otherRW * sd[3]) + java.lang.Math.fma(otherRZ, sd[4], otherRY * -sd[5]);
        double _buf1 = java.lang.Math.fma(otherRY, sd[6], otherRW * sd[4]) + java.lang.Math.fma(otherRX, sd[5], otherRZ * -sd[3]);
        double _buf2 = java.lang.Math.fma(otherRZ, sd[6], otherRW * sd[5]) + java.lang.Math.fma(otherRY, sd[3], otherRX * -sd[4]);
        dd[6] = java.lang.Math.fma(otherRW, sd[6], otherRX * -sd[3]) - java.lang.Math.fma(otherRY, sd[4], otherRZ * sd[5]);
        dd[7] = otherSX * sd[7];
        dd[8] = otherSY * sd[8];
        dd[9] = otherSZ * sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private DoubleTransform mul_s26e3d2ef_1_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, DoubleTransform dest, double[] sd, double[] dd) {
        double _buf0 = ((otherRX) * (sd[6]) + (otherRW * sd[3])) + ((otherRZ) * (sd[4]) - (otherRY * sd[5]));
        double _buf1 = ((otherRY) * (sd[6]) + (otherRW * sd[4])) + ((otherRX) * (sd[5]) - (otherRZ * sd[3]));
        double _buf2 = ((otherRZ) * (sd[6]) + (otherRW * sd[5])) + ((otherRY) * (sd[3]) - (otherRX * sd[4]));
        dd[6] = ((otherRW) * (sd[6]) - (otherRX * sd[3])) - ((otherRY) * (sd[4]) + (otherRZ * sd[5]));
        dd[7] = otherSX * sd[7];
        dd[8] = otherSY * sd[8];
        dd[9] = otherSZ * sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
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
        double[] otherData = ((DoubleTransformImpl) other).data;
        double otherTX = otherData[0];
        double otherTY = otherData[1];
        double otherTZ = otherData[2];
        double otherRX = otherData[3];
        double otherRY = otherData[4];
        double otherRZ = otherData[5];
        double otherRW = otherData[6];
        double otherSX = otherData[7];
        double otherSY = otherData[8];
        double otherSZ = otherData[9];
        if (org.joml2.internal.FmaSwitch.ON) return preMul_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest);
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
        if (org.joml2.internal.FmaSwitch.ON) return preMul_fma(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest);
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
        dd[0] = java.lang.Math.fma(otherRY, _t12, java.lang.Math.fma(-otherRZ, _t13, java.lang.Math.fma(otherRW, _t14, java.lang.Math.fma(otherSX, sd[0], otherTX))));
        dd[1] = java.lang.Math.fma(otherRZ, _t14, java.lang.Math.fma(-otherRX, _t12, java.lang.Math.fma(otherRW, _t13, java.lang.Math.fma(otherSY, sd[1], otherTY))));
        dd[2] = java.lang.Math.fma(otherRX, _t13, java.lang.Math.fma(-otherRY, _t14, java.lang.Math.fma(otherRW, _t12, java.lang.Math.fma(otherSZ, sd[2], otherTZ))));
        return preMul_sa55bcc5e_1_fma(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, dd, java.lang.Math.fma(otherRX, sd[6], otherRW * sd[3]) + java.lang.Math.fma(otherRY, sd[5], -otherRZ * sd[4]), java.lang.Math.fma(otherRY, sd[6], otherRW * sd[4]) + java.lang.Math.fma(otherRZ, sd[3], -otherRX * sd[5]));
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
        dd[0] = ((otherRY) * (_t12) + (((((otherRW) * (_t14) + (((otherSX) * (sd[0]) + (otherTX))))) - (otherRZ) * (_t13))));
        dd[1] = ((otherRZ) * (_t14) + (((((otherRW) * (_t13) + (((otherSY) * (sd[1]) + (otherTY))))) - (otherRX) * (_t12))));
        dd[2] = ((otherRX) * (_t13) + (((((otherRW) * (_t12) + (((otherSZ) * (sd[2]) + (otherTZ))))) - (otherRY) * (_t14))));
        return preMul_sa55bcc5e_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, dd, ((otherRX) * (sd[6]) + (otherRW * sd[3])) + ((otherRY) * (sd[5]) - (otherRZ * sd[4])), ((otherRY) * (sd[6]) + (otherRW * sd[4])) + ((otherRZ) * (sd[3]) - (otherRX * sd[5])));
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private DoubleTransform preMul_sa55bcc5e_1_fma(double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, DoubleTransform dest, double[] sd, double[] dd, double _buf0, double _buf1) {
        double _buf2 = java.lang.Math.fma(otherRZ, sd[6], otherRW * sd[5]) + java.lang.Math.fma(otherRX, sd[4], -otherRY * sd[3]);
        dd[6] = java.lang.Math.fma(otherRW, sd[6], -otherRX * sd[3]) - java.lang.Math.fma(otherRY, sd[4], otherRZ * sd[5]);
        dd[7] = otherSX * sd[7];
        dd[8] = otherSY * sd[8];
        dd[9] = otherSZ * sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private DoubleTransform preMul_sa55bcc5e_1_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, DoubleTransform dest, double[] sd, double[] dd, double _buf0, double _buf1) {
        double _buf2 = ((otherRZ) * (sd[6]) + (otherRW * sd[5])) + ((otherRX) * (sd[4]) - (otherRY * sd[3]));
        dd[6] = ((otherRW) * (sd[6]) - (otherRX * sd[3])) - ((otherRY) * (sd[4]) + (otherRZ * sd[5]));
        dd[7] = otherSX * sd[7];
        dd[8] = otherSY * sd[8];
        dd[9] = otherSZ * sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /**
     * Compute the difference between this transform and {@code other}, i.e. the
     * translation-rotation-scale transformation {@code D} with {@code this * D = other}, that is
     * {@code D = this^-1 * other}, and store the result in {@code dest}.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param other the target transform, reached by composing this transform with the result
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform difference(DoubleTransformR other, @Mutated DoubleTransform dest) {
        double[] otherData = ((DoubleTransformImpl) other).data;
        return difference(otherData[0], otherData[1], otherData[2], otherData[3], otherData[4], otherData[5], otherData[6], otherData[7], otherData[8], otherData[9], dest);
    }

    /**
     * Compute the difference between this transform and ({@code otherTX}, {@code otherTY},
     * {@code otherTZ}, {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW},
     * {@code otherSX}, {@code otherSY}, {@code otherSZ}), i.e. the translation-rotation-scale
     * transformation {@code D} with
     * {@code this * D = (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)},
     * that is
     * {@code D = this^-1 * (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)},
     * and store the result in {@code dest}.
     * <p>
     * A transform carries no shear, so this composition is exact only for a uniform scale: under a
     * non-uniform scale the shear the product would have is dropped, and applying the result to a
     * point is then not the same as applying the operands one after the other (likewise
     * {@code invert()} composes with {@code this} to the identity but is not the pointwise inverse;
     * {@code transformPositionInverse} is).
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
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
        if (!org.joml2.internal.FmaSwitch.ON) return difference_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest);
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
        return difference_s50dc31b8_1_fma(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, ((DoubleTransformImpl) dest).data, _rcp0, _sp4, _sp0, _rcp1, _sp3, _sp1, _rcp2, _sp5, _sp2, 2.0 * java.lang.Math.fma(_sp0, sd[3], -(_sp1 * sd[5])), 2.0 * java.lang.Math.fma(_sp1, sd[4], -(_sp2 * sd[3])), 2.0 * java.lang.Math.fma(_sp2, sd[5], -(_sp0 * sd[4])), 2.0 * java.lang.Math.fma(_sp3, sd[5], -(_sp4 * sd[3])), 2.0 * java.lang.Math.fma(_sp5, sd[3], -(_sp3 * sd[4])), 2.0 * java.lang.Math.fma(_sp4, sd[4], -(_sp5 * sd[5])));
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
        return difference_s50dc31b8_1_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, ((DoubleTransformImpl) dest).data, _rcp0, _sp4, _sp0, _rcp1, _sp3, _sp1, _rcp2, _sp5, _sp2, 2.0 * ((_sp0) * (sd[3]) - (_sp1 * sd[5])), 2.0 * ((_sp1) * (sd[4]) - (_sp2 * sd[3])), 2.0 * ((_sp2) * (sd[5]) - (_sp0 * sd[4])), 2.0 * ((_sp3) * (sd[5]) - (_sp4 * sd[3])), 2.0 * ((_sp5) * (sd[3]) - (_sp3 * sd[4])), 2.0 * ((_sp4) * (sd[4]) - (_sp5 * sd[5])));
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleTransform difference_s50dc31b8_1_fma(double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, DoubleTransform dest, double[] sd, double[] dd, double _rcp0, double _sp4, double _sp0, double _rcp1, double _sp3, double _sp1, double _rcp2, double _sp5, double _sp2, double _t30, double _t31, double _t32, double _t33, double _t34, double _t35) {
        dd[0] = java.lang.Math.fma(sd[5], _t30, -sd[4] * _t31) + java.lang.Math.fma(sd[6], _t32, _sp1) + (java.lang.Math.fma(sd[5], _t33, -sd[4] * _t34) + java.lang.Math.fma(sd[6], _t35, -_sp3));
        dd[1] = java.lang.Math.fma(sd[3], _t31, -sd[5] * _t32) + java.lang.Math.fma(sd[6], _t30, _sp2) + (java.lang.Math.fma(sd[3], _t34, -sd[5] * _t35) + java.lang.Math.fma(sd[6], _t33, -_sp5));
        dd[2] = java.lang.Math.fma(sd[4], _t32, -sd[3] * _t30) + java.lang.Math.fma(sd[6], _t31, _sp0) + (java.lang.Math.fma(sd[4], _t35, -sd[3] * _t33) + java.lang.Math.fma(sd[6], _t34, -_sp4));
        return difference_s50dc31b8_2_fma(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, dd, _rcp0, _rcp1, _rcp2, java.lang.Math.fma(otherRX, sd[6], otherRW * -sd[3]) + java.lang.Math.fma(otherRY, sd[5], otherRZ * -sd[4]), java.lang.Math.fma(otherRY, sd[6], otherRW * -sd[4]) + java.lang.Math.fma(otherRZ, sd[3], otherRX * -sd[5]));
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleTransform difference_s50dc31b8_1_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, DoubleTransform dest, double[] sd, double[] dd, double _rcp0, double _sp4, double _sp0, double _rcp1, double _sp3, double _sp1, double _rcp2, double _sp5, double _sp2, double _t30, double _t31, double _t32, double _t33, double _t34, double _t35) {
        dd[0] = ((sd[5]) * (_t30) - (sd[4] * _t31)) + ((sd[6]) * (_t32) + (_sp1)) + (((sd[5]) * (_t33) - (sd[4] * _t34)) + ((sd[6]) * (_t35) - (_sp3)));
        dd[1] = ((sd[3]) * (_t31) - (sd[5] * _t32)) + ((sd[6]) * (_t30) + (_sp2)) + (((sd[3]) * (_t34) - (sd[5] * _t35)) + ((sd[6]) * (_t33) - (_sp5)));
        dd[2] = ((sd[4]) * (_t32) - (sd[3] * _t30)) + ((sd[6]) * (_t31) + (_sp0)) + (((sd[4]) * (_t35) - (sd[3] * _t33)) + ((sd[6]) * (_t34) - (_sp4)));
        return difference_s50dc31b8_2_mulAdd(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, dd, _rcp0, _rcp1, _rcp2, ((otherRX) * (sd[6]) - (otherRW * sd[3])) + ((otherRY) * (sd[5]) - (otherRZ * sd[4])), ((otherRY) * (sd[6]) - (otherRW * sd[4])) + ((otherRZ) * (sd[3]) - (otherRX * sd[5])));
    }

    /** Piece 3 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleTransform difference_s50dc31b8_2_fma(double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, DoubleTransform dest, double[] sd, double[] dd, double _rcp0, double _rcp1, double _rcp2, double _buf0, double _buf1) {
        double _buf2 = java.lang.Math.fma(otherRX, sd[4], otherRY * -sd[3]) + java.lang.Math.fma(otherRZ, sd[6], otherRW * -sd[5]);
        dd[6] = java.lang.Math.fma(otherRX, sd[3], otherRW * sd[6]) - java.lang.Math.fma(otherRZ, -sd[5], otherRY * -sd[4]);
        dd[7] = otherSX * _rcp1;
        dd[8] = otherSY * _rcp2;
        dd[9] = otherSZ * _rcp0;
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 3 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleTransform difference_s50dc31b8_2_mulAdd(double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, DoubleTransform dest, double[] sd, double[] dd, double _rcp0, double _rcp1, double _rcp2, double _buf0, double _buf1) {
        double _buf2 = ((otherRX) * (sd[4]) - (otherRY * sd[3])) + ((otherRZ) * (sd[6]) - (otherRW * sd[5]));
        dd[6] = ((otherRX) * (sd[3]) + (otherRW * sd[6])) - ((otherRZ) * (-sd[5]) - (otherRY * sd[4]));
        dd[7] = otherSX * _rcp1;
        dd[8] = otherSY * _rcp2;
        dd[9] = otherSZ * _rcp0;
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
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
        if (!org.joml2.internal.FmaSwitch.ON) return invert_mulAdd(dest);
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
        double _t18 = 2.0 * java.lang.Math.fma(_sp0, sd[5], -(_sp1 * sd[3]));
        double _t19 = 2.0 * java.lang.Math.fma(_sp2, sd[3], -(_sp0 * sd[4]));
        double _t20 = 2.0 * java.lang.Math.fma(_sp1, sd[4], -(_sp2 * sd[5]));
        dd[0] = java.lang.Math.fma(sd[5], _t18, java.lang.Math.fma(_t0, _t19, java.lang.Math.fma(sd[6], _t20, -_sp0)));
        dd[1] = java.lang.Math.fma(sd[3], _t19, java.lang.Math.fma(_t1, _t20, java.lang.Math.fma(sd[6], _t18, -_sp2)));
        dd[2] = java.lang.Math.fma(sd[4], _t20, java.lang.Math.fma(_t2, _t18, java.lang.Math.fma(sd[6], _t19, -_sp1)));
        dd[3] = _t2;
        dd[4] = _t0;
        dd[5] = _t1;
        dd[7] = _rcp0;
        dd[8] = _rcp2;
        dd[9] = _rcp1;
        dd[6] = sd[6];
        return dest;
    }

    /** {@code invert} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform invert_mulAdd(@Mutated DoubleTransform dest) {
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
        double _t18 = 2.0 * ((_sp0) * (sd[5]) - (_sp1 * sd[3]));
        double _t19 = 2.0 * ((_sp2) * (sd[3]) - (_sp0 * sd[4]));
        double _t20 = 2.0 * ((_sp1) * (sd[4]) - (_sp2 * sd[5]));
        dd[0] = ((sd[5]) * (_t18) + (((_t0) * (_t19) + (((sd[6]) * (_t20) - (_sp0))))));
        dd[1] = ((sd[3]) * (_t19) + (((_t1) * (_t20) + (((sd[6]) * (_t18) - (_sp2))))));
        dd[2] = ((sd[4]) * (_t20) + (((_t2) * (_t18) + (((sd[6]) * (_t19) - (_sp1))))));
        dd[3] = _t2;
        dd[4] = _t0;
        dd[5] = _t1;
        dd[7] = _rcp0;
        dd[8] = _rcp2;
        dd[9] = _rcp1;
        dd[6] = sd[6];
        return dest;
    }

    /**
     * Normalize this transform so that its rotation part has unit length, leaving its translation
     * and scale unchanged (a zero-length rotation yields the zero quaternion), and store the result
     * in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform normalize(@Mutated DoubleTransform dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return normalize_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t3 = java.lang.Math.fma(sd[6], sd[6], java.lang.Math.fma(sd[5], sd[5], java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4])));
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

    /** {@code normalize} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform normalize_mulAdd(@Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t3 = ((sd[6]) * (sd[6]) + (((sd[5]) * (sd[5]) + (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))))));
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
     * Get the Euler angles in radians of this transform for rotations about the X, Y and Z axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first, then Y,
     * then X) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXYZ(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[4] * sd[5];
        double _t2 = sd[4] * sd[6];
        double _t10 = 2.0 * java.lang.Math.fma(sd[3], sd[5], _t2);
        double _t11 = 2.0 * java.lang.Math.fma(sd[3], sd[6], _t1);
        double _t12 = 2.0 * java.lang.Math.fma(sd[3], sd[6], -_t1);
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0);
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[3], sd[3], sd[5] * sd[5]), 1.0);
        double _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 7.888609052210118E-31) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]), 2.0 * java.lang.Math.fma(sd[3], sd[5], -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
        }
        dd[1] = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        return dest;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[4] * sd[5];
        double _t2 = sd[4] * sd[6];
        double _t10 = 2.0 * ((sd[3]) * (sd[5]) + (_t2));
        double _t11 = 2.0 * ((sd[3]) * (sd[6]) + (_t1));
        double _t12 = 2.0 * ((sd[3]) * (sd[6]) - (_t1));
        double _t13 = ((1.0) - (2.0) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        double _t14 = ((1.0) - (2.0) * (((sd[3]) * (sd[3]) + (sd[5] * sd[5]))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 7.888609052210118E-31) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(((_t13) * (2.0 * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]))) + (2.0 * ((sd[3]) * (sd[5]) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
        }
        dd[1] = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the X, Y and Z axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first, then Y,
     * then X) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must lie
     * in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-100}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXYZ(double epsilon, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_fma(epsilon, dest);
        return getEulerAnglesXYZ_mulAdd(epsilon, dest);
    }

    /** {@code getEulerAnglesXYZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_fma(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[4] * sd[5];
        double _t2 = sd[4] * sd[6];
        double _t10 = 2.0 * java.lang.Math.fma(sd[3], sd[5], _t2);
        double _t11 = 2.0 * java.lang.Math.fma(sd[3], sd[6], _t1);
        double _t12 = 2.0 * java.lang.Math.fma(sd[3], sd[6], -_t1);
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0);
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[3], sd[3], sd[5] * sd[5]), 1.0);
        double _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]), 2.0 * java.lang.Math.fma(sd[3], sd[5], -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
        }
        dd[1] = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        return dest;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[4] * sd[5];
        double _t2 = sd[4] * sd[6];
        double _t10 = 2.0 * ((sd[3]) * (sd[5]) + (_t2));
        double _t11 = 2.0 * ((sd[3]) * (sd[6]) + (_t1));
        double _t12 = 2.0 * ((sd[3]) * (sd[6]) - (_t1));
        double _t13 = ((1.0) - (2.0) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        double _t14 = ((1.0) - (2.0) * (((sd[3]) * (sd[3]) + (sd[5] * sd[5]))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dd[0] = Math.atan2(_t11, _t14);
            dd[2] = 0.0;
        } else {
            dd[0] = Math.atan2(_t12, _t13);
            dd[2] = Math.atan2(((_t13) * (2.0 * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]))) + (2.0 * ((sd[3]) * (sd[5]) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
        }
        dd[1] = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the X, Z and Y axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first, then Z,
     * then X) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXZY(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXZY_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[4] * sd[5];
        double _t9 = 2.0 * java.lang.Math.fma(sd[3], sd[6], _t1);
        double _t10 = 2.0 * java.lang.Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[3], sd[3], sd[5] * sd[5]), 1.0);
        double _t12 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0);
        double _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 7.888609052210118E-31) {
            dd[0] = Math.atan2(2.0 * java.lang.Math.fma(sd[3], sd[6], -_t1), _t12);
            dd[1] = 0.0;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(java.lang.Math.fma(2.0 * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]), _t9, _t11 * -2.0 * java.lang.Math.fma(sd[3], sd[5], sd[4] * -sd[6])), java.lang.Math.fma(_t12, _t11, _t9 * -2.0 * java.lang.Math.fma(sd[4], sd[5], sd[3] * -sd[6])));
        }
        dd[2] = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        return dest;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[4] * sd[5];
        double _t9 = 2.0 * ((sd[3]) * (sd[6]) + (_t1));
        double _t10 = 2.0 * ((sd[5]) * (sd[6]) - (sd[3] * sd[4]));
        double _t11 = ((1.0) - (2.0) * (((sd[3]) * (sd[3]) + (sd[5] * sd[5]))));
        double _t12 = ((1.0) - (2.0) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 7.888609052210118E-31) {
            dd[0] = Math.atan2(2.0 * ((sd[3]) * (sd[6]) - (_t1)), _t12);
            dd[1] = 0.0;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(((2.0 * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]))) * (_t9) - (_t11 * 2.0 * ((sd[3]) * (sd[5]) - (sd[4] * sd[6])))), ((_t12) * (_t11) - (_t9 * 2.0 * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])))));
        }
        dd[2] = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the X, Z and Y axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first, then Z,
     * then X) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must lie
     * in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-100}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXZY(double epsilon, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            double[] sd = this.data;
            double _t1 = sd[4] * sd[5];
            double _t9 = 2.0 * java.lang.Math.fma(sd[3], sd[6], _t1);
            double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[3], sd[3], sd[5] * sd[5]), 1.0);
            return getEulerAnglesXZY_scb45b304_1_fma(epsilon, dest, sd, ((Double3Impl) dest).data, _t1, _t9, 2.0 * java.lang.Math.fma(sd[5], sd[6], -(sd[3] * sd[4])), _t11, java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0), java.lang.Math.fma(_t11, _t11, _t9 * _t9));
        } else {
            double[] sd = this.data;
            double _t1 = sd[4] * sd[5];
            double _t9 = 2.0 * ((sd[3]) * (sd[6]) + (_t1));
            double _t11 = ((1.0) - (2.0) * (((sd[3]) * (sd[3]) + (sd[5] * sd[5]))));
            return getEulerAnglesXZY_scb45b304_1_mulAdd(epsilon, dest, sd, ((Double3Impl) dest).data, _t1, _t9, 2.0 * ((sd[5]) * (sd[6]) - (sd[3] * sd[4])), _t11, ((1.0) - (2.0) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4])))), ((_t11) * (_t11) + (_t9 * _t9)));
        }
    }

    /** Piece 2 of {@code getEulerAnglesXZY}, split to fit the inline budget; reached only through it. */
    private Double3 getEulerAnglesXZY_scb45b304_1_fma(double epsilon, Double3 dest, double[] sd, double[] dd, double _t1, double _t9, double _t10, double _t11, double _t12, double _t14) {
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dd[0] = Math.atan2(2.0 * java.lang.Math.fma(sd[3], sd[6], -_t1), _t12);
            dd[1] = 0.0;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(java.lang.Math.fma(2.0 * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]), _t9, _t11 * -2.0 * java.lang.Math.fma(sd[3], sd[5], sd[4] * -sd[6])), java.lang.Math.fma(_t12, _t11, _t9 * -2.0 * java.lang.Math.fma(sd[4], sd[5], sd[3] * -sd[6])));
        }
        dd[2] = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        return dest;
    }

    /** Piece 2 of {@code getEulerAnglesXZY}, split to fit the inline budget; reached only through it. */
    private Double3 getEulerAnglesXZY_scb45b304_1_mulAdd(double epsilon, Double3 dest, double[] sd, double[] dd, double _t1, double _t9, double _t10, double _t11, double _t12, double _t14) {
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dd[0] = Math.atan2(2.0 * ((sd[3]) * (sd[6]) - (_t1)), _t12);
            dd[1] = 0.0;
        } else {
            dd[0] = Math.atan2(_t9, _t11);
            dd[1] = Math.atan2(((2.0 * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]))) * (_t9) - (_t11 * 2.0 * ((sd[3]) * (sd[5]) - (sd[4] * sd[6])))), ((_t12) * (_t11) - (_t9 * 2.0 * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])))));
        }
        dd[2] = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the Y, X and Z axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first, then X,
     * then Y) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYXZ(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYXZ_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[4] * sd[5];
        double _t2 = sd[4] * sd[6];
        double _t9 = 2.0 * java.lang.Math.fma(sd[3], sd[5], _t2);
        double _t10 = 2.0 * java.lang.Math.fma(sd[3], sd[6], -_t0);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0);
        double _t12 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[4], sd[4], sd[5] * sd[5]), 1.0);
        double _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        dd[0] = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 7.888609052210118E-31) {
            dd[1] = Math.atan2(2.0 * java.lang.Math.fma(sd[4], sd[6], sd[3] * -sd[5]), _t12);
            dd[2] = 0.0;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(java.lang.Math.fma(_t9, 2.0 * java.lang.Math.fma(sd[3], sd[6], _t0), _t11 * -2.0 * java.lang.Math.fma(sd[3], sd[4], -sd[5] * sd[6])), java.lang.Math.fma(_t11, _t12, _t9 * -2.0 * java.lang.Math.fma(sd[3], sd[5], -_t2)));
        }
        return dest;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[4] * sd[5];
        double _t2 = sd[4] * sd[6];
        double _t9 = 2.0 * ((sd[3]) * (sd[5]) + (_t2));
        double _t10 = 2.0 * ((sd[3]) * (sd[6]) - (_t0));
        double _t11 = ((1.0) - (2.0) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        double _t12 = ((1.0) - (2.0) * (((sd[4]) * (sd[4]) + (sd[5] * sd[5]))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        dd[0] = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 7.888609052210118E-31) {
            dd[1] = Math.atan2(2.0 * ((sd[4]) * (sd[6]) - (sd[3] * sd[5])), _t12);
            dd[2] = 0.0;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(((_t9) * (2.0 * ((sd[3]) * (sd[6]) + (_t0))) - (_t11 * 2.0 * ((sd[3]) * (sd[4]) - (sd[5] * sd[6])))), ((_t11) * (_t12) - (_t9 * 2.0 * ((sd[3]) * (sd[5]) - (_t2)))));
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the Y, X and Z axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first, then X,
     * then Y) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must lie
     * in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-100}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYXZ(double epsilon, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYXZ_fma(epsilon, dest);
        return getEulerAnglesYXZ_mulAdd(epsilon, dest);
    }

    /** {@code getEulerAnglesYXZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_fma(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[4] * sd[5];
        double _t2 = sd[4] * sd[6];
        double _t9 = 2.0 * java.lang.Math.fma(sd[3], sd[5], _t2);
        double _t10 = 2.0 * java.lang.Math.fma(sd[3], sd[6], -_t0);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0);
        double _t12 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[4], sd[4], sd[5] * sd[5]), 1.0);
        double _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        dd[0] = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dd[1] = Math.atan2(2.0 * java.lang.Math.fma(sd[4], sd[6], sd[3] * -sd[5]), _t12);
            dd[2] = 0.0;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(java.lang.Math.fma(_t9, 2.0 * java.lang.Math.fma(sd[3], sd[6], _t0), _t11 * -2.0 * java.lang.Math.fma(sd[3], sd[4], -sd[5] * sd[6])), java.lang.Math.fma(_t11, _t12, _t9 * -2.0 * java.lang.Math.fma(sd[3], sd[5], -_t2)));
        }
        return dest;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[4] * sd[5];
        double _t2 = sd[4] * sd[6];
        double _t9 = 2.0 * ((sd[3]) * (sd[5]) + (_t2));
        double _t10 = 2.0 * ((sd[3]) * (sd[6]) - (_t0));
        double _t11 = ((1.0) - (2.0) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        double _t12 = ((1.0) - (2.0) * (((sd[4]) * (sd[4]) + (sd[5] * sd[5]))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        dd[0] = Math.atan2(_t10, java.lang.Math.sqrt(_t14));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dd[1] = Math.atan2(2.0 * ((sd[4]) * (sd[6]) - (sd[3] * sd[5])), _t12);
            dd[2] = 0.0;
        } else {
            dd[1] = Math.atan2(_t9, _t11);
            dd[2] = Math.atan2(((_t9) * (2.0 * ((sd[3]) * (sd[6]) + (_t0))) - (_t11 * 2.0 * ((sd[3]) * (sd[4]) - (sd[5] * sd[6])))), ((_t11) * (_t12) - (_t9 * 2.0 * ((sd[3]) * (sd[5]) - (_t2)))));
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the Y, Z and X axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first, then Z,
     * then Y) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYZX(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYZX_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t2 = sd[5] * sd[6];
        double _t11 = 2.0 * java.lang.Math.fma(sd[3], sd[4], _t2);
        double _t12 = 2.0 * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]);
        double _t13 = 2.0 * java.lang.Math.fma(sd[4], sd[6], -(sd[3] * sd[5]));
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[4], sd[4], sd[5] * sd[5]), 1.0);
        double _t15 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0);
        double _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= java.lang.Math.fma(_t11, _t11, _t17) * 7.888609052210118E-31) {
            dd[0] = 0.0;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t14, 2.0 * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]), 2.0 * java.lang.Math.fma(sd[3], sd[4], -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, java.lang.Math.sqrt(_t17));
        return dest;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t2 = sd[5] * sd[6];
        double _t11 = 2.0 * ((sd[3]) * (sd[4]) + (_t2));
        double _t12 = 2.0 * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]));
        double _t13 = 2.0 * ((sd[4]) * (sd[6]) - (sd[3] * sd[5]));
        double _t14 = ((1.0) - (2.0) * (((sd[4]) * (sd[4]) + (sd[5] * sd[5]))));
        double _t15 = ((1.0) - (2.0) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        double _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= ((_t11) * (_t11) + (_t17)) * 7.888609052210118E-31) {
            dd[0] = 0.0;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(((_t14) * (2.0 * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]))) + (2.0 * ((sd[3]) * (sd[4]) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13)));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, java.lang.Math.sqrt(_t17));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the Y, Z and X axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first, then Z,
     * then Y) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must lie
     * in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-100}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYZX(double epsilon, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYZX_fma(epsilon, dest);
        return getEulerAnglesYZX_mulAdd(epsilon, dest);
    }

    /** {@code getEulerAnglesYZX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_fma(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t2 = sd[5] * sd[6];
        double _t11 = 2.0 * java.lang.Math.fma(sd[3], sd[4], _t2);
        double _t12 = 2.0 * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]);
        double _t13 = 2.0 * java.lang.Math.fma(sd[4], sd[6], -(sd[3] * sd[5]));
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[4], sd[4], sd[5] * sd[5]), 1.0);
        double _t15 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[3], sd[3], sd[4] * sd[4]), 1.0);
        double _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= epsilon * java.lang.Math.fma(_t11, _t11, _t17)) {
            dd[0] = 0.0;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t14, 2.0 * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]), 2.0 * java.lang.Math.fma(sd[3], sd[4], -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, java.lang.Math.sqrt(_t17));
        return dest;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t2 = sd[5] * sd[6];
        double _t11 = 2.0 * ((sd[3]) * (sd[4]) + (_t2));
        double _t12 = 2.0 * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]));
        double _t13 = 2.0 * ((sd[4]) * (sd[6]) - (sd[3] * sd[5]));
        double _t14 = ((1.0) - (2.0) * (((sd[4]) * (sd[4]) + (sd[5] * sd[5]))));
        double _t15 = ((1.0) - (2.0) * (((sd[3]) * (sd[3]) + (sd[4] * sd[4]))));
        double _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= epsilon * ((_t11) * (_t11) + (_t17))) {
            dd[0] = 0.0;
            dd[1] = Math.atan2(_t12, _t15);
        } else {
            dd[0] = Math.atan2(((_t14) * (2.0 * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]))) + (2.0 * ((sd[3]) * (sd[4]) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13)));
            dd[1] = Math.atan2(_t13, _t14);
        }
        dd[2] = Math.atan2(_t11, java.lang.Math.sqrt(_t17));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the Z, X and Y axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first, then X,
     * then Z) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZXY(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZXY_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[5] * sd[5];
        double _t10 = 2.0 * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        double _t11 = 2.0 * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]);
        double _t12 = 2.0 * java.lang.Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[3], sd[3], _t1), 1.0);
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[4], sd[4], _t1), 1.0);
        double _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        dd[0] = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 7.888609052210118E-31) {
            dd[1] = 0.0;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]), 2.0 * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6])) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[5] * sd[5];
        double _t10 = 2.0 * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]));
        double _t11 = 2.0 * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]));
        double _t12 = 2.0 * ((sd[5]) * (sd[6]) - (sd[3] * sd[4]));
        double _t13 = ((1.0) - (2.0) * (((sd[3]) * (sd[3]) + (_t1))));
        double _t14 = ((1.0) - (2.0) * (((sd[4]) * (sd[4]) + (_t1))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        dd[0] = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 7.888609052210118E-31) {
            dd[1] = 0.0;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(((_t13) * (2.0 * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]))) + (2.0 * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the Z, X and Y axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first, then X,
     * then Z) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must lie
     * in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-100}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZXY(double epsilon, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZXY_fma(epsilon, dest);
        return getEulerAnglesZXY_mulAdd(epsilon, dest);
    }

    /** {@code getEulerAnglesZXY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_fma(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[5] * sd[5];
        double _t10 = 2.0 * java.lang.Math.fma(sd[3], sd[6], sd[4] * sd[5]);
        double _t11 = 2.0 * java.lang.Math.fma(sd[3], sd[4], sd[5] * sd[6]);
        double _t12 = 2.0 * java.lang.Math.fma(sd[5], sd[6], -(sd[3] * sd[4]));
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[3], sd[3], _t1), 1.0);
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[4], sd[4], _t1), 1.0);
        double _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        dd[0] = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dd[1] = 0.0;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]), 2.0 * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6])) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t1 = sd[5] * sd[5];
        double _t10 = 2.0 * ((sd[3]) * (sd[6]) + (sd[4] * sd[5]));
        double _t11 = 2.0 * ((sd[3]) * (sd[4]) + (sd[5] * sd[6]));
        double _t12 = 2.0 * ((sd[5]) * (sd[6]) - (sd[3] * sd[4]));
        double _t13 = ((1.0) - (2.0) * (((sd[3]) * (sd[3]) + (_t1))));
        double _t14 = ((1.0) - (2.0) * (((sd[4]) * (sd[4]) + (_t1))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        dd[0] = Math.atan2(_t10, java.lang.Math.sqrt(_t16));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dd[1] = 0.0;
            dd[2] = Math.atan2(_t11, _t14);
        } else {
            dd[1] = Math.atan2(((_t13) * (2.0 * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]))) + (2.0 * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])) * _t12)), ((_t13) * (_t14) + (_t11 * _t12)));
            dd[2] = Math.atan2(_t12, _t13);
        }
        return dest;
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the Z, Y and X axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first, then Y,
     * then Z) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZYX(@Mutated Double3 dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZYX_mulAdd(dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[5] * sd[5];
        double _t1 = sd[5] * sd[6];
        double _t8 = 2.0 * java.lang.Math.fma(sd[3], sd[4], _t1);
        double _t9 = 2.0 * java.lang.Math.fma(sd[4], sd[6], -sd[3] * sd[5]);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[4], sd[4], _t0), 1.0);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[3], sd[3], _t0), 1.0);
        double _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= java.lang.Math.fma(_t9, _t9, _t13) * 7.888609052210118E-31) {
            dd[0] = 0.0;
            dd[2] = Math.atan2(2.0 * java.lang.Math.fma(sd[5], sd[6], -sd[3] * sd[4]), _t11);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t8, 2.0 * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]), _t10 * -2.0 * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6]))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0 * java.lang.Math.fma(sd[3], sd[4], -_t1)));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[5] * sd[5];
        double _t1 = sd[5] * sd[6];
        double _t8 = 2.0 * ((sd[3]) * (sd[4]) + (_t1));
        double _t9 = 2.0 * ((sd[4]) * (sd[6]) - (sd[3] * sd[5]));
        double _t10 = ((1.0) - (2.0) * (((sd[4]) * (sd[4]) + (_t0))));
        double _t11 = ((1.0) - (2.0) * (((sd[3]) * (sd[3]) + (_t0))));
        double _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= ((_t9) * (_t9) + (_t13)) * 7.888609052210118E-31) {
            dd[0] = 0.0;
            dd[2] = Math.atan2(2.0 * ((sd[5]) * (sd[6]) - (sd[3] * sd[4])), _t11);
        } else {
            dd[0] = Math.atan2(((_t8) * (2.0 * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]))) - (_t10 * 2.0 * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])))), ((_t11) * (_t10) - (_t8 * 2.0 * ((sd[3]) * (sd[4]) - (_t1)))));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, java.lang.Math.sqrt(_t13));
        return dest;
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the Z, Y and X axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first, then Y,
     * then Z) and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0. The last angle is read off the
     * rotation the first one leaves, so the three rebuild the rotation to within rounding even next
     * to the gimbal lock.
     * <p>
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must lie
     * in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-100}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZYX(double epsilon, @Mutated Double3 dest) {
        if (org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZYX_fma(epsilon, dest);
        return getEulerAnglesZYX_mulAdd(epsilon, dest);
    }

    /** {@code getEulerAnglesZYX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_fma(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[5] * sd[5];
        double _t1 = sd[5] * sd[6];
        double _t8 = 2.0 * java.lang.Math.fma(sd[3], sd[4], _t1);
        double _t9 = 2.0 * java.lang.Math.fma(sd[4], sd[6], -sd[3] * sd[5]);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[4], sd[4], _t0), 1.0);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(sd[3], sd[3], _t0), 1.0);
        double _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= epsilon * java.lang.Math.fma(_t9, _t9, _t13)) {
            dd[0] = 0.0;
            dd[2] = Math.atan2(2.0 * java.lang.Math.fma(sd[5], sd[6], -sd[3] * sd[4]), _t11);
        } else {
            dd[0] = Math.atan2(java.lang.Math.fma(_t8, 2.0 * java.lang.Math.fma(sd[3], sd[5], sd[4] * sd[6]), _t10 * -2.0 * java.lang.Math.fma(sd[4], sd[5], -(sd[3] * sd[6]))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0 * java.lang.Math.fma(sd[3], sd[4], -_t1)));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd(double epsilon, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[5] * sd[5];
        double _t1 = sd[5] * sd[6];
        double _t8 = 2.0 * ((sd[3]) * (sd[4]) + (_t1));
        double _t9 = 2.0 * ((sd[4]) * (sd[6]) - (sd[3] * sd[5]));
        double _t10 = ((1.0) - (2.0) * (((sd[4]) * (sd[4]) + (_t0))));
        double _t11 = ((1.0) - (2.0) * (((sd[3]) * (sd[3]) + (_t0))));
        double _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= epsilon * ((_t9) * (_t9) + (_t13))) {
            dd[0] = 0.0;
            dd[2] = Math.atan2(2.0 * ((sd[5]) * (sd[6]) - (sd[3] * sd[4])), _t11);
        } else {
            dd[0] = Math.atan2(((_t8) * (2.0 * ((sd[3]) * (sd[5]) + (sd[4] * sd[6]))) - (_t10 * 2.0 * ((sd[4]) * (sd[5]) - (sd[3] * sd[6])))), ((_t11) * (_t10) - (_t8 * 2.0 * ((sd[3]) * (sd[4]) - (_t1)))));
            dd[2] = Math.atan2(_t8, _t10);
        }
        dd[1] = Math.atan2(_t9, java.lang.Math.sqrt(_t13));
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
        if (COL_NARROW) return getRotation_narrow(dest);
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 0);
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
    @Mutated public DoubleTransform makeRotationAxis(double angle, Double3R axis) {
        double[] axisData = ((Double3Impl) axis).data;
        double axisX = axisData[0];
        double axisY = axisData[1];
        double axisZ = axisData[2];
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sinWithCos(_t0);
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = axisX * _t1;
        dd[4] = axisY * _t1;
        dd[5] = axisZ * _t1;
        dd[6] = Math.cosWithSin(_t0);
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
        double _t1 = Math.sinWithCos(_t0);
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = axisX * _t1;
        dd[4] = axisY * _t1;
        dd[5] = axisZ * _t1;
        dd[6] = Math.cosWithSin(_t0);
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
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = Math.sinWithCos(_t0);
        dd[4] = 0.0;
        dd[5] = 0.0;
        dd[6] = Math.cosWithSin(_t0);
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }

    /**
     * Set this transform to a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians
     * about the X, Y and Z axes, in that order (intrinsic rotations, each about the axes the
     * previous ones turned: the matrix product {@code Rx * Ry * Rz}, so a vector is rotated about
     * the fixed Z axis first, then Y, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this
     */
    @Mutated public DoubleTransform makeRotationXYZ(double angleX, double angleY, double angleZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationXYZ_mulAdd(angleX, angleY, angleZ);
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t6;
        double _t12 = _t7 * _t4;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        dd[4] = java.lang.Math.fma(_t10, _t5, _t9 * -_t8);
        dd[5] = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        dd[6] = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }

    /** {@code makeRotationXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform makeRotationXYZ_mulAdd(double angleX, double angleY, double angleZ) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t6;
        double _t12 = _t7 * _t4;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = ((_t9) * (_t5) + (_t10 * _t8));
        dd[4] = ((_t10) * (_t5) - (_t9 * _t8));
        dd[5] = ((_t11) * (_t5) + (_t12 * _t8));
        dd[6] = ((_t12) * (_t5) - (_t11 * _t8));
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }

    /**
     * Set this transform to a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians
     * about the X, Z and Y axes, in that order (intrinsic rotations, each about the axes the
     * previous ones turned: the matrix product {@code Rx * Rz * Ry}, so a vector is rotated about
     * the fixed Y axis first, then Z, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this
     */
    @Mutated public DoubleTransform makeRotationXZY(double angleX, double angleZ, double angleY) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationXZY_mulAdd(angleX, angleZ, angleY);
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = java.lang.Math.fma(_t9, _t5, -(_t10 * _t8));
        dd[4] = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        dd[5] = java.lang.Math.fma(_t9, _t8, _t10 * _t5);
        dd[6] = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }

    /** {@code makeRotationXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform makeRotationXZY_mulAdd(double angleX, double angleZ, double angleY) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = ((_t9) * (_t5) - (_t10 * _t8));
        dd[4] = ((_t11) * (_t8) - (_t12 * _t5));
        dd[5] = ((_t9) * (_t8) + (_t10 * _t5));
        dd[6] = ((_t12) * (_t8) + (_t11 * _t5));
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
        if (COL_NARROW) return makeRotationY_narrow(angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        VEC_2.intoArray(dd, 0);
        dd[4] = Math.sinWithCos(_t0);
        dd[5] = 0.0;
        dd[6] = Math.cosWithSin(_t0);
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }

    /**
     * Set this transform to a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians
     * about the Y, X and Z axes, in that order (intrinsic rotations, each about the axes the
     * previous ones turned: the matrix product {@code Ry * Rx * Rz}, so a vector is rotated about
     * the fixed Z axis first, then X, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this
     */
    @Mutated public DoubleTransform makeRotationYXZ(double angleY, double angleX, double angleZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationYXZ_mulAdd(angleY, angleX, angleZ);
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        dd[4] = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        dd[5] = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        dd[6] = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }

    /** {@code makeRotationYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform makeRotationYXZ_mulAdd(double angleY, double angleX, double angleZ) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = ((_t9) * (_t5) + (_t10 * _t8));
        dd[4] = ((_t10) * (_t5) - (_t9 * _t8));
        dd[5] = ((_t11) * (_t8) - (_t12 * _t5));
        dd[6] = ((_t12) * (_t8) + (_t11 * _t5));
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }

    /**
     * Set this transform to a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians
     * about the Y, Z and X axes, in that order (intrinsic rotations, each about the axes the
     * previous ones turned: the matrix product {@code Ry * Rz * Rx}, so a vector is rotated about
     * the fixed X axis first, then Z, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this
     */
    @Mutated public DoubleTransform makeRotationYZX(double angleY, double angleZ, double angleX) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationYZX_mulAdd(angleY, angleZ, angleX);
        double[] dd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        dd[4] = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        dd[5] = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        dd[6] = java.lang.Math.fma(_t10, _t5, _t9 * -_t8);
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }

    /** {@code makeRotationYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform makeRotationYZX_mulAdd(double angleY, double angleZ, double angleX) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = ((_t9) * (_t5) + (_t10 * _t8));
        dd[4] = ((_t11) * (_t5) + (_t12 * _t8));
        dd[5] = ((_t12) * (_t5) - (_t11 * _t8));
        dd[6] = ((_t10) * (_t5) - (_t9 * _t8));
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
        if (COL_NARROW) return makeRotationZ_narrow(angle);
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        VEC_2.intoArray(dd, 0);
        dd[4] = 0.0;
        dd[5] = Math.sinWithCos(_t0);
        dd[6] = Math.cosWithSin(_t0);
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }

    /**
     * Set this transform to a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians
     * about the Z, X and Y axes, in that order (intrinsic rotations, each about the axes the
     * previous ones turned: the matrix product {@code Rz * Rx * Ry}, so a vector is rotated about
     * the fixed Y axis first, then X, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this
     */
    @Mutated public DoubleTransform makeRotationZXY(double angleZ, double angleX, double angleY) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationZXY_mulAdd(angleZ, angleX, angleY);
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t6;
        double _t12 = _t7 * _t4;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        dd[4] = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        dd[5] = java.lang.Math.fma(_t9, _t8, _t10 * _t5);
        dd[6] = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }

    /** {@code makeRotationZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform makeRotationZXY_mulAdd(double angleZ, double angleX, double angleY) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t6;
        double _t12 = _t7 * _t4;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = ((_t9) * (_t5) - (_t10 * _t8));
        dd[4] = ((_t11) * (_t5) + (_t12 * _t8));
        dd[5] = ((_t9) * (_t8) + (_t10 * _t5));
        dd[6] = ((_t12) * (_t5) - (_t11 * _t8));
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }

    /**
     * Set this transform to a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians
     * about the Z, Y and X axes, in that order (intrinsic rotations, each about the axes the
     * previous ones turned: the matrix product {@code Rz * Ry * Rx}, so a vector is rotated about
     * the fixed X axis first, then Y, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this
     */
    @Mutated public DoubleTransform makeRotationZYX(double angleZ, double angleY, double angleX) {
        if (!org.joml2.internal.FmaSwitch.ON) return makeRotationZYX_mulAdd(angleZ, angleY, angleX);
        double[] dd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = java.lang.Math.fma(_t9, _t5, -(_t10 * _t8));
        dd[4] = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        dd[5] = java.lang.Math.fma(_t12, _t8, -(_t11 * _t5));
        dd[6] = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }

    /** {@code makeRotationZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform makeRotationZYX_mulAdd(double angleZ, double angleY, double angleX) {
        double[] dd = this.data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = ((_t9) * (_t5) - (_t10 * _t8));
        dd[4] = ((_t11) * (_t8) + (_t12 * _t5));
        dd[5] = ((_t12) * (_t8) - (_t11 * _t5));
        dd[6] = ((_t10) * (_t5) + (_t9 * _t8));
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
    @Mutated public DoubleTransform makeScaling(Double3R scale) {
        double[] scaleData = ((Double3Impl) scale).data;
        if (COL_NARROW) return makeScaling_narrow(scale);
        double scaleX = scaleData[0];
        double scaleY = scaleData[1];
        double scaleZ = scaleData[2];
        double[] dd = this.data;
        VEC_2.intoArray(dd, 0);
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
        if (COL_NARROW) return makeScaling_narrow(scaleX, scaleY, scaleZ);
        double[] dd = this.data;
        VEC_2.intoArray(dd, 0);
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
        if (COL_NARROW) return makeScaling_narrow(scale);
        double[] dd = this.data;
        VEC_2.intoArray(dd, 0);
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
        double[] rotationData = ((DoubleQuatImpl) rotation).data;
        double rotationX = rotationData[0];
        double rotationY = rotationData[1];
        double rotationZ = rotationData[2];
        double rotationW = rotationData[3];
        if (!org.joml2.internal.FmaSwitch.ON) return rotate_mulAdd(rotationX, rotationY, rotationZ, rotationW, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = java.lang.Math.fma(rotationX, sd[6], rotationW * sd[3]) + java.lang.Math.fma(rotationZ, sd[4], -(rotationY * sd[5]));
        double _buf1 = java.lang.Math.fma(rotationY, sd[6], rotationW * sd[4]) + java.lang.Math.fma(rotationX, sd[5], rotationZ * -sd[3]);
        double _buf2 = java.lang.Math.fma(rotationZ, sd[6], rotationW * sd[5]) + java.lang.Math.fma(rotationY, sd[3], -(rotationX * sd[4]));
        dd[6] = java.lang.Math.fma(rotationW, sd[6], rotationX * -sd[3]) - java.lang.Math.fma(rotationY, sd[4], rotationZ * sd[5]);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotate_mulAdd(rotationX, rotationY, rotationZ, rotationW, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = java.lang.Math.fma(rotationX, sd[6], rotationW * sd[3]) + java.lang.Math.fma(rotationZ, sd[4], -(rotationY * sd[5]));
        double _buf1 = java.lang.Math.fma(rotationY, sd[6], rotationW * sd[4]) + java.lang.Math.fma(rotationX, sd[5], rotationZ * -sd[3]);
        double _buf2 = java.lang.Math.fma(rotationZ, sd[6], rotationW * sd[5]) + java.lang.Math.fma(rotationY, sd[3], -(rotationX * sd[4]));
        dd[6] = java.lang.Math.fma(rotationW, sd[6], rotationX * -sd[3]) - java.lang.Math.fma(rotationY, sd[4], rotationZ * sd[5]);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** {@code rotate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotate_mulAdd(double rotationX, double rotationY, double rotationZ, double rotationW, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = ((rotationX) * (sd[6]) + (rotationW * sd[3])) + ((rotationZ) * (sd[4]) - (rotationY * sd[5]));
        double _buf1 = ((rotationY) * (sd[6]) + (rotationW * sd[4])) + ((rotationX) * (sd[5]) - (rotationZ * sd[3]));
        double _buf2 = ((rotationZ) * (sd[6]) + (rotationW * sd[5])) + ((rotationY) * (sd[3]) - (rotationX * sd[4]));
        dd[6] = ((rotationW) * (sd[6]) - (rotationX * sd[3])) - ((rotationY) * (sd[4]) + (rotationZ * sd[5]));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
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
        double[] axisData = ((Double3Impl) axis).data;
        double axisX = axisData[0];
        double axisY = axisData[1];
        double axisZ = axisData[2];
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        if (org.joml2.internal.FmaSwitch.ON) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
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
        if (org.joml2.internal.FmaSwitch.ON) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateAxis_fma(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        double _t3 = axisX * _t2;
        double _t4 = axisZ * _t2;
        double _t5 = axisY * _t2;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateAxis_s78d6c85b_1_fma(dest, sd, dd, _t1, _t3, _t4, _t5, java.lang.Math.fma(sd[3], _t1, sd[6] * _t3) + java.lang.Math.fma(sd[4], _t4, -(sd[5] * _t5)), java.lang.Math.fma(sd[4], _t1, sd[6] * _t5) + java.lang.Math.fma(sd[5], _t3, -sd[3] * _t4), java.lang.Math.fma(sd[5], _t1, sd[6] * _t4) + java.lang.Math.fma(sd[3], _t5, -(sd[4] * _t3)));
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateAxis_mulAdd(double angle, double axisX, double axisY, double axisZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        double _t3 = axisX * _t2;
        double _t4 = axisZ * _t2;
        double _t5 = axisY * _t2;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = ((sd[3]) * (_t1) + (sd[6] * _t3)) + ((sd[4]) * (_t4) - (sd[5] * _t5));
        double _buf1 = ((sd[4]) * (_t1) + (sd[6] * _t5)) + ((sd[5]) * (_t3) - (sd[3] * _t4));
        double _buf2 = ((sd[5]) * (_t1) + (sd[6] * _t4)) + ((sd[3]) * (_t5) - (sd[4] * _t3));
        dd[6] = ((sd[6]) * (_t1) - (sd[3] * _t3)) - ((sd[4]) * (_t5) + (sd[5] * _t4));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateAxis_s78d6c85b_1_fma(DoubleTransform dest, double[] sd, double[] dd, double _t1, double _t3, double _t4, double _t5, double _buf0, double _buf1, double _buf2) {
        dd[6] = java.lang.Math.fma(sd[6], _t1, -sd[3] * _t3) - java.lang.Math.fma(sd[4], _t5, sd[5] * _t4);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateX_mulAdd(angle, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = java.lang.Math.fma(sd[3], _t1, sd[6] * _t2);
        double _buf1 = java.lang.Math.fma(sd[4], _t1, sd[5] * _t2);
        dd[5] = java.lang.Math.fma(sd[5], _t1, -(sd[4] * _t2));
        dd[6] = java.lang.Math.fma(sd[6], _t1, -(sd[3] * _t2));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        return dest;
    }

    /** {@code rotateX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateX_mulAdd(double angle, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = ((sd[3]) * (_t1) + (sd[6] * _t2));
        double _buf1 = ((sd[4]) * (_t1) + (sd[5] * _t2));
        dd[5] = ((sd[5]) * (_t1) - (sd[4] * _t2));
        dd[6] = ((sd[6]) * (_t1) - (sd[3] * _t2));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X), to this transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateXYZ_mulAdd(angleX, angleY, angleZ, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        double _t19 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        double _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        double _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        double _t22 = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateXYZ_s1482cb77_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[3], _t21, sd[6] * _t19) + java.lang.Math.fma(sd[4], _t20, -(sd[5] * _t22)), java.lang.Math.fma(sd[4], _t21, sd[6] * _t22) + java.lang.Math.fma(sd[5], _t19, -sd[3] * _t20));
    }

    /** {@code rotateXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateXYZ_mulAdd(double angleX, double angleY, double angleZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        double _t19 = ((_t11) * (_t5) + (_t12 * _t8));
        double _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        double _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        double _t22 = ((_t12) * (_t5) - (_t11 * _t8));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateXYZ_s1482cb77_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, ((sd[3]) * (_t21) + (sd[6] * _t19)) + ((sd[4]) * (_t20) - (sd[5] * _t22)), ((sd[4]) * (_t21) + (sd[6] * _t22)) + ((sd[5]) * (_t19) - (sd[3] * _t20)));
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateXYZ_s1482cb77_1_fma(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = java.lang.Math.fma(sd[5], _t21, sd[6] * _t20) + java.lang.Math.fma(sd[3], _t22, -(sd[4] * _t19));
        dd[6] = java.lang.Math.fma(sd[6], _t21, -sd[3] * _t19) - java.lang.Math.fma(sd[4], _t22, sd[5] * _t20);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateXYZ_s1482cb77_1_mulAdd(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = ((sd[5]) * (_t21) + (sd[6] * _t20)) + ((sd[3]) * (_t22) - (sd[4] * _t19));
        dd[6] = ((sd[6]) * (_t21) - (sd[3] * _t19)) - ((sd[4]) * (_t22) + (sd[5] * _t20));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X), to this transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateXZY_mulAdd(angleX, angleZ, angleY, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        double _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        double _t20 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        double _t21 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        double _t22 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateXZY_s793a461b_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[3], _t19, sd[6] * _t21) + java.lang.Math.fma(sd[4], _t20, -(sd[5] * _t22)), java.lang.Math.fma(sd[4], _t19, sd[6] * _t22) + java.lang.Math.fma(sd[5], _t21, -sd[3] * _t20));
    }

    /** {@code rotateXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateXZY_mulAdd(double angleX, double angleZ, double angleY, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        double _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        double _t20 = ((_t11) * (_t5) + (_t12 * _t8));
        double _t21 = ((_t11) * (_t8) - (_t12 * _t5));
        double _t22 = ((_t10) * (_t5) - (_t9 * _t8));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateXZY_s793a461b_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, ((sd[3]) * (_t19) + (sd[6] * _t21)) + ((sd[4]) * (_t20) - (sd[5] * _t22)), ((sd[4]) * (_t19) + (sd[6] * _t22)) + ((sd[5]) * (_t21) - (sd[3] * _t20)));
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateXZY_s793a461b_1_fma(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = java.lang.Math.fma(sd[5], _t19, sd[6] * _t20) + java.lang.Math.fma(sd[3], _t22, -(sd[4] * _t21));
        dd[6] = java.lang.Math.fma(sd[6], _t19, -sd[3] * _t21) - java.lang.Math.fma(sd[4], _t22, sd[5] * _t20);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateXZY_s793a461b_1_mulAdd(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = ((sd[5]) * (_t19) + (sd[6] * _t20)) + ((sd[3]) * (_t22) - (sd[4] * _t21));
        dd[6] = ((sd[6]) * (_t19) - (sd[3] * _t21)) - ((sd[4]) * (_t22) + (sd[5] * _t20));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateY_mulAdd(angle, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = java.lang.Math.fma(sd[3], _t1, -(sd[5] * _t2));
        double _buf1 = java.lang.Math.fma(sd[4], _t1, sd[6] * _t2);
        dd[5] = java.lang.Math.fma(sd[3], _t2, sd[5] * _t1);
        dd[6] = java.lang.Math.fma(sd[6], _t1, -(sd[4] * _t2));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        return dest;
    }

    /** {@code rotateY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateY_mulAdd(double angle, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = ((sd[3]) * (_t1) - (sd[5] * _t2));
        double _buf1 = ((sd[4]) * (_t1) + (sd[6] * _t2));
        dd[5] = ((sd[3]) * (_t2) + (sd[5] * _t1));
        dd[6] = ((sd[6]) * (_t1) - (sd[4] * _t2));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y), to this transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateYXZ_mulAdd(angleY, angleX, angleZ, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        double _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        double _t20 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        double _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        double _t22 = java.lang.Math.fma(_t12, _t8, -(_t11 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateYXZ_sf37d62e3_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[3], _t19, sd[6] * _t20) + java.lang.Math.fma(sd[4], _t21, -(sd[5] * _t22)), java.lang.Math.fma(sd[4], _t19, sd[6] * _t22) + java.lang.Math.fma(sd[5], _t20, -sd[3] * _t21));
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateYXZ_mulAdd(double angleY, double angleX, double angleZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        double _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        double _t20 = ((_t11) * (_t8) + (_t12 * _t5));
        double _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        double _t22 = ((_t12) * (_t8) - (_t11 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateYXZ_sf37d62e3_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, ((sd[3]) * (_t19) + (sd[6] * _t20)) + ((sd[4]) * (_t21) - (sd[5] * _t22)), ((sd[4]) * (_t19) + (sd[6] * _t22)) + ((sd[5]) * (_t20) - (sd[3] * _t21)));
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateYXZ_sf37d62e3_1_fma(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = java.lang.Math.fma(sd[5], _t19, sd[6] * _t21) + java.lang.Math.fma(sd[3], _t22, -(sd[4] * _t20));
        dd[6] = java.lang.Math.fma(sd[6], _t19, -sd[3] * _t20) - java.lang.Math.fma(sd[4], _t22, sd[5] * _t21);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateYXZ_sf37d62e3_1_mulAdd(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = ((sd[5]) * (_t19) + (sd[6] * _t21)) + ((sd[3]) * (_t22) - (sd[4] * _t20));
        dd[6] = ((sd[6]) * (_t19) - (sd[3] * _t20)) - ((sd[4]) * (_t22) + (sd[5] * _t21));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y), to this transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateYZX_mulAdd(angleY, angleZ, angleX, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t3;
        double _t12 = _t6 * _t4;
        double _t19 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        double _t20 = java.lang.Math.fma(_t12, _t5, _t11 * _t8);
        double _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        double _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateYZX_s155e0a53_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[3], _t21, sd[6] * _t19) + java.lang.Math.fma(sd[4], _t22, -(sd[5] * _t20)), java.lang.Math.fma(sd[4], _t21, sd[6] * _t20) + java.lang.Math.fma(sd[5], _t19, -sd[3] * _t22));
    }

    /** {@code rotateYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateYZX_mulAdd(double angleY, double angleZ, double angleX, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t3;
        double _t12 = _t6 * _t4;
        double _t19 = ((_t10) * (_t5) + (_t9 * _t8));
        double _t20 = ((_t12) * (_t5) + (_t11 * _t8));
        double _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        double _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateYZX_s155e0a53_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, ((sd[3]) * (_t21) + (sd[6] * _t19)) + ((sd[4]) * (_t22) - (sd[5] * _t20)), ((sd[4]) * (_t21) + (sd[6] * _t20)) + ((sd[5]) * (_t19) - (sd[3] * _t22)));
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateYZX_s155e0a53_1_fma(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = java.lang.Math.fma(sd[5], _t21, sd[6] * _t22) + java.lang.Math.fma(sd[3], _t20, -(sd[4] * _t19));
        dd[6] = java.lang.Math.fma(sd[6], _t21, -sd[3] * _t19) - java.lang.Math.fma(sd[4], _t20, sd[5] * _t22);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateYZX_s155e0a53_1_mulAdd(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = ((sd[5]) * (_t21) + (sd[6] * _t22)) + ((sd[3]) * (_t20) - (sd[4] * _t19));
        dd[6] = ((sd[6]) * (_t21) - (sd[3] * _t19)) - ((sd[4]) * (_t20) + (sd[5] * _t22));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateZ_mulAdd(angle, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = java.lang.Math.fma(sd[3], _t1, sd[4] * _t2);
        dd[4] = java.lang.Math.fma(sd[4], _t1, -(sd[3] * _t2));
        double _buf1 = java.lang.Math.fma(sd[5], _t1, sd[6] * _t2);
        dd[6] = java.lang.Math.fma(sd[6], _t1, -(sd[5] * _t2));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[5] = _buf1;
        return dest;
    }

    /** {@code rotateZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateZ_mulAdd(double angle, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        double _buf0 = ((sd[3]) * (_t1) + (sd[4] * _t2));
        dd[4] = ((sd[4]) * (_t1) - (sd[3] * _t2));
        double _buf1 = ((sd[5]) * (_t1) + (sd[6] * _t2));
        dd[6] = ((sd[6]) * (_t1) - (sd[5] * _t2));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[5] = _buf1;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z), to this transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateZXY_mulAdd(angleZ, angleX, angleY, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        double _t19 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        double _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        double _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        double _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateZXY_s566c1933_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[3], _t21, sd[6] * _t22) + java.lang.Math.fma(sd[4], _t19, -(sd[5] * _t20)), java.lang.Math.fma(sd[4], _t21, sd[6] * _t20) + java.lang.Math.fma(sd[5], _t22, -sd[3] * _t19));
    }

    /** {@code rotateZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateZXY_mulAdd(double angleZ, double angleX, double angleY, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        double _t19 = ((_t11) * (_t8) + (_t12 * _t5));
        double _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        double _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        double _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateZXY_s566c1933_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, ((sd[3]) * (_t21) + (sd[6] * _t22)) + ((sd[4]) * (_t19) - (sd[5] * _t20)), ((sd[4]) * (_t21) + (sd[6] * _t20)) + ((sd[5]) * (_t22) - (sd[3] * _t19)));
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateZXY_s566c1933_1_fma(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = java.lang.Math.fma(sd[5], _t21, sd[6] * _t19) + java.lang.Math.fma(sd[3], _t20, -(sd[4] * _t22));
        dd[6] = java.lang.Math.fma(sd[6], _t21, -sd[3] * _t22) - java.lang.Math.fma(sd[4], _t20, sd[5] * _t19);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateZXY_s566c1933_1_mulAdd(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = ((sd[5]) * (_t21) + (sd[6] * _t19)) + ((sd[3]) * (_t20) - (sd[4] * _t22));
        dd[6] = ((sd[6]) * (_t21) - (sd[3] * _t22)) - ((sd[4]) * (_t20) + (sd[5] * _t19));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z), to this transform and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return rotateZYX_mulAdd(angleZ, angleY, angleX, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t3 * _t7;
        double _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        double _t20 = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        double _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        double _t22 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateZYX_sb3442857_1_fma(dest, sd, dd, _t19, _t20, _t21, _t22, java.lang.Math.fma(sd[3], _t19, sd[6] * _t21) + java.lang.Math.fma(sd[4], _t22, -(sd[5] * _t20)), java.lang.Math.fma(sd[4], _t19, sd[6] * _t20) + java.lang.Math.fma(sd[5], _t21, -sd[3] * _t22));
    }

    /** {@code rotateZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateZYX_mulAdd(double angleZ, double angleY, double angleX, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t3 * _t7;
        double _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        double _t20 = ((_t12) * (_t8) + (_t11 * _t5));
        double _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        double _t22 = ((_t11) * (_t8) - (_t12 * _t5));
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateZYX_sb3442857_1_mulAdd(dest, sd, dd, _t19, _t20, _t21, _t22, ((sd[3]) * (_t19) + (sd[6] * _t21)) + ((sd[4]) * (_t22) - (sd[5] * _t20)), ((sd[4]) * (_t19) + (sd[6] * _t20)) + ((sd[5]) * (_t21) - (sd[3] * _t22)));
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateZYX_sb3442857_1_fma(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = java.lang.Math.fma(sd[5], _t19, sd[6] * _t22) + java.lang.Math.fma(sd[3], _t20, -(sd[4] * _t21));
        dd[6] = java.lang.Math.fma(sd[6], _t19, -sd[3] * _t21) - java.lang.Math.fma(sd[4], _t20, sd[5] * _t22);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateZYX_sb3442857_1_mulAdd(DoubleTransform dest, double[] sd, double[] dd, double _t19, double _t20, double _t21, double _t22, double _buf0, double _buf1) {
        double _buf2 = ((sd[5]) * (_t19) + (sd[6] * _t22)) + ((sd[3]) * (_t20) - (sd[4] * _t21));
        dd[6] = ((sd[6]) * (_t19) - (sd[3] * _t21)) - ((sd[4]) * (_t20) + (sd[5] * _t22));
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
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
        double[] scaleData = ((Double3Impl) scale).data;
        if (COL_NARROW) return scale_narrow(scale, dest);
        double scaleX = scaleData[0];
        double scaleY = scaleData[1];
        double scaleZ = scaleData[2];
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
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
        if (COL_NARROW) return scale_narrow(scaleX, scaleY, scaleZ, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
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
        if (COL_NARROW) return scale_narrow(scale, dest);
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        DoubleVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
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
        double[] translationData = ((Double3Impl) translation).data;
        if (COL_NARROW) return translate_narrow(translation, dest);
        double translationX = translationData[0];
        double translationY = translationData[1];
        double translationZ = translationData[2];
        if (org.joml2.internal.FmaSwitch.ON) return translate_fma(translationX, translationY, translationZ, dest);
        return translate_mulAdd(translationX, translationY, translationZ, dest);
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
        if (COL_NARROW) return translate_narrow(translationX, translationY, translationZ, dest);
        if (org.joml2.internal.FmaSwitch.ON) return translate_fma(translationX, translationY, translationZ, dest);
        return translate_mulAdd(translationX, translationY, translationZ, dest);
    }

    /** {@code translate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform translate_fma(double translationX, double translationY, double translationZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = sd[8] * translationY;
        double _t1 = sd[7] * translationX;
        double _t2 = sd[9] * translationZ;
        double _t12 = 2.0 * java.lang.Math.fma(sd[3], _t0, -(sd[4] * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(sd[5], _t1, -(sd[3] * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(sd[4], _t2, -(sd[5] * _t0));
        dd[0] = java.lang.Math.fma(sd[4], _t12, java.lang.Math.fma(-sd[5], _t13, java.lang.Math.fma(sd[6], _t14, java.lang.Math.fma(sd[7], translationX, sd[0]))));
        dd[1] = java.lang.Math.fma(sd[5], _t14, java.lang.Math.fma(-sd[3], _t12, java.lang.Math.fma(sd[6], _t13, java.lang.Math.fma(sd[8], translationY, sd[1]))));
        dd[2] = java.lang.Math.fma(sd[3], _t13, java.lang.Math.fma(-sd[4], _t14, java.lang.Math.fma(sd[6], _t12, java.lang.Math.fma(sd[9], translationZ, sd[2]))));
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }

    /** {@code translate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform translate_mulAdd(double translationX, double translationY, double translationZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = sd[8] * translationY;
        double _t1 = sd[7] * translationX;
        double _t2 = sd[9] * translationZ;
        double _t12 = 2.0 * ((sd[3]) * (_t0) - (sd[4] * _t1));
        double _t13 = 2.0 * ((sd[5]) * (_t1) - (sd[3] * _t2));
        double _t14 = 2.0 * ((sd[4]) * (_t2) - (sd[5] * _t0));
        dd[0] = ((sd[4]) * (_t12) + (((((sd[6]) * (_t14) + (((sd[7]) * (translationX) + (sd[0]))))) - (sd[5]) * (_t13))));
        dd[1] = ((sd[5]) * (_t14) + (((((sd[6]) * (_t13) + (((sd[8]) * (translationY) + (sd[1]))))) - (sd[3]) * (_t12))));
        dd[2] = ((sd[3]) * (_t13) + (((((sd[6]) * (_t12) + (((sd[9]) * (translationZ) + (sd[2]))))) - (sd[4]) * (_t14))));
        DoubleVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
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
        double[] vData = ((Double3Impl) v).data;
        double vX = vData[0];
        double vY = vData[1];
        double vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(vX, vY, vZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[8] * vY;
        double _t1 = sd[7] * vX;
        double _t2 = sd[9] * vZ;
        double _t12 = 2.0 * java.lang.Math.fma(sd[3], _t0, -(sd[4] * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(sd[5], _t1, -(sd[3] * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(sd[4], _t2, -(sd[5] * _t0));
        dd[0] = java.lang.Math.fma(sd[4], _t12, java.lang.Math.fma(-sd[5], _t13, java.lang.Math.fma(sd[6], _t14, java.lang.Math.fma(sd[7], vX, sd[0]))));
        dd[1] = java.lang.Math.fma(sd[5], _t14, java.lang.Math.fma(-sd[3], _t12, java.lang.Math.fma(sd[6], _t13, java.lang.Math.fma(sd[8], vY, sd[1]))));
        dd[2] = java.lang.Math.fma(sd[3], _t13, java.lang.Math.fma(-sd[4], _t14, java.lang.Math.fma(sd[6], _t12, java.lang.Math.fma(sd[9], vZ, sd[2]))));
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
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(vX, vY, vZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[8] * vY;
        double _t1 = sd[7] * vX;
        double _t2 = sd[9] * vZ;
        double _t12 = 2.0 * java.lang.Math.fma(sd[3], _t0, -(sd[4] * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(sd[5], _t1, -(sd[3] * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(sd[4], _t2, -(sd[5] * _t0));
        dd[0] = java.lang.Math.fma(sd[4], _t12, java.lang.Math.fma(-sd[5], _t13, java.lang.Math.fma(sd[6], _t14, java.lang.Math.fma(sd[7], vX, sd[0]))));
        dd[1] = java.lang.Math.fma(sd[5], _t14, java.lang.Math.fma(-sd[3], _t12, java.lang.Math.fma(sd[6], _t13, java.lang.Math.fma(sd[8], vY, sd[1]))));
        dd[2] = java.lang.Math.fma(sd[3], _t13, java.lang.Math.fma(-sd[4], _t14, java.lang.Math.fma(sd[6], _t12, java.lang.Math.fma(sd[9], vZ, sd[2]))));
        return dest;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transform_mulAdd(double vX, double vY, double vZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[8] * vY;
        double _t1 = sd[7] * vX;
        double _t2 = sd[9] * vZ;
        double _t12 = 2.0 * ((sd[3]) * (_t0) - (sd[4] * _t1));
        double _t13 = 2.0 * ((sd[5]) * (_t1) - (sd[3] * _t2));
        double _t14 = 2.0 * ((sd[4]) * (_t2) - (sd[5] * _t0));
        dd[0] = ((sd[4]) * (_t12) + (((((sd[6]) * (_t14) + (((sd[7]) * (vX) + (sd[0]))))) - (sd[5]) * (_t13))));
        dd[1] = ((sd[5]) * (_t14) + (((((sd[6]) * (_t13) + (((sd[8]) * (vY) + (sd[1]))))) - (sd[3]) * (_t12))));
        dd[2] = ((sd[3]) * (_t13) + (((((sd[6]) * (_t12) + (((sd[9]) * (vZ) + (sd[2]))))) - (sd[4]) * (_t14))));
        return dest;
    }

    /**
     * Transform the given direction by the rotation part of this transform, ignoring translation
     * and scale, and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirection(Double3R v, @Mutated Double3 dest) {
        double[] vData = ((Double3Impl) v).data;
        double vX = vData[0];
        double vY = vData[1];
        double vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirection_mulAdd(vX, vY, vZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[3], vY, -(sd[4] * vX));
        double _t10 = 2.0 * java.lang.Math.fma(sd[5], vX, -(sd[3] * vZ));
        double _t11 = 2.0 * java.lang.Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, vY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, vZ)));
        return dest;
    }

    /**
     * Transform the given direction by the rotation part of this transform, ignoring translation
     * and scale, and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirection_mulAdd(vX, vY, vZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[3], vY, -(sd[4] * vX));
        double _t10 = 2.0 * java.lang.Math.fma(sd[5], vX, -(sd[3] * vZ));
        double _t11 = 2.0 * java.lang.Math.fma(sd[4], vZ, -(sd[5] * vY));
        dd[0] = java.lang.Math.fma(sd[4], _t9, java.lang.Math.fma(-sd[5], _t10, java.lang.Math.fma(sd[6], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[5], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, vY)));
        dd[2] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[4], _t11, java.lang.Math.fma(sd[6], _t9, vZ)));
        return dest;
    }

    /** {@code transformDirection} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformDirection_mulAdd(double vX, double vY, double vZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[3]) * (vY) - (sd[4] * vX));
        double _t10 = 2.0 * ((sd[5]) * (vX) - (sd[3] * vZ));
        double _t11 = 2.0 * ((sd[4]) * (vZ) - (sd[5] * vY));
        dd[0] = ((sd[4]) * (_t9) + (((((sd[6]) * (_t11) + (vX))) - (sd[5]) * (_t10))));
        dd[1] = ((sd[5]) * (_t11) + (((((sd[6]) * (_t10) + (vY))) - (sd[3]) * (_t9))));
        dd[2] = ((sd[3]) * (_t10) + (((((sd[6]) * (_t9) + (vZ))) - (sd[4]) * (_t11))));
        return dest;
    }

    /**
     * Transform the given direction by the inverse of this transform's rotation (world to local),
     * ignoring translation and scale, without materializing {@code invert()}, and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirectionInverse(Double3R v, @Mutated Double3 dest) {
        double[] vData = ((Double3Impl) v).data;
        double vX = vData[0];
        double vY = vData[1];
        double vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirectionInverse_mulAdd(vX, vY, vZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[3], vZ, -(sd[5] * vX));
        double _t10 = 2.0 * java.lang.Math.fma(sd[4], vX, -(sd[3] * vY));
        double _t11 = 2.0 * java.lang.Math.fma(sd[5], vY, -(sd[4] * vZ));
        dd[0] = java.lang.Math.fma(sd[5], _t9, java.lang.Math.fma(-sd[4], _t10, java.lang.Math.fma(sd[6], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[5], _t11, java.lang.Math.fma(sd[6], _t9, vY)));
        dd[2] = java.lang.Math.fma(sd[4], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, vZ)));
        return dest;
    }

    /**
     * Transform the given direction by the inverse of this transform's rotation (world to local),
     * ignoring translation and scale, without materializing {@code invert()}, and store the result
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformDirectionInverse_mulAdd(vX, vY, vZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[3], vZ, -(sd[5] * vX));
        double _t10 = 2.0 * java.lang.Math.fma(sd[4], vX, -(sd[3] * vY));
        double _t11 = 2.0 * java.lang.Math.fma(sd[5], vY, -(sd[4] * vZ));
        dd[0] = java.lang.Math.fma(sd[5], _t9, java.lang.Math.fma(-sd[4], _t10, java.lang.Math.fma(sd[6], _t11, vX)));
        dd[1] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[5], _t11, java.lang.Math.fma(sd[6], _t9, vY)));
        dd[2] = java.lang.Math.fma(sd[4], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, vZ)));
        return dest;
    }

    /** {@code transformDirectionInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformDirectionInverse_mulAdd(double vX, double vY, double vZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[3]) * (vZ) - (sd[5] * vX));
        double _t10 = 2.0 * ((sd[4]) * (vX) - (sd[3] * vY));
        double _t11 = 2.0 * ((sd[5]) * (vY) - (sd[4] * vZ));
        dd[0] = ((sd[5]) * (_t9) + (((((sd[6]) * (_t11) + (vX))) - (sd[4]) * (_t10))));
        dd[1] = ((sd[3]) * (_t10) + (((((sd[6]) * (_t9) + (vY))) - (sd[5]) * (_t11))));
        dd[2] = ((sd[4]) * (_t11) + (((((sd[6]) * (_t10) + (vZ))) - (sd[3]) * (_t9))));
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
        double[] pData = ((Double3Impl) p).data;
        double pX = pData[0];
        double pY = pData[1];
        double pZ = pData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transformInverse_mulAdd(pX, pY, pZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        double _t12 = 2.0 * java.lang.Math.fma(sd[3], _t0, -(sd[5] * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(sd[4], _t1, -(sd[3] * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(sd[5], _t2, -(sd[4] * _t0));
        dd[0] = java.lang.Math.fma(sd[5], _t12, java.lang.Math.fma(-sd[4], _t13, java.lang.Math.fma(sd[6], _t14, _t1))) / sd[7];
        dd[1] = java.lang.Math.fma(sd[3], _t13, java.lang.Math.fma(-sd[5], _t14, java.lang.Math.fma(sd[6], _t12, _t2))) / sd[8];
        dd[2] = java.lang.Math.fma(sd[4], _t14, java.lang.Math.fma(-sd[3], _t12, java.lang.Math.fma(sd[6], _t13, _t0))) / sd[9];
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformInverse_mulAdd(pX, pY, pZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        double _t12 = 2.0 * java.lang.Math.fma(sd[3], _t0, -(sd[5] * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(sd[4], _t1, -(sd[3] * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(sd[5], _t2, -(sd[4] * _t0));
        dd[0] = java.lang.Math.fma(sd[5], _t12, java.lang.Math.fma(-sd[4], _t13, java.lang.Math.fma(sd[6], _t14, _t1))) / sd[7];
        dd[1] = java.lang.Math.fma(sd[3], _t13, java.lang.Math.fma(-sd[5], _t14, java.lang.Math.fma(sd[6], _t12, _t2))) / sd[8];
        dd[2] = java.lang.Math.fma(sd[4], _t14, java.lang.Math.fma(-sd[3], _t12, java.lang.Math.fma(sd[6], _t13, _t0))) / sd[9];
        return dest;
    }

    /** {@code transformInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformInverse_mulAdd(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        double _t12 = 2.0 * ((sd[3]) * (_t0) - (sd[5] * _t1));
        double _t13 = 2.0 * ((sd[4]) * (_t1) - (sd[3] * _t2));
        double _t14 = 2.0 * ((sd[5]) * (_t2) - (sd[4] * _t0));
        dd[0] = ((sd[5]) * (_t12) + (((((sd[6]) * (_t14) + (_t1))) - (sd[4]) * (_t13)))) / sd[7];
        dd[1] = ((sd[3]) * (_t13) + (((((sd[6]) * (_t12) + (_t2))) - (sd[5]) * (_t14)))) / sd[8];
        dd[2] = ((sd[4]) * (_t14) + (((((sd[6]) * (_t13) + (_t0))) - (sd[3]) * (_t12)))) / sd[9];
        return dest;
    }

    /**
     * Transform the given position by this transform, treating it as a point with an implicit
     * {@code w = 1}, and store the result in {@code dest}.
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
     * {@code w = 1}, and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(vX, vY, vZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[8] * vY;
        double _t1 = sd[7] * vX;
        double _t2 = sd[9] * vZ;
        double _t12 = 2.0 * java.lang.Math.fma(sd[3], _t0, -(sd[4] * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(sd[5], _t1, -(sd[3] * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(sd[4], _t2, -(sd[5] * _t0));
        dd[0] = java.lang.Math.fma(sd[4], _t12, java.lang.Math.fma(-sd[5], _t13, java.lang.Math.fma(sd[6], _t14, java.lang.Math.fma(sd[7], vX, sd[0]))));
        dd[1] = java.lang.Math.fma(sd[5], _t14, java.lang.Math.fma(-sd[3], _t12, java.lang.Math.fma(sd[6], _t13, java.lang.Math.fma(sd[8], vY, sd[1]))));
        dd[2] = java.lang.Math.fma(sd[3], _t13, java.lang.Math.fma(-sd[4], _t14, java.lang.Math.fma(sd[6], _t12, java.lang.Math.fma(sd[9], vZ, sd[2]))));
        return dest;
    }

    /**
     * Transform the given position by the inverse of this transform (world to local), without
     * materializing {@code invert()}, and store the result in {@code dest}.
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
     * materializing {@code invert()}, and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformInverse_mulAdd(pX, pY, pZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = pZ - sd[2];
        double _t1 = pX - sd[0];
        double _t2 = pY - sd[1];
        double _t12 = 2.0 * java.lang.Math.fma(sd[3], _t0, -(sd[5] * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(sd[4], _t1, -(sd[3] * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(sd[5], _t2, -(sd[4] * _t0));
        dd[0] = java.lang.Math.fma(sd[5], _t12, java.lang.Math.fma(-sd[4], _t13, java.lang.Math.fma(sd[6], _t14, _t1))) / sd[7];
        dd[1] = java.lang.Math.fma(sd[3], _t13, java.lang.Math.fma(-sd[5], _t14, java.lang.Math.fma(sd[6], _t12, _t2))) / sd[8];
        dd[2] = java.lang.Math.fma(sd[4], _t14, java.lang.Math.fma(-sd[3], _t12, java.lang.Math.fma(sd[6], _t13, _t0))) / sd[9];
        return dest;
    }

    /**
     * Transform the given vector by the linear part of this transform, i.e. apply its scale and
     * rotation but not its translation, and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformVector(Double3R v, @Mutated Double3 dest) {
        double[] vData = ((Double3Impl) v).data;
        double vX = vData[0];
        double vY = vData[1];
        double vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transformVector_mulAdd(vX, vY, vZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[9] * vZ;
        double _t1 = sd[8] * vY;
        double _t2 = sd[7] * vX;
        double _t12 = 2.0 * java.lang.Math.fma(sd[4], _t0, -(sd[5] * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(sd[3], _t1, -(sd[4] * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(sd[5], _t2, -(sd[3] * _t0));
        dd[0] = java.lang.Math.fma(sd[7], vX, java.lang.Math.fma(sd[6], _t12, java.lang.Math.fma(sd[4], _t13, -(sd[5] * _t14))));
        dd[1] = java.lang.Math.fma(sd[8], vY, java.lang.Math.fma(sd[6], _t14, java.lang.Math.fma(sd[5], _t12, -(sd[3] * _t13))));
        dd[2] = java.lang.Math.fma(sd[9], vZ, java.lang.Math.fma(sd[6], _t13, java.lang.Math.fma(sd[3], _t14, -(sd[4] * _t12))));
        return dest;
    }

    /**
     * Transform the given vector by the linear part of this transform, i.e. apply its scale and
     * rotation but not its translation, and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformVector_mulAdd(vX, vY, vZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[9] * vZ;
        double _t1 = sd[8] * vY;
        double _t2 = sd[7] * vX;
        double _t12 = 2.0 * java.lang.Math.fma(sd[4], _t0, -(sd[5] * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(sd[3], _t1, -(sd[4] * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(sd[5], _t2, -(sd[3] * _t0));
        dd[0] = java.lang.Math.fma(sd[7], vX, java.lang.Math.fma(sd[6], _t12, java.lang.Math.fma(sd[4], _t13, -(sd[5] * _t14))));
        dd[1] = java.lang.Math.fma(sd[8], vY, java.lang.Math.fma(sd[6], _t14, java.lang.Math.fma(sd[5], _t12, -(sd[3] * _t13))));
        dd[2] = java.lang.Math.fma(sd[9], vZ, java.lang.Math.fma(sd[6], _t13, java.lang.Math.fma(sd[3], _t14, -(sd[4] * _t12))));
        return dest;
    }

    /** {@code transformVector} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformVector_mulAdd(double vX, double vY, double vZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t0 = sd[9] * vZ;
        double _t1 = sd[8] * vY;
        double _t2 = sd[7] * vX;
        double _t12 = 2.0 * ((sd[4]) * (_t0) - (sd[5] * _t1));
        double _t13 = 2.0 * ((sd[3]) * (_t1) - (sd[4] * _t2));
        double _t14 = 2.0 * ((sd[5]) * (_t2) - (sd[3] * _t0));
        dd[0] = ((sd[7]) * (vX) + (((sd[6]) * (_t12) + (((sd[4]) * (_t13) - (sd[5] * _t14))))));
        dd[1] = ((sd[8]) * (vY) + (((sd[6]) * (_t14) + (((sd[5]) * (_t12) - (sd[3] * _t13))))));
        dd[2] = ((sd[9]) * (vZ) + (((sd[6]) * (_t13) + (((sd[3]) * (_t14) - (sd[4] * _t12))))));
        return dest;
    }

    /**
     * Transform the given vector by the inverse of this transform's linear part (world to local),
     * i.e. undo its rotation and scale but not its translation, without materializing
     * {@code invert()}, and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformVectorInverse(Double3R v, @Mutated Double3 dest) {
        double[] vData = ((Double3Impl) v).data;
        double vX = vData[0];
        double vY = vData[1];
        double vZ = vData[2];
        if (!org.joml2.internal.FmaSwitch.ON) return transformVectorInverse_mulAdd(vX, vY, vZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[3], vZ, -(sd[5] * vX));
        double _t10 = 2.0 * java.lang.Math.fma(sd[4], vX, -(sd[3] * vY));
        double _t11 = 2.0 * java.lang.Math.fma(sd[5], vY, -(sd[4] * vZ));
        dd[0] = java.lang.Math.fma(sd[5], _t9, java.lang.Math.fma(-sd[4], _t10, java.lang.Math.fma(sd[6], _t11, vX))) / sd[7];
        dd[1] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[5], _t11, java.lang.Math.fma(sd[6], _t9, vY))) / sd[8];
        dd[2] = java.lang.Math.fma(sd[4], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, vZ))) / sd[9];
        return dest;
    }

    /**
     * Transform the given vector by the inverse of this transform's linear part (world to local),
     * i.e. undo its rotation and scale but not its translation, without materializing
     * {@code invert()}, and store the result in {@code dest}.
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
        if (!org.joml2.internal.FmaSwitch.ON) return transformVectorInverse_mulAdd(vX, vY, vZ, dest);
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * java.lang.Math.fma(sd[3], vZ, -(sd[5] * vX));
        double _t10 = 2.0 * java.lang.Math.fma(sd[4], vX, -(sd[3] * vY));
        double _t11 = 2.0 * java.lang.Math.fma(sd[5], vY, -(sd[4] * vZ));
        dd[0] = java.lang.Math.fma(sd[5], _t9, java.lang.Math.fma(-sd[4], _t10, java.lang.Math.fma(sd[6], _t11, vX))) / sd[7];
        dd[1] = java.lang.Math.fma(sd[3], _t10, java.lang.Math.fma(-sd[5], _t11, java.lang.Math.fma(sd[6], _t9, vY))) / sd[8];
        dd[2] = java.lang.Math.fma(sd[4], _t11, java.lang.Math.fma(-sd[3], _t9, java.lang.Math.fma(sd[6], _t10, vZ))) / sd[9];
        return dest;
    }

    /** {@code transformVectorInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformVectorInverse_mulAdd(double vX, double vY, double vZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _t9 = 2.0 * ((sd[3]) * (vZ) - (sd[5] * vX));
        double _t10 = 2.0 * ((sd[4]) * (vX) - (sd[3] * vY));
        double _t11 = 2.0 * ((sd[5]) * (vY) - (sd[4] * vZ));
        dd[0] = ((sd[5]) * (_t9) + (((((sd[6]) * (_t11) + (vX))) - (sd[4]) * (_t10)))) / sd[7];
        dd[1] = ((sd[3]) * (_t10) + (((((sd[6]) * (_t9) + (vY))) - (sd[5]) * (_t11)))) / sd[8];
        dd[2] = ((sd[4]) * (_t11) + (((((sd[6]) * (_t10) + (vZ))) - (sd[3]) * (_t9)))) / sd[9];
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
        return "DoubleTransform(" + this.data[0] + ", " + this.data[1] + ", " + this.data[2] + ", " + this.data[3] + ", " + this.data[4] + ", " + this.data[5] + ", " + this.data[6] + ", " + this.data[7] + ", " + this.data[8] + ", " + this.data[9] + ")";
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
        double[] otherData = ((DoubleTransformImpl) other).data;
        double[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherData[0]) <= epsilon
            && java.lang.Math.abs(sd[1] - otherData[1]) <= epsilon
            && java.lang.Math.abs(sd[2] - otherData[2]) <= epsilon
            && java.lang.Math.abs(sd[3] - otherData[3]) <= epsilon
            && java.lang.Math.abs(sd[4] - otherData[4]) <= epsilon
            && java.lang.Math.abs(sd[5] - otherData[5]) <= epsilon
            && java.lang.Math.abs(sd[6] - otherData[6]) <= epsilon
            && java.lang.Math.abs(sd[7] - otherData[7]) <= epsilon
            && java.lang.Math.abs(sd[8] - otherData[8]) <= epsilon
            && java.lang.Math.abs(sd[9] - otherData[9]) <= epsilon;
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

    @Mutated public DoubleTransform load(double[] src, int offset) {
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

    public ByteBuffer store(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    public ByteBuffer storeRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 80) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 80);
        return buf;
    }

    @Mutated public DoubleTransform load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public DoubleTransform loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public DoubleTransform loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 80) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 80);
        return this;
    }

    public DoubleTransform storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    @Mutated public DoubleTransform loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }

    public MemorySegment store(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }

    @Mutated public DoubleTransform load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }

    @Mutated public DoubleTransform load(long offset, MemorySegment src) {
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

    @Mutated public DoubleTransform load(float[] src, int offset) {
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

    public ByteBuffer storeFloat(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeFloatAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }

    public ByteBuffer storeFloatRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 40) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 40);
        return buf;
    }

    @Mutated public DoubleTransform loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, buf.position(), buf);
    }

    @Mutated public DoubleTransform loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }

    @Mutated public DoubleTransform loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 40) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadFloatAbsolute(this, pos, buf);
        buf.position(pos + 40);
        return this;
    }

    public DoubleTransform storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }

    @Mutated public DoubleTransform loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }

    public MemorySegment storeFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }

    public MemorySegment storeFloat(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }

    @Mutated public DoubleTransform loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(this, 0L, src); }

    @Mutated public DoubleTransform loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(this, offset, src);
    }

    private static final VectorSpecies<Double> COL_SPECIES = DoubleVector.SPECIES_256;
    private static final DoubleVector VEC_1 = DoubleVector.fromArray(COL_SPECIES, new double[] {0.0, 0.0, 0.0, 1.0}, 0);
    private static final DoubleVector VEC_2 = DoubleVector.fromArray(COL_SPECIES, new double[] {0.0, 0.0, 0.0, 0.0}, 0);
    private static final double[] DATA_0 = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0};

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

    private DoubleTransform setScale_narrow(Double3R s, @Mutated DoubleTransform dest) {
        double[] sData = ((Double3Impl) s).data;
        double sX = sData[0];
        double sY = sData[1];
        double sZ = sData[2];
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

    private DoubleTransform setScale_narrow(double sX, double sY, double sZ, @Mutated DoubleTransform dest) {
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

    private DoubleTransform setScale_narrow(double uniform, @Mutated DoubleTransform dest) {
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

    private DoubleTransform setTranslation_narrow(Double3R t, @Mutated DoubleTransform dest) {
        double[] tData = ((Double3Impl) t).data;
        double tY = tData[1];
        double tZ = tData[2];
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = tData[0];
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

    private DoubleTransform setTranslation_narrow(double tX, double tY, double tZ, @Mutated DoubleTransform dest) {
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

    private DoubleDualQuat toDualQuat_narrow(@Mutated DoubleDualQuat dest) {
        if (!org.joml2.internal.FmaSwitch.ON) return toDualQuat_mulAdd_narrow(dest);
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
        dd[4] = 0.5 * java.lang.Math.fma(_t0, _rd4, java.lang.Math.fma(_rd0, _rd6, _rd1 * _rd5));
        dd[5] = 0.5 * java.lang.Math.fma(_rd2, _rd3, java.lang.Math.fma(_rd1, _rd6, -_rd0 * _rd5));
        dd[6] = 0.5 * java.lang.Math.fma(_rd2, _rd6, java.lang.Math.fma(_rd0, _rd4, -_rd1 * _rd3));
        dd[7] = 0.5 * java.lang.Math.fma(_t0, _rd5, java.lang.Math.fma(-_rd1, _rd4, -_rd0 * _rd3));
        return dest;
    }

    private DoubleRigid toRigid_narrow(@Mutated DoubleRigid dest) {
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

    private DoubleTransform set_narrow(Double3R translation) {
        double[] translationData = ((Double3Impl) translation).data;
        double translationY = translationData[1];
        double translationZ = translationData[2];
        double[] dd = this.data;
        dd[0] = translationData[0];
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

    private DoubleTransform set_narrow(double translationX, double translationY, double translationZ) {
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

    private DoubleQuat getRotation_narrow(@Mutated DoubleQuat dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleQuatImpl) dest).data;
        dd[0] = sd[3];
        dd[1] = sd[4];
        dd[2] = sd[5];
        dd[3] = sd[6];
        return dest;
    }

    private DoubleTransform makeRotationY_narrow(double angle) {
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = Math.sinWithCos(_t0);
        dd[5] = 0.0;
        dd[6] = Math.cosWithSin(_t0);
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }

    private DoubleTransform makeRotationZ_narrow(double angle) {
        double[] dd = this.data;
        double _t0 = 0.5 * angle;
        dd[0] = 0.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = Math.sinWithCos(_t0);
        dd[6] = Math.cosWithSin(_t0);
        dd[7] = 1.0;
        dd[8] = 1.0;
        dd[9] = 1.0;
        return this;
    }

    private DoubleTransform makeScaling_narrow(Double3R scale) {
        double[] scaleData = ((Double3Impl) scale).data;
        double scaleX = scaleData[0];
        double scaleY = scaleData[1];
        double scaleZ = scaleData[2];
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

    private DoubleTransform makeScaling_narrow(double scaleX, double scaleY, double scaleZ) {
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

    private DoubleTransform makeScaling_narrow(double scale) {
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

    private DoubleTransform scale_narrow(Double3R scale, @Mutated DoubleTransform dest) {
        double[] scaleData = ((Double3Impl) scale).data;
        double scaleX = scaleData[0];
        double scaleY = scaleData[1];
        double scaleZ = scaleData[2];
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

    private DoubleTransform scale_narrow(double scaleX, double scaleY, double scaleZ, @Mutated DoubleTransform dest) {
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

    private DoubleTransform scale_narrow(double scale, @Mutated DoubleTransform dest) {
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

    private DoubleTransform translate_narrow(Double3R translation, @Mutated DoubleTransform dest) {
        double[] translationData = ((Double3Impl) translation).data;
        double translationX = translationData[0];
        double translationY = translationData[1];
        double translationZ = translationData[2];
        if (org.joml2.internal.FmaSwitch.ON) return translate_fma_narrow(translationX, translationY, translationZ, dest);
        return translate_mulAdd_narrow(translationX, translationY, translationZ, dest);
    }

    private DoubleTransform translate_narrow(double translationX, double translationY, double translationZ, @Mutated DoubleTransform dest) {
        if (org.joml2.internal.FmaSwitch.ON) return translate_fma_narrow(translationX, translationY, translationZ, dest);
        return translate_mulAdd_narrow(translationX, translationY, translationZ, dest);
    }

    private DoubleDualQuat toDualQuat_mulAdd_narrow(@Mutated DoubleDualQuat dest) {
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
        dd[4] = 0.5 * ((_t0) * (_rd4) + (((_rd0) * (_rd6) + (_rd1 * _rd5))));
        dd[5] = 0.5 * ((_rd2) * (_rd3) + (((_rd1) * (_rd6) - (_rd0 * _rd5))));
        dd[6] = 0.5 * ((_rd2) * (_rd6) + (((_rd0) * (_rd4) - (_rd1 * _rd3))));
        dd[7] = 0.5 * ((_t0) * (_rd5) + (((-_rd1) * (_rd4) - (_rd0 * _rd3))));
        return dest;
    }

    private DoubleTransform translate_fma_narrow(double translationX, double translationY, double translationZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = sd[8] * translationY;
        double _t1 = sd[7] * translationX;
        double _t2 = sd[9] * translationZ;
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
        dd[0] = java.lang.Math.fma(_rd1, _t12, java.lang.Math.fma(-_rd2, _t13, java.lang.Math.fma(_rd3, _t14, java.lang.Math.fma(_rd4, translationX, sd[0]))));
        dd[1] = java.lang.Math.fma(_rd2, _t14, java.lang.Math.fma(-_rd0, _t12, java.lang.Math.fma(_rd3, _t13, java.lang.Math.fma(_rd5, translationY, sd[1]))));
        dd[2] = java.lang.Math.fma(_rd0, _t13, java.lang.Math.fma(-_rd1, _t14, java.lang.Math.fma(_rd3, _t12, java.lang.Math.fma(_rd6, translationZ, sd[2]))));
        dd[3] = _rd0;
        dd[4] = _rd1;
        dd[5] = _rd2;
        dd[6] = _rd3;
        dd[7] = _rd4;
        dd[8] = _rd5;
        dd[9] = _rd6;
        return dest;
    }

    private DoubleTransform translate_mulAdd_narrow(double translationX, double translationY, double translationZ, @Mutated DoubleTransform dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        double _t0 = sd[8] * translationY;
        double _t1 = sd[7] * translationX;
        double _t2 = sd[9] * translationZ;
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
        dd[0] = ((_rd1) * (_t12) + (((((_rd3) * (_t14) + (((_rd4) * (translationX) + (sd[0]))))) - (_rd2) * (_t13))));
        dd[1] = ((_rd2) * (_t14) + (((((_rd3) * (_t13) + (((_rd5) * (translationY) + (sd[1]))))) - (_rd0) * (_t12))));
        dd[2] = ((_rd0) * (_t13) + (((((_rd3) * (_t12) + (((_rd6) * (translationZ) + (sd[2]))))) - (_rd1) * (_t14))));
        dd[3] = _rd0;
        dd[4] = _rd1;
        dd[5] = _rd2;
        dd[6] = _rd3;
        dd[7] = _rd4;
        dd[8] = _rd5;
        dd[9] = _rd6;
        return dest;
    }
}
