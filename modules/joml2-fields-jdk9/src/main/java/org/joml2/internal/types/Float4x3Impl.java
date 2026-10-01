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
 * Generated implementation of {@link Float4x3} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public class Float4x3Impl implements Float4x3 {

    public float m00;
    public float m10;
    public float m20;
    public float m30;
    public float m01;
    public float m11;
    public float m21;
    public float m31;
    public float m02;
    public float m12;
    public float m22;
    public float m32;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
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
        m00 = 1;
        m11 = 1;
        m22 = 1;
    }

    public Float4x3Impl(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float m30, float m31, float m32) {
        this.m00 = m00;
        this.m10 = m10;
        this.m20 = m20;
        this.m30 = m30;
        this.m01 = m01;
        this.m11 = m11;
        this.m21 = m21;
        this.m31 = m31;
        this.m02 = m02;
        this.m12 = m12;
        this.m22 = m22;
        this.m32 = m32;
    }

    public Float4x3Impl(Float4x3R src) {
        this.m00 = src.m00();
        this.m10 = src.m10();
        this.m20 = src.m20();
        this.m30 = src.m30();
        this.m01 = src.m01();
        this.m11 = src.m11();
        this.m21 = src.m21();
        this.m31 = src.m31();
        this.m02 = src.m02();
        this.m12 = src.m12();
        this.m22 = src.m22();
        this.m32 = src.m32();
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
        Float4Impl d = (Float4Impl) dest;
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        float _idxSw3;
        switch (col) {
            case 0: _idxSw0 = this.m00; _idxSw1 = this.m10; _idxSw2 = this.m20; _idxSw3 = this.m30; break;
            case 1: _idxSw0 = this.m01; _idxSw1 = this.m11; _idxSw2 = this.m21; _idxSw3 = this.m31; break;
            case 2: _idxSw0 = this.m02; _idxSw1 = this.m12; _idxSw2 = this.m22; _idxSw3 = this.m32; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw0;
        d.y = _idxSw1;
        d.z = _idxSw2;
        d.w = _idxSw3;
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
    public Double4 getColumn(int col, @Mutated Double4 dest) {
        Double4Impl d = (Double4Impl) dest;
        float _idxSw4;
        float _idxSw5;
        float _idxSw6;
        float _idxSw7;
        switch (col) {
            case 0: _idxSw4 = this.m00; _idxSw5 = this.m10; _idxSw6 = this.m20; _idxSw7 = this.m30; break;
            case 1: _idxSw4 = this.m01; _idxSw5 = this.m11; _idxSw6 = this.m21; _idxSw7 = this.m31; break;
            case 2: _idxSw4 = this.m02; _idxSw5 = this.m12; _idxSw6 = this.m22; _idxSw7 = this.m32; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        d.x = _idxSw4;
        d.y = _idxSw5;
        d.z = _idxSw6;
        d.w = _idxSw7;
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
    public Float3 getRow(int row, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (row) {
            case 0: _idxSw0 = this.m00; _idxSw1 = this.m01; _idxSw2 = this.m02; break;
            case 1: _idxSw0 = this.m10; _idxSw1 = this.m11; _idxSw2 = this.m12; break;
            case 2: _idxSw0 = this.m20; _idxSw1 = this.m21; _idxSw2 = this.m22; break;
            case 3: _idxSw0 = this.m30; _idxSw1 = this.m31; _idxSw2 = this.m32; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw0;
        d.y = _idxSw1;
        d.z = _idxSw2;
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
    public Double3 getRow(int row, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _idxSw3;
        float _idxSw4;
        float _idxSw5;
        switch (row) {
            case 0: _idxSw3 = this.m00; _idxSw4 = this.m01; _idxSw5 = this.m02; break;
            case 1: _idxSw3 = this.m10; _idxSw4 = this.m11; _idxSw5 = this.m12; break;
            case 2: _idxSw3 = this.m20; _idxSw4 = this.m21; _idxSw5 = this.m22; break;
            case 3: _idxSw3 = this.m30; _idxSw4 = this.m31; _idxSw5 = this.m32; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        d.x = _idxSw3;
        d.y = _idxSw4;
        d.z = _idxSw5;
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
        if (Math.useFma()) {
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(this.m00, this.m00, java.lang.Math.fma(this.m01, this.m01, this.m02 * this.m02)) + java.lang.Math.fma(this.m10, this.m10, java.lang.Math.fma(this.m11, this.m11, this.m12 * this.m12)) + (java.lang.Math.fma(this.m20, this.m20, java.lang.Math.fma(this.m21, this.m21, this.m22 * this.m22)) + java.lang.Math.fma(this.m30, this.m30, java.lang.Math.fma(this.m31, this.m31, this.m32 * this.m32))));
        } else {
            return (float) java.lang.Math.sqrt(((this.m00) * (this.m00) + (((this.m01) * (this.m01) + (this.m02 * this.m02)))) + ((this.m10) * (this.m10) + (((this.m11) * (this.m11) + (this.m12 * this.m12)))) + (((this.m20) * (this.m20) + (((this.m21) * (this.m21) + (this.m22 * this.m22)))) + ((this.m30) * (this.m30) + (((this.m31) * (this.m31) + (this.m32 * this.m32))))));
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
        Float3x4Impl d = (Float3x4Impl) dest;
        d.m00 = this.m00;
        d.m01 = this.m10;
        d.m02 = this.m20;
        d.m03 = this.m30;
        d.m10 = this.m01;
        d.m11 = this.m11;
        d.m12 = this.m21;
        d.m13 = this.m31;
        d.m20 = this.m02;
        d.m21 = this.m12;
        d.m22 = this.m22;
        d.m23 = this.m32;
        d.properties = Joml.BIT_AFFINE;
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
    public Double3x4 transpose(@Mutated Double3x4 dest) {
        Double3x4Impl d = (Double3x4Impl) dest;
        d.m00 = this.m00;
        d.m01 = this.m10;
        d.m02 = this.m20;
        d.m03 = this.m30;
        d.m10 = this.m01;
        d.m11 = this.m11;
        d.m12 = this.m21;
        d.m13 = this.m31;
        d.m20 = this.m02;
        d.m21 = this.m12;
        d.m22 = this.m22;
        d.m23 = this.m32;
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
    public Float4x3 add(Float4x3R other, @Mutated Float4x3 dest) {
        Float4x3Impl d = (Float4x3Impl) dest;
        d.m00 = other.m00() + this.m00;
        d.m10 = other.m10() + this.m10;
        d.m20 = other.m20() + this.m20;
        d.m30 = other.m30() + this.m30;
        d.m01 = other.m01() + this.m01;
        d.m11 = other.m11() + this.m11;
        d.m21 = other.m21() + this.m21;
        d.m31 = other.m31() + this.m31;
        d.m02 = other.m02() + this.m02;
        d.m12 = other.m12() + this.m12;
        d.m22 = other.m22() + this.m22;
        d.m32 = other.m32() + this.m32;
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
        Double4x3Impl d = (Double4x3Impl) dest;
        d.m00 = other.m00() + this.m00;
        d.m10 = m10 + this.m10;
        d.m20 = m20 + this.m20;
        d.m30 = m30 + this.m30;
        d.m01 = m01 + this.m01;
        d.m11 = m11 + this.m11;
        d.m21 = m21 + this.m21;
        d.m31 = m31 + this.m31;
        d.m02 = m02 + this.m02;
        d.m12 = m12 + this.m12;
        d.m22 = m22 + this.m22;
        d.m32 = m32 + this.m32;
        return d;
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
        Float4x3Impl d = (Float4x3Impl) dest;
        d.m00 = m00 + this.m00;
        d.m10 = m10 + this.m10;
        d.m20 = m20 + this.m20;
        d.m30 = m30 + this.m30;
        d.m01 = m01 + this.m01;
        d.m11 = m11 + this.m11;
        d.m21 = m21 + this.m21;
        d.m31 = m31 + this.m31;
        d.m02 = m02 + this.m02;
        d.m12 = m12 + this.m12;
        d.m22 = m22 + this.m22;
        d.m32 = m32 + this.m32;
        return d;
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
        Double4x3Impl d = (Double4x3Impl) dest;
        d.m00 = m00 + this.m00;
        d.m10 = m10 + this.m10;
        d.m20 = m20 + this.m20;
        d.m30 = m30 + this.m30;
        d.m01 = m01 + this.m01;
        d.m11 = m11 + this.m11;
        d.m21 = m21 + this.m21;
        d.m31 = m31 + this.m31;
        d.m02 = m02 + this.m02;
        d.m12 = m12 + this.m12;
        d.m22 = m22 + this.m22;
        d.m32 = m32 + this.m32;
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
    public Float4x3 mul(float scalar, @Mutated Float4x3 dest) {
        Float4x3Impl d = (Float4x3Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = scalar * this.m10;
        d.m20 = scalar * this.m20;
        d.m30 = scalar * this.m30;
        d.m01 = scalar * this.m01;
        d.m11 = scalar * this.m11;
        d.m21 = scalar * this.m21;
        d.m31 = scalar * this.m31;
        d.m02 = scalar * this.m02;
        d.m12 = scalar * this.m12;
        d.m22 = scalar * this.m22;
        d.m32 = scalar * this.m32;
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
    public Double4x3 mul(float scalar, @Mutated Double4x3 dest) {
        Double4x3Impl d = (Double4x3Impl) dest;
        d.m00 = scalar * this.m00;
        d.m10 = scalar * this.m10;
        d.m20 = scalar * this.m20;
        d.m30 = scalar * this.m30;
        d.m01 = scalar * this.m01;
        d.m11 = scalar * this.m11;
        d.m21 = scalar * this.m21;
        d.m31 = scalar * this.m31;
        d.m02 = scalar * this.m02;
        d.m12 = scalar * this.m12;
        d.m22 = scalar * this.m22;
        d.m32 = scalar * this.m32;
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
    public Float4x3 negate(@Mutated Float4x3 dest) {
        Float4x3Impl d = (Float4x3Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m20 = -this.m20;
        d.m30 = -this.m30;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.m21 = -this.m21;
        d.m31 = -this.m31;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -this.m22;
        d.m32 = -this.m32;
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
    public Double4x3 negate(@Mutated Double4x3 dest) {
        Double4x3Impl d = (Double4x3Impl) dest;
        d.m00 = -this.m00;
        d.m10 = -this.m10;
        d.m20 = -this.m20;
        d.m30 = -this.m30;
        d.m01 = -this.m01;
        d.m11 = -this.m11;
        d.m21 = -this.m21;
        d.m31 = -this.m31;
        d.m02 = -this.m02;
        d.m12 = -this.m12;
        d.m22 = -this.m22;
        d.m32 = -this.m32;
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
    public Float4x3 sub(Float4x3R other, @Mutated Float4x3 dest) {
        Float4x3Impl d = (Float4x3Impl) dest;
        d.m00 = this.m00 - other.m00();
        d.m10 = this.m10 - other.m10();
        d.m20 = this.m20 - other.m20();
        d.m30 = this.m30 - other.m30();
        d.m01 = this.m01 - other.m01();
        d.m11 = this.m11 - other.m11();
        d.m21 = this.m21 - other.m21();
        d.m31 = this.m31 - other.m31();
        d.m02 = this.m02 - other.m02();
        d.m12 = this.m12 - other.m12();
        d.m22 = this.m22 - other.m22();
        d.m32 = this.m32 - other.m32();
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
        Double4x3Impl d = (Double4x3Impl) dest;
        d.m00 = this.m00 - other.m00();
        d.m10 = this.m10 - m10;
        d.m20 = this.m20 - m20;
        d.m30 = this.m30 - m30;
        d.m01 = this.m01 - m01;
        d.m11 = this.m11 - m11;
        d.m21 = this.m21 - m21;
        d.m31 = this.m31 - m31;
        d.m02 = this.m02 - m02;
        d.m12 = this.m12 - m12;
        d.m22 = this.m22 - m22;
        d.m32 = this.m32 - m32;
        return d;
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
        Float4x3Impl d = (Float4x3Impl) dest;
        d.m00 = this.m00 - m00;
        d.m10 = this.m10 - m10;
        d.m20 = this.m20 - m20;
        d.m30 = this.m30 - m30;
        d.m01 = this.m01 - m01;
        d.m11 = this.m11 - m11;
        d.m21 = this.m21 - m21;
        d.m31 = this.m31 - m31;
        d.m02 = this.m02 - m02;
        d.m12 = this.m12 - m12;
        d.m22 = this.m22 - m22;
        d.m32 = this.m32 - m32;
        return d;
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
        Double4x3Impl d = (Double4x3Impl) dest;
        d.m00 = this.m00 - m00;
        d.m10 = this.m10 - m10;
        d.m20 = this.m20 - m20;
        d.m30 = this.m30 - m30;
        d.m01 = this.m01 - m01;
        d.m11 = this.m11 - m11;
        d.m21 = this.m21 - m21;
        d.m31 = this.m31 - m31;
        d.m02 = this.m02 - m02;
        d.m12 = this.m12 - m12;
        d.m22 = this.m22 - m22;
        d.m32 = this.m32 - m32;
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
    @Mutated public Float4x3 set(Float4x3R v) {
        this.m00 = v.m00();
        this.m10 = v.m10();
        this.m20 = v.m20();
        this.m30 = v.m30();
        this.m01 = v.m01();
        this.m11 = v.m11();
        this.m21 = v.m21();
        this.m31 = v.m31();
        this.m02 = v.m02();
        this.m12 = v.m12();
        this.m22 = v.m22();
        this.m32 = v.m32();
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
        this.m00 = m00;
        this.m10 = m10;
        this.m20 = m20;
        this.m30 = m30;
        this.m01 = m01;
        this.m11 = m11;
        this.m21 = m21;
        this.m31 = m31;
        this.m02 = m02;
        this.m12 = m12;
        this.m22 = m22;
        this.m32 = m32;
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
        Double4x3Impl d = (Double4x3Impl) dest;
        d.m00 = this.m00;
        d.m10 = this.m10;
        d.m20 = this.m20;
        d.m30 = this.m30;
        d.m01 = this.m01;
        d.m11 = this.m11;
        d.m21 = this.m21;
        d.m31 = this.m31;
        d.m02 = this.m02;
        d.m12 = this.m12;
        d.m22 = this.m22;
        d.m32 = this.m32;
        return d;
    }


    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated public Float4x3 makeIdentity() {
        this.m00 = 1.0f;
        this.m10 = 0.0f;
        this.m20 = 0.0f;
        this.m30 = 0.0f;
        this.m01 = 0.0f;
        this.m11 = 1.0f;
        this.m21 = 0.0f;
        this.m31 = 0.0f;
        this.m02 = 0.0f;
        this.m12 = 0.0f;
        this.m22 = 1.0f;
        this.m32 = 0.0f;
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
        Float4x3Impl d = (Float4x3Impl) dest;
        d.m00 = Math.fma(t, other.m00() - this.m00, this.m00);
        d.m10 = Math.fma(t, other.m10() - this.m10, this.m10);
        d.m20 = Math.fma(t, other.m20() - this.m20, this.m20);
        d.m30 = Math.fma(t, other.m30() - this.m30, this.m30);
        d.m01 = Math.fma(t, other.m01() - this.m01, this.m01);
        d.m11 = Math.fma(t, other.m11() - this.m11, this.m11);
        d.m21 = Math.fma(t, other.m21() - this.m21, this.m21);
        d.m31 = Math.fma(t, other.m31() - this.m31, this.m31);
        d.m02 = Math.fma(t, other.m02() - this.m02, this.m02);
        d.m12 = Math.fma(t, other.m12() - this.m12, this.m12);
        d.m22 = Math.fma(t, other.m22() - this.m22, this.m22);
        d.m32 = Math.fma(t, other.m32() - this.m32, this.m32);
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
        Float4x3Impl d = (Float4x3Impl) dest;
        d.m00 = Math.fma(t, m00 - this.m00, this.m00);
        d.m10 = Math.fma(t, m10 - this.m10, this.m10);
        d.m20 = Math.fma(t, m20 - this.m20, this.m20);
        d.m30 = Math.fma(t, m30 - this.m30, this.m30);
        d.m01 = Math.fma(t, m01 - this.m01, this.m01);
        d.m11 = Math.fma(t, m11 - this.m11, this.m11);
        d.m21 = Math.fma(t, m21 - this.m21, this.m21);
        d.m31 = Math.fma(t, m31 - this.m31, this.m31);
        d.m02 = Math.fma(t, m02 - this.m02, this.m02);
        d.m12 = Math.fma(t, m12 - this.m12, this.m12);
        d.m22 = Math.fma(t, m22 - this.m22, this.m22);
        d.m32 = Math.fma(t, m32 - this.m32, this.m32);
        return d;
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
        Double4x3Impl d = (Double4x3Impl) dest;
        d.m00 = Math.fma(t, m00 - this.m00, this.m00);
        d.m10 = Math.fma(t, m10 - this.m10, this.m10);
        d.m20 = Math.fma(t, m20 - this.m20, this.m20);
        d.m30 = Math.fma(t, m30 - this.m30, this.m30);
        d.m01 = Math.fma(t, m01 - this.m01, this.m01);
        d.m11 = Math.fma(t, m11 - this.m11, this.m11);
        d.m21 = Math.fma(t, m21 - this.m21, this.m21);
        d.m31 = Math.fma(t, m31 - this.m31, this.m31);
        d.m02 = Math.fma(t, m02 - this.m02, this.m02);
        d.m12 = Math.fma(t, m12 - this.m12, this.m12);
        d.m22 = Math.fma(t, m22 - this.m22, this.m22);
        d.m32 = Math.fma(t, m32 - this.m32, this.m32);
        return d;
    }

    /** Private column 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s161385dd_c0_fma(Float4x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20) {
        _dst.m00 = java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, _r4 * _r5));
        _dst.m10 = java.lang.Math.fma(_r0, _r12, java.lang.Math.fma(_r2, _r13, _r4 * _r14));
        _dst.m20 = java.lang.Math.fma(_r0, _r15, java.lang.Math.fma(_r2, _r16, _r4 * _r17));
        _dst.m30 = java.lang.Math.fma(_r0, _r18, java.lang.Math.fma(_r2, _r19, _r4 * _r20));
    }

    /** Private column 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s161385dd_c0_mulAdd(Float4x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20) {
        _dst.m00 = ((_r0) * (_r1) + (((_r2) * (_r3) + (_r4 * _r5))));
        _dst.m10 = ((_r0) * (_r12) + (((_r2) * (_r13) + (_r4 * _r14))));
        _dst.m20 = ((_r0) * (_r15) + (((_r2) * (_r16) + (_r4 * _r17))));
        _dst.m30 = ((_r0) * (_r18) + (((_r2) * (_r19) + (_r4 * _r20))));
    }

    /** Private column 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s161385dd_c1_fma(Float4x3Impl _dst, float _r6, float _r1, float _r7, float _r3, float _r8, float _r5, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20) {
        _dst.m01 = java.lang.Math.fma(_r6, _r1, java.lang.Math.fma(_r7, _r3, _r8 * _r5));
        _dst.m11 = java.lang.Math.fma(_r6, _r12, java.lang.Math.fma(_r7, _r13, _r8 * _r14));
        _dst.m21 = java.lang.Math.fma(_r6, _r15, java.lang.Math.fma(_r7, _r16, _r8 * _r17));
        _dst.m31 = java.lang.Math.fma(_r6, _r18, java.lang.Math.fma(_r7, _r19, _r8 * _r20));
    }

    /** Private column 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s161385dd_c1_mulAdd(Float4x3Impl _dst, float _r6, float _r1, float _r7, float _r3, float _r8, float _r5, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20) {
        _dst.m01 = ((_r6) * (_r1) + (((_r7) * (_r3) + (_r8 * _r5))));
        _dst.m11 = ((_r6) * (_r12) + (((_r7) * (_r13) + (_r8 * _r14))));
        _dst.m21 = ((_r6) * (_r15) + (((_r7) * (_r16) + (_r8 * _r17))));
        _dst.m31 = ((_r6) * (_r18) + (((_r7) * (_r19) + (_r8 * _r20))));
    }

    /** Private column 2 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s161385dd_c2_fma(Float4x3Impl _dst, float _r9, float _r1, float _r10, float _r3, float _r11, float _r5, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20) {
        _dst.m02 = java.lang.Math.fma(_r9, _r1, java.lang.Math.fma(_r10, _r3, _r11 * _r5));
        _dst.m12 = java.lang.Math.fma(_r9, _r12, java.lang.Math.fma(_r10, _r13, _r11 * _r14));
        _dst.m22 = java.lang.Math.fma(_r9, _r15, java.lang.Math.fma(_r10, _r16, _r11 * _r17));
        _dst.m32 = java.lang.Math.fma(_r9, _r18, java.lang.Math.fma(_r10, _r19, _r11 * _r20));
    }

    /** Private column 2 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s161385dd_c2_mulAdd(Float4x3Impl _dst, float _r9, float _r1, float _r10, float _r3, float _r11, float _r5, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20) {
        _dst.m02 = ((_r9) * (_r1) + (((_r10) * (_r3) + (_r11 * _r5))));
        _dst.m12 = ((_r9) * (_r12) + (((_r10) * (_r13) + (_r11 * _r14))));
        _dst.m22 = ((_r9) * (_r15) + (((_r10) * (_r16) + (_r11 * _r17))));
        _dst.m32 = ((_r9) * (_r18) + (((_r10) * (_r19) + (_r11 * _r20))));
    }

    /** Private tail of {@code mul}; reached only through it. */
    private void mul_s161385dd_tail_fma(Float4x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19) {
        float _r20 = this.m31;
        mul_s161385dd_c0_fma(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20);
        mul_s161385dd_c1_fma(_dst, _r6, _r1, _r7, _r3, _r8, _r5, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20);
        mul_s161385dd_c2_fma(_dst, _r9, _r1, _r10, _r3, _r11, _r5, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20);
    }

    /** Private tail of {@code mul}; reached only through it. */
    private void mul_s161385dd_tail_mulAdd(Float4x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19) {
        float _r20 = this.m31;
        mul_s161385dd_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20);
        mul_s161385dd_c1_mulAdd(_dst, _r6, _r1, _r7, _r3, _r8, _r5, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20);
        mul_s161385dd_c2_mulAdd(_dst, _r9, _r1, _r10, _r3, _r11, _r5, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20);
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
        Float4x3Impl d = (Float4x3Impl) dest;
        float _r0 = right.m20();
        float _r1 = this.m02;
        float _r2 = right.m00();
        float _r3 = this.m00;
        float _r4 = right.m10();
        float _r5 = this.m01;
        float _r6 = right.m21();
        float _r7 = right.m01();
        float _r8 = right.m11();
        float _r9 = right.m22();
        float _r10 = right.m02();
        float _r11 = right.m12();
        float _r12 = this.m12;
        float _r13 = this.m10;
        float _r14 = this.m11;
        float _r15 = this.m22;
        float _r16 = this.m20;
        float _r17 = this.m21;
        float _r18 = this.m32;
        float _r19 = this.m30;
        if (Math.useFma()) mul_s161385dd_tail_fma(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19); else mul_s161385dd_tail_mulAdd(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19);
        return d;
    }

    /** Private column 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s7cf23976_c0_fma(Double4x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20) {
        _dst.m00 = java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, _r4 * _r5));
        _dst.m10 = java.lang.Math.fma(_r0, _r12, java.lang.Math.fma(_r2, _r13, _r4 * _r14));
        _dst.m20 = java.lang.Math.fma(_r0, _r15, java.lang.Math.fma(_r2, _r16, _r4 * _r17));
        _dst.m30 = java.lang.Math.fma(_r0, _r18, java.lang.Math.fma(_r2, _r19, _r4 * _r20));
    }

    /** Private column 0 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s7cf23976_c0_mulAdd(Double4x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20) {
        _dst.m00 = ((_r0) * (_r1) + (((_r2) * (_r3) + (_r4 * _r5))));
        _dst.m10 = ((_r0) * (_r12) + (((_r2) * (_r13) + (_r4 * _r14))));
        _dst.m20 = ((_r0) * (_r15) + (((_r2) * (_r16) + (_r4 * _r17))));
        _dst.m30 = ((_r0) * (_r18) + (((_r2) * (_r19) + (_r4 * _r20))));
    }

    /** Private column 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s7cf23976_c1_fma(Double4x3Impl _dst, float _r6, float _r1, float _r7, float _r3, float _r8, float _r5, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20) {
        _dst.m01 = java.lang.Math.fma(_r6, _r1, java.lang.Math.fma(_r7, _r3, _r8 * _r5));
        _dst.m11 = java.lang.Math.fma(_r6, _r12, java.lang.Math.fma(_r7, _r13, _r8 * _r14));
        _dst.m21 = java.lang.Math.fma(_r6, _r15, java.lang.Math.fma(_r7, _r16, _r8 * _r17));
        _dst.m31 = java.lang.Math.fma(_r6, _r18, java.lang.Math.fma(_r7, _r19, _r8 * _r20));
    }

    /** Private column 1 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s7cf23976_c1_mulAdd(Double4x3Impl _dst, float _r6, float _r1, float _r7, float _r3, float _r8, float _r5, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20) {
        _dst.m01 = ((_r6) * (_r1) + (((_r7) * (_r3) + (_r8 * _r5))));
        _dst.m11 = ((_r6) * (_r12) + (((_r7) * (_r13) + (_r8 * _r14))));
        _dst.m21 = ((_r6) * (_r15) + (((_r7) * (_r16) + (_r8 * _r17))));
        _dst.m31 = ((_r6) * (_r18) + (((_r7) * (_r19) + (_r8 * _r20))));
    }

    /** Private column 2 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s7cf23976_c2_fma(Double4x3Impl _dst, float _r9, float _r1, float _r10, float _r3, float _r11, float _r5, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20) {
        _dst.m02 = java.lang.Math.fma(_r9, _r1, java.lang.Math.fma(_r10, _r3, _r11 * _r5));
        _dst.m12 = java.lang.Math.fma(_r9, _r12, java.lang.Math.fma(_r10, _r13, _r11 * _r14));
        _dst.m22 = java.lang.Math.fma(_r9, _r15, java.lang.Math.fma(_r10, _r16, _r11 * _r17));
        _dst.m32 = java.lang.Math.fma(_r9, _r18, java.lang.Math.fma(_r10, _r19, _r11 * _r20));
    }

    /** Private column 2 of {@code mul}: computes and stores it; reached only through it. */
    private void mul_s7cf23976_c2_mulAdd(Double4x3Impl _dst, float _r9, float _r1, float _r10, float _r3, float _r11, float _r5, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20) {
        _dst.m02 = ((_r9) * (_r1) + (((_r10) * (_r3) + (_r11 * _r5))));
        _dst.m12 = ((_r9) * (_r12) + (((_r10) * (_r13) + (_r11 * _r14))));
        _dst.m22 = ((_r9) * (_r15) + (((_r10) * (_r16) + (_r11 * _r17))));
        _dst.m32 = ((_r9) * (_r18) + (((_r10) * (_r19) + (_r11 * _r20))));
    }

    /** Private tail of {@code mul}; reached only through it. */
    private void mul_s7cf23976_tail_fma(Double4x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19) {
        float _r20 = this.m31;
        mul_s7cf23976_c0_fma(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20);
        mul_s7cf23976_c1_fma(_dst, _r6, _r1, _r7, _r3, _r8, _r5, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20);
        mul_s7cf23976_c2_fma(_dst, _r9, _r1, _r10, _r3, _r11, _r5, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20);
    }

    /** Private tail of {@code mul}; reached only through it. */
    private void mul_s7cf23976_tail_mulAdd(Double4x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19) {
        float _r20 = this.m31;
        mul_s7cf23976_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20);
        mul_s7cf23976_c1_mulAdd(_dst, _r6, _r1, _r7, _r3, _r8, _r5, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20);
        mul_s7cf23976_c2_mulAdd(_dst, _r9, _r1, _r10, _r3, _r11, _r5, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19, _r20);
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
        Double4x3Impl d = (Double4x3Impl) dest;
        float _r0 = right.m20();
        float _r1 = this.m02;
        float _r2 = right.m00();
        float _r3 = this.m00;
        float _r4 = right.m10();
        float _r5 = this.m01;
        float _r6 = right.m21();
        float _r7 = right.m01();
        float _r8 = right.m11();
        float _r9 = right.m22();
        float _r10 = right.m02();
        float _r11 = right.m12();
        float _r12 = this.m12;
        float _r13 = this.m10;
        float _r14 = this.m11;
        float _r15 = this.m22;
        float _r16 = this.m20;
        float _r17 = this.m21;
        float _r18 = this.m32;
        float _r19 = this.m30;
        if (Math.useFma()) mul_s7cf23976_tail_fma(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19); else mul_s7cf23976_tail_mulAdd(d, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19);
        return d;
    }

    /** Private column 0 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s63d3aacf_c0_fma(Float4x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r16, float _r17, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _r27) {
        _dst.m00 = java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, java.lang.Math.fma(_r4, _r5, _r6 * _r7)));
        _dst.m10 = java.lang.Math.fma(_r16, _r1, java.lang.Math.fma(_r17, _r3, java.lang.Math.fma(_r18, _r5, _r19 * _r7)));
        _dst.m20 = java.lang.Math.fma(_r20, _r1, java.lang.Math.fma(_r21, _r3, java.lang.Math.fma(_r22, _r5, _r23 * _r7)));
        _dst.m30 = java.lang.Math.fma(_r24, _r1, java.lang.Math.fma(_r25, _r3, java.lang.Math.fma(_r26, _r5, _r27 * _r7)));
    }

    /** Private column 0 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s63d3aacf_c0_mulAdd(Float4x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r16, float _r17, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _r27) {
        _dst.m00 = ((_r0) * (_r1) + (((_r2) * (_r3) + (((_r4) * (_r5) + (_r6 * _r7))))));
        _dst.m10 = ((_r16) * (_r1) + (((_r17) * (_r3) + (((_r18) * (_r5) + (_r19 * _r7))))));
        _dst.m20 = ((_r20) * (_r1) + (((_r21) * (_r3) + (((_r22) * (_r5) + (_r23 * _r7))))));
        _dst.m30 = ((_r24) * (_r1) + (((_r25) * (_r3) + (((_r26) * (_r5) + (_r27 * _r7))))));
    }

    /** Private column 1 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s63d3aacf_c1_fma(Float4x3Impl _dst, float _r0, float _r8, float _r2, float _r9, float _r4, float _r10, float _r6, float _r11, float _r16, float _r17, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _r27) {
        _dst.m01 = java.lang.Math.fma(_r0, _r8, java.lang.Math.fma(_r2, _r9, java.lang.Math.fma(_r4, _r10, _r6 * _r11)));
        _dst.m11 = java.lang.Math.fma(_r16, _r8, java.lang.Math.fma(_r17, _r9, java.lang.Math.fma(_r18, _r10, _r19 * _r11)));
        _dst.m21 = java.lang.Math.fma(_r20, _r8, java.lang.Math.fma(_r21, _r9, java.lang.Math.fma(_r22, _r10, _r23 * _r11)));
        _dst.m31 = java.lang.Math.fma(_r24, _r8, java.lang.Math.fma(_r25, _r9, java.lang.Math.fma(_r26, _r10, _r27 * _r11)));
    }

    /** Private column 1 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s63d3aacf_c1_mulAdd(Float4x3Impl _dst, float _r0, float _r8, float _r2, float _r9, float _r4, float _r10, float _r6, float _r11, float _r16, float _r17, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _r27) {
        _dst.m01 = ((_r0) * (_r8) + (((_r2) * (_r9) + (((_r4) * (_r10) + (_r6 * _r11))))));
        _dst.m11 = ((_r16) * (_r8) + (((_r17) * (_r9) + (((_r18) * (_r10) + (_r19 * _r11))))));
        _dst.m21 = ((_r20) * (_r8) + (((_r21) * (_r9) + (((_r22) * (_r10) + (_r23 * _r11))))));
        _dst.m31 = ((_r24) * (_r8) + (((_r25) * (_r9) + (((_r26) * (_r10) + (_r27 * _r11))))));
    }

    /** Private column 2 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s63d3aacf_c2_fma(Float4x3Impl _dst, float _r0, float _r12, float _r2, float _r13, float _r4, float _r14, float _r6, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _r27) {
        _dst.m02 = java.lang.Math.fma(_r0, _r12, java.lang.Math.fma(_r2, _r13, java.lang.Math.fma(_r4, _r14, _r6 * _r15)));
        _dst.m12 = java.lang.Math.fma(_r16, _r12, java.lang.Math.fma(_r17, _r13, java.lang.Math.fma(_r18, _r14, _r19 * _r15)));
        _dst.m22 = java.lang.Math.fma(_r20, _r12, java.lang.Math.fma(_r21, _r13, java.lang.Math.fma(_r22, _r14, _r23 * _r15)));
        _dst.m32 = java.lang.Math.fma(_r24, _r12, java.lang.Math.fma(_r25, _r13, java.lang.Math.fma(_r26, _r14, _r27 * _r15)));
    }

    /** Private column 2 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s63d3aacf_c2_mulAdd(Float4x3Impl _dst, float _r0, float _r12, float _r2, float _r13, float _r4, float _r14, float _r6, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _r27) {
        _dst.m02 = ((_r0) * (_r12) + (((_r2) * (_r13) + (((_r4) * (_r14) + (_r6 * _r15))))));
        _dst.m12 = ((_r16) * (_r12) + (((_r17) * (_r13) + (((_r18) * (_r14) + (_r19 * _r15))))));
        _dst.m22 = ((_r20) * (_r12) + (((_r21) * (_r13) + (((_r22) * (_r14) + (_r23 * _r15))))));
        _dst.m32 = ((_r24) * (_r12) + (((_r25) * (_r13) + (((_r26) * (_r14) + (_r27 * _r15))))));
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private void preMul_s63d3aacf_tail_fma(Float4x3Impl _dst, Float4x4R other, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19) {
        float _r20 = other.m23();
        float _r21 = other.m22();
        float _r22 = other.m20();
        float _r23 = other.m21();
        float _r24 = other.m33();
        float _r25 = other.m32();
        float _r26 = other.m30();
        float _r27 = other.m31();
        preMul_s63d3aacf_c0_fma(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r16, _r17, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _r27);
        preMul_s63d3aacf_c1_fma(_dst, _r0, _r8, _r2, _r9, _r4, _r10, _r6, _r11, _r16, _r17, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _r27);
        preMul_s63d3aacf_c2_fma(_dst, _r0, _r12, _r2, _r13, _r4, _r14, _r6, _r15, _r16, _r17, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _r27);
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private void preMul_s63d3aacf_tail_mulAdd(Float4x3Impl _dst, Float4x4R other, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19) {
        float _r20 = other.m23();
        float _r21 = other.m22();
        float _r22 = other.m20();
        float _r23 = other.m21();
        float _r24 = other.m33();
        float _r25 = other.m32();
        float _r26 = other.m30();
        float _r27 = other.m31();
        preMul_s63d3aacf_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r16, _r17, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _r27);
        preMul_s63d3aacf_c1_mulAdd(_dst, _r0, _r8, _r2, _r9, _r4, _r10, _r6, _r11, _r16, _r17, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _r27);
        preMul_s63d3aacf_c2_mulAdd(_dst, _r0, _r12, _r2, _r13, _r4, _r14, _r6, _r15, _r16, _r17, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _r27);
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
        Float4x3Impl d = (Float4x3Impl) dest;
        float _r0 = other.m03();
        float _r1 = this.m30;
        float _r2 = other.m02();
        float _r3 = this.m20;
        float _r4 = other.m00();
        float _r5 = this.m00;
        float _r6 = other.m01();
        float _r7 = this.m10;
        float _r8 = this.m31;
        float _r9 = this.m21;
        float _r10 = this.m01;
        float _r11 = this.m11;
        float _r12 = this.m32;
        float _r13 = this.m22;
        float _r14 = this.m02;
        float _r15 = this.m12;
        float _r16 = other.m13();
        float _r17 = other.m12();
        float _r18 = other.m10();
        float _r19 = other.m11();
        if (Math.useFma()) preMul_s63d3aacf_tail_fma(d, other, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19); else preMul_s63d3aacf_tail_mulAdd(d, other, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19);
        return d;
    }

    /** Private column 0 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s6736b2c4_c0_fma(Double4x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r16, float _r17, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _r27) {
        _dst.m00 = java.lang.Math.fma(_r0, _r1, java.lang.Math.fma(_r2, _r3, java.lang.Math.fma(_r4, _r5, _r6 * _r7)));
        _dst.m10 = java.lang.Math.fma(_r16, _r1, java.lang.Math.fma(_r17, _r3, java.lang.Math.fma(_r18, _r5, _r19 * _r7)));
        _dst.m20 = java.lang.Math.fma(_r20, _r1, java.lang.Math.fma(_r21, _r3, java.lang.Math.fma(_r22, _r5, _r23 * _r7)));
        _dst.m30 = java.lang.Math.fma(_r24, _r1, java.lang.Math.fma(_r25, _r3, java.lang.Math.fma(_r26, _r5, _r27 * _r7)));
    }

    /** Private column 0 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s6736b2c4_c0_mulAdd(Double4x3Impl _dst, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r16, float _r17, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _r27) {
        _dst.m00 = ((_r0) * (_r1) + (((_r2) * (_r3) + (((_r4) * (_r5) + (_r6 * _r7))))));
        _dst.m10 = ((_r16) * (_r1) + (((_r17) * (_r3) + (((_r18) * (_r5) + (_r19 * _r7))))));
        _dst.m20 = ((_r20) * (_r1) + (((_r21) * (_r3) + (((_r22) * (_r5) + (_r23 * _r7))))));
        _dst.m30 = ((_r24) * (_r1) + (((_r25) * (_r3) + (((_r26) * (_r5) + (_r27 * _r7))))));
    }

    /** Private column 1 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s6736b2c4_c1_fma(Double4x3Impl _dst, float _r0, float _r8, float _r2, float _r9, float _r4, float _r10, float _r6, float _r11, float _r16, float _r17, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _r27) {
        _dst.m01 = java.lang.Math.fma(_r0, _r8, java.lang.Math.fma(_r2, _r9, java.lang.Math.fma(_r4, _r10, _r6 * _r11)));
        _dst.m11 = java.lang.Math.fma(_r16, _r8, java.lang.Math.fma(_r17, _r9, java.lang.Math.fma(_r18, _r10, _r19 * _r11)));
        _dst.m21 = java.lang.Math.fma(_r20, _r8, java.lang.Math.fma(_r21, _r9, java.lang.Math.fma(_r22, _r10, _r23 * _r11)));
        _dst.m31 = java.lang.Math.fma(_r24, _r8, java.lang.Math.fma(_r25, _r9, java.lang.Math.fma(_r26, _r10, _r27 * _r11)));
    }

    /** Private column 1 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s6736b2c4_c1_mulAdd(Double4x3Impl _dst, float _r0, float _r8, float _r2, float _r9, float _r4, float _r10, float _r6, float _r11, float _r16, float _r17, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _r27) {
        _dst.m01 = ((_r0) * (_r8) + (((_r2) * (_r9) + (((_r4) * (_r10) + (_r6 * _r11))))));
        _dst.m11 = ((_r16) * (_r8) + (((_r17) * (_r9) + (((_r18) * (_r10) + (_r19 * _r11))))));
        _dst.m21 = ((_r20) * (_r8) + (((_r21) * (_r9) + (((_r22) * (_r10) + (_r23 * _r11))))));
        _dst.m31 = ((_r24) * (_r8) + (((_r25) * (_r9) + (((_r26) * (_r10) + (_r27 * _r11))))));
    }

    /** Private column 2 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s6736b2c4_c2_fma(Double4x3Impl _dst, float _r0, float _r12, float _r2, float _r13, float _r4, float _r14, float _r6, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _r27) {
        _dst.m02 = java.lang.Math.fma(_r0, _r12, java.lang.Math.fma(_r2, _r13, java.lang.Math.fma(_r4, _r14, _r6 * _r15)));
        _dst.m12 = java.lang.Math.fma(_r16, _r12, java.lang.Math.fma(_r17, _r13, java.lang.Math.fma(_r18, _r14, _r19 * _r15)));
        _dst.m22 = java.lang.Math.fma(_r20, _r12, java.lang.Math.fma(_r21, _r13, java.lang.Math.fma(_r22, _r14, _r23 * _r15)));
        _dst.m32 = java.lang.Math.fma(_r24, _r12, java.lang.Math.fma(_r25, _r13, java.lang.Math.fma(_r26, _r14, _r27 * _r15)));
    }

    /** Private column 2 of {@code preMul}: computes and stores it; reached only through it. */
    private void preMul_s6736b2c4_c2_mulAdd(Double4x3Impl _dst, float _r0, float _r12, float _r2, float _r13, float _r4, float _r14, float _r6, float _r15, float _r16, float _r17, float _r18, float _r19, float _r20, float _r21, float _r22, float _r23, float _r24, float _r25, float _r26, float _r27) {
        _dst.m02 = ((_r0) * (_r12) + (((_r2) * (_r13) + (((_r4) * (_r14) + (_r6 * _r15))))));
        _dst.m12 = ((_r16) * (_r12) + (((_r17) * (_r13) + (((_r18) * (_r14) + (_r19 * _r15))))));
        _dst.m22 = ((_r20) * (_r12) + (((_r21) * (_r13) + (((_r22) * (_r14) + (_r23 * _r15))))));
        _dst.m32 = ((_r24) * (_r12) + (((_r25) * (_r13) + (((_r26) * (_r14) + (_r27 * _r15))))));
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private void preMul_s6736b2c4_tail_fma(Double4x3Impl _dst, Float4x4R other, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19) {
        float _r20 = other.m23();
        float _r21 = other.m22();
        float _r22 = other.m20();
        float _r23 = other.m21();
        float _r24 = other.m33();
        float _r25 = other.m32();
        float _r26 = other.m30();
        float _r27 = other.m31();
        preMul_s6736b2c4_c0_fma(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r16, _r17, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _r27);
        preMul_s6736b2c4_c1_fma(_dst, _r0, _r8, _r2, _r9, _r4, _r10, _r6, _r11, _r16, _r17, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _r27);
        preMul_s6736b2c4_c2_fma(_dst, _r0, _r12, _r2, _r13, _r4, _r14, _r6, _r15, _r16, _r17, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _r27);
    }

    /** Private tail of {@code preMul}; reached only through it. */
    private void preMul_s6736b2c4_tail_mulAdd(Double4x3Impl _dst, Float4x4R other, float _r0, float _r1, float _r2, float _r3, float _r4, float _r5, float _r6, float _r7, float _r8, float _r9, float _r10, float _r11, float _r12, float _r13, float _r14, float _r15, float _r16, float _r17, float _r18, float _r19) {
        float _r20 = other.m23();
        float _r21 = other.m22();
        float _r22 = other.m20();
        float _r23 = other.m21();
        float _r24 = other.m33();
        float _r25 = other.m32();
        float _r26 = other.m30();
        float _r27 = other.m31();
        preMul_s6736b2c4_c0_mulAdd(_dst, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r16, _r17, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _r27);
        preMul_s6736b2c4_c1_mulAdd(_dst, _r0, _r8, _r2, _r9, _r4, _r10, _r6, _r11, _r16, _r17, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _r27);
        preMul_s6736b2c4_c2_mulAdd(_dst, _r0, _r12, _r2, _r13, _r4, _r14, _r6, _r15, _r16, _r17, _r18, _r19, _r20, _r21, _r22, _r23, _r24, _r25, _r26, _r27);
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
        Double4x3Impl d = (Double4x3Impl) dest;
        float _r0 = other.m03();
        float _r1 = this.m30;
        float _r2 = other.m02();
        float _r3 = this.m20;
        float _r4 = other.m00();
        float _r5 = this.m00;
        float _r6 = other.m01();
        float _r7 = this.m10;
        float _r8 = this.m31;
        float _r9 = this.m21;
        float _r10 = this.m01;
        float _r11 = this.m11;
        float _r12 = this.m32;
        float _r13 = this.m22;
        float _r14 = this.m02;
        float _r15 = this.m12;
        float _r16 = other.m13();
        float _r17 = other.m12();
        float _r18 = other.m10();
        float _r19 = other.m11();
        if (Math.useFma()) preMul_s6736b2c4_tail_fma(d, other, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19); else preMul_s6736b2c4_tail_mulAdd(d, other, _r0, _r1, _r2, _r3, _r4, _r5, _r6, _r7, _r8, _r9, _r10, _r11, _r12, _r13, _r14, _r15, _r16, _r17, _r18, _r19);
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
    public Float4x3 addScaled(Float4x3R other, float weight, @Mutated Float4x3 dest) {
        Float4x3Impl d = (Float4x3Impl) dest;
        d.m00 = Math.fma(weight, other.m00(), this.m00);
        d.m10 = Math.fma(weight, other.m10(), this.m10);
        d.m20 = Math.fma(weight, other.m20(), this.m20);
        d.m30 = Math.fma(weight, other.m30(), this.m30);
        d.m01 = Math.fma(weight, other.m01(), this.m01);
        d.m11 = Math.fma(weight, other.m11(), this.m11);
        d.m21 = Math.fma(weight, other.m21(), this.m21);
        d.m31 = Math.fma(weight, other.m31(), this.m31);
        d.m02 = Math.fma(weight, other.m02(), this.m02);
        d.m12 = Math.fma(weight, other.m12(), this.m12);
        d.m22 = Math.fma(weight, other.m22(), this.m22);
        d.m32 = Math.fma(weight, other.m32(), this.m32);
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
        Double4x3Impl d = (Double4x3Impl) dest;
        d.m00 = Math.fma(weight, other.m00(), this.m00);
        d.m10 = Math.fma(weight, m10, this.m10);
        d.m20 = Math.fma(weight, m20, this.m20);
        d.m30 = Math.fma(weight, m30, this.m30);
        d.m01 = Math.fma(weight, m01, this.m01);
        d.m11 = Math.fma(weight, m11, this.m11);
        d.m21 = Math.fma(weight, m21, this.m21);
        d.m31 = Math.fma(weight, m31, this.m31);
        d.m02 = Math.fma(weight, m02, this.m02);
        d.m12 = Math.fma(weight, m12, this.m12);
        d.m22 = Math.fma(weight, m22, this.m22);
        d.m32 = Math.fma(weight, m32, this.m32);
        return d;
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
        Float4x3Impl d = (Float4x3Impl) dest;
        d.m00 = Math.fma(weight, m00, this.m00);
        d.m10 = Math.fma(weight, m10, this.m10);
        d.m20 = Math.fma(weight, m20, this.m20);
        d.m30 = Math.fma(weight, m30, this.m30);
        d.m01 = Math.fma(weight, m01, this.m01);
        d.m11 = Math.fma(weight, m11, this.m11);
        d.m21 = Math.fma(weight, m21, this.m21);
        d.m31 = Math.fma(weight, m31, this.m31);
        d.m02 = Math.fma(weight, m02, this.m02);
        d.m12 = Math.fma(weight, m12, this.m12);
        d.m22 = Math.fma(weight, m22, this.m22);
        d.m32 = Math.fma(weight, m32, this.m32);
        return d;
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
        Double4x3Impl d = (Double4x3Impl) dest;
        d.m00 = Math.fma(weight, m00, this.m00);
        d.m10 = Math.fma(weight, m10, this.m10);
        d.m20 = Math.fma(weight, m20, this.m20);
        d.m30 = Math.fma(weight, m30, this.m30);
        d.m01 = Math.fma(weight, m01, this.m01);
        d.m11 = Math.fma(weight, m11, this.m11);
        d.m21 = Math.fma(weight, m21, this.m21);
        d.m31 = Math.fma(weight, m31, this.m31);
        d.m02 = Math.fma(weight, m02, this.m02);
        d.m12 = Math.fma(weight, m12, this.m12);
        d.m22 = Math.fma(weight, m22, this.m22);
        d.m32 = Math.fma(weight, m32, this.m32);
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
    public Float4 mul(Float3R v, @Mutated Float4 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        if (Math.useFma()) {
            Float4Impl d = (Float4Impl) dest;
            d.x = java.lang.Math.fma(this.m02, vZ, java.lang.Math.fma(this.m00, vX, this.m01 * vY));
            d.y = java.lang.Math.fma(this.m12, vZ, java.lang.Math.fma(this.m10, vX, this.m11 * vY));
            d.z = java.lang.Math.fma(this.m22, vZ, java.lang.Math.fma(this.m20, vX, this.m21 * vY));
            d.w = java.lang.Math.fma(this.m32, vZ, java.lang.Math.fma(this.m30, vX, this.m31 * vY));
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            d.x = ((this.m02) * (vZ) + (((this.m00) * (vX) + (this.m01 * vY))));
            d.y = ((this.m12) * (vZ) + (((this.m10) * (vX) + (this.m11 * vY))));
            d.z = ((this.m22) * (vZ) + (((this.m20) * (vX) + (this.m21 * vY))));
            d.w = ((this.m32) * (vZ) + (((this.m30) * (vX) + (this.m31 * vY))));
            return d;
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
            Double4Impl d = (Double4Impl) dest;
            d.x = java.lang.Math.fma(this.m02, vZ, java.lang.Math.fma(this.m00, vX, this.m01 * vY));
            d.y = java.lang.Math.fma(this.m12, vZ, java.lang.Math.fma(this.m10, vX, this.m11 * vY));
            d.z = java.lang.Math.fma(this.m22, vZ, java.lang.Math.fma(this.m20, vX, this.m21 * vY));
            d.w = java.lang.Math.fma(this.m32, vZ, java.lang.Math.fma(this.m30, vX, this.m31 * vY));
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            d.x = ((this.m02) * (vZ) + (((this.m00) * (vX) + (this.m01 * vY))));
            d.y = ((this.m12) * (vZ) + (((this.m10) * (vX) + (this.m11 * vY))));
            d.z = ((this.m22) * (vZ) + (((this.m20) * (vX) + (this.m21 * vY))));
            d.w = ((this.m32) * (vZ) + (((this.m30) * (vX) + (this.m31 * vY))));
            return d;
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
            Float4Impl d = (Float4Impl) dest;
            d.x = java.lang.Math.fma(this.m02, vZ, java.lang.Math.fma(this.m00, vX, this.m01 * vY));
            d.y = java.lang.Math.fma(this.m12, vZ, java.lang.Math.fma(this.m10, vX, this.m11 * vY));
            d.z = java.lang.Math.fma(this.m22, vZ, java.lang.Math.fma(this.m20, vX, this.m21 * vY));
            d.w = java.lang.Math.fma(this.m32, vZ, java.lang.Math.fma(this.m30, vX, this.m31 * vY));
            return d;
        } else {
            Float4Impl d = (Float4Impl) dest;
            d.x = ((this.m02) * (vZ) + (((this.m00) * (vX) + (this.m01 * vY))));
            d.y = ((this.m12) * (vZ) + (((this.m10) * (vX) + (this.m11 * vY))));
            d.z = ((this.m22) * (vZ) + (((this.m20) * (vX) + (this.m21 * vY))));
            d.w = ((this.m32) * (vZ) + (((this.m30) * (vX) + (this.m31 * vY))));
            return d;
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
            Double4Impl d = (Double4Impl) dest;
            d.x = java.lang.Math.fma(this.m02, vZ, java.lang.Math.fma(this.m00, vX, this.m01 * vY));
            d.y = java.lang.Math.fma(this.m12, vZ, java.lang.Math.fma(this.m10, vX, this.m11 * vY));
            d.z = java.lang.Math.fma(this.m22, vZ, java.lang.Math.fma(this.m20, vX, this.m21 * vY));
            d.w = java.lang.Math.fma(this.m32, vZ, java.lang.Math.fma(this.m30, vX, this.m31 * vY));
            return d;
        } else {
            Double4Impl d = (Double4Impl) dest;
            d.x = ((this.m02) * (vZ) + (((this.m00) * (vX) + (this.m01 * vY))));
            d.y = ((this.m12) * (vZ) + (((this.m10) * (vX) + (this.m11 * vY))));
            d.z = ((this.m22) * (vZ) + (((this.m20) * (vX) + (this.m21 * vY))));
            d.w = ((this.m32) * (vZ) + (((this.m30) * (vX) + (this.m31 * vY))));
            return d;
        }
    }

    public float m00() { return this.m00; }
    public float m01() { return this.m01; }
    public float m02() { return this.m02; }
    public float m10() { return this.m10; }
    public float m11() { return this.m11; }
    public float m12() { return this.m12; }
    public float m20() { return this.m20; }
    public float m21() { return this.m21; }
    public float m22() { return this.m22; }
    public float m30() { return this.m30; }
    public float m31() { return this.m31; }
    public float m32() { return this.m32; }

    @Override public String toString() {
        return "Float4x3(\n    " + m00() + ", " + m01() + ", " + m02() + "\n    " + m10() + ", " + m11() + ", " + m12() + "\n    " + m20() + ", " + m21() + ", " + m22() + "\n    " + m30() + ", " + m31() + ", " + m32() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float4x3Impl)) return false;
        Float4x3Impl o = (Float4x3Impl) obj;
        return Float.floatToIntBits(m00) == Float.floatToIntBits(o.m00)
            && Float.floatToIntBits(m01) == Float.floatToIntBits(o.m01)
            && Float.floatToIntBits(m02) == Float.floatToIntBits(o.m02)
            && Float.floatToIntBits(m10) == Float.floatToIntBits(o.m10)
            && Float.floatToIntBits(m11) == Float.floatToIntBits(o.m11)
            && Float.floatToIntBits(m12) == Float.floatToIntBits(o.m12)
            && Float.floatToIntBits(m20) == Float.floatToIntBits(o.m20)
            && Float.floatToIntBits(m21) == Float.floatToIntBits(o.m21)
            && Float.floatToIntBits(m22) == Float.floatToIntBits(o.m22)
            && Float.floatToIntBits(m30) == Float.floatToIntBits(o.m30)
            && Float.floatToIntBits(m31) == Float.floatToIntBits(o.m31)
            && Float.floatToIntBits(m32) == Float.floatToIntBits(o.m32);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(m00);
        h = 31 * h + Float.floatToIntBits(m01);
        h = 31 * h + Float.floatToIntBits(m02);
        h = 31 * h + Float.floatToIntBits(m10);
        h = 31 * h + Float.floatToIntBits(m11);
        h = 31 * h + Float.floatToIntBits(m12);
        h = 31 * h + Float.floatToIntBits(m20);
        h = 31 * h + Float.floatToIntBits(m21);
        h = 31 * h + Float.floatToIntBits(m22);
        h = 31 * h + Float.floatToIntBits(m30);
        h = 31 * h + Float.floatToIntBits(m31);
        h = 31 * h + Float.floatToIntBits(m32);
        return h;
    }

    @Override public boolean isFinite() {
        return Float.isFinite(m00)
            && Float.isFinite(m01)
            && Float.isFinite(m02)
            && Float.isFinite(m10)
            && Float.isFinite(m11)
            && Float.isFinite(m12)
            && Float.isFinite(m20)
            && Float.isFinite(m21)
            && Float.isFinite(m22)
            && Float.isFinite(m30)
            && Float.isFinite(m31)
            && Float.isFinite(m32);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(m00)
            || Float.isNaN(m01)
            || Float.isNaN(m02)
            || Float.isNaN(m10)
            || Float.isNaN(m11)
            || Float.isNaN(m12)
            || Float.isNaN(m20)
            || Float.isNaN(m21)
            || Float.isNaN(m22)
            || Float.isNaN(m30)
            || Float.isNaN(m31)
            || Float.isNaN(m32);
    }

    @Override public boolean equalsEpsilon(Float4x3R other, float epsilon) {
        return java.lang.Math.abs(m00 - other.m00()) <= epsilon
            && java.lang.Math.abs(m01 - other.m01()) <= epsilon
            && java.lang.Math.abs(m02 - other.m02()) <= epsilon
            && java.lang.Math.abs(m10 - other.m10()) <= epsilon
            && java.lang.Math.abs(m11 - other.m11()) <= epsilon
            && java.lang.Math.abs(m12 - other.m12()) <= epsilon
            && java.lang.Math.abs(m20 - other.m20()) <= epsilon
            && java.lang.Math.abs(m21 - other.m21()) <= epsilon
            && java.lang.Math.abs(m22 - other.m22()) <= epsilon
            && java.lang.Math.abs(m30 - other.m30()) <= epsilon
            && java.lang.Math.abs(m31 - other.m31()) <= epsilon
            && java.lang.Math.abs(m32 - other.m32()) <= epsilon;
    }

    public float[] storeCM(@Mutated float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[offset + 3] = this.m30;
        dest[offset + 4] = this.m01;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = this.m21;
        dest[offset + 7] = this.m31;
        dest[offset + 8] = this.m02;
        dest[offset + 9] = this.m12;
        dest[offset + 10] = this.m22;
        dest[offset + 11] = this.m32;
        return dest;
    }
    public @Mutated Float4x3 loadCM(float[] src, int offset) {
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m20 = src[offset + 2];
        this.m30 = src[offset + 3];
        this.m01 = src[offset + 4];
        this.m11 = src[offset + 5];
        this.m21 = src[offset + 6];
        this.m31 = src[offset + 7];
        this.m02 = src[offset + 8];
        this.m12 = src[offset + 9];
        this.m22 = src[offset + 10];
        this.m32 = src[offset + 11];
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

    public double[] storeCM(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[offset + 3] = this.m30;
        dest[offset + 4] = this.m01;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = this.m21;
        dest[offset + 7] = this.m31;
        dest[offset + 8] = this.m02;
        dest[offset + 9] = this.m12;
        dest[offset + 10] = this.m22;
        dest[offset + 11] = this.m32;
        return dest;
    }
    public @Mutated Float4x3 loadCM(double[] src, int offset) {
        this.m00 = (float) src[offset];
        this.m10 = (float) src[offset + 1];
        this.m20 = (float) src[offset + 2];
        this.m30 = (float) src[offset + 3];
        this.m01 = (float) src[offset + 4];
        this.m11 = (float) src[offset + 5];
        this.m21 = (float) src[offset + 6];
        this.m31 = (float) src[offset + 7];
        this.m02 = (float) src[offset + 8];
        this.m12 = (float) src[offset + 9];
        this.m22 = (float) src[offset + 10];
        this.m32 = (float) src[offset + 11];
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

    public float[] storeRM(@Mutated float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m10;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = this.m12;
        dest[offset + 6] = this.m20;
        dest[offset + 7] = this.m21;
        dest[offset + 8] = this.m22;
        dest[offset + 9] = this.m30;
        dest[offset + 10] = this.m31;
        dest[offset + 11] = this.m32;
        return dest;
    }
    public @Mutated Float4x3 loadRM(float[] src, int offset) {
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m02 = src[offset + 2];
        this.m10 = src[offset + 3];
        this.m11 = src[offset + 4];
        this.m12 = src[offset + 5];
        this.m20 = src[offset + 6];
        this.m21 = src[offset + 7];
        this.m22 = src[offset + 8];
        this.m30 = src[offset + 9];
        this.m31 = src[offset + 10];
        this.m32 = src[offset + 11];
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

    public double[] storeRM(@Mutated double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m10;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = this.m12;
        dest[offset + 6] = this.m20;
        dest[offset + 7] = this.m21;
        dest[offset + 8] = this.m22;
        dest[offset + 9] = this.m30;
        dest[offset + 10] = this.m31;
        dest[offset + 11] = this.m32;
        return dest;
    }
    public @Mutated Float4x3 loadRM(double[] src, int offset) {
        this.m00 = (float) src[offset];
        this.m01 = (float) src[offset + 1];
        this.m02 = (float) src[offset + 2];
        this.m10 = (float) src[offset + 3];
        this.m11 = (float) src[offset + 4];
        this.m12 = (float) src[offset + 5];
        this.m20 = (float) src[offset + 6];
        this.m21 = (float) src[offset + 7];
        this.m22 = (float) src[offset + 8];
        this.m30 = (float) src[offset + 9];
        this.m31 = (float) src[offset + 10];
        this.m32 = (float) src[offset + 11];
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

    public float[] storeCM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[offset + 3] = this.m30;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m21;
        dest[_p1 + 3] = this.m31;
        dest[_p2] = this.m02;
        dest[_p2 + 1] = this.m12;
        dest[_p2 + 2] = this.m22;
        dest[_p2 + 3] = this.m32;
        return dest;
    }
    public @Mutated Float4x3 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.m00 = src[offset];
        this.m10 = src[offset + 1];
        this.m20 = src[offset + 2];
        this.m30 = src[offset + 3];
        this.m01 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m21 = src[_p1 + 2];
        this.m31 = src[_p1 + 3];
        this.m02 = src[_p2];
        this.m12 = src[_p2 + 1];
        this.m22 = src[_p2 + 2];
        this.m32 = src[_p2 + 3];
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

    public double[] storeCM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[offset + 3] = this.m30;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m21;
        dest[_p1 + 3] = this.m31;
        dest[_p2] = this.m02;
        dest[_p2 + 1] = this.m12;
        dest[_p2 + 2] = this.m22;
        dest[_p2 + 3] = this.m32;
        return dest;
    }
    public @Mutated Float4x3 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        this.m00 = (float) src[offset];
        this.m10 = (float) src[offset + 1];
        this.m20 = (float) src[offset + 2];
        this.m30 = (float) src[offset + 3];
        this.m01 = (float) src[_p1];
        this.m11 = (float) src[_p1 + 1];
        this.m21 = (float) src[_p1 + 2];
        this.m31 = (float) src[_p1 + 3];
        this.m02 = (float) src[_p2];
        this.m12 = (float) src[_p2 + 1];
        this.m22 = (float) src[_p2 + 2];
        this.m32 = (float) src[_p2 + 3];
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

    public float[] storeRM(@Mutated float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m12;
        dest[_p2] = this.m20;
        dest[_p2 + 1] = this.m21;
        dest[_p2 + 2] = this.m22;
        dest[_p3] = this.m30;
        dest[_p3 + 1] = this.m31;
        dest[_p3 + 2] = this.m32;
        return dest;
    }
    public @Mutated Float4x3 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        this.m00 = src[offset];
        this.m01 = src[offset + 1];
        this.m02 = src[offset + 2];
        this.m10 = src[_p1];
        this.m11 = src[_p1 + 1];
        this.m12 = src[_p1 + 2];
        this.m20 = src[_p2];
        this.m21 = src[_p2 + 1];
        this.m22 = src[_p2 + 2];
        this.m30 = src[_p3];
        this.m31 = src[_p3 + 1];
        this.m32 = src[_p3 + 2];
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

    public double[] storeRM(@Mutated double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m12;
        dest[_p2] = this.m20;
        dest[_p2 + 1] = this.m21;
        dest[_p2 + 2] = this.m22;
        dest[_p3] = this.m30;
        dest[_p3 + 1] = this.m31;
        dest[_p3 + 2] = this.m32;
        return dest;
    }
    public @Mutated Float4x3 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        int _p3 = _p2 + stride;
        this.m00 = (float) src[offset];
        this.m01 = (float) src[offset + 1];
        this.m02 = (float) src[offset + 2];
        this.m10 = (float) src[_p1];
        this.m11 = (float) src[_p1 + 1];
        this.m12 = (float) src[_p1 + 2];
        this.m20 = (float) src[_p2];
        this.m21 = (float) src[_p2 + 1];
        this.m22 = (float) src[_p2 + 2];
        this.m30 = (float) src[_p3];
        this.m31 = (float) src[_p3 + 1];
        this.m32 = (float) src[_p3 + 2];
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
}
