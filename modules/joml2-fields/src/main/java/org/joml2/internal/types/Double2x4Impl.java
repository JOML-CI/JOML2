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
 * Generated implementation of {@link Double2x4} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Double2x4Impl implements Double2x4 {

    public double m00;
    public double m01;
    public double m02;
    public double m03;
    public double m10;
    public double m11;
    public double m12;
    public double m13;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double2x4SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double2x4SegOpsUnsafe()
                        : new Double2x4SegOpsMS();
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
        m00 = 1;
        m11 = 1;
    }

    public Double2x4Impl(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
    }

    public Double2x4Impl(Double2x4R src) {
        this.m00 = src.m00();
        this.m01 = src.m01();
        this.m02 = src.m02();
        this.m03 = src.m03();
        this.m10 = src.m10();
        this.m11 = src.m11();
        this.m12 = src.m12();
        this.m13 = src.m13();
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
        Double2Impl d = (Double2Impl) dest;
        double _idxSw0;
        double _idxSw1;
        switch (col) {
            case 0: _idxSw0 = this.m00; _idxSw1 = this.m10; break;
            case 1: _idxSw0 = this.m01; _idxSw1 = this.m11; break;
            case 2: _idxSw0 = this.m02; _idxSw1 = this.m12; break;
            case 3: _idxSw0 = this.m03; _idxSw1 = this.m13; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw0;
        d.y = _idxSw1;
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
    public Double4 getRow(int row, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        double _idxSw3;
        switch (row) {
            case 0: _idxSw0 = this.m00; _idxSw1 = this.m01; _idxSw2 = this.m02; _idxSw3 = this.m03; break;
            case 1: _idxSw0 = this.m10; _idxSw1 = this.m11; _idxSw2 = this.m12; _idxSw3 = this.m13; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw0;
        d.y = _idxSw1;
        d.z = _idxSw2;
        d.w = _idxSw3;
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
            return java.lang.Math.sqrt(java.lang.Math.fma(this.m00, this.m00, this.m01 * this.m01) + java.lang.Math.fma(this.m02, this.m02, this.m03 * this.m03) + (java.lang.Math.fma(this.m10, this.m10, this.m11 * this.m11) + java.lang.Math.fma(this.m12, this.m12, this.m13 * this.m13)));
        } else {
            return java.lang.Math.sqrt(((this.m00) * (this.m00) + (this.m01 * this.m01)) + ((this.m02) * (this.m02) + (this.m03 * this.m03)) + (((this.m10) * (this.m10) + (this.m11 * this.m11)) + ((this.m12) * (this.m12) + (this.m13 * this.m13))));
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
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m01;
        d.m20 = this.m02;
        d.m30 = this.m03;
        d.m01 = this.m10;
        d.m11 = this.m11;
        d.m21 = this.m12;
        d.m31 = this.m13;
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
    public Double2x4 add(Double2x4R other, @Mutated Double2x4 dest) {
        Double2x4Impl d = (Double2x4Impl) dest;
        d.m00 = other.m00() + this.m00;
        d.m01 = other.m01() + this.m01;
        d.m02 = other.m02() + this.m02;
        d.m03 = other.m03() + this.m03;
        d.m10 = other.m10() + this.m10;
        d.m11 = other.m11() + this.m11;
        d.m12 = other.m12() + this.m12;
        d.m13 = other.m13() + this.m13;
        return d;
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
        Double2x4Impl d = (Double2x4Impl) dest;
        d.m00 = m00 + this.m00;
        d.m01 = m01 + this.m01;
        d.m02 = m02 + this.m02;
        d.m03 = m03 + this.m03;
        d.m10 = m10 + this.m10;
        d.m11 = m11 + this.m11;
        d.m12 = m12 + this.m12;
        d.m13 = m13 + this.m13;
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
    public Double2x4 mul(double scalar, @Mutated Double2x4 dest) {
        Double2x4Impl d = (Double2x4Impl) dest;
        d.m00 = scalar * this.m00;
        d.m01 = scalar * this.m01;
        d.m02 = scalar * this.m02;
        d.m03 = scalar * this.m03;
        d.m10 = scalar * this.m10;
        d.m11 = scalar * this.m11;
        d.m12 = scalar * this.m12;
        d.m13 = scalar * this.m13;
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
    public Double2x4 negate(@Mutated Double2x4 dest) {
        Double2x4Impl d = (Double2x4Impl) dest;
        d.m00 = -this.m00;
        d.m01 = -this.m01;
        d.m02 = -this.m02;
        d.m03 = -this.m03;
        d.m10 = -this.m10;
        d.m11 = -this.m11;
        d.m12 = -this.m12;
        d.m13 = -this.m13;
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
    public Double2x4 sub(Double2x4R other, @Mutated Double2x4 dest) {
        Double2x4Impl d = (Double2x4Impl) dest;
        d.m00 = this.m00 - other.m00();
        d.m01 = this.m01 - other.m01();
        d.m02 = this.m02 - other.m02();
        d.m03 = this.m03 - other.m03();
        d.m10 = this.m10 - other.m10();
        d.m11 = this.m11 - other.m11();
        d.m12 = this.m12 - other.m12();
        d.m13 = this.m13 - other.m13();
        return d;
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
        Double2x4Impl d = (Double2x4Impl) dest;
        d.m00 = this.m00 - m00;
        d.m01 = this.m01 - m01;
        d.m02 = this.m02 - m02;
        d.m03 = this.m03 - m03;
        d.m10 = this.m10 - m10;
        d.m11 = this.m11 - m11;
        d.m12 = this.m12 - m12;
        d.m13 = this.m13 - m13;
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
    @Mutated public Double2x4 set(Double2x4R v) {
        this.m00 = v.m00();
        this.m01 = v.m01();
        this.m02 = v.m02();
        this.m03 = v.m03();
        this.m10 = v.m10();
        this.m11 = v.m11();
        this.m12 = v.m12();
        this.m13 = v.m13();
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
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
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
        Float2x4Impl d = (Float2x4Impl) dest;
        d.m00 = (float) (this.m00);
        d.m01 = (float) (this.m01);
        d.m02 = (float) (this.m02);
        d.m03 = (float) (this.m03);
        d.m10 = (float) (this.m10);
        d.m11 = (float) (this.m11);
        d.m12 = (float) (this.m12);
        d.m13 = (float) (this.m13);
        return d;
    }


    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Double2x4 makeIdentity() {
        this.m00 = 1.0;
        this.m01 = 0.0;
        this.m02 = 0.0;
        this.m03 = 0.0;
        this.m10 = 0.0;
        this.m11 = 1.0;
        this.m12 = 0.0;
        this.m13 = 0.0;
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
        Double2x4Impl d = (Double2x4Impl) dest;
        d.m00 = Math.fma(t, other.m00() - this.m00, this.m00);
        d.m01 = Math.fma(t, other.m01() - this.m01, this.m01);
        d.m02 = Math.fma(t, other.m02() - this.m02, this.m02);
        d.m03 = Math.fma(t, other.m03() - this.m03, this.m03);
        d.m10 = Math.fma(t, other.m10() - this.m10, this.m10);
        d.m11 = Math.fma(t, other.m11() - this.m11, this.m11);
        d.m12 = Math.fma(t, other.m12() - this.m12, this.m12);
        d.m13 = Math.fma(t, other.m13() - this.m13, this.m13);
        return d;
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
        Double2x4Impl d = (Double2x4Impl) dest;
        d.m00 = Math.fma(t, m00 - this.m00, this.m00);
        d.m01 = Math.fma(t, m01 - this.m01, this.m01);
        d.m02 = Math.fma(t, m02 - this.m02, this.m02);
        d.m03 = Math.fma(t, m03 - this.m03, this.m03);
        d.m10 = Math.fma(t, m10 - this.m10, this.m10);
        d.m11 = Math.fma(t, m11 - this.m11, this.m11);
        d.m12 = Math.fma(t, m12 - this.m12, this.m12);
        d.m13 = Math.fma(t, m13 - this.m13, this.m13);
        return d;
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
        Double2x4Impl d = (Double2x4Impl) dest;
        double _rd0 = right.m00();
        double _rd1 = right.m01();
        double _rd2 = right.m02();
        double _rd3 = right.m03();
        double _rd4 = this.m00;
        double _rd5 = this.m01;
        double _rd6 = this.m10;
        double _rd7 = this.m11;
        d.m00 = Math.fma(_rd0, _rd4, right.m10() * _rd5);
        d.m01 = Math.fma(_rd1, _rd4, right.m11() * _rd5);
        d.m02 = Math.fma(_rd2, _rd4, Math.fma(right.m12(), _rd5, this.m02));
        d.m03 = Math.fma(_rd3, _rd4, Math.fma(right.m13(), _rd5, this.m03));
        d.m10 = Math.fma(_rd0, _rd6, right.m10() * _rd7);
        d.m11 = Math.fma(_rd1, _rd6, right.m11() * _rd7);
        d.m12 = Math.fma(_rd2, _rd6, Math.fma(right.m12(), _rd7, this.m12));
        d.m13 = Math.fma(_rd3, _rd6, Math.fma(right.m13(), _rd7, this.m13));
        return d;
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
        Double2x4Impl d = (Double2x4Impl) dest;
        double _rd0 = this.m00;
        double _rd1 = this.m01;
        double _rd2 = this.m10;
        double _rd3 = this.m11;
        d.m00 = Math.fma(m00, _rd0, m10 * _rd1);
        d.m01 = Math.fma(m01, _rd0, m11 * _rd1);
        d.m02 = Math.fma(m02, _rd0, Math.fma(m12, _rd1, this.m02));
        d.m03 = Math.fma(m03, _rd0, Math.fma(m13, _rd1, this.m03));
        d.m10 = Math.fma(m00, _rd2, m10 * _rd3);
        d.m11 = Math.fma(m01, _rd2, m11 * _rd3);
        d.m12 = Math.fma(m02, _rd2, Math.fma(m12, _rd3, this.m12));
        d.m13 = Math.fma(m03, _rd2, Math.fma(m13, _rd3, this.m13));
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
    public Double2x4 preMul(Double2x4R other, @Mutated Double2x4 dest) {
        Double2x4Impl d = (Double2x4Impl) dest;
        double _rd0 = other.m00();
        double _rd1 = other.m01();
        double _rd2 = other.m10();
        double _rd3 = other.m11();
        double _rd4 = this.m00;
        double _rd5 = this.m01;
        double _rd6 = this.m02;
        double _rd7 = this.m03;
        d.m00 = Math.fma(_rd0, _rd4, _rd1 * this.m10);
        d.m01 = Math.fma(_rd0, _rd5, _rd1 * this.m11);
        d.m02 = Math.fma(_rd0, _rd6, Math.fma(_rd1, this.m12, other.m02()));
        d.m03 = Math.fma(_rd0, _rd7, Math.fma(_rd1, this.m13, other.m03()));
        d.m10 = Math.fma(_rd2, _rd4, _rd3 * this.m10);
        d.m11 = Math.fma(_rd2, _rd5, _rd3 * this.m11);
        d.m12 = Math.fma(_rd2, _rd6, Math.fma(_rd3, this.m12, other.m12()));
        d.m13 = Math.fma(_rd2, _rd7, Math.fma(_rd3, this.m13, other.m13()));
        return d;
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
        Double2x4Impl d = (Double2x4Impl) dest;
        double _rd0 = this.m00;
        double _rd1 = this.m01;
        double _rd2 = this.m02;
        double _rd3 = this.m03;
        d.m00 = Math.fma(m00, _rd0, m01 * this.m10);
        d.m01 = Math.fma(m00, _rd1, m01 * this.m11);
        d.m02 = Math.fma(m00, _rd2, Math.fma(m01, this.m12, m02));
        d.m03 = Math.fma(m00, _rd3, Math.fma(m01, this.m13, m03));
        d.m10 = Math.fma(m10, _rd0, m11 * this.m10);
        d.m11 = Math.fma(m10, _rd1, m11 * this.m11);
        d.m12 = Math.fma(m10, _rd2, Math.fma(m11, this.m12, m12));
        d.m13 = Math.fma(m10, _rd3, Math.fma(m11, this.m13, m13));
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
    public Double2x4 addScaled(Double2x4R other, double weight, @Mutated Double2x4 dest) {
        Double2x4Impl d = (Double2x4Impl) dest;
        d.m00 = Math.fma(weight, other.m00(), this.m00);
        d.m01 = Math.fma(weight, other.m01(), this.m01);
        d.m02 = Math.fma(weight, other.m02(), this.m02);
        d.m03 = Math.fma(weight, other.m03(), this.m03);
        d.m10 = Math.fma(weight, other.m10(), this.m10);
        d.m11 = Math.fma(weight, other.m11(), this.m11);
        d.m12 = Math.fma(weight, other.m12(), this.m12);
        d.m13 = Math.fma(weight, other.m13(), this.m13);
        return d;
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
            Double2x4Impl d = (Double2x4Impl) dest;
            d.m00 = java.lang.Math.fma(weight, m00, this.m00);
            d.m01 = java.lang.Math.fma(weight, m01, this.m01);
            d.m02 = java.lang.Math.fma(weight, m02, this.m02);
            d.m03 = java.lang.Math.fma(weight, m03, this.m03);
            d.m10 = java.lang.Math.fma(weight, m10, this.m10);
            d.m11 = java.lang.Math.fma(weight, m11, this.m11);
            d.m12 = java.lang.Math.fma(weight, m12, this.m12);
            d.m13 = java.lang.Math.fma(weight, m13, this.m13);
            return d;
        } else {
            Double2x4Impl d = (Double2x4Impl) dest;
            d.m00 = ((weight) * (m00) + (this.m00));
            d.m01 = ((weight) * (m01) + (this.m01));
            d.m02 = ((weight) * (m02) + (this.m02));
            d.m03 = ((weight) * (m03) + (this.m03));
            d.m10 = ((weight) * (m10) + (this.m10));
            d.m11 = ((weight) * (m11) + (this.m11));
            d.m12 = ((weight) * (m12) + (this.m12));
            d.m13 = ((weight) * (m13) + (this.m13));
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
    public Double2 mul(Double4R v, @Mutated Double2 dest) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        double vW = v.w();
        if (Math.useFma()) {
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(this.m03, vW, java.lang.Math.fma(this.m02, vZ, java.lang.Math.fma(this.m00, vX, this.m01 * vY)));
            d.y = java.lang.Math.fma(this.m13, vW, java.lang.Math.fma(this.m12, vZ, java.lang.Math.fma(this.m10, vX, this.m11 * vY)));
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((this.m03) * (vW) + (((this.m02) * (vZ) + (((this.m00) * (vX) + (this.m01 * vY))))));
            d.y = ((this.m13) * (vW) + (((this.m12) * (vZ) + (((this.m10) * (vX) + (this.m11 * vY))))));
            return d;
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
            Double2Impl d = (Double2Impl) dest;
            d.x = java.lang.Math.fma(this.m03, vW, java.lang.Math.fma(this.m02, vZ, java.lang.Math.fma(this.m00, vX, this.m01 * vY)));
            d.y = java.lang.Math.fma(this.m13, vW, java.lang.Math.fma(this.m12, vZ, java.lang.Math.fma(this.m10, vX, this.m11 * vY)));
            return d;
        } else {
            Double2Impl d = (Double2Impl) dest;
            d.x = ((this.m03) * (vW) + (((this.m02) * (vZ) + (((this.m00) * (vX) + (this.m01 * vY))))));
            d.y = ((this.m13) * (vW) + (((this.m12) * (vZ) + (((this.m10) * (vX) + (this.m11 * vY))))));
            return d;
        }
    }

    public double m00() { return this.m00; }
    public double m01() { return this.m01; }
    public double m02() { return this.m02; }
    public double m03() { return this.m03; }
    public double m10() { return this.m10; }
    public double m11() { return this.m11; }
    public double m12() { return this.m12; }
    public double m13() { return this.m13; }

    @Override public String toString() {
        return "Double2x4(\n    " + m00() + ", " + m01() + ", " + m02() + ", " + m03() + "\n    " + m10() + ", " + m11() + ", " + m12() + ", " + m13() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double2x4Impl)) return false;
        Double2x4Impl o = (Double2x4Impl) obj;
        return Double.doubleToLongBits(m00) == Double.doubleToLongBits(o.m00)
            && Double.doubleToLongBits(m01) == Double.doubleToLongBits(o.m01)
            && Double.doubleToLongBits(m02) == Double.doubleToLongBits(o.m02)
            && Double.doubleToLongBits(m03) == Double.doubleToLongBits(o.m03)
            && Double.doubleToLongBits(m10) == Double.doubleToLongBits(o.m10)
            && Double.doubleToLongBits(m11) == Double.doubleToLongBits(o.m11)
            && Double.doubleToLongBits(m12) == Double.doubleToLongBits(o.m12)
            && Double.doubleToLongBits(m13) == Double.doubleToLongBits(o.m13);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(Double.doubleToLongBits(m00) ^ (Double.doubleToLongBits(m00) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m01) ^ (Double.doubleToLongBits(m01) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m02) ^ (Double.doubleToLongBits(m02) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m03) ^ (Double.doubleToLongBits(m03) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m10) ^ (Double.doubleToLongBits(m10) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m11) ^ (Double.doubleToLongBits(m11) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m12) ^ (Double.doubleToLongBits(m12) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m13) ^ (Double.doubleToLongBits(m13) >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return Double.isFinite(m00)
            && Double.isFinite(m01)
            && Double.isFinite(m02)
            && Double.isFinite(m03)
            && Double.isFinite(m10)
            && Double.isFinite(m11)
            && Double.isFinite(m12)
            && Double.isFinite(m13);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(m00)
            || Double.isNaN(m01)
            || Double.isNaN(m02)
            || Double.isNaN(m03)
            || Double.isNaN(m10)
            || Double.isNaN(m11)
            || Double.isNaN(m12)
            || Double.isNaN(m13);
    }

    @Override public boolean equalsEpsilon(Double2x4R other, double epsilon) {
        return java.lang.Math.abs(m00 - other.m00()) <= epsilon
            && java.lang.Math.abs(m01 - other.m01()) <= epsilon
            && java.lang.Math.abs(m02 - other.m02()) <= epsilon
            && java.lang.Math.abs(m03 - other.m03()) <= epsilon
            && java.lang.Math.abs(m10 - other.m10()) <= epsilon
            && java.lang.Math.abs(m11 - other.m11()) <= epsilon
            && java.lang.Math.abs(m12 - other.m12()) <= epsilon
            && java.lang.Math.abs(m13 - other.m13()) <= epsilon;
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m01;
        dest[offset + 3] = this.m11;
        dest[offset + 4] = this.m02;
        dest[offset + 5] = this.m12;
        dest[offset + 6] = this.m03;
        dest[offset + 7] = this.m13;
        return dest;
    }
    public @Mutated Double2x4 loadCM(double[] src, int offset) {
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m01 = src[offset + 2];
        this.m11 = src[offset + 3];
        this.m02 = src[offset + 4];
        this.m12 = src[offset + 5];
        this.m03 = src[offset + 6];
        this.m13 = src[offset + 7];
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
    public MemorySegment storeCM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest); }
    public MemorySegment storeCM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest);
    }
    @Mutated public Double2x4 loadCM(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src); }
    public Double2x4 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src);
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) this.m01;
        dest[offset + 3] = (float) this.m11;
        dest[offset + 4] = (float) this.m02;
        dest[offset + 5] = (float) this.m12;
        dest[offset + 6] = (float) this.m03;
        dest[offset + 7] = (float) this.m13;
        return dest;
    }
    public @Mutated Double2x4 loadCM(float[] src, int offset) {
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m01 = src[offset + 2];
        this.m11 = src[offset + 3];
        this.m02 = src[offset + 4];
        this.m12 = src[offset + 5];
        this.m03 = src[offset + 6];
        this.m13 = src[offset + 7];
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
    public MemorySegment storeCMFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCMFloat(this, 0L, dest); }
    public MemorySegment storeCMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest);
    }
    @Mutated public Double2x4 loadCMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMFloat(this, 0L, src); }
    public Double2x4 loadCMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m03;
        dest[offset + 4] = this.m10;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = this.m12;
        dest[offset + 7] = this.m13;
        return dest;
    }
    public @Mutated Double2x4 loadRM(double[] src, int offset) {
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m02 = src[offset + 2];
        this.m03 = src[offset + 3];
        this.m10 = src[offset + 4];
        this.m11 = src[offset + 5];
        this.m12 = src[offset + 6];
        this.m13 = src[offset + 7];
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
    public MemorySegment storeRM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest); }
    public MemorySegment storeRM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }
    @Mutated public Double2x4 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src); }
    public Double2x4 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) this.m02;
        dest[offset + 3] = (float) this.m03;
        dest[offset + 4] = (float) this.m10;
        dest[offset + 5] = (float) this.m11;
        dest[offset + 6] = (float) this.m12;
        dest[offset + 7] = (float) this.m13;
        return dest;
    }
    public @Mutated Double2x4 loadRM(float[] src, int offset) {
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m02 = src[offset + 2];
        this.m03 = src[offset + 3];
        this.m10 = src[offset + 4];
        this.m11 = src[offset + 5];
        this.m12 = src[offset + 6];
        this.m13 = src[offset + 7];
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
    public MemorySegment storeRMFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRMFloat(this, 0L, dest); }
    public MemorySegment storeRMFloat(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest);
    }
    @Mutated public Double2x4 loadRMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMFloat(this, 0L, src); }
    public Double2x4 loadRMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src);
    }

    public double[] storeCM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        dest[_p2] = this.m02;
        dest[_p2 + 1] = this.m12;
        dest[_p3] = this.m03;
        dest[_p3 + 1] = this.m13;
        return dest;
    }
    public @Mutated Double2x4 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m01 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m02 = src[_p2];
        this.m12 = src[_p2 + 1];
        this.m03 = src[_p3];
        this.m13 = src[_p3 + 1];
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
    public MemorySegment storeCM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest, stride); }
    public MemorySegment storeCM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }
    @Mutated public Double2x4 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src, stride); }
    public Double2x4 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src, stride);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[_p1] = (float) this.m01;
        dest[_p1 + 1] = (float) this.m11;
        dest[_p2] = (float) this.m02;
        dest[_p2 + 1] = (float) this.m12;
        dest[_p3] = (float) this.m03;
        dest[_p3 + 1] = (float) this.m13;
        return dest;
    }
    public @Mutated Double2x4 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m01 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m02 = src[_p2];
        this.m12 = src[_p2 + 1];
        this.m03 = src[_p3];
        this.m13 = src[_p3 + 1];
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
    public MemorySegment storeCMFloat(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCMFloat(this, 0L, dest, stride); }
    public MemorySegment storeCMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest, stride);
    }
    @Mutated public Double2x4 loadCMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMFloat(this, 0L, src, stride); }
    public Double2x4 loadCMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src, stride);
    }

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m03;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m12;
        dest[_p1 + 3] = this.m13;
        return dest;
    }
    public @Mutated Double2x4 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m02 = src[offset + 2];
        this.m03 = src[offset + 3];
        this.m10 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m12 = src[_p1 + 2];
        this.m13 = src[_p1 + 3];
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
    public MemorySegment storeRM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest, stride); }
    public MemorySegment storeRM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }
    @Mutated public Double2x4 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src, stride); }
    public Double2x4 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) this.m02;
        dest[offset + 3] = (float) this.m03;
        dest[_p1] = (float) this.m10;
        dest[_p1 + 1] = (float) this.m11;
        dest[_p1 + 2] = (float) this.m12;
        dest[_p1 + 3] = (float) this.m13;
        return dest;
    }
    public @Mutated Double2x4 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m02 = src[offset + 2];
        this.m03 = src[offset + 3];
        this.m10 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m12 = src[_p1 + 2];
        this.m13 = src[_p1 + 3];
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
    public MemorySegment storeRMFloat(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRMFloat(this, 0L, dest, stride); }
    public MemorySegment storeRMFloat(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest, stride);
    }
    @Mutated public Double2x4 loadRMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMFloat(this, 0L, src, stride); }
    public Double2x4 loadRMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src, stride);
    }
}
