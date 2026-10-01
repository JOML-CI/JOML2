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
 * Generated implementation of {@link FloatTransform} backed by a {@code float[]} array, with Vector
 * API SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatTransformImpl implements FloatTransform {

    public float[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatTransformSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatTransformSegOpsUnsafe()
                        : new FloatTransformSegOpsMS();
        static final FloatTransformBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatTransformBbOpsUnsafe()
                        : new FloatTransformBbOpsApi();
        static final FloatTransformRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatTransformRawOpsUnsafe()
                        : new FloatTransformRawOpsApi();
    }

    public FloatTransformImpl() {
        data = new float[10];
        data[6] = 1;
        data[7] = 1;
        data[8] = 1;
        data[9] = 1;
    }

    public FloatTransformImpl(float tX, float tY, float tZ, float rX, float rY, float rZ, float rW, float sX, float sY, float sZ) {
        float[] dd = this.data = new float[10];
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

    public FloatTransformImpl(FloatTransformR src) {
        float[] dd = this.data = new float[10];
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
    public @Mutated FloatTransform makeFromAxisAngle(Float3R axis, float angle, Float3R translation) {
        return makeFromAxisAngle(axis.x(), axis.y(), axis.z(), angle, translation.x(), translation.y(), translation.z());
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
    @Mutated public FloatTransform makeFromAxisAngle(float axisX, float axisY, float axisZ, float angle, float translationX, float translationY, float translationZ) {
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
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
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
    public @Mutated FloatTransform makeTranslationRotation(Float3R translation, FloatQuatR rotation) {
        return makeTranslationRotation(translation.x(), translation.y(), translation.z(), rotation.x(), rotation.y(), rotation.z(), rotation.w());
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
    @Mutated public FloatTransform makeTranslationRotation(float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW) {
        float[] dd = this.data;
        dd[0] = translationX;
        dd[1] = translationY;
        dd[2] = translationZ;
        dd[3] = rotationX;
        dd[4] = rotationY;
        dd[5] = rotationZ;
        dd[6] = rotationW;
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
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
    public @Mutated FloatTransform makeTranslationRotationScale(Float3R translation, FloatQuatR rotation, Float3R scale) {
        return makeTranslationRotationScale(translation.x(), translation.y(), translation.z(), rotation.x(), rotation.y(), rotation.z(), rotation.w(), scale.x(), scale.y(), scale.z());
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
    @Mutated public FloatTransform makeTranslationRotationScale(float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        float[] dd = this.data;
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
    public @Mutated FloatTransform set(FloatTransformR v) {
        return set(v.tX(), v.tY(), v.tZ(), v.rX(), v.rY(), v.rZ(), v.rW(), v.sX(), v.sY(), v.sZ());
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
    @Mutated public FloatTransform set(float vTX, float vTY, float vTZ, float vRX, float vRY, float vRZ, float vRW, float vSX, float vSY, float vSZ) {
        float[] dd = this.data;
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
    public FloatTransform setRotation(FloatQuatR r, @Mutated FloatTransform dest) {
        return setRotation(r.x(), r.y(), r.z(), r.w(), dest);
    }


    /**
     * Set the rotation of this transform to {@code r} and store the result in {@code dest}.
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
    public DoubleTransform setRotation(FloatQuatR r, @Mutated DoubleTransform dest) {
        return setRotation(r.x(), r.y(), r.z(), r.w(), dest);
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
    public FloatTransform setRotation(float rX, float rY, float rZ, float rW, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
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
    public DoubleTransform setRotation(float rX, float rY, float rZ, float rW, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
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
    public FloatTransform setScale(Float3R s, @Mutated FloatTransform dest) {
        return setScale(s.x(), s.y(), s.z(), dest);
    }


    /**
     * Set the scale of this transform to {@code s} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform setScale(Float3R s, @Mutated DoubleTransform dest) {
        return setScale(s.x(), s.y(), s.z(), dest);
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
    public FloatTransform setScale(float sX, float sY, float sZ, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sX the {@code x} component of the vector {@code (sX, sY, sZ)}
     * @param sY the {@code y} component of the vector {@code (sX, sY, sZ)}
     * @param sZ the {@code z} component of the vector {@code (sX, sY, sZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform setScale(float sX, float sY, float sZ, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
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
    public FloatTransform setScale(float uniform, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = uniform;
        dd[8] = uniform;
        dd[9] = uniform;
        return dest;
    }


    /**
     * Set the scale of this transform to {@code uniform} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param uniform the uniform scale factor
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform setScale(float uniform, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
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
    public FloatTransform setTranslation(Float3R t, @Mutated FloatTransform dest) {
        return setTranslation(t.x(), t.y(), t.z(), dest);
    }


    /**
     * Set the translation of this transform to {@code t} and store the result in {@code dest}.
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
    public DoubleTransform setTranslation(Float3R t, @Mutated DoubleTransform dest) {
        return setTranslation(t.x(), t.y(), t.z(), dest);
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
    public FloatTransform setTranslation(float tX, float tY, float tZ, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        dd[0] = tX;
        dd[1] = tY;
        dd[2] = tZ;
        FloatVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Set the translation of this transform to ({@code tX}, {@code tY}, {@code tZ}) and store the
     * result in {@code dest}.
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
    public DoubleTransform setTranslation(float tX, float tY, float tZ, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
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
    public @Mutated FloatTransform makeFromDualQuat(FloatDualQuatR dq) {
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
    @Mutated public FloatTransform makeFromDualQuat(float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        float[] dd = this.data;
        dd[0] = 2.0f * (Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + Math.fma(dqRW, dqDX, -(dqRX * dqDW)));
        dd[1] = 2.0f * (Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + Math.fma(dqRW, dqDY, -(dqRY * dqDW)));
        dd[2] = 2.0f * (Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + Math.fma(dqRW, dqDZ, -(dqRZ * dqDW)));
        dd[3] = dqRX;
        dd[4] = dqRY;
        dd[5] = dqRZ;
        dd[6] = dqRW;
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
        return this;
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
    @Mutated public FloatTransform makeFromMatrix(Float3x3R m) {
        float[] dd = this.data;
        float[] mData = ((Float3x3Impl) m).data;
        return makeFromMatrix_s4398bb65_1(m, dd, mData, -mData[4], -mData[8], Math.fma(mData[5], mData[5], Math.fma(mData[3], mData[3], mData[4] * mData[4])));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatTransform makeFromMatrix_s4398bb65_1(Float3x3R m, float[] dd, float[] mData, float _t0, float _t1, float _t12) {
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t13 = Math.fma(mData[8], mData[8], Math.fma(mData[6], mData[6], mData[7] * mData[7]));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t14 = Math.fma(mData[2], mData[2], Math.fma(mData[0], mData[0], mData[1] * mData[1]));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t15 = (1.0f / (float) Math.sqrt(_t12));
        float _t16 = (1.0f / (float) Math.sqrt(_t13));
        float _t18 = (float) Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        float _t20 = mData[8] * _t16;
        float _t21 = mData[7] * _t16;
        float _t24 = mData[5] * _t15;
        float _t25 = mData[4] * _t15;
        return makeFromMatrix_s4398bb65_2(dd, mData, _t0, _t1, _t12, _t13, _t15, _t16, _t18, mData[1] * _t17, _t20, _t21, mData[2] * _t17, _t24, _t25, mData[0] * _t17, Math.fma(mData[7], _t16, _t24), Math.fma(mData[5], _t15, -_t21), Math.max(_t25, _t20));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatTransform makeFromMatrix_s4398bb65_2(float[] dd, float[] mData, float _t0, float _t1, float _t12, float _t13, float _t15, float _t16, float _t18, float _t19, float _t20, float _t21, float _t22, float _t24, float _t25, float _t27, float _t32, float _t36, float _t37) {
        float _t47 = Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), mData[3] * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), mData[6] * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
        float _t48, _t49, _t50;
        if (_t47 < 0.0f) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        float _t64 = Math.fma(mData[4], _t15, Math.fma(mData[8], _t16, _t52));
        float _t66 = Math.fma(mData[4], _t15, Math.fma(_t1, _t16, _t53));
        return makeFromMatrix_s4398bb65_3(dd, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, Math.fma(mData[3], _t15, _t49), Math.fma(mData[6], _t16, _t50), Math.fma(mData[6], _t16, -_t50), Math.fma(-mData[3], _t15, _t49), Math.fma(mData[4], _t15, Math.fma(mData[8], _t16, _t48)), _t64, 0.5f * (1.0f / (float) Math.sqrt(_t64)), _t66, Math.fma(mData[8], _t16, Math.fma(_t0, _t15, _t53)), Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52)), 0.5f * (1.0f / (float) Math.sqrt(_t66)));
    }

    /** Piece 4 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatTransform makeFromMatrix_s4398bb65_3(float[] dd, float _t12, float _t13, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t55, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0, float _t66, float _t67, float _t68, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t68));
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56;
        dd[4] = _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) Math.sqrt(_t66) : _sp2 * _t32;
        dd[5] = _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) Math.sqrt(_t67);
        return makeFromMatrix_s4398bb65_4(dd, _t12, _t13, _t18, _t20, _t25, _t36, _t37, _t47, _t48, _t57, _t58, _t63, _t64, _sp1, _sp2, _sp3);
    }

    /**
     * Piece 5 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 3 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private FloatTransform makeFromMatrix_s4398bb65_4(float[] dd, float _t12, float _t13, float _t18, float _t20, float _t25, float _t36, float _t37, float _t47, float _t48, float _t57, float _t58, float _t63, float _t64, float _sp1, float _sp2, float _sp3) {
        dd[6] = _t63 > 0.0f ? 0.5f * (float) Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58;
        dd[7] = _t47 < 0.0f ? -_t18 : _t18;
        dd[8] = (float) Math.sqrt(_t12);
        dd[9] = (float) Math.sqrt(_t13);
        return this;
    }


    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatTransform makeFromMatrix_degenerate(Float3x3R m) {
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
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (Math.abs(_t35) < Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (Math.abs(_t39) < Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (Math.abs(_t42) < Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
        float _t105 = _t102 * _t75;
        float _t106 = _t103 * _t76;
        float _t107 = _t104 * _t77;
        float _t108 = _t103 * _t79;
        float _t109 = _t102 * _t78;
        float _t110 = _t104 * _t80;
        float _t117 = _t103 * _t91;
        float _t118 = _t104 * _t92;
        float _t119 = _t102 * _t90;
        float _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t170 = 1.0f;
                    _t173 = 0.0f;
                    _t169 = 0.0f;
                    _t171 = 0.0f;
                    _t174 = 1.0f;
                    _t172 = 0.0f;
                    _t175 = 0.0f;
                    _t176 = 1.0f;
                } else {
                    _t168 = _t105;
                    _t170 = _t119;
                    _t173 = _t109;
                    _t169 = Math.fma(_t35, _t105, -(_t36 * _t109));
                    _t171 = Math.fma(_t37, _t109, -(_t35 * _t119));
                    _t174 = Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t168 = Math.fma(_t38, _t108, -(_t39 * _t117));
                    _t170 = Math.fma(_t39, _t106, -(_t40 * _t108));
                    _t173 = Math.fma(_t40, _t117, -(_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = Math.fma(_t35, _t38, -(_t37 * _t39));
                    _t170 = Math.fma(_t36, _t39, -(_t35 * _t40));
                    _t173 = Math.fma(_t37, _t40, -(_t36 * _t38));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        } else {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t118;
                    _t171 = _t107;
                    _t174 = _t110;
                    _t172 = Math.fma(_t41, _t118, -(_t43 * _t107));
                    _t175 = Math.fma(_t42, _t107, -(_t41 * _t110));
                    _t176 = Math.fma(_t43, _t110, -(_t42 * _t118));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = Math.fma(_t35, _t41, -(_t36 * _t42));
                    _t171 = Math.fma(_t37, _t42, -(_t35 * _t43));
                    _t174 = Math.fma(_t36, _t43, -(_t37 * _t41));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = Math.fma(_t41, _t38, -(_t43 * _t40));
                    _t175 = Math.fma(_t42, _t40, -(_t41 * _t39));
                    _t176 = Math.fma(_t43, _t39, -(_t42 * _t38));
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
        float _t185 = _t173 - _t169;
        float _t186 = Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
        float _t196 = Math.fma(Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, Math.fma(Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
        float _t197, _t198, _t199;
        if (_t196 < 0.0f) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        float _t202 = _t198 + _t168;
        float _t203 = _t199 + _t171;
        float _t204 = _t171 - _t199;
        float _t205 = _t198 - _t168;
        float _t209 = _t197 + _t170 + _t174;
        float _t210 = 1.0f + _t209;
        float _t211 = 1.0f + _t197 - _t170 - _t174;
        float _t212 = 1.0f + _t170 - _t197 - _t174;
        float _t213 = 1.0f + _t174 - _t197 - _t170;
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t211));
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dd[4] = _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) Math.sqrt(_t212) : _sp2 * _t187;
        dd[5] = _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) Math.sqrt(_t213);
        dd[6] = _t209 > 0.0f ? 0.5f * (float) Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dd[7] = _t196 < 0.0f ? -_t56 : _t56;
        dd[8] = _t27 <= 0.0f ? 0.0f : (float) Math.sqrt(_t27) / _t0;
        dd[9] = _t28 <= 0.0f ? 0.0f : (float) Math.sqrt(_t28) / _t1;
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
    @Mutated public FloatTransform makeFromMatrix(Float3x4R m) {
        float[] dd = this.data;
        float[] mData = ((Float3x4Impl) m).data;
        return makeFromMatrix_s7b6de20e_1(m, dd, mData, -mData[5], -mData[10], Math.fma(mData[9], mData[9], Math.fma(mData[1], mData[1], mData[5] * mData[5])));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatTransform makeFromMatrix_s7b6de20e_1(Float3x4R m, float[] dd, float[] mData, float _t0, float _t1, float _t12) {
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t13 = Math.fma(mData[10], mData[10], Math.fma(mData[2], mData[2], mData[6] * mData[6]));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t14 = Math.fma(mData[8], mData[8], Math.fma(mData[0], mData[0], mData[4] * mData[4]));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t15 = (1.0f / (float) Math.sqrt(_t12));
        float _t16 = (1.0f / (float) Math.sqrt(_t13));
        float _t18 = (float) Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        float _t20 = mData[10] * _t16;
        float _t21 = mData[6] * _t16;
        float _t24 = mData[9] * _t15;
        float _t25 = mData[5] * _t15;
        return makeFromMatrix_s7b6de20e_2(dd, mData, _t0, _t1, _t12, _t13, _t15, _t16, _t18, mData[4] * _t17, _t20, _t21, mData[8] * _t17, _t24, _t25, mData[0] * _t17, Math.fma(mData[6], _t16, _t24), Math.fma(mData[9], _t15, -_t21), Math.max(_t25, _t20));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatTransform makeFromMatrix_s7b6de20e_2(float[] dd, float[] mData, float _t0, float _t1, float _t12, float _t13, float _t15, float _t16, float _t18, float _t19, float _t20, float _t21, float _t22, float _t24, float _t25, float _t27, float _t32, float _t36, float _t37) {
        float _t47 = Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), mData[1] * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), mData[2] * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
        float _t48, _t49, _t50;
        if (_t47 < 0.0f) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        float _t64 = Math.fma(mData[5], _t15, Math.fma(mData[10], _t16, _t52));
        float _t66 = Math.fma(mData[5], _t15, Math.fma(_t1, _t16, _t53));
        return makeFromMatrix_s7b6de20e_3(dd, mData, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, Math.fma(mData[1], _t15, _t49), Math.fma(mData[2], _t16, _t50), Math.fma(mData[2], _t16, -_t50), Math.fma(-mData[1], _t15, _t49), Math.fma(mData[5], _t15, Math.fma(mData[10], _t16, _t48)), _t64, 0.5f * (1.0f / (float) Math.sqrt(_t64)), _t66, Math.fma(mData[10], _t16, Math.fma(_t0, _t15, _t53)), Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52)), 0.5f * (1.0f / (float) Math.sqrt(_t66)));
    }

    /** Piece 4 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatTransform makeFromMatrix_s7b6de20e_3(float[] dd, float[] mData, float _t12, float _t13, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t55, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0, float _t66, float _t67, float _t68, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t68));
        dd[0] = mData[3];
        dd[1] = mData[7];
        dd[2] = mData[11];
        dd[3] = _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56;
        dd[4] = _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) Math.sqrt(_t66) : _sp2 * _t32;
        dd[5] = _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) Math.sqrt(_t67);
        return makeFromMatrix_s4398bb65_4(dd, _t12, _t13, _t18, _t20, _t25, _t36, _t37, _t47, _t48, _t57, _t58, _t63, _t64, _sp1, _sp2, _sp3);
    }


    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatTransform makeFromMatrix_degenerate(Float3x4R m) {
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
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (Math.abs(_t35) < Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (Math.abs(_t39) < Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (Math.abs(_t42) < Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
        float _t105 = _t102 * _t75;
        float _t106 = _t103 * _t76;
        float _t107 = _t104 * _t77;
        float _t108 = _t103 * _t79;
        float _t109 = _t102 * _t78;
        float _t110 = _t104 * _t80;
        float _t117 = _t103 * _t91;
        float _t118 = _t104 * _t92;
        float _t119 = _t102 * _t90;
        float _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t170 = 1.0f;
                    _t173 = 0.0f;
                    _t169 = 0.0f;
                    _t171 = 0.0f;
                    _t174 = 1.0f;
                    _t172 = 0.0f;
                    _t175 = 0.0f;
                    _t176 = 1.0f;
                } else {
                    _t168 = _t105;
                    _t170 = _t119;
                    _t173 = _t109;
                    _t169 = Math.fma(_t35, _t105, -(_t36 * _t109));
                    _t171 = Math.fma(_t37, _t109, -(_t35 * _t119));
                    _t174 = Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t168 = Math.fma(_t38, _t108, -(_t39 * _t117));
                    _t170 = Math.fma(_t39, _t106, -(_t40 * _t108));
                    _t173 = Math.fma(_t40, _t117, -(_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = Math.fma(_t35, _t38, -(_t37 * _t39));
                    _t170 = Math.fma(_t36, _t39, -(_t35 * _t40));
                    _t173 = Math.fma(_t37, _t40, -(_t36 * _t38));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        } else {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t118;
                    _t171 = _t107;
                    _t174 = _t110;
                    _t172 = Math.fma(_t41, _t118, -(_t43 * _t107));
                    _t175 = Math.fma(_t42, _t107, -(_t41 * _t110));
                    _t176 = Math.fma(_t43, _t110, -(_t42 * _t118));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = Math.fma(_t35, _t41, -(_t36 * _t42));
                    _t171 = Math.fma(_t37, _t42, -(_t35 * _t43));
                    _t174 = Math.fma(_t36, _t43, -(_t37 * _t41));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = Math.fma(_t41, _t38, -(_t43 * _t40));
                    _t175 = Math.fma(_t42, _t40, -(_t41 * _t39));
                    _t176 = Math.fma(_t43, _t39, -(_t42 * _t38));
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
        float _t185 = _t173 - _t169;
        float _t186 = Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
        float _t196 = Math.fma(Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, Math.fma(Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
        float _t197, _t198, _t199;
        if (_t196 < 0.0f) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        float _t202 = _t198 + _t168;
        float _t203 = _t199 + _t171;
        float _t204 = _t171 - _t199;
        float _t205 = _t198 - _t168;
        float _t209 = _t197 + _t170 + _t174;
        float _t210 = 1.0f + _t209;
        float _t211 = 1.0f + _t197 - _t170 - _t174;
        float _t212 = 1.0f + _t170 - _t197 - _t174;
        float _t213 = 1.0f + _t174 - _t197 - _t170;
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t211));
        dd[0] = mData[3];
        dd[1] = mData[7];
        dd[2] = mData[11];
        dd[3] = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dd[4] = _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) Math.sqrt(_t212) : _sp2 * _t187;
        dd[5] = _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) Math.sqrt(_t213);
        dd[6] = _t209 > 0.0f ? 0.5f * (float) Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dd[7] = _t196 < 0.0f ? -_t56 : _t56;
        dd[8] = _t27 <= 0.0f ? 0.0f : (float) Math.sqrt(_t27) / _t0;
        dd[9] = _t28 <= 0.0f ? 0.0f : (float) Math.sqrt(_t28) / _t1;
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
    @Mutated public FloatTransform makeFromMatrix(Float4x4R m) {
        float[] dd = this.data;
        float[] mData = ((Float4x4Impl) m).data;
        return makeFromMatrix_s40927f3d_1(m, dd, mData, -mData[5], -mData[10], Math.fma(mData[6], mData[6], Math.fma(mData[4], mData[4], mData[5] * mData[5])));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatTransform makeFromMatrix_s40927f3d_1(Float4x4R m, float[] dd, float[] mData, float _t0, float _t1, float _t12) {
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t13 = Math.fma(mData[10], mData[10], Math.fma(mData[8], mData[8], mData[9] * mData[9]));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t14 = Math.fma(mData[2], mData[2], Math.fma(mData[0], mData[0], mData[1] * mData[1]));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        float _t15 = (1.0f / (float) Math.sqrt(_t12));
        float _t16 = (1.0f / (float) Math.sqrt(_t13));
        float _t18 = (float) Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        float _t20 = mData[10] * _t16;
        float _t21 = mData[9] * _t16;
        float _t24 = mData[6] * _t15;
        float _t25 = mData[5] * _t15;
        return makeFromMatrix_s40927f3d_2(dd, mData, _t0, _t1, _t12, _t13, _t15, _t16, _t18, mData[1] * _t17, _t20, _t21, mData[2] * _t17, _t24, _t25, mData[0] * _t17, Math.fma(mData[9], _t16, _t24), Math.fma(mData[6], _t15, -_t21), Math.max(_t25, _t20));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatTransform makeFromMatrix_s40927f3d_2(float[] dd, float[] mData, float _t0, float _t1, float _t12, float _t13, float _t15, float _t16, float _t18, float _t19, float _t20, float _t21, float _t22, float _t24, float _t25, float _t27, float _t32, float _t36, float _t37) {
        float _t47 = Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), mData[4] * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), mData[8] * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
        float _t48, _t49, _t50;
        if (_t47 < 0.0f) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        float _t64 = Math.fma(mData[5], _t15, Math.fma(mData[10], _t16, _t52));
        float _t66 = Math.fma(mData[5], _t15, Math.fma(_t1, _t16, _t53));
        return makeFromMatrix_s40927f3d_3(dd, mData, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, Math.fma(mData[4], _t15, _t49), Math.fma(mData[8], _t16, _t50), Math.fma(mData[8], _t16, -_t50), Math.fma(-mData[4], _t15, _t49), Math.fma(mData[5], _t15, Math.fma(mData[10], _t16, _t48)), _t64, 0.5f * (1.0f / (float) Math.sqrt(_t64)), _t66, Math.fma(mData[10], _t16, Math.fma(_t0, _t15, _t53)), Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52)), 0.5f * (1.0f / (float) Math.sqrt(_t66)));
    }

    /** Piece 4 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatTransform makeFromMatrix_s40927f3d_3(float[] dd, float[] mData, float _t12, float _t13, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t55, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0, float _t66, float _t67, float _t68, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t68));
        dd[0] = mData[12];
        dd[1] = mData[13];
        dd[2] = mData[14];
        dd[3] = _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56;
        dd[4] = _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) Math.sqrt(_t66) : _sp2 * _t32;
        dd[5] = _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) Math.sqrt(_t67);
        return makeFromMatrix_s4398bb65_4(dd, _t12, _t13, _t18, _t20, _t25, _t36, _t37, _t47, _t48, _t57, _t58, _t63, _t64, _sp1, _sp2, _sp3);
    }


    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatTransform makeFromMatrix_degenerate(Float4x4R m) {
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
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (Math.abs(_t35) < Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (Math.abs(_t39) < Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (Math.abs(_t42) < Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
        float _t105 = _t102 * _t75;
        float _t106 = _t103 * _t76;
        float _t107 = _t104 * _t77;
        float _t108 = _t103 * _t79;
        float _t109 = _t102 * _t78;
        float _t110 = _t104 * _t80;
        float _t117 = _t103 * _t91;
        float _t118 = _t104 * _t92;
        float _t119 = _t102 * _t90;
        float _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t170 = 1.0f;
                    _t173 = 0.0f;
                    _t169 = 0.0f;
                    _t171 = 0.0f;
                    _t174 = 1.0f;
                    _t172 = 0.0f;
                    _t175 = 0.0f;
                    _t176 = 1.0f;
                } else {
                    _t168 = _t105;
                    _t170 = _t119;
                    _t173 = _t109;
                    _t169 = Math.fma(_t35, _t105, -(_t36 * _t109));
                    _t171 = Math.fma(_t37, _t109, -(_t35 * _t119));
                    _t174 = Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t168 = Math.fma(_t38, _t108, -(_t39 * _t117));
                    _t170 = Math.fma(_t39, _t106, -(_t40 * _t108));
                    _t173 = Math.fma(_t40, _t117, -(_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = Math.fma(_t35, _t38, -(_t37 * _t39));
                    _t170 = Math.fma(_t36, _t39, -(_t35 * _t40));
                    _t173 = Math.fma(_t37, _t40, -(_t36 * _t38));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        } else {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t118;
                    _t171 = _t107;
                    _t174 = _t110;
                    _t172 = Math.fma(_t41, _t118, -(_t43 * _t107));
                    _t175 = Math.fma(_t42, _t107, -(_t41 * _t110));
                    _t176 = Math.fma(_t43, _t110, -(_t42 * _t118));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = Math.fma(_t35, _t41, -(_t36 * _t42));
                    _t171 = Math.fma(_t37, _t42, -(_t35 * _t43));
                    _t174 = Math.fma(_t36, _t43, -(_t37 * _t41));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = Math.fma(_t41, _t38, -(_t43 * _t40));
                    _t175 = Math.fma(_t42, _t40, -(_t41 * _t39));
                    _t176 = Math.fma(_t43, _t39, -(_t42 * _t38));
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
        float _t185 = _t173 - _t169;
        float _t186 = Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
        float _t196 = Math.fma(Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, Math.fma(Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
        float _t197, _t198, _t199;
        if (_t196 < 0.0f) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        float _t202 = _t198 + _t168;
        float _t203 = _t199 + _t171;
        float _t204 = _t171 - _t199;
        float _t205 = _t198 - _t168;
        float _t209 = _t197 + _t170 + _t174;
        float _t210 = 1.0f + _t209;
        float _t211 = 1.0f + _t197 - _t170 - _t174;
        float _t212 = 1.0f + _t170 - _t197 - _t174;
        float _t213 = 1.0f + _t174 - _t197 - _t170;
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t211));
        dd[0] = mData[12];
        dd[1] = mData[13];
        dd[2] = mData[14];
        dd[3] = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dd[4] = _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) Math.sqrt(_t212) : _sp2 * _t187;
        dd[5] = _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) Math.sqrt(_t213);
        dd[6] = _t209 > 0.0f ? 0.5f * (float) Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dd[7] = _t196 < 0.0f ? -_t56 : _t56;
        dd[8] = _t27 <= 0.0f ? 0.0f : (float) Math.sqrt(_t27) / _t0;
        dd[9] = _t28 <= 0.0f ? 0.0f : (float) Math.sqrt(_t28) / _t1;
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
    public @Mutated FloatTransform makeFromRigid(FloatRigidR r) {
        return makeFromRigid(r.tX(), r.tY(), r.tZ(), r.rX(), r.rY(), r.rZ(), r.rW());
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
    @Mutated public FloatTransform makeFromRigid(float rTX, float rTY, float rTZ, float rRX, float rRY, float rRZ, float rRW) {
        float[] dd = this.data;
        dd[0] = rTX;
        dd[1] = rTY;
        dd[2] = rTZ;
        dd[3] = rRX;
        dd[4] = rRY;
        dd[5] = rRZ;
        dd[6] = rRW;
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
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
     * Convert this transform's rigid motion (rotation and translation) to a unit dual quaternion;
     * the scale is dropped (dual quaternions cannot represent it) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
     * Compute the matrix representation of this transform and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4x4 toMatrix(@Mutated Float4x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x4Impl) dest).data;
        float _t0 = sd[7] + sd[7];
        float _t1 = sd[8] + sd[8];
        float _t2 = sd[9] + sd[9];
        float _t3 = sd[5] * sd[5];
        float _t4 = sd[5] * sd[6];
        float _t5 = sd[4] * sd[6];
        float _buf0 = Math.fma(-Math.fma(sd[4], sd[4], _t3), _t0, sd[7]);
        float _buf1 = Math.fma(sd[3], sd[4], _t4) * _t0;
        float _buf2 = Math.fma(sd[3], sd[5], -_t5) * _t0;
        dd[3] = 0.0f;
        float _buf3 = Math.fma(sd[3], sd[4], -_t4) * _t1;
        float _buf4 = Math.fma(-Math.fma(sd[3], sd[3], _t3), _t1, sd[8]);
        float _buf5 = Math.fma(sd[3], sd[6], sd[4] * sd[5]) * _t1;
        dd[7] = 0.0f;
        float _buf6 = Math.fma(sd[3], sd[5], _t5) * _t2;
        dd[9] = Math.fma(sd[4], sd[5], -(sd[3] * sd[6])) * _t2;
        return toMatrix_s6357fbbf_1(dest, sd, dd, _t2, _buf0, _buf1, _buf2, _buf3, _buf4, _buf5, _buf6);
    }

    /** Piece 2 of {@code toMatrix}, split to fit the inline budget; reached only through it. */
    private Float4x4 toMatrix_s6357fbbf_1(Float4x4 dest, float[] sd, float[] dd, float _t2, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4, float _buf5, float _buf6) {
        dd[10] = Math.fma(-Math.fma(sd[3], sd[3], sd[4] * sd[4]), _t2, sd[9]);
        dd[11] = 0.0f;
        dd[12] = sd[0];
        dd[13] = sd[1];
        dd[14] = sd[2];
        dd[15] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        dd[8] = _buf6;
        ((Float4x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Compute the matrix representation of this transform and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4x4 toMatrix(@Mutated Double4x4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4x4Impl) dest).data;
        float _t0 = sd[7] + sd[7];
        float _t1 = sd[8] + sd[8];
        float _t2 = sd[9] + sd[9];
        float _t3 = sd[5] * sd[5];
        float _t4 = sd[5] * sd[6];
        float _t5 = sd[4] * sd[6];
        float _buf0 = Math.fma(-Math.fma(sd[4], sd[4], _t3), _t0, sd[7]);
        float _buf1 = Math.fma(sd[3], sd[4], _t4) * _t0;
        float _buf2 = Math.fma(sd[3], sd[5], -_t5) * _t0;
        dd[3] = 0.0f;
        float _buf3 = Math.fma(sd[3], sd[4], -_t4) * _t1;
        float _buf4 = Math.fma(-Math.fma(sd[3], sd[3], _t3), _t1, sd[8]);
        float _buf5 = Math.fma(sd[3], sd[6], sd[4] * sd[5]) * _t1;
        dd[7] = 0.0f;
        float _buf6 = Math.fma(sd[3], sd[5], _t5) * _t2;
        dd[9] = Math.fma(sd[4], sd[5], -(sd[3] * sd[6])) * _t2;
        return toMatrix_s9c292502_1(dest, sd, dd, _t2, _buf0, _buf1, _buf2, _buf3, _buf4, _buf5, _buf6);
    }

    /** Piece 2 of {@code toMatrix}, split to fit the inline budget; reached only through it. */
    private Double4x4 toMatrix_s9c292502_1(Double4x4 dest, float[] sd, double[] dd, float _t2, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4, float _buf5, float _buf6) {
        dd[10] = Math.fma(-Math.fma(sd[3], sd[3], sd[4] * sd[4]), _t2, sd[9]);
        dd[11] = 0.0f;
        dd[12] = sd[0];
        dd[13] = sd[1];
        dd[14] = sd[2];
        dd[15] = 1.0f;
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
        dd[8] = _buf6;
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
    public Float3x3 toMatrix3x3(@Mutated Float3x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x3Impl) dest).data;
        float _t0 = sd[7] + sd[7];
        float _t1 = sd[8] + sd[8];
        float _t2 = sd[9] + sd[9];
        float _t3 = sd[5] * sd[5];
        float _t4 = sd[5] * sd[6];
        float _t5 = sd[4] * sd[6];
        dd[0] = Math.fma(-Math.fma(sd[4], sd[4], _t3), _t0, sd[7]);
        float _buf0 = Math.fma(sd[3], sd[4], _t4) * _t0;
        float _buf1 = Math.fma(sd[3], sd[5], -_t5) * _t0;
        dd[3] = Math.fma(sd[3], sd[4], -_t4) * _t1;
        float _buf2 = Math.fma(-Math.fma(sd[3], sd[3], _t3), _t1, sd[8]);
        dd[5] = Math.fma(sd[3], sd[6], sd[4] * sd[5]) * _t1;
        dd[6] = Math.fma(sd[3], sd[5], _t5) * _t2;
        dd[7] = Math.fma(sd[4], sd[5], -(sd[3] * sd[6])) * _t2;
        return toMatrix3x3_s431d3829_1(dest, sd, dd, _t2, _buf0, _buf1, _buf2);
    }

    /** Piece 2 of {@code toMatrix3x3}, split to fit the inline budget; reached only through it. */
    private Float3x3 toMatrix3x3_s431d3829_1(Float3x3 dest, float[] sd, float[] dd, float _t2, float _buf0, float _buf1, float _buf2) {
        dd[8] = Math.fma(-Math.fma(sd[3], sd[3], sd[4] * sd[4]), _t2, sd[9]);
        dd[1] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
        ((Float3x3Impl) dest).properties = 0;
        return dest;
    }


    /**
     * Compute the 3x3 linear block ({@code R * S}) of this transform (the translation is dropped)
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x3 toMatrix3x3(@Mutated Double3x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x3Impl) dest).data;
        float _t0 = sd[7] + sd[7];
        float _t1 = sd[8] + sd[8];
        float _t2 = sd[9] + sd[9];
        float _t3 = sd[5] * sd[5];
        float _t4 = sd[5] * sd[6];
        float _t5 = sd[4] * sd[6];
        dd[0] = Math.fma(-Math.fma(sd[4], sd[4], _t3), _t0, sd[7]);
        float _buf0 = Math.fma(sd[3], sd[4], _t4) * _t0;
        float _buf1 = Math.fma(sd[3], sd[5], -_t5) * _t0;
        dd[3] = Math.fma(sd[3], sd[4], -_t4) * _t1;
        float _buf2 = Math.fma(-Math.fma(sd[3], sd[3], _t3), _t1, sd[8]);
        dd[5] = Math.fma(sd[3], sd[6], sd[4] * sd[5]) * _t1;
        dd[6] = Math.fma(sd[3], sd[5], _t5) * _t2;
        dd[7] = Math.fma(sd[4], sd[5], -(sd[3] * sd[6])) * _t2;
        return toMatrix3x3_sdb008b20_1(dest, sd, dd, _t2, _buf0, _buf1, _buf2);
    }

    /** Piece 2 of {@code toMatrix3x3}, split to fit the inline budget; reached only through it. */
    private Double3x3 toMatrix3x3_sdb008b20_1(Double3x3 dest, float[] sd, double[] dd, float _t2, float _buf0, float _buf1, float _buf2) {
        dd[8] = Math.fma(-Math.fma(sd[3], sd[3], sd[4] * sd[4]), _t2, sd[9]);
        dd[1] = _buf0;
        dd[2] = _buf1;
        dd[4] = _buf2;
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
    public Float3x4 toMatrix3x4(@Mutated Float3x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x4Impl) dest).data;
        float _t0 = sd[7] + sd[7];
        float _t1 = sd[8] + sd[8];
        float _t2 = sd[9] + sd[9];
        float _t3 = sd[5] * sd[5];
        float _t4 = sd[5] * sd[6];
        float _t5 = sd[4] * sd[6];
        float _buf0 = Math.fma(-Math.fma(sd[4], sd[4], _t3), _t0, sd[7]);
        float _buf1 = Math.fma(sd[3], sd[4], -_t4) * _t1;
        float _buf2 = Math.fma(sd[3], sd[5], _t5) * _t2;
        float _buf3 = sd[0];
        float _buf4 = Math.fma(sd[3], sd[4], _t4) * _t0;
        float _buf5 = Math.fma(-Math.fma(sd[3], sd[3], _t3), _t1, sd[8]);
        float _buf6 = Math.fma(sd[4], sd[5], -(sd[3] * sd[6])) * _t2;
        dd[7] = sd[1];
        dd[8] = Math.fma(sd[3], sd[5], -_t5) * _t0;
        return toMatrix3x4_scac7d5c1_1(dest, sd, dd, _t2, _buf0, _buf1, _buf2, _buf3, _buf4, _buf5, _buf6, Math.fma(sd[3], sd[6], sd[4] * sd[5]) * _t1);
    }

    /** Piece 2 of {@code toMatrix3x4}, split to fit the inline budget; reached only through it. */
    private Float3x4 toMatrix3x4_scac7d5c1_1(Float3x4 dest, float[] sd, float[] dd, float _t2, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4, float _buf5, float _buf6, float _buf7) {
        dd[10] = Math.fma(-Math.fma(sd[3], sd[3], sd[4] * sd[4]), _t2, sd[9]);
        dd[11] = sd[2];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        dd[9] = _buf7;
        ((Float3x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Compute the 3x4 matrix representation of this transform (the omitted last row is implicitly
     * {@code 0, 0, 0, 1}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x4 toMatrix3x4(@Mutated Double3x4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        float _t0 = sd[7] + sd[7];
        float _t1 = sd[8] + sd[8];
        float _t2 = sd[9] + sd[9];
        float _t3 = sd[5] * sd[5];
        float _t4 = sd[5] * sd[6];
        float _t5 = sd[4] * sd[6];
        float _buf0 = Math.fma(-Math.fma(sd[4], sd[4], _t3), _t0, sd[7]);
        float _buf1 = Math.fma(sd[3], sd[4], -_t4) * _t1;
        float _buf2 = Math.fma(sd[3], sd[5], _t5) * _t2;
        float _buf3 = sd[0];
        float _buf4 = Math.fma(sd[3], sd[4], _t4) * _t0;
        float _buf5 = Math.fma(-Math.fma(sd[3], sd[3], _t3), _t1, sd[8]);
        float _buf6 = Math.fma(sd[4], sd[5], -(sd[3] * sd[6])) * _t2;
        dd[7] = sd[1];
        dd[8] = Math.fma(sd[3], sd[5], -_t5) * _t0;
        return toMatrix3x4_sec42bb4e_1(dest, sd, dd, _t2, _buf0, _buf1, _buf2, _buf3, _buf4, _buf5, _buf6, Math.fma(sd[3], sd[6], sd[4] * sd[5]) * _t1);
    }

    /** Piece 2 of {@code toMatrix3x4}, split to fit the inline budget; reached only through it. */
    private Double3x4 toMatrix3x4_sec42bb4e_1(Double3x4 dest, float[] sd, double[] dd, float _t2, float _buf0, float _buf1, float _buf2, float _buf3, float _buf4, float _buf5, float _buf6, float _buf7) {
        dd[10] = Math.fma(-Math.fma(sd[3], sd[3], sd[4] * sd[4]), _t2, sd[9]);
        dd[11] = sd[2];
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
        dd[4] = _buf4;
        dd[5] = _buf5;
        dd[6] = _buf6;
        dd[9] = _buf7;
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
    public FloatRigid toRigid(@Mutated FloatRigid dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRigidImpl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        return dest;
    }


    /**
     * Narrow this transform to a rigid transform (translation and rotation; the scale is dropped)
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRigid toRigid(@Mutated DoubleRigid dest) {
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
     * Convert this transform to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform toDouble(@Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
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
     * Set this transform to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public FloatTransform makeIdentity() {
        float[] dd = this.data;
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
    public @Mutated FloatTransform set(FloatQuatR rotation) {
        return set(rotation.x(), rotation.y(), rotation.z(), rotation.w());
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
    @Mutated public FloatTransform set(float rotationX, float rotationY, float rotationZ, float rotationW) {
        float[] dd = this.data;
        dd[0] = 0.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = rotationX;
        dd[4] = rotationY;
        dd[5] = rotationZ;
        dd[6] = rotationW;
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
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
    public @Mutated FloatTransform set(Float3R translation) {
        return set(translation.x(), translation.y(), translation.z());
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
    @Mutated public FloatTransform set(float translationX, float translationY, float translationZ) {
        float[] dd = this.data;
        dd[0] = translationX;
        dd[1] = translationY;
        dd[2] = translationZ;
        VEC_1.intoArray(dd, 3);
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
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
    public FloatTransform lerp(FloatTransformR other, float t, @Mutated FloatTransform dest) {
        return lerp(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), other.sX(), other.sY(), other.sZ(), t, dest);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length; the rotation of
     * {@code other} must have unit length.
     *
     * @param other the transform to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform lerp(FloatTransformR other, float t, @Mutated DoubleTransform dest) {
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
    public FloatTransform lerp(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, float t, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
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
        return lerp_se37c393f_1(otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ, t, dest, sd, dd, _t0, _t17, 1.0f / _t17, (float) Math.sin(t * _t16), _t21, _t22, _t23, _t24, (float) Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private FloatTransform lerp_se37c393f_1(float otherTX, float otherTY, float otherTZ, float otherSX, float otherSY, float otherSZ, float t, FloatTransform dest, float[] sd, float[] dd, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
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
        dd[0] = Math.fma(t, otherTX - sd[0], sd[0]);
        return lerp_se37c393f_2(otherTY, otherTZ, otherSX, otherSY, otherSZ, t, dest, sd, dd);
    }

    /** Piece 3 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private FloatTransform lerp_se37c393f_2(float otherTY, float otherTZ, float otherSX, float otherSY, float otherSZ, float t, FloatTransform dest, float[] sd, float[] dd) {
        dd[1] = Math.fma(t, otherTY - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherTZ - sd[2], sd[2]);
        dd[7] = Math.fma(t, otherSX - sd[7], sd[7]);
        dd[8] = Math.fma(t, otherSY - sd[8], sd[8]);
        dd[9] = Math.fma(t, otherSZ - sd[9], sd[9]);
        return dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleTransform lerp(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, float t, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        return lerp_s82cffb32_1(otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ, t, dest, sd, dd, _t0, _t17, 1.0f / _t17, (float) Math.sin(t * _t16), _t21, _t22, _t23, _t24, (float) Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleTransform lerp_s82cffb32_1(float otherTX, float otherTY, float otherTZ, float otherSX, float otherSY, float otherSZ, float t, DoubleTransform dest, float[] sd, double[] dd, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
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
        dd[0] = Math.fma(t, otherTX - sd[0], sd[0]);
        return lerp_s82cffb32_2(otherTY, otherTZ, otherSX, otherSY, otherSZ, t, dest, sd, dd);
    }

    /** Piece 3 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleTransform lerp_s82cffb32_2(float otherTY, float otherTZ, float otherSX, float otherSY, float otherSZ, float t, DoubleTransform dest, float[] sd, double[] dd) {
        dd[1] = Math.fma(t, otherTY - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherTZ - sd[2], sd[2]);
        dd[7] = Math.fma(t, otherSX - sd[7], sd[7]);
        dd[8] = Math.fma(t, otherSY - sd[8], sd[8]);
        dd[9] = Math.fma(t, otherSZ - sd[9], sd[9]);
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
    public FloatTransform mul(FloatTransformR other, @Mutated FloatTransform dest) {
        return mul(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), other.sX(), other.sY(), other.sZ(), dest);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length; the rotation of
     * {@code other} must have unit length.
     *
     * @param other the right operand
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform mul(FloatTransformR other, @Mutated DoubleTransform dest) {
        return mul(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), other.sX(), other.sY(), other.sZ(), dest);
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
    public FloatTransform mul(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        float _t0 = otherTY * sd[8];
        float _t1 = otherTX * sd[7];
        float _t2 = otherTZ * sd[9];
        float _t12 = 2.0f * Math.fma(sd[3], _t0, -(sd[4] * _t1));
        float _t13 = 2.0f * Math.fma(sd[5], _t1, -(sd[3] * _t2));
        float _t14 = 2.0f * Math.fma(sd[4], _t2, -(sd[5] * _t0));
        dd[0] = Math.fma(sd[4], _t12, Math.fma(-sd[5], _t13, Math.fma(sd[6], _t14, Math.fma(otherTX, sd[7], sd[0]))));
        dd[1] = Math.fma(sd[5], _t14, Math.fma(-sd[3], _t12, Math.fma(sd[6], _t13, Math.fma(otherTY, sd[8], sd[1]))));
        dd[2] = Math.fma(sd[3], _t13, Math.fma(-sd[4], _t14, Math.fma(sd[6], _t12, Math.fma(otherTZ, sd[9], sd[2]))));
        return mul_s829558c4_1(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, dd, Math.fma(otherRX, sd[6], otherRW * sd[3]) + Math.fma(otherRZ, sd[4], -(otherRY * sd[5])));
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private FloatTransform mul_s829558c4_1(float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, FloatTransform dest, float[] sd, float[] dd, float _buf0) {
        float _buf1 = Math.fma(otherRY, sd[6], otherRW * sd[4]) + Math.fma(otherRX, sd[5], -(otherRZ * sd[3]));
        float _buf2 = Math.fma(otherRZ, sd[6], otherRW * sd[5]) + Math.fma(otherRY, sd[3], -(otherRX * sd[4]));
        dd[6] = Math.fma(otherRW, sd[6], -(otherRX * sd[3])) - Math.fma(otherRY, sd[4], otherRZ * sd[5]);
        dd[7] = otherSX * sd[7];
        dd[8] = otherSY * sd[8];
        dd[9] = otherSZ * sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleTransform mul(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        float _t0 = otherTY * sd[8];
        float _t1 = otherTX * sd[7];
        float _t2 = otherTZ * sd[9];
        float _t12 = 2.0f * Math.fma(sd[3], _t0, -(sd[4] * _t1));
        float _t13 = 2.0f * Math.fma(sd[5], _t1, -(sd[3] * _t2));
        float _t14 = 2.0f * Math.fma(sd[4], _t2, -(sd[5] * _t0));
        dd[0] = Math.fma(sd[4], _t12, Math.fma(-sd[5], _t13, Math.fma(sd[6], _t14, Math.fma(otherTX, sd[7], sd[0]))));
        dd[1] = Math.fma(sd[5], _t14, Math.fma(-sd[3], _t12, Math.fma(sd[6], _t13, Math.fma(otherTY, sd[8], sd[1]))));
        dd[2] = Math.fma(sd[3], _t13, Math.fma(-sd[4], _t14, Math.fma(sd[6], _t12, Math.fma(otherTZ, sd[9], sd[2]))));
        return mul_sfbb7313b_1(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, dd, Math.fma(otherRX, sd[6], otherRW * sd[3]) + Math.fma(otherRZ, sd[4], -(otherRY * sd[5])));
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private DoubleTransform mul_sfbb7313b_1(float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, DoubleTransform dest, float[] sd, double[] dd, float _buf0) {
        float _buf1 = Math.fma(otherRY, sd[6], otherRW * sd[4]) + Math.fma(otherRX, sd[5], -(otherRZ * sd[3]));
        float _buf2 = Math.fma(otherRZ, sd[6], otherRW * sd[5]) + Math.fma(otherRY, sd[3], -(otherRX * sd[4]));
        dd[6] = Math.fma(otherRW, sd[6], -(otherRX * sd[3])) - Math.fma(otherRY, sd[4], otherRZ * sd[5]);
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
    public FloatTransform preMul(FloatTransformR other, @Mutated FloatTransform dest) {
        return preMul(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), other.sX(), other.sY(), other.sZ(), dest);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length; the rotation of
     * {@code other} must have unit length.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform preMul(FloatTransformR other, @Mutated DoubleTransform dest) {
        return preMul(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), other.sX(), other.sY(), other.sZ(), dest);
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
    public FloatTransform preMul(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        float _t0 = otherSY * sd[1];
        float _t1 = otherSX * sd[0];
        float _t2 = otherSZ * sd[2];
        float _t12 = 2.0f * Math.fma(otherRX, _t0, -(otherRY * _t1));
        float _t13 = 2.0f * Math.fma(otherRZ, _t1, -(otherRX * _t2));
        float _t14 = 2.0f * Math.fma(otherRY, _t2, -(otherRZ * _t0));
        dd[0] = Math.fma(otherRY, _t12, Math.fma(-otherRZ, _t13, Math.fma(otherRW, _t14, Math.fma(otherSX, sd[0], otherTX))));
        dd[1] = Math.fma(otherRZ, _t14, Math.fma(-otherRX, _t12, Math.fma(otherRW, _t13, Math.fma(otherSY, sd[1], otherTY))));
        dd[2] = Math.fma(otherRX, _t13, Math.fma(-otherRY, _t14, Math.fma(otherRW, _t12, Math.fma(otherSZ, sd[2], otherTZ))));
        return preMul_s6485f9ab_1(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, dd, Math.fma(otherRX, sd[6], otherRW * sd[3]) + Math.fma(otherRY, sd[5], -(otherRZ * sd[4])), Math.fma(otherRY, sd[6], otherRW * sd[4]) + Math.fma(otherRZ, sd[3], -(otherRX * sd[5])));
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private FloatTransform preMul_s6485f9ab_1(float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, FloatTransform dest, float[] sd, float[] dd, float _buf0, float _buf1) {
        float _buf2 = Math.fma(otherRZ, sd[6], otherRW * sd[5]) + Math.fma(otherRX, sd[4], -(otherRY * sd[3]));
        dd[6] = Math.fma(otherRW, sd[6], -(otherRX * sd[3])) - Math.fma(otherRY, sd[4], otherRZ * sd[5]);
        dd[7] = otherSX * sd[7];
        dd[8] = otherSY * sd[8];
        dd[9] = otherSZ * sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleTransform preMul(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        float _t0 = otherSY * sd[1];
        float _t1 = otherSX * sd[0];
        float _t2 = otherSZ * sd[2];
        float _t12 = 2.0f * Math.fma(otherRX, _t0, -(otherRY * _t1));
        float _t13 = 2.0f * Math.fma(otherRZ, _t1, -(otherRX * _t2));
        float _t14 = 2.0f * Math.fma(otherRY, _t2, -(otherRZ * _t0));
        dd[0] = Math.fma(otherRY, _t12, Math.fma(-otherRZ, _t13, Math.fma(otherRW, _t14, Math.fma(otherSX, sd[0], otherTX))));
        dd[1] = Math.fma(otherRZ, _t14, Math.fma(-otherRX, _t12, Math.fma(otherRW, _t13, Math.fma(otherSY, sd[1], otherTY))));
        dd[2] = Math.fma(otherRX, _t13, Math.fma(-otherRY, _t14, Math.fma(otherRW, _t12, Math.fma(otherSZ, sd[2], otherTZ))));
        return preMul_s742ed43e_1(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, dd, Math.fma(otherRX, sd[6], otherRW * sd[3]) + Math.fma(otherRY, sd[5], -(otherRZ * sd[4])), Math.fma(otherRY, sd[6], otherRW * sd[4]) + Math.fma(otherRZ, sd[3], -(otherRX * sd[5])));
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private DoubleTransform preMul_s742ed43e_1(float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, DoubleTransform dest, float[] sd, double[] dd, float _buf0, float _buf1) {
        float _buf2 = Math.fma(otherRZ, sd[6], otherRW * sd[5]) + Math.fma(otherRX, sd[4], -(otherRY * sd[3]));
        dd[6] = Math.fma(otherRW, sd[6], -(otherRX * sd[3])) - Math.fma(otherRY, sd[4], otherRZ * sd[5]);
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
    public FloatTransform difference(FloatTransformR other, @Mutated FloatTransform dest) {
        return difference(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), other.sX(), other.sY(), other.sZ(), dest);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of the scale of this transform must be non-zero.
     *
     * @param other the target transform, reached by composing this transform with the result
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform difference(FloatTransformR other, @Mutated DoubleTransform dest) {
        return difference(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), other.sX(), other.sY(), other.sZ(), dest);
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
    public FloatTransform difference(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        float _rcp0 = 1.0f / sd[9];
        float _sp4 = _rcp0 * sd[2];
        float _sp0 = otherTZ * _rcp0;
        float _rcp1 = 1.0f / sd[7];
        float _sp3 = _rcp1 * sd[0];
        float _sp1 = otherTX * _rcp1;
        float _rcp2 = 1.0f / sd[8];
        float _sp5 = _rcp2 * sd[1];
        float _sp2 = otherTY * _rcp2;
        return difference_s3157bc99_1(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, dd, _rcp0, _sp4, _sp0, _rcp1, _sp3, _sp1, _rcp2, _sp5, _sp2, 2.0f * Math.fma(_sp0, sd[3], -(_sp1 * sd[5])), 2.0f * Math.fma(_sp1, sd[4], -(_sp2 * sd[3])), 2.0f * Math.fma(_sp2, sd[5], -(_sp0 * sd[4])), 2.0f * Math.fma(_sp3, sd[5], -(_sp4 * sd[3])), 2.0f * Math.fma(_sp5, sd[3], -(_sp3 * sd[4])), 2.0f * Math.fma(_sp4, sd[4], -(_sp5 * sd[5])));
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private FloatTransform difference_s3157bc99_1(float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, FloatTransform dest, float[] sd, float[] dd, float _rcp0, float _sp4, float _sp0, float _rcp1, float _sp3, float _sp1, float _rcp2, float _sp5, float _sp2, float _t30, float _t31, float _t32, float _t33, float _t34, float _t35) {
        dd[0] = Math.fma(sd[5], _t30, -(sd[4] * _t31)) + Math.fma(sd[6], _t32, _sp1) + (Math.fma(sd[5], _t33, -(sd[4] * _t34)) + Math.fma(sd[6], _t35, -_sp3));
        dd[1] = Math.fma(sd[3], _t31, -(sd[5] * _t32)) + Math.fma(sd[6], _t30, _sp2) + (Math.fma(sd[3], _t34, -(sd[5] * _t35)) + Math.fma(sd[6], _t33, -_sp5));
        dd[2] = Math.fma(sd[4], _t32, -(sd[3] * _t30)) + Math.fma(sd[6], _t31, _sp0) + (Math.fma(sd[4], _t35, -(sd[3] * _t33)) + Math.fma(sd[6], _t34, -_sp4));
        return difference_s3157bc99_2(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, dd, _rcp0, _rcp1, _rcp2, Math.fma(otherRX, sd[6], -(otherRW * sd[3])) + Math.fma(otherRY, sd[5], -(otherRZ * sd[4])), Math.fma(otherRY, sd[6], -(otherRW * sd[4])) + Math.fma(otherRZ, sd[3], -(otherRX * sd[5])));
    }

    /** Piece 3 of {@code difference}, split to fit the inline budget; reached only through it. */
    private FloatTransform difference_s3157bc99_2(float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, FloatTransform dest, float[] sd, float[] dd, float _rcp0, float _rcp1, float _rcp2, float _buf0, float _buf1) {
        float _buf2 = Math.fma(otherRX, sd[4], -(otherRY * sd[3])) + Math.fma(otherRZ, sd[6], -(otherRW * sd[5]));
        dd[6] = Math.fma(otherRX, sd[3], otherRW * sd[6]) - Math.fma(-otherRZ, sd[5], -(otherRY * sd[4]));
        dd[7] = otherSX * _rcp1;
        dd[8] = otherSY * _rcp2;
        dd[9] = otherSZ * _rcp0;
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public DoubleTransform difference(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        float _rcp0 = 1.0f / sd[9];
        float _sp4 = _rcp0 * sd[2];
        float _sp0 = otherTZ * _rcp0;
        float _rcp1 = 1.0f / sd[7];
        float _sp3 = _rcp1 * sd[0];
        float _sp1 = otherTX * _rcp1;
        float _rcp2 = 1.0f / sd[8];
        float _sp5 = _rcp2 * sd[1];
        float _sp2 = otherTY * _rcp2;
        return difference_s2880de78_1(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, dd, _rcp0, _sp4, _sp0, _rcp1, _sp3, _sp1, _rcp2, _sp5, _sp2, 2.0f * Math.fma(_sp0, sd[3], -(_sp1 * sd[5])), 2.0f * Math.fma(_sp1, sd[4], -(_sp2 * sd[3])), 2.0f * Math.fma(_sp2, sd[5], -(_sp0 * sd[4])), 2.0f * Math.fma(_sp3, sd[5], -(_sp4 * sd[3])), 2.0f * Math.fma(_sp5, sd[3], -(_sp3 * sd[4])), 2.0f * Math.fma(_sp4, sd[4], -(_sp5 * sd[5])));
    }

    /** Piece 2 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleTransform difference_s2880de78_1(float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, DoubleTransform dest, float[] sd, double[] dd, float _rcp0, float _sp4, float _sp0, float _rcp1, float _sp3, float _sp1, float _rcp2, float _sp5, float _sp2, float _t30, float _t31, float _t32, float _t33, float _t34, float _t35) {
        dd[0] = Math.fma(sd[5], _t30, -(sd[4] * _t31)) + Math.fma(sd[6], _t32, _sp1) + (Math.fma(sd[5], _t33, -(sd[4] * _t34)) + Math.fma(sd[6], _t35, -_sp3));
        dd[1] = Math.fma(sd[3], _t31, -(sd[5] * _t32)) + Math.fma(sd[6], _t30, _sp2) + (Math.fma(sd[3], _t34, -(sd[5] * _t35)) + Math.fma(sd[6], _t33, -_sp5));
        dd[2] = Math.fma(sd[4], _t32, -(sd[3] * _t30)) + Math.fma(sd[6], _t31, _sp0) + (Math.fma(sd[4], _t35, -(sd[3] * _t33)) + Math.fma(sd[6], _t34, -_sp4));
        return difference_s2880de78_2(otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, dest, sd, dd, _rcp0, _rcp1, _rcp2, Math.fma(otherRX, sd[6], -(otherRW * sd[3])) + Math.fma(otherRY, sd[5], -(otherRZ * sd[4])), Math.fma(otherRY, sd[6], -(otherRW * sd[4])) + Math.fma(otherRZ, sd[3], -(otherRX * sd[5])));
    }

    /** Piece 3 of {@code difference}, split to fit the inline budget; reached only through it. */
    private DoubleTransform difference_s2880de78_2(float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, DoubleTransform dest, float[] sd, double[] dd, float _rcp0, float _rcp1, float _rcp2, float _buf0, float _buf1) {
        float _buf2 = Math.fma(otherRX, sd[4], -(otherRY * sd[3])) + Math.fma(otherRZ, sd[6], -(otherRW * sd[5]));
        dd[6] = Math.fma(otherRX, sd[3], otherRW * sd[6]) - Math.fma(-otherRZ, sd[5], -(otherRY * sd[4]));
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
    public FloatTransform invert(@Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        float _rcp0 = 1.0f / sd[7];
        float _sp0 = sd[0] * _rcp0;
        float _rcp1 = 1.0f / sd[9];
        float _sp1 = sd[2] * _rcp1;
        float _rcp2 = 1.0f / sd[8];
        float _sp2 = sd[1] * _rcp2;
        float _t0 = -sd[4];
        float _t1 = -sd[5];
        float _t2 = -sd[3];
        float _t18 = 2.0f * Math.fma(_sp0, sd[5], -(_sp1 * sd[3]));
        float _t19 = 2.0f * Math.fma(_sp2, sd[3], -(_sp0 * sd[4]));
        float _t20 = 2.0f * Math.fma(_sp1, sd[4], -(_sp2 * sd[5]));
        dd[0] = Math.fma(sd[5], _t18, Math.fma(_t0, _t19, Math.fma(sd[6], _t20, -_sp0)));
        dd[1] = Math.fma(sd[3], _t19, Math.fma(_t1, _t20, Math.fma(sd[6], _t18, -_sp2)));
        dd[2] = Math.fma(sd[4], _t20, Math.fma(_t2, _t18, Math.fma(sd[6], _t19, -_sp1)));
        dd[3] = _t2;
        dd[4] = _t0;
        dd[5] = _t1;
        dd[6] = sd[6];
        dd[7] = _rcp0;
        dd[8] = _rcp2;
        dd[9] = _rcp1;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform invert(@Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        float _rcp0 = 1.0f / sd[7];
        float _sp0 = sd[0] * _rcp0;
        float _rcp1 = 1.0f / sd[9];
        float _sp1 = sd[2] * _rcp1;
        float _rcp2 = 1.0f / sd[8];
        float _sp2 = sd[1] * _rcp2;
        float _t0 = -sd[4];
        float _t1 = -sd[5];
        float _t2 = -sd[3];
        float _t18 = 2.0f * Math.fma(_sp0, sd[5], -(_sp1 * sd[3]));
        float _t19 = 2.0f * Math.fma(_sp2, sd[3], -(_sp0 * sd[4]));
        float _t20 = 2.0f * Math.fma(_sp1, sd[4], -(_sp2 * sd[5]));
        dd[0] = Math.fma(sd[5], _t18, Math.fma(_t0, _t19, Math.fma(sd[6], _t20, -_sp0)));
        dd[1] = Math.fma(sd[3], _t19, Math.fma(_t1, _t20, Math.fma(sd[6], _t18, -_sp2)));
        dd[2] = Math.fma(sd[4], _t20, Math.fma(_t2, _t18, Math.fma(sd[6], _t19, -_sp1)));
        dd[3] = _t2;
        dd[4] = _t0;
        dd[5] = _t1;
        dd[6] = sd[6];
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
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatTransform normalize(@Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
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
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Normalize this transform so that its rotation part has unit length, leaving its translation
     * and scale unchanged (a zero-length rotation yields the zero quaternion) and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform normalize(@Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
     * Get the rotation of this transform and store the result in {@code dest}.
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
     * Get the rotation of this transform and store the result in {@code dest}.
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
     * Get the scaling factors of this transform and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getScale(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[7];
        dd[1] = sd[8];
        dd[2] = sd[9];
        return dest;
    }


    /**
     * Get the scaling factors of this transform and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getScale(@Mutated Double3 dest) {
        float[] sd = this.data;
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
    public Float3 getTranslation(@Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
    }


    /**
     * Get the translation of this transform and store the result in {@code dest}.
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
     * Set this transform to a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return this
     */
    public @Mutated FloatTransform makeRotationAxis(float angle, Float3R axis) {
        return makeRotationAxis(angle, axis.x(), axis.y(), axis.z());
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
    @Mutated public FloatTransform makeRotationAxis(float angle, float axisX, float axisY, float axisZ) {
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
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
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
    @Mutated public FloatTransform makeRotationX(float angle) {
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
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
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
    @Mutated public FloatTransform makeRotationXYZ(float angleX, float angleY, float angleZ) {
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
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
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
    @Mutated public FloatTransform makeRotationXZY(float angleX, float angleZ, float angleY) {
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
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
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
    @Mutated public FloatTransform makeRotationY(float angle) {
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        VEC_2.intoArray(dd, 0);
        dd[4] = _t1;
        dd[5] = 0.0f;
        dd[6] = (float) Math.cosFromSin(_t1, _t0);
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
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
    @Mutated public FloatTransform makeRotationYXZ(float angleY, float angleX, float angleZ) {
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
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
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
    @Mutated public FloatTransform makeRotationYZX(float angleY, float angleZ, float angleX) {
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
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
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
    @Mutated public FloatTransform makeRotationZ(float angle) {
        float[] dd = this.data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        VEC_2.intoArray(dd, 0);
        dd[4] = 0.0f;
        dd[5] = _t1;
        dd[6] = (float) Math.cosFromSin(_t1, _t0);
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
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
    @Mutated public FloatTransform makeRotationZXY(float angleZ, float angleX, float angleY) {
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
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
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
    @Mutated public FloatTransform makeRotationZYX(float angleZ, float angleY, float angleX) {
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
        dd[7] = 1.0f;
        dd[8] = 1.0f;
        dd[9] = 1.0f;
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
    public @Mutated FloatTransform makeScaling(Float3R scale) {
        return makeScaling(scale.x(), scale.y(), scale.z());
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
    @Mutated public FloatTransform makeScaling(float scaleX, float scaleY, float scaleZ) {
        float[] dd = this.data;
        VEC_2.intoArray(dd, 0);
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 1.0f;
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
    @Mutated public FloatTransform makeScaling(float scale) {
        float[] dd = this.data;
        VEC_2.intoArray(dd, 0);
        dd[4] = 0.0f;
        dd[5] = 0.0f;
        dd[6] = 1.0f;
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
    public FloatTransform rotate(FloatQuatR rotation, @Mutated FloatTransform dest) {
        return rotate(rotation.x(), rotation.y(), rotation.z(), rotation.w(), dest);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform rotate(FloatQuatR rotation, @Mutated DoubleTransform dest) {
        return rotate(rotation.x(), rotation.y(), rotation.z(), rotation.w(), dest);
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
    public FloatTransform rotate(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = Math.fma(rotationX, sd[6], rotationW * sd[3]) + Math.fma(rotationZ, sd[4], -(rotationY * sd[5]));
        float _buf1 = Math.fma(rotationY, sd[6], rotationW * sd[4]) + Math.fma(rotationX, sd[5], -(rotationZ * sd[3]));
        float _buf2 = Math.fma(rotationZ, sd[6], rotationW * sd[5]) + Math.fma(rotationY, sd[3], -(rotationX * sd[4]));
        dd[6] = Math.fma(rotationW, sd[6], -(rotationX * sd[3])) - Math.fma(rotationY, sd[4], rotationZ * sd[5]);
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
    public DoubleTransform rotate(float rotationX, float rotationY, float rotationZ, float rotationW, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        float _buf0 = Math.fma(rotationX, sd[6], rotationW * sd[3]) + Math.fma(rotationZ, sd[4], -(rotationY * sd[5]));
        float _buf1 = Math.fma(rotationY, sd[6], rotationW * sd[4]) + Math.fma(rotationX, sd[5], -(rotationZ * sd[3]));
        float _buf2 = Math.fma(rotationZ, sd[6], rotationW * sd[5]) + Math.fma(rotationY, sd[3], -(rotationX * sd[4]));
        dd[6] = Math.fma(rotationW, sd[6], -(rotationX * sd[3])) - Math.fma(rotationY, sd[4], rotationZ * sd[5]);
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
    public FloatTransform rotateAxis(float angle, Float3R axis, @Mutated FloatTransform dest) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z(), dest);
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
    public DoubleTransform rotateAxis(float angle, Float3R axis, @Mutated DoubleTransform dest) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z(), dest);
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
    public FloatTransform rotateAxis(float angle, float axisX, float axisY, float axisZ, @Mutated FloatTransform dest) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = (float) Math.cosFromSin(_t1, _t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateAxis_s8fb069da_1(dest, sd, dd, _t2, _t3, _t4, _t5, Math.fma(sd[3], _t5, sd[6] * _t2) + Math.fma(sd[4], _t3, -(sd[5] * _t4)), Math.fma(sd[4], _t5, sd[6] * _t4) + Math.fma(sd[5], _t2, -(sd[3] * _t3)), Math.fma(sd[5], _t5, sd[6] * _t3) + Math.fma(sd[3], _t4, -(sd[4] * _t2)));
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private FloatTransform rotateAxis_s8fb069da_1(FloatTransform dest, float[] sd, float[] dd, float _t2, float _t3, float _t4, float _t5, float _buf0, float _buf1, float _buf2) {
        dd[6] = Math.fma(sd[6], _t5, -(sd[3] * _t2)) - Math.fma(sd[4], _t4, sd[5] * _t3);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
        return dest;
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
    public DoubleTransform rotateAxis(float angle, float axisX, float axisY, float axisZ, @Mutated DoubleTransform dest) {
        if (axisY == 0 && axisZ == 0 && Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = (float) Math.cosFromSin(_t1, _t0);
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return rotateAxis_se932841d_1(dest, sd, dd, _t2, _t3, _t4, _t5, Math.fma(sd[3], _t5, sd[6] * _t2) + Math.fma(sd[4], _t3, -(sd[5] * _t4)), Math.fma(sd[4], _t5, sd[6] * _t4) + Math.fma(sd[5], _t2, -(sd[3] * _t3)), Math.fma(sd[5], _t5, sd[6] * _t3) + Math.fma(sd[3], _t4, -(sd[4] * _t2)));
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateAxis_se932841d_1(DoubleTransform dest, float[] sd, double[] dd, float _t2, float _t3, float _t4, float _t5, float _buf0, float _buf1, float _buf2) {
        dd[6] = Math.fma(sd[6], _t5, -(sd[3] * _t2)) - Math.fma(sd[4], _t4, sd[5] * _t3);
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
    public FloatTransform rotateX(float angle, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
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
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform rotateX(float angle, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
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
    public FloatTransform rotateXYZ(float angleX, float angleY, float angleZ, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
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
        return rotateXYZ_s99db4da9_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t21, sd[6] * _t19) + Math.fma(sd[4], _t20, -(sd[5] * _t22)), Math.fma(sd[4], _t21, sd[6] * _t22) + Math.fma(sd[5], _t19, -(sd[3] * _t20)));
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private FloatTransform rotateXYZ_s99db4da9_1(FloatTransform dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[5], _t21, sd[6] * _t20) + Math.fma(sd[3], _t22, -(sd[4] * _t19));
        dd[6] = Math.fma(sd[6], _t21, -(sd[3] * _t19)) - Math.fma(sd[4], _t22, sd[5] * _t20);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
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
    public DoubleTransform rotateXYZ(float angleX, float angleY, float angleZ, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        return rotateXYZ_se7d3bf68_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t21, sd[6] * _t19) + Math.fma(sd[4], _t20, -(sd[5] * _t22)), Math.fma(sd[4], _t21, sd[6] * _t22) + Math.fma(sd[5], _t19, -(sd[3] * _t20)));
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateXYZ_se7d3bf68_1(DoubleTransform dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[5], _t21, sd[6] * _t20) + Math.fma(sd[3], _t22, -(sd[4] * _t19));
        dd[6] = Math.fma(sd[6], _t21, -(sd[3] * _t19)) - Math.fma(sd[4], _t22, sd[5] * _t20);
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
    public FloatTransform rotateXZY(float angleX, float angleZ, float angleY, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
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
        return rotateXZY_s32fc2323_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t19, sd[6] * _t21) + Math.fma(sd[4], _t20, -(sd[5] * _t22)), Math.fma(sd[4], _t19, sd[6] * _t22) + Math.fma(sd[5], _t21, -(sd[3] * _t20)));
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private FloatTransform rotateXZY_s32fc2323_1(FloatTransform dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[5], _t19, sd[6] * _t20) + Math.fma(sd[3], _t22, -(sd[4] * _t21));
        dd[6] = Math.fma(sd[6], _t19, -(sd[3] * _t21)) - Math.fma(sd[4], _t22, sd[5] * _t20);
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
    public DoubleTransform rotateXZY(float angleX, float angleZ, float angleY, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        return rotateXZY_s2b40626_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t19, sd[6] * _t21) + Math.fma(sd[4], _t20, -(sd[5] * _t22)), Math.fma(sd[4], _t19, sd[6] * _t22) + Math.fma(sd[5], _t21, -(sd[3] * _t20)));
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateXZY_s2b40626_1(DoubleTransform dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[5], _t19, sd[6] * _t20) + Math.fma(sd[3], _t22, -(sd[4] * _t21));
        dd[6] = Math.fma(sd[6], _t19, -(sd[3] * _t21)) - Math.fma(sd[4], _t22, sd[5] * _t20);
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
    public FloatTransform rotateY(float angle, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
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
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform rotateY(float angle, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
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
    public FloatTransform rotateYXZ(float angleY, float angleX, float angleZ, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
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
        return rotateYXZ_sfe748457_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t19, sd[6] * _t20) + Math.fma(sd[4], _t21, -(sd[5] * _t22)), Math.fma(sd[4], _t19, sd[6] * _t22) + Math.fma(sd[5], _t20, -(sd[3] * _t21)));
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private FloatTransform rotateYXZ_sfe748457_1(FloatTransform dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[5], _t19, sd[6] * _t21) + Math.fma(sd[3], _t22, -(sd[4] * _t20));
        dd[6] = Math.fma(sd[6], _t19, -(sd[3] * _t20)) - Math.fma(sd[4], _t22, sd[5] * _t21);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
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
    public DoubleTransform rotateYXZ(float angleY, float angleX, float angleZ, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        return rotateYXZ_s5473163a_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t19, sd[6] * _t20) + Math.fma(sd[4], _t21, -(sd[5] * _t22)), Math.fma(sd[4], _t19, sd[6] * _t22) + Math.fma(sd[5], _t20, -(sd[3] * _t21)));
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateYXZ_s5473163a_1(DoubleTransform dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[5], _t19, sd[6] * _t21) + Math.fma(sd[3], _t22, -(sd[4] * _t20));
        dd[6] = Math.fma(sd[6], _t19, -(sd[3] * _t20)) - Math.fma(sd[4], _t22, sd[5] * _t21);
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
    public FloatTransform rotateYZX(float angleY, float angleZ, float angleX, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
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
        return rotateYZX_sfdd45cbf_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t21, sd[6] * _t19) + Math.fma(sd[4], _t22, -(sd[5] * _t20)), Math.fma(sd[4], _t21, sd[6] * _t20) + Math.fma(sd[5], _t19, -(sd[3] * _t22)));
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private FloatTransform rotateYZX_sfdd45cbf_1(FloatTransform dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[5], _t21, sd[6] * _t22) + Math.fma(sd[3], _t20, -(sd[4] * _t19));
        dd[6] = Math.fma(sd[6], _t21, -(sd[3] * _t19)) - Math.fma(sd[4], _t20, sd[5] * _t22);
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
    public DoubleTransform rotateYZX(float angleY, float angleZ, float angleX, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        return rotateYZX_s7b8fddb2_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t21, sd[6] * _t19) + Math.fma(sd[4], _t22, -(sd[5] * _t20)), Math.fma(sd[4], _t21, sd[6] * _t20) + Math.fma(sd[5], _t19, -(sd[3] * _t22)));
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateYZX_s7b8fddb2_1(DoubleTransform dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[5], _t21, sd[6] * _t22) + Math.fma(sd[3], _t20, -(sd[4] * _t19));
        dd[6] = Math.fma(sd[6], _t21, -(sd[3] * _t19)) - Math.fma(sd[4], _t20, sd[5] * _t22);
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
    public FloatTransform rotateZ(float angle, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
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
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[5] = _buf1;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform rotateZ(float angle, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[5] = _buf1;
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
    public FloatTransform rotateZXY(float angleZ, float angleX, float angleY, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
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
        return rotateZXY_sad5fe64f_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t21, sd[6] * _t22) + Math.fma(sd[4], _t19, -(sd[5] * _t20)), Math.fma(sd[4], _t21, sd[6] * _t20) + Math.fma(sd[5], _t22, -(sd[3] * _t19)));
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private FloatTransform rotateZXY_sad5fe64f_1(FloatTransform dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[5], _t21, sd[6] * _t19) + Math.fma(sd[3], _t20, -(sd[4] * _t22));
        dd[6] = Math.fma(sd[6], _t21, -(sd[3] * _t22)) - Math.fma(sd[4], _t20, sd[5] * _t19);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[3] = _buf0;
        dd[4] = _buf1;
        dd[5] = _buf2;
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
    public DoubleTransform rotateZXY(float angleZ, float angleX, float angleY, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        return rotateZXY_sd19307e2_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t21, sd[6] * _t22) + Math.fma(sd[4], _t19, -(sd[5] * _t20)), Math.fma(sd[4], _t21, sd[6] * _t20) + Math.fma(sd[5], _t22, -(sd[3] * _t19)));
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateZXY_sd19307e2_1(DoubleTransform dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[5], _t21, sd[6] * _t19) + Math.fma(sd[3], _t20, -(sd[4] * _t22));
        dd[6] = Math.fma(sd[6], _t21, -(sd[3] * _t22)) - Math.fma(sd[4], _t20, sd[5] * _t19);
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
    public FloatTransform rotateZYX(float angleZ, float angleY, float angleX, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
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
        return rotateZYX_sc1f2f90d_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t19, sd[6] * _t21) + Math.fma(sd[4], _t22, -(sd[5] * _t20)), Math.fma(sd[4], _t19, sd[6] * _t20) + Math.fma(sd[5], _t21, -(sd[3] * _t22)));
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private FloatTransform rotateZYX_sc1f2f90d_1(FloatTransform dest, float[] sd, float[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[5], _t19, sd[6] * _t22) + Math.fma(sd[3], _t20, -(sd[4] * _t21));
        dd[6] = Math.fma(sd[6], _t19, -(sd[3] * _t21)) - Math.fma(sd[4], _t20, sd[5] * _t22);
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
    public DoubleTransform rotateZYX(float angleZ, float angleY, float angleX, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
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
        return rotateZYX_s6bfed75c_1(dest, sd, dd, _t19, _t20, _t21, _t22, Math.fma(sd[3], _t19, sd[6] * _t21) + Math.fma(sd[4], _t22, -(sd[5] * _t20)), Math.fma(sd[4], _t19, sd[6] * _t20) + Math.fma(sd[5], _t21, -(sd[3] * _t22)));
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private DoubleTransform rotateZYX_s6bfed75c_1(DoubleTransform dest, float[] sd, double[] dd, float _t19, float _t20, float _t21, float _t22, float _buf0, float _buf1) {
        float _buf2 = Math.fma(sd[5], _t19, sd[6] * _t22) + Math.fma(sd[3], _t20, -(sd[4] * _t21));
        dd[6] = Math.fma(sd[6], _t19, -(sd[3] * _t21)) - Math.fma(sd[4], _t20, sd[5] * _t22);
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
    public FloatTransform scale(Float3R scale, @Mutated FloatTransform dest) {
        return scale(scale.x(), scale.y(), scale.z(), dest);
    }


    /**
     * Apply a scaling by {@code scale} to this transform and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code S} the scaling transform, then the new
     * transform will be {@code M * S}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * S * v}, the scaling will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scale the scale factors
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform scale(Float3R scale, @Mutated DoubleTransform dest) {
        return scale(scale.x(), scale.y(), scale.z(), dest);
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
    public FloatTransform scale(float scaleX, float scaleY, float scaleZ, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scaleX the {@code x} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param scaleY the {@code y} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param scaleZ the {@code z} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform scale(float scaleX, float scaleY, float scaleZ, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
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
    public FloatTransform scale(float scale, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = scale * sd[7];
        dd[8] = scale * sd[8];
        dd[9] = scale * sd[9];
        return dest;
    }


    /**
     * Apply a scaling by {@code scale} to this transform and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code S} the scaling transform, then the new
     * transform will be {@code M * S}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * S * v}, the scaling will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scale the scale factor
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform scale(float scale, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
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
    public FloatTransform translate(Float3R translation, @Mutated FloatTransform dest) {
        return translate(translation.x(), translation.y(), translation.z(), dest);
    }


    /**
     * Apply a translation by {@code translation} to this transform and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code T} the translation transform, then the new
     * transform will be {@code M * T}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * T * v}, the translation will be applied first.
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
    public DoubleTransform translate(Float3R translation, @Mutated DoubleTransform dest) {
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
    public FloatTransform translate(float translationX, float translationY, float translationZ, @Mutated FloatTransform dest) {
        float[] sd = this.data;
        float[] dd = ((FloatTransformImpl) dest).data;
        float _t0 = sd[8] * translationY;
        float _t1 = sd[7] * translationX;
        float _t2 = sd[9] * translationZ;
        float _t12 = 2.0f * Math.fma(sd[3], _t0, -(sd[4] * _t1));
        float _t13 = 2.0f * Math.fma(sd[5], _t1, -(sd[3] * _t2));
        float _t14 = 2.0f * Math.fma(sd[4], _t2, -(sd[5] * _t0));
        dd[0] = Math.fma(sd[4], _t12, Math.fma(-sd[5], _t13, Math.fma(sd[6], _t14, Math.fma(sd[7], translationX, sd[0]))));
        dd[1] = Math.fma(sd[5], _t14, Math.fma(-sd[3], _t12, Math.fma(sd[6], _t13, Math.fma(sd[8], translationY, sd[1]))));
        dd[2] = Math.fma(sd[3], _t13, Math.fma(-sd[4], _t14, Math.fma(sd[6], _t12, Math.fma(sd[9], translationZ, sd[2]))));
        FloatVector.fromArray(COL_SPECIES, sd, 3).intoArray(dd, 3);
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        return dest;
    }


    /**
     * Apply a translation by ({@code translationX}, {@code translationY}, {@code translationZ}) to
     * this transform and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} transform and {@code T} the translation transform, then the new
     * transform will be {@code M * T}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * T * v}, the translation will be applied first.
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
    public DoubleTransform translate(float translationX, float translationY, float translationZ, @Mutated DoubleTransform dest) {
        float[] sd = this.data;
        double[] dd = ((DoubleTransformImpl) dest).data;
        float _t0 = sd[8] * translationY;
        float _t1 = sd[7] * translationX;
        float _t2 = sd[9] * translationZ;
        float _t12 = 2.0f * Math.fma(sd[3], _t0, -(sd[4] * _t1));
        float _t13 = 2.0f * Math.fma(sd[5], _t1, -(sd[3] * _t2));
        float _t14 = 2.0f * Math.fma(sd[4], _t2, -(sd[5] * _t0));
        dd[0] = Math.fma(sd[4], _t12, Math.fma(-sd[5], _t13, Math.fma(sd[6], _t14, Math.fma(sd[7], translationX, sd[0]))));
        dd[1] = Math.fma(sd[5], _t14, Math.fma(-sd[3], _t12, Math.fma(sd[6], _t13, Math.fma(sd[8], translationY, sd[1]))));
        dd[2] = Math.fma(sd[3], _t13, Math.fma(-sd[4], _t14, Math.fma(sd[6], _t12, Math.fma(sd[9], translationZ, sd[2]))));
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
     * Transform {@code v} by this transform and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    public Float3 transform(Float3R v, @Mutated Float3 dest) {
        return transform(v.x(), v.y(), v.z(), dest);
    }


    /**
     * Transform {@code v} by this transform and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transform(Float3R v, @Mutated Double3 dest) {
        return transform(v.x(), v.y(), v.z(), dest);
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
    public Float3 transform(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[8] * vY;
        float _t1 = sd[7] * vX;
        float _t2 = sd[9] * vZ;
        float _t12 = 2.0f * Math.fma(sd[3], _t0, -(sd[4] * _t1));
        float _t13 = 2.0f * Math.fma(sd[5], _t1, -(sd[3] * _t2));
        float _t14 = 2.0f * Math.fma(sd[4], _t2, -(sd[5] * _t0));
        dd[0] = Math.fma(sd[4], _t12, Math.fma(-sd[5], _t13, Math.fma(sd[6], _t14, Math.fma(sd[7], vX, sd[0]))));
        dd[1] = Math.fma(sd[5], _t14, Math.fma(-sd[3], _t12, Math.fma(sd[6], _t13, Math.fma(sd[8], vY, sd[1]))));
        dd[2] = Math.fma(sd[3], _t13, Math.fma(-sd[4], _t14, Math.fma(sd[6], _t12, Math.fma(sd[9], vZ, sd[2]))));
        return dest;
    }


    /**
     * Transform ({@code vX}, {@code vY}, {@code vZ}) by this transform and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
        float _t0 = sd[8] * vY;
        float _t1 = sd[7] * vX;
        float _t2 = sd[9] * vZ;
        float _t12 = 2.0f * Math.fma(sd[3], _t0, -(sd[4] * _t1));
        float _t13 = 2.0f * Math.fma(sd[5], _t1, -(sd[3] * _t2));
        float _t14 = 2.0f * Math.fma(sd[4], _t2, -(sd[5] * _t0));
        dd[0] = Math.fma(sd[4], _t12, Math.fma(-sd[5], _t13, Math.fma(sd[6], _t14, Math.fma(sd[7], vX, sd[0]))));
        dd[1] = Math.fma(sd[5], _t14, Math.fma(-sd[3], _t12, Math.fma(sd[6], _t13, Math.fma(sd[8], vY, sd[1]))));
        dd[2] = Math.fma(sd[3], _t13, Math.fma(-sd[4], _t14, Math.fma(sd[6], _t12, Math.fma(sd[9], vZ, sd[2]))));
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
    public Float3 transformDirection(Float3R v, @Mutated Float3 dest) {
        return transformDirection(v.x(), v.y(), v.z(), dest);
    }


    /**
     * Transform the given direction by the rotation part of this transform, ignoring translation
     * and scale and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirection(Float3R v, @Mutated Double3 dest) {
        return transformDirection(v.x(), v.y(), v.z(), dest);
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
     * Transform the given direction by the rotation part of this transform, ignoring translation
     * and scale and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
    public Float3 transformDirectionInverse(Float3R v, @Mutated Float3 dest) {
        return transformDirectionInverse(v.x(), v.y(), v.z(), dest);
    }


    /**
     * Transform the given direction by the inverse of this transform's rotation (world to local),
     * ignoring translation and scale, without materializing {@code invert()} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformDirectionInverse(Float3R v, @Mutated Double3 dest) {
        return transformDirectionInverse(v.x(), v.y(), v.z(), dest);
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
     * Transform the given direction by the inverse of this transform's rotation (world to local),
     * ignoring translation and scale, without materializing {@code invert()} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
     * Transform {@code p} by the inverse of this transform and store the result in {@code dest}.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Float3 transformInverse(Float3R p, @Mutated Float3 dest) {
        return transformInverse(p.x(), p.y(), p.z(), dest);
    }


    /**
     * Transform {@code p} by the inverse of this transform and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformInverse(Float3R p, @Mutated Double3 dest) {
        return transformInverse(p.x(), p.y(), p.z(), dest);
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
    public Float3 transformInverse(float pX, float pY, float pZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t12 = 2.0f * Math.fma(sd[3], _t0, -(sd[5] * _t1));
        float _t13 = 2.0f * Math.fma(sd[4], _t1, -(sd[3] * _t2));
        float _t14 = 2.0f * Math.fma(sd[5], _t2, -(sd[4] * _t0));
        dd[0] = Math.fma(sd[5], _t12, Math.fma(-sd[4], _t13, Math.fma(sd[6], _t14, _t1))) / sd[7];
        dd[1] = Math.fma(sd[3], _t13, Math.fma(-sd[5], _t14, Math.fma(sd[6], _t12, _t2))) / sd[8];
        dd[2] = Math.fma(sd[4], _t14, Math.fma(-sd[3], _t12, Math.fma(sd[6], _t13, _t0))) / sd[9];
        return dest;
    }


    /**
     * Transform ({@code pX}, {@code pY}, {@code pZ}) by the inverse of this transform and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3 transformInverse(float pX, float pY, float pZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = pZ - sd[2];
        float _t1 = pX - sd[0];
        float _t2 = pY - sd[1];
        float _t12 = 2.0f * Math.fma(sd[3], _t0, -(sd[5] * _t1));
        float _t13 = 2.0f * Math.fma(sd[4], _t1, -(sd[3] * _t2));
        float _t14 = 2.0f * Math.fma(sd[5], _t2, -(sd[4] * _t0));
        dd[0] = Math.fma(sd[5], _t12, Math.fma(-sd[4], _t13, Math.fma(sd[6], _t14, _t1))) / sd[7];
        dd[1] = Math.fma(sd[3], _t13, Math.fma(-sd[5], _t14, Math.fma(sd[6], _t12, _t2))) / sd[8];
        dd[2] = Math.fma(sd[4], _t14, Math.fma(-sd[3], _t12, Math.fma(sd[6], _t13, _t0))) / sd[9];
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
    public Float3 transformPosition(Float3R v, @Mutated Float3 dest) {
        return transform(v, dest);
    }


    /**
     * Transform the given position by this transform, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPosition(Float3R v, @Mutated Double3 dest) {
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
    public Float3 transformPosition(float vX, float vY, float vZ, @Mutated Float3 dest) {
        return transform(vX, vY, vZ, dest);
    }


    /**
     * Transform the given position by this transform, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
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
    public Float3 transformPositionInverse(Float3R p, @Mutated Float3 dest) {
        return transformInverse(p, dest);
    }


    /**
     * Transform the given position by the inverse of this transform (world to local), without
     * materializing {@code invert()} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformPositionInverse(Float3R p, @Mutated Double3 dest) {
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
    public Float3 transformPositionInverse(float pX, float pY, float pZ, @Mutated Float3 dest) {
        return transformInverse(pX, pY, pZ, dest);
    }


    /**
     * Transform the given position by the inverse of this transform (world to local), without
     * materializing {@code invert()} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3 transformPositionInverse(float pX, float pY, float pZ, @Mutated Double3 dest) {
        return transformInverse(pX, pY, pZ, dest);
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
    public Float3 transformVector(Float3R v, @Mutated Float3 dest) {
        return transformVector(v.x(), v.y(), v.z(), dest);
    }


    /**
     * Transform the given vector by the linear part of this transform, i.e. apply its scale and
     * rotation but not its translation and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformVector(Float3R v, @Mutated Double3 dest) {
        return transformVector(v.x(), v.y(), v.z(), dest);
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
    public Float3 transformVector(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t0 = sd[9] * vZ;
        float _t1 = sd[8] * vY;
        float _t2 = sd[7] * vX;
        float _t12 = 2.0f * Math.fma(sd[4], _t0, -(sd[5] * _t1));
        float _t13 = 2.0f * Math.fma(sd[3], _t1, -(sd[4] * _t2));
        float _t14 = 2.0f * Math.fma(sd[5], _t2, -(sd[3] * _t0));
        dd[0] = Math.fma(sd[7], vX, Math.fma(sd[6], _t12, Math.fma(sd[4], _t13, -(sd[5] * _t14))));
        dd[1] = Math.fma(sd[8], vY, Math.fma(sd[6], _t14, Math.fma(sd[5], _t12, -(sd[3] * _t13))));
        dd[2] = Math.fma(sd[9], vZ, Math.fma(sd[6], _t13, Math.fma(sd[3], _t14, -(sd[4] * _t12))));
        return dest;
    }


    /**
     * Transform the given vector by the linear part of this transform, i.e. apply its scale and
     * rotation but not its translation and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformVector(float vX, float vY, float vZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t0 = sd[9] * vZ;
        float _t1 = sd[8] * vY;
        float _t2 = sd[7] * vX;
        float _t12 = 2.0f * Math.fma(sd[4], _t0, -(sd[5] * _t1));
        float _t13 = 2.0f * Math.fma(sd[3], _t1, -(sd[4] * _t2));
        float _t14 = 2.0f * Math.fma(sd[5], _t2, -(sd[3] * _t0));
        dd[0] = Math.fma(sd[7], vX, Math.fma(sd[6], _t12, Math.fma(sd[4], _t13, -(sd[5] * _t14))));
        dd[1] = Math.fma(sd[8], vY, Math.fma(sd[6], _t14, Math.fma(sd[5], _t12, -(sd[3] * _t13))));
        dd[2] = Math.fma(sd[9], vZ, Math.fma(sd[6], _t13, Math.fma(sd[3], _t14, -(sd[4] * _t12))));
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
    public Float3 transformVectorInverse(Float3R v, @Mutated Float3 dest) {
        return transformVectorInverse(v.x(), v.y(), v.z(), dest);
    }


    /**
     * Transform the given vector by the inverse of this transform's linear part (world to local),
     * i.e. undo its rotation and scale but not its translation, without materializing
     * {@code invert()} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    public Double3 transformVectorInverse(Float3R v, @Mutated Double3 dest) {
        return transformVectorInverse(v.x(), v.y(), v.z(), dest);
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
    public Float3 transformVectorInverse(float vX, float vY, float vZ, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[3], vZ, -(sd[5] * vX));
        float _t10 = 2.0f * Math.fma(sd[4], vX, -(sd[3] * vY));
        float _t11 = 2.0f * Math.fma(sd[5], vY, -(sd[4] * vZ));
        dd[0] = Math.fma(sd[5], _t9, Math.fma(-sd[4], _t10, Math.fma(sd[6], _t11, vX))) / sd[7];
        dd[1] = Math.fma(sd[3], _t10, Math.fma(-sd[5], _t11, Math.fma(sd[6], _t9, vY))) / sd[8];
        dd[2] = Math.fma(sd[4], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, vZ))) / sd[9];
        return dest;
    }


    /**
     * Transform the given vector by the inverse of this transform's linear part (world to local),
     * i.e. undo its rotation and scale but not its translation, without materializing
     * {@code invert()} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double3 transformVectorInverse(float vX, float vY, float vZ, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _t9 = 2.0f * Math.fma(sd[3], vZ, -(sd[5] * vX));
        float _t10 = 2.0f * Math.fma(sd[4], vX, -(sd[3] * vY));
        float _t11 = 2.0f * Math.fma(sd[5], vY, -(sd[4] * vZ));
        dd[0] = Math.fma(sd[5], _t9, Math.fma(-sd[4], _t10, Math.fma(sd[6], _t11, vX))) / sd[7];
        dd[1] = Math.fma(sd[3], _t10, Math.fma(-sd[5], _t11, Math.fma(sd[6], _t9, vY))) / sd[8];
        dd[2] = Math.fma(sd[4], _t11, Math.fma(-sd[3], _t9, Math.fma(sd[6], _t10, vZ))) / sd[9];
        return dest;
    }

    public float tX() { return data[0]; }
    public float tY() { return data[1]; }
    public float tZ() { return data[2]; }
    public float rX() { return data[3]; }
    public float rY() { return data[4]; }
    public float rZ() { return data[5]; }
    public float rW() { return data[6]; }
    public float sX() { return data[7]; }
    public float sY() { return data[8]; }
    public float sZ() { return data[9]; }

    @Override public String toString() {
        return "FloatTransform(" + tX() + ", " + tY() + ", " + tZ() + ", " + rX() + ", " + rY() + ", " + rZ() + ", " + rW() + ", " + sX() + ", " + sY() + ", " + sZ() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatTransformImpl)) return false;
        FloatTransformImpl o = (FloatTransformImpl) obj;
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
            && Float.isFinite(data[6])
            && Float.isFinite(data[7])
            && Float.isFinite(data[8])
            && Float.isFinite(data[9]);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(data[0])
            || Float.isNaN(data[1])
            || Float.isNaN(data[2])
            || Float.isNaN(data[3])
            || Float.isNaN(data[4])
            || Float.isNaN(data[5])
            || Float.isNaN(data[6])
            || Float.isNaN(data[7])
            || Float.isNaN(data[8])
            || Float.isNaN(data[9]);
    }

    @Override public boolean equalsEpsilon(FloatTransformR other, float epsilon) {
        return Math.abs(data[0] - other.tX()) <= epsilon
            && Math.abs(data[1] - other.tY()) <= epsilon
            && Math.abs(data[2] - other.tZ()) <= epsilon
            && Math.abs(data[3] - other.rX()) <= epsilon
            && Math.abs(data[4] - other.rY()) <= epsilon
            && Math.abs(data[5] - other.rZ()) <= epsilon
            && Math.abs(data[6] - other.rW()) <= epsilon
            && Math.abs(data[7] - other.sX()) <= epsilon
            && Math.abs(data[8] - other.sY()) <= epsilon
            && Math.abs(data[9] - other.sZ()) <= epsilon;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset + 0] = this.data[0];
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
    public @Mutated FloatTransform load(float[] src, int offset) {
        this.data[0] = src[offset + 0];
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
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public FloatTransform loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatTransform loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public FloatTransform storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public FloatTransform loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    public FloatTransform load(long offset, MemorySegment src) {
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
        dest[offset + 7] = this.data[7];
        dest[offset + 8] = this.data[8];
        dest[offset + 9] = this.data[9];
        return dest;
    }
    public @Mutated FloatTransform load(double[] src, int offset) {
        this.data[0] = (float) src[offset + 0];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        this.data[4] = (float) src[offset + 4];
        this.data[5] = (float) src[offset + 5];
        this.data[6] = (float) src[offset + 6];
        this.data[7] = (float) src[offset + 7];
        this.data[8] = (float) src[offset + 8];
        this.data[9] = (float) src[offset + 9];
        return this;
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public FloatTransform loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }
    public FloatTransform loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public FloatTransform storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public FloatTransform loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }
    public MemorySegment storeDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }
    public FloatTransform loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(this, offset, src);
    }

    private static final VectorSpecies<Float> COL_SPECIES = FloatVector.SPECIES_128;
    private static final FloatVector VEC_1 = FloatVector.fromArray(COL_SPECIES, new float[]{0.0f, 0.0f, 0.0f, 1.0f}, 0);
    private static final FloatVector VEC_2 = FloatVector.fromArray(COL_SPECIES, new float[]{0.0f, 0.0f, 0.0f, 0.0f}, 0);
    private static final float[] DATA_0 = new float[] {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f};

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
