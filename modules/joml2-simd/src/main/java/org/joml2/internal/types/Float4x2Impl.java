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
 * Generated implementation of {@link Float4x2} backed by a {@code float[]} array, with Vector API
 * SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Float4x2Impl implements Float4x2 {

    public float[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Float4x2SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float4x2SegOpsUnsafe()
                        : new Float4x2SegOpsMS();
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
        float[] sd = this.data;
        return (float) Math.sqrt(Math.fma(sd[0], sd[0], sd[4] * sd[4]) + Math.fma(sd[1], sd[1], sd[5] * sd[5]) + (Math.fma(sd[2], sd[2], sd[6] * sd[6]) + Math.fma(sd[3], sd[3], sd[7] * sd[7])));
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
        var _col0 = FloatVector.fromArray(COL_SPECIES, sd, 0);
        var _col1 = FloatVector.fromArray(COL_SPECIES, sd, 4);
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
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
        float _buf0 = sd[1];
        dd[2] = sd[2];
        float _buf1 = sd[3];
        dd[4] = sd[4];
        float _buf2 = sd[5];
        dd[6] = sd[6];
        dd[7] = sd[7];
        dd[1] = _buf0;
        dd[3] = _buf1;
        dd[5] = _buf2;
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
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _lanes = _sp.length();
        int _i = 0;
        for (; _i + _lanes <= 8; _i += _lanes) {
            FloatVector.fromArray(_sp, otherData, _i).add(FloatVector.fromArray(_sp, sd, _i)).intoArray(dd, _i);
        }
        for (; _i < 8; _i++) {
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
    public Double4x2 add(Float4x2R other, @Mutated Double4x2 dest) {
        return add(other.m00(), other.m01(), other.m10(), other.m11(), other.m20(), other.m21(), other.m30(), other.m31(), dest);
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
        var _sv0 = FloatVector.broadcast(COL_SPECIES, scalar);
        var _col0 = _sv0.mul(FloatVector.fromArray(COL_SPECIES, sd, 0));
        var _col1 = _sv0.mul(FloatVector.fromArray(COL_SPECIES, sd, 4));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
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
        var _col0 = FloatVector.fromArray(COL_SPECIES, sd, 0).neg();
        var _col1 = FloatVector.fromArray(COL_SPECIES, sd, 4).neg();
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
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
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _lanes = _sp.length();
        int _i = 0;
        for (; _i + _lanes <= 8; _i += _lanes) {
            FloatVector.fromArray(_sp, sd, _i).sub(FloatVector.fromArray(_sp, otherData, _i)).intoArray(dd, _i);
        }
        for (; _i < 8; _i++) {
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
    public Double4x2 sub(Float4x2R other, @Mutated Double4x2 dest) {
        return sub(other.m00(), other.m01(), other.m10(), other.m11(), other.m20(), other.m21(), other.m30(), other.m31(), dest);
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
        var _col0 = FloatVector.fromArray(COL_SPECIES, vData, 0);
        var _col1 = FloatVector.fromArray(COL_SPECIES, vData, 4);
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
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
        if (FloatVector.SPECIES_PREFERRED.length() >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, DATA_0, 0).intoArray(dd, 0);
        } else {
            FloatVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            FloatVector.fromArray(COL_SPECIES, DATA_0, 4).intoArray(dd, 4);
        }
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
        if (SimdMath.USE_FMA) return lerp_fma(other, t, dest);
        return lerp_mulAdd(other, t, dest);
    }

    private Float4x2 lerp_fma(Float4x2R other, float t, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x2Impl) other).data;
        float[] dd = ((Float4x2Impl) dest).data;
        var _sv0 = FloatVector.broadcast(COL_SPECIES, t);
        var _sv1 = FloatVector.fromArray(COL_SPECIES, sd, 0);
        var _col0 = _sv0.fma(FloatVector.fromArray(COL_SPECIES, otherData, 0).sub(_sv1), _sv1);
        var _sv2 = FloatVector.fromArray(COL_SPECIES, sd, 4);
        var _col1 = _sv0.fma(FloatVector.fromArray(COL_SPECIES, otherData, 4).sub(_sv2), _sv2);
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        return dest;
    }

    private Float4x2 lerp_mulAdd(Float4x2R other, float t, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x2Impl) other).data;
        float[] dd = ((Float4x2Impl) dest).data;
        var _sv0 = FloatVector.broadcast(COL_SPECIES, t);
        var _sv1 = FloatVector.fromArray(COL_SPECIES, sd, 0);
        var _col0 = _sv0.mul(FloatVector.fromArray(COL_SPECIES, otherData, 0).sub(_sv1)).add(_sv1);
        var _sv2 = FloatVector.fromArray(COL_SPECIES, sd, 4);
        var _col1 = _sv0.mul(FloatVector.fromArray(COL_SPECIES, otherData, 4).sub(_sv2)).add(_sv2);
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
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
        return lerp(other.m00(), other.m01(), other.m10(), other.m11(), other.m20(), other.m21(), other.m30(), other.m31(), t, dest);
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
        if (SimdMath.USE_FMA) return lerp_fma(m00, m01, m10, m11, m20, m21, m30, m31, t, dest);
        return lerp_mulAdd(m00, m01, m10, m11, m20, m21, m30, m31, t, dest);
    }

    private Float4x2 lerp_fma(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31, float t, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x2Impl) dest).data;
        var _sv0 = FloatVector.broadcast(COL_SPECIES, t);
        var _sv1 = FloatVector.fromArray(COL_SPECIES, sd, 0);
        var _col0 = _sv0.fma(FloatVector.zero(COL_SPECIES).withLane(0, m00).withLane(1, m10).withLane(2, m20).withLane(3, m30).sub(_sv1), _sv1);
        var _sv2 = FloatVector.fromArray(COL_SPECIES, sd, 4);
        var _col1 = _sv0.fma(FloatVector.zero(COL_SPECIES).withLane(0, m01).withLane(1, m11).withLane(2, m21).withLane(3, m31).sub(_sv2), _sv2);
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        return dest;
    }

    private Float4x2 lerp_mulAdd(float m00, float m01, float m10, float m11, float m20, float m21, float m30, float m31, float t, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4x2Impl) dest).data;
        var _sv0 = FloatVector.broadcast(COL_SPECIES, t);
        var _sv1 = FloatVector.fromArray(COL_SPECIES, sd, 0);
        var _col0 = _sv0.mul(FloatVector.zero(COL_SPECIES).withLane(0, m00).withLane(1, m10).withLane(2, m20).withLane(3, m30).sub(_sv1)).add(_sv1);
        var _sv2 = FloatVector.fromArray(COL_SPECIES, sd, 4);
        var _col1 = _sv0.mul(FloatVector.zero(COL_SPECIES).withLane(0, m01).withLane(1, m11).withLane(2, m21).withLane(3, m31).sub(_sv2)).add(_sv2);
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
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
        if (SimdMath.USE_FMA) return mul_fma(right, dest);
        return mul_mulAdd(right, dest);
    }

    private Float4x2 mul_fma(Float2x2R right, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float4x2Impl) dest).data;
        var _sv0 = FloatVector.fromArray(COL_SPECIES, sd, 0);
        var _sv1 = FloatVector.fromArray(COL_SPECIES, sd, 4);
        var _col0 = FloatVector.broadcast(COL_SPECIES, rightData[0]).fma(_sv0, FloatVector.broadcast(COL_SPECIES, rightData[1]).mul(_sv1));
        var _col1 = FloatVector.broadcast(COL_SPECIES, rightData[2]).fma(_sv0, FloatVector.broadcast(COL_SPECIES, rightData[3]).mul(_sv1));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        return dest;
    }

    private Float4x2 mul_mulAdd(Float2x2R right, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] rightData = ((Float2x2Impl) right).data;
        float[] dd = ((Float4x2Impl) dest).data;
        var _sv0 = FloatVector.fromArray(COL_SPECIES, sd, 0);
        var _sv1 = FloatVector.fromArray(COL_SPECIES, sd, 4);
        var _col0 = FloatVector.broadcast(COL_SPECIES, rightData[0]).mul(_sv0).add(FloatVector.broadcast(COL_SPECIES, rightData[1]).mul(_sv1));
        var _col1 = FloatVector.broadcast(COL_SPECIES, rightData[2]).mul(_sv0).add(FloatVector.broadcast(COL_SPECIES, rightData[3]).mul(_sv1));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
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
        float _buf0 = Math.fma(rightData[0], sd[0], rightData[1] * sd[4]);
        float _buf1 = Math.fma(rightData[0], sd[1], rightData[1] * sd[5]);
        float _buf2 = Math.fma(rightData[0], sd[2], rightData[1] * sd[6]);
        float _buf3 = Math.fma(rightData[0], sd[3], rightData[1] * sd[7]);
        dd[4] = Math.fma(rightData[2], sd[0], rightData[3] * sd[4]);
        dd[5] = Math.fma(rightData[2], sd[1], rightData[3] * sd[5]);
        dd[6] = Math.fma(rightData[2], sd[2], rightData[3] * sd[6]);
        dd[7] = Math.fma(rightData[2], sd[3], rightData[3] * sd[7]);
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[3] = _buf3;
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
        if (SimdMath.USE_FMA) return preMul_fma(other, dest);
        return preMul_mulAdd(other, dest);
    }

    private Float4x2 preMul_fma(Float4x4R other, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x4Impl) other).data;
        float[] dd = ((Float4x2Impl) dest).data;
        var _sv0 = FloatVector.fromArray(COL_SPECIES, otherData, 12);
        var _sv1 = FloatVector.fromArray(COL_SPECIES, otherData, 8);
        var _sv2 = FloatVector.fromArray(COL_SPECIES, otherData, 0);
        var _sv3 = FloatVector.fromArray(COL_SPECIES, otherData, 4);
        var _col0 = _sv0.fma(FloatVector.broadcast(COL_SPECIES, sd[3]), _sv1.fma(FloatVector.broadcast(COL_SPECIES, sd[2]), _sv2.fma(FloatVector.broadcast(COL_SPECIES, sd[0]), _sv3.mul(FloatVector.broadcast(COL_SPECIES, sd[1])))));
        var _col1 = _sv0.fma(FloatVector.broadcast(COL_SPECIES, sd[7]), _sv1.fma(FloatVector.broadcast(COL_SPECIES, sd[6]), _sv2.fma(FloatVector.broadcast(COL_SPECIES, sd[4]), _sv3.mul(FloatVector.broadcast(COL_SPECIES, sd[5])))));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        return dest;
    }

    private Float4x2 preMul_mulAdd(Float4x4R other, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x4Impl) other).data;
        float[] dd = ((Float4x2Impl) dest).data;
        var _sv0 = FloatVector.fromArray(COL_SPECIES, otherData, 12);
        var _sv1 = FloatVector.fromArray(COL_SPECIES, otherData, 8);
        var _sv2 = FloatVector.fromArray(COL_SPECIES, otherData, 0);
        var _sv3 = FloatVector.fromArray(COL_SPECIES, otherData, 4);
        var _col0 = _sv0.mul(FloatVector.broadcast(COL_SPECIES, sd[3])).add(_sv1.mul(FloatVector.broadcast(COL_SPECIES, sd[2])).add(_sv2.mul(FloatVector.broadcast(COL_SPECIES, sd[0])).add(_sv3.mul(FloatVector.broadcast(COL_SPECIES, sd[1])))));
        var _col1 = _sv0.mul(FloatVector.broadcast(COL_SPECIES, sd[7])).add(_sv1.mul(FloatVector.broadcast(COL_SPECIES, sd[6])).add(_sv2.mul(FloatVector.broadcast(COL_SPECIES, sd[4])).add(_sv3.mul(FloatVector.broadcast(COL_SPECIES, sd[5])))));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
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
        float _buf0 = Math.fma(otherData[12], sd[3], Math.fma(otherData[8], sd[2], Math.fma(otherData[0], sd[0], otherData[4] * sd[1])));
        float _buf1 = Math.fma(otherData[13], sd[3], Math.fma(otherData[9], sd[2], Math.fma(otherData[1], sd[0], otherData[5] * sd[1])));
        float _buf2 = Math.fma(otherData[14], sd[3], Math.fma(otherData[10], sd[2], Math.fma(otherData[2], sd[0], otherData[6] * sd[1])));
        dd[3] = Math.fma(otherData[15], sd[3], Math.fma(otherData[11], sd[2], Math.fma(otherData[3], sd[0], otherData[7] * sd[1])));
        return preMul_sd163df3b_1(dest, sd, otherData, dd, _buf0, _buf1, _buf2, Math.fma(otherData[12], sd[7], Math.fma(otherData[8], sd[6], Math.fma(otherData[0], sd[4], otherData[4] * sd[5]))));
    }

    /** Piece 2 of {@code preMul}, split to fit the inline budget; reached only through it. */
    private Double4x2 preMul_sd163df3b_1(Double4x2 dest, float[] sd, float[] otherData, double[] dd, float _buf0, float _buf1, float _buf2, float _buf3) {
        float _buf4 = Math.fma(otherData[13], sd[7], Math.fma(otherData[9], sd[6], Math.fma(otherData[1], sd[4], otherData[5] * sd[5])));
        float _buf5 = Math.fma(otherData[14], sd[7], Math.fma(otherData[10], sd[6], Math.fma(otherData[2], sd[4], otherData[6] * sd[5])));
        dd[7] = Math.fma(otherData[15], sd[7], Math.fma(otherData[11], sd[6], Math.fma(otherData[3], sd[4], otherData[7] * sd[5])));
        dd[0] = _buf0;
        dd[1] = _buf1;
        dd[2] = _buf2;
        dd[4] = _buf3;
        dd[5] = _buf4;
        dd[6] = _buf5;
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
        if (SimdMath.USE_FMA) return addScaled_fma(other, weight, dest);
        return addScaled_mulAdd(other, weight, dest);
    }

    private Float4x2 addScaled_fma(Float4x2R other, float weight, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x2Impl) other).data;
        float[] dd = ((Float4x2Impl) dest).data;
        var _sv0 = FloatVector.broadcast(COL_SPECIES, weight);
        var _col0 = _sv0.fma(FloatVector.fromArray(COL_SPECIES, otherData, 0), FloatVector.fromArray(COL_SPECIES, sd, 0));
        var _col1 = _sv0.fma(FloatVector.fromArray(COL_SPECIES, otherData, 4), FloatVector.fromArray(COL_SPECIES, sd, 4));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        return dest;
    }

    private Float4x2 addScaled_mulAdd(Float4x2R other, float weight, @Mutated Float4x2 dest) {
        float[] sd = this.data;
        float[] otherData = ((Float4x2Impl) other).data;
        float[] dd = ((Float4x2Impl) dest).data;
        var _sv0 = FloatVector.broadcast(COL_SPECIES, weight);
        var _col0 = _sv0.mul(FloatVector.fromArray(COL_SPECIES, otherData, 0)).add(FloatVector.fromArray(COL_SPECIES, sd, 0));
        var _col1 = _sv0.mul(FloatVector.fromArray(COL_SPECIES, otherData, 4)).add(FloatVector.fromArray(COL_SPECIES, sd, 4));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
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
        float[] sd = this.data;
        float[] dd = ((Float4x2Impl) dest).data;
        dd[0] = Math.fma(weight, m00, sd[0]);
        dd[1] = Math.fma(weight, m10, sd[1]);
        dd[2] = Math.fma(weight, m20, sd[2]);
        dd[3] = Math.fma(weight, m30, sd[3]);
        dd[4] = Math.fma(weight, m01, sd[4]);
        dd[5] = Math.fma(weight, m11, sd[5]);
        dd[6] = Math.fma(weight, m21, sd[6]);
        dd[7] = Math.fma(weight, m31, sd[7]);
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double4x2Impl) dest).data;
        dd[0] = Math.fma(weight, m00, sd[0]);
        dd[1] = Math.fma(weight, m10, sd[1]);
        dd[2] = Math.fma(weight, m20, sd[2]);
        dd[3] = Math.fma(weight, m30, sd[3]);
        dd[4] = Math.fma(weight, m01, sd[4]);
        dd[5] = Math.fma(weight, m11, sd[5]);
        dd[6] = Math.fma(weight, m21, sd[6]);
        dd[7] = Math.fma(weight, m31, sd[7]);
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
    public Float4 mul(Float2R v, @Mutated Float4 dest) {
        return mul(v.x(), v.y(), dest);
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
        return mul(v.x(), v.y(), dest);
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
        if (SimdMath.USE_FMA) return mul_fma(vX, vY, dest);
        return mul_mulAdd(vX, vY, dest);
    }

    private Float4 mul_fma(float vX, float vY, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        var _col0 = FloatVector.fromArray(COL_SPECIES, sd, 0).fma(FloatVector.broadcast(COL_SPECIES, vX), FloatVector.fromArray(COL_SPECIES, sd, 4).mul(FloatVector.broadcast(COL_SPECIES, vY)));
        _col0.intoArray(dd, 0);
        return dest;
    }

    private Float4 mul_mulAdd(float vX, float vY, @Mutated Float4 dest) {
        float[] sd = this.data;
        float[] dd = ((Float4Impl) dest).data;
        var _col0 = FloatVector.fromArray(COL_SPECIES, sd, 0).mul(FloatVector.broadcast(COL_SPECIES, vX)).add(FloatVector.fromArray(COL_SPECIES, sd, 4).mul(FloatVector.broadcast(COL_SPECIES, vY)));
        _col0.intoArray(dd, 0);
        return dest;
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
        float[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        dd[0] = Math.fma(sd[0], vX, sd[4] * vY);
        dd[1] = Math.fma(sd[1], vX, sd[5] * vY);
        dd[2] = Math.fma(sd[2], vX, sd[6] * vY);
        dd[3] = Math.fma(sd[3], vX, sd[7] * vY);
        return dest;
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
        return Math.abs(data[0] - other.m00()) <= epsilon
            && Math.abs(data[4] - other.m01()) <= epsilon
            && Math.abs(data[1] - other.m10()) <= epsilon
            && Math.abs(data[5] - other.m11()) <= epsilon
            && Math.abs(data[2] - other.m20()) <= epsilon
            && Math.abs(data[6] - other.m21()) <= epsilon
            && Math.abs(data[3] - other.m30()) <= epsilon
            && Math.abs(data[7] - other.m31()) <= epsilon;
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        float[] d = this.data;
        if (FloatVector.SPECIES_PREFERRED.length() >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, d, 0).intoArray(dest, offset);
        } else {
            FloatVector.fromArray(COL_SPECIES, d, 0).intoArray(dest, offset);
            FloatVector.fromArray(COL_SPECIES, d, 4).intoArray(dest, offset + 4);
        }
        return dest;
    }
    public @Mutated Float4x2 loadCM(float[] src, int offset) {
        float[] d = this.data;
        if (FloatVector.SPECIES_PREFERRED.length() >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, src, offset).intoArray(d, 0);
        } else {
            FloatVector.fromArray(COL_SPECIES, src, offset).intoArray(d, 0);
            FloatVector.fromArray(COL_SPECIES, src, offset + 4).intoArray(d, 4);
        }
        return this;
    }
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf) {
        float[] d = this.data;
        if (buf.hasArray()) {
            float[] arr = buf.array();
            int off = buf.arrayOffset() + index;
            if (FloatVector.SPECIES_PREFERRED.length() >= 8) {
                FloatVector.fromArray(FloatVector.SPECIES_256, d, 0).intoArray(arr, off);
            } else {
                FloatVector.fromArray(COL_SPECIES, d, 0).intoArray(arr, off);
                FloatVector.fromArray(COL_SPECIES, d, 4).intoArray(arr, off + 4);
            }
        } else if (buf.order() != ByteOrder.nativeOrder() || buf.isReadOnly()) {
            return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
        } else {
            MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
            long baseOff = (long) index * 4;
            if (FloatVector.SPECIES_PREFERRED.length() >= 8) {
                FloatVector.fromArray(FloatVector.SPECIES_256, d, 0).intoMemorySegment(seg, baseOff, ByteOrder.nativeOrder());
            } else {
                FloatVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(seg, baseOff, ByteOrder.nativeOrder());
                FloatVector.fromArray(COL_SPECIES, d, 4).intoMemorySegment(seg, baseOff + 16L, ByteOrder.nativeOrder());
            }
        }
        return buf;
    }
    @Mutated public Float4x2 loadCMAbsolute(int index, FloatBuffer buf) {
        float[] d = this.data;
        if (buf.hasArray()) {
            float[] arr = buf.array();
            int off = buf.arrayOffset() + index;
            if (FloatVector.SPECIES_PREFERRED.length() >= 8) {
                FloatVector.fromArray(FloatVector.SPECIES_256, arr, off).intoArray(d, 0);
            } else {
                FloatVector.fromArray(COL_SPECIES, arr, off).intoArray(d, 0);
                FloatVector.fromArray(COL_SPECIES, arr, off + 4).intoArray(d, 4);
            }
        } else if (buf.order() != ByteOrder.nativeOrder()) {
            return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
        } else {
            MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
            long baseOff = (long) index * 4;
            if (FloatVector.SPECIES_PREFERRED.length() >= 8) {
                FloatVector.fromMemorySegment(FloatVector.SPECIES_256, seg, baseOff, ByteOrder.nativeOrder()).intoArray(d, 0);
            } else {
                FloatVector.fromMemorySegment(COL_SPECIES, seg, baseOff, ByteOrder.nativeOrder()).intoArray(d, 0);
                FloatVector.fromMemorySegment(COL_SPECIES, seg, baseOff + 16L, ByteOrder.nativeOrder()).intoArray(d, 4);
            }
        }
        return this;
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf) {
        if (buf.order() != ByteOrder.nativeOrder() || buf.isReadOnly()) return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
        float[] d = this.data;
        MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
        if (FloatVector.SPECIES_PREFERRED.length() >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, d, 0).intoMemorySegment(seg, index, ByteOrder.nativeOrder());
        } else {
            FloatVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(seg, index, ByteOrder.nativeOrder());
            FloatVector.fromArray(COL_SPECIES, d, 4).intoMemorySegment(seg, index + 16L, ByteOrder.nativeOrder());
        }
        return buf;
    }
    public Float4x2 loadCMAbsolute(int index, ByteBuffer buf) {
        if (buf.order() != ByteOrder.nativeOrder()) return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
        float[] d = this.data;
        MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
        if (FloatVector.SPECIES_PREFERRED.length() >= 8) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, seg, index, ByteOrder.nativeOrder()).intoArray(d, 0);
        } else {
            FloatVector.fromMemorySegment(COL_SPECIES, seg, index, ByteOrder.nativeOrder()).intoArray(d, 0);
            FloatVector.fromMemorySegment(COL_SPECIES, seg, index + 16L, ByteOrder.nativeOrder()).intoArray(d, 4);
        }
        return this;
    }
    public Float4x2 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }
    @Mutated public Float4x2 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }
    public MemorySegment storeCM(long offset, MemorySegment dest) {
        float[] d = this.data;
        MemorySegment seg = dest;
        if (FloatVector.SPECIES_PREFERRED.length() >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, d, 0).intoMemorySegment(seg, offset, ByteOrder.nativeOrder());
        } else {
            FloatVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(seg, offset, ByteOrder.nativeOrder());
            FloatVector.fromArray(COL_SPECIES, d, 4).intoMemorySegment(seg, offset + 16L, ByteOrder.nativeOrder());
        }
        return dest;
    }
    public Float4x2 loadCM(long offset, MemorySegment src) {
        float[] d = this.data;
        MemorySegment seg = src;
        if (FloatVector.SPECIES_PREFERRED.length() >= 8) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, seg, offset, ByteOrder.nativeOrder()).intoArray(d, 0);
        } else {
            FloatVector.fromMemorySegment(COL_SPECIES, seg, offset, ByteOrder.nativeOrder()).intoArray(d, 0);
            FloatVector.fromMemorySegment(COL_SPECIES, seg, offset + 16L, ByteOrder.nativeOrder()).intoArray(d, 4);
        }
        return this;
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.data[0];
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
        this.data[0] = (float) src[offset + 0];
        this.data[1] = (float) src[offset + 1];
        this.data[2] = (float) src[offset + 2];
        this.data[3] = (float) src[offset + 3];
        this.data[4] = (float) src[offset + 4];
        this.data[5] = (float) src[offset + 5];
        this.data[6] = (float) src[offset + 6];
        this.data[7] = (float) src[offset + 7];
        return this;
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    @Mutated public Float4x2 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf);
    }
    public Float4x2 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf);
    }
    public Float4x2 storeCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address);
    }
    @Mutated public Float4x2 loadCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address);
    }
    public MemorySegment storeCMDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest);
    }
    public Float4x2 loadCMDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMDouble(this, offset, src);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        if (dest == this.data) return storeRM_aliased(dest, offset);
        return storeRM_distinct(dest, offset);
    }
    private float[] storeRM_distinct(float[] dest, int offset) {
        dest[offset + 0] = this.data[0];
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
        float t0 = d[0];
        float t1 = d[1];
        float t2 = d[2];
        float t3 = d[3];
        float t4 = d[4];
        float t5 = d[5];
        float t6 = d[6];
        float t7 = d[7];
        dest[offset + 0] = t0;
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
        this.data[0] = src[offset + 0];
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
        float t0 = src[offset + 0];
        float t1 = src[offset + 1];
        float t2 = src[offset + 2];
        float t3 = src[offset + 3];
        float t4 = src[offset + 4];
        float t5 = src[offset + 5];
        float t6 = src[offset + 6];
        float t7 = src[offset + 7];
        float[] d = this.data;
        d[0] = t0;
        d[4] = t1;
        d[1] = t2;
        d[5] = t3;
        d[2] = t4;
        d[6] = t5;
        d[3] = t6;
        d[7] = t7;
        return this;
    }
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    @Mutated public Float4x2 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public Float4x2 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public Float4x2 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }
    @Mutated public Float4x2 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }
    public MemorySegment storeRM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }
    public Float4x2 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.data[0];
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
        this.data[0] = (float) src[offset + 0];
        this.data[4] = (float) src[offset + 1];
        this.data[1] = (float) src[offset + 2];
        this.data[5] = (float) src[offset + 3];
        this.data[2] = (float) src[offset + 4];
        this.data[6] = (float) src[offset + 5];
        this.data[3] = (float) src[offset + 6];
        this.data[7] = (float) src[offset + 7];
        return this;
    }
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    @Mutated public Float4x2 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf);
    }
    public Float4x2 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf);
    }
    public Float4x2 storeRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address);
    }
    @Mutated public Float4x2 loadRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address);
    }
    public MemorySegment storeRMDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest);
    }
    public Float4x2 loadRMDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMDouble(this, offset, src);
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
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float4x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public Float4x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public Float4x2 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }
    @Mutated public Float4x2 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }
    public MemorySegment storeCM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }
    public Float4x2 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src, stride);
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
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float4x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf, stride);
    }
    public Float4x2 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf, stride);
    }
    public Float4x2 storeCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address, stride);
    }
    @Mutated public Float4x2 loadCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address, stride);
    }
    public MemorySegment storeCMDouble(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest, stride);
    }
    public Float4x2 loadCMDouble(long offset, MemorySegment src, int stride) {
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
        float t0 = d[0];
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
        dest[offset] = t0;
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
        float t0 = src[offset];
        float t1 = src[offset + 1];
        float t2 = src[_p1];
        float t3 = src[_p1 + 1];
        float t4 = src[_p2];
        float t5 = src[_p2 + 1];
        float t6 = src[_p3];
        float t7 = src[_p3 + 1];
        float[] d = this.data;
        d[0] = t0;
        d[4] = t1;
        d[1] = t2;
        d[5] = t3;
        d[2] = t4;
        d[6] = t5;
        d[3] = t6;
        d[7] = t7;
        return this;
    }
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float4x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public Float4x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public Float4x2 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }
    @Mutated public Float4x2 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }
    public MemorySegment storeRM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }
    public Float4x2 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src, stride);
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
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float4x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf, stride);
    }
    public Float4x2 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf, stride);
    }
    public Float4x2 storeRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address, stride);
    }
    @Mutated public Float4x2 loadRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address, stride);
    }
    public MemorySegment storeRMDouble(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest, stride);
    }
    public Float4x2 loadRMDouble(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMDouble(this, offset, src, stride);
    }

    private static final VectorSpecies<Float> COL_SPECIES = FloatVector.SPECIES_128;
    private static final float[] DATA_0 = new float[] {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f};

}
