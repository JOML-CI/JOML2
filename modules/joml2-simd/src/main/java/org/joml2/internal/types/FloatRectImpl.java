// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import jdk.incubator.vector.*;
import org.joml2.internal.simd.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link FloatRect} backed by a {@code float[]} array, with Vector API
 * SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatRectImpl implements FloatRect {

    public float[] data;

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
        data = new float[4];
        data[0] = Float.POSITIVE_INFINITY;
        data[1] = Float.POSITIVE_INFINITY;
        data[2] = Float.NEGATIVE_INFINITY;
        data[3] = Float.NEGATIVE_INFINITY;
    }

    public FloatRectImpl(float minX, float minY, float maxX, float maxY) {
        float[] dd = this.data = new float[4];
        dd[0] = minX;
        dd[1] = minY;
        dd[2] = maxX;
        dd[3] = maxY;
    }

    public FloatRectImpl(FloatRectR src) {
        float[] dd = this.data = new float[4];
        dd[0] = src.minX();
        dd[1] = src.minY();
        dd[2] = src.maxX();
        dd[3] = src.maxY();
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
        float[] sd = this.data;
        float[] otherData = ((FloatRectImpl) other).data;
        float[] dd = ((FloatRectImpl) dest).data;
        FloatVector.fromArray(COL_SPECIES, otherData, 0).add(FloatVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = other.minX() + sd[0];
        dd[1] = otherMinY + sd[1];
        dd[2] = otherMaxX + sd[2];
        dd[3] = otherMaxY + sd[3];
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((FloatRectImpl) dest).data;
        dd[0] = otherMinX + sd[0];
        dd[1] = otherMinY + sd[1];
        dd[2] = otherMaxX + sd[2];
        dd[3] = otherMaxY + sd[3];
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = otherMinX + sd[0];
        dd[1] = otherMinY + sd[1];
        dd[2] = otherMaxX + sd[2];
        dd[3] = otherMaxY + sd[3];
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((FloatRectImpl) dest).data;
        float _buf0 = -sd[2];
        float _buf1 = -sd[3];
        dd[2] = -sd[0];
        dd[3] = -sd[1];
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = -sd[2];
        dd[1] = -sd[3];
        dd[2] = -sd[0];
        dd[3] = -sd[1];
        return dest;
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
        float[] sd = this.data;
        float[] otherData = ((FloatRectImpl) other).data;
        float[] dd = ((FloatRectImpl) dest).data;
        FloatVector.fromArray(COL_SPECIES, sd, 0).sub(FloatVector.fromArray(COL_SPECIES, otherData, 0)).intoArray(dd, 0);
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = sd[0] - other.minX();
        dd[1] = sd[1] - otherMinY;
        dd[2] = sd[2] - otherMaxX;
        dd[3] = sd[3] - otherMaxY;
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((FloatRectImpl) dest).data;
        dd[0] = sd[0] - otherMinX;
        dd[1] = sd[1] - otherMinY;
        dd[2] = sd[2] - otherMaxX;
        dd[3] = sd[3] - otherMaxY;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = sd[0] - otherMinX;
        dd[1] = sd[1] - otherMinY;
        dd[2] = sd[2] - otherMaxX;
        dd[3] = sd[3] - otherMaxY;
        return dest;
    }


    /**
     * Set this rectangle to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the rectangle to copy
     * @return this
     */
    @Mutated public FloatRect set(FloatRectR v) {
        float[] dd = this.data;
        float[] vData = ((FloatRectImpl) v).data;
        FloatVector.fromArray(COL_SPECIES, vData, 0).intoArray(dd, 0);
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
        float[] dd = this.data;
        dd[0] = vMinX;
        dd[1] = vMinY;
        dd[2] = vMaxX;
        dd[3] = vMaxY;
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
        float[] sd = this.data;
        float[] maxData = ((Float2Impl) max).data;
        float[] dd = ((FloatRectImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = maxData[0];
        dd[3] = maxData[1];
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = maxX;
        dd[3] = maxY;
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((FloatRectImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = maxX;
        dd[3] = maxY;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = maxX;
        dd[3] = maxY;
        return dest;
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
        float[] sd = this.data;
        float[] minData = ((Float2Impl) min).data;
        float[] dd = ((FloatRectImpl) dest).data;
        dd[0] = minData[0];
        dd[1] = minData[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = min.x();
        dd[1] = minY;
        dd[2] = sd[2];
        dd[3] = sd[3];
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((FloatRectImpl) dest).data;
        dd[0] = minX;
        dd[1] = minY;
        dd[2] = sd[2];
        dd[3] = sd[3];
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = minX;
        dd[1] = minY;
        dd[2] = sd[2];
        dd[3] = sd[3];
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        return dest;
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
        float[] sd = this.data;
        int[] dd = ((IntRectImpl) dest).data;
        dd[0] = (int) (sd[0]);
        dd[1] = (int) (sd[1]);
        dd[2] = (int) (sd[2]);
        dd[3] = (int) (sd[3]);
        return dest;
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
        float[] sd = this.data;
        int[] dd = ((IntRectImpl) dest).data;
        switch (roundingMode) {
            case TRUNCATE -> { return toInt(dest); }
            case FLOOR -> {
                dd[0] = (int) Math.floor(sd[0]);
                dd[1] = (int) Math.floor(sd[1]);
                dd[2] = (int) Math.floor(sd[2]);
                dd[3] = (int) Math.floor(sd[3]);
            }
            case CEILING -> {
                dd[0] = (int) Math.ceil(sd[0]);
                dd[1] = (int) Math.ceil(sd[1]);
                dd[2] = (int) Math.ceil(sd[2]);
                dd[3] = (int) Math.ceil(sd[3]);
            }
            case HALF_TOWARD_POSITIVE_INFINITY -> {
                dd[0] = Math.round(sd[0]);
                dd[1] = Math.round(sd[1]);
                dd[2] = Math.round(sd[2]);
                dd[3] = Math.round(sd[3]);
            }
            case HALF_AWAY_FROM_ZERO -> {
                dd[0] = (int) (sd[0] >= 0 ? Math.floor(sd[0] + 0.5) : Math.ceil(sd[0] - 0.5));
                dd[1] = (int) (sd[1] >= 0 ? Math.floor(sd[1] + 0.5) : Math.ceil(sd[1] - 0.5));
                dd[2] = (int) (sd[2] >= 0 ? Math.floor(sd[2] + 0.5) : Math.ceil(sd[2] - 0.5));
                dd[3] = (int) (sd[3] >= 0 ? Math.floor(sd[3] + 0.5) : Math.ceil(sd[3] - 0.5));
            }
            case HALF_EVEN -> {
                dd[0] = (int) Math.rint(sd[0]);
                dd[1] = (int) Math.rint(sd[1]);
                dd[2] = (int) Math.rint(sd[2]);
                dd[3] = (int) Math.rint(sd[3]);
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
        if (SimdMath.USE_FMA) return lerp_fma(other, t, dest);
        return lerp_mulAdd(other, t, dest);
    }

    private FloatRect lerp_fma(FloatRectR other, float t, @Mutated FloatRect dest) {
        float[] sd = this.data;
        float[] otherData = ((FloatRectImpl) other).data;
        float[] dd = ((FloatRectImpl) dest).data;
        var _sv0 = FloatVector.fromArray(COL_SPECIES, sd, 0);
        FloatVector.broadcast(COL_SPECIES, t).fma(FloatVector.fromArray(COL_SPECIES, otherData, 0).sub(_sv0), _sv0).intoArray(dd, 0);
        return dest;
    }

    private FloatRect lerp_mulAdd(FloatRectR other, float t, @Mutated FloatRect dest) {
        float[] sd = this.data;
        float[] otherData = ((FloatRectImpl) other).data;
        float[] dd = ((FloatRectImpl) dest).data;
        var _sv0 = FloatVector.fromArray(COL_SPECIES, sd, 0);
        FloatVector.broadcast(COL_SPECIES, t).mul(FloatVector.fromArray(COL_SPECIES, otherData, 0).sub(_sv0)).add(_sv0).intoArray(dd, 0);
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
        float otherMinY = other.minY();
        float otherMaxX = other.maxX();
        float otherMaxY = other.maxY();
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = Math.fma(t, other.minX() - sd[0], sd[0]);
        dd[1] = Math.fma(t, otherMinY - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherMaxX - sd[2], sd[2]);
        dd[3] = Math.fma(t, otherMaxY - sd[3], sd[3]);
        return dest;
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
        if (SimdMath.USE_FMA) return lerp_fma(otherMinX, otherMinY, otherMaxX, otherMaxY, t, dest);
        return lerp_mulAdd(otherMinX, otherMinY, otherMaxX, otherMaxY, t, dest);
    }

    private FloatRect lerp_fma(float otherMinX, float otherMinY, float otherMaxX, float otherMaxY, float t, @Mutated FloatRect dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRectImpl) dest).data;
        var _sv0 = FloatVector.fromArray(COL_SPECIES, sd, 0);
        FloatVector.broadcast(COL_SPECIES, t).fma(FloatVector.zero(COL_SPECIES).withLane(0, otherMinX).withLane(1, otherMinY).withLane(2, otherMaxX).withLane(3, otherMaxY).sub(_sv0), _sv0).intoArray(dd, 0);
        return dest;
    }

    private FloatRect lerp_mulAdd(float otherMinX, float otherMinY, float otherMaxX, float otherMaxY, float t, @Mutated FloatRect dest) {
        float[] sd = this.data;
        float[] dd = ((FloatRectImpl) dest).data;
        var _sv0 = FloatVector.fromArray(COL_SPECIES, sd, 0);
        FloatVector.broadcast(COL_SPECIES, t).mul(FloatVector.zero(COL_SPECIES).withLane(0, otherMinX).withLane(1, otherMinY).withLane(2, otherMaxX).withLane(3, otherMaxY).sub(_sv0)).add(_sv0).intoArray(dd, 0);
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = Math.fma(t, otherMinX - sd[0], sd[0]);
        dd[1] = Math.fma(t, otherMinY - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherMaxX - sd[2], sd[2]);
        dd[3] = Math.fma(t, otherMaxY - sd[3], sd[3]);
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((FloatRectImpl) dest).data;
        float _buf0 = java.lang.Math.min(sd[0], sd[2]);
        float _buf1 = java.lang.Math.min(sd[1], sd[3]);
        dd[2] = java.lang.Math.max(sd[0], sd[2]);
        dd[3] = java.lang.Math.max(sd[1], sd[3]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], sd[2]);
        dd[1] = java.lang.Math.min(sd[1], sd[3]);
        dd[2] = java.lang.Math.max(sd[0], sd[2]);
        dd[3] = java.lang.Math.max(sd[1], sd[3]);
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((FloatRectImpl) dest).data;
        dd[0] = sd[0] - margin;
        dd[1] = sd[1] - margin;
        dd[2] = margin + sd[2];
        dd[3] = margin + sd[3];
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = sd[0] - margin;
        dd[1] = sd[1] - margin;
        dd[2] = margin + sd[2];
        dd[3] = margin + sd[3];
        return dest;
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
        float[] sd = this.data;
        float[] otherData = ((FloatRectImpl) other).data;
        float[] dd = ((FloatRectImpl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], otherData[0]);
        dd[1] = java.lang.Math.max(sd[1], otherData[1]);
        dd[2] = java.lang.Math.min(sd[2], otherData[2]);
        dd[3] = java.lang.Math.min(sd[3], otherData[3]);
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], other.minX());
        dd[1] = java.lang.Math.max(sd[1], otherMinY);
        dd[2] = java.lang.Math.min(sd[2], otherMaxX);
        dd[3] = java.lang.Math.min(sd[3], otherMaxY);
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((FloatRectImpl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], otherMinX);
        dd[1] = java.lang.Math.max(sd[1], otherMinY);
        dd[2] = java.lang.Math.min(sd[2], otherMaxX);
        dd[3] = java.lang.Math.min(sd[3], otherMaxY);
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], otherMinX);
        dd[1] = java.lang.Math.max(sd[1], otherMinY);
        dd[2] = java.lang.Math.min(sd[2], otherMaxX);
        dd[3] = java.lang.Math.min(sd[3], otherMaxY);
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((FloatRectImpl) dest).data;
        float _t0 = sx * sd[0];
        float _t1 = sx * sd[2];
        float _t2 = sy * sd[1];
        float _t3 = sy * sd[3];
        dd[0] = java.lang.Math.min(_t0, _t1);
        dd[1] = java.lang.Math.min(_t2, _t3);
        dd[2] = java.lang.Math.max(_t0, _t1);
        dd[3] = java.lang.Math.max(_t2, _t3);
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        float _t0 = sx * sd[0];
        float _t1 = sx * sd[2];
        float _t2 = sy * sd[1];
        float _t3 = sy * sd[3];
        dd[0] = java.lang.Math.min(_t0, _t1);
        dd[1] = java.lang.Math.min(_t2, _t3);
        dd[2] = java.lang.Math.max(_t0, _t1);
        dd[3] = java.lang.Math.max(_t2, _t3);
        return dest;
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
        float[] sd = this.data;
        float[] deltaData = ((Float2Impl) delta).data;
        float[] dd = ((FloatRectImpl) dest).data;
        FloatVector.broadcast(COL_SPECIES, deltaData[0]).blend(FloatVector.broadcast(COL_SPECIES, deltaData[1]), MASK_0).add(FloatVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = deltaX + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaX + sd[2];
        dd[3] = deltaY + sd[3];
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((FloatRectImpl) dest).data;
        FloatVector.broadcast(COL_SPECIES, deltaX).blend(FloatVector.broadcast(COL_SPECIES, deltaY), MASK_0).add(FloatVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = deltaX + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaX + sd[2];
        dd[3] = deltaY + sd[3];
        return dest;
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
        float[] sd = this.data;
        float[] otherData = ((FloatRectImpl) other).data;
        float[] dd = ((FloatRectImpl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], otherData[0]);
        dd[1] = java.lang.Math.min(sd[1], otherData[1]);
        dd[2] = java.lang.Math.max(sd[2], otherData[2]);
        dd[3] = java.lang.Math.max(sd[3], otherData[3]);
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], other.minX());
        dd[1] = java.lang.Math.min(sd[1], otherMinY);
        dd[2] = java.lang.Math.max(sd[2], otherMaxX);
        dd[3] = java.lang.Math.max(sd[3], otherMaxY);
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((FloatRectImpl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], otherMinX);
        dd[1] = java.lang.Math.min(sd[1], otherMinY);
        dd[2] = java.lang.Math.max(sd[2], otherMaxX);
        dd[3] = java.lang.Math.max(sd[3], otherMaxY);
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], otherMinX);
        dd[1] = java.lang.Math.min(sd[1], otherMinY);
        dd[2] = java.lang.Math.max(sd[2], otherMaxX);
        dd[3] = java.lang.Math.max(sd[3], otherMaxY);
        return dest;
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
        float[] sd = this.data;
        float[] pData = ((Float2Impl) p).data;
        float[] dd = ((FloatRectImpl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], pData[0]);
        dd[1] = java.lang.Math.min(sd[1], pData[1]);
        dd[2] = java.lang.Math.max(sd[2], pData[0]);
        dd[3] = java.lang.Math.max(sd[3], pData[1]);
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], pX);
        dd[1] = java.lang.Math.min(sd[1], pY);
        dd[2] = java.lang.Math.max(sd[2], pX);
        dd[3] = java.lang.Math.max(sd[3], pY);
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((FloatRectImpl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], pX);
        dd[1] = java.lang.Math.min(sd[1], pY);
        dd[2] = java.lang.Math.max(sd[2], pX);
        dd[3] = java.lang.Math.max(sd[3], pY);
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], pX);
        dd[1] = java.lang.Math.min(sd[1], pY);
        dd[2] = java.lang.Math.max(sd[2], pX);
        dd[3] = java.lang.Math.max(sd[3], pY);
        return dest;
    }


    /**
     * Compute the area of this rectangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the area of this rectangle
     */
    public float area() {
        float[] sd = this.data;
        return java.lang.Math.max(0.0f, sd[2] - sd[0]) * java.lang.Math.max(0.0f, sd[3] - sd[1]);
    }


    /**
     * Compute the x coordinate of the center of this rectangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the x coordinate of the center of this rectangle
     */
    public float centerX() {
        float[] sd = this.data;
        return 0.5f * sd[0] + 0.5f * sd[2];
    }


    /**
     * Compute the y coordinate of the center of this rectangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the y coordinate of the center of this rectangle
     */
    public float centerY() {
        float[] sd = this.data;
        return 0.5f * sd[1] + 0.5f * sd[3];
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
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], java.lang.Math.min(p.x(), sd[2]));
        dd[1] = java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[3]));
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], java.lang.Math.min(p.x(), sd[2]));
        dd[1] = java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[3]));
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], java.lang.Math.min(pX, sd[2]));
        dd[1] = java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[3]));
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], java.lang.Math.min(pX, sd[2]));
        dd[1] = java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[3]));
        return dest;
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
        float[] sd = this.data;
        if (!(pX >= sd[0])) return false;
        if (!(pX <= sd[2])) return false;
        if (!(pY >= sd[1])) return false;
        return pY <= sd[3];
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
        float[] sd = this.data;
        if (!(pX >= sd[0])) return false;
        if (!(pX <= sd[2])) return false;
        if (!(pY >= sd[1])) return false;
        return pY <= sd[3];
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
        float[] sd = this.data;
        if (!(sd[0] <= o.minX())) return false;
        if (!(sd[2] >= o.maxX())) return false;
        if (!(sd[1] <= o.minY())) return false;
        return sd[3] >= o.maxY();
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
        float[] sd = this.data;
        if (!(sd[0] <= oMinX)) return false;
        if (!(sd[2] >= oMaxX)) return false;
        if (!(sd[1] <= oMinY)) return false;
        return sd[3] >= oMaxY;
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
        float[] sd = this.data;
        float _t4 = pX - java.lang.Math.max(sd[0], java.lang.Math.min(pX, sd[2]));
        float _t5 = pY - java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[3]));
        return Math.fma(_t4, _t4, _t5 * _t5);
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
        float[] sd = this.data;
        float _t4 = pX - java.lang.Math.max(sd[0], java.lang.Math.min(pX, sd[2]));
        float _t5 = pY - java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[3]));
        return Math.fma(_t4, _t4, _t5 * _t5);
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
        float[] sd = this.data;
        float _t6 = java.lang.Math.max(0.0f, java.lang.Math.max(sd[0] - other.maxX(), other.minX() - sd[2]));
        float _t7 = java.lang.Math.max(0.0f, java.lang.Math.max(sd[1] - other.maxY(), other.minY() - sd[3]));
        return Math.fma(_t6, _t6, _t7 * _t7);
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
        float[] sd = this.data;
        float _t6 = java.lang.Math.max(0.0f, java.lang.Math.max(sd[0] - otherMaxX, otherMinX - sd[2]));
        float _t7 = java.lang.Math.max(0.0f, java.lang.Math.max(sd[1] - otherMaxY, otherMinY - sd[3]));
        return Math.fma(_t6, _t6, _t7 * _t7);
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
        float[] sd = this.data;
        float _t4 = pX - java.lang.Math.max(sd[0], java.lang.Math.min(pX, sd[2]));
        float _t5 = pY - java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[3]));
        return (float) java.lang.Math.sqrt(Math.fma(_t4, _t4, _t5 * _t5));
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
        float[] sd = this.data;
        float _t4 = pX - java.lang.Math.max(sd[0], java.lang.Math.min(pX, sd[2]));
        float _t5 = pY - java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[3]));
        return (float) java.lang.Math.sqrt(Math.fma(_t4, _t4, _t5 * _t5));
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
        float[] sd = this.data;
        float _t6 = java.lang.Math.max(0.0f, java.lang.Math.max(sd[0] - other.maxX(), other.minX() - sd[2]));
        float _t7 = java.lang.Math.max(0.0f, java.lang.Math.max(sd[1] - other.maxY(), other.minY() - sd[3]));
        return (float) java.lang.Math.sqrt(Math.fma(_t6, _t6, _t7 * _t7));
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
        float[] sd = this.data;
        float _t6 = java.lang.Math.max(0.0f, java.lang.Math.max(sd[0] - otherMaxX, otherMinX - sd[2]));
        float _t7 = java.lang.Math.max(0.0f, java.lang.Math.max(sd[1] - otherMaxY, otherMinY - sd[3]));
        return (float) java.lang.Math.sqrt(Math.fma(_t6, _t6, _t7 * _t7));
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
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = 0.5f * sd[0] + 0.5f * sd[2];
        dd[1] = 0.5f * sd[1] + 0.5f * sd[3];
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = 0.5f * sd[0] + 0.5f * sd[2];
        dd[1] = 0.5f * sd[1] + 0.5f * sd[3];
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = sd[2];
        dd[1] = sd[3];
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = sd[2];
        dd[1] = sd[3];
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        return dest;
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
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        dd[0] = sd[2] - sd[0];
        dd[1] = sd[3] - sd[1];
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = sd[2] - sd[0];
        dd[1] = sd[3] - sd[1];
        return dest;
    }


    /**
     * Compute the height of this rectangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the height of this rectangle
     */
    public float height() {
        float[] sd = this.data;
        return sd[3] - sd[1];
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
        float[] sd = this.data;
        if (!(sd[2] >= o.minX())) return false;
        if (!(sd[0] <= o.maxX())) return false;
        if (!(sd[3] >= o.minY())) return false;
        return sd[1] <= o.maxY();
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
        float[] sd = this.data;
        if (!(sd[2] >= oMinX)) return false;
        if (!(sd[0] <= oMaxX)) return false;
        if (!(sd[3] >= oMinY)) return false;
        return sd[1] <= oMaxY;
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
        float[] sd = this.data;
        if (!(sd[0] <= sd[2])) return false;
        return sd[1] <= sd[3];
    }


    /**
     * Compute the width of this rectangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the width of this rectangle
     */
    public float width() {
        float[] sd = this.data;
        return sd[2] - sd[0];
    }

    public float minX() { return data[0]; }
    public float minY() { return data[1]; }
    public float maxX() { return data[2]; }
    public float maxY() { return data[3]; }

    @Override public String toString() {
        return "FloatRect(" + minX() + ", " + minY() + ", " + maxX() + ", " + maxY() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatRectImpl)) return false;
        FloatRectImpl o = (FloatRectImpl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Float.isFinite(data[0])
            && Float.isFinite(data[1])
            && Float.isFinite(data[2])
            && Float.isFinite(data[3]);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(data[0])
            || Float.isNaN(data[1])
            || Float.isNaN(data[2])
            || Float.isNaN(data[3]);
    }

    @Override public boolean equalsEpsilon(FloatRectR other, float epsilon) {
        return java.lang.Math.abs(data[0] - other.minX()) <= epsilon
            && java.lang.Math.abs(data[1] - other.minY()) <= epsilon
            && java.lang.Math.abs(data[2] - other.maxX()) <= epsilon
            && java.lang.Math.abs(data[3] - other.maxY()) <= epsilon;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        float[] d = this.data;
        FloatVector.fromArray(COL_SPECIES, d, 0).intoArray(dest, offset);
        return dest;
    }
    public @Mutated FloatRect load(float[] src, int offset) {
        float[] d = this.data;
        FloatVector.fromArray(COL_SPECIES, src, offset).intoArray(d, 0);
        return this;
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        if (!buf.hasArray()) return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
        float[] d = this.data;
        float[] arr = buf.array();
        int off = buf.arrayOffset() + index;
        FloatVector.fromArray(COL_SPECIES, d, 0).intoArray(arr, off);
        return buf;
    }
    @Mutated public FloatRect loadAbsolute(int index, FloatBuffer buf) {
        if (!buf.hasArray()) return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
        float[] d = this.data;
        float[] arr = buf.array();
        int off = buf.arrayOffset() + index;
        FloatVector.fromArray(COL_SPECIES, arr, off).intoArray(d, 0);
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
    public MemorySegment store(long offset, MemorySegment dest) {
        float[] d = this.data;
        FloatVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(dest, offset, ByteOrder.nativeOrder());
        return dest;
    }
    public FloatRect load(long offset, MemorySegment src) {
        float[] d = this.data;
        FloatVector.fromMemorySegment(COL_SPECIES, src, offset, ByteOrder.nativeOrder()).intoArray(d, 0);
        return this;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }
    public @Mutated FloatRect load(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
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

    private static final VectorSpecies<Float> COL_SPECIES = FloatVector.SPECIES_128;
    private static final VectorMask<Float> MASK_0 = VectorMask.fromValues(COL_SPECIES, false, true, false, true);
}
