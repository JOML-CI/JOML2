// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link FloatRect} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatRectImpl implements FloatRect {

    public float minX;
    public float minY;
    public float maxX;
    public float maxY;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatRectSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatRectSegOpsUnsafe()
                        : new FloatRectSegOpsMS();
        static final FloatRectBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatRectBbOpsUnsafe()
                        : new FloatRectBbOpsApi();
        static final FloatRectRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new FloatRectRawOpsUnsafe()
                        : new FloatRectRawOpsApi();
    }

    public FloatRectImpl() {
        minX = Float.POSITIVE_INFINITY;
        minY = Float.POSITIVE_INFINITY;
        maxX = Float.NEGATIVE_INFINITY;
        maxY = Float.NEGATIVE_INFINITY;
    }

    public FloatRectImpl(float minX, float minY, float maxX, float maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public FloatRectImpl(FloatRectR src) {
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
    public FloatRect add(FloatRectR other, @Mutated FloatRect dest) {
        float otherMinY = other.minY();
        float otherMaxX = other.maxX();
        float otherMaxY = other.maxY();
        FloatRectImpl d = (FloatRectImpl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to add
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect add(FloatRectR other, @Mutated DoubleRect dest) {
        float otherMinY = other.minY();
        float otherMaxX = other.maxX();
        float otherMaxY = other.maxY();
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
    public FloatRect add(float otherMinX, float otherMinY, float otherMaxX, float otherMaxY, @Mutated FloatRect dest) {
        FloatRectImpl d = (FloatRectImpl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
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
    public DoubleRect add(float otherMinX, float otherMinY, float otherMaxX, float otherMaxY, @Mutated DoubleRect dest) {
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
    public FloatRect negate(@Mutated FloatRect dest) {
        FloatRectImpl d = (FloatRectImpl) dest;
        float _rd0 = this.minX;
        float _rd1 = this.minY;
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
     * The computation is performed at {@code float} precision; each result component is widened to
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
    public FloatRect sub(FloatRectR other, @Mutated FloatRect dest) {
        float otherMinY = other.minY();
        float otherMaxX = other.maxX();
        float otherMaxY = other.maxY();
        FloatRectImpl d = (FloatRectImpl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to subtract
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect sub(FloatRectR other, @Mutated DoubleRect dest) {
        float otherMinY = other.minY();
        float otherMaxX = other.maxX();
        float otherMaxY = other.maxY();
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
    public FloatRect sub(float otherMinX, float otherMinY, float otherMaxX, float otherMaxY, @Mutated FloatRect dest) {
        FloatRectImpl d = (FloatRectImpl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
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
    public DoubleRect sub(float otherMinX, float otherMinY, float otherMaxX, float otherMaxY, @Mutated DoubleRect dest) {
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
    public @Mutated FloatRect set(FloatRectR v) {
        float vMinY = v.minY();
        float vMaxX = v.maxX();
        float vMaxY = v.maxY();
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
    @Mutated public FloatRect set(float vMinX, float vMinY, float vMaxX, float vMaxY) {
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
    public FloatRect setMax(Float2R max, @Mutated FloatRect dest) {
        float maxX = max.x();
        float maxY = max.y();
        FloatRectImpl d = (FloatRectImpl) dest;
        d.minX = this.minX;
        d.minY = this.minY;
        d.maxX = maxX;
        d.maxY = maxY;
        return d;
    }


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
    public DoubleRect setMax(Float2R max, @Mutated DoubleRect dest) {
        float maxX = max.x();
        float maxY = max.y();
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
    public FloatRect setMax(float maxX, float maxY, @Mutated FloatRect dest) {
        FloatRectImpl d = (FloatRectImpl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect setMax(float maxX, float maxY, @Mutated DoubleRect dest) {
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
    public FloatRect setMin(Float2R min, @Mutated FloatRect dest) {
        float minY = min.y();
        FloatRectImpl d = (FloatRectImpl) dest;
        d.minX = min.x();
        d.minY = minY;
        d.maxX = this.maxX;
        d.maxY = this.maxY;
        return d;
    }


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
    public DoubleRect setMin(Float2R min, @Mutated DoubleRect dest) {
        float minY = min.y();
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
    public FloatRect setMin(float minX, float minY, @Mutated FloatRect dest) {
        FloatRectImpl d = (FloatRectImpl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY)}
     * @param minY the {@code y} component of the vector {@code (minX, minY)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect setMin(float minX, float minY, @Mutated DoubleRect dest) {
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = minX;
        d.minY = minY;
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
     * Convert this rectangle to {@code int} precision and store the result in {@code dest}.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public IntRect toInt(@Mutated IntRect dest) {
        IntRectImpl d = (IntRectImpl) dest;
        d.minX = (int) (this.minX);
        d.minY = (int) (this.minY);
        d.maxX = (int) (this.maxX);
        d.maxY = (int) (this.maxY);
        return d;
    }


    /**
     * Convert this rectangle to {@code int} precision and store the result in {@code dest}.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param roundingMode the rounding mode to use
     * @param dest will hold the result
     * @return dest
     */
    public IntRect toInt(RoundingMode roundingMode, @Mutated IntRect dest) {
        IntRectImpl d = (IntRectImpl) dest;
        switch (roundingMode) {
            case TRUNCATE -> { return toInt(dest); }
            case FLOOR -> {
                d.minX = (int) Math.floor(this.minX);
                d.minY = (int) Math.floor(this.minY);
                d.maxX = (int) Math.floor(this.maxX);
                d.maxY = (int) Math.floor(this.maxY);
            }
            case CEILING -> {
                d.minX = (int) Math.ceil(this.minX);
                d.minY = (int) Math.ceil(this.minY);
                d.maxX = (int) Math.ceil(this.maxX);
                d.maxY = (int) Math.ceil(this.maxY);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                d.minX = Math.round(this.minX);
                d.minY = Math.round(this.minY);
                d.maxX = Math.round(this.maxX);
                d.maxY = Math.round(this.maxY);
            }
            case HALF_AWAY_FROM_ZERO -> {
                d.minX = (int) (this.minX >= 0 ? Math.floor(this.minX + 0.5) : Math.ceil(this.minX - 0.5));
                d.minY = (int) (this.minY >= 0 ? Math.floor(this.minY + 0.5) : Math.ceil(this.minY - 0.5));
                d.maxX = (int) (this.maxX >= 0 ? Math.floor(this.maxX + 0.5) : Math.ceil(this.maxX - 0.5));
                d.maxY = (int) (this.maxY >= 0 ? Math.floor(this.maxY + 0.5) : Math.ceil(this.maxY - 0.5));
            }
            case HALF_EVEN -> {
                d.minX = (int) Math.rint(this.minX);
                d.minY = (int) Math.rint(this.minY);
                d.maxX = (int) Math.rint(this.maxX);
                d.maxY = (int) Math.rint(this.maxY);
            }
        }
        return dest;
    }


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
    public FloatRect lerp(FloatRectR other, float t, @Mutated FloatRect dest) {
        float otherMinX = other.minX();
        float otherMinY = other.minY();
        float otherMaxX = other.maxX();
        float otherMaxY = other.maxY();
        if (Math.useFma()) {
            FloatRectImpl d = (FloatRectImpl) dest;
            d.minX = java.lang.Math.fma(t, otherMinX - this.minX, this.minX);
            d.minY = java.lang.Math.fma(t, otherMinY - this.minY, this.minY);
            d.maxX = java.lang.Math.fma(t, otherMaxX - this.maxX, this.maxX);
            d.maxY = java.lang.Math.fma(t, otherMaxY - this.maxY, this.maxY);
            return d;
        } else {
            FloatRectImpl d = (FloatRectImpl) dest;
            d.minX = ((t) * (otherMinX - this.minX) + (this.minX));
            d.minY = ((t) * (otherMinY - this.minY) + (this.minY));
            d.maxX = ((t) * (otherMaxX - this.maxX) + (this.maxX));
            d.maxY = ((t) * (otherMaxY - this.maxY) + (this.maxY));
            return d;
        }
    }


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
    public DoubleRect lerp(FloatRectR other, float t, @Mutated DoubleRect dest) {
        float otherMinX = other.minX();
        float otherMinY = other.minY();
        float otherMaxX = other.maxX();
        float otherMaxY = other.maxY();
        if (Math.useFma()) {
            DoubleRectImpl d = (DoubleRectImpl) dest;
            d.minX = java.lang.Math.fma(t, otherMinX - this.minX, this.minX);
            d.minY = java.lang.Math.fma(t, otherMinY - this.minY, this.minY);
            d.maxX = java.lang.Math.fma(t, otherMaxX - this.maxX, this.maxX);
            d.maxY = java.lang.Math.fma(t, otherMaxY - this.maxY, this.maxY);
            return d;
        } else {
            DoubleRectImpl d = (DoubleRectImpl) dest;
            d.minX = ((t) * (otherMinX - this.minX) + (this.minX));
            d.minY = ((t) * (otherMinY - this.minY) + (this.minY));
            d.maxX = ((t) * (otherMaxX - this.maxX) + (this.maxX));
            d.maxY = ((t) * (otherMaxY - this.maxY) + (this.maxY));
            return d;
        }
    }


    /**
     * Linearly interpolate between this rectangle and ({@code otherMinX}, {@code otherMinY},
     * {@code otherMaxX}, {@code otherMaxY}) using the interpolation factor {@code t} and store the
     * result in {@code dest}.
     * <p>
     * The interpolation starts at this rectangle (interpolation factor {@code 0}) and ends at
     * ({@code otherMinX}, {@code otherMinY}, {@code otherMaxX}, {@code otherMaxY}) (interpolation
     * factor {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t},
     * as in JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact
     * only up to the rounding of {@code other - this}, which shows when this component is much
     * larger in magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
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
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatRect lerp(float otherMinX, float otherMinY, float otherMaxX, float otherMaxY, float t, @Mutated FloatRect dest) {
        if (Math.useFma()) {
            FloatRectImpl d = (FloatRectImpl) dest;
            d.minX = java.lang.Math.fma(t, otherMinX - this.minX, this.minX);
            d.minY = java.lang.Math.fma(t, otherMinY - this.minY, this.minY);
            d.maxX = java.lang.Math.fma(t, otherMaxX - this.maxX, this.maxX);
            d.maxY = java.lang.Math.fma(t, otherMaxY - this.maxY, this.maxY);
            return d;
        } else {
            FloatRectImpl d = (FloatRectImpl) dest;
            d.minX = ((t) * (otherMinX - this.minX) + (this.minX));
            d.minY = ((t) * (otherMinY - this.minY) + (this.minY));
            d.maxX = ((t) * (otherMaxX - this.maxX) + (this.maxX));
            d.maxY = ((t) * (otherMaxY - this.maxY) + (this.maxY));
            return d;
        }
    }


    /**
     * Linearly interpolate between this rectangle and ({@code otherMinX}, {@code otherMinY},
     * {@code otherMaxX}, {@code otherMaxY}) using the interpolation factor {@code t} and store the
     * result in {@code dest}.
     * <p>
     * The interpolation starts at this rectangle (interpolation factor {@code 0}) and ends at
     * ({@code otherMinX}, {@code otherMinY}, {@code otherMaxX}, {@code otherMaxY}) (interpolation
     * factor {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t},
     * as in JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact
     * only up to the rounding of {@code other - this}, which shows when this component is much
     * larger in magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
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
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect lerp(float otherMinX, float otherMinY, float otherMaxX, float otherMaxY, float t, @Mutated DoubleRect dest) {
        if (Math.useFma()) {
            DoubleRectImpl d = (DoubleRectImpl) dest;
            d.minX = java.lang.Math.fma(t, otherMinX - this.minX, this.minX);
            d.minY = java.lang.Math.fma(t, otherMinY - this.minY, this.minY);
            d.maxX = java.lang.Math.fma(t, otherMaxX - this.maxX, this.maxX);
            d.maxY = java.lang.Math.fma(t, otherMaxY - this.maxY, this.maxY);
            return d;
        } else {
            DoubleRectImpl d = (DoubleRectImpl) dest;
            d.minX = ((t) * (otherMinX - this.minX) + (this.minX));
            d.minY = ((t) * (otherMinY - this.minY) + (this.minY));
            d.maxX = ((t) * (otherMaxX - this.maxX) + (this.maxX));
            d.maxY = ((t) * (otherMaxY - this.maxY) + (this.maxY));
            return d;
        }
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
    public FloatRect correctBounds(@Mutated FloatRect dest) {
        FloatRectImpl d = (FloatRectImpl) dest;
        float _rd0 = this.minX;
        float _rd1 = this.minY;
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
     * The computation is performed at {@code float} precision; each result component is widened to
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
    public FloatRect expand(float margin, @Mutated FloatRect dest) {
        FloatRectImpl d = (FloatRectImpl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param margin the amount to expand by in every direction
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect expand(float margin, @Mutated DoubleRect dest) {
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
    public FloatRect intersect(FloatRectR other, @Mutated FloatRect dest) {
        float otherMinY = other.minY();
        float otherMaxX = other.maxX();
        float otherMaxY = other.maxY();
        FloatRectImpl d = (FloatRectImpl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to intersect with
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect intersect(FloatRectR other, @Mutated DoubleRect dest) {
        float otherMinY = other.minY();
        float otherMaxX = other.maxX();
        float otherMaxY = other.maxY();
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
    public FloatRect intersect(float otherMinX, float otherMinY, float otherMaxX, float otherMaxY, @Mutated FloatRect dest) {
        FloatRectImpl d = (FloatRectImpl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
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
    public DoubleRect intersect(float otherMinX, float otherMinY, float otherMaxX, float otherMaxY, @Mutated DoubleRect dest) {
        DoubleRectImpl d = (DoubleRectImpl) dest;
        d.minX = java.lang.Math.max(this.minX, otherMinX);
        d.minY = java.lang.Math.max(this.minY, otherMinY);
        d.maxX = java.lang.Math.min(this.maxX, otherMaxX);
        d.maxY = java.lang.Math.min(this.maxY, otherMaxY);
        return d;
    }


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
    public FloatRect scale(float sx, float sy, @Mutated FloatRect dest) {
        FloatRectImpl d = (FloatRectImpl) dest;
        float _t0 = sx * this.minX;
        float _t1 = sx * this.maxX;
        float _t2 = sy * this.minY;
        float _t3 = sy * this.maxY;
        d.minX = java.lang.Math.min(_t0, _t1);
        d.minY = java.lang.Math.min(_t2, _t3);
        d.maxX = java.lang.Math.max(_t0, _t1);
        d.maxY = java.lang.Math.max(_t2, _t3);
        return d;
    }


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
    public DoubleRect scale(float sx, float sy, @Mutated DoubleRect dest) {
        DoubleRectImpl d = (DoubleRectImpl) dest;
        float _t0 = sx * this.minX;
        float _t1 = sx * this.maxX;
        float _t2 = sy * this.minY;
        float _t3 = sy * this.maxY;
        d.minX = java.lang.Math.min(_t0, _t1);
        d.minY = java.lang.Math.min(_t2, _t3);
        d.maxX = java.lang.Math.max(_t0, _t1);
        d.maxY = java.lang.Math.max(_t2, _t3);
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
    public FloatRect translate(Float2R delta, @Mutated FloatRect dest) {
        float deltaX = delta.x();
        float deltaY = delta.y();
        FloatRectImpl d = (FloatRectImpl) dest;
        d.minX = deltaX + this.minX;
        d.minY = deltaY + this.minY;
        d.maxX = deltaX + this.maxX;
        d.maxY = deltaY + this.maxY;
        return d;
    }


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
    public DoubleRect translate(Float2R delta, @Mutated DoubleRect dest) {
        float deltaX = delta.x();
        float deltaY = delta.y();
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
    public FloatRect translate(float deltaX, float deltaY, @Mutated FloatRect dest) {
        FloatRectImpl d = (FloatRectImpl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect translate(float deltaX, float deltaY, @Mutated DoubleRect dest) {
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
    public FloatRect union(FloatRectR other, @Mutated FloatRect dest) {
        float otherMinY = other.minY();
        float otherMaxX = other.maxX();
        float otherMaxY = other.maxY();
        FloatRectImpl d = (FloatRectImpl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the rectangle to include in the union
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect union(FloatRectR other, @Mutated DoubleRect dest) {
        float otherMinY = other.minY();
        float otherMaxX = other.maxX();
        float otherMaxY = other.maxY();
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
    public FloatRect union(float otherMinX, float otherMinY, float otherMaxX, float otherMaxY, @Mutated FloatRect dest) {
        FloatRectImpl d = (FloatRectImpl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
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
    public DoubleRect union(float otherMinX, float otherMinY, float otherMaxX, float otherMaxY, @Mutated DoubleRect dest) {
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
    public FloatRect union(Float2R p, @Mutated FloatRect dest) {
        float pX = p.x();
        float pY = p.y();
        FloatRectImpl d = (FloatRectImpl) dest;
        d.minX = java.lang.Math.min(this.minX, pX);
        d.minY = java.lang.Math.min(this.minY, pY);
        d.maxX = java.lang.Math.max(this.maxX, pX);
        d.maxY = java.lang.Math.max(this.maxY, pY);
        return d;
    }


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
    public DoubleRect union(Float2R p, @Mutated DoubleRect dest) {
        float pX = p.x();
        float pY = p.y();
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
    public FloatRect union(float pX, float pY, @Mutated FloatRect dest) {
        FloatRectImpl d = (FloatRectImpl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY)}
     * @param pY the {@code y} component of the vector {@code (pX, pY)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect union(float pX, float pY, @Mutated DoubleRect dest) {
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
     * Valid input: the default range of the package documentation.
     *
     * @return the area of this rectangle
     */
    public float area() {
        return java.lang.Math.max(0.0f, this.maxX - this.minX) * java.lang.Math.max(0.0f, this.maxY - this.minY);
    }


    /**
     * Compute the x coordinate of the center of this rectangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the x coordinate of the center of this rectangle
     */
    public float centerX() {
        return 0.5f * this.minX + 0.5f * this.maxX;
    }


    /**
     * Compute the y coordinate of the center of this rectangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the y coordinate of the center of this rectangle
     */
    public float centerY() {
        return 0.5f * this.minY + 0.5f * this.maxY;
    }


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
    public Float2 closestPointToPoint(Float2R p, @Mutated Float2 dest) {
        float pY = p.y();
        Float2Impl d = (Float2Impl) dest;
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
    public Double2 closestPointToPoint(Float2R p, @Mutated Double2 dest) {
        float pY = p.y();
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
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY)} to find the closest point to
     * @param pY the {@code y} component of the point {@code (pX, pY)} to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Float2 closestPointToPoint(float pX, float pY, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY)} to find the closest point to
     * @param pY the {@code y} component of the point {@code (pX, pY)} to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Double2 closestPointToPoint(float pX, float pY, @Mutated Double2 dest) {
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
    public boolean containsPoint(Float2R p) {
        float pX = p.x();
        float pY = p.y();
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
    public boolean containsPoint(float pX, float pY) {
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
    public boolean containsRect(FloatRectR o) {
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
    public boolean containsRect(float oMinX, float oMinY, float oMaxX, float oMaxY) {
        if (!(this.minX <= oMinX)) return false;
        if (!(this.maxX >= oMaxX)) return false;
        if (!(this.minY <= oMinY)) return false;
        return this.maxY >= oMaxY;
    }


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
    public float distanceSquaredToPoint(Float2R p) {
        float pX = p.x();
        float pY = p.y();
        if (Math.useFma()) {
            float _t4 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
            float _t5 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
            return java.lang.Math.fma(_t4, _t4, _t5 * _t5);
        } else {
            float _t4 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
            float _t5 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
            return ((_t4) * (_t4) + (_t5 * _t5));
        }
    }


    /**
     * Compute the squared distance between this rectangle and the given point, i.e. the squared
     * length of the difference between the point and its per-axis clamp into the rectangle's
     * bounds; zero for a point inside or on the rectangle.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY)} to measure the distance to
     * @param pY the {@code y} component of the point {@code (pX, pY)} to measure the distance to
     * @return the squared distance between this rectangle and the given point, i.e. the squared
     *        length of the difference between the point and its per-axis clamp into the rectangle's
     *        bounds; zero for a point inside or on the rectangle
     */
    public float distanceSquaredToPoint(float pX, float pY) {
        if (Math.useFma()) {
            float _t4 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
            float _t5 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
            return java.lang.Math.fma(_t4, _t4, _t5 * _t5);
        } else {
            float _t4 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
            float _t5 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
            return ((_t4) * (_t4) + (_t5 * _t5));
        }
    }


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
    public float distanceSquaredToRect(FloatRectR other) {
        float otherMinX = other.minX();
        float otherMinY = other.minY();
        float otherMaxX = other.maxX();
        float otherMaxY = other.maxY();
        if (Math.useFma()) {
            float _t6 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - otherMaxX, otherMinX - this.maxX));
            float _t7 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - otherMaxY, otherMinY - this.maxY));
            return java.lang.Math.fma(_t6, _t6, _t7 * _t7);
        } else {
            float _t6 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - otherMaxX, otherMinX - this.maxX));
            float _t7 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - otherMaxY, otherMinY - this.maxY));
            return ((_t6) * (_t6) + (_t7 * _t7));
        }
    }


    /**
     * Compute the squared distance between this rectangle and the given rectangle, i.e. the squared
     * length of the shortest vector between any two points of the two rectangles; zero when they
     * overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component; {@code (otherMinX, otherMinY)} must not exceed {@code (otherMaxX, otherMaxY)} in
     * any component.
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
    public float distanceSquaredToRect(float otherMinX, float otherMinY, float otherMaxX, float otherMaxY) {
        if (Math.useFma()) {
            float _t6 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - otherMaxX, otherMinX - this.maxX));
            float _t7 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - otherMaxY, otherMinY - this.maxY));
            return java.lang.Math.fma(_t6, _t6, _t7 * _t7);
        } else {
            float _t6 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - otherMaxX, otherMinX - this.maxX));
            float _t7 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - otherMaxY, otherMinY - this.maxY));
            return ((_t6) * (_t6) + (_t7 * _t7));
        }
    }


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
    public float distanceToPoint(Float2R p) {
        float pX = p.x();
        float pY = p.y();
        if (Math.useFma()) {
            float _t4 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
            float _t5 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t4, _t4, _t5 * _t5));
        } else {
            float _t4 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
            float _t5 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
            return (float) java.lang.Math.sqrt(((_t4) * (_t4) + (_t5 * _t5)));
        }
    }


    /**
     * Compute the distance between this rectangle and the given point, i.e. the length of the
     * difference between the point and its per-axis clamp into the rectangle's bounds; zero for a
     * point inside or on the rectangle.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY)} to measure the distance to
     * @param pY the {@code y} component of the point {@code (pX, pY)} to measure the distance to
     * @return the distance between this rectangle and the given point, i.e. the length of the
     *        difference between the point and its per-axis clamp into the rectangle's bounds; zero
     *        for a point inside or on the rectangle
     */
    public float distanceToPoint(float pX, float pY) {
        if (Math.useFma()) {
            float _t4 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
            float _t5 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t4, _t4, _t5 * _t5));
        } else {
            float _t4 = pX - java.lang.Math.max(this.minX, java.lang.Math.min(pX, this.maxX));
            float _t5 = pY - java.lang.Math.max(this.minY, java.lang.Math.min(pY, this.maxY));
            return (float) java.lang.Math.sqrt(((_t4) * (_t4) + (_t5 * _t5)));
        }
    }


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
    public float distanceToRect(FloatRectR other) {
        float otherMinX = other.minX();
        float otherMinY = other.minY();
        float otherMaxX = other.maxX();
        float otherMaxY = other.maxY();
        if (Math.useFma()) {
            float _t6 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - otherMaxX, otherMinX - this.maxX));
            float _t7 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - otherMaxY, otherMinY - this.maxY));
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, _t7 * _t7));
        } else {
            float _t6 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - otherMaxX, otherMinX - this.maxX));
            float _t7 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - otherMaxY, otherMinY - this.maxY));
            return (float) java.lang.Math.sqrt(((_t6) * (_t6) + (_t7 * _t7)));
        }
    }


    /**
     * Compute the distance between this rectangle and the given rectangle, i.e. the length of the
     * shortest vector between any two points of the two rectangles; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the minimum
     * corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component; {@code (otherMinX, otherMinY)} must not exceed {@code (otherMaxX, otherMaxY)} in
     * any component.
     *
     * @param otherMinX the {@code minX} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)} to measure the distance to
     * @param otherMinY the {@code minY} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)} to measure the distance to
     * @param otherMaxX the {@code maxX} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)} to measure the distance to
     * @param otherMaxY the {@code maxY} component of the rectangle
     *        {@code (otherMinX, otherMinY, otherMaxX, otherMaxY)} to measure the distance to
     * @return the distance between this rectangle and the given rectangle, i.e. the length of the
     *        shortest vector between any two points of the two rectangles; zero when they overlap
     *        or touch
     */
    public float distanceToRect(float otherMinX, float otherMinY, float otherMaxX, float otherMaxY) {
        if (Math.useFma()) {
            float _t6 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - otherMaxX, otherMinX - this.maxX));
            float _t7 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - otherMaxY, otherMinY - this.maxY));
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, _t7 * _t7));
        } else {
            float _t6 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minX - otherMaxX, otherMinX - this.maxX));
            float _t7 = java.lang.Math.max(0.0f, java.lang.Math.max(this.minY - otherMaxY, otherMinY - this.maxY));
            return (float) java.lang.Math.sqrt(((_t6) * (_t6) + (_t7 * _t7)));
        }
    }


    /**
     * Get the center of this rectangle and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float2 getCenter(@Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = 0.5f * this.minX + 0.5f * this.maxX;
        d.y = 0.5f * this.minY + 0.5f * this.maxY;
        return d;
    }


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
    public Double2 getCenter(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = 0.5f * this.minX + 0.5f * this.maxX;
        d.y = 0.5f * this.minY + 0.5f * this.maxY;
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
    public Float2 getMax(@Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = this.maxX;
        d.y = this.maxY;
        return d;
    }


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
    public Float2 getMin(@Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = this.minX;
        d.y = this.minY;
        return d;
    }


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
    public Float2 getSize(@Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = this.maxX - this.minX;
        d.y = this.maxY - this.minY;
        return d;
    }


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
    public Double2 getSize(@Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = this.maxX - this.minX;
        d.y = this.maxY - this.minY;
        return d;
    }


    /**
     * Compute the height of this rectangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the height of this rectangle
     */
    public float height() {
        return this.maxY - this.minY;
    }


    /**
     * Determine whether this rectangle intersects {@code o}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the rectangle to test
     * @return {@code true} if this rectangle intersects {@code o}, {@code false} otherwise
     */
    public boolean intersectsRect(FloatRectR o) {
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
    public boolean intersectsRect(float oMinX, float oMinY, float oMaxX, float oMaxY) {
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
     * Valid input: the default range of the package documentation.
     *
     * @return the width of this rectangle
     */
    public float width() {
        return this.maxX - this.minX;
    }

    public float minX() { return this.minX; }
    public float minY() { return this.minY; }
    public float maxX() { return this.maxX; }
    public float maxY() { return this.maxY; }

    @Override public String toString() {
        return "FloatRect(" + minX() + ", " + minY() + ", " + maxX() + ", " + maxY() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatRectImpl)) return false;
        FloatRectImpl o = (FloatRectImpl) obj;
        return Float.floatToIntBits(minX) == Float.floatToIntBits(o.minX)
            && Float.floatToIntBits(minY) == Float.floatToIntBits(o.minY)
            && Float.floatToIntBits(maxX) == Float.floatToIntBits(o.maxX)
            && Float.floatToIntBits(maxY) == Float.floatToIntBits(o.maxY);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(minX);
        h = 31 * h + Float.floatToIntBits(minY);
        h = 31 * h + Float.floatToIntBits(maxX);
        h = 31 * h + Float.floatToIntBits(maxY);
        return h;
    }

    @Override public boolean isFinite() {
        return Float.isFinite(minX)
            && Float.isFinite(minY)
            && Float.isFinite(maxX)
            && Float.isFinite(maxY);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(minX)
            || Float.isNaN(minY)
            || Float.isNaN(maxX)
            || Float.isNaN(maxY);
    }

    @Override public boolean equalsEpsilon(FloatRectR other, float epsilon) {
        return java.lang.Math.abs(minX - other.minX()) <= epsilon
            && java.lang.Math.abs(minY - other.minY()) <= epsilon
            && java.lang.Math.abs(maxX - other.maxX()) <= epsilon
            && java.lang.Math.abs(maxY - other.maxY()) <= epsilon;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = this.minX;
        dest[offset + 1] = this.minY;
        dest[offset + 2] = this.maxX;
        dest[offset + 3] = this.maxY;
        return dest;
    }
    public @Mutated FloatRect load(float[] src, int offset) {
        this.minX = src[offset];
        this.minY = src[offset + 1];
        this.maxX = src[offset + 2];
        this.maxY = src[offset + 3];
        return this;
    }
    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }
    @Mutated public FloatRect load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatRect loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatRect loadRelative(FloatBuffer buf) {
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
    public FloatRect load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public FloatRect loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public FloatRect loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatRect r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return r;
    }
    public FloatRect storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public FloatRect loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.store(this, 0L, dest); }
    public MemorySegment store(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.store(this, offset, dest);
    }
    @Mutated public FloatRect load(MemorySegment src) { return StoreLoad.SEG_OPS.load(this, 0L, src); }
    public FloatRect load(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.load(this, offset, src);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.minX;
        dest[offset + 1] = this.minY;
        dest[offset + 2] = this.maxX;
        dest[offset + 3] = this.maxY;
        return dest;
    }
    public @Mutated FloatRect load(double[] src, int offset) {
        this.minX = (float) src[offset];
        this.minY = (float) src[offset + 1];
        this.maxX = (float) src[offset + 2];
        this.maxY = (float) src[offset + 3];
        return this;
    }
    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return buf;
    }
    @Mutated public FloatRect load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public FloatRect loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public FloatRect loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }
    public ByteBuffer storeDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }
    public FloatRect loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }
    public FloatRect loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }
    public FloatRect loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        FloatRect r = StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return r;
    }
    public FloatRect storeDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeDoubleUnsafe(this, address);
    }
    @Mutated public FloatRect loadDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadDoubleUnsafe(this, address);
    }
    public MemorySegment storeDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeDouble(this, 0L, dest); }
    public MemorySegment storeDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeDouble(this, offset, dest);
    }
    @Mutated public FloatRect loadDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadDouble(this, 0L, src); }
    public FloatRect loadDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadDouble(this, offset, src);
    }
}
