// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

/**
 * Immutable rectangle of {@code int} components.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance.
 * <p>
 * {@code equals} compares the components element-wise with {@code ==}. {@code hashCode} is
 * consistent with it.
 * <p>
 * {@code equalsEpsilon} compares per component with an exact, non-negative integer tolerance: the
 * difference is widened to {@code long} before its magnitude is taken, so the two are compared
 * exactly without overflow, and a negative {@code epsilon} matches nothing.
 *
 * @param minX the {@code minX} component
 * @param minY the {@code minY} component
 * @param maxX the {@code maxX} component
 * @param maxY the {@code maxY} component
 */
public record IntRect(int minX, int minY, int maxX, int maxY) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int SIZE_BYTES = 16;

    /**
     * Canonical constructor.
     * <p>
     * Valid input: any value.
     *
     * @param minX the {@code minX} component
     * @param minY the {@code minY} component
     * @param maxX the {@code maxX} component
     * @param maxY the {@code maxY} component
     */
    public IntRect(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     * Create a new instance initialized to empty inverted bounds (so any union starts from the
     * first added geometry).
     * <p>
     * Valid input: any value.
     */
    public IntRect() {
        this(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    /**
     * Create a rectangle from its minimum and maximum corners.
     * <p>
     * Valid input: any value.
     *
     * @param min the minimum corner of the rectangle
     * @param max the maximum corner of the rectangle
     */
    public IntRect(Int2 min, Int2 max) {
        this(min.x(), min.y(), max.x(), max.y());
    }

    /** {@return the {@code minX} component} <p>Valid input: any value. */
    public int minX() { return minX; }
    /** {@return the {@code minY} component} <p>Valid input: any value. */
    public int minY() { return minY; }
    /** {@return the {@code maxX} component} <p>Valid input: any value. */
    public int maxX() { return maxX; }
    /** {@return the {@code maxY} component} <p>Valid input: any value. */
    public int maxY() { return maxY; }

    /**
     * Create a new rectangle from its minimum and maximum corners.
     * <p>
     * Valid input: any value.
     *
     * @param min the minimum corner of the rectangle
     * @param max the maximum corner of the rectangle
     * @return the resulting rectangle
     */
    public IntRect set(Int2 min, Int2 max) {
        return new IntRect(min, max);
    }


    /**
     * Add each bound of {@code other} to the corresponding bound of this rectangle, returning the
     * result as a value.
     * <p>
     * The bounds combine element-wise: each bound of the result is the sum of the corresponding
     * bounds. That is neither the Minkowski sum of the two rectangles nor a translation; to move a
     * rectangle, add the same offset to both of its corners.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to add
     * @return the resulting rectangle
     */
    public IntRect add(IntRect other) {
        return add(other.minX(), other.minY(), other.maxX(), other.maxY());
    }


    /**
     * Add each bound of ({@code otherMinX}, {@code otherMinY}, {@code otherMaxX},
     * {@code otherMaxY}) to the corresponding bound of this rectangle, returning the result as a
     * value.
     * <p>
     * The bounds combine element-wise: each bound of the result is the sum of the corresponding
     * bounds. That is neither the Minkowski sum of the two rectangles nor a translation; to move a
     * rectangle, add the same offset to both of its corners.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherMinX the {@code minX} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMinY the {@code minY} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMaxX the {@code maxX} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMaxY the {@code maxY} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @return the resulting rectangle
     */
    public IntRect add(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY) {
        return new IntRect(otherMinX + this.minX, otherMinY + this.minY, otherMaxX + this.maxX, otherMaxY + this.maxY);
    }


    /**
     * Reflect this rectangle through the origin, so that it spans {@code (-maxX, -maxY)} to
     * {@code (-minX, -minY)}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting rectangle
     */
    public IntRect negate() {
        return new IntRect(-this.maxX, -this.maxY, -this.minX, -this.minY);
    }


    /**
     * Subtract each bound of {@code other} from the corresponding bound of this rectangle,
     * returning the result as a value.
     * <p>
     * The bounds combine element-wise: each bound of the result is the difference of the
     * corresponding bounds. That is neither the Minkowski difference of the two rectangles nor a
     * translation; to move a rectangle, add the same offset to both of its corners.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to subtract
     * @return the resulting rectangle
     */
    public IntRect sub(IntRect other) {
        return sub(other.minX(), other.minY(), other.maxX(), other.maxY());
    }


    /**
     * Subtract each bound of ({@code otherMinX}, {@code otherMinY}, {@code otherMaxX},
     * {@code otherMaxY}) from the corresponding bound of this rectangle, returning the result as a
     * value.
     * <p>
     * The bounds combine element-wise: each bound of the result is the difference of the
     * corresponding bounds. That is neither the Minkowski difference of the two rectangles nor a
     * translation; to move a rectangle, add the same offset to both of its corners.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherMinX the {@code minX} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMinY the {@code minY} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMaxX the {@code maxX} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMaxY the {@code maxY} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @return the resulting rectangle
     */
    public IntRect sub(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY) {
        return new IntRect(this.minX - otherMinX, this.minY - otherMinY, this.maxX - otherMaxX, this.maxY - otherMaxY);
    }


    /**
     * Create a new rectangle from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the rectangle to copy
     * @return the resulting rectangle
     */
    public IntRect set(IntRect v) {
        return set(v.minX(), v.minY(), v.maxX(), v.maxY());
    }


    /**
     * Create a new rectangle from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vMinX the {@code minX} component of the rectangle {@code (vMinX, vMinY, vMaxX, vMaxY)}
     * @param vMinY the {@code minY} component of the rectangle {@code (vMinX, vMinY, vMaxX, vMaxY)}
     * @param vMaxX the {@code maxX} component of the rectangle {@code (vMinX, vMinY, vMaxX, vMaxY)}
     * @param vMaxY the {@code maxY} component of the rectangle {@code (vMinX, vMinY, vMaxX, vMaxY)}
     * @return the resulting rectangle
     */
    public IntRect set(int vMinX, int vMinY, int vMaxX, int vMaxY) {
        return new IntRect(vMinX, vMinY, vMaxX, vMaxY);
    }


    /**
     * Set the maximum corner of this rectangle to {@code max}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param max the maximum corner of the box
     * @return the resulting rectangle
     */
    public IntRect setMax(Int2 max) {
        return setMax(max.x(), max.y());
    }


    /**
     * Set the maximum corner of this rectangle to ({@code maxX}, {@code maxY}), returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY)}
     * @return the resulting rectangle
     */
    public IntRect setMax(int maxX, int maxY) {
        return new IntRect(this.minX, this.minY, maxX, maxY);
    }


    /**
     * Set the minimum corner of this rectangle to {@code min}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param min the minimum corner of the box
     * @return the resulting rectangle
     */
    public IntRect setMin(Int2 min) {
        return setMin(min.x(), min.y());
    }


    /**
     * Set the minimum corner of this rectangle to ({@code minX}, {@code minY}), returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY)}
     * @param minY the {@code y} component of the vector {@code (minX, minY)}
     * @return the resulting rectangle
     */
    public IntRect setMin(int minX, int minY) {
        return new IntRect(minX, minY, this.maxX, this.maxY);
    }


    /**
     * Convert this rectangle to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code FloatRect} holding the result
     */
    public FloatRect toFloat() {
        return new FloatRect(this.minX, this.minY, this.maxX, this.maxY);
    }


    /**
     * Convert this rectangle to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code DoubleRect} holding the result
     */
    public DoubleRect toDouble() {
        return new DoubleRect(this.minX, this.minY, this.maxX, this.maxY);
    }


    /**
     * Swap the minimum and maximum bounds of this rectangle where necessary so the bounds are
     * valid, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting rectangle
     */
    public IntRect correctBounds() {
        return new IntRect(Math.min(this.minX, this.maxX), Math.min(this.minY, this.maxY), Math.max(this.minX, this.maxX), Math.max(this.minY, this.maxY));
    }


    /**
     * Expand this rectangle by {@code margin} in every direction, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param margin the amount to expand by in every direction
     * @return the resulting rectangle
     */
    public IntRect expand(int margin) {
        return new IntRect(this.minX - margin, this.minY - margin, margin + this.maxX, margin + this.maxY);
    }


    /**
     * Set this rectangle to the intersection of itself and {@code other} (disjoint inputs yield
     * inverted bounds - check {@code isValid()}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to intersect with
     * @return the resulting rectangle
     */
    public IntRect intersect(IntRect other) {
        return intersect(other.minX(), other.minY(), other.maxX(), other.maxY());
    }


    /**
     * Set this rectangle to the intersection of itself and ({@code otherMinX}, {@code otherMinY},
     * {@code otherMaxX}, {@code otherMaxY}) (disjoint inputs yield inverted bounds - check
     * {@code isValid()}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherMinX the {@code minX} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMinY the {@code minY} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMaxX the {@code maxX} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMaxY the {@code maxY} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @return the resulting rectangle
     */
    public IntRect intersect(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY) {
        return new IntRect(Math.max(this.minX, otherMinX), Math.max(this.minY, otherMinY), Math.min(this.maxX, otherMaxX), Math.min(this.maxY, otherMaxY));
    }


    /**
     * Translate this rectangle by {@code delta}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @return the resulting rectangle
     */
    public IntRect translate(Int2 delta) {
        return translate(delta.x(), delta.y());
    }


    /**
     * Translate this rectangle by ({@code deltaX}, {@code deltaY}), returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY)}
     * @return the resulting rectangle
     */
    public IntRect translate(int deltaX, int deltaY) {
        return new IntRect(deltaX + this.minX, deltaY + this.minY, deltaX + this.maxX, deltaY + this.maxY);
    }


    /**
     * Set this rectangle to the union of itself and {@code other}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to include in the union
     * @return the resulting rectangle
     */
    public IntRect union(IntRect other) {
        return union(other.minX(), other.minY(), other.maxX(), other.maxY());
    }


    /**
     * Set this rectangle to the union of itself and ({@code otherMinX}, {@code otherMinY},
     * {@code otherMaxX}, {@code otherMaxY}), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherMinX the {@code minX} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMinY the {@code minY} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMaxX the {@code maxX} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMaxY the {@code maxY} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @return the resulting rectangle
     */
    public IntRect union(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY) {
        return new IntRect(Math.min(this.minX, otherMinX), Math.min(this.minY, otherMinY), Math.max(this.maxX, otherMaxX), Math.max(this.maxY, otherMaxY));
    }


    /**
     * Grow this rectangle to include the point {@code p}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to include
     * @return the resulting rectangle
     */
    public IntRect union(Int2 p) {
        return union(p.x(), p.y());
    }


    /**
     * Grow this rectangle to include the point ({@code pX}, {@code pY}), returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY)}
     * @param pY the {@code y} component of the vector {@code (pX, pY)}
     * @return the resulting rectangle
     */
    public IntRect union(int pX, int pY) {
        return new IntRect(Math.min(this.minX, pX), Math.min(this.minY, pY), Math.max(this.maxX, pX), Math.max(this.maxY, pY));
    }


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
    public long area() {
        return Math.max(0L, (long) this.maxX - (long) this.minX) * Math.max(0L, (long) this.maxY - (long) this.minY);
    }


    /**
     * Compute the x coordinate of the center of this rectangle (integer division truncates toward
     * zero).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the x coordinate of the center of this rectangle (integer division truncates toward
     *        zero)
     */
    public int centerX() {
        int _t1 = this.minX ^ this.maxX;
        int _t3 = (this.minX & this.maxX) + (_t1 >> 1);
        return _t3 < 0 ? _t3 + (_t1 & 1) : _t3;
    }


    /**
     * Compute the y coordinate of the center of this rectangle (integer division truncates toward
     * zero).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the y coordinate of the center of this rectangle (integer division truncates toward
     *        zero)
     */
    public int centerY() {
        int _t1 = this.minY ^ this.maxY;
        int _t3 = (this.minY & this.maxY) + (_t1 >> 1);
        return _t3 < 0 ? _t3 + (_t1 & 1) : _t3;
    }


    /**
     * Compute the point of this rectangle closest to the given point, i.e. the point clamped per
     * axis into the rectangle's bounds. For a point inside or on the rectangle, the result is the
     * point itself.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
     * <p>
     * Valid input: the minimum corner of this rectangle must not exceed the maximum corner of this
     * rectangle in any component.
     *
     * @param p the point to find the closest point to
     * @return the resulting vector
     */
    public Int2 closestPointToPoint(Int2 p) {
        return closestPointToPoint(p.x(), p.y());
    }


    /**
     * Compute the point of this rectangle closest to the given point, i.e. the point clamped per
     * axis into the rectangle's bounds. For a point inside or on the rectangle, the result is the
     * point itself.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
     * <p>
     * Valid input: the minimum corner of this rectangle must not exceed the maximum corner of this
     * rectangle in any component.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY)} to find the closest point to
     * @param pY the {@code y} component of the point {@code (pX, pY)} to find the closest point to
     * @return the resulting vector
     */
    public Int2 closestPointToPoint(int pX, int pY) {
        return new Int2(Math.max(this.minX, Math.min(pX, this.maxX)), Math.max(this.minY, Math.min(pY, this.maxY)));
    }


    /**
     * Determine whether this rectangle contains the given point (boundary inclusive).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to test
     * @return {@code true} if this rectangle contains the given point (boundary inclusive),
     *        {@code false} otherwise
     */
    public boolean containsPoint(Int2 p) {
        return containsPoint(p.x(), p.y());
    }


    /**
     * Determine whether this rectangle contains the given point (boundary inclusive).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY)}
     * @param pY the {@code y} component of the vector {@code (pX, pY)}
     * @return {@code true} if this rectangle contains the given point (boundary inclusive),
     *        {@code false} otherwise
     */
    public boolean containsPoint(int pX, int pY) {
        if (!(pX >= this.minX)) return false;
        if (!(pX <= this.maxX)) return false;
        if (!(pY >= this.minY)) return false;
        return pY <= this.maxY;
    }


    /**
     * Determine whether this rectangle completely contains {@code o}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the rectangle to test
     * @return {@code true} if this rectangle completely contains {@code o}, {@code false} otherwise
     */
    public boolean containsRect(IntRect o) {
        return containsRect(o.minX(), o.minY(), o.maxX(), o.maxY());
    }


    /**
     * Determine whether this rectangle completely contains ({@code oMinX}, {@code oMinY},
     * {@code oMaxX}, {@code oMaxY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param oMinX the {@code minX} component of the rectangle {@code (oMinX, oMinY, oMaxX, oMaxY)}
     * @param oMinY the {@code minY} component of the rectangle {@code (oMinX, oMinY, oMaxX, oMaxY)}
     * @param oMaxX the {@code maxX} component of the rectangle {@code (oMinX, oMinY, oMaxX, oMaxY)}
     * @param oMaxY the {@code maxY} component of the rectangle {@code (oMinX, oMinY, oMaxX, oMaxY)}
     * @return {@code true} if this rectangle completely contains ({@code oMinX}, {@code oMinY},
     *        {@code oMaxX}, {@code oMaxY}), {@code false} otherwise
     */
    public boolean containsRect(int oMinX, int oMinY, int oMaxX, int oMaxY) {
        if (!(this.minX <= oMinX)) return false;
        if (!(this.maxX >= oMaxX)) return false;
        if (!(this.minY <= oMinY)) return false;
        return this.maxY >= oMaxY;
    }


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
    public long distanceSquaredToPoint(Int2 p) {
        return distanceSquaredToPoint(p.x(), p.y());
    }


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
     * @param pX the {@code x} component of the point {@code (pX, pY)} to measure the distance to
     * @param pY the {@code y} component of the point {@code (pX, pY)} to measure the distance to
     * @return the squared distance between this rectangle and the given point, i.e. the squared
     *        length of the difference between the point and its per-axis clamp into the rectangle's
     *        bounds; zero for a point inside or on the rectangle; Long.MAX_VALUE if that exceeds
     *        the long range
     */
    public long distanceSquaredToPoint(int pX, int pY) {
        long _t4 = (long) pX - Math.max((long) this.minX, Math.min((long) pX, (long) this.maxX));
        long _t5 = (long) pY - Math.max((long) this.minY, Math.min((long) pY, (long) this.maxY));
        long _t8 = _t4 * _t4 + _t5 * _t5;
        return Math.max(Math.abs(_t4), Math.abs(_t5)) >= 3037000500L ? 9223372036854775807L : _t8 < 0L ? 9223372036854775807L : _t8;
    }


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
    public long distanceSquaredToRect(IntRect other) {
        return distanceSquaredToRect(other.minX(), other.minY(), other.maxX(), other.maxY());
    }


    /**
     * Compute the squared distance between this rectangle and the given rectangle, i.e. the squared
     * length of the shortest vector between any two points of the two rectangles; zero when they
     * overlap or touch.
     * <p>
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the minimum corner of this rectangle must not exceed the maximum corner of this
     * rectangle in any component; {@code (otherMinX, otherMinY)} must not exceed
     * {@code (otherMaxX, otherMaxY)} in any component.
     *
     * @param otherMinX the {@code minX} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)} to measure the distance to
     * @param otherMinY the {@code minY} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)} to measure the distance to
     * @param otherMaxX the {@code maxX} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)} to measure the distance to
     * @param otherMaxY the {@code maxY} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)} to measure the distance to
     * @return the squared distance between this rectangle and the given rectangle, i.e. the squared
     *        length of the shortest vector between any two points of the two rectangles; zero when
     *        they overlap or touch
     */
    public long distanceSquaredToRect(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY) {
        long _t6 = Math.max(0L, Math.max((long) this.minX - (long) otherMaxX, (long) otherMinX - (long) this.maxX));
        long _t7 = Math.max(0L, Math.max((long) this.minY - (long) otherMaxY, (long) otherMinY - (long) this.maxY));
        long _t10 = _t6 * _t6 + _t7 * _t7;
        return Math.max(Math.abs(_t6), Math.abs(_t7)) >= 3037000500L ? 9223372036854775807L : _t10 < 0L ? 9223372036854775807L : _t10;
    }


    /**
     * Get the center of this rectangle, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int2 getCenter() {
        int _t1 = this.minX ^ this.maxX;
        int _t3 = this.minY ^ this.maxY;
        int _t6 = (this.minX & this.maxX) + (_t1 >> 1);
        int _t7 = (this.minY & this.maxY) + (_t3 >> 1);
        return new Int2(_t6 < 0 ? _t6 + (_t1 & 1) : _t6, _t7 < 0 ? _t7 + (_t3 & 1) : _t7);
    }


    /**
     * Get the maximum corner of this rectangle, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int2 getMax() {
        return new Int2(this.maxX, this.maxY);
    }


    /**
     * Get the minimum corner of this rectangle, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int2 getMin() {
        return new Int2(this.minX, this.minY);
    }


    /**
     * Get the size, i.e. the maximum minus the minimum corner per axis of this rectangle, returning
     * the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Int2 getSize() {
        return new Int2(this.maxX - this.minX, this.maxY - this.minY);
    }


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
    public long height() {
        return (long) this.maxY - (long) this.minY;
    }


    /**
     * Determine whether this rectangle intersects {@code o}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the rectangle to test
     * @return {@code true} if this rectangle intersects {@code o}, {@code false} otherwise
     */
    public boolean intersectsRect(IntRect o) {
        return intersectsRect(o.minX(), o.minY(), o.maxX(), o.maxY());
    }


    /**
     * Determine whether this rectangle intersects ({@code oMinX}, {@code oMinY}, {@code oMaxX},
     * {@code oMaxY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param oMinX the {@code minX} component of the rectangle {@code (oMinX, oMinY, oMaxX, oMaxY)}
     * @param oMinY the {@code minY} component of the rectangle {@code (oMinX, oMinY, oMaxX, oMaxY)}
     * @param oMaxX the {@code maxX} component of the rectangle {@code (oMinX, oMinY, oMaxX, oMaxY)}
     * @param oMaxY the {@code maxY} component of the rectangle {@code (oMinX, oMinY, oMaxX, oMaxY)}
     * @return {@code true} if this rectangle intersects ({@code oMinX}, {@code oMinY},
     *        {@code oMaxX}, {@code oMaxY}), {@code false} otherwise
     */
    public boolean intersectsRect(int oMinX, int oMinY, int oMaxX, int oMaxY) {
        if (!(this.maxX >= oMinX)) return false;
        if (!(this.minX <= oMaxX)) return false;
        if (!(this.maxY >= oMinY)) return false;
        return this.minY <= oMaxY;
    }


    /**
     * Determine whether this rectangle is valid, i.e. no minimum bound exceeds its maximum.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return {@code true} if this rectangle is valid, i.e. no minimum bound exceeds its maximum,
     *        {@code false} otherwise
     */
    public boolean isValid() {
        if (!(this.minX <= this.maxX)) return false;
        return this.minY <= this.maxY;
    }


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
    public long width() {
        return (long) this.maxX - (long) this.minX;
    }

    /**
     * {@return a copy with the {@code minX} component replaced by {@code v}}
     * <p>
     * Valid input: any value.
     *
     * @param v the new value of the {@code minX} component
     */
    public IntRect withMinX(int v) {
        return new IntRect(v, minY, maxX, maxY);
    }

    /**
     * {@return a copy with the {@code minY} component replaced by {@code v}}
     * <p>
     * Valid input: any value.
     *
     * @param v the new value of the {@code minY} component
     */
    public IntRect withMinY(int v) {
        return new IntRect(minX, v, maxX, maxY);
    }

    /**
     * {@return a copy with the {@code maxX} component replaced by {@code v}}
     * <p>
     * Valid input: any value.
     *
     * @param v the new value of the {@code maxX} component
     */
    public IntRect withMaxX(int v) {
        return new IntRect(minX, minY, v, maxY);
    }

    /**
     * {@return a copy with the {@code maxY} component replaced by {@code v}}
     * <p>
     * Valid input: any value.
     *
     * @param v the new value of the {@code maxY} component
     */
    public IntRect withMaxY(int v) {
        return new IntRect(minX, minY, maxX, v);
    }

    @Override public String toString() {
        return "IntRect(" + minX() + ", " + minY() + ", " + maxX() + ", " + maxY() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IntRect)) return false;
        IntRect o = (IntRect) obj;
        return minX == o.minX
            && minY == o.minY
            && maxX == o.maxX
            && maxY == o.maxY;
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + minX;
        h = 31 * h + minY;
        h = 31 * h + maxX;
        h = 31 * h + maxY;
        return h;
    }

    /** {@return whether all components of this value are finite} Integer components always are, so this always returns {@code true}. <p>Valid input: any value. */
    public boolean isFinite() {
        return true;
    }

    /** {@return whether any component of this value is NaN} Integer components never are, so this always returns {@code false}. <p>Valid input: any value. */
    public boolean isNaN() {
        return false;
    }

    /**
     * Compare this value component-wise against {@code other}, allowing a difference of at
     * most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with an exact, non-negative integer tolerance:
     * the difference is widened to {@code long} before its magnitude is taken, so the two are
     * compared exactly without overflow, and a negative {@code epsilon} matches nothing.
     * <p>
     * Valid input: any value.
     *
     * @param other the value to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    public boolean equalsEpsilon(IntRect other, int epsilon) {
        return Math.abs((long) minX - (long) other.minX()) <= epsilon
            && Math.abs((long) minY - (long) other.minY()) <= epsilon
            && Math.abs((long) maxX - (long) other.maxX()) <= epsilon
            && Math.abs((long) maxY - (long) other.maxY()) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final IntRectBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new IntRectBbOpsUnsafe()
                        : new IntRectBbOpsApi();
        static final IntRectRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new IntRectRawOpsUnsafe()
                        : new IntRectRawOpsApi();
    }


    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public int[] store(int[] dest, int offset) {
        dest[offset + 0] = this.minX;
        dest[offset + 1] = this.minY;
        dest[offset + 2] = this.maxX;
        dest[offset + 3] = this.maxY;
        return dest;
    }

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @return dest
     */
    public int[] store(int[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code IntRect} holding the loaded elements
     */
    public static IntRect load(int[] src, int offset) {
        int _c0 = src[offset + 0];
        int _c1 = src[offset + 1];
        int _c2 = src[offset + 2];
        int _c3 = src[offset + 3];
        return new IntRect(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return a new {@code IntRect} holding the loaded elements
     */
    public static IntRect load(int[] src) { return load(src, 0); }

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
     * @param buf the destination buffer
     * @return buf
     */
    public IntBuffer store(IntBuffer buf) {
        return storeAbsolute(buf.position(), buf);
    }

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
     * @param buf the destination buffer
     * @return buf
     */
    public IntBuffer storeAbsolute(int index, IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

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
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public IntBuffer storeRelative(IntBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param buf the source buffer
     * @return a new {@code IntRect} holding the loaded elements
     */
    public static IntRect load(IntBuffer buf) {
        return loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
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
     * @param buf the source buffer
     * @return a new {@code IntRect} holding the loaded elements
     */
    public static IntRect loadAbsolute(int index, IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
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
     * @param buf the source buffer
     * @return a new {@code IntRect} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static IntRect loadRelative(IntBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        IntRect r = loadAbsolute(pos, buf);
        buf.position(pos + 4);
        return r;
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer store(ByteBuffer buf) {
        return storeAbsolute(buf.position(), buf);
    }

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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
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
     * @param buf the source byte buffer
     * @return a new {@code IntRect} holding the loaded elements
     */
    public static IntRect load(ByteBuffer buf) {
        return loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at the given absolute index (the
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
     * @param buf the source byte buffer
     * @return a new {@code IntRect} holding the loaded elements
     */
    public static IntRect loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position and advancing
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
     * @param buf the source byte buffer
     * @return a new {@code IntRect} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static IntRect loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        IntRect r = loadAbsolute(pos, buf);
        buf.position(pos + 16);
        return r;
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
    public IntRect storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return a new {@code IntRect} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static IntRect loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(address);
    }


    /**
     * Store the elements into the given array, converting each element to {@code long}, starting at
     * the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public long[] store(long[] dest, int offset) {
        dest[offset + 0] = this.minX;
        dest[offset + 1] = this.minY;
        dest[offset + 2] = this.maxX;
        dest[offset + 3] = this.maxY;
        return dest;
    }

    /**
     * Store the elements into the given array, converting each element to {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param dest the destination array
     * @return dest
     */
    public long[] store(long[] dest) { return store(dest, 0); }

    /**
     * Load the elements from the given array, converting each element from {@code long}, starting
     * at the given offset.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code IntRect} holding the loaded elements
     */
    public static IntRect load(long[] src, int offset) {
        int _c0 = (int) src[offset + 0];
        int _c1 = (int) src[offset + 1];
        int _c2 = (int) src[offset + 2];
        int _c3 = (int) src[offset + 3];
        return new IntRect(_c0, _c1, _c2, _c3);
    }

    /**
     * Load the elements from the given array, converting each element from {@code long}.
     * <p>
     * Valid input: any value.
     *
     * @param src the source array
     * @return a new {@code IntRect} holding the loaded elements
     */
    public static IntRect load(long[] src) { return load(src, 0); }

    /**
     * Store the elements into the given buffer, converting each element to {@code long}, starting
     * at its current position (the position is not modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public LongBuffer store(LongBuffer buf) {
        return storeAbsolute(buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code long}, starting
     * at the given absolute index (the position is not used or modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public LongBuffer storeAbsolute(int index, LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer, converting each element to {@code long}, starting
     * at its current position and advancing the position accordingly.
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
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public LongBuffer storeRelative(LongBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeAbsolute(pos, buf);
        buf.position(pos + 4);
        return buf;
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code long}, starting
     * at its current position (the position is not modified).
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
     * @param buf the source buffer
     * @return a new {@code IntRect} holding the loaded elements
     */
    public static IntRect load(LongBuffer buf) {
        return loadAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code long}, starting
     * at the given absolute index (the position is not used or modified).
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
     * @param buf the source buffer
     * @return a new {@code IntRect} holding the loaded elements
     */
    public static IntRect loadAbsolute(int index, LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer, converting each element from {@code long}, starting
     * at its current position and advancing the position accordingly.
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
     * @param buf the source buffer
     * @return a new {@code IntRect} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static IntRect loadRelative(LongBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        IntRect r = loadAbsolute(pos, buf);
        buf.position(pos + 4);
        return r;
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeLong(ByteBuffer buf) {
        return storeLongAbsolute(buf.position(), buf);
    }

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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeLongAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeLongAbsolute(this, index, buf);
    }

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
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeLongRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        storeLongAbsolute(pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code long},
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
     * @param buf the source byte buffer
     * @return a new {@code IntRect} holding the loaded elements
     */
    public static IntRect loadLong(ByteBuffer buf) {
        return loadLongAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code long},
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
     * @param buf the source byte buffer
     * @return a new {@code IntRect} holding the loaded elements
     */
    public static IntRect loadLongAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadLongAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code long},
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
     * @param buf the source byte buffer
     * @return a new {@code IntRect} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static IntRect loadLongRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        IntRect r = loadLongAbsolute(pos, buf);
        buf.position(pos + 32);
        return r;
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
    public IntRect storeLongUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeLongUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code long}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value.
     *
     * @param address the raw memory address
     * @return a new {@code IntRect} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static IntRect loadLongUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadLongUnsafe(address);
    }

}
