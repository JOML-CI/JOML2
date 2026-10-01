// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.types;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link Float4x2} backed by a {@code float[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Float4x2Impl implements Float4x2 {

    public float[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Float4x2BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float4x2BbOpsUnsafe()
                        : new Float4x2BbOpsApi();
        static final Float4x2RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float4x2RawOpsUnsafe()
                        : new Float4x2RawOpsApi();
    }

    public Float4x2Impl() {
        data = new float[8];
        data[0] = 1;
        data[5] = 1;
    }

    public Float4x2Impl(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31) {
        float[] dd = this.data = new float[8];
        dd[0] = m00;
        dd[1] = m10;
        dd[2] = m20;
        dd[3] = m30;
        dd[4] = m01;
        dd[5] = m11;
        dd[6] = m21;
        dd[7] = m31;
    }

    public Float4x2Impl(Float4x2R src) {
        Float4x2Impl s = (Float4x2Impl) src;
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
    public Float4 getColumn(int col, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        float _idxSw3;
        switch (col) {
            case 0: _idxSw0 = sd[0]; _idxSw1 = sd[1]; _idxSw2 = sd[2]; _idxSw3 = sd[3]; break;
            case 1: _idxSw0 = sd[4]; _idxSw1 = sd[5]; _idxSw2 = sd[6]; _idxSw3 = sd[7]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dd[0] = _idxSw0;
        dd[1] = _idxSw1;
        dd[2] = _idxSw2;
        dd[3] = _idxSw3;
        return dest;
    }


    /**
     * Get the column at the given index of this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param col the column index
     * @param dest will hold the result
     * @return dest
     * @throws IndexOutOfBoundsException if {@code col} is not in {@code [0, COLUMNS)}
     */
    public Double4 getColumn(int col, @Mutated Double4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        float _idxSw4;
        float _idxSw5;
        float _idxSw6;
        float _idxSw7;
        switch (col) {
            case 0: _idxSw4 = sd[0]; _idxSw5 = sd[1]; _idxSw6 = sd[2]; _idxSw7 = sd[3]; break;
            case 1: _idxSw4 = sd[4]; _idxSw5 = sd[5]; _idxSw6 = sd[6]; _idxSw7 = sd[7]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dd[0] = _idxSw4;
        dd[1] = _idxSw5;
        dd[2] = _idxSw6;
        dd[3] = _idxSw7;
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
    public Float2 getRow(int row, @Mutated Float2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2Impl) dest).data;
        float _idxSw0;
        float _idxSw1;
        switch (row) {
            case 0: _idxSw0 = sd[0]; _idxSw1 = sd[4]; break;
            case 1: _idxSw0 = sd[1]; _idxSw1 = sd[5]; break;
            case 2: _idxSw0 = sd[2]; _idxSw1 = sd[6]; break;
            case 3: _idxSw0 = sd[3]; _idxSw1 = sd[7]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw0;
        dd[1] = _idxSw1;
        return dest;
    }


    /**
     * Get the row at the given index of this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param row the row index
     * @param dest will hold the result
     * @return dest
     * @throws IndexOutOfBoundsException if {@code row} is not in {@code [0, ROWS)}
     */
    public Double2 getRow(int row, @Mutated Double2 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        float _idxSw2;
        float _idxSw3;
        switch (row) {
            case 0: _idxSw2 = sd[0]; _idxSw3 = sd[4]; break;
            case 1: _idxSw2 = sd[1]; _idxSw3 = sd[5]; break;
            case 2: _idxSw2 = sd[2]; _idxSw3 = sd[6]; break;
            case 3: _idxSw2 = sd[3]; _idxSw3 = sd[7]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw2;
        dd[1] = _idxSw3;
        return dest;
    }


    /**
     * Compute the Frobenius norm of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Frobenius norm of this matrix
     */
    public float frobeniusNorm() {
        if (Math.useFma()) {
            float[] sd = this.data;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(sd[0], sd[0], sd[4] * sd[4]) + java.lang.Math.fma(sd[1], sd[1], sd[5] * sd[5]) + (java.lang.Math.fma(sd[2], sd[2], sd[6] * sd[6]) + java.lang.Math.fma(sd[3], sd[3], sd[7] * sd[7])));
        } else {
            float[] sd = this.data;
            return (float) java.lang.Math.sqrt(((sd[0]) * (sd[0]) + (sd[4] * sd[4])) + ((sd[1]) * (sd[1]) + (sd[5] * sd[5])) + (((sd[2]) * (sd[2]) + (sd[6] * sd[6])) + ((sd[3]) * (sd[3]) + (sd[7] * sd[7]))));
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
    public Float2x4 transpose(@Mutated Float2x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float2x4Impl) dest).data;
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
     * Transpose this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2x4 transpose(@Mutated Double2x4 dest) {
        float[] sd = this.data;
        double[] dd = ((Double2x4Impl) dest).data;
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
    public Float4x2 add(Float4x2R other, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x2Impl) other).data;
        float[] dd = ((Float4x2Impl) dest).data;
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
     * Add {@code other} to this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to add
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 add(Float4x2R other, @Mutated Double4x2 dest) {
        float m01 = other.m01();
        float m10 = other.m10();
        float m11 = other.m11();
        float m20 = other.m20();
        float m21 = other.m21();
        float m30 = other.m30();
        float m31 = other.m31();
        float[] sd = this.data;
        double[] dd = ((Double4x2Impl) dest).data;
        dd[0] = other.m00() + sd[0];
        dd[1] = m10 + sd[1];
        dd[2] = m20 + sd[2];
        dd[3] = m30 + sd[3];
        dd[4] = m01 + sd[4];
        dd[5] = m11 + sd[5];
        dd[6] = m21 + sd[6];
        dd[7] = m31 + sd[7];
        return dest;
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21},
     * {@code m30}, {@code m31}) to this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Float4x2 add(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x2Impl) dest).data;
        dd[0] = m00 + sd[0];
        dd[1] = m10 + sd[1];
        dd[2] = m20 + sd[2];
        dd[3] = m30 + sd[3];
        dd[4] = m01 + sd[4];
        dd[5] = m11 + sd[5];
        dd[6] = m21 + sd[6];
        dd[7] = m31 + sd[7];
        return dest;
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21},
     * {@code m30}, {@code m31}) to this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 add(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31, @Mutated Double4x2 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4x2Impl) dest).data;
        dd[0] = m00 + sd[0];
        dd[1] = m10 + sd[1];
        dd[2] = m20 + sd[2];
        dd[3] = m30 + sd[3];
        dd[4] = m01 + sd[4];
        dd[5] = m11 + sd[5];
        dd[6] = m21 + sd[6];
        dd[7] = m31 + sd[7];
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
    public Float4x2 mul(float scalar, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x2Impl) dest).data;
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
     * Multiply each component of this matrix by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 mul(float scalar, @Mutated Double4x2 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4x2Impl) dest).data;
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
    public Float4x2 negate(@Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x2Impl) dest).data;
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
     * Negate this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 negate(@Mutated Double4x2 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4x2Impl) dest).data;
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
    public Float4x2 sub(Float4x2R other, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x2Impl) other).data;
        float[] dd = ((Float4x2Impl) dest).data;
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
     * Subtract {@code other} from this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to subtract
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 sub(Float4x2R other, @Mutated Double4x2 dest) {
        float m01 = other.m01();
        float m10 = other.m10();
        float m11 = other.m11();
        float m20 = other.m20();
        float m21 = other.m21();
        float m30 = other.m30();
        float m31 = other.m31();
        float[] sd = this.data;
        double[] dd = ((Double4x2Impl) dest).data;
        dd[0] = sd[0] - other.m00();
        dd[1] = sd[1] - m10;
        dd[2] = sd[2] - m20;
        dd[3] = sd[3] - m30;
        dd[4] = sd[4] - m01;
        dd[5] = sd[5] - m11;
        dd[6] = sd[6] - m21;
        dd[7] = sd[7] - m31;
        return dest;
    }


    /**
     * Subtract ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21},
     * {@code m30}, {@code m31}) from this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Float4x2 sub(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x2Impl) dest).data;
        dd[0] = sd[0] - m00;
        dd[1] = sd[1] - m10;
        dd[2] = sd[2] - m20;
        dd[3] = sd[3] - m30;
        dd[4] = sd[4] - m01;
        dd[5] = sd[5] - m11;
        dd[6] = sd[6] - m21;
        dd[7] = sd[7] - m31;
        return dest;
    }


    /**
     * Subtract ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21},
     * {@code m30}, {@code m31}) from this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 sub(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31, @Mutated Double4x2 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4x2Impl) dest).data;
        dd[0] = sd[0] - m00;
        dd[1] = sd[1] - m10;
        dd[2] = sd[2] - m20;
        dd[3] = sd[3] - m30;
        dd[4] = sd[4] - m01;
        dd[5] = sd[5] - m11;
        dd[6] = sd[6] - m21;
        dd[7] = sd[7] - m31;
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
    @Mutated public Float4x2 set(Float4x2R v) {
        float[] dd = this.data;
        float[] vData = ((Float4x2Impl) v).data;
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
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @return this
     */
    @Mutated public Float4x2 set(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31) {
        float[] dd = this.data;
        dd[0] = m00;
        dd[1] = m10;
        dd[2] = m20;
        dd[3] = m30;
        dd[4] = m01;
        dd[5] = m11;
        dd[6] = m21;
        dd[7] = m31;
        return this;
    }


    /**
     * Convert this matrix to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 toDouble(@Mutated Double4x2 dest) {
        float[] sd = this.data;
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
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Float4x2 makeIdentity() {
        float[] dd = this.data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 0.0f;
        dd[5] = 1.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
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
    public Float4x2 lerp(Float4x2R other, float t, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x2Impl) other).data;
        float[] dd = ((Float4x2Impl) dest).data;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 lerp(Float4x2R other, float t, @Mutated Double4x2 dest) {
        float m01 = other.m01();
        float m10 = other.m10();
        float m11 = other.m11();
        float m20 = other.m20();
        float m21 = other.m21();
        float m30 = other.m30();
        float m31 = other.m31();
        float[] sd = this.data;
        double[] dd = ((Double4x2Impl) dest).data;
        dd[0] = Math.fma(t, other.m00() - sd[0], sd[0]);
        dd[1] = Math.fma(t, m10 - sd[1], sd[1]);
        dd[2] = Math.fma(t, m20 - sd[2], sd[2]);
        dd[3] = Math.fma(t, m30 - sd[3], sd[3]);
        dd[4] = Math.fma(t, m01 - sd[4], sd[4]);
        dd[5] = Math.fma(t, m11 - sd[5], sd[5]);
        dd[6] = Math.fma(t, m21 - sd[6], sd[6]);
        dd[7] = Math.fma(t, m31 - sd[7], sd[7]);
        return dest;
    }


    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m10},
     * {@code m11}, {@code m20}, {@code m21}, {@code m30}, {@code m31}) using the interpolation
     * factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21}, {@code m30},
     * {@code m31}) (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4x2 lerp(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31, float t, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x2Impl) dest).data;
        dd[0] = Math.fma(t, m00 - sd[0], sd[0]);
        dd[1] = Math.fma(t, m10 - sd[1], sd[1]);
        dd[2] = Math.fma(t, m20 - sd[2], sd[2]);
        dd[3] = Math.fma(t, m30 - sd[3], sd[3]);
        dd[4] = Math.fma(t, m01 - sd[4], sd[4]);
        dd[5] = Math.fma(t, m11 - sd[5], sd[5]);
        dd[6] = Math.fma(t, m21 - sd[6], sd[6]);
        dd[7] = Math.fma(t, m31 - sd[7], sd[7]);
        return dest;
    }


    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m10},
     * {@code m11}, {@code m20}, {@code m21}, {@code m30}, {@code m31}) using the interpolation
     * factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21}, {@code m30},
     * {@code m31}) (interpolation factor {@code 1}). Each linearly interpolated component is
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
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 lerp(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31, float t, @Mutated Double4x2 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4x2Impl) dest).data;
        dd[0] = Math.fma(t, m00 - sd[0], sd[0]);
        dd[1] = Math.fma(t, m10 - sd[1], sd[1]);
        dd[2] = Math.fma(t, m20 - sd[2], sd[2]);
        dd[3] = Math.fma(t, m30 - sd[3], sd[3]);
        dd[4] = Math.fma(t, m01 - sd[4], sd[4]);
        dd[5] = Math.fma(t, m11 - sd[5], sd[5]);
        dd[6] = Math.fma(t, m21 - sd[6], sd[6]);
        dd[7] = Math.fma(t, m31 - sd[7], sd[7]);
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
    public Float4x2 mul(Float2x2R right, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float4x2Impl) dest).data;
        float _rd0 = rightData[0];
        float _rd1 = rightData[1];
        float _rd2 = rightData[2];
        float _rd3 = rightData[3];
        float _rd4 = sd[0];
        float _rd5 = sd[1];
        float _rd6 = sd[2];
        float _rd7 = sd[3];
        float _rd8 = sd[4];
        float _rd9 = sd[5];
        float _rd10 = sd[6];
        float _rd11 = sd[7];
        dd[0] = Math.fma(_rd0, _rd4, _rd1 * _rd8);
        dd[1] = Math.fma(_rd0, _rd5, _rd1 * _rd9);
        dd[2] = Math.fma(_rd0, _rd6, _rd1 * _rd10);
        dd[3] = Math.fma(_rd0, _rd7, _rd1 * _rd11);
        dd[4] = Math.fma(_rd2, _rd4, _rd3 * _rd8);
        dd[5] = Math.fma(_rd2, _rd5, _rd3 * _rd9);
        dd[6] = Math.fma(_rd2, _rd6, _rd3 * _rd10);
        dd[7] = Math.fma(_rd2, _rd7, _rd3 * _rd11);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 mul(Float2x2R right, @Mutated Double4x2 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        double[] dd = ((Double4x2Impl) dest).data;
        dd[0] = Math.fma(rightData[0], sd[0], rightData[1] * sd[4]);
        dd[1] = Math.fma(rightData[0], sd[1], rightData[1] * sd[5]);
        dd[2] = Math.fma(rightData[0], sd[2], rightData[1] * sd[6]);
        dd[3] = Math.fma(rightData[0], sd[3], rightData[1] * sd[7]);
        dd[4] = Math.fma(rightData[2], sd[0], rightData[3] * sd[4]);
        dd[5] = Math.fma(rightData[2], sd[1], rightData[3] * sd[5]);
        dd[6] = Math.fma(rightData[2], sd[2], rightData[3] * sd[6]);
        dd[7] = Math.fma(rightData[2], sd[3], rightData[3] * sd[7]);
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
    public Float4x2 preMul(Float4x4R other, @Mutated Float4x2 dest) {
        if (Math.useFma()) return preMul_fma(other, dest);
        return preMul_mulAdd(other, dest);
    }

    /** {@code preMul} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x2 preMul_fma(Float4x4R other, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x4Impl) other).data;
        float[] dd = ((Float4x2Impl) dest).data;
        float _rd0 = otherData[0];
        float _rd1 = otherData[1];
        float _rd2 = otherData[2];
        float _rd3 = otherData[3];
        float _rd4 = otherData[4];
        float _rd5 = otherData[5];
        float _rd6 = otherData[6];
        float _rd7 = otherData[7];
        float _rd8 = otherData[8];
        float _rd9 = otherData[9];
        float _rd10 = otherData[10];
        float _rd11 = otherData[11];
        float _rd12 = otherData[12];
        float _rd13 = otherData[13];
        float _rd14 = otherData[14];
        float _rd15 = otherData[15];
        float _rd16 = sd[0];
        float _rd17 = sd[1];
        float _rd18 = sd[2];
        float _rd19 = sd[3];
        float _rd20 = sd[4];
        float _rd21 = sd[5];
        float _rd22 = sd[6];
        float _rd23 = sd[7];
        dd[0] = java.lang.Math.fma(_rd12, _rd19, java.lang.Math.fma(_rd8, _rd18, java.lang.Math.fma(_rd0, _rd16, _rd4 * _rd17)));
        dd[1] = java.lang.Math.fma(_rd13, _rd19, java.lang.Math.fma(_rd9, _rd18, java.lang.Math.fma(_rd1, _rd16, _rd5 * _rd17)));
        return preMul_s31fe6124_1_fma(dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7, _rd8, _rd9, _rd10, _rd11, _rd12, _rd13, _rd14, _rd15, _rd16, _rd17, _rd18, _rd19, _rd20, _rd21, _rd22, _rd23);
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x2 preMul_mulAdd(Float4x4R other, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x4Impl) other).data;
        float[] dd = ((Float4x2Impl) dest).data;
        float _rd0 = otherData[0];
        float _rd1 = otherData[1];
        float _rd2 = otherData[2];
        float _rd3 = otherData[3];
        float _rd4 = otherData[4];
        float _rd5 = otherData[5];
        float _rd6 = otherData[6];
        float _rd7 = otherData[7];
        float _rd8 = otherData[8];
        float _rd9 = otherData[9];
        float _rd10 = otherData[10];
        float _rd11 = otherData[11];
        float _rd12 = otherData[12];
        float _rd13 = otherData[13];
        float _rd14 = otherData[14];
        float _rd15 = otherData[15];
        float _rd16 = sd[0];
        float _rd17 = sd[1];
        float _rd18 = sd[2];
        float _rd19 = sd[3];
        float _rd20 = sd[4];
        float _rd21 = sd[5];
        float _rd22 = sd[6];
        float _rd23 = sd[7];
        dd[0] = ((_rd12) * (_rd19) + (((_rd8) * (_rd18) + (((_rd0) * (_rd16) + (_rd4 * _rd17))))));
        dd[1] = ((_rd13) * (_rd19) + (((_rd9) * (_rd18) + (((_rd1) * (_rd16) + (_rd5 * _rd17))))));
        return preMul_s31fe6124_1_mulAdd(dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7, _rd8, _rd9, _rd10, _rd11, _rd12, _rd13, _rd14, _rd15, _rd16, _rd17, _rd18, _rd19, _rd20, _rd21, _rd22, _rd23);
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Float4x2 preMul_s31fe6124_1_fma(Float4x2 dest, float[] dd, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7, float _rd8, float _rd9, float _rd10, float _rd11, float _rd12, float _rd13, float _rd14, float _rd15, float _rd16, float _rd17, float _rd18, float _rd19, float _rd20, float _rd21, float _rd22, float _rd23) {
        dd[2] = java.lang.Math.fma(_rd14, _rd19, java.lang.Math.fma(_rd10, _rd18, java.lang.Math.fma(_rd2, _rd16, _rd6 * _rd17)));
        dd[3] = java.lang.Math.fma(_rd15, _rd19, java.lang.Math.fma(_rd11, _rd18, java.lang.Math.fma(_rd3, _rd16, _rd7 * _rd17)));
        dd[4] = java.lang.Math.fma(_rd12, _rd23, java.lang.Math.fma(_rd8, _rd22, java.lang.Math.fma(_rd0, _rd20, _rd4 * _rd21)));
        dd[5] = java.lang.Math.fma(_rd13, _rd23, java.lang.Math.fma(_rd9, _rd22, java.lang.Math.fma(_rd1, _rd20, _rd5 * _rd21)));
        dd[6] = java.lang.Math.fma(_rd14, _rd23, java.lang.Math.fma(_rd10, _rd22, java.lang.Math.fma(_rd2, _rd20, _rd6 * _rd21)));
        dd[7] = java.lang.Math.fma(_rd15, _rd23, java.lang.Math.fma(_rd11, _rd22, java.lang.Math.fma(_rd3, _rd20, _rd7 * _rd21)));
        return dest;
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Float4x2 preMul_s31fe6124_1_mulAdd(Float4x2 dest, float[] dd, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7, float _rd8, float _rd9, float _rd10, float _rd11, float _rd12, float _rd13, float _rd14, float _rd15, float _rd16, float _rd17, float _rd18, float _rd19, float _rd20, float _rd21, float _rd22, float _rd23) {
        dd[2] = ((_rd14) * (_rd19) + (((_rd10) * (_rd18) + (((_rd2) * (_rd16) + (_rd6 * _rd17))))));
        dd[3] = ((_rd15) * (_rd19) + (((_rd11) * (_rd18) + (((_rd3) * (_rd16) + (_rd7 * _rd17))))));
        dd[4] = ((_rd12) * (_rd23) + (((_rd8) * (_rd22) + (((_rd0) * (_rd20) + (_rd4 * _rd21))))));
        dd[5] = ((_rd13) * (_rd23) + (((_rd9) * (_rd22) + (((_rd1) * (_rd20) + (_rd5 * _rd21))))));
        dd[6] = ((_rd14) * (_rd23) + (((_rd10) * (_rd22) + (((_rd2) * (_rd20) + (_rd6 * _rd21))))));
        dd[7] = ((_rd15) * (_rd23) + (((_rd11) * (_rd22) + (((_rd3) * (_rd20) + (_rd7 * _rd21))))));
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 preMul(Float4x4R other, @Mutated Double4x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x4Impl) other).data;
        double[] dd = ((Double4x2Impl) dest).data;
        dd[0] = Math.fma(otherData[12], sd[3], Math.fma(otherData[8], sd[2], Math.fma(otherData[0], sd[0], otherData[4] * sd[1])));
        dd[1] = Math.fma(otherData[13], sd[3], Math.fma(otherData[9], sd[2], Math.fma(otherData[1], sd[0], otherData[5] * sd[1])));
        dd[2] = Math.fma(otherData[14], sd[3], Math.fma(otherData[10], sd[2], Math.fma(otherData[2], sd[0], otherData[6] * sd[1])));
        dd[3] = Math.fma(otherData[15], sd[3], Math.fma(otherData[11], sd[2], Math.fma(otherData[3], sd[0], otherData[7] * sd[1])));
        dd[4] = Math.fma(otherData[12], sd[7], Math.fma(otherData[8], sd[6], Math.fma(otherData[0], sd[4], otherData[4] * sd[5])));
        return (Math.useFma() ? preMul_sd163df3b_1_fma(dest, sd, otherData, dd) : preMul_sd163df3b_1_mulAdd(dest, sd, otherData, dd));
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Double4x2 preMul_sd163df3b_1_fma(Double4x2 dest, float[] sd, float[] otherData, double[] dd) {
        dd[5] = java.lang.Math.fma(otherData[13], sd[7], java.lang.Math.fma(otherData[9], sd[6], java.lang.Math.fma(otherData[1], sd[4], otherData[5] * sd[5])));
        dd[6] = java.lang.Math.fma(otherData[14], sd[7], java.lang.Math.fma(otherData[10], sd[6], java.lang.Math.fma(otherData[2], sd[4], otherData[6] * sd[5])));
        dd[7] = java.lang.Math.fma(otherData[15], sd[7], java.lang.Math.fma(otherData[11], sd[6], java.lang.Math.fma(otherData[3], sd[4], otherData[7] * sd[5])));
        return dest;
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Double4x2 preMul_sd163df3b_1_mulAdd(Double4x2 dest, float[] sd, float[] otherData, double[] dd) {
        dd[5] = ((otherData[13]) * (sd[7]) + (((otherData[9]) * (sd[6]) + (((otherData[1]) * (sd[4]) + (otherData[5] * sd[5]))))));
        dd[6] = ((otherData[14]) * (sd[7]) + (((otherData[10]) * (sd[6]) + (((otherData[2]) * (sd[4]) + (otherData[6] * sd[5]))))));
        dd[7] = ((otherData[15]) * (sd[7]) + (((otherData[11]) * (sd[6]) + (((otherData[3]) * (sd[4]) + (otherData[7] * sd[5]))))));
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
    public Float4x2 addScaled(Float4x2R other, float weight, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x2Impl) other).data;
        float[] dd = ((Float4x2Impl) dest).data;
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
     * Add {@code other} scaled by {@code weight} to this matrix and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 addScaled(Float4x2R other, float weight, @Mutated Double4x2 dest) {
        return addScaled(other.m00(), other.m01(), other.m10(), other.m11(), other.m20(), other.m21(), other.m30(), other.m31(), weight, dest);
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21},
     * {@code m30}, {@code m31}) scaled by {@code weight} to this matrix and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m10}, {@code m11},
     *        {@code m20}, {@code m21}, {@code m30}, {@code m31}) by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Float4x2 addScaled(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31, float weight, @Mutated Float4x2 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(weight, m00, sd[0]);
            dd[1] = java.lang.Math.fma(weight, m10, sd[1]);
            dd[2] = java.lang.Math.fma(weight, m20, sd[2]);
            dd[3] = java.lang.Math.fma(weight, m30, sd[3]);
            dd[4] = java.lang.Math.fma(weight, m01, sd[4]);
            dd[5] = java.lang.Math.fma(weight, m11, sd[5]);
            dd[6] = java.lang.Math.fma(weight, m21, sd[6]);
            dd[7] = java.lang.Math.fma(weight, m31, sd[7]);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4x2Impl) dest).data;
            dd[0] = ((weight) * (m00) + (sd[0]));
            dd[1] = ((weight) * (m10) + (sd[1]));
            dd[2] = ((weight) * (m20) + (sd[2]));
            dd[3] = ((weight) * (m30) + (sd[3]));
            dd[4] = ((weight) * (m01) + (sd[4]));
            dd[5] = ((weight) * (m11) + (sd[5]));
            dd[6] = ((weight) * (m21) + (sd[6]));
            dd[7] = ((weight) * (m31) + (sd[7]));
            return dest;
        }
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21},
     * {@code m30}, {@code m31}) scaled by {@code weight} to this matrix and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m10}, {@code m11},
     *        {@code m20}, {@code m21}, {@code m30}, {@code m31}) by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 addScaled(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31, float weight, @Mutated Double4x2 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4x2Impl) dest).data;
            dd[0] = java.lang.Math.fma(weight, m00, sd[0]);
            dd[1] = java.lang.Math.fma(weight, m10, sd[1]);
            dd[2] = java.lang.Math.fma(weight, m20, sd[2]);
            dd[3] = java.lang.Math.fma(weight, m30, sd[3]);
            dd[4] = java.lang.Math.fma(weight, m01, sd[4]);
            dd[5] = java.lang.Math.fma(weight, m11, sd[5]);
            dd[6] = java.lang.Math.fma(weight, m21, sd[6]);
            dd[7] = java.lang.Math.fma(weight, m31, sd[7]);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4x2Impl) dest).data;
            dd[0] = ((weight) * (m00) + (sd[0]));
            dd[1] = ((weight) * (m10) + (sd[1]));
            dd[2] = ((weight) * (m20) + (sd[2]));
            dd[3] = ((weight) * (m30) + (sd[3]));
            dd[4] = ((weight) * (m01) + (sd[4]));
            dd[5] = ((weight) * (m11) + (sd[5]));
            dd[6] = ((weight) * (m21) + (sd[6]));
            dd[7] = ((weight) * (m31) + (sd[7]));
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
    public Float4 mul(Float2R v, @Mutated Float4 dest) {
        float vX = v.x();
        float vY = v.y();
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], vX, sd[4] * vY);
            dd[1] = java.lang.Math.fma(sd[1], vX, sd[5] * vY);
            dd[2] = java.lang.Math.fma(sd[2], vX, sd[6] * vY);
            dd[3] = java.lang.Math.fma(sd[3], vX, sd[7] * vY);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = ((sd[0]) * (vX) + (sd[4] * vY));
            dd[1] = ((sd[1]) * (vX) + (sd[5] * vY));
            dd[2] = ((sd[2]) * (vX) + (sd[6] * vY));
            dd[3] = ((sd[3]) * (vX) + (sd[7] * vY));
            return dest;
        }
    }


    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the right operand of the product
     * @param dest will hold the result
     * @return dest
     */
    public Double4 mul(Float2R v, @Mutated Double4 dest) {
        float vX = v.x();
        float vY = v.y();
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], vX, sd[4] * vY);
            dd[1] = java.lang.Math.fma(sd[1], vX, sd[5] * vY);
            dd[2] = java.lang.Math.fma(sd[2], vX, sd[6] * vY);
            dd[3] = java.lang.Math.fma(sd[3], vX, sd[7] * vY);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((sd[0]) * (vX) + (sd[4] * vY));
            dd[1] = ((sd[1]) * (vX) + (sd[5] * vY));
            dd[2] = ((sd[2]) * (vX) + (sd[6] * vY));
            dd[3] = ((sd[3]) * (vX) + (sd[7] * vY));
            return dest;
        }
    }


    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Float4 mul(float vX, float vY, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], vX, sd[4] * vY);
            dd[1] = java.lang.Math.fma(sd[1], vX, sd[5] * vY);
            dd[2] = java.lang.Math.fma(sd[2], vX, sd[6] * vY);
            dd[3] = java.lang.Math.fma(sd[3], vX, sd[7] * vY);
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = ((sd[0]) * (vX) + (sd[4] * vY));
            dd[1] = ((sd[1]) * (vX) + (sd[5] * vY));
            dd[2] = ((sd[2]) * (vX) + (sd[6] * vY));
            dd[3] = ((sd[3]) * (vX) + (sd[7] * vY));
            return dest;
        }
    }


    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 mul(float vX, float vY, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[0], vX, sd[4] * vY);
            dd[1] = java.lang.Math.fma(sd[1], vX, sd[5] * vY);
            dd[2] = java.lang.Math.fma(sd[2], vX, sd[6] * vY);
            dd[3] = java.lang.Math.fma(sd[3], vX, sd[7] * vY);
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((sd[0]) * (vX) + (sd[4] * vY));
            dd[1] = ((sd[1]) * (vX) + (sd[5] * vY));
            dd[2] = ((sd[2]) * (vX) + (sd[6] * vY));
            dd[3] = ((sd[3]) * (vX) + (sd[7] * vY));
            return dest;
        }
    }

    public float m00() { return data[0]; }
    public float m01() { return data[4]; }
    public float m10() { return data[1]; }
    public float m11() { return data[5]; }
    public float m20() { return data[2]; }
    public float m21() { return data[6]; }
    public float m30() { return data[3]; }
    public float m31() { return data[7]; }

    @Override public String toString() {
        return "Float4x2(\n    " + m00() + ", " + m01() + "\n    " + m10() + ", " + m11() + "\n    " + m20() + ", " + m21() + "\n    " + m30() + ", " + m31() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float4x2Impl)) return false;
        Float4x2Impl o = (Float4x2Impl) obj;
        return java.util.Arrays.equals(data, o.data);
    }

    @Override public int hashCode() {
        return java.util.Arrays.hashCode(data);
    }

    @Override public boolean isFinite() {
        return Float.isFinite(data[0])
            && Float.isFinite(data[1])
            && Float.isFinite(data[2])
            && Float.isFinite(data[3])
            && Float.isFinite(data[4])
            && Float.isFinite(data[5])
            && Float.isFinite(data[6])
            && Float.isFinite(data[7]);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(data[0])
            || Float.isNaN(data[1])
            || Float.isNaN(data[2])
            || Float.isNaN(data[3])
            || Float.isNaN(data[4])
            || Float.isNaN(data[5])
            || Float.isNaN(data[6])
            || Float.isNaN(data[7]);
    }

    @Override public boolean equalsEpsilon(Float4x2R other, float epsilon) {
        return java.lang.Math.abs(data[0] - other.m00()) <= epsilon
            && java.lang.Math.abs(data[4] - other.m01()) <= epsilon
            && java.lang.Math.abs(data[1] - other.m10()) <= epsilon
            && java.lang.Math.abs(data[5] - other.m11()) <= epsilon
            && java.lang.Math.abs(data[2] - other.m20()) <= epsilon
            && java.lang.Math.abs(data[6] - other.m21()) <= epsilon
            && java.lang.Math.abs(data[3] - other.m30()) <= epsilon
            && java.lang.Math.abs(data[7] - other.m31()) <= epsilon;
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
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
    public @Mutated Float4x2 loadCM(float[] src, int offset) {
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
    @Mutated public Float4x2 loadCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Float4x2 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    @Mutated public Float4x2 loadCMRelative(FloatBuffer buf) {
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
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }
    public Float4x2 loadCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    public Float4x2 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public Float4x2 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x2 r = StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return r;
    }
    public Float4x2 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }
    @Mutated public Float4x2 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
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
        return dest;
    }
    public @Mutated Float4x2 loadCM(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        this.data[4] = (float) src[offset + 4];
        this.data[5] = (float) src[offset + 5];
        this.data[6] = (float) src[offset + 6];
        this.data[7] = (float) src[offset + 7];
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
    @Mutated public Float4x2 loadCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Float4x2 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    @Mutated public Float4x2 loadCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return this;
    }
    public ByteBuffer storeCMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }
    public Float4x2 loadCMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, buf.position(), buf);
    }
    public Float4x2 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf);
    }
    public Float4x2 loadCMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x2 r = StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return r;
    }
    public Float4x2 storeCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address);
    }
    @Mutated public Float4x2 loadCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        if (dest == this.data) return storeRM_aliased(dest, offset);
        return storeRM_distinct(dest, offset);
    }
    private float[] storeRM_distinct(float[] dest, int offset) {
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
    private float[] storeRM_aliased(float[] dest, int offset) {
        float[] d = this.data;
        float t1 = d[1];
        float t2 = d[2];
        float t3 = d[3];
        float t4 = d[4];
        float t5 = d[5];
        float t6 = d[6];
        float t7 = d[7];
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
    @Mutated public Float4x2 loadRM(float[] src, int offset) {
        if (src == this.data) return loadRM_aliased(src, offset);
        return loadRM_distinct(src, offset);
    }
    private Float4x2 loadRM_distinct(float[] src, int offset) {
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
    private Float4x2 loadRM_aliased(float[] src, int offset) {
        float t1 = src[offset + 1];
        float t2 = src[offset + 2];
        float t3 = src[offset + 3];
        float t4 = src[offset + 4];
        float t5 = src[offset + 5];
        float t6 = src[offset + 6];
        float t7 = src[offset + 7];
        float[] d = this.data;
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
    @Mutated public Float4x2 loadRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Float4x2 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    @Mutated public Float4x2 loadRMRelative(FloatBuffer buf) {
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
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }
    public Float4x2 loadRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    public Float4x2 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public Float4x2 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x2 r = StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return r;
    }
    public Float4x2 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }
    @Mutated public Float4x2 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
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
    public @Mutated Float4x2 loadRM(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[4] = (float) src[offset + 1];
        this.data[1] = (float) src[offset + 2];
        this.data[5] = (float) src[offset + 3];
        this.data[2] = (float) src[offset + 4];
        this.data[6] = (float) src[offset + 5];
        this.data[3] = (float) src[offset + 6];
        this.data[7] = (float) src[offset + 7];
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
    @Mutated public Float4x2 loadRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Float4x2 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    @Mutated public Float4x2 loadRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return this;
    }
    public ByteBuffer storeRMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }
    public Float4x2 loadRMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, buf.position(), buf);
    }
    public Float4x2 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf);
    }
    public Float4x2 loadRMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x2 r = StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return r;
    }
    public Float4x2 storeRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address);
    }
    @Mutated public Float4x2 loadRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
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
    public @Mutated Float4x2 loadCM(float[] src, int offset, int stride) {
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
    public FloatBuffer storeCM(@Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public FloatBuffer storeCMRelative(@Mutated FloatBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return buf;
    }
    @Mutated public Float4x2 loadCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Float4x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float4x2 loadCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return this;
    }
    public ByteBuffer storeCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 4);
        return buf;
    }
    public Float4x2 loadCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    public Float4x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public Float4x2 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x2 r = StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 4);
        return r;
    }
    public Float4x2 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }
    @Mutated public Float4x2 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }

    public double[] storeCM(@Mutated double[] dest, int offset, int stride) {
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
    public @Mutated Float4x2 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        this.data[4] = (float) src[_p1];
        this.data[5] = (float) src[_p1 + 1];
        this.data[6] = (float) src[_p1 + 2];
        this.data[7] = (float) src[_p1 + 3];
        return this;
    }
    public DoubleBuffer storeCM(@Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public DoubleBuffer storeCMRelative(@Mutated DoubleBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return buf;
    }
    @Mutated public Float4x2 loadCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Float4x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float4x2 loadCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return this;
    }
    public ByteBuffer storeCMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 8);
        return buf;
    }
    public Float4x2 loadCMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, buf.position(), buf, stride);
    }
    public Float4x2 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf, stride);
    }
    public Float4x2 loadCMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x2 r = StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 8);
        return r;
    }
    public Float4x2 storeCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address, stride);
    }
    @Mutated public Float4x2 loadCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
        if (dest == this.data) return storeRM_aliased(dest, offset, stride);
        return storeRM_distinct(dest, offset, stride);
    }
    private float[] storeRM_distinct(float[] dest, int offset, int stride) {
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
    private float[] storeRM_aliased(float[] dest, int offset, int stride) {
        float[] d = this.data;
        float t1 = d[1];
        float t2 = d[2];
        float t3 = d[3];
        float t4 = d[4];
        float t5 = d[5];
        float t6 = d[6];
        float t7 = d[7];
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
    @Mutated public Float4x2 loadRM(float[] src, int offset, int stride) {
        if (src == this.data) return loadRM_aliased(src, offset, stride);
        return loadRM_distinct(src, offset, stride);
    }
    private Float4x2 loadRM_distinct(float[] src, int offset, int stride) {
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
    private Float4x2 loadRM_aliased(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        float t1 = src[offset + 1];
        float t2 = src[_p1];
        float t3 = src[_p1 + 1];
        float t4 = src[_p2];
        float t5 = src[_p2 + 1];
        float t6 = src[_p3];
        float t7 = src[_p3 + 1];
        float[] d = this.data;
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
    @Mutated public Float4x2 loadRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Float4x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float4x2 loadRMRelative(FloatBuffer buf, int stride) {
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
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + (4 * stride) * 4);
        return buf;
    }
    public Float4x2 loadRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    public Float4x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public Float4x2 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x2 r = StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + (4 * stride) * 4);
        return r;
    }
    public Float4x2 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }
    @Mutated public Float4x2 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
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
    public @Mutated Float4x2 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        this.data[0] = (float) src[offset];
        this.data[4] = (float) src[offset + 1];
        this.data[1] = (float) src[_p1];
        this.data[5] = (float) src[_p1 + 1];
        this.data[2] = (float) src[_p2];
        this.data[6] = (float) src[_p2 + 1];
        this.data[3] = (float) src[_p3];
        this.data[7] = (float) src[_p3 + 1];
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
    @Mutated public Float4x2 loadRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Float4x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float4x2 loadRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride);
        return this;
    }
    public ByteBuffer storeRMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + (4 * stride) * 8);
        return buf;
    }
    public Float4x2 loadRMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, buf.position(), buf, stride);
    }
    public Float4x2 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf, stride);
    }
    public Float4x2 loadRMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x2 r = StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + (4 * stride) * 8);
        return r;
    }
    public Float4x2 storeRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address, stride);
    }
    @Mutated public Float4x2 loadRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address, stride);
    }
}
