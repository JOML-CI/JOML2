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
 * Generated implementation of {@link Double4x3} backed by a {@code double[]} array, with Vector API
 * SIMD kernels where profitable.
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
        double[] sd = this.data;
        return Math.sqrt(Math.fma(sd[0], sd[0], Math.fma(sd[4], sd[4], sd[8] * sd[8])) + Math.fma(sd[1], sd[1], Math.fma(sd[5], sd[5], sd[9] * sd[9])) + (Math.fma(sd[2], sd[2], Math.fma(sd[6], sd[6], sd[10] * sd[10])) + Math.fma(sd[3], sd[3], Math.fma(sd[7], sd[7], sd[11] * sd[11]))));
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
        var _col0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _col1 = DoubleVector.fromArray(COL_SPECIES, sd, 4);
        var _col2 = DoubleVector.fromArray(COL_SPECIES, sd, 8);
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
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
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _lanes = _sp.length();
        int _i = 0;
        for (; _i + _lanes <= 12; _i += _lanes) {
            DoubleVector.fromArray(_sp, otherData, _i).add(DoubleVector.fromArray(_sp, sd, _i)).intoArray(dd, _i);
        }
        for (; _i < 12; _i++) {
            dd[_i] = otherData[_i] + sd[_i];
        }
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
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, scalar);
        var _col0 = _sv0.mul(DoubleVector.fromArray(COL_SPECIES, sd, 0));
        var _col1 = _sv0.mul(DoubleVector.fromArray(COL_SPECIES, sd, 4));
        var _col2 = _sv0.mul(DoubleVector.fromArray(COL_SPECIES, sd, 8));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
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
        var _col0 = DoubleVector.fromArray(COL_SPECIES, sd, 0).neg();
        var _col1 = DoubleVector.fromArray(COL_SPECIES, sd, 4).neg();
        var _col2 = DoubleVector.fromArray(COL_SPECIES, sd, 8).neg();
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
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
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _lanes = _sp.length();
        int _i = 0;
        for (; _i + _lanes <= 12; _i += _lanes) {
            DoubleVector.fromArray(_sp, sd, _i).sub(DoubleVector.fromArray(_sp, otherData, _i)).intoArray(dd, _i);
        }
        for (; _i < 12; _i++) {
            dd[_i] = sd[_i] - otherData[_i];
        }
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
        var _col0 = DoubleVector.fromArray(COL_SPECIES, vData, 0);
        var _col1 = DoubleVector.fromArray(COL_SPECIES, vData, 4);
        var _col2 = DoubleVector.fromArray(COL_SPECIES, vData, 8);
        _col0.intoArray(dd, 0);
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
        DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
        DoubleVector.fromArray(COL_SPECIES, DATA_0, 4).intoArray(dd, 4);
        DoubleVector.fromArray(COL_SPECIES, DATA_0, 8).intoArray(dd, 8);
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
        if (SimdMath.USE_FMA) return lerp_fma(other, t, dest);
        return lerp_mulAdd(other, t, dest);
    }

    private Double4x3 lerp_fma(Double4x3R other, double t, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x3Impl) other).data;
        double[] dd = ((Double4x3Impl) dest).data;
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, t);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _col0 = _sv0.fma(DoubleVector.fromArray(COL_SPECIES, otherData, 0).sub(_sv1), _sv1);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, sd, 4);
        var _col1 = _sv0.fma(DoubleVector.fromArray(COL_SPECIES, otherData, 4).sub(_sv2), _sv2);
        var _sv3 = DoubleVector.fromArray(COL_SPECIES, sd, 8);
        var _col2 = _sv0.fma(DoubleVector.fromArray(COL_SPECIES, otherData, 8).sub(_sv3), _sv3);
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
        return dest;
    }

    private Double4x3 lerp_mulAdd(Double4x3R other, double t, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x3Impl) other).data;
        double[] dd = ((Double4x3Impl) dest).data;
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, t);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _col0 = _sv0.mul(DoubleVector.fromArray(COL_SPECIES, otherData, 0).sub(_sv1)).add(_sv1);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, sd, 4);
        var _col1 = _sv0.mul(DoubleVector.fromArray(COL_SPECIES, otherData, 4).sub(_sv2)).add(_sv2);
        var _sv3 = DoubleVector.fromArray(COL_SPECIES, sd, 8);
        var _col2 = _sv0.mul(DoubleVector.fromArray(COL_SPECIES, otherData, 8).sub(_sv3)).add(_sv3);
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
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
        if (SimdMath.USE_FMA) return lerp_fma(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32, t, dest);
        return lerp_mulAdd(m00, m01, m02, m10, m11, m12, m20, m21, m22, m30, m31, m32, t, dest);
    }

    private Double4x3 lerp_fma(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, double m30, double m31, double m32, double t, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, t);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _col0 = _sv0.fma(DoubleVector.zero(COL_SPECIES).withLane(0, m00).withLane(1, m10).withLane(2, m20).withLane(3, m30).sub(_sv1), _sv1);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, sd, 4);
        var _col1 = _sv0.fma(DoubleVector.zero(COL_SPECIES).withLane(0, m01).withLane(1, m11).withLane(2, m21).withLane(3, m31).sub(_sv2), _sv2);
        var _sv3 = DoubleVector.fromArray(COL_SPECIES, sd, 8);
        var _col2 = _sv0.fma(DoubleVector.zero(COL_SPECIES).withLane(0, m02).withLane(1, m12).withLane(2, m22).withLane(3, m32).sub(_sv3), _sv3);
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
        return dest;
    }

    private Double4x3 lerp_mulAdd(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, double m30, double m31, double m32, double t, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x3Impl) dest).data;
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, t);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _col0 = _sv0.mul(DoubleVector.zero(COL_SPECIES).withLane(0, m00).withLane(1, m10).withLane(2, m20).withLane(3, m30).sub(_sv1)).add(_sv1);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, sd, 4);
        var _col1 = _sv0.mul(DoubleVector.zero(COL_SPECIES).withLane(0, m01).withLane(1, m11).withLane(2, m21).withLane(3, m31).sub(_sv2)).add(_sv2);
        var _sv3 = DoubleVector.fromArray(COL_SPECIES, sd, 8);
        var _col2 = _sv0.mul(DoubleVector.zero(COL_SPECIES).withLane(0, m02).withLane(1, m12).withLane(2, m22).withLane(3, m32).sub(_sv3)).add(_sv3);
        _col0.intoArray(dd, 0);
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
        if (SimdMath.USE_FMA) return mul_fma(right, dest);
        return mul_mulAdd(right, dest);
    }

    private Double4x3 mul_fma(Double3x3R right, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double4x3Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 8);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, sd, 4);
        var _col0 = DoubleVector.broadcast(COL_SPECIES, rightData[2]).fma(_sv0, DoubleVector.broadcast(COL_SPECIES, rightData[0]).fma(_sv1, DoubleVector.broadcast(COL_SPECIES, rightData[1]).mul(_sv2)));
        var _col1 = DoubleVector.broadcast(COL_SPECIES, rightData[5]).fma(_sv0, DoubleVector.broadcast(COL_SPECIES, rightData[3]).fma(_sv1, DoubleVector.broadcast(COL_SPECIES, rightData[4]).mul(_sv2)));
        var _col2 = DoubleVector.broadcast(COL_SPECIES, rightData[8]).fma(_sv0, DoubleVector.broadcast(COL_SPECIES, rightData[6]).fma(_sv1, DoubleVector.broadcast(COL_SPECIES, rightData[7]).mul(_sv2)));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
        return dest;
    }

    private Double4x3 mul_mulAdd(Double3x3R right, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double3x3Impl) right).data;
        double[] dd = ((Double4x3Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 8);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, sd, 4);
        var _col0 = DoubleVector.broadcast(COL_SPECIES, rightData[2]).mul(_sv0).add(DoubleVector.broadcast(COL_SPECIES, rightData[0]).mul(_sv1).add(DoubleVector.broadcast(COL_SPECIES, rightData[1]).mul(_sv2)));
        var _col1 = DoubleVector.broadcast(COL_SPECIES, rightData[5]).mul(_sv0).add(DoubleVector.broadcast(COL_SPECIES, rightData[3]).mul(_sv1).add(DoubleVector.broadcast(COL_SPECIES, rightData[4]).mul(_sv2)));
        var _col2 = DoubleVector.broadcast(COL_SPECIES, rightData[8]).mul(_sv0).add(DoubleVector.broadcast(COL_SPECIES, rightData[6]).mul(_sv1).add(DoubleVector.broadcast(COL_SPECIES, rightData[7]).mul(_sv2)));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
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
        if (SimdMath.USE_FMA) return preMul_fma(other, dest);
        return preMul_mulAdd(other, dest);
    }

    private Double4x3 preMul_fma(Double4x4R other, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x4Impl) other).data;
        double[] dd = ((Double4x3Impl) dest).data;
        double _r0 = sd[3];
        double _r1 = sd[2];
        double _r2 = sd[0];
        double _r3 = sd[1];
        double _r4 = sd[7];
        double _r5 = sd[6];
        double _r6 = sd[4];
        double _r7 = sd[5];
        double _r8 = sd[11];
        double _r9 = sd[10];
        double _r10 = sd[8];
        double _r11 = sd[9];
        preMul_s1d439ee6_tail(dd, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, otherData);
        return dest;
    }

    private Double4x3 preMul_mulAdd(Double4x4R other, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x4Impl) other).data;
        double[] dd = ((Double4x3Impl) dest).data;
        double _r0 = sd[3];
        double _r1 = sd[2];
        double _r2 = sd[0];
        double _r3 = sd[1];
        double _r4 = sd[7];
        double _r5 = sd[6];
        double _r6 = sd[4];
        double _r7 = sd[5];
        double _r8 = sd[11];
        double _r9 = sd[10];
        double _r10 = sd[8];
        double _r11 = sd[9];
        preMul_sc80d851_tail(dd, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, otherData);
        return dest;
    }

    /** Private vector tail of {@code preMul_s1d439ee6}: loads, computes and stores every column; reached only through it. */
    private static void preMul_s1d439ee6_tail(double[] dd, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double[] otherData) {
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, otherData, 12);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, otherData, 8);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, otherData, 0);
        var _sv3 = DoubleVector.fromArray(COL_SPECIES, otherData, 4);
        var _col0 = _sv0.fma(DoubleVector.broadcast(COL_SPECIES, _r0), _sv1.fma(DoubleVector.broadcast(COL_SPECIES, _r1), _sv2.fma(DoubleVector.broadcast(COL_SPECIES, _r2), _sv3.mul(DoubleVector.broadcast(COL_SPECIES, _r3)))));
        var _col1 = _sv0.fma(DoubleVector.broadcast(COL_SPECIES, _r4), _sv1.fma(DoubleVector.broadcast(COL_SPECIES, _r5), _sv2.fma(DoubleVector.broadcast(COL_SPECIES, _r6), _sv3.mul(DoubleVector.broadcast(COL_SPECIES, _r7)))));
        var _col2 = _sv0.fma(DoubleVector.broadcast(COL_SPECIES, _r8), _sv1.fma(DoubleVector.broadcast(COL_SPECIES, _r9), _sv2.fma(DoubleVector.broadcast(COL_SPECIES, _r10), _sv3.mul(DoubleVector.broadcast(COL_SPECIES, _r11)))));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
    }

    /** Private vector tail of {@code preMul_sc80d851}: loads, computes and stores every column; reached only through it. */
    private static void preMul_sc80d851_tail(double[] dd, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double[] otherData) {
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, otherData, 12);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, otherData, 8);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, otherData, 0);
        var _sv3 = DoubleVector.fromArray(COL_SPECIES, otherData, 4);
        var _col0 = _sv0.mul(DoubleVector.broadcast(COL_SPECIES, _r0)).add(_sv1.mul(DoubleVector.broadcast(COL_SPECIES, _r1)).add(_sv2.mul(DoubleVector.broadcast(COL_SPECIES, _r2)).add(_sv3.mul(DoubleVector.broadcast(COL_SPECIES, _r3)))));
        var _col1 = _sv0.mul(DoubleVector.broadcast(COL_SPECIES, _r4)).add(_sv1.mul(DoubleVector.broadcast(COL_SPECIES, _r5)).add(_sv2.mul(DoubleVector.broadcast(COL_SPECIES, _r6)).add(_sv3.mul(DoubleVector.broadcast(COL_SPECIES, _r7)))));
        var _col2 = _sv0.mul(DoubleVector.broadcast(COL_SPECIES, _r8)).add(_sv1.mul(DoubleVector.broadcast(COL_SPECIES, _r9)).add(_sv2.mul(DoubleVector.broadcast(COL_SPECIES, _r10)).add(_sv3.mul(DoubleVector.broadcast(COL_SPECIES, _r11)))));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
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
        if (SimdMath.USE_FMA) return addScaled_fma(other, weight, dest);
        return addScaled_mulAdd(other, weight, dest);
    }

    private Double4x3 addScaled_fma(Double4x3R other, double weight, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x3Impl) other).data;
        double[] dd = ((Double4x3Impl) dest).data;
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, weight);
        var _col0 = _sv0.fma(DoubleVector.fromArray(COL_SPECIES, otherData, 0), DoubleVector.fromArray(COL_SPECIES, sd, 0));
        var _col1 = _sv0.fma(DoubleVector.fromArray(COL_SPECIES, otherData, 4), DoubleVector.fromArray(COL_SPECIES, sd, 4));
        var _col2 = _sv0.fma(DoubleVector.fromArray(COL_SPECIES, otherData, 8), DoubleVector.fromArray(COL_SPECIES, sd, 8));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
        return dest;
    }

    private Double4x3 addScaled_mulAdd(Double4x3R other, double weight, @Mutated Double4x3 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x3Impl) other).data;
        double[] dd = ((Double4x3Impl) dest).data;
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, weight);
        var _col0 = _sv0.mul(DoubleVector.fromArray(COL_SPECIES, otherData, 0)).add(DoubleVector.fromArray(COL_SPECIES, sd, 0));
        var _col1 = _sv0.mul(DoubleVector.fromArray(COL_SPECIES, otherData, 4)).add(DoubleVector.fromArray(COL_SPECIES, sd, 4));
        var _col2 = _sv0.mul(DoubleVector.fromArray(COL_SPECIES, otherData, 8)).add(DoubleVector.fromArray(COL_SPECIES, sd, 8));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        _col2.intoArray(dd, 8);
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
        return mul(v.x(), v.y(), v.z(), dest);
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
        if (SimdMath.USE_FMA) return mul_fma(vX, vY, vZ, dest);
        return mul_mulAdd(vX, vY, vZ, dest);
    }

    private Double4 mul_fma(double vX, double vY, double vZ, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        var _col0 = DoubleVector.fromArray(COL_SPECIES, sd, 8).fma(DoubleVector.broadcast(COL_SPECIES, vZ), DoubleVector.fromArray(COL_SPECIES, sd, 0).fma(DoubleVector.broadcast(COL_SPECIES, vX), DoubleVector.fromArray(COL_SPECIES, sd, 4).mul(DoubleVector.broadcast(COL_SPECIES, vY))));
        _col0.intoArray(dd, 0);
        return dest;
    }

    private Double4 mul_mulAdd(double vX, double vY, double vZ, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        var _col0 = DoubleVector.fromArray(COL_SPECIES, sd, 8).mul(DoubleVector.broadcast(COL_SPECIES, vZ)).add(DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(COL_SPECIES, vX)).add(DoubleVector.fromArray(COL_SPECIES, sd, 4).mul(DoubleVector.broadcast(COL_SPECIES, vY))));
        _col0.intoArray(dd, 0);
        return dest;
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
        return Math.abs(data[0] - other.m00()) <= epsilon
            && Math.abs(data[4] - other.m01()) <= epsilon
            && Math.abs(data[8] - other.m02()) <= epsilon
            && Math.abs(data[1] - other.m10()) <= epsilon
            && Math.abs(data[5] - other.m11()) <= epsilon
            && Math.abs(data[9] - other.m12()) <= epsilon
            && Math.abs(data[2] - other.m20()) <= epsilon
            && Math.abs(data[6] - other.m21()) <= epsilon
            && Math.abs(data[10] - other.m22()) <= epsilon
            && Math.abs(data[3] - other.m30()) <= epsilon
            && Math.abs(data[7] - other.m31()) <= epsilon
            && Math.abs(data[11] - other.m32()) <= epsilon;
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        double[] d = this.data;
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoArray(dest, offset);
        DoubleVector.fromArray(COL_SPECIES, d, 4).intoArray(dest, offset + 4);
        DoubleVector.fromArray(COL_SPECIES, d, 8).intoArray(dest, offset + 8);
        return dest;
    }
    public @Mutated Double4x3 loadCM(double[] src, int offset) {
        double[] d = this.data;
        DoubleVector.fromArray(COL_SPECIES, src, offset).intoArray(d, 0);
        DoubleVector.fromArray(COL_SPECIES, src, offset + 4).intoArray(d, 4);
        DoubleVector.fromArray(COL_SPECIES, src, offset + 8).intoArray(d, 8);
        return this;
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf) {
        double[] d = this.data;
        if (buf.hasArray()) {
            double[] arr = buf.array();
            int off = buf.arrayOffset() + index;
            DoubleVector.fromArray(COL_SPECIES, d, 0).intoArray(arr, off);
            DoubleVector.fromArray(COL_SPECIES, d, 4).intoArray(arr, off + 4);
            DoubleVector.fromArray(COL_SPECIES, d, 8).intoArray(arr, off + 8);
        } else if (buf.order() != ByteOrder.nativeOrder() || buf.isReadOnly()) {
            return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
        } else {
            MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
            long baseOff = (long) index * 8;
            DoubleVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(seg, baseOff, ByteOrder.nativeOrder());
            DoubleVector.fromArray(COL_SPECIES, d, 4).intoMemorySegment(seg, baseOff + 32L, ByteOrder.nativeOrder());
            DoubleVector.fromArray(COL_SPECIES, d, 8).intoMemorySegment(seg, baseOff + 64L, ByteOrder.nativeOrder());
        }
        return buf;
    }
    @Mutated public Double4x3 loadCMAbsolute(int index, DoubleBuffer buf) {
        double[] d = this.data;
        if (buf.hasArray()) {
            double[] arr = buf.array();
            int off = buf.arrayOffset() + index;
            DoubleVector.fromArray(COL_SPECIES, arr, off).intoArray(d, 0);
            DoubleVector.fromArray(COL_SPECIES, arr, off + 4).intoArray(d, 4);
            DoubleVector.fromArray(COL_SPECIES, arr, off + 8).intoArray(d, 8);
        } else if (buf.order() != ByteOrder.nativeOrder()) {
            return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
        } else {
            MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
            long baseOff = (long) index * 8;
            DoubleVector.fromMemorySegment(COL_SPECIES, seg, baseOff, ByteOrder.nativeOrder()).intoArray(d, 0);
            DoubleVector.fromMemorySegment(COL_SPECIES, seg, baseOff + 32L, ByteOrder.nativeOrder()).intoArray(d, 4);
            DoubleVector.fromMemorySegment(COL_SPECIES, seg, baseOff + 64L, ByteOrder.nativeOrder()).intoArray(d, 8);
        }
        return this;
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf) {
        if (buf.order() != ByteOrder.nativeOrder() || buf.isReadOnly()) return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
        double[] d = this.data;
        MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(seg, index, ByteOrder.nativeOrder());
        DoubleVector.fromArray(COL_SPECIES, d, 4).intoMemorySegment(seg, index + 32L, ByteOrder.nativeOrder());
        DoubleVector.fromArray(COL_SPECIES, d, 8).intoMemorySegment(seg, index + 64L, ByteOrder.nativeOrder());
        return buf;
    }
    public Double4x3 loadCMAbsolute(int index, ByteBuffer buf) {
        if (buf.order() != ByteOrder.nativeOrder()) return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
        double[] d = this.data;
        MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
        DoubleVector.fromMemorySegment(COL_SPECIES, seg, index, ByteOrder.nativeOrder()).intoArray(d, 0);
        DoubleVector.fromMemorySegment(COL_SPECIES, seg, index + 32L, ByteOrder.nativeOrder()).intoArray(d, 4);
        DoubleVector.fromMemorySegment(COL_SPECIES, seg, index + 64L, ByteOrder.nativeOrder()).intoArray(d, 8);
        return this;
    }
    public Double4x3 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }
    @Mutated public Double4x3 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }
    public MemorySegment storeCM(long offset, MemorySegment dest) {
        double[] d = this.data;
        MemorySegment seg = dest;
        DoubleVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(seg, offset, ByteOrder.nativeOrder());
        DoubleVector.fromArray(COL_SPECIES, d, 4).intoMemorySegment(seg, offset + 32L, ByteOrder.nativeOrder());
        DoubleVector.fromArray(COL_SPECIES, d, 8).intoMemorySegment(seg, offset + 64L, ByteOrder.nativeOrder());
        return dest;
    }
    public Double4x3 loadCM(long offset, MemorySegment src) {
        double[] d = this.data;
        MemorySegment seg = src;
        DoubleVector.fromMemorySegment(COL_SPECIES, seg, offset, ByteOrder.nativeOrder()).intoArray(d, 0);
        DoubleVector.fromMemorySegment(COL_SPECIES, seg, offset + 32L, ByteOrder.nativeOrder()).intoArray(d, 4);
        DoubleVector.fromMemorySegment(COL_SPECIES, seg, offset + 64L, ByteOrder.nativeOrder()).intoArray(d, 8);
        return this;
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.data[0];
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
        this.data[0] = src[offset + 0];
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
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    @Mutated public Double4x3 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf);
    }
    public Double4x3 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf);
    }
    public Double4x3 storeCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address);
    }
    @Mutated public Double4x3 loadCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address);
    }
    public MemorySegment storeCMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest);
    }
    public Double4x3 loadCMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        if (dest == this.data) return storeRM_aliased(dest, offset);
        return storeRM_distinct(dest, offset);
    }
    private double[] storeRM_distinct(double[] dest, int offset) {
        dest[offset + 0] = this.data[0];
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
        double t0 = d[0];
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
        dest[offset + 0] = t0;
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
        this.data[0] = src[offset + 0];
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
        double t0 = src[offset + 0];
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
        d[0] = t0;
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
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    @Mutated public Double4x3 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public Double4x3 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public Double4x3 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }
    @Mutated public Double4x3 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }
    public MemorySegment storeRM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }
    public Double4x3 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.data[0];
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
        this.data[0] = src[offset + 0];
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
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    @Mutated public Double4x3 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf);
    }
    public Double4x3 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf);
    }
    public Double4x3 storeRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address);
    }
    @Mutated public Double4x3 loadRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address);
    }
    public MemorySegment storeRMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest);
    }
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
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double4x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public Double4x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public Double4x3 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }
    @Mutated public Double4x3 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }
    public MemorySegment storeCM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }
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
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double4x3 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf, stride);
    }
    public Double4x3 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf, stride);
    }
    public Double4x3 storeCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double4x3 loadCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address, stride);
    }
    public MemorySegment storeCMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest, stride);
    }
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
        double t0 = d[0];
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
        dest[offset] = t0;
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
        double t0 = src[offset];
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
        d[0] = t0;
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
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double4x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public Double4x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public Double4x3 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }
    @Mutated public Double4x3 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }
    public MemorySegment storeRM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }
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
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double4x3 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf, stride);
    }
    public Double4x3 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf, stride);
    }
    public Double4x3 storeRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double4x3 loadRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address, stride);
    }
    public MemorySegment storeRMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest, stride);
    }
    public Double4x3 loadRMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src, stride);
    }

    private static final VectorSpecies<Double> COL_SPECIES = DoubleVector.SPECIES_256;
    private static final double[] DATA_0 = new double[] {1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0};

}
