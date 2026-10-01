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
import org.joml2.internal.unsafe.HeapSegments;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Generated implementation of {@link Float4x3} backed by a {@code float[]} array, with Vector API
 * SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Float4x3Impl implements Float4x3 {
    public float[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Float4x3SegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float4x3SegOpsUnsafe()
            : new Float4x3SegOpsMS();
        static final Float4x3BbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Float4x3BbOpsUnsafe()
            : new Float4x3BbOpsApi();
        static final Float4x3RawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
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
        if (org.joml2.internal.FmaSwitch.ON) {
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
        var _col1 = FloatVector.fromArray(COL_SPECIES, sd, 4);
        var _col2 = FloatVector.fromArray(COL_SPECIES, sd, 8);
        FloatVector.fromArray(COL_SPECIES, sd, 0).intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
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
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _lanes = _sp.length();
        int _i = 0;
        for (; _i + _lanes <= 12; _i += _lanes) {
            FloatVector.fromArray(_sp, otherData, _i).add(FloatVector.fromArray(_sp, sd, _i)).intoArray(dd, _i);
        }
        for (; _i < 12; _i++) {
            dd[_i] = otherData[_i] + sd[_i];
        }
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
        float[] otherData = ((Float4x3Impl) other).data;
        float m01 = otherData[4];
        float m02 = otherData[8];
        float m10 = otherData[1];
        float m11 = otherData[5];
        float m12 = otherData[9];
        float m20 = otherData[2];
        float m21 = otherData[6];
        float m22 = otherData[10];
        float m30 = otherData[3];
        float m31 = otherData[7];
        float m32 = otherData[11];
        float[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = otherData[0] + sd[0];
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
        var _sv0 = FloatVector.broadcast(COL_SPECIES, scalar);
        var _col1 = _sv0.mul(FloatVector.fromArray(COL_SPECIES, sd, 4));
        var _col2 = _sv0.mul(FloatVector.fromArray(COL_SPECIES, sd, 8));
        _sv0.mul(FloatVector.fromArray(COL_SPECIES, sd, 0)).intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
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
        var _col1 = FloatVector.fromArray(COL_SPECIES, sd, 4).neg();
        var _col2 = FloatVector.fromArray(COL_SPECIES, sd, 8).neg();
        FloatVector.fromArray(COL_SPECIES, sd, 0).neg().intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
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
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _lanes = _sp.length();
        int _i = 0;
        for (; _i + _lanes <= 12; _i += _lanes) {
            FloatVector.fromArray(_sp, sd, _i).sub(FloatVector.fromArray(_sp, otherData, _i)).intoArray(dd, _i);
        }
        for (; _i < 12; _i++) {
            dd[_i] = sd[_i] - otherData[_i];
        }
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
        float[] otherData = ((Float4x3Impl) other).data;
        float m01 = otherData[4];
        float m02 = otherData[8];
        float m10 = otherData[1];
        float m11 = otherData[5];
        float m12 = otherData[9];
        float m20 = otherData[2];
        float m21 = otherData[6];
        float m22 = otherData[10];
        float m30 = otherData[3];
        float m31 = otherData[7];
        float m32 = otherData[11];
        float[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = sd[0] - otherData[0];
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
        var _col1 = FloatVector.fromArray(COL_SPECIES, vData, 4);
        var _col2 = FloatVector.fromArray(COL_SPECIES, vData, 8);
        FloatVector.fromArray(COL_SPECIES, vData, 0).intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
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
        VectorSpecies<Float> _COL_SPECIES = COL_SPECIES;
        float[] _DATA_0 = DATA_0;
        FloatVector.fromArray(_COL_SPECIES, _DATA_0, 0).intoArray(dd, 0);
        FloatVector.fromArray(_COL_SPECIES, _DATA_0, 4).intoArray(dd, 4);
        FloatVector.fromArray(_COL_SPECIES, _DATA_0, 8).intoArray(dd, 8);
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
     * shows when this component is much larger in magnitude than the other one (1e8 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Float4x3 lerp(Float4x3R other, float t, @Mutated Float4x3 dest) {
        if (SimdMath.USE_FMA) return lerp_fma(other, t, dest);
        return lerp_mulAdd(other, t, dest);
    }

    private Float4x3 lerp_fma(Float4x3R other, float t, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x3Impl) other).data;
        float[] dd = ((Float4x3Impl) dest).data;
        VectorSpecies<Float> _COL_SPECIES = COL_SPECIES;
        var _sv0 = FloatVector.broadcast(_COL_SPECIES, t);
        var _sv1 = FloatVector.fromArray(_COL_SPECIES, sd, 0);
        var _sv2 = FloatVector.fromArray(_COL_SPECIES, sd, 4);
        var _sv3 = FloatVector.fromArray(_COL_SPECIES, sd, 8);
        var _col1 = _sv0.fma(FloatVector.fromArray(_COL_SPECIES, otherData, 4).sub(_sv2), _sv2);
        var _col2 = _sv0.fma(FloatVector.fromArray(_COL_SPECIES, otherData, 8).sub(_sv3), _sv3);
        _sv0.fma(FloatVector.fromArray(_COL_SPECIES, otherData, 0).sub(_sv1), _sv1).intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
        return dest;
    }

    private Float4x3 lerp_mulAdd(Float4x3R other, float t, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x3Impl) other).data;
        float[] dd = ((Float4x3Impl) dest).data;
        VectorSpecies<Float> _COL_SPECIES = COL_SPECIES;
        var _sv0 = FloatVector.broadcast(_COL_SPECIES, t);
        var _sv1 = FloatVector.fromArray(_COL_SPECIES, sd, 0);
        var _sv2 = FloatVector.fromArray(_COL_SPECIES, sd, 4);
        var _sv3 = FloatVector.fromArray(_COL_SPECIES, sd, 8);
        var _col1 = _sv0.mul(FloatVector.fromArray(_COL_SPECIES, otherData, 4).sub(_sv2)).add(_sv2);
        var _col2 = _sv0.mul(FloatVector.fromArray(_COL_SPECIES, otherData, 8).sub(_sv3)).add(_sv3);
        _sv0.mul(FloatVector.fromArray(_COL_SPECIES, otherData, 0).sub(_sv1)).add(_sv1).intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
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
     * shows when this component is much larger in magnitude than the other one (1e8 towards 1 ends
     * at 0).
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
        float[] otherData = ((Float4x3Impl) other).data;
        float m00 = otherData[0];
        float m01 = otherData[4];
        float m02 = otherData[8];
        float m10 = otherData[1];
        float m11 = otherData[5];
        float m12 = otherData[9];
        float m20 = otherData[2];
        float m21 = otherData[6];
        float m22 = otherData[10];
        float m30 = otherData[3];
        float m31 = otherData[7];
        float m32 = otherData[11];
        if (org.joml2.internal.FmaSwitch.ON) return lerp_fma(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32, t, dest);
        return lerp_mulAdd(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32, t, dest);
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
     * magnitude than the other one (1e8 towards 1 ends at 0).
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
        if (SimdMath.USE_FMA) {
            float[] sd = this.data;
            float[] dd = ((Float4x3Impl) dest).data;
            VectorSpecies<Float> _COL_SPECIES = COL_SPECIES;
            var _sv0 = FloatVector.broadcast(_COL_SPECIES, t);
            var _sv1 = FloatVector.fromArray(_COL_SPECIES, sd, 0);
            var _sv2 = FloatVector.fromArray(_COL_SPECIES, sd, 4);
            var _sv3 = FloatVector.fromArray(_COL_SPECIES, sd, 8);
            _sv0.fma(FloatVector.zero(_COL_SPECIES).withLane(0, m00).withLane(1, m10).withLane(2, m20).withLane(3, m30).sub(_sv1), _sv1).intoArray(dd, 0);
            _sv0.fma(FloatVector.zero(_COL_SPECIES).withLane(0, m01).withLane(1, m11).withLane(2, m21).withLane(3, m31).sub(_sv2), _sv2).intoArray(dd, 4);
            _sv0.fma(FloatVector.zero(_COL_SPECIES).withLane(0, m02).withLane(1, m12).withLane(2, m22).withLane(3, m32).sub(_sv3), _sv3).intoArray(dd, 8);
            return dest;
        }
        return lerp_mulAdd(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32, t, dest);
    }

    private Float4x3 lerp_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32, float t, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x3Impl) dest).data;
        VectorSpecies<Float> _COL_SPECIES = COL_SPECIES;
        var _sv0 = FloatVector.broadcast(_COL_SPECIES, t);
        var _sv1 = FloatVector.fromArray(_COL_SPECIES, sd, 0);
        var _sv2 = FloatVector.fromArray(_COL_SPECIES, sd, 4);
        var _sv3 = FloatVector.fromArray(_COL_SPECIES, sd, 8);
        _sv0.mul(FloatVector.zero(_COL_SPECIES).withLane(0, m00).withLane(1, m10).withLane(2, m20).withLane(3, m30).sub(_sv1)).add(_sv1).intoArray(dd, 0);
        _sv0.mul(FloatVector.zero(_COL_SPECIES).withLane(0, m01).withLane(1, m11).withLane(2, m21).withLane(3, m31).sub(_sv2)).add(_sv2).intoArray(dd, 4);
        _sv0.mul(FloatVector.zero(_COL_SPECIES).withLane(0, m02).withLane(1, m12).withLane(2, m22).withLane(3, m32).sub(_sv3)).add(_sv3).intoArray(dd, 8);
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
     * magnitude than the other one (1e8 towards 1 ends at 0).
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
        if (org.joml2.internal.FmaSwitch.ON) return lerp_fma(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32, t, dest);
        return lerp_mulAdd(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32, t, dest);
    }

    /** {@code lerp} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x3 lerp_fma(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32, float t, @Mutated Double4x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = java.lang.Math.fma(t, m00 - sd[0], sd[0]);
        dd[1] = java.lang.Math.fma(t, m10 - sd[1], sd[1]);
        dd[2] = java.lang.Math.fma(t, m20 - sd[2], sd[2]);
        dd[3] = java.lang.Math.fma(t, m30 - sd[3], sd[3]);
        dd[4] = java.lang.Math.fma(t, m01 - sd[4], sd[4]);
        dd[5] = java.lang.Math.fma(t, m11 - sd[5], sd[5]);
        dd[6] = java.lang.Math.fma(t, m21 - sd[6], sd[6]);
        dd[7] = java.lang.Math.fma(t, m31 - sd[7], sd[7]);
        dd[8] = java.lang.Math.fma(t, m02 - sd[8], sd[8]);
        dd[9] = java.lang.Math.fma(t, m12 - sd[9], sd[9]);
        dd[10] = java.lang.Math.fma(t, m22 - sd[10], sd[10]);
        dd[11] = java.lang.Math.fma(t, m32 - sd[11], sd[11]);
        return dest;
    }

    /** {@code lerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x3 lerp_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32, float t, @Mutated Double4x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = ((t) * (m00 - sd[0]) + (sd[0]));
        dd[1] = ((t) * (m10 - sd[1]) + (sd[1]));
        dd[2] = ((t) * (m20 - sd[2]) + (sd[2]));
        dd[3] = ((t) * (m30 - sd[3]) + (sd[3]));
        dd[4] = ((t) * (m01 - sd[4]) + (sd[4]));
        dd[5] = ((t) * (m11 - sd[5]) + (sd[5]));
        dd[6] = ((t) * (m21 - sd[6]) + (sd[6]));
        dd[7] = ((t) * (m31 - sd[7]) + (sd[7]));
        dd[8] = ((t) * (m02 - sd[8]) + (sd[8]));
        dd[9] = ((t) * (m12 - sd[9]) + (sd[9]));
        dd[10] = ((t) * (m22 - sd[10]) + (sd[10]));
        dd[11] = ((t) * (m32 - sd[11]) + (sd[11]));
        return dest;
    }

    /**
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * This is the plain 4x3 * 3x3 matrix product: the inner dimensions meet, no operand is
     * extended.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @param dest will hold the result
     * @return dest
     */
    public Float4x3 mul(Float3x3R right, @Mutated Float4x3 dest) {
        if (SimdMath.USE_FMA) return mul_fma(right, dest);
        return mul_mulAdd(right, dest);
    }

    private Float4x3 mul_fma(Float3x3R right, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float4x3Impl) dest).data;
        VectorSpecies<Float> _COL_SPECIES = COL_SPECIES;
        var _sv0 = FloatVector.fromArray(_COL_SPECIES, sd, 8);
        var _sv1 = FloatVector.fromArray(_COL_SPECIES, sd, 0);
        var _sv2 = FloatVector.fromArray(_COL_SPECIES, sd, 4);
        var _col1 = FloatVector.broadcast(_COL_SPECIES, rightData[5]).fma(_sv0, FloatVector.broadcast(_COL_SPECIES, rightData[3]).fma(_sv1, FloatVector.broadcast(_COL_SPECIES, rightData[4]).mul(_sv2)));
        var _col2 = FloatVector.broadcast(_COL_SPECIES, rightData[8]).fma(_sv0, FloatVector.broadcast(_COL_SPECIES, rightData[6]).fma(_sv1, FloatVector.broadcast(_COL_SPECIES, rightData[7]).mul(_sv2)));
        FloatVector.broadcast(_COL_SPECIES, rightData[2]).fma(_sv0, FloatVector.broadcast(_COL_SPECIES, rightData[0]).fma(_sv1, FloatVector.broadcast(_COL_SPECIES, rightData[1]).mul(_sv2))).intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
        return dest;
    }

    private Float4x3 mul_mulAdd(Float3x3R right, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        float[] dd = ((Float4x3Impl) dest).data;
        VectorSpecies<Float> _COL_SPECIES = COL_SPECIES;
        var _sv0 = FloatVector.fromArray(_COL_SPECIES, sd, 8);
        var _sv1 = FloatVector.fromArray(_COL_SPECIES, sd, 0);
        var _sv2 = FloatVector.fromArray(_COL_SPECIES, sd, 4);
        var _col1 = FloatVector.broadcast(_COL_SPECIES, rightData[5]).mul(_sv0).add(FloatVector.broadcast(_COL_SPECIES, rightData[3]).mul(_sv1).add(FloatVector.broadcast(_COL_SPECIES, rightData[4]).mul(_sv2)));
        var _col2 = FloatVector.broadcast(_COL_SPECIES, rightData[8]).mul(_sv0).add(FloatVector.broadcast(_COL_SPECIES, rightData[6]).mul(_sv1).add(FloatVector.broadcast(_COL_SPECIES, rightData[7]).mul(_sv2)));
        FloatVector.broadcast(_COL_SPECIES, rightData[2]).mul(_sv0).add(FloatVector.broadcast(_COL_SPECIES, rightData[0]).mul(_sv1).add(FloatVector.broadcast(_COL_SPECIES, rightData[1]).mul(_sv2))).intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
        return dest;
    }

    /**
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * This is the plain 4x3 * 3x3 matrix product: the inner dimensions meet, no operand is
     * extended.
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
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(right, dest);
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = java.lang.Math.fma(rightData[2], sd[8], java.lang.Math.fma(rightData[0], sd[0], rightData[1] * sd[4]));
        dd[1] = java.lang.Math.fma(rightData[2], sd[9], java.lang.Math.fma(rightData[0], sd[1], rightData[1] * sd[5]));
        dd[2] = java.lang.Math.fma(rightData[2], sd[10], java.lang.Math.fma(rightData[0], sd[2], rightData[1] * sd[6]));
        dd[3] = java.lang.Math.fma(rightData[2], sd[11], java.lang.Math.fma(rightData[0], sd[3], rightData[1] * sd[7]));
        dd[4] = java.lang.Math.fma(rightData[5], sd[8], java.lang.Math.fma(rightData[3], sd[0], rightData[4] * sd[4]));
        dd[5] = java.lang.Math.fma(rightData[5], sd[9], java.lang.Math.fma(rightData[3], sd[1], rightData[4] * sd[5]));
        dd[6] = java.lang.Math.fma(rightData[5], sd[10], java.lang.Math.fma(rightData[3], sd[2], rightData[4] * sd[6]));
        return mul_se021cdfd_1_fma(dest, sd, rightData, dd);
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x3 mul_mulAdd(Float3x3R right, @Mutated Double4x3 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float3x3Impl) right).data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = ((rightData[2]) * (sd[8]) + (((rightData[0]) * (sd[0]) + (rightData[1] * sd[4]))));
        dd[1] = ((rightData[2]) * (sd[9]) + (((rightData[0]) * (sd[1]) + (rightData[1] * sd[5]))));
        dd[2] = ((rightData[2]) * (sd[10]) + (((rightData[0]) * (sd[2]) + (rightData[1] * sd[6]))));
        dd[3] = ((rightData[2]) * (sd[11]) + (((rightData[0]) * (sd[3]) + (rightData[1] * sd[7]))));
        dd[4] = ((rightData[5]) * (sd[8]) + (((rightData[3]) * (sd[0]) + (rightData[4] * sd[4]))));
        dd[5] = ((rightData[5]) * (sd[9]) + (((rightData[3]) * (sd[1]) + (rightData[4] * sd[5]))));
        dd[6] = ((rightData[5]) * (sd[10]) + (((rightData[3]) * (sd[2]) + (rightData[4] * sd[6]))));
        return mul_se021cdfd_1_mulAdd(dest, sd, rightData, dd);
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
        if (SimdMath.USE_FMA) return preMul_fma(other, dest);
        return preMul_mulAdd(other, dest);
    }

    private Float4x3 preMul_fma(Float4x4R other, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x4Impl) other).data;
        float[] dd = ((Float4x3Impl) dest).data;
        float _r0 = sd[3];
        float _r1 = sd[2];
        float _r2 = sd[0];
        float _r3 = sd[1];
        float _r4 = sd[7];
        float _r5 = sd[6];
        float _r6 = sd[4];
        float _r7 = sd[5];
        float _r8 = sd[11];
        float _r9 = sd[10];
        float _r10 = sd[8];
        float _r11 = sd[9];
        {
            VectorSpecies<Float> _COL_SPECIES = COL_SPECIES;
            var _sv0 = FloatVector.fromArray(_COL_SPECIES, otherData, 12);
            var _sv1 = FloatVector.fromArray(_COL_SPECIES, otherData, 8);
            var _sv2 = FloatVector.fromArray(_COL_SPECIES, otherData, 0);
            var _sv3 = FloatVector.fromArray(_COL_SPECIES, otherData, 4);
            _sv0.fma(FloatVector.broadcast(_COL_SPECIES, _r0), _sv1.fma(FloatVector.broadcast(_COL_SPECIES, _r1), _sv2.fma(FloatVector.broadcast(_COL_SPECIES, _r2), _sv3.mul(FloatVector.broadcast(_COL_SPECIES, _r3))))).intoArray(dd, 0);
            _sv0.fma(FloatVector.broadcast(_COL_SPECIES, _r4), _sv1.fma(FloatVector.broadcast(_COL_SPECIES, _r5), _sv2.fma(FloatVector.broadcast(_COL_SPECIES, _r6), _sv3.mul(FloatVector.broadcast(_COL_SPECIES, _r7))))).intoArray(dd, 4);
            _sv0.fma(FloatVector.broadcast(_COL_SPECIES, _r8), _sv1.fma(FloatVector.broadcast(_COL_SPECIES, _r9), _sv2.fma(FloatVector.broadcast(_COL_SPECIES, _r10), _sv3.mul(FloatVector.broadcast(_COL_SPECIES, _r11))))).intoArray(dd, 8);
        }
        return dest;
    }

    private Float4x3 preMul_mulAdd(Float4x4R other, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x4Impl) other).data;
        float[] dd = ((Float4x3Impl) dest).data;
        float _r0 = sd[3];
        float _r1 = sd[2];
        float _r2 = sd[0];
        float _r3 = sd[1];
        float _r4 = sd[7];
        float _r5 = sd[6];
        float _r6 = sd[4];
        float _r7 = sd[5];
        float _r8 = sd[11];
        float _r9 = sd[10];
        float _r10 = sd[8];
        float _r11 = sd[9];
        preMul_s3bc98a71_tail(dd, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, otherData);
        return dest;
    }

    /** Private vector tail of {@code preMul_s3bc98a71}: loads, computes and stores every column; reached only through it. */
    private static void preMul_s3bc98a71_tail(float[] dd, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float[] otherData) {
        VectorSpecies<Float> _COL_SPECIES = COL_SPECIES;
        var _sv0 = FloatVector.fromArray(_COL_SPECIES, otherData, 12);
        var _sv1 = FloatVector.fromArray(_COL_SPECIES, otherData, 8);
        var _sv2 = FloatVector.fromArray(_COL_SPECIES, otherData, 0);
        var _sv3 = FloatVector.fromArray(_COL_SPECIES, otherData, 4);
        _sv0.mul(FloatVector.broadcast(_COL_SPECIES, _r0)).add(_sv1.mul(FloatVector.broadcast(_COL_SPECIES, _r1)).add(_sv2.mul(FloatVector.broadcast(_COL_SPECIES, _r2)).add(_sv3.mul(FloatVector.broadcast(_COL_SPECIES, _r3))))).intoArray(dd, 0);
        _sv0.mul(FloatVector.broadcast(_COL_SPECIES, _r4)).add(_sv1.mul(FloatVector.broadcast(_COL_SPECIES, _r5)).add(_sv2.mul(FloatVector.broadcast(_COL_SPECIES, _r6)).add(_sv3.mul(FloatVector.broadcast(_COL_SPECIES, _r7))))).intoArray(dd, 4);
        _sv0.mul(FloatVector.broadcast(_COL_SPECIES, _r8)).add(_sv1.mul(FloatVector.broadcast(_COL_SPECIES, _r9)).add(_sv2.mul(FloatVector.broadcast(_COL_SPECIES, _r10)).add(_sv3.mul(FloatVector.broadcast(_COL_SPECIES, _r11))))).intoArray(dd, 8);
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
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(other, dest);
        float[] sd = this.data;
        float[] otherData = ((Float4x4Impl) other).data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = java.lang.Math.fma(otherData[12], sd[3], java.lang.Math.fma(otherData[8], sd[2], java.lang.Math.fma(otherData[0], sd[0], otherData[4] * sd[1])));
        dd[1] = java.lang.Math.fma(otherData[13], sd[3], java.lang.Math.fma(otherData[9], sd[2], java.lang.Math.fma(otherData[1], sd[0], otherData[5] * sd[1])));
        dd[2] = java.lang.Math.fma(otherData[14], sd[3], java.lang.Math.fma(otherData[10], sd[2], java.lang.Math.fma(otherData[2], sd[0], otherData[6] * sd[1])));
        dd[3] = java.lang.Math.fma(otherData[15], sd[3], java.lang.Math.fma(otherData[11], sd[2], java.lang.Math.fma(otherData[3], sd[0], otherData[7] * sd[1])));
        dd[4] = java.lang.Math.fma(otherData[12], sd[7], java.lang.Math.fma(otherData[8], sd[6], java.lang.Math.fma(otherData[0], sd[4], otherData[4] * sd[5])));
        return preMul_sf86f58ae_1_fma(dest, sd, otherData, dd);
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x3 preMul_mulAdd(Float4x4R other, @Mutated Double4x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x4Impl) other).data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = ((otherData[12]) * (sd[3]) + (((otherData[8]) * (sd[2]) + (((otherData[0]) * (sd[0]) + (otherData[4] * sd[1]))))));
        dd[1] = ((otherData[13]) * (sd[3]) + (((otherData[9]) * (sd[2]) + (((otherData[1]) * (sd[0]) + (otherData[5] * sd[1]))))));
        dd[2] = ((otherData[14]) * (sd[3]) + (((otherData[10]) * (sd[2]) + (((otherData[2]) * (sd[0]) + (otherData[6] * sd[1]))))));
        dd[3] = ((otherData[15]) * (sd[3]) + (((otherData[11]) * (sd[2]) + (((otherData[3]) * (sd[0]) + (otherData[7] * sd[1]))))));
        dd[4] = ((otherData[12]) * (sd[7]) + (((otherData[8]) * (sd[6]) + (((otherData[0]) * (sd[4]) + (otherData[4] * sd[5]))))));
        return preMul_sf86f58ae_1_mulAdd(dest, sd, otherData, dd);
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
        dd[11] = ((otherData[15]) * (sd[11]) + (((otherData[11]) * (sd[10]) + (((otherData[3]) * (sd[8]) + (otherData[7] * sd[9]))))));
        return dest;
    }

    /** Piece 3 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Double4x3 preMul_sf86f58ae_2_fma(Double4x3 dest, float[] sd, float[] otherData, double[] dd) {
        dd[11] = java.lang.Math.fma(otherData[15], sd[11], java.lang.Math.fma(otherData[11], sd[10], java.lang.Math.fma(otherData[3], sd[8], otherData[7] * sd[9])));
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
        if (SimdMath.USE_FMA) return addScaled_fma(other, weight, dest);
        return addScaled_mulAdd(other, weight, dest);
    }

    private Float4x3 addScaled_fma(Float4x3R other, float weight, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x3Impl) other).data;
        float[] dd = ((Float4x3Impl) dest).data;
        VectorSpecies<Float> _COL_SPECIES = COL_SPECIES;
        var _sv0 = FloatVector.broadcast(_COL_SPECIES, weight);
        var _col1 = _sv0.fma(FloatVector.fromArray(_COL_SPECIES, otherData, 4), FloatVector.fromArray(_COL_SPECIES, sd, 4));
        var _col2 = _sv0.fma(FloatVector.fromArray(_COL_SPECIES, otherData, 8), FloatVector.fromArray(_COL_SPECIES, sd, 8));
        _sv0.fma(FloatVector.fromArray(_COL_SPECIES, otherData, 0), FloatVector.fromArray(_COL_SPECIES, sd, 0)).intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
        return dest;
    }

    private Float4x3 addScaled_mulAdd(Float4x3R other, float weight, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x3Impl) other).data;
        float[] dd = ((Float4x3Impl) dest).data;
        VectorSpecies<Float> _COL_SPECIES = COL_SPECIES;
        var _sv0 = FloatVector.broadcast(_COL_SPECIES, weight);
        var _col1 = _sv0.mul(FloatVector.fromArray(_COL_SPECIES, otherData, 4)).add(FloatVector.fromArray(_COL_SPECIES, sd, 4));
        var _col2 = _sv0.mul(FloatVector.fromArray(_COL_SPECIES, otherData, 8)).add(FloatVector.fromArray(_COL_SPECIES, sd, 8));
        _sv0.mul(FloatVector.fromArray(_COL_SPECIES, otherData, 0)).add(FloatVector.fromArray(_COL_SPECIES, sd, 0)).intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
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
        float[] otherData = ((Float4x3Impl) other).data;
        return addScaled(otherData[0], otherData[4], otherData[8], otherData[1], otherData[5], otherData[9], otherData[2], otherData[6], otherData[10], otherData[3], otherData[7], otherData[11], weight, dest);
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
        if (!org.joml2.internal.FmaSwitch.ON) return addScaled_mulAdd(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32, weight, dest);
        float[] sd = this.data;
        float[] dd = ((Float4x3Impl) dest).data;
        dd[0] = java.lang.Math.fma(weight, m00, sd[0]);
        dd[1] = java.lang.Math.fma(weight, m10, sd[1]);
        dd[2] = java.lang.Math.fma(weight, m20, sd[2]);
        dd[3] = java.lang.Math.fma(weight, m30, sd[3]);
        dd[4] = java.lang.Math.fma(weight, m01, sd[4]);
        dd[5] = java.lang.Math.fma(weight, m11, sd[5]);
        dd[6] = java.lang.Math.fma(weight, m21, sd[6]);
        dd[7] = java.lang.Math.fma(weight, m31, sd[7]);
        dd[8] = java.lang.Math.fma(weight, m02, sd[8]);
        dd[9] = java.lang.Math.fma(weight, m12, sd[9]);
        dd[10] = java.lang.Math.fma(weight, m22, sd[10]);
        dd[11] = java.lang.Math.fma(weight, m32, sd[11]);
        return dest;
    }

    /** {@code addScaled} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x3 addScaled_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32, float weight, @Mutated Float4x3 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x3Impl) dest).data;
        dd[0] = ((weight) * (m00) + (sd[0]));
        dd[1] = ((weight) * (m10) + (sd[1]));
        dd[2] = ((weight) * (m20) + (sd[2]));
        dd[3] = ((weight) * (m30) + (sd[3]));
        dd[4] = ((weight) * (m01) + (sd[4]));
        dd[5] = ((weight) * (m11) + (sd[5]));
        dd[6] = ((weight) * (m21) + (sd[6]));
        dd[7] = ((weight) * (m31) + (sd[7]));
        dd[8] = ((weight) * (m02) + (sd[8]));
        dd[9] = ((weight) * (m12) + (sd[9]));
        dd[10] = ((weight) * (m22) + (sd[10]));
        dd[11] = ((weight) * (m32) + (sd[11]));
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
        if (!org.joml2.internal.FmaSwitch.ON) return addScaled_mulAdd(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32, weight, dest);
        float[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = java.lang.Math.fma(weight, m00, sd[0]);
        dd[1] = java.lang.Math.fma(weight, m10, sd[1]);
        dd[2] = java.lang.Math.fma(weight, m20, sd[2]);
        dd[3] = java.lang.Math.fma(weight, m30, sd[3]);
        dd[4] = java.lang.Math.fma(weight, m01, sd[4]);
        dd[5] = java.lang.Math.fma(weight, m11, sd[5]);
        dd[6] = java.lang.Math.fma(weight, m21, sd[6]);
        dd[7] = java.lang.Math.fma(weight, m31, sd[7]);
        dd[8] = java.lang.Math.fma(weight, m02, sd[8]);
        dd[9] = java.lang.Math.fma(weight, m12, sd[9]);
        dd[10] = java.lang.Math.fma(weight, m22, sd[10]);
        dd[11] = java.lang.Math.fma(weight, m32, sd[11]);
        return dest;
    }

    /** {@code addScaled} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x3 addScaled_mulAdd(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32, float weight, @Mutated Double4x3 dest) {
        float[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        dd[0] = ((weight) * (m00) + (sd[0]));
        dd[1] = ((weight) * (m10) + (sd[1]));
        dd[2] = ((weight) * (m20) + (sd[2]));
        dd[3] = ((weight) * (m30) + (sd[3]));
        dd[4] = ((weight) * (m01) + (sd[4]));
        dd[5] = ((weight) * (m11) + (sd[5]));
        dd[6] = ((weight) * (m21) + (sd[6]));
        dd[7] = ((weight) * (m31) + (sd[7]));
        dd[8] = ((weight) * (m02) + (sd[8]));
        dd[9] = ((weight) * (m12) + (sd[9]));
        dd[10] = ((weight) * (m22) + (sd[10]));
        dd[11] = ((weight) * (m32) + (sd[11]));
        return dest;
    }

    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v}, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the right operand of the product
     * @param dest will hold the result
     * @return dest
     */
    public Float4 mul(Float3R v, @Mutated Float4 dest) {
        float[] vData = ((Float3Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        float vZ = vData[2];
        if (SimdMath.USE_FMA) return mul_fma(vX, vY, vZ, dest);
        return mul_mulAdd(vX, vY, vZ, dest);
    }

    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v}, and store the result in {@code dest}.
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
        float[] vData = ((Float3Impl) v).data;
        float vX = vData[0];
        float vY = vData[1];
        float vZ = vData[2];
        if (org.joml2.internal.FmaSwitch.ON) {
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
     * {@code this * v}, and store the result in {@code dest}.
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
        if (SimdMath.USE_FMA) return mul_fma(vX, vY, vZ, dest);
        return mul_mulAdd(vX, vY, vZ, dest);
    }

    private Float4 mul_fma(float vX, float vY, float vZ, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        VectorSpecies<Float> _COL_SPECIES = COL_SPECIES;
        FloatVector.fromArray(_COL_SPECIES, sd, 8).fma(FloatVector.broadcast(_COL_SPECIES, vZ), FloatVector.fromArray(_COL_SPECIES, sd, 0).fma(FloatVector.broadcast(_COL_SPECIES, vX), FloatVector.fromArray(_COL_SPECIES, sd, 4).mul(FloatVector.broadcast(_COL_SPECIES, vY)))).intoArray(dd, 0);
        return dest;
    }

    private Float4 mul_mulAdd(float vX, float vY, float vZ, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        VectorSpecies<Float> _COL_SPECIES = COL_SPECIES;
        FloatVector.fromArray(_COL_SPECIES, sd, 8).mul(FloatVector.broadcast(_COL_SPECIES, vZ)).add(FloatVector.fromArray(_COL_SPECIES, sd, 0).mul(FloatVector.broadcast(_COL_SPECIES, vX)).add(FloatVector.fromArray(_COL_SPECIES, sd, 4).mul(FloatVector.broadcast(_COL_SPECIES, vY)))).intoArray(dd, 0);
        return dest;
    }

    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v}, and store the result in {@code dest}.
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
        if (org.joml2.internal.FmaSwitch.ON) {
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
        return "Float4x3(\n    " + this.data[0] + ", " + this.data[4] + ", " + this.data[8] + "\n    " + this.data[1] + ", " + this.data[5] + ", " + this.data[9] + "\n    " + this.data[2] + ", " + this.data[6] + ", " + this.data[10] + "\n    " + this.data[3] + ", " + this.data[7] + ", " + this.data[11] + "\n)";
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
        float[] otherData = ((Float4x3Impl) other).data;
        float[] sd = this.data;
        return java.lang.Math.abs(sd[0] - otherData[0]) <= epsilon
            && java.lang.Math.abs(sd[4] - otherData[4]) <= epsilon
            && java.lang.Math.abs(sd[8] - otherData[8]) <= epsilon
            && java.lang.Math.abs(sd[1] - otherData[1]) <= epsilon
            && java.lang.Math.abs(sd[5] - otherData[5]) <= epsilon
            && java.lang.Math.abs(sd[9] - otherData[9]) <= epsilon
            && java.lang.Math.abs(sd[2] - otherData[2]) <= epsilon
            && java.lang.Math.abs(sd[6] - otherData[6]) <= epsilon
            && java.lang.Math.abs(sd[10] - otherData[10]) <= epsilon
            && java.lang.Math.abs(sd[3] - otherData[3]) <= epsilon
            && java.lang.Math.abs(sd[7] - otherData[7]) <= epsilon
            && java.lang.Math.abs(sd[11] - otherData[11]) <= epsilon;
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        float[] d = this.data;
        FloatVector.fromArray(COL_SPECIES, d, 0).intoArray(dest, offset);
        FloatVector.fromArray(COL_SPECIES, d, 4).intoArray(dest, offset + 4);
        FloatVector.fromArray(COL_SPECIES, d, 8).intoArray(dest, offset + 8);
        return dest;
    }

    @Mutated public Float4x3 loadCM(float[] src, int offset) {
        float[] d = this.data;
        FloatVector.fromArray(COL_SPECIES, src, offset).intoArray(d, 0);
        FloatVector.fromArray(COL_SPECIES, src, offset + 4).intoArray(d, 4);
        FloatVector.fromArray(COL_SPECIES, src, offset + 8).intoArray(d, 8);
        return this;
    }

    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf) {
        if (!buf.hasArray()) return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
        float[] d = this.data;
        float[] arr = buf.array();
        int off = buf.arrayOffset() + index;
        FloatVector.fromArray(COL_SPECIES, d, 0).intoArray(arr, off);
        FloatVector.fromArray(COL_SPECIES, d, 4).intoArray(arr, off + 4);
        FloatVector.fromArray(COL_SPECIES, d, 8).intoArray(arr, off + 8);
        return buf;
    }

    @Mutated public Float4x3 loadCMAbsolute(int index, FloatBuffer buf) {
        if (!buf.hasArray()) return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
        float[] d = this.data;
        float[] arr = buf.array();
        int off = buf.arrayOffset() + index;
        FloatVector.fromArray(COL_SPECIES, arr, off).intoArray(d, 0);
        FloatVector.fromArray(COL_SPECIES, arr, off + 4).intoArray(d, 4);
        FloatVector.fromArray(COL_SPECIES, arr, off + 8).intoArray(d, 8);
        return this;
    }

    public ByteBuffer storeCM(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCMAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }

    public ByteBuffer storeCMRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }

    @Mutated public Float4x3 loadCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float4x3 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }

    @Mutated public Float4x3 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return this;
    }

    public Float4x3 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }

    @Mutated public Float4x3 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }

    private boolean storeCMLanes(MemorySegment dest, long offset) {
        byte[] b = HeapSegments.laneBytes(dest, true);
        if (b == null) return false;
        int at = HeapSegments.index(dest, offset);
        float[] d = this.data;
        FloatVector.fromArray(COL_SPECIES, d, 0).reinterpretAsBytes().intoArray(b, at);
        FloatVector.fromArray(COL_SPECIES, d, 4).reinterpretAsBytes().intoArray(b, at + 16);
        FloatVector.fromArray(COL_SPECIES, d, 8).reinterpretAsBytes().intoArray(b, at + 32);
        return true;
    }

    public MemorySegment storeCM(long offset, @Mutated MemorySegment dest) {
        if (!dest.isNative() && storeCMLanes(dest, offset)) return dest;
        float[] d = this.data;
        FloatVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(dest, offset, ByteOrder.nativeOrder());
        FloatVector.fromArray(COL_SPECIES, d, 4).intoMemorySegment(dest, offset + 16L, ByteOrder.nativeOrder());
        FloatVector.fromArray(COL_SPECIES, d, 8).intoMemorySegment(dest, offset + 32L, ByteOrder.nativeOrder());
        return dest;
    }

    private boolean loadCMLanes(MemorySegment src, long offset) {
        byte[] b = HeapSegments.laneBytes(src, false);
        if (b == null) return false;
        int at = HeapSegments.index(src, offset);
        float[] d = this.data;
        ByteVector.fromArray(ByteVector.SPECIES_128, b, at).reinterpretAsFloats().intoArray(d, 0);
        ByteVector.fromArray(ByteVector.SPECIES_128, b, at + 16).reinterpretAsFloats().intoArray(d, 4);
        ByteVector.fromArray(ByteVector.SPECIES_128, b, at + 32).reinterpretAsFloats().intoArray(d, 8);
        return true;
    }

    @Mutated public Float4x3 loadCM(long offset, MemorySegment src) {
        if (!src.isNative() && loadCMLanes(src, offset)) return this;
        float[] d = this.data;
        FloatVector.fromMemorySegment(COL_SPECIES, src, offset, ByteOrder.nativeOrder()).intoArray(d, 0);
        FloatVector.fromMemorySegment(COL_SPECIES, src, offset + 16L, ByteOrder.nativeOrder()).intoArray(d, 4);
        FloatVector.fromMemorySegment(COL_SPECIES, src, offset + 32L, ByteOrder.nativeOrder()).intoArray(d, 8);
        return this;
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

    @Mutated public Float4x3 loadCM(double[] src, int offset) {
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

    public ByteBuffer storeCMDouble(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCMDoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeCMDoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 96) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 96);
        return buf;
    }

    @Mutated public Float4x3 loadCMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float4x3 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf);
    }

    @Mutated public Float4x3 loadCMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 96) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 96);
        return this;
    }

    public Float4x3 storeCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address);
    }

    @Mutated public Float4x3 loadCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address);
    }

    public MemorySegment storeCMDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCMDouble(this, 0L, dest); }

    public MemorySegment storeCMDouble(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest);
    }

    @Mutated public Float4x3 loadCMDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMDouble(this, 0L, src); }

    @Mutated public Float4x3 loadCMDouble(long offset, MemorySegment src) {
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

    public ByteBuffer storeRM(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRMAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }

    public ByteBuffer storeRMRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return buf;
    }

    @Mutated public Float4x3 loadRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float4x3 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }

    @Mutated public Float4x3 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return this;
    }

    public Float4x3 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }

    @Mutated public Float4x3 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }

    public MemorySegment storeRM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest); }

    public MemorySegment storeRM(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }

    @Mutated public Float4x3 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src); }

    @Mutated public Float4x3 loadRM(long offset, MemorySegment src) {
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

    @Mutated public Float4x3 loadRM(double[] src, int offset) {
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

    public ByteBuffer storeRMDouble(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRMDoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeRMDoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 96) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 96);
        return buf;
    }

    @Mutated public Float4x3 loadRMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, buf.position(), buf);
    }

    @Mutated public Float4x3 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf);
    }

    @Mutated public Float4x3 loadRMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 96) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 96);
        return this;
    }

    public Float4x3 storeRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address);
    }

    @Mutated public Float4x3 loadRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address);
    }

    public MemorySegment storeRMDouble(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRMDouble(this, 0L, dest); }

    public MemorySegment storeRMDouble(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest);
    }

    @Mutated public Float4x3 loadRMDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMDouble(this, 0L, src); }

    @Mutated public Float4x3 loadRMDouble(long offset, MemorySegment src) {
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

    @Mutated public Float4x3 loadCM(float[] src, int offset, int stride) {
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

    public ByteBuffer storeCM(@Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }

    public ByteBuffer storeCMAbsolute(int index, @Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }

    public ByteBuffer storeCMRelative(@Mutated ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride * 4);
        return buf;
    }

    @Mutated public Float4x3 loadCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float4x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }

    @Mutated public Float4x3 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride * 4);
        return this;
    }

    public Float4x3 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }

    @Mutated public Float4x3 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }

    public MemorySegment storeCM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest, stride); }

    public MemorySegment storeCM(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }

    @Mutated public Float4x3 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src, stride); }

    @Mutated public Float4x3 loadCM(long offset, MemorySegment src, int stride) {
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

    @Mutated public Float4x3 loadCM(double[] src, int offset, int stride) {
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

    public ByteBuffer storeCMDouble(@Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, buf.position(), buf, stride);
    }

    public ByteBuffer storeCMDoubleAbsolute(int index, @Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf, stride);
    }

    public ByteBuffer storeCMDoubleRelative(@Mutated ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride * 8);
        return buf;
    }

    @Mutated public Float4x3 loadCMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float4x3 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf, stride);
    }

    @Mutated public Float4x3 loadCMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride * 8);
        return this;
    }

    public Float4x3 storeCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address, stride);
    }

    @Mutated public Float4x3 loadCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address, stride);
    }

    public MemorySegment storeCMDouble(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCMDouble(this, 0L, dest, stride); }

    public MemorySegment storeCMDouble(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest, stride);
    }

    @Mutated public Float4x3 loadCMDouble(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMDouble(this, 0L, src, stride); }

    @Mutated public Float4x3 loadCMDouble(long offset, MemorySegment src, int stride) {
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

    public ByteBuffer storeRM(@Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }

    public ByteBuffer storeRMAbsolute(int index, @Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }

    public ByteBuffer storeRMRelative(@Mutated ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride * 4);
        return buf;
    }

    @Mutated public Float4x3 loadRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float4x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }

    @Mutated public Float4x3 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride * 4);
        return this;
    }

    public Float4x3 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }

    @Mutated public Float4x3 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }

    public MemorySegment storeRM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest, stride); }

    public MemorySegment storeRM(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }

    @Mutated public Float4x3 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src, stride); }

    @Mutated public Float4x3 loadRM(long offset, MemorySegment src, int stride) {
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

    @Mutated public Float4x3 loadRM(double[] src, int offset, int stride) {
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

    public ByteBuffer storeRMDouble(@Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, buf.position(), buf, stride);
    }

    public ByteBuffer storeRMDoubleAbsolute(int index, @Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf, stride);
    }

    public ByteBuffer storeRMDoubleRelative(@Mutated ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride * 8);
        return buf;
    }

    @Mutated public Float4x3 loadRMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Float4x3 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf, stride);
    }

    @Mutated public Float4x3 loadRMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride * 8);
        return this;
    }

    public Float4x3 storeRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address, stride);
    }

    @Mutated public Float4x3 loadRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address, stride);
    }

    public MemorySegment storeRMDouble(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRMDouble(this, 0L, dest, stride); }

    public MemorySegment storeRMDouble(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest, stride);
    }

    @Mutated public Float4x3 loadRMDouble(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMDouble(this, 0L, src, stride); }

    @Mutated public Float4x3 loadRMDouble(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMDouble(this, offset, src, stride);
    }

    private static final VectorSpecies<Float> COL_SPECIES = FloatVector.SPECIES_128;
    private static final float[] DATA_0 = new float[] {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
}
