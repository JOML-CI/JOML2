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
 * Generated implementation of {@link Double4x2} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Double4x2Impl implements Double4x2 {
    public double m00;
    public double m10;
    public double m20;
    public double m30;
    public double m01;
    public double m11;
    public double m21;
    public double m31;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Double4x2SegOps SEG_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Double4x2SegOpsUnsafe()
            : new Double4x2SegOpsMS();
        static final Double4x2BbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Double4x2BbOpsUnsafe()
            : new Double4x2BbOpsApi();
        static final Double4x2RawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new Double4x2RawOpsUnsafe()
            : new Double4x2RawOpsApi();
    }

    public Double4x2Impl() {
        m00 = 1;
        m11 = 1;
    }

    public Double4x2Impl(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31) {
        this.m00 = m00;
        this.m10 = m10;
        this.m20 = m20;
        this.m30 = m30;
        this.m01 = m01;
        this.m11 = m11;
        this.m21 = m21;
        this.m31 = m31;
    }

    public Double4x2Impl(Double4x2R src) {
        Double4x2Impl srcImpl = (Double4x2Impl) src;
        this.m00 = srcImpl.m00;
        this.m10 = srcImpl.m10;
        this.m20 = srcImpl.m20;
        this.m30 = srcImpl.m30;
        this.m01 = srcImpl.m01;
        this.m11 = srcImpl.m11;
        this.m21 = srcImpl.m21;
        this.m31 = srcImpl.m31;
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
        Double4Impl d = (Double4Impl) dest;
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        double _idxSw3;
        switch (col) {
            case 0: _idxSw0 = this.m00; _idxSw1 = this.m10; _idxSw2 = this.m20; _idxSw3 = this.m30; break;
            case 1: _idxSw0 = this.m01; _idxSw1 = this.m11; _idxSw2 = this.m21; _idxSw3 = this.m31; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw0;
        d.y = _idxSw1;
        d.z = _idxSw2;
        d.w = _idxSw3;
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
            case 3: _idxSw0 = this.m30; _idxSw1 = this.m31; break;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            return java.lang.Math.sqrt(java.lang.Math.fma(this.m00, this.m00, this.m01 * this.m01) + java.lang.Math.fma(this.m10, this.m10, this.m11 * this.m11) + (java.lang.Math.fma(this.m20, this.m20, this.m21 * this.m21) + java.lang.Math.fma(this.m30, this.m30, this.m31 * this.m31)));
        } else {
            return java.lang.Math.sqrt(((this.m00) * (this.m00) + (this.m01 * this.m01)) + ((this.m10) * (this.m10) + (this.m11 * this.m11)) + (((this.m20) * (this.m20) + (this.m21 * this.m21)) + ((this.m30) * (this.m30) + (this.m31 * this.m31))));
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
    public Double2x4 transpose(@Mutated Double2x4 dest) {
        Double2x4Impl d = (Double2x4Impl) dest;
        d.m00 = this.m00;
        d.m01 = this.m10;
        d.m02 = this.m20;
        d.m03 = this.m30;
        d.m10 = this.m01;
        d.m11 = this.m11;
        d.m12 = this.m21;
        d.m13 = this.m31;
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
    public Double4x2 add(Double4x2R other, @Mutated Double4x2 dest) {
        Double4x2Impl otherImpl = (Double4x2Impl) other;
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = otherImpl.m00 + this.m00;
        d.m10 = otherImpl.m10 + this.m10;
        d.m20 = otherImpl.m20 + this.m20;
        d.m30 = otherImpl.m30 + this.m30;
        d.m01 = otherImpl.m01 + this.m01;
        d.m11 = otherImpl.m11 + this.m11;
        d.m21 = otherImpl.m21 + this.m21;
        d.m31 = otherImpl.m31 + this.m31;
        return d;
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
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = m00 + this.m00;
        d.m10 = m10 + this.m10;
        d.m20 = m20 + this.m20;
        d.m30 = m30 + this.m30;
        d.m01 = m01 + this.m01;
        d.m11 = m11 + this.m11;
        d.m21 = m21 + this.m21;
        d.m31 = m31 + this.m31;
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
    public Double4x2 mul(double scalar, @Mutated Double4x2 dest) {
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = scalar * this.m10;
        d.m20 = scalar * this.m20;
        d.m30 = scalar * this.m30;
        d.m01 = scalar * this.m01;
        d.m11 = scalar * this.m11;
        d.m21 = scalar * this.m21;
        d.m31 = scalar * this.m31;
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
    public Double4x2 negate(@Mutated Double4x2 dest) {
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m20 = -this.m20;
        d.m30 = -this.m30;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.m21 = -this.m21;
        d.m31 = -this.m31;
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
    public Double4x2 sub(Double4x2R other, @Mutated Double4x2 dest) {
        Double4x2Impl otherImpl = (Double4x2Impl) other;
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = this.m00 - otherImpl.m00;
        d.m10 = this.m10 - otherImpl.m10;
        d.m20 = this.m20 - otherImpl.m20;
        d.m30 = this.m30 - otherImpl.m30;
        d.m01 = this.m01 - otherImpl.m01;
        d.m11 = this.m11 - otherImpl.m11;
        d.m21 = this.m21 - otherImpl.m21;
        d.m31 = this.m31 - otherImpl.m31;
        return d;
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
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = this.m00 - m00;
        d.m10 = this.m10 - m10;
        d.m20 = this.m20 - m20;
        d.m30 = this.m30 - m30;
        d.m01 = this.m01 - m01;
        d.m11 = this.m11 - m11;
        d.m21 = this.m21 - m21;
        d.m31 = this.m31 - m31;
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
    @Mutated public Double4x2 set(Double4x2R v) {
        Double4x2Impl vImpl = (Double4x2Impl) v;
        this.m00 = vImpl.m00;
        this.m10 = vImpl.m10;
        this.m20 = vImpl.m20;
        this.m30 = vImpl.m30;
        this.m01 = vImpl.m01;
        this.m11 = vImpl.m11;
        this.m21 = vImpl.m21;
        this.m31 = vImpl.m31;
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
        this.m00 = m00;
        this.m10 = m10;
        this.m20 = m20;
        this.m30 = m30;
        this.m01 = m01;
        this.m11 = m11;
        this.m21 = m21;
        this.m31 = m31;
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
        Float4x2Impl d = (Float4x2Impl) dest;
        d.m00 = (float) (this.m00);
        d.m10 = (float) (this.m10);
        d.m20 = (float) (this.m20);
        d.m30 = (float) (this.m30);
        d.m01 = (float) (this.m01);
        d.m11 = (float) (this.m11);
        d.m21 = (float) (this.m21);
        d.m31 = (float) (this.m31);
        return d;
    }

    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Double4x2 makeIdentity() {
        this.m00 = 1.0;
        this.m10 = 0.0;
        this.m20 = 0.0;
        this.m30 = 0.0;
        this.m01 = 0.0;
        this.m11 = 1.0;
        this.m21 = 0.0;
        this.m31 = 0.0;
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
     * shows when this component is much larger in magnitude than the other one (1e17 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 lerp(Double4x2R other, double t, @Mutated Double4x2 dest) {
        Double4x2Impl otherImpl = (Double4x2Impl) other;
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(other, t, dest);
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = java.lang.Math.fma(t, otherImpl.m00 - this.m00, this.m00);
        d.m10 = java.lang.Math.fma(t, otherImpl.m10 - this.m10, this.m10);
        d.m20 = java.lang.Math.fma(t, otherImpl.m20 - this.m20, this.m20);
        d.m30 = java.lang.Math.fma(t, otherImpl.m30 - this.m30, this.m30);
        d.m01 = java.lang.Math.fma(t, otherImpl.m01 - this.m01, this.m01);
        d.m11 = java.lang.Math.fma(t, otherImpl.m11 - this.m11, this.m11);
        d.m21 = java.lang.Math.fma(t, otherImpl.m21 - this.m21, this.m21);
        d.m31 = java.lang.Math.fma(t, otherImpl.m31 - this.m31, this.m31);
        return d;
    }

    /** {@code lerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x2 lerp_mulAdd(Double4x2R other, double t, @Mutated Double4x2 dest) {
        Double4x2Impl otherImpl = (Double4x2Impl) other;
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = ((t) * (otherImpl.m00 - this.m00) + (this.m00));
        d.m10 = ((t) * (otherImpl.m10 - this.m10) + (this.m10));
        d.m20 = ((t) * (otherImpl.m20 - this.m20) + (this.m20));
        d.m30 = ((t) * (otherImpl.m30 - this.m30) + (this.m30));
        d.m01 = ((t) * (otherImpl.m01 - this.m01) + (this.m01));
        d.m11 = ((t) * (otherImpl.m11 - this.m11) + (this.m11));
        d.m21 = ((t) * (otherImpl.m21 - this.m21) + (this.m21));
        d.m31 = ((t) * (otherImpl.m31 - this.m31) + (this.m31));
        return d;
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
     * shows when this component is much larger in magnitude than the other one (1e17 towards 1 ends
     * at 0).
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
        if (!org.joml2.internal.FmaSwitch.ON) return lerp_mulAdd(m00, m01, m10, m11, m20, m21, m30, m31, t, dest);
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = java.lang.Math.fma(t, m00 - this.m00, this.m00);
        d.m10 = java.lang.Math.fma(t, m10 - this.m10, this.m10);
        d.m20 = java.lang.Math.fma(t, m20 - this.m20, this.m20);
        d.m30 = java.lang.Math.fma(t, m30 - this.m30, this.m30);
        d.m01 = java.lang.Math.fma(t, m01 - this.m01, this.m01);
        d.m11 = java.lang.Math.fma(t, m11 - this.m11, this.m11);
        d.m21 = java.lang.Math.fma(t, m21 - this.m21, this.m21);
        d.m31 = java.lang.Math.fma(t, m31 - this.m31, this.m31);
        return d;
    }

    /** {@code lerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x2 lerp_mulAdd(double m00, double m01, double m10, double m11, double m20, double m21, double m30, double m31, double t, @Mutated Double4x2 dest) {
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = ((t) * (m00 - this.m00) + (this.m00));
        d.m10 = ((t) * (m10 - this.m10) + (this.m10));
        d.m20 = ((t) * (m20 - this.m20) + (this.m20));
        d.m30 = ((t) * (m30 - this.m30) + (this.m30));
        d.m01 = ((t) * (m01 - this.m01) + (this.m01));
        d.m11 = ((t) * (m11 - this.m11) + (this.m11));
        d.m21 = ((t) * (m21 - this.m21) + (this.m21));
        d.m31 = ((t) * (m31 - this.m31) + (this.m31));
        return d;
    }

    /**
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * This is the plain 4x2 * 2x2 matrix product: the inner dimensions meet, no operand is
     * extended.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @param dest will hold the result
     * @return dest
     */
    public Double4x2 mul(Double2x2R right, @Mutated Double4x2 dest) {
        Double2x2Impl rightImpl = (Double2x2Impl) right;
        if (!org.joml2.internal.FmaSwitch.ON) return mul_mulAdd(right, dest);
        Double4x2Impl d = (Double4x2Impl) dest;
        double _rd0 = this.m00;
        double _rd1 = this.m10;
        double _rd2 = this.m20;
        double _rd3 = this.m30;
        d.m00 = java.lang.Math.fma(rightImpl.m00, _rd0, rightImpl.m10 * this.m01);
        d.m10 = java.lang.Math.fma(rightImpl.m00, _rd1, rightImpl.m10 * this.m11);
        d.m20 = java.lang.Math.fma(rightImpl.m00, _rd2, rightImpl.m10 * this.m21);
        d.m30 = java.lang.Math.fma(rightImpl.m00, _rd3, rightImpl.m10 * this.m31);
        d.m01 = java.lang.Math.fma(rightImpl.m01, _rd0, rightImpl.m11 * this.m01);
        d.m11 = java.lang.Math.fma(rightImpl.m01, _rd1, rightImpl.m11 * this.m11);
        d.m21 = java.lang.Math.fma(rightImpl.m01, _rd2, rightImpl.m11 * this.m21);
        d.m31 = java.lang.Math.fma(rightImpl.m01, _rd3, rightImpl.m11 * this.m31);
        return d;
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x2 mul_mulAdd(Double2x2R right, @Mutated Double4x2 dest) {
        Double2x2Impl rightImpl = (Double2x2Impl) right;
        Double4x2Impl d = (Double4x2Impl) dest;
        double _rd0 = this.m00;
        double _rd1 = this.m10;
        double _rd2 = this.m20;
        double _rd3 = this.m30;
        d.m00 = ((rightImpl.m00) * (_rd0) + (rightImpl.m10 * this.m01));
        d.m10 = ((rightImpl.m00) * (_rd1) + (rightImpl.m10 * this.m11));
        d.m20 = ((rightImpl.m00) * (_rd2) + (rightImpl.m10 * this.m21));
        d.m30 = ((rightImpl.m00) * (_rd3) + (rightImpl.m10 * this.m31));
        d.m01 = ((rightImpl.m01) * (_rd0) + (rightImpl.m11 * this.m01));
        d.m11 = ((rightImpl.m01) * (_rd1) + (rightImpl.m11 * this.m11));
        d.m21 = ((rightImpl.m01) * (_rd2) + (rightImpl.m11 * this.m21));
        d.m31 = ((rightImpl.m01) * (_rd3) + (rightImpl.m11 * this.m31));
        return d;
    }

    /** Private column 0 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s5c316050_c0_fma(Double4x2Impl _dst, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _r12, double _r13, double _r14, double _r15, double _r16, double _r17, double _r18, double _r19, double _r20, double _r21, double _r22, double _r23) {
        _dst.m00 = java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, java.lang.Math.fma(_r4, _r5, _r6 * _r7)));
        _dst.m10 = java.lang.Math.fma(_r12, _r1, java.lang.Math.fma(_r13, _r3, java.lang.Math.fma(_r14, _r5, _r15 * _r7)));
        _dst.m20 = java.lang.Math.fma(_r16, _r1, java.lang.Math.fma(_r17, _r3, java.lang.Math.fma(_r18, _r5, _r19 * _r7)));
        _dst.m30 = java.lang.Math.fma(_r20, _r1, java.lang.Math.fma(_r21, _r3, java.lang.Math.fma(_r22, _r5, _r23 * _r7)));
    }

    /** Private column 1 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s5c316050_c1_fma(Double4x2Impl _dst, double _r0, double _r8, double _r2, double _r9, double _r4, double _r10, double _r6, double _r11, double _r12, double _r13, double _r14, double _r15, double _r16, double _r17, double _r18, double _r19, double _r20, double _r21, double _r22, double _r23) {
        _dst.m01 = java.lang.Math.fma(_r0, _r8, java.lang.Math.fma(_r2, _r9, java.lang.Math.fma(_r4, _r10, _r6 * _r11)));
        _dst.m11 = java.lang.Math.fma(_r12, _r8, java.lang.Math.fma(_r13, _r9, java.lang.Math.fma(_r14, _r10, _r15 * _r11)));
        _dst.m21 = java.lang.Math.fma(_r16, _r8, java.lang.Math.fma(_r17, _r9, java.lang.Math.fma(_r18, _r10, _r19 * _r11)));
        _dst.m31 = java.lang.Math.fma(_r20, _r8, java.lang.Math.fma(_r21, _r9, java.lang.Math.fma(_r22, _r10, _r23 * _r11)));
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private void preMul_s5c316050_tail_fma(Double4x2Impl _dst, Double4x4R other, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _r12, double _r13, double _r14, double _r15, double _r16, double _r17, double _r18, double _r19) {
        Double4x4Impl otherImpl = (Double4x4Impl) other;
        double _r20 = otherImpl.m33;
        double _r21 = otherImpl.m32;
        double _r22 = otherImpl.m30;
        double _r23 = otherImpl.m31;
        preMul_s5c316050_c0_fma(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20, _r21, _r22, _r23);
        preMul_s5c316050_c1_fma(_dst, _r0, _r8, _r2, _r9, _r4, _r10, _r6, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20, _r21, _r22, _r23);
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private void preMul_s5c316050_tail_mulAdd(Double4x2Impl _dst, Double4x4R other, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _r12, double _r13, double _r14, double _r15, double _r16, double _r17, double _r18, double _r19) {
        Double4x4Impl otherImpl = (Double4x4Impl) other;
        double _r20 = otherImpl.m33;
        double _r21 = otherImpl.m32;
        double _r22 = otherImpl.m30;
        double _r23 = otherImpl.m31;
        _dst.m00 = ((_r0) * (_r1) + (((_r2) * (_r3) + (((_r4) * (_r5) + (_r6 * _r7))))));
        _dst.m10 = ((_r12) * (_r1) + (((_r13) * (_r3) + (((_r14) * (_r5) + (_r15 * _r7))))));
        _dst.m20 = ((_r16) * (_r1) + (((_r17) * (_r3) + (((_r18) * (_r5) + (_r19 * _r7))))));
        _dst.m30 = ((_r20) * (_r1) + (((_r21) * (_r3) + (((_r22) * (_r5) + (_r23 * _r7))))));
        _dst.m01 = ((_r0) * (_r8) + (((_r2) * (_r9) + (((_r4) * (_r10) + (_r6 * _r11))))));
        _dst.m11 = ((_r12) * (_r8) + (((_r13) * (_r9) + (((_r14) * (_r10) + (_r15 * _r11))))));
        _dst.m21 = ((_r16) * (_r8) + (((_r17) * (_r9) + (((_r18) * (_r10) + (_r19 * _r11))))));
        _dst.m31 = ((_r20) * (_r8) + (((_r21) * (_r9) + (((_r22) * (_r10) + (_r23 * _r11))))));
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
        Double4x4Impl otherImpl = (Double4x4Impl) other;
        if (!org.joml2.internal.FmaSwitch.ON) return preMul_mulAdd(other, dest);
        Double4x2Impl d = (Double4x2Impl) dest;
        double _r0 = otherImpl.m03;
        double _r1 = this.m30;
        double _r2 = otherImpl.m02;
        double _r3 = this.m20;
        double _r4 = otherImpl.m00;
        double _r5 = this.m00;
        double _r6 = otherImpl.m01;
        double _r7 = this.m10;
        double _r8 = this.m31;
        double _r9 = this.m21;
        double _r10 = this.m01;
        double _r11 = this.m11;
        double _r12 = otherImpl.m13;
        double _r13 = otherImpl.m12;
        double _r14 = otherImpl.m10;
        double _r15 = otherImpl.m11;
        double _r16 = otherImpl.m23;
        double _r17 = otherImpl.m22;
        double _r18 = otherImpl.m20;
        double _r19 = otherImpl.m21;
        preMul_s5c316050_tail_fma(d, other, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19);
        return d;
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x2 preMul_mulAdd(Double4x4R other, @Mutated Double4x2 dest) {
        Double4x4Impl otherImpl = (Double4x4Impl) other;
        Double4x2Impl d = (Double4x2Impl) dest;
        double _r0 = otherImpl.m03;
        double _r1 = this.m30;
        double _r2 = otherImpl.m02;
        double _r3 = this.m20;
        double _r4 = otherImpl.m00;
        double _r5 = this.m00;
        double _r6 = otherImpl.m01;
        double _r7 = this.m10;
        double _r8 = this.m31;
        double _r9 = this.m21;
        double _r10 = this.m01;
        double _r11 = this.m11;
        double _r12 = otherImpl.m13;
        double _r13 = otherImpl.m12;
        double _r14 = otherImpl.m10;
        double _r15 = otherImpl.m11;
        double _r16 = otherImpl.m23;
        double _r17 = otherImpl.m22;
        double _r18 = otherImpl.m20;
        double _r19 = otherImpl.m21;
        preMul_s5c316050_tail_mulAdd(d, other, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19);
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
    public Double4x2 addScaled(Double4x2R other, double weight, @Mutated Double4x2 dest) {
        Double4x2Impl otherImpl = (Double4x2Impl) other;
        if (!org.joml2.internal.FmaSwitch.ON) return addScaled_mulAdd(other, weight, dest);
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = java.lang.Math.fma(weight, otherImpl.m00, this.m00);
        d.m10 = java.lang.Math.fma(weight, otherImpl.m10, this.m10);
        d.m20 = java.lang.Math.fma(weight, otherImpl.m20, this.m20);
        d.m30 = java.lang.Math.fma(weight, otherImpl.m30, this.m30);
        d.m01 = java.lang.Math.fma(weight, otherImpl.m01, this.m01);
        d.m11 = java.lang.Math.fma(weight, otherImpl.m11, this.m11);
        d.m21 = java.lang.Math.fma(weight, otherImpl.m21, this.m21);
        d.m31 = java.lang.Math.fma(weight, otherImpl.m31, this.m31);
        return d;
    }

    /** {@code addScaled} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x2 addScaled_mulAdd(Double4x2R other, double weight, @Mutated Double4x2 dest) {
        Double4x2Impl otherImpl = (Double4x2Impl) other;
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = ((weight) * (otherImpl.m00) + (this.m00));
        d.m10 = ((weight) * (otherImpl.m10) + (this.m10));
        d.m20 = ((weight) * (otherImpl.m20) + (this.m20));
        d.m30 = ((weight) * (otherImpl.m30) + (this.m30));
        d.m01 = ((weight) * (otherImpl.m01) + (this.m01));
        d.m11 = ((weight) * (otherImpl.m11) + (this.m11));
        d.m21 = ((weight) * (otherImpl.m21) + (this.m21));
        d.m31 = ((weight) * (otherImpl.m31) + (this.m31));
        return d;
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
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4x2Impl d = (Double4x2Impl) dest;
            d.m00 = java.lang.Math.fma(weight, m00, this.m00);
            d.m10 = java.lang.Math.fma(weight, m10, this.m10);
            d.m20 = java.lang.Math.fma(weight, m20, this.m20);
            d.m30 = java.lang.Math.fma(weight, m30, this.m30);
            d.m01 = java.lang.Math.fma(weight, m01, this.m01);
            d.m11 = java.lang.Math.fma(weight, m11, this.m11);
            d.m21 = java.lang.Math.fma(weight, m21, this.m21);
            d.m31 = java.lang.Math.fma(weight, m31, this.m31);
            return d;
        } else {
            Double4x2Impl d = (Double4x2Impl) dest;
            d.m00 = ((weight) * (m00) + (this.m00));
            d.m10 = ((weight) * (m10) + (this.m10));
            d.m20 = ((weight) * (m20) + (this.m20));
            d.m30 = ((weight) * (m30) + (this.m30));
            d.m01 = ((weight) * (m01) + (this.m01));
            d.m11 = ((weight) * (m11) + (this.m11));
            d.m21 = ((weight) * (m21) + (this.m21));
            d.m31 = ((weight) * (m31) + (this.m31));
            return d;
        }
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
    public Double4 mul(Double2R v, @Mutated Double4 dest) {
        Double2Impl vImpl = (Double2Impl) v;
        double vX = vImpl.x;
        double vY = vImpl.y;
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            d.x = java.lang.Math.fma(this.m00, vX, this.m01 * vY);
            d.y = java.lang.Math.fma(this.m10, vX, this.m11 * vY);
            d.z = java.lang.Math.fma(this.m20, vX, this.m21 * vY);
            d.w = java.lang.Math.fma(this.m30, vX, this.m31 * vY);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            d.x = ((this.m00) * (vX) + (this.m01 * vY));
            d.y = ((this.m10) * (vX) + (this.m11 * vY));
            d.z = ((this.m20) * (vX) + (this.m21 * vY));
            d.w = ((this.m30) * (vX) + (this.m31 * vY));
            return d;
        }
    }

    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v}, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @param dest will hold the result
     * @return dest
     */
    public Double4 mul(double vX, double vY, @Mutated Double4 dest) {
        if (org.joml2.internal.FmaSwitch.ON) {
            Double4Impl d = (Double4Impl) dest;
            d.x = java.lang.Math.fma(this.m00, vX, this.m01 * vY);
            d.y = java.lang.Math.fma(this.m10, vX, this.m11 * vY);
            d.z = java.lang.Math.fma(this.m20, vX, this.m21 * vY);
            d.w = java.lang.Math.fma(this.m30, vX, this.m31 * vY);
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            d.x = ((this.m00) * (vX) + (this.m01 * vY));
            d.y = ((this.m10) * (vX) + (this.m11 * vY));
            d.z = ((this.m20) * (vX) + (this.m21 * vY));
            d.w = ((this.m30) * (vX) + (this.m31 * vY));
            return d;
        }
    }

    public double m00() { return this.m00; }
    public double m01() { return this.m01; }
    public double m10() { return this.m10; }
    public double m11() { return this.m11; }
    public double m20() { return this.m20; }
    public double m21() { return this.m21; }
    public double m30() { return this.m30; }
    public double m31() { return this.m31; }

    @Override public String toString() {
        return "Double4x2(\n    " + this.m00 + ", " + this.m01 + "\n    " + this.m10 + ", " + this.m11 + "\n    " + this.m20 + ", " + this.m21 + "\n    " + this.m30 + ", " + this.m31 + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double4x2Impl)) return false;
        Double4x2Impl o = (Double4x2Impl) obj;
        return Double.doubleToLongBits(m00) == Double.doubleToLongBits(o.m00)
            && Double.doubleToLongBits(m01) == Double.doubleToLongBits(o.m01)
            && Double.doubleToLongBits(m10) == Double.doubleToLongBits(o.m10)
            && Double.doubleToLongBits(m11) == Double.doubleToLongBits(o.m11)
            && Double.doubleToLongBits(m20) == Double.doubleToLongBits(o.m20)
            && Double.doubleToLongBits(m21) == Double.doubleToLongBits(o.m21)
            && Double.doubleToLongBits(m30) == Double.doubleToLongBits(o.m30)
            && Double.doubleToLongBits(m31) == Double.doubleToLongBits(o.m31);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int) (Double.doubleToLongBits(m00) ^ (Double.doubleToLongBits(m00) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m01) ^ (Double.doubleToLongBits(m01) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m10) ^ (Double.doubleToLongBits(m10) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m11) ^ (Double.doubleToLongBits(m11) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m20) ^ (Double.doubleToLongBits(m20) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m21) ^ (Double.doubleToLongBits(m21) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m30) ^ (Double.doubleToLongBits(m30) >>> 32));
        h = 31 * h + (int) (Double.doubleToLongBits(m31) ^ (Double.doubleToLongBits(m31) >>> 32));
        return h;
    }

    @Override public boolean isFinite() {
        return Double.isFinite(m00)
            && Double.isFinite(m01)
            && Double.isFinite(m10)
            && Double.isFinite(m11)
            && Double.isFinite(m20)
            && Double.isFinite(m21)
            && Double.isFinite(m30)
            && Double.isFinite(m31);
    }

    @Override public boolean isNaN() {
        return Double.isNaN(m00)
            || Double.isNaN(m01)
            || Double.isNaN(m10)
            || Double.isNaN(m11)
            || Double.isNaN(m20)
            || Double.isNaN(m21)
            || Double.isNaN(m30)
            || Double.isNaN(m31);
    }

    @Override public boolean equalsEpsilon(Double4x2R other, double epsilon) {
        Double4x2Impl otherImpl = (Double4x2Impl) other;
        return java.lang.Math.abs(m00 - otherImpl.m00) <= epsilon
            && java.lang.Math.abs(m01 - otherImpl.m01) <= epsilon
            && java.lang.Math.abs(m10 - otherImpl.m10) <= epsilon
            && java.lang.Math.abs(m11 - otherImpl.m11) <= epsilon
            && java.lang.Math.abs(m20 - otherImpl.m20) <= epsilon
            && java.lang.Math.abs(m21 - otherImpl.m21) <= epsilon
            && java.lang.Math.abs(m30 - otherImpl.m30) <= epsilon
            && java.lang.Math.abs(m31 - otherImpl.m31) <= epsilon;
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[offset + 3] = this.m30;
        dest[offset + 4] = this.m01;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = this.m21;
        dest[offset + 7] = this.m31;
        return dest;
    }

    @Mutated public Double4x2 loadCM(double[] src, int offset) {
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m20 = src[offset + 2];
        this.m30 = src[offset + 3];
        this.m01 = src[offset + 4];
        this.m11 = src[offset + 5];
        this.m21 = src[offset + 6];
        this.m31 = src[offset + 7];
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

    @Mutated public Double4x2 loadCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double4x2 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }

    @Mutated public Double4x2 loadCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return this;
    }

    public ByteBuffer storeCM(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCMAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }

    public ByteBuffer storeCMRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }

    @Mutated public Double4x2 loadCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double4x2 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }

    @Mutated public Double4x2 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return this;
    }

    public Double4x2 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }

    @Mutated public Double4x2 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }

    public MemorySegment storeCM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest); }

    public MemorySegment storeCM(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest);
    }

    @Mutated public Double4x2 loadCM(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src); }

    @Mutated public Double4x2 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src);
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) this.m20;
        dest[offset + 3] = (float) this.m30;
        dest[offset + 4] = (float) this.m01;
        dest[offset + 5] = (float) this.m11;
        dest[offset + 6] = (float) this.m21;
        dest[offset + 7] = (float) this.m31;
        return dest;
    }

    @Mutated public Double4x2 loadCM(float[] src, int offset) {
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m20 = src[offset + 2];
        this.m30 = src[offset + 3];
        this.m01 = src[offset + 4];
        this.m11 = src[offset + 5];
        this.m21 = src[offset + 6];
        this.m31 = src[offset + 7];
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

    @Mutated public Double4x2 loadCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double4x2 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }

    @Mutated public Double4x2 loadCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return this;
    }

    public ByteBuffer storeCMFloat(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeCMFloatAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf);
    }

    public ByteBuffer storeCMFloatRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    @Mutated public Double4x2 loadCMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double4x2 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf);
    }

    @Mutated public Double4x2 loadCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMFloatAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return this;
    }

    public Double4x2 storeCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address);
    }

    @Mutated public Double4x2 loadCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address);
    }

    public MemorySegment storeCMFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeCMFloat(this, 0L, dest); }

    public MemorySegment storeCMFloat(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest);
    }

    @Mutated public Double4x2 loadCMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMFloat(this, 0L, src); }

    @Mutated public Double4x2 loadCMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m10;
        dest[offset + 3] = this.m11;
        dest[offset + 4] = this.m20;
        dest[offset + 5] = this.m21;
        dest[offset + 6] = this.m30;
        dest[offset + 7] = this.m31;
        return dest;
    }

    @Mutated public Double4x2 loadRM(double[] src, int offset) {
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m10 = src[offset + 2];
        this.m11 = src[offset + 3];
        this.m20 = src[offset + 4];
        this.m21 = src[offset + 5];
        this.m30 = src[offset + 6];
        this.m31 = src[offset + 7];
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

    @Mutated public Double4x2 loadRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double4x2 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }

    @Mutated public Double4x2 loadRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return this;
    }

    public ByteBuffer storeRM(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRMAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }

    public ByteBuffer storeRMRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }

    @Mutated public Double4x2 loadRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double4x2 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }

    @Mutated public Double4x2 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return this;
    }

    public Double4x2 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }

    @Mutated public Double4x2 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }

    public MemorySegment storeRM(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest); }

    public MemorySegment storeRM(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
    }

    @Mutated public Double4x2 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src); }

    @Mutated public Double4x2 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) this.m10;
        dest[offset + 3] = (float) this.m11;
        dest[offset + 4] = (float) this.m20;
        dest[offset + 5] = (float) this.m21;
        dest[offset + 6] = (float) this.m30;
        dest[offset + 7] = (float) this.m31;
        return dest;
    }

    @Mutated public Double4x2 loadRM(float[] src, int offset) {
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m10 = src[offset + 2];
        this.m11 = src[offset + 3];
        this.m20 = src[offset + 4];
        this.m21 = src[offset + 5];
        this.m30 = src[offset + 6];
        this.m31 = src[offset + 7];
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

    @Mutated public Double4x2 loadRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double4x2 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }

    @Mutated public Double4x2 loadRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf);
        buf.position(pos + 8);
        return this;
    }

    public ByteBuffer storeRMFloat(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeRMFloatAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf);
    }

    public ByteBuffer storeRMFloatRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    @Mutated public Double4x2 loadRMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, buf.position(), buf);
    }

    @Mutated public Double4x2 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf);
    }

    @Mutated public Double4x2 loadRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMFloatAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return this;
    }

    public Double4x2 storeRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address);
    }

    @Mutated public Double4x2 loadRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address);
    }

    public MemorySegment storeRMFloat(@Mutated MemorySegment dest) { return StoreLoad.SEG_OPS.storeRMFloat(this, 0L, dest); }

    public MemorySegment storeRMFloat(long offset, @Mutated MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest);
    }

    @Mutated public Double4x2 loadRMFloat(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMFloat(this, 0L, src); }

    @Mutated public Double4x2 loadRMFloat(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src);
    }

    public double[] storeCM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[offset + 3] = this.m30;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m21;
        dest[_p1 + 3] = this.m31;
        return dest;
    }

    @Mutated public Double4x2 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m20 = src[offset + 2];
        this.m30 = src[offset + 3];
        this.m01 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m21 = src[_p1 + 2];
        this.m31 = src[_p1 + 3];
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

    @Mutated public Double4x2 loadCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Double4x2 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }

    @Mutated public Double4x2 loadCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return this;
    }

    public ByteBuffer storeCM(@Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }

    public ByteBuffer storeCMAbsolute(int index, @Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }

    public ByteBuffer storeCMRelative(@Mutated ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 8);
        return buf;
    }

    @Mutated public Double4x2 loadCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Double4x2 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }

    @Mutated public Double4x2 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 8);
        return this;
    }

    public Double4x2 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }

    @Mutated public Double4x2 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
    }

    public MemorySegment storeCM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest, stride); }

    public MemorySegment storeCM(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
    }

    @Mutated public Double4x2 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(this, 0L, src, stride); }

    @Mutated public Double4x2 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src, stride);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) this.m20;
        dest[offset + 3] = (float) this.m30;
        dest[_p1] = (float) this.m01;
        dest[_p1 + 1] = (float) this.m11;
        dest[_p1 + 2] = (float) this.m21;
        dest[_p1 + 3] = (float) this.m31;
        return dest;
    }

    @Mutated public Double4x2 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m20 = src[offset + 2];
        this.m30 = src[offset + 3];
        this.m01 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m21 = src[_p1 + 2];
        this.m31 = src[_p1 + 3];
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

    @Mutated public Double4x2 loadCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Double4x2 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }

    @Mutated public Double4x2 loadCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride);
        return this;
    }

    public ByteBuffer storeCMFloat(@Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf, stride);
    }

    public ByteBuffer storeCMFloatAbsolute(int index, @Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf, stride);
    }

    public ByteBuffer storeCMFloatRelative(@Mutated ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 4);
        return buf;
    }

    @Mutated public Double4x2 loadCMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Double4x2 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(this, index, buf, stride);
    }

    @Mutated public Double4x2 loadCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadCMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + 2 * stride * 4);
        return this;
    }

    public Double4x2 storeCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address, stride);
    }

    @Mutated public Double4x2 loadCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(this, address, stride);
    }

    public MemorySegment storeCMFloat(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCMFloat(this, 0L, dest, stride); }

    public MemorySegment storeCMFloat(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMFloat(this, offset, dest, stride);
    }

    @Mutated public Double4x2 loadCMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMFloat(this, 0L, src, stride); }

    @Mutated public Double4x2 loadCMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCMFloat(this, offset, src, stride);
    }

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        dest[_p2] = this.m20;
        dest[_p2 + 1] = this.m21;
        dest[_p3] = this.m30;
        dest[_p3 + 1] = this.m31;
        return dest;
    }

    @Mutated public Double4x2 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m10 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m20 = src[_p2];
        this.m21 = src[_p2 + 1];
        this.m30 = src[_p3];
        this.m31 = src[_p3 + 1];
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

    @Mutated public Double4x2 loadRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Double4x2 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }

    @Mutated public Double4x2 loadRMRelative(DoubleBuffer buf, int stride) {
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
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride * 8);
        return buf;
    }

    @Mutated public Double4x2 loadRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Double4x2 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }

    @Mutated public Double4x2 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride * 8);
        return this;
    }

    public Double4x2 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }

    @Mutated public Double4x2 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
    }

    public MemorySegment storeRM(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest, stride); }

    public MemorySegment storeRM(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
    }

    @Mutated public Double4x2 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(this, 0L, src, stride); }

    @Mutated public Double4x2 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(this, offset, src, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[_p1] = (float) this.m10;
        dest[_p1 + 1] = (float) this.m11;
        dest[_p2] = (float) this.m20;
        dest[_p2 + 1] = (float) this.m21;
        dest[_p3] = (float) this.m30;
        dest[_p3 + 1] = (float) this.m31;
        return dest;
    }

    @Mutated public Double4x2 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m10 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m20 = src[_p2];
        this.m21 = src[_p2 + 1];
        this.m30 = src[_p3];
        this.m31 = src[_p3 + 1];
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

    @Mutated public Double4x2 loadRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Double4x2 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }

    @Mutated public Double4x2 loadRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride);
        return this;
    }

    public ByteBuffer storeRMFloat(@Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf, stride);
    }

    public ByteBuffer storeRMFloatAbsolute(int index, @Mutated ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf, stride);
    }

    public ByteBuffer storeRMFloatRelative(@Mutated ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride * 4);
        return buf;
    }

    @Mutated public Double4x2 loadRMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, buf.position(), buf, stride);
    }

    @Mutated public Double4x2 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(this, index, buf, stride);
    }

    @Mutated public Double4x2 loadRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 4L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadRMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + 4 * stride * 4);
        return this;
    }

    public Double4x2 storeRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address, stride);
    }

    @Mutated public Double4x2 loadRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(this, address, stride);
    }

    public MemorySegment storeRMFloat(@Mutated MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRMFloat(this, 0L, dest, stride); }

    public MemorySegment storeRMFloat(long offset, @Mutated MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMFloat(this, offset, dest, stride);
    }

    @Mutated public Double4x2 loadRMFloat(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMFloat(this, 0L, src, stride); }

    @Mutated public Double4x2 loadRMFloat(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMFloat(this, offset, src, stride);
    }
}
