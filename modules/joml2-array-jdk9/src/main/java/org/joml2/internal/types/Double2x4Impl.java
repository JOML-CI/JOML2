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
 * Generated implementation of {@link Double2x4} backed by a {@code double[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Double2x4Impl implements Double2x4 {

    public double[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double2x4BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double2x4BbOpsUnsafe()
                        : new Double2x4BbOpsApi();
        static final Double2x4RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double2x4RawOpsUnsafe()
                        : new Double2x4RawOpsApi();
    }

    public Double2x4Impl() {
        data = new double[8];
        data[0] = 1;
        data[5] = 1;
    }

    public Double2x4Impl(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13) {
        double[] dd = this.data = new double[8];
        dd[0] = m00;
        dd[1] = m01;
        dd[2] = m02;
        dd[3] = m03;
        dd[4] = m10;
        dd[5] = m11;
        dd[6] = m12;
        dd[7] = m13;
    }

    public Double2x4Impl(Double2x4R src) {
        Double2x4Impl s = (Double2x4Impl) src;
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
    public Double2 getColumn(int col, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _idxSw0;
        double _idxSw1;
        switch (col) {
            case 0: _idxSw0 = sd[0]; _idxSw1 = sd[4]; break;
            case 1: _idxSw0 = sd[1]; _idxSw1 = sd[5]; break;
            case 2: _idxSw0 = sd[2]; _idxSw1 = sd[6]; break;
            case 3: _idxSw0 = sd[3]; _idxSw1 = sd[7]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dd[0] = _idxSw0;
        dd[1] = _idxSw1;
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
    public Double4 getRow(int row, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        double _idxSw3;
        switch (row) {
            case 0: _idxSw0 = sd[0]; _idxSw1 = sd[1]; _idxSw2 = sd[2]; _idxSw3 = sd[3]; break;
            case 1: _idxSw0 = sd[4]; _idxSw1 = sd[5]; _idxSw2 = sd[6]; _idxSw3 = sd[7]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw0;
        dd[1] = _idxSw1;
        dd[2] = _idxSw2;
        dd[3] = _idxSw3;
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
            return java.lang.Math.sqrt(java.lang.Math.fma(sd[0], sd[0], sd[1] * sd[1]) + java.lang.Math.fma(sd[2], sd[2], sd[3] * sd[3]) + (java.lang.Math.fma(sd[4], sd[4], sd[5] * sd[5]) + java.lang.Math.fma(sd[6], sd[6], sd[7] * sd[7])));
        } else {
            double[] sd = this.data;
            return java.lang.Math.sqrt(((sd[0]) * (sd[0]) + (sd[1] * sd[1])) + ((sd[2]) * (sd[2]) + (sd[3] * sd[3])) + (((sd[4]) * (sd[4]) + (sd[5] * sd[5])) + ((sd[6]) * (sd[6]) + (sd[7] * sd[7]))));
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
    public Double4x2 transpose(@Mutated Double4x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x2Impl) dest).data;
        dd[0] = sd[0];
        dd[1] = sd[1];
        dd[2] = sd[2];
        dd[3] = sd[3];
        dd[4] = sd[4];
        dd[5] = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
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
    public Double2x4 add(Double2x4R other, @Mutated Double2x4 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x4Impl) other).data;
        double[] dd = ((Double2x4Impl) dest).data;
        dd[0] = otherData[0] + sd[0];
        dd[1] = otherData[1] + sd[1];
        dd[2] = otherData[2] + sd[2];
        dd[3] = otherData[3] + sd[3];
        dd[4] = otherData[4] + sd[4];
        dd[5] = otherData[5] + sd[5];
        dd[6] = otherData[6] + sd[6];
        dd[7] = otherData[7] + sd[7];
        return dest;
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}) to this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Double2x4 add(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, @Mutated Double2x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x4Impl) dest).data;
        dd[0] = m00 + sd[0];
        dd[1] = m01 + sd[1];
        dd[2] = m02 + sd[2];
        dd[3] = m03 + sd[3];
        dd[4] = m10 + sd[4];
        dd[5] = m11 + sd[5];
        dd[6] = m12 + sd[6];
        dd[7] = m13 + sd[7];
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
    public Double2x4 mul(double scalar, @Mutated Double2x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x4Impl) dest).data;
        dd[0] = scalar * sd[0];
        dd[1] = scalar * sd[1];
        dd[2] = scalar * sd[2];
        dd[3] = scalar * sd[3];
        dd[4] = scalar * sd[4];
        dd[5] = scalar * sd[5];
        dd[6] = scalar * sd[6];
        dd[7] = scalar * sd[7];
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
    public Double2x4 negate(@Mutated Double2x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x4Impl) dest).data;
        dd[0] = -sd[0];
        dd[1] = -sd[1];
        dd[2] = -sd[2];
        dd[3] = -sd[3];
        dd[4] = -sd[4];
        dd[5] = -sd[5];
        dd[6] = -sd[6];
        dd[7] = -sd[7];
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
    public Double2x4 sub(Double2x4R other, @Mutated Double2x4 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x4Impl) other).data;
        double[] dd = ((Double2x4Impl) dest).data;
        dd[0] = sd[0] - otherData[0];
        dd[1] = sd[1] - otherData[1];
        dd[2] = sd[2] - otherData[2];
        dd[3] = sd[3] - otherData[3];
        dd[4] = sd[4] - otherData[4];
        dd[5] = sd[5] - otherData[5];
        dd[6] = sd[6] - otherData[6];
        dd[7] = sd[7] - otherData[7];
        return dest;
    }


    /**
     * Subtract ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}) from this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Double2x4 sub(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, @Mutated Double2x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x4Impl) dest).data;
        dd[0] = sd[0] - m00;
        dd[1] = sd[1] - m01;
        dd[2] = sd[2] - m02;
        dd[3] = sd[3] - m03;
        dd[4] = sd[4] - m10;
        dd[5] = sd[5] - m11;
        dd[6] = sd[6] - m12;
        dd[7] = sd[7] - m13;
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
    @Mutated public Double2x4 set(Double2x4R v) {
        double[] dd = this.data;
        double[] vData = ((Double2x4Impl) v).data;
        dd[0] = vData[0];
        dd[1] = vData[1];
        dd[2] = vData[2];
        dd[3] = vData[3];
        dd[4] = vData[4];
        dd[5] = vData[5];
        dd[6] = vData[6];
        dd[7] = vData[7];
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
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @return this
     */
    @Mutated public Double2x4 set(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13) {
        double[] dd = this.data;
        dd[0] = m00;
        dd[1] = m01;
        dd[2] = m02;
        dd[3] = m03;
        dd[4] = m10;
        dd[5] = m11;
        dd[6] = m12;
        dd[7] = m13;
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
    public Float2x4 toFloat(@Mutated Float2x4 dest) {
        double[] sd = this.data;
        float[] dd = ((Float2x4Impl) dest).data;
        dd[0] = (float) (sd[0]);
        dd[1] = (float) (sd[1]);
        dd[2] = (float) (sd[2]);
        dd[3] = (float) (sd[3]);
        dd[4] = (float) (sd[4]);
        dd[5] = (float) (sd[5]);
        dd[6] = (float) (sd[6]);
        dd[7] = (float) (sd[7]);
        return dest;
    }


    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Double2x4 makeIdentity() {
        double[] dd = this.data;
        dd[0] = 1.0;
        dd[1] = 0.0;
        dd[2] = 0.0;
        dd[3] = 0.0;
        dd[4] = 0.0;
        dd[5] = 1.0;
        dd[6] = 0.0;
        dd[7] = 0.0;
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
    public Double2x4 lerp(Double2x4R other, double t, @Mutated Double2x4 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x4Impl) other).data;
        double[] dd = ((Double2x4Impl) dest).data;
        dd[0] = Math.fma(t, otherData[0] - sd[0], sd[0]);
        dd[1] = Math.fma(t, otherData[1] - sd[1], sd[1]);
        dd[2] = Math.fma(t, otherData[2] - sd[2], sd[2]);
        dd[3] = Math.fma(t, otherData[3] - sd[3], sd[3]);
        dd[4] = Math.fma(t, otherData[4] - sd[4], sd[4]);
        dd[5] = Math.fma(t, otherData[5] - sd[5], sd[5]);
        dd[6] = Math.fma(t, otherData[6] - sd[6], sd[6]);
        dd[7] = Math.fma(t, otherData[7] - sd[7], sd[7]);
        return dest;
    }


    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m03}, {@code m10}, {@code m11}, {@code m12}, {@code m13}) using the interpolation
     * factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11}, {@code m12},
     * {@code m13}) (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double2x4 lerp(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double t, @Mutated Double2x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x4Impl) dest).data;
        dd[0] = Math.fma(t, m00 - sd[0], sd[0]);
        dd[1] = Math.fma(t, m01 - sd[1], sd[1]);
        dd[2] = Math.fma(t, m02 - sd[2], sd[2]);
        dd[3] = Math.fma(t, m03 - sd[3], sd[3]);
        dd[4] = Math.fma(t, m10 - sd[4], sd[4]);
        dd[5] = Math.fma(t, m11 - sd[5], sd[5]);
        dd[6] = Math.fma(t, m12 - sd[6], sd[6]);
        dd[7] = Math.fma(t, m13 - sd[7], sd[7]);
        return dest;
    }


    /**
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Both operands are identity-extended to this matrix's square size for the multiplication, and
     * the product is projected back onto this shape.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @param dest will hold the result
     * @return dest
     */
    public Double2x4 mul(Double2x4R right, @Mutated Double2x4 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x4Impl) right).data;
        double[] dd = ((Double2x4Impl) dest).data;
        double _rd0 = rightData[0];
        double _rd1 = rightData[1];
        double _rd2 = rightData[2];
        double _rd3 = rightData[3];
        double _rd4 = rightData[4];
        double _rd5 = rightData[5];
        double _rd6 = rightData[6];
        double _rd7 = rightData[7];
        double _rd8 = sd[0];
        double _rd9 = sd[1];
        double _rd10 = sd[4];
        double _rd11 = sd[5];
        dd[0] = Math.fma(_rd0, _rd8, _rd4 * _rd9);
        dd[1] = Math.fma(_rd1, _rd8, _rd5 * _rd9);
        dd[2] = Math.fma(_rd2, _rd8, Math.fma(_rd6, _rd9, sd[2]));
        dd[3] = Math.fma(_rd3, _rd8, Math.fma(_rd7, _rd9, sd[3]));
        dd[4] = Math.fma(_rd0, _rd10, _rd4 * _rd11);
        dd[5] = Math.fma(_rd1, _rd10, _rd5 * _rd11);
        dd[6] = Math.fma(_rd2, _rd10, Math.fma(_rd6, _rd11, sd[6]));
        dd[7] = Math.fma(_rd3, _rd10, Math.fma(_rd7, _rd11, sd[7]));
        return dest;
    }


    /**
     * Multiply this matrix by ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10},
     * {@code m11}, {@code m12}, {@code m13}) and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Both operands are identity-extended to this matrix's square size for the multiplication, and
     * the product is projected back onto this shape.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Double2x4 mul(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, @Mutated Double2x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x4Impl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[4];
        double _rd3 = sd[5];
        dd[0] = Math.fma(m00, _rd0, m10 * _rd1);
        dd[1] = Math.fma(m01, _rd0, m11 * _rd1);
        dd[2] = Math.fma(m02, _rd0, Math.fma(m12, _rd1, sd[2]));
        dd[3] = Math.fma(m03, _rd0, Math.fma(m13, _rd1, sd[3]));
        dd[4] = Math.fma(m00, _rd2, m10 * _rd3);
        dd[5] = Math.fma(m01, _rd2, m11 * _rd3);
        dd[6] = Math.fma(m02, _rd2, Math.fma(m12, _rd3, sd[6]));
        dd[7] = Math.fma(m03, _rd2, Math.fma(m13, _rd3, sd[7]));
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
    public Double2x4 preMul(Double2x4R other, @Mutated Double2x4 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x4Impl) other).data;
        double[] dd = ((Double2x4Impl) dest).data;
        double _rd0 = otherData[0];
        double _rd1 = otherData[1];
        double _rd2 = otherData[4];
        double _rd3 = otherData[5];
        double _rd4 = sd[0];
        double _rd5 = sd[1];
        double _rd6 = sd[2];
        double _rd7 = sd[3];
        double _rd8 = sd[4];
        double _rd9 = sd[5];
        double _rd10 = sd[6];
        double _rd11 = sd[7];
        dd[0] = Math.fma(_rd0, _rd4, _rd1 * _rd8);
        dd[1] = Math.fma(_rd0, _rd5, _rd1 * _rd9);
        dd[2] = Math.fma(_rd0, _rd6, Math.fma(_rd1, _rd10, otherData[2]));
        dd[3] = Math.fma(_rd0, _rd7, Math.fma(_rd1, _rd11, otherData[3]));
        dd[4] = Math.fma(_rd2, _rd4, _rd3 * _rd8);
        dd[5] = Math.fma(_rd2, _rd5, _rd3 * _rd9);
        dd[6] = Math.fma(_rd2, _rd6, Math.fma(_rd3, _rd10, otherData[6]));
        dd[7] = Math.fma(_rd2, _rd7, Math.fma(_rd3, _rd11, otherData[7]));
        return dest;
    }


    /**
     * Pre-multiply the transformation ({@code m00}, {@code m01}, {@code m02}, {@code m03},
     * {@code m10}, {@code m11}, {@code m12}, {@code m13}) onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the given transformation matrix, then the
     * new matrix will be {@code T * M}. So when transforming a vector {@code v} with the new matrix
     * by using {@code T * M * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Double2x4 preMul(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, @Mutated Double2x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x4Impl) dest).data;
        double _rd0 = sd[0];
        double _rd1 = sd[1];
        double _rd2 = sd[2];
        double _rd3 = sd[3];
        double _rd4 = sd[4];
        double _rd5 = sd[5];
        double _rd6 = sd[6];
        double _rd7 = sd[7];
        dd[0] = Math.fma(m00, _rd0, m01 * _rd4);
        dd[1] = Math.fma(m00, _rd1, m01 * _rd5);
        dd[2] = Math.fma(m00, _rd2, Math.fma(m01, _rd6, m02));
        dd[3] = Math.fma(m00, _rd3, Math.fma(m01, _rd7, m03));
        dd[4] = Math.fma(m10, _rd0, m11 * _rd4);
        dd[5] = Math.fma(m10, _rd1, m11 * _rd5);
        dd[6] = Math.fma(m10, _rd2, Math.fma(m11, _rd6, m12));
        dd[7] = Math.fma(m10, _rd3, Math.fma(m11, _rd7, m13));
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
    public Double2x4 addScaled(Double2x4R other, double weight, @Mutated Double2x4 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double2x4Impl) other).data;
        double[] dd = ((Double2x4Impl) dest).data;
        dd[0] = Math.fma(weight, otherData[0], sd[0]);
        dd[1] = Math.fma(weight, otherData[1], sd[1]);
        dd[2] = Math.fma(weight, otherData[2], sd[2]);
        dd[3] = Math.fma(weight, otherData[3], sd[3]);
        dd[4] = Math.fma(weight, otherData[4], sd[4]);
        dd[5] = Math.fma(weight, otherData[5], sd[5]);
        dd[6] = Math.fma(weight, otherData[6], sd[6]);
        dd[7] = Math.fma(weight, otherData[7], sd[7]);
        return dest;
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}) scaled by {@code weight} to this matrix and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m02}, {@code m03},
     *        {@code m10}, {@code m11}, {@code m12}, {@code m13}) by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Double2x4 addScaled(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double weight, @Mutated Double2x4 dest) {
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double2x4Impl) dest).data;
            dd[0] = java.lang.Math.fma(weight, m00, sd[0]);
            dd[1] = java.lang.Math.fma(weight, m01, sd[1]);
            dd[2] = java.lang.Math.fma(weight, m02, sd[2]);
            dd[3] = java.lang.Math.fma(weight, m03, sd[3]);
            dd[4] = java.lang.Math.fma(weight, m10, sd[4]);
            dd[5] = java.lang.Math.fma(weight, m11, sd[5]);
            dd[6] = java.lang.Math.fma(weight, m12, sd[6]);
            dd[7] = java.lang.Math.fma(weight, m13, sd[7]);
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double2x4Impl) dest).data;
            dd[0] = ((weight) * (m00) + (sd[0]));
            dd[1] = ((weight) * (m01) + (sd[1]));
            dd[2] = ((weight) * (m02) + (sd[2]));
            dd[3] = ((weight) * (m03) + (sd[3]));
            dd[4] = ((weight) * (m10) + (sd[4]));
            dd[5] = ((weight) * (m11) + (sd[5]));
            dd[6] = ((weight) * (m12) + (sd[6]));
            dd[7] = ((weight) * (m13) + (sd[7]));
            return dest;
        }
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
    public Double2 mul(Double4R v, @Mutated Double2 dest) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        double vW = v.w();
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[3], vW, java.lang.Math.fma(sd[2], vZ, java.lang.Math.fma(sd[0], vX, sd[1] * vY)));
            dd[1] = java.lang.Math.fma(sd[7], vW, java.lang.Math.fma(sd[6], vZ, java.lang.Math.fma(sd[4], vX, sd[5] * vY)));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = ((sd[3]) * (vW) + (((sd[2]) * (vZ) + (((sd[0]) * (vX) + (sd[1] * vY))))));
            dd[1] = ((sd[7]) * (vW) + (((sd[6]) * (vZ) + (((sd[4]) * (vX) + (sd[5] * vY))))));
            return dest;
        }
    }


    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vW the {@code w} component of the vector {@code (vX, vY, vZ, vW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 mul(double vX, double vY, double vZ, double vW, @Mutated Double2 dest) {
        if (Math.useFma()) {
            double[] sd = this.data;
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[3], vW, java.lang.Math.fma(sd[2], vZ, java.lang.Math.fma(sd[0], vX, sd[1] * vY)));
            dd[1] = java.lang.Math.fma(sd[7], vW, java.lang.Math.fma(sd[6], vZ, java.lang.Math.fma(sd[4], vX, sd[5] * vY)));
            return dest;
        } else {
            double[] sd = this.data;
            double[] dd = ((Double2Impl) dest).data;
            dd[0] = ((sd[3]) * (vW) + (((sd[2]) * (vZ) + (((sd[0]) * (vX) + (sd[1] * vY))))));
            dd[1] = ((sd[7]) * (vW) + (((sd[6]) * (vZ) + (((sd[4]) * (vX) + (sd[5] * vY))))));
            return dest;
        }
    }

    public double m00() { return data[0]; }
    public double m01() { return data[1]; }
    public double m02() { return data[2]; }
    public double m03() { return data[3]; }
    public double m10() { return data[4]; }
    public double m11() { return data[5]; }
    public double m12() { return data[6]; }
    public double m13() { return data[7]; }

    @Override public String toString() {
        return "Double2x4(\n    " + m00() + ", " + m01() + ", " + m02() + ", " + m03() + "\n    " + m10() + ", " + m11() + ", " + m12() + ", " + m13() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double2x4Impl)) return false;
        Double2x4Impl o = (Double2x4Impl) obj;
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
            && Double.isFinite(data[7]);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(data[0])
            || Double.isNaN(data[1])
            || Double.isNaN(data[2])
            || Double.isNaN(data[3])
            || Double.isNaN(data[4])
            || Double.isNaN(data[5])
            || Double.isNaN(data[6])
            || Double.isNaN(data[7]);
    }

    @Override public boolean equalsEpsilon(Double2x4R other, double epsilon) {
        return java.lang.Math.abs(data[0] - other.m00()) <= epsilon
            && java.lang.Math.abs(data[1] - other.m01()) <= epsilon
            && java.lang.Math.abs(data[2] - other.m02()) <= epsilon
            && java.lang.Math.abs(data[3] - other.m03()) <= epsilon
            && java.lang.Math.abs(data[4] - other.m10()) <= epsilon
            && java.lang.Math.abs(data[5] - other.m11()) <= epsilon
            && java.lang.Math.abs(data[6] - other.m12()) <= epsilon
            && java.lang.Math.abs(data[7] - other.m13()) <= epsilon;
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        if (dest == this.data) return storeCM_aliased(dest, offset);
        return storeCM_distinct(dest, offset);
    }
    private double[] storeCM_distinct(double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[4];
        dest[offset + 2] = this.data[1];
        dest[offset + 3] = this.data[5];
        dest[offset + 4] = this.data[2];
        dest[offset + 5] = this.data[6];
        dest[offset + 6] = this.data[3];
        dest[offset + 7] = this.data[7];
        return dest;
    }
    private double[] storeCM_aliased(double[] dest, int offset) {
        double[] d = this.data;
        double t1 = d[1];
        double t2 = d[2];
        double t3 = d[3];
        double t4 = d[4];
        double t5 = d[5];
        double t6 = d[6];
        double t7 = d[7];
        dest[offset] = d[0];
        dest[offset + 1] = t4;
        dest[offset + 2] = t1;
        dest[offset + 3] = t5;
        dest[offset + 4] = t2;
        dest[offset + 5] = t6;
        dest[offset + 6] = t3;
        dest[offset + 7] = t7;
        return dest;
    }
    @Mutated public Double2x4 loadCM(double[] src, int offset) {
        if (src == this.data) return loadCM_aliased(src, offset);
        return loadCM_distinct(src, offset);
    }
    private Double2x4 loadCM_distinct(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[4] = src[offset + 1];
        this.data[1] = src[offset + 2];
        this.data[5] = src[offset + 3];
        this.data[2] = src[offset + 4];
        this.data[6] = src[offset + 5];
        this.data[3] = src[offset + 6];
        this.data[7] = src[offset + 7];
        return this;
    }
    private Double2x4 loadCM_aliased(double[] src, int offset) {
        double t1 = src[offset + 1];
        double t2 = src[offset + 2];
        double t3 = src[offset + 3];
        double t4 = src[offset + 4];
        double t5 = src[offset + 5];
        double t6 = src[offset + 6];
        double t7 = src[offset + 7];
        double[] d = this.data;
        d[0] = src[offset];
        d[4] = t1;
        d[1] = t2;
        d[5] = t3;
        d[2] = t4;
        d[6] = t5;
        d[3] = t6;
        d[7] = t7;
        return this;
    }
    public DoubleBuffer storeCM(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    public DoubleBuffer storeCMRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return buf;
    }
    @Mutated public Double2x4 loadCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double2x4 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    @Mutated public Double2x4 loadCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return this;
    }
    public ByteBuffer storeCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }
    public Double2x4 loadCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    public Double2x4 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public Double2x4 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return r;
    }
    public Double2x4 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }
    @Mutated public Double2x4 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[4];
        dest[offset + 2] = (float) this.data[1];
        dest[offset + 3] = (float) this.data[5];
        dest[offset + 4] = (float) this.data[2];
        dest[offset + 5] = (float) this.data[6];
        dest[offset + 6] = (float) this.data[3];
        dest[offset + 7] = (float) this.data[7];
        return dest;
    }
    public @Mutated Double2x4 loadCM(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[4] = src[offset + 1];
        this.data[1] = src[offset + 2];
        this.data[5] = src[offset + 3];
        this.data[2] = src[offset + 4];
        this.data[6] = src[offset + 5];
        this.data[3] = src[offset + 6];
        this.data[7] = src[offset + 7];
        return this;
    }
    public FloatBuffer storeCM(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    public FloatBuffer storeCMRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return buf;
    }
    @Mutated public Double2x4 loadCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double2x4 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    @Mutated public Double2x4 loadCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return this;
    }
    public ByteBuffer storeCMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }
    public Double2x4 loadCMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, buf.position(), buf);
    }
    public Double2x4 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf);
    }
    public Double2x4 loadCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadCMFloatAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return r;
    }
    public Double2x4 storeCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address);
    }
    @Mutated public Double2x4 loadCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[offset + 4] = this.data[4];
        dest[offset + 5] = this.data[5];
        dest[offset + 6] = this.data[6];
        dest[offset + 7] = this.data[7];
        return dest;
    }
    public @Mutated Double2x4 loadRM(double[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
        this.data[7] = src[offset + 7];
        return this;
    }
    public DoubleBuffer storeRM(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public DoubleBuffer storeRMRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return buf;
    }
    @Mutated public Double2x4 loadRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double2x4 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    @Mutated public Double2x4 loadRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return this;
    }
    public ByteBuffer storeRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }
    public Double2x4 loadRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    public Double2x4 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public Double2x4 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return r;
    }
    public Double2x4 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }
    @Mutated public Double2x4 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        dest[offset + 4] = (float) this.data[4];
        dest[offset + 5] = (float) this.data[5];
        dest[offset + 6] = (float) this.data[6];
        dest[offset + 7] = (float) this.data[7];
        return dest;
    }
    public @Mutated Double2x4 loadRM(float[] src, int offset) {
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
        this.data[7] = src[offset + 7];
        return this;
    }
    public FloatBuffer storeRM(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public FloatBuffer storeRMRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return buf;
    }
    @Mutated public Double2x4 loadRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double2x4 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    @Mutated public Double2x4 loadRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return this;
    }
    public ByteBuffer storeRMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }
    public Double2x4 loadRMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, buf.position(), buf);
    }
    public Double2x4 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf);
    }
    public Double2x4 loadRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadRMFloatAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return r;
    }
    public Double2x4 storeRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address);
    }
    @Mutated public Double2x4 loadRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address);
    }

    public double[] storeCM(@Mutated double[] dest, int offset, int stride) {
        if (dest == this.data) return storeCM_aliased(dest, offset, stride);
        return storeCM_distinct(dest, offset, stride);
    }
    private double[] storeCM_distinct(double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[4];
        dest[_p1] = this.data[1];
        dest[_p1 + 1] = this.data[5];
        dest[_p2] = this.data[2];
        dest[_p2 + 1] = this.data[6];
        dest[_p3] = this.data[3];
        dest[_p3 + 1] = this.data[7];
        return dest;
    }
    private double[] storeCM_aliased(double[] dest, int offset, int stride) {
        double[] d = this.data;
        double t1 = d[1];
        double t2 = d[2];
        double t3 = d[3];
        double t4 = d[4];
        double t5 = d[5];
        double t6 = d[6];
        double t7 = d[7];
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        dest[offset] = d[0];
        dest[offset + 1] = t4;
        dest[_p1] = t1;
        dest[_p1 + 1] = t5;
        dest[_p2] = t2;
        dest[_p2 + 1] = t6;
        dest[_p3] = t3;
        dest[_p3 + 1] = t7;
        return dest;
    }
    @Mutated public Double2x4 loadCM(double[] src, int offset, int stride) {
        if (src == this.data) return loadCM_aliased(src, offset, stride);
        return loadCM_distinct(src, offset, stride);
    }
    private Double2x4 loadCM_distinct(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        this.data[0] = src[offset];
        this.data[4] = src[offset + 1];
        this.data[1] = src[_p1];
        this.data[5] = src[_p1 + 1];
        this.data[2] = src[_p2];
        this.data[6] = src[_p2 + 1];
        this.data[3] = src[_p3];
        this.data[7] = src[_p3 + 1];
        return this;
    }
    private Double2x4 loadCM_aliased(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        double t1 = src[offset + 1];
        double t2 = src[_p1];
        double t3 = src[_p1 + 1];
        double t4 = src[_p2];
        double t5 = src[_p2 + 1];
        double t6 = src[_p3];
        double t7 = src[_p3 + 1];
        double[] d = this.data;
        d[0] = src[offset];
        d[4] = t1;
        d[1] = t2;
        d[5] = t3;
        d[2] = t4;
        d[6] = t5;
        d[3] = t6;
        d[7] = t7;
        return this;
    }
    public DoubleBuffer storeCM(@Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public DoubleBuffer storeCMRelative(@Mutated DoubleBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride);
        return buf;
    }
    @Mutated public Double2x4 loadCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Double2x4 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double2x4 loadCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride);
        return this;
    }
    public ByteBuffer storeCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + (4 * stride) * 8);
        return buf;
    }
    public Double2x4 loadCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    public Double2x4 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public Double2x4 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + (4 * stride) * 8);
        return r;
    }
    public Double2x4 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }
    @Mutated public Double2x4 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[4];
        dest[_p1] = (float) this.data[1];
        dest[_p1 + 1] = (float) this.data[5];
        dest[_p2] = (float) this.data[2];
        dest[_p2 + 1] = (float) this.data[6];
        dest[_p3] = (float) this.data[3];
        dest[_p3 + 1] = (float) this.data[7];
        return dest;
    }
    public @Mutated Double2x4 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        this.data[0] = src[offset];
        this.data[4] = src[offset + 1];
        this.data[1] = src[_p1];
        this.data[5] = src[_p1 + 1];
        this.data[2] = src[_p2];
        this.data[6] = src[_p2 + 1];
        this.data[3] = src[_p3];
        this.data[7] = src[_p3 + 1];
        return this;
    }
    public FloatBuffer storeCM(@Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public FloatBuffer storeCMRelative(@Mutated FloatBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride);
        return buf;
    }
    @Mutated public Double2x4 loadCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Double2x4 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double2x4 loadCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride);
        return this;
    }
    public ByteBuffer storeCMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (4 * stride) * 4);
        return buf;
    }
    public Double2x4 loadCMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, buf.position(), buf, stride);
    }
    public Double2x4 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf, stride);
    }
    public Double2x4 loadCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadCMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (4 * stride) * 4);
        return r;
    }
    public Double2x4 storeCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double2x4 loadCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address, stride);
    }

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.data[0];
        dest[offset + 1] = this.data[1];
        dest[offset + 2] = this.data[2];
        dest[offset + 3] = this.data[3];
        dest[_p1] = this.data[4];
        dest[_p1 + 1] = this.data[5];
        dest[_p1 + 2] = this.data[6];
        dest[_p1 + 3] = this.data[7];
        return dest;
    }
    public @Mutated Double2x4 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[_p1];
        this.data[5] = src[_p1 + 1];
        this.data[6] = src[_p1 + 2];
        this.data[7] = src[_p1 + 3];
        return this;
    }
    public DoubleBuffer storeRM(@Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public DoubleBuffer storeRMRelative(@Mutated DoubleBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return buf;
    }
    @Mutated public Double2x4 loadRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Double2x4 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double2x4 loadRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return this;
    }
    public ByteBuffer storeRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 8);
        return buf;
    }
    public Double2x4 loadRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    public Double2x4 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public Double2x4 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 8);
        return r;
    }
    public Double2x4 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }
    @Mutated public Double2x4 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[1];
        dest[offset + 2] = (float) this.data[2];
        dest[offset + 3] = (float) this.data[3];
        dest[_p1] = (float) this.data[4];
        dest[_p1 + 1] = (float) this.data[5];
        dest[_p1 + 2] = (float) this.data[6];
        dest[_p1 + 3] = (float) this.data[7];
        return dest;
    }
    public @Mutated Double2x4 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.data[0] = src[offset];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[_p1];
        this.data[5] = src[_p1 + 1];
        this.data[6] = src[_p1 + 2];
        this.data[7] = src[_p1 + 3];
        return this;
    }
    public FloatBuffer storeRM(@Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public FloatBuffer storeRMRelative(@Mutated FloatBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return buf;
    }
    @Mutated public Double2x4 loadRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Double2x4 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double2x4 loadRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return this;
    }
    public ByteBuffer storeRMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 4);
        return buf;
    }
    public Double2x4 loadRMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, buf.position(), buf, stride);
    }
    public Double2x4 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf, stride);
    }
    public Double2x4 loadRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double2x4 r = StoreLoad.BB_OPS.loadRMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 4);
        return r;
    }
    public Double2x4 storeRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double2x4 loadRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address, stride);
    }
}
