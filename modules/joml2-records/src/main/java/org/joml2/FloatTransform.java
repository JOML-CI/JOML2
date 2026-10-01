// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;
import java.nio.ByteOrder;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

/**
 * Immutable transform of single-precision {@code float} components.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance.
 * <p>
 * Its rotation is a unit quaternion. Every operation that applies, composes, inverts or converts
 * this transform assumes its rotation has unit length and does not divide it out. A value that has
 * drifted from unit length (after many multiplications, say) gives wrong results rather than an
 * error: {@code normalize} it first.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns).
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal to an equal infinity (the difference {@code Inf - Inf} is NaN), and to a finite
 * one only under an infinite {@code epsilon}; a NaN component never compares equal to anything.
 *
 * @param tX the {@code tX} component
 * @param tY the {@code tY} component
 * @param tZ the {@code tZ} component
 * @param rX the {@code rX} component
 * @param rY the {@code rY} component
 * @param rZ the {@code rZ} component
 * @param rW the {@code rW} component
 * @param sX the {@code sX} component
 * @param sY the {@code sY} component
 * @param sZ the {@code sZ} component
 */
public record FloatTransform(float tX, float tY, float tZ, float rX, float rY, float rZ, float rW, float sX, float sY, float sZ) {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 40;

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
     * @param sX the {@code sX} component
     * @param sY the {@code sY} component
     * @param sZ the {@code sZ} component
     */
    public FloatTransform(float tX, float tY, float tZ, float rX, float rY, float rZ, float rW, float sX, float sY, float sZ) {
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

    /**
     * Create a new instance initialized to the identity transform.
     * <p>
     * Valid input: the method reads no input.
     */
    public FloatTransform() {
        this(0, 0, 0, 0, 0, 0, 1, 1, 1, 1);
    }

    /**
     * Create a transform from its translation, rotation and scale.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param translation the translation
     * @param rotation the rotation quaternion, taken as given (not normalized)
     * @param scale the scale along each local axis
     */
    public FloatTransform(Float3 translation, FloatQuat rotation, Float3 scale) {
        this(translation.x(), translation.y(), translation.z(), rotation.x(), rotation.y(), rotation.z(), rotation.w(), scale.x(), scale.y(), scale.z());
    }

    /**
     * Create an array of {@code length} elements, each the instance with all components 0.
     * <p>
     * An ordinary array, filled: the value variant ({@code joml2-value}) hands out a flat one,
     * elements stored inline, where the application allows it.
     * <p>
     * Valid input: {@code length} must not be negative.
     *
     * @param length the number of elements
     * @return the new array
     * @throws NegativeArraySizeException if {@code length} is negative
     */
    public static FloatTransform[] newArray(int length) {
        FloatTransform[] a = new FloatTransform[length];
        java.util.Arrays.fill(a, new FloatTransform(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        return a;
    }

    /**
     * Create an array of {@code length} elements, each {@code init}.
     * <p>
     * An ordinary array, filled: the value variant ({@code joml2-value}) hands out a flat one,
     * elements stored inline, where the application allows it.
     * <p>
     * Valid input: {@code length} must not be negative.
     *
     * @param length the number of elements
     * @param init the value every element starts as
     * @return the new array
     * @throws NullPointerException if {@code init} is null
     * @throws NegativeArraySizeException if {@code length} is negative
     */
    public static FloatTransform[] newArray(int length, FloatTransform init) {
        java.util.Objects.requireNonNull(init, "init");
        FloatTransform[] a = new FloatTransform[length];
        java.util.Arrays.fill(a, init);
        return a;
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

    /** {@return the {@code sX} component} <p>Valid input: any value, NaN and the infinities included. */
    public float sX() { return sX; }

    /** {@return the {@code sY} component} <p>Valid input: any value, NaN and the infinities included. */
    public float sY() { return sY; }

    /** {@return the {@code sZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public float sZ() { return sZ; }

    /**
     * Create a new transform from its translation, rotation and scale.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param translation the translation
     * @param rotation the rotation quaternion, taken as given (not normalized)
     * @param scale the scale along each local axis
     * @return the resulting transform
     */
    public FloatTransform set(Float3 translation, FloatQuat rotation, Float3 scale) {
        return new FloatTransform(translation, rotation, scale);
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
     * @return the resulting transform
     */
    public static FloatTransform makeFromAxisAngle(Float3 axis, float angle, Float3 translation) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        return new FloatTransform(translationX, translationY, translationZ, axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosWithSin(_t0), 1.0f, 1.0f, 1.0f);
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
     * @return the resulting transform
     */
    public static FloatTransform makeFromAxisAngle(float axisX, float axisY, float axisZ, float angle, float translationX, float translationY, float translationZ) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        return new FloatTransform(translationX, translationY, translationZ, axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosWithSin(_t0), 1.0f, 1.0f, 1.0f);
    }

    /**
     * Create a rigid transformation that first rotates by {@code rotation} and then translates by
     * {@code translation} ({@code T * R}).
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param translation the translation
     * @param rotation the rotation
     * @return the resulting transform
     */
    public static FloatTransform makeTranslationRotation(Float3 translation, FloatQuat rotation) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        return new FloatTransform(translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, 1.0f, 1.0f, 1.0f);
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
     * @return the resulting transform
     */
    public static FloatTransform makeTranslationRotation(float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW) {
        return new FloatTransform(translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Create a transformation composed of the given translation, rotation and scale, applied in
     * scale-rotation-translation order.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param translation the translation
     * @param rotation the rotation
     * @param scale the scale factors
     * @return the resulting transform
     */
    public static FloatTransform makeTranslationRotationScale(Float3 translation, FloatQuat rotation, Float3 scale) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        float scaleX = scale.x();
        float scaleY = scale.y();
        float scaleZ = scale.z();
        return new FloatTransform(translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
    }

    /**
     * Create a transformation composed of the given translation, rotation and scale, applied in
     * scale-rotation-translation order.
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
     * @return the resulting transform
     */
    public static FloatTransform makeTranslationRotationScale(float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        return new FloatTransform(translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
    }

    /**
     * Return a transform with the values of {@code v}: that transform itself (an immutable value
     * needs no copy).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the transform to copy
     * @return the resulting transform
     */
    public FloatTransform set(FloatTransform v) {
        float vTX = v.tX;
        float vTY = v.tY;
        float vTZ = v.tZ;
        float vRX = v.rX;
        float vRY = v.rY;
        float vRZ = v.rZ;
        float vRW = v.rW;
        float vSX = v.sX;
        float vSY = v.sY;
        float vSZ = v.sZ;
        return new FloatTransform(vTX, vTY, vTZ, vRX, vRY, vRZ, vRW, vSX, vSY, vSZ);
    }

    /**
     * Create a new transform from the given values.
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
     * @return the resulting transform
     */
    public FloatTransform set(float vTX, float vTY, float vTZ, float vRX, float vRY, float vRZ, float vRW, float vSX, float vSY, float vSZ) {
        return new FloatTransform(vTX, vTY, vTZ, vRX, vRY, vRZ, vRW, vSX, vSY, vSZ);
    }

    /**
     * Set the rotation of this transform to {@code r}, returning the result as a value.
     * <p>
     * Valid input: {@code r} must have unit length.
     *
     * @param r the new rotation
     * @return the resulting transform
     */
    public FloatTransform setRotation(FloatQuat r) {
        float rX = r.x();
        float rY = r.y();
        float rZ = r.z();
        float rW = r.w();
        return new FloatTransform(this.tX, this.tY, this.tZ, rX, rY, rZ, rW, this.sX, this.sY, this.sZ);
    }

    /**
     * Set the rotation of this transform to ({@code rX}, {@code rY}, {@code rZ}, {@code rW}),
     * returning the result as a value.
     * <p>
     * Valid input: {@code (rX, rY, rZ, rW)} must have unit length.
     *
     * @param rX the {@code x} component of the quaternion {@code (rX, rY, rZ, rW)}
     * @param rY the {@code y} component of the quaternion {@code (rX, rY, rZ, rW)}
     * @param rZ the {@code z} component of the quaternion {@code (rX, rY, rZ, rW)}
     * @param rW the {@code w} component of the quaternion {@code (rX, rY, rZ, rW)}
     * @return the resulting transform
     */
    public FloatTransform setRotation(float rX, float rY, float rZ, float rW) {
        return new FloatTransform(this.tX, this.tY, this.tZ, rX, rY, rZ, rW, this.sX, this.sY, this.sZ);
    }

    /**
     * Set the scale of this transform to {@code s}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @return the resulting transform
     */
    public FloatTransform setScale(Float3 s) {
        float sX = s.x();
        float sY = s.y();
        float sZ = s.z();
        return new FloatTransform(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW, sX, sY, sZ);
    }

    /**
     * Set the scale of this transform to ({@code sX}, {@code sY}, {@code sZ}), returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sX the {@code x} component of the vector {@code (sX, sY, sZ)}
     * @param sY the {@code y} component of the vector {@code (sX, sY, sZ)}
     * @param sZ the {@code z} component of the vector {@code (sX, sY, sZ)}
     * @return the resulting transform
     */
    public FloatTransform setScale(float sX, float sY, float sZ) {
        return new FloatTransform(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW, sX, sY, sZ);
    }

    /**
     * Set the scale of this transform to {@code uniform}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param uniform the uniform scale factor
     * @return the resulting transform
     */
    public FloatTransform setScale(float uniform) {
        return new FloatTransform(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW, uniform, uniform, uniform);
    }

    /**
     * Set the translation of this transform to {@code t}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the translation vector
     * @return the resulting transform
     */
    public FloatTransform setTranslation(Float3 t) {
        float tX = t.x();
        float tY = t.y();
        float tZ = t.z();
        return new FloatTransform(tX, tY, tZ, this.rX, this.rY, this.rZ, this.rW, this.sX, this.sY, this.sZ);
    }

    /**
     * Set the translation of this transform to ({@code tX}, {@code tY}, {@code tZ}), returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param tX the {@code x} component of the vector {@code (tX, tY, tZ)}
     * @param tY the {@code y} component of the vector {@code (tX, tY, tZ)}
     * @param tZ the {@code z} component of the vector {@code (tX, tY, tZ)}
     * @return the resulting transform
     */
    public FloatTransform setTranslation(float tX, float tY, float tZ) {
        return new FloatTransform(tX, tY, tZ, this.rX, this.rY, this.rZ, this.rW, this.sX, this.sY, this.sZ);
    }

    /**
     * Create the rigid motion of the unit dual quaternion {@code dq} (its translation and rotation,
     * scale = 1).
     * <p>
     * Valid input: {@code dq} must be a unit dual quaternion.
     *
     * @param dq the dual quaternion to convert
     * @return the resulting transform
     */
    public static FloatTransform makeFromDualQuat(FloatDualQuat dq) {
        float dqRX = dq.rX();
        float dqRY = dq.rY();
        float dqRZ = dq.rZ();
        float dqRW = dq.rW();
        float dqDX = dq.dX();
        float dqDY = dq.dY();
        float dqDZ = dq.dZ();
        float dqDW = dq.dW();
        if (org.joml2.internal.FmaSwitch.ON) {
            return new FloatTransform(2.0f * (java.lang.Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + java.lang.Math.fma(dqRW, dqDX, dqRX * -dqDW)), 2.0f * (java.lang.Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + java.lang.Math.fma(dqRW, dqDY, dqRY * -dqDW)), 2.0f * (java.lang.Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + java.lang.Math.fma(dqRW, dqDZ, dqRZ * -dqDW)), dqRX, dqRY, dqRZ, dqRW, 1.0f, 1.0f, 1.0f);
        } else {
            return new FloatTransform(2.0f * (((dqRY) * (dqDZ) - (dqRZ * dqDY)) + ((dqRW) * (dqDX) - (dqRX * dqDW))), 2.0f * (((dqRZ) * (dqDX) - (dqRX * dqDZ)) + ((dqRW) * (dqDY) - (dqRY * dqDW))), 2.0f * (((dqRX) * (dqDY) - (dqRY * dqDX)) + ((dqRW) * (dqDZ) - (dqRZ * dqDW))), dqRX, dqRY, dqRZ, dqRW, 1.0f, 1.0f, 1.0f);
        }
    }

    /**
     * Create the rigid motion of the unit dual quaternion ({@code dqRX}, {@code dqRY},
     * {@code dqRZ}, {@code dqRW}, {@code dqDX}, {@code dqDY}, {@code dqDZ}, {@code dqDW}) (its
     * translation and rotation, scale = 1).
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
     * @return the resulting transform
     */
    public static FloatTransform makeFromDualQuat(float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        if (org.joml2.internal.FmaSwitch.ON) {
            return new FloatTransform(2.0f * (java.lang.Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + java.lang.Math.fma(dqRW, dqDX, dqRX * -dqDW)), 2.0f * (java.lang.Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + java.lang.Math.fma(dqRW, dqDY, dqRY * -dqDW)), 2.0f * (java.lang.Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + java.lang.Math.fma(dqRW, dqDZ, dqRZ * -dqDW)), dqRX, dqRY, dqRZ, dqRW, 1.0f, 1.0f, 1.0f);
        } else {
            return new FloatTransform(2.0f * (((dqRY) * (dqDZ) - (dqRZ * dqDY)) + ((dqRW) * (dqDX) - (dqRX * dqDW))), 2.0f * (((dqRZ) * (dqDX) - (dqRX * dqDZ)) + ((dqRW) * (dqDY) - (dqRY * dqDW))), 2.0f * (((dqRX) * (dqDY) - (dqRY * dqDX)) + ((dqRW) * (dqDZ) - (dqRZ * dqDW))), dqRX, dqRY, dqRZ, dqRW, 1.0f, 1.0f, 1.0f);
        }
    }

    /**
     * Create the decomposition of the given matrix's linear {@code R * S} block, with zero
     * translation (scale is removed by normalizing the columns, but shear is not removed: a sheared
     * block yields a rotation quaternion that is not unit length; a mirroring block (negative
     * determinant) gives a negative x scale, and the rotation of the block with its first column
     * negated).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return the resulting transform
     */
    public static FloatTransform makeFromMatrix(Float3x3 m) {
        if (org.joml2.internal.FmaSwitch.ON) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static FloatTransform makeFromMatrix_fma(Float3x3 m) {
        float _m_m21 = m.m21();
        float _m_m01 = m.m01();
        float _m_m11 = m.m11();
        float _t12 = java.lang.Math.fma(_m_m21, _m_m21, java.lang.Math.fma(_m_m01, _m_m01, _m_m11 * _m_m11));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _m_m22 = m.m22();
        float _m_m02 = m.m02();
        float _m_m12 = m.m12();
        float _t13 = java.lang.Math.fma(_m_m22, _m_m22, java.lang.Math.fma(_m_m02, _m_m02, _m_m12 * _m_m12));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _m_m20 = m.m20();
        float _m_m00 = m.m00();
        float _m_m10 = m.m10();
        float _t14 = java.lang.Math.fma(_m_m20, _m_m20, java.lang.Math.fma(_m_m00, _m_m00, _m_m10 * _m_m10));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        float _sfx8 = (float) java.lang.Math.sqrt(_t12);
        float _t15 = 1.0f / _sfx8;
        float _sfx9 = (float) java.lang.Math.sqrt(_t13);
        return makeFromMatrix_s6b8df64f_1_fma(m, -_m_m11, -_m_m22, _t18, _m_m10 * _t17, _m_m20 * _t17, _m_m00 * _t17, 0.0f, 0.0f, 0.0f, _sfx8, _t15, _m_m21 * _t15, _m_m11 * _t15, _sfx9, 1.0f / _sfx9);
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static FloatTransform makeFromMatrix_mulAdd(Float3x3 m) {
        float _m_m21 = m.m21();
        float _m_m01 = m.m01();
        float _m_m11 = m.m11();
        float _t12 = ((_m_m21) * (_m_m21) + (((_m_m01) * (_m_m01) + (_m_m11 * _m_m11))));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _m_m22 = m.m22();
        float _m_m02 = m.m02();
        float _m_m12 = m.m12();
        float _t13 = ((_m_m22) * (_m_m22) + (((_m_m02) * (_m_m02) + (_m_m12 * _m_m12))));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _m_m20 = m.m20();
        float _m_m00 = m.m00();
        float _m_m10 = m.m10();
        float _t14 = ((_m_m20) * (_m_m20) + (((_m_m00) * (_m_m00) + (_m_m10 * _m_m10))));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        float _sfx8 = (float) java.lang.Math.sqrt(_t12);
        float _t15 = 1.0f / _sfx8;
        float _sfx9 = (float) java.lang.Math.sqrt(_t13);
        return makeFromMatrix_s6b8df64f_1_mulAdd(m, -_m_m11, -_m_m22, _t18, _m_m10 * _t17, _m_m20 * _t17, _m_m00 * _t17, 0.0f, 0.0f, 0.0f, _sfx8, _t15, _m_m21 * _t15, _m_m11 * _t15, _sfx9, 1.0f / _sfx9);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatTransform makeFromMatrix_s6b8df64f_1_fma(Float3x3 m, float _t0, float _t1, float _t18, float _t19, float _t22, float _t27, float _sfx0, float _sfx1, float _sfx2, float _sfx8, float _t15, float _t24, float _t25, float _sfx9, float _t16) {
        float _m_m22 = m.m22();
        float _t20 = _m_m22 * _t16;
        float _m_m12 = m.m12();
        float _t21 = _m_m12 * _t16;
        float _t48, _t49, _t50, _sfx7;
        float _m_m01 = m.m01();
        float _m_m02 = m.m02();
        if (java.lang.Math.fma(-java.lang.Math.fma(_t19, _t20, -(_t21 * _t22)), _m_m01 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t19, _t24, -(_t25 * _t22)), _m_m02 * _t16, java.lang.Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27)) < 0.0f) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
            _sfx7 = -_t18;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
            _sfx7 = _t18;
        }
        float _t52 = 1.0f + _t48;
        float _t64 = java.lang.Math.fma(m.m11(), _t15, java.lang.Math.fma(_m_m22, _t16, _t52));
        return makeFromMatrix_s6b8df64f_2_fma(m, _t0, _t1, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, java.lang.Math.fma(_m_m12, _t16, _t24), java.lang.Math.fma(m.m21(), _t15, -_t21), _t48, _sfx7, _t52, 1.0f - _t48, java.lang.Math.fma(_m_m01, _t15, _t49), java.lang.Math.fma(_m_m02, _t16, _t50), java.lang.Math.fma(_m_m02, _t16, -_t50), java.lang.Math.fma(-_m_m01, _t15, _t49), _t64, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatTransform makeFromMatrix_s6b8df64f_1_mulAdd(Float3x3 m, float _t0, float _t1, float _t18, float _t19, float _t22, float _t27, float _sfx0, float _sfx1, float _sfx2, float _sfx8, float _t15, float _t24, float _t25, float _sfx9, float _t16) {
        float _m_m22 = m.m22();
        float _t20 = _m_m22 * _t16;
        float _m_m12 = m.m12();
        float _t21 = _m_m12 * _t16;
        float _t48, _t49, _t50, _sfx7;
        float _m_m02 = m.m02();
        float _m_m01 = m.m01();
        if (((((((_t19) * (_t24) - (_t25 * _t22))) * (_m_m02 * _t16) + (((_t25) * (_t20) - (_t21 * _t24)) * _t27))) - ((_t19) * (_t20) - (_t21 * _t22)) * (_m_m01 * _t15)) < 0.0f) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
            _sfx7 = -_t18;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
            _sfx7 = _t18;
        }
        float _t52 = 1.0f + _t48;
        float _t64 = ((m.m11()) * (_t15) + (((_m_m22) * (_t16) + (_t52))));
        return makeFromMatrix_s6b8df64f_2_mulAdd(m, _t0, _t1, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, ((_m_m12) * (_t16) + (_t24)), ((m.m21()) * (_t15) - (_t21)), _t48, _sfx7, _t52, 1.0f - _t48, ((_m_m01) * (_t15) + (_t49)), ((_m_m02) * (_t16) + (_t50)), ((_m_m02) * (_t16) - (_t50)), ((_t49) - (_m_m01) * (_t15)), _t64, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatTransform makeFromMatrix_s6b8df64f_2_fma(Float3x3 m, float _t0, float _t1, float _sfx0, float _sfx1, float _sfx2, float _sfx8, float _t15, float _t25, float _sfx9, float _t16, float _t20, float _t32, float _t36, float _t48, float _sfx7, float _t52, float _t53, float _t55, float _t56, float _t57, float _t58, float _t64, float _sp0) {
        float _m_m11 = m.m11();
        float _t66 = java.lang.Math.fma(_m_m11, _t15, java.lang.Math.fma(_t1, _t16, _t53));
        float _m_m22 = m.m22();
        float _t67 = java.lang.Math.fma(_m_m22, _t16, java.lang.Math.fma(_t0, _t15, _t53));
        float _t68 = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t68));
        float _sfx3, _sfx4, _sfx5, _sfx6;
        if (java.lang.Math.fma(_m_m11, _t15, java.lang.Math.fma(_m_m22, _t16, _t48)) > 0.0f) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5f * (float) java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > Math.max(_t25, _t20)) {
                _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t68);
                _sfx4 = _sp3 * _t55;
                _sfx5 = _sp3 * _t56;
                _sfx6 = _sp3 * _t36;
            } else {
                if (_t25 > _t20) {
                    _sfx3 = _sp1 * _t55;
                    _sfx4 = 0.5f * (float) java.lang.Math.sqrt(_t66);
                    _sfx5 = _sp1 * _t32;
                    _sfx6 = _sp1 * _t57;
                } else {
                    _sfx3 = _sp2 * _t56;
                    _sfx4 = _sp2 * _t32;
                    _sfx5 = 0.5f * (float) java.lang.Math.sqrt(_t67);
                    _sfx6 = _sp2 * _t58;
                }
            }
        }
        return new FloatTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatTransform makeFromMatrix_s6b8df64f_2_mulAdd(Float3x3 m, float _t0, float _t1, float _sfx0, float _sfx1, float _sfx2, float _sfx8, float _t15, float _t25, float _sfx9, float _t16, float _t20, float _t32, float _t36, float _t48, float _sfx7, float _t52, float _t53, float _t55, float _t56, float _t57, float _t58, float _t64, float _sp0) {
        float _m_m11 = m.m11();
        float _t66 = ((_m_m11) * (_t15) + (((_t1) * (_t16) + (_t53))));
        float _m_m22 = m.m22();
        float _t67 = ((_m_m22) * (_t16) + (((_t0) * (_t15) + (_t53))));
        float _t68 = ((_t0) * (_t15) + (((_t1) * (_t16) + (_t52))));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t68));
        float _sfx3, _sfx4, _sfx5, _sfx6;
        if (((_m_m11) * (_t15) + (((_m_m22) * (_t16) + (_t48)))) > 0.0f) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5f * (float) java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > Math.max(_t25, _t20)) {
                _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t68);
                _sfx4 = _sp3 * _t55;
                _sfx5 = _sp3 * _t56;
                _sfx6 = _sp3 * _t36;
            } else {
                if (_t25 > _t20) {
                    _sfx3 = _sp1 * _t55;
                    _sfx4 = 0.5f * (float) java.lang.Math.sqrt(_t66);
                    _sfx5 = _sp1 * _t32;
                    _sfx6 = _sp1 * _t57;
                } else {
                    _sfx3 = _sp2 * _t56;
                    _sfx4 = _sp2 * _t32;
                    _sfx5 = 0.5f * (float) java.lang.Math.sqrt(_t67);
                    _sfx6 = _sp2 * _t58;
                }
            }
        }
        return new FloatTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad23f_tail_fma(float _t31, float _t17, float _t15, float _t16, float _t32, float _t13, float _t12, float _t14, float _t35, float _t36, float _t29, float _t2, float _t37, float _t27, float _t28, float _t0, float _t1) {
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t44 = java.lang.Math.abs(_t35);
        float _t45 = java.lang.Math.abs(_t36);
        float _t48 = java.lang.Math.abs(_t39);
        float _t49 = java.lang.Math.abs(_t40);
        float _t52 = java.lang.Math.abs(_t42);
        float _t53 = java.lang.Math.abs(_t41);
        float _t75, _t78;
        if (_t44 < _t45) {
            _t75 = _t37;
            _t78 = 0.0f;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
        }
        float _t76, _t79;
        if (_t48 < _t49) {
            _t76 = _t38;
            _t79 = 0.0f;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
        }
        float _t77, _t80;
        if (_t52 < _t53) {
            _t77 = _t43;
            _t80 = 0.0f;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
        }
        return makeFromMatrix_degenerate_s37cad23f_tail2_fma(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad23f_tail_mulAdd(float _t31, float _t17, float _t15, float _t16, float _t32, float _t13, float _t12, float _t14, float _t35, float _t36, float _t29, float _t2, float _t37, float _t27, float _t28, float _t0, float _t1) {
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t44 = java.lang.Math.abs(_t35);
        float _t45 = java.lang.Math.abs(_t36);
        float _t48 = java.lang.Math.abs(_t39);
        float _t49 = java.lang.Math.abs(_t40);
        float _t52 = java.lang.Math.abs(_t42);
        float _t53 = java.lang.Math.abs(_t41);
        float _t75, _t78;
        if (_t44 < _t45) {
            _t75 = _t37;
            _t78 = 0.0f;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
        }
        float _t76, _t79;
        if (_t48 < _t49) {
            _t76 = _t38;
            _t79 = 0.0f;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
        }
        float _t77, _t80;
        if (_t52 < _t53) {
            _t77 = _t43;
            _t80 = 0.0f;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
        }
        return makeFromMatrix_degenerate_s37cad23f_tail2_mulAdd(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad23f_tail2_fma(float _t44, float _t45, float _t36, float _t35, float _t48, float _t49, float _t40, float _t39, float _t52, float _t53, float _t41, float _t42, float _t78, float _t75, float _t79, float _t76, float _t80, float _t77, float _t27, float _t28, float _t29, float _t38, float _t37, float _t43, float _t56, float _t0, float _t1) {
        float _t90 = _t44 < _t45 ? -_t36 : _t35;
        float _t91 = _t48 < _t49 ? -_t40 : _t39;
        float _t92 = _t52 < _t53 ? -_t41 : _t42;
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t78, _t78, java.lang.Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t92, _t92, _t77 * _t77))));
        return makeFromMatrix_degenerate_s37cad23f_tail3_fma(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad23f_tail2_mulAdd(float _t44, float _t45, float _t36, float _t35, float _t48, float _t49, float _t40, float _t39, float _t52, float _t53, float _t41, float _t42, float _t78, float _t75, float _t79, float _t76, float _t80, float _t77, float _t27, float _t28, float _t29, float _t38, float _t37, float _t43, float _t56, float _t0, float _t1) {
        float _t90 = _t44 < _t45 ? -_t36 : _t35;
        float _t91 = _t48 < _t49 ? -_t40 : _t39;
        float _t92 = _t52 < _t53 ? -_t41 : _t42;
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(((_t78) * (_t78) + (((_t90) * (_t90) + (_t75 * _t75))))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(((_t79) * (_t79) + (((_t91) * (_t91) + (_t76 * _t76))))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(((_t80) * (_t80) + (((_t92) * (_t92) + (_t77 * _t77))))));
        return makeFromMatrix_degenerate_s37cad23f_tail3_mulAdd(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad23f_tail3_fma(float _t27, float _t28, float _t29, float _t105, float _t38, float _t108, float _t39, float _t117, float _t35, float _t37, float _t41, float _t118, float _t36, float _t109, float _t42, float _t119, float _t106, float _t40, float _t43, float _t107, float _t110, float _t56, float _t0, float _t1) {
        float _t168, _t169;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t169 = 0.0f;
                } else {
                    _t168 = _t105;
                    _t169 = java.lang.Math.fma(_t35, _t105, -(_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0f ? java.lang.Math.fma(_t38, _t108, -_t39 * _t117) : java.lang.Math.fma(_t35, _t38, _t37 * -_t39);
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t118 : java.lang.Math.fma(_t35, _t41, -(_t36 * _t42)) : _t38;
        }
        return makeFromMatrix_degenerate_s37cad23f_tail4_fma(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad23f_tail3_mulAdd(float _t27, float _t28, float _t29, float _t105, float _t38, float _t108, float _t39, float _t117, float _t35, float _t37, float _t41, float _t118, float _t36, float _t109, float _t42, float _t119, float _t106, float _t40, float _t43, float _t107, float _t110, float _t56, float _t0, float _t1) {
        float _t168, _t169;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t169 = 0.0f;
                } else {
                    _t168 = _t105;
                    _t169 = ((_t35) * (_t105) - (_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0f ? ((_t38) * (_t108) - (_t39 * _t117)) : ((_t35) * (_t38) - (_t37 * _t39));
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t118 : ((_t35) * (_t41) - (_t36 * _t42)) : _t38;
        }
        return makeFromMatrix_degenerate_s37cad23f_tail4_mulAdd(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad23f_tail4_fma(float _t27, float _t28, float _t29, float _t119, float _t39, float _t106, float _t40, float _t108, float _t36, float _t35, float _t43, float _t107, float _t37, float _t109, float _t42, float _t41, float _t118, float _t38, float _t117, float _t110, float _t105, float _t169, float _t168, float _t56, float _t0, float _t1) {
        float _t170, _t171;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t170 = 1.0f;
                    _t171 = 0.0f;
                } else {
                    _t170 = _t119;
                    _t171 = java.lang.Math.fma(_t37, _t109, -_t35 * _t119);
                }
            } else {
                _t170 = _t29 <= 0.0f ? java.lang.Math.fma(_t39, _t106, -(_t40 * _t108)) : java.lang.Math.fma(_t36, _t39, -_t35 * _t40);
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t107 : java.lang.Math.fma(_t37, _t42, -_t35 * _t43) : _t40;
        }
        return makeFromMatrix_degenerate_s37cad23f_tail5_fma(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad23f_tail4_mulAdd(float _t27, float _t28, float _t29, float _t119, float _t39, float _t106, float _t40, float _t108, float _t36, float _t35, float _t43, float _t107, float _t37, float _t109, float _t42, float _t41, float _t118, float _t38, float _t117, float _t110, float _t105, float _t169, float _t168, float _t56, float _t0, float _t1) {
        float _t170, _t171;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t170 = 1.0f;
                    _t171 = 0.0f;
                } else {
                    _t170 = _t119;
                    _t171 = ((_t37) * (_t109) - (_t35 * _t119));
                }
            } else {
                _t170 = _t29 <= 0.0f ? ((_t39) * (_t106) - (_t40 * _t108)) : ((_t36) * (_t39) - (_t35 * _t40));
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t107 : ((_t37) * (_t42) - (_t35 * _t43)) : _t40;
        }
        return makeFromMatrix_degenerate_s37cad23f_tail5_mulAdd(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad23f_tail5_fma(float _t29, float _t27, float _t28, float _t108, float _t41, float _t118, float _t43, float _t107, float _t38, float _t40, float _t35, float _t109, float _t117, float _t106, float _t37, float _t36, float _t42, float _t110, float _t119, float _t105, float _t39, float _t169, float _t170, float _t168, float _t171, float _t56, float _t0, float _t1) {
        float _t172, _t173;
        if (_t29 <= 0.0f) {
            if (_t27 <= 0.0f) {
                if (_t28 <= 0.0f) {
                    _t172 = 0.0f;
                    _t173 = 0.0f;
                } else {
                    _t172 = _t108;
                    _t173 = java.lang.Math.fma(_t40, _t117, -(_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0f ? java.lang.Math.fma(_t41, _t118, -(_t43 * _t107)) : java.lang.Math.fma(_t41, _t38, -(_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0f ? _t28 <= 0.0f ? _t109 : java.lang.Math.fma(_t37, _t40, -(_t36 * _t38)) : _t42;
        }
        return makeFromMatrix_degenerate_s37cad23f_tail6_fma(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad23f_tail5_mulAdd(float _t29, float _t27, float _t28, float _t108, float _t41, float _t118, float _t43, float _t107, float _t38, float _t40, float _t35, float _t109, float _t117, float _t106, float _t37, float _t36, float _t42, float _t110, float _t119, float _t105, float _t39, float _t169, float _t170, float _t168, float _t171, float _t56, float _t0, float _t1) {
        float _t172, _t173;
        if (_t29 <= 0.0f) {
            if (_t27 <= 0.0f) {
                if (_t28 <= 0.0f) {
                    _t172 = 0.0f;
                    _t173 = 0.0f;
                } else {
                    _t172 = _t108;
                    _t173 = ((_t40) * (_t117) - (_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0f ? ((_t41) * (_t118) - (_t43 * _t107)) : ((_t41) * (_t38) - (_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0f ? _t28 <= 0.0f ? _t109 : ((_t37) * (_t40) - (_t36 * _t38)) : _t42;
        }
        return makeFromMatrix_degenerate_s37cad23f_tail6_mulAdd(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad23f_tail6_fma(float _t28, float _t29, float _t27, float _t110, float _t36, float _t119, float _t37, float _t105, float _t43, float _t41, float _t39, float _t117, float _t42, float _t107, float _t40, float _t106, float _t118, float _t38, float _t173, float _t169, float _t170, float _t168, float _t171, float _t172, float _t56, float _t0, float _t1) {
        float _t174, _t175;
        if (_t28 <= 0.0f) {
            if (_t29 <= 0.0f) {
                if (_t27 <= 0.0f) {
                    _t174 = 1.0f;
                    _t175 = 0.0f;
                } else {
                    _t174 = _t110;
                    _t175 = java.lang.Math.fma(_t42, _t107, -(_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0f ? java.lang.Math.fma(_t36, _t119, -(_t37 * _t105)) : java.lang.Math.fma(_t36, _t43, -(_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t117 : java.lang.Math.fma(_t42, _t40, -(_t41 * _t39)) : _t37;
        }
        return makeFromMatrix_degenerate_s37cad23f_tail7_fma(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad23f_tail6_mulAdd(float _t28, float _t29, float _t27, float _t110, float _t36, float _t119, float _t37, float _t105, float _t43, float _t41, float _t39, float _t117, float _t42, float _t107, float _t40, float _t106, float _t118, float _t38, float _t173, float _t169, float _t170, float _t168, float _t171, float _t172, float _t56, float _t0, float _t1) {
        float _t174, _t175;
        if (_t28 <= 0.0f) {
            if (_t29 <= 0.0f) {
                if (_t27 <= 0.0f) {
                    _t174 = 1.0f;
                    _t175 = 0.0f;
                } else {
                    _t174 = _t110;
                    _t175 = ((_t42) * (_t107) - (_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0f ? ((_t36) * (_t119) - (_t37 * _t105)) : ((_t36) * (_t43) - (_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t117 : ((_t42) * (_t40) - (_t41 * _t39)) : _t37;
        }
        return makeFromMatrix_degenerate_s37cad23f_tail7_mulAdd(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad23f_tail7_fma(float _t29, float _t27, float _t28, float _t106, float _t43, float _t110, float _t42, float _t118, float _t39, float _t38, float _t36, float _t173, float _t169, float _t170, float _t174, float _t168, float _t171, float _t172, float _t175, float _t56, float _t0, float _t1) {
        float _t176 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t28 <= 0.0f ? 1.0f : _t106 : _t28 <= 0.0f ? java.lang.Math.fma(_t43, _t110, -_t42 * _t118) : java.lang.Math.fma(_t43, _t39, -_t42 * _t38) : _t36;
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
        return makeFromMatrix_degenerate_s37cad23f_tail8(_t199, _t171, _t198, _t168, _t197, _t170, _t174, _t173 - _t169, Math.max(_t170, _t174), _t198 + _t168, _t173 + _t169, _t196, _t56, _t27, _t0, _t28, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad23f_tail7_mulAdd(float _t29, float _t27, float _t28, float _t106, float _t43, float _t110, float _t42, float _t118, float _t39, float _t38, float _t36, float _t173, float _t169, float _t170, float _t174, float _t168, float _t171, float _t172, float _t175, float _t56, float _t0, float _t1) {
        float _t176 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t28 <= 0.0f ? 1.0f : _t106 : _t28 <= 0.0f ? ((_t43) * (_t110) - (_t42 * _t118)) : ((_t43) * (_t39) - (_t42 * _t38)) : _t36;
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
        return makeFromMatrix_degenerate_s37cad23f_tail8(_t199, _t171, _t198, _t168, _t197, _t170, _t174, _t173 - _t169, Math.max(_t170, _t174), _t198 + _t168, _t173 + _t169, _t196, _t56, _t27, _t0, _t28, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad23f_tail8(float _t199, float _t171, float _t198, float _t168, float _t197, float _t170, float _t174, float _t185, float _t186, float _t202, float _t187, float _t196, float _t56, float _t27, float _t0, float _t28, float _t1) {
        float _t203 = _t199 + _t171;
        float _t209 = _t197 + _t170 + _t174;
        float _t210 = 1.0f + _t209;
        float _t211 = 1.0f + _t197 - _t170 - _t174;
        float _t212 = 1.0f + _t170 - _t197 - _t174;
        float _t213 = 1.0f + _t174 - _t197 - _t170;
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t213));
        float _sfx3 = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        return makeFromMatrix_degenerate_s37cad23f_tail9(_t209, _sp0, _t171 - _t199, _t197, _t186, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t211)), _t202, _t170, _t174, _t212, _sp2, _t187, _t198 - _t168, _t203, _sp1, _t213, _t210, _t185, _t196, _t56, _t27, _t0, _t28, _t1, 0.0f, 0.0f, 0.0f, _sfx3);
    }

    /**
     * Private tail of {@code makeFromMatrix_degenerate}. Shared by 3 identical private paths of
     * {@code makeFromMatrix}; reached only through it.
     */
    private static FloatTransform makeFromMatrix_degenerate_s37cad23f_tail9(float _t209, float _sp0, float _t204, float _t197, float _t186, float _sp3, float _t202, float _t170, float _t174, float _t212, float _sp2, float _t187, float _t205, float _t203, float _sp1, float _t213, float _t210, float _t185, float _t196, float _t56, float _t27, float _t0, float _t28, float _t1, float _sfx0, float _sfx1, float _sfx2, float _sfx3) {
        float _sfx4, _sfx5, _sfx6;
        if (_t209 > 0.0f) {
            _sfx4 = _sp0 * _t204;
            _sfx5 = _sp0 * _t205;
            _sfx6 = 0.5f * (float) java.lang.Math.sqrt(_t210);
        } else {
            if (_t197 > _t186) {
                _sfx4 = _sp3 * _t202;
                _sfx5 = _sp3 * _t203;
                _sfx6 = _sp3 * _t185;
            } else {
                if (_t170 > _t174) {
                    _sfx4 = 0.5f * (float) java.lang.Math.sqrt(_t212);
                    _sfx5 = _sp1 * _t187;
                    _sfx6 = _sp1 * _t204;
                } else {
                    _sfx4 = _sp2 * _t187;
                    _sfx5 = 0.5f * (float) java.lang.Math.sqrt(_t213);
                    _sfx6 = _sp2 * _t205;
                }
            }
        }
        float _sfx7 = _t196 < 0.0f ? -_t56 : _t56;
        float _sfx8 = _t27 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t27) / _t0;
        float _sfx9 = _t28 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t28) / _t1;
        return new FloatTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static FloatTransform makeFromMatrix_degenerate_fma(Float3x3 m) {
        float _m_m01 = m.m01();
        float _m_m11 = m.m11();
        float _m_m21 = m.m21();
        float _t0 = unitScale(_m_m01, _m_m11, _m_m21);
        float _m_m02 = m.m02();
        float _m_m12 = m.m12();
        float _m_m22 = m.m22();
        float _t1 = unitScale(_m_m02, _m_m12, _m_m22);
        float _m_m00 = m.m00();
        float _m_m10 = m.m10();
        float _m_m20 = m.m20();
        float _t2 = unitScale(_m_m00, _m_m10, _m_m20);
        float _t12 = _m_m21 * _t0;
        float _t13 = _m_m01 * _t0;
        float _t14 = _m_m11 * _t0;
        float _t15 = _m_m22 * _t1;
        float _t16 = _m_m02 * _t1;
        float _t17 = _m_m12 * _t1;
        float _t18 = _m_m20 * _t2;
        float _t19 = _m_m00 * _t2;
        float _t20 = _m_m10 * _t2;
        float _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_s37cad23f_tail_fma((1.0f / (float) java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0f / (float) java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t30 * _t20, _t27, _t28, _t0, _t1);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static FloatTransform makeFromMatrix_degenerate_mulAdd(Float3x3 m) {
        float _m_m01 = m.m01();
        float _m_m11 = m.m11();
        float _m_m21 = m.m21();
        float _t0 = unitScale(_m_m01, _m_m11, _m_m21);
        float _m_m02 = m.m02();
        float _m_m12 = m.m12();
        float _m_m22 = m.m22();
        float _t1 = unitScale(_m_m02, _m_m12, _m_m22);
        float _m_m00 = m.m00();
        float _m_m10 = m.m10();
        float _m_m20 = m.m20();
        float _t2 = unitScale(_m_m00, _m_m10, _m_m20);
        float _t12 = _m_m21 * _t0;
        float _t13 = _m_m01 * _t0;
        float _t14 = _m_m11 * _t0;
        float _t15 = _m_m22 * _t1;
        float _t16 = _m_m02 * _t1;
        float _t17 = _m_m12 * _t1;
        float _t18 = _m_m20 * _t2;
        float _t19 = _m_m00 * _t2;
        float _t20 = _m_m10 * _t2;
        float _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        float _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_s37cad23f_tail_mulAdd((1.0f / (float) java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0f / (float) java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t30 * _t20, _t27, _t28, _t0, _t1);
    }

    /**
     * Create the TRS decomposition of the given affine matrix: translation from the last column,
     * scale from the column lengths of the upper-left 3x3 block, rotation from the
     * column-normalized block (scale is removed by normalizing the columns, but shear is not
     * removed: a sheared block yields a rotation quaternion that is not unit length; a mirroring
     * block (negative determinant) gives a negative x scale, and the rotation of the block with its
     * first column negated).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return the resulting transform
     */
    public static FloatTransform makeFromMatrix(Float3x4 m) {
        if (org.joml2.internal.FmaSwitch.ON) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static FloatTransform makeFromMatrix_fma(Float3x4 m) {
        float _m_m21 = m.m21();
        float _m_m01 = m.m01();
        float _m_m11 = m.m11();
        float _t12 = java.lang.Math.fma(_m_m21, _m_m21, java.lang.Math.fma(_m_m01, _m_m01, _m_m11 * _m_m11));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _m_m22 = m.m22();
        float _m_m02 = m.m02();
        float _m_m12 = m.m12();
        float _t13 = java.lang.Math.fma(_m_m22, _m_m22, java.lang.Math.fma(_m_m02, _m_m02, _m_m12 * _m_m12));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _m_m20 = m.m20();
        float _m_m00 = m.m00();
        float _m_m10 = m.m10();
        float _t14 = java.lang.Math.fma(_m_m20, _m_m20, java.lang.Math.fma(_m_m00, _m_m00, _m_m10 * _m_m10));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        float _sfx8 = (float) java.lang.Math.sqrt(_t12);
        float _t15 = 1.0f / _sfx8;
        return makeFromMatrix_s69dd4b8e_1_fma(m, _t13, -_m_m11, -_m_m22, _t18, _m_m10 * _t17, _m_m20 * _t17, _m_m00 * _t17, m.m03(), m.m13(), m.m23(), _sfx8, _t15, _m_m21 * _t15, _m_m11 * _t15);
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static FloatTransform makeFromMatrix_mulAdd(Float3x4 m) {
        float _m_m21 = m.m21();
        float _m_m01 = m.m01();
        float _m_m11 = m.m11();
        float _t12 = ((_m_m21) * (_m_m21) + (((_m_m01) * (_m_m01) + (_m_m11 * _m_m11))));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _m_m22 = m.m22();
        float _m_m02 = m.m02();
        float _m_m12 = m.m12();
        float _t13 = ((_m_m22) * (_m_m22) + (((_m_m02) * (_m_m02) + (_m_m12 * _m_m12))));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _m_m20 = m.m20();
        float _m_m00 = m.m00();
        float _m_m10 = m.m10();
        float _t14 = ((_m_m20) * (_m_m20) + (((_m_m00) * (_m_m00) + (_m_m10 * _m_m10))));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        float _sfx8 = (float) java.lang.Math.sqrt(_t12);
        float _t15 = 1.0f / _sfx8;
        return makeFromMatrix_s69dd4b8e_1_mulAdd(m, _t13, -_m_m11, -_m_m22, _t18, _m_m10 * _t17, _m_m20 * _t17, _m_m00 * _t17, m.m03(), m.m13(), m.m23(), _sfx8, _t15, _m_m21 * _t15, _m_m11 * _t15);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatTransform makeFromMatrix_s69dd4b8e_1_fma(Float3x4 m, float _t13, float _t0, float _t1, float _t18, float _t19, float _t22, float _t27, float _sfx0, float _sfx1, float _sfx2, float _sfx8, float _t15, float _t24, float _t25) {
        float _sfx9 = (float) java.lang.Math.sqrt(_t13);
        float _t16 = 1.0f / _sfx9;
        float _m_m22 = m.m22();
        float _t20 = _m_m22 * _t16;
        float _m_m12 = m.m12();
        float _t21 = _m_m12 * _t16;
        float _t48, _t49, _t50, _sfx7;
        float _m_m01 = m.m01();
        float _m_m02 = m.m02();
        if (java.lang.Math.fma(-java.lang.Math.fma(_t19, _t20, -(_t21 * _t22)), _m_m01 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t19, _t24, -(_t25 * _t22)), _m_m02 * _t16, java.lang.Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27)) < 0.0f) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
            _sfx7 = -_t18;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
            _sfx7 = _t18;
        }
        float _t52 = 1.0f + _t48;
        float _t64 = java.lang.Math.fma(m.m11(), _t15, java.lang.Math.fma(_m_m22, _t16, _t52));
        return makeFromMatrix_s69dd4b8e_2_fma(m, _t0, _t1, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, java.lang.Math.fma(_m_m12, _t16, _t24), java.lang.Math.fma(m.m21(), _t15, -_t21), _t48, _sfx7, _t52, 1.0f - _t48, java.lang.Math.fma(_m_m01, _t15, _t49), java.lang.Math.fma(_m_m02, _t16, _t50), java.lang.Math.fma(_m_m02, _t16, -_t50), java.lang.Math.fma(-_m_m01, _t15, _t49), _t64, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatTransform makeFromMatrix_s69dd4b8e_1_mulAdd(Float3x4 m, float _t13, float _t0, float _t1, float _t18, float _t19, float _t22, float _t27, float _sfx0, float _sfx1, float _sfx2, float _sfx8, float _t15, float _t24, float _t25) {
        float _sfx9 = (float) java.lang.Math.sqrt(_t13);
        float _t16 = 1.0f / _sfx9;
        float _m_m22 = m.m22();
        float _t20 = _m_m22 * _t16;
        float _m_m12 = m.m12();
        float _t21 = _m_m12 * _t16;
        float _t48, _t49, _t50, _sfx7;
        float _m_m02 = m.m02();
        float _m_m01 = m.m01();
        if (((((((_t19) * (_t24) - (_t25 * _t22))) * (_m_m02 * _t16) + (((_t25) * (_t20) - (_t21 * _t24)) * _t27))) - ((_t19) * (_t20) - (_t21 * _t22)) * (_m_m01 * _t15)) < 0.0f) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
            _sfx7 = -_t18;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
            _sfx7 = _t18;
        }
        float _t52 = 1.0f + _t48;
        float _t64 = ((m.m11()) * (_t15) + (((_m_m22) * (_t16) + (_t52))));
        return makeFromMatrix_s69dd4b8e_2_mulAdd(m, _t0, _t1, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, ((_m_m12) * (_t16) + (_t24)), ((m.m21()) * (_t15) - (_t21)), _t48, _sfx7, _t52, 1.0f - _t48, ((_m_m01) * (_t15) + (_t49)), ((_m_m02) * (_t16) + (_t50)), ((_m_m02) * (_t16) - (_t50)), ((_t49) - (_m_m01) * (_t15)), _t64, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatTransform makeFromMatrix_s69dd4b8e_2_fma(Float3x4 m, float _t0, float _t1, float _sfx0, float _sfx1, float _sfx2, float _sfx8, float _t15, float _t25, float _sfx9, float _t16, float _t20, float _t32, float _t36, float _t48, float _sfx7, float _t52, float _t53, float _t55, float _t56, float _t57, float _t58, float _t64, float _sp0) {
        float _m_m11 = m.m11();
        float _t66 = java.lang.Math.fma(_m_m11, _t15, java.lang.Math.fma(_t1, _t16, _t53));
        float _m_m22 = m.m22();
        float _t67 = java.lang.Math.fma(_m_m22, _t16, java.lang.Math.fma(_t0, _t15, _t53));
        float _t68 = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t68));
        float _sfx3, _sfx4, _sfx5, _sfx6;
        if (java.lang.Math.fma(_m_m11, _t15, java.lang.Math.fma(_m_m22, _t16, _t48)) > 0.0f) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5f * (float) java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > Math.max(_t25, _t20)) {
                _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t68);
                _sfx4 = _sp3 * _t55;
                _sfx5 = _sp3 * _t56;
                _sfx6 = _sp3 * _t36;
            } else {
                if (_t25 > _t20) {
                    _sfx3 = _sp1 * _t55;
                    _sfx4 = 0.5f * (float) java.lang.Math.sqrt(_t66);
                    _sfx5 = _sp1 * _t32;
                    _sfx6 = _sp1 * _t57;
                } else {
                    _sfx3 = _sp2 * _t56;
                    _sfx4 = _sp2 * _t32;
                    _sfx5 = 0.5f * (float) java.lang.Math.sqrt(_t67);
                    _sfx6 = _sp2 * _t58;
                }
            }
        }
        return new FloatTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatTransform makeFromMatrix_s69dd4b8e_2_mulAdd(Float3x4 m, float _t0, float _t1, float _sfx0, float _sfx1, float _sfx2, float _sfx8, float _t15, float _t25, float _sfx9, float _t16, float _t20, float _t32, float _t36, float _t48, float _sfx7, float _t52, float _t53, float _t55, float _t56, float _t57, float _t58, float _t64, float _sp0) {
        float _m_m11 = m.m11();
        float _t66 = ((_m_m11) * (_t15) + (((_t1) * (_t16) + (_t53))));
        float _m_m22 = m.m22();
        float _t67 = ((_m_m22) * (_t16) + (((_t0) * (_t15) + (_t53))));
        float _t68 = ((_t0) * (_t15) + (((_t1) * (_t16) + (_t52))));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t68));
        float _sfx3, _sfx4, _sfx5, _sfx6;
        if (((_m_m11) * (_t15) + (((_m_m22) * (_t16) + (_t48)))) > 0.0f) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5f * (float) java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > Math.max(_t25, _t20)) {
                _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t68);
                _sfx4 = _sp3 * _t55;
                _sfx5 = _sp3 * _t56;
                _sfx6 = _sp3 * _t36;
            } else {
                if (_t25 > _t20) {
                    _sfx3 = _sp1 * _t55;
                    _sfx4 = 0.5f * (float) java.lang.Math.sqrt(_t66);
                    _sfx5 = _sp1 * _t32;
                    _sfx6 = _sp1 * _t57;
                } else {
                    _sfx3 = _sp2 * _t56;
                    _sfx4 = _sp2 * _t32;
                    _sfx5 = 0.5f * (float) java.lang.Math.sqrt(_t67);
                    _sfx6 = _sp2 * _t58;
                }
            }
        }
        return new FloatTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad600_tail_fma(float _t30, float _t20, float _t31, float _t17, float _t15, float _t16, float _t32, float _t13, float _t12, float _t14, float _t35, float _t36, float _t29, float _t2, float _t27, float _t28, Float3x4 m, float _t0, float _t1) {
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t44 = java.lang.Math.abs(_t35);
        float _t45 = java.lang.Math.abs(_t36);
        float _t48 = java.lang.Math.abs(_t39);
        float _t49 = java.lang.Math.abs(_t40);
        float _t52 = java.lang.Math.abs(_t42);
        float _t53 = java.lang.Math.abs(_t41);
        float _t75, _t78;
        if (_t44 < _t45) {
            _t75 = _t37;
            _t78 = 0.0f;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
        }
        float _t76, _t79;
        if (_t48 < _t49) {
            _t76 = _t38;
            _t79 = 0.0f;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
        }
        float _t77, _t80;
        if (_t52 < _t53) {
            _t77 = _t43;
            _t80 = 0.0f;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
        }
        return makeFromMatrix_degenerate_s37cad600_tail2_fma(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, m, _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad600_tail_mulAdd(float _t30, float _t20, float _t31, float _t17, float _t15, float _t16, float _t32, float _t13, float _t12, float _t14, float _t35, float _t36, float _t29, float _t2, float _t27, float _t28, Float3x4 m, float _t0, float _t1) {
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t44 = java.lang.Math.abs(_t35);
        float _t45 = java.lang.Math.abs(_t36);
        float _t48 = java.lang.Math.abs(_t39);
        float _t49 = java.lang.Math.abs(_t40);
        float _t52 = java.lang.Math.abs(_t42);
        float _t53 = java.lang.Math.abs(_t41);
        float _t75, _t78;
        if (_t44 < _t45) {
            _t75 = _t37;
            _t78 = 0.0f;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
        }
        float _t76, _t79;
        if (_t48 < _t49) {
            _t76 = _t38;
            _t79 = 0.0f;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
        }
        float _t77, _t80;
        if (_t52 < _t53) {
            _t77 = _t43;
            _t80 = 0.0f;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
        }
        return makeFromMatrix_degenerate_s37cad600_tail2_mulAdd(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, m, _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad600_tail2_fma(float _t44, float _t45, float _t36, float _t35, float _t48, float _t49, float _t40, float _t39, float _t52, float _t53, float _t41, float _t42, float _t78, float _t75, float _t79, float _t76, float _t80, float _t77, float _t27, float _t28, float _t29, float _t38, float _t37, float _t43, Float3x4 m, float _t56, float _t0, float _t1) {
        float _t90 = _t44 < _t45 ? -_t36 : _t35;
        float _t91 = _t48 < _t49 ? -_t40 : _t39;
        float _t92 = _t52 < _t53 ? -_t41 : _t42;
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t78, _t78, java.lang.Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t92, _t92, _t77 * _t77))));
        return makeFromMatrix_degenerate_s37cad600_tail3_fma(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad600_tail2_mulAdd(float _t44, float _t45, float _t36, float _t35, float _t48, float _t49, float _t40, float _t39, float _t52, float _t53, float _t41, float _t42, float _t78, float _t75, float _t79, float _t76, float _t80, float _t77, float _t27, float _t28, float _t29, float _t38, float _t37, float _t43, Float3x4 m, float _t56, float _t0, float _t1) {
        float _t90 = _t44 < _t45 ? -_t36 : _t35;
        float _t91 = _t48 < _t49 ? -_t40 : _t39;
        float _t92 = _t52 < _t53 ? -_t41 : _t42;
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(((_t78) * (_t78) + (((_t90) * (_t90) + (_t75 * _t75))))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(((_t79) * (_t79) + (((_t91) * (_t91) + (_t76 * _t76))))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(((_t80) * (_t80) + (((_t92) * (_t92) + (_t77 * _t77))))));
        return makeFromMatrix_degenerate_s37cad600_tail3_mulAdd(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad600_tail3_fma(float _t27, float _t28, float _t29, float _t105, float _t38, float _t108, float _t39, float _t117, float _t35, float _t37, float _t41, float _t118, float _t36, float _t109, float _t42, float _t119, float _t106, float _t40, float _t43, float _t107, float _t110, Float3x4 m, float _t56, float _t0, float _t1) {
        float _t168, _t169;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t169 = 0.0f;
                } else {
                    _t168 = _t105;
                    _t169 = java.lang.Math.fma(_t35, _t105, -(_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0f ? java.lang.Math.fma(_t38, _t108, -_t39 * _t117) : java.lang.Math.fma(_t35, _t38, _t37 * -_t39);
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t118 : java.lang.Math.fma(_t35, _t41, -(_t36 * _t42)) : _t38;
        }
        return makeFromMatrix_degenerate_s37cad600_tail4_fma(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad600_tail3_mulAdd(float _t27, float _t28, float _t29, float _t105, float _t38, float _t108, float _t39, float _t117, float _t35, float _t37, float _t41, float _t118, float _t36, float _t109, float _t42, float _t119, float _t106, float _t40, float _t43, float _t107, float _t110, Float3x4 m, float _t56, float _t0, float _t1) {
        float _t168, _t169;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t169 = 0.0f;
                } else {
                    _t168 = _t105;
                    _t169 = ((_t35) * (_t105) - (_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0f ? ((_t38) * (_t108) - (_t39 * _t117)) : ((_t35) * (_t38) - (_t37 * _t39));
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t118 : ((_t35) * (_t41) - (_t36 * _t42)) : _t38;
        }
        return makeFromMatrix_degenerate_s37cad600_tail4_mulAdd(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad600_tail4_fma(float _t27, float _t28, float _t29, float _t119, float _t39, float _t106, float _t40, float _t108, float _t36, float _t35, float _t43, float _t107, float _t37, float _t109, float _t42, float _t41, float _t118, float _t38, float _t117, float _t110, float _t105, float _t169, float _t168, Float3x4 m, float _t56, float _t0, float _t1) {
        float _t170, _t171;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t170 = 1.0f;
                    _t171 = 0.0f;
                } else {
                    _t170 = _t119;
                    _t171 = java.lang.Math.fma(_t37, _t109, -_t35 * _t119);
                }
            } else {
                _t170 = _t29 <= 0.0f ? java.lang.Math.fma(_t39, _t106, -(_t40 * _t108)) : java.lang.Math.fma(_t36, _t39, -_t35 * _t40);
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t107 : java.lang.Math.fma(_t37, _t42, -_t35 * _t43) : _t40;
        }
        return makeFromMatrix_degenerate_s37cad600_tail5_fma(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad600_tail4_mulAdd(float _t27, float _t28, float _t29, float _t119, float _t39, float _t106, float _t40, float _t108, float _t36, float _t35, float _t43, float _t107, float _t37, float _t109, float _t42, float _t41, float _t118, float _t38, float _t117, float _t110, float _t105, float _t169, float _t168, Float3x4 m, float _t56, float _t0, float _t1) {
        float _t170, _t171;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t170 = 1.0f;
                    _t171 = 0.0f;
                } else {
                    _t170 = _t119;
                    _t171 = ((_t37) * (_t109) - (_t35 * _t119));
                }
            } else {
                _t170 = _t29 <= 0.0f ? ((_t39) * (_t106) - (_t40 * _t108)) : ((_t36) * (_t39) - (_t35 * _t40));
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t107 : ((_t37) * (_t42) - (_t35 * _t43)) : _t40;
        }
        return makeFromMatrix_degenerate_s37cad600_tail5_mulAdd(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad600_tail5_fma(float _t29, float _t27, float _t28, float _t108, float _t41, float _t118, float _t43, float _t107, float _t38, float _t40, float _t35, float _t109, float _t117, float _t106, float _t37, float _t36, float _t42, float _t110, float _t119, float _t105, float _t39, float _t169, float _t170, float _t168, float _t171, Float3x4 m, float _t56, float _t0, float _t1) {
        float _t172, _t173;
        if (_t29 <= 0.0f) {
            if (_t27 <= 0.0f) {
                if (_t28 <= 0.0f) {
                    _t172 = 0.0f;
                    _t173 = 0.0f;
                } else {
                    _t172 = _t108;
                    _t173 = java.lang.Math.fma(_t40, _t117, -(_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0f ? java.lang.Math.fma(_t41, _t118, -(_t43 * _t107)) : java.lang.Math.fma(_t41, _t38, -(_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0f ? _t28 <= 0.0f ? _t109 : java.lang.Math.fma(_t37, _t40, -(_t36 * _t38)) : _t42;
        }
        return makeFromMatrix_degenerate_s37cad600_tail6_fma(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad600_tail5_mulAdd(float _t29, float _t27, float _t28, float _t108, float _t41, float _t118, float _t43, float _t107, float _t38, float _t40, float _t35, float _t109, float _t117, float _t106, float _t37, float _t36, float _t42, float _t110, float _t119, float _t105, float _t39, float _t169, float _t170, float _t168, float _t171, Float3x4 m, float _t56, float _t0, float _t1) {
        float _t172, _t173;
        if (_t29 <= 0.0f) {
            if (_t27 <= 0.0f) {
                if (_t28 <= 0.0f) {
                    _t172 = 0.0f;
                    _t173 = 0.0f;
                } else {
                    _t172 = _t108;
                    _t173 = ((_t40) * (_t117) - (_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0f ? ((_t41) * (_t118) - (_t43 * _t107)) : ((_t41) * (_t38) - (_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0f ? _t28 <= 0.0f ? _t109 : ((_t37) * (_t40) - (_t36 * _t38)) : _t42;
        }
        return makeFromMatrix_degenerate_s37cad600_tail6_mulAdd(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad600_tail6_fma(float _t28, float _t29, float _t27, float _t110, float _t36, float _t119, float _t37, float _t105, float _t43, float _t41, float _t39, float _t117, float _t42, float _t107, float _t40, float _t106, float _t118, float _t38, float _t173, float _t169, float _t170, float _t168, float _t171, float _t172, Float3x4 m, float _t56, float _t0, float _t1) {
        float _t174, _t175;
        if (_t28 <= 0.0f) {
            if (_t29 <= 0.0f) {
                if (_t27 <= 0.0f) {
                    _t174 = 1.0f;
                    _t175 = 0.0f;
                } else {
                    _t174 = _t110;
                    _t175 = java.lang.Math.fma(_t42, _t107, -(_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0f ? java.lang.Math.fma(_t36, _t119, -(_t37 * _t105)) : java.lang.Math.fma(_t36, _t43, -(_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t117 : java.lang.Math.fma(_t42, _t40, -(_t41 * _t39)) : _t37;
        }
        return makeFromMatrix_degenerate_s37cad600_tail7_fma(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad600_tail6_mulAdd(float _t28, float _t29, float _t27, float _t110, float _t36, float _t119, float _t37, float _t105, float _t43, float _t41, float _t39, float _t117, float _t42, float _t107, float _t40, float _t106, float _t118, float _t38, float _t173, float _t169, float _t170, float _t168, float _t171, float _t172, Float3x4 m, float _t56, float _t0, float _t1) {
        float _t174, _t175;
        if (_t28 <= 0.0f) {
            if (_t29 <= 0.0f) {
                if (_t27 <= 0.0f) {
                    _t174 = 1.0f;
                    _t175 = 0.0f;
                } else {
                    _t174 = _t110;
                    _t175 = ((_t42) * (_t107) - (_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0f ? ((_t36) * (_t119) - (_t37 * _t105)) : ((_t36) * (_t43) - (_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t117 : ((_t42) * (_t40) - (_t41 * _t39)) : _t37;
        }
        return makeFromMatrix_degenerate_s37cad600_tail7_mulAdd(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad600_tail7_fma(float _t29, float _t27, float _t28, float _t106, float _t43, float _t110, float _t42, float _t118, float _t39, float _t38, float _t36, float _t173, float _t169, float _t170, float _t174, float _t168, float _t171, float _t172, float _t175, Float3x4 m, float _t56, float _t0, float _t1) {
        float _t176 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t28 <= 0.0f ? 1.0f : _t106 : _t28 <= 0.0f ? java.lang.Math.fma(_t43, _t110, -_t42 * _t118) : java.lang.Math.fma(_t43, _t39, -_t42 * _t38) : _t36;
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
        return makeFromMatrix_degenerate_s37cad600_tail8(_t199, _t171, _t198, _t168, _t197, _t170, _t174, m, _t173 - _t169, Math.max(_t170, _t174), _t198 + _t168, _t173 + _t169, _t196, _t56, _t27, _t0, _t28, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad600_tail7_mulAdd(float _t29, float _t27, float _t28, float _t106, float _t43, float _t110, float _t42, float _t118, float _t39, float _t38, float _t36, float _t173, float _t169, float _t170, float _t174, float _t168, float _t171, float _t172, float _t175, Float3x4 m, float _t56, float _t0, float _t1) {
        float _t176 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t28 <= 0.0f ? 1.0f : _t106 : _t28 <= 0.0f ? ((_t43) * (_t110) - (_t42 * _t118)) : ((_t43) * (_t39) - (_t42 * _t38)) : _t36;
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
        return makeFromMatrix_degenerate_s37cad600_tail8(_t199, _t171, _t198, _t168, _t197, _t170, _t174, m, _t173 - _t169, Math.max(_t170, _t174), _t198 + _t168, _t173 + _t169, _t196, _t56, _t27, _t0, _t28, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37cad600_tail8(float _t199, float _t171, float _t198, float _t168, float _t197, float _t170, float _t174, Float3x4 m, float _t185, float _t186, float _t202, float _t187, float _t196, float _t56, float _t27, float _t0, float _t28, float _t1) {
        float _t203 = _t199 + _t171;
        float _t209 = _t197 + _t170 + _t174;
        float _t210 = 1.0f + _t209;
        float _t211 = 1.0f + _t197 - _t170 - _t174;
        float _t212 = 1.0f + _t170 - _t197 - _t174;
        float _t213 = 1.0f + _t174 - _t197 - _t170;
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t213));
        float _sfx0 = m.m03();
        float _sfx1 = m.m13();
        float _sfx2 = m.m23();
        float _sfx3 = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        return makeFromMatrix_degenerate_s37cad23f_tail9(_t209, _sp0, _t171 - _t199, _t197, _t186, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t211)), _t202, _t170, _t174, _t212, _sp2, _t187, _t198 - _t168, _t203, _sp1, _t213, _t210, _t185, _t196, _t56, _t27, _t0, _t28, _t1, _sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static FloatTransform makeFromMatrix_degenerate_fma(Float3x4 m) {
        float _m_m01 = m.m01();
        float _m_m11 = m.m11();
        float _m_m21 = m.m21();
        float _t0 = unitScale(_m_m01, _m_m11, _m_m21);
        float _m_m02 = m.m02();
        float _m_m12 = m.m12();
        float _m_m22 = m.m22();
        float _t1 = unitScale(_m_m02, _m_m12, _m_m22);
        float _m_m00 = m.m00();
        float _m_m10 = m.m10();
        float _m_m20 = m.m20();
        float _t2 = unitScale(_m_m00, _m_m10, _m_m20);
        float _t12 = _m_m21 * _t0;
        float _t13 = _m_m01 * _t0;
        float _t14 = _m_m11 * _t0;
        float _t15 = _m_m22 * _t1;
        float _t16 = _m_m02 * _t1;
        float _t17 = _m_m12 * _t1;
        float _t18 = _m_m20 * _t2;
        float _t19 = _m_m00 * _t2;
        float _t20 = _m_m10 * _t2;
        float _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_s37cad600_tail_fma(_t30, _t20, (1.0f / (float) java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0f / (float) java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t27, _t28, m, _t0, _t1);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static FloatTransform makeFromMatrix_degenerate_mulAdd(Float3x4 m) {
        float _m_m01 = m.m01();
        float _m_m11 = m.m11();
        float _m_m21 = m.m21();
        float _t0 = unitScale(_m_m01, _m_m11, _m_m21);
        float _m_m02 = m.m02();
        float _m_m12 = m.m12();
        float _m_m22 = m.m22();
        float _t1 = unitScale(_m_m02, _m_m12, _m_m22);
        float _m_m00 = m.m00();
        float _m_m10 = m.m10();
        float _m_m20 = m.m20();
        float _t2 = unitScale(_m_m00, _m_m10, _m_m20);
        float _t12 = _m_m21 * _t0;
        float _t13 = _m_m01 * _t0;
        float _t14 = _m_m11 * _t0;
        float _t15 = _m_m22 * _t1;
        float _t16 = _m_m02 * _t1;
        float _t17 = _m_m12 * _t1;
        float _t18 = _m_m20 * _t2;
        float _t19 = _m_m00 * _t2;
        float _t20 = _m_m10 * _t2;
        float _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        float _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_s37cad600_tail_mulAdd(_t30, _t20, (1.0f / (float) java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0f / (float) java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t27, _t28, m, _t0, _t1);
    }

    /**
     * Create the TRS decomposition of the given affine matrix: translation from the last column,
     * scale from the column lengths of the upper-left 3x3 block, rotation from the
     * column-normalized block (scale is removed by normalizing the columns, but shear is not
     * removed: a sheared block yields a rotation quaternion that is not unit length; a mirroring
     * block (negative determinant) gives a negative x scale, and the rotation of the block with its
     * first column negated).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return the resulting transform
     */
    public static FloatTransform makeFromMatrix(Float4x4 m) {
        if (org.joml2.internal.FmaSwitch.ON) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static FloatTransform makeFromMatrix_fma(Float4x4 m) {
        float _m_m21 = m.m21();
        float _m_m01 = m.m01();
        float _m_m11 = m.m11();
        float _t12 = java.lang.Math.fma(_m_m21, _m_m21, java.lang.Math.fma(_m_m01, _m_m01, _m_m11 * _m_m11));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _m_m22 = m.m22();
        float _m_m02 = m.m02();
        float _m_m12 = m.m12();
        float _t13 = java.lang.Math.fma(_m_m22, _m_m22, java.lang.Math.fma(_m_m02, _m_m02, _m_m12 * _m_m12));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _m_m20 = m.m20();
        float _m_m00 = m.m00();
        float _m_m10 = m.m10();
        float _t14 = java.lang.Math.fma(_m_m20, _m_m20, java.lang.Math.fma(_m_m00, _m_m00, _m_m10 * _m_m10));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        float _sfx8 = (float) java.lang.Math.sqrt(_t12);
        float _t15 = 1.0f / _sfx8;
        return makeFromMatrix_s4d2e9ff7_1_fma(m, _t13, -_m_m11, -_m_m22, _t18, _m_m10 * _t17, _m_m20 * _t17, _m_m00 * _t17, m.m03(), m.m13(), m.m23(), _sfx8, _t15, _m_m21 * _t15, _m_m11 * _t15);
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static FloatTransform makeFromMatrix_mulAdd(Float4x4 m) {
        float _m_m21 = m.m21();
        float _m_m01 = m.m01();
        float _m_m11 = m.m11();
        float _t12 = ((_m_m21) * (_m_m21) + (((_m_m01) * (_m_m01) + (_m_m11 * _m_m11))));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _m_m22 = m.m22();
        float _m_m02 = m.m02();
        float _m_m12 = m.m12();
        float _t13 = ((_m_m22) * (_m_m22) + (((_m_m02) * (_m_m02) + (_m_m12 * _m_m12))));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _m_m20 = m.m20();
        float _m_m00 = m.m00();
        float _m_m10 = m.m10();
        float _t14 = ((_m_m20) * (_m_m20) + (((_m_m00) * (_m_m00) + (_m_m10 * _m_m10))));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        float _sfx8 = (float) java.lang.Math.sqrt(_t12);
        float _t15 = 1.0f / _sfx8;
        return makeFromMatrix_s4d2e9ff7_1_mulAdd(m, _t13, -_m_m11, -_m_m22, _t18, _m_m10 * _t17, _m_m20 * _t17, _m_m00 * _t17, m.m03(), m.m13(), m.m23(), _sfx8, _t15, _m_m21 * _t15, _m_m11 * _t15);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatTransform makeFromMatrix_s4d2e9ff7_1_fma(Float4x4 m, float _t13, float _t0, float _t1, float _t18, float _t19, float _t22, float _t27, float _sfx0, float _sfx1, float _sfx2, float _sfx8, float _t15, float _t24, float _t25) {
        float _sfx9 = (float) java.lang.Math.sqrt(_t13);
        float _t16 = 1.0f / _sfx9;
        float _m_m22 = m.m22();
        float _t20 = _m_m22 * _t16;
        float _m_m12 = m.m12();
        float _t21 = _m_m12 * _t16;
        float _t48, _t49, _t50, _sfx7;
        float _m_m01 = m.m01();
        float _m_m02 = m.m02();
        if (java.lang.Math.fma(-java.lang.Math.fma(_t19, _t20, -(_t21 * _t22)), _m_m01 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t19, _t24, -(_t25 * _t22)), _m_m02 * _t16, java.lang.Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27)) < 0.0f) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
            _sfx7 = -_t18;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
            _sfx7 = _t18;
        }
        float _t52 = 1.0f + _t48;
        float _t64 = java.lang.Math.fma(m.m11(), _t15, java.lang.Math.fma(_m_m22, _t16, _t52));
        return makeFromMatrix_s4d2e9ff7_2_fma(m, _t0, _t1, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, java.lang.Math.fma(_m_m12, _t16, _t24), java.lang.Math.fma(m.m21(), _t15, -_t21), _t48, _sfx7, _t52, 1.0f - _t48, java.lang.Math.fma(_m_m01, _t15, _t49), java.lang.Math.fma(_m_m02, _t16, _t50), java.lang.Math.fma(_m_m02, _t16, -_t50), java.lang.Math.fma(-_m_m01, _t15, _t49), _t64, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatTransform makeFromMatrix_s4d2e9ff7_1_mulAdd(Float4x4 m, float _t13, float _t0, float _t1, float _t18, float _t19, float _t22, float _t27, float _sfx0, float _sfx1, float _sfx2, float _sfx8, float _t15, float _t24, float _t25) {
        float _sfx9 = (float) java.lang.Math.sqrt(_t13);
        float _t16 = 1.0f / _sfx9;
        float _m_m22 = m.m22();
        float _t20 = _m_m22 * _t16;
        float _m_m12 = m.m12();
        float _t21 = _m_m12 * _t16;
        float _t48, _t49, _t50, _sfx7;
        float _m_m02 = m.m02();
        float _m_m01 = m.m01();
        if (((((((_t19) * (_t24) - (_t25 * _t22))) * (_m_m02 * _t16) + (((_t25) * (_t20) - (_t21 * _t24)) * _t27))) - ((_t19) * (_t20) - (_t21 * _t22)) * (_m_m01 * _t15)) < 0.0f) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
            _sfx7 = -_t18;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
            _sfx7 = _t18;
        }
        float _t52 = 1.0f + _t48;
        float _t64 = ((m.m11()) * (_t15) + (((_m_m22) * (_t16) + (_t52))));
        return makeFromMatrix_s4d2e9ff7_2_mulAdd(m, _t0, _t1, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, ((_m_m12) * (_t16) + (_t24)), ((m.m21()) * (_t15) - (_t21)), _t48, _sfx7, _t52, 1.0f - _t48, ((_m_m01) * (_t15) + (_t49)), ((_m_m02) * (_t16) + (_t50)), ((_m_m02) * (_t16) - (_t50)), ((_t49) - (_m_m01) * (_t15)), _t64, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatTransform makeFromMatrix_s4d2e9ff7_2_fma(Float4x4 m, float _t0, float _t1, float _sfx0, float _sfx1, float _sfx2, float _sfx8, float _t15, float _t25, float _sfx9, float _t16, float _t20, float _t32, float _t36, float _t48, float _sfx7, float _t52, float _t53, float _t55, float _t56, float _t57, float _t58, float _t64, float _sp0) {
        float _m_m11 = m.m11();
        float _t66 = java.lang.Math.fma(_m_m11, _t15, java.lang.Math.fma(_t1, _t16, _t53));
        float _m_m22 = m.m22();
        float _t67 = java.lang.Math.fma(_m_m22, _t16, java.lang.Math.fma(_t0, _t15, _t53));
        float _t68 = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t68));
        float _sfx3, _sfx4, _sfx5, _sfx6;
        if (java.lang.Math.fma(_m_m11, _t15, java.lang.Math.fma(_m_m22, _t16, _t48)) > 0.0f) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5f * (float) java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > Math.max(_t25, _t20)) {
                _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t68);
                _sfx4 = _sp3 * _t55;
                _sfx5 = _sp3 * _t56;
                _sfx6 = _sp3 * _t36;
            } else {
                if (_t25 > _t20) {
                    _sfx3 = _sp1 * _t55;
                    _sfx4 = 0.5f * (float) java.lang.Math.sqrt(_t66);
                    _sfx5 = _sp1 * _t32;
                    _sfx6 = _sp1 * _t57;
                } else {
                    _sfx3 = _sp2 * _t56;
                    _sfx4 = _sp2 * _t32;
                    _sfx5 = 0.5f * (float) java.lang.Math.sqrt(_t67);
                    _sfx6 = _sp2 * _t58;
                }
            }
        }
        return new FloatTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static FloatTransform makeFromMatrix_s4d2e9ff7_2_mulAdd(Float4x4 m, float _t0, float _t1, float _sfx0, float _sfx1, float _sfx2, float _sfx8, float _t15, float _t25, float _sfx9, float _t16, float _t20, float _t32, float _t36, float _t48, float _sfx7, float _t52, float _t53, float _t55, float _t56, float _t57, float _t58, float _t64, float _sp0) {
        float _m_m11 = m.m11();
        float _t66 = ((_m_m11) * (_t15) + (((_t1) * (_t16) + (_t53))));
        float _m_m22 = m.m22();
        float _t67 = ((_m_m22) * (_t16) + (((_t0) * (_t15) + (_t53))));
        float _t68 = ((_t0) * (_t15) + (((_t1) * (_t16) + (_t52))));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t68));
        float _sfx3, _sfx4, _sfx5, _sfx6;
        if (((_m_m11) * (_t15) + (((_m_m22) * (_t16) + (_t48)))) > 0.0f) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5f * (float) java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > Math.max(_t25, _t20)) {
                _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t68);
                _sfx4 = _sp3 * _t55;
                _sfx5 = _sp3 * _t56;
                _sfx6 = _sp3 * _t36;
            } else {
                if (_t25 > _t20) {
                    _sfx3 = _sp1 * _t55;
                    _sfx4 = 0.5f * (float) java.lang.Math.sqrt(_t66);
                    _sfx5 = _sp1 * _t32;
                    _sfx6 = _sp1 * _t57;
                } else {
                    _sfx3 = _sp2 * _t56;
                    _sfx4 = _sp2 * _t32;
                    _sfx5 = 0.5f * (float) java.lang.Math.sqrt(_t67);
                    _sfx6 = _sp2 * _t58;
                }
            }
        }
        return new FloatTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37d8ed81_tail_fma(float _t30, float _t20, float _t31, float _t17, float _t15, float _t16, float _t32, float _t13, float _t12, float _t14, float _t35, float _t36, float _t29, float _t2, float _t27, float _t28, Float4x4 m, float _t0, float _t1) {
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t44 = java.lang.Math.abs(_t35);
        float _t45 = java.lang.Math.abs(_t36);
        float _t48 = java.lang.Math.abs(_t39);
        float _t49 = java.lang.Math.abs(_t40);
        float _t52 = java.lang.Math.abs(_t42);
        float _t53 = java.lang.Math.abs(_t41);
        float _t75, _t78;
        if (_t44 < _t45) {
            _t75 = _t37;
            _t78 = 0.0f;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
        }
        float _t76, _t79;
        if (_t48 < _t49) {
            _t76 = _t38;
            _t79 = 0.0f;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
        }
        float _t77, _t80;
        if (_t52 < _t53) {
            _t77 = _t43;
            _t80 = 0.0f;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
        }
        return makeFromMatrix_degenerate_s37d8ed81_tail2_fma(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, m, _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37d8ed81_tail_mulAdd(float _t30, float _t20, float _t31, float _t17, float _t15, float _t16, float _t32, float _t13, float _t12, float _t14, float _t35, float _t36, float _t29, float _t2, float _t27, float _t28, Float4x4 m, float _t0, float _t1) {
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t44 = java.lang.Math.abs(_t35);
        float _t45 = java.lang.Math.abs(_t36);
        float _t48 = java.lang.Math.abs(_t39);
        float _t49 = java.lang.Math.abs(_t40);
        float _t52 = java.lang.Math.abs(_t42);
        float _t53 = java.lang.Math.abs(_t41);
        float _t75, _t78;
        if (_t44 < _t45) {
            _t75 = _t37;
            _t78 = 0.0f;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
        }
        float _t76, _t79;
        if (_t48 < _t49) {
            _t76 = _t38;
            _t79 = 0.0f;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
        }
        float _t77, _t80;
        if (_t52 < _t53) {
            _t77 = _t43;
            _t80 = 0.0f;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
        }
        return makeFromMatrix_degenerate_s37d8ed81_tail2_mulAdd(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, m, _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37d8ed81_tail2_fma(float _t44, float _t45, float _t36, float _t35, float _t48, float _t49, float _t40, float _t39, float _t52, float _t53, float _t41, float _t42, float _t78, float _t75, float _t79, float _t76, float _t80, float _t77, float _t27, float _t28, float _t29, float _t38, float _t37, float _t43, Float4x4 m, float _t56, float _t0, float _t1) {
        float _t90 = _t44 < _t45 ? -_t36 : _t35;
        float _t91 = _t48 < _t49 ? -_t40 : _t39;
        float _t92 = _t52 < _t53 ? -_t41 : _t42;
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t78, _t78, java.lang.Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t92, _t92, _t77 * _t77))));
        return makeFromMatrix_degenerate_s37d8ed81_tail3_fma(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37d8ed81_tail2_mulAdd(float _t44, float _t45, float _t36, float _t35, float _t48, float _t49, float _t40, float _t39, float _t52, float _t53, float _t41, float _t42, float _t78, float _t75, float _t79, float _t76, float _t80, float _t77, float _t27, float _t28, float _t29, float _t38, float _t37, float _t43, Float4x4 m, float _t56, float _t0, float _t1) {
        float _t90 = _t44 < _t45 ? -_t36 : _t35;
        float _t91 = _t48 < _t49 ? -_t40 : _t39;
        float _t92 = _t52 < _t53 ? -_t41 : _t42;
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(((_t78) * (_t78) + (((_t90) * (_t90) + (_t75 * _t75))))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(((_t79) * (_t79) + (((_t91) * (_t91) + (_t76 * _t76))))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(((_t80) * (_t80) + (((_t92) * (_t92) + (_t77 * _t77))))));
        return makeFromMatrix_degenerate_s37d8ed81_tail3_mulAdd(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37d8ed81_tail3_fma(float _t27, float _t28, float _t29, float _t105, float _t38, float _t108, float _t39, float _t117, float _t35, float _t37, float _t41, float _t118, float _t36, float _t109, float _t42, float _t119, float _t106, float _t40, float _t43, float _t107, float _t110, Float4x4 m, float _t56, float _t0, float _t1) {
        float _t168, _t169;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t169 = 0.0f;
                } else {
                    _t168 = _t105;
                    _t169 = java.lang.Math.fma(_t35, _t105, -(_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0f ? java.lang.Math.fma(_t38, _t108, -_t39 * _t117) : java.lang.Math.fma(_t35, _t38, _t37 * -_t39);
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t118 : java.lang.Math.fma(_t35, _t41, -(_t36 * _t42)) : _t38;
        }
        return makeFromMatrix_degenerate_s37d8ed81_tail4_fma(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37d8ed81_tail3_mulAdd(float _t27, float _t28, float _t29, float _t105, float _t38, float _t108, float _t39, float _t117, float _t35, float _t37, float _t41, float _t118, float _t36, float _t109, float _t42, float _t119, float _t106, float _t40, float _t43, float _t107, float _t110, Float4x4 m, float _t56, float _t0, float _t1) {
        float _t168, _t169;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t169 = 0.0f;
                } else {
                    _t168 = _t105;
                    _t169 = ((_t35) * (_t105) - (_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0f ? ((_t38) * (_t108) - (_t39 * _t117)) : ((_t35) * (_t38) - (_t37 * _t39));
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t118 : ((_t35) * (_t41) - (_t36 * _t42)) : _t38;
        }
        return makeFromMatrix_degenerate_s37d8ed81_tail4_mulAdd(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37d8ed81_tail4_fma(float _t27, float _t28, float _t29, float _t119, float _t39, float _t106, float _t40, float _t108, float _t36, float _t35, float _t43, float _t107, float _t37, float _t109, float _t42, float _t41, float _t118, float _t38, float _t117, float _t110, float _t105, float _t169, float _t168, Float4x4 m, float _t56, float _t0, float _t1) {
        float _t170, _t171;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t170 = 1.0f;
                    _t171 = 0.0f;
                } else {
                    _t170 = _t119;
                    _t171 = java.lang.Math.fma(_t37, _t109, -_t35 * _t119);
                }
            } else {
                _t170 = _t29 <= 0.0f ? java.lang.Math.fma(_t39, _t106, -(_t40 * _t108)) : java.lang.Math.fma(_t36, _t39, -_t35 * _t40);
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t107 : java.lang.Math.fma(_t37, _t42, -_t35 * _t43) : _t40;
        }
        return makeFromMatrix_degenerate_s37d8ed81_tail5_fma(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37d8ed81_tail4_mulAdd(float _t27, float _t28, float _t29, float _t119, float _t39, float _t106, float _t40, float _t108, float _t36, float _t35, float _t43, float _t107, float _t37, float _t109, float _t42, float _t41, float _t118, float _t38, float _t117, float _t110, float _t105, float _t169, float _t168, Float4x4 m, float _t56, float _t0, float _t1) {
        float _t170, _t171;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t170 = 1.0f;
                    _t171 = 0.0f;
                } else {
                    _t170 = _t119;
                    _t171 = ((_t37) * (_t109) - (_t35 * _t119));
                }
            } else {
                _t170 = _t29 <= 0.0f ? ((_t39) * (_t106) - (_t40 * _t108)) : ((_t36) * (_t39) - (_t35 * _t40));
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t107 : ((_t37) * (_t42) - (_t35 * _t43)) : _t40;
        }
        return makeFromMatrix_degenerate_s37d8ed81_tail5_mulAdd(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37d8ed81_tail5_fma(float _t29, float _t27, float _t28, float _t108, float _t41, float _t118, float _t43, float _t107, float _t38, float _t40, float _t35, float _t109, float _t117, float _t106, float _t37, float _t36, float _t42, float _t110, float _t119, float _t105, float _t39, float _t169, float _t170, float _t168, float _t171, Float4x4 m, float _t56, float _t0, float _t1) {
        float _t172, _t173;
        if (_t29 <= 0.0f) {
            if (_t27 <= 0.0f) {
                if (_t28 <= 0.0f) {
                    _t172 = 0.0f;
                    _t173 = 0.0f;
                } else {
                    _t172 = _t108;
                    _t173 = java.lang.Math.fma(_t40, _t117, -(_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0f ? java.lang.Math.fma(_t41, _t118, -(_t43 * _t107)) : java.lang.Math.fma(_t41, _t38, -(_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0f ? _t28 <= 0.0f ? _t109 : java.lang.Math.fma(_t37, _t40, -(_t36 * _t38)) : _t42;
        }
        return makeFromMatrix_degenerate_s37d8ed81_tail6_fma(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37d8ed81_tail5_mulAdd(float _t29, float _t27, float _t28, float _t108, float _t41, float _t118, float _t43, float _t107, float _t38, float _t40, float _t35, float _t109, float _t117, float _t106, float _t37, float _t36, float _t42, float _t110, float _t119, float _t105, float _t39, float _t169, float _t170, float _t168, float _t171, Float4x4 m, float _t56, float _t0, float _t1) {
        float _t172, _t173;
        if (_t29 <= 0.0f) {
            if (_t27 <= 0.0f) {
                if (_t28 <= 0.0f) {
                    _t172 = 0.0f;
                    _t173 = 0.0f;
                } else {
                    _t172 = _t108;
                    _t173 = ((_t40) * (_t117) - (_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0f ? ((_t41) * (_t118) - (_t43 * _t107)) : ((_t41) * (_t38) - (_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0f ? _t28 <= 0.0f ? _t109 : ((_t37) * (_t40) - (_t36 * _t38)) : _t42;
        }
        return makeFromMatrix_degenerate_s37d8ed81_tail6_mulAdd(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37d8ed81_tail6_fma(float _t28, float _t29, float _t27, float _t110, float _t36, float _t119, float _t37, float _t105, float _t43, float _t41, float _t39, float _t117, float _t42, float _t107, float _t40, float _t106, float _t118, float _t38, float _t173, float _t169, float _t170, float _t168, float _t171, float _t172, Float4x4 m, float _t56, float _t0, float _t1) {
        float _t174, _t175;
        if (_t28 <= 0.0f) {
            if (_t29 <= 0.0f) {
                if (_t27 <= 0.0f) {
                    _t174 = 1.0f;
                    _t175 = 0.0f;
                } else {
                    _t174 = _t110;
                    _t175 = java.lang.Math.fma(_t42, _t107, -(_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0f ? java.lang.Math.fma(_t36, _t119, -(_t37 * _t105)) : java.lang.Math.fma(_t36, _t43, -(_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t117 : java.lang.Math.fma(_t42, _t40, -(_t41 * _t39)) : _t37;
        }
        return makeFromMatrix_degenerate_s37d8ed81_tail7_fma(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37d8ed81_tail6_mulAdd(float _t28, float _t29, float _t27, float _t110, float _t36, float _t119, float _t37, float _t105, float _t43, float _t41, float _t39, float _t117, float _t42, float _t107, float _t40, float _t106, float _t118, float _t38, float _t173, float _t169, float _t170, float _t168, float _t171, float _t172, Float4x4 m, float _t56, float _t0, float _t1) {
        float _t174, _t175;
        if (_t28 <= 0.0f) {
            if (_t29 <= 0.0f) {
                if (_t27 <= 0.0f) {
                    _t174 = 1.0f;
                    _t175 = 0.0f;
                } else {
                    _t174 = _t110;
                    _t175 = ((_t42) * (_t107) - (_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0f ? ((_t36) * (_t119) - (_t37 * _t105)) : ((_t36) * (_t43) - (_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t117 : ((_t42) * (_t40) - (_t41 * _t39)) : _t37;
        }
        return makeFromMatrix_degenerate_s37d8ed81_tail7_mulAdd(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37d8ed81_tail7_fma(float _t29, float _t27, float _t28, float _t106, float _t43, float _t110, float _t42, float _t118, float _t39, float _t38, float _t36, float _t173, float _t169, float _t170, float _t174, float _t168, float _t171, float _t172, float _t175, Float4x4 m, float _t56, float _t0, float _t1) {
        float _t176 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t28 <= 0.0f ? 1.0f : _t106 : _t28 <= 0.0f ? java.lang.Math.fma(_t43, _t110, -_t42 * _t118) : java.lang.Math.fma(_t43, _t39, -_t42 * _t38) : _t36;
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
        return makeFromMatrix_degenerate_s37d8ed81_tail8(_t199, _t171, _t198, _t168, _t197, _t170, _t174, m, _t173 - _t169, Math.max(_t170, _t174), _t198 + _t168, _t173 + _t169, _t196, _t56, _t27, _t0, _t28, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37d8ed81_tail7_mulAdd(float _t29, float _t27, float _t28, float _t106, float _t43, float _t110, float _t42, float _t118, float _t39, float _t38, float _t36, float _t173, float _t169, float _t170, float _t174, float _t168, float _t171, float _t172, float _t175, Float4x4 m, float _t56, float _t0, float _t1) {
        float _t176 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t28 <= 0.0f ? 1.0f : _t106 : _t28 <= 0.0f ? ((_t43) * (_t110) - (_t42 * _t118)) : ((_t43) * (_t39) - (_t42 * _t38)) : _t36;
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
        return makeFromMatrix_degenerate_s37d8ed81_tail8(_t199, _t171, _t198, _t168, _t197, _t170, _t174, m, _t173 - _t169, Math.max(_t170, _t174), _t198 + _t168, _t173 + _t169, _t196, _t56, _t27, _t0, _t28, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static FloatTransform makeFromMatrix_degenerate_s37d8ed81_tail8(float _t199, float _t171, float _t198, float _t168, float _t197, float _t170, float _t174, Float4x4 m, float _t185, float _t186, float _t202, float _t187, float _t196, float _t56, float _t27, float _t0, float _t28, float _t1) {
        float _t203 = _t199 + _t171;
        float _t209 = _t197 + _t170 + _t174;
        float _t210 = 1.0f + _t209;
        float _t211 = 1.0f + _t197 - _t170 - _t174;
        float _t212 = 1.0f + _t170 - _t197 - _t174;
        float _t213 = 1.0f + _t174 - _t197 - _t170;
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t213));
        float _sfx0 = m.m03();
        float _sfx1 = m.m13();
        float _sfx2 = m.m23();
        float _sfx3 = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        return makeFromMatrix_degenerate_s37cad23f_tail9(_t209, _sp0, _t171 - _t199, _t197, _t186, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t211)), _t202, _t170, _t174, _t212, _sp2, _t187, _t198 - _t168, _t203, _sp1, _t213, _t210, _t185, _t196, _t56, _t27, _t0, _t28, _t1, _sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static FloatTransform makeFromMatrix_degenerate_fma(Float4x4 m) {
        float _m_m01 = m.m01();
        float _m_m11 = m.m11();
        float _m_m21 = m.m21();
        float _t0 = unitScale(_m_m01, _m_m11, _m_m21);
        float _m_m02 = m.m02();
        float _m_m12 = m.m12();
        float _m_m22 = m.m22();
        float _t1 = unitScale(_m_m02, _m_m12, _m_m22);
        float _m_m00 = m.m00();
        float _m_m10 = m.m10();
        float _m_m20 = m.m20();
        float _t2 = unitScale(_m_m00, _m_m10, _m_m20);
        float _t12 = _m_m21 * _t0;
        float _t13 = _m_m01 * _t0;
        float _t14 = _m_m11 * _t0;
        float _t15 = _m_m22 * _t1;
        float _t16 = _m_m02 * _t1;
        float _t17 = _m_m12 * _t1;
        float _t18 = _m_m20 * _t2;
        float _t19 = _m_m00 * _t2;
        float _t20 = _m_m10 * _t2;
        float _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_s37d8ed81_tail_fma(_t30, _t20, (1.0f / (float) java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0f / (float) java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t27, _t28, m, _t0, _t1);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static FloatTransform makeFromMatrix_degenerate_mulAdd(Float4x4 m) {
        float _m_m01 = m.m01();
        float _m_m11 = m.m11();
        float _m_m21 = m.m21();
        float _t0 = unitScale(_m_m01, _m_m11, _m_m21);
        float _m_m02 = m.m02();
        float _m_m12 = m.m12();
        float _m_m22 = m.m22();
        float _t1 = unitScale(_m_m02, _m_m12, _m_m22);
        float _m_m00 = m.m00();
        float _m_m10 = m.m10();
        float _m_m20 = m.m20();
        float _t2 = unitScale(_m_m00, _m_m10, _m_m20);
        float _t12 = _m_m21 * _t0;
        float _t13 = _m_m01 * _t0;
        float _t14 = _m_m11 * _t0;
        float _t15 = _m_m22 * _t1;
        float _t16 = _m_m02 * _t1;
        float _t17 = _m_m12 * _t1;
        float _t18 = _m_m20 * _t2;
        float _t19 = _m_m00 * _t2;
        float _t20 = _m_m10 * _t2;
        float _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        float _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_s37d8ed81_tail_mulAdd(_t30, _t20, (1.0f / (float) java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0f / (float) java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t27, _t28, m, _t0, _t1);
    }

    /**
     * Create the given rigid transform's motion (translation and rotation), with scale = 1.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param r the rigid transform to convert
     * @return the resulting transform
     */
    public static FloatTransform makeFromRigid(FloatRigid r) {
        float rTX = r.tX();
        float rTY = r.tY();
        float rTZ = r.tZ();
        float rRX = r.rX();
        float rRY = r.rY();
        float rRZ = r.rZ();
        float rRW = r.rW();
        return new FloatTransform(rTX, rTY, rTZ, rRX, rRY, rRZ, rRW, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Create the given rigid transform's motion (translation and rotation), with scale = 1.
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
     * @return the resulting transform
     */
    public static FloatTransform makeFromRigid(float rTX, float rTY, float rTZ, float rRX, float rRY, float rRZ, float rRW) {
        return new FloatTransform(rTX, rTY, rTZ, rRX, rRY, rRZ, rRW, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Convert this transform's rigid motion (rotation and translation) to a unit dual quaternion;
     * the scale is dropped (dual quaternions cannot represent it), returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the resulting dual quaternion
     */
    public FloatDualQuat toDualQuat() {
        if (!org.joml2.internal.FmaSwitch.ON) return toDualQuat_mulAdd();
        float _t0 = -this.tZ;
        return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5f * java.lang.Math.fma(_t0, this.rY, java.lang.Math.fma(this.tX, this.rW, this.tY * this.rZ)), 0.5f * java.lang.Math.fma(this.tZ, this.rX, java.lang.Math.fma(this.tY, this.rW, -this.tX * this.rZ)), 0.5f * java.lang.Math.fma(this.tZ, this.rW, java.lang.Math.fma(this.tX, this.rY, -this.tY * this.rX)), 0.5f * java.lang.Math.fma(_t0, this.rZ, java.lang.Math.fma(-this.tY, this.rY, -this.tX * this.rX)));
    }

    /** {@code toDualQuat} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatDualQuat toDualQuat_mulAdd() {
        float _t0 = -this.tZ;
        return new FloatDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5f * ((_t0) * (this.rY) + (((this.tX) * (this.rW) + (this.tY * this.rZ)))), 0.5f * ((this.tZ) * (this.rX) + (((this.tY) * (this.rW) - (this.tX * this.rZ)))), 0.5f * ((this.tZ) * (this.rW) + (((this.tX) * (this.rY) - (this.tY * this.rX)))), 0.5f * ((_t0) * (this.rZ) + (((-this.tY) * (this.rY) - (this.tX * this.rX)))));
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c0_fma(float _t3, float _t0, float _t4, float _t5, float[] _m) {
        _m[0] = java.lang.Math.fma(-java.lang.Math.fma(this.rY, this.rY, _t3), _t0, this.sX);
        _m[4] = java.lang.Math.fma(this.rX, this.rY, _t4) * _t0;
        _m[8] = java.lang.Math.fma(this.rX, this.rZ, -_t5) * _t0;
        _m[12] = 0.0f;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c1_fma(float _t4, float _t1, float _t3, float[] _m) {
        _m[1] = java.lang.Math.fma(this.rX, this.rY, -_t4) * _t1;
        _m[5] = java.lang.Math.fma(-java.lang.Math.fma(this.rX, this.rX, _t3), _t1, this.sY);
        _m[9] = java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ) * _t1;
        _m[13] = 0.0f;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c1_mulAdd(float _t4, float _t1, float _t3, float[] _m) {
        _m[1] = ((this.rX) * (this.rY) - (_t4)) * _t1;
        _m[5] = ((this.sY) - ((this.rX) * (this.rX) + (_t3)) * (_t1));
        _m[9] = ((this.rX) * (this.rW) + (this.rY * this.rZ)) * _t1;
        _m[13] = 0.0f;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c2_fma(float _t5, float _t2, float[] _m) {
        _m[2] = java.lang.Math.fma(this.rX, this.rZ, _t5) * _t2;
        _m[6] = java.lang.Math.fma(this.rY, this.rZ, -(this.rX * this.rW)) * _t2;
        _m[10] = java.lang.Math.fma(-java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), _t2, this.sZ);
        _m[14] = 0.0f;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c2_mulAdd(float _t5, float _t2, float[] _m) {
        _m[2] = ((this.rX) * (this.rZ) + (_t5)) * _t2;
        _m[6] = ((this.rY) * (this.rZ) - (this.rX * this.rW)) * _t2;
        _m[10] = ((this.sZ) - ((this.rX) * (this.rX) + (this.rY * this.rY)) * (_t2));
        _m[14] = 0.0f;
    }

    /**
     * Compute the matrix representation of this transform, returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the resulting matrix
     */
    public Float4x4 toMatrix() {
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix_mulAdd();
        float _t3 = this.rZ * this.rZ;
        float _t4 = this.rZ * this.rW;
        float _t5 = this.rY * this.rW;
        float[] _m = new float[16];
        toMatrix_s0_c0_fma(_t3, this.sX + this.sX, _t4, _t5, _m);
        toMatrix_s0_c1_fma(_t4, this.sY + this.sY, _t3, _m);
        toMatrix_s0_c2_fma(_t5, this.sZ + this.sZ, _m);
        _m[3] = this.tX;
        _m[7] = this.tY;
        _m[11] = this.tZ;
        _m[15] = 1.0f;
        return new Float4x4(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7], _m[8], _m[9], _m[10], _m[11], _m[12], _m[13], _m[14], _m[15], Joml.BIT_AFFINE);
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x4 toMatrix_mulAdd() {
        float _t3 = this.rZ * this.rZ;
        float _t4 = this.rZ * this.rW;
        float _t5 = this.rY * this.rW;
        float[] _m = new float[16];
        {
            float _t0 = this.sX + this.sX;
            _m[0] = ((this.sX) - ((this.rY) * (this.rY) + (_t3)) * (_t0));
            _m[4] = ((this.rX) * (this.rY) + (_t4)) * _t0;
            _m[8] = ((this.rX) * (this.rZ) - (_t5)) * _t0;
            _m[12] = 0.0f;
        }
        toMatrix_s0_c1_mulAdd(_t4, this.sY + this.sY, _t3, _m);
        toMatrix_s0_c2_mulAdd(_t5, this.sZ + this.sZ, _m);
        _m[3] = this.tX;
        _m[7] = this.tY;
        _m[11] = this.tZ;
        _m[15] = 1.0f;
        return new Float4x4(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7], _m[8], _m[9], _m[10], _m[11], _m[12], _m[13], _m[14], _m[15], Joml.BIT_AFFINE);
    }

    /**
     * Compute the 3x3 linear block ({@code R * S}) of this transform (the translation is dropped),
     * returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the resulting matrix
     */
    public Float3x3 toMatrix3x3() {
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix3x3_mulAdd();
        float _t0 = this.sX + this.sX;
        float _t1 = this.sY + this.sY;
        float _t2 = this.sZ + this.sZ;
        float _t3 = this.rZ * this.rZ;
        float _t4 = this.rZ * this.rW;
        float _t5 = this.rY * this.rW;
        return new Float3x3(java.lang.Math.fma(-java.lang.Math.fma(this.rY, this.rY, _t3), _t0, this.sX), java.lang.Math.fma(this.rX, this.rY, -_t4) * _t1, java.lang.Math.fma(this.rX, this.rZ, _t5) * _t2, java.lang.Math.fma(this.rX, this.rY, _t4) * _t0, java.lang.Math.fma(-java.lang.Math.fma(this.rX, this.rX, _t3), _t1, this.sY), java.lang.Math.fma(this.rY, this.rZ, -(this.rX * this.rW)) * _t2, java.lang.Math.fma(this.rX, this.rZ, -_t5) * _t0, java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ) * _t1, java.lang.Math.fma(-java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), _t2, this.sZ), 0);
    }

    /** {@code toMatrix3x3} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x3 toMatrix3x3_mulAdd() {
        float _t0 = this.sX + this.sX;
        float _t1 = this.sY + this.sY;
        float _t2 = this.sZ + this.sZ;
        float _t3 = this.rZ * this.rZ;
        float _t4 = this.rZ * this.rW;
        float _t5 = this.rY * this.rW;
        return new Float3x3(((this.sX) - ((this.rY) * (this.rY) + (_t3)) * (_t0)), ((this.rX) * (this.rY) - (_t4)) * _t1, ((this.rX) * (this.rZ) + (_t5)) * _t2, ((this.rX) * (this.rY) + (_t4)) * _t0, ((this.sY) - ((this.rX) * (this.rX) + (_t3)) * (_t1)), ((this.rY) * (this.rZ) - (this.rX * this.rW)) * _t2, ((this.rX) * (this.rZ) - (_t5)) * _t0, ((this.rX) * (this.rW) + (this.rY * this.rZ)) * _t1, ((this.sZ) - ((this.rX) * (this.rX) + (this.rY * this.rY)) * (_t2)), 0);
    }

    /**
     * Compute the 3x4 matrix representation of this transform (the omitted last row is implicitly
     * {@code 0, 0, 0, 1}), returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the resulting matrix
     */
    public Float3x4 toMatrix3x4() {
        if (!org.joml2.internal.FmaSwitch.ON) return toMatrix3x4_mulAdd();
        float _t0 = this.sX + this.sX;
        float _t1 = this.sY + this.sY;
        float _t2 = this.sZ + this.sZ;
        float _t3 = this.rZ * this.rZ;
        float _t4 = this.rZ * this.rW;
        float _t5 = this.rY * this.rW;
        return new Float3x4(java.lang.Math.fma(-java.lang.Math.fma(this.rY, this.rY, _t3), _t0, this.sX), java.lang.Math.fma(this.rX, this.rY, -_t4) * _t1, java.lang.Math.fma(this.rX, this.rZ, _t5) * _t2, this.tX, java.lang.Math.fma(this.rX, this.rY, _t4) * _t0, java.lang.Math.fma(-java.lang.Math.fma(this.rX, this.rX, _t3), _t1, this.sY), java.lang.Math.fma(this.rY, this.rZ, -(this.rX * this.rW)) * _t2, this.tY, java.lang.Math.fma(this.rX, this.rZ, -_t5) * _t0, java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ) * _t1, java.lang.Math.fma(-java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), _t2, this.sZ), this.tZ, Joml.BIT_AFFINE);
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x4 toMatrix3x4_mulAdd() {
        float _t0 = this.sX + this.sX;
        float _t1 = this.sY + this.sY;
        float _t2 = this.sZ + this.sZ;
        float _t3 = this.rZ * this.rZ;
        float _t4 = this.rZ * this.rW;
        float _t5 = this.rY * this.rW;
        return new Float3x4(((this.sX) - ((this.rY) * (this.rY) + (_t3)) * (_t0)), ((this.rX) * (this.rY) - (_t4)) * _t1, ((this.rX) * (this.rZ) + (_t5)) * _t2, this.tX, ((this.rX) * (this.rY) + (_t4)) * _t0, ((this.sY) - ((this.rX) * (this.rX) + (_t3)) * (_t1)), ((this.rY) * (this.rZ) - (this.rX * this.rW)) * _t2, this.tY, ((this.rX) * (this.rZ) - (_t5)) * _t0, ((this.rX) * (this.rW) + (this.rY * this.rZ)) * _t1, ((this.sZ) - ((this.rX) * (this.rX) + (this.rY * this.rY)) * (_t2)), this.tZ, Joml.BIT_AFFINE);
    }

    /**
     * Narrow this transform to a rigid transform (translation and rotation; the scale is dropped),
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting rigid transform
     */
    public FloatRigid toRigid() {
        return new FloatRigid(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW);
    }

    /**
     * Convert this transform to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code DoubleTransform} holding the result
     */
    public DoubleTransform toDouble() {
        return new DoubleTransform(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW, this.sX, this.sY, this.sZ);
    }

    /**
     * Create an identity transform.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting transform
     */
    public static FloatTransform makeIdentity() {
        return new FloatTransform(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Create a new transform representing a pure rotation by {@code rotation} (zero translation,
     * unit scale).
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @return the resulting transform
     */
    public FloatTransform set(FloatQuat rotation) {
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        return new FloatTransform(0.0f, 0.0f, 0.0f, rotationX, rotationY, rotationZ, rotationW, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Create a new transform representing a pure rotation by ({@code rotationX}, {@code rotationY},
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
     * @return the resulting transform
     */
    public FloatTransform set(float rotationX, float rotationY, float rotationZ, float rotationW) {
        return new FloatTransform(0.0f, 0.0f, 0.0f, rotationX, rotationY, rotationZ, rotationW, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Create a new transform representing a pure rotation by {@code rotation} (zero translation,
     * unit scale).
     * <p>
     * Alias for {@code set}.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @return the resulting transform
     */
    public static FloatTransform makeRotation(FloatQuat rotation) {
        float rotationX = rotation.x();
        float rotationY = rotation.y();
        float rotationZ = rotation.z();
        float rotationW = rotation.w();
        return new FloatTransform(0.0f, 0.0f, 0.0f, rotationX, rotationY, rotationZ, rotationW, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Create a new transform representing a pure rotation by ({@code rotationX}, {@code rotationY},
     * {@code rotationZ}, {@code rotationW}) (zero translation, unit scale).
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
     * @return the resulting transform
     */
    public static FloatTransform makeRotation(float rotationX, float rotationY, float rotationZ, float rotationW) {
        return new FloatTransform(0.0f, 0.0f, 0.0f, rotationX, rotationY, rotationZ, rotationW, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Create a new transform representing a pure translation by {@code translation} (identity
     * rotation, unit scale).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation
     * @return the resulting transform
     */
    public FloatTransform set(Float3 translation) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        return new FloatTransform(translationX, translationY, translationZ, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Create a new transform representing a pure translation by ({@code translationX},
     * {@code translationY}, {@code translationZ}) (identity rotation, unit scale).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @return the resulting transform
     */
    public FloatTransform set(float translationX, float translationY, float translationZ) {
        return new FloatTransform(translationX, translationY, translationZ, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Create a new transform representing a pure translation by {@code translation} (identity
     * rotation, unit scale).
     * <p>
     * Alias for {@code set}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation
     * @return the resulting transform
     */
    public static FloatTransform makeTranslation(Float3 translation) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        return new FloatTransform(translationX, translationY, translationZ, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Create a new transform representing a pure translation by ({@code translationX},
     * {@code translationY}, {@code translationZ}) (identity rotation, unit scale).
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
     * @return the resulting transform
     */
    public static FloatTransform makeTranslation(float translationX, float translationY, float translationZ) {
        return new FloatTransform(translationX, translationY, translationZ, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Interpolate between this transform and {@code other} using the interpolation factor
     * {@code t}, interpolating translation and scale linearly and the rotation via shortest-arc
     * slerp, returning the result as a value.
     * <p>
     * The interpolation starts at this transform (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (1e8 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the rotation of this transform must have unit length; the rotation of
     * {@code other} must have unit length; {@code t} must lie in {@code [0, 1]}.
     *
     * @param other the transform to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting transform
     */
    public FloatTransform lerp(FloatTransform other, float t) {
        float otherTX = other.tX;
        float otherTY = other.tY;
        float otherTZ = other.tZ;
        float otherRX = other.rX;
        float otherRY = other.rY;
        float otherRZ = other.rZ;
        float otherRW = other.rW;
        float otherSX = other.sX;
        float otherSY = other.sY;
        float otherSZ = other.sZ;
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, t);
        float _t12 = java.lang.Math.fma(otherRW, this.rW, java.lang.Math.fma(otherRZ, this.rZ, java.lang.Math.fma(otherRX, this.rX, otherRY * this.rY)));
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
        float _t29 = java.lang.Math.fma(-_t15, _t15, 1.0f);
        float _t32 = Math.sinWithCos(_t18) * (1.0f / (float) java.lang.Math.sqrt(_t29));
        return lerp_s7d7bed2d_tail_fma((float) java.lang.Math.sqrt(_t29), java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18)), _t32, _t21, t, 1.0f - t, _t22, _t23, _t24, otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private FloatTransform lerp_s7d7bed2d_tail_fma(float _t30, float _t38, float _t32, float _t21, float t, float _t1, float _t22, float _t23, float _t24, float otherTX, float otherTY, float otherTZ, float otherSX, float otherSY, float otherSZ) {
        float _t43, _t44, _t45, _t46;
        if (_t30 > 0.0f) {
            _t43 = java.lang.Math.fma(this.rW, _t38, _t32 * _t21);
            _t44 = java.lang.Math.fma(this.rZ, _t38, _t32 * _t22);
            _t45 = java.lang.Math.fma(this.rX, _t38, _t32 * _t23);
            _t46 = java.lang.Math.fma(this.rY, _t38, _t32 * _t24);
        } else {
            _t43 = java.lang.Math.fma(t, _t21, this.rW * _t1);
            _t44 = java.lang.Math.fma(t, _t22, this.rZ * _t1);
            _t45 = java.lang.Math.fma(t, _t23, this.rX * _t1);
            _t46 = java.lang.Math.fma(t, _t24, this.rY * _t1);
        }
        float _t50 = java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, java.lang.Math.fma(_t45, _t45, _t46 * _t46)));
        float _sfx0 = java.lang.Math.fma(t, otherTX - this.tX, this.tX);
        float _sfx1 = java.lang.Math.fma(t, otherTY - this.tY, this.tY);
        return lerp_s7d7bed2d_tail2_fma(t, otherTZ, _t50, (1.0f / (float) java.lang.Math.sqrt(_t50)), _t45, _t46, _t44, _t43, otherSX, otherSY, otherSZ, _sfx0, _sfx1);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private FloatTransform lerp_s7d7bed2d_tail_mulAdd(float _t30, float _t38, float _t32, float _t21, float t, float _t1, float _t22, float _t23, float _t24, float otherTX, float otherTY, float otherTZ, float otherSX, float otherSY, float otherSZ) {
        float _t43, _t44, _t45, _t46;
        if (_t30 > 0.0f) {
            _t43 = ((this.rW) * (_t38) + (_t32 * _t21));
            _t44 = ((this.rZ) * (_t38) + (_t32 * _t22));
            _t45 = ((this.rX) * (_t38) + (_t32 * _t23));
            _t46 = ((this.rY) * (_t38) + (_t32 * _t24));
        } else {
            _t43 = ((t) * (_t21) + (this.rW * _t1));
            _t44 = ((t) * (_t22) + (this.rZ * _t1));
            _t45 = ((t) * (_t23) + (this.rX * _t1));
            _t46 = ((t) * (_t24) + (this.rY * _t1));
        }
        float _t50 = ((_t43) * (_t43) + (((_t44) * (_t44) + (((_t45) * (_t45) + (_t46 * _t46))))));
        float _sfx0 = ((t) * (otherTX - this.tX) + (this.tX));
        float _sfx1 = ((t) * (otherTY - this.tY) + (this.tY));
        return lerp_s7d7bed2d_tail2_mulAdd(t, otherTZ, _t50, (1.0f / (float) java.lang.Math.sqrt(_t50)), _t45, _t46, _t44, _t43, otherSX, otherSY, otherSZ, _sfx0, _sfx1);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private FloatTransform lerp_s7d7bed2d_tail2_fma(float t, float otherTZ, float _t50, float _t51, float _t45, float _t46, float _t44, float _t43, float otherSX, float otherSY, float otherSZ, float _sfx0, float _sfx1) {
        float _sfx2 = java.lang.Math.fma(t, otherTZ - this.tZ, this.tZ);
        float _sfx3, _sfx4, _sfx5, _sfx6;
        if (_t50 != 0.0f) {
            _sfx3 = _t51 * _t45;
            _sfx4 = _t51 * _t46;
            _sfx5 = _t51 * _t44;
            _sfx6 = _t51 * _t43;
        } else {
            _sfx3 = 0.0f;
            _sfx4 = 0.0f;
            _sfx5 = 0.0f;
            _sfx6 = 0.0f;
        }
        float _sfx7 = java.lang.Math.fma(t, otherSX - this.sX, this.sX);
        float _sfx8 = java.lang.Math.fma(t, otherSY - this.sY, this.sY);
        float _sfx9 = java.lang.Math.fma(t, otherSZ - this.sZ, this.sZ);
        return new FloatTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private FloatTransform lerp_s7d7bed2d_tail2_mulAdd(float t, float otherTZ, float _t50, float _t51, float _t45, float _t46, float _t44, float _t43, float otherSX, float otherSY, float otherSZ, float _sfx0, float _sfx1) {
        float _sfx2 = ((t) * (otherTZ - this.tZ) + (this.tZ));
        float _sfx3, _sfx4, _sfx5, _sfx6;
        if (_t50 != 0.0f) {
            _sfx3 = _t51 * _t45;
            _sfx4 = _t51 * _t46;
            _sfx5 = _t51 * _t44;
            _sfx6 = _t51 * _t43;
        } else {
            _sfx3 = 0.0f;
            _sfx4 = 0.0f;
            _sfx5 = 0.0f;
            _sfx6 = 0.0f;
        }
        float _sfx7 = ((t) * (otherSX - this.sX) + (this.sX));
        float _sfx8 = ((t) * (otherSY - this.sY) + (this.sY));
        float _sfx9 = ((t) * (otherSZ - this.sZ) + (this.sZ));
        return new FloatTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /**
     * Interpolate between this transform and ({@code otherTX}, {@code otherTY}, {@code otherTZ},
     * {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherSX},
     * {@code otherSY}, {@code otherSZ}) using the interpolation factor {@code t}, interpolating
     * translation and scale linearly and the rotation via shortest-arc slerp, returning the result
     * as a value.
     * <p>
     * The interpolation starts at this transform (interpolation factor {@code 0}) and ends at
     * ({@code otherTX}, {@code otherTY}, {@code otherTZ}, {@code otherRX}, {@code otherRY},
     * {@code otherRZ}, {@code otherRW}, {@code otherSX}, {@code otherSY}, {@code otherSZ})
     * (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (1e8 towards 1 ends
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
     * @return the resulting transform
     */
    public FloatTransform lerp(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, float t) {
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, t);
        float _t12 = java.lang.Math.fma(otherRW, this.rW, java.lang.Math.fma(otherRZ, this.rZ, java.lang.Math.fma(otherRX, this.rX, otherRY * this.rY)));
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
        float _t29 = java.lang.Math.fma(-_t15, _t15, 1.0f);
        float _t32 = Math.sinWithCos(_t18) * (1.0f / (float) java.lang.Math.sqrt(_t29));
        return lerp_s7d7bed2d_tail_fma((float) java.lang.Math.sqrt(_t29), java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18)), _t32, _t21, t, 1.0f - t, _t22, _t23, _t24, otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ);
    }

    /** {@code lerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform lerp_mulAdd(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ, float t) {
        float _t12 = ((otherRW) * (this.rW) + (((otherRZ) * (this.rZ) + (((otherRX) * (this.rX) + (otherRY * this.rY))))));
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
        float _t29 = ((1.0f) - (_t15) * (_t15));
        float _t32 = Math.sinWithCos(_t18) * (1.0f / (float) java.lang.Math.sqrt(_t29));
        return lerp_s7d7bed2d_tail_mulAdd((float) java.lang.Math.sqrt(_t29), ((Math.cosWithSin(_t18)) - (_t32) * (_t15)), _t32, _t21, t, 1.0f - t, _t22, _t23, _t24, otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ);
    }

    /**
     * Multiply this transform by {@code other}, returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform mul(FloatTransform other) {
        return mul(other.tX, other.tY, other.tZ, other.rX, other.rY, other.rZ, other.rW, other.sX, other.sY, other.sZ);
    }

    /** Private tail of {@code mul}; reached only through it. */
    private FloatTransform mul_s7c2aaf6f_tail_fma(float otherRX, float otherRW, float otherRZ, float otherRY, float otherSX, float otherSY, float otherSZ, float _sfx0, float _sfx1, float _sfx2) {
        float _sfx3 = java.lang.Math.fma(otherRX, this.rW, otherRW * this.rX) + java.lang.Math.fma(otherRZ, this.rY, otherRY * -this.rZ);
        float _sfx4 = java.lang.Math.fma(otherRY, this.rW, otherRW * this.rY) + java.lang.Math.fma(otherRX, this.rZ, otherRZ * -this.rX);
        float _sfx5 = java.lang.Math.fma(otherRZ, this.rW, otherRW * this.rZ) + java.lang.Math.fma(otherRY, this.rX, otherRX * -this.rY);
        float _sfx6 = java.lang.Math.fma(otherRW, this.rW, otherRX * -this.rX) - java.lang.Math.fma(otherRY, this.rY, otherRZ * this.rZ);
        float _sfx7 = otherSX * this.sX;
        float _sfx8 = otherSY * this.sY;
        float _sfx9 = otherSZ * this.sZ;
        return new FloatTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Private tail of {@code mul}; reached only through it. */
    private FloatTransform mul_s7c2aaf6f_tail_mulAdd(float otherRX, float otherRW, float otherRZ, float otherRY, float otherSX, float otherSY, float otherSZ, float _sfx0, float _sfx1, float _sfx2) {
        float _sfx3 = ((otherRX) * (this.rW) + (otherRW * this.rX)) + ((otherRZ) * (this.rY) - (otherRY * this.rZ));
        float _sfx4 = ((otherRY) * (this.rW) + (otherRW * this.rY)) + ((otherRX) * (this.rZ) - (otherRZ * this.rX));
        float _sfx5 = ((otherRZ) * (this.rW) + (otherRW * this.rZ)) + ((otherRY) * (this.rX) - (otherRX * this.rY));
        float _sfx6 = ((otherRW) * (this.rW) - (otherRX * this.rX)) - ((otherRY) * (this.rY) + (otherRZ * this.rZ));
        float _sfx7 = otherSX * this.sX;
        float _sfx8 = otherSY * this.sY;
        float _sfx9 = otherSZ * this.sZ;
        return new FloatTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /**
     * Multiply this transform by ({@code otherTX}, {@code otherTY}, {@code otherTZ},
     * {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherSX},
     * {@code otherSY}, {@code otherSZ}), returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform mul(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ);
        float _t0 = otherTY * this.sY;
        float _t1 = otherTX * this.sX;
        float _t2 = otherTZ * this.sZ;
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, _t0, -(this.rY * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(this.rZ, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(this.rY, _t2, -(this.rZ * _t0));
        float _sfx0 = java.lang.Math.fma(this.rY, _t12, java.lang.Math.fma(-this.rZ, _t13, java.lang.Math.fma(this.rW, _t14, java.lang.Math.fma(otherTX, this.sX, this.tX))));
        float _sfx1 = java.lang.Math.fma(this.rZ, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, java.lang.Math.fma(otherTY, this.sY, this.tY))));
        float _sfx2 = java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rY, _t14, java.lang.Math.fma(this.rW, _t12, java.lang.Math.fma(otherTZ, this.sZ, this.tZ))));
        return mul_s7c2aaf6f_tail_fma(otherRX, otherRW, otherRZ, otherRY, otherSX, otherSY, otherSZ, _sfx0, _sfx1, _sfx2);
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform mul_mulAdd(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ) {
        float _t0 = otherTY * this.sY;
        float _t1 = otherTX * this.sX;
        float _t2 = otherTZ * this.sZ;
        float _t12 = 2.0f * ((this.rX) * (_t0) - (this.rY * _t1));
        float _t13 = 2.0f * ((this.rZ) * (_t1) - (this.rX * _t2));
        float _t14 = 2.0f * ((this.rY) * (_t2) - (this.rZ * _t0));
        float _sfx0 = ((this.rY) * (_t12) + (((((this.rW) * (_t14) + (((otherTX) * (this.sX) + (this.tX))))) - (this.rZ) * (_t13))));
        float _sfx1 = ((this.rZ) * (_t14) + (((((this.rW) * (_t13) + (((otherTY) * (this.sY) + (this.tY))))) - (this.rX) * (_t12))));
        float _sfx2 = ((this.rX) * (_t13) + (((((this.rW) * (_t12) + (((otherTZ) * (this.sZ) + (this.tZ))))) - (this.rY) * (_t14))));
        return mul_s7c2aaf6f_tail_mulAdd(otherRX, otherRW, otherRZ, otherRY, otherSX, otherSY, otherSZ, _sfx0, _sfx1, _sfx2);
    }

    /**
     * Pre-multiply {@code other} onto this transform, returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform preMul(FloatTransform other) {
        float otherTX = other.tX;
        float otherTY = other.tY;
        float otherTZ = other.tZ;
        float otherRX = other.rX;
        float otherRY = other.rY;
        float otherRZ = other.rZ;
        float otherRW = other.rW;
        float otherSX = other.sX;
        float otherSY = other.sY;
        float otherSZ = other.sZ;
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = otherSY * this.tY;
            float _t1 = otherSX * this.tX;
            float _t2 = otherSZ * this.tZ;
            return preMul_s7c2aaf6f_tail_fma(otherRY, _t2, otherRZ, _t0, 2.0f * java.lang.Math.fma(otherRX, _t0, -(otherRY * _t1)), 2.0f * java.lang.Math.fma(otherRZ, _t1, -(otherRX * _t2)), otherRW, otherSX, otherTX, otherRX, otherSY, otherTY, otherSZ, otherTZ);
        } else {
            float _t0 = otherSY * this.tY;
            float _t1 = otherSX * this.tX;
            float _t2 = otherSZ * this.tZ;
            return preMul_s7c2aaf6f_tail_mulAdd(otherRY, _t2, otherRZ, _t0, 2.0f * ((otherRX) * (_t0) - (otherRY * _t1)), 2.0f * ((otherRZ) * (_t1) - (otherRX * _t2)), otherRW, otherSX, otherTX, otherRX, otherSY, otherTY, otherSZ, otherTZ);
        }
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private FloatTransform preMul_s7c2aaf6f_tail_fma(float otherRY, float _t2, float otherRZ, float _t0, float _t12, float _t13, float otherRW, float otherSX, float otherTX, float otherRX, float otherSY, float otherTY, float otherSZ, float otherTZ) {
        float _t14 = 2.0f * java.lang.Math.fma(otherRY, _t2, -(otherRZ * _t0));
        return new FloatTransform(java.lang.Math.fma(otherRY, _t12, java.lang.Math.fma(-otherRZ, _t13, java.lang.Math.fma(otherRW, _t14, java.lang.Math.fma(otherSX, this.tX, otherTX)))), java.lang.Math.fma(otherRZ, _t14, java.lang.Math.fma(-otherRX, _t12, java.lang.Math.fma(otherRW, _t13, java.lang.Math.fma(otherSY, this.tY, otherTY)))), java.lang.Math.fma(otherRX, _t13, java.lang.Math.fma(-otherRY, _t14, java.lang.Math.fma(otherRW, _t12, java.lang.Math.fma(otherSZ, this.tZ, otherTZ)))), java.lang.Math.fma(otherRX, this.rW, otherRW * this.rX) + java.lang.Math.fma(otherRY, this.rZ, -otherRZ * this.rY), java.lang.Math.fma(otherRY, this.rW, otherRW * this.rY) + java.lang.Math.fma(otherRZ, this.rX, -otherRX * this.rZ), java.lang.Math.fma(otherRZ, this.rW, otherRW * this.rZ) + java.lang.Math.fma(otherRX, this.rY, -otherRY * this.rX), java.lang.Math.fma(otherRW, this.rW, -otherRX * this.rX) - java.lang.Math.fma(otherRY, this.rY, otherRZ * this.rZ), otherSX * this.sX, otherSY * this.sY, otherSZ * this.sZ);
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private FloatTransform preMul_s7c2aaf6f_tail_mulAdd(float otherRY, float _t2, float otherRZ, float _t0, float _t12, float _t13, float otherRW, float otherSX, float otherTX, float otherRX, float otherSY, float otherTY, float otherSZ, float otherTZ) {
        float _t14 = 2.0f * ((otherRY) * (_t2) - (otherRZ * _t0));
        return new FloatTransform(((otherRY) * (_t12) + (((((otherRW) * (_t14) + (((otherSX) * (this.tX) + (otherTX))))) - (otherRZ) * (_t13)))), ((otherRZ) * (_t14) + (((((otherRW) * (_t13) + (((otherSY) * (this.tY) + (otherTY))))) - (otherRX) * (_t12)))), ((otherRX) * (_t13) + (((((otherRW) * (_t12) + (((otherSZ) * (this.tZ) + (otherTZ))))) - (otherRY) * (_t14)))), ((otherRX) * (this.rW) + (otherRW * this.rX)) + ((otherRY) * (this.rZ) - (otherRZ * this.rY)), ((otherRY) * (this.rW) + (otherRW * this.rY)) + ((otherRZ) * (this.rX) - (otherRX * this.rZ)), ((otherRZ) * (this.rW) + (otherRW * this.rZ)) + ((otherRX) * (this.rY) - (otherRY * this.rX)), ((otherRW) * (this.rW) - (otherRX * this.rX)) - ((otherRY) * (this.rY) + (otherRZ * this.rZ)), otherSX * this.sX, otherSY * this.sY, otherSZ * this.sZ);
    }

    /**
     * Pre-multiply ({@code otherTX}, {@code otherTY}, {@code otherTZ}, {@code otherRX},
     * {@code otherRY}, {@code otherRZ}, {@code otherRW}, {@code otherSX}, {@code otherSY},
     * {@code otherSZ}) onto this transform, returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform preMul(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = otherSY * this.tY;
            float _t1 = otherSX * this.tX;
            float _t2 = otherSZ * this.tZ;
            return preMul_s7c2aaf6f_tail_fma(otherRY, _t2, otherRZ, _t0, 2.0f * java.lang.Math.fma(otherRX, _t0, -(otherRY * _t1)), 2.0f * java.lang.Math.fma(otherRZ, _t1, -(otherRX * _t2)), otherRW, otherSX, otherTX, otherRX, otherSY, otherTY, otherSZ, otherTZ);
        } else {
            float _t0 = otherSY * this.tY;
            float _t1 = otherSX * this.tX;
            float _t2 = otherSZ * this.tZ;
            return preMul_s7c2aaf6f_tail_mulAdd(otherRY, _t2, otherRZ, _t0, 2.0f * ((otherRX) * (_t0) - (otherRY * _t1)), 2.0f * ((otherRZ) * (_t1) - (otherRX * _t2)), otherRW, otherSX, otherTX, otherRX, otherSY, otherTY, otherSZ, otherTZ);
        }
    }

    /**
     * Compute the difference between this transform and {@code other}, i.e. the
     * translation-rotation-scale transformation {@code D} with {@code this * D = other}, that is
     * {@code D = this^-1 * other}, returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform difference(FloatTransform other) {
        return difference(other.tX, other.tY, other.tZ, other.rX, other.rY, other.rZ, other.rW, other.sX, other.sY, other.sZ);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private FloatTransform difference_s7c2aaf6f_tail_fma(float _t30, float _t31, float _t32, float _sp1, float _t33, float _t34, float _t35, float _sp3, float _sp2, float _sp5, float _sp0, float _sp4, float otherRX, float otherRW, float otherRY, float otherRZ, float otherSX, float _rcp1, float otherSY, float _rcp2, float otherSZ, float _rcp0) {
        float _sfx0 = java.lang.Math.fma(this.rZ, _t30, -this.rY * _t31) + java.lang.Math.fma(this.rW, _t32, _sp1) + (java.lang.Math.fma(this.rZ, _t33, -this.rY * _t34) + java.lang.Math.fma(this.rW, _t35, -_sp3));
        float _sfx1 = java.lang.Math.fma(this.rX, _t31, -this.rZ * _t32) + java.lang.Math.fma(this.rW, _t30, _sp2) + (java.lang.Math.fma(this.rX, _t34, -this.rZ * _t35) + java.lang.Math.fma(this.rW, _t33, -_sp5));
        float _sfx2 = java.lang.Math.fma(this.rY, _t32, -this.rX * _t30) + java.lang.Math.fma(this.rW, _t31, _sp0) + (java.lang.Math.fma(this.rY, _t35, -this.rX * _t33) + java.lang.Math.fma(this.rW, _t34, -_sp4));
        return difference_s7c2aaf6f_tail2_fma(otherRX, otherRW, otherRY, otherRZ, otherSX, _rcp1, otherSY, _rcp2, otherSZ, _rcp0, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private FloatTransform difference_s7c2aaf6f_tail_mulAdd(float _t30, float _t31, float _t32, float _sp1, float _t33, float _t34, float _t35, float _sp3, float _sp2, float _sp5, float _sp0, float _sp4, float otherRX, float otherRW, float otherRY, float otherRZ, float otherSX, float _rcp1, float otherSY, float _rcp2, float otherSZ, float _rcp0) {
        float _sfx0 = ((this.rZ) * (_t30) - (this.rY * _t31)) + ((this.rW) * (_t32) + (_sp1)) + (((this.rZ) * (_t33) - (this.rY * _t34)) + ((this.rW) * (_t35) - (_sp3)));
        float _sfx1 = ((this.rX) * (_t31) - (this.rZ * _t32)) + ((this.rW) * (_t30) + (_sp2)) + (((this.rX) * (_t34) - (this.rZ * _t35)) + ((this.rW) * (_t33) - (_sp5)));
        float _sfx2 = ((this.rY) * (_t32) - (this.rX * _t30)) + ((this.rW) * (_t31) + (_sp0)) + (((this.rY) * (_t35) - (this.rX * _t33)) + ((this.rW) * (_t34) - (_sp4)));
        return difference_s7c2aaf6f_tail2_mulAdd(otherRX, otherRW, otherRY, otherRZ, otherSX, _rcp1, otherSY, _rcp2, otherSZ, _rcp0, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private FloatTransform difference_s7c2aaf6f_tail2_fma(float otherRX, float otherRW, float otherRY, float otherRZ, float otherSX, float _rcp1, float otherSY, float _rcp2, float otherSZ, float _rcp0, float _sfx0, float _sfx1, float _sfx2) {
        float _sfx3 = java.lang.Math.fma(otherRX, this.rW, otherRW * -this.rX) + java.lang.Math.fma(otherRY, this.rZ, otherRZ * -this.rY);
        float _sfx4 = java.lang.Math.fma(otherRY, this.rW, otherRW * -this.rY) + java.lang.Math.fma(otherRZ, this.rX, otherRX * -this.rZ);
        float _sfx5 = java.lang.Math.fma(otherRX, this.rY, otherRY * -this.rX) + java.lang.Math.fma(otherRZ, this.rW, otherRW * -this.rZ);
        float _sfx6 = java.lang.Math.fma(otherRX, this.rX, otherRW * this.rW) - java.lang.Math.fma(otherRZ, -this.rZ, otherRY * -this.rY);
        float _sfx7 = otherSX * _rcp1;
        float _sfx8 = otherSY * _rcp2;
        float _sfx9 = otherSZ * _rcp0;
        return new FloatTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private FloatTransform difference_s7c2aaf6f_tail2_mulAdd(float otherRX, float otherRW, float otherRY, float otherRZ, float otherSX, float _rcp1, float otherSY, float _rcp2, float otherSZ, float _rcp0, float _sfx0, float _sfx1, float _sfx2) {
        float _sfx3 = ((otherRX) * (this.rW) - (otherRW * this.rX)) + ((otherRY) * (this.rZ) - (otherRZ * this.rY));
        float _sfx4 = ((otherRY) * (this.rW) - (otherRW * this.rY)) + ((otherRZ) * (this.rX) - (otherRX * this.rZ));
        float _sfx5 = ((otherRX) * (this.rY) - (otherRY * this.rX)) + ((otherRZ) * (this.rW) - (otherRW * this.rZ));
        float _sfx6 = ((otherRX) * (this.rX) + (otherRW * this.rW)) - ((otherRZ) * (-this.rZ) - (otherRY * this.rY));
        float _sfx7 = otherSX * _rcp1;
        float _sfx8 = otherSY * _rcp2;
        float _sfx9 = otherSZ * _rcp0;
        return new FloatTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /**
     * Compute the difference between this transform and ({@code otherTX}, {@code otherTY},
     * {@code otherTZ}, {@code otherRX}, {@code otherRY}, {@code otherRZ}, {@code otherRW},
     * {@code otherSX}, {@code otherSY}, {@code otherSZ}), i.e. the translation-rotation-scale
     * transformation {@code D} with
     * {@code this * D = (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)},
     * that is
     * {@code D = this^-1 * (otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ)},
     * returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform difference(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return difference_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ);
        float _rcp0 = 1.0f / this.sZ;
        float _sp4 = _rcp0 * this.tZ;
        float _sp0 = otherTZ * _rcp0;
        float _rcp1 = 1.0f / this.sX;
        float _sp3 = _rcp1 * this.tX;
        float _sp1 = otherTX * _rcp1;
        float _rcp2 = 1.0f / this.sY;
        float _sp5 = _rcp2 * this.tY;
        float _sp2 = otherTY * _rcp2;
        return difference_s7c2aaf6f_tail_fma(2.0f * java.lang.Math.fma(_sp0, this.rX, -(_sp1 * this.rZ)), 2.0f * java.lang.Math.fma(_sp1, this.rY, -(_sp2 * this.rX)), 2.0f * java.lang.Math.fma(_sp2, this.rZ, -(_sp0 * this.rY)), _sp1, 2.0f * java.lang.Math.fma(_sp3, this.rZ, -(_sp4 * this.rX)), 2.0f * java.lang.Math.fma(_sp5, this.rX, -(_sp3 * this.rY)), 2.0f * java.lang.Math.fma(_sp4, this.rY, -(_sp5 * this.rZ)), _sp3, _sp2, _sp5, _sp0, _sp4, otherRX, otherRW, otherRY, otherRZ, otherSX, _rcp1, otherSY, _rcp2, otherSZ, _rcp0);
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform difference_mulAdd(float otherTX, float otherTY, float otherTZ, float otherRX, float otherRY, float otherRZ, float otherRW, float otherSX, float otherSY, float otherSZ) {
        float _rcp0 = 1.0f / this.sZ;
        float _sp4 = _rcp0 * this.tZ;
        float _sp0 = otherTZ * _rcp0;
        float _rcp1 = 1.0f / this.sX;
        float _sp3 = _rcp1 * this.tX;
        float _sp1 = otherTX * _rcp1;
        float _rcp2 = 1.0f / this.sY;
        float _sp5 = _rcp2 * this.tY;
        float _sp2 = otherTY * _rcp2;
        return difference_s7c2aaf6f_tail_mulAdd(2.0f * ((_sp0) * (this.rX) - (_sp1 * this.rZ)), 2.0f * ((_sp1) * (this.rY) - (_sp2 * this.rX)), 2.0f * ((_sp2) * (this.rZ) - (_sp0 * this.rY)), _sp1, 2.0f * ((_sp3) * (this.rZ) - (_sp4 * this.rX)), 2.0f * ((_sp5) * (this.rX) - (_sp3 * this.rY)), 2.0f * ((_sp4) * (this.rY) - (_sp5 * this.rZ)), _sp3, _sp2, _sp5, _sp0, _sp4, otherRX, otherRW, otherRY, otherRZ, otherSX, _rcp1, otherSY, _rcp2, otherSZ, _rcp0);
    }

    /**
     * Invert this transform within its shear-free translation-rotation-scale form
     * ({@code inverse.mul(this)} is the identity), returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform invert() {
        if (!org.joml2.internal.FmaSwitch.ON) return invert_mulAdd();
        float _rcp0 = 1.0f / this.sX;
        float _sp0 = this.tX * _rcp0;
        float _rcp1 = 1.0f / this.sZ;
        float _sp1 = this.tZ * _rcp1;
        float _rcp2 = 1.0f / this.sY;
        float _sp2 = this.tY * _rcp2;
        float _t0 = -this.rY;
        float _t1 = -this.rZ;
        float _t2 = -this.rX;
        float _t18 = 2.0f * java.lang.Math.fma(_sp0, this.rZ, -(_sp1 * this.rX));
        float _t19 = 2.0f * java.lang.Math.fma(_sp2, this.rX, -(_sp0 * this.rY));
        float _t20 = 2.0f * java.lang.Math.fma(_sp1, this.rY, -(_sp2 * this.rZ));
        return new FloatTransform(java.lang.Math.fma(this.rZ, _t18, java.lang.Math.fma(_t0, _t19, java.lang.Math.fma(this.rW, _t20, -_sp0))), java.lang.Math.fma(this.rX, _t19, java.lang.Math.fma(_t1, _t20, java.lang.Math.fma(this.rW, _t18, -_sp2))), java.lang.Math.fma(this.rY, _t20, java.lang.Math.fma(_t2, _t18, java.lang.Math.fma(this.rW, _t19, -_sp1))), _t2, _t0, _t1, this.rW, _rcp0, _rcp2, _rcp1);
    }

    /** {@code invert} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform invert_mulAdd() {
        float _rcp0 = 1.0f / this.sX;
        float _sp0 = this.tX * _rcp0;
        float _rcp1 = 1.0f / this.sZ;
        float _sp1 = this.tZ * _rcp1;
        float _rcp2 = 1.0f / this.sY;
        float _sp2 = this.tY * _rcp2;
        float _t0 = -this.rY;
        float _t1 = -this.rZ;
        float _t2 = -this.rX;
        float _t18 = 2.0f * ((_sp0) * (this.rZ) - (_sp1 * this.rX));
        float _t19 = 2.0f * ((_sp2) * (this.rX) - (_sp0 * this.rY));
        float _t20 = 2.0f * ((_sp1) * (this.rY) - (_sp2 * this.rZ));
        return new FloatTransform(((this.rZ) * (_t18) + (((_t0) * (_t19) + (((this.rW) * (_t20) - (_sp0)))))), ((this.rX) * (_t19) + (((_t1) * (_t20) + (((this.rW) * (_t18) - (_sp2)))))), ((this.rY) * (_t20) + (((_t2) * (_t18) + (((this.rW) * (_t19) - (_sp1)))))), _t2, _t0, _t1, this.rW, _rcp0, _rcp2, _rcp1);
    }

    /**
     * Normalize this transform so that its rotation part has unit length, leaving its translation
     * and scale unchanged (a zero-length rotation yields the zero quaternion), returning the result
     * as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting transform
     */
    public FloatTransform normalize() {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t3 = java.lang.Math.fma(this.rW, this.rW, java.lang.Math.fma(this.rZ, this.rZ, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY)));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                return new FloatTransform(this.tX, this.tY, this.tZ, this.rX * _t4, this.rY * _t4, this.rZ * _t4, this.rW * _t4, this.sX, this.sY, this.sZ);
            } else {
                return new FloatTransform(this.tX, this.tY, this.tZ, 0.0f, 0.0f, 0.0f, 0.0f, this.sX, this.sY, this.sZ);
            }
        } else {
            float _t3 = ((this.rW) * (this.rW) + (((this.rZ) * (this.rZ) + (((this.rX) * (this.rX) + (this.rY * this.rY))))));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                return new FloatTransform(this.tX, this.tY, this.tZ, this.rX * _t4, this.rY * _t4, this.rZ * _t4, this.rW * _t4, this.sX, this.sY, this.sZ);
            } else {
                return new FloatTransform(this.tX, this.tY, this.tZ, 0.0f, 0.0f, 0.0f, 0.0f, this.sX, this.sY, this.sZ);
            }
        }
    }

    /**
     * Normalize this transform so that its rotation part has unit length, leaving its translation
     * and scale unchanged, unless the rotation has zero length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the normalized transform, or an empty {@code Optional} when the rotation has zero
     *        length
     */
    public java.util.Optional<FloatTransform> tryNormalize() {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _ct0 = java.lang.Math.fma(this.rW, this.rW, java.lang.Math.fma(this.rZ, this.rZ, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY)));
            if (!(_ct0 > 0.0f)) return java.util.Optional.empty();
            float _t5 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
            return java.util.Optional.of(new FloatTransform(this.tX, this.tY, this.tZ, this.rX * _t5, this.rY * _t5, this.rZ * _t5, this.rW * _t5, this.sX, this.sY, this.sZ));
        } else {
            float _ct0 = ((this.rW) * (this.rW) + (((this.rZ) * (this.rZ) + (((this.rX) * (this.rX) + (this.rY * this.rY))))));
            if (!(_ct0 > 0.0f)) return java.util.Optional.empty();
            float _t5 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
            return java.util.Optional.of(new FloatTransform(this.tX, this.tY, this.tZ, this.rX * _t5, this.rY * _t5, this.rZ * _t5, this.rW * _t5, this.sX, this.sY, this.sZ));
        }
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the X, Y and Z axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first, then Y,
     * then X), returning the result as a value.
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
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesXYZ() {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_mulAdd();
        float _t1 = this.rY * this.rZ;
        float _t2 = this.rY * this.rW;
        float _t10 = 2.0f * java.lang.Math.fma(this.rX, this.rZ, _t2);
        float _t11 = 2.0f * java.lang.Math.fma(this.rX, this.rW, _t1);
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, this.rW, -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rZ * this.rZ), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            return new Float3(Math.atan2(_t11, _t14), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), 0.0f);
        } else {
            return new Float3(Math.atan2(_t12, _t13), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW), 2.0f * java.lang.Math.fma(this.rX, this.rZ, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
        }
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesXYZ_mulAdd() {
        float _t1 = this.rY * this.rZ;
        float _t2 = this.rY * this.rW;
        float _t10 = 2.0f * ((this.rX) * (this.rZ) + (_t2));
        float _t11 = 2.0f * ((this.rX) * (this.rW) + (_t1));
        float _t12 = 2.0f * ((this.rX) * (this.rW) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
        float _t14 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rZ * this.rZ))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            return new Float3(Math.atan2(_t11, _t14), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), 0.0f);
        } else {
            return new Float3(Math.atan2(_t12, _t13), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), Math.atan2(((_t13) * (2.0f * ((this.rX) * (this.rY) + (this.rZ * this.rW))) + (2.0f * ((this.rX) * (this.rZ) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
        }
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the X, Y and Z axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first, then Y,
     * then X), returning the result as a value.
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
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must lie
     * in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesXYZ(float epsilon) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesXYZ_mulAdd(epsilon);
        float _t1 = this.rY * this.rZ;
        float _t2 = this.rY * this.rW;
        float _t10 = 2.0f * java.lang.Math.fma(this.rX, this.rZ, _t2);
        float _t11 = 2.0f * java.lang.Math.fma(this.rX, this.rW, _t1);
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, this.rW, -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rZ * this.rZ), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            return new Float3(Math.atan2(_t11, _t14), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), 0.0f);
        } else {
            return new Float3(Math.atan2(_t12, _t13), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW), 2.0f * java.lang.Math.fma(this.rX, this.rZ, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
        }
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesXYZ_mulAdd(float epsilon) {
        float _t1 = this.rY * this.rZ;
        float _t2 = this.rY * this.rW;
        float _t10 = 2.0f * ((this.rX) * (this.rZ) + (_t2));
        float _t11 = 2.0f * ((this.rX) * (this.rW) + (_t1));
        float _t12 = 2.0f * ((this.rX) * (this.rW) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
        float _t14 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rZ * this.rZ))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            return new Float3(Math.atan2(_t11, _t14), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), 0.0f);
        } else {
            return new Float3(Math.atan2(_t12, _t13), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), Math.atan2(((_t13) * (2.0f * ((this.rX) * (this.rY) + (this.rZ * this.rW))) + (2.0f * ((this.rX) * (this.rZ) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
        }
    }

    /** Private tail of {@code getEulerAnglesXZY}; reached only through it. */
    private Float3 getEulerAnglesXZY_s0_tail_fma(float _t10, float _t14, float _t1, float _t12, float _t9, float _t11) {
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            return new Float3(Math.atan2(2.0f * java.lang.Math.fma(this.rX, this.rW, -_t1), _t12), 0.0f, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        } else {
            return new Float3(Math.atan2(_t9, _t11), Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW), _t9, _t11 * -2.0f * java.lang.Math.fma(this.rX, this.rZ, this.rY * -this.rW)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(this.rY, this.rZ, this.rX * -this.rW))), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        }
    }

    /** Private tail of {@code getEulerAnglesXZY}; reached only through it. */
    private Float3 getEulerAnglesXZY_s0_tail_mulAdd(float _t10, float _t14, float _t1, float _t12, float _t9, float _t11) {
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            return new Float3(Math.atan2(2.0f * ((this.rX) * (this.rW) - (_t1)), _t12), 0.0f, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        } else {
            return new Float3(Math.atan2(_t9, _t11), Math.atan2(((2.0f * ((this.rX) * (this.rY) + (this.rZ * this.rW))) * (_t9) - (_t11 * 2.0f * ((this.rX) * (this.rZ) - (this.rY * this.rW)))), ((_t12) * (_t11) - (_t9 * 2.0f * ((this.rY) * (this.rZ) - (this.rX * this.rW))))), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        }
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the X, Z and Y axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first, then Z,
     * then X), returning the result as a value.
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
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesXZY() {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t1 = this.rY * this.rZ;
            float _t9 = 2.0f * java.lang.Math.fma(this.rX, this.rW, _t1);
            float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rZ * this.rZ), 1.0f);
            return getEulerAnglesXZY_s0_tail_fma(2.0f * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), java.lang.Math.fma(_t11, _t11, _t9 * _t9), _t1, java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f), _t9, _t11);
        } else {
            float _t1 = this.rY * this.rZ;
            float _t9 = 2.0f * ((this.rX) * (this.rW) + (_t1));
            float _t11 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rZ * this.rZ))));
            return getEulerAnglesXZY_s0_tail_mulAdd(2.0f * ((this.rZ) * (this.rW) - (this.rX * this.rY)), ((_t11) * (_t11) + (_t9 * _t9)), _t1, ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rY * this.rY)))), _t9, _t11);
        }
    }

    /** Private tail of {@code getEulerAnglesXZY}; reached only through it. */
    private Float3 getEulerAnglesXZY_s35c5b5a6_tail_fma(float epsilon, float _t10, float _t14, float _t1, float _t12, float _t9, float _t11) {
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            return new Float3(Math.atan2(2.0f * java.lang.Math.fma(this.rX, this.rW, -_t1), _t12), 0.0f, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        } else {
            return new Float3(Math.atan2(_t9, _t11), Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW), _t9, _t11 * -2.0f * java.lang.Math.fma(this.rX, this.rZ, this.rY * -this.rW)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(this.rY, this.rZ, this.rX * -this.rW))), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        }
    }

    /** Private tail of {@code getEulerAnglesXZY}; reached only through it. */
    private Float3 getEulerAnglesXZY_s35c5b5a6_tail_mulAdd(float epsilon, float _t10, float _t14, float _t1, float _t12, float _t9, float _t11) {
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            return new Float3(Math.atan2(2.0f * ((this.rX) * (this.rW) - (_t1)), _t12), 0.0f, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        } else {
            return new Float3(Math.atan2(_t9, _t11), Math.atan2(((2.0f * ((this.rX) * (this.rY) + (this.rZ * this.rW))) * (_t9) - (_t11 * 2.0f * ((this.rX) * (this.rZ) - (this.rY * this.rW)))), ((_t12) * (_t11) - (_t9 * 2.0f * ((this.rY) * (this.rZ) - (this.rX * this.rW))))), Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        }
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the X, Z and Y axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first, then Z,
     * then X), returning the result as a value.
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
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must lie
     * in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesXZY(float epsilon) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t1 = this.rY * this.rZ;
            float _t9 = 2.0f * java.lang.Math.fma(this.rX, this.rW, _t1);
            float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rZ * this.rZ), 1.0f);
            return getEulerAnglesXZY_s35c5b5a6_tail_fma(epsilon, 2.0f * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), java.lang.Math.fma(_t11, _t11, _t9 * _t9), _t1, java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f), _t9, _t11);
        } else {
            float _t1 = this.rY * this.rZ;
            float _t9 = 2.0f * ((this.rX) * (this.rW) + (_t1));
            float _t11 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rZ * this.rZ))));
            return getEulerAnglesXZY_s35c5b5a6_tail_mulAdd(epsilon, 2.0f * ((this.rZ) * (this.rW) - (this.rX * this.rY)), ((_t11) * (_t11) + (_t9 * _t9)), _t1, ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rY * this.rY)))), _t9, _t11);
        }
    }

    /** Private tail of {@code getEulerAnglesYXZ}; reached only through it. */
    private Float3 getEulerAnglesYXZ_s0_tail_fma(float _t10, float _t14, float _t12, float _t9, float _t11, float _t0, float _t2) {
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)), Math.atan2(2.0f * java.lang.Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), _t12), 0.0f);
        } else {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)), Math.atan2(_t9, _t11), Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(this.rX, this.rW, _t0), _t11 * -2.0f * java.lang.Math.fma(this.rX, this.rY, -(this.rZ * this.rW))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(this.rX, this.rZ, -_t2))));
        }
    }

    /** Private tail of {@code getEulerAnglesYXZ}; reached only through it. */
    private Float3 getEulerAnglesYXZ_s0_tail_mulAdd(float _t10, float _t14, float _t12, float _t9, float _t11, float _t0, float _t2) {
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)), Math.atan2(2.0f * ((this.rY) * (this.rW) - (this.rX * this.rZ)), _t12), 0.0f);
        } else {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)), Math.atan2(_t9, _t11), Math.atan2(((_t9) * (2.0f * ((this.rX) * (this.rW) + (_t0))) - (_t11 * 2.0f * ((this.rX) * (this.rY) - (this.rZ * this.rW)))), ((_t11) * (_t12) - (_t9 * 2.0f * ((this.rX) * (this.rZ) - (_t2))))));
        }
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the Y, X and Z axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first, then X,
     * then Y), returning the result as a value.
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
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesYXZ() {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = this.rY * this.rZ;
            float _t2 = this.rY * this.rW;
            float _t9 = 2.0f * java.lang.Math.fma(this.rX, this.rZ, _t2);
            float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
            return getEulerAnglesYXZ_s0_tail_fma(2.0f * java.lang.Math.fma(this.rX, this.rW, -_t0), java.lang.Math.fma(_t11, _t11, _t9 * _t9), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.rY, this.rZ * this.rZ), 1.0f), _t9, _t11, _t0, _t2);
        } else {
            float _t0 = this.rY * this.rZ;
            float _t2 = this.rY * this.rW;
            float _t9 = 2.0f * ((this.rX) * (this.rZ) + (_t2));
            float _t11 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
            return getEulerAnglesYXZ_s0_tail_mulAdd(2.0f * ((this.rX) * (this.rW) - (_t0)), ((_t11) * (_t11) + (_t9 * _t9)), ((1.0f) - (2.0f) * (((this.rY) * (this.rY) + (this.rZ * this.rZ)))), _t9, _t11, _t0, _t2);
        }
    }

    /** Private tail of {@code getEulerAnglesYXZ}; reached only through it. */
    private Float3 getEulerAnglesYXZ_s35c5b5a6_tail_fma(float epsilon, float _t10, float _t14, float _t12, float _t9, float _t11, float _t0, float _t2) {
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)), Math.atan2(2.0f * java.lang.Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), _t12), 0.0f);
        } else {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)), Math.atan2(_t9, _t11), Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(this.rX, this.rW, _t0), _t11 * -2.0f * java.lang.Math.fma(this.rX, this.rY, -(this.rZ * this.rW))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(this.rX, this.rZ, -_t2))));
        }
    }

    /** Private tail of {@code getEulerAnglesYXZ}; reached only through it. */
    private Float3 getEulerAnglesYXZ_s35c5b5a6_tail_mulAdd(float epsilon, float _t10, float _t14, float _t12, float _t9, float _t11, float _t0, float _t2) {
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)), Math.atan2(2.0f * ((this.rY) * (this.rW) - (this.rX * this.rZ)), _t12), 0.0f);
        } else {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)), Math.atan2(_t9, _t11), Math.atan2(((_t9) * (2.0f * ((this.rX) * (this.rW) + (_t0))) - (_t11 * 2.0f * ((this.rX) * (this.rY) - (this.rZ * this.rW)))), ((_t11) * (_t12) - (_t9 * 2.0f * ((this.rX) * (this.rZ) - (_t2))))));
        }
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the Y, X and Z axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first, then X,
     * then Y), returning the result as a value.
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
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must lie
     * in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesYXZ(float epsilon) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = this.rY * this.rZ;
            float _t2 = this.rY * this.rW;
            float _t9 = 2.0f * java.lang.Math.fma(this.rX, this.rZ, _t2);
            float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
            return getEulerAnglesYXZ_s35c5b5a6_tail_fma(epsilon, 2.0f * java.lang.Math.fma(this.rX, this.rW, -_t0), java.lang.Math.fma(_t11, _t11, _t9 * _t9), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.rY, this.rZ * this.rZ), 1.0f), _t9, _t11, _t0, _t2);
        } else {
            float _t0 = this.rY * this.rZ;
            float _t2 = this.rY * this.rW;
            float _t9 = 2.0f * ((this.rX) * (this.rZ) + (_t2));
            float _t11 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
            return getEulerAnglesYXZ_s35c5b5a6_tail_mulAdd(epsilon, 2.0f * ((this.rX) * (this.rW) - (_t0)), ((_t11) * (_t11) + (_t9 * _t9)), ((1.0f) - (2.0f) * (((this.rY) * (this.rY) + (this.rZ * this.rZ)))), _t9, _t11, _t0, _t2);
        }
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the Y, Z and X axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first, then Z,
     * then Y), returning the result as a value.
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
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesYZX() {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYZX_mulAdd();
        float _t2 = this.rZ * this.rW;
        float _t11 = 2.0f * java.lang.Math.fma(this.rX, this.rY, _t2);
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW);
        float _t13 = 2.0f * java.lang.Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.rY, this.rZ * this.rZ), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= java.lang.Math.fma(_t11, _t11, _t17) * 2.2737368E-13f) {
            return new Float3(0.0f, Math.atan2(_t12, _t15), Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        } else {
            return new Float3(Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ), 2.0f * java.lang.Math.fma(this.rX, this.rY, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13)), Math.atan2(_t13, _t14), Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        }
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesYZX_mulAdd() {
        float _t2 = this.rZ * this.rW;
        float _t11 = 2.0f * ((this.rX) * (this.rY) + (_t2));
        float _t12 = 2.0f * ((this.rX) * (this.rZ) + (this.rY * this.rW));
        float _t13 = 2.0f * ((this.rY) * (this.rW) - (this.rX * this.rZ));
        float _t14 = ((1.0f) - (2.0f) * (((this.rY) * (this.rY) + (this.rZ * this.rZ))));
        float _t15 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= ((_t11) * (_t11) + (_t17)) * 2.2737368E-13f) {
            return new Float3(0.0f, Math.atan2(_t12, _t15), Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        } else {
            return new Float3(Math.atan2(((_t14) * (2.0f * ((this.rX) * (this.rW) + (this.rY * this.rZ))) + (2.0f * ((this.rX) * (this.rY) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13))), Math.atan2(_t13, _t14), Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        }
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the Y, Z and X axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first, then Z,
     * then Y), returning the result as a value.
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
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must lie
     * in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesYZX(float epsilon) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesYZX_mulAdd(epsilon);
        float _t2 = this.rZ * this.rW;
        float _t11 = 2.0f * java.lang.Math.fma(this.rX, this.rY, _t2);
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW);
        float _t13 = 2.0f * java.lang.Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.rY, this.rZ * this.rZ), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= epsilon * java.lang.Math.fma(_t11, _t11, _t17)) {
            return new Float3(0.0f, Math.atan2(_t12, _t15), Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        } else {
            return new Float3(Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ), 2.0f * java.lang.Math.fma(this.rX, this.rY, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13)), Math.atan2(_t13, _t14), Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        }
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesYZX_mulAdd(float epsilon) {
        float _t2 = this.rZ * this.rW;
        float _t11 = 2.0f * ((this.rX) * (this.rY) + (_t2));
        float _t12 = 2.0f * ((this.rX) * (this.rZ) + (this.rY * this.rW));
        float _t13 = 2.0f * ((this.rY) * (this.rW) - (this.rX * this.rZ));
        float _t14 = ((1.0f) - (2.0f) * (((this.rY) * (this.rY) + (this.rZ * this.rZ))));
        float _t15 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= epsilon * ((_t11) * (_t11) + (_t17))) {
            return new Float3(0.0f, Math.atan2(_t12, _t15), Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        } else {
            return new Float3(Math.atan2(((_t14) * (2.0f * ((this.rX) * (this.rW) + (this.rY * this.rZ))) + (2.0f * ((this.rX) * (this.rY) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13))), Math.atan2(_t13, _t14), Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        }
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the Z, X and Y axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first, then X,
     * then Z), returning the result as a value.
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
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesZXY() {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZXY_mulAdd();
        float _t1 = this.rZ * this.rZ;
        float _t10 = 2.0f * java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ);
        float _t11 = 2.0f * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW);
        float _t12 = 2.0f * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.rY, _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), 0.0f, Math.atan2(_t11, _t14));
        } else {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW), 2.0f * java.lang.Math.fma(this.rY, this.rZ, -(this.rX * this.rW)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)), Math.atan2(_t12, _t13));
        }
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesZXY_mulAdd() {
        float _t1 = this.rZ * this.rZ;
        float _t10 = 2.0f * ((this.rX) * (this.rW) + (this.rY * this.rZ));
        float _t11 = 2.0f * ((this.rX) * (this.rY) + (this.rZ * this.rW));
        float _t12 = 2.0f * ((this.rZ) * (this.rW) - (this.rX * this.rY));
        float _t13 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((this.rY) * (this.rY) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), 0.0f, Math.atan2(_t11, _t14));
        } else {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), Math.atan2(((_t13) * (2.0f * ((this.rX) * (this.rZ) + (this.rY * this.rW))) + (2.0f * ((this.rY) * (this.rZ) - (this.rX * this.rW)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))), Math.atan2(_t12, _t13));
        }
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the Z, X and Y axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first, then X,
     * then Z), returning the result as a value.
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
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must lie
     * in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesZXY(float epsilon) {
        if (!org.joml2.internal.FmaSwitch.ON) return getEulerAnglesZXY_mulAdd(epsilon);
        float _t1 = this.rZ * this.rZ;
        float _t10 = 2.0f * java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ);
        float _t11 = 2.0f * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW);
        float _t12 = 2.0f * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.rY, _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), 0.0f, Math.atan2(_t11, _t14));
        } else {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW), 2.0f * java.lang.Math.fma(this.rY, this.rZ, -(this.rX * this.rW)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)), Math.atan2(_t12, _t13));
        }
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesZXY_mulAdd(float epsilon) {
        float _t1 = this.rZ * this.rZ;
        float _t10 = 2.0f * ((this.rX) * (this.rW) + (this.rY * this.rZ));
        float _t11 = 2.0f * ((this.rX) * (this.rY) + (this.rZ * this.rW));
        float _t12 = 2.0f * ((this.rZ) * (this.rW) - (this.rX * this.rY));
        float _t13 = ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((this.rY) * (this.rY) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), 0.0f, Math.atan2(_t11, _t14));
        } else {
            return new Float3(Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)), Math.atan2(((_t13) * (2.0f * ((this.rX) * (this.rZ) + (this.rY * this.rW))) + (2.0f * ((this.rY) * (this.rZ) - (this.rX * this.rW)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))), Math.atan2(_t12, _t13));
        }
    }

    /** Private tail of {@code getEulerAnglesZYX}; reached only through it. */
    private Float3 getEulerAnglesZYX_s0_tail_fma(float _t9, float _t13, float _t8, float _t10, float _t11, float _t1) {
        if (_t13 <= java.lang.Math.fma(_t9, _t9, _t13) * 2.2737368E-13f) {
            return new Float3(0.0f, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)), Math.atan2(2.0f * java.lang.Math.fma(this.rZ, this.rW, -this.rX * this.rY), _t11));
        } else {
            return new Float3(Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW), _t10 * -2.0f * java.lang.Math.fma(this.rY, this.rZ, -(this.rX * this.rW))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(this.rX, this.rY, -_t1))), Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)), Math.atan2(_t8, _t10));
        }
    }

    /** Private tail of {@code getEulerAnglesZYX}; reached only through it. */
    private Float3 getEulerAnglesZYX_s0_tail_mulAdd(float _t9, float _t13, float _t8, float _t10, float _t11, float _t1) {
        if (_t13 <= ((_t9) * (_t9) + (_t13)) * 2.2737368E-13f) {
            return new Float3(0.0f, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)), Math.atan2(2.0f * ((this.rZ) * (this.rW) - (this.rX * this.rY)), _t11));
        } else {
            return new Float3(Math.atan2(((_t8) * (2.0f * ((this.rX) * (this.rZ) + (this.rY * this.rW))) - (_t10 * 2.0f * ((this.rY) * (this.rZ) - (this.rX * this.rW)))), ((_t11) * (_t10) - (_t8 * 2.0f * ((this.rX) * (this.rY) - (_t1))))), Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)), Math.atan2(_t8, _t10));
        }
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the Z, Y and X axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first, then Y,
     * then Z), returning the result as a value.
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
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesZYX() {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = this.rZ * this.rZ;
            float _t1 = this.rZ * this.rW;
            float _t8 = 2.0f * java.lang.Math.fma(this.rX, this.rY, _t1);
            float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.rY, _t0), 1.0f);
            return getEulerAnglesZYX_s0_tail_fma(2.0f * java.lang.Math.fma(this.rY, this.rW, -this.rX * this.rZ), java.lang.Math.fma(_t10, _t10, _t8 * _t8), _t8, _t10, java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, _t0), 1.0f), _t1);
        } else {
            float _t0 = this.rZ * this.rZ;
            float _t1 = this.rZ * this.rW;
            float _t8 = 2.0f * ((this.rX) * (this.rY) + (_t1));
            float _t10 = ((1.0f) - (2.0f) * (((this.rY) * (this.rY) + (_t0))));
            return getEulerAnglesZYX_s0_tail_mulAdd(2.0f * ((this.rY) * (this.rW) - (this.rX * this.rZ)), ((_t10) * (_t10) + (_t8 * _t8)), _t8, _t10, ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (_t0)))), _t1);
        }
    }

    /** Private tail of {@code getEulerAnglesZYX}; reached only through it. */
    private Float3 getEulerAnglesZYX_s35c5b5a6_tail_fma(float epsilon, float _t9, float _t13, float _t8, float _t10, float _t11, float _t1) {
        if (_t13 <= epsilon * java.lang.Math.fma(_t9, _t9, _t13)) {
            return new Float3(0.0f, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)), Math.atan2(2.0f * java.lang.Math.fma(this.rZ, this.rW, -this.rX * this.rY), _t11));
        } else {
            return new Float3(Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW), _t10 * -2.0f * java.lang.Math.fma(this.rY, this.rZ, -(this.rX * this.rW))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(this.rX, this.rY, -_t1))), Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)), Math.atan2(_t8, _t10));
        }
    }

    /** Private tail of {@code getEulerAnglesZYX}; reached only through it. */
    private Float3 getEulerAnglesZYX_s35c5b5a6_tail_mulAdd(float epsilon, float _t9, float _t13, float _t8, float _t10, float _t11, float _t1) {
        if (_t13 <= epsilon * ((_t9) * (_t9) + (_t13))) {
            return new Float3(0.0f, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)), Math.atan2(2.0f * ((this.rZ) * (this.rW) - (this.rX * this.rY)), _t11));
        } else {
            return new Float3(Math.atan2(((_t8) * (2.0f * ((this.rX) * (this.rZ) + (this.rY * this.rW))) - (_t10 * 2.0f * ((this.rY) * (this.rZ) - (this.rX * this.rW)))), ((_t11) * (_t10) - (_t8 * 2.0f * ((this.rX) * (this.rY) - (_t1))))), Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)), Math.atan2(_t8, _t10));
        }
    }

    /**
     * Get the Euler angles in radians of this transform for rotations about the Z, Y and X axes, in
     * that order (intrinsic rotations, each about the axes the previous ones turned: the matrix
     * product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first, then Y,
     * then Z), returning the result as a value.
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
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must lie
     * in {@code [0, 1)}.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is at most {@code epsilon} times the sum of the squares it
     *        is formed from (the first angle then carries the coupled rotation and the last is 0);
     *        the overload without it uses {@code 2^-42}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesZYX(float epsilon) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = this.rZ * this.rZ;
            float _t1 = this.rZ * this.rW;
            float _t8 = 2.0f * java.lang.Math.fma(this.rX, this.rY, _t1);
            float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rY, this.rY, _t0), 1.0f);
            return getEulerAnglesZYX_s35c5b5a6_tail_fma(epsilon, 2.0f * java.lang.Math.fma(this.rY, this.rW, -this.rX * this.rZ), java.lang.Math.fma(_t10, _t10, _t8 * _t8), _t8, _t10, java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.rX, this.rX, _t0), 1.0f), _t1);
        } else {
            float _t0 = this.rZ * this.rZ;
            float _t1 = this.rZ * this.rW;
            float _t8 = 2.0f * ((this.rX) * (this.rY) + (_t1));
            float _t10 = ((1.0f) - (2.0f) * (((this.rY) * (this.rY) + (_t0))));
            return getEulerAnglesZYX_s35c5b5a6_tail_mulAdd(epsilon, 2.0f * ((this.rY) * (this.rW) - (this.rX * this.rZ)), ((_t10) * (_t10) + (_t8 * _t8)), _t8, _t10, ((1.0f) - (2.0f) * (((this.rX) * (this.rX) + (_t0)))), _t1);
        }
    }

    /**
     * Get the rotation of this transform, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting quaternion
     */
    public FloatQuat getRotation() {
        return new FloatQuat(this.rX, this.rY, this.rZ, this.rW);
    }

    /**
     * Get the scaling factors of this transform, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float3 getScale() {
        return new Float3(this.sX, this.sY, this.sZ);
    }

    /**
     * Get the translation of this transform, returning the result as a value.
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
     * @return the resulting transform
     */
    public static FloatTransform makeRotationAxis(float angle, Float3 axis) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        return new FloatTransform(0.0f, 0.0f, 0.0f, axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosWithSin(_t0), 1.0f, 1.0f, 1.0f);
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
     * @return the resulting transform
     */
    public static FloatTransform makeRotationAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        return new FloatTransform(0.0f, 0.0f, 0.0f, axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosWithSin(_t0), 1.0f, 1.0f, 1.0f);
    }

    /**
     * Create a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting transform
     */
    public static FloatTransform makeRotationX(float angle) {
        float _t0 = 0.5f * angle;
        return new FloatTransform(0.0f, 0.0f, 0.0f, Math.sinWithCos(_t0), 0.0f, 0.0f, Math.cosWithSin(_t0), 1.0f, 1.0f, 1.0f);
    }

    /**
     * Create a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return the resulting transform
     */
    public static FloatTransform makeRotationXYZ(float angleX, float angleY, float angleZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
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
            return new FloatTransform(0.0f, 0.0f, 0.0f, java.lang.Math.fma(_t9, _t5, _t10 * _t8), java.lang.Math.fma(_t10, _t5, _t9 * -_t8), java.lang.Math.fma(_t11, _t5, _t12 * _t8), java.lang.Math.fma(_t12, _t5, _t11 * -_t8), 1.0f, 1.0f, 1.0f);
        } else {
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
            return new FloatTransform(0.0f, 0.0f, 0.0f, ((_t9) * (_t5) + (_t10 * _t8)), ((_t10) * (_t5) - (_t9 * _t8)), ((_t11) * (_t5) + (_t12 * _t8)), ((_t12) * (_t5) - (_t11 * _t8)), 1.0f, 1.0f, 1.0f);
        }
    }

    /**
     * Create a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return the resulting transform
     */
    public static FloatTransform makeRotationXZY(float angleX, float angleZ, float angleY) {
        if (org.joml2.internal.FmaSwitch.ON) {
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
            return new FloatTransform(0.0f, 0.0f, 0.0f, java.lang.Math.fma(_t9, _t5, -(_t10 * _t8)), java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)), java.lang.Math.fma(_t9, _t8, _t10 * _t5), java.lang.Math.fma(_t12, _t8, _t11 * _t5), 1.0f, 1.0f, 1.0f);
        } else {
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
            return new FloatTransform(0.0f, 0.0f, 0.0f, ((_t9) * (_t5) - (_t10 * _t8)), ((_t11) * (_t8) - (_t12 * _t5)), ((_t9) * (_t8) + (_t10 * _t5)), ((_t12) * (_t8) + (_t11 * _t5)), 1.0f, 1.0f, 1.0f);
        }
    }

    /**
     * Create a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting transform
     */
    public static FloatTransform makeRotationY(float angle) {
        float _t0 = 0.5f * angle;
        return new FloatTransform(0.0f, 0.0f, 0.0f, 0.0f, Math.sinWithCos(_t0), 0.0f, Math.cosWithSin(_t0), 1.0f, 1.0f, 1.0f);
    }

    /**
     * Create a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return the resulting transform
     */
    public static FloatTransform makeRotationYXZ(float angleY, float angleX, float angleZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
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
            return new FloatTransform(0.0f, 0.0f, 0.0f, java.lang.Math.fma(_t9, _t5, _t10 * _t8), java.lang.Math.fma(_t10, _t5, -(_t9 * _t8)), java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)), java.lang.Math.fma(_t12, _t8, _t11 * _t5), 1.0f, 1.0f, 1.0f);
        } else {
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
            return new FloatTransform(0.0f, 0.0f, 0.0f, ((_t9) * (_t5) + (_t10 * _t8)), ((_t10) * (_t5) - (_t9 * _t8)), ((_t11) * (_t8) - (_t12 * _t5)), ((_t12) * (_t8) + (_t11 * _t5)), 1.0f, 1.0f, 1.0f);
        }
    }

    /**
     * Create a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return the resulting transform
     */
    public static FloatTransform makeRotationYZX(float angleY, float angleZ, float angleX) {
        if (org.joml2.internal.FmaSwitch.ON) {
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
            return new FloatTransform(0.0f, 0.0f, 0.0f, java.lang.Math.fma(_t9, _t5, _t10 * _t8), java.lang.Math.fma(_t11, _t5, _t12 * _t8), java.lang.Math.fma(_t12, _t5, _t11 * -_t8), java.lang.Math.fma(_t10, _t5, _t9 * -_t8), 1.0f, 1.0f, 1.0f);
        } else {
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
            return new FloatTransform(0.0f, 0.0f, 0.0f, ((_t9) * (_t5) + (_t10 * _t8)), ((_t11) * (_t5) + (_t12 * _t8)), ((_t12) * (_t5) - (_t11 * _t8)), ((_t10) * (_t5) - (_t9 * _t8)), 1.0f, 1.0f, 1.0f);
        }
    }

    /**
     * Create a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting transform
     */
    public static FloatTransform makeRotationZ(float angle) {
        float _t0 = 0.5f * angle;
        return new FloatTransform(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, Math.sinWithCos(_t0), Math.cosWithSin(_t0), 1.0f, 1.0f, 1.0f);
    }

    /**
     * Create a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return the resulting transform
     */
    public static FloatTransform makeRotationZXY(float angleZ, float angleX, float angleY) {
        if (org.joml2.internal.FmaSwitch.ON) {
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
            return new FloatTransform(0.0f, 0.0f, 0.0f, java.lang.Math.fma(_t9, _t5, _t10 * -_t8), java.lang.Math.fma(_t11, _t5, _t12 * _t8), java.lang.Math.fma(_t9, _t8, _t10 * _t5), java.lang.Math.fma(_t12, _t5, _t11 * -_t8), 1.0f, 1.0f, 1.0f);
        } else {
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
            return new FloatTransform(0.0f, 0.0f, 0.0f, ((_t9) * (_t5) - (_t10 * _t8)), ((_t11) * (_t5) + (_t12 * _t8)), ((_t9) * (_t8) + (_t10 * _t5)), ((_t12) * (_t5) - (_t11 * _t8)), 1.0f, 1.0f, 1.0f);
        }
    }

    /**
     * Create a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return the resulting transform
     */
    public static FloatTransform makeRotationZYX(float angleZ, float angleY, float angleX) {
        if (org.joml2.internal.FmaSwitch.ON) {
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
            return new FloatTransform(0.0f, 0.0f, 0.0f, java.lang.Math.fma(_t9, _t5, -(_t10 * _t8)), java.lang.Math.fma(_t11, _t8, _t12 * _t5), java.lang.Math.fma(_t12, _t8, -(_t11 * _t5)), java.lang.Math.fma(_t10, _t5, _t9 * _t8), 1.0f, 1.0f, 1.0f);
        } else {
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
            return new FloatTransform(0.0f, 0.0f, 0.0f, ((_t9) * (_t5) - (_t10 * _t8)), ((_t11) * (_t8) + (_t12 * _t5)), ((_t12) * (_t8) - (_t11 * _t5)), ((_t10) * (_t5) + (_t9 * _t8)), 1.0f, 1.0f, 1.0f);
        }
    }

    /**
     * Create a scaling transformation that scales by {@code scale}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scale the scale factors
     * @return the resulting transform
     */
    public static FloatTransform makeScaling(Float3 scale) {
        float scaleX = scale.x();
        float scaleY = scale.y();
        float scaleZ = scale.z();
        return new FloatTransform(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, scaleX, scaleY, scaleZ);
    }

    /**
     * Create a scaling transformation that scales by ({@code scaleX}, {@code scaleY},
     * {@code scaleZ}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scaleX the {@code x} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param scaleY the {@code y} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param scaleZ the {@code z} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @return the resulting transform
     */
    public static FloatTransform makeScaling(float scaleX, float scaleY, float scaleZ) {
        return new FloatTransform(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, scaleX, scaleY, scaleZ);
    }

    /**
     * Create a scaling transformation that scales by {@code scale}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scale the scale factor
     * @return the resulting transform
     */
    public static FloatTransform makeScaling(float scale) {
        return new FloatTransform(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, scale, scale, scale);
    }

    /**
     * Apply the rotation represented by the quaternion {@code rotation} to this transform,
     * returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform rotate(FloatQuat rotation) {
        return rotate(rotation.x(), rotation.y(), rotation.z(), rotation.w());
    }

    /**
     * Apply the rotation represented by the quaternion ({@code rotationX}, {@code rotationY},
     * {@code rotationZ}, {@code rotationW}) to this transform, returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform rotate(float rotationX, float rotationY, float rotationZ, float rotationW) {
        if (org.joml2.internal.FmaSwitch.ON) {
            return new FloatTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(rotationX, this.rW, rotationW * this.rX) + java.lang.Math.fma(rotationZ, this.rY, -(rotationY * this.rZ)), java.lang.Math.fma(rotationY, this.rW, rotationW * this.rY) + java.lang.Math.fma(rotationX, this.rZ, rotationZ * -this.rX), java.lang.Math.fma(rotationZ, this.rW, rotationW * this.rZ) + java.lang.Math.fma(rotationY, this.rX, -(rotationX * this.rY)), java.lang.Math.fma(rotationW, this.rW, rotationX * -this.rX) - java.lang.Math.fma(rotationY, this.rY, rotationZ * this.rZ), this.sX, this.sY, this.sZ);
        } else {
            return new FloatTransform(this.tX, this.tY, this.tZ, ((rotationX) * (this.rW) + (rotationW * this.rX)) + ((rotationZ) * (this.rY) - (rotationY * this.rZ)), ((rotationY) * (this.rW) + (rotationW * this.rY)) + ((rotationX) * (this.rZ) - (rotationZ * this.rX)), ((rotationZ) * (this.rW) + (rotationW * this.rZ)) + ((rotationY) * (this.rX) - (rotationX * this.rY)), ((rotationW) * (this.rW) - (rotationX * this.rX)) - ((rotationY) * (this.rY) + (rotationZ * this.rZ)), this.sX, this.sY, this.sZ);
        }
    }

    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this transform,
     * returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform rotateAxis(float angle, Float3 axis) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        if (org.joml2.internal.FmaSwitch.ON) return rotateAxis_fma(angle, axisX, axisY, axisZ);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ);
    }

    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) to this transform, returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform rotateAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        if (org.joml2.internal.FmaSwitch.ON) return rotateAxis_fma(angle, axisX, axisY, axisZ);
        return rotateAxis_mulAdd(angle, axisX, axisY, axisZ);
    }

    /** {@code rotateAxis} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform rotateAxis_fma(float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        return new FloatTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t1, this.rW * _t3) + java.lang.Math.fma(this.rY, _t4, -(this.rZ * _t5)), java.lang.Math.fma(this.rY, _t1, this.rW * _t5) + java.lang.Math.fma(this.rZ, _t3, -this.rX * _t4), java.lang.Math.fma(this.rZ, _t1, this.rW * _t4) + java.lang.Math.fma(this.rX, _t5, -(this.rY * _t3)), java.lang.Math.fma(this.rW, _t1, -this.rX * _t3) - java.lang.Math.fma(this.rY, _t5, this.rZ * _t4), this.sX, this.sY, this.sZ);
    }

    /** {@code rotateAxis} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform rotateAxis_mulAdd(float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        return new FloatTransform(this.tX, this.tY, this.tZ, ((this.rX) * (_t1) + (this.rW * _t3)) + ((this.rY) * (_t4) - (this.rZ * _t5)), ((this.rY) * (_t1) + (this.rW * _t5)) + ((this.rZ) * (_t3) - (this.rX * _t4)), ((this.rZ) * (_t1) + (this.rW * _t4)) + ((this.rX) * (_t5) - (this.rY * _t3)), ((this.rW) * (_t1) - (this.rX * _t3)) - ((this.rY) * (_t5) + (this.rZ * _t4)), this.sX, this.sY, this.sZ);
    }

    /**
     * Apply a rotation of {@code angle} radians about the X axis to this transform, returning the
     * result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform rotateX(float angle) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            return new FloatTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t1, this.rW * _t2), java.lang.Math.fma(this.rY, _t1, this.rZ * _t2), java.lang.Math.fma(this.rZ, _t1, -(this.rY * _t2)), java.lang.Math.fma(this.rW, _t1, -(this.rX * _t2)), this.sX, this.sY, this.sZ);
        } else {
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            return new FloatTransform(this.tX, this.tY, this.tZ, ((this.rX) * (_t1) + (this.rW * _t2)), ((this.rY) * (_t1) + (this.rZ * _t2)), ((this.rZ) * (_t1) - (this.rY * _t2)), ((this.rW) * (_t1) - (this.rX * _t2)), this.sX, this.sY, this.sZ);
        }
    }

    /** Private tail of {@code rotateXYZ}; reached only through it. */
    private FloatTransform rotateXYZ_s6e793366_tail_fma(float _t12, float _t5, float _t11, float _t8, float _t21, float _t19, float _t20) {
        float _t22 = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        return new FloatTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t21, this.rW * _t19) + java.lang.Math.fma(this.rY, _t20, -(this.rZ * _t22)), java.lang.Math.fma(this.rY, _t21, this.rW * _t22) + java.lang.Math.fma(this.rZ, _t19, -this.rX * _t20), java.lang.Math.fma(this.rZ, _t21, this.rW * _t20) + java.lang.Math.fma(this.rX, _t22, -(this.rY * _t19)), java.lang.Math.fma(this.rW, _t21, -this.rX * _t19) - java.lang.Math.fma(this.rY, _t22, this.rZ * _t20), this.sX, this.sY, this.sZ);
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of {@code rotateXYZ}
     * and {@code rotateXZY}; reached only through them.
     */
    private FloatTransform rotateXYZ_s6e793366_tail_mulAdd(float _t12, float _t5, float _t11, float _t8, float _t21, float _t19, float _t20) {
        float _t22 = ((_t12) * (_t5) - (_t11 * _t8));
        return new FloatTransform(this.tX, this.tY, this.tZ, ((this.rX) * (_t21) + (this.rW * _t19)) + ((this.rY) * (_t20) - (this.rZ * _t22)), ((this.rY) * (_t21) + (this.rW * _t22)) + ((this.rZ) * (_t19) - (this.rX * _t20)), ((this.rZ) * (_t21) + (this.rW * _t20)) + ((this.rX) * (_t22) - (this.rY * _t19)), ((this.rW) * (_t21) - (this.rX * _t19)) - ((this.rY) * (_t22) + (this.rZ * _t20)), this.sX, this.sY, this.sZ);
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Ry * Rz}, so a vector is rotated about the fixed Z axis first,
     * then Y, then X), to this transform, returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform rotateXYZ(float angleX, float angleY, float angleZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
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
            return rotateXYZ_s6e793366_tail_fma(_t12, _t5, _t11, _t8, java.lang.Math.fma(_t9, _t5, _t10 * -_t8), java.lang.Math.fma(_t11, _t5, _t12 * _t8), java.lang.Math.fma(_t10, _t5, _t9 * _t8));
        } else {
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
            return rotateXYZ_s6e793366_tail_mulAdd(_t12, _t5, _t11, _t8, ((_t9) * (_t5) - (_t10 * _t8)), ((_t11) * (_t5) + (_t12 * _t8)), ((_t10) * (_t5) + (_t9 * _t8)));
        }
    }

    /**
     * Private tail of {@code rotateXZY}. Shared by the identical private paths of {@code rotateXZY}
     * and {@code rotateYXZ}; reached only through them.
     */
    private FloatTransform rotateXZY_s48549da6_tail_fma(float _t10, float _t5, float _t9, float _t8, float _t19, float _t21, float _t20) {
        float _t22 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        return new FloatTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t19, this.rW * _t21) + java.lang.Math.fma(this.rY, _t20, -(this.rZ * _t22)), java.lang.Math.fma(this.rY, _t19, this.rW * _t22) + java.lang.Math.fma(this.rZ, _t21, -this.rX * _t20), java.lang.Math.fma(this.rZ, _t19, this.rW * _t20) + java.lang.Math.fma(this.rX, _t22, -(this.rY * _t21)), java.lang.Math.fma(this.rW, _t19, -this.rX * _t21) - java.lang.Math.fma(this.rY, _t22, this.rZ * _t20), this.sX, this.sY, this.sZ);
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rx * Rz * Ry}, so a vector is rotated about the fixed Y axis first,
     * then Z, then X), to this transform, returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform rotateXZY(float angleX, float angleZ, float angleY) {
        if (org.joml2.internal.FmaSwitch.ON) {
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
            return rotateXZY_s48549da6_tail_fma(_t10, _t5, _t9, _t8, java.lang.Math.fma(_t9, _t5, _t10 * _t8), java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)), java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        } else {
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
            return rotateXYZ_s6e793366_tail_mulAdd(_t10, _t5, _t9, _t8, ((_t9) * (_t5) + (_t10 * _t8)), ((_t11) * (_t8) - (_t12 * _t5)), ((_t11) * (_t5) + (_t12 * _t8)));
        }
    }

    /**
     * Apply a rotation of {@code angle} radians about the Y axis to this transform, returning the
     * result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform rotateY(float angle) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            return new FloatTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t1, -(this.rZ * _t2)), java.lang.Math.fma(this.rY, _t1, this.rW * _t2), java.lang.Math.fma(this.rX, _t2, this.rZ * _t1), java.lang.Math.fma(this.rW, _t1, -(this.rY * _t2)), this.sX, this.sY, this.sZ);
        } else {
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            return new FloatTransform(this.tX, this.tY, this.tZ, ((this.rX) * (_t1) - (this.rZ * _t2)), ((this.rY) * (_t1) + (this.rW * _t2)), ((this.rX) * (_t2) + (this.rZ * _t1)), ((this.rW) * (_t1) - (this.rY * _t2)), this.sX, this.sY, this.sZ);
        }
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rx * Rz}, so a vector is rotated about the fixed Z axis first,
     * then X, then Y), to this transform, returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform rotateYXZ(float angleY, float angleX, float angleZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
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
            return rotateXZY_s48549da6_tail_fma(_t12, _t8, _t11, _t5, java.lang.Math.fma(_t9, _t5, _t10 * _t8), java.lang.Math.fma(_t11, _t8, _t12 * _t5), java.lang.Math.fma(_t10, _t5, -(_t9 * _t8)));
        } else {
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
            return rotateXYZ_s6e793366_tail_mulAdd(_t12, _t8, _t11, _t5, ((_t9) * (_t5) + (_t10 * _t8)), ((_t11) * (_t8) + (_t12 * _t5)), ((_t10) * (_t5) - (_t9 * _t8)));
        }
    }

    /** Private tail of {@code rotateYZX}; reached only through it. */
    private FloatTransform rotateYZX_s71a48226_tail_fma(float _t11, float _t5, float _t12, float _t8, float _t21, float _t19, float _t20) {
        float _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        return new FloatTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t21, this.rW * _t19) + java.lang.Math.fma(this.rY, _t22, -(this.rZ * _t20)), java.lang.Math.fma(this.rY, _t21, this.rW * _t20) + java.lang.Math.fma(this.rZ, _t19, -this.rX * _t22), java.lang.Math.fma(this.rZ, _t21, this.rW * _t22) + java.lang.Math.fma(this.rX, _t20, -(this.rY * _t19)), java.lang.Math.fma(this.rW, _t21, -this.rX * _t19) - java.lang.Math.fma(this.rY, _t20, this.rZ * _t22), this.sX, this.sY, this.sZ);
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private FloatTransform rotateYZX_s71a48226_tail_mulAdd(float _t11, float _t5, float _t12, float _t8, float _t21, float _t19, float _t20) {
        float _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        return new FloatTransform(this.tX, this.tY, this.tZ, ((this.rX) * (_t21) + (this.rW * _t19)) + ((this.rY) * (_t22) - (this.rZ * _t20)), ((this.rY) * (_t21) + (this.rW * _t20)) + ((this.rZ) * (_t19) - (this.rX * _t22)), ((this.rZ) * (_t21) + (this.rW * _t22)) + ((this.rX) * (_t20) - (this.rY * _t19)), ((this.rW) * (_t21) - (this.rX * _t19)) - ((this.rY) * (_t20) + (this.rZ * _t22)), this.sX, this.sY, this.sZ);
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Ry * Rz * Rx}, so a vector is rotated about the fixed X axis first,
     * then Z, then Y), to this transform, returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform rotateYZX(float angleY, float angleZ, float angleX) {
        if (org.joml2.internal.FmaSwitch.ON) {
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
            return rotateYZX_s71a48226_tail_fma(_t11, _t5, _t12, _t8, java.lang.Math.fma(_t9, _t5, _t10 * -_t8), java.lang.Math.fma(_t10, _t5, _t9 * _t8), java.lang.Math.fma(_t12, _t5, _t11 * _t8));
        } else {
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
            return rotateYZX_s71a48226_tail_mulAdd(_t11, _t5, _t12, _t8, ((_t9) * (_t5) - (_t10 * _t8)), ((_t10) * (_t5) + (_t9 * _t8)), ((_t12) * (_t5) + (_t11 * _t8)));
        }
    }

    /**
     * Apply a rotation of {@code angle} radians about the Z axis to this transform, returning the
     * result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform rotateZ(float angle) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            return new FloatTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t1, this.rY * _t2), java.lang.Math.fma(this.rY, _t1, -(this.rX * _t2)), java.lang.Math.fma(this.rZ, _t1, this.rW * _t2), java.lang.Math.fma(this.rW, _t1, -(this.rZ * _t2)), this.sX, this.sY, this.sZ);
        } else {
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            return new FloatTransform(this.tX, this.tY, this.tZ, ((this.rX) * (_t1) + (this.rY * _t2)), ((this.rY) * (_t1) - (this.rX * _t2)), ((this.rZ) * (_t1) + (this.rW * _t2)), ((this.rW) * (_t1) - (this.rZ * _t2)), this.sX, this.sY, this.sZ);
        }
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private FloatTransform rotateZXY_s673d9226_tail_fma(float _t11, float _t5, float _t12, float _t8, float _t21, float _t19, float _t20) {
        float _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        return new FloatTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t21, this.rW * _t22) + java.lang.Math.fma(this.rY, _t19, -(this.rZ * _t20)), java.lang.Math.fma(this.rY, _t21, this.rW * _t20) + java.lang.Math.fma(this.rZ, _t22, -this.rX * _t19), java.lang.Math.fma(this.rZ, _t21, this.rW * _t19) + java.lang.Math.fma(this.rX, _t20, -(this.rY * _t22)), java.lang.Math.fma(this.rW, _t21, -this.rX * _t22) - java.lang.Math.fma(this.rY, _t20, this.rZ * _t19), this.sX, this.sY, this.sZ);
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private FloatTransform rotateZXY_s673d9226_tail_mulAdd(float _t11, float _t5, float _t12, float _t8, float _t21, float _t19, float _t20) {
        float _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        return new FloatTransform(this.tX, this.tY, this.tZ, ((this.rX) * (_t21) + (this.rW * _t22)) + ((this.rY) * (_t19) - (this.rZ * _t20)), ((this.rY) * (_t21) + (this.rW * _t20)) + ((this.rZ) * (_t22) - (this.rX * _t19)), ((this.rZ) * (_t21) + (this.rW * _t19)) + ((this.rX) * (_t20) - (this.rY * _t22)), ((this.rW) * (_t21) - (this.rX * _t22)) - ((this.rY) * (_t20) + (this.rZ * _t19)), this.sX, this.sY, this.sZ);
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Rx * Ry}, so a vector is rotated about the fixed Y axis first,
     * then X, then Z), to this transform, returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform rotateZXY(float angleZ, float angleX, float angleY) {
        if (org.joml2.internal.FmaSwitch.ON) {
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
            return rotateZXY_s673d9226_tail_fma(_t11, _t5, _t12, _t8, java.lang.Math.fma(_t9, _t5, _t10 * -_t8), java.lang.Math.fma(_t11, _t8, _t12 * _t5), java.lang.Math.fma(_t10, _t5, _t9 * _t8));
        } else {
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
            return rotateZXY_s673d9226_tail_mulAdd(_t11, _t5, _t12, _t8, ((_t9) * (_t5) - (_t10 * _t8)), ((_t11) * (_t8) + (_t12 * _t5)), ((_t10) * (_t5) + (_t9 * _t8)));
        }
    }

    /** Private tail of {@code rotateZYX}; reached only through it. */
    private FloatTransform rotateZYX_s4118fc66_tail_fma(float _t11, float _t8, float _t12, float _t5, float _t19, float _t21, float _t20) {
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        return new FloatTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t19, this.rW * _t21) + java.lang.Math.fma(this.rY, _t22, -(this.rZ * _t20)), java.lang.Math.fma(this.rY, _t19, this.rW * _t20) + java.lang.Math.fma(this.rZ, _t21, -this.rX * _t22), java.lang.Math.fma(this.rZ, _t19, this.rW * _t22) + java.lang.Math.fma(this.rX, _t20, -(this.rY * _t21)), java.lang.Math.fma(this.rW, _t19, -this.rX * _t21) - java.lang.Math.fma(this.rY, _t20, this.rZ * _t22), this.sX, this.sY, this.sZ);
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (intrinsic rotations, each about the axes the previous ones turned:
     * the matrix product {@code Rz * Ry * Rx}, so a vector is rotated about the fixed X axis first,
     * then Y, then Z), to this transform, returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform rotateZYX(float angleZ, float angleY, float angleX) {
        if (org.joml2.internal.FmaSwitch.ON) {
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
            return rotateZYX_s4118fc66_tail_fma(_t11, _t8, _t12, _t5, java.lang.Math.fma(_t9, _t5, _t10 * _t8), java.lang.Math.fma(_t10, _t5, -(_t9 * _t8)), java.lang.Math.fma(_t12, _t8, _t11 * _t5));
        } else {
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
            return rotateYZX_s71a48226_tail_mulAdd(_t11, _t8, _t12, _t5, ((_t9) * (_t5) + (_t10 * _t8)), ((_t10) * (_t5) - (_t9 * _t8)), ((_t12) * (_t8) + (_t11 * _t5)));
        }
    }

    /**
     * Apply a scaling by {@code scale} to this transform, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} transform and {@code S} the scaling transform, then the new
     * transform will be {@code M * S}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scale the scale factors
     * @return the resulting transform
     */
    public FloatTransform scale(Float3 scale) {
        float scaleX = scale.x();
        float scaleY = scale.y();
        float scaleZ = scale.z();
        return new FloatTransform(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW, scaleX * this.sX, scaleY * this.sY, scaleZ * this.sZ);
    }

    /**
     * Apply a scaling by ({@code scaleX}, {@code scaleY}, {@code scaleZ}) to this transform,
     * returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform scale(float scaleX, float scaleY, float scaleZ) {
        return new FloatTransform(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW, scaleX * this.sX, scaleY * this.sY, scaleZ * this.sZ);
    }

    /**
     * Apply a scaling by {@code scale} to this transform, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} transform and {@code S} the scaling transform, then the new
     * transform will be {@code M * S}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scale the scale factor
     * @return the resulting transform
     */
    public FloatTransform scale(float scale) {
        return new FloatTransform(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW, scale * this.sX, scale * this.sY, scale * this.sZ);
    }

    /**
     * Apply a translation by {@code translation} to this transform, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} transform and {@code T} the translation transform, then the new
     * transform will be {@code M * T}. So when transforming a vector {@code v} with the new
     * transform by using {@code M * T * v}, the translation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation offsets
     * @return the resulting transform
     */
    public FloatTransform translate(Float3 translation) {
        float translationX = translation.x();
        float translationY = translation.y();
        float translationZ = translation.z();
        if (!org.joml2.internal.FmaSwitch.ON) return translate_mulAdd(translationX, translationY, translationZ);
        float _t0 = this.sY * translationY;
        float _t1 = this.sX * translationX;
        float _t2 = this.sZ * translationZ;
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, _t0, -(this.rY * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(this.rZ, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(this.rY, _t2, -(this.rZ * _t0));
        return new FloatTransform(java.lang.Math.fma(this.rY, _t12, java.lang.Math.fma(-this.rZ, _t13, java.lang.Math.fma(this.rW, _t14, java.lang.Math.fma(this.sX, translationX, this.tX)))), java.lang.Math.fma(this.rZ, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, java.lang.Math.fma(this.sY, translationY, this.tY)))), java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rY, _t14, java.lang.Math.fma(this.rW, _t12, java.lang.Math.fma(this.sZ, translationZ, this.tZ)))), this.rX, this.rY, this.rZ, this.rW, this.sX, this.sY, this.sZ);
    }

    /**
     * Apply a translation by ({@code translationX}, {@code translationY}, {@code translationZ}) to
     * this transform, returning the result as a value.
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
     * @return the resulting transform
     */
    public FloatTransform translate(float translationX, float translationY, float translationZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return translate_mulAdd(translationX, translationY, translationZ);
        float _t0 = this.sY * translationY;
        float _t1 = this.sX * translationX;
        float _t2 = this.sZ * translationZ;
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, _t0, -(this.rY * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(this.rZ, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(this.rY, _t2, -(this.rZ * _t0));
        return new FloatTransform(java.lang.Math.fma(this.rY, _t12, java.lang.Math.fma(-this.rZ, _t13, java.lang.Math.fma(this.rW, _t14, java.lang.Math.fma(this.sX, translationX, this.tX)))), java.lang.Math.fma(this.rZ, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, java.lang.Math.fma(this.sY, translationY, this.tY)))), java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rY, _t14, java.lang.Math.fma(this.rW, _t12, java.lang.Math.fma(this.sZ, translationZ, this.tZ)))), this.rX, this.rY, this.rZ, this.rW, this.sX, this.sY, this.sZ);
    }

    /** {@code translate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatTransform translate_mulAdd(float translationX, float translationY, float translationZ) {
        float _t0 = this.sY * translationY;
        float _t1 = this.sX * translationX;
        float _t2 = this.sZ * translationZ;
        float _t12 = 2.0f * ((this.rX) * (_t0) - (this.rY * _t1));
        float _t13 = 2.0f * ((this.rZ) * (_t1) - (this.rX * _t2));
        float _t14 = 2.0f * ((this.rY) * (_t2) - (this.rZ * _t0));
        return new FloatTransform(((this.rY) * (_t12) + (((((this.rW) * (_t14) + (((this.sX) * (translationX) + (this.tX))))) - (this.rZ) * (_t13)))), ((this.rZ) * (_t14) + (((((this.rW) * (_t13) + (((this.sY) * (translationY) + (this.tY))))) - (this.rX) * (_t12)))), ((this.rX) * (_t13) + (((((this.rW) * (_t12) + (((this.sZ) * (translationZ) + (this.tZ))))) - (this.rY) * (_t14)))), this.rX, this.rY, this.rZ, this.rW, this.sX, this.sY, this.sZ);
    }

    /**
     * Transform {@code v} by this transform, returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the vector to transform
     * @return the resulting vector
     */
    public Float3 transform(Float3 v) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(vX, vY, vZ);
        float _t0 = this.sY * vY;
        float _t1 = this.sX * vX;
        float _t2 = this.sZ * vZ;
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, _t0, -(this.rY * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(this.rZ, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(this.rY, _t2, -(this.rZ * _t0));
        return new Float3(java.lang.Math.fma(this.rY, _t12, java.lang.Math.fma(-this.rZ, _t13, java.lang.Math.fma(this.rW, _t14, java.lang.Math.fma(this.sX, vX, this.tX)))), java.lang.Math.fma(this.rZ, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, java.lang.Math.fma(this.sY, vY, this.tY)))), java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rY, _t14, java.lang.Math.fma(this.rW, _t12, java.lang.Math.fma(this.sZ, vZ, this.tZ)))));
    }

    /**
     * Transform ({@code vX}, {@code vY}, {@code vZ}) by this transform, returning the result as a
     * value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Float3 transform(float vX, float vY, float vZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(vX, vY, vZ);
        float _t0 = this.sY * vY;
        float _t1 = this.sX * vX;
        float _t2 = this.sZ * vZ;
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, _t0, -(this.rY * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(this.rZ, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(this.rY, _t2, -(this.rZ * _t0));
        return new Float3(java.lang.Math.fma(this.rY, _t12, java.lang.Math.fma(-this.rZ, _t13, java.lang.Math.fma(this.rW, _t14, java.lang.Math.fma(this.sX, vX, this.tX)))), java.lang.Math.fma(this.rZ, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, java.lang.Math.fma(this.sY, vY, this.tY)))), java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rY, _t14, java.lang.Math.fma(this.rW, _t12, java.lang.Math.fma(this.sZ, vZ, this.tZ)))));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transform_mulAdd(float vX, float vY, float vZ) {
        float _t0 = this.sY * vY;
        float _t1 = this.sX * vX;
        float _t2 = this.sZ * vZ;
        float _t12 = 2.0f * ((this.rX) * (_t0) - (this.rY * _t1));
        float _t13 = 2.0f * ((this.rZ) * (_t1) - (this.rX * _t2));
        float _t14 = 2.0f * ((this.rY) * (_t2) - (this.rZ * _t0));
        return new Float3(((this.rY) * (_t12) + (((((this.rW) * (_t14) + (((this.sX) * (vX) + (this.tX))))) - (this.rZ) * (_t13)))), ((this.rZ) * (_t14) + (((((this.rW) * (_t13) + (((this.sY) * (vY) + (this.tY))))) - (this.rX) * (_t12)))), ((this.rX) * (_t13) + (((((this.rW) * (_t12) + (((this.sZ) * (vZ) + (this.tZ))))) - (this.rY) * (_t14)))));
    }

    /**
     * Transform the given direction by the rotation part of this transform, ignoring translation
     * and scale, returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the direction to transform
     * @return the resulting vector
     */
    public Float3 transformDirection(Float3 v) {
        return transformDirection(v.x(), v.y(), v.z());
    }

    /**
     * Transform the given direction by the rotation part of this transform, ignoring translation
     * and scale, returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Float3 transformDirection(float vX, float vY, float vZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t9 = 2.0f * java.lang.Math.fma(this.rX, vY, -(this.rY * vX));
            float _t10 = 2.0f * java.lang.Math.fma(this.rZ, vX, -(this.rX * vZ));
            float _t11 = 2.0f * java.lang.Math.fma(this.rY, vZ, -(this.rZ * vY));
            return new Float3(java.lang.Math.fma(this.rY, _t9, java.lang.Math.fma(-this.rZ, _t10, java.lang.Math.fma(this.rW, _t11, vX))), java.lang.Math.fma(this.rZ, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vY))), java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rY, _t11, java.lang.Math.fma(this.rW, _t9, vZ))));
        } else {
            float _t9 = 2.0f * ((this.rX) * (vY) - (this.rY * vX));
            float _t10 = 2.0f * ((this.rZ) * (vX) - (this.rX * vZ));
            float _t11 = 2.0f * ((this.rY) * (vZ) - (this.rZ * vY));
            return new Float3(((this.rY) * (_t9) + (((((this.rW) * (_t11) + (vX))) - (this.rZ) * (_t10)))), ((this.rZ) * (_t11) + (((((this.rW) * (_t10) + (vY))) - (this.rX) * (_t9)))), ((this.rX) * (_t10) + (((((this.rW) * (_t9) + (vZ))) - (this.rY) * (_t11)))));
        }
    }

    /**
     * Transform the given direction by the inverse of this transform's rotation (world to local),
     * ignoring translation and scale, without materializing {@code invert()}, returning the result
     * as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the direction to transform
     * @return the resulting vector
     */
    public Float3 transformDirectionInverse(Float3 v) {
        return transformDirectionInverse(v.x(), v.y(), v.z());
    }

    /**
     * Transform the given direction by the inverse of this transform's rotation (world to local),
     * ignoring translation and scale, without materializing {@code invert()}, returning the result
     * as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Float3 transformDirectionInverse(float vX, float vY, float vZ) {
        if (org.joml2.internal.FmaSwitch.ON) {
            float _t9 = 2.0f * java.lang.Math.fma(this.rX, vZ, -(this.rZ * vX));
            float _t10 = 2.0f * java.lang.Math.fma(this.rY, vX, -(this.rX * vY));
            float _t11 = 2.0f * java.lang.Math.fma(this.rZ, vY, -(this.rY * vZ));
            return new Float3(java.lang.Math.fma(this.rZ, _t9, java.lang.Math.fma(-this.rY, _t10, java.lang.Math.fma(this.rW, _t11, vX))), java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rZ, _t11, java.lang.Math.fma(this.rW, _t9, vY))), java.lang.Math.fma(this.rY, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vZ))));
        } else {
            float _t9 = 2.0f * ((this.rX) * (vZ) - (this.rZ * vX));
            float _t10 = 2.0f * ((this.rY) * (vX) - (this.rX * vY));
            float _t11 = 2.0f * ((this.rZ) * (vY) - (this.rY * vZ));
            return new Float3(((this.rZ) * (_t9) + (((((this.rW) * (_t11) + (vX))) - (this.rY) * (_t10)))), ((this.rX) * (_t10) + (((((this.rW) * (_t9) + (vY))) - (this.rZ) * (_t11)))), ((this.rY) * (_t11) + (((((this.rW) * (_t10) + (vZ))) - (this.rX) * (_t9)))));
        }
    }

    /**
     * Transform {@code p} by the inverse of this transform, returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param p the position to transform
     * @return the resulting vector
     */
    public Float3 transformInverse(Float3 p) {
        float pX = p.x();
        float pY = p.y();
        float pZ = p.z();
        if (!org.joml2.internal.FmaSwitch.ON) return transformInverse_mulAdd(pX, pY, pZ);
        float _t0 = pZ - this.tZ;
        float _t1 = pX - this.tX;
        float _t2 = pY - this.tY;
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(this.rZ, _t2, -(this.rY * _t0));
        return new Float3(java.lang.Math.fma(this.rZ, _t12, java.lang.Math.fma(-this.rY, _t13, java.lang.Math.fma(this.rW, _t14, _t1))) / this.sX, java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rZ, _t14, java.lang.Math.fma(this.rW, _t12, _t2))) / this.sY, java.lang.Math.fma(this.rY, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, _t0))) / this.sZ);
    }

    /**
     * Transform ({@code pX}, {@code pY}, {@code pZ}) by the inverse of this transform, returning
     * the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @return the resulting vector
     */
    public Float3 transformInverse(float pX, float pY, float pZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return transformInverse_mulAdd(pX, pY, pZ);
        float _t0 = pZ - this.tZ;
        float _t1 = pX - this.tX;
        float _t2 = pY - this.tY;
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(this.rZ, _t2, -(this.rY * _t0));
        return new Float3(java.lang.Math.fma(this.rZ, _t12, java.lang.Math.fma(-this.rY, _t13, java.lang.Math.fma(this.rW, _t14, _t1))) / this.sX, java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rZ, _t14, java.lang.Math.fma(this.rW, _t12, _t2))) / this.sY, java.lang.Math.fma(this.rY, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, _t0))) / this.sZ);
    }

    /** {@code transformInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transformInverse_mulAdd(float pX, float pY, float pZ) {
        float _t0 = pZ - this.tZ;
        float _t1 = pX - this.tX;
        float _t2 = pY - this.tY;
        float _t12 = 2.0f * ((this.rX) * (_t0) - (this.rZ * _t1));
        float _t13 = 2.0f * ((this.rY) * (_t1) - (this.rX * _t2));
        float _t14 = 2.0f * ((this.rZ) * (_t2) - (this.rY * _t0));
        return new Float3(((this.rZ) * (_t12) + (((((this.rW) * (_t14) + (_t1))) - (this.rY) * (_t13)))) / this.sX, ((this.rX) * (_t13) + (((((this.rW) * (_t12) + (_t2))) - (this.rZ) * (_t14)))) / this.sY, ((this.rY) * (_t14) + (((((this.rW) * (_t13) + (_t0))) - (this.rX) * (_t12)))) / this.sZ);
    }

    /**
     * Transform the given position by this transform, treating it as a point with an implicit
     * {@code w = 1}, returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the position to transform
     * @return the resulting vector
     */
    public Float3 transformPosition(Float3 v) {
        return transform(v);
    }

    /**
     * Transform the given position by this transform, treating it as a point with an implicit
     * {@code w = 1}, returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Float3 transformPosition(float vX, float vY, float vZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return transform_mulAdd(vX, vY, vZ);
        float _t0 = this.sY * vY;
        float _t1 = this.sX * vX;
        float _t2 = this.sZ * vZ;
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, _t0, -(this.rY * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(this.rZ, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(this.rY, _t2, -(this.rZ * _t0));
        return new Float3(java.lang.Math.fma(this.rY, _t12, java.lang.Math.fma(-this.rZ, _t13, java.lang.Math.fma(this.rW, _t14, java.lang.Math.fma(this.sX, vX, this.tX)))), java.lang.Math.fma(this.rZ, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, java.lang.Math.fma(this.sY, vY, this.tY)))), java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rY, _t14, java.lang.Math.fma(this.rW, _t12, java.lang.Math.fma(this.sZ, vZ, this.tZ)))));
    }

    /**
     * Transform the given position by the inverse of this transform (world to local), without
     * materializing {@code invert()}, returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param p the position to transform
     * @return the resulting vector
     */
    public Float3 transformPositionInverse(Float3 p) {
        return transformInverse(p);
    }

    /**
     * Transform the given position by the inverse of this transform (world to local), without
     * materializing {@code invert()}, returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @return the resulting vector
     */
    public Float3 transformPositionInverse(float pX, float pY, float pZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return transformInverse_mulAdd(pX, pY, pZ);
        float _t0 = pZ - this.tZ;
        float _t1 = pX - this.tX;
        float _t2 = pY - this.tY;
        float _t12 = 2.0f * java.lang.Math.fma(this.rX, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(this.rY, _t1, -(this.rX * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(this.rZ, _t2, -(this.rY * _t0));
        return new Float3(java.lang.Math.fma(this.rZ, _t12, java.lang.Math.fma(-this.rY, _t13, java.lang.Math.fma(this.rW, _t14, _t1))) / this.sX, java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rZ, _t14, java.lang.Math.fma(this.rW, _t12, _t2))) / this.sY, java.lang.Math.fma(this.rY, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, _t0))) / this.sZ);
    }

    /**
     * Transform the given vector by the linear part of this transform, i.e. apply its scale and
     * rotation but not its translation, returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the vector to transform
     * @return the resulting vector
     */
    public Float3 transformVector(Float3 v) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        if (!org.joml2.internal.FmaSwitch.ON) return transformVector_mulAdd(vX, vY, vZ);
        float _t0 = this.sZ * vZ;
        float _t1 = this.sY * vY;
        float _t2 = this.sX * vX;
        float _t12 = 2.0f * java.lang.Math.fma(this.rY, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(this.rX, _t1, -(this.rY * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(this.rZ, _t2, -(this.rX * _t0));
        return new Float3(java.lang.Math.fma(this.sX, vX, java.lang.Math.fma(this.rW, _t12, java.lang.Math.fma(this.rY, _t13, -(this.rZ * _t14)))), java.lang.Math.fma(this.sY, vY, java.lang.Math.fma(this.rW, _t14, java.lang.Math.fma(this.rZ, _t12, -(this.rX * _t13)))), java.lang.Math.fma(this.sZ, vZ, java.lang.Math.fma(this.rW, _t13, java.lang.Math.fma(this.rX, _t14, -(this.rY * _t12)))));
    }

    /**
     * Transform the given vector by the linear part of this transform, i.e. apply its scale and
     * rotation but not its translation, returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Float3 transformVector(float vX, float vY, float vZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return transformVector_mulAdd(vX, vY, vZ);
        float _t0 = this.sZ * vZ;
        float _t1 = this.sY * vY;
        float _t2 = this.sX * vX;
        float _t12 = 2.0f * java.lang.Math.fma(this.rY, _t0, -(this.rZ * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(this.rX, _t1, -(this.rY * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(this.rZ, _t2, -(this.rX * _t0));
        return new Float3(java.lang.Math.fma(this.sX, vX, java.lang.Math.fma(this.rW, _t12, java.lang.Math.fma(this.rY, _t13, -(this.rZ * _t14)))), java.lang.Math.fma(this.sY, vY, java.lang.Math.fma(this.rW, _t14, java.lang.Math.fma(this.rZ, _t12, -(this.rX * _t13)))), java.lang.Math.fma(this.sZ, vZ, java.lang.Math.fma(this.rW, _t13, java.lang.Math.fma(this.rX, _t14, -(this.rY * _t12)))));
    }

    /** {@code transformVector} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transformVector_mulAdd(float vX, float vY, float vZ) {
        float _t0 = this.sZ * vZ;
        float _t1 = this.sY * vY;
        float _t2 = this.sX * vX;
        float _t12 = 2.0f * ((this.rY) * (_t0) - (this.rZ * _t1));
        float _t13 = 2.0f * ((this.rX) * (_t1) - (this.rY * _t2));
        float _t14 = 2.0f * ((this.rZ) * (_t2) - (this.rX * _t0));
        return new Float3(((this.sX) * (vX) + (((this.rW) * (_t12) + (((this.rY) * (_t13) - (this.rZ * _t14)))))), ((this.sY) * (vY) + (((this.rW) * (_t14) + (((this.rZ) * (_t12) - (this.rX * _t13)))))), ((this.sZ) * (vZ) + (((this.rW) * (_t13) + (((this.rX) * (_t14) - (this.rY * _t12)))))));
    }

    /**
     * Transform the given vector by the inverse of this transform's linear part (world to local),
     * i.e. undo its rotation and scale but not its translation, without materializing
     * {@code invert()}, returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param v the vector to transform
     * @return the resulting vector
     */
    public Float3 transformVectorInverse(Float3 v) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        if (!org.joml2.internal.FmaSwitch.ON) return transformVectorInverse_mulAdd(vX, vY, vZ);
        float _t9 = 2.0f * java.lang.Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * java.lang.Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * java.lang.Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Float3(java.lang.Math.fma(this.rZ, _t9, java.lang.Math.fma(-this.rY, _t10, java.lang.Math.fma(this.rW, _t11, vX))) / this.sX, java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rZ, _t11, java.lang.Math.fma(this.rW, _t9, vY))) / this.sY, java.lang.Math.fma(this.rY, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vZ))) / this.sZ);
    }

    /**
     * Transform the given vector by the inverse of this transform's linear part (world to local),
     * i.e. undo its rotation and scale but not its translation, without materializing
     * {@code invert()}, returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length; each component of the
     * scale of this transform must be non-zero.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Float3 transformVectorInverse(float vX, float vY, float vZ) {
        if (!org.joml2.internal.FmaSwitch.ON) return transformVectorInverse_mulAdd(vX, vY, vZ);
        float _t9 = 2.0f * java.lang.Math.fma(this.rX, vZ, -(this.rZ * vX));
        float _t10 = 2.0f * java.lang.Math.fma(this.rY, vX, -(this.rX * vY));
        float _t11 = 2.0f * java.lang.Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Float3(java.lang.Math.fma(this.rZ, _t9, java.lang.Math.fma(-this.rY, _t10, java.lang.Math.fma(this.rW, _t11, vX))) / this.sX, java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rZ, _t11, java.lang.Math.fma(this.rW, _t9, vY))) / this.sY, java.lang.Math.fma(this.rY, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vZ))) / this.sZ);
    }

    /** {@code transformVectorInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transformVectorInverse_mulAdd(float vX, float vY, float vZ) {
        float _t9 = 2.0f * ((this.rX) * (vZ) - (this.rZ * vX));
        float _t10 = 2.0f * ((this.rY) * (vX) - (this.rX * vY));
        float _t11 = 2.0f * ((this.rZ) * (vY) - (this.rY * vZ));
        return new Float3(((this.rZ) * (_t9) + (((((this.rW) * (_t11) + (vX))) - (this.rY) * (_t10)))) / this.sX, ((this.rX) * (_t10) + (((((this.rW) * (_t9) + (vY))) - (this.rZ) * (_t11)))) / this.sY, ((this.rY) * (_t11) + (((((this.rW) * (_t10) + (vZ))) - (this.rX) * (_t9)))) / this.sZ);
    }

    /**
     * {@return a copy with the {@code tX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code tX} component
     */
    public FloatTransform withTX(float v) {
        return new FloatTransform(v, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ);
    }

    /**
     * {@return a copy with the {@code tY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code tY} component
     */
    public FloatTransform withTY(float v) {
        return new FloatTransform(tX, v, tZ, rX, rY, rZ, rW, sX, sY, sZ);
    }

    /**
     * {@return a copy with the {@code tZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code tZ} component
     */
    public FloatTransform withTZ(float v) {
        return new FloatTransform(tX, tY, v, rX, rY, rZ, rW, sX, sY, sZ);
    }

    /**
     * {@return a copy with the {@code rX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rX} component
     */
    public FloatTransform withRX(float v) {
        return new FloatTransform(tX, tY, tZ, v, rY, rZ, rW, sX, sY, sZ);
    }

    /**
     * {@return a copy with the {@code rY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rY} component
     */
    public FloatTransform withRY(float v) {
        return new FloatTransform(tX, tY, tZ, rX, v, rZ, rW, sX, sY, sZ);
    }

    /**
     * {@return a copy with the {@code rZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rZ} component
     */
    public FloatTransform withRZ(float v) {
        return new FloatTransform(tX, tY, tZ, rX, rY, v, rW, sX, sY, sZ);
    }

    /**
     * {@return a copy with the {@code rW} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rW} component
     */
    public FloatTransform withRW(float v) {
        return new FloatTransform(tX, tY, tZ, rX, rY, rZ, v, sX, sY, sZ);
    }

    /**
     * {@return a copy with the {@code sX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code sX} component
     */
    public FloatTransform withSX(float v) {
        return new FloatTransform(tX, tY, tZ, rX, rY, rZ, rW, v, sY, sZ);
    }

    /**
     * {@return a copy with the {@code sY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code sY} component
     */
    public FloatTransform withSY(float v) {
        return new FloatTransform(tX, tY, tZ, rX, rY, rZ, rW, sX, v, sZ);
    }

    /**
     * {@return a copy with the {@code sZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code sZ} component
     */
    public FloatTransform withSZ(float v) {
        return new FloatTransform(tX, tY, tZ, rX, rY, rZ, rW, sX, sY, v);
    }

    @Override public String toString() {
        return "FloatTransform(" + this.tX + ", " + this.tY + ", " + this.tZ + ", " + this.rX + ", " + this.rY + ", " + this.rZ + ", " + this.rW + ", " + this.sX + ", " + this.sY + ", " + this.sZ + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatTransform)) return false;
        FloatTransform o = (FloatTransform) obj;
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

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
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

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
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

    /**
     * Compare this value component-wise against {@code other}, allowing a difference of at
     * most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
     * compares equal to an equal infinity (the difference {@code Inf - Inf} is NaN), and to a
     * finite one only under an infinite {@code epsilon}; a NaN component never compares equal to
     * anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the value to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    public boolean equalsEpsilon(FloatTransform other, float epsilon) {
        return java.lang.Math.abs(tX - other.tX) <= epsilon
            && java.lang.Math.abs(tY - other.tY) <= epsilon
            && java.lang.Math.abs(tZ - other.tZ) <= epsilon
            && java.lang.Math.abs(rX - other.rX) <= epsilon
            && java.lang.Math.abs(rY - other.rY) <= epsilon
            && java.lang.Math.abs(rZ - other.rZ) <= epsilon
            && java.lang.Math.abs(rW - other.rW) <= epsilon
            && java.lang.Math.abs(sX - other.sX) <= epsilon
            && java.lang.Math.abs(sY - other.sY) <= epsilon
            && java.lang.Math.abs(sZ - other.sZ) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatTransformSegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatTransformSegOpsUnsafe()
            : new FloatTransformSegOpsMS();
        static final FloatTransformBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatTransformBbOpsUnsafe()
            : new FloatTransformBbOpsApi();
        static final FloatTransformRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatTransformRawOpsUnsafe()
            : new FloatTransformRawOpsApi();
    }

    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
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
        dest[offset + 7] = this.sX;
        dest[offset + 8] = this.sY;
        dest[offset + 9] = this.sZ;
        return dest;
    }

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store(float[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform load(float[] src, int offset) {
        float _c0 = src[offset];
        float _c1 = src[offset + 1];
        float _c2 = src[offset + 2];
        float _c3 = src[offset + 3];
        float _c4 = src[offset + 4];
        float _c5 = src[offset + 5];
        float _c6 = src[offset + 6];
        float _c7 = src[offset + 7];
        float _c8 = src[offset + 8];
        float _c9 = src[offset + 9];
        return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform load(float[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRelative(FloatBuffer buf) {
        if (buf.remaining() < 10) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 10);
        return buf;
    }

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code FloatTransform} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatTransform loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 10) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatTransform r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 10);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 40) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 40);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position and advancing
     * the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code FloatTransform} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatTransform loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 40) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatTransform r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 40);
        return r;
    }

    /**
     * Store the elements into the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public FloatTransform storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store(MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

    /**
     * Load the elements from the given memory segment, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(offset, src);
    }

    /**
     * Store the elements into the given array, converting each element to {@code double}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
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
        dest[offset + 7] = this.sX;
        dest[offset + 8] = this.sY;
        dest[offset + 9] = this.sZ;
        return dest;
    }

    /**
     * Store the elements into the given array, converting each element to {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code double}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform load(double[] src, int offset) {
        float _c0 = (float) src[offset];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[offset + 2];
        float _c3 = (float) src[offset + 3];
        float _c4 = (float) src[offset + 4];
        float _c5 = (float) src[offset + 5];
        float _c6 = (float) src[offset + 6];
        float _c7 = (float) src[offset + 7];
        float _c8 = (float) src[offset + 8];
        float _c9 = (float) src[offset + 9];
        return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
    }

    /**
     * Load the elements from the given array, converting each element from {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform load(double[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code double}, starting
     * at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRelative(DoubleBuffer buf) {
        if (buf.remaining() < 10) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 10);
        return buf;
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
     * starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code double},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct buffer in native byte order takes the fast path; any other
     * byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source buffer
     * @return a new {@code FloatTransform} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static FloatTransform loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 10) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatTransform r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 10);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
     * starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
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
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code buf}.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 80) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 80);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
     * starting at its current position (the position is not modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * With the UNSAFE backend, a direct or heap buffer in native byte order takes the fast path;
     * any other byte order is honoured through the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code buf}.
     *
     * @param buf the source byte buffer
     * @return a new {@code FloatTransform} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static FloatTransform loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 80) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatTransform r = StoreLoad.BB_OPS.loadDoubleAbsolute(pos, buf);
        buf.position(pos + 80);
        return r;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public FloatTransform storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment, converting each element to {@code double}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeDouble(MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code double},
     * starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
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
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(0L, src); }

    /**
     * Load the elements from the given memory segment, converting each element from {@code double},
     * starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code FloatTransform} holding the loaded elements
     */
    public static FloatTransform loadDouble(long offset, MemorySegment src) {
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

    private abstract static class FloatTransformSegOps {
        public abstract MemorySegment store(FloatTransform self, long offset, MemorySegment dest);
        public abstract FloatTransform load(long offset, MemorySegment src);
        public abstract MemorySegment storeDouble(FloatTransform self, long offset, MemorySegment dest);
        public abstract FloatTransform loadDouble(long offset, MemorySegment src);
    }

    private static final class FloatTransformSegOpsMS extends FloatTransformSegOps {
        public MemorySegment store(FloatTransform self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, self.tX);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, self.tY);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, self.tZ);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, self.rX);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, self.rY);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, self.rZ);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, self.rW);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, self.sX);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, self.sY);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, self.sZ);
            return dest;
        }

        public FloatTransform load(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            float _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            float _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
            float _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
            float _c3 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
            float _c4 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
            float _c5 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
            float _c6 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
            float _c7 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
            float _c8 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 32L);
            float _c9 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 36L);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public MemorySegment storeDouble(FloatTransform self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset, self.tX);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 8L, self.tY);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 16L, self.tZ);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 24L, self.rX);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 32L, self.rY);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 40L, self.rZ);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 48L, self.rW);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 56L, self.sX);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 64L, self.sY);
            dest.set(_JAVA_DOUBLE_UNALIGNED, offset + 72L, self.sZ);
            return dest;
        }

        public FloatTransform loadDouble(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            float _c0 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            float _c1 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
            float _c2 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
            float _c3 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
            float _c4 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
            float _c5 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
            float _c6 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
            float _c7 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
            float _c8 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 64L);
            float _c9 = (float) src.get(_JAVA_DOUBLE_UNALIGNED, offset + 72L);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }
    }

    private static final class FloatTransformSegOpsUnsafe extends FloatTransformSegOps {
        private static final FloatTransformSegOpsMS MS = new FloatTransformSegOpsMS();

        public MemorySegment store(FloatTransform self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.store(self, offset, dest);
            if (!dest.isNative()) return storeOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.tX);
            _U.putFloat(address + 4L, self.tY);
            _U.putFloat(address + 8L, self.tZ);
            _U.putFloat(address + 12L, self.rX);
            _U.putFloat(address + 16L, self.rY);
            _U.putFloat(address + 20L, self.rZ);
            _U.putFloat(address + 24L, self.rW);
            _U.putFloat(address + 28L, self.sX);
            _U.putFloat(address + 32L, self.sY);
            _U.putFloat(address + 36L, self.sZ);
            return dest;
        }

        private static MemorySegment storeOnHeap(FloatTransform self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.store(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.tX);
            _U.putFloat(bytes, address + 4L, self.tY);
            _U.putFloat(bytes, address + 8L, self.tZ);
            _U.putFloat(bytes, address + 12L, self.rX);
            _U.putFloat(bytes, address + 16L, self.rY);
            _U.putFloat(bytes, address + 20L, self.rZ);
            _U.putFloat(bytes, address + 24L, self.rW);
            _U.putFloat(bytes, address + 28L, self.sX);
            _U.putFloat(bytes, address + 32L, self.sY);
            _U.putFloat(bytes, address + 36L, self.sZ);
            return dest;
        }

        public FloatTransform load(long offset, MemorySegment src) {
            if (!src.isNative()) return loadOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            float _c6 = _U.getFloat(address + 24L);
            float _c7 = _U.getFloat(address + 28L);
            float _c8 = _U.getFloat(address + 32L);
            float _c9 = _U.getFloat(address + 36L);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        private static FloatTransform loadOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.load(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4L);
            float _c2 = _U.getFloat(bytes, address + 8L);
            float _c3 = _U.getFloat(bytes, address + 12L);
            float _c4 = _U.getFloat(bytes, address + 16L);
            float _c5 = _U.getFloat(bytes, address + 20L);
            float _c6 = _U.getFloat(bytes, address + 24L);
            float _c7 = _U.getFloat(bytes, address + 28L);
            float _c8 = _U.getFloat(bytes, address + 32L);
            float _c9 = _U.getFloat(bytes, address + 36L);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public MemorySegment storeDouble(FloatTransform self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
            if (!dest.isNative()) return storeDoubleOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.tX);
            _U.putDouble(address + 8L, self.tY);
            _U.putDouble(address + 16L, self.tZ);
            _U.putDouble(address + 24L, self.rX);
            _U.putDouble(address + 32L, self.rY);
            _U.putDouble(address + 40L, self.rZ);
            _U.putDouble(address + 48L, self.rW);
            _U.putDouble(address + 56L, self.sX);
            _U.putDouble(address + 64L, self.sY);
            _U.putDouble(address + 72L, self.sZ);
            return dest;
        }

        private static MemorySegment storeDoubleOnHeap(FloatTransform self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeDouble(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.tX);
            _U.putDouble(bytes, address + 8L, self.tY);
            _U.putDouble(bytes, address + 16L, self.tZ);
            _U.putDouble(bytes, address + 24L, self.rX);
            _U.putDouble(bytes, address + 32L, self.rY);
            _U.putDouble(bytes, address + 40L, self.rZ);
            _U.putDouble(bytes, address + 48L, self.rW);
            _U.putDouble(bytes, address + 56L, self.sX);
            _U.putDouble(bytes, address + 64L, self.sY);
            _U.putDouble(bytes, address + 72L, self.sZ);
            return dest;
        }

        public FloatTransform loadDouble(long offset, MemorySegment src) {
            if (!src.isNative()) return loadDoubleOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            float _c6 = (float) _U.getDouble(address + 48L);
            float _c7 = (float) _U.getDouble(address + 56L);
            float _c8 = (float) _U.getDouble(address + 64L);
            float _c9 = (float) _U.getDouble(address + 72L);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        private static FloatTransform loadDoubleOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadDouble(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8L);
            float _c2 = (float) _U.getDouble(bytes, address + 16L);
            float _c3 = (float) _U.getDouble(bytes, address + 24L);
            float _c4 = (float) _U.getDouble(bytes, address + 32L);
            float _c5 = (float) _U.getDouble(bytes, address + 40L);
            float _c6 = (float) _U.getDouble(bytes, address + 48L);
            float _c7 = (float) _U.getDouble(bytes, address + 56L);
            float _c8 = (float) _U.getDouble(bytes, address + 64L);
            float _c9 = (float) _U.getDouble(bytes, address + 72L);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }
    }

    private abstract static class FloatTransformBbOps {
        public abstract FloatBuffer storeAbsolute(FloatTransform self, int index, FloatBuffer buf);
        public abstract FloatTransform loadAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeAbsolute(FloatTransform self, int index, ByteBuffer buf);
        public abstract FloatTransform loadAbsolute(int index, ByteBuffer buf);
        public abstract DoubleBuffer storeAbsolute(FloatTransform self, int index, DoubleBuffer buf);
        public abstract FloatTransform loadAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeDoubleAbsolute(FloatTransform self, int index, ByteBuffer buf);
        public abstract FloatTransform loadDoubleAbsolute(int index, ByteBuffer buf);
    }

    private static final class FloatTransformBbOpsApi extends FloatTransformBbOps {
        public FloatBuffer storeAbsolute(FloatTransform self, int index, FloatBuffer buf) {
            buf.put(index, self.tX);
            buf.put(index + 1, self.tY);
            buf.put(index + 2, self.tZ);
            buf.put(index + 3, self.rX);
            buf.put(index + 4, self.rY);
            buf.put(index + 5, self.rZ);
            buf.put(index + 6, self.rW);
            buf.put(index + 7, self.sX);
            buf.put(index + 8, self.sY);
            buf.put(index + 9, self.sZ);
            return buf;
        }

        public FloatTransform loadAbsolute(int index, FloatBuffer buf) {
            float _c0 = buf.get(index);
            float _c1 = buf.get(index + 1);
            float _c2 = buf.get(index + 2);
            float _c3 = buf.get(index + 3);
            float _c4 = buf.get(index + 4);
            float _c5 = buf.get(index + 5);
            float _c6 = buf.get(index + 6);
            float _c7 = buf.get(index + 7);
            float _c8 = buf.get(index + 8);
            float _c9 = buf.get(index + 9);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public ByteBuffer storeAbsolute(FloatTransform self, int index, ByteBuffer buf) {
            buf.putFloat(index, self.tX);
            buf.putFloat(index + 4, self.tY);
            buf.putFloat(index + 8, self.tZ);
            buf.putFloat(index + 12, self.rX);
            buf.putFloat(index + 16, self.rY);
            buf.putFloat(index + 20, self.rZ);
            buf.putFloat(index + 24, self.rW);
            buf.putFloat(index + 28, self.sX);
            buf.putFloat(index + 32, self.sY);
            buf.putFloat(index + 36, self.sZ);
            return buf;
        }

        public FloatTransform loadAbsolute(int index, ByteBuffer buf) {
            float _c0 = buf.getFloat(index);
            float _c1 = buf.getFloat(index + 4);
            float _c2 = buf.getFloat(index + 8);
            float _c3 = buf.getFloat(index + 12);
            float _c4 = buf.getFloat(index + 16);
            float _c5 = buf.getFloat(index + 20);
            float _c6 = buf.getFloat(index + 24);
            float _c7 = buf.getFloat(index + 28);
            float _c8 = buf.getFloat(index + 32);
            float _c9 = buf.getFloat(index + 36);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public DoubleBuffer storeAbsolute(FloatTransform self, int index, DoubleBuffer buf) {
            buf.put(index, self.tX);
            buf.put(index + 1, self.tY);
            buf.put(index + 2, self.tZ);
            buf.put(index + 3, self.rX);
            buf.put(index + 4, self.rY);
            buf.put(index + 5, self.rZ);
            buf.put(index + 6, self.rW);
            buf.put(index + 7, self.sX);
            buf.put(index + 8, self.sY);
            buf.put(index + 9, self.sZ);
            return buf;
        }

        public FloatTransform loadAbsolute(int index, DoubleBuffer buf) {
            float _c0 = (float) buf.get(index);
            float _c1 = (float) buf.get(index + 1);
            float _c2 = (float) buf.get(index + 2);
            float _c3 = (float) buf.get(index + 3);
            float _c4 = (float) buf.get(index + 4);
            float _c5 = (float) buf.get(index + 5);
            float _c6 = (float) buf.get(index + 6);
            float _c7 = (float) buf.get(index + 7);
            float _c8 = (float) buf.get(index + 8);
            float _c9 = (float) buf.get(index + 9);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public ByteBuffer storeDoubleAbsolute(FloatTransform self, int index, ByteBuffer buf) {
            buf.putDouble(index, self.tX);
            buf.putDouble(index + 8, self.tY);
            buf.putDouble(index + 16, self.tZ);
            buf.putDouble(index + 24, self.rX);
            buf.putDouble(index + 32, self.rY);
            buf.putDouble(index + 40, self.rZ);
            buf.putDouble(index + 48, self.rW);
            buf.putDouble(index + 56, self.sX);
            buf.putDouble(index + 64, self.sY);
            buf.putDouble(index + 72, self.sZ);
            return buf;
        }

        public FloatTransform loadDoubleAbsolute(int index, ByteBuffer buf) {
            float _c0 = (float) buf.getDouble(index);
            float _c1 = (float) buf.getDouble(index + 8);
            float _c2 = (float) buf.getDouble(index + 16);
            float _c3 = (float) buf.getDouble(index + 24);
            float _c4 = (float) buf.getDouble(index + 32);
            float _c5 = (float) buf.getDouble(index + 40);
            float _c6 = (float) buf.getDouble(index + 48);
            float _c7 = (float) buf.getDouble(index + 56);
            float _c8 = (float) buf.getDouble(index + 64);
            float _c9 = (float) buf.getDouble(index + 72);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }
    }

    private static final class FloatTransformBbOpsUnsafe extends FloatTransformBbOps {
        private static final FloatTransformBbOpsApi API = new FloatTransformBbOpsApi();

        public FloatBuffer storeAbsolute(FloatTransform self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            _U.putFloat(address, self.tX);
            _U.putFloat(address + 4L, self.tY);
            _U.putFloat(address + 8L, self.tZ);
            _U.putFloat(address + 12L, self.rX);
            _U.putFloat(address + 16L, self.rY);
            _U.putFloat(address + 20L, self.rZ);
            _U.putFloat(address + 24L, self.rW);
            _U.putFloat(address + 28L, self.sX);
            _U.putFloat(address + 32L, self.sY);
            _U.putFloat(address + 36L, self.sZ);
            return buf;
        }

        public FloatTransform loadAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            float _c6 = _U.getFloat(address + 24L);
            float _c7 = _U.getFloat(address + 28L);
            float _c8 = _U.getFloat(address + 32L);
            float _c9 = _U.getFloat(address + 36L);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public ByteBuffer storeAbsolute(FloatTransform self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(address, self.tX);
            _U.putFloat(address + 4L, self.tY);
            _U.putFloat(address + 8L, self.tZ);
            _U.putFloat(address + 12L, self.rX);
            _U.putFloat(address + 16L, self.rY);
            _U.putFloat(address + 20L, self.rZ);
            _U.putFloat(address + 24L, self.rW);
            _U.putFloat(address + 28L, self.sX);
            _U.putFloat(address + 32L, self.sY);
            _U.putFloat(address + 36L, self.sZ);
            return buf;
        }

        private static ByteBuffer storeAbsoluteElse(FloatTransform self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(bytes, address, self.tX);
            _U.putFloat(bytes, address + 4L, self.tY);
            _U.putFloat(bytes, address + 8L, self.tZ);
            _U.putFloat(bytes, address + 12L, self.rX);
            _U.putFloat(bytes, address + 16L, self.rY);
            _U.putFloat(bytes, address + 20L, self.rZ);
            _U.putFloat(bytes, address + 24L, self.rW);
            _U.putFloat(bytes, address + 28L, self.sX);
            _U.putFloat(bytes, address + 32L, self.sY);
            _U.putFloat(bytes, address + 36L, self.sZ);
            return buf;
        }

        public FloatTransform loadAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            float _c6 = _U.getFloat(address + 24L);
            float _c7 = _U.getFloat(address + 28L);
            float _c8 = _U.getFloat(address + 32L);
            float _c9 = _U.getFloat(address + 36L);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        private static FloatTransform loadAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4L);
            float _c2 = _U.getFloat(bytes, address + 8L);
            float _c3 = _U.getFloat(bytes, address + 12L);
            float _c4 = _U.getFloat(bytes, address + 16L);
            float _c5 = _U.getFloat(bytes, address + 20L);
            float _c6 = _U.getFloat(bytes, address + 24L);
            float _c7 = _U.getFloat(bytes, address + 28L);
            float _c8 = _U.getFloat(bytes, address + 32L);
            float _c9 = _U.getFloat(bytes, address + 36L);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public DoubleBuffer storeAbsolute(FloatTransform self, int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            _U.putDouble(address, self.tX);
            _U.putDouble(address + 8L, self.tY);
            _U.putDouble(address + 16L, self.tZ);
            _U.putDouble(address + 24L, self.rX);
            _U.putDouble(address + 32L, self.rY);
            _U.putDouble(address + 40L, self.rZ);
            _U.putDouble(address + 48L, self.rW);
            _U.putDouble(address + 56L, self.sX);
            _U.putDouble(address + 64L, self.sY);
            _U.putDouble(address + 72L, self.sZ);
            return buf;
        }

        public FloatTransform loadAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            float _c6 = (float) _U.getDouble(address + 48L);
            float _c7 = (float) _U.getDouble(address + 56L);
            float _c8 = (float) _U.getDouble(address + 64L);
            float _c9 = (float) _U.getDouble(address + 72L);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public ByteBuffer storeDoubleAbsolute(FloatTransform self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeDoubleAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(address, self.tX);
            _U.putDouble(address + 8L, self.tY);
            _U.putDouble(address + 16L, self.tZ);
            _U.putDouble(address + 24L, self.rX);
            _U.putDouble(address + 32L, self.rY);
            _U.putDouble(address + 40L, self.rZ);
            _U.putDouble(address + 48L, self.rW);
            _U.putDouble(address + 56L, self.sX);
            _U.putDouble(address + 64L, self.sY);
            _U.putDouble(address + 72L, self.sZ);
            return buf;
        }

        private static ByteBuffer storeDoubleAbsoluteElse(FloatTransform self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeDoubleAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putDouble(bytes, address, self.tX);
            _U.putDouble(bytes, address + 8L, self.tY);
            _U.putDouble(bytes, address + 16L, self.tZ);
            _U.putDouble(bytes, address + 24L, self.rX);
            _U.putDouble(bytes, address + 32L, self.rY);
            _U.putDouble(bytes, address + 40L, self.rZ);
            _U.putDouble(bytes, address + 48L, self.rW);
            _U.putDouble(bytes, address + 56L, self.sX);
            _U.putDouble(bytes, address + 64L, self.sY);
            _U.putDouble(bytes, address + 72L, self.sZ);
            return buf;
        }

        public FloatTransform loadDoubleAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadDoubleAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            float _c6 = (float) _U.getDouble(address + 48L);
            float _c7 = (float) _U.getDouble(address + 56L);
            float _c8 = (float) _U.getDouble(address + 64L);
            float _c9 = (float) _U.getDouble(address + 72L);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        private static FloatTransform loadDoubleAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadDoubleAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8L);
            float _c2 = (float) _U.getDouble(bytes, address + 16L);
            float _c3 = (float) _U.getDouble(bytes, address + 24L);
            float _c4 = (float) _U.getDouble(bytes, address + 32L);
            float _c5 = (float) _U.getDouble(bytes, address + 40L);
            float _c6 = (float) _U.getDouble(bytes, address + 48L);
            float _c7 = (float) _U.getDouble(bytes, address + 56L);
            float _c8 = (float) _U.getDouble(bytes, address + 64L);
            float _c9 = (float) _U.getDouble(bytes, address + 72L);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }
    }

    private abstract static class FloatTransformRawOps {
        public abstract FloatTransform storeUnsafe(FloatTransform self, long address);
        public abstract FloatTransform loadUnsafe(long address);
        public abstract FloatTransform storeDoubleUnsafe(FloatTransform self, long address);
        public abstract FloatTransform loadDoubleUnsafe(long address);
    }

    private static final class FloatTransformRawOpsApi extends FloatTransformRawOps {
        public FloatTransform storeUnsafe(FloatTransform self, long address) {
            self.store(0L, virtualMemory().asSlice(address, 40L));
            return self;
        }

        public FloatTransform loadUnsafe(long address) {
            return FloatTransform.load(0L, virtualMemory().asSlice(address, 40L));
        }

        public FloatTransform storeDoubleUnsafe(FloatTransform self, long address) {
            self.storeDouble(0L, virtualMemory().asSlice(address, 80L));
            return self;
        }

        public FloatTransform loadDoubleUnsafe(long address) {
            return FloatTransform.loadDouble(0L, virtualMemory().asSlice(address, 80L));
        }
    }

    private static final class FloatTransformRawOpsUnsafe extends FloatTransformRawOps {
        public FloatTransform storeUnsafe(FloatTransform self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, self.tX);
            _U.putFloat(address + 4L, self.tY);
            _U.putFloat(address + 8L, self.tZ);
            _U.putFloat(address + 12L, self.rX);
            _U.putFloat(address + 16L, self.rY);
            _U.putFloat(address + 20L, self.rZ);
            _U.putFloat(address + 24L, self.rW);
            _U.putFloat(address + 28L, self.sX);
            _U.putFloat(address + 32L, self.sY);
            _U.putFloat(address + 36L, self.sZ);
            return self;
        }

        public FloatTransform storeHeap(FloatTransform self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, self.tX);
            _U.putFloat(bytes, address + 4L, self.tY);
            _U.putFloat(bytes, address + 8L, self.tZ);
            _U.putFloat(bytes, address + 12L, self.rX);
            _U.putFloat(bytes, address + 16L, self.rY);
            _U.putFloat(bytes, address + 20L, self.rZ);
            _U.putFloat(bytes, address + 24L, self.rW);
            _U.putFloat(bytes, address + 28L, self.sX);
            _U.putFloat(bytes, address + 32L, self.sY);
            _U.putFloat(bytes, address + 36L, self.sZ);
            return self;
        }

        public FloatTransform loadUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(address);
            float _c1 = _U.getFloat(address + 4L);
            float _c2 = _U.getFloat(address + 8L);
            float _c3 = _U.getFloat(address + 12L);
            float _c4 = _U.getFloat(address + 16L);
            float _c5 = _U.getFloat(address + 20L);
            float _c6 = _U.getFloat(address + 24L);
            float _c7 = _U.getFloat(address + 28L);
            float _c8 = _U.getFloat(address + 32L);
            float _c9 = _U.getFloat(address + 36L);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public FloatTransform loadHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = _U.getFloat(bytes, address);
            float _c1 = _U.getFloat(bytes, address + 4L);
            float _c2 = _U.getFloat(bytes, address + 8L);
            float _c3 = _U.getFloat(bytes, address + 12L);
            float _c4 = _U.getFloat(bytes, address + 16L);
            float _c5 = _U.getFloat(bytes, address + 20L);
            float _c6 = _U.getFloat(bytes, address + 24L);
            float _c7 = _U.getFloat(bytes, address + 28L);
            float _c8 = _U.getFloat(bytes, address + 32L);
            float _c9 = _U.getFloat(bytes, address + 36L);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public FloatTransform storeDoubleUnsafe(FloatTransform self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(address, self.tX);
            _U.putDouble(address + 8L, self.tY);
            _U.putDouble(address + 16L, self.tZ);
            _U.putDouble(address + 24L, self.rX);
            _U.putDouble(address + 32L, self.rY);
            _U.putDouble(address + 40L, self.rZ);
            _U.putDouble(address + 48L, self.rW);
            _U.putDouble(address + 56L, self.sX);
            _U.putDouble(address + 64L, self.sY);
            _U.putDouble(address + 72L, self.sZ);
            return self;
        }

        public FloatTransform storeDoubleHeap(FloatTransform self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putDouble(bytes, address, self.tX);
            _U.putDouble(bytes, address + 8L, self.tY);
            _U.putDouble(bytes, address + 16L, self.tZ);
            _U.putDouble(bytes, address + 24L, self.rX);
            _U.putDouble(bytes, address + 32L, self.rY);
            _U.putDouble(bytes, address + 40L, self.rZ);
            _U.putDouble(bytes, address + 48L, self.rW);
            _U.putDouble(bytes, address + 56L, self.sX);
            _U.putDouble(bytes, address + 64L, self.sY);
            _U.putDouble(bytes, address + 72L, self.sZ);
            return self;
        }

        public FloatTransform loadDoubleUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(address);
            float _c1 = (float) _U.getDouble(address + 8L);
            float _c2 = (float) _U.getDouble(address + 16L);
            float _c3 = (float) _U.getDouble(address + 24L);
            float _c4 = (float) _U.getDouble(address + 32L);
            float _c5 = (float) _U.getDouble(address + 40L);
            float _c6 = (float) _U.getDouble(address + 48L);
            float _c7 = (float) _U.getDouble(address + 56L);
            float _c8 = (float) _U.getDouble(address + 64L);
            float _c9 = (float) _U.getDouble(address + 72L);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public FloatTransform loadDoubleHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            float _c0 = (float) _U.getDouble(bytes, address);
            float _c1 = (float) _U.getDouble(bytes, address + 8L);
            float _c2 = (float) _U.getDouble(bytes, address + 16L);
            float _c3 = (float) _U.getDouble(bytes, address + 24L);
            float _c4 = (float) _U.getDouble(bytes, address + 32L);
            float _c5 = (float) _U.getDouble(bytes, address + 40L);
            float _c6 = (float) _U.getDouble(bytes, address + 48L);
            float _c7 = (float) _U.getDouble(bytes, address + 56L);
            float _c8 = (float) _U.getDouble(bytes, address + 64L);
            float _c9 = (float) _U.getDouble(bytes, address + 72L);
            return new FloatTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }
    }
}
