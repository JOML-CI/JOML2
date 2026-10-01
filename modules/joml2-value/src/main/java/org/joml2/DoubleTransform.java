// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

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
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
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
     * Valid input: any value, NaN and the infinities included.
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
     * Create a new transform from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the transform to copy
     * @return the resulting transform
     */
    public DoubleTransform set(DoubleTransform v) {
        double vTX = v.tX();
        double vTY = v.tY();
        double vTZ = v.tZ();
        double vRX = v.rX();
        double vRY = v.rY();
        double vRZ = v.rZ();
        double vRW = v.rW();
        double vSX = v.sX();
        double vSY = v.sY();
        double vSZ = v.sZ();
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
     * Create the rigid motion of the unit dual quaternion {@code dq} (translation and rotation from
     * {@code dq}, scale = 1).
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
        return new DoubleTransform(2.0 * (Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + Math.fma(dqRW, dqDX, -(dqRX * dqDW))), 2.0 * (Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + Math.fma(dqRW, dqDY, -(dqRY * dqDW))), 2.0 * (Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + Math.fma(dqRW, dqDZ, -(dqRZ * dqDW))), dqRX, dqRY, dqRZ, dqRW, 1.0, 1.0, 1.0);
    }


    /**
     * Create the rigid motion of the unit dual quaternion ({@code dqRX}, {@code dqRY},
     * {@code dqRZ}, {@code dqRW}, {@code dqDX}, {@code dqDY}, {@code dqDZ}, {@code dqDW})
     * (translation and rotation from {@code dq}, scale = 1).
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
        return new DoubleTransform(2.0 * (Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + Math.fma(dqRW, dqDX, -(dqRX * dqDW))), 2.0 * (Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + Math.fma(dqRW, dqDY, -(dqRY * dqDW))), 2.0 * (Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + Math.fma(dqRW, dqDZ, -(dqRZ * dqDW))), dqRX, dqRY, dqRZ, dqRW, 1.0, 1.0, 1.0);
    }


    /**
     * Create the decomposition of the given matrix's linear {@code R * S} block, with zero
     * translation (scale is removed by normalizing the columns, but shear is not removed: a sheared
     * block yields a rotation quaternion that is not unit length).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return the resulting transform
     */
    public static DoubleTransform makeFromMatrix(Double3x3 m) {
        double _t12 = Math.fma(m.m21(), m.m21(), Math.fma(m.m01(), m.m01(), m.m11() * m.m11()));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t13 = Math.fma(m.m22(), m.m22(), Math.fma(m.m02(), m.m02(), m.m12() * m.m12()));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t14 = Math.fma(m.m20(), m.m20(), Math.fma(m.m00(), m.m00(), m.m10() * m.m10()));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        double _sfx8 = java.lang.Math.sqrt(_t12);
        double _t15 = 1.0 / _sfx8;
        double _sfx9 = java.lang.Math.sqrt(_t13);
        return makeFromMatrix_s4b73775e_1(m, -m.m11(), -m.m22(), _t18, m.m10() * _t17, m.m20() * _t17, m.m00() * _t17, 0.0, 0.0, 0.0, _sfx8, _t15, m.m21() * _t15, m.m11() * _t15, _sfx9, 1.0 / _sfx9);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_s4b73775e_1(Double3x3 m, double _t0, double _t1, double _t18, double _t19, double _t22, double _t27, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t24, double _t25, double _sfx9, double _t16) {
        double _t20 = m.m22() * _t16;
        double _t21 = m.m12() * _t16;
        double _t48, _t49, _t50, _sfx7;
        if (Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), m.m01() * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), m.m02() * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27)) < 0.0) {
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
        double _t64 = Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t52));
        return makeFromMatrix_s4b73775e_2(m, _t0, _t1, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, Math.fma(m.m12(), _t16, _t24), Math.fma(m.m21(), _t15, -_t21), _t48, _sfx7, _t52, 1.0 - _t48, Math.fma(m.m01(), _t15, _t49), Math.fma(m.m02(), _t16, _t50), Math.fma(m.m02(), _t16, -_t50), Math.fma(-m.m01(), _t15, _t49), _t64, 0.5 * (1.0 / java.lang.Math.sqrt(_t64)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_s4b73775e_2(Double3x3 m, double _t0, double _t1, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t25, double _sfx9, double _t16, double _t20, double _t32, double _t36, double _t48, double _sfx7, double _t52, double _t53, double _t55, double _t56, double _t57, double _t58, double _t64, double _sp0) {
        double _t66 = Math.fma(m.m11(), _t15, Math.fma(_t1, _t16, _t53));
        double _t67 = Math.fma(m.m22(), _t16, Math.fma(_t0, _t15, _t53));
        double _t68 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t68));
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t48)) > 0.0) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > java.lang.Math.max(_t25, _t20)) {
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
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail(double _t31, double _t17, double _t15, double _t16, double _t32, double _t13, double _t12, double _t14, double _t35, double _t36, double _t29, double _t2, double _t37, double _t27, double _t28, double _t0, double _t1) {
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
        return makeFromMatrix_degenerate_s91e5aa_tail2(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail2(double _t44, double _t45, double _t36, double _t35, double _t48, double _t49, double _t40, double _t39, double _t52, double _t53, double _t41, double _t42, double _t78, double _t75, double _t79, double _t76, double _t80, double _t77, double _t27, double _t28, double _t29, double _t38, double _t37, double _t43, double _t56, double _t0, double _t1) {
        double _t90 = _t44 < _t45 ? -_t36 : _t35;
        double _t91 = _t48 < _t49 ? -_t40 : _t39;
        double _t92 = _t52 < _t53 ? -_t41 : _t42;
        double _t102 = (1.0 / java.lang.Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / java.lang.Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / java.lang.Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
        return makeFromMatrix_degenerate_s91e5aa_tail3(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail3(double _t27, double _t28, double _t29, double _t105, double _t38, double _t108, double _t39, double _t117, double _t35, double _t37, double _t41, double _t118, double _t36, double _t109, double _t42, double _t119, double _t106, double _t40, double _t43, double _t107, double _t110, double _t56, double _t0, double _t1) {
        double _t168, _t169;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t169 = 0.0;
                } else {
                    _t168 = _t105;
                    _t169 = Math.fma(_t35, _t105, -(_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0 ? Math.fma(_t38, _t108, -(_t39 * _t117)) : Math.fma(_t35, _t38, -(_t37 * _t39));
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t118 : Math.fma(_t35, _t41, -(_t36 * _t42)) : _t38;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail4(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail4(double _t27, double _t28, double _t29, double _t119, double _t39, double _t106, double _t40, double _t108, double _t36, double _t35, double _t43, double _t107, double _t37, double _t109, double _t42, double _t41, double _t118, double _t38, double _t117, double _t110, double _t105, double _t169, double _t168, double _t56, double _t0, double _t1) {
        double _t170, _t171;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t170 = 1.0;
                    _t171 = 0.0;
                } else {
                    _t170 = _t119;
                    _t171 = Math.fma(_t37, _t109, -(_t35 * _t119));
                }
            } else {
                _t170 = _t29 <= 0.0 ? Math.fma(_t39, _t106, -(_t40 * _t108)) : Math.fma(_t36, _t39, -(_t35 * _t40));
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t107 : Math.fma(_t37, _t42, -(_t35 * _t43)) : _t40;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail5(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail5(double _t29, double _t27, double _t28, double _t108, double _t41, double _t118, double _t43, double _t107, double _t38, double _t40, double _t35, double _t109, double _t117, double _t106, double _t37, double _t36, double _t42, double _t110, double _t119, double _t105, double _t39, double _t169, double _t170, double _t168, double _t171, double _t56, double _t0, double _t1) {
        double _t172, _t173;
        if (_t29 <= 0.0) {
            if (_t27 <= 0.0) {
                if (_t28 <= 0.0) {
                    _t172 = 0.0;
                    _t173 = 0.0;
                } else {
                    _t172 = _t108;
                    _t173 = Math.fma(_t40, _t117, -(_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0 ? Math.fma(_t41, _t118, -(_t43 * _t107)) : Math.fma(_t41, _t38, -(_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0 ? _t28 <= 0.0 ? _t109 : Math.fma(_t37, _t40, -(_t36 * _t38)) : _t42;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail6(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail6(double _t28, double _t29, double _t27, double _t110, double _t36, double _t119, double _t37, double _t105, double _t43, double _t41, double _t39, double _t117, double _t42, double _t107, double _t40, double _t106, double _t118, double _t38, double _t173, double _t169, double _t170, double _t168, double _t171, double _t172, double _t56, double _t0, double _t1) {
        double _t174, _t175;
        if (_t28 <= 0.0) {
            if (_t29 <= 0.0) {
                if (_t27 <= 0.0) {
                    _t174 = 1.0;
                    _t175 = 0.0;
                } else {
                    _t174 = _t110;
                    _t175 = Math.fma(_t42, _t107, -(_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0 ? Math.fma(_t36, _t119, -(_t37 * _t105)) : Math.fma(_t36, _t43, -(_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t117 : Math.fma(_t42, _t40, -(_t41 * _t39)) : _t37;
        }
        return makeFromMatrix_degenerate_s91e5aa_tail7(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e5aa_tail7(double _t29, double _t27, double _t28, double _t106, double _t43, double _t110, double _t42, double _t118, double _t39, double _t38, double _t36, double _t173, double _t169, double _t170, double _t174, double _t168, double _t171, double _t172, double _t175, double _t56, double _t0, double _t1) {
        double _t176 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t28 <= 0.0 ? 1.0 : _t106 : _t28 <= 0.0 ? Math.fma(_t43, _t110, -(_t42 * _t118)) : Math.fma(_t43, _t39, -(_t42 * _t38)) : _t36;
        double _t196 = Math.fma(Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, Math.fma(Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
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
        return makeFromMatrix_degenerate_s91e5aa_tail8(_t199, _t171, _t198, _t168, _t197, _t170, _t174, _t173 - _t169, java.lang.Math.max(_t170, _t174), _t198 + _t168, _t173 + _t169, _t196, _t56, _t27, _t0, _t28, _t1);
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
    private static DoubleTransform makeFromMatrix_degenerate(Double3x3 m) {
        double _t0 = unitScale(m.m01(), m.m11(), m.m21());
        double _t1 = unitScale(m.m02(), m.m12(), m.m22());
        double _t2 = unitScale(m.m00(), m.m10(), m.m20());
        double _t12 = m.m21() * _t0;
        double _t13 = m.m01() * _t0;
        double _t14 = m.m11() * _t0;
        double _t15 = m.m22() * _t1;
        double _t16 = m.m02() * _t1;
        double _t17 = m.m12() * _t1;
        double _t18 = m.m20() * _t2;
        double _t19 = m.m00() * _t2;
        double _t20 = m.m10() * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_s91e5aa_tail((1.0 / java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0 / java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t30 * _t20, _t27, _t28, _t0, _t1);
    }


    /**
     * Create the TRS decomposition of the given affine matrix: translation from the last column,
     * scale from the column lengths of the upper-left 3x3 block, rotation from the
     * column-normalized block (scale is removed by normalizing the columns, but shear is not
     * removed: a sheared block yields a rotation quaternion that is not unit length).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return the resulting transform
     */
    public static DoubleTransform makeFromMatrix(Double3x4 m) {
        double _t12 = Math.fma(m.m21(), m.m21(), Math.fma(m.m01(), m.m01(), m.m11() * m.m11()));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t13 = Math.fma(m.m22(), m.m22(), Math.fma(m.m02(), m.m02(), m.m12() * m.m12()));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t14 = Math.fma(m.m20(), m.m20(), Math.fma(m.m00(), m.m00(), m.m10() * m.m10()));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        double _sfx8 = java.lang.Math.sqrt(_t12);
        double _t15 = 1.0 / _sfx8;
        return makeFromMatrix_s4d24221f_1(m, _t13, -m.m11(), -m.m22(), _t18, m.m10() * _t17, m.m20() * _t17, m.m00() * _t17, m.m03(), m.m13(), m.m23(), _sfx8, _t15, m.m21() * _t15);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_s4d24221f_1(Double3x4 m, double _t13, double _t0, double _t1, double _t18, double _t19, double _t22, double _t27, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t24) {
        double _t25 = m.m11() * _t15;
        double _sfx9 = java.lang.Math.sqrt(_t13);
        double _t16 = 1.0 / _sfx9;
        double _t20 = m.m22() * _t16;
        double _t21 = m.m12() * _t16;
        double _t48, _t49, _t50, _sfx7;
        if (Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), m.m01() * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), m.m02() * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27)) < 0.0) {
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
        return makeFromMatrix_s4d24221f_2(m, _t0, _t1, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, Math.fma(m.m12(), _t16, _t24), Math.fma(m.m21(), _t15, -_t21), _t48, _sfx7, _t52, 1.0 - _t48, Math.fma(m.m01(), _t15, _t49), Math.fma(m.m02(), _t16, _t50), Math.fma(m.m02(), _t16, -_t50), Math.fma(-m.m01(), _t15, _t49), Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t52)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_s4d24221f_2(Double3x4 m, double _t0, double _t1, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t25, double _sfx9, double _t16, double _t20, double _t32, double _t36, double _t48, double _sfx7, double _t52, double _t53, double _t55, double _t56, double _t57, double _t58, double _t64) {
        double _t66 = Math.fma(m.m11(), _t15, Math.fma(_t1, _t16, _t53));
        double _t67 = Math.fma(m.m22(), _t16, Math.fma(_t0, _t15, _t53));
        double _t68 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52));
        return makeFromMatrix_s4d24221f_3(m, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, _t32, _t36, _t48, _sfx7, _t55, _t56, _t57, _t58, _t64, 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), _t66, _t67, _t68, 0.5 * (1.0 / java.lang.Math.sqrt(_t66)), 0.5 * (1.0 / java.lang.Math.sqrt(_t67)), 0.5 * (1.0 / java.lang.Math.sqrt(_t68)));
    }

    /** Piece 4 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_s4d24221f_3(Double3x4 m, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t25, double _sfx9, double _t16, double _t20, double _t32, double _t36, double _t48, double _sfx7, double _t55, double _t56, double _t57, double _t58, double _t64, double _sp0, double _t66, double _t67, double _t68, double _sp1, double _sp2, double _sp3) {
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t48)) > 0.0) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > java.lang.Math.max(_t25, _t20)) {
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
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail(double _t30, double _t20, double _t31, double _t17, double _t15, double _t16, double _t32, double _t13, double _t12, double _t14, double _t35, double _t36, double _t29, double _t2, double _t27, double _t28, Double3x4 m, double _t0, double _t1) {
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
        return makeFromMatrix_degenerate_s91e96b_tail2(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, m, _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail2(double _t44, double _t45, double _t36, double _t35, double _t48, double _t49, double _t40, double _t39, double _t52, double _t53, double _t41, double _t42, double _t78, double _t75, double _t79, double _t76, double _t80, double _t77, double _t27, double _t28, double _t29, double _t38, double _t37, double _t43, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t90 = _t44 < _t45 ? -_t36 : _t35;
        double _t91 = _t48 < _t49 ? -_t40 : _t39;
        double _t92 = _t52 < _t53 ? -_t41 : _t42;
        double _t102 = (1.0 / java.lang.Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / java.lang.Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / java.lang.Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
        return makeFromMatrix_degenerate_s91e96b_tail3(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail3(double _t27, double _t28, double _t29, double _t105, double _t38, double _t108, double _t39, double _t117, double _t35, double _t37, double _t41, double _t118, double _t36, double _t109, double _t42, double _t119, double _t106, double _t40, double _t43, double _t107, double _t110, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t168, _t169;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t169 = 0.0;
                } else {
                    _t168 = _t105;
                    _t169 = Math.fma(_t35, _t105, -(_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0 ? Math.fma(_t38, _t108, -(_t39 * _t117)) : Math.fma(_t35, _t38, -(_t37 * _t39));
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t118 : Math.fma(_t35, _t41, -(_t36 * _t42)) : _t38;
        }
        return makeFromMatrix_degenerate_s91e96b_tail4(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail4(double _t27, double _t28, double _t29, double _t119, double _t39, double _t106, double _t40, double _t108, double _t36, double _t35, double _t43, double _t107, double _t37, double _t109, double _t42, double _t41, double _t118, double _t38, double _t117, double _t110, double _t105, double _t169, double _t168, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t170, _t171;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t170 = 1.0;
                    _t171 = 0.0;
                } else {
                    _t170 = _t119;
                    _t171 = Math.fma(_t37, _t109, -(_t35 * _t119));
                }
            } else {
                _t170 = _t29 <= 0.0 ? Math.fma(_t39, _t106, -(_t40 * _t108)) : Math.fma(_t36, _t39, -(_t35 * _t40));
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t107 : Math.fma(_t37, _t42, -(_t35 * _t43)) : _t40;
        }
        return makeFromMatrix_degenerate_s91e96b_tail5(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail5(double _t29, double _t27, double _t28, double _t108, double _t41, double _t118, double _t43, double _t107, double _t38, double _t40, double _t35, double _t109, double _t117, double _t106, double _t37, double _t36, double _t42, double _t110, double _t119, double _t105, double _t39, double _t169, double _t170, double _t168, double _t171, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t172, _t173;
        if (_t29 <= 0.0) {
            if (_t27 <= 0.0) {
                if (_t28 <= 0.0) {
                    _t172 = 0.0;
                    _t173 = 0.0;
                } else {
                    _t172 = _t108;
                    _t173 = Math.fma(_t40, _t117, -(_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0 ? Math.fma(_t41, _t118, -(_t43 * _t107)) : Math.fma(_t41, _t38, -(_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0 ? _t28 <= 0.0 ? _t109 : Math.fma(_t37, _t40, -(_t36 * _t38)) : _t42;
        }
        return makeFromMatrix_degenerate_s91e96b_tail6(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail6(double _t28, double _t29, double _t27, double _t110, double _t36, double _t119, double _t37, double _t105, double _t43, double _t41, double _t39, double _t117, double _t42, double _t107, double _t40, double _t106, double _t118, double _t38, double _t173, double _t169, double _t170, double _t168, double _t171, double _t172, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t174, _t175;
        if (_t28 <= 0.0) {
            if (_t29 <= 0.0) {
                if (_t27 <= 0.0) {
                    _t174 = 1.0;
                    _t175 = 0.0;
                } else {
                    _t174 = _t110;
                    _t175 = Math.fma(_t42, _t107, -(_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0 ? Math.fma(_t36, _t119, -(_t37 * _t105)) : Math.fma(_t36, _t43, -(_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t117 : Math.fma(_t42, _t40, -(_t41 * _t39)) : _t37;
        }
        return makeFromMatrix_degenerate_s91e96b_tail7(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_s91e96b_tail7(double _t29, double _t27, double _t28, double _t106, double _t43, double _t110, double _t42, double _t118, double _t39, double _t38, double _t36, double _t173, double _t169, double _t170, double _t174, double _t168, double _t171, double _t172, double _t175, Double3x4 m, double _t56, double _t0, double _t1) {
        double _t176 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t28 <= 0.0 ? 1.0 : _t106 : _t28 <= 0.0 ? Math.fma(_t43, _t110, -(_t42 * _t118)) : Math.fma(_t43, _t39, -(_t42 * _t38)) : _t36;
        double _t196 = Math.fma(Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, Math.fma(Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
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
        return makeFromMatrix_degenerate_s91e96b_tail8(_t199, _t171, _t198, _t168, _t197, _t170, _t174, m, _t173 - _t169, java.lang.Math.max(_t170, _t174), _t198 + _t168, _t173 + _t169, _t196, _t56, _t27, _t0, _t28, _t1);
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
    private static DoubleTransform makeFromMatrix_degenerate(Double3x4 m) {
        double _t0 = unitScale(m.m01(), m.m11(), m.m21());
        double _t1 = unitScale(m.m02(), m.m12(), m.m22());
        double _t2 = unitScale(m.m00(), m.m10(), m.m20());
        double _t12 = m.m21() * _t0;
        double _t13 = m.m01() * _t0;
        double _t14 = m.m11() * _t0;
        double _t15 = m.m22() * _t1;
        double _t16 = m.m02() * _t1;
        double _t17 = m.m12() * _t1;
        double _t18 = m.m20() * _t2;
        double _t19 = m.m00() * _t2;
        double _t20 = m.m10() * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_s91e96b_tail(_t30, _t20, (1.0 / java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0 / java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t27, _t28, m, _t0, _t1);
    }


    /**
     * Create the TRS decomposition of the given affine matrix: translation from the last column,
     * scale from the column lengths of the upper-left 3x3 block, rotation from the
     * column-normalized block (scale is removed by normalizing the columns, but shear is not
     * removed: a sheared block yields a rotation quaternion that is not unit length).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to convert
     * @return the resulting transform
     */
    public static DoubleTransform makeFromMatrix(Double4x4 m) {
        double _t12 = Math.fma(m.m21(), m.m21(), Math.fma(m.m01(), m.m01(), m.m11() * m.m11()));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t13 = Math.fma(m.m22(), m.m22(), Math.fma(m.m02(), m.m02(), m.m12() * m.m12()));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t14 = Math.fma(m.m20(), m.m20(), Math.fma(m.m00(), m.m00(), m.m10() * m.m10()));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return makeFromMatrix_degenerate(m);
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        double _sfx8 = java.lang.Math.sqrt(_t12);
        double _t15 = 1.0 / _sfx8;
        return makeFromMatrix_sf93a4716_1(m, _t13, -m.m11(), -m.m22(), _t18, m.m10() * _t17, m.m20() * _t17, m.m00() * _t17, m.m03(), m.m13(), m.m23(), _sfx8, _t15, m.m21() * _t15);
    }

    /** Piece 2 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_sf93a4716_1(Double4x4 m, double _t13, double _t0, double _t1, double _t18, double _t19, double _t22, double _t27, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t24) {
        double _t25 = m.m11() * _t15;
        double _sfx9 = java.lang.Math.sqrt(_t13);
        double _t16 = 1.0 / _sfx9;
        double _t20 = m.m22() * _t16;
        double _t21 = m.m12() * _t16;
        double _t48, _t49, _t50, _sfx7;
        if (Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), m.m01() * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), m.m02() * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27)) < 0.0) {
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
        return makeFromMatrix_sf93a4716_2(m, _t0, _t1, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, Math.fma(m.m12(), _t16, _t24), Math.fma(m.m21(), _t15, -_t21), _t48, _sfx7, _t52, 1.0 - _t48, Math.fma(m.m01(), _t15, _t49), Math.fma(m.m02(), _t16, _t50), Math.fma(m.m02(), _t16, -_t50), Math.fma(-m.m01(), _t15, _t49), Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t52)));
    }

    /** Piece 3 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_sf93a4716_2(Double4x4 m, double _t0, double _t1, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t25, double _sfx9, double _t16, double _t20, double _t32, double _t36, double _t48, double _sfx7, double _t52, double _t53, double _t55, double _t56, double _t57, double _t58, double _t64) {
        double _t66 = Math.fma(m.m11(), _t15, Math.fma(_t1, _t16, _t53));
        double _t67 = Math.fma(m.m22(), _t16, Math.fma(_t0, _t15, _t53));
        double _t68 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52));
        return makeFromMatrix_sf93a4716_3(m, _sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, _t32, _t36, _t48, _sfx7, _t55, _t56, _t57, _t58, _t64, 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), _t66, _t67, _t68, 0.5 * (1.0 / java.lang.Math.sqrt(_t66)), 0.5 * (1.0 / java.lang.Math.sqrt(_t67)), 0.5 * (1.0 / java.lang.Math.sqrt(_t68)));
    }

    /** Piece 4 of {@code makeFromMatrix}, split to fit the inline budget; reached only through it. */
    private static DoubleTransform makeFromMatrix_sf93a4716_3(Double4x4 m, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t25, double _sfx9, double _t16, double _t20, double _t32, double _t36, double _t48, double _sfx7, double _t55, double _t56, double _t57, double _t58, double _t64, double _sp0, double _t66, double _t67, double _t68, double _sp1, double _sp2, double _sp3) {
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (Math.fma(m.m11(), _t15, Math.fma(m.m22(), _t16, _t48)) > 0.0) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > java.lang.Math.max(_t25, _t20)) {
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
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail(double _t30, double _t20, double _t31, double _t17, double _t15, double _t16, double _t32, double _t13, double _t12, double _t14, double _t35, double _t36, double _t29, double _t2, double _t27, double _t28, Double4x4 m, double _t0, double _t1) {
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
        return makeFromMatrix_degenerate_sa000ec_tail2(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, m, _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail2(double _t44, double _t45, double _t36, double _t35, double _t48, double _t49, double _t40, double _t39, double _t52, double _t53, double _t41, double _t42, double _t78, double _t75, double _t79, double _t76, double _t80, double _t77, double _t27, double _t28, double _t29, double _t38, double _t37, double _t43, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t90 = _t44 < _t45 ? -_t36 : _t35;
        double _t91 = _t48 < _t49 ? -_t40 : _t39;
        double _t92 = _t52 < _t53 ? -_t41 : _t42;
        double _t102 = (1.0 / java.lang.Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / java.lang.Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / java.lang.Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
        return makeFromMatrix_degenerate_sa000ec_tail3(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail3(double _t27, double _t28, double _t29, double _t105, double _t38, double _t108, double _t39, double _t117, double _t35, double _t37, double _t41, double _t118, double _t36, double _t109, double _t42, double _t119, double _t106, double _t40, double _t43, double _t107, double _t110, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t168, _t169;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t169 = 0.0;
                } else {
                    _t168 = _t105;
                    _t169 = Math.fma(_t35, _t105, -(_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0 ? Math.fma(_t38, _t108, -(_t39 * _t117)) : Math.fma(_t35, _t38, -(_t37 * _t39));
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t118 : Math.fma(_t35, _t41, -(_t36 * _t42)) : _t38;
        }
        return makeFromMatrix_degenerate_sa000ec_tail4(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail4(double _t27, double _t28, double _t29, double _t119, double _t39, double _t106, double _t40, double _t108, double _t36, double _t35, double _t43, double _t107, double _t37, double _t109, double _t42, double _t41, double _t118, double _t38, double _t117, double _t110, double _t105, double _t169, double _t168, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t170, _t171;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t170 = 1.0;
                    _t171 = 0.0;
                } else {
                    _t170 = _t119;
                    _t171 = Math.fma(_t37, _t109, -(_t35 * _t119));
                }
            } else {
                _t170 = _t29 <= 0.0 ? Math.fma(_t39, _t106, -(_t40 * _t108)) : Math.fma(_t36, _t39, -(_t35 * _t40));
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t107 : Math.fma(_t37, _t42, -(_t35 * _t43)) : _t40;
        }
        return makeFromMatrix_degenerate_sa000ec_tail5(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail5(double _t29, double _t27, double _t28, double _t108, double _t41, double _t118, double _t43, double _t107, double _t38, double _t40, double _t35, double _t109, double _t117, double _t106, double _t37, double _t36, double _t42, double _t110, double _t119, double _t105, double _t39, double _t169, double _t170, double _t168, double _t171, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t172, _t173;
        if (_t29 <= 0.0) {
            if (_t27 <= 0.0) {
                if (_t28 <= 0.0) {
                    _t172 = 0.0;
                    _t173 = 0.0;
                } else {
                    _t172 = _t108;
                    _t173 = Math.fma(_t40, _t117, -(_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0 ? Math.fma(_t41, _t118, -(_t43 * _t107)) : Math.fma(_t41, _t38, -(_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0 ? _t28 <= 0.0 ? _t109 : Math.fma(_t37, _t40, -(_t36 * _t38)) : _t42;
        }
        return makeFromMatrix_degenerate_sa000ec_tail6(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail6(double _t28, double _t29, double _t27, double _t110, double _t36, double _t119, double _t37, double _t105, double _t43, double _t41, double _t39, double _t117, double _t42, double _t107, double _t40, double _t106, double _t118, double _t38, double _t173, double _t169, double _t170, double _t168, double _t171, double _t172, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t174, _t175;
        if (_t28 <= 0.0) {
            if (_t29 <= 0.0) {
                if (_t27 <= 0.0) {
                    _t174 = 1.0;
                    _t175 = 0.0;
                } else {
                    _t174 = _t110;
                    _t175 = Math.fma(_t42, _t107, -(_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0 ? Math.fma(_t36, _t119, -(_t37 * _t105)) : Math.fma(_t36, _t43, -(_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t117 : Math.fma(_t42, _t40, -(_t41 * _t39)) : _t37;
        }
        return makeFromMatrix_degenerate_sa000ec_tail7(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, m, _t56, _t0, _t1);
    }

    /** Private tail of {@code makeFromMatrix_degenerate}; reached only through it. */
    private static DoubleTransform makeFromMatrix_degenerate_sa000ec_tail7(double _t29, double _t27, double _t28, double _t106, double _t43, double _t110, double _t42, double _t118, double _t39, double _t38, double _t36, double _t173, double _t169, double _t170, double _t174, double _t168, double _t171, double _t172, double _t175, Double4x4 m, double _t56, double _t0, double _t1) {
        double _t176 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t28 <= 0.0 ? 1.0 : _t106 : _t28 <= 0.0 ? Math.fma(_t43, _t110, -(_t42 * _t118)) : Math.fma(_t43, _t39, -(_t42 * _t38)) : _t36;
        double _t196 = Math.fma(Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, Math.fma(Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
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
        return makeFromMatrix_degenerate_sa000ec_tail8(_t199, _t171, _t198, _t168, _t197, _t170, _t174, m, _t173 - _t169, java.lang.Math.max(_t170, _t174), _t198 + _t168, _t173 + _t169, _t196, _t56, _t27, _t0, _t28, _t1);
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
    private static DoubleTransform makeFromMatrix_degenerate(Double4x4 m) {
        double _t0 = unitScale(m.m01(), m.m11(), m.m21());
        double _t1 = unitScale(m.m02(), m.m12(), m.m22());
        double _t2 = unitScale(m.m00(), m.m10(), m.m20());
        double _t12 = m.m21() * _t0;
        double _t13 = m.m01() * _t0;
        double _t14 = m.m11() * _t0;
        double _t15 = m.m22() * _t1;
        double _t16 = m.m02() * _t1;
        double _t17 = m.m12() * _t1;
        double _t18 = m.m20() * _t2;
        double _t19 = m.m00() * _t2;
        double _t20 = m.m10() * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        return makeFromMatrix_degenerate_sa000ec_tail(_t30, _t20, (1.0 / java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0 / java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t27, _t28, m, _t0, _t1);
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
        double _t0 = -this.tZ;
        return new DoubleDualQuat(this.rX, this.rY, this.rZ, this.rW, 0.5 * Math.fma(_t0, this.rY, Math.fma(this.tX, this.rW, this.tY * this.rZ)), 0.5 * Math.fma(this.tZ, this.rX, Math.fma(this.tY, this.rW, -(this.tX * this.rZ))), 0.5 * Math.fma(this.tZ, this.rW, Math.fma(this.tX, this.rY, -(this.tY * this.rX))), 0.5 * Math.fma(_t0, this.rZ, Math.fma(-this.tY, this.rY, -(this.tX * this.rX))));
    }

    /** Private per-column body of {@code toMatrix}; reached only through it. */
    private double[] toMatrix_s0_c0(double _t3, double _t0, double _t4, double _t5) {
        return new double[] {Math.fma(-Math.fma(this.rY, this.rY, _t3), _t0, this.sX), Math.fma(this.rX, this.rY, _t4) * _t0, Math.fma(this.rX, this.rZ, -_t5) * _t0, 0.0};
    }

    /** Private per-column body of {@code toMatrix}; reached only through it. */
    private double[] toMatrix_s0_c1(double _t4, double _t1, double _t3) {
        return new double[] {Math.fma(this.rX, this.rY, -_t4) * _t1, Math.fma(-Math.fma(this.rX, this.rX, _t3), _t1, this.sY), Math.fma(this.rX, this.rW, this.rY * this.rZ) * _t1, 0.0};
    }

    /** Private per-column body of {@code toMatrix}; reached only through it. */
    private double[] toMatrix_s0_c2(double _t5, double _t2) {
        return new double[] {Math.fma(this.rX, this.rZ, _t5) * _t2, Math.fma(this.rY, this.rZ, -(this.rX * this.rW)) * _t2, Math.fma(-Math.fma(this.rX, this.rX, this.rY * this.rY), _t2, this.sZ), 0.0};
    }


    /**
     * Compute the matrix representation of this transform, returning the result as a value.
     * <p>
     * Valid input: the rotation of this transform must have unit length.
     *
     * @return the resulting matrix
     */
    public Double4x4 toMatrix() {
        double _t3 = this.rZ * this.rZ;
        double _t4 = this.rZ * this.rW;
        double _t5 = this.rY * this.rW;
        double[] _col0 = toMatrix_s0_c0(_t3, this.sX + this.sX, _t4, _t5);
        double[] _col1 = toMatrix_s0_c1(_t4, this.sY + this.sY, _t3);
        double[] _col2 = toMatrix_s0_c2(_t5, this.sZ + this.sZ);
        double[] _col3 = new double[] {this.tX, this.tY, this.tZ, 1.0};
        return new Double4x4(_col0[0], _col1[0], _col2[0], _col3[0], _col0[1], _col1[1], _col2[1], _col3[1], _col0[2], _col1[2], _col2[2], _col3[2], _col0[3], _col1[3], _col2[3], _col3[3], Joml.BIT_AFFINE);
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
        double _t0 = this.sX + this.sX;
        double _t1 = this.sY + this.sY;
        double _t2 = this.sZ + this.sZ;
        double _t3 = this.rZ * this.rZ;
        double _t4 = this.rZ * this.rW;
        double _t5 = this.rY * this.rW;
        return new Double3x3(Math.fma(-Math.fma(this.rY, this.rY, _t3), _t0, this.sX), Math.fma(this.rX, this.rY, -_t4) * _t1, Math.fma(this.rX, this.rZ, _t5) * _t2, Math.fma(this.rX, this.rY, _t4) * _t0, Math.fma(-Math.fma(this.rX, this.rX, _t3), _t1, this.sY), Math.fma(this.rY, this.rZ, -(this.rX * this.rW)) * _t2, Math.fma(this.rX, this.rZ, -_t5) * _t0, Math.fma(this.rX, this.rW, this.rY * this.rZ) * _t1, Math.fma(-Math.fma(this.rX, this.rX, this.rY * this.rY), _t2, this.sZ), 0);
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
        double _t0 = this.sX + this.sX;
        double _t1 = this.sY + this.sY;
        double _t2 = this.sZ + this.sZ;
        double _t3 = this.rZ * this.rZ;
        double _t4 = this.rZ * this.rW;
        double _t5 = this.rY * this.rW;
        return new Double3x4(Math.fma(-Math.fma(this.rY, this.rY, _t3), _t0, this.sX), Math.fma(this.rX, this.rY, -_t4) * _t1, Math.fma(this.rX, this.rZ, _t5) * _t2, this.tX, Math.fma(this.rX, this.rY, _t4) * _t0, Math.fma(-Math.fma(this.rX, this.rX, _t3), _t1, this.sY), Math.fma(this.rY, this.rZ, -(this.rX * this.rW)) * _t2, this.tY, Math.fma(this.rX, this.rZ, -_t5) * _t0, Math.fma(this.rX, this.rW, this.rY * this.rZ) * _t1, Math.fma(-Math.fma(this.rX, this.rX, this.rY * this.rY), _t2, this.sZ), this.tZ, Joml.BIT_AFFINE);
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
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the rotation of this transform must have unit length; the rotation of
     * {@code other} must have unit length.
     *
     * @param other the transform to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting transform
     */
    public DoubleTransform lerp(DoubleTransform other, double t) {
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
        double _t0 = 1.0 - t;
        double _t12 = Math.fma(otherRW, this.rW, Math.fma(otherRZ, this.rZ, Math.fma(otherRX, this.rX, otherRY * this.rY)));
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
        return lerp_s2f52a392_tail(_t17, Math.sin(_t0 * _t16), Math.sin(t * _t16), _t21, 1.0 / _t17, t, _t0, _t22, _t23, _t24, otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private DoubleTransform lerp_s2f52a392_tail(double _t17, double _t25, double _t19, double _t21, double _t17_inv, double t, double _t0, double _t22, double _t23, double _t24, double otherTX, double otherTY, double otherTZ, double otherSX, double otherSY, double otherSZ) {
        double _t42, _t43, _t44, _t45;
        if (_t17 > 0.0) {
            _t42 = Math.fma(this.rW, _t25, _t19 * _t21) * _t17_inv;
            _t43 = Math.fma(this.rZ, _t25, _t19 * _t22) * _t17_inv;
            _t44 = Math.fma(this.rX, _t25, _t19 * _t23) * _t17_inv;
            _t45 = Math.fma(this.rY, _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = Math.fma(t, _t21, this.rW * _t0);
            _t43 = Math.fma(t, _t22, this.rZ * _t0);
            _t44 = Math.fma(t, _t23, this.rX * _t0);
            _t45 = Math.fma(t, _t24, this.rY * _t0);
        }
        double _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        double _sfx0 = Math.fma(t, otherTX - this.tX, this.tX);
        return lerp_s2f52a392_tail2(t, otherTY, otherTZ, _t49, (1.0 / java.lang.Math.sqrt(_t49)), _t44, _t45, _t43, _t42, otherSX, otherSY, otherSZ, _sfx0);
    }

    /** Private tail of {@code lerp}; reached only through it. */
    private DoubleTransform lerp_s2f52a392_tail2(double t, double otherTY, double otherTZ, double _t49, double _t50, double _t44, double _t45, double _t43, double _t42, double otherSX, double otherSY, double otherSZ, double _sfx0) {
        double _sfx1 = Math.fma(t, otherTY - this.tY, this.tY);
        double _sfx2 = Math.fma(t, otherTZ - this.tZ, this.tZ);
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
        double _sfx7 = Math.fma(t, otherSX - this.sX, this.sX);
        double _sfx8 = Math.fma(t, otherSY - this.sY, this.sY);
        double _sfx9 = Math.fma(t, otherSZ - this.sZ, this.sZ);
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
     * @return the resulting transform
     */
    public DoubleTransform lerp(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ, double t) {
        double _t0 = 1.0 - t;
        double _t12 = Math.fma(otherRW, this.rW, Math.fma(otherRZ, this.rZ, Math.fma(otherRX, this.rX, otherRY * this.rY)));
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
        return lerp_s2f52a392_tail(_t17, Math.sin(_t0 * _t16), Math.sin(t * _t16), _t21, 1.0 / _t17, t, _t0, _t22, _t23, _t24, otherTX, otherTY, otherTZ, otherSX, otherSY, otherSZ);
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
        return mul(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), other.sX(), other.sY(), other.sZ());
    }

    /** Private tail of {@code mul}; reached only through it. */
    private DoubleTransform mul_s25974579_tail(double otherRX, double otherRW, double otherRZ, double otherRY, double otherSX, double otherSY, double otherSZ, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3 = Math.fma(otherRX, this.rW, otherRW * this.rX) + Math.fma(otherRZ, this.rY, -(otherRY * this.rZ));
        double _sfx4 = Math.fma(otherRY, this.rW, otherRW * this.rY) + Math.fma(otherRX, this.rZ, -(otherRZ * this.rX));
        double _sfx5 = Math.fma(otherRZ, this.rW, otherRW * this.rZ) + Math.fma(otherRY, this.rX, -(otherRX * this.rY));
        double _sfx6 = Math.fma(otherRW, this.rW, -(otherRX * this.rX)) - Math.fma(otherRY, this.rY, otherRZ * this.rZ);
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
        double _t0 = otherTY * this.sY;
        double _t1 = otherTX * this.sX;
        double _t2 = otherTZ * this.sZ;
        double _t12 = 2.0 * Math.fma(this.rX, _t0, -(this.rY * _t1));
        double _t13 = 2.0 * Math.fma(this.rZ, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * Math.fma(this.rY, _t2, -(this.rZ * _t0));
        double _sfx0 = Math.fma(this.rY, _t12, Math.fma(-this.rZ, _t13, Math.fma(this.rW, _t14, Math.fma(otherTX, this.sX, this.tX))));
        double _sfx1 = Math.fma(this.rZ, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, Math.fma(otherTY, this.sY, this.tY))));
        double _sfx2 = Math.fma(this.rX, _t13, Math.fma(-this.rY, _t14, Math.fma(this.rW, _t12, Math.fma(otherTZ, this.sZ, this.tZ))));
        return mul_s25974579_tail(otherRX, otherRW, otherRZ, otherRY, otherSX, otherSY, otherSZ, _sfx0, _sfx1, _sfx2);
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
        double _t0 = otherSY * this.tY;
        double _t1 = otherSX * this.tX;
        double _t2 = otherSZ * this.tZ;
        return preMul_s25974579_tail(otherRY, _t2, otherRZ, _t0, 2.0 * Math.fma(otherRX, _t0, -(otherRY * _t1)), 2.0 * Math.fma(otherRZ, _t1, -(otherRX * _t2)), otherRW, otherSX, otherTX, otherRX, otherSY, otherTY, otherSZ, otherTZ);
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private DoubleTransform preMul_s25974579_tail(double otherRY, double _t2, double otherRZ, double _t0, double _t12, double _t13, double otherRW, double otherSX, double otherTX, double otherRX, double otherSY, double otherTY, double otherSZ, double otherTZ) {
        double _t14 = 2.0 * Math.fma(otherRY, _t2, -(otherRZ * _t0));
        return new DoubleTransform(Math.fma(otherRY, _t12, Math.fma(-otherRZ, _t13, Math.fma(otherRW, _t14, Math.fma(otherSX, this.tX, otherTX)))), Math.fma(otherRZ, _t14, Math.fma(-otherRX, _t12, Math.fma(otherRW, _t13, Math.fma(otherSY, this.tY, otherTY)))), Math.fma(otherRX, _t13, Math.fma(-otherRY, _t14, Math.fma(otherRW, _t12, Math.fma(otherSZ, this.tZ, otherTZ)))), Math.fma(otherRX, this.rW, otherRW * this.rX) + Math.fma(otherRY, this.rZ, -(otherRZ * this.rY)), Math.fma(otherRY, this.rW, otherRW * this.rY) + Math.fma(otherRZ, this.rX, -(otherRX * this.rZ)), Math.fma(otherRZ, this.rW, otherRW * this.rZ) + Math.fma(otherRX, this.rY, -(otherRY * this.rX)), Math.fma(otherRW, this.rW, -(otherRX * this.rX)) - Math.fma(otherRY, this.rY, otherRZ * this.rZ), otherSX * this.sX, otherSY * this.sY, otherSZ * this.sZ);
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
        double _t0 = otherSY * this.tY;
        double _t1 = otherSX * this.tX;
        double _t2 = otherSZ * this.tZ;
        return preMul_s25974579_tail(otherRY, _t2, otherRZ, _t0, 2.0 * Math.fma(otherRX, _t0, -(otherRY * _t1)), 2.0 * Math.fma(otherRZ, _t1, -(otherRX * _t2)), otherRW, otherSX, otherTX, otherRX, otherSY, otherTY, otherSZ, otherTZ);
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
     * Valid input: each component of the scale of this transform must be non-zero.
     *
     * @param other the target transform, reached by composing this transform with the result
     * @return the resulting transform
     */
    public DoubleTransform difference(DoubleTransform other) {
        return difference(other.tX(), other.tY(), other.tZ(), other.rX(), other.rY(), other.rZ(), other.rW(), other.sX(), other.sY(), other.sZ());
    }

    /** Private tail of {@code difference}; reached only through it. */
    private DoubleTransform difference_s25974579_tail(double _t30, double _t31, double _t32, double _sp1, double _t33, double _t34, double _t35, double _sp3, double _sp2, double _sp5, double _sp0, double _sp4, double otherRX, double otherRW, double otherRY, double otherRZ, double otherSX, double _rcp1, double otherSY, double _rcp2, double otherSZ, double _rcp0) {
        double _sfx0 = Math.fma(this.rZ, _t30, -(this.rY * _t31)) + Math.fma(this.rW, _t32, _sp1) + (Math.fma(this.rZ, _t33, -(this.rY * _t34)) + Math.fma(this.rW, _t35, -_sp3));
        double _sfx1 = Math.fma(this.rX, _t31, -(this.rZ * _t32)) + Math.fma(this.rW, _t30, _sp2) + (Math.fma(this.rX, _t34, -(this.rZ * _t35)) + Math.fma(this.rW, _t33, -_sp5));
        double _sfx2 = Math.fma(this.rY, _t32, -(this.rX * _t30)) + Math.fma(this.rW, _t31, _sp0) + (Math.fma(this.rY, _t35, -(this.rX * _t33)) + Math.fma(this.rW, _t34, -_sp4));
        return difference_s25974579_tail2(otherRX, otherRW, otherRY, otherRZ, otherSX, _rcp1, otherSY, _rcp2, otherSZ, _rcp0, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code difference}; reached only through it. */
    private DoubleTransform difference_s25974579_tail2(double otherRX, double otherRW, double otherRY, double otherRZ, double otherSX, double _rcp1, double otherSY, double _rcp2, double otherSZ, double _rcp0, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3 = Math.fma(otherRX, this.rW, -(otherRW * this.rX)) + Math.fma(otherRY, this.rZ, -(otherRZ * this.rY));
        double _sfx4 = Math.fma(otherRY, this.rW, -(otherRW * this.rY)) + Math.fma(otherRZ, this.rX, -(otherRX * this.rZ));
        double _sfx5 = Math.fma(otherRX, this.rY, -(otherRY * this.rX)) + Math.fma(otherRZ, this.rW, -(otherRW * this.rZ));
        double _sfx6 = Math.fma(otherRX, this.rX, otherRW * this.rW) - Math.fma(-otherRZ, this.rZ, -(otherRY * this.rY));
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
     * @return the resulting transform
     */
    public DoubleTransform difference(double otherTX, double otherTY, double otherTZ, double otherRX, double otherRY, double otherRZ, double otherRW, double otherSX, double otherSY, double otherSZ) {
        double _rcp0 = 1.0 / this.sZ;
        double _sp4 = _rcp0 * this.tZ;
        double _sp0 = otherTZ * _rcp0;
        double _rcp1 = 1.0 / this.sX;
        double _sp3 = _rcp1 * this.tX;
        double _sp1 = otherTX * _rcp1;
        double _rcp2 = 1.0 / this.sY;
        double _sp5 = _rcp2 * this.tY;
        double _sp2 = otherTY * _rcp2;
        return difference_s25974579_tail(2.0 * Math.fma(_sp0, this.rX, -(_sp1 * this.rZ)), 2.0 * Math.fma(_sp1, this.rY, -(_sp2 * this.rX)), 2.0 * Math.fma(_sp2, this.rZ, -(_sp0 * this.rY)), _sp1, 2.0 * Math.fma(_sp3, this.rZ, -(_sp4 * this.rX)), 2.0 * Math.fma(_sp5, this.rX, -(_sp3 * this.rY)), 2.0 * Math.fma(_sp4, this.rY, -(_sp5 * this.rZ)), _sp3, _sp2, _sp5, _sp0, _sp4, otherRX, otherRW, otherRY, otherRZ, otherSX, _rcp1, otherSY, _rcp2, otherSZ, _rcp0);
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
        double _rcp0 = 1.0 / this.sX;
        double _sp0 = this.tX * _rcp0;
        double _rcp1 = 1.0 / this.sZ;
        double _sp1 = this.tZ * _rcp1;
        double _rcp2 = 1.0 / this.sY;
        double _sp2 = this.tY * _rcp2;
        double _t0 = -this.rY;
        double _t1 = -this.rZ;
        double _t2 = -this.rX;
        double _t18 = 2.0 * Math.fma(_sp0, this.rZ, -(_sp1 * this.rX));
        double _t19 = 2.0 * Math.fma(_sp2, this.rX, -(_sp0 * this.rY));
        double _t20 = 2.0 * Math.fma(_sp1, this.rY, -(_sp2 * this.rZ));
        return new DoubleTransform(Math.fma(this.rZ, _t18, Math.fma(_t0, _t19, Math.fma(this.rW, _t20, -_sp0))), Math.fma(this.rX, _t19, Math.fma(_t1, _t20, Math.fma(this.rW, _t18, -_sp2))), Math.fma(this.rY, _t20, Math.fma(_t2, _t18, Math.fma(this.rW, _t19, -_sp1))), _t2, _t0, _t1, this.rW, _rcp0, _rcp2, _rcp1);
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
        double _t3 = Math.fma(this.rW, this.rW, Math.fma(this.rZ, this.rZ, Math.fma(this.rX, this.rX, this.rY * this.rY)));
        double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0) {
            return new DoubleTransform(this.tX, this.tY, this.tZ, this.rX * _t4, this.rY * _t4, this.rZ * _t4, this.rW * _t4, this.sX, this.sY, this.sZ);
        } else {
            return new DoubleTransform(this.tX, this.tY, this.tZ, 0.0, 0.0, 0.0, 0.0, this.sX, this.sY, this.sZ);
        }
    }


    /**
     * Get the Euler angles in radians of this transform, to be applied about the X, Y and Z axes,
     * in that order, returning the result as a value.
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
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesXYZ() {
        double _t1 = this.rY * this.rZ;
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        double _t9 = 2.0 * Math.fma(this.rX, this.rW, -_t1);
        double _t10 = Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-15) {
            return new Double3(Math.atan2(2.0 * Math.fma(this.rX, this.rW, _t1), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t3), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, _t10), Math.atan2(_t8, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0, Math.fma(this.rY, this.rY, _t3), 1.0)));
        }
    }


    /**
     * Get the Euler angles in radians of this transform, to be applied about the X, Z and Y axes,
     * in that order, returning the result as a value.
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
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesXZY() {
        double _t0 = this.rZ * this.rZ;
        double _t1 = this.rY * this.rZ;
        double _t7 = 2.0 * Math.fma(this.rX, this.rW, _t1);
        double _t8 = 2.0 * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        double _t9 = Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            return new Double3(Math.atan2(2.0 * Math.fma(this.rX, this.rW, -_t1), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), 0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(_t7, _t9), Math.atan2(2.0 * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        }
    }


    /**
     * Get the Euler angles in radians of this transform, to be applied about the Y, X and Z axes,
     * in that order, returning the result as a value.
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
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesYXZ() {
        double _t3 = this.rZ * this.rZ;
        double _t8 = 2.0 * Math.fma(this.rX, this.rZ, this.rY * this.rW);
        double _t9 = 2.0 * Math.fma(this.rX, this.rW, -(this.rY * this.rZ));
        double _t10 = Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-15) {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(2.0 * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0, Math.fma(this.rY, this.rY, _t3), 1.0)), 0.0);
        } else {
            return new Double3(Math.atan2(_t9, java.lang.Math.sqrt(_t12)), Math.atan2(_t8, _t10), Math.atan2(2.0 * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t3), 1.0)));
        }
    }


    /**
     * Get the Euler angles in radians of this transform, to be applied about the Y, Z and X axes,
     * in that order, returning the result as a value.
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
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesYZX() {
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        double _t8 = 2.0 * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        double _t9 = Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(2.0 * Math.fma(this.rX, this.rZ, this.rY * this.rW), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        } else {
            return new Double3(Math.atan2(2.0 * Math.fma(this.rX, this.rW, -(this.rY * this.rZ)), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0)), Math.atan2(_t8, _t9), Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        }
    }


    /**
     * Get the Euler angles in radians of this transform, to be applied about the Z, X and Y axes,
     * in that order, returning the result as a value.
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
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesZXY() {
        double _t1 = this.rZ * this.rZ;
        double _t7 = 2.0 * Math.fma(this.rX, this.rW, this.rY * this.rZ);
        double _t8 = 2.0 * Math.fma(this.rZ, this.rW, -(this.rX * this.rY));
        double _t9 = Math.fma(-2.0, Math.fma(this.rX, this.rX, _t1), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), 0.0, Math.atan2(2.0 * Math.fma(this.rX, this.rY, this.rZ * this.rW), Math.fma(-2.0, Math.fma(this.rY, this.rY, _t1), 1.0)));
        } else {
            return new Double3(Math.atan2(_t7, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * Math.fma(this.rY, this.rW, -(this.rX * this.rZ)), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t8, _t9));
        }
    }


    /**
     * Get the Euler angles in radians of this transform, to be applied about the Z, Y and X axes,
     * in that order, returning the result as a value.
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
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Double3 getEulerAnglesZYX() {
        double _t0 = this.rZ * this.rZ;
        double _t7 = 2.0 * Math.fma(this.rX, this.rY, this.rZ * this.rW);
        double _t8 = 2.0 * Math.fma(this.rY, this.rW, -(this.rX * this.rZ));
        double _t9 = Math.fma(-2.0, Math.fma(this.rY, this.rY, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(2.0 * Math.fma(this.rZ, this.rW, -(this.rX * this.rY)), Math.fma(-2.0, Math.fma(this.rX, this.rX, _t0), 1.0)));
        } else {
            return new Double3(Math.atan2(2.0 * Math.fma(this.rX, this.rW, this.rY * this.rZ), Math.fma(-2.0, Math.fma(this.rX, this.rX, this.rY * this.rY), 1.0)), Math.atan2(_t8, java.lang.Math.sqrt(_t11)), Math.atan2(_t7, _t9));
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
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return the resulting transform
     */
    public static DoubleTransform makeRotationXYZ(double angleX, double angleY, double angleZ) {
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
        return new DoubleTransform(0.0, 0.0, 0.0, Math.fma(_t10, _t7, _t11 * _t5), Math.fma(_t11, _t7, -(_t10 * _t5)), Math.fma(_t9, _t7, _t12 * _t5), Math.fma(_t12, _t7, -(_t9 * _t5)), 1.0, 1.0, 1.0);
    }


    /**
     * Create a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return the resulting transform
     */
    public static DoubleTransform makeRotationXZY(double angleX, double angleZ, double angleY) {
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
        return new DoubleTransform(0.0, 0.0, 0.0, Math.fma(_t10, _t7, -(_t11 * _t5)), Math.fma(_t12, _t5, -(_t9 * _t7)), Math.fma(_t10, _t5, _t11 * _t7), Math.fma(_t9, _t5, _t12 * _t7), 1.0, 1.0, 1.0);
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
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return the resulting transform
     */
    public static DoubleTransform makeRotationYXZ(double angleY, double angleX, double angleZ) {
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
        return new DoubleTransform(0.0, 0.0, 0.0, Math.fma(_t10, _t7, _t11 * _t5), Math.fma(_t11, _t7, -(_t10 * _t5)), Math.fma(_t12, _t5, -(_t9 * _t7)), Math.fma(_t9, _t5, _t12 * _t7), 1.0, 1.0, 1.0);
    }


    /**
     * Create a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return the resulting transform
     */
    public static DoubleTransform makeRotationYZX(double angleY, double angleZ, double angleX) {
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
        return new DoubleTransform(0.0, 0.0, 0.0, Math.fma(_t9, _t6, _t12 * _t5), Math.fma(_t10, _t6, _t11 * _t5), Math.fma(_t11, _t6, -(_t10 * _t5)), Math.fma(_t12, _t6, -(_t9 * _t5)), 1.0, 1.0, 1.0);
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
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return the resulting transform
     */
    public static DoubleTransform makeRotationZXY(double angleZ, double angleX, double angleY) {
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
        return new DoubleTransform(0.0, 0.0, 0.0, Math.fma(_t10, _t7, -(_t11 * _t5)), Math.fma(_t9, _t7, _t12 * _t5), Math.fma(_t10, _t5, _t11 * _t7), Math.fma(_t12, _t7, -(_t9 * _t5)), 1.0, 1.0, 1.0);
    }


    /**
     * Create a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return the resulting transform
     */
    public static DoubleTransform makeRotationZYX(double angleZ, double angleY, double angleX) {
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
        return new DoubleTransform(0.0, 0.0, 0.0, Math.fma(_t12, _t5, -(_t9 * _t8)), Math.fma(_t10, _t8, _t11 * _t5), Math.fma(_t11, _t8, -(_t10 * _t5)), Math.fma(_t9, _t5, _t12 * _t8), 1.0, 1.0, 1.0);
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
        return new DoubleTransform(this.tX, this.tY, this.tZ, Math.fma(rotationX, this.rW, rotationW * this.rX) + Math.fma(rotationZ, this.rY, -(rotationY * this.rZ)), Math.fma(rotationY, this.rW, rotationW * this.rY) + Math.fma(rotationX, this.rZ, -(rotationZ * this.rX)), Math.fma(rotationZ, this.rW, rotationW * this.rZ) + Math.fma(rotationY, this.rX, -(rotationX * this.rY)), Math.fma(rotationW, this.rW, -(rotationX * this.rX)) - Math.fma(rotationY, this.rY, rotationZ * this.rZ), this.sX, this.sY, this.sZ);
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
        return new DoubleTransform(this.tX, this.tY, this.tZ, Math.fma(rotationX, this.rW, rotationW * this.rX) + Math.fma(rotationZ, this.rY, -(rotationY * this.rZ)), Math.fma(rotationY, this.rW, rotationW * this.rY) + Math.fma(rotationX, this.rZ, -(rotationZ * this.rX)), Math.fma(rotationZ, this.rW, rotationW * this.rZ) + Math.fma(rotationY, this.rX, -(rotationX * this.rY)), Math.fma(rotationW, this.rW, -(rotationX * this.rX)) - Math.fma(rotationY, this.rY, rotationZ * this.rZ), this.sX, this.sY, this.sZ);
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
        return new DoubleTransform(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t5, this.rW * _t2) + Math.fma(this.rY, _t3, -(this.rZ * _t4)), Math.fma(this.rY, _t5, this.rW * _t4) + Math.fma(this.rZ, _t2, -(this.rX * _t3)), Math.fma(this.rZ, _t5, this.rW * _t3) + Math.fma(this.rX, _t4, -(this.rY * _t2)), Math.fma(this.rW, _t5, -(this.rX * _t2)) - Math.fma(this.rY, _t4, this.rZ * _t3), this.sX, this.sY, this.sZ);
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
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        return new DoubleTransform(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t2, this.rW * _t1), Math.fma(this.rY, _t2, this.rZ * _t1), Math.fma(this.rZ, _t2, -(this.rY * _t1)), Math.fma(this.rW, _t2, -(this.rX * _t1)), this.sX, this.sY, this.sZ);
    }

    /**
     * Private tail of {@code rotateXYZ}. Shared by the identical private paths of
     * {@code rotateXYZ}, {@code rotateXZY} and {@code rotateYXZ}; reached only through them.
     */
    private DoubleTransform rotateXYZ_s361a4ff5_tail(double _t11, double _t8, double _t10, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        return new DoubleTransform(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t21, this.rW * _t19) + Math.fma(this.rY, _t20, -(this.rZ * _t22)), Math.fma(this.rY, _t21, this.rW * _t22) + Math.fma(this.rZ, _t19, -(this.rX * _t20)), Math.fma(this.rZ, _t21, this.rW * _t20) + Math.fma(this.rX, _t22, -(this.rY * _t19)), Math.fma(this.rW, _t21, -(this.rX * _t19)) - Math.fma(this.rY, _t22, this.rZ * _t20), this.sX, this.sY, this.sZ);
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this transform, returning the result as a value.
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
        return rotateXYZ_s361a4ff5_tail(_t11, _t8, _t10, _t5, Math.fma(_t14, _t8, -(_t9 * _t5)), Math.fma(_t10, _t8, _t11 * _t5), Math.fma(_t9, _t8, _t14 * _t5));
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this transform, returning the result as a value.
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
        return rotateXYZ_s361a4ff5_tail(_t12, _t5, _t9, _t8, Math.fma(_t9, _t5, _t12 * _t8), Math.fma(_t10, _t8, -(_t11 * _t5)), Math.fma(_t10, _t5, _t11 * _t8));
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
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        return new DoubleTransform(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t2, -(this.rZ * _t1)), Math.fma(this.rY, _t2, this.rW * _t1), Math.fma(this.rX, _t1, this.rZ * _t2), Math.fma(this.rW, _t2, -(this.rY * _t1)), this.sX, this.sY, this.sZ);
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this transform, returning the result as a value.
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
        return rotateXYZ_s361a4ff5_tail(_t11, _t8, _t10, _t5, Math.fma(_t9, _t5, _t12 * _t8), Math.fma(_t10, _t8, _t11 * _t5), Math.fma(_t12, _t5, -(_t9 * _t8)));
    }

    /**
     * Private tail of {@code rotateYZX}. Shared by the identical private paths of {@code rotateYZX}
     * and {@code rotateZYX}; reached only through them.
     */
    private DoubleTransform rotateYZX_s2c94f613_tail(double _t10, double _t8, double _t11, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return new DoubleTransform(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t21, this.rW * _t19) + Math.fma(this.rY, _t22, -(this.rZ * _t20)), Math.fma(this.rY, _t21, this.rW * _t20) + Math.fma(this.rZ, _t19, -(this.rX * _t22)), Math.fma(this.rZ, _t21, this.rW * _t22) + Math.fma(this.rX, _t20, -(this.rY * _t19)), Math.fma(this.rW, _t21, -(this.rX * _t19)) - Math.fma(this.rY, _t20, this.rZ * _t22), this.sX, this.sY, this.sZ);
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this transform, returning the result as a value.
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
        return rotateYZX_s2c94f613_tail(_t10, _t8, _t11, _t5, Math.fma(_t14, _t8, -(_t9 * _t5)), Math.fma(_t9, _t8, _t14 * _t5), Math.fma(_t11, _t8, _t10 * _t5));
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
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        return new DoubleTransform(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t2, this.rY * _t1), Math.fma(this.rY, _t2, -(this.rX * _t1)), Math.fma(this.rZ, _t2, this.rW * _t1), Math.fma(this.rW, _t2, -(this.rZ * _t1)), this.sX, this.sY, this.sZ);
    }

    /** Private tail of {@code rotateZXY}; reached only through it. */
    private DoubleTransform rotateZXY_s7e5a0297_tail(double _t10, double _t8, double _t11, double _t5, double _t21, double _t19, double _t20) {
        double _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        return new DoubleTransform(this.tX, this.tY, this.tZ, Math.fma(this.rX, _t21, this.rW * _t22) + Math.fma(this.rY, _t19, -(this.rZ * _t20)), Math.fma(this.rY, _t21, this.rW * _t20) + Math.fma(this.rZ, _t22, -(this.rX * _t19)), Math.fma(this.rZ, _t21, this.rW * _t19) + Math.fma(this.rX, _t20, -(this.rY * _t22)), Math.fma(this.rW, _t21, -(this.rX * _t22)) - Math.fma(this.rY, _t20, this.rZ * _t19), this.sX, this.sY, this.sZ);
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this transform, returning the result as a value.
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
        return rotateZXY_s7e5a0297_tail(_t10, _t8, _t11, _t5, Math.fma(_t14, _t8, -(_t9 * _t5)), Math.fma(_t10, _t5, _t11 * _t8), Math.fma(_t9, _t8, _t14 * _t5));
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this transform, returning the result as a value.
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
        return rotateYZX_s2c94f613_tail(_t10, _t8, _t11, _t5, Math.fma(_t9, _t5, _t12 * _t8), Math.fma(_t12, _t5, -(_t9 * _t8)), Math.fma(_t11, _t8, _t10 * _t5));
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
        double _t0 = this.sY * translationY;
        double _t1 = this.sX * translationX;
        double _t2 = this.sZ * translationZ;
        double _t12 = 2.0 * Math.fma(this.rX, _t0, -(this.rY * _t1));
        double _t13 = 2.0 * Math.fma(this.rZ, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * Math.fma(this.rY, _t2, -(this.rZ * _t0));
        return new DoubleTransform(Math.fma(this.rY, _t12, Math.fma(-this.rZ, _t13, Math.fma(this.rW, _t14, Math.fma(this.sX, translationX, this.tX)))), Math.fma(this.rZ, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, Math.fma(this.sY, translationY, this.tY)))), Math.fma(this.rX, _t13, Math.fma(-this.rY, _t14, Math.fma(this.rW, _t12, Math.fma(this.sZ, translationZ, this.tZ)))), this.rX, this.rY, this.rZ, this.rW, this.sX, this.sY, this.sZ);
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
        double _t0 = this.sY * translationY;
        double _t1 = this.sX * translationX;
        double _t2 = this.sZ * translationZ;
        double _t12 = 2.0 * Math.fma(this.rX, _t0, -(this.rY * _t1));
        double _t13 = 2.0 * Math.fma(this.rZ, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * Math.fma(this.rY, _t2, -(this.rZ * _t0));
        return new DoubleTransform(Math.fma(this.rY, _t12, Math.fma(-this.rZ, _t13, Math.fma(this.rW, _t14, Math.fma(this.sX, translationX, this.tX)))), Math.fma(this.rZ, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, Math.fma(this.sY, translationY, this.tY)))), Math.fma(this.rX, _t13, Math.fma(-this.rY, _t14, Math.fma(this.rW, _t12, Math.fma(this.sZ, translationZ, this.tZ)))), this.rX, this.rY, this.rZ, this.rW, this.sX, this.sY, this.sZ);
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
        double _t0 = this.sY * vY;
        double _t1 = this.sX * vX;
        double _t2 = this.sZ * vZ;
        double _t12 = 2.0 * Math.fma(this.rX, _t0, -(this.rY * _t1));
        double _t13 = 2.0 * Math.fma(this.rZ, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * Math.fma(this.rY, _t2, -(this.rZ * _t0));
        return new Double3(Math.fma(this.rY, _t12, Math.fma(-this.rZ, _t13, Math.fma(this.rW, _t14, Math.fma(this.sX, vX, this.tX)))), Math.fma(this.rZ, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, Math.fma(this.sY, vY, this.tY)))), Math.fma(this.rX, _t13, Math.fma(-this.rY, _t14, Math.fma(this.rW, _t12, Math.fma(this.sZ, vZ, this.tZ)))));
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
        double _t0 = this.sY * vY;
        double _t1 = this.sX * vX;
        double _t2 = this.sZ * vZ;
        double _t12 = 2.0 * Math.fma(this.rX, _t0, -(this.rY * _t1));
        double _t13 = 2.0 * Math.fma(this.rZ, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * Math.fma(this.rY, _t2, -(this.rZ * _t0));
        return new Double3(Math.fma(this.rY, _t12, Math.fma(-this.rZ, _t13, Math.fma(this.rW, _t14, Math.fma(this.sX, vX, this.tX)))), Math.fma(this.rZ, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, Math.fma(this.sY, vY, this.tY)))), Math.fma(this.rX, _t13, Math.fma(-this.rY, _t14, Math.fma(this.rW, _t12, Math.fma(this.sZ, vZ, this.tZ)))));
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
        double _t9 = 2.0 * Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Double3(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ))));
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
        double _t9 = 2.0 * Math.fma(this.rX, vY, -(this.rY * vX));
        double _t10 = 2.0 * Math.fma(this.rZ, vX, -(this.rX * vZ));
        double _t11 = 2.0 * Math.fma(this.rY, vZ, -(this.rZ * vY));
        return new Double3(Math.fma(this.rY, _t9, Math.fma(-this.rZ, _t10, Math.fma(this.rW, _t11, vX))), Math.fma(this.rZ, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vY))), Math.fma(this.rX, _t10, Math.fma(-this.rY, _t11, Math.fma(this.rW, _t9, vZ))));
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
        double _t9 = 2.0 * Math.fma(this.rX, vZ, -(this.rZ * vX));
        double _t10 = 2.0 * Math.fma(this.rY, vX, -(this.rX * vY));
        double _t11 = 2.0 * Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Double3(Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX))), Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY))), Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ))));
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
        double _t9 = 2.0 * Math.fma(this.rX, vZ, -(this.rZ * vX));
        double _t10 = 2.0 * Math.fma(this.rY, vX, -(this.rX * vY));
        double _t11 = 2.0 * Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Double3(Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX))), Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY))), Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ))));
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
        double _t0 = p.z() - this.tZ;
        double _t1 = p.x() - this.tX;
        double _t2 = p.y() - this.tY;
        double _t12 = 2.0 * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        double _t13 = 2.0 * Math.fma(this.rY, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        return new Double3(Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1))) / this.sX, Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2))) / this.sY, Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0))) / this.sZ);
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
        double _t0 = pZ - this.tZ;
        double _t1 = pX - this.tX;
        double _t2 = pY - this.tY;
        double _t12 = 2.0 * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        double _t13 = 2.0 * Math.fma(this.rY, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        return new Double3(Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1))) / this.sX, Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2))) / this.sY, Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0))) / this.sZ);
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
        double _t0 = this.sY * vY;
        double _t1 = this.sX * vX;
        double _t2 = this.sZ * vZ;
        double _t12 = 2.0 * Math.fma(this.rX, _t0, -(this.rY * _t1));
        double _t13 = 2.0 * Math.fma(this.rZ, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * Math.fma(this.rY, _t2, -(this.rZ * _t0));
        return new Double3(Math.fma(this.rY, _t12, Math.fma(-this.rZ, _t13, Math.fma(this.rW, _t14, Math.fma(this.sX, vX, this.tX)))), Math.fma(this.rZ, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, Math.fma(this.sY, vY, this.tY)))), Math.fma(this.rX, _t13, Math.fma(-this.rY, _t14, Math.fma(this.rW, _t12, Math.fma(this.sZ, vZ, this.tZ)))));
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
        double _t0 = pZ - this.tZ;
        double _t1 = pX - this.tX;
        double _t2 = pY - this.tY;
        double _t12 = 2.0 * Math.fma(this.rX, _t0, -(this.rZ * _t1));
        double _t13 = 2.0 * Math.fma(this.rY, _t1, -(this.rX * _t2));
        double _t14 = 2.0 * Math.fma(this.rZ, _t2, -(this.rY * _t0));
        return new Double3(Math.fma(this.rZ, _t12, Math.fma(-this.rY, _t13, Math.fma(this.rW, _t14, _t1))) / this.sX, Math.fma(this.rX, _t13, Math.fma(-this.rZ, _t14, Math.fma(this.rW, _t12, _t2))) / this.sY, Math.fma(this.rY, _t14, Math.fma(-this.rX, _t12, Math.fma(this.rW, _t13, _t0))) / this.sZ);
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
        double _t0 = this.sZ * vZ;
        double _t1 = this.sY * vY;
        double _t2 = this.sX * vX;
        double _t12 = 2.0 * Math.fma(this.rY, _t0, -(this.rZ * _t1));
        double _t13 = 2.0 * Math.fma(this.rX, _t1, -(this.rY * _t2));
        double _t14 = 2.0 * Math.fma(this.rZ, _t2, -(this.rX * _t0));
        return new Double3(Math.fma(this.sX, vX, Math.fma(this.rW, _t12, Math.fma(this.rY, _t13, -(this.rZ * _t14)))), Math.fma(this.sY, vY, Math.fma(this.rW, _t14, Math.fma(this.rZ, _t12, -(this.rX * _t13)))), Math.fma(this.sZ, vZ, Math.fma(this.rW, _t13, Math.fma(this.rX, _t14, -(this.rY * _t12)))));
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
        double _t0 = this.sZ * vZ;
        double _t1 = this.sY * vY;
        double _t2 = this.sX * vX;
        double _t12 = 2.0 * Math.fma(this.rY, _t0, -(this.rZ * _t1));
        double _t13 = 2.0 * Math.fma(this.rX, _t1, -(this.rY * _t2));
        double _t14 = 2.0 * Math.fma(this.rZ, _t2, -(this.rX * _t0));
        return new Double3(Math.fma(this.sX, vX, Math.fma(this.rW, _t12, Math.fma(this.rY, _t13, -(this.rZ * _t14)))), Math.fma(this.sY, vY, Math.fma(this.rW, _t14, Math.fma(this.rZ, _t12, -(this.rX * _t13)))), Math.fma(this.sZ, vZ, Math.fma(this.rW, _t13, Math.fma(this.rX, _t14, -(this.rY * _t12)))));
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
        double _t9 = 2.0 * Math.fma(this.rX, vZ, -(this.rZ * vX));
        double _t10 = 2.0 * Math.fma(this.rY, vX, -(this.rX * vY));
        double _t11 = 2.0 * Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Double3(Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX))) / this.sX, Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY))) / this.sY, Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ))) / this.sZ);
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
        double _t9 = 2.0 * Math.fma(this.rX, vZ, -(this.rZ * vX));
        double _t10 = 2.0 * Math.fma(this.rY, vX, -(this.rX * vY));
        double _t11 = 2.0 * Math.fma(this.rZ, vY, -(this.rY * vZ));
        return new Double3(Math.fma(this.rZ, _t9, Math.fma(-this.rY, _t10, Math.fma(this.rW, _t11, vX))) / this.sX, Math.fma(this.rX, _t10, Math.fma(-this.rZ, _t11, Math.fma(this.rW, _t9, vY))) / this.sY, Math.fma(this.rY, _t11, Math.fma(-this.rX, _t9, Math.fma(this.rW, _t10, vZ))) / this.sZ);
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
        return "DoubleTransform(" + tX() + ", " + tY() + ", " + tZ() + ", " + rX() + ", " + rY() + ", " + rZ() + ", " + rW() + ", " + sX() + ", " + sY() + ", " + sZ() + ")";
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
        h = 31 * h + (int)(Double.doubleToLongBits(tX) ^ (Double.doubleToLongBits(tX) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(tY) ^ (Double.doubleToLongBits(tY) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(tZ) ^ (Double.doubleToLongBits(tZ) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(rX) ^ (Double.doubleToLongBits(rX) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(rY) ^ (Double.doubleToLongBits(rY) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(rZ) ^ (Double.doubleToLongBits(rZ) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(rW) ^ (Double.doubleToLongBits(rW) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(sX) ^ (Double.doubleToLongBits(sX) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(sY) ^ (Double.doubleToLongBits(sY) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(sZ) ^ (Double.doubleToLongBits(sZ) >>> 32));
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
     * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and
     * a NaN component never compares equal to anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the value to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    public boolean equalsEpsilon(DoubleTransform other, double epsilon) {
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


    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform load(double[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment store(MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform load(MemorySegment src) { return StoreLoad.SEG_OPS.load(0L, src); }

    /**
     * Load the elements from the given memory segment, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store(float[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code float}, starting
     * at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform load(float[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code float}, starting
     * at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    public MemorySegment storeFloat(MemorySegment dest) { return StoreLoad.SEG_OPS.storeFloat(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code float},
     * starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return a new {@code DoubleTransform} holding the loaded elements
     */
    public static DoubleTransform loadFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadFloat(0L, src); }

    /**
     * Load the elements from the given memory segment, converting each element from {@code float},
     * starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
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
