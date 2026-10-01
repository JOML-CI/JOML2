// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import java.nio.ByteBuffer;
import java.lang.foreign.MemorySegment;

/**
 * Read-only view of a quaternion of single-precision {@code float} components.
 * <p>
 * Declares the accessors, queries, store methods and dest-form operations that read but never
 * mutate the receiver; the mutable counterpart is {@link FloatQuat}. APIs that only read a
 * quaternion should accept {@code FloatQuatR}, so callers can pass mutable instances without
 * exposing them to modification.
 * <p>
 * Arguments of type {@code FloatQuatR} must be instances created by the library ({@link Joml}
 * factories / the library's own types); the implementations read cached state through the library's
 * own classes, so foreign implementations of the {@code *R} interfaces are not supported as
 * arguments.
 * <p>
 * A rotation is a unit quaternion. The operations that apply this quaternion as a rotation - the
 * transforms, the matrix conversions, the Euler angles, {@code angleTo} and {@code rotateTowards} -
 * assume unit length and do not divide it out. A value that has drifted from unit length (after
 * many multiplications, say) gives wrong results rather than an error: {@code normalize} it first.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns). Only instances
 * of this library's implementation compare equal to each other; the {@code equals} of a quaternion
 * never returns {@code true} for an object of another type.
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 */
public interface FloatQuatR {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 16;

    /** {@return a shared identity quaternion} Never mutate it - it is returned as the read-only view for that reason. <p>Valid input: any value, NaN and the infinities included. */
    public static FloatQuatR IDENTITY() { return Holder.IDENTITY; }
    /** {@return a shared all-zero quaternion} Never mutate it - it is returned as the read-only view for that reason. <p>Valid input: any value, NaN and the infinities included. */
    public static FloatQuatR ZERO() { return Holder.ZERO; }


    /**
     * Invert this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat invert(@Mutated FloatQuat dest);

    /**
     * Invert this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat invert(@Mutated DoubleQuat dest);

    /**
     * Compute the inverse of the product of this quaternion and {@code other}, i.e.
     * {@code (this * other)^-1} and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero; {@code other} must be non-zero.
     *
     * @param other the right factor of the product
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat invertProduct(FloatQuatR other, @Mutated FloatQuat dest);

    /**
     * Compute the inverse of the product of this quaternion and {@code other}, i.e.
     * {@code (this * other)^-1} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must be non-zero; {@code other} must be non-zero.
     *
     * @param other the right factor of the product
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat invertProduct(FloatQuatR other, @Mutated DoubleQuat dest);

    /**
     * Compute the inverse of the product of this quaternion and ({@code x}, {@code y}, {@code z},
     * {@code w}) and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero; {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat invertProduct(float x, float y, float z, float w, @Mutated FloatQuat dest);

    /**
     * Compute the inverse of the product of this quaternion and ({@code x}, {@code y}, {@code z},
     * {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must be non-zero; {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat invertProduct(float x, float y, float z, float w, @Mutated DoubleQuat dest);

    /**
     * Add {@code other} to this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to add
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat add(FloatQuatR other, @Mutated FloatQuat dest);

    /**
     * Add {@code other} to this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to add
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat add(FloatQuatR other, @Mutated DoubleQuat dest);

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat add(float x, float y, float z, float w, @Mutated FloatQuat dest);

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat add(float x, float y, float z, float w, @Mutated DoubleQuat dest);

    /**
     * Multiply each component of this quaternion by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat mul(float scalar, @Mutated FloatQuat dest);

    /**
     * Multiply each component of this quaternion by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat mul(float scalar, @Mutated DoubleQuat dest);

    /**
     * Negate this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat negate(@Mutated FloatQuat dest);

    /**
     * Negate this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat negate(@Mutated DoubleQuat dest);

    /**
     * Subtract {@code other} from this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to subtract
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat sub(FloatQuatR other, @Mutated FloatQuat dest);

    /**
     * Subtract {@code other} from this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to subtract
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat sub(FloatQuatR other, @Mutated DoubleQuat dest);

    /**
     * Subtract ({@code x}, {@code y}, {@code z}, {@code w}) from this quaternion and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat sub(float x, float y, float z, float w, @Mutated FloatQuat dest);

    /**
     * Subtract ({@code x}, {@code y}, {@code z}, {@code w}) from this quaternion and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat sub(float x, float y, float z, float w, @Mutated DoubleQuat dest);

    /**
     * Convert this quaternion to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat toDouble(@Mutated DoubleQuat dest);

    /**
     * Convert this quaternion to a pure-rotation dual quaternion (zero dual part) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat toDualQuat(@Mutated FloatDualQuat dest);

    /**
     * Convert this quaternion to a pure-rotation dual quaternion (zero dual part) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat toDualQuat(@Mutated DoubleDualQuat dest);

    /**
     * Compute the matrix representation of this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4x4 toMatrix(@Mutated Float4x4 dest);

    /**
     * Compute the matrix representation of this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 toMatrix(@Mutated Double4x4 dest);

    /**
     * Compute the 3x3 rotation matrix representation of this quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3x3 toMatrix3x3(@Mutated Float3x3 dest);

    /**
     * Compute the 3x3 rotation matrix representation of this quaternion and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3x3 toMatrix3x3(@Mutated Double3x3 dest);

    /**
     * Compute the 3x4 matrix representation of this quaternion (the omitted last row is implicitly
     * {@code 0, 0, 0, 1}) and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3x4 toMatrix3x4(@Mutated Float3x4 dest);

    /**
     * Compute the 3x4 matrix representation of this quaternion (the omitted last row is implicitly
     * {@code 0, 0, 0, 1}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3x4 toMatrix3x4(@Mutated Double3x4 dest);

    /**
     * Decompose this quaternion into a swing about an axis perpendicular to {@code axis} followed
     * by a twist about {@code axis}, storing them in {@code swing} and {@code twist} respectively,
     * such that {@code swing * twist} is this rotation.
     * <p>
     * Equivalent to calling {@code getSwing} and {@code getTwist} separately, but shares the work.
     * The twist is the identity when the rotation is a pure swing, including the 180-degree
     * perpendicular case where it is undefined.
     * <p>
     * Valid input: {@code axis} must have unit length; this quaternion must have unit length.
     *
     * @param axis the rotation axis
     * @param swing will hold the swing
     * @param twist will hold the twist
     * @return this
     */
    FloatQuat decomposeSwingTwist(Float3R axis, @Mutated FloatQuat swing, @Mutated FloatQuat twist);

    /**
     * Decompose this quaternion into a swing about an axis perpendicular to {@code axis} followed
     * by a twist about {@code axis}, storing them in {@code swing} and {@code twist} respectively,
     * such that {@code swing * twist} is this rotation.
     * <p>
     * Equivalent to calling {@code getSwing} and {@code getTwist} separately, but shares the work.
     * The twist is the identity when the rotation is a pure swing, including the 180-degree
     * perpendicular case where it is undefined.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code axis} must have unit length; this quaternion must have unit length.
     *
     * @param axis the rotation axis
     * @param swing will hold the swing
     * @param twist will hold the twist
     * @return this
     */
    FloatQuat decomposeSwingTwist(Float3R axis, @Mutated DoubleQuat swing, @Mutated DoubleQuat twist);

    /**
     * Decompose this quaternion into a swing about an axis perpendicular to ({@code x}, {@code y},
     * {@code z}) followed by a twist about ({@code x}, {@code y}, {@code z}), storing them in
     * {@code swing} and {@code twist} respectively, such that {@code swing * twist} is this
     * rotation.
     * <p>
     * Equivalent to calling {@code getSwing} and {@code getTwist} separately, but shares the work.
     * The twist is the identity when the rotation is a pure swing, including the 180-degree
     * perpendicular case where it is undefined.
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length; this quaternion must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param swing will hold the swing
     * @param twist will hold the twist
     * @return this
     */
    FloatQuat decomposeSwingTwist(float x, float y, float z, @Mutated FloatQuat swing, @Mutated FloatQuat twist);

    /**
     * Decompose this quaternion into a swing about an axis perpendicular to ({@code x}, {@code y},
     * {@code z}) followed by a twist about ({@code x}, {@code y}, {@code z}), storing them in
     * {@code swing} and {@code twist} respectively, such that {@code swing * twist} is this
     * rotation.
     * <p>
     * Equivalent to calling {@code getSwing} and {@code getTwist} separately, but shares the work.
     * The twist is the identity when the rotation is a pure swing, including the 180-degree
     * perpendicular case where it is undefined.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length; this quaternion must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param swing will hold the swing
     * @param twist will hold the twist
     * @return this
     */
    FloatQuat decomposeSwingTwist(float x, float y, float z, @Mutated DoubleQuat swing, @Mutated DoubleQuat twist);

    /**
     * Extract the swing component of this quaternion: the rotation perpendicular to {@code axis} in
     * the swing-twist decomposition and store the result in {@code dest}.
     * <p>
     * The swing carries the rotation that tilts the axis itself; its own axis is perpendicular to
     * the given one.
     * <p>
     * Valid input: {@code axis} must have unit length; this quaternion must have unit length.
     *
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat getSwing(Float3R axis, @Mutated FloatQuat dest);

    /**
     * Extract the swing component of this quaternion: the rotation perpendicular to {@code axis} in
     * the swing-twist decomposition and store the result in {@code dest}.
     * <p>
     * The swing carries the rotation that tilts the axis itself; its own axis is perpendicular to
     * the given one.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code axis} must have unit length; this quaternion must have unit length.
     *
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat getSwing(Float3R axis, @Mutated DoubleQuat dest);

    /**
     * Extract the swing component of this quaternion: the rotation perpendicular to ({@code x},
     * {@code y}, {@code z}) in the swing-twist decomposition and store the result in {@code dest}.
     * <p>
     * The swing carries the rotation that tilts the axis itself; its own axis is perpendicular to
     * the given one.
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length; this quaternion must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat getSwing(float x, float y, float z, @Mutated FloatQuat dest);

    /**
     * Extract the swing component of this quaternion: the rotation perpendicular to ({@code x},
     * {@code y}, {@code z}) in the swing-twist decomposition and store the result in {@code dest}.
     * <p>
     * The swing carries the rotation that tilts the axis itself; its own axis is perpendicular to
     * the given one.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length; this quaternion must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat getSwing(float x, float y, float z, @Mutated DoubleQuat dest);

    /**
     * Extract the twist component of this quaternion: the rotation about {@code axis} in the
     * swing-twist decomposition and store the result in {@code dest}.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity when the rotation is a
     * pure swing, including the 180-degree perpendicular case where the twist angle is undefined.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat getTwist(Float3R axis, @Mutated FloatQuat dest);

    /**
     * Extract the twist component of this quaternion: the rotation about {@code axis} in the
     * swing-twist decomposition and store the result in {@code dest}.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity when the rotation is a
     * pure swing, including the 180-degree perpendicular case where the twist angle is undefined.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat getTwist(Float3R axis, @Mutated DoubleQuat dest);

    /**
     * Extract the twist component of this quaternion: the rotation about ({@code x}, {@code y},
     * {@code z}) in the swing-twist decomposition and store the result in {@code dest}.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity when the rotation is a
     * pure swing, including the 180-degree perpendicular case where the twist angle is undefined.
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat getTwist(float x, float y, float z, @Mutated FloatQuat dest);

    /**
     * Extract the twist component of this quaternion: the rotation about ({@code x}, {@code y},
     * {@code z}) in the swing-twist decomposition and store the result in {@code dest}.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity when the rotation is a
     * pure swing, including the 180-degree perpendicular case where the twist angle is undefined.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat getTwist(float x, float y, float z, @Mutated DoubleQuat dest);

    /**
     * Linearly interpolate between this quaternion and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat lerp(FloatQuatR other, float t, @Mutated FloatQuat dest);

    /**
     * Linearly interpolate between this quaternion and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat lerp(FloatQuatR other, float t, @Mutated DoubleQuat dest);

    /**
     * Linearly interpolate between this quaternion and ({@code x}, {@code y}, {@code z}, {@code w})
     * using the interpolation factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code x}, {@code y}, {@code z}, {@code w}) (interpolation factor {@code 1}). Each linearly
     * interpolated component is {@code this + (other - this) * t}, as in JOML and glMatrix:
     * monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the rounding
     * of {@code other - this}, which shows when this component is much larger in magnitude than the
     * other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat lerp(float x, float y, float z, float w, float t, @Mutated FloatQuat dest);

    /**
     * Linearly interpolate between this quaternion and ({@code x}, {@code y}, {@code z}, {@code w})
     * using the interpolation factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code x}, {@code y}, {@code z}, {@code w}) (interpolation factor {@code 1}). Each linearly
     * interpolated component is {@code this + (other - this) * t}, as in JOML and glMatrix:
     * monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the rounding
     * of {@code other - this}, which shows when this component is much larger in magnitude than the
     * other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat lerp(float x, float y, float z, float w, float t, @Mutated DoubleQuat dest);

    /**
     * Interpolate between this quaternion and {@code target} using the interpolation factor
     * {@code alpha} and normalize the result and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat nlerp(FloatQuatR target, float alpha, @Mutated FloatQuat dest);

    /**
     * Interpolate between this quaternion and {@code target} using the interpolation factor
     * {@code alpha} and normalize the result and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat nlerp(FloatQuatR target, float alpha, @Mutated DoubleQuat dest);

    /**
     * Interpolate between this quaternion and ({@code x}, {@code y}, {@code z}, {@code w}) using
     * the interpolation factor {@code alpha} and normalize the result and store the result in
     * {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code x}, {@code y}, {@code z}, {@code w}) (interpolation factor {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat nlerp(float x, float y, float z, float w, float alpha, @Mutated FloatQuat dest);

    /**
     * Interpolate between this quaternion and ({@code x}, {@code y}, {@code z}, {@code w}) using
     * the interpolation factor {@code alpha} and normalize the result and store the result in
     * {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code x}, {@code y}, {@code z}, {@code w}) (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat nlerp(float x, float y, float z, float w, float alpha, @Mutated DoubleQuat dest);

    /**
     * Interpolate along the shortest path between this quaternion and {@code target} using the
     * interpolation factor {@code alpha} and normalize the result and store the result in
     * {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat nlerpShortest(FloatQuatR target, float alpha, @Mutated FloatQuat dest);

    /**
     * Interpolate along the shortest path between this quaternion and {@code target} using the
     * interpolation factor {@code alpha} and normalize the result and store the result in
     * {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat nlerpShortest(FloatQuatR target, float alpha, @Mutated DoubleQuat dest);

    /**
     * Interpolate along the shortest path between this quaternion and ({@code x}, {@code y},
     * {@code z}, {@code w}) using the interpolation factor {@code alpha} and normalize the result
     * and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code x}, {@code y}, {@code z}, {@code w}) (interpolation factor {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat nlerpShortest(float x, float y, float z, float w, float alpha, @Mutated FloatQuat dest);

    /**
     * Interpolate along the shortest path between this quaternion and ({@code x}, {@code y},
     * {@code z}, {@code w}) using the interpolation factor {@code alpha} and normalize the result
     * and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code x}, {@code y}, {@code z}, {@code w}) (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat nlerpShortest(float x, float y, float z, float w, float alpha, @Mutated DoubleQuat dest);

    /**
     * Spherically interpolate between this quaternion and {@code target} using the interpolation
     * factor {@code alpha} and store the result in {@code dest}.
     * <p>
     * This method interpolates along the arc as given: when the two quaternions' dot product is
     * negative, the longer path around the sphere is taken. Use {@link #slerpShortest} (or negate
     * one operand) to always interpolate along the shorter arc.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code target} must have unit length; this quaternion must have unit length.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat slerp(FloatQuatR target, float alpha, @Mutated FloatQuat dest);

    /**
     * Spherically interpolate between this quaternion and {@code target} using the interpolation
     * factor {@code alpha} and store the result in {@code dest}.
     * <p>
     * This method interpolates along the arc as given: when the two quaternions' dot product is
     * negative, the longer path around the sphere is taken. Use {@link #slerpShortest} (or negate
     * one operand) to always interpolate along the shorter arc.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code target} must have unit length; this quaternion must have unit length.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat slerp(FloatQuatR target, float alpha, @Mutated DoubleQuat dest);

    /**
     * Spherically interpolate between this quaternion and ({@code x}, {@code y}, {@code z},
     * {@code w}) using the interpolation factor {@code alpha} and store the result in {@code dest}.
     * <p>
     * This method interpolates along the arc as given: when the two quaternions' dot product is
     * negative, the longer path around the sphere is taken. Use {@link #slerpShortest} (or negate
     * one operand) to always interpolate along the shorter arc.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code x}, {@code y}, {@code z}, {@code w}) (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length; this quaternion must have unit
     * length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat slerp(float x, float y, float z, float w, float alpha, @Mutated FloatQuat dest);

    /**
     * Spherically interpolate between this quaternion and ({@code x}, {@code y}, {@code z},
     * {@code w}) using the interpolation factor {@code alpha} and store the result in {@code dest}.
     * <p>
     * This method interpolates along the arc as given: when the two quaternions' dot product is
     * negative, the longer path around the sphere is taken. Use {@link #slerpShortest} (or negate
     * one operand) to always interpolate along the shorter arc.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code x}, {@code y}, {@code z}, {@code w}) (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length; this quaternion must have unit
     * length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat slerp(float x, float y, float z, float w, float alpha, @Mutated DoubleQuat dest);

    /**
     * Spherically interpolate along the shortest path between this quaternion and {@code target}
     * using the interpolation factor {@code alpha} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code target} must have unit length; this quaternion must have unit length.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat slerpShortest(FloatQuatR target, float alpha, @Mutated FloatQuat dest);

    /**
     * Spherically interpolate along the shortest path between this quaternion and {@code target}
     * using the interpolation factor {@code alpha} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code target} must have unit length; this quaternion must have unit length.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat slerpShortest(FloatQuatR target, float alpha, @Mutated DoubleQuat dest);

    /**
     * Spherically interpolate along the shortest path between this quaternion and ({@code x},
     * {@code y}, {@code z}, {@code w}) using the interpolation factor {@code alpha} and store the
     * result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code x}, {@code y}, {@code z}, {@code w}) (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length; this quaternion must have unit
     * length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat slerpShortest(float x, float y, float z, float w, float alpha, @Mutated FloatQuat dest);

    /**
     * Spherically interpolate along the shortest path between this quaternion and ({@code x},
     * {@code y}, {@code z}, {@code w}) using the interpolation factor {@code alpha} and store the
     * result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code x}, {@code y}, {@code z}, {@code w}) (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length; this quaternion must have unit
     * length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat slerpShortest(float x, float y, float z, float w, float alpha, @Mutated DoubleQuat dest);

    /**
     * Perform spherical quadrangle interpolation (SQUAD) between this quaternion (the start
     * rotation) and the target rotation, shaped by the two inner control quaternions and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length; {@code control0} must have unit length;
     * {@code control1} must have unit length; {@code target} must have unit length.
     *
     * @param control0 the inner control quaternion associated with the start rotation
     * @param control1 the inner control quaternion associated with the end rotation
     * @param target the target rotation
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat squad(FloatQuatR control0, FloatQuatR control1, FloatQuatR target, float t, @Mutated FloatQuat dest);

    /**
     * Perform spherical quadrangle interpolation (SQUAD) between this quaternion (the start
     * rotation) and the target rotation, shaped by the two inner control quaternions and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length; {@code control0} must have unit length;
     * {@code control1} must have unit length; {@code target} must have unit length.
     *
     * @param control0 the inner control quaternion associated with the start rotation
     * @param control1 the inner control quaternion associated with the end rotation
     * @param target the target rotation
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat squad(FloatQuatR control0, FloatQuatR control1, FloatQuatR target, float t, @Mutated DoubleQuat dest);

    /**
     * Perform spherical quadrangle interpolation (SQUAD) between this quaternion (the start
     * rotation) and the target rotation, shaped by the two inner control quaternions and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length;
     * {@code (control0X, control0Y, control0Z, control0W)} must have unit length;
     * {@code (control1X, control1Y, control1Z, control1W)} must have unit length;
     * {@code (targetX, targetY, targetZ, targetW)} must have unit length.
     *
     * @param control0X the {@code x} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0Y the {@code y} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0Z the {@code z} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0W the {@code w} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control1X the {@code x} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1Y the {@code y} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1Z the {@code z} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1W the {@code w} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat squad(float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, @Mutated FloatQuat dest);

    /**
     * Perform spherical quadrangle interpolation (SQUAD) between this quaternion (the start
     * rotation) and the target rotation, shaped by the two inner control quaternions and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length;
     * {@code (control0X, control0Y, control0Z, control0W)} must have unit length;
     * {@code (control1X, control1Y, control1Z, control1W)} must have unit length;
     * {@code (targetX, targetY, targetZ, targetW)} must have unit length.
     *
     * @param control0X the {@code x} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0Y the {@code y} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0Z the {@code z} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0W the {@code w} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control1X the {@code x} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1Y the {@code y} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1Z the {@code z} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1W the {@code w} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat squad(float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, @Mutated DoubleQuat dest);

    /**
     * Multiply this quaternion by {@code other} and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code Q * R}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code Q * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat mul(FloatQuatR other, @Mutated FloatQuat dest);

    /**
     * Multiply this quaternion by {@code other} and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code Q * R}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code Q * R * v}, the transformation of the operand will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat mul(FloatQuatR other, @Mutated DoubleQuat dest);

    /**
     * Multiply this quaternion by ({@code x}, {@code y}, {@code z}, {@code w}) and store the result
     * in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code Q * R}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code Q * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat mul(float x, float y, float z, float w, @Mutated FloatQuat dest);

    /**
     * Multiply this quaternion by ({@code x}, {@code y}, {@code z}, {@code w}) and store the result
     * in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code Q * R}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code Q * R * v}, the transformation of the operand will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat mul(float x, float y, float z, float w, @Mutated DoubleQuat dest);

    /**
     * Pre-multiply the transformation {@code other} onto this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code T} the given transformation quaternion,
     * then the new quaternion will be {@code T * Q}. So when transforming a vector {@code v} with
     * the new quaternion by using {@code T * Q * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat preMul(FloatQuatR other, @Mutated FloatQuat dest);

    /**
     * Pre-multiply the transformation {@code other} onto this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code T} the given transformation quaternion,
     * then the new quaternion will be {@code T * Q}. So when transforming a vector {@code v} with
     * the new quaternion by using {@code T * Q * v}, the given transformation will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat preMul(FloatQuatR other, @Mutated DoubleQuat dest);

    /**
     * Pre-multiply the transformation ({@code x}, {@code y}, {@code z}, {@code w}) onto this
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code T} the given transformation quaternion,
     * then the new quaternion will be {@code T * Q}. So when transforming a vector {@code v} with
     * the new quaternion by using {@code T * Q * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat preMul(float x, float y, float z, float w, @Mutated FloatQuat dest);

    /**
     * Pre-multiply the transformation ({@code x}, {@code y}, {@code z}, {@code w}) onto this
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code T} the given transformation quaternion,
     * then the new quaternion will be {@code T * Q}. So when transforming a vector {@code v} with
     * the new quaternion by using {@code T * Q * v}, the given transformation will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat preMul(float x, float y, float z, float w, @Mutated DoubleQuat dest);

    /**
     * Add {@code other} scaled by {@code weight} to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat addScaled(FloatQuatR other, float weight, @Mutated FloatQuat dest);

    /**
     * Add {@code other} scaled by {@code weight} to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat addScaled(FloatQuatR other, float weight, @Mutated DoubleQuat dest);

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) scaled by {@code weight} to this quaternion
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param weight the factor to scale ({@code x}, {@code y}, {@code z}, {@code w}) by before
     *        adding
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat addScaled(float x, float y, float z, float w, float weight, @Mutated FloatQuat dest);

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) scaled by {@code weight} to this quaternion
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param weight the factor to scale ({@code x}, {@code y}, {@code z}, {@code w}) by before
     *        adding
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat addScaled(float x, float y, float z, float w, float weight, @Mutated DoubleQuat dest);

    /**
     * Compute the rotation angle in radians of this quaternion, within {@code [0, 2*PI]}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the rotation angle in radians of this quaternion, within {@code [0, 2*PI]}
     */
    float angle();

    /**
     * Compute the angle in radians between this quaternion and {@code other}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: this quaternion must have unit length; {@code other} must have unit length.
     *
     * @param other the quaternion to measure the angle to
     * @return the angle in radians between this quaternion and {@code other}
     */
    float angleTo(FloatQuatR other);

    /**
     * Compute the angle in radians between this quaternion and ({@code x}, {@code y}, {@code z},
     * {@code w}).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: this quaternion must have unit length; {@code (x, y, z, w)} must have unit
     * length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @return the angle in radians between this quaternion and ({@code x}, {@code y}, {@code z},
     *        {@code w})
     */
    float angleTo(float x, float y, float z, float w);

    /**
     * Get the normalized rotation axis of this quaternion (zero when the rotation angle is zero)
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 axis(@Mutated Float3 dest);

    /**
     * Get the normalized rotation axis of this quaternion (zero when the rotation angle is zero)
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
    Double3 axis(@Mutated Double3 dest);

    /**
     * Recompute the {@code w} component of this quaternion from {@code x}, {@code y} and {@code z},
     * assuming unit length (the positive square root is chosen) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat calculateW(@Mutated FloatQuat dest);

    /**
     * Recompute the {@code w} component of this quaternion from {@code x}, {@code y} and {@code z},
     * assuming unit length (the positive square root is chosen) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat calculateW(@Mutated DoubleQuat dest);

    /**
     * Conjugate this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat conjugate(@Mutated FloatQuat dest);

    /**
     * Conjugate this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat conjugate(@Mutated DoubleQuat dest);

    /**
     * Conjugate this quaternion by {@code q}, i.e. compute {@code q * this * conj(q)} where
     * {@code q} is the given quaternion (equal to {@code q * this * q^-1} when it has unit length)
     * and store the result in {@code dest}.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the quaternion to conjugate by
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat conjugateBy(FloatQuatR q, @Mutated FloatQuat dest);

    /**
     * Conjugate this quaternion by {@code q}, i.e. compute {@code q * this * conj(q)} where
     * {@code q} is the given quaternion (equal to {@code q * this * q^-1} when it has unit length)
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the quaternion to conjugate by
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat conjugateBy(FloatQuatR q, @Mutated DoubleQuat dest);

    /**
     * Conjugate this quaternion by ({@code x}, {@code y}, {@code z}, {@code w}), i.e. compute
     * {@code q * this * conj(q)} where {@code q} is the given quaternion (equal to
     * {@code q * this * q^-1} when it has unit length) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat conjugateBy(float x, float y, float z, float w, @Mutated FloatQuat dest);

    /**
     * Conjugate this quaternion by ({@code x}, {@code y}, {@code z}, {@code w}), i.e. compute
     * {@code q * this * conj(q)} where {@code q} is the given quaternion (equal to
     * {@code q * this * q^-1} when it has unit length) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat conjugateBy(float x, float y, float z, float w, @Mutated DoubleQuat dest);

    /**
     * Compute the difference between this quaternion and {@code other}, i.e. the rotation {@code D}
     * with {@code this * D = other}, that is {@code D = this^-1 * other} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param other the target quaternion, reached by composing this quaternion with the result
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat difference(FloatQuatR other, @Mutated FloatQuat dest);

    /**
     * Compute the difference between this quaternion and {@code other}, i.e. the rotation {@code D}
     * with {@code this * D = other}, that is {@code D = this^-1 * other} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param other the target quaternion, reached by composing this quaternion with the result
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat difference(FloatQuatR other, @Mutated DoubleQuat dest);

    /**
     * Compute the difference between this quaternion and ({@code x}, {@code y}, {@code z},
     * {@code w}), i.e. the rotation {@code D} with {@code this * D = (x, y, z, w)}, that is
     * {@code D = this^-1 * (x, y, z, w)} and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat difference(float x, float y, float z, float w, @Mutated FloatQuat dest);

    /**
     * Compute the difference between this quaternion and ({@code x}, {@code y}, {@code z},
     * {@code w}), i.e. the rotation {@code D} with {@code this * D = (x, y, z, w)}, that is
     * {@code D = this^-1 * (x, y, z, w)} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat difference(float x, float y, float z, float w, @Mutated DoubleQuat dest);

    /**
     * Compute the dot product of this quaternion and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this quaternion and {@code other}
     */
    float dot(FloatQuatR other);

    /**
     * Compute the dot product of this quaternion and ({@code x}, {@code y}, {@code z}, {@code w}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @return the dot product of this quaternion and ({@code x}, {@code y}, {@code z}, {@code w})
     */
    float dot(float x, float y, float z, float w);

    /**
     * Compute the exponential of this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat exp(@Mutated FloatQuat dest);

    /**
     * Compute the exponential of this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat exp(@Mutated DoubleQuat dest);

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the X, Y and Z axes,
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
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 getEulerAnglesXYZ(@Mutated Float3 dest);

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the X, Y and Z axes,
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
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesXYZ(@Mutated Double3 dest);

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the X, Z and Y axes,
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
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 getEulerAnglesXZY(@Mutated Float3 dest);

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the X, Z and Y axes,
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
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesXZY(@Mutated Double3 dest);

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Y, X and Z axes,
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
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 getEulerAnglesYXZ(@Mutated Float3 dest);

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Y, X and Z axes,
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
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesYXZ(@Mutated Double3 dest);

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Y, Z and X axes,
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
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 getEulerAnglesYZX(@Mutated Float3 dest);

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Y, Z and X axes,
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
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesYZX(@Mutated Double3 dest);

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Z, X and Y axes,
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
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 getEulerAnglesZXY(@Mutated Float3 dest);

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Z, X and Y axes,
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
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesZXY(@Mutated Double3 dest);

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Z, Y and X axes,
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
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 getEulerAnglesZYX(@Mutated Float3 dest);

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Z, Y and X axes,
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
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesZYX(@Mutated Double3 dest);

    /**
     * Integrate the given angular velocity over the given time step and apply the resulting
     * rotation to this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angularVel the angular velocity, in radians per second, applied in the reference frame
     * @param dt the time step
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat integrate(Float3R angularVel, float dt, @Mutated FloatQuat dest);

    /**
     * Integrate the given angular velocity over the given time step and apply the resulting
     * rotation to this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angularVel the angular velocity, in radians per second, applied in the reference frame
     * @param dt the time step
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat integrate(Float3R angularVel, float dt, @Mutated DoubleQuat dest);

    /**
     * Integrate the given angular velocity over the given time step and apply the resulting
     * rotation to this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dt the time step
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat integrate(float x, float y, float z, float dt, @Mutated FloatQuat dest);

    /**
     * Integrate the given angular velocity over the given time step and apply the resulting
     * rotation to this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dt the time step
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat integrate(float x, float y, float z, float dt, @Mutated DoubleQuat dest);

    /**
     * Obtain the direction of {@code -X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 invNegativeX(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code -X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNegativeX(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 invNegativeY(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code -Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNegativeY(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 invNegativeZ(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code -Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNegativeZ(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 invNormalizedNegativeX(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code -X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNormalizedNegativeX(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 invNormalizedNegativeY(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code -Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNormalizedNegativeY(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 invNormalizedNegativeZ(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code -Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNormalizedNegativeZ(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 invNormalizedPositiveX(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code +X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNormalizedPositiveX(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 invNormalizedPositiveY(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code +Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNormalizedPositiveY(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 invNormalizedPositiveZ(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code +Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNormalizedPositiveZ(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 invPositiveX(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code +X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invPositiveX(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 invPositiveY(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code +Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invPositiveY(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 invPositiveZ(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code +Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invPositiveZ(@Mutated Double3 dest);

    /**
     * Compute the length of this quaternion.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the length of this quaternion
     */
    float length();

    /**
     * Compute the squared length of this quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this quaternion
     */
    float lengthSquared();

    /**
     * Compute the natural logarithm of this quaternion and store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat log(@Mutated FloatQuat dest);

    /**
     * Compute the natural logarithm of this quaternion and store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat log(@Mutated DoubleQuat dest);

    /**
     * Obtain the direction of {@code -X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 negativeX(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code -X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 negativeX(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 negativeY(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code -Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 negativeY(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 negativeZ(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code -Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 negativeZ(@Mutated Double3 dest);

    /**
     * Normalize this quaternion to unit length and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat normalize(@Mutated FloatQuat dest);

    /**
     * Normalize this quaternion to unit length and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat normalize(@Mutated DoubleQuat dest);

    /**
     * Obtain the direction of {@code -X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 normalizedNegativeX(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code -X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 normalizedNegativeX(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 normalizedNegativeY(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code -Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 normalizedNegativeY(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 normalizedNegativeZ(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code -Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 normalizedNegativeZ(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 normalizedPositiveX(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code +X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 normalizedPositiveX(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 normalizedPositiveY(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code +Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 normalizedPositiveY(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 normalizedPositiveZ(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code +Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 normalizedPositiveZ(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 positiveX(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code +X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 positiveX(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 positiveY(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code +Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 positiveY(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 positiveZ(@Mutated Float3 dest);

    /**
     * Obtain the direction of {@code +Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 positiveZ(@Mutated Double3 dest);

    /**
     * Raise this quaternion to the power of {@code t}, i.e. compute {@code exp(t * log(this))} and
     * store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the exponent
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat pow(float t, @Mutated FloatQuat dest);

    /**
     * Raise this quaternion to the power of {@code t}, i.e. compute {@code exp(t * log(this))} and
     * store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the exponent
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat pow(float t, @Mutated DoubleQuat dest);

    /**
     * Pre-multiply {@code other} onto this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code R * Q}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code R * Q * v}, the transformation of the operand will be applied last.
     * <p>
     * Identical to {@link #preMul}; the lower-case spelling is kept for JOML 1 source
     * compatibility.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat premul(FloatQuatR other, @Mutated FloatQuat dest);

    /**
     * Pre-multiply {@code other} onto this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code R * Q}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code R * Q * v}, the transformation of the operand will be applied last.
     * <p>
     * Identical to {@link #preMul}; the lower-case spelling is kept for JOML 1 source
     * compatibility.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat premul(FloatQuatR other, @Mutated DoubleQuat dest);

    /**
     * Pre-multiply ({@code x}, {@code y}, {@code z}, {@code w}) onto this quaternion and store the
     * result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code R * Q}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code R * Q * v}, the transformation of the operand will be applied last.
     * <p>
     * Identical to {@link #preMul}; the lower-case spelling is kept for JOML 1 source
     * compatibility.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat premul(float x, float y, float z, float w, @Mutated FloatQuat dest);

    /**
     * Pre-multiply ({@code x}, {@code y}, {@code z}, {@code w}) onto this quaternion and store the
     * result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code R * Q}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code R * Q * v}, the transformation of the operand will be applied last.
     * <p>
     * Identical to {@link #preMul}; the lower-case spelling is kept for JOML 1 source
     * compatibility.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat premul(float x, float y, float z, float w, @Mutated DoubleQuat dest);

    /**
     * Rotate this quaternion towards {@code target}, by at most the given maximum angle and store
     * the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: this quaternion must have unit length; {@code target} must have unit length.
     *
     * @param target the target rotation
     * @param step the maximum rotation angle in radians
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat rotateTowards(FloatQuatR target, float step, @Mutated FloatQuat dest);

    /**
     * Rotate this quaternion towards {@code target}, by at most the given maximum angle and store
     * the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length; {@code target} must have unit length.
     *
     * @param target the target rotation
     * @param step the maximum rotation angle in radians
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat rotateTowards(FloatQuatR target, float step, @Mutated DoubleQuat dest);

    /**
     * Rotate this quaternion towards ({@code x}, {@code y}, {@code z}, {@code w}), by at most the
     * given maximum angle and store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: this quaternion must have unit length; {@code (x, y, z, w)} must have unit
     * length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param step the maximum rotation angle in radians
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat rotateTowards(float x, float y, float z, float w, float step, @Mutated FloatQuat dest);

    /**
     * Rotate this quaternion towards ({@code x}, {@code y}, {@code z}, {@code w}), by at most the
     * given maximum angle and store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length; {@code (x, y, z, w)} must have unit
     * length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param step the maximum rotation angle in radians
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat rotateTowards(float x, float y, float z, float w, float step, @Mutated DoubleQuat dest);

    /**
     * Apply a rotation transformation that makes {@code +z} point along {@code dir} to this
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat lookAlong(Float3R dir, Float3R up, @Mutated FloatQuat dest);

    /**
     * Apply a rotation transformation that makes {@code +z} point along {@code dir} to this
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * need a non-zero direction and up vector that are not parallel.)
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction the local {@code +z} axis is
     *        mapped to
     * @param up the direction of "up"
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat lookAlong(Float3R dir, Float3R up, @Mutated DoubleQuat dest);

    /**
     * Apply a rotation transformation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}) to this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatQuat dest);

    /**
     * Apply a rotation transformation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}) to this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * need a non-zero direction and up vector that are not parallel.)
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleQuat dest);

    /**
     * Pre-multiply a rotation of {@code angle} radians about the X axis onto this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat preRotateX(float angle, @Mutated FloatQuat dest);

    /**
     * Pre-multiply a rotation of {@code angle} radians about the X axis onto this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
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
    DoubleQuat preRotateX(float angle, @Mutated DoubleQuat dest);

    /**
     * Pre-multiply a rotation of {@code angle} radians about the Y axis onto this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat preRotateY(float angle, @Mutated FloatQuat dest);

    /**
     * Pre-multiply a rotation of {@code angle} radians about the Y axis onto this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
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
    DoubleQuat preRotateY(float angle, @Mutated DoubleQuat dest);

    /**
     * Pre-multiply a rotation of {@code angle} radians about the Z axis onto this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat preRotateZ(float angle, @Mutated FloatQuat dest);

    /**
     * Pre-multiply a rotation of {@code angle} radians about the Z axis onto this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
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
    DoubleQuat preRotateZ(float angle, @Mutated DoubleQuat dest);

    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat rotateAxis(float angle, Float3R axis, @Mutated FloatQuat dest);

    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleQuat rotateAxis(float angle, Float3R axis, @Mutated DoubleQuat dest);

    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code x}, {@code y}, {@code z}) to
     * this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length.
     *
     * @param angle the angle in radians
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat rotateAxis(float angle, float x, float y, float z, @Mutated FloatQuat dest);

    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code x}, {@code y}, {@code z}) to
     * this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length.
     *
     * @param angle the angle in radians
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat rotateAxis(float angle, float x, float y, float z, @Mutated DoubleQuat dest);

    /**
     * Apply the rotation that rotates {@code fromDir} onto {@code toDir} (for opposite vectors an
     * arbitrary perpendicular rotation axis is chosen) to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length.
     *
     * @param fromDir the direction to rotate from (must be a unit vector)
     * @param toDir the direction to rotate onto (must be a unit vector)
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat rotateTo(Float3R fromDir, Float3R toDir, @Mutated FloatQuat dest);

    /**
     * Apply the rotation that rotates {@code fromDir} onto {@code toDir} (for opposite vectors an
     * arbitrary perpendicular rotation axis is chosen) to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length.
     *
     * @param fromDir the direction to rotate from (must be a unit vector)
     * @param toDir the direction to rotate onto (must be a unit vector)
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat rotateTo(Float3R fromDir, Float3R toDir, @Mutated DoubleQuat dest);

    /**
     * Apply the rotation that rotates ({@code fromDirX}, {@code fromDirY}, {@code fromDirZ}) onto
     * ({@code toDirX}, {@code toDirY}, {@code toDirZ}) (for opposite vectors an arbitrary
     * perpendicular rotation axis is chosen) to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
     * <p>
     * Valid input: {@code (fromDirX, fromDirY, fromDirZ)} must have unit length;
     * {@code (toDirX, toDirY, toDirZ)} must have unit length.
     *
     * @param fromDirX the {@code x} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirY the {@code y} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirZ the {@code z} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param toDirX the {@code x} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirY the {@code y} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirZ the {@code z} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat rotateTo(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, @Mutated FloatQuat dest);

    /**
     * Apply the rotation that rotates ({@code fromDirX}, {@code fromDirY}, {@code fromDirZ}) onto
     * ({@code toDirX}, {@code toDirY}, {@code toDirZ}) (for opposite vectors an arbitrary
     * perpendicular rotation axis is chosen) to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (fromDirX, fromDirY, fromDirZ)} must have unit length;
     * {@code (toDirX, toDirY, toDirZ)} must have unit length.
     *
     * @param fromDirX the {@code x} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirY the {@code y} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirZ the {@code z} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param toDirX the {@code x} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirY the {@code y} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirZ the {@code z} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat rotateTo(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, @Mutated DoubleQuat dest);

    /**
     * Rotate this quaternion by {@code angle} radians about the local X axis and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat rotateX(float angle, @Mutated FloatQuat dest);

    /**
     * Rotate this quaternion by {@code angle} radians about the local X axis and store the result
     * in {@code dest}.
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
    DoubleQuat rotateX(float angle, @Mutated DoubleQuat dest);

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat rotateXYZ(float angleX, float angleY, float angleZ, @Mutated FloatQuat dest);

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleQuat rotateXYZ(float angleX, float angleY, float angleZ, @Mutated DoubleQuat dest);

    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat rotateXZY(float angleX, float angleZ, float angleY, @Mutated FloatQuat dest);

    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleQuat rotateXZY(float angleX, float angleZ, float angleY, @Mutated DoubleQuat dest);

    /**
     * Rotate this quaternion by {@code angle} radians about the local Y axis and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat rotateY(float angle, @Mutated FloatQuat dest);

    /**
     * Rotate this quaternion by {@code angle} radians about the local Y axis and store the result
     * in {@code dest}.
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
    DoubleQuat rotateY(float angle, @Mutated DoubleQuat dest);

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat rotateYXZ(float angleY, float angleX, float angleZ, @Mutated FloatQuat dest);

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleQuat rotateYXZ(float angleY, float angleX, float angleZ, @Mutated DoubleQuat dest);

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat rotateYZX(float angleY, float angleZ, float angleX, @Mutated FloatQuat dest);

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleQuat rotateYZX(float angleY, float angleZ, float angleX, @Mutated DoubleQuat dest);

    /**
     * Rotate this quaternion by {@code angle} radians about the local Z axis and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat rotateZ(float angle, @Mutated FloatQuat dest);

    /**
     * Rotate this quaternion by {@code angle} radians about the local Z axis and store the result
     * in {@code dest}.
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
    DoubleQuat rotateZ(float angle, @Mutated DoubleQuat dest);

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat rotateZXY(float angleZ, float angleX, float angleY, @Mutated FloatQuat dest);

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleQuat rotateZXY(float angleZ, float angleX, float angleY, @Mutated DoubleQuat dest);

    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat rotateZYX(float angleZ, float angleY, float angleX, @Mutated FloatQuat dest);

    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleQuat rotateZYX(float angleZ, float angleY, float angleX, @Mutated DoubleQuat dest);

    /**
     * Transform {@code v} by this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    Float3 transform(Float3R v, @Mutated Float3 dest);

    /**
     * Transform {@code v} by this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    Double3 transform(Float3R v, @Mutated Double3 dest);

    /**
     * Transform ({@code x}, {@code y}, {@code z}) by this quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Float3 transform(float x, float y, float z, @Mutated Float3 dest);

    /**
     * Transform ({@code x}, {@code y}, {@code z}) by this quaternion and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Double3 transform(float x, float y, float z, @Mutated Double3 dest);

    /**
     * Transform {@code v} by this quaternion and store the result back into {@code v}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param v the vector to transform (also receives the result)
     * @return {@code v}
     */
    default Float3 transform(@Mutated Float3 v) { return transform(v, v); }

    /**
     * Transform {@code v} by the inverse of this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    Float3 transformInverse(Float3R v, @Mutated Float3 dest);

    /**
     * Transform {@code v} by the inverse of this quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformInverse(Float3R v, @Mutated Double3 dest);

    /**
     * Transform ({@code x}, {@code y}, {@code z}) by the inverse of this quaternion and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Float3 transformInverse(float x, float y, float z, @Mutated Float3 dest);

    /**
     * Transform ({@code x}, {@code y}, {@code z}) by the inverse of this quaternion and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformInverse(float x, float y, float z, @Mutated Double3 dest);

    /**
     * Transform {@code v} by the inverse of this quaternion and store the result back into
     * {@code v}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param v the vector to transform (also receives the result)
     * @return {@code v}
     */
    default Float3 transformInverse(@Mutated Float3 v) { return transformInverse(v, v); }
    /** {@return the value of the {@code x} component} <p>Valid input: any value, NaN and the infinities included. */
    float x();
    /** {@return the value of the {@code y} component} <p>Valid input: any value, NaN and the infinities included. */
    float y();
    /** {@return the value of the {@code z} component} <p>Valid input: any value, NaN and the infinities included. */
    float z();
    /** {@return the value of the {@code w} component} <p>Valid input: any value, NaN and the infinities included. */
    float w();

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    default float[] store(@Mutated float[] dest) { return store(dest, 0); }

    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    float[] store(@Mutated float[] dest, int offset);

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
     * @param dest the destination buffer
     * @return dest
     */
    default FloatBuffer store(@Mutated FloatBuffer dest) { return storeAbsolute(dest.position(), dest); }

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
     * @param dest the destination buffer
     * @return dest
     */
    default FloatBuffer storeAbsolute(@Mutated FloatBuffer dest) { return storeAbsolute(dest.position(), dest); }

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
     * @param dest the destination buffer
     * @return dest
     */
    FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer dest);

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
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default FloatBuffer storeRelative(@Mutated FloatBuffer dest) {
        if (dest.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 4);
        return dest;
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
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer store(@Mutated ByteBuffer dest) { return storeAbsolute(dest.position(), dest); }

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
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeAbsolute(@Mutated ByteBuffer dest) { return storeAbsolute(dest.position(), dest); }

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
     * @param dest the destination byte buffer
     * @return dest
     */
    ByteBuffer storeAbsolute(int index, @Mutated ByteBuffer dest);

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
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 16);
        return dest;
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
    default MemorySegment store(@Mutated MemorySegment dest) { return store(0L, dest); }

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
    MemorySegment store(long offset, @Mutated MemorySegment dest);

    /**
     * Store the elements into the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    FloatQuat storeUnsafe(long address);

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    default double[] store(@Mutated double[] dest) { return store(dest, 0); }

    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    double[] store(@Mutated double[] dest, int offset);

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
     * @param dest the destination buffer
     * @return dest
     */
    default DoubleBuffer store(@Mutated DoubleBuffer dest) { return storeAbsolute(dest.position(), dest); }

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
     * @param dest the destination buffer
     * @return dest
     */
    default DoubleBuffer storeAbsolute(@Mutated DoubleBuffer dest) { return storeAbsolute(dest.position(), dest); }

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
     * @param dest the destination buffer
     * @return dest
     */
    DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer dest);

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
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default DoubleBuffer storeRelative(@Mutated DoubleBuffer dest) {
        if (dest.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 4);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
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
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeDouble(@Mutated ByteBuffer dest) { return storeDoubleAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
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
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeDoubleAbsolute(@Mutated ByteBuffer dest) { return storeDoubleAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
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
     * @param dest the destination byte buffer
     * @return dest
     */
    ByteBuffer storeDoubleAbsolute(int index, @Mutated ByteBuffer dest);

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
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
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeDoubleRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeDoubleAbsolute(pos, dest);
        dest.position(pos + 32);
        return dest;
    }

    /**
     * Store the elements into the given memory segment, converting each element to {@code double}.
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
    default MemorySegment storeDouble(@Mutated MemorySegment dest) { return storeDouble(0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code double},
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
    MemorySegment storeDouble(long offset, @Mutated MemorySegment dest);

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    FloatQuat storeDoubleUnsafe(long address);

    /** {@return whether all components of this quaternion are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    boolean isFinite();

    /** {@return whether any component of this quaternion is NaN} <p>Valid input: any value, NaN and the infinities included. */
    boolean isNaN();

    /**
     * Compare this quaternion component-wise against {@code other}, allowing a difference
     * of at most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
     * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and
     * a NaN component never compares equal to anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the quaternion to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    boolean equalsEpsilon(FloatQuatR other, float epsilon);

    /**
     * Compare this quaternion with the given object for element-wise equality.
     * <p>
     * Each component is compared bitwise, as by {@code Float.floatToIntBits}: {@code 0.0} and
     * {@code -0.0} are not equal, and NaN is equal to NaN. Use {@link #equalsEpsilon} for a
     * tolerant comparison.
     * <p>
     * Only instances of this library's implementation compare equal to each other; any other object
     * yields {@code false}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param obj the object to compare with
     * @return {@code true} if {@code obj} is a quaternion of this library with element-wise equal
     *        components, {@code false} otherwise
     */
    boolean equals(@org.jspecify.annotations.Nullable Object obj);

    /**
     * Compute a hash code consistent with {@link #equals}: it is derived from the components via
     * {@code Float.floatToIntBits} alone.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return the hash code of this quaternion
     */
    int hashCode();

    /** Backs {@code ZERO()} and {@code IDENTITY()}: defers the shared instances'
     *  allocation to first use, avoiding a class-initialization cycle with the
     *  implementation class. Not part of the public API. */
    final class Holder {
        private Holder() {}
        static final FloatQuatR IDENTITY = Joml.floatQuat();
        static final FloatQuatR ZERO = Joml.floatQuat(0, 0, 0, 0);
    }
}
