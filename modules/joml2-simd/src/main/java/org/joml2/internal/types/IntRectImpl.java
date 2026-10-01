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
import java.nio.IntBuffer;
import java.nio.LongBuffer;

/**
 * Generated implementation of {@link IntRect} backed by a {@code int[]} array, with Vector API SIMD
 * kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class IntRectImpl implements IntRect {

    public int[] data;

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
        data = new int[4];
        data[0] = Integer.MAX_VALUE;
        data[1] = Integer.MAX_VALUE;
        data[2] = Integer.MIN_VALUE;
        data[3] = Integer.MIN_VALUE;
    }

    public IntRectImpl(int minX, int minY, int maxX, int maxY) {
        int[] dd = this.data = new int[4];
        dd[0] = minX;
        dd[1] = minY;
        dd[2] = maxX;
        dd[3] = maxY;
    }

    public IntRectImpl(IntRectR src) {
        int[] dd = this.data = new int[4];
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
    public IntRect add(IntRectR other, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] otherData = ((IntRectImpl) other).data;
        int[] dd = ((IntRectImpl) dest).data;
        IntVector.fromArray(COL_SPECIES, otherData, 0).add(IntVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
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
        int[] sd = this.data;
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
    public IntRect add(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] dd = ((IntRectImpl) dest).data;
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
        int[] sd = this.data;
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
    public IntRect negate(@Mutated IntRect dest) {
        int[] sd = this.data;
        int[] dd = ((IntRectImpl) dest).data;
        int _buf0 = -sd[2];
        int _buf1 = -sd[3];
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect negate(@Mutated DoubleRect dest) {
        int[] sd = this.data;
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
    public IntRect sub(IntRectR other, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] otherData = ((IntRectImpl) other).data;
        int[] dd = ((IntRectImpl) dest).data;
        IntVector.fromArray(COL_SPECIES, sd, 0).sub(IntVector.fromArray(COL_SPECIES, otherData, 0)).intoArray(dd, 0);
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
        int[] sd = this.data;
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
    public IntRect sub(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] dd = ((IntRectImpl) dest).data;
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
        int[] sd = this.data;
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
    @Mutated public IntRect set(IntRectR v) {
        int[] dd = this.data;
        int[] vData = ((IntRectImpl) v).data;
        IntVector.fromArray(COL_SPECIES, vData, 0).intoArray(dd, 0);
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
        int[] dd = this.data;
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
    public IntRect setMax(Int2R max, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] maxData = ((Int2Impl) max).data;
        int[] dd = ((IntRectImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = maxData[0];
        dd[3] = maxData[1];
        return dest;
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
        int[] sd = this.data;
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
    public IntRect setMax(int maxX, int maxY, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] dd = ((IntRectImpl) dest).data;
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
        int[] sd = this.data;
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
    public IntRect setMin(Int2R min, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] minData = ((Int2Impl) min).data;
        int[] dd = ((IntRectImpl) dest).data;
        dd[0] = minData[0];
        dd[1] = minData[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        return dest;
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
        int[] sd = this.data;
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
    public IntRect setMin(int minX, int minY, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] dd = ((IntRectImpl) dest).data;
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
        int[] sd = this.data;
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
        int[] sd = this.data;
        float[] dd = ((FloatRectImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
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
        int[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
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
    public IntRect correctBounds(@Mutated IntRect dest) {
        int[] sd = this.data;
        int[] dd = ((IntRectImpl) dest).data;
        int _buf0 = java.lang.Math.min(sd[0], sd[2]);
        int _buf1 = java.lang.Math.min(sd[1], sd[3]);
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
     * The computation is performed at {@code int} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleRect correctBounds(@Mutated DoubleRect dest) {
        int[] sd = this.data;
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
    public IntRect expand(int margin, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] dd = ((IntRectImpl) dest).data;
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
        int[] sd = this.data;
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
    public IntRect intersect(IntRectR other, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] otherData = ((IntRectImpl) other).data;
        int[] dd = ((IntRectImpl) dest).data;
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
        int[] sd = this.data;
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
    public IntRect intersect(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] dd = ((IntRectImpl) dest).data;
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
        int[] sd = this.data;
        double[] dd = ((DoubleRectImpl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], otherMinX);
        dd[1] = java.lang.Math.max(sd[1], otherMinY);
        dd[2] = java.lang.Math.min(sd[2], otherMaxX);
        dd[3] = java.lang.Math.min(sd[3], otherMaxY);
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
    public IntRect translate(Int2R delta, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] deltaData = ((Int2Impl) delta).data;
        int[] dd = ((IntRectImpl) dest).data;
        IntVector.broadcast(COL_SPECIES, deltaData[0]).blend(IntVector.broadcast(COL_SPECIES, deltaData[1]), MASK_0).add(IntVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
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
        int[] sd = this.data;
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
    public IntRect translate(int deltaX, int deltaY, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] dd = ((IntRectImpl) dest).data;
        IntVector.broadcast(COL_SPECIES, deltaX).blend(IntVector.broadcast(COL_SPECIES, deltaY), MASK_0).add(IntVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        return dest;
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
        int[] sd = this.data;
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
    public IntRect union(IntRectR other, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] otherData = ((IntRectImpl) other).data;
        int[] dd = ((IntRectImpl) dest).data;
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
        int[] sd = this.data;
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
    public IntRect union(int otherMinX, int otherMinY, int otherMaxX, int otherMaxY, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] dd = ((IntRectImpl) dest).data;
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
        int[] sd = this.data;
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
    public IntRect union(Int2R p, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] pData = ((Int2Impl) p).data;
        int[] dd = ((IntRectImpl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], pData[0]);
        dd[1] = java.lang.Math.min(sd[1], pData[1]);
        dd[2] = java.lang.Math.max(sd[2], pData[0]);
        dd[3] = java.lang.Math.max(sd[3], pData[1]);
        return dest;
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
        int[] sd = this.data;
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
    public IntRect union(int pX, int pY, @Mutated IntRect dest) {
        int[] sd = this.data;
        int[] dd = ((IntRectImpl) dest).data;
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
        int[] sd = this.data;
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
     * The value is computed and returned as a {@code long}, so it does not wrap at the {@code int}
     * range; it is exact as long as it fits in a {@code long}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the area of this rectangle
     */
    public long area() {
        int[] sd = this.data;
        return java.lang.Math.max(0L, (long) sd[2] - sd[0]) * java.lang.Math.max(0L, (long) sd[3] - sd[1]);
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
        int[] sd = this.data;
        int _t1 = sd[0] ^ sd[2];
        int _t3 = (sd[0] & sd[2]) + (_t1 >> 1);
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
        int[] sd = this.data;
        int _t1 = sd[1] ^ sd[3];
        int _t3 = (sd[1] & sd[3]) + (_t1 >> 1);
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
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
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
        int[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
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
        int[] sd = this.data;
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
     * Valid input: the minimum corner of this rectangle must not exceed the maximum corner of this
     * rectangle in any component.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY)} to find the closest point to
     * @param pY the {@code y} component of the point {@code (pX, pY)} to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Int2 closestPointToPoint(int pX, int pY, @Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
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
        int[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
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
        int[] sd = this.data;
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
    public boolean containsPoint(Int2R p) {
        int pX = p.x();
        int pY = p.y();
        int[] sd = this.data;
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
    public boolean containsPoint(int pX, int pY) {
        int[] sd = this.data;
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
    public boolean containsRect(IntRectR o) {
        int[] sd = this.data;
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
    public boolean containsRect(int oMinX, int oMinY, int oMaxX, int oMaxY) {
        int[] sd = this.data;
        if (!(sd[0] <= oMinX)) return false;
        if (!(sd[2] >= oMaxX)) return false;
        if (!(sd[1] <= oMinY)) return false;
        return sd[3] >= oMaxY;
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
        int[] sd = this.data;
        long _t4 = (long) pX - java.lang.Math.max((long) sd[0], java.lang.Math.min((long) pX, (long) sd[2]));
        long _t5 = (long) pY - java.lang.Math.max((long) sd[1], java.lang.Math.min((long) pY, (long) sd[3]));
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
        int[] sd = this.data;
        long _t4 = (long) pX - java.lang.Math.max((long) sd[0], java.lang.Math.min((long) pX, (long) sd[2]));
        long _t5 = (long) pY - java.lang.Math.max((long) sd[1], java.lang.Math.min((long) pY, (long) sd[3]));
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
        int[] sd = this.data;
        long _t6 = java.lang.Math.max(0L, java.lang.Math.max((long) sd[0] - other.maxX(), (long) other.minX() - sd[2]));
        long _t7 = java.lang.Math.max(0L, java.lang.Math.max((long) sd[1] - other.maxY(), (long) other.minY() - sd[3]));
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
        int[] sd = this.data;
        long _t6 = java.lang.Math.max(0L, java.lang.Math.max((long) sd[0] - otherMaxX, (long) otherMinX - sd[2]));
        long _t7 = java.lang.Math.max(0L, java.lang.Math.max((long) sd[1] - otherMaxY, (long) otherMinY - sd[3]));
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
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        int _t1 = sd[0] ^ sd[2];
        int _t3 = sd[1] ^ sd[3];
        int _t6 = (sd[0] & sd[2]) + (_t1 >> 1);
        int _t7 = (sd[1] & sd[3]) + (_t3 >> 1);
        dd[0] = _t6 < 0 ? _t6 + (_t1 & 1) : _t6;
        dd[1] = _t7 < 0 ? _t7 + (_t3 & 1) : _t7;
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        int _t1 = sd[0] ^ sd[2];
        int _t3 = sd[1] ^ sd[3];
        int _t6 = (sd[0] & sd[2]) + (_t1 >> 1);
        int _t7 = (sd[1] & sd[3]) + (_t3 >> 1);
        dd[0] = _t6 < 0 ? _t6 + (_t1 & 1) : _t6;
        dd[1] = _t7 < 0 ? _t7 + (_t3 & 1) : _t7;
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        int _t1 = sd[0] ^ sd[2];
        int _t3 = sd[1] ^ sd[3];
        int _t6 = (sd[0] & sd[2]) + (_t1 >> 1);
        int _t7 = (sd[1] & sd[3]) + (_t3 >> 1);
        dd[0] = _t6 < 0 ? _t6 + (_t1 & 1) : _t6;
        dd[1] = _t7 < 0 ? _t7 + (_t3 & 1) : _t7;
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
    public Int2 getMax(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        dd[0] = sd[2];
        dd[1] = sd[3];
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = sd[2];
        dd[1] = sd[3];
        return dest;
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
        int[] sd = this.data;
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
    public Int2 getMin(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        return dest;
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
        int[] sd = this.data;
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
    public Int2 getSize(@Mutated Int2 dest) {
        int[] sd = this.data;
        int[] dd = ((Int2Impl) dest).data;
        dd[0] = sd[2] - sd[0];
        dd[1] = sd[3] - sd[1];
        return dest;
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
        int[] sd = this.data;
        long[] dd = ((Long2Impl) dest).data;
        dd[0] = sd[2] - sd[0];
        dd[1] = sd[3] - sd[1];
        return dest;
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
        int[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        dd[0] = sd[2] - sd[0];
        dd[1] = sd[3] - sd[1];
        return dest;
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
        int[] sd = this.data;
        return (long) sd[3] - sd[1];
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
        int[] sd = this.data;
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
    public boolean intersectsRect(int oMinX, int oMinY, int oMaxX, int oMaxY) {
        int[] sd = this.data;
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
        int[] sd = this.data;
        if (!(sd[0] <= sd[2])) return false;
        return sd[1] <= sd[3];
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
        int[] sd = this.data;
        return (long) sd[2] - sd[0];
    }

    public int minX() { return data[0]; }
    public int minY() { return data[1]; }
    public int maxX() { return data[2]; }
    public int maxY() { return data[3]; }

    @Override public String toString() {
        return "IntRect(" + minX() + ", " + minY() + ", " + maxX() + ", " + maxY() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IntRectImpl)) return false;
        IntRectImpl o = (IntRectImpl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return true;
    }

    @Override public boolean isNaN() {
        return false;
    }

    @Override public boolean equalsEpsilon(IntRectR other, int epsilon) {
        return java.lang.Math.abs((long) data[0] - other.minX()) <= epsilon
            && java.lang.Math.abs((long) data[1] - other.minY()) <= epsilon
            && java.lang.Math.abs((long) data[2] - other.maxX()) <= epsilon
            && java.lang.Math.abs((long) data[3] - other.maxY()) <= epsilon;
    }

    public int[] store(@Mutated int[] dest, int offset) {
        int[] d = this.data;
        IntVector.fromArray(COL_SPECIES, d, 0).intoArray(dest, offset);
        return dest;
    }
    public @Mutated IntRect load(int[] src, int offset) {
        int[] d = this.data;
        IntVector.fromArray(COL_SPECIES, src, offset).intoArray(d, 0);
        return this;
    }
    public IntBuffer storeAbsolute(int index, @Mutated IntBuffer buf) {
        if (!buf.hasArray()) return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
        int[] d = this.data;
        int[] arr = buf.array();
        int off = buf.arrayOffset() + index;
        IntVector.fromArray(COL_SPECIES, d, 0).intoArray(arr, off);
        return buf;
    }
    @Mutated public IntRect loadAbsolute(int index, IntBuffer buf) {
        if (!buf.hasArray()) return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
        int[] d = this.data;
        int[] arr = buf.array();
        int off = buf.arrayOffset() + index;
        IntVector.fromArray(COL_SPECIES, arr, off).intoArray(d, 0);
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
    public MemorySegment store(long offset, MemorySegment dest) {
        int[] d = this.data;
        IntVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(dest, offset, ByteOrder.nativeOrder());
        return dest;
    }
    public IntRect load(long offset, MemorySegment src) {
        int[] d = this.data;
        IntVector.fromMemorySegment(COL_SPECIES, src, offset, ByteOrder.nativeOrder()).intoArray(d, 0);
        return this;
    }

    public long[] store(@Mutated long[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        return dest;
    }
    public @Mutated IntRect load(long[] src, int offset) {
        this.data[0] = (int) src[offset];
        this.data[1] = (int) src[offset + 1];
        this.data[2] = (int) src[offset + 2];
        this.data[3] = (int) src[offset + 3];
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

    private static final VectorSpecies<Integer> COL_SPECIES = IntVector.SPECIES_128;
    private static final VectorMask<Integer> MASK_0 = VectorMask.fromValues(COL_SPECIES, false, true, false, true);
}
