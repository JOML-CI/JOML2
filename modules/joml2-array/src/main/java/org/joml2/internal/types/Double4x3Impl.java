// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Generated implementation of {@link Double4x3} backed by a {@code double[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Double4x3Impl implements Double4x3 {

    public double[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double4x3SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double4x3SegOpsUnsafe()
                        : new Double4x3SegOpsMS();
        static final Double4x3BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double4x3BbOpsUnsafe()
                        : new Double4x3BbOpsApi();
        static final Double4x3RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double4x3RawOpsUnsafe()
                        : new Double4x3RawOpsApi();
    }

    public Double4x3Impl() {
        data = new double[12];
        data[0] = 1;
        data[5] = 1;
        data[10] = 1;
    }

    public Double4x3Impl(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, double m30, double m31, double m32) {
        double[] dd = this.data = new double[12];
        dd[0] = m00;
        dd[1] = m10;
        dd[2] = m20;
        dd[3] = m30;
        dd[4] = m01;
        dd[5] = m11;
        dd[6] = m21;
        dd[7] = m31;
        dd[8] = m02;
        dd[9] = m12;
        dd[10] = m22;
        dd[11] = m32;
    }

    public Double4x3Impl(Double4x3R src) {
        Double4x3Impl s = (Double4x3Impl) src;
        this.data = s.data.clone();
    }


    /**
     * Get the column at the given index of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param col the column index
     * @param dest will hold the result
     * @return dest
     * @throws IndexOutOfBoundsException if {@code col} is not in {@code [0, COLUMNS)}
     */
    public Double4 getColumn(int col, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        double _idxSw3;
        switch (col) {
            case 0: _idxSw0 = sd[0]; _idxSw1 = sd[1]; _idxSw2 = sd[2]; _idxSw3 = sd[3]; break;
            case 1: _idxSw0 = sd[4]; _idxSw1 = sd[5]; _idxSw2 = sd[6]; _idxSw3 = sd[7]; break;
            case 2: _idxSw0 = sd[8]; _idxSw1 = sd[9]; _idxSw2 = sd[10]; _idxSw3 = sd[11]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dd[0] = _idxSw0;
        dd[1] = _idxSw1;
        dd[2] = _idxSw2;
        dd[3] = _idxSw3;
        return dest;
    }


    /**
     * Get the row at the given index of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param row the row index
     * @param dest will hold the result
     * @return dest
     * @throws IndexOutOfBoundsException if {@code row} is not in {@code [0, ROWS)}
     */
    public Double3 getRow(int row, @Mutated Double3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (row) {
            case 0: _idxSw0 = sd[0]; _idxSw1 = sd[4]; _idxSw2 = sd[8]; break;
            case 1: _idxSw0 = sd[1]; _idxSw1 = sd[5]; _idxSw2 = sd[9]; break;
            case 2: _idxSw0 = sd[2]; _idxSw1 = sd[6]; _idxSw2 = sd[10]; break;
            case 3: _idxSw0 = sd[3]; _idxSw1 = sd[7]; _idxSw2 = sd[11]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw0;
        dd[1] = _idxSw1;
        dd[2] = _idxSw2;
        return dest;
    }


    /**
     * Compute the Frobenius norm of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Frobenius norm of this matrix
     */
    public double frobeniusNorm() {
        if (Math.useFma()) {
            double[] sd = this.data;
            return java.lang.Math.sqrt(java.lang.Math.fma(sd[0], sd[0], java.lang.Math.fma(sd[4], sd[4], sd[8] * sd[8])) + java.lang.Math.fma(sd[1], sd[1], java.lang.Math.fma(sd[5], sd[5], sd[9] * sd[9])) + (java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[6], sd[6], sd[10] * sd[10])) + java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[7], sd[7], sd[11] * sd[11]))));
        } else {
            double[] sd = this.data;
            return java.lang.Math.sqrt(((sd[0]) * (sd[0]) + (((sd[4]) * (sd[4]) + (sd[8] * sd[8])))) + ((sd[1]) * (sd[1]) + (((sd[5]) * (sd[5]) + (sd[9] * sd[9])))) + (((sd[2]) * (sd[2]) + (((sd[6]) * (sd[6]) + (sd[10] * sd[10])))) + ((sd[3]) * (sd[3]) + (((sd[7]) * (sd[7]) + (sd[11] * sd[11]))))));
        }
    }


    /**
     * Transpose this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x4 transpose(@Mutated Double3x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double3x4Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[8] = sd[8];
        dd[9] = sd[9];
        dd[10] = sd[10];
        dd[11] = sd[11];
        ((Double3x4Impl) dest).properties = Joml.BIT_AFFINE;
        return dest;
    }


    /**
     * Add {@code other} to this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to add
     * @param dest will hold the result
     * @return dest
     */
    public Double4x3 add(Double4x3R other, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x3Impl) other).data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = otherData[0] + sd[0];
        dd[1] = otherData[1] + sd[1];
        dd[2] = otherData[2] + sd[2];
        dd[3] = otherData[3] + sd[3];
        dd[4] = otherData[4] + sd[4];
        dd[5] = otherData[5] + sd[5];
        dd[6] = otherData[6] + sd[6];
        dd[7] = otherData[7] + sd[7];
        dd[8] = otherData[8] + sd[8];
        dd[9] = otherData[9] + sd[9];
        dd[10] = otherData[10] + sd[10];
        dd[11] = otherData[11] + sd[11];
        return dest;
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}, {@code m30}, {@code m31}, {@code m32}) to this matrix
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Double4x3 add(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, double m30, double m31, double m32, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = m00 + sd[0];
        dd[1] = m10 + sd[1];
        dd[2] = m20 + sd[2];
        dd[3] = m30 + sd[3];
        dd[4] = m01 + sd[4];
        dd[5] = m11 + sd[5];
        dd[6] = m21 + sd[6];
        dd[7] = m31 + sd[7];
        dd[8] = m02 + sd[8];
        dd[9] = m12 + sd[9];
        dd[10] = m22 + sd[10];
        dd[11] = m32 + sd[11];
        return dest;
    }


    /**
     * Multiply each component of this matrix by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Double4x3 mul(double scalar, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        dd[4] = scalar * sd[4];
        dd[5] = scalar * sd[5];
        dd[6] = scalar * sd[6];
        dd[7] = scalar * sd[7];
        dd[8] = scalar * sd[8];
        dd[9] = scalar * sd[9];
        dd[10] = scalar * sd[10];
        dd[11] = scalar * sd[11];
        return dest;
    }


    /**
     * Negate this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4x3 negate(@Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
        dd[8] = -sd[8];
        dd[9] = -sd[9];
        dd[10] = -sd[10];
        dd[11] = -sd[11];
        return dest;
    }


    /**
     * Subtract {@code other} from this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Double4x3 sub(Double4x3R other, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x3Impl) other).data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = sd[0] - otherData[0];
        dd[1] = sd[1] - otherData[1];
        dd[2] = sd[2] - otherData[2];
        dd[3] = sd[3] - otherData[3];
        dd[4] = sd[4] - otherData[4];
        dd[5] = sd[5] - otherData[5];
        dd[6] = sd[6] - otherData[6];
        dd[7] = sd[7] - otherData[7];
        dd[8] = sd[8] - otherData[8];
        dd[9] = sd[9] - otherData[9];
        dd[10] = sd[10] - otherData[10];
        dd[11] = sd[11] - otherData[11];
        return dest;
    }


    /**
     * Subtract ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}, {@code m30}, {@code m31}, {@code m32}) from this
     * matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Double4x3 sub(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, double m30, double m31, double m32, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = sd[0] - m00;
        dd[1] = sd[1] - m10;
        dd[2] = sd[2] - m20;
        dd[3] = sd[3] - m30;
        dd[4] = sd[4] - m01;
        dd[5] = sd[5] - m11;
        dd[6] = sd[6] - m21;
        dd[7] = sd[7] - m31;
        dd[8] = sd[8] - m02;
        dd[9] = sd[9] - m12;
        dd[10] = sd[10] - m22;
        dd[11] = sd[11] - m32;
        return dest;
    }


    /**
     * Set this matrix to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the matrix to copy
     * @return this
     */
    @Mutated public Double4x3 set(Double4x3R v) {
        double[] dd = this.data;
        double[] vData = ((Double4x3Impl) v).data;
        dd[0] = vData[0];
        dd[1] = vData[1];
        dd[2] = vData[2];
        dd[3] = vData[3];
        dd[4] = vData[4];
        dd[5] = vData[5];
        dd[6] = vData[6];
        dd[7] = vData[7];
        dd[8] = vData[8];
        dd[9] = vData[9];
        dd[10] = vData[10];
        dd[11] = vData[11];
        return this;
    }


    /**
     * Set this matrix to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @return this
     */
    @Mutated public Double4x3 set(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, double m30, double m31, double m32) {
        double[] dd = this.data;
        dd[0] = m00;
        dd[1] = m10;
        dd[2] = m20;
        dd[3] = m30;
        dd[4] = m01;
        dd[5] = m11;
        dd[6] = m21;
        dd[7] = m31;
        dd[8] = m02;
        dd[9] = m12;
        dd[10] = m22;
        dd[11] = m32;
        return this;
    }


    /**
     * Convert this matrix to {@code float} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4x3 toFloat(@Mutated Float4x3 dest) {
        double[] sd = this.data;
        float[] dd = ((Float4x3Impl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        dd[3] = (float) (sd[3]);
        dd[4] = (float) (sd[4]);
        dd[5] = (float) (sd[5]);
        dd[6] = (float) (sd[6]);
        dd[7] = (float) (sd[7]);
        dd[8] = (float) (sd[8]);
        dd[9] = (float) (sd[9]);
        dd[10] = (float) (sd[10]);
        dd[11] = (float) (sd[11]);
        return dest;
    }


    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Double4x3 makeIdentity() {
        double[] dd = this.data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = 1.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
        dd[8] = 0.0;
        dd[9] = 0.0;
        dd[10] = 1.0;
        dd[11] = 0.0;
        return this;
    }


    /**
     * Linearly interpolate between this matrix and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4x3 lerp(Double4x3R other, double t, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x3Impl) other).data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = Math.fma(t, otherData[0] - sd[0], sd[0]);
        dd[1] = Math.fma(t, otherData[1] - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherData[2] - sd[2], sd[2]);
        dd[3] = Math.fma(t, otherData[3] - sd[3], sd[3]);
        dd[4] = Math.fma(t, otherData[4] - sd[4], sd[4]);
        dd[5] = Math.fma(t, otherData[5] - sd[5], sd[5]);
        dd[6] = Math.fma(t, otherData[6] - sd[6], sd[6]);
        dd[7] = Math.fma(t, otherData[7] - sd[7], sd[7]);
        dd[8] = Math.fma(t, otherData[8] - sd[8], sd[8]);
        dd[9] = Math.fma(t, otherData[9] - sd[9], sd[9]);
        dd[10] = Math.fma(t, otherData[10] - sd[10], sd[10]);
        dd[11] = Math.fma(t, otherData[11] - sd[11], sd[11]);
        return dest;
    }


    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m10}, {@code m11}, {@code m12}, {@code m20}, {@code m21}, {@code m22}, {@code m30},
     * {@code m31}, {@code m32}) using the interpolation factor {@code t} and store the result in
     * {@code dest}.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12}, {@code m20},
     * {@code m21}, {@code m22}, {@code m30}, {@code m31}, {@code m32}) (interpolation factor
     * {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t}, as in
     * JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only
     * up to the rounding of {@code other - this}, which shows when this component is much larger in
     * magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4x3 lerp(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, double m30, double m31, double m32, double t, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = Math.fma(t, m00 - sd[0], sd[0]);
        dd[1] = Math.fma(t, m10 - sd[1], sd[1]);
        dd[2] = Math.fma(t, m20 - sd[2], sd[2]);
        dd[3] = Math.fma(t, m30 - sd[3], sd[3]);
        dd[4] = Math.fma(t, m01 - sd[4], sd[4]);
        dd[5] = Math.fma(t, m11 - sd[5], sd[5]);
        dd[6] = Math.fma(t, m21 - sd[6], sd[6]);
        dd[7] = Math.fma(t, m31 - sd[7], sd[7]);
        dd[8] = Math.fma(t, m02 - sd[8], sd[8]);
        dd[9] = Math.fma(t, m12 - sd[9], sd[9]);
        dd[10] = Math.fma(t, m22 - sd[10], sd[10]);
        dd[11] = Math.fma(t, m32 - sd[11], sd[11]);
        return dest;
    }


    /**
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * The operand is identity-extended to this matrix's square size before the multiplication, and
     * the product is projected back onto this shape.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @param dest will hold the result
     * @return dest
     */
    public Double4x3 mul(Double3x3R right, @Mutated Double4x3 dest) {
        if (Math.useFma()) return mul_fma(right, dest);
        return mul_mulAdd(right, dest);
    }

    /** {@code mul} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x3 mul_fma(Double3x3R right, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double4x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = rightData[4];
        double _rd5 = rightData[5];
        double _rd6 = rightData[6];
        double _rd7 = rightData[7];
        double _rd8 = rightData[8];
        double _rd9 = sd[0];
        double _rd10 = sd[1];
        double _rd11 = sd[2];
        double _rd12 = sd[3];
        double _rd13 = sd[4];
        double _rd14 = sd[5];
        double _rd15 = sd[6];
        double _rd16 = sd[7];
        double _rd17 = sd[8];
        double _rd18 = sd[9];
        double _rd19 = sd[10];
        double _rd20 = sd[11];
        dd[0] = java.lang.Math.fma(_rd2, _rd17, java.lang.Math.fma(_rd0, _rd9, _rd1 * _rd13));
        dd[1] = java.lang.Math.fma(_rd2, _rd18, java.lang.Math.fma(_rd0, _rd10, _rd1 * _rd14));
        dd[2] = java.lang.Math.fma(_rd2, _rd19, java.lang.Math.fma(_rd0, _rd11, _rd1 * _rd15));
        dd[3] = java.lang.Math.fma(_rd2, _rd20, java.lang.Math.fma(_rd0, _rd12, _rd1 * _rd16));
        dd[4] = java.lang.Math.fma(_rd5, _rd17, java.lang.Math.fma(_rd3, _rd9, _rd4 * _rd13));
        return mul_sd370657a_1_fma(dest, dd, _rd3, _rd4, _rd5, _rd6, _rd7, _rd8, _rd9, _rd10, _rd11, _rd12, _rd13, _rd14, _rd15, _rd16, _rd17, _rd18, _rd19, _rd20);
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x3 mul_mulAdd(Double3x3R right, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double4x3Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = rightData[4];
        double _rd5 = rightData[5];
        double _rd6 = rightData[6];
        double _rd7 = rightData[7];
        double _rd8 = rightData[8];
        double _rd9 = sd[0];
        double _rd10 = sd[1];
        double _rd11 = sd[2];
        double _rd12 = sd[3];
        double _rd13 = sd[4];
        double _rd14 = sd[5];
        double _rd15 = sd[6];
        double _rd16 = sd[7];
        double _rd17 = sd[8];
        double _rd18 = sd[9];
        double _rd19 = sd[10];
        double _rd20 = sd[11];
        dd[0] = ((_rd2) * (_rd17) + (((_rd0) * (_rd9) + (_rd1 * _rd13))));
        dd[1] = ((_rd2) * (_rd18) + (((_rd0) * (_rd10) + (_rd1 * _rd14))));
        dd[2] = ((_rd2) * (_rd19) + (((_rd0) * (_rd11) + (_rd1 * _rd15))));
        dd[3] = ((_rd2) * (_rd20) + (((_rd0) * (_rd12) + (_rd1 * _rd16))));
        dd[4] = ((_rd5) * (_rd17) + (((_rd3) * (_rd9) + (_rd4 * _rd13))));
        return mul_sd370657a_1_mulAdd(dest, dd, _rd3, _rd4, _rd5, _rd6, _rd7, _rd8, _rd9, _rd10, _rd11, _rd12, _rd13, _rd14, _rd15, _rd16, _rd17, _rd18, _rd19, _rd20);
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private Double4x3 mul_sd370657a_1_fma(Double4x3 dest, double[] dd, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7, double _rd8, double _rd9, double _rd10, double _rd11, double _rd12, double _rd13, double _rd14, double _rd15, double _rd16, double _rd17, double _rd18, double _rd19, double _rd20) {
        dd[5] = java.lang.Math.fma(_rd5, _rd18, java.lang.Math.fma(_rd3, _rd10, _rd4 * _rd14));
        dd[6] = java.lang.Math.fma(_rd5, _rd19, java.lang.Math.fma(_rd3, _rd11, _rd4 * _rd15));
        dd[7] = java.lang.Math.fma(_rd5, _rd20, java.lang.Math.fma(_rd3, _rd12, _rd4 * _rd16));
        dd[8] = java.lang.Math.fma(_rd8, _rd17, java.lang.Math.fma(_rd6, _rd9, _rd7 * _rd13));
        dd[9] = java.lang.Math.fma(_rd8, _rd18, java.lang.Math.fma(_rd6, _rd10, _rd7 * _rd14));
        dd[10] = java.lang.Math.fma(_rd8, _rd19, java.lang.Math.fma(_rd6, _rd11, _rd7 * _rd15));
        dd[11] = java.lang.Math.fma(_rd8, _rd20, java.lang.Math.fma(_rd6, _rd12, _rd7 * _rd16));
        return dest;
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private Double4x3 mul_sd370657a_1_mulAdd(Double4x3 dest, double[] dd, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7, double _rd8, double _rd9, double _rd10, double _rd11, double _rd12, double _rd13, double _rd14, double _rd15, double _rd16, double _rd17, double _rd18, double _rd19, double _rd20) {
        dd[5] = ((_rd5) * (_rd18) + (((_rd3) * (_rd10) + (_rd4 * _rd14))));
        dd[6] = ((_rd5) * (_rd19) + (((_rd3) * (_rd11) + (_rd4 * _rd15))));
        dd[7] = ((_rd5) * (_rd20) + (((_rd3) * (_rd12) + (_rd4 * _rd16))));
        dd[8] = ((_rd8) * (_rd17) + (((_rd6) * (_rd9) + (_rd7 * _rd13))));
        dd[9] = ((_rd8) * (_rd18) + (((_rd6) * (_rd10) + (_rd7 * _rd14))));
        dd[10] = ((_rd8) * (_rd19) + (((_rd6) * (_rd11) + (_rd7 * _rd15))));
        dd[11] = ((_rd8) * (_rd20) + (((_rd6) * (_rd12) + (_rd7 * _rd16))));
        return dest;
    }


    /**
     * Pre-multiply the transformation {@code other} onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the given transformation matrix, then the
     * new matrix will be {@code T * M}. So when transforming a vector {@code v} with the new matrix
     * by using {@code T * M * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public Double4x3 preMul(Double4x4R other, @Mutated Double4x3 dest) {
        if (Math.useFma()) return preMul_fma(other, dest);
        return preMul_mulAdd(other, dest);
    }

    /** {@code preMul} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x3 preMul_fma(Double4x4R other, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x4Impl) other).data;
        double[] dd = ((Double4x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = otherData[4];
        double _rd5 = otherData[5];
        double _rd6 = otherData[6];
        double _rd7 = otherData[7];
        double _rd8 = otherData[8];
        double _rd9 = otherData[9];
        double _rd10 = otherData[10];
        double _rd11 = otherData[11];
        double _rd12 = otherData[12];
        double _rd13 = otherData[13];
        double _rd14 = otherData[14];
        double _rd15 = otherData[15];
        double _rd16 = sd[0];
        double _rd17 = sd[1];
        double _rd18 = sd[2];
        double _rd19 = sd[3];
        double _rd20 = sd[4];
        double _rd21 = sd[5];
        double _rd22 = sd[6];
        double _rd23 = sd[7];
        double _rd24 = sd[8];
        double _rd25 = sd[9];
        double _rd26 = sd[10];
        double _rd27 = sd[11];
        dd[0] = java.lang.Math.fma(_rd12, _rd19, java.lang.Math.fma(_rd8, _rd18, java.lang.Math.fma(_rd0, _rd16, _rd4 * _rd17)));
        return preMul_secdf5983_1_fma(dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7, _rd8, _rd9, _rd10, _rd11, _rd12, _rd13, _rd14, _rd15, _rd16, _rd17, _rd18, _rd19, _rd20, _rd21, _rd22, _rd23, _rd24, _rd25, _rd26, _rd27);
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x3 preMul_mulAdd(Double4x4R other, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x4Impl) other).data;
        double[] dd = ((Double4x3Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[2];
        double _rd3 = otherData[3];
        double _rd4 = otherData[4];
        double _rd5 = otherData[5];
        double _rd6 = otherData[6];
        double _rd7 = otherData[7];
        double _rd8 = otherData[8];
        double _rd9 = otherData[9];
        double _rd10 = otherData[10];
        double _rd11 = otherData[11];
        double _rd12 = otherData[12];
        double _rd13 = otherData[13];
        double _rd14 = otherData[14];
        double _rd15 = otherData[15];
        double _rd16 = sd[0];
        double _rd17 = sd[1];
        double _rd18 = sd[2];
        double _rd19 = sd[3];
        double _rd20 = sd[4];
        double _rd21 = sd[5];
        double _rd22 = sd[6];
        double _rd23 = sd[7];
        double _rd24 = sd[8];
        double _rd25 = sd[9];
        double _rd26 = sd[10];
        double _rd27 = sd[11];
        dd[0] = ((_rd12) * (_rd19) + (((_rd8) * (_rd18) + (((_rd0) * (_rd16) + (_rd4 * _rd17))))));
        return preMul_secdf5983_1_mulAdd(dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7, _rd8, _rd9, _rd10, _rd11, _rd12, _rd13, _rd14, _rd15, _rd16, _rd17, _rd18, _rd19, _rd20, _rd21, _rd22, _rd23, _rd24, _rd25, _rd26, _rd27);
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Double4x3 preMul_secdf5983_1_fma(Double4x3 dest, double[] dd, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7, double _rd8, double _rd9, double _rd10, double _rd11, double _rd12, double _rd13, double _rd14, double _rd15, double _rd16, double _rd17, double _rd18, double _rd19, double _rd20, double _rd21, double _rd22, double _rd23, double _rd24, double _rd25, double _rd26, double _rd27) {
        dd[1] = java.lang.Math.fma(_rd13, _rd19, java.lang.Math.fma(_rd9, _rd18, java.lang.Math.fma(_rd1, _rd16, _rd5 * _rd17)));
        dd[2] = java.lang.Math.fma(_rd14, _rd19, java.lang.Math.fma(_rd10, _rd18, java.lang.Math.fma(_rd2, _rd16, _rd6 * _rd17)));
        dd[3] = java.lang.Math.fma(_rd15, _rd19, java.lang.Math.fma(_rd11, _rd18, java.lang.Math.fma(_rd3, _rd16, _rd7 * _rd17)));
        dd[4] = java.lang.Math.fma(_rd12, _rd23, java.lang.Math.fma(_rd8, _rd22, java.lang.Math.fma(_rd0, _rd20, _rd4 * _rd21)));
        dd[5] = java.lang.Math.fma(_rd13, _rd23, java.lang.Math.fma(_rd9, _rd22, java.lang.Math.fma(_rd1, _rd20, _rd5 * _rd21)));
        dd[6] = java.lang.Math.fma(_rd14, _rd23, java.lang.Math.fma(_rd10, _rd22, java.lang.Math.fma(_rd2, _rd20, _rd6 * _rd21)));
        dd[7] = java.lang.Math.fma(_rd15, _rd23, java.lang.Math.fma(_rd11, _rd22, java.lang.Math.fma(_rd3, _rd20, _rd7 * _rd21)));
        dd[8] = java.lang.Math.fma(_rd12, _rd27, java.lang.Math.fma(_rd8, _rd26, java.lang.Math.fma(_rd0, _rd24, _rd4 * _rd25)));
        dd[9] = java.lang.Math.fma(_rd13, _rd27, java.lang.Math.fma(_rd9, _rd26, java.lang.Math.fma(_rd1, _rd24, _rd5 * _rd25)));
        return preMul_secdf5983_2_fma(dest, dd, _rd2, _rd3, _rd6, _rd7, _rd10, _rd11, _rd14, _rd15, _rd24, _rd25, _rd26, _rd27);
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Double4x3 preMul_secdf5983_1_mulAdd(Double4x3 dest, double[] dd, double _rd0, double _rd1, double _rd2, double _rd3, double _rd4, double _rd5, double _rd6, double _rd7, double _rd8, double _rd9, double _rd10, double _rd11, double _rd12, double _rd13, double _rd14, double _rd15, double _rd16, double _rd17, double _rd18, double _rd19, double _rd20, double _rd21, double _rd22, double _rd23, double _rd24, double _rd25, double _rd26, double _rd27) {
        dd[1] = ((_rd13) * (_rd19) + (((_rd9) * (_rd18) + (((_rd1) * (_rd16) + (_rd5 * _rd17))))));
        dd[2] = ((_rd14) * (_rd19) + (((_rd10) * (_rd18) + (((_rd2) * (_rd16) + (_rd6 * _rd17))))));
        dd[3] = ((_rd15) * (_rd19) + (((_rd11) * (_rd18) + (((_rd3) * (_rd16) + (_rd7 * _rd17))))));
        dd[4] = ((_rd12) * (_rd23) + (((_rd8) * (_rd22) + (((_rd0) * (_rd20) + (_rd4 * _rd21))))));
        dd[5] = ((_rd13) * (_rd23) + (((_rd9) * (_rd22) + (((_rd1) * (_rd20) + (_rd5 * _rd21))))));
        dd[6] = ((_rd14) * (_rd23) + (((_rd10) * (_rd22) + (((_rd2) * (_rd20) + (_rd6 * _rd21))))));
        dd[7] = ((_rd15) * (_rd23) + (((_rd11) * (_rd22) + (((_rd3) * (_rd20) + (_rd7 * _rd21))))));
        dd[8] = ((_rd12) * (_rd27) + (((_rd8) * (_rd26) + (((_rd0) * (_rd24) + (_rd4 * _rd25))))));
        dd[9] = ((_rd13) * (_rd27) + (((_rd9) * (_rd26) + (((_rd1) * (_rd24) + (_rd5 * _rd25))))));
        return preMul_secdf5983_2_mulAdd(dest, dd, _rd2, _rd3, _rd6, _rd7, _rd10, _rd11, _rd14, _rd15, _rd24, _rd25, _rd26, _rd27);
    }

    /** Piece 3 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Double4x3 preMul_secdf5983_2_fma(Double4x3 dest, double[] dd, double _rd2, double _rd3, double _rd6, double _rd7, double _rd10, double _rd11, double _rd14, double _rd15, double _rd24, double _rd25, double _rd26, double _rd27) {
        dd[10] = java.lang.Math.fma(_rd14, _rd27, java.lang.Math.fma(_rd10, _rd26, java.lang.Math.fma(_rd2, _rd24, _rd6 * _rd25)));
        dd[11] = java.lang.Math.fma(_rd15, _rd27, java.lang.Math.fma(_rd11, _rd26, java.lang.Math.fma(_rd3, _rd24, _rd7 * _rd25)));
        return dest;
    }

    /** Piece 3 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Double4x3 preMul_secdf5983_2_mulAdd(Double4x3 dest, double[] dd, double _rd2, double _rd3, double _rd6, double _rd7, double _rd10, double _rd11, double _rd14, double _rd15, double _rd24, double _rd25, double _rd26, double _rd27) {
        dd[10] = ((_rd14) * (_rd27) + (((_rd10) * (_rd26) + (((_rd2) * (_rd24) + (_rd6 * _rd25))))));
        dd[11] = ((_rd15) * (_rd27) + (((_rd11) * (_rd26) + (((_rd3) * (_rd24) + (_rd7 * _rd25))))));
        return dest;
    }


    /**
     * Add {@code other} scaled by {@code weight} to this matrix and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Double4x3 addScaled(Double4x3R other, double weight, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x3Impl) other).data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = Math.fma(weight, otherData[0], sd[0]);
        dd[1] = Math.fma(weight, otherData[1], sd[1]);
        dd[2] = Math.fma(weight, otherData[2], sd[2]);
        dd[3] = Math.fma(weight, otherData[3], sd[3]);
        dd[4] = Math.fma(weight, otherData[4], sd[4]);
        dd[5] = Math.fma(weight, otherData[5], sd[5]);
        dd[6] = Math.fma(weight, otherData[6], sd[6]);
        dd[7] = Math.fma(weight, otherData[7], sd[7]);
        dd[8] = Math.fma(weight, otherData[8], sd[8]);
        dd[9] = Math.fma(weight, otherData[9], sd[9]);
        dd[10] = Math.fma(weight, otherData[10], sd[10]);
        dd[11] = Math.fma(weight, otherData[11], sd[11]);
        return dest;
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}, {@code m30}, {@code m31}, {@code m32}) scaled by
     * {@code weight} to this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m02}, {@code m10},
     *        {@code m11}, {@code m12}, {@code m20}, {@code m21}, {@code m22}, {@code m30},
     *        {@code m31}, {@code m32}) by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Double4x3 addScaled(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, double m30, double m31, double m32, double weight, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = Math.fma(weight, m00, sd[0]);
        dd[1] = Math.fma(weight, m10, sd[1]);
        dd[2] = Math.fma(weight, m20, sd[2]);
        dd[3] = Math.fma(weight, m30, sd[3]);
        dd[4] = Math.fma(weight, m01, sd[4]);
        dd[5] = Math.fma(weight, m11, sd[5]);
        dd[6] = Math.fma(weight, m21, sd[6]);
        dd[7] = Math.fma(weight, m31, sd[7]);
        dd[8] = Math.fma(weight, m02, sd[8]);
        dd[9] = Math.fma(weight, m12, sd[9]);
        dd[10] = Math.fma(weight, m22, sd[10]);
        dd[11] = Math.fma(weight, m32, sd[11]);
        return dest;
    }


    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the right operand of the product
     * @param dest will hold the result
     * @return dest
     */
    public Double4 mul(Double3R v, @Mutated Double4 dest) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[8], vZ, java.lang.Math.fma(sd[0], vX, sd[4] * vY));
            dd[1] = java.lang.Math.fma(sd[9], vZ, java.lang.Math.fma(sd[1], vX, sd[5] * vY));
            dd[2] = java.lang.Math.fma(sd[10], vZ, java.lang.Math.fma(sd[2], vX, sd[6] * vY));
            dd[3] = java.lang.Math.fma(sd[11], vZ, java.lang.Math.fma(sd[3], vX, sd[7] * vY));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((sd[8]) * (vZ) + (((sd[0]) * (vX) + (sd[4] * vY))));
            dd[1] = ((sd[9]) * (vZ) + (((sd[1]) * (vX) + (sd[5] * vY))));
            dd[2] = ((sd[10]) * (vZ) + (((sd[2]) * (vX) + (sd[6] * vY))));
            dd[3] = ((sd[11]) * (vZ) + (((sd[3]) * (vX) + (sd[7] * vY))));
            return dest;
        }
    }


    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 mul(double vX, double vY, double vZ, @Mutated Double4 dest) {
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[8], vZ, java.lang.Math.fma(sd[0], vX, sd[4] * vY));
            dd[1] = java.lang.Math.fma(sd[9], vZ, java.lang.Math.fma(sd[1], vX, sd[5] * vY));
            dd[2] = java.lang.Math.fma(sd[10], vZ, java.lang.Math.fma(sd[2], vX, sd[6] * vY));
            dd[3] = java.lang.Math.fma(sd[11], vZ, java.lang.Math.fma(sd[3], vX, sd[7] * vY));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((sd[8]) * (vZ) + (((sd[0]) * (vX) + (sd[4] * vY))));
            dd[1] = ((sd[9]) * (vZ) + (((sd[1]) * (vX) + (sd[5] * vY))));
            dd[2] = ((sd[10]) * (vZ) + (((sd[2]) * (vX) + (sd[6] * vY))));
            dd[3] = ((sd[11]) * (vZ) + (((sd[3]) * (vX) + (sd[7] * vY))));
            return dest;
        }
    }

    public double m00() { return data[0]; }
    public double m01() { return data[4]; }
    public double m02() { return data[8]; }
    public double m10() { return data[1]; }
    public double m11() { return data[5]; }
    public double m12() { return data[9]; }
    public double m20() { return data[2]; }
    public double m21() { return data[6]; }
    public double m22() { return data[10]; }
    public double m30() { return data[3]; }
    public double m31() { return data[7]; }
    public double m32() { return data[11]; }

    @Override public String toString() {
        return "Double4x3(\n    " + m00() + ", " + m01() + ", " + m02() + "\n    " + m10() + ", " + m11() + ", " + m12() + "\n    " + m20() + ", " + m21() + ", " + m22() + "\n    " + m30() + ", " + m31() + ", " + m32() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double4x3Impl)) return false;
        Double4x3Impl o = (Double4x3Impl) obj;
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
            && Double.isFinite(data[5])
            && Double.isFinite(data[6])
            && Double.isFinite(data[7])
            && Double.isFinite(data[8])
            && Double.isFinite(data[9])
            && Double.isFinite(data[10])
            && Double.isFinite(data[11]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1])
            || Double.isNaN(data[2])
            || Double.isNaN(data[3])
            || Double.isNaN(data[4])
            || Double.isNaN(data[5])
            || Double.isNaN(data[6])
            || Double.isNaN(data[7])
            || Double.isNaN(data[8])
            || Double.isNaN(data[9])
            || Double.isNaN(data[10])
            || Double.isNaN(data[11]);
    }

    @Override public boolean equalsEpsilon(Double4x3R other, double epsilon) {
        return java.lang.Math.abs(data[0] - other.m00()) <= epsilon
            && java.lang.Math.abs(data[4] - other.m01()) <= epsilon
            && java.lang.Math.abs(data[8] - other.m02()) <= epsilon
            && java.lang.Math.abs(data[1] - other.m10()) <= epsilon
            && java.lang.Math.abs(data[5] - other.m11()) <= epsilon
            && java.lang.Math.abs(data[9] - other.m12()) <= epsilon
            && java.lang.Math.abs(data[2] - other.m20()) <= epsilon
            && java.lang.Math.abs(data[6] - other.m21()) <= epsilon
            && java.lang.Math.abs(data[10] - other.m22()) <= epsilon
            && java.lang.Math.abs(data[3] - other.m30()) <= epsilon
            && java.lang.Math.abs(data[7] - other.m31()) <= epsilon
            && java.lang.Math.abs(data[11] - other.m32()) <= epsilon;
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        dest[offset + 6] = this.data[6];
        dest[offset + 7] = this.data[7];
        dest[offset + 8] = this.data[8];
        dest[offset + 9] = this.data[9];
        dest[offset + 10] = this.data[10];
        dest[offset + 11] = this.data[11];
        return dest;
    }
    public @Mutated Double4x3 loadCM(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
        this.data[7] = src[offset + 7];
        this.data[8] = src[offset + 8];
        this.data[9] = src[offset + 9];
        this.data[10] = src[offset + 10];
        this.data[11] = src[offset + 11];
        return this;
    }
    public DoubleBuffer storeCM(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    public DoubleBuffer storeCMRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return buf;
    }
    @Mutated public Double4x3 loadCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double4x3 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    @Mutated public Double4x3 loadCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return this;
    }
    public ByteBuffer storeCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 96) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 96);
        return buf;
    }
    public Double4x3 loadCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    public Double4x3 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public Double4x3 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 96) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x3 r = StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 96);
        return r;
    }
    public Double4x3 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }
    @Mutated public Double4x3 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }
    public MemorySegment storeCM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest); }
    public MemorySegment storeCM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest);
    }
    @Mutated public Double4x3 loadCM(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src); }
    public Double4x3 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src);
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        dest[offset + 4] = (float) this.data[4];
        dest[offset + 5] = (float) this.data[5];
        dest[offset + 6] = (float) this.data[6];
        dest[offset + 7] = (float) this.data[7];
        dest[offset + 8] = (float) this.data[8];
        dest[offset + 9] = (float) this.data[9];
        dest[offset + 10] = (float) this.data[10];
        dest[offset + 11] = (float) this.data[11];
        return dest;
    }
    public @Mutated Double4x3 loadCM(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
        this.data[7] = src[offset + 7];
        this.data[8] = src[offset + 8];
        this.data[9] = src[offset + 9];
        this.data[10] = src[offset + 10];
        this.data[11] = src[offset + 11];
        return this;
    }
    public FloatBuffer storeCM(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    public FloatBuffer storeCMRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return buf;
    }
    @Mutated public Double4x3 loadCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double4x3 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    @Mutated public Double4x3 loadCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return this;
    }
    public ByteBuffer storeCMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }
    public Double4x3 loadCMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, buf.position(), buf);
    }
    public Double4x3 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf);
    }
    public Double4x3 loadCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x3 r = StoreLoad.BB_OPS.loadCMFloatAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return r;
    }
    public Double4x3 storeCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address);
    }
    @Mutated public Double4x3 loadCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address);
    }
    public MemorySegment storeCMFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCMFloat(this, 0L, dest); }
    public MemorySegment storeCMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest);
    }
    @Mutated public Double4x3 loadCMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMFloat(this, 0L, src); }
    public Double4x3 loadCMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        if (dest == this.data) return storeRM_aliased(dest, offset);
        return storeRM_distinct(dest, offset);
    }
    private double[] storeRM_distinct(double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[4];
        dest[offset + 2] = this.data[8];
        dest[offset + 3] = this.data[1];
        dest[offset + 4] = this.data[5];
        dest[offset + 5] = this.data[9];
        dest[offset + 6] = this.data[2];
        dest[offset + 7] = this.data[6];
        dest[offset + 8] = this.data[10];
        dest[offset + 9] = this.data[3];
        dest[offset + 10] = this.data[7];
        dest[offset + 11] = this.data[11];
        return dest;
    }
    private double[] storeRM_aliased(double[] dest, int offset) {
        double[] d = this.data;
        double t1 = d[1];
        double t2 = d[2];
        double t3 = d[3];
        double t4 = d[4];
        double t5 = d[5];
        double t6 = d[6];
        double t7 = d[7];
        double t8 = d[8];
        double t9 = d[9];
        double t10 = d[10];
        double t11 = d[11];
        dest[offset] = d[0];
        dest[offset + 1] = t4;
        dest[offset + 2] = t8;
        dest[offset + 3] = t1;
        dest[offset + 4] = t5;
        dest[offset + 5] = t9;
        dest[offset + 6] = t2;
        dest[offset + 7] = t6;
        dest[offset + 8] = t10;
        dest[offset + 9] = t3;
        dest[offset + 10] = t7;
        dest[offset + 11] = t11;
        return dest;
    }
    @Mutated public Double4x3 loadRM(double[] src, int offset) {
        if (src == this.data) return loadRM_aliased(src, offset);
        return loadRM_distinct(src, offset);
    }
    private Double4x3 loadRM_distinct(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[4] = src[offset + 1];
        this.data[8] = src[offset + 2];
        this.data[1] = src[offset + 3];
        this.data[5] = src[offset + 4];
        this.data[9] = src[offset + 5];
        this.data[2] = src[offset + 6];
        this.data[6] = src[offset + 7];
        this.data[10] = src[offset + 8];
        this.data[3] = src[offset + 9];
        this.data[7] = src[offset + 10];
        this.data[11] = src[offset + 11];
        return this;
    }
    private Double4x3 loadRM_aliased(double[] src, int offset) {
        double t1 = src[offset + 1];
        double t2 = src[offset + 2];
        double t3 = src[offset + 3];
        double t4 = src[offset + 4];
        double t5 = src[offset + 5];
        double t6 = src[offset + 6];
        double t7 = src[offset + 7];
        double t8 = src[offset + 8];
        double t9 = src[offset + 9];
        double t10 = src[offset + 10];
        double t11 = src[offset + 11];
        double[] d = this.data;
        d[0] = src[offset];
        d[4] = t1;
        d[8] = t2;
        d[1] = t3;
        d[5] = t4;
        d[9] = t5;
        d[2] = t6;
        d[6] = t7;
        d[10] = t8;
        d[3] = t9;
        d[7] = t10;
        d[11] = t11;
        return this;
    }
    public DoubleBuffer storeRM(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public DoubleBuffer storeRMRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return buf;
    }
    @Mutated public Double4x3 loadRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double4x3 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    @Mutated public Double4x3 loadRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return this;
    }
    public ByteBuffer storeRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 96) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 96);
        return buf;
    }
    public Double4x3 loadRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    public Double4x3 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public Double4x3 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 96) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x3 r = StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 96);
        return r;
    }
    public Double4x3 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }
    @Mutated public Double4x3 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }
    public MemorySegment storeRM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest); }
    public MemorySegment storeRM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }
    @Mutated public Double4x3 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src); }
    public Double4x3 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[4];
        dest[offset + 2] = (float) this.data[8];
        dest[offset + 3] = (float) this.data[1];
        dest[offset + 4] = (float) this.data[5];
        dest[offset + 5] = (float) this.data[9];
        dest[offset + 6] = (float) this.data[2];
        dest[offset + 7] = (float) this.data[6];
        dest[offset + 8] = (float) this.data[10];
        dest[offset + 9] = (float) this.data[3];
        dest[offset + 10] = (float) this.data[7];
        dest[offset + 11] = (float) this.data[11];
        return dest;
    }
    public @Mutated Double4x3 loadRM(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[4] = src[offset + 1];
        this.data[8] = src[offset + 2];
        this.data[1] = src[offset + 3];
        this.data[5] = src[offset + 4];
        this.data[9] = src[offset + 5];
        this.data[2] = src[offset + 6];
        this.data[6] = src[offset + 7];
        this.data[10] = src[offset + 8];
        this.data[3] = src[offset + 9];
        this.data[7] = src[offset + 10];
        this.data[11] = src[offset + 11];
        return this;
    }
    public FloatBuffer storeRM(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public FloatBuffer storeRMRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return buf;
    }
    @Mutated public Double4x3 loadRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double4x3 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    @Mutated public Double4x3 loadRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return this;
    }
    public ByteBuffer storeRMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }
    public Double4x3 loadRMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, buf.position(), buf);
    }
    public Double4x3 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf);
    }
    public Double4x3 loadRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x3 r = StoreLoad.BB_OPS.loadRMFloatAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return r;
    }
    public Double4x3 storeRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address);
    }
    @Mutated public Double4x3 loadRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address);
    }
    public MemorySegment storeRMFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRMFloat(this, 0L, dest); }
    public MemorySegment storeRMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest);
    }
    @Mutated public Double4x3 loadRMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMFloat(this, 0L, src); }
    public Double4x3 loadRMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src);
    }

    public double[] storeCM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[_p1] = this.data[4];
        dest[_p1 + 1] = this.data[5];
        dest[_p1 + 2] = this.data[6];
        dest[_p1 + 3] = this.data[7];
        dest[_p2] = this.data[8];
        dest[_p2 + 1] = this.data[9];
        dest[_p2 + 2] = this.data[10];
        dest[_p2 + 3] = this.data[11];
        return dest;
    }
    public @Mutated Double4x3 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[_p1];
        this.data[5] = src[_p1 + 1];
        this.data[6] = src[_p1 + 2];
        this.data[7] = src[_p1 + 3];
        this.data[8] = src[_p2];
        this.data[9] = src[_p2 + 1];
        this.data[10] = src[_p2 + 2];
        this.data[11] = src[_p2 + 3];
        return this;
    }
    public DoubleBuffer storeCM(@Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public DoubleBuffer storeCMRelative(@Mutated DoubleBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
        return buf;
    }
    @Mutated public Double4x3 loadCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Double4x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double4x3 loadCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
        return this;
    }
    public ByteBuffer storeCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
        return buf;
    }
    public Double4x3 loadCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    public Double4x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public Double4x3 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x3 r = StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
        return r;
    }
    public Double4x3 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }
    @Mutated public Double4x3 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }
    public MemorySegment storeCM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest, stride); }
    public MemorySegment storeCM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }
    @Mutated public Double4x3 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src, stride); }
    public Double4x3 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src, stride);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        dest[_p1] = (float) this.data[4];
        dest[_p1 + 1] = (float) this.data[5];
        dest[_p1 + 2] = (float) this.data[6];
        dest[_p1 + 3] = (float) this.data[7];
        dest[_p2] = (float) this.data[8];
        dest[_p2 + 1] = (float) this.data[9];
        dest[_p2 + 2] = (float) this.data[10];
        dest[_p2 + 3] = (float) this.data[11];
        return dest;
    }
    public @Mutated Double4x3 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[_p1];
        this.data[5] = src[_p1 + 1];
        this.data[6] = src[_p1 + 2];
        this.data[7] = src[_p1 + 3];
        this.data[8] = src[_p2];
        this.data[9] = src[_p2 + 1];
        this.data[10] = src[_p2 + 2];
        this.data[11] = src[_p2 + 3];
        return this;
    }
    public FloatBuffer storeCM(@Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public FloatBuffer storeCMRelative(@Mutated FloatBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
        return buf;
    }
    @Mutated public Double4x3 loadCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Double4x3 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double4x3 loadCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
        return this;
    }
    public ByteBuffer storeCMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return buf;
    }
    public Double4x3 loadCMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, buf.position(), buf, stride);
    }
    public Double4x3 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf, stride);
    }
    public Double4x3 loadCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x3 r = StoreLoad.BB_OPS.loadCMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return r;
    }
    public Double4x3 storeCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double4x3 loadCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address, stride);
    }
    public MemorySegment storeCMFloat(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCMFloat(this, 0L, dest, stride); }
    public MemorySegment storeCMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest, stride);
    }
    @Mutated public Double4x3 loadCMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMFloat(this, 0L, src, stride); }
    public Double4x3 loadCMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src, stride);
    }

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
        if (dest == this.data) return storeRM_aliased(dest, offset, stride);
        return storeRM_distinct(dest, offset, stride);
    }
    private double[] storeRM_distinct(double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[4];
        dest[offset + 2] = this.data[8];
        dest[_p1] = this.data[1];
        dest[_p1 + 1] = this.data[5];
        dest[_p1 + 2] = this.data[9];
        dest[_p2] = this.data[2];
        dest[_p2 + 1] = this.data[6];
        dest[_p2 + 2] = this.data[10];
        dest[_p3] = this.data[3];
        dest[_p3 + 1] = this.data[7];
        dest[_p3 + 2] = this.data[11];
        return dest;
    }
    private double[] storeRM_aliased(double[] dest, int offset, int stride) {
        double[] d = this.data;
        double t1 = d[1];
        double t2 = d[2];
        double t3 = d[3];
        double t4 = d[4];
        double t5 = d[5];
        double t6 = d[6];
        double t7 = d[7];
        double t8 = d[8];
        double t9 = d[9];
        double t10 = d[10];
        double t11 = d[11];
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        dest[offset] = d[0];
        dest[offset + 1] = t4;
        dest[offset + 2] = t8;
        dest[_p1] = t1;
        dest[_p1 + 1] = t5;
        dest[_p1 + 2] = t9;
        dest[_p2] = t2;
        dest[_p2 + 1] = t6;
        dest[_p2 + 2] = t10;
        dest[_p3] = t3;
        dest[_p3 + 1] = t7;
        dest[_p3 + 2] = t11;
        return dest;
    }
    @Mutated public Double4x3 loadRM(double[] src, int offset, int stride) {
        if (src == this.data) return loadRM_aliased(src, offset, stride);
        return loadRM_distinct(src, offset, stride);
    }
    private Double4x3 loadRM_distinct(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        this.data[0] = src[offset];
        this.data[4] = src[offset + 1];
        this.data[8] = src[offset + 2];
        this.data[1] = src[_p1];
        this.data[5] = src[_p1 + 1];
        this.data[9] = src[_p1 + 2];
        this.data[2] = src[_p2];
        this.data[6] = src[_p2 + 1];
        this.data[10] = src[_p2 + 2];
        this.data[3] = src[_p3];
        this.data[7] = src[_p3 + 1];
        this.data[11] = src[_p3 + 2];
        return this;
    }
    private Double4x3 loadRM_aliased(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        double t1 = src[offset + 1];
        double t2 = src[offset + 2];
        double t3 = src[_p1];
        double t4 = src[_p1 + 1];
        double t5 = src[_p1 + 2];
        double t6 = src[_p2];
        double t7 = src[_p2 + 1];
        double t8 = src[_p2 + 2];
        double t9 = src[_p3];
        double t10 = src[_p3 + 1];
        double t11 = src[_p3 + 2];
        double[] d = this.data;
        d[0] = src[offset];
        d[4] = t1;
        d[8] = t2;
        d[1] = t3;
        d[5] = t4;
        d[9] = t5;
        d[2] = t6;
        d[6] = t7;
        d[10] = t8;
        d[3] = t9;
        d[7] = t10;
        d[11] = t11;
        return this;
    }
    public DoubleBuffer storeRM(@Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public DoubleBuffer storeRMRelative(@Mutated DoubleBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride);
        return buf;
    }
    @Mutated public Double4x3 loadRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Double4x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double4x3 loadRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride);
        return this;
    }
    public ByteBuffer storeRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + (4 * stride) * 8);
        return buf;
    }
    public Double4x3 loadRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    public Double4x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public Double4x3 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x3 r = StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + (4 * stride) * 8);
        return r;
    }
    public Double4x3 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }
    @Mutated public Double4x3 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }
    public MemorySegment storeRM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest, stride); }
    public MemorySegment storeRM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }
    @Mutated public Double4x3 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src, stride); }
    public Double4x3 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[4];
        dest[offset + 2] = (float) this.data[8];
        dest[_p1] = (float) this.data[1];
        dest[_p1 + 1] = (float) this.data[5];
        dest[_p1 + 2] = (float) this.data[9];
        dest[_p2] = (float) this.data[2];
        dest[_p2 + 1] = (float) this.data[6];
        dest[_p2 + 2] = (float) this.data[10];
        dest[_p3] = (float) this.data[3];
        dest[_p3 + 1] = (float) this.data[7];
        dest[_p3 + 2] = (float) this.data[11];
        return dest;
    }
    public @Mutated Double4x3 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        this.data[0] = src[offset];
        this.data[4] = src[offset + 1];
        this.data[8] = src[offset + 2];
        this.data[1] = src[_p1];
        this.data[5] = src[_p1 + 1];
        this.data[9] = src[_p1 + 2];
        this.data[2] = src[_p2];
        this.data[6] = src[_p2 + 1];
        this.data[10] = src[_p2 + 2];
        this.data[3] = src[_p3];
        this.data[7] = src[_p3 + 1];
        this.data[11] = src[_p3 + 2];
        return this;
    }
    public FloatBuffer storeRM(@Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public FloatBuffer storeRMRelative(@Mutated FloatBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride);
        return buf;
    }
    @Mutated public Double4x3 loadRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Double4x3 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double4x3 loadRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride);
        return this;
    }
    public ByteBuffer storeRMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (4 * stride) * 4);
        return buf;
    }
    public Double4x3 loadRMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, buf.position(), buf, stride);
    }
    public Double4x3 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf, stride);
    }
    public Double4x3 loadRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double4x3 r = StoreLoad.BB_OPS.loadRMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (4 * stride) * 4);
        return r;
    }
    public Double4x3 storeRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double4x3 loadRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address, stride);
    }
    public MemorySegment storeRMFloat(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRMFloat(this, 0L, dest, stride); }
    public MemorySegment storeRMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest, stride);
    }
    @Mutated public Double4x3 loadRMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMFloat(this, 0L, src, stride); }
    public Double4x3 loadRMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src, stride);
    }
}
