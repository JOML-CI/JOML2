// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import java.nio.ByteBuffer;
import java.lang.foreign.MemorySegment;

/**
 * Read-only view of a rectangle of single-precision {@code float} components.
 * <p>
 * Declares the accessors, queries, store methods and dest-form operations that read but never
 * mutate the receiver; the mutable counterpart is {@link FloatRect}. APIs that only read a
 * rectangle should accept {@code FloatRectR}, so callers can pass mutable instances without
 * exposing them to modification.
 * <p>
 * Arguments of type {@code FloatRectR} must be instances created by the library ({@link Joml}
 * factories / the library's own types); the implementations read cached state through the library's
 * own classes, so foreign implementations of the {@code *R} interfaces are not supported as
 * arguments.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns). Only instances
 * of this library's implementation compare equal to each other; the {@code equals} of a rectangle
 * never returns {@code true} for an object of another type.
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 */
public interface FloatRectR {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 16;

    /** {@return a shared all-zero rectangle} Never mutate it - it is returned as the read-only view for that reason. <p>Valid input: any value, NaN and the infinities included. */
    public static FloatRectR ZERO() { return Holder.ZERO; }


    /**
     * Add each bound of {@code other} to the corresponding bound of this rectangle and store the
     * result in {@code dest}.
     * <p>
     * The bounds combine element-wise: each bound of the result is the sum of the corresponding
     * bounds. That is neither the Minkowski sum of the two rectangles nor a translation; to move a
     * rectangle, add the same offset to both of its corners.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to add
     * @param dest will hold the result
     * @return dest
     */
    FloatRect add(FloatRectR other, @Mutated FloatRect dest);

    /**
     * Add each bound of {@code other} to the corresponding bound of this rectangle and store the
     * result in {@code dest}.
     * <p>
     * The bounds combine element-wise: each bound of the result is the sum of the corresponding
     * bounds. That is neither the Minkowski sum of the two rectangles nor a translation; to move a
     * rectangle, add the same offset to both of its corners.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to add
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect add(FloatRectR other, @Mutated DoubleRect dest);

    /**
     * Add each bound of ({@code minX}, {@code minY}, {@code maxX}, {@code maxY}) to the
     * corresponding bound of this rectangle and store the result in {@code dest}.
     * <p>
     * The bounds combine element-wise: each bound of the result is the sum of the corresponding
     * bounds. That is neither the Minkowski sum of the two rectangles nor a translation; to move a
     * rectangle, add the same offset to both of its corners.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param minY the {@code minY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxX the {@code maxX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxY the {@code maxY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param dest will hold the result
     * @return dest
     */
    FloatRect add(float minX, float minY, float maxX, float maxY, @Mutated FloatRect dest);

    /**
     * Add each bound of ({@code minX}, {@code minY}, {@code maxX}, {@code maxY}) to the
     * corresponding bound of this rectangle and store the result in {@code dest}.
     * <p>
     * The bounds combine element-wise: each bound of the result is the sum of the corresponding
     * bounds. That is neither the Minkowski sum of the two rectangles nor a translation; to move a
     * rectangle, add the same offset to both of its corners.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param minY the {@code minY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxX the {@code maxX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxY the {@code maxY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect add(float minX, float minY, float maxX, float maxY, @Mutated DoubleRect dest);

    /**
     * Reflect this rectangle through the origin, so that it spans {@code (-maxX, -maxY)} to
     * {@code (-minX, -minY)} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatRect negate(@Mutated FloatRect dest);

    /**
     * Reflect this rectangle through the origin, so that it spans {@code (-maxX, -maxY)} to
     * {@code (-minX, -minY)} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect negate(@Mutated DoubleRect dest);

    /**
     * Subtract each bound of {@code other} from the corresponding bound of this rectangle and store
     * the result in {@code dest}.
     * <p>
     * The bounds combine element-wise: each bound of the result is the difference of the
     * corresponding bounds. That is neither the Minkowski difference of the two rectangles nor a
     * translation; to move a rectangle, add the same offset to both of its corners.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to subtract
     * @param dest will hold the result
     * @return dest
     */
    FloatRect sub(FloatRectR other, @Mutated FloatRect dest);

    /**
     * Subtract each bound of {@code other} from the corresponding bound of this rectangle and store
     * the result in {@code dest}.
     * <p>
     * The bounds combine element-wise: each bound of the result is the difference of the
     * corresponding bounds. That is neither the Minkowski difference of the two rectangles nor a
     * translation; to move a rectangle, add the same offset to both of its corners.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to subtract
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect sub(FloatRectR other, @Mutated DoubleRect dest);

    /**
     * Subtract each bound of ({@code minX}, {@code minY}, {@code maxX}, {@code maxY}) from the
     * corresponding bound of this rectangle and store the result in {@code dest}.
     * <p>
     * The bounds combine element-wise: each bound of the result is the difference of the
     * corresponding bounds. That is neither the Minkowski difference of the two rectangles nor a
     * translation; to move a rectangle, add the same offset to both of its corners.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param minY the {@code minY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxX the {@code maxX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxY the {@code maxY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param dest will hold the result
     * @return dest
     */
    FloatRect sub(float minX, float minY, float maxX, float maxY, @Mutated FloatRect dest);

    /**
     * Subtract each bound of ({@code minX}, {@code minY}, {@code maxX}, {@code maxY}) from the
     * corresponding bound of this rectangle and store the result in {@code dest}.
     * <p>
     * The bounds combine element-wise: each bound of the result is the difference of the
     * corresponding bounds. That is neither the Minkowski difference of the two rectangles nor a
     * translation; to move a rectangle, add the same offset to both of its corners.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param minY the {@code minY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxX the {@code maxX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxY the {@code maxY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect sub(float minX, float minY, float maxX, float maxY, @Mutated DoubleRect dest);

    /**
     * Set the maximum corner of this rectangle to {@code max} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param max the maximum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    FloatRect setMax(Float2R max, @Mutated FloatRect dest);

    /**
     * Set the maximum corner of this rectangle to {@code max} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param max the maximum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect setMax(Float2R max, @Mutated DoubleRect dest);

    /**
     * Set the maximum corner of this rectangle to ({@code x}, {@code y}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    FloatRect setMax(float x, float y, @Mutated FloatRect dest);

    /**
     * Set the maximum corner of this rectangle to ({@code x}, {@code y}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect setMax(float x, float y, @Mutated DoubleRect dest);

    /**
     * Set the minimum corner of this rectangle to {@code min} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param min the minimum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    FloatRect setMin(Float2R min, @Mutated FloatRect dest);

    /**
     * Set the minimum corner of this rectangle to {@code min} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param min the minimum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect setMin(Float2R min, @Mutated DoubleRect dest);

    /**
     * Set the minimum corner of this rectangle to ({@code x}, {@code y}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    FloatRect setMin(float x, float y, @Mutated FloatRect dest);

    /**
     * Set the minimum corner of this rectangle to ({@code x}, {@code y}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect setMin(float x, float y, @Mutated DoubleRect dest);

    /**
     * Convert this rectangle to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect toDouble(@Mutated DoubleRect dest);

    /**
     * Convert this rectangle to {@code int} precision and store the result in {@code dest}.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    IntRect toInt(@Mutated IntRect dest);

    /**
     * Convert this rectangle to {@code int} precision and store the result in {@code dest}.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mode the rounding mode to use
     * @param dest will hold the result
     * @return dest
     */
    IntRect toInt(RoundingMode mode, @Mutated IntRect dest);

    /**
     * Linearly interpolate between this rectangle and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this rectangle (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    FloatRect lerp(FloatRectR other, float t, @Mutated FloatRect dest);

    /**
     * Linearly interpolate between this rectangle and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this rectangle (interpolation factor {@code 0}) and ends at
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
     * @param other the rectangle to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect lerp(FloatRectR other, float t, @Mutated DoubleRect dest);

    /**
     * Linearly interpolate between this rectangle and ({@code minX}, {@code minY}, {@code maxX},
     * {@code maxY}) using the interpolation factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this rectangle (interpolation factor {@code 0}) and ends at
     * ({@code minX}, {@code minY}, {@code maxX}, {@code maxY}) (interpolation factor {@code 1}).
     * Each linearly interpolated component is {@code this + (other - this) * t}, as in JOML and
     * glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the
     * rounding of {@code other - this}, which shows when this component is much larger in magnitude
     * than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param minY the {@code minY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxX the {@code maxX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxY the {@code maxY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    FloatRect lerp(float minX, float minY, float maxX, float maxY, float t, @Mutated FloatRect dest);

    /**
     * Linearly interpolate between this rectangle and ({@code minX}, {@code minY}, {@code maxX},
     * {@code maxY}) using the interpolation factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this rectangle (interpolation factor {@code 0}) and ends at
     * ({@code minX}, {@code minY}, {@code maxX}, {@code maxY}) (interpolation factor {@code 1}).
     * Each linearly interpolated component is {@code this + (other - this) * t}, as in JOML and
     * glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the
     * rounding of {@code other - this}, which shows when this component is much larger in magnitude
     * than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param minY the {@code minY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxX the {@code maxX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxY the {@code maxY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect lerp(float minX, float minY, float maxX, float maxY, float t, @Mutated DoubleRect dest);

    /**
     * Swap the minimum and maximum bounds of this rectangle where necessary so the bounds are valid
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatRect correctBounds(@Mutated FloatRect dest);

    /**
     * Swap the minimum and maximum bounds of this rectangle where necessary so the bounds are valid
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
    DoubleRect correctBounds(@Mutated DoubleRect dest);

    /**
     * Expand this rectangle by {@code margin} in every direction and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param margin the amount to expand by in every direction
     * @param dest will hold the result
     * @return dest
     */
    FloatRect expand(float margin, @Mutated FloatRect dest);

    /**
     * Expand this rectangle by {@code margin} in every direction and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param margin the amount to expand by in every direction
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect expand(float margin, @Mutated DoubleRect dest);

    /**
     * Set this rectangle to the intersection of itself and {@code other} (disjoint inputs yield
     * inverted bounds - check {@code isValid()}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to intersect with
     * @param dest will hold the result
     * @return dest
     */
    FloatRect intersect(FloatRectR other, @Mutated FloatRect dest);

    /**
     * Set this rectangle to the intersection of itself and {@code other} (disjoint inputs yield
     * inverted bounds - check {@code isValid()}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to intersect with
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect intersect(FloatRectR other, @Mutated DoubleRect dest);

    /**
     * Set this rectangle to the intersection of itself and ({@code minX}, {@code minY},
     * {@code maxX}, {@code maxY}) (disjoint inputs yield inverted bounds - check {@code isValid()})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param minY the {@code minY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxX the {@code maxX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxY the {@code maxY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param dest will hold the result
     * @return dest
     */
    FloatRect intersect(float minX, float minY, float maxX, float maxY, @Mutated FloatRect dest);

    /**
     * Set this rectangle to the intersection of itself and ({@code minX}, {@code minY},
     * {@code maxX}, {@code maxY}) (disjoint inputs yield inverted bounds - check {@code isValid()})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param minY the {@code minY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxX the {@code maxX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxY the {@code maxY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect intersect(float minX, float minY, float maxX, float maxY, @Mutated DoubleRect dest);

    /**
     * Scale the bounds of this rectangle about the origin {@code (0, 0)} by the given factors (a
     * negative factor mirrors the rectangle, which keeps its minimum below its maximum) and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sx the scale factor along the x axis
     * @param sy the scale factor along the y axis
     * @param dest will hold the result
     * @return dest
     */
    FloatRect scale(float sx, float sy, @Mutated FloatRect dest);

    /**
     * Scale the bounds of this rectangle about the origin {@code (0, 0)} by the given factors (a
     * negative factor mirrors the rectangle, which keeps its minimum below its maximum) and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sx the scale factor along the x axis
     * @param sy the scale factor along the y axis
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect scale(float sx, float sy, @Mutated DoubleRect dest);

    /**
     * Translate this rectangle by {@code delta} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    FloatRect translate(Float2R delta, @Mutated FloatRect dest);

    /**
     * Translate this rectangle by {@code delta} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect translate(Float2R delta, @Mutated DoubleRect dest);

    /**
     * Translate this rectangle by ({@code x}, {@code y}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    FloatRect translate(float x, float y, @Mutated FloatRect dest);

    /**
     * Translate this rectangle by ({@code x}, {@code y}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect translate(float x, float y, @Mutated DoubleRect dest);

    /**
     * Set this rectangle to the union of itself and {@code other} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to include in the union
     * @param dest will hold the result
     * @return dest
     */
    FloatRect union(FloatRectR other, @Mutated FloatRect dest);

    /**
     * Set this rectangle to the union of itself and {@code other} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to include in the union
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect union(FloatRectR other, @Mutated DoubleRect dest);

    /**
     * Set this rectangle to the union of itself and ({@code minX}, {@code minY}, {@code maxX},
     * {@code maxY}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param minY the {@code minY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxX the {@code maxX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxY the {@code maxY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param dest will hold the result
     * @return dest
     */
    FloatRect union(float minX, float minY, float maxX, float maxY, @Mutated FloatRect dest);

    /**
     * Set this rectangle to the union of itself and ({@code minX}, {@code minY}, {@code maxX},
     * {@code maxY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param minY the {@code minY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxX the {@code maxX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxY the {@code maxY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect union(float minX, float minY, float maxX, float maxY, @Mutated DoubleRect dest);

    /**
     * Grow this rectangle to include the point {@code p} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to include
     * @param dest will hold the result
     * @return dest
     */
    FloatRect union(Float2R p, @Mutated FloatRect dest);

    /**
     * Grow this rectangle to include the point {@code p} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to include
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect union(Float2R p, @Mutated DoubleRect dest);

    /**
     * Grow this rectangle to include the point ({@code x}, {@code y}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    FloatRect union(float x, float y, @Mutated FloatRect dest);

    /**
     * Grow this rectangle to include the point ({@code x}, {@code y}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect union(float x, float y, @Mutated DoubleRect dest);

    /**
     * Compute the area of this rectangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the area of this rectangle
     */
    float area();

    /**
     * Compute the x coordinate of the center of this rectangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the x coordinate of the center of this rectangle
     */
    float centerX();

    /**
     * Compute the y coordinate of the center of this rectangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the y coordinate of the center of this rectangle
     */
    float centerY();

    /**
     * Compute the point of this rectangle closest to the given point, i.e. the point clamped per
     * axis into the rectangle's bounds. For a point inside or on the rectangle, the result is the
     * point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    Float2 closestPointToPoint(Float2R p, @Mutated Float2 dest);

    /**
     * Compute the point of this rectangle closest to the given point, i.e. the point clamped per
     * axis into the rectangle's bounds. For a point inside or on the rectangle, the result is the
     * point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    Double2 closestPointToPoint(Float2R p, @Mutated Double2 dest);

    /**
     * Compute the point of this rectangle closest to the given point, i.e. the point clamped per
     * axis into the rectangle's bounds. For a point inside or on the rectangle, the result is the
     * point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param x the {@code x} component of the point {@code (x, y)} to find the closest point to
     * @param y the {@code y} component of the point {@code (x, y)} to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    Float2 closestPointToPoint(float x, float y, @Mutated Float2 dest);

    /**
     * Compute the point of this rectangle closest to the given point, i.e. the point clamped per
     * axis into the rectangle's bounds. For a point inside or on the rectangle, the result is the
     * point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param x the {@code x} component of the point {@code (x, y)} to find the closest point to
     * @param y the {@code y} component of the point {@code (x, y)} to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    Double2 closestPointToPoint(float x, float y, @Mutated Double2 dest);

    /**
     * Compute the point of this rectangle closest to the given point, i.e. the point clamped per
     * axis into the rectangle's bounds. For a point inside or on the rectangle, the result is the
     * point itself.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param p the point to find the closest point to (also receives the result)
     * @return {@code p}
     */
    default Float2 closestPointToPoint(@Mutated Float2 p) { return closestPointToPoint(p, p); }

    /**
     * Determine whether this rectangle contains the given point (boundary inclusive).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to test
     * @return {@code true} if this rectangle contains the given point (boundary inclusive),
     *        {@code false} otherwise
     */
    boolean containsPoint(Float2R p);

    /**
     * Determine whether this rectangle contains the given point (boundary inclusive).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return {@code true} if this rectangle contains the given point (boundary inclusive),
     *        {@code false} otherwise
     */
    boolean containsPoint(float x, float y);

    /**
     * Determine whether this rectangle completely contains {@code o}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the rectangle to test
     * @return {@code true} if this rectangle completely contains {@code o}, {@code false} otherwise
     */
    boolean containsRect(FloatRectR o);

    /**
     * Determine whether this rectangle completely contains ({@code minX}, {@code minY},
     * {@code maxX}, {@code maxY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param minY the {@code minY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxX the {@code maxX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxY the {@code maxY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @return {@code true} if this rectangle completely contains ({@code minX}, {@code minY},
     *        {@code maxX}, {@code maxY}), {@code false} otherwise
     */
    boolean containsRect(float minX, float minY, float maxX, float maxY);

    /**
     * Compute the squared distance between this rectangle and the given point, i.e. the squared
     * length of the difference between the point and its per-axis clamp into the rectangle's
     * bounds; zero for a point inside or on the rectangle.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this rectangle and the given point, i.e. the squared
     *        length of the difference between the point and its per-axis clamp into the rectangle's
     *        bounds; zero for a point inside or on the rectangle
     */
    float distanceSquaredToPoint(Float2R p);

    /**
     * Compute the squared distance between this rectangle and the given point, i.e. the squared
     * length of the difference between the point and its per-axis clamp into the rectangle's
     * bounds; zero for a point inside or on the rectangle.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param x the {@code x} component of the point {@code (x, y)} to measure the distance to
     * @param y the {@code y} component of the point {@code (x, y)} to measure the distance to
     * @return the squared distance between this rectangle and the given point, i.e. the squared
     *        length of the difference between the point and its per-axis clamp into the rectangle's
     *        bounds; zero for a point inside or on the rectangle
     */
    float distanceSquaredToPoint(float x, float y);

    /**
     * Compute the squared distance between this rectangle and the given rectangle, i.e. the squared
     * length of the shortest vector between any two points of the two rectangles; zero when they
     * overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component; the minimum corner of {@code other} must not exceed the maximum corner of
     * {@code other} in any component.
     *
     * @param other the rectangle to measure the distance to
     * @return the squared distance between this rectangle and the given rectangle, i.e. the squared
     *        length of the shortest vector between any two points of the two rectangles; zero when
     *        they overlap or touch
     */
    float distanceSquaredToRect(FloatRectR other);

    /**
     * Compute the squared distance between this rectangle and the given rectangle, i.e. the squared
     * length of the shortest vector between any two points of the two rectangles; zero when they
     * overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component; {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component.
     *
     * @param minX the {@code minX} component of the rectangle {@code (minX, minY, maxX, maxY)} to
     *        measure the distance to
     * @param minY the {@code minY} component of the rectangle {@code (minX, minY, maxX, maxY)} to
     *        measure the distance to
     * @param maxX the {@code maxX} component of the rectangle {@code (minX, minY, maxX, maxY)} to
     *        measure the distance to
     * @param maxY the {@code maxY} component of the rectangle {@code (minX, minY, maxX, maxY)} to
     *        measure the distance to
     * @return the squared distance between this rectangle and the given rectangle, i.e. the squared
     *        length of the shortest vector between any two points of the two rectangles; zero when
     *        they overlap or touch
     */
    float distanceSquaredToRect(float minX, float minY, float maxX, float maxY);

    /**
     * Compute the distance between this rectangle and the given point, i.e. the length of the
     * difference between the point and its per-axis clamp into the rectangle's bounds; zero for a
     * point inside or on the rectangle.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param p the point to measure the distance to
     * @return the distance between this rectangle and the given point, i.e. the length of the
     *        difference between the point and its per-axis clamp into the rectangle's bounds; zero
     *        for a point inside or on the rectangle
     */
    float distanceToPoint(Float2R p);

    /**
     * Compute the distance between this rectangle and the given point, i.e. the length of the
     * difference between the point and its per-axis clamp into the rectangle's bounds; zero for a
     * point inside or on the rectangle.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param x the {@code x} component of the point {@code (x, y)} to measure the distance to
     * @param y the {@code y} component of the point {@code (x, y)} to measure the distance to
     * @return the distance between this rectangle and the given point, i.e. the length of the
     *        difference between the point and its per-axis clamp into the rectangle's bounds; zero
     *        for a point inside or on the rectangle
     */
    float distanceToPoint(float x, float y);

    /**
     * Compute the distance between this rectangle and the given rectangle, i.e. the length of the
     * shortest vector between any two points of the two rectangles; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component; the minimum corner of {@code other} must not exceed the maximum corner of
     * {@code other} in any component.
     *
     * @param other the rectangle to measure the distance to
     * @return the distance between this rectangle and the given rectangle, i.e. the length of the
     *        shortest vector between any two points of the two rectangles; zero when they overlap
     *        or touch
     */
    float distanceToRect(FloatRectR other);

    /**
     * Compute the distance between this rectangle and the given rectangle, i.e. the length of the
     * shortest vector between any two points of the two rectangles; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component; {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component.
     *
     * @param minX the {@code minX} component of the rectangle {@code (minX, minY, maxX, maxY)} to
     *        measure the distance to
     * @param minY the {@code minY} component of the rectangle {@code (minX, minY, maxX, maxY)} to
     *        measure the distance to
     * @param maxX the {@code maxX} component of the rectangle {@code (minX, minY, maxX, maxY)} to
     *        measure the distance to
     * @param maxY the {@code maxY} component of the rectangle {@code (minX, minY, maxX, maxY)} to
     *        measure the distance to
     * @return the distance between this rectangle and the given rectangle, i.e. the length of the
     *        shortest vector between any two points of the two rectangles; zero when they overlap
     *        or touch
     */
    float distanceToRect(float minX, float minY, float maxX, float maxY);

    /**
     * Get the center of this rectangle and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 getCenter(@Mutated Float2 dest);

    /**
     * Get the center of this rectangle and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 getCenter(@Mutated Double2 dest);

    /**
     * Get the maximum corner of this rectangle and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 getMax(@Mutated Float2 dest);

    /**
     * Get the maximum corner of this rectangle and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 getMax(@Mutated Double2 dest);

    /**
     * Get the minimum corner of this rectangle and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 getMin(@Mutated Float2 dest);

    /**
     * Get the minimum corner of this rectangle and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 getMin(@Mutated Double2 dest);

    /**
     * Get the size, i.e. the maximum minus the minimum corner per axis of this rectangle and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 getSize(@Mutated Float2 dest);

    /**
     * Get the size, i.e. the maximum minus the minimum corner per axis of this rectangle and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 getSize(@Mutated Double2 dest);

    /**
     * Compute the height of this rectangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the height of this rectangle
     */
    float height();

    /**
     * Determine whether this rectangle intersects {@code o}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the rectangle to test
     * @return {@code true} if this rectangle intersects {@code o}, {@code false} otherwise
     */
    boolean intersectsRect(FloatRectR o);

    /**
     * Determine whether this rectangle intersects ({@code minX}, {@code minY}, {@code maxX},
     * {@code maxY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param minY the {@code minY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxX the {@code maxX} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @param maxY the {@code maxY} component of the rectangle {@code (minX, minY, maxX, maxY)}
     * @return {@code true} if this rectangle intersects ({@code minX}, {@code minY}, {@code maxX},
     *        {@code maxY}), {@code false} otherwise
     */
    boolean intersectsRect(float minX, float minY, float maxX, float maxY);

    /**
     * Determine whether this rectangle is valid, i.e. no minimum bound exceeds its maximum.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return {@code true} if this rectangle is valid, i.e. no minimum bound exceeds its maximum,
     *        {@code false} otherwise
     */
    boolean isValid();

    /**
     * Compute the width of this rectangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the width of this rectangle
     */
    float width();
    /** {@return the value of the {@code minX} component} <p>Valid input: any value, NaN and the infinities included. */
    float minX();
    /** {@return the value of the {@code minY} component} <p>Valid input: any value, NaN and the infinities included. */
    float minY();
    /** {@return the value of the {@code maxX} component} <p>Valid input: any value, NaN and the infinities included. */
    float maxX();
    /** {@return the value of the {@code maxY} component} <p>Valid input: any value, NaN and the infinities included. */
    float maxY();

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
    FloatRect storeUnsafe(long address);

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
    FloatRect storeDoubleUnsafe(long address);

    /** {@return whether all components of this rectangle are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    boolean isFinite();

    /** {@return whether any component of this rectangle is NaN} <p>Valid input: any value, NaN and the infinities included. */
    boolean isNaN();

    /**
     * Compare this rectangle component-wise against {@code other}, allowing a difference
     * of at most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
     * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and
     * a NaN component never compares equal to anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the rectangle to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    boolean equalsEpsilon(FloatRectR other, float epsilon);

    /**
     * Compare this rectangle with the given object for element-wise equality.
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
     * @return {@code true} if {@code obj} is a rectangle of this library with element-wise equal
     *        components, {@code false} otherwise
     */
    boolean equals(@org.jspecify.annotations.Nullable Object obj);

    /**
     * Compute a hash code consistent with {@link #equals}: it is derived from the components via
     * {@code Float.floatToIntBits} alone.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return the hash code of this rectangle
     */
    int hashCode();

    /** Backs {@code ZERO()}: defers the shared instance's
     *  allocation to first use, avoiding a class-initialization cycle with the
     *  implementation class. Not part of the public API. */
    final class Holder {
        private Holder() {}
        static final FloatRectR ZERO = Joml.floatRect(0, 0, 0, 0);
    }
}
