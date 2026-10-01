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
 * Generated implementation of {@link FloatTransform} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatTransformImpl implements FloatTransform {

    public float tX;
    public float tY;
    public float tZ;
    public float rX;
    public float rY;
    public float rZ;
    public float rW;
    public float sX;
    public float sY;
    public float sZ;

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
        rW = 1;
        sX = 1;
        sY = 1;
        sZ = 1;
    }

    public FloatTransformImpl(float tX, float tY, float tZ, float rX, float rY, float rZ, float rW, float sX, float sY, float sZ) {
        this.tX = tX;
        this.tY = tY;
        this.tZ = tZ;
        this.rX = rX;
        this.rY = rY;
        this.rZ = rZ;
        this.rW = rW;
        this.sX = sX;
        this.sY = sY;
        this.sZ = sZ;
    }

    public FloatTransformImpl(FloatTransformR src) {
        this.tX = src.tX();
        this.tY = src.tY();
        this.tZ = src.tZ();
        this.rX = src.rX();
        this.rY = src.rY();
        this.rZ = src.rZ();
        this.rW = src.rW();
        this.sX = src.sX();
        this.sY = src.sY();
        this.sZ = src.sZ();
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
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
    @Mutated public FloatTransform makeFromAxisAngle(float axisX, float axisY, float axisZ, float angle, float translationX, float translationY, float translationZ) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.tX = translationX;
        this.tY = translationY;
        this.tZ = translationZ;
        this.rX = axisX * _t1;
        this.rY = axisY * _t1;
        this.rZ = axisZ * _t1;
        this.rW = Math.cosFromSin(_t1, _t0);
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
    @Mutated public FloatTransform makeTranslationRotation(float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW) {
        this.tX = translationX;
        this.tY = translationY;
        this.tZ = translationZ;
        this.rX = rotationX;
        this.rY = rotationY;
        this.rZ = rotationZ;
        this.rW = rotationW;
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
        float translationY = translation.y();
        float translationZ = translation.z();
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        float scaleX = scale.x();
        float scaleY = scale.y();
        float scaleZ = scale.z();
        this.tX = translation.x();
        this.tY = translationY;
        this.tZ = translationZ;
        this.rX = rotationX;
        this.rY = rotationY;
        this.rZ = rotationZ;
        this.rW = rotationW;
        this.sX = scaleX;
        this.sY = scaleY;
        this.sZ = scaleZ;
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
    @Mutated public FloatTransform makeTranslationRotationScale(float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        this.tX = translationX;
        this.tY = translationY;
        this.tZ = translationZ;
        this.rX = rotationX;
        this.rY = rotationY;
        this.rZ = rotationZ;
        this.rW = rotationW;
        this.sX = scaleX;
        this.sY = scaleY;
        this.sZ = scaleZ;
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
        float vTY = v.tY();
        float vTZ = v.tZ();
        float vRX = v.rX();
        float vRY = v.rY();
        float vRZ = v.rZ();
        float vRW = v.rW();
        float vSX = v.sX();
        float vSY = v.sY();
        float vSZ = v.sZ();
        this.tX = v.tX();
        this.tY = vTY;
        this.tZ = vTZ;
        this.rX = vRX;
        this.rY = vRY;
        this.rZ = vRZ;
        this.rW = vRW;
        this.sX = vSX;
        this.sY = vSY;
        this.sZ = vSZ;
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
    @Mutated public FloatTransform set(float vTX, float vTY, float vTZ, float vRX, float vRY, float vRZ, float vRW, float vSX, float vSY, float vSZ) {
        this.tX = vTX;
        this.tY = vTY;
        this.tZ = vTZ;
        this.rX = vRX;
        this.rY = vRY;
        this.rZ = vRZ;
        this.rW = vRW;
        this.sX = vSX;
        this.sY = vSY;
        this.sZ = vSZ;
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
        float rX = r.x();
        float rY = r.y();
        float rZ = r.z();
        float rW = r.w();
        FloatTransformImpl d = (FloatTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = rX;
        d.rY = rY;
        d.rZ = rZ;
        d.rW = rW;
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        float rX = r.x();
        float rY = r.y();
        float rZ = r.z();
        float rW = r.w();
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = rX;
        d.rY = rY;
        d.rZ = rZ;
        d.rW = rW;
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        FloatTransformImpl d = (FloatTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = rX;
        d.rY = rY;
        d.rZ = rZ;
        d.rW = rW;
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = rX;
        d.rY = rY;
        d.rZ = rZ;
        d.rW = rW;
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        float sX = s.x();
        float sY = s.y();
        float sZ = s.z();
        FloatTransformImpl d = (FloatTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = sX;
        d.sY = sY;
        d.sZ = sZ;
        return d;
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
        float sX = s.x();
        float sY = s.y();
        float sZ = s.z();
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = sX;
        d.sY = sY;
        d.sZ = sZ;
        return d;
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
        FloatTransformImpl d = (FloatTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = sX;
        d.sY = sY;
        d.sZ = sZ;
        return d;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = sX;
        d.sY = sY;
        d.sZ = sZ;
        return d;
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
        FloatTransformImpl d = (FloatTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = uniform;
        d.sY = uniform;
        d.sZ = uniform;
        return d;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = uniform;
        d.sY = uniform;
        d.sZ = uniform;
        return d;
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
        float tY = t.y();
        float tZ = t.z();
        FloatTransformImpl d = (FloatTransformImpl) dest;
        d.tX = t.x();
        d.tY = tY;
        d.tZ = tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        float tY = t.y();
        float tZ = t.z();
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = t.x();
        d.tY = tY;
        d.tZ = tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        FloatTransformImpl d = (FloatTransformImpl) dest;
        d.tX = tX;
        d.tY = tY;
        d.tZ = tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = tX;
        d.tY = tY;
        d.tZ = tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        if (Math.useFma()) {
            this.tX = 2.0f * (java.lang.Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + java.lang.Math.fma(dqRW, dqDX, -(dqRX * dqDW)));
            this.tY = 2.0f * (java.lang.Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + java.lang.Math.fma(dqRW, dqDY, -(dqRY * dqDW)));
            this.tZ = 2.0f * (java.lang.Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + java.lang.Math.fma(dqRW, dqDZ, -(dqRZ * dqDW)));
            this.rX = dqRX;
            this.rY = dqRY;
            this.rZ = dqRZ;
            this.rW = dqRW;
            this.sX = 1.0f;
            this.sY = 1.0f;
            this.sZ = 1.0f;
            return this;
        } else {
            this.tX = 2.0f * (((dqRY) * (dqDZ) - (dqRZ * dqDY)) + ((dqRW) * (dqDX) - (dqRX * dqDW)));
            this.tY = 2.0f * (((dqRZ) * (dqDX) - (dqRX * dqDZ)) + ((dqRW) * (dqDY) - (dqRY * dqDW)));
            this.tZ = 2.0f * (((dqRX) * (dqDY) - (dqRY * dqDX)) + ((dqRW) * (dqDZ) - (dqRZ * dqDW)));
            this.rX = dqRX;
            this.rY = dqRY;
            this.rZ = dqRZ;
            this.rW = dqRW;
            this.sX = 1.0f;
            this.sY = 1.0f;
            this.sZ = 1.0f;
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
    @Mutated public FloatTransform makeFromMatrix(Float3x3R m) {
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform makeFromMatrix_fma(Float3x3R m) {
        float _r0 = m.m11();
        float _r1 = m.m22();
        float _r2 = m.m21();
        float _r3 = m.m01();
        float _r4 = m.m02();
        float _r5 = m.m12();
        float _r6 = m.m20();
        float _r7 = m.m00();
        float _r8 = m.m10();
        float _t12 = java.lang.Math.fma(_r2, _r2, java.lang.Math.fma(_r3, _r3, _r0 * _r0));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t13 = java.lang.Math.fma(_r1, _r1, java.lang.Math.fma(_r4, _r4, _r5 * _r5));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t14 = java.lang.Math.fma(_r6, _r6, java.lang.Math.fma(_r7, _r7, _r8 * _r8));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        float _t24 = _r2 * _t15;
        return makeFromMatrix_s4398bb65_1_fma(this, _r0, _r1, _r2, _r3, _r4, _t12, _t13, -_r0, -_r1, _t15, _t16, _t18, _r8 * _t17, _r1 * _t16, _r5 * _t16, _r6 * _t17, _t24, _r0 * _t15, _r7 * _t17, java.lang.Math.fma(_r5, _t16, _t24));
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform makeFromMatrix_mulAdd(Float3x3R m) {
        float _r0 = m.m11();
        float _r1 = m.m22();
        float _r2 = m.m21();
        float _r3 = m.m01();
        float _r4 = m.m02();
        float _r5 = m.m12();
        float _r6 = m.m20();
        float _r7 = m.m00();
        float _r8 = m.m10();
        float _t12 = ((_r2) * (_r2) + (((_r3) * (_r3) + (_r0 * _r0))));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t13 = ((_r1) * (_r1) + (((_r4) * (_r4) + (_r5 * _r5))));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t14 = ((_r6) * (_r6) + (((_r7) * (_r7) + (_r8 * _r8))));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        float _t24 = _r2 * _t15;
        return makeFromMatrix_s4398bb65_1_mulAdd(this, _r0, _r1, _r2, _r3, _r4, _t12, _t13, -_r0, -_r1, _t15, _t16, _t18, _r8 * _t17, _r1 * _t16, _r5 * _t16, _r6 * _t17, _t24, _r0 * _t15, _r7 * _t17, ((_r5) * (_t16) + (_t24)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatTransform makeFromMatrix_s4398bb65_1_fma(FloatTransformImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _t12, float _t13, float _t0, float _t1, float _t15, float _t16, float _t18, float _t19, float _t20, float _t21, float _t22, float _t24, float _t25, float _t27, float _t32) {
        float _t47 = java.lang.Math.fma(-java.lang.Math.fma(_t19, _t20, -(_t21 * _t22)), _r3 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t19, _t24, -(_t25 * _t22)), _r4 * _t16, java.lang.Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
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
        float _t64 = java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_r1, _t16, _t52));
        float _t66 = java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_t1, _t16, _t53));
        float _t67 = java.lang.Math.fma(_r1, _t16, java.lang.Math.fma(_t0, _t15, _t53));
        return makeFromMatrix_s4398bb65_2(d, _t12, _t13, _t18, _t20, _t25, _t32, java.lang.Math.fma(_r2, _t15, -_t21), java.lang.Math.max(_t25, _t20), _t47, _t48, java.lang.Math.fma(_r3, _t15, _t49), java.lang.Math.fma(_r4, _t16, _t50), java.lang.Math.fma(_r4, _t16, -_t50), java.lang.Math.fma(-_r3, _t15, _t49), java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_r1, _t16, _t48)), _t64, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)), _t66, _t67, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatTransform makeFromMatrix_s4398bb65_1_mulAdd(FloatTransformImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _t12, float _t13, float _t0, float _t1, float _t15, float _t16, float _t18, float _t19, float _t20, float _t21, float _t22, float _t24, float _t25, float _t27, float _t32) {
        float _t47 = ((-((_t19) * (_t20) - (_t21 * _t22))) * (_r3 * _t15) + (((((_t19) * (_t24) - (_t25 * _t22))) * (_r4 * _t16) + (((_t25) * (_t20) - (_t21 * _t24)) * _t27))));
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
        float _t64 = ((_r0) * (_t15) + (((_r1) * (_t16) + (_t52))));
        float _t66 = ((_r0) * (_t15) + (((_t1) * (_t16) + (_t53))));
        float _t67 = ((_r1) * (_t16) + (((_t0) * (_t15) + (_t53))));
        return makeFromMatrix_s4398bb65_2(d, _t12, _t13, _t18, _t20, _t25, _t32, ((_r2) * (_t15) - (_t21)), java.lang.Math.max(_t25, _t20), _t47, _t48, ((_r3) * (_t15) + (_t49)), ((_r4) * (_t16) + (_t50)), ((_r4) * (_t16) - (_t50)), ((-_r3) * (_t15) + (_t49)), ((_r0) * (_t15) + (((_r1) * (_t16) + (_t48)))), _t64, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)), _t66, _t67, ((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatTransform makeFromMatrix_s4398bb65_2(FloatTransformImpl d, float _t12, float _t13, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t55, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0, float _t66, float _t67, float _t68, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t68));
        d.tX = 0.0f;
        d.tY = 0.0f;
        d.tZ = 0.0f;
        d.rX = _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56;
        d.rY = _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) java.lang.Math.sqrt(_t66) : _sp2 * _t32;
        d.rZ = _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) java.lang.Math.sqrt(_t67);
        d.rW = _t63 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58;
        d.sX = _t47 < 0.0f ? -_t18 : _t18;
        d.sY = (float) java.lang.Math.sqrt(_t12);
        d.sZ = (float) java.lang.Math.sqrt(_t13);
        return d;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatTransform makeFromMatrix_degenerate_fma(Float3x3R m) {
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
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t78, _t78, java.lang.Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t92, _t92, _t77 * _t77))));
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
                    _t169 = java.lang.Math.fma(_t35, _t105, -(_t36 * _t109));
                    _t171 = java.lang.Math.fma(_t37, _t109, -(_t35 * _t119));
                    _t174 = java.lang.Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t185 = _t173 - _t169;
        float _t186 = java.lang.Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
        float _t196 = java.lang.Math.fma(java.lang.Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, java.lang.Math.fma(java.lang.Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, java.lang.Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
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
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t211));
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
        this.rX = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        this.rY = _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) java.lang.Math.sqrt(_t212) : _sp2 * _t187;
        this.rZ = _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) java.lang.Math.sqrt(_t213);
        this.rW = _t209 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        this.sX = _t196 < 0.0f ? -_t56 : _t56;
        this.sY = _t27 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t27) / _t0;
        this.sZ = _t28 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t28) / _t1;
        return this;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatTransform makeFromMatrix_degenerate_mulAdd(Float3x3R m) {
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
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(((_t78) * (_t78) + (((_t90) * (_t90) + (_t75 * _t75))))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(((_t79) * (_t79) + (((_t91) * (_t91) + (_t76 * _t76))))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(((_t80) * (_t80) + (((_t92) * (_t92) + (_t77 * _t77))))));
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
                    _t169 = ((_t35) * (_t105) - (_t36 * _t109));
                    _t171 = ((_t37) * (_t109) - (_t35 * _t119));
                    _t174 = ((_t36) * (_t119) - (_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t185 = _t173 - _t169;
        float _t186 = java.lang.Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
        float _t196 = ((((_t168) * (_t169) - (_t170 * _t171))) * (_t172) + (((((_t173) * (_t171) - (_t168 * _t174))) * (_t175) + (((_t170) * (_t174) - (_t173 * _t169)) * _t176))));
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
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t211));
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
        this.rX = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        this.rY = _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) java.lang.Math.sqrt(_t212) : _sp2 * _t187;
        this.rZ = _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) java.lang.Math.sqrt(_t213);
        this.rW = _t209 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        this.sX = _t196 < 0.0f ? -_t56 : _t56;
        this.sY = _t27 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t27) / _t0;
        this.sZ = _t28 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t28) / _t1;
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
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform makeFromMatrix_fma(Float3x4R m) {
        float _r0 = m.m11();
        float _r1 = m.m22();
        float _r2 = m.m21();
        float _r3 = m.m01();
        float _r4 = m.m02();
        float _r5 = m.m12();
        float _r6 = m.m20();
        float _r7 = m.m00();
        float _r8 = m.m10();
        float _t12 = java.lang.Math.fma(_r2, _r2, java.lang.Math.fma(_r3, _r3, _r0 * _r0));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t13 = java.lang.Math.fma(_r1, _r1, java.lang.Math.fma(_r4, _r4, _r5 * _r5));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t14 = java.lang.Math.fma(_r6, _r6, java.lang.Math.fma(_r7, _r7, _r8 * _r8));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _r10 = m.m13();
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        return makeFromMatrix_s7b6de20e_3_fma(m, this, _r0, _r1, _r2, _r3, _r4, _r5, _r7, _t12, _t13, _r10, m.m23(), -_r0, -_r1, _t15, _t16, _t18, _t17, _r8 * _t17, _r1 * _t16, _r5 * _t16, _r6 * _t17, _r2 * _t15);
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform makeFromMatrix_mulAdd(Float3x4R m) {
        float _r0 = m.m11();
        float _r1 = m.m22();
        float _r2 = m.m21();
        float _r3 = m.m01();
        float _r4 = m.m02();
        float _r5 = m.m12();
        float _r6 = m.m20();
        float _r7 = m.m00();
        float _r8 = m.m10();
        float _t12 = ((_r2) * (_r2) + (((_r3) * (_r3) + (_r0 * _r0))));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t13 = ((_r1) * (_r1) + (((_r4) * (_r4) + (_r5 * _r5))));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t14 = ((_r6) * (_r6) + (((_r7) * (_r7) + (_r8 * _r8))));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _r10 = m.m13();
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        return makeFromMatrix_s7b6de20e_3_mulAdd(m, this, _r0, _r1, _r2, _r3, _r4, _r5, _r7, _t12, _t13, _r10, m.m23(), -_r0, -_r1, _t15, _t16, _t18, _t17, _r8 * _t17, _r1 * _t16, _r5 * _t16, _r6 * _t17, _r2 * _t15);
    }

    /**
     * Part 1 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private float makeFromMatrix_s7b6de20e_1_fma(float _r3, float _r4, float _t15, float _t16, float _t19, float _t20, float _t21, float _t22, float _t24, float _t25, float _t27) {
        return java.lang.Math.fma(-java.lang.Math.fma(_t19, _t20, -(_t21 * _t22)), _r3 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t19, _t24, -(_t25 * _t22)), _r4 * _t16, java.lang.Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
    }

    /**
     * Part 1 of {@code makeFromMatrix}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code makeFromMatrix}; reached only through it.
     */
    private float makeFromMatrix_s7b6de20e_1_mulAdd(float _r3, float _r4, float _t15, float _t16, float _t19, float _t20, float _t21, float _t22, float _t24, float _t25, float _t27) {
        return ((-((_t19) * (_t20) - (_t21 * _t22))) * (_r3 * _t15) + (((((_t19) * (_t24) - (_t25 * _t22))) * (_r4 * _t16) + (((_t25) * (_t20) - (_t21 * _t24)) * _t27))));
    }

    /** Part 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private void makeFromMatrix_s7b6de20e_2(Float3x4R m, FloatTransformImpl d, float _r10, float _r11, float _t20, float _t25, float _t32, float _t36, float _t37, float _t48, float _t55, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0, float _t66, float _t67, float _t68) {
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t68));
        d.tX = m.m03();
        d.tY = _r10;
        d.tZ = _r11;
        d.rX = _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56;
        d.rY = _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) java.lang.Math.sqrt(_t66) : _sp2 * _t32;
        d.rZ = _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) java.lang.Math.sqrt(_t67);
        d.rW = _t63 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58;
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatTransform makeFromMatrix_s7b6de20e_3_fma(Float3x4R m, FloatTransformImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r7, float _t12, float _t13, float _r10, float _r11, float _t0, float _t1, float _t15, float _t16, float _t18, float _t17, float _t19, float _t20, float _t21, float _t22, float _t24) {
        float _t25 = _r0 * _t15;
        float _t27 = _r7 * _t17;
        float _t47 = makeFromMatrix_s7b6de20e_1_fma(_r3, _r4, _t15, _t16, _t19, _t20, _t21, _t22, _t24, _t25, _t27);
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
        float _t64 = java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_r1, _t16, _t52));
        makeFromMatrix_s7b6de20e_2(m, d, _r10, _r11, _t20, _t25, java.lang.Math.fma(_r5, _t16, _t24), java.lang.Math.fma(_r2, _t15, -_t21), java.lang.Math.max(_t25, _t20), _t48, java.lang.Math.fma(_r3, _t15, _t49), java.lang.Math.fma(_r4, _t16, _t50), java.lang.Math.fma(_r4, _t16, -_t50), java.lang.Math.fma(-_r3, _t15, _t49), java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_r1, _t16, _t48)), _t64, (0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64))), java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_t1, _t16, _t53)), java.lang.Math.fma(_r1, _t16, java.lang.Math.fma(_t0, _t15, _t53)), java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)));
        d.sX = _t47 < 0.0f ? -_t18 : _t18;
        d.sY = (float) java.lang.Math.sqrt(_t12);
        d.sZ = (float) java.lang.Math.sqrt(_t13);
        return d;
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatTransform makeFromMatrix_s7b6de20e_3_mulAdd(Float3x4R m, FloatTransformImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r7, float _t12, float _t13, float _r10, float _r11, float _t0, float _t1, float _t15, float _t16, float _t18, float _t17, float _t19, float _t20, float _t21, float _t22, float _t24) {
        float _t25 = _r0 * _t15;
        float _t27 = _r7 * _t17;
        float _t47 = makeFromMatrix_s7b6de20e_1_mulAdd(_r3, _r4, _t15, _t16, _t19, _t20, _t21, _t22, _t24, _t25, _t27);
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
        float _t64 = ((_r0) * (_t15) + (((_r1) * (_t16) + (_t52))));
        makeFromMatrix_s7b6de20e_2(m, d, _r10, _r11, _t20, _t25, ((_r5) * (_t16) + (_t24)), ((_r2) * (_t15) - (_t21)), java.lang.Math.max(_t25, _t20), _t48, ((_r3) * (_t15) + (_t49)), ((_r4) * (_t16) + (_t50)), ((_r4) * (_t16) - (_t50)), ((-_r3) * (_t15) + (_t49)), ((_r0) * (_t15) + (((_r1) * (_t16) + (_t48)))), _t64, (0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64))), ((_r0) * (_t15) + (((_t1) * (_t16) + (_t53)))), ((_r1) * (_t16) + (((_t0) * (_t15) + (_t53)))), ((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))));
        d.sX = _t47 < 0.0f ? -_t18 : _t18;
        d.sY = (float) java.lang.Math.sqrt(_t12);
        d.sZ = (float) java.lang.Math.sqrt(_t13);
        return d;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatTransform makeFromMatrix_degenerate_fma(Float3x4R m) {
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
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t78, _t78, java.lang.Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t92, _t92, _t77 * _t77))));
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
                    _t169 = java.lang.Math.fma(_t35, _t105, -(_t36 * _t109));
                    _t171 = java.lang.Math.fma(_t37, _t109, -(_t35 * _t119));
                    _t174 = java.lang.Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t185 = _t173 - _t169;
        float _t186 = java.lang.Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
        float _t196 = java.lang.Math.fma(java.lang.Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, java.lang.Math.fma(java.lang.Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, java.lang.Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
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
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t211));
        this.tX = m.m03();
        this.tY = m.m13();
        this.tZ = m.m23();
        this.rX = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        this.rY = _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) java.lang.Math.sqrt(_t212) : _sp2 * _t187;
        this.rZ = _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) java.lang.Math.sqrt(_t213);
        this.rW = _t209 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        this.sX = _t196 < 0.0f ? -_t56 : _t56;
        this.sY = _t27 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t27) / _t0;
        this.sZ = _t28 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t28) / _t1;
        return this;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatTransform makeFromMatrix_degenerate_mulAdd(Float3x4R m) {
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
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(((_t78) * (_t78) + (((_t90) * (_t90) + (_t75 * _t75))))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(((_t79) * (_t79) + (((_t91) * (_t91) + (_t76 * _t76))))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(((_t80) * (_t80) + (((_t92) * (_t92) + (_t77 * _t77))))));
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
                    _t169 = ((_t35) * (_t105) - (_t36 * _t109));
                    _t171 = ((_t37) * (_t109) - (_t35 * _t119));
                    _t174 = ((_t36) * (_t119) - (_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t185 = _t173 - _t169;
        float _t186 = java.lang.Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
        float _t196 = ((((_t168) * (_t169) - (_t170 * _t171))) * (_t172) + (((((_t173) * (_t171) - (_t168 * _t174))) * (_t175) + (((_t170) * (_t174) - (_t173 * _t169)) * _t176))));
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
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t211));
        this.tX = m.m03();
        this.tY = m.m13();
        this.tZ = m.m23();
        this.rX = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        this.rY = _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) java.lang.Math.sqrt(_t212) : _sp2 * _t187;
        this.rZ = _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) java.lang.Math.sqrt(_t213);
        this.rW = _t209 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        this.sX = _t196 < 0.0f ? -_t56 : _t56;
        this.sY = _t27 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t27) / _t0;
        this.sZ = _t28 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t28) / _t1;
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
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform makeFromMatrix_fma(Float4x4R m) {
        float _r0 = m.m11();
        float _r1 = m.m22();
        float _r2 = m.m21();
        float _r3 = m.m01();
        float _r4 = m.m02();
        float _r5 = m.m12();
        float _r6 = m.m20();
        float _r7 = m.m00();
        float _r8 = m.m10();
        float _t12 = java.lang.Math.fma(_r2, _r2, java.lang.Math.fma(_r3, _r3, _r0 * _r0));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t13 = java.lang.Math.fma(_r1, _r1, java.lang.Math.fma(_r4, _r4, _r5 * _r5));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t14 = java.lang.Math.fma(_r6, _r6, java.lang.Math.fma(_r7, _r7, _r8 * _r8));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _r10 = m.m13();
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        return makeFromMatrix_s40927f3d_3_fma(m, this, _r0, _r1, _r2, _r3, _r4, _r5, _r7, _t12, _t13, _r10, m.m23(), -_r0, -_r1, _t15, _t16, _t18, _t17, _r8 * _t17, _r1 * _t16, _r5 * _t16, _r6 * _t17, _r2 * _t15);
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform makeFromMatrix_mulAdd(Float4x4R m) {
        float _r0 = m.m11();
        float _r1 = m.m22();
        float _r2 = m.m21();
        float _r3 = m.m01();
        float _r4 = m.m02();
        float _r5 = m.m12();
        float _r6 = m.m20();
        float _r7 = m.m00();
        float _r8 = m.m10();
        float _t12 = ((_r2) * (_r2) + (((_r3) * (_r3) + (_r0 * _r0))));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t13 = ((_r1) * (_r1) + (((_r4) * (_r4) + (_r5 * _r5))));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t14 = ((_r6) * (_r6) + (((_r7) * (_r7) + (_r8 * _r8))));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _r10 = m.m13();
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        return makeFromMatrix_s40927f3d_3_mulAdd(m, this, _r0, _r1, _r2, _r3, _r4, _r5, _r7, _t12, _t13, _r10, m.m23(), -_r0, -_r1, _t15, _t16, _t18, _t17, _r8 * _t17, _r1 * _t16, _r5 * _t16, _r6 * _t17, _r2 * _t15);
    }

    /** Part 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private void makeFromMatrix_s40927f3d_2(Float4x4R m, FloatTransformImpl d, float _r10, float _r11, float _t20, float _t25, float _t32, float _t36, float _t37, float _t48, float _t55, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0, float _t66, float _t67, float _t68) {
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t68));
        d.tX = m.m03();
        d.tY = _r10;
        d.tZ = _r11;
        d.rX = _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56;
        d.rY = _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) java.lang.Math.sqrt(_t66) : _sp2 * _t32;
        d.rZ = _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) java.lang.Math.sqrt(_t67);
        d.rW = _t63 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58;
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatTransform makeFromMatrix_s40927f3d_3_fma(Float4x4R m, FloatTransformImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r7, float _t12, float _t13, float _r10, float _r11, float _t0, float _t1, float _t15, float _t16, float _t18, float _t17, float _t19, float _t20, float _t21, float _t22, float _t24) {
        float _t25 = _r0 * _t15;
        float _t27 = _r7 * _t17;
        float _t47 = makeFromMatrix_s7b6de20e_1_fma(_r3, _r4, _t15, _t16, _t19, _t20, _t21, _t22, _t24, _t25, _t27);
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
        float _t64 = java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_r1, _t16, _t52));
        makeFromMatrix_s40927f3d_2(m, d, _r10, _r11, _t20, _t25, java.lang.Math.fma(_r5, _t16, _t24), java.lang.Math.fma(_r2, _t15, -_t21), java.lang.Math.max(_t25, _t20), _t48, java.lang.Math.fma(_r3, _t15, _t49), java.lang.Math.fma(_r4, _t16, _t50), java.lang.Math.fma(_r4, _t16, -_t50), java.lang.Math.fma(-_r3, _t15, _t49), java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_r1, _t16, _t48)), _t64, (0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64))), java.lang.Math.fma(_r0, _t15, java.lang.Math.fma(_t1, _t16, _t53)), java.lang.Math.fma(_r1, _t16, java.lang.Math.fma(_t0, _t15, _t53)), java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)));
        d.sX = _t47 < 0.0f ? -_t18 : _t18;
        d.sY = (float) java.lang.Math.sqrt(_t12);
        d.sZ = (float) java.lang.Math.sqrt(_t13);
        return d;
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private FloatTransform makeFromMatrix_s40927f3d_3_mulAdd(Float4x4R m, FloatTransformImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r7, float _t12, float _t13, float _r10, float _r11, float _t0, float _t1, float _t15, float _t16, float _t18, float _t17, float _t19, float _t20, float _t21, float _t22, float _t24) {
        float _t25 = _r0 * _t15;
        float _t27 = _r7 * _t17;
        float _t47 = makeFromMatrix_s7b6de20e_1_mulAdd(_r3, _r4, _t15, _t16, _t19, _t20, _t21, _t22, _t24, _t25, _t27);
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
        float _t64 = ((_r0) * (_t15) + (((_r1) * (_t16) + (_t52))));
        makeFromMatrix_s40927f3d_2(m, d, _r10, _r11, _t20, _t25, ((_r5) * (_t16) + (_t24)), ((_r2) * (_t15) - (_t21)), java.lang.Math.max(_t25, _t20), _t48, ((_r3) * (_t15) + (_t49)), ((_r4) * (_t16) + (_t50)), ((_r4) * (_t16) - (_t50)), ((-_r3) * (_t15) + (_t49)), ((_r0) * (_t15) + (((_r1) * (_t16) + (_t48)))), _t64, (0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64))), ((_r0) * (_t15) + (((_t1) * (_t16) + (_t53)))), ((_r1) * (_t16) + (((_t0) * (_t15) + (_t53)))), ((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))));
        d.sX = _t47 < 0.0f ? -_t18 : _t18;
        d.sY = (float) java.lang.Math.sqrt(_t12);
        d.sZ = (float) java.lang.Math.sqrt(_t13);
        return d;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatTransform makeFromMatrix_degenerate_fma(Float4x4R m) {
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
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t78, _t78, java.lang.Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t92, _t92, _t77 * _t77))));
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
                    _t169 = java.lang.Math.fma(_t35, _t105, -(_t36 * _t109));
                    _t171 = java.lang.Math.fma(_t37, _t109, -(_t35 * _t119));
                    _t174 = java.lang.Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t185 = _t173 - _t169;
        float _t186 = java.lang.Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
        float _t196 = java.lang.Math.fma(java.lang.Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, java.lang.Math.fma(java.lang.Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, java.lang.Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
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
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t211));
        this.tX = m.m03();
        this.tY = m.m13();
        this.tZ = m.m23();
        this.rX = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        this.rY = _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) java.lang.Math.sqrt(_t212) : _sp2 * _t187;
        this.rZ = _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) java.lang.Math.sqrt(_t213);
        this.rW = _t209 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        this.sX = _t196 < 0.0f ? -_t56 : _t56;
        this.sY = _t27 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t27) / _t0;
        this.sZ = _t28 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t28) / _t1;
        return this;
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    @Mutated private FloatTransform makeFromMatrix_degenerate_mulAdd(Float4x4R m) {
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
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(((_t78) * (_t78) + (((_t90) * (_t90) + (_t75 * _t75))))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(((_t79) * (_t79) + (((_t91) * (_t91) + (_t76 * _t76))))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(((_t80) * (_t80) + (((_t92) * (_t92) + (_t77 * _t77))))));
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
                    _t169 = ((_t35) * (_t105) - (_t36 * _t109));
                    _t171 = ((_t37) * (_t109) - (_t35 * _t119));
                    _t174 = ((_t36) * (_t119) - (_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t185 = _t173 - _t169;
        float _t186 = java.lang.Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
        float _t196 = ((((_t168) * (_t169) - (_t170 * _t171))) * (_t172) + (((((_t173) * (_t171) - (_t168 * _t174))) * (_t175) + (((_t170) * (_t174) - (_t173 * _t169)) * _t176))));
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
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t211));
        this.tX = m.m03();
        this.tY = m.m13();
        this.tZ = m.m23();
        this.rX = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        this.rY = _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) java.lang.Math.sqrt(_t212) : _sp2 * _t187;
        this.rZ = _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) java.lang.Math.sqrt(_t213);
        this.rW = _t209 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        this.sX = _t196 < 0.0f ? -_t56 : _t56;
        this.sY = _t27 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t27) / _t0;
        this.sZ = _t28 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t28) / _t1;
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
        float rTY = r.tY();
        float rTZ = r.tZ();
        float rRX = r.rX();
        float rRY = r.rY();
        float rRZ = r.rZ();
        float rRW = r.rW();
        this.tX = r.tX();
        this.tY = rTY;
        this.tZ = rTZ;
        this.rX = rRX;
        this.rY = rRY;
        this.rZ = rRZ;
        this.rW = rRW;
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
    @Mutated public FloatTransform makeFromRigid(float rTX, float rTY, float rTZ, float rRX, float rRY, float rRZ, float rRW) {
        this.tX = rTX;
        this.tY = rTY;
        this.tZ = rTZ;
        this.rX = rRX;
        this.rY = rRY;
        this.rZ = rRZ;
        this.rW = rRW;
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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

    /** Private column 0 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s5af251ec_c0_fma(Float4x4Impl _dst, float _r5, float _t3, float _t0, float _r0, float _r6, float _t4, float _r3, float _t5) {
        _dst.m00 = java.lang.Math.fma(-java.lang.Math.fma(_r5, _r5, _t3), _t0, _r0);
        _dst.m10 = java.lang.Math.fma(_r6, _r5, _t4) * _t0;
        _dst.m20 = java.lang.Math.fma(_r6, _r3, -_t5) * _t0;
        _dst.m30 = 0.0f;
    }

    /** Private column 0 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s5af251ec_c0_mulAdd(Float4x4Impl _dst, float _r5, float _t3, float _t0, float _r0, float _r6, float _t4, float _r3, float _t5) {
        _dst.m00 = ((-((_r5) * (_r5) + (_t3))) * (_t0) + (_r0));
        _dst.m10 = ((_r6) * (_r5) + (_t4)) * _t0;
        _dst.m20 = ((_r6) * (_r3) - (_t5)) * _t0;
        _dst.m30 = 0.0f;
    }

    /** Private column 1 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s5af251ec_c1_fma(Float4x4Impl _dst, float _r6, float _r5, float _t4, float _t1, float _t3, float _r1, float _r4, float _r3) {
        _dst.m01 = java.lang.Math.fma(_r6, _r5, -_t4) * _t1;
        _dst.m11 = java.lang.Math.fma(-java.lang.Math.fma(_r6, _r6, _t3), _t1, _r1);
        _dst.m21 = java.lang.Math.fma(_r6, _r4, _r5 * _r3) * _t1;
        _dst.m31 = 0.0f;
    }

    /** Private column 1 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s5af251ec_c1_mulAdd(Float4x4Impl _dst, float _r6, float _r5, float _t4, float _t1, float _t3, float _r1, float _r4, float _r3) {
        _dst.m01 = ((_r6) * (_r5) - (_t4)) * _t1;
        _dst.m11 = ((-((_r6) * (_r6) + (_t3))) * (_t1) + (_r1));
        _dst.m21 = ((_r6) * (_r4) + (_r5 * _r3)) * _t1;
        _dst.m31 = 0.0f;
    }

    /** Private column 2 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s5af251ec_c2_fma(Float4x4Impl _dst, float _r6, float _r3, float _t5, float _t2, float _r5, float _r4, float _r2) {
        _dst.m02 = java.lang.Math.fma(_r6, _r3, _t5) * _t2;
        _dst.m12 = java.lang.Math.fma(_r5, _r3, -(_r6 * _r4)) * _t2;
        _dst.m22 = java.lang.Math.fma(-java.lang.Math.fma(_r6, _r6, _r5 * _r5), _t2, _r2);
        _dst.m32 = 0.0f;
    }

    /** Private column 2 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s5af251ec_c2_mulAdd(Float4x4Impl _dst, float _r6, float _r3, float _t5, float _t2, float _r5, float _r4, float _r2) {
        _dst.m02 = ((_r6) * (_r3) + (_t5)) * _t2;
        _dst.m12 = ((_r5) * (_r3) - (_r6 * _r4)) * _t2;
        _dst.m22 = ((-((_r6) * (_r6) + (_r5 * _r5))) * (_t2) + (_r2));
        _dst.m32 = 0.0f;
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
        Float4x4Impl d = (Float4x4Impl) dest;
        float _r0 = this.sX;
        float _r1 = this.sY;
        float _r2 = this.sZ;
        float _r3 = this.rZ;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rX;
        float _r7 = this.tX;
        float _r8 = this.tY;
        float _r9 = this.tZ;
        float _t3 = _r3 * _r3;
        float _t4 = _r3 * _r4;
        float _t5 = _r5 * _r4;
        if (Math.useFma()) toMatrix_s5af251ec_c0_fma(d, _r5, _t3, _r0 + _r0, _r0, _r6, _t4, _r3, _t5); else toMatrix_s5af251ec_c0_mulAdd(d, _r5, _t3, _r0 + _r0, _r0, _r6, _t4, _r3, _t5);
        if (Math.useFma()) toMatrix_s5af251ec_c1_fma(d, _r6, _r5, _t4, _r1 + _r1, _t3, _r1, _r4, _r3); else toMatrix_s5af251ec_c1_mulAdd(d, _r6, _r5, _t4, _r1 + _r1, _t3, _r1, _r4, _r3);
        if (Math.useFma()) toMatrix_s5af251ec_c2_fma(d, _r6, _r3, _t5, _r2 + _r2, _r5, _r4, _r2); else toMatrix_s5af251ec_c2_mulAdd(d, _r6, _r3, _t5, _r2 + _r2, _r5, _r4, _r2);
        d.m03 = _r7;
        d.m13 = _r8;
        d.m23 = _r9;
        d.m33 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /** Private column 0 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c0_fma(Double4x4Impl _dst, float _r5, float _t3, float _t0, float _r0, float _r6, float _t4, float _r3, float _t5) {
        _dst.m00 = java.lang.Math.fma(-java.lang.Math.fma(_r5, _r5, _t3), _t0, _r0);
        _dst.m10 = java.lang.Math.fma(_r6, _r5, _t4) * _t0;
        _dst.m20 = java.lang.Math.fma(_r6, _r3, -_t5) * _t0;
        _dst.m30 = 0.0f;
    }

    /** Private column 0 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c0_mulAdd(Double4x4Impl _dst, float _r5, float _t3, float _t0, float _r0, float _r6, float _t4, float _r3, float _t5) {
        _dst.m00 = ((-((_r5) * (_r5) + (_t3))) * (_t0) + (_r0));
        _dst.m10 = ((_r6) * (_r5) + (_t4)) * _t0;
        _dst.m20 = ((_r6) * (_r3) - (_t5)) * _t0;
        _dst.m30 = 0.0f;
    }

    /** Private column 1 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c1_fma(Double4x4Impl _dst, float _r6, float _r5, float _t4, float _t1, float _t3, float _r1, float _r4, float _r3) {
        _dst.m01 = java.lang.Math.fma(_r6, _r5, -_t4) * _t1;
        _dst.m11 = java.lang.Math.fma(-java.lang.Math.fma(_r6, _r6, _t3), _t1, _r1);
        _dst.m21 = java.lang.Math.fma(_r6, _r4, _r5 * _r3) * _t1;
        _dst.m31 = 0.0f;
    }

    /** Private column 1 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c1_mulAdd(Double4x4Impl _dst, float _r6, float _r5, float _t4, float _t1, float _t3, float _r1, float _r4, float _r3) {
        _dst.m01 = ((_r6) * (_r5) - (_t4)) * _t1;
        _dst.m11 = ((-((_r6) * (_r6) + (_t3))) * (_t1) + (_r1));
        _dst.m21 = ((_r6) * (_r4) + (_r5 * _r3)) * _t1;
        _dst.m31 = 0.0f;
    }

    /** Private column 2 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c2_fma(Double4x4Impl _dst, float _r6, float _r3, float _t5, float _t2, float _r5, float _r4, float _r2) {
        _dst.m02 = java.lang.Math.fma(_r6, _r3, _t5) * _t2;
        _dst.m12 = java.lang.Math.fma(_r5, _r3, -(_r6 * _r4)) * _t2;
        _dst.m22 = java.lang.Math.fma(-java.lang.Math.fma(_r6, _r6, _r5 * _r5), _t2, _r2);
        _dst.m32 = 0.0f;
    }

    /** Private column 2 of {@code toMatrix}: computes and stores it; reached only through it. */
    private void toMatrix_s20bb8ca5_c2_mulAdd(Double4x4Impl _dst, float _r6, float _r3, float _t5, float _t2, float _r5, float _r4, float _r2) {
        _dst.m02 = ((_r6) * (_r3) + (_t5)) * _t2;
        _dst.m12 = ((_r5) * (_r3) - (_r6 * _r4)) * _t2;
        _dst.m22 = ((-((_r6) * (_r6) + (_r5 * _r5))) * (_t2) + (_r2));
        _dst.m32 = 0.0f;
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
        Double4x4Impl d = (Double4x4Impl) dest;
        float _r0 = this.sX;
        float _r1 = this.sY;
        float _r2 = this.sZ;
        float _r3 = this.rZ;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rX;
        float _r7 = this.tX;
        float _r8 = this.tY;
        float _r9 = this.tZ;
        float _t3 = _r3 * _r3;
        float _t4 = _r3 * _r4;
        float _t5 = _r5 * _r4;
        if (Math.useFma()) toMatrix_s20bb8ca5_c0_fma(d, _r5, _t3, _r0 + _r0, _r0, _r6, _t4, _r3, _t5); else toMatrix_s20bb8ca5_c0_mulAdd(d, _r5, _t3, _r0 + _r0, _r0, _r6, _t4, _r3, _t5);
        if (Math.useFma()) toMatrix_s20bb8ca5_c1_fma(d, _r6, _r5, _t4, _r1 + _r1, _t3, _r1, _r4, _r3); else toMatrix_s20bb8ca5_c1_mulAdd(d, _r6, _r5, _t4, _r1 + _r1, _t3, _r1, _r4, _r3);
        if (Math.useFma()) toMatrix_s20bb8ca5_c2_fma(d, _r6, _r3, _t5, _r2 + _r2, _r5, _r4, _r2); else toMatrix_s20bb8ca5_c2_mulAdd(d, _r6, _r3, _t5, _r2 + _r2, _r5, _r4, _r2);
        d.m03 = _r7;
        d.m13 = _r8;
        d.m23 = _r9;
        d.m33 = 1.0f;
        d.properties = Joml.BIT_AFFINE;
        return d;
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
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = this.sX + this.sX;
        float _t1 = this.sY + this.sY;
        float _t2 = this.sZ + this.sZ;
        float _t3 = this.rZ * this.rZ;
        float _t4 = this.rZ * this.rW;
        float _t5 = this.rY * this.rW;
        d.m00 = Math.fma(-Math.fma(this.rY, this.rY, _t3), _t0, this.sX);
        d.m10 = Math.fma(this.rX, this.rY, _t4) * _t0;
        d.m20 = Math.fma(this.rX, this.rZ, -_t5) * _t0;
        d.m01 = Math.fma(this.rX, this.rY, -_t4) * _t1;
        d.m11 = Math.fma(-Math.fma(this.rX, this.rX, _t3), _t1, this.sY);
        d.m21 = Math.fma(this.rX, this.rW, this.rY * this.rZ) * _t1;
        d.m02 = Math.fma(this.rX, this.rZ, _t5) * _t2;
        d.m12 = Math.fma(this.rY, this.rZ, -(this.rX * this.rW)) * _t2;
        d.m22 = Math.fma(-Math.fma(this.rX, this.rX, this.rY * this.rY), _t2, this.sZ);
        d.properties = 0;
        return d;
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
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t0 = this.sX + this.sX;
        float _t1 = this.sY + this.sY;
        float _t2 = this.sZ + this.sZ;
        float _t3 = this.rZ * this.rZ;
        float _t4 = this.rZ * this.rW;
        float _t5 = this.rY * this.rW;
        d.m00 = Math.fma(-Math.fma(this.rY, this.rY, _t3), _t0, this.sX);
        d.m10 = Math.fma(this.rX, this.rY, _t4) * _t0;
        d.m20 = Math.fma(this.rX, this.rZ, -_t5) * _t0;
        d.m01 = Math.fma(this.rX, this.rY, -_t4) * _t1;
        d.m11 = Math.fma(-Math.fma(this.rX, this.rX, _t3), _t1, this.sY);
        d.m21 = Math.fma(this.rX, this.rW, this.rY * this.rZ) * _t1;
        d.m02 = Math.fma(this.rX, this.rZ, _t5) * _t2;
        d.m12 = Math.fma(this.rY, this.rZ, -(this.rX * this.rW)) * _t2;
        d.m22 = Math.fma(-Math.fma(this.rX, this.rX, this.rY * this.rY), _t2, this.sZ);
        d.properties = 0;
        return d;
    }

    /** Private column 0 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s7311250d_c0_fma(Float3x4Impl _dst, float _r5, float _t3, float _t0, float _r0, float _r6, float _t4, float _r3, float _t5) {
        _dst.m00 = java.lang.Math.fma(-java.lang.Math.fma(_r5, _r5, _t3), _t0, _r0);
        _dst.m10 = java.lang.Math.fma(_r6, _r5, _t4) * _t0;
        _dst.m20 = java.lang.Math.fma(_r6, _r3, -_t5) * _t0;
    }

    /** Private column 0 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s7311250d_c0_mulAdd(Float3x4Impl _dst, float _r5, float _t3, float _t0, float _r0, float _r6, float _t4, float _r3, float _t5) {
        _dst.m00 = ((-((_r5) * (_r5) + (_t3))) * (_t0) + (_r0));
        _dst.m10 = ((_r6) * (_r5) + (_t4)) * _t0;
        _dst.m20 = ((_r6) * (_r3) - (_t5)) * _t0;
    }

    /** Private column 1 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s7311250d_c1_fma(Float3x4Impl _dst, float _r6, float _r5, float _t4, float _t1, float _t3, float _r1, float _r4, float _r3) {
        _dst.m01 = java.lang.Math.fma(_r6, _r5, -_t4) * _t1;
        _dst.m11 = java.lang.Math.fma(-java.lang.Math.fma(_r6, _r6, _t3), _t1, _r1);
        _dst.m21 = java.lang.Math.fma(_r6, _r4, _r5 * _r3) * _t1;
    }

    /** Private column 1 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s7311250d_c1_mulAdd(Float3x4Impl _dst, float _r6, float _r5, float _t4, float _t1, float _t3, float _r1, float _r4, float _r3) {
        _dst.m01 = ((_r6) * (_r5) - (_t4)) * _t1;
        _dst.m11 = ((-((_r6) * (_r6) + (_t3))) * (_t1) + (_r1));
        _dst.m21 = ((_r6) * (_r4) + (_r5 * _r3)) * _t1;
    }

    /** Private column 2 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s7311250d_c2_fma(Float3x4Impl _dst, float _r6, float _r3, float _t5, float _t2, float _r5, float _r4, float _r2) {
        _dst.m02 = java.lang.Math.fma(_r6, _r3, _t5) * _t2;
        _dst.m12 = java.lang.Math.fma(_r5, _r3, -(_r6 * _r4)) * _t2;
        _dst.m22 = java.lang.Math.fma(-java.lang.Math.fma(_r6, _r6, _r5 * _r5), _t2, _r2);
    }

    /** Private column 2 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s7311250d_c2_mulAdd(Float3x4Impl _dst, float _r6, float _r3, float _t5, float _t2, float _r5, float _r4, float _r2) {
        _dst.m02 = ((_r6) * (_r3) + (_t5)) * _t2;
        _dst.m12 = ((_r5) * (_r3) - (_r6 * _r4)) * _t2;
        _dst.m22 = ((-((_r6) * (_r6) + (_r5 * _r5))) * (_t2) + (_r2));
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
        Float3x4Impl d = (Float3x4Impl) dest;
        float _r0 = this.sX;
        float _r1 = this.sY;
        float _r2 = this.sZ;
        float _r3 = this.rZ;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rX;
        float _r7 = this.tX;
        float _r8 = this.tY;
        float _r9 = this.tZ;
        float _t3 = _r3 * _r3;
        float _t4 = _r3 * _r4;
        float _t5 = _r5 * _r4;
        if (Math.useFma()) toMatrix3x4_s7311250d_c0_fma(d, _r5, _t3, _r0 + _r0, _r0, _r6, _t4, _r3, _t5); else toMatrix3x4_s7311250d_c0_mulAdd(d, _r5, _t3, _r0 + _r0, _r0, _r6, _t4, _r3, _t5);
        if (Math.useFma()) toMatrix3x4_s7311250d_c1_fma(d, _r6, _r5, _t4, _r1 + _r1, _t3, _r1, _r4, _r3); else toMatrix3x4_s7311250d_c1_mulAdd(d, _r6, _r5, _t4, _r1 + _r1, _t3, _r1, _r4, _r3);
        if (Math.useFma()) toMatrix3x4_s7311250d_c2_fma(d, _r6, _r3, _t5, _r2 + _r2, _r5, _r4, _r2); else toMatrix3x4_s7311250d_c2_mulAdd(d, _r6, _r3, _t5, _r2 + _r2, _r5, _r4, _r2);
        d.m03 = _r7;
        d.m13 = _r8;
        d.m23 = _r9;
        d.properties = Joml.BIT_AFFINE;
        return d;
    }

    /** Private column 0 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c0_fma(Double3x4Impl _dst, float _r5, float _t3, float _t0, float _r0, float _r6, float _t4, float _r3, float _t5) {
        _dst.m00 = java.lang.Math.fma(-java.lang.Math.fma(_r5, _r5, _t3), _t0, _r0);
        _dst.m10 = java.lang.Math.fma(_r6, _r5, _t4) * _t0;
        _dst.m20 = java.lang.Math.fma(_r6, _r3, -_t5) * _t0;
    }

    /** Private column 0 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c0_mulAdd(Double3x4Impl _dst, float _r5, float _t3, float _t0, float _r0, float _r6, float _t4, float _r3, float _t5) {
        _dst.m00 = ((-((_r5) * (_r5) + (_t3))) * (_t0) + (_r0));
        _dst.m10 = ((_r6) * (_r5) + (_t4)) * _t0;
        _dst.m20 = ((_r6) * (_r3) - (_t5)) * _t0;
    }

    /** Private column 1 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c1_fma(Double3x4Impl _dst, float _r6, float _r5, float _t4, float _t1, float _t3, float _r1, float _r4, float _r3) {
        _dst.m01 = java.lang.Math.fma(_r6, _r5, -_t4) * _t1;
        _dst.m11 = java.lang.Math.fma(-java.lang.Math.fma(_r6, _r6, _t3), _t1, _r1);
        _dst.m21 = java.lang.Math.fma(_r6, _r4, _r5 * _r3) * _t1;
    }

    /** Private column 1 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c1_mulAdd(Double3x4Impl _dst, float _r6, float _r5, float _t4, float _t1, float _t3, float _r1, float _r4, float _r3) {
        _dst.m01 = ((_r6) * (_r5) - (_t4)) * _t1;
        _dst.m11 = ((-((_r6) * (_r6) + (_t3))) * (_t1) + (_r1));
        _dst.m21 = ((_r6) * (_r4) + (_r5 * _r3)) * _t1;
    }

    /** Private column 2 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c2_fma(Double3x4Impl _dst, float _r6, float _r3, float _t5, float _t2, float _r5, float _r4, float _r2) {
        _dst.m02 = java.lang.Math.fma(_r6, _r3, _t5) * _t2;
        _dst.m12 = java.lang.Math.fma(_r5, _r3, -(_r6 * _r4)) * _t2;
        _dst.m22 = java.lang.Math.fma(-java.lang.Math.fma(_r6, _r6, _r5 * _r5), _t2, _r2);
    }

    /** Private column 2 of {@code toMatrix3x4}: computes and stores it; reached only through it. */
    private void toMatrix3x4_s38da5fc6_c2_mulAdd(Double3x4Impl _dst, float _r6, float _r3, float _t5, float _t2, float _r5, float _r4, float _r2) {
        _dst.m02 = ((_r6) * (_r3) + (_t5)) * _t2;
        _dst.m12 = ((_r5) * (_r3) - (_r6 * _r4)) * _t2;
        _dst.m22 = ((-((_r6) * (_r6) + (_r5 * _r5))) * (_t2) + (_r2));
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
        Double3x4Impl d = (Double3x4Impl) dest;
        float _r0 = this.sX;
        float _r1 = this.sY;
        float _r2 = this.sZ;
        float _r3 = this.rZ;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rX;
        float _r7 = this.tX;
        float _r8 = this.tY;
        float _r9 = this.tZ;
        float _t3 = _r3 * _r3;
        float _t4 = _r3 * _r4;
        float _t5 = _r5 * _r4;
        if (Math.useFma()) toMatrix3x4_s38da5fc6_c0_fma(d, _r5, _t3, _r0 + _r0, _r0, _r6, _t4, _r3, _t5); else toMatrix3x4_s38da5fc6_c0_mulAdd(d, _r5, _t3, _r0 + _r0, _r0, _r6, _t4, _r3, _t5);
        if (Math.useFma()) toMatrix3x4_s38da5fc6_c1_fma(d, _r6, _r5, _t4, _r1 + _r1, _t3, _r1, _r4, _r3); else toMatrix3x4_s38da5fc6_c1_mulAdd(d, _r6, _r5, _t4, _r1 + _r1, _t3, _r1, _r4, _r3);
        if (Math.useFma()) toMatrix3x4_s38da5fc6_c2_fma(d, _r6, _r3, _t5, _r2 + _r2, _r5, _r4, _r2); else toMatrix3x4_s38da5fc6_c2_mulAdd(d, _r6, _r3, _t5, _r2 + _r2, _r5, _r4, _r2);
        d.m03 = _r7;
        d.m13 = _r8;
        d.m23 = _r9;
        d.properties = Joml.BIT_AFFINE;
        return d;
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
        FloatRigidImpl d = (FloatRigidImpl) dest;
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
     * Convert this transform to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleTransform toDouble(@Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
    }


    /**
     * Set this transform to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public FloatTransform makeIdentity() {
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
        this.rX = 0.0f;
        this.rY = 0.0f;
        this.rZ = 0.0f;
        this.rW = 1.0f;
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
    @Mutated public FloatTransform set(float rotationX, float rotationY, float rotationZ, float rotationW) {
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
        this.rX = rotationX;
        this.rY = rotationY;
        this.rZ = rotationZ;
        this.rW = rotationW;
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
        float translationY = translation.y();
        float translationZ = translation.z();
        this.tX = translation.x();
        this.tY = translationY;
        this.tZ = translationZ;
        this.rX = 0.0f;
        this.rY = 0.0f;
        this.rZ = 0.0f;
        this.rW = 1.0f;
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
    @Mutated public FloatTransform set(float translationX, float translationY, float translationZ) {
        this.tX = translationX;
        this.tY = translationY;
        this.tZ = translationZ;
        this.rX = 0.0f;
        this.rY = 0.0f;
        this.rZ = 0.0f;
        this.rW = 1.0f;
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
        float _r0 = this.rW;
        float _r1 = this.rZ;
        float _r2 = this.rX;
        float _r3 = this.rY;
        float _t0 = 1.0f - t;
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
        return (Math.useFma() ? lerp_se37c393f_1_fma(otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ, t, (FloatTransformImpl) dest, _r0, _r1, _r2, _r3, this.tX, this.tY, this.tZ, this.sX, this.sY, this.sZ, _t0, _t17, 1.0f / _t17, Math.sin(t * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16)) : lerp_se37c393f_1_mulAdd(otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ, t, (FloatTransformImpl) dest, _r0, _r1, _r2, _r3, this.tX, this.tY, this.tZ, this.sX, this.sY, this.sZ, _t0, _t17, 1.0f / _t17, Math.sin(t * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16)));
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private FloatTransform lerp_se37c393f_1_fma(float otherTX, float otherTY, float otherTZ, float otherSX, float otherSY, float otherSZ, float t, FloatTransformImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
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
        d.tX = java.lang.Math.fma(t, otherTX - _r4, _r4);
        d.tY = java.lang.Math.fma(t, otherTY - _r5, _r5);
        d.tZ = java.lang.Math.fma(t, otherTZ - _r6, _r6);
        d.rX = _t49 != 0.0f ? _t50 * _t44 : 0.0f;
        d.rY = _t49 != 0.0f ? _t50 * _t45 : 0.0f;
        d.rZ = _t49 != 0.0f ? _t50 * _t43 : 0.0f;
        return lerp_se37c393f_2_fma(otherSX, otherSY, otherSZ, t, d, _r7, _r8, _r9, _t42, _t49, _t50);
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private FloatTransform lerp_se37c393f_1_mulAdd(float otherTX, float otherTY, float otherTZ, float otherSX, float otherSY, float otherSZ, float t, FloatTransformImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
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
        d.tX = ((t) * (otherTX - _r4) + (_r4));
        d.tY = ((t) * (otherTY - _r5) + (_r5));
        d.tZ = ((t) * (otherTZ - _r6) + (_r6));
        d.rX = _t49 != 0.0f ? _t50 * _t44 : 0.0f;
        d.rY = _t49 != 0.0f ? _t50 * _t45 : 0.0f;
        d.rZ = _t49 != 0.0f ? _t50 * _t43 : 0.0f;
        return lerp_se37c393f_2_mulAdd(otherSX, otherSY, otherSZ, t, d, _r7, _r8, _r9, _t42, _t49, _t50);
    }

    /** Piece 3 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private FloatTransform lerp_se37c393f_2_fma(float otherSX, float otherSY, float otherSZ, float t, FloatTransformImpl d, float _r7, float _r8, float _r9, float _t42, float _t49, float _t50) {
        d.rW = _t49 != 0.0f ? _t50 * _t42 : 0.0f;
        d.sX = java.lang.Math.fma(t, otherSX - _r7, _r7);
        d.sY = java.lang.Math.fma(t, otherSY - _r8, _r8);
        d.sZ = java.lang.Math.fma(t, otherSZ - _r9, _r9);
        return d;
    }

    /** Piece 3 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private FloatTransform lerp_se37c393f_2_mulAdd(float otherSX, float otherSY, float otherSZ, float t, FloatTransformImpl d, float _r7, float _r8, float _r9, float _t42, float _t49, float _t50) {
        d.rW = _t49 != 0.0f ? _t50 * _t42 : 0.0f;
        d.sX = ((t) * (otherSX - _r7) + (_r7));
        d.sY = ((t) * (otherSY - _r8) + (_r8));
        d.sZ = ((t) * (otherSZ - _r9) + (_r9));
        return d;
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
        float _r0 = this.rW;
        float _r1 = this.rZ;
        float _r2 = this.rX;
        float _r3 = this.rY;
        float _t0 = 1.0f - t;
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
        return (Math.useFma() ? lerp_s82cffb32_1_fma(otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ, t, (DoubleTransformImpl) dest, _r0, _r1, _r2, _r3, this.tX, this.tY, this.tZ, this.sX, this.sY, this.sZ, _t0, _t17, 1.0f / _t17, Math.sin(t * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16)) : lerp_s82cffb32_1_mulAdd(otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ, t, (DoubleTransformImpl) dest, _r0, _r1, _r2, _r3, this.tX, this.tY, this.tZ, this.sX, this.sY, this.sZ, _t0, _t17, 1.0f / _t17, Math.sin(t * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16)));
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleTransform lerp_s82cffb32_1_fma(float otherTX, float otherTY, float otherTZ, float otherSX, float otherSY, float otherSZ, float t, DoubleTransformImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
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
        d.tX = java.lang.Math.fma(t, otherTX - _r4, _r4);
        d.tY = java.lang.Math.fma(t, otherTY - _r5, _r5);
        d.tZ = java.lang.Math.fma(t, otherTZ - _r6, _r6);
        d.rX = _t49 != 0.0f ? _t50 * _t44 : 0.0f;
        d.rY = _t49 != 0.0f ? _t50 * _t45 : 0.0f;
        return lerp_s82cffb32_2_fma(otherSX, otherSY, otherSZ, t, d, _r7, _r8, _r9, _t42, _t43, _t49, _t50);
    }

    /** Piece 2 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleTransform lerp_s82cffb32_1_mulAdd(float otherTX, float otherTY, float otherTZ, float otherSX, float otherSY, float otherSZ, float t, DoubleTransformImpl d, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
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
        d.tX = ((t) * (otherTX - _r4) + (_r4));
        d.tY = ((t) * (otherTY - _r5) + (_r5));
        d.tZ = ((t) * (otherTZ - _r6) + (_r6));
        d.rX = _t49 != 0.0f ? _t50 * _t44 : 0.0f;
        d.rY = _t49 != 0.0f ? _t50 * _t45 : 0.0f;
        return lerp_s82cffb32_2_mulAdd(otherSX, otherSY, otherSZ, t, d, _r7, _r8, _r9, _t42, _t43, _t49, _t50);
    }

    /** Piece 3 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleTransform lerp_s82cffb32_2_fma(float otherSX, float otherSY, float otherSZ, float t, DoubleTransformImpl d, float _r7, float _r8, float _r9, float _t42, float _t43, float _t49, float _t50) {
        d.rZ = _t49 != 0.0f ? _t50 * _t43 : 0.0f;
        d.rW = _t49 != 0.0f ? _t50 * _t42 : 0.0f;
        d.sX = java.lang.Math.fma(t, otherSX - _r7, _r7);
        d.sY = java.lang.Math.fma(t, otherSY - _r8, _r8);
        d.sZ = java.lang.Math.fma(t, otherSZ - _r9, _r9);
        return d;
    }

    /** Piece 3 of {@code lerp}, split to fit the inline budget; reached only through it. */
    private DoubleTransform lerp_s82cffb32_2_mulAdd(float otherSX, float otherSY, float otherSZ, float t, DoubleTransformImpl d, float _r7, float _r8, float _r9, float _t42, float _t43, float _t49, float _t50) {
        d.rZ = _t49 != 0.0f ? _t50 * _t43 : 0.0f;
        d.rW = _t49 != 0.0f ? _t50 * _t42 : 0.0f;
        d.sX = ((t) * (otherSX - _r7) + (_r7));
        d.sY = ((t) * (otherSY - _r8) + (_r8));
        d.sZ = ((t) * (otherSZ - _r9) + (_r9));
        return d;
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

    /** Private store group 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s3e5a2933_c0_fma(FloatTransformImpl _dst, float _r4, float _t12, float _r5, float _t13, float _r6, float _t14, float otherTX, float _r1, float _r7, float _r3, float otherTY, float _r0, float _r8, float otherTZ, float _r2, float _r9, float otherRX, float otherRW, float otherRZ, float otherRY) {
        _dst.tX = java.lang.Math.fma(_r4, _t12, java.lang.Math.fma(-_r5, _t13, java.lang.Math.fma(_r6, _t14, java.lang.Math.fma(otherTX, _r1, _r7))));
        _dst.tY = java.lang.Math.fma(_r5, _t14, java.lang.Math.fma(-_r3, _t12, java.lang.Math.fma(_r6, _t13, java.lang.Math.fma(otherTY, _r0, _r8))));
        _dst.tZ = java.lang.Math.fma(_r3, _t13, java.lang.Math.fma(-_r4, _t14, java.lang.Math.fma(_r6, _t12, java.lang.Math.fma(otherTZ, _r2, _r9))));
        _dst.rX = java.lang.Math.fma(otherRX, _r6, otherRW * _r3) + java.lang.Math.fma(otherRZ, _r4, -(otherRY * _r5));
    }

    /** Private store group 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s3e5a2933_c0_mulAdd(FloatTransformImpl _dst, float _r4, float _t12, float _r5, float _t13, float _r6, float _t14, float otherTX, float _r1, float _r7, float _r3, float otherTY, float _r0, float _r8, float otherTZ, float _r2, float _r9, float otherRX, float otherRW, float otherRZ, float otherRY) {
        _dst.tX = ((_r4) * (_t12) + (((-_r5) * (_t13) + (((_r6) * (_t14) + (((otherTX) * (_r1) + (_r7))))))));
        _dst.tY = ((_r5) * (_t14) + (((-_r3) * (_t12) + (((_r6) * (_t13) + (((otherTY) * (_r0) + (_r8))))))));
        _dst.tZ = ((_r3) * (_t13) + (((-_r4) * (_t14) + (((_r6) * (_t12) + (((otherTZ) * (_r2) + (_r9))))))));
        _dst.rX = ((otherRX) * (_r6) + (otherRW * _r3)) + ((otherRZ) * (_r4) - (otherRY * _r5));
    }

    /** Private store group 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s3e5a2933_c1_fma(FloatTransformImpl _dst, float otherRY, float _r6, float otherRW, float _r4, float otherRX, float _r5, float otherRZ, float _r3) {
        _dst.rY = java.lang.Math.fma(otherRY, _r6, otherRW * _r4) + java.lang.Math.fma(otherRX, _r5, -(otherRZ * _r3));
        _dst.rZ = java.lang.Math.fma(otherRZ, _r6, otherRW * _r5) + java.lang.Math.fma(otherRY, _r3, -(otherRX * _r4));
        _dst.rW = java.lang.Math.fma(otherRW, _r6, -(otherRX * _r3)) - java.lang.Math.fma(otherRY, _r4, otherRZ * _r5);
    }

    /** Private store group 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s3e5a2933_c1_mulAdd(FloatTransformImpl _dst, float otherRY, float _r6, float otherRW, float _r4, float otherRX, float _r5, float otherRZ, float _r3) {
        _dst.rY = ((otherRY) * (_r6) + (otherRW * _r4)) + ((otherRX) * (_r5) - (otherRZ * _r3));
        _dst.rZ = ((otherRZ) * (_r6) + (otherRW * _r5)) + ((otherRY) * (_r3) - (otherRX * _r4));
        _dst.rW = ((otherRW) * (_r6) - (otherRX * _r3)) - ((otherRY) * (_r4) + (otherRZ * _r5));
    }

    /** Private tail of {@code mul}; reached only through it. */
    private void mul_s3e5a2933_tail_fma(FloatTransformImpl _dst, float _r4, float _t2, float _r5, float _t0, float _t12, float _t13, float _r6, float otherTX, float _r1, float _r7, float _r3, float otherTY, float _r0, float _r8, float otherTZ, float _r2, float _r9, float otherRX, float otherRW, float otherRZ, float otherRY, float otherSX, float otherSY, float otherSZ) {
        mul_s3e5a2933_c0_fma(_dst, _r4, _t12, _r5, _t13, _r6, 2.0f * java.lang.Math.fma(_r4, _t2, -(_r5 * _t0)), otherTX, _r1, _r7, _r3, otherTY, _r0, _r8, otherTZ, _r2, _r9, otherRX, otherRW, otherRZ, otherRY);
        mul_s3e5a2933_c1_fma(_dst, otherRY, _r6, otherRW, _r4, otherRX, _r5, otherRZ, _r3);
        _dst.sX = otherSX * _r1;
        _dst.sY = otherSY * _r0;
        _dst.sZ = otherSZ * _r2;
    }

    /** Private tail of {@code mul}; reached only through it. */
    private void mul_s3e5a2933_tail_mulAdd(FloatTransformImpl _dst, float _r4, float _t2, float _r5, float _t0, float _t12, float _t13, float _r6, float otherTX, float _r1, float _r7, float _r3, float otherTY, float _r0, float _r8, float otherTZ, float _r2, float _r9, float otherRX, float otherRW, float otherRZ, float otherRY, float otherSX, float otherSY, float otherSZ) {
        mul_s3e5a2933_c0_mulAdd(_dst, _r4, _t12, _r5, _t13, _r6, 2.0f * ((_r4) * (_t2) - (_r5 * _t0)), otherTX, _r1, _r7, _r3, otherTY, _r0, _r8, otherTZ, _r2, _r9, otherRX, otherRW, otherRZ, otherRY);
        mul_s3e5a2933_c1_mulAdd(_dst, otherRY, _r6, otherRW, _r4, otherRX, _r5, otherRZ, _r3);
        _dst.sX = otherSX * _r1;
        _dst.sY = otherSY * _r0;
        _dst.sZ = otherSZ * _r2;
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
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.sY;
        float _r1 = this.sX;
        float _r2 = this.sZ;
        float _r3 = this.rX;
        float _r4 = this.rY;
        float _r5 = this.rZ;
        float _r6 = this.rW;
        float _r7 = this.tX;
        float _r8 = this.tY;
        float _r9 = this.tZ;
        float _t0 = otherTY * _r0;
        float _t1 = otherTX * _r1;
        float _t2 = otherTZ * _r2;
        if (Math.useFma()) mul_s3e5a2933_tail_fma(d, _r4, _t2, _r5, _t0, 2.0f * java.lang.Math.fma(_r3, _t0, -(_r4 * _t1)), 2.0f * java.lang.Math.fma(_r5, _t1, -(_r3 * _t2)), _r6, otherTX, _r1, _r7, _r3, otherTY, _r0, _r8, otherTZ, _r2, _r9, otherRX, otherRW, otherRZ, otherRY, otherSX, otherSY, otherSZ); else mul_s3e5a2933_tail_mulAdd(d, _r4, _t2, _r5, _t0, 2.0f * ((_r3) * (_t0) - (_r4 * _t1)), 2.0f * ((_r5) * (_t1) - (_r3 * _t2)), _r6, otherTX, _r1, _r7, _r3, otherTY, _r0, _r8, otherTZ, _r2, _r9, otherRX, otherRW, otherRZ, otherRY, otherSX, otherSY, otherSZ);
        return d;
    }

    /** Private store group 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s11cc6446_c0_fma(DoubleTransformImpl _dst, float _r4, float _t12, float _r5, float _t13, float _r6, float _t14, float otherTX, float _r1, float _r7, float _r3, float otherTY, float _r0, float _r8, float otherTZ, float _r2, float _r9, float otherRX, float otherRW, float otherRZ, float otherRY) {
        _dst.tX = java.lang.Math.fma(_r4, _t12, java.lang.Math.fma(-_r5, _t13, java.lang.Math.fma(_r6, _t14, java.lang.Math.fma(otherTX, _r1, _r7))));
        _dst.tY = java.lang.Math.fma(_r5, _t14, java.lang.Math.fma(-_r3, _t12, java.lang.Math.fma(_r6, _t13, java.lang.Math.fma(otherTY, _r0, _r8))));
        _dst.tZ = java.lang.Math.fma(_r3, _t13, java.lang.Math.fma(-_r4, _t14, java.lang.Math.fma(_r6, _t12, java.lang.Math.fma(otherTZ, _r2, _r9))));
        _dst.rX = java.lang.Math.fma(otherRX, _r6, otherRW * _r3) + java.lang.Math.fma(otherRZ, _r4, -(otherRY * _r5));
    }

    /** Private store group 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s11cc6446_c0_mulAdd(DoubleTransformImpl _dst, float _r4, float _t12, float _r5, float _t13, float _r6, float _t14, float otherTX, float _r1, float _r7, float _r3, float otherTY, float _r0, float _r8, float otherTZ, float _r2, float _r9, float otherRX, float otherRW, float otherRZ, float otherRY) {
        _dst.tX = ((_r4) * (_t12) + (((-_r5) * (_t13) + (((_r6) * (_t14) + (((otherTX) * (_r1) + (_r7))))))));
        _dst.tY = ((_r5) * (_t14) + (((-_r3) * (_t12) + (((_r6) * (_t13) + (((otherTY) * (_r0) + (_r8))))))));
        _dst.tZ = ((_r3) * (_t13) + (((-_r4) * (_t14) + (((_r6) * (_t12) + (((otherTZ) * (_r2) + (_r9))))))));
        _dst.rX = ((otherRX) * (_r6) + (otherRW * _r3)) + ((otherRZ) * (_r4) - (otherRY * _r5));
    }

    /** Private store group 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s11cc6446_c1_fma(DoubleTransformImpl _dst, float otherRY, float _r6, float otherRW, float _r4, float otherRX, float _r5, float otherRZ, float _r3) {
        _dst.rY = java.lang.Math.fma(otherRY, _r6, otherRW * _r4) + java.lang.Math.fma(otherRX, _r5, -(otherRZ * _r3));
        _dst.rZ = java.lang.Math.fma(otherRZ, _r6, otherRW * _r5) + java.lang.Math.fma(otherRY, _r3, -(otherRX * _r4));
        _dst.rW = java.lang.Math.fma(otherRW, _r6, -(otherRX * _r3)) - java.lang.Math.fma(otherRY, _r4, otherRZ * _r5);
    }

    /** Private store group 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s11cc6446_c1_mulAdd(DoubleTransformImpl _dst, float otherRY, float _r6, float otherRW, float _r4, float otherRX, float _r5, float otherRZ, float _r3) {
        _dst.rY = ((otherRY) * (_r6) + (otherRW * _r4)) + ((otherRX) * (_r5) - (otherRZ * _r3));
        _dst.rZ = ((otherRZ) * (_r6) + (otherRW * _r5)) + ((otherRY) * (_r3) - (otherRX * _r4));
        _dst.rW = ((otherRW) * (_r6) - (otherRX * _r3)) - ((otherRY) * (_r4) + (otherRZ * _r5));
    }

    /** Private tail of {@code mul}; reached only through it. */
    private void mul_s11cc6446_tail_fma(DoubleTransformImpl _dst, float _r4, float _t2, float _r5, float _t0, float _t12, float _t13, float _r6, float otherTX, float _r1, float _r7, float _r3, float otherTY, float _r0, float _r8, float otherTZ, float _r2, float _r9, float otherRX, float otherRW, float otherRZ, float otherRY, float otherSX, float otherSY, float otherSZ) {
        mul_s11cc6446_c0_fma(_dst, _r4, _t12, _r5, _t13, _r6, 2.0f * java.lang.Math.fma(_r4, _t2, -(_r5 * _t0)), otherTX, _r1, _r7, _r3, otherTY, _r0, _r8, otherTZ, _r2, _r9, otherRX, otherRW, otherRZ, otherRY);
        mul_s11cc6446_c1_fma(_dst, otherRY, _r6, otherRW, _r4, otherRX, _r5, otherRZ, _r3);
        _dst.sX = otherSX * _r1;
        _dst.sY = otherSY * _r0;
        _dst.sZ = otherSZ * _r2;
    }

    /** Private tail of {@code mul}; reached only through it. */
    private void mul_s11cc6446_tail_mulAdd(DoubleTransformImpl _dst, float _r4, float _t2, float _r5, float _t0, float _t12, float _t13, float _r6, float otherTX, float _r1, float _r7, float _r3, float otherTY, float _r0, float _r8, float otherTZ, float _r2, float _r9, float otherRX, float otherRW, float otherRZ, float otherRY, float otherSX, float otherSY, float otherSZ) {
        mul_s11cc6446_c0_mulAdd(_dst, _r4, _t12, _r5, _t13, _r6, 2.0f * ((_r4) * (_t2) - (_r5 * _t0)), otherTX, _r1, _r7, _r3, otherTY, _r0, _r8, otherTZ, _r2, _r9, otherRX, otherRW, otherRZ, otherRY);
        mul_s11cc6446_c1_mulAdd(_dst, otherRY, _r6, otherRW, _r4, otherRX, _r5, otherRZ, _r3);
        _dst.sX = otherSX * _r1;
        _dst.sY = otherSY * _r0;
        _dst.sZ = otherSZ * _r2;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.sY;
        float _r1 = this.sX;
        float _r2 = this.sZ;
        float _r3 = this.rX;
        float _r4 = this.rY;
        float _r5 = this.rZ;
        float _r6 = this.rW;
        float _r7 = this.tX;
        float _r8 = this.tY;
        float _r9 = this.tZ;
        float _t0 = otherTY * _r0;
        float _t1 = otherTX * _r1;
        float _t2 = otherTZ * _r2;
        if (Math.useFma()) mul_s11cc6446_tail_fma(d, _r4, _t2, _r5, _t0, 2.0f * java.lang.Math.fma(_r3, _t0, -(_r4 * _t1)), 2.0f * java.lang.Math.fma(_r5, _t1, -(_r3 * _t2)), _r6, otherTX, _r1, _r7, _r3, otherTY, _r0, _r8, otherTZ, _r2, _r9, otherRX, otherRW, otherRZ, otherRY, otherSX, otherSY, otherSZ); else mul_s11cc6446_tail_mulAdd(d, _r4, _t2, _r5, _t0, 2.0f * ((_r3) * (_t0) - (_r4 * _t1)), 2.0f * ((_r5) * (_t1) - (_r3 * _t2)), _r6, otherTX, _r1, _r7, _r3, otherTY, _r0, _r8, otherTZ, _r2, _r9, otherRX, otherRW, otherRZ, otherRY, otherSX, otherSY, otherSZ);
        return d;
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

    /** Private store group 0 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s3e5a2933_c0_fma(FloatTransformImpl _dst, float otherRY, float _t12, float otherRZ, float _t13, float otherRW, float _t14, float otherSX, float _r1, float otherTX, float otherRX, float otherSY, float _r0, float otherTY, float otherSZ, float _r2, float otherTZ, float _r3, float _r4, float _r5, float _r6) {
        _dst.tX = java.lang.Math.fma(otherRY, _t12, java.lang.Math.fma(-otherRZ, _t13, java.lang.Math.fma(otherRW, _t14, java.lang.Math.fma(otherSX, _r1, otherTX))));
        _dst.tY = java.lang.Math.fma(otherRZ, _t14, java.lang.Math.fma(-otherRX, _t12, java.lang.Math.fma(otherRW, _t13, java.lang.Math.fma(otherSY, _r0, otherTY))));
        _dst.tZ = java.lang.Math.fma(otherRX, _t13, java.lang.Math.fma(-otherRY, _t14, java.lang.Math.fma(otherRW, _t12, java.lang.Math.fma(otherSZ, _r2, otherTZ))));
        _dst.rX = java.lang.Math.fma(otherRX, _r3, otherRW * _r4) + java.lang.Math.fma(otherRY, _r5, -(otherRZ * _r6));
    }

    /** Private store group 0 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s3e5a2933_c0_mulAdd(FloatTransformImpl _dst, float otherRY, float _t12, float otherRZ, float _t13, float otherRW, float _t14, float otherSX, float _r1, float otherTX, float otherRX, float otherSY, float _r0, float otherTY, float otherSZ, float _r2, float otherTZ, float _r3, float _r4, float _r5, float _r6) {
        _dst.tX = ((otherRY) * (_t12) + (((-otherRZ) * (_t13) + (((otherRW) * (_t14) + (((otherSX) * (_r1) + (otherTX))))))));
        _dst.tY = ((otherRZ) * (_t14) + (((-otherRX) * (_t12) + (((otherRW) * (_t13) + (((otherSY) * (_r0) + (otherTY))))))));
        _dst.tZ = ((otherRX) * (_t13) + (((-otherRY) * (_t14) + (((otherRW) * (_t12) + (((otherSZ) * (_r2) + (otherTZ))))))));
        _dst.rX = ((otherRX) * (_r3) + (otherRW * _r4)) + ((otherRY) * (_r5) - (otherRZ * _r6));
    }

    /**
     * Private store group 1 of {@code preMul}: computes and stores it. Shared by the identical
     * private paths of {@code preMul}, {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY},
     * {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only
     * through them.
     */
    private void preMul_s3e5a2933_c1_fma(FloatTransformImpl _dst, float otherRY, float _r3, float otherRW, float _r6, float otherRZ, float _r4, float otherRX, float _r5) {
        _dst.rY = java.lang.Math.fma(otherRY, _r3, otherRW * _r6) + java.lang.Math.fma(otherRZ, _r4, -(otherRX * _r5));
        _dst.rZ = java.lang.Math.fma(otherRZ, _r3, otherRW * _r5) + java.lang.Math.fma(otherRX, _r6, -(otherRY * _r4));
        _dst.rW = java.lang.Math.fma(otherRW, _r3, -(otherRX * _r4)) - java.lang.Math.fma(otherRY, _r6, otherRZ * _r5);
    }

    /**
     * Private store group 1 of {@code preMul}: computes and stores it. Shared by the identical
     * private paths of {@code preMul}, {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY},
     * {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only
     * through them.
     */
    private void preMul_s3e5a2933_c1_mulAdd(FloatTransformImpl _dst, float otherRY, float _r3, float otherRW, float _r6, float otherRZ, float _r4, float otherRX, float _r5) {
        _dst.rY = ((otherRY) * (_r3) + (otherRW * _r6)) + ((otherRZ) * (_r4) - (otherRX * _r5));
        _dst.rZ = ((otherRZ) * (_r3) + (otherRW * _r5)) + ((otherRX) * (_r6) - (otherRY * _r4));
        _dst.rW = ((otherRW) * (_r3) - (otherRX * _r4)) - ((otherRY) * (_r6) + (otherRZ * _r5));
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private void preMul_s3e5a2933_tail_fma(FloatTransformImpl _dst, float otherRY, float _t2, float otherRZ, float _t0, float _t12, float _t13, float otherRW, float otherSX, float _r1, float otherTX, float otherRX, float otherSY, float _r0, float otherTY, float otherSZ, float _r2, float otherTZ, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9) {
        preMul_s3e5a2933_c0_fma(_dst, otherRY, _t12, otherRZ, _t13, otherRW, 2.0f * java.lang.Math.fma(otherRY, _t2, -(otherRZ * _t0)), otherSX, _r1, otherTX, otherRX, otherSY, _r0, otherTY, otherSZ, _r2, otherTZ, _r3, _r4, _r5, _r6);
        preMul_s3e5a2933_c1_fma(_dst, otherRY, _r3, otherRW, _r6, otherRZ, _r4, otherRX, _r5);
        _dst.sX = otherSX * _r7;
        _dst.sY = otherSY * _r8;
        _dst.sZ = otherSZ * _r9;
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private void preMul_s3e5a2933_tail_mulAdd(FloatTransformImpl _dst, float otherRY, float _t2, float otherRZ, float _t0, float _t12, float _t13, float otherRW, float otherSX, float _r1, float otherTX, float otherRX, float otherSY, float _r0, float otherTY, float otherSZ, float _r2, float otherTZ, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9) {
        preMul_s3e5a2933_c0_mulAdd(_dst, otherRY, _t12, otherRZ, _t13, otherRW, 2.0f * ((otherRY) * (_t2) - (otherRZ * _t0)), otherSX, _r1, otherTX, otherRX, otherSY, _r0, otherTY, otherSZ, _r2, otherTZ, _r3, _r4, _r5, _r6);
        preMul_s3e5a2933_c1_mulAdd(_dst, otherRY, _r3, otherRW, _r6, otherRZ, _r4, otherRX, _r5);
        _dst.sX = otherSX * _r7;
        _dst.sY = otherSY * _r8;
        _dst.sZ = otherSZ * _r9;
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
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.tY;
        float _r1 = this.tX;
        float _r2 = this.tZ;
        float _r3 = this.rW;
        float _r4 = this.rX;
        float _r5 = this.rZ;
        float _r6 = this.rY;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
        float _t0 = otherSY * _r0;
        float _t1 = otherSX * _r1;
        float _t2 = otherSZ * _r2;
        if (Math.useFma()) preMul_s3e5a2933_tail_fma(d, otherRY, _t2, otherRZ, _t0, 2.0f * java.lang.Math.fma(otherRX, _t0, -(otherRY * _t1)), 2.0f * java.lang.Math.fma(otherRZ, _t1, -(otherRX * _t2)), otherRW, otherSX, _r1, otherTX, otherRX, otherSY, _r0, otherTY, otherSZ, _r2, otherTZ, _r3, _r4, _r5, _r6, _r7, _r8, _r9); else preMul_s3e5a2933_tail_mulAdd(d, otherRY, _t2, otherRZ, _t0, 2.0f * ((otherRX) * (_t0) - (otherRY * _t1)), 2.0f * ((otherRZ) * (_t1) - (otherRX * _t2)), otherRW, otherSX, _r1, otherTX, otherRX, otherSY, _r0, otherTY, otherSZ, _r2, otherTZ, _r3, _r4, _r5, _r6, _r7, _r8, _r9);
        return d;
    }

    /** Private store group 0 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s11cc6446_c0_fma(DoubleTransformImpl _dst, float otherRY, float _t12, float otherRZ, float _t13, float otherRW, float _t14, float otherSX, float _r1, float otherTX, float otherRX, float otherSY, float _r0, float otherTY, float otherSZ, float _r2, float otherTZ, float _r3, float _r4, float _r5, float _r6) {
        _dst.tX = java.lang.Math.fma(otherRY, _t12, java.lang.Math.fma(-otherRZ, _t13, java.lang.Math.fma(otherRW, _t14, java.lang.Math.fma(otherSX, _r1, otherTX))));
        _dst.tY = java.lang.Math.fma(otherRZ, _t14, java.lang.Math.fma(-otherRX, _t12, java.lang.Math.fma(otherRW, _t13, java.lang.Math.fma(otherSY, _r0, otherTY))));
        _dst.tZ = java.lang.Math.fma(otherRX, _t13, java.lang.Math.fma(-otherRY, _t14, java.lang.Math.fma(otherRW, _t12, java.lang.Math.fma(otherSZ, _r2, otherTZ))));
        _dst.rX = java.lang.Math.fma(otherRX, _r3, otherRW * _r4) + java.lang.Math.fma(otherRY, _r5, -(otherRZ * _r6));
    }

    /** Private store group 0 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s11cc6446_c0_mulAdd(DoubleTransformImpl _dst, float otherRY, float _t12, float otherRZ, float _t13, float otherRW, float _t14, float otherSX, float _r1, float otherTX, float otherRX, float otherSY, float _r0, float otherTY, float otherSZ, float _r2, float otherTZ, float _r3, float _r4, float _r5, float _r6) {
        _dst.tX = ((otherRY) * (_t12) + (((-otherRZ) * (_t13) + (((otherRW) * (_t14) + (((otherSX) * (_r1) + (otherTX))))))));
        _dst.tY = ((otherRZ) * (_t14) + (((-otherRX) * (_t12) + (((otherRW) * (_t13) + (((otherSY) * (_r0) + (otherTY))))))));
        _dst.tZ = ((otherRX) * (_t13) + (((-otherRY) * (_t14) + (((otherRW) * (_t12) + (((otherSZ) * (_r2) + (otherTZ))))))));
        _dst.rX = ((otherRX) * (_r3) + (otherRW * _r4)) + ((otherRY) * (_r5) - (otherRZ * _r6));
    }

    /**
     * Private store group 1 of {@code preMul}: computes and stores it. Shared by the identical
     * private paths of {@code preMul}, {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY},
     * {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only
     * through them.
     */
    private void preMul_s11cc6446_c1_fma(DoubleTransformImpl _dst, float otherRY, float _r3, float otherRW, float _r6, float otherRZ, float _r4, float otherRX, float _r5) {
        _dst.rY = java.lang.Math.fma(otherRY, _r3, otherRW * _r6) + java.lang.Math.fma(otherRZ, _r4, -(otherRX * _r5));
        _dst.rZ = java.lang.Math.fma(otherRZ, _r3, otherRW * _r5) + java.lang.Math.fma(otherRX, _r6, -(otherRY * _r4));
        _dst.rW = java.lang.Math.fma(otherRW, _r3, -(otherRX * _r4)) - java.lang.Math.fma(otherRY, _r6, otherRZ * _r5);
    }

    /**
     * Private store group 1 of {@code preMul}: computes and stores it. Shared by the identical
     * private paths of {@code preMul}, {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY},
     * {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only
     * through them.
     */
    private void preMul_s11cc6446_c1_mulAdd(DoubleTransformImpl _dst, float otherRY, float _r3, float otherRW, float _r6, float otherRZ, float _r4, float otherRX, float _r5) {
        _dst.rY = ((otherRY) * (_r3) + (otherRW * _r6)) + ((otherRZ) * (_r4) - (otherRX * _r5));
        _dst.rZ = ((otherRZ) * (_r3) + (otherRW * _r5)) + ((otherRX) * (_r6) - (otherRY * _r4));
        _dst.rW = ((otherRW) * (_r3) - (otherRX * _r4)) - ((otherRY) * (_r6) + (otherRZ * _r5));
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private void preMul_s11cc6446_tail_fma(DoubleTransformImpl _dst, float otherRY, float _t2, float otherRZ, float _t0, float _t12, float _t13, float otherRW, float otherSX, float _r1, float otherTX, float otherRX, float otherSY, float _r0, float otherTY, float otherSZ, float _r2, float otherTZ, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9) {
        preMul_s11cc6446_c0_fma(_dst, otherRY, _t12, otherRZ, _t13, otherRW, 2.0f * java.lang.Math.fma(otherRY, _t2, -(otherRZ * _t0)), otherSX, _r1, otherTX, otherRX, otherSY, _r0, otherTY, otherSZ, _r2, otherTZ, _r3, _r4, _r5, _r6);
        preMul_s11cc6446_c1_fma(_dst, otherRY, _r3, otherRW, _r6, otherRZ, _r4, otherRX, _r5);
        _dst.sX = otherSX * _r7;
        _dst.sY = otherSY * _r8;
        _dst.sZ = otherSZ * _r9;
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private void preMul_s11cc6446_tail_mulAdd(DoubleTransformImpl _dst, float otherRY, float _t2, float otherRZ, float _t0, float _t12, float _t13, float otherRW, float otherSX, float _r1, float otherTX, float otherRX, float otherSY, float _r0, float otherTY, float otherSZ, float _r2, float otherTZ, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9) {
        preMul_s11cc6446_c0_mulAdd(_dst, otherRY, _t12, otherRZ, _t13, otherRW, 2.0f * ((otherRY) * (_t2) - (otherRZ * _t0)), otherSX, _r1, otherTX, otherRX, otherSY, _r0, otherTY, otherSZ, _r2, otherTZ, _r3, _r4, _r5, _r6);
        preMul_s11cc6446_c1_mulAdd(_dst, otherRY, _r3, otherRW, _r6, otherRZ, _r4, otherRX, _r5);
        _dst.sX = otherSX * _r7;
        _dst.sY = otherSY * _r8;
        _dst.sZ = otherSZ * _r9;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.tY;
        float _r1 = this.tX;
        float _r2 = this.tZ;
        float _r3 = this.rW;
        float _r4 = this.rX;
        float _r5 = this.rZ;
        float _r6 = this.rY;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
        float _t0 = otherSY * _r0;
        float _t1 = otherSX * _r1;
        float _t2 = otherSZ * _r2;
        if (Math.useFma()) preMul_s11cc6446_tail_fma(d, otherRY, _t2, otherRZ, _t0, 2.0f * java.lang.Math.fma(otherRX, _t0, -(otherRY * _t1)), 2.0f * java.lang.Math.fma(otherRZ, _t1, -(otherRX * _t2)), otherRW, otherSX, _r1, otherTX, otherRX, otherSY, _r0, otherTY, otherSZ, _r2, otherTZ, _r3, _r4, _r5, _r6, _r7, _r8, _r9); else preMul_s11cc6446_tail_mulAdd(d, otherRY, _t2, otherRZ, _t0, 2.0f * ((otherRX) * (_t0) - (otherRY * _t1)), 2.0f * ((otherRZ) * (_t1) - (otherRX * _t2)), otherRW, otherSX, _r1, otherTX, otherRX, otherSY, _r0, otherTY, otherSZ, _r2, otherTZ, _r3, _r4, _r5, _r6, _r7, _r8, _r9);
        return d;
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

    /** Private store group 0 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s3e5a2933_c0_fma(FloatTransformImpl _dst, float _r7, float _t30, float _r8, float _t31, float _r9, float _t32, float _sp1, float _t33, float _t34, float _t35, float _sp3, float _r6, float _sp2, float _sp5, float _sp0, float _sp4, float otherRX, float otherRW, float otherRY, float otherRZ) {
        _dst.tX = java.lang.Math.fma(_r7, _t30, -(_r8 * _t31)) + java.lang.Math.fma(_r9, _t32, _sp1) + (java.lang.Math.fma(_r7, _t33, -(_r8 * _t34)) + java.lang.Math.fma(_r9, _t35, -_sp3));
        _dst.tY = java.lang.Math.fma(_r6, _t31, -(_r7 * _t32)) + java.lang.Math.fma(_r9, _t30, _sp2) + (java.lang.Math.fma(_r6, _t34, -(_r7 * _t35)) + java.lang.Math.fma(_r9, _t33, -_sp5));
        _dst.tZ = java.lang.Math.fma(_r8, _t32, -(_r6 * _t30)) + java.lang.Math.fma(_r9, _t31, _sp0) + (java.lang.Math.fma(_r8, _t35, -(_r6 * _t33)) + java.lang.Math.fma(_r9, _t34, -_sp4));
        _dst.rX = java.lang.Math.fma(otherRX, _r9, -(otherRW * _r6)) + java.lang.Math.fma(otherRY, _r7, -(otherRZ * _r8));
    }

    /** Private store group 0 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s3e5a2933_c0_mulAdd(FloatTransformImpl _dst, float _r7, float _t30, float _r8, float _t31, float _r9, float _t32, float _sp1, float _t33, float _t34, float _t35, float _sp3, float _r6, float _sp2, float _sp5, float _sp0, float _sp4, float otherRX, float otherRW, float otherRY, float otherRZ) {
        _dst.tX = ((_r7) * (_t30) - (_r8 * _t31)) + ((_r9) * (_t32) + (_sp1)) + (((_r7) * (_t33) - (_r8 * _t34)) + ((_r9) * (_t35) - (_sp3)));
        _dst.tY = ((_r6) * (_t31) - (_r7 * _t32)) + ((_r9) * (_t30) + (_sp2)) + (((_r6) * (_t34) - (_r7 * _t35)) + ((_r9) * (_t33) - (_sp5)));
        _dst.tZ = ((_r8) * (_t32) - (_r6 * _t30)) + ((_r9) * (_t31) + (_sp0)) + (((_r8) * (_t35) - (_r6 * _t33)) + ((_r9) * (_t34) - (_sp4)));
        _dst.rX = ((otherRX) * (_r9) - (otherRW * _r6)) + ((otherRY) * (_r7) - (otherRZ * _r8));
    }

    /** Private store group 1 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s3e5a2933_c1_fma(FloatTransformImpl _dst, float otherRY, float _r9, float otherRW, float _r8, float otherRZ, float _r6, float otherRX, float _r7) {
        _dst.rY = java.lang.Math.fma(otherRY, _r9, -(otherRW * _r8)) + java.lang.Math.fma(otherRZ, _r6, -(otherRX * _r7));
        _dst.rZ = java.lang.Math.fma(otherRX, _r8, -(otherRY * _r6)) + java.lang.Math.fma(otherRZ, _r9, -(otherRW * _r7));
        _dst.rW = java.lang.Math.fma(otherRX, _r6, otherRW * _r9) - java.lang.Math.fma(-otherRZ, _r7, -(otherRY * _r8));
    }

    /** Private store group 1 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s3e5a2933_c1_mulAdd(FloatTransformImpl _dst, float otherRY, float _r9, float otherRW, float _r8, float otherRZ, float _r6, float otherRX, float _r7) {
        _dst.rY = ((otherRY) * (_r9) - (otherRW * _r8)) + ((otherRZ) * (_r6) - (otherRX * _r7));
        _dst.rZ = ((otherRX) * (_r8) - (otherRY * _r6)) + ((otherRZ) * (_r9) - (otherRW * _r7));
        _dst.rW = ((otherRX) * (_r6) + (otherRW * _r9)) - ((-otherRZ) * (_r7) - (otherRY * _r8));
    }

    /** Private tail of {@code difference}; reached only through it. */
    private void difference_s3e5a2933_tail_fma(FloatTransformImpl _dst, float _sp2, float _r7, float _sp0, float _r8, float _sp3, float _sp4, float _r6, float _sp5, float _t30, float _t31, float _r9, float _sp1, float otherRX, float otherRW, float otherRY, float otherRZ, float otherSX, float _rcp1, float otherSY, float _rcp2, float otherSZ, float _rcp0) {
        difference_s3e5a2933_c0_fma(_dst, _r7, _t30, _r8, _t31, _r9, 2.0f * java.lang.Math.fma(_sp2, _r7, -(_sp0 * _r8)), _sp1, 2.0f * java.lang.Math.fma(_sp3, _r7, -(_sp4 * _r6)), 2.0f * java.lang.Math.fma(_sp5, _r6, -(_sp3 * _r8)), 2.0f * java.lang.Math.fma(_sp4, _r8, -(_sp5 * _r7)), _sp3, _r6, _sp2, _sp5, _sp0, _sp4, otherRX, otherRW, otherRY, otherRZ);
        difference_s3e5a2933_c1_fma(_dst, otherRY, _r9, otherRW, _r8, otherRZ, _r6, otherRX, _r7);
        _dst.sX = otherSX * _rcp1;
        _dst.sY = otherSY * _rcp2;
        _dst.sZ = otherSZ * _rcp0;
    }

    /** Private tail of {@code difference}; reached only through it. */
    private void difference_s3e5a2933_tail_mulAdd(FloatTransformImpl _dst, float _sp2, float _r7, float _sp0, float _r8, float _sp3, float _sp4, float _r6, float _sp5, float _t30, float _t31, float _r9, float _sp1, float otherRX, float otherRW, float otherRY, float otherRZ, float otherSX, float _rcp1, float otherSY, float _rcp2, float otherSZ, float _rcp0) {
        difference_s3e5a2933_c0_mulAdd(_dst, _r7, _t30, _r8, _t31, _r9, 2.0f * ((_sp2) * (_r7) - (_sp0 * _r8)), _sp1, 2.0f * ((_sp3) * (_r7) - (_sp4 * _r6)), 2.0f * ((_sp5) * (_r6) - (_sp3 * _r8)), 2.0f * ((_sp4) * (_r8) - (_sp5 * _r7)), _sp3, _r6, _sp2, _sp5, _sp0, _sp4, otherRX, otherRW, otherRY, otherRZ);
        difference_s3e5a2933_c1_mulAdd(_dst, otherRY, _r9, otherRW, _r8, otherRZ, _r6, otherRX, _r7);
        _dst.sX = otherSX * _rcp1;
        _dst.sY = otherSY * _rcp2;
        _dst.sZ = otherSZ * _rcp0;
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
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r1 = this.tZ;
        float _r3 = this.tX;
        float _r5 = this.tY;
        float _r6 = this.rX;
        float _r7 = this.rZ;
        float _r8 = this.rY;
        float _r9 = this.rW;
        float _rcp0 = 1.0f / this.sZ;
        float _sp0 = otherTZ * _rcp0;
        float _rcp1 = 1.0f / this.sX;
        float _sp1 = otherTX * _rcp1;
        float _rcp2 = 1.0f / this.sY;
        float _sp2 = otherTY * _rcp2;
        if (Math.useFma()) difference_s3e5a2933_tail_fma(d, _sp2, _r7, _sp0, _r8, _rcp1 * _r3, _rcp0 * _r1, _r6, _rcp2 * _r5, 2.0f * java.lang.Math.fma(_sp0, _r6, -(_sp1 * _r7)), 2.0f * java.lang.Math.fma(_sp1, _r8, -(_sp2 * _r6)), _r9, _sp1, otherRX, otherRW, otherRY, otherRZ, otherSX, _rcp1, otherSY, _rcp2, otherSZ, _rcp0); else difference_s3e5a2933_tail_mulAdd(d, _sp2, _r7, _sp0, _r8, _rcp1 * _r3, _rcp0 * _r1, _r6, _rcp2 * _r5, 2.0f * ((_sp0) * (_r6) - (_sp1 * _r7)), 2.0f * ((_sp1) * (_r8) - (_sp2 * _r6)), _r9, _sp1, otherRX, otherRW, otherRY, otherRZ, otherSX, _rcp1, otherSY, _rcp2, otherSZ, _rcp0);
        return d;
    }

    /** Private store group 0 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s11cc6446_c0_fma(DoubleTransformImpl _dst, float _r7, float _t30, float _r8, float _t31, float _r9, float _t32, float _sp1, float _t33, float _t34, float _t35, float _sp3, float _r6, float _sp2, float _sp5, float _sp0, float _sp4, float otherRX, float otherRW, float otherRY, float otherRZ) {
        _dst.tX = java.lang.Math.fma(_r7, _t30, -(_r8 * _t31)) + java.lang.Math.fma(_r9, _t32, _sp1) + (java.lang.Math.fma(_r7, _t33, -(_r8 * _t34)) + java.lang.Math.fma(_r9, _t35, -_sp3));
        _dst.tY = java.lang.Math.fma(_r6, _t31, -(_r7 * _t32)) + java.lang.Math.fma(_r9, _t30, _sp2) + (java.lang.Math.fma(_r6, _t34, -(_r7 * _t35)) + java.lang.Math.fma(_r9, _t33, -_sp5));
        _dst.tZ = java.lang.Math.fma(_r8, _t32, -(_r6 * _t30)) + java.lang.Math.fma(_r9, _t31, _sp0) + (java.lang.Math.fma(_r8, _t35, -(_r6 * _t33)) + java.lang.Math.fma(_r9, _t34, -_sp4));
        _dst.rX = java.lang.Math.fma(otherRX, _r9, -(otherRW * _r6)) + java.lang.Math.fma(otherRY, _r7, -(otherRZ * _r8));
    }

    /** Private store group 0 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s11cc6446_c0_mulAdd(DoubleTransformImpl _dst, float _r7, float _t30, float _r8, float _t31, float _r9, float _t32, float _sp1, float _t33, float _t34, float _t35, float _sp3, float _r6, float _sp2, float _sp5, float _sp0, float _sp4, float otherRX, float otherRW, float otherRY, float otherRZ) {
        _dst.tX = ((_r7) * (_t30) - (_r8 * _t31)) + ((_r9) * (_t32) + (_sp1)) + (((_r7) * (_t33) - (_r8 * _t34)) + ((_r9) * (_t35) - (_sp3)));
        _dst.tY = ((_r6) * (_t31) - (_r7 * _t32)) + ((_r9) * (_t30) + (_sp2)) + (((_r6) * (_t34) - (_r7 * _t35)) + ((_r9) * (_t33) - (_sp5)));
        _dst.tZ = ((_r8) * (_t32) - (_r6 * _t30)) + ((_r9) * (_t31) + (_sp0)) + (((_r8) * (_t35) - (_r6 * _t33)) + ((_r9) * (_t34) - (_sp4)));
        _dst.rX = ((otherRX) * (_r9) - (otherRW * _r6)) + ((otherRY) * (_r7) - (otherRZ * _r8));
    }

    /** Private store group 1 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s11cc6446_c1_fma(DoubleTransformImpl _dst, float otherRY, float _r9, float otherRW, float _r8, float otherRZ, float _r6, float otherRX, float _r7) {
        _dst.rY = java.lang.Math.fma(otherRY, _r9, -(otherRW * _r8)) + java.lang.Math.fma(otherRZ, _r6, -(otherRX * _r7));
        _dst.rZ = java.lang.Math.fma(otherRX, _r8, -(otherRY * _r6)) + java.lang.Math.fma(otherRZ, _r9, -(otherRW * _r7));
        _dst.rW = java.lang.Math.fma(otherRX, _r6, otherRW * _r9) - java.lang.Math.fma(-otherRZ, _r7, -(otherRY * _r8));
    }

    /** Private store group 1 of {@code difference}: computes and stores it; reached only through it. */
    private void difference_s11cc6446_c1_mulAdd(DoubleTransformImpl _dst, float otherRY, float _r9, float otherRW, float _r8, float otherRZ, float _r6, float otherRX, float _r7) {
        _dst.rY = ((otherRY) * (_r9) - (otherRW * _r8)) + ((otherRZ) * (_r6) - (otherRX * _r7));
        _dst.rZ = ((otherRX) * (_r8) - (otherRY * _r6)) + ((otherRZ) * (_r9) - (otherRW * _r7));
        _dst.rW = ((otherRX) * (_r6) + (otherRW * _r9)) - ((-otherRZ) * (_r7) - (otherRY * _r8));
    }

    /** Private tail of {@code difference}; reached only through it. */
    private void difference_s11cc6446_tail_fma(DoubleTransformImpl _dst, float _sp2, float _r7, float _sp0, float _r8, float _sp3, float _sp4, float _r6, float _sp5, float _t30, float _t31, float _r9, float _sp1, float otherRX, float otherRW, float otherRY, float otherRZ, float otherSX, float _rcp1, float otherSY, float _rcp2, float otherSZ, float _rcp0) {
        difference_s11cc6446_c0_fma(_dst, _r7, _t30, _r8, _t31, _r9, 2.0f * java.lang.Math.fma(_sp2, _r7, -(_sp0 * _r8)), _sp1, 2.0f * java.lang.Math.fma(_sp3, _r7, -(_sp4 * _r6)), 2.0f * java.lang.Math.fma(_sp5, _r6, -(_sp3 * _r8)), 2.0f * java.lang.Math.fma(_sp4, _r8, -(_sp5 * _r7)), _sp3, _r6, _sp2, _sp5, _sp0, _sp4, otherRX, otherRW, otherRY, otherRZ);
        difference_s11cc6446_c1_fma(_dst, otherRY, _r9, otherRW, _r8, otherRZ, _r6, otherRX, _r7);
        _dst.sX = otherSX * _rcp1;
        _dst.sY = otherSY * _rcp2;
        _dst.sZ = otherSZ * _rcp0;
    }

    /** Private tail of {@code difference}; reached only through it. */
    private void difference_s11cc6446_tail_mulAdd(DoubleTransformImpl _dst, float _sp2, float _r7, float _sp0, float _r8, float _sp3, float _sp4, float _r6, float _sp5, float _t30, float _t31, float _r9, float _sp1, float otherRX, float otherRW, float otherRY, float otherRZ, float otherSX, float _rcp1, float otherSY, float _rcp2, float otherSZ, float _rcp0) {
        difference_s11cc6446_c0_mulAdd(_dst, _r7, _t30, _r8, _t31, _r9, 2.0f * ((_sp2) * (_r7) - (_sp0 * _r8)), _sp1, 2.0f * ((_sp3) * (_r7) - (_sp4 * _r6)), 2.0f * ((_sp5) * (_r6) - (_sp3 * _r8)), 2.0f * ((_sp4) * (_r8) - (_sp5 * _r7)), _sp3, _r6, _sp2, _sp5, _sp0, _sp4, otherRX, otherRW, otherRY, otherRZ);
        difference_s11cc6446_c1_mulAdd(_dst, otherRY, _r9, otherRW, _r8, otherRZ, _r6, otherRX, _r7);
        _dst.sX = otherSX * _rcp1;
        _dst.sY = otherSY * _rcp2;
        _dst.sZ = otherSZ * _rcp0;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r1 = this.tZ;
        float _r3 = this.tX;
        float _r5 = this.tY;
        float _r6 = this.rX;
        float _r7 = this.rZ;
        float _r8 = this.rY;
        float _r9 = this.rW;
        float _rcp0 = 1.0f / this.sZ;
        float _sp0 = otherTZ * _rcp0;
        float _rcp1 = 1.0f / this.sX;
        float _sp1 = otherTX * _rcp1;
        float _rcp2 = 1.0f / this.sY;
        float _sp2 = otherTY * _rcp2;
        if (Math.useFma()) difference_s11cc6446_tail_fma(d, _sp2, _r7, _sp0, _r8, _rcp1 * _r3, _rcp0 * _r1, _r6, _rcp2 * _r5, 2.0f * java.lang.Math.fma(_sp0, _r6, -(_sp1 * _r7)), 2.0f * java.lang.Math.fma(_sp1, _r8, -(_sp2 * _r6)), _r9, _sp1, otherRX, otherRW, otherRY, otherRZ, otherSX, _rcp1, otherSY, _rcp2, otherSZ, _rcp0); else difference_s11cc6446_tail_mulAdd(d, _sp2, _r7, _sp0, _r8, _rcp1 * _r3, _rcp0 * _r1, _r6, _rcp2 * _r5, 2.0f * ((_sp0) * (_r6) - (_sp1 * _r7)), 2.0f * ((_sp1) * (_r8) - (_sp2 * _r6)), _r9, _sp1, otherRX, otherRW, otherRY, otherRZ, otherSX, _rcp1, otherSY, _rcp2, otherSZ, _rcp0);
        return d;
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
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _rcp0 = 1.0f / this.sX;
        float _sp0 = this.tX * _rcp0;
        float _rcp1 = 1.0f / this.sZ;
        float _sp1 = this.tZ * _rcp1;
        float _rcp2 = 1.0f / this.sY;
        float _sp2 = this.tY * _rcp2;
        float _t0 = -this.rY;
        float _t1 = -this.rZ;
        float _t2 = -this.rX;
        float _t18 = 2.0f * Math.fma(_sp0, this.rZ, -(_sp1 * this.rX));
        float _t19 = 2.0f * Math.fma(_sp2, this.rX, -(_sp0 * this.rY));
        float _t20 = 2.0f * Math.fma(_sp1, this.rY, -(_sp2 * this.rZ));
        d.tX = Math.fma(this.rZ, _t18, Math.fma(_t0, _t19, Math.fma(this.rW, _t20, -_sp0)));
        d.tY = Math.fma(this.rX, _t19, Math.fma(_t1, _t20, Math.fma(this.rW, _t18, -_sp2)));
        d.tZ = Math.fma(this.rY, _t20, Math.fma(_t2, _t18, Math.fma(this.rW, _t19, -_sp1)));
        d.rX = _t2;
        d.rY = _t0;
        d.rZ = _t1;
        d.rW = this.rW;
        d.sX = _rcp0;
        d.sY = _rcp2;
        d.sZ = _rcp1;
        return d;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _rcp0 = 1.0f / this.sX;
        float _sp0 = this.tX * _rcp0;
        float _rcp1 = 1.0f / this.sZ;
        float _sp1 = this.tZ * _rcp1;
        float _rcp2 = 1.0f / this.sY;
        float _sp2 = this.tY * _rcp2;
        float _t0 = -this.rY;
        float _t1 = -this.rZ;
        float _t2 = -this.rX;
        float _t18 = 2.0f * Math.fma(_sp0, this.rZ, -(_sp1 * this.rX));
        float _t19 = 2.0f * Math.fma(_sp2, this.rX, -(_sp0 * this.rY));
        float _t20 = 2.0f * Math.fma(_sp1, this.rY, -(_sp2 * this.rZ));
        d.tX = Math.fma(this.rZ, _t18, Math.fma(_t0, _t19, Math.fma(this.rW, _t20, -_sp0)));
        d.tY = Math.fma(this.rX, _t19, Math.fma(_t1, _t20, Math.fma(this.rW, _t18, -_sp2)));
        d.tZ = Math.fma(this.rY, _t20, Math.fma(_t2, _t18, Math.fma(this.rW, _t19, -_sp1)));
        d.rX = _t2;
        d.rY = _t0;
        d.rZ = _t1;
        d.rW = this.rW;
        d.sX = _rcp0;
        d.sY = _rcp2;
        d.sZ = _rcp1;
        return d;
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
        FloatTransformImpl d = (FloatTransformImpl) dest;
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
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
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
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
     * Get the rotation of this transform and store the result in {@code dest}.
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = this.rX;
        d.y = this.rY;
        d.z = this.rZ;
        d.w = this.rW;
        return d;
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
        Float3Impl d = (Float3Impl) dest;
        d.x = this.sX;
        d.y = this.sY;
        d.z = this.sZ;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = this.sX;
        d.y = this.sY;
        d.z = this.sZ;
        return d;
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
        Float3Impl d = (Float3Impl) dest;
        d.x = this.tX;
        d.y = this.tY;
        d.z = this.tZ;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        d.x = this.tX;
        d.y = this.tY;
        d.z = this.tZ;
        return d;
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
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
    @Mutated public FloatTransform makeRotationAxis(float angle, float axisX, float axisY, float axisZ) {
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
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
        this.rX = _t1;
        this.rY = 0.0f;
        this.rZ = 0.0f;
        this.rW = Math.cosFromSin(_t1, _t0);
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
        this.rX = 0.0f;
        this.rY = _t1;
        this.rZ = 0.0f;
        this.rW = Math.cosFromSin(_t1, _t0);
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
        this.rX = 0.0f;
        this.rY = 0.0f;
        this.rZ = _t1;
        this.rW = Math.cosFromSin(_t1, _t0);
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
        this.sX = 1.0f;
        this.sY = 1.0f;
        this.sZ = 1.0f;
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
        float scaleX = scale.x();
        float scaleY = scale.y();
        float scaleZ = scale.z();
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
        this.rX = 0.0f;
        this.rY = 0.0f;
        this.rZ = 0.0f;
        this.rW = 1.0f;
        this.sX = scaleX;
        this.sY = scaleY;
        this.sZ = scaleZ;
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
    @Mutated public FloatTransform makeScaling(float scaleX, float scaleY, float scaleZ) {
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
        this.rX = 0.0f;
        this.rY = 0.0f;
        this.rZ = 0.0f;
        this.rW = 1.0f;
        this.sX = scaleX;
        this.sY = scaleY;
        this.sZ = scaleZ;
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
        this.tX = 0.0f;
        this.tY = 0.0f;
        this.tZ = 0.0f;
        this.rX = 0.0f;
        this.rY = 0.0f;
        this.rZ = 0.0f;
        this.rW = 1.0f;
        this.sX = scale;
        this.sY = scale;
        this.sZ = scale;
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
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.rZ;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = Math.fma(rotationX, this.rW, rotationW * _rd0) + Math.fma(rotationZ, _rd1, -(rotationY * _rd2));
        d.rY = Math.fma(rotationY, this.rW, rotationW * _rd1) + Math.fma(rotationX, _rd2, -(rotationZ * _rd0));
        d.rZ = Math.fma(rotationZ, this.rW, rotationW * _rd2) + Math.fma(rotationY, _rd0, -(rotationX * _rd1));
        d.rW = Math.fma(rotationW, this.rW, -(rotationX * _rd0)) - Math.fma(rotationY, _rd1, rotationZ * _rd2);
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = Math.fma(rotationX, this.rW, rotationW * this.rX) + Math.fma(rotationZ, this.rY, -(rotationY * this.rZ));
        d.rY = Math.fma(rotationY, this.rW, rotationW * this.rY) + Math.fma(rotationX, this.rZ, -(rotationZ * this.rX));
        d.rZ = Math.fma(rotationZ, this.rW, rotationW * this.rZ) + Math.fma(rotationY, this.rX, -(rotationX * this.rY));
        d.rW = Math.fma(rotationW, this.rW, -(rotationX * this.rX)) - Math.fma(rotationY, this.rY, rotationZ * this.rZ);
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        float _rd2 = this.rZ;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = Math.fma(rotationX, this.rW, rotationW * _rd0) + Math.fma(rotationZ, _rd1, -(rotationY * _rd2));
        d.rY = Math.fma(rotationY, this.rW, rotationW * _rd1) + Math.fma(rotationX, _rd2, -(rotationZ * _rd0));
        d.rZ = Math.fma(rotationZ, this.rW, rotationW * _rd2) + Math.fma(rotationY, _rd0, -(rotationX * _rd1));
        d.rW = Math.fma(rotationW, this.rW, -(rotationX * _rd0)) - Math.fma(rotationY, _rd1, rotationZ * _rd2);
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = Math.fma(rotationX, this.rW, rotationW * this.rX) + Math.fma(rotationZ, this.rY, -(rotationY * this.rZ));
        d.rY = Math.fma(rotationY, this.rW, rotationW * this.rY) + Math.fma(rotationX, this.rZ, -(rotationZ * this.rX));
        d.rZ = Math.fma(rotationZ, this.rW, rotationW * this.rZ) + Math.fma(rotationY, this.rX, -(rotationX * this.rY));
        d.rW = Math.fma(rotationW, this.rW, -(rotationX * this.rX)) - Math.fma(rotationY, this.rY, rotationZ * this.rZ);
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
    private void rotateAxis_s45c040b5_c0_fma(FloatTransformImpl _dst, float _r0, float _r1, float _r2, float _r3, float _t5, float _r4, float _t2, float _r5, float _t3, float _r6, float _t4) {
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
    private void rotateAxis_s45c040b5_c0_mulAdd(FloatTransformImpl _dst, float _r0, float _r1, float _r2, float _r3, float _t5, float _r4, float _t2, float _r5, float _t3, float _r6, float _t4) {
        _dst.tX = _r0;
        _dst.tY = _r1;
        _dst.tZ = _r2;
        _dst.rX = ((_r3) * (_t5) + (_r4 * _t2)) + ((_r5) * (_t3) - (_r6 * _t4));
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
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        if (Math.useFma()) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform rotateAxis_fma(float angle, float axisX, float axisY, float axisZ, @Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        rotateAxis_s45c040b5_c0_fma(d, _r0, _r1, _r2, _r3, _t5, _r4, _t2, _r5, _t3, _r6, _t4);
        preMul_s3e5a2933_c1_fma(d, _r5, _t5, _r4, _t4, _r6, _t2, _r3, _t3);
        d.sX = _r7;
        d.sY = _r8;
        d.sZ = _r9;
        return d;
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform rotateAxis_mulAdd(float angle, float axisX, float axisY, float axisZ, @Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        rotateAxis_s45c040b5_c0_mulAdd(d, _r0, _r1, _r2, _r3, _t5, _r4, _t2, _r5, _t3, _r6, _t4);
        preMul_s3e5a2933_c1_mulAdd(d, _r5, _t5, _r4, _t4, _r6, _t2, _r3, _t3);
        d.sX = _r7;
        d.sY = _r8;
        d.sZ = _r9;
        return d;
    }

    /**
     * Private store group 0 of {@code rotateAxis}: computes and stores it. Shared by the identical
     * private paths of {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ},
     * {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private void rotateAxis_s77293d04_c0_fma(DoubleTransformImpl _dst, float _r0, float _r1, float _r2, float _r3, float _t5, float _r4, float _t2, float _r5, float _t3, float _r6, float _t4) {
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
    private void rotateAxis_s77293d04_c0_mulAdd(DoubleTransformImpl _dst, float _r0, float _r1, float _r2, float _r3, float _t5, float _r4, float _t2, float _r5, float _t3, float _r6, float _t4) {
        _dst.tX = _r0;
        _dst.tY = _r1;
        _dst.tZ = _r2;
        _dst.rX = ((_r3) * (_t5) + (_r4 * _t2)) + ((_r5) * (_t3) - (_r6 * _t4));
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
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        if (Math.useFma()) return rotateAxis_fma(angle, axisX, axisY, axisZ, dest);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ, dest);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateAxis_fma(float angle, float axisX, float axisY, float axisZ, @Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        rotateAxis_s77293d04_c0_fma(d, _r0, _r1, _r2, _r3, _t5, _r4, _t2, _r5, _t3, _r6, _t4);
        preMul_s11cc6446_c1_fma(d, _r5, _t5, _r4, _t4, _r6, _t2, _r3, _t3);
        d.sX = _r7;
        d.sY = _r8;
        d.sZ = _r9;
        return d;
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateAxis_mulAdd(float angle, float axisX, float axisY, float axisZ, @Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        rotateAxis_s77293d04_c0_mulAdd(d, _r0, _r1, _r2, _r3, _t5, _r4, _t2, _r5, _t3, _r6, _t4);
        preMul_s11cc6446_c1_mulAdd(d, _r5, _t5, _r4, _t4, _r6, _t2, _r3, _t3);
        d.sX = _r7;
        d.sY = _r8;
        d.sZ = _r9;
        return d;
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
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = Math.fma(_rd0, _t2, this.rW * _t1);
        d.rY = Math.fma(_rd1, _t2, this.rZ * _t1);
        d.rZ = Math.fma(this.rZ, _t2, -(_rd1 * _t1));
        d.rW = Math.fma(this.rW, _t2, -(_rd0 * _t1));
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = Math.fma(this.rX, _t2, this.rW * _t1);
        d.rY = Math.fma(this.rY, _t2, this.rZ * _t1);
        d.rZ = Math.fma(this.rZ, _t2, -(this.rY * _t1));
        d.rW = Math.fma(this.rW, _t2, -(this.rX * _t1));
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void rotateXYZ_s7f3aa2ea_tail_fma(FloatTransformImpl _dst, float _t11, float _t8, float _t10, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _t19, float _r5, float _t20, float _r6, float _r7, float _r8, float _r9) {
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        rotateAxis_s45c040b5_c0_fma(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t20, _r6, _t22);
        preMul_s3e5a2933_c1_fma(_dst, _r5, _t21, _r4, _t22, _r6, _t19, _r3, _t20);
        _dst.sX = _r7;
        _dst.sY = _r8;
        _dst.sZ = _r9;
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void rotateXYZ_s7f3aa2ea_tail_mulAdd(FloatTransformImpl _dst, float _t11, float _t8, float _t10, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _t19, float _r5, float _t20, float _r6, float _r7, float _r8, float _r9) {
        float _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        rotateAxis_s45c040b5_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t20, _r6, _t22);
        preMul_s3e5a2933_c1_mulAdd(_dst, _r5, _t21, _r4, _t22, _r6, _t19, _r3, _t20);
        _dst.sX = _r7;
        _dst.sY = _r8;
        _dst.sZ = _r9;
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
        if (Math.useFma()) return rotateXYZ_fma(angleX, angleY, angleZ, dest);
        return rotateXYZ_mulAdd(angleX, angleY, angleZ, dest);
    }

    /** {@code rotateXYZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform rotateXYZ_fma(float angleX, float angleY, float angleZ, @Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateXYZ_s7f3aa2ea_tail_fma(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r4, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r5, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r6, _r7, _r8, _r9);
        return d;
    }

    /** {@code rotateXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform rotateXYZ_mulAdd(float angleX, float angleY, float angleZ, @Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateXYZ_s7f3aa2ea_tail_mulAdd(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, ((_t14) * (_t8) - (_t9 * _t5)), _r4, ((_t10) * (_t8) + (_t11 * _t5)), _r5, ((_t9) * (_t8) + (_t14 * _t5)), _r6, _r7, _r8, _r9);
        return d;
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void rotateXYZ_s6cfb216f_tail_fma(DoubleTransformImpl _dst, float _t11, float _t8, float _t10, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _t19, float _r5, float _t20, float _r6, float _r7, float _r8, float _r9) {
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        rotateAxis_s77293d04_c0_fma(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t20, _r6, _t22);
        preMul_s11cc6446_c1_fma(_dst, _r5, _t21, _r4, _t22, _r6, _t19, _r3, _t20);
        _dst.sX = _r7;
        _dst.sY = _r8;
        _dst.sZ = _r9;
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private void rotateXYZ_s6cfb216f_tail_mulAdd(DoubleTransformImpl _dst, float _t11, float _t8, float _t10, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _t19, float _r5, float _t20, float _r6, float _r7, float _r8, float _r9) {
        float _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        rotateAxis_s77293d04_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t20, _r6, _t22);
        preMul_s11cc6446_c1_mulAdd(_dst, _r5, _t21, _r4, _t22, _r6, _t19, _r3, _t20);
        _dst.sX = _r7;
        _dst.sY = _r8;
        _dst.sZ = _r9;
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
        if (Math.useFma()) return rotateXYZ_fma(angleX, angleY, angleZ, dest);
        return rotateXYZ_mulAdd(angleX, angleY, angleZ, dest);
    }

    /** {@code rotateXYZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateXYZ_fma(float angleX, float angleY, float angleZ, @Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateXYZ_s6cfb216f_tail_fma(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r4, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r5, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r6, _r7, _r8, _r9);
        return d;
    }

    /** {@code rotateXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateXYZ_mulAdd(float angleX, float angleY, float angleZ, @Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateXYZ_s6cfb216f_tail_mulAdd(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, ((_t14) * (_t8) - (_t9 * _t5)), _r4, ((_t10) * (_t8) + (_t11 * _t5)), _r5, ((_t9) * (_t8) + (_t14 * _t5)), _r6, _r7, _r8, _r9);
        return d;
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
        if (Math.useFma()) return rotateXZY_fma(angleX, angleZ, angleY, dest);
        return rotateXZY_mulAdd(angleX, angleZ, angleY, dest);
    }

    /** {@code rotateXZY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform rotateXZY_fma(float angleX, float angleZ, float angleY, @Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateXYZ_s7f3aa2ea_tail_fma(d, _t12, _t5, _t9, _t8, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r4, java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), _r5, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r6, _r7, _r8, _r9);
        return d;
    }

    /** {@code rotateXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform rotateXZY_mulAdd(float angleX, float angleZ, float angleY, @Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateXYZ_s7f3aa2ea_tail_mulAdd(d, _t12, _t5, _t9, _t8, _r0, _r1, _r2, _r3, ((_t9) * (_t5) + (_t12 * _t8)), _r4, ((_t10) * (_t8) - (_t11 * _t5)), _r5, ((_t10) * (_t5) + (_t11 * _t8)), _r6, _r7, _r8, _r9);
        return d;
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
        if (Math.useFma()) return rotateXZY_fma(angleX, angleZ, angleY, dest);
        return rotateXZY_mulAdd(angleX, angleZ, angleY, dest);
    }

    /** {@code rotateXZY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateXZY_fma(float angleX, float angleZ, float angleY, @Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateXYZ_s6cfb216f_tail_fma(d, _t12, _t5, _t9, _t8, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r4, java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), _r5, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r6, _r7, _r8, _r9);
        return d;
    }

    /** {@code rotateXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateXZY_mulAdd(float angleX, float angleZ, float angleY, @Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateXYZ_s6cfb216f_tail_mulAdd(d, _t12, _t5, _t9, _t8, _r0, _r1, _r2, _r3, ((_t9) * (_t5) + (_t12 * _t8)), _r4, ((_t10) * (_t8) - (_t11 * _t5)), _r5, ((_t10) * (_t5) + (_t11 * _t8)), _r6, _r7, _r8, _r9);
        return d;
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
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        float _rd0 = this.rX;
        float _rd1 = this.rY;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = Math.fma(_rd0, _t2, -(this.rZ * _t1));
        d.rY = Math.fma(_rd1, _t2, this.rW * _t1);
        d.rZ = Math.fma(_rd0, _t1, this.rZ * _t2);
        d.rW = Math.fma(this.rW, _t2, -(_rd1 * _t1));
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = Math.fma(this.rX, _t2, -(this.rZ * _t1));
        d.rY = Math.fma(this.rY, _t2, this.rW * _t1);
        d.rZ = Math.fma(this.rX, _t1, this.rZ * _t2);
        d.rW = Math.fma(this.rW, _t2, -(this.rY * _t1));
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        if (Math.useFma()) return rotateYXZ_fma(angleY, angleX, angleZ, dest);
        return rotateYXZ_mulAdd(angleY, angleX, angleZ, dest);
    }

    /** {@code rotateYXZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform rotateYXZ_fma(float angleY, float angleX, float angleZ, @Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateXYZ_s7f3aa2ea_tail_fma(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r4, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r5, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r6, _r7, _r8, _r9);
        return d;
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform rotateYXZ_mulAdd(float angleY, float angleX, float angleZ, @Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateXYZ_s7f3aa2ea_tail_mulAdd(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, ((_t9) * (_t5) + (_t12 * _t8)), _r4, ((_t10) * (_t8) + (_t11 * _t5)), _r5, ((_t12) * (_t5) - (_t9 * _t8)), _r6, _r7, _r8, _r9);
        return d;
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
        if (Math.useFma()) return rotateYXZ_fma(angleY, angleX, angleZ, dest);
        return rotateYXZ_mulAdd(angleY, angleX, angleZ, dest);
    }

    /** {@code rotateYXZ} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateYXZ_fma(float angleY, float angleX, float angleZ, @Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateXYZ_s6cfb216f_tail_fma(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r4, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r5, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r6, _r7, _r8, _r9);
        return d;
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateYXZ_mulAdd(float angleY, float angleX, float angleZ, @Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateXYZ_s6cfb216f_tail_mulAdd(d, _t11, _t8, _t10, _t5, _r0, _r1, _r2, _r3, ((_t9) * (_t5) + (_t12 * _t8)), _r4, ((_t10) * (_t8) + (_t11 * _t5)), _r5, ((_t12) * (_t5) - (_t9 * _t8)), _r6, _r7, _r8, _r9);
        return d;
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void rotateYZX_sbfa1aa_tail_fma(FloatTransformImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _t19, float _r5, float _r6, float _t20, float _r7, float _r8, float _r9) {
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        rotateAxis_s45c040b5_c0_fma(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t22, _r6, _t20);
        preMul_s3e5a2933_c1_fma(_dst, _r5, _t21, _r4, _t20, _r6, _t19, _r3, _t22);
        _dst.sX = _r7;
        _dst.sY = _r8;
        _dst.sZ = _r9;
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void rotateYZX_sbfa1aa_tail_mulAdd(FloatTransformImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _t19, float _r5, float _r6, float _t20, float _r7, float _r8, float _r9) {
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        rotateAxis_s45c040b5_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t22, _r6, _t20);
        preMul_s3e5a2933_c1_mulAdd(_dst, _r5, _t21, _r4, _t20, _r6, _t19, _r3, _t22);
        _dst.sX = _r7;
        _dst.sY = _r8;
        _dst.sZ = _r9;
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
        if (Math.useFma()) return rotateYZX_fma(angleY, angleZ, angleX, dest);
        return rotateYZX_mulAdd(angleY, angleZ, angleX, dest);
    }

    /** {@code rotateYZX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform rotateYZX_fma(float angleY, float angleZ, float angleX, @Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateYZX_sbfa1aa_tail_fma(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r4, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r5, _r6, java.lang.Math.fma(_t11, _t8, _t10 * _t5), _r7, _r8, _r9);
        return d;
    }

    /** {@code rotateYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform rotateYZX_mulAdd(float angleY, float angleZ, float angleX, @Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateYZX_sbfa1aa_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, ((_t14) * (_t8) - (_t9 * _t5)), _r4, ((_t9) * (_t8) + (_t14 * _t5)), _r5, _r6, ((_t11) * (_t8) + (_t10 * _t5)), _r7, _r8, _r9);
        return d;
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void rotateYZX_s1c15faaf_tail_fma(DoubleTransformImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _t19, float _r5, float _r6, float _t20, float _r7, float _r8, float _r9) {
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        rotateAxis_s77293d04_c0_fma(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t22, _r6, _t20);
        preMul_s11cc6446_c1_fma(_dst, _r5, _t21, _r4, _t20, _r6, _t19, _r3, _t22);
        _dst.sX = _r7;
        _dst.sY = _r8;
        _dst.sZ = _r9;
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private void rotateYZX_s1c15faaf_tail_mulAdd(DoubleTransformImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _t19, float _r5, float _r6, float _t20, float _r7, float _r8, float _r9) {
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        rotateAxis_s77293d04_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t19, _r5, _t22, _r6, _t20);
        preMul_s11cc6446_c1_mulAdd(_dst, _r5, _t21, _r4, _t20, _r6, _t19, _r3, _t22);
        _dst.sX = _r7;
        _dst.sY = _r8;
        _dst.sZ = _r9;
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
        if (Math.useFma()) return rotateYZX_fma(angleY, angleZ, angleX, dest);
        return rotateYZX_mulAdd(angleY, angleZ, angleX, dest);
    }

    /** {@code rotateYZX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateYZX_fma(float angleY, float angleZ, float angleX, @Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateYZX_s1c15faaf_tail_fma(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r4, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r5, _r6, java.lang.Math.fma(_t11, _t8, _t10 * _t5), _r7, _r8, _r9);
        return d;
    }

    /** {@code rotateYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateYZX_mulAdd(float angleY, float angleZ, float angleX, @Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateYZX_s1c15faaf_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, ((_t14) * (_t8) - (_t9 * _t5)), _r4, ((_t9) * (_t8) + (_t14 * _t5)), _r5, _r6, ((_t11) * (_t8) + (_t10 * _t5)), _r7, _r8, _r9);
        return d;
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
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        float _rd0 = this.rX;
        float _rd1 = this.rZ;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = Math.fma(_rd0, _t2, this.rY * _t1);
        d.rY = Math.fma(this.rY, _t2, -(_rd0 * _t1));
        d.rZ = Math.fma(_rd1, _t2, this.rW * _t1);
        d.rW = Math.fma(this.rW, _t2, -(_rd1 * _t1));
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = Math.fma(this.rX, _t2, this.rY * _t1);
        d.rY = Math.fma(this.rY, _t2, -(this.rX * _t1));
        d.rZ = Math.fma(this.rZ, _t2, this.rW * _t1);
        d.rW = Math.fma(this.rW, _t2, -(this.rZ * _t1));
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s711cb1aa_tail_fma(FloatTransformImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _r5, float _t19, float _r6, float _t20, float _r7, float _r8, float _r9) {
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        rotateAxis_s45c040b5_c0_fma(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t22, _r5, _t19, _r6, _t20);
        preMul_s3e5a2933_c1_fma(_dst, _r5, _t21, _r4, _t20, _r6, _t22, _r3, _t19);
        _dst.sX = _r7;
        _dst.sY = _r8;
        _dst.sZ = _r9;
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s711cb1aa_tail_mulAdd(FloatTransformImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _r5, float _t19, float _r6, float _t20, float _r7, float _r8, float _r9) {
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        rotateAxis_s45c040b5_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t22, _r5, _t19, _r6, _t20);
        preMul_s3e5a2933_c1_mulAdd(_dst, _r5, _t21, _r4, _t20, _r6, _t22, _r3, _t19);
        _dst.sX = _r7;
        _dst.sY = _r8;
        _dst.sZ = _r9;
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
        if (Math.useFma()) return rotateZXY_fma(angleZ, angleX, angleY, dest);
        return rotateZXY_mulAdd(angleZ, angleX, angleY, dest);
    }

    /** {@code rotateZXY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform rotateZXY_fma(float angleZ, float angleX, float angleY, @Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateZXY_s711cb1aa_tail_fma(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r4, _r5, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r6, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r7, _r8, _r9);
        return d;
    }

    /** {@code rotateZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform rotateZXY_mulAdd(float angleZ, float angleX, float angleY, @Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateZXY_s711cb1aa_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, ((_t14) * (_t8) - (_t9 * _t5)), _r4, _r5, ((_t10) * (_t5) + (_t11 * _t8)), _r6, ((_t9) * (_t8) + (_t14 * _t5)), _r7, _r8, _r9);
        return d;
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s375aeaaf_tail_fma(DoubleTransformImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _r5, float _t19, float _r6, float _t20, float _r7, float _r8, float _r9) {
        float _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        rotateAxis_s77293d04_c0_fma(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t22, _r5, _t19, _r6, _t20);
        preMul_s11cc6446_c1_fma(_dst, _r5, _t21, _r4, _t20, _r6, _t22, _r3, _t19);
        _dst.sX = _r7;
        _dst.sY = _r8;
        _dst.sZ = _r9;
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private void rotateZXY_s375aeaaf_tail_mulAdd(DoubleTransformImpl _dst, float _t10, float _t8, float _t11, float _t5, float _r0, float _r1, float _r2, float _r3, float _t21, float _r4, float _r5, float _t19, float _r6, float _t20, float _r7, float _r8, float _r9) {
        float _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        rotateAxis_s77293d04_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _t21, _r4, _t22, _r5, _t19, _r6, _t20);
        preMul_s11cc6446_c1_mulAdd(_dst, _r5, _t21, _r4, _t20, _r6, _t22, _r3, _t19);
        _dst.sX = _r7;
        _dst.sY = _r8;
        _dst.sZ = _r9;
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
        if (Math.useFma()) return rotateZXY_fma(angleZ, angleX, angleY, dest);
        return rotateZXY_mulAdd(angleZ, angleX, angleY, dest);
    }

    /** {@code rotateZXY} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateZXY_fma(float angleZ, float angleX, float angleY, @Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateZXY_s375aeaaf_tail_fma(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t14, _t8, -(_t9 * _t5)), _r4, _r5, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r6, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r7, _r8, _r9);
        return d;
    }

    /** {@code rotateZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateZXY_mulAdd(float angleZ, float angleX, float angleY, @Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateZXY_s375aeaaf_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, ((_t14) * (_t8) - (_t9 * _t5)), _r4, _r5, ((_t10) * (_t5) + (_t11 * _t8)), _r6, ((_t9) * (_t8) + (_t14 * _t5)), _r7, _r8, _r9);
        return d;
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
        if (Math.useFma()) return rotateZYX_fma(angleZ, angleY, angleX, dest);
        return rotateZYX_mulAdd(angleZ, angleY, angleX, dest);
    }

    /** {@code rotateZYX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform rotateZYX_fma(float angleZ, float angleY, float angleX, @Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateYZX_sbfa1aa_tail_fma(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r4, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r5, _r6, java.lang.Math.fma(_t11, _t8, _t10 * _t5), _r7, _r8, _r9);
        return d;
    }

    /** {@code rotateZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform rotateZYX_mulAdd(float angleZ, float angleY, float angleX, @Mutated FloatTransform dest) {
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateYZX_sbfa1aa_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, ((_t9) * (_t5) + (_t12 * _t8)), _r4, ((_t12) * (_t5) - (_t9 * _t8)), _r5, _r6, ((_t11) * (_t8) + (_t10 * _t5)), _r7, _r8, _r9);
        return d;
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
        if (Math.useFma()) return rotateZYX_fma(angleZ, angleY, angleX, dest);
        return rotateZYX_mulAdd(angleZ, angleY, angleX, dest);
    }

    /** {@code rotateZYX} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateZYX_fma(float angleZ, float angleY, float angleX, @Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateYZX_s1c15faaf_tail_fma(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r4, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r5, _r6, java.lang.Math.fma(_t11, _t8, _t10 * _t5), _r7, _r8, _r9);
        return d;
    }

    /** {@code rotateZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotateZYX_mulAdd(float angleZ, float angleY, float angleX, @Mutated DoubleTransform dest) {
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _r0 = this.tX;
        float _r1 = this.tY;
        float _r2 = this.tZ;
        float _r3 = this.rX;
        float _r4 = this.rW;
        float _r5 = this.rY;
        float _r6 = this.rZ;
        float _r7 = this.sX;
        float _r8 = this.sY;
        float _r9 = this.sZ;
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
        rotateYZX_s1c15faaf_tail_mulAdd(d, _t10, _t8, _t11, _t5, _r0, _r1, _r2, _r3, ((_t9) * (_t5) + (_t12 * _t8)), _r4, ((_t12) * (_t5) - (_t9 * _t8)), _r5, _r6, ((_t11) * (_t8) + (_t10 * _t5)), _r7, _r8, _r9);
        return d;
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
        float scaleX = scale.x();
        float scaleY = scale.y();
        float scaleZ = scale.z();
        FloatTransformImpl d = (FloatTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = scaleX * this.sX;
        d.sY = scaleY * this.sY;
        d.sZ = scaleZ * this.sZ;
        return d;
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
        float scaleX = scale.x();
        float scaleY = scale.y();
        float scaleZ = scale.z();
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = scaleX * this.sX;
        d.sY = scaleY * this.sY;
        d.sZ = scaleZ * this.sZ;
        return d;
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
        FloatTransformImpl d = (FloatTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = scaleX * this.sX;
        d.sY = scaleY * this.sY;
        d.sZ = scaleZ * this.sZ;
        return d;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = scaleX * this.sX;
        d.sY = scaleY * this.sY;
        d.sZ = scaleZ * this.sZ;
        return d;
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
        FloatTransformImpl d = (FloatTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = scale * this.sX;
        d.sY = scale * this.sY;
        d.sZ = scale * this.sZ;
        return d;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        d.tX = this.tX;
        d.tY = this.tY;
        d.tZ = this.tZ;
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = scale * this.sX;
        d.sY = scale * this.sY;
        d.sZ = scale * this.sZ;
        return d;
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
        FloatTransformImpl d = (FloatTransformImpl) dest;
        float _t0 = this.sY * translationY;
        float _t1 = this.sX * translationX;
        float _t2 = this.sZ * translationZ;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rY * _t1));
        float _t13 = 2.0f * Math.fma(this.rZ, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rY, _t2, -(this.rZ * _t0));
        d.tX = Math.fma(this.rY, _t12, Math.fma(-this.rZ, _t13, Math.fma(this.rW, _t14, Math.fma(this.sX, translationX, this.tX))));
        d.tY = Math.fma(this.rZ, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, Math.fma(this.sY, translationY, this.tY))));
        d.tZ = Math.fma(this.rX, _t13, Math.fma(-this.rY, _t14, Math.fma(this.rW, _t12, Math.fma(this.sZ, translationZ, this.tZ))));
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        DoubleTransformImpl d = (DoubleTransformImpl) dest;
        float _t0 = this.sY * translationY;
        float _t1 = this.sX * translationX;
        float _t2 = this.sZ * translationZ;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rY * _t1));
        float _t13 = 2.0f * Math.fma(this.rZ, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rY, _t2, -(this.rZ * _t0));
        d.tX = Math.fma(this.rY, _t12, Math.fma(-this.rZ, _t13, Math.fma(this.rW, _t14, Math.fma(this.sX, translationX, this.tX))));
        d.tY = Math.fma(this.rZ, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, Math.fma(this.sY, translationY, this.tY))));
        d.tZ = Math.fma(this.rX, _t13, Math.fma(-this.rY, _t14, Math.fma(this.rW, _t12, Math.fma(this.sZ, translationZ, this.tZ))));
        d.rX = this.rX;
        d.rY = this.rY;
        d.rZ = this.rZ;
        d.rW = this.rW;
        d.sX = this.sX;
        d.sY = this.sY;
        d.sZ = this.sZ;
        return d;
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
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.sY * vY;
        float _t1 = this.sX * vX;
        float _t2 = this.sZ * vZ;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rY * _t1));
        float _t13 = 2.0f * Math.fma(this.rZ, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rY, _t2, -(this.rZ * _t0));
        d.x = Math.fma(this.rY, _t12, Math.fma(-this.rZ, _t13, Math.fma(this.rW, _t14, Math.fma(this.sX, vX, this.tX))));
        d.y = Math.fma(this.rZ, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, Math.fma(this.sY, vY, this.tY))));
        d.z = Math.fma(this.rX, _t13, Math.fma(-this.rY, _t14, Math.fma(this.rW, _t12, Math.fma(this.sZ, vZ, this.tZ))));
        return d;
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
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.sY * vY;
        float _t1 = this.sX * vX;
        float _t2 = this.sZ * vZ;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rY * _t1));
        float _t13 = 2.0f * Math.fma(this.rZ, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rY, _t2, -(this.rZ * _t0));
        d.x = Math.fma(this.rY, _t12, Math.fma(-this.rZ, _t13, Math.fma(this.rW, _t14, Math.fma(this.sX, vX, this.tX))));
        d.y = Math.fma(this.rZ, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, Math.fma(this.sY, vY, this.tY))));
        d.z = Math.fma(this.rX, _t13, Math.fma(-this.rY, _t14, Math.fma(this.rW, _t12, Math.fma(this.sZ, vZ, this.tZ))));
        return d;
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
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.sY * vY;
        float _t1 = this.sX * vX;
        float _t2 = this.sZ * vZ;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rY * _t1));
        float _t13 = 2.0f * Math.fma(this.rZ, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rY, _t2, -(this.rZ * _t0));
        d.x = Math.fma(this.rY, _t12, Math.fma(-this.rZ, _t13, Math.fma(this.rW, _t14, Math.fma(this.sX, vX, this.tX))));
        d.y = Math.fma(this.rZ, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, Math.fma(this.sY, vY, this.tY))));
        d.z = Math.fma(this.rX, _t13, Math.fma(-this.rY, _t14, Math.fma(this.rW, _t12, Math.fma(this.sZ, vZ, this.tZ))));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.sY * vY;
        float _t1 = this.sX * vX;
        float _t2 = this.sZ * vZ;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rY * _t1));
        float _t13 = 2.0f * Math.fma(this.rZ, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rY, _t2, -(this.rZ * _t0));
        d.x = Math.fma(this.rY, _t12, Math.fma(-this.rZ, _t13, Math.fma(this.rW, _t14, Math.fma(this.sX, vX, this.tX))));
        d.y = Math.fma(this.rZ, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, Math.fma(this.sY, vY, this.tY))));
        d.z = Math.fma(this.rX, _t13, Math.fma(-this.rY, _t14, Math.fma(this.rW, _t12, Math.fma(this.sZ, vZ, this.tZ))));
        return d;
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
        Float3Impl d = (Float3Impl) dest;
        float _t0 = p.z() - this.tZ;
        float _t1 = p.x() - this.tX;
        float _t2 = p.y() - this.tY;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        d.x = Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1))) / this.sX;
        d.y = Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2))) / this.sY;
        d.z = Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0))) / this.sZ;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t0 = p.z() - this.tZ;
        float _t1 = p.x() - this.tX;
        float _t2 = p.y() - this.tY;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        d.x = Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1))) / this.sX;
        d.y = Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2))) / this.sY;
        d.z = Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0))) / this.sZ;
        return d;
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
        Float3Impl d = (Float3Impl) dest;
        float _t0 = pZ - this.tZ;
        float _t1 = pX - this.tX;
        float _t2 = pY - this.tY;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        d.x = Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1))) / this.sX;
        d.y = Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2))) / this.sY;
        d.z = Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0))) / this.sZ;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t0 = pZ - this.tZ;
        float _t1 = pX - this.tX;
        float _t2 = pY - this.tY;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        d.x = Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1))) / this.sX;
        d.y = Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2))) / this.sY;
        d.z = Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0))) / this.sZ;
        return d;
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
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.sY * vY;
        float _t1 = this.sX * vX;
        float _t2 = this.sZ * vZ;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rY * _t1));
        float _t13 = 2.0f * Math.fma(this.rZ, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rY, _t2, -(this.rZ * _t0));
        d.x = Math.fma(this.rY, _t12, Math.fma(-this.rZ, _t13, Math.fma(this.rW, _t14, Math.fma(this.sX, vX, this.tX))));
        d.y = Math.fma(this.rZ, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, Math.fma(this.sY, vY, this.tY))));
        d.z = Math.fma(this.rX, _t13, Math.fma(-this.rY, _t14, Math.fma(this.rW, _t12, Math.fma(this.sZ, vZ, this.tZ))));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.sY * vY;
        float _t1 = this.sX * vX;
        float _t2 = this.sZ * vZ;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rY * _t1));
        float _t13 = 2.0f * Math.fma(this.rZ, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rY, _t2, -(this.rZ * _t0));
        d.x = Math.fma(this.rY, _t12, Math.fma(-this.rZ, _t13, Math.fma(this.rW, _t14, Math.fma(this.sX, vX, this.tX))));
        d.y = Math.fma(this.rZ, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, Math.fma(this.sY, vY, this.tY))));
        d.z = Math.fma(this.rX, _t13, Math.fma(-this.rY, _t14, Math.fma(this.rW, _t12, Math.fma(this.sZ, vZ, this.tZ))));
        return d;
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
        Float3Impl d = (Float3Impl) dest;
        float _t0 = pZ - this.tZ;
        float _t1 = pX - this.tX;
        float _t2 = pY - this.tY;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        d.x = Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1))) / this.sX;
        d.y = Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2))) / this.sY;
        d.z = Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0))) / this.sZ;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t0 = pZ - this.tZ;
        float _t1 = pX - this.tX;
        float _t2 = pY - this.tY;
        float _t12 = 2.0f * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        d.x = Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1))) / this.sX;
        d.y = Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2))) / this.sY;
        d.z = Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0))) / this.sZ;
        return d;
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
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.sZ * vZ;
        float _t1 = this.sY * vY;
        float _t2 = this.sX * vX;
        float _t12 = 2.0f * Math.fma(this.rY, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rX, _t1, -(this.rY * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rX * _t0));
        d.x = Math.fma(this.sX, vX, Math.fma(this.rW, _t12, Math.fma(this.rY, _t13, -(this.rZ * _t14))));
        d.y = Math.fma(this.sY, vY, Math.fma(this.rW, _t14, Math.fma(this.rZ, _t12, -(this.rX * _t13))));
        d.z = Math.fma(this.sZ, vZ, Math.fma(this.rW, _t13, Math.fma(this.rX, _t14, -(this.rY * _t12))));
        return d;
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
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.sZ * vZ;
        float _t1 = this.sY * vY;
        float _t2 = this.sX * vX;
        float _t12 = 2.0f * Math.fma(this.rY, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rX, _t1, -(this.rY * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rX * _t0));
        d.x = Math.fma(this.sX, vX, Math.fma(this.rW, _t12, Math.fma(this.rY, _t13, -(this.rZ * _t14))));
        d.y = Math.fma(this.sY, vY, Math.fma(this.rW, _t14, Math.fma(this.rZ, _t12, -(this.rX * _t13))));
        d.z = Math.fma(this.sZ, vZ, Math.fma(this.rW, _t13, Math.fma(this.rX, _t14, -(this.rY * _t12))));
        return d;
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
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.sZ * vZ;
        float _t1 = this.sY * vY;
        float _t2 = this.sX * vX;
        float _t12 = 2.0f * Math.fma(this.rY, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rX, _t1, -(this.rY * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rX * _t0));
        d.x = Math.fma(this.sX, vX, Math.fma(this.rW, _t12, Math.fma(this.rY, _t13, -(this.rZ * _t14))));
        d.y = Math.fma(this.sY, vY, Math.fma(this.rW, _t14, Math.fma(this.rZ, _t12, -(this.rX * _t13))));
        d.z = Math.fma(this.sZ, vZ, Math.fma(this.rW, _t13, Math.fma(this.rX, _t14, -(this.rY * _t12))));
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.sZ * vZ;
        float _t1 = this.sY * vY;
        float _t2 = this.sX * vX;
        float _t12 = 2.0f * Math.fma(this.rY, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * Math.fma(this.rX, _t1, -(this.rY * _t2));
        float _t14 = 2.0f * Math.fma(this.rZ, _t2, -(this.rX * _t0));
        d.x = Math.fma(this.sX, vX, Math.fma(this.rW, _t12, Math.fma(this.rY, _t13, -(this.rZ * _t14))));
        d.y = Math.fma(this.sY, vY, Math.fma(this.rW, _t14, Math.fma(this.rZ, _t12, -(this.rX * _t13))));
        d.z = Math.fma(this.sZ, vZ, Math.fma(this.rW, _t13, Math.fma(this.rX, _t14, -(this.rY * _t12))));
        return d;
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
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * Math.fma(this.rZ, vY, -(this.rY * vZ));
        d.x = Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX))) / this.sX;
        d.y = Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY))) / this.sY;
        d.z = Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ))) / this.sZ;
        return d;
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
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * Math.fma(this.rZ, vY, -(this.rY * vZ));
        d.x = Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX))) / this.sX;
        d.y = Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY))) / this.sY;
        d.z = Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ))) / this.sZ;
        return d;
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
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * Math.fma(this.rZ, vY, -(this.rY * vZ));
        d.x = Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX))) / this.sX;
        d.y = Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY))) / this.sY;
        d.z = Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ))) / this.sZ;
        return d;
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
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * Math.fma(this.rZ, vY, -(this.rY * vZ));
        d.x = Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX))) / this.sX;
        d.y = Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY))) / this.sY;
        d.z = Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ))) / this.sZ;
        return d;
    }

    public float tX() { return this.tX; }
    public float tY() { return this.tY; }
    public float tZ() { return this.tZ; }
    public float rX() { return this.rX; }
    public float rY() { return this.rY; }
    public float rZ() { return this.rZ; }
    public float rW() { return this.rW; }
    public float sX() { return this.sX; }
    public float sY() { return this.sY; }
    public float sZ() { return this.sZ; }

    @Override public String toString() {
        return "FloatTransform(" + tX() + ", " + tY() + ", " + tZ() + ", " + rX() + ", " + rY() + ", " + rZ() + ", " + rW() + ", " + sX() + ", " + sY() + ", " + sZ() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatTransformImpl)) return false;
        FloatTransformImpl o = (FloatTransformImpl) obj;
        return Float.floatToIntBits(tX) == Float.floatToIntBits(o.tX)
            && Float.floatToIntBits(tY) == Float.floatToIntBits(o.tY)
            && Float.floatToIntBits(tZ) == Float.floatToIntBits(o.tZ)
            && Float.floatToIntBits(rX) == Float.floatToIntBits(o.rX)
            && Float.floatToIntBits(rY) == Float.floatToIntBits(o.rY)
            && Float.floatToIntBits(rZ) == Float.floatToIntBits(o.rZ)
            && Float.floatToIntBits(rW) == Float.floatToIntBits(o.rW)
            && Float.floatToIntBits(sX) == Float.floatToIntBits(o.sX)
            && Float.floatToIntBits(sY) == Float.floatToIntBits(o.sY)
            && Float.floatToIntBits(sZ) == Float.floatToIntBits(o.sZ);
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
        h = 31 * h + Float.floatToIntBits(sX);
        h = 31 * h + Float.floatToIntBits(sY);
        h = 31 * h + Float.floatToIntBits(sZ);
        return h;
    }

    @Override public boolean isFinite() {
        return Float.isFinite(tX)
            && Float.isFinite(tY)
            && Float.isFinite(tZ)
            && Float.isFinite(rX)
            && Float.isFinite(rY)
            && Float.isFinite(rZ)
            && Float.isFinite(rW)
            && Float.isFinite(sX)
            && Float.isFinite(sY)
            && Float.isFinite(sZ);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(tX)
            || Float.isNaN(tY)
            || Float.isNaN(tZ)
            || Float.isNaN(rX)
            || Float.isNaN(rY)
            || Float.isNaN(rZ)
            || Float.isNaN(rW)
            || Float.isNaN(sX)
            || Float.isNaN(sY)
            || Float.isNaN(sZ);
    }

    @Override public boolean equalsEpsilon(FloatTransformR other, float epsilon) {
        return java.lang.Math.abs(tX - other.tX()) <= epsilon
            && java.lang.Math.abs(tY - other.tY()) <= epsilon
            && java.lang.Math.abs(tZ - other.tZ()) <= epsilon
            && java.lang.Math.abs(rX - other.rX()) <= epsilon
            && java.lang.Math.abs(rY - other.rY()) <= epsilon
            && java.lang.Math.abs(rZ - other.rZ()) <= epsilon
            && java.lang.Math.abs(rW - other.rW()) <= epsilon
            && java.lang.Math.abs(sX - other.sX()) <= epsilon
            && java.lang.Math.abs(sY - other.sY()) <= epsilon
            && java.lang.Math.abs(sZ - other.sZ()) <= epsilon;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = this.tX;
        dest[offset + 1] = this.tY;
        dest[offset + 2] = this.tZ;
        dest[offset + 3] = this.rX;
        dest[offset + 4] = this.rY;
        dest[offset + 5] = this.rZ;
        dest[offset + 6] = this.rW;
        dest[offset + 7] = this.sX;
        dest[offset + 8] = this.sY;
        dest[offset + 9] = this.sZ;
        return dest;
    }
    public @Mutated FloatTransform load(float[] src, int offset) {
        this.tX = src[offset];
        this.tY = src[offset + 1];
        this.tZ = src[offset + 2];
        this.rX = src[offset + 3];
        this.rY = src[offset + 4];
        this.rZ = src[offset + 5];
        this.rW = src[offset + 6];
        this.sX = src[offset + 7];
        this.sY = src[offset + 8];
        this.sZ = src[offset + 9];
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
    @Mutated public FloatTransform load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatTransform loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatTransform loadRelative(FloatBuffer buf) {
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
        if (buf.remaining() < 40) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 40);
        return buf;
    }
    public FloatTransform load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public FloatTransform loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public FloatTransform loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 40) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatTransform r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 40);
        return r;
    }
    public FloatTransform storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public FloatTransform loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public FloatTransform load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public FloatTransform load(long offset, MemorySegment src) {
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
        dest[offset + 7] = this.sX;
        dest[offset + 8] = this.sY;
        dest[offset + 9] = this.sZ;
        return dest;
    }
    public @Mutated FloatTransform load(double[] src, int offset) {
        this.tX = (float) src[offset];
        this.tY = (float) src[offset + 1];
        this.tZ = (float) src[offset + 2];
        this.rX = (float) src[offset + 3];
        this.rY = (float) src[offset + 4];
        this.rZ = (float) src[offset + 5];
        this.rW = (float) src[offset + 6];
        this.sX = (float) src[offset + 7];
        this.sY = (float) src[offset + 8];
        this.sZ = (float) src[offset + 9];
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
    @Mutated public FloatTransform load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatTransform loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatTransform loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 10) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 10);
        return this;
    }
    public ByteBuffer storeDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 80) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 80);
        return buf;
    }
    public FloatTransform loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }
    public FloatTransform loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public FloatTransform loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 80) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatTransform r = StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 80);
        return r;
    }
    public FloatTransform storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public FloatTransform loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }
    public MemorySegment storeDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }
    public MemorySegment storeDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }
    @Mutated public FloatTransform loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(this, 0L, src); }
    public FloatTransform loadDouble(long offset, MemorySegment src) {
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
