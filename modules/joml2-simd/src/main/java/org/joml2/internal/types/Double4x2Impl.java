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
 * Generated implementation of {@link Double4x2} backed by a {@code double[]} array, with Vector API
 * SIMD kernels where profitable.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Double4x2Impl implements Double4x2 {

    public double[] data;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double4x2SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double4x2SegOpsUnsafe()
                        : new Double4x2SegOpsMS();
        static final Double4x2BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double4x2BbOpsUnsafe()
                        : new Double4x2BbOpsApi();
        static final Double4x2RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double4x2RawOpsUnsafe()
                        : new Double4x2RawOpsApi();
    }

    public Double4x2Impl() {
        data = new double[8];
        data[0] = 1;
        data[5] = 1;
    }

    public Double4x2Impl(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31) {
        double[] dd = this.data = new double[8];
        dd[0] = m00;
        dd[1] = m10;
        dd[2] = m20;
        dd[3] = m30;
        dd[4] = m01;
        dd[5] = m11;
        dd[6] = m21;
        dd[7] = m31;
    }

    public Double4x2Impl(Double4x2R src) {
        Double4x2Impl s = (Double4x2Impl) src;
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
    public Double2 getRow(int row, @Mutated Double2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2Impl) dest).data;
        double _idxSw0;
        double _idxSw1;
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
     * Compute the Frobenius norm of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Frobenius norm of this matrix
     */
    public double frobeniusNorm() {
        double[] sd = this.data;
        return Math.sqrt(Math.fma(sd[0], sd[0], sd[4] * sd[4]) + Math.fma(sd[1], sd[1], sd[5] * sd[5]) + (Math.fma(sd[2], sd[2], sd[6] * sd[6]) + Math.fma(sd[3], sd[3], sd[7] * sd[7])));
    }


    /**
     * Transpose this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2x4 transpose(@Mutated Double2x4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double2x4Impl) dest).data;
        var _col0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _col1 = DoubleVector.fromArray(COL_SPECIES, sd, 4);
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
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
    public Double4x2 add(Double4x2R other, @Mutated Double4x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x2Impl) other).data;
        double[] dd = ((Double4x2Impl) dest).data;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _lanes = _sp.length();
        int _i = 0;
        for (; _i + _lanes <= 8; _i += _lanes) {
            DoubleVector.fromArray(_sp, otherData, _i).add(DoubleVector.fromArray(_sp, sd, _i)).intoArray(dd, _i);
        }
        for (; _i < 8; _i++) {
            dd[_i] = otherData[_i] + sd[_i];
        }
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
    public Double4x2 add(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31, @Mutated Double4x2 dest) {
        double[] sd = this.data;
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
    public Double4x2 mul(double scalar, @Mutated Double4x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x2Impl) dest).data;
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, scalar);
        var _col0 = _sv0.mul(DoubleVector.fromArray(COL_SPECIES, sd, 0));
        var _col1 = _sv0.mul(DoubleVector.fromArray(COL_SPECIES, sd, 4));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
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
    public Double4x2 negate(@Mutated Double4x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x2Impl) dest).data;
        var _col0 = DoubleVector.fromArray(COL_SPECIES, sd, 0).neg();
        var _col1 = DoubleVector.fromArray(COL_SPECIES, sd, 4).neg();
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
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
    public Double4x2 sub(Double4x2R other, @Mutated Double4x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x2Impl) other).data;
        double[] dd = ((Double4x2Impl) dest).data;
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _lanes = _sp.length();
        int _i = 0;
        for (; _i + _lanes <= 8; _i += _lanes) {
            DoubleVector.fromArray(_sp, sd, _i).sub(DoubleVector.fromArray(_sp, otherData, _i)).intoArray(dd, _i);
        }
        for (; _i < 8; _i++) {
            dd[_i] = sd[_i] - otherData[_i];
        }
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
    public Double4x2 sub(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31, @Mutated Double4x2 dest) {
        double[] sd = this.data;
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
    @Mutated public Double4x2 set(Double4x2R v) {
        double[] dd = this.data;
        double[] vData = ((Double4x2Impl) v).data;
        var _col0 = DoubleVector.fromArray(COL_SPECIES, vData, 0);
        var _col1 = DoubleVector.fromArray(COL_SPECIES, vData, 4);
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
    @Mutated public Double4x2 set(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31) {
        double[] dd = this.data;
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
     * Convert this matrix to {@code float} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4x2 toFloat(@Mutated Float4x2 dest) {
        double[] sd = this.data;
        float[] dd = ((Float4x2Impl) dest).data;
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
    @Mutated public Double4x2 makeIdentity() {
        double[] dd = this.data;
        if (DoubleVector.SPECIES_PREFERRED.length() >= 8) {
            DoubleVector.fromArray(DoubleVector.SPECIES_512, DATA_0, 0).intoArray(dd, 0);
        } else {
            DoubleVector.fromArray(COL_SPECIES, DATA_0, 0).intoArray(dd, 0);
            DoubleVector.fromArray(COL_SPECIES, DATA_0, 4).intoArray(dd, 4);
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
    public Double4x2 lerp(Double4x2R other, double t, @Mutated Double4x2 dest) {
        if (SimdMath.USE_FMA) return lerp_fma(other, t, dest);
        return lerp_mulAdd(other, t, dest);
    }

    private Double4x2 lerp_fma(Double4x2R other, double t, @Mutated Double4x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x2Impl) other).data;
        double[] dd = ((Double4x2Impl) dest).data;
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, t);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _col0 = _sv0.fma(DoubleVector.fromArray(COL_SPECIES, otherData, 0).sub(_sv1), _sv1);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, sd, 4);
        var _col1 = _sv0.fma(DoubleVector.fromArray(COL_SPECIES, otherData, 4).sub(_sv2), _sv2);
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        return dest;
    }

    private Double4x2 lerp_mulAdd(Double4x2R other, double t, @Mutated Double4x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x2Impl) other).data;
        double[] dd = ((Double4x2Impl) dest).data;
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, t);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _col0 = _sv0.mul(DoubleVector.fromArray(COL_SPECIES, otherData, 0).sub(_sv1)).add(_sv1);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, sd, 4);
        var _col1 = _sv0.mul(DoubleVector.fromArray(COL_SPECIES, otherData, 4).sub(_sv2)).add(_sv2);
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
    public Double4x2 lerp(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31, double t, @Mutated Double4x2 dest) {
        if (SimdMath.USE_FMA) return lerp_fma(m00, m01, m10, m11, m20, m21, m30, m31, t, dest);
        return lerp_mulAdd(m00, m01, m10, m11, m20, m21, m30, m31, t, dest);
    }

    private Double4x2 lerp_fma(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31, double t, @Mutated Double4x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x2Impl) dest).data;
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, t);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _col0 = _sv0.fma(DoubleVector.zero(COL_SPECIES).withLane(0, m00).withLane(1, m10).withLane(2, m20).withLane(3, m30).sub(_sv1), _sv1);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, sd, 4);
        var _col1 = _sv0.fma(DoubleVector.zero(COL_SPECIES).withLane(0, m01).withLane(1, m11).withLane(2, m21).withLane(3, m31).sub(_sv2), _sv2);
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        return dest;
    }

    private Double4x2 lerp_mulAdd(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31, double t, @Mutated Double4x2 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4x2Impl) dest).data;
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, t);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _col0 = _sv0.mul(DoubleVector.zero(COL_SPECIES).withLane(0, m00).withLane(1, m10).withLane(2, m20).withLane(3, m30).sub(_sv1)).add(_sv1);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, sd, 4);
        var _col1 = _sv0.mul(DoubleVector.zero(COL_SPECIES).withLane(0, m01).withLane(1, m11).withLane(2, m21).withLane(3, m31).sub(_sv2)).add(_sv2);
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
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 mul(Double2x2R right, @Mutated Double4x2 dest) {
        if (SimdMath.USE_FMA) return mul_fma(right, dest);
        return mul_mulAdd(right, dest);
    }

    private Double4x2 mul_fma(Double2x2R right, @Mutated Double4x2 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double4x2Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, sd, 4);
        var _col0 = DoubleVector.broadcast(COL_SPECIES, rightData[0]).fma(_sv0, DoubleVector.broadcast(COL_SPECIES, rightData[1]).mul(_sv1));
        var _col1 = DoubleVector.broadcast(COL_SPECIES, rightData[2]).fma(_sv0, DoubleVector.broadcast(COL_SPECIES, rightData[3]).mul(_sv1));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        return dest;
    }

    private Double4x2 mul_mulAdd(Double2x2R right, @Mutated Double4x2 dest) {
        double[] sd = this.data;
        double[] rightData = ((Double2x2Impl) right).data;
        double[] dd = ((Double4x2Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, sd, 0);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, sd, 4);
        var _col0 = DoubleVector.broadcast(COL_SPECIES, rightData[0]).mul(_sv0).add(DoubleVector.broadcast(COL_SPECIES, rightData[1]).mul(_sv1));
        var _col1 = DoubleVector.broadcast(COL_SPECIES, rightData[2]).mul(_sv0).add(DoubleVector.broadcast(COL_SPECIES, rightData[3]).mul(_sv1));
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
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 preMul(Double4x4R other, @Mutated Double4x2 dest) {
        if (SimdMath.USE_FMA) return preMul_fma(other, dest);
        return preMul_mulAdd(other, dest);
    }

    private Double4x2 preMul_fma(Double4x4R other, @Mutated Double4x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x4Impl) other).data;
        double[] dd = ((Double4x2Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, otherData, 12);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, otherData, 8);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, otherData, 0);
        var _sv3 = DoubleVector.fromArray(COL_SPECIES, otherData, 4);
        var _col0 = _sv0.fma(DoubleVector.broadcast(COL_SPECIES, sd[3]), _sv1.fma(DoubleVector.broadcast(COL_SPECIES, sd[2]), _sv2.fma(DoubleVector.broadcast(COL_SPECIES, sd[0]), _sv3.mul(DoubleVector.broadcast(COL_SPECIES, sd[1])))));
        var _col1 = _sv0.fma(DoubleVector.broadcast(COL_SPECIES, sd[7]), _sv1.fma(DoubleVector.broadcast(COL_SPECIES, sd[6]), _sv2.fma(DoubleVector.broadcast(COL_SPECIES, sd[4]), _sv3.mul(DoubleVector.broadcast(COL_SPECIES, sd[5])))));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        return dest;
    }

    private Double4x2 preMul_mulAdd(Double4x4R other, @Mutated Double4x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x4Impl) other).data;
        double[] dd = ((Double4x2Impl) dest).data;
        var _sv0 = DoubleVector.fromArray(COL_SPECIES, otherData, 12);
        var _sv1 = DoubleVector.fromArray(COL_SPECIES, otherData, 8);
        var _sv2 = DoubleVector.fromArray(COL_SPECIES, otherData, 0);
        var _sv3 = DoubleVector.fromArray(COL_SPECIES, otherData, 4);
        var _col0 = _sv0.mul(DoubleVector.broadcast(COL_SPECIES, sd[3])).add(_sv1.mul(DoubleVector.broadcast(COL_SPECIES, sd[2])).add(_sv2.mul(DoubleVector.broadcast(COL_SPECIES, sd[0])).add(_sv3.mul(DoubleVector.broadcast(COL_SPECIES, sd[1])))));
        var _col1 = _sv0.mul(DoubleVector.broadcast(COL_SPECIES, sd[7])).add(_sv1.mul(DoubleVector.broadcast(COL_SPECIES, sd[6])).add(_sv2.mul(DoubleVector.broadcast(COL_SPECIES, sd[4])).add(_sv3.mul(DoubleVector.broadcast(COL_SPECIES, sd[5])))));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
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
    public Double4x2 addScaled(Double4x2R other, double weight, @Mutated Double4x2 dest) {
        if (SimdMath.USE_FMA) return addScaled_fma(other, weight, dest);
        return addScaled_mulAdd(other, weight, dest);
    }

    private Double4x2 addScaled_fma(Double4x2R other, double weight, @Mutated Double4x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x2Impl) other).data;
        double[] dd = ((Double4x2Impl) dest).data;
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, weight);
        var _col0 = _sv0.fma(DoubleVector.fromArray(COL_SPECIES, otherData, 0), DoubleVector.fromArray(COL_SPECIES, sd, 0));
        var _col1 = _sv0.fma(DoubleVector.fromArray(COL_SPECIES, otherData, 4), DoubleVector.fromArray(COL_SPECIES, sd, 4));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        return dest;
    }

    private Double4x2 addScaled_mulAdd(Double4x2R other, double weight, @Mutated Double4x2 dest) {
        double[] sd = this.data;
        double[] otherData = ((Double4x2Impl) other).data;
        double[] dd = ((Double4x2Impl) dest).data;
        var _sv0 = DoubleVector.broadcast(COL_SPECIES, weight);
        var _col0 = _sv0.mul(DoubleVector.fromArray(COL_SPECIES, otherData, 0)).add(DoubleVector.fromArray(COL_SPECIES, sd, 0));
        var _col1 = _sv0.mul(DoubleVector.fromArray(COL_SPECIES, otherData, 4)).add(DoubleVector.fromArray(COL_SPECIES, sd, 4));
        _col0.intoArray(dd, 0);
        _col1.intoArray(dd, 4);
        return dest;
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
    public Double4x2 addScaled(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31, double weight, @Mutated Double4x2 dest) {
        double[] sd = this.data;
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
    public Double4 mul(Double2R v, @Mutated Double4 dest) {
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
    public Double4 mul(double vX, double vY, @Mutated Double4 dest) {
        if (SimdMath.USE_FMA) return mul_fma(vX, vY, dest);
        return mul_mulAdd(vX, vY, dest);
    }

    private Double4 mul_fma(double vX, double vY, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        var _col0 = DoubleVector.fromArray(COL_SPECIES, sd, 0).fma(DoubleVector.broadcast(COL_SPECIES, vX), DoubleVector.fromArray(COL_SPECIES, sd, 4).mul(DoubleVector.broadcast(COL_SPECIES, vY)));
        _col0.intoArray(dd, 0);
        return dest;
    }

    private Double4 mul_mulAdd(double vX, double vY, @Mutated Double4 dest) {
        double[] sd = this.data;
        double[] dd = ((Double4Impl) dest).data;
        var _col0 = DoubleVector.fromArray(COL_SPECIES, sd, 0).mul(DoubleVector.broadcast(COL_SPECIES, vX)).add(DoubleVector.fromArray(COL_SPECIES, sd, 4).mul(DoubleVector.broadcast(COL_SPECIES, vY)));
        _col0.intoArray(dd, 0);
        return dest;
    }

    public double m00() { return data[0]; }
    public double m01() { return data[4]; }
    public double m10() { return data[1]; }
    public double m11() { return data[5]; }
    public double m20() { return data[2]; }
    public double m21() { return data[6]; }
    public double m30() { return data[3]; }
    public double m31() { return data[7]; }

    @Override public String toString() {
        return "Double4x2(\n    " + m00() + ", " + m01() + "\n    " + m10() + ", " + m11() + "\n    " + m20() + ", " + m21() + "\n    " + m30() + ", " + m31() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double4x2Impl)) return false;
        Double4x2Impl o = (Double4x2Impl) obj;
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

    @Override public boolean equalsEpsilon(Double4x2R other, double epsilon) {
        return Math.abs(data[0] - other.m00()) <= epsilon
            && Math.abs(data[4] - other.m01()) <= epsilon
            && Math.abs(data[1] - other.m10()) <= epsilon
            && Math.abs(data[5] - other.m11()) <= epsilon
            && Math.abs(data[2] - other.m20()) <= epsilon
            && Math.abs(data[6] - other.m21()) <= epsilon
            && Math.abs(data[3] - other.m30()) <= epsilon
            && Math.abs(data[7] - other.m31()) <= epsilon;
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        double[] d = this.data;
        if (DoubleVector.SPECIES_PREFERRED.length() >= 8) {
            DoubleVector.fromArray(DoubleVector.SPECIES_512, d, 0).intoArray(dest, offset);
        } else {
            DoubleVector.fromArray(COL_SPECIES, d, 0).intoArray(dest, offset);
            DoubleVector.fromArray(COL_SPECIES, d, 4).intoArray(dest, offset + 4);
        }
        return dest;
    }
    public @Mutated Double4x2 loadCM(double[] src, int offset) {
        double[] d = this.data;
        if (DoubleVector.SPECIES_PREFERRED.length() >= 8) {
            DoubleVector.fromArray(DoubleVector.SPECIES_512, src, offset).intoArray(d, 0);
        } else {
            DoubleVector.fromArray(COL_SPECIES, src, offset).intoArray(d, 0);
            DoubleVector.fromArray(COL_SPECIES, src, offset + 4).intoArray(d, 4);
        }
        return this;
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf) {
        double[] d = this.data;
        if (buf.hasArray()) {
            double[] arr = buf.array();
            int off = buf.arrayOffset() + index;
            if (DoubleVector.SPECIES_PREFERRED.length() >= 8) {
                DoubleVector.fromArray(DoubleVector.SPECIES_512, d, 0).intoArray(arr, off);
            } else {
                DoubleVector.fromArray(COL_SPECIES, d, 0).intoArray(arr, off);
                DoubleVector.fromArray(COL_SPECIES, d, 4).intoArray(arr, off + 4);
            }
        } else if (buf.order() != ByteOrder.nativeOrder() || buf.isReadOnly()) {
            return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
        } else {
            MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
            long baseOff = (long) index * 8;
            if (DoubleVector.SPECIES_PREFERRED.length() >= 8) {
                DoubleVector.fromArray(DoubleVector.SPECIES_512, d, 0).intoMemorySegment(seg, baseOff, ByteOrder.nativeOrder());
            } else {
                DoubleVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(seg, baseOff, ByteOrder.nativeOrder());
                DoubleVector.fromArray(COL_SPECIES, d, 4).intoMemorySegment(seg, baseOff + 32L, ByteOrder.nativeOrder());
            }
        }
        return buf;
    }
    @Mutated public Double4x2 loadCMAbsolute(int index, DoubleBuffer buf) {
        double[] d = this.data;
        if (buf.hasArray()) {
            double[] arr = buf.array();
            int off = buf.arrayOffset() + index;
            if (DoubleVector.SPECIES_PREFERRED.length() >= 8) {
                DoubleVector.fromArray(DoubleVector.SPECIES_512, arr, off).intoArray(d, 0);
            } else {
                DoubleVector.fromArray(COL_SPECIES, arr, off).intoArray(d, 0);
                DoubleVector.fromArray(COL_SPECIES, arr, off + 4).intoArray(d, 4);
            }
        } else if (buf.order() != ByteOrder.nativeOrder()) {
            return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
        } else {
            MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
            long baseOff = (long) index * 8;
            if (DoubleVector.SPECIES_PREFERRED.length() >= 8) {
                DoubleVector.fromMemorySegment(DoubleVector.SPECIES_512, seg, baseOff, ByteOrder.nativeOrder()).intoArray(d, 0);
            } else {
                DoubleVector.fromMemorySegment(COL_SPECIES, seg, baseOff, ByteOrder.nativeOrder()).intoArray(d, 0);
                DoubleVector.fromMemorySegment(COL_SPECIES, seg, baseOff + 32L, ByteOrder.nativeOrder()).intoArray(d, 4);
            }
        }
        return this;
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf) {
        if (buf.order() != ByteOrder.nativeOrder() || buf.isReadOnly()) return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
        double[] d = this.data;
        MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
        if (DoubleVector.SPECIES_PREFERRED.length() >= 8) {
            DoubleVector.fromArray(DoubleVector.SPECIES_512, d, 0).intoMemorySegment(seg, index, ByteOrder.nativeOrder());
        } else {
            DoubleVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(seg, index, ByteOrder.nativeOrder());
            DoubleVector.fromArray(COL_SPECIES, d, 4).intoMemorySegment(seg, index + 32L, ByteOrder.nativeOrder());
        }
        return buf;
    }
    public Double4x2 loadCMAbsolute(int index, ByteBuffer buf) {
        if (buf.order() != ByteOrder.nativeOrder()) return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
        double[] d = this.data;
        MemorySegment seg = MemorySegment.ofBuffer(buf.duplicate().position(0));
        if (DoubleVector.SPECIES_PREFERRED.length() >= 8) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_512, seg, index, ByteOrder.nativeOrder()).intoArray(d, 0);
        } else {
            DoubleVector.fromMemorySegment(COL_SPECIES, seg, index, ByteOrder.nativeOrder()).intoArray(d, 0);
            DoubleVector.fromMemorySegment(COL_SPECIES, seg, index + 32L, ByteOrder.nativeOrder()).intoArray(d, 4);
        }
        return this;
    }
    public Double4x2 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }
    @Mutated public Double4x2 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }
    public MemorySegment storeCM(long offset, MemorySegment dest) {
        double[] d = this.data;
        MemorySegment seg = dest;
        if (DoubleVector.SPECIES_PREFERRED.length() >= 8) {
            DoubleVector.fromArray(DoubleVector.SPECIES_512, d, 0).intoMemorySegment(seg, offset, ByteOrder.nativeOrder());
        } else {
            DoubleVector.fromArray(COL_SPECIES, d, 0).intoMemorySegment(seg, offset, ByteOrder.nativeOrder());
            DoubleVector.fromArray(COL_SPECIES, d, 4).intoMemorySegment(seg, offset + 32L, ByteOrder.nativeOrder());
        }
        return dest;
    }
    public Double4x2 loadCM(long offset, MemorySegment src) {
        double[] d = this.data;
        MemorySegment seg = src;
        if (DoubleVector.SPECIES_PREFERRED.length() >= 8) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_512, seg, offset, ByteOrder.nativeOrder()).intoArray(d, 0);
        } else {
            DoubleVector.fromMemorySegment(COL_SPECIES, seg, offset, ByteOrder.nativeOrder()).intoArray(d, 0);
            DoubleVector.fromMemorySegment(COL_SPECIES, seg, offset + 32L, ByteOrder.nativeOrder()).intoArray(d, 4);
        }
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
        return dest;
    }
    public @Mutated Double4x2 loadCM(float[] src, int offset) {
        this.data[0] = src[offset + 0];
        this.data[1] = src[offset + 1];
        this.data[2] = src[offset + 2];
        this.data[3] = src[offset + 3];
        this.data[4] = src[offset + 4];
        this.data[5] = src[offset + 5];
        this.data[6] = src[offset + 6];
        this.data[7] = src[offset + 7];
        return this;
    }
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    @Mutated public Double4x2 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf);
    }
    public Double4x2 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf);
    }
    public Double4x2 storeCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address);
    }
    @Mutated public Double4x2 loadCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address);
    }
    public MemorySegment storeCMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest);
    }
    public Double4x2 loadCMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        if (dest == this.data) return storeRM_aliased(dest, offset);
        return storeRM_distinct(dest, offset);
    }
    private double[] storeRM_distinct(double[] dest, int offset) {
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
    @Mutated public Double4x2 loadRM(double[] src, int offset) {
        if (src == this.data) return loadRM_aliased(src, offset);
        return loadRM_distinct(src, offset);
    }
    private Double4x2 loadRM_distinct(double[] src, int offset) {
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
    private Double4x2 loadRM_aliased(double[] src, int offset) {
        double t0 = src[offset + 0];
        double t1 = src[offset + 1];
        double t2 = src[offset + 2];
        double t3 = src[offset + 3];
        double t4 = src[offset + 4];
        double t5 = src[offset + 5];
        double t6 = src[offset + 6];
        double t7 = src[offset + 7];
        double[] d = this.data;
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
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    @Mutated public Double4x2 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public Double4x2 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public Double4x2 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }
    @Mutated public Double4x2 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }
    public MemorySegment storeRM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }
    public Double4x2 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.data[0];
        dest[offset + 1] = (float) this.data[4];
        dest[offset + 2] = (float) this.data[1];
        dest[offset + 3] = (float) this.data[5];
        dest[offset + 4] = (float) this.data[2];
        dest[offset + 5] = (float) this.data[6];
        dest[offset + 6] = (float) this.data[3];
        dest[offset + 7] = (float) this.data[7];
        return dest;
    }
    public @Mutated Double4x2 loadRM(float[] src, int offset) {
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
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    @Mutated public Double4x2 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf);
    }
    public Double4x2 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf);
    }
    public Double4x2 storeRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address);
    }
    @Mutated public Double4x2 loadRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address);
    }
    public MemorySegment storeRMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest);
    }
    public Double4x2 loadRMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src);
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
    public @Mutated Double4x2 loadCM(double[] src, int offset, int stride) {
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
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double4x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public Double4x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public Double4x2 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }
    @Mutated public Double4x2 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }
    public MemorySegment storeCM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }
    public Double4x2 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src, stride);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
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
    public @Mutated Double4x2 loadCM(float[] src, int offset, int stride) {
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
    @Mutated public Double4x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf, stride);
    }
    public Double4x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf, stride);
    }
    public Double4x2 storeCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double4x2 loadCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address, stride);
    }
    public MemorySegment storeCMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest, stride);
    }
    public Double4x2 loadCMFloat(long offset, MemorySegment src, int stride) {
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
        dest[_p1] = this.data[1];
        dest[_p1 + 1] = this.data[5];
        dest[_p2] = this.data[2];
        dest[_p2 + 1] = this.data[6];
        dest[_p3] = this.data[3];
        dest[_p3 + 1] = this.data[7];
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
    @Mutated public Double4x2 loadRM(double[] src, int offset, int stride) {
        if (src == this.data) return loadRM_aliased(src, offset, stride);
        return loadRM_distinct(src, offset, stride);
    }
    private Double4x2 loadRM_distinct(double[] src, int offset, int stride) {
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
    private Double4x2 loadRM_aliased(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        double t0 = src[offset];
        double t1 = src[offset + 1];
        double t2 = src[_p1];
        double t3 = src[_p1 + 1];
        double t4 = src[_p2];
        double t5 = src[_p2 + 1];
        double t6 = src[_p3];
        double t7 = src[_p3 + 1];
        double[] d = this.data;
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
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double4x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public Double4x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public Double4x2 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }
    @Mutated public Double4x2 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }
    public MemorySegment storeRM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }
    public Double4x2 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
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
    public @Mutated Double4x2 loadRM(float[] src, int offset, int stride) {
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
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double4x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf, stride);
    }
    public Double4x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf, stride);
    }
    public Double4x2 storeRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double4x2 loadRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address, stride);
    }
    public MemorySegment storeRMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest, stride);
    }
    public Double4x2 loadRMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src, stride);
    }

    private static final VectorSpecies<Double> COL_SPECIES = DoubleVector.SPECIES_256;
    private static final double[] DATA_0 = new double[] {1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0};

}
