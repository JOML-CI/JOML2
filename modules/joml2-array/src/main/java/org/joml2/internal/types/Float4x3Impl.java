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
 * Generated implementation of {@link Float4x3} backed by a {@code float[]} array.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Float4x3Impl implements Float4x3 {

    public float[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Float4x3SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float4x3SegOpsUnsafe()
                        : new Float4x3SegOpsMS();
        static final Float4x3BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float4x3BbOpsUnsafe()
                        : new Float4x3BbOpsApi();
        static final Float4x3RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float4x3RawOpsUnsafe()
                        : new Float4x3RawOpsApi();
    }

    public Float4x3Impl() {
        data = new float[12];
        data[0] = 1;
        data[5] = 1;
        data[10] = 1;
    }

    public Float4x3Impl(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32) {
        float[] dd = this.data = new float[12];
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

    public Float4x3Impl(Float4x3R src) {
        Float4x3Impl s = (Float4x3Impl) src;
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
            case 2: _idxSw4 = sd[8]; _idxSw5 = sd[9]; _idxSw6 = sd[10]; _idxSw7 = sd[11]; break;
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
    public Float3 getRow(int row, @Mutated Float3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3Impl) dest).data;
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
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
    public Double3 getRow(int row, @Mutated Double3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double3Impl) dest).data;
        float _idxSw3;
        float _idxSw4;
        float _idxSw5;
        switch (row) {
            case 0: _idxSw3 = sd[0]; _idxSw4 = sd[4]; _idxSw5 = sd[8]; break;
            case 1: _idxSw3 = sd[1]; _idxSw4 = sd[5]; _idxSw5 = sd[9]; break;
            case 2: _idxSw3 = sd[2]; _idxSw4 = sd[6]; _idxSw5 = sd[10]; break;
            case 3: _idxSw3 = sd[3]; _idxSw4 = sd[7]; _idxSw5 = sd[11]; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dd[0] = _idxSw3;
        dd[1] = _idxSw4;
        dd[2] = _idxSw5;
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
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(sd[0], sd[0], java.lang.Math.fma(sd[4], sd[4], sd[8] * sd[8])) + java.lang.Math.fma(sd[1], sd[1], java.lang.Math.fma(sd[5], sd[5], sd[9] * sd[9])) + (java.lang.Math.fma(sd[2], sd[2], java.lang.Math.fma(sd[6], sd[6], sd[10] * sd[10])) + java.lang.Math.fma(sd[3], sd[3], java.lang.Math.fma(sd[7], sd[7], sd[11] * sd[11]))));
        } else {
            float[] sd = this.data;
            return (float) java.lang.Math.sqrt(((sd[0]) * (sd[0]) + (((sd[4]) * (sd[4]) + (sd[8] * sd[8])))) + ((sd[1]) * (sd[1]) + (((sd[5]) * (sd[5]) + (sd[9] * sd[9])))) + (((sd[2]) * (sd[2]) + (((sd[6]) * (sd[6]) + (sd[10] * sd[10])))) + ((sd[3]) * (sd[3]) + (((sd[7]) * (sd[7]) + (sd[11] * sd[11]))))));
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
    public Float3x4 transpose(@Mutated Float3x4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float3x4Impl) dest).data;
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
        ((Float3x4Impl) dest).properties = Joml.BIT_AFFINE;
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
    public Double3x4 transpose(@Mutated Double3x4 dest) {
        float[] sd = this.data;
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
    public Float4x3 add(Float4x3R other, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x3Impl) other).data;
        float[] dd = ((Float4x3Impl) dest).data;
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
    public Double4x3 add(Float4x3R other, @Mutated Double4x3 dest) {
        float m01 = other.m01();
        float m02 = other.m02();
        float m10 = other.m10();
        float m11 = other.m11();
        float m12 = other.m12();
        float m20 = other.m20();
        float m21 = other.m21();
        float m22 = other.m22();
        float m30 = other.m30();
        float m31 = other.m31();
        float m32 = other.m32();
        float[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = other.m00() + sd[0];
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
    public Float4x3 add(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x3Impl) dest).data;
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
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}, {@code m30}, {@code m31}, {@code m32}) to this matrix
     * and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4x3 add(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32, @Mutated Double4x3 dest) {
        float[] sd = this.data;
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
    public Float4x3 mul(float scalar, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x3Impl) dest).data;
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
    public Double4x3 mul(float scalar, @Mutated Double4x3 dest) {
        float[] sd = this.data;
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
    public Float4x3 negate(@Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x3Impl) dest).data;
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
    public Double4x3 negate(@Mutated Double4x3 dest) {
        float[] sd = this.data;
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
    public Float4x3 sub(Float4x3R other, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x3Impl) other).data;
        float[] dd = ((Float4x3Impl) dest).data;
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
    public Double4x3 sub(Float4x3R other, @Mutated Double4x3 dest) {
        float m01 = other.m01();
        float m02 = other.m02();
        float m10 = other.m10();
        float m11 = other.m11();
        float m12 = other.m12();
        float m20 = other.m20();
        float m21 = other.m21();
        float m22 = other.m22();
        float m30 = other.m30();
        float m31 = other.m31();
        float m32 = other.m32();
        float[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = sd[0] - other.m00();
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
    public Float4x3 sub(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x3Impl) dest).data;
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
     * Subtract ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}, {@code m30}, {@code m31}, {@code m32}) from this
     * matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4x3 sub(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32, @Mutated Double4x3 dest) {
        float[] sd = this.data;
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
    @Mutated public Float4x3 set(Float4x3R v) {
        float[] dd = this.data;
        float[] vData = ((Float4x3Impl) v).data;
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
    @Mutated public Float4x3 set(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32) {
        float[] dd = this.data;
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
     * Convert this matrix to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double4x3 toDouble(@Mutated Double4x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
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
        return dest;
    }


    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Float4x3 makeIdentity() {
        float[] dd = this.data;
        dd[0] = 1.0f;
        dd[1] = 0.0f;
        dd[2] = 0.0f;
        dd[3] = 0.0f;
        dd[4] = 0.0f;
        dd[5] = 1.0f;
        dd[6] = 0.0f;
        dd[7] = 0.0f;
        dd[8] = 0.0f;
        dd[9] = 0.0f;
        dd[10] = 1.0f;
        dd[11] = 0.0f;
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
    public Float4x3 lerp(Float4x3R other, float t, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x3Impl) other).data;
        float[] dd = ((Float4x3Impl) dest).data;
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
    public Double4x3 lerp(Float4x3R other, float t, @Mutated Double4x3 dest) {
        return lerp(other.m00(), other.m01(), other.m02(), other.m10(), other.m11(), other.m12(), other.m20(), other.m21(), other.m22(), other.m30(), other.m31(), other.m32(), t, dest);
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
    public Float4x3 lerp(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32, float t, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x3Impl) dest).data;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4x3 lerp(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32, float t, @Mutated Double4x3 dest) {
        float[] sd = this.data;
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
    public Float4x3 mul(Float3x3R right, @Mutated Float4x3 dest) {
        if (Math.useFma()) return mul_fma(right, dest);
        return mul_mulAdd(right, dest);
    }

    /** {@code mul} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x3 mul_fma(Float3x3R right, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float4x3Impl) dest).data;
        float _rd0 = rightData[0];
        float _rd1 = rightData[1];
        float _rd2 = rightData[2];
        float _rd3 = rightData[3];
        float _rd4 = rightData[4];
        float _rd5 = rightData[5];
        float _rd6 = rightData[6];
        float _rd7 = rightData[7];
        float _rd8 = rightData[8];
        float _rd9 = sd[0];
        float _rd10 = sd[1];
        float _rd11 = sd[2];
        float _rd12 = sd[3];
        float _rd13 = sd[4];
        float _rd14 = sd[5];
        float _rd15 = sd[6];
        float _rd16 = sd[7];
        float _rd17 = sd[8];
        float _rd18 = sd[9];
        float _rd19 = sd[10];
        float _rd20 = sd[11];
        dd[0] = java.lang.Math.fma(_rd2, _rd17, java.lang.Math.fma(_rd0, _rd9, _rd1 * _rd13));
        dd[1] = java.lang.Math.fma(_rd2, _rd18, java.lang.Math.fma(_rd0, _rd10, _rd1 * _rd14));
        dd[2] = java.lang.Math.fma(_rd2, _rd19, java.lang.Math.fma(_rd0, _rd11, _rd1 * _rd15));
        dd[3] = java.lang.Math.fma(_rd2, _rd20, java.lang.Math.fma(_rd0, _rd12, _rd1 * _rd16));
        dd[4] = java.lang.Math.fma(_rd5, _rd17, java.lang.Math.fma(_rd3, _rd9, _rd4 * _rd13));
        return mul_s9893e1d0_1_fma(dest, dd, _rd3, _rd4, _rd5, _rd6, _rd7, _rd8, _rd9, _rd10, _rd11, _rd12, _rd13, _rd14, _rd15, _rd16, _rd17, _rd18, _rd19, _rd20);
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x3 mul_mulAdd(Float3x3R right, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float4x3Impl) dest).data;
        float _rd0 = rightData[0];
        float _rd1 = rightData[1];
        float _rd2 = rightData[2];
        float _rd3 = rightData[3];
        float _rd4 = rightData[4];
        float _rd5 = rightData[5];
        float _rd6 = rightData[6];
        float _rd7 = rightData[7];
        float _rd8 = rightData[8];
        float _rd9 = sd[0];
        float _rd10 = sd[1];
        float _rd11 = sd[2];
        float _rd12 = sd[3];
        float _rd13 = sd[4];
        float _rd14 = sd[5];
        float _rd15 = sd[6];
        float _rd16 = sd[7];
        float _rd17 = sd[8];
        float _rd18 = sd[9];
        float _rd19 = sd[10];
        float _rd20 = sd[11];
        dd[0] = ((_rd2) * (_rd17) + (((_rd0) * (_rd9) + (_rd1 * _rd13))));
        dd[1] = ((_rd2) * (_rd18) + (((_rd0) * (_rd10) + (_rd1 * _rd14))));
        dd[2] = ((_rd2) * (_rd19) + (((_rd0) * (_rd11) + (_rd1 * _rd15))));
        dd[3] = ((_rd2) * (_rd20) + (((_rd0) * (_rd12) + (_rd1 * _rd16))));
        dd[4] = ((_rd5) * (_rd17) + (((_rd3) * (_rd9) + (_rd4 * _rd13))));
        return mul_s9893e1d0_1_mulAdd(dest, dd, _rd3, _rd4, _rd5, _rd6, _rd7, _rd8, _rd9, _rd10, _rd11, _rd12, _rd13, _rd14, _rd15, _rd16, _rd17, _rd18, _rd19, _rd20);
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private Float4x3 mul_s9893e1d0_1_fma(Float4x3 dest, float[] dd, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7, float _rd8, float _rd9, float _rd10, float _rd11, float _rd12, float _rd13, float _rd14, float _rd15, float _rd16, float _rd17, float _rd18, float _rd19, float _rd20) {
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
    private Float4x3 mul_s9893e1d0_1_mulAdd(Float4x3 dest, float[] dd, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7, float _rd8, float _rd9, float _rd10, float _rd11, float _rd12, float _rd13, float _rd14, float _rd15, float _rd16, float _rd17, float _rd18, float _rd19, float _rd20) {
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
    public Double4x3 mul(Float3x3R right, @Mutated Double4x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = Math.fma(rightData[2], sd[8], Math.fma(rightData[0], sd[0], rightData[1] * sd[4]));
        dd[1] = Math.fma(rightData[2], sd[9], Math.fma(rightData[0], sd[1], rightData[1] * sd[5]));
        dd[2] = Math.fma(rightData[2], sd[10], Math.fma(rightData[0], sd[2], rightData[1] * sd[6]));
        dd[3] = Math.fma(rightData[2], sd[11], Math.fma(rightData[0], sd[3], rightData[1] * sd[7]));
        dd[4] = Math.fma(rightData[5], sd[8], Math.fma(rightData[3], sd[0], rightData[4] * sd[4]));
        dd[5] = Math.fma(rightData[5], sd[9], Math.fma(rightData[3], sd[1], rightData[4] * sd[5]));
        dd[6] = Math.fma(rightData[5], sd[10], Math.fma(rightData[3], sd[2], rightData[4] * sd[6]));
        return (Math.useFma() ? mul_se021cdfd_1_fma(dest, sd, rightData, dd) : mul_se021cdfd_1_mulAdd(dest, sd, rightData, dd));
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private Double4x3 mul_se021cdfd_1_fma(Double4x3 dest, float[] sd, float[] rightData, double[] dd) {
        dd[7] = java.lang.Math.fma(rightData[5], sd[11], java.lang.Math.fma(rightData[3], sd[3], rightData[4] * sd[7]));
        dd[8] = java.lang.Math.fma(rightData[8], sd[8], java.lang.Math.fma(rightData[6], sd[0], rightData[7] * sd[4]));
        dd[9] = java.lang.Math.fma(rightData[8], sd[9], java.lang.Math.fma(rightData[6], sd[1], rightData[7] * sd[5]));
        dd[10] = java.lang.Math.fma(rightData[8], sd[10], java.lang.Math.fma(rightData[6], sd[2], rightData[7] * sd[6]));
        dd[11] = java.lang.Math.fma(rightData[8], sd[11], java.lang.Math.fma(rightData[6], sd[3], rightData[7] * sd[7]));
        return dest;
    }

    /** Piece 2 of {@code mul}, split to fit the inline budget; reached only through it. */
    private Double4x3 mul_se021cdfd_1_mulAdd(Double4x3 dest, float[] sd, float[] rightData, double[] dd) {
        dd[7] = ((rightData[5]) * (sd[11]) + (((rightData[3]) * (sd[3]) + (rightData[4] * sd[7]))));
        dd[8] = ((rightData[8]) * (sd[8]) + (((rightData[6]) * (sd[0]) + (rightData[7] * sd[4]))));
        dd[9] = ((rightData[8]) * (sd[9]) + (((rightData[6]) * (sd[1]) + (rightData[7] * sd[5]))));
        dd[10] = ((rightData[8]) * (sd[10]) + (((rightData[6]) * (sd[2]) + (rightData[7] * sd[6]))));
        dd[11] = ((rightData[8]) * (sd[11]) + (((rightData[6]) * (sd[3]) + (rightData[7] * sd[7]))));
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
    public Float4x3 preMul(Float4x4R other, @Mutated Float4x3 dest) {
        if (Math.useFma()) return preMul_fma(other, dest);
        return preMul_mulAdd(other, dest);
    }

    /** {@code preMul} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x3 preMul_fma(Float4x4R other, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x4Impl) other).data;
        float[] dd = ((Float4x3Impl) dest).data;
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
        float _rd24 = sd[8];
        float _rd25 = sd[9];
        float _rd26 = sd[10];
        float _rd27 = sd[11];
        dd[0] = java.lang.Math.fma(_rd12, _rd19, java.lang.Math.fma(_rd8, _rd18, java.lang.Math.fma(_rd0, _rd16, _rd4 * _rd17)));
        return preMul_sac0c5b81_1_fma(dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7, _rd8, _rd9, _rd10, _rd11, _rd12, _rd13, _rd14, _rd15, _rd16, _rd17, _rd18, _rd19, _rd20, _rd21, _rd22, _rd23, _rd24, _rd25, _rd26, _rd27);
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x3 preMul_mulAdd(Float4x4R other, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x4Impl) other).data;
        float[] dd = ((Float4x3Impl) dest).data;
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
        float _rd24 = sd[8];
        float _rd25 = sd[9];
        float _rd26 = sd[10];
        float _rd27 = sd[11];
        dd[0] = ((_rd12) * (_rd19) + (((_rd8) * (_rd18) + (((_rd0) * (_rd16) + (_rd4 * _rd17))))));
        return preMul_sac0c5b81_1_mulAdd(dest, dd, _rd0, _rd1, _rd2, _rd3, _rd4, _rd5, _rd6, _rd7, _rd8, _rd9, _rd10, _rd11, _rd12, _rd13, _rd14, _rd15, _rd16, _rd17, _rd18, _rd19, _rd20, _rd21, _rd22, _rd23, _rd24, _rd25, _rd26, _rd27);
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Float4x3 preMul_sac0c5b81_1_fma(Float4x3 dest, float[] dd, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7, float _rd8, float _rd9, float _rd10, float _rd11, float _rd12, float _rd13, float _rd14, float _rd15, float _rd16, float _rd17, float _rd18, float _rd19, float _rd20, float _rd21, float _rd22, float _rd23, float _rd24, float _rd25, float _rd26, float _rd27) {
        dd[1] = java.lang.Math.fma(_rd13, _rd19, java.lang.Math.fma(_rd9, _rd18, java.lang.Math.fma(_rd1, _rd16, _rd5 * _rd17)));
        dd[2] = java.lang.Math.fma(_rd14, _rd19, java.lang.Math.fma(_rd10, _rd18, java.lang.Math.fma(_rd2, _rd16, _rd6 * _rd17)));
        dd[3] = java.lang.Math.fma(_rd15, _rd19, java.lang.Math.fma(_rd11, _rd18, java.lang.Math.fma(_rd3, _rd16, _rd7 * _rd17)));
        dd[4] = java.lang.Math.fma(_rd12, _rd23, java.lang.Math.fma(_rd8, _rd22, java.lang.Math.fma(_rd0, _rd20, _rd4 * _rd21)));
        dd[5] = java.lang.Math.fma(_rd13, _rd23, java.lang.Math.fma(_rd9, _rd22, java.lang.Math.fma(_rd1, _rd20, _rd5 * _rd21)));
        dd[6] = java.lang.Math.fma(_rd14, _rd23, java.lang.Math.fma(_rd10, _rd22, java.lang.Math.fma(_rd2, _rd20, _rd6 * _rd21)));
        dd[7] = java.lang.Math.fma(_rd15, _rd23, java.lang.Math.fma(_rd11, _rd22, java.lang.Math.fma(_rd3, _rd20, _rd7 * _rd21)));
        dd[8] = java.lang.Math.fma(_rd12, _rd27, java.lang.Math.fma(_rd8, _rd26, java.lang.Math.fma(_rd0, _rd24, _rd4 * _rd25)));
        dd[9] = java.lang.Math.fma(_rd13, _rd27, java.lang.Math.fma(_rd9, _rd26, java.lang.Math.fma(_rd1, _rd24, _rd5 * _rd25)));
        return preMul_sac0c5b81_2_fma(dest, dd, _rd2, _rd3, _rd6, _rd7, _rd10, _rd11, _rd14, _rd15, _rd24, _rd25, _rd26, _rd27);
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Float4x3 preMul_sac0c5b81_1_mulAdd(Float4x3 dest, float[] dd, float _rd0, float _rd1, float _rd2, float _rd3, float _rd4, float _rd5, float _rd6, float _rd7, float _rd8, float _rd9, float _rd10, float _rd11, float _rd12, float _rd13, float _rd14, float _rd15, float _rd16, float _rd17, float _rd18, float _rd19, float _rd20, float _rd21, float _rd22, float _rd23, float _rd24, float _rd25, float _rd26, float _rd27) {
        dd[1] = ((_rd13) * (_rd19) + (((_rd9) * (_rd18) + (((_rd1) * (_rd16) + (_rd5 * _rd17))))));
        dd[2] = ((_rd14) * (_rd19) + (((_rd10) * (_rd18) + (((_rd2) * (_rd16) + (_rd6 * _rd17))))));
        dd[3] = ((_rd15) * (_rd19) + (((_rd11) * (_rd18) + (((_rd3) * (_rd16) + (_rd7 * _rd17))))));
        dd[4] = ((_rd12) * (_rd23) + (((_rd8) * (_rd22) + (((_rd0) * (_rd20) + (_rd4 * _rd21))))));
        dd[5] = ((_rd13) * (_rd23) + (((_rd9) * (_rd22) + (((_rd1) * (_rd20) + (_rd5 * _rd21))))));
        dd[6] = ((_rd14) * (_rd23) + (((_rd10) * (_rd22) + (((_rd2) * (_rd20) + (_rd6 * _rd21))))));
        dd[7] = ((_rd15) * (_rd23) + (((_rd11) * (_rd22) + (((_rd3) * (_rd20) + (_rd7 * _rd21))))));
        dd[8] = ((_rd12) * (_rd27) + (((_rd8) * (_rd26) + (((_rd0) * (_rd24) + (_rd4 * _rd25))))));
        dd[9] = ((_rd13) * (_rd27) + (((_rd9) * (_rd26) + (((_rd1) * (_rd24) + (_rd5 * _rd25))))));
        return preMul_sac0c5b81_2_mulAdd(dest, dd, _rd2, _rd3, _rd6, _rd7, _rd10, _rd11, _rd14, _rd15, _rd24, _rd25, _rd26, _rd27);
    }

    /** Piece 3 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Float4x3 preMul_sac0c5b81_2_fma(Float4x3 dest, float[] dd, float _rd2, float _rd3, float _rd6, float _rd7, float _rd10, float _rd11, float _rd14, float _rd15, float _rd24, float _rd25, float _rd26, float _rd27) {
        dd[10] = java.lang.Math.fma(_rd14, _rd27, java.lang.Math.fma(_rd10, _rd26, java.lang.Math.fma(_rd2, _rd24, _rd6 * _rd25)));
        dd[11] = java.lang.Math.fma(_rd15, _rd27, java.lang.Math.fma(_rd11, _rd26, java.lang.Math.fma(_rd3, _rd24, _rd7 * _rd25)));
        return dest;
    }

    /** Piece 3 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Float4x3 preMul_sac0c5b81_2_mulAdd(Float4x3 dest, float[] dd, float _rd2, float _rd3, float _rd6, float _rd7, float _rd10, float _rd11, float _rd14, float _rd15, float _rd24, float _rd25, float _rd26, float _rd27) {
        dd[10] = ((_rd14) * (_rd27) + (((_rd10) * (_rd26) + (((_rd2) * (_rd24) + (_rd6 * _rd25))))));
        dd[11] = ((_rd15) * (_rd27) + (((_rd11) * (_rd26) + (((_rd3) * (_rd24) + (_rd7 * _rd25))))));
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
    public Double4x3 preMul(Float4x4R other, @Mutated Double4x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x4Impl) other).data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = Math.fma(otherData[12], sd[3], Math.fma(otherData[8], sd[2], Math.fma(otherData[0], sd[0], otherData[4] * sd[1])));
        dd[1] = Math.fma(otherData[13], sd[3], Math.fma(otherData[9], sd[2], Math.fma(otherData[1], sd[0], otherData[5] * sd[1])));
        dd[2] = Math.fma(otherData[14], sd[3], Math.fma(otherData[10], sd[2], Math.fma(otherData[2], sd[0], otherData[6] * sd[1])));
        dd[3] = Math.fma(otherData[15], sd[3], Math.fma(otherData[11], sd[2], Math.fma(otherData[3], sd[0], otherData[7] * sd[1])));
        dd[4] = Math.fma(otherData[12], sd[7], Math.fma(otherData[8], sd[6], Math.fma(otherData[0], sd[4], otherData[4] * sd[5])));
        return (Math.useFma() ? preMul_sf86f58ae_1_fma(dest, sd, otherData, dd) : preMul_sf86f58ae_1_mulAdd(dest, sd, otherData, dd));
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Double4x3 preMul_sf86f58ae_1_fma(Double4x3 dest, float[] sd, float[] otherData, double[] dd) {
        dd[5] = java.lang.Math.fma(otherData[13], sd[7], java.lang.Math.fma(otherData[9], sd[6], java.lang.Math.fma(otherData[1], sd[4], otherData[5] * sd[5])));
        dd[6] = java.lang.Math.fma(otherData[14], sd[7], java.lang.Math.fma(otherData[10], sd[6], java.lang.Math.fma(otherData[2], sd[4], otherData[6] * sd[5])));
        dd[7] = java.lang.Math.fma(otherData[15], sd[7], java.lang.Math.fma(otherData[11], sd[6], java.lang.Math.fma(otherData[3], sd[4], otherData[7] * sd[5])));
        dd[8] = java.lang.Math.fma(otherData[12], sd[11], java.lang.Math.fma(otherData[8], sd[10], java.lang.Math.fma(otherData[0], sd[8], otherData[4] * sd[9])));
        dd[9] = java.lang.Math.fma(otherData[13], sd[11], java.lang.Math.fma(otherData[9], sd[10], java.lang.Math.fma(otherData[1], sd[8], otherData[5] * sd[9])));
        dd[10] = java.lang.Math.fma(otherData[14], sd[11], java.lang.Math.fma(otherData[10], sd[10], java.lang.Math.fma(otherData[2], sd[8], otherData[6] * sd[9])));
        return preMul_sf86f58ae_2_fma(dest, sd, otherData, dd);
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Double4x3 preMul_sf86f58ae_1_mulAdd(Double4x3 dest, float[] sd, float[] otherData, double[] dd) {
        dd[5] = ((otherData[13]) * (sd[7]) + (((otherData[9]) * (sd[6]) + (((otherData[1]) * (sd[4]) + (otherData[5] * sd[5]))))));
        dd[6] = ((otherData[14]) * (sd[7]) + (((otherData[10]) * (sd[6]) + (((otherData[2]) * (sd[4]) + (otherData[6] * sd[5]))))));
        dd[7] = ((otherData[15]) * (sd[7]) + (((otherData[11]) * (sd[6]) + (((otherData[3]) * (sd[4]) + (otherData[7] * sd[5]))))));
        dd[8] = ((otherData[12]) * (sd[11]) + (((otherData[8]) * (sd[10]) + (((otherData[0]) * (sd[8]) + (otherData[4] * sd[9]))))));
        dd[9] = ((otherData[13]) * (sd[11]) + (((otherData[9]) * (sd[10]) + (((otherData[1]) * (sd[8]) + (otherData[5] * sd[9]))))));
        dd[10] = ((otherData[14]) * (sd[11]) + (((otherData[10]) * (sd[10]) + (((otherData[2]) * (sd[8]) + (otherData[6] * sd[9]))))));
        return preMul_sf86f58ae_2_mulAdd(dest, sd, otherData, dd);
    }

    /** Piece 3 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Double4x3 preMul_sf86f58ae_2_fma(Double4x3 dest, float[] sd, float[] otherData, double[] dd) {
        dd[11] = java.lang.Math.fma(otherData[15], sd[11], java.lang.Math.fma(otherData[11], sd[10], java.lang.Math.fma(otherData[3], sd[8], otherData[7] * sd[9])));
        return dest;
    }

    /** Piece 3 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Double4x3 preMul_sf86f58ae_2_mulAdd(Double4x3 dest, float[] sd, float[] otherData, double[] dd) {
        dd[11] = ((otherData[15]) * (sd[11]) + (((otherData[11]) * (sd[10]) + (((otherData[3]) * (sd[8]) + (otherData[7] * sd[9]))))));
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
    public Float4x3 addScaled(Float4x3R other, float weight, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x3Impl) other).data;
        float[] dd = ((Float4x3Impl) dest).data;
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
    public Double4x3 addScaled(Float4x3R other, float weight, @Mutated Double4x3 dest) {
        float m01 = other.m01();
        float m02 = other.m02();
        float m10 = other.m10();
        float m11 = other.m11();
        float m12 = other.m12();
        float m20 = other.m20();
        float m21 = other.m21();
        float m22 = other.m22();
        float m30 = other.m30();
        float m31 = other.m31();
        float m32 = other.m32();
        float[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = Math.fma(weight, other.m00(), sd[0]);
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
    public Float4x3 addScaled(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32, float weight, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x3Impl) dest).data;
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
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}, {@code m30}, {@code m31}, {@code m32}) scaled by
     * {@code weight} to this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double4x3 addScaled(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32, float weight, @Mutated Double4x3 dest) {
        float[] sd = this.data;
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
    public Float4 mul(Float3R v, @Mutated Float4 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[8], vZ, java.lang.Math.fma(sd[0], vX, sd[4] * vY));
            dd[1] = java.lang.Math.fma(sd[9], vZ, java.lang.Math.fma(sd[1], vX, sd[5] * vY));
            dd[2] = java.lang.Math.fma(sd[10], vZ, java.lang.Math.fma(sd[2], vX, sd[6] * vY));
            dd[3] = java.lang.Math.fma(sd[11], vZ, java.lang.Math.fma(sd[3], vX, sd[7] * vY));
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the right operand of the product
     * @param dest will hold the result
     * @return dest
     */
    public Double4 mul(Float3R v, @Mutated Double4 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[8], vZ, java.lang.Math.fma(sd[0], vX, sd[4] * vY));
            dd[1] = java.lang.Math.fma(sd[9], vZ, java.lang.Math.fma(sd[1], vX, sd[5] * vY));
            dd[2] = java.lang.Math.fma(sd[10], vZ, java.lang.Math.fma(sd[2], vX, sd[6] * vY));
            dd[3] = java.lang.Math.fma(sd[11], vZ, java.lang.Math.fma(sd[3], vX, sd[7] * vY));
            return dest;
        } else {
            float[] sd = this.data;
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
    public Float4 mul(float vX, float vY, float vZ, @Mutated Float4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[8], vZ, java.lang.Math.fma(sd[0], vX, sd[4] * vY));
            dd[1] = java.lang.Math.fma(sd[9], vZ, java.lang.Math.fma(sd[1], vX, sd[5] * vY));
            dd[2] = java.lang.Math.fma(sd[10], vZ, java.lang.Math.fma(sd[2], vX, sd[6] * vY));
            dd[3] = java.lang.Math.fma(sd[11], vZ, java.lang.Math.fma(sd[3], vX, sd[7] * vY));
            return dest;
        } else {
            float[] sd = this.data;
            float[] dd = ((Float4Impl) dest).data;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 mul(float vX, float vY, float vZ, @Mutated Double4 dest) {
        if (Math.useFma()) {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = java.lang.Math.fma(sd[8], vZ, java.lang.Math.fma(sd[0], vX, sd[4] * vY));
            dd[1] = java.lang.Math.fma(sd[9], vZ, java.lang.Math.fma(sd[1], vX, sd[5] * vY));
            dd[2] = java.lang.Math.fma(sd[10], vZ, java.lang.Math.fma(sd[2], vX, sd[6] * vY));
            dd[3] = java.lang.Math.fma(sd[11], vZ, java.lang.Math.fma(sd[3], vX, sd[7] * vY));
            return dest;
        } else {
            float[] sd = this.data;
            double[] dd = ((Double4Impl) dest).data;
            dd[0] = ((sd[8]) * (vZ) + (((sd[0]) * (vX) + (sd[4] * vY))));
            dd[1] = ((sd[9]) * (vZ) + (((sd[1]) * (vX) + (sd[5] * vY))));
            dd[2] = ((sd[10]) * (vZ) + (((sd[2]) * (vX) + (sd[6] * vY))));
            dd[3] = ((sd[11]) * (vZ) + (((sd[3]) * (vX) + (sd[7] * vY))));
            return dest;
        }
    }

    public float m00() { return data[0]; }
    public float m01() { return data[4]; }
    public float m02() { return data[8]; }
    public float m10() { return data[1]; }
    public float m11() { return data[5]; }
    public float m12() { return data[9]; }
    public float m20() { return data[2]; }
    public float m21() { return data[6]; }
    public float m22() { return data[10]; }
    public float m30() { return data[3]; }
    public float m31() { return data[7]; }
    public float m32() { return data[11]; }

    @Override public String toString() {
        return "Float4x3(\n    " + m00() + ", " + m01() + ", " + m02() + "\n    " + m10() + ", " + m11() + ", " + m12() + "\n    " + m20() + ", " + m21() + ", " + m22() + "\n    " + m30() + ", " + m31() + ", " + m32() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float4x3Impl)) return false;
        Float4x3Impl o = (Float4x3Impl) obj;
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
            && Float.isFinite(data[7])
            && Float.isFinite(data[8])
            && Float.isFinite(data[9])
            && Float.isFinite(data[10])
            && Float.isFinite(data[11]);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(data[0])
            || Float.isNaN(data[1])
            || Float.isNaN(data[2])
            || Float.isNaN(data[3])
            || Float.isNaN(data[4])
            || Float.isNaN(data[5])
            || Float.isNaN(data[6])
            || Float.isNaN(data[7])
            || Float.isNaN(data[8])
            || Float.isNaN(data[9])
            || Float.isNaN(data[10])
            || Float.isNaN(data[11]);
    }

    @Override public boolean equalsEpsilon(Float4x3R other, float epsilon) {
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

    public float[] storeCM(@Mutated float[] dest, int offset) {
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
    public @Mutated Float4x3 loadCM(float[] src, int offset) {
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
    @Mutated public Float4x3 loadCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Float4x3 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    @Mutated public Float4x3 loadCMRelative(FloatBuffer buf) {
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
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }
    public Float4x3 loadCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    public Float4x3 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public Float4x3 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return r;
    }
    public Float4x3 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }
    @Mutated public Float4x3 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }
    public MemorySegment storeCM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest); }
    public MemorySegment storeCM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest);
    }
    @Mutated public Float4x3 loadCM(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src); }
    public Float4x3 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src);
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
    public @Mutated Float4x3 loadCM(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        this.data[4] = (float) src[offset + 4];
        this.data[5] = (float) src[offset + 5];
        this.data[6] = (float) src[offset + 6];
        this.data[7] = (float) src[offset + 7];
        this.data[8] = (float) src[offset + 8];
        this.data[9] = (float) src[offset + 9];
        this.data[10] = (float) src[offset + 10];
        this.data[11] = (float) src[offset + 11];
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
    @Mutated public Float4x3 loadCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Float4x3 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    @Mutated public Float4x3 loadCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return this;
    }
    public ByteBuffer storeCMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 96) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 96);
        return buf;
    }
    public Float4x3 loadCMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, buf.position(), buf);
    }
    public Float4x3 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf);
    }
    public Float4x3 loadCMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 96) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 96);
        return r;
    }
    public Float4x3 storeCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address);
    }
    @Mutated public Float4x3 loadCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address);
    }
    public MemorySegment storeCMDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCMDouble(this, 0L, dest); }
    public MemorySegment storeCMDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest);
    }
    @Mutated public Float4x3 loadCMDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMDouble(this, 0L, src); }
    public Float4x3 loadCMDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMDouble(this, offset, src);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        if (dest == this.data) return storeRM_aliased(dest, offset);
        return storeRM_distinct(dest, offset);
    }
    private float[] storeRM_distinct(float[] dest, int offset) {
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
    private float[] storeRM_aliased(float[] dest, int offset) {
        float[] d = this.data;
        float t1 = d[1];
        float t2 = d[2];
        float t3 = d[3];
        float t4 = d[4];
        float t5 = d[5];
        float t6 = d[6];
        float t7 = d[7];
        float t8 = d[8];
        float t9 = d[9];
        float t10 = d[10];
        float t11 = d[11];
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
    @Mutated public Float4x3 loadRM(float[] src, int offset) {
        if (src == this.data) return loadRM_aliased(src, offset);
        return loadRM_distinct(src, offset);
    }
    private Float4x3 loadRM_distinct(float[] src, int offset) {
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
    private Float4x3 loadRM_aliased(float[] src, int offset) {
        float t1 = src[offset + 1];
        float t2 = src[offset + 2];
        float t3 = src[offset + 3];
        float t4 = src[offset + 4];
        float t5 = src[offset + 5];
        float t6 = src[offset + 6];
        float t7 = src[offset + 7];
        float t8 = src[offset + 8];
        float t9 = src[offset + 9];
        float t10 = src[offset + 10];
        float t11 = src[offset + 11];
        float[] d = this.data;
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
    @Mutated public Float4x3 loadRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Float4x3 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    @Mutated public Float4x3 loadRMRelative(FloatBuffer buf) {
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
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }
    public Float4x3 loadRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    public Float4x3 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public Float4x3 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return r;
    }
    public Float4x3 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }
    @Mutated public Float4x3 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }
    public MemorySegment storeRM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest); }
    public MemorySegment storeRM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }
    @Mutated public Float4x3 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src); }
    public Float4x3 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
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
    public @Mutated Float4x3 loadRM(double[] src, int offset) {
        this.data[0] = (float) src[offset];
        this.data[4] = (float) src[offset + 1];
        this.data[8] = (float) src[offset + 2];
        this.data[1] = (float) src[offset + 3];
        this.data[5] = (float) src[offset + 4];
        this.data[9] = (float) src[offset + 5];
        this.data[2] = (float) src[offset + 6];
        this.data[6] = (float) src[offset + 7];
        this.data[10] = (float) src[offset + 8];
        this.data[3] = (float) src[offset + 9];
        this.data[7] = (float) src[offset + 10];
        this.data[11] = (float) src[offset + 11];
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
    @Mutated public Float4x3 loadRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Float4x3 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    @Mutated public Float4x3 loadRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 12);
        return this;
    }
    public ByteBuffer storeRMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 96) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 96);
        return buf;
    }
    public Float4x3 loadRMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, buf.position(), buf);
    }
    public Float4x3 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf);
    }
    public Float4x3 loadRMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 96) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 96);
        return r;
    }
    public Float4x3 storeRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address);
    }
    @Mutated public Float4x3 loadRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address);
    }
    public MemorySegment storeRMDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRMDouble(this, 0L, dest); }
    public MemorySegment storeRMDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest);
    }
    @Mutated public Float4x3 loadRMDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMDouble(this, 0L, src); }
    public Float4x3 loadRMDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMDouble(this, offset, src);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
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
    public @Mutated Float4x3 loadCM(float[] src, int offset, int stride) {
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
    @Mutated public Float4x3 loadCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Float4x3 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float4x3 loadCMRelative(FloatBuffer buf, int stride) {
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
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return buf;
    }
    public Float4x3 loadCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    public Float4x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public Float4x3 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return r;
    }
    public Float4x3 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }
    @Mutated public Float4x3 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }
    public MemorySegment storeCM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest, stride); }
    public MemorySegment storeCM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }
    @Mutated public Float4x3 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src, stride); }
    public Float4x3 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src, stride);
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
    public @Mutated Float4x3 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.data[0] = (float) src[offset];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        this.data[4] = (float) src[_p1];
        this.data[5] = (float) src[_p1 + 1];
        this.data[6] = (float) src[_p1 + 2];
        this.data[7] = (float) src[_p1 + 3];
        this.data[8] = (float) src[_p2];
        this.data[9] = (float) src[_p2 + 1];
        this.data[10] = (float) src[_p2 + 2];
        this.data[11] = (float) src[_p2 + 3];
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
    @Mutated public Float4x3 loadCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Float4x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float4x3 loadCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
        return this;
    }
    public ByteBuffer storeCMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
        return buf;
    }
    public Float4x3 loadCMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, buf.position(), buf, stride);
    }
    public Float4x3 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf, stride);
    }
    public Float4x3 loadCMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
        return r;
    }
    public Float4x3 storeCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address, stride);
    }
    @Mutated public Float4x3 loadCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address, stride);
    }
    public MemorySegment storeCMDouble(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCMDouble(this, 0L, dest, stride); }
    public MemorySegment storeCMDouble(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest, stride);
    }
    @Mutated public Float4x3 loadCMDouble(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMDouble(this, 0L, src, stride); }
    public Float4x3 loadCMDouble(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCMDouble(this, offset, src, stride);
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
    private float[] storeRM_aliased(float[] dest, int offset, int stride) {
        float[] d = this.data;
        float t1 = d[1];
        float t2 = d[2];
        float t3 = d[3];
        float t4 = d[4];
        float t5 = d[5];
        float t6 = d[6];
        float t7 = d[7];
        float t8 = d[8];
        float t9 = d[9];
        float t10 = d[10];
        float t11 = d[11];
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
    @Mutated public Float4x3 loadRM(float[] src, int offset, int stride) {
        if (src == this.data) return loadRM_aliased(src, offset, stride);
        return loadRM_distinct(src, offset, stride);
    }
    private Float4x3 loadRM_distinct(float[] src, int offset, int stride) {
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
    private Float4x3 loadRM_aliased(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        float t1 = src[offset + 1];
        float t2 = src[offset + 2];
        float t3 = src[_p1];
        float t4 = src[_p1 + 1];
        float t5 = src[_p1 + 2];
        float t6 = src[_p2];
        float t7 = src[_p2 + 1];
        float t8 = src[_p2 + 2];
        float t9 = src[_p3];
        float t10 = src[_p3 + 1];
        float t11 = src[_p3 + 2];
        float[] d = this.data;
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
    @Mutated public Float4x3 loadRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Float4x3 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float4x3 loadRMRelative(FloatBuffer buf, int stride) {
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
    public Float4x3 loadRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    public Float4x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public Float4x3 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + (4 * stride) * 4);
        return r;
    }
    public Float4x3 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }
    @Mutated public Float4x3 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }
    public MemorySegment storeRM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest, stride); }
    public MemorySegment storeRM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }
    @Mutated public Float4x3 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src, stride); }
    public Float4x3 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src, stride);
    }

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
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
    public @Mutated Float4x3 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        this.data[0] = (float) src[offset];
        this.data[4] = (float) src[offset + 1];
        this.data[8] = (float) src[offset + 2];
        this.data[1] = (float) src[_p1];
        this.data[5] = (float) src[_p1 + 1];
        this.data[9] = (float) src[_p1 + 2];
        this.data[2] = (float) src[_p2];
        this.data[6] = (float) src[_p2 + 1];
        this.data[10] = (float) src[_p2 + 2];
        this.data[3] = (float) src[_p3];
        this.data[7] = (float) src[_p3 + 1];
        this.data[11] = (float) src[_p3 + 2];
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
    @Mutated public Float4x3 loadRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Float4x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float4x3 loadRMRelative(DoubleBuffer buf, int stride) {
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
    public Float4x3 loadRMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, buf.position(), buf, stride);
    }
    public Float4x3 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf, stride);
    }
    public Float4x3 loadRMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float4x3 r = StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + (4 * stride) * 8);
        return r;
    }
    public Float4x3 storeRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address, stride);
    }
    @Mutated public Float4x3 loadRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address, stride);
    }
    public MemorySegment storeRMDouble(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRMDouble(this, 0L, dest, stride); }
    public MemorySegment storeRMDouble(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest, stride);
    }
    @Mutated public Float4x3 loadRMDouble(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMDouble(this, 0L, src, stride); }
    public Float4x3 loadRMDouble(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMDouble(this, offset, src, stride);
    }
}
