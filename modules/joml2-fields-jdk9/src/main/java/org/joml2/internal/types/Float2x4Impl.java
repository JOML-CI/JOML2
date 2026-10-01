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
 * Generated implementation of {@link Float2x4} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Float2x4Impl implements Float2x4 {

    public float m00;
    public float m01;
    public float m02;
    public float m03;
    public float m10;
    public float m11;
    public float m12;
    public float m13;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Float2x4BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float2x4BbOpsUnsafe()
                        : new Float2x4BbOpsApi();
        static final Float2x4RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float2x4RawOpsUnsafe()
                        : new Float2x4RawOpsApi();
    }

    public Float2x4Impl() {
        m00 = 1;
        m11 = 1;
    }

    public Float2x4Impl(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
    }

    public Float2x4Impl(Float2x4R src) {
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
    public Float2 getColumn(int col, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        float _idxSw0;
        float _idxSw1;
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
    public Double2 getColumn(int col, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        float _idxSw2;
        float _idxSw3;
        switch (col) {
            case 0: _idxSw2 = this.m00; _idxSw3 = this.m10; break;
            case 1: _idxSw2 = this.m01; _idxSw3 = this.m11; break;
            case 2: _idxSw2 = this.m02; _idxSw3 = this.m12; break;
            case 3: _idxSw2 = this.m03; _idxSw3 = this.m13; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw2;
        d.y = _idxSw3;
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
    public Float4 getRow(int row, @Mutated Float4 dest) {
        Float4Impl d = (Float4Impl) dest;
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        float _idxSw3;
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
    public Double4 getRow(int row, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        float _idxSw4;
        float _idxSw5;
        float _idxSw6;
        float _idxSw7;
        switch (row) {
            case 0: _idxSw4 = this.m00; _idxSw5 = this.m01; _idxSw6 = this.m02; _idxSw7 = this.m03; break;
            case 1: _idxSw4 = this.m10; _idxSw5 = this.m11; _idxSw6 = this.m12; _idxSw7 = this.m13; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw4;
        d.y = _idxSw5;
        d.z = _idxSw6;
        d.w = _idxSw7;
        return d;
    }


    /**
     * Compute the Frobenius norm of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Frobenius norm of this matrix
     */
    public float frobeniusNorm() {
        return (float) Math.sqrt(Math.fma(this.m00, this.m00, this.m01 * this.m01) + Math.fma(this.m02, this.m02, this.m03 * this.m03) + (Math.fma(this.m10, this.m10, this.m11 * this.m11) + Math.fma(this.m12, this.m12, this.m13 * this.m13)));
    }


    /**
     * Transpose this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float4x2 transpose(@Mutated Float4x2 dest) {
        Float4x2Impl d = (Float4x2Impl) dest;
        d.m00 = this.m00;
        float _buf0 = this.m01;
        float _buf1 = this.m02;
        float _buf2 = this.m03;
        d.m01 = this.m10;
        d.m11 = this.m11;
        d.m21 = this.m12;
        d.m31 = this.m13;
        d.m10 = _buf0;
        d.m20 = _buf1;
        d.m30 = _buf2;
        return d;
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
    public Double4x2 transpose(@Mutated Double4x2 dest) {
        Double4x2Impl d = (Double4x2Impl) dest;
        d.m00 = this.m00;
        float _buf0 = this.m01;
        float _buf1 = this.m02;
        float _buf2 = this.m03;
        d.m01 = this.m10;
        d.m11 = this.m11;
        d.m21 = this.m12;
        d.m31 = this.m13;
        d.m10 = _buf0;
        d.m20 = _buf1;
        d.m30 = _buf2;
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
    public Float2x4 add(Float2x4R other, @Mutated Float2x4 dest) {
        Float2x4Impl d = (Float2x4Impl) dest;
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
    public Double2x4 add(Float2x4R other, @Mutated Double2x4 dest) {
        return add(other.m00(), other.m01(), other.m02(), other.m03(), other.m10(), other.m11(), other.m12(), other.m13(), dest);
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
    public Float2x4 add(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, @Mutated Float2x4 dest) {
        Float2x4Impl d = (Float2x4Impl) dest;
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
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}) to this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double2x4 add(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, @Mutated Double2x4 dest) {
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
    public Float2x4 mul(float scalar, @Mutated Float2x4 dest) {
        Float2x4Impl d = (Float2x4Impl) dest;
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
    public Double2x4 mul(float scalar, @Mutated Double2x4 dest) {
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
    public Float2x4 negate(@Mutated Float2x4 dest) {
        Float2x4Impl d = (Float2x4Impl) dest;
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
    public Float2x4 sub(Float2x4R other, @Mutated Float2x4 dest) {
        Float2x4Impl d = (Float2x4Impl) dest;
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
    public Double2x4 sub(Float2x4R other, @Mutated Double2x4 dest) {
        return sub(other.m00(), other.m01(), other.m02(), other.m03(), other.m10(), other.m11(), other.m12(), other.m13(), dest);
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
    public Float2x4 sub(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, @Mutated Float2x4 dest) {
        Float2x4Impl d = (Float2x4Impl) dest;
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
     * Subtract ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}) from this matrix and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double2x4 sub(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, @Mutated Double2x4 dest) {
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
    @Mutated public Float2x4 set(Float2x4R v) {
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
    @Mutated public Float2x4 set(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13) {
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
     * Convert this matrix to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Double2x4 toDouble(@Mutated Double2x4 dest) {
        Double2x4Impl d = (Double2x4Impl) dest;
        d.m00 = this.m00;
        d.m01 = this.m01;
        d.m02 = this.m02;
        d.m03 = this.m03;
        d.m10 = this.m10;
        d.m11 = this.m11;
        d.m12 = this.m12;
        d.m13 = this.m13;
        return d;
    }


    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Float2x4 makeIdentity() {
        this.m00 = 1.0f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m03 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = 1.0f;
        this.m12 = 0.0f;
        this.m13 = 0.0f;
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
    public Float2x4 lerp(Float2x4R other, float t, @Mutated Float2x4 dest) {
        Float2x4Impl d = (Float2x4Impl) dest;
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
    public Double2x4 lerp(Float2x4R other, float t, @Mutated Double2x4 dest) {
        return lerp(other.m00(), other.m01(), other.m02(), other.m03(), other.m10(), other.m11(), other.m12(), other.m13(), t, dest);
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
    public Float2x4 lerp(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float t, @Mutated Float2x4 dest) {
        Float2x4Impl d = (Float2x4Impl) dest;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double2x4 lerp(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float t, @Mutated Double2x4 dest) {
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
    public Float2x4 mul(Float2x4R right, @Mutated Float2x4 dest) {
        Float2x4Impl d = (Float2x4Impl) dest;
        float _buf0 = Math.fma(right.m00(), this.m00, right.m10() * this.m01);
        float _buf1 = Math.fma(right.m01(), this.m00, right.m11() * this.m01);
        float _buf2 = Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02));
        float _buf3 = Math.fma(right.m03(), this.m00, Math.fma(right.m13(), this.m01, this.m03));
        float _buf4 = Math.fma(right.m00(), this.m10, right.m10() * this.m11);
        float _buf5 = Math.fma(right.m01(), this.m10, right.m11() * this.m11);
        d.m12 = Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12));
        d.m13 = Math.fma(right.m03(), this.m10, Math.fma(right.m13(), this.m11, this.m13));
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.m03 = _buf3;
        d.m10 = _buf4;
        d.m11 = _buf5;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @param dest will hold the result
     * @return dest
     */
    public Double2x4 mul(Float2x4R right, @Mutated Double2x4 dest) {
        return mul(right.m00(), right.m01(), right.m02(), right.m03(), right.m10(), right.m11(), right.m12(), right.m13(), dest);
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
    public Float2x4 mul(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, @Mutated Float2x4 dest) {
        Float2x4Impl d = (Float2x4Impl) dest;
        float _buf0 = Math.fma(m00, this.m00, m10 * this.m01);
        float _buf1 = Math.fma(m01, this.m00, m11 * this.m01);
        d.m02 = Math.fma(m02, this.m00, Math.fma(m12, this.m01, this.m02));
        d.m03 = Math.fma(m03, this.m00, Math.fma(m13, this.m01, this.m03));
        float _buf2 = Math.fma(m00, this.m10, m10 * this.m11);
        float _buf3 = Math.fma(m01, this.m10, m11 * this.m11);
        d.m12 = Math.fma(m02, this.m10, Math.fma(m12, this.m11, this.m12));
        d.m13 = Math.fma(m03, this.m10, Math.fma(m13, this.m11, this.m13));
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m10 = _buf2;
        d.m11 = _buf3;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double2x4 mul(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, @Mutated Double2x4 dest) {
        Double2x4Impl d = (Double2x4Impl) dest;
        float _buf0 = Math.fma(m00, this.m00, m10 * this.m01);
        float _buf1 = Math.fma(m01, this.m00, m11 * this.m01);
        d.m02 = Math.fma(m02, this.m00, Math.fma(m12, this.m01, this.m02));
        d.m03 = Math.fma(m03, this.m00, Math.fma(m13, this.m01, this.m03));
        float _buf2 = Math.fma(m00, this.m10, m10 * this.m11);
        float _buf3 = Math.fma(m01, this.m10, m11 * this.m11);
        d.m12 = Math.fma(m02, this.m10, Math.fma(m12, this.m11, this.m12));
        d.m13 = Math.fma(m03, this.m10, Math.fma(m13, this.m11, this.m13));
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m10 = _buf2;
        d.m11 = _buf3;
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
    public Float2x4 preMul(Float2x4R other, @Mutated Float2x4 dest) {
        Float2x4Impl d = (Float2x4Impl) dest;
        float _buf0 = Math.fma(other.m00(), this.m00, other.m01() * this.m10);
        float _buf1 = Math.fma(other.m00(), this.m01, other.m01() * this.m11);
        float _buf2 = Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02()));
        float _buf3 = Math.fma(other.m00(), this.m03, Math.fma(other.m01(), this.m13, other.m03()));
        float _buf4 = Math.fma(other.m10(), this.m00, other.m11() * this.m10);
        float _buf5 = Math.fma(other.m10(), this.m01, other.m11() * this.m11);
        d.m12 = Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12()));
        d.m13 = Math.fma(other.m10(), this.m03, Math.fma(other.m11(), this.m13, other.m13()));
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.m03 = _buf3;
        d.m10 = _buf4;
        d.m11 = _buf5;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    public Double2x4 preMul(Float2x4R other, @Mutated Double2x4 dest) {
        return preMul(other.m00(), other.m01(), other.m02(), other.m03(), other.m10(), other.m11(), other.m12(), other.m13(), dest);
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
    public Float2x4 preMul(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, @Mutated Float2x4 dest) {
        Float2x4Impl d = (Float2x4Impl) dest;
        float _buf0 = Math.fma(m00, this.m00, m01 * this.m10);
        float _buf1 = Math.fma(m00, this.m01, m01 * this.m11);
        float _buf2 = Math.fma(m00, this.m02, Math.fma(m01, this.m12, m02));
        float _buf3 = Math.fma(m00, this.m03, Math.fma(m01, this.m13, m03));
        d.m10 = Math.fma(m10, this.m00, m11 * this.m10);
        d.m11 = Math.fma(m10, this.m01, m11 * this.m11);
        d.m12 = Math.fma(m10, this.m02, Math.fma(m11, this.m12, m12));
        d.m13 = Math.fma(m10, this.m03, Math.fma(m11, this.m13, m13));
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.m03 = _buf3;
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double2x4 preMul(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, @Mutated Double2x4 dest) {
        Double2x4Impl d = (Double2x4Impl) dest;
        float _buf0 = Math.fma(m00, this.m00, m01 * this.m10);
        float _buf1 = Math.fma(m00, this.m01, m01 * this.m11);
        float _buf2 = Math.fma(m00, this.m02, Math.fma(m01, this.m12, m02));
        float _buf3 = Math.fma(m00, this.m03, Math.fma(m01, this.m13, m03));
        d.m10 = Math.fma(m10, this.m00, m11 * this.m10);
        d.m11 = Math.fma(m10, this.m01, m11 * this.m11);
        d.m12 = Math.fma(m10, this.m02, Math.fma(m11, this.m12, m12));
        d.m13 = Math.fma(m10, this.m03, Math.fma(m11, this.m13, m13));
        d.m00 = _buf0;
        d.m01 = _buf1;
        d.m02 = _buf2;
        d.m03 = _buf3;
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
    public Float2x4 addScaled(Float2x4R other, float weight, @Mutated Float2x4 dest) {
        Float2x4Impl d = (Float2x4Impl) dest;
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
    public Double2x4 addScaled(Float2x4R other, float weight, @Mutated Double2x4 dest) {
        return addScaled(other.m00(), other.m01(), other.m02(), other.m03(), other.m10(), other.m11(), other.m12(), other.m13(), weight, dest);
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
    public Float2x4 addScaled(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float weight, @Mutated Float2x4 dest) {
        Float2x4Impl d = (Float2x4Impl) dest;
        d.m00 = Math.fma(weight, m00, this.m00);
        d.m01 = Math.fma(weight, m01, this.m01);
        d.m02 = Math.fma(weight, m02, this.m02);
        d.m03 = Math.fma(weight, m03, this.m03);
        d.m10 = Math.fma(weight, m10, this.m10);
        d.m11 = Math.fma(weight, m11, this.m11);
        d.m12 = Math.fma(weight, m12, this.m12);
        d.m13 = Math.fma(weight, m13, this.m13);
        return d;
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}) scaled by {@code weight} to this matrix and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
    public Double2x4 addScaled(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float weight, @Mutated Double2x4 dest) {
        Double2x4Impl d = (Double2x4Impl) dest;
        d.m00 = Math.fma(weight, m00, this.m00);
        d.m01 = Math.fma(weight, m01, this.m01);
        d.m02 = Math.fma(weight, m02, this.m02);
        d.m03 = Math.fma(weight, m03, this.m03);
        d.m10 = Math.fma(weight, m10, this.m10);
        d.m11 = Math.fma(weight, m11, this.m11);
        d.m12 = Math.fma(weight, m12, this.m12);
        d.m13 = Math.fma(weight, m13, this.m13);
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
    public Float2 mul(Float4R v, @Mutated Float2 dest) {
        return mul(v.x(), v.y(), v.z(), v.w(), dest);
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
    public Double2 mul(Float4R v, @Mutated Double2 dest) {
        return mul(v.x(), v.y(), v.z(), v.w(), dest);
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
    public Float2 mul(float vX, float vY, float vZ, float vW, @Mutated Float2 dest) {
        Float2Impl d = (Float2Impl) dest;
        d.x = Math.fma(this.m03, vW, Math.fma(this.m02, vZ, Math.fma(this.m00, vX, this.m01 * vY)));
        d.y = Math.fma(this.m13, vW, Math.fma(this.m12, vZ, Math.fma(this.m10, vX, this.m11 * vY)));
        return d;
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
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vW the {@code w} component of the vector {@code (vX, vY, vZ, vW)}
     * @param dest will hold the result
     * @return dest
     */
    public Double2 mul(float vX, float vY, float vZ, float vW, @Mutated Double2 dest) {
        Double2Impl d = (Double2Impl) dest;
        d.x = Math.fma(this.m03, vW, Math.fma(this.m02, vZ, Math.fma(this.m00, vX, this.m01 * vY)));
        d.y = Math.fma(this.m13, vW, Math.fma(this.m12, vZ, Math.fma(this.m10, vX, this.m11 * vY)));
        return d;
    }

    public float m00() { return this.m00; }
    public float m01() { return this.m01; }
    public float m02() { return this.m02; }
    public float m03() { return this.m03; }
    public float m10() { return this.m10; }
    public float m11() { return this.m11; }
    public float m12() { return this.m12; }
    public float m13() { return this.m13; }

    @Override public String toString() {
        return "Float2x4(\n    " + m00() + ", " + m01() + ", " + m02() + ", " + m03() + "\n    " + m10() + ", " + m11() + ", " + m12() + ", " + m13() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float2x4Impl)) return false;
        Float2x4Impl o = (Float2x4Impl) obj;
        return Float.floatToIntBits(m00) == Float.floatToIntBits(o.m00)
            && Float.floatToIntBits(m01) == Float.floatToIntBits(o.m01)
            && Float.floatToIntBits(m02) == Float.floatToIntBits(o.m02)
            && Float.floatToIntBits(m03) == Float.floatToIntBits(o.m03)
            && Float.floatToIntBits(m10) == Float.floatToIntBits(o.m10)
            && Float.floatToIntBits(m11) == Float.floatToIntBits(o.m11)
            && Float.floatToIntBits(m12) == Float.floatToIntBits(o.m12)
            && Float.floatToIntBits(m13) == Float.floatToIntBits(o.m13);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(m00);
        h = 31 * h + Float.floatToIntBits(m01);
        h = 31 * h + Float.floatToIntBits(m02);
        h = 31 * h + Float.floatToIntBits(m03);
        h = 31 * h + Float.floatToIntBits(m10);
        h = 31 * h + Float.floatToIntBits(m11);
        h = 31 * h + Float.floatToIntBits(m12);
        h = 31 * h + Float.floatToIntBits(m13);
        return h;
    }

    @Override public boolean isFinite() {
        return Float.isFinite(m00)
            && Float.isFinite(m01)
            && Float.isFinite(m02)
            && Float.isFinite(m03)
            && Float.isFinite(m10)
            && Float.isFinite(m11)
            && Float.isFinite(m12)
            && Float.isFinite(m13);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(m00)
            || Float.isNaN(m01)
            || Float.isNaN(m02)
            || Float.isNaN(m03)
            || Float.isNaN(m10)
            || Float.isNaN(m11)
            || Float.isNaN(m12)
            || Float.isNaN(m13);
    }

    @Override public boolean equalsEpsilon(Float2x4R other, float epsilon) {
        return Math.abs(m00 - other.m00()) <= epsilon
            && Math.abs(m01 - other.m01()) <= epsilon
            && Math.abs(m02 - other.m02()) <= epsilon
            && Math.abs(m03 - other.m03()) <= epsilon
            && Math.abs(m10 - other.m10()) <= epsilon
            && Math.abs(m11 - other.m11()) <= epsilon
            && Math.abs(m12 - other.m12()) <= epsilon
            && Math.abs(m13 - other.m13()) <= epsilon;
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset + 0] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m01;
        dest[offset + 3] = this.m11;
        dest[offset + 4] = this.m02;
        dest[offset + 5] = this.m12;
        dest[offset + 6] = this.m03;
        dest[offset + 7] = this.m13;
        return dest;
    }
    public @Mutated Float2x4 loadCM(float[] src, int offset) {
        this.m00 = src[offset + 0];
        this.m10 = src[offset + 1];
        this.m01 = src[offset + 2];
        this.m11 = src[offset + 3];
        this.m02 = src[offset + 4];
        this.m12 = src[offset + 5];
        this.m03 = src[offset + 6];
        this.m13 = src[offset + 7];
        return this;
    }
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    @Mutated public Float2x4 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    public Float2x4 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public Float2x4 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }
    @Mutated public Float2x4 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address);
    }

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m01;
        dest[offset + 3] = this.m11;
        dest[offset + 4] = this.m02;
        dest[offset + 5] = this.m12;
        dest[offset + 6] = this.m03;
        dest[offset + 7] = this.m13;
        return dest;
    }
    public @Mutated Float2x4 loadCM(double[] src, int offset) {
        this.m00 = (float) src[offset + 0];
        this.m10 = (float) src[offset + 1];
        this.m01 = (float) src[offset + 2];
        this.m11 = (float) src[offset + 3];
        this.m02 = (float) src[offset + 4];
        this.m12 = (float) src[offset + 5];
        this.m03 = (float) src[offset + 6];
        this.m13 = (float) src[offset + 7];
        return this;
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }
    @Mutated public Float2x4 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf);
    }
    public ByteBuffer storeCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf);
    }
    public Float2x4 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf);
    }
    public Float2x4 storeCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address);
    }
    @Mutated public Float2x4 loadCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address);
    }

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset + 0] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m03;
        dest[offset + 4] = this.m10;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = this.m12;
        dest[offset + 7] = this.m13;
        return dest;
    }
    public @Mutated Float2x4 loadRM(float[] src, int offset) {
        this.m00 = src[offset + 0];
        this.m01 = src[offset + 1];
        this.m02 = src[offset + 2];
        this.m03 = src[offset + 3];
        this.m10 = src[offset + 4];
        this.m11 = src[offset + 5];
        this.m12 = src[offset + 6];
        this.m13 = src[offset + 7];
        return this;
    }
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    @Mutated public Float2x4 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    public Float2x4 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public Float2x4 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }
    @Mutated public Float2x4 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address);
    }

    public double[] storeRM(@Mutated double[] dest, int offset) {
        dest[offset + 0] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m03;
        dest[offset + 4] = this.m10;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = this.m12;
        dest[offset + 7] = this.m13;
        return dest;
    }
    public @Mutated Float2x4 loadRM(double[] src, int offset) {
        this.m00 = (float) src[offset + 0];
        this.m01 = (float) src[offset + 1];
        this.m02 = (float) src[offset + 2];
        this.m03 = (float) src[offset + 3];
        this.m10 = (float) src[offset + 4];
        this.m11 = (float) src[offset + 5];
        this.m12 = (float) src[offset + 6];
        this.m13 = (float) src[offset + 7];
        return this;
    }
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }
    @Mutated public Float2x4 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf);
    }
    public ByteBuffer storeRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf);
    }
    public Float2x4 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf);
    }
    public Float2x4 storeRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address);
    }
    @Mutated public Float2x4 loadRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address);
    }

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
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
    public @Mutated Float2x4 loadCM(float[] src, int offset, int stride) {
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
    public FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float2x4 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    public Float2x4 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public Float2x4 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
    }
    @Mutated public Float2x4 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(this, address, stride);
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
    public @Mutated Float2x4 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        this.m00 = (float) src[offset];
        this.m10 = (float) src[offset + 1];
        this.m01 = (float) src[_p1];
        this.m11 = (float) src[_p1 + 1];
        this.m02 = (float) src[_p2];
        this.m12 = (float) src[_p2 + 1];
        this.m03 = (float) src[_p3];
        this.m13 = (float) src[_p3 + 1];
        return this;
    }
    public DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float2x4 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf, stride);
    }
    public Float2x4 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(this, index, buf, stride);
    }
    public Float2x4 storeCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address, stride);
    }
    @Mutated public Float2x4 loadCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(this, address, stride);
    }

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
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
    public @Mutated Float2x4 loadRM(float[] src, int offset, int stride) {
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
    public FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float2x4 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    public Float2x4 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public Float2x4 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }
    @Mutated public Float2x4 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(this, address, stride);
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
    public @Mutated Float2x4 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        this.m00 = (float) src[offset];
        this.m01 = (float) src[offset + 1];
        this.m02 = (float) src[offset + 2];
        this.m03 = (float) src[offset + 3];
        this.m10 = (float) src[_p1];
        this.m11 = (float) src[_p1 + 1];
        this.m12 = (float) src[_p1 + 2];
        this.m13 = (float) src[_p1 + 3];
        return this;
    }
    public DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }
    @Mutated public Float2x4 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(this, index, buf, stride);
    }
    public ByteBuffer storeRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf, stride);
    }
    public Float2x4 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(this, index, buf, stride);
    }
    public Float2x4 storeRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address, stride);
    }
    @Mutated public Float2x4 loadRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(this, address, stride);
    }

}
