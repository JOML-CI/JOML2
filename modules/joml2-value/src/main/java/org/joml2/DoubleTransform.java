// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import org.joml2.internal.unsafe.HeapSegments;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;
import java.nio.ByteOrder;
import org.joml2.internal.unsafe.UnsafeCopy;
import static org.joml2.internal.unsafe.VirtualMemoryHolder.virtualMemory;

/**
 * Immutable transform of double-precision {@code double} components, declared as a value record.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance; as a value class, instances have no identity and may be flattened by the JVM.
 * <p>
 * Its rotation is a unit quaternion. Every operation that applies, composes, inverts or converts
 * this transform assumes its rotation has unit length and does not divide it out. A value that has
 * drifted from unit length (after many multiplications, say) gives wrong results rather than an
 * error: {@code normalize} it first.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Double.doubleToLongBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
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
@jdk.internal.vm.annotation.LooselyConsistentValue
public value record DoubleTransform(double tX, double tY, double tZ, double rX, double rY, double rZ, double rW, double sX, double sY, double sZ) {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 80;

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
    public DoubleTransform(double tX, double tY, double tZ, double rX, double rY, double rZ, double rW, double sX, double sY, double sZ) {
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
    public DoubleTransform() {
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
    public DoubleTransform(Double3 translation, DoubleQuat rotation, Double3 scale) {
        this(translation.x(), translation.y(), translation.z(), rotation.x(), rotation.y(), rotation.z(), rotation.w(), scale.x(), scale.y(), scale.z());
    }

    /**
     * Create an array of {@code length} elements, each the instance with all components 0.
     * <p>
     * The array is flat where the JVM lets this library create one: when the application exports
     * {@code java.base/jdk.internal.value} to it
     * ({@code --add-exports java.base/jdk.internal.value=org.joml2}, or {@code =ALL-UNNAMED} on the
     * classpath). Its elements are then stored inline, without an object each: reading one follows
     * no reference and storing one allocates nothing. Such an array holds no {@code null} - storing
     * one throws {@link NullPointerException} - and an element written by one thread while another
     * reads it can be read torn, part old, part new. Without the export it is an ordinary array,
     * filled the same way.
     * <p>
     * Valid input: {@code length} must not be negative.
     *
     * @param length the number of elements
     * @return the new array
     * @throws NegativeArraySizeException if {@code length} is negative
     */
    public static DoubleTransform[] newArray(int length) {
        return (DoubleTransform[]) org.joml2.internal.value.FlatArrays.newArray(DoubleTransform.class, length, new DoubleTransform(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    }

    /**
     * Create an array of {@code length} elements, each {@code init}.
     * <p>
     * The array is flat where the JVM lets this library create one: when the application exports
     * {@code java.base/jdk.internal.value} to it
     * ({@code --add-exports java.base/jdk.internal.value=org.joml2}, or {@code =ALL-UNNAMED} on the
     * classpath). Its elements are then stored inline, without an object each: reading one follows
     * no reference and storing one allocates nothing. Such an array holds no {@code null} - storing
     * one throws {@link NullPointerException} - and an element written by one thread while another
     * reads it can be read torn, part old, part new. Without the export it is an ordinary array,
     * filled the same way.
     * <p>
     * Valid input: {@code length} must not be negative.
     *
     * @param length the number of elements
     * @param init the value every element starts as
     * @return the new array
     * @throws NullPointerException if {@code init} is null
     * @throws NegativeArraySizeException if {@code length} is negative
     */
    public static DoubleTransform[] newArray(int length, DoubleTransform init) {
        return (DoubleTransform[]) org.joml2.internal.value.FlatArrays.newArray(DoubleTransform.class, length, init);
    }

    /** {@return the {@code tX} component} <p>Valid input: any value, NaN and the infinities included. */
    public double tX() { return tX; }

    /** {@return the {@code tY} component} <p>Valid input: any value, NaN and the infinities included. */
    public double tY() { return tY; }

    /** {@return the {@code tZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public double tZ() { return tZ; }

    /** {@return the {@code rX} component} <p>Valid input: any value, NaN and the infinities included. */
    public double rX() { return rX; }

    /** {@return the {@code rY} component} <p>Valid input: any value, NaN and the infinities included. */
    public double rY() { return rY; }

    /** {@return the {@code rZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public double rZ() { return rZ; }

    /** {@return the {@code rW} component} <p>Valid input: any value, NaN and the infinities included. */
    public double rW() { return rW; }

    /** {@return the {@code sX} component} <p>Valid input: any value, NaN and the infinities included. */
    public double sX() { return sX; }

    /** {@return the {@code sY} component} <p>Valid input: any value, NaN and the infinities included. */
    public double sY() { return sY; }

    /** {@return the {@code sZ} component} <p>Valid input: any value, NaN and the infinities included. */
    public double sZ() { return sZ; }

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
    public DoubleTransform set(Double3 translation, DoubleQuat rotation, Double3 scale) {
        return new DoubleTransform(translation, rotation, scale);
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
    public static DoubleTransform makeFromAxisAngle(Double3 axis, double angle, Double3 translation) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleTransform(translationX, translationY, translationZ, axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0), 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeFromAxisAngle(double axisX, double axisY, double axisZ, double angle, double translationX, double translationY, double translationZ) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleTransform(translationX, translationY, translationZ, axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0), 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeTranslationRotation(Double3 translation, DoubleQuat rotation) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        return new DoubleTransform(translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeTranslationRotation(double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW) {
        return new DoubleTransform(translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeTranslationRotationScale(Double3 translation, DoubleQuat rotation, Double3 scale) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        double scaleX = scale.x();
        double scaleY = scale.y();
        double scaleZ = scale.z();
        return new DoubleTransform(translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
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
    public static DoubleTransform makeTranslationRotationScale(double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        return new DoubleTransform(translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
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
    public DoubleTransform set(DoubleTransform v) {
        double vTX = v.tX;
        double vTY = v.tY;
        double vTZ = v.tZ;
        double vRX = v.rX;
        double vRY = v.rY;
        double vRZ = v.rZ;
        double vRW = v.rW;
        double vSX = v.sX;
        double vSY = v.sY;
        double vSZ = v.sZ;
        return new DoubleTransform(vTX, vTY, vTZ, vRX, vRY, vRZ, vRW, vSX, vSY, vSZ);
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
    public DoubleTransform set(double vTX, double vTY, double vTZ, double vRX, double vRY, double vRZ, double vRW, double vSX, double vSY, double vSZ) {
        return new DoubleTransform(vTX, vTY, vTZ, vRX, vRY, vRZ, vRW, vSX, vSY, vSZ);
    }

    /**
     * Set the rotation of this transform to {@code r}, returning the result as a value.
     * <p>
     * Valid input: {@code r} must have unit length.
     *
     * @param r the new rotation
     * @return the resulting transform
     */
    public DoubleTransform setRotation(DoubleQuat r) {
        double rX = r.x();
        double rY = r.y();
        double rZ = r.z();
        double rW = r.w();
        return new DoubleTransform(this.tX, this.tY, this.tZ, rX, rY, rZ, rW, this.sX, this.sY, this.sZ);
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
    public DoubleTransform setRotation(double rX, double rY, double rZ, double rW) {
        return new DoubleTransform(this.tX, this.tY, this.tZ, rX, rY, rZ, rW, this.sX, this.sY, this.sZ);
    }

    /**
     * Set the scale of this transform to {@code s}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @return the resulting transform
     */
    public DoubleTransform setScale(Double3 s) {
        double sX = s.x();
        double sY = s.y();
        double sZ = s.z();
        return new DoubleTransform(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW, sX, sY, sZ);
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
    public DoubleTransform setScale(double sX, double sY, double sZ) {
        return new DoubleTransform(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW, sX, sY, sZ);
    }

    /**
     * Set the scale of this transform to {@code uniform}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param uniform the uniform scale factor
     * @return the resulting transform
     */
    public DoubleTransform setScale(double uniform) {
        return new DoubleTransform(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW, uniform, uniform, uniform);
    }

    /**
     * Set the translation of this transform to {@code t}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the translation vector
     * @return the resulting transform
     */
    public DoubleTransform setTranslation(Double3 t) {
        double tX = t.x();
        double tY = t.y();
        double tZ = t.z();
        return new DoubleTransform(tX, tY, tZ, this.rX, this.rY, this.rZ, this.rW, this.sX, this.sY, this.sZ);
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
    public DoubleTransform setTranslation(double tX, double tY, double tZ) {
        return new DoubleTransform(tX, tY, tZ, this.rX, this.rY, this.rZ, this.rW, this.sX, this.sY, this.sZ);
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
    public static DoubleTransform makeFromDualQuat(DoubleDualQuat dq) {
        double dqRX = dq.rX();
        double dqRY = dq.rY();
        double dqRZ = dq.rZ();
        double dqRW = dq.rW();
        double dqDX = dq.dX();
        double dqDY = dq.dY();
        double dqDZ = dq.dZ();
        double dqDW = dq.dW();
        if (Math.useFma()) {
            return new DoubleTransform(2.0 * (java.lang.Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + java.lang.Math.fma(dqRW, dqDX, dqRX * -dqDW)), 2.0 * (java.lang.Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + java.lang.Math.fma(dqRW, dqDY, dqRY * -dqDW)), 2.0 * (java.lang.Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + java.lang.Math.fma(dqRW, dqDZ, dqRZ * -dqDW)), dqRX, dqRY, dqRZ, dqRW, 1.0, 1.0, 1.0);
        } else {
            return new DoubleTransform(2.0 * (((dqRY) * (dqDZ) - (dqRZ * dqDY)) + ((dqRW) * (dqDX) - (dqRX * dqDW))), 2.0 * (((dqRZ) * (dqDX) - (dqRX * dqDZ)) + ((dqRW) * (dqDY) - (dqRY * dqDW))), 2.0 * (((dqRX) * (dqDY) - (dqRY * dqDX)) + ((dqRW) * (dqDZ) - (dqRZ * dqDW))), dqRX, dqRY, dqRZ, dqRW, 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeFromDualQuat(double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        if (Math.useFma()) {
            return new DoubleTransform(2.0 * (java.lang.Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + java.lang.Math.fma(dqRW, dqDX, dqRX * -dqDW)), 2.0 * (java.lang.Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + java.lang.Math.fma(dqRW, dqDY, dqRY * -dqDW)), 2.0 * (java.lang.Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + java.lang.Math.fma(dqRW, dqDZ, dqRZ * -dqDW)), dqRX, dqRY, dqRZ, dqRW, 1.0, 1.0, 1.0);
        } else {
            return new DoubleTransform(2.0 * (((dqRY) * (dqDZ) - (dqRZ * dqDY)) + ((dqRW) * (dqDX) - (dqRX * dqDW))), 2.0 * (((dqRZ) * (dqDX) - (dqRX * dqDZ)) + ((dqRW) * (dqDY) - (dqRY * dqDW))), 2.0 * (((dqRX) * (dqDY) - (dqRY * dqDX)) + ((dqRW) * (dqDZ) - (dqRZ * dqDW))), dqRX, dqRY, dqRZ, dqRW, 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeFromMatrix(Double3x3 m) {
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleTransform makeFromMatrix_fma(Double3x3 m) {
        double _m_m21 = m.m21();
        double _m_m01 = m.m01();
        double _m_m11 = m.m11();
        double _t12 = java.lang.Math.fma(_m_m21, _m_m21, java.lang.Math.fma(_m_m01, _m_m01, _m_m11 * _m_m11));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _m_m22 = m.m22();
        double _m_m02 = m.m02();
        double _m_m12 = m.m12();
        double _t13 = java.lang.Math.fma(_m_m22, _m_m22, java.lang.Math.fma(_m_m02, _m_m02, _m_m12 * _m_m12));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _m_m20 = m.m20();
        double _m_m00 = m.m00();
        double _m_m10 = m.m10();
        double _t14 = java.lang.Math.fma(_m_m20, _m_m20, java.lang.Math.fma(_m_m00, _m_m00, _m_m10 * _m_m10));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        double _sfx8 = java.lang.Math.sqrt(_t12);
        double _t15 = 1.0 / _sfx8;
        double _sfx9 = java.lang.Math.sqrt(_t13);
        return makeFromMatrix_s4b73775e_1_fma(m, -_m_m11, -_m_m22, _t18, _m_m10 * _t17, _m_m20 * _t17, _m_m00 * _t17, 0.0, 0.0, 0.0, _sfx8, _t15, _m_m21 * _t15, _m_m11 * _t15, _sfx9, 1.0 / _sfx9);
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleTransform makeFromMatrix_mulAdd(Double3x3 m) {
        double _m_m21 = m.m21();
        double _m_m01 = m.m01();
        double _m_m11 = m.m11();
        double _t12 = ((_m_m21) * (_m_m21) + (((_m_m01) * (_m_m01) + (_m_m11 * _m_m11))));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _m_m22 = m.m22();
        double _m_m02 = m.m02();
        double _m_m12 = m.m12();
        double _t13 = ((_m_m22) * (_m_m22) + (((_m_m02) * (_m_m02) + (_m_m12 * _m_m12))));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _m_m20 = m.m20();
        double _m_m00 = m.m00();
        double _m_m10 = m.m10();
        double _t14 = ((_m_m20) * (_m_m20) + (((_m_m00) * (_m_m00) + (_m_m10 * _m_m10))));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        double _sfx8 = java.lang.Math.sqrt(_t12);
        double _t15 = 1.0 / _sfx8;
        double _sfx9 = java.lang.Math.sqrt(_t13);
        return makeFromMatrix_s4b73775e_1_mulAdd(m, -_m_m11, -_m_m22, _t18, _m_m10 * _t17, _m_m20 * _t17, _m_m00 * _t17, 0.0, 0.0, 0.0, _sfx8, _t15, _m_m21 * _t15, _m_m11 * _t15, _sfx9, 1.0 / _sfx9);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_s4b73775e_1_fma(Double3x3 m, double _t0, double _t1, double _t18, double _t19, double _t22, double _t27, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t24, double _t25, double _sfx9, double _t16) {
        double _m_m22 = m.m22();
        double _t20 = _m_m22 * _t16;
        double _m_m12 = m.m12();
        double _t21 = _m_m12 * _t16;
        double _t48, _t49, _t50, _sfx7;
        double _m_m01 = m.m01();
        double _m_m02 = m.m02();
        if (java.lang.Math.fma(-java.lang.Math.fma(_t19, _t20, -(_t21 * _t22)), _m_m01 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t19, _t24, -(_t25 * _t22)), _m_m02 * _t16, java.lang.Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27)) < 0.0) {
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
        double _t52 = 1.0 + _t48;
        double _t64 = java.lang.Math.fma(m.m11(), _t15, java.lang.Math.fma(_m_m22, _t16, _t52));
        return makeFromMatrix_s4b73775e_2_fma(m, _t0, _t1, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, java.lang.Math.fma(_m_m12, _t16, _t24), java.lang.Math.fma(m.m21(), _t15, -_t21), _t48, _sfx7, _t52, 1.0 - _t48, java.lang.Math.fma(_m_m01, _t15, _t49), java.lang.Math.fma(_m_m02, _t16, _t50), java.lang.Math.fma(_m_m02, _t16, -_t50), java.lang.Math.fma(-_m_m01, _t15, _t49), _t64, 0.5 * (1.0 / java.lang.Math.sqrt(_t64)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_s4b73775e_1_mulAdd(Double3x3 m, double _t0, double _t1, double _t18, double _t19, double _t22, double _t27, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t24, double _t25, double _sfx9, double _t16) {
        double _m_m22 = m.m22();
        double _t20 = _m_m22 * _t16;
        double _m_m12 = m.m12();
        double _t21 = _m_m12 * _t16;
        double _t48, _t49, _t50, _sfx7;
        double _m_m02 = m.m02();
        double _m_m01 = m.m01();
        if (((((((_t19) * (_t24) - (_t25 * _t22))) * (_m_m02 * _t16) + (((_t25) * (_t20) - (_t21 * _t24)) * _t27))) - ((_t19) * (_t20) - (_t21 * _t22)) * (_m_m01 * _t15)) < 0.0) {
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
        double _t52 = 1.0 + _t48;
        double _t64 = ((m.m11()) * (_t15) + (((_m_m22) * (_t16) + (_t52))));
        return makeFromMatrix_s4b73775e_2_mulAdd(m, _t0, _t1, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, ((_m_m12) * (_t16) + (_t24)), ((m.m21()) * (_t15) - (_t21)), _t48, _sfx7, _t52, 1.0 - _t48, ((_m_m01) * (_t15) + (_t49)), ((_m_m02) * (_t16) + (_t50)), ((_m_m02) * (_t16) - (_t50)), ((_t49) - (_m_m01) * (_t15)), _t64, 0.5 * (1.0 / java.lang.Math.sqrt(_t64)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_s4b73775e_2_fma(Double3x3 m, double _t0, double _t1, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t25, double _sfx9, double _t16, double _t20, double _t32, double _t36, double _t48, double _sfx7, double _t52, double _t53, double _t55, double _t56, double _t57, double _t58, double _t64, double _sp0) {
        double _m_m11 = m.m11();
        double _t66 = java.lang.Math.fma(_m_m11, _t15, java.lang.Math.fma(_t1, _t16, _t53));
        double _m_m22 = m.m22();
        double _t67 = java.lang.Math.fma(_m_m22, _t16, java.lang.Math.fma(_t0, _t15, _t53));
        double _t68 = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t68));
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (java.lang.Math.fma(_m_m11, _t15, java.lang.Math.fma(_m_m22, _t16, _t48)) > 0.0) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > Math.max(_t25, _t20)) {
                _sfx3 = 0.5 * java.lang.Math.sqrt(_t68);
                _sfx4 = _sp3 * _t55;
                _sfx5 = _sp3 * _t56;
                _sfx6 = _sp3 * _t36;
            } else {
                if (_t25 > _t20) {
                    _sfx3 = _sp1 * _t55;
                    _sfx4 = 0.5 * java.lang.Math.sqrt(_t66);
                    _sfx5 = _sp1 * _t32;
                    _sfx6 = _sp1 * _t57;
                } else {
                    _sfx3 = _sp2 * _t56;
                    _sfx4 = _sp2 * _t32;
                    _sfx5 = 0.5 * java.lang.Math.sqrt(_t67);
                    _sfx6 = _sp2 * _t58;
                }
            }
        }
        return new DoubleTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_s4b73775e_2_mulAdd(Double3x3 m, double _t0, double _t1, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t25, double _sfx9, double _t16, double _t20, double _t32, double _t36, double _t48, double _sfx7, double _t52, double _t53, double _t55, double _t56, double _t57, double _t58, double _t64, double _sp0) {
        double _m_m11 = m.m11();
        double _t66 = ((_m_m11) * (_t15) + (((_t1) * (_t16) + (_t53))));
        double _m_m22 = m.m22();
        double _t67 = ((_m_m22) * (_t16) + (((_t0) * (_t15) + (_t53))));
        double _t68 = ((_t0) * (_t15) + (((_t1) * (_t16) + (_t52))));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t68));
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (((_m_m11) * (_t15) + (((_m_m22) * (_t16) + (_t48)))) > 0.0) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > Math.max(_t25, _t20)) {
                _sfx3 = 0.5 * java.lang.Math.sqrt(_t68);
                _sfx4 = _sp3 * _t55;
                _sfx5 = _sp3 * _t56;
                _sfx6 = _sp3 * _t36;
            } else {
                if (_t25 > _t20) {
                    _sfx3 = _sp1 * _t55;
                    _sfx4 = 0.5 * java.lang.Math.sqrt(_t66);
                    _sfx5 = _sp1 * _t32;
                    _sfx6 = _sp1 * _t57;
                } else {
                    _sfx3 = _sp2 * _t56;
                    _sfx4 = _sp2 * _t32;
                    _sfx5 = 0.5 * java.lang.Math.sqrt(_t67);
                    _sfx6 = _sp2 * _t58;
                }
            }
        }
        return new DoubleTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail_fma(double _t31, double _t17, double _t15, double _t16, double _t32, double _t13, double _t12, double _t14, double _t35, double _t36, double _t29, double _t2, double _t37, double _t27, double _t28, double _t0, double _t1) {
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t44 = java.lang.Math.abs(_t35);
        double _t45 = java.lang.Math.abs(_t36);
        double _t48 = java.lang.Math.abs(_t39);
        double _t49 = java.lang.Math.abs(_t40);
        double _t52 = java.lang.Math.abs(_t42);
        double _t53 = java.lang.Math.abs(_t41);
        double _t75, _t78;
        if (_t44 < _t45) {
            _t75 = _t37;
            _t78 = 0.0;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
        }
        double _t76, _t79;
        if (_t48 < _t49) {
            _t76 = _t38;
            _t79 = 0.0;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
        }
        double _t77, _t80;
        if (_t52 < _t53) {
            _t77 = _t43;
            _t80 = 0.0;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail2_fma(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail_mulAdd(double _t31, double _t17, double _t15, double _t16, double _t32, double _t13, double _t12, double _t14, double _t35, double _t36, double _t29, double _t2, double _t37, double _t27, double _t28, double _t0, double _t1) {
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t44 = java.lang.Math.abs(_t35);
        double _t45 = java.lang.Math.abs(_t36);
        double _t48 = java.lang.Math.abs(_t39);
        double _t49 = java.lang.Math.abs(_t40);
        double _t52 = java.lang.Math.abs(_t42);
        double _t53 = java.lang.Math.abs(_t41);
        double _t75, _t78;
        if (_t44 < _t45) {
            _t75 = _t37;
            _t78 = 0.0;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
        }
        double _t76, _t79;
        if (_t48 < _t49) {
            _t76 = _t38;
            _t79 = 0.0;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
        }
        double _t77, _t80;
        if (_t52 < _t53) {
            _t77 = _t43;
            _t80 = 0.0;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail2_mulAdd(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail2_fma(double _t44, double _t45, double _t36, double _t35, double _t48, double _t49, double _t40, double _t39, double _t52, double _t53, double _t41, double _t42, double _t78, double _t75, double _t79, double _t76, double _t80, double _t77, double _t27, double _t28, double _t29, double _t38, double _t37, double _t43, double _t56, double _t0, double _t1) {
        double _t90 = _t44 < _t45 ? -_t36 : _t35;
        double _t91 = _t48 < _t49 ? -_t40 : _t39;
        double _t92 = _t52 < _t53 ? -_t41 : _t42;
        double _t102 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t78, _t78, java.lang.Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t92, _t92, _t77 * _t77))));
        return makeFromMatrix_degenerate_s91e5aa_tail3_fma(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail2_mulAdd(double _t44, double _t45, double _t36, double _t35, double _t48, double _t49, double _t40, double _t39, double _t52, double _t53, double _t41, double _t42, double _t78, double _t75, double _t79, double _t76, double _t80, double _t77, double _t27, double _t28, double _t29, double _t38, double _t37, double _t43, double _t56, double _t0, double _t1) {
        double _t90 = _t44 < _t45 ? -_t36 : _t35;
        double _t91 = _t48 < _t49 ? -_t40 : _t39;
        double _t92 = _t52 < _t53 ? -_t41 : _t42;
        double _t102 = (1.0 / java.lang.Math.sqrt(((_t78) * (_t78) + (((_t90) * (_t90) + (_t75 * _t75))))));
        double _t103 = (1.0 / java.lang.Math.sqrt(((_t79) * (_t79) + (((_t91) * (_t91) + (_t76 * _t76))))));
        double _t104 = (1.0 / java.lang.Math.sqrt(((_t80) * (_t80) + (((_t92) * (_t92) + (_t77 * _t77))))));
        return makeFromMatrix_degenerate_s91e5aa_tail3_mulAdd(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail3_fma(double _t27, double _t28, double _t29, double _t105, double _t38, double _t108, double _t39, double _t117, double _t35, double _t37, double _t41, double _t118, double _t36, double _t109, double _t42, double _t119, double _t106, double _t40, double _t43, double _t107, double _t110, double _t56, double _t0, double _t1) {
        double _t168, _t169;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t169 = 0.0;
                } else {
                    _t168 = _t105;
                    _t169 = java.lang.Math.fma(_t35, _t105, -(_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0 ? java.lang.Math.fma(_t38, _t108, -_t39 * _t117) : java.lang.Math.fma(_t35, _t38, _t37 * -_t39);
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t118 : java.lang.Math.fma(_t35, _t41, -(_t36 * _t42)) : _t38;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail4_fma(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail3_mulAdd(double _t27, double _t28, double _t29, double _t105, double _t38, double _t108, double _t39, double _t117, double _t35, double _t37, double _t41, double _t118, double _t36, double _t109, double _t42, double _t119, double _t106, double _t40, double _t43, double _t107, double _t110, double _t56, double _t0, double _t1) {
        double _t168, _t169;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t169 = 0.0;
                } else {
                    _t168 = _t105;
                    _t169 = ((_t35) * (_t105) - (_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0 ? ((_t38) * (_t108) - (_t39 * _t117)) : ((_t35) * (_t38) - (_t37 * _t39));
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t118 : ((_t35) * (_t41) - (_t36 * _t42)) : _t38;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail4_mulAdd(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail4_fma(double _t27, double _t28, double _t29, double _t119, double _t39, double _t106, double _t40, double _t108, double _t36, double _t35, double _t43, double _t107, double _t37, double _t109, double _t42, double _t41, double _t118, double _t38, double _t117, double _t110, double _t105, double _t169, double _t168, double _t56, double _t0, double _t1) {
        double _t170, _t171;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t170 = 1.0;
                    _t171 = 0.0;
                } else {
                    _t170 = _t119;
                    _t171 = java.lang.Math.fma(_t37, _t109, -_t35 * _t119);
                }
            } else {
                _t170 = _t29 <= 0.0 ? java.lang.Math.fma(_t39, _t106, -(_t40 * _t108)) : java.lang.Math.fma(_t36, _t39, -_t35 * _t40);
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t107 : java.lang.Math.fma(_t37, _t42, -_t35 * _t43) : _t40;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail5_fma(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail4_mulAdd(double _t27, double _t28, double _t29, double _t119, double _t39, double _t106, double _t40, double _t108, double _t36, double _t35, double _t43, double _t107, double _t37, double _t109, double _t42, double _t41, double _t118, double _t38, double _t117, double _t110, double _t105, double _t169, double _t168, double _t56, double _t0, double _t1) {
        double _t170, _t171;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t170 = 1.0;
                    _t171 = 0.0;
                } else {
                    _t170 = _t119;
                    _t171 = ((_t37) * (_t109) - (_t35 * _t119));
                }
            } else {
                _t170 = _t29 <= 0.0 ? ((_t39) * (_t106) - (_t40 * _t108)) : ((_t36) * (_t39) - (_t35 * _t40));
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t107 : ((_t37) * (_t42) - (_t35 * _t43)) : _t40;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail5_mulAdd(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail5_fma(double _t29, double _t27, double _t28, double _t108, double _t41, double _t118, double _t43, double _t107, double _t38, double _t40, double _t35, double _t109, double _t117, double _t106, double _t37, double _t36, double _t42, double _t110, double _t119, double _t105, double _t39, double _t169, double _t170, double _t168, double _t171, double _t56, double _t0, double _t1) {
        double _t172, _t173;
        if (_t29 <= 0.0) {
            if (_t27 <= 0.0) {
                if (_t28 <= 0.0) {
                    _t172 = 0.0;
                    _t173 = 0.0;
                } else {
                    _t172 = _t108;
                    _t173 = java.lang.Math.fma(_t40, _t117, -(_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0 ? java.lang.Math.fma(_t41, _t118, -(_t43 * _t107)) : java.lang.Math.fma(_t41, _t38, -(_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0 ? _t28 <= 0.0 ? _t109 : java.lang.Math.fma(_t37, _t40, -(_t36 * _t38)) : _t42;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail6_fma(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail5_mulAdd(double _t29, double _t27, double _t28, double _t108, double _t41, double _t118, double _t43, double _t107, double _t38, double _t40, double _t35, double _t109, double _t117, double _t106, double _t37, double _t36, double _t42, double _t110, double _t119, double _t105, double _t39, double _t169, double _t170, double _t168, double _t171, double _t56, double _t0, double _t1) {
        double _t172, _t173;
        if (_t29 <= 0.0) {
            if (_t27 <= 0.0) {
                if (_t28 <= 0.0) {
                    _t172 = 0.0;
                    _t173 = 0.0;
                } else {
                    _t172 = _t108;
                    _t173 = ((_t40) * (_t117) - (_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0 ? ((_t41) * (_t118) - (_t43 * _t107)) : ((_t41) * (_t38) - (_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0 ? _t28 <= 0.0 ? _t109 : ((_t37) * (_t40) - (_t36 * _t38)) : _t42;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail6_mulAdd(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail6_fma(double _t28, double _t29, double _t27, double _t110, double _t36, double _t119, double _t37, double _t105, double _t43, double _t41, double _t39, double _t117, double _t42, double _t107, double _t40, double _t106, double _t118, double _t38, double _t173, double _t169, double _t170, double _t168, double _t171, double _t172, double _t56, double _t0, double _t1) {
        double _t174, _t175;
        if (_t28 <= 0.0) {
            if (_t29 <= 0.0) {
                if (_t27 <= 0.0) {
                    _t174 = 1.0;
                    _t175 = 0.0;
                } else {
                    _t174 = _t110;
                    _t175 = java.lang.Math.fma(_t42, _t107, -(_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0 ? java.lang.Math.fma(_t36, _t119, -(_t37 * _t105)) : java.lang.Math.fma(_t36, _t43, -(_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t117 : java.lang.Math.fma(_t42, _t40, -(_t41 * _t39)) : _t37;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail7_fma(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail6_mulAdd(double _t28, double _t29, double _t27, double _t110, double _t36, double _t119, double _t37, double _t105, double _t43, double _t41, double _t39, double _t117, double _t42, double _t107, double _t40, double _t106, double _t118, double _t38, double _t173, double _t169, double _t170, double _t168, double _t171, double _t172, double _t56, double _t0, double _t1) {
        double _t174, _t175;
        if (_t28 <= 0.0) {
            if (_t29 <= 0.0) {
                if (_t27 <= 0.0) {
                    _t174 = 1.0;
                    _t175 = 0.0;
                } else {
                    _t174 = _t110;
                    _t175 = ((_t42) * (_t107) - (_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0 ? ((_t36) * (_t119) - (_t37 * _t105)) : ((_t36) * (_t43) - (_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t117 : ((_t42) * (_t40) - (_t41 * _t39)) : _t37;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail7_mulAdd(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail7_fma(double _t29, double _t27, double _t28, double _t106, double _t43, double _t110, double _t42, double _t118, double _t39, double _t38, double _t36, double _t173, double _t169, double _t170, double _t174, double _t168, double _t171, double _t172, double _t175, double _t56, double _t0, double _t1) {
        double _t176 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t28 <= 0.0 ? 1.0 : _t106 : _t28 <= 0.0 ? java.lang.Math.fma(_t43, _t110, -_t42 * _t118) : java.lang.Math.fma(_t43, _t39, -_t42 * _t38) : _t36;
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
        return makeFromMatrix_degenerate_s91e5aa_tail8(_t199, _t171, _t198, _t168, _t197, _t170, _t174, _t173 - _t169, Math.max(_t170, _t174), _t198 + _t168, _t173 + _t169, _t196, _t56, _t27, _t0, _t28, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail7_mulAdd(double _t29, double _t27, double _t28, double _t106, double _t43, double _t110, double _t42, double _t118, double _t39, double _t38, double _t36, double _t173, double _t169, double _t170, double _t174, double _t168, double _t171, double _t172, double _t175, double _t56, double _t0, double _t1) {
        double _t176 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t28 <= 0.0 ? 1.0 : _t106 : _t28 <= 0.0 ? ((_t43) * (_t110) - (_t42 * _t118)) : ((_t43) * (_t39) - (_t42 * _t38)) : _t36;
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
        return makeFromMatrix_degenerate_s91e5aa_tail8(_t199, _t171, _t198, _t168, _t197, _t170, _t174, _t173 - _t169, Math.max(_t170, _t174), _t198 + _t168, _t173 + _t169, _t196, _t56, _t27, _t0, _t28, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail8(double _t199, double _t171, double _t198, double _t168, double _t197, double _t170, double _t174, double _t185, double _t186, double _t202, double _t187, double _t196, double _t56, double _t27, double _t0, double _t28, double _t1) {
        double _t203 = _t199 + _t171;
        double _t209 = _t197 + _t170 + _t174;
        double _t210 = 1.0 + _t209;
        double _t211 = 1.0 + _t197 - _t170 - _t174;
        double _t212 = 1.0 + _t170 - _t197 - _t174;
        double _t213 = 1.0 + _t174 - _t197 - _t170;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t213));
        double _sfx3 = _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        return makeFromMatrix_degenerate_s91e5aa_tail9(_t209, _sp0, _t171 - _t199, _t197, _t186, 0.5 * (1.0 / java.lang.Math.sqrt(_t211)), _t202, _t170, _t174, _t212, _sp2, _t187, _t198 - _t168, _t203, _sp1, _t213, _t210, _t185, _t196, _t56, _t27, _t0, _t28, _t1, 0.0, 0.0, 0.0, _sfx3);
    }

    /**
     * Private tail of {@code makeFromMatrix_degenerate}. Shared by 3 identical private paths of
     * {@code makeFromMatrix}; reached only through it.
     */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail9(double _t209, double _sp0, double _t204, double _t197, double _t186, double _sp3, double _t202, double _t170, double _t174, double _t212, double _sp2, double _t187, double _t205, double _t203, double _sp1, double _t213, double _t210, double _t185, double _t196, double _t56, double _t27, double _t0, double _t28, double _t1, double _sfx0, double _sfx1, double _sfx2, double _sfx3) {
        double _sfx4, _sfx5, _sfx6;
        if (_t209 > 0.0) {
            _sfx4 = _sp0 * _t204;
            _sfx5 = _sp0 * _t205;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t210);
        } else {
            if (_t197 > _t186) {
                _sfx4 = _sp3 * _t202;
                _sfx5 = _sp3 * _t203;
                _sfx6 = _sp3 * _t185;
            } else {
                if (_t170 > _t174) {
                    _sfx4 = 0.5 * java.lang.Math.sqrt(_t212);
                    _sfx5 = _sp1 * _t187;
                    _sfx6 = _sp1 * _t204;
                } else {
                    _sfx4 = _sp2 * _t187;
                    _sfx5 = 0.5 * java.lang.Math.sqrt(_t213);
                    _sfx6 = _sp2 * _t205;
                }
            }
        }
        double _sfx7 = _t196 < 0.0 ? -_t56 : _t56;
        double _sfx8 = _t27 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t27) / _t0;
        double _sfx9 = _t28 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t28) / _t1;
        return new DoubleTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static DoubleTransform makeFromMatrix_degenerate_fma(Double3x3 m) {
        double _m_m01 = m.m01();
        double _m_m11 = m.m11();
        double _m_m21 = m.m21();
        double _t0 = unitScale(_m_m01, _m_m11, _m_m21);
        double _m_m02 = m.m02();
        double _m_m12 = m.m12();
        double _m_m22 = m.m22();
        double _t1 = unitScale(_m_m02, _m_m12, _m_m22);
        double _m_m00 = m.m00();
        double _m_m10 = m.m10();
        double _m_m20 = m.m20();
        double _t2 = unitScale(_m_m00, _m_m10, _m_m20);
        double _t12 = _m_m21 * _t0;
        double _t13 = _m_m01 * _t0;
        double _t14 = _m_m11 * _t0;
        double _t15 = _m_m22 * _t1;
        double _t16 = _m_m02 * _t1;
        double _t17 = _m_m12 * _t1;
        double _t18 = _m_m20 * _t2;
        double _t19 = _m_m00 * _t2;
        double _t20 = _m_m10 * _t2;
        double _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_s91e5aa_tail_fma((1.0 / java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0 / java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t30 * _t20, _t27, _t28, _t0, _t1);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static DoubleTransform makeFromMatrix_degenerate_mulAdd(Double3x3 m) {
        double _m_m01 = m.m01();
        double _m_m11 = m.m11();
        double _m_m21 = m.m21();
        double _t0 = unitScale(_m_m01, _m_m11, _m_m21);
        double _m_m02 = m.m02();
        double _m_m12 = m.m12();
        double _m_m22 = m.m22();
        double _t1 = unitScale(_m_m02, _m_m12, _m_m22);
        double _m_m00 = m.m00();
        double _m_m10 = m.m10();
        double _m_m20 = m.m20();
        double _t2 = unitScale(_m_m00, _m_m10, _m_m20);
        double _t12 = _m_m21 * _t0;
        double _t13 = _m_m01 * _t0;
        double _t14 = _m_m11 * _t0;
        double _t15 = _m_m22 * _t1;
        double _t16 = _m_m02 * _t1;
        double _t17 = _m_m12 * _t1;
        double _t18 = _m_m20 * _t2;
        double _t19 = _m_m00 * _t2;
        double _t20 = _m_m10 * _t2;
        double _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        double _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_s91e5aa_tail_mulAdd((1.0 / java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0 / java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t30 * _t20, _t27, _t28, _t0, _t1);
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
    public static DoubleTransform makeFromMatrix(Double3x4 m) {
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleTransform makeFromMatrix_fma(Double3x4 m) {
        double _m_m21 = m.m21();
        double _m_m01 = m.m01();
        double _m_m11 = m.m11();
        double _t12 = java.lang.Math.fma(_m_m21, _m_m21, java.lang.Math.fma(_m_m01, _m_m01, _m_m11 * _m_m11));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _m_m22 = m.m22();
        double _m_m02 = m.m02();
        double _m_m12 = m.m12();
        double _t13 = java.lang.Math.fma(_m_m22, _m_m22, java.lang.Math.fma(_m_m02, _m_m02, _m_m12 * _m_m12));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _m_m20 = m.m20();
        double _m_m00 = m.m00();
        double _m_m10 = m.m10();
        double _t14 = java.lang.Math.fma(_m_m20, _m_m20, java.lang.Math.fma(_m_m00, _m_m00, _m_m10 * _m_m10));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        double _sfx8 = java.lang.Math.sqrt(_t12);
        double _t15 = 1.0 / _sfx8;
        return makeFromMatrix_s4d24221f_1_fma(m, _t13, -_m_m11, -_m_m22, _t18, _m_m10 * _t17, _m_m20 * _t17, _m_m00 * _t17, m.m03(), m.m13(), m.m23(), _sfx8, _t15, _m_m21 * _t15);
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleTransform makeFromMatrix_mulAdd(Double3x4 m) {
        double _m_m21 = m.m21();
        double _m_m01 = m.m01();
        double _m_m11 = m.m11();
        double _t12 = ((_m_m21) * (_m_m21) + (((_m_m01) * (_m_m01) + (_m_m11 * _m_m11))));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _m_m22 = m.m22();
        double _m_m02 = m.m02();
        double _m_m12 = m.m12();
        double _t13 = ((_m_m22) * (_m_m22) + (((_m_m02) * (_m_m02) + (_m_m12 * _m_m12))));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _m_m20 = m.m20();
        double _m_m00 = m.m00();
        double _m_m10 = m.m10();
        double _t14 = ((_m_m20) * (_m_m20) + (((_m_m00) * (_m_m00) + (_m_m10 * _m_m10))));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        double _sfx8 = java.lang.Math.sqrt(_t12);
        double _t15 = 1.0 / _sfx8;
        return makeFromMatrix_s4d24221f_1_mulAdd(m, _t13, -_m_m11, -_m_m22, _t18, _m_m10 * _t17, _m_m20 * _t17, _m_m00 * _t17, m.m03(), m.m13(), m.m23(), _sfx8, _t15, _m_m21 * _t15);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_s4d24221f_1_fma(Double3x4 m, double _t13, double _t0, double _t1, double _t18, double _t19, double _t22, double _t27, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t24) {
        double _m_m11 = m.m11();
        double _t25 = _m_m11 * _t15;
        double _sfx9 = java.lang.Math.sqrt(_t13);
        double _t16 = 1.0 / _sfx9;
        double _m_m22 = m.m22();
        double _t20 = _m_m22 * _t16;
        double _m_m12 = m.m12();
        double _t21 = _m_m12 * _t16;
        double _t48, _t49, _t50, _sfx7;
        double _m_m01 = m.m01();
        double _m_m02 = m.m02();
        if (java.lang.Math.fma(-java.lang.Math.fma(_t19, _t20, -(_t21 * _t22)), _m_m01 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t19, _t24, -(_t25 * _t22)), _m_m02 * _t16, java.lang.Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27)) < 0.0) {
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
        double _t52 = 1.0 + _t48;
        return makeFromMatrix_s4d24221f_2_fma(m, _t0, _t1, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, java.lang.Math.fma(_m_m12, _t16, _t24), java.lang.Math.fma(m.m21(), _t15, -_t21), _t48, _sfx7, _t52, 1.0 - _t48, java.lang.Math.fma(_m_m01, _t15, _t49), java.lang.Math.fma(_m_m02, _t16, _t50), java.lang.Math.fma(_m_m02, _t16, -_t50), java.lang.Math.fma(-_m_m01, _t15, _t49), java.lang.Math.fma(_m_m11, _t15, java.lang.Math.fma(_m_m22, _t16, _t52)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_s4d24221f_1_mulAdd(Double3x4 m, double _t13, double _t0, double _t1, double _t18, double _t19, double _t22, double _t27, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t24) {
        double _m_m11 = m.m11();
        double _t25 = _m_m11 * _t15;
        double _sfx9 = java.lang.Math.sqrt(_t13);
        double _t16 = 1.0 / _sfx9;
        double _m_m22 = m.m22();
        double _t20 = _m_m22 * _t16;
        double _m_m12 = m.m12();
        double _t21 = _m_m12 * _t16;
        double _t48, _t49, _t50, _sfx7;
        double _m_m02 = m.m02();
        double _m_m01 = m.m01();
        if (((((((_t19) * (_t24) - (_t25 * _t22))) * (_m_m02 * _t16) + (((_t25) * (_t20) - (_t21 * _t24)) * _t27))) - ((_t19) * (_t20) - (_t21 * _t22)) * (_m_m01 * _t15)) < 0.0) {
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
        double _t52 = 1.0 + _t48;
        return makeFromMatrix_s4d24221f_2_mulAdd(m, _t0, _t1, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, ((_m_m12) * (_t16) + (_t24)), ((m.m21()) * (_t15) - (_t21)), _t48, _sfx7, _t52, 1.0 - _t48, ((_m_m01) * (_t15) + (_t49)), ((_m_m02) * (_t16) + (_t50)), ((_m_m02) * (_t16) - (_t50)), ((_t49) - (_m_m01) * (_t15)), ((_m_m11) * (_t15) + (((_m_m22) * (_t16) + (_t52)))));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_s4d24221f_2_fma(Double3x4 m, double _t0, double _t1, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t25, double _sfx9, double _t16, double _t20, double _t32, double _t36, double _t48, double _sfx7, double _t52, double _t53, double _t55, double _t56, double _t57, double _t58, double _t64) {
        double _t66 = java.lang.Math.fma(m.m11(), _t15, java.lang.Math.fma(_t1, _t16, _t53));
        double _t67 = java.lang.Math.fma(m.m22(), _t16, java.lang.Math.fma(_t0, _t15, _t53));
        double _t68 = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        return makeFromMatrix_s4d24221f_3_fma(m, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, _t32, _t36, _t48, _sfx7, _t55, _t56, _t57, _t58, _t64, 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), _t66, _t67, _t68, 0.5 * (1.0 / java.lang.Math.sqrt(_t66)), 0.5 * (1.0 / java.lang.Math.sqrt(_t67)), 0.5 * (1.0 / java.lang.Math.sqrt(_t68)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_s4d24221f_2_mulAdd(Double3x4 m, double _t0, double _t1, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t25, double _sfx9, double _t16, double _t20, double _t32, double _t36, double _t48, double _sfx7, double _t52, double _t53, double _t55, double _t56, double _t57, double _t58, double _t64) {
        double _t66 = ((m.m11()) * (_t15) + (((_t1) * (_t16) + (_t53))));
        double _t67 = ((m.m22()) * (_t16) + (((_t0) * (_t15) + (_t53))));
        double _t68 = ((_t0) * (_t15) + (((_t1) * (_t16) + (_t52))));
        return makeFromMatrix_s4d24221f_3_mulAdd(m, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, _t32, _t36, _t48, _sfx7, _t55, _t56, _t57, _t58, _t64, 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), _t66, _t67, _t68, 0.5 * (1.0 / java.lang.Math.sqrt(_t66)), 0.5 * (1.0 / java.lang.Math.sqrt(_t67)), 0.5 * (1.0 / java.lang.Math.sqrt(_t68)));
    }

    /** Piece 4 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_s4d24221f_3_fma(Double3x4 m, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t25, double _sfx9, double _t16, double _t20, double _t32, double _t36, double _t48, double _sfx7, double _t55, double _t56, double _t57, double _t58, double _t64, double _sp0, double _t66, double _t67, double _t68, double _sp1, double _sp2, double _sp3) {
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (java.lang.Math.fma(m.m11(), _t15, java.lang.Math.fma(m.m22(), _t16, _t48)) > 0.0) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > Math.max(_t25, _t20)) {
                _sfx3 = 0.5 * java.lang.Math.sqrt(_t68);
                _sfx4 = _sp3 * _t55;
                _sfx5 = _sp3 * _t56;
                _sfx6 = _sp3 * _t36;
            } else {
                if (_t25 > _t20) {
                    _sfx3 = _sp1 * _t55;
                    _sfx4 = 0.5 * java.lang.Math.sqrt(_t66);
                    _sfx5 = _sp1 * _t32;
                    _sfx6 = _sp1 * _t57;
                } else {
                    _sfx3 = _sp2 * _t56;
                    _sfx4 = _sp2 * _t32;
                    _sfx5 = 0.5 * java.lang.Math.sqrt(_t67);
                    _sfx6 = _sp2 * _t58;
                }
            }
        }
        return new DoubleTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Piece 4 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_s4d24221f_3_mulAdd(Double3x4 m, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t25, double _sfx9, double _t16, double _t20, double _t32, double _t36, double _t48, double _sfx7, double _t55, double _t56, double _t57, double _t58, double _t64, double _sp0, double _t66, double _t67, double _t68, double _sp1, double _sp2, double _sp3) {
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (((m.m11()) * (_t15) + (((m.m22()) * (_t16) + (_t48)))) > 0.0) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > Math.max(_t25, _t20)) {
                _sfx3 = 0.5 * java.lang.Math.sqrt(_t68);
                _sfx4 = _sp3 * _t55;
                _sfx5 = _sp3 * _t56;
                _sfx6 = _sp3 * _t36;
            } else {
                if (_t25 > _t20) {
                    _sfx3 = _sp1 * _t55;
                    _sfx4 = 0.5 * java.lang.Math.sqrt(_t66);
                    _sfx5 = _sp1 * _t32;
                    _sfx6 = _sp1 * _t57;
                } else {
                    _sfx3 = _sp2 * _t56;
                    _sfx4 = _sp2 * _t32;
                    _sfx5 = 0.5 * java.lang.Math.sqrt(_t67);
                    _sfx6 = _sp2 * _t58;
                }
            }
        }
        return new DoubleTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail_fma(double _t30, double _t20, double _t31, double _t17, double _t15, double _t16, double _t32, double _t13, double _t12, double _t14, double _t35, double _t36, double _t29, double _t2, double _t27, double _t28, Double3x4 m, double _t0, double _t1) {
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t44 = java.lang.Math.abs(_t35);
        double _t45 = java.lang.Math.abs(_t36);
        double _t48 = java.lang.Math.abs(_t39);
        double _t49 = java.lang.Math.abs(_t40);
        double _t52 = java.lang.Math.abs(_t42);
        double _t53 = java.lang.Math.abs(_t41);
        double _t75, _t78;
        if (_t44 < _t45) {
            _t75 = _t37;
            _t78 = 0.0;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
        }
        double _t76, _t79;
        if (_t48 < _t49) {
            _t76 = _t38;
            _t79 = 0.0;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
        }
        double _t77, _t80;
        if (_t52 < _t53) {
            _t77 = _t43;
            _t80 = 0.0;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
        }
        return makeFromMatrix_degenerate_s91e96b_tail2_fma(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, m, _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail_mulAdd(double _t30, double _t20, double _t31, double _t17, double _t15, double _t16, double _t32, double _t13, double _t12, double _t14, double _t35, double _t36, double _t29, double _t2, double _t27, double _t28, Double3x4 m, double _t0, double _t1) {
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t44 = java.lang.Math.abs(_t35);
        double _t45 = java.lang.Math.abs(_t36);
        double _t48 = java.lang.Math.abs(_t39);
        double _t49 = java.lang.Math.abs(_t40);
        double _t52 = java.lang.Math.abs(_t42);
        double _t53 = java.lang.Math.abs(_t41);
        double _t75, _t78;
        if (_t44 < _t45) {
            _t75 = _t37;
            _t78 = 0.0;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
        }
        double _t76, _t79;
        if (_t48 < _t49) {
            _t76 = _t38;
            _t79 = 0.0;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
        }
        double _t77, _t80;
        if (_t52 < _t53) {
            _t77 = _t43;
            _t80 = 0.0;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
        }
        return makeFromMatrix_degenerate_s91e96b_tail2_mulAdd(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, m, _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail2_fma(double _t44, double _t45, double _t36, double _t35, double _t48, double _t49, double _t40, double _t39, double _t52, double _t53, double _t41, double _t42, double _t78, double _t75, double _t79, double _t76, double _t80, double _t77, double _t27, double _t28, double _t29, double _t38, double _t37, double _t43, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t90 = _t44 < _t45 ? -_t36 : _t35;
        double _t91 = _t48 < _t49 ? -_t40 : _t39;
        double _t92 = _t52 < _t53 ? -_t41 : _t42;
        double _t102 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t78, _t78, java.lang.Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t92, _t92, _t77 * _t77))));
        return makeFromMatrix_degenerate_s91e96b_tail3_fma(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail2_mulAdd(double _t44, double _t45, double _t36, double _t35, double _t48, double _t49, double _t40, double _t39, double _t52, double _t53, double _t41, double _t42, double _t78, double _t75, double _t79, double _t76, double _t80, double _t77, double _t27, double _t28, double _t29, double _t38, double _t37, double _t43, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t90 = _t44 < _t45 ? -_t36 : _t35;
        double _t91 = _t48 < _t49 ? -_t40 : _t39;
        double _t92 = _t52 < _t53 ? -_t41 : _t42;
        double _t102 = (1.0 / java.lang.Math.sqrt(((_t78) * (_t78) + (((_t90) * (_t90) + (_t75 * _t75))))));
        double _t103 = (1.0 / java.lang.Math.sqrt(((_t79) * (_t79) + (((_t91) * (_t91) + (_t76 * _t76))))));
        double _t104 = (1.0 / java.lang.Math.sqrt(((_t80) * (_t80) + (((_t92) * (_t92) + (_t77 * _t77))))));
        return makeFromMatrix_degenerate_s91e96b_tail3_mulAdd(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail3_fma(double _t27, double _t28, double _t29, double _t105, double _t38, double _t108, double _t39, double _t117, double _t35, double _t37, double _t41, double _t118, double _t36, double _t109, double _t42, double _t119, double _t106, double _t40, double _t43, double _t107, double _t110, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t168, _t169;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t169 = 0.0;
                } else {
                    _t168 = _t105;
                    _t169 = java.lang.Math.fma(_t35, _t105, -(_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0 ? java.lang.Math.fma(_t38, _t108, -_t39 * _t117) : java.lang.Math.fma(_t35, _t38, _t37 * -_t39);
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t118 : java.lang.Math.fma(_t35, _t41, -(_t36 * _t42)) : _t38;
        }
        return makeFromMatrix_degenerate_s91e96b_tail4_fma(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail3_mulAdd(double _t27, double _t28, double _t29, double _t105, double _t38, double _t108, double _t39, double _t117, double _t35, double _t37, double _t41, double _t118, double _t36, double _t109, double _t42, double _t119, double _t106, double _t40, double _t43, double _t107, double _t110, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t168, _t169;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t169 = 0.0;
                } else {
                    _t168 = _t105;
                    _t169 = ((_t35) * (_t105) - (_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0 ? ((_t38) * (_t108) - (_t39 * _t117)) : ((_t35) * (_t38) - (_t37 * _t39));
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t118 : ((_t35) * (_t41) - (_t36 * _t42)) : _t38;
        }
        return makeFromMatrix_degenerate_s91e96b_tail4_mulAdd(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail4_fma(double _t27, double _t28, double _t29, double _t119, double _t39, double _t106, double _t40, double _t108, double _t36, double _t35, double _t43, double _t107, double _t37, double _t109, double _t42, double _t41, double _t118, double _t38, double _t117, double _t110, double _t105, double _t169, double _t168, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t170, _t171;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t170 = 1.0;
                    _t171 = 0.0;
                } else {
                    _t170 = _t119;
                    _t171 = java.lang.Math.fma(_t37, _t109, -_t35 * _t119);
                }
            } else {
                _t170 = _t29 <= 0.0 ? java.lang.Math.fma(_t39, _t106, -(_t40 * _t108)) : java.lang.Math.fma(_t36, _t39, -_t35 * _t40);
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t107 : java.lang.Math.fma(_t37, _t42, -_t35 * _t43) : _t40;
        }
        return makeFromMatrix_degenerate_s91e96b_tail5_fma(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail4_mulAdd(double _t27, double _t28, double _t29, double _t119, double _t39, double _t106, double _t40, double _t108, double _t36, double _t35, double _t43, double _t107, double _t37, double _t109, double _t42, double _t41, double _t118, double _t38, double _t117, double _t110, double _t105, double _t169, double _t168, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t170, _t171;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t170 = 1.0;
                    _t171 = 0.0;
                } else {
                    _t170 = _t119;
                    _t171 = ((_t37) * (_t109) - (_t35 * _t119));
                }
            } else {
                _t170 = _t29 <= 0.0 ? ((_t39) * (_t106) - (_t40 * _t108)) : ((_t36) * (_t39) - (_t35 * _t40));
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t107 : ((_t37) * (_t42) - (_t35 * _t43)) : _t40;
        }
        return makeFromMatrix_degenerate_s91e96b_tail5_mulAdd(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail5_fma(double _t29, double _t27, double _t28, double _t108, double _t41, double _t118, double _t43, double _t107, double _t38, double _t40, double _t35, double _t109, double _t117, double _t106, double _t37, double _t36, double _t42, double _t110, double _t119, double _t105, double _t39, double _t169, double _t170, double _t168, double _t171, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t172, _t173;
        if (_t29 <= 0.0) {
            if (_t27 <= 0.0) {
                if (_t28 <= 0.0) {
                    _t172 = 0.0;
                    _t173 = 0.0;
                } else {
                    _t172 = _t108;
                    _t173 = java.lang.Math.fma(_t40, _t117, -(_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0 ? java.lang.Math.fma(_t41, _t118, -(_t43 * _t107)) : java.lang.Math.fma(_t41, _t38, -(_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0 ? _t28 <= 0.0 ? _t109 : java.lang.Math.fma(_t37, _t40, -(_t36 * _t38)) : _t42;
        }
        return makeFromMatrix_degenerate_s91e96b_tail6_fma(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail5_mulAdd(double _t29, double _t27, double _t28, double _t108, double _t41, double _t118, double _t43, double _t107, double _t38, double _t40, double _t35, double _t109, double _t117, double _t106, double _t37, double _t36, double _t42, double _t110, double _t119, double _t105, double _t39, double _t169, double _t170, double _t168, double _t171, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t172, _t173;
        if (_t29 <= 0.0) {
            if (_t27 <= 0.0) {
                if (_t28 <= 0.0) {
                    _t172 = 0.0;
                    _t173 = 0.0;
                } else {
                    _t172 = _t108;
                    _t173 = ((_t40) * (_t117) - (_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0 ? ((_t41) * (_t118) - (_t43 * _t107)) : ((_t41) * (_t38) - (_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0 ? _t28 <= 0.0 ? _t109 : ((_t37) * (_t40) - (_t36 * _t38)) : _t42;
        }
        return makeFromMatrix_degenerate_s91e96b_tail6_mulAdd(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail6_fma(double _t28, double _t29, double _t27, double _t110, double _t36, double _t119, double _t37, double _t105, double _t43, double _t41, double _t39, double _t117, double _t42, double _t107, double _t40, double _t106, double _t118, double _t38, double _t173, double _t169, double _t170, double _t168, double _t171, double _t172, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t174, _t175;
        if (_t28 <= 0.0) {
            if (_t29 <= 0.0) {
                if (_t27 <= 0.0) {
                    _t174 = 1.0;
                    _t175 = 0.0;
                } else {
                    _t174 = _t110;
                    _t175 = java.lang.Math.fma(_t42, _t107, -(_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0 ? java.lang.Math.fma(_t36, _t119, -(_t37 * _t105)) : java.lang.Math.fma(_t36, _t43, -(_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t117 : java.lang.Math.fma(_t42, _t40, -(_t41 * _t39)) : _t37;
        }
        return makeFromMatrix_degenerate_s91e96b_tail7_fma(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail6_mulAdd(double _t28, double _t29, double _t27, double _t110, double _t36, double _t119, double _t37, double _t105, double _t43, double _t41, double _t39, double _t117, double _t42, double _t107, double _t40, double _t106, double _t118, double _t38, double _t173, double _t169, double _t170, double _t168, double _t171, double _t172, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t174, _t175;
        if (_t28 <= 0.0) {
            if (_t29 <= 0.0) {
                if (_t27 <= 0.0) {
                    _t174 = 1.0;
                    _t175 = 0.0;
                } else {
                    _t174 = _t110;
                    _t175 = ((_t42) * (_t107) - (_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0 ? ((_t36) * (_t119) - (_t37 * _t105)) : ((_t36) * (_t43) - (_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t117 : ((_t42) * (_t40) - (_t41 * _t39)) : _t37;
        }
        return makeFromMatrix_degenerate_s91e96b_tail7_mulAdd(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail7_fma(double _t29, double _t27, double _t28, double _t106, double _t43, double _t110, double _t42, double _t118, double _t39, double _t38, double _t36, double _t173, double _t169, double _t170, double _t174, double _t168, double _t171, double _t172, double _t175, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t176 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t28 <= 0.0 ? 1.0 : _t106 : _t28 <= 0.0 ? java.lang.Math.fma(_t43, _t110, -_t42 * _t118) : java.lang.Math.fma(_t43, _t39, -_t42 * _t38) : _t36;
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
        return makeFromMatrix_degenerate_s91e96b_tail8(_t199, _t171, _t198, _t168, _t197, _t170, _t174, m, _t173 - _t169, Math.max(_t170, _t174), _t198 + _t168, _t173 + _t169, _t196, _t56, _t27, _t0, _t28, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail7_mulAdd(double _t29, double _t27, double _t28, double _t106, double _t43, double _t110, double _t42, double _t118, double _t39, double _t38, double _t36, double _t173, double _t169, double _t170, double _t174, double _t168, double _t171, double _t172, double _t175, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t176 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t28 <= 0.0 ? 1.0 : _t106 : _t28 <= 0.0 ? ((_t43) * (_t110) - (_t42 * _t118)) : ((_t43) * (_t39) - (_t42 * _t38)) : _t36;
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
        return makeFromMatrix_degenerate_s91e96b_tail8(_t199, _t171, _t198, _t168, _t197, _t170, _t174, m, _t173 - _t169, Math.max(_t170, _t174), _t198 + _t168, _t173 + _t169, _t196, _t56, _t27, _t0, _t28, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail8(double _t199, double _t171, double _t198, double _t168, double _t197, double _t170, double _t174, Double3x4 m, double _t185, double _t186, double _t202, double _t187, double _t196, double _t56, double _t27, double _t0, double _t28, double _t1) {
        double _t203 = _t199 + _t171;
        double _t209 = _t197 + _t170 + _t174;
        double _t210 = 1.0 + _t209;
        double _t211 = 1.0 + _t197 - _t170 - _t174;
        double _t212 = 1.0 + _t170 - _t197 - _t174;
        double _t213 = 1.0 + _t174 - _t197 - _t170;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t213));
        double _sfx0 = m.m03();
        double _sfx1 = m.m13();
        double _sfx2 = m.m23();
        double _sfx3 = _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        return makeFromMatrix_degenerate_s91e5aa_tail9(_t209, _sp0, _t171 - _t199, _t197, _t186, 0.5 * (1.0 / java.lang.Math.sqrt(_t211)), _t202, _t170, _t174, _t212, _sp2, _t187, _t198 - _t168, _t203, _sp1, _t213, _t210, _t185, _t196, _t56, _t27, _t0, _t28, _t1, _sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static DoubleTransform makeFromMatrix_degenerate_fma(Double3x4 m) {
        double _m_m01 = m.m01();
        double _m_m11 = m.m11();
        double _m_m21 = m.m21();
        double _t0 = unitScale(_m_m01, _m_m11, _m_m21);
        double _m_m02 = m.m02();
        double _m_m12 = m.m12();
        double _m_m22 = m.m22();
        double _t1 = unitScale(_m_m02, _m_m12, _m_m22);
        double _m_m00 = m.m00();
        double _m_m10 = m.m10();
        double _m_m20 = m.m20();
        double _t2 = unitScale(_m_m00, _m_m10, _m_m20);
        double _t12 = _m_m21 * _t0;
        double _t13 = _m_m01 * _t0;
        double _t14 = _m_m11 * _t0;
        double _t15 = _m_m22 * _t1;
        double _t16 = _m_m02 * _t1;
        double _t17 = _m_m12 * _t1;
        double _t18 = _m_m20 * _t2;
        double _t19 = _m_m00 * _t2;
        double _t20 = _m_m10 * _t2;
        double _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_s91e96b_tail_fma(_t30, _t20, (1.0 / java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0 / java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t27, _t28, m, _t0, _t1);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static DoubleTransform makeFromMatrix_degenerate_mulAdd(Double3x4 m) {
        double _m_m01 = m.m01();
        double _m_m11 = m.m11();
        double _m_m21 = m.m21();
        double _t0 = unitScale(_m_m01, _m_m11, _m_m21);
        double _m_m02 = m.m02();
        double _m_m12 = m.m12();
        double _m_m22 = m.m22();
        double _t1 = unitScale(_m_m02, _m_m12, _m_m22);
        double _m_m00 = m.m00();
        double _m_m10 = m.m10();
        double _m_m20 = m.m20();
        double _t2 = unitScale(_m_m00, _m_m10, _m_m20);
        double _t12 = _m_m21 * _t0;
        double _t13 = _m_m01 * _t0;
        double _t14 = _m_m11 * _t0;
        double _t15 = _m_m22 * _t1;
        double _t16 = _m_m02 * _t1;
        double _t17 = _m_m12 * _t1;
        double _t18 = _m_m20 * _t2;
        double _t19 = _m_m00 * _t2;
        double _t20 = _m_m10 * _t2;
        double _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        double _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_s91e96b_tail_mulAdd(_t30, _t20, (1.0 / java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0 / java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t27, _t28, m, _t0, _t1);
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
    public static DoubleTransform makeFromMatrix(Double4x4 m) {
        if (Math.useFma()) return makeFromMatrix_fma(m);
        return makeFromMatrix_mulAdd(m);
    }

    /** {@code makeFromMatrix} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleTransform makeFromMatrix_fma(Double4x4 m) {
        double _m_m21 = m.m21();
        double _m_m01 = m.m01();
        double _m_m11 = m.m11();
        double _t12 = java.lang.Math.fma(_m_m21, _m_m21, java.lang.Math.fma(_m_m01, _m_m01, _m_m11 * _m_m11));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _m_m22 = m.m22();
        double _m_m02 = m.m02();
        double _m_m12 = m.m12();
        double _t13 = java.lang.Math.fma(_m_m22, _m_m22, java.lang.Math.fma(_m_m02, _m_m02, _m_m12 * _m_m12));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _m_m20 = m.m20();
        double _m_m00 = m.m00();
        double _m_m10 = m.m10();
        double _t14 = java.lang.Math.fma(_m_m20, _m_m20, java.lang.Math.fma(_m_m00, _m_m00, _m_m10 * _m_m10));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_fma(m);
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        double _sfx8 = java.lang.Math.sqrt(_t12);
        double _t15 = 1.0 / _sfx8;
        return makeFromMatrix_sf93a4716_1_fma(m, _t13, -_m_m11, -_m_m22, _t18, _m_m10 * _t17, _m_m20 * _t17, _m_m00 * _t17, m.m03(), m.m13(), m.m23(), _sfx8, _t15, _m_m21 * _t15);
    }

    /** {@code makeFromMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleTransform makeFromMatrix_mulAdd(Double4x4 m) {
        double _m_m21 = m.m21();
        double _m_m01 = m.m01();
        double _m_m11 = m.m11();
        double _t12 = ((_m_m21) * (_m_m21) + (((_m_m01) * (_m_m01) + (_m_m11 * _m_m11))));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _m_m22 = m.m22();
        double _m_m02 = m.m02();
        double _m_m12 = m.m12();
        double _t13 = ((_m_m22) * (_m_m22) + (((_m_m02) * (_m_m02) + (_m_m12 * _m_m12))));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _m_m20 = m.m20();
        double _m_m00 = m.m00();
        double _m_m10 = m.m10();
        double _t14 = ((_m_m20) * (_m_m20) + (((_m_m00) * (_m_m00) + (_m_m10 * _m_m10))));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate_mulAdd(m);
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        double _sfx8 = java.lang.Math.sqrt(_t12);
        double _t15 = 1.0 / _sfx8;
        return makeFromMatrix_sf93a4716_1_mulAdd(m, _t13, -_m_m11, -_m_m22, _t18, _m_m10 * _t17, _m_m20 * _t17, _m_m00 * _t17, m.m03(), m.m13(), m.m23(), _sfx8, _t15, _m_m21 * _t15);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_sf93a4716_1_fma(Double4x4 m, double _t13, double _t0, double _t1, double _t18, double _t19, double _t22, double _t27, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t24) {
        double _m_m11 = m.m11();
        double _t25 = _m_m11 * _t15;
        double _sfx9 = java.lang.Math.sqrt(_t13);
        double _t16 = 1.0 / _sfx9;
        double _m_m22 = m.m22();
        double _t20 = _m_m22 * _t16;
        double _m_m12 = m.m12();
        double _t21 = _m_m12 * _t16;
        double _t48, _t49, _t50, _sfx7;
        double _m_m01 = m.m01();
        double _m_m02 = m.m02();
        if (java.lang.Math.fma(-java.lang.Math.fma(_t19, _t20, -(_t21 * _t22)), _m_m01 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t19, _t24, -(_t25 * _t22)), _m_m02 * _t16, java.lang.Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27)) < 0.0) {
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
        double _t52 = 1.0 + _t48;
        return makeFromMatrix_sf93a4716_2_fma(m, _t0, _t1, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, java.lang.Math.fma(_m_m12, _t16, _t24), java.lang.Math.fma(m.m21(), _t15, -_t21), _t48, _sfx7, _t52, 1.0 - _t48, java.lang.Math.fma(_m_m01, _t15, _t49), java.lang.Math.fma(_m_m02, _t16, _t50), java.lang.Math.fma(_m_m02, _t16, -_t50), java.lang.Math.fma(-_m_m01, _t15, _t49), java.lang.Math.fma(_m_m11, _t15, java.lang.Math.fma(_m_m22, _t16, _t52)));
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_sf93a4716_1_mulAdd(Double4x4 m, double _t13, double _t0, double _t1, double _t18, double _t19, double _t22, double _t27, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t24) {
        double _m_m11 = m.m11();
        double _t25 = _m_m11 * _t15;
        double _sfx9 = java.lang.Math.sqrt(_t13);
        double _t16 = 1.0 / _sfx9;
        double _m_m22 = m.m22();
        double _t20 = _m_m22 * _t16;
        double _m_m12 = m.m12();
        double _t21 = _m_m12 * _t16;
        double _t48, _t49, _t50, _sfx7;
        double _m_m02 = m.m02();
        double _m_m01 = m.m01();
        if (((((((_t19) * (_t24) - (_t25 * _t22))) * (_m_m02 * _t16) + (((_t25) * (_t20) - (_t21 * _t24)) * _t27))) - ((_t19) * (_t20) - (_t21 * _t22)) * (_m_m01 * _t15)) < 0.0) {
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
        double _t52 = 1.0 + _t48;
        return makeFromMatrix_sf93a4716_2_mulAdd(m, _t0, _t1, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, ((_m_m12) * (_t16) + (_t24)), ((m.m21()) * (_t15) - (_t21)), _t48, _sfx7, _t52, 1.0 - _t48, ((_m_m01) * (_t15) + (_t49)), ((_m_m02) * (_t16) + (_t50)), ((_m_m02) * (_t16) - (_t50)), ((_t49) - (_m_m01) * (_t15)), ((_m_m11) * (_t15) + (((_m_m22) * (_t16) + (_t52)))));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_sf93a4716_2_fma(Double4x4 m, double _t0, double _t1, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t25, double _sfx9, double _t16, double _t20, double _t32, double _t36, double _t48, double _sfx7, double _t52, double _t53, double _t55, double _t56, double _t57, double _t58, double _t64) {
        double _t66 = java.lang.Math.fma(m.m11(), _t15, java.lang.Math.fma(_t1, _t16, _t53));
        double _t67 = java.lang.Math.fma(m.m22(), _t16, java.lang.Math.fma(_t0, _t15, _t53));
        double _t68 = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        return makeFromMatrix_sf93a4716_3_fma(m, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, _t32, _t36, _t48, _sfx7, _t55, _t56, _t57, _t58, _t64, 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), _t66, _t67, _t68, 0.5 * (1.0 / java.lang.Math.sqrt(_t66)), 0.5 * (1.0 / java.lang.Math.sqrt(_t67)), 0.5 * (1.0 / java.lang.Math.sqrt(_t68)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_sf93a4716_2_mulAdd(Double4x4 m, double _t0, double _t1, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t25, double _sfx9, double _t16, double _t20, double _t32, double _t36, double _t48, double _sfx7, double _t52, double _t53, double _t55, double _t56, double _t57, double _t58, double _t64) {
        double _t66 = ((m.m11()) * (_t15) + (((_t1) * (_t16) + (_t53))));
        double _t67 = ((m.m22()) * (_t16) + (((_t0) * (_t15) + (_t53))));
        double _t68 = ((_t0) * (_t15) + (((_t1) * (_t16) + (_t52))));
        return makeFromMatrix_sf93a4716_3_mulAdd(m, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, _t32, _t36, _t48, _sfx7, _t55, _t56, _t57, _t58, _t64, 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), _t66, _t67, _t68, 0.5 * (1.0 / java.lang.Math.sqrt(_t66)), 0.5 * (1.0 / java.lang.Math.sqrt(_t67)), 0.5 * (1.0 / java.lang.Math.sqrt(_t68)));
    }

    /** Piece 4 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_sf93a4716_3_fma(Double4x4 m, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t25, double _sfx9, double _t16, double _t20, double _t32, double _t36, double _t48, double _sfx7, double _t55, double _t56, double _t57, double _t58, double _t64, double _sp0, double _t66, double _t67, double _t68, double _sp1, double _sp2, double _sp3) {
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (java.lang.Math.fma(m.m11(), _t15, java.lang.Math.fma(m.m22(), _t16, _t48)) > 0.0) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > Math.max(_t25, _t20)) {
                _sfx3 = 0.5 * java.lang.Math.sqrt(_t68);
                _sfx4 = _sp3 * _t55;
                _sfx5 = _sp3 * _t56;
                _sfx6 = _sp3 * _t36;
            } else {
                if (_t25 > _t20) {
                    _sfx3 = _sp1 * _t55;
                    _sfx4 = 0.5 * java.lang.Math.sqrt(_t66);
                    _sfx5 = _sp1 * _t32;
                    _sfx6 = _sp1 * _t57;
                } else {
                    _sfx3 = _sp2 * _t56;
                    _sfx4 = _sp2 * _t32;
                    _sfx5 = 0.5 * java.lang.Math.sqrt(_t67);
                    _sfx6 = _sp2 * _t58;
                }
            }
        }
        return new DoubleTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Piece 4 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_sf93a4716_3_mulAdd(Double4x4 m, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t25, double _sfx9, double _t16, double _t20, double _t32, double _t36, double _t48, double _sfx7, double _t55, double _t56, double _t57, double _t58, double _t64, double _sp0, double _t66, double _t67, double _t68, double _sp1, double _sp2, double _sp3) {
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (((m.m11()) * (_t15) + (((m.m22()) * (_t16) + (_t48)))) > 0.0) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > Math.max(_t25, _t20)) {
                _sfx3 = 0.5 * java.lang.Math.sqrt(_t68);
                _sfx4 = _sp3 * _t55;
                _sfx5 = _sp3 * _t56;
                _sfx6 = _sp3 * _t36;
            } else {
                if (_t25 > _t20) {
                    _sfx3 = _sp1 * _t55;
                    _sfx4 = 0.5 * java.lang.Math.sqrt(_t66);
                    _sfx5 = _sp1 * _t32;
                    _sfx6 = _sp1 * _t57;
                } else {
                    _sfx3 = _sp2 * _t56;
                    _sfx4 = _sp2 * _t32;
                    _sfx5 = 0.5 * java.lang.Math.sqrt(_t67);
                    _sfx6 = _sp2 * _t58;
                }
            }
        }
        return new DoubleTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail_fma(double _t30, double _t20, double _t31, double _t17, double _t15, double _t16, double _t32, double _t13, double _t12, double _t14, double _t35, double _t36, double _t29, double _t2, double _t27, double _t28, Double4x4 m, double _t0, double _t1) {
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t44 = java.lang.Math.abs(_t35);
        double _t45 = java.lang.Math.abs(_t36);
        double _t48 = java.lang.Math.abs(_t39);
        double _t49 = java.lang.Math.abs(_t40);
        double _t52 = java.lang.Math.abs(_t42);
        double _t53 = java.lang.Math.abs(_t41);
        double _t75, _t78;
        if (_t44 < _t45) {
            _t75 = _t37;
            _t78 = 0.0;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
        }
        double _t76, _t79;
        if (_t48 < _t49) {
            _t76 = _t38;
            _t79 = 0.0;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
        }
        double _t77, _t80;
        if (_t52 < _t53) {
            _t77 = _t43;
            _t80 = 0.0;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
        }
        return makeFromMatrix_degenerate_sa000ec_tail2_fma(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, m, _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail_mulAdd(double _t30, double _t20, double _t31, double _t17, double _t15, double _t16, double _t32, double _t13, double _t12, double _t14, double _t35, double _t36, double _t29, double _t2, double _t27, double _t28, Double4x4 m, double _t0, double _t1) {
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t44 = java.lang.Math.abs(_t35);
        double _t45 = java.lang.Math.abs(_t36);
        double _t48 = java.lang.Math.abs(_t39);
        double _t49 = java.lang.Math.abs(_t40);
        double _t52 = java.lang.Math.abs(_t42);
        double _t53 = java.lang.Math.abs(_t41);
        double _t75, _t78;
        if (_t44 < _t45) {
            _t75 = _t37;
            _t78 = 0.0;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
        }
        double _t76, _t79;
        if (_t48 < _t49) {
            _t76 = _t38;
            _t79 = 0.0;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
        }
        double _t77, _t80;
        if (_t52 < _t53) {
            _t77 = _t43;
            _t80 = 0.0;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
        }
        return makeFromMatrix_degenerate_sa000ec_tail2_mulAdd(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, m, _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail2_fma(double _t44, double _t45, double _t36, double _t35, double _t48, double _t49, double _t40, double _t39, double _t52, double _t53, double _t41, double _t42, double _t78, double _t75, double _t79, double _t76, double _t80, double _t77, double _t27, double _t28, double _t29, double _t38, double _t37, double _t43, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t90 = _t44 < _t45 ? -_t36 : _t35;
        double _t91 = _t48 < _t49 ? -_t40 : _t39;
        double _t92 = _t52 < _t53 ? -_t41 : _t42;
        double _t102 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t78, _t78, java.lang.Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t92, _t92, _t77 * _t77))));
        return makeFromMatrix_degenerate_sa000ec_tail3_fma(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail2_mulAdd(double _t44, double _t45, double _t36, double _t35, double _t48, double _t49, double _t40, double _t39, double _t52, double _t53, double _t41, double _t42, double _t78, double _t75, double _t79, double _t76, double _t80, double _t77, double _t27, double _t28, double _t29, double _t38, double _t37, double _t43, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t90 = _t44 < _t45 ? -_t36 : _t35;
        double _t91 = _t48 < _t49 ? -_t40 : _t39;
        double _t92 = _t52 < _t53 ? -_t41 : _t42;
        double _t102 = (1.0 / java.lang.Math.sqrt(((_t78) * (_t78) + (((_t90) * (_t90) + (_t75 * _t75))))));
        double _t103 = (1.0 / java.lang.Math.sqrt(((_t79) * (_t79) + (((_t91) * (_t91) + (_t76 * _t76))))));
        double _t104 = (1.0 / java.lang.Math.sqrt(((_t80) * (_t80) + (((_t92) * (_t92) + (_t77 * _t77))))));
        return makeFromMatrix_degenerate_sa000ec_tail3_mulAdd(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail3_fma(double _t27, double _t28, double _t29, double _t105, double _t38, double _t108, double _t39, double _t117, double _t35, double _t37, double _t41, double _t118, double _t36, double _t109, double _t42, double _t119, double _t106, double _t40, double _t43, double _t107, double _t110, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t168, _t169;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t169 = 0.0;
                } else {
                    _t168 = _t105;
                    _t169 = java.lang.Math.fma(_t35, _t105, -(_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0 ? java.lang.Math.fma(_t38, _t108, -_t39 * _t117) : java.lang.Math.fma(_t35, _t38, _t37 * -_t39);
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t118 : java.lang.Math.fma(_t35, _t41, -(_t36 * _t42)) : _t38;
        }
        return makeFromMatrix_degenerate_sa000ec_tail4_fma(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail3_mulAdd(double _t27, double _t28, double _t29, double _t105, double _t38, double _t108, double _t39, double _t117, double _t35, double _t37, double _t41, double _t118, double _t36, double _t109, double _t42, double _t119, double _t106, double _t40, double _t43, double _t107, double _t110, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t168, _t169;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t169 = 0.0;
                } else {
                    _t168 = _t105;
                    _t169 = ((_t35) * (_t105) - (_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0 ? ((_t38) * (_t108) - (_t39 * _t117)) : ((_t35) * (_t38) - (_t37 * _t39));
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t118 : ((_t35) * (_t41) - (_t36 * _t42)) : _t38;
        }
        return makeFromMatrix_degenerate_sa000ec_tail4_mulAdd(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail4_fma(double _t27, double _t28, double _t29, double _t119, double _t39, double _t106, double _t40, double _t108, double _t36, double _t35, double _t43, double _t107, double _t37, double _t109, double _t42, double _t41, double _t118, double _t38, double _t117, double _t110, double _t105, double _t169, double _t168, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t170, _t171;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t170 = 1.0;
                    _t171 = 0.0;
                } else {
                    _t170 = _t119;
                    _t171 = java.lang.Math.fma(_t37, _t109, -_t35 * _t119);
                }
            } else {
                _t170 = _t29 <= 0.0 ? java.lang.Math.fma(_t39, _t106, -(_t40 * _t108)) : java.lang.Math.fma(_t36, _t39, -_t35 * _t40);
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t107 : java.lang.Math.fma(_t37, _t42, -_t35 * _t43) : _t40;
        }
        return makeFromMatrix_degenerate_sa000ec_tail5_fma(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail4_mulAdd(double _t27, double _t28, double _t29, double _t119, double _t39, double _t106, double _t40, double _t108, double _t36, double _t35, double _t43, double _t107, double _t37, double _t109, double _t42, double _t41, double _t118, double _t38, double _t117, double _t110, double _t105, double _t169, double _t168, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t170, _t171;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t170 = 1.0;
                    _t171 = 0.0;
                } else {
                    _t170 = _t119;
                    _t171 = ((_t37) * (_t109) - (_t35 * _t119));
                }
            } else {
                _t170 = _t29 <= 0.0 ? ((_t39) * (_t106) - (_t40 * _t108)) : ((_t36) * (_t39) - (_t35 * _t40));
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t107 : ((_t37) * (_t42) - (_t35 * _t43)) : _t40;
        }
        return makeFromMatrix_degenerate_sa000ec_tail5_mulAdd(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail5_fma(double _t29, double _t27, double _t28, double _t108, double _t41, double _t118, double _t43, double _t107, double _t38, double _t40, double _t35, double _t109, double _t117, double _t106, double _t37, double _t36, double _t42, double _t110, double _t119, double _t105, double _t39, double _t169, double _t170, double _t168, double _t171, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t172, _t173;
        if (_t29 <= 0.0) {
            if (_t27 <= 0.0) {
                if (_t28 <= 0.0) {
                    _t172 = 0.0;
                    _t173 = 0.0;
                } else {
                    _t172 = _t108;
                    _t173 = java.lang.Math.fma(_t40, _t117, -(_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0 ? java.lang.Math.fma(_t41, _t118, -(_t43 * _t107)) : java.lang.Math.fma(_t41, _t38, -(_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0 ? _t28 <= 0.0 ? _t109 : java.lang.Math.fma(_t37, _t40, -(_t36 * _t38)) : _t42;
        }
        return makeFromMatrix_degenerate_sa000ec_tail6_fma(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail5_mulAdd(double _t29, double _t27, double _t28, double _t108, double _t41, double _t118, double _t43, double _t107, double _t38, double _t40, double _t35, double _t109, double _t117, double _t106, double _t37, double _t36, double _t42, double _t110, double _t119, double _t105, double _t39, double _t169, double _t170, double _t168, double _t171, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t172, _t173;
        if (_t29 <= 0.0) {
            if (_t27 <= 0.0) {
                if (_t28 <= 0.0) {
                    _t172 = 0.0;
                    _t173 = 0.0;
                } else {
                    _t172 = _t108;
                    _t173 = ((_t40) * (_t117) - (_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0 ? ((_t41) * (_t118) - (_t43 * _t107)) : ((_t41) * (_t38) - (_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0 ? _t28 <= 0.0 ? _t109 : ((_t37) * (_t40) - (_t36 * _t38)) : _t42;
        }
        return makeFromMatrix_degenerate_sa000ec_tail6_mulAdd(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail6_fma(double _t28, double _t29, double _t27, double _t110, double _t36, double _t119, double _t37, double _t105, double _t43, double _t41, double _t39, double _t117, double _t42, double _t107, double _t40, double _t106, double _t118, double _t38, double _t173, double _t169, double _t170, double _t168, double _t171, double _t172, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t174, _t175;
        if (_t28 <= 0.0) {
            if (_t29 <= 0.0) {
                if (_t27 <= 0.0) {
                    _t174 = 1.0;
                    _t175 = 0.0;
                } else {
                    _t174 = _t110;
                    _t175 = java.lang.Math.fma(_t42, _t107, -(_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0 ? java.lang.Math.fma(_t36, _t119, -(_t37 * _t105)) : java.lang.Math.fma(_t36, _t43, -(_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t117 : java.lang.Math.fma(_t42, _t40, -(_t41 * _t39)) : _t37;
        }
        return makeFromMatrix_degenerate_sa000ec_tail7_fma(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail6_mulAdd(double _t28, double _t29, double _t27, double _t110, double _t36, double _t119, double _t37, double _t105, double _t43, double _t41, double _t39, double _t117, double _t42, double _t107, double _t40, double _t106, double _t118, double _t38, double _t173, double _t169, double _t170, double _t168, double _t171, double _t172, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t174, _t175;
        if (_t28 <= 0.0) {
            if (_t29 <= 0.0) {
                if (_t27 <= 0.0) {
                    _t174 = 1.0;
                    _t175 = 0.0;
                } else {
                    _t174 = _t110;
                    _t175 = ((_t42) * (_t107) - (_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0 ? ((_t36) * (_t119) - (_t37 * _t105)) : ((_t36) * (_t43) - (_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t117 : ((_t42) * (_t40) - (_t41 * _t39)) : _t37;
        }
        return makeFromMatrix_degenerate_sa000ec_tail7_mulAdd(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail7_fma(double _t29, double _t27, double _t28, double _t106, double _t43, double _t110, double _t42, double _t118, double _t39, double _t38, double _t36, double _t173, double _t169, double _t170, double _t174, double _t168, double _t171, double _t172, double _t175, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t176 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t28 <= 0.0 ? 1.0 : _t106 : _t28 <= 0.0 ? java.lang.Math.fma(_t43, _t110, -_t42 * _t118) : java.lang.Math.fma(_t43, _t39, -_t42 * _t38) : _t36;
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
        return makeFromMatrix_degenerate_sa000ec_tail8(_t199, _t171, _t198, _t168, _t197, _t170, _t174, m, _t173 - _t169, Math.max(_t170, _t174), _t198 + _t168, _t173 + _t169, _t196, _t56, _t27, _t0, _t28, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail7_mulAdd(double _t29, double _t27, double _t28, double _t106, double _t43, double _t110, double _t42, double _t118, double _t39, double _t38, double _t36, double _t173, double _t169, double _t170, double _t174, double _t168, double _t171, double _t172, double _t175, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t176 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t28 <= 0.0 ? 1.0 : _t106 : _t28 <= 0.0 ? ((_t43) * (_t110) - (_t42 * _t118)) : ((_t43) * (_t39) - (_t42 * _t38)) : _t36;
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
        return makeFromMatrix_degenerate_sa000ec_tail8(_t199, _t171, _t198, _t168, _t197, _t170, _t174, m, _t173 - _t169, Math.max(_t170, _t174), _t198 + _t168, _t173 + _t169, _t196, _t56, _t27, _t0, _t28, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail8(double _t199, double _t171, double _t198, double _t168, double _t197, double _t170, double _t174, Double4x4 m, double _t185, double _t186, double _t202, double _t187, double _t196, double _t56, double _t27, double _t0, double _t28, double _t1) {
        double _t203 = _t199 + _t171;
        double _t209 = _t197 + _t170 + _t174;
        double _t210 = 1.0 + _t209;
        double _t211 = 1.0 + _t197 - _t170 - _t174;
        double _t212 = 1.0 + _t170 - _t197 - _t174;
        double _t213 = 1.0 + _t174 - _t197 - _t170;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t213));
        double _sfx0 = m.m03();
        double _sfx1 = m.m13();
        double _sfx2 = m.m23();
        double _sfx3 = _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        return makeFromMatrix_degenerate_s91e5aa_tail9(_t209, _sp0, _t171 - _t199, _t197, _t186, 0.5 * (1.0 / java.lang.Math.sqrt(_t211)), _t202, _t170, _t174, _t212, _sp2, _t187, _t198 - _t168, _t203, _sp1, _t213, _t210, _t185, _t196, _t56, _t27, _t0, _t28, _t1, _sfx0, _sfx1, _sfx2, _sfx3);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static DoubleTransform makeFromMatrix_degenerate_fma(Double4x4 m) {
        double _m_m01 = m.m01();
        double _m_m11 = m.m11();
        double _m_m21 = m.m21();
        double _t0 = unitScale(_m_m01, _m_m11, _m_m21);
        double _m_m02 = m.m02();
        double _m_m12 = m.m12();
        double _m_m22 = m.m22();
        double _t1 = unitScale(_m_m02, _m_m12, _m_m22);
        double _m_m00 = m.m00();
        double _m_m10 = m.m10();
        double _m_m20 = m.m20();
        double _t2 = unitScale(_m_m00, _m_m10, _m_m20);
        double _t12 = _m_m21 * _t0;
        double _t13 = _m_m01 * _t0;
        double _t14 = _m_m11 * _t0;
        double _t15 = _m_m22 * _t1;
        double _t16 = _m_m02 * _t1;
        double _t17 = _m_m12 * _t1;
        double _t18 = _m_m20 * _t2;
        double _t19 = _m_m00 * _t2;
        double _t20 = _m_m10 * _t2;
        double _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_sa000ec_tail_fma(_t30, _t20, (1.0 / java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0 / java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t27, _t28, m, _t0, _t1);
    }

    /**
     * Degenerate-input path of {@code makeFromMatrix}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private static DoubleTransform makeFromMatrix_degenerate_mulAdd(Double4x4 m) {
        double _m_m01 = m.m01();
        double _m_m11 = m.m11();
        double _m_m21 = m.m21();
        double _t0 = unitScale(_m_m01, _m_m11, _m_m21);
        double _m_m02 = m.m02();
        double _m_m12 = m.m12();
        double _m_m22 = m.m22();
        double _t1 = unitScale(_m_m02, _m_m12, _m_m22);
        double _m_m00 = m.m00();
        double _m_m10 = m.m10();
        double _m_m20 = m.m20();
        double _t2 = unitScale(_m_m00, _m_m10, _m_m20);
        double _t12 = _m_m21 * _t0;
        double _t13 = _m_m01 * _t0;
        double _t14 = _m_m11 * _t0;
        double _t15 = _m_m22 * _t1;
        double _t16 = _m_m02 * _t1;
        double _t17 = _m_m12 * _t1;
        double _t18 = _m_m20 * _t2;
        double _t19 = _m_m00 * _t2;
        double _t20 = _m_m10 * _t2;
        double _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        double _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_sa000ec_tail_mulAdd(_t30, _t20, (1.0 / java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0 / java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t27, _t28, m, _t0, _t1);
    }

    /**
     * Create the given rigid transform's motion (translation and rotation), with scale = 1.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param r the rigid transform to convert
     * @return the resulting transform
     */
    public static DoubleTransform makeFromRigid(DoubleRigid r) {
        double rTX = r.tX();
        double rTY = r.tY();
        double rTZ = r.tZ();
        double rRX = r.rX();
        double rRY = r.rY();
        double rRZ = r.rZ();
        double rRW = r.rW();
        return new DoubleTransform(rTX, rTY, rTZ, rRX, rRY, rRZ, rRW, 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeFromRigid(double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        return new DoubleTransform(rTX, rTY, rTZ, rRX, rRY, rRZ, rRW, 1.0, 1.0, 1.0);
    }

    /**
     * Convert this transform's rigid motion (rotation and translation) to a unit dual quaternion;
     * the scale is dropped (dual quaternions cannot represent it), returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the resulting dual quaternion
     */
    public DoubleDualQuat toDualQuat() {
        if (!Math.useFma()) return toDualQuat_mulAdd();
        double _t0 = -this.tZ;
        return new DoubleDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5 * java.lang.Math.fma(_t0, this.rY, java.lang.Math.fma(this.tX, this.rW, this.tY * this.rZ)), 0.5 * java.lang.Math.fma(this.tZ, this.rX, java.lang.Math.fma(this.tY, this.rW, -this.tX * this.rZ)), 0.5 * java.lang.Math.fma(this.tZ, this.rW, java.lang.Math.fma(this.tX, this.rY, -this.tY * this.rX)), 0.5 * java.lang.Math.fma(_t0, this.rZ, java.lang.Math.fma(-this.tY, this.rY, -this.tX * this.rX)));
    }

    /** {@code toDualQuat} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleDualQuat toDualQuat_mulAdd() {
        double _t0 = -this.tZ;
        return new DoubleDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5 * ((_t0) * (this.rY) + (((this.tX) * (this.rW) + (this.tY * this.rZ)))), 0.5 * ((this.tZ) * (this.rX) + (((this.tY) * (this.rW) - (this.tX * this.rZ)))), 0.5 * ((this.tZ) * (this.rW) + (((this.tX) * (this.rY) - (this.tY * this.rX)))), 0.5 * ((_t0) * (this.rZ) + (((-this.tY) * (this.rY) - (this.tX * this.rX)))));
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c0_fma(double _t3, double _t0, double _t4, double _t5, double[] _m) {
        _m[0] = java.lang.Math.fma(-java.lang.Math.fma(this.rY, this.rY, _t3), _t0, this.sX);
        _m[4] = java.lang.Math.fma(this.rX, this.rY, _t4) * _t0;
        _m[8] = java.lang.Math.fma(this.rX, this.rZ, -_t5) * _t0;
        _m[12] = 0.0;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c0_mulAdd(double _t3, double _t0, double _t4, double _t5, double[] _m) {
        _m[0] = ((this.sX) - ((this.rY) * (this.rY) + (_t3)) * (_t0));
        _m[4] = ((this.rX) * (this.rY) + (_t4)) * _t0;
        _m[8] = ((this.rX) * (this.rZ) - (_t5)) * _t0;
        _m[12] = 0.0;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c1_fma(double _t4, double _t1, double _t3, double[] _m) {
        _m[1] = java.lang.Math.fma(this.rX, this.rY, -_t4) * _t1;
        _m[5] = java.lang.Math.fma(-java.lang.Math.fma(this.rX, this.rX, _t3), _t1, this.sY);
        _m[9] = java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ) * _t1;
        _m[13] = 0.0;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c1_mulAdd(double _t4, double _t1, double _t3, double[] _m) {
        _m[1] = ((this.rX) * (this.rY) - (_t4)) * _t1;
        _m[5] = ((this.sY) - ((this.rX) * (this.rX) + (_t3)) * (_t1));
        _m[9] = ((this.rX) * (this.rW) + (this.rY * this.rZ)) * _t1;
        _m[13] = 0.0;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c2_fma(double _t5, double _t2, double[] _m) {
        _m[2] = java.lang.Math.fma(this.rX, this.rZ, _t5) * _t2;
        _m[6] = java.lang.Math.fma(this.rY, this.rZ, -(this.rX * this.rW)) * _t2;
        _m[10] = java.lang.Math.fma(-java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), _t2, this.sZ);
        _m[14] = 0.0;
    }

    /** Private per-column body of {@code toMatrix}, stored into {@code _m}; reached only through it. */
    private void toMatrix_s0_c2_mulAdd(double _t5, double _t2, double[] _m) {
        _m[2] = ((this.rX) * (this.rZ) + (_t5)) * _t2;
        _m[6] = ((this.rY) * (this.rZ) - (this.rX * this.rW)) * _t2;
        _m[10] = ((this.sZ) - ((this.rX) * (this.rX) + (this.rY * this.rY)) * (_t2));
        _m[14] = 0.0;
    }

    /**
     * Compute the matrix representation of this transform, returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the resulting matrix
     */
    public Double4x4 toMatrix() {
        if (!Math.useFma()) return toMatrix_mulAdd();
        double _t3 = this.rZ * this.rZ;
        double _t4 = this.rZ * this.rW;
        double _t5 = this.rY * this.rW;
        double[] _m = new double[16];
        toMatrix_s0_c0_fma(_t3, this.sX + this.sX, _t4, _t5, _m);
        toMatrix_s0_c1_fma(_t4, this.sY + this.sY, _t3, _m);
        toMatrix_s0_c2_fma(_t5, this.sZ + this.sZ, _m);
        _m[3] = this.tX;
        _m[7] = this.tY;
        _m[11] = this.tZ;
        _m[15] = 1.0;
        return new Double4x4(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7], _m[8], _m[9], _m[10], _m[11], _m[12], _m[13], _m[14], _m[15], Joml.BIT_AFFINE);
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x4 toMatrix_mulAdd() {
        double _t3 = this.rZ * this.rZ;
        double _t4 = this.rZ * this.rW;
        double _t5 = this.rY * this.rW;
        double[] _m = new double[16];
        toMatrix_s0_c0_mulAdd(_t3, this.sX + this.sX, _t4, _t5, _m);
        toMatrix_s0_c1_mulAdd(_t4, this.sY + this.sY, _t3, _m);
        toMatrix_s0_c2_mulAdd(_t5, this.sZ + this.sZ, _m);
        _m[3] = this.tX;
        _m[7] = this.tY;
        _m[11] = this.tZ;
        _m[15] = 1.0;
        return new Double4x4(_m[0], _m[1], _m[2], _m[3], _m[4], _m[5], _m[6], _m[7], _m[8], _m[9], _m[10], _m[11], _m[12], _m[13], _m[14], _m[15], Joml.BIT_AFFINE);
    }

    /**
     * Compute the 3x3 linear block ({@code R * S}) of this transform (the translation is dropped),
     * returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the resulting matrix
     */
    public Double3x3 toMatrix3x3() {
        if (!Math.useFma()) return toMatrix3x3_mulAdd();
        double _t0 = this.sX + this.sX;
        double _t1 = this.sY + this.sY;
        double _t2 = this.sZ + this.sZ;
        double _t3 = this.rZ * this.rZ;
        double _t4 = this.rZ * this.rW;
        double _t5 = this.rY * this.rW;
        return new Double3x3(java.lang.Math.fma(-java.lang.Math.fma(this.rY, this.rY, _t3), _t0, this.sX), java.lang.Math.fma(this.rX, this.rY, -_t4) * _t1, java.lang.Math.fma(this.rX, this.rZ, _t5) * _t2, java.lang.Math.fma(this.rX, this.rY, _t4) * _t0, java.lang.Math.fma(-java.lang.Math.fma(this.rX, this.rX, _t3), _t1, this.sY), java.lang.Math.fma(this.rY, this.rZ, -(this.rX * this.rW)) * _t2, java.lang.Math.fma(this.rX, this.rZ, -_t5) * _t0, java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ) * _t1, java.lang.Math.fma(-java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), _t2, this.sZ), 0);
    }

    /** {@code toMatrix3x3} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x3 toMatrix3x3_mulAdd() {
        double _t0 = this.sX + this.sX;
        double _t1 = this.sY + this.sY;
        double _t2 = this.sZ + this.sZ;
        double _t3 = this.rZ * this.rZ;
        double _t4 = this.rZ * this.rW;
        double _t5 = this.rY * this.rW;
        return new Double3x3(((this.sX) - ((this.rY) * (this.rY) + (_t3)) * (_t0)), ((this.rX) * (this.rY) - (_t4)) * _t1, ((this.rX) * (this.rZ) + (_t5)) * _t2, ((this.rX) * (this.rY) + (_t4)) * _t0, ((this.sY) - ((this.rX) * (this.rX) + (_t3)) * (_t1)), ((this.rY) * (this.rZ) - (this.rX * this.rW)) * _t2, ((this.rX) * (this.rZ) - (_t5)) * _t0, ((this.rX) * (this.rW) + (this.rY * this.rZ)) * _t1, ((this.sZ) - ((this.rX) * (this.rX) + (this.rY * this.rY)) * (_t2)), 0);
    }

    /**
     * Compute the 3x4 matrix representation of this transform (the omitted last row is implicitly
     * {@code 0, 0, 0, 1}), returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the resulting matrix
     */
    public Double3x4 toMatrix3x4() {
        if (!Math.useFma()) return toMatrix3x4_mulAdd();
        double _t0 = this.sX + this.sX;
        double _t1 = this.sY + this.sY;
        double _t2 = this.sZ + this.sZ;
        double _t3 = this.rZ * this.rZ;
        double _t4 = this.rZ * this.rW;
        double _t5 = this.rY * this.rW;
        return new Double3x4(java.lang.Math.fma(-java.lang.Math.fma(this.rY, this.rY, _t3), _t0, this.sX), java.lang.Math.fma(this.rX, this.rY, -_t4) * _t1, java.lang.Math.fma(this.rX, this.rZ, _t5) * _t2, this.tX, java.lang.Math.fma(this.rX, this.rY, _t4) * _t0, java.lang.Math.fma(-java.lang.Math.fma(this.rX, this.rX, _t3), _t1, this.sY), java.lang.Math.fma(this.rY, this.rZ, -(this.rX * this.rW)) * _t2, this.tY, java.lang.Math.fma(this.rX, this.rZ, -_t5) * _t0, java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ) * _t1, java.lang.Math.fma(-java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), _t2, this.sZ), this.tZ, Joml.BIT_AFFINE);
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x4 toMatrix3x4_mulAdd() {
        double _t0 = this.sX + this.sX;
        double _t1 = this.sY + this.sY;
        double _t2 = this.sZ + this.sZ;
        double _t3 = this.rZ * this.rZ;
        double _t4 = this.rZ * this.rW;
        double _t5 = this.rY * this.rW;
        return new Double3x4(((this.sX) - ((this.rY) * (this.rY) + (_t3)) * (_t0)), ((this.rX) * (this.rY) - (_t4)) * _t1, ((this.rX) * (this.rZ) + (_t5)) * _t2, this.tX, ((this.rX) * (this.rY) + (_t4)) * _t0, ((this.sY) - ((this.rX) * (this.rX) + (_t3)) * (_t1)), ((this.rY) * (this.rZ) - (this.rX * this.rW)) * _t2, this.tY, ((this.rX) * (this.rZ) - (_t5)) * _t0, ((this.rX) * (this.rW) + (this.rY * this.rZ)) * _t1, ((this.sZ) - ((this.rX) * (this.rX) + (this.rY * this.rY)) * (_t2)), this.tZ, Joml.BIT_AFFINE);
    }

    /**
     * Narrow this transform to a rigid transform (translation and rotation; the scale is dropped),
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting rigid transform
     */
    public DoubleRigid toRigid() {
        return new DoubleRigid(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW);
    }

    /**
     * Convert this transform to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code FloatTransform} holding the result
     */
    public FloatTransform toFloat() {
        return new FloatTransform((float) (this.tX), (float) (this.tY), (float) (this.tZ), (float) (this.rX), (float) (this.rY), (float) (this.rZ), (float) (this.rW), (float) (this.sX), (float) (this.sY), (float) (this.sZ));
    }

    /**
     * Create an identity transform.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting transform
     */
    public static DoubleTransform makeIdentity() {
        return new DoubleTransform(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0);
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
    public DoubleTransform set(DoubleQuat rotation) {
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        return new DoubleTransform(0.0, 0.0, 0.0, rotationX, rotationY, rotationZ, rotationW, 1.0, 1.0, 1.0);
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
    public DoubleTransform set(double rotationX, double rotationY, double rotationZ, double rotationW) {
        return new DoubleTransform(0.0, 0.0, 0.0, rotationX, rotationY, rotationZ, rotationW, 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeRotation(DoubleQuat rotation) {
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        return new DoubleTransform(0.0, 0.0, 0.0, rotationX, rotationY, rotationZ, rotationW, 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeRotation(double rotationX, double rotationY, double rotationZ, double rotationW) {
        return new DoubleTransform(0.0, 0.0, 0.0, rotationX, rotationY, rotationZ, rotationW, 1.0, 1.0, 1.0);
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
    public DoubleTransform set(Double3 translation) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        return new DoubleTransform(translationX, translationY, translationZ, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0);
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
    public DoubleTransform set(double translationX, double translationY, double translationZ) {
        return new DoubleTransform(translationX, translationY, translationZ, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeTranslation(Double3 translation) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        return new DoubleTransform(translationX, translationY, translationZ, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeTranslation(double translationX, double translationY, double translationZ) {
        return new DoubleTransform(translationX, translationY, translationZ, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0);
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
     * shows when this component is much larger in magnitude than the other one (1e17 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the rotation of this transform must have unit length; the rotation of
     * {@code other} must have unit length.
     *
     * @param other the transform to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting transform
     */
    public DoubleTransform lerp(DoubleTransform other, double t) {
        double otherTX = other.tX;
        double otherTY = other.tY;
        double otherTZ = other.tZ;
        double otherRX = other.rX;
        double otherRY = other.rY;
        double otherRZ = other.rZ;
        double otherRW = other.rW;
        double otherSX = other.sX;
        double otherSY = other.sY;
        double otherSZ = other.sZ;
        if (!Math.useFma()) return lerp_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, t);
        double _t0 = 1.0 - t;
        double _t12 = java.lang.Math.fma(otherRW, this.rW, java.lang.Math.fma(otherRZ, this.rZ, java.lang.Math.fma(otherRX, this.rX, otherRY * this.rY)));
        double _t16 = Math.acos(Math.min(1.0, java.lang.Math.abs(_t12)));
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
        return lerp_s2f52a392_tail_fma(_t17, Math.sin(_t0 * _t16), Math.sin(t * _t16), _t21, 1.0 / _t17, t, _t0, _t22, _t23, _t24, otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private DoubleTransform lerp_s2f52a392_tail_fma(double _t17, double _t25, double _t19, double _t21, double _t17_inv, double t, double _t0, double _t22, double _t23, double _t24, double otherTX, double otherTY, double otherTZ, double otherSX, double otherSY, double otherSZ) {
        double _t42, _t43, _t44, _t45;
        if (_t17 > 0.0) {
            _t42 = java.lang.Math.fma(this.rW, _t25, _t19 * _t21) * _t17_inv;
            _t43 = java.lang.Math.fma(this.rZ, _t25, _t19 * _t22) * _t17_inv;
            _t44 = java.lang.Math.fma(this.rX, _t25, _t19 * _t23) * _t17_inv;
            _t45 = java.lang.Math.fma(this.rY, _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = java.lang.Math.fma(t, _t21, this.rW * _t0);
            _t43 = java.lang.Math.fma(t, _t22, this.rZ * _t0);
            _t44 = java.lang.Math.fma(t, _t23, this.rX * _t0);
            _t45 = java.lang.Math.fma(t, _t24, this.rY * _t0);
        }
        double _t49 = java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, _t45 * _t45)));
        double _sfx0 = java.lang.Math.fma(t, otherTX - this.tX, this.tX);
        return lerp_s2f52a392_tail2_fma(t, otherTY, otherTZ, _t49, (1.0 / java.lang.Math.sqrt(_t49)), _t44, _t45, _t43, _t42, otherSX, otherSY, otherSZ, _sfx0);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private DoubleTransform lerp_s2f52a392_tail_mulAdd(double _t17, double _t25, double _t19, double _t21, double _t17_inv, double t, double _t0, double _t22, double _t23, double _t24, double otherTX, double otherTY, double otherTZ, double otherSX, double otherSY, double otherSZ) {
        double _t42, _t43, _t44, _t45;
        if (_t17 > 0.0) {
            _t42 = ((this.rW) * (_t25) + (_t19 * _t21)) * _t17_inv;
            _t43 = ((this.rZ) * (_t25) + (_t19 * _t22)) * _t17_inv;
            _t44 = ((this.rX) * (_t25) + (_t19 * _t23)) * _t17_inv;
            _t45 = ((this.rY) * (_t25) + (_t19 * _t24)) * _t17_inv;
        } else {
            _t42 = ((t) * (_t21) + (this.rW * _t0));
            _t43 = ((t) * (_t22) + (this.rZ * _t0));
            _t44 = ((t) * (_t23) + (this.rX * _t0));
            _t45 = ((t) * (_t24) + (this.rY * _t0));
        }
        double _t49 = ((_t42) * (_t42) + (((_t43) * (_t43) + (((_t44) * (_t44) + (_t45 * _t45))))));
        double _sfx0 = ((t) * (otherTX - this.tX) + (this.tX));
        return lerp_s2f52a392_tail2_mulAdd(t, otherTY, otherTZ, _t49, (1.0 / java.lang.Math.sqrt(_t49)), _t44, _t45, _t43, _t42, otherSX, otherSY, otherSZ, _sfx0);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private DoubleTransform lerp_s2f52a392_tail2_fma(double t, double otherTY, double otherTZ, double _t49, double _t50, double _t44, double _t45, double _t43, double _t42, double otherSX, double otherSY, double otherSZ, double _sfx0) {
        double _sfx1 = java.lang.Math.fma(t, otherTY - this.tY, this.tY);
        double _sfx2 = java.lang.Math.fma(t, otherTZ - this.tZ, this.tZ);
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (_t49 != 0.0) {
            _sfx3 = _t50 * _t44;
            _sfx4 = _t50 * _t45;
            _sfx5 = _t50 * _t43;
            _sfx6 = _t50 * _t42;
        } else {
            _sfx3 = 0.0;
            _sfx4 = 0.0;
            _sfx5 = 0.0;
            _sfx6 = 0.0;
        }
        double _sfx7 = java.lang.Math.fma(t, otherSX - this.sX, this.sX);
        double _sfx8 = java.lang.Math.fma(t, otherSY - this.sY, this.sY);
        double _sfx9 = java.lang.Math.fma(t, otherSZ - this.sZ, this.sZ);
        return new DoubleTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private DoubleTransform lerp_s2f52a392_tail2_mulAdd(double t, double otherTY, double otherTZ, double _t49, double _t50, double _t44, double _t45, double _t43, double _t42, double otherSX, double otherSY, double otherSZ, double _sfx0) {
        double _sfx1 = ((t) * (otherTY - this.tY) + (this.tY));
        double _sfx2 = ((t) * (otherTZ - this.tZ) + (this.tZ));
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (_t49 != 0.0) {
            _sfx3 = _t50 * _t44;
            _sfx4 = _t50 * _t45;
            _sfx5 = _t50 * _t43;
            _sfx6 = _t50 * _t42;
        } else {
            _sfx3 = 0.0;
            _sfx4 = 0.0;
            _sfx5 = 0.0;
            _sfx6 = 0.0;
        }
        double _sfx7 = ((t) * (otherSX - this.sX) + (this.sX));
        double _sfx8 = ((t) * (otherSY - this.sY) + (this.sY));
        double _sfx9 = ((t) * (otherSZ - this.sZ) + (this.sZ));
        return new DoubleTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
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
     * shows when this component is much larger in magnitude than the other one (1e17 towards 1 ends
     * at 0).
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
     * @return the resulting transform
     */
    public DoubleTransform lerp(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, double t) {
        if (!Math.useFma()) return lerp_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ, t);
        double _t0 = 1.0 - t;
        double _t12 = java.lang.Math.fma(otherRW, this.rW, java.lang.Math.fma(otherRZ, this.rZ, java.lang.Math.fma(otherRX, this.rX, otherRY * this.rY)));
        double _t16 = Math.acos(Math.min(1.0, java.lang.Math.abs(_t12)));
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
        return lerp_s2f52a392_tail_fma(_t17, Math.sin(_t0 * _t16), Math.sin(t * _t16), _t21, 1.0 / _t17, t, _t0, _t22, _t23, _t24, otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ);
    }

    /** {@code lerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform lerp_mulAdd(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, double t) {
        double _t0 = 1.0 - t;
        double _t12 = ((otherRW) * (this.rW) + (((otherRZ) * (this.rZ) + (((otherRX) * (this.rX) + (otherRY * this.rY))))));
        double _t16 = Math.acos(Math.min(1.0, java.lang.Math.abs(_t12)));
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
        return lerp_s2f52a392_tail_mulAdd(_t17, Math.sin(_t0 * _t16), Math.sin(t * _t16), _t21, 1.0 / _t17, t, _t0, _t22, _t23, _t24, otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ);
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
    public DoubleTransform mul(DoubleTransform other) {
        return mul(other.tX, other.tY, other.tZ, other.rX, other.rY, other.rZ, other.rW, other.sX, other.sY, other.sZ);
    }

    /** Private tail of {@code mul}; reached only through it. */
    private DoubleTransform mul_s25974579_tail_fma(double otherRX, double otherRW, double otherRZ, double otherRY, double otherSX, double otherSY, double otherSZ, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3 = java.lang.Math.fma(otherRX, this.rW, otherRW * this.rX) + java.lang.Math.fma(otherRZ, this.rY, otherRY * -this.rZ);
        double _sfx4 = java.lang.Math.fma(otherRY, this.rW, otherRW * this.rY) + java.lang.Math.fma(otherRX, this.rZ, otherRZ * -this.rX);
        double _sfx5 = java.lang.Math.fma(otherRZ, this.rW, otherRW * this.rZ) + java.lang.Math.fma(otherRY, this.rX, otherRX * -this.rY);
        double _sfx6 = java.lang.Math.fma(otherRW, this.rW, otherRX * -this.rX) - java.lang.Math.fma(otherRY, this.rY, otherRZ * this.rZ);
        double _sfx7 = otherSX * this.sX;
        double _sfx8 = otherSY * this.sY;
        double _sfx9 = otherSZ * this.sZ;
        return new DoubleTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Private tail of {@code mul}; reached only through it. */
    private DoubleTransform mul_s25974579_tail_mulAdd(double otherRX, double otherRW, double otherRZ, double otherRY, double otherSX, double otherSY, double otherSZ, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3 = ((otherRX) * (this.rW) + (otherRW * this.rX)) + ((otherRZ) * (this.rY) - (otherRY * this.rZ));
        double _sfx4 = ((otherRY) * (this.rW) + (otherRW * this.rY)) + ((otherRX) * (this.rZ) - (otherRZ * this.rX));
        double _sfx5 = ((otherRZ) * (this.rW) + (otherRW * this.rZ)) + ((otherRY) * (this.rX) - (otherRX * this.rY));
        double _sfx6 = ((otherRW) * (this.rW) - (otherRX * this.rX)) - ((otherRY) * (this.rY) + (otherRZ * this.rZ));
        double _sfx7 = otherSX * this.sX;
        double _sfx8 = otherSY * this.sY;
        double _sfx9 = otherSZ * this.sZ;
        return new DoubleTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
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
    public DoubleTransform mul(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ) {
        if (!Math.useFma()) return mul_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ);
        double _t0 = otherTY * this.sY;
        double _t1 = otherTX * this.sX;
        double _t2 = otherTZ * this.sZ;
        double _t12 = 2.0 * java.lang.Math.fma(this.rX, _t0, -(this.rY * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(this.rZ, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(this.rY, _t2, -(this.rZ * _t0));
        double _sfx0 = java.lang.Math.fma(this.rY, _t12, java.lang.Math.fma(-this.rZ, _t13, java.lang.Math.fma(this.rW, _t14, java.lang.Math.fma(otherTX, this.sX, this.tX))));
        double _sfx1 = java.lang.Math.fma(this.rZ, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, java.lang.Math.fma(otherTY, this.sY, this.tY))));
        double _sfx2 = java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rY, _t14, java.lang.Math.fma(this.rW, _t12, java.lang.Math.fma(otherTZ, this.sZ, this.tZ))));
        return mul_s25974579_tail_fma(otherRX, otherRW, otherRZ, otherRY, otherSX, otherSY, otherSZ, _sfx0, _sfx1, _sfx2);
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform mul_mulAdd(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ) {
        double _t0 = otherTY * this.sY;
        double _t1 = otherTX * this.sX;
        double _t2 = otherTZ * this.sZ;
        double _t12 = 2.0 * ((this.rX) * (_t0) - (this.rY * _t1));
        double _t13 = 2.0 * ((this.rZ) * (_t1) - (this.rX * _t2));
        double _t14 = 2.0 * ((this.rY) * (_t2) - (this.rZ * _t0));
        double _sfx0 = ((this.rY) * (_t12) + (((((this.rW) * (_t14) + (((otherTX) * (this.sX) + (this.tX))))) - (this.rZ) * (_t13))));
        double _sfx1 = ((this.rZ) * (_t14) + (((((this.rW) * (_t13) + (((otherTY) * (this.sY) + (this.tY))))) - (this.rX) * (_t12))));
        double _sfx2 = ((this.rX) * (_t13) + (((((this.rW) * (_t12) + (((otherTZ) * (this.sZ) + (this.tZ))))) - (this.rY) * (_t14))));
        return mul_s25974579_tail_mulAdd(otherRX, otherRW, otherRZ, otherRY, otherSX, otherSY, otherSZ, _sfx0, _sfx1, _sfx2);
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
    public DoubleTransform preMul(DoubleTransform other) {
        double otherTX = other.tX;
        double otherTY = other.tY;
        double otherTZ = other.tZ;
        double otherRX = other.rX;
        double otherRY = other.rY;
        double otherRZ = other.rZ;
        double otherRW = other.rW;
        double otherSX = other.sX;
        double otherSY = other.sY;
        double otherSZ = other.sZ;
        if (Math.useFma()) {
            double _t0 = otherSY * this.tY;
            double _t1 = otherSX * this.tX;
            double _t2 = otherSZ * this.tZ;
            return preMul_s25974579_tail_fma(otherRY, _t2, otherRZ, _t0, 2.0 * java.lang.Math.fma(otherRX, _t0, -(otherRY * _t1)), 2.0 * java.lang.Math.fma(otherRZ, _t1, -(otherRX * _t2)), otherRW, otherSX, otherTX, otherRX, otherSY, otherTY, otherSZ, otherTZ);
        } else {
            double _t0 = otherSY * this.tY;
            double _t1 = otherSX * this.tX;
            double _t2 = otherSZ * this.tZ;
            return preMul_s25974579_tail_mulAdd(otherRY, _t2, otherRZ, _t0, 2.0 * ((otherRX) * (_t0) - (otherRY * _t1)), 2.0 * ((otherRZ) * (_t1) - (otherRX * _t2)), otherRW, otherSX, otherTX, otherRX, otherSY, otherTY, otherSZ, otherTZ);
        }
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private DoubleTransform preMul_s25974579_tail_fma(double otherRY, double _t2, double otherRZ, double _t0, double _t12, double _t13, double otherRW, double otherSX, double otherTX, double otherRX, double otherSY, double otherTY, double otherSZ, double otherTZ) {
        double _t14 = 2.0 * java.lang.Math.fma(otherRY, _t2, -(otherRZ * _t0));
        return new DoubleTransform(java.lang.Math.fma(otherRY, _t12, java.lang.Math.fma(-otherRZ, _t13, java.lang.Math.fma(otherRW, _t14, java.lang.Math.fma(otherSX, this.tX, otherTX)))), java.lang.Math.fma(otherRZ, _t14, java.lang.Math.fma(-otherRX, _t12, java.lang.Math.fma(otherRW, _t13, java.lang.Math.fma(otherSY, this.tY, otherTY)))), java.lang.Math.fma(otherRX, _t13, java.lang.Math.fma(-otherRY, _t14, java.lang.Math.fma(otherRW, _t12, java.lang.Math.fma(otherSZ, this.tZ, otherTZ)))), java.lang.Math.fma(otherRX, this.rW, otherRW * this.rX) + java.lang.Math.fma(otherRY, this.rZ, -otherRZ * this.rY), java.lang.Math.fma(otherRY, this.rW, otherRW * this.rY) + java.lang.Math.fma(otherRZ, this.rX, -otherRX * this.rZ), java.lang.Math.fma(otherRZ, this.rW, otherRW * this.rZ) + java.lang.Math.fma(otherRX, this.rY, -otherRY * this.rX), java.lang.Math.fma(otherRW, this.rW, -otherRX * this.rX) - java.lang.Math.fma(otherRY, this.rY, otherRZ * this.rZ), otherSX * this.sX, otherSY * this.sY, otherSZ * this.sZ);
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private DoubleTransform preMul_s25974579_tail_mulAdd(double otherRY, double _t2, double otherRZ, double _t0, double _t12, double _t13, double otherRW, double otherSX, double otherTX, double otherRX, double otherSY, double otherTY, double otherSZ, double otherTZ) {
        double _t14 = 2.0 * ((otherRY) * (_t2) - (otherRZ * _t0));
        return new DoubleTransform(((otherRY) * (_t12) + (((((otherRW) * (_t14) + (((otherSX) * (this.tX) + (otherTX))))) - (otherRZ) * (_t13)))), ((otherRZ) * (_t14) + (((((otherRW) * (_t13) + (((otherSY) * (this.tY) + (otherTY))))) - (otherRX) * (_t12)))), ((otherRX) * (_t13) + (((((otherRW) * (_t12) + (((otherSZ) * (this.tZ) + (otherTZ))))) - (otherRY) * (_t14)))), ((otherRX) * (this.rW) + (otherRW * this.rX)) + ((otherRY) * (this.rZ) - (otherRZ * this.rY)), ((otherRY) * (this.rW) + (otherRW * this.rY)) + ((otherRZ) * (this.rX) - (otherRX * this.rZ)), ((otherRZ) * (this.rW) + (otherRW * this.rZ)) + ((otherRX) * (this.rY) - (otherRY * this.rX)), ((otherRW) * (this.rW) - (otherRX * this.rX)) - ((otherRY) * (this.rY) + (otherRZ * this.rZ)), otherSX * this.sX, otherSY * this.sY, otherSZ * this.sZ);
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
    public DoubleTransform preMul(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ) {
        if (Math.useFma()) {
            double _t0 = otherSY * this.tY;
            double _t1 = otherSX * this.tX;
            double _t2 = otherSZ * this.tZ;
            return preMul_s25974579_tail_fma(otherRY, _t2, otherRZ, _t0, 2.0 * java.lang.Math.fma(otherRX, _t0, -(otherRY * _t1)), 2.0 * java.lang.Math.fma(otherRZ, _t1, -(otherRX * _t2)), otherRW, otherSX, otherTX, otherRX, otherSY, otherTY, otherSZ, otherTZ);
        } else {
            double _t0 = otherSY * this.tY;
            double _t1 = otherSX * this.tX;
            double _t2 = otherSZ * this.tZ;
            return preMul_s25974579_tail_mulAdd(otherRY, _t2, otherRZ, _t0, 2.0 * ((otherRX) * (_t0) - (otherRY * _t1)), 2.0 * ((otherRZ) * (_t1) - (otherRX * _t2)), otherRW, otherSX, otherTX, otherRX, otherSY, otherTY, otherSZ, otherTZ);
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
    public DoubleTransform difference(DoubleTransform other) {
        return difference(other.tX, other.tY, other.tZ, other.rX, other.rY, other.rZ, other.rW, other.sX, other.sY, other.sZ);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private DoubleTransform difference_s25974579_tail_fma(double _t30, double _t31, double _t32, double _sp1, double _t33, double _t34, double _t35, double _sp3, double _sp2, double _sp5, double _sp0, double _sp4, double otherRX, double otherRW, double otherRY, double otherRZ, double otherSX, double _rcp1, double otherSY, double _rcp2, double otherSZ, double _rcp0) {
        double _sfx0 = java.lang.Math.fma(this.rZ, _t30, -this.rY * _t31) + java.lang.Math.fma(this.rW, _t32, _sp1) + (java.lang.Math.fma(this.rZ, _t33, -this.rY * _t34) + java.lang.Math.fma(this.rW, _t35, -_sp3));
        double _sfx1 = java.lang.Math.fma(this.rX, _t31, -this.rZ * _t32) + java.lang.Math.fma(this.rW, _t30, _sp2) + (java.lang.Math.fma(this.rX, _t34, -this.rZ * _t35) + java.lang.Math.fma(this.rW, _t33, -_sp5));
        double _sfx2 = java.lang.Math.fma(this.rY, _t32, -this.rX * _t30) + java.lang.Math.fma(this.rW, _t31, _sp0) + (java.lang.Math.fma(this.rY, _t35, -this.rX * _t33) + java.lang.Math.fma(this.rW, _t34, -_sp4));
        return difference_s25974579_tail2_fma(otherRX, otherRW, otherRY, otherRZ, otherSX, _rcp1, otherSY, _rcp2, otherSZ, _rcp0, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private DoubleTransform difference_s25974579_tail_mulAdd(double _t30, double _t31, double _t32, double _sp1, double _t33, double _t34, double _t35, double _sp3, double _sp2, double _sp5, double _sp0, double _sp4, double otherRX, double otherRW, double otherRY, double otherRZ, double otherSX, double _rcp1, double otherSY, double _rcp2, double otherSZ, double _rcp0) {
        double _sfx0 = ((this.rZ) * (_t30) - (this.rY * _t31)) + ((this.rW) * (_t32) + (_sp1)) + (((this.rZ) * (_t33) - (this.rY * _t34)) + ((this.rW) * (_t35) - (_sp3)));
        double _sfx1 = ((this.rX) * (_t31) - (this.rZ * _t32)) + ((this.rW) * (_t30) + (_sp2)) + (((this.rX) * (_t34) - (this.rZ * _t35)) + ((this.rW) * (_t33) - (_sp5)));
        double _sfx2 = ((this.rY) * (_t32) - (this.rX * _t30)) + ((this.rW) * (_t31) + (_sp0)) + (((this.rY) * (_t35) - (this.rX * _t33)) + ((this.rW) * (_t34) - (_sp4)));
        return difference_s25974579_tail2_mulAdd(otherRX, otherRW, otherRY, otherRZ, otherSX, _rcp1, otherSY, _rcp2, otherSZ, _rcp0, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private DoubleTransform difference_s25974579_tail2_fma(double otherRX, double otherRW, double otherRY, double otherRZ, double otherSX, double _rcp1, double otherSY, double _rcp2, double otherSZ, double _rcp0, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3 = java.lang.Math.fma(otherRX, this.rW, otherRW * -this.rX) + java.lang.Math.fma(otherRY, this.rZ, otherRZ * -this.rY);
        double _sfx4 = java.lang.Math.fma(otherRY, this.rW, otherRW * -this.rY) + java.lang.Math.fma(otherRZ, this.rX, otherRX * -this.rZ);
        double _sfx5 = java.lang.Math.fma(otherRX, this.rY, otherRY * -this.rX) + java.lang.Math.fma(otherRZ, this.rW, otherRW * -this.rZ);
        double _sfx6 = java.lang.Math.fma(otherRX, this.rX, otherRW * this.rW) - java.lang.Math.fma(otherRZ, -this.rZ, otherRY * -this.rY);
        double _sfx7 = otherSX * _rcp1;
        double _sfx8 = otherSY * _rcp2;
        double _sfx9 = otherSZ * _rcp0;
        return new DoubleTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private DoubleTransform difference_s25974579_tail2_mulAdd(double otherRX, double otherRW, double otherRY, double otherRZ, double otherSX, double _rcp1, double otherSY, double _rcp2, double otherSZ, double _rcp0, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3 = ((otherRX) * (this.rW) - (otherRW * this.rX)) + ((otherRY) * (this.rZ) - (otherRZ * this.rY));
        double _sfx4 = ((otherRY) * (this.rW) - (otherRW * this.rY)) + ((otherRZ) * (this.rX) - (otherRX * this.rZ));
        double _sfx5 = ((otherRX) * (this.rY) - (otherRY * this.rX)) + ((otherRZ) * (this.rW) - (otherRW * this.rZ));
        double _sfx6 = ((otherRX) * (this.rX) + (otherRW * this.rW)) - ((otherRZ) * (-this.rZ) - (otherRY * this.rY));
        double _sfx7 = otherSX * _rcp1;
        double _sfx8 = otherSY * _rcp2;
        double _sfx9 = otherSZ * _rcp0;
        return new DoubleTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
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
    public DoubleTransform difference(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ) {
        if (!Math.useFma()) return difference_mulAdd(otherTX, otherTY, otherTZ, otherRX, otherRY, otherRZ, otherRW, otherSX, otherSY, otherSZ);
        double _rcp0 = 1.0 / this.sZ;
        double _sp4 = _rcp0 * this.tZ;
        double _sp0 = otherTZ * _rcp0;
        double _rcp1 = 1.0 / this.sX;
        double _sp3 = _rcp1 * this.tX;
        double _sp1 = otherTX * _rcp1;
        double _rcp2 = 1.0 / this.sY;
        double _sp5 = _rcp2 * this.tY;
        double _sp2 = otherTY * _rcp2;
        return difference_s25974579_tail_fma(2.0 * java.lang.Math.fma(_sp0, this.rX, -(_sp1 * this.rZ)), 2.0 * java.lang.Math.fma(_sp1, this.rY, -(_sp2 * this.rX)), 2.0 * java.lang.Math.fma(_sp2, this.rZ, -(_sp0 * this.rY)), _sp1, 2.0 * java.lang.Math.fma(_sp3, this.rZ, -(_sp4 * this.rX)), 2.0 * java.lang.Math.fma(_sp5, this.rX, -(_sp3 * this.rY)), 2.0 * java.lang.Math.fma(_sp4, this.rY, -(_sp5 * this.rZ)), _sp3, _sp2, _sp5, _sp0, _sp4, otherRX, otherRW, otherRY, otherRZ, otherSX, _rcp1, otherSY, _rcp2, otherSZ, _rcp0);
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform difference_mulAdd(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ) {
        double _rcp0 = 1.0 / this.sZ;
        double _sp4 = _rcp0 * this.tZ;
        double _sp0 = otherTZ * _rcp0;
        double _rcp1 = 1.0 / this.sX;
        double _sp3 = _rcp1 * this.tX;
        double _sp1 = otherTX * _rcp1;
        double _rcp2 = 1.0 / this.sY;
        double _sp5 = _rcp2 * this.tY;
        double _sp2 = otherTY * _rcp2;
        return difference_s25974579_tail_mulAdd(2.0 * ((_sp0) * (this.rX) - (_sp1 * this.rZ)), 2.0 * ((_sp1) * (this.rY) - (_sp2 * this.rX)), 2.0 * ((_sp2) * (this.rZ) - (_sp0 * this.rY)), _sp1, 2.0 * ((_sp3) * (this.rZ) - (_sp4 * this.rX)), 2.0 * ((_sp5) * (this.rX) - (_sp3 * this.rY)), 2.0 * ((_sp4) * (this.rY) - (_sp5 * this.rZ)), _sp3, _sp2, _sp5, _sp0, _sp4, otherRX, otherRW, otherRY, otherRZ, otherSX, _rcp1, otherSY, _rcp2, otherSZ, _rcp0);
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
    public DoubleTransform invert() {
        if (!Math.useFma()) return invert_mulAdd();
        double _rcp0 = 1.0 / this.sX;
        double _sp0 = this.tX * _rcp0;
        double _rcp1 = 1.0 / this.sZ;
        double _sp1 = this.tZ * _rcp1;
        double _rcp2 = 1.0 / this.sY;
        double _sp2 = this.tY * _rcp2;
        double _t0 = -this.rY;
        double _t1 = -this.rZ;
        double _t2 = -this.rX;
        double _t18 = 2.0 * java.lang.Math.fma(_sp0, this.rZ, -(_sp1 * this.rX));
        double _t19 = 2.0 * java.lang.Math.fma(_sp2, this.rX, -(_sp0 * this.rY));
        double _t20 = 2.0 * java.lang.Math.fma(_sp1, this.rY, -(_sp2 * this.rZ));
        return new DoubleTransform(java.lang.Math.fma(this.rZ, _t18, java.lang.Math.fma(_t0, _t19, java.lang.Math.fma(this.rW, _t20, -_sp0))), java.lang.Math.fma(this.rX, _t19, java.lang.Math.fma(_t1, _t20, java.lang.Math.fma(this.rW, _t18, -_sp2))), java.lang.Math.fma(this.rY, _t20, java.lang.Math.fma(_t2, _t18, java.lang.Math.fma(this.rW, _t19, -_sp1))), _t2, _t0, _t1, this.rW, _rcp0, _rcp2, _rcp1);
    }

    /** {@code invert} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform invert_mulAdd() {
        double _rcp0 = 1.0 / this.sX;
        double _sp0 = this.tX * _rcp0;
        double _rcp1 = 1.0 / this.sZ;
        double _sp1 = this.tZ * _rcp1;
        double _rcp2 = 1.0 / this.sY;
        double _sp2 = this.tY * _rcp2;
        double _t0 = -this.rY;
        double _t1 = -this.rZ;
        double _t2 = -this.rX;
        double _t18 = 2.0 * ((_sp0) * (this.rZ) - (_sp1 * this.rX));
        double _t19 = 2.0 * ((_sp2) * (this.rX) - (_sp0 * this.rY));
        double _t20 = 2.0 * ((_sp1) * (this.rY) - (_sp2 * this.rZ));
        return new DoubleTransform(((this.rZ) * (_t18) + (((_t0) * (_t19) + (((this.rW) * (_t20) - (_sp0)))))), ((this.rX) * (_t19) + (((_t1) * (_t20) + (((this.rW) * (_t18) - (_sp2)))))), ((this.rY) * (_t20) + (((_t2) * (_t18) + (((this.rW) * (_t19) - (_sp1)))))), _t2, _t0, _t1, this.rW, _rcp0, _rcp2, _rcp1);
    }

    /**
     * Normalize this transform so that its rotation part has unit length, leaving its translation
     * and scale unchanged (a zero-length rotation yields the zero quaternion), returning the result
     * as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting transform
     */
    public DoubleTransform normalize() {
        if (Math.useFma()) {
            double _t3 = java.lang.Math.fma(this.rW, this.rW, java.lang.Math.fma(this.rZ, this.rZ, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY)));
            double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0) {
                return new DoubleTransform(this.tX, this.tY, this.tZ, this.rX * _t4, this.rY * _t4, this.rZ * _t4, this.rW * _t4, this.sX, this.sY, this.sZ);
            } else {
                return new DoubleTransform(this.tX, this.tY, this.tZ, 0.0, 0.0, 0.0, 0.0, this.sX, this.sY, this.sZ);
            }
        } else {
            double _t3 = ((this.rW) * (this.rW) + (((this.rZ) * (this.rZ) + (((this.rX) * (this.rX) + (this.rY * this.rY))))));
            double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0) {
                return new DoubleTransform(this.tX, this.tY, this.tZ, this.rX * _t4, this.rY * _t4, this.rZ * _t4, this.rW * _t4, this.sX, this.sY, this.sZ);
            } else {
                return new DoubleTransform(this.tX, this.tY, this.tZ, 0.0, 0.0, 0.0, 0.0, this.sX, this.sY, this.sZ);
            }
        }
    }

    /**
     * Normalize this transform so that its rotation part has unit length, leaving its translation
     * and scale unchanged, unless the rotation has zero length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the normalized transform, or an empty {@code Optional} when the rotation has zero
     *        length
     */
    public java.util.Optional<DoubleTransform> tryNormalize() {
        if (Math.useFma()) {
            double _ct0 = java.lang.Math.fma(this.rW, this.rW, java.lang.Math.fma(this.rZ, this.rZ, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY)));
            if (!(_ct0 > 0.0)) return java.util.Optional.empty();
            double _t5 = (1.0 / java.lang.Math.sqrt(_ct0));
            return java.util.Optional.of(new DoubleTransform(this.tX, this.tY, this.tZ, this.rX * _t5, this.rY * _t5, this.rZ * _t5, this.rW * _t5, this.sX, this.sY, this.sZ));
        } else {
            double _ct0 = ((this.rW) * (this.rW) + (((this.rZ) * (this.rZ) + (((this.rX) * (this.rX) + (this.rY * this.rY))))));
            if (!(_ct0 > 0.0)) return java.util.Optional.empty();
            double _t5 = (1.0 / java.lang.Math.sqrt(_ct0));
            return java.util.Optional.of(new DoubleTransform(this.tX, this.tY, this.tZ, this.rX * _t5, this.rY * _t5, this.rZ * _t5, this.rW * _t5, this.sX, this.sY, this.sZ));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesXYZ() {
        if (!Math.useFma()) return getEulerAnglesXYZ_mulAdd();
        double _t1 = this.rY * this.rZ;
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, this.rW, -_t1);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        double _t12 = java.lang.Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < java.lang.Math.fma(_t8, _t8, _t12) * 1.0E-15) {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rW, _t1), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t3), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, _t10), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t3), 1.0)));
        }
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd() {
        double _t1 = this.rY * this.rZ;
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * ((this.rX) * (this.rZ) + (this.rY * this.rW));
        double _t9 = 2.0 * ((this.rX) * (this.rW) - (_t1));
        double _t10 = ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
        double _t12 = ((_t10) * (_t10) + (_t9 * _t9));
        if (_t12 < ((_t8) * (_t8) + (_t12)) * 1.0E-15) {
            return new Double3(Math.atan2(2.0 * ((this.rX) * (this.rW) + (_t1)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t3))))), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, _t10), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * ((this.rZ) * (this.rW) - (this.rX * this.rY)), ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t3))))));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must not
     * be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-15}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesXYZ(double epsilon) {
        if (!Math.useFma()) return getEulerAnglesXYZ_mulAdd(epsilon);
        double _t1 = this.rY * this.rZ;
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, this.rW, -_t1);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        double _t12 = java.lang.Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < epsilon * java.lang.Math.fma(_t8, _t8, _t12)) {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rW, _t1), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t3), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, _t10), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t3), 1.0)));
        }
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd(double epsilon) {
        double _t1 = this.rY * this.rZ;
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * ((this.rX) * (this.rZ) + (this.rY * this.rW));
        double _t9 = 2.0 * ((this.rX) * (this.rW) - (_t1));
        double _t10 = ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
        double _t12 = ((_t10) * (_t10) + (_t9 * _t9));
        if (_t12 < epsilon * ((_t8) * (_t8) + (_t12))) {
            return new Double3(Math.atan2(2.0 * ((this.rX) * (this.rW) + (_t1)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t3))))), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, _t10), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * ((this.rZ) * (this.rW) - (this.rX * this.rY)), ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t3))))));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesXZY() {
        if (!Math.useFma()) return getEulerAnglesXZY_mulAdd();
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rY * this.rZ;
        double _t7 = 2.0 * java.lang.Math.fma(this.rX, this.rW, _t1);
        double _t8 = 2.0 * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < java.lang.Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rW, -_t1), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), 0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(_t7, _t9), Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t0), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        }
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd() {
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rY * this.rZ;
        double _t7 = 2.0 * ((this.rX) * (this.rW) + (_t1));
        double _t8 = 2.0 * ((this.rZ) * (this.rW) - (this.rX * this.rY));
        double _t9 = ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < ((_t8) * (_t8) + (_t11)) * 1.0E-15) {
            return new Double3(Math.atan2(2.0 * ((this.rX) * (this.rW) - (_t1)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))))), 0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(_t7, _t9), Math.atan2(2.0 * ((this.rX) * (this.rZ) + (this.rY * this.rW)), ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t0))))), Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must not
     * be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-15}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesXZY(double epsilon) {
        if (!Math.useFma()) return getEulerAnglesXZY_mulAdd(epsilon);
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rY * this.rZ;
        double _t7 = 2.0 * java.lang.Math.fma(this.rX, this.rW, _t1);
        double _t8 = 2.0 * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < epsilon * java.lang.Math.fma(_t8, _t8, _t11)) {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rW, -_t1), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), 0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(_t7, _t9), Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t0), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        }
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd(double epsilon) {
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rY * this.rZ;
        double _t7 = 2.0 * ((this.rX) * (this.rW) + (_t1));
        double _t8 = 2.0 * ((this.rZ) * (this.rW) - (this.rX * this.rY));
        double _t9 = ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < epsilon * ((_t8) * (_t8) + (_t11))) {
            return new Double3(Math.atan2(2.0 * ((this.rX) * (this.rW) - (_t1)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))))), 0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(_t7, _t9), Math.atan2(2.0 * ((this.rX) * (this.rZ) + (this.rY * this.rW)), ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t0))))), Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesYXZ() {
        if (!Math.useFma()) return getEulerAnglesYXZ_mulAdd();
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, this.rW, this.rY * -this.rZ);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        double _t12 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < java.lang.Math.fma(_t9, _t9, _t12) * 1.0E-15) {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * java.lang.Math.fma(this.rY, this.rW, this.rX * -this.rZ), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t3), 1.0)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(_t8, _t10), Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t3), 1.0)));
        }
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd() {
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * ((this.rX) * (this.rZ) + (this.rY * this.rW));
        double _t9 = 2.0 * ((this.rX) * (this.rW) - (this.rY * this.rZ));
        double _t10 = ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
        double _t12 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t12 < ((_t9) * (_t9) + (_t12)) * 1.0E-15) {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * ((this.rY) * (this.rW) - (this.rX * this.rZ)), ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t3))))), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(_t8, _t10), Math.atan2(2.0 * ((this.rX) * (this.rY) + (this.rZ * this.rW)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t3))))));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must not
     * be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-15}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesYXZ(double epsilon) {
        if (!Math.useFma()) return getEulerAnglesYXZ_mulAdd(epsilon);
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, this.rW, this.rY * -this.rZ);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        double _t12 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < epsilon * java.lang.Math.fma(_t9, _t9, _t12)) {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * java.lang.Math.fma(this.rY, this.rW, this.rX * -this.rZ), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t3), 1.0)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(_t8, _t10), Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t3), 1.0)));
        }
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd(double epsilon) {
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * ((this.rX) * (this.rZ) + (this.rY * this.rW));
        double _t9 = 2.0 * ((this.rX) * (this.rW) - (this.rY * this.rZ));
        double _t10 = ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))));
        double _t12 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t12 < epsilon * ((_t9) * (_t9) + (_t12))) {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * ((this.rY) * (this.rW) - (this.rX * this.rZ)), ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t3))))), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(_t8, _t10), Math.atan2(2.0 * ((this.rX) * (this.rY) + (this.rZ * this.rW)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t3))))));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesYZX() {
        if (!Math.useFma()) return getEulerAnglesYZX_mulAdd();
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW);
        double _t8 = 2.0 * java.lang.Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < java.lang.Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rW, -(this.rY * this.rZ)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t0), 1.0)), Math.atan2(_t8, _t9), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        }
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd() {
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * ((this.rX) * (this.rY) + (this.rZ * this.rW));
        double _t8 = 2.0 * ((this.rY) * (this.rW) - (this.rX * this.rZ));
        double _t9 = ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        if (_t11 < ((_t7) * (_t7) + (_t11)) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(2.0 * ((this.rX) * (this.rZ) + (this.rY * this.rW)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))))), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(2.0 * ((this.rX) * (this.rW) - (this.rY * this.rZ)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t0))))), Math.atan2(_t8, _t9), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must not
     * be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-15}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesYZX(double epsilon) {
        if (!Math.useFma()) return getEulerAnglesYZX_mulAdd(epsilon);
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW);
        double _t8 = 2.0 * java.lang.Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < epsilon * java.lang.Math.fma(_t7, _t7, _t11)) {
            return new Double3(0.0, Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rZ, this.rY * this.rW), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rW, -(this.rY * this.rZ)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t0), 1.0)), Math.atan2(_t8, _t9), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        }
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd(double epsilon) {
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * ((this.rX) * (this.rY) + (this.rZ * this.rW));
        double _t8 = 2.0 * ((this.rY) * (this.rW) - (this.rX * this.rZ));
        double _t9 = ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        if (_t11 < epsilon * ((_t7) * (_t7) + (_t11))) {
            return new Double3(0.0, Math.atan2(2.0 * ((this.rX) * (this.rZ) + (this.rY * this.rW)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))))), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(2.0 * ((this.rX) * (this.rW) - (this.rY * this.rZ)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t0))))), Math.atan2(_t8, _t9), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesZXY() {
        if (!Math.useFma()) return getEulerAnglesZXY_mulAdd();
        double _t1 = this.rZ * this.rZ;
        double _t7 = 2.0 * java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ);
        double _t8 = 2.0 * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t1), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < java.lang.Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), 0.0, Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t1), 1.0)));
        } else {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * java.lang.Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t8, _t9));
        }
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd() {
        double _t1 = this.rZ * this.rZ;
        double _t7 = 2.0 * ((this.rX) * (this.rW) + (this.rY * this.rZ));
        double _t8 = 2.0 * ((this.rZ) * (this.rW) - (this.rX * this.rY));
        double _t9 = ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t1))));
        double _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        if (_t11 < ((_t7) * (_t7) + (_t11)) * 1.0E-15) {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), 0.0, Math.atan2(2.0 * ((this.rX) * (this.rY) + (this.rZ * this.rW)), ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t1))))));
        } else {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * ((this.rY) * (this.rW) - (this.rX * this.rZ)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))))), Math.atan2(_t8, _t9));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must not
     * be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-15}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesZXY(double epsilon) {
        if (!Math.useFma()) return getEulerAnglesZXY_mulAdd(epsilon);
        double _t1 = this.rZ * this.rZ;
        double _t7 = 2.0 * java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ);
        double _t8 = 2.0 * java.lang.Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t1), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < epsilon * java.lang.Math.fma(_t7, _t7, _t11)) {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), 0.0, Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t1), 1.0)));
        } else {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * java.lang.Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t8, _t9));
        }
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd(double epsilon) {
        double _t1 = this.rZ * this.rZ;
        double _t7 = 2.0 * ((this.rX) * (this.rW) + (this.rY * this.rZ));
        double _t8 = 2.0 * ((this.rZ) * (this.rW) - (this.rX * this.rY));
        double _t9 = ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t1))));
        double _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        if (_t11 < epsilon * ((_t7) * (_t7) + (_t11))) {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), 0.0, Math.atan2(2.0 * ((this.rX) * (this.rY) + (this.rZ * this.rW)), ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t1))))));
        } else {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * ((this.rY) * (this.rW) - (this.rX * this.rZ)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))))), Math.atan2(_t8, _t9));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesZYX() {
        if (!Math.useFma()) return getEulerAnglesZYX_mulAdd();
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW);
        double _t8 = 2.0 * java.lang.Math.fma(this.rY, this.rW, -this.rX * this.rZ);
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < java.lang.Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * java.lang.Math.fma(this.rZ, this.rW, -this.rX * this.rY), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t0), 1.0)));
        } else {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(_t7, _t9));
        }
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd() {
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * ((this.rX) * (this.rY) + (this.rZ * this.rW));
        double _t8 = 2.0 * ((this.rY) * (this.rW) - (this.rX * this.rZ));
        double _t9 = ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < ((_t8) * (_t8) + (_t11)) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * ((this.rZ) * (this.rW) - (this.rX * this.rY)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t0))))));
        } else {
            return new Double3(Math.atan2(2.0 * ((this.rX) * (this.rW) + (this.rY * this.rZ)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))))), Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(_t7, _t9));
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * Valid input: the rotation of this transform must have unit length; {@code epsilon} must not
     * be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-15}
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesZYX(double epsilon) {
        if (!Math.useFma()) return getEulerAnglesZYX_mulAdd(epsilon);
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * java.lang.Math.fma(this.rX, this.rY, this.rZ * this.rW);
        double _t8 = 2.0 * java.lang.Math.fma(this.rY, this.rW, -this.rX * this.rZ);
        double _t9 = java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rY, this.rY, _t0), 1.0);
        double _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < epsilon * java.lang.Math.fma(_t8, _t8, _t11)) {
            return new Double3(0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * java.lang.Math.fma(this.rZ, this.rW, -this.rX * this.rY), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, _t0), 1.0)));
        } else {
            return new Double3(Math.atan2(2.0 * java.lang.Math.fma(this.rX, this.rW, this.rY * this.rZ), java.lang.Math.fma(-2.0, java.lang.Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(_t7, _t9));
        }
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd(double epsilon) {
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * ((this.rX) * (this.rY) + (this.rZ * this.rW));
        double _t8 = 2.0 * ((this.rY) * (this.rW) - (this.rX * this.rZ));
        double _t9 = ((1.0) - (2.0) * (((this.rY) * (this.rY) + (_t0))));
        double _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < epsilon * ((_t8) * (_t8) + (_t11))) {
            return new Double3(0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * ((this.rZ) * (this.rW) - (this.rX * this.rY)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (_t0))))));
        } else {
            return new Double3(Math.atan2(2.0 * ((this.rX) * (this.rW) + (this.rY * this.rZ)), ((1.0) - (2.0) * (((this.rX) * (this.rX) + (this.rY * this.rY))))), Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(_t7, _t9));
        }
    }

    /**
     * Get the rotation of this transform, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting quaternion
     */
    public DoubleQuat getRotation() {
        return new DoubleQuat(this.rX, this.rY, this.rZ, this.rW);
    }

    /**
     * Get the scaling factors of this transform, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getScale() {
        return new Double3(this.sX, this.sY, this.sZ);
    }

    /**
     * Get the translation of this transform, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Double3 getTranslation() {
        return new Double3(this.tX, this.tY, this.tZ);
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
    public static DoubleTransform makeRotationAxis(double angle, Double3 axis) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleTransform(0.0, 0.0, 0.0, axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0), 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeRotationAxis(double angle, double axisX, double axisY, double axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleTransform(0.0, 0.0, 0.0, axisX * _t1, axisY * _t1, axisZ * _t1, Math.cosFromSin(_t1, _t0), 1.0, 1.0, 1.0);
    }

    /**
     * Create a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting transform
     */
    public static DoubleTransform makeRotationX(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleTransform(0.0, 0.0, 0.0, _t1, 0.0, 0.0, Math.cosFromSin(_t1, _t0), 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeRotationXYZ(double angleX, double angleY, double angleZ) {
        if (!Math.useFma()) return makeRotationXYZ_mulAdd(angleX, angleY, angleZ);
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
        return new DoubleTransform(0.0, 0.0, 0.0, java.lang.Math.fma(_t10, _t7, _t11 * _t5), java.lang.Math.fma(_t11, _t7, _t10 * -_t5), java.lang.Math.fma(_t9, _t7, _t12 * _t5), java.lang.Math.fma(_t12, _t7, _t9 * -_t5), 1.0, 1.0, 1.0);
    }

    /** {@code makeRotationXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleTransform makeRotationXYZ_mulAdd(double angleX, double angleY, double angleZ) {
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
        return new DoubleTransform(0.0, 0.0, 0.0, ((_t10) * (_t7) + (_t11 * _t5)), ((_t11) * (_t7) - (_t10 * _t5)), ((_t9) * (_t7) + (_t12 * _t5)), ((_t12) * (_t7) - (_t9 * _t5)), 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeRotationXZY(double angleX, double angleZ, double angleY) {
        if (!Math.useFma()) return makeRotationXZY_mulAdd(angleX, angleZ, angleY);
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
        return new DoubleTransform(0.0, 0.0, 0.0, java.lang.Math.fma(_t10, _t7, -(_t11 * _t5)), java.lang.Math.fma(_t12, _t5, -(_t9 * _t7)), java.lang.Math.fma(_t10, _t5, _t11 * _t7), java.lang.Math.fma(_t9, _t5, _t12 * _t7), 1.0, 1.0, 1.0);
    }

    /** {@code makeRotationXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleTransform makeRotationXZY_mulAdd(double angleX, double angleZ, double angleY) {
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
        return new DoubleTransform(0.0, 0.0, 0.0, ((_t10) * (_t7) - (_t11 * _t5)), ((_t12) * (_t5) - (_t9 * _t7)), ((_t10) * (_t5) + (_t11 * _t7)), ((_t9) * (_t5) + (_t12 * _t7)), 1.0, 1.0, 1.0);
    }

    /**
     * Create a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting transform
     */
    public static DoubleTransform makeRotationY(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleTransform(0.0, 0.0, 0.0, 0.0, _t1, 0.0, Math.cosFromSin(_t1, _t0), 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeRotationYXZ(double angleY, double angleX, double angleZ) {
        if (!Math.useFma()) return makeRotationYXZ_mulAdd(angleY, angleX, angleZ);
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
        return new DoubleTransform(0.0, 0.0, 0.0, java.lang.Math.fma(_t10, _t7, _t11 * _t5), java.lang.Math.fma(_t11, _t7, -(_t10 * _t5)), java.lang.Math.fma(_t12, _t5, -(_t9 * _t7)), java.lang.Math.fma(_t9, _t5, _t12 * _t7), 1.0, 1.0, 1.0);
    }

    /** {@code makeRotationYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleTransform makeRotationYXZ_mulAdd(double angleY, double angleX, double angleZ) {
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
        return new DoubleTransform(0.0, 0.0, 0.0, ((_t10) * (_t7) + (_t11 * _t5)), ((_t11) * (_t7) - (_t10 * _t5)), ((_t12) * (_t5) - (_t9 * _t7)), ((_t9) * (_t5) + (_t12 * _t7)), 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeRotationYZX(double angleY, double angleZ, double angleX) {
        if (!Math.useFma()) return makeRotationYZX_mulAdd(angleY, angleZ, angleX);
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
        return new DoubleTransform(0.0, 0.0, 0.0, java.lang.Math.fma(_t9, _t6, _t12 * _t5), java.lang.Math.fma(_t10, _t6, _t11 * _t5), java.lang.Math.fma(_t11, _t6, _t10 * -_t5), java.lang.Math.fma(_t12, _t6, _t9 * -_t5), 1.0, 1.0, 1.0);
    }

    /** {@code makeRotationYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleTransform makeRotationYZX_mulAdd(double angleY, double angleZ, double angleX) {
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
        return new DoubleTransform(0.0, 0.0, 0.0, ((_t9) * (_t6) + (_t12 * _t5)), ((_t10) * (_t6) + (_t11 * _t5)), ((_t11) * (_t6) - (_t10 * _t5)), ((_t12) * (_t6) - (_t9 * _t5)), 1.0, 1.0, 1.0);
    }

    /**
     * Create a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting transform
     */
    public static DoubleTransform makeRotationZ(double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        return new DoubleTransform(0.0, 0.0, 0.0, 0.0, 0.0, _t1, Math.cosFromSin(_t1, _t0), 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeRotationZXY(double angleZ, double angleX, double angleY) {
        if (!Math.useFma()) return makeRotationZXY_mulAdd(angleZ, angleX, angleY);
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
        return new DoubleTransform(0.0, 0.0, 0.0, java.lang.Math.fma(_t10, _t7, _t11 * -_t5), java.lang.Math.fma(_t9, _t7, _t12 * _t5), java.lang.Math.fma(_t10, _t5, _t11 * _t7), java.lang.Math.fma(_t12, _t7, _t9 * -_t5), 1.0, 1.0, 1.0);
    }

    /** {@code makeRotationZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleTransform makeRotationZXY_mulAdd(double angleZ, double angleX, double angleY) {
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
        return new DoubleTransform(0.0, 0.0, 0.0, ((_t10) * (_t7) - (_t11 * _t5)), ((_t9) * (_t7) + (_t12 * _t5)), ((_t10) * (_t5) + (_t11 * _t7)), ((_t12) * (_t7) - (_t9 * _t5)), 1.0, 1.0, 1.0);
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
    public static DoubleTransform makeRotationZYX(double angleZ, double angleY, double angleX) {
        if (!Math.useFma()) return makeRotationZYX_mulAdd(angleZ, angleY, angleX);
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
        return new DoubleTransform(0.0, 0.0, 0.0, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), java.lang.Math.fma(_t10, _t8, _t11 * _t5), java.lang.Math.fma(_t11, _t8, -(_t10 * _t5)), java.lang.Math.fma(_t9, _t5, _t12 * _t8), 1.0, 1.0, 1.0);
    }

    /** {@code makeRotationZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static DoubleTransform makeRotationZYX_mulAdd(double angleZ, double angleY, double angleX) {
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
        return new DoubleTransform(0.0, 0.0, 0.0, ((_t12) * (_t5) - (_t9 * _t8)), ((_t10) * (_t8) + (_t11 * _t5)), ((_t11) * (_t8) - (_t10 * _t5)), ((_t9) * (_t5) + (_t12 * _t8)), 1.0, 1.0, 1.0);
    }

    /**
     * Create a scaling transformation that scales by {@code scale}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scale the scale factors
     * @return the resulting transform
     */
    public static DoubleTransform makeScaling(Double3 scale) {
        double scaleX = scale.x();
        double scaleY = scale.y();
        double scaleZ = scale.z();
        return new DoubleTransform(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, scaleX, scaleY, scaleZ);
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
    public static DoubleTransform makeScaling(double scaleX, double scaleY, double scaleZ) {
        return new DoubleTransform(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, scaleX, scaleY, scaleZ);
    }

    /**
     * Create a scaling transformation that scales by {@code scale}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scale the scale factor
     * @return the resulting transform
     */
    public static DoubleTransform makeScaling(double scale) {
        return new DoubleTransform(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, scale, scale, scale);
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
    public DoubleTransform rotate(DoubleQuat rotation) {
        double rotationX = rotation.x();
        double rotationY = rotation.y();
        double rotationZ = rotation.z();
        double rotationW = rotation.w();
        if (!Math.useFma()) return rotate_mulAdd(rotationX, rotationY, rotationZ, rotationW);
        return new DoubleTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(rotationX, this.rW, rotationW * this.rX) + java.lang.Math.fma(rotationZ, this.rY, -(rotationY * this.rZ)), java.lang.Math.fma(rotationY, this.rW, rotationW * this.rY) + java.lang.Math.fma(rotationX, this.rZ, rotationZ * -this.rX), java.lang.Math.fma(rotationZ, this.rW, rotationW * this.rZ) + java.lang.Math.fma(rotationY, this.rX, -(rotationX * this.rY)), java.lang.Math.fma(rotationW, this.rW, rotationX * -this.rX) - java.lang.Math.fma(rotationY, this.rY, rotationZ * this.rZ), this.sX, this.sY, this.sZ);
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
    public DoubleTransform rotate(double rotationX, double rotationY, double rotationZ, double rotationW) {
        if (!Math.useFma()) return rotate_mulAdd(rotationX, rotationY, rotationZ, rotationW);
        return new DoubleTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(rotationX, this.rW, rotationW * this.rX) + java.lang.Math.fma(rotationZ, this.rY, -(rotationY * this.rZ)), java.lang.Math.fma(rotationY, this.rW, rotationW * this.rY) + java.lang.Math.fma(rotationX, this.rZ, rotationZ * -this.rX), java.lang.Math.fma(rotationZ, this.rW, rotationW * this.rZ) + java.lang.Math.fma(rotationY, this.rX, -(rotationX * this.rY)), java.lang.Math.fma(rotationW, this.rW, rotationX * -this.rX) - java.lang.Math.fma(rotationY, this.rY, rotationZ * this.rZ), this.sX, this.sY, this.sZ);
    }

    /** {@code rotate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform rotate_mulAdd(double rotationX, double rotationY, double rotationZ, double rotationW) {
        return new DoubleTransform(this.tX, this.tY, this.tZ, ((rotationX) * (this.rW) + (rotationW * this.rX)) + ((rotationZ) * (this.rY) - (rotationY * this.rZ)), ((rotationY) * (this.rW) + (rotationW * this.rY)) + ((rotationX) * (this.rZ) - (rotationZ * this.rX)), ((rotationZ) * (this.rW) + (rotationW * this.rZ)) + ((rotationY) * (this.rX) - (rotationX * this.rY)), ((rotationW) * (this.rW) - (rotationX * this.rX)) - ((rotationY) * (this.rY) + (rotationZ * this.rZ)), this.sX, this.sY, this.sZ);
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
    public DoubleTransform rotateAxis(double angle, Double3 axis) {
        return rotateAxis(angle, axis.x(), axis.y(), axis.z());
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
    public DoubleTransform rotateAxis(double angle, double axisX, double axisY, double axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        return new DoubleTransform(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t5, this.rW * _t2) + Math.fma(this.rY, _t3, -(this.rZ * _t4)), Math.fma(this.rY, _t5, this.rW * _t4) + Math.fma(this.rZ, _t2, -this.rX * _t3), Math.fma(this.rZ, _t5, this.rW * _t3) + Math.fma(this.rX, _t4, -(this.rY * _t2)), Math.fma(this.rW, _t5, -this.rX * _t2) - Math.fma(this.rY, _t4, this.rZ * _t3), this.sX, this.sY, this.sZ);
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
    public DoubleTransform rotateX(double angle) {
        if (Math.useFma()) {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t2, this.rW * _t1), java.lang.Math.fma(this.rY, _t2, this.rZ * _t1), java.lang.Math.fma(this.rZ, _t2, -(this.rY * _t1)), java.lang.Math.fma(this.rW, _t2, -(this.rX * _t1)), this.sX, this.sY, this.sZ);
        } else {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleTransform(this.tX, this.tY, this.tZ, ((this.rX) * (_t2) + (this.rW * _t1)), ((this.rY) * (_t2) + (this.rZ * _t1)), ((this.rZ) * (_t2) - (this.rY * _t1)), ((this.rW) * (_t2) - (this.rX * _t1)), this.sX, this.sY, this.sZ);
        }
    }

    /** Private tail of {@code rotateXYZ}; reached only through it. */
    private DoubleTransform rotateXYZ_s361a4ff5_tail_fma(double _t11, double _t8, double _t10, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = java.lang.Math.fma(_t11, _t8, _t10 * -_t5);
        return new DoubleTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t21, this.rW * _t19) + java.lang.Math.fma(this.rY, _t20, -(this.rZ * _t22)), java.lang.Math.fma(this.rY, _t21, this.rW * _t22) + java.lang.Math.fma(this.rZ, _t19, -this.rX * _t20), java.lang.Math.fma(this.rZ, _t21, this.rW * _t20) + java.lang.Math.fma(this.rX, _t22, -(this.rY * _t19)), java.lang.Math.fma(this.rW, _t21, -this.rX * _t19) - java.lang.Math.fma(this.rY, _t22, this.rZ * _t20), this.sX, this.sY, this.sZ);
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of {@code rotateXYZ}
     * and {@code rotateXZY}; reached only through them.
     */
    private DoubleTransform rotateXYZ_s361a4ff5_tail_mulAdd(double _t11, double _t8, double _t10, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = ((_t11) * (_t8) - (_t10 * _t5));
        return new DoubleTransform(this.tX, this.tY, this.tZ, ((this.rX) * (_t21) + (this.rW * _t19)) + ((this.rY) * (_t20) - (this.rZ * _t22)), ((this.rY) * (_t21) + (this.rW * _t22)) + ((this.rZ) * (_t19) - (this.rX * _t20)), ((this.rZ) * (_t21) + (this.rW * _t20)) + ((this.rX) * (_t22) - (this.rY * _t19)), ((this.rW) * (_t21) - (this.rX * _t19)) - ((this.rY) * (_t22) + (this.rZ * _t20)), this.sX, this.sY, this.sZ);
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
    public DoubleTransform rotateXYZ(double angleX, double angleY, double angleZ) {
        if (Math.useFma()) {
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
            return rotateXYZ_s361a4ff5_tail_fma(_t11, _t8, _t10, _t5, java.lang.Math.fma(_t14, _t8, _t9 * -_t5), java.lang.Math.fma(_t10, _t8, _t11 * _t5), java.lang.Math.fma(_t9, _t8, _t14 * _t5));
        } else {
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
            return rotateXYZ_s361a4ff5_tail_mulAdd(_t11, _t8, _t10, _t5, ((_t14) * (_t8) - (_t9 * _t5)), ((_t10) * (_t8) + (_t11 * _t5)), ((_t9) * (_t8) + (_t14 * _t5)));
        }
    }

    /**
     * Private tail of {@code rotateXZY}. Shared by the identical private paths of {@code rotateXZY}
     * and {@code rotateYXZ}; reached only through them.
     */
    private DoubleTransform rotateXZY_s17ac2dd3_tail_fma(double _t12, double _t5, double _t9, double _t8, double _t19, double _t21, double _t20) {
        double _t22 = java.lang.Math.fma(_t12, _t5, -(_t9 * _t8));
        return new DoubleTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t19, this.rW * _t21) + java.lang.Math.fma(this.rY, _t20, -(this.rZ * _t22)), java.lang.Math.fma(this.rY, _t19, this.rW * _t22) + java.lang.Math.fma(this.rZ, _t21, -this.rX * _t20), java.lang.Math.fma(this.rZ, _t19, this.rW * _t20) + java.lang.Math.fma(this.rX, _t22, -(this.rY * _t21)), java.lang.Math.fma(this.rW, _t19, -this.rX * _t21) - java.lang.Math.fma(this.rY, _t22, this.rZ * _t20), this.sX, this.sY, this.sZ);
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
    public DoubleTransform rotateXZY(double angleX, double angleZ, double angleY) {
        if (Math.useFma()) {
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
            return rotateXZY_s17ac2dd3_tail_fma(_t12, _t5, _t9, _t8, java.lang.Math.fma(_t9, _t5, _t12 * _t8), java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), java.lang.Math.fma(_t10, _t5, _t11 * _t8));
        } else {
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
            return rotateXYZ_s361a4ff5_tail_mulAdd(_t12, _t5, _t9, _t8, ((_t9) * (_t5) + (_t12 * _t8)), ((_t10) * (_t8) - (_t11 * _t5)), ((_t10) * (_t5) + (_t11 * _t8)));
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
    public DoubleTransform rotateY(double angle) {
        if (Math.useFma()) {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t2, -(this.rZ * _t1)), java.lang.Math.fma(this.rY, _t2, this.rW * _t1), java.lang.Math.fma(this.rX, _t1, this.rZ * _t2), java.lang.Math.fma(this.rW, _t2, -(this.rY * _t1)), this.sX, this.sY, this.sZ);
        } else {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleTransform(this.tX, this.tY, this.tZ, ((this.rX) * (_t2) - (this.rZ * _t1)), ((this.rY) * (_t2) + (this.rW * _t1)), ((this.rX) * (_t1) + (this.rZ * _t2)), ((this.rW) * (_t2) - (this.rY * _t1)), this.sX, this.sY, this.sZ);
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
    public DoubleTransform rotateYXZ(double angleY, double angleX, double angleZ) {
        if (Math.useFma()) {
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
            return rotateXZY_s17ac2dd3_tail_fma(_t11, _t8, _t10, _t5, java.lang.Math.fma(_t9, _t5, _t12 * _t8), java.lang.Math.fma(_t10, _t8, _t11 * _t5), java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)));
        } else {
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
            return rotateXYZ_s361a4ff5_tail_mulAdd(_t11, _t8, _t10, _t5, ((_t9) * (_t5) + (_t12 * _t8)), ((_t10) * (_t8) + (_t11 * _t5)), ((_t12) * (_t5) - (_t9 * _t8)));
        }
    }

    /** Private tail of {@code rotateYZX}; reached only through it. */
    private DoubleTransform rotateYZX_s2c94f613_tail_fma(double _t10, double _t8, double _t11, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = java.lang.Math.fma(_t10, _t8, _t11 * -_t5);
        return new DoubleTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t21, this.rW * _t19) + java.lang.Math.fma(this.rY, _t22, -(this.rZ * _t20)), java.lang.Math.fma(this.rY, _t21, this.rW * _t20) + java.lang.Math.fma(this.rZ, _t19, -this.rX * _t22), java.lang.Math.fma(this.rZ, _t21, this.rW * _t22) + java.lang.Math.fma(this.rX, _t20, -(this.rY * _t19)), java.lang.Math.fma(this.rW, _t21, -this.rX * _t19) - java.lang.Math.fma(this.rY, _t20, this.rZ * _t22), this.sX, this.sY, this.sZ);
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private DoubleTransform rotateYZX_s2c94f613_tail_mulAdd(double _t10, double _t8, double _t11, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        return new DoubleTransform(this.tX, this.tY, this.tZ, ((this.rX) * (_t21) + (this.rW * _t19)) + ((this.rY) * (_t22) - (this.rZ * _t20)), ((this.rY) * (_t21) + (this.rW * _t20)) + ((this.rZ) * (_t19) - (this.rX * _t22)), ((this.rZ) * (_t21) + (this.rW * _t22)) + ((this.rX) * (_t20) - (this.rY * _t19)), ((this.rW) * (_t21) - (this.rX * _t19)) - ((this.rY) * (_t20) + (this.rZ * _t22)), this.sX, this.sY, this.sZ);
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
    public DoubleTransform rotateYZX(double angleY, double angleZ, double angleX) {
        if (Math.useFma()) {
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
            return rotateYZX_s2c94f613_tail_fma(_t10, _t8, _t11, _t5, java.lang.Math.fma(_t14, _t8, _t9 * -_t5), java.lang.Math.fma(_t9, _t8, _t14 * _t5), java.lang.Math.fma(_t11, _t8, _t10 * _t5));
        } else {
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
            return rotateYZX_s2c94f613_tail_mulAdd(_t10, _t8, _t11, _t5, ((_t14) * (_t8) - (_t9 * _t5)), ((_t9) * (_t8) + (_t14 * _t5)), ((_t11) * (_t8) + (_t10 * _t5)));
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
    public DoubleTransform rotateZ(double angle) {
        if (Math.useFma()) {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t2, this.rY * _t1), java.lang.Math.fma(this.rY, _t2, -(this.rX * _t1)), java.lang.Math.fma(this.rZ, _t2, this.rW * _t1), java.lang.Math.fma(this.rW, _t2, -(this.rZ * _t1)), this.sX, this.sY, this.sZ);
        } else {
            double _t0 = 0.5 * angle;
            double _t1 = Math.sin(_t0);
            double _t2 = Math.cosFromSin(_t1, _t0);
            return new DoubleTransform(this.tX, this.tY, this.tZ, ((this.rX) * (_t2) + (this.rY * _t1)), ((this.rY) * (_t2) - (this.rX * _t1)), ((this.rZ) * (_t2) + (this.rW * _t1)), ((this.rW) * (_t2) - (this.rZ * _t1)), this.sX, this.sY, this.sZ);
        }
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private DoubleTransform rotateZXY_s7e5a0297_tail_fma(double _t10, double _t8, double _t11, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = java.lang.Math.fma(_t10, _t8, _t11 * -_t5);
        return new DoubleTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t21, this.rW * _t22) + java.lang.Math.fma(this.rY, _t19, -(this.rZ * _t20)), java.lang.Math.fma(this.rY, _t21, this.rW * _t20) + java.lang.Math.fma(this.rZ, _t22, -this.rX * _t19), java.lang.Math.fma(this.rZ, _t21, this.rW * _t19) + java.lang.Math.fma(this.rX, _t20, -(this.rY * _t22)), java.lang.Math.fma(this.rW, _t21, -this.rX * _t22) - java.lang.Math.fma(this.rY, _t20, this.rZ * _t19), this.sX, this.sY, this.sZ);
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private DoubleTransform rotateZXY_s7e5a0297_tail_mulAdd(double _t10, double _t8, double _t11, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = ((_t10) * (_t8) - (_t11 * _t5));
        return new DoubleTransform(this.tX, this.tY, this.tZ, ((this.rX) * (_t21) + (this.rW * _t22)) + ((this.rY) * (_t19) - (this.rZ * _t20)), ((this.rY) * (_t21) + (this.rW * _t20)) + ((this.rZ) * (_t22) - (this.rX * _t19)), ((this.rZ) * (_t21) + (this.rW * _t19)) + ((this.rX) * (_t20) - (this.rY * _t22)), ((this.rW) * (_t21) - (this.rX * _t22)) - ((this.rY) * (_t20) + (this.rZ * _t19)), this.sX, this.sY, this.sZ);
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
    public DoubleTransform rotateZXY(double angleZ, double angleX, double angleY) {
        if (Math.useFma()) {
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
            return rotateZXY_s7e5a0297_tail_fma(_t10, _t8, _t11, _t5, java.lang.Math.fma(_t14, _t8, _t9 * -_t5), java.lang.Math.fma(_t10, _t5, _t11 * _t8), java.lang.Math.fma(_t9, _t8, _t14 * _t5));
        } else {
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
            return rotateZXY_s7e5a0297_tail_mulAdd(_t10, _t8, _t11, _t5, ((_t14) * (_t8) - (_t9 * _t5)), ((_t10) * (_t5) + (_t11 * _t8)), ((_t9) * (_t8) + (_t14 * _t5)));
        }
    }

    /** Private tail of {@code rotateZYX}; reached only through it. */
    private DoubleTransform rotateZYX_s5febe075_tail_fma(double _t10, double _t8, double _t11, double _t5, double _t19, double _t21, double _t20) {
        double _t22 = java.lang.Math.fma(_t10, _t8, -(_t11 * _t5));
        return new DoubleTransform(this.tX, this.tY, this.tZ, java.lang.Math.fma(this.rX, _t19, this.rW * _t21) + java.lang.Math.fma(this.rY, _t22, -(this.rZ * _t20)), java.lang.Math.fma(this.rY, _t19, this.rW * _t20) + java.lang.Math.fma(this.rZ, _t21, -this.rX * _t22), java.lang.Math.fma(this.rZ, _t19, this.rW * _t22) + java.lang.Math.fma(this.rX, _t20, -(this.rY * _t21)), java.lang.Math.fma(this.rW, _t19, -this.rX * _t21) - java.lang.Math.fma(this.rY, _t20, this.rZ * _t22), this.sX, this.sY, this.sZ);
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
    public DoubleTransform rotateZYX(double angleZ, double angleY, double angleX) {
        if (Math.useFma()) {
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
            return rotateZYX_s5febe075_tail_fma(_t10, _t8, _t11, _t5, java.lang.Math.fma(_t9, _t5, _t12 * _t8), java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), java.lang.Math.fma(_t11, _t8, _t10 * _t5));
        } else {
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
            return rotateYZX_s2c94f613_tail_mulAdd(_t10, _t8, _t11, _t5, ((_t9) * (_t5) + (_t12 * _t8)), ((_t12) * (_t5) - (_t9 * _t8)), ((_t11) * (_t8) + (_t10 * _t5)));
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
    public DoubleTransform scale(Double3 scale) {
        double scaleX = scale.x();
        double scaleY = scale.y();
        double scaleZ = scale.z();
        return new DoubleTransform(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW, scaleX * this.sX, scaleY * this.sY, scaleZ * this.sZ);
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
    public DoubleTransform scale(double scaleX, double scaleY, double scaleZ) {
        return new DoubleTransform(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW, scaleX * this.sX, scaleY * this.sY, scaleZ * this.sZ);
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
    public DoubleTransform scale(double scale) {
        return new DoubleTransform(this.tX, this.tY, this.tZ, this.rX, this.rY, this.rZ, this.rW, scale * this.sX, scale * this.sY, scale * this.sZ);
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
    public DoubleTransform translate(Double3 translation) {
        double translationX = translation.x();
        double translationY = translation.y();
        double translationZ = translation.z();
        if (!Math.useFma()) return translate_mulAdd(translationX, translationY, translationZ);
        double _t0 = this.sY * translationY;
        double _t1 = this.sX * translationX;
        double _t2 = this.sZ * translationZ;
        double _t12 = 2.0 * java.lang.Math.fma(this.rX, _t0, -(this.rY * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(this.rZ, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(this.rY, _t2, -(this.rZ * _t0));
        return new DoubleTransform(java.lang.Math.fma(this.rY, _t12, java.lang.Math.fma(-this.rZ, _t13, java.lang.Math.fma(this.rW, _t14, java.lang.Math.fma(this.sX, translationX, this.tX)))), java.lang.Math.fma(this.rZ, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, java.lang.Math.fma(this.sY, translationY, this.tY)))), java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rY, _t14, java.lang.Math.fma(this.rW, _t12, java.lang.Math.fma(this.sZ, translationZ, this.tZ)))), this.rX, this.rY, this.rZ, this.rW, this.sX, this.sY, this.sZ);
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
    public DoubleTransform translate(double translationX, double translationY, double translationZ) {
        if (!Math.useFma()) return translate_mulAdd(translationX, translationY, translationZ);
        double _t0 = this.sY * translationY;
        double _t1 = this.sX * translationX;
        double _t2 = this.sZ * translationZ;
        double _t12 = 2.0 * java.lang.Math.fma(this.rX, _t0, -(this.rY * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(this.rZ, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(this.rY, _t2, -(this.rZ * _t0));
        return new DoubleTransform(java.lang.Math.fma(this.rY, _t12, java.lang.Math.fma(-this.rZ, _t13, java.lang.Math.fma(this.rW, _t14, java.lang.Math.fma(this.sX, translationX, this.tX)))), java.lang.Math.fma(this.rZ, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, java.lang.Math.fma(this.sY, translationY, this.tY)))), java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rY, _t14, java.lang.Math.fma(this.rW, _t12, java.lang.Math.fma(this.sZ, translationZ, this.tZ)))), this.rX, this.rY, this.rZ, this.rW, this.sX, this.sY, this.sZ);
    }

    /** {@code translate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleTransform translate_mulAdd(double translationX, double translationY, double translationZ) {
        double _t0 = this.sY * translationY;
        double _t1 = this.sX * translationX;
        double _t2 = this.sZ * translationZ;
        double _t12 = 2.0 * ((this.rX) * (_t0) - (this.rY * _t1));
        double _t13 = 2.0 * ((this.rZ) * (_t1) - (this.rX * _t2));
        double _t14 = 2.0 * ((this.rY) * (_t2) - (this.rZ * _t0));
        return new DoubleTransform(((this.rY) * (_t12) + (((((this.rW) * (_t14) + (((this.sX) * (translationX) + (this.tX))))) - (this.rZ) * (_t13)))), ((this.rZ) * (_t14) + (((((this.rW) * (_t13) + (((this.sY) * (translationY) + (this.tY))))) - (this.rX) * (_t12)))), ((this.rX) * (_t13) + (((((this.rW) * (_t12) + (((this.sZ) * (translationZ) + (this.tZ))))) - (this.rY) * (_t14)))), this.rX, this.rY, this.rZ, this.rW, this.sX, this.sY, this.sZ);
    }

    /**
     * Transform {@code v} by this transform, returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @param v the vector to transform
     * @return the resulting vector
     */
    public Double3 transform(Double3 v) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        if (!Math.useFma()) return transform_mulAdd(vX, vY, vZ);
        double _t0 = this.sY * vY;
        double _t1 = this.sX * vX;
        double _t2 = this.sZ * vZ;
        double _t12 = 2.0 * java.lang.Math.fma(this.rX, _t0, -(this.rY * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(this.rZ, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(this.rY, _t2, -(this.rZ * _t0));
        return new Double3(java.lang.Math.fma(this.rY, _t12, java.lang.Math.fma(-this.rZ, _t13, java.lang.Math.fma(this.rW, _t14, java.lang.Math.fma(this.sX, vX, this.tX)))), java.lang.Math.fma(this.rZ, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, java.lang.Math.fma(this.sY, vY, this.tY)))), java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rY, _t14, java.lang.Math.fma(this.rW, _t12, java.lang.Math.fma(this.sZ, vZ, this.tZ)))));
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
    public Double3 transform(double vX, double vY, double vZ) {
        if (!Math.useFma()) return transform_mulAdd(vX, vY, vZ);
        double _t0 = this.sY * vY;
        double _t1 = this.sX * vX;
        double _t2 = this.sZ * vZ;
        double _t12 = 2.0 * java.lang.Math.fma(this.rX, _t0, -(this.rY * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(this.rZ, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(this.rY, _t2, -(this.rZ * _t0));
        return new Double3(java.lang.Math.fma(this.rY, _t12, java.lang.Math.fma(-this.rZ, _t13, java.lang.Math.fma(this.rW, _t14, java.lang.Math.fma(this.sX, vX, this.tX)))), java.lang.Math.fma(this.rZ, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, java.lang.Math.fma(this.sY, vY, this.tY)))), java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rY, _t14, java.lang.Math.fma(this.rW, _t12, java.lang.Math.fma(this.sZ, vZ, this.tZ)))));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transform_mulAdd(double vX, double vY, double vZ) {
        double _t0 = this.sY * vY;
        double _t1 = this.sX * vX;
        double _t2 = this.sZ * vZ;
        double _t12 = 2.0 * ((this.rX) * (_t0) - (this.rY * _t1));
        double _t13 = 2.0 * ((this.rZ) * (_t1) - (this.rX * _t2));
        double _t14 = 2.0 * ((this.rY) * (_t2) - (this.rZ * _t0));
        return new Double3(((this.rY) * (_t12) + (((((this.rW) * (_t14) + (((this.sX) * (vX) + (this.tX))))) - (this.rZ) * (_t13)))), ((this.rZ) * (_t14) + (((((this.rW) * (_t13) + (((this.sY) * (vY) + (this.tY))))) - (this.rX) * (_t12)))), ((this.rX) * (_t13) + (((((this.rW) * (_t12) + (((this.sZ) * (vZ) + (this.tZ))))) - (this.rY) * (_t14)))));
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
    public Double3 transformDirection(Double3 v) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        if (!Math.useFma()) return transformDirection_mulAdd(vX, vY, vZ);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * java.lang.Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Double3(java.lang.Math.fma(this.rY, _t9, java.lang.Math.fma(-this.rZ, _t10, java.lang.Math.fma(this.rW, _t11, vX))), java.lang.Math.fma(this.rZ, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vY))), java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rY, _t11, java.lang.Math.fma(this.rW, _t9, vZ))));
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
    public Double3 transformDirection(double vX, double vY, double vZ) {
        if (!Math.useFma()) return transformDirection_mulAdd(vX, vY, vZ);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * java.lang.Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Double3(java.lang.Math.fma(this.rY, _t9, java.lang.Math.fma(-this.rZ, _t10, java.lang.Math.fma(this.rW, _t11, vX))), java.lang.Math.fma(this.rZ, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vY))), java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rY, _t11, java.lang.Math.fma(this.rW, _t9, vZ))));
    }

    /** {@code transformDirection} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformDirection_mulAdd(double vX, double vY, double vZ) {
        double _t9 = 2.0 * ((this.rX) * (vY) - (this.rY * vX));
        double _t10 = 2.0 * ((this.rZ) * (vX) - (this.rX * vZ));
        double _t11 = 2.0 * ((this.rY) * (vZ) - (this.rZ * vY));
        return new Double3(((this.rY) * (_t9) + (((((this.rW) * (_t11) + (vX))) - (this.rZ) * (_t10)))), ((this.rZ) * (_t11) + (((((this.rW) * (_t10) + (vY))) - (this.rX) * (_t9)))), ((this.rX) * (_t10) + (((((this.rW) * (_t9) + (vZ))) - (this.rY) * (_t11)))));
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
    public Double3 transformDirectionInverse(Double3 v) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        if (!Math.useFma()) return transformDirectionInverse_mulAdd(vX, vY, vZ);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, vZ, -(this.rZ * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.rY, vX, -(this.rX * vY));
        double _t11 = 2.0 * java.lang.Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Double3(java.lang.Math.fma(this.rZ, _t9, java.lang.Math.fma(-this.rY, _t10, java.lang.Math.fma(this.rW, _t11, vX))), java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rZ, _t11, java.lang.Math.fma(this.rW, _t9, vY))), java.lang.Math.fma(this.rY, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vZ))));
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
    public Double3 transformDirectionInverse(double vX, double vY, double vZ) {
        if (!Math.useFma()) return transformDirectionInverse_mulAdd(vX, vY, vZ);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, vZ, -(this.rZ * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.rY, vX, -(this.rX * vY));
        double _t11 = 2.0 * java.lang.Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Double3(java.lang.Math.fma(this.rZ, _t9, java.lang.Math.fma(-this.rY, _t10, java.lang.Math.fma(this.rW, _t11, vX))), java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rZ, _t11, java.lang.Math.fma(this.rW, _t9, vY))), java.lang.Math.fma(this.rY, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vZ))));
    }

    /** {@code transformDirectionInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformDirectionInverse_mulAdd(double vX, double vY, double vZ) {
        double _t9 = 2.0 * ((this.rX) * (vZ) - (this.rZ * vX));
        double _t10 = 2.0 * ((this.rY) * (vX) - (this.rX * vY));
        double _t11 = 2.0 * ((this.rZ) * (vY) - (this.rY * vZ));
        return new Double3(((this.rZ) * (_t9) + (((((this.rW) * (_t11) + (vX))) - (this.rY) * (_t10)))), ((this.rX) * (_t10) + (((((this.rW) * (_t9) + (vY))) - (this.rZ) * (_t11)))), ((this.rY) * (_t11) + (((((this.rW) * (_t10) + (vZ))) - (this.rX) * (_t9)))));
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
    public Double3 transformInverse(Double3 p) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        if (!Math.useFma()) return transformInverse_mulAdd(pX, pY, pZ);
        double _t0 = pZ - this.tZ;
        double _t1 = pX - this.tX;
        double _t2 = pY - this.tY;
        double _t12 = 2.0 * java.lang.Math.fma(this.rX, _t0, -(this.rZ * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(this.rY, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(this.rZ, _t2, -(this.rY * _t0));
        return new Double3(java.lang.Math.fma(this.rZ, _t12, java.lang.Math.fma(-this.rY, _t13, java.lang.Math.fma(this.rW, _t14, _t1))) / this.sX, java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rZ, _t14, java.lang.Math.fma(this.rW, _t12, _t2))) / this.sY, java.lang.Math.fma(this.rY, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, _t0))) / this.sZ);
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
    public Double3 transformInverse(double pX, double pY, double pZ) {
        if (!Math.useFma()) return transformInverse_mulAdd(pX, pY, pZ);
        double _t0 = pZ - this.tZ;
        double _t1 = pX - this.tX;
        double _t2 = pY - this.tY;
        double _t12 = 2.0 * java.lang.Math.fma(this.rX, _t0, -(this.rZ * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(this.rY, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(this.rZ, _t2, -(this.rY * _t0));
        return new Double3(java.lang.Math.fma(this.rZ, _t12, java.lang.Math.fma(-this.rY, _t13, java.lang.Math.fma(this.rW, _t14, _t1))) / this.sX, java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rZ, _t14, java.lang.Math.fma(this.rW, _t12, _t2))) / this.sY, java.lang.Math.fma(this.rY, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, _t0))) / this.sZ);
    }

    /** {@code transformInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformInverse_mulAdd(double pX, double pY, double pZ) {
        double _t0 = pZ - this.tZ;
        double _t1 = pX - this.tX;
        double _t2 = pY - this.tY;
        double _t12 = 2.0 * ((this.rX) * (_t0) - (this.rZ * _t1));
        double _t13 = 2.0 * ((this.rY) * (_t1) - (this.rX * _t2));
        double _t14 = 2.0 * ((this.rZ) * (_t2) - (this.rY * _t0));
        return new Double3(((this.rZ) * (_t12) + (((((this.rW) * (_t14) + (_t1))) - (this.rY) * (_t13)))) / this.sX, ((this.rX) * (_t13) + (((((this.rW) * (_t12) + (_t2))) - (this.rZ) * (_t14)))) / this.sY, ((this.rY) * (_t14) + (((((this.rW) * (_t13) + (_t0))) - (this.rX) * (_t12)))) / this.sZ);
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
    public Double3 transformPosition(Double3 v) {
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
    public Double3 transformPosition(double vX, double vY, double vZ) {
        if (!Math.useFma()) return transform_mulAdd(vX, vY, vZ);
        double _t0 = this.sY * vY;
        double _t1 = this.sX * vX;
        double _t2 = this.sZ * vZ;
        double _t12 = 2.0 * java.lang.Math.fma(this.rX, _t0, -(this.rY * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(this.rZ, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(this.rY, _t2, -(this.rZ * _t0));
        return new Double3(java.lang.Math.fma(this.rY, _t12, java.lang.Math.fma(-this.rZ, _t13, java.lang.Math.fma(this.rW, _t14, java.lang.Math.fma(this.sX, vX, this.tX)))), java.lang.Math.fma(this.rZ, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, java.lang.Math.fma(this.sY, vY, this.tY)))), java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rY, _t14, java.lang.Math.fma(this.rW, _t12, java.lang.Math.fma(this.sZ, vZ, this.tZ)))));
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
    public Double3 transformPositionInverse(Double3 p) {
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
    public Double3 transformPositionInverse(double pX, double pY, double pZ) {
        if (!Math.useFma()) return transformInverse_mulAdd(pX, pY, pZ);
        double _t0 = pZ - this.tZ;
        double _t1 = pX - this.tX;
        double _t2 = pY - this.tY;
        double _t12 = 2.0 * java.lang.Math.fma(this.rX, _t0, -(this.rZ * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(this.rY, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(this.rZ, _t2, -(this.rY * _t0));
        return new Double3(java.lang.Math.fma(this.rZ, _t12, java.lang.Math.fma(-this.rY, _t13, java.lang.Math.fma(this.rW, _t14, _t1))) / this.sX, java.lang.Math.fma(this.rX, _t13, java.lang.Math.fma(-this.rZ, _t14, java.lang.Math.fma(this.rW, _t12, _t2))) / this.sY, java.lang.Math.fma(this.rY, _t14, java.lang.Math.fma(-this.rX, _t12, java.lang.Math.fma(this.rW, _t13, _t0))) / this.sZ);
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
    public Double3 transformVector(Double3 v) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        if (!Math.useFma()) return transformVector_mulAdd(vX, vY, vZ);
        double _t0 = this.sZ * vZ;
        double _t1 = this.sY * vY;
        double _t2 = this.sX * vX;
        double _t12 = 2.0 * java.lang.Math.fma(this.rY, _t0, -(this.rZ * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(this.rX, _t1, -(this.rY * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(this.rZ, _t2, -(this.rX * _t0));
        return new Double3(java.lang.Math.fma(this.sX, vX, java.lang.Math.fma(this.rW, _t12, java.lang.Math.fma(this.rY, _t13, -(this.rZ * _t14)))), java.lang.Math.fma(this.sY, vY, java.lang.Math.fma(this.rW, _t14, java.lang.Math.fma(this.rZ, _t12, -(this.rX * _t13)))), java.lang.Math.fma(this.sZ, vZ, java.lang.Math.fma(this.rW, _t13, java.lang.Math.fma(this.rX, _t14, -(this.rY * _t12)))));
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
    public Double3 transformVector(double vX, double vY, double vZ) {
        if (!Math.useFma()) return transformVector_mulAdd(vX, vY, vZ);
        double _t0 = this.sZ * vZ;
        double _t1 = this.sY * vY;
        double _t2 = this.sX * vX;
        double _t12 = 2.0 * java.lang.Math.fma(this.rY, _t0, -(this.rZ * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(this.rX, _t1, -(this.rY * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(this.rZ, _t2, -(this.rX * _t0));
        return new Double3(java.lang.Math.fma(this.sX, vX, java.lang.Math.fma(this.rW, _t12, java.lang.Math.fma(this.rY, _t13, -(this.rZ * _t14)))), java.lang.Math.fma(this.sY, vY, java.lang.Math.fma(this.rW, _t14, java.lang.Math.fma(this.rZ, _t12, -(this.rX * _t13)))), java.lang.Math.fma(this.sZ, vZ, java.lang.Math.fma(this.rW, _t13, java.lang.Math.fma(this.rX, _t14, -(this.rY * _t12)))));
    }

    /** {@code transformVector} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformVector_mulAdd(double vX, double vY, double vZ) {
        double _t0 = this.sZ * vZ;
        double _t1 = this.sY * vY;
        double _t2 = this.sX * vX;
        double _t12 = 2.0 * ((this.rY) * (_t0) - (this.rZ * _t1));
        double _t13 = 2.0 * ((this.rX) * (_t1) - (this.rY * _t2));
        double _t14 = 2.0 * ((this.rZ) * (_t2) - (this.rX * _t0));
        return new Double3(((this.sX) * (vX) + (((this.rW) * (_t12) + (((this.rY) * (_t13) - (this.rZ * _t14)))))), ((this.sY) * (vY) + (((this.rW) * (_t14) + (((this.rZ) * (_t12) - (this.rX * _t13)))))), ((this.sZ) * (vZ) + (((this.rW) * (_t13) + (((this.rX) * (_t14) - (this.rY * _t12)))))));
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
    public Double3 transformVectorInverse(Double3 v) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        if (!Math.useFma()) return transformVectorInverse_mulAdd(vX, vY, vZ);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, vZ, -(this.rZ * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.rY, vX, -(this.rX * vY));
        double _t11 = 2.0 * java.lang.Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Double3(java.lang.Math.fma(this.rZ, _t9, java.lang.Math.fma(-this.rY, _t10, java.lang.Math.fma(this.rW, _t11, vX))) / this.sX, java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rZ, _t11, java.lang.Math.fma(this.rW, _t9, vY))) / this.sY, java.lang.Math.fma(this.rY, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vZ))) / this.sZ);
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
    public Double3 transformVectorInverse(double vX, double vY, double vZ) {
        if (!Math.useFma()) return transformVectorInverse_mulAdd(vX, vY, vZ);
        double _t9 = 2.0 * java.lang.Math.fma(this.rX, vZ, -(this.rZ * vX));
        double _t10 = 2.0 * java.lang.Math.fma(this.rY, vX, -(this.rX * vY));
        double _t11 = 2.0 * java.lang.Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Double3(java.lang.Math.fma(this.rZ, _t9, java.lang.Math.fma(-this.rY, _t10, java.lang.Math.fma(this.rW, _t11, vX))) / this.sX, java.lang.Math.fma(this.rX, _t10, java.lang.Math.fma(-this.rZ, _t11, java.lang.Math.fma(this.rW, _t9, vY))) / this.sY, java.lang.Math.fma(this.rY, _t11, java.lang.Math.fma(-this.rX, _t9, java.lang.Math.fma(this.rW, _t10, vZ))) / this.sZ);
    }

    /** {@code transformVectorInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformVectorInverse_mulAdd(double vX, double vY, double vZ) {
        double _t9 = 2.0 * ((this.rX) * (vZ) - (this.rZ * vX));
        double _t10 = 2.0 * ((this.rY) * (vX) - (this.rX * vY));
        double _t11 = 2.0 * ((this.rZ) * (vY) - (this.rY * vZ));
        return new Double3(((this.rZ) * (_t9) + (((((this.rW) * (_t11) + (vX))) - (this.rY) * (_t10)))) / this.sX, ((this.rX) * (_t10) + (((((this.rW) * (_t9) + (vY))) - (this.rZ) * (_t11)))) / this.sY, ((this.rY) * (_t11) + (((((this.rW) * (_t10) + (vZ))) - (this.rX) * (_t9)))) / this.sZ);
    }

    /**
     * {@return a copy with the {@code tX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code tX} component
     */
    public DoubleTransform withTX(double v) {
        return new DoubleTransform(v, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ);
    }

    /**
     * {@return a copy with the {@code tY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code tY} component
     */
    public DoubleTransform withTY(double v) {
        return new DoubleTransform(tX, v, tZ, rX, rY, rZ, rW, sX, sY, sZ);
    }

    /**
     * {@return a copy with the {@code tZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code tZ} component
     */
    public DoubleTransform withTZ(double v) {
        return new DoubleTransform(tX, tY, v, rX, rY, rZ, rW, sX, sY, sZ);
    }

    /**
     * {@return a copy with the {@code rX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rX} component
     */
    public DoubleTransform withRX(double v) {
        return new DoubleTransform(tX, tY, tZ, v, rY, rZ, rW, sX, sY, sZ);
    }

    /**
     * {@return a copy with the {@code rY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rY} component
     */
    public DoubleTransform withRY(double v) {
        return new DoubleTransform(tX, tY, tZ, rX, v, rZ, rW, sX, sY, sZ);
    }

    /**
     * {@return a copy with the {@code rZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rZ} component
     */
    public DoubleTransform withRZ(double v) {
        return new DoubleTransform(tX, tY, tZ, rX, rY, v, rW, sX, sY, sZ);
    }

    /**
     * {@return a copy with the {@code rW} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code rW} component
     */
    public DoubleTransform withRW(double v) {
        return new DoubleTransform(tX, tY, tZ, rX, rY, rZ, v, sX, sY, sZ);
    }

    /**
     * {@return a copy with the {@code sX} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code sX} component
     */
    public DoubleTransform withSX(double v) {
        return new DoubleTransform(tX, tY, tZ, rX, rY, rZ, rW, v, sY, sZ);
    }

    /**
     * {@return a copy with the {@code sY} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code sY} component
     */
    public DoubleTransform withSY(double v) {
        return new DoubleTransform(tX, tY, tZ, rX, rY, rZ, rW, sX, v, sZ);
    }

    /**
     * {@return a copy with the {@code sZ} component replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code sZ} component
     */
    public DoubleTransform withSZ(double v) {
        return new DoubleTransform(tX, tY, tZ, rX, rY, rZ, rW, sX, sY, v);
    }

    @Override public String toString() {
        return "DoubleTransform(" + this.tX + ", " + this.tY + ", " + this.tZ + ", " + this.rX + ", " + this.rY + ", " + this.rZ + ", " + this.rW + ", " + this.sX + ", " + this.sY + ", " + this.sZ + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleTransform)) return false;
        DoubleTransform o = (DoubleTransform) obj;
        return Double.doubleToLongBits(tX) == Double.doubleToLongBits(o.tX)
            && Double.doubleToLongBits(tY) == Double.doubleToLongBits(o.tY)
            && Double.doubleToLongBits(tZ) == Double.doubleToLongBits(o.tZ)
            && Double.doubleToLongBits(rX) == Double.doubleToLongBits(o.rX)
            && Double.doubleToLongBits(rY) == Double.doubleToLongBits(o.rY)
            && Double.doubleToLongBits(rZ) == Double.doubleToLongBits(o.rZ)
            && Double.doubleToLongBits(rW) == Double.doubleToLongBits(o.rW)
            && Double.doubleToLongBits(sX) == Double.doubleToLongBits(o.sX)
            && Double.doubleToLongBits(sY) == Double.doubleToLongBits(o.sY)
            && Double.doubleToLongBits(sZ) == Double.doubleToLongBits(o.sZ);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int) (Double.doubleToLongBits(tX) ^ (Double.doubleToLongBits(tX) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(tY) ^ (Double.doubleToLongBits(tY) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(tZ) ^ (Double.doubleToLongBits(tZ) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(rX) ^ (Double.doubleToLongBits(rX) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(rY) ^ (Double.doubleToLongBits(rY) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(rZ) ^ (Double.doubleToLongBits(rZ) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(rW) ^ (Double.doubleToLongBits(rW) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(sX) ^ (Double.doubleToLongBits(sX) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(sY) ^ (Double.doubleToLongBits(sY) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(sZ) ^ (Double.doubleToLongBits(sZ) >>> 32));
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Double.isFinite(tX)
            && Double.isFinite(tY)
            && Double.isFinite(tZ)
            && Double.isFinite(rX)
            && Double.isFinite(rY)
            && Double.isFinite(rZ)
            && Double.isFinite(rW)
            && Double.isFinite(sX)
            && Double.isFinite(sY)
            && Double.isFinite(sZ);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Double.isNaN(tX)
            || Double.isNaN(tY)
            || Double.isNaN(tZ)
            || Double.isNaN(rX)
            || Double.isNaN(rY)
            || Double.isNaN(rZ)
            || Double.isNaN(rW)
            || Double.isNaN(sX)
            || Double.isNaN(sY)
            || Double.isNaN(sZ);
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
    public boolean equalsEpsilon(DoubleTransform other, double epsilon) {
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
     * Store the elements into the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform load(double[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        double _c6 = src[offset + 6];
        double _c7 = src[offset + 7];
        double _c8 = src[offset + 8];
        double _c9 = src[offset + 9];
        return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform load(double[] src) { return load(src, 0); }

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
    public DoubleBuffer store(DoubleBuffer buf) {
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
    public DoubleBuffer storeAbsolute(int index, DoubleBuffer buf) {
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
    public DoubleBuffer storeRelative(DoubleBuffer buf) {
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
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform load(DoubleBuffer buf) {
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
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform loadAbsolute(int index, DoubleBuffer buf) {
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
     * @return a new {@code DoubleTransform} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleTransform loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 10) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleTransform r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
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
        if (buf.remaining() < 80) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 80);
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
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform load(ByteBuffer buf) {
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
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform loadAbsolute(int index, ByteBuffer buf) {
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
     * @return a new {@code DoubleTransform} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleTransform loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 80) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleTransform r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 80);
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
    public DoubleTransform storeUnsafe(long address) {
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
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform loadUnsafe(long address) {
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
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

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
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(offset, src);
    }

    /**
     * Store the elements into the given array, converting each element to {@code float}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] store(float[] dest, int offset) {
        dest[offset] = (float) this.tX;
        dest[offset + 1] = (float) this.tY;
        dest[offset + 2] = (float) this.tZ;
        dest[offset + 3] = (float) this.rX;
        dest[offset + 4] = (float) this.rY;
        dest[offset + 5] = (float) this.rZ;
        dest[offset + 6] = (float) this.rW;
        dest[offset + 7] = (float) this.sX;
        dest[offset + 8] = (float) this.sY;
        dest[offset + 9] = (float) this.sZ;
        return dest;
    }

    /**
     * Store the elements into the given array, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements written must lie within
     * {@code dest}.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store(float[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code float}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform load(float[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        double _c6 = src[offset + 6];
        double _c7 = src[offset + 7];
        double _c8 = src[offset + 8];
        double _c9 = src[offset + 9];
        return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
    }

    /**
     * Load the elements from the given array, converting each element from {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source array
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform load(float[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code float}, starting
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
    public FloatBuffer store(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code float}, starting
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
    public FloatBuffer storeAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code float}, starting
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
    public FloatBuffer storeRelative(FloatBuffer buf) {
        if (buf.remaining() < 10) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 10);
        return buf;
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at its current position (the position is not modified).
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
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at the given absolute index (the position is not used or modified).
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
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code float}, starting
     * at its current position and advancing the position accordingly.
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
     * @return a new {@code DoubleTransform} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleTransform loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 10) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleTransform r = StoreLoad.BB_OPS.loadAbsolute(pos, buf);
        buf.position(pos + 10);
        return r;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
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
    public ByteBuffer storeFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
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
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
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
    public ByteBuffer storeFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 40) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 40);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
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
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
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
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code float},
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
     * @return a new {@code DoubleTransform} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static DoubleTransform loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 40) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleTransform r = StoreLoad.BB_OPS.loadFloatAbsolute(pos, buf);
        buf.position(pos + 40);
        return r;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be writable for all the elements.
     *
     * @param address the raw memory address
     * @return this
     */
    public DoubleTransform storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included; the memory at {@code address} must
     * be readable for all the elements.
     *
     * @param address the raw memory address
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment, converting each element to {@code float}.
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
    public MemorySegment storeFloat(MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code float},
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
    public MemorySegment storeFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment, converting each element from {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers, native-order heap {@code ByteBuffer}s,
     * native segments and {@code byte[]}-backed heap segments are not bounds-checked and segment
     * liveness / thread confinement is not verified; the API backend performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included; the elements read must lie within
     * {@code src}.
     *
     * @param src the source memory segment
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(0L, src); }

    /**
     * Load the elements from the given memory segment, converting each element from {@code float},
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
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(offset, src);
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

    private abstract static class DoubleTransformSegOps {
        public abstract MemorySegment store(DoubleTransform self, long offset, MemorySegment dest);
        public abstract DoubleTransform load(long offset, MemorySegment src);
        public abstract MemorySegment storeFloat(DoubleTransform self, long offset, MemorySegment dest);
        public abstract DoubleTransform loadFloat(long offset, MemorySegment src);
    }

    private static final class DoubleTransformSegOpsMS extends DoubleTransformSegOps {
        public MemorySegment store(DoubleTransform self, long offset, MemorySegment dest) {
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

        public DoubleTransform load(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = ValueLayout.JAVA_DOUBLE_UNALIGNED;
            double _c0 = src.get(_JAVA_DOUBLE_UNALIGNED, offset);
            double _c1 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 8L);
            double _c2 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 16L);
            double _c3 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 24L);
            double _c4 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 32L);
            double _c5 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 40L);
            double _c6 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 48L);
            double _c7 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 56L);
            double _c8 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 64L);
            double _c9 = src.get(_JAVA_DOUBLE_UNALIGNED, offset + 72L);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public MemorySegment storeFloat(DoubleTransform self, long offset, MemorySegment dest) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            dest.set(_JAVA_FLOAT_UNALIGNED, offset, (float) self.tX);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 4L, (float) self.tY);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 8L, (float) self.tZ);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 12L, (float) self.rX);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 16L, (float) self.rY);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 20L, (float) self.rZ);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 24L, (float) self.rW);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 28L, (float) self.sX);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 32L, (float) self.sY);
            dest.set(_JAVA_FLOAT_UNALIGNED, offset + 36L, (float) self.sZ);
            return dest;
        }

        public DoubleTransform loadFloat(long offset, MemorySegment src) {
            java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = ValueLayout.JAVA_FLOAT_UNALIGNED;
            double _c0 = src.get(_JAVA_FLOAT_UNALIGNED, offset);
            double _c1 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 4L);
            double _c2 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 8L);
            double _c3 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 12L);
            double _c4 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 16L);
            double _c5 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 20L);
            double _c6 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 24L);
            double _c7 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 28L);
            double _c8 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 32L);
            double _c9 = src.get(_JAVA_FLOAT_UNALIGNED, offset + 36L);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }
    }

    private static final class DoubleTransformSegOpsUnsafe extends DoubleTransformSegOps {
        private static final DoubleTransformSegOpsMS MS = new DoubleTransformSegOpsMS();

        public MemorySegment store(DoubleTransform self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.store(self, offset, dest);
            if (!dest.isNative()) return storeOnHeap(self, offset, dest);
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

        private static MemorySegment storeOnHeap(DoubleTransform self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.store(self, offset, dest);
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

        public DoubleTransform load(long offset, MemorySegment src) {
            if (!src.isNative()) return loadOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            double _c4 = _U.getDouble(address + 32L);
            double _c5 = _U.getDouble(address + 40L);
            double _c6 = _U.getDouble(address + 48L);
            double _c7 = _U.getDouble(address + 56L);
            double _c8 = _U.getDouble(address + 64L);
            double _c9 = _U.getDouble(address + 72L);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        private static DoubleTransform loadOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.load(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8L);
            double _c2 = _U.getDouble(bytes, address + 16L);
            double _c3 = _U.getDouble(bytes, address + 24L);
            double _c4 = _U.getDouble(bytes, address + 32L);
            double _c5 = _U.getDouble(bytes, address + 40L);
            double _c6 = _U.getDouble(bytes, address + 48L);
            double _c7 = _U.getDouble(bytes, address + 56L);
            double _c8 = _U.getDouble(bytes, address + 64L);
            double _c9 = _U.getDouble(bytes, address + 72L);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public MemorySegment storeFloat(DoubleTransform self, long offset, MemorySegment dest) {
            if (dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
            if (!dest.isNative()) return storeFloatOnHeap(self, offset, dest);
            long address = dest.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.tX);
            _U.putFloat(address + 4L, (float) self.tY);
            _U.putFloat(address + 8L, (float) self.tZ);
            _U.putFloat(address + 12L, (float) self.rX);
            _U.putFloat(address + 16L, (float) self.rY);
            _U.putFloat(address + 20L, (float) self.rZ);
            _U.putFloat(address + 24L, (float) self.rW);
            _U.putFloat(address + 28L, (float) self.sX);
            _U.putFloat(address + 32L, (float) self.sY);
            _U.putFloat(address + 36L, (float) self.sZ);
            return dest;
        }

        private static MemorySegment storeFloatOnHeap(DoubleTransform self, long offset, MemorySegment dest) {
            byte[] bytes = HeapSegments.bytes(dest);
            if (bytes == null) return MS.storeFloat(self, offset, dest);
            long address = HeapSegments.offset(dest) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.tX);
            _U.putFloat(bytes, address + 4L, (float) self.tY);
            _U.putFloat(bytes, address + 8L, (float) self.tZ);
            _U.putFloat(bytes, address + 12L, (float) self.rX);
            _U.putFloat(bytes, address + 16L, (float) self.rY);
            _U.putFloat(bytes, address + 20L, (float) self.rZ);
            _U.putFloat(bytes, address + 24L, (float) self.rW);
            _U.putFloat(bytes, address + 28L, (float) self.sX);
            _U.putFloat(bytes, address + 32L, (float) self.sY);
            _U.putFloat(bytes, address + 36L, (float) self.sZ);
            return dest;
        }

        public DoubleTransform loadFloat(long offset, MemorySegment src) {
            if (!src.isNative()) return loadFloatOnHeap(offset, src);
            long address = src.address() + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            double _c4 = _U.getFloat(address + 16L);
            double _c5 = _U.getFloat(address + 20L);
            double _c6 = _U.getFloat(address + 24L);
            double _c7 = _U.getFloat(address + 28L);
            double _c8 = _U.getFloat(address + 32L);
            double _c9 = _U.getFloat(address + 36L);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        private static DoubleTransform loadFloatOnHeap(long offset, MemorySegment src) {
            byte[] bytes = HeapSegments.bytes(src);
            if (bytes == null) return MS.loadFloat(offset, src);
            long address = HeapSegments.offset(src) + offset;
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4L);
            double _c2 = _U.getFloat(bytes, address + 8L);
            double _c3 = _U.getFloat(bytes, address + 12L);
            double _c4 = _U.getFloat(bytes, address + 16L);
            double _c5 = _U.getFloat(bytes, address + 20L);
            double _c6 = _U.getFloat(bytes, address + 24L);
            double _c7 = _U.getFloat(bytes, address + 28L);
            double _c8 = _U.getFloat(bytes, address + 32L);
            double _c9 = _U.getFloat(bytes, address + 36L);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }
    }

    private abstract static class DoubleTransformBbOps {
        public abstract DoubleBuffer storeAbsolute(DoubleTransform self, int index, DoubleBuffer buf);
        public abstract DoubleTransform loadAbsolute(int index, DoubleBuffer buf);
        public abstract ByteBuffer storeAbsolute(DoubleTransform self, int index, ByteBuffer buf);
        public abstract DoubleTransform loadAbsolute(int index, ByteBuffer buf);
        public abstract FloatBuffer storeAbsolute(DoubleTransform self, int index, FloatBuffer buf);
        public abstract DoubleTransform loadAbsolute(int index, FloatBuffer buf);
        public abstract ByteBuffer storeFloatAbsolute(DoubleTransform self, int index, ByteBuffer buf);
        public abstract DoubleTransform loadFloatAbsolute(int index, ByteBuffer buf);
    }

    private static final class DoubleTransformBbOpsApi extends DoubleTransformBbOps {
        public DoubleBuffer storeAbsolute(DoubleTransform self, int index, DoubleBuffer buf) {
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

        public DoubleTransform loadAbsolute(int index, DoubleBuffer buf) {
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(index + 2);
            double _c3 = buf.get(index + 3);
            double _c4 = buf.get(index + 4);
            double _c5 = buf.get(index + 5);
            double _c6 = buf.get(index + 6);
            double _c7 = buf.get(index + 7);
            double _c8 = buf.get(index + 8);
            double _c9 = buf.get(index + 9);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public ByteBuffer storeAbsolute(DoubleTransform self, int index, ByteBuffer buf) {
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

        public DoubleTransform loadAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getDouble(index);
            double _c1 = buf.getDouble(index + 8);
            double _c2 = buf.getDouble(index + 16);
            double _c3 = buf.getDouble(index + 24);
            double _c4 = buf.getDouble(index + 32);
            double _c5 = buf.getDouble(index + 40);
            double _c6 = buf.getDouble(index + 48);
            double _c7 = buf.getDouble(index + 56);
            double _c8 = buf.getDouble(index + 64);
            double _c9 = buf.getDouble(index + 72);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public FloatBuffer storeAbsolute(DoubleTransform self, int index, FloatBuffer buf) {
            buf.put(index, (float) self.tX);
            buf.put(index + 1, (float) self.tY);
            buf.put(index + 2, (float) self.tZ);
            buf.put(index + 3, (float) self.rX);
            buf.put(index + 4, (float) self.rY);
            buf.put(index + 5, (float) self.rZ);
            buf.put(index + 6, (float) self.rW);
            buf.put(index + 7, (float) self.sX);
            buf.put(index + 8, (float) self.sY);
            buf.put(index + 9, (float) self.sZ);
            return buf;
        }

        public DoubleTransform loadAbsolute(int index, FloatBuffer buf) {
            double _c0 = buf.get(index);
            double _c1 = buf.get(index + 1);
            double _c2 = buf.get(index + 2);
            double _c3 = buf.get(index + 3);
            double _c4 = buf.get(index + 4);
            double _c5 = buf.get(index + 5);
            double _c6 = buf.get(index + 6);
            double _c7 = buf.get(index + 7);
            double _c8 = buf.get(index + 8);
            double _c9 = buf.get(index + 9);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public ByteBuffer storeFloatAbsolute(DoubleTransform self, int index, ByteBuffer buf) {
            buf.putFloat(index, (float) self.tX);
            buf.putFloat(index + 4, (float) self.tY);
            buf.putFloat(index + 8, (float) self.tZ);
            buf.putFloat(index + 12, (float) self.rX);
            buf.putFloat(index + 16, (float) self.rY);
            buf.putFloat(index + 20, (float) self.rZ);
            buf.putFloat(index + 24, (float) self.rW);
            buf.putFloat(index + 28, (float) self.sX);
            buf.putFloat(index + 32, (float) self.sY);
            buf.putFloat(index + 36, (float) self.sZ);
            return buf;
        }

        public DoubleTransform loadFloatAbsolute(int index, ByteBuffer buf) {
            double _c0 = buf.getFloat(index);
            double _c1 = buf.getFloat(index + 4);
            double _c2 = buf.getFloat(index + 8);
            double _c3 = buf.getFloat(index + 12);
            double _c4 = buf.getFloat(index + 16);
            double _c5 = buf.getFloat(index + 20);
            double _c6 = buf.getFloat(index + 24);
            double _c7 = buf.getFloat(index + 28);
            double _c8 = buf.getFloat(index + 32);
            double _c9 = buf.getFloat(index + 36);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }
    }

    private static final class DoubleTransformBbOpsUnsafe extends DoubleTransformBbOps {
        private static final DoubleTransformBbOpsApi API = new DoubleTransformBbOpsApi();

        public DoubleBuffer storeAbsolute(DoubleTransform self, int index, DoubleBuffer buf) {
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

        public DoubleTransform loadAbsolute(int index, DoubleBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 8L;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            double _c4 = _U.getDouble(address + 32L);
            double _c5 = _U.getDouble(address + 40L);
            double _c6 = _U.getDouble(address + 48L);
            double _c7 = _U.getDouble(address + 56L);
            double _c8 = _U.getDouble(address + 64L);
            double _c9 = _U.getDouble(address + 72L);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public ByteBuffer storeAbsolute(DoubleTransform self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeAbsoluteElse(self, index, buf);
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

        private static ByteBuffer storeAbsoluteElse(DoubleTransform self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeAbsolute(self, index, buf);
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

        public DoubleTransform loadAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            double _c4 = _U.getDouble(address + 32L);
            double _c5 = _U.getDouble(address + 40L);
            double _c6 = _U.getDouble(address + 48L);
            double _c7 = _U.getDouble(address + 56L);
            double _c8 = _U.getDouble(address + 64L);
            double _c9 = _U.getDouble(address + 72L);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        private static DoubleTransform loadAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8L);
            double _c2 = _U.getDouble(bytes, address + 16L);
            double _c3 = _U.getDouble(bytes, address + 24L);
            double _c4 = _U.getDouble(bytes, address + 32L);
            double _c5 = _U.getDouble(bytes, address + 40L);
            double _c6 = _U.getDouble(bytes, address + 48L);
            double _c7 = _U.getDouble(bytes, address + 56L);
            double _c8 = _U.getDouble(bytes, address + 64L);
            double _c9 = _U.getDouble(bytes, address + 72L);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public FloatBuffer storeAbsolute(DoubleTransform self, int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return API.storeAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            _U.putFloat(address, (float) self.tX);
            _U.putFloat(address + 4L, (float) self.tY);
            _U.putFloat(address + 8L, (float) self.tZ);
            _U.putFloat(address + 12L, (float) self.rX);
            _U.putFloat(address + 16L, (float) self.rY);
            _U.putFloat(address + 20L, (float) self.rZ);
            _U.putFloat(address + 24L, (float) self.rW);
            _U.putFloat(address + 28L, (float) self.sX);
            _U.putFloat(address + 32L, (float) self.sY);
            _U.putFloat(address + 36L, (float) self.sZ);
            return buf;
        }

        public DoubleTransform loadAbsolute(int index, FloatBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return API.loadAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index * 4L;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            double _c4 = _U.getFloat(address + 16L);
            double _c5 = _U.getFloat(address + 20L);
            double _c6 = _U.getFloat(address + 24L);
            double _c7 = _U.getFloat(address + 28L);
            double _c8 = _U.getFloat(address + 32L);
            double _c9 = _U.getFloat(address + 36L);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public ByteBuffer storeFloatAbsolute(DoubleTransform self, int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder()) return storeFloatAbsoluteElse(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(address, (float) self.tX);
            _U.putFloat(address + 4L, (float) self.tY);
            _U.putFloat(address + 8L, (float) self.tZ);
            _U.putFloat(address + 12L, (float) self.rX);
            _U.putFloat(address + 16L, (float) self.rY);
            _U.putFloat(address + 20L, (float) self.rZ);
            _U.putFloat(address + 24L, (float) self.rW);
            _U.putFloat(address + 28L, (float) self.sX);
            _U.putFloat(address + 32L, (float) self.sY);
            _U.putFloat(address + 36L, (float) self.sZ);
            return buf;
        }

        private static ByteBuffer storeFloatAbsoluteElse(DoubleTransform self, int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.isReadOnly() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.storeFloatAbsolute(self, index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            _U.putFloat(bytes, address, (float) self.tX);
            _U.putFloat(bytes, address + 4L, (float) self.tY);
            _U.putFloat(bytes, address + 8L, (float) self.tZ);
            _U.putFloat(bytes, address + 12L, (float) self.rX);
            _U.putFloat(bytes, address + 16L, (float) self.rY);
            _U.putFloat(bytes, address + 20L, (float) self.rZ);
            _U.putFloat(bytes, address + 24L, (float) self.rW);
            _U.putFloat(bytes, address + 28L, (float) self.sX);
            _U.putFloat(bytes, address + 32L, (float) self.sY);
            _U.putFloat(bytes, address + 36L, (float) self.sZ);
            return buf;
        }

        public DoubleTransform loadFloatAbsolute(int index, ByteBuffer buf) {
            if (!buf.isDirect() || buf.order() != ByteOrder.nativeOrder()) return loadFloatAbsoluteElse(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            double _c4 = _U.getFloat(address + 16L);
            double _c5 = _U.getFloat(address + 20L);
            double _c6 = _U.getFloat(address + 24L);
            double _c7 = _U.getFloat(address + 28L);
            double _c8 = _U.getFloat(address + 32L);
            double _c9 = _U.getFloat(address + 36L);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        private static DoubleTransform loadFloatAbsoluteElse(int index, ByteBuffer buf) {
            byte[] bytes = buf.isDirect() || buf.order() != ByteOrder.nativeOrder() ? null : UnsafeCopy.heapBytes(buf);
            if (bytes == null) return API.loadFloatAbsolute(index, buf);
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            long address = _U.getLong(buf, UnsafeCopy.BB_ADDRESS_OFFSET) + index;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4L);
            double _c2 = _U.getFloat(bytes, address + 8L);
            double _c3 = _U.getFloat(bytes, address + 12L);
            double _c4 = _U.getFloat(bytes, address + 16L);
            double _c5 = _U.getFloat(bytes, address + 20L);
            double _c6 = _U.getFloat(bytes, address + 24L);
            double _c7 = _U.getFloat(bytes, address + 28L);
            double _c8 = _U.getFloat(bytes, address + 32L);
            double _c9 = _U.getFloat(bytes, address + 36L);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }
    }

    private abstract static class DoubleTransformRawOps {
        public abstract DoubleTransform storeUnsafe(DoubleTransform self, long address);
        public abstract DoubleTransform loadUnsafe(long address);
        public abstract DoubleTransform storeFloatUnsafe(DoubleTransform self, long address);
        public abstract DoubleTransform loadFloatUnsafe(long address);
    }

    private static final class DoubleTransformRawOpsApi extends DoubleTransformRawOps {
        public DoubleTransform storeUnsafe(DoubleTransform self, long address) {
            self.store(0L, virtualMemory().asSlice(address, 80L));
            return self;
        }

        public DoubleTransform loadUnsafe(long address) {
            return DoubleTransform.load(0L, virtualMemory().asSlice(address, 80L));
        }

        public DoubleTransform storeFloatUnsafe(DoubleTransform self, long address) {
            self.storeFloat(0L, virtualMemory().asSlice(address, 40L));
            return self;
        }

        public DoubleTransform loadFloatUnsafe(long address) {
            return DoubleTransform.loadFloat(0L, virtualMemory().asSlice(address, 40L));
        }
    }

    private static final class DoubleTransformRawOpsUnsafe extends DoubleTransformRawOps {
        public DoubleTransform storeUnsafe(DoubleTransform self, long address) {
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

        public DoubleTransform storeHeap(DoubleTransform self, byte[] bytes, long address) {
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

        public DoubleTransform loadUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(address);
            double _c1 = _U.getDouble(address + 8L);
            double _c2 = _U.getDouble(address + 16L);
            double _c3 = _U.getDouble(address + 24L);
            double _c4 = _U.getDouble(address + 32L);
            double _c5 = _U.getDouble(address + 40L);
            double _c6 = _U.getDouble(address + 48L);
            double _c7 = _U.getDouble(address + 56L);
            double _c8 = _U.getDouble(address + 64L);
            double _c9 = _U.getDouble(address + 72L);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public DoubleTransform loadHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getDouble(bytes, address);
            double _c1 = _U.getDouble(bytes, address + 8L);
            double _c2 = _U.getDouble(bytes, address + 16L);
            double _c3 = _U.getDouble(bytes, address + 24L);
            double _c4 = _U.getDouble(bytes, address + 32L);
            double _c5 = _U.getDouble(bytes, address + 40L);
            double _c6 = _U.getDouble(bytes, address + 48L);
            double _c7 = _U.getDouble(bytes, address + 56L);
            double _c8 = _U.getDouble(bytes, address + 64L);
            double _c9 = _U.getDouble(bytes, address + 72L);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public DoubleTransform storeFloatUnsafe(DoubleTransform self, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(address, (float) self.tX);
            _U.putFloat(address + 4L, (float) self.tY);
            _U.putFloat(address + 8L, (float) self.tZ);
            _U.putFloat(address + 12L, (float) self.rX);
            _U.putFloat(address + 16L, (float) self.rY);
            _U.putFloat(address + 20L, (float) self.rZ);
            _U.putFloat(address + 24L, (float) self.rW);
            _U.putFloat(address + 28L, (float) self.sX);
            _U.putFloat(address + 32L, (float) self.sY);
            _U.putFloat(address + 36L, (float) self.sZ);
            return self;
        }

        public DoubleTransform storeFloatHeap(DoubleTransform self, byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            _U.putFloat(bytes, address, (float) self.tX);
            _U.putFloat(bytes, address + 4L, (float) self.tY);
            _U.putFloat(bytes, address + 8L, (float) self.tZ);
            _U.putFloat(bytes, address + 12L, (float) self.rX);
            _U.putFloat(bytes, address + 16L, (float) self.rY);
            _U.putFloat(bytes, address + 20L, (float) self.rZ);
            _U.putFloat(bytes, address + 24L, (float) self.rW);
            _U.putFloat(bytes, address + 28L, (float) self.sX);
            _U.putFloat(bytes, address + 32L, (float) self.sY);
            _U.putFloat(bytes, address + 36L, (float) self.sZ);
            return self;
        }

        public DoubleTransform loadFloatUnsafe(long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(address);
            double _c1 = _U.getFloat(address + 4L);
            double _c2 = _U.getFloat(address + 8L);
            double _c3 = _U.getFloat(address + 12L);
            double _c4 = _U.getFloat(address + 16L);
            double _c5 = _U.getFloat(address + 20L);
            double _c6 = _U.getFloat(address + 24L);
            double _c7 = _U.getFloat(address + 28L);
            double _c8 = _U.getFloat(address + 32L);
            double _c9 = _U.getFloat(address + 36L);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }

        public DoubleTransform loadFloatHeap(byte[] bytes, long address) {
            org.joml2.internal.unsafe.UnsafeOps _U = U;
            double _c0 = _U.getFloat(bytes, address);
            double _c1 = _U.getFloat(bytes, address + 4L);
            double _c2 = _U.getFloat(bytes, address + 8L);
            double _c3 = _U.getFloat(bytes, address + 12L);
            double _c4 = _U.getFloat(bytes, address + 16L);
            double _c5 = _U.getFloat(bytes, address + 20L);
            double _c6 = _U.getFloat(bytes, address + 24L);
            double _c7 = _U.getFloat(bytes, address + 28L);
            double _c8 = _U.getFloat(bytes, address + 32L);
            double _c9 = _U.getFloat(bytes, address + 36L);
            return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
        }
    }
}
