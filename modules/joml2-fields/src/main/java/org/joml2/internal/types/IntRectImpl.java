// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

/**
 * Generated implementation of {@link IntRect} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class IntRectImpl implements IntRect {

    public int minX;
    public int minY;
    public int maxX;
    public int maxY;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final IntRectSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new IntRectSegOpsUnsafe()
                        : new IntRectSegOpsMS();
        static final IntRectBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new IntRectBbOpsUnsafe()
                        : new IntRectBbOpsApi();
        static final IntRectRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new IntRectRawOpsUnsafe()
                        : new IntRectRawOpsApi();
    }

    public IntRectImpl() {
        minX = Integer.MAX_VALUE;
        minY = Integer.MAX_VALUE;
        maxX = Integer.MIN_VALUE;
        maxY = Integer.MIN_VALUE;
    }

    public IntRectImpl(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public IntRectImpl(IntRectR src) {
        this.minX = src.minX();
        this.minY = src.minY();
        this.maxX = src.maxX();
        this.maxY = src.maxY();
    }


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
    public IntRect add(IntRectR other, @Mutated IntRect dest) {
        int otherMinY = other.minY();
        int otherMaxX = other.maxX();
        int otherMaxY = other.maxY();
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = other.minX() + this.minX;
        d.minY = otherMinY + this.minY;
        d.maxX = otherMaxX + this.maxX;
        d.maxY = otherMaxY + this.maxY;
        return d;
    }


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
    public DoubleRect add(IntRectR other, @Mutated DoubleRect dest) {
        int otherMinY = other.minY();
        int otherMaxX = other.maxX();
        int otherMaxY = other.maxY();
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = other.minX() + this.minX;
        d.minY = otherMinY + this.minY;
        d.maxX = otherMaxX + this.maxX;
        d.maxY = otherMaxY + this.maxY;
        return d;
    }


    /**
     * Add each bound of ({@code otherMinX}, {@code otherMinY}, {@code otherMaxX},
     * {@code otherMaxY}) to the corresponding bound of this rectangle and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public IntRect add(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY, @Mutated IntRect dest) {
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = otherMinX + this.minX;
        d.minY = otherMinY + this.minY;
        d.maxX = otherMaxX + this.maxX;
        d.maxY = otherMaxY + this.maxY;
        return d;
    }


    /**
     * Add each bound of ({@code otherMinX}, {@code otherMinY}, {@code otherMaxX},
     * {@code otherMaxY}) to the corresponding bound of this rectangle and store the result in
     * {@code dest}.
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
     * @param otherMinX the {@code minX} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMinY the {@code minY} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMaxX the {@code maxX} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMaxY the {@code maxY} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect add(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY, @Mutated DoubleRect dest) {
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = otherMinX + this.minX;
        d.minY = otherMinY + this.minY;
        d.maxX = otherMaxX + this.maxX;
        d.maxY = otherMaxY + this.maxY;
        return d;
    }


    /**
     * Reflect this rectangle through the origin, so that it spans {@code (-maxX, -maxY)} to
     * {@code (-minX, -minY)} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public IntRect negate(@Mutated IntRect dest) {
        IntRectImpl d = (IntRectImpl) dest;
        int _rd0 = this.minX;
        int _rd1 = this.minY;
        d.minX = -this.maxX;
        d.minY = -this.maxY;
        d.maxX = -_rd0;
        d.maxY = -_rd1;
        return d;
    }


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
    public DoubleRect negate(@Mutated DoubleRect dest) {
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = -this.maxX;
        d.minY = -this.maxY;
        d.maxX = -this.minX;
        d.maxY = -this.minY;
        return d;
    }


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
    public IntRect sub(IntRectR other, @Mutated IntRect dest) {
        int otherMinY = other.minY();
        int otherMaxX = other.maxX();
        int otherMaxY = other.maxY();
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = this.minX - other.minX();
        d.minY = this.minY - otherMinY;
        d.maxX = this.maxX - otherMaxX;
        d.maxY = this.maxY - otherMaxY;
        return d;
    }


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
    public DoubleRect sub(IntRectR other, @Mutated DoubleRect dest) {
        int otherMinY = other.minY();
        int otherMaxX = other.maxX();
        int otherMaxY = other.maxY();
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = this.minX - other.minX();
        d.minY = this.minY - otherMinY;
        d.maxX = this.maxX - otherMaxX;
        d.maxY = this.maxY - otherMaxY;
        return d;
    }


    /**
     * Subtract each bound of ({@code otherMinX}, {@code otherMinY}, {@code otherMaxX},
     * {@code otherMaxY}) from the corresponding bound of this rectangle and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public IntRect sub(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY, @Mutated IntRect dest) {
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = this.minX - otherMinX;
        d.minY = this.minY - otherMinY;
        d.maxX = this.maxX - otherMaxX;
        d.maxY = this.maxY - otherMaxY;
        return d;
    }


    /**
     * Subtract each bound of ({@code otherMinX}, {@code otherMinY}, {@code otherMaxX},
     * {@code otherMaxY}) from the corresponding bound of this rectangle and store the result in
     * {@code dest}.
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
     * @param otherMinX the {@code minX} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMinY the {@code minY} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMaxX the {@code maxX} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param otherMaxY the {@code maxY} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect sub(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY, @Mutated DoubleRect dest) {
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = this.minX - otherMinX;
        d.minY = this.minY - otherMinY;
        d.maxX = this.maxX - otherMaxX;
        d.maxY = this.maxY - otherMaxY;
        return d;
    }


    /**
     * Set this rectangle to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the rectangle to copy
     * @return this
     */
    public @Mutated IntRect set(IntRectR v) {
        int vMinY = v.minY();
        int vMaxX = v.maxX();
        int vMaxY = v.maxY();
        this.minX = v.minX();
        this.minY = vMinY;
        this.maxX = vMaxX;
        this.maxY = vMaxY;
        return this;
    }


    /**
     * Set this rectangle to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vMinX the {@code minX} component of the rectangle {@code (vMinX, vMinY, vMaxX, vMaxY)}
     * @param vMinY the {@code minY} component of the rectangle {@code (vMinX, vMinY, vMaxX, vMaxY)}
     * @param vMaxX the {@code maxX} component of the rectangle {@code (vMinX, vMinY, vMaxX, vMaxY)}
     * @param vMaxY the {@code maxY} component of the rectangle {@code (vMinX, vMinY, vMaxX, vMaxY)}
     * @return this
     */
    @Mutated public IntRect set(int vMinX, int vMinY, int vMaxX, int vMaxY) {
        this.minX = vMinX;
        this.minY = vMinY;
        this.maxX = vMaxX;
        this.maxY = vMaxY;
        return this;
    }


    /**
     * Set the maximum corner of this rectangle to {@code max} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param max the maximum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    public IntRect setMax(Int2R max, @Mutated IntRect dest) {
        int maxX = max.x();
        int maxY = max.y();
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = this.minX;
        d.minY = this.minY;
        d.maxX = maxX;
        d.maxY = maxY;
        return d;
    }


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
    public DoubleRect setMax(Int2R max, @Mutated DoubleRect dest) {
        int maxX = max.x();
        int maxY = max.y();
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = this.minX;
        d.minY = this.minY;
        d.maxX = maxX;
        d.maxY = maxY;
        return d;
    }


    /**
     * Set the maximum corner of this rectangle to ({@code maxX}, {@code maxY}) and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY)}
     * @param dest will hold the result
     * @return dest
     */
    public IntRect setMax(int maxX, int maxY, @Mutated IntRect dest) {
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = this.minX;
        d.minY = this.minY;
        d.maxX = maxX;
        d.maxY = maxY;
        return d;
    }


    /**
     * Set the maximum corner of this rectangle to ({@code maxX}, {@code maxY}) and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect setMax(int maxX, int maxY, @Mutated DoubleRect dest) {
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = this.minX;
        d.minY = this.minY;
        d.maxX = maxX;
        d.maxY = maxY;
        return d;
    }


    /**
     * Set the minimum corner of this rectangle to {@code min} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param min the minimum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    public IntRect setMin(Int2R min, @Mutated IntRect dest) {
        int minY = min.y();
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = min.x();
        d.minY = minY;
        d.maxX = this.maxX;
        d.maxY = this.maxY;
        return d;
    }


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
    public DoubleRect setMin(Int2R min, @Mutated DoubleRect dest) {
        int minY = min.y();
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = min.x();
        d.minY = minY;
        d.maxX = this.maxX;
        d.maxY = this.maxY;
        return d;
    }


    /**
     * Set the minimum corner of this rectangle to ({@code minX}, {@code minY}) and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY)}
     * @param minY the {@code y} component of the vector {@code (minX, minY)}
     * @param dest will hold the result
     * @return dest
     */
    public IntRect setMin(int minX, int minY, @Mutated IntRect dest) {
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = minX;
        d.minY = minY;
        d.maxX = this.maxX;
        d.maxY = this.maxY;
        return d;
    }


    /**
     * Set the minimum corner of this rectangle to ({@code minX}, {@code minY}) and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY)}
     * @param minY the {@code y} component of the vector {@code (minX, minY)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect setMin(int minX, int minY, @Mutated DoubleRect dest) {
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = minX;
        d.minY = minY;
        d.maxX = this.maxX;
        d.maxY = this.maxY;
        return d;
    }


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
    public FloatRect toFloat(@Mutated FloatRect dest) {
        FloatRectImpl d = (FloatRectImpl) dest;
        d.minX = this.minX;
        d.minY = this.minY;
        d.maxX = this.maxX;
        d.maxY = this.maxY;
        return d;
    }


    /**
     * Convert this rectangle to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect toDouble(@Mutated DoubleRect dest) {
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = this.minX;
        d.minY = this.minY;
        d.maxX = this.maxX;
        d.maxY = this.maxY;
        return d;
    }


    /**
     * Swap the minimum and maximum bounds of this rectangle where necessary so the bounds are valid
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public IntRect correctBounds(@Mutated IntRect dest) {
        IntRectImpl d = (IntRectImpl) dest;
        int _rd0 = this.minX;
        int _rd1 = this.minY;
        d.minX = java.lang.Math.min(_rd0, this.maxX);
        d.minY = java.lang.Math.min(_rd1, this.maxY);
        d.maxX = java.lang.Math.max(_rd0, this.maxX);
        d.maxY = java.lang.Math.max(_rd1, this.maxY);
        return d;
    }


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
    public DoubleRect correctBounds(@Mutated DoubleRect dest) {
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = java.lang.Math.min(this.minX, this.maxX);
        d.minY = java.lang.Math.min(this.minY, this.maxY);
        d.maxX = java.lang.Math.max(this.minX, this.maxX);
        d.maxY = java.lang.Math.max(this.minY, this.maxY);
        return d;
    }


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
    public IntRect expand(int margin, @Mutated IntRect dest) {
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = this.minX - margin;
        d.minY = this.minY - margin;
        d.maxX = margin + this.maxX;
        d.maxY = margin + this.maxY;
        return d;
    }


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
    public DoubleRect expand(int margin, @Mutated DoubleRect dest) {
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = this.minX - margin;
        d.minY = this.minY - margin;
        d.maxX = margin + this.maxX;
        d.maxY = margin + this.maxY;
        return d;
    }


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
    public IntRect intersect(IntRectR other, @Mutated IntRect dest) {
        int otherMinY = other.minY();
        int otherMaxX = other.maxX();
        int otherMaxY = other.maxY();
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = java.lang.Math.max(this.minX, other.minX());
        d.minY = java.lang.Math.max(this.minY, otherMinY);
        d.maxX = java.lang.Math.min(this.maxX, otherMaxX);
        d.maxY = java.lang.Math.min(this.maxY, otherMaxY);
        return d;
    }


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
    public DoubleRect intersect(IntRectR other, @Mutated DoubleRect dest) {
        int otherMinY = other.minY();
        int otherMaxX = other.maxX();
        int otherMaxY = other.maxY();
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = java.lang.Math.max(this.minX, other.minX());
        d.minY = java.lang.Math.max(this.minY, otherMinY);
        d.maxX = java.lang.Math.min(this.maxX, otherMaxX);
        d.maxY = java.lang.Math.min(this.maxY, otherMaxY);
        return d;
    }


    /**
     * Set this rectangle to the intersection of itself and ({@code otherMinX}, {@code otherMinY},
     * {@code otherMaxX}, {@code otherMaxY}) (disjoint inputs yield inverted bounds - check
     * {@code isValid()}) and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public IntRect intersect(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY, @Mutated IntRect dest) {
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = java.lang.Math.max(this.minX, otherMinX);
        d.minY = java.lang.Math.max(this.minY, otherMinY);
        d.maxX = java.lang.Math.min(this.maxX, otherMaxX);
        d.maxY = java.lang.Math.min(this.maxY, otherMaxY);
        return d;
    }


    /**
     * Set this rectangle to the intersection of itself and ({@code otherMinX}, {@code otherMinY},
     * {@code otherMaxX}, {@code otherMaxY}) (disjoint inputs yield inverted bounds - check
     * {@code isValid()}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect intersect(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY, @Mutated DoubleRect dest) {
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = java.lang.Math.max(this.minX, otherMinX);
        d.minY = java.lang.Math.max(this.minY, otherMinY);
        d.maxX = java.lang.Math.min(this.maxX, otherMaxX);
        d.maxY = java.lang.Math.min(this.maxY, otherMaxY);
        return d;
    }


    /**
     * Translate this rectangle by {@code delta} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public IntRect translate(Int2R delta, @Mutated IntRect dest) {
        int deltaX = delta.x();
        int deltaY = delta.y();
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = deltaX + this.minX;
        d.minY = deltaY + this.minY;
        d.maxX = deltaX + this.maxX;
        d.maxY = deltaY + this.maxY;
        return d;
    }


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
    public DoubleRect translate(Int2R delta, @Mutated DoubleRect dest) {
        int deltaX = delta.x();
        int deltaY = delta.y();
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = deltaX + this.minX;
        d.minY = deltaY + this.minY;
        d.maxX = deltaX + this.maxX;
        d.maxY = deltaY + this.maxY;
        return d;
    }


    /**
     * Translate this rectangle by ({@code deltaX}, {@code deltaY}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY)}
     * @param dest will hold the result
     * @return dest
     */
    public IntRect translate(int deltaX, int deltaY, @Mutated IntRect dest) {
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = deltaX + this.minX;
        d.minY = deltaY + this.minY;
        d.maxX = deltaX + this.maxX;
        d.maxY = deltaY + this.maxY;
        return d;
    }


    /**
     * Translate this rectangle by ({@code deltaX}, {@code deltaY}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect translate(int deltaX, int deltaY, @Mutated DoubleRect dest) {
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = deltaX + this.minX;
        d.minY = deltaY + this.minY;
        d.maxX = deltaX + this.maxX;
        d.maxY = deltaY + this.maxY;
        return d;
    }


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
    public IntRect union(IntRectR other, @Mutated IntRect dest) {
        int otherMinY = other.minY();
        int otherMaxX = other.maxX();
        int otherMaxY = other.maxY();
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = java.lang.Math.min(this.minX, other.minX());
        d.minY = java.lang.Math.min(this.minY, otherMinY);
        d.maxX = java.lang.Math.max(this.maxX, otherMaxX);
        d.maxY = java.lang.Math.max(this.maxY, otherMaxY);
        return d;
    }


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
    public DoubleRect union(IntRectR other, @Mutated DoubleRect dest) {
        int otherMinY = other.minY();
        int otherMaxX = other.maxX();
        int otherMaxY = other.maxY();
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = java.lang.Math.min(this.minX, other.minX());
        d.minY = java.lang.Math.min(this.minY, otherMinY);
        d.maxX = java.lang.Math.max(this.maxX, otherMaxX);
        d.maxY = java.lang.Math.max(this.maxY, otherMaxY);
        return d;
    }


    /**
     * Set this rectangle to the union of itself and ({@code otherMinX}, {@code otherMinY},
     * {@code otherMaxX}, {@code otherMaxY}) and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    public IntRect union(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY, @Mutated IntRect dest) {
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = java.lang.Math.min(this.minX, otherMinX);
        d.minY = java.lang.Math.min(this.minY, otherMinY);
        d.maxX = java.lang.Math.max(this.maxX, otherMaxX);
        d.maxY = java.lang.Math.max(this.maxY, otherMaxY);
        return d;
    }


    /**
     * Set this rectangle to the union of itself and ({@code otherMinX}, {@code otherMinY},
     * {@code otherMaxX}, {@code otherMaxY}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
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
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect union(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY, @Mutated DoubleRect dest) {
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = java.lang.Math.min(this.minX, otherMinX);
        d.minY = java.lang.Math.min(this.minY, otherMinY);
        d.maxX = java.lang.Math.max(this.maxX, otherMaxX);
        d.maxY = java.lang.Math.max(this.maxY, otherMaxY);
        return d;
    }


    /**
     * Grow this rectangle to include the point {@code p} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to include
     * @param dest will hold the result
     * @return dest
     */
    public IntRect union(Int2R p, @Mutated IntRect dest) {
        int pX = p.x();
        int pY = p.y();
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = java.lang.Math.min(this.minX, pX);
        d.minY = java.lang.Math.min(this.minY, pY);
        d.maxX = java.lang.Math.max(this.maxX, pX);
        d.maxY = java.lang.Math.max(this.maxY, pY);
        return d;
    }


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
    public DoubleRect union(Int2R p, @Mutated DoubleRect dest) {
        int pX = p.x();
        int pY = p.y();
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = java.lang.Math.min(this.minX, pX);
        d.minY = java.lang.Math.min(this.minY, pY);
        d.maxX = java.lang.Math.max(this.maxX, pX);
        d.maxY = java.lang.Math.max(this.maxY, pY);
        return d;
    }


    /**
     * Grow this rectangle to include the point ({@code pX}, {@code pY}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY)}
     * @param pY the {@code y} component of the vector {@code (pX, pY)}
     * @param dest will hold the result
     * @return dest
     */
    public IntRect union(int pX, int pY, @Mutated IntRect dest) {
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = java.lang.Math.min(this.minX, pX);
        d.minY = java.lang.Math.min(this.minY, pY);
        d.maxX = java.lang.Math.max(this.maxX, pX);
        d.maxY = java.lang.Math.max(this.maxY, pY);
        return d;
    }


    /**
     * Grow this rectangle to include the point ({@code pX}, {@code pY}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY)}
     * @param pY the {@code y} component of the vector {@code (pX, pY)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect union(int pX, int pY, @Mutated DoubleRect dest) {
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = java.lang.Math.min(this.minX, pX);
        d.minY = java.lang.Math.min(this.minY, pY);
        d.maxX = java.lang.Math.max(this.maxX, pX);
        d.maxY = java.lang.Math.max(this.maxY, pY);
        return d;
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
        return java.lang.Math.max(0L, (long) this.maxX - this.minX) * java.lang.Math.max(0L, (long) this.maxY - this.minY);
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
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: the minimum corner of this rectangle must not exceed the maximum corner of this
     * rectangle in any component.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Int2 closestPointToPoint(Int2R p, @Mutated Int2 dest) {
        int pY = p.y();
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.max(this.minX, java.lang.Math.min(p.x(), this.maxX));
        d.y = java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        return d;
    }


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
    public Long2 closestPointToPoint(Int2R p, @Mutated Long2 dest) {
        int pY = p.y();
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.max(this.minX, java.lang.Math.min(p.x(), this.maxX));
        d.y = java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        return d;
    }


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
    public Double2 closestPointToPoint(Int2R p, @Mutated Double2 dest) {
        int pY = p.y();
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.max(this.minX, java.lang.Math.min(p.x(), this.maxX));
        d.y = java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        return d;
    }


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
     * @param pX the {@code x} component of the point {@code (pX, pY)} to find the closest point to
     * @param pY the {@code y} component of the point {@code (pX, pY)} to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Int2 closestPointToPoint(int pX, int pY, @Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
        d.y = java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        return d;
    }


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
     * @param pX the {@code x} component of the point {@code (pX, pY)} to find the closest point to
     * @param pY the {@code y} component of the point {@code (pX, pY)} to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Long2 closestPointToPoint(int pX, int pY, @Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
        d.y = java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        return d;
    }


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
     * @param pX the {@code x} component of the point {@code (pX, pY)} to find the closest point to
     * @param pY the {@code y} component of the point {@code (pX, pY)} to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Double2 closestPointToPoint(int pX, int pY, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
        d.y = java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
        return d;
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
    public boolean containsPoint(Int2R p) {
        int pX = p.x();
        int pY = p.y();
        if (!(pX >= this.minX)) return false;
        if (!(pX <= this.maxX)) return false;
        if (!(pY >= this.minY)) return false;
        return pY <= this.maxY;
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
    public boolean containsRect(IntRectR o) {
        if (!(this.minX <= o.minX())) return false;
        if (!(this.maxX >= o.maxX())) return false;
        if (!(this.minY <= o.minY())) return false;
        return this.maxY >= o.maxY();
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
    public long distanceSquaredToPoint(Int2R p) {
        int pX = p.x();
        int pY = p.y();
        long _t4 = (long) pX - java.lang.Math.max((long) this.minX, java.lang.Math.min((long) pX, (long) this.maxX));
        long _t5 = (long) pY - java.lang.Math.max((long) this.minY, java.lang.Math.min((long) pY, (long) this.maxY));
        long _t8 = _t4 * _t4 + _t5 * _t5;
        return java.lang.Math.max(java.lang.Math.abs(_t4), java.lang.Math.abs(_t5)) >= 3037000500L ? 9223372036854775807L : _t8 < 0L ? 9223372036854775807L : _t8;
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
        long _t4 = (long) pX - java.lang.Math.max((long) this.minX, java.lang.Math.min((long) pX, (long) this.maxX));
        long _t5 = (long) pY - java.lang.Math.max((long) this.minY, java.lang.Math.min((long) pY, (long) this.maxY));
        long _t8 = _t4 * _t4 + _t5 * _t5;
        return java.lang.Math.max(java.lang.Math.abs(_t4), java.lang.Math.abs(_t5)) >= 3037000500L ? 9223372036854775807L : _t8 < 0L ? 9223372036854775807L : _t8;
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
    public long distanceSquaredToRect(IntRectR other) {
        long _t6 = java.lang.Math.max(0L, java.lang.Math.max((long) this.minX - other.maxX(), (long) other.minX() - this.maxX));
        long _t7 = java.lang.Math.max(0L, java.lang.Math.max((long) this.minY - other.maxY(), (long) other.minY() - this.maxY));
        long _t10 = _t6 * _t6 + _t7 * _t7;
        return java.lang.Math.max(java.lang.Math.abs(_t6), java.lang.Math.abs(_t7)) >= 3037000500L ? 9223372036854775807L : _t10 < 0L ? 9223372036854775807L : _t10;
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
        long _t6 = java.lang.Math.max(0L, java.lang.Math.max((long) this.minX - otherMaxX, (long) otherMinX - this.maxX));
        long _t7 = java.lang.Math.max(0L, java.lang.Math.max((long) this.minY - otherMaxY, (long) otherMinY - this.maxY));
        long _t10 = _t6 * _t6 + _t7 * _t7;
        return java.lang.Math.max(java.lang.Math.abs(_t6), java.lang.Math.abs(_t7)) >= 3037000500L ? 9223372036854775807L : _t10 < 0L ? 9223372036854775807L : _t10;
    }


    /**
     * Get the center of this rectangle and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int2 getCenter(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        int _t1 = this.minX ^ this.maxX;
        int _t3 = this.minY ^ this.maxY;
        int _t6 = (this.minX & this.maxX) + (_t1 >> 1);
        int _t7 = (this.minY & this.maxY) + (_t3 >> 1);
        d.x = _t6 < 0 ? _t6 + (_t1 & 1) : _t6;
        d.y = _t7 < 0 ? _t7 + (_t3 & 1) : _t7;
        return d;
    }


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
    public Long2 getCenter(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        int _t1 = this.minX ^ this.maxX;
        int _t3 = this.minY ^ this.maxY;
        int _t6 = (this.minX & this.maxX) + (_t1 >> 1);
        int _t7 = (this.minY & this.maxY) + (_t3 >> 1);
        d.x = _t6 < 0 ? _t6 + (_t1 & 1) : _t6;
        d.y = _t7 < 0 ? _t7 + (_t3 & 1) : _t7;
        return d;
    }


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
    public Double2 getCenter(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        int _t1 = this.minX ^ this.maxX;
        int _t3 = this.minY ^ this.maxY;
        int _t6 = (this.minX & this.maxX) + (_t1 >> 1);
        int _t7 = (this.minY & this.maxY) + (_t3 >> 1);
        d.x = _t6 < 0 ? _t6 + (_t1 & 1) : _t6;
        d.y = _t7 < 0 ? _t7 + (_t3 & 1) : _t7;
        return d;
    }


    /**
     * Get the maximum corner of this rectangle and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int2 getMax(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = this.maxX;
        d.y = this.maxY;
        return d;
    }


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
    public Long2 getMax(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.maxX;
        d.y = this.maxY;
        return d;
    }


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
    public Double2 getMax(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = this.maxX;
        d.y = this.maxY;
        return d;
    }


    /**
     * Get the minimum corner of this rectangle and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int2 getMin(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = this.minX;
        d.y = this.minY;
        return d;
    }


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
    public Long2 getMin(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.minX;
        d.y = this.minY;
        return d;
    }


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
    public Double2 getMin(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = this.minX;
        d.y = this.minY;
        return d;
    }


    /**
     * Get the size, i.e. the maximum minus the minimum corner per axis of this rectangle and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Int2 getSize(@Mutated Int2 dest) {
        Int2Impl d = (Int2Impl) dest;
        d.x = this.maxX - this.minX;
        d.y = this.maxY - this.minY;
        return d;
    }


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
    public Long2 getSize(@Mutated Long2 dest) {
        Long2Impl d = (Long2Impl) dest;
        d.x = this.maxX - this.minX;
        d.y = this.maxY - this.minY;
        return d;
    }


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
    public Double2 getSize(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = this.maxX - this.minX;
        d.y = this.maxY - this.minY;
        return d;
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
        return (long) this.maxY - this.minY;
    }


    /**
     * Determine whether this rectangle intersects {@code o}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the rectangle to test
     * @return {@code true} if this rectangle intersects {@code o}, {@code false} otherwise
     */
    public boolean intersectsRect(IntRectR o) {
        if (!(this.maxX >= o.minX())) return false;
        if (!(this.minX <= o.maxX())) return false;
        if (!(this.maxY >= o.minY())) return false;
        return this.minY <= o.maxY();
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
        return (long) this.maxX - this.minX;
    }

    public int minX() { return this.minX; }
    public int minY() { return this.minY; }
    public int maxX() { return this.maxX; }
    public int maxY() { return this.maxY; }

    @Override public String toString() {
        return "IntRect(" + minX() + ", " + minY() + ", " + maxX() + ", " + maxY() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IntRectImpl)) return false;
        IntRectImpl o = (IntRectImpl) obj;
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

    @Override public boolean isFinite() {
        return true;
    }

    @Override public boolean isNaN() {
        return false;
    }

    @Override public boolean equalsEpsilon(IntRectR other, int epsilon) {
        return java.lang.Math.abs((long) minX - other.minX()) <= epsilon
            && java.lang.Math.abs((long) minY - other.minY()) <= epsilon
            && java.lang.Math.abs((long) maxX - other.maxX()) <= epsilon
            && java.lang.Math.abs((long) maxY - other.maxY()) <= epsilon;
    }

    public int[] store(@Mutated int[] dest, int offset) {
        dest[offset] = this.minX;
        dest[offset + 1] = this.minY;
        dest[offset + 2] = this.maxX;
        dest[offset + 3] = this.maxY;
        return dest;
    }
    public @Mutated IntRect load(int[] src, int offset) {
        this.minX = src[offset];
        this.minY = src[offset + 1];
        this.maxX = src[offset + 2];
        this.maxY = src[offset + 3];
        return this;
    }
    public IntBuffer store(@Mutated IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public IntBuffer storeAbsolute(int index, @Mutated IntBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public IntBuffer storeRelative(@Mutated IntBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }
    @Mutated public IntRect load(IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public IntRect loadAbsolute(int index, IntBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public IntRect loadRelative(IntBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }
    public IntRect load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public IntRect loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public IntRect loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        IntRect r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return r;
    }
    public IntRect storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public IntRect loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public IntRect load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public IntRect load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public long[] store(@Mutated long[] dest, int offset) {
        dest[offset] = this.minX;
        dest[offset + 1] = this.minY;
        dest[offset + 2] = this.maxX;
        dest[offset + 3] = this.maxY;
        return dest;
    }
    public @Mutated IntRect load(long[] src, int offset) {
        this.minX = (int) src[offset];
        this.minY = (int) src[offset + 1];
        this.maxX = (int) src[offset + 2];
        this.maxY = (int) src[offset + 3];
        return this;
    }
    public LongBuffer store(@Mutated LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public LongBuffer storeAbsolute(int index, @Mutated LongBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public LongBuffer storeRelative(@Mutated LongBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }
    @Mutated public IntRect load(LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public IntRect loadAbsolute(int index, LongBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public IntRect loadRelative(LongBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }
    public ByteBuffer storeLong(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeLongAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeLongAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeLongAbsolute(this, index, buf);
    }
    public ByteBuffer storeLongRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeLongAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }
    public IntRect loadLong(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadLongAbsolute(this, buf.position(), buf);
    }
    public IntRect loadLongAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadLongAbsolute(this, index, buf);
    }
    public IntRect loadLongRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        IntRect r = StoreLoad.BB_OPS.loadLongAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return r;
    }
    public IntRect storeLongUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeLongUnsafe(this, address);
    }
    @Mutated public IntRect loadLongUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadLongUnsafe(this, address);
    }
    public MemorySegment storeLong(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeLong(this, 0L, dest); }
    public MemorySegment storeLong(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeLong(this, offset, dest);
    }
    @Mutated public IntRect loadLong(MemorySegment src) { return StoreLoad.SEG_OPS.loadLong(this, 0L, src); }
    public IntRect loadLong(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadLong(this, offset, src);
    }
}
