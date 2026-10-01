// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import java.nio.ByteBuffer;
import java.lang.foreign.MemorySegment;

/**
 * Read-only view of a dual quaternion of single-precision {@code float} components.
 * <p>
 * Declares the accessors, queries, store methods and dest-form operations that read but never
 * mutate the receiver; the mutable counterpart is {@link FloatDualQuat}. APIs that only read a dual
 * quaternion should accept {@code FloatDualQuatR}, so callers can pass mutable instances without
 * exposing them to modification.
 * <p>
 * Arguments of type {@code FloatDualQuatR} must be instances created by the library ({@link Joml}
 * factories / the library's own types); the implementations read cached state through the library's
 * own classes, so foreign implementations of the {@code *R} interfaces are not supported as
 * arguments.
 * <p>
 * A rigid motion is a unit dual quaternion: a unit real part, and a dual part orthogonal to it. The
 * operations that apply, invert or convert this dual quaternion assume it and do not divide the
 * real part's length out. A value that has drifted from unit length (after many multiplications,
 * say) gives wrong results rather than an error: {@code normalize} it first.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns). Only instances
 * of this library's implementation compare equal to each other; the {@code equals} of a dual
 * quaternion never returns {@code true} for an object of another type.
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 */
public interface FloatDualQuatR {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 32;

    /** {@return a shared all-zero dual quaternion} Never mutate it - it is returned as the read-only view for that reason. <p>Valid input: any value, NaN and the infinities included. */
    public static FloatDualQuatR ZERO() { return Holder.ZERO; }


    /**
     * Add {@code other} to this dual quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to add
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat add(FloatDualQuatR other, @Mutated FloatDualQuat dest);

    /**
     * Add {@code other} to this dual quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to add
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat add(FloatDualQuatR other, @Mutated DoubleDualQuat dest);

    /**
     * Add ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ},
     * {@code dW}) to this dual quaternion and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat add(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, @Mutated FloatDualQuat dest);

    /**
     * Add ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ},
     * {@code dW}) to this dual quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat add(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, @Mutated DoubleDualQuat dest);

    /**
     * Multiply each component of this dual quaternion by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat mul(float scalar, @Mutated FloatDualQuat dest);

    /**
     * Multiply each component of this dual quaternion by {@code scalar} and store the result in
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
    DoubleDualQuat mul(float scalar, @Mutated DoubleDualQuat dest);

    /**
     * Negate this dual quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat negate(@Mutated FloatDualQuat dest);

    /**
     * Negate this dual quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat negate(@Mutated DoubleDualQuat dest);

    /**
     * Subtract {@code other} from this dual quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to subtract
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat sub(FloatDualQuatR other, @Mutated FloatDualQuat dest);

    /**
     * Subtract {@code other} from this dual quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to subtract
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat sub(FloatDualQuatR other, @Mutated DoubleDualQuat dest);

    /**
     * Subtract ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ},
     * {@code dW}) from this dual quaternion and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat sub(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, @Mutated FloatDualQuat dest);

    /**
     * Subtract ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ},
     * {@code dW}) from this dual quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat sub(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, @Mutated DoubleDualQuat dest);

    /**
     * Convert this dual quaternion to {@code double} precision and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat toDouble(@Mutated DoubleDualQuat dest);

    /**
     * Convert this unit dual quaternion to a rigid transform (an exact conversion - both represent
     * rotation plus translation) and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatRigid toRigid(@Mutated FloatRigid dest);

    /**
     * Convert this unit dual quaternion to a rigid transform (an exact conversion - both represent
     * rotation plus translation) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleRigid toRigid(@Mutated DoubleRigid dest);

    /**
     * Convert this unit dual quaternion to a TRS transform (translation and rotation from the rigid
     * motion, scale = 1) and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatTransform toTransform(@Mutated FloatTransform dest);

    /**
     * Convert this unit dual quaternion to a TRS transform (translation and rotation from the rigid
     * motion, scale = 1) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleTransform toTransform(@Mutated DoubleTransform dest);

    /**
     * Determine whether the real (rotation) part of this dual quaternion has unit length.
     * <p>
     * Valid input: {@code epsilon} must not be negative.
     *
     * @param epsilon the maximum allowed deviation of the squared real-part length from {@code 1}
     * @return {@code true} if the real (rotation) part of this dual quaternion has unit length,
     *        {@code false} otherwise
     */
    boolean isUnit(float epsilon);

    /**
     * Blend this dual quaternion with {@code other} using dual-quaternion linear blending with the
     * weight {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this dual quaternion (weight {@code 0}) and ends at {@code other}
     * (weight {@code 1}).
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code other} must be a
     * unit dual quaternion; {@code t} must lie in {@code [0, 1]}.
     *
     * @param other the dual quaternion to blend towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat dlb(FloatDualQuatR other, float t, @Mutated FloatDualQuat dest);

    /**
     * Blend this dual quaternion with {@code other} using dual-quaternion linear blending with the
     * weight {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this dual quaternion (weight {@code 0}) and ends at {@code other}
     * (weight {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion; {@code other} must be a
     * unit dual quaternion; {@code t} must lie in {@code [0, 1]}.
     *
     * @param other the dual quaternion to blend towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat dlb(FloatDualQuatR other, float t, @Mutated DoubleDualQuat dest);

    /**
     * Blend this dual quaternion with ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX},
     * {@code dY}, {@code dZ}, {@code dW}) using dual-quaternion linear blending with the weight
     * {@code t} and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat dlb(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, float t, @Mutated FloatDualQuat dest);

    /**
     * Blend this dual quaternion with ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX},
     * {@code dY}, {@code dZ}, {@code dW}) using dual-quaternion linear blending with the weight
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this dual quaternion (weight {@code 0}) and ends at ({@code rX},
     * {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ}, {@code dW}) (weight
     * {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat dlb(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, float t, @Mutated DoubleDualQuat dest);

    /**
     * Linearly interpolate between this dual quaternion and {@code other} using the interpolation
     * factor {@code t} and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat lerp(FloatDualQuatR other, float t, @Mutated FloatDualQuat dest);

    /**
     * Linearly interpolate between this dual quaternion and {@code other} using the interpolation
     * factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
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
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat lerp(FloatDualQuatR other, float t, @Mutated DoubleDualQuat dest);

    /**
     * Linearly interpolate between this dual quaternion and ({@code rX}, {@code rY}, {@code rZ},
     * {@code rW}, {@code dX}, {@code dY}, {@code dZ}, {@code dW}) using the interpolation factor
     * {@code t} and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat lerp(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, float t, @Mutated FloatDualQuat dest);

    /**
     * Linearly interpolate between this dual quaternion and ({@code rX}, {@code rY}, {@code rZ},
     * {@code rW}, {@code dX}, {@code dY}, {@code dZ}, {@code dW}) using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ},
     * {@code dW}) (interpolation factor {@code 1}). Each linearly interpolated component is
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
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat lerp(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, float t, @Mutated DoubleDualQuat dest);

    /**
     * Screw-linearly interpolate between this dual quaternion and {@code other} using the
     * interpolation factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code other} must be a unit dual quaternion; this dual quaternion must be a
     * unit dual quaternion.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat sclerp(FloatDualQuatR other, float t, @Mutated FloatDualQuat dest);

    /**
     * Screw-linearly interpolate between this dual quaternion and {@code other} using the
     * interpolation factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code other} must be a unit dual quaternion; this dual quaternion must be a
     * unit dual quaternion.
     *
     * @param other the dual quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat sclerp(FloatDualQuatR other, float t, @Mutated DoubleDualQuat dest);

    /**
     * Screw-linearly interpolate between this dual quaternion and ({@code rX}, {@code rY},
     * {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ}, {@code dW}) using the
     * interpolation factor {@code t} and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat sclerp(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, float t, @Mutated FloatDualQuat dest);

    /**
     * Screw-linearly interpolate between this dual quaternion and ({@code rX}, {@code rY},
     * {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ}, {@code dW}) using the
     * interpolation factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this dual quaternion (interpolation factor {@code 0}) and ends at
     * ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ},
     * {@code dW}) (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat sclerp(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, float t, @Mutated DoubleDualQuat dest);

    /**
     * Multiply this dual quaternion by {@code other} and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code Q * R * v}, the transformation of the operand will be applied
     * first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat mul(FloatDualQuatR other, @Mutated FloatDualQuat dest);

    /**
     * Multiply this dual quaternion by {@code other} and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code Q * R * v}, the transformation of the operand will be applied
     * first.
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
    DoubleDualQuat mul(FloatDualQuatR other, @Mutated DoubleDualQuat dest);

    /**
     * Multiply this dual quaternion by ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX},
     * {@code dY}, {@code dZ}, {@code dW}) and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat mul(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, @Mutated FloatDualQuat dest);

    /**
     * Multiply this dual quaternion by ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX},
     * {@code dY}, {@code dZ}, {@code dW}) and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code Q * R * v}, the transformation of the operand will be applied
     * first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat mul(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, @Mutated DoubleDualQuat dest);

    /**
     * Pre-multiply {@code other} onto this dual quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code R * Q * v}, the transformation of the operand will be applied
     * last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat preMul(FloatDualQuatR other, @Mutated FloatDualQuat dest);

    /**
     * Pre-multiply {@code other} onto this dual quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code R * Q * v}, the transformation of the operand will be applied
     * last.
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
    DoubleDualQuat preMul(FloatDualQuatR other, @Mutated DoubleDualQuat dest);

    /**
     * Pre-multiply ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY},
     * {@code dZ}, {@code dW}) onto this dual quaternion and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat preMul(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, @Mutated FloatDualQuat dest);

    /**
     * Pre-multiply ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY},
     * {@code dZ}, {@code dW}) onto this dual quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the operand, then the new dual
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new dual
     * quaternion by using {@code R * Q * v}, the transformation of the operand will be applied
     * last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat preMul(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, @Mutated DoubleDualQuat dest);

    /**
     * Add {@code other} scaled by {@code weight} to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat addScaled(FloatDualQuatR other, float weight, @Mutated FloatDualQuat dest);

    /**
     * Add {@code other} scaled by {@code weight} to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the dual quaternion to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat addScaled(FloatDualQuatR other, float weight, @Mutated DoubleDualQuat dest);

    /**
     * Add ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ},
     * {@code dW}) scaled by {@code weight} to this dual quaternion and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat addScaled(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, float weight, @Mutated FloatDualQuat dest);

    /**
     * Add ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ},
     * {@code dW}) scaled by {@code weight} to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat addScaled(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, float weight, @Mutated DoubleDualQuat dest);

    /**
     * Compute the quaternion conjugate of this dual quaternion, conjugating both the real and the
     * dual part (for a unit dual quaternion this is its inverse) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat conjugate(@Mutated FloatDualQuat dest);

    /**
     * Compute the quaternion conjugate of this dual quaternion, conjugating both the real and the
     * dual part (for a unit dual quaternion this is its inverse) and store the result in
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
    DoubleDualQuat conjugate(@Mutated DoubleDualQuat dest);

    /**
     * Compute the difference between this dual quaternion and {@code other}, i.e. the rigid
     * transformation {@code D} with {@code this * D = other}, that is {@code D = this^-1 * other}
     * and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param other the target dual quaternion, reached by composing this dual quaternion with the
     *        result
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat difference(FloatDualQuatR other, @Mutated FloatDualQuat dest);

    /**
     * Compute the difference between this dual quaternion and {@code other}, i.e. the rigid
     * transformation {@code D} with {@code this * D = other}, that is {@code D = this^-1 * other}
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param other the target dual quaternion, reached by composing this dual quaternion with the
     *        result
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat difference(FloatDualQuatR other, @Mutated DoubleDualQuat dest);

    /**
     * Compute the difference between this dual quaternion and ({@code rX}, {@code rY}, {@code rZ},
     * {@code rW}, {@code dX}, {@code dY}, {@code dZ}, {@code dW}), i.e. the rigid transformation
     * {@code D} with {@code this * D = (rX, rY, rZ, rW, dX, dY, dZ, dW)}, that is
     * {@code D = this^-1 * (rX, rY, rZ, rW, dX, dY, dZ, dW)} and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat difference(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, @Mutated FloatDualQuat dest);

    /**
     * Compute the difference between this dual quaternion and ({@code rX}, {@code rY}, {@code rZ},
     * {@code rW}, {@code dX}, {@code dY}, {@code dZ}, {@code dW}), i.e. the rigid transformation
     * {@code D} with {@code this * D = (rX, rY, rZ, rW, dX, dY, dZ, dW)}, that is
     * {@code D = this^-1 * (rX, rY, rZ, rW, dX, dY, dZ, dW)} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat difference(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW, @Mutated DoubleDualQuat dest);

    /**
     * Compute the dot product of this dual quaternion and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this dual quaternion and {@code other}
     */
    float dot(FloatDualQuatR other);

    /**
     * Compute the dot product of this dual quaternion and ({@code rX}, {@code rY}, {@code rZ},
     * {@code rW}, {@code dX}, {@code dY}, {@code dZ}, {@code dW}).
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
     * @return the dot product of this dual quaternion and ({@code rX}, {@code rY}, {@code rZ},
     *        {@code rW}, {@code dX}, {@code dY}, {@code dZ}, {@code dW})
     */
    float dot(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW);

    /**
     * Compute the dual-number conjugate of this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat dualConjugate(@Mutated FloatDualQuat dest);

    /**
     * Compute the dual-number conjugate of this dual quaternion and store the result in
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
    DoubleDualQuat dualConjugate(@Mutated DoubleDualQuat dest);

    /**
     * Compute the exponential of this dual quaternion and store the result in {@code dest}.
     * <p>
     * This dual quaternion is read as a screw-motion generator, a pure dual quaternion as
     * {@code log} returns it: its scalar parts {@code rW} and {@code dW} are taken as zero and
     * ignored. The result is a unit dual quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat exp(@Mutated FloatDualQuat dest);

    /**
     * Compute the exponential of this dual quaternion and store the result in {@code dest}.
     * <p>
     * This dual quaternion is read as a screw-motion generator, a pure dual quaternion as
     * {@code log} returns it: its scalar parts {@code rW} and {@code dW} are taken as zero and
     * ignored. The result is a unit dual quaternion.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat exp(@Mutated DoubleDualQuat dest);

    /**
     * Get the dual part of this dual quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat getDual(@Mutated FloatQuat dest);

    /**
     * Get the dual part of this dual quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat getDual(@Mutated DoubleQuat dest);

    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the X, Y and Z
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 getEulerAnglesXYZ(@Mutated Float3 dest);

    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the X, Y and Z
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesXYZ(@Mutated Double3 dest);

    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the X, Z and Y
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 getEulerAnglesXZY(@Mutated Float3 dest);

    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the X, Z and Y
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesXZY(@Mutated Double3 dest);

    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the Y, X and Z
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 getEulerAnglesYXZ(@Mutated Float3 dest);

    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the Y, X and Z
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesYXZ(@Mutated Double3 dest);

    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the Y, Z and X
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 getEulerAnglesYZX(@Mutated Float3 dest);

    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the Y, Z and X
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesYZX(@Mutated Double3 dest);

    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the Z, X and Y
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 getEulerAnglesZXY(@Mutated Float3 dest);

    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the Z, X and Y
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesZXY(@Mutated Double3 dest);

    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the Z, Y and X
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 getEulerAnglesZYX(@Mutated Float3 dest);

    /**
     * Get the Euler angles in radians of this dual quaternion, to be applied about the Z, Y and X
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
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesZYX(@Mutated Double3 dest);

    /**
     * Get the rotation of this dual quaternion, i.e. its raw real part (a unit quaternion only when
     * this dual quaternion has unit length) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatQuat getRotation(@Mutated FloatQuat dest);

    /**
     * Get the rotation of this dual quaternion, i.e. its raw real part (a unit quaternion only when
     * this dual quaternion has unit length) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat getRotation(@Mutated DoubleQuat dest);

    /**
     * Get the translation of this dual quaternion, i.e. {@code 2 * dual * conj(real)} (the actual
     * translation only when this dual quaternion has unit length) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 getTranslation(@Mutated Float3 dest);

    /**
     * Get the translation of this dual quaternion, i.e. {@code 2 * dual * conj(real)} (the actual
     * translation only when this dual quaternion has unit length) and store the result in
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
    Double3 getTranslation(@Mutated Double3 dest);

    /**
     * Compute the inverse of this dual quaternion (its conjugate, for a unit dual quaternion) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat inverseUnit(@Mutated FloatDualQuat dest);

    /**
     * Compute the inverse of this dual quaternion (its conjugate, for a unit dual quaternion) and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat inverseUnit(@Mutated DoubleDualQuat dest);

    /**
     * Invert this dual quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the real part of this dual quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat invert(@Mutated FloatDualQuat dest);

    /**
     * Invert this dual quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the real part of this dual quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat invert(@Mutated DoubleDualQuat dest);

    /**
     * Compute the length of this dual quaternion's real (rotation) part.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the length of this dual quaternion's real (rotation) part
     */
    float length();

    /**
     * Compute the squared length of this dual quaternion's real (rotation) part.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this dual quaternion's real (rotation) part
     */
    float lengthSquared();

    /**
     * Compute the natural logarithm of this dual quaternion and store the result in {@code dest}.
     * <p>
     * This dual quaternion must be a unit dual quaternion; the result is pure (both scalar parts
     * zero), the input {@code exp} expects.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat log(@Mutated FloatDualQuat dest);

    /**
     * Compute the natural logarithm of this dual quaternion and store the result in {@code dest}.
     * <p>
     * This dual quaternion must be a unit dual quaternion; the result is pure (both scalar parts
     * zero), the input {@code exp} expects.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat log(@Mutated DoubleDualQuat dest);

    /**
     * Normalize this dual quaternion so that its real (rotation) part has unit length (a zero real
     * part yields the zero dual quaternion) and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat normalize(@Mutated FloatDualQuat dest);

    /**
     * Normalize this dual quaternion so that its real (rotation) part has unit length (a zero real
     * part yields the zero dual quaternion) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat normalize(@Mutated DoubleDualQuat dest);

    /**
     * Raise this dual quaternion to the power of {@code t} (screw-motion power: {@code t = 0}
     * yields the identity, {@code t = 1} yields {@code this}) and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param t the exponent
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat pow(float t, @Mutated FloatDualQuat dest);

    /**
     * Raise this dual quaternion to the power of {@code t} (screw-motion power: {@code t = 0}
     * yields the identity, {@code t = 1} yields {@code this}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param t the exponent
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat pow(float t, @Mutated DoubleDualQuat dest);

    /**
     * Set the dual half of this dual quaternion to {@code dual}, keeping the real (rotation) half
     * as it is and store the result in {@code dest}.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} follows the new dual half; use
     * {@code setTranslation} to set the translation itself.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dual the new dual half
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat setDual(FloatQuatR dual, @Mutated FloatDualQuat dest);

    /**
     * Set the dual half of this dual quaternion to {@code dual}, keeping the real (rotation) half
     * as it is and store the result in {@code dest}.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} follows the new dual half; use
     * {@code setTranslation} to set the translation itself.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dual the new dual half
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat setDual(FloatQuatR dual, @Mutated DoubleDualQuat dest);

    /**
     * Set the dual half of this dual quaternion to ({@code x}, {@code y}, {@code z}, {@code w}),
     * keeping the real (rotation) half as it is and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat setDual(float x, float y, float z, float w, @Mutated FloatDualQuat dest);

    /**
     * Set the dual half of this dual quaternion to ({@code x}, {@code y}, {@code z}, {@code w}),
     * keeping the real (rotation) half as it is and store the result in {@code dest}.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} follows the new dual half; use
     * {@code setTranslation} to set the translation itself.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the new dual half {@code (x, y, z, w)}
     * @param y the {@code y} component of the new dual half {@code (x, y, z, w)}
     * @param z the {@code z} component of the new dual half {@code (x, y, z, w)}
     * @param w the {@code w} component of the new dual half {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat setDual(float x, float y, float z, float w, @Mutated DoubleDualQuat dest);

    /**
     * Set the real (rotation) half of this dual quaternion to {@code real}, keeping the dual half
     * as it is and store the result in {@code dest}.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} changes with the real half; use
     * {@code setRotation} to replace the rotation and keep the translation.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param real the new real (rotation) half
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat setReal(FloatQuatR real, @Mutated FloatDualQuat dest);

    /**
     * Set the real (rotation) half of this dual quaternion to {@code real}, keeping the dual half
     * as it is and store the result in {@code dest}.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} changes with the real half; use
     * {@code setRotation} to replace the rotation and keep the translation.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param real the new real (rotation) half
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat setReal(FloatQuatR real, @Mutated DoubleDualQuat dest);

    /**
     * Set the real (rotation) half of this dual quaternion to ({@code x}, {@code y}, {@code z},
     * {@code w}), keeping the dual half as it is and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat setReal(float x, float y, float z, float w, @Mutated FloatDualQuat dest);

    /**
     * Set the real (rotation) half of this dual quaternion to ({@code x}, {@code y}, {@code z},
     * {@code w}), keeping the dual half as it is and store the result in {@code dest}.
     * <p>
     * The encoded translation {@code 2 * dual * conj(real)} changes with the real half; use
     * {@code setRotation} to replace the rotation and keep the translation.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the new real (rotation) half {@code (x, y, z, w)}
     * @param y the {@code y} component of the new real (rotation) half {@code (x, y, z, w)}
     * @param z the {@code z} component of the new real (rotation) half {@code (x, y, z, w)}
     * @param w the {@code w} component of the new real (rotation) half {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat setReal(float x, float y, float z, float w, @Mutated DoubleDualQuat dest);

    /**
     * Set the rotation of this dual quaternion to {@code rotation} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the new rotation
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat setRotation(FloatQuatR rotation, @Mutated FloatDualQuat dest);

    /**
     * Set the rotation of this dual quaternion to {@code rotation} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the new rotation
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat setRotation(FloatQuatR rotation, @Mutated DoubleDualQuat dest);

    /**
     * Set the rotation of this dual quaternion to ({@code x}, {@code y}, {@code z}, {@code w}) and
     * store the result in {@code dest}.
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
    FloatDualQuat setRotation(float x, float y, float z, float w, @Mutated FloatDualQuat dest);

    /**
     * Set the rotation of this dual quaternion to ({@code x}, {@code y}, {@code z}, {@code w}) and
     * store the result in {@code dest}.
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
    DoubleDualQuat setRotation(float x, float y, float z, float w, @Mutated DoubleDualQuat dest);

    /**
     * Set the translation of this dual quaternion to {@code translation} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the real part of this dual quaternion must have unit length.
     *
     * @param translation the new translation
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat setTranslation(Float3R translation, @Mutated FloatDualQuat dest);

    /**
     * Set the translation of this dual quaternion to {@code translation} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the real part of this dual quaternion must have unit length.
     *
     * @param translation the new translation
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat setTranslation(Float3R translation, @Mutated DoubleDualQuat dest);

    /**
     * Set the translation of this dual quaternion to ({@code x}, {@code y}, {@code z}) and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the real part of this dual quaternion must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat setTranslation(float x, float y, float z, @Mutated FloatDualQuat dest);

    /**
     * Set the translation of this dual quaternion to ({@code x}, {@code y}, {@code z}) and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the real part of this dual quaternion must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat setTranslation(float x, float y, float z, @Mutated DoubleDualQuat dest);

    /**
     * Compute the matrix representation of this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4x4 toMatrix(@Mutated Float4x4 dest);

    /**
     * Compute the matrix representation of this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 toMatrix(@Mutated Double4x4 dest);

    /**
     * Compute the 3x3 matrix representation of the rotation part of this dual quaternion (the
     * encoded translation is dropped) and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3x3 toMatrix3x3(@Mutated Float3x3 dest);

    /**
     * Compute the 3x3 matrix representation of the rotation part of this dual quaternion (the
     * encoded translation is dropped) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3x3 toMatrix3x3(@Mutated Double3x3 dest);

    /**
     * Compute the 3x4 matrix representation of this dual quaternion (the omitted last row is
     * implicitly {@code 0, 0, 0, 1}) and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3x4 toMatrix3x4(@Mutated Float3x4 dest);

    /**
     * Compute the 3x4 matrix representation of this dual quaternion (the omitted last row is
     * implicitly {@code 0, 0, 0, 1}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3x4 toMatrix3x4(@Mutated Double3x4 dest);

    /**
     * Apply a rotation transformation that makes {@code +z} point along {@code dir} to this dual
     * quaternion and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat lookAlong(Float3R dir, Float3R up, @Mutated FloatDualQuat dest);

    /**
     * Apply a rotation transformation that makes {@code +z} point along {@code dir} to this dual
     * quaternion and store the result in {@code dest}.
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
    DoubleDualQuat lookAlong(Float3R dir, Float3R up, @Mutated DoubleDualQuat dest);

    /**
     * Apply a rotation transformation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}) to this dual quaternion and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatDualQuat dest);

    /**
     * Apply a rotation transformation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}) to this dual quaternion and store the result in {@code dest}.
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
    DoubleDualQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleDualQuat dest);

    /**
     * Pre-multiply the rotation represented by the quaternion {@code rotation} onto this dual
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code R * Q}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat preRotate(FloatQuatR rotation, @Mutated FloatDualQuat dest);

    /**
     * Pre-multiply the rotation represented by the quaternion {@code rotation} onto this dual
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code R * Q}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat preRotate(FloatQuatR rotation, @Mutated DoubleDualQuat dest);

    /**
     * Pre-multiply the rotation represented by the quaternion ({@code x}, {@code y}, {@code z},
     * {@code w}) onto this dual quaternion and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat preRotate(float x, float y, float z, float w, @Mutated FloatDualQuat dest);

    /**
     * Pre-multiply the rotation represented by the quaternion ({@code x}, {@code y}, {@code z},
     * {@code w}) onto this dual quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code R * Q}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code R * Q * v}, the rotation will be applied last.
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
    DoubleDualQuat preRotate(float x, float y, float z, float w, @Mutated DoubleDualQuat dest);

    /**
     * Apply the rotation represented by the quaternion {@code rotation} to this dual quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param rotation the rotation to apply
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat rotate(FloatQuatR rotation, @Mutated FloatDualQuat dest);

    /**
     * Apply the rotation represented by the quaternion {@code rotation} to this dual quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleDualQuat rotate(FloatQuatR rotation, @Mutated DoubleDualQuat dest);

    /**
     * Apply the rotation represented by the quaternion ({@code x}, {@code y}, {@code z}, {@code w})
     * to this dual quaternion and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat rotate(float x, float y, float z, float w, @Mutated FloatDualQuat dest);

    /**
     * Apply the rotation represented by the quaternion ({@code x}, {@code y}, {@code z}, {@code w})
     * to this dual quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleDualQuat rotate(float x, float y, float z, float w, @Mutated DoubleDualQuat dest);

    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this dual quaternion
     * and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat rotateAxis(float angle, Float3R axis, @Mutated FloatDualQuat dest);

    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this dual quaternion
     * and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleDualQuat rotateAxis(float angle, Float3R axis, @Mutated DoubleDualQuat dest);

    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code x}, {@code y}, {@code z}) to
     * this dual quaternion and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat rotateAxis(float angle, float x, float y, float z, @Mutated FloatDualQuat dest);

    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code x}, {@code y}, {@code z}) to
     * this dual quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleDualQuat rotateAxis(float angle, float x, float y, float z, @Mutated DoubleDualQuat dest);

    /**
     * Apply a rotation of {@code angle} radians about the X axis to this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat rotateX(float angle, @Mutated FloatDualQuat dest);

    /**
     * Apply a rotation of {@code angle} radians about the X axis to this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleDualQuat rotateX(float angle, @Mutated DoubleDualQuat dest);

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this dual quaternion and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat rotateXYZ(float angleX, float angleY, float angleZ, @Mutated FloatDualQuat dest);

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleDualQuat rotateXYZ(float angleX, float angleY, float angleZ, @Mutated DoubleDualQuat dest);

    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this dual quaternion and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat rotateXZY(float angleX, float angleZ, float angleY, @Mutated FloatDualQuat dest);

    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleDualQuat rotateXZY(float angleX, float angleZ, float angleY, @Mutated DoubleDualQuat dest);

    /**
     * Apply a rotation of {@code angle} radians about the Y axis to this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat rotateY(float angle, @Mutated FloatDualQuat dest);

    /**
     * Apply a rotation of {@code angle} radians about the Y axis to this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleDualQuat rotateY(float angle, @Mutated DoubleDualQuat dest);

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this dual quaternion and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat rotateYXZ(float angleY, float angleX, float angleZ, @Mutated FloatDualQuat dest);

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleDualQuat rotateYXZ(float angleY, float angleX, float angleZ, @Mutated DoubleDualQuat dest);

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this dual quaternion and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat rotateYZX(float angleY, float angleZ, float angleX, @Mutated FloatDualQuat dest);

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleDualQuat rotateYZX(float angleY, float angleZ, float angleX, @Mutated DoubleDualQuat dest);

    /**
     * Apply a rotation of {@code angle} radians about the Z axis to this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat rotateZ(float angle, @Mutated FloatDualQuat dest);

    /**
     * Apply a rotation of {@code angle} radians about the Z axis to this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleDualQuat rotateZ(float angle, @Mutated DoubleDualQuat dest);

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this dual quaternion and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat rotateZXY(float angleZ, float angleX, float angleY, @Mutated FloatDualQuat dest);

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleDualQuat rotateZXY(float angleZ, float angleX, float angleY, @Mutated DoubleDualQuat dest);

    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this dual quaternion and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat rotateZYX(float angleZ, float angleY, float angleX, @Mutated FloatDualQuat dest);

    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code R} the rotation dual quaternion, then
     * the new dual quaternion will be {@code Q * R}. So when transforming a vector {@code v} with
     * the new dual quaternion by using {@code Q * R * v}, the rotation will be applied first.
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
    DoubleDualQuat rotateZYX(float angleZ, float angleY, float angleX, @Mutated DoubleDualQuat dest);

    /**
     * Apply a translation by {@code translation} to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code T} the translation dual quaternion,
     * then the new dual quaternion will be {@code Q * T}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * T * v}, the translation will be applied
     * first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param translation the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat translate(Float3R translation, @Mutated FloatDualQuat dest);

    /**
     * Apply a translation by {@code translation} to this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code T} the translation dual quaternion,
     * then the new dual quaternion will be {@code Q * T}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * T * v}, the translation will be applied
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
    DoubleDualQuat translate(Float3R translation, @Mutated DoubleDualQuat dest);

    /**
     * Apply a translation by ({@code x}, {@code y}, {@code z}) to this dual quaternion and store
     * the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    FloatDualQuat translate(float x, float y, float z, @Mutated FloatDualQuat dest);

    /**
     * Apply a translation by ({@code x}, {@code y}, {@code z}) to this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} dual quaternion and {@code T} the translation dual quaternion,
     * then the new dual quaternion will be {@code Q * T}. So when transforming a vector {@code v}
     * with the new dual quaternion by using {@code Q * T * v}, the translation will be applied
     * first.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat translate(float x, float y, float z, @Mutated DoubleDualQuat dest);

    /**
     * Transform {@code p} by this dual quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    Float3 transform(Float3R p, @Mutated Float3 dest);

    /**
     * Transform {@code p} by this dual quaternion and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    Double3 transform(Float3R p, @Mutated Double3 dest);

    /**
     * Transform ({@code x}, {@code y}, {@code z}) by this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Float3 transform(float x, float y, float z, @Mutated Float3 dest);

    /**
     * Transform ({@code x}, {@code y}, {@code z}) by this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Double3 transform(float x, float y, float z, @Mutated Double3 dest);

    /**
     * Transform {@code p} by this dual quaternion and store the result back into {@code p}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform (also receives the result)
     * @return {@code p}
     */
    default Float3 transform(@Mutated Float3 p) { return transform(p, p); }

    /**
     * Transform the given direction by this dual quaternion, ignoring any translation and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    Float3 transformDirection(Float3R v, @Mutated Float3 dest);

    /**
     * Transform the given direction by this dual quaternion, ignoring any translation and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformDirection(Float3R v, @Mutated Double3 dest);

    /**
     * Transform the given direction by this dual quaternion, ignoring any translation and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Float3 transformDirection(float x, float y, float z, @Mutated Float3 dest);

    /**
     * Transform the given direction by this dual quaternion, ignoring any translation and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformDirection(float x, float y, float z, @Mutated Double3 dest);

    /**
     * Transform the given direction by this dual quaternion, ignoring any translation and store the
     * result back into {@code v}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the direction to transform (also receives the result)
     * @return {@code v}
     */
    default Float3 transformDirection(@Mutated Float3 v) { return transformDirection(v, v); }

    /**
     * Transform the given direction by the inverse of this dual quaternion's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    Float3 transformDirectionInverse(Float3R v, @Mutated Float3 dest);

    /**
     * Transform the given direction by the inverse of this dual quaternion's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformDirectionInverse(Float3R v, @Mutated Double3 dest);

    /**
     * Transform the given direction by the inverse of this dual quaternion's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Float3 transformDirectionInverse(float x, float y, float z, @Mutated Float3 dest);

    /**
     * Transform the given direction by the inverse of this dual quaternion's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformDirectionInverse(float x, float y, float z, @Mutated Double3 dest);

    /**
     * Transform the given direction by the inverse of this dual quaternion's rotation (world to
     * local), ignoring the translation, without materializing {@code invert()} and store the result
     * back into {@code v}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the direction to transform (also receives the result)
     * @return {@code v}
     */
    default Float3 transformDirectionInverse(@Mutated Float3 v) { return transformDirectionInverse(v, v); }

    /**
     * Transform {@code p} by the inverse of this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    Float3 transformInverse(Float3R p, @Mutated Float3 dest);

    /**
     * Transform {@code p} by the inverse of this dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformInverse(Float3R p, @Mutated Double3 dest);

    /**
     * Transform ({@code x}, {@code y}, {@code z}) by the inverse of this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Float3 transformInverse(float x, float y, float z, @Mutated Float3 dest);

    /**
     * Transform ({@code x}, {@code y}, {@code z}) by the inverse of this dual quaternion and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformInverse(float x, float y, float z, @Mutated Double3 dest);

    /**
     * Transform {@code p} by the inverse of this dual quaternion and store the result back into
     * {@code p}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform (also receives the result)
     * @return {@code p}
     */
    default Float3 transformInverse(@Mutated Float3 p) { return transformInverse(p, p); }

    /**
     * Transform the given position by this dual quaternion, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    Float3 transformPosition(Float3R p, @Mutated Float3 dest);

    /**
     * Transform the given position by this dual quaternion, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformPosition(Float3R p, @Mutated Double3 dest);

    /**
     * Transform the given position by this dual quaternion, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Float3 transformPosition(float x, float y, float z, @Mutated Float3 dest);

    /**
     * Transform the given position by this dual quaternion, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformPosition(float x, float y, float z, @Mutated Double3 dest);

    /**
     * Transform the given position by this dual quaternion, treating it as a point with an implicit
     * {@code w = 1} and store the result back into {@code p}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform (also receives the result)
     * @return {@code p}
     */
    default Float3 transformPosition(@Mutated Float3 p) { return transformPosition(p, p); }

    /**
     * Transform the given position by the inverse of this dual quaternion (world to local), without
     * materializing {@code invert()} and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    Float3 transformPositionInverse(Float3R p, @Mutated Float3 dest);

    /**
     * Transform the given position by the inverse of this dual quaternion (world to local), without
     * materializing {@code invert()} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformPositionInverse(Float3R p, @Mutated Double3 dest);

    /**
     * Transform the given position by the inverse of this dual quaternion (world to local), without
     * materializing {@code invert()} and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Float3 transformPositionInverse(float x, float y, float z, @Mutated Float3 dest);

    /**
     * Transform the given position by the inverse of this dual quaternion (world to local), without
     * materializing {@code invert()} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformPositionInverse(float x, float y, float z, @Mutated Double3 dest);

    /**
     * Transform the given position by the inverse of this dual quaternion (world to local), without
     * materializing {@code invert()} and store the result back into {@code p}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param p the position to transform (also receives the result)
     * @return {@code p}
     */
    default Float3 transformPositionInverse(@Mutated Float3 p) { return transformPositionInverse(p, p); }

    /**
     * Transform the given vector by the rotation part of this dual quaternion, ignoring the
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    Float3 transformVector(Float3R v, @Mutated Float3 dest);

    /**
     * Transform the given vector by the rotation part of this dual quaternion, ignoring the
     * translation and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformVector(Float3R v, @Mutated Double3 dest);

    /**
     * Transform the given vector by the rotation part of this dual quaternion, ignoring the
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Float3 transformVector(float x, float y, float z, @Mutated Float3 dest);

    /**
     * Transform the given vector by the rotation part of this dual quaternion, ignoring the
     * translation and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformVector(float x, float y, float z, @Mutated Double3 dest);

    /**
     * Transform the given vector by the rotation part of this dual quaternion, ignoring the
     * translation and store the result back into {@code v}.
     * <p>
     * Valid input: this dual quaternion must be a unit dual quaternion.
     *
     * @param v the vector to transform (also receives the result)
     * @return {@code v}
     */
    default Float3 transformVector(@Mutated Float3 v) { return transformVector(v, v); }
    /** {@return the value of the {@code rX} component} <p>Valid input: any value, NaN and the infinities included. */
    float rX();
    /** {@return the value of the {@code rY} component} <p>Valid input: any value, NaN and the infinities included. */
    float rY();
    /** {@return the value of the {@code rZ} component} <p>Valid input: any value, NaN and the infinities included. */
    float rZ();
    /** {@return the value of the {@code rW} component} <p>Valid input: any value, NaN and the infinities included. */
    float rW();
    /** {@return the value of the {@code dX} component} <p>Valid input: any value, NaN and the infinities included. */
    float dX();
    /** {@return the value of the {@code dY} component} <p>Valid input: any value, NaN and the infinities included. */
    float dY();
    /** {@return the value of the {@code dZ} component} <p>Valid input: any value, NaN and the infinities included. */
    float dZ();
    /** {@return the value of the {@code dW} component} <p>Valid input: any value, NaN and the infinities included. */
    float dW();

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
        if (dest.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 8);
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
        if (dest.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 32);
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
    FloatDualQuat storeUnsafe(long address);

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
        if (dest.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 8);
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
        if (dest.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeDoubleAbsolute(pos, dest);
        dest.position(pos + 64);
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
    FloatDualQuat storeDoubleUnsafe(long address);

    /** {@return whether all components of this dual quaternion are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    boolean isFinite();

    /** {@return whether any component of this dual quaternion is NaN} <p>Valid input: any value, NaN and the infinities included. */
    boolean isNaN();

    /**
     * Compare this dual quaternion component-wise against {@code other}, allowing a difference
     * of at most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
     * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and
     * a NaN component never compares equal to anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the dual quaternion to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    boolean equalsEpsilon(FloatDualQuatR other, float epsilon);

    /**
     * Compare this dual quaternion with the given object for element-wise equality.
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
     * @return {@code true} if {@code obj} is a dual quaternion of this library with element-wise
     *        equal components, {@code false} otherwise
     */
    boolean equals(@org.jspecify.annotations.Nullable Object obj);

    /**
     * Compute a hash code consistent with {@link #equals}: it is derived from the components via
     * {@code Float.floatToIntBits} alone.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return the hash code of this dual quaternion
     */
    int hashCode();

    /** Backs {@code ZERO()}: defers the shared instance's
     *  allocation to first use, avoiding a class-initialization cycle with the
     *  implementation class. Not part of the public API. */
    final class Holder {
        private Holder() {}
        static final FloatDualQuatR ZERO = Joml.floatDualQuat(0, 0, 0, 0, 0, 0, 0, 0);
    }
}
