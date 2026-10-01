// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ByteBuffer;

/**
 * Read-only view of a rectangle of {@code int} components.
 * <p>
 * Declares the accessors, queries, store methods and dest-form operations that read but never
 * mutate the receiver; the mutable counterpart is {@link IntRect}. APIs that only read a rectangle
 * should accept {@code IntRectR}, so callers can pass mutable instances without exposing them to
 * modification.
 * <p>
 * Arguments of type {@code IntRectR} must be instances created by the library ({@link Joml}
 * factories / the library's own types); the implementations read cached state through the library's
 * own classes, so foreign implementations of the {@code *R} interfaces are not supported as
 * arguments.
 * <p>
 * {@code equals} compares the components element-wise with {@code ==}. {@code hashCode} is
 * consistent with it. Only instances of this library's implementation compare equal to each other;
 * the {@code equals} of a rectangle never returns {@code true} for an object of another type.
 * <p>
 * {@code equalsEpsilon} compares per component with an exact, non-negative integer tolerance: the
 * difference is widened to {@code long} before its magnitude is taken, so the two are compared
 * exactly without overflow, and a negative {@code epsilon} matches nothing.
 */
public interface IntRectR {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 16;

    /** {@return a shared all-zero rectangle} Never mutate it - it is returned as the read-only view for that reason. <p>Valid input: any value. */
    public static IntRectR ZERO() { return Holder.ZERO; }


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
    IntRect add(IntRectR other, @Mutated IntRect dest);

    /**
     * Add each bound of {@code other} to the corresponding bound of this rectangle and store the
     * result in {@code dest}.
     * <p>
     * The bounds combine element-wise: each bound of the result is the sum of the corresponding
     * bounds. That is neither the Minkowski sum of the two rectangles nor a translation; to move a
     * rectangle, add the same offset to both of its corners.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to add
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect add(IntRectR other, @Mutated DoubleRect dest);

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
    IntRect add(int minX, int minY, int maxX, int maxY, @Mutated IntRect dest);

    /**
     * Add each bound of ({@code minX}, {@code minY}, {@code maxX}, {@code maxY}) to the
     * corresponding bound of this rectangle and store the result in {@code dest}.
     * <p>
     * The bounds combine element-wise: each bound of the result is the sum of the corresponding
     * bounds. That is neither the Minkowski sum of the two rectangles nor a translation; to move a
     * rectangle, add the same offset to both of its corners.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
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
    DoubleRect add(int minX, int minY, int maxX, int maxY, @Mutated DoubleRect dest);

    /**
     * Reflect this rectangle through the origin, so that it spans {@code (-maxX, -maxY)} to
     * {@code (-minX, -minY)} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    IntRect negate(@Mutated IntRect dest);

    /**
     * Reflect this rectangle through the origin, so that it spans {@code (-maxX, -maxY)} to
     * {@code (-minX, -minY)} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
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
    IntRect sub(IntRectR other, @Mutated IntRect dest);

    /**
     * Subtract each bound of {@code other} from the corresponding bound of this rectangle and store
     * the result in {@code dest}.
     * <p>
     * The bounds combine element-wise: each bound of the result is the difference of the
     * corresponding bounds. That is neither the Minkowski difference of the two rectangles nor a
     * translation; to move a rectangle, add the same offset to both of its corners.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to subtract
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect sub(IntRectR other, @Mutated DoubleRect dest);

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
    IntRect sub(int minX, int minY, int maxX, int maxY, @Mutated IntRect dest);

    /**
     * Subtract each bound of ({@code minX}, {@code minY}, {@code maxX}, {@code maxY}) from the
     * corresponding bound of this rectangle and store the result in {@code dest}.
     * <p>
     * The bounds combine element-wise: each bound of the result is the difference of the
     * corresponding bounds. That is neither the Minkowski difference of the two rectangles nor a
     * translation; to move a rectangle, add the same offset to both of its corners.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
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
    DoubleRect sub(int minX, int minY, int maxX, int maxY, @Mutated DoubleRect dest);

    /**
     * Set the maximum corner of this rectangle to {@code max} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param max the maximum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    IntRect setMax(Int2R max, @Mutated IntRect dest);

    /**
     * Set the maximum corner of this rectangle to {@code max} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param max the maximum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect setMax(Int2R max, @Mutated DoubleRect dest);

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
    IntRect setMax(int x, int y, @Mutated IntRect dest);

    /**
     * Set the maximum corner of this rectangle to ({@code x}, {@code y}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect setMax(int x, int y, @Mutated DoubleRect dest);

    /**
     * Set the minimum corner of this rectangle to {@code min} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param min the minimum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    IntRect setMin(Int2R min, @Mutated IntRect dest);

    /**
     * Set the minimum corner of this rectangle to {@code min} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param min the minimum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect setMin(Int2R min, @Mutated DoubleRect dest);

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
    IntRect setMin(int x, int y, @Mutated IntRect dest);

    /**
     * Set the minimum corner of this rectangle to ({@code x}, {@code y}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect setMin(int x, int y, @Mutated DoubleRect dest);

    /**
     * Convert this rectangle to {@code float} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    FloatRect toFloat(@Mutated FloatRect dest);

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
     * Swap the minimum and maximum bounds of this rectangle where necessary so the bounds are valid
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    IntRect correctBounds(@Mutated IntRect dest);

    /**
     * Swap the minimum and maximum bounds of this rectangle where necessary so the bounds are valid
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
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
    IntRect expand(int margin, @Mutated IntRect dest);

    /**
     * Expand this rectangle by {@code margin} in every direction and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param margin the amount to expand by in every direction
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect expand(int margin, @Mutated DoubleRect dest);

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
    IntRect intersect(IntRectR other, @Mutated IntRect dest);

    /**
     * Set this rectangle to the intersection of itself and {@code other} (disjoint inputs yield
     * inverted bounds - check {@code isValid()}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to intersect with
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect intersect(IntRectR other, @Mutated DoubleRect dest);

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
    IntRect intersect(int minX, int minY, int maxX, int maxY, @Mutated IntRect dest);

    /**
     * Set this rectangle to the intersection of itself and ({@code minX}, {@code minY},
     * {@code maxX}, {@code maxY}) (disjoint inputs yield inverted bounds - check {@code isValid()})
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
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
    DoubleRect intersect(int minX, int minY, int maxX, int maxY, @Mutated DoubleRect dest);

    /**
     * Translate this rectangle by {@code delta} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    IntRect translate(Int2R delta, @Mutated IntRect dest);

    /**
     * Translate this rectangle by {@code delta} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect translate(Int2R delta, @Mutated DoubleRect dest);

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
    IntRect translate(int x, int y, @Mutated IntRect dest);

    /**
     * Translate this rectangle by ({@code x}, {@code y}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect translate(int x, int y, @Mutated DoubleRect dest);

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
    IntRect union(IntRectR other, @Mutated IntRect dest);

    /**
     * Set this rectangle to the union of itself and {@code other} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to include in the union
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect union(IntRectR other, @Mutated DoubleRect dest);

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
    IntRect union(int minX, int minY, int maxX, int maxY, @Mutated IntRect dest);

    /**
     * Set this rectangle to the union of itself and ({@code minX}, {@code minY}, {@code maxX},
     * {@code maxY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
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
    DoubleRect union(int minX, int minY, int maxX, int maxY, @Mutated DoubleRect dest);

    /**
     * Grow this rectangle to include the point {@code p} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to include
     * @param dest will hold the result
     * @return dest
     */
    IntRect union(Int2R p, @Mutated IntRect dest);

    /**
     * Grow this rectangle to include the point {@code p} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to include
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect union(Int2R p, @Mutated DoubleRect dest);

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
    IntRect union(int x, int y, @Mutated IntRect dest);

    /**
     * Grow this rectangle to include the point ({@code x}, {@code y}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleRect union(int x, int y, @Mutated DoubleRect dest);

    /**
     * Compute the area of this rectangle.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the area of this rectangle
     */
    long area();

    /**
     * Compute the x coordinate of the center of this rectangle (integer division truncates toward
     * zero).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the x coordinate of the center of this rectangle (integer division truncates toward
     *        zero)
     */
    int centerX();

    /**
     * Compute the y coordinate of the center of this rectangle (integer division truncates toward
     * zero).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the y coordinate of the center of this rectangle (integer division truncates toward
     *        zero)
     */
    int centerY();

    /**
     * Compute the point of this rectangle closest to the given point, i.e. the point clamped per
     * axis into the rectangle's bounds. For a point inside or on the rectangle, the result is the
     * point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: the minimum corner of this rectangle must not exceed the maximum corner of this
     * rectangle in any component.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    Int2 closestPointToPoint(Int2R p, @Mutated Int2 dest);

    /**
     * Compute the point of this rectangle closest to the given point, i.e. the point clamped per
     * axis into the rectangle's bounds. For a point inside or on the rectangle, the result is the
     * point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the minimum corner of this rectangle must not exceed the maximum corner of this
     * rectangle in any component.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    Long2 closestPointToPoint(Int2R p, @Mutated Long2 dest);

    /**
     * Compute the point of this rectangle closest to the given point, i.e. the point clamped per
     * axis into the rectangle's bounds. For a point inside or on the rectangle, the result is the
     * point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the minimum corner of this rectangle must not exceed the maximum corner of this
     * rectangle in any component.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    Double2 closestPointToPoint(Int2R p, @Mutated Double2 dest);

    /**
     * Compute the point of this rectangle closest to the given point, i.e. the point clamped per
     * axis into the rectangle's bounds. For a point inside or on the rectangle, the result is the
     * point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: the minimum corner of this rectangle must not exceed the maximum corner of this
     * rectangle in any component.
     *
     * @param x the {@code x} component of the point {@code (x, y)} to find the closest point to
     * @param y the {@code y} component of the point {@code (x, y)} to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    Int2 closestPointToPoint(int x, int y, @Mutated Int2 dest);

    /**
     * Compute the point of this rectangle closest to the given point, i.e. the point clamped per
     * axis into the rectangle's bounds. For a point inside or on the rectangle, the result is the
     * point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the minimum corner of this rectangle must not exceed the maximum corner of this
     * rectangle in any component.
     *
     * @param x the {@code x} component of the point {@code (x, y)} to find the closest point to
     * @param y the {@code y} component of the point {@code (x, y)} to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    Long2 closestPointToPoint(int x, int y, @Mutated Long2 dest);

    /**
     * Compute the point of this rectangle closest to the given point, i.e. the point clamped per
     * axis into the rectangle's bounds. For a point inside or on the rectangle, the result is the
     * point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the minimum corner of this rectangle must not exceed the maximum corner of this
     * rectangle in any component.
     *
     * @param x the {@code x} component of the point {@code (x, y)} to find the closest point to
     * @param y the {@code y} component of the point {@code (x, y)} to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    Double2 closestPointToPoint(int x, int y, @Mutated Double2 dest);

    /**
     * Compute the point of this rectangle closest to the given point, i.e. the point clamped per
     * axis into the rectangle's bounds. For a point inside or on the rectangle, the result is the
     * point itself.
     * <p>
     * Valid input: the minimum corner of this rectangle must not exceed the maximum corner of this
     * rectangle in any component.
     *
     * @param p the point to find the closest point to (also receives the result)
     * @return {@code p}
     */
    default Int2 closestPointToPoint(@Mutated Int2 p) { return closestPointToPoint(p, p); }

    /**
     * Determine whether this rectangle contains the given point (boundary inclusive).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to test
     * @return {@code true} if this rectangle contains the given point (boundary inclusive),
     *        {@code false} otherwise
     */
    boolean containsPoint(Int2R p);

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
    boolean containsPoint(int x, int y);

    /**
     * Determine whether this rectangle completely contains {@code o}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the rectangle to test
     * @return {@code true} if this rectangle completely contains {@code o}, {@code false} otherwise
     */
    boolean containsRect(IntRectR o);

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
    boolean containsRect(int minX, int minY, int maxX, int maxY);

    /**
     * Compute the squared distance between this rectangle and the given point, i.e. the squared
     * length of the difference between the point and its per-axis clamp into the rectangle's
     * bounds; zero for a point inside or on the rectangle; Long.MAX_VALUE if that exceeds the long
     * range.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the minimum corner of this rectangle must not exceed the maximum corner of this
     * rectangle in any component.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this rectangle and the given point, i.e. the squared
     *        length of the difference between the point and its per-axis clamp into the rectangle's
     *        bounds; zero for a point inside or on the rectangle; Long.MAX_VALUE if that exceeds
     *        the long range
     */
    long distanceSquaredToPoint(Int2R p);

    /**
     * Compute the squared distance between this rectangle and the given point, i.e. the squared
     * length of the difference between the point and its per-axis clamp into the rectangle's
     * bounds; zero for a point inside or on the rectangle; Long.MAX_VALUE if that exceeds the long
     * range.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the minimum corner of this rectangle must not exceed the maximum corner of this
     * rectangle in any component.
     *
     * @param x the {@code x} component of the point {@code (x, y)} to measure the distance to
     * @param y the {@code y} component of the point {@code (x, y)} to measure the distance to
     * @return the squared distance between this rectangle and the given point, i.e. the squared
     *        length of the difference between the point and its per-axis clamp into the rectangle's
     *        bounds; zero for a point inside or on the rectangle; Long.MAX_VALUE if that exceeds
     *        the long range
     */
    long distanceSquaredToPoint(int x, int y);

    /**
     * Compute the squared distance between this rectangle and the given rectangle, i.e. the squared
     * length of the shortest vector between any two points of the two rectangles; zero when they
     * overlap or touch.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the minimum corner of this rectangle must not exceed the maximum corner of this
     * rectangle in any component; the minimum corner of {@code other} must not exceed the maximum
     * corner of {@code other} in any component.
     *
     * @param other the rectangle to measure the distance to
     * @return the squared distance between this rectangle and the given rectangle, i.e. the squared
     *        length of the shortest vector between any two points of the two rectangles; zero when
     *        they overlap or touch
     */
    long distanceSquaredToRect(IntRectR other);

    /**
     * Compute the squared distance between this rectangle and the given rectangle, i.e. the squared
     * length of the shortest vector between any two points of the two rectangles; zero when they
     * overlap or touch.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the minimum corner of this rectangle must not exceed the maximum corner of this
     * rectangle in any component; {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any
     * component.
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
    long distanceSquaredToRect(int minX, int minY, int maxX, int maxY);

    /**
     * Get the center of this rectangle and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int2 getCenter(@Mutated Int2 dest);

    /**
     * Get the center of this rectangle and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 getCenter(@Mutated Long2 dest);

    /**
     * Get the center of this rectangle and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
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
    Int2 getMax(@Mutated Int2 dest);

    /**
     * Get the maximum corner of this rectangle and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 getMax(@Mutated Long2 dest);

    /**
     * Get the maximum corner of this rectangle and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
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
    Int2 getMin(@Mutated Int2 dest);

    /**
     * Get the minimum corner of this rectangle and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 getMin(@Mutated Long2 dest);

    /**
     * Get the minimum corner of this rectangle and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
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
    Int2 getSize(@Mutated Int2 dest);

    /**
     * Get the size, i.e. the maximum minus the minimum corner per axis of this rectangle and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code long} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long2 getSize(@Mutated Long2 dest);

    /**
     * Get the size, i.e. the maximum minus the minimum corner per axis of this rectangle and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
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
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the height of this rectangle
     */
    long height();

    /**
     * Determine whether this rectangle intersects {@code o}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the rectangle to test
     * @return {@code true} if this rectangle intersects {@code o}, {@code false} otherwise
     */
    boolean intersectsRect(IntRectR o);

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
    boolean intersectsRect(int minX, int minY, int maxX, int maxY);

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
     * The value is computed and returned as {@code long}, so it is exact: a result beyond the
     * {@code int} range does not wrap.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the width of this rectangle
     */
    long width();
    /** {@return the value of the {@code minX} component} <p>Valid input: any value. */
    int minX();
    /** {@return the value of the {@code minY} component} <p>Valid input: any value. */
    int minY();
    /** {@return the value of the {@code maxX} component} <p>Valid input: any value. */
    int maxX();
    /** {@return the value of the {@code maxY} component} <p>Valid input: any value. */
    int maxY();

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @return dest
     */
    default int[] store(@Mutated int[] dest) { return store(dest, 0); }

    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    int[] store(@Mutated int[] dest, int offset);

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default IntBuffer store(@Mutated IntBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default IntBuffer storeAbsolute(@Mutated IntBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    IntBuffer storeAbsolute(int index, @Mutated IntBuffer dest);

    /**
     * Store the elements into the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default IntBuffer storeRelative(@Mutated IntBuffer dest) {
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
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
     * Store the elements into the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    IntRect storeUnsafe(long address);

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @return dest
     */
    default long[] store(@Mutated long[] dest) { return store(dest, 0); }

    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    long[] store(@Mutated long[] dest, int offset);

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default LongBuffer store(@Mutated LongBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default LongBuffer storeAbsolute(@Mutated LongBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    LongBuffer storeAbsolute(int index, @Mutated LongBuffer dest);

    /**
     * Store the elements into the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default LongBuffer storeRelative(@Mutated LongBuffer dest) {
        if (dest.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 4);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code long},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeLong(@Mutated ByteBuffer dest) { return storeLongAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code long},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeLongAbsolute(@Mutated ByteBuffer dest) { return storeLongAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code long},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @return dest
     */
    ByteBuffer storeLongAbsolute(int index, @Mutated ByteBuffer dest);

    /**
     * Store the elements into the given byte buffer, converting each element to {@code long},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeLongRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeLongAbsolute(pos, dest);
        dest.position(pos + 32);
        return dest;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code long}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    IntRect storeLongUnsafe(long address);

    /** {@return whether all components of this rectangle are finite} Integer components always are, so this always returns {@code true}. <p>Valid input: any value. */
    boolean isFinite();

    /** {@return whether any component of this rectangle is NaN} Integer components never are, so this always returns {@code false}. <p>Valid input: any value. */
    boolean isNaN();

    /**
     * Compare this rectangle component-wise against {@code other}, allowing a difference
     * of at most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with an exact, non-negative integer tolerance:
     * the difference is widened to {@code long} before its magnitude is taken, so the two are
     * compared exactly without overflow, and a negative {@code epsilon} matches nothing.
     * <p>
     * Valid input: any value.
     *
     * @param other the rectangle to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    boolean equalsEpsilon(IntRectR other, int epsilon);

    /**
     * Compare this rectangle with the given object for element-wise equality.
     * <p>
     * Each component is compared with {@code ==}. Use {@link #equalsEpsilon} for a tolerant
     * comparison.
     * <p>
     * Only instances of this library's implementation compare equal to each other; any other object
     * yields {@code false}.
     * <p>
     * Valid input: any value.
     *
     * @param obj the object to compare with
     * @return {@code true} if {@code obj} is a rectangle of this library with element-wise equal
     *        components, {@code false} otherwise
     */
    boolean equals(@org.jspecify.annotations.Nullable Object obj);

    /**
     * Compute a hash code consistent with {@link #equals}: it is derived from the components alone.
     * <p>
     * Valid input: any value.
     *
     * @return the hash code of this rectangle
     */
    int hashCode();

    /** Backs {@code ZERO()}: defers the shared instance's
     *  allocation to first use, avoiding a class-initialization cycle with the
     *  implementation class. Not part of the public API. */
    final class Holder {
        private Holder() {}
        static final IntRectR ZERO = Joml.intRect(0, 0, 0, 0);
    }
}
