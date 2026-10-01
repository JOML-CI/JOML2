// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link DoubleAABB} backed by a {@code double[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class DoubleAABBImpl implements DoubleAABB {

    public double[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final DoubleAABBBbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleAABBBbOpsUnsafe()
                        : new DoubleAABBBbOpsApi();
        static final DoubleAABBRawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new DoubleAABBRawOpsUnsafe()
                        : new DoubleAABBRawOpsApi();
    }

    public DoubleAABBImpl() {
        data = new double[6];
        data[0] = Double.POSITIVE_INFINITY;
        data[1] = Double.POSITIVE_INFINITY;
        data[2] = Double.POSITIVE_INFINITY;
        data[3] = Double.NEGATIVE_INFINITY;
        data[4] = Double.NEGATIVE_INFINITY;
        data[5] = Double.NEGATIVE_INFINITY;
    }

    public DoubleAABBImpl(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double[] dd = this.data = new double[6];
        dd[0] = minX;
        dd[1] = minY;
        dd[2] = minZ;
        dd[3] = maxX;
        dd[4] = maxY;
        dd[5] = maxZ;
    }

    public DoubleAABBImpl(DoubleAABBR src) {
        double[] dd = this.data = new double[6];
        dd[0] = src.minX();
        dd[1] = src.minY();
        dd[2] = src.minZ();
        dd[3] = src.maxX();
        dd[4] = src.maxY();
        dd[5] = src.maxZ();
    }


    /**
     * Set this axis-aligned bounding box to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the axis-aligned bounding box to copy
     * @return this
     */
    public @Mutated DoubleAABB set(DoubleAABBR v) {
        double minY = v.minY();
        double minZ = v.minZ();
        double maxX = v.maxX();
        double maxY = v.maxY();
        double maxZ = v.maxZ();
        double[] dd = this.data;
        dd[0] = v.minX();
        dd[1] = minY;
        dd[2] = minZ;
        dd[3] = maxX;
        dd[4] = maxY;
        dd[5] = maxZ;
        return this;
    }


    /**
     * Set this axis-aligned bounding box to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param minY the {@code minY} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param minZ the {@code minZ} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxX the {@code maxX} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxY the {@code maxY} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxZ the {@code maxZ} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @return this
     */
    @Mutated public DoubleAABB set(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double[] dd = this.data;
        dd[0] = minX;
        dd[1] = minY;
        dd[2] = minZ;
        dd[3] = maxX;
        dd[4] = maxY;
        dd[5] = maxZ;
        return this;
    }


    /**
     * Set the maximum corner of this axis-aligned bounding box to {@code max} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param max the maximum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB setMax(Double3R max, @Mutated DoubleAABB dest) {
        double maxX = max.x();
        double maxY = max.y();
        double maxZ = max.z();
        double[] sd = this.data;
        double[] dd = ((DoubleAABBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = maxX;
        dd[4] = maxY;
        dd[5] = maxZ;
        return dest;
    }


    /**
     * Set the maximum corner of this axis-aligned bounding box to ({@code maxX}, {@code maxY},
     * {@code maxZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY, maxZ)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY, maxZ)}
     * @param maxZ the {@code z} component of the vector {@code (maxX, maxY, maxZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB setMax(double maxX, double maxY, double maxZ, @Mutated DoubleAABB dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleAABBImpl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = maxX;
        dd[4] = maxY;
        dd[5] = maxZ;
        return dest;
    }


    /**
     * Set the minimum corner of this axis-aligned bounding box to {@code min} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param min the minimum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB setMin(Double3R min, @Mutated DoubleAABB dest) {
        double minY = min.y();
        double minZ = min.z();
        double[] sd = this.data;
        double[] dd = ((DoubleAABBImpl) dest).data;
        dd[0] = min.x();
        dd[1] = minY;
        dd[2] = minZ;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        return dest;
    }


    /**
     * Set the minimum corner of this axis-aligned bounding box to ({@code minX}, {@code minY},
     * {@code minZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY, minZ)}
     * @param minY the {@code y} component of the vector {@code (minX, minY, minZ)}
     * @param minZ the {@code z} component of the vector {@code (minX, minY, minZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB setMin(double minX, double minY, double minZ, @Mutated DoubleAABB dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleAABBImpl) dest).data;
        dd[0] = minX;
        dd[1] = minY;
        dd[2] = minZ;
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        return dest;
    }


    /**
     * Convert this axis-aligned bounding box to {@code float} precision and store the result in
     * {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public FloatAABB toFloat(@Mutated FloatAABB dest) {
        double[] sd = this.data;
        float[] dd = ((FloatAABBImpl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        dd[3] = (float) (sd[3]);
        dd[4] = (float) (sd[4]);
        dd[5] = (float) (sd[5]);
        return dest;
    }


    /**
     * Swap the minimum and maximum bounds of this axis-aligned bounding box where necessary so the
     * bounds are valid and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB correctBounds(@Mutated DoubleAABB dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleAABBImpl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        dd[0] = java.lang.Math.min(_rd0, _rd3);
        dd[1] = java.lang.Math.min(_rd1, _rd4);
        dd[2] = java.lang.Math.min(_rd2, _rd5);
        dd[3] = java.lang.Math.max(_rd0, _rd3);
        dd[4] = java.lang.Math.max(_rd1, _rd4);
        dd[5] = java.lang.Math.max(_rd2, _rd5);
        return dest;
    }


    /**
     * Transform this axis-aligned bounding box by {@code m} and set it to the axis-aligned box
     * enclosing the transformed box and store the result in {@code dest}.
     * <p>
     * Valid input: the minimum corner of this axis-aligned bounding box must not exceed the maximum
     * corner of this axis-aligned bounding box in any component.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB transform(Double3x4R m, @Mutated DoubleAABB dest) {
        if (Math.useFma()) return transform_fma(m, dest);
        return transform_mulAdd(m, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleAABB transform_fma(Double3x4R m, @Mutated DoubleAABB dest) {
        double[] sd = this.data;
        double[] mData = ((Double3x4Impl) m).data;
        double _t9 = sd[3] - sd[0];
        double _t10 = sd[4] - sd[1];
        double _t11 = sd[5] - sd[2];
        double _t12 = sd[2] + sd[5];
        double _t13 = sd[0] + sd[3];
        double _t14 = sd[1] + sd[4];
        return transform_s50dd0e65_1_fma(dest, mData, ((DoubleAABBImpl) dest).data, _t9, _t10, _t11, mData[2] * _t12, mData[6] * _t12, mData[10] * _t12, java.lang.Math.fma(mData[0], _t13, mData[1] * _t14), java.lang.Math.fma(mData[4], _t13, mData[5] * _t14), java.lang.Math.fma(mData[8], _t13, mData[9] * _t14), java.lang.Math.fma(_t11, java.lang.Math.abs(mData[2]), java.lang.Math.fma(_t9, java.lang.Math.abs(mData[0]), _t10 * java.lang.Math.abs(mData[1]))), java.lang.Math.fma(_t11, java.lang.Math.abs(mData[6]), java.lang.Math.fma(_t9, java.lang.Math.abs(mData[4]), _t10 * java.lang.Math.abs(mData[5]))), java.lang.Math.fma(_t11, java.lang.Math.abs(mData[10]), java.lang.Math.fma(_t9, java.lang.Math.abs(mData[8]), _t10 * java.lang.Math.abs(mData[9]))));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleAABB transform_mulAdd(Double3x4R m, @Mutated DoubleAABB dest) {
        double[] sd = this.data;
        double[] mData = ((Double3x4Impl) m).data;
        double _t9 = sd[3] - sd[0];
        double _t10 = sd[4] - sd[1];
        double _t11 = sd[5] - sd[2];
        double _t12 = sd[2] + sd[5];
        double _t13 = sd[0] + sd[3];
        double _t14 = sd[1] + sd[4];
        return transform_s50dd0e65_1_mulAdd(dest, mData, ((DoubleAABBImpl) dest).data, _t9, _t10, _t11, mData[2] * _t12, mData[6] * _t12, mData[10] * _t12, ((mData[0]) * (_t13) + (mData[1] * _t14)), ((mData[4]) * (_t13) + (mData[5] * _t14)), ((mData[8]) * (_t13) + (mData[9] * _t14)), ((_t11) * (java.lang.Math.abs(mData[2])) + (((_t9) * (java.lang.Math.abs(mData[0])) + (_t10 * java.lang.Math.abs(mData[1]))))), ((_t11) * (java.lang.Math.abs(mData[6])) + (((_t9) * (java.lang.Math.abs(mData[4])) + (_t10 * java.lang.Math.abs(mData[5]))))), ((_t11) * (java.lang.Math.abs(mData[10])) + (((_t9) * (java.lang.Math.abs(mData[8])) + (_t10 * java.lang.Math.abs(mData[9]))))));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleAABB transform_s50dd0e65_1_fma(DoubleAABB dest, double[] mData, double[] dd, double _t9, double _t10, double _t11, double _t18, double _t20, double _t22, double _t28, double _t29, double _t30, double _t35, double _t36, double _t37) {
        if (java.lang.Math.min(java.lang.Math.min(0.5 * _t9, 0.5 * _t10), 0.5 * _t11) < 0.0) {
            dd[0] = Double.POSITIVE_INFINITY;
            dd[1] = Double.POSITIVE_INFINITY;
            dd[2] = Double.POSITIVE_INFINITY;
            dd[3] = Double.NEGATIVE_INFINITY;
            dd[4] = Double.NEGATIVE_INFINITY;
            dd[5] = Double.NEGATIVE_INFINITY;
        } else {
            dd[0] = java.lang.Math.fma(0.5, _t18, java.lang.Math.fma(0.5, _t28, java.lang.Math.fma(-0.5, _t35, mData[3])));
            dd[1] = java.lang.Math.fma(0.5, _t20, java.lang.Math.fma(0.5, _t29, java.lang.Math.fma(-0.5, _t36, mData[7])));
            dd[2] = java.lang.Math.fma(0.5, _t22, java.lang.Math.fma(0.5, _t30, java.lang.Math.fma(-0.5, _t37, mData[11])));
            dd[3] = java.lang.Math.fma(0.5, _t18, java.lang.Math.fma(0.5, _t28, java.lang.Math.fma(0.5, _t35, mData[3])));
            dd[4] = java.lang.Math.fma(0.5, _t20, java.lang.Math.fma(0.5, _t29, java.lang.Math.fma(0.5, _t36, mData[7])));
            dd[5] = java.lang.Math.fma(0.5, _t22, java.lang.Math.fma(0.5, _t30, java.lang.Math.fma(0.5, _t37, mData[11])));
        }
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleAABB transform_s50dd0e65_1_mulAdd(DoubleAABB dest, double[] mData, double[] dd, double _t9, double _t10, double _t11, double _t18, double _t20, double _t22, double _t28, double _t29, double _t30, double _t35, double _t36, double _t37) {
        if (java.lang.Math.min(java.lang.Math.min(0.5 * _t9, 0.5 * _t10), 0.5 * _t11) < 0.0) {
            dd[0] = Double.POSITIVE_INFINITY;
            dd[1] = Double.POSITIVE_INFINITY;
            dd[2] = Double.POSITIVE_INFINITY;
            dd[3] = Double.NEGATIVE_INFINITY;
            dd[4] = Double.NEGATIVE_INFINITY;
            dd[5] = Double.NEGATIVE_INFINITY;
        } else {
            dd[0] = ((0.5) * (_t18) + (((0.5) * (_t28) + (((-0.5) * (_t35) + (mData[3]))))));
            dd[1] = ((0.5) * (_t20) + (((0.5) * (_t29) + (((-0.5) * (_t36) + (mData[7]))))));
            dd[2] = ((0.5) * (_t22) + (((0.5) * (_t30) + (((-0.5) * (_t37) + (mData[11]))))));
            dd[3] = ((0.5) * (_t18) + (((0.5) * (_t28) + (((0.5) * (_t35) + (mData[3]))))));
            dd[4] = ((0.5) * (_t20) + (((0.5) * (_t29) + (((0.5) * (_t36) + (mData[7]))))));
            dd[5] = ((0.5) * (_t22) + (((0.5) * (_t30) + (((0.5) * (_t37) + (mData[11]))))));
        }
        return dest;
    }


    /**
     * Transform this axis-aligned bounding box by {@code m} and set it to the axis-aligned box
     * enclosing the transformed box and store the result in {@code dest}.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the minimum corner of this axis-aligned bounding box must not exceed the maximum
     * corner of this axis-aligned bounding box in any component.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB transform(Double4x4R m, @Mutated DoubleAABB dest) {
        if (Math.useFma()) return transform_fma(m, dest);
        return transform_mulAdd(m, dest);
    }

    /** {@code transform} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleAABB transform_fma(Double4x4R m, @Mutated DoubleAABB dest) {
        double[] sd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        double _t9 = sd[3] - sd[0];
        double _t10 = sd[4] - sd[1];
        double _t11 = sd[5] - sd[2];
        double _t12 = sd[2] + sd[5];
        double _t13 = sd[0] + sd[3];
        double _t14 = sd[1] + sd[4];
        return transform_s9749ea4a_1_fma(dest, mData, ((DoubleAABBImpl) dest).data, _t9, _t10, _t11, mData[8] * _t12, mData[9] * _t12, mData[10] * _t12, java.lang.Math.fma(mData[0], _t13, mData[4] * _t14), java.lang.Math.fma(mData[1], _t13, mData[5] * _t14), java.lang.Math.fma(mData[2], _t13, mData[6] * _t14), java.lang.Math.fma(_t11, java.lang.Math.abs(mData[8]), java.lang.Math.fma(_t9, java.lang.Math.abs(mData[0]), _t10 * java.lang.Math.abs(mData[4]))), java.lang.Math.fma(_t11, java.lang.Math.abs(mData[9]), java.lang.Math.fma(_t9, java.lang.Math.abs(mData[1]), _t10 * java.lang.Math.abs(mData[5]))), java.lang.Math.fma(_t11, java.lang.Math.abs(mData[10]), java.lang.Math.fma(_t9, java.lang.Math.abs(mData[2]), _t10 * java.lang.Math.abs(mData[6]))));
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleAABB transform_mulAdd(Double4x4R m, @Mutated DoubleAABB dest) {
        double[] sd = this.data;
        double[] mData = ((Double4x4Impl) m).data;
        double _t9 = sd[3] - sd[0];
        double _t10 = sd[4] - sd[1];
        double _t11 = sd[5] - sd[2];
        double _t12 = sd[2] + sd[5];
        double _t13 = sd[0] + sd[3];
        double _t14 = sd[1] + sd[4];
        return transform_s9749ea4a_1_mulAdd(dest, mData, ((DoubleAABBImpl) dest).data, _t9, _t10, _t11, mData[8] * _t12, mData[9] * _t12, mData[10] * _t12, ((mData[0]) * (_t13) + (mData[4] * _t14)), ((mData[1]) * (_t13) + (mData[5] * _t14)), ((mData[2]) * (_t13) + (mData[6] * _t14)), ((_t11) * (java.lang.Math.abs(mData[8])) + (((_t9) * (java.lang.Math.abs(mData[0])) + (_t10 * java.lang.Math.abs(mData[4]))))), ((_t11) * (java.lang.Math.abs(mData[9])) + (((_t9) * (java.lang.Math.abs(mData[1])) + (_t10 * java.lang.Math.abs(mData[5]))))), ((_t11) * (java.lang.Math.abs(mData[10])) + (((_t9) * (java.lang.Math.abs(mData[2])) + (_t10 * java.lang.Math.abs(mData[6]))))));
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleAABB transform_s9749ea4a_1_fma(DoubleAABB dest, double[] mData, double[] dd, double _t9, double _t10, double _t11, double _t18, double _t20, double _t22, double _t28, double _t29, double _t30, double _t35, double _t36, double _t37) {
        if (java.lang.Math.min(java.lang.Math.min(0.5 * _t9, 0.5 * _t10), 0.5 * _t11) < 0.0) {
            dd[0] = Double.POSITIVE_INFINITY;
            dd[1] = Double.POSITIVE_INFINITY;
            dd[2] = Double.POSITIVE_INFINITY;
            dd[3] = Double.NEGATIVE_INFINITY;
            dd[4] = Double.NEGATIVE_INFINITY;
            dd[5] = Double.NEGATIVE_INFINITY;
        } else {
            dd[0] = java.lang.Math.fma(0.5, _t18, java.lang.Math.fma(0.5, _t28, java.lang.Math.fma(-0.5, _t35, mData[12])));
            dd[1] = java.lang.Math.fma(0.5, _t20, java.lang.Math.fma(0.5, _t29, java.lang.Math.fma(-0.5, _t36, mData[13])));
            dd[2] = java.lang.Math.fma(0.5, _t22, java.lang.Math.fma(0.5, _t30, java.lang.Math.fma(-0.5, _t37, mData[14])));
            dd[3] = java.lang.Math.fma(0.5, _t18, java.lang.Math.fma(0.5, _t28, java.lang.Math.fma(0.5, _t35, mData[12])));
            dd[4] = java.lang.Math.fma(0.5, _t20, java.lang.Math.fma(0.5, _t29, java.lang.Math.fma(0.5, _t36, mData[13])));
            dd[5] = java.lang.Math.fma(0.5, _t22, java.lang.Math.fma(0.5, _t30, java.lang.Math.fma(0.5, _t37, mData[14])));
        }
        return dest;
    }

    /** Piece 2 of {@code transform}, split to fit the inline budget; reached only through it. */
    private DoubleAABB transform_s9749ea4a_1_mulAdd(DoubleAABB dest, double[] mData, double[] dd, double _t9, double _t10, double _t11, double _t18, double _t20, double _t22, double _t28, double _t29, double _t30, double _t35, double _t36, double _t37) {
        if (java.lang.Math.min(java.lang.Math.min(0.5 * _t9, 0.5 * _t10), 0.5 * _t11) < 0.0) {
            dd[0] = Double.POSITIVE_INFINITY;
            dd[1] = Double.POSITIVE_INFINITY;
            dd[2] = Double.POSITIVE_INFINITY;
            dd[3] = Double.NEGATIVE_INFINITY;
            dd[4] = Double.NEGATIVE_INFINITY;
            dd[5] = Double.NEGATIVE_INFINITY;
        } else {
            dd[0] = ((0.5) * (_t18) + (((0.5) * (_t28) + (((-0.5) * (_t35) + (mData[12]))))));
            dd[1] = ((0.5) * (_t20) + (((0.5) * (_t29) + (((-0.5) * (_t36) + (mData[13]))))));
            dd[2] = ((0.5) * (_t22) + (((0.5) * (_t30) + (((-0.5) * (_t37) + (mData[14]))))));
            dd[3] = ((0.5) * (_t18) + (((0.5) * (_t28) + (((0.5) * (_t35) + (mData[12]))))));
            dd[4] = ((0.5) * (_t20) + (((0.5) * (_t29) + (((0.5) * (_t36) + (mData[13]))))));
            dd[5] = ((0.5) * (_t22) + (((0.5) * (_t30) + (((0.5) * (_t37) + (mData[14]))))));
        }
        return dest;
    }


    /**
     * Translate this axis-aligned bounding box by {@code delta} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param delta the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB translate(Double3R delta, @Mutated DoubleAABB dest) {
        double deltaX = delta.x();
        double deltaY = delta.y();
        double deltaZ = delta.z();
        double[] sd = this.data;
        double[] dd = ((DoubleAABBImpl) dest).data;
        dd[0] = deltaX + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaZ + sd[2];
        dd[3] = deltaX + sd[3];
        dd[4] = deltaY + sd[4];
        dd[5] = deltaZ + sd[5];
        return dest;
    }


    /**
     * Translate this axis-aligned bounding box by ({@code deltaX}, {@code deltaY}, {@code deltaZ})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param deltaX the {@code x} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaY the {@code y} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param deltaZ the {@code z} component of the vector {@code (deltaX, deltaY, deltaZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB translate(double deltaX, double deltaY, double deltaZ, @Mutated DoubleAABB dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleAABBImpl) dest).data;
        dd[0] = deltaX + sd[0];
        dd[1] = deltaY + sd[1];
        dd[2] = deltaZ + sd[2];
        dd[3] = deltaX + sd[3];
        dd[4] = deltaY + sd[4];
        dd[5] = deltaZ + sd[5];
        return dest;
    }


    /**
     * Set this axis-aligned bounding box to the union of itself and {@code other} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the axis-aligned bounding box to include in the union
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB union(DoubleAABBR other, @Mutated DoubleAABB dest) {
        double minY = other.minY();
        double minZ = other.minZ();
        double maxX = other.maxX();
        double maxY = other.maxY();
        double maxZ = other.maxZ();
        double[] sd = this.data;
        double[] dd = ((DoubleAABBImpl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], other.minX());
        dd[1] = java.lang.Math.min(sd[1], minY);
        dd[2] = java.lang.Math.min(sd[2], minZ);
        dd[3] = java.lang.Math.max(sd[3], maxX);
        dd[4] = java.lang.Math.max(sd[4], maxY);
        dd[5] = java.lang.Math.max(sd[5], maxZ);
        return dest;
    }


    /**
     * Set this axis-aligned bounding box to the union of itself and ({@code minX}, {@code minY},
     * {@code minZ}, {@code maxX}, {@code maxY}, {@code maxZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param minX the {@code minX} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param minY the {@code minY} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param minZ the {@code minZ} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxX the {@code maxX} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxY the {@code maxY} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxZ the {@code maxZ} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB union(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, @Mutated DoubleAABB dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleAABBImpl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], minX);
        dd[1] = java.lang.Math.min(sd[1], minY);
        dd[2] = java.lang.Math.min(sd[2], minZ);
        dd[3] = java.lang.Math.max(sd[3], maxX);
        dd[4] = java.lang.Math.max(sd[4], maxY);
        dd[5] = java.lang.Math.max(sd[5], maxZ);
        return dest;
    }


    /**
     * Grow this axis-aligned bounding box to include the point {@code p} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p the point to include
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB union(Double3R p, @Mutated DoubleAABB dest) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        double[] sd = this.data;
        double[] dd = ((DoubleAABBImpl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], pX);
        dd[1] = java.lang.Math.min(sd[1], pY);
        dd[2] = java.lang.Math.min(sd[2], pZ);
        dd[3] = java.lang.Math.max(sd[3], pX);
        dd[4] = java.lang.Math.max(sd[4], pY);
        dd[5] = java.lang.Math.max(sd[5], pZ);
        return dest;
    }


    /**
     * Grow this axis-aligned bounding box to include the point ({@code pX}, {@code pY}, {@code pZ})
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param pX the {@code x} component of the vector {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the vector {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the vector {@code (pX, pY, pZ)}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleAABB union(double pX, double pY, double pZ, @Mutated DoubleAABB dest) {
        double[] sd = this.data;
        double[] dd = ((DoubleAABBImpl) dest).data;
        dd[0] = java.lang.Math.min(sd[0], pX);
        dd[1] = java.lang.Math.min(sd[1], pY);
        dd[2] = java.lang.Math.min(sd[2], pZ);
        dd[3] = java.lang.Math.max(sd[3], pX);
        dd[4] = java.lang.Math.max(sd[4], pY);
        dd[5] = java.lang.Math.max(sd[5], pZ);
        return dest;
    }


    /**
     * Compute the point of this axis-aligned bounding box closest to the given point, i.e. the
     * point clamped per axis into the box's bounds. For a point inside or on the box, the result is
     * the point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointToPoint(Double3R p, @Mutated Double3 dest) {
        double pY = p.y();
        double pZ = p.z();
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], java.lang.Math.min(p.x(), sd[3]));
        dd[1] = java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[4]));
        dd[2] = java.lang.Math.max(sd[2], java.lang.Math.min(pZ, sd[5]));
        return dest;
    }


    /**
     * Compute the point of this axis-aligned bounding box closest to the given point, i.e. the
     * point clamped per axis into the box's bounds. For a point inside or on the box, the result is
     * the point itself.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to find the closest point
     *        to
     * @param dest will hold the result
     * @return dest
     */
    public Double3 closestPointToPoint(double pX, double pY, double pZ, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = java.lang.Math.max(sd[0], java.lang.Math.min(pX, sd[3]));
        dd[1] = java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[4]));
        dd[2] = java.lang.Math.max(sd[2], java.lang.Math.min(pZ, sd[5]));
        return dest;
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given box, i.e.
     * the squared length of the shortest vector between any two points of the two boxes; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component; the minimum corner of {@code other} must not
     * exceed the maximum corner of {@code other} in any component.
     *
     * @param other the box to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given box, i.e.
     *        the squared length of the shortest vector between any two points of the two boxes;
     *        zero when they overlap or touch
     */
    public double distanceSquaredToAABB(DoubleAABBR other) {
        double minX = other.minX();
        double minY = other.minY();
        double minZ = other.minZ();
        double maxX = other.maxX();
        double maxY = other.maxY();
        double maxZ = other.maxZ();
        if (Math.useFma()) {
            double[] sd = this.data;
            double _t9 = java.lang.Math.max(0.0, java.lang.Math.max(sd[2] - maxZ, minZ - sd[5]));
            double _t10 = java.lang.Math.max(0.0, java.lang.Math.max(sd[0] - maxX, minX - sd[3]));
            double _t11 = java.lang.Math.max(0.0, java.lang.Math.max(sd[1] - maxY, minY - sd[4]));
            return java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        } else {
            double[] sd = this.data;
            double _t9 = java.lang.Math.max(0.0, java.lang.Math.max(sd[2] - maxZ, minZ - sd[5]));
            double _t10 = java.lang.Math.max(0.0, java.lang.Math.max(sd[0] - maxX, minX - sd[3]));
            double _t11 = java.lang.Math.max(0.0, java.lang.Math.max(sd[1] - maxY, minY - sd[4]));
            return ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        }
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given box, i.e.
     * the squared length of the shortest vector between any two points of the two boxes; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component; {@code (minX, minY, minZ)} must not exceed
     * {@code (maxX, maxY, maxZ)} in any component.
     *
     * @param minX the {@code minX} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minY the {@code minY} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minZ the {@code minZ} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxX the {@code maxX} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxY the {@code maxY} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxZ the {@code maxZ} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given box, i.e.
     *        the squared length of the shortest vector between any two points of the two boxes;
     *        zero when they overlap or touch
     */
    public double distanceSquaredToAABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (Math.useFma()) {
            double[] sd = this.data;
            double _t9 = java.lang.Math.max(0.0, java.lang.Math.max(sd[2] - maxZ, minZ - sd[5]));
            double _t10 = java.lang.Math.max(0.0, java.lang.Math.max(sd[0] - maxX, minX - sd[3]));
            double _t11 = java.lang.Math.max(0.0, java.lang.Math.max(sd[1] - maxY, minY - sd[4]));
            return java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        } else {
            double[] sd = this.data;
            double _t9 = java.lang.Math.max(0.0, java.lang.Math.max(sd[2] - maxZ, minZ - sd[5]));
            double _t10 = java.lang.Math.max(0.0, java.lang.Math.max(sd[0] - maxX, minX - sd[3]));
            double _t11 = java.lang.Math.max(0.0, java.lang.Math.max(sd[1] - maxY, minY - sd[4]));
            return ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        }
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given point, i.e.
     * the squared length of the difference between the point and its per-axis clamp into the box's
     * bounds; zero for a point inside or on the box.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given point, i.e.
     *        the squared length of the difference between the point and its per-axis clamp into the
     *        box's bounds; zero for a point inside or on the box
     */
    public double distanceSquaredToPoint(Double3R p) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        if (Math.useFma()) {
            double[] sd = this.data;
            double _t6 = pZ - java.lang.Math.max(sd[2], java.lang.Math.min(pZ, sd[5]));
            double _t7 = pX - java.lang.Math.max(sd[0], java.lang.Math.min(pX, sd[3]));
            double _t8 = pY - java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[4]));
            return java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8));
        } else {
            double[] sd = this.data;
            double _t6 = pZ - java.lang.Math.max(sd[2], java.lang.Math.min(pZ, sd[5]));
            double _t7 = pX - java.lang.Math.max(sd[0], java.lang.Math.min(pX, sd[3]));
            double _t8 = pY - java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[4]));
            return ((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))));
        }
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given point, i.e.
     * the squared length of the difference between the point and its per-axis clamp into the box's
     * bounds; zero for a point inside or on the box.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the squared distance between this axis-aligned bounding box and the given point, i.e.
     *        the squared length of the difference between the point and its per-axis clamp into the
     *        box's bounds; zero for a point inside or on the box
     */
    public double distanceSquaredToPoint(double pX, double pY, double pZ) {
        if (Math.useFma()) {
            double[] sd = this.data;
            double _t6 = pZ - java.lang.Math.max(sd[2], java.lang.Math.min(pZ, sd[5]));
            double _t7 = pX - java.lang.Math.max(sd[0], java.lang.Math.min(pX, sd[3]));
            double _t8 = pY - java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[4]));
            return java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8));
        } else {
            double[] sd = this.data;
            double _t6 = pZ - java.lang.Math.max(sd[2], java.lang.Math.min(pZ, sd[5]));
            double _t7 = pX - java.lang.Math.max(sd[0], java.lang.Math.min(pX, sd[3]));
            double _t8 = pY - java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[4]));
            return ((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))));
        }
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given sphere,
     * i.e. the square of the distance from the box to the sphere's center minus the radius, clamped
     * at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param sphere the sphere to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given sphere,
     *        i.e. the square of the distance from the box to the sphere's center minus the radius,
     *        clamped at zero; zero when they overlap or touch
     */
    public double distanceSquaredToSphere(DoubleSphereR sphere) {
        double sphereX = sphere.x();
        double sphereY = sphere.y();
        double sphereZ = sphere.z();
        double sphereR = sphere.r();
        if (Math.useFma()) {
            double[] sd = this.data;
            double _t6 = sphereZ - java.lang.Math.max(sd[2], java.lang.Math.min(sphereZ, sd[5]));
            double _t7 = sphereX - java.lang.Math.max(sd[0], java.lang.Math.min(sphereX, sd[3]));
            double _t8 = sphereY - java.lang.Math.max(sd[1], java.lang.Math.min(sphereY, sd[4]));
            double _t14 = java.lang.Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - sphereR);
            return _t14 * _t14;
        } else {
            double[] sd = this.data;
            double _t6 = sphereZ - java.lang.Math.max(sd[2], java.lang.Math.min(sphereZ, sd[5]));
            double _t7 = sphereX - java.lang.Math.max(sd[0], java.lang.Math.min(sphereX, sd[3]));
            double _t8 = sphereY - java.lang.Math.max(sd[1], java.lang.Math.min(sphereY, sd[4]));
            double _t14 = java.lang.Math.max(0.0, java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - sphereR);
            return _t14 * _t14;
        }
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given sphere,
     * i.e. the square of the distance from the box to the sphere's center minus the radius, clamped
     * at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param sphereX the {@code x} component of the sphere
     *        {@code (sphereX, sphereY, sphereZ, sphereR)} to measure the distance to
     * @param sphereY the {@code y} component of the sphere
     *        {@code (sphereX, sphereY, sphereZ, sphereR)} to measure the distance to
     * @param sphereZ the {@code z} component of the sphere
     *        {@code (sphereX, sphereY, sphereZ, sphereR)} to measure the distance to
     * @param sphereR the {@code r} component of the sphere
     *        {@code (sphereX, sphereY, sphereZ, sphereR)} to measure the distance to
     * @return the squared distance between this axis-aligned bounding box and the given sphere,
     *        i.e. the square of the distance from the box to the sphere's center minus the radius,
     *        clamped at zero; zero when they overlap or touch
     */
    public double distanceSquaredToSphere(double sphereX, double sphereY, double sphereZ, double sphereR) {
        if (Math.useFma()) {
            double[] sd = this.data;
            double _t6 = sphereZ - java.lang.Math.max(sd[2], java.lang.Math.min(sphereZ, sd[5]));
            double _t7 = sphereX - java.lang.Math.max(sd[0], java.lang.Math.min(sphereX, sd[3]));
            double _t8 = sphereY - java.lang.Math.max(sd[1], java.lang.Math.min(sphereY, sd[4]));
            double _t14 = java.lang.Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - sphereR);
            return _t14 * _t14;
        } else {
            double[] sd = this.data;
            double _t6 = sphereZ - java.lang.Math.max(sd[2], java.lang.Math.min(sphereZ, sd[5]));
            double _t7 = sphereX - java.lang.Math.max(sd[0], java.lang.Math.min(sphereX, sd[3]));
            double _t8 = sphereY - java.lang.Math.max(sd[1], java.lang.Math.min(sphereY, sd[4]));
            double _t14 = java.lang.Math.max(0.0, java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - sphereR);
            return _t14 * _t14;
        }
    }


    /**
     * Compute the squared distance between this axis-aligned bounding box and the given sphere,
     * i.e. the square of the distance from the box to the sphere's center minus the radius, clamped
     * at zero; zero when they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @return the squared distance between this axis-aligned bounding box and the given sphere,
     *        i.e. the square of the distance from the box to the sphere's center minus the radius,
     *        clamped at zero; zero when they overlap or touch
     */
    public double distanceSquaredToSphere(Double3R center, double radius) {
        double sphereX = center.x();
        double sphereY = center.y();
        double sphereZ = center.z();
        if (Math.useFma()) {
            double[] sd = this.data;
            double _t6 = sphereZ - java.lang.Math.max(sd[2], java.lang.Math.min(sphereZ, sd[5]));
            double _t7 = sphereX - java.lang.Math.max(sd[0], java.lang.Math.min(sphereX, sd[3]));
            double _t8 = sphereY - java.lang.Math.max(sd[1], java.lang.Math.min(sphereY, sd[4]));
            double _t14 = java.lang.Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - radius);
            return _t14 * _t14;
        } else {
            double[] sd = this.data;
            double _t6 = sphereZ - java.lang.Math.max(sd[2], java.lang.Math.min(sphereZ, sd[5]));
            double _t7 = sphereX - java.lang.Math.max(sd[0], java.lang.Math.min(sphereX, sd[3]));
            double _t8 = sphereY - java.lang.Math.max(sd[1], java.lang.Math.min(sphereY, sd[4]));
            double _t14 = java.lang.Math.max(0.0, java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - radius);
            return _t14 * _t14;
        }
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given box, i.e. the
     * length of the shortest vector between any two points of the two boxes; zero when they overlap
     * or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component; the minimum corner of {@code other} must not
     * exceed the maximum corner of {@code other} in any component.
     *
     * @param other the box to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given box, i.e. the
     *        length of the shortest vector between any two points of the two boxes; zero when they
     *        overlap or touch
     */
    public double distanceToAABB(DoubleAABBR other) {
        double minX = other.minX();
        double minY = other.minY();
        double minZ = other.minZ();
        double maxX = other.maxX();
        double maxY = other.maxY();
        double maxZ = other.maxZ();
        if (Math.useFma()) {
            double[] sd = this.data;
            double _t9 = java.lang.Math.max(0.0, java.lang.Math.max(sd[2] - maxZ, minZ - sd[5]));
            double _t10 = java.lang.Math.max(0.0, java.lang.Math.max(sd[0] - maxX, minX - sd[3]));
            double _t11 = java.lang.Math.max(0.0, java.lang.Math.max(sd[1] - maxY, minY - sd[4]));
            return java.lang.Math.sqrt(java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11)));
        } else {
            double[] sd = this.data;
            double _t9 = java.lang.Math.max(0.0, java.lang.Math.max(sd[2] - maxZ, minZ - sd[5]));
            double _t10 = java.lang.Math.max(0.0, java.lang.Math.max(sd[0] - maxX, minX - sd[3]));
            double _t11 = java.lang.Math.max(0.0, java.lang.Math.max(sd[1] - maxY, minY - sd[4]));
            return java.lang.Math.sqrt(((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))));
        }
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given box, i.e. the
     * length of the shortest vector between any two points of the two boxes; zero when they overlap
     * or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component; {@code (minX, minY, minZ)} must not exceed
     * {@code (maxX, maxY, maxZ)} in any component.
     *
     * @param minX the {@code minX} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minY the {@code minY} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param minZ the {@code minZ} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxX the {@code maxX} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxY the {@code maxY} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @param maxZ the {@code maxZ} component of the box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)} to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given box, i.e. the
     *        length of the shortest vector between any two points of the two boxes; zero when they
     *        overlap or touch
     */
    public double distanceToAABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (Math.useFma()) {
            double[] sd = this.data;
            double _t9 = java.lang.Math.max(0.0, java.lang.Math.max(sd[2] - maxZ, minZ - sd[5]));
            double _t10 = java.lang.Math.max(0.0, java.lang.Math.max(sd[0] - maxX, minX - sd[3]));
            double _t11 = java.lang.Math.max(0.0, java.lang.Math.max(sd[1] - maxY, minY - sd[4]));
            return java.lang.Math.sqrt(java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11)));
        } else {
            double[] sd = this.data;
            double _t9 = java.lang.Math.max(0.0, java.lang.Math.max(sd[2] - maxZ, minZ - sd[5]));
            double _t10 = java.lang.Math.max(0.0, java.lang.Math.max(sd[0] - maxX, minX - sd[3]));
            double _t11 = java.lang.Math.max(0.0, java.lang.Math.max(sd[1] - maxY, minY - sd[4]));
            return java.lang.Math.sqrt(((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))));
        }
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given plane, i.e. the
     * distance from the box's center to the plane minus the box's extent along the plane normal,
     * clamped at zero; zero when the plane intersects or touches the box. The plane's normal need
     * not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of {@code plane} must be non-zero.
     *
     * @param plane the plane to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given plane, i.e. the
     *        distance from the box's center to the plane minus the box's extent along the plane
     *        normal, clamped at zero; zero when the plane intersects or touches the box. The
     *        plane's normal need not be of unit length
     */
    public double distanceToPlane(DoublePlaneR plane) {
        double planeA = plane.a();
        double planeB = plane.b();
        double planeC = plane.c();
        double planeD = plane.d();
        if (Math.useFma()) {
            double[] sd = this.data;
            return (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))) * java.lang.Math.max(0.0, java.lang.Math.fma(-0.5, java.lang.Math.fma(sd[5] - sd[2], java.lang.Math.abs(planeC), java.lang.Math.fma(sd[3] - sd[0], java.lang.Math.abs(planeA), (sd[4] - sd[1]) * java.lang.Math.abs(planeB))), java.lang.Math.abs(java.lang.Math.fma(0.5, java.lang.Math.fma(planeC, sd[2] + sd[5], java.lang.Math.fma(planeA, sd[0] + sd[3], planeB * (sd[1] + sd[4]))), planeD))));
        } else {
            double[] sd = this.data;
            return (1.0 / java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB)))))) * java.lang.Math.max(0.0, ((-0.5) * (((sd[5] - sd[2]) * (java.lang.Math.abs(planeC)) + (((sd[3] - sd[0]) * (java.lang.Math.abs(planeA)) + ((sd[4] - sd[1]) * java.lang.Math.abs(planeB)))))) + (java.lang.Math.abs(((0.5) * (((planeC) * (sd[2] + sd[5]) + (((planeA) * (sd[0] + sd[3]) + (planeB * (sd[1] + sd[4])))))) + (planeD))))));
        }
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given plane, i.e. the
     * distance from the box's center to the plane minus the box's extent along the plane normal,
     * clamped at zero; zero when the plane intersects or touches the box. The plane's normal need
     * not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of {@code (planeA, planeB, planeC, planeD)} must be non-zero.
     *
     * @param planeA the {@code a} component of the plane {@code (planeA, planeB, planeC, planeD)}
     *        to measure the distance to
     * @param planeB the {@code b} component of the plane {@code (planeA, planeB, planeC, planeD)}
     *        to measure the distance to
     * @param planeC the {@code c} component of the plane {@code (planeA, planeB, planeC, planeD)}
     *        to measure the distance to
     * @param planeD the {@code d} component of the plane {@code (planeA, planeB, planeC, planeD)}
     *        to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given plane, i.e. the
     *        distance from the box's center to the plane minus the box's extent along the plane
     *        normal, clamped at zero; zero when the plane intersects or touches the box. The
     *        plane's normal need not be of unit length
     */
    public double distanceToPlane(double planeA, double planeB, double planeC, double planeD) {
        if (Math.useFma()) {
            double[] sd = this.data;
            return (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))) * java.lang.Math.max(0.0, java.lang.Math.fma(-0.5, java.lang.Math.fma(sd[5] - sd[2], java.lang.Math.abs(planeC), java.lang.Math.fma(sd[3] - sd[0], java.lang.Math.abs(planeA), (sd[4] - sd[1]) * java.lang.Math.abs(planeB))), java.lang.Math.abs(java.lang.Math.fma(0.5, java.lang.Math.fma(planeC, sd[2] + sd[5], java.lang.Math.fma(planeA, sd[0] + sd[3], planeB * (sd[1] + sd[4]))), planeD))));
        } else {
            double[] sd = this.data;
            return (1.0 / java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB)))))) * java.lang.Math.max(0.0, ((-0.5) * (((sd[5] - sd[2]) * (java.lang.Math.abs(planeC)) + (((sd[3] - sd[0]) * (java.lang.Math.abs(planeA)) + ((sd[4] - sd[1]) * java.lang.Math.abs(planeB)))))) + (java.lang.Math.abs(((0.5) * (((planeC) * (sd[2] + sd[5]) + (((planeA) * (sd[0] + sd[3]) + (planeB * (sd[1] + sd[4])))))) + (planeD))))));
        }
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given plane, i.e. the
     * distance from the box's center to the plane minus the box's extent along the plane normal,
     * clamped at zero; zero when the plane intersects or touches the box. The plane's normal need
     * not be of unit length.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * normal of {@code plane} must be non-zero.
     *
     * @param plane the plane to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given plane, i.e. the
     *        distance from the box's center to the plane minus the box's extent along the plane
     *        normal, clamped at zero; zero when the plane intersects or touches the box. The
     *        plane's normal need not be of unit length
     */
    public double distanceToPlane(Double4R plane) {
        double planeA = plane.x();
        double planeB = plane.y();
        double planeC = plane.z();
        double planeD = plane.w();
        if (Math.useFma()) {
            double[] sd = this.data;
            return (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(planeC, planeC, java.lang.Math.fma(planeA, planeA, planeB * planeB)))) * java.lang.Math.max(0.0, java.lang.Math.fma(-0.5, java.lang.Math.fma(sd[5] - sd[2], java.lang.Math.abs(planeC), java.lang.Math.fma(sd[3] - sd[0], java.lang.Math.abs(planeA), (sd[4] - sd[1]) * java.lang.Math.abs(planeB))), java.lang.Math.abs(java.lang.Math.fma(0.5, java.lang.Math.fma(planeC, sd[2] + sd[5], java.lang.Math.fma(planeA, sd[0] + sd[3], planeB * (sd[1] + sd[4]))), planeD))));
        } else {
            double[] sd = this.data;
            return (1.0 / java.lang.Math.sqrt(((planeC) * (planeC) + (((planeA) * (planeA) + (planeB * planeB)))))) * java.lang.Math.max(0.0, ((-0.5) * (((sd[5] - sd[2]) * (java.lang.Math.abs(planeC)) + (((sd[3] - sd[0]) * (java.lang.Math.abs(planeA)) + ((sd[4] - sd[1]) * java.lang.Math.abs(planeB)))))) + (java.lang.Math.abs(((0.5) * (((planeC) * (sd[2] + sd[5]) + (((planeA) * (sd[0] + sd[3]) + (planeB * (sd[1] + sd[4])))))) + (planeD))))));
        }
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given point, i.e. the
     * length of the difference between the point and its per-axis clamp into the box's bounds; zero
     * for a point inside or on the box.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param p the point to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given point, i.e. the
     *        length of the difference between the point and its per-axis clamp into the box's
     *        bounds; zero for a point inside or on the box
     */
    public double distanceToPoint(Double3R p) {
        double pX = p.x();
        double pY = p.y();
        double pZ = p.z();
        if (Math.useFma()) {
            double[] sd = this.data;
            double _t6 = pZ - java.lang.Math.max(sd[2], java.lang.Math.min(pZ, sd[5]));
            double _t7 = pX - java.lang.Math.max(sd[0], java.lang.Math.min(pX, sd[3]));
            double _t8 = pY - java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[4]));
            return java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        } else {
            double[] sd = this.data;
            double _t6 = pZ - java.lang.Math.max(sd[2], java.lang.Math.min(pZ, sd[5]));
            double _t7 = pX - java.lang.Math.max(sd[0], java.lang.Math.min(pX, sd[3]));
            double _t8 = pY - java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[4]));
            return java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8)))));
        }
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given point, i.e. the
     * length of the difference between the point and its per-axis clamp into the box's bounds; zero
     * for a point inside or on the box.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param pX the {@code x} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pY the {@code y} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @param pZ the {@code z} component of the point {@code (pX, pY, pZ)} to measure the distance
     *        to
     * @return the distance between this axis-aligned bounding box and the given point, i.e. the
     *        length of the difference between the point and its per-axis clamp into the box's
     *        bounds; zero for a point inside or on the box
     */
    public double distanceToPoint(double pX, double pY, double pZ) {
        if (Math.useFma()) {
            double[] sd = this.data;
            double _t6 = pZ - java.lang.Math.max(sd[2], java.lang.Math.min(pZ, sd[5]));
            double _t7 = pX - java.lang.Math.max(sd[0], java.lang.Math.min(pX, sd[3]));
            double _t8 = pY - java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[4]));
            return java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        } else {
            double[] sd = this.data;
            double _t6 = pZ - java.lang.Math.max(sd[2], java.lang.Math.min(pZ, sd[5]));
            double _t7 = pX - java.lang.Math.max(sd[0], java.lang.Math.min(pX, sd[3]));
            double _t8 = pY - java.lang.Math.max(sd[1], java.lang.Math.min(pY, sd[4]));
            return java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8)))));
        }
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given sphere, i.e. the
     * distance from the box to the sphere's center minus the radius, clamped at zero; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param sphere the sphere to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given sphere, i.e. the
     *        distance from the box to the sphere's center minus the radius, clamped at zero; zero
     *        when they overlap or touch
     */
    public double distanceToSphere(DoubleSphereR sphere) {
        double sphereX = sphere.x();
        double sphereY = sphere.y();
        double sphereZ = sphere.z();
        double sphereR = sphere.r();
        if (Math.useFma()) {
            double[] sd = this.data;
            double _t6 = sphereZ - java.lang.Math.max(sd[2], java.lang.Math.min(sphereZ, sd[5]));
            double _t7 = sphereX - java.lang.Math.max(sd[0], java.lang.Math.min(sphereX, sd[3]));
            double _t8 = sphereY - java.lang.Math.max(sd[1], java.lang.Math.min(sphereY, sd[4]));
            return java.lang.Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - sphereR);
        } else {
            double[] sd = this.data;
            double _t6 = sphereZ - java.lang.Math.max(sd[2], java.lang.Math.min(sphereZ, sd[5]));
            double _t7 = sphereX - java.lang.Math.max(sd[0], java.lang.Math.min(sphereX, sd[3]));
            double _t8 = sphereY - java.lang.Math.max(sd[1], java.lang.Math.min(sphereY, sd[4]));
            return java.lang.Math.max(0.0, java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - sphereR);
        }
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given sphere, i.e. the
     * distance from the box to the sphere's center minus the radius, clamped at zero; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param sphereX the {@code x} component of the sphere
     *        {@code (sphereX, sphereY, sphereZ, sphereR)} to measure the distance to
     * @param sphereY the {@code y} component of the sphere
     *        {@code (sphereX, sphereY, sphereZ, sphereR)} to measure the distance to
     * @param sphereZ the {@code z} component of the sphere
     *        {@code (sphereX, sphereY, sphereZ, sphereR)} to measure the distance to
     * @param sphereR the {@code r} component of the sphere
     *        {@code (sphereX, sphereY, sphereZ, sphereR)} to measure the distance to
     * @return the distance between this axis-aligned bounding box and the given sphere, i.e. the
     *        distance from the box to the sphere's center minus the radius, clamped at zero; zero
     *        when they overlap or touch
     */
    public double distanceToSphere(double sphereX, double sphereY, double sphereZ, double sphereR) {
        if (Math.useFma()) {
            double[] sd = this.data;
            double _t6 = sphereZ - java.lang.Math.max(sd[2], java.lang.Math.min(sphereZ, sd[5]));
            double _t7 = sphereX - java.lang.Math.max(sd[0], java.lang.Math.min(sphereX, sd[3]));
            double _t8 = sphereY - java.lang.Math.max(sd[1], java.lang.Math.min(sphereY, sd[4]));
            return java.lang.Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - sphereR);
        } else {
            double[] sd = this.data;
            double _t6 = sphereZ - java.lang.Math.max(sd[2], java.lang.Math.min(sphereZ, sd[5]));
            double _t7 = sphereX - java.lang.Math.max(sd[0], java.lang.Math.min(sphereX, sd[3]));
            double _t8 = sphereY - java.lang.Math.max(sd[1], java.lang.Math.min(sphereY, sd[4]));
            return java.lang.Math.max(0.0, java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - sphereR);
        }
    }


    /**
     * Compute the distance between this axis-aligned bounding box and the given sphere, i.e. the
     * distance from the box to the sphere's center minus the radius, clamped at zero; zero when
     * they overlap or touch.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; the
     * minimum corner of this axis-aligned bounding box must not exceed the maximum corner of this
     * axis-aligned bounding box in any component.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @return the distance between this axis-aligned bounding box and the given sphere, i.e. the
     *        distance from the box to the sphere's center minus the radius, clamped at zero; zero
     *        when they overlap or touch
     */
    public double distanceToSphere(Double3R center, double radius) {
        double sphereX = center.x();
        double sphereY = center.y();
        double sphereZ = center.z();
        if (Math.useFma()) {
            double[] sd = this.data;
            double _t6 = sphereZ - java.lang.Math.max(sd[2], java.lang.Math.min(sphereZ, sd[5]));
            double _t7 = sphereX - java.lang.Math.max(sd[0], java.lang.Math.min(sphereX, sd[3]));
            double _t8 = sphereY - java.lang.Math.max(sd[1], java.lang.Math.min(sphereY, sd[4]));
            return java.lang.Math.max(0.0, java.lang.Math.sqrt(java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8))) - radius);
        } else {
            double[] sd = this.data;
            double _t6 = sphereZ - java.lang.Math.max(sd[2], java.lang.Math.min(sphereZ, sd[5]));
            double _t7 = sphereX - java.lang.Math.max(sd[0], java.lang.Math.min(sphereX, sd[3]));
            double _t8 = sphereY - java.lang.Math.max(sd[1], java.lang.Math.min(sphereY, sd[4]));
            return java.lang.Math.max(0.0, java.lang.Math.sqrt(((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))) - radius);
        }
    }


    /**
     * Get the center of this axis-aligned bounding box and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getCenter(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = 0.5 * sd[0] + 0.5 * sd[3];
        dd[1] = 0.5 * sd[1] + 0.5 * sd[4];
        dd[2] = 0.5 * sd[2] + 0.5 * sd[5];
        return dest;
    }


    /**
     * Get the maximum corner of this axis-aligned bounding box and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getMax(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[3];
        dd[1] = sd[4];
        dd[2] = sd[5];
        return dest;
    }


    /**
     * Get the minimum corner of this axis-aligned bounding box and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getMin(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        return dest;
    }


    /**
     * Get the size, i.e. the maximum minus the minimum corner per axis of this axis-aligned
     * bounding box and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getSize(@Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        dd[0] = sd[3] - sd[0];
        dd[1] = sd[4] - sd[1];
        dd[2] = sd[5] - sd[2];
        return dest;
    }


    /**
     * Determine whether this axis-aligned bounding box is valid, i.e. no minimum bound exceeds its
     * maximum.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return {@code true} if this axis-aligned bounding box is valid, i.e. no minimum bound
     *        exceeds its maximum, {@code false} otherwise
     */
    public boolean isValid() {
        double[] sd = this.data;
        if (!(sd[0] <= sd[3])) return false;
        if (!(sd[1] <= sd[4])) return false;
        return sd[2] <= sd[5];
    }

    public double minX() { return data[0]; }
    public double minY() { return data[1]; }
    public double minZ() { return data[2]; }
    public double maxX() { return data[3]; }
    public double maxY() { return data[4]; }
    public double maxZ() { return data[5]; }

    @Override public String toString() {
        return "DoubleAABB(" + minX() + ", " + minY() + ", " + minZ() + ", " + maxX() + ", " + maxY() + ", " + maxZ() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DoubleAABBImpl)) return false;
        DoubleAABBImpl o = (DoubleAABBImpl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Double.isFinite(data[0])
            && Double.isFinite(data[1])
            && Double.isFinite(data[2])
            && Double.isFinite(data[3])
            && Double.isFinite(data[4])
            && Double.isFinite(data[5]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1])
            || Double.isNaN(data[2])
            || Double.isNaN(data[3])
            || Double.isNaN(data[4])
            || Double.isNaN(data[5]);
    }

    @Override public boolean equalsEpsilon(DoubleAABBR other, double epsilon) {
        return java.lang.Math.abs(data[0] - other.minX()) <= epsilon
            && java.lang.Math.abs(data[1] - other.minY()) <= epsilon
            && java.lang.Math.abs(data[2] - other.minZ()) <= epsilon
            && java.lang.Math.abs(data[3] - other.maxX()) <= epsilon
            && java.lang.Math.abs(data[4] - other.maxY()) <= epsilon
            && java.lang.Math.abs(data[5] - other.maxZ()) <= epsilon;
    }

    public boolean containsPoint(double x, double y, double z) {
        return Intersectiond.testPointAabb(x, y, z, minX(), minY(), minZ(), maxX(), maxY(), maxZ());
    }

    public boolean containsPoint(Double3R p) {
        return Intersectiond.testPointAabb(p, this);
    }

    public boolean containsAABB(DoubleAABBR o) {
        return Intersectiond.testAabbAabbContains(this, o);
    }

    public boolean intersectsAABB(DoubleAABBR o) {
        return Intersectiond.testAabbAabb(this, o);
    }

    public boolean intersectsSweptAABB(DoubleAABBR other, double vX, double vY, double vZ) {
        return Intersectiond.testMovingAabbAabb(other.minX(), other.minY(), other.minZ(), other.maxX(), other.maxY(), other.maxZ(), vX, vY, vZ, minX(), minY(), minZ(), maxX(), maxY(), maxZ());
    }

    public boolean intersectsSweptAABB(DoubleAABBR other, Double3R velocity) {
        return intersectsSweptAABB(other, velocity.x(), velocity.y(), velocity.z());
    }

    public boolean intersectsSphere(DoubleSphereR sph) {
        return Intersectiond.testAabbSphere(this, sph);
    }

    public boolean intersectsPlane(DoublePlaneR plane) {
        return Intersectiond.testAabbPlane(minX(), minY(), minZ(), maxX(), maxY(), maxZ(), plane.a(), plane.b(), plane.c(), plane.d());
    }

    public boolean intersectsRay(DoubleRayR r) {
        return Intersectiond.testRayAabb(r, this);
    }

    public boolean intersectRay(DoubleRayR r, @Mutated Double2 dest) {
        return Intersectiond.intersectRayAabb(r, this, dest);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        return dest;
    }
    public @Mutated DoubleAABB load(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        return this;
    }
    public DoubleBuffer store(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public DoubleBuffer storeRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return buf;
    }
    @Mutated public DoubleAABB load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public DoubleAABB loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public DoubleAABB loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return this;
    }
    public ByteBuffer store(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public ByteBuffer storeRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }
    public DoubleAABB load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    public DoubleAABB loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    public DoubleAABB loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleAABB r = StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return r;
    }
    public DoubleAABB storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }
    @Mutated public DoubleAABB loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        dest[offset + 4] = (float) this.data[4];
        dest[offset + 5] = (float) this.data[5];
        return dest;
    }
    public @Mutated DoubleAABB load(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        return this;
    }
    public FloatBuffer store(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeAbsolute(this, index, buf);
    }
    public FloatBuffer storeRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return buf;
    }
    @Mutated public DoubleAABB load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }
    @Mutated public DoubleAABB loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }
    @Mutated public DoubleAABB loadRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return this;
    }
    public ByteBuffer storeFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeFloatAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }
    public DoubleAABB loadFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, buf.position(), buf);
    }
    public DoubleAABB loadFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadFloatAbsolute(this, index, buf);
    }
    public DoubleAABB loadFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        DoubleAABB r = StoreLoad.BB_OPS.loadFloatAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return r;
    }
    public DoubleAABB storeFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeFloatUnsafe(this, address);
    }
    @Mutated public DoubleAABB loadFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadFloatUnsafe(this, address);
    }
}
