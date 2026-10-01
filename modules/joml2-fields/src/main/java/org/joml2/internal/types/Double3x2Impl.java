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
 * Generated implementation of {@link Double3x2} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Double3x2Impl implements Double3x2 {

    public double m00;
    public double m10;
    public double m20;
    public double m01;
    public double m11;
    public double m21;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double3x2SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double3x2SegOpsUnsafe()
                        : new Double3x2SegOpsMS();
        static final Double3x2BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double3x2BbOpsUnsafe()
                        : new Double3x2BbOpsApi();
        static final Double3x2RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double3x2RawOpsUnsafe()
                        : new Double3x2RawOpsApi();
    }

    public Double3x2Impl() {
        m00 = 1;
        m11 = 1;
    }

    public Double3x2Impl(double m00, double m01, double m10, double m11, double m20, double m21) {
        this.m00 = m00;
        this.m10 = m10;
        this.m20 = m20;
        this.m01 = m01;
        this.m11 = m11;
        this.m21 = m21;
    }

    public Double3x2Impl(Double3x2R src) {
        this.m00 = src.m00();
        this.m10 = src.m10();
        this.m20 = src.m20();
        this.m01 = src.m01();
        this.m11 = src.m11();
        this.m21 = src.m21();
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
    public Double3 getColumn(int col, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (col) {
            case 0: _idxSw0 = this.m00; _idxSw1 = this.m10; _idxSw2 = this.m20; break;
            case 1: _idxSw0 = this.m01; _idxSw1 = this.m11; _idxSw2 = this.m21; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw0;
        d.y = _idxSw1;
        d.z = _idxSw2;
        return d;
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
        Double2Impl d = (Double2Impl) dest;
        double _idxSw0;
        double _idxSw1;
        switch (row) {
            case 0: _idxSw0 = this.m00; _idxSw1 = this.m01; break;
            case 1: _idxSw0 = this.m10; _idxSw1 = this.m11; break;
            case 2: _idxSw0 = this.m20; _idxSw1 = this.m21; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw0;
        d.y = _idxSw1;
        return d;
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
            return java.lang.Math.sqrt(java.lang.Math.fma(this.m21, this.m21, java.lang.Math.fma(this.m20, this.m20, java.lang.Math.fma(this.m11, this.m11, java.lang.Math.fma(this.m10, this.m10, java.lang.Math.fma(this.m00, this.m00, this.m01 * this.m01))))));
        } else {
            return java.lang.Math.sqrt(((this.m21) * (this.m21) + (((this.m20) * (this.m20) + (((this.m11) * (this.m11) + (((this.m10) * (this.m10) + (((this.m00) * (this.m00) + (this.m01 * this.m01)))))))))));
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
    public Double2x3 transpose(@Mutated Double2x3 dest) {
        Double2x3Impl d = (Double2x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m01;
        d.m01 = this.m10;
        d.m11 = this.m11;
        d.m02 = this.m20;
        d.m12 = this.m21;
        d.properties = Joml.BIT_AFFINE;
        return d;
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
    public Double3x2 add(Double3x2R other, @Mutated Double3x2 dest) {
        Double3x2Impl d = (Double3x2Impl) dest;
        d.m00 = other.m00() + this.m00;
        d.m10 = other.m10() + this.m10;
        d.m20 = other.m20() + this.m20;
        d.m01 = other.m01() + this.m01;
        d.m11 = other.m11() + this.m11;
        d.m21 = other.m21() + this.m21;
        return d;
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21}) to this
     * matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Double3x2 add(double m00, double m01, double m10, double m11, double m20, double m21, @Mutated Double3x2 dest) {
        Double3x2Impl d = (Double3x2Impl) dest;
        d.m00 = m00 + this.m00;
        d.m10 = m10 + this.m10;
        d.m20 = m20 + this.m20;
        d.m01 = m01 + this.m01;
        d.m11 = m11 + this.m11;
        d.m21 = m21 + this.m21;
        return d;
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
    public Double3x2 mul(double scalar, @Mutated Double3x2 dest) {
        Double3x2Impl d = (Double3x2Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = scalar * this.m10;
        d.m20 = scalar * this.m20;
        d.m01 = scalar * this.m01;
        d.m11 = scalar * this.m11;
        d.m21 = scalar * this.m21;
        return d;
    }


    /**
     * Negate this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double3x2 negate(@Mutated Double3x2 dest) {
        Double3x2Impl d = (Double3x2Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m20 = -this.m20;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.m21 = -this.m21;
        return d;
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
    public Double3x2 sub(Double3x2R other, @Mutated Double3x2 dest) {
        Double3x2Impl d = (Double3x2Impl) dest;
        d.m00 = this.m00 - other.m00();
        d.m10 = this.m10 - other.m10();
        d.m20 = this.m20 - other.m20();
        d.m01 = this.m01 - other.m01();
        d.m11 = this.m11 - other.m11();
        d.m21 = this.m21 - other.m21();
        return d;
    }


    /**
     * Subtract ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21}) from
     * this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param dest will hold the result
     * @return dest
     */
    public Double3x2 sub(double m00, double m01, double m10, double m11, double m20, double m21, @Mutated Double3x2 dest) {
        Double3x2Impl d = (Double3x2Impl) dest;
        d.m00 = this.m00 - m00;
        d.m10 = this.m10 - m10;
        d.m20 = this.m20 - m20;
        d.m01 = this.m01 - m01;
        d.m11 = this.m11 - m11;
        d.m21 = this.m21 - m21;
        return d;
    }


    /**
     * Set this matrix to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the matrix to copy
     * @return this
     */
    @Mutated public Double3x2 set(Double3x2R v) {
        this.m00 = v.m00();
        this.m10 = v.m10();
        this.m20 = v.m20();
        this.m01 = v.m01();
        this.m11 = v.m11();
        this.m21 = v.m21();
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
     * @return this
     */
    @Mutated public Double3x2 set(double m00, double m01, double m10, double m11, double m20, double m21) {
        this.m00 = m00;
        this.m10 = m10;
        this.m20 = m20;
        this.m01 = m01;
        this.m11 = m11;
        this.m21 = m21;
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
    public Float3x2 toFloat(@Mutated Float3x2 dest) {
        Float3x2Impl d = (Float3x2Impl) dest;
        d.m00 = (float) (this.m00);
        d.m10 = (float) (this.m10);
        d.m20 = (float) (this.m20);
        d.m01 = (float) (this.m01);
        d.m11 = (float) (this.m11);
        d.m21 = (float) (this.m21);
        return d;
    }


    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Double3x2 makeIdentity() {
        this.m00 = 1.0;
        this.m10 = 0.0;
        this.m20 = 0.0;
        this.m01 = 0.0;
        this.m11 = 1.0;
        this.m21 = 0.0;
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
    public Double3x2 lerp(Double3x2R other, double t, @Mutated Double3x2 dest) {
        if (Math.useFma()) {
            Double3x2Impl d = (Double3x2Impl) dest;
            d.m00 = java.lang.Math.fma(t, other.m00() - this.m00, this.m00);
            d.m10 = java.lang.Math.fma(t, other.m10() - this.m10, this.m10);
            d.m20 = java.lang.Math.fma(t, other.m20() - this.m20, this.m20);
            d.m01 = java.lang.Math.fma(t, other.m01() - this.m01, this.m01);
            d.m11 = java.lang.Math.fma(t, other.m11() - this.m11, this.m11);
            d.m21 = java.lang.Math.fma(t, other.m21() - this.m21, this.m21);
            return d;
        } else {
            Double3x2Impl d = (Double3x2Impl) dest;
            d.m00 = ((t) * (other.m00() - this.m00) + (this.m00));
            d.m10 = ((t) * (other.m10() - this.m10) + (this.m10));
            d.m20 = ((t) * (other.m20() - this.m20) + (this.m20));
            d.m01 = ((t) * (other.m01() - this.m01) + (this.m01));
            d.m11 = ((t) * (other.m11() - this.m11) + (this.m11));
            d.m21 = ((t) * (other.m21() - this.m21) + (this.m21));
            return d;
        }
    }


    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m10},
     * {@code m11}, {@code m20}, {@code m21}) using the interpolation factor {@code t} and store the
     * result in {@code dest}.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21}) (interpolation
     * factor {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t},
     * as in JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact
     * only up to the rounding of {@code other - this}, which shows when this component is much
     * larger in magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double3x2 lerp(double m00, double m01, double m10, double m11, double m20, double m21, double t, @Mutated Double3x2 dest) {
        if (Math.useFma()) {
            Double3x2Impl d = (Double3x2Impl) dest;
            d.m00 = java.lang.Math.fma(t, m00 - this.m00, this.m00);
            d.m10 = java.lang.Math.fma(t, m10 - this.m10, this.m10);
            d.m20 = java.lang.Math.fma(t, m20 - this.m20, this.m20);
            d.m01 = java.lang.Math.fma(t, m01 - this.m01, this.m01);
            d.m11 = java.lang.Math.fma(t, m11 - this.m11, this.m11);
            d.m21 = java.lang.Math.fma(t, m21 - this.m21, this.m21);
            return d;
        } else {
            Double3x2Impl d = (Double3x2Impl) dest;
            d.m00 = ((t) * (m00 - this.m00) + (this.m00));
            d.m10 = ((t) * (m10 - this.m10) + (this.m10));
            d.m20 = ((t) * (m20 - this.m20) + (this.m20));
            d.m01 = ((t) * (m01 - this.m01) + (this.m01));
            d.m11 = ((t) * (m11 - this.m11) + (this.m11));
            d.m21 = ((t) * (m21 - this.m21) + (this.m21));
            return d;
        }
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
    public Double3x2 mul(Double2x2R right, @Mutated Double3x2 dest) {
        Double3x2Impl d = (Double3x2Impl) dest;
        double _rd0 = this.m00;
        double _rd1 = this.m10;
        double _rd2 = this.m20;
        d.m00 = Math.fma(right.m00(), _rd0, right.m10() * this.m01);
        d.m10 = Math.fma(right.m00(), _rd1, right.m10() * this.m11);
        d.m20 = Math.fma(right.m00(), _rd2, right.m10() * this.m21);
        d.m01 = Math.fma(right.m01(), _rd0, right.m11() * this.m01);
        d.m11 = Math.fma(right.m01(), _rd1, right.m11() * this.m11);
        d.m21 = Math.fma(right.m01(), _rd2, right.m11() * this.m21);
        return d;
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
    public Double3x2 preMul(Double3x3R other, @Mutated Double3x2 dest) {
        Double3x2Impl d = (Double3x2Impl) dest;
        double _rd0 = this.m00;
        double _rd1 = this.m10;
        double _rd2 = this.m01;
        double _rd3 = this.m11;
        d.m00 = Math.fma(other.m02(), this.m20, Math.fma(other.m00(), _rd0, other.m01() * _rd1));
        d.m10 = Math.fma(other.m12(), this.m20, Math.fma(other.m10(), _rd0, other.m11() * _rd1));
        d.m20 = Math.fma(other.m22(), this.m20, Math.fma(other.m20(), _rd0, other.m21() * _rd1));
        d.m01 = Math.fma(other.m02(), this.m21, Math.fma(other.m00(), _rd2, other.m01() * _rd3));
        d.m11 = Math.fma(other.m12(), this.m21, Math.fma(other.m10(), _rd2, other.m11() * _rd3));
        d.m21 = Math.fma(other.m22(), this.m21, Math.fma(other.m20(), _rd2, other.m21() * _rd3));
        return d;
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
    public Double3x2 addScaled(Double3x2R other, double weight, @Mutated Double3x2 dest) {
        if (Math.useFma()) {
            Double3x2Impl d = (Double3x2Impl) dest;
            d.m00 = java.lang.Math.fma(weight, other.m00(), this.m00);
            d.m10 = java.lang.Math.fma(weight, other.m10(), this.m10);
            d.m20 = java.lang.Math.fma(weight, other.m20(), this.m20);
            d.m01 = java.lang.Math.fma(weight, other.m01(), this.m01);
            d.m11 = java.lang.Math.fma(weight, other.m11(), this.m11);
            d.m21 = java.lang.Math.fma(weight, other.m21(), this.m21);
            return d;
        } else {
            Double3x2Impl d = (Double3x2Impl) dest;
            d.m00 = ((weight) * (other.m00()) + (this.m00));
            d.m10 = ((weight) * (other.m10()) + (this.m10));
            d.m20 = ((weight) * (other.m20()) + (this.m20));
            d.m01 = ((weight) * (other.m01()) + (this.m01));
            d.m11 = ((weight) * (other.m11()) + (this.m11));
            d.m21 = ((weight) * (other.m21()) + (this.m21));
            return d;
        }
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m10}, {@code m11}, {@code m20}, {@code m21}) scaled by
     * {@code weight} to this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m10}, {@code m11},
     *        {@code m20}, {@code m21}) by before adding
     * @param dest will hold the result
     * @return dest
     */
    public Double3x2 addScaled(double m00, double m01, double m10, double m11, double m20, double m21, double weight, @Mutated Double3x2 dest) {
        if (Math.useFma()) {
            Double3x2Impl d = (Double3x2Impl) dest;
            d.m00 = java.lang.Math.fma(weight, m00, this.m00);
            d.m10 = java.lang.Math.fma(weight, m10, this.m10);
            d.m20 = java.lang.Math.fma(weight, m20, this.m20);
            d.m01 = java.lang.Math.fma(weight, m01, this.m01);
            d.m11 = java.lang.Math.fma(weight, m11, this.m11);
            d.m21 = java.lang.Math.fma(weight, m21, this.m21);
            return d;
        } else {
            Double3x2Impl d = (Double3x2Impl) dest;
            d.m00 = ((weight) * (m00) + (this.m00));
            d.m10 = ((weight) * (m10) + (this.m10));
            d.m20 = ((weight) * (m20) + (this.m20));
            d.m01 = ((weight) * (m01) + (this.m01));
            d.m11 = ((weight) * (m11) + (this.m11));
            d.m21 = ((weight) * (m21) + (this.m21));
            return d;
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
    public Double3 mul(Double2R v, @Mutated Double3 dest) {
        double vX = v.x();
        double vY = v.y();
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(this.m00, vX, this.m01 * vY);
            d.y = java.lang.Math.fma(this.m10, vX, this.m11 * vY);
            d.z = java.lang.Math.fma(this.m20, vX, this.m21 * vY);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((this.m00) * (vX) + (this.m01 * vY));
            d.y = ((this.m10) * (vX) + (this.m11 * vY));
            d.z = ((this.m20) * (vX) + (this.m21 * vY));
            return d;
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
    public Double3 mul(double vX, double vY, @Mutated Double3 dest) {
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(this.m00, vX, this.m01 * vY);
            d.y = java.lang.Math.fma(this.m10, vX, this.m11 * vY);
            d.z = java.lang.Math.fma(this.m20, vX, this.m21 * vY);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((this.m00) * (vX) + (this.m01 * vY));
            d.y = ((this.m10) * (vX) + (this.m11 * vY));
            d.z = ((this.m20) * (vX) + (this.m21 * vY));
            return d;
        }
    }

    public double m00() { return this.m00; }
    public double m01() { return this.m01; }
    public double m10() { return this.m10; }
    public double m11() { return this.m11; }
    public double m20() { return this.m20; }
    public double m21() { return this.m21; }

    @Override public String toString() {
        return "Double3x2(\n    " + m00() + ", " + m01() + "\n    " + m10() + ", " + m11() + "\n    " + m20() + ", " + m21() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double3x2Impl)) return false;
        Double3x2Impl o = (Double3x2Impl) obj;
        return Double.doubleToLongBits(m00) == Double.doubleToLongBits(o.m00)
            && Double.doubleToLongBits(m01) == Double.doubleToLongBits(o.m01)
            && Double.doubleToLongBits(m10) == Double.doubleToLongBits(o.m10)
            && Double.doubleToLongBits(m11) == Double.doubleToLongBits(o.m11)
            && Double.doubleToLongBits(m20) == Double.doubleToLongBits(o.m20)
            && Double.doubleToLongBits(m21) == Double.doubleToLongBits(o.m21);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(Double.doubleToLongBits(m00) ^ (Double.doubleToLongBits(m00) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m01) ^ (Double.doubleToLongBits(m01) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m10) ^ (Double.doubleToLongBits(m10) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m11) ^ (Double.doubleToLongBits(m11) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m20) ^ (Double.doubleToLongBits(m20) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m21) ^ (Double.doubleToLongBits(m21) >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return Double.isFinite(m00)
            && Double.isFinite(m01)
            && Double.isFinite(m10)
            && Double.isFinite(m11)
            && Double.isFinite(m20)
            && Double.isFinite(m21);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(m00)
            || Double.isNaN(m01)
            || Double.isNaN(m10)
            || Double.isNaN(m11)
            || Double.isNaN(m20)
            || Double.isNaN(m21);
    }

    @Override public boolean equalsEpsilon(Double3x2R other, double epsilon) {
        return java.lang.Math.abs(m00 - other.m00()) <= epsilon
            && java.lang.Math.abs(m01 - other.m01()) <= epsilon
            && java.lang.Math.abs(m10 - other.m10()) <= epsilon
            && java.lang.Math.abs(m11 - other.m11()) <= epsilon
            && java.lang.Math.abs(m20 - other.m20()) <= epsilon
            && java.lang.Math.abs(m21 - other.m21()) <= epsilon;
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[offset + 3] = this.m01;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = this.m21;
        return dest;
    }
    public @Mutated Double3x2 loadCM(double[] src, int offset) {
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m20 = src[offset + 2];
        this.m01 = src[offset + 3];
        this.m11 = src[offset + 4];
        this.m21 = src[offset + 5];
        return this;
    }
    public DoubleBuffer storeCM(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    public DoubleBuffer storeCMRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return buf;
    }
    @Mutated public Double3x2 loadCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double3x2 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    @Mutated public Double3x2 loadCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 6);
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
    public Double3x2 loadCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    public Double3x2 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public Double3x2 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x2 r = StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return r;
    }
    public Double3x2 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }
    @Mutated public Double3x2 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }
    public MemorySegment storeCM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest); }
    public MemorySegment storeCM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest);
    }
    @Mutated public Double3x2 loadCM(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src); }
    public Double3x2 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src);
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) this.m20;
        dest[offset + 3] = (float) this.m01;
        dest[offset + 4] = (float) this.m11;
        dest[offset + 5] = (float) this.m21;
        return dest;
    }
    public @Mutated Double3x2 loadCM(float[] src, int offset) {
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m20 = src[offset + 2];
        this.m01 = src[offset + 3];
        this.m11 = src[offset + 4];
        this.m21 = src[offset + 5];
        return this;
    }
    public FloatBuffer storeCM(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    public FloatBuffer storeCMRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return buf;
    }
    @Mutated public Double3x2 loadCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double3x2 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    @Mutated public Double3x2 loadCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return this;
    }
    public ByteBuffer storeCMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }
    public Double3x2 loadCMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, buf.position(), buf);
    }
    public Double3x2 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf);
    }
    public Double3x2 loadCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x2 r = StoreLoad.BB_OPS.loadCMFloatAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return r;
    }
    public Double3x2 storeCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address);
    }
    @Mutated public Double3x2 loadCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address);
    }
    public MemorySegment storeCMFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCMFloat(this, 0L, dest); }
    public MemorySegment storeCMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest);
    }
    @Mutated public Double3x2 loadCMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMFloat(this, 0L, src); }
    public Double3x2 loadCMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m10;
        dest[offset + 3] = this.m11;
        dest[offset + 4] = this.m20;
        dest[offset + 5] = this.m21;
        return dest;
    }
    public @Mutated Double3x2 loadRM(double[] src, int offset) {
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m10 = src[offset + 2];
        this.m11 = src[offset + 3];
        this.m20 = src[offset + 4];
        this.m21 = src[offset + 5];
        return this;
    }
    public DoubleBuffer storeRM(@Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public DoubleBuffer storeRMRelative(@Mutated DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return buf;
    }
    @Mutated public Double3x2 loadRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double3x2 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    @Mutated public Double3x2 loadRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 6);
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
    public Double3x2 loadRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    public Double3x2 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public Double3x2 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x2 r = StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 48);
        return r;
    }
    public Double3x2 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }
    @Mutated public Double3x2 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }
    public MemorySegment storeRM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest); }
    public MemorySegment storeRM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }
    @Mutated public Double3x2 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src); }
    public Double3x2 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) this.m10;
        dest[offset + 3] = (float) this.m11;
        dest[offset + 4] = (float) this.m20;
        dest[offset + 5] = (float) this.m21;
        return dest;
    }
    public @Mutated Double3x2 loadRM(float[] src, int offset) {
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m10 = src[offset + 2];
        this.m11 = src[offset + 3];
        this.m20 = src[offset + 4];
        this.m21 = src[offset + 5];
        return this;
    }
    public FloatBuffer storeRM(@Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public FloatBuffer storeRMRelative(@Mutated FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return buf;
    }
    @Mutated public Double3x2 loadRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }
    @Mutated public Double3x2 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    @Mutated public Double3x2 loadRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 6);
        return this;
    }
    public ByteBuffer storeRMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return buf;
    }
    public Double3x2 loadRMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, buf.position(), buf);
    }
    public Double3x2 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf);
    }
    public Double3x2 loadRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x2 r = StoreLoad.BB_OPS.loadRMFloatAbsolute(this, pos, buf);
        buf.position(pos + 24);
        return r;
    }
    public Double3x2 storeRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address);
    }
    @Mutated public Double3x2 loadRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address);
    }
    public MemorySegment storeRMFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRMFloat(this, 0L, dest); }
    public MemorySegment storeRMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest);
    }
    @Mutated public Double3x2 loadRMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMFloat(this, 0L, src); }
    public Double3x2 loadRMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src);
    }

    public double[] storeCM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m21;
        return dest;
    }
    public @Mutated Double3x2 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m20 = src[offset + 2];
        this.m01 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m21 = src[_p1 + 2];
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
    @Mutated public Double3x2 loadCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Double3x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double3x2 loadCMRelative(DoubleBuffer buf, int stride) {
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
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 8);
        return buf;
    }
    public Double3x2 loadCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    public Double3x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public Double3x2 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x2 r = StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 8);
        return r;
    }
    public Double3x2 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }
    @Mutated public Double3x2 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }
    public MemorySegment storeCM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest, stride); }
    public MemorySegment storeCM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }
    @Mutated public Double3x2 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src, stride); }
    public Double3x2 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src, stride);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) this.m20;
        dest[_p1] = (float) this.m01;
        dest[_p1 + 1] = (float) this.m11;
        dest[_p1 + 2] = (float) this.m21;
        return dest;
    }
    public @Mutated Double3x2 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m20 = src[offset + 2];
        this.m01 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m21 = src[_p1 + 2];
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
    @Mutated public Double3x2 loadCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Double3x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double3x2 loadCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return this;
    }
    public ByteBuffer storeCMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 4);
        return buf;
    }
    public Double3x2 loadCMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, buf.position(), buf, stride);
    }
    public Double3x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf, stride);
    }
    public Double3x2 loadCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x2 r = StoreLoad.BB_OPS.loadCMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (2 * stride) * 4);
        return r;
    }
    public Double3x2 storeCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double3x2 loadCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address, stride);
    }
    public MemorySegment storeCMFloat(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCMFloat(this, 0L, dest, stride); }
    public MemorySegment storeCMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest, stride);
    }
    @Mutated public Double3x2 loadCMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMFloat(this, 0L, src, stride); }
    public Double3x2 loadCMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src, stride);
    }

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        dest[_p2] = this.m20;
        dest[_p2 + 1] = this.m21;
        return dest;
    }
    public @Mutated Double3x2 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m10 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m20 = src[_p2];
        this.m21 = src[_p2 + 1];
        return this;
    }
    public DoubleBuffer storeRM(@Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public DoubleBuffer storeRMRelative(@Mutated DoubleBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
        return buf;
    }
    @Mutated public Double3x2 loadRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Double3x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double3x2 loadRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
        return this;
    }
    public ByteBuffer storeRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
        return buf;
    }
    public Double3x2 loadRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    public Double3x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public Double3x2 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x2 r = StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
        return r;
    }
    public Double3x2 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }
    @Mutated public Double3x2 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }
    public MemorySegment storeRM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest, stride); }
    public MemorySegment storeRM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }
    @Mutated public Double3x2 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src, stride); }
    public Double3x2 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[_p1] = (float) this.m10;
        dest[_p1 + 1] = (float) this.m11;
        dest[_p2] = (float) this.m20;
        dest[_p2 + 1] = (float) this.m21;
        return dest;
    }
    public @Mutated Double3x2 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m10 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m20 = src[_p2];
        this.m21 = src[_p2 + 1];
        return this;
    }
    public FloatBuffer storeRM(@Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public FloatBuffer storeRMRelative(@Mutated FloatBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
        return buf;
    }
    @Mutated public Double3x2 loadRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }
    @Mutated public Double3x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Double3x2 loadRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
        return this;
    }
    public ByteBuffer storeRMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf, stride);
    }
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return buf;
    }
    public Double3x2 loadRMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, buf.position(), buf, stride);
    }
    public Double3x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf, stride);
    }
    public Double3x2 loadRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x2 r = StoreLoad.BB_OPS.loadRMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return r;
    }
    public Double3x2 storeRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address, stride);
    }
    @Mutated public Double3x2 loadRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address, stride);
    }
    public MemorySegment storeRMFloat(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRMFloat(this, 0L, dest, stride); }
    public MemorySegment storeRMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest, stride);
    }
    @Mutated public Double3x2 loadRMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMFloat(this, 0L, src, stride); }
    public Double3x2 loadRMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src, stride);
    }
}
