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
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link DoubleRect} backed by a {@code double[]} array, with Vector
 * API SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoubleRectImpl implements DoubleRect {

    public double[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoubleRectSegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleRectSegOpsUnsafe()
                        : new DoubleRectSegOpsMS();
        static final DoubleRectBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleRectBbOpsUnsafe()
                        : new DoubleRectBbOpsApi();
        static final DoubleRectRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleRectRawOpsUnsafe()
                        : new DoubleRectRawOpsApi();
    }

    public DoubleRectImpl() {
        data = new double[4];
        data[0] = Double.POSITIVE_INFINITY;
        data[1] = Double.POSITIVE_INFINITY;
        data[2] = Double.NEGATIVE_INFINITY;
        data[3] = Double.NEGATIVE_INFINITY;
    }

    public DoubleRectImpl(double minX, double minY, double maxX, double maxY) {
        double[] dd = this.data = new double[4];
        dd[0] = minX;
        dd[1] = minY;
        dd[2] = maxX;
        dd[3] = maxY;
    }

    public DoubleRectImpl(DoubleRectR src) {
        double[] dd = this.data = new double[4];
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
    public DoubleRect add(DoubleRectR other, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] otherData = ((DoubleRectImpl) other).data;
        double[] dd = ((DoubleRectImpl) dest).data;
        var _col0 = DoubleVector.fromArray(COL_SPECIES, otherData, 0).add(DoubleVector.fromArray(COL_SPECIES, sd, 0));
        _col0.intoArray(dd, 0);
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
    public DoubleRect add(double otherMinX, double otherMinY, double otherMaxX, double otherMaxY, @Mutated DoubleRect dest) {
        double[] sd = this.data;
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
    public DoubleRect negate(@Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        double _buf0 = -sd[2];
        double _buf1 = -sd[3];
        dd[2] = -sd[0];
        dd[3] = -sd[1];
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public DoubleRect sub(DoubleRectR other, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] otherData = ((DoubleRectImpl) other).data;
        double[] dd = ((DoubleRectImpl) dest).data;
        var _col0 = DoubleVector.fromArray(COL_SPECIES, sd, 0).sub(DoubleVector.fromArray(COL_SPECIES, otherData, 0));
        _col0.intoArray(dd, 0);
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
    public DoubleRect sub(double otherMinX, double otherMinY, double otherMaxX, double otherMaxY, @Mutated DoubleRect dest) {
        double[] sd = this.data;
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
    @Mutated public DoubleRect set(DoubleRectR v) {
        double[] dd = this.data;
        double[] vData = ((DoubleRectImpl) v).data;
        var _col0 = DoubleVector.fromArray(COL_SPECIES, vData, 0);
        _col0.intoArray(dd, 0);
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
    @Mutated public DoubleRect set(double vMinX, double vMinY, double vMaxX, double vMaxY) {
        double[] dd = this.data;
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
    public DoubleRect setMax(Double2R max, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] maxData = ((Double2Impl) max).data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = maxData[0];
        dd[3] = maxData[1];
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
    public DoubleRect setMax(double maxX, double maxY, @Mutated DoubleRect dest) {
        double[] sd = this.data;
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
    public DoubleRect setMin(Double2R min, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] minData = ((Double2Impl) min).data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = minData[0];
        dd[1] = minData[1];
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
    public DoubleRect setMin(double minX, double minY, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = minX;
        dd[1] = minY;
        dd[2] = sd[2];
        dd[3] = sd[3];
        return dest;
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
        double[] sd = this.data;
        float[] dd = ((FloatRectImpl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        dd[3] = (float) (sd[3]);
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
        double[] sd = this.data;
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
        double[] sd = this.data;
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
                dd[0] = (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(sd[0])));
                dd[1] = (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(sd[1])));
                dd[2] = (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(sd[2])));
                dd[3] = (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, Math.round(sd[3])));
            }
            case HALF_AWAY_FROM_ZERO -> {
                dd[0] = (int) (Math.abs(sd[0] - Math.rint(sd[0])) == 0.5 ? sd[0] + Math.copySign(0.5, sd[0]) : Math.rint(sd[0]));
                dd[1] = (int) (Math.abs(sd[1] - Math.rint(sd[1])) == 0.5 ? sd[1] + Math.copySign(0.5, sd[1]) : Math.rint(sd[1]));
                dd[2] = (int) (Math.abs(sd[2] - Math.rint(sd[2])) == 0.5 ? sd[2] + Math.copySign(0.5, sd[2]) : Math.rint(sd[2]));
                dd[3] = (int) (Math.abs(sd[3] - Math.rint(sd[3])) == 0.5 ? sd[3] + Math.copySign(0.5, sd[3]) : Math.rint(sd[3]));
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
    public DoubleRect lerp(DoubleRectR other, double t, @Mutated DoubleRect dest) {
        if (SimdMath.USE_FMA) return lerp_fma(other, t, dest);
        return lerp_mulAdd(other, t, dest);
    }

    private DoubleRect lerp_fma(DoubleRectR other, double t, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] otherData = ((DoubleRectImpl) other).data;
        double[] dd = ((DoubleRectImpl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _col0 = DoubleVector.broadcast(COL_SPECIES, t).fma(DoubleVector.fromArray(COL_SPECIES, otherData, 0).sub(_sv0), _sv0);
        _col0.intoArray(dd, 0);
        return dest;
    }

    private DoubleRect lerp_mulAdd(DoubleRectR other, double t, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] otherData = ((DoubleRectImpl) other).data;
        double[] dd = ((DoubleRectImpl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _col0 = DoubleVector.broadcast(COL_SPECIES, t).mul(DoubleVector.fromArray(COL_SPECIES, otherData, 0).sub(_sv0)).add(_sv0);
        _col0.intoArray(dd, 0);
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
    public DoubleRect lerp(double otherMinX, double otherMinY, double otherMaxX, double otherMaxY, double t, @Mutated DoubleRect dest) {
        if (SimdMath.USE_FMA) return lerp_fma(otherMinX, otherMinY, otherMaxX, otherMaxY, t, dest);
        return lerp_mulAdd(otherMinX, otherMinY, otherMaxX, otherMaxY, t, dest);
    }

    private DoubleRect lerp_fma(double otherMinX, double otherMinY, double otherMaxX, double otherMaxY, double t, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _col0 = DoubleVector.broadcast(COL_SPECIES, t).fma(DoubleVector.zero(COL_SPECIES).withLane(0, otherMinX).withLane(1, otherMinY).withLane(2, otherMaxX).withLane(3, otherMaxY).sub(_sv0), _sv0);
        _col0.intoArray(dd, 0);
        return dest;
    }

    private DoubleRect lerp_mulAdd(double otherMinX, double otherMinY, double otherMaxX, double otherMaxY, double t, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _col0 = DoubleVector.broadcast(COL_SPECIES, t).mul(DoubleVector.zero(COL_SPECIES).withLane(0, otherMinX).withLane(1, otherMinY).withLane(2, otherMaxX).withLane(3, otherMaxY).sub(_sv0)).add(_sv0);
        _col0.intoArray(dd, 0);
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
    public DoubleRect correctBounds(@Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        double _buf0 = Math.min(sd[0], sd[2]);
        double _buf1 = Math.min(sd[1], sd[3]);
        dd[2] = Math.max(sd[0], sd[2]);
        dd[3] = Math.max(sd[1], sd[3]);
        dd[0] = _buf0;
        dd[1] = _buf1;
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
    public DoubleRect expand(double margin, @Mutated DoubleRect dest) {
        double[] sd = this.data;
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
    public DoubleRect intersect(DoubleRectR other, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] otherData = ((DoubleRectImpl) other).data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = Math.max(sd[0], otherData[0]);
        dd[1] = Math.max(sd[1], otherData[1]);
        dd[2] = Math.min(sd[2], otherData[2]);
        dd[3] = Math.min(sd[3], otherData[3]);
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
    public DoubleRect intersect(double otherMinX, double otherMinY, double otherMaxX, double otherMaxY, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = Math.max(sd[0], otherMinX);
        dd[1] = Math.max(sd[1], otherMinY);
        dd[2] = Math.min(sd[2], otherMaxX);
        dd[3] = Math.min(sd[3], otherMaxY);
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
    public DoubleRect scale(double sx, double sy, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        double _t0 = sx * sd[0];
        double _t1 = sx * sd[2];
        double _t2 = sy * sd[1];
        double _t3 = sy * sd[3];
        dd[0] = Math.min(_t0, _t1);
        dd[1] = Math.min(_t2, _t3);
        dd[2] = Math.max(_t0, _t1);
        dd[3] = Math.max(_t2, _t3);
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
    public DoubleRect translate(Double2R delta, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] deltaData = ((Double2Impl) delta).data;
        double[] dd = ((DoubleRectImpl) dest).data;
        var _col0 = DoubleVector.broadcast(COL_SPECIES, deltaData[0]).blend(DoubleVector.broadcast(COL_SPECIES, deltaData[1]), MASK_0).add(DoubleVector.fromArray(COL_SPECIES, sd, 0));
        _col0.intoArray(dd, 0);
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
    public DoubleRect translate(double deltaX, double deltaY, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        var _col0 = DoubleVector.broadcast(COL_SPECIES, deltaX).blend(DoubleVector.broadcast(COL_SPECIES, deltaY), MASK_0).add(DoubleVector.fromArray(COL_SPECIES, sd, 0));
        _col0.intoArray(dd, 0);
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
    public DoubleRect union(DoubleRectR other, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] otherData = ((DoubleRectImpl) other).data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = Math.min(sd[0], otherData[0]);
        dd[1] = Math.min(sd[1], otherData[1]);
        dd[2] = Math.max(sd[2], otherData[2]);
        dd[3] = Math.max(sd[3], otherData[3]);
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
    public DoubleRect union(double otherMinX, double otherMinY, double otherMaxX, double otherMaxY, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = Math.min(sd[0], otherMinX);
        dd[1] = Math.min(sd[1], otherMinY);
        dd[2] = Math.max(sd[2], otherMaxX);
        dd[3] = Math.max(sd[3], otherMaxY);
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
    public DoubleRect union(Double2R p, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] pData = ((Double2Impl) p).data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = Math.min(sd[0], pData[0]);
        dd[1] = Math.min(sd[1], pData[1]);
        dd[2] = Math.max(sd[2], pData[0]);
        dd[3] = Math.max(sd[3], pData[1]);
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
    public DoubleRect union(double pX, double pY, @Mutated DoubleRect dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = Math.min(sd[0], pX);
        dd[1] = Math.min(sd[1], pY);
        dd[2] = Math.max(sd[2], pX);
        dd[3] = Math.max(sd[3], pY);
        return dest;
    }


    /**
     * Compute the area of this rectangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the area of this rectangle
     */
    public double area() {
        double[] sd = this.data;
        return Math.max(0.0, sd[2] - sd[0]) * Math.max(0.0, sd[3] - sd[1]);
    }


    /**
     * Compute the x coordinate of the center of this rectangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the x coordinate of the center of this rectangle
     */
    public double centerX() {
        double[] sd = this.data;
        return 0.5 * sd[0] + 0.5 * sd[2];
    }


    /**
     * Compute the y coordinate of the center of this rectangle.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the y coordinate of the center of this rectangle
     */
    public double centerY() {
        double[] sd = this.data;
        return 0.5 * sd[1] + 0.5 * sd[3];
    }


    /**
     * Compute the point of this rectangle closest to the given point, i.e. the point clamped per
     * axis into the rectangle's bounds. For a point inside or on the rectangle, the result is the
     * point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Double2 closestPointToPoint(Double2R p, @Mutated Double2 dest) {
        return closestPointToPoint(p.x(), p.y(), dest);
    }


    /**
     * Compute the point of this rectangle closest to the given point, i.e. the point clamped per
     * axis into the rectangle's bounds. For a point inside or on the rectangle, the result is the
     * point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY)} to find the closest point to
     * @param pY the {@code y} component of the point {@code (pX, pY)} to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Double2 closestPointToPoint(double pX, double pY, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = Math.max(sd[0], Math.min(pX, sd[2]));
        dd[1] = Math.max(sd[1], Math.min(pY, sd[3]));
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
    public boolean containsPoint(Double2R p) {
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
    public boolean containsPoint(double pX, double pY) {
        double[] sd = this.data;
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
    public boolean containsRect(DoubleRectR o) {
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
    public boolean containsRect(double oMinX, double oMinY, double oMaxX, double oMaxY) {
        double[] sd = this.data;
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
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this rectangle and the given point, i.e. the squared
     *        length of the difference between the point and its per-axis clamp into the rectangle's
     *        bounds; zero for a point inside or on the rectangle
     */
    public double distanceSquaredToPoint(Double2R p) {
        return distanceSquaredToPoint(p.x(), p.y());
    }


    /**
     * Compute the squared distance between this rectangle and the given point, i.e. the squared
     * length of the difference between the point and its per-axis clamp into the rectangle's
     * bounds; zero for a point inside or on the rectangle.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY)} to measure the distance to
     * @param pY the {@code y} component of the point {@code (pX, pY)} to measure the distance to
     * @return the squared distance between this rectangle and the given point, i.e. the squared
     *        length of the difference between the point and its per-axis clamp into the rectangle's
     *        bounds; zero for a point inside or on the rectangle
     */
    public double distanceSquaredToPoint(double pX, double pY) {
        double[] sd = this.data;
        double _t4 = pX - Math.max(sd[0], Math.min(pX, sd[2]));
        double _t5 = pY - Math.max(sd[1], Math.min(pY, sd[3]));
        return Math.fma(_t4, _t4, _t5 * _t5);
    }


    /**
     * Compute the squared distance between this rectangle and the given rectangle, i.e. the squared
     * length of the shortest vector between any two points of the two rectangles; zero when they
     * overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component; the minimum corner of {@code other} must not exceed the maximum corner of
     * {@code other} in any component.
     *
     * @param other the rectangle to measure the distance to
     * @return the squared distance between this rectangle and the given rectangle, i.e. the squared
     *        length of the shortest vector between any two points of the two rectangles; zero when
     *        they overlap or touch
     */
    public double distanceSquaredToRect(DoubleRectR other) {
        return distanceSquaredToRect(other.minX(), other.minY(), other.maxX(), other.maxY());
    }


    /**
     * Compute the squared distance between this rectangle and the given rectangle, i.e. the squared
     * length of the shortest vector between any two points of the two rectangles; zero when they
     * overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this rectangle must not exceed the maximum corner of this rectangle in any
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
    public double distanceSquaredToRect(double otherMinX, double otherMinY, double otherMaxX, double otherMaxY) {
        double[] sd = this.data;
        double _t6 = Math.max(0.0, Math.max(sd[0] - otherMaxX, otherMinX - sd[2]));
        double _t7 = Math.max(0.0, Math.max(sd[1] - otherMaxY, otherMinY - sd[3]));
        return Math.fma(_t6, _t6, _t7 * _t7);
    }


    /**
     * Compute the distance between this rectangle and the given point, i.e. the length of the
     * difference between the point and its per-axis clamp into the rectangle's bounds; zero for a
     * point inside or on the rectangle.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param p the point to measure the distance to
     * @return the distance between this rectangle and the given point, i.e. the length of the
     *        difference between the point and its per-axis clamp into the rectangle's bounds; zero
     *        for a point inside or on the rectangle
     */
    public double distanceToPoint(Double2R p) {
        return distanceToPoint(p.x(), p.y());
    }


    /**
     * Compute the distance between this rectangle and the given point, i.e. the length of the
     * difference between the point and its per-axis clamp into the rectangle's bounds; zero for a
     * point inside or on the rectangle.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY)} to measure the distance to
     * @param pY the {@code y} component of the point {@code (pX, pY)} to measure the distance to
     * @return the distance between this rectangle and the given point, i.e. the length of the
     *        difference between the point and its per-axis clamp into the rectangle's bounds; zero
     *        for a point inside or on the rectangle
     */
    public double distanceToPoint(double pX, double pY) {
        double[] sd = this.data;
        double _t4 = pX - Math.max(sd[0], Math.min(pX, sd[2]));
        double _t5 = pY - Math.max(sd[1], Math.min(pY, sd[3]));
        return Math.sqrt(Math.fma(_t4, _t4, _t5 * _t5));
    }


    /**
     * Compute the distance between this rectangle and the given rectangle, i.e. the length of the
     * shortest vector between any two points of the two rectangles; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this rectangle must not exceed the maximum corner of this rectangle in any
     * component; the minimum corner of {@code other} must not exceed the maximum corner of
     * {@code other} in any component.
     *
     * @param other the rectangle to measure the distance to
     * @return the distance between this rectangle and the given rectangle, i.e. the length of the
     *        shortest vector between any two points of the two rectangles; zero when they overlap
     *        or touch
     */
    public double distanceToRect(DoubleRectR other) {
        return distanceToRect(other.minX(), other.minY(), other.maxX(), other.maxY());
    }


    /**
     * Compute the distance between this rectangle and the given rectangle, i.e. the length of the
     * shortest vector between any two points of the two rectangles; zero when they overlap or
     * touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this rectangle must not exceed the maximum corner of this rectangle in any
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
    public double distanceToRect(double otherMinX, double otherMinY, double otherMaxX, double otherMaxY) {
        double[] sd = this.data;
        double _t6 = Math.max(0.0, Math.max(sd[0] - otherMaxX, otherMinX - sd[2]));
        double _t7 = Math.max(0.0, Math.max(sd[1] - otherMaxY, otherMinY - sd[3]));
        return Math.sqrt(Math.fma(_t6, _t6, _t7 * _t7));
    }


    /**
     * Get the center of this rectangle and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2 getCenter(@Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = 0.5 * sd[0] + 0.5 * sd[2];
        dd[1] = 0.5 * sd[1] + 0.5 * sd[3];
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
    public Double2 getMax(@Mutated Double2 dest) {
        double[] sd = this.data;
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
    public Double2 getMin(@Mutated Double2 dest) {
        double[] sd = this.data;
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
    public Double2 getSize(@Mutated Double2 dest) {
        double[] sd = this.data;
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
    public double height() {
        double[] sd = this.data;
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
    public boolean intersectsRect(DoubleRectR o) {
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
    public boolean intersectsRect(double oMinX, double oMinY, double oMaxX, double oMaxY) {
        double[] sd = this.data;
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
        double[] sd = this.data;
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
    public double width() {
        double[] sd = this.data;
        return sd[2] - sd[0];
    }

    public double minX() { return data[0]; }
    public double minY() { return data[1]; }
    public double maxX() { return data[2]; }
    public double maxY() { return data[3]; }

    @Override public String toString() {
        return "DoubleRect(" + minX() + ", " + minY() + ", " + maxX() + ", " + maxY() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleRectImpl)) return false;
        DoubleRectImpl o = (DoubleRectImpl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Double.isFinite(data[0])
            && Double.isFinite(data[1])
            && Double.isFinite(data[2])
            && Double.isFinite(data[3]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1])
            || Double.isNaN(data[2])
            || Double.isNaN(data[3]);
    }

    @Override public boolean equalsEpsilon(DoubleRectR other, double epsilon) {
        return Math.abs(data[0] - other.minX()) <= epsilon
            && Math.abs(data[1] - other.minY()) <= epsilon
            && Math.abs(data[2] - other.maxX()) <= epsilon
            && Math.abs(data[3] - other.maxY()) <= epsilon;
    }

    public double[] store(@Mutated double[] dest, int offset) {
        double[] d = this.data;
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoArray(dest, offset);
        return dest;
    }
    public @Mutated DoubleRect load(double[] src, int offset) {
        double[] d = this.data;
        DoubleVector.fromArray(COL_SPECIES, src, offset).intoArray(d, 0);
        return this;
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        double[] d = this.data;
        if (buf.hasArray()) {
            double[] arr = buf.array();
            int off = buf.arrayOffset() + index;
            DoubleVector.fromArray(COL_SPECIES, d, 0).intoArray(arr, off);
        } else if (buf.order() != ByteOrder.nativeOrder() || buf.isReadOnly()) {
            return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
        } else {
            MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
            long baseOff = (long) index * 8;
            DoubleVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(seg, baseOff, ByteOrder.nativeOrder());
        }
        return buf;
    }
    @Mutated public DoubleRect loadAbsolute(int index, DoubleBuffer buf) {
        double[] d = this.data;
        if (buf.hasArray()) {
            double[] arr = buf.array();
            int off = buf.arrayOffset() + index;
            DoubleVector.fromArray(COL_SPECIES, arr, off).intoArray(d, 0);
        } else if (buf.order() != ByteOrder.nativeOrder()) {
            return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
        } else {
            MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
            long baseOff = (long) index * 8;
            DoubleVector.fromMemorySegment(COL_SPECIES, seg, baseOff, ByteOrder.nativeOrder()).intoArray(d, 0);
        }
        return this;
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        if (buf.order() != ByteOrder.nativeOrder() || buf.isReadOnly()) return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
        double[] d = this.data;
        MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(seg, index, ByteOrder.nativeOrder());
        return buf;
    }
    public DoubleRect loadAbsolute(int index, ByteBuffer buf) {
        if (buf.order() != ByteOrder.nativeOrder()) return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
        double[] d = this.data;
        MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
        DoubleVector.fromMemorySegment(COL_SPECIES, seg, index, ByteOrder.nativeOrder()).intoArray(d, 0);
        return this;
    }
    public DoubleRect storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public DoubleRect loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }
    public MemorySegment store(long offset, MemorySegment dest) {
        double[] d = this.data;
        MemorySegment seg = dest;
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(seg, offset, ByteOrder.nativeOrder());
        return dest;
    }
    public DoubleRect load(long offset, MemorySegment src) {
        double[] d = this.data;
        MemorySegment seg = src;
        DoubleVector.fromMemorySegment(COL_SPECIES, seg, offset, ByteOrder.nativeOrder()).intoArray(d, 0);
        return this;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        return dest;
    }
    public @Mutated DoubleRect load(float[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        return this;
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    @Mutated public DoubleRect loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public DoubleRect loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public DoubleRect storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public DoubleRect loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }
    public MemorySegment storeFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeFloat(this, offset, dest);
    }
    public DoubleRect loadFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadFloat(this, offset, src);
    }

    private static final VectorSpecies<Double> COL_SPECIES = DoubleVector.SPECIES_256;
    private static final VectorMask<Double> MASK_0 = VectorMask.fromValues(COL_SPECIES, false, true, false, true);

}
