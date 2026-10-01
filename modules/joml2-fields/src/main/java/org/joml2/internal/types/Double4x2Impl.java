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
        this.m00 = src.m00();
        this.m10 = src.m10();
        this.m20 = src.m20();
        this.m30 = src.m30();
        this.m01 = src.m01();
        this.m11 = src.m11();
        this.m21 = src.m21();
        this.m31 = src.m31();
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
        return Math.sqrt(Math.fma(this.m00, this.m00, this.m01 * this.m01) + Math.fma(this.m10, this.m10, this.m11 * this.m11) + (Math.fma(this.m20, this.m20, this.m21 * this.m21) + Math.fma(this.m30, this.m30, this.m31 * this.m31)));
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
        double _buf0 = this.m10;
        d.m02 = this.m20;
        double _buf1 = this.m30;
        d.m10 = this.m01;
        double _buf2 = this.m11;
        d.m12 = this.m21;
        d.m13 = this.m31;
        d.m01 = _buf0;
        d.m03 = _buf1;
        d.m11 = _buf2;
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
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = other.m00() + this.m00;
        d.m10 = other.m10() + this.m10;
        d.m20 = other.m20() + this.m20;
        d.m30 = other.m30() + this.m30;
        d.m01 = other.m01() + this.m01;
        d.m11 = other.m11() + this.m11;
        d.m21 = other.m21() + this.m21;
        d.m31 = other.m31() + this.m31;
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
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = this.m00 - other.m00();
        d.m10 = this.m10 - other.m10();
        d.m20 = this.m20 - other.m20();
        d.m30 = this.m30 - other.m30();
        d.m01 = this.m01 - other.m01();
        d.m11 = this.m11 - other.m11();
        d.m21 = this.m21 - other.m21();
        d.m31 = this.m31 - other.m31();
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
        this.m00 = v.m00();
        this.m10 = v.m10();
        this.m20 = v.m20();
        this.m30 = v.m30();
        this.m01 = v.m01();
        this.m11 = v.m11();
        this.m21 = v.m21();
        this.m31 = v.m31();
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
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = Math.fma(t, other.m00() - this.m00, this.m00);
        d.m10 = Math.fma(t, other.m10() - this.m10, this.m10);
        d.m20 = Math.fma(t, other.m20() - this.m20, this.m20);
        d.m30 = Math.fma(t, other.m30() - this.m30, this.m30);
        d.m01 = Math.fma(t, other.m01() - this.m01, this.m01);
        d.m11 = Math.fma(t, other.m11() - this.m11, this.m11);
        d.m21 = Math.fma(t, other.m21() - this.m21, this.m21);
        d.m31 = Math.fma(t, other.m31() - this.m31, this.m31);
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
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = Math.fma(t, m00 - this.m00, this.m00);
        d.m10 = Math.fma(t, m10 - this.m10, this.m10);
        d.m20 = Math.fma(t, m20 - this.m20, this.m20);
        d.m30 = Math.fma(t, m30 - this.m30, this.m30);
        d.m01 = Math.fma(t, m01 - this.m01, this.m01);
        d.m11 = Math.fma(t, m11 - this.m11, this.m11);
        d.m21 = Math.fma(t, m21 - this.m21, this.m21);
        d.m31 = Math.fma(t, m31 - this.m31, this.m31);
        return d;
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
        Double4x2Impl d = (Double4x2Impl) dest;
        double _buf0 = Math.fma(right.m00(), this.m00, right.m10() * this.m01);
        double _buf1 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        double _buf2 = Math.fma(right.m00(), this.m20, right.m10() * this.m21);
        double _buf3 = Math.fma(right.m00(), this.m30, right.m10() * this.m31);
        d.m01 = Math.fma(right.m01(), this.m00, right.m11() * this.m01);
        d.m11 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        d.m21 = Math.fma(right.m01(), this.m20, right.m11() * this.m21);
        d.m31 = Math.fma(right.m01(), this.m30, right.m11() * this.m31);
        d.m00 = _buf0;
        d.m10 = _buf1;
        d.m20 = _buf2;
        d.m30 = _buf3;
        return d;
    }

    /** Private column 0 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s5c316050_c0(Double4x2Impl _dst, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _r12, double _r13, double _r14, double _r15, double _r16, double _r17, double _r18, double _r19, double _r20, double _r21, double _r22, double _r23) {
        _dst.m00 = Math.fma(_r0, _r1, Math.fma(_r2, _r3, Math.fma(_r4, _r5, _r6 * _r7)));
        _dst.m10 = Math.fma(_r12, _r1, Math.fma(_r13, _r3, Math.fma(_r14, _r5, _r15 * _r7)));
        _dst.m20 = Math.fma(_r16, _r1, Math.fma(_r17, _r3, Math.fma(_r18, _r5, _r19 * _r7)));
        _dst.m30 = Math.fma(_r20, _r1, Math.fma(_r21, _r3, Math.fma(_r22, _r5, _r23 * _r7)));
    }

    /** Private column 1 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s5c316050_c1(Double4x2Impl _dst, double _r0, double _r8, double _r2, double _r9, double _r4, double _r10, double _r6, double _r11, double _r12, double _r13, double _r14, double _r15, double _r16, double _r17, double _r18, double _r19, double _r20, double _r21, double _r22, double _r23) {
        _dst.m01 = Math.fma(_r0, _r8, Math.fma(_r2, _r9, Math.fma(_r4, _r10, _r6 * _r11)));
        _dst.m11 = Math.fma(_r12, _r8, Math.fma(_r13, _r9, Math.fma(_r14, _r10, _r15 * _r11)));
        _dst.m21 = Math.fma(_r16, _r8, Math.fma(_r17, _r9, Math.fma(_r18, _r10, _r19 * _r11)));
        _dst.m31 = Math.fma(_r20, _r8, Math.fma(_r21, _r9, Math.fma(_r22, _r10, _r23 * _r11)));
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private void preMul_s5c316050_tail(Double4x2Impl _dst, Double4x4R other, double _r0, double _r1, double _r2, double _r3, double _r4, double _r5, double _r6, double _r7, double _r8, double _r9, double _r10, double _r11, double _r12, double _r13, double _r14, double _r15, double _r16, double _r17, double _r18, double _r19) {
        double _r20 = other.m33();
        double _r21 = other.m32();
        double _r22 = other.m30();
        double _r23 = other.m31();
        preMul_s5c316050_c0(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20, _r21, _r22, _r23);
        preMul_s5c316050_c1(_dst, _r0, _r8, _r2, _r9, _r4, _r10, _r6, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20, _r21, _r22, _r23);
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
        Double4x2Impl d = (Double4x2Impl) dest;
        double _r0 = other.m03();
        double _r1 = this.m30;
        double _r2 = other.m02();
        double _r3 = this.m20;
        double _r4 = other.m00();
        double _r5 = this.m00;
        double _r6 = other.m01();
        double _r7 = this.m10;
        double _r8 = this.m31;
        double _r9 = this.m21;
        double _r10 = this.m01;
        double _r11 = this.m11;
        double _r12 = other.m13();
        double _r13 = other.m12();
        double _r14 = other.m10();
        double _r15 = other.m11();
        double _r16 = other.m23();
        double _r17 = other.m22();
        double _r18 = other.m20();
        double _r19 = other.m21();
        preMul_s5c316050_tail(d, other, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19);
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
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = Math.fma(weight, other.m00(), this.m00);
        d.m10 = Math.fma(weight, other.m10(), this.m10);
        d.m20 = Math.fma(weight, other.m20(), this.m20);
        d.m30 = Math.fma(weight, other.m30(), this.m30);
        d.m01 = Math.fma(weight, other.m01(), this.m01);
        d.m11 = Math.fma(weight, other.m11(), this.m11);
        d.m21 = Math.fma(weight, other.m21(), this.m21);
        d.m31 = Math.fma(weight, other.m31(), this.m31);
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
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = Math.fma(weight, m00, this.m00);
        d.m10 = Math.fma(weight, m10, this.m10);
        d.m20 = Math.fma(weight, m20, this.m20);
        d.m30 = Math.fma(weight, m30, this.m30);
        d.m01 = Math.fma(weight, m01, this.m01);
        d.m11 = Math.fma(weight, m11, this.m11);
        d.m21 = Math.fma(weight, m21, this.m21);
        d.m31 = Math.fma(weight, m31, this.m31);
        return d;
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
        Double4Impl d = (Double4Impl) dest;
        d.x = Math.fma(this.m00, vX, this.m01 * vY);
        d.y = Math.fma(this.m10, vX, this.m11 * vY);
        d.z = Math.fma(this.m20, vX, this.m21 * vY);
        d.w = Math.fma(this.m30, vX, this.m31 * vY);
        return d;
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
        return "Double4x2(\n    " + m00() + ", " + m01() + "\n    " + m10() + ", " + m11() + "\n    " + m20() + ", " + m21() + "\n    " + m30() + ", " + m31() + "\n)";
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
        h = 31 * h + (int)(Double.doubleToLongBits(m00) ^ (Double.doubleToLongBits(m00) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m01) ^ (Double.doubleToLongBits(m01) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m10) ^ (Double.doubleToLongBits(m10) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m11) ^ (Double.doubleToLongBits(m11) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m20) ^ (Double.doubleToLongBits(m20) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m21) ^ (Double.doubleToLongBits(m21) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m30) ^ (Double.doubleToLongBits(m30) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m31) ^ (Double.doubleToLongBits(m31) >>> 32));
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
        return Math.abs(m00 - other.m00()) <= epsilon
            && Math.abs(m01 - other.m01()) <= epsilon
            && Math.abs(m10 - other.m10()) <= epsilon
            && Math.abs(m11 - other.m11()) <= epsilon
            && Math.abs(m20 - other.m20()) <= epsilon
            && Math.abs(m21 - other.m21()) <= epsilon
            && Math.abs(m30 - other.m30()) <= epsilon
            && Math.abs(m31 - other.m31()) <= epsilon;
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[offset + 3] = this.m30;
        dest[offset + 4] = this.m01;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = this.m21;
        dest[offset + 7] = this.m31;
        return dest;
    }
    public @Mutated Double4x2 loadCM(double[] src, int offset) {
        this.m00 = src[offset + 0];
        this.m10 = src[offset + 1];
        this.m20 = src[offset + 2];
        this.m30 = src[offset + 3];
        this.m01 = src[offset + 4];
        this.m11 = src[offset + 5];
        this.m21 = src[offset + 6];
        this.m31 = src[offset + 7];
        return this;
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    @Mutated public Double4x2 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    public Double4x2 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public Double4x2 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }
    @Mutated public Double4x2 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }
    public MemorySegment storeCM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest);
    }
    public Double4x2 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(this, offset, src);
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset + 0] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) this.m20;
        dest[offset + 3] = (float) this.m30;
        dest[offset + 4] = (float) this.m01;
        dest[offset + 5] = (float) this.m11;
        dest[offset + 6] = (float) this.m21;
        dest[offset + 7] = (float) this.m31;
        return dest;
    }
    public @Mutated Double4x2 loadCM(float[] src, int offset) {
        this.m00 = src[offset + 0];
        this.m10 = src[offset + 1];
        this.m20 = src[offset + 2];
        this.m30 = src[offset + 3];
        this.m01 = src[offset + 4];
        this.m11 = src[offset + 5];
        this.m21 = src[offset + 6];
        this.m31 = src[offset + 7];
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
        dest[offset + 0] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m10;
        dest[offset + 3] = this.m11;
        dest[offset + 4] = this.m20;
        dest[offset + 5] = this.m21;
        dest[offset + 6] = this.m30;
        dest[offset + 7] = this.m31;
        return dest;
    }
    public @Mutated Double4x2 loadRM(double[] src, int offset) {
        this.m00 = src[offset + 0];
        this.m01 = src[offset + 1];
        this.m10 = src[offset + 2];
        this.m11 = src[offset + 3];
        this.m20 = src[offset + 4];
        this.m21 = src[offset + 5];
        this.m30 = src[offset + 6];
        this.m31 = src[offset + 7];
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
        dest[offset + 0] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) this.m10;
        dest[offset + 3] = (float) this.m11;
        dest[offset + 4] = (float) this.m20;
        dest[offset + 5] = (float) this.m21;
        dest[offset + 6] = (float) this.m30;
        dest[offset + 7] = (float) this.m31;
        return dest;
    }
    public @Mutated Double4x2 loadRM(float[] src, int offset) {
        this.m00 = src[offset + 0];
        this.m01 = src[offset + 1];
        this.m10 = src[offset + 2];
        this.m11 = src[offset + 3];
        this.m20 = src[offset + 4];
        this.m21 = src[offset + 5];
        this.m30 = src[offset + 6];
        this.m31 = src[offset + 7];
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
    public @Mutated Double4x2 loadCM(double[] src, int offset, int stride) {
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
    public @Mutated Double4x2 loadCM(float[] src, int offset, int stride) {
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
    public @Mutated Double4x2 loadRM(double[] src, int offset, int stride) {
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
    public @Mutated Double4x2 loadRM(float[] src, int offset, int stride) {
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

}
