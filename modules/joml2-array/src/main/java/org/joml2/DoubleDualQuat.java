// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.ByteBuffer;
import java.lang.foreign.MemorySegment;

/**
 * A dual quaternion of double-precision {@code double} components.
 * <p>
 * Extends the read-only view {@link DoubleDualQuatR} with the mutating surface: in-place
 * self-forms, setters and load methods. A self-form operation writes its result to {@code this} and
 * returns it - unless the {@code joml.returnNew} mode is enabled, in which case it leaves
 * {@code this} unchanged and returns a freshly allocated instance.
 * <p>
 * Instances are created through the {@link Joml} factory methods.
 * <p>
 * A rigid motion is a unit dual quaternion: a unit real part, and a dual part orthogonal to it. The
 * operations that apply, invert or convert this dual quaternion assume it and do not divide the
 * real part's length out. A value that has drifted from unit length (after many multiplications,
 * say) gives wrong results rather than an error: {@code normalize} it first.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Double.doubleToLongBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns). Only instances
 * of this library's implementation compare equal to each other; the {@code equals} of a dual
 * quaternion never returns {@code true} for an object of another type.
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 */
public interface DoubleDualQuat extends DoubleDualQuatR {

    /**
     * Add {@code other} to this dual quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat add(DoubleDualQuatR other) { return add(other, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Add ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ},
     * {@code dW}) to this dual quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param rX the {@code rX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rY the {@code rY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rZ the {@code rZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rW the {@code rW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dX the {@code dX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dY the {@code dY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dZ the {@code dZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dW the {@code dW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat add(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW) { return add(rX, rY, rZ, rW, dX, dY, dZ, dW, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Multiply each component of this dual quaternion by {@code scalar}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat mul(double scalar) { return mul(scalar, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Negate this dual quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat negate() { return negate(Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Subtract {@code other} from this dual quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to subtract
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat sub(DoubleDualQuatR other) { return sub(other, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Subtract ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ},
     * {@code dW}) from this dual quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param rX the {@code rX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rY the {@code rY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rZ the {@code rZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rW the {@code rW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dX the {@code dX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dY the {@code dY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dZ the {@code dZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dW the {@code dW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat sub(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW) { return sub(rX, rY, rZ, rW, dX, dY, dZ, dW, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Set this dual quaternion to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the dual quaternion to copy
     * @return this
     */
    @Mutated DoubleDualQuat set(DoubleDualQuatR v);

    /**
     * Set this dual quaternion to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param rX the {@code rX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rY the {@code rY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rZ the {@code rZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rW the {@code rW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dX the {@code dX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dY the {@code dY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dZ the {@code dZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dW the {@code dW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @return this
     */
    @Mutated DoubleDualQuat set(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW);

    /**
     * Convert this dual quaternion to {@code float} precision, returning the result as a new
     * instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code FloatDualQuat} holding the result
     */
    default FloatDualQuat toFloat() { return toFloat(Joml.floatDualQuat()); }

    /**
     * Set this dual quaternion to the rigid motion of the given rigid transform (an exact
     * conversion - both represent rotation plus translation).
     * <p>
     * Valid input: the rotation of {@code r} must have unit length.
     *
     * @param r the rigid transform to convert
     * @return this
     */
    @Mutated DoubleDualQuat makeFromRigid(DoubleRigidR r);

    /**
     * Set this dual quaternion to the rigid motion of the given rigid transform (an exact
     * conversion - both represent rotation plus translation).
     * <p>
     * Valid input: {@code (rX, rY, rZ, rW)} must have unit length.
     *
     * @param tX the {@code tX} component of the rigid transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW)}
     * @param tY the {@code tY} component of the rigid transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW)}
     * @param tZ the {@code tZ} component of the rigid transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW)}
     * @param rX the {@code rX} component of the rigid transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW)}
     * @param rY the {@code rY} component of the rigid transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW)}
     * @param rZ the {@code rZ} component of the rigid transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW)}
     * @param rW the {@code rW} component of the rigid transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW)}
     * @return this
     */
    @Mutated DoubleDualQuat makeFromRigid(double tX, double tY, double tZ, double rX, double rY, double rZ, double rW);

    /**
     * Set this dual quaternion to the rigid motion (rotation and translation) of the given
     * transform; the scale is dropped (dual quaternions cannot represent it).
     * <p>
     * Valid input: the rotation of {@code t} must have unit length.
     *
     * @param t the transform to convert
     * @return this
     */
    @Mutated DoubleDualQuat makeFromTransform(DoubleTransformR t);

    /**
     * Set this dual quaternion to the rigid motion (rotation and translation) of the given
     * transform; the scale is dropped (dual quaternions cannot represent it).
     * <p>
     * Valid input: {@code (rX, rY, rZ, rW)} must have unit length.
     *
     * @param tX the {@code tX} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param tY the {@code tY} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param tZ the {@code tZ} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param rX the {@code rX} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param rY the {@code rY} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param rZ the {@code rZ} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param rW the {@code rW} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param sX the {@code sX} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param sY the {@code sY} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param sZ the {@code sZ} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @return this
     */
    @Mutated DoubleDualQuat makeFromTransform(double tX, double tY, double tZ, double rX, double rY, double rZ, double rW, double sX, double sY, double sZ);

    /**
     * Set this dual quaternion to the rotation of {@code angle} radians about the axis
     * {@code axis}, combined with a translation by {@code translation}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param axis the rotation axis
     * @param angle the angle in radians
     * @param translation the translation
     * @return this
     */
    @Mutated DoubleDualQuat makeFromAxisAngle(Double3R axis, double angle, Double3R translation);

    /**
     * Set this dual quaternion to the rotation of {@code angle} radians about the axis
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
    @Mutated DoubleDualQuat makeFromAxisAngle(double axisX, double axisY, double axisZ, double angle, double translationX, double translationY, double translationZ);

    /**
     * Set this dual quaternion to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated DoubleDualQuat makeIdentity();

    /**
     * Set this dual quaternion to a rigid transformation that first rotates by {@code rotation} and
     * then translates by {@code translation} ({@code T * R}).
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param translation the translation
     * @param rotation the rotation
     * @return this
     */
    @Mutated DoubleDualQuat makeTranslationRotation(Double3R translation, DoubleQuatR rotation);

    /**
     * Set this dual quaternion to a rigid transformation that first rotates by ({@code rotationX},
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
    @Mutated DoubleDualQuat makeTranslationRotation(double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW);

    /**
     * Set all components of this dual quaternion to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated DoubleDualQuat makeZero();

    /**
     * Set this dual quaternion to a pure rotation by {@code rotation} (zero translation).
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @return this
     */
    @Mutated DoubleDualQuat set(DoubleQuatR rotation);

    /**
     * Set this dual quaternion to a pure rotation by ({@code x}, {@code y}, {@code z}, {@code w})
     * (zero translation).
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @return this
     */
    @Mutated DoubleDualQuat set(double x, double y, double z, double w);

    /**
     * Set this dual quaternion to a pure rotation by {@code rotation} (zero translation).
     * <p>
     * Alias for {@code set}.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @return this
     */
    @Mutated default DoubleDualQuat makeRotation(DoubleQuatR rotation) { return set(rotation); }

    /**
     * Set this dual quaternion to a pure rotation by ({@code x}, {@code y}, {@code z}, {@code w})
     * (zero translation).
     * <p>
     * Alias for {@code set}.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @return this
     */
    @Mutated default DoubleDualQuat makeRotation(double x, double y, double z, double w) { return set(x, y, z, w); }

    /**
     * Set this dual quaternion to the given values.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @param translation the translation
     * @return this
     */
    @Mutated DoubleDualQuat set(DoubleQuatR rotation, Double3R translation);

    /**
     * Set this dual quaternion to the given values.
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
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @return this
     */
    @Mutated DoubleDualQuat set(double rotationX, double rotationY, double rotationZ, double rotationW, double translationX, double translationY, double translationZ);

    /**
     * Set this dual quaternion to a pure translation by {@code translation} (identity rotation).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation
     * @return this
     */
    @Mutated DoubleDualQuat set(Double3R translation);

    /**
     * Set this dual quaternion to a pure translation by ({@code x}, {@code y}, {@code z}) (identity
     * rotation).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this
     */
    @Mutated DoubleDualQuat set(double x, double y, double z);

    /**
     * Set this dual quaternion to a pure translation by {@code translation} (identity rotation).
     * <p>
     * Alias for {@code set}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation
     * @return this
     */
    @Mutated default DoubleDualQuat makeTranslation(Double3R translation) { return set(translation); }

    /**
     * Set this dual quaternion to a pure translation by ({@code x}, {@code y}, {@code z}) (identity
     * rotation).
     * <p>
     * Alias for {@code set}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this
     */
    @Mutated default DoubleDualQuat makeTranslation(double x, double y, double z) { return set(x, y, z); }

    /**
     * Blend this dual quaternion with {@code other} using dual-quaternion linear blending with the
     * weight {@code t}.
     * <p>
     * The interpolation starts at this dual quaternion (weight {@code 0}) and ends at {@code other}
     * (weight {@code 1}).
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code other} must be a
     * unit dual quaternion; {@code t} must lie in {@code [0, 1]}.
     *
     * @param other the dual quaternion to blend towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat dlb(DoubleDualQuatR other, double t) { return dlb(other, t, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Blend this dual quaternion with ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX},
     * {@code dY}, {@code dZ}, {@code dW}) using dual-quaternion linear blending with the weight
     * {@code t}.
     * <p>
     * The interpolation starts at this dual quaternion (weight {@code 0}) and ends at ({@code rX},
     * {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ}, {@code dW}) (weight
     * {@code 1}).
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion;
     * {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)} must be a unit dual quaternion; {@code t} must lie
     * in {@code [0, 1]}.
     *
     * @param rX the {@code rX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rY the {@code rY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rZ the {@code rZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rW the {@code rW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dX the {@code dX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dY the {@code dY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dZ the {@code dZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dW the {@code dW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat dlb(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW, double t) { return dlb(rX, rY, rZ, rW, dX, dY, dZ, dW, t, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Linearly interpolate between this dual quaternion and {@code other} using the interpolation
     * factor {@code t}.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat lerp(DoubleDualQuatR other, double t) { return lerp(other, t, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Linearly interpolate between this dual quaternion and ({@code rX}, {@code rY}, {@code rZ},
     * {@code rW}, {@code dX}, {@code dY}, {@code dZ}, {@code dW}) using the interpolation factor
     * {@code t}.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ},
     * {@code dW}) (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param rX the {@code rX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rY the {@code rY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rZ the {@code rZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rW the {@code rW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dX the {@code dX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dY the {@code dY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dZ the {@code dZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dW the {@code dW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat lerp(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW, double t) { return lerp(rX, rY, rZ, rW, dX, dY, dZ, dW, t, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Screw-linearly interpolate between this dual quaternion and {@code other} using the
     * interpolation factor {@code t}.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code other} must be a unit dual quaternion; this dual quaternion must be a
     * unit dual quaternion.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat sclerp(DoubleDualQuatR other, double t) { return sclerp(other, t, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Screw-linearly interpolate between this dual quaternion and ({@code rX}, {@code rY},
     * {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ}, {@code dW}) using the
     * interpolation factor {@code t}.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ},
     * {@code dW}) (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)} must be a unit dual quaternion; this
     * dual quaternion must be a unit dual quaternion.
     *
     * @param rX the {@code rX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rY the {@code rY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rZ the {@code rZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rW the {@code rW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dX the {@code dX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dY the {@code dY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dZ the {@code dZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dW the {@code dW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat sclerp(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW, double t) { return sclerp(rX, rY, rZ, rW, dX, dY, dZ, dW, t, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Multiply this dual quaternion by {@code other}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code Q * R * v}, the transformation of the operand will be applied
     * first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat mul(DoubleDualQuatR other) { return mul(other, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Multiply this dual quaternion by ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX},
     * {@code dY}, {@code dZ}, {@code dW}).
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code Q * R * v}, the transformation of the operand will be applied
     * first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param rX the {@code rX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rY the {@code rY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rZ the {@code rZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rW the {@code rW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dX the {@code dX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dY the {@code dY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dZ the {@code dZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dW the {@code dW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat mul(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW) { return mul(rX, rY, rZ, rW, dX, dY, dZ, dW, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Pre-multiply {@code other} onto this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code R * Q * v}, the transformation of the operand will be applied
     * last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat preMul(DoubleDualQuatR other) { return preMul(other, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Pre-multiply ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY},
     * {@code dZ}, {@code dW}) onto this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code R * Q * v}, the transformation of the operand will be applied
     * last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param rX the {@code rX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rY the {@code rY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rZ the {@code rZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rW the {@code rW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dX the {@code dX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dY the {@code dY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dZ the {@code dZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dW the {@code dW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat preMul(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW) { return preMul(rX, rY, rZ, rW, dX, dY, dZ, dW, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Add {@code other} scaled by {@code weight} to this dual quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat addScaled(DoubleDualQuatR other, double weight) { return addScaled(other, weight, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Add ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ},
     * {@code dW}) scaled by {@code weight} to this dual quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param rX the {@code rX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rY the {@code rY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rZ the {@code rZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rW the {@code rW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dX the {@code dX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dY the {@code dY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dZ the {@code dZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dW the {@code dW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param weight the factor to scale ({@code rX}, {@code rY}, {@code rZ}, {@code rW},
     *        {@code dX}, {@code dY}, {@code dZ}, {@code dW}) by before adding
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat addScaled(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW, double weight) { return addScaled(rX, rY, rZ, rW, dX, dY, dZ, dW, weight, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Compute the quaternion conjugate of this dual quaternion, conjugating both the real and the
     * dual part (for a unit dual quaternion this is its inverse).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat conjugate() { return conjugate(Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Compute the difference between this dual quaternion and {@code other}, i.e. the rigid
     * transformation {@code D} with {@code this * D = other}, that is {@code D = this^-1 * other}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param other the target dual quaternion, reached by composing this dual quaternion with the
     *        result
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat difference(DoubleDualQuatR other) { return difference(other, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Compute the difference between this dual quaternion and ({@code rX}, {@code rY}, {@code rZ},
     * {@code rW}, {@code dX}, {@code dY}, {@code dZ}, {@code dW}), i.e. the rigid transformation
     * {@code D} with {@code this * D = (rX, rY, rZ, rW, dX, dY, dZ, dW)}, that is
     * {@code D = this^-1 * (rX, rY, rZ, rW, dX, dY, dZ, dW)}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param rX the {@code rX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rY the {@code rY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rZ the {@code rZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rW the {@code rW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dX the {@code dX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dY the {@code dY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dZ the {@code dZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dW the {@code dW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat difference(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW) { return difference(rX, rY, rZ, rW, dX, dY, dZ, dW, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Compute the dual-number conjugate of this dual quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat dualConjugate() { return dualConjugate(Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Compute the exponential of this dual quaternion.
     * <p>
     * This dual quaternion is read as a screw-motion generator, a pure dual quaternion as
     * {@code log} returns it: its scalar parts {@code rW} and {@code dW} are taken as zero and
     * ignored. The result is a unit dual quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat exp() { return exp(Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Compute the inverse of this dual quaternion (its conjugate, for a unit dual quaternion).
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat inverseUnit() { return inverseUnit(Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Invert this dual quaternion.
     * <p>
     * Valid input: the real part of this dual quaternion must be non-zero.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat invert() { return invert(Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Compute the natural logarithm of this dual quaternion.
     * <p>
     * This dual quaternion must be a unit dual quaternion; the result is pure (both scalar parts
     * zero), the input {@code exp} expects.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat log() { return log(Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Set this dual quaternion to the rigid motion of the given matrix: rotation from its
     * upper-left 3x3 block and translation from its last column.
     * <p>
     * Valid input: {@code m} must be a rotation matrix.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated DoubleDualQuat makeFromMatrix(Double4x4R m);

    /**
     * Set this dual quaternion to the rigid motion of the given matrix: rotation from its
     * upper-left 3x3 block and translation from its last column.
     * <p>
     * Valid input: {@code m} must be a rotation matrix.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated DoubleDualQuat makeFromMatrix(Double3x4R m);

    /**
     * Set this dual quaternion to the rotation represented by the given matrix (which must be a
     * rotation: orthonormal, with determinant +1 - a scaled or sheared block gives a wrong
     * quaternion, not a longer one; {@code getNormalizedRotation} strips scale first), with zero
     * translation.
     * <p>
     * Valid input: {@code m} must be a rotation matrix.
     *
     * @param m the matrix to convert
     * @return this
     */
    @Mutated DoubleDualQuat makeFromMatrix(Double3x3R m);

    /**
     * Normalize this dual quaternion so that its real (rotation) part has unit length (a zero real
     * part yields the zero dual quaternion).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat normalize() { return normalize(Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Raise this dual quaternion to the power of {@code t} (screw-motion power: {@code t = 0}
     * yields the identity, {@code t = 1} yields {@code this}).
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param t the exponent
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat pow(double t) { return pow(t, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Set the dual half of this dual quaternion to {@code dual}, keeping the real (rotation) half
     * as it is.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} follows the new dual half; use
     * {@code setTranslation} to set the translation itself.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dual the new dual half
     * @return this
     */
    @Mutated default DoubleDualQuat setDual(DoubleQuatR dual) { return setDual(dual, this); }

    /**
     * Set the dual half of this dual quaternion to ({@code x}, {@code y}, {@code z}, {@code w}),
     * keeping the real (rotation) half as it is.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} follows the new dual half; use
     * {@code setTranslation} to set the translation itself.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the new dual half {@code (x, y, z, w)}
     * @param y the {@code y} component of the new dual half {@code (x, y, z, w)}
     * @param z the {@code z} component of the new dual half {@code (x, y, z, w)}
     * @param w the {@code w} component of the new dual half {@code (x, y, z, w)}
     * @return this
     */
    @Mutated default DoubleDualQuat setDual(double x, double y, double z, double w) { return setDual(x, y, z, w, this); }

    /**
     * Set the real (rotation) half of this dual quaternion to {@code real}, keeping the dual half
     * as it is.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} changes with the real half; use
     * {@code setRotation} to replace the rotation and keep the translation.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param real the new real (rotation) half
     * @return this
     */
    @Mutated default DoubleDualQuat setReal(DoubleQuatR real) { return setReal(real, this); }

    /**
     * Set the real (rotation) half of this dual quaternion to ({@code x}, {@code y}, {@code z},
     * {@code w}), keeping the dual half as it is.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} changes with the real half; use
     * {@code setRotation} to replace the rotation and keep the translation.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the new real (rotation) half {@code (x, y, z, w)}
     * @param y the {@code y} component of the new real (rotation) half {@code (x, y, z, w)}
     * @param z the {@code z} component of the new real (rotation) half {@code (x, y, z, w)}
     * @param w the {@code w} component of the new real (rotation) half {@code (x, y, z, w)}
     * @return this
     */
    @Mutated default DoubleDualQuat setReal(double x, double y, double z, double w) { return setReal(x, y, z, w, this); }

    /**
     * Set the rotation of this dual quaternion to {@code rotation}.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the new rotation
     * @return this
     */
    @Mutated default DoubleDualQuat setRotation(DoubleQuatR rotation) { return setRotation(rotation, this); }

    /**
     * Set the rotation of this dual quaternion to ({@code x}, {@code y}, {@code z}, {@code w}).
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @return this
     */
    @Mutated default DoubleDualQuat setRotation(double x, double y, double z, double w) { return setRotation(x, y, z, w, this); }

    /**
     * Set the translation of this dual quaternion to {@code translation}.
     * <p>
     * Valid input: the real part of this dual quaternion must have unit length.
     *
     * @param translation the new translation
     * @return this
     */
    @Mutated default DoubleDualQuat setTranslation(Double3R translation) { return setTranslation(translation, this); }

    /**
     * Set the translation of this dual quaternion to ({@code x}, {@code y}, {@code z}).
     * <p>
     * Valid input: the real part of this dual quaternion must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this
     */
    @Mutated default DoubleDualQuat setTranslation(double x, double y, double z) { return setTranslation(x, y, z, this); }

    /**
     * Apply a rotation transformation that makes {@code +z} point along {@code dir} to this dual
     * quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction the local {@code +z} axis is
     *        mapped to
     * @param up the direction of "up"
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat lookAlong(Double3R dir, Double3R up) { return lookAlong(dir, up, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Apply a rotation transformation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}) to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code L} the "look along" dual quaternion,
     * then the new dual quaternion will be {@code Q * L}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * L * v}, the "look along" will be applied
     * first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) { return lookAlong(dirX, dirY, dirZ, upX, upY, upZ, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Set this dual quaternion to a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return this
     */
    @Mutated DoubleDualQuat makeRotationAxis(double angle, Double3R axis);

    /**
     * Set this dual quaternion to a rotation of {@code angle} radians about the axis ({@code x},
     * {@code y}, {@code z}).
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length.
     *
     * @param angle the angle in radians
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this
     */
    @Mutated DoubleDualQuat makeRotationAxis(double angle, double x, double y, double z);

    /**
     * Set this dual quaternion to a rotation that makes {@code +z} point along {@code dir}.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction the local {@code +z} axis is
     *        mapped to
     * @param up the direction of "up"
     * @return this
     */
    @Mutated DoubleDualQuat makeRotationLookAlong(Double3R dir, Double3R up);

    /**
     * Set this dual quaternion to a rotation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * need a non-zero direction and up vector that are not parallel.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @return this
     */
    @Mutated DoubleDualQuat makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ);

    /**
     * Set this dual quaternion to a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated DoubleDualQuat makeRotationX(double angle);

    /**
     * Set this dual quaternion to a rotation of {@code angleX}, {@code angleY} and {@code angleZ}
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
    @Mutated DoubleDualQuat makeRotationXYZ(double angleX, double angleY, double angleZ);

    /**
     * Set this dual quaternion to a rotation of {@code angleX}, {@code angleZ} and {@code angleY}
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
    @Mutated DoubleDualQuat makeRotationXZY(double angleX, double angleZ, double angleY);

    /**
     * Set this dual quaternion to a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated DoubleDualQuat makeRotationY(double angle);

    /**
     * Set this dual quaternion to a rotation of {@code angleY}, {@code angleX} and {@code angleZ}
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
    @Mutated DoubleDualQuat makeRotationYXZ(double angleY, double angleX, double angleZ);

    /**
     * Set this dual quaternion to a rotation of {@code angleY}, {@code angleZ} and {@code angleX}
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
    @Mutated DoubleDualQuat makeRotationYZX(double angleY, double angleZ, double angleX);

    /**
     * Set this dual quaternion to a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated DoubleDualQuat makeRotationZ(double angle);

    /**
     * Set this dual quaternion to a rotation of {@code angleZ}, {@code angleX} and {@code angleY}
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
    @Mutated DoubleDualQuat makeRotationZXY(double angleZ, double angleX, double angleY);

    /**
     * Set this dual quaternion to a rotation of {@code angleZ}, {@code angleY} and {@code angleX}
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
    @Mutated DoubleDualQuat makeRotationZYX(double angleZ, double angleY, double angleX);

    /**
     * Pre-multiply the rotation represented by the quaternion {@code rotation} onto this dual
     * quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code R * Q}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat preRotate(DoubleQuatR rotation) { return preRotate(rotation, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Pre-multiply the rotation represented by the quaternion ({@code x}, {@code y}, {@code z},
     * {@code w}) onto this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code R * Q}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat preRotate(double x, double y, double z, double w) { return preRotate(x, y, z, w, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Apply the rotation represented by the quaternion {@code rotation} to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation to apply
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat rotate(DoubleQuatR rotation) { return rotate(rotation, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Apply the rotation represented by the quaternion ({@code x}, {@code y}, {@code z}, {@code w})
     * to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat rotate(double x, double y, double z, double w) { return rotate(x, y, z, w, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this dual
     * quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat rotateAxis(double angle, Double3R axis) { return rotateAxis(angle, axis, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code x}, {@code y}, {@code z}) to
     * this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length.
     *
     * @param angle the angle in radians
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat rotateAxis(double angle, double x, double y, double z) { return rotateAxis(angle, x, y, z, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Apply a rotation of {@code angle} radians about the X axis to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat rotateX(double angle) { return rotateX(angle, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat rotateXYZ(double angleX, double angleY, double angleZ) { return rotateXYZ(angleX, angleY, angleZ, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat rotateXZY(double angleX, double angleZ, double angleY) { return rotateXZY(angleX, angleZ, angleY, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Apply a rotation of {@code angle} radians about the Y axis to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat rotateY(double angle) { return rotateY(angle, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat rotateYXZ(double angleY, double angleX, double angleZ) { return rotateYXZ(angleY, angleX, angleZ, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat rotateYZX(double angleY, double angleZ, double angleX) { return rotateYZX(angleY, angleZ, angleX, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Apply a rotation of {@code angle} radians about the Z axis to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat rotateZ(double angle) { return rotateZ(angle, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat rotateZXY(double angleZ, double angleX, double angleY) { return rotateZXY(angleZ, angleX, angleY, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat rotateZYX(double angleZ, double angleY, double angleX) { return rotateZYX(angleZ, angleY, angleX, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Apply a translation by {@code translation} to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code T} the translation dual quaternion,
     * then the new dual quaternion will be {@code Q * T}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * T * v}, the translation will be applied
     * first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation offsets
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat translate(Double3R translation) { return translate(translation, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Apply a translation by ({@code x}, {@code y}, {@code z}) to this dual quaternion.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code T} the translation dual quaternion,
     * then the new dual quaternion will be {@code Q * T}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * T * v}, the translation will be applied
     * first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default DoubleDualQuat translate(double x, double y, double z) { return translate(x, y, z, Joml.RETURN_NEW ? Joml.doubleDualQuat() : this); }

    /**
     * Set this dual quaternion from its real and dual parts.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param real the real part, i.e. the rotation quaternion
     * @param dual the dual part, carrying the translation
     * @return this
     */
    @Mutated default DoubleDualQuat set(DoubleQuatR real, DoubleQuatR dual) { return set(real.x(), real.y(), real.z(), real.w(), dual.x(), dual.y(), dual.z(), dual.w()); }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default DoubleDualQuat load(double[] src) { return load(src, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated DoubleDualQuat load(double[] src, int offset);

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
     * @param src the source buffer
     * @return this
     */
    @Mutated default DoubleDualQuat load(DoubleBuffer src) { return loadAbsolute(src.position(), src); }

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
     * @param src the source buffer
     * @return this
     */
    @Mutated default DoubleDualQuat loadAbsolute(DoubleBuffer src) { return loadAbsolute(src.position(), src); }

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
     * @param src the source buffer
     * @return this
     */
    @Mutated DoubleDualQuat loadAbsolute(int index, DoubleBuffer src);

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
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default DoubleDualQuat loadRelative(DoubleBuffer src) {
        if (src.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 8);
        return this;
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default DoubleDualQuat load(ByteBuffer src) { return loadAbsolute(src.position(), src); }

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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default DoubleDualQuat loadAbsolute(ByteBuffer src) { return loadAbsolute(src.position(), src); }

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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated DoubleDualQuat loadAbsolute(int index, ByteBuffer src);

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
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default DoubleDualQuat loadRelative(ByteBuffer src) {
        if (src.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 64);
        return this;
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
     * @return this
     */
    @Mutated default DoubleDualQuat load(MemorySegment src) { return load(0L, src); }

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
     * @return this
     */
    @Mutated DoubleDualQuat load(long offset, MemorySegment src);

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    @Mutated DoubleDualQuat loadUnsafe(long address);

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default DoubleDualQuat load(float[] src) { return load(src, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated DoubleDualQuat load(float[] src, int offset);

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
     * @param src the source buffer
     * @return this
     */
    @Mutated default DoubleDualQuat load(FloatBuffer src) { return loadAbsolute(src.position(), src); }

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
     * @param src the source buffer
     * @return this
     */
    @Mutated default DoubleDualQuat loadAbsolute(FloatBuffer src) { return loadAbsolute(src.position(), src); }

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
     * @param src the source buffer
     * @return this
     */
    @Mutated DoubleDualQuat loadAbsolute(int index, FloatBuffer src);

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
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default DoubleDualQuat loadRelative(FloatBuffer src) {
        if (src.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 8);
        return this;
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default DoubleDualQuat loadFloat(ByteBuffer src) { return loadFloatAbsolute(src.position(), src); }

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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default DoubleDualQuat loadFloatAbsolute(ByteBuffer src) { return loadFloatAbsolute(src.position(), src); }

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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated DoubleDualQuat loadFloatAbsolute(int index, ByteBuffer src);

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
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default DoubleDualQuat loadFloatRelative(ByteBuffer src) {
        if (src.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadFloatAbsolute(pos, src);
        src.position(pos + 32);
        return this;
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
     * @return this
     */
    @Mutated default DoubleDualQuat loadFloat(MemorySegment src) { return loadFloat(0L, src); }

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
     * @return this
     */
    @Mutated DoubleDualQuat loadFloat(long offset, MemorySegment src);

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    @Mutated DoubleDualQuat loadFloatUnsafe(long address);
}
